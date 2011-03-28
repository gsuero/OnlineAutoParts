<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/slider.css" type="text/css"
	media="screen" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.easing.1.2.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.anythingslider.js"
	type="text/javascript" charset="utf-8"></script>

<script type="text/javascript">
	function formatText(index, panel) {
		return index + "";
	}

	$(function() {
		$('.anythingSlider').anythingSlider( {
			easing : "easeInOutExpo", // Anything other than "linear" or "swing" requires the easing plugin
			autoPlay : true, // This turns off the entire FUNCTIONALY, not just if it starts running or not.
			delay : 3000, // How long between slide transitions in AutoPlay mode
			startStopped : false, // If autoPlay is on, this can force it to start stopped
			animationTime : 600, // How long the slide transition takes
			hashTags : true, // Should links change the hashtag in the URL?
			buildNavigation : true, // If true, builds and list of anchor links to link to each slide
			pauseOnHover : true, // If true, and autoPlay is enabled, the show will pause on hover
			startText : "Iniciar", // Start text
			stopText : "Parar", // Stop text
			navigationFormatter : formatText
		// Details at the top of the file on this use (advanced use)
				});

		$("#slide-jump").click(function() {
			$('.anythingSlider').anythingSlider(6);
		});

	});
</script>

<table class="normalTable home" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td width="520" align="left">
			<div class="anythingSlider">
	          <div class="wrapper">
	            <ul>
	              <li>                 
	                 <div id="quoteSlide">
	                    <blockquote>Nunca me habia impresionado tanto un piloto como aquel pequeño Canadiense que aparecio en mi fabrica con un mono raido, una maleta vieja y solo 200$…Solo Tazio Nuvolari me habia echo disfrutar como Gilles Villeneuve.</blockquote>
	                    <p> - <a id="perma" href="http://es.wikipedia.org/wiki/Enzo_Ferrari">Enzo Ferrari</a></p>
	                 </div>
	              </li>
	             <li>
	         	    <img src="<%=request.getContextPath()%>/images/slides/nissanV6.jpg" border="0"></img>
	             </li>
	             <li>
	         	    <img src="<%=request.getContextPath()%>/images/slides/bmw.jpg" border="0"></img>
	             </li>	
	             <li>
	                 <div id="quoteSlide">
	                    <blockquote>Yo no se como es el alma, pero si es que existe, los motores deben tener una porque se quejan, se desesperan, se rebelan y se comportan como niños a los que se forma, día a día, educándolos.</blockquote>
	                    <p> - <a id="perma" href="http://es.wikipedia.org/wiki/Enzo_Ferrari">Enzo Ferrari</a></p>
	                 </div>
	             </li>
	             <li>
	         	    <img src="<%=request.getContextPath()%>/images/slides/bmw.jpg" border="0"></img>
	             </li>	              
	            </ul>        
	          </div>
	        </div>
        </td>
	<td valign="top" align="center">
		<table cellspacing="0" cellpadding="0" class="tableBox cotiza">
		<tr>
			<th>Cotiza tu pieza</th>
		</tr>
		<tr>
			<td></td>
		</tr>
		</table>
	</td>
	</tr>
	<tr>
		<td align="left" valign="top">
			<div id="accesorios" class="boxDiv">&nbsp;</div>
			<div id="cambioVidrio" class="boxDiv">&nbsp;</div>
			<div id="cambioBateria" class="boxDiv">&nbsp;</div>
		</td>
		<td align="center" valign="top">		
			<div id="specials" class="boxDiv">&nbsp;&nbsp;</div>
		</td>
	</tr>
</table>