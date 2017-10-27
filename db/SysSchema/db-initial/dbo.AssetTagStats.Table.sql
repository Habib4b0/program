SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AssetTagStats](
	[tagStatsId] [bigint] NOT NULL,
	[tagId] [bigint] NULL,
	[classNameId] [bigint] NULL,
	[assetCount] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[tagStatsId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
