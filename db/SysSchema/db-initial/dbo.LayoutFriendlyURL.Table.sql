SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LayoutFriendlyURL](
	[uuid_] [nvarchar](75) NULL,
	[layoutFriendlyURLId] [bigint] NOT NULL,
	[groupId] [bigint] NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[userName] [nvarchar](75) NULL,
	[createDate] [datetime] NULL,
	[modifiedDate] [datetime] NULL,
	[plid] [bigint] NULL,
	[privateLayout] [bit] NULL,
	[friendlyURL] [nvarchar](255) NULL,
	[languageId] [nvarchar](75) NULL,
PRIMARY KEY CLUSTERED 
(
	[layoutFriendlyURLId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'77731d3a-ac3f-426c-a1c5-409d268b3862', 10179, 10175, 10157, 10161, N'', CAST(N'2017-05-12 00:54:29.497' AS DateTime), CAST(N'2017-05-12 00:54:29.497' AS DateTime), 10178, 1, N'/manage', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'f4959c4b-e8f9-4dc7-b050-7870919d8ca3', 10188, 10184, 10157, 10161, N'', CAST(N'2017-05-12 00:54:30.643' AS DateTime), CAST(N'2017-05-12 00:54:30.643' AS DateTime), 10187, 0, N'/welcome', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'af1d8c49-fd38-4bfa-b291-71d9698fe1dc', 10324, 10320, 10157, 10161, N'', CAST(N'2017-05-12 00:54:34.077' AS DateTime), CAST(N'2017-05-12 00:54:34.077' AS DateTime), 10323, 1, N'/layout', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'44423686-1cc2-4c9e-b498-28bb10c12cb0', 10336, 10332, 10157, 10161, N'', CAST(N'2017-05-12 00:54:34.480' AS DateTime), CAST(N'2017-05-12 00:54:34.480' AS DateTime), 10335, 1, N'/layout', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'3d73a900-4bb0-4c77-aa83-e66528811f5f', 10346, 10342, 10157, 10161, N'', CAST(N'2017-05-12 00:54:34.707' AS DateTime), CAST(N'2017-05-12 00:54:34.707' AS DateTime), 10345, 1, N'/layout', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'c2abe4e6-3635-489f-8929-788d0615ad0f', 10365, 10353, 10157, 10161, N'', CAST(N'2017-05-12 00:54:36.863' AS DateTime), CAST(N'2017-05-12 00:54:36.863' AS DateTime), 10364, 1, N'/home', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'ea9ea65d-7d30-4015-959c-978c6dfa42c7', 10373, 10353, 10157, 10161, N'', CAST(N'2017-05-12 00:54:37.070' AS DateTime), CAST(N'2017-05-12 00:54:37.070' AS DateTime), 10372, 1, N'/wiki', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'c0cf6bfb-6999-4b32-8119-4870bc2a2197', 10391, 10379, 10157, 10161, N'', CAST(N'2017-05-12 00:54:37.980' AS DateTime), CAST(N'2017-05-12 00:54:37.980' AS DateTime), 10390, 1, N'/home', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'eec8271f-a5bc-41f9-bfd8-ccda7dd87e79', 10400, 10379, 10157, 10161, N'', CAST(N'2017-05-12 00:54:38.193' AS DateTime), CAST(N'2017-05-12 00:54:38.193' AS DateTime), 10399, 1, N'/documents', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'13068efd-87b7-4a26-ac2c-e6a51c786634', 10408, 10379, 10157, 10161, N'', CAST(N'2017-05-12 00:54:38.347' AS DateTime), CAST(N'2017-05-12 00:54:38.347' AS DateTime), 10407, 1, N'/news', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'425feb93-b595-4501-88dc-b64d06e36c9c', 10803, 10204, 10157, 10201, N'Test Test', CAST(N'2017-05-17 04:49:13.123' AS DateTime), CAST(N'2017-05-17 04:49:13.123' AS DateTime), 10802, 1, N'/home', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'bab1a2b0-023c-41ca-bb99-d4046679a3de', 10809, 10204, 10157, 10201, N'Test Test', CAST(N'2017-05-17 04:49:13.960' AS DateTime), CAST(N'2017-05-17 04:49:13.960' AS DateTime), 10808, 0, N'/home', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'bfa01023-ec95-4408-91d8-e38da33fab17', 10823, 10184, 10157, 10201, N'Test Test', CAST(N'2017-05-17 05:07:30.393' AS DateTime), CAST(N'2017-05-17 05:07:30.393' AS DateTime), 10822, 0, N'/home', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'3a2dbf53-c0d6-4677-8ad1-ac15661ebbf7', 10844, 10832, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 05:31:53.093' AS DateTime), CAST(N'2017-05-17 05:31:53.093' AS DateTime), 10843, 1, N'/home', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'43f52ee9-2d61-4da2-ae15-7b3c9e5326bd', 10850, 10832, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 05:31:53.197' AS DateTime), CAST(N'2017-05-17 05:31:53.197' AS DateTime), 10849, 0, N'/home', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'3a7f0f93-9ebe-4ea5-be42-6d5a4a46e999', 10861, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 05:45:13.463' AS DateTime), CAST(N'2017-05-17 05:45:13.463' AS DateTime), 10860, 0, N'/global-files', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'035afdeb-d518-47f4-9684-741c1a1332e7', 10867, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 05:45:52.287' AS DateTime), CAST(N'2017-05-17 05:45:52.287' AS DateTime), 10866, 0, N'/company-master', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'0edb1e45-ced7-4f83-a033-4e3f6951741a', 10873, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 05:46:21.887' AS DateTime), CAST(N'2017-05-17 05:46:21.887' AS DateTime), 10872, 0, N'/company-family-plan', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'5256a630-58a8-48b9-b51d-ee5e674653a5', 10879, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 05:46:45.383' AS DateTime), CAST(N'2017-05-17 05:46:45.383' AS DateTime), 10878, 0, N'/customer-groups', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'40fc6848-2aef-48f8-a2ac-5c380f337211', 10885, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 05:47:16.930' AS DateTime), CAST(N'2017-05-17 05:47:16.930' AS DateTime), 10884, 0, N'/item-master', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'31cb75a4-6b43-403d-99ef-65b6982e420a', 10891, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 05:48:36.933' AS DateTime), CAST(N'2017-05-17 05:48:36.933' AS DateTime), 10890, 0, N'/item-family-plan', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'5c632b9e-0438-4254-99eb-f9af56bb2002', 10897, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 05:49:34.563' AS DateTime), CAST(N'2017-05-17 05:49:34.563' AS DateTime), 10896, 0, N'/item-groups', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'1f538013-0359-4bd2-bbd1-1fe0f45ef652', 10903, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 05:49:54.073' AS DateTime), CAST(N'2017-05-17 05:49:54.073' AS DateTime), 10902, 0, N'/price-schedule', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'4c33a8a2-d9b6-486e-9363-90297667d25e', 10909, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 05:50:18.647' AS DateTime), CAST(N'2017-05-17 05:50:18.647' AS DateTime), 10908, 0, N'/rebate-plan', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'c40562af-026a-4bc2-97d8-f2fd5d691f3e', 10915, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 05:50:35.517' AS DateTime), CAST(N'2017-05-17 05:50:35.517' AS DateTime), 10914, 0, N'/rebate-schedule', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'655927c3-5ed3-4d85-b77a-df5bd1496c68', 10921, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 05:50:46.463' AS DateTime), CAST(N'2017-05-17 05:50:46.463' AS DateTime), 10920, 0, N'/deduction-calendar', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'21745925-70ca-438a-a662-b4d8d4697f2f', 10927, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 05:50:59.047' AS DateTime), CAST(N'2017-05-17 05:50:59.047' AS DateTime), 10926, 0, N'/net-sales-formula', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'442b241c-63af-4c2d-8b76-d397bbb436ca', 10933, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 05:51:10.493' AS DateTime), CAST(N'2017-05-17 05:51:10.493' AS DateTime), 10932, 0, N'/compliance-deduction-rules', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'2f0ad249-6331-4446-9307-6a1a3475e165', 10939, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 05:51:45.593' AS DateTime), CAST(N'2017-05-17 05:51:45.593' AS DateTime), 10938, 0, N'/contract-mgmt', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'c2d51c73-29aa-4e6e-8502-c71aadd020a9', 10945, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 05:51:57.303' AS DateTime), CAST(N'2017-05-17 05:51:57.303' AS DateTime), 10944, 0, N'/contract-header', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'da608706-a945-4bfa-a0f8-65cef9aeeeb0', 10951, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 05:52:11.213' AS DateTime), CAST(N'2017-05-17 05:52:11.213' AS DateTime), 10950, 0, N'/contract-dashboard', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'7032d763-944f-4892-b690-8124329e0010', 10957, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 05:52:38.810' AS DateTime), CAST(N'2017-05-17 05:52:38.810' AS DateTime), 10956, 0, N'/transaction-mgmt', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'8d5ab159-1077-4863-884f-b25fb79845e9', 10963, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 05:52:52.997' AS DateTime), CAST(N'2017-05-17 05:52:52.997' AS DateTime), 10962, 0, N'/demand', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'e730c785-7602-4e3b-9fcc-6f7aedaba933', 10969, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 05:53:06.667' AS DateTime), CAST(N'2017-05-17 05:53:06.667' AS DateTime), 10968, 0, N'/gts-forecast', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'a65878f2-1752-402e-b22a-63a4a316b0ae', 10975, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 05:54:18.227' AS DateTime), CAST(N'2017-05-17 05:54:18.227' AS DateTime), 10974, 0, N'/sales-forecast', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'76e88b7f-a9b5-4630-bc95-059d362420d4', 10981, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 05:54:31.797' AS DateTime), CAST(N'2017-05-17 05:54:31.797' AS DateTime), 10980, 0, N'/gts-actual', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'e1543f1d-6e04-49cb-95c8-77fa3e74c4b7', 10987, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 05:54:51.017' AS DateTime), CAST(N'2017-05-17 05:54:51.017' AS DateTime), 10986, 0, N'/sales-actual', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'7aaf4c6c-919d-4ae5-98ba-2eb563d00166', 10993, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 05:55:05.397' AS DateTime), CAST(N'2017-05-17 05:55:05.397' AS DateTime), 10992, 0, N'/inventory-withdrawals', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'b07639fb-762a-46d6-baed-6ad92cd68e3c', 10999, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 05:55:21.593' AS DateTime), CAST(N'2017-05-17 05:55:21.593' AS DateTime), 10998, 0, N'/payments', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'87912501-5e7c-4739-a312-80443838fdd8', 11005, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 05:56:36.533' AS DateTime), CAST(N'2017-05-17 05:56:36.533' AS DateTime), 11004, 0, N'/returns', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'c5409747-60f3-4a87-8f37-58ec32fd3169', 11011, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 05:56:46.773' AS DateTime), CAST(N'2017-05-17 05:56:46.773' AS DateTime), 11010, 0, N'/average-shelf-life', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'c7c89e11-2d9c-40e1-bdcf-8fb65a7b06ec', 11017, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 05:56:56.357' AS DateTime), CAST(N'2017-05-17 05:56:56.357' AS DateTime), 11016, 0, N'/lot-master', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'05bebc05-e7a4-490a-98d4-bc08abd6f97f', 11023, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 05:57:06.057' AS DateTime), CAST(N'2017-05-17 05:57:06.057' AS DateTime), 11022, 0, N'/cpi', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'8bd4992c-d013-4345-b7e5-1eae957a6d68', 11029, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 05:57:16.693' AS DateTime), CAST(N'2017-05-17 05:57:16.693' AS DateTime), 11028, 0, N'/audit-inbound', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'0eff09db-ca16-4516-8234-3e7a6be43e30', 11035, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 05:57:30.377' AS DateTime), CAST(N'2017-05-17 05:57:30.377' AS DateTime), 11034, 0, N'/invalid-integration', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'8997b534-37f2-4f44-958c-2ae8106b1018', 11041, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 05:57:44.613' AS DateTime), CAST(N'2017-05-17 05:57:44.613' AS DateTime), 11040, 0, N'/consolidated-financial-forecast', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'9bafccba-32d0-422a-a3f2-60665431e226', 11047, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 05:58:01.757' AS DateTime), CAST(N'2017-05-17 05:58:01.757' AS DateTime), 11046, 0, N'/accrual-rate-projection', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'6f7eaf5e-3596-45e8-ae86-c9f93c8dad96', 11053, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 05:58:21.120' AS DateTime), CAST(N'2017-05-17 05:58:21.120' AS DateTime), 11052, 0, N'/gl-cost-center', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'95cd5ad8-3995-473e-9785-9600a299fadd', 11059, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 05:59:20.967' AS DateTime), CAST(N'2017-05-17 05:59:20.967' AS DateTime), 11058, 0, N'/global-files-item-master', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'f3e0c268-c887-44ee-ae2b-b07d53857665', 11065, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 05:59:49.740' AS DateTime), CAST(N'2017-05-17 05:59:49.740' AS DateTime), 11064, 0, N'/global-files-item-identifier', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'f560b7bc-8be1-4f88-ba6f-d855cc20af96', 11071, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 06:00:06.050' AS DateTime), CAST(N'2017-05-17 06:00:06.050' AS DateTime), 11070, 0, N'/global-files-item-pricing', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'4dc8afb8-6a2d-40da-af38-f4b04b3602b0', 11077, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 06:00:19.920' AS DateTime), CAST(N'2017-05-17 06:00:19.920' AS DateTime), 11076, 0, N'/global-files-company-master', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'3eff07e2-9688-4887-afc2-4292b2e57820', 11083, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 06:00:39.810' AS DateTime), CAST(N'2017-05-17 06:00:39.810' AS DateTime), 11082, 0, N'/global-files-company-identifier', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'7e0f51c7-880b-473f-a2bc-f91f610c1e2a', 11089, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 06:01:00.520' AS DateTime), CAST(N'2017-05-17 06:01:00.520' AS DateTime), 11088, 0, N'/global-files-company-parent', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'efc5c005-b214-46a6-89f8-8fafc5b73230', 11095, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 06:01:14.423' AS DateTime), CAST(N'2017-05-17 06:01:14.423' AS DateTime), 11094, 0, N'/global-files-company-trade-class', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'2616fe1a-7e12-4b13-8dbd-ac0c11faf59d', 11101, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 06:01:27.740' AS DateTime), CAST(N'2017-05-17 06:01:27.740' AS DateTime), 11100, 0, N'/accrual-details', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'ebe8d9b5-ac23-4e59-bc90-f5a10997b5f5', 11107, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 06:01:37.537' AS DateTime), CAST(N'2017-05-17 06:01:37.537' AS DateTime), 11106, 0, N'/gl-balance', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'85c38cb3-a940-4e2c-a847-78d5be6bc060', 11113, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 06:02:11.803' AS DateTime), CAST(N'2017-05-17 06:02:11.803' AS DateTime), 11112, 0, N'/admin-console', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'f2dde0f0-57c8-4dfc-8a1e-b393164374b4', 11119, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 06:02:24.593' AS DateTime), CAST(N'2017-05-17 06:02:24.593' AS DateTime), 11118, 0, N'/bpm', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'739a2587-743b-4f03-aaa0-ef9745e4a406', 11125, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 06:02:51.123' AS DateTime), CAST(N'2017-05-17 06:02:51.123' AS DateTime), 11124, 0, N'/relationship-builder', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'3633fdd1-afbf-4e97-9c68-9df9b8e5af9f', 11131, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 06:03:02.710' AS DateTime), CAST(N'2017-05-17 06:03:02.710' AS DateTime), 11130, 0, N'/forecast-configuration', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'af35f37c-4c61-4735-bdd1-6dab4c07202a', 11137, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 06:03:39.700' AS DateTime), CAST(N'2017-05-17 06:03:39.700' AS DateTime), 11136, 0, N'/file-management', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'69f927ae-2e3f-4bd7-9460-f378a120af0c', 11143, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 06:03:50.430' AS DateTime), CAST(N'2017-05-17 06:03:50.430' AS DateTime), 11142, 0, N'/email-configuration', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'4112abb1-e7a6-4fb4-aa1d-1de7d0290a9b', 11149, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 06:04:01.630' AS DateTime), CAST(N'2017-05-17 06:04:01.630' AS DateTime), 11148, 0, N'/process-scheduler', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'ce3121c9-9b12-4038-b752-9e1a6358267d', 11155, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 06:04:12.093' AS DateTime), CAST(N'2017-05-17 06:04:12.093' AS DateTime), 11154, 0, N'/process-monitor', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'e393e2ef-61e0-4220-ae79-686c56d42ff4', 11161, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 06:04:22.550' AS DateTime), CAST(N'2017-05-17 06:04:22.550' AS DateTime), 11160, 0, N'/udc-configuration', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'132ddee1-e3e2-4614-9c42-549e32a9ddaa', 11167, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 06:04:33.180' AS DateTime), CAST(N'2017-05-17 06:04:33.180' AS DateTime), 11166, 0, N'/server-logging', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'5bba8c6b-a699-4c55-b937-5df784beb5ac', 11173, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 06:04:44.443' AS DateTime), CAST(N'2017-05-17 06:04:44.443' AS DateTime), 11172, 0, N'/database-monitoring', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'7c29c00a-e60d-4878-ac4e-7c3702a140b3', 11179, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 06:04:54.470' AS DateTime), CAST(N'2017-05-17 06:04:54.470' AS DateTime), 11178, 0, N'/etlfile-management', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'929a5a7a-5fae-457a-a1cc-7d317fa18802', 11185, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 06:05:15.700' AS DateTime), CAST(N'2017-05-17 06:05:15.700' AS DateTime), 11184, 0, N'/security', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'ad31ee4f-a758-4fba-87ca-730466d7df54', 11191, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 06:05:37.747' AS DateTime), CAST(N'2017-05-17 06:05:37.747' AS DateTime), 11190, 0, N'/business-role-management', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'390d3989-460f-497d-8c8b-3f284cab5157', 11197, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 06:05:49.887' AS DateTime), CAST(N'2017-05-17 06:05:49.887' AS DateTime), 11196, 0, N'/user-group-business-role-master', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'58f0c1ef-9b19-4a34-8538-a5c54345dbcf', 11203, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 06:06:02.937' AS DateTime), CAST(N'2017-05-17 06:06:02.937' AS DateTime), 11202, 0, N'/business-role-module-master', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'fdc70c2c-41f3-4ee0-b36e-50d0dd5a81c4', 11209, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 06:06:47.960' AS DateTime), CAST(N'2017-05-17 06:06:47.960' AS DateTime), 11208, 0, N'/forecasting-planning', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'eef902b6-8510-4e29-8cbe-1cb11a879b9f', 11215, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 06:07:05.347' AS DateTime), CAST(N'2017-05-17 06:07:05.347' AS DateTime), 11214, 0, N'/commercial-forecasting', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'6505d190-d694-4d5a-b271-5cc5508e8d85', 11221, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 06:07:17.757' AS DateTime), CAST(N'2017-05-17 06:07:17.757' AS DateTime), 11220, 0, N'/government-forecasting', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'b95ce286-dcbf-4c6e-9b89-8dadac64df15', 11227, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 06:08:23.000' AS DateTime), CAST(N'2017-05-17 06:08:23.000' AS DateTime), 11226, 0, N'/return-forecasting', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'8972ca2d-7c35-4aef-a4db-6380b0783c5b', 11233, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 06:08:34.770' AS DateTime), CAST(N'2017-05-17 06:08:34.770' AS DateTime), 11232, 0, N'/price-type-forecasting', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'5028450e-cead-4d71-aae5-35255584bf0d', 11239, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 06:08:46.173' AS DateTime), CAST(N'2017-05-17 06:08:46.173' AS DateTime), 11238, 0, N'/consolidated-financial-forecast1', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'6d79597b-6c22-4fdf-b047-24fe60c4880a', 11245, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 06:09:00.480' AS DateTime), CAST(N'2017-05-17 06:09:00.480' AS DateTime), 11244, 0, N'/accrual-rate-projection1', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'1d192b2c-4f2f-4415-a18f-4067dbf96f18', 11251, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 06:10:02.137' AS DateTime), CAST(N'2017-05-17 06:10:02.137' AS DateTime), 11250, 0, N'/workflow-inbox', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'5c19f5fd-c7ae-48cc-bb20-05af8c91445d', 11257, 10184, 10157, 10829, N'BPI ADMIN', CAST(N'2017-05-17 06:10:13.987' AS DateTime), CAST(N'2017-05-17 06:10:13.987' AS DateTime), 11256, 0, N'/global-change-mgmt', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'967ad4fa-108e-43e2-9e3c-4b911248f8cc', 11441, 11433, 10157, 11430, N'John Smith', CAST(N'2017-05-17 07:01:40.163' AS DateTime), CAST(N'2017-05-17 07:01:40.163' AS DateTime), 11440, 1, N'/home', N'en_US')
INSERT [dbo].[LayoutFriendlyURL] ([uuid_], [layoutFriendlyURLId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [plid], [privateLayout], [friendlyURL], [languageId]) VALUES (N'c798e7cb-a68a-4a2c-a2e7-3c70314fa3ea', 11447, 11433, 10157, 11430, N'John Smith', CAST(N'2017-05-17 07:01:40.397' AS DateTime), CAST(N'2017-05-17 07:01:40.397' AS DateTime), 11446, 0, N'/home', N'en_US')
