/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.salesprojection.form;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.gtnforecasting.abstractforecast.ForecastSalesProjection;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.dto.SalesRowDto;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.logic.GroupFilter;
import com.stpl.app.gtnforecasting.logic.Utility;
import com.stpl.app.gtnforecasting.salesprojection.logic.NMSalesExcelLogic;
import com.stpl.app.gtnforecasting.salesprojection.logic.tablelogic.NMSalesProjectionTableLogic;
import com.stpl.app.gtnforecasting.salesprojection.utils.HeaderUtils;
import com.stpl.app.gtnforecasting.salesprojectionresults.logic.SPRCommonLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.ui.ForecastUI;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.ChangeCustomMenuBarValueUtil;
import com.stpl.app.gtnforecasting.utils.CommonUtil;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import static com.stpl.app.gtnforecasting.utils.CommonUtils.isInteger;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.SalesExcelNM;
import com.stpl.app.gtnforecasting.utils.UISecurityUtil;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import static com.stpl.app.utils.Constants.CommonConstants.ACTION_EDIT;
import static com.stpl.app.utils.Constants.CommonConstants.ACTION_VIEW;
import static com.stpl.app.utils.Constants.CommonConstants.SELECT_ONE;
import static com.stpl.app.utils.Constants.FrequencyConstants.ANNUAL;
import static com.stpl.app.utils.Constants.FrequencyConstants.MONTHLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.QUARTERLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.SEMI_ANNUAL;
import static com.stpl.app.utils.Constants.LabelConstants.PRODUCT;
import com.stpl.app.utils.UiUtils;
import com.stpl.ifs.ui.extfilteringtable.FreezePagedTreeTable;
import com.stpl.ifs.ui.extfilteringtable.PageTreeTableLogic;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.ui.util.converters.DataFormatConverter;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.ui.HorizontalLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;

import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.asi.ui.custommenubar.MenuItemDTO;
import org.asi.ui.extfilteringtable.ExtCustomTreeTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Commercial Sales Projection
 *
 * @author sibi
 */
public class NMSalesProjection extends ForecastSalesProjection {

	private DataFormatConverter percentFormat = new DataFormatConverter("#,##0.000", DataFormatConverter.INDICATOR_PERCENT);
    private final StplSecurity stplSecurity = new StplSecurity();
    private static final Logger LOGGER = LoggerFactory.getLogger(NMSalesProjection.class);
    
    private final SPRCommonLogic sprCommonLogic = new SPRCommonLogic();
    protected NMSalesProjectionTableLogic nmSalesProjectionTableLogic;
    protected String allUpperCase = "ALL";
    private final Map<String, Object> excelParentRecords = new HashMap();
    public static final String SID = "SID";
    private final SessionDTO sessionDTO;

    public static final String SELECT_LEVEL_LABEL = "-Select Level-";
    public static final String SELECT_ALL_LABEL = "Select All";
    public static final String SALES = "Sales";
    public static final String CURRENCY_NO_DECIMAL = "currencyNoDecimal";
    public static final String UNIT_NO_DECIMAL = "unitNoDecimal";
    public static final String UNITS = "Units";
    private static final String ACNT_GRWOTH = "AccountGrowth";
    
    protected CustomMenuBar.SubMenuCloseListener productListener = new CustomMenuBar.SubMenuCloseListener() {
        @Override
        public void subMenuClose(CustomMenuBar.SubMenuCloseEvent event) {
            String menuItemValue = ChangeCustomMenuBarValueUtil.getMenuItemToDisplay(productFilterValues);
            ChangeCustomMenuBarValueUtil.setMenuItemToDisplay(productFilterDdlb, menuItemValue);
            generateProductToBeLoaded = (List) commonLogic.getFilterValues(productFilterValues).get(SID);
            loadCustomerLevelFilter(String.valueOf(customerlevelDdlb.getValue()));
        }
    };

    protected CustomMenuBar.SubMenuCloseListener salesInclusionListener = new CustomMenuBar.SubMenuCloseListener() {
        @Override
        public void subMenuClose(CustomMenuBar.SubMenuCloseEvent event) {
          String salesInclusionValue = ChangeCustomMenuBarValueUtil.getInclusionMenuItemToDisplay(salesInclusionValues);
          ChangeCustomMenuBarValueUtil.setMenuItemToDisplay(salesInclusionDdlb, salesInclusionValue);
        }
    };
    protected CustomMenuBar.SubMenuCloseListener displayFormatListener = new CustomMenuBar.SubMenuCloseListener() {
        @Override
        public void subMenuClose(CustomMenuBar.SubMenuCloseEvent event) {
            String salesInclusionValue = ChangeCustomMenuBarValueUtil.getInclusionMenuItemToDisplay(displayFormatValues);
            ChangeCustomMenuBarValueUtil.setMenuItemToDisplay(displayFormatDdlb, salesInclusionValue);
        }
    };
    protected CustomMenuBar.SubMenuCloseListener cutomerListener = new CustomMenuBar.SubMenuCloseListener() {
        @Override
        public void subMenuClose(CustomMenuBar.SubMenuCloseEvent event) {
            String menuItemValue = ChangeCustomMenuBarValueUtil.getMenuItemToDisplay(customerFilterValues);
            ChangeCustomMenuBarValueUtil.setMenuItemToDisplay(customerFilterDdlb, menuItemValue);
            generateCustomerToBeLoaded = (List) commonLogic.getFilterValues(customerFilterValues).get(SID);
            loadProductLevelFilter(String.valueOf(productlevelDdlb.getValue()));
        }
    };

    public NMSalesProjection(SessionDTO session, String screenName) {
        super(session, screenName);
        enableDisableFields();
        this.sessionDTO = session;
        if (CommonUtil.isValueEligibleForLoading()) {
            loadSalesInclusion();
            loadDisplayFormatDdlb();
            CommonUtil commonUtils = CommonUtil.getInstance();
            commonUtils.loadConvertionFactorComboBox(conversionFactorDdlb, Constant.CONVERSION_FACTOR);
        }
        init();

    }

