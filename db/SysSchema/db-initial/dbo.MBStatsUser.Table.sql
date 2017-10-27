SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MBStatsUser](
	[statsUserId] [bigint] NOT NULL,
	[groupId] [bigint] NULL,
	[userId] [bigint] NULL,
	[messageCount] [int] NULL,
	[lastPostDate] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[statsUserId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
