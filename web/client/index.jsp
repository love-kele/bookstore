<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>bookStore首页</title>
    <%-- 导入css --%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/main.css" type="text/css"/>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath }/client/js/head.js"></script>--%>
    <!-- 导入首页轮播图css和js脚本 -->
    <link type="text/css" href="${pageContext.request.contextPath }/client/css/autoplay.css" rel="stylesheet"/>
    <script type="text/javascript" src="${pageContext.request.contextPath }/client/js/autoplay.js"></script>

</head>

<body class="main">
<%@include file="head.jsp" %>
<%@include file="menu_search.jsp" %>

<!-- 图书商场首页轮播图  开始 -->
<div id="box_autoplay">
    <div class="list">
        <ul>
            <li><img src="${pageContext.request.contextPath }/client/ad/index_ad1.jpg" width="900" height="335"/></li>
            <li><img src="${pageContext.request.contextPath }/client/ad/index_ad3.jpg" width="900" height="335"/></li>
            <li><img src="${pageContext.request.contextPath }/client/ad/index_ad4.jpg" width="900" height="335"/></li>
            <li><img src="${pageContext.request.contextPath }/client/ad/index_ad5.jpg" width="900" height="335"/></li>
        </ul>
    </div>
</div>
<!-- 图书商场首页轮播图  结束 -->

<div id="divcontent">
    <table width="900px" border="0" cellspacing="0">
        <tr>
            <td width="497">
                <img src="${pageContext.request.contextPath}/client/images/billboard.gif" width="497" height="38"/>
                <table cellspacing="0" class="ctl">
                    <tr>
                        <td width="485" height="29">
                            尊敬的网上书城用户,<br>\r\n　　为了让大家有更好的购物体验，3月25日起，凡一次购买100元以上的顾客送10元代金券！<br>
                            具体活动时间请留意公告，感谢大家的支持与理解，祝大家购物愉快！<br>\r\n3月23日<br>\r\n网上书城系统管理部<br>
                        </td>
                    </tr>
                </table>
            </td>
            <td style="padding:5px 15px 10px 40px">
                <table width="100%" border="0" cellspacing="0">
                    <tr>
                        <td>
                            <img src="${pageContext.request.contextPath}/client/images/hottitle.gif" width="126"
                                 height="29"/>
                        </td>
                    </tr>
                </table>
                <table width="100%" border="0" cellspacing="0">
                    <tr>

                        <td style="width:80; text-align:center">
                            <a href="${pageContext.request.contextPath }/findProductById?id=${books[1].id}">
                                <img src="${pageContext.request.contextPath }${books[0].imgurl}" width="102"
                                     height="130" border="0"/>
                            </a>
                            <br/>
                            <a href="${pageContext.request.contextPath }/findProductById?id=${books[1].id}" id="book1">${books[0].name}</a>
                            <%-- <br />作者:${pArray[2] } --%>
                        </td>

                        <td style="width:80; text-align:center">
                            <a href="${pageContext.request.contextPath }/findProductById?id=${books[1].id}">
                                <img src="${pageContext.request.contextPath }${books[1].imgurl}" width="102"
                                     height="130" border="0"/>
                            </a>
                            <br/>
                            <a href="${pageContext.request.contextPath }/findProductById?id=${books[1].id}">${books[1].name}</a>
                            <%-- <br />作者:${pArray[2] } --%>
                        </td>

                    </tr>
                </table>
            </td>
        </tr>
    </table>
</div>
<%@ include file="foot.jsp" %>
</body>


<script>

    $(function () {
        var book1= $("#book1").text();

        if (book1==undefined||book1==""||book1==null) {
            $.ajax({
                url: "${pageContext.request.contextPath}/shownewbook",

                type: "GET",

                success: function () {
                    location.href = "${pageContext.request.contextPath}/client/index.jsp";
                }

            })
        }
    })
</script>
</html>
