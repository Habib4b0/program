SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BlogsEntry](
	[uuid_] [nvarchar](75) NULL,
	[entryId] [bigint] NOT NULL,
	[groupId] [bigint] NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[userName] [nvarchar](75) NULL,
	[createDate] [datetime] NULL,
	[modifiedDate] [datetime] NULL,
	[title] [nvarchar](150) NULL,
	[urlTitle] [nvarchar](150) NULL,
	[description] [nvarchar](2000) NULL,
	[content] [nvarchar](max) NULL,
	[displayDate] [datetime] NULL,
	[allowPingbacks] [bit] NULL,
	[allowTrackbacks] [bit] NULL,
	[trackbacks] [nvarchar](max) NULL,
	[smallImage] [bit] NULL,
	[smallImageId] [bigint] NULL,
	[smallImageURL] [nvarchar](2000) NULL,
	[status] [int] NULL,
	[statusByUserId] [bigint] NULL,
	[statusByUserName] [nvarchar](75) NULL,
	[statusDate] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[entryId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
