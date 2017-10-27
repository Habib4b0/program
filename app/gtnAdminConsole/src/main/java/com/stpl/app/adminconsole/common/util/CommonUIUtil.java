/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.common.util;

import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.processscheduler.logic.ProcessSchedulerLogic;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import org.vaadin.alump.beforeunload.BeforeUnload;

/**
 * The Class CommonUIUtil.
 *
 * @author Elangovan
 */
public final class CommonUIUtil {

    public static final String COUNTRY = "Country";
    public static final String COMPANY_NAME = "companyName";
    public static final String FORECAST_MONTH = "Forecast Month";
    public static final String UNCAPTURED_UNITS_LABEL = "Uncaptured Units";
    public static final String FORECAST_MONTH1 = "forecastMonth";
    public static final String ITEM_ID = "itemId";
    public static final String UNCAPTURED_UNITS_RATIO = "Uncaptured Units Ratio";
    
    public final Object[] fileMgmtHistoryResultColumns = new Object[]{"file", COMPANY_NAME, "businessUnit", "effectiveDate", "type", "version", "fromPeriod", "toPeriod"};

    public final String[] fileMgmtHistoryResultHeader = new String[]{"File", "Company", "Business Unit", "Effective Date", "Type", CommonUtil.VERSION, "From Period", "To Period"};
    public static final String COUNTRY1 = "country";

    public final Object[] fileMgmtLookupResultColumns = new Object[]{"fileType", COMPANY_NAME, "businessUnit", COUNTRY1, "fileName", "type", "version", "fromDate", "toDate"};

    public final String[] fileMgmtLookupResultHeader = new String[]{"File Type", "Company", "Business Unit", COUNTRY, "File Name", "Type", CommonUtil.VERSION, "From Date", "To Date"};
    public static final String MONTH1 = "month";
    public static final String PRICE = "price";
    public static final String ITEM_NAME = "itemName";
    public static final String START_DATE = "startDate";

    public final Object[] fileMgmtLookupDetailsColumns = new Object[]{CommonUtil.CHECK, "year", MONTH1, "itemNo", ITEM_NAME, START_DATE, PRICE, "units", "dollars"};
    public static final String FORECAST_TYPE = "Forecast Type";
    public static final String MARKET_SHARE_RATIO = "Market Share Ratio";
    public static final String MARKET_SIZE_UNITS = "Market Size Units";
    public static final String MARKET_SHARE_UNITS = "Market Share Units";
    public static final String FORECAST_YEAR = "Forecast Year";
    public static final String SEGMENT = "Segment";
    public static final String ORGANIZATION_KEY = "Organization Key";
    public static final String TOTAL_DEMAND_UNITS = "Total Demand Units";
    public static final String NET_SALES_AMOUNT = "Net Sales Amount";
    public static final String BATCH_ID = "Batch Id";
    public static final String GROSS_AMOUNT = "Gross Amount";
    public static final String INVENTORY_UNIT_CHANGE = "Inventory Unit Change";
    public static final String TOTAL_DEMAND_AMOUNT = "Total Demand Amount";
    public static final String ITEM_ID_LABEL = "Item Id";
    public static final String ITEM_IDENTIFIER = "Item Identifier";
    public static final String GROSS_PRICE = "Gross Price";
    public static final String ITEM_IDENTIFIER_CODE_QUALIFIER = "Item Identifier Code Qualifier";
    public static final String GROSS_UNITS = "Gross Units";
    public static final String NET_SALES_PRICE = "Net Sales Price";
   
    

    public final String[] fileMgtLookupDemandDetailsHeader = new String[]{ConstantsUtils.EMPTY, FORECAST_TYPE, FORECAST_YEAR, FORECAST_MONTH, ITEM_ID_LABEL, ITEM_IDENTIFIER_CODE_QUALIFIER, ITEM_IDENTIFIER,
        "Brand Id", SEGMENT, MARKET_SIZE_UNITS, MARKET_SHARE_RATIO, MARKET_SHARE_UNITS, UNCAPTURED_UNITS_LABEL, UNCAPTURED_UNITS_RATIO, TOTAL_DEMAND_UNITS, TOTAL_DEMAND_AMOUNT, INVENTORY_UNIT_CHANGE, GROSS_UNITS, GROSS_PRICE, GROSS_AMOUNT, NET_SALES_PRICE, NET_SALES_AMOUNT, BATCH_ID, ORGANIZATION_KEY};
    public static final String ORGANIZATION_KEY1 = "organizationKey";
    public static final String BRAND_ID1 = "brandId";
    public static final String BATCH_ID_PROPERTY = "batchId";
    public static final String SEGMENT1 = "segment";
    
