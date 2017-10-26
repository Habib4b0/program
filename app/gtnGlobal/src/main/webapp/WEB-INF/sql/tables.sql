create table ITEM_IRT_IDENTIFIER (
	ItemSystemId INTEGER not null,
	ItemIdentifier VARCHAR(75) not null,
	IdentifierStatus INTEGER,
	ItemIrtQualifierId INTEGER not null,
	StartDate DATE null,
	EndDate DATE null,
	EntityCode INTEGER,
	CreatedBy VARCHAR(75) null,
	CreatedDate DATE null,
	ModifiedBy VARCHAR(75) null,
	ModifiedDate DATE null,
	BatchId VARCHAR(75) null,
	primary key (ItemSystemId, ItemIdentifier, ItemIrtQualifierId)
);

create table ITEM_IRT_IDENTIFIER_HISTORY (
	ItemSystemId INTEGER not null,
	ItemUom INTEGER,
	ItemPricingQualifierId INTEGER not null,
	ItemPrice DOUBLE,
	PricingCodeStatus INTEGER,
	StartDate DATE null,
	EndDate DATE null,
	EntityCode INTEGER,
	CreatedBy VARCHAR(75) null,
	CreatedDate DATE null,
	ModifiedBy VARCHAR(75) null,
	ModifiedDate DATE null,
	BatchId VARCHAR(75) null,
	primary key (ItemSystemId, ItemPricingQualifierId)
);

create table ITEM_IRT_QUALIFIER (
	QualifierId INTEGER not null primary key IDENTITY,
	QualifierCode VARCHAR(75) null,
	QualifierName VARCHAR(75) null,
	ModuleName VARCHAR(75) null,
	ExpirationRequired VARCHAR(75) null,
	SpecificTpRequired VARCHAR(75) null,
	CreatedDate DATE null,
	ModifiedDate DATE null,
	CreatedBy VARCHAR(75) null,
	ModifiedBy VARCHAR(75) null,
	BatchId VARCHAR(75) null
);

create table ITEM_MASTER (
	ItemSystemId INTEGER not null primary key IDENTITY,
	ItemId VARCHAR(75) null,
	ItemNo VARCHAR(75) null,
	ItemCode VARCHAR(75) null,
	ItemName VARCHAR(75) null,
	ItemDesc VARCHAR(75) null,
	PackageSize INTEGER,
	PackageSizeIntroDate DATE null,
	Upps VARCHAR(75) null,
	ItemStartDate DATE null,
	ItemEndDate DATE null,
	ItemStatus INTEGER,
	ManufacturerId INTEGER,
	LabelerCode VARCHAR(75) null,
	OrganizationKey VARCHAR(75) null,
	AcquisitionDate DATE null,
	AuthorizedGeneric VARCHAR(75) null,
	AuthorizedGenericStartDate DATE null,
	AuthorizedGenericEndDate DATE null,
	Form INTEGER,
	Brand VARCHAR(75) null,
	Strength INTEGER,
	TherapeuticClass VARCHAR(75) null,
	FirstSaleDate DATE null,
	ItemTypeIndication VARCHAR(75) null,
	ItemClass INTEGER,
	ItemType INTEGER,
	MarketTerminationDate DATE null,
	NewFormulationIndicator VARCHAR(75) null,
	NewFormulation VARCHAR(75) null,
	NewFormulationStartDate DATE null,
	NewFormulationEndDate DATE null,
	PediatricExclusiveIndicator VARCHAR(75) null,
	PediatricExclusiveStartDate DATE null,
	PediatricExclusiveEndDate DATE null,
	ClottingFactorIndicator VARCHAR(75) null,
	ClottingFactorStartDate DATE null,
	ClottingFactorEndDate DATE null,
	PrimaryUom INTEGER,
	SecondaryUom INTEGER,
	ShelfLife VARCHAR(75) null,
	ShelfLifeType VARCHAR(75) null,
	DualPricingIndicator VARCHAR(75) null,
	ItemFamilyId INTEGER,
	Udc1 INTEGER,
	Udc2 INTEGER,
	Udc3 INTEGER,
	Udc4 INTEGER,
	Udc5 INTEGER,
	Udc6 INTEGER,
	AcquiredAmp INTEGER,
	AcquiredBamp INTEGER,
	ObraBamp INTEGER,
	Dra INTEGER,
	CreatedBy VARCHAR(75) null,
	CreatedDate DATE null,
	ModifiedBy VARCHAR(75) null,
	ModifiedDate DATE null,
	BatchId VARCHAR(75) null,
	DoesPerUnit VARCHAR(75) null,
	InBoundStatus VARCHAR(75) null,
	RecordLockStatus VARCHAR(75) null,
	DiscontinuationDate DATE null,
	LastLotExpirationDate DATE null,
	DivestitureDate DATE null,
	NonFedaralExpirationDate DATE null,
	Ndc9 VARCHAR(75) null,
	Ndc8 VARCHAR(75) null,
	DisplayBrand VARCHAR(75) null,
	BrandId VARCHAR(75) null,
	ItemCategory VARCHAR(75) null,
	BaselineAmp VARCHAR(75) null,
	BasecpiPeriod VARCHAR(75) null,
	Basecpi VARCHAR(75) null
);

