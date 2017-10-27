SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SocialActivityCounter](
	[activityCounterId] [bigint] NOT NULL,
	[groupId] [bigint] NULL,
	[companyId] [bigint] NULL,
	[classNameId] [bigint] NULL,
	[classPK] [bigint] NULL,
	[name] [nvarchar](75) NULL,
	[ownerType] [int] NULL,
	[currentValue] [int] NULL,
	[totalValue] [int] NULL,
	[graceValue] [int] NULL,
	[startPeriod] [int] NULL,
	[endPeriod] [int] NULL,
	[active_] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[activityCounterId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
