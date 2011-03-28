var X = 0;
var Y = 0;

function opennormalpopup(url, name, width, height)
{
		var win = window.open(url,name,'width='+width+',height='+height+',status=yes,location=no,menubar=no;toolbar=no,scrollbars=yes');
		return win;
}

function backgroundFilter() {
	var div;
	if (document.getElementById) {
		// Standard way to get element
		div = document.getElementById('backgroundFilter');
	} else if (document.all) {
		// Get the element in old IE's
		div = document.all['backgroundFilter'];
	}
	// if the style.display value is blank we try to check it out here
	if (div.style.display == '' && div.offsetWidth != undefined && div.offsetHeight != undefined) {
		div.style.display = (div.offsetWidth != 0 && div.offsetHeight != 0) ? 'block' : 'none';
	}
	// If the background is hidden ('none') then it will display it ('block').
	// If the background is displayed ('block') then it will hide it ('none').
	div.style.display = (div.style.display == '' || div.style.display == 'block') ? 'none' : 'block';
}

function editView(id)
{
	var div;
	if (document.getElementById) {
		// Standard way to get element
		div = document.getElementById(id);
	} else if (document.all) {
		// Get the element in old IE's
		div = document.all[id];
	}
	// if the style.display value is blank we try to check it out here
	if (div.style.display == '' && div.offsetWidth != undefined && div.offsetHeight != undefined) {
		div.style.display = (div.offsetWidth != 0 && elem.offsetHeight != 0) ? 'block' : 'none';
	}
	// If the PopUp is hidden ('none') then it will display it ('block').
	// If the PopUp is displayed ('block') then it will hide it ('none').
	div.style.display = (div.style.display == '' || div.style.display == 'block') ? 'none' : 'block';
	// Off-sets the X position by 15px
	/*X = X + 15;
	div.style.left = X + 'px';
	div.style.top = Y + 'px';
	*/
}

function setVehicleHeader(text, value) {
	//alert("Veh&iacute;culo #" + value + " : " + text.value);
	$("#veh"+value).get(0).firstChild.data = "Vehículo #" + value + " : " + text.value;
}

function hideById(id) {
	//$('#'+id).fadeTo("slow", 0.0);
	$('#'+id).slideUp("slow");
	//$('#'+id).get(0).style.display='none';
}
function showById(id, display) {
	//$('#'+id).get(0).style.display=display;
	//$('#'+id).fadeTo("slow", 1.0);
	$('#'+id).slideDown("slow");
}

function updateElementId(id, value) {
	$('#'+id).html(value);
}
function refreshCarContentCount() {
	var count = 0;
	$.ajax({
		   type: "POST",
		   url: CONTEXT_PATH+"/ajax/getCartCount.do",
		   async: false,
		   cache: false,
		   success: function(msg){
	    	 count=msg;
		   }
		 });
	updateElementId('headerCartContent', count);
	if($('#CartContentCount') != null)
		updateElementId('CartContentCount', count);
}


function carContentIncrease() {
	var count = $('#headerCartContent').html();
	updateElementId('headerCartContent', ++count);
}
function carContentDecrease() {
	var count = $('#headerCartContent').html();
	updateElementId('headerCartContent', --count);
}

