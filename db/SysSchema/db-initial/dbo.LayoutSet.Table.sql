SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LayoutSet](
	[layoutSetId] [bigint] NOT NULL,
	[groupId] [bigint] NULL,
	[companyId] [bigint] NULL,
	[createDate] [datetime] NULL,
	[modifiedDate] [datetime] NULL,
	[privateLayout] [bit] NULL,
	[logo] [bit] NULL,
	[logoId] [bigint] NULL,
	[themeId] [nvarchar](75) NULL,
	[colorSchemeId] [nvarchar](75) NULL,
	[wapThemeId] [nvarchar](75) NULL,
	[wapColorSchemeId] [nvarchar](75) NULL,
	[css] [nvarchar](max) NULL,
	[pageCount] [int] NULL,
	[settings_] [nvarchar](max) NULL,
	[layoutSetPrototypeUuid] [nvarchar](75) NULL,
	[layoutSetPrototypeLinkEnabled] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[layoutSetId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (10176, 10175, 10157, CAST(N'2017-05-12 00:54:29.210' AS DateTime), CAST(N'2017-05-12 00:54:29.613' AS DateTime), 1, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 1, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (10177, 10175, 10157, CAST(N'2017-05-12 00:54:29.230' AS DateTime), CAST(N'2017-05-12 00:54:29.230' AS DateTime), 0, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 0, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (10185, 10184, 10157, CAST(N'2017-05-12 00:54:30.490' AS DateTime), CAST(N'2017-05-12 00:54:30.490' AS DateTime), 1, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 0, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (10186, 10184, 10157, CAST(N'2017-05-12 00:54:30.500' AS DateTime), CAST(N'2017-05-17 06:10:13.993' AS DateTime), 0, 1, 10821, N'default_WAR_defaulttheme', N'01', N'mobile', N'01', N'', 69, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (10195, 10194, 10157, CAST(N'2017-05-12 00:54:30.777' AS DateTime), CAST(N'2017-05-12 00:54:30.777' AS DateTime), 1, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 0, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (10196, 10194, 10157, CAST(N'2017-05-12 00:54:30.777' AS DateTime), CAST(N'2017-05-12 00:54:30.777' AS DateTime), 0, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 0, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (10198, 10197, 10157, CAST(N'2017-05-12 00:54:31.117' AS DateTime), CAST(N'2017-05-12 00:54:31.117' AS DateTime), 1, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 0, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (10199, 10197, 10157, CAST(N'2017-05-12 00:54:31.117' AS DateTime), CAST(N'2017-05-12 00:54:31.117' AS DateTime), 0, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 0, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (10205, 10204, 10157, CAST(N'2017-05-12 00:54:31.493' AS DateTime), CAST(N'2017-05-17 04:49:13.207' AS DateTime), 1, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 1, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (10206, 10204, 10157, CAST(N'2017-05-12 00:54:31.507' AS DateTime), CAST(N'2017-05-17 04:49:13.967' AS DateTime), 0, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 1, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (10321, 10320, 10157, CAST(N'2017-05-12 00:54:34.010' AS DateTime), CAST(N'2017-05-12 00:54:34.083' AS DateTime), 1, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 1, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (10322, 10320, 10157, CAST(N'2017-05-12 00:54:34.013' AS DateTime), CAST(N'2017-05-12 00:54:34.013' AS DateTime), 0, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 0, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (10333, 10332, 10157, CAST(N'2017-05-12 00:54:34.457' AS DateTime), CAST(N'2017-05-12 00:54:34.487' AS DateTime), 1, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 1, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (10334, 10332, 10157, CAST(N'2017-05-12 00:54:34.457' AS DateTime), CAST(N'2017-05-12 00:54:34.457' AS DateTime), 0, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 0, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (10343, 10342, 10157, CAST(N'2017-05-12 00:54:34.683' AS DateTime), CAST(N'2017-05-12 00:54:34.717' AS DateTime), 1, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 1, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (10344, 10342, 10157, CAST(N'2017-05-12 00:54:34.687' AS DateTime), CAST(N'2017-05-12 00:54:34.687' AS DateTime), 0, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 0, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (10354, 10353, 10157, CAST(N'2017-05-12 00:54:34.870' AS DateTime), CAST(N'2017-05-12 00:54:37.163' AS DateTime), 1, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 2, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (10355, 10353, 10157, CAST(N'2017-05-12 00:54:34.873' AS DateTime), CAST(N'2017-05-12 00:54:34.873' AS DateTime), 0, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 0, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (10380, 10379, 10157, CAST(N'2017-05-12 00:54:37.187' AS DateTime), CAST(N'2017-05-12 00:54:38.500' AS DateTime), 1, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 3, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (10381, 10379, 10157, CAST(N'2017-05-12 00:54:37.187' AS DateTime), CAST(N'2017-05-12 00:54:37.187' AS DateTime), 0, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 0, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (10833, 10832, 10157, CAST(N'2017-05-17 05:21:09.197' AS DateTime), CAST(N'2017-05-17 05:31:53.127' AS DateTime), 1, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 1, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (10834, 10832, 10157, CAST(N'2017-05-17 05:21:09.200' AS DateTime), CAST(N'2017-05-17 05:31:53.203' AS DateTime), 0, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 1, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (11434, 11433, 10157, CAST(N'2017-05-17 07:01:03.577' AS DateTime), CAST(N'2017-05-17 07:01:40.210' AS DateTime), 1, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 1, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (11435, 11433, 10157, CAST(N'2017-05-17 07:01:03.577' AS DateTime), CAST(N'2017-05-17 07:01:40.400' AS DateTime), 0, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 1, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (11459, 11458, 10157, CAST(N'2017-05-18 03:44:18.937' AS DateTime), CAST(N'2017-05-18 03:44:18.937' AS DateTime), 1, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 0, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (11460, 11458, 10157, CAST(N'2017-05-18 03:44:18.940' AS DateTime), CAST(N'2017-05-18 03:44:18.940' AS DateTime), 0, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 0, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (11463, 11462, 10157, CAST(N'2017-05-18 03:45:52.727' AS DateTime), CAST(N'2017-05-18 03:45:52.727' AS DateTime), 1, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 0, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (11464, 11462, 10157, CAST(N'2017-05-18 03:45:52.727' AS DateTime), CAST(N'2017-05-18 03:45:52.727' AS DateTime), 0, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 0, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (11467, 11466, 10157, CAST(N'2017-05-18 03:46:08.513' AS DateTime), CAST(N'2017-05-18 03:46:08.513' AS DateTime), 1, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 0, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (11468, 11466, 10157, CAST(N'2017-05-18 03:46:08.513' AS DateTime), CAST(N'2017-05-18 03:46:08.513' AS DateTime), 0, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 0, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (11499, 11498, 10157, CAST(N'2017-05-18 03:51:11.477' AS DateTime), CAST(N'2017-05-18 03:51:11.477' AS DateTime), 1, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 0, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (11500, 11498, 10157, CAST(N'2017-05-18 03:51:11.477' AS DateTime), CAST(N'2017-05-18 03:51:11.477' AS DateTime), 0, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 0, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (11503, 11502, 10157, CAST(N'2017-05-18 03:51:29.387' AS DateTime), CAST(N'2017-05-18 03:51:29.387' AS DateTime), 1, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 0, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (11504, 11502, 10157, CAST(N'2017-05-18 03:51:29.390' AS DateTime), CAST(N'2017-05-18 03:51:29.390' AS DateTime), 0, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 0, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (11507, 11506, 10157, CAST(N'2017-05-18 03:51:41.847' AS DateTime), CAST(N'2017-05-18 03:51:41.847' AS DateTime), 1, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 0, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (11508, 11506, 10157, CAST(N'2017-05-18 03:51:41.847' AS DateTime), CAST(N'2017-05-18 03:51:41.847' AS DateTime), 0, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 0, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (11511, 11510, 10157, CAST(N'2017-05-18 03:52:01.933' AS DateTime), CAST(N'2017-05-18 03:52:01.933' AS DateTime), 1, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 0, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (11512, 11510, 10157, CAST(N'2017-05-18 03:52:01.933' AS DateTime), CAST(N'2017-05-18 03:52:01.933' AS DateTime), 0, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 0, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (11515, 11514, 10157, CAST(N'2017-05-18 03:52:22.827' AS DateTime), CAST(N'2017-05-18 03:52:22.827' AS DateTime), 1, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 0, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (11516, 11514, 10157, CAST(N'2017-05-18 03:52:22.830' AS DateTime), CAST(N'2017-05-18 03:52:22.830' AS DateTime), 0, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 0, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (11519, 11518, 10157, CAST(N'2017-05-18 03:52:35.663' AS DateTime), CAST(N'2017-05-18 03:52:35.663' AS DateTime), 1, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 0, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (11520, 11518, 10157, CAST(N'2017-05-18 03:52:35.663' AS DateTime), CAST(N'2017-05-18 03:52:35.663' AS DateTime), 0, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 0, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (11523, 11522, 10157, CAST(N'2017-05-18 03:52:47.377' AS DateTime), CAST(N'2017-05-18 03:52:47.377' AS DateTime), 1, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 0, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (11524, 11522, 10157, CAST(N'2017-05-18 03:52:47.377' AS DateTime), CAST(N'2017-05-18 03:52:47.377' AS DateTime), 0, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 0, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (11527, 11526, 10157, CAST(N'2017-05-18 03:52:59.757' AS DateTime), CAST(N'2017-05-18 03:52:59.757' AS DateTime), 1, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 0, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (11528, 11526, 10157, CAST(N'2017-05-18 03:52:59.757' AS DateTime), CAST(N'2017-05-18 03:52:59.757' AS DateTime), 0, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 0, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (11535, 11534, 10157, CAST(N'2017-05-18 03:53:26.867' AS DateTime), CAST(N'2017-05-18 03:53:26.867' AS DateTime), 1, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 0, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (11536, 11534, 10157, CAST(N'2017-05-18 03:53:26.867' AS DateTime), CAST(N'2017-05-18 03:53:26.867' AS DateTime), 0, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 0, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (11591, 11590, 10157, CAST(N'2017-05-18 04:25:40.293' AS DateTime), CAST(N'2017-05-18 04:25:40.293' AS DateTime), 1, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 0, N'', N'', 0)
INSERT [dbo].[LayoutSet] ([layoutSetId], [groupId], [companyId], [createDate], [modifiedDate], [privateLayout], [logo], [logoId], [themeId], [colorSchemeId], [wapThemeId], [wapColorSchemeId], [css], [pageCount], [settings_], [layoutSetPrototypeUuid], [layoutSetPrototypeLinkEnabled]) VALUES (11592, 11590, 10157, CAST(N'2017-05-18 04:25:40.293' AS DateTime), CAST(N'2017-05-18 04:25:40.293' AS DateTime), 0, 0, 0, N'classic', N'01', N'mobile', N'01', N'', 0, N'', N'', 0)
