SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DLFileEntryMetadata](
	[uuid_] [nvarchar](75) NULL,
	[fileEntryMetadataId] [bigint] NOT NULL,
	[DDMStorageId] [bigint] NULL,
	[DDMStructureId] [bigint] NULL,
	[fileEntryTypeId] [bigint] NULL,
	[fileEntryId] [bigint] NULL,
	[fileVersionId] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[fileEntryMetadataId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
