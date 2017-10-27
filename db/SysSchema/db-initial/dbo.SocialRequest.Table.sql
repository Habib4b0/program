SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SocialRequest](
	[uuid_] [nvarchar](75) NULL,
	[requestId] [bigint] NOT NULL,
	[groupId] [bigint] NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[createDate] [bigint] NULL,
	[modifiedDate] [bigint] NULL,
	[classNameId] [bigint] NULL,
	[classPK] [bigint] NULL,
	[type_] [int] NULL,
	[extraData] [nvarchar](2000) NULL,
	[receiverUserId] [bigint] NULL,
	[status] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[requestId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
