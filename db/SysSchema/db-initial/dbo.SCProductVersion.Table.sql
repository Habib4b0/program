SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SCProductVersion](
	[productVersionId] [bigint] NOT NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[userName] [nvarchar](75) NULL,
	[createDate] [datetime] NULL,
	[modifiedDate] [datetime] NULL,
	[productEntryId] [bigint] NULL,
	[version] [nvarchar](75) NULL,
	[changeLog] [nvarchar](2000) NULL,
	[downloadPageURL] [nvarchar](2000) NULL,
	[directDownloadURL] [nvarchar](2000) NULL,
	[repoStoreArtifact] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[productVersionId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
