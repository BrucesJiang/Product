<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="divhead">
    <table cellspacing="0" class="headtable">
        <tr>
            <td>
                <a href="/views/home.jsp">
                    <img src="/public/images/logo.png" width="95" height="30" border="0"/>
                </a> ||
                <c:if test="${user == null}">
                <a href="/views/user/register.jsp">新用户注册</a> |
                <a href="/views/user/login.jsp">请登录</a>
                </c:if>
                <c:if test="${user != null}">
                    <span>欢迎， ${user.username}</span>
                </c:if>
            </td>
            <td style="text-align:right">
                <img src="/public/images/cart.gif" width="26" height="23" style="margin-bottom:-4px"/>&nbsp;
                <a href="cart.jsp">购物车</a> |
                <a href="#">帮助中心</a> |
                <c:if test="${user != null}">
                    <a href="${pageContext.request.contextPath }/myAccount" >我的帐户</a> |
                    <a href="${pageContext.request.contextPath }/user?method=logout">登出</a>
                </c:if>
            </td>
        </tr>
    </table>
</div>