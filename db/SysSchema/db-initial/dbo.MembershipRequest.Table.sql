SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MembershipRequest](
	[membershipRequestId] [bigint] NOT NULL,
	[groupId] [bigint] NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[createDate] [datetime] NULL,
	[comments] [nvarchar](2000) NULL,
	[replyComments] [nvarchar](2000) NULL,
	[replyDate] [datetime] NULL,
	[replierUserId] [bigint] NULL,
	[statusId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[membershipRequestId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
