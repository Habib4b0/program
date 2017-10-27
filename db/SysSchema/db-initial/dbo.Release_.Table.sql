SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Release_](
	[releaseId] [bigint] NOT NULL,
	[createDate] [datetime] NULL,
	[modifiedDate] [datetime] NULL,
	[servletContextName] [nvarchar](75) NULL,
	[buildNumber] [int] NULL,
	[buildDate] [datetime] NULL,
	[verified] [bit] NULL,
	[state_] [int] NULL,
	[testString] [nvarchar](1024) NULL,
PRIMARY KEY CLUSTERED 
(
	[releaseId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[Release_] ([releaseId], [createDate], [modifiedDate], [servletContextName], [buildNumber], [buildDate], [verified], [state_], [testString]) VALUES (1, CAST(N'2017-05-12 11:33:07.747' AS DateTime), CAST(N'2017-07-27 00:52:18.213' AS DateTime), N'portal', 6201, CAST(N'2014-03-20 00:00:00.000' AS DateTime), 1, 0, N'You take the blue pill, the story ends, you wake up in your bed and believe whatever you want to believe. You take the red pill, you stay in Wonderland, and I show you how deep the rabbit hole goes.')
