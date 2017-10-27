SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[VirtualHost](
	[virtualHostId] [bigint] NOT NULL,
	[companyId] [bigint] NULL,
	[layoutSetId] [bigint] NULL,
	[hostname] [nvarchar](75) NULL,
PRIMARY KEY CLUSTERED 
(
	[virtualHostId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[VirtualHost] ([virtualHostId], [companyId], [layoutSetId], [hostname]) VALUES (10160, 10157, 0, N'localhost')
