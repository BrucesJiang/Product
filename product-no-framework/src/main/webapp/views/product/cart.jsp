<%--
  Created by IntelliJ IDEA.
  User: Bruce Jiang
  Date: 2018/2/5
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>购物车管理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="/public/css/main.css" type="text/css"/>
    <script type="text/javascript">
        function changeNum(id, num, totalCount) {
            num = parseInt(num);
            totalCount = parseInt(totalCount);
            if (num < 1) {
                if (confirm("是否确认要删除此商品？")) {
                    num = 0;
                } else {
                    num = 1;
                }
            }
            if (num > totalCount) {
                alert("购买数量不能大于库存数量！");
                num = totalCount;
            }
            location.href = "${pageContext.request.contextPath}/cart?method=chn&id=" + id + "&num=" + num;
        }
    </script>
</head>
<body class="main">
<jsp:include page="/views/head.jsp"/>
<jsp:include page="/views/search.jsp"/>
<div id="divpagecontent">
    <table width="100%" border="0" cellspacing="0">
        <tr>
            <td>
                <div style="text-align:right; margin:5px 10px 5px 0px">
                    <a href="index.html">首页</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;购物车
                </div>
                <table cellspacing="0" class="infocontent">
                    <tr>
                        <td><img src="/public/ad/page_ad.jpg" width="666" height="89"/>
                            <table width="100%" border="0" cellspacing="0">
                                <tr>
                                    <td><img src="/public/images/buy.gif" width="635" height="38"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <table cellspacing="1" class="carttable">
                                            <tr>
                                                <td width="10%">序号</td>
                                                <td width="30%">商品名称</td>
                                                <td width="10%">价格</td>
                                                <td width="20%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数量</td>
                                                <td width="10%">库存</td>
                                                <td width="10%">小计</td>
                                                <td width="10%">取消</td>
                                            </tr>
                                        </table>
                                        <c:set var="sum" value="0"> </c:set>
                                        <c:forEach items="${cart }" var="entry" varStatus="vs">
                                            <table width="100%" border="0" cellspacing="0">
                                                <tr>
                                                    <td width="10%">${vs.count }</td>
                                                    <td width="30%">${entry.key.name }</td>
                                                    <td width="10%">${entry.key.price }</td>
                                                    <td width="20%">
                                                        <input type="button" value='-' style="width:20px"
                                                               onclick="changeNum('${entry.key.id}','${entry.value-1 }','${entry.key.pnum }')">
                                                        <input name="text" type="text" value="${entry.value }"
                                                               style="width:40px;text-align:center"/>
                                                        <input type="button" value='+' style="width:20px"
                                                               onclick="changeNum('${entry.key.id}','${entry.value+1 }','${entry.key.pnum }')">
                                                    </td>
                                                    <td width="10%">${entry.key.pnum }</td>
                                                    <td width="10%">${entry.value*entry.key.price }</td>
                                                    <td width="10%">
                                                        <a href="${pageContext.request.contextPath}/cart?method=chn&id=${entry.key.id}&num=0"
                                                           style="color:#FF0000; font-weight:bold">X</a></td>
                                                </tr>
                                            </table>
                                            <c:set var="sum" value="${sum+entry.value*entry.key.price }"> </c:set>
                                        </c:forEach>
                                        <table cellspacing="1" class="carttable">
                                            <tr>
                                                <td style="text-align:right; padding-right:40px;">
                                                    <font style="color:#FF6600; font-weight:bold">合计：&nbsp;&nbsp;${sum}元</font>
                                                </td>
                                            </tr>
                                        </table>
                                        <div style="text-align:right; margin-top:10px">
                                            <a href="${pageContext.request.contextPath}/pageServlet">
                                                <img src="/public/images/gwc_jx.gif" border="0"/> </a>
                                            &nbsp;&nbsp;&nbsp;&nbsp;
                                            <a href="${pageContext.request.contextPath}/views/product/order.jsp">
                                                <img src="/public/images/gwc_buy.gif" border="0"/> </a>
                                        </div>
                                    </td>
                                </tr>
                            </table>
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