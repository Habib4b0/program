SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SocialActivityLimit](
	[activityLimitId] [bigint] NOT NULL,
	[groupId] [bigint] NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[classNameId] [bigint] NULL,
	[classPK] [bigint] NULL,
	[activityType] [int] NULL,
	[activityCounterName] [nvarchar](75) NULL,
	[value] [nvarchar](75) NULL,
PRIMARY KEY CLUSTERED 
(
	[activityLimitId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
