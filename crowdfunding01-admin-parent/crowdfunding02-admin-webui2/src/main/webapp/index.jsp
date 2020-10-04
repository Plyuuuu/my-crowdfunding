<%--
  Created by IntelliJ IDEA.
  User: LvPeng
  Date: 2020/9/29
  Time: 17:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>

    <%--http://localhost:8080/webui2/test/ssm.html
        ${pageContext.request.serverName}:localhost
        ${pageContext.request.serverPort}:8080
        ${pageContext.request.contextPath}:/webui2   (Application context)
    --%>
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">

    <%--引入JQuery--%>
    <script type="text/javascript" src="resource/jquery/jquery-2.1.1.min.js"></script>

    <%--引入Layer 基于JQuery 要放在其后面--%>
    <script type="text/javascript" src="resource/layer/layer.js"></script>

    <%--JS代码 --%>
    <script type="text/javascript">
        $(function () {
            $("#btn1").click(function () {
                /*
                * 响应状态码200才执行：$.get();
                                    $.post();
                * */

                $.ajax({
                    "url":"send/array.html",        //请求目标资源的地址
                    "type":"post",                  //请求方式
                    "data":{
                        "array":[5,8,12]
                    },                //要发送的请求参数
                    "dataType":"text",              //如何对待服务器返回的结果
                    "success":function (response) { //服务器端成功处理请求的回调函数
                        alert(response);
                    },
                    "error":function (response) {   //服务端请求处理失败调用的回调函数
                        alert(response);
                    }
                });
            });

            $("#btn2").click(function () {
                /*
                * 响应状态码200才执行：$.get();
                                    $.post();
                * */

                $.ajax({
                    "url":"send/array/two.html",    //请求目标资源的地址
                    "type":"post",                  //请求方式
                    "data":{                        //要发送的请求参数
                        "array[0]":5,
                        "array[1]":8,
                        "array[2]":12
                    },
                    "dataType":"text",              //如何对待服务器返回的结果
                    "success":function (response) { //服务器端成功处理请求的回调函数
                        alert(response);
                    },
                    "error":function (response) {   //服务端请求处理失败调用的回调函数
                        alert(response);
                    }
                });
            });

            $("#btn3").click(function () {
                /*
                * 响应状态码200才执行：$.get();
                                    $.post();
                * */
                //准备好要发送给服务器的数据
                var array = [5,8,12];//JSON数组
                //var obj = {"name":"tom","age":12};//Json对象

                // 把json数组转成Json字符串
                //"['5','8','12']"
                var requestBody = JSON.stringify(array);

                console.log("array:"+array.length+" requestBody:"+requestBody.length);

                $.ajax({
                    "url":"send/array/three.html",    //请求目标资源的地址
                    "type":"post",                  //请求方式
                    "data":requestBody,
                    "contentType":"application/json;charset=UTF-8",//告诉服务器传的数据是Json数组
                    "dataType":"text",              //如何对待服务器返回的结果
                    "success":function (response) { //服务器端成功处理请求的回调函数
                        alert(response);
                    },
                    "error":function (response) {   //服务端请求处理失败调用的回调函数
                        alert(response);
                    }
                });
            });

            $("#btn4").click(function () {
                //准备发送的数据Json对象
                var student = {
                    "stuId":5,
                    "stuName":"迪丽热巴",
                    "address":{
                        "province":"江西",
                        "city":"南昌",
                        "street":"双港"
                    },
                    "subjects":[{
                        "subjectName":"Java",
                        "subjectScore":99
                    },{
                        "subjectName":"数据结构",
                        "subjectScore":98
                    }],
                    "map":{
                        "k1":"v1",
                        "k2":"v2"
                    }
                }

                //吧json对象转成json字符串
                var requestBody = JSON.stringify(student);

                //发ajax请求
                $.ajax({
                    "url":"send/compose/object.json",
                    "type":"post",
                    "data":requestBody,
                    "contentType":"application/json;charset=UTF-8",
                    "dataType":"json",
                    "success":function (response) {
                        //alert(response);
                        console.log(response);
                    },
                    "error":function (response) {
                        //alert(response);
                        console.log(response);
                    }
                });

            });

            $("#btn5").click(function () {
                /*浏览器自带的*/
                /*alert("aaa...s");*/

                layer.msg("Layer 的弹框 ");

            });

        });
    </script>

</head>
<body>
    <%--参考base   注意:test 前面还不可以加/ --%>
    <a href="test/ssm.html">测试ssm整合</a>
    <br>
    <br>

    <button id="btn1">Send [5,8,12] One</button>

    <br>
    <br>

    <button id="btn2">Send [5,8,12] Tow</button>
    <br>
    <br>

    <button id="btn3">Send [5,8,12] Three</button>

    <br>
    <br>

    <button id="btn4">Send Compose Object</button>

    <br>
    <br>

    <button id="btn5">点我弹框</button>

    <br>
    <br>
    <a href="/webui2/admin/to/login/page.html">点击登录</a>
</body>
</html>
