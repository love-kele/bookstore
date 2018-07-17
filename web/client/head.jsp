<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/client/js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/client/js/head.js"></script>
<div id="divhead">
    <table cellspacing="0" class="headtable">
        <tr>
            <td>
                <a href="${pageContext.request.contextPath }/index.jsp">
                    <img src="${pageContext.request.contextPath}/client/images/logo.png" width="200" height="60"
                         border="0"/>
                </a>
            </td>
            <td style="text-align:right">
                <img src="${pageContext.request.contextPath}/client/images/cart.gif" width="26" height="23"
                     style="margin-bottom:-4px"/>&nbsp;<a
                    href="${pageContext.request.contextPath}/showmycart?id=${user.id}" id="cart">购物车</a>
                | <a href="#">帮助中心</a>
                | <a href="${pageContext.request.contextPath}/client/login.jsp" id="myaccount" >我的帐户</a>
                | <a href="${pageContext.request.contextPath}/client/register.jsp">新用户注册</a>
                <%--<br><br><br>欢迎您：--%>

             <input type="text" hidden="hidden" value="${user.username}" id="logined"/>


            </td>
        </tr>

    </table>
</div>

