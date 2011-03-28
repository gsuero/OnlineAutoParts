<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.autoservice.commons.Constants"%>
<s:set name="user" value="USER" scope="session"/>
<table id="tableHeader">
	<tr>
		<td width="250"><h2><a href="<%=request.getContextPath()%>/Home.do">AutoServiciosEnLinea.com</a></h2></td>
		<td width="100"></td>
		<td width="400" valign="top">
		<div id="headerLinks">
		<s:if test="#session.USER != null">
			<a href="<%=request.getContextPath()%>/user/profile.do"><s:property value="#session.USER.getFirstName()" />&nbsp;<s:property value="#session.USER.getLastName()" /></a>
			(<a href="/autoWEB/user/logout.do" >Salir</a>)
		</s:if>
		</div>
		</td>
	</tr>
</table>