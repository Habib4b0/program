SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DDMStructure](
	[uuid_] [nvarchar](75) NULL,
	[structureId] [bigint] NOT NULL,
	[groupId] [bigint] NULL,
	[companyId] [bigint] NULL,
	[userId] [bigint] NULL,
	[userName] [nvarchar](75) NULL,
	[createDate] [datetime] NULL,
	[modifiedDate] [datetime] NULL,
	[parentStructureId] [bigint] NULL,
	[classNameId] [bigint] NULL,
	[structureKey] [nvarchar](75) NULL,
	[name] [nvarchar](2000) NULL,
	[description] [nvarchar](2000) NULL,
	[xsd] [nvarchar](max) NULL,
	[storageType] [nvarchar](75) NULL,
	[type_] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[structureId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
INSERT [dbo].[DDMStructure] ([uuid_], [structureId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [parentStructureId], [classNameId], [structureKey], [name], [description], [xsd], [storageType], [type_]) VALUES (N'5e27d1ab-6482-403b-b026-672e55588795', 10306, 10197, 10157, 10161, N'', CAST(N'2017-05-12 00:54:33.293' AS DateTime), CAST(N'2017-05-12 00:54:33.293' AS DateTime), 0, 10091, N'LEARNING MODULE METADATA', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Name language-id="en_US">Learning Module Metadata</Name></root>', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Learning Module Metadata</Description></root>', N'<?xml version="1.0"?>

<root available-locales="en_US" default-locale="en_US">
	<dynamic-element dataType="string" indexType="keyword" multiple="true" name="select2235" readOnly="false" required="false" showLabel="true" type="select">
		<dynamic-element name="home_edition" type="option" value="HE">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[Home Edition]]></entry>
			</meta-data>
		</dynamic-element>
		<dynamic-element name="business_edition" type="option" value="BE">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[Business Edition]]></entry>
			</meta-data>
		</dynamic-element>
		<dynamic-element name="enterprise_edition" type="option" value="EE">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[Enterprise Edition]]></entry>
			</meta-data>
		</dynamic-element>
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Product]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" indexType="keyword" multiple="true" name="select3212" readOnly="false" required="false" showLabel="true" type="select">
		<dynamic-element name="1_0" type="option" value="1">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[1.0]]></entry>
			</meta-data>
		</dynamic-element>
		<dynamic-element name="2_0" type="option" value="2">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[2.0]]></entry>
			</meta-data>
		</dynamic-element>
		<dynamic-element name="3_0" type="option" value="3">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[3.0]]></entry>
			</meta-data>
		</dynamic-element>
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Version]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" indexType="keyword" multiple="true" name="select4115" readOnly="false" required="false" showLabel="true" type="select">
		<dynamic-element name="administration" type="option" value="admin">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[Administration]]></entry>
			</meta-data>
		</dynamic-element>
		<dynamic-element name="installation" type="option" value="install">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[Installation]]></entry>
			</meta-data>
		</dynamic-element>
		<dynamic-element name="configuration" type="option" value="config">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[Configuration]]></entry>
			</meta-data>
		</dynamic-element>
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Topics]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" indexType="keyword" multiple="false" name="select5069" readOnly="false" required="false" showLabel="true" type="select">
		<dynamic-element name="beginner" type="option" value="beginner">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[Beginner]]></entry>
			</meta-data>
		</dynamic-element>
		<dynamic-element name="intermediate" type="option" value="intermediate">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[Intermediate]]></entry>
			</meta-data>
		</dynamic-element>
		<dynamic-element name="advanced" type="option" value="advanced">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[Advanced]]></entry>
			</meta-data>
		</dynamic-element>
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Level]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
</root>', N'xml', 0)
INSERT [dbo].[DDMStructure] ([uuid_], [structureId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [parentStructureId], [classNameId], [structureKey], [name], [description], [xsd], [storageType], [type_]) VALUES (N'c82b4253-091d-4ef4-bd8d-78cfcd3dbe93', 10307, 10197, 10157, 10161, N'', CAST(N'2017-05-12 00:54:33.457' AS DateTime), CAST(N'2017-05-12 00:54:33.457' AS DateTime), 0, 10091, N'MARKETING CAMPAIGN THEME METADATA', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Name language-id="en_US">Marketing Campaign Theme Metadata</Name></root>', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Marketing Campaign Theme Metadata</Description></root>', N'<?xml version="1.0"?>

<root available-locales="en_US" default-locale="en_US">
	<dynamic-element dataType="string" indexType="keyword" multiple="false" name="select2305" readOnly="false" required="false" showLabel="true" type="select">
		<dynamic-element name="strong_company" type="option" value="strong">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[Strong Company]]></entry>
			</meta-data>
		</dynamic-element>
		<dynamic-element name="new_product_launch" type="option" value="product">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[New Product Launch]]></entry>
			</meta-data>
		</dynamic-element>
		<dynamic-element name="company_philosophy" type="option" value="philosophy">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[Company Philosophy]]></entry>
			</meta-data>
		</dynamic-element>
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Select]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" indexType="keyword" multiple="false" name="select3229" readOnly="false" required="false" showLabel="true" type="select">
		<dynamic-element name="your_trusted_advisor" type="option" value="advisor">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[Your Trusted Advisor]]></entry>
			</meta-data>
		</dynamic-element>
		<dynamic-element name="10_years_of_customer_solutions" type="option" value="solutions">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[10 Years of Customer Solutions]]></entry>
			</meta-data>
		</dynamic-element>
		<dynamic-element name="making_a_difference" type="option" value="difference">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[Making a Difference]]></entry>
			</meta-data>
		</dynamic-element>
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Campaign Theme]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" indexType="keyword" multiple="false" name="select4282" readOnly="false" required="false" showLabel="true" type="select">
		<dynamic-element name="awareness" type="option" value="awareness">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[Awareness]]></entry>
			</meta-data>
		</dynamic-element>
		<dynamic-element name="lead_generation" type="option" value="leads">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[Lead Generation]]></entry>
			</meta-data>
		</dynamic-element>
		<dynamic-element name="customer_service" type="option" value="service">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[Customer Service]]></entry>
			</meta-data>
		</dynamic-element>
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Business Goal]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
</root>', N'xml', 0)
INSERT [dbo].[DDMStructure] ([uuid_], [structureId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [parentStructureId], [classNameId], [structureKey], [name], [description], [xsd], [storageType], [type_]) VALUES (N'ff7f8bc1-7d36-40c3-bc05-0f4560a7b86b', 10308, 10197, 10157, 10161, N'', CAST(N'2017-05-12 00:54:33.487' AS DateTime), CAST(N'2017-05-12 00:54:33.487' AS DateTime), 0, 10091, N'MEETING METADATA', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Name language-id="en_US">Meeting Metadata</Name></root>', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Metadata for meeting</Description></root>', N'<?xml version="1.0"?>

<root available-locales="en_US" default-locale="en_US">
	<dynamic-element dataType="date" fieldNamespace="ddm" indexType="keyword" name="ddm-date3054" readOnly="false" required="false" showLabel="true" type="ddm-date" width="25">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Date]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" indexType="keyword" name="text2217" readOnly="false" required="false" showLabel="true" type="text" width="25">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Meeting Name]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" indexType="keyword" name="text4569" readOnly="false" required="false" showLabel="true" type="text" width="25">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Time]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" indexType="keyword" name="text5638" readOnly="false" required="false" showLabel="true" type="text" width="25">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Location]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" indexType="text" name="textarea6584" readOnly="false" required="false" showLabel="true" type="textarea" width="25">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Description]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" indexType="text" name="textarea7502" readOnly="false" required="false" showLabel="true" type="textarea" width="25">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Participants]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
