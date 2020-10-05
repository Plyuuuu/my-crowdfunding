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
        window.pageNum = 5;
        window.pageSize = 10;
        window.keyword = "";

        //调用执行分页的函数
        generatePage();

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
                                <input class="form-control has-success" type="text" placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button type="button" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
                    </form>
                    <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
                    <button type="button" class="btn btn-primary" style="float:right;" onclick="window.location.href='form.html'"><i class="glyphicon glyphicon-plus"></i> 新增</button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr >
                                <th width="30">#</th>
                                <th width="30"><input type="checkbox"></th>
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

</body>
</html>