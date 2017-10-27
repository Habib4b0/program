SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ResourceTypePermission](
	[resourceTypePermissionId] [bigint] NOT NULL,
	[companyId] [bigint] NULL,
	[groupId] [bigint] NULL,
	[name] [nvarchar](75) NULL,
	[roleId] [bigint] NULL,
	[actionIds] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[resourceTypePermissionId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
