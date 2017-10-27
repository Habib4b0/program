SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ShoppingItemPrice](
	[itemPriceId] [bigint] NOT NULL,
	[itemId] [bigint] NULL,
	[minQuantity] [int] NULL,
	[maxQuantity] [int] NULL,
	[price] [float] NULL,
	[discount] [float] NULL,
	[taxable] [bit] NULL,
	[shipping] [float] NULL,
	[useShippingFormula] [bit] NULL,
	[status] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[itemPriceId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