</root>', N'xml', 0)
INSERT [dbo].[DDMStructure] ([uuid_], [structureId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [parentStructureId], [classNameId], [structureKey], [name], [description], [xsd], [storageType], [type_]) VALUES (N'dfb7cfca-cd1e-417a-8b25-fa97c5ef8ee1', 10310, 10197, 10157, 10161, N'', CAST(N'2017-05-12 00:54:33.560' AS DateTime), CAST(N'2017-05-12 00:54:33.560' AS DateTime), 0, 10091, N'AUTO_21E1C984-C927-4808-833D-F618FB4576D3', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Name language-id="en_US">Contract</Name></root>', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Contract</Description></root>', N'<?xml version="1.0"?>

<root available-locales="en_US" default-locale="en_US">
	<dynamic-element dataType="date" fieldNamespace="ddm" indexType="keyword" name="ddm-date18949" readOnly="false" required="false" showLabel="true" type="ddm-date" width="25">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Effective Date]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="date" fieldNamespace="ddm" indexType="keyword" name="ddm-date20127" readOnly="false" required="false" showLabel="true" type="ddm-date" width="25">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Expiration Date]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" indexType="keyword" multiple="false" name="select10264" readOnly="false" required="false" showLabel="true" type="select">
		<dynamic-element name="nda" type="option" value="NDA">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[NDA]]></entry>
			</meta-data>
		</dynamic-element>
		<dynamic-element name="msa" type="option" value="MSA">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[MSA]]></entry>
			</meta-data>
		</dynamic-element>
		<dynamic-element name="license_agreement" type="option" value="License">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[License Agreement]]></entry>
			</meta-data>
		</dynamic-element>
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Contract Type]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" indexType="keyword" multiple="false" name="select4893" readOnly="false" required="false" showLabel="true" type="select">
		<dynamic-element name="draft" type="option" value="Draft">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[Draft]]></entry>
			</meta-data>
		</dynamic-element>
		<dynamic-element name="in_review" type="option" value="Review">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[In Review]]></entry>
			</meta-data>
		</dynamic-element>
		<dynamic-element name="suspended" type="option" value="Suspended">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[Suspended]]></entry>
			</meta-data>
		</dynamic-element>
		<dynamic-element name="signed" type="option" value="Signed">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[Signed]]></entry>
			</meta-data>
		</dynamic-element>
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Status]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" indexType="keyword" name="text14822" readOnly="false" required="false" showLabel="true" type="text" width="25">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Legal Reviewer]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" indexType="keyword" name="text17700" readOnly="false" required="false" showLabel="true" type="text" width="25">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Signing Authority]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" indexType="keyword" name="text2087" readOnly="false" required="false" showLabel="true" type="text" width="25">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Deal Name]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
</root>', N'xml', 1)
INSERT [dbo].[DDMStructure] ([uuid_], [structureId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [parentStructureId], [classNameId], [structureKey], [name], [description], [xsd], [storageType], [type_]) VALUES (N'1e45b5f2-b50b-4fd7-8e16-7f89028966b2', 10312, 10197, 10157, 10161, N'', CAST(N'2017-05-12 00:54:33.667' AS DateTime), CAST(N'2017-05-12 00:54:33.667' AS DateTime), 0, 10091, N'AUTO_AE9EC8E7-B18D-4EB3-9F79-BC0E5BC5A8D2', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Name language-id="en_US">Marketing Banner</Name></root>', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Marketing Banner</Description></root>', N'<?xml version="1.0"?>

<root available-locales="en_US" default-locale="en_US">
	<dynamic-element dataType="string" indexType="keyword" name="radio5547" readOnly="false" required="false" showLabel="true" type="radio">
		<dynamic-element name="yes" type="option" value="yes">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[Yes]]></entry>
			</meta-data>
		</dynamic-element>
		<dynamic-element name="no" type="option" value="no">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[No]]></entry>
			</meta-data>
		</dynamic-element>
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Needs Legal Review]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" indexType="keyword" name="text2033" readOnly="false" required="false" showLabel="true" type="text" width="25">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Banner Name]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" indexType="text" name="textarea2873" readOnly="false" required="false" showLabel="true" type="textarea" width="25">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Description]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
</root>', N'xml', 1)
INSERT [dbo].[DDMStructure] ([uuid_], [structureId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [parentStructureId], [classNameId], [structureKey], [name], [description], [xsd], [storageType], [type_]) VALUES (N'f96832cb-e6a8-4c9f-9560-5ad42db7bbe4', 10314, 10197, 10157, 10161, N'', CAST(N'2017-05-12 00:54:33.720' AS DateTime), CAST(N'2017-05-12 00:54:33.720' AS DateTime), 0, 10091, N'AUTO_BE4F0B6C-35DF-480B-B908-D03B6EEC45B5', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Name language-id="en_US">Online Training</Name></root>', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Online Training</Description></root>', N'<?xml version="1.0"?>

<root available-locales="en_US" default-locale="en_US">
	<dynamic-element dataType="string" indexType="keyword" name="text2082" readOnly="false" required="false" showLabel="true" type="text" width="25">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Lesson Title]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" indexType="keyword" name="text2979" readOnly="false" required="false" showLabel="true" type="text" width="25">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Author]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
</root>', N'xml', 1)
INSERT [dbo].[DDMStructure] ([uuid_], [structureId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [parentStructureId], [classNameId], [structureKey], [name], [description], [xsd], [storageType], [type_]) VALUES (N'6a04a429-be9d-4233-a87f-56026c8b1abf', 10316, 10197, 10157, 10161, N'', CAST(N'2017-05-12 00:54:33.787' AS DateTime), CAST(N'2017-05-12 00:54:33.787' AS DateTime), 0, 10091, N'AUTO_3B9A5B2D-ABE4-4836-BF5C-1214768BEE10', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Name language-id="en_US">Sales Presentation</Name></root>', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Sales Presentation</Description></root>', N'<?xml version="1.0"?>

<root available-locales="en_US" default-locale="en_US">
	<dynamic-element dataType="string" indexType="keyword" multiple="false" name="select2890" readOnly="false" required="false" showLabel="true" type="select">
		<dynamic-element name="home_edition" type="option" value="HE">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[Home Edition]]></entry>
			</meta-data>
		</dynamic-element>
		<dynamic-element name="business_edition" type="option" value="BE">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[Business Edition]]></entry>
			</meta-data>
		</dynamic-element>
		<dynamic-element name="enterprise_edition" type="option" value="EE">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[Enterprise Edition]]></entry>
			</meta-data>
		</dynamic-element>
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Product]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" indexType="keyword" multiple="false" name="select3864" readOnly="false" required="false" showLabel="true" type="select">
		<dynamic-element name="1_0" type="option" value="1">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[1.0]]></entry>
			</meta-data>
		</dynamic-element>
		<dynamic-element name="2_0" type="option" value="2">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[2.0]]></entry>
			</meta-data>
		</dynamic-element>
		<dynamic-element name="3_0" type="option" value="3">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[3.0]]></entry>
			</meta-data>
		</dynamic-element>
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Version]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" indexType="keyword" multiple="true" name="select4831" readOnly="false" required="false" showLabel="true" type="select">
		<dynamic-element name="website" type="option" value="website">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[Website]]></entry>
			</meta-data>
		</dynamic-element>
		<dynamic-element name="collaboration" type="option" value="collaboration">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[Collaboration]]></entry>
			</meta-data>
		</dynamic-element>
		<dynamic-element name="intranet" type="option" value="intranet">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[Intranet]]></entry>
			</meta-data>
		</dynamic-element>
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Areas of Interest]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" indexType="keyword" multiple="true" name="select5929" readOnly="false" required="false" showLabel="true" type="select">
		<dynamic-element name="acme" type="option" value="acme">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[ACME]]></entry>
			</meta-data>
		</dynamic-element>
		<dynamic-element name="sevencogs" type="option" value="sevencogs">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[SevenCogs]]></entry>
			</meta-data>
		</dynamic-element>
		<dynamic-element name="freeplus" type="option" value="freeplus">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[FreePlus]]></entry>
			</meta-data>
		</dynamic-element>
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Competitors]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" indexType="keyword" name="text1993" readOnly="false" required="false" showLabel="true" type="text" width="25">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Prospect Name]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
</root>', N'xml', 1)
INSERT [dbo].[DDMStructure] ([uuid_], [structureId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [parentStructureId], [classNameId], [structureKey], [name], [description], [xsd], [storageType], [type_]) VALUES (N'412b9361-7703-4868-a354-508a776d62a1', 10318, 10197, 10157, 10161, N'', CAST(N'2017-05-12 00:54:33.867' AS DateTime), CAST(N'2017-05-12 00:54:33.867' AS DateTime), 0, 10317, N'TIKARAWMETADATA', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Name language-id="en_US">TIKARAWMETADATA</Name></root>', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">TIKARAWMETADATA</Description></root>', N'<root available-locales="en_US" default-locale="en_US"><dynamic-element dataType="string" name="ClimateForcast_PROGRAM_ID" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.ClimateForcast.PROGRAM_ID]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="ClimateForcast_COMMAND_LINE" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.ClimateForcast.COMMAND_LINE]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="ClimateForcast_HISTORY" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.ClimateForcast.HISTORY]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="ClimateForcast_TABLE_ID" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.ClimateForcast.TABLE_ID]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="ClimateForcast_INSTITUTION" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.ClimateForcast.INSTITUTION]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="ClimateForcast_SOURCE" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.ClimateForcast.SOURCE]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="ClimateForcast_CONTACT" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.ClimateForcast.CONTACT]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="ClimateForcast_PROJECT_ID" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.ClimateForcast.PROJECT_ID]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="ClimateForcast_CONVENTIONS" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.ClimateForcast.CONVENTIONS]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="ClimateForcast_REFERENCES" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.ClimateForcast.REFERENCES]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="ClimateForcast_ACKNOWLEDGEMENT" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.ClimateForcast.ACKNOWLEDGEMENT]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="ClimateForcast_REALIZATION" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.ClimateForcast.REALIZATION]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="ClimateForcast_EXPERIMENT_ID" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.ClimateForcast.EXPERIMENT_ID]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="ClimateForcast_COMMENT" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.ClimateForcast.COMMENT]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="ClimateForcast_MODEL_NAME_ENGLISH" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.ClimateForcast.MODEL_NAME_ENGLISH]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="CreativeCommons_LICENSE_URL" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.CreativeCommons.LICENSE_URL]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="CreativeCommons_LICENSE_LOCATION" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.CreativeCommons.LICENSE_LOCATION]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="CreativeCommons_WORK_TYPE" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.CreativeCommons.WORK_TYPE]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="DublinCore_NAMESPACE_URI_DC" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.DublinCore.NAMESPACE_URI_DC]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="DublinCore_NAMESPACE_URI_DC_TERMS" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.DublinCore.NAMESPACE_URI_DC_TERMS]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="DublinCore_PREFIX_DC" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.DublinCore.PREFIX_DC]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="DublinCore_PREFIX_DC_TERMS" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.DublinCore.PREFIX_DC_TERMS]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="DublinCore_FORMAT" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.DublinCore.FORMAT]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="DublinCore_IDENTIFIER" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.DublinCore.IDENTIFIER]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="DublinCore_MODIFIED" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.DublinCore.MODIFIED]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="DublinCore_CONTRIBUTOR" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.DublinCore.CONTRIBUTOR]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="DublinCore_COVERAGE" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.DublinCore.COVERAGE]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="DublinCore_CREATOR" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.DublinCore.CREATOR]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="DublinCore_CREATED" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.DublinCore.CREATED]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="DublinCore_DATE" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.DublinCore.DATE]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="DublinCore_DESCRIPTION" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.DublinCore.DESCRIPTION]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="DublinCore_LANGUAGE" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.DublinCore.LANGUAGE]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="DublinCore_PUBLISHER" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.DublinCore.PUBLISHER]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="DublinCore_RELATION" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.DublinCore.RELATION]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="DublinCore_RIGHTS" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.DublinCore.RIGHTS]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="DublinCore_SOURCE" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.DublinCore.SOURCE]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="DublinCore_SUBJECT" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.DublinCore.SUBJECT]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="DublinCore_TITLE" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.DublinCore.TITLE]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="DublinCore_TYPE" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.DublinCore.TYPE]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="Geographic_LATITUDE" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.Geographic.LATITUDE]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="Geographic_LONGITUDE" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.Geographic.LONGITUDE]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="Geographic_ALTITUDE" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.Geographic.ALTITUDE]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="HttpHeaders_CONTENT_ENCODING" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.HttpHeaders.CONTENT_ENCODING]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="HttpHeaders_CONTENT_LANGUAGE" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.HttpHeaders.CONTENT_LANGUAGE]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="HttpHeaders_CONTENT_LENGTH" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.HttpHeaders.CONTENT_LENGTH]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="HttpHeaders_CONTENT_LOCATION" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.HttpHeaders.CONTENT_LOCATION]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="HttpHeaders_CONTENT_DISPOSITION" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.HttpHeaders.CONTENT_DISPOSITION]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="HttpHeaders_CONTENT_MD5" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.HttpHeaders.CONTENT_MD5]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="HttpHeaders_CONTENT_TYPE" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.HttpHeaders.CONTENT_TYPE]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="HttpHeaders_LAST_MODIFIED" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.HttpHeaders.LAST_MODIFIED]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="HttpHeaders_LOCATION" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.HttpHeaders.LOCATION]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="Message_MESSAGE_RECIPIENT_ADDRESS" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.Message.MESSAGE_RECIPIENT_ADDRESS]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="Message_MESSAGE_FROM" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.Message.MESSAGE_FROM]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="Message_MESSAGE_TO" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.Message.MESSAGE_TO]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="Message_MESSAGE_CC" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.Message.MESSAGE_CC]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="Message_MESSAGE_BCC" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.Message.MESSAGE_BCC]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="MSOffice_KEYWORDS" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.MSOffice.KEYWORDS]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="MSOffice_COMMENTS" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.MSOffice.COMMENTS]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="MSOffice_LAST_AUTHOR" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.MSOffice.LAST_AUTHOR]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="MSOffice_AUTHOR" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.MSOffice.AUTHOR]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="MSOffice_APPLICATION_NAME" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.MSOffice.APPLICATION_NAME]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="MSOffice_REVISION_NUMBER" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.MSOffice.REVISION_NUMBER]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="MSOffice_TEMPLATE" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.MSOffice.TEMPLATE]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="MSOffice_TOTAL_TIME" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.MSOffice.TOTAL_TIME]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="MSOffice_PRESENTATION_FORMAT" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.MSOffice.PRESENTATION_FORMAT]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="MSOffice_NOTES" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.MSOffice.NOTES]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="MSOffice_MANAGER" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.MSOffice.MANAGER]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="MSOffice_APPLICATION_VERSION" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.MSOffice.APPLICATION_VERSION]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="MSOffice_VERSION" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.MSOffice.VERSION]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="MSOffice_CONTENT_STATUS" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.MSOffice.CONTENT_STATUS]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="MSOffice_CATEGORY" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.MSOffice.CATEGORY]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="MSOffice_COMPANY" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.MSOffice.COMPANY]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="MSOffice_SECURITY" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.MSOffice.SECURITY]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="MSOffice_SLIDE_COUNT" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.MSOffice.SLIDE_COUNT]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="MSOffice_PAGE_COUNT" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.MSOffice.PAGE_COUNT]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="MSOffice_PARAGRAPH_COUNT" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.MSOffice.PARAGRAPH_COUNT]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="MSOffice_LINE_COUNT" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.MSOffice.LINE_COUNT]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="MSOffice_WORD_COUNT" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.MSOffice.WORD_COUNT]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="MSOffice_CHARACTER_COUNT" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.MSOffice.CHARACTER_COUNT]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="MSOffice_CHARACTER_COUNT_WITH_SPACES" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.MSOffice.CHARACTER_COUNT_WITH_SPACES]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="MSOffice_TABLE_COUNT" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.MSOffice.TABLE_COUNT]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="MSOffice_IMAGE_COUNT" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.MSOffice.IMAGE_COUNT]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="MSOffice_OBJECT_COUNT" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.MSOffice.OBJECT_COUNT]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="MSOffice_EDIT_TIME" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.MSOffice.EDIT_TIME]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="MSOffice_CREATION_DATE" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.MSOffice.CREATION_DATE]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="MSOffice_LAST_SAVED" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.MSOffice.LAST_SAVED]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="MSOffice_LAST_PRINTED" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.MSOffice.LAST_PRINTED]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="MSOffice_USER_DEFINED_METADATA_NAME_PREFIX" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.MSOffice.USER_DEFINED_METADATA_NAME_PREFIX]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="TIFF_BITS_PER_SAMPLE" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.TIFF.BITS_PER_SAMPLE]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="TIFF_IMAGE_LENGTH" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.TIFF.IMAGE_LENGTH]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="TIFF_IMAGE_WIDTH" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.TIFF.IMAGE_WIDTH]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="TIFF_SAMPLES_PER_PIXEL" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.TIFF.SAMPLES_PER_PIXEL]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="TIFF_FLASH_FIRED" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.TIFF.FLASH_FIRED]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="TIFF_EXPOSURE_TIME" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.TIFF.EXPOSURE_TIME]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="TIFF_F_NUMBER" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.TIFF.F_NUMBER]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="TIFF_FOCAL_LENGTH" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.TIFF.FOCAL_LENGTH]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="TIFF_ISO_SPEED_RATINGS" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.TIFF.ISO_SPEED_RATINGS]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="TIFF_EQUIPMENT_MAKE" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.TIFF.EQUIPMENT_MAKE]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="TIFF_EQUIPMENT_MODEL" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.TIFF.EQUIPMENT_MODEL]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="TIFF_SOFTWARE" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.TIFF.SOFTWARE]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="TIFF_ORIENTATION" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.TIFF.ORIENTATION]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="TIFF_RESOLUTION_HORIZONTAL" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.TIFF.RESOLUTION_HORIZONTAL]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="TIFF_RESOLUTION_VERTICAL" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.TIFF.RESOLUTION_VERTICAL]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="TIFF_RESOLUTION_UNIT" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.TIFF.RESOLUTION_UNIT]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="TIFF_ORIGINAL_DATE" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.TIFF.ORIGINAL_DATE]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="TikaMetadataKeys_RESOURCE_NAME_KEY" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.TikaMetadataKeys.RESOURCE_NAME_KEY]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="TikaMetadataKeys_PROTECTED" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.TikaMetadataKeys.PROTECTED]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="TikaMetadataKeys_EMBEDDED_RELATIONSHIP_ID" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.TikaMetadataKeys.EMBEDDED_RELATIONSHIP_ID]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="TikaMimeKeys_TIKA_MIME_FILE" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.TikaMimeKeys.TIKA_MIME_FILE]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="TikaMimeKeys_MIME_TYPE_MAGIC" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.TikaMimeKeys.MIME_TYPE_MAGIC]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="XMPDM_DURATION" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.XMPDM.DURATION]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="XMPDM_ABS_PEAK_AUDIO_FILE_PATH" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.XMPDM.ABS_PEAK_AUDIO_FILE_PATH]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="XMPDM_ALBUM" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.XMPDM.ALBUM]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="XMPDM_ALT_TAPE_NAME" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.XMPDM.ALT_TAPE_NAME]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="XMPDM_ARTIST" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.XMPDM.ARTIST]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="XMPDM_AUDIO_MOD_DATE" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.XMPDM.AUDIO_MOD_DATE]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="XMPDM_AUDIO_SAMPLE_RATE" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.XMPDM.AUDIO_SAMPLE_RATE]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="XMPDM_AUDIO_SAMPLE_TYPE" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.XMPDM.AUDIO_SAMPLE_TYPE]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="XMPDM_AUDIO_CHANNEL_TYPE" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.XMPDM.AUDIO_CHANNEL_TYPE]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="XMPDM_AUDIO_COMPRESSOR" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.XMPDM.AUDIO_COMPRESSOR]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="XMPDM_COMPOSER" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.XMPDM.COMPOSER]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="XMPDM_COPYRIGHT" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.XMPDM.COPYRIGHT]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="XMPDM_ENGINEER" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.XMPDM.ENGINEER]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="XMPDM_FILE_DATA_RATE" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.XMPDM.FILE_DATA_RATE]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="XMPDM_GENRE" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.XMPDM.GENRE]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="XMPDM_INSTRUMENT" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.XMPDM.INSTRUMENT]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="XMPDM_KEY" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.XMPDM.KEY]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="XMPDM_LOG_COMMENT" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.XMPDM.LOG_COMMENT]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="XMPDM_LOOP" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.XMPDM.LOOP]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="XMPDM_NUMBER_OF_BEATS" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.XMPDM.NUMBER_OF_BEATS]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="XMPDM_METADATA_MOD_DATE" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.XMPDM.METADATA_MOD_DATE]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="XMPDM_PULL_DOWN" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.XMPDM.PULL_DOWN]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="XMPDM_RELATIVE_PEAK_AUDIO_FILE_PATH" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.XMPDM.RELATIVE_PEAK_AUDIO_FILE_PATH]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="XMPDM_RELEASE_DATE" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.XMPDM.RELEASE_DATE]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="XMPDM_SCALE_TYPE" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.XMPDM.SCALE_TYPE]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="XMPDM_SCENE" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.XMPDM.SCENE]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="XMPDM_SHOT_DATE" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.XMPDM.SHOT_DATE]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="XMPDM_SHOT_LOCATION" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.XMPDM.SHOT_LOCATION]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="XMPDM_SHOT_NAME" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.XMPDM.SHOT_NAME]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="XMPDM_SPEAKER_PLACEMENT" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.XMPDM.SPEAKER_PLACEMENT]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="XMPDM_STRETCH_MODE" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.XMPDM.STRETCH_MODE]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="XMPDM_TAPE_NAME" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.XMPDM.TAPE_NAME]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="XMPDM_TEMPO" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.XMPDM.TEMPO]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="XMPDM_TIME_SIGNATURE" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.XMPDM.TIME_SIGNATURE]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="XMPDM_TRACK_NUMBER" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.XMPDM.TRACK_NUMBER]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="XMPDM_VIDEO_ALPHA_MODE" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.XMPDM.VIDEO_ALPHA_MODE]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="XMPDM_VIDEO_ALPHA_UNITY_IS_TRANSPARENT" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.XMPDM.VIDEO_ALPHA_UNITY_IS_TRANSPARENT]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="XMPDM_VIDEO_COLOR_SPACE" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.XMPDM.VIDEO_COLOR_SPACE]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="XMPDM_VIDEO_COMPRESSOR" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.XMPDM.VIDEO_COMPRESSOR]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="XMPDM_VIDEO_FIELD_ORDER" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.XMPDM.VIDEO_FIELD_ORDER]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="XMPDM_VIDEO_FRAME_RATE" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.XMPDM.VIDEO_FRAME_RATE]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="XMPDM_VIDEO_MOD_DATE" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.XMPDM.VIDEO_MOD_DATE]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="XMPDM_VIDEO_PIXEL_DEPTH" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.XMPDM.VIDEO_PIXEL_DEPTH]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element><dynamic-element dataType="string" name="XMPDM_VIDEO_PIXEL_ASPECT_RATIO" required="false" showLabel="true" type="text"><meta-data locale="en_US"><entry name="label"><![CDATA[metadata.XMPDM.VIDEO_PIXEL_ASPECT_RATIO]]></entry><entry name="predefinedValue"><![CDATA[]]></entry></meta-data></dynamic-element></root>', N'xml', 0)
INSERT [dbo].[DDMStructure] ([uuid_], [structureId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [parentStructureId], [classNameId], [structureKey], [name], [description], [xsd], [storageType], [type_]) VALUES (N'275e7208-24e1-46a2-b84c-14ce8e4de299', 10415, 10184, 10157, 10161, N'', CAST(N'2017-05-12 00:54:38.523' AS DateTime), CAST(N'2017-05-12 00:54:38.523' AS DateTime), 0, 10098, N'CONTACTS', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Name language-id="en_US">Contacts</Name></root>', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Contacts</Description></root>', N'<?xml version="1.0"?>

<root available-locales="en_US" default-locale="en_US">
	<dynamic-element dataType="string" name="company" required="false" showLabel="true" type="text" width="50">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Company]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" name="email" required="false" showLabel="true" type="text" width="50">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Email]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" name="firstName" required="false" showLabel="true" type="text" width="50">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[First Name]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" multiple="false" name="imService" required="false" showLabel="true" type="select">
		<dynamic-element name="aol" type="option" value="aol">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[AOL]]></entry>
			</meta-data>
		</dynamic-element>
		<dynamic-element name="yahoo" type="option" value="yahoo">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[Yahoo]]></entry>
			</meta-data>
		</dynamic-element>
		<dynamic-element name="gtalk" type="option" value="gtalk">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[GTalk]]></entry>
			</meta-data>
		</dynamic-element>
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Instant Messenger Service]]></entry>
			<entry name="predefinedValue"><![CDATA[["gtalk"]]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" name="imUserName" required="false" showLabel="true" type="text" width="50">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Instant Messenger]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" name="jobTitle" required="false" showLabel="true" type="text" width="50">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Job Title]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" name="lastName" required="false" showLabel="true" type="text" width="50">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Last Name]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" name="notes" required="false" showLabel="true" type="textarea" width="100">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Notes]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" name="phoneMobile" required="false" showLabel="true" type="text" width="50">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Phone (Mobile)]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" name="phoneOffice" required="false" showLabel="true" type="text" width="50">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Phone (Office)]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
