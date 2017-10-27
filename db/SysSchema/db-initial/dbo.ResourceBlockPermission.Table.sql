SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ResourceBlockPermission](
	[resourceBlockPermissionId] [bigint] NOT NULL,
	[resourceBlockId] [bigint] NULL,
	[roleId] [bigint] NULL,
	[actionIds] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[resourceBlockPermissionId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
