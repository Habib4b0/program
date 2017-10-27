SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UserNotificationDelivery](
	[userNotificationDeliveryId] [bigint] NOT NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[portletId] [nvarchar](200) NULL,
	[classNameId] [bigint] NULL,
	[notificationType] [int] NULL,
	[deliveryType] [int] NULL,
	[deliver] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[userNotificationDeliveryId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
