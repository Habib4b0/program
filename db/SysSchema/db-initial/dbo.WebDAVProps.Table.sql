SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[WebDAVProps](
	[webDavPropsId] [bigint] NOT NULL,
	[companyId] [bigint] NULL,
	[createDate] [datetime] NULL,
	[modifiedDate] [datetime] NULL,
	[classNameId] [bigint] NULL,
	[classPK] [bigint] NULL,
	[props] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[webDavPropsId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
