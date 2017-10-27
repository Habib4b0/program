SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AssetVocabulary](
	[uuid_] [nvarchar](75) NULL,
	[vocabularyId] [bigint] NOT NULL,
	[groupId] [bigint] NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[userName] [nvarchar](75) NULL,
	[createDate] [datetime] NULL,
	[modifiedDate] [datetime] NULL,
	[name] [nvarchar](75) NULL,
	[title] [nvarchar](2000) NULL,
	[description] [nvarchar](2000) NULL,
	[settings_] [nvarchar](2000) NULL,
PRIMARY KEY CLUSTERED 
(
	[vocabularyId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[AssetVocabulary] ([uuid_], [vocabularyId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [name], [title], [description], [settings_]) VALUES (N'84523908-f94f-430a-a52f-170c9c9884a1', 10327, 10197, 10157, 10161, N'', CAST(N'2017-05-12 00:54:34.090' AS DateTime), CAST(N'2017-05-12 00:54:34.090' AS DateTime), N'Topic', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Title language-id="en_US">Topic</Title></root>', N'', N'')
