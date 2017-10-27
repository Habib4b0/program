SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ServiceComponent](
	[serviceComponentId] [bigint] NOT NULL,
	[buildNamespace] [nvarchar](75) NULL,
	[buildNumber] [bigint] NULL,
	[buildDate] [bigint] NULL,
	[data_] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[serviceComponentId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
INSERT [dbo].[ServiceComponent] ([serviceComponentId], [buildNamespace], [buildNumber], [buildDate], [data_]) VALUES (10649, N'gtn', 3, 1442335276994, N'<?xml version="1.0"?>

<data>
	<tables-sql><![CDATA[]]></tables-sql>
	<sequences-sql><![CDATA[]]></sequences-sql>
	<indexes-sql><![CDATA[]]></indexes-sql>
</data>')
INSERT [dbo].[ServiceComponent] ([serviceComponentId], [buildNamespace], [buildNumber], [buildDate], [data_]) VALUES (10701, N'gtnPartII', 1, 1454577753781, N'<?xml version="1.0"?>

<data>
	<tables-sql><![CDATA[]]></tables-sql>
	<sequences-sql><![CDATA[]]></sequences-sql>
	<indexes-sql><![CDATA[]]></indexes-sql>
</data>')
INSERT [dbo].[ServiceComponent] ([serviceComponentId], [buildNamespace], [buildNumber], [buildDate], [data_]) VALUES (10704, N'gtnPartI', 145, 1493198573059, N'<?xml version="1.0"?>

<data>
	<tables-sql><![CDATA[]]></tables-sql>
	<sequences-sql><![CDATA[]]></sequences-sql>
	<indexes-sql><![CDATA[]]></indexes-sql>
</data>')
INSERT [dbo].[ServiceComponent] ([serviceComponentId], [buildNamespace], [buildNumber], [buildDate], [data_]) VALUES (11703, N'gtnPartI', 147, 1499418224904, N'<?xml version="1.0"?>

<data>
	<tables-sql><![CDATA[]]></tables-sql>
	<sequences-sql><![CDATA[]]></sequences-sql>
	<indexes-sql><![CDATA[]]></indexes-sql>
</data>')
