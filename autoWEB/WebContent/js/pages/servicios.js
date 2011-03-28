function hideAllServices() {
	hideById('notSelectedService');
	hideById('cambioAceiteDiv');
	hideById('tintadoDiv');
	hideById('bateriaDiv');
	hideById('tuneUpDiv');
	hideById('cerrajeriaDiv');
	hideById('gomasDiv');
}

var serviceType;
/* utility functions */
function nationalDays(date) {
	var m = date.getMonth(), d = date.getDate(), y = date.getFullYear();
	for (i = 0; i < disabledDays.length; i++) {
		if($.inArray((m+1) + '-' + d + '-' + y,disabledDays) != -1 || new Date() > date) {
			return [false];
		}
	}
	return [true];
}
function noWeekendsOrHolidays(date) {
	var noWeekend = jQuery.datepicker.noWeekends(date);
	return noWeekend[0] ? nationalDays(date) : noWeekend;
}

$(document).ready(function() {
	//$(".multiselect").multiselect({sortable: false, searchable: false});
	$('#helpTintado').click(function() {
		var url = CONTEXT_PATH+"/servicios/TintadoHelp.jsp";
		var wind = opennormalpopup(url, "Ayuda", 900, 555);
		//879px × 539px
	});
	
	$(function() {
		$("#fechaServicio").datepicker({
			minDate: new Date(),
			//dateFormat: 'DD, MM, d, yy',
			constrainInput: true,
			beforeShowDay: noWeekendsOrHolidays
		});
	});

	$('#marcaBateria').change(function() {
		  if ($(this).val() == 'otra') {
			  $('#marcaBateriaOtra').removeAttr("disabled");
		  } else {
			  $('#marcaBateriaOtra').val('');
			  $('#marcaBateriaOtra').attr("disabled", true);
		  }
		});
	
	if (LOGGED_USER == true) {
		showById('ownAddresLink','inline');
	}
	
	setCurrentMenu("servicios");

	$("select#Servicios_car").val('0');
 
	$("#quantity").keydown(onlyNumbers);
	$("#gomasQuantity").keydown(onlyNumbers);

	$('#useOwnAddress').click(function() {
		$('#ciudad').val($('#ownCiudad').val());
		$('#sector').val($('#ownSector').val());
		$('#calle').val($('#ownCalle').val());
		$('#numero').val($('#ownNumero').val());
		$('#apto').val($('#ownApto').val());
		$('#edificio').val($('#ownEdificio').val());
		$('#telefono').val($('#ownTelefono').val());
		$('#celular').val($('#ownCelular').val());
	});
	$('#agregarAlCarrito').click(function() {
		var desc = $('#hDescripcion').val();
		var marca = $('#hMarca').val();
		var chassis = $('#hChassis').val();
		var modelo = $('#hModelo').val();
		var anio = $('#hAnio').val();
		
		var fecha = $('#fechaServicio').val(); // 08/10/2010
		var horario = $('#serviceTime').val();
		var ciudad = $('#ciudad').val();
		var sector = $('#sector').val();
		var calle = $('#calle').val();
		var numero = $('#numero').val();
		var apartamento = $('#apto').val();
		var edificio = $('#edificio').val();
		var telefono = $('#telefono').val();
		var celular = $('#celular').val();
		var referencias = $('#referencia').val();
		
		var errorMessage = "";
		var urlPart;		
		var serviceSelected = true;
		if (serviceType == 1) { // cambio de aceite...
			var tipoAceite = $('#oilType').val();
			var marcaAceite = $('#oilBrand').val();
			var cantidadAceite = $('#oilQuantity').val();
			urlPart = "&oilType="+tipoAceite+"&marcaAceite="+marcaAceite+"&cantidadAceite="+cantidadAceite;
		} else if (serviceType == 2) {
			var cristalesATintar = $('#cristalesATintar').val();
			var tintdoOscuridad = $('#tintdoOscuridad').val();
			var tipoTintado = $('#tipoTintado').val();
			 var cristales;
			if (cristalesATintar == null || cristalesATintar.length < 1 || cristalesATintar == '0') {
				errorMessage += "\n\t - Debe seleccionar los cristales que desea tintar";
			} else {
				if (cristalesATintar instanceof Array) {
					cristales = cristalesATintar.join("|");
				} else {
					cristales = cristalesATintar.split(",");
	            	if (cristales instanceof Array) {
	            		cristales = cristalesATintar.join("|");
	            	}
				}
			}
			if (tintdoOscuridad == null || tintdoOscuridad.length < 1 || tintdoOscuridad == '0')
				errorMessage += "\n\t - Debe seleccionar la oscuridad del tintado seleccionado";
			if (tipoTintado == null || tipoTintado.length < 1 || tipoTintado == '0')
				errorMessage += "\n\t - Debe seleccionar la calidad del tintado seleccionado";
			urlPart = "&tipoTintado="+tipoTintado+"&oscuridad="+tintdoOscuridad+"&vidriosATintar="+cristales;
			//alert(urlPart);
		
		} else if (serviceType == 3) {
			var tipoBateria = $('#tipoBateria').val();
			var marcaBateria = $('#marcaBateria').val();
			var referenciaBateria = $('#referenciaBateria').val();
			var marcaBateriaOtra = $('#marcaBateriaOtra').val();

			if (tipoBateria == null || tipoBateria.length < 1 || tipoBateria == '0') {
				errorMessage += "\n\t - Debe seleccionar el tipo de batería que desea";
			} 
			
			if (marcaBateria == 'otra' && (marcaBateriaOtra == null || marcaBateriaOtra.length < 1)) {
				errorMessage += "\n\t - Debe especificar la marca de su preferencia";
			} else  if (marcaBateria == 'otra') {
				marcaBateria = marcaBateriaOtra;
			}
			urlPart = "&marcaBateria="+marcaBateria+"&tipoBateria="+tipoBateria+"&referenciaBateria="+referenciaBateria;
		} else if (serviceType == 5) {
			// do nothing, we dont need anything at this service.... yet :P
			urlPart="";
		} else if (serviceType == 6) {
			var referenciaGomas = $('#referenciaGomas').val();
			var marcaGoma = $('#marcaGoma').val();
			var gomasQuantity = $('#gomasQuantity').val();

			if (referenciaGomas == null || referenciaGomas.length < 1 || referenciaGomas == '0') {
				errorMessage += "\n\t - Debe especificar las referencias para las gomas que desea";
			} 
			if (marcaGoma == null || marcaGoma.length < 1 || marcaGoma == '0')  
				errorMessage += "\n\t - Debe especificar la marca de su preferencia";
			
			if (gomasQuantity == null || gomasQuantity.length < 1 || gomasQuantity <= 0) 
				errorMessage += "\n\t - Debe suministrar una cantidad válida de gomas";
			
			urlPart = "&referenciaGomas="+referenciaGomas+"&marcaPredilecta="+marcaGoma+"&quantity="+gomasQuantity;
		} else {
			errorMessage += "\n\t - Debe seleccionar un servicio.";
			serviceSelected = false;
		}
		
		if (serviceSelected) {
			if (desc == null || desc.length < 5)
				errorMessage += "\n\t - Debe completar una descripción para este automóvil";
	
			if (marca == null || marca.length < 1 || marca < 1)
				errorMessage += "\n\t - Debe seleccionar marca y modelo para este automóvil";
			else if (modelo == null || modelo.length < 1 || modelo < 1)
				errorMessage += "\n\t - Debe seleccionar marca y modelo para este automóvil";
	
			if (anio == null || anio.length < 1 || anio < 1900)
				errorMessage += "\n\t - Debe suministrar el año del automóvil";
	
			// lets do contact validation...
			if (fecha == null || fecha.length != 10)
				errorMessage += "\n\t - Fecha de servicio es incorrecta.";
	
			if (horario == null || horario.length < 0 || horario == '0')
				errorMessage += "\n\t - Debe seleccionar un Horario.";
			
			if (ciudad == null || ciudad.length < 1 || ciudad == '0')
				errorMessage += "\n\t - Debe seleccionar una ciudad.";
			
			if (sector == null || sector.length < 1)
				errorMessage += "\n\t - Debe suministrar el sector en los campos de contacto.";
			
			if (calle == null || calle.length < 1)
				errorMessage += "\n\t - Debe suministrar la calle en los campos de contacto.";
			
			if (numero == null || numero.length < 1)
				errorMessage += "\n\t - Debe suministrar el número en los campos de contacto.";
			
			if ((telefono == null || telefono.length < 6) || (celular == null || celular.length < 6))
				errorMessage += "\n\t - Debe suministrar un número de contacto.";
		}
		if (errorMessage.length == 0) {
			if (addServiceToCart(serviceType, urlPart, fecha, horario, ciudad, sector, calle, numero, apartamento, edificio, telefono, celular, referencias, desc, marca, chassis, modelo, anio)) {
				refreshCarContentCount();
			} else {
				alert("Un error ha ocurrido agregando itemes al carrito de compra. Si el problema persiste, favor comunicar vía contactos.");
			}
		} else {
			alert("Existen errores en el formulario:"+errorMessage);
		}
	});
	$('#subtitle').html('');
	$('#cambioAceiteBtn').click(function() {
		hideAllServices();
		showById('cambioAceiteDiv','inline');
		$('#subtitle').html(": Cambio de Aceite");
		serviceType = 1;
		return false;
	});
	$('#tintadoBtn').click(function() {
		hideAllServices();
		showById('tintadoDiv','inline');
		$('#subtitle').html(": Tintado de Cristales");
		serviceType = 2;
		return false;
	});
	$('#bateriaBtn').click(function() {
		hideAllServices();
		showById('bateriaDiv','inline');
		$('#subtitle').html(": Cambio de Batería");
		serviceType = 3;
		return false;
	});
	$('#tuneUpBtn').click(function() {
		hideAllServices();
		showById('tuneUpDiv','inline');
		$('#subtitle').html(": Tune Up");
		serviceType = 5;
		return false;
	});
	$('#cerrajeriaBtn').click(function() {
		hideAllServices();
		showById('cerrajeriaDiv','inline');
		$('#subtitle').html(": Cerrajería");
		serviceType = 4;
		return false;
	});
	$('#gomasBtn').click(function() {
		hideAllServices();
		showById('gomasDiv','inline');
		$('#subtitle').html(": Cambio de Gomas");
		serviceType = 6;
		return false;
	});
	
	$('#ownedCar').click(function() {
		if (LOGGED_USER) {
			enableOwnedCars();
		} else {
			alert("Para aprovechar esta funcionalidad es necesario estar registrado en nuestro sistema.");
			$('#anotherCar').attr('checked', true);
			enableAnotherCars();
		}
		return false;
	});

	$('#anotherCar').click(function() {
		enableAnotherCars();
		return false;
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
		enableAnotherCars();
	} else {
		enableOwnedCars();
	}
	
	if (SERVICIO_SELECCIONADO == 1){
 	  $('#cambioAceiteBtn').click();
 	  $('#cambioAceiteBtn').addClass("ui-state-active");
	} else if (SERVICIO_SELECCIONADO == 2){
		$('#tintadoBtn').click();
		$('#tintadoBtn').addClass("ui-state-active");
	} else if (SERVICIO_SELECCIONADO == 3){
		$('#bateriaBtn').click();
		$('#bateriaBtn').addClass("ui-state-active");
	} else if (SERVICIO_SELECCIONADO == 5){
		$('#tuneUpBtn').click();
		$('#tuneUpBtn').addClass("ui-state-active");
	} else if (SERVICIO_SELECCIONADO == 4){
		$('#cerrajeriaBtn').click();
		$('#cerrajeriaBtn').addClass("ui-state-active");
	} else if (SERVICIO_SELECCIONADO == 6){
		$('#gomasBtn').click();
		$('#gomasBtn').addClass("ui-state-active");
	}
});

function setModelosForMarca(value) {
	$.getJSON(CONTEXT_PATH+"/ajax/getModelos.do?marca="+value,
		    function(data){
			  var options = ''; 
	          $.each(data, function(i,item){
	            options += '<option value="' + item.optionValue + '">' + item.optionDisplay + '</option>';
	          });
			  $('#ServiciosMultiples_modelos').html(options);
			  $('#ServiciosMultiples_modelos').attr('disabled',false);
			  $('#hMarca').val(value);
			  //$('#ServiciosMultiples_modelos'+index).val($('#saveProfileCars_modelo_'+index+'_').val());	          
        });
}

function enableOwnedCars()
{
	$('#AnotherCars :input').attr('checked', false);
	$('#AnotherCars :input').attr('disabled', true);
	$('#OwnedCars :input').removeAttr('disabled');
	$('#AnotherCars').fadeTo("slow", 0.3);
	$('#OwnedCars').fadeTo("slow", 1.0);
	$('#anotherCar').removeClass('ui-state-active');
	$('#ownedCar').addClass('ui-state-active');

	var selectedCar = $('#selectCar').val();
	if (selectedCar != null && selectedCar > 0) {
		$('#hDescripcion').val(currentCarsArray[selectedCar][0]);
		$('#hMarca').val(currentCarsArray[selectedCar][1]);
		$('#hChassis').val(currentCarsArray[selectedCar][4]);
		$('#hModelo').val(currentCarsArray[selectedCar][2]);
		$('#hAnio').val(currentCarsArray[selectedCar][3]);
	} else {
		$('#hDescripcion').val('');
		$('#hMarca').val('');
		$('#hChassis').val('');
		$('#hModelo').val('');
		$('#hAnio').val('');
	}
	
}
function enableAnotherCars()
{
	$('#OwnedCars :input').attr('checked', false);
	$('#OwnedCars :input').attr('disabled', true);
	$('#AnotherCars :input').removeAttr('disabled');
	$('#OwnedCars').fadeTo("slow", 0.3);
	$('#AnotherCars').fadeTo("slow", 1.0);
	$('#ownedCar').removeClass('ui-state-active');
	$('#anotherCar').addClass('ui-state-active');
	  
	$('#hDescripcion').val($('#ServiciosMultiples_descripcion').val());
	$('#hMarca').val($('#ServiciosMultiples_marca').val());
	$('#hChassis').val($('#ServiciosMultiples_chassis').val());
	$('#hModelo').val($('#ServiciosMultiples_modelo').val());
	$('#hAnio').val($('#ServiciosMultiples_anio').val());
}

function fillVehicleData(vehicleid) {
	//$('#ServiciosMultiples_ownedSelectedCar').val(vehicleid);
	updateElementId('OwnedDescripcion',currentCarsArray[vehicleid][0]);
	updateElementId('OwnedAnio',currentCarsArray[vehicleid][3]);
	updateElementId('OwnedModelo',getModeloDescripcion(currentCarsArray[vehicleid][1],currentCarsArray[vehicleid][2]));
	updateElementId('OwnedMarca',$("#ServiciosMultiples_marca option[value='"+currentCarsArray[vehicleid][1]+"']").text());
	updateElementId('OwnedChassis',currentCarsArray[vehicleid][4]);

	$('#hDescripcion').val(currentCarsArray[vehicleid][0]);
	$('#hMarca').val(currentCarsArray[vehicleid][1]);
	$('#hChassis').val(currentCarsArray[vehicleid][4]);
	$('#hModelo').val(currentCarsArray[vehicleid][2]);
	$('#hAnio').val(currentCarsArray[vehicleid][3]);
	
}