    public final Object[] fileMgmtLookupDemandDetailsColumns = new Object[]{CommonUtil.CHECK, "forecastType", CommonUtil.FORECAST_YEAR, FORECAST_MONTH1, ITEM_ID,
        "itemIdentifierCodeQualifier", "itemIdentifier", BRAND_ID1, SEGMENT1, "marketSizeUnits", "marketShareRatio", "marketShareUnits", "uncapturedUnits", "uncapturedUnitsRatio",
        "totalDemandUnits", "totalDemandAmount", "inventoryUnitChange", "grossUnits", "grossPrice", "grossAmount", "netSalesPrice", "netSalesAmount", BATCH_ID_PROPERTY, ORGANIZATION_KEY1};
    public static final String MONTH = "Month";
    public static final String ITEM_NAME_LABEL = "Item Name";
    public static final String BRAND_ID = "Brand ID";
    public static final String ITEM_ID1 = "Item ID";
    public static final String BATCH_ID1 = "Batch ID";

    public final String[] fileMgtLookupAdjustedDemandDetailsHeader = new String[]{ConstantsUtils.EMPTY, ITEM_ID1, ITEM_NAME_LABEL, BRAND_ID, "Brand Name", SEGMENT, "Year", MONTH, MARKET_SIZE_UNITS, MARKET_SHARE_RATIO, MARKET_SHARE_UNITS, UNCAPTURED_UNITS_LABEL, UNCAPTURED_UNITS_RATIO, TOTAL_DEMAND_UNITS, TOTAL_DEMAND_AMOUNT, INVENTORY_UNIT_CHANGE, GROSS_UNITS, GROSS_PRICE, GROSS_AMOUNT, NET_SALES_PRICE, NET_SALES_AMOUNT, BATCH_ID1, "Source", ORGANIZATION_KEY};
    public final Object[] fileMgtLookupAdjustedDemandDetailsColumns = new Object[]{CommonUtil.CHECK, ITEM_ID, ITEM_NAME, BRAND_ID1, "brandName", SEGMENT1,
        CommonUtil.FORECAST_YEAR, FORECAST_MONTH1, "marketSizeUnits", "marketShareRatio", "marketShareUnits", "uncapturedUnits", "uncapturedUnitsRatio", "totalDemandUnits", "totalDemandAmount", "inventoryUnitChange", "grossUnits", "grossPrice", "grossAmount", "netSalesPrice", "netSalesAmount", BATCH_ID_PROPERTY, "source", ORGANIZATION_KEY1};

    public final Object[] fileMgmtLookupInventoryDetailaSummaryColumns = new Object[]{CommonUtil.CHECK, "year", MONTH1, "day", "week", ITEM_ID, "itemIdentifierCodeQualifier", "itemIdentifier",
        "unitsWithdrawn", "amountWithdrawn", PRICE, BATCH_ID_PROPERTY, ORGANIZATION_KEY1};
    public static final String UNITS_WITHDRAWN = "Units Withdrawn";
    public static final String PRICE_LABEL = "Price";
    public static final String AMOUNT_WITHDRAWN = "Amount Withdrawn";

    public final String[] fileMgmtLookupInventoryDetailaSummaryHeaders = new String[]{"", "Year", MONTH, "Day", "Week", ITEM_ID1, ITEM_IDENTIFIER_CODE_QUALIFIER, ITEM_IDENTIFIER, UNITS_WITHDRAWN, AMOUNT_WITHDRAWN, PRICE_LABEL, BATCH_ID1, ORGANIZATION_KEY};
    public static final String CREATED_DATE = "createdDate";
    public static final String CREATED_BY1 = "createdBy";
    public static final String MODIFIED_DATE1 = "modifiedDate";

