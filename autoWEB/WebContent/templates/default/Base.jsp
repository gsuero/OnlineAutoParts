<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link  rel="stylesheet" type="text/css" href="http://localhost:8084/autoWEB/templates/default/css/styles.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>
<body>
	<div align="center" id="bigContainer">
	<div id="container" align="center">
		<table id="layoutTable">
			<tr>
				<td>
					<div id="headerdiv">
						<tiles:insertAttribute name="header" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div id="menudiv">
						<tiles:insertAttribute  name="menu" />
					</div>
				</td>
						
			</tr>
			<tr>
				<td>
					<div id="bodydiv">
						<tiles:insertAttribute  name="body" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div id="footerdiv">
						<tiles:insertAttribute  name="footer" />
					</div>
				</td>
			</tr>	
		</table>
	</div>
	</div>
</body>
</html>