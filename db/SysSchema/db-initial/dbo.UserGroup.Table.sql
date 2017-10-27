SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UserGroup](
	[uuid_] [nvarchar](75) NULL,
	[userGroupId] [bigint] NOT NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[userName] [nvarchar](75) NULL,
	[createDate] [datetime] NULL,
	[modifiedDate] [datetime] NULL,
	[parentUserGroupId] [bigint] NULL,
	[name] [nvarchar](75) NULL,
	[groupType] [nvarchar](75) NULL,
	[description] [nvarchar](2000) NULL,
	[addedByLDAPImport] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[userGroupId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[UserGroup] ([uuid_], [userGroupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [parentUserGroupId], [name], [groupType], [description], [addedByLDAPImport]) VALUES (N'80f079ea-3b4c-4c7b-8417-4f0a15f5d4e9', 11457, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-18 03:44:18.703' AS DateTime), CAST(N'2017-05-18 03:45:30.790' AS DateTime), 0, N'Business', N'Business', N'', 0)
INSERT [dbo].[UserGroup] ([uuid_], [userGroupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [parentUserGroupId], [name], [groupType], [description], [addedByLDAPImport]) VALUES (N'8fa5f984-8978-4d8a-ab3e-6f6a8e600531', 11461, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-18 03:45:52.717' AS DateTime), CAST(N'2017-05-18 03:45:52.717' AS DateTime), 0, N'System', N'System', N'', 0)
INSERT [dbo].[UserGroup] ([uuid_], [userGroupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [parentUserGroupId], [name], [groupType], [description], [addedByLDAPImport]) VALUES (N'8cce3734-95d3-44ea-b0fb-d27f33d9c170', 11465, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-18 03:46:08.510' AS DateTime), CAST(N'2017-05-18 03:46:08.510' AS DateTime), 0, N'Domain', N'Domain', N'', 0)
INSERT [dbo].[UserGroup] ([uuid_], [userGroupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [parentUserGroupId], [name], [groupType], [description], [addedByLDAPImport]) VALUES (N'fd077239-7a90-4afc-a037-2b06c4c5b60b', 11497, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-18 03:51:11.473' AS DateTime), CAST(N'2017-05-18 05:51:24.760' AS DateTime), 0, N'Global Files Group', N'System', N'Global Files Group', 0)
INSERT [dbo].[UserGroup] ([uuid_], [userGroupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [parentUserGroupId], [name], [groupType], [description], [addedByLDAPImport]) VALUES (N'd6d03f5b-2659-4353-90a1-e5859dfc1c99', 11501, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-18 03:51:29.173' AS DateTime), CAST(N'2017-05-18 06:34:16.423' AS DateTime), 0, N'Contract Mgmt Files Group', N'System', N'Contract Mgmt Files Group', 0)
INSERT [dbo].[UserGroup] ([uuid_], [userGroupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [parentUserGroupId], [name], [groupType], [description], [addedByLDAPImport]) VALUES (N'fd5e72ae-f570-4670-bb5d-32628ac37dc5', 11505, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-18 03:51:41.840' AS DateTime), CAST(N'2017-05-18 06:07:37.423' AS DateTime), 0, N'Transaction Files Group', N'System', N'Transaction Files Group', 0)
INSERT [dbo].[UserGroup] ([uuid_], [userGroupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [parentUserGroupId], [name], [groupType], [description], [addedByLDAPImport]) VALUES (N'967d5da7-68be-4d05-a8ce-0091a7cdbfe2', 11509, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-18 03:52:01.930' AS DateTime), CAST(N'2017-05-18 03:52:01.930' AS DateTime), 0, N'AdminConsole Files Group', N'System', N'AdminConsole Files Group', 0)
INSERT [dbo].[UserGroup] ([uuid_], [userGroupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [parentUserGroupId], [name], [groupType], [description], [addedByLDAPImport]) VALUES (N'dbe6f777-8700-4771-8baf-c83b57ddc578', 11513, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-18 03:52:22.823' AS DateTime), CAST(N'2017-05-18 03:52:22.823' AS DateTime), 0, N'Forecast Files Group', N'System', N'Forecast Files Group', 0)
INSERT [dbo].[UserGroup] ([uuid_], [userGroupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [parentUserGroupId], [name], [groupType], [description], [addedByLDAPImport]) VALUES (N'f23a676d-6dbf-4ee3-b4f0-d415f6bbbe1a', 11517, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-18 03:52:35.657' AS DateTime), CAST(N'2017-05-18 06:22:36.417' AS DateTime), 0, N'Price Type Group', N'System', N'Price Type Group', 0)
INSERT [dbo].[UserGroup] ([uuid_], [userGroupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [parentUserGroupId], [name], [groupType], [description], [addedByLDAPImport]) VALUES (N'bd065a88-d79c-4bed-a6e0-11b337583dec', 11521, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-18 03:52:47.373' AS DateTime), CAST(N'2017-05-18 03:52:47.373' AS DateTime), 0, N'FutureEvents Files Group', N'System', N'FutureEvents Files Group', 0)
INSERT [dbo].[UserGroup] ([uuid_], [userGroupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [parentUserGroupId], [name], [groupType], [description], [addedByLDAPImport]) VALUES (N'44ff5325-d0ef-4dd7-acf3-1d504dbb1021', 11525, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-18 03:52:59.753' AS DateTime), CAST(N'2017-05-18 03:52:59.753' AS DateTime), 0, N'Security Files Group', N'System', N'Security Files Group', 0)
INSERT [dbo].[UserGroup] ([uuid_], [userGroupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [parentUserGroupId], [name], [groupType], [description], [addedByLDAPImport]) VALUES (N'051d510f-0e18-4aee-8ee1-621ddea24412', 11533, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-18 03:53:26.863' AS DateTime), CAST(N'2017-05-18 06:06:44.117' AS DateTime), 0, N'Consolidated Financial Forecast Group', N'System', N'Consolidated Financial Forecast Group', 0)
