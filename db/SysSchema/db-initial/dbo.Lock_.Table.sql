SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Lock_](
	[uuid_] [nvarchar](75) NULL,
	[lockId] [bigint] NOT NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[userName] [nvarchar](75) NULL,
	[createDate] [datetime] NULL,
	[className] [nvarchar](75) NULL,
	[key_] [nvarchar](200) NULL,
	[owner] [nvarchar](255) NULL,
	[inheritable] [bit] NULL,
	[expirationDate] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[lockId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
