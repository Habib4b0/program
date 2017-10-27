SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UserTracker](
	[userTrackerId] [bigint] NOT NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[modifiedDate] [datetime] NULL,
	[sessionId] [nvarchar](200) NULL,
	[remoteAddr] [nvarchar](75) NULL,
	[remoteHost] [nvarchar](75) NULL,
	[userAgent] [nvarchar](200) NULL,
PRIMARY KEY CLUSTERED 
(
	[userTrackerId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
