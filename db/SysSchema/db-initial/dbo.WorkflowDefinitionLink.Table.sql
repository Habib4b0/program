SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[WorkflowDefinitionLink](
	[workflowDefinitionLinkId] [bigint] NOT NULL,
	[groupId] [bigint] NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[userName] [nvarchar](75) NULL,
	[createDate] [datetime] NULL,
	[modifiedDate] [datetime] NULL,
	[classNameId] [bigint] NULL,
	[classPK] [bigint] NULL,
	[typePK] [bigint] NULL,
	[workflowDefinitionName] [nvarchar](75) NULL,
	[workflowDefinitionVersion] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[workflowDefinitionLinkId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
