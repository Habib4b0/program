SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UserNotificationEvent](
	[uuid_] [nvarchar](75) NULL,
	[userNotificationEventId] [bigint] NOT NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[type_] [nvarchar](75) NULL,
	[timestamp] [bigint] NULL,
	[deliverBy] [bigint] NULL,
	[delivered] [bit] NULL,
	[payload] [nvarchar](max) NULL,
	[archived] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[userNotificationEventId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
