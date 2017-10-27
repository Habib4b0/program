SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PasswordPolicyRel](
	[passwordPolicyRelId] [bigint] NOT NULL,
	[passwordPolicyId] [bigint] NULL,
	[classNameId] [bigint] NULL,
	[classPK] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[passwordPolicyRelId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[PasswordPolicyRel] ([passwordPolicyRelId], [passwordPolicyId], [classNameId], [classPK]) VALUES (10203, 10200, 10005, 10201)
INSERT [dbo].[PasswordPolicyRel] ([passwordPolicyRelId], [passwordPolicyId], [classNameId], [classPK]) VALUES (10831, 10200, 10005, 10829)
INSERT [dbo].[PasswordPolicyRel] ([passwordPolicyRelId], [passwordPolicyId], [classNameId], [classPK]) VALUES (11432, 10200, 10005, 11430)
INSERT [dbo].[PasswordPolicyRel] ([passwordPolicyRelId], [passwordPolicyId], [classNameId], [classPK]) VALUES (11589, 10200, 10005, 11587)
