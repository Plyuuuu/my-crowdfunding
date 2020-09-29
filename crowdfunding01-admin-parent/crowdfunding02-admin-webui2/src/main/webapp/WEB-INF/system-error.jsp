<%--
  Created by IntelliJ IDEA.
  User: LvPeng
  Date: 2020/9/29
  Time: 22:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
    <h1>出错啦！</h1>
    <%--从请求域取出Exception对象，在进一步访问message属性能显示错误消息--%>
    ${requestScope.exception.message}
</body>
</html>
