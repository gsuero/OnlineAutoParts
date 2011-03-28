<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/jquery.ui.tabs.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript">
	$(function() {
		$("#fechaNacimiento").datepicker();
	});
	$(function() {
		$("#accordion").accordion();
	});
	$(function() {
		$("#tabs").tabs();
	});

	function setVehicleHeader(text, value) {
		//alert("Veh&iacute;culo #" + value + " : " + text.value);
		$("#veh"+value).get(0).firstChild.data = "Vehículo #" + value + " : " + text.value;
	}
</script>

			<h1>Registro</h1>
 			<s:actionerror id="actionError" theme="css_xhtml" />
			<s:actionmessage id="actionMessage" theme="css_xhtml" />

<table id="centerTableFull">
	<tr>
		<td align="center">
			<s:form action="doSignup" method="POST" theme="css_xhtml" validate="true" cssClass="topSpace">
				<button aria-disabled="false" role="button" class="ui-button ui-widget ui-state-default ui-priority-primary ui-corner-all ui-button-text-only" id="button"><span class="ui-button-text">Registrarme</span></button>
				&nbsp;<button onClick="alert('alerta');return false" aria-disabled="false" role="button" class="ui-button ui-widget ui-state-default ui-priority-secondary ui-corner-all ui-button-text-only" id="button"><span class="ui-button-text">Cancelar</span></button>
				<div id="tabs" align="left">
					<ul>
						<li><a href="#tabs-1">Información personal</a></li>
						<li><a href="#tabs-2">Contactos</a></li>
						<li><a href="#tabs-3">Vehiculos</a></li>
					</ul>
					<div id="tabs-1">
					<h2>Informaci&oacute;n B&aacute;sica</h2>
					<table class="normalTable" width="400" cellspacing="0" cellpadding="3">
						<tr class="iteredRow">
							<td width="175">Saludos : </td>
							<td width="50"></td>
							<td width="175"><s:select name="salutation" list="salutationList" headerKey="0" headerValue="" /></td>
						</tr>
						<tr>
							<td>Primer nombre :</td>
							<td></td>
							<td><s:textfield required="true" name="firstName" maxlength="60" /></td>
						</tr>
						<tr class="iteredRow">
							<td>Inicial segundo nombre :</td>
							<td></td>
							<td><s:textfield name="middleName" maxlength="1" /></td>
						</tr>
						<tr>
							<td>Apellidos :</td>
							<td></td>
							<td><s:textfield required="true" name="lastName" maxlength="120" /></td>
						</tr>
						<tr class="iteredRow">
							<td>Nombre de usuario :</td>
							<td></td>
							<td><s:textfield required="true" name="login" maxlength="25" /></td>
						</tr>
						<tr>
							<td>Fecha de Nacimiento :</td>
							<td></td>
							<td><s:textfield  required="true" name="birthDate" id="fechaNacimiento" maxlength="10" /></td>
						</tr>
						<tr class="iteredRow">
							<td>Contrase&ntilde;a :</td>
							<td></td>
							<td><s:password name="password" size="10" maxlength="25" required="true" /></td>
						</tr>
						<tr>
							<td>Confirmaci&oacute;n :</td>
							<td></td>
							<td><s:password name="passwordConfirmation" size="10" maxlength="25" required="true" /></td>
						</tr>
						<tr class="iteredRow">
							<td>Correo Electr&oacute;nico :</td>
							<td></td>
							<td><s:textfield name="eMail" maxlength="300" required="true" /></td>
						</tr>
						<tr>
							<td>Como se entero de nosotros :</td>
							<td></td>
							<td><s:textfield name="referal" maxlength="255" /></td>
						</tr>
						<tr class="iteredRow">
							<td>Taller habitual :</td>
							<td></td>
							<td><s:textfield name="habitualPit"  maxlength="100" /></td>
						</tr>
					</table>
					</div>
					<div id="tabs-2">
					<h2>Contactos</h2>
					<table class="normalTable" width="400" cellspacing="0" cellpadding="3">
						<tr class="iteredRow">
							<td width="175">Ciudad : </td>
							<td width="50"></td>
							<td width="175"><s:select name="ciudad" list="ciudadList" listKey="cityCode" listValue="cityName" headerKey="0" headerValue="" /></td>
						</tr>
						<tr>
							<td>Sector :</td>
							<td></td>
							<td><s:textfield name="sector" maxlength="255" /></td>
						</tr>
						<tr class="iteredRow">
							<td>Calle :</td>
							<td></td>
							<td><s:textfield name="calle" maxlength="100" /></td>
						</tr>
						<tr>
							<td>N&uacute;mero :</td>
							<td></td>
							<td><s:textfield name="numero" maxlength="100" /></td>
						</tr>
						<tr class="iteredRow">
							<td>Apartamento :</td>
							<td></td>
							<td><s:textfield name="apto" maxlength="255" /></td>
						</tr>
						<tr>
							<td>Tel&eacute;fono (809-555-5555) :</td>
							<td></td>
							<td><s:textfield name="telefono" maxlength="15" /></td>
						</tr>
						<tr class="iteredRow">
							<td>Celular (809-555-5555) :</td>
							<td></td>
							<td><s:textfield name="celular" maxlength="15" /></td>
						</tr>
					</table>
					</div>
						<div id="tabs-3">
						<h2>Vehiculos</h2>
						<p>Enlace hasta 5 vehiculos a su perfil.</p>
						<table class="normalTable" width="400" cellspacing="0" cellpadding="3">
						<tr>
						<td>
						
							<div id="accordion">
								<h3><a href="#" id="veh1">Veh&iacute;culo #1</a></h3>
								<div>
									<s:checkbox name="car[0]" theme="css_xhtml" label="Agregar" labelposition="right" /> 
									<table class="normalTable" width="100%" cellspacing="0" cellpadding="3">
										<tr class="iteredRow">
											<td width="40%">Descripci&oacute;n : </td>
											<td width="10%"></td>
											<td width="40%"><s:textfield name="descripcion[0]" maxlength="150" onblur="setVehicleHeader(this, '1')" /></td>
										</tr>
										<tr>
											<td>Chassis :</td>
											<td></td>
											<td><s:textfield name="chassis[0]" maxlength="30" /></td>
										</tr>
										<tr class="iteredRow">
											<td>Marca :</td>
											<td></td>
											<td><s:textfield name="marca[0]" maxlength="65" /></td>
										</tr>
										<tr>
											<td>Modelo :</td>
											<td></td>
											<td><s:textfield name="modelo[0]" maxlength="65" /></td>
										</tr>
										<tr class="iteredRow">
											<td>A&ntilde;o :</td>
											<td></td>
											<td><s:textfield name="anio[0]" maxlength="4" /></td>
										</tr>
									</table>
								</div>
								<h3><a href="#" id="veh2">Veh&iacute;culo #2</a></h3>
								<div>
									<s:checkbox name="car[1]"  theme="css_xhtml" label="Agregar" labelposition="right" />
									<table class="normalTable" width="100%" cellspacing="0" cellpadding="3">
										<tr class="iteredRow">
											<td width="40%">Descripci&oacute;n : </td>
											<td width="10%"></td>
											<td width="40%"><s:textfield name="descripcion[1]" maxlength="150" onblur="setVehicleHeader(this, '2')" /></td>
										</tr>
										<tr>
											<td>Chassis :</td>
											<td></td>
											<td><s:textfield name="chassis[1]" maxlength="30" /></td>
										</tr>
										<tr class="iteredRow">
											<td>Marca :</td>
											<td></td>
											<td><s:textfield name="marca[1]" maxlength="65" /></td>
										</tr>
										<tr>
											<td>Modelo :</td>
											<td></td>
											<td><s:textfield name="modelo[1]" maxlength="65" /></td>
										</tr>
										<tr class="iteredRow">
											<td>A&ntilde;o :</td>
											<td></td>
											<td><s:textfield name="anio[1]" maxlength="4" /></td>
										</tr>
									</table>
								</div>
								<h3><a href="#" id="veh3">Veh&iacute;culo #3</a></h3>
								<div>
									<s:checkbox name="car[2]"  theme="css_xhtml"  label="Agregar" labelposition="right" />	
									<table class="normalTable" width="100%" cellspacing="0" cellpadding="3">
										<tr class="iteredRow">
											<td width="40%">Descripci&oacute;n : </td>
											<td width="10%"></td>
											<td width="40%"><s:textfield name="descripcion[2]" maxlength="150" onblur="setVehicleHeader(this, '3')" /></td>
										</tr>
										<tr>
											<td>Chassis :</td>
											<td></td>
											<td><s:textfield name="chassis[2]" maxlength="30" /></td>
										</tr>
										<tr class="iteredRow">
											<td>Marca :</td>
											<td></td>
											<td><s:textfield name="marca[2]" maxlength="65" /></td>
										</tr>
										<tr>
											<td>Modelo :</td>
											<td></td>
											<td><s:textfield name="modelo[2]" maxlength="65" /></td>
										</tr>
										<tr class="iteredRow">
											<td>A&ntilde;o :</td>
											<td></td>
											<td><s:textfield name="anio[2]" maxlength="4" /></td>
										</tr>
									</table>							
								</div>
								<h3><a href="#" id="veh4">Veh&iacute;culo #4</a></h3>
								<div>
									<s:checkbox name="car[3]"  theme="css_xhtml" label="Agregar" labelposition="right" />
									<table class="normalTable" width="100%" cellspacing="0" cellpadding="3">
										<tr class="iteredRow">
											<td width="40%">Descripci&oacute;n : </td>
											<td width="10%"></td>
											<td width="40%"><s:textfield name="descripcion[3]" maxlength="150" onblur="setVehicleHeader(this, '4')" /></td>
										</tr>
										<tr>
											<td>Chassis :</td>
											<td></td>
											<td><s:textfield name="chassis[3]" maxlength="30" /></td>
										</tr>
										<tr class="iteredRow">
											<td>Marca :</td>
											<td></td>
											<td><s:textfield name="marca[3]" maxlength="65" /></td>
										</tr>
										<tr>
											<td>Modelo :</td>
											<td></td>
											<td><s:textfield name="modelo[3]" maxlength="65" /></td>
										</tr>
										<tr class="iteredRow">
											<td>A&ntilde;o :</td>
											<td></td>
											<td><s:textfield name="anio[3]" maxlength="4" /></td>
										</tr>
									</table>
								</div>
								<h3><a href="#" id="veh5">Veh&iacute;culo #5</a></h3>
								<div>
									<s:checkbox name="car[4]"  theme="css_xhtml" label="Agregar" labelposition="right" />
									<table class="normalTable" width="100%" cellspacing="0" cellpadding="3">
										<tr class="iteredRow">
											<td width="40%">Descripci&oacute;n : </td>
											<td width="10%"></td>
											<td width="40%"><s:textfield name="descripcion[4]" maxlength="150" onblur="setVehicleHeader(this, '5')" /></td>
										</tr>
										<tr>
											<td>Chassis :</td>
											<td></td>
											<td><s:textfield name="chassis[4]" maxlength="30" /></td>
										</tr>
										<tr class="iteredRow">
											<td>Marca :</td>
											<td></td>
											<td><s:textfield name="marca[4]" maxlength="65" /></td>
										</tr>
										<tr>
											<td>Modelo :</td>
											<td></td>
											<td><s:textfield name="modelo[4]" maxlength="65" /></td>
										</tr>
										<tr class="iteredRow">
											<td>A&ntilde;o :</td>
											<td></td>
											<td><s:textfield name="anio[4]" maxlength="4" /></td>
										</tr>
									</table>
								</div>
							</div>
						</td>
						</tr>
						</table>
					</div>
				</div>
				<button aria-disabled="false" role="button" class="ui-button ui-widget ui-state-default ui-priority-primary ui-corner-all ui-button-text-only" id="button"><span class="ui-button-text">Registrarme</span></button>
				&nbsp;<button onClick="alert('alerta');return false" aria-disabled="false" role="button" class="ui-button ui-widget ui-state-default ui-priority-secondary ui-corner-all ui-button-text-only" id="button"><span class="ui-button-text">Cancelar</span></button>
			</s:form>
			</td>
	</tr>
</table>