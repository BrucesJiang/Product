<%--
  Created by IntelliJ IDEA.
  User: Bruce Jiang
  Date: 2018/2/2
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>修改用户信息</title>
    <link rel="stylesheet" href="/public/css/main.css" type="text/css"/>
    <script type="text/javascript" src="/public/js/my.js"></script>
</head>
<body class="main" onload="startSecond()">
<jsp:include page="/views/head.jsp"/>
<jsp:include page="/views/search.jsp"/>
<div id="divcontent">
    <table width="850px" border="0" cellspacing="0">
        <tr>
            <td style="padding: 30px; text-align: center">
                <table
                        width="60%" border="0" cellspacing="0" style="margin-top: 70px">
                    <tr>
                        <td style="width:98px">
                            <img src="/public/images/icontexto.jpg" width="128" height="128"/>
                        </td>
                        <td style="padding-top: 30px">
                            <font style="font-weight: bold; color: #FF0000">${msg}，请重新登录</font><br/>
                            <br/> <a href="/views/home.jsp"><span id="second">5</span>秒后自动为您转跳回首页</a></td>
                    </tr>
                </table>
                <h1>&nbsp;</h1></td>
        </tr>
    </table>
</div>
<jsp:include page="/views/foot.jsp"/>
</body>
</body>
</html>
