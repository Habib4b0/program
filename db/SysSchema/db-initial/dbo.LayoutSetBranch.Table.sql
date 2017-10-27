SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LayoutSetBranch](
	[layoutSetBranchId] [bigint] NOT NULL,
	[groupId] [bigint] NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[userName] [nvarchar](75) NULL,
	[createDate] [datetime] NULL,
	[modifiedDate] [datetime] NULL,
	[privateLayout] [bit] NULL,
	[name] [nvarchar](75) NULL,
	[description] [nvarchar](2000) NULL,
	[master] [bit] NULL,
	[logo] [bit] NULL,
	[logoId] [bigint] NULL,
	[themeId] [nvarchar](75) NULL,
	[colorSchemeId] [nvarchar](75) NULL,
	[wapThemeId] [nvarchar](75) NULL,
	[wapColorSchemeId] [nvarchar](75) NULL,
	[css] [nvarchar](max) NULL,
	[settings_] [nvarchar](max) NULL,
	[layoutSetPrototypeUuid] [nvarchar](75) NULL,
	[layoutSetPrototypeLinkEnabled] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[layoutSetBranchId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
