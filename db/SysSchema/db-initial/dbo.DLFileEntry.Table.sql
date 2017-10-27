SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DLFileEntry](
	[uuid_] [nvarchar](75) NULL,
	[fileEntryId] [bigint] NOT NULL,
	[groupId] [bigint] NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[userName] [nvarchar](75) NULL,
	[createDate] [datetime] NULL,
	[modifiedDate] [datetime] NULL,
	[classNameId] [bigint] NULL,
	[classPK] [bigint] NULL,
	[repositoryId] [bigint] NULL,
	[folderId] [bigint] NULL,
	[treePath] [nvarchar](2000) NULL,
	[name] [nvarchar](255) NULL,
	[extension] [nvarchar](75) NULL,
	[mimeType] [nvarchar](75) NULL,
	[title] [nvarchar](255) NULL,
	[description] [nvarchar](2000) NULL,
	[extraSettings] [nvarchar](max) NULL,
	[fileEntryTypeId] [bigint] NULL,
	[version] [nvarchar](75) NULL,
	[size_] [bigint] NULL,
	[readCount] [int] NULL,
	[smallImageId] [bigint] NULL,
	[largeImageId] [bigint] NULL,
	[custom1ImageId] [bigint] NULL,
	[custom2ImageId] [bigint] NULL,
	[manualCheckInRequired] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[fileEntryId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
