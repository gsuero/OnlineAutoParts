<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- <link rel="stylesheet" href="<%=request.getContextPath()%>/css/multiselect.css" type="text/css"	media="screen" />-->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/jquery.ui.tabs.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/minified/jquery.ui.dialog.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/minified/jquery.ui.position.min.js"></script>
<!-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/jquery.ui.multiselect.js"></script>-->
<script>
	var disabledDays;
	var SERVICIO_SELECCIONADO = <s:if test="#request.SERVICE_OPTED == null">0</s:if><s:else><s:property value="#request.SERVICE_OPTED" /></s:else>;
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/pages/servicios.js"></script>
<script>
var currentCarsArray = new Array();
</script>
<s:form action="ServiciosMultiples" namespace="/tienda" method="POST" theme="css_xhtml" validate="false">
<table class="normalTable" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td align="center">
		<h1>Servicios <span id="subtitle">: Prueba</span></h1>
		<br />
		<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
			<th colspan="3" align="center">
				<div class="fg-buttonset fg-buttonset-single">
					<button class="fg-button ui-state-default ui-priority-primary ui-corner-left" id="ownedCar">Mis Autom&oacute;viles</button>
					<button class="fg-button ui-state-default ui-priority-primary ui-corner-right ui-state-active" id="anotherCar">Otro Autom&oacute;vil</button>
				</div>
			
			</th>

			</tr>
			<tr>
				<td>
					<s:hidden name="hMarca" id="hMarca" />
					<s:hidden name="hDescripcion" id="hDescripcion" />
					<s:hidden name="hChassis" id="hChassis" />
					<s:hidden name="hModelo" id="hModelo" />
					<s:hidden name="hAnio" id="hAnio" />
					<div id="OwnedCars">					
					<table class="normalTable" width="100%" cellspacing="0" cellpadding="3">
						<tr>
							<td width="40%">Veh&iacute;culos : </td>
							<td width="10%"></td>
							<td width="40%" align="right"><span id="OwnedVehicle"><s:select name="car" list="carros" listValue="description" listKey="id" headerKey="0" headerValue="" id="selectCar" onchange="fillVehicleData(this.value)" /></span></td>
						</tr>
						<tr class="iteredRow">
							<td width="40%">Descripci&oacute;n : </td>
							<td width="10%"></td>
							<td width="40%"><span id="OwnedDescripcion"></span></td>
						</tr>
						<tr>
							<td>Chassis :</td>
							<td></td>
							<td><span id="OwnedChassis"></span></td>
						</tr>
						<tr class="iteredRow">
							<td>Marca :</td>
							<td></td>
							<td><span id="OwnedMarca"></span></td>
						</tr>
						<tr>
							<td>Modelo :</td>
							<td></td>
							<td>
							<span id="OwnedModelo"></span>
							</td>
						</tr>
						<tr class="iteredRow">
							<td>A&ntilde;o :</td>
							<td></td>
							<td><span id="OwnedAnio"></span></td>
						</tr>
					</table>
					</div>
				</td>
				<td></td>
				<td>
					<div id="AnotherCars">
					<table class="normalTable" width="100%" cellspacing="0" cellpadding="3">
						<tr class="iteredRow">
							<td width="40%">Descripci&oacute;n : </td>
							<td width="10%"></td>
							<td width="40%"><s:textfield name="descripcion" maxlength="150" onblur="$('#hDescripcion').val(this.value);" /></td>
						</tr>
						<tr>
							<td>Chassis :</td>
							<td></td>
							<td><s:textfield name="chassis" maxlength="30" onblur="$('#hChassis').val(this.value);" /></td>
						</tr>
						<tr class="iteredRow">
							<td>Marca :</td>
							<td></td>
							<td><s:select name="marca" list="marcas" listValue="marcaName" listKey="marcaCode" headerKey="0" headerValue="" onchange="setModelosForMarca(this.value)" /></td>
						</tr>
						<tr>
							<td>Modelo :</td>
							<td></td>
							<td>
							<s:select name="modelos" list="#{'0':'Seleccionar Marca'}" disabled="true" onchange="$('#hModelo').val(this.value);" />
							</td>
						</tr>
						<tr class="iteredRow">
							<td>A&ntilde;o :</td>
							<td></td>
							<td><s:select name="anio" list="aniosList" headerKey="0" headerValue="" onchange="$('#hAnio').val(this.value);" /></td>
						</tr>
					</table>
					</div>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="4">
						<table id="servicesTable">
							<tr>
								<td colspan="4" align="center">
								<div class="fg-buttonset fg-buttonset-single">
									<button id="cambioAceiteBtn" class="fg-button ui-state-default ui-priority-primary ui-corner-left">Cambio de Aceite</button>
									<button id="tintadoBtn" class="fg-button ui-state-default ui-priority-primary">Tintado</button>
									<button id="bateriaBtn" class="fg-button ui-state-default ui-priority-primary">Cambio Bater&iacute;a</button>
									<button id="tuneUpBtn" class="fg-button ui-state-default ui-priority-primary">Tune Up</button>
									<button id="cerrajeriaBtn" class="fg-button ui-state-default ui-priority-primary">Cerrajer&iacute;a</button>
									<button id="gomasBtn" class="fg-button ui-state-default ui-priority-primary ui-corner-right">Cambio de Gomas</button>
								</div>
								
								</td>
							</tr>
							<tr>
								<td colspan="4" align="center">
								<div id="cambioAceiteDiv" style="display:none;">
									<table id="tablaAceiteService">
										<tr>
											<td width="25%"><s:select name="oilType" id="oilType" label="Tipo de Aceite" list="oilTypes" headerKey="0" headerValue=" ----- " listKey="value" listValue="label" /></td>
											<td width="25%"><s:select name="oilBrand" id="oilBrand" label="Marca preferida" list="oilBrands" headerKey="0" headerValue=" ----- " listKey="value" listValue="label" /></td>
											<td width="50%">
																						<div id="wwgrp_ServiciosMultiples_oilQuantity" class="wwgrp">
													<div id="wwlbl_ServiciosMultiples_oilQuantity" class="wwlbl">
														<label for="oilQuantity" class="label">Cantidad:</label></div> <br><div id="wwctrl_ServiciosMultiples_oilBrand" class="wwctrl">
														<input type="text" value="4" style="width:25px;margin:3px;" name="oilQuantity" id="oilQuantity" size="2" maxlength="2" /><br />1/4's de aceite.
													</div>
												</div>
											</td>
										</tr>
									</table>
									
								</div>
								<div id="tintadoDiv" style="display:none;">
									<table id="tablaTintadoService">
										<tr>
											<td width="70%">
												<s:select name="cristalesATintar" id="cristalesATintar" label="Cristales a Tintar" list="crystalTypes" listKey="value" listValue="label" multiple="true" size="11" cssClass="multiselect" cssStyle="width: 250px;" />
											</td>
											<td width="30%" valign="top">
												<br />
												<br />
												<s:select name="tintadoOscuridad" id="tintdoOscuridad" label="Oscuridad" list="tintadoOscuridades" headerKey="0" headerValue=" ----- " listKey="value" listValue="label" />
												<br /><s:select name="tipoTintado" id="tipoTintado" label="Calidad" list="tiposTintado" headerKey="0" headerValue=" ----- " listKey="value" listValue="label" />
												<br />
												<br />
												<br />
												<a href="#"><img src="<%=request.getContextPath()%>/images/ayuda.png" border=0" id="helpTintado" /> </a>
											</td>
										</tr>
									</table>
								</div>
								<div id="bateriaDiv" style="display:none;">
									<table id="tablaBateriaService" width="90%">
										<tr>
											<td width="33%">
												<s:select name="tipoBateria" id="tipoBateria" label="Tipo" list="tiposBateria" headerKey="0" headerValue=" ----- " listKey="value" listValue="label" /> 
											</td>
											<td width="33%">
												<s:select name="marcaBateria" cssStyle="width:120px;" id="marcaBateria" label="Marca preferida" list="marcasBateria" headerKey="0" headerValue=" ----- " listKey="value" listValue="label" />
												<s:textfield disabled="true" cssStyle="width:120px;" name="marcaBateria" id="marcaBateriaOtra" maxlength="100" labelposition="left" />
											</td>
											<td width="34%">
												<s:textfield name="referenciaBateria" id="referenciaBateria" maxlength="255" label="Referencias" />
											</td>
										</tr>
									</table>
								</div>
								<div id="tuneUpDiv" style="display:none;">
									Servicios de TuneUp a su veh&iacute;culo.<br />Favor especificar fecha y hora a recibir el servicio de tunning para su veh&iacute;culo.
								</div>
								<div id="cerrajeriaDiv" style="display:none;">
									div cerrajeria
								</div>
								<div id="gomasDiv" style="display:none;">
									<table id="tablaGomasService">
										<tr>
											<td width="33%"><s:select name="referenciaGomas" id="referenciaGomas" label="Referencia de Gomas" list="referenciasGomas" headerKey="0" headerValue=" ----- " listKey="value" listValue="label" /></td>
											<td width="33%"><s:select name="marcaGoma" id="marcaGoma" label="Marca preferida" list="marcasGoma" headerKey="0" headerValue=" ----- " listKey="value" listValue="label" /></td>
											<td width="34%">
												<div id="wwgrp_ServiciosMultiples_gomasQuantity" class="wwgrp">
													<div id="wwlbl_ServiciosMultiples_gomasQuantity" class="wwlbl">
														<label for="gomasQuantity" class="label">Cantidad:</label></div> <br><div id="wwctrl_ServiciosMultiples_gomasQuantity" class="wwctrl">
														<input type="text" value="4" style="width:25px;margin:3px;" name="gomasQuantity" id="gomasQuantity" size="2" maxlength="2" /><br />
													</div>
												</div>
											</td>
										</tr>
									</table>
								</div>
								<div id="notSelectedService"><h3 style="text-align: center;">Elija un servicio a recibir</h3></div>
								</td>
							</tr>
							<tr>
								<td align="left" colspan="4"><h2>Cuando y donde recibir el servicio</h2></td>
							</tr>
							<tr>
								<td align="left" width="20%">Horario a recibir el servicio :</td>
								<td align="left" width="80%" nowrap="nowrap" colspan="3"><div><s:textfield cssStyle="width:85px;" required="true" name="serviceDate" id="fechaServicio" maxlength="10" theme="simple" /> - <s:select name="serviceTime" id="serviceTime" list="servicesTimes" listKey="label" listValue="value" headerKey="0" headerValue="Seleccionar Horario" theme="simple" /></div>
								</td>
							</tr>
							<tr class="iteredRow">
								<td align="left">Ciudad : </td>
								<td align="left"><s:select name="ciudad" list="ciudadList" id="ciudad" listKey="cityCode" listValue="cityName" headerKey="0" headerValue="" /></td>
								<td align="left" colspan="2"><div id="ownAddresLink" style="display:none;"><a href="#" id="useOwnAddress">Utilizar direcci&oacute;n de registro</a></div>
								<s:hidden name="ownCiudad"  id="ownCiudad" />
								<s:hidden name="ownSector"  id="ownSector" />
								<s:hidden name="ownCalle"  id="ownCalle" />
								<s:hidden name="ownNumero"  id="ownNumero" />
								<s:hidden name="ownApto"  id="ownApto" />
								<s:hidden name="ownEdificio"  id="ownEdificio" />
								<s:hidden name="ownTelefono"  id="ownTelefono" />
								<s:hidden name="ownCelular"  id="ownCelular" />
								</td>
							</tr>
							<tr>
								<td align="left">Sector :</td>
								<td align="left" colspan="3"><s:textfield name="sector" id="sector" maxlength="255" /></td>
							</tr>
							<tr class="iteredRow">
								<td align="left">Calle :</td>
								<td align="left"><s:textfield name="calle" maxlength="100" id="calle" /></td>
								<td align="left" width="20%">N&uacute;mero :</td>
								<td align="left" width="30%"><s:textfield name="numero" id="numero" maxlength="100" /></td>
							</tr>
							<tr>
								<td align="left">Apartamento :</td>
								<td align="left"><s:textfield name="apto" maxlength="255" id="apto" /></td>
								<td align="left">Edificio:</td>
								<td align="left"><s:textfield name="edificio" maxlength="255" id="edificio" /></td>
							</tr>
							<tr class="iteredRow">
								<td align="left">Tel&eacute;fono :</td>
								<td align="left"><s:textfield name="telefono" maxlength="15" id="telefono" /></td>
								<td align="left">Celular :</td>
								<td align="left"><s:textfield name="celular" maxlength="15" id="celular" /></td>
							</tr>
							<tr>
								<td align="left" valign="top">Referencias :</td>
								<td align="left" colspan="3"><s:textarea name="referencia" id="referencia" cols="30" rows="3" /> </td>
							</tr>
						</table>
				 </td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td align="center" height="60">					
					<button class="fg-button ui-state-default ui-corner-all" type="button" id="agregarAlCarrito">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Agregar al carrito&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</button>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</s:form>
<script>
	<s:iterator value="carros">
	currentCarsArray[<s:property value="id" />] = ['<s:property value="description" />', '<s:property value="marca" />', '<s:property value="modelo" />','<s:property value="anio" />','<s:property value="chassis" />'];</s:iterator>
	disabledDays = [<s:iterator value="holidays">"<s:property value="value" />",</s:iterator>"00-00-0000"];
</script>