SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrgLabor](
	[orgLaborId] [bigint] NOT NULL,
	[organizationId] [bigint] NULL,
	[typeId] [int] NULL,
	[sunOpen] [int] NULL,
	[sunClose] [int] NULL,
	[monOpen] [int] NULL,
	[monClose] [int] NULL,
	[tueOpen] [int] NULL,
	[tueClose] [int] NULL,
	[wedOpen] [int] NULL,
	[wedClose] [int] NULL,
	[thuOpen] [int] NULL,
	[thuClose] [int] NULL,
	[friOpen] [int] NULL,
	[friClose] [int] NULL,
	[satOpen] [int] NULL,
	[satClose] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[orgLaborId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