</root>', N'xml', 0)
INSERT [dbo].[DDMStructure] ([uuid_], [structureId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [parentStructureId], [classNameId], [structureKey], [name], [description], [xsd], [storageType], [type_]) VALUES (N'fc4caf93-6521-49f9-ae22-a68e57d0e049', 10416, 10184, 10157, 10161, N'', CAST(N'2017-05-12 00:54:38.610' AS DateTime), CAST(N'2017-05-12 00:54:38.610' AS DateTime), 0, 10098, N'EVENTS', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Name language-id="en_US">Events</Name></root>', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Events</Description></root>', N'<?xml version="1.0"?>

<root available-locales="en_US" default-locale="en_US">
	<dynamic-element dataType="document-library" fieldNamespace="ddm" name="attachment" required="false" showLabel="true" type="ddm-documentlibrary">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Attachment]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[Upload documents no larger than 3,000k.]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="double" fieldNamespace="ddm" name="cost" required="false" showLabel="true" type="ddm-number" width="25">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Cost]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" name="description" required="false" showLabel="true" type="textarea" width="100">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Description]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="date" fieldNamespace="ddm" name="eventDate" required="false" showLabel="true" type="ddm-date" width="25">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Date]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" name="eventName" required="false" showLabel="true" type="text" width="50">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Event Name]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" name="eventTime" required="false" showLabel="true" type="text" width="25">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Time]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" name="location" required="false" showLabel="true" type="text" width="50">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Location]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
