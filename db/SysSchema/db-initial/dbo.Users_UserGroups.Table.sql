SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users_UserGroups](
	[userId] [bigint] NOT NULL,
	[userGroupId] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[userId] ASC,
	[userGroupId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[Users_UserGroups] ([userId], [userGroupId]) VALUES (10829, 11457)
INSERT [dbo].[Users_UserGroups] ([userId], [userGroupId]) VALUES (11430, 11457)
INSERT [dbo].[Users_UserGroups] ([userId], [userGroupId]) VALUES (11430, 11497)
INSERT [dbo].[Users_UserGroups] ([userId], [userGroupId]) VALUES (11430, 11501)
INSERT [dbo].[Users_UserGroups] ([userId], [userGroupId]) VALUES (11430, 11505)
INSERT [dbo].[Users_UserGroups] ([userId], [userGroupId]) VALUES (11430, 11509)
INSERT [dbo].[Users_UserGroups] ([userId], [userGroupId]) VALUES (11430, 11513)
INSERT [dbo].[Users_UserGroups] ([userId], [userGroupId]) VALUES (11430, 11517)
INSERT [dbo].[Users_UserGroups] ([userId], [userGroupId]) VALUES (11430, 11521)
INSERT [dbo].[Users_UserGroups] ([userId], [userGroupId]) VALUES (11430, 11525)
INSERT [dbo].[Users_UserGroups] ([userId], [userGroupId]) VALUES (11430, 11533)
