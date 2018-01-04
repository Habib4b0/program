/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.discountprojectionresults.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.gtnforecasting.abstractforecast.ForecastDiscountProjectionResults;
import com.stpl.app.gtnforecasting.discountprojectionresults.dto.ComparisonFilterGenerator;
import com.stpl.app.gtnforecasting.discountprojectionresults.dto.DiscountProjectionResultsDTO;
import com.stpl.app.gtnforecasting.discountprojectionresults.logic.MMDPRLogic;
import com.stpl.app.gtnforecasting.discountprojectionresults.logic.tablelogic.DPRTableLogic;
import com.stpl.app.gtnforecasting.discountprojectionresults.utils.HeaderUtils;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.logic.DataSelectionLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.ui.ForecastUI;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.ANNUALLY;
import static com.stpl.app.gtnforecasting.utils.Constant.MONTHLY;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.HISTORY_END_MONTH;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.HISTORY_END_YEAR;
import static com.stpl.app.gtnforecasting.utils.Constant.QUARTERLY;
import static com.stpl.app.gtnforecasting.utils.Constant.SELECT_ONE;
import static com.stpl.app.gtnforecasting.utils.Constant.YEAR;
import static com.stpl.app.utils.Constants.FrequencyConstants.MONTHS;
import static com.stpl.app.utils.Constants.FrequencyConstants.QUARTERS;
import static com.stpl.app.utils.Constants.FrequencyConstants.SEMI_ANNUAL;
import static com.stpl.app.utils.Constants.FrequencyConstants.SEMI_ANNUALLY;
import static com.stpl.app.utils.Constants.LabelConstants.ASCENDING;
import static com.stpl.app.utils.Constants.LabelConstants.DISCOUNT;
import com.stpl.ifs.ui.extfilteringtable.FreezePagedTreeTable;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Sizeable;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.HorizontalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import org.asi.ui.extfilteringtable.ExtCustomTreeTable;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;

/**
 *
 * @author gopinath
 */
public class ManagedDiscountProjectionResult extends ForecastDiscountProjectionResults {

    private final SessionDTO sessionDTO;

    private String screenName = StringUtils.EMPTY;
    private final BeanItemContainer<String> historyBean = new BeanItemContainer<>(String.class);
    private DPRTableLogic dprTableLogic = new DPRTableLogic();
    private FreezePagedTreeTable freezedPagedResults = new FreezePagedTreeTable(dprTableLogic);
    private CustomTableHeaderDTO customLeftHeader = new CustomTableHeaderDTO();
    private CustomTableHeaderDTO customRightHeader = new CustomTableHeaderDTO();
    private CustomTableHeaderDTO customFullHeader = new CustomTableHeaderDTO();
    private final ProjectionSelectionDTO projectionDTO = new ProjectionSelectionDTO();
    private HorizontalLayout controlLayout;
    private ExtTreeContainer<DiscountProjectionResultsDTO> beanContainerResult = new ExtTreeContainer<>(DiscountProjectionResultsDTO.class,ExtContainer.DataStructureMode.MAP);
    private final ExtCustomTreeTable customExcelTableResult = new ExtCustomTreeTable();
    private ExtTreeContainer<DiscountProjectionResultsDTO> excelResultBean = new ExtTreeContainer<>(
            DiscountProjectionResultsDTO.class,ExtContainer.DataStructureMode.MAP);
    private final MMDPRLogic mmLogic = new MMDPRLogic();
    private final DataSelectionLogic dsLogic = new DataSelectionLogic();
    /**
     * The max split position.
     */
    private final float maxSplitPos = NumericConstants.THOUSAND;

    /**
     * The min split position.
     */
    private final float minSplitPos = NumericConstants.TWO_HUNDRED;

    /**
     * The split position.
     */
    private final float splitPos = NumericConstants.THREE_HUNDRED;
    private List<List<String>> discountlist = new ArrayList<>();

    public ManagedDiscountProjectionResult(SessionDTO session, String screenName) {
        super(screenName, session);

        LOGGER.debug("ManagedDiscountProjectionResult Constructor starts ");
        this.sessionDTO = session;
        this.screenName = screenName;
        configureFields();
        LOGGER.debug("ManagedDiscountProjectionResult Constructor ends ");
    }

