/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.salesprojection.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.gtnforecasting.abstractforecast.ForecastSalesProjection;
import com.stpl.app.gtnforecasting.dto.SalesRowDto;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.logic.GroupFilter;
import com.stpl.app.gtnforecasting.logic.Utility;
import com.stpl.app.gtnforecasting.lookups.NMPmpyCalculator;
import com.stpl.app.gtnforecasting.lookups.logic.PmpyLogic;
import com.stpl.app.gtnforecasting.salesprojection.logic.tablelogic.NMSalesProjectionTableLogic;
import com.stpl.app.gtnforecasting.salesprojection.utils.HeaderUtils;
import com.stpl.app.gtnforecasting.salesprojectionresults.logic.SPRCommonLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.ui.ForecastUI;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.CommonUtil;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import static com.stpl.app.gtnforecasting.utils.CommonUtils.isInteger;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.DASH;
import com.stpl.app.gtnforecasting.utils.UISecurityUtil;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import static com.stpl.app.utils.Constants.CommonConstants.ACTION_EDIT;
import static com.stpl.app.utils.Constants.CommonConstants.ACTION_VIEW;
import static com.stpl.app.utils.Constants.CommonConstants.NULL;
import static com.stpl.app.utils.Constants.CommonConstants.SELECT_ONE;
import static com.stpl.app.utils.Constants.FrequencyConstants.ANNUAL;
import static com.stpl.app.utils.Constants.FrequencyConstants.MONTHLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.QUARTERLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.SEMI_ANNUAL;
import static com.stpl.app.utils.Constants.LabelConstants.PRODUCT;
import com.stpl.app.utils.UiUtils;
import com.stpl.ifs.ui.extfilteringtable.FreezePagedTreeTable;
import com.stpl.ifs.ui.extfilteringtable.PageTreeTableLogic;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Window;
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
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.jboss.logging.Logger;

/**
 * Commercial Sales Projection
 *
 * @author sibi
 */
public class NMSalesProjection extends ForecastSalesProjection {

    final StplSecurity stplSecurity = new StplSecurity();
    boolean generated = false;
    boolean firstGenerated = false;
    private static final Logger LOGGER = Logger.getLogger(NMSalesProjection.class);
    List<String> projectedPeriodList = new ArrayList();
    private SPRCommonLogic sprCommonLogic = new SPRCommonLogic();
    protected NMSalesProjectionTableLogic mSalesProjectionTableLogic;
    protected String ALL = "ALL";
    public static final String SID = "SID";
  
    public static final String SELECT_ALL_LABEL = "Select All";
    protected CustomMenuBar.SubMenuCloseListener productListener = new CustomMenuBar.SubMenuCloseListener() {
        @Override
        public void subMenuClose(CustomMenuBar.SubMenuCloseEvent event) {
            generateProductToBeLoaded=(List) commonLogic.getFilterValues(productFilterValues).get(SID);
            loadCustomerLevelFilter(String.valueOf(customerlevelDdlb.getValue()));
        }
    };
   
    protected CustomMenuBar.SubMenuCloseListener cutomerListener = new CustomMenuBar.SubMenuCloseListener() {
        @Override
        public void subMenuClose(CustomMenuBar.SubMenuCloseEvent event) {
             generateCustomerToBeLoaded=(List) commonLogic.getFilterValues(customerFilterValues).get(SID);
            loadProductLevelFilter(String.valueOf(productlevelDdlb.getValue()));
        }
    };
    public NMSalesProjection(SessionDTO session, String screenName) {
        super(session, screenName);
        if (CommonUtil.isValueEligibleForLoading()) {
            loadSalesInclusion();
            loadDisplayFormatDdlb();
            CommonUtil commonUtils = CommonUtil.getInstance();
            commonUtils.loadConvertionFactorComboBox(conversionFactorDdlb, Constant.CONVERSION_FACTOR);
        }
        if (ACTION_EDIT.getConstant().equalsIgnoreCase(session.getAction()) || ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction())) {
            super.setProjectionSelection(false);
        }

        init();
    }

    /**
     * Init method
     */
    public void init() {
        LOGGER.debug("Inside NMSalesProjection Screen " + session.getUserId());
        configureProjectionDTO();
        Utility.loadHierarchyList(session);
        generateBtnLogic(null);
        configureGroupDDLB();
        super.configureGraph();
        securityForButton();
    }

    @Override
    protected void levelFilterDdlbChangeOption() {
        projectionDTO.setHierarchyNo(StringUtils.EMPTY);
        if (levelFilter.getValue() != null) {
            projectionDTO.setIsFilter(true);
            projectionDTO.setLevelFilter(true);
            projectionDTO.setLevelFilterValue(String.valueOf(UiUtils.parseStringToInteger(String.valueOf(levelFilter.getValue()).split("-")[0].trim())));
            projectionDTO.setFilterLevelNo(Integer.valueOf(projectionDTO.getLevelFilterValue()));
            mSalesProjectionTableLogic.setProjectionResultsData(projectionDTO);
            projectionDTO.setLevelFilter(false);
        } else {
            projectionDTO.setIsFilter(false);
            projectionDTO.setLevelFilter(false);
            projectionDTO.setFilterLevelNo(0);
            projectionDTO.setLevelFilterValue(StringUtils.EMPTY);
            mSalesProjectionTableLogic.setProjectionResultsData(projectionDTO);
        }
    }

    @Override
    protected void excelExportLogic() {
        try {
            configureExcelResultTable();
            levelFilterDdlbChangeOption(true);
            excelTable.setRefresh(Boolean.TRUE);
            excelTable.setDoubleHeaderVisible(false);
            ForecastUI.EXCEL_CLOSE = true;
            ExcelExport exp = null;
            int exportAt = projectionDTO.getHeaderMapForExcel().size() - 1;
            if ((QUARTERLY.getConstant().equals(String.valueOf(nmFrequencyDdlb.getValue())) || MONTHLY.getConstant().equals(String.valueOf(nmFrequencyDdlb.getValue())))) {
                for (int i = 0; i < projectionDTO.getHeaderMapForExcel().size(); i++) {
                    excelTable.setVisibleColumns(((List<Object>) projectionDTO.getHeaderMapForExcel().get(i).get(0)).toArray());
                    Object[] header = ((List<Object>) projectionDTO.getHeaderMapForExcel().get(i).get(1)).toArray();
                    excelTable.setColumnHeaders(Arrays.copyOf(header, header.length, String[].class));
                    excelTable.setRefresh(true);
                    String sheetName = "Year " + String.valueOf(projectionDTO.getHeaderMapForExcel().get(i).get(NumericConstants.TWO));
                    ForecastUI.EXCEL_CLOSE = true;
                    if (i == 0) {
                        exp = new ExcelExport(new ExtCustomTableHolder(excelTable), sheetName, Constant.SALES_PROJECTION, "Sales_Projection.xls", false);
                    } else {
                        exp.setNextTableHolder(new ExtCustomTableHolder(excelTable), sheetName);
                    }
                    if (i == exportAt) {
                        exp.exportMultipleTabs(true);
                    } else {
                        exp.exportMultipleTabs(false);
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
                exp = new ExcelExport(new ExtCustomTableHolder(excelTable), Constant.SALES_PROJECTION, Constant.SALES_PROJECTION, "Sales_Projection.xls", false);
                exp.export();
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    @Override
    protected void enableDisableFields() {
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
            calculateButtonLogic();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(NMSalesProjection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void massUpdateLogic() {
        if ((Constant.LabelConstants.DISABLE).equals(massUpdate.getValue())) {
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
        LOGGER.debug(" customId  " + customId);
        projectionDTO.setCustomId(customId);
        if (customId != 0) {
            session.setCustomId(customId);
            Utility.loadCustomHierarchyList(session);
        }
        LOGGER.debug(" currentHierarchy " + currentHierarchy.size());
        generateLogic();
        if (viewDdlb.getValue() != null
                && !Constant.NULL.equalsIgnoreCase(String.valueOf(viewDdlb.getValue()))
                && !SELECT_ONE.getConstant().equalsIgnoreCase(String.valueOf(viewDdlb.getValue()))
                && !DASH.equalsIgnoreCase(String.valueOf(viewDdlb.getValue()))) {
            editBtn.setEnabled(true);
        } else {
            editBtn.setEnabled(false);
        }
        LOGGER.debug("customDdlbChangeOption ValueChangeEvent ends ");
    }

    @Override
    protected void expandButtonLogic() {
        try {
            if (StringUtils.isNotBlank(String.valueOf(level.getValue())) || !Constant.NULL.equals(String.valueOf(level.getValue()))) {
                projectionDTO.setExpandCollapseFlag(Boolean.TRUE);
                expandCollapseLevelOption(true, level.getValue());
                projectionDTO.setExpandCollapseFlag(Boolean.FALSE);
            } else {
                projectionDTO.setExpandCollapseFlag(Boolean.FALSE);
                AbstractNotificationUtils.getErrorNotification("No Level Selected", "Please select a Level from the drop down.");
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    @Override
    protected void collapseButtonLogic() {
        mSalesProjectionTableLogic.setRefresh(false);
        expandCollapseLevelOption(false, level.getValue());
        mSalesProjectionTableLogic.setRefresh(true);
    }

    @Override
    protected void fieldDdlbLogic() {
        if (Constant.ACCOUNT_GROWTH.equals(fieldDdlb.getValue()) || Constant.PRODUCT_GROWTH.equals(fieldDdlb.getValue()) || Constant.SALES_SMALL.equals(fieldDdlb.getValue()) || Constant.UNIT_VOLUME.equals(fieldDdlb.getValue())|| Constant.SELECT_ONE.equals(fieldDdlb.getValue()) || Constant.NULL.equals(String.valueOf(fieldDdlb.getValue()))) {
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
         loadDisplayFormatDdlb();
        if (ACTION_EDIT.getConstant().equalsIgnoreCase(session.getAction()) || ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction())) {
            super.setProjectionSelection(true);
            loadSalesInclusion();
        } else {
            resetForAdd();
        }
    }

    public void resetForAdd() throws IllegalStateException {
        CommonLogic.unCheckMultiSelect(productFilterValues);
        CommonLogic.unCheckMultiSelect(customerFilterValues);
        productlevelDdlb.select(Constant.SELECT_ONE);
        customerlevelDdlb.select(Constant.SELECT_ONE);
        projectionDTO.setProductLevelFilter(Collections.EMPTY_LIST);
        projectionDTO.setCustomerLevelFilter(Collections.EMPTY_LIST);
        loadSalesInclusion();
    }

    @Override
    protected void pmpyLogic() {
        PmpyLogic pmpyLogic = new PmpyLogic();

        boolean tpSelected = false;
        boolean hasActuals = false;
        int i = 0;
        String hierarchyNo = StringUtils.EMPTY;

        for (SalesRowDto dto : customContainer.getBeans()) {
            if ((Boolean) dto.getPropertyValue(Constant.CHECK) && (Constant.TRADINGPARTNER.equals(dto.getHierarchyLevel()) || Constant.TRADING_PARTNER.equals(dto.getHierarchyLevel()))) {
                tpSelected = true;
                i++;
                hierarchyNo = dto.getHierarchyNo();

            }
            for (Object key : dto.getProperties().keySet()) {
                if (((Boolean) dto.getPropertyValue(Constant.CHECK) && (Constant.TRADINGPARTNER.equals(dto.getHierarchyLevel()) || Constant.TRADING_PARTNER.equals(dto.getHierarchyLevel()))) && (String.valueOf(key).contains("Actual"))) {
                    String value = String.valueOf(dto.getProperties().get(key));
                    if (!value.equals("-") && !value.equals("0.00") && !value.equals("$0") && !value.equals(DASH) && !value.equals("0.000000")) {
                        hasActuals = true;
                    }

                }

            }

        }

        if (tpSelected && i == 1) {

            if (!hasActuals) {
                String historyPeriods = String.valueOf(historyDdlb.getValue());

                hierarchyNo = " WHERE RLD1.HIERARCHY_NO like '" + hierarchyNo + "' ";
                Object[] inputParameters = new Object[NumericConstants.TEN];
                inputParameters[0] = session.getProjectionId();
                inputParameters[1] = hierarchyNo;
                List<Object> projectionDetailsIdForPMPY = pmpyLogic.getNmProjectionDetId(inputParameters);
                int projectionDetailsId = Integer.valueOf(projectionDetailsIdForPMPY.get(0).toString());
                List list = pmpyLogic.getTradingPartnerInfo(projectionDetailsId);

                String tradeName = String.valueOf(list.get(0) != null ? list.get(0) : " ");
                String tradeNo = String.valueOf(list.get(1) != null ? list.get(1) : " ");
                String contractHolder = String.valueOf(list.get(NumericConstants.TWO) != null ? list.get(NumericConstants.TWO) : " ");

                final NMPmpyCalculator pmpyCalc = new NMPmpyCalculator(historyPeriods, projectionDetailsIdForPMPY, rightHeader, tradeName, tradeNo, contractHolder, session, projectionDTO);
                pmpyCalc.addCloseListener(new Window.CloseListener() {
                    @Override
                    public void windowClose(Window.CloseEvent e) {
                        if (pmpyCalc.isImportEvent()) {
                            refreshTableData(getCheckedRecordsHierarchyNo());
                        }

                    }
                });
                getUI().addWindow(pmpyCalc);
            } else {
                AbstractNotificationUtils.getErrorNotification("PMPY calculation cannot be performed",
                        "PMPY calculation cannot be performed for trading partner which already have history values.");
            }
        } else if (tpSelected && i > 1) {
            AbstractNotificationUtils.getErrorNotification("More than one Trading Partner Selected",
                    "There are More than one trading partners selected.\n Please select only one trading partner and try again");

        } else {

            AbstractNotificationUtils.getErrorNotification("No Trading Partner Selected.", "Please select a Trading Partner. ");

        }

    }

    protected void channelsViewChange() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void generateBtnLogic(Button.ClickEvent event) {
        try {
            projectionDTO.setCustomerLevelFilter(generateCustomerToBeLoaded);
            projectionDTO.setProductLevelFilter(generateProductToBeLoaded);
            if (checkSelection()) {
                LOGGER.debug("generate button click listener starts ");
                generated = true;
                firstGenerated = true;
                tableLayout.removeAllComponents();
                mSalesProjectionTableLogic = new NMSalesProjectionTableLogic();
                resultsTable = new FreezePagedTreeTable(mSalesProjectionTableLogic);
                super.initializeResultTable();
                configureResultTable();
                addResultTable();
                generateLogic();
                generated = false;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.debug("generate button click listener ends ");

    }

    /**
     * Configures the result table.
     */
    private void configureResultTable() {
        configureProjectionDTO();
        mSalesProjectionTableLogic.setTreeNodeMultiClick(false);
        mSalesProjectionTableLogic.setPageLength(NumericConstants.TWENTY);
        mSalesProjectionTableLogic.sinkItemPerPageWithPageLength(false);
        List<Integer> pagelength = CommonLogic.getPageNumber();
        mSalesProjectionTableLogic.getControlConfig().setPageLengthsAndCaptions(pagelength);
        excelHeader = new CustomTableHeaderDTO();
        leftHeader = HeaderUtils.getSalesLeftTableColumns(projectionDTO);

        excelHeader.addSingleColumn(Constant.LEVELNAME, "Level Name", String.class);
        if (projectionDTO.getScreenName().equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
            excelHeader.addSingleColumn(Constant.GROUP, "Group", String.class);
        }
        excelHeader.addSingleColumn(Constant.BASELINE, "Base Line", String.class);
        excelHeader.addSingleColumn(Constant.METHODOLOGY, "Methodology", String.class);
        rightHeader = HeaderUtils.getSalesProjectionRightTableColumns(projectionDTO, fullHeader, excelHeader);
        resultsTable.getLeftFreezeAsTable().setFilterBarVisible(true);
        customContainer = new ExtTreeContainer<>(SalesRowDto.class, ExtContainer.DataStructureMode.MAP);
        customContainer.setColumnProperties(leftHeader.getProperties());
        customContainer.setColumnProperties(rightHeader.getProperties());
        mSalesProjectionTableLogic.setContainerDataSource(customContainer);
        leftTable = resultsTable.getLeftFreezeAsTable();
        rightTable = resultsTable.getRightFreezeAsTable();
        leftTable.setEditable(true);
        rightTable.setEditable(true);

        leftTable.setVisibleColumns(leftHeader.getSingleColumns().toArray());
        leftTable.setColumnHeaders(leftHeader.getSingleHeaders().toArray(new String[leftHeader.getSingleHeaders().size()]));
        leftTable.setDoubleHeaderVisible(true);
        leftTable.setDoubleHeaderVisibleColumns(leftHeader.getDoubleColumns().toArray());
        leftTable.setDoubleHeaderColumnHeaders(leftHeader.getDoubleHeaders().toArray(new String[leftHeader.getDoubleHeaders().size()]));

        rightTable.setVisibleColumns(rightHeader.getSingleColumns().toArray());
        rightTable.setColumnHeaders(rightHeader.getSingleHeaders().toArray(new String[rightHeader.getSingleHeaders().size()]));

        for (Object obj : leftHeader.getSingleColumns()) {
            if (String.valueOf(obj).contains(Constant.GROUP)) {
                resultsTable.getLeftFreezeAsTable().setColumnWidth(obj, NumericConstants.ONE_THREE_FIVE);
            } else if (String.valueOf(obj).contains(Constant.LEVELNAME)) {
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
        HorizontalLayout controls = mSalesProjectionTableLogic.createControls();
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
                if (customId != 0) {
                    List<Leveldto> hierarchyList = new ArrayList<>();
                    for (Leveldto leveldto : session.getCustomHierarchyMap().get(customId)) {
                        if (!"D".equals(leveldto.getHierarchyIndicator())) {
                            hierarchyList.add(leveldto);
                        }
                    }
                    Utility.loadLevelValue(level, levelFilter, null, hierarchyList, Constant.CUSTOM_LABEL);
                    Leveldto levelDTO = (Leveldto) session.getCustomHierarchyMap().get(customId).get(0);
                    projectionDTO.setHierarchyIndicator(levelDTO.getHierarchyIndicator());
                }
            } else if ((Constant.CUSTOMER_SMALL).equals(view.getValue())) {
                leftTable.setColumnCollapsingAllowed(true);
                leftTable.setColumnCollapsed(Constant.GROUP, false);
                Utility.loadLevelValue(level, levelFilter, null, session.getCustomerHierarchyList(), view.getValue().toString());
            }
            CommonUtil.getInstance().waitsForOtherThreadsToComplete(session.getFutureValue(Constant.FILE_INSERT, 0));
            loadAllFilters();
            mSalesProjectionTableLogic.setProjectionResultsData(projectionDTO);
        }

    public void loadAllFilters() {
        List<String> checkedValues = getCheckedSalesInclusionValues();
        projectionDTO.getSessionDTO().setSalesInclusion(ALL);
        if (checkedValues.size() == 1) {
            projectionDTO.getSessionDTO().setSalesInclusion(checkedValues.get(0).equalsIgnoreCase("Yes") ? "1" : "0");
        }
        projectionDTO.setCustomerLevelFilter(getCustomerFilterValues());
        projectionDTO.setProductLevelFilter(getProductFilterValues());
        projectionDTO.setUomCode(unitOfMeasureDdlb.getValue() == null ? "EACH" : String.valueOf(unitOfMeasureDdlb.getValue()));
	projectionDTO.setDisplayFormat(CommonUtil.getDisplayFormatSelectedValues(displayFormatValues));
        projectionDTO.setConversionFactor(conversionFactorDdlb.getValue());
        CommonLogic.updateForFilter(projectionDTO,"SALES",false);
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
                ? 1 : Integer.valueOf(projectionDTO.getSessionDTO().getCustomerLevelNumber()));
        projectionDTO.setProductLevelNo(StringUtils.isBlank(projectionDTO.getSessionDTO().getProductLevelNumber()) || Constant.NULL.equals(projectionDTO.getSessionDTO().getProductLevelNumber())
                ? 1 : Integer.valueOf(projectionDTO.getSessionDTO().getProductLevelNumber()));
        projectionDTO.setProjectionId(projectionDTO.getSessionDTO().getProjectionId());
        projectionDTO.setUserId(Integer.valueOf(projectionDTO.getSessionDTO().getUserId()));
        projectionDTO.setSessionId(Integer.valueOf(projectionDTO.getSessionDTO().getSessionId()));
        projectionDTO.setFrequency(String.valueOf(nmFrequencyDdlb.getValue()));
        projectionDTO.setProjectionOrder(String.valueOf(proPeriodOrd.getValue()));
        projectionDTO.setActualsOrProjections(String.valueOf(actualsProjections.getValue()));
        String history = String.valueOf(historyDdlb.getValue());
        history = history.trim();
        if (history != null && !StringUtils.isBlank(history) && !NULL.equals(history) && !SELECT_ONE.getConstant().equals(history)) {
            toHist = true;
            projectionDTO.setHistory(history);
            historyNum = Integer.valueOf(projectionDTO.getHistory());
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
        
          if (CommonUtil.isValueEligibleForLoading()) {
            salesProjectionSelection.setVisible(false);
            tabsheet1.addTab(salesProjectionSelectionLayout,"Display Selection");
            tabsheet1.addTab(salesProjectionfilterLayout,"Filter Options");
            tabsheet1.addStyleName(ValoTheme.TABSHEET_FRAMED);
            tabsheet1.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
            loadProductLevel();
            loadCustomerLevel();
            loadCustomerLevelFilter(StringUtils.EMPTY);
            loadProductLevelFilter(StringUtils.EMPTY);
            commonLogic.loadUnitOfMeasureDdlb(unitOfMeasureDdlb,session);
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
                generate.setVisible(Boolean.FALSE);
                expand.setVisible(Boolean.FALSE);
                collapse.setVisible(Boolean.FALSE);
                newBtn.setVisible(Boolean.FALSE);
                editBtn.setVisible(Boolean.FALSE);
            }
            if (functionPsHM.get(CommonUtils.PMPY) != null && ((AppPermission) functionPsHM.get(CommonUtils.PMPY)).isFunctionFlag()) {
                pmpy.setVisible(true);
            } else {
                pmpy.setVisible(false);
            }
            if (functionPsHM.get(CommonUtils.ALT_HISTORY_BTN) != null && ((AppPermission) functionPsHM.get(CommonUtils.ALT_HISTORY_BTN)).isFunctionFlag()) {
                altHistoryBtn.setVisible(true);
            } else {
                altHistoryBtn.setVisible(false);
            }
            if (!(functionPsHM.get(CommonUtils.CALCULATE) != null && ((AppPermission) functionPsHM.get(CommonUtils.CALCULATE)).isFunctionFlag())) {
                calculate.setVisible(Boolean.FALSE);
                populate.setVisible(Boolean.FALSE);
                adjust.setVisible(Boolean.FALSE);
            }
            
            if ((functionPsHM.get(CommonUtils.TOTAL_LIVES_LAYOUT) != null && ((AppPermission) functionPsHM.get(CommonUtils.TOTAL_LIVES_LAYOUT)).isFunctionFlag())) {
                totalLivesLayout.setVisible(true);
            } else {
                totalLivesLayout.setVisible(false);
            }

        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (SystemException ex) {
            LOGGER.error(ex);
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
            sprCommonLogic.saveNMSRPSelection(map, session.getProjectionId(), Constant.SALES_PROJECTION);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("saveSPResults method ends");
    }

    public boolean getSubmitFlag() {
        if (customContainer.getItemIds().size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public PageTreeTableLogic getTableLogic() {
        return mSalesProjectionTableLogic;
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
    
    
    private void loadProductLevel()  {

        int hierarchyLevelNo = isInteger(session.getProductLevelNumber()) ? Integer.valueOf(session.getProductLevelNumber()) : 0;
        currentHierarchy = CommonLogic.getProductHierarchy(session.getProjectionId(), hierarchyLevelNo, session.getProdRelationshipBuilderSid());
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
        productFilterValues = productFilterDdlb.addItem("-Select Level-", null);
        
        if (!levelNo.isEmpty()) {
            productLevelFilter.add(0, new Object[]{0, SELECT_ALL});
            productLevelFilter.addAll(commonLogic.getProductLevelValues(session.getProjectionId(), levelNo, projectionDTO,(List)generateCustomerToBeLoaded,new ArrayList<>()));
            CommonLogic.loadCustomMenuBar(productLevelFilter, productFilterValues);
        }
        productFilterDdlb.setScrollable(true);
        productFilterDdlb.setPageLength(NumericConstants.TEN);
        CommonLogic.loadMenuBar(generateProductToBeLoaded, productFilterValues);
        productFilterDdlb.addSubMenuCloseListener(productListener);
    }
    public static final String SELECT_ALL = "Select All";

    private void loadCustomerLevel() {
        int hierarchyNo = isInteger(session.getCustomerLevelNumber()) ? Integer.valueOf(session.getCustomerLevelNumber()) : 0;
        currentHierarchy = CommonLogic.getCustomerHierarchy(session.getProjectionId(), hierarchyNo, session.getCustRelationshipBuilderSid());
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
        customerFilterValues = customerFilterDdlb.addItem("-Select Level-", null);
        if (!levelNo.isEmpty()) {
            customerLevelFilter.add(0, new Object[]{0, SELECT_ALL});
            customerLevelFilter.addAll(commonLogic.getCustomerLevelValues(session.getProjectionId(), levelNo, projectionDTO,(List)generateProductToBeLoaded,new ArrayList<>()));
            CommonLogic.loadCustomMenuBar(customerLevelFilter,customerFilterValues);
        }
        customerFilterDdlb.setScrollable(true);
        customerFilterDdlb.setPageLength(NumericConstants.TEN);
        CommonLogic.loadMenuBar(generateCustomerToBeLoaded, customerFilterValues);
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

    private void loadSalesInclusion() throws IllegalStateException {
        String[] variablesalesInclusion= {"Yes", "No"};
        salesInclusionDdlb.removeItems();
        salesInclusionValues = salesInclusionDdlb.addItem("-Select Values-", null);
        CustomMenuBar.CustomMenuItem[] salesInclusionCustomItem = new CustomMenuBar.CustomMenuItem[variablesalesInclusion.length];
        for (int i = 0; i < variablesalesInclusion.length; i++) {
            salesInclusionCustomItem[i] = salesInclusionValues.addItem(variablesalesInclusion[i].trim(), null);
            salesInclusionCustomItem[i].setCheckable(true);
            salesInclusionCustomItem[i].setItemClickable(true);
            salesInclusionCustomItem[i].setItemClickNotClosable(true);
            
        }
    }
    
      private void loadDisplayFormatDdlb() throws IllegalStateException {
        List<Object[]> displayFormatFilter = new ArrayList<>();
        displayFormatFilter.addAll(commonLogic.displayFormatValues());
        displayFormatValues = displayFormatDdlb.addItem("-Select Values-", null);
        commonLogic.loadDisplayFormat(displayFormatFilter, displayFormatValues);
        displayFormatDdlb.setScrollable(true);
    }
      
}
