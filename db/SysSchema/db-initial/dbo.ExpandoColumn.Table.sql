SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ExpandoColumn](
	[columnId] [bigint] NOT NULL,
	[companyId] [bigint] NULL,
	[tableId] [bigint] NULL,
	[name] [nvarchar](75) NULL,
	[type_] [int] NULL,
	[defaultData] [nvarchar](2000) NULL,
	[typeSettings] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[columnId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
INSERT [dbo].[ExpandoColumn] ([columnId], [companyId], [tableId], [name], [type_], [defaultData], [typeSettings]) VALUES (10841, 10157, 10840, N'navItem-icon1', 15, N'', N'')
INSERT [dbo].[ExpandoColumn] ([columnId], [companyId], [tableId], [name], [type_], [defaultData], [typeSettings]) VALUES (10842, 10157, 10840, N'navItem-icon2', 15, N'', N'')