create table ITEM_PRICING_HISTORY (
	ItemPricingHistoryId INTEGER not null primary key IDENTITY,
	ItemSystemId INTEGER,
	ItemUom INTEGER,
	ItemPricingQualifierId INTEGER,
	ItemPrice DOUBLE,
	PricingCodeStatus INTEGER,
	StartDate DATE null,
	EndDate DATE null,
	EntityCode INTEGER,
	CreatedBy VARCHAR(75) null,
	CreatedDate DATE null,
	ModifiedBy VARCHAR(75) null,
	ModifiedDate DATE null,
	BatchId VARCHAR(75) null,
	LastModifiedDate DATE null,
	ActionFlag VARCHAR(75) null
);

create table ITEM_PRICING_QUALIFIER (
	ItemPricingQualifierId INTEGER not null primary key IDENTITY,
	ItemPricingCodeQualifier VARCHAR(75) null,
	ItemPricingCodeQualifierName VARCHAR(75) null,
	ExpirationRequired VARCHAR(75) null,
	SpecificTpRequired VARCHAR(75) null,
	CreatedBy VARCHAR(75) null,
	CreatedDate DATE null,
	ModifiedBy VARCHAR(75) null,
	ModifiedDate DATE null,
	BatchId VARCHAR(75) null
);

create table ItemIrtIdentifier (
	ItemSystemId INTEGER not null,
	ItemIdentifier VARCHAR(75) not null,
	IdentifierStatus INTEGER,
	ItemIrtQualifierId INTEGER not null,
	StartDate DATE null,
	EndDate DATE null,
	EntityCode INTEGER,
	CreatedBy VARCHAR(75) null,
	CreatedDate DATE null,
	ModifiedBy VARCHAR(75) null,
	ModifiedDate DATE null,
	BatchId VARCHAR(75) null,
	primary key (ItemSystemId, ItemIdentifier, ItemIrtQualifierId)
);

create table ItemIrtIdentifierHistory (
	ItemIrtIdentifierHistoryId INTEGER not null primary key IDENTITY,
	ItemSystemId INTEGER,
	ItemIdentifier VARCHAR(75) null,
	IdentifierStatus INTEGER,
	ItemIrtQualifierId INTEGER,
	StartDate DATE null,
	EndDate DATE null,
	EntityCode INTEGER,
	CreatedBy VARCHAR(75) null,
	CreatedDate DATE null,
	ModifiedBy VARCHAR(75) null,
	ModifiedDate DATE null,
	BatchId VARCHAR(75) null,
	LastModifiedDate DATE null,
	ActionFlag VARCHAR(75) null
);

create table ItemPricing (
	ItemSystemId INTEGER not null,
	ItemUom INTEGER,
	ItemPricingQualifierId INTEGER not null,
	ItemPrice DOUBLE,
	PricingCodeStatus INTEGER,
	StartDate DATE null,
	EndDate DATE null,
	EntityCode INTEGER,
	CreatedBy VARCHAR(75) null,
	CreatedDate DATE null,
	ModifiedBy VARCHAR(75) null,
	ModifiedDate DATE null,
	BatchId VARCHAR(75) null,
	primary key (ItemSystemId, ItemPricingQualifierId)
);

create table ItemPricingHistory (
	ItemPricingHistoryId INTEGER not null primary key IDENTITY,
	ItemSystemId INTEGER,
	ItemUom INTEGER,
	ItemPricingQualifierId INTEGER,
	ItemPrice DOUBLE,
	PricingCodeStatus INTEGER,
	StartDate DATE null,
	EndDate DATE null,
	EntityCode INTEGER,
	CreatedBy VARCHAR(75) null,
	CreatedDate DATE null,
	ModifiedBy VARCHAR(75) null,
	ModifiedDate DATE null,
	BatchId VARCHAR(75) null,
	LastModifiedDate DATE null,
	ActionFlag VARCHAR(75) null
);

