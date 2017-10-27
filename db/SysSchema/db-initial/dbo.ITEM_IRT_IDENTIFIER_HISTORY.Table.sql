SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ITEM_IRT_IDENTIFIER_HISTORY](
	[ItemSystemId] [int] NOT NULL,
	[ItemUom] [int] NULL,
	[ItemPricingQualifierId] [int] NOT NULL,
	[ItemPrice] [float] NULL,
	[PricingCodeStatus] [int] NULL,
	[StartDate] [datetime] NULL,
	[EndDate] [datetime] NULL,
	[EntityCode] [int] NULL,
	[CreatedBy] [nvarchar](75) NULL,
	[CreatedDate] [datetime] NULL,
	[ModifiedBy] [nvarchar](75) NULL,
	[ModifiedDate] [datetime] NULL,
	[BatchId] [nvarchar](75) NULL,
PRIMARY KEY CLUSTERED 
(
	[ItemSystemId] ASC,
	[ItemPricingQualifierId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
