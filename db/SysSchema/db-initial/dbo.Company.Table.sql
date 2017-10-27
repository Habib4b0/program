SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Company](
	[companyId] [bigint] NOT NULL,
	[accountId] [bigint] NULL,
	[webId] [nvarchar](75) NULL,
	[key_] [nvarchar](max) NULL,
	[mx] [nvarchar](75) NULL,
	[homeURL] [nvarchar](2000) NULL,
	[logoId] [bigint] NULL,
	[system] [bit] NULL,
	[maxUsers] [int] NULL,
	[active_] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[companyId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
INSERT [dbo].[Company] ([companyId], [accountId], [webId], [key_], [mx], [homeURL], [logoId], [system], [maxUsers], [active_]) VALUES (10157, 10159, N'stpl.com', N'rO0ABXNyAB9qYXZheC5jcnlwdG8uc3BlYy5TZWNyZXRLZXlTcGVjW0cLZuIwYU0CAAJMAAlhbGdvcml0aG10ABJMamF2YS9sYW5nL1N0cmluZztbAANrZXl0AAJbQnhwdAADQUVTdXIAAltCrPMX+AYIVOACAAB4cAAAABAOK4mEup8z+I2dJbCDVfo3', N'stpl.com', N'/web/guest/home?p_p_id=58&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&saveLastPath=false&_58_struts_action=%2Flogin%2Flogin', 0, 0, 0, 1)