</root>', N'xml', 0)
INSERT [dbo].[DDMStructure] ([uuid_], [structureId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [parentStructureId], [classNameId], [structureKey], [name], [description], [xsd], [storageType], [type_]) VALUES (N'de7cf410-1330-4270-87e8-b12db67dcdf4', 10417, 10184, 10157, 10161, N'', CAST(N'2017-05-12 00:54:38.627' AS DateTime), CAST(N'2017-05-12 00:54:38.627' AS DateTime), 0, 10098, N'INVENTORY', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Name language-id="en_US">Inventory</Name></root>', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Inventory</Description></root>', N'<?xml version="1.0"?>

<root available-locales="en_US" default-locale="en_US">
	<dynamic-element dataType="string" name="description" required="false" showLabel="true" type="textarea" width="100">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Description]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="style"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" name="item" required="false" showLabel="true" type="text" width="25">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Item]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="style"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" name="location" required="false" showLabel="true" type="text" width="25">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Location]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="style"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="date" fieldNamespace="ddm" name="purchaseDate" required="false" showLabel="true" type="ddm-date" width="25">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Purchase Date]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="style"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="double" fieldNamespace="ddm" name="purchasePrice" required="false" showLabel="true" type="ddm-number" width="25">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Purchase Price]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="double" fieldNamespace="ddm" name="quantity" required="false" showLabel="true" type="ddm-number" width="25">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Quantity]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