/*********************
//* jQuery Drop Line Menu- By Dynamic Drive: http://www.dynamicdrive.com/
//* Last updated: June 27th, 09'
//* Menu avaiable at DD CSS Library: http://www.dynamicdrive.com/style/
*********************/
var droplinemenu={
		arrowimage: {classname: 'downarrowclass', src: 'down.gif', leftpadding: 5}, //customize down arrow image
		animateduration: {over: 200, out: 300}, //duration of slide in/ out animation, in milliseconds
		buildmenu:function(menuid){
			jQuery(document).ready(function($){
				var $mainmenu=$("#"+menuid+">ul");
				var $headers=$mainmenu.find("ul").parent();
				$headers.each(function(i){
					var $curobj=$(this);
					var $subul=$(this).find('ul:eq(0)');
					this._dimensions={h:$curobj.find('a:eq(0)').outerHeight()};
					this.istopheader=$curobj.parents("ul").length==1? true : false;
					if (!this.istopheader)
						$subul.css({left:0, top:this._dimensions.h});
					var $innerheader=$curobj.children('a').eq(0);
					$innerheader=($innerheader.children().eq(0).is('span'))? $innerheader.children().eq(0) : $innerheader; //if header contains inner SPAN, use that
					/*$innerheader.append(
						'<img src="'+ droplinemenu.arrowimage.src
						+'" class="' + droplinemenu.arrowimage.classname
						+ '" style="border:0; padding-left: '+droplinemenu.arrowimage.leftpadding+'px" />'
					);
					*/
					$curobj.hover(
						function(e){
							var $targetul=$(this).children("ul:eq(0)");
							if ($targetul.queue().length<=1) //if 1 or less queued animations
								if (this.istopheader)
									$targetul.css({top: $mainmenu.offset().top-20});
								if (document.all && !window.XMLHttpRequest) //detect IE6 or less, fix issue with overflow
									$mainmenu.find('ul').css({overflow: (this.istopheader)? 'hidden' : 'visible'});
								$targetul.slideDown(droplinemenu.animateduration.over);
						},
						function(e){
							var $targetul=$(this).children("ul:eq(0)");
							$targetul.slideUp(droplinemenu.animateduration.out);
						}
					); //end hover
				}); //end $headers.each()
				$mainmenu.find("ul").css({display:'none', visibility:'visible'});
			}); //end document.ready
		}
		};

function enterKeyPress(e)
{
    code= (e.keyCode ? e.keyCode : e.which);
    if (code == 13) 
    	return true;
    return false;
    
}

function setCurrentMenu(id) {
	//$('#'+id).removeClass('myClass noClass').addClass('yourClass');
	$('#inicioMenu').removeClass('current');
	$('#accesoriosMenu').removeClass('current');
	$('#serviciosMenu').removeClass('current');
	$('#contactosMenu').removeClass('current');
	$('#'+id+'Menu').addClass('current');
}

function getModeloDescripcion(marcaid, modeloid) {
	var parameters = "marcaid="+marcaid+"&modeloid="+modeloid;
	var modelo = "";
	$.ajax({
		   type: "POST",
		   url: CONTEXT_PATH+"/ajax/getModeloDescription.do",
		   data: parameters,
		   async: false,
		   cache: false,
		   success: function(msg){
		     modelo = msg;
		   }
		 });
	return modelo;
}

function removePartFromCart(descripcion, marca, chassis, modelo, anio, part) {
	var valido = false;
	var parameters = "operation=remove&type=parts&descripcion="+descripcion+"&marca="+marca+"&chassis="+chassis+"&modelo="+modelo+"&anio="+anio+"&cat=0&part="+part+"&quantity=0";
	$.ajax({
		   type: "POST",
		   url: CONTEXT_PATH+"/ajax/cartAction.do",
		   data: parameters,
		   async: false,
		   cache: false,
		   success: function(msg){
		     if (msg == 'success') {
		    	 valido=true;
		     } else {
		    	 alert(msg);
		    	 valido =false;
		     }
		   }
		 });
	return valido;
}

function removeServiceFromCart(descripcion, marca, chassis, modelo, anio, date, serviceType,fecha, horario, data) {
	var valido = false;
	var parameters = "operation=remove&type=services&serviceType="+serviceType+"&descripcion="+descripcion+"&marca="+marca+"&chassis="+chassis+"&modelo="+modelo+"&anio="+anio+"&horario="+horario+"&fecha="+fecha;
	var serviceData = data.split("::");
	var urlPart = "";
	if (serviceType == 1) { // cambio de aceite...
		var tipoAceite = serviceData[0];
		var marcaAceite = serviceData[1];
		var cantidadAceite = serviceData[2];
		urlPart = "&oilType="+tipoAceite+"&marcaAceite="+marcaAceite+"&cantidadAceite="+cantidadAceite;
	} else if (serviceType == 2) {
		var tintdoOscuridad = serviceData[1];
		var tipoTintado = serviceData[0];
		var cristalesATintar = serviceData[2];
		urlPart = "&tipoTintado="+tipoTintado+"&oscuridad="+tintdoOscuridad+"&vidriosATintar="+cristalesATintar;
	} else if (serviceType == 3) {
		var tipoBateria = serviceData[0];
		var marcaBateria = serviceData[1];
		var referenciaBateria = serviceData[2];
		urlPart = "&marcaBateria="+marcaBateria+"&tipoBateria="+tipoBateria+"&referenciaBateria="+referenciaBateria;
	} else if (serviceType == 5) {
		urlPart="";
	} else if (serviceType == 6) {
		var referenciaGomas = serviceData[0];
		var marcaGoma = serviceData[1];
		var gomasQuantity = serviceData[2];
		urlPart = "&referenciaGomas="+referenciaGomas+"&marcaPredilecta="+marcaGoma+"&quantity="+gomasQuantity;
	}
	//alert(parameters+urlPart+"&startServiceTime="+date)
	$.ajax({
		   type: "POST",
		   url: CONTEXT_PATH+"/ajax/cartAction.do" ,
		   data: parameters+urlPart+"&startServiceTime="+date,
		   async: false,
		   cache: false,
		   success: function(msg){
		     if (msg == 'success') {
		    	 valido=true;
		     } else {
		    	 alert(msg);
		    	 valido =false;
		     }
		   }
		 });
	return valido;
}

