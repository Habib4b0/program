SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[global_ItemPricingQualifier](
	[ItemPricingQualifierId] [int] IDENTITY(1,1) NOT NULL,
	[ItemPricingCodeQualifier] [nvarchar](75) NULL,
	[ItemPricingCodeQualifierName] [nvarchar](75) NULL,
	[ExpirationRequired] [nvarchar](75) NULL,
	[SpecificTpRequired] [nvarchar](75) NULL,
	[CreatedBy] [nvarchar](75) NULL,
	[CreatedDate] [datetime] NULL,
	[ModifiedBy] [nvarchar](75) NULL,
	[ModifiedDate] [datetime] NULL,
	[BatchId] [nvarchar](75) NULL,
PRIMARY KEY CLUSTERED 
(
	[ItemPricingQualifierId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
