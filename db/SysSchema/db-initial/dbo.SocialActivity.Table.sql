SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SocialActivity](
	[activityId] [bigint] NOT NULL,
	[groupId] [bigint] NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[createDate] [bigint] NULL,
	[activitySetId] [bigint] NULL,
	[mirrorActivityId] [bigint] NULL,
	[classNameId] [bigint] NULL,
	[classPK] [bigint] NULL,
	[parentClassNameId] [bigint] NULL,
	[parentClassPK] [bigint] NULL,
	[type_] [int] NULL,
	[extraData] [nvarchar](2000) NULL,
	[receiverUserId] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[activityId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
