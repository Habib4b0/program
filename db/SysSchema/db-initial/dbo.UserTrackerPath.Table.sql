SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UserTrackerPath](
	[userTrackerPathId] [bigint] NOT NULL,
	[userTrackerId] [bigint] NULL,
	[path_] [nvarchar](2000) NULL,
	[pathDate] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[userTrackerPathId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
