SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[JournalFeed](
	[uuid_] [nvarchar](75) NULL,
	[id_] [bigint] NOT NULL,
	[groupId] [bigint] NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[userName] [nvarchar](75) NULL,
	[createDate] [datetime] NULL,
	[modifiedDate] [datetime] NULL,
	[feedId] [nvarchar](75) NULL,
	[name] [nvarchar](75) NULL,
	[description] [nvarchar](2000) NULL,
	[type_] [nvarchar](75) NULL,
	[structureId] [nvarchar](75) NULL,
	[templateId] [nvarchar](75) NULL,
	[rendererTemplateId] [nvarchar](75) NULL,
	[delta] [int] NULL,
	[orderByCol] [nvarchar](75) NULL,
	[orderByType] [nvarchar](75) NULL,
	[targetLayoutFriendlyUrl] [nvarchar](255) NULL,
	[targetPortletId] [nvarchar](75) NULL,
	[contentField] [nvarchar](75) NULL,
	[feedFormat] [nvarchar](75) NULL,
	[feedVersion] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
