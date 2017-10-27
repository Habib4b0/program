SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[JournalContentSearch](
	[contentSearchId] [bigint] NOT NULL,
	[groupId] [bigint] NULL,
	[companyId] [bigint] NULL,
	[privateLayout] [bit] NULL,
	[layoutId] [bigint] NULL,
	[portletId] [nvarchar](200) NULL,
	[articleId] [nvarchar](75) NULL,
PRIMARY KEY CLUSTERED 
(
	[contentSearchId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
