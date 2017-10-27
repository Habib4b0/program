SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PluginSetting](
	[pluginSettingId] [bigint] NOT NULL,
	[companyId] [bigint] NULL,
	[pluginId] [nvarchar](75) NULL,
	[pluginType] [nvarchar](75) NULL,
	[roles] [nvarchar](2000) NULL,
	[active_] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[pluginSettingId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
