package com.stpl.app.util;

import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.app.transactional.common.logic.SearchLogic;
import com.stpl.app.transactional.common.util.QueryUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.ARMConstants;
import com.vaadin.data.Property;
import org.jboss.logging.Logger;

import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.asi.ui.custommenubar.MenuItemDTO;
import java.io.File;
import java.util.List;
import java.util.ListIterator;
import org.vaadin.alump.beforeunload.BeforeUnload;
/**
 * *.
 *
 * @author manikanta
 */
public final class CommonUIUtils {

    public static SearchLogic logic = new SearchLogic();

    /**
     * Visible Columns for Forecast Sales Interface.
     */
    private static final Logger LOGGER = Logger.getLogger(CommonUIUtils.class);

    /**
     * The Constant FORECASTING_SALES_COLUMNS.
     */
    public final Object[] forecastingSalesColumns = new Object[]{"forecastName", "forecastYear", "forecastMonth", "country", "ndc", "forecastDate", "forecastStartDate", "forecastVer",
        ConstantUtil.PRICE, "forecastValue", "dollars", "modifiedDate", "actualSalesPercentage", "actualSalesPercentageMonth", "percentageEstimate", "percentageEstimateYear", "units",
        "forecastValueType", "forecastInterfaceId", "product", "forecastedSalesPercentageMonth", "segment", "brand", "forecastedSalesPercentage"};

    /**
     * Header and for Forecast Sales Interface.
     */
    public final String[] forecastingSalesHeader = new String[]{"Forecast Name", "Forecast Year", "Forecast Month", "Country", "NDC", "Forecast Date", "Forecast Start Date",
        "Forecast Version", "Price", "Forecast Value", "Dollars", "Modified Date", "Actual Sales Percentage", "Actual Sales Percentage Month", "Percentage Estimate", "Percentage Estimate Year",
        "Units", "Forecast Value Type", "Forecast Interface ID", "Product", "Forecasted Sales Percentage Month", "Segment", "Brand", "Forecasted Sales Percentage"};
    public final String CONTRACT_NO1 = "contractNo";
    public final String ITEM_NO1 = "itemNo";
    public final String CONTRACT_ID1 = "contractId";

    /**
     * Visible Columns for Best Price Interface.
     */
    public final Object[] bestPriceColumns = new Object[]{ConstantUtil.ITEMID, ITEM_NO1, CONTRACT_NO1, CONTRACT_ID1, ConstantUtil.ACCOUNTID, "period", "initialBestPrice"};
    public static final String ITEM_ID = "Item ID";
    public static final String ITEM_NO = "Item No";
    public static final String CONTRACT_NO = "Contract No";
    public static final String CONTRACT_ID = "Contract ID";
    public static final String ACCOUNT_ID = "Account ID";

    /**
     * Header for Best Price Interface.
     */
    public final String[] bestPriceHeader = new String[]{ITEM_ID, ITEM_NO, CONTRACT_NO, CONTRACT_ID, ACCOUNT_ID, "Period", "Initial Best Price"};

    /**
     * * Visible Columns for Formula Details Interface.
     */
    public final Object[] formulaDetailsColumns = new Object[]{"formulaId", "formulaNo", "formulaDesc", "companyId", "itemId", "startDate", "endDate", "contractPrice1", "contractPrice2", "contractPrice3",
        "rebatePercent1", "rebatePercent2", "rebatePercent3"};

    /**
     * Header for Formula Details Interface.
     */
    public final String[] formulaDetailsHeader = new String[]{"Formula ID", "Formula No", "Formula Desc", "Company ID", ITEM_ID, "Start Date", "End Date", "Contract Price 1", "Contract Price 2", "Contract Price 3",
        "Rebate Percent 1", "Rebate Percent 2", "Rebate Percent 3"};

    /**
     * Visible Columns for Audit Inbound Interface.
     */
    public final Object[] auditInboundColumns = new Object[]{"auditId", "applicationProcess", "status", "source", "batchId", "interfaceRunStartDate", "interfaceRunEndDate",
        "sentRecordCount", "sentRecordAmount", "receivedRecordAmount", "addCount", "changeCount", "deleteCount", "invalidRecordCount", "invalidRecordAmount", "discrepancyAmount",
        "validRecordAmount"};
    public final String STATUS = "Status";