    public final Object[] fileMgmtLookupInventoryDetailsDetailsColumns = new Object[]{CommonUtil.CHECK, "year", MONTH1, "day", "week", "companyId", COMPANY_NAME, ITEM_ID, ITEM_NAME, 
        "unitsWithdrawn", "amountWithdrawn", PRICE, CREATED_BY1, CREATED_DATE, "modifiedBy", MODIFIED_DATE1, "inboundStatus", BATCH_ID_PROPERTY, "source", "forecastName", "forecastVersion", COUNTRY1, ORGANIZATION_KEY1};
    public static final String CREATED_BY = "Created By";
    public static final String MODIFIED_DATE = "Modified Date";
    public static final String FORECAST_NAME = "Forecast Name";

    public final String[] fileMgmtLookupInventoryDetailsDetailsHeader = new String[]{"", "Year", MONTH, "Day", "Week", "Company ID", "Company Name", ITEM_ID1, ITEM_NAME_LABEL, UNITS_WITHDRAWN, AMOUNT_WITHDRAWN, PRICE_LABEL, CREATED_BY, "Created Date", "Modified By", MODIFIED_DATE, "Add Chg Del Indicator", BATCH_ID1, "Source ID", FORECAST_NAME, "Forecast Ver", COUNTRY, ORGANIZATION_KEY};
    public static final String UNITS = "Units";
    public static final String FORECAST_DATE = "Forecast Date";

    public final String[] fileMgmtDetailsLookupHeader = new String[]{ConstantsUtils.EMPTY, "Year", MONTH, "Item #", ITEM_NAME_LABEL, FORECAST_DATE, PRICE_LABEL, UNITS, "Dollars"};
    public static final String VERSION_NO = "versionNo";

    public final Object[] acSearchResultColumns = new Object[]{"resultRSName", "resultRSDesc", "resultRSType", "resultRSHierarchyName", VERSION_NO, START_DATE, CREATED_DATE, MODIFIED_DATE1,
        "resultCreatedBy"};
    public static final String RELATIONSHIP_NAME = "Relationship Name";
    public static final String HIERARCHY_NAME = "Hierarchy Name";
    public static final String CREATION_DATE = "Creation Date";
    public static final String RELATIONSHIP_DESCRIPTION = "Relationship Description";
    public static final String RELATIONSHIP_TYPE_LABEL = "Relationship Type";
    public static final String START_DATE_LABEL = "Start Date";

    public final String[] acSearchResultHeader = new String[]{RELATIONSHIP_NAME, RELATIONSHIP_DESCRIPTION, RELATIONSHIP_TYPE_LABEL, HIERARCHY_NAME, "version No", START_DATE_LABEL, CREATION_DATE, MODIFIED_DATE, CREATED_BY};
    public static final String HIERARCHY_NAME1 = "hierarchyName";

    public final Object[] acHbSearchResultColumns = new Object[]{HIERARCHY_NAME1, "hierarchyType", "hierarchyCategoryInString", "noOfLevels", VERSION_NO, CREATED_DATE, MODIFIED_DATE1, CREATED_BY1};

    public final String[] acHbSearchResultHeader = new String[]{HIERARCHY_NAME, "Hierarchy Type", "Hierarchy Category", "# of Levels", CommonUtil.VERSION, CREATION_DATE, MODIFIED_DATE, CREATED_BY};

    public final Object[] acHbLevelDefnitionColumns = new Object[]{"levelNo", "levelName", "tableName", "fieldName", "userDefinedOrLinked"};

    public final String[] acHbLevelDefnitionHeader = new String[]{"Level #", "Level Name", "Table Name", "Field Name", "User Defined/ Linked"};

    public final Object[] acHbLevelValuesColumns = new Object[]{"levelValues"};

    public final String[] acHbLevelValuesHeader = new String[]{"Level Values"};

    public final Object[] tableLookupColumns = new Object[]{"tableName"};

    public final String[] tableLookupHeader = new String[]{"Table Name"};

    public final Object[] fieldLookupColumn = new Object[]{"fieldName"};

