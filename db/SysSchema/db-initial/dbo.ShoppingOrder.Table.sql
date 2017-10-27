SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ShoppingOrder](
	[orderId] [bigint] NOT NULL,
	[groupId] [bigint] NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[userName] [nvarchar](75) NULL,
	[createDate] [datetime] NULL,
	[modifiedDate] [datetime] NULL,
	[number_] [nvarchar](75) NULL,
	[tax] [float] NULL,
	[shipping] [float] NULL,
	[altShipping] [nvarchar](75) NULL,
	[requiresShipping] [bit] NULL,
	[insure] [bit] NULL,
	[insurance] [float] NULL,
	[couponCodes] [nvarchar](75) NULL,
	[couponDiscount] [float] NULL,
	[billingFirstName] [nvarchar](75) NULL,
	[billingLastName] [nvarchar](75) NULL,
	[billingEmailAddress] [nvarchar](75) NULL,
	[billingCompany] [nvarchar](75) NULL,
	[billingStreet] [nvarchar](75) NULL,
	[billingCity] [nvarchar](75) NULL,
	[billingState] [nvarchar](75) NULL,
	[billingZip] [nvarchar](75) NULL,
	[billingCountry] [nvarchar](75) NULL,
	[billingPhone] [nvarchar](75) NULL,
	[shipToBilling] [bit] NULL,
	[shippingFirstName] [nvarchar](75) NULL,
	[shippingLastName] [nvarchar](75) NULL,
	[shippingEmailAddress] [nvarchar](75) NULL,
	[shippingCompany] [nvarchar](75) NULL,
	[shippingStreet] [nvarchar](75) NULL,
	[shippingCity] [nvarchar](75) NULL,
	[shippingState] [nvarchar](75) NULL,
	[shippingZip] [nvarchar](75) NULL,
	[shippingCountry] [nvarchar](75) NULL,
	[shippingPhone] [nvarchar](75) NULL,
	[ccName] [nvarchar](75) NULL,
	[ccType] [nvarchar](75) NULL,
	[ccNumber] [nvarchar](75) NULL,
	[ccExpMonth] [int] NULL,
	[ccExpYear] [int] NULL,
	[ccVerNumber] [nvarchar](75) NULL,
	[comments] [nvarchar](2000) NULL,
	[ppTxnId] [nvarchar](75) NULL,
	[ppPaymentStatus] [nvarchar](75) NULL,
	[ppPaymentGross] [float] NULL,
	[ppReceiverEmail] [nvarchar](75) NULL,
	[ppPayerEmail] [nvarchar](75) NULL,
	[sendOrderEmail] [bit] NULL,
	[sendShippingEmail] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[orderId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
