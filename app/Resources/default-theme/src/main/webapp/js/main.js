$(document).ready(
	function() {
		try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
		var homeIcon = $("<i class='icon-home home-icon'></i>");
		var divider = $("<span class='divider'><i class='icon-angle-right arrow-icon'></i></span>");
		homeIcon.insertBefore(".breadcrumbs li.first a");
		$(".breadcrumbs .divider").remove();
		divider.insertAfter(".breadcrumbs li a");
		$(".breadcrumbs li.last .divider").remove(); 
		
		/* user greeting message */
		$('.user-full-name').prepend('<small>'+Liferay.Language.get("welcome")+',</small>');
		$('#_145_navAccountControlsNavbarBtn').append($('.user-full-name').clone());

		/* Login Hook */
//		var closeBtn = $('<button class="image-viewer-close close" type="button">×</button>');
//		$('body.signed-out .portlet-login').append(closeBtn);
//		var header = $('<h4 class="header blue lighter bigger"><i class="icon-coffee green"></i>Please Enter Your Information</h4>');	
//		header.insertBefore('.portlet-login .portlet .portlet-body form');

		var iconKey = $('<i class="icon-key"></i>');
		$('.portlet-login .portlet .portlet-body form .btn-primary').prepend(iconKey);
		
		var iconUser = $('<i class="icon-user"></i>');
		iconUser.insertAfter('.portlet-login .portlet .portlet-body form #_58_login');
		
		var iconLock = $('<i class="icon-lock"></i>');
		iconLock.insertAfter('.portlet-login .portlet .portlet-body form #_58_password');
		
		/* notification icon */
		var iconBell = $('<i class="icon-bell-alt icon-animated-bell"></i>');
		$('.dockbar-user-notifications .user-notification-link').prepend(iconBell);
		
		
		
	});
$(window).load(function() {
	/* Calender */
	$(".calendar-portlet").delay( 1000 ).fadeIn();
});