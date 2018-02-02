<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>电子书城</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/public/css/main.css" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/public/js/my.js">
    </script>
</head>
<body class="main" onload="startSecond()">
<jsp:include page="/views/head.jsp"/>
<jsp:include page="/views/search.jsp"/>
<div id="divcontent">
    <table width="850px" border="0" cellspacing="0">
        <tr>
            <td style="padding:30px; text-align:center">
                <table width="60%"
                       border="0" cellspacing="0" style="margin-top:70px">
                    <tr>
                        <td style="width:98"><img
                                src="${pageContext.request.contextPath}/public/images/icontexto.jpg" width="128"
                                height="128"/>
                        </td>
                        <td style="padding-top:30px"><font
                                style="font-weight:bold; color:#FF0000">注册成功,别忘记激活帐户啊</font><br/>
                            <br/> <a href="../home.jsp"><span id="second">5</span>秒后自动为您转跳回首页</a>
                        </td>
                    </tr>
                </table>
                <h1>&nbsp;</h1>
            </td>
        </tr>
    </table>
</div>
<div id="divfoot">
    <table width="100%" border="0" cellspacing="0">
        <tr>
            <td rowspan="2" style="width:10%"><img src="${pageContext.request.contextPath}/public/images/bottomlogo.gif"
                                                   width="195" height="50" style="margin-left:175px"/></td>
            <td style="padding-top:5px; padding-left:50px"><a href="#"><font color="#747556"><b>CONTACT US</b> </font>
            </a></td>
        </tr>
        <tr>
            <td style="padding-left:50px"><font color="#CCCCCC"><b>COPYRIGHT 2008 &copy; eShop All Rights RESERVED.</b>
            </font></td>
        </tr>
    </table>
</div>
</body>
</html>
