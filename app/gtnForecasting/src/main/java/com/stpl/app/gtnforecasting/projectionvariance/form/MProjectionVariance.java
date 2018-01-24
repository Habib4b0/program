package com.stpl.app.gtnforecasting.projectionvariance.form;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.gtnforecasting.abstractforecast.ForecastProjectionVariance;
import com.stpl.app.gtnforecasting.dto.ProjectionVarianceDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.logic.Utility;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.WindowMessagesName.RESET_CONFIRMATION;
import com.stpl.app.gtnforecasting.projectionvariance.dto.ComparisonFilterGenerator;
import com.stpl.app.gtnforecasting.projectionvariance.dto.ComparisonLookupDTO;
import com.stpl.app.gtnforecasting.projectionvariance.form.lookup.MComparisonLookup;
import com.stpl.app.gtnforecasting.projectionvariance.logic.MProjectionVarianceLogic;
import com.stpl.app.gtnforecasting.projectionvariance.logic.NMProjectionVarianceLogic;
import com.stpl.app.gtnforecasting.projectionvariance.logic.tablelogic.ProjectionVarianceTableLogic; 
import com.stpl.app.gtnforecasting.projectionvariance.utils.HeaderUtils;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.ui.ForecastUI;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.ANNUAL;
import static com.stpl.app.gtnforecasting.utils.Constant.ANNUALLY;
import static com.stpl.app.gtnforecasting.utils.Constant.CalculatePeriods.CALCULATE;
import static com.stpl.app.gtnforecasting.utils.Constant.FrequencyConstants.MONTHS;
import static com.stpl.app.gtnforecasting.utils.Constant.FrequencyConstants.QUARTERS;
import static com.stpl.app.gtnforecasting.utils.Constant.MONTHLY;
import static com.stpl.app.gtnforecasting.utils.Constant.QUARTERLY;
import static com.stpl.app.gtnforecasting.utils.Constant.SELECT_ONE;
import static com.stpl.app.gtnforecasting.utils.Constant.SEMI_ANNUALLY;
import static com.stpl.app.gtnforecasting.utils.Constant.SPACE;
import static com.stpl.app.gtnforecasting.utils.Constant.YEAR;
import com.stpl.app.gtnforecasting.utils.FunctionNameUtil;
import com.stpl.app.gtnforecasting.utils.PVChart;
import com.stpl.app.gtnforecasting.utils.PVGraphWindow;
import com.stpl.app.gtnforecasting.utils.UISecurityUtil;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.utils.Constants;
import static com.stpl.app.utils.Constants.FrequencyConstants.SEMI_ANNUAL;
import static com.stpl.app.utils.Constants.LabelConstants.*;
import com.stpl.app.utils.UiUtils;
import com.stpl.ifs.ui.extfilteringtable.ExtPagedTreeTable;
import com.stpl.ifs.ui.extfilteringtable.FreezePagedTreeTable;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import static com.stpl.ifs.util.constants.GlobalConstants.*;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import com.vaadin.v7.data.Property.ValueChangeEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.keyvalue.MultiKey;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;
/**
 * The Class ProjectionVariance.
 */
