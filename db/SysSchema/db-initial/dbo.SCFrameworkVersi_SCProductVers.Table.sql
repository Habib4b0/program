SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SCFrameworkVersi_SCProductVers](
	[frameworkVersionId] [bigint] NOT NULL,
	[productVersionId] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[frameworkVersionId] ASC,
	[productVersionId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
