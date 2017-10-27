SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PasswordPolicy](
	[uuid_] [nvarchar](75) NULL,
	[passwordPolicyId] [bigint] NOT NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[userName] [nvarchar](75) NULL,
	[createDate] [datetime] NULL,
	[modifiedDate] [datetime] NULL,
	[defaultPolicy] [bit] NULL,
	[name] [nvarchar](75) NULL,
	[description] [nvarchar](2000) NULL,
	[changeable] [bit] NULL,
	[changeRequired] [bit] NULL,
	[minAge] [bigint] NULL,
	[checkSyntax] [bit] NULL,
	[allowDictionaryWords] [bit] NULL,
	[minAlphanumeric] [int] NULL,
	[minLength] [int] NULL,
	[minLowerCase] [int] NULL,
	[minNumbers] [int] NULL,
	[minSymbols] [int] NULL,
	[minUpperCase] [int] NULL,
	[regex] [nvarchar](75) NULL,
	[history] [bit] NULL,
	[historyCount] [int] NULL,
	[expireable] [bit] NULL,
	[maxAge] [bigint] NULL,
	[warningTime] [bigint] NULL,
	[graceLimit] [int] NULL,
	[lockout] [bit] NULL,
	[maxFailure] [int] NULL,
	[lockoutDuration] [bigint] NULL,
	[requireUnlock] [bit] NULL,
	[resetFailureCount] [bigint] NULL,
	[resetTicketMaxAge] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[passwordPolicyId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[PasswordPolicy] ([uuid_], [passwordPolicyId], [companyId], [userId], [userName], [createDate], [modifiedDate], [defaultPolicy], [name], [description], [changeable], [changeRequired], [minAge], [checkSyntax], [allowDictionaryWords], [minAlphanumeric], [minLength], [minLowerCase], [minNumbers], [minSymbols], [minUpperCase], [regex], [history], [historyCount], [expireable], [maxAge], [warningTime], [graceLimit], [lockout], [maxFailure], [lockoutDuration], [requireUnlock], [resetFailureCount], [resetTicketMaxAge]) VALUES (N'35ed316c-94c6-48fa-8dda-78034c9026e4', 10200, 10157, 10161, N'', CAST(N'2017-05-12 00:54:31.183' AS DateTime), CAST(N'2017-05-12 00:54:31.183' AS DateTime), 1, N'Default Password Policy', N'Default Password Policy', 1, 1, 0, 0, 1, 0, 6, 0, 1, 0, 1, N'(?=.{4})(?:[a-zA-Z0-9]*)', 0, 6, 0, 8640000, 86400, 0, 0, 3, 0, 1, 600, 86400)
