<%--
  Created by IntelliJ IDEA.
  User: LvPeng
  Date: 2020/9/29
  Time: 22:48
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

    <script type="text/javascript">
        $(function () {
            $("#backBtn").click(function () {
                //相当于浏览器后退按钮
                window.history.back();
            });

        });
    </script>
    <style>

    </style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <div><a class="navbar-brand" href="index.html" style="font-size:32px;">筑梦网-帮助你实现梦想的平台</a></div>
        </div>
    </div>
</nav>

<div class="container">

    <h2 class="form-signin-heading"><i class="glyphicon glyphicon-log-in" ></i>System Message</h2>

    <%--
        requestScope:对应存放request域数据的map
        requestScope.exception:相当于request.getAttribute("exception")
        requestScope.exception.message：相当于exception.getMessage()
    --%>
    <h3>${requestScope.exception.message}</h3>

    <button id="backBtn" style="width: 150px ; height: 50px" class="btn btn-lg btn-success btn-block">返回上一步</button><br><br>
    <button id="toLoginPageBtn" style="width: 150px ; height: 50px" class="btn btn-lg btn-success btn-block"><a href="admin/to/login/page.html">前往登录</a></button>
</div>

</body>
</html>