    /**
     * Configure fields.
     */
    private void configureFields() {
        CommonUtils.loadDDLBFromProperties(frequencyDdlb, Constant.FREQUENCY);
        frequencyDdlb.setValue(QUARTERLY);
        historyDdlb.addItem(Constant.FOUR_QUARTERS);
        historyDdlb.setValue(Constant.FOUR_QUARTERS);
        discountOpg.setImmediate(true);
        discountOpg.addStyleName(Constant.HORIZONTAL);
        discountOpg.addStyleName(Constant.OPTION_GROUP_WIDTH);
        discountOpg.addItem(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED);
        discountOpg.addItem(Constant.SUPPLEMENTAL);
        discountOpg.addItem(Constant.BOTH);
        discountOpg.setValue(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED);
        periodOrderOpg.setImmediate(true);
        periodOrderOpg.addStyleName(Constant.HORIZONTAL);
        periodOrderOpg.addStyleName(Constant.OPTION_GROUP_WIDTH);
        periodOrderOpg.addItem(ASCENDING.getConstant());
        periodOrderOpg.addItem(Constant.DESCENDING);
        periodOrderOpg.setValue(ASCENDING.getConstant());
        actualOrProjectionsOpg.setImmediate(true);
        actualOrProjectionsOpg.addStyleName(Constant.HORIZONTAL);
        actualOrProjectionsOpg.addStyleName(Constant.OPTION_GROUP_WIDTH);
        actualOrProjectionsOpg.addItem(Constant.ACTUALS);
        actualOrProjectionsOpg.addItem(Constant.PROJECTIONS);
        actualOrProjectionsOpg.addItem(Constant.BOTH);
        actualOrProjectionsOpg.setValue(Constant.ACTUALS);
        pivotViewOpg.setImmediate(true);
        pivotViewOpg.addStyleName(Constant.HORIZONTAL);
        pivotViewOpg.addStyleName(Constant.OPTION_GROUP_WIDTH);
        pivotViewOpg.addItem(Constant.PERIOD);
        pivotViewOpg.addItem(DISCOUNT.getConstant());
        pivotViewOpg.setValue(Constant.PERIOD);
        brand.addItem(SELECT_ONE);
        therapeuticClass.addItem(SELECT_ONE);

        excelBtn.setIcon(excelExportImage);
        initializeResultTable();
        if (loadProjectionSelection()) {
            configureResultTable();
        }
        addResultTable();
        loadTherapeuticClass();
        therapeuticClass.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {

                List<String> brandList = mmLogic.getBrandValue(sessionDTO.getProjectionId(), String.valueOf(therapeuticClass.getValue()));
                if (brandList.size() != 0) {
                    for (String value : brandList) {
                        brand.addItem(value);
                    }
                }

            }
        });
        customExcelTableResult.setVisible(false);
        customExcelTableResult.setImmediate(true);

    }

    /**
     * Load history.
     *
     * @param frequency the frequency
     * @return the list
     */
    protected final List<String> loadHistory(String frequency, String period) {
        LOGGER.debug("Entering loadHistory method");
        List<String> history;
        history = sessionDTO.getFrequencyAndQuaterValue(frequency);
        Integer endValue = 0;
        if (history == null || history.isEmpty()) {
            Map<String, Integer> historyEndDetails = CommonUtils.getHistoryEndDetails(sessionDTO, frequency);
            endValue = CommonUtils.getProjections(sessionDTO.getForecastDTO().getHistoryStartDate(), CommonUtils.getDate(historyEndDetails.get(HISTORY_END_MONTH), historyEndDetails.get(HISTORY_END_YEAR)), frequency);

            history = CommonUtils.getHistoryDdlbList(endValue, period);
            sessionDTO.addFrequencyAndQuater(frequency, history);
        }
        LOGGER.debug("End of loadHistory method");
        return history;
    }

    /**
     * Initialize Result Table.
     */
    private void initializeResultTable() {
        freezedPagedResults.markAsDirty();
        freezedPagedResults.setSelectable(false);
        freezedPagedResults.setSplitPosition(splitPos, Sizeable.Unit.PIXELS);
        freezedPagedResults.setMinSplitPosition(minSplitPos, Sizeable.Unit.PIXELS);
        freezedPagedResults.setMaxSplitPosition(maxSplitPos, Sizeable.Unit.PIXELS);
        freezedPagedResults.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
    }

    /**
     * Add Result Table.
     */
    private void addResultTable() {
        tableVerticalLayout.addComponent(freezedPagedResults);
        controlLayout = dprTableLogic.createControls();
        dprTableLogic.sinkItemPerPageWithPageLength(false);
        tableVerticalLayout.addComponent(controlLayout);
        tableVerticalLayout.addComponent(customExcelTableResult);
    }

    /**
     * Configure Result Table.
     */
    private void configureResultTable() {
        dprTableLogic.setPageLength(NumericConstants.TEN);
        customFullHeader = new CustomTableHeaderDTO();
        customLeftHeader = HeaderUtils.getDiscountProjectionResultsLeftTableColumn(customFullHeader);
        customRightHeader = HeaderUtils.getMMDiscountProjectionResultsRightTableColumn(projectionDTO, customFullHeader);

        beanContainerResult = new ExtTreeContainer<>(DiscountProjectionResultsDTO.class,ExtContainer.DataStructureMode.MAP);
        beanContainerResult.setColumnProperties(customFullHeader.getProperties());
        dprTableLogic.setContainerDataSource(beanContainerResult);
        dprTableLogic.setTreeNodeMultiClick(false);
        List<Integer> pagelength = CommonLogic.getPageNumber();
        dprTableLogic.getControlConfig().setPageLengthsAndCaptions(pagelength);
        dprTableLogic.setPageLength(NumericConstants.TEN);
        dprTableLogic.sinkItemPerPageWithPageLength(false);
        final ExtFilterTreeTable leftTable = freezedPagedResults
                .getLeftFreezeAsTable();
        final ExtFilterTreeTable rightTable = freezedPagedResults
                .getRightFreezeAsTable();
        leftTable.setImmediate(true);
        rightTable.setImmediate(true);
        freezedPagedResults.setHeight(Constant.PX_390);
        leftTable.setHeight(Constant.PX_390);
        rightTable.setHeight(Constant.PX_390);
        leftTable.setColumnWidth(Constant.GROUP, NumericConstants.THREE_HUNDRED);
        leftTable.setDoubleHeaderColumnWidth(Constant.GROUP, NumericConstants.THREE_HUNDRED);
        leftTable.setVisibleColumns(customLeftHeader.getSingleColumns().toArray());
        leftTable.setColumnHeaders(customLeftHeader.getSingleHeaders().toArray(new String[customLeftHeader.getSingleHeaders().size()]));
        leftTable.setSortEnabled(false);
        rightTable.setVisibleColumns(customRightHeader.getSingleColumns().toArray());
        
        rightTable.setColumnHeaders(customRightHeader.getSingleHeaders().toArray(new String[customRightHeader.getSingleHeaders().size()]));
        for (Object propertyId : rightTable.getVisibleColumns()) {
            rightTable.setColumnAlignment(propertyId, ExtCustomTable.Align.RIGHT);
        }

        freezedPagedResults.setDoubleHeaderVisible(true);
        leftTable.setFilterBarVisible(true);
        leftTable.setFilterDecorator(new ExtDemoFilterDecorator());
        leftTable.setFilterGenerator(new ComparisonFilterGenerator(projectionDTO, dprTableLogic));
        leftTable.setSortEnabled(false);
        rightTable
                .setDoubleHeaderVisibleColumns(customRightHeader.getDoubleColumns().toArray());
        rightTable
                .setDoubleHeaderColumnHeaders(customRightHeader.getDoubleHeaders().toArray(new String[customRightHeader.getDoubleHeaders().size()]));

        freezedPagedResults.getRightFreezeAsTable().setDoubleHeaderMap(customRightHeader.getDoubleHeaderMaps());

    }

    public boolean loadProjectionSelection() {
        boolean flag = false;
        Object freq = frequencyDdlb.getValue();
        Object hist = historyDdlb.getValue();
        boolean freqFlag = false;
        int historyNum = 0;
            if ((freq != null) && (!SELECT_ONE.equals(freq.toString()))) {
                freqFlag = true;
                projectionDTO.setFrequency(freq.toString());
            }

        boolean histFlag = false;
            if ((hist != null) && (!SELECT_ONE.equals(hist.toString()))) {
                histFlag = true;
                projectionDTO.setHistory(String.valueOf(hist).replace("Years", Constant.YEAR));
                hist = String.valueOf(hist).replace("Years", Constant.YEAR);

                if (freq.equals(QUARTERLY)) {
                    historyNum = Integer.valueOf(String.valueOf(hist).replace("Quarter", StringUtils.EMPTY).replace(Constant.S_SMALL, StringUtils.EMPTY).trim());
                } else if (freq.equals(SEMI_ANNUALLY.getConstant())) {
                    historyNum = Integer.valueOf(String.valueOf(hist).replace("Semi-Annual Periods", StringUtils.EMPTY).trim());

                } else if (freq.equals(MONTHLY)) {
                    historyNum = Integer.valueOf(String.valueOf(hist).replace("Month", StringUtils.EMPTY).replace(Constant.S_SMALL, StringUtils.EMPTY).trim());

                } else if (freq.equals(ANNUALLY)) {
                    historyNum = Integer.valueOf(String.valueOf(hist).replace(Constant.YEAR, StringUtils.EMPTY).trim());
                }
            }

        if (freqFlag && histFlag) {
            flag = true;
            projectionId = sessionDTO.getProjectionId();
            discountlist = new ArrayList<>();

            projectionDTO.setForecastDTO(sessionDTO.getForecastDTO());
            projectionDTO.setHistoryNum(historyNum);
            projectionDTO.setProjectionNum(CommonUtils.getProjectionNumber(projectionDTO.getFrequency(), sessionDTO));
            projectionDTO.setProjection(StringUtils.EMPTY + projectionDTO.getProjectionNum());
            projectionDTO.setUserId(Integer.valueOf(sessionDTO.getUserId()));
            projectionDTO.setSessionId(Integer.valueOf(sessionDTO.getSessionId()));

            projectionDTO.setActualsOrProjections(String.valueOf(actualOrProjectionsOpg.getValue()));
            projectionDTO.setForecastConfigPeriods(CommonUtils.prepareProjectionPeriodList(projectionDTO));
            projectionDTO.setProjectionId(sessionDTO.getProjectionId());
            projectionDTO.setProjectionOrder(String.valueOf(periodOrderOpg.getValue()));
            projectionDTO.setPivotView(String.valueOf(pivotViewOpg.getValue()));
            tablePanel.setCaption(String.valueOf(pivotViewOpg.getValue()) + " Pivot View");
            projectionDTO.setSessionDTO(sessionDTO);
            projectionDTO.setCustomId(0);

            projectionDTO.setDiscountList(new ArrayList<>(discountlist));
            projectionDTO.setCustomerLevelNo(Integer.valueOf(sessionDTO.getCustomerLevelNumber()));
            projectionDTO.setProductLevelNo(Integer.valueOf(sessionDTO.getProductLevelNumber()));
            projectionDTO.setHierarchyIndicator(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
            projectionDTO.setCusFieldName(sessionDTO.getCusFieldName());
            projectionDTO.setDiscountValue(String.valueOf(discountOpg));
            viewChange();
            projectionDTO.setMarketTypeValue(Constant.MM);
            projectionDTO.setDefinedContract(dsLogic.getContractValue(StringUtils.EMPTY + sessionDTO.getCustomerHierarchyId()));
        }

        return flag;
    }

    public void viewChange() {
        projectionDTO.setIsCustomHierarchy(false);
        final ExtFilterTreeTable leftTable = freezedPagedResults
                .getLeftFreezeAsTable();
        leftTable.setFilterBarVisible(true);
        leftTable.setFilterDecorator(new ExtDemoFilterDecorator());
        leftTable.setFilterGenerator(new ComparisonFilterGenerator(projectionDTO, dprTableLogic));
        if (pivotViewOpg.getValue() != null) {
            projectionDTO.setView(String.valueOf(pivotViewOpg.getValue()));
            customIdToSelect = 0;
            projectionDTO.setIsCustomHierarchy(false);

        }

    }

    public void generateLogic() {
        dprTableLogic.clearAll();
        dprTableLogic.setRefresh(false);
        loadResultTable(0, StringUtils.EMPTY);
        dprTableLogic.setRefresh(true);

    }

    private void loadResultTable(int levelNo, String hierarchyNo) {
        final ExtFilterTreeTable leftTable = freezedPagedResults
                .getLeftFreezeAsTable();
        projectionDTO.setFilterLevelNo(levelNo);
        projectionDTO.setFilterHierarchyNo(hierarchyNo);
        projectionDTO.setProductHierarchyNo(StringUtils.EMPTY);
        projectionDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
        dprTableLogic.setProjectionResultsData(projectionDTO, screenName);
        leftTable.setFilterBarVisible(true);
        leftTable.setFilterDecorator(new ExtDemoFilterDecorator());
        leftTable.setFilterGenerator(new ComparisonFilterGenerator(projectionDTO, dprTableLogic));
    }

    public void loadTherapeuticClass() {
        List<String> listValue = mmLogic.getTherapeuticValue(sessionDTO.getProjectionId());
        if (listValue.size() != 0) {
            for (String list : listValue) {
                therapeuticClass.addItem(list);
            }
        }

    }

    private void configureExcelResultTable() {
        excelResultBean = new ExtTreeContainer<>(DiscountProjectionResultsDTO.class,ExtContainer.DataStructureMode.MAP);
        excelResultBean.setColumnProperties(customFullHeader.getProperties());
        customExcelTableResult.setRefresh(false);
        customExcelTableResult.setVisible(false);
        customExcelTableResult.setContainerDataSource(excelResultBean);
        customExcelTableResult.setVisibleColumns(customFullHeader.getSingleColumns().toArray());
        customExcelTableResult.setColumnHeaders(customFullHeader.getSingleHeaders().toArray(new String[customFullHeader.getSingleHeaders().size()]));
        customExcelTableResult.setDoubleHeaderVisible(true);
        customExcelTableResult
                .setDoubleHeaderVisibleColumns(customFullHeader.getDoubleColumns().toArray());
        customExcelTableResult
                .setDoubleHeaderColumnHeaders(customFullHeader.getDoubleHeaders().toArray(new String[customFullHeader.getDoubleHeaders().size()]));

        customExcelTableResult.setDoubleHeaderMap(customFullHeader.getDoubleHeaderMaps());
        customExcelTableResult.setRefresh(true);
    }

    /**
     * Loads the Excel Results.
     */
    @SuppressWarnings("serial")
    private void loadExcelResultTable() {
        excelResultBean.removeAllItems();

        int count1 = mmLogic.getConfiguredMMDiscountResultsCount(new Object(), projectionDTO);

        List<DiscountProjectionResultsDTO> resultList1 = mmLogic.getConfiguredMMDicountResults(new Object(), 0, count1, projectionDTO);
        loadDataToContainer(resultList1, null);
    }

    public void loadDataToContainer(List<DiscountProjectionResultsDTO> resultList, Object parentId) {
        for (DiscountProjectionResultsDTO dto : resultList) {
            excelResultBean.addBean(dto);
            if (parentId != null) {
                excelResultBean.setParent(dto, parentId);
            }
            if (dto.getParent() == 1) {
                excelResultBean.setChildrenAllowed(dto, true);
                addLowerLevelsForExport(dto);
            } else {
                excelResultBean.setChildrenAllowed(dto, false);
            }
        }
    }

    public void addLowerLevelsForExport(Object id) {
        int count = mmLogic.getConfiguredMMDiscountResultsCount(id, projectionDTO);
        List<DiscountProjectionResultsDTO> resultList = mmLogic.getConfiguredMMDicountResults(id, 0, count, projectionDTO);
        loadDataToContainer(resultList, id);
    }

    @Override
    protected void loadFrequency() {
        LOGGER.debug("DPR ValueChangeEvent initiated with frequency -->"
                + frequencyDdlb.getValue());
        if (frequencyDdlb.getValue() == null) {
            historyDdlb.removeAllItems();
            historyDdlb.addItem(SELECT_ONE);
            historyDdlb.setNullSelectionItemId(SELECT_ONE);
        } else {
            historyDdlb.removeAllItems();
            historyDdlb.addItem(SELECT_ONE);
            historyDdlb.setNullSelectionItemId(SELECT_ONE);
            String historyConstant = StringUtils.EMPTY;
            if (ANNUALLY.equals(String.valueOf(frequencyDdlb.getValue()))) {
                historyBean.addAll(loadHistory(ANNUALLY, YEAR));
                if (historyBean.size() >= 1) {
                    historyConstant = "1 Year";
                } else {
                    historyConstant = SELECT_ONE;
                }
            } else if (SEMI_ANNUALLY.getConstant().equals(String.valueOf(frequencyDdlb
                    .getValue()))) {
                historyBean.addAll(loadHistory(SEMI_ANNUALLY.getConstant(), SEMI_ANNUAL.getConstant()));
                if (historyBean.size() >= NumericConstants.TWO) {
                    historyConstant = "2 Semi-Annual";
                } else {
                    historyConstant = SELECT_ONE;
                }
            } else if (QUARTERLY.equals(
                    String.valueOf(frequencyDdlb.getValue()))) {
                historyBean.addAll(loadHistory(QUARTERLY, QUARTERS.getConstant()));
                if (historyBean.size() >= NumericConstants.FOUR) {
                    historyConstant = Constant.FOUR_QUARTERS;
                } else {
                    historyConstant = SELECT_ONE;
                }

            } else if (MONTHLY.equals(
                    String.valueOf(frequencyDdlb.getValue()))) {
                historyBean.addAll(loadHistory(MONTHLY, MONTHS.getConstant()));
                if (historyBean.size() >= NumericConstants.TWELVE) {
                    historyConstant = "12 Months";
                } else {
                    historyConstant = SELECT_ONE;
                }
            }

            historyDdlb.setContainerDataSource(historyBean);
            historyDdlb.setValue(historyConstant);
        }
        LOGGER.debug("DPR ValueChangeEvent ends ");
    }

    @Override
    protected void resetButtonLogic() {
        new AbstractNotificationUtils() {
            @Override
            public void noMethod() {
                return;
            }

            @Override
            /**
             * The method is triggered when Yes button of the message box is
             * pressed .
             *
             * @param buttonId The buttonId of the pressed button.
             */
            public void yesMethod() {
                frequencyDdlb.select(QUARTERLY);
                historyDdlb.select(Constant.FOUR_QUARTERS);
                pivotViewOpg.select(Constant.PERIOD);
                periodOrderOpg.select(ASCENDING.getConstant());
                actualOrProjectionsOpg.select(Constant.ACTUALS);
                therapeuticClass.select(SELECT_ONE);
                brand.select(SELECT_ONE);
            }
        }.getConfirmationMessage("Reset Confirmation", "Are you sure you want to reset the page to default/previous values?");
    }

    @Override
    protected void generateButtonLogic() {
        if (loadProjectionSelection()) {
            tableVerticalLayout.removeComponent(freezedPagedResults);
            tableVerticalLayout.removeComponent(controlLayout);

            dprTableLogic = new DPRTableLogic();
            freezedPagedResults = new FreezePagedTreeTable(dprTableLogic);
            initializeResultTable();
            configureResultTable();
            addResultTable();
            generateLogic();
        } else {
            MessageBox.showPlain(Icon.ERROR, "Generate Error", "You must select a Frequency and History from the drop down list boxes.", ButtonId.OK);
        }
    }

    @Override
    protected void newCustomHierarchyLogic() {
        return;
    }

    @Override
    protected void excelButtonLogic() {
        configureExcelResultTable();
        loadExcelResultTable();
        ForecastUI.EXCEL_CLOSE=true;
        ExcelExport exp = null;
        if (Constant.PERIOD.equals(String.valueOf(pivotViewOpg.getValue())) && (QUARTERLY.equals(String.valueOf(frequencyDdlb.getValue())) || MONTHLY.equals(String.valueOf(frequencyDdlb.getValue())))) {
            int exportAt = projectionDTO.getHeaderMapForExcel().size() - 1;
            for (int i = 0; i < projectionDTO.getHeaderMapForExcel().size(); i++) {
                customExcelTableResult.setVisibleColumns(((List<Object>) projectionDTO.getHeaderMapForExcel().get(i).get(0)).toArray());
                Object[] header = ((List<Object>) projectionDTO.getHeaderMapForExcel().get(i).get(1)).toArray();
                customExcelTableResult.setColumnHeaders(Arrays.copyOf(header, header.length, String[].class));
                customExcelTableResult
                        .setDoubleHeaderVisibleColumns(((List<Object>) projectionDTO.getHeaderMapForExcel().get(i).get(NumericConstants.THREE)).toArray());
                Object[] doubleHeader = ((List<Object>) projectionDTO.getHeaderMapForExcel().get(i).get(NumericConstants.FOUR)).toArray();
                customExcelTableResult
                        .setDoubleHeaderColumnHeaders(Arrays.copyOf(doubleHeader, doubleHeader.length, String[].class));

                customExcelTableResult.setDoubleHeaderMap((Map<Object, Object[]>) projectionDTO.getHeaderMapForExcel().get(i).get(NumericConstants.FIVE));
                customExcelTableResult.setRefresh(true);
                String sheetName = "Year " + String.valueOf(projectionDTO.getHeaderMapForExcel().get(i).get(NumericConstants.TWO));
                ForecastUI.EXCEL_CLOSE = true;
                if (i == 0) {
                    exp = new ExcelExport(new ExtCustomTableHolder(customExcelTableResult), sheetName, Constant.DISCOUNT_PROJECTION_RESULTS, "Discount_Projection_Results.xls", false);
                } else {
                    exp.setNextTableHolder(new ExtCustomTableHolder(customExcelTableResult), sheetName);
                }
                if (i == exportAt) {
                    exp.exportMultipleTabs(true);
                } else {
                    exp.exportMultipleTabs(false);
                }
            }
        } else {
            exp = new ExcelExport(new ExtCustomTableHolder(customExcelTableResult), Constant.DISCOUNT_PROJECTION_RESULTS, Constant.DISCOUNT_PROJECTION_RESULTS, "Discount_Projection_Results.xls", false);
            exp.export();
        }
    }

    @Override
    protected void editHierarchyBtnLogic() {
        return;
    }

    @Override
    protected void customDdlbChangeOptionLogic() {
        return;
    }

    @Override
    protected void graphExportLogics() {
        return;
    }

    @Override
    protected void expandButtonLogic() {
        return;
    }

    @Override
    protected void collapseButtonLogic() {
        return;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        return;
    }

    @Override
    protected void loadCustomDDLB() {
        return;
    }

    public void saveDPR() {
        try {
            LOGGER.debug("saveSPResults method starts");
            Map map = new HashMap();
            map.put(Constant.FREQUENCY_SMALL, frequencyDdlb.getValue() != null ? frequencyDdlb.getValue().toString() : StringUtils.EMPTY);
            map.put(Constant.HISTORY_CAPS, historyDdlb.getValue() != null ? historyDdlb.getValue().toString() : StringUtils.EMPTY);
            map.put("Actuals/Projections", actualOrProjectionsOpg.getValue().toString());
            map.put(Constant.PERIOD_ORDER, periodOrderOpg.getValue().toString());
            map.put("Pivot", pivotViewOpg.getValue().toString());
            CommonLogic.saveProjectionSelection(map, sessionDTO.getProjectionId(), "MM Discount Projection Results");
            LOGGER.debug("saveSPResults method ends");
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }
}
