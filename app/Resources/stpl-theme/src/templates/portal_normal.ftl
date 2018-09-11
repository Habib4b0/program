<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key=" lang.dir" />" lang="${w3c_language_id}">

<head>
	<title>${the_title} - ${company_name}</title>
	<meta content="initial-scale=1.0, width=device-width" name="viewport" />
	<@liferay_util["include"] page=top_head_include />
	<script>
		window.__define = window.define;
		window.__require = window.require;
		window.define = undefined;
		window.require = undefined;
	</script>

	<link rel="stylesheet" href="${themeDisplay.getPathThemeRoot()}/css/style.css">
	<link rel="stylesheet" href="${themeDisplay.getPathThemeRoot()}/css/custom-Style/application-custom-styles.css">
	<link rel="stylesheet" href="${themeDisplay.getPathThemeRoot()}/css/custom-Style/gtnFrameworkCss.css">
	<link rel="stylesheet" href="${themeDisplay.getPathThemeRoot()}/css/custom-Style/portal-custom-styles.css">


	<#--<link rel="stylesheet" href="${themeDisplay.getPathThemeRoot()}/css/bootstrap.min.css">
		<#--<link href="${themeDisplay.getPathThemeRoot()}/css/font-awesome.min.css" rel="stylesheet"> -->
			<#-- <script src="${themeDisplay.getPathThemeRoot()}/js/jquery-1.12.4.min.js">
				</script> -->
				<#--<script src="${themeDisplay.getPathThemeRoot()}/js/bootstrap.min.js">
					</script> -->
					<script src="${themeDisplay.getPathThemeRoot()}/js/custom.js"></script>


					<script>
						window.define = window.__define;
						window.require = window.__require;
						window.__define = undefined;
						window.__require = undefined;
					</script>
</head>

<body class="${css_class}">
	<#if is_signed_in>
		<@liferay_util["include"] page=body_top_include />
		<#if permissionChecker.isOmniadmin()>
			<@liferay.control_menu />
		</#if>
		<nav id="main-head" class="navbar navbar-default">
			<div class="container-fluid">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle mobile-nav collapsed" data-toggle="collapse" data-target="#sidebar"
					 aria-expanded="false">
						<span></span>
						<span></span>
						<span></span>
					</button>
					<a id="company_logo" class="navbar-brand" href="${theme_display.getURLHome()}"> <img src="${htmlUtil.escape(theme_display.getCompanyLogo())}"
						 alt="Company-logo" "></a>
    </div>  
    <div class=" collapse navbar-collapse">
						<ul class="nav navbar-nav navbar-right">
							<li class="dropdown profile">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
									<img src="${themeDisplay.getPathThemeRoot()}/img/user_male_portrait.png" alt=""> <span class="user-full-name"><small>Welcome,</small>
										${user_first_name} </span><span class="caret"></span></a>
								<ul class="dropdown-menu">
									<#assign myProfile=theme_display.getURLMyAccount().toString() + "&controlPanelCategory=my" />
									<li><a href='${myProfile}'>My Profile</a></li>
									<li><a href="${sign_out_url}">Sign Out</a></li>
								</ul>
							</li>
						</ul>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>
		<div class="clear"></div>
		<div class="wrapper">
			<#include "${full_templates_path}/side_bar.ftl" />

			<div id="content" class="container-fluid sidebar-max">
				<div class="row">
					<div class="breadcrumbs col-lg-12">
						<ul>
							<@liferay.breadcrumbs />
						</ul>
					</div>
				</div>
				<#else>
				<a class="pull-right white-text" data-redirect="false" href="${htmlUtil.escape(theme_display.getURLSignIn())}" role="menuitem" title=""> 
					<i class="icon-user"></i> <span class="nav-item-label">  ${languageUtil.get(locale, "sign-in") }</span> 
				</a>			
			</#if>
	<div class="page-content">
		<#if selectable>
			<@liferay_util["include"] page=content_include />
			<#else>
				${portletDisplay.recycle()}

				${portletDisplay.setTitle(the_title)}

				<@liferay_theme["wrap-portlet"] page="portlet.ftl">
					<@liferay_util["include"] page=content_include />
					</@>
		</#if>
	</div>
	</div> <!-- main-content-->
	</div><!-- /#content -->
	</div>

	<#--<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-small btn-inverse">
		<i class="icon-double-angle-up icon-only bigger-110"></i>
		</a>

		<div class="clear"></div>
		<#include "${full_templates_path}/footer.ftl" />-->

		<@liferay_util["include"] page=body_bottom_include />
		<@liferay_util["include"] page=bottom_include />
</body>

</html>