<%--
  Created by IntelliJ IDEA.
  User: LvPeng
  Date: 2020/9/30
  Time: 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html lang="zh-CN">
<head>


    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">

    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <link rel="stylesheet"  href="resource/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet"  href="resource/css/font-awesome.min.css">
    <link rel="stylesheet"  href="resource/css/login.css">

    <script  src="resource/jquery/jquery-2.1.1.min.js"></script>
    <script  src="resource/bootstrap/js/bootstrap.min.js"></script>
    <style>

    </style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <div><a class="navbar-brand" href="index.html" style="font-size:32px;">筑梦网-实现梦想的平台</a></div>
        </div>
    </div>
</nav>

<div class="container">

    <form action="admin/do/login.html" method="post" class="form-signin" role="form">

        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-log-in"></i> 管理员登录</h2>

        <p>${requestScope.exception.message}</p>

        <div class="form-group has-success has-feedback">
            <input name="loginAcct" type="text" class="form-control" id="inputSuccess1" placeholder="请输入登录账号" autofocus>
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
        </div>

        <div class="form-group has-success has-feedback">
            <input name="userPswd" type="text" class="form-control" id="inputSuccess2" placeholder="请输入登录密码" style="margin-top:10px;">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
        </div>

        <%--<a class="btn btn-lg btn-success btn-block" href="main.html" > 登录</a>--%>
        <button type="submit" class="btn btn-lg btn-success btn-block" >登录</button>

    </form>
</div>

</body>
</html>
