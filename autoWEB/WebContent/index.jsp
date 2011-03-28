<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    	               "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="javax.sql.*"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="com.autoservice.commons.Constants"%>
<%@page import="com.autoservice.ejbs.user.UserBeanLocal"%>
<%@page import="com.autoservice.ejbs.user.UserBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.autoservice.commons.security.PasswordBusiness"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GlassFish JSP Page</title>
</head>
<body>
<h1>Hello World!</h1>
<%
	try {
		UserBeanLocal userBean = new UserBean();
		out.println(userBean.sayHello("Garis Suero") + "<br />");
		//out.print(userBean.updateUser("cuanticazo","Mr","Garis",'M',"Suero Alvarez","maldito4ever","garis.suero@gmail.com") + "<br />");
		//error de casteo siempre que me llega la data de User...
		// a veces castea, otras veces no... hay que revisar la persistencia de los datos...
	} catch (Exception ex) {
		out.print("Exception " + ex.getMessage());
		out.println(ex.toString());
	}
	//userBean.createUser(2,"cuanticazo","Mr.","Garis",'M',"Suero","1234567","cuantico_@hotmail.com");
%>
</body>
</html>
