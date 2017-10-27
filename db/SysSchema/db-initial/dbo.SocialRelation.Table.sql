SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SocialRelation](
	[uuid_] [nvarchar](75) NULL,
	[relationId] [bigint] NOT NULL,
	[companyId] [bigint] NULL,
	[createDate] [bigint] NULL,
	[userId1] [bigint] NULL,
	[userId2] [bigint] NULL,
	[type_] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[relationId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
