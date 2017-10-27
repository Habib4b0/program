SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LayoutSetPrototype](
	[uuid_] [nvarchar](75) NULL,
	[layoutSetPrototypeId] [bigint] NOT NULL,
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
	[layoutSetPrototypeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[LayoutSetPrototype] ([uuid_], [layoutSetPrototypeId], [companyId], [userId], [userName], [createDate], [modifiedDate], [name], [description], [settings_], [active_]) VALUES (N'5aa815fb-2859-4021-9e7f-402c6fc6e552', 10352, 10157, 10161, N'', CAST(N'2017-05-12 00:54:34.850' AS DateTime), CAST(N'2017-05-17 05:02:50.533' AS DateTime), N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Name language-id="en_US">Community Site</Name></root>', N'Site with Forums and Wiki', N'layoutsUpdateable=true
', 1)
INSERT [dbo].[LayoutSetPrototype] ([uuid_], [layoutSetPrototypeId], [companyId], [userId], [userName], [createDate], [modifiedDate], [name], [description], [settings_], [active_]) VALUES (N'81c88911-c60b-402c-bea9-41cfcbd0661c', 10378, 10157, 10161, N'', CAST(N'2017-05-12 00:54:37.180' AS DateTime), CAST(N'2017-05-17 05:02:50.533' AS DateTime), N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Name language-id="en_US">Intranet Site</Name></root>', N'Site with Documents and News', N'layoutsUpdateable=true
', 1)
