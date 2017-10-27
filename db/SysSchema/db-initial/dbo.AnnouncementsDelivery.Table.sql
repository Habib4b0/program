SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AnnouncementsDelivery](
	[deliveryId] [bigint] NOT NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[type_] [nvarchar](75) NULL,
	[email] [bit] NULL,
	[sms] [bit] NULL,
	[website] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[deliveryId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[AnnouncementsDelivery] ([deliveryId], [companyId], [userId], [type_], [email], [sms], [website]) VALUES (10836, 10157, 10829, N'general', 0, 0, 0)
INSERT [dbo].[AnnouncementsDelivery] ([deliveryId], [companyId], [userId], [type_], [email], [sms], [website]) VALUES (10837, 10157, 10829, N'news', 0, 0, 0)
INSERT [dbo].[AnnouncementsDelivery] ([deliveryId], [companyId], [userId], [type_], [email], [sms], [website]) VALUES (10838, 10157, 10829, N'test', 0, 0, 0)
INSERT [dbo].[AnnouncementsDelivery] ([deliveryId], [companyId], [userId], [type_], [email], [sms], [website]) VALUES (11437, 10157, 11430, N'general', 0, 0, 0)
INSERT [dbo].[AnnouncementsDelivery] ([deliveryId], [companyId], [userId], [type_], [email], [sms], [website]) VALUES (11438, 10157, 11430, N'news', 0, 0, 0)
INSERT [dbo].[AnnouncementsDelivery] ([deliveryId], [companyId], [userId], [type_], [email], [sms], [website]) VALUES (11439, 10157, 11430, N'test', 0, 0, 0)
INSERT [dbo].[AnnouncementsDelivery] ([deliveryId], [companyId], [userId], [type_], [email], [sms], [website]) VALUES (11594, 10157, 11587, N'general', 0, 0, 0)
INSERT [dbo].[AnnouncementsDelivery] ([deliveryId], [companyId], [userId], [type_], [email], [sms], [website]) VALUES (11595, 10157, 11587, N'news', 0, 0, 0)
INSERT [dbo].[AnnouncementsDelivery] ([deliveryId], [companyId], [userId], [type_], [email], [sms], [website]) VALUES (11596, 10157, 11587, N'test', 0, 0, 0)