    /**
     * Init method
     */
    public final void init() {
        LOGGER.debug("Inside NMSalesProjection Screen= {} ", session.getUserId());
        configureProjectionDTO();
        Utility.loadHierarchyList(session);
        if (ACTION_EDIT.getConstant().equalsIgnoreCase(session.getAction()) || ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction())) {
            super.setProjectionSelection(false);
        }
        nmFrequencyDdlb.setValue(session.getDsFrequency());
        generateBtnLogic(null);
        configureGroupDDLB();
        super.configureGraph();
        securityForButton();
    }
    
    public void setFrequency(SessionDTO session)
    {
        nmFrequencyDdlb.setValue(session.getDsFrequency());
    }

    @Override
    protected void levelFilterDdlbChangeOption() {
        projectionDTO.setHierarchyNo(StringUtils.EMPTY);
        if (levelFilter.getValue() != null) {
            projectionDTO.setIsFilter(true);
            projectionDTO.setLevelFilter(true);
            projectionDTO.setLevelFilterValue(String.valueOf(UiUtils.parseStringToInteger(String.valueOf(levelFilter.getValue()).split("-")[0].trim())));
            projectionDTO.setFilterLevelNo(Integer.parseInt(projectionDTO.getLevelFilterValue()));
            nmSalesProjectionTableLogic.setProjectionResultsData(projectionDTO);
            projectionDTO.setLevelFilter(false);
        } else {
            projectionDTO.setIsFilter(false);
            projectionDTO.setLevelFilter(false);
            projectionDTO.setFilterLevelNo(0);
            projectionDTO.setLevelFilterValue(StringUtils.EMPTY);
            nmSalesProjectionTableLogic.setProjectionResultsData(projectionDTO);
        }
    }

    @Override
    protected void excelExportLogic() {
       long startTime = System.currentTimeMillis(); 
        try {
            configureExcelResultTable();
            getExcelSalesCommercial();
            excelTable.setRefresh(BooleanConstant.getTrueFlag());
            excelTable.setDoubleHeaderVisible(false);
            ForecastUI.setEXCELCLOSE(true);
            ExcelExport exp = null;
            int exportAt = projectionDTO.getHeaderMapForExcel().size() - 1;
            if ((QUARTERLY.getConstant().equals(String.valueOf(nmFrequencyDdlb.getValue())) || MONTHLY.getConstant().equals(String.valueOf(nmFrequencyDdlb.getValue())))) {
                for (int i = 0; i < projectionDTO.getHeaderMapForExcel().size(); i++) {
                    Object[] column = ((List<Object>) projectionDTO.getHeaderMapForExcel().get(i).get(0)).toArray();
                    column = ArrayUtils.removeElement(column, "levelName");

                    Object[] header = ((List<Object>) projectionDTO.getHeaderMapForExcel().get(i).get(1)).toArray();
                    header = ArrayUtils.remove(header, 0);

                    Object[] displayFormatIndex = CommonUtil.getDisplayFormatSelectedValues(displayFormatValues);
                    if (displayFormatIndex.length == 1) {
                        for (int k = 0; k < displayFormatIndex.length; k++) {
                            LOGGER.info("obj--------------= {}", k);
                            int index = (Integer) displayFormatIndex[k];
                            if (index == 0) {
                                column = ArrayUtils.removeElement(column, Constant.DF_LEVEL_NAME);
                                header = ArrayUtils.removeElement(header, Constant.LEVEL_NAME_HEADER);
                            } else {
                                column = ArrayUtils.removeElement(column, Constant.DF_LEVEL_NUMBER);
                                header = ArrayUtils.removeElement(header, Constant.LEVEL_NUMBER_HEADER);
                            }
                        }
                    }
                    
                    
                    // Added for isAccountGrowth checking
                    boolean isAg = false;
                    for (Object propertyId : excelTable.getContainerPropertyIds()) {
                        if (String.valueOf(propertyId).endsWith(ACNT_GRWOTH)) {
                            isAg = true;
                            excelTable.setConverter(propertyId, percentFormat);
                        }
                    }
                    
                    securityForListView(column, Arrays.copyOf(header, header.length, String[].class), excelTable);

                    excelTable.setRefresh(true);
                    String sheetName = "Year " + String.valueOf(projectionDTO.getHeaderMapForExcel().get(i).get(NumericConstants.TWO));
                    ForecastUI.setEXCELCLOSE(true);
                     Map<String, String> formatterMap = new HashMap<>();
                     formatterMap.put(CURRENCY_NO_DECIMAL, SALES);
                     formatterMap.put(UNIT_NO_DECIMAL, UNITS);
                     formatterMap.put("UNITTWODECIMAL", ACNT_GRWOTH);
                     formatterMap.put("UNIT_DECIMAL", "ProductGrowth");
                     
                     // Added PG_SUM and AG_SUM cols to fomatter map 
                     formatterMap.put("PRODUCT_GROWTH_SUM", "ProductGrowthSum");
                     formatterMap.put("ACCOUNT_GROWTH_SUM", "AccountGrowthSum");
                     formatterMap.put("CHILD_COUNT", "ChildCount");
                     
                    if (i == 0) {
                        exp = new SalesExcelNM(new ExtCustomTableHolder(excelTable), sheetName,
                                Constant.SALES_PROJECTION, SALES_PROJECTION_XLS, false, formatterMap, isAg);
                    } else {
                        if (exp != null) {
                            exp.setNextTableHolder(new ExtCustomTableHolder(excelTable), sheetName);
                        }
                    }
                    if (exp != null) {
                        boolean export = i == exportAt;
                        exp.exportMultipleTabs(export);
                    }
                    
                }
            } else {
                List<String> columnHeader = new ArrayList<>();
                List<Object> visibleColumns = new ArrayList<>();
                for (Object obj : excelHeader.getSingleColumns()) {
                    visibleColumns.add(obj);
                }
                for (String header : excelHeader.getSingleHeaders()) {
                    columnHeader.add(StringUtils.EMPTY + header);
                }
                  Object[] displayFormatIndex = CommonUtil.getDisplayFormatSelectedValues(displayFormatValues);
                    if (displayFormatIndex.length == 1) {
                        for (int k = 0; k < displayFormatIndex.length; k++) {
                            LOGGER.info("obj--------------===== {}" , k);
                            int index = (Integer) displayFormatIndex[k];
                            if (index == 0) {
                                visibleColumns.remove(Constant.DF_LEVEL_NAME);
                                columnHeader.remove(Constant.LEVEL_NAME_HEADER);
                            } else {
                                visibleColumns.remove(Constant.DF_LEVEL_NUMBER);
                                columnHeader.remove(Constant.LEVEL_NUMBER_HEADER);
                            }
                        }
                    }
                    
                 // Added for isAccountGrowth checking
                    boolean isAg = false;
                    for (Object propertyId : excelTable.getContainerPropertyIds()) {
                        if (String.valueOf(propertyId).endsWith(ACNT_GRWOTH)) {
                            isAg = true;
                            excelTable.setConverter(propertyId, percentFormat);
                        }
                    }
                    
                    Map<String, String> formatterMap = new HashMap<>();
                     formatterMap.put(CURRENCY_NO_DECIMAL, SALES);
                     formatterMap.put(UNIT_NO_DECIMAL, UNITS);
                     formatterMap.put("UNITTWODECIMAL", ACNT_GRWOTH);
                     formatterMap.put("UNIT_DECIMAL", "ProductGrowth");
                     
                     // Added PG_SUM and AG_SUM cols to fomatter map 
                     formatterMap.put("PRODUCT_GROWTH_SUM", "ProductGrowthSum");
                     formatterMap.put("ACCOUNT_GROWTH_SUM", "AccountGrowthSum");
                     formatterMap.put("CHILD_COUNT", "ChildCount");
                     
                securityForListView(visibleColumns.toArray(), Arrays.copyOf(columnHeader.toArray(), columnHeader.size(), String[].class), excelTable);
                exp = new SalesExcelNM(new ExtCustomTableHolder(excelTable), Constant.SALES_PROJECTION, Constant.SALES_PROJECTION, SALES_PROJECTION_XLS, false, formatterMap, isAg);
                exp.export();
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.info(e.getMessage(),e);
        }
        long endTime = System.currentTimeMillis();
        LOGGER.info("Excel Export time--------------------------------------------------------------{}",(endTime-startTime)/1000);
    }
    public static final String SALES_PROJECTION_XLS = "Sales_Projection.xls";

    @Override
    protected final void enableDisableFields() {
        return;
    }

    @Override
    protected void populateBtnLogic() {
        populateButtonLogic();
    }

    @Override
    protected void adjustmentLogic() {
        return;
    }

    @Override
    protected void calculateLogic() {
        try {
            setSalesValueChange(true);
            calculateButtonLogic();
        } catch (Exception ex) {
            LoggerFactory.getLogger(NMSalesProjection.class.getName()).error(StringUtils.EMPTY, ex);
        }
    }

    @Override
    protected void massUpdateLogic() {
        if ((Constant.LabelConstants.DISABLE.getConstant()).equals(massUpdate.getValue())) {
            fieldDdlb.setValue(Constant.SELECT_ONE);
            fieldDdlb.setEnabled(false);
            valueDdlb.setEnabled(false);
            valueTxt.setEnabled(false);
            startPeriod.setEnabled(false);
            endPeriod.setEnabled(false);
            populate.setEnabled(false);

        } else {
            fieldDdlb.setEnabled(true);
            fieldDdlb.setValue(Constant.SELECT_ONE);
            valueDdlb.setEnabled(true);
            valueTxt.setEnabled(true);
            startPeriod.setEnabled(true);
            endPeriod.setEnabled(true);
            populate.setEnabled(true);
        }
    }

    @Override
    protected void viewChangeOption() {
        return;
    }

    @Override
    protected void customDdlbChangeOption() {
        LOGGER.debug("customDdlbChangeOption ValueChangeEvent initiated ");
        customId = CommonLogic.customDdlbOptionChange(viewDdlb, editBtn, level);
        LOGGER.debug(" customId= {} ", customId);
        projectionDTO.setCustomId(customId);
        if (customId != 0) {
            session.setCustomId(customId);
            Utility.loadCustomHierarchyList(session);
        }
        LOGGER.debug(" currentHierarchy= {} ", currentHierarchy.size());
        generateLogic();
        editBtn.setEnabled(false);
        LOGGER.debug("customDdlbChangeOption ValueChangeEvent ends ");
    }

    @Override
    protected void expandButtonLogic() {
        try {
            if (StringUtils.isNotBlank(String.valueOf(level.getValue())) || !Constant.NULL.equals(String.valueOf(level.getValue()))) {
                projectionDTO.setExpandCollapseFlag(BooleanConstant.getTrueFlag());
                expandCollapseLevelOption(true, level.getValue());
                projectionDTO.setExpandCollapseFlag(BooleanConstant.getFalseFlag());
            } else {
                projectionDTO.setExpandCollapseFlag(BooleanConstant.getFalseFlag());
                AbstractNotificationUtils.getErrorNotification("No Level Selected", "Please select a Level from the drop down.");
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    protected void collapseButtonLogic() {
        nmSalesProjectionTableLogic.setRefresh(false);
        expandCollapseLevelOption(false, level.getValue());
        nmSalesProjectionTableLogic.setRefresh(true);
    }

    @Override
    protected void fieldDdlbLogic() {
        if (Constant.ACCOUNT_GROWTH.equals(fieldDdlb.getValue()) || Constant.PRODUCT_GROWTH.equals(fieldDdlb.getValue()) || Constant.SALES_SMALL.equals(fieldDdlb.getValue()) || Constant.UNIT_VOLUME.equals(fieldDdlb.getValue()) || Constant.SELECT_ONE.equals(fieldDdlb.getValue()) || Constant.NULL.equals(String.valueOf(fieldDdlb.getValue()))) {
            startPeriod.setVisible(true);
            endPeriod.setVisible(true);
            lblStart.setVisible(true);
            lblEnd.setVisible(true);
            valueTxt.setVisible(true);
            valueTxt.setValue(StringUtils.EMPTY);
            startPeriod.setValue(Constant.SELECT_ONE);
            endPeriod.setValue(Constant.SELECT_ONE);
            valueTxt.setVisible(true);
            valueDdlb.setVisible(false);
            valueDdlb.select(Constant.SELECT_ONE);
        } else if (Constant.GROUPFCAPS.equals(fieldDdlb.getValue())) {
            valueDdlb.setVisible(true);
            valueTxt.setVisible(false);
            valueTxt.setValue(StringUtils.EMPTY);
            startPeriod.setVisible(false);
            endPeriod.setVisible(false);
            lblStart.setVisible(false);
            lblEnd.setVisible(false);
        }

    }

    @Override
    protected void resetBtnLogic() {
        if (nmFrequencyDdlb.getValue().equals(MONTHLY.getConstant())
                || (nmFrequencyDdlb.getValue().equals(QUARTERLY.getConstant()))
                || (nmFrequencyDdlb.getValue().equals(ANNUAL.getConstant()))
                || (nmFrequencyDdlb.getValue().equals(SEMI_ANNUAL.getConstant()))) {
            nmFrequencyDdlb.setValue(QUARTERLY.getConstant());
            historyDdlb.setValue(NumericConstants.FOUR);
        }
        actualsProjections.select(Constant.ACTUALS);
        proPeriodOrd.select(Constant.ASCENDING);
        variables.removeAllItems();
        variables.addItem(Constant.SALES_SMALL);
        variables.addItem(Constant.UNITS_SMALL);
        variables.addItem(Constant.PRODUCT_GROWTH);
        variables.addItem(Constant.ACCOUNT_GROWTH);
        variables.select(Constant.SALES_SMALL);
        unitOfMeasureDdlb.select("EACH");
        conversionFactorDdlb.select(Constant.CONVERSION_FACTOR_DEFALUT_VALUE);
        loadDisplayFormatDdlb();
        if (ACTION_EDIT.getConstant().equalsIgnoreCase(session.getAction()) || ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction())) {
            super.setProjectionSelection(true);
            loadSalesInclusion();
        } else {
            resetForAdd();
        }
    }

    public void resetForAdd() {
        CommonLogic.unCheckMultiSelect(productFilterValues);
        CommonLogic.unCheckMultiSelect(customerFilterValues);
        productlevelDdlb.select(Constant.SELECT_ONE);
        customerlevelDdlb.select(Constant.SELECT_ONE);
        projectionDTO.setProductLevelFilter(Collections.emptyList());
        projectionDTO.setCustomerLevelFilter(Collections.emptyList());
        loadSalesInclusion();
    }

    protected void channelsViewChange() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void generateBtnLogic(Button.ClickEvent event) {
        try {
            
            projectionDTO.setCustomerLevelFilter(generateCustomerToBeLoaded);
            projectionDTO.setProductLevelFilter(generateProductToBeLoaded);
             loadAllFilters();
            if (checkSelection()) {
                LOGGER.debug("generate button click listener starts ");
                tableLayout.removeAllComponents();
                nmSalesProjectionTableLogic = new NMSalesProjectionTableLogic();
                resultsTable = new FreezePagedTreeTable(nmSalesProjectionTableLogic);
                super.initializeResultTable();
                configureResultTable();
                addResultTable();
                CommonLogic.procedureCompletionCheck(session, "sales", String.valueOf(view.getValue()));
                generateLogic();
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.debug("generate button click listener ends ");
    }

    private void customerFilterGenerate(boolean customerFlag, boolean productFlag) {
        boolean customerFilterFlag = customerFlag;
        boolean productFilterFlag = productFlag;
        if ((!generateProductToBeLoaded.isEmpty() || !generateCustomerToBeLoaded.isEmpty()) || !customerFilterFlag || !productFilterFlag) {
            LOGGER.info("generateBtn :Inside Filter Option");
            session.setFunctionMode("F");
            CommonLogic.procedureCompletionCheck(session, "sales", String.valueOf(view.getValue()));
            CommonLogic.updateFlagStatusToRForAllViewsDiscount(session, Constant.SALES1);
            dataLogic.nmSalesViewsPopulationProcedureWithoutTruncation(session);
            CommonUtil.getInstance().waitForSeconds();
            customerFilterFlag = true;
            productFilterFlag = true;
        }
        LOGGER.debug("customerFlag{} , productFlag{} ", customerFilterFlag, productFilterFlag);
    }

    private void commercialGenerate(boolean customerFlag, boolean productFlag) {
        if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projectionDTO.getScreenName())) {
            if (!projectionDTO.getFrequency().equals(nmFrequencyDdlb.getValue())) {
                session.setFunctionMode(session.getAction().equalsIgnoreCase(Constant.ADD_FULL_SMALL) ? "G" : "E");
                session.setDsFrequency(String.valueOf(nmFrequencyDdlb.getValue()));
                CommonLogic.updateFlagStatusToRForAllViewsDiscount(session, Constant.SALES1);
                dataLogic.nmSalesViewsPopulationProcedure(session);
                CommonUtil.getInstance().waitForSeconds();
            }
            uomValueChangeGenerate();
            customerFilterGenerate(customerFlag, productFlag);
            projectionDTO.setGroup(StringUtils.EMPTY);

        }
    }
    public void checkSpFrequency(){
        boolean spFlag = true;
        if(spFlag && (!session.getDsFrequency().equals(nmFrequencyDdlb.getValue()))){            
            spFlag =false;
            AbstractNotificationUtils.getInfoNotification("Info", "Changes have been made to the display selection. Please generate to view the changes in the results");
            projectionDTO.setFrequency(String.valueOf(nmFrequencyDdlb.getValue()));
        
        }
    }

    private void uomValueChangeGenerate() {
        if (uomValueChange) {
            session.setUomCode(unitOfMeasureDdlb.getValue() == null ? "EACH" : String.valueOf(unitOfMeasureDdlb.getValue()));
            dataLogic.nmSalesViewsPopulationProcedureUOM(session);
            uomValueChange = false;
        }
    }

    /**
     * Configures the result table.
     */
    private void configureResultTable() {
        configureProjectionDTO();
        nmSalesProjectionTableLogic.setTreeNodeMultiClick(false);
        nmSalesProjectionTableLogic.setPageLength(NumericConstants.TWENTY);
        nmSalesProjectionTableLogic.sinkItemPerPageWithPageLength(false);
        List<Integer> pagelength = CommonLogic.getPageNumber();
        nmSalesProjectionTableLogic.getControlConfig().setPageLengthsAndCaptions(pagelength);
        excelHeader = new CustomTableHeaderDTO();
        leftHeader = HeaderUtils.getSalesLeftTableColumns(projectionDTO);

      
        if (CommonUtil.isValueEligibleForLoading()) {
            excelHeader.addSingleColumn("dfLevelNumber", "Level Number", String.class);
            excelHeader.addSingleColumn("dfLevelName", Constant.LEVEL_NAME_HEADER, String.class);
        } else{
            excelHeader.addSingleColumn(Constant.LEVELNAME, Constant.LEVEL_NAME_HEADER, String.class);
        }

        if (projectionDTO.getScreenName().equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
            excelHeader.addSingleColumn(Constant.GROUP, "Group", String.class);
        }
        excelHeader.addSingleColumn(Constant.BASELINE, "Base Line", String.class);
        excelHeader.addSingleColumn(Constant.METHODOLOGY, "Methodology", String.class);
        rightHeader = HeaderUtils.getSalesProjectionRightTableColumns(projectionDTO, fullHeader, excelHeader);
        resultsTable.getLeftFreezeAsTable().setFilterBarVisible(true);
        setCustomContainer(new ExtTreeContainer<>(SalesRowDto.class, ExtContainer.DataStructureMode.MAP));
        getCustomContainer().setColumnProperties(leftHeader.getProperties());
        getCustomContainer().setColumnProperties(rightHeader.getProperties());
        nmSalesProjectionTableLogic.setContainerDataSource(getCustomContainer());
        leftTable = resultsTable.getLeftFreezeAsTable();
        rightTable = resultsTable.getRightFreezeAsTable();
        leftTable.setEditable(true);
        rightTable.setEditable(true);

        String[] columnLeftHeader = new String[leftHeader.getSingleHeaders().size()];
        securityForListView(leftHeader.getSingleColumns().toArray(), leftHeader.getSingleHeaders().toArray(columnLeftHeader), leftTable);
        leftTable.setDoubleHeaderVisible(true);
        leftTable.setDoubleHeaderVisibleColumns(leftHeader.getDoubleColumns().toArray());
        leftTable.setDoubleHeaderColumnHeaders(leftHeader.getDoubleHeaders().toArray(new String[leftHeader.getDoubleHeaders().size()]));

        rightTable.setVisibleColumns(rightHeader.getSingleColumns().toArray());
        rightTable.setColumnHeaders(rightHeader.getSingleHeaders().toArray(new String[rightHeader.getSingleHeaders().size()]));

        for (Object obj : leftHeader.getSingleColumns()) {
            if (String.valueOf(obj).contains(Constant.GROUP)) {
                resultsTable.getLeftFreezeAsTable().setColumnWidth(obj, NumericConstants.ONE_THREE_FIVE);
            } else if (String.valueOf(obj).contains(Constant.LEVEL_NAME)) {
                resultsTable.getLeftFreezeAsTable().setColumnWidth(obj, NumericConstants.ONE_THREE_ZERO);
            }
        }
        rightTable.setDoubleHeaderVisible(true);
        rightTable.setDoubleHeaderVisibleColumns(rightHeader.getDoubleColumns().toArray());
        rightTable.setDoubleHeaderColumnHeaders(rightHeader.getDoubleHeaders().toArray(new String[rightHeader.getDoubleHeaders().size()]));
        resultsTable.setHeight(Constant.SIX_FIFTY_PX);
        leftTable.setHeight(Constant.SIX_FIFTY_PX);
        rightTable.setHeight(Constant.SIX_FIFTY_PX);
        rightTable.setDoubleHeaderMap(rightHeader.getDoubleHeaderMaps());
        leftTable.setDoubleHeaderMap(leftHeader.getDoubleHeaderMaps());
        loadPeriods();
        leftTable.setSortEnabled(false);
        rightTable.setSortEnabled(false);
        configureTableFieldFactory();
        levelFilter.removeValueChangeListener(levelFilterChangeOption);
        levelFilter.addValueChangeListener(levelFilterChangeOption);
        getHeaderList();
    }

    public void getHeaderList() {
        List<String> headerList = new ArrayList<>();
        for (Object header : rightHeader.getSingleColumns()) {
            headerList.add(String.valueOf(header));
        }
        projectionDTO.setHeaderMapColumn(headerList);
    }

    /**
     * Adds the layout and pagination control to the Layout.
     */
    private void addResultTable() {
        tableLayout.addComponent(resultsTable);
        HorizontalLayout controls = nmSalesProjectionTableLogic.createControls();
        HorizontalLayout controlLayout = CommonLogic.getResponsiveControls(controls);
        tableLayout.addComponent(controlLayout);
    }

   
    protected List getCheckedSalesInclusionValues() {
        List<String> results = new ArrayList<>();
        if (salesInclusionValues != null && salesInclusionValues.getSize() > 0) {
            List<CustomMenuBar.CustomMenuItem> items = salesInclusionValues.getChildren();
            for (Iterator<CustomMenuBar.CustomMenuItem> it = items.iterator(); it.hasNext();) {
                CustomMenuBar.CustomMenuItem customMenuItem1 = it.next();
                if (customMenuItem1.isChecked()) {
                    results.add(customMenuItem1.getMenuItem().getCaption());
                }
            }
        }
        return results;
    }

    /**
     * Contains the generate logic.
     */
    private void generateLogic() {
        projectionDTO.setHierarchyIndicator(Constant.CUSTOMER_SMALL.equals(view.getValue()) ? Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY : Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
        if ((PRODUCT.getConstant()).equals(view.getValue())) {
            leftTable.setColumnCollapsingAllowed(true);
            leftTable.setColumnCollapsed(Constant.GROUP, true);
            Utility.loadLevelValue(level, levelFilter, null, session.getProductHierarchyList(), view.getValue().toString());
        } else if ((Constant.CUSTOM_LABEL).equals(view.getValue())) {
            leftTable.setColumnCollapsingAllowed(true);
            leftTable.setColumnCollapsed(Constant.GROUP, false);
        } else if ((Constant.CUSTOMER_SMALL).equals(view.getValue())) {
            leftTable.setColumnCollapsingAllowed(true);
            leftTable.setColumnCollapsed(Constant.GROUP, false);
            Utility.loadLevelValue(level, levelFilter, null, session.getCustomerHierarchyList(), view.getValue().toString());
        }
        CommonUtil.getInstance().waitsForOtherThreadsToComplete(session.getFutureValue(Constant.FILE_INSERT, 0));

        nmSalesProjectionTableLogic.setProjectionResultsData(projectionDTO);
        session.setDsFrequency(String.valueOf(nmFrequencyDdlb.getValue()));
    }

    public void loadAllFilters() {
        List<String> checkedValues = getCheckedSalesInclusionValues();
        projectionDTO.getSessionDTO().setSalesInclusion(allUpperCase);
        if (checkedValues.size() == 1) {
            projectionDTO.getSessionDTO().setSalesInclusion(checkedValues.get(0).equalsIgnoreCase("Yes") ? "1" : "0");
        }
        projectionDTO.setCustomerLevelFilter(getCustomerFilterValues());
        projectionDTO.setProductLevelFilter(getProductFilterValues());
        projectionDTO.setUomCode(unitOfMeasureDdlb.getValue() == null ? "EACH" : String.valueOf(unitOfMeasureDdlb.getValue()));
        projectionDTO.setDisplayFormat(CommonUtil.getDisplayFormatSelectedValues(displayFormatValues));
        projectionDTO.setConversionFactor(conversionFactorDdlb.getValue());
        CommonLogic.updateForFilter(projectionDTO, "SALES", false);
        CommonLogic.updateCustomerProductCustomTables(session);
    }

    /**
     * Configures the Projection Selection DTO on entering Screen. Sets the
     * values in the Session DTO to the ProjectionSelectionDTO.
     */
    private void configureProjectionDTO() {
        boolean toHist = false;
        int historyNum = 0;
        projectionDTO.setScreenName(screenName);
        projectionDTO.setCustRelationshipBuilderSid(projectionDTO.getSessionDTO().getCustRelationshipBuilderSid());
        projectionDTO.setProdRelationshipBuilderSid(projectionDTO.getSessionDTO().getProdRelationshipBuilderSid());
        projectionDTO.setCustomerLevelNo(StringUtils.isBlank(projectionDTO.getSessionDTO().getCustomerLevelNumber()) || Constant.NULL.equals(projectionDTO.getSessionDTO().getCustomerLevelNumber())
                ? 1 : Integer.parseInt(projectionDTO.getSessionDTO().getCustomerLevelNumber()));
        projectionDTO.setProductLevelNo(StringUtils.isBlank(projectionDTO.getSessionDTO().getProductLevelNumber()) || Constant.NULL.equals(projectionDTO.getSessionDTO().getProductLevelNumber())
                ? 1 : Integer.parseInt(projectionDTO.getSessionDTO().getProductLevelNumber()));
        projectionDTO.setProjectionId(projectionDTO.getSessionDTO().getProjectionId());
        projectionDTO.setUserId(Integer.parseInt(projectionDTO.getSessionDTO().getUserId()));
        projectionDTO.setSessionId(Integer.parseInt(projectionDTO.getSessionDTO().getSessionId()));
        checkFrequencyChange();
        projectionDTO.setFrequency(String.valueOf(nmFrequencyDdlb.getValue()));
        projectionDTO.setProjectionOrder(String.valueOf(proPeriodOrd.getValue()));
        projectionDTO.setActualsOrProjections(String.valueOf(actualsProjections.getValue()));
        String history = String.valueOf(historyDdlb.getValue());
        history = history.trim();
        if (history != null && !StringUtils.isBlank(history) && !Constant.NULL.equals(history) && !SELECT_ONE.getConstant().equals(history)) {
            toHist = true;
            projectionDTO.setHistory(history);
            historyNum = Integer.parseInt(projectionDTO.getHistory());
        }
        if (toHist) {
            projectionDTO.setForecastDTO(session.getForecastDTO());
            projectionDTO.setHistoryNum(historyNum);
            projectionDTO.setProjectionNum(CommonUtils.getProjectionNumber(projectionDTO.getFrequency(), session));
        }

        if (variables.getValue() != null) {
            String tempVariables = variables.getValue().toString();
            tempVariables = tempVariables.substring(1, tempVariables.length() - 1).trim();
            final String[] col = tempVariables.split(",");
            List<String> colArray = new ArrayList<>();
            for (String string : col) {
                colArray.add(string.trim());
            }
            Collection<?> temp = variables.getItemIds();
            List<String> itemId = (List<String>) temp;
            List<String> sortedArray = new ArrayList<>();
            for (String string : itemId) {
                if (colArray.contains(string)) {
                    sortedArray.add(string);
                }
            }
            projectionDTO.setVariableList(sortedArray);
        }

    }

    private void configureGroupDDLB() {
        fieldDdlb.addItem(Constant.GROUPFCAPS);
        fieldDdlb.removeItem(Constant.GROUPFCAPS);
        groupBean.removeAllItems();
        groupBean.addBean(Constant.SHOW_ALL_GROUPS);
        GroupFilter.initSalesMap(session);
        groupBean.addAll(session.getSalesgroupSet());
        massGroupBean.removeAllItems();
        massGroupBean.addBean(Constant.SELECT_ONE);
        massGroupBean.addAll(session.getSalesgroupSet());
        valueDdlb.setNewItemsAllowed(true);
        valueDdlb.setContainerDataSource(massGroupBean);
        valueDdlb.setNullSelectionAllowed(true);
        valueDdlb.setNullSelectionItemId(Constant.SELECT_ONE);
        valueDdlb.select(Constant.SELECT_ONE);
        valueDdlb.setTextInputAllowed(true);
        boolean isEnabled = Utility.customEnableForRelationFromDS(session.getCustomRelationShipSid());
        view.setItemEnabled(Constant.CUSTOM_LABEL, isEnabled);
        if(session.getCustomRelationShipSid()==0){
           viewDdlb.setValue(null);
           newBtn.setEnabled(false); 
        }
        else{
        view.setItemEnabled(Constant.CUSTOM_LABEL, true);
        }
        if (CommonUtil.isValueEligibleForLoading()) {
            salesProjectionSelection.setVisible(false);
            tabsheet1.addTab(salesProjectionSelectionLayout, "Display Selection");
            tabsheet1.addTab(salesProjectionfilterLayout, "Filter Options");
            tabsheet1.addStyleName(ValoTheme.TABSHEET_FRAMED);
            tabsheet1.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
            loadProductLevel();
            loadCustomerLevel();
            loadCustomerLevelFilter(StringUtils.EMPTY);
            loadProductLevelFilter(StringUtils.EMPTY);
            commonLogic.loadUnitOfMeasureDdlb(unitOfMeasureDdlb, session);
        } else {
            unitOfMeasureDdlb.setVisible(false);
            unitOfMeasure.setVisible(false);
            tabsheet1.setVisible(false);
            salesProjectionfilterLayout.setVisible(false);
        }
    }

    public void securityForButton() {
        try {
            final Map<String, AppPermission> functionPsHM = stplSecurity.getBusinessFunctionPermissionForNm(String.valueOf(VaadinSession.getCurrent().getAttribute("businessRoleIds")), GlobalConstants.getCommercialConstant() + "," + UISecurityUtil.SALES_PROJECTION);
            if (!(functionPsHM.get(CommonUtils.GENERATE_BUTTON) != null && ((AppPermission) functionPsHM.get(CommonUtils.GENERATE_BUTTON)).isFunctionFlag())) {
                generate.setVisible(BooleanConstant.getFalseFlag());
                expand.setVisible(BooleanConstant.getFalseFlag());
                collapse.setVisible(BooleanConstant.getFalseFlag());
                newBtn.setVisible(BooleanConstant.getFalseFlag());
                editBtn.setVisible(BooleanConstant.getFalseFlag());
            }
            if (functionPsHM.get(CommonUtils.ALT_HISTORY_BTN) != null && ((AppPermission) functionPsHM.get(CommonUtils.ALT_HISTORY_BTN)).isFunctionFlag()) {
                altHistoryBtn.setVisible(true);
            } else {
                altHistoryBtn.setVisible(false);
            }
            if (!(functionPsHM.get(CommonUtils.CALCULATE) != null && ((AppPermission) functionPsHM.get(CommonUtils.CALCULATE)).isFunctionFlag())) {
                calculate.setVisible(BooleanConstant.getFalseFlag());
                populate.setVisible(BooleanConstant.getFalseFlag());
                adjust.setVisible(BooleanConstant.getFalseFlag());
            }

            if ((functionPsHM.get(CommonUtils.TOTAL_LIVES_LAYOUT) != null && ((AppPermission) functionPsHM.get(CommonUtils.TOTAL_LIVES_LAYOUT)).isFunctionFlag())) {
                totalLivesLayout.setVisible(true);
            } else {
                totalLivesLayout.setVisible(false);
            }

        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    public void saveSPSave() {
        LOGGER.debug("saveSPResults method starts");
        try {
            Map map = new HashMap();
            map.put(Constant.FREQUENCY_SMALL, nmFrequencyDdlb.getValue().toString());
            map.put(Constant.HISTORY_CAPS, historyDdlb.getValue().toString());
            map.put("Actuals/Projections", actualsProjections.getValue().toString());
            map.put(Constant.PERIOD_ORDER, proPeriodOrd.getValue().toString());
            map.put(Constant.VARIABLES, variables.getValue().toString());
            map.put(Constant.DISPLAY_FORMAT_SAVE, StringUtils.join(CommonUtil.getDisplayFormatSelectedValues(displayFormatValues), CommonUtil.COMMA));
            map.put(Constant.CUSTOMER_LEVEL_DDLB, customerlevelDdlb.getValue());
            map.put(Constant.CUSTOMER_LEVEL_VALUE, StringUtils.join(getCustomerFilterValues(), CommonUtil.COMMA));
            map.put(Constant.PRODUCT_LEVEL_DDLB, productlevelDdlb.getValue());
            map.put(Constant.PRODUCT_LEVEL_VALUE, StringUtils.join(getProductFilterValues(), CommonUtil.COMMA));
            map.put(Constant.SALES_INCLUSION_DDLB, StringUtils.join(CommonUtil.getDisplayFormatSelectedValues(salesInclusionValues), CommonUtil.COMMA));
            map.put(Constant.UNIT_OF_MEASURE, String.valueOf(unitOfMeasureDdlb.getValue()));
            map.put(Constant.CONVERSION_FACTOR_DDLB, String.valueOf(conversionFactorDdlb.getValue()));
            sprCommonLogic.saveNMSRPSelection(map, session.getProjectionId(), Constant.SALES_PROJECTION);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        LOGGER.debug("saveSPResults method ends");
    }

    public boolean getSubmitFlag() {
        return !getCustomContainer().isEmpty();
    }

    @Override
    public PageTreeTableLogic getTableLogic() {
        return nmSalesProjectionTableLogic;
    }

    @Override
    protected void variableChangeLogic() {
        if ((Constant.ACTUAL).equals(variable.getValue())) {
            allocMethodology.setEnabled(false);
            allocMethodology.setValue(Constant.SELECT_ONE);
            adjustment.setEnabled(false);
            adjustment.setValue("0");
        } else {
            allocMethodology.setEnabled(true);
            adjustment.setEnabled(true);
            adjustment.setValue(StringUtils.EMPTY);
        }
    }

    private void loadProductLevel() {

        int hierarchyLevelNo = isInteger(session.getProductLevelNumber()) ? Integer.parseInt(session.getProductLevelNumber()) : 0;
        currentHierarchy = CommonLogic.getProductHierarchy(session.getProjectionId(), hierarchyLevelNo, session.getProdRelationshipBuilderSid(), session.getProductRelationVersion());
        Utility.loadDdlbForLevelFilterOption(productlevelDdlb, currentHierarchy, NAME);
        productlevelDdlb.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                generateProductToBeLoaded = Collections.emptyList();
                if (event.getProperty().getValue() != null) {
                    String productlevelDdlbValue = productlevelDdlb.getItemCaption(String.valueOf(event.getProperty().getValue()));
                    loadProductLevelFilter(productlevelDdlbValue);
                } else {
                    loadProductLevelFilter(StringUtils.EMPTY);
                }
            }
        });
    }

    private void loadProductLevelFilter(String levelNo) {
        List<Object[]> productLevelFilter = new ArrayList<>();

        productFilterDdlb.removeSubMenuCloseListener(productListener);
        productFilterDdlb.removeItems();
        productFilterValues = productFilterDdlb.addItem(SELECT_LEVEL_LABEL, null);
        if (!levelNo.isEmpty()) {
            productLevelFilter.add(0, new Object[]{0, SELECT_ALL});
            productLevelFilter.addAll(commonLogic.getProductLevelValues(session.getProjectionId(), levelNo, projectionDTO, (List) generateCustomerToBeLoaded, Collections.emptyList(), String.valueOf(session.getProductRelationVersion())));
            CommonLogic.loadCustomMenuBar(productLevelFilter, productFilterValues);
        }
        productFilterDdlb.setScrollable(true);
        productFilterDdlb.setPageLength(NumericConstants.TEN);
        CommonLogic.loadMenuBar(generateProductToBeLoaded, productFilterValues);
        String productMenuItemValue = ChangeCustomMenuBarValueUtil.getMenuItemToDisplay(productFilterValues);
        ChangeCustomMenuBarValueUtil.setMenuItemToDisplay(productFilterDdlb, productMenuItemValue);
        productFilterDdlb.addSubMenuCloseListener(productListener);
    }
    public static final String SELECT_ALL = "Select All";

    private void loadCustomerLevel() {
        int hierarchyNo = isInteger(session.getCustomerLevelNumber()) ? Integer.parseInt(session.getCustomerLevelNumber()) : 0;
        currentHierarchy = CommonLogic.getCustomerHierarchy(session.getProjectionId(), hierarchyNo, session.getCustRelationshipBuilderSid(), session.getCustomerRelationVersion());
        Utility.loadDdlbForLevelFilterOption(customerlevelDdlb, currentHierarchy, NAME);

        customerlevelDdlb.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                generateCustomerToBeLoaded = Collections.emptyList();
                if (event.getProperty().getValue() != null) {
                    String customerlevelDdlbValue = String.valueOf(customerlevelDdlb.getValue());
                    loadCustomerLevelFilter(customerlevelDdlbValue);
                } else {
                    loadCustomerLevelFilter(StringUtils.EMPTY);
                }
            }
        });
    }

    private void loadCustomerLevelFilter(String levelNo) {
        List<Object[]> customerLevelFilter = new ArrayList<>();
        customerFilterDdlb.removeSubMenuCloseListener(cutomerListener);
        customerFilterDdlb.removeItems();
        customerFilterValues = customerFilterDdlb.addItem(SELECT_LEVEL_LABEL, null);
        if (!levelNo.isEmpty()) {
            customerLevelFilter.add(0, new Object[]{0, SELECT_ALL});
            customerLevelFilter.addAll(commonLogic.getCustomerLevelValues(session.getProjectionId(), levelNo, projectionDTO, (List) generateProductToBeLoaded, new ArrayList<>(), String.valueOf(session.getCustomerRelationVersion())));
            CommonLogic.loadCustomMenuBar(customerLevelFilter, customerFilterValues);
        }
        customerFilterDdlb.setScrollable(true);
        customerFilterDdlb.setPageLength(NumericConstants.TEN);
        CommonLogic.loadMenuBar(generateCustomerToBeLoaded, customerFilterValues);
        String customerMenuItemValue = ChangeCustomMenuBarValueUtil.getMenuItemToDisplay(customerFilterValues);
        ChangeCustomMenuBarValueUtil.setMenuItemToDisplay(customerFilterDdlb, customerMenuItemValue);
        customerFilterDdlb.addSubMenuCloseListener(cutomerListener);
    }

    protected List getCustomerFilterValues() {
        List<Object> customerList = new ArrayList<>();
        if (customerFilterValues != null && customerFilterValues.getSize() > 0) {
            List<CustomMenuBar.CustomMenuItem> items = customerFilterValues.getChildren();
            for (Iterator<CustomMenuBar.CustomMenuItem> it = items.iterator(); it.hasNext();) {
                CustomMenuBar.CustomMenuItem customMenuItem1 = it.next();
                if (customMenuItem1.isChecked() && !String.valueOf(customMenuItem1.getMenuItem().getWindow()).equals("0")) {
                    customerList.add(customMenuItem1.getMenuItem().getWindow());
                }
            }
        }
        return customerList;
    }

    protected List getProductFilterValues() {
        List<Object> productList = new ArrayList<>();
        if (productFilterValues != null && productFilterValues.getSize() > 0) {
            List<CustomMenuBar.CustomMenuItem> items = productFilterValues.getChildren();
            for (Iterator<CustomMenuBar.CustomMenuItem> it = items.iterator(); it.hasNext();) {
                CustomMenuBar.CustomMenuItem customMenuItem1 = it.next();
                if (customMenuItem1.isChecked() && !String.valueOf(customMenuItem1.getMenuItem().getWindow()).equals("0")) {
                    productList.add(customMenuItem1.getMenuItem().getWindow());
                }
            }
        }
        return productList;
    }

    private void loadSalesInclusion() {
        String[] variablesalesInclusion = {"Yes", "No"};
        salesInclusionDdlb.removeSubMenuCloseListener(salesInclusionListener);
        salesInclusionDdlb.removeItems();
        salesInclusionValues = salesInclusionDdlb.addItem("-Select Values-", null);
        CustomMenuBar.CustomMenuItem[] salesInclusionCustomItem = new CustomMenuBar.CustomMenuItem[variablesalesInclusion.length];
        for (int i = 0; i < variablesalesInclusion.length; i++) {
            MenuItemDTO dto = new MenuItemDTO(i, variablesalesInclusion[i].trim());
            salesInclusionCustomItem[i] = salesInclusionValues.addItem(dto, null);
            salesInclusionCustomItem[i].setCheckable(true);
            salesInclusionCustomItem[i].setItemClickable(true);
            salesInclusionCustomItem[i].setItemClickNotClosable(true);

        }
         salesInclusionDdlb.addSubMenuCloseListener(salesInclusionListener);
        if (!ACTION_EDIT.getConstant().equalsIgnoreCase(session.getAction()) || (!ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction()) )) {
             salesInclusionDdlb.addSubMenuCloseListener(salesInclusionListener);
        salesInclusionValues.getChildren().get(0).setChecked(true);
         ChangeCustomMenuBarValueUtil.setMenuItemToDisplay(salesInclusionDdlb, "Yes");
        }
        
    }

    private void loadDisplayFormatDdlb()  {
        List<Object[]> displayFormatFilter = new ArrayList<>();
        displayFormatFilter.addAll(commonLogic.displayFormatValues());
        displayFormatDdlb.removeSubMenuCloseListener(displayFormatListener);
        displayFormatDdlb.removeItems();
        displayFormatValues = displayFormatDdlb.addItem("-Select Values-", null);
        commonLogic.loadDisplayFormat(displayFormatFilter, displayFormatValues);
        displayFormatDdlb.setScrollable(true);
        String displayFormatMenuItemValue = ChangeCustomMenuBarValueUtil.getInclusionMenuItemToDisplay(displayFormatValues);
        ChangeCustomMenuBarValueUtil.setMenuItemToDisplay(displayFormatDdlb, displayFormatMenuItemValue);
        displayFormatDdlb.addSubMenuCloseListener(displayFormatListener);
    }

    private void securityForListView(Object[] visibleColumnArray, String[] columnHeaderArray, ExtCustomTreeTable table) {
        try {
            final String userId = String.valueOf(sessionDTO.getUserId());
            final Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "Forecasting", "Commercial", "Sales Projection", sessionDTO.getAction());
            List<List> headeInformationsList = CommonLogic.isPropertyVisibleAccess(visibleColumnArray, columnHeaderArray, functionHM);
            List<String> headerArray = headeInformationsList.get(1);
            table.setVisibleColumns(headeInformationsList.get(0).toArray());
            table.setColumnHeaders(headerArray.toArray(new String[headerArray.size()]));
        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    private void getExcelSalesCommercial() {
        try {
            String parentKey;
            String tempKey;
            List<Object[]> salesExcelList = getSalesExcelResults(projectionDTO);
            NMSalesExcelLogic nmSalesExcelLogic = new NMSalesExcelLogic();
            List historyColumn = salesLogic.getHistoryColumn(salesLogic.getHeader(projectionDTO));
            nmSalesExcelLogic.getCustomizedExcelData(salesExcelList, projectionDTO, historyColumn);
            SalesRowDto itemId = new SalesRowDto();
            for (Iterator<String> it = nmSalesExcelLogic.getHierarchyKeys().listIterator(); it.hasNext();) {
                String key = it.next();
                it.remove();
                if (nmSalesExcelLogic.getResultMap().containsKey(key)) {
                    itemId = nmSalesExcelLogic.getResultMap().get(key);
                    nmSalesExcelLogic.getResultMap().remove(key);
                }
                excelContainer.addBean(itemId);
                Object parentItemId;
                key = key.contains("$") ? key.substring(0, key.indexOf('$')) : key;
                tempKey = key.trim();
                    parentKey = CommonUtil.getParentItemId(key);
                parentItemId = excelParentRecords.get(parentKey);
                if (parentItemId != null) {
                    excelContainer.setParent(itemId, parentItemId);
                }
                excelParentRecords.put(tempKey, itemId);
                excelContainer.setChildrenAllowed(itemId, true);
            }
            excelContainer.sort(new Object[]{"levelName"}, new boolean[]{true});
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    private List<Object[]> getSalesExcelResults(ProjectionSelectionDTO projectionSelectionDTO) {
        if (!projectionSelectionDTO.isIsCustomHierarchy()) {
            return salesLogic.getSalesExcelResults(projectionDTO);
        } else {
            return salesLogic.getSalesResultsExcelCustom(projectionSelectionDTO);
        }
    }

    @Override
    protected void excelExportBtnClickLogic() {
        try {
            configureExcelResultTable();
            levelFilterDdlbChangeOption(true);
            excelTable.setRefresh(BooleanConstant.getTrueFlag());
            excelTable.setDoubleHeaderVisible(false);
            ForecastUI.setEXCELCLOSE(true);
            ExcelExport exp = null;
            int exportAt = projectionDTO.getHeaderMapForExcel().size() - 1;
            if ((QUARTERLY.getConstant().equals(String.valueOf(nmFrequencyDdlb.getValue())) || MONTHLY.getConstant().equals(String.valueOf(nmFrequencyDdlb.getValue())))) {
                for (int i = 0; i < projectionDTO.getHeaderMapForExcel().size(); i++) {

                    excelTable.setVisibleColumns(((List<Object>) projectionDTO.getHeaderMapForExcel().get(i).get(0)).toArray());
                    Object[] header = ((List<Object>) projectionDTO.getHeaderMapForExcel().get(i).get(1)).toArray();
                    excelTable.setColumnHeaders(Arrays.copyOf(header, header.length, String[].class));
                    excelTable.setRefresh(true);
                    String sheetName = "Year " + String.valueOf(projectionDTO.getHeaderMapForExcel().get(i).get(NumericConstants.TWO));
                    
                    // Added for isAccountGrowth checking
                    boolean isAg = false;
                    for (Object propertyId : excelTable.getContainerPropertyIds()) {
                        if (String.valueOf(propertyId).endsWith(ACNT_GRWOTH)) {
                            isAg = true;
                            excelTable.setConverter(propertyId, percentFormat);
                        }
                    }
                    
                    
                    ForecastUI.setEXCELCLOSE(true);
                    Map<String, String> formatterMap = new HashMap<>();
                    formatterMap.put(CURRENCY_NO_DECIMAL, SALES);
                    formatterMap.put(UNIT_NO_DECIMAL, UNITS);
                    if (i == 0) {
                        exp = new SalesExcelNM(new ExtCustomTableHolder(excelTable), sheetName,
                                Constant.SALES_PROJECTION, SALES_PROJECTION_XLS, false, formatterMap, isAg);
                    } else {
                        if (exp != null) {
                            exp.setNextTableHolder(new ExtCustomTableHolder(excelTable), sheetName);
                        }
                    }
                    if (exp != null) {
                        boolean export = i == exportAt;
                        exp.exportMultipleTabs(export);
                    }
                }
            } else {
                List<String> columnHeader = new ArrayList<>();
                List<Object> visibleColumns = new ArrayList<>();
                for (Object obj : excelHeader.getSingleColumns()) {
                    visibleColumns.add(obj);
                }
                for (String header : excelHeader.getSingleHeaders()) {
                    columnHeader.add(StringUtils.EMPTY + header);
                }

                excelTable.setVisibleColumns(visibleColumns.toArray());
                excelTable.setColumnHeaders(Arrays.copyOf(columnHeader.toArray(), columnHeader.size(), String[].class));
                tableLayout.addComponent(excelTable);
                exp = new ExcelExport(new ExtCustomTableHolder(excelTable), Constant.SALES_PROJECTION, Constant.SALES_PROJECTION, SALES_PROJECTION_XLS, false);
                
                exp.export();
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void checkFrequencyChange() {
        boolean customerFlag = (generateCustomerToBeLoaded.containsAll(projectionDTO.getCustomerLevelFilter())
                    && generateCustomerToBeLoaded.size() == projectionDTO.getCustomerLevelFilter().size());
            boolean productFlag = (generateProductToBeLoaded.containsAll(projectionDTO.getProductLevelFilter())
                    && generateProductToBeLoaded.size() == projectionDTO.getProductLevelFilter().size());
            commercialGenerate(customerFlag, productFlag);
    }

}
