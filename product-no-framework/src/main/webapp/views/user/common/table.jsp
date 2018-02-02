<%--
  Created by IntelliJ IDEA.
  User: Bruce Jiang
  Date: 2018/2/2
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<td width="25%">
    <table width="100%" border="0" cellspacing="0" style="margin-top:30px">
        <tr>
            <td class="listtitle">我的帐户</td>
        </tr>
        <tr>
            <td class="listtd">
                <img src="/public/images/miniicon.gif" width="9" height="6"/>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <a href="${pageContext.request.contextPath }/user?method=findbyid&id=${user.id}">用户信息修改</a>
            </td>
        </tr>
        <tr>
            <td class="listtd">
                <img src="/public/images/miniicon.gif" width="9" height="6"/>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <a href="${pageContext.request.contextPath }/order?method=findOrderByUserId">订单查询</a>
            </td>
        </tr>
        <tr>
            <td class="listtd">
                <img src="/public/images/miniicon.gif" width="9" height="6"/>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <a href="${pageContext.request.contextPath }/logout">用戶退出</a>
            </td>
        </tr>
    </table>
</td>