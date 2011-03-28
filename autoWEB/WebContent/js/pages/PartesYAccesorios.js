$(document).ready(function() {
	setCurrentMenu("accesorios");
	/*$('#OwnedCars :input').attr('checked', false);
	$('#OwnedCars :input').attr('disabled', true);
	$('#AnotherCar :input').attr('checked', false);
	$('#AnotherCar :input').attr('disabled', true);
*/

	$("select#PiezasYAccesorios__car").val('0');
 
	$("#quantity").keydown(onlyNumbers);


	$('#agregarAlCarrito').click(function() {
		//less do some validation...
		var desc = $('#hDescripcion').val();
		var marca = $('#hMarca').val();
		var chassis = $('#hChassis').val();
		var modelo = $('#hModelo').val();
		var anio = $('#hAnio').val();
		var cat = $('#PiezasYAccesorios_categoria').val();
		var part = $('#PiezasYAccesorios_part').val();
		var quantity = $('#quantity').val();

		var errorMessage = "";
		if (desc == null || desc.length < 5)
			errorMessage += "\n\t - Debe completar una descripción para este automóvil";

		if (marca == null || marca.length < 1 || marca < 1)
			errorMessage += "\n\t - Debe seleccionar marca y modelo para este automóvil";
		else if (modelo == null || modelo.length < 1 || modelo < 1)
			errorMessage += "\n\t - Debe seleccionar marca y modelo para este automóvil";

		/*if (chassis == null || chassis.length < 1)
			errorMessage += "\n\t - Debe suministrar un numero de chassis";
*/
		if (anio == null || anio.length < 1 || anio < 1900)
			errorMessage += "\n\t - Debe suministrar el año del automóvil";

		if (cat == null || cat.length < 1 || cat == 0)
			errorMessage += "\n\t - Debe seleccionar categoría y pieza a agregar al carrito.";
		else if (part == null || part.length < 1 || part ==0)
			errorMessage += "\n\t - Debe seleccionar categoría y pieza a agregar al carrito.";

		if (errorMessage.length == 0) {
			if (addPartToCart(desc, marca, chassis, modelo, anio, cat, part, quantity)) {
				refreshCarContentCount();
			} else {
				alert("Un error ha ocurrido agregando itemes al carrito de compra. Si el problema persiste, favor comunicar vía contactos.");
			}
		} else {
			alert("Existen errores en el formulario:"+errorMessage);
		}
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
});

function getParts(catid) {
		showById('loadingPartsProgressBar','inline');
		$.getJSON(CONTEXT_PATH+"/ajax/getPartsByCategory.do?categoryid="+catid,
			    function(data){
				  var options = ''; 
		          $.each(data, function(i,item){
			          if (item.disponible == 1)
		            		options += '<option value="' + item.piezaId + '">' + item.descripcion + '</option>';
		          });
				  $('#PiezasYAccesorios_part').html(options);
				  $('#PiezasYAccesorios_part').attr('disabled',false);
				  $('#quantity').val('1');
	        });
		hideById('loadingPartsProgressBar');	
}
function setModelosForMarca(value) {
	$.getJSON(CONTEXT_PATH+"/ajax/getModelos.do?marca="+value,
		    function(data){
			  var options = ''; 
	          $.each(data, function(i,item){
	            options += '<option value="' + item.optionValue + '">' + item.optionDisplay + '</option>';
	          });
			  $('#PiezasYAccesorios_modelos').html(options);
			  $('#PiezasYAccesorios_modelos').attr('disabled',false);
			  $('#hMarca').val(value);
			  //$('#PiezasYAccesorios_modelos'+index).val($('#saveProfileCars_modelo_'+index+'_').val());	          
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
	  
	$('#hDescripcion').val($('#PiezasYAccesorios_descripcion').val());
	$('#hMarca').val($('#PiezasYAccesorios_marca').val());
	$('#hChassis').val($('#PiezasYAccesorios_chassis').val());
	$('#hModelo').val($('#PiezasYAccesorios_modelo').val());
	$('#hAnio').val($('#PiezasYAccesorios_anio').val());
}

function fillVehicleData(vehicleid) {
	//$('#PiezasYAccesorios_ownedSelectedCar').val(vehicleid);
	updateElementId('OwnedDescripcion',currentCarsArray[vehicleid][0]);
	updateElementId('OwnedAnio',currentCarsArray[vehicleid][3]);
	updateElementId('OwnedModelo',getModeloDescripcion(currentCarsArray[vehicleid][1],currentCarsArray[vehicleid][2]));
	updateElementId('OwnedMarca',$("#PiezasYAccesorios_marca option[value='"+currentCarsArray[vehicleid][1]+"']").text());
	updateElementId('OwnedChassis',currentCarsArray[vehicleid][4]);

	$('#hDescripcion').val(currentCarsArray[vehicleid][0]);
	$('#hMarca').val(currentCarsArray[vehicleid][1]);
	$('#hChassis').val(currentCarsArray[vehicleid][4]);
	$('#hModelo').val(currentCarsArray[vehicleid][2]);
	$('#hAnio').val(currentCarsArray[vehicleid][3]);
	
}