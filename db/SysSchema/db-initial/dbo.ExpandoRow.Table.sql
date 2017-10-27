SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ExpandoRow](
	[rowId_] [bigint] NOT NULL,
	[companyId] [bigint] NULL,
	[modifiedDate] [datetime] NULL,
	[tableId] [bigint] NULL,
	[classPK] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[rowId_] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
