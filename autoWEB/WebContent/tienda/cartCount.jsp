<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<s:if test="#session.ITEMS_COUNT_CART != null"><s:property value="#session.ITEMS_COUNT_CART" /></s:if><s:if test="#session.ITEMS_COUNT_CART == null">0</s:if>