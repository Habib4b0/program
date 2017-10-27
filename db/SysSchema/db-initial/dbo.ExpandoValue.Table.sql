SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ExpandoValue](
	[valueId] [bigint] NOT NULL,
	[companyId] [bigint] NULL,
	[tableId] [bigint] NULL,
	[columnId] [bigint] NULL,
	[rowId_] [bigint] NULL,
	[classNameId] [bigint] NULL,
	[classPK] [bigint] NULL,
	[data_] [nvarchar](2000) NULL,
PRIMARY KEY CLUSTERED 
(
	[valueId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
