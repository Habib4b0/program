SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Groups_Roles](
	[groupId] [bigint] NOT NULL,
	[roleId] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[groupId] ASC,
	[roleId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[Groups_Roles] ([groupId], [roleId]) VALUES (11498, 11580)
INSERT [dbo].[Groups_Roles] ([groupId], [roleId]) VALUES (11502, 11576)
INSERT [dbo].[Groups_Roles] ([groupId], [roleId]) VALUES (11506, 11585)
INSERT [dbo].[Groups_Roles] ([groupId], [roleId]) VALUES (11510, 11565)
INSERT [dbo].[Groups_Roles] ([groupId], [roleId]) VALUES (11514, 11578)
INSERT [dbo].[Groups_Roles] ([groupId], [roleId]) VALUES (11518, 11583)
INSERT [dbo].[Groups_Roles] ([groupId], [roleId]) VALUES (11522, 11579)
INSERT [dbo].[Groups_Roles] ([groupId], [roleId]) VALUES (11526, 11584)
INSERT [dbo].[Groups_Roles] ([groupId], [roleId]) VALUES (11534, 11573)