    public final String[] fieldLookupHeader = new String[]{"Field Name"};

    public final Object[] rbSelectedLevelColumns = new Object[]{"levelValue"};

    public final Object[] rbAvailableLevelColumn = new Object[]{"levelValue"};

    public static final String HIERARCHY_CATEGORY = "HIERARCHY_CATEGORY";

    public static final String RELATIONSHIP_TYPE = "RELATIONSHIP_TYPE";

    public final Object[] itemResultColumns = new Object[]{
        ITEM_ID, "itemNo", "itemCode", ITEM_NAME, "itemDesc", "itemStartDate", "itemEndDate", "itemStatus",
        "therapeuticClass", "brand", "form", "strength", "packageSizeCode", "packageSizeIntroDate", "upps",
        "manufacturerId", "manufacturerNo", "manufacturerName", "labelerCode", ORGANIZATION_KEY1,
        "acquisitionDate", "authorizedGeneric", "authorizedGenericStartDate", "authorizedGenericEndDate",
        "firstSaleDate", "itemTypeIndicator", "itemClass", "itemType", "marketTerminationDate", "newFormulationIndicator",
        "newFormulation", "newFormulationStartDate", "newFormulationEndDate", "pediatricExclusiveIndicator",
        "pediatricExclusiveStartDate", "pediatricExclusiveEndDate", "clottingFactorIndicator", "clottingFactorStartDate",
        "clottingFactorEndDate", "primaryUom", "secondaryUom", "shelfLife", "shelfLifeType", "dualPricingIndicator",
        "itemFamilyId", "udc1", "udc2", "udc3", "udc4", "udc5", "udc6", "acquiredAmp", "acquiredBamp", "obraBamp", "dra",
        "dosesPerUnit", "discontinuationDate", "lastLotExpirationDate", "ndc9", "ndc8", "displayBrand", "innovatorCode",
        "baselineAmp", "baseYearCpi"};

    public final String[] itemResultHeader = new String[]{
        ITEM_ID1, "Item No", "Item Code", ITEM_NAME_LABEL, "Item Desc", "Item Start Date", "Item End Date",
        "Item Status", "Therapeutic Class", "Brand", "Form", "Strength", "Package Size Code",
        "Package Size Intro Date", "UPPS", "Manufacturer ID", "Manufacturer No", "Manufacturer Name",
        "Labeler Code", ORGANIZATION_KEY, "Acquisition Date", "Authorized Generic", "Authorized Generic Start Date",
        "Authorized Generic End Date", "First Sale Date", "Item Type Indicator", "Item Class ", "Item Type ",
        "Market Termination Date", "New Formulation Indicator", "New Formulation", "New Formulation Start Date",
        "New Formulation End Date", "Pediatric Exclusive Indicator", "Pediatric Exclusive Start Date",
        "Pediatric Exclusive End Date", "Clotting Factor Indicator", "Clotting Factor Start Date",
        "Clotting Factor End Date", "Primary UOM", "Secondary UOM", "Shelf Life", "Shelf Life Type",
        "Dual Pricing Indicator", "Item Family ID", "UDC 1", "UDC 2", "UDC 3", "UDC 4", "UDC 5", "UDC 6", "Acquired AMP",
        "Acquired BAMP", "OBRA BAMP", "DRA", "Doses per Unit", "Discontinuation Date", "Last Lot Expiration Date",
        "NDC9", "NDC8", "Display Brand", "Innovator Code", "Baseline AMP", "Base Year CPI"};

    public final Object[] hierarchyOutboundSearchColumns = new Object[]{CommonUtil.CHECK, HIERARCHY_NAME1, "hierarchyTypeDto", "hierarchyCategory", "noOfLevels", VERSION_NO, CREATED_DATE, MODIFIED_DATE1, CREATED_BY1};

    public final String[] hierarchyOutboundSearchHeader = new String[]{"", HIERARCHY_NAME, "Hierarchy Type", "Hierarchy Category", "# of Levels", CommonUtil.VERSION, CREATION_DATE, MODIFIED_DATE, CREATED_BY};

