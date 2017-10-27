SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SCLicense](
	[licenseId] [bigint] NOT NULL,
	[name] [nvarchar](75) NULL,
	[url] [nvarchar](2000) NULL,
	[openSource] [bit] NULL,
	[active_] [bit] NULL,
	[recommended] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[licenseId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