</root>', N'xml', 0)
INSERT [dbo].[DDMStructure] ([uuid_], [structureId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [parentStructureId], [classNameId], [structureKey], [name], [description], [xsd], [storageType], [type_]) VALUES (N'80943ff1-076a-4010-9d8a-235a87aa161f', 10418, 10184, 10157, 10161, N'', CAST(N'2017-05-12 00:54:38.660' AS DateTime), CAST(N'2017-05-12 00:54:38.660' AS DateTime), 0, 10098, N'ISSUES TRACKING', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Name language-id="en_US">Issues Tracking</Name></root>', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Issue Tracking</Description></root>', N'<?xml version="1.0"?>

<root available-locales="en_US" default-locale="en_US">
	<dynamic-element dataType="string" name="assignedTo" required="false" showLabel="true" type="text" width="25">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Assigned To]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="document-library" fieldNamespace="ddm" name="attachment" required="false" showLabel="true" type="ddm-documentlibrary">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Attachment]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[Upload documents no larger than 3,000k.]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" name="comments" required="false" showLabel="true" type="textarea" width="100">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Comments]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" name="description" required="false" showLabel="true" type="textarea" width="100">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Description]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="date" fieldNamespace="ddm" name="dueDate" required="false" showLabel="true" type="ddm-date">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Due Date]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" name="issueId" required="false" showLabel="true" type="text" width="25">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Issue ID]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" multiple="false" name="severity" required="false" showLabel="true" type="select">
		<dynamic-element name="critical" type="option" value="critical">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[Critical]]></entry>
			</meta-data>
		</dynamic-element>
		<dynamic-element name="major" type="option" value="major">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[Major]]></entry>
			</meta-data>
		</dynamic-element>
		<dynamic-element name="minor" type="option" value="minor">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[Minor]]></entry>
			</meta-data>
		</dynamic-element>
		<dynamic-element name="trivial" type="option" value="trivial">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[Trivial]]></entry>
			</meta-data>
		</dynamic-element>
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Severity]]></entry>
			<entry name="predefinedValue"><![CDATA[["minor"]]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" multiple="false" name="status" required="false" showLabel="true" type="select">
		<dynamic-element name="open" type="option" value="open">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[Open]]></entry>
			</meta-data>
		</dynamic-element>
		<dynamic-element name="pending" type="option" value="pending">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[Pending]]></entry>
			</meta-data>
		</dynamic-element>
		<dynamic-element name="completed" type="option" value="completed">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[Completed]]></entry>
			</meta-data>
		</dynamic-element>
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Status]]></entry>
			<entry name="predefinedValue"><![CDATA[["open"]]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" name="title" required="false" showLabel="true" type="text" width="100">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Title]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
</root>', N'xml', 0)
INSERT [dbo].[DDMStructure] ([uuid_], [structureId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [parentStructureId], [classNameId], [structureKey], [name], [description], [xsd], [storageType], [type_]) VALUES (N'ddacc61d-99e0-4da2-ad52-68c721564304', 10419, 10184, 10157, 10161, N'', CAST(N'2017-05-12 00:54:38.680' AS DateTime), CAST(N'2017-05-12 00:54:38.680' AS DateTime), 0, 10098, N'MEETING MINUTES', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Name language-id="en_US">Meeting Minutes</Name></root>', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">Meeting Minutes</Description></root>', N'<?xml version="1.0"?>

<root available-locales="en_US" default-locale="en_US">
	<dynamic-element dataType="document-library" fieldNamespace="ddm" name="attachment" required="false" showLabel="true" type="ddm-documentlibrary">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Attachment]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[Upload documents no larger than 3,000k.]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" name="author" required="false" showLabel="true" type="text">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Author]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" name="description" required="false" showLabel="true" type="textarea" width="100">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Description]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" name="duration" required="false" showLabel="true" type="text">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Meeting Duration]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="date" fieldNamespace="ddm" name="meetingDate" required="false" showLabel="true" type="ddm-date">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Meeting Date]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" name="minutes" required="false" showLabel="true" type="textarea" width="100">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Minutes]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" name="title" required="false" showLabel="true" type="text" width="100">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Title]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
