SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Image](
	[imageId] [bigint] NOT NULL,
	[modifiedDate] [datetime] NULL,
	[type_] [nvarchar](75) NULL,
	[height] [int] NULL,
	[width] [int] NULL,
	[size_] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[imageId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[Image] ([imageId], [modifiedDate], [type_], [height], [width], [size_]) VALUES (10821, CAST(N'2017-05-17 05:06:53.497' AS DateTime), N'png', 32, 77, 2729)
