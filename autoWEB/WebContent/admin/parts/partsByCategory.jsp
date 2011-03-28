<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/minified/jquery.ui.dialog.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/minified/jquery.ui.position.min.js"></script>
<script src="<%=request.getContextPath()%>/js/grid.locale-sp.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/jquery.jqGrid.min.js" type="text/javascript"></script>
<script>
$.jgrid.useJSON = true;
//http://www.trirand.com/jqgridwiki/doku.php?id=wiki%3Acommon_rules
$(document).ready(function() {
	//alert(CONTEXT_PATH);
	var lastsel;
	jQuery("#rowed3").jqGrid(
			{
				url : CONTEXT_PATH+'/ajax/getPartesByCategory.do?catid=<s:property value="categoryId" />',
				//url : '/autoWEB/text.html',
				datatype: "json",
			    ajaxGridOptions: { contentType: "application/json" },
			    jsonReader : { 
			        root: "rows", 
			        page: "page", 
			        total: "total", 
			        records: "records", 
			        repeatitems: false 
			    },
			    headertitles: true,
				colNames : [ 'ID', 'Descripcion', 'Disponible'],
				colModel : [ {
					name : 'piezaId',
					index : 'piezaId',
					align : "right",
					width : 50, 
					editable : false,
					hidden: true, 
					editable: true, 
					editrules: { edithidden: false }, 
					hidedlg: true
				}, {
					name : 'descripcion',
					index : 'descripcion',
					width : 390,
					editable : true,
					required : true
				}, {
					name : 'disponible',
					index : 'disponible',
					width : 80,
					editable : true,
					edittype : 'select',
					editoptions:{value:"0:No;1:Si"},
					required : true
				} ],
				rowNum : 20,
				rowList : [ 20, 40, 60, 80 ],
				pager : '#prowed3',
				sortname : 'piezaId',
				//postData: {piezaId : lastsel},
				//mtype:"POST",
				viewrecords : true,
				sortorder : "desc",
				onSelectRow : function(id) {
					if (id && id !== lastsel) {
						jQuery('#rowed3').jqGrid('restoreRow', lastsel);
						jQuery('#rowed3').jqGrid('editRow', id, true);
						lastsel = id;
					}
				},
				editurl : CONTEXT_PATH+'/ajax/admin/savePieza.do?categoria=<s:property value="categoryId" />',
				caption : "Piezas"
			});
	jQuery("#rowed3").jqGrid('navGrid', "#prowed3", {
		edit : false,
		add : false,
		del : false
	});
})
</script>
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/ui.jqgrid.css" />
<h1>Partes</h1>
 			<s:actionerror id="actionError" theme="css_xhtml" />
			<s:actionmessage id="actionMessage" theme="css_xhtml" />

<s:form action="PartCategories" method="POST"  theme="css_xhtml" cssClass="topSpace" onsubmit="return false">
	<table id="centerTableFull">
		<tr>
			<td align="center">
			Categor&iacute;a: <strong><s:property value="categoryName" /> </strong>
			<table id="rowed3"></table> <div id="prowed3"></div> <br /> 
			</td>
		</tr>
	</table>
</s:form>