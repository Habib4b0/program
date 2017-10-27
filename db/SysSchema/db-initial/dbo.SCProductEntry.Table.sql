SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SCProductEntry](
	[productEntryId] [bigint] NOT NULL,
	[groupId] [bigint] NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[userName] [nvarchar](75) NULL,
	[createDate] [datetime] NULL,
	[modifiedDate] [datetime] NULL,
	[name] [nvarchar](75) NULL,
	[type_] [nvarchar](75) NULL,
	[tags] [nvarchar](255) NULL,
	[shortDescription] [nvarchar](2000) NULL,
	[longDescription] [nvarchar](2000) NULL,
	[pageURL] [nvarchar](2000) NULL,
	[author] [nvarchar](75) NULL,
	[repoGroupId] [nvarchar](75) NULL,
	[repoArtifactId] [nvarchar](75) NULL,
PRIMARY KEY CLUSTERED 
(
	[productEntryId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
