SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LayoutRevision](
	[layoutRevisionId] [bigint] NOT NULL,
	[groupId] [bigint] NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[userName] [nvarchar](75) NULL,
	[createDate] [datetime] NULL,
	[modifiedDate] [datetime] NULL,
	[layoutSetBranchId] [bigint] NULL,
	[layoutBranchId] [bigint] NULL,
	[parentLayoutRevisionId] [bigint] NULL,
	[head] [bit] NULL,
	[major] [bit] NULL,
	[plid] [bigint] NULL,
	[privateLayout] [bit] NULL,
	[name] [nvarchar](2000) NULL,
	[title] [nvarchar](2000) NULL,
	[description] [nvarchar](2000) NULL,
	[keywords] [nvarchar](2000) NULL,
	[robots] [nvarchar](2000) NULL,
	[typeSettings] [nvarchar](max) NULL,
	[iconImage] [bit] NULL,
	[iconImageId] [bigint] NULL,
	[themeId] [nvarchar](75) NULL,
	[colorSchemeId] [nvarchar](75) NULL,
	[wapThemeId] [nvarchar](75) NULL,
	[wapColorSchemeId] [nvarchar](75) NULL,
	[css] [nvarchar](max) NULL,
	[status] [int] NULL,
	[statusByUserId] [bigint] NULL,
	[statusByUserName] [nvarchar](75) NULL,
	[statusDate] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[layoutRevisionId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
