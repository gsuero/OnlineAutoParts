<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.autoservice.commons.Constants"%>
<s:set name="user" value="USER" scope="session"/>
<table id="tableHeader" border="0">
	<tr>
	<td width="30%" valign="top" align="left">
		<h2><a href="<%=request.getContextPath()%>/Home.do"><img src="<%=request.getContextPath()%>/images/headerlogo.jpg" id="headerLogo" /></a></h2>
	</td>
	<td width="70%" valign="top" height="5px">
		<div id="headerLinks">
		<a>Live Chat</a> |
		<s:if test="#session.USER == null">
			<a href="<%=request.getContextPath()%>/user/signup.do">Registrate</a> | <a href="/autoWEB/user/login.do">Iniciar Sesi&oacute;n</a>
		</s:if>
		<s:if test="#session.USER != null">
			<a href="<%=request.getContextPath()%>/user/profile.do"><s:property value="#session.USER.getFirstName()" />&nbsp;<s:property value="#session.USER.getLastName()" /></a>
			(<a href="/autoWEB/user/logout.do" >Salir</a>)
		</s:if>
		&nbsp;|&nbsp;<a href="/autoWEB/tienda/Carrito.do">Carrito</a> (<a href="/autoWEB/tienda/Carrito.do"><span id="headerCartContent"><s:if test="#session.ITEMS_COUNT_CART != null"><s:property value="#session.ITEMS_COUNT_CART" /></s:if><s:if test="#session.ITEMS_COUNT_CART == null">0</s:if></span></a>)
		</div>
	</td>
	</tr>
</table>