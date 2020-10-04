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
<%@include file="common/include-head.jsp"%>

<body>

<%@include file="common/include-nav.jsp"%>

<div class="container-fluid">
    <div class="row">

        <%@include file="common/include-sidebar.jsp"%>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="admin/to/main/page.html">首页</a></li>
                <li><a href="admin/get/page.html">数据列表</a></li>
                <li class="active">更新</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
                <div class="panel-body">
                    <form action="admin/update.html" method="post" role="form">
                        <input type="hidden" name="id" value="${requestScope.admin.id}">
                        <input type="hidden" name="pageNum" value="${param.pageNum}">
                        <input type="hidden" name="keyword" value="${param.keyword}">


                        <p>${requestScope.exception.message}</p>

                        <div class="form-group">
                            <label for="exampleInputPassword1">登录账号</label>
                            <input name="loginAcct" value="${requestScope.admin.loginAcct}" type="text" class="form-control" id="exampleInputPassword1" placeholder="请输入登录账号">
                        </div>



                        <div class="form-group">
                            <label for="exampleInputPassword1">用户昵称</label>
                            <input value="${requestScope.admin.userName}" name="userName"  type="text" class="form-control" id="exampleInputPassword1" placeholder="请输入用户名称">
                        </div>

                        <div class="form-group">
                            <label for="exampleInputEmail1">邮箱地址</label>
                            <input value="${requestScope.admin.email}" name="email"  type="email" class="form-control" id="exampleInputEmail1" placeholder="请输入邮箱地址">
                            <p class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxx@xxxx.com</p>
                        </div>

                        <button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-edit"></i> 修改</button>
                        <button type="reset" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>