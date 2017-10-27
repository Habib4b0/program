SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ShoppingOrderItem](
	[orderItemId] [bigint] NOT NULL,
	[orderId] [bigint] NULL,
	[itemId] [nvarchar](75) NULL,
	[sku] [nvarchar](75) NULL,
	[name] [nvarchar](200) NULL,
	[description] [nvarchar](2000) NULL,
	[properties] [nvarchar](2000) NULL,
	[price] [float] NULL,
	[quantity] [int] NULL,
	[shippedDate] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[orderItemId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