create table ItemPricingQualifier (
	ItemPricingQualifierId INTEGER not null primary key IDENTITY,
	ItemPricingCodeQualifier VARCHAR(75) null,
	ItemPricingCodeQualifierName VARCHAR(75) null,
	ExpirationRequired VARCHAR(75) null,
	SpecificTpRequired VARCHAR(75) null,
	CreatedBy VARCHAR(75) null,
	CreatedDate DATE null,
	ModifiedBy VARCHAR(75) null,
	ModifiedDate DATE null,
	BatchId VARCHAR(75) null
);

create table Qualifier (
	QualifierId INTEGER not null primary key IDENTITY,
	QualifierCode VARCHAR(75) null,
	QualifierName VARCHAR(75) null,
	ModuleName VARCHAR(75) null,
	ExpirationRequired VARCHAR(75) null,
	SpecificTpRequired VARCHAR(75) null,
	CreatedDate DATE null,
	ModifiedDate DATE null,
	CreatedBy VARCHAR(75) null,
	ModifiedBy VARCHAR(75) null,
	BatchId VARCHAR(75) null
);

create table com_ItemIrtIdentifier (
	ItemSystemId INTEGER not null,
	ItemIdentifier VARCHAR(75) not null,
	IdentifierStatus INTEGER,
	ItemIrtQualifierId INTEGER not null,
	StartDate DATE null,
	EndDate DATE null,
	EntityCode INTEGER,
	CreatedBy VARCHAR(75) null,
	CreatedDate DATE null,
	ModifiedBy VARCHAR(75) null,
	ModifiedDate DATE null,
	BatchId VARCHAR(75) null,
	primary key (ItemSystemId, ItemIdentifier, ItemIrtQualifierId)
);

create table com_ItemIrtIdentifierHistory (
	ItemIrtIdentifierHistoryId INTEGER not null primary key IDENTITY,
	ItemSystemId INTEGER,
	ItemIdentifier VARCHAR(75) null,
	IdentifierStatus INTEGER,
	ItemIrtQualifierId INTEGER,
	StartDate DATE null,
	EndDate DATE null,
	EntityCode INTEGER,
	CreatedBy VARCHAR(75) null,
	CreatedDate DATE null,
	ModifiedBy VARCHAR(75) null,
	ModifiedDate DATE null,
	BatchId VARCHAR(75) null,
	LastModifiedDate DATE null,
	ActionFlag VARCHAR(75) null
);

create table com_ItemPricing (
	ItemSystemId INTEGER not null,
	ItemUom INTEGER,
	ItemPricingQualifierId INTEGER not null,
	ItemPrice DOUBLE,
	PricingCodeStatus INTEGER,
	StartDate DATE null,
	EndDate DATE null,
	EntityCode INTEGER,
	CreatedBy VARCHAR(75) null,
	CreatedDate DATE null,
	ModifiedBy VARCHAR(75) null,
	ModifiedDate DATE null,
	BatchId VARCHAR(75) null,
	primary key (ItemSystemId, ItemPricingQualifierId)
);

create table com_ItemPricingHistory (
	ItemPricingHistoryId INTEGER not null primary key IDENTITY,
	ItemSystemId INTEGER,
	ItemUom INTEGER,
	ItemPricingQualifierId INTEGER,
	ItemPrice DOUBLE,
	PricingCodeStatus INTEGER,
	StartDate DATE null,
	EndDate DATE null,
	EntityCode INTEGER,
	CreatedBy VARCHAR(75) null,
	CreatedDate DATE null,
	ModifiedBy VARCHAR(75) null,
	ModifiedDate DATE null,
	BatchId VARCHAR(75) null,
	LastModifiedDate DATE null,
	ActionFlag VARCHAR(75) null
);

create table com_ItemPricingQualifier (
	ItemPricingQualifierId INTEGER not null primary key IDENTITY,
	ItemPricingCodeQualifier VARCHAR(75) null,
	ItemPricingCodeQualifierName VARCHAR(75) null,
	ExpirationRequired VARCHAR(75) null,
	SpecificTpRequired VARCHAR(75) null,
	CreatedBy VARCHAR(75) null,
	CreatedDate DATE null,
	ModifiedBy VARCHAR(75) null,
	ModifiedDate DATE null,
	BatchId VARCHAR(75) null
);

