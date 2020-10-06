<%--
  Created by IntelliJ IDEA.
  User: LvPeng
  Date: 2020/9/30
  Time: 18:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>

<html lang="zh-CN">
<%@include file="../../common/include-head.jsp"%>

<link rel="stylesheet" href="resource/css/pagination.css"/>
<script type="text/javascript" src="resource/jquery/jquery.pagination.js"></script>



<script type="text/javascript">

    $(function () {

        //为分页操作准备初始化数据
        window.pageNum = 1;
        window.pageSize = 10;
        window.keyword = "";

        //调用执行分页的函数
        generatePage();

        //给查询按钮绑定单击函数
        $("#searchBtn").click(function () {
            // 获取关键词数据，赋值给全局变量
            window.keyword = $("#keywordInput").val();
            // 调用分页函数刷新页面
            generatePage();
        });

        //点击新增按钮打开模态框
        $("#showAddModelBtn").click(function () {
            $("#addModal").modal("show");
        });

        //为模态框内新增按钮绑定单击函数
        $("#saveRoleBtn").click(function () {
            // 获取用户在文本框输入的名称
            // $.trim()除去字符串两端的空格
            var roleName = $.trim($("#addModal [name=roleName]").val());

            //发送Ajax请求
            $.ajax({
                "url":"role/save.json",
                "typr":"post",
                "data":{
                    "name":roleName
                },
                "dataType":"json",
                "success":function (response) {
                    var result = response.result;
                    if (result == "SUCCESS"){
                        layer.msg("操作成功!");

                        //重新加载分页数据
                        window.pageNum = 999999;
                        generatePage();

                    }
                    if (result == "FAILED"){
                        layer.msg("操作失败!"+response.message);
                    }
                },
                "error":function (response) {
                    layer.msg(response.status+" "+response.statusText);
                }
            });

            //关闭模态框
            $("#addModal").modal("hide");

            //清理模态框
            $("#addModal [name=roleName]").var("");

        });

        //给页面上的铅笔按钮绑定单击响应函数,打开模态框
        //传统方式只能在第一页绑定
       /* $(".pencilBtn").click(function () {

        });*/

       //使用jQuery的on函数可以解决上面的问题
        // 首先找到 动态生成 的元素所附着的 静态元素
        $("#rolePageBody").on("click",".pencilBtn",function () {

            //打开模态框
            $("#editModal").modal("show");

            // 获取表格中的当前的角色名称
            var roleName = $(this).parent().prev().text();

            // 获取当前角色的id，依据是：<button id='"+roleId+"'
            //为了后面的请求能用roleID
            window.roleId = this.id;

            // 使用roleName的值设置模态框的文本框
            $("#editModal [name = roleName]").val(roleName);

        });

        // 给模态框中的更新按钮绑定函数
        $("#updateRoleBtn").click(function () {

            //从文本框获取新的角色名称
            var roleName = $("#editModal [name=roleName]").val();

            //发送ajax请求执行更新
            $.ajax({
                "url":"role/update.json",
                "type":"post",
                "data":{
                    "id":window.roleId,
                    "name":roleName
                },
                "dataType":"json",
                "success":function (response) {
                    var result = response.result;
                    if (result == "SUCCESS"){
                        layer.msg("操作成功!");

                        //重新加载分页数据
                        generatePage();

                    }
                    if (result == "FAILED"){
                        layer.msg("操作失败!"+response.message);
                    }
                },
                "error":function (response) {
                    layer.msg(response.status+" "+response.statusText);
                }
            });

            //关闭模态框
            $("#editModal").modal("hide");

        });

        // 点击模态框确认按钮执行删除
        $("#removeRoleBtn").click(function () {

            var requestBody = JSON.stringify(window.roleIdArray);

            $.ajax({
                "url":"role/remove/by/role/id/array.json",
                "type":"post",
                "data":requestBody,
                "contentType":"application/json;charset=UTF-8",
                "dataType":"json",
                "success":function (response) {
                    var result = response.result;
                    if (result == "SUCCESS"){
                        layer.msg("操作成功!");

                        //重新加载分页数据
                        generatePage();

                    }
                    if (result == "FAILED"){
                        layer.msg("操作失败!"+response.message);
                    }
                },
                "error":function (response) {
                    layer.msg(response.status+" "+response.statusText);
                }
            });
            //关闭模态框
            $("#confirmModal").modal("hide");
        });

        // 给单条删除按钮绑定函数
        $("#rolePageBody").on("click",".removeBtn",function () {
            
            // 从当前按钮出发获取角色名称
            var roleName = $(this).parent().prev().text();

            console.log("要删除的角色Id: "+this.id);

            // 创建role对象数组
            var roleArray = [{
                roleId : this.id,
                roleName : roleName
            }]


            //调用专门的函数打开模态框
            showConfirmModal(roleArray);
        });


        //给总的checkBox绑定单击响应函数
        $("#summaryBox").click(function () {

            //获取当前复选框自身状态
            var currentStatus = this.checked;

            // 当前多选框状态设置其他单选框状态
            $(".itemBox").prop("checked",currentStatus);


        });

        //全选、全不选的反向操作
        $("#rolePageBody").on("click",".itemBox",function () {

            //获取当前已经选中itemBox数量
            var checkedBoxCount = $(".itemBox:checked").length;
            //获取全部itemBox数量
            var totalBoxCount = $(".itemBox").length;

            //使用二者比较结果设置总的checkbox
            $("#summaryBox").prop("checked",checkedBoxCount == totalBoxCount);
        });

        //给批量删除绑定单击响应函数
        $("#batchRemoveBtn").click(function () {

            // 创建数组对象用来存放获取到的角色对象
            var roleArray = [];

            //遍历选中的多选框
            $(".itemBox:checked").each(function () {

                // 使用this引用当前遍历得到的多选框
                var roleId = this.id;
                console.log("roleId:"+roleId);

                //通过DOM操作获取角色名称
                var roleName = $(this).parent().next().text();

                roleArray.push({
                    "roleId": roleId,
                    "roleName":roleName,
                });

            });

            console.log(roleArray);

            // 检查roleArray的长度是否为0
            if (roleArray.length == 0){
                layer.msg("您还没选择要删除的角色哦~");
                return;
            }

            //调用专门的函数打开模态框
            showConfirmModal(roleArray);
        });

    });

</script>
<script type="text/javascript" src="resource/myjs/my-role.js"></script>

<body>

<%@include file="../../common/include-nav.jsp"%>

<div class="container-fluid">
    <div class="row">

        <%@include file="../../common/include-sidebar.jsp"%>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input id="keywordInput" class="form-control has-success" type="text" placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button id="searchBtn" type="button" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
                    </form>
                    <button id="batchRemoveBtn" type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
                    <button id="showAddModelBtn" type="button" class="btn btn-primary" style="float:right;" ><i class="glyphicon glyphicon-plus"></i> 新增</button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr >
                                <th width="30">#</th>
                                <th width="30"><input id="summaryBox" type="checkbox"></th>
                                <th>名称</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody id="rolePageBody">


                            </tbody>
                            <tfoot>
                            <tr >
                                <td colspan="6" align="center">

                                    <div id="Pagination" class="pagination"><%--这里显示分页--%></div>


                                </td>
                            </tr>

                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="../../common/model/role/model-role-add.jsp"%>
<%@include file="../../common/model/role/modal-role-edit.jsp"%>
<%@include file="../../common/model/role/model-role-confirm.jsp"%>

</body>
</html>