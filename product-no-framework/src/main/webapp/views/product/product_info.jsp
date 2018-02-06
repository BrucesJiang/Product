<%--
  Created by IntelliJ IDEA.
  User: Bruce Jiang
  Date: 2018/2/4
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>商品信息</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="/public/css/main.css" type="text/css" />
</head>
<body class="main">
<jsp:include page="/views/head.jsp" />
<jsp:include page="/views/search.jsp" />
<div id="divpagecontent">
    <table width="100%" border="0" cellspacing="0">
        <tr>
            <td><div style="text-align:right; margin:5px 10px 5px 0px">
                <a href="/views/home.jsp">首页</a>
                &nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;
                <a href="#">&nbsp;计算机</a>
                &nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;java编程思想
            </div>
                <table cellspacing="0" class="infocontent">
                    <tr>
                        <td>
                            <img src="/public/images/ad/page_ad.jpg" width="645" height="84" />
                            <table width="100%%" border="0" cellspacing="0">
                                <tr>
                                    <td width="30%">
                                        <div class="divbookcover">
                                            <p>
                                                <img src="${pageContext.request.contextPath }/public/images/bookimage/${product.imgUrl}" width="213" height="269" border="0" />
                                            </p>
                                        </div>
                                        <div style="text-align:center; margin-top:25px">
                                            <a href="${pageContext.request.contextPath }/cart?method=addcart&id=${product.id}"> <img src="/public/images/buybutton.gif" border="0" />
                                            </a>
                                        </div>
                                    </td>
                                    <td style="padding:20px 5px 5px 5px">
                                        <img src="/public/images/miniicon.gif" width="16" height="13" />
                                        <font class="bookname">&nbsp;&nbsp;${product.name }</font>
                                        <hr />售价：<font color="#FF0000">￥:${product.price }元</font>
                                        <hr /> 类别：${product.category }
                                        <hr />
                                        <p>
                                            <b>内容简介：</b>
                                        </p>
                                        ${productb.description }
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table></td>
        </tr>
    </table>
</div>
<jsp:include page="/views/foot.jsp" />
</body>
</html>
