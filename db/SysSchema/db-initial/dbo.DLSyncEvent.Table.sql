SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DLSyncEvent](
	[syncEventId] [bigint] NOT NULL,
	[modifiedTime] [bigint] NULL,
	[event] [nvarchar](75) NULL,
	[type_] [nvarchar](75) NULL,
	[typePK] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[syncEventId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
