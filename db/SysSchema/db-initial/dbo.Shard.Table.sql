SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Shard](
	[shardId] [bigint] NOT NULL,
	[classNameId] [bigint] NULL,
	[classPK] [bigint] NULL,
	[name] [nvarchar](75) NULL,
PRIMARY KEY CLUSTERED 
(
	[shardId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[Shard] ([shardId], [classNameId], [classPK], [name]) VALUES (10158, 10025, 10157, N'default')
