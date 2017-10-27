SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Organization_](
	[uuid_] [nvarchar](75) NULL,
	[organizationId] [bigint] NOT NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[userName] [nvarchar](75) NULL,
	[createDate] [datetime] NULL,
	[modifiedDate] [datetime] NULL,
	[parentOrganizationId] [bigint] NULL,
	[treePath] [nvarchar](2000) NULL,
	[name] [nvarchar](100) NULL,
	[type_] [nvarchar](75) NULL,
	[recursable] [bit] NULL,
	[regionId] [bigint] NULL,
	[countryId] [bigint] NULL,
	[statusId] [int] NULL,
	[comments] [nvarchar](2000) NULL,
PRIMARY KEY CLUSTERED 
(
	[organizationId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