function updatePartInCart(descripcion, marca, chassis, modelo, anio, cat, part, quantity) {
	var valido = false;
	var parameters = "operation=update&type=parts&descripcion="+descripcion+"&marca="+marca+"&chassis="+chassis+"&modelo="+modelo+"&anio="+anio+"&cat="+cat+"&part="+part+"&quantity="+quantity;
	$.ajax({
		   type: "POST",
		   url: CONTEXT_PATH+"/ajax/cartAction.do",
		   data: parameters,
		   async: false,
		   cache: false,
		   success: function(msg){
		     if (msg == 'success') {
		    	 valido=true;
		     } else {
		    	 alert(msg);
		    	 valido =false;
		     }
		   }
		 });
	refreshCarContentCount();
	return valido;
}

function addPartToCart(descripcion, marca, chassis, modelo, anio, cat, part, quantity) {
	var valido = false;
	var parameters = "operation=add&type=parts&descripcion="+descripcion+"&marca="+marca+"&chassis="+chassis+"&modelo="+modelo+"&anio="+anio+"&cat="+cat+"&part="+part+"&quantity="+quantity;
	$.ajax({
		   type: "POST",
		   url: CONTEXT_PATH+"/ajax/cartAction.do",
		   data: parameters,
		   async: false,
		   cache: false,
		   success: function(msg){
		     if (msg == 'success') {
		    	 valido=true;
		     } else {
		    	 alert(msg);
		    	 valido =false;
		     }
		   }
		 });
	refreshCarContentCount();
	return valido;
}
function ask(id, question, callback) {
	$('#'+id+'Msg').html(question);
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
				callback(true);
			},
			'No borrar': function() {
				$(this).dialog('close');
				callback(false);
			}
		}
	});
}

function deleteTr(object) {
	   //$(object).closest("tr").slideUp();fadeOut('slow'
	$(object).closest("tr").fadeOut('slow');
	   //$(object).closest("table").find("tr:eq(" + colnum + ")").remove();
}

function onlyNumbers(event) {
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
}

function addServiceToCart(serviceType, urlPart, fecha, horario, ciudad, sector, calle, numero, apartamento, edificio, telefono, celular, referencias, descripcion, marca, chassis, modelo, anio) {
	var valido = false;
	var parameters = "operation=add&type=services&serviceType="+serviceType+"&fecha="+fecha+"&horario="+horario+"&ciudad="+ciudad+"&sector="+sector;
	parameters += "&calle="+calle+"&numero="+numero+"&apartamento="+apartamento+"&edificio="+edificio+"&telefono="+telefono+"&celular="+celular+"&referencias="+referencias+"&&descripcion="+descripcion+"&marca="+marca+"&chassis="+chassis+"&modelo="+modelo+"&anio="+anio+urlPart;
	//alert(parameters);
	$.ajax({
		   type: "POST",
		   url: CONTEXT_PATH+"/ajax/cartAction.do",
		   data: parameters,
		   async: false,
		   cache: false,
		   success: function(msg){
		     if (msg == 'success') {
		    	 valido=true;
		     } else {
		    	 alert(msg);
		    	 valido =false;
		     }
		   }
		 });
	refreshCarContentCount();
	return valido;
}