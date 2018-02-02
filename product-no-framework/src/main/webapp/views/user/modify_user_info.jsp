<%--
  Created by IntelliJ IDEA.
  User: Bruce Jiang
  Date: 2018/2/2
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>用户信息修改</title>
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
                <div style="text-align: right; margin: 5px 10px 5px 0px">
                    <a href="/views/home.jsp">首页</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;
                    <a href="/views/user/account_management.jsp">&nbsp;我的帐户</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;用户信息修改
                </div>
                <table cellspacing="0" class="infocontent">
                    <tr>
                        <td>
                            <form action="${pageContext.request.contextPath }/user?method=update" method="post">
                                <input type="hidden" name="id" value="${user.id }"/>
                                <table width="100%" border="0" cellspacing="2" class="upline">
                                    <tr>
                                        <td style="text-align: right; width: 20%">会员邮箱：</td>
                                        <td style="width: 40%; padding-left: 20px">${user.email }</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td style="text-align: right">会员名：</td>
                                        <td style="padding-left: 20px">${user.username }</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td style="text-align: right">修改密码：</td>
                                        <td><input type="password" name="password"
                                                   class="textinput"/></td>
                                        <td><font color="#999999">密码设置至少6位</font></td>
                                    </tr>
                                    <tr>
                                        <td style="text-align: right">重复密码：</td>
                                        <td><input type="password" class="textinput"/></td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td style="text-align: right">性别：</td>
                                        <td colspan="2">&nbsp;&nbsp;
                                            <input type="radio" name="gender"
                                                   value="男" ${user.gender== "男"? "checked='checked' ":""} />男
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            <input type="radio" name="gender"
                                                   value="女" ${user.gender=="女"? "checked='checked' ":"" } />女
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="text-align: right">联系方式：</td>
                                        <td colspan="2">
                                            <input name="telephone" type="text" value="${u.telephone }"
                                                   class="textinput"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="text-align: right">&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                </table>
                                <p style="text-align: center">
                                    <input type="image" src="/public/images/botton.gif" border="0">
                                </p>
                                <p style="text-align: center">&nbsp;</p>
                            </form>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</div>
<jsp:include page="/views/foot.jsp"/>
</body>
</html>
