SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Contact_](
	[contactId] [bigint] NOT NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[userName] [nvarchar](75) NULL,
	[createDate] [datetime] NULL,
	[modifiedDate] [datetime] NULL,
	[classNameId] [bigint] NULL,
	[classPK] [bigint] NULL,
	[accountId] [bigint] NULL,
	[parentContactId] [bigint] NULL,
	[emailAddress] [nvarchar](75) NULL,
	[firstName] [nvarchar](75) NULL,
	[middleName] [nvarchar](75) NULL,
	[lastName] [nvarchar](75) NULL,
	[prefixId] [int] NULL,
	[suffixId] [int] NULL,
	[male] [bit] NULL,
	[birthday] [datetime] NULL,
	[smsSn] [nvarchar](75) NULL,
	[aimSn] [nvarchar](75) NULL,
	[facebookSn] [nvarchar](75) NULL,
	[icqSn] [nvarchar](75) NULL,
	[jabberSn] [nvarchar](75) NULL,
	[msnSn] [nvarchar](75) NULL,
	[mySpaceSn] [nvarchar](75) NULL,
	[skypeSn] [nvarchar](75) NULL,
	[twitterSn] [nvarchar](75) NULL,
	[ymSn] [nvarchar](75) NULL,
	[employeeStatusId] [nvarchar](75) NULL,
	[employeeNumber] [nvarchar](75) NULL,
	[jobTitle] [nvarchar](100) NULL,
	[jobClass] [nvarchar](75) NULL,
	[hoursOfOperation] [nvarchar](75) NULL,
PRIMARY KEY CLUSTERED 
(
	[contactId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[Contact_] ([contactId], [companyId], [userId], [userName], [createDate], [modifiedDate], [classNameId], [classPK], [accountId], [parentContactId], [emailAddress], [firstName], [middleName], [lastName], [prefixId], [suffixId], [male], [birthday], [smsSn], [aimSn], [facebookSn], [icqSn], [jabberSn], [msnSn], [mySpaceSn], [skypeSn], [twitterSn], [ymSn], [employeeStatusId], [employeeNumber], [jobTitle], [jobClass], [hoursOfOperation]) VALUES (10162, 10157, 10161, N'', CAST(N'2017-05-12 00:54:27.703' AS DateTime), CAST(N'2017-05-12 00:54:27.703' AS DateTime), 10005, 10161, 10159, 0, N'default@stpl.com', N'', N'', N'', 0, 0, 1, CAST(N'2017-05-12 00:54:27.703' AS DateTime), N'', N'', N'', N'', N'', N'', N'', N'', N'', N'', N'', N'', N'', N'', N'')
INSERT [dbo].[Contact_] ([contactId], [companyId], [userId], [userName], [createDate], [modifiedDate], [classNameId], [classPK], [accountId], [parentContactId], [emailAddress], [firstName], [middleName], [lastName], [prefixId], [suffixId], [male], [birthday], [smsSn], [aimSn], [facebookSn], [icqSn], [jabberSn], [msnSn], [mySpaceSn], [skypeSn], [twitterSn], [ymSn], [employeeStatusId], [employeeNumber], [jobTitle], [jobClass], [hoursOfOperation]) VALUES (10202, 10157, 10201, N'', CAST(N'2017-05-12 00:54:31.250' AS DateTime), CAST(N'2017-05-12 00:54:31.250' AS DateTime), 10005, 10201, 10159, 0, N'test@stpl.com', N'Test', N'', N'Test', 0, 0, 1, CAST(N'1970-01-01 00:00:00.000' AS DateTime), N'', N'', N'', N'', N'', N'', N'', N'', N'', N'', N'', N'', N'', N'', N'')
INSERT [dbo].[Contact_] ([contactId], [companyId], [userId], [userName], [createDate], [modifiedDate], [classNameId], [classPK], [accountId], [parentContactId], [emailAddress], [firstName], [middleName], [lastName], [prefixId], [suffixId], [male], [birthday], [smsSn], [aimSn], [facebookSn], [icqSn], [jabberSn], [msnSn], [mySpaceSn], [skypeSn], [twitterSn], [ymSn], [employeeStatusId], [employeeNumber], [jobTitle], [jobClass], [hoursOfOperation]) VALUES (10830, 10157, 10201, N'Test Test', CAST(N'2017-05-17 05:21:08.963' AS DateTime), CAST(N'2017-05-18 04:31:58.710' AS DateTime), 10005, 10829, 10159, 0, N'bpiadmin@cel.com', N'BPI', N'', N'ADMIN', 0, 0, 1, CAST(N'1970-01-01 00:00:00.000' AS DateTime), N'', N'', N'', N'', N'', N'', N'', N'', N'', N'', N'', N'', N'', N'', N'')
INSERT [dbo].[Contact_] ([contactId], [companyId], [userId], [userName], [createDate], [modifiedDate], [classNameId], [classPK], [accountId], [parentContactId], [emailAddress], [firstName], [middleName], [lastName], [prefixId], [suffixId], [male], [birthday], [smsSn], [aimSn], [facebookSn], [icqSn], [jabberSn], [msnSn], [mySpaceSn], [skypeSn], [twitterSn], [ymSn], [employeeStatusId], [employeeNumber], [jobTitle], [jobClass], [hoursOfOperation]) VALUES (11431, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 07:01:03.517' AS DateTime), CAST(N'2017-05-18 04:32:12.647' AS DateTime), 10005, 11430, 10159, 0, N'john.smith@cel.com', N'John', N'', N'Smith', 0, 0, 1, CAST(N'1970-01-01 00:00:00.000' AS DateTime), N'', N'', N'', N'', N'', N'', N'', N'', N'', N'', N'', N'', N'', N'', N'')
INSERT [dbo].[Contact_] ([contactId], [companyId], [userId], [userName], [createDate], [modifiedDate], [classNameId], [classPK], [accountId], [parentContactId], [emailAddress], [firstName], [middleName], [lastName], [prefixId], [suffixId], [male], [birthday], [smsSn], [aimSn], [facebookSn], [icqSn], [jabberSn], [msnSn], [mySpaceSn], [skypeSn], [twitterSn], [ymSn], [employeeStatusId], [employeeNumber], [jobTitle], [jobClass], [hoursOfOperation]) VALUES (11588, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-18 04:25:40.157' AS DateTime), CAST(N'2017-05-18 04:25:40.157' AS DateTime), 10005, 11587, 10159, 0, N'etluser@cel.com', N'ETL', N'', N'User', 0, 0, 1, CAST(N'1970-01-01 00:00:00.000' AS DateTime), N'', N'', N'', N'', N'', N'', N'', N'', N'', N'', N'', N'', N'', N'', N'')