    /**
     * Header for Audit Inbound Interface.
     */
    public final String[] auditInboundHeader = new String[]{"Audit ID", "Application Process", STATUS, "Source", "Batch ID", "Interface Run Start Date", "Interface Run End Date",
        "Sent Record Count", "Sent Record Amount", "Received Record Amount", "Add Count", "Change Count", "Delete Count", "Invalid Record Count", "Invalid Record Amount", "Discrepancy Amount",
        "Valid Record Amount"};
    public static final String PROVISION_ID = "provisionId";
    public static final String UPLOAD_DATE = "uploadDate";
    public static final String ACTIVE = "isActive";
    public static final String ACCOUNT_NO = "accountNo";
    public static final String ACCOUNT_NO_LABEL = "Account No";

    /**
     * Visible Columns for Actuals Master Interface.
     */
    public final Object[] actualsColumn = new Object[]{ConstantUtil.ACCOUNTID, "programStateCode", "settlementNo", "accrualActualEndDate", "actualId", "divisionId", "organizationKey",
        "actualsMasterId", "quantityInclusion", "cashPaidDate", "source", "analysisCode", "accrualActualStartDate", "salesAmount", "quantity", "sentOut", ACCOUNT_NO, "accountName", PROVISION_ID, "amount", "marketId", ACTIVE, "settlementMethodNo", "itemIdentifierCodeQualifier", "priceAdjustmentName", "recordSequence", "mandatedDiscountAmount",
        "parentcomDivmktBrandProdkey", ConstantUtil.PRICE, "dispensedDate", ConstantUtil.ITEMID, "accrualProcessed", UPLOAD_DATE, "invoiceLineNo", ITEM_NO1, "acctIdentifierCodeQualifier",
        CONTRACT_ID1, "brandId", "comDivMktBrandProdKey", "claimIndicator", "provisionClaimIndicator"};
    public final String PROVISION_ID1 = "Provision ID";
    public final String UPLOAD_DATE_LABEL = "Upload Date";

    /**
     * Header for Actuals Master Interface.
     */
    public final String[] actualsTableHeader = new String[]{ACCOUNT_ID, "Program State Code", "Settlement No", "Accrual Actual End Date", "Actual ID", "Division ID", "Organization Key",
        "Actuals Master ID", "Quantity Inclusion", "Cash Paid Date", "Source", "Analysis Code", "Accrual Actual Start Date", "Sales Amount", "Quantity", "Sent Out", ACCOUNT_NO_LABEL, "Account Name", PROVISION_ID1, "Amount", "Market ID", "Is Active", "Settlement Method No", "Item Identifier Code Qualifier", "Price Adjustment Name", "Record Sequence", "Mandated Discount Amount",
        "Parentcom Divmkt Brand Prodkey", ConstantUtil.PRICE_DUP, "Dispensed Date", ITEM_ID, "Accrual Processed", UPLOAD_DATE_LABEL, "Invoice Line No", ITEM_NO, "Acct Identifier Code Qualifier", CONTRACT_ID, "Brand ID", "Com DivMkt Brand ProdKey", "Claim Indicator", "Provision Claim Indicator"};

    /**
     * Visible Columns for CPI Index Interface.
     */
    public final Object[] cpiColumns = new Object[]{"indexId", "status", "indexType", "effectiveDate", "indexValue"};

    /**
     * Header for CPI Index Interface.
     */
    public final String[] cpiTableHeader = new String[]{"Index ID", STATUS, "Index Type", "Effective Date", "Index Value"};

    /**
     * Visible Columns for GL Balance Interface.
     */
    public final Object[] glBalColumns = new Object[]{ConstantUtil.ACCOUNTID, ACCOUNT_NO, "balance", UPLOAD_DATE, ACTIVE, "insertedDateField", "year", "period", "closeIndicator"};

    /**
     * Header for GL Balance Interface.
     */
    public final String[] glBalTableHeader = new String[]{ACCOUNT_ID, ACCOUNT_NO_LABEL, "Balance", UPLOAD_DATE_LABEL, STATUS, "Inserted Date", "Year", "Period", "Close Indicator"};

    /**
     * Visible Columns for GL Cost Center Interface.
     */
    public final Object[] glCostColumns = new Object[]{"companyCode", "companyCostCenter", "ndc8", UPLOAD_DATE};

    /**
     * Header for GL Cost Center Interface.
     */
    public final String[] glCostTableHeaders = new String[]{"Company Code", "Company Cost Center", "NDC 8", UPLOAD_DATE_LABEL};

    /**
     * Visible Columns for Item Hierarchy Interface.
     */
    public final Object[] itemHierarchyColumns = new Object[]{"level0", "level1", "level2", "level3", "level4", "level5", "level6", "level7"};

    /**
     * Header for Item Hierarchy Interface.
     */
    public final String[] itemHierarchyTableHeader = new String[]{"Level 0", "Level 1", "Level 2", "Level 3", "Level 4", "Level 5", "Level 6", "Level 7"};

