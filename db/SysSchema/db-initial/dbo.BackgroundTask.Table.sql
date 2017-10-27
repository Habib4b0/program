SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BackgroundTask](
	[backgroundTaskId] [bigint] NOT NULL,
	[groupId] [bigint] NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[userName] [nvarchar](75) NULL,
	[createDate] [datetime] NULL,
	[modifiedDate] [datetime] NULL,
	[name] [nvarchar](75) NULL,
	[servletContextNames] [nvarchar](255) NULL,
	[taskExecutorClassName] [nvarchar](200) NULL,
	[taskContext] [nvarchar](max) NULL,
	[completed] [bit] NULL,
	[completionDate] [datetime] NULL,
	[status] [int] NULL,
	[statusMessage] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[backgroundTaskId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
