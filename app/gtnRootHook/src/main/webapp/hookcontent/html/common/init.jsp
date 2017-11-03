<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/ddm" prefix="liferay-ddm" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-bean-el" prefix="bean-el" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-html-el" prefix="html-el" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic-el" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>

<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="com.stpl.counter.service.CounterLocalServiceUtil" %><%@
page import="com.stpl.portal.LocaleException" %><%@
page import="com.stpl.portal.NoSuchLayoutException" %><%@
page import="com.stpl.portal.NoSuchRoleException" %><%@
page import="com.stpl.portal.NoSuchUserException" %><%@
page import="com.stpl.portal.kernel.bean.BeanParamUtil" %><%@
page import="com.stpl.portal.kernel.bean.BeanPropertiesUtil" %><%@
page import="com.stpl.portal.kernel.cal.Recurrence" %><%@
page import="com.stpl.portal.kernel.captcha.CaptchaMaxChallengesException" %><%@
page import="com.stpl.portal.kernel.captcha.CaptchaTextException" %><%@
page import="com.stpl.portal.kernel.configuration.Filter" %><%@
page import="com.stpl.portal.kernel.dao.orm.QueryUtil" %><%@
page import="com.stpl.portal.kernel.dao.search.DisplayTerms" %><%@
page import="com.stpl.portal.kernel.dao.search.ResultRow" %><%@
page import="com.stpl.portal.kernel.dao.search.RowChecker" %><%@
page import="com.stpl.portal.kernel.dao.search.SearchContainer" %><%@
page import="com.stpl.portal.kernel.dao.search.SearchEntry" %><%@
page import="com.stpl.portal.kernel.dao.search.TextSearchEntry" %><%@
page import="com.stpl.portal.kernel.exception.PortalException" %><%@
page import="com.stpl.portal.kernel.exception.SystemException" %><%@
page import="com.stpl.portal.kernel.json.JSONArray" %><%@
page import="com.stpl.portal.kernel.json.JSONFactoryUtil" %><%@
page import="com.stpl.portal.kernel.json.JSONObject" %><%@
page import="com.stpl.portal.kernel.language.LanguageUtil" %><%@
page import="com.stpl.portal.kernel.language.LanguageWrapper" %><%@
page import="com.stpl.portal.kernel.language.UnicodeLanguageUtil" %><%@
page import="com.stpl.portal.kernel.log.Log" %><%@
page import="com.stpl.portal.kernel.log.LogFactoryUtil" %><%@
page import="com.stpl.portal.kernel.log.LogUtil" %><%@
page import="com.stpl.portal.kernel.messaging.DestinationNames" %><%@
page import="com.stpl.portal.kernel.plugin.PluginPackage" %><%@
page import="com.stpl.portal.kernel.portlet.DynamicRenderRequest" %><%@
page import="com.stpl.portal.kernel.portlet.LiferayPortletMode" %><%@
page import="com.stpl.portal.kernel.portlet.LiferayPortletRequest" %><%@
page import="com.stpl.portal.kernel.portlet.LiferayPortletResponse" %><%@
page import="com.stpl.portal.kernel.portlet.LiferayPortletURL" %><%@
page import="com.stpl.portal.kernel.portlet.LiferayWindowState" %><%@
page import="com.stpl.portal.kernel.repository.model.FileEntry" %><%@
page import="com.stpl.portal.kernel.repository.model.FileVersion" %><%@
page import="com.stpl.portal.kernel.search.Field" %><%@
page import="com.stpl.portal.kernel.search.Hits" %><%@
page import="com.stpl.portal.kernel.search.Indexer" %><%@
page import="com.stpl.portal.kernel.search.IndexerRegistryUtil" %><%@
page import="com.stpl.portal.kernel.search.QueryConfig" %><%@
page import="com.stpl.portal.kernel.search.SearchContext" %><%@
page import="com.stpl.portal.kernel.search.SearchContextFactory" %><%@
page import="com.stpl.portal.kernel.search.SearchResultUtil" %><%@
page import="com.stpl.portal.kernel.search.Sort" %><%@
page import="com.stpl.portal.kernel.search.SortFactoryUtil" %><%@
page import="com.stpl.portal.kernel.search.Summary" %><%@
page import="com.stpl.portal.kernel.servlet.BrowserSnifferUtil" %><%@
page import="com.stpl.portal.kernel.servlet.BufferCacheServletResponse" %><%@
page import="com.stpl.portal.kernel.servlet.PortalMessages" %><%@
page import="com.stpl.portal.kernel.servlet.ServletContextPool" %><%@
page import="com.stpl.portal.kernel.servlet.ServletContextUtil" %><%@
page import="com.stpl.portal.kernel.servlet.SessionErrors" %><%@
page import="com.stpl.portal.kernel.servlet.SessionMessages" %><%@
page import="com.stpl.portal.kernel.staging.LayoutStagingUtil" %><%@
page import="com.stpl.portal.kernel.template.StringTemplateResource" %><%@
page import="com.stpl.portal.kernel.template.TemplateHandler" %><%@
page import="com.stpl.portal.kernel.template.TemplateHandlerRegistryUtil" %><%@
page import="com.stpl.portal.kernel.upload.UploadException" %><%@
page import="com.stpl.portal.kernel.util.ArrayUtil" %><%@
page import="com.stpl.portal.kernel.util.CalendarFactoryUtil" %><%@
page import="com.stpl.portal.kernel.util.CalendarUtil" %><%@
page import="com.stpl.portal.kernel.util.CharPool" %><%@
page import="com.stpl.portal.kernel.util.Constants" %><%@
page import="com.stpl.portal.kernel.util.ContentTypes" %><%@
page import="com.stpl.portal.kernel.util.CookieKeys" %><%@
page import="com.stpl.portal.kernel.util.DateUtil" %><%@
page import="com.stpl.portal.kernel.util.FastDateFormatFactoryUtil" %><%@
page import="com.stpl.portal.kernel.util.GetterUtil" %><%@
page import="com.stpl.portal.kernel.util.HtmlUtil" %><%@
page import="com.stpl.portal.kernel.util.Http" %><%@
page import="com.stpl.portal.kernel.util.HttpUtil" %><%@
page import="com.stpl.portal.kernel.util.IntegerWrapper" %><%@
page import="com.stpl.portal.kernel.util.JavaConstants" %><%@
page import="com.stpl.portal.kernel.util.KeyValuePair" %><%@
page import="com.stpl.portal.kernel.util.KeyValuePairComparator" %><%@
page import="com.stpl.portal.kernel.util.ListUtil" %><%@
page import="com.stpl.portal.kernel.util.LocaleUtil" %><%@
page import="com.stpl.portal.kernel.util.LocalizationUtil" %><%@
page import="com.stpl.portal.kernel.util.MapUtil" %><%@
page import="com.stpl.portal.kernel.util.MathUtil" %><%@
page import="com.stpl.portal.kernel.util.ObjectValuePair" %><%@
page import="com.stpl.portal.kernel.util.OrderByComparator" %><%@
page import="com.stpl.portal.kernel.util.OrderedProperties" %><%@
page import="com.stpl.portal.kernel.util.ParamUtil" %><%@
page import="com.stpl.portal.kernel.util.PrefsParamUtil" %><%@
page import="com.stpl.portal.kernel.util.PropertiesParamUtil" %><%@
page import="com.stpl.portal.kernel.util.PropertiesUtil" %><%@
page import="com.stpl.portal.kernel.util.PropsKeys" %><%@
page import="com.stpl.portal.kernel.util.ReleaseInfo" %><%@
page import="com.stpl.portal.kernel.util.ResourceBundleUtil" %><%@
page import="com.stpl.portal.kernel.util.ServerDetector" %><%@
page import="com.stpl.portal.kernel.util.SetUtil" %><%@
page import="com.stpl.portal.kernel.util.SortedArrayList" %><%@
page import="com.stpl.portal.kernel.util.StringBundler" %><%@
page import="com.stpl.portal.kernel.util.StringComparator" %><%@
page import="com.stpl.portal.kernel.util.StringPool" %><%@
page import="com.stpl.portal.kernel.util.StringUtil" %><%@
page import="com.stpl.portal.kernel.util.TextFormatter" %><%@
page import="com.stpl.portal.kernel.util.Time" %><%@
page import="com.stpl.portal.kernel.util.TimeZoneUtil" %><%@
page import="com.stpl.portal.kernel.util.Tuple" %><%@
page import="com.stpl.portal.kernel.util.UnicodeFormatter" %><%@
page import="com.stpl.portal.kernel.util.UnicodeProperties" %><%@
page import="com.stpl.portal.kernel.util.UniqueList" %><%@
page import="com.stpl.portal.kernel.util.Validator" %><%@
page import="com.stpl.portal.kernel.workflow.WorkflowConstants" %><%@
page import="com.stpl.portal.kernel.workflow.WorkflowDefinition" %><%@
page import="com.stpl.portal.kernel.workflow.WorkflowDefinitionManagerUtil" %><%@
page import="com.stpl.portal.kernel.workflow.WorkflowEngineManagerUtil" %><%@
page import="com.stpl.portal.kernel.workflow.WorkflowHandlerRegistryUtil" %><%@
page import="com.stpl.portal.layoutconfiguration.util.RuntimePageUtil" %><%@
page import="com.stpl.portal.model.*" %><%@
page import="com.stpl.portal.model.impl.*" %><%@
page import="com.stpl.portal.plugin.PluginUtil" %><%@
page import="com.stpl.portal.portletfilerepository.PortletFileRepositoryUtil" %><%@
page import="com.stpl.portal.security.auth.AuthTokenUtil" %><%@
page import="com.stpl.portal.security.auth.PrincipalException" %><%@
page import="com.stpl.portal.security.permission.ActionKeys" %><%@
page import="com.stpl.portal.security.permission.PermissionChecker" %><%@
page import="com.stpl.portal.security.permission.ResourceActionsUtil" %><%@
page import="com.stpl.portal.service.*" %><%@
page import="com.stpl.portal.service.permission.GroupPermissionUtil" %><%@
page import="com.stpl.portal.service.permission.LayoutPermissionUtil" %><%@
page import="com.stpl.portal.service.permission.LayoutPrototypePermissionUtil" %><%@
page import="com.stpl.portal.service.permission.LayoutSetPrototypePermissionUtil" %><%@
page import="com.stpl.portal.service.permission.PortalPermissionUtil" %><%@
page import="com.stpl.portal.service.permission.PortletPermissionUtil" %><%@
page import="com.stpl.portal.service.permission.RolePermissionUtil" %><%@
page import="com.stpl.portal.struts.StrutsUtil" %><%@
page import="com.stpl.portal.struts.TilesAttributeUtil" %><%@
page import="com.stpl.portal.theme.ThemeDisplay" %><%@
page import="com.stpl.portal.util.ClassLoaderUtil" %><%@
page import="com.stpl.portal.util.JavaScriptBundleUtil" %><%@
page import="com.stpl.portal.util.LayoutLister" %><%@
page import="com.stpl.portal.util.LayoutView" %><%@
page import="com.stpl.portal.util.Portal" %><%@
page import="com.stpl.portal.util.PortalUtil" %><%@
page import="com.stpl.portal.util.PortletCategoryKeys" %><%@
page import="com.stpl.portal.util.PortletKeys" %><%@
page import="com.stpl.portal.util.PrefsPropsUtil" %><%@
page import="com.stpl.portal.util.PropsUtil" %><%@
page import="com.stpl.portal.util.PropsValues" %><%@
page import="com.stpl.portal.util.SessionClicks" %><%@
page import="com.stpl.portal.util.SessionTreeJSClicks" %><%@
page import="com.stpl.portal.util.ShutdownUtil" %><%@
page import="com.stpl.portal.util.WebAppPool" %><%@
page import="com.stpl.portal.util.WebKeys" %><%@
page import="com.stpl.portal.util.comparator.PortletCategoryComparator" %><%@
page import="com.stpl.portal.util.comparator.PortletTitleComparator" %><%@
page import="com.stpl.portal.webserver.WebServerServletTokenUtil" %><%@
page import="com.stpl.portlet.InvokerPortlet" %><%@
page import="com.stpl.portlet.PortalPreferences" %><%@
page import="com.stpl.portlet.PortletConfigFactoryUtil" %><%@
page import="com.stpl.portlet.PortletInstanceFactoryUtil" %><%@
page import="com.stpl.portlet.PortletPreferencesFactoryUtil" %><%@
page import="com.stpl.portlet.PortletResponseImpl" %><%@
page import="com.stpl.portlet.PortletSetupUtil" %><%@
page import="com.stpl.portlet.PortletURLFactoryUtil" %><%@
page import="com.stpl.portlet.PortletURLImpl" %><%@
page import="com.stpl.portlet.PortletURLUtil" %><%@
page import="com.stpl.portlet.RenderParametersPool" %><%@
page import="com.stpl.portlet.RenderRequestFactory" %><%@
page import="com.stpl.portlet.RenderRequestImpl" %><%@
page import="com.stpl.portlet.RenderResponseFactory" %><%@
page import="com.stpl.portlet.RenderResponseImpl" %><%@
page import="com.stpl.portlet.asset.AssetRendererFactoryRegistryUtil" %><%@
page import="com.stpl.portlet.asset.model.AssetCategory" %><%@
page import="com.stpl.portlet.asset.model.AssetEntry" %><%@
page import="com.stpl.portlet.asset.model.AssetRenderer" %><%@
page import="com.stpl.portlet.asset.model.AssetRendererFactory" %><%@
page import="com.stpl.portlet.asset.model.AssetTag" %><%@
page import="com.stpl.portlet.asset.model.AssetVocabulary" %><%@
page import="com.stpl.portlet.asset.service.AssetCategoryLocalServiceUtil" %><%@
page import="com.stpl.portlet.asset.service.AssetCategoryServiceUtil" %><%@
page import="com.stpl.portlet.asset.service.AssetEntryLocalServiceUtil" %><%@
page import="com.stpl.portlet.asset.service.AssetEntryServiceUtil" %><%@
page import="com.stpl.portlet.asset.service.AssetTagLocalServiceUtil" %><%@
page import="com.stpl.portlet.asset.service.AssetTagServiceUtil" %><%@
page import="com.stpl.portlet.asset.service.AssetVocabularyLocalServiceUtil" %><%@
page import="com.stpl.portlet.asset.service.AssetVocabularyServiceUtil" %><%@
page import="com.stpl.portlet.asset.service.persistence.AssetEntryQuery" %><%@
page import="com.stpl.portlet.asset.util.AssetUtil" %><%@
page import="com.stpl.portlet.blogs.model.BlogsEntry" %><%@
page import="com.stpl.portlet.documentlibrary.FileSizeException" %><%@
page import="com.stpl.portlet.documentlibrary.model.DLFileEntry" %><%@
page import="com.stpl.portlet.documentlibrary.model.DLFileEntryConstants" %><%@
page import="com.stpl.portlet.documentlibrary.service.DLAppLocalServiceUtil" %><%@
page import="com.stpl.portlet.documentlibrary.util.DLUtil" %><%@
page import="com.stpl.portlet.documentlibrary.util.DocumentConversionUtil" %><%@
page import="com.stpl.portlet.dynamicdatamapping.NoSuchStructureException" %><%@
page import="com.stpl.portlet.dynamicdatamapping.model.DDMStructure" %><%@
page import="com.stpl.portlet.dynamicdatamapping.model.DDMTemplate" %><%@
page import="com.stpl.portlet.dynamicdatamapping.service.DDMStructureLocalServiceUtil" %><%@
page import="com.stpl.portlet.dynamicdatamapping.service.DDMTemplateLocalServiceUtil" %><%@
page import="com.stpl.portlet.dynamicdatamapping.service.permission.DDMTemplatePermission" %><%@
page import="com.stpl.portlet.expando.model.ExpandoBridge" %><%@
page import="com.stpl.portlet.journal.action.EditArticleAction" %><%@
page import="com.stpl.portlet.journal.model.JournalArticle" %><%@
page import="com.stpl.portlet.journal.model.JournalArticleConstants" %><%@
page import="com.stpl.portlet.journal.model.JournalArticleDisplay" %><%@
page import="com.stpl.portlet.journal.search.ArticleSearch" %><%@
page import="com.stpl.portlet.journal.search.ArticleSearchTerms" %><%@
page import="com.stpl.portlet.journal.service.JournalArticleLocalServiceUtil" %><%@
page import="com.stpl.portlet.journal.service.JournalArticleServiceUtil" %><%@
page import="com.stpl.portlet.journalcontent.util.JournalContentUtil" %><%@
page import="com.stpl.portlet.messageboards.model.MBMessage" %><%@
page import="com.stpl.portlet.messageboards.service.MBMessageLocalServiceUtil" %><%@
page import="com.stpl.portlet.portletconfiguration.util.PortletConfigurationUtil" %><%@
page import="com.stpl.portlet.portletdisplaytemplate.util.PortletDisplayTemplateUtil" %><%@
page import="com.stpl.portlet.rolesadmin.util.RolesAdminUtil" %><%@
page import="com.stpl.portlet.sites.util.Sites" %><%@
page import="com.stpl.portlet.sites.util.SitesUtil" %><%@
page import="com.stpl.portlet.trash.model.TrashEntry" %><%@
page import="com.stpl.portlet.trash.util.TrashUtil" %><%@
page import="com.stpl.portlet.usergroupsadmin.search.UserGroupDisplayTerms" %><%@
page import="com.stpl.portlet.usergroupsadmin.search.UserGroupSearch" %><%@
page import="com.stpl.portlet.usersadmin.search.GroupSearch" %><%@
page import="com.stpl.portlet.usersadmin.search.GroupSearchTerms" %><%@
page import="com.stpl.portlet.usersadmin.search.OrganizationSearch" %><%@
page import="com.stpl.portlet.usersadmin.search.OrganizationSearchTerms" %><%@
page import="com.stpl.portlet.usersadmin.search.UserSearch" %><%@
page import="com.stpl.portlet.usersadmin.search.UserSearchTerms" %><%@
page import="com.stpl.portlet.usersadmin.util.UsersAdminUtil" %><%@
page import="com.stpl.taglib.util.OutputTag" %><%@
page import="com.stpl.util.ContentUtil" %><%@
page import="com.stpl.util.CreditCard" %><%@
page import="com.stpl.util.Encryptor" %><%@
page import="com.stpl.util.JS" %><%@
page import="com.stpl.util.PKParser" %><%@
page import="com.stpl.util.PwdGenerator" %><%@
page import="com.stpl.util.State" %><%@
page import="com.stpl.util.StateUtil" %><%@
page import="com.stpl.util.log4j.Levels" %><%@
page import="com.stpl.util.portlet.PortletRequestUtil" %><%@
page import="com.stpl.util.xml.XMLFormatter" %>

