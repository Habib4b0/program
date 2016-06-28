/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.common.util;

import com.stpl.app.adminconsole.common.dto.SessionDTO;
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

    public static final Object[] FILE_MGT_HISTORY_RESULT_COLUMNS = new Object[]{"file", "effectiveDate", "type", "version", "fromPeriod", "toPeriod"};

    public static final String[] FILE_MGT_HISTORY_RESULT_HEADER = new String[]{"File", "Effective Date", "Type", "Version", "From Period", "To Period"};

    public static final Object[] FILE_MGT_LOOKUP_RESULT_COLUMNS = new Object[]{"fileType", "country", "fileName", "type", "version", "fromDate", "toDate"};

    public static final String[] FILE_MGT_LOOKUP_RESULT_HEADER = new String[]{"File Type", "Country", "File Name", "Type", "Version", "From Date", "To Date"};

    public static final Object[] FILE_MGT_LOOKUP_DETAILS_COLUMNS = new Object[]{"check", "year", "month", "itemNo", "itemName", "startDate", "price", "units", "dollars"};

    public static final String[] FILE_MGT_LOOKUP_DEMAND_DETAILS_HEADER = new String[]{ConstantsUtils.EMPTY, "Forecast Type", "Forecast Year", "Forecast Month",
        "Item Id", "Item Identifier Code Qualifier", "Item Identifier",
        "Brand Id", "Segment", "Market Size Units", "Market Share Ratio",
        "Market Share Units", "Uncaptured Units", "Uncaptured Units Ratio",
        "Total Demand Units", "Total Demand Amount", "Inventory Unit Change",
        "Gross Units","Gross Price","Gross Amount","Net Sales Price",
        "Net Sales Amount", "Batch Id", "Organization Key"};
    public static final Object[] FILE_MGT_LOOKUP_DEMAND_DETAILS_COLUMNS = new Object[]{"check", "forecastType", "forcastYear", "forecastMonth", "itemId",
        "itemIdentifierCodeQualifier", "itemIdentifier", "brandId", "segment", "marketSizeUnits", "marketShareRatio", "marketShareUnits", "uncapturedUnits", "uncapturedUnitsRatio", "totalDemandUnits", "totalDemandAmount", "inventoryUnitChange", "grossUnits", "grossPrice", "grossAmount", "netSalesPrice", "netSalesAmount", "batchId", "organizationKey"};

    public static final String[] FILE_MGT_LOOKUP_ADJUSTED_DEMAND_DETAILS_HEADER = new String[]{ConstantsUtils.EMPTY, "Item ID", "Item Name", "Brand ID", "Brand Name", "Segment", "Year",
        "Month", "Market Size Units", "Market Share Ratio", "Market Share Units", "Uncaptured Units",
        "Uncaptured Units Ratio", "Total Demand Units", "Total Demand Amount", "Inventory Unit Change",
        "Gross Units", "Gross Price", "Gross Amount", "Net Sales Price", "Net Sales Amount",
        "Batch ID", "Source", "Organization Key"};
    public static final Object[] FILE_MGT_LOOKUP_ADJUSTED_DEMAND_DETAILS_COLUMNS = new Object[]{"check", "itemId", "itemName", "brandId", "brandName", "segment",
        "forcastYear", "forecastMonth", "marketSizeUnits", "marketShareRatio", "marketShareUnits", "uncapturedUnits", "uncapturedUnitsRatio", "totalDemandUnits", "totalDemandAmount", "inventoryUnitChange", "grossUnits", "grossPrice", "grossAmount", "netSalesPrice", "netSalesAmount", "batchId", "source", "organizationKey"};

    public static final Object[] FILE_MGT_LOOKUP_INVENTORY_DETAILS_SUMMARY_COLUMNS = new Object[]{"check", "year", "month", "day", "week", "itemId", "itemIdentifierCodeQualifier", "itemIdentifier",
        "unitsWithdrawn", "amountWithdrawn", "price", "batchId", "organizationKey"};

    public static final String[] FILE_MGT_LOOKUP_INVENTORY_DETAILS_SUMMARY_HEADER = new String[]{"", "Year", "Month", "Day", "Week", "Item ID", "Item Identifier Code Qualifier", "Item Identifier",
        "Units Withdrawn", "Amount Withdrawn", "Price", "Batch ID", "Organization Key"};

    public static final Object[] FILE_MGT_LOOKUP_INVENTORY_DETAILS_DETAILS_COLUMNS = new Object[]{"check", "year", "month", "day", "week", "companyId", "companyName", "itemId", "itemName", 
        "unitsWithdrawn", "amountWithdrawn", "price", "createdBy", "createdDate", "modifiedBy", "modifiedDate", "inboundStatus", "batchId", "source", "forecastName", "forecastVersion", "country", "organizationKey" };

    public static final String[] FILE_MGT_LOOKUP_INVENTORY_DETAILS_DETAILS_HEADER = new String[]{"", "Year", "Month", "Day", "Week", "Company ID", "Company Name", "Item ID", "Item Name", 
        "Units Withdrawn", "Amount Withdrawn", "Price", "Created By", "Created Date", "Modified By", "Modified Date", "Add Chg Del Indicator", "Batch ID", "Source ID", "Forecast Name", "Forecast Ver", "Country", "Organization Key" };

    public static final String[] FILE_MGT_LOOKUP_DETAILS_HEADER = new String[]{ConstantsUtils.EMPTY, "Year", "Month", "Item #", "Item Name", "Forecast Date", "Price", "Units", "Dollars"};

    public static final Object[] AC_SEARCH_RESULT_COLUMNS = new Object[]{"resultRSName", "resultRSDesc", "resultRSType", "resultRSHierarchyName", "versionNo", "startDate", "createdDate", "modifiedDate",
        "resultCreatedBy"};

    public static final String[] AC_SEARCH_RESULT_HEADER = new String[]{"Relationship Name", "Relationship Description", "Relationship Type", "Hierarchy Name", "version No", "Start Date", "Creation Date",
        "Modified Date", "Created By"};

    public static final Object[] AC_HB_SEARCH_RESULT_COLUMNS = new Object[]{"hierarchyName", "hierarchyType", "hierarchyCategoryInString", "noOfLevels", "versionNo", "createdDate", "modifiedDate", "createdBy"};

    public static final String[] AC_HB_SEARCH_RESULT_HEADER = new String[]{"Hierarchy Name", "Hierarchy Type", "Hierarchy Category", "# of Levels", "Version", "Creation Date", "Modified Date", "Created By"};

    public static final Object[] AC_HB_LEVEL_DEFINITION_COLUMNS = new Object[]{"levelNo", "levelName", "tableName", "fieldName", "userDefinedOrLinked"};

    public static final String[] AC_HB_LEVEL_DEFINITION_HEADER = new String[]{"Level #", "Level Name", "Table Name", "Field Name", "User Defined/ Linked"};

    public static final Object[] AC_HB_LEVEL_VALUES_COLUMNS = new Object[]{"levelValues"};

    public static final String[] AC_HB_LEVEL_VALUES_HEADER = new String[]{"Level Values"};

    public static final Object[] TABLE_LOOKUP_COLUMN = new Object[]{"tableName"};

    public static final String[] TABLE_LOOKUP_HEADER = new String[]{"Table Name"};

    public static final Object[] FIELD_LOOKUP_COLUMN = new Object[]{"fieldName"};

    public static final String[] FIELD_LOOKUP_HEADER = new String[]{"Field Name"};

    public static final Object[] RB_SELECTED_LEVEL_COLUMN = new Object[]{"levelValue"};

    public static final Object[] RB_AVAILABLE_LEVEL_COLUMN = new Object[]{"levelValue"};

    public static final String HIERARCHY_CATEGORY = "HIERARCHY_CATEGORY";

    public static final String RELATIONSHIP_TYPE = "RELATIONSHIP_TYPE";

    public static final Object[] ITEM_RESULTS_COLUMNS = new Object[]{
        "itemId", "itemNo", "itemCode", "itemName", "itemDesc", "itemStartDate", "itemEndDate", "itemStatus",
        "therapeuticClass", "brand", "form", "strength", "packageSizeCode", "packageSizeIntroDate", "upps",
        "manufacturerId", "manufacturerNo", "manufacturerName", "labelerCode", "organizationKey",
        "acquisitionDate", "authorizedGeneric", "authorizedGenericStartDate", "authorizedGenericEndDate",
        "firstSaleDate", "itemTypeIndicator", "itemClass", "itemType", "marketTerminationDate", "newFormulationIndicator",
        "newFormulation", "newFormulationStartDate", "newFormulationEndDate", "pediatricExclusiveIndicator",
        "pediatricExclusiveStartDate", "pediatricExclusiveEndDate", "clottingFactorIndicator", "clottingFactorStartDate",
        "clottingFactorEndDate", "primaryUom", "secondaryUom", "shelfLife", "shelfLifeType", "dualPricingIndicator",
        "itemFamilyId", "udc1", "udc2", "udc3", "udc4", "udc5", "udc6", "acquiredAmp", "acquiredBamp", "obraBamp", "dra",
        "dosesPerUnit", "discontinuationDate", "lastLotExpirationDate", "ndc9", "ndc8", "displayBrand", "innovatorCode",
        "baselineAmp", "baseYearCpi"};

    public static final String[] ITEM_RESULTS_HEADER = new String[]{
        "Item ID", "Item No", "Item Code", "Item Name", "Item Desc", "Item Start Date", "Item End Date",
        "Item Status", "Therapeutic Class", "Brand", "Form", "Strength", "Package Size Code",
        "Package Size Intro Date", "UPPS", "Manufacturer ID", "Manufacturer No", "Manufacturer Name",
        "Labeler Code", "Organization Key", "Acquisition Date", "Authorized Generic", "Authorized Generic Start Date",
        "Authorized Generic End Date", "First Sale Date", "Item Type Indicator", "Item Class ", "Item Type ",
        "Market Termination Date", "New Formulation Indicator", "New Formulation", "New Formulation Start Date",
        "New Formulation End Date", "Pediatric Exclusive Indicator", "Pediatric Exclusive Start Date",
        "Pediatric Exclusive End Date", "Clotting Factor Indicator", "Clotting Factor Start Date",
        "Clotting Factor End Date", "Primary UOM", "Secondary UOM", "Shelf Life", "Shelf Life Type",
        "Dual Pricing Indicator", "Item Family ID", "UDC 1", "UDC 2", "UDC 3", "UDC 4", "UDC 5", "UDC 6", "Acquired AMP",
        "Acquired BAMP", "OBRA BAMP", "DRA", "Doses per Unit", "Discontinuation Date", "Last Lot Expiration Date",
        "NDC9", "NDC8", "Display Brand", "Innovator Code", "Baseline AMP", "Base Year CPI"};

    public static final Object[] HIERARCHY_OUTBOUND_SEARCH_COLUMNS = new Object[]{"check", "hierarchyName", "hierarchyTypeDto", "hierarchyCategory", "noOfLevels", "versionNo", "createdDate", "modifiedDate", "createdBy"};

    public static final String[] HIERARCHY_OUTBOUND_SEARCH_HEADER = new String[]{"", "Hierarchy Name", "Hierarchy Type", "Hierarchy Category", "# of Levels", "Version", "Creation Date", "Modified Date", "Created By"};

    public static final Object[] RELATIONSHIP_OUTBOUND_SEARCH_COLUMNS = new Object[]{"check",
        "relationshipName", "relationshipDesc", "relationshipType", "hierarchyName", "versionNo", "startDate", "createdDate", "modifiedDate", "createdBy"};

    public static final String[] RELATIONSHIP_OUTBOUND_SEARCH_HEADER = new String[]{"",
        "Relationship Name", "Relationship Description", "Relationship Type", "Hierarchy Name", "Version No", "Start Date", "Creation Date", "Modified Date", "Created By"};

    public static final Object[] RELATIONSHIP_OUTBOUND_EXCEL_COLUMNS = new Object[]{"relationshipName", "relationshipDescription", "relationshipType", "hierarchyLevelName", "relationshipTree", "hierarchyName", "hierarchyVersionNo", "startDate", "buildType"};

    public static final String[] RELATIONSHIP_OUTBOUND_EXCEL_HEADER = new String[]{"Relationship Name", "Relationship Description", "Relationship Type", "Hierarchy Level Name", "Relationship", "Hierarchy Name", "Hierarchy Version No", "Start Date", "Build Type"};

    public static final String[] FILE_MGT_LOOKUP_CUSTOMER_HEADER = new String[]{"Forecast Year", "Forecast Month",
        "Item Id", "Company Id", "Units", "Price Type",
        "Price", "Sales Amount", "Sales Inclusion", "Deduction Id",
        "Deduction Category", "Deduction Type", "Deduction Program Type",
        "Adjustment Code", "Deduction Rate", "Deduction Amount",
        "Deduction Inclusion", "Forecast Value Type", "Brand ID", "Segment", "Batch Id", "Organization Key", "Forecast Version", "Country", "Forecast Name",
        "Forecast Date", "Forecast Interface Id"};
    public static final Object[] FILE_MGT_LOOKUP_CUSTOMER_COLUMNS = new Object[]{"forcastYear", "forecastMonth", "itemId",
        "companyId", "units", "priceType", "price", "salesAmount", "salesInclusion", "deductionId", "deductionCategory", "deductionType", "deductionProgramType", "adjustmentCode", "deductionRate", "deductionAmount", "deductionInclusion", "forecastValueType", "brandId", "segment", "batchId", "organizationKey", "forecastVersion", "country", "forecastName",
        "forecastDate", "customerGtsForecastIntfId"};

    SessionDTO sessionDTO = new SessionDTO();

    private CommonUIUtil() {

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
}
