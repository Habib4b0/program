SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrgGroupRole](
	[organizationId] [bigint] NOT NULL,
	[groupId] [bigint] NOT NULL,
	[roleId] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[organizationId] ASC,
	[groupId] ASC,
	[roleId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
