SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[JournalArticle](
	[uuid_] [nvarchar](75) NULL,
	[id_] [bigint] NOT NULL,
	[resourcePrimKey] [bigint] NULL,
	[groupId] [bigint] NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[userName] [nvarchar](75) NULL,
	[createDate] [datetime] NULL,
	[modifiedDate] [datetime] NULL,
	[folderId] [bigint] NULL,
	[classNameId] [bigint] NULL,
	[classPK] [bigint] NULL,
	[treePath] [nvarchar](2000) NULL,
	[articleId] [nvarchar](75) NULL,
	[version] [float] NULL,
	[title] [nvarchar](2000) NULL,
	[urlTitle] [nvarchar](150) NULL,
	[description] [nvarchar](2000) NULL,
	[content] [nvarchar](max) NULL,
	[type_] [nvarchar](75) NULL,
	[structureId] [nvarchar](75) NULL,
	[templateId] [nvarchar](75) NULL,
	[layoutUuid] [nvarchar](75) NULL,
	[displayDate] [datetime] NULL,
	[expirationDate] [datetime] NULL,
	[reviewDate] [datetime] NULL,
	[indexable] [bit] NULL,
	[smallImage] [bit] NULL,
	[smallImageId] [bigint] NULL,
	[smallImageURL] [nvarchar](2000) NULL,
	[status] [int] NULL,
	[statusByUserId] [bigint] NULL,
	[statusByUserName] [nvarchar](75) NULL,
	[statusDate] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
