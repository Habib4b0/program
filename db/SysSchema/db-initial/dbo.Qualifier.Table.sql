SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Qualifier](
	[QualifierId] [int] IDENTITY(1,1) NOT NULL,
	[QualifierCode] [nvarchar](75) NULL,
	[QualifierName] [nvarchar](75) NULL,
	[ModuleName] [nvarchar](75) NULL,
	[ExpirationRequired] [nvarchar](75) NULL,
	[SpecificTpRequired] [nvarchar](75) NULL,
	[CreatedDate] [datetime] NULL,
	[ModifiedDate] [datetime] NULL,
	[CreatedBy] [nvarchar](75) NULL,
	[ModifiedBy] [nvarchar](75) NULL,
	[BatchId] [nvarchar](75) NULL,
PRIMARY KEY CLUSTERED 
(
	[QualifierId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
