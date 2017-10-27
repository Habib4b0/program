SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SystemEvent](
	[systemEventId] [bigint] NOT NULL,
	[groupId] [bigint] NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[userName] [nvarchar](75) NULL,
	[createDate] [datetime] NULL,
	[classNameId] [bigint] NULL,
	[classPK] [bigint] NULL,
	[classUuid] [nvarchar](75) NULL,
	[referrerClassNameId] [bigint] NULL,
	[parentSystemEventId] [bigint] NULL,
	[systemEventSetKey] [bigint] NULL,
	[type_] [int] NULL,
	[extraData] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[systemEventId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
INSERT [dbo].[SystemEvent] ([systemEventId], [groupId], [companyId], [userId], [userName], [createDate], [classNameId], [classPK], [classUuid], [referrerClassNameId], [parentSystemEventId], [systemEventSetKey], [type_], [extraData]) VALUES (11599, 0, 10157, 0, N'', CAST(N'2017-05-18 05:44:37.507' AS DateTime), 10004, 11562, N'66cd9b63-64b7-48e7-8368-70092b2bdc41', 0, 0, 11600, 1, N'')
INSERT [dbo].[SystemEvent] ([systemEventId], [groupId], [companyId], [userId], [userName], [createDate], [classNameId], [classPK], [classUuid], [referrerClassNameId], [parentSystemEventId], [systemEventSetKey], [type_], [extraData]) VALUES (11602, 0, 10157, 0, N'', CAST(N'2017-05-18 05:44:48.323' AS DateTime), 10004, 11572, N'df50f52f-8cea-40dc-8b25-782cc62b8cad', 0, 0, 11603, 1, N'')
INSERT [dbo].[SystemEvent] ([systemEventId], [groupId], [companyId], [userId], [userName], [createDate], [classNameId], [classPK], [classUuid], [referrerClassNameId], [parentSystemEventId], [systemEventSetKey], [type_], [extraData]) VALUES (11605, 0, 10157, 0, N'', CAST(N'2017-05-18 06:04:05.567' AS DateTime), 10006, 11537, N'54720b29-4d8e-4c95-8d86-748915bc4b5a', 0, 0, 11606, 1, N'')
INSERT [dbo].[SystemEvent] ([systemEventId], [groupId], [companyId], [userId], [userName], [createDate], [classNameId], [classPK], [classUuid], [referrerClassNameId], [parentSystemEventId], [systemEventSetKey], [type_], [extraData]) VALUES (11608, 0, 10157, 0, N'', CAST(N'2017-05-18 06:04:15.457' AS DateTime), 10006, 11545, N'f82fcd49-4cd8-46e3-8bc0-54c02d04a18d', 0, 0, 11609, 1, N'')
INSERT [dbo].[SystemEvent] ([systemEventId], [groupId], [companyId], [userId], [userName], [createDate], [classNameId], [classPK], [classUuid], [referrerClassNameId], [parentSystemEventId], [systemEventSetKey], [type_], [extraData]) VALUES (11611, 0, 10157, 0, N'', CAST(N'2017-05-18 06:04:30.820' AS DateTime), 10006, 11493, N'7e89f152-09f9-4e90-9963-55a94b6705a2', 0, 0, 11612, 1, N'')
INSERT [dbo].[SystemEvent] ([systemEventId], [groupId], [companyId], [userId], [userName], [createDate], [classNameId], [classPK], [classUuid], [referrerClassNameId], [parentSystemEventId], [systemEventSetKey], [type_], [extraData]) VALUES (11614, 0, 10157, 0, N'', CAST(N'2017-05-18 06:04:35.693' AS DateTime), 10006, 11489, N'ac226b01-d706-4583-ba5f-a7f5664913a5', 0, 0, 11615, 1, N'')
INSERT [dbo].[SystemEvent] ([systemEventId], [groupId], [companyId], [userId], [userName], [createDate], [classNameId], [classPK], [classUuid], [referrerClassNameId], [parentSystemEventId], [systemEventSetKey], [type_], [extraData]) VALUES (11617, 0, 10157, 0, N'', CAST(N'2017-05-18 06:04:42.583' AS DateTime), 10006, 11553, N'8b93df53-df61-41a8-99ea-91d51071769d', 0, 0, 11618, 1, N'')
INSERT [dbo].[SystemEvent] ([systemEventId], [groupId], [companyId], [userId], [userName], [createDate], [classNameId], [classPK], [classUuid], [referrerClassNameId], [parentSystemEventId], [systemEventSetKey], [type_], [extraData]) VALUES (11620, 0, 10157, 0, N'', CAST(N'2017-05-18 06:04:48.043' AS DateTime), 10006, 11549, N'e38a02a8-02b8-4ad8-bafd-5836d3ed566d', 0, 0, 11621, 1, N'')
INSERT [dbo].[SystemEvent] ([systemEventId], [groupId], [companyId], [userId], [userName], [createDate], [classNameId], [classPK], [classUuid], [referrerClassNameId], [parentSystemEventId], [systemEventSetKey], [type_], [extraData]) VALUES (11623, 0, 10157, 0, N'', CAST(N'2017-05-18 06:04:53.380' AS DateTime), 10006, 11557, N'8863a440-cf4c-48d2-8a96-9dbe1514c368', 0, 0, 11624, 1, N'')
INSERT [dbo].[SystemEvent] ([systemEventId], [groupId], [companyId], [userId], [userName], [createDate], [classNameId], [classPK], [classUuid], [referrerClassNameId], [parentSystemEventId], [systemEventSetKey], [type_], [extraData]) VALUES (11626, 0, 10157, 0, N'', CAST(N'2017-05-18 06:04:58.520' AS DateTime), 10006, 11541, N'46ec9c36-b79a-443d-9bcf-ecd24ec2308b', 0, 0, 11627, 1, N'')
INSERT [dbo].[SystemEvent] ([systemEventId], [groupId], [companyId], [userId], [userName], [createDate], [classNameId], [classPK], [classUuid], [referrerClassNameId], [parentSystemEventId], [systemEventSetKey], [type_], [extraData]) VALUES (11629, 0, 10157, 0, N'', CAST(N'2017-05-18 06:05:09.087' AS DateTime), 10004, 11570, N'9c0901bc-fff6-4f27-a89c-4ae6dab0dc81', 0, 0, 11630, 1, N'')
INSERT [dbo].[SystemEvent] ([systemEventId], [groupId], [companyId], [userId], [userName], [createDate], [classNameId], [classPK], [classUuid], [referrerClassNameId], [parentSystemEventId], [systemEventSetKey], [type_], [extraData]) VALUES (11632, 0, 10157, 0, N'', CAST(N'2017-05-18 06:05:13.667' AS DateTime), 10004, 11571, N'4f09ea61-2970-44c1-8ed5-79d3bef8c805', 0, 0, 11633, 1, N'')
INSERT [dbo].[SystemEvent] ([systemEventId], [groupId], [companyId], [userId], [userName], [createDate], [classNameId], [classPK], [classUuid], [referrerClassNameId], [parentSystemEventId], [systemEventSetKey], [type_], [extraData]) VALUES (11635, 0, 10157, 0, N'', CAST(N'2017-05-18 06:08:27.363' AS DateTime), 10004, 11564, N'ef9b2c09-bb6b-4a54-9a37-9e3886f4ba62', 0, 0, 11636, 1, N'')
INSERT [dbo].[SystemEvent] ([systemEventId], [groupId], [companyId], [userId], [userName], [createDate], [classNameId], [classPK], [classUuid], [referrerClassNameId], [parentSystemEventId], [systemEventSetKey], [type_], [extraData]) VALUES (11638, 0, 10157, 0, N'', CAST(N'2017-05-18 06:10:22.337' AS DateTime), 10004, 11581, N'346b70d6-2264-482b-8305-b5eeb5a22c3c', 0, 0, 11639, 1, N'')
INSERT [dbo].[SystemEvent] ([systemEventId], [groupId], [companyId], [userId], [userName], [createDate], [classNameId], [classPK], [classUuid], [referrerClassNameId], [parentSystemEventId], [systemEventSetKey], [type_], [extraData]) VALUES (11641, 0, 10157, 0, N'', CAST(N'2017-05-18 06:16:57.017' AS DateTime), 10006, 11477, N'e20ef131-8c88-45cb-a89f-74f23d1240d2', 0, 0, 11642, 1, N'')
INSERT [dbo].[SystemEvent] ([systemEventId], [groupId], [companyId], [userId], [userName], [createDate], [classNameId], [classPK], [classUuid], [referrerClassNameId], [parentSystemEventId], [systemEventSetKey], [type_], [extraData]) VALUES (11644, 0, 10157, 0, N'', CAST(N'2017-05-18 06:17:01.337' AS DateTime), 10006, 11469, N'c8305bbf-9268-47de-91f2-71ca9715a809', 0, 0, 11645, 1, N'')
INSERT [dbo].[SystemEvent] ([systemEventId], [groupId], [companyId], [userId], [userName], [createDate], [classNameId], [classPK], [classUuid], [referrerClassNameId], [parentSystemEventId], [systemEventSetKey], [type_], [extraData]) VALUES (11647, 0, 10157, 0, N'', CAST(N'2017-05-18 06:17:05.250' AS DateTime), 10006, 11473, N'09728191-0de0-4bb8-81ec-8388d1849460', 0, 0, 11648, 1, N'')
INSERT [dbo].[SystemEvent] ([systemEventId], [groupId], [companyId], [userId], [userName], [createDate], [classNameId], [classPK], [classUuid], [referrerClassNameId], [parentSystemEventId], [systemEventSetKey], [type_], [extraData]) VALUES (11650, 0, 10157, 0, N'', CAST(N'2017-05-18 06:17:14.893' AS DateTime), 10006, 11481, N'd587031e-345d-423f-aa29-139af929ee3e', 0, 0, 11651, 1, N'')
INSERT [dbo].[SystemEvent] ([systemEventId], [groupId], [companyId], [userId], [userName], [createDate], [classNameId], [classPK], [classUuid], [referrerClassNameId], [parentSystemEventId], [systemEventSetKey], [type_], [extraData]) VALUES (11653, 0, 10157, 0, N'', CAST(N'2017-05-18 06:17:25.320' AS DateTime), 10006, 11485, N'2d2b9c89-2ac3-465f-ae1d-16d9785e656c', 0, 0, 11654, 1, N'')
INSERT [dbo].[SystemEvent] ([systemEventId], [groupId], [companyId], [userId], [userName], [createDate], [classNameId], [classPK], [classUuid], [referrerClassNameId], [parentSystemEventId], [systemEventSetKey], [type_], [extraData]) VALUES (11656, 0, 10157, 0, N'', CAST(N'2017-05-18 06:19:17.980' AS DateTime), 10004, 11582, N'6c71b546-dac0-4d21-824c-a275f543784c', 0, 0, 11657, 1, N'')
INSERT [dbo].[SystemEvent] ([systemEventId], [groupId], [companyId], [userId], [userName], [createDate], [classNameId], [classPK], [classUuid], [referrerClassNameId], [parentSystemEventId], [systemEventSetKey], [type_], [extraData]) VALUES (11659, 0, 10157, 0, N'', CAST(N'2017-05-18 06:19:53.110' AS DateTime), 10004, 11577, N'cad57716-c350-47f0-baa6-806ed41d8d50', 0, 0, 11660, 1, N'')
INSERT [dbo].[SystemEvent] ([systemEventId], [groupId], [companyId], [userId], [userName], [createDate], [classNameId], [classPK], [classUuid], [referrerClassNameId], [parentSystemEventId], [systemEventSetKey], [type_], [extraData]) VALUES (11662, 0, 10157, 0, N'', CAST(N'2017-05-18 06:20:04.813' AS DateTime), 10006, 11529, N'6168f005-9d97-4f74-81fb-a81377186125', 0, 0, 11663, 1, N'')
