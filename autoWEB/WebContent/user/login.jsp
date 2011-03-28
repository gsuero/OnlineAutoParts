<%@ taglib prefix="s" uri="/struts-tags" %>
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>
</head>
<body>
	<b><s:actionerror /></b>
	<s:actionmessage />
	<s:form action="doLogin" method="POST">
		<s:textfield key="login" label="Usuario:" />
		<s:password key="password" label="Contraseña" />
		<s:checkbox label="Recordarme" key="remember" />
		<s:submit label="Iniciar Sesion"></s:submit>
	</s:form>
</body>
</html>