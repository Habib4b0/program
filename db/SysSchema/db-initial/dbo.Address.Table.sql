SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Address](
	[uuid_] [nvarchar](75) NULL,
	[addressId] [bigint] NOT NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[userName] [nvarchar](75) NULL,
	[createDate] [datetime] NULL,
	[modifiedDate] [datetime] NULL,
	[classNameId] [bigint] NULL,
	[classPK] [bigint] NULL,
	[street1] [nvarchar](75) NULL,
	[street2] [nvarchar](75) NULL,
	[street3] [nvarchar](75) NULL,
	[city] [nvarchar](75) NULL,
	[zip] [nvarchar](75) NULL,
	[regionId] [bigint] NULL,
	[countryId] [bigint] NULL,
	[typeId] [int] NULL,
	[mailing] [bit] NULL,
	[primary_] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[addressId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
