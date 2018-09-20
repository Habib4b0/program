<#assign side_bar_shortcut =  getterUtil.getString(theme_settings["side_bar_shortcut"]!"", "") />
<#assign horizontal_menu =  getterUtil.getString(theme_settings["horizontal_menu"]!"", "") />
<#assign footer_section_1 =  getterUtil.getString(theme_settings["footer_section_1_article_id"]!"", "") />
<#assign show_signout  = "true">
<#if is_sso??>
<#assign show_signout  = "false">
</#if>
<#assign displayLanguage = false />

<#if journalContentUtil??>
	<#--********************** header-menu *************************** -->
	<#if journalContentUtil.getContent(group_id?long,  nav_header_profile_menu , "", "${locale}", theme_display) != null >
	    <#assign nav_header_profile_menu = journalContentUtil.getContent(group_id?long,  nav_header_profile_menu, "", "${locale}", theme_display) />
	</#if>

	<#--*********************** side-bar-shortcut *********************# -->
	<#if journalContentUtil.getContent(group_id?long,  side_bar_shortcut , "", "${locale}", theme_display) != null >
	    <#assign side_bar_shortcut = journalContentUtil.getContent(group_id?long,  side_bar_shortcut, "", "${locale}", theme_display) />
	</#if>
	
	<#------------ Horizontal menu ---------- ## -->
	<#if journalContentUtil.getContent(group_id?long,  horizontal_menu_id , "", "${locale}", theme_display) != null >
	    <#assign horizontal_menu = journalContentUtil.getContent(group_id?long,  horizontal_menu_id, "", "${locale}", theme_display) />
	<#elseif getterUtil.getString(theme_settings["horizontal_menu_id"]!"", "") == "Site pages">
		<#assign horizontal_menu =  "#sitepages()" />
	</#if>
		
	<#------------ Footer Content ---------- ## -->
	<#if journalContentUtil.getContent(group_id?long,  footer_section_1_article_id , "", "${locale}", theme_display) != null >
	    <#assign footer_section_1_article_id = journalContentUtil.getContent(group_id?long,  footer_section_1_article_id, "", "${locale}", theme_display) />
	<#elseif getterUtil.getString(theme_settings["footer_section_1_article_id"]!"", "") == "Site pages">
		<#assign footer_section_1_article_id =  "#sitepages()" />
	</#if>
	
	<#-----------  Language bar ---------- ## -->
	<#if getterUtil.getBoolean(theme_settings["show_language_choice"]!"", false)>
		<#assign displayLanguage = true />
	</#if>
</#if>