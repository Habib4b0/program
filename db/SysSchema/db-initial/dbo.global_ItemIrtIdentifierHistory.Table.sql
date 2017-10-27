SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[global_ItemIrtIdentifierHistory](
	[ItemIrtIdentifierHistoryId] [int] IDENTITY(1,1) NOT NULL,
	[ItemSystemId] [int] NULL,
	[ItemIdentifier] [nvarchar](75) NULL,
	[IdentifierStatus] [int] NULL,
	[ItemIrtQualifierId] [int] NULL,
	[StartDate] [datetime] NULL,
	[EndDate] [datetime] NULL,
	[EntityCode] [int] NULL,
	[CreatedBy] [nvarchar](75) NULL,
	[CreatedDate] [datetime] NULL,
	[ModifiedBy] [nvarchar](75) NULL,
	[ModifiedDate] [datetime] NULL,
	[BatchId] [nvarchar](75) NULL,
	[LastModifiedDate] [datetime] NULL,
	[ActionFlag] [nvarchar](75) NULL,
PRIMARY KEY CLUSTERED 
(
	[ItemIrtIdentifierHistoryId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
