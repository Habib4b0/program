SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DLFolder](
	[uuid_] [nvarchar](75) NULL,
	[folderId] [bigint] NOT NULL,
	[groupId] [bigint] NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[userName] [nvarchar](75) NULL,
	[createDate] [datetime] NULL,
	[modifiedDate] [datetime] NULL,
	[repositoryId] [bigint] NULL,
	[mountPoint] [bit] NULL,
	[parentFolderId] [bigint] NULL,
	[treePath] [nvarchar](2000) NULL,
	[name] [nvarchar](100) NULL,
	[description] [nvarchar](2000) NULL,
	[lastPostDate] [datetime] NULL,
	[defaultFileEntryTypeId] [bigint] NULL,
	[hidden_] [bit] NULL,
	[overrideFileEntryTypes] [bit] NULL,
	[status] [int] NULL,
	[statusByUserId] [bigint] NULL,
	[statusByUserName] [nvarchar](75) NULL,
	[statusDate] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[folderId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
