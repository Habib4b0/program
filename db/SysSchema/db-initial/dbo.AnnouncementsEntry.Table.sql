SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AnnouncementsEntry](
	[uuid_] [nvarchar](75) NULL,
	[entryId] [bigint] NOT NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[userName] [nvarchar](75) NULL,
	[createDate] [datetime] NULL,
	[modifiedDate] [datetime] NULL,
	[classNameId] [bigint] NULL,
	[classPK] [bigint] NULL,
	[title] [nvarchar](75) NULL,
	[content] [nvarchar](max) NULL,
	[url] [nvarchar](2000) NULL,
	[type_] [nvarchar](75) NULL,
	[displayDate] [datetime] NULL,
	[expirationDate] [datetime] NULL,
	[priority] [int] NULL,
	[alert] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[entryId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
