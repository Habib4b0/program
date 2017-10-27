SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role_](
	[uuid_] [nvarchar](75) NULL,
	[roleId] [bigint] NOT NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[userName] [nvarchar](75) NULL,
	[createDate] [datetime] NULL,
	[modifiedDate] [datetime] NULL,
	[classNameId] [bigint] NULL,
	[classPK] [bigint] NULL,
	[name] [nvarchar](75) NULL,
	[title] [nvarchar](2000) NULL,
	[description] [nvarchar](2000) NULL,
	[type_] [int] NULL,
	[subtype] [nvarchar](75) NULL,
PRIMARY KEY CLUSTERED 
(
	[roleId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[Role_] ([uuid_], [roleId], [companyId], [userId], [userName], [createDate], [modifiedDate], [classNameId], [classPK], [name], [title], [description], [type_], [subtype]) VALUES (N'50552b38-6aac-4c00-88da-6972fd22c282', 10164, 10157, 10161, N'', CAST(N'2017-05-12 00:54:28.190' AS DateTime), CAST(N'2017-05-12 00:54:28.190' AS DateTime), 10004, 10164, N'Administrator', N'', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Administrators are super users who can do anything.</Description></root>', 1, N'')
INSERT [dbo].[Role_] ([uuid_], [roleId], [companyId], [userId], [userName], [createDate], [modifiedDate], [classNameId], [classPK], [name], [title], [description], [type_], [subtype]) VALUES (N'0856b44b-a6b6-48e2-80dd-e9b7cb7aad29', 10165, 10157, 10161, N'', CAST(N'2017-05-12 00:54:28.343' AS DateTime), CAST(N'2017-05-12 00:54:28.343' AS DateTime), 10004, 10165, N'Guest', N'', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Unauthenticated users always have this role.</Description></root>', 1, N'')
INSERT [dbo].[Role_] ([uuid_], [roleId], [companyId], [userId], [userName], [createDate], [modifiedDate], [classNameId], [classPK], [name], [title], [description], [type_], [subtype]) VALUES (N'e9c7af57-3b22-4541-a963-4c4fad8ce577', 10166, 10157, 10161, N'', CAST(N'2017-05-12 00:54:28.367' AS DateTime), CAST(N'2017-05-12 00:54:28.367' AS DateTime), 10004, 10166, N'Owner', N'', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">This is an implied role with respect to the objects users create.</Description></root>', 1, N'')
INSERT [dbo].[Role_] ([uuid_], [roleId], [companyId], [userId], [userName], [createDate], [modifiedDate], [classNameId], [classPK], [name], [title], [description], [type_], [subtype]) VALUES (N'08a2a710-d666-43f2-ad73-2317277b4fcc', 10167, 10157, 10161, N'', CAST(N'2017-05-12 00:54:28.387' AS DateTime), CAST(N'2017-05-12 00:54:28.387' AS DateTime), 10004, 10167, N'Power User', N'', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Power Users have their own personal site.</Description></root>', 1, N'')
INSERT [dbo].[Role_] ([uuid_], [roleId], [companyId], [userId], [userName], [createDate], [modifiedDate], [classNameId], [classPK], [name], [title], [description], [type_], [subtype]) VALUES (N'62619b34-3aeb-4ba0-bf68-a905809f5283', 10168, 10157, 10161, N'', CAST(N'2017-05-12 00:54:28.413' AS DateTime), CAST(N'2017-05-12 00:54:28.413' AS DateTime), 10004, 10168, N'User', N'', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Authenticated users should be assigned this role.</Description></root>', 1, N'')
INSERT [dbo].[Role_] ([uuid_], [roleId], [companyId], [userId], [userName], [createDate], [modifiedDate], [classNameId], [classPK], [name], [title], [description], [type_], [subtype]) VALUES (N'59d75aea-bce1-497c-8d97-e40a65753c27', 10169, 10157, 10161, N'', CAST(N'2017-05-12 00:54:28.553' AS DateTime), CAST(N'2017-05-12 00:54:28.553' AS DateTime), 10004, 10169, N'Organization Administrator', N'', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Organization Administrators are super users of their organization but cannot make other users into Organization Administrators.</Description></root>', 3, N'')
INSERT [dbo].[Role_] ([uuid_], [roleId], [companyId], [userId], [userName], [createDate], [modifiedDate], [classNameId], [classPK], [name], [title], [description], [type_], [subtype]) VALUES (N'f4fab0a5-c91e-4c40-b9c8-cfb1ad8cba6a', 10170, 10157, 10161, N'', CAST(N'2017-05-12 00:54:28.573' AS DateTime), CAST(N'2017-05-12 00:54:28.573' AS DateTime), 10004, 10170, N'Organization Owner', N'', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Organization Owners are super users of their organization and can assign organization roles to users.</Description></root>', 3, N'')
INSERT [dbo].[Role_] ([uuid_], [roleId], [companyId], [userId], [userName], [createDate], [modifiedDate], [classNameId], [classPK], [name], [title], [description], [type_], [subtype]) VALUES (N'2f9c63f9-6d9c-484a-9482-8a0b33edcb64', 10171, 10157, 10161, N'', CAST(N'2017-05-12 00:54:28.590' AS DateTime), CAST(N'2017-05-12 00:54:28.590' AS DateTime), 10004, 10171, N'Organization User', N'', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">All users who belong to an organization have this role within that organization.</Description></root>', 3, N'')
INSERT [dbo].[Role_] ([uuid_], [roleId], [companyId], [userId], [userName], [createDate], [modifiedDate], [classNameId], [classPK], [name], [title], [description], [type_], [subtype]) VALUES (N'03c17175-5ed1-4bb8-b34f-684cca12aa6f', 10172, 10157, 10161, N'', CAST(N'2017-05-12 00:54:28.607' AS DateTime), CAST(N'2017-05-12 00:54:28.607' AS DateTime), 10004, 10172, N'Site Administrator', N'', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Site Administrators are super users of their site but cannot make other users into Site Administrators.</Description></root>', 2, N'')
INSERT [dbo].[Role_] ([uuid_], [roleId], [companyId], [userId], [userName], [createDate], [modifiedDate], [classNameId], [classPK], [name], [title], [description], [type_], [subtype]) VALUES (N'5c6bcd77-ba7a-4f11-ae28-e8935b15800d', 10173, 10157, 10161, N'', CAST(N'2017-05-12 00:54:28.627' AS DateTime), CAST(N'2017-05-12 00:54:28.627' AS DateTime), 10004, 10173, N'Site Member', N'', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">All users who belong to a site have this role within that site.</Description></root>', 2, N'')
INSERT [dbo].[Role_] ([uuid_], [roleId], [companyId], [userId], [userName], [createDate], [modifiedDate], [classNameId], [classPK], [name], [title], [description], [type_], [subtype]) VALUES (N'4eee533e-db20-4bcc-b399-018c08032952', 10174, 10157, 10161, N'', CAST(N'2017-05-12 00:54:28.643' AS DateTime), CAST(N'2017-05-12 00:54:28.643' AS DateTime), 10004, 10174, N'Site Owner', N'', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Site Owners are super users of their site and can assign site roles to users.</Description></root>', 2, N'')
INSERT [dbo].[Role_] ([uuid_], [roleId], [companyId], [userId], [userName], [createDate], [modifiedDate], [classNameId], [classPK], [name], [title], [description], [type_], [subtype]) VALUES (N'cd23aab3-65ad-49a1-ac89-002acc7b7f69', 11456, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-18 02:08:12.310' AS DateTime), CAST(N'2017-05-18 02:08:12.310' AS DateTime), 10004, 11456, N'analyst', N'', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">BPM Analyst</Description></root>', 1, N'')
INSERT [dbo].[Role_] ([uuid_], [roleId], [companyId], [userId], [userName], [createDate], [modifiedDate], [classNameId], [classPK], [name], [title], [description], [type_], [subtype]) VALUES (N'4a4c86e5-f376-448e-a138-bcca8c9efa58', 11561, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-18 03:58:43.293' AS DateTime), CAST(N'2017-05-18 03:58:43.293' AS DateTime), 10004, 11561, N'Accounting user', N'', N'', 1, N'')
INSERT [dbo].[Role_] ([uuid_], [roleId], [companyId], [userId], [userName], [createDate], [modifiedDate], [classNameId], [classPK], [name], [title], [description], [type_], [subtype]) VALUES (N'4b470c39-fe75-42f3-abd1-7051d0d2b8c2', 11563, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-18 03:59:18.940' AS DateTime), CAST(N'2017-05-18 03:59:18.940' AS DateTime), 10004, 11563, N'admin', N'', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">BPM admin</Description></root>', 1, N'')
INSERT [dbo].[Role_] ([uuid_], [roleId], [companyId], [userId], [userName], [createDate], [modifiedDate], [classNameId], [classPK], [name], [title], [description], [type_], [subtype]) VALUES (N'4ff4fd29-e658-423b-8371-d96346f3f341', 11565, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-18 03:59:49.597' AS DateTime), CAST(N'2017-05-18 06:08:09.973' AS DateTime), 10004, 11565, N'Admin Console Role', N'', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Admin Console Role</Description></root>', 1, N'')
INSERT [dbo].[Role_] ([uuid_], [roleId], [companyId], [userId], [userName], [createDate], [modifiedDate], [classNameId], [classPK], [name], [title], [description], [type_], [subtype]) VALUES (N'0be112d0-c72c-4a1b-9a0e-d355b0c2126e', 11566, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-18 04:00:14.823' AS DateTime), CAST(N'2017-05-18 04:00:14.823' AS DateTime), 10004, 11566, N'Approver 1', N'', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Approver 1</Description></root>', 1, N'')
INSERT [dbo].[Role_] ([uuid_], [roleId], [companyId], [userId], [userName], [createDate], [modifiedDate], [classNameId], [classPK], [name], [title], [description], [type_], [subtype]) VALUES (N'4689f653-2eac-4910-98aa-674889497f18', 11567, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-18 04:00:25.757' AS DateTime), CAST(N'2017-05-18 04:00:25.757' AS DateTime), 10004, 11567, N'Approver 2', N'', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Approver 2</Description></root>', 1, N'')
INSERT [dbo].[Role_] ([uuid_], [roleId], [companyId], [userId], [userName], [createDate], [modifiedDate], [classNameId], [classPK], [name], [title], [description], [type_], [subtype]) VALUES (N'ba584730-3174-4ae7-884e-edb0b13d46bb', 11568, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-18 04:01:30.177' AS DateTime), CAST(N'2017-05-18 04:01:30.177' AS DateTime), 10004, 11568, N'Approver 3', N'', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Approver 3</Description></root>', 1, N'')
INSERT [dbo].[Role_] ([uuid_], [roleId], [companyId], [userId], [userName], [createDate], [modifiedDate], [classNameId], [classPK], [name], [title], [description], [type_], [subtype]) VALUES (N'60f9de71-420b-41c8-a45b-1d4f876cafc5', 11569, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-18 04:01:38.200' AS DateTime), CAST(N'2017-05-18 04:01:38.200' AS DateTime), 10004, 11569, N'Approver 4', N'', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Approver 4</Description></root>', 1, N'')
INSERT [dbo].[Role_] ([uuid_], [roleId], [companyId], [userId], [userName], [createDate], [modifiedDate], [classNameId], [classPK], [name], [title], [description], [type_], [subtype]) VALUES (N'041dfa27-4210-4163-893b-c24de6847e51', 11573, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-18 04:02:30.733' AS DateTime), CAST(N'2017-05-18 06:08:50.507' AS DateTime), 10004, 11573, N'Consolidated Financial Role', N'', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Consolidated Financial Role</Description></root>', 1, N'')
INSERT [dbo].[Role_] ([uuid_], [roleId], [companyId], [userId], [userName], [createDate], [modifiedDate], [classNameId], [classPK], [name], [title], [description], [type_], [subtype]) VALUES (N'b9e97e83-ac1f-4de4-b891-1f3bdf798260', 11574, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-18 04:02:57.777' AS DateTime), CAST(N'2017-05-18 04:02:57.777' AS DateTime), 10004, 11574, N'Contract Analyst Heavy Director', N'', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Heavy Director Role</Description></root>', 1, N'')
INSERT [dbo].[Role_] ([uuid_], [roleId], [companyId], [userId], [userName], [createDate], [modifiedDate], [classNameId], [classPK], [name], [title], [description], [type_], [subtype]) VALUES (N'28f3222c-914e-4922-a3fd-de19a39156ba', 11575, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-18 04:03:11.247' AS DateTime), CAST(N'2017-05-18 04:03:11.247' AS DateTime), 10004, 11575, N'Contract Analyst Manager', N'', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Contract Analyst Manager</Description></root>', 1, N'')
INSERT [dbo].[Role_] ([uuid_], [roleId], [companyId], [userId], [userName], [createDate], [modifiedDate], [classNameId], [classPK], [name], [title], [description], [type_], [subtype]) VALUES (N'42809260-31a8-4390-b43c-35bbfb099e60', 11576, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-18 04:03:21.797' AS DateTime), CAST(N'2017-05-18 06:10:56.643' AS DateTime), 10004, 11576, N'Contract Mgmt Role', N'', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Contract Mgmt Role</Description></root>', 1, N'')
INSERT [dbo].[Role_] ([uuid_], [roleId], [companyId], [userId], [userName], [createDate], [modifiedDate], [classNameId], [classPK], [name], [title], [description], [type_], [subtype]) VALUES (N'e47b662b-87a2-4425-8258-257da3f45884', 11578, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-18 04:03:53.260' AS DateTime), CAST(N'2017-05-18 06:09:04.597' AS DateTime), 10004, 11578, N'Forecasting Role', N'', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Forecasting Role</Description></root>', 1, N'')
INSERT [dbo].[Role_] ([uuid_], [roleId], [companyId], [userId], [userName], [createDate], [modifiedDate], [classNameId], [classPK], [name], [title], [description], [type_], [subtype]) VALUES (N'0b4a045f-789d-4d10-88a8-2efb191dc29e', 11579, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-18 04:04:04.800' AS DateTime), CAST(N'2017-05-18 06:09:15.133' AS DateTime), 10004, 11579, N'Future Events Role', N'', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Future Events Role</Description></root>', 1, N'')
INSERT [dbo].[Role_] ([uuid_], [roleId], [companyId], [userId], [userName], [createDate], [modifiedDate], [classNameId], [classPK], [name], [title], [description], [type_], [subtype]) VALUES (N'435f027d-7b47-4eac-ab27-ff8bb0af08f3', 11580, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-18 04:04:34.473' AS DateTime), CAST(N'2017-05-18 06:10:38.127' AS DateTime), 10004, 11580, N'Global Files Role', N'', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Global Files Role</Description></root>', 1, N'')
INSERT [dbo].[Role_] ([uuid_], [roleId], [companyId], [userId], [userName], [createDate], [modifiedDate], [classNameId], [classPK], [name], [title], [description], [type_], [subtype]) VALUES (N'7ff96339-7ddb-4eeb-9056-dc1ed2fcc239', 11583, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-18 04:05:46.837' AS DateTime), CAST(N'2017-05-18 06:22:04.740' AS DateTime), 10004, 11583, N'Price Type Role', N'', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Price Type Role</Description></root>', 1, N'')
INSERT [dbo].[Role_] ([uuid_], [roleId], [companyId], [userId], [userName], [createDate], [modifiedDate], [classNameId], [classPK], [name], [title], [description], [type_], [subtype]) VALUES (N'a7e901a9-d0ba-4f64-922b-771538a9b9bf', 11584, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-18 04:05:59.327' AS DateTime), CAST(N'2017-05-18 06:09:59.667' AS DateTime), 10004, 11584, N'Security Role', N'', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Security Role</Description></root>', 1, N'')
INSERT [dbo].[Role_] ([uuid_], [roleId], [companyId], [userId], [userName], [createDate], [modifiedDate], [classNameId], [classPK], [name], [title], [description], [type_], [subtype]) VALUES (N'd3418bc0-5504-47d1-a2a5-780dd24f4135', 11585, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-18 04:06:21.530' AS DateTime), CAST(N'2017-05-18 06:09:46.413' AS DateTime), 10004, 11585, N'Transaction Mgmt Role', N'', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Transaction Mgmt Role</Description></root>', 1, N'')
