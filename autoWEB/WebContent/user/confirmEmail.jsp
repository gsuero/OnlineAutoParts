<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>	
	<s:actionmessage theme="css_xhtml" />
	<h4>Felicidades se ha completado el registro, ya puede iniciar sesi&oacute;n.</h4>
	
	<div align="center">
	<table class="normalTable" id="confirmationCode" cellpadding="0" cellspacing="3" width="600">
		<tr>
			<td nowrap="nowrap" width="200"><strong>Correo Electr&oacute;nico</strong> : </td>
			<td><s:property value="emailAddress" /></td>
		</tr>
				<tr>
			<td nowrap="nowrap"><strong>C&oacute;digo de confirmaci&oacute;n</strong> : </td>
			<td><s:property value="confirmationCode" /></td>
		</tr>
	</table>
	</div> 
