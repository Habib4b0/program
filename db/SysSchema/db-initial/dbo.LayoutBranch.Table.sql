SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LayoutBranch](
	[LayoutBranchId] [bigint] NOT NULL,
	[groupId] [bigint] NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[userName] [nvarchar](75) NULL,
	[layoutSetBranchId] [bigint] NULL,
	[plid] [bigint] NULL,
	[name] [nvarchar](75) NULL,
	[description] [nvarchar](2000) NULL,
	[master] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[LayoutBranchId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