    public final Object[] relationshipOutboundSearchColumns = new Object[]{CommonUtil.CHECK,
        "relationshipName", "relationshipDesc", "relationshipType", HIERARCHY_NAME1, VERSION_NO, START_DATE, CREATED_DATE, MODIFIED_DATE1, CREATED_BY1};

    public final String[] relationshipOutboundSearchHeader = new String[]{"", RELATIONSHIP_NAME, RELATIONSHIP_DESCRIPTION, RELATIONSHIP_TYPE_LABEL, HIERARCHY_NAME, "Version No", START_DATE_LABEL, CREATION_DATE, MODIFIED_DATE, CREATED_BY};

    public final Object[] relationshipOutboundExcelColumns = new Object[]{"relationshipName", "relationshipDescription", "relationshipType", "hierarchyLevelName", "relationshipTree", HIERARCHY_NAME1, "hierarchyVersionNo", START_DATE, "buildType"};

    public final String[] relationshipOutboundExcelHeader = new String[]{RELATIONSHIP_NAME, RELATIONSHIP_DESCRIPTION, RELATIONSHIP_TYPE_LABEL, "Hierarchy Level Name", "Relationship", HIERARCHY_NAME, "Hierarchy Version No", START_DATE_LABEL, "Build Type"};

    public final String[] fileMgmtLookupCustomerHeader = new String[]{FORECAST_YEAR, FORECAST_MONTH, ITEM_ID_LABEL, "Company Id", UNITS, "Price Type", PRICE_LABEL, "Sales Amount", "Sales Inclusion", "Deduction Id",
        "Deduction Category", "Deduction Type", "Deduction Program Type",
        "Adjustment Code", "Deduction Rate", "Deduction Amount",
        "Deduction Inclusion", "Forecast Value Type", BRAND_ID, SEGMENT, BATCH_ID, ORGANIZATION_KEY, "Forecast Version", COUNTRY, FORECAST_NAME, FORECAST_DATE, "Forecast Interface Id"};
    public final Object[] fileMgmtLookupCustomerColumns = new Object[]{CommonUtil.FORECAST_YEAR, FORECAST_MONTH1, ITEM_ID,
        "companyId", "units", "priceType", PRICE, "salesAmount", "salesInclusion", "deductionId", "deductionCategory", "deductionType", "deductionProgramType", "adjustmentCode", "deductionRate", "deductionAmount", "deductionInclusion", "forecastValueType", BRAND_ID1, SEGMENT1, BATCH_ID_PROPERTY, ORGANIZATION_KEY1, "forecastVersion", COUNTRY1, "forecastName",
        "forecastDate", "customerGtsForecastIntfId"};

    SessionDTO sessionDTO = new SessionDTO();

    private CommonUIUtil() {

    }
    private static CommonUIUtil object;

    public static CommonUIUtil getInstance() {
        if (object == null) {
            object = new CommonUIUtil();
        }
        return object;
    }

