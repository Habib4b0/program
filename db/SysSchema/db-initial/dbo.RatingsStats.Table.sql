SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RatingsStats](
	[statsId] [bigint] NOT NULL,
	[classNameId] [bigint] NULL,
	[classPK] [bigint] NULL,
	[totalEntries] [int] NULL,
	[totalScore] [float] NULL,
	[averageScore] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[statsId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
