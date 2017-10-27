SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ShoppingItemField](
	[itemFieldId] [bigint] NOT NULL,
	[itemId] [bigint] NULL,
	[name] [nvarchar](75) NULL,
	[values_] [nvarchar](2000) NULL,
	[description] [nvarchar](2000) NULL,
PRIMARY KEY CLUSTERED 
(
	[itemFieldId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