create table com_Qualifier (
	QualifierId INTEGER not null primary key IDENTITY,
	QualifierCode VARCHAR(75) null,
	QualifierName VARCHAR(75) null,
	ModuleName VARCHAR(75) null,
	ExpirationRequired VARCHAR(75) null,
	SpecificTpRequired VARCHAR(75) null,
	CreatedDate DATE null,
	ModifiedDate DATE null,
	CreatedBy VARCHAR(75) null,
	ModifiedBy VARCHAR(75) null,
	BatchId VARCHAR(75) null
);

create table global_ItemIrtIdentifier (
	ItemSystemId INTEGER not null,
	ItemIdentifier VARCHAR(75) not null,
	IdentifierStatus INTEGER,
	ItemIrtQualifierId INTEGER not null,
	StartDate DATE null,
	EndDate DATE null,
	EntityCode INTEGER,
	CreatedBy VARCHAR(75) null,
	CreatedDate DATE null,
	ModifiedBy VARCHAR(75) null,
	ModifiedDate DATE null,
	BatchId VARCHAR(75) null,
	primary key (ItemSystemId, ItemIdentifier, ItemIrtQualifierId)
);

create table global_ItemIrtIdentifierHistory (
	ItemIrtIdentifierHistoryId INTEGER not null primary key IDENTITY,
	ItemSystemId INTEGER,
	ItemIdentifier VARCHAR(75) null,
	IdentifierStatus INTEGER,
	ItemIrtQualifierId INTEGER,
	StartDate DATE null,
	EndDate DATE null,
	EntityCode INTEGER,
	CreatedBy VARCHAR(75) null,
	CreatedDate DATE null,
	ModifiedBy VARCHAR(75) null,
	ModifiedDate DATE null,
	BatchId VARCHAR(75) null,
	LastModifiedDate DATE null,
	ActionFlag VARCHAR(75) null
);

create table global_ItemPricing (
	ItemSystemId INTEGER not null,
	ItemUom INTEGER,
	ItemPricingQualifierId INTEGER not null,
	ItemPrice DOUBLE,
	PricingCodeStatus INTEGER,
	StartDate DATE null,
	EndDate DATE null,
	EntityCode INTEGER,
	CreatedBy VARCHAR(75) null,
	CreatedDate DATE null,
	ModifiedBy VARCHAR(75) null,
	ModifiedDate DATE null,
	BatchId VARCHAR(75) null,
	primary key (ItemSystemId, ItemPricingQualifierId)
);

create table global_ItemPricingHistory (
	ItemPricingHistoryId INTEGER not null primary key IDENTITY,
	ItemSystemId INTEGER,
	ItemUom INTEGER,
	ItemPricingQualifierId INTEGER,
	ItemPrice DOUBLE,
	PricingCodeStatus INTEGER,
	StartDate DATE null,
	EndDate DATE null,
	EntityCode INTEGER,
	CreatedBy VARCHAR(75) null,
	CreatedDate DATE null,
	ModifiedBy VARCHAR(75) null,
	ModifiedDate DATE null,
	BatchId VARCHAR(75) null,
	LastModifiedDate DATE null,
	ActionFlag VARCHAR(75) null
);

create table global_ItemPricingQualifier (
	ItemPricingQualifierId INTEGER not null primary key IDENTITY,
	ItemPricingCodeQualifier VARCHAR(75) null,
	ItemPricingCodeQualifierName VARCHAR(75) null,
	ExpirationRequired VARCHAR(75) null,
	SpecificTpRequired VARCHAR(75) null,
	CreatedBy VARCHAR(75) null,
	CreatedDate DATE null,
	ModifiedBy VARCHAR(75) null,
	ModifiedDate DATE null,
	BatchId VARCHAR(75) null
);

create table global_Qualifier (
	QualifierId INTEGER not null primary key IDENTITY,
	QualifierCode VARCHAR(75) null,
	QualifierName VARCHAR(75) null,
	ModuleName VARCHAR(75) null,
	ExpirationRequired VARCHAR(75) null,
	SpecificTpRequired VARCHAR(75) null,
	CreatedDate DATE null,
	ModifiedDate DATE null,
	CreatedBy VARCHAR(75) null,
	ModifiedBy VARCHAR(75) null,
	BatchId VARCHAR(75) null
);