SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PortalPreferences](
	[portalPreferencesId] [bigint] NOT NULL,
	[ownerId] [bigint] NULL,
	[ownerType] [int] NULL,
	[preferences] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[portalPreferencesId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
INSERT [dbo].[PortalPreferences] ([portalPreferencesId], [ownerId], [ownerType], [preferences]) VALUES (10154, 0, 1, N'<portlet-preferences />')
INSERT [dbo].[PortalPreferences] ([portalPreferencesId], [ownerId], [ownerType], [preferences]) VALUES (10163, 10157, 1, N'<portlet-preferences><preference><name>company.security.strangers.verify</name><value>false</value></preference><preference><name>admin.email.from.address</name><value>support@bpitechnologies.com</value></preference><preference><name>locales</name><value>ca_ES,zh_CN,en_US,fi_FI,fr_FR,de_DE,iw_IL,hu_HU,ja_JP,pt_BR,es_ES</value></preference><preference><name>ldap.server.ids</name><value></value></preference><preference><name>admin.email.password.reset.subject</name><value>[$PORTAL_URL$]: Reset Your Password</value></preference><preference><name>admin.email.user.added.subject</name><value>[$PORTAL_URL$]: Your New Account</value></preference><preference><name>admin.email.password.sent.body</name><value>Dear [$TO_NAME$],&lt;br /&gt;[$NEW_LINE$]&lt;br /&gt;[$NEW_LINE$]Your new password for [$PORTAL_URL$] is [$USER_PASSWORD$].&lt;br /&gt;[$NEW_LINE$]&lt;br /&gt;[$NEW_LINE$]The request for a new password was made from [$REMOTE_ADDRESS$] / [$REMOTE_HOST$] with the browser [$USER_AGENT$].&lt;br /&gt;[$NEW_LINE$]&lt;br /&gt;[$NEW_LINE$]Sincerely,&lt;br /&gt;[$NEW_LINE$][$FROM_NAME$]&lt;br /&gt;[$NEW_LINE$][$FROM_ADDRESS$]&lt;br /&gt;[$NEW_LINE$][$PORTAL_URL$]</value></preference><preference><name>admin.email.verification.subject</name><value>[$PORTAL_URL$]: Email Address Verification</value></preference><preference><name>admin.default.role.names</name><value>Power User[$NEW_LINE$]User</value></preference><preference><name>company.security.strangers.with.mx</name><value>false</value></preference><preference><name>company.security.auto.login</name><value>false</value></preference><preference><name>default.landing.page.path</name><value>/web/guest/home</value></preference><preference><name>company.security.site.logo</name><value>true</value></preference><preference><name>admin.email.password.sent.subject</name><value>[$PORTAL_URL$]: Your New Password</value></preference><preference><name>admin.email.password.reset.body</name><value>Dear [$TO_NAME$],&lt;br /&gt;[$NEW_LINE$]&lt;br /&gt;[$NEW_LINE$]You can reset your password for [$PORTAL_URL$] at [$PASSWORD_RESET_URL$].&lt;br /&gt;[$NEW_LINE$]&lt;br /&gt;[$NEW_LINE$]The request for a new password was made from [$REMOTE_ADDRESS$] / [$REMOTE_HOST$].&lt;br /&gt;[$NEW_LINE$]&lt;br /&gt;[$NEW_LINE$]Sincerely,&lt;br /&gt;[$NEW_LINE$][$FROM_NAME$]&lt;br /&gt;[$NEW_LINE$][$FROM_ADDRESS$]&lt;br /&gt;[$NEW_LINE$][$PORTAL_URL$]</value></preference><preference><name>admin.email.from.name</name><value>BPI Support</value></preference><preference><name>company.security.send.password.reset.link</name><value>false</value></preference><preference><name>company.security.send.password</name><value>false</value></preference><preference><name>default.logout.page.path</name><value>/web/guest/home?p_p_id=58&amp;p_p_lifecycle=0&amp;p_p_state=maximized&amp;p_p_mode=view&amp;saveLastPath=false&amp;_58_struts_action=%2Flogin%2Flogin</value></preference><preference><name>admin.email.verification.body</name><value>Dear [$TO_NAME$],&lt;br /&gt;[$NEW_LINE$]&lt;br /&gt;[$NEW_LINE$]Please verify your email address for [$PORTAL_URL$] at [$EMAIL_VERIFICATION_URL$].&lt;br /&gt;[$NEW_LINE$]&lt;br /&gt;[$NEW_LINE$]Your verification code is [$EMAIL_VERIFICATION_CODE$] Sincerely,&lt;br /&gt;[$NEW_LINE$][$FROM_NAME$]&lt;br /&gt;[$NEW_LINE$][$FROM_ADDRESS$]&lt;br /&gt;[$NEW_LINE$][$PORTAL_URL$]</value></preference><preference><name>admin.email.user.added.no.password.body</name><value>Dear [$TO_NAME$],&lt;br /&gt;[$NEW_LINE$]&lt;br /&gt;[$NEW_LINE$]Welcome! You recently created an account at [$PORTAL_URL$].&lt;br /&gt;[$NEW_LINE$]&lt;br /&gt;[$NEW_LINE$]Sincerely,&lt;br /&gt;[$NEW_LINE$][$FROM_NAME$]&lt;br /&gt;[$NEW_LINE$][$FROM_ADDRESS$]&lt;br /&gt;[$NEW_LINE$][$PORTAL_URL$]</value></preference><preference><name>admin.email.user.added.body</name><value>Dear [$TO_NAME$],&lt;br /&gt;[$NEW_LINE$]&lt;br /&gt;[$NEW_LINE$]Welcome! You recently created an account at [$PORTAL_URL$]. Your password is [$USER_PASSWORD$]. Enjoy!&lt;br /&gt;[$NEW_LINE$]&lt;br /&gt;[$NEW_LINE$]Sincerely,&lt;br /&gt;[$NEW_LINE$][$FROM_NAME$]&lt;br /&gt;[$NEW_LINE$][$FROM_ADDRESS$]&lt;br /&gt;[$NEW_LINE$][$PORTAL_URL$]</value></preference><preference><name>company.security.strangers</name><value>false</value></preference><preference><name>company.security.auth.type</name><value>screenName</value></preference></portlet-preferences>')
INSERT [dbo].[PortalPreferences] ([portalPreferencesId], [ownerId], [ownerType], [preferences]) VALUES (10706, 10161, 4, N'<portlet-preferences />')
INSERT [dbo].[PortalPreferences] ([portalPreferencesId], [ownerId], [ownerType], [preferences]) VALUES (10814, 10201, 4, N'<portlet-preferences><preference><name>128#roles-order-by-col</name><value>title</value></preference><preference><name>128#roles-order-by-type</name><value>asc</value></preference></portlet-preferences>')
INSERT [dbo].[PortalPreferences] ([portalPreferencesId], [ownerId], [ownerType], [preferences]) VALUES (10855, 10829, 4, N'<portlet-preferences><preference><name>com.stpl.portal.util.SessionClicks#_145_portletCategory18</name><value>open</value></preference><preference><name>com.stpl.portal.util.SessionTreeJSClicks#layoutsTree</name><value>61,3,19,</value></preference><preference><name>com.stpl.portal.util.SessionClicks#_145_portletCategory12</name><value>open</value></preference><preference><name>com.stpl.portal.util.SessionClicks#_145_portletCategory17</name><value>open</value></preference><preference><name>com.stpl.portal.util.SessionClicks#_145_portletCategory26</name><value>closed</value></preference><preference><name>125#users-order-by-col</name><value>last-name</value></preference><preference><name>com.stpl.portal.util.SessionClicks#_145_portletCategory16</name><value>open</value></preference><preference><name>128#roles-order-by-col</name><value>title</value></preference><preference><name>com.stpl.portal.util.SessionClicks#_145_portletCategory1</name><value>closed</value></preference><preference><name>com.stpl.portal.util.SessionClicks#panel-manage-site_administration.pages</name><value>closed</value></preference><preference><name>com.stpl.portal.util.SessionClicks#_145_portletCategory20</name><value>open</value></preference><preference><name>com.stpl.portal.util.SessionClicks#layoutsTreeRootNode</name><value>1</value></preference><preference><name>com.stpl.portal.util.SessionClicks#_145_portletCategory14</name><value>open</value></preference><preference><name>128#roles-order-by-type</name><value>asc</value></preference><preference><name>com.stpl.portal.util.SessionClicks#panel-manage-site_administration.users</name><value>closed</value></preference><preference><name>com.stpl.portal.util.SessionClicks#_145_portletCategory3</name><value>closed</value></preference><preference><name>com.stpl.portal.util.SessionClicks#_145_portletCategory11</name><value>closed</value></preference><preference><name>com.stpl.portal.util.SessionClicks#_145_portletCategory15</name><value>open</value></preference><preference><name>com.stpl.portal.util.SessionClicks#_145_portletCategory13</name><value>closed</value></preference><preference><name>125#users-order-by-type</name><value>asc</value></preference><preference><name>com.stpl.portal.util.SessionClicks#_145_portletCategory7</name><value>open</value></preference><preference><name>com.stpl.portal.util.SessionClicks#_145_portletCategory2</name><value>closed</value></preference><preference><name>com.stpl.portal.util.SessionClicks#_145_portletCategory4</name><value>open</value></preference><preference><name>125#user-groups-order-by-col</name><value>name</value></preference><preference><name>125#user-groups-order-by-type</name><value>asc</value></preference><preference><name>com.stpl.portal.util.SessionClicks#_145_portletCategory5</name><value>closed</value></preference></portlet-preferences>')
INSERT [dbo].[PortalPreferences] ([portalPreferencesId], [ownerId], [ownerType], [preferences]) VALUES (11452, 11430, 4, N'<portlet-preferences />')
