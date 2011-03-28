<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/minified/jquery.ui.dialog.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/minified/jquery.ui.position.min.js"></script>
<script src="<%=request.getContextPath()%>/js/grid.locale-sp.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/jquery.jqGrid.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/pages/admin/parts.js" type="text/javascript"> </script> 
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/ui.jqgrid.css" />
<h1>Categorias y Partes</h1>
 			<s:actionerror id="actionError" theme="css_xhtml" />
			<s:actionmessage id="actionMessage" theme="css_xhtml" />

<s:form action="PartCategories" method="POST"  theme="css_xhtml" cssClass="topSpace" onsubmit="return false">
<table id="centerTableFull">
	<tr>
		<td align="center">
		<div id="confirmaBorradoPieza" title="Borrar Pieza" style="display:none;">
			<p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>¿Est&aacute; seguro que desea borrar esta Pieza?</p>
		</div>
		<div id="confirmaBorradoCategoria" title="Borrar Categoría" style="display:none;">
			<p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>¿Est&aacute; seguro que desea borrar esta Categor&iacute;a?</p>
		</div>
			<table class="normalTable" width="745" cellspacing="0" cellpadding="3">
				<tr>
					<td width="435" align="left" valign="top">
							<div id="leftFloat"><h2>Piezas sin categor&iacute;a</h2></div>
							<div id="rightFloat"><a id="editar" href="#" onclick="addPart()" class="fg-button ui-state-default ui-corner-all">Agregar <img width="20" height="20" src="<%=request.getContextPath()%>/templates/default/img/add.png" border="0" /></a></div>
							<div id="clearSides"></div>
						<div id="orphanPartEdit" class="editMode ui-dialog ui-widget ui-widget-content ui-corner-all" style="width:435px;">
							 <div style="-moz-user-select: none;" unselectable="on" class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix">
 							<span style="-moz-user-select: none;" unselectable="on" id="ui-dialog-title-confirmaBorradoAutomovil" class="ui-dialog-title">Editar Pieza</span>  
							<a style="-moz-user-select: none;" unselectable="on" role="button" class="ui-dialog-titlebar-close ui-corner-all" href="#"><span style="-moz-user-select: none;" unselectable="on" class="ui-icon ui-icon-closethick">close</span>
							</a>
							</div>
							<div class="ui-dialog-content ui-widget-content">
							<div id="orphanPartError" class="error"></div>
							<input type="hidden" name="piezaid" value="" id="piezaid" />
						<table class="normalTable" width="420" cellspacing="0" cellpadding="3">
							<tr class="iteredRow">
								<td width="25%">Pieza : </td>
								<td width="10%"></td>
								<td width="65%"><input name="pieza" value="" id="piezaEdit" size="38" /></td>
							</tr>
							<tr>
								<td>Disponible : </td>
								<td></td>
								<td>
									<select name="disponibleEdit" id="disponibleEdit">
										<option value="1" selected="selected">Si</option>
										<option value="0">No</option>
									</select>
								</td>
							</tr>
							<tr class="iteredRow">
								<td>Categor&iacute;a : </td>
								<td></td>
								<td><select name="categoryEdit" id="categoryEdit">
									<option value="0" selected="selected"></option>
									<s:iterator value="categories" status="status">
										<option value="<s:property value="categoryId"/>"><s:property value="category" /></option>
									</s:iterator>
									</select>
								</td>
							</tr>
						</table>
						</div>
						<div class="ui-dialog-buttonpane ui-widget-content ui-helper-clearfix">
						<div id="basicaButtons" align="center">
							<div id="editPartProgressBar" style="display:none;" align="center"><img src="<%=request.getContextPath()%>/templates/default/img/loadingWatch.gif" border="0" /></div>
							<button id="ButtonAddPart" style="display:none;" type="button" onclick="if (addPiezaAction()) { editView('orphanPartEdit');backgroundFilter();}" class="fg-button ui-state-default ui-priority-primary ui-corner-all">Agregar</button>
							<button id="ButtonSavePart" type="button" onclick="if (savePiezaAction()) { editView('orphanPartEdit');backgroundFilter();}" class="fg-button ui-state-default ui-priority-primary ui-corner-all">Salvar</button>
							<button id="ButtonCancelEditPart" type="button" onclick="editView('orphanPartEdit');backgroundFilter();" class="fg-button ui-state-default ui-priority-secondary ui-corner-all">Cancelar</button>
							<br />
						</div>
						</div>
						</div>
						<div id="partsTableProgressBar" style="display:none;" align="center"><img src="<%=request.getContextPath()%>/templates/default/img/loadingWatch.gif" border="0" /></div>
						
						<table class="normalTable" width="420" cellspacing="0" cellpadding="3" id="partList">
							<tbody>
								<tr>
									<th width="320">Pieza </th>
									<th width="10"></th>
									<th width="45" align="center">Categor&iacute;a</th>
									<th width="22" align="center">E</th>
									<th width="23" align="center">Borrar</th>
							   </tr>
							</tbody>
								<s:iterator value="orphanParts" status="status">
									<tbody>
									<tr class="<s:if test="#status.odd == true ">iteredRow</s:if><s:else>nothing</s:else>">
										<td><span id="iteredpieza<s:property value="piezaId" />"><s:property value="descripcion" /></span></td>
										<td></td>
										<td align="center"><a href="#" onclick="assignCategory(<s:property value="piezaId" />,<s:property value="disponible" />,'<s:property value="descripcion" />', '<s:property value="category" />' )">Asignar</a></td>
										<td align="center"><a href="#" onclick="editPart(<s:property value="piezaId" />,<s:property value="disponible" />,'<s:property value="descripcion" />', '<s:property value="category" />' )"><img width="15" height="15" src="<%=request.getContextPath()%>/templates/default/img/edit.png" border="0" /></a></td>
										<td align="center"><a href="#" onclick="deletePart(<s:property value="piezaId" />)"><img width="15" height="15" src="<%=request.getContextPath()%>/templates/default/img/Delete.png" border="0" /></a></td>
								   </tr>
								   </tbody>
								</s:iterator>
						</table>
					</td>
					<td>&nbsp;
					</td>
					<td width="285" align="left" valign="top" class="borderLeftDotted">
							<div id="leftFloat"><h2>Categor&iacute;as</h2></div>
							<div id="rightFloat"><a id="editar" href="#" onclick="addPartCategory()" class="fg-button ui-state-default ui-corner-all">Agregar <img width="20" height="20" src="<%=request.getContextPath()%>/templates/default/img/add.png" border="0" /></a></div>
							<div id="clearSides"></div>
					
						<div id="categoryEditView" class="editMode ui-dialog ui-widget ui-widget-content ui-corner-all" style="width:300px;">
							 <div style="-moz-user-select: none;" unselectable="on" class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix">
 							<span style="-moz-user-select: none;" unselectable="on" id="ui-dialog-title-confirmaBorradoAutomovil" class="ui-dialog-title">Editar Categor&iacute;a</span>  
							<a style="-moz-user-select: none;" unselectable="on" role="button" class="ui-dialog-titlebar-close ui-corner-all" href="#"><span style="-moz-user-select: none;" unselectable="on" class="ui-icon ui-icon-closethick">close</span>
							</a>
							</div>
							<div class="ui-dialog-content ui-widget-content">
							<div id="categoryError" class="error"></div>
							<input type="hidden" name="categoryidedit" value="" id="categoryidedit" />
						<table class="normalTable" width="281" cellspacing="0" cellpadding="3">
							<tr class="iteredRow">
								<td width="25%">Categor&iacute;a : </td>
								<td width="10%"></td>
								<td width="65%"><input name="categoryDescEdit" value="" id="categoryDescEdit" size="23" /></td>
							</tr>
							<tr>
								<td>Disponible : </td>
								<td></td>
								<td>
									<select name="statusEdit" id="statusEdit">
										<option value="1" selected="selected">Activa</option>
										<option value="0">Inactiva</option>
									</select>
								</td>
							</tr>
							<tr class="iteredRow">
								<td>Categor&iacute;a padre: </td>
								<td></td>
								<td><select name="parentCategoryEdit" id="parentCategoryEdit">
									<option value="0" selected="selected"></option>
									<s:iterator value="categories" status="status">
										<option value="<s:property value="categoryId"/>"><s:property value="category" /></option>
									</s:iterator>
									</select>
								</td>
							</tr>
						</table>
						</div>
						<div class="ui-dialog-buttonpane ui-widget-content ui-helper-clearfix">
						<div id="basicaButtons" align="center">
							<div id="editCategoryProgressBar" style="display:none;" align="center"><img src="<%=request.getContextPath()%>/templates/default/img/loadingWatch.gif" border="0" /></div>
							<button id="ButtonAddCategory" style="display:none;" type="button" onclick="if (addCategoryAction()) { editView('categoryEditView');backgroundFilter();}" class="fg-button ui-state-default ui-priority-primary ui-corner-all">Agregar</button>
							<button id="ButtonSaveCategory" type="button" onclick="if (saveCategoryAction()) { editView('categoryEditView');backgroundFilter();}" class="fg-button ui-state-default ui-priority-primary ui-corner-all">Salvar</button>
							<button id="ButtonCancelEditCategory" type="button" onclick="editView('categoryEditView');backgroundFilter();" class="fg-button ui-state-default ui-priority-secondary ui-corner-all">Cancelar</button>
							<br />
						</div>
						</div>
						</div>
						<div id="catsTableProgressBar" style="display:none;" align="center"><img src="<%=request.getContextPath()%>/templates/default/img/loadingWatch.gif" border="0" /></div>
						<table class="normalTable" width="280" cellspacing="0" cellpadding="3" id="categoryList">
							<tbody>
								<tr>
									<th width="50%">Categor&iacute;a</th>
									<th width="5%"></th>
									<th width="15%" align="center">Piezas</th>
									<th width="15%" align="center">E</th>
									<th width="15%" align="center">Borrar</th>
							   </tr>
							</tbody>
							<s:iterator value="categories" status="status">
								<tbody>
								<tr class="<s:if test="#status.odd == true ">iteredRow</s:if><s:else>nothing</s:else>">
									<td><s:property value="category" /> </td>
									<td></td>
									<td align="center"><a href="#" onclick="seeParts(<s:property value="categoryId" />)">Piezas</a></td>
									<td align="center"><a href="#" onclick="editCategory(<s:property value="categoryId" />, <s:property value="status" />, '<s:property value="category" />', '<s:property value="parentCategory" />')"><img width="15" height="15" src="<%=request.getContextPath()%>/templates/default/img/edit.png" border="0" /></a></td>
									<td align="center"><a href="#" onclick="deleteCategory(<s:property value="categoryId" />)"><img width="15" height="15" src="<%=request.getContextPath()%>/templates/default/img/Delete.png" border="0" /></a></td>
							   </tr>
							   </tbody>
							</s:iterator>
						</table>
					</td>
				</tr>
			</table>
						
		</td>
	</tr>
</table>

</s:form>