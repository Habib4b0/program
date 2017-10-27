SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[JournalArticleResource](
	[uuid_] [nvarchar](75) NULL,
	[resourcePrimKey] [bigint] NOT NULL,
	[groupId] [bigint] NULL,
	[articleId] [nvarchar](75) NULL,
PRIMARY KEY CLUSTERED 
(
	[resourcePrimKey] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
