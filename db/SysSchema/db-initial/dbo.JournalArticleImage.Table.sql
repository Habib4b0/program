SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[JournalArticleImage](
	[articleImageId] [bigint] NOT NULL,
	[groupId] [bigint] NULL,
	[articleId] [nvarchar](75) NULL,
	[version] [float] NULL,
	[elInstanceId] [nvarchar](75) NULL,
	[elName] [nvarchar](75) NULL,
	[languageId] [nvarchar](75) NULL,
	[tempImage] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[articleImageId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
