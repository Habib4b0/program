SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DLFileEntryType](
	[uuid_] [nvarchar](75) NULL,
	[fileEntryTypeId] [bigint] NOT NULL,
	[groupId] [bigint] NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[userName] [nvarchar](75) NULL,
	[createDate] [datetime] NULL,
	[modifiedDate] [datetime] NULL,
	[fileEntryTypeKey] [nvarchar](75) NULL,
	[name] [nvarchar](2000) NULL,
	[description] [nvarchar](2000) NULL,
PRIMARY KEY CLUSTERED 
(
	[fileEntryTypeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[DLFileEntryType] ([uuid_], [fileEntryTypeId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [fileEntryTypeKey], [name], [description]) VALUES (N'09a7eb74-dfed-4bb0-bf6e-2129e42ade22', 0, 0, 0, 0, N'', CAST(N'2017-05-12 00:53:29.437' AS DateTime), CAST(N'2017-05-12 00:53:29.437' AS DateTime), N'BASIC-DOCUMENT', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Name language-id="en_US">basic-document</Name></root>', N'')
INSERT [dbo].[DLFileEntryType] ([uuid_], [fileEntryTypeId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [fileEntryTypeKey], [name], [description]) VALUES (N'21e1c984-c927-4808-833d-f618fb4576d3', 10309, 10197, 10157, 10161, N'', CAST(N'2017-05-12 00:54:33.583' AS DateTime), CAST(N'2017-05-12 00:54:33.583' AS DateTime), N'CONTRACT', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Name language-id="en_US">Contract</Name></root>', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Contract</Description></root>')
INSERT [dbo].[DLFileEntryType] ([uuid_], [fileEntryTypeId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [fileEntryTypeKey], [name], [description]) VALUES (N'ae9ec8e7-b18d-4eb3-9f79-bc0e5bc5a8d2', 10311, 10197, 10157, 10161, N'', CAST(N'2017-05-12 00:54:33.687' AS DateTime), CAST(N'2017-05-12 00:54:33.687' AS DateTime), N'MARKETING BANNER', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Name language-id="en_US">Marketing Banner</Name></root>', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Marketing Banner</Description></root>')
INSERT [dbo].[DLFileEntryType] ([uuid_], [fileEntryTypeId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [fileEntryTypeKey], [name], [description]) VALUES (N'be4f0b6c-35df-480b-b908-d03b6eec45b5', 10313, 10197, 10157, 10161, N'', CAST(N'2017-05-12 00:54:33.740' AS DateTime), CAST(N'2017-05-12 00:54:33.740' AS DateTime), N'ONLINE TRAINING', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Name language-id="en_US">Online Training</Name></root>', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Online Training</Description></root>')
INSERT [dbo].[DLFileEntryType] ([uuid_], [fileEntryTypeId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [fileEntryTypeKey], [name], [description]) VALUES (N'3b9a5b2d-abe4-4836-bf5c-1214768bee10', 10315, 10197, 10157, 10161, N'', CAST(N'2017-05-12 00:54:33.810' AS DateTime), CAST(N'2017-05-12 00:54:33.810' AS DateTime), N'SALES PRESENTATION', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Name language-id="en_US">Sales Presentation</Name></root>', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Sales Presentation</Description></root>')
