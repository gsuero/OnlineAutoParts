<%@ taglib prefix="s" uri="/struts-tags"%>
<table class="normalTable" width="344" cellspacing="0" cellpadding="3" id="carList">
	<s:iterator value="%{#request.USER_CARS}" status="status">
		<tbody>
		<tr class="<s:if test="#status.odd == true ">iteredRow</s:if><s:else>nothing</s:else>">
			<td width="90"><strong>Veh&iacute;culo #<s:property value="%{#status.index+1}" /></strong> :</td>
			<td width="10"></td>
			<td width="170"><s:property value="description" /></td>
			<td width="30" align="center"><a href="#" onclick="setVehicleView(<s:property value="%{#status.index+1}" />)"><img width="15" height="15" src="<%=request.getContextPath()%>/templates/default/img/edit.png" border="0" /></a></td>
			<td width="30" align="center"><a href="#" onclick="borrarAutomovil('<s:property value="description" />', '<s:property value="chassis" />')"><img width="15" height="15" src="<%=request.getContextPath()%>/templates/default/img/Delete.png" border="0" /></a></td>
	   </tr>
	   </tbody>
	</s:iterator>
</table>