public class MProjectionVariance extends ForecastProjectionVariance {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MProjectionVariance.class);
    /**
     * The excel export image.
     */
    private final Resource excelExportImage = new ThemeResource("img/excel.png");
    /**
     * The graph image.
     */
    private final Resource graphImage = new ThemeResource("img/chart.png");
    /**
     * The from bean.
     */
    /**
     * The to bean.
     */
    /**
     * The table control Layout.
     */
    private  ExtTreeContainer<ProjectionVarianceDTO> beanContainerResult = new ExtTreeContainer<>(ProjectionVarianceDTO.class,ExtContainer.DataStructureMode.MAP);
    private static List<Integer> projectionIdList = new ArrayList<>();
    private Map<Integer, String> projectionMap = new HashMap<>();
    private static List<String> projectionNameList = new ArrayList<>();
    private final MProjectionVarianceLogic pvLogic = new MProjectionVarianceLogic();
    private boolean firstGenerated = false;
    private boolean canLoad = true;
    /**
     * The Session DTO.
     */
    private final SessionDTO session;
    /**
     * The custom id.
     */

    /**
     * The custom id to select.
     */
    private List<Leveldto> currentHierarchy = new ArrayList<>();
    private final List<Leveldto> viewChangeHierarchy = new ArrayList<>();
    private ExtPagedTreeTable leftTable;
    private ExtPagedTreeTable rightTable;
    private final String scrnName;
    private boolean isComparisonLookupOpened;
     /** To reduce unwanted DB hits **/
    private Map<MultiKey,List> contractTypeList=new HashMap<>();

    /**
     * Constructor for ProjectionVariance.
     * @param sessionDTO
     * @param screenName
     */
    public MProjectionVariance(SessionDTO sessionDTO, String screenName) {
        super(sessionDTO, screenName);
        this.scrnName = screenName;
        groupLabel.setVisible(false);
        group.setVisible(false);
        excelTable.setVisible(false);
        LOGGER.debug("ProjectionVariance Constructor initiated ");
        this.session = sessionDTO;
        pvSelectionDTO.setMandatedView(sessionDTO.getAction());
        pvSelectionDTO.setSession(sessionDTO);
        pvSelectionDTO.setSessionDTO(sessionDTO);
        excelTable.setVisible(false);
        LOGGER.debug("ProjectionVariance Constructor ends ");
    }

    /**
     * Configure fields.
     */
    private void configureFields() {

        frequency.addItem(Constant.SELECT_ONE);
        frequency.addItem(ANNUALLY);
        frequency.addItem(SEMI_ANNUALLY);
        frequency.addItem(QUARTERLY);
        frequency.addItem(MONTHLY);
        frequency.setValue(QUARTERLY);
        variables.setVisible(false);
        String[] variableValues = Constant.PVVariables.names();
        customMenuItem = customMenuBar.addItem("-Select Variables-", null);
        CustomMenuBar.CustomMenuItem[] customItem = new CustomMenuBar.CustomMenuItem[variableValues.length];
        for (int i = 0; i < variableValues.length; i++) {
            customItem[i] = customMenuItem.addItem(variableValues[i].trim(), null);
            customItem[i].setCheckable(true);
            customItem[i].setItemClickable(true);

            customItem[i].setItemClickNotClosable(true);
            if (i == 0) {
                customItem[i].setCheckAll(true);
            }
        }
        
        String[] variableCategoryValues = Constant.PVVariableCategory.names();
        variableCategoryCustomMenuItem = variableCategoryCustomMenuBar.addItem("-Select Variables-", null);
        CustomMenuBar.CustomMenuItem[] variableCategoryCustomItem = new CustomMenuBar.CustomMenuItem[variableCategoryValues.length];
        for (int i = 0; i < variableCategoryValues.length; i++) {
            variableCategoryCustomItem[i] = variableCategoryCustomMenuItem.addItem(variableCategoryValues[i].trim(), null);
            variableCategoryCustomItem[i].setCheckable(true);
            variableCategoryCustomItem[i].setItemClickable(true);
            variableCategoryCustomItem[i].setItemClickNotClosable(true);
        }

        discountLevel.addStyleName(Constant.OPTION_GROUP_WIDTH);
        discountLevel.addStyleName(Constant.HORIZONTAL);
        discountLevel.addItem(TOTAL_DISCOUNT.getConstant());
        discountLevel.addItem(Constant.COMPONENT);
        discountLevel.select(TOTAL_DISCOUNT.getConstant());

        excelBtn.setIcon(excelExportImage);
        graphBtn.setIcon(graphImage);
        initialTableLoad();
        if (Constant.EDIT_SMALL.equals(session.getAction()) || Constant.VIEW.equals(session.getAction())) {
            setProjectionSelection();
        }
    }

    public void initialTableLoad() {
        try{
        addResultTable();
        getCheckedValues();
        loadProjectionSelection();
        loadComparison();
        configureBaseVariables();
        configureResultTable();
        }catch(Exception e){
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Load history.
     *
     * @param frequency the frequency
     * @return the list
     */
    protected static final List<String> loadHistory(String frequency) {
        List<String> history = new ArrayList<>();
        int endValue = 0;
        String freq = StringUtils.EMPTY;
        if (ANNUAL.equals(frequency)) {
            endValue = CALCULATE.getAnnualCount();
            freq = YEAR;
        } else if (SEMI_ANNUAL.getConstant().equals(frequency)) {

            endValue = CALCULATE.getSemiAnnualCount();
            freq = SEMI_ANNUAL.getConstant();
        } else if (QUARTERLY.equals(frequency)) {

            endValue = CALCULATE.getQuarterCount();
            freq = QUARTERS.getConstant();
        } else if (MONTHLY.equals(frequency)) {

            endValue = CALCULATE.getMonthCount();
            freq = MONTHS.getConstant();
        }

        for (int i = 1; i <= endValue; i++) {
            if ((i == 1) && (QUARTERS.getConstant().equals(freq) || MONTHS.getConstant().equals(freq))) {
                String period = freq.replace(Constant.S_SMALL, StringUtils.EMPTY);
                history.add(String.valueOf(i) + SPACE + period);
            } else {
                history.add(String.valueOf(i) + SPACE + freq);
            }
        }
        return history;
    }

    /**
     * Load frequency.
     *
     * @param event the event
     */
    @Override
    protected void loadFrequency() {
        LOGGER.debug("ProjectionVariance ValueChangeEvent initiated with frequency -->" + frequency.getValue());
        if (frequency.getValue() != null && !"null".equals(String.valueOf(frequency.getValue())) && !"".equals(String.valueOf(frequency.getValue()))) {
            loadProjectionSelection();
            fullHeader = new CustomTableHeaderDTO();
            HeaderUtils.getPVRightTableColumns(pvSelectionDTO, fullHeader);
            loadFromPeriod(Constant.SELECT_ONE);
            loadToPeriod(Constant.SELECT_ONE);
            toDate.setValue(Constant.SELECT_ONE);
        }

        LOGGER.debug("ProjectionVariance ValueChangeEvent ends ");
    }

    /**
     * Comparison lookup listener.
     *
     * @param event the event
     */
    @Override
    protected void comparisonLookupLogic() {
        LOGGER.debug("Comparision lookup started");
        MComparisonLookup comparisonLookupWindow = new MComparisonLookup(comparison, session, scrnName,getContractTypeList(),pvSelectionDTO,tableLogic);
        UI.getCurrent().addWindow(comparisonLookupWindow);
        isComparisonLookupOpened = true;
        LOGGER.debug("Comparision lookup ends");
    }

    /**
     * Reset btn.
     *
     * @param event the event
     */
    @Override
    protected void resetBtnLogic() {
        new AbstractNotificationUtils() {
            @Override
            public void noMethod() {
                // do nothing
            }

            @Override
            /**
             * The method is triggered when Yes button of the message box is
             * pressed .
             *
             * @param buttonId The buttonId of the pressed button.
             */
            public void yesMethod() {
                frequency.setValue(Constant.QUARTERLY);
                level.select(Constant.TOTAL);
                projectionPeriodOrder.select(Constant.ASCENDING);
                discountLevel.select(TOTAL_DISCOUNT.getConstant());
                pivotView.select(Constant.PERIOD);
                fromDate.setValue(null);
                toDate.setValue(null);
                comparison.setReadOnly(false);
                comparison.setValue(SELECT_ONE);
                comparison.setData(null);
                comparison.setReadOnly(true);
                Object[] variableItemIds = variables.getItemIds().toArray();
                for (Object variableItemId : variableItemIds) {
                    variables.unselect(variableItemId);
                }

                Object[] itemIds = variableCategory.getItemIds().toArray();
                for (Object itemId : itemIds) {
                    variableCategory.unselect(itemId);
                }
            }
        }.getConfirmationMessage(RESET_CONFIRMATION.getConstant(), "Are you sure you want to reset the page to default/previous values?");
    }

    /**
     * View.
     *
     * @param event the event
     */
    @UiHandler("view")
    public void view(ValueChangeEvent event) {

        if (CUSTOM.getConstant().equals(String.valueOf(view.getValue()))) {
            customDdlb.setEnabled(true);
            addViewBtn.setEnabled(true);
        } else {
            customDdlb.setEnabled(false);
            editViewBtn.setEnabled(false);
            addViewBtn.setEnabled(false);
        }
    }

    /**
     * Configure Result Table.
     */
    private void configureResultTable() {

        fullHeader = new CustomTableHeaderDTO();
        leftHeader = HeaderUtils.getLeftTableColumns(fullHeader, view.getValue().toString());
        List<Object> HeaderPropertyIds = HeaderUtils.getPVRightTableColumns(pvSelectionDTO, fullHeader);
        rightHeader = (CustomTableHeaderDTO) HeaderPropertyIds.get(0);
        pvSelectionDTO.setRightHeaderPeriod(rightHeader);
        alignRight();
        setBeanContainerResult(new ExtTreeContainer<>(ProjectionVarianceDTO.class,ExtContainer.DataStructureMode.MAP));
        getBeanContainerResult().setColumnProperties(leftHeader.getProperties());
        getBeanContainerResult().setColumnProperties(rightHeader.getProperties());
        tableLogic.setScreenName(scrnName);
        tableLogic.setContainerDataSource(getBeanContainerResult());
        tableLogic.setTreeNodeMultiClick(false);
        tableLogic.setPageLength(NumericConstants.TWENTY);
        tableLogic.sinkItemPerPageWithPageLength(false);
        List<Integer> pagelength = CommonLogic.getPageNumber();
        tableLogic.getControlConfig().setPageLengthsAndCaptions(pagelength);

        leftTable = resultsTable.getLeftFreezeAsTable();
        rightTable = resultsTable.getRightFreezeAsTable();
        leftTable.setImmediate(true);
        rightTable.setImmediate(true);
        if (projectionPeriodOrder.getValue().toString().equalsIgnoreCase(Constant.DESCENDING) && pivotView.getValue().toString().equalsIgnoreCase(Constant.PERIOD)) {
            Collections.reverse(rightHeader.getDoubleColumns());
            Collections.reverse(rightHeader.getDoubleHeaders());
            Collections.reverse(rightHeader.getSingleColumns());
            Collections.reverse(rightHeader.getSingleHeaders());
        }
        resultsTable.setHeight(Constant.SIX_FIFTY_PX);
        leftTable.setHeight(Constant.SIX_FIFTY_PX);
        rightTable.setHeight(Constant.SIX_FIFTY_PX);
        leftTable.setColumnWidth(Constant.GROUP, NumericConstants.THREE_HUNDRED);
        leftTable.setDoubleHeaderColumnWidth(Constant.GROUP, NumericConstants.THREE_HUNDRED);
        leftTable.setVisibleColumns(leftHeader.getSingleColumns().toArray());
        leftTable.setColumnHeaders(leftHeader.getSingleHeaders().toArray(new String[leftHeader.getSingleHeaders().size()]));
        leftTable.setFilterBarVisible(true);
        leftTable.setFilterDecorator(new ExtDemoFilterDecorator());
        leftTable.setFilterGenerator(new ComparisonFilterGenerator(pvSelectionDTO, tableLogic, Constant.DETAIL.equals(level.getValue()) ? true : false,getContractTypeList()));
        rightTable.setVisibleColumns(rightHeader.getSingleColumns().toArray());
        rightTable.setColumnHeaders(rightHeader.getSingleHeaders().toArray(new String[rightHeader.getSingleHeaders().size()]));
        UiUtils.setExtFilterTreeTableColumnWidth(rightTable, NumericConstants.ONE_SEVEN_ZERO, TAB_PROJECTION_VARIANCE.getConstant());
        resultsTable.getRightFreezeAsTable().setDoubleHeaderVisible(true);
        rightTable.setDoubleHeaderVisibleColumns(rightHeader.getDoubleColumns().toArray());
        rightTable.setDoubleHeaderColumnHeaders(rightHeader.getDoubleHeaders().toArray(new String[rightHeader.getDoubleHeaders().size()]));
        resultsTable.getRightFreezeAsTable().setDoubleHeaderMap(rightHeader.getDoubleHeaderMaps());
        final List<String> programCodeNameList = (List<String>) HeaderPropertyIds.get(4);
        pvSelectionDTO.setProgramCodeNameList(programCodeNameList);
        loadPeriods(rightHeader.getDoubleHeaders());
        if ("Variable".equals(pvSelectionDTO.getPivotView()) && Constant.COMPONENT.equals(pvSelectionDTO.getDiscountLevel())) {
            final List<String> listCollapseIcon1st = (List<String>) HeaderPropertyIds.get(1);
            final List<String> listCollapse2nd = (List<String>) HeaderPropertyIds.get(NumericConstants.TWO);
            final List<String> programCodeListCollapse = (List<String>) HeaderPropertyIds.get(NumericConstants.THREE);
            rightTable.setColumnCollapsingAllowed(true);
            for (int i = 0; i < listCollapseIcon1st.size(); i++) {
                rightTable.setDoubleHeaderColumnExpandIcon(listCollapseIcon1st.get(i), false);
            }

            for (int i = 0; i < listCollapse2nd.size(); i++) {
                if (Constant.DETAIL.equals(pvSelectionDTO.getLevel())) {
                    rightTable.setDoubleHeaderColumnExpandIcon(listCollapse2nd.get(i), false);
                }
                rightTable.setDoubleHeaderColumnCollapsed(listCollapse2nd.get(i), true);
            }

            for (int i = 0; i < programCodeListCollapse.size(); i++) {
                rightTable.setDoubleHeaderColumnCollapsed(programCodeListCollapse.get(i), true);
            }
            rightTable.addDoubleHeaderColumnExpandIconListener(new ExtCustomTable.DoubleHeaderColumnExpandIconListener() {
                private static final long serialVersionUID = -4215343675341144627L;

                @Override
                public void doubleHeaderColumnExpandIcon(ExtCustomTable.DoubleHeaderColumnExpandIconEvent event) {
                    LOGGER.debug("Projection Variance addColumnExpandIconListener initiated ");

                    boolean totalLevelFlag = listCollapseIcon1st.contains(event.getPropertyId());

                    if (totalLevelFlag) {
                        for (int i = 0; i < listCollapseIcon1st.size(); i++) {
                            if (listCollapseIcon1st.get(i).equals(event.getPropertyId())) {
                                int j = 0;
                                for (j = i * NumericConstants.TWO; j < ((i * NumericConstants.TWO) + NumericConstants.TWO); j++) {
                                    rightTable.setDoubleHeaderColumnCollapsed(listCollapse2nd.get(j), !event.isExpanded());
                                }
                                if (Constant.DETAIL.equals(pvSelectionDTO.getLevel())) {
                                    j = i * NumericConstants.TWO;
                                    int temp = programCodeNameList.size();
                                    if (event.isExpanded()) {
                                        int collapseCount = i * temp;
                                        for (int t = j * temp; t < ((j * temp) + temp); t++) {
                                            rightTable.setDoubleHeaderColumnCollapsed(programCodeListCollapse.get(t), true);
                                            rightTable.setDoubleHeaderColumnExpandIcon(listCollapse2nd.get(collapseCount), false);
                                            rightTable.setDoubleHeaderColumnExpandIcon(listCollapse2nd.get(collapseCount + 1), false);
                                        }
                                    } else {
                                        for (int t = j * temp; t < ((j * temp) + (temp * NumericConstants.TWO)); t++) {
                                            rightTable.setDoubleHeaderColumnCollapsed(programCodeListCollapse.get(t), true);
                                        }
                                    }
                                }
                            }
                        }
                    } else if (Constant.DETAIL.equals(pvSelectionDTO.getLevel())) {
                        for (int i = 0; i < listCollapse2nd.size(); i++) {
                            if (listCollapse2nd.get(i).equals(event.getPropertyId())) {
                                int temp = programCodeNameList.size();
                                for (int j = i * temp; j < ((i * temp) + temp); j++) {
                                    rightTable.setDoubleHeaderColumnCollapsed(programCodeListCollapse.get(j), !event.isExpanded());
                                }
                            }
                        }
                    }

                    LOGGER.debug("Projection Variance addColumnExpandIconListener ends ");
                }
            });
        }

        if (fromDate.getValue() != null && toDate.getValue() != null) {
            if (pivotView.getValue().equals(Constant.PERIOD)) {
                pvLogic.getDateRangeHeaders(rightTable, rightHeader, pvSelectionDTO.getFromDate(), pvSelectionDTO.getToDate(), String.valueOf(frequency.getValue()));
            } else {
                List<String> periodList = pvSelectionDTO.getPeriodList();
                List<String> pivotList = new ArrayList<>();
                int start = periodList.indexOf(fromDate.getValue().toString().replace(" ", StringUtils.EMPTY));
                int end = periodList.indexOf(toDate.getValue().toString().replace(" ", StringUtils.EMPTY));
                for (int i = start; i <= end; i++) {
                    pivotList.add(periodList.get(i));
                }
                pvSelectionDTO.setPeriodList(pivotList);
            }
        }
        levelFilter.removeValueChangeListener(levelFilterChangeOption);
        levelFilter.addValueChangeListener(levelFilterChangeOption);
    }

    public void loadProjectionSelection() {

        String freq = String.valueOf(frequency.getValue());
        int historyNum = 0;

        if (freq.equals(QUARTERLY)) {
            historyNum = NumericConstants.TWELVE;
        } else if (freq.equals(SEMI_ANNUALLY)) {
            historyNum = NumericConstants.SIX;

        } else if (freq.equals(MONTHLY)) {
            historyNum = NumericConstants.THIRTY_SIX;

        } else if (freq.equals(ANNUALLY)) {
            historyNum = NumericConstants.THREE;
        }

        pvSelectionDTO.setFrequency(String.valueOf(frequency.getValue()));
        pvSelectionDTO.setUserId(Integer.parseInt(session.getUserId()));
        pvSelectionDTO.setSessionId(Integer.parseInt(session.getSessionId()));
        pvSelectionDTO.setCustomerLevelNo(Integer.valueOf(session.getCustomerLevelNumber()));
        pvSelectionDTO.setProductLevelNo(Integer.valueOf(session.getProductLevelNumber()));
        pvSelectionDTO.setForecastDTO(session.getForecastDTO());
        pvSelectionDTO.setProjectionId(session.getProjectionId());
        pvSelectionDTO.setCurrentProjectionID(session.getProjectionId());
        pvSelectionDTO.setCurrentProjectionName("Current Projection");

        pvSelectionDTO.setLevel(String.valueOf(level.getValue()));
        pvSelectionDTO.setDiscountLevel(String.valueOf(discountLevel.getValue()));
        pvSelectionDTO.setVariableCategory(String.valueOf(variableCategory.getValue()));
        pvSelectionDTO.setVariables(variablesValue);
        pvSelectionDTO.setView(view.getValue().toString());
        if (CUSTOM.getConstant().equalsIgnoreCase(pvSelectionDTO.getView())) {

            if (customId != 0) {
                pvSelectionDTO.setCustomCount(pvLogic.customCount(customId));
            } else {
                pvSelectionDTO.setCustomCount(0);
            }
        }
        pvSelectionDTO.setProjectionPeriodOrder(projectionPeriodOrder.getValue().toString());
        pvSelectionDTO.setPivotView(pivotView.getValue().toString());
        pvSelectionDTO.setHistoryNum(historyNum);
        pvSelectionDTO.setCustomerRelationId(session.getCustomerRelationId());
        pvSelectionDTO.setProductRelationId(session.getProductRelationId());
        pvSelectionDTO.setProjectionNum(CommonUtils.getProjectionNumber(String.valueOf(frequency.getValue()), session));
        viewChange(false);
        pivotPanel.setCaption(pivotView.getValue().toString() + SPACE + PIVOT_VIEW.getConstant());

    }

    @Override
    protected void getGenerateCall(boolean isExcelFlag) {
        getCheckedValues();
        loadProjectionSelection();
        loadComparison();
        configureBaseVariables();
        generated = true;
        firstGenerated = true;

        loadVariables();
        loadProjectionSelection();
        if (isExcelFlag) {
            configureExcelTable();
        } else {
            tableVerticalLayout.removeAllComponents();
            tableLogic = new ProjectionVarianceTableLogic();
            resultsTable = new FreezePagedTreeTable(tableLogic);
            initializeResultTable();
            pvSelectionDTO.setFromDate(String.valueOf(fromDate.getValue()));
            pvSelectionDTO.setToDate(String.valueOf(toDate.getValue()));
            configureResultTable();
            loadFromPeriod(String.valueOf(fromDate.getValue()));
            loadToPeriod(String.valueOf(fromDate.getValue()));
            addResultTable();
            generateLogic();
        }
        editViewBtn.setEnabled(CUSTOM.getConstant().equals(String.valueOf(view.getValue()))
                && (!Constant.NULL.equals(customDdlb.getValue()) && !SELECT_ONE.equals(customDdlb.getValue())));
        generated = false;
    }

    public void generateLogic() {
        pvSelectionDTO.setSession(session);
        pvSelectionDTO.setSessionDTO(session);
        pvSelectionDTO.setMandatedView(session.getAction());
        setCurrentHierarchy(new ArrayList<>(viewChangeHierarchy));
        levelFilter.removeValueChangeListener(levelFilterChangeOption);
        loadLevelAndFilterValue();
        tableLogic.clearAll();
        loadVariables();
        tableLogic.setRefresh(false);
        pvSelectionDTO.setIslevelFiler(false);
        loadResultTable(0, StringUtils.EMPTY);
        tableLogic.setRefresh(true);
        levelFilter.addValueChangeListener(levelFilterChangeOption);
    }

    public void configureBaseVariables() {

        String columns = pvSelectionDTO.getVariableCategory();
        String tempVariables = pvSelectionDTO.getVariables();

        LOGGER.debug("Entering Configure Base Variables method");
        pvSelectionDTO.setColValue(false);
        pvSelectionDTO.setColVariance(false);
        pvSelectionDTO.setColPercentage(false);
        pvSelectionDTO.setVarGTS(false);
        pvSelectionDTO.setVarExFacSales(false);
        pvSelectionDTO.setVarDemandSales(false);
        pvSelectionDTO.setVarInvSales(false);
        pvSelectionDTO.setVarPerExFacSales(false);
        pvSelectionDTO.setVarPerDemandSales(false);
        pvSelectionDTO.setVarPerInvSales(false);
        pvSelectionDTO.setVarContractsales(false);
        pvSelectionDTO.setVarContractUnits(false);
        pvSelectionDTO.setVarDisAmount(false);
        pvSelectionDTO.setVarDisRate(false);
        pvSelectionDTO.setVarNetSales(false);
        pvSelectionDTO.setVarCOGC(false);
        pvSelectionDTO.setVarRPU(false);
        pvSelectionDTO.setVarNetProfit(false);

        columns = columns.substring(1, columns.length() - 1);

        final String[] col = columns.split(",");

        for (String value : col) {

            value = StringUtils.trim(value);
            if (value.equalsIgnoreCase(StringUtils.trim(Constants.LabelConstants.VALUE.getConstant()))) {
                pvSelectionDTO.setColValue(true);

            } else if (value.equalsIgnoreCase(StringUtils.trim(Constants.LabelConstants.VARIANCE.getConstant()))) {
                pvSelectionDTO.setColVariance(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constants.LabelConstants.PERC_CHANGE.getConstant()))) {
                pvSelectionDTO.setColPercentage(true);
            }
        }
        if (StringUtils.isNotBlank(tempVariables)) {
            tempVariables = tempVariables.substring(1, tempVariables.length() - 1);
            final String[] var = tempVariables.split(",");
            for (String value : var) {

                value = StringUtils.trim(value);
                if (value.equalsIgnoreCase(StringUtils.trim(Constant.PVVariables.EX_FACTORY_SALES.toString()))) {
                    pvSelectionDTO.setVarExFacSales(true);
                } else if (value.equalsIgnoreCase(StringUtils.trim(Constant.PVVariables.DEMAND_SALES.toString()))) {
                    pvSelectionDTO.setVarDemandSales(true);
                } else if (value.equalsIgnoreCase(StringUtils.trim(Constant.PVVariables.INVENTORY_SALES.toString()))) {
                    pvSelectionDTO.setVarInvSales(true);
                } else if (value.equalsIgnoreCase(StringUtils.trim(Constant.PVVariables.PER_EX_FACTORY.toString()))) {
                    pvSelectionDTO.setVarPerExFacSales(true);
                } else if (value.equalsIgnoreCase(StringUtils.trim(Constant.PVVariables.PER_DEMAND.toString()))) {
                    pvSelectionDTO.setVarPerDemandSales(true);
                } else if (value.equalsIgnoreCase(StringUtils.trim(Constant.PVVariables.PER_INVENORY_WITHDRAW.toString()))) {
                    pvSelectionDTO.setVarPerInvSales(true);
                } else if (value.equalsIgnoreCase(StringUtils.trim(Constant.PVVariables.VAR_CONTRACT_SALES.toString()))) {
                    pvSelectionDTO.setVarContractsales(true);
                } else if (value.equalsIgnoreCase(StringUtils.trim(Constant.PVVariables.VAR_CONTRACT_UNITS.toString()))) {
                    pvSelectionDTO.setVarContractUnits(true);
                } else if (value.equalsIgnoreCase(StringUtils.trim(Constant.PVVariables.VAR_DIS_AMOUNT.toString()))) {
                    pvSelectionDTO.setVarDisAmount(true);
                } else if (value.equalsIgnoreCase(StringUtils.trim(Constant.PVVariables.VAR_DIS_RATE.toString()))) {
                    pvSelectionDTO.setVarDisRate(true);
                } else if (value.equalsIgnoreCase(StringUtils.trim(Constant.PVVariables.VAR_RPU.toString()))) {
                    pvSelectionDTO.setVarRPU(true);
                } else if (value.equalsIgnoreCase(StringUtils.trim(Constant.PVVariables.VAR_NETSALES.toString()))) {
                    pvSelectionDTO.setVarNetSales(true);
                } else if (value.equalsIgnoreCase(StringUtils.trim(Constant.PVVariables.VAR_COGS.toString()))) {
                    pvSelectionDTO.setVarCOGC(true);
                } else if (value.equalsIgnoreCase(StringUtils.trim(Constant.PVVariables.VAR_NET_PROFITE.toString()))) {
                    pvSelectionDTO.setVarNetProfit(true);
                }
            }
        }

        LOGGER.debug("End of Configure Base Variables method");

    }

    private void loadComparison() {
        if (isComparisonLookupOpened) {
            ComparisonLookupDTO comparisonLookup = (ComparisonLookupDTO) comparison.getData();
            projectionMap = new HashMap<>();
            projectionNameList = new ArrayList<>();
            projectionIdList = new ArrayList<>();
            if (comparisonLookup != null) {
                if (!comparisonLookup.getSelected().isEmpty()) {
                    for (ComparisonLookupDTO object : comparisonLookup.getSelected()) {
                        projectionNameList.add(object.getProjectionName());
                        projectionMap.put(Integer.valueOf(object.getProjectionId()), object.getProjectionName());
                        projectionIdList.add(Integer.valueOf(object.getProjectionId()));
                    }
                } else if (!comparisonLookup.getProjectionMap().isEmpty()) {
                    for (Map.Entry<Integer, String> entry : comparisonLookup.getProjectionMap().entrySet()) {
                        Integer projId = entry.getKey();
                        String projName = entry.getValue();
                        projectionMap.put(projId, projName);
                        projectionIdList.add(projId);

                    }
                }
            }
            pvSelectionDTO.setProjIdList(projectionIdList);
            pvSelectionDTO.setProjectionMap(projectionMap);
        }
    }

    private void loadPeriods(List<String> doubleColumns) {
        String fromDateValue = fromDate.getValue() != null ? fromDate.getValue().toString() : StringUtils.EMPTY;
        String toDateValue = toDate.getValue() != null ? toDate.getValue().toString() : StringUtils.EMPTY;

        fromDate.removeAllItems();
        toDate.removeAllItems();
        fromDate.addItem(SELECT_ONE);
        toDate.addItem(SELECT_ONE);
        if (String.valueOf(pivotView.getValue()).equalsIgnoreCase(Constant.PERIOD)) {
            if (!doubleColumns.isEmpty()) {
                for (int i = 1; i < doubleColumns.size(); i++) {
                    fromDate.addItem(doubleColumns.get(i));
                    toDate.addItem(doubleColumns.get(i));
                }
            }
        } else if (pvSelectionDTO.getPivotList() != null && !pvSelectionDTO.getPivotList().isEmpty()) {
            for (int i = 1; i < pvSelectionDTO.getPivotList().size(); i++) {
                fromDate.addItem(pvSelectionDTO.getPivotList().get(i));
                toDate.addItem(pvSelectionDTO.getPivotList().get(i));
            }
        }

        fromDate.select(fromDateValue);
        toDate.select(toDateValue);
    }

    public void loadAllLevelValue() {
        LOGGER.debug("loadAllLevelValue initiated ");
        levelDdlb.setEnabled(true);
        levelDdlb.removeAllItems();
        levelDdlb.addItem(SELECT_ONE);
        levelDdlb.setNullSelectionItemId(SELECT_ONE);
        levelDdlb.setValue(SELECT_ONE);
        if (pvSelectionDTO.isIsCustomHierarchy()) {
            levelDdlb.setEnabled(false);
        } else {
            List<com.stpl.ifs.ui.forecastds.dto.Leveldto> newLevelList = null;
            if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(pvSelectionDTO.getHierarchyIndicator())) {
                newLevelList = CommonLogic.getAllHierarchyLevels(pvSelectionDTO.getCustomerLevelNo(), session.getProjectionId(), Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY,session.getAction());
            } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(pvSelectionDTO.getHierarchyIndicator())) {
                newLevelList = CommonLogic.getAllHierarchyLevels(pvSelectionDTO.getProductLevelNo(), session.getProjectionId(), Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY,session.getAction());
            }
            if (newLevelList != null) {
                for (com.stpl.ifs.ui.forecastds.dto.Leveldto levelDto : newLevelList) {
                    String levelFiterSid = levelDto.getLevelNo() + "~" + levelDto.getHierarchyNo();
                    String caption = levelDto.getRelationshipLevelName();
                    Object itemId = levelFiterSid;
                    levelDdlb.addItem(itemId);
                    levelDdlb.setItemCaption(itemId, caption);
                }
            }
        }

        LOGGER.debug("loadAllLevelValue ends ");
    }

    /**
     * Loads the results.
     */
    @SuppressWarnings("serial")
    private void loadResultTable(int levelNo, String hierarchyNo) {
        pvSelectionDTO.setFilterLevelNo(levelNo);
        pvSelectionDTO.setFilterHierarchyNo(hierarchyNo);
        pvSelectionDTO.setProductHierarchyNo(StringUtils.EMPTY);
        pvSelectionDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
        pvSelectionDTO.setIsCustomerDdlb(false);
        pvSelectionDTO.setCustomerRelationId(session.getCustomerRelationId());
        pvSelectionDTO.setProductRelationId(session.getProductRelationId());
        pvSelectionDTO.setCustRelationshipBuilderSid(String.valueOf(session.getCustomerRelationId()));
        pvSelectionDTO.setProdRelationshipBuilderSid(String.valueOf(session.getProductRelationId()));
        pvSelectionDTO.setView(view.getValue().toString());
        pvSelectionDTO.clearNonFetchableIndex();
        tableLogic.setProjectionResultsData(currentHierarchy, pvSelectionDTO, levelNo);
        leftTable.setFilterBarVisible(true);
        leftTable.setFilterDecorator(new ExtDemoFilterDecorator());
        leftTable.setFilterGenerator(new ComparisonFilterGenerator(pvSelectionDTO, tableLogic, Constant.DETAIL.equals(level.getValue()) ? true : false,getContractTypeList()));
    }

    public List<Leveldto> getCurrentHierarchy() {
        return currentHierarchy == null ? currentHierarchy : new ArrayList<>(currentHierarchy);
    }

    public void setCurrentHierarchy(List<Leveldto> currentHierarchy) {
        this.currentHierarchy = currentHierarchy == null ? currentHierarchy : new ArrayList<>(currentHierarchy);
    }

    public void loadVariables() {

        configureBaseVariables();
    }

    @Override
    protected void customDdlbChangeOption() {
        LOGGER.debug("customDdlbChangeOption ValueChangeEvent initiated ");
        customId = CommonLogic.customDdlbOptionChange(customDdlb, editViewBtn);
        pvSelectionDTO.setCustomId(customId);

        if (customId != 0) {
            session.setCustomId(customId);
            Utility.loadCustomHierarchyList(session);
            if (Constant.TOTAL.equalsIgnoreCase(pvSelectionDTO.getLevel())) {
                levelDdlb.setEnabled(false);
                expandLvlBtn.setEnabled(false);
                collapseLvlBtn.setEnabled(false);
            } else {
                levelDdlb.setEnabled(true);
                expandLvlBtn.setEnabled(true);
                collapseLvlBtn.setEnabled(true);
            }

            pvSelectionDTO.setCustomCount(pvLogic.customCount(customId));
        } else {
            levelDdlb.setEnabled(false);
            expandLvlBtn.setEnabled(false);
            collapseLvlBtn.setEnabled(false);
            pvSelectionDTO.setCustomCount(0);
        }
        levelFilter.setEnabled(false);

        if (!generated && firstGenerated) {
            generateLogic();
        }
        LOGGER.debug("customDdlbChangeOption ValueChangeEvent ends with customId=" + customId);
    }

    @Override
    protected void viewChange(boolean viewChange) {
        pvSelectionDTO.setIsCustomHierarchy(false);
        customDdlb.setEnabled(false);
        editViewBtn.setEnabled(false);
        addViewBtn.setEnabled(false);
        if (view.getValue() != null) {
            if (CUSTOM.getConstant().equals(String.valueOf(view.getValue()))) {
                pvSelectionDTO.setHierarchyIndicator(StringUtils.EMPTY);
                pvSelectionDTO.setIsCustomHierarchy(true);
                if (firstGenerated && !generated) {
                    tableLogic.clearAll();
                    tableLogic.getControlTable().getContainerDataSource().removeAllItems();
                }
                loadCustomDDLB();
                levelFilter.setEnabled(false);
                leftTable.setFilterBarVisible(true);
                leftTable.setFilterDecorator(new ExtDemoFilterDecorator());
                leftTable.setFilterGenerator(new ComparisonFilterGenerator(pvSelectionDTO, tableLogic, false,getContractTypeList()));
            } else {
                customIdToSelect = customId;
                setDisableFields();
                pvSelectionDTO.setIsCustomHierarchy(false);
                if (CUSTOMER.getConstant().equals(String.valueOf(view.getValue()))) {
                    pvSelectionDTO.setHierarchyIndicator(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
                    if (viewChange && firstGenerated) {
                        generateLogic();
                        leftTable.setColumnHeader(Constant.GROUP, CUSTOMER.getConstant());
                    }
                } else if (PRODUCT.getConstant().equals(String.valueOf(view.getValue()))) {
                    pvSelectionDTO.setHierarchyIndicator(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
                    if (viewChange && firstGenerated) {
                        generateLogic();
                        leftTable.setColumnHeader(Constant.GROUP, PRODUCT.getConstant());
                    }
                }
            }
        }
    }

    @Override
    protected void expandCollapseLevelOption(boolean isExpand, Object value) {

        leftTable.setFilterBarVisible(true);
        leftTable.setFilterDecorator(new ExtDemoFilterDecorator());
        leftTable.setFilterGenerator(new ComparisonFilterGenerator(pvSelectionDTO, tableLogic, false,getContractTypeList()));

        List<Object> levelHierarchy = CommonLogic.getLevelNoAndHierarchyNo(value);
        int levelNo = Integer.valueOf(String.valueOf(levelHierarchy.get(0)));
        if (levelNo > 0) {
            if (pvSelectionDTO.isIslevelFiler()) {
                levelFilter.removeValueChangeListener(levelFilterChangeOption);
                levelFilter.setValue(SELECT_ONE);
                pvSelectionDTO.setIslevelFiler(false);
                tableLogic.clearAll();
                levelFilter.addValueChangeListener(levelFilterChangeOption);
            } else {
                tableLogic.clearAllExceptCurrentPage();
            }
            String hierarchyIndicator = String.valueOf(levelHierarchy.get(1));
            pvSelectionDTO.setHierarchyIndicator(hierarchyIndicator);
            if (!isExpand) {
                levelNo--;
            }
            tableLogic.loadExpandData(levelNo);
        }

    }

    /**
     * Excel button logic.
     *
     * @param event the event
     */
    @Override
    protected void excelBtnLogic() {
        try {
            configureExcelTable();
            loadExcelResultTable();
            ForecastUI.setEXCEL_CLOSE(true);
            ExcelExport exp = new ExcelExport(new ExtCustomTableHolder(excelTable), TAB_PROJECTION_VARIANCE.getConstant(), TAB_PROJECTION_VARIANCE.getConstant(), "Projection_Variance.xls", false);
            exp.export();
            tableVerticalLayout.removeComponent(excelTable);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }

    }

    /**
     * Loads the Excel Results.
     */
    @SuppressWarnings("serial")
    private void loadExcelResultTable() {
        resultExcelContainer.removeAllItems();
        int count1 = pvLogic.getConfiguredProjectionVarianceCount(new Object(), pvSelectionDTO, true);
        List<ProjectionVarianceDTO> resultList1 = pvLogic.getConfiguredProjectionVariance(new Object(), pvSelectionDTO, 0, count1);
        loadDataToContainer(resultList1, null);
    }

    public void loadDataToContainer(List<ProjectionVarianceDTO> resultList, Object parentId) {
        for (ProjectionVarianceDTO dto : resultList) {
            resultExcelContainer.addBean(dto);
            if (parentId != null) {
                resultExcelContainer.setParent(dto, parentId);
            }
            if (dto.getParent() == 1) {
                resultExcelContainer.setChildrenAllowed(dto, true);
                addLowerLevelsForExport(dto);
            } else {
                resultExcelContainer.setChildrenAllowed(dto, false);
            }
        }
    }

    public void addLowerLevelsForExport(Object id) {
        pvSelectionDTO.setFilterLevelNo(0);
        pvSelectionDTO.setFilterHierarchyNo(StringUtils.EMPTY);
        pvSelectionDTO.setProductHierarchyNo(StringUtils.EMPTY);
        pvSelectionDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
        int count = pvLogic.getConfiguredProjectionVarianceCount(id, pvSelectionDTO, true);

        List<ProjectionVarianceDTO> resultList = pvLogic.getConfiguredProjectionVariance(id, pvSelectionDTO, 0, count);
        loadDataToContainer(resultList, id);
    }

    /**
     * Graph button click listener.
     *
     * @param event the event
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    @Override
    protected void graphBtnLogic() {
        List chartList = new ArrayList();

        for (Object obj : resultsTable.getRightFreezeAsTable().getContainerDataSource().getItemIds()) {
            ProjectionVarianceDTO dto = (ProjectionVarianceDTO) obj;
            if (dto.getLevelNo() != null && dto.getParent() != null && dto.getParent() == 0) {
                chartList.add(dto);
            }
        }
        final PVChart chart = new PVChart(chartList, String.valueOf(frequency.getValue()), StringUtils.EMPTY, fullHeader, pvSelectionDTO);
        final PVGraphWindow pvGraphWindow = new PVGraphWindow(chart.getChart(), "Projection Variance");
        UI.getCurrent().addWindow(pvGraphWindow);
        pvGraphWindow.focus();
    }

    private void alignRight() {
        for (Object obj : rightHeader.getSingleColumns()) {
            resultsTable.getRightFreezeAsTable().setColumnAlignment(obj, ExtCustomTable.Align.RIGHT);
        }
    }

    /**
     * Disable for total
     */
    @Override
    protected void setDisableFields() {
        if (Constant.TOTAL.equalsIgnoreCase(pvSelectionDTO.getLevel())) {
            levelDdlb.setEnabled(false);
            expandLvlBtn.setEnabled(false);
            collapseLvlBtn.setEnabled(false);
            levelFilter.setEnabled(false);
            view.setEnabled(false);
        } else {
            levelDdlb.setEnabled(true);
            expandLvlBtn.setEnabled(true);
            collapseLvlBtn.setEnabled(true);
            if (!pvSelectionDTO.isIsCustomHierarchy()) {
                levelFilter.setEnabled(true);
            } else {
                levelFilter.setEnabled(false);
            }
            view.setEnabled(true);
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        return;
    }

    @Override
    public void callTableLogic(int levelNo, String hierarchyNo, final boolean excelFlag) {
        tableLogic.clearAll();
        tableLogic.setRefresh(false);
        loadResultTable(levelNo, hierarchyNo);
        tableLogic.setRefresh(true);
    }

    public void savePvSelections() {

        LOGGER.debug("savePVSelections method starts");
        Map map = new HashMap();
        String priorProjectionIds = projectionIdList == null || projectionIdList.isEmpty() ? StringUtils.EMPTY : projectionIdList.toString();
        map.put("Comparison", priorProjectionIds);
        map.put("Level", level.getValue().toString());
        map.put("Projection Period Order", projectionPeriodOrder.getValue().toString());
        map.put("From", fromDate.getValue() != null ? fromDate.getValue().toString() : StringUtils.EMPTY);
        map.put("To", toDate.getValue() != null ? toDate.getValue().toString() : StringUtils.EMPTY);
        map.put(Constant.FREQUENCY_SMALL, frequency.getValue() != null ? frequency.getValue().toString() : StringUtils.EMPTY);
        map.put("Discount Level", discountLevel.getValue() != null ? discountLevel.getValue().toString() : StringUtils.EMPTY);
        map.put("Pivot View", pivotView.getValue() != null ? pivotView.getValue().toString() : StringUtils.EMPTY );
        map.put("Variable Category",variableCategory.getValue() != null ? variableCategory.getValue().toString() : StringUtils.EMPTY);
        map.put(Constant.VARIABLES, getCheckedValues());
        pvLogic.saveMPVSelection(map, session.getProjectionId(), TAB_PROJECTION_VARIANCE.getConstant());
        LOGGER.debug("savePVSelections method ends");

    }

    public void setProjectionSelection() {
        Map<Object, Object> map = CommonLogic.getMProjectionSelection(session.getProjectionId(), TAB_PROJECTION_VARIANCE.getConstant());
        if (map != null && !map.isEmpty()) {

            Object value = map.get(Constant.FREQUENCY_SMALL);
            if (value != null) {
                frequency.setValue(String.valueOf(value));
            }
            value = map.get("Comparison");
            if (value != null && StringUtils.isNotBlank(value.toString()) && !Constant.NULL.equals(value.toString())) {
                comparison.setReadOnly(false);
                loadComparisonOnEdit(String.valueOf(value));
                comparison.setReadOnly(true);
            }

            value = map.get("Level");
            if (value != null) {
                level.setValue(String.valueOf(value));
            }
            value = map.get("Discount Level");
            if (value != null) {
                discountLevel.setValue(String.valueOf(value));
            }
            value = map.get("Projection Period Order");
            if (value != null) {
                projectionPeriodOrder.setValue(String.valueOf(value));
            }
            value = map.get("Pivot View");
            if (value != null) {
                pivotView.setValue(String.valueOf(value));
            }
            value = map.get("From");
            if (value != null && StringUtils.isBlank(String.valueOf(value))) {
                fromDate.setValue(String.valueOf(value));
            }
            value = map.get("To");
            if (value != null && StringUtils.isBlank(String.valueOf(value))) {
                toDate.setValue(String.valueOf(value));
            }
            value = map.get("Variable Category");
            if (value != null) {
                String val = value.toString();
                val = val.substring(1, val.length() - 1);
                final String[] col = val.split(",");
                for (int i = 0; i < col.length; i++) {
                    if (i == Constant.ZERO) {
                        variableCategory.select(String.valueOf(col[i]));
                    } else {
                        variableCategory.select(String.valueOf(col[i]).substring(1, col[i].length()));
                    }
                }
            }

            value = map.get(Constant.VARIABLES);
            if (value != null) {
                String val = value.toString();
                val = val.substring(1, val.length() - 1);
                final String[] col = val.split(",");
                for (int i = 0; i < col.length; i++) {
                    if (i == Constant.ZERO) {
                        for (CustomMenuBar.CustomMenuItem string : customMenuItem.getChildren()) {
                            if (string.getText().equals(String.valueOf(col[i]).trim())) {
                                string.setChecked(true);
                            }
                        }
                    } else {
                        for (CustomMenuBar.CustomMenuItem string : customMenuItem.getChildren()) {
                            if (string.getText().equals(String.valueOf(col[i]).substring(1, col[i].length()).trim())) {
                                string.setChecked(true);
                            }
                        }
                    }
                }
            }

        }
    }

    /**
     *
     *
     * @param moduleAndTabName
     * @throws Exception
     */
    public void setButtonSecurity() {
        try {
            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constant.USER_ID));
            final Map<String, AppPermission> functionPsHM = stplSecurity.getBusinessFunctionPermission(userId, getGovernmentConstant() + "," + UISecurityUtil.PROJECTION_VARIANCE);
            if (functionPsHM.get(FunctionNameUtil.GENERATE) != null && !((AppPermission) functionPsHM.get(FunctionNameUtil.GENERATE)).isFunctionFlag()) {
                generateBtn.setVisible(Boolean.FALSE);
                expandLvlBtn.setVisible(Boolean.FALSE);
                collapseLvlBtn.setVisible(Boolean.FALSE);
                addViewBtn.setVisible(Boolean.FALSE);
                editViewBtn.setVisible(Boolean.FALSE);
            }
        } catch (com.liferay.portal.kernel.exception.PortalException ex) {
            LoggerFactory.getLogger(MProjectionVariance.class.getName()).error( StringUtils.EMPTY, ex);
        } catch (SystemException ex) {
            LoggerFactory.getLogger(MProjectionVariance.class.getName()).error( StringUtils.EMPTY, ex);
        }
    }

    private void loadComparisonOnEdit(String projectionIds) {
        NMProjectionVarianceLogic logic = new NMProjectionVarianceLogic();
        List<List> resultList = logic.getComparisonProjections(projectionIds);
        if (resultList != null && !resultList.isEmpty()) {
            projectionIdList = resultList.get(0);
            projectionNameList = resultList.get(1);
            projectionMap = new HashMap<>();
            for (int i = 0; i < projectionIdList.size(); i++) {
                projectionMap.put(projectionIdList.get(i), projectionNameList.get(i));
            }
            comparison.setValue(projectionNameList.size() > 1 ? Constant.MULTIPLE : projectionNameList.get(0));
            pvSelectionDTO.setProjIdList(projectionIdList);
            pvSelectionDTO.setProjectionMap(projectionMap);
        }
    }

    public void configureScreen() {
        if (canLoad) {
            configureFields();
            setButtonSecurity();
            canLoad = false;
        }
    }

	public ExtTreeContainer<ProjectionVarianceDTO> getBeanContainerResult() {
		return beanContainerResult;
	}

	public void setBeanContainerResult(ExtTreeContainer<ProjectionVarianceDTO> beanContainerResult) {
		this.beanContainerResult = beanContainerResult;
	}

	public Map<MultiKey,List> getContractTypeList() {
		return contractTypeList;
	}

	public void setContractTypeList(Map<MultiKey,List> contractTypeList) {
		this.contractTypeList = contractTypeList;
	}

}
