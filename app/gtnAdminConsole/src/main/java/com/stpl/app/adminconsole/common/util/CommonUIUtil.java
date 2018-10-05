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
    
    private static final Object[] FILE_MGMT_HISTORY_RESULT_COLUMNS = new Object[]{"file", COMPANY_NAME, "businessUnit", "effectiveDate", "type", "version", "fromPeriod", "toPeriod"};

    private static final String[] FILE_MGMT_HISTORY_RESULT_HEADER = new String[]{"File", "Company", "Business Unit", "Effective Date", "Type", CommonUtil.VERSION, "From Period", "To Period"};
    public static final String COUNTRY1 = "country";

    private static final Object[] FILE_MGMT_LOOKUP_RESULT_COLUMNS = new Object[]{"fileType", COMPANY_NAME, "businessUnit", COUNTRY1, "fileName", "type", "version", "fromDate", "toDate"};

    private static final String[] FILE_MGMT_LOOKUP_RESULT_HEADER = new String[]{"File Type", "Company", "Business Unit", COUNTRY, "File Name", "Type", CommonUtil.VERSION, "From Date", "To Date"};
    public static final String MONTH1 = "month";
    public static final String PRICE = "price";
    public static final String ITEM_NAME = "itemName";
    public static final String START_DATE = "startDate";

    private static final Object[] FILE_MGMT_LOOKUP_DETAILS_COLUMNS = new Object[]{CommonUtil.CHECK, "year", MONTH1, "itemNo", ITEM_NAME, START_DATE, PRICE, "units", "dollars"};
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
   
    

    private static final String[] FILE_MGT_LOOKUP_DEMAND_DETAILS_HEADER = new String[]{ConstantsUtils.EMPTY, FORECAST_TYPE, FORECAST_YEAR, FORECAST_MONTH, ITEM_ID_LABEL, ITEM_IDENTIFIER_CODE_QUALIFIER, ITEM_IDENTIFIER,
        "Brand Id", SEGMENT, MARKET_SIZE_UNITS, MARKET_SHARE_RATIO, MARKET_SHARE_UNITS, UNCAPTURED_UNITS_LABEL, UNCAPTURED_UNITS_RATIO, TOTAL_DEMAND_UNITS, TOTAL_DEMAND_AMOUNT, INVENTORY_UNIT_CHANGE, GROSS_UNITS, GROSS_PRICE, GROSS_AMOUNT, NET_SALES_PRICE, NET_SALES_AMOUNT, BATCH_ID, ORGANIZATION_KEY};
    public static final String ORGANIZATION_KEY1 = "organizationKey";
    public static final String BRAND_ID1 = "brandId";
    public static final String BATCH_ID_PROPERTY = "batchId";
    public static final String SEGMENT1 = "segment";
    
    private static final Object[] FILE_MGMT_LOOKUP_DEMAND_DETAILS_COLUMNS = new Object[]{CommonUtil.CHECK, "forecastType", CommonUtil.FORECAST_YEAR, FORECAST_MONTH1, ITEM_ID,
        "itemIdentifierCodeQualifier", "itemIdentifier", BRAND_ID1, SEGMENT1, "marketSizeUnits", "marketShareRatio", "marketShareUnits", "uncapturedUnits", "uncapturedUnitsRatio",
        "totalDemandUnits", "totalDemandAmount", "inventoryUnitChange", "grossUnits", "grossPrice", "grossAmount", "netSalesPrice", "netSalesAmount", BATCH_ID_PROPERTY, ORGANIZATION_KEY1};
    public static final String MONTH = "Month";
    public static final String ITEM_NAME_LABEL = "Item Name";
    public static final String BRAND_ID = "Brand ID";
    public static final String ITEM_ID1 = "Item ID";
    public static final String BATCH_ID1 = "Batch ID";

    private static final String[] FILE_MGT_LOOKUP_ADJUSTED_DEMAND_DETAILS_HEADER = new String[]{ConstantsUtils.EMPTY, ITEM_ID1, ITEM_NAME_LABEL, BRAND_ID, "Brand Name", SEGMENT, "Year", MONTH, MARKET_SIZE_UNITS, MARKET_SHARE_RATIO, MARKET_SHARE_UNITS, UNCAPTURED_UNITS_LABEL, UNCAPTURED_UNITS_RATIO, TOTAL_DEMAND_UNITS, TOTAL_DEMAND_AMOUNT, INVENTORY_UNIT_CHANGE, GROSS_UNITS, GROSS_PRICE, GROSS_AMOUNT, NET_SALES_PRICE, NET_SALES_AMOUNT, BATCH_ID1, "Source", ORGANIZATION_KEY};
    private static final Object[] FILE_MGT_LOOKUP_ADJUSTED_DEMAND_DETAILS_COLUMNS = new Object[]{CommonUtil.CHECK, ITEM_ID, ITEM_NAME, BRAND_ID1, "brandName", SEGMENT1,
        CommonUtil.FORECAST_YEAR, FORECAST_MONTH1, "marketSizeUnits", "marketShareRatio", "marketShareUnits", "uncapturedUnits", "uncapturedUnitsRatio", "totalDemandUnits", "totalDemandAmount", "inventoryUnitChange", "grossUnits", "grossPrice", "grossAmount", "netSalesPrice", "netSalesAmount", BATCH_ID_PROPERTY, "source", ORGANIZATION_KEY1};

    private static final Object[] FILE_MGMT_LOOKUP_INVENTORY_DETAILA_SUMMARY_COLUMNS = new Object[]{CommonUtil.CHECK, "year", MONTH1, "day", "week", ITEM_ID, "itemIdentifierCodeQualifier", "itemIdentifier",
        "unitsWithdrawn", "amountWithdrawn", PRICE, BATCH_ID_PROPERTY, ORGANIZATION_KEY1};
    public static final String UNITS_WITHDRAWN = "Units Withdrawn";
    public static final String PRICE_LABEL = "Price";
    public static final String AMOUNT_WITHDRAWN = "Amount Withdrawn";

    private static final String[] FILE_MGMT_LOOKUP_INVENTORY_DETAILS_SUMMARY_HEADERS = new String[]{"", "Year", MONTH, "Day", "Week", ITEM_ID1, ITEM_IDENTIFIER_CODE_QUALIFIER, ITEM_IDENTIFIER, UNITS_WITHDRAWN, AMOUNT_WITHDRAWN, PRICE_LABEL, BATCH_ID1, ORGANIZATION_KEY};
    public static final String CREATED_DATE = "createdDate";
    public static final String CREATED_BY1 = "createdBy";
    public static final String MODIFIED_DATE1 = "modifiedDate";

    private static final Object[] FILE_MGMT_LOOKUP_INVENTORY_DETAILS_COLUMNS = new Object[]{CommonUtil.CHECK, "year", MONTH1, "day", "week", "companyId", COMPANY_NAME, ITEM_ID, ITEM_NAME, 
        "unitsWithdrawn", "amountWithdrawn", PRICE, CREATED_BY1, CREATED_DATE, "modifiedBy", MODIFIED_DATE1, "inboundStatus", BATCH_ID_PROPERTY, "source", "forecastName", "forecastVersion", COUNTRY1, ORGANIZATION_KEY1};
    public static final String CREATED_BY = "Created By";
    public static final String MODIFIED_DATE = "Modified Date";
    public static final String FORECAST_NAME = "Forecast Name";

    private static final String[] FILE_MGMT_LOOKUP_INVENTORY_DETAILS_HEADER = new String[]{"", "Year", MONTH, "Day", "Week", "Company ID", "Company Name", ITEM_ID1, ITEM_NAME_LABEL, UNITS_WITHDRAWN, AMOUNT_WITHDRAWN, PRICE_LABEL, CREATED_BY, "Created Date", "Modified By", MODIFIED_DATE, "Add Chg Del Indicator", BATCH_ID1, "Source ID", FORECAST_NAME, "Forecast Ver", COUNTRY, ORGANIZATION_KEY};
    public static final String UNITS = "Units";
    public static final String FORECAST_DATE = "Forecast Date";

    private static final String[] FILE_MGMT_DETAILS_LOOKUP_HEADER = new String[]{ConstantsUtils.EMPTY, "Year", MONTH, "Item #", ITEM_NAME_LABEL, FORECAST_DATE, PRICE_LABEL, UNITS, "Dollars"};
    public static final String VERSION_NO = "versionNo";

    public static final String RELATIONSHIP_NAME = "Relationship Name";
    public static final String HIERARCHY_NAME = "Hierarchy Name";
    public static final String CREATION_DATE = "Creation Date";
    public static final String RELATIONSHIP_DESCRIPTION = "Relationship Description";
    public static final String RELATIONSHIP_TYPE_LABEL = "Relationship Type";
    public static final String START_DATE_LABEL = "Start Date";

    public static final String HIERARCHY_NAME1 = "hierarchyName";

    public static final String HIERARCHY_CATEGORY = "HIERARCHY_CATEGORY";

    public static final String RELATIONSHIP_TYPE = "RELATIONSHIP_TYPE";

    private static final Object[] HIERARCHY_OUTBOUND_SEARCH_COLUMNS = new Object[]{CommonUtil.CHECK, HIERARCHY_NAME1, "hierarchyTypeDto", "hierarchyCategory", "noOfLevels", VERSION_NO, CREATED_DATE, MODIFIED_DATE1, CREATED_BY1};

    private static final String[] HIERARCHY_OUTBOUND_SEARCH_HEADER = new String[]{"", HIERARCHY_NAME, "Hierarchy Type", "Hierarchy Category", "# of Levels", CommonUtil.VERSION, CREATION_DATE, MODIFIED_DATE, CREATED_BY};

    private static final Object[] RELATIONSHIP_OUTBOUND_SEARCH_COLUMNS = new Object[]{CommonUtil.CHECK,
        "relationshipName", "relationshipDesc", "relationshipType", HIERARCHY_NAME1, VERSION_NO, START_DATE, CREATED_DATE, MODIFIED_DATE1, CREATED_BY1};

    private static final String[] RELATIONSHIP_OUTBOUND_SEARCH_HEADER = new String[]{"", RELATIONSHIP_NAME, RELATIONSHIP_DESCRIPTION, RELATIONSHIP_TYPE_LABEL, HIERARCHY_NAME, "Version No", START_DATE_LABEL, CREATION_DATE, MODIFIED_DATE, CREATED_BY};

    private static final Object[] RELATIONSHIP_OUTBOUND_EXCEL_COLUMNS = new Object[]{"relationshipName", "relationshipDescription", "relationshipType", "hierarchyLevelName", "relationshipTree", HIERARCHY_NAME1, "hierarchyVersionNo", START_DATE, "buildType"};

    private static final String[] RELATIONSHIP_OUTBOUND_EXCEL_HEADER = new String[]{RELATIONSHIP_NAME, RELATIONSHIP_DESCRIPTION, RELATIONSHIP_TYPE_LABEL, "Hierarchy Level Name", "Relationship", HIERARCHY_NAME, "Hierarchy Version No", START_DATE_LABEL, "Build Type"};

    private static final String[] FILE_MGMT_LOOKUP_CUSTOMER_HEADER = new String[]{FORECAST_YEAR, FORECAST_MONTH, ITEM_ID_LABEL, "Company Id", UNITS, "Price Type", PRICE_LABEL, "Sales Amount", "Sales Inclusion", "Deduction Id",
        "Deduction Category", "Deduction Type", "Deduction Program Type",
        "Adjustment Code", "Deduction Rate", "Deduction Amount",
        "Deduction Inclusion", "Forecast Value Type", BRAND_ID, SEGMENT, BATCH_ID, ORGANIZATION_KEY, "Forecast Version", COUNTRY, FORECAST_NAME, FORECAST_DATE, "Forecast Interface Id"};
    private static final Object[] FILE_MGMT_LOOKUP_CUSTOMER_COLUMNS = new Object[]{CommonUtil.FORECAST_YEAR, FORECAST_MONTH1, ITEM_ID,
        "companyId", "units", "priceType", PRICE, "salesAmount", "salesInclusion", "deductionId", "deductionCategory", "deductionType", "deductionProgramType", "adjustmentCode", "deductionRate", "deductionAmount", "deductionInclusion", "forecastValueType", BRAND_ID1, SEGMENT1, BATCH_ID_PROPERTY, ORGANIZATION_KEY1, "forecastVersion", COUNTRY1, "forecastName",
        "forecastDate", "customerGtsForecastIntfId"};
    
    private static final String[] EXCEL_FILEMGT_LOOKUP_ADJUSTED_DEMAND_DETAILS_HEADER = new String[]{ITEM_ID1, ITEM_NAME_LABEL, BRAND_ID, "Brand Name", SEGMENT, "Year", MONTH, MARKET_SIZE_UNITS, MARKET_SHARE_RATIO, MARKET_SHARE_UNITS, UNCAPTURED_UNITS_LABEL, UNCAPTURED_UNITS_RATIO, TOTAL_DEMAND_UNITS, TOTAL_DEMAND_AMOUNT, INVENTORY_UNIT_CHANGE, GROSS_UNITS, GROSS_PRICE, GROSS_AMOUNT, NET_SALES_PRICE, NET_SALES_AMOUNT, BATCH_ID1, "Source", ORGANIZATION_KEY};

    private static final String[] EXCEL_FILEMGT_LOOKUP_DEMAND_DETAILS_HEADER = new String[]{FORECAST_TYPE, FORECAST_YEAR, FORECAST_MONTH, ITEM_ID_LABEL, ITEM_IDENTIFIER_CODE_QUALIFIER, ITEM_IDENTIFIER,
        "Brand Id", SEGMENT, MARKET_SIZE_UNITS, MARKET_SHARE_RATIO, MARKET_SHARE_UNITS, UNCAPTURED_UNITS_LABEL, UNCAPTURED_UNITS_RATIO, TOTAL_DEMAND_UNITS, TOTAL_DEMAND_AMOUNT, INVENTORY_UNIT_CHANGE, GROSS_UNITS, GROSS_PRICE, GROSS_AMOUNT, NET_SALES_PRICE, NET_SALES_AMOUNT, BATCH_ID, ORGANIZATION_KEY};

    private static final String[] EXCEL_FILEMGT_LOOKUP_DETAILS_HEADER = new String[]{"Year", MONTH, "Item #", ITEM_NAME_LABEL, FORECAST_DATE, PRICE_LABEL, UNITS, "Dollars"};

    private static final String[] EXCEL_FILEMGT_LOOKUP_INVENTORY_DETAILS_HEADER = new String[]{"Year", MONTH, "Day", "Week", "Company ID", "Company Name", ITEM_ID1, ITEM_NAME_LABEL, UNITS_WITHDRAWN, AMOUNT_WITHDRAWN, PRICE_LABEL, CREATED_BY, "Created Date", "Modified By", MODIFIED_DATE, "Add Chg Del Indicator", BATCH_ID1, "Source ID", FORECAST_NAME, "Forecast Ver", COUNTRY, ORGANIZATION_KEY};

    private static final String[] EXCEL_FILEMGT_LOOKUP_INVENTORY_SUMMARY_HEADER = new String[]{"Year", MONTH, "Day", "Week", ITEM_ID1, ITEM_IDENTIFIER_CODE_QUALIFIER, ITEM_IDENTIFIER, UNITS_WITHDRAWN, AMOUNT_WITHDRAWN, PRICE_LABEL, BATCH_ID1, ORGANIZATION_KEY};


    private SessionDTO sessionDTO = new SessionDTO();

    private CommonUIUtil() {

    }
    private static CommonUIUtil object;

    public static synchronized CommonUIUtil getInstance() {
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
    public SessionDTO getSessionDTO() {
		return sessionDTO;
	}

	public void setSessionDTO(SessionDTO sessionDTO) {
		this.sessionDTO = sessionDTO;
	}
        
    public static Object[] getfileMgmtHistoryResultColumns() {
        return FILE_MGMT_HISTORY_RESULT_COLUMNS.clone();
    }

    public static String[] getfileMgmtHistoryResultHeader() {
        return FILE_MGMT_HISTORY_RESULT_HEADER.clone();
    }

    public static Object[] getfileMgmtLookupResultColumns() {
        return FILE_MGMT_LOOKUP_RESULT_COLUMNS.clone();
    }

    public static String[] getfileMgmtLookupResultHeader() {
        return FILE_MGMT_LOOKUP_RESULT_HEADER.clone();
    }

    public static Object[] getfileMgmtLookupDetailsColumns() {
        return FILE_MGMT_LOOKUP_DETAILS_COLUMNS.clone();
    }

    public static String[] getfileMgtLookupDemandDetailsHeader() {
        return FILE_MGT_LOOKUP_DEMAND_DETAILS_HEADER.clone();
    }

    public static Object[] getfileMgmtLookupDemandDetailsColumns() {
        return FILE_MGMT_LOOKUP_DEMAND_DETAILS_COLUMNS.clone();
    }

    public static String[] getfileMgmtDetailsLookupHeader() {
        return FILE_MGMT_DETAILS_LOOKUP_HEADER.clone();
    }
    
    public static String[] getfileMgtLookupAdjustedDemandDetailsHeader() {
        return FILE_MGT_LOOKUP_ADJUSTED_DEMAND_DETAILS_HEADER.clone();
    }

    public static Object[] getfileMgtLookupAdjustedDemandDetailsColumns() {
        return FILE_MGT_LOOKUP_ADJUSTED_DEMAND_DETAILS_COLUMNS.clone();
    }

    public static Object[] getfileMgmtLookupInventoryDetailsSummaryColumns() {
        return FILE_MGMT_LOOKUP_INVENTORY_DETAILA_SUMMARY_COLUMNS.clone();
    }

    public static String[] getfileMgmtLookupInventoryDetailsSummaryHeaders() {
        return FILE_MGMT_LOOKUP_INVENTORY_DETAILS_SUMMARY_HEADERS.clone();
    }

    public static Object[] getfileMgmtLookupInventoryDetailsColumns() {
        return FILE_MGMT_LOOKUP_INVENTORY_DETAILS_COLUMNS.clone();
    }
    
    public static String[] getfileMgmtLookupInventoryDetailsHeader() {
        return FILE_MGMT_LOOKUP_INVENTORY_DETAILS_HEADER.clone();
    }

    public static String[] getfileMgmtLookupCustomerHeader() {
        return FILE_MGMT_LOOKUP_CUSTOMER_HEADER.clone();
    }

    public static Object[] getfileMgmtLookupCustomerColumns() {
        return FILE_MGMT_LOOKUP_CUSTOMER_COLUMNS.clone();
    }

    public static String[] getexcelFileMgtLookupAdjustedDemandDetailsHeader() {
        return EXCEL_FILEMGT_LOOKUP_ADJUSTED_DEMAND_DETAILS_HEADER.clone();
    }

    public static String[] getexcelFileMgtLookupDemandDetailsHeader() {
        return EXCEL_FILEMGT_LOOKUP_DEMAND_DETAILS_HEADER.clone();
    }
    
    public static String[] getexcelFileMgtLookupDetailsHeader() {
        return EXCEL_FILEMGT_LOOKUP_DETAILS_HEADER.clone();
    }

    public static String[] getexcelFileMgtLookupInventoryDetailsHeader() {
        return EXCEL_FILEMGT_LOOKUP_INVENTORY_DETAILS_HEADER.clone();
    }

    public static String[] getexcelFilemgtLookupInventorySummaryHeader() {
        return EXCEL_FILEMGT_LOOKUP_INVENTORY_SUMMARY_HEADER.clone();
    }

    public static Object[] getrelationshipOutboundSearchColumns() {
        return RELATIONSHIP_OUTBOUND_SEARCH_COLUMNS.clone();
    }

    public static String[] getrelationshipOutboundSearchHeader() {
        return RELATIONSHIP_OUTBOUND_SEARCH_HEADER.clone();
    }
    
    public static Object[] getrelationshipOutboundExcelColumns() {
        return RELATIONSHIP_OUTBOUND_EXCEL_COLUMNS.clone();
    }

    public static String[] getrelationshipOutboundExcelHeader() {
        return RELATIONSHIP_OUTBOUND_EXCEL_HEADER.clone();
    }

    public static Object[] gethierarchyOutboundSearchColumns() {
        return HIERARCHY_OUTBOUND_SEARCH_COLUMNS.clone();
    }

    public static String[] gethierarchyOutboundSearchHeader() {
        return HIERARCHY_OUTBOUND_SEARCH_HEADER.clone();
    }
   
}
