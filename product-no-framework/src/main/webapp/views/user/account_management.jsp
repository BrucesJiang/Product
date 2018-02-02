<%--
  Created by IntelliJ IDEA.
  User: Bruce Jiang
  Date: 2018/2/2
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>账户管理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="/public/css/main.css" type="text/css"/>
</head>
<body class="main">
<jsp:include page="/views/head.jsp"/>
<jsp:include page="/views/search.jsp"/>
<div id="divpagecontent">
    <table width="100%" border="0" cellspacing="0">
        <tr>
            <jsp:include page="/views/user/common/table.jsp"/>
            <td>
                <div style="text-align:right; margin:5px 10px 5px 0px">
                    <a href="/views/index.jsp">首页</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;
                    <a href="/views/user/account_management.jsp">&nbsp;我的帐户</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;欢迎
                </div>
                <table cellspacing="0" class="infocontent">
                    <tr>
                        <td style="padding:20px">
                            <img src="/public/images/bg.jpg" width="631" height="436"/>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</div>
<jsp:include page="/views/foot.jsp" />
</body>
</html>
