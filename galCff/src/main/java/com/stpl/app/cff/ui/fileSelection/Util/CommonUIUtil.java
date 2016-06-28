/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.fileSelection.Util;

// TODO: Auto-generated Javadoc

import com.stpl.app.cff.dto.SessionDTO;
import org.apache.commons.lang.StringUtils;
//import org.vaadin.alump.beforeunload.BeforeUnload;

/**
 * The Class CommonUIUtil.
 *
 * @author Elangovan
 */
public final class CommonUIUtil {

	/** The Constant COUNTRY. */
	public static final String COUNTRY = "Country";

	/** The Constant FILE_MGT_HISTORY_RESULT_COLUMNS. */
	public static final Object[] FILE_MGT_HISTORY_RESULT_COLUMNS = new Object[] { "file", "effectiveDate", "type", "version", "fromPeriod", "toPeriod" };

	/** The Constant FILE_MGT_HISTORY_RESULT_HEADER. */
	public static final String[] FILE_MGT_HISTORY_RESULT_HEADER = new String[] { "File", "Effective Date", "Type", "Version", "From Period", "To Period" };

	/** The Constant FILE_MGT_LOOKUP_RESULT_COLUMNS. */
	public static final Object[] FILE_MGT_LOOKUP_RESULT_COLUMNS = new Object[] { "fileType", "country", "fileName", "type", "version","fromDate","toDate"};

	/** The Constant FILE_MGT_LOOKUP_RESULT_HEADER. */
	public static final String[] FILE_MGT_LOOKUP_RESULT_HEADER = new String[] { "File Type", "Country", "File Name", "Type", "Version","From Date","To Date"};

