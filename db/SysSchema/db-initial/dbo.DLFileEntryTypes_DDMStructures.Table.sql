SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DLFileEntryTypes_DDMStructures](
	[structureId] [bigint] NOT NULL,
	[fileEntryTypeId] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[structureId] ASC,
	[fileEntryTypeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[DLFileEntryTypes_DDMStructures] ([structureId], [fileEntryTypeId]) VALUES (10306, 10313)
INSERT [dbo].[DLFileEntryTypes_DDMStructures] ([structureId], [fileEntryTypeId]) VALUES (10307, 10311)
INSERT [dbo].[DLFileEntryTypes_DDMStructures] ([structureId], [fileEntryTypeId]) VALUES (10308, 10315)
INSERT [dbo].[DLFileEntryTypes_DDMStructures] ([structureId], [fileEntryTypeId]) VALUES (10310, 10309)
INSERT [dbo].[DLFileEntryTypes_DDMStructures] ([structureId], [fileEntryTypeId]) VALUES (10312, 10311)
INSERT [dbo].[DLFileEntryTypes_DDMStructures] ([structureId], [fileEntryTypeId]) VALUES (10314, 10313)
INSERT [dbo].[DLFileEntryTypes_DDMStructures] ([structureId], [fileEntryTypeId]) VALUES (10316, 10315)
