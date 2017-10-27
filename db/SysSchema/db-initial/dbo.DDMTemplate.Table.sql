SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DDMTemplate](
	[uuid_] [nvarchar](75) NULL,
	[templateId] [bigint] NOT NULL,
	[groupId] [bigint] NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[userName] [nvarchar](75) NULL,
	[createDate] [datetime] NULL,
	[modifiedDate] [datetime] NULL,
	[classNameId] [bigint] NULL,
	[classPK] [bigint] NULL,
	[templateKey] [nvarchar](75) NULL,
	[name] [nvarchar](2000) NULL,
	[description] [nvarchar](2000) NULL,
	[type_] [nvarchar](75) NULL,
	[mode_] [nvarchar](75) NULL,
	[language] [nvarchar](75) NULL,
	[script] [nvarchar](max) NULL,
	[cacheable] [bit] NULL,
	[smallImage] [bit] NULL,
	[smallImageId] [bigint] NULL,
	[smallImageURL] [nvarchar](75) NULL,
PRIMARY KEY CLUSTERED 
(
	[templateId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
INSERT [dbo].[DDMTemplate] ([uuid_], [templateId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [classNameId], [classPK], [templateKey], [name], [description], [type_], [mode_], [language], [script], [cacheable], [smallImage], [smallImageId], [smallImageURL]) VALUES (N'ee62c77d-6a08-4f9d-9b30-e3cdc641b2d2', 10421, 10197, 10157, 10161, N'', CAST(N'2017-05-12 00:54:38.810' AS DateTime), CAST(N'2017-05-12 00:54:38.810' AS DateTime), 10085, 0, N'ASSET-TAGS-NAVIGATION-COLOR-BY-POPULARITY-FTL', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Name language-id="en_US">Color by Popularity</Name></root>', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Displays asset tags colored by popularity: red (high), yellow (medium), and green (low).</Description></root>', N'display', N'', N'ftl', N'<#if entries?has_content>
	<ul class="tag-items tag-list">
		<#assign scopeGroupId = getterUtil.getLong(scopeGroupId, themeDisplay.getScopeGroupId()) />
		<#assign classNameId = getterUtil.getLong(classNameId, 0) />

		<#assign maxCount = 1 />
		<#assign minCount = 1 />

		<#list entries as entry>
			<#assign maxCount = liferay.max(maxCount, entry.getAssetCount()) />
			<#assign minCount = liferay.min(minCount, entry.getAssetCount()) />
		</#list>

		<#assign multiplier = 1 />

		<#if maxCount != minCount>
			<#assign multiplier = 3 / (maxCount - minCount) />
		</#if>

		<#list entries as entry>
			<li class="taglib-asset-tags-summary">
				<#assign popularity = (maxCount - (maxCount - (entry.getAssetCount() - minCount))) * multiplier />

				<#if popularity < 1>
					<#assign color = "green" />
				<#elseif (popularity >= 1) && (popularity < 2)>
					<#assign color = "orange" />
				<#else>
					<#assign color = "red" />
				</#if>

				<#assign tagURL = renderResponse.createRenderURL() />

				${tagURL.setParameter("resetCur", "true")}
				${tagURL.setParameter("tag", entry.getName())}

				<a class ="tag" style="color: ${color};" href="${tagURL}">
					${entry.getName()}

					<#if (showAssetCount == "true")>
						<span class="tag-asset-count">(${count})</span>
					</#if>
				</a>
			</li>
		</#list>
	</ul>

	<br style="clear: both;" />
</#if>', 0, 0, 10422, N'')
INSERT [dbo].[DDMTemplate] ([uuid_], [templateId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [classNameId], [classPK], [templateKey], [name], [description], [type_], [mode_], [language], [script], [cacheable], [smallImage], [smallImageId], [smallImageURL]) VALUES (N'f8aaf737-4e03-4d2f-9652-fb538ea5ce09', 10423, 10197, 10157, 10161, N'', CAST(N'2017-05-12 00:54:38.860' AS DateTime), CAST(N'2017-05-12 00:54:38.860' AS DateTime), 10083, 0, N'ASSET-PUBLISHER-RICH-SUMMARY-FTL', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Name language-id="en_US">Rich Summary</Name></root>', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Displays abstracts, icons, related assets, and print/edit actions for assets. Optionally include asset bookmarks and ratings.</Description></root>', N'display', N'', N'ftl', N'<#assign liferay_ui = taglibLiferayHash["/WEB-INF/tld/liferay-ui.tld"] />

<#list entries as entry>
	<#assign entry = entry />

	<#assign assetRenderer = entry.getAssetRenderer() />

	<#assign entryTitle = htmlUtil.escape(assetRenderer.getTitle(locale)) />

	<#assign viewURL = assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, entry) />

	<#if assetLinkBehavior != "showFullContent">
		<#assign viewURL = assetRenderer.getURLViewInContext(renderRequest, renderResponse, viewURL) />
	</#if>

	<div class="asset-abstract">
		<div class="lfr-meta-actions asset-actions">
			<@getPrintIcon />

			<@getFlagsIcon />

			<@getEditIcon />
		</div>

		<h3 class="asset-title">
			<a href="${viewURL}"><img alt="" src="${assetRenderer.getIconPath(renderRequest)}" />${entryTitle}</a>
		</h3>

		<@getMetadataField fieldName="tags" />

		<@getMetadataField fieldName="create-date" />

		<@getMetadataField fieldName="view-count" />

		<div class="asset-content">
			<@getSocialBookmarks />

			<div class="asset-summary">
				<@getMetadataField fieldName="author" />

				${htmlUtil.escape(assetRenderer.getSummary(locale))}

				<a href="${viewURL}"><@liferay.language key="read-more" /><span class="hide-accessible"><@liferay.language key="about" />${entryTitle}</span> &raquo;</a>
			</div>

			<@getRatings />

			<@getRelatedAssets />

			<@getDiscussion />
		</div>
	</div>

</#list>

<#macro getDiscussion>
	<#if validator.isNotNull(assetRenderer.getDiscussionPath()) && (enableComments == "true")>
		<br />

		<#assign discussionURL = renderResponse.createActionURL() />

		${discussionURL.setParameter("struts_action", "/asset_publisher/" + assetRenderer.getDiscussionPath())}

		<@liferay_ui["discussion"]
			className=entry.getClassName()
			classPK=entry.getClassPK()
			formAction=discussionURL?string
			formName="fm" + entry.getClassPK()
			ratingsEnabled=enableCommentRatings == "true"
			redirect=portalUtil.getCurrentURL(request)
			userId=assetRenderer.getUserId()
		/>
	</#if>
</#macro>

<#macro getEditIcon>
	<#if assetRenderer.hasEditPermission(themeDisplay.getPermissionChecker())>
		<#assign redirectURL = renderResponse.createRenderURL() />

		${redirectURL.setParameter("struts_action", "/asset_publisher/add_asset_redirect")}
		${redirectURL.setWindowState("pop_up")}

		<#assign editPortletURL = assetRenderer.getURLEdit(renderRequest, renderResponse, windowStateFactory.getWindowState("pop_up"), redirectURL)!"" />

		<#if validator.isNotNull(editPortletURL)>
			<#assign title = languageUtil.format(locale, "edit-x", entryTitle) />

			<@liferay_ui["icon"]
				image="edit"
				message=title
				url="javascript:Liferay.Util.openWindow({dialog: {width: 960}, id:''" + renderResponse.getNamespace() + "editAsset'', title: ''" + title + "'', uri:''" + htmlUtil.escapeURL(editPortletURL.toString()) + "''});"
			/>
		</#if>
	</#if>
</#macro>

<#macro getFlagsIcon>
	<#if enableFlags == "true">
		<@liferay_ui["flags"]
			className=entry.getClassName()
			classPK=entry.getClassPK()
			contentTitle=entry.getTitle(locale)
			label=false
			reportedUserId=entry.getUserId()
		/>
	</#if>
</#macro>

<#macro getMetadataField
	fieldName
>
	<#if stringUtil.split(metadataFields)?seq_contains(fieldName)>
		<span class="metadata-entry metadata-"${fieldName}">
			<#assign dateFormat = "dd MMM yyyy - HH:mm:ss" />

			<#if fieldName == "author">
				<@liferay.language key="by" /> ${portalUtil.getUserName(assetRenderer.getUserId(), assetRenderer.getUserName())}
			<#elseif fieldName == "categories">
				<@liferay_ui["asset-categories-summary"]
					className=entry.getClassName()
					classPK=entry.getClassPK()
					portletURL=renderResponse.createRenderURL()
				/>
			<#elseif fieldName == "create-date">
				${dateUtil.getDate(entry.getCreateDate(), dateFormat, locale)}
			<#elseif fieldName == "expiration-date">
				${dateUtil.getDate(entry.getExpirationDate(), dateFormat, locale)}
			<#elseif fieldName == "modified-date">
				${dateUtil.getDate(entry.getModifiedDate(), dateFormat, locale)}
			<#elseif fieldName == "priority">
				${entry.getPriority()}
			<#elseif fieldName == "publish-date">
				${dateUtil.getDate(entry.getPublishDate(), dateFormat, locale)}
			<#elseif fieldName == "tags">
				<@liferay_ui["asset-tags-summary"]
					className=entry.getClassName()
					classPK=entry.getClassPK()
					portletURL=renderResponse.createRenderURL()
				/>
			<#elseif fieldName == "view-count">
				<@liferay_ui["icon"]
					image="history"
				/>

				${entry.getViewCount()} <@liferay.language key="views" />
			</#if>
		</span>
	</#if>
</#macro>

<#macro getPrintIcon>
	<#if enablePrint == "true" >
		<#assign printURL = renderResponse.createRenderURL() />

		${printURL.setParameter("struts_action", "/asset_publisher/view_content")}
		${printURL.setParameter("assetEntryId", entry.getEntryId()?string)}
		${printURL.setParameter("viewMode", "print")}
		${printURL.setParameter("type", entry.getAssetRendererFactory().getType())}

		<#if (validator.isNotNull(assetRenderer.getUrlTitle()))>
			<#if (assetRenderer.getGroupId() != themeDisplay.getScopeGroupId())>
				${printURL.setParameter("groupId", assetRenderer.getGroupId()?string)}
			</#if>

			${printURL.setParameter("urlTitle", assetRenderer.getUrlTitle())}
		</#if>

		${printURL.setWindowState("pop_up")}

		<@liferay_ui["icon"]
			image="print"
			message="print"
			url="javascript:Liferay.Util.openWindow({id:''" + renderResponse.getNamespace() + "printAsset'', title: ''" + languageUtil.format(locale, "print-x-x", ["hide-accessible", entryTitle]) + "'', uri: ''" + htmlUtil.escapeURL(printURL.toString()) + "''});"
		/>
	</#if>
</#macro>

<#macro getRatings>
	<#if (enableRatings == "true")>
		<div class="asset-ratings">
			<@liferay_ui["ratings"]
				className=entry.getClassName()
				classPK=entry.getClassPK()
			/>
		</div>
	</#if>
</#macro>

<#macro getRelatedAssets>
	<#if enableRelatedAssets == "true">
		<@liferay_ui["asset-links"]
			assetEntryId=entry.getEntryId()
		/>
	</#if>
</#macro>

<#macro getSocialBookmarks>
	<#if enableSocialBookmarks == "true">
		<@liferay_ui["social-bookmarks"]
			displayStyle="${socialBookmarksDisplayStyle}"
			target="_blank"
			title=entry.getTitle(locale)
			url=viewURL
		/>
	</#if>
</#macro>', 0, 0, 10424, N'')
INSERT [dbo].[DDMTemplate] ([uuid_], [templateId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [classNameId], [classPK], [templateKey], [name], [description], [type_], [mode_], [language], [script], [cacheable], [smallImage], [smallImageId], [smallImageURL]) VALUES (N'fb1da4b2-8dd4-4bea-80b3-223a73edc2a1', 10425, 10197, 10157, 10161, N'', CAST(N'2017-05-12 00:54:38.880' AS DateTime), CAST(N'2017-05-12 00:54:38.880' AS DateTime), 10155, 0, N'DOCUMENTLIBRARY-CAROUSEL-FTL', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Name language-id="en_US">Carousel</Name></root>', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Displays images in a carousel.</Description></root>', N'display', N'', N'ftl', N'<#assign aui = taglibLiferayHash["/WEB-INF/tld/aui.tld"] />
<#assign liferay_portlet = taglibLiferayHash["/WEB-INF/tld/liferay-portlet.tld"] />

<#if entries?has_content>
	<style>
		#<@liferay_portlet.namespace />carousel .carousel-item {
			background-color: #000;
			height: 250px;
			overflow: hidden;
			text-align: center;
			width: 700px;
		}

		#<@liferay_portlet.namespace />carousel .carousel-item img {
			max-height: 250px;
			max-width: 700px;
		}
	</style>

	<div id="<@liferay_portlet.namespace />carousel">
		<#assign imageMimeTypes = propsUtil.getArray("dl.file.entry.preview.image.mime.types") />

		<#list entries as entry>
			<#if imageMimeTypes?seq_contains(entry.getMimeType()) >
				<div class="carousel-item">
					<img src="${dlUtil.getPreviewURL(entry, entry.getFileVersion(), themeDisplay, "")}" />
				</div>
			</#if>
		</#list>
	</div>

	<@aui.script use="aui-carousel">
		new A.Carousel(
			{
				contentBox: ''#<@liferay_portlet.namespace />carousel'',
				height: 250,
				intervalTime: 2,
				width: 700
			}
		).render();
	</@aui.script>
</#if>', 0, 0, 10426, N'')
INSERT [dbo].[DDMTemplate] ([uuid_], [templateId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [classNameId], [classPK], [templateKey], [name], [description], [type_], [mode_], [language], [script], [cacheable], [smallImage], [smallImageId], [smallImageURL]) VALUES (N'd2223584-1704-4784-9264-65932c28f267', 10427, 10197, 10157, 10161, N'', CAST(N'2017-05-12 00:54:39.113' AS DateTime), CAST(N'2017-05-12 00:54:39.113' AS DateTime), 10081, 0, N'ASSET-CATEGORIES-NAVIGATION-MULTI-COLUMN-LAYOUT-FTL', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Name language-id="en_US">Multi Column Layout</Name></root>', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Displays a column for each vocabulary. Each column includes the name of a vocabulary with the vocabulary''s top level categories listed underneath.</Description></root>', N'display', N'', N'ftl', N'<#assign aui = taglibLiferayHash["/WEB-INF/tld/aui.tld"] />

<#if entries?has_content>
	<@aui.layout>
		<#list entries as entry>
			<@aui.column columnWidth=25>
				<div class="results-header">
					<h3>
						${entry.getName()}
					</h3>
				</div>

				<#assign categories = entry.getCategories()>

				<@displayCategories categories=categories />
			</@aui.column>
		</#list>
	</@aui.layout>
</#if>

<#macro displayCategories
	categories
>
	<#if categories?has_content>
		<ul class="categories">
			<#list categories as category>
				<li>
					<#assign categoryURL = renderResponse.createRenderURL()>

					${categoryURL.setParameter("resetCur", "true")}
					${categoryURL.setParameter("categoryId", category.getCategoryId()?string)}

					<a href="${categoryURL}">${category.getName()}</a>

					<#if serviceLocator??>
						<#assign assetCategoryService = serviceLocator.findService("com.stpl.portlet.asset.service.AssetCategoryService")>

						<#assign childCategories = assetCategoryService.getChildCategories(category.getCategoryId())>

						<@displayCategories categories=childCategories />
					</#if>
				</li>
			</#list>
		</ul>
	</#if>
</#macro>', 0, 0, 10428, N'')
INSERT [dbo].[DDMTemplate] ([uuid_], [templateId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [classNameId], [classPK], [templateKey], [name], [description], [type_], [mode_], [language], [script], [cacheable], [smallImage], [smallImageId], [smallImageURL]) VALUES (N'7f81bbaf-c8fa-45df-8678-fd355758d40f', 10429, 10197, 10157, 10161, N'', CAST(N'2017-05-12 00:54:39.153' AS DateTime), CAST(N'2017-05-12 00:54:39.153' AS DateTime), 10034, 0, N'SITE-MAP-MULTI-COLUMN-LAYOUT-FTL', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Name language-id="en_US">Multi Column Layout</Name></root>', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Displays a column for each top level page. Each column includes the name of a top level page with the page''s immediate children listed underneath.</Description></root>', N'display', N'', N'ftl', N'<#assign aui = taglibLiferayHash["/WEB-INF/tld/aui.tld"] />

<#if entries?has_content>
	<@aui.layout>
		<#list entries as entry>
		    <@aui.column columnWidth=25>
				<div class="results-header">
					<h3>
						<#assign layoutURL = portalUtil.getLayoutURL(entry, themeDisplay)>

						<a href="${layoutURL}">${entry.getName(locale)}</a>
					</h3>
				</div>

				<#assign pages = entry.getChildren()>

				<@displayPages pages = pages />
		    </@aui.column>
		</#list>
	</@aui.layout>
</#if>

<#macro displayPages
	pages
>
	<#if pages?has_content>
		<ul class="child-pages">
			<#list pages as page>
				<li>
					<#assign pageLayoutURL = portalUtil.getLayoutURL(page, themeDisplay)>

					<a href="${pageLayoutURL}">${page.getName(locale)}</a>

					<#assign childPages = page.getChildren()>

					<@displayPages pages = childPages />
				</li>
			</#list>
		</ul>
	</#if>
</#macro>', 0, 0, 10430, N'')
INSERT [dbo].[DDMTemplate] ([uuid_], [templateId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [classNameId], [classPK], [templateKey], [name], [description], [type_], [mode_], [language], [script], [cacheable], [smallImage], [smallImageId], [smallImageURL]) VALUES (N'0205dc9c-ddba-4fdc-a615-b6be73f69032', 10431, 10197, 10157, 10161, N'', CAST(N'2017-05-12 00:54:39.167' AS DateTime), CAST(N'2017-05-12 00:54:39.167' AS DateTime), 10016, 0, N'WIKI-SOCIAL-FTL', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Name language-id="en_US">Social</Name></root>', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Displays social bookmarks and ratings for wiki pages and their child pages.</Description></root>', N'display', N'', N'ftl', N'<#assign liferay_ui = taglibLiferayHash["/WEB-INF/tld/liferay-ui.tld"] />

<#assign wikiPageClassName = "com.stpl.portlet.wiki.model.WikiPage" />

<#assign assetRenderer = assetEntry.getAssetRenderer() />

<div class="taglib-header">
	<h1 class="header-title">${entry.getTitle()}</h1>
</div>

<div style="float: right;">
	<@getEditIcon />

	<@getPageDetailsIcon />

	<@getPrintIcon />
</div>

<div class="wiki-body">
	<div class="wiki-info">
		<span class="stats">${assetEntry.getViewCount()} <@liferay.language key="views" /></span> |

		<span class="date"><@liferay.language key="last-modified" /> ${dateUtil.getDate(entry.getModifiedDate(), "dd MMM yyyy - HH:mm:ss", locale)}</span>

		<span class="author"><@liferay.language key="by" /> ${htmlUtil.escape(portalUtil.getUserName(entry.getUserId(), entry.getUserName()))}</span>
	</div>

	<div class="wiki-content">
		<@liferay_ui["social-bookmarks"]
			displayStyle="normal"
			target="_blank"
			title=entry.getTitle()
			url=viewURL
		/>

		${formattedContent}
	</div>

	<div class="page-actions">
		<div class="article-actions">
			<@getAddChildPageIcon />

			<@getAttatchmentsIcon />
		</div>
	</div>

	 <br />

	<@getRatings cssClass="page-ratings" entry=entry />

	<@getRelatedAssets />
</div>

<div class="page-categorization">
	<div class="page-categories">
		<#assign viewCategorizedPagesURL = renderResponse.createRenderURL() />

		${viewCategorizedPagesURL.setParameter("struts_action", "/wiki/view_categorized_pages")}
		${viewCategorizedPagesURL.setParameter("nodeId", entry.getNodeId()?string)}

		<@liferay_ui["asset-categories-summary"]
			className=wikiPageClassName
			classPK=entry.getResourcePrimKey()
			portletURL=viewCategorizedPagesURL
		/>
	</div>

	<div class="page-tags">
		<#assign viewTaggedPagesURL = renderResponse.createRenderURL() />

		${viewTaggedPagesURL.setParameter("struts_action", "/wiki/view_tagged_pages")}
		${viewTaggedPagesURL.setParameter("nodeId", entry.getNodeId()?string)}

		<@liferay_ui["asset-tags-summary"]
			className=wikiPageClassName
			classPK=entry.getResourcePrimKey()
			portletURL=viewTaggedPagesURL
		/>
	</div>
</div>

<#assign childPages = entry.getChildPages() />

<#if (childPages?has_content)>
	<div class="child-pages">
		<h2><@liferay.language key="children-pages" /></h2>

		<table class="taglib-search-iterator">
			<tr class="portlet-section-header results-header">
				<th>
					<@liferay.language key="page" />
				</th>
				<th>
					<@liferay.language key="last-modified" />
				</th>
				<th>
					<@liferay.language key="ratings" />
				</th>
			</tr>

			<#list childPages as childPage>
				<tr class="results-row">
					<#assign viewPageURL = renderResponse.createRenderURL() />

					${viewPageURL.setParameter("struts_action", "/wiki/view")}

					<#assign childNode = childPage.getNode() />

					${viewPageURL.setParameter("nodeName", childNode.getName())}
					${viewPageURL.setParameter("title", childPage.getTitle())}

					<td>
						<a href="${viewPageURL}">${childPage.getTitle()}</a>
					</td>
					<td>
						<a href="${viewPageURL}">${dateUtil.getDate(childPage.getModifiedDate(),"dd MMM yyyy - HH:mm:ss", locale)} <@liferay.language key="by" /> ${htmlUtil.escape(portalUtil.getUserName(childPage.getUserId(), childPage.getUserName()))}</a>
					</td>
					<td>
						<@getRatings cssClass="child-ratings" entry=childPage />
					</td>
				</tr>
			</#list>
		</table>
	</div>
</#if>

<@getDiscussion />

<#macro getAddChildPageIcon>
	<#if assetRenderer.hasEditPermission(themeDisplay.getPermissionChecker())>
		<#assign addPageURL = renderResponse.createRenderURL() />

		${addPageURL.setParameter("struts_action", "/wiki/edit_page")}
		${addPageURL.setParameter("redirect", currentURL)}
		${addPageURL.setParameter("nodeId", entry.getNodeId()?string)}
		${addPageURL.setParameter("title", "")}
		${addPageURL.setParameter("editTitle", "1")}
		${addPageURL.setParameter("parentTitle", entry.getTitle())}

		<@liferay_ui["icon"]
			image="add_article"
			label=true
			message="add-child-page"
			url=addPageURL?string
		/>
	</#if>
</#macro>

<#macro getAttatchmentsIcon>
	<#assign viewPageAttachmentsURL = renderResponse.createRenderURL() />

	${viewPageAttachmentsURL.setParameter("struts_action", "/wiki/view_page_attachments") }

	<@liferay_ui["icon"]
		image="clip"
		label=true
		message=''${entry.getAttachmentsFileEntriesCount() + languageUtil.get(locale, "attachments")}''
		url=viewPageAttachmentsURL?string
	/>
</#macro>

<#macro getDiscussion>
	<#if validator.isNotNull(assetRenderer.getDiscussionPath()) && (enableComments == "true")>
		<br />

		<#assign discussionURL = renderResponse.createActionURL() />

		${discussionURL.setParameter("struts_action", "/wiki/" + assetRenderer.getDiscussionPath())}

		<@liferay_ui["discussion"]
			className=wikiPageClassName
			classPK=entry.getResourcePrimKey()
			formAction=discussionURL?string
			formName="fm2"
			ratingsEnabled=enableCommentRatings == "true"
			redirect=currentURL
			subject=assetRenderer.getTitle(locale)
			userId=assetRenderer.getUserId()
		/>
	</#if>
</#macro>

<#macro getEditIcon>
	<#if assetRenderer.hasEditPermission(themeDisplay.getPermissionChecker())>
		<#assign editPageURL = renderResponse.createRenderURL() />

		${editPageURL.setParameter("struts_action", "/wiki/edit_page")}
		${editPageURL.setParameter("redirect", currentURL)}
		${editPageURL.setParameter("nodeId", entry.getNodeId()?string)}
		${editPageURL.setParameter("title", entry.getTitle())}

		<@liferay_ui["icon"]
			image="edit"
			message=entry.getTitle()
			url=editPageURL?string
		/>
	</#if>
</#macro>

<#macro getPageDetailsIcon>
	<#assign viewPageDetailsURL = renderResponse.createRenderURL() />

	${viewPageDetailsURL.setParameter("struts_action", "/wiki/view_page_details")}
	${viewPageDetailsURL.setParameter("redirect", currentURL)}

	<@liferay_ui["icon"]
		image="history"
		message="details"
		url=viewPageDetailsURL?string
	/>
</#macro>

<#macro getPrintIcon>
	<#assign printURL = renderResponse.createRenderURL() />

	${printURL.setParameter("viewMode", "print")}
	${printURL.setWindowState("pop_up")}

	<#assign title = languageUtil.format(locale, "print-x-x", ["hide-accessible", htmlUtil.escape(assetRenderer.getTitle(locale))]) />
	<#assign taglibPrintURL = "javascript:Liferay.Util.openWindow({dialog: {width: 960}, id:''" + renderResponse.getNamespace() + "printAsset'', title: ''" + title + "'', uri: ''" + htmlUtil.escapeURL(printURL.toString()) + "''});" />

	<@liferay_ui["icon"]
		image="print"
		message="print"
		url=taglibPrintURL
	/>
</#macro>

<#macro getRatings
	cssClass
	entry
>
	<#if enablePageRatings == "true">
		<div class="${cssClass}">
			<@liferay_ui["ratings"]
				className=wikiPageClassName
				classPK=entry.getResourcePrimKey()
			/>
		</div>
	</#if>
</#macro>

<#macro getRelatedAssets>
	<#if assetEntry?? && (enableRelatedAssets == "true")>
		<@liferay_ui["asset-links"]
			assetEntryId=assetEntry.getEntryId()
		/>
	</#if>
</#macro>', 0, 0, 10432, N'')
INSERT [dbo].[DDMTemplate] ([uuid_], [templateId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [classNameId], [classPK], [templateKey], [name], [description], [type_], [mode_], [language], [script], [cacheable], [smallImage], [smallImageId], [smallImageURL]) VALUES (N'fb6a1a94-07c9-40b9-97e7-8b7e368e710b', 10433, 10197, 10157, 10161, N'', CAST(N'2017-05-12 00:54:39.190' AS DateTime), CAST(N'2017-05-12 00:54:39.190' AS DateTime), 10007, 0, N'BLOGS-BASIC-FTL', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Name language-id="en_US">Basic</Name></root>', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Displays titles, authors, and abstracts compactly for blog entries.</Description></root>', N'display', N'', N'ftl', N'<#assign liferay_ui = taglibLiferayHash["/WEB-INF/tld/liferay-ui.tld"] />

<#list entries as entry>
	<div class="entry">
		<#assign viewURL = renderResponse.createRenderURL() />

		${viewURL.setParameter("struts_action", "/blogs/view_entry")}
		${viewURL.setParameter("redirect", currentURL)}
		${viewURL.setParameter("urlTitle", entry.getUrlTitle())}

		<div class="entry-content">
			<div class="entry-title">
				<h2><a href="${viewURL}">${htmlUtil.escape(entry.getTitle())}</a></h2>
			</div>
		</div>

		<div class="entry-body">
			<div class="entry-author">
				<@liferay.language key="written-by" /> ${htmlUtil.escape(portalUtil.getUserName(entry.getUserId(), entry.getUserName()))}
			</div>

			<#assign summary = entry.getDescription() />

			<#if (validator.isNull(summary))>
				<#assign summary = entry.getContent() />
			</#if>

			${stringUtil.shorten(htmlUtil.stripHtml(summary), 100)}

			<a href="${viewURL}"><@liferay.language key="read-more" /> <span class="hide-accessible"><@liferay.language key="about"/>${htmlUtil.escape(entry.getTitle())}</span> &raquo;</a>
		</div>

		<div class="entry-footer">
			<span class="entry-date">
				${dateUtil.getDate(entry.getCreateDate(), "dd MMM yyyy - HH:mm:ss", locale)}
			</span>

			<#assign blogsEntryClassName = "com.stpl.portlet.blogs.model.BlogsEntry" />

			<#if (enableFlags == "true")>
				<@liferay_ui["flags"]
					className=blogsEntryClassName
					classPK=entry.getEntryId()
					contentTitle=entry.getTitle()
					reportedUserId=entry.getUserId()
				/>
			</#if>

			<span class="entry-categories">
				<@liferay_ui["asset-categories-summary"]
					className=blogsEntryClassName
					classPK=entry.getEntryId()
					portletURL=renderResponse.createRenderURL()
				/>
			</span>

			<span class="entry-tags">
				<@liferay_ui["asset-tags-summary"]
					className=blogsEntryClassName
					classPK=entry.getEntryId()
					portletURL=renderResponse.createRenderURL()
				/>
			</span>
		</div>
	</div>

	<div class="separator"><!-- --></div>
</#list>', 0, 0, 10434, N'')