	/** The Constant FILE_MGT_LOOKUP_DETAILS_COLUMNS. */
	public static final Object[] FILE_MGT_LOOKUP_DETAILS_COLUMNS = new Object[] {"year", "month", "itemNo", "itemName", "startDate", "price", "units", "dollars" };
	/** The Constant FILE_MGT_LOOKUP_DETAILS_COLUMNS. */
	public static final Object[] FILE_MGT_LOOKUP_DETAILS_COLUMNS_EXCEL = new Object[] {"year", "month", "itemNo", "itemName", "startDate", "price", "units", "dollars" };
        /** The Constant FILE_MGT_LOOKUP_DEMAND_DETAILS_HEADER */
	public static final String[] FILE_MGT_LOOKUP_DEMAND_DETAILS_HEADER = new String[] {"Forecast Type","Forecast Year","Forecast Month",
                                                                                    "Item Id","Item Identifier Code Qualifier","Item Identifier",
                                                                                    "Brand Id","Segment","Market Size Units","Market Share Ratio",
                                                                                    "Market Share Units","Uncaptured Units","Uncaptured Units Ratio",
                                                                                    "Total Demand Units","Total Demand Amount","Inventory Unit Change",
                                                                                    "Gross Units","Gross Price","Gross Amount","Net Sales Price",
        /** The Constant FILE_MGT_LOOKUP_DEMAND_DETAILS_COLUMNS*/                                                                            "Net Sales Amount","Batch Id","Organization Key"};
        public static final Object[] FILE_MGT_LOOKUP_DEMAND_DETAILS_COLUMNS = new Object[] {"forecastType","forcastYear","forecastMonth","itemId",
                                                                                "itemIdentifierCodeQualifier","itemIdentifier","brandId","segment","marketSizeUnits"
                                                                                ,"marketShareRatio","marketShareUnits","uncapturedUnits","uncapturedUnitsRatio"
                                                                                ,"totalDemandUnits","totalDemandAmount","inventoryUnitChange","grossUnits","grossPrice"
                                                                                ,"grossAmount","netSalesPrice","netSalesAmount","batchId","organizationKey"};
	public static final String[] FILE_MGT_LOOKUP_DEMAND_DETAILS_HEADER_EXCEL = new String[] {"Forecast Type","Forecast Year","Forecast Month",
                                                                                    "Item Id","Item Identifier Code Qualifier","Item Identifier",
                                                                                    "Brand Id","Segment","Market Size Units","Market Share Ratio",
                                                                                    "Market Share Units","Uncaptured Units","Uncaptured Units Ratio",
                                                                                    "Total Demand Units","Total Demand Amount","Inventory Unit Change",
                                                                                    "Gross Units","Gross Price","Gross Amount","Net Sales Price",
        /** The Constant FILE_MGT_LOOKUP_DEMAND_DETAILS_COLUMNS*/                                                                            "Net Sales Amount","Batch Id","Organization Key"};
        public static final Object[] FILE_MGT_LOOKUP_DEMAND_DETAILS_COLUMNS_EXCEL = new Object[] {"forecastType","forcastYear","forecastMonth","itemId",
                                                                                "itemIdentifierCodeQualifier","itemIdentifier","brandId","segment","marketSizeUnits"
                                                                                ,"marketShareRatio","marketShareUnits","uncapturedUnits","uncapturedUnitsRatio"
                                                                                ,"totalDemandUnits","totalDemandAmount","inventoryUnitChange","grossUnits","grossPrice"
                                                                                ,"grossAmount","netSalesPrice","netSalesAmount","batchId","organizationKey"};
           /** The Constant FILE_MGT_LOOKUP_INVENTORY_DETAILS_COLUMNS*/ 
         public static final Object[] FILE_MGT_LOOKUP_INVENTORY_DETAILS_SUMMARY_COLUMNS =new Object[]{"year","month","day","week","itemId","itemIdentifierCodeQualifier","itemIdentifier",
                                                                                        "unitsWithdrawn","amountWithdrawn","price","batchId","organizationKey"};
            /** The Constant FILE_MGT_LOOKUP_INVENTORY_DETAILS_HEADER*/ 
         public static final String[] FILE_MGT_LOOKUP_INVENTORY_DETAILS_SUMMARY_HEADER =new String[]{"Year","Month","Day","Week","Item ID","Item Identifier Code Qualifier","Item Identifier",
                                                                                        "Units Withdrawn","Amount Withdrawn","Price","Batch ID","Organization Key"};
           /** The Constant FILE_MGT_LOOKUP_INVENTORY_DETAILS_COLUMNS*/ 
         public static final Object[] FILE_MGT_LOOKUP_INVENTORY_DETAILS_SUMMARY_COLUMNS_EXCEL =new Object[]{"year","month","day","week","itemId","itemIdentifierCodeQualifier","itemIdentifier",
                                                                                        "unitsWithdrawn","amountWithdrawn","price","batchId","organizationKey"};
            /** The Constant FILE_MGT_LOOKUP_INVENTORY_DETAILS_HEADER*/ 
         public static final String[] FILE_MGT_LOOKUP_INVENTORY_DETAILS_SUMMARY_HEADER_EXCEL =new String[]{"Year","Month","Day","Week","Item ID","Item Identifier Code Qualifier","Item Identifier",
                                                                                        "Units Withdrawn","Amount Withdrawn","Price","Batch ID","Organization Key"};
         /** The Constant FILE_MGT_LOOKUP_INVENTORY_DETAILS_COLUMNS*/ 
         public static final Object[] FILE_MGT_LOOKUP_INVENTORY_DETAILS_DETAILS_COLUMNS =new Object[]{"year","month","day","week","companyId","identifierCodeQualifier","companyIdentifier","itemId","itemIdentifierCodeQualifier",
                                                                                        "itemIdentifier","unitsWithdrawn","amountWithdrawn","price","batchId","organizationKey"};
            /** The Constant FILE_MGT_LOOKUP_INVENTORY_DETAILS_HEADER*/ 
         public static final String[] FILE_MGT_LOOKUP_INVENTORY_DETAILS_DETAILS_HEADER =new String[]{"Year","Month","Day","Week","Company Id","Identifier Code Qualifier","Company Identifier","Item ID","Item Identifier Code Qualifier",
                                                                                        "Item Identifier","Units Withdrawn","Amount Withdrawn","Price","Batch ID","Organization Key"};
         
         /** The Constant FILE_MGT_LOOKUP_INVENTORY_DETAILS_COLUMNS*/ 
         public static final Object[] FILE_MGT_LOOKUP_INVENTORY_DETAILS_DETAILS_COLUMNS_EXCEL =new Object[]{"year","month","day","week","companyId","identifierCodeQualifier","companyIdentifier","itemId","itemIdentifierCodeQualifier",
                                                                                        "itemIdentifier","unitsWithdrawn","amountWithdrawn","price","batchId","organizationKey"};
            /** The Constant FILE_MGT_LOOKUP_INVENTORY_DETAILS_HEADER*/ 
         public static final String[] FILE_MGT_LOOKUP_INVENTORY_DETAILS_DETAILS_HEADER_EXCEL =new String[]{"Year","Month","Day","Week","Company Id","Identifier Code Qualifier","Company Identifier","Item ID","Item Identifier Code Qualifier",
                                                                                        "Item Identifier","Units Withdrawn","Amount Withdrawn","Price","Batch ID","Organization Key"};
         
