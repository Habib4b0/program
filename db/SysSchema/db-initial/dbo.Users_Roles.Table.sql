SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users_Roles](
	[roleId] [bigint] NOT NULL,
	[userId] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[roleId] ASC,
	[userId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[Users_Roles] ([roleId], [userId]) VALUES (10164, 10201)
INSERT [dbo].[Users_Roles] ([roleId], [userId]) VALUES (10164, 10829)
INSERT [dbo].[Users_Roles] ([roleId], [userId]) VALUES (10165, 10161)
INSERT [dbo].[Users_Roles] ([roleId], [userId]) VALUES (10167, 10201)
INSERT [dbo].[Users_Roles] ([roleId], [userId]) VALUES (10167, 10829)
INSERT [dbo].[Users_Roles] ([roleId], [userId]) VALUES (10167, 11430)
INSERT [dbo].[Users_Roles] ([roleId], [userId]) VALUES (10167, 11587)
INSERT [dbo].[Users_Roles] ([roleId], [userId]) VALUES (10168, 10201)
INSERT [dbo].[Users_Roles] ([roleId], [userId]) VALUES (10168, 10829)
INSERT [dbo].[Users_Roles] ([roleId], [userId]) VALUES (10168, 11430)
INSERT [dbo].[Users_Roles] ([roleId], [userId]) VALUES (10168, 11587)
INSERT [dbo].[Users_Roles] ([roleId], [userId]) VALUES (11456, 10829)
INSERT [dbo].[Users_Roles] ([roleId], [userId]) VALUES (11456, 11430)
INSERT [dbo].[Users_Roles] ([roleId], [userId]) VALUES (11563, 10829)
INSERT [dbo].[Users_Roles] ([roleId], [userId]) VALUES (11566, 10829)
INSERT [dbo].[Users_Roles] ([roleId], [userId]) VALUES (11566, 11430)
INSERT [dbo].[Users_Roles] ([roleId], [userId]) VALUES (11567, 10829)
INSERT [dbo].[Users_Roles] ([roleId], [userId]) VALUES (11567, 11430)
INSERT [dbo].[Users_Roles] ([roleId], [userId]) VALUES (11574, 10829)
INSERT [dbo].[Users_Roles] ([roleId], [userId]) VALUES (11574, 11430)
INSERT [dbo].[Users_Roles] ([roleId], [userId]) VALUES (11575, 10829)
INSERT [dbo].[Users_Roles] ([roleId], [userId]) VALUES (11575, 11430)
