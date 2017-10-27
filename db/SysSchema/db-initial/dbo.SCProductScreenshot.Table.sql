SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SCProductScreenshot](
	[productScreenshotId] [bigint] NOT NULL,
	[companyId] [bigint] NULL,
	[groupId] [bigint] NULL,
	[productEntryId] [bigint] NULL,
	[thumbnailId] [bigint] NULL,
	[fullImageId] [bigint] NULL,
	[priority] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[productScreenshotId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
