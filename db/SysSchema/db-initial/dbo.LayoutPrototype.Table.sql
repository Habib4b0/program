SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LayoutPrototype](
	[uuid_] [nvarchar](75) NULL,
	[layoutPrototypeId] [bigint] NOT NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[userName] [nvarchar](75) NULL,
	[createDate] [datetime] NULL,
	[modifiedDate] [datetime] NULL,
	[name] [nvarchar](2000) NULL,
	[description] [nvarchar](2000) NULL,
	[settings_] [nvarchar](2000) NULL,
	[active_] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[layoutPrototypeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[LayoutPrototype] ([uuid_], [layoutPrototypeId], [companyId], [userId], [userName], [createDate], [modifiedDate], [name], [description], [settings_], [active_]) VALUES (N'6594cff9-f655-4583-89e3-1d5e39b2cdea', 10319, 10157, 10161, N'', CAST(N'2017-05-12 00:54:33.970' AS DateTime), CAST(N'2017-05-12 00:54:33.970' AS DateTime), N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Name language-id="en_US">Blog</Name></root>', N'Create, edit, and view blogs from this page. Explore topics using tags, and connect with other members that blog.', N'', 1)
INSERT [dbo].[LayoutPrototype] ([uuid_], [layoutPrototypeId], [companyId], [userId], [userName], [createDate], [modifiedDate], [name], [description], [settings_], [active_]) VALUES (N'43e53527-f321-4ca7-9f1a-477fd37dd628', 10331, 10157, 10161, N'', CAST(N'2017-05-12 00:54:34.447' AS DateTime), CAST(N'2017-05-12 00:54:34.447' AS DateTime), N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Name language-id="en_US">Content Display Page</Name></root>', N'Create, edit, and explore web content with this page. Search available content, explore related content with tags, and browse content categories.', N'', 1)
INSERT [dbo].[LayoutPrototype] ([uuid_], [layoutPrototypeId], [companyId], [userId], [userName], [createDate], [modifiedDate], [name], [description], [settings_], [active_]) VALUES (N'9528264a-1119-4b8a-9413-5a5d6a1a7a5f', 10341, 10157, 10161, N'', CAST(N'2017-05-12 00:54:34.677' AS DateTime), CAST(N'2017-05-12 00:54:34.677' AS DateTime), N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Name language-id="en_US">Wiki</Name></root>', N'Collaborate with members through the wiki on this page. Discover related content through tags, and navigate quickly and easily with categories.', N'', 1)
