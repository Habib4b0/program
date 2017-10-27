SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DLFileVersion](
	[uuid_] [nvarchar](75) NULL,
	[fileVersionId] [bigint] NOT NULL,
	[groupId] [bigint] NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[userName] [nvarchar](75) NULL,
	[createDate] [datetime] NULL,
	[modifiedDate] [datetime] NULL,
	[repositoryId] [bigint] NULL,
	[folderId] [bigint] NULL,
	[fileEntryId] [bigint] NULL,
	[treePath] [nvarchar](2000) NULL,
	[extension] [nvarchar](75) NULL,
	[mimeType] [nvarchar](75) NULL,
	[title] [nvarchar](255) NULL,
	[description] [nvarchar](2000) NULL,
	[changeLog] [nvarchar](75) NULL,
	[extraSettings] [nvarchar](max) NULL,
	[fileEntryTypeId] [bigint] NULL,
	[version] [nvarchar](75) NULL,
	[size_] [bigint] NULL,
	[checksum] [nvarchar](75) NULL,
	[status] [int] NULL,
	[statusByUserId] [bigint] NULL,
	[statusByUserName] [nvarchar](75) NULL,
	[statusDate] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[fileVersionId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