    /**
     * Visible Columns for Item Hierarchy Definition Interface.
     */
    public final Object[] itemHierarchyDefnColumns = new Object[]{"member", "alias", "bpiLvl"};

    /**
     * Header for Item Hierarchy Definition Interface.
     */
    public final String[] ItemHierarchyDefnHeaders = new String[]{"Member", "Alias", "BPI Level"};

    /**
     * Visible Columns for Lot Master Interface.
     */
    public final Object[] lotMasterColumns = new Object[]{"lotNo", ConstantUtil.ITEMID, "lastLotSoldDate", "lotExpirationDate"};

    /**
     * Header for Lot Master Interface.
     */
    public final String[] lotMasterTableheader = new String[]{"Lot No", ITEM_ID, "Last Lot Sold Date", "Lot Expiration Date"};

    /**
     * Visible Columns Master Data Attribute Interface.
     */
    public final Object[] masterDataAttributeColumns = new Object[]{"masterId", "masterType", "masterAttribute", "column1", "column2", "column3", "column4", "column5", "column6",
        "column7", "column8", "column9", "column10", "column11", "column12", "column13", "column14", "column15", "column16", "column17", "column18", "column19", "column20"};

    /**
     * Header Master Data Attribute Interface.
     */
    public final String[] masterDataAttributeHeaders = new String[]{"Master ID", "Master Type", "Master Attribute", "Column 1", "Column 2", "Column 3", "Column 4", "Column 5",
        "Column 6", "Column 7", "Column 8", "Column 9", "Column 10", "Column 11", "Column 12", "Column 13", "Column 14", "Column 15", "Column 16", "Column 17", "Column 18", "Column 19",
        "Column 20"};

    /**
     * Visible Columns Sales Master Interface.
     */
    public final Object[] salesColumns = new Object[]{"salesId", ConstantUtil.ITEMID, ITEM_NO1, ConstantUtil.ACCOUNTID, ACCOUNT_NO, CONTRACT_ID1, CONTRACT_NO1, "companyId",
        "invoiceNumber", "invoiceDate", "organizationKey", "itemCodeQualifier", "parentItemId", "itemParentNo", "itemUom", "docType", "orderNumber", PROVISION_ID, "orderReceivedDate",
        "invoiceLineNumber", "orderType", "orderSubtype", "analysisCode", "priceAdjustmentName", "recordSequence", ConstantUtil.PRICE, "quantity", "lotNo", "amount", "accountType",
        "customerSubtype", "wholesaleOwnerId", "accountName", "identifierCodeQualifier", "customerCompanyCode", ACTIVE, "divisionId", "marketId", "brandId"};

    /**
     * Header Sales Master Interface.
     */
    public final String[] salesTableHeader = new String[]{"Sales ID", ITEM_ID, ITEM_NO, ACCOUNT_ID, ACCOUNT_NO_LABEL, CONTRACT_ID, CONTRACT_NO, "Company ID", "Invoice Number",
        "Invoice Date", "Organization Key", "Item Code Qualifier", "Parent Item ID", "Item Parent No", "Item UOM", "Doc Type", "Order Number", PROVISION_ID1, "Order Received Date",
        "Invoice Line Number", "Order Type", "Order Subtype", "Analysis Code", "Price Adjustment Name", "Record Sequence", "Price", "Quantity", "Lot No", "Amount", "Account Type",
        "Customer Subtype", "Wholesaler Owner ID", "Account Name", "Identifier Code Qualifier", "Customer Company Code", "Is Active", "Division ID", "Market ID", "Brand ID"};

    /**
     * Visible Columns for Average Shelf Life Interface.
     */
    public final Object[] avgColumns = new Object[]{ConstantUtil.ITEMID, "avgShelfLife", "itemIdType"};

    /**
     * Header Average for Shelf Life Interface.
     */
    public final String[] avgTableHeader = new String[]{ITEM_ID, "Avg Shelf Life", "Item ID Type"};

    /**
     * Visible Columns for Average Shelf Life Interface.
     */
    public final Object[] accrualColumns = new Object[]{"glCompanyName", "costCenter", "glAccount", "customerNo", "customerName", CONTRACT_NO1, "contractName", "contractHolder", "brand", ITEM_NO1, "itemName", "accrualType", PROVISION_ID, "accrualPeriodStartDate", "accrualPeriodEndDate"};

    /**
     * Header Average for Shelf Life Interface.
     */
    public final String[] accrualHeader = new String[]{"GL Company Name", "Cost Center", "GL Account", "Customer No", "Customer Name", CONTRACT_NO, "Contract Name", "Contract Holder", "Brand", ITEM_NO, "Item Name", "Accrual Type", PROVISION_ID1, "From Accrual Period Start Date", "To Accrual Period End Date"};

