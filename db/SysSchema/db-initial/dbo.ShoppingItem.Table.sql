SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ShoppingItem](
	[itemId] [bigint] NOT NULL,
	[groupId] [bigint] NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[userName] [nvarchar](75) NULL,
	[createDate] [datetime] NULL,
	[modifiedDate] [datetime] NULL,
	[categoryId] [bigint] NULL,
	[sku] [nvarchar](75) NULL,
	[name] [nvarchar](200) NULL,
	[description] [nvarchar](2000) NULL,
	[properties] [nvarchar](2000) NULL,
	[fields_] [bit] NULL,
	[fieldsQuantities] [nvarchar](2000) NULL,
	[minQuantity] [int] NULL,
	[maxQuantity] [int] NULL,
	[price] [float] NULL,
	[discount] [float] NULL,
	[taxable] [bit] NULL,
	[shipping] [float] NULL,
	[useShippingFormula] [bit] NULL,
	[requiresShipping] [bit] NULL,
	[stockQuantity] [int] NULL,
	[featured_] [bit] NULL,
	[sale_] [bit] NULL,
	[smallImage] [bit] NULL,
	[smallImageId] [bigint] NULL,
	[smallImageURL] [nvarchar](2000) NULL,
	[mediumImage] [bit] NULL,
	[mediumImageId] [bigint] NULL,
	[mediumImageURL] [nvarchar](2000) NULL,
	[largeImage] [bit] NULL,
	[largeImageId] [bigint] NULL,
	[largeImageURL] [nvarchar](2000) NULL,
PRIMARY KEY CLUSTERED 
(
	[itemId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