<%@ page import="java.io.Serializable" %>

<%@ page import="java.text.DateFormat" %><%@
page import="java.text.DecimalFormat" %><%@
page import="java.text.Format" %><%@
page import="java.text.NumberFormat" %><%@
page import="java.text.SimpleDateFormat" %>

<%@ page import="java.util.ArrayList" %><%@
page import="java.util.Arrays" %><%@
page import="java.util.Calendar" %><%@
page import="java.util.Collection" %><%@
page import="java.util.Collections" %><%@
page import="java.util.Currency" %><%@
page import="java.util.Date" %><%@
page import="java.util.Enumeration" %><%@
page import="java.util.HashMap" %><%@
page import="java.util.HashSet" %><%@
page import="java.util.Iterator" %><%@
page import="java.util.LinkedHashMap" %><%@
page import="java.util.LinkedHashSet" %><%@
page import="java.util.List" %><%@
page import="java.util.Locale" %><%@
page import="java.util.Map" %><%@
page import="java.util.Properties" %><%@
page import="java.util.ResourceBundle" %><%@
page import="java.util.Set" %><%@
page import="java.util.Stack" %><%@
page import="java.util.TimeZone" %><%@
page import="java.util.TreeMap" %><%@
page import="java.util.TreeSet" %>

<%@ page import="javax.portlet.MimeResponse" %><%@
page import="javax.portlet.PortletConfig" %><%@
page import="javax.portlet.PortletContext" %><%@
page import="javax.portlet.PortletException" %><%@
page import="javax.portlet.PortletMode" %><%@
page import="javax.portlet.PortletPreferences" %><%@
page import="javax.portlet.PortletRequest" %><%@
page import="javax.portlet.PortletResponse" %><%@
page import="javax.portlet.PortletURL" %><%@
page import="javax.portlet.ResourceURL" %><%@
page import="javax.portlet.UnavailableException" %><%@
page import="javax.portlet.ValidatorException" %><%@
page import="javax.portlet.WindowState" %>

<liferay-theme:defineObjects />

<%@ include file="/html/common/init-ext.jsp" %>