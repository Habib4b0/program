SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CalEvent](
	[uuid_] [nvarchar](75) NULL,
	[eventId] [bigint] NOT NULL,
	[groupId] [bigint] NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[userName] [nvarchar](75) NULL,
	[createDate] [datetime] NULL,
	[modifiedDate] [datetime] NULL,
	[title] [nvarchar](75) NULL,
	[description] [nvarchar](2000) NULL,
	[location] [nvarchar](2000) NULL,
	[startDate] [datetime] NULL,
	[endDate] [datetime] NULL,
	[durationHour] [int] NULL,
	[durationMinute] [int] NULL,
	[allDay] [bit] NULL,
	[timeZoneSensitive] [bit] NULL,
	[type_] [nvarchar](75) NULL,
	[repeating] [bit] NULL,
	[recurrence] [nvarchar](max) NULL,
	[remindBy] [int] NULL,
	[firstReminder] [int] NULL,
	[secondReminder] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[eventId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
