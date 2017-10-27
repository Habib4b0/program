SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ListType](
	[listTypeId] [int] NOT NULL,
	[name] [nvarchar](75) NULL,
	[type_] [nvarchar](75) NULL,
PRIMARY KEY CLUSTERED 
(
	[listTypeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (10000, N'billing', N'com.stpl.portal.model.Account.address')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (10001, N'other', N'com.stpl.portal.model.Account.address')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (10002, N'p-o-box', N'com.stpl.portal.model.Account.address')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (10003, N'shipping', N'com.stpl.portal.model.Account.address')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (10004, N'email-address', N'com.stpl.portal.model.Account.emailAddress')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (10005, N'email-address-2', N'com.stpl.portal.model.Account.emailAddress')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (10006, N'email-address-3', N'com.stpl.portal.model.Account.emailAddress')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (10007, N'fax', N'com.stpl.portal.model.Account.phone')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (10008, N'local', N'com.stpl.portal.model.Account.phone')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (10009, N'other', N'com.stpl.portal.model.Account.phone')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (10010, N'toll-free', N'com.stpl.portal.model.Account.phone')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (10011, N'tty', N'com.stpl.portal.model.Account.phone')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (10012, N'intranet', N'com.stpl.portal.model.Account.website')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (10013, N'public', N'com.stpl.portal.model.Account.website')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (11000, N'business', N'com.stpl.portal.model.Contact.address')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (11001, N'other', N'com.stpl.portal.model.Contact.address')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (11002, N'personal', N'com.stpl.portal.model.Contact.address')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (11003, N'email-address', N'com.stpl.portal.model.Contact.emailAddress')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (11004, N'email-address-2', N'com.stpl.portal.model.Contact.emailAddress')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (11005, N'email-address-3', N'com.stpl.portal.model.Contact.emailAddress')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (11006, N'business', N'com.stpl.portal.model.Contact.phone')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (11007, N'business-fax', N'com.stpl.portal.model.Contact.phone')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (11008, N'mobile-phone', N'com.stpl.portal.model.Contact.phone')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (11009, N'other', N'com.stpl.portal.model.Contact.phone')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (11010, N'pager', N'com.stpl.portal.model.Contact.phone')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (11011, N'personal', N'com.stpl.portal.model.Contact.phone')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (11012, N'personal-fax', N'com.stpl.portal.model.Contact.phone')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (11013, N'tty', N'com.stpl.portal.model.Contact.phone')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (11014, N'dr', N'com.stpl.portal.model.Contact.prefix')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (11015, N'mr', N'com.stpl.portal.model.Contact.prefix')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (11016, N'mrs', N'com.stpl.portal.model.Contact.prefix')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (11017, N'ms', N'com.stpl.portal.model.Contact.prefix')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (11020, N'ii', N'com.stpl.portal.model.Contact.suffix')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (11021, N'iii', N'com.stpl.portal.model.Contact.suffix')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (11022, N'iv', N'com.stpl.portal.model.Contact.suffix')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (11023, N'jr', N'com.stpl.portal.model.Contact.suffix')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (11024, N'phd', N'com.stpl.portal.model.Contact.suffix')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (11025, N'sr', N'com.stpl.portal.model.Contact.suffix')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (11026, N'blog', N'com.stpl.portal.model.Contact.website')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (11027, N'business', N'com.stpl.portal.model.Contact.website')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (11028, N'other', N'com.stpl.portal.model.Contact.website')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (11029, N'personal', N'com.stpl.portal.model.Contact.website')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (12000, N'billing', N'com.stpl.portal.model.Organization.address')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (12001, N'other', N'com.stpl.portal.model.Organization.address')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (12002, N'p-o-box', N'com.stpl.portal.model.Organization.address')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (12003, N'shipping', N'com.stpl.portal.model.Organization.address')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (12004, N'email-address', N'com.stpl.portal.model.Organization.emailAddress')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (12005, N'email-address-2', N'com.stpl.portal.model.Organization.emailAddress')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (12006, N'email-address-3', N'com.stpl.portal.model.Organization.emailAddress')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (12007, N'fax', N'com.stpl.portal.model.Organization.phone')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (12008, N'local', N'com.stpl.portal.model.Organization.phone')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (12009, N'other', N'com.stpl.portal.model.Organization.phone')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (12010, N'toll-free', N'com.stpl.portal.model.Organization.phone')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (12011, N'tty', N'com.stpl.portal.model.Organization.phone')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (12012, N'administrative', N'com.stpl.portal.model.Organization.service')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (12013, N'contracts', N'com.stpl.portal.model.Organization.service')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (12014, N'donation', N'com.stpl.portal.model.Organization.service')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (12015, N'retail', N'com.stpl.portal.model.Organization.service')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (12016, N'training', N'com.stpl.portal.model.Organization.service')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (12017, N'full-member', N'com.stpl.portal.model.Organization.status')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (12018, N'provisional-member', N'com.stpl.portal.model.Organization.status')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (12019, N'intranet', N'com.stpl.portal.model.Organization.website')
INSERT [dbo].[ListType] ([listTypeId], [name], [type_]) VALUES (12020, N'public', N'com.stpl.portal.model.Organization.website')