    public final Object[] viewSearchLookupColumns = new Object[]{"viewName", "viewType", "createdDate", "createdBy", "modifiedDate", "modifiedBy"};

    public final String[] viewSearchLookupHeader = new String[]{"View Name", "View Type", "Created Date", "Created By", "Modified Date", "Modified By"};

    /**
     * Assigned Integer value to Excel Export offset.
     */
    public static final int EXCEL_EXPORT_OFFSET = 100000;

    /**
     * The Constructor.
     */
    private CommonUIUtils() {

    }
    private static CommonUIUtils object;
    
    public static CommonUIUtils getInstance() {
        if (object == null) {
            object = new CommonUIUtils();
        }
        return object;
    }

    /**
     * Success notification.
     *
     * @param message the message
     * @Comments: Added by Mani
     * @Purpose: This method is used to display Success Notification after save
     */
    public static void successNotification(final String message) {
        try {
            LOGGER.debug("Entering successNotification with message value :::: " + message);
            final Notification notif = new Notification(message, Notification.Type.HUMANIZED_MESSAGE);
            notif.setPosition(Position.MIDDLE_CENTER);
            notif.setStyleName("mystyle");
            notif.show(Page.getCurrent());
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.debug("Ends successNotification");
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
    public static final void beforeUnloadCloseUi(final UI uI, final String moduleName) {
        BeforeUnload ob = BeforeUnload.closeBeforeUnload(uI);
        ob.addUnloadListener(new BeforeUnload.UnloadListener() {
            @Override
            public void unload(BeforeUnload.UnloadEvent event) {
                try {
                    if (ConstantUtil.ARP_OUTBOUND.equals(moduleName) || ConstantUtil.ST_CFF_OUTBOUND.equals(moduleName) || ConstantUtil.VW_CUSTOMER_GTS_FORECAST.equals(moduleName)) {
                        List<String> fileList = null;
                        if (ConstantUtil.ARP_OUTBOUND.equals(moduleName)) {
                            logic.deleteARPTempTable();
                            fileList = (List) VaadinSession.getCurrent().getAttribute("ARP_EXPORT_FILE_DIR");
                        } else if (ConstantUtil.VW_CUSTOMER_GTS_FORECAST.equals(moduleName)) {
                            fileList = (List) VaadinSession.getCurrent().getAttribute("GTS_FORECAST_DIR");
                        } else {
                            logic.deleteCFFTempTable();
                            fileList = (List) VaadinSession.getCurrent().getAttribute("CFF_EXPORT_FILE_DIR");
                        }

                        if (fileList != null && !fileList.isEmpty()) {
                            LOGGER.debug("Files: " + fileList);
                            for (ListIterator<String> it = fileList.listIterator(); it.hasNext();) {
                                String fileName = it.next();
                                LOGGER.debug("Processing file " + fileName);
                                it.remove();
                                if (!"null".equals(fileName) && !"".equals(fileName.trim())) {
                                    File exportDir = new File(fileName);
                                    if (exportDir.isDirectory()) {
                                        File[] exportedFiles = exportDir.listFiles();
                                        for (File exportedFile : exportedFiles) {
                                            if (exportedFile.exists()) {
                                                String expFileName = exportedFile.getAbsolutePath();
                                                boolean deleted = exportedFile.delete();
                                                LOGGER.debug("Deleting file " + expFileName + ": " + deleted);
                                            }
                                        }
                                        LOGGER.debug("Deleting directory " + exportDir.getAbsolutePath());
                                        exportDir.delete();
                                    } else {
                                        LOGGER.debug("Not a directory");
                                    }
                                } else {
                                    LOGGER.debug("Cannot delete file");
                                }
                            }
                        }
                    }
                    uI.close();
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        });

    }

    public static HorizontalLayout getResponsiveControls(HorizontalLayout tempLayout) {
        HorizontalLayout controlBar = new HorizontalLayout();
        controlBar.setStyleName("responsivePagedTable");

        HorizontalLayout pageSize = (HorizontalLayout) tempLayout.getComponent(0);
        HorizontalLayout pageManagement = (HorizontalLayout) tempLayout.getComponent(1);

        CssLayout cssLayout = new CssLayout();
        cssLayout.setSizeFull();
        cssLayout.addComponent(pageSize.getComponent(0));
        cssLayout.addComponent(pageSize.getComponent(0));
        for (int index = 0; index < NumericConstants.EIGHT; index++) {
            cssLayout.addComponent(pageManagement.getComponent(0));
        }
        controlBar.addComponent(cssLayout);
        return controlBar;
    }

    public static ComboBox getCustomizedComboBox(ComboBox select) {
        select.select("10");
        select.removeItem("25");
        select.removeItem("50");
        select.removeItem("100");
        select.removeItem("600");
        return select;
    }

    public static final CustomMenuBar.CustomMenuItem loadDeductionsDdlb(final ComboBox comboBox, final CustomMenuBar menuBar) {
        menuBar.setScrollable(true);
        menuBar.setPageLength(NumericConstants.TEN);
        final CustomMenuBar.CustomMenuItem customMenuItemDed = menuBar.addItem("  - Select Value -  ", null);
        comboBox.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (comboBox.getValue() != null) {
                    if (Integer.parseInt(String.valueOf(comboBox.getValue())) != 0) {
                        int id = (int) comboBox.getValue();
                        String description = comboBox.getItemCaption(id);
                        if (description != null) {
                            List<Object[]> list = loadDeductionsValues(description);
                            CustomMenuBar.CustomMenuItem[] customItem = new CustomMenuBar.CustomMenuItem[list.size()];
                            customMenuItemDed.removeChildren();
                            for (int i = 0; i < list.size(); i++) {
                                String header = String.valueOf(list.get(i)[1]);
                                String column = header.replace(" ", StringUtils.EMPTY);
                                MenuItemDTO dto = new MenuItemDTO(column, header);
                                dto.setId(Integer.valueOf(list.get(i)[0].toString()));
                                customItem[i] = customMenuItemDed.addItem(dto, null);
                                customItem[i].setCheckable(true);
                                customItem[i].setItemClickable(true);
                                customItem[i].setItemClickNotClosable(true);
                            }
                        }
                    } else {
                        customMenuItemDed.removeChildren();
                    }
                } else {
                    customMenuItemDed.removeChildren();
                }
            }
        });
        return customMenuItemDed;
    }

    public static ComboBox loadComboBoxWithIntegerForComboBox(final ComboBox select, String listName, boolean isFilter) {
        try {
            select.removeAllItems();
            select.setImmediate(true);
            select.setNullSelectionAllowed(false);
            select.setData(listName);
            select.addItem(0);
            select.setItemCaption(0, isFilter ? ConstantsUtils.SHOW_ALL : ConstantsUtils.SELECT_ONE);
            List<com.stpl.ifs.util.HelperDTO> list = HelperListUtil.getInstance().getListNameMap().get(listName);
            if (list != null && !list.isEmpty()) {
                for (com.stpl.ifs.util.HelperDTO helperDTO : list) {
                    select.addItem(helperDTO.getId());
                    select.setItemCaption(helperDTO.getId(), helperDTO.getDescription());
                }
            }
            select.select(0);
            select.markAsDirty();
            select.setDescription((String) (select.getValue() == CommonUtils.DASH ? ConstantsUtils.SELECT_ONE : select.getItemCaption(select.getValue())));
        } catch (Exception e) {
            LOGGER.debug("Error while loading Drop down :" + listName + " with :" + e);
        }
        return select;
    }

    private static List<Object[]> loadDeductionsValues(String deductionLevel) {
        Map<String, String> summaryLevelMap = loadDedutionMap();
        String deductionColumn = summaryLevelMap.get(deductionLevel);
        List deductionsValues = QueryUtils.loadDeductionsValues(new ArrayList<>(Arrays.asList(deductionColumn, deductionColumn, QueryUtils.splitter(deductionColumn))), "loadDeductionValue");
        return deductionsValues;
    }

    public static Map<String, String> loadDedutionMap() {
        Map<String, String> deductionLevelMap = new HashMap<>();
        deductionLevelMap.put(ARMConstants.getDeductionCategory(), "A.RS_CATEGORY");
        deductionLevelMap.put(ARMConstants.getDeductionType(), "A.RS_TYPE");
        deductionLevelMap.put(ARMConstants.getDeductionProgram(), "A.REBATE_PROGRAM_TYPE");
        deductionLevelMap.put(ARMConstants.getDeduction(), "A.RS_ID");
        deductionLevelMap.put(ARMConstants.getDeductionCategory2(), "U.UDC2");
        deductionLevelMap.put(ARMConstants.getDeductionCategory3(), "U.UDC3");
        deductionLevelMap.put(ARMConstants.getDeductionCategory4(), "U.UDC4");
        deductionLevelMap.put(ARMConstants.getDeductionCategory5(), "U.UDC5");
        deductionLevelMap.put(ARMConstants.getDeductionCategory6(), "U.UDC6");
        return deductionLevelMap;
    }

}
