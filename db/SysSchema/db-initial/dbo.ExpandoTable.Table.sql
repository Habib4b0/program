SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ExpandoTable](
	[tableId] [bigint] NOT NULL,
	[companyId] [bigint] NULL,
	[classNameId] [bigint] NULL,
	[name] [nvarchar](75) NULL,
PRIMARY KEY CLUSTERED 
(
	[tableId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[ExpandoTable] ([tableId], [companyId], [classNameId], [name]) VALUES (10840, 10157, 10002, N'CUSTOM_FIELDS')
