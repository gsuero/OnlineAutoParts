var X = 0;
var Y = 0;



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
	$('#'+id).get(0).style.display='none';
}
function showById(id, display) {
	$('#'+id).get(0).style.display=display;
}

function updateElementId(id, value) {
	$('#'+id).html(value);
}



/*********************
//* jQuery Drop Line Menu- By Dynamic Drive: http://www.dynamicdrive.com/
//* Last updated: June 27th, 09'
//* Menu avaiable at DD CSS Library: http://www.dynamicdrive.com/style/
*********************/
var droplinemenu={
		arrowimage: {classname: 'downarrowclass', src: 'down.gif', leftpadding: 5}, //customize down arrow image
		animateduration: {over: 200, out: 100}, //duration of slide in/ out animation, in milliseconds
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
									$targetul.css({top: $mainmenu.offset().top-37});
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