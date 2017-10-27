SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SocialActivitySetting](
	[activitySettingId] [bigint] NOT NULL,
	[groupId] [bigint] NULL,
	[companyId] [bigint] NULL,
	[classNameId] [bigint] NULL,
	[activityType] [int] NULL,
	[name] [nvarchar](75) NULL,
	[value] [nvarchar](1024) NULL,
PRIMARY KEY CLUSTERED 
(
	[activitySettingId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
