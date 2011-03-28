<%@ taglib prefix="s" uri="/struts-tags" %>

<s:actionmessage theme="css_xhtml" />

<script>
function delayer(){
    window.location = "<%=request.getContextPath()%>/Home.do";
}
setTimeout('delayer()', 4000);
</script>