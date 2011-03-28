<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/jquery.ui.tabs.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/minified/jquery.ui.dialog.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/minified/jquery.ui.position.min.js"></script>
<script>
var currentCarsArray = new Array();
function actualizarItem(descripcion, marca, chassis, modelo, anio, part, cat, object) {
	var quantity = $('#quantity-old-'+part).val();
	var newQuantity = $('#quantity-'+part).val();
	if (newQuantity < 1) {
		borrarItem(descripcion, marca, chassis, modelo, anio, part, object);
	}
	
	if (quantity != newQuantity) {
		if (updatePartInCart(descripcion, marca, chassis, modelo, anio, cat, part, newQuantity)) {
			return true;
		} else {
			return false;
		}
	}
}
// itemType: service=1;accesorio=2
function borrarService(descripcion, marca, chassis, modelo, anio, date, serviceType, fecha, horario, data, object) {
	var id = "confirmaBorradoItem";
	$('#'+id+'Msg').html("¿Esta seguro que desea borrar este item?");
	$("#"+id).dialog({
		resizable: false,
		height:140,
		resizable: false,
		height:200,
		modal: true,
		position: 'center',
		closeOnEscape: true,
		modal: true,
		buttons: {
			'Si': function() {
				$(this).dialog('close');
					if (removeServiceFromCart(descripcion, marca, chassis, modelo, anio, date, serviceType, fecha, horario, data)) {
						deleteTr(object);
						refreshCarContentCount();
						return true;
					} else {
						return false;
					}
			},
			'No borrar': function() {
				$(this).dialog('close');
				return false;
			}
		}
	});	
}

function borrarItem(descripcion, marca, chassis, modelo, anio, part, object) {
	var id = "confirmaBorradoItem";
	$('#'+id+'Msg').html("¿Esta seguro que desea borrar este item?");
	$("#"+id).dialog({
		resizable: false,
		height:140,
		resizable: false,
		height:200,
		modal: true,
		position: 'center',
		closeOnEscape: true,
		modal: true,
		buttons: {
			'Si': function() {
				$(this).dialog('close');
					if (removePartFromCart(descripcion, marca, chassis, modelo, anio, part)) {
						deleteTr(object);
						refreshCarContentCount();
						return true;
					} else {
						return false;
					}
			},
			'No borrar': function() {
				$(this).dialog('close');
				return false;
			}
		}
	});	
}
function borrarItemes(valor) {
	if (valor) {
		var selected = false;
		var borrar = true;
		$('form#Carrito input:checkbox').each( function() {
			if (this.checked) {
				var value = this.value;
				if (value.length > 4) {
					selected = true;
					// brandCode +'|'+modelCode+'|'+anio+'|'+desc||part;
					var values = value.split('||');
					var data = values[1];
					values = values[0].split('|');
					var brandCode = values[0];
					var modelCode = values[1];
					var anio = values[2];
					var desc = values[3];
					
					//alert("parte: " + part + " -> " + values);
					var partOrService = data.split('::');
					//alert(partOrService);
					if (partOrService[0] == 'part') {
						removePartFromCart(desc, brandCode, "", modelCode, anio, partOrService[1]);
						deleteTr(this);
					} else if (partOrService[0] == 'service') {
						var fecha = partOrService[2];
						var horario = partOrService[3];
						var chassis = "";//values[6];
						var date = fecha + " " + horario;
						
						var data = "";
						if (partOrService[1] == 3) {
							var tipoBateria = partOrService[4];
							var marcaBateria = partOrService[5];
							var referenciaBateria = partOrService[6];
							data = tipoBateria+ "::" +marcaBateria+ "::" +referenciaBateria;
						} else if (partOrService[1] == 5) {
							data = "";
						} else if (partOrService[1] == 6) {
							var referenciaGoma = partOrService[4];
							var marcaGoma = partOrService[5];
							var cantidadGoma = partOrService[6];
							data = referenciaGoma + "::" + marcaGoma+ "::" +cantidadGoma;
						} else if (partOrService[1] == 1) {
							var tipoAceite = partOrService[4];
							var marcaAceite = partOrService[5];
							var cantidadAceite = partOrService[6];
							data = tipoAceite + "::" +marcaAceite+ "::" +cantidadAceite; 
						} else if (partOrService[1] == 2) {
							var tipoTintado = partOrService[5];
							var oscuridadTintado = partOrService[4];
							var cristalesATintar = partOrService[6];
							cristales = cristalesATintar.split("--");
			            	if (cristales instanceof Array) {
			            		cristales = cristales.join("|");
			            	}
							data = oscuridadTintado + "::" + tipoTintado + "::" + cristales;
						}
						if (removeServiceFromCart(desc, brandCode, chassis, modelCode, anio, date, partOrService[1], fecha, horario, data)) {
							deleteTr(this);
							refreshCarContentCount();
						}
					}
					
				}
			}
		});	
		if (!selected) {
			alert("Debe seleccionar un item para aplicar esta accion");
		} else {
			refreshCarContentCount();
		}
	}
}
$(document).ready(function() {
	//setCurrentMenu("accesorios");

 	$(".tooltips").hover(
		function() { $(this).contents("span:last-child").css({ display: "block" }); },
		function() { $(this).contents("span:last-child").css({ display: "none" }); }
	);

	$(".tooltips").mousemove(function(e) {
		var mousex = e.pageX + 10;
		var mousey = e.pageY + 5;
		$(this).contents("span:last-child").css({  top: mousey, left: mousex });
	});

	
	$('#borrar').click(function(event) {
		var selected = false;
		$('form#Carrito input:checkbox').each( function() {
			if (this.checked) {
				var value = this.value;
				if (value.length > 4) {
					selected = true;
				}
			}
		});	
		if (!selected) {
			alert("Debe seleccionar un item para aplicar esta accion");
		} else {
			ask('confirmaBorradoItem', '¿Esta seguro que desea borrar los itemes seleccionados?', borrarItemes);
		}

	});
	$("#quantity").keydown(function(event) {
		if ( event.keyCode == 46 || event.keyCode == 8 ) {
			// let it happen, don't do anything
		}
		else {
			if (event.keyCode < 95) { 
				if (event.keyCode < 48 || event.keyCode > 57 ) {
					event.preventDefault();	
				}
			} else {
				if (event.keyCode < 96 || event.keyCode > 105 ) {
					event.preventDefault();
				}
			} 	
		}
	});
	
	$(".fg-button:not(.ui-state-disabled)")
	.hover(
		function(){ 
			$(this).addClass("ui-state-hover"); 
		},
		function(){ 
			$(this).removeClass("ui-state-hover"); 
		}
	)
	.mousedown(function(){
			$(this).parents('.fg-buttonset-single:first').find(".fg-button.ui-state-active").removeClass("ui-state-active");
			if( $(this).is('.ui-state-active.fg-button-toggleable, .fg-buttonset-multi .ui-state-active') ){ $(this).removeClass("ui-state-active"); }
			else { $(this).addClass("ui-state-active"); }	
	})
	.mouseup(function(){
		if(! $(this).is('.fg-button-toggleable, .fg-buttonset-single .fg-button,  .fg-buttonset-multi .fg-button') ){
			$(this).removeClass("ui-state-active");
		}
	});

	
	if (LOGGED_USER == false) {
		//enableAnotherCars();
	} else {
		//enableOwnedCars();
	}
});
function checkAllItem(me, brandCode, modelCode, anio, desc) {
	var tmp = brandCode +'|'+modelCode+'|'+anio+'|'+desc;
	var values;
	$('form#Carrito input:checkbox').each( function() {
		var valor = this.value;
		values = valor.split("||",1);
		if (tmp == values[0]) {
			this.checked = me.checked;
		}
	});
}
</script>
<s:form action="Carrito" namespace="/tienda" method="POST" theme="css_xhtml" validate="false">
<table class="normalTable" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td align="center">
		<div id="confirmaBorradoItem" title="Confirmación" style="display:none;">
			<p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span><span id="confirmaBorradoItemMsg"></span></p>
		</div>
		<h1>Su carrito de compras</h1>
		<table id="shoppingCartTable">
			<s:if test="%{#request.vehicleList != null}">
			<s:iterator value="vehicleList" status="status" var="car">
				<tr>
					<td colspan="4"><h2><s:property value="#car.description" /></h2></td>
				</tr>
				<tr>
					<th width="5%"><input type="checkbox" value="1" name="hitem" id="hitem" onclick="checkAllItem(this, '<s:property value="#car.brandcode" />','<s:property value="#car.modelcode" />','<s:property value="#car.anio" />','<s:property value="#car.description" />')" /></th>
					<th width="55%">Descripci&oacute;n</th>
					<th width="12%">Cantidad</th>
					<th width="28%">Acci&oacute;n</th>
				</tr>				
				<s:iterator value="items" status="itemStatus" var="item">
				<tr class="item">
					<td><span id=""><input type="checkbox" value="<s:property value="#car.brandcode" />|<s:property value="#car.modelcode" />|<s:property value="#car.anio" />|<s:property value="#car.description" />||part::<s:property value="#item.id" />" name="item" id="item" /></span></td>
					<td><span><a href="<s:property value="#item.URL" />"><s:property value="#item.description" /></a></span></td>
					<td align="center"><span id="quantity"><input type="hidden" value="<s:property value="#item.quantity" />" id="quantity-old-<s:property value="#item.id" />" /><input type="text" value="<s:property value="#item.quantity" />" id="quantity-<s:property value="#item.id" />" maxlength="2" size="3" onkeydown="onlyNumbers(event)" /></span></td>
					<td align="right"><a href="#"onclick="actualizarItem('<s:property value="#car.description" />', '<s:property value="#car.brandcode" />', '<s:property value="#car.chassis" />', '<s:property value="#car.modelcode" />', '<s:property value="#car.anio" />', '<s:property value="#item.id" />', '<s:property value="#item.catid" />', this, 2);"><strong>Actualizar</strong></a><br /><a href="#" onclick="borrarItem('<s:property value="#car.description" />', '<s:property value="#car.brandcode" />', '<s:property value="#car.chassis" />', '<s:property value="#car.modelcode" />', '<s:property value="#car.anio" />', '<s:property value="#item.id" />', this);">Borrar</a></td>
				</tr>
				</s:iterator>
				<s:iterator value="services" status="serviceStatus" var="service">
				<tr class="item">
					<s:if test="%{#service.type == 1}">
					<td><span id=""><input type="checkbox" value="<s:property value="#car.brandcode" />|<s:property value="#car.modelcode" />|<s:property value="#car.anio" />|<s:property value="#car.description" />||service::<s:property value="#service.type" />::<s:property value="#service.fecha" />::<s:property value="#service.horario" />::<s:property value="#service.objectService.tipoAceite" />::<s:property value="#service.objectService.marcaAceite" />::<s:property value="#service.objectService.cantidadAceite" />" name="item" id="item" /></span></td>
					<td>
					<div id="header_wrapper" class="tooltips">
						<a href="#">Cambio de Aceite</a>
						<span class="tooltip-style1">
						<strong>Tipo Aceite</strong>: <s:property value="#service.objectService.tipoAceite" />
						<br /><strong>Marca Aceite</strong>: <s:property value="#service.objectService.marcaAceite" />
						<br /><strong>Cantidad de 1/4's</strong>: <s:property value="#service.objectService.cantidadAceite" />
						</span>
					</div>
					</td>
					<td align="center"><span id="quantity">-</span></td>
					<td align="right"><a href="#" onclick="borrarService('<s:property value="#car.description" />', '<s:property value="#car.brandcode" />', '<s:property value="#car.chassis" />', '<s:property value="#car.modelcode" />', '<s:property value="#car.anio" />','<s:property value="#service.startServiceTime" />',<s:property value="#service.type" />,'<s:property value="#service.fecha" />','<s:property value="#service.horario" />', '<s:property value="#service.objectService.tipoAceite" />::<s:property value="#service.objectService.marcaAceite" />::<s:property value="#service.objectService.cantidadAceite" />', this);">Borrar</a></td>
					</s:if>
					<s:elseif test="%{#service.type== 2}">
					<td><span id=""><input type="checkbox" value="<s:property value="#car.brandcode" />|<s:property value="#car.modelcode" />|<s:property value="#car.anio" />|<s:property value="#car.description" />||service::<s:property value="#service.type" />::<s:property value="#service.fecha" />::<s:property value="#service.horario" />::<s:property value="#service.objectService.quality" />::<s:property value="#service.objectService.oscurity" />::<s:iterator value="#service.objectService.glasses" var="glass2" status="glass2Status"><s:if test="%{#glass2Status.index > 0}">--</s:if><s:property value="#glass2" /></s:iterator>" name="item" id="item" /></span></td>
					<td>
						<div id="header_wrapper" class="tooltips">
							<a href="#">Tintado Cristal</a>
							<span class="tooltip-style1">
								<strong>Calidad</strong>: <s:property value="#service.objectService.quality" />
								<br /><strong>Oscuridad</strong>: <s:property value="#service.objectService.oscurity" />
								<br /><strong>Cristales</strong>: 
								<s:iterator value="#service.objectService.glasses" var="glass" status="glassStatus">
									<s:if test="%{#glassStatus.index > 0}"><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</s:if><s:property value="#glass" />
								</s:iterator>
							</span>
						</div>
						</td>
						<td align="center"><span id="quantity">-</span></td>
						<td align="right"><a href="#" onclick="borrarService('<s:property value="#car.description" />', '<s:property value="#car.brandcode" />', '<s:property value="#car.chassis" />', '<s:property value="#car.modelcode" />', '<s:property value="#car.anio" />','<s:property value="#service.startServiceTime" />',<s:property value="#service.type" />,'<s:property value="#service.fecha" />','<s:property value="#service.horario" />', '<s:property value="#service.objectService.quality" />::<s:property value="#service.objectService.oscurity" />::<s:iterator value="#service.objectService.glasses" var="glass2" status="glass2Status"><s:if test="%{#glass2Status.index > 0}">|</s:if><s:property value="#glass2" /></s:iterator>', this, 1);">Borrar</a></td>
					</s:elseif>
					<s:elseif test="%{#service.type== 3}">
					<td><span id=""><input type="checkbox" value="<s:property value="#car.brandcode" />|<s:property value="#car.modelcode" />|<s:property value="#car.anio" />|<s:property value="#car.description" />||service::<s:property value="#service.type" />::<s:property value="#service.fecha" />::<s:property value="#service.horario" />::<s:property value="#service.objectService.tipoBateria" />::<s:property value="#service.objectService.batteryBrand" />::<s:property value="#service.objectService.referencia" />" name="item" id="item" /></span></td>
					<td>
					<div id="header_wrapper" class="tooltips">
						<a href="#">Cambio de Bater&iacute;a</a>
						<span class="tooltip-style1">
						<strong>Marca Bater&iacute;a</strong>: <s:property value="#service.objectService.batteryBrand" />
						<br /><strong>Tipo Bater&iacute;a</strong>: <s:property value="#service.objectService.tipoBateria" />
						<br /><strong>Referencia</strong>: <s:property value="#service.objectService.referencia" />
						</span>
					</div>
					</td>
					<td align="center"><span id="quantity">-</span></td>
					<td align="right"><a href="#" onclick="borrarService('<s:property value="#car.description" />', '<s:property value="#car.brandcode" />', '<s:property value="#car.chassis" />', '<s:property value="#car.modelcode" />', '<s:property value="#car.anio" />','<s:property value="#service.startServiceTime" />',<s:property value="#service.type" />,'<s:property value="#service.fecha" />','<s:property value="#service.horario" />', '<s:property value="#service.objectService.tipoBateria" />::<s:property value="#service.objectService.batteryBrand" />::<s:property value="#service.objectService.referencia" />', this);">Borrar</a></td>
					
					</s:elseif>
					<s:elseif test="%{#service.type== 5}">
					<td><span id=""><input type="checkbox" value="<s:property value="#car.brandcode" />|<s:property value="#car.modelcode" />|<s:property value="#car.anio" />|<s:property value="#car.description" />||service::<s:property value="#service.type" />::<s:property value="#service.fecha" />::<s:property value="#service.horario" />" name="item" id="item" /></span></td>
					<td>
					Tune Up
					</td>
					<td align="center"><span id="quantity">-</span></td>
					<td align="right"><a href="#" onclick="borrarService('<s:property value="#car.description" />', '<s:property value="#car.brandcode" />', '<s:property value="#car.chassis" />', '<s:property value="#car.modelcode" />', '<s:property value="#car.anio" />','<s:property value="#service.startServiceTime" />',<s:property value="#service.type" />,'<s:property value="#service.fecha" />','<s:property value="#service.horario" />', '', this);">Borrar</a></td>
						
					</s:elseif>
					<s:elseif test="%{#service.type== 6}">
					<td><span id=""><input type="checkbox" value="<s:property value="#car.brandcode" />|<s:property value="#car.modelcode" />|<s:property value="#car.anio" />|<s:property value="#car.description" />||service::<s:property value="#service.type" />::<s:property value="#service.fecha" />::<s:property value="#service.horario" />::<s:property value="#service.objectService.referencia" />::<s:property value="#service.objectService.marcaPredilecta" />::<s:property value="#service.objectService.quantity" />" name="item" id="item" /></span></td>
					<td>
						<div id="header_wrapper" class="tooltips">
							<a href="#">Cambio de Gomas</a>
							<span class="tooltip-style1">
							<strong>Referencia</strong>: <s:property value="#service.objectService.referencia" />
							<br /><strong>Marca Predilecta</strong>: <s:property value="#service.objectService.marcaPredilecta" />
							<br /><strong>Cantidad</strong>: <s:property value="#service.objectService.quantity" />
							</span>
						</div>
					</td>
					<td align="center"><span id="quantity">-</span></td>
					<td align="right"><a href="#" onclick="borrarService('<s:property value="#car.description" />', '<s:property value="#car.brandcode" />', '<s:property value="#car.chassis" />', '<s:property value="#car.modelcode" />', '<s:property value="#car.anio" />','<s:property value="#service.startServiceTime" />',<s:property value="#service.type" />,'<s:property value="#service.fecha" />','<s:property value="#service.horario" />', '<s:property value="#service.objectService.referencia" />::<s:property value="#service.objectService.marcaPredilecta" />::<s:property value="#service.objectService.quantity" />', this);">Borrar</a></td>
					</s:elseif>
					<s:else>
					Servicio inv&aacute;lido
					</s:else> 
				</tr>
				</s:iterator>
			</s:iterator>

			</s:if>
			<s:else>
				<tr>
					<td><div class="centered">No tiene piezas y/o accesorios en el carrito de compras.</div></td>
				</tr>
			</s:else>
		</table>
			<s:if test="%{#request.vehicleList != null}">
			<table id="shoppingCartTableOptions">
				<tr>
					<td width="10%" valign="top">
						<button class="fg-button ui-state-default ui-corner-all" type="button" id="borrar" >Borrar</button>
					</td>
					<td width="3%"></td>
					<td width="87%" align="right" valign="top">
						<br />
						<br />
						<table id="totalsTable">
						<tr>
							<th> Total de <span id="CartContentCount"><s:if test="#session.ITEMS_COUNT_CART != null"><s:property value="#session.ITEMS_COUNT_CART" /></s:if><s:if test="#session.ITEMS_COUNT_CART == null">0</s:if></span> itemes en su carrito</th>
						</tr>
						<tr>
							<td>Notas: Nuestro promedio de respuesta a las solicitudes de cotizaciones es de 1 hora h&aacute;bil.<br /> El tiempo de respuesta de las cotizaciones jam&aacute;s exceder&aacute; las 12 horas.</td>
						</tr>
						<tr>
							<td id="footer"><button class="fg-button ui-state-default ui-corner-all" type="button" id="solicitarCotizacion">Solicitar Cotizaci&oacute;n</button></td>
						</tr>
						</table>
					
					</td>
				</tr>
			</table>
			</s:if>
		</td>
	</tr>
</table>
</s:form>