	/** The Constant FILE_MGT_LOOKUP_DETAILS_HEADER. */
	public static final String[] FILE_MGT_LOOKUP_DETAILS_HEADER = new String[] {"Year", "Month", "Item #", "Item Name", "Forecast Date", "Price", "Units", "Dollars" };
	public static final String[] FILE_MGT_LOOKUP_DETAILS_HEADER_EXCEL = new String[] {"Year", "Month", "Item #", "Item Name", "Forecast Date", "Price", "Units", "Dollars" };

	/** The Constant AC_SEARCH_RESULT_COLUMNS. */
	public static final Object[] AC_SEARCH_RESULT_COLUMNS = new Object[] { "resultRSName", "resultRSDesc", "resultRSType", "resultRSHierarchyName","versionNo","startDate", "createdDate", "modifiedDate",
			"resultCreatedBy" };

	/** The Constant AC_SEARCH_RESULT_HEADER. */
	public static final String[] AC_SEARCH_RESULT_HEADER = new String[] { "Relationship Name", "Relationship Description", "Relationship Type", "Hierarchy Name","version No", "Start Date", "Creation Date",
			"Modified Date", "Created By" };

	/** The Constant AC_HB_SEARCH_RESULT_COLUMNS. */
	public static final Object[] AC_HB_SEARCH_RESULT_COLUMNS = new Object[] { "hierarchyName", "hierarchyType","hierarchyCategoryInString","noOfLevels", "versionNo","createdDate", "modifiedDate", "createdBy" };

	/** The Constant AC_HB_SEARCH_RESULT_HEADER. */
	public static final String[] AC_HB_SEARCH_RESULT_HEADER = new String[] { "Hierarchy Name", "Hierarchy Type","Hierarchy Category", "# of Levels", "Version" ,"Creation Date", "Modified Date", "Created By"};

	/** The Constant AC_HB_LEVEL_DEFINITION_COLUMNS. */
	public static final Object[] AC_HB_LEVEL_DEFINITION_COLUMNS = new Object[] { "levelNo", "levelName", "tableName", "fieldName", "userDefinedOrLinked" };

	/** The Constant AC_HB_LEVEL_DEFINITION_HEADER. */
	public static final String[] AC_HB_LEVEL_DEFINITION_HEADER = new String[] { "Level #", "Level Name", "Table Name", "Field Name", "User Defined/ Linked" };

	/** The Constant AC_HB_LEVEL_VALUES_COLUMNS. */
	public static final Object[] AC_HB_LEVEL_VALUES_COLUMNS = new Object[] { "levelValues" };

	/** The Constant AC_HB_LEVEL_VALUES_HEADER. */
	public static final String[] AC_HB_LEVEL_VALUES_HEADER = new String[] { "Level Values" };

	/** The Constant TABLE_LOOKUP_COLUMN. */
	public static final Object[] TABLE_LOOKUP_COLUMN = new Object[] { "tableName" };

	/** The Constant TABLE_LOOKUP_HEADER. */
	public static final String[] TABLE_LOOKUP_HEADER = new String[] { "Table Name" };

	/** The Constant FIELD_LOOKUP_COLUMN. */
	public static final Object[] FIELD_LOOKUP_COLUMN = new Object[] { "fieldName" };

	/** The Constant FIELD_LOOKUP_HEADER. */
	public static final String[] FIELD_LOOKUP_HEADER = new String[] { "Field Name" };

	/** The Constant RB_SELECTED_LEVEL_COLUMN. */
	public static final Object[] RB_SELECTED_LEVEL_COLUMN = new Object[] { "levelValue" };

	/** The Constant RB_AVAILABLE_LEVEL_COLUMN. */
	public static final Object[] RB_AVAILABLE_LEVEL_COLUMN = new Object[] { "levelValue"};

	/** The Constant HIERARCHY_CATEGORY. */
	public static final String HIERARCHY_CATEGORY = "HIERARCHY_CATEGORY";

             /** The Constant ITEM_RESULTS_COLUMNS. */
             public static final Object[] ITEM_RESULTS_COLUMNS = new Object[] {
			 "itemId","itemNo","itemCode","itemName","itemDesc","itemStartDate","itemEndDate","itemStatus",
                         "therapeuticClass","brand","form","strength","packageSizeCode","packageSizeIntroDate","upps",
                         "manufacturerId","manufacturerNo","manufacturerName","labelerCode","organizationKey",
                         "acquisitionDate","authorizedGeneric","authorizedGenericStartDate","authorizedGenericEndDate",
                         "firstSaleDate","itemTypeIndicator","itemClass","itemType","marketTerminationDate","newFormulationIndicator",
                         "newFormulation","newFormulationStartDate","newFormulationEndDate","pediatricExclusiveIndicator",
                         "pediatricExclusiveStartDate","pediatricExclusiveEndDate","clottingFactorIndicator","clottingFactorStartDate",
                         "clottingFactorEndDate","primaryUom","secondaryUom","shelfLife","shelfLifeType","dualPricingIndicator",
                         "itemFamilyId","udc1","udc2","udc3","udc4","udc5","udc6","acquiredAmp","acquiredBamp","obraBamp","dra",
                         "dosesPerUnit","discontinuationDate","lastLotExpirationDate","ndc9","ndc8","displayBrand","innovatorCode",
                         "baselineAmp","baseYearCpi" };

