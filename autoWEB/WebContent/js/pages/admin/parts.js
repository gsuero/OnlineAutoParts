function seeParts(catid) {
	var url = CONTEXT_PATH+"/adminme/PartsByCat.do?catid="+catid;
	var wind = opennormalpopup(url, "Piezas", 550, 500);	
}

function deleteDirectCat(categoryid) {
	showById('catsTableProgressBar','inline');
	var parameters = "operation=delete&categoryid="+categoryid;
	var borradoExitoso = false;
	$.ajax({
	   type: "POST",
	   url: CONTEXT_PATH+"/ajax/admin/saveCategory.do",
	   data: parameters,
	   async: false,
	   success: function(msg){
	     if (msg == 'success') {
	    	 borradoExitoso = true;
	     } else {
		     alert(msg);
	     }
	   }
	 });

	/* if (borradoExitoso) {
		 $("iteredpieza"+piezaid).closest("tr").remove();
	 }*/
	hideById('catsTableProgressBar');
}


function saveCategoryAction() {
	showById('editCategoryProgressBar','inline');
	hideById('ButtonSaveCategory');
	hideById('ButtonCancelEditCategory');
	var valido = true;
	var categoryid = $('#categoryidedit').val();
	var status = $('#statusEdit').val();
	var parentCategory = $('#parentCategoryEdit').val();
	var desc = $('#categoryDescEdit').val();
	var errorMessage = "";
	
	if (status != 0 && status != 1){ 
		errorMessage += "<br />&nbsp;&nbsp;- Información no integra.";
		valido = false;
	}
	if (parentCategory.length < 1 && parentCategory != 0){
		errorMessage += "<br />&nbsp;&nbsp;- Categoria inválida.";
		valido = false;
	}
	if (desc.lnegth <=1) {
		errorMessage += "<br />&nbsp;&nbsp;- Nombre de pieza invalido.";
		valido = false;
	}
	if (valido) {
		var parameters = "operation=update&categoryid="+categoryid+"&desc="+desc+"&parentcategoria="+parentCategory+"&status="+status;
		$.ajax({
			   type: "POST",
			   url: CONTEXT_PATH+"/ajax/admin/saveCategory.do",
			   data: parameters,
			   async: false,
			   cache: false,
			   success: function(msg){
			     if (msg == 'success') {
			   		//$('#carList > tbody:last').append(newRow);
			    	 $('#categoryError').html("");
//			    	 updateElementId('salutationSpan', salutation);
			    	 valido=true;
			     } else {
			    	 $('#categoryError').html('Existen errores en el formulario: <br /><br />'+msg);
			    	 valido =false;
			     }
			   }
			 });

	} else {
		$('#categoryError').html('Existen errores en el formulario: <br /><br />'+errorMessage);
		valido =false;
	}

	hideById('editCategoryProgressBar');
	showById('ButtonSaveCategory','inline');
	showById('ButtonCancelEditCategory','inline');
	return valido;	
}
function addCategoryAction() {
	showById('editCategoryProgressBar','inline');
	hideById('ButtonAddCategory');
	hideById('ButtonCancelEditCategory');
	var valido = true;
	var status = $('#statusEdit').val();
	var parentCategory = $('#parentCategoryEdit').val();
	var desc = $('#categoryDescEdit').val();
	var errorMessage = "";
	
	if (status != 0 && status != 1){ 
		errorMessage += "<br />&nbsp;&nbsp;- Información no integra.";
		valido = false;
	}
	if (parentCategory.length < 1 && parentCategory != 0){
		errorMessage += "<br />&nbsp;&nbsp;- Categoria inválida.";
		valido = false;
	}
	if (desc.lnegth <=1) {
		errorMessage += "<br />&nbsp;&nbsp;- Nombre de pieza invalido.";
		valido = false;
	}
	if (valido) {
		var parameters = "operation=add&desc="+desc+"&parentcategoria="+parentCategory+"&status="+status;
		$.ajax({
			   type: "POST",
			   url: CONTEXT_PATH+"/ajax/admin/saveCategory.do",
			   data: parameters,
			   async: false,
			   cache: false,
			   success: function(msg){
			     if (msg == 'success') {
			   		//$('#carList > tbody:last').append(newRow);
			    	 $('#categoryError').html("");
//			    	 updateElementId('salutationSpan', salutation);
			    	 valido=true;
			     } else {
			    	 $('#categoryError').html('Existen errores en el formulario: <br /><br />'+msg);
			    	 valido =false;
			     }
			   }
			 });

	} else {
		$('#categoryError').html('Existen errores en el formulario: <br /><br />'+errorMessage);
		valido =false;
	}

	hideById('editCategoryProgressBar');
	showById('ButtonAddCategory','inline');
	showById('ButtonCancelEditCategory','inline');
	return valido;	
}

function deleteCategory(catId) {
	$("#confirmaBorradoCategoria").dialog({
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
				deleteDirectCat(catId);
			},
			'No borrar': function() {
				$(this).dialog('close');
			}
		}
	});
}
function addPartCategory(){
	$('#categoryidedit').val('');
	$('#statusEdit').val('');
	$('#parentCategoryEdit').val('');
	$('#categoryDescEdit').val('');
	hideById('ButtonSaveCategory');
	showById('ButtonAddCategory','inline');
	
	editView('categoryEditView'); 
	backgroundFilter();
}
function viewParts(categoryid) {
	
}

