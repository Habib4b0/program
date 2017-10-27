SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account_](
	[accountId] [bigint] NOT NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[userName] [nvarchar](75) NULL,
	[createDate] [datetime] NULL,
	[modifiedDate] [datetime] NULL,
	[parentAccountId] [bigint] NULL,
	[name] [nvarchar](75) NULL,
	[legalName] [nvarchar](75) NULL,
	[legalId] [nvarchar](75) NULL,
	[legalType] [nvarchar](75) NULL,
	[sicCode] [nvarchar](75) NULL,
	[tickerSymbol] [nvarchar](75) NULL,
	[industry] [nvarchar](75) NULL,
	[type_] [nvarchar](75) NULL,
	[size_] [nvarchar](75) NULL,
PRIMARY KEY CLUSTERED 
(
	[accountId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[Account_] ([accountId], [companyId], [userId], [userName], [createDate], [modifiedDate], [parentAccountId], [name], [legalName], [legalId], [legalType], [sicCode], [tickerSymbol], [industry], [type_], [size_]) VALUES (10159, 10157, 0, N'', CAST(N'2017-05-12 00:54:27.903' AS DateTime), CAST(N'2017-05-26 06:22:21.647' AS DateTime), 0, N'Stpl', N'', N'', N'', N'', N'', N'', N'', N'')
