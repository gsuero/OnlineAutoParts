<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/jquery.ui.tabs.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/minified/jquery.ui.dialog.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/minified/jquery.ui.position.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/pages/PartesYAccesorios.js"></script>
<script>
var currentCarsArray = new Array();
var parts = new Array();
</script>
<s:form action="PiezasYAccesorios" namespace="/tienda" method="POST" theme="css_xhtml" validate="false">
<table class="normalTable" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td align="center">
		<div id="confirmaBorradoAutomovil" title="Borrar Vehiculo" style="display:none;">
			<p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>¿Est&aacute; seguro que desea borrar este veh&iacute;culo?</p>
		</div>
		<h1>Partes y Accesorios</h1>
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
				<th>Categor&iacute;as</th>
				<th></th>
				<th>Piezas y Accesorios</th>
			</tr>
			<tr>
				<td align="center"><s:select size="10" name="categoria" list="categories" cssClass="selectMultiple" listValue="category" listKey="categoryId" onchange="getParts(this.value)"  /></td>
				<td><div id="loadingPartsProgressBar" style="display:none;" align="center"><img src="<%=request.getContextPath()%>/templates/default/img/loadingWatch.gif" border="0" /></div></td>
				<td align="center"><s:select size="10" name="part" list="#{}" cssClass="selectMultiple" cssStyle="float: Both;" onchange="$('#quantity').val('1');" />
				</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td align="center" height="60">
					Cantidad:&nbsp;<input type="text" value="1" style="width:25px;margin:3px;" name="quantity" id="quantity" size="2" maxlength="2" /><br />
					<button class="fg-button ui-state-default ui-corner-all" type="button" id="agregarAlCarrito">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Agregar al carrito&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</button>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<script>
	<s:iterator value="carros">
	currentCarsArray[<s:property value="id" />] = ['<s:property value="description" />', '<s:property value="marca" />', '<s:property value="modelo" />','<s:property value="anio" />','<s:property value="chassis" />'];</s:iterator>
</script>
</s:form>