SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UserIdMapper](
	[userIdMapperId] [bigint] NOT NULL,
	[userId] [bigint] NULL,
	[type_] [nvarchar](75) NULL,
	[description] [nvarchar](75) NULL,
	[externalUserId] [nvarchar](75) NULL,
PRIMARY KEY CLUSTERED 
(
	[userIdMapperId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