function editCategory(categoryId, status, descripcion, parentCategory){
	$('#categoryidedit').val(categoryId);
	$('#statusEdit').val(status);
	$('#parentCategoryEdit').val(parentCategory);
	$('#categoryDescEdit').val(descripcion);
	
	editView('categoryEditView'); 
	backgroundFilter();
}
// http://trirand.com/blog/jqgrid/server.php?q=2
function addPart(){
	$('#piezaid').val('');
	$('#disponibleEdit').val('');
	$('#categoryEdit').val('');
	$('#piezaEdit').val('');
	hideById('ButtonSavePart');
	showById('ButtonAddPart','inline');
	
	editView('orphanPartEdit'); 
	backgroundFilter();
}

function assignCategory(piezaId, disponible, descripcion, categoryid){
	editPart(piezaId, disponible, descripcion, categoryid);
	$('#categoryEdit').focus();
}
function editPart(piezaId, disponible, descripcion, categoryid){
	$('#piezaid').val(piezaId);
	$('#disponibleEdit').val(disponible);
	$('#categoryEdit').val(categoryid);
	$('#piezaEdit').val(descripcion);
	
	editView('orphanPartEdit'); 
	backgroundFilter();
}

function deletePart(piezaId){
	$("#confirmaBorradoPieza").dialog({
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
				deleteDirectPart(piezaId);
			},
			'No borrar': function() {
				$(this).dialog('close');
			}
		}
	});
}


function addPiezaAction() {
	showById('editPartProgressBar','inline');
	hideById('ButtonAddPart');
	hideById('ButtonCancelEditPart');
	var valido = true;
	var disponible = $('#disponibleEdit').val();
	var categoria = $('#categoryEdit').val();
	var desc = $('#piezaEdit').val();
	var errorMessage = "";
	
	if (disponible != 0 && disponible != 1){ 
		errorMessage += "<br />&nbsp;&nbsp;- Información no integra.";
		valido = false;
	}
	if (categoria.length < 1 && categoria != 0){
		errorMessage += "<br />&nbsp;&nbsp;- Categoria inválida.";
		valido = false;
	}
	if (desc.lnegth <=1) {
		errorMessage += "<br />&nbsp;&nbsp;- Nombre de pieza invalido.";
		valido = false;
	}
	if (valido) {
		var parameters = "operation=add&descripcion="+desc+"&categoria="+categoria+"&disponible="+disponible;
		$.ajax({
			   type: "POST",
			   url: CONTEXT_PATH+"/ajax/admin/savePieza.do",
			   data: parameters,
			   async: false,
			   cache: false,
			   success: function(msg){
			     if (msg == 'success') {
			   		//$('#carList > tbody:last').append(newRow);
			    	 $('#orphanPartError').html("");
//			    	 updateElementId('salutationSpan', salutation);
			    	 valido=true;
			     } else {
			    	 $('#orphanPartError').html('Existen errores en el formulario: <br /><br />'+msg);
			    	 valido =false;
			     }
			   }
			 });

	} else {
		$('#orphanPartError').html('Existen errores en el formulario: <br /><br />'+errorMessage);
		valido =false;
	}

	hideById('editPartProgressBar');
	showById('ButtonAddPart','inline');
	showById('ButtonCancelEditPart','inline');
	return valido;
}

function savePiezaAction() {
	showById('editPartProgressBar','inline');
	hideById('ButtonSavePart');
	hideById('ButtonCancelEditPart');
	var valido = true;
	var piezaid = $('#piezaid').val();
	var disponible = $('#disponibleEdit').val();
	var categoria = $('#categoryEdit').val();
	var desc = $('#piezaEdit').val();
	var errorMessage = "";
	if (piezaid < 1) {
		errorMessage += "<br />&nbsp;&nbsp;- Pieza inválida.";
		valido = false;
	}
	if (disponible != 0 && disponible != 1){ 
		errorMessage += "<br />&nbsp;&nbsp;- Información no integra.";
		valido = false;
	}
	if (categoria.length < 1 && categoria != 0){
		errorMessage += "<br />&nbsp;&nbsp;- Categoria inválida.";
		valido = false;
	}
	if (desc.lnegth <=1) {
		errorMessage += "<br />&nbsp;&nbsp;- Nombre de pieza invalido.";
		valido = false;
	}
	if (valido) {
		var parameters = "operation=update&piezaId="+piezaid+"&descripcion="+desc+"&categoria="+categoria+"&disponible="+disponible;
		$.ajax({
			   type: "POST",
			   url: CONTEXT_PATH+"/ajax/admin/savePieza.do",
			   data: parameters,
			   async: false,
			   cache: false,
			   success: function(msg){
			     if (msg == 'success') {
			   		//$('#carList > tbody:last').append(newRow);
			    	 $('#orphanPartError').html("");
//			    	 updateElementId('salutationSpan', salutation);
			    	 valido=true;
			     } else {
			    	 $('#orphanPartError').html('Existen errores en el formulario: <br /><br />'+msg);
			    	 valido =false;
			     }
			   }
			 });

	} else {
		$('#orphanPartError').html('Existen errores en el formulario: <br /><br />'+errorMessage);
		valido =false;
	}

	hideById('editPartProgressBar');
	showById('ButtonSavePart','inline');
	showById('ButtonCancelEditPart','inline');
	return valido;
}

function deleteDirectPart(piezaid) {
	showById('partsTableProgressBar','inline');
	var parameters = "operation=delete&piezaId="+piezaid;
	var borradoExitoso = false;
	$.ajax({
	   type: "POST",
	   url: CONTEXT_PATH+"/ajax/admin/savePieza.do",
	   data: parameters,
	   async: false,
	   success: function(msg){
	     if (msg == 'success') {
	    	 borradoExitoso = true;
	     } else {
		     alert(msg);
	     }
	   }
	 });

	 if (borradoExitoso) {
		 $("iteredpieza"+piezaid).closest("tr").remove();
	 }
	hideById('partsTableProgressBar');
}


