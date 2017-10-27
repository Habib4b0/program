SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ResourceBlock](
	[resourceBlockId] [bigint] NOT NULL,
	[companyId] [bigint] NULL,
	[groupId] [bigint] NULL,
	[name] [nvarchar](75) NULL,
	[permissionsHash] [nvarchar](75) NULL,
	[referenceCount] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[resourceBlockId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
