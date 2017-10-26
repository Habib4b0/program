create index IX_C80EDDC0 on ITEM_IRT_IDENTIFIER (ItemIdentifier, ItemIrtQualifierId);
create index IX_34E291C0 on ITEM_IRT_IDENTIFIER (ItemSystemId);
create index IX_2663FF01 on ITEM_IRT_IDENTIFIER (ItemSystemId, ItemIrtQualifierId);
create index IX_EF2553B1 on ITEM_IRT_IDENTIFIER (ItemSystemId, ItemIrtQualifierId, ItemIdentifier);

create index IX_7951858A on ITEM_IRT_IDENTIFIER_HISTORY (ItemPricingQualifierId);
create index IX_E560C5F5 on ITEM_IRT_IDENTIFIER_HISTORY (ItemSystemId);
create index IX_596B06B6 on ITEM_IRT_IDENTIFIER_HISTORY (ItemSystemId, ItemIrtQualifierId);
create index IX_E1585166 on ITEM_IRT_IDENTIFIER_HISTORY (ItemSystemId, ItemIrtQualifierId, ItemIdentifier);
create index IX_4BB1F63B on ITEM_IRT_IDENTIFIER_HISTORY (ItemSystemId, ItemPricingQualifierId);

create index IX_6126429B on ITEM_IRT_QUALIFIER (ModuleName);
create index IX_4FFA27D7 on ITEM_IRT_QUALIFIER (QualifierCode);

create index IX_3CC41EAC on ITEM_MASTER (ItemDesc);
create index IX_1268F6 on ITEM_MASTER (ItemId);
create index IX_4D998426 on ITEM_MASTER (ItemName);
create index IX_14D81C on ITEM_MASTER (ItemNo);
create index IX_2E41F58D on ITEM_MASTER (ItemStatus);
create index IX_592A2915 on ITEM_MASTER (ItemType);
create index IX_8C546CB4 on ITEM_MASTER (ManufacturerId);

create index IX_CD661AEC on ITEM_PRICING_HISTORY (ItemSystemId, ItemPricingQualifierId);

create index IX_3117CB29 on ITEM_PRICING_QUALIFIER (ItemPricingCodeQualifier);

create index IX_B1367EF8 on ItemIrtIdentifier (ItemIdentifier, ItemIrtQualifierId);
create index IX_98B6ECF8 on ItemIrtIdentifier (ItemSystemId);
create index IX_78F28E39 on ItemIrtIdentifier (ItemSystemId, ItemIrtQualifierId);
create index IX_EDDE72E9 on ItemIrtIdentifier (ItemSystemId, ItemIrtQualifierId, ItemIdentifier);

create index IX_4521D08B on ItemIrtIdentifierHistory (ItemSystemId, ItemIrtQualifierId);
create index IX_1DB2D13B on ItemIrtIdentifierHistory (ItemSystemId, ItemIrtQualifierId, ItemIdentifier);

create index IX_D2FF685F on ItemPricing (ItemPricingQualifierId);
create index IX_814F3F0A on ItemPricing (ItemSystemId);
create index IX_29FBC050 on ItemPricing (ItemSystemId, ItemPricingQualifierId);

create index IX_94D7B93E on ItemPricingHistory (ItemSystemId, ItemPricingQualifierId);

create index IX_29CBCBFB on ItemPricingQualifier (ItemPricingCodeQualifier);

create index IX_434FDBB on Qualifier (ModuleName);
create index IX_7F3C18B7 on Qualifier (QualifierCode);

create index IX_B5824A3A on com_ItemIrtIdentifier (ItemIdentifier, ItemIrtQualifierId);
create index IX_131A95BA on com_ItemIrtIdentifier (ItemSystemId);
create index IX_D0DC41FB on com_ItemIrtIdentifier (ItemSystemId, ItemIrtQualifierId);
create index IX_3ABC62AB on com_ItemIrtIdentifier (ItemSystemId, ItemIrtQualifierId, ItemIdentifier);

create index IX_101CBE89 on com_ItemIrtIdentifierHistory (ItemSystemId, ItemIrtQualifierId);
create index IX_FFBE0339 on com_ItemIrtIdentifierHistory (ItemSystemId, ItemIrtQualifierId, ItemIdentifier);

create index IX_ADFCE021 on com_ItemPricing (ItemPricingQualifierId);
create index IX_D6C9214C on com_ItemPricing (ItemSystemId);
create index IX_9CE37C92 on com_ItemPricing (ItemSystemId, ItemPricingQualifierId);

create index IX_85120EBC on com_ItemPricingHistory (ItemSystemId, ItemPricingQualifierId);

create index IX_E72D46F9 on com_ItemPricingQualifier (ItemPricingCodeQualifier);

create index IX_C8EE50FD on com_Qualifier (ModuleName);
create index IX_79BBE635 on com_Qualifier (QualifierCode);

create index IX_B4A4FE5C on global_ItemIrtIdentifier (ItemIdentifier, ItemIrtQualifierId);
create index IX_496B0F5C on global_ItemIrtIdentifier (ItemSystemId);
create index IX_401969D on global_ItemIrtIdentifier (ItemSystemId, ItemIrtQualifierId);
create index IX_4C38334D on global_ItemIrtIdentifier (ItemSystemId, ItemIrtQualifierId, ItemIdentifier);

create index IX_988F4FA7 on global_ItemIrtIdentifierHistory (ItemSystemId, ItemIrtQualifierId);
create index IX_ED69857 on global_ItemIrtIdentifierHistory (ItemSystemId, ItemIrtQualifierId, ItemIdentifier);

create index IX_EE53B8C3 on global_ItemPricing (ItemPricingQualifierId);
create index IX_4AD5FC6E on global_ItemPricing (ItemSystemId);
create index IX_8D3991B4 on global_ItemPricing (ItemSystemId, ItemPricingQualifierId);

create index IX_EC924F5A on global_ItemPricingHistory (ItemSystemId, ItemPricingQualifierId);

create index IX_F70A1517 on global_ItemPricingQualifier (ItemPricingCodeQualifier);

create index IX_F5A0D1F on global_Qualifier (ModuleName);
create index IX_6CF61ED3 on global_Qualifier (QualifierCode);