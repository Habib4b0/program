SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ShoppingCoupon](
	[couponId] [bigint] NOT NULL,
	[groupId] [bigint] NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[userName] [nvarchar](75) NULL,
	[createDate] [datetime] NULL,
	[modifiedDate] [datetime] NULL,
	[code_] [nvarchar](75) NULL,
	[name] [nvarchar](75) NULL,
	[description] [nvarchar](2000) NULL,
	[startDate] [datetime] NULL,
	[endDate] [datetime] NULL,
	[active_] [bit] NULL,
	[limitCategories] [nvarchar](2000) NULL,
	[limitSkus] [nvarchar](2000) NULL,
	[minOrder] [float] NULL,
	[discount] [float] NULL,
	[discountType] [nvarchar](75) NULL,
PRIMARY KEY CLUSTERED 
(
	[couponId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
