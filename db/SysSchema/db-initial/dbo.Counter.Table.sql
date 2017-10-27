SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Counter](
	[name] [nvarchar](75) NOT NULL,
	[currentId] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[Counter] ([name], [currentId]) VALUES (N'com.stpl.counter.model.Counter', 12400)
INSERT [dbo].[Counter] ([name], [currentId]) VALUES (N'com.stpl.portal.model.Layout#10175#true', 1)
INSERT [dbo].[Counter] ([name], [currentId]) VALUES (N'com.stpl.portal.model.Layout#10184#false', 69)
INSERT [dbo].[Counter] ([name], [currentId]) VALUES (N'com.stpl.portal.model.Layout#10204#false', 1)
INSERT [dbo].[Counter] ([name], [currentId]) VALUES (N'com.stpl.portal.model.Layout#10204#true', 1)
INSERT [dbo].[Counter] ([name], [currentId]) VALUES (N'com.stpl.portal.model.Layout#10320#true', 1)
INSERT [dbo].[Counter] ([name], [currentId]) VALUES (N'com.stpl.portal.model.Layout#10332#true', 1)
INSERT [dbo].[Counter] ([name], [currentId]) VALUES (N'com.stpl.portal.model.Layout#10342#true', 1)
INSERT [dbo].[Counter] ([name], [currentId]) VALUES (N'com.stpl.portal.model.Layout#10353#true', 2)
INSERT [dbo].[Counter] ([name], [currentId]) VALUES (N'com.stpl.portal.model.Layout#10379#true', 3)
INSERT [dbo].[Counter] ([name], [currentId]) VALUES (N'com.stpl.portal.model.Layout#10832#false', 1)
INSERT [dbo].[Counter] ([name], [currentId]) VALUES (N'com.stpl.portal.model.Layout#10832#true', 1)
INSERT [dbo].[Counter] ([name], [currentId]) VALUES (N'com.stpl.portal.model.Layout#11433#false', 1)
INSERT [dbo].[Counter] ([name], [currentId]) VALUES (N'com.stpl.portal.model.Layout#11433#true', 1)
INSERT [dbo].[Counter] ([name], [currentId]) VALUES (N'com.stpl.portal.model.ResourceAction', 2100)
INSERT [dbo].[Counter] ([name], [currentId]) VALUES (N'com.stpl.portal.model.ResourcePermission', 2200)
