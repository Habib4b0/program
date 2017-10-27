SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[WikiPage](
	[uuid_] [nvarchar](75) NULL,
	[pageId] [bigint] NOT NULL,
	[resourcePrimKey] [bigint] NULL,
	[groupId] [bigint] NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[userName] [nvarchar](75) NULL,
	[createDate] [datetime] NULL,
	[modifiedDate] [datetime] NULL,
	[nodeId] [bigint] NULL,
	[title] [nvarchar](255) NULL,
	[version] [float] NULL,
	[minorEdit] [bit] NULL,
	[content] [nvarchar](max) NULL,
	[summary] [nvarchar](2000) NULL,
	[format] [nvarchar](75) NULL,
	[head] [bit] NULL,
	[parentTitle] [nvarchar](255) NULL,
	[redirectTitle] [nvarchar](255) NULL,
	[status] [int] NULL,
	[statusByUserId] [bigint] NULL,
	[statusByUserName] [nvarchar](75) NULL,
	[statusDate] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[pageId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
