<#assign portlet_display = portletDisplay />
<#assign portlet_back_url = htmlUtil.escapeHREF(portlet_display.getURLBack()) />
<#assign portlet_display_name = htmlUtil.escape(portlet_display.getPortletDisplayName()) />
<#assign portlet_display_root_portlet_id = htmlUtil.escape(portlet_display.getRootPortletId()) />
<#assign portlet_id = htmlUtil.escapeAttribute(portlet_display.getId()) />
<#assign portlet_title = htmlUtil.escape(portlet_display.getTitle()) />
<#assign portlet_toolbar = portlet_display.getPortletToolbar() />
<#assign is_signed_in = themeDisplay.isSignedIn() />

<section class="portlet" id="portlet_${portlet_id}">
	<header class="portlet-topper">
		<div class="portlet-title-default">
		</div>

		<#list portlet_toolbar.getPortletTitleMenus(portlet_display_root_portlet_id, renderRequest, renderResponse) as portletTitleMenu>
			<menu class="portlet-title-menu portlet-topper-toolbar" id="portlet-title-menu_${portlet_id}_${portletTitleMenu_index + 1}" type="toolbar">
				<@liferay_ui["menu"] menu=portletTitleMenu />
			</menu>
		</#list>

		<menu class="portlet-topper-toolbar" id="portlet-topper-toolbar_${portlet_id}" type="toolbar">
			<#if portlet_display.isShowBackIcon()>
				<a class="portlet-icon-back" href="${portlet_back_url}"><@liferay.language key="return-to-full-page" /></a>
			<#else>
				<@liferay_portlet["icon-options"] />
			</#if>
		</menu>
	</header>
<#assign portlet_css_class = "" />
<#if is_signed_in>
	<#assign portlet_css_class = "portlet-content" />
</#if>

	<div class="${portlet_css_class}">

	<#if is_signed_in>
	 <h2 class="portlet-title-text">${portlet_title}</h2>  
</#if>
		

		${portlet_display.writeContent(writer)}
	</div>
</section>