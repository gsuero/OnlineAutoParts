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
<script type="text/javascript">
	function saveContactInformationAction() {
		showById('contactInformationProgressBar','inline');
		hideById('saveContactos');
		hideById('cancelContactos');

		var valido = true;
		var errorMessage = "";
		var ciudad = $('#saveContactInformation_ciudad').val();
		var sector = $('#saveContactInformation_sector').val();
		var calle = $('#saveContactInformation_calle').val();
		var numero = $('#saveContactInformation_numero').val();
		var apto = $('#saveContactInformation_apto').val();
		var telefono = $('#saveContactInformation_telefono').val();
		var celular = $('#saveContactInformation_celular').val();

		if (valido) {
			var parameters = "operation=update&ciudad="+ciudad+"&sector="+sector+"&calle="+calle+"&numero="+numero+"&apto="+apto+"&telefono="+telefono+"&celular="+celular;
			$.ajax({
				   type: "POST",
				   url: "<%=request.getContextPath()%>/ajax/saveContactInformation.do",
				   data: parameters,
				   async: false,
				   cache: false,
				   success: function(msg){
				     if (msg == 'success') {
				    	 $('#contactInformationError').html("");
				    	 updateElementId('ciudadSpan', $('#saveContactInformation_ciudad :selected').text());
				    	 updateElementId('sectorSpan', sector);
				    	 updateElementId('calleSpan', calle);
				    	 updateElementId('numeroSpan', numero);
				    	 updateElementId('aptoSpan', apto);
				    	 updateElementId('telefonoSpan', telefono);
				    	 updateElementId('celularSpan', celular);
				    	 valido=true;
				     } else {
				    	 $('#contactInformationError').html('Existen errores en el formulario: <br /><br />'+msg);
				    	 valido =false;
				     }
				   }
				 });

		} else {
			$('#contactInformationError').html('Existen errores en el formulario: <br /><br />'+errorMessage);
			valido =false;
		}

		
		hideById('contactInformationProgressBar');
		showById('saveContactos','inline');
		showById('cancelContactos','inline');
		return valido;
	}
	function saveBasicInformationAction() {
		showById('basicInformationProgressBar','inline');
		hideById('ButtonSaveBasicInformation');
		hideById('ButtonCancelBasicInformation');
		var valido = true;
		var errorMessage = "";
		var salutation = $('#saveBasicInformation_salutation').val();
		var login = $('#saveBasicInformation_login').val();
		var firstname = $('#saveBasicInformation_firstName').val();
		var middlename = $('#saveBasicInformation_middleName').val();
		var lastname = $('#saveBasicInformation_lastName').val();
		var fechaNacimiento = $('#fechaNacimiento').val();
		var password = $('#saveBasicInformation_password').val();
		var confirmacion = $('#saveBasicInformation_passwordConfirmation').val();
		var habitualPit = $('#saveBasicInformation_habitualPit').val();
		// validar que login no este siendo utilizado...
		// confirmar por aqui mismo que confirmacion contrasenia sea la misma....
		if (password != confirmacion) {
			valido=false;
			errorMessage += "\n\t- La contraseña y su confirmación no coinciden.";
			$('#saveBasicInformation_password').val('');
			$('#saveBasicInformation_passwordConfirmation').val('');
			$('#saveBasicInformation_password').focus();
		}
		if (valido) {
			var parameters = "operation=update&login="+login+"&salutation="+salutation+"&firstname="+firstname+"&middlename="+middlename+"&lastname="+lastname+"&fechaNacimiento="+fechaNacimiento+"&password="+password+"&habitualPit="+habitualPit;
			$.ajax({
				   type: "POST",
				   url: "<%=request.getContextPath()%>/ajax/saveBasicInformation.do",
				   data: parameters,
				   async: false,
				   cache: false,
				   success: function(msg){
				     if (msg == 'success') {
				   		//$('#carList > tbody:last').append(newRow);
				    	 $('#basicInformationError').html("");
				    	 updateElementId('salutationSpan', salutation);
				    	 updateElementId('firstNameSpan', firstname);
				    	 updateElementId('middleNameSpan', middlename);
				    	 updateElementId('lastNameSpan', lastname);
				    	 updateElementId('fechaNacimientoSpan', fechaNacimiento);
				    	 updateElementId('habitualPitSpan', habitualPit);
				    	 valido=true;
				     } else {
				    	 $('#basicInformationError').html('Existen errores en el formulario: <br /><br />'+msg);
				    	 valido =false;
				     }
				   }
				 });

		} else {
			$('#basicInformationError').html('Existen errores en el formulario: <br /><br />'+errorMessage);
			valido =false;
		}

		hideById('basicInformationProgressBar');
		showById('ButtonSaveBasicInformation','inline');
		showById('ButtonCancelBasicInformation','inline');
		return valido;
	}

	function addVehicle(){
		showById('vehiculosProgressBar','inline');
		hideById('addVehiculos');
		hideById('cancelVehiculos');
		var actionCar = $('#actionCar').val();
		var desc = $('#saveProfileCars_descripcion_'+actionCar+'_').val();
		var marca = $('#saveProfileCars_marca_'+actionCar+'_').val();
		var modelo = $('#saveProfileCars_modelo_'+actionCar+'_').val();
		var anio = $('#saveProfileCars_anio_'+actionCar+'_').val();
		var chassis= $('#saveProfileCars_chassis_'+actionCar+'_').val();
		var parameters = "operation=add&desc="+desc+"&chas="+chassis+"&ano="+anio+"&model="+modelo+"&brand="+marca+"";
		//alert(parameters);
		$.ajax({
		   type: "POST",
		   url: "<%=request.getContextPath()%>/ajax/saveProfileCars.do",
		   data: parameters,
		   async: false,
		   cache: false,
		   success: function(msg){
		     if (msg == 'success') {
		    	$('#car'+actionCar).attr('checked', true);
		    	var newRow = "";
		    	var actionCarPlusOne = (parseInt(actionCar)+parseInt('1'));
		    	var cssClass = 'iteredRow';
		    	if (actionCarPlusOne == 2 || actionCarPlusOne == 4) {
					cssClass = 'nothing';
		    	}
				newRow += '<tr class=\"'+cssClass+'\">';
				newRow += '<td><strong>Veh&iacute;culo #' + (actionCarPlusOne) + ' </strong> : </td>';
				newRow += '<td></td>';
				newRow += '<td>'+desc+'</td>';
				newRow += '<td align="center"><a href="#" onclick="setVehicleView(' + actionCarPlusOne + ')"><img width="15" height="15" src="<%=request.getContextPath()%>/templates/default/img/edit.png" border="0" /></a></td>';
				newRow += '<td align="center"><a href="#" onclick="borrarAutomovil(\''+desc+'\', \''+chassis+'\')"><img width="15" height="15" src="<%=request.getContextPath()%>/templates/default/img/Delete.png" border="0" /></a></td>';
				newRow += "</tr>";
		   		$('#carList > tbody:last').append(newRow);
		     } else {
			     alert(msg);
		     }
		   }
		 });

		showById('cancelVehiculos','inline');
		showById('addVehiculos','inline');
		hideById('vehiculosProgressBar');
	}
	function saveVehicle() {
		showById('vehiculosProgressBar','inline');
		hideById('saveVehiculos');
		hideById('cancelVehiculos');

		var actionCar = $('#actionCar').val();
		var desc = $('#saveProfileCars_descripcion_'+actionCar+'_').val();
		var carid = $('#saveProfileCars_carid_'+actionCar+'_').val();
		var marca = $('#saveProfileCars_marca_'+actionCar+'_').val();
		var modelo = $('#saveProfileCars_modelo'+actionCar+'').val();
		var anio = $('#saveProfileCars_anio_'+actionCar+'_').val();
		var chassis= $('#saveProfileCars_chassis_'+actionCar+'_').val();
		var parameters = "operation=update&desc="+desc+"&chas="+chassis+"&ano="+anio+"&model="+modelo+"&brand="+marca+"&carid="+carid;
		//alert(parameters);
		$.ajax({
		   type: "POST",
		   url: "<%=request.getContextPath()%>/ajax/saveProfileCars.do",
		   data: parameters,
		   async: false,
		   cache: false,
		   success: function(msg){
		     if (msg == 'success') {
		    	 actualizaListaAutomoviles();
			     alert('Vehículo \''+desc + '\' actualizado satisfactoriamente.');
		     } else {
			     alert(msg);
		     }
		   }
		 });

		showById('cancelVehiculos','inline');
		showById('saveVehiculos','inline');
		hideById('vehiculosProgressBar');
	}
	
	function borrarAutomovil(desc, chassis) {
		showById('vehiculosListProgressBar','inline');
		$("#confirmaBorradoAutomovil").dialog({
			resizable: false,
			height:140,
			//autoOpen: false,
			resizable: false,
			height:200,
			modal: true,
			position: 'center',
			closeOnEscape: true,
			modal: true,
			buttons: {
				'Si': function() {
					$(this).dialog('close');
					borrarAutomovilDirect(desc, chassis);
				},
				'No borrar': function() {
					$(this).dialog('close');
				}
			}
		});
		hideById('vehiculosListProgressBar');
	}
	function borrarAutomovilDirect(desc, chassis) {
		var actionCar = $('#actionCar').val();
		var parameters = "operation=delete&desc="+desc+"&chas="+chassis+"";
		var borradoExitoso = false;
		$.ajax({
		   type: "POST",
		   url: "<%=request.getContextPath()%>/ajax/deleteProfileCars.do",
		   data: parameters,
		   async: false,
		   success: function(msg){
		     if (msg == 'success') {
		    	 borradoExitoso = true;
		    	 $('#car'+actionCar).attr('checked', false);
		 		 $('#saveProfileCars_descripcion_'+actionCar+'_').val('');
				 $('#saveProfileCars_marca_'+actionCar+'_').val('');
				 $('#saveProfileCars_modelo_'+actionCar+'_').val('');
				 $('#saveProfileCars_anio_'+actionCar+'_').val('');
				$('#saveProfileCars_chassis_'+actionCar+'_').val('');
		     } else {
			     alert(msg);
		     }
		   }
		 });

		 if (borradoExitoso) {
			actualizaListaAutomoviles();
		 }
	}
	function actualizaListaAutomoviles() {
		$.ajax({
			   type: "POST",
			   url: "<%=request.getContextPath()%>/ajax/getCarsTable.do",
			   data: "operation=refresh",
			   async: false,
			   success: function(msg){
		    	 $('#carList').replaceWith(msg);
			   }
			 });
	}
	function setVehicleView(vehicleId) {
		//return confirm(vehicleId);
		if (vehicleId > 0) {
			var i=1;
			for (i = 1; i <= 5; i++)
			{
				var name = "#editVehiculo"+i; 
				if ($(name)!= null && $(name).get(0) != null) {
					$(name).get(0).style.display='none';
				}
			}
			var activate = "#editVehiculo"+vehicleId;
			if ($(activate) != null && $(activate).get(0) != null) {
				$(activate).get(0).style.display='inline';
			}
			setSaveCarButton();
			editView('vehiculosModify'); 
			backgroundFilter();
		} else { // vamos a agregar un vehiculo nuevo...
			var checkButtons = $('carAdded');
			var hayEspacio = false;
			for(a=0; a<5;a++) {
				var id = 'car'+a;
				var isChecked = isCheckedById(id);
				//alert("Checked: #" +id + " " + isChecked);
				if (!isChecked) {
					setVehicleView(a+1);
					setActionCar(a);
					setAddCarButton();
					hayEspacio = true;
					break;
				}
			}
			if (!hayEspacio) {
				alert('Solo es posible tener 5 vehículos relacionados a su perfil.');
			}
		}
	}

	function setActionCar(carPosition) {
		//<input type="hidden" name="actionCar" id="actionCar" value="0" />
		//$('actionCar').get(0).value = carPosition;
		$('#actionCar').val(carPosition);
	}
	function setAddCarButton() {
		$('#addVehiculos').get(0).style.display='inline';
		$('#saveVehiculos').get(0).style.display='none';
	}
	function setSaveCarButton() {
		$('#addVehiculos').get(0).style.display='none';
		$('#saveVehiculos').get(0).style.display='inline';
	}

    function isCheckedById(id) 
    { 
    	return $('#'+id).attr('checked');
    }
	function setModelosForMarca(value,index) {
		$.getJSON(
				"<%=request.getContextPath()%>/ajax/getModelos.do?marca="+value,
			    function(data){
				  var options = ''; 
		          $.each(data, function(i,item){
		            options += '<option value="' + item.optionValue + '">' + item.optionDisplay + '</option>';
		          });
				  $('#saveProfileCars_modelo'+index).html(options);
				  $('#saveProfileCars_modelo'+index).attr('disabled',false);
				  $('#saveProfileCars_modelo'+index).val($('#saveProfileCars_modelo_'+index+'_').val());	          
	        });
    }
	function setModelosVehiculos() {
		for(a=0; a<5;a++) {
			var id = 'car'+a;
			if (isCheckedById(id)) {
				var value = $('#saveProfileCars_marca_'+a+'_').val();
				setModelosForMarca(value,a);
			}
		}
    }

	$(function() {
	    <s:iterator value="#{'0':'0','1':'1','2':'2','3':'4','5':'5'}" status="status">
		$('#saveProfileCars_marca_<s:property value="%{#status.index}" />_').change(function(){
			if ($(this).val() > 0) { 
				setModelosForMarca($(this).val(),<s:property value="%{#status.index}" />);
			} else {
				  $('#saveProfileCars_modelo<s:property value="%{#status.index}" />').attr('disabled',true);
			}
		})
		$('#saveProfileCars_modelo<s:property value="%{#status.index}" />').val($('#saveProfileCars_modelo_<s:property value="%{#status.index}_" />').val());

		$('#saveProfileCars_modelo_<s:property value="%{#status.index}" />_').change(function(){
			if ($(this).val() > 0) { 
				$('#saveProfileCars_modelo<s:property value="%{#status.index}" />').val($(this).val());
			}
		})

		//modelo4			
	 	</s:iterator>
	 	
		$("#fechaNacimiento").datepicker();

	});

	$(document).ready(function() {
		 setModelosVehiculos();		
	})

</script>
<table class="normalTable" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td align="center">
		<div id="confirmaBorradoAutomovil" title="Borrar Vehiculo" style="display:none;">
			<p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>¿Est&aacute; seguro que desea borrar este veh&iacute;culo?</p>
		</div>
			<table class="normalTable" width="745" cellspacing="0" cellpadding="3">
				<tr>
					<td width="344" align="left" valign="top">
					<s:form action="saveBasicInformation" namespace="/ajax" method="POST" theme="css_xhtml">
					<s:hidden name="userid" />
						<div id=informacionBasicaModify class="editMode ui-dialog ui-widget ui-widget-content ui-corner-all" style="width:344px;">
							 <div style="-moz-user-select: none;" unselectable="on" class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix">
							<span style="-moz-user-select: none;" unselectable="on" id="ui-dialog-title-confirmaBorradoAutomovil" class="ui-dialog-title">Informaci&oacute;n B&aacute;sica</span>
							<a style="-moz-user-select: none;" unselectable="on" role="button" class="ui-dialog-titlebar-close ui-corner-all" href="#"><span style="-moz-user-select: none;" unselectable="on" class="ui-icon ui-icon-closethick">close</span>
							</a>
							</div>
							<div class="ui-dialog-content ui-widget-content">
							<div id="basicInformationError" class="error"></div>
						<table class="normalTable" width="329" cellspacing="0" cellpadding="3">
							<tr class="iteredRow">
								<td width="45%">Saludos : </td>
								<td width="10%"></td>
								<td width="45%"><s:select name="salutation" list="salutationList" headerKey="0" headerValue="" /></td>
							</tr>
							<tr>
								<td>Primer nombre :</td>
								<td></td>
								<td><s:textfield required="true" name="firstName" maxlength="60" /></td>
							</tr>
							<tr class="iteredRow">
								<td>Inicial Segundo nombre :</td>
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
								<td><s:textfield required="true" name="login" maxlength="25" disabled="true" /></td>
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
								<td>Taller habitual :</td>
								<td></td>
								<td><s:textfield name="habitualPit"  maxlength="100" /></td>
							</tr>
						</table>
						</div>
						<div class="ui-dialog-buttonpane ui-widget-content ui-helper-clearfix">
						<div id="basicaButtons" align="center">
							<div id="basicInformationProgressBar" style="display:none;" align="center"><img src="<%=request.getContextPath()%>/templates/default/img/loadingWatch.gif" border="0" /></div>
							<button id="ButtonSaveBasicInformation" type="button" onclick="if (saveBasicInformationAction()) { editView('informacionBasicaModify');backgroundFilter();}" class="fg-button ui-state-default ui-priority-primary ui-corner-all">Salvar</button>
							<button id="ButtonCancelBasicInformation" type="button" onclick="editView('informacionBasicaModify');backgroundFilter();" class="fg-button ui-state-default ui-priority-secondary ui-corner-all">Cancelar</button>
							<br />
						</div>
						</div>
						</div>
						<div id="informacionBasica">
							<div id="leftFloat"><h2>Informaci&oacute;n B&aacute;sica</h2></div>
							<div id="rightFloat"><a id="editar" href="#" onclick="editView('informacionBasicaModify'); backgroundFilter()" class="fg-button ui-state-default ui-corner-all">Editar <img width="20" height="20" src="<%=request.getContextPath()%>/templates/default/img/edit2.png" border="0" /></a></div>
							<div id="clearSides"></div>
						<table class="normalTable" width="344" cellspacing="0" cellpadding="3">
							<tr class="iteredRow">
								<td width="45%"><strong>Saludos</strong> : </td>
								<td width="10%"></td>
								<td width="45%"><span id="salutationSpan"><s:property value="salutation" /></span></td>
							</tr>
							<tr>
								<td><strong>Primer nombre</strong> :</td>
								<td></td>
								<td><span id="firstNameSpan"><s:property value="firstName" /></span></td>
							</tr>
							<tr class="iteredRow">
								<td><strong>Inicial segundo nombre</strong> :</td>
								<td></td>
								<td><span id="middleNameSpan"><s:property value="middleName" /></span></td>
							</tr>
							<tr>
								<td><strong>Apellidos</strong> :</td>
								<td></td>
								<td><span id="lastNameSpan"><s:property value="lastName" /></span></td>
							</tr>
							<tr class="iteredRow">
								<td><strong>Nombre de usuario</strong> :</td>
								<td></td>
								<td><span id="loginSpan"><s:property value="login" /></span></td>
							</tr>
							<tr>
								<td><strong>Fecha de Nacimiento</strong> :</td>
								<td></td>
								<td><span id="birthDateSpan"><s:property value="birthDate" /></span></td>
							</tr>
							<tr class="iteredRow">
								<td><strong>Contrase&ntilde;a</strong> :</td>
								<td></td>
								<td><span id="contrasenaSpan">********</span></td>
							</tr>
							<tr>
								<td><strong>Correo Electr&oacute;nico</strong> :</td>
								<td></td>
								<td><span id="emailSpan"><s:property value="eMail"  /></span></td>
							</tr>
							<tr  class="iteredRow">
								<td><strong>Taller habitual</strong> :</td>
								<td></td>
								<td><span id="habitualPitSpan"><s:property value="habitualPit"/></span></td>
							</tr>
						</table>
						</div>
						</s:form>
					</td>
					<td width="20">&nbsp;</td>
					<td width="344" align="left" valign="top">
					<s:form action="saveContactInformation" namespace="/ajax" method="POST" theme="css_xhtml">
						<div id=contactosModify class="editMode ui-dialog ui-widget ui-widget-content ui-corner-all" style="width:344px;">
							 <div style="-moz-user-select: none;" unselectable="on" class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix">
							<span style="-moz-user-select: none;" unselectable="on" id="ui-dialog-title-confirmaBorradoAutomovil" class="ui-dialog-title">Contactos</span>
							<a style="-moz-user-select: none;" unselectable="on" role="button" class="ui-dialog-titlebar-close ui-corner-all" href="#"><span style="-moz-user-select: none;" unselectable="on" class="ui-icon ui-icon-closethick">close</span>
							</a>
							</div>
							<div class="ui-dialog-content ui-widget-content">
							<div id="contactInformationError" class="error"></div>
						<table class="normalTable" width="329" cellspacing="0" cellpadding="3">
							<tr class="iteredRow">
								<td width="45%">Ciudad : </td>
								<td width="10%"></td>
								<td width="45%"><s:select name="ciudad" list="ciudadList" listKey="cityCode" listValue="cityName" headerKey="0" headerValue="" /></td>
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
						<br />
						</div>
						<div class="ui-dialog-buttonpane ui-widget-content ui-helper-clearfix">
						<div id="basicaButtons" align="center">
							<div id="contactInformationProgressBar" style="display:none;" align="center"><img src="<%=request.getContextPath()%>/templates/default/img/loadingWatch.gif" border="0" /></div>
							<button id="saveContactos" type="button" onclick="if (saveContactInformationAction()) { editView('contactosModify');backgroundFilter();}" class="fg-button ui-state-default ui-priority-primary ui-corner-all">Salvar</button>
							<button id="cancelContactos" type="button" onclick="editView('contactosModify');backgroundFilter();" class="fg-button ui-state-default ui-priority-secondary ui-corner-all">Cancelar</button>
						</div>
						</div>
						</div>
						
						<div id="contactos">
							<div id="leftFloat"><h2>Contactos</h2></div>
							<div id="rightFloat"><a id="editar" href="#" onclick="editView('contactosModify'); backgroundFilter()" class="fg-button ui-state-default ui-corner-all">Editar <img width="20" height="20" src="<%=request.getContextPath()%>/templates/default/img/edit2.png" border="0" /></a></div>
							<div id="clearSides"></div>
							<table class="normalTable" width="344" cellspacing="0" cellpadding="3">
								<tr class="iteredRow">
									<td width="45%"><strong>Ciudad</strong> : </td>
									<td width="10%"></td>
									<td width="45%"><span id="ciudadLabelSpan"><s:property value="ciudadLabel" /></span></td>
								</tr>
								<tr>
									<td><strong>Sector</strong> :</td>
									<td></td>
									<td><span id="sectorSpan"><s:property value="sector" /></span></td>
								</tr>
								<tr class="iteredRow">
									<td><strong>Calle</strong> :</td>
									<td></td>
									<td><span id="calleSpan"><s:property value="calle" /></span></td>
								</tr>
								<tr>
									<td><strong>N&uacute;mero</strong> :</td>
									<td></td>
									<td><span id="numeroSpan"><s:property value="numero" /></span></td>
								</tr>
								<tr class="iteredRow">
									<td><strong>Apartamento</strong> :</td>
									<td></td>
									<td><span id="aptoSpan"><s:property value="apto" /></span></td>
								</tr>
								<tr>
									<td><strong>Tel&eacute;fono</strong> :</td>
									<td></td>
									<td><span id="telefonoSpan"><s:property value="telefono" /></span></td>
								</tr>
								<tr class="iteredRow">
									<td><strong>Celular</strong> :</td>
									<td></td>
									<td><span id="celularSpan"><s:property value="celular" /></span></td>
								</tr>
							</table>
						</div>
						</s:form>
					</td>
				</tr>
				<tr>
					<td align="left" valign="top">
						<s:form action="saveProfileCars" namespace="/ajax" method="POST" theme="css_xhtml">
						<div id="vehiculosModify" class="editMode ui-dialog ui-widget ui-widget-content ui-corner-all" style="width:344px;">
							 <div style="-moz-user-select: none;" unselectable="on" class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix">
							<span style="-moz-user-select: none;" unselectable="on" id="ui-dialog-title-confirmaBorradoAutomovil" class="ui-dialog-title">Veh&iacute;culos</span>
							<a style="-moz-user-select: none;" unselectable="on" role="button" class="ui-dialog-titlebar-close ui-corner-all" href="#"><span style="-moz-user-select: none;" unselectable="on" class="ui-icon ui-icon-closethick">close</span>
							</a>
							</div>
							<div class="ui-dialog-content ui-widget-content">
								<table class="normalTable" width="329" cellspacing="0" cellpadding="3">
								<tr>
								<td>
								<div id="editVehiculo1" style="display:none;">
									<h3><span id="veh1">Veh&iacute;culo #1</span></h3>
									<s:checkbox cssClass="checkbox" name="car[0]" id="car0" theme="css_xhtml" disabled="true" />
									<s:hidden name="carid[0]" />
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
											<td><s:select name="marca[0]" list="marcaList" listValue="marcaName" listKey="marcaCode" headerKey="0" headerValue="" /></td>
										</tr>
										<tr>
											<td>Modelo :</td>
											<td></td>
											<td>
											<s:select name="modelo0" 
											list="#{'0':'Seleccionar Marca'}" disabled="true" />
											<s:hidden name="modelo[0]" />
											</td>
										</tr>
										<tr class="iteredRow">
											<td>A&ntilde;o :</td>
											<td></td>
											<td><s:select name="anio[0]" list="anioList" headerKey="0" headerValue="" /></td>
										</tr>
									</table>
								</div>
																
								<div id="editVehiculo2" style="display:none;">
									<h3><span id="veh2">Veh&iacute;culo #2</span></h3>
									<s:checkbox cssClass="checkbox" name="car[1]" id="car1" theme="css_xhtml" disabled="true" />
									<s:hidden name="carid[1]" />
									<table class="normalTable" width="329" cellspacing="0" cellpadding="3">
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
											<td><s:select name="marca[1]" list="marcaList" listValue="marcaName" listKey="marcaCode" headerKey="0" headerValue="" /></td>
										</tr>
										<tr>
											<td>Modelo :</td>
											<td></td>
											<td>
											<s:select name="modelo1" 
											list="#{'0':'Seleccionar Marca'}" disabled="true" />
											<s:hidden name="modelo[1]" />
											</td>
										</tr>
										<tr class="iteredRow">
											<td>A&ntilde;o :</td>
											<td></td>
											<td><s:select name="anio[1]" list="anioList" headerKey="0" headerValue="" /></td>
										</tr>
									</table>
								</div>
								<div id="editVehiculo3" style="display:none;">
									<h3><span id="veh3">Veh&iacute;culo #3</span></h3>
									<s:checkbox cssClass="checkbox" name="car[2]" id="car2" theme="css_xhtml"  disabled="true" />
									<s:hidden name="carid[2]" />	
									<table class="normalTable" width="329" cellspacing="0" cellpadding="3">
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
											<td><s:select name="marca[2]" list="marcaList" listValue="marcaName" listKey="marcaCode" headerKey="0" headerValue="" /></td>
										</tr>
										<tr>
											<td>Modelo :</td>
											<td></td>
											<td>
											<s:select name="modelo2" 
											list="#{'0':'Seleccionar Marca'}" disabled="true" />
											<s:hidden name="modelo[2]" />
											</td>
										</tr>
										<tr class="iteredRow">
											<td>A&ntilde;o :</td>
											<td></td>
											<td><s:select name="anio[2]" list="anioList" headerKey="0" headerValue="" /></td>
										</tr>
									</table>							
								</div>
								<div id="editVehiculo4" style="display:none;">
									<h3><span id="veh4">Veh&iacute;culo #4</span></h3>
									<s:checkbox cssClass="checkbox" name="car[3]" id="car3" theme="css_xhtml" disabled="true" />
									<s:hidden name="carid[3]" />
									<table class="normalTable" width="329" cellspacing="0" cellpadding="3">
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
											<td><s:select name="marca[3]" list="marcaList" listValue="marcaName" listKey="marcaCode" headerKey="0" headerValue="" /></td>
										</tr>
										<tr>
											<td>Modelo :</td>
											<td></td>
											<td>
											<s:select name="modelo3" 
											list="#{'0':'Seleccionar Marca'}" disabled="true" />
											<s:hidden name="modelo[3]" />
											</td>
										</tr>
										<tr class="iteredRow">
											<td>A&ntilde;o :</td>
											<td></td>
											<td><s:select name="anio[3]" list="anioList" headerKey="0" headerValue="" /></td>
										</tr>
									</table>
								</div>
								<div id="editVehiculo5" style="display:none;">
									<h3><span id="veh5">Veh&iacute;culo #5</span></h3>
									<s:checkbox name="car[4]" id="car4" theme="css_xhtml" disabled="true" />
									<s:hidden name="carid[4]" />
									<table class="normalTable" width="329" cellspacing="0" cellpadding="3">
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
											<td><s:select name="marca[4]" list="marcaList" listValue="marcaName" listKey="marcaCode" headerKey="0" headerValue="" /></td>
										</tr>
										<tr>
											<td>Modelo :</td>
											<td></td>
											<td>
											<s:select name="modelo4" 
											list="#{'0':'Seleccionar Marca'}" disabled="true" />
											<s:hidden name="modelo[4]" />
											</td>
										</tr>
										<tr class="iteredRow">
											<td>A&ntilde;o :</td>
											<td></td>
											<td><s:select name="anio[4]" list="anioList" headerKey="0" headerValue="" /></td>
										</tr>
									</table>
								</div>
								</td>
								</tr>
								</table>
							</div><div class="ui-dialog-buttonpane ui-widget-content ui-helper-clearfix">
							<div id="vehiculosButtons" align="center">
									<input type="hidden" name="actionCar" id="actionCar" value="0" />
									<div id="vehiculosProgressBar" style="display:none;" align="center"><img src="<%=request.getContextPath()%>/templates/default/img/loadingWatch.gif" border="0" /></div>
									<button style="display:none"  id="addVehiculos" type="button" onclick="addVehicle();editView('vehiculosModify');backgroundFilter();" class="fg-button ui-state-default ui-priority-primary ui-corner-all">Agregar</button>
									<button style="display:none"  id="saveVehiculos" type="button" onclick="saveVehicle();editView('vehiculosModify');backgroundFilter();" class="fg-button ui-state-default ui-priority-primary ui-corner-all">Salvar</button>
									<button id="cancelVehiculos" type="button" onclick="editView('vehiculosModify');backgroundFilter();" class="fg-button ui-state-default ui-priority-secondary ui-corner-all">Cancelar</button>
									<br />
							</div>
							</div>
						</div>
						</s:form> <!--  formulario termina de vehiculos salvar -->
						<div id="vehiculos">
						 <!--
						 <div align="right"><a href="#" onclick="editView('vehiculosModify'); backgroundFilter()" class="fg-button ui-state-default ui-corner-all">Editar</a></div>
						  -->
						  <br />
						<div id="leftFloat"><h2>Veh&iacute;culos</h2></div>
						<div id="clearSides"></div>
							<div id="divCarsList">
								<table class="normalTable" width="344" cellspacing="0" cellpadding="3" id="carList">
								<s:iterator value="vehicles" status="status">
									<tbody>
									<tr class="<s:if test="#status.odd == true ">iteredRow</s:if><s:else>nothing</s:else>">
										<td width="90"><strong>Veh&iacute;culo #<s:property value="%{#status.index+1}" /></strong> :</td>
										<td width="10"></td>
										<td width="170"><s:property value="description" /></td>
										<td width="30" align="center"><a href="#" onclick="setActionCar(<s:property value="%{#status.index}" />);setVehicleView(<s:property value="%{#status.index+1}" />)"><img width="15" height="15" src="<%=request.getContextPath()%>/templates/default/img/edit.png" border="0" /></a></td>
										<td width="30" align="center"><a href="#" onclick="setActionCar(<s:property value="%{#status.index}" />); borrarAutomovil('<s:property value="description" />', '<s:property value="chassis" />')"><img width="15" height="15" src="<%=request.getContextPath()%>/templates/default/img/Delete.png" border="0" /></a></td>
								   </tr>
								   </tbody>
								</s:iterator>
								</table>
							</div>
							<div id="vehiculosListProgressBar" style="display:none;" align="center"><img src="<%=request.getContextPath()%>/templates/default/img/loadingWatch.gif" border="0" /></div>
						</div>					
					</td>
					<td>&nbsp;</td>
					<td align="left" valign="top">
					<h2>Configuraciones</h2>
					</td>
				</tr>
				<tr>
				   <td colspan="2" align="center">
				   	<button id="agregarVehiculo" type="button" onclick="setVehicleView(0)" class="fg-button ui-state-default ui-priority-primary ui-corner-all">Agregar Veh&iacute;culo</button>
				   </td>
				</tr>
			</table>
			</td>
	</tr>
</table>