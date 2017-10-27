SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MBMailingList](
	[uuid_] [nvarchar](75) NULL,
	[mailingListId] [bigint] NOT NULL,
	[groupId] [bigint] NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[userName] [nvarchar](75) NULL,
	[createDate] [datetime] NULL,
	[modifiedDate] [datetime] NULL,
	[categoryId] [bigint] NULL,
	[emailAddress] [nvarchar](75) NULL,
	[inProtocol] [nvarchar](75) NULL,
	[inServerName] [nvarchar](75) NULL,
	[inServerPort] [int] NULL,
	[inUseSSL] [bit] NULL,
	[inUserName] [nvarchar](75) NULL,
	[inPassword] [nvarchar](75) NULL,
	[inReadInterval] [int] NULL,
	[outEmailAddress] [nvarchar](75) NULL,
	[outCustom] [bit] NULL,
	[outServerName] [nvarchar](75) NULL,
	[outServerPort] [int] NULL,
	[outUseSSL] [bit] NULL,
	[outUserName] [nvarchar](75) NULL,
	[outPassword] [nvarchar](75) NULL,
	[allowAnonymous] [bit] NULL,
	[active_] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[mailingListId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