</root>', N'xml', 0)
INSERT [dbo].[DDMStructure] ([uuid_], [structureId], [groupId], [companyId], [userId], [userName], [createDate], [modifiedDate], [parentStructureId], [classNameId], [structureKey], [name], [description], [xsd], [storageType], [type_]) VALUES (N'fedb7826-89bf-47bf-9e96-6c6baef907d2', 10420, 10184, 10157, 10161, N'', CAST(N'2017-05-12 00:54:38.703' AS DateTime), CAST(N'2017-05-12 00:54:38.703' AS DateTime), 0, 10098, N'TO DO', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Name language-id="en_US">To Do</Name></root>', N'<?xml version=''1.0'' encoding=''UTF-8''?><root available-locales="en_US" default-locale="en_US"><Description language-id="en_US">To Do</Description></root>', N'<?xml version="1.0"?>

<root available-locales="en_US" default-locale="en_US">
	<dynamic-element dataType="string" name="assignedTo" required="false" showLabel="true" type="text" width="50">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Assigned To]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="document-library" fieldNamespace="ddm" name="attachment" required="false" showLabel="true" type="ddm-documentlibrary">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Attachment]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
			<entry name="tip"><![CDATA[Upload documents no larger than 3,000k.]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" name="comments" required="false" showLabel="true" type="textarea" width="100">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Comments]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" name="description" required="false" showLabel="true" type="textarea" width="100">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Description]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="date" fieldNamespace="ddm" name="endDate" required="false" showLabel="true" type="ddm-date">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[End Date]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="integer" fieldNamespace="ddm" name="percentComplete" required="false" showLabel="true" type="ddm-integer" width="25">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[% Complete]]></entry>
			<entry name="predefinedValue"><![CDATA[0]]></entry>
			<entry name="tip"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" multiple="false" name="severity" required="false" showLabel="true" type="select">
		<dynamic-element name="critical" type="option" value="critical">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[Critical]]></entry>
			</meta-data>
		</dynamic-element>
		<dynamic-element name="major" type="option" value="major">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[Major]]></entry>
			</meta-data>
		</dynamic-element>
		<dynamic-element name="minor" type="option" value="minor">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[Minor]]></entry>
			</meta-data>
		</dynamic-element>
		<dynamic-element name="trivial" type="option" value="trivial">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[Trivial]]></entry>
			</meta-data>
		</dynamic-element>
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Severity]]></entry>
			<entry name="predefinedValue"><![CDATA[["minor"]]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="date" fieldNamespace="ddm" name="startDate" required="false" showLabel="true" type="ddm-date">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Start Date]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" multiple="false" name="status" required="false" showLabel="true" type="select">
		<dynamic-element name="open" type="option" value="open">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[Open]]></entry>
			</meta-data>
		</dynamic-element>
		<dynamic-element name="pending" type="option" value="pending">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[Pending]]></entry>
			</meta-data>
		</dynamic-element>
		<dynamic-element name="completed" type="option" value="completed">
			<meta-data locale="en_US">
				<entry name="label"><![CDATA[Completed]]></entry>
			</meta-data>
		</dynamic-element>
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Status]]></entry>
			<entry name="predefinedValue"><![CDATA[["open"]]]></entry>
		</meta-data>
	</dynamic-element>
	<dynamic-element dataType="string" name="title" required="false" showLabel="true" type="text" width="50">
		<meta-data locale="en_US">
			<entry name="label"><![CDATA[Title]]></entry>
			<entry name="predefinedValue"><![CDATA[]]></entry>
		</meta-data>
	</dynamic-element>
</root>', N'xml', 0)
