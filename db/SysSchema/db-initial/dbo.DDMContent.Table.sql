SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DDMContent](
	[uuid_] [nvarchar](75) NULL,
	[contentId] [bigint] NOT NULL,
	[groupId] [bigint] NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[userName] [nvarchar](75) NULL,
	[createDate] [datetime] NULL,
	[modifiedDate] [datetime] NULL,
	[name] [nvarchar](2000) NULL,
	[description] [nvarchar](2000) NULL,
	[xml] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[contentId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
