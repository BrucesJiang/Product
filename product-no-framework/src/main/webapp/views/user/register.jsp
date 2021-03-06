<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>注册页面</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%--导入css --%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/public/css/main.css" type="text/css"/>
</head>
<body class="main">
<%@include file="/views/head.jsp" %>
<%--导入头 --%>
<%@include file="/views/search.jsp" %>
<%--导入导航条与搜索 --%>

<div id="divcontent">
    <form action="${pageContext.request.contextPath}/user?method=register"
          method="post">
        <table width="850px" border="0" cellspacing="0">
            <tr>
                <td style="padding:30px">
                    <h1>新会员注册 </h1> <span>${user_msg}</span>
                    <table width="70%" border="0" cellspacing="2" class="upline">
                        <tr>
                            <td style="text-align:right; width:20%">会员邮箱：</td>
                            <td style="width:40%">
                                <input type="text" class="textinput"
                                       name="email"/></td>
                            <td><font color="#999999">请输入有效的邮箱地址</font></td>
                        </tr>
                        <tr>
                            <td style="text-align:right">会员名：</td>
                            <td>
                                <input type="text" class="textinput" name="username"/>
                            </td>
                            <td><font color="#999999">用户名设置至少6位</font></td>
                        </tr>
                        <tr>
                            <td style="text-align:right">密码：</td>
                            <td><input type="password" class="textinput"
                                       name="password"/></td>
                            <td><font color="#999999">密码设置至少6位</font></td>
                        </tr>
                        <tr>
                            <td style="text-align:right">重复密码：</td>
                            <td><input type="password" class="textinput"
                                       name="repassword"/></td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td style="text-align:right">性别：</td>
                            <td colspan="2"><input type="radio" name="gender" value="男" checked="checked"/>
                                <span>男</span>
                                <input type="radio" name="gender" value="女"/><span>女</span>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align:right">联系电话：</td>
                            <td colspan="2"><input type="text" class="textinput" name="telephone"/></td>
                        </tr>
                        <tr>
                            <td style="text-align:right">个人介绍：</td>
                            <td colspan="2"><textarea class="textarea" name="introduce"></textarea>
                            </td>
                        </tr>
                    </table>
                    <h1>注册校验</h1>
                    <table width="80%" border="0" cellspacing="2">
                        <tr>
                            <td style="text-align:right; width:20%">输入校验码：</td>
                            <td style="width:50%"><input type="text" name="ckcode"/>
                            </td>
                            <td>${ckcode_msg }</td>
                        </tr>
                        <tr>
                            <td style="text-align:right;width:20%;">&nbsp;</td>
                            <td colspan="2" style="width:50%"><img
                                    src="/check" width="180"
                                    height="30" style="height:30px;" id="img"/>
                                <a href="javascript:void(0);" onclick="changeImage()"><span>看不清换一张</span></a>
                            </td>
                        </tr>
                    </table>
                    <table width="70%" border="0" cellspacing="0">
                        <tr>
                            <td style="padding-top:20px; text-align:center"><input
                                    type="image" src="${pageContext.request.contextPath}/public/images/signup.gif" name="submit" border="0">
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </form>
</div>
<script type="text/javascript">
    function changeImage() {  //更换验证码
        document.getElementById("img").src = "${pageContext.request.contextPath}/check?time=" + new Date().getTime();
    }
</script>
<div id="divfoot">
    <table width="100%" border="0" cellspacing="0">
        <tr>
            <td rowspan="2" style="width:10%"><img
                    src="${pageContext.request.contextPath}/public/images/bottomlogo.gif" width="195" height="50"
                    style="margin-left:175px"/></td>
            <td style="padding-top:5px; padding-left:50px"><a href="#"><font
                    color="#747556"><b>CONTACT US</b> </font> </a></td>
        </tr>
        <tr>
            <td style="padding-left:50px"><font color="#CCCCCC"><b>COPYRIGHT
                2008 &copy; eShop All Rights RESERVED.</b> </font></td>
        </tr>
    </table>
</div>
</body>
</html>
