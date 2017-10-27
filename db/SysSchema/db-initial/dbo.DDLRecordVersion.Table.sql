SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DDLRecordVersion](
	[recordVersionId] [bigint] NOT NULL,
	[groupId] [bigint] NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[userName] [nvarchar](75) NULL,
	[createDate] [datetime] NULL,
	[DDMStorageId] [bigint] NULL,
	[recordSetId] [bigint] NULL,
	[recordId] [bigint] NULL,
	[version] [nvarchar](75) NULL,
	[displayIndex] [int] NULL,
	[status] [int] NULL,
	[statusByUserId] [bigint] NULL,
	[statusByUserName] [nvarchar](75) NULL,
	[statusDate] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[recordVersionId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