    /**
     * <h1>Before Unload function</h1>
     * <p>
     * Purpose : Fix for the Communication Error <br>
     * The Before Unload function is used to Close the Vaadin UI when the UI is
     * unloaded from the browser , so that the data will not retained when the
     * user navigates back to same UI. <br>
     *
     * Comments about Code : Action performed after the listener is fix for the
     * Refresh problem <br>
     * Refresh Problems Faced : As the Vaadin Session Values has been changed to
     * Sesison DTO, on refresh the Id gets flushed and Screen collapse. The
     * Actions performed after the Unload listener is to maintain the required
     * ID
     *
     *
     * @param uI - UI Object
     * @param sessionDTO - SessionDTO Object
     * @return
     */
    public static final void beforeUnloadCloseUi(final UI uI, final SessionDTO sessionDTO) {
        BeforeUnload ob = BeforeUnload.closeBeforeUnload(uI);
        ob.addUnloadListener(new BeforeUnload.UnloadListener() {

            @Override
            public void unload(BeforeUnload.UnloadEvent event) {
                VaadinSession.getCurrent().setAttribute(ConstantsUtils.SYS_ID, sessionDTO.getSystemId());
                VaadinSession.getCurrent().setAttribute(ConstantsUtils.MODE, sessionDTO.getMode());
                if (!"true".equals(VaadinSession.getCurrent().getAttribute(ConstantsUtils.EXCEL_CLOSE))) {
                    uI.close();
                } else {
                    VaadinSession.getCurrent().setAttribute(ConstantsUtils.EXCEL_CLOSE, "");
                }
                ProcessSchedulerLogic processSchedulerLogic = new ProcessSchedulerLogic();
                processSchedulerLogic.deleteTempTable(sessionDTO);
            }
        });
        if (VaadinSession.getCurrent().getAttribute(ConstantsUtils.MODE) != null && !"".equals(VaadinSession.getCurrent().getAttribute(ConstantsUtils.MODE))) {
            sessionDTO.setMode((String) VaadinSession.getCurrent().getAttribute(ConstantsUtils.MODE));
            VaadinSession.getCurrent().setAttribute(ConstantsUtils.MODE, "");
        }
        if (VaadinSession.getCurrent().getAttribute(ConstantsUtils.SYS_ID) != null && !"".equals(VaadinSession.getCurrent().getAttribute(ConstantsUtils.SYS_ID))) {
            sessionDTO.setSystemId((Integer) VaadinSession.getCurrent().getAttribute(ConstantsUtils.SYS_ID));
            VaadinSession.getCurrent().setAttribute(ConstantsUtils.SYS_ID, "");
        }

    }
    public final String[] excelFileMgtLookupAdjustedDemandDetailsHeader = new String[]{ITEM_ID1, ITEM_NAME_LABEL, BRAND_ID, "Brand Name", SEGMENT, "Year", MONTH, MARKET_SIZE_UNITS, MARKET_SHARE_RATIO, MARKET_SHARE_UNITS, UNCAPTURED_UNITS_LABEL, UNCAPTURED_UNITS_RATIO, TOTAL_DEMAND_UNITS, TOTAL_DEMAND_AMOUNT, INVENTORY_UNIT_CHANGE, GROSS_UNITS, GROSS_PRICE, GROSS_AMOUNT, NET_SALES_PRICE, NET_SALES_AMOUNT, BATCH_ID1, "Source", ORGANIZATION_KEY};

    public final String[] excelFileMgtLookupDemandDetailsHeader = new String[]{FORECAST_TYPE, FORECAST_YEAR, FORECAST_MONTH, ITEM_ID_LABEL, ITEM_IDENTIFIER_CODE_QUALIFIER, ITEM_IDENTIFIER,
        "Brand Id", SEGMENT, MARKET_SIZE_UNITS, MARKET_SHARE_RATIO, MARKET_SHARE_UNITS, UNCAPTURED_UNITS_LABEL, UNCAPTURED_UNITS_RATIO, TOTAL_DEMAND_UNITS, TOTAL_DEMAND_AMOUNT, INVENTORY_UNIT_CHANGE, GROSS_UNITS, GROSS_PRICE, GROSS_AMOUNT, NET_SALES_PRICE, NET_SALES_AMOUNT, BATCH_ID, ORGANIZATION_KEY};

    public final String[] excelFileMgtLookupDetailsHeader = new String[]{"Year", MONTH, "Item #", ITEM_NAME_LABEL, FORECAST_DATE, PRICE_LABEL, UNITS, "Dollars"};

    public final String[] excelFileMgtLookupInventoryDetailsHeader = new String[]{"Year", MONTH, "Day", "Week", "Company ID", "Company Name", ITEM_ID1, ITEM_NAME_LABEL, UNITS_WITHDRAWN, AMOUNT_WITHDRAWN, PRICE_LABEL, CREATED_BY, "Created Date", "Modified By", MODIFIED_DATE, "Add Chg Del Indicator", BATCH_ID1, "Source ID", FORECAST_NAME, "Forecast Ver", COUNTRY, ORGANIZATION_KEY};

    public final String[] excelFileMgtLookupInventorySummaryHeader = new String[]{"Year", MONTH, "Day", "Week", ITEM_ID1, ITEM_IDENTIFIER_CODE_QUALIFIER, ITEM_IDENTIFIER, UNITS_WITHDRAWN, AMOUNT_WITHDRAWN, PRICE_LABEL, BATCH_ID1, ORGANIZATION_KEY};


}
