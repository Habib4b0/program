SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PasswordTracker](
	[passwordTrackerId] [bigint] NOT NULL,
	[userId] [bigint] NULL,
	[createDate] [datetime] NULL,
	[password_] [nvarchar](75) NULL,
PRIMARY KEY CLUSTERED 
(
	[passwordTrackerId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
