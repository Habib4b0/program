SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AssetCategory](
	[uuid_] [nvarchar](75) NULL,
	[categoryId] [bigint] NOT NULL,
	[groupId] [bigint] NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[userName] [nvarchar](75) NULL,
	[createDate] [datetime] NULL,
	[modifiedDate] [datetime] NULL,
	[parentCategoryId] [bigint] NULL,
	[leftCategoryId] [bigint] NULL,
	[rightCategoryId] [bigint] NULL,
	[name] [nvarchar](75) NULL,
	[title] [nvarchar](2000) NULL,
	[description] [nvarchar](2000) NULL,
	[vocabularyId] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[categoryId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