	/** The Constant ITEM_RESULTS_HEADER. */
	public static final String[] ITEM_RESULTS_HEADER = new String[] {
			"Item ID","Item No","Item Code","Item Name","Item Desc","Item Start Date","Item End Date",
                        "Item Status","Therapeutic Class","Brand","Form","Strength","Package Size Code",
                        "Package Size Intro Date","UPPS","Manufacturer ID","Manufacturer No","Manufacturer Name",
                        "Labeler Code","Organization Key","Acquisition Date","Authorized Generic","Authorized Generic Start Date",
                        "Authorized Generic End Date","First Sale Date","Item Type Indicator","Item Class ","Item Type ",
                        "Market Termination Date","New Formulation Indicator","New Formulation","New Formulation Start Date",
                        "New Formulation End Date","Pediatric Exclusive Indicator","Pediatric Exclusive Start Date",
                        "Pediatric Exclusive End Date","Clotting Factor Indicator","Clotting Factor Start Date",
                        "Clotting Factor End Date","Primary UOM","Secondary UOM","Shelf Life","Shelf Life Type",
                        "Dual Pricing Indicator","Item Family ID","UDC 1","UDC 2","UDC 3","UDC 4","UDC 5","UDC 6","Acquired AMP",
                        "Acquired BAMP","OBRA BAMP","DRA","Doses per Unit","Discontinuation Date","Last Lot Expiration Date",
                        "NDC9","NDC8","Display Brand","Innovator Code","Baseline AMP","Base Year CPI" }; 
        
        public static final String[] FILE_MGT_LOOKUP_CUSTOMER_HEADER = new String[] {"Forecast Year","Forecast Month",
                                                                                    "Item Id","Company Id","Units","Price Type",
                                                                                    "Price","Sales Amount","Sales Inclusion","Deduction Id",
                                                                                    "Deduction Category","Deduction Type","Deduction Program Type",
                                                                                    "Adjustment Code","Deduction Rate","Deduction Amount",
                                                                                    "Deduction Inclusion","Forecast Value Type","Brand ID","Segment"
                                                                                    ,"Batch Id","Organization Key","Forecast Version","Country","Forecast Name",
                                                                                    "Forecast Date"};
       public static final Object[] FILE_MGT_LOOKUP_CUSTOMER_COLUMNS = new Object[] {"forcastYear","forecastMonth","itemId",
                                                                                "companyId","units","priceType","price","salesAmount","salesInclusion"
                                                                                ,"deductionId","deductionCategory","deductionType","deductionProgramType"
                                                                                ,"adjustmentCode","deductionRate","deductionAmount","deductionInclusion","forecastValueType"
                                                                                ,"brandId","segment","batchId","organizationKey","forecastVersion","country","forecastName",
                                                                                "forecastDate"};

            public static final String[] FILE_MGT_LOOKUP_ADJUSTED_DEMAND_DETAILS_HEADER = new String[]{ "Item ID", "Item Name", "Brand ID", "Brand Name", "Segment", "Year",
        "Month", "Market Size Units", "Market Share Ratio", "Market Share Units", "Uncaptured Units",
        "Uncaptured Units Ratio", "Total Demand Units", "Total Demand Amount", "Inventory Unit Change",
        "Gross Units", "Gross Price", "Gross Amount", "Net Sales Price", "Net Sales Amount",
        "Batch ID", "Source", "Organization Key"};
    public static final Object[] FILE_MGT_LOOKUP_ADJUSTED_DEMAND_DETAILS_COLUMNS = new Object[]{ "itemId", "itemName", "brandId", "brandName", "segment",
        "forcastYear", "forecastMonth", "marketSizeUnits", "marketShareRatio", "marketShareUnits", 
        "uncapturedUnits", "uncapturedUnitsRatio",  "totalDemandUnits", "totalDemandAmount", 
        "inventoryUnitChange", "grossUnits", "grossPrice", "grossAmount", "netSalesPrice", "netSalesAmount",
        "batchId", "source", "organizationKey"};

        SessionDTO sessionDTO=new SessionDTO();
	/**
	 * private constructor to protect to object creation.
	 */
	private CommonUIUtil() {
		// private constructor to protect to object creation.
	}
}
