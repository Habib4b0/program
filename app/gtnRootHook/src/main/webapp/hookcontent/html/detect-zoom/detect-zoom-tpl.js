document.write("<div id='detect-zoom-main' class='detect-zoom-container' aria-live='assertive' aria-label='This content is announced automatically and does not need to be navigated into.' aria-relevant='additions'>");
document.write("<div class='detect-zoom-main-container'>");
document.write("<div class='detect-zoom-slider-system' role='alert'>");
document.write("<div class='detect-zoom-sliderContent'><img class='detect-zoom-slider-img' src='/html/detect-zoom/slider.png' ></div></div>");
document.write("<div class='detect-zoom-notification-system' role='alert'>");
document.write("<div class='detect-zoom-popupContent'>");
document.write("<div class='gwt-HTML'>");
document.write("<h1>Resolution Alert</h1>");
document.write("<p>Application can be viewed best at 100% zoom. Please press <kbd-container><kbd>Ctrl</kbd>+<kbd>+ / -</kbd></kbd-container> to increase/decrease the zoom level.</p>");
document.write("</div>");
document.write("</div>");
document.write("</div>");
document.write("</div>");
document.write("</div>");
document.write("<style>.detect-zoom-slider-img{width:20px !important; vertical-align: top !important;cursor: pointer;}");
document.write(".detect-zoom-main-container{position:relative !important;left:0 !important;right:0;max-width:100%;z-index:10050;}");
document.write(".detect-zoom-notification-system{left:0!important;right:0;max-width:100%;margin:0!important;border-radius:0;-webkit-box-shadow:0 0 20px 0 rgba(0,0,0,.25);box-shadow:0 0 20px 0 rgba(0,0,0,.25);");
document.write("padding:12px 15px;background-color:#444;background-color:rgba(111,137,253,.77);font-weight:400;line-height:22px;visibility:visible;top:0;text-align:center;");
document.write("-webkit-backface-visibility:hidden;-moz-backface-visibility:hidden;-ms-backface-visibility:hidden;backface-visibility:hidden}.detect-zoom-container{display:none;font:300 16px/1.55 'Open Sans',sans-serif;");
document.write("cursor:default}.detect-zoom-notification-system h1{color:#fff;vertical-align:middle;font-size:19px;line-height:1;margin:0;display:inline-block;text-align:left;font-weight:inherit;white-space:nowrap;letter-spacing:0}");
document.write(".detect-zoom-notification-system h1~p{margin-left:24px}.detect-zoom-notification-system p{color:#fffbfb;line-height:1.4;margin:0;display:inline-block;vertical-align:middle;max-width:55em;text-align:left;max-height:20em;");
document.write("overflow:auto}kbd,kbd-container{display:inline-block;margin:0 .1em;font-family:Arial,'Helvetica Neue',Helvetica,sans-serif;font-size:11px;line-height:1.4;color:#242729;text-shadow:0 1px 0 #FFF;background-color:#e1e3e5;");
document.write("box-shadow:0 1px 0 rgba(12,13,14,.2),0 0 0 2px #FFF inset;white-space:nowrap}kbd-container{padding:.1em;border:1px solid #adb3b9;border-radius:3px}kbd{padding:.1em .6em;border:1px solid #adb3b9;border-radius:3px}.detect-zoom-slider-system");
document.write("{position:absolute;left:50% !important;top:100% !important;}</style>");
$(document).ready(function () {
        refresh();
        $(window).on('resize', refresh);	
		$(".detect-zoom-slider-img").on('click', sliderClick);		
    });
	function refresh() {
		if(window.detectZoom.isResized()){
			$("#detect-zoom-main").show();
			$(".detect-zoom-notification-system").show();
		}else{
		    $("#detect-zoom-main").hide();
		}	
    }	
	function sliderClick() {	
			$(".detect-zoom-notification-system").slideToggle();
    }