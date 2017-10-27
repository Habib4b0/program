SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AssetCategoryProperty](
	[categoryPropertyId] [bigint] NOT NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[userName] [nvarchar](75) NULL,
	[createDate] [datetime] NULL,
	[modifiedDate] [datetime] NULL,
	[categoryId] [bigint] NULL,
	[key_] [nvarchar](75) NULL,
	[value] [nvarchar](75) NULL,
PRIMARY KEY CLUSTERED 
(
	[categoryPropertyId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
