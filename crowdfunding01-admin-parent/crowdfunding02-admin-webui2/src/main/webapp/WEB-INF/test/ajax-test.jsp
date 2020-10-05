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
<%@include file="../common/include-head.jsp"%>

<script type="text/javascript">
    $(function () {

          $("#asyncBtn") .click(function () {

            console.log("Ajax before");

            $.ajax({
               "url":"test/ajax/async.html",
                "type":"post",
                "dataType":"text",
                "async":false, //关闭异步模式，注意：“false”会当成true，因为字符串不为空
                "success":function (response) {
                    console.log(response);
                    console.log("In Ajax");
                }

            });

            console.log("Ajax late");

          });
    });
</script>

<body>

<%@include file="../common/include-nav.jsp"%>

<div class="container-fluid">
    <div class="row">

        <%@include file="../common/include-sidebar.jsp"%>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">


                <button id="asyncBtn">测试Ajax</button>

        </div>
    </div>
</div>

</body>
</html>