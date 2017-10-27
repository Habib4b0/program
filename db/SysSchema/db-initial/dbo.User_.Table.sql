SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User_](
	[uuid_] [nvarchar](75) NULL,
	[userId] [bigint] NOT NULL,
	[companyId] [bigint] NULL,
	[createDate] [datetime] NULL,
	[modifiedDate] [datetime] NULL,
	[defaultUser] [bit] NULL,
	[contactId] [bigint] NULL,
	[password_] [nvarchar](75) NULL,
	[passwordEncrypted] [bit] NULL,
	[passwordReset] [bit] NULL,
	[passwordModifiedDate] [datetime] NULL,
	[digest] [nvarchar](255) NULL,
	[reminderQueryQuestion] [nvarchar](75) NULL,
	[reminderQueryAnswer] [nvarchar](75) NULL,
	[graceLoginCount] [int] NULL,
	[screenName] [nvarchar](75) NULL,
	[emailAddress] [nvarchar](75) NULL,
	[facebookId] [bigint] NULL,
	[ldapServerId] [bigint] NULL,
	[openId] [nvarchar](1024) NULL,
	[portraitId] [bigint] NULL,
	[languageId] [nvarchar](75) NULL,
	[timeZoneId] [nvarchar](75) NULL,
	[greeting] [nvarchar](255) NULL,
	[comments] [nvarchar](2000) NULL,
	[firstName] [nvarchar](75) NULL,
	[middleName] [nvarchar](75) NULL,
	[lastName] [nvarchar](75) NULL,
	[jobTitle] [nvarchar](100) NULL,
	[loginDate] [datetime] NULL,
	[loginIP] [nvarchar](75) NULL,
	[lastLoginDate] [datetime] NULL,
	[lastLoginIP] [nvarchar](75) NULL,
	[lastFailedLoginDate] [datetime] NULL,
	[failedLoginAttempts] [int] NULL,
	[lockout] [bit] NULL,
	[lockoutDate] [datetime] NULL,
	[agreedToTermsOfUse] [bit] NULL,
	[emailAddressVerified] [bit] NULL,
	[status] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[userId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[User_] ([uuid_], [userId], [companyId], [createDate], [modifiedDate], [defaultUser], [contactId], [password_], [passwordEncrypted], [passwordReset], [passwordModifiedDate], [digest], [reminderQueryQuestion], [reminderQueryAnswer], [graceLoginCount], [screenName], [emailAddress], [facebookId], [ldapServerId], [openId], [portraitId], [languageId], [timeZoneId], [greeting], [comments], [firstName], [middleName], [lastName], [jobTitle], [loginDate], [loginIP], [lastLoginDate], [lastLoginIP], [lastFailedLoginDate], [failedLoginAttempts], [lockout], [lockoutDate], [agreedToTermsOfUse], [emailAddressVerified], [status]) VALUES (N'5ee81cac-f3c7-4d3d-ba05-bb6b57f4e902', 10161, 10157, CAST(N'2017-05-12 00:54:27.703' AS DateTime), CAST(N'2017-05-12 00:54:27.703' AS DateTime), 1, 10162, N'password', 0, 0, NULL, N'ae5767db2cd7fb428336dbccbb170f20,851ce4a13a5b0a0025437f763ca13bcb,851ce4a13a5b0a0025437f763ca13bcb', N'', N'', 0, N'10161', N'default@stpl.com', 0, 0, N'', 0, N'en_US', N'UTC', N'Welcome!', N'', N'', N'', N'', N'', CAST(N'2017-05-12 00:54:27.703' AS DateTime), N'', NULL, N'', NULL, 0, 0, NULL, 1, 0, 0)
INSERT [dbo].[User_] ([uuid_], [userId], [companyId], [createDate], [modifiedDate], [defaultUser], [contactId], [password_], [passwordEncrypted], [passwordReset], [passwordModifiedDate], [digest], [reminderQueryQuestion], [reminderQueryAnswer], [graceLoginCount], [screenName], [emailAddress], [facebookId], [ldapServerId], [openId], [portraitId], [languageId], [timeZoneId], [greeting], [comments], [firstName], [middleName], [lastName], [jobTitle], [loginDate], [loginIP], [lastLoginDate], [lastLoginIP], [lastFailedLoginDate], [failedLoginAttempts], [lockout], [lockoutDate], [agreedToTermsOfUse], [emailAddressVerified], [status]) VALUES (N'262ba604-ee5f-46c6-bdc7-e4a41a2df870', 10201, 10157, CAST(N'2017-05-12 00:54:31.250' AS DateTime), CAST(N'2017-05-12 00:54:31.250' AS DateTime), 0, 10202, N'qUqP5cyxm6YcTAhz05Hph5gvu9M=', 1, 0, NULL, N'd81f6ecbada52479e67b5c5a3656a3c8,751da756639bc033b572ba2e7849b589,4986d8c7d643d1a3f3242e849771f4d0', N'test@stpl.com', N'test', 0, N'test', N'test@stpl.com', 0, -1, N'', 0, N'en_US', N'UTC', N'Welcome Test Test!', N'', N'Test', N'', N'Test', N'', CAST(N'2017-05-17 05:37:15.693' AS DateTime), N'10.4.75.134', CAST(N'2017-05-17 05:30:48.477' AS DateTime), N'10.4.75.134', NULL, 0, 0, NULL, 1, 1, 5)
INSERT [dbo].[User_] ([uuid_], [userId], [companyId], [createDate], [modifiedDate], [defaultUser], [contactId], [password_], [passwordEncrypted], [passwordReset], [passwordModifiedDate], [digest], [reminderQueryQuestion], [reminderQueryAnswer], [graceLoginCount], [screenName], [emailAddress], [facebookId], [ldapServerId], [openId], [portraitId], [languageId], [timeZoneId], [greeting], [comments], [firstName], [middleName], [lastName], [jobTitle], [loginDate], [loginIP], [lastLoginDate], [lastLoginIP], [lastFailedLoginDate], [failedLoginAttempts], [lockout], [lockoutDate], [agreedToTermsOfUse], [emailAddressVerified], [status]) VALUES (N'764b81c3-1f57-404c-adf9-840eea8ac8f0', 10829, 10157, CAST(N'2017-05-17 05:21:08.963' AS DateTime), CAST(N'2017-05-18 04:31:58.710' AS DateTime), 0, 10830, N'+o0R3EuWmEXTG4zJtf+rLZCnvUs=', 1, 0, CAST(N'2017-07-24 02:17:46.897' AS DateTime), N'26f1c06858032e99f3f67fd0183ed95f,96d9751fc2b4ca23ad4deec26db5ad48,4a674f57c00043165221dc571c5ad6ed', N'test@stpl.com', N'test', 0, N'bpiadmin', N'bpiadmin@cel.com', 0, -1, N'', 0, N'en_US', N'UTC', N'Welcome BPI ADMIN!', N'', N'BPI', N'', N'ADMIN', N'', CAST(N'2017-07-27 01:43:48.927' AS DateTime), N'10.4.69.118', CAST(N'2017-07-24 23:46:48.240' AS DateTime), N'10.4.69.118', NULL, 0, 0, NULL, 1, 0, 0)
INSERT [dbo].[User_] ([uuid_], [userId], [companyId], [createDate], [modifiedDate], [defaultUser], [contactId], [password_], [passwordEncrypted], [passwordReset], [passwordModifiedDate], [digest], [reminderQueryQuestion], [reminderQueryAnswer], [graceLoginCount], [screenName], [emailAddress], [facebookId], [ldapServerId], [openId], [portraitId], [languageId], [timeZoneId], [greeting], [comments], [firstName], [middleName], [lastName], [jobTitle], [loginDate], [loginIP], [lastLoginDate], [lastLoginIP], [lastFailedLoginDate], [failedLoginAttempts], [lockout], [lockoutDate], [agreedToTermsOfUse], [emailAddressVerified], [status]) VALUES (N'0d2d3a18-d48a-4f04-8076-b45c7f757705', 11430, 10157, CAST(N'2017-05-17 07:01:03.517' AS DateTime), CAST(N'2017-05-18 04:32:12.647' AS DateTime), 0, 11431, N'mcw20s4BV8GZiyKOfgp9nsWK3uE=', 1, 1, CAST(N'2017-05-17 07:01:57.523' AS DateTime), N'b9903f5f5fd101b19571ae89e0041b9e,bf0069753ad787255706f56a495826db,7444647e386c5ccfe851f85dd23d65b9', N'john.Smith', N'JohnS@123', 0, N'john.smith', N'john.smith@cel.com', 0, -1, N'', 0, N'en_US', N'UTC', N'Welcome John Smith!', N'', N'John', N'', N'Smith', N'', CAST(N'2017-05-18 08:21:02.790' AS DateTime), N'10.4.75.134', CAST(N'2017-05-18 07:23:54.927' AS DateTime), N'10.4.75.134', CAST(N'2017-07-24 05:48:17.617' AS DateTime), 5, 0, NULL, 1, 0, 0)
INSERT [dbo].[User_] ([uuid_], [userId], [companyId], [createDate], [modifiedDate], [defaultUser], [contactId], [password_], [passwordEncrypted], [passwordReset], [passwordModifiedDate], [digest], [reminderQueryQuestion], [reminderQueryAnswer], [graceLoginCount], [screenName], [emailAddress], [facebookId], [ldapServerId], [openId], [portraitId], [languageId], [timeZoneId], [greeting], [comments], [firstName], [middleName], [lastName], [jobTitle], [loginDate], [loginIP], [lastLoginDate], [lastLoginIP], [lastFailedLoginDate], [failedLoginAttempts], [lockout], [lockoutDate], [agreedToTermsOfUse], [emailAddressVerified], [status]) VALUES (N'992d6b85-fb6f-42bf-a2ac-a7950efff44e', 11587, 10157, CAST(N'2017-05-18 04:25:40.157' AS DateTime), CAST(N'2017-05-18 04:25:40.157' AS DateTime), 0, 11588, N'1kN5pw9EWK7s9eQtuMqW+hflz5s=', 1, 1, CAST(N'2017-05-18 04:25:40.303' AS DateTime), N'', N'', N'', 0, N'etluser', N'etluser@cel.com', 0, -1, N'', 0, N'en_US', N'UTC', N'Welcome ETL User!', N'', N'ETL', N'', N'User', N'', NULL, N'', NULL, N'', NULL, 0, 0, NULL, 0, 0, 0)
