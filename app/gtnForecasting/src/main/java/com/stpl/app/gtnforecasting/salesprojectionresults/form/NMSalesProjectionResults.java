/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.salesprojectionresults.form;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.gtnforecasting.abstractforecast.AbstractForm;
import com.stpl.app.gtnforecasting.abstractforecast.ForecastSalesProjectionResults;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.dto.SalesProjectionResultsDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.logic.Utility;
import com.stpl.app.gtnforecasting.salesprojectionresults.logic.NMSalesProjectionResultsLogic;
import com.stpl.app.gtnforecasting.salesprojectionresults.logic.SPRCommonLogic;
import com.stpl.app.gtnforecasting.salesprojectionresults.logic.tablelogic.NMSalesProjectionResultsTableLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.ui.ForecastUI;
import com.stpl.app.gtnforecasting.ui.form.lookups.CustomTreeBuild;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.HeaderUtils;
import com.stpl.app.gtnforecasting.utils.NmSPRGraphWindow;
import com.stpl.app.gtnforecasting.utils.SPRChart;
import com.stpl.app.gtnforecasting.utils.UISecurityUtil;
import com.stpl.app.model.CustomViewMaster;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.utils.Constants;
import static com.stpl.app.utils.Constants.CalculatePeriods.CALCULATE;
import static com.stpl.app.utils.Constants.CommonConstants.*;
import static com.stpl.app.utils.Constants.FrequencyConstants.*;
import static com.stpl.app.utils.Constants.LabelConstants.TAB_SALES_PROJECTION_RESULTS;
import static com.stpl.app.utils.Constants.ResourceConstants.*;
import com.stpl.ifs.ui.extfilteringtable.FreezePagedTreeTable;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.HorizontalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import org.asi.ui.extfilteringtable.ExtCustomTreeTable;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class SalesProjectionResults.
 *
 * @author maheshj
 */
public class NMSalesProjectionResults extends ForecastSalesProjectionResults {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(NMSalesProjectionResults.class);

    private final List<Object> possibleKeyList = new ArrayList<>();
    private boolean sales;
    private boolean units;

    /**
     * The excel export image.
     */
    private final Resource excelExportImage = new ThemeResource(
            EXCEL_IMAGE_PATH.getConstant());
    /**
     * The graph image.
     */
    private final Resource graphImage = new ThemeResource(
            GRAPH_IMAGE_PATH.getConstant());
    /* The bean for loading history drop down */
    /**
     * The history bean.
     */
    private final BeanItemContainer<String> historyBean = new BeanItemContainer<>(
            String.class);
    /**
     * The map left visible columns.
     */
    private Map<Object, Object[]> mapLeftVisibleColumns = new HashMap<>();
    /**
     * The map right visible columns.
     */
    private Map<Object, Object[]> mapRightVisibleColumns = new HashMap<>();
    private ExtFilterTreeTable leftTable;
    private ExtFilterTreeTable rightTable;
    private final SessionDTO session;
    private List<Object> headerList;
    private List<CustomViewMaster> customViewList = new ArrayList<>();
    private int customIdToSelect = 0;
    private ExtCustomTreeTable exportPeriodViewTable;
    public static final String SALES_RESULTS = Constant.SALES_PROJECTION_RESULTS;
    private int customId = 0;
    private List<Date> startAndTodate;

    private final ProjectionSelectionDTO projectionDTO = new ProjectionSelectionDTO();
    private CustomTableHeaderDTO fullHeader = new CustomTableHeaderDTO();
    private boolean firstGenerated = false;
    private boolean generated = false;
    private int tradingPartnerNo = 0;
    private final Property.ValueChangeListener levelFilterChangeOption = new Property.ValueChangeListener() {

        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            levelFilterDdlbChangeOption(false);
        }
    };
    /**
     * The projection id.
     */
    private int projectionId;
    /**
     * The max split position.
     */
    private final float maxSplitPosition = 1000;

    /**
     * The min split position.
     */
    private final float minSplitPosition = 200;

    /**
     * The split position.
     */
    private final float splitPosition = 300;
    /**
     * The table control Layout.
     */
    protected HorizontalLayout controlLayout;

    protected String screenName = StringUtils.EMPTY;

    /**
     * The period table id.
     */
    /**
     * The result bean.
     */
    private ExtTreeContainer<SalesProjectionResultsDTO> resultBean = new ExtTreeContainer<>(
            SalesProjectionResultsDTO.class, ExtContainer.DataStructureMode.MAP);
    private ExtTreeContainer<SalesProjectionResultsDTO> excelResultBean = new ExtTreeContainer<>(
            SalesProjectionResultsDTO.class, ExtContainer.DataStructureMode.MAP);
    protected CustomTableHeaderDTO rightDTO;
    protected NMSalesProjectionResultsTableLogic tableLogic = new NMSalesProjectionResultsTableLogic();
    private final SPRCommonLogic sprCommonLogic = new SPRCommonLogic();
    protected FreezePagedTreeTable resultsTable = new FreezePagedTreeTable(tableLogic);
    protected boolean flag = false;
    protected boolean isTabVisible = true;

    /**
     * Instantiates a new SALES_SMALL projection results.
     */
    public NMSalesProjectionResults(SessionDTO session, String screenName) {
        super();
        LOGGER.info("SalesProjectionResults Constructor initiated ");
        this.session = session;
        this.screenName = screenName;
        projectionDTO.setTabName(Constant.SALES_PROJECTION_RESULTS);
        if (flag) {
            configure();
        }
        flag = true;
        LOGGER.info("SalesProjectionResults Constructor ends ");
    }

    /**
     * Configure fields.
     */
    private void configureFields() {
        LOGGER.info("configureFields method starts");
        frequency.addItem(SELECT_ONE.getConstant());
        frequency.setNullSelectionItemId(SELECT_ONE.getConstant());
        frequency.addItem(Constant.ANNUALLY);
        frequency.addItem(Constant.SEMI_ANNUALLY);
        frequency.addItem(Constant.QUARTERLY);
        frequency.addItem(Constant.MONTHLY);
        frequency.setValue(Constant.QUARTERLY);
        frequency.focus();

        history.addItem(Constant.FOUR_QUARTERS);
        history.setValue(Constant.FOUR_QUARTERS);

        salesOrUnits.addItem(Constant.SALES_SMALL);
        salesOrUnits.addItem(Constant.UNITS_SMALL);
        salesOrUnits.addItem(Constant.BOTH);
        salesOrUnits.setValue(Constant.SALES_SMALL);

        periodOrder.addItem(Constant.ASCENDING);
        periodOrder.addItem(Constant.DESCENDING);
        periodOrder.setValue(Constant.ASCENDING);

        actualOrProj.addItem(Constant.ACTUALS);
        actualOrProj.addItem(Constant.PROJECTIONS);
        actualOrProj.addItem(Constant.BOTH);
        actualOrProj.setValue(Constant.ACTUALS);

        pivotView.addItem(Constant.PERIOD);
        pivotView.addItem("Variable");
        pivotView.setValue(Constant.PERIOD);

        excelBtn.setIcon(excelExportImage);
        graphBtn.setIcon(graphImage);

        view.addItem(Constant.CUSTOMER_SMALL);
        view.addItem(Constant.PRODUCT_LABEL);
        view.addItem(Constant.CUSTOM_LABEL);
        view.setValue(Constant.CUSTOMER_SMALL);
        view.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                viewChange(true);
            }
        });

        level.addItem(SELECT_ONE.getConstant());
        level.setNullSelectionItemId(SELECT_ONE.getConstant());
        level.setValue(SELECT_ONE.getConstant());
        levelFilter.addItem(SELECT_ONE.getConstant());
        levelFilter.setNullSelectionItemId(SELECT_ONE.getConstant());
        levelFilter.setValue(SELECT_ONE.getConstant());

        customDdlb.addItem(SELECT_ONE.getConstant());
        customDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
        customDdlb.setValue(SELECT_ONE.getConstant());
        customDdlb.setEnabled(false);

        excelBtn.setIcon(excelExportImage);
        graphBtn.setIcon(graphImage);

        editBtn.setEnabled(false);
        panelpivot.setCaption("Period Pivot View");
        initializeResultTable();
        if (loadProjectionSelection()) {
            configureTable();
        }
        addResultTable();
        configureTabOrder();
        LOGGER.info("configureFields methos ends");
    }

    /**
     * Load history.
     *
     * @param frequency the frequency
     * @return the list
     */
    protected final List<String> loadHistory(String frequency, String period) {
        LOGGER.info("loadHistory method starts");
        List<String> history = new ArrayList<>();
        int endValue = 0;
        String freq = StringUtils.EMPTY;
        if (ANNUALLY.getConstant().equals(frequency)) {
            endValue = CALCULATE.getAnnualCount();
            freq = YEARS.getConstant();
        } else if (SEMI_ANNUALLY.getConstant().equals(frequency)) {

            endValue = CALCULATE.getSemiAnnualCount();
            freq = SEMI_ANNUAL.getConstant();
        } else if (QUARTERLY.getConstant().equals(frequency)) {

            endValue = CALCULATE.getQuarterCount();
            freq = QUARTERS.getConstant();
        } else if (MONTHLY.getConstant().equals(frequency)) {

            endValue = CALCULATE.getMonthCount();
            freq = MONTHS.getConstant();
        }

        for (int i = 1; i <= endValue; i++) {

            if ((i == 1)
                    && (QUARTERS.getConstant().equals(freq) || MONTHS
                    .getConstant().equals(freq) || YEARS.getConstant().equals(freq))) {
                period = freq.replace(Constant.S_SMALL, StringUtils.EMPTY);
                history.add(String.valueOf(i) + SPACE.getConstant() + period);
            } else {
                history.add(String.valueOf(i) + SPACE.getConstant() + freq);
            }
        }
        LOGGER.info("loadHistory method ends");
        return history;
    }

    /**
     * Load frequency.
     *
     * @param event the event
     */
    @Override
    protected void loadFrequency() {
        LOGGER.info("SalesProjectionResults ValueChangeEvent initiated with frequency -->"
                + frequency.getValue());
        if (frequency.getValue() == null) {
            history.removeAllItems();
            history.addItem(SELECT_ONE.getConstant());
            history.setNullSelectionItemId(SELECT_ONE.getConstant());
        } else {
            String historyConstant = StringUtils.EMPTY;
            if (Constant.ANNUALLY.equals(String.valueOf(frequency.getValue()))) {
                history.removeAllItems();
                history.addItem(SELECT_ONE.getConstant());
                history.setNullSelectionItemId(SELECT_ONE.getConstant());
                historyBean.addAll(loadHistory(Constant.ANNUALLY, YEAR.getConstant()));
                historyConstant = "1 Year";

            } else if (Constant.SEMI_ANNUALLY.equals(String.valueOf(frequency
                    .getValue()))) {
                history.removeAllItems();
                history.addItem(SELECT_ONE.getConstant());
                history.setNullSelectionItemId(SELECT_ONE.getConstant());
                historyBean.addAll(loadHistory(SEMI_ANNUALLY.getConstant(), SEMI_ANNUAL.getConstant()));
                historyConstant = "2 Semi-Annual";

            } else if (QUARTERLY.getConstant().equals(
                    String.valueOf(frequency.getValue()))) {
                history.removeAllItems();
                history.addItem(SELECT_ONE.getConstant());
                history.setNullSelectionItemId(SELECT_ONE.getConstant());
                historyBean.addAll(loadHistory(QUARTERLY.getConstant(), QUARTERS.getConstant()));
                historyConstant = Constant.FOUR_QUARTERS;
            } else if (MONTHLY.getConstant().equals(
                    String.valueOf(frequency.getValue()))) {
                history.removeAllItems();
                history.addItem(SELECT_ONE.getConstant());
                history.setNullSelectionItemId(SELECT_ONE.getConstant());
                historyBean.addAll(loadHistory(MONTHLY.getConstant(), MONTHS.getConstant()));
                historyConstant = "12 Months";
            }

            history.setContainerDataSource(historyBean);
            history.setValue(historyConstant);
        }
        LOGGER.info("SalesProjectionResults ValueChangeEvent ends ");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener
     * .ViewChangeEvent)
     */
    @Override
    public void enter(ViewChangeEvent event) {
        // TODO Auto-generated method stub
    }

    /**
     * Configure table.
     */
    public void configureTable() {
        LOGGER.info("configureTable method starts");

        fullHeader = new CustomTableHeaderDTO();

        CustomTableHeaderDTO leftDTO = HeaderUtils.getSalesProjectionResultsLeftTableColumns(fullHeader);
        projectionDTO.setProjTabName("SPR");
        rightDTO = HeaderUtils.getSalesProjectionResultsRightTableColumns(projectionDTO, fullHeader);
        projectionDTO.setProjTabName(StringUtils.EMPTY);
        resultBean = new ExtTreeContainer<>(SalesProjectionResultsDTO.class, ExtContainer.DataStructureMode.MAP);
        resultBean.setColumnProperties(leftDTO.getProperties());
        resultBean.setColumnProperties(rightDTO.getProperties());
        tableLogic.setTreeNodeMultiClick(false);

        tableLogic.setPageLength(NumericConstants.TWENTY);
        tableLogic.sinkItemPerPageWithPageLength(false);
        List<Integer> pagelength = CommonLogic.getPageNumber();
        tableLogic.getControlConfig().setPageLengthsAndCaptions(pagelength);
        tableLogic.setContainerDataSource(resultBean);

        leftTable = resultsTable.getLeftFreezeAsTable();
        rightTable = resultsTable.getRightFreezeAsTable();
        leftTable.markAsDirty();
        rightTable.markAsDirty();
        leftTable.setVisibleColumns(leftDTO.getSingleColumns().toArray());
        leftTable.setColumnHeaders(leftDTO.getSingleHeaders().toArray(new String[leftDTO.getSingleHeaders().size()]));

        leftTable.setFilterBarVisible(true);
        resultsTable.setHeight(Constant.SIX_FIFTY_PX);
        leftTable.setHeight(Constant.SIX_FIFTY_PX);
        rightTable.setHeight(Constant.SIX_FIFTY_PX);
        mapLeftVisibleColumns = leftDTO.getDoubleHeaderMaps();
        rightTable.setVisibleColumns(rightDTO.getSingleColumns().toArray());

        rightTable.setColumnHeaders(rightDTO.getSingleHeaders().toArray(new String[rightDTO.getSingleHeaders().size()]));
        for (int i = 0; i < rightDTO.getSingleColumns().size(); i++) {
            rightTable.setColumnAlignment(rightDTO.getSingleColumns().get(i), ExtCustomTable.Align.RIGHT);
        }
        rightTable.setDoubleHeaderVisible(true);
        rightTable
                .setDoubleHeaderVisibleColumns(rightDTO.getDoubleColumns().toArray());
        rightTable
                .setDoubleHeaderColumnHeaders(rightDTO.getDoubleHeaders().toArray(new String[rightDTO.getDoubleHeaders().size()]));
        for (int i = 0; i < rightDTO.getDoubleColumns().size(); i++) {
            rightTable.setColumnAlignment(rightDTO.getDoubleColumns().get(i), ExtCustomTable.Align.CENTER);
        }
        mapRightVisibleColumns = rightDTO.getDoubleHeaderMaps();
        resultsTable.setDoubleHeaderMap(mapLeftVisibleColumns, mapRightVisibleColumns);
        rightTable
                .addDoubleHeaderColumnCheckListener(new ExtCustomTable.DoubleHeaderColumnCheckListener() {
                    @Override
                    public void doubleHeaderColumnCheck(
                            ExtCustomTable.DoubleHeaderColumnCheckEvent event) {
                                Notification.show("Current Value: " + event.isChecked()
                                        + "\nPrropertyId: " + event.getPropertyId());
                            }
                });
        rightTable
                .addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
                    @Override
                    public void columnCheck(
                            ExtCustomTable.ColumnCheckEvent event) {
                                Notification.show("Current Value: " + event.isChecked()
                                        + "\nPrropertyId: " + event.getPropertyId());
                            }
                });
        configureTableFieldFactory();
        LOGGER.info("configureTable method ends");
    }

    /**
     * Reset button logic.
     *
     * @param event the event
     */
    @Override
    public void resetButtonLogic() {
        LOGGER.info("resetButtonLogic method starts");
        new AbstractNotificationUtils() {

            @Override
            public void noMethod() {
                return;
            }

            @Override
            public void yesMethod() {
                frequency.setValue(Constant.QUARTERLY);
                history.setValue(Constant.FOUR_QUARTERS);
                salesOrUnits.setValue(Constant.SALES_SMALL);
                periodOrder.setValue(Constant.ASCENDING);
                actualOrProj.setValue(Constant.ACTUALS);
                pivotView.setValue(Constant.PERIOD);
            }
        }.getConfirmationMessage("Reset?", "Are you sure you want to reset the values in the Sales Projection Selection section to the previous values?");
        LOGGER.info("resetButtonLogic method ends");
    }

    /**
     * Generate button logic.
     *
     * @param event the event
     */
    @Override
    public void generateButtonLogic() {
        generated = true;
        firstGenerated = true;
        int pageNo = tableLogic.getItemsPerPage();
        if (loadProjectionSelection()) {
            layout.removeComponent(resultsTable);
            layout.removeComponent(controlLayout);
            if ("variable".equalsIgnoreCase(pivotView.getValue().toString())) {
                panelpivot.setCaption("Variable Pivot View");
            } else {
                panelpivot.setCaption("Period Pivot View");
            }
            NMSalesProjectionResultsLogic logic = tableLogic.getSprLogic();
            tableLogic.setSprLogic(logic);
            resultsTable = new FreezePagedTreeTable(tableLogic);
            initializeResultTable();
            configureTable();
            addResultTable();
            generateLogic();
            tableLogic.setItemsPerPage(pageNo);
        } else {
            MessageBox.showPlain(Icon.INFO, "Error", "Please select a frequency and/or history and try again.", ButtonId.OK);
        }
        generated = false;
    }

    public void generateLogic() {
        levelFilter.removeValueChangeListener(levelFilterChangeOption);
        loadLevelAndFilterValue();
        levelFilter.addValueChangeListener(levelFilterChangeOption);
        tableLogic.clearAll();
        projectionDTO.setIsFilter(false);
        loadResultTable(0, StringUtils.EMPTY);
        if (customId != 0 && projectionDTO.isIsCustomHierarchy()) {
            editBtn.setEnabled(true);
        }
    }

    public ExtTreeContainer<SalesProjectionResultsDTO> loadTable(List<Object> headerList, boolean excelFlag, int levelNo, ExtTreeContainer<SalesProjectionResultsDTO> container, String indicator) {
        LOGGER.info("loadTable method starts");
        try {
            String salesUnits = salesOrUnits.getValue().toString();
            setBaseVariables(salesUnits);
            addProjectionTotal(container, indicator, levelNo);
            List<List> list;
            NMSalesProjectionResultsLogic logic = new NMSalesProjectionResultsLogic();
            Object[] selection = new Object[NumericConstants.NINE];
            selection[0] = session.getProjectionId();
            selection[1] = frequency.getValue().toString();
            selection[NumericConstants.TWO] = levelNo;
            selection[NumericConstants.THREE] = view.getValue().toString();
            selection[NumericConstants.FOUR] = history.getValue().toString();
            selection[NumericConstants.FIVE] = StringUtils.EMPTY;
            selection[NumericConstants.SIX] = indicator;
            selection[NumericConstants.SEVEN] = session.getUserId();
            selection[NumericConstants.EIGHT] = session.getSessionId();
            list = logic.generateSalesProjectionResults(selection, String.valueOf(salesOrUnits.getValue()), String.valueOf(actualOrProj.getValue()), headerList, pivotView.getValue().toString());
            if (!list.isEmpty()) {
                List<SalesProjectionResultsDTO> levelList = list.get(0);
                List<SalesProjectionResultsDTO> salesList = list.get(1);
                List<SalesProjectionResultsDTO> unitList = list.get(NumericConstants.TWO);
                if (Constant.PERIOD_SMALL.equalsIgnoreCase(pivotView.getValue().toString())) {

                    container.addBean(levelList.get(0));
                    container.setChildrenAllowed(levelList.get(0), true);
                    if (sales) {
                        container.addBean(salesList.get(0));
                        container.setChildrenAllowed(salesList.get(0), false);
                    }
                    if (units) {
                        container.addBean(unitList.get(0));
                        container.setChildrenAllowed(unitList.get(0), false);
                    }
                    container.setParent(salesList.get(0), levelList.get(0));
                    container.setParent(unitList.get(0), levelList.get(0));

                } else {
                    container.addBean(levelList.get(0));
                    container.setChildrenAllowed(levelList.get(0), true);
                    for (int i = 0; i < salesList.size(); i++) {
                        container.addBean(salesList.get(i));
                        container.setChildrenAllowed(salesList.get(i), false);
                        container.setParent(salesList.get(i), levelList.get(0));
                    }
                }
                if (excelFlag) {
                    Object[] obj = container.getItemIds().toArray();
                    if (sales && units) {
                        addLowerLevels(obj[obj.length - NumericConstants.THREE], excelFlag, container, StringUtils.EMPTY, StringUtils.EMPTY);
                    } else {
                        addLowerLevels(obj[obj.length - NumericConstants.TWO], excelFlag, container, StringUtils.EMPTY, StringUtils.EMPTY);
                    }
                }
            }
            LOGGER.info("loadTable method ends");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return container;
    }

    public void setBaseVariables(String salesUnits) {
        LOGGER.info("setBaseVariables method starts");
        if (Constant.BOTH_SMALL.equalsIgnoreCase(salesUnits)) {
            sales = true;
            units = true;
        } else if (Constant.SALES.equalsIgnoreCase(salesUnits)) {
            sales = true;
            units = false;
        } else {
            sales = false;
            units = true;
        }
        LOGGER.info("setBaseVariables method ends");
    }

    public void addLowerLevels(Object id, boolean excelFlag, ExtTreeContainer<SalesProjectionResultsDTO> container, String indicator, String levelNo) {
        try {
            LOGGER.info("addLowerLevels method starts");
            NMSalesProjectionResultsLogic logic = new NMSalesProjectionResultsLogic();
            SalesProjectionResultsDTO levelDto = getBeanFromId(id);
            List<List> resulList;
            Object[] selections = new Object[NumericConstants.NINE];
            selections[0] = session.getProjectionId();
            selections[1] = frequency.getValue().toString();
            if ("custom".equalsIgnoreCase(String.valueOf(view.getValue()))) {
                selections[NumericConstants.TWO] = levelNo;
                selections[NumericConstants.SIX] = indicator;
            } else {
                selections[NumericConstants.TWO] = levelDto.getLevelNo() + 1;
                selections[NumericConstants.SIX] = StringUtils.EMPTY;
            }
            selections[NumericConstants.THREE] = view.getValue().toString();
            selections[NumericConstants.FOUR] = history.getValue().toString();
            selections[NumericConstants.FIVE] = levelDto.getHierarchyNo();
            selections[NumericConstants.SEVEN] = session.getUserId();
            selections[NumericConstants.EIGHT] = session.getSessionId();
            resulList = logic.generateSalesProjectionResults(selections, String.valueOf(salesOrUnits.getValue()), String.valueOf(actualOrProj.getValue()), headerList, pivotView.getValue().toString());
            if (!resulList.isEmpty()) {
                List<SalesProjectionResultsDTO> resultList = resulList.get(0);
                List<SalesProjectionResultsDTO> salesList = resulList.get(1);
                List<SalesProjectionResultsDTO> unitList = resulList.get(NumericConstants.TWO);
                if (Constant.PERIOD_SMALL.equalsIgnoreCase(pivotView.getValue().toString())) {
                    container.addBean(resultList.get(0));
                    container.setChildrenAllowed(resultList.get(0), true);
                    container.setParent(resultList.get(0), levelDto);
                    if (sales) {
                        container.addBean(salesList.get(0));
                        container.setChildrenAllowed(salesList.get(0), false);
                        container.setParent(salesList.get(0), resultList.get(0));
                    }
                    if (units) {
                        container.addBean(unitList.get(0));
                        container.setChildrenAllowed(unitList.get(0), false);
                        container.setParent(unitList.get(0), resultList.get(0));
                    }
                    if (excelFlag) {
                        Object[] obj1 = container.getItemIds().toArray();
                        if (sales && units) {
                            addLowerLevels(obj1[obj1.length - NumericConstants.THREE], excelFlag, container, StringUtils.EMPTY, StringUtils.EMPTY);
                        } else {
                            addLowerLevels(obj1[obj1.length - NumericConstants.TWO], excelFlag, container, StringUtils.EMPTY, StringUtils.EMPTY);
                        }
                    }

                } else {
                    container.addBean(resultList.get(0));
                    container.setChildrenAllowed(resultList.get(0), true);
                    container.setParent(resultList.get(0), levelDto);
                    for (int i = 0; i < salesList.size(); i++) {
                        container.addBean(salesList.get(i));
                        container.setChildrenAllowed(salesList.get(i), false);
                        container.setParent(salesList.get(i), resultList.get(0));
                    }
                }
            }
            LOGGER.info("addLowerLevels method ends");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    public SalesProjectionResultsDTO getBeanFromId(Object obj) {
        LOGGER.info("getBeanFromId method starts");
        BeanItem<?> targetItem = null;
        if (obj instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) obj;
        } else if (obj instanceof SalesProjectionResultsDTO) {
            targetItem = new BeanItem<>(
                    (SalesProjectionResultsDTO) obj);
        }
        LOGGER.info("getBeanFromId method ends");
        return (SalesProjectionResultsDTO) targetItem.getBean();
    }

    public void saveSPResults() {
        LOGGER.info("saveSPResults method starts");
        try {
            if (isIsTabVisible()) {
                Map map = new HashMap();
                map.put(Constant.FREQUENCY_SMALL, frequency.getValue() != null ? frequency.getValue().toString() : StringUtils.EMPTY);
                map.put(Constant.HISTORY_CAPS, history.getValue() != null ? history.getValue().toString() : StringUtils.EMPTY);
                map.put("Sales/Units", salesOrUnits.getValue() != null ? salesOrUnits.getValue().toString() : StringUtils.EMPTY);
                map.put("Actuals/Projections", actualOrProj.getValue() != null ? actualOrProj.getValue().toString() : StringUtils.EMPTY);
                map.put(Constant.PERIOD_ORDER, periodOrder.getValue() != null ? periodOrder.getValue().toString() : StringUtils.EMPTY);
                map.put(Constant.PIVOT_SMALL, pivotView.getValue() != null ? pivotView.getValue().toString() : StringUtils.EMPTY);
                sprCommonLogic.saveNMSRPSelection(map, session.getProjectionId(), Constant.SALES_PROJECTION_RESULTS);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.info("saveSPResults method ends");
    }

    /**
     * Removes the items recursively.
     *
     * @param item the item
     */
    public void addProjectionTotal(ExtTreeContainer<SalesProjectionResultsDTO> container, String indicator, int levelNo) {
        LOGGER.info("addProjectionTotal method starts");
        String salesUnits = salesOrUnits.getValue().toString();
        setBaseVariables(salesUnits);
        SalesProjectionResultsDTO sprDTO = new SalesProjectionResultsDTO();
        sprDTO.setLevelValue(Constant.PROJECTION_TOTAL);
        List<List> list;
        NMSalesProjectionResultsLogic logic = new NMSalesProjectionResultsLogic();
        Object[] selection = new Object[NumericConstants.NINE];
        selection[0] = session.getProjectionId();
        selection[1] = frequency.getValue().toString();
        selection[NumericConstants.TWO] = levelNo;
        selection[NumericConstants.THREE] = view.getValue().toString();
        selection[NumericConstants.FOUR] = history.getValue().toString();
        selection[NumericConstants.FIVE] = StringUtils.EMPTY;
        selection[NumericConstants.SIX] = indicator;
        selection[NumericConstants.SEVEN] = session.getUserId();
        selection[NumericConstants.EIGHT] = session.getSessionId();
        list = logic.generateSalesProjectionResults(selection, String.valueOf(salesOrUnits.getValue()), String.valueOf(actualOrProj.getValue()), headerList, pivotView.getValue().toString());
        if (!list.isEmpty()) {
            List<SalesProjectionResultsDTO> salesList = list.get(1);
            List<SalesProjectionResultsDTO> unitList = list.get(NumericConstants.TWO);
            List<SalesProjectionResultsDTO> gts = logic.getGTSResult(session.getProjectionId(), session.getSessionId(), session.getUserId(), selection, pivotView.getValue().toString());
            if (Constant.PERIOD_SMALL.equalsIgnoreCase(pivotView.getValue().toString())) {
                container.addBean(sprDTO);
                container.setChildrenAllowed(sprDTO, false);
                if (!gts.isEmpty() && sales) {
                    container.addBean(gts.get(0));
                    container.setChildrenAllowed(gts.get(0), false);
                }
                if (sales) {
                    container.addBean(salesList.get(0));
                    container.setChildrenAllowed(salesList.get(0), false);
                }
                if (units) {
                    container.addBean(unitList.get(0));
                    container.setChildrenAllowed(unitList.get(0), false);
                }
            } else {
                container.addBean(sprDTO);
                container.setChildrenAllowed(sprDTO, false);
                for (int i = 0; i < salesList.size(); i++) {
                    container.addBean(salesList.get(i));
                    container.setChildrenAllowed(salesList.get(i), false);
                }
            }
        }
        LOGGER.info("addProjectionTotal method ends");
    }

    @Override
    public void newCustomHierarchyLogic() {
        LOGGER.info("newCustomHierarchhy clickEvent method starts");
        final CustomTreeBuild customTree = new CustomTreeBuild(session);
        customTree.addCloseListener(new Window.CloseListener() {

            @Override
            public void windowClose(Window.CloseEvent e) {
                if (customTree.isIsSelect()) {
                    customIdToSelect = customTree.getCustomId();
                }
                session.setCustomerViewList(CommonLogic.getCustomViewList(session.getProjectionId()));
                loadCustomDDLB(false);
            }
        });
        UI.getCurrent().addWindow(customTree);
        LOGGER.info("newCustomHierarchhy clickEvent method ends");
    }

    public void loadCustomDDLB(boolean fireValueChange) {
        LOGGER.info("loadCustomDDLB initiated ");
        customDdlb.setEnabled(true);

        if (!generated) {
            customDdlb.removeAllItems();
            customDdlb.addItem(SELECT_ONE);
            customDdlb.setNullSelectionItemId(SELECT_ONE);
            if (session.getCustomerViewList().isEmpty()) {
                customViewList = CommonLogic.getCustomViewList(session.getProjectionId());
                session.setCustomerViewList(customViewList);
            } else {
                customViewList = session.getCustomerViewList();
            }
            if (customViewList != null) {
                for (CustomViewMaster obj : customViewList) {
                    int customSid = obj.getCustomViewMasterSid();
                    Object itemId = customSid;
                    if (customIdToSelect == customSid) {
                    }
                    customDdlb.addItem(itemId);
                    customDdlb.setItemCaption(itemId, obj.getViewName());
                }
            }
            if (fireValueChange) {
                customDdlb.setValue(SELECT_ONE);
            }
            if (customIdToSelect == 0) {
                level.setEnabled(false);
                customDdlb.setValue(SELECT_ONE);
                if (firstGenerated) {
                    generateLogic();
                }
            } else {
                level.setEnabled(false);
                customDdlb.select(customIdToSelect);
            }
        }
        LOGGER.info("loadCustomDDLB ends ");
    }

    /**
     * Excel button logic.
     *
     * @param event the event
     */
    @Override
    public void excelButtonLogic() {
        configureExcelResultTable();
        levelFilterDdlbChangeOption(true);
        exportPeriodViewTable.setRefresh(Boolean.TRUE);
        exportPeriodViewTable.setDoubleHeaderVisible(true);
        ForecastUI.setEXCEL_CLOSE(true);
        ExcelExport exp = null;
        int exportAt = projectionDTO.getHeaderMapForExcel().size() - 1;
        if (Constant.PERIOD.equals(String.valueOf(pivotView.getValue())) && (QUARTERLY.getConstant().equals(String.valueOf(frequency.getValue())) || MONTHLY.getConstant().equals(String.valueOf(frequency.getValue())))) {
            for (int i = 0; i < projectionDTO.getHeaderMapForExcel().size(); i++) {
                exportPeriodViewTable.setVisibleColumns(((List<Object>) projectionDTO.getHeaderMapForExcel().get(i).get(0)).toArray());
                Object[] header = ((List<Object>) projectionDTO.getHeaderMapForExcel().get(i).get(1)).toArray();
                exportPeriodViewTable.setColumnHeaders(Arrays.copyOf(header, header.length, String[].class));
                exportPeriodViewTable
                        .setDoubleHeaderVisibleColumns(((List<Object>) projectionDTO.getHeaderMapForExcel().get(i).get(NumericConstants.THREE)).toArray());
                Object[] doubleHeader = ((List<Object>) projectionDTO.getHeaderMapForExcel().get(i).get(NumericConstants.FOUR)).toArray();
                exportPeriodViewTable
                        .setDoubleHeaderColumnHeaders(Arrays.copyOf(doubleHeader, doubleHeader.length, String[].class));

                exportPeriodViewTable.setDoubleHeaderMap((Map<Object, Object[]>) projectionDTO.getHeaderMapForExcel().get(i).get(NumericConstants.FIVE));
                exportPeriodViewTable.setRefresh(true);
                String sheetName = "Year " + String.valueOf(projectionDTO.getHeaderMapForExcel().get(i).get(NumericConstants.TWO));
                ForecastUI.setEXCEL_CLOSE(true);
                if (i == 0) {
                    exp = new ExcelExport(new ExtCustomTableHolder(exportPeriodViewTable), sheetName, Constant.SALES_PROJECTION_RESULTS, "Sales_Projection_Results.xls", false);
                } else {
                    exp.setNextTableHolder(new ExtCustomTableHolder(exportPeriodViewTable), sheetName);
                }
                if (i == exportAt) {
                    exp.exportMultipleTabs(true);
                } else {
                    exp.exportMultipleTabs(false);
                }
            }
        } else {
            exp = new ExcelExport(new ExtCustomTableHolder(exportPeriodViewTable), SALES_RESULTS, SALES_RESULTS, "Sales_Projection_Results.xls", false);
            exp.export();
        }
        layout.removeComponent(exportPeriodViewTable);
    }

    @Override
    public void editHierarchyBtnLogic() {
        LOGGER.info("editHierarchyBtn clickEvent method starts");
        if (CommonLogic.editButtonValidation(customDdlb, customViewList)) {
            final CustomTreeBuild customTree = new CustomTreeBuild(session, customId);
            customTree.addCloseListener(new Window.CloseListener() {

                @Override
                public void windowClose(Window.CloseEvent e) {
                    customIdToSelect = customTree.getCustomId();
                    session.setCustomerViewList(CommonLogic.getCustomViewList(session.getProjectionId()));
                    loadCustomDDLB(true);
                }
            });
            UI.getCurrent().addWindow(customTree);
        }
        LOGGER.info("editHierarchyBtn clickEvent method ends");
    }

    /**
     * value change of view.
     *
     * @param event the event
     */
    @Override
    public void customDdlbChangeOptionLogic() {
        LOGGER.info("customDdlbChangeOption ValueChangeEvent initiated ");
        customId = CommonLogic.customDdlbOptionChange(customDdlb, editBtn, level);
        projectionDTO.setCustomId(customId);
        int tpNo = CommonLogic.getTradingPartnerLevelNo(true, customId);
        projectionDTO.setTpLevel(tpNo);
        level.setEnabled(customId != 0);
        if (customId != 0) {
            session.setCustomId(customId);
            Utility.loadCustomHierarchyList(session);
        }
        if (!generated && firstGenerated) {
        generateLogic();
        }
        LOGGER.info("customDdlbChangeOption ValueChangeEvent ends ");
    }

    @Override
    public void graphExportLogics() {
        LOGGER.info("graphExport clickEvent method starts");
        graphExportLogic();
        LOGGER.info("graphExport clickEvent method ends");
    }

    public void graphExportLogic() {
        LOGGER.info("graphExportLogic method starts");
        List chartList = new ArrayList();
        for (SalesProjectionResultsDTO dto : resultBean.getBeans()) {
            if (dto.getLevelNo() != null) {
                chartList.add(dto);
            }
        }
        final SPRChart chart = new SPRChart(chartList, fullHeader, projectionDTO);
        final NmSPRGraphWindow salesGraphWindow = new NmSPRGraphWindow(chart.getChart(), SALES_RESULTS);
        UI.getCurrent().addWindow(salesGraphWindow);
        salesGraphWindow.focus();
        LOGGER.info("graphExportLogic method ends");
    }

    @Override
    public void expandButtonLogic() {
        if (level.getValue() != null && !SELECT_ONE.equals(level.getValue()) && !StringUtils.EMPTY.equals(level.getValue())) {
            expandCollapseLevelOption(true, level.getValue());
        }
    }

    @Override
    public void collapseButtonLogic() {
        if (level.getValue() != null && !SELECT_ONE.equals(level.getValue()) && !StringUtils.EMPTY.equals(level.getValue())) {
            expandCollapseLevelOption(false, level.getValue());
        }
    }

    public int getTabNumber() {
        return Constant.TWO;
    }

    /**
     * Add Result Table.
     */
    @SuppressWarnings("serial")
    private void addResultTable() {
        layout.addComponent(resultsTable);
        HorizontalLayout controls = tableLogic.createControls();
        controlLayout = CommonLogic.getResponsiveControls(controls);
        layout.addComponent(controlLayout);
    }

    /**
     * Initialize Result Table.
     */
    @SuppressWarnings("serial")
    private void initializeResultTable() {
        resultsTable.markAsDirty();
        resultsTable.setSelectable(false);
        resultsTable.setPageLength(NumericConstants.NINETEEN);
        resultsTable.setSplitPosition(splitPosition, Sizeable.Unit.PIXELS);
        resultsTable.setMinSplitPosition(minSplitPosition, Sizeable.Unit.PIXELS);
        resultsTable.setMaxSplitPosition(maxSplitPosition, Sizeable.Unit.PIXELS);
        resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
    }

    public boolean loadProjectionSelection() {
        boolean toRet = false;
        Object freq = frequency.getValue();
        boolean toFreq = false;
        int historyNum = 0;
        if ((freq != null) && (!SELECT_ONE.equals(freq.toString()))) {
            toFreq = true;
            projectionDTO.setFrequency(freq.toString());
        }
        Object hist = history.getValue();
        boolean toHist = false;
        if ((hist != null) && (!SELECT_ONE.equals(hist.toString()))) {
            toHist = true;
            projectionDTO.setHistory(hist.toString());
            String[] array = projectionDTO.getHistory().split(" ");
            historyNum = Integer.valueOf(array[0]);
        }
        if (toFreq && toHist) {
            toRet = true;
            projectionDTO.setSessionDTO(session);
            projectionId = session.getProjectionId();
            projectionDTO.setScreenName(screenName);
            projectionDTO.setUserId(Integer.valueOf(session.getUserId()));
            projectionDTO.setSessionId(Integer.valueOf(session.getSessionId()));
            projectionDTO.setCustRelationshipBuilderSid(session.getCustRelationshipBuilderSid());
            projectionDTO.setProdRelationshipBuilderSid(session.getProdRelationshipBuilderSid());
            projectionDTO.setActualsOrProjections(actualOrProj.getValue().toString());
            projectionDTO.setProjectionId(session.getProjectionId());
            projectionDTO.setSalesOrUnit(salesOrUnits.getValue().toString());
            projectionDTO.setProjectionOrder(periodOrder.getValue().toString());
            projectionDTO.setPivotView(pivotView.getValue().toString());
            projectionDTO.setCustomId(customId);
            projectionDTO.setView(view.getValue().toString());
            projectionDTO.setCustomerLevelNo(Integer.valueOf(session.getCustomerLevelNumber()));
            projectionDTO.setProductLevelNo(Integer.valueOf(session.getProductLevelNumber()));
            projectionDTO.setForecastDTO(session.getForecastDTO());
            projectionDTO.setHistoryNum(historyNum);
            projectionDTO.setProjectionNum(CommonUtils.getProjectionNumber(projectionDTO.getFrequency(), session));
            viewChange(false);
        }

        return toRet;
    }

    public void viewChange(boolean viewChange) {
        projectionDTO.setIsCustomHierarchy(false);
        customDdlb.setEnabled(false);
        editBtn.setEnabled(false);

        if (view.getValue() != null) {
            setTradingNo();
            if (Constant.CUSTOM_LABEL.equals(String.valueOf(view.getValue()))) {
                projectionDTO.setHierarchyIndicator(StringUtils.EMPTY);
                projectionDTO.setIsCustomHierarchy(true);
                if (firstGenerated && !generated) {
                    tableLogic.clearAll();
                    tableLogic.getControlTable().getContainerDataSource().removeAllItems();
                }
                levelFilter.setEnabled(false);
                loadCustomDDLB(false);

            } else {
                level.setEnabled(true);
                levelFilter.setEnabled(true);
                customIdToSelect = customId;

                projectionDTO.setTpLevel(tradingPartnerNo);
                projectionDTO.setIsCustomHierarchy(false);
                if (Constant.CUSTOMER_SMALL.equals(String.valueOf(view.getValue()))) {
                    projectionDTO.setHierarchyIndicator(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
                    projectionDTO.setRelationshipBuilderSid(projectionDTO.getCustRelationshipBuilderSid());
                    if (viewChange && firstGenerated) {
                        generateLogic();
                    }
                } else if (Constant.PRODUCT_LABEL.equals(String.valueOf(view.getValue()))) {
                    projectionDTO.setHierarchyIndicator(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
                    projectionDTO.setRelationshipBuilderSid(projectionDTO.getProdRelationshipBuilderSid());
                    if (viewChange && firstGenerated) {
                        generateLogic();
                    }
                }
            }
        }
    }

    public void loadLevelAndFilterValue() {
        LOGGER.info("loadLevelAndFilterValue initiated ");
        level.removeAllItems();
        level.addItem(SELECT_ONE);
        level.setNullSelectionItemId(SELECT_ONE);
        level.setValue(SELECT_ONE);
        level.setId("levelDdlb");
        levelFilter.removeAllItems();
        levelFilter.addItem(SELECT_ONE);
        levelFilter.setNullSelectionItemId(SELECT_ONE);
        levelFilter.setValue(SELECT_ONE);
        if (projectionDTO.isIsCustomHierarchy()) {
            if (!session.getCustomHierarchyMap().isEmpty() && customId != 0) {
                Utility.loadLevelValueForResult(level, null, null, session.getCustomHierarchyMap().get(customId), Constant.CUSTOM_LABEL);
            }
        } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projectionDTO.getHierarchyIndicator())) {
            Utility.loadLevelValueForResult(level, levelFilter, null, session.getCustomerHierarchyList(), view.getValue().toString());
        } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projectionDTO.getHierarchyIndicator())) {
            Utility.loadLevelValueForResult(level, levelFilter, null, session.getProductHierarchyList(), view.getValue().toString());

        }
        LOGGER.info("loadLevelAndFilterValue ends ");
    }

    /**
     * Loads the results.
     */
    @SuppressWarnings("serial")
    private void loadResultTable(int levelNo, String hierarchyNo) {
        projectionDTO.setFilterLevelNo(levelNo);
        projectionDTO.setFilterHierarchyNo(hierarchyNo);
        projectionDTO.setProductHierarchyNo(StringUtils.EMPTY);
        projectionDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
        projectionDTO.clearNonFetchableIndex();
        tableLogic.setProjectionResultsData(projectionDTO);
        configureTableFieldFactory();
    }

    public void levelFilterDdlbChangeOption(boolean excelExport) {
        List<Object> levelHierarchy = CommonLogic.getLevelNoAndHierarchyNo(levelFilter.getValue());

        int levelNo = Integer.valueOf(String.valueOf(levelHierarchy.get(0)));
        if (levelNo < 0) {
            levelNo = 0;
        }
        projectionDTO.setIsFilter(true);
        if (levelNo == 0) {
            projectionDTO.setIsFilter(false);
        }
        String hierarchyNo = String.valueOf(levelHierarchy.get(1));
        projectionDTO.setIsFilter(true);
        if (levelNo == 0) {
            projectionDTO.setIsFilter(false);
        }
        if (excelExport) {
            loadExcelResultTable(levelNo, hierarchyNo);
        } else {
            tableLogic.clearAll();
            loadResultTable(levelNo, hierarchyNo);
        }

    }

    /**
     * Loads the Excel Results.
     */
    @SuppressWarnings("serial")
    private void loadExcelResultTable(int levelNo, String hierarchyNo) {

        excelResultBean.removeAllItems();
        projectionDTO.setFilterLevelNo(levelNo);
        projectionDTO.setFilterHierarchyNo(hierarchyNo);
        projectionDTO.setProductHierarchyNo(StringUtils.EMPTY);
        projectionDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
        projectionDTO.clearNonFetchableIndex();
        loadHierarchyIndicator();
        tableLogic.loadExcelData(excelResultBean);
    }

    private void loadHierarchyIndicator() {
        setTradingNo();
        if (Constant.CUSTOMER_SMALL.equals(String.valueOf(view.getValue()))) {
            projectionDTO.setHierarchyIndicator(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
            projectionDTO.setRelationshipBuilderSid(projectionDTO.getCustRelationshipBuilderSid());
        } else if (Constant.PRODUCT_LABEL.equals(String.valueOf(view.getValue()))) {
            projectionDTO.setHierarchyIndicator(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
            projectionDTO.setRelationshipBuilderSid(projectionDTO.getProdRelationshipBuilderSid());
        }
    }

    public void addLowerLevelsForExport(Object id) {
        projectionDTO.setFilterLevelNo(0);
        projectionDTO.setFilterHierarchyNo(StringUtils.EMPTY);
        projectionDTO.setProductHierarchyNo(StringUtils.EMPTY);
        projectionDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
        int count = tableLogic.getSprLogic().getConfiguredSalesProjectionResultsCount(id, projectionDTO, true);
        List<SalesProjectionResultsDTO> resultList = tableLogic.getSprLogic().getConfiguredSalesProjectionResults(id, 0, count, projectionDTO);
        loadDataToContainer(resultList, id);
    }

    public void loadDataToContainer(List<SalesProjectionResultsDTO> resultList, Object parentId) {
        for (SalesProjectionResultsDTO dto : resultList) {
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

    /**
     * Inits the product.
     */
    @SuppressWarnings("serial")
    private void configureExcelResultTable() {
        excelResultBean = new ExtTreeContainer<>(SalesProjectionResultsDTO.class, ExtContainer.DataStructureMode.MAP);

        Map<String, String> selection = new HashMap<>();
        selection.put(Constant.FREQUENCY, frequency.getValue().toString());
        selection.put(Constant.HISTORY, history.getValue().toString());
        selection.put(Constant.ORDER, periodOrder.getValue().toString());
        selection.put("projectFrequency", history.getValue().toString());
        selection.put(Constant.SALESORUNITS, salesOrUnits.getValue().toString());
        selection.put("screen", "spr");
        selection.put(Constant.ACTUALSORPROJECTIONS, actualOrProj.getValue().toString());
        selection.put(Constant.VIEW, pivotView.getValue().toString());
        if (startAndTodate == null) {
            startAndTodate = CommonUtils.getStartandTodate();
        }
        Date startDate = startAndTodate.get(0);
        Date endDate = startAndTodate.get(1);
        if (startDate != null && endDate != null) {
            selection.put("projectionNum", String.valueOf(CommonUtils.getProjections(new Date(), endDate, frequency.getValue().toString())));
        }
        String[] his = history.getValue().toString().split(" ");
        selection.put("historyNum", String.valueOf(Integer.valueOf(his[0])));
        exportPeriodViewTable = new ExtFilterTreeTable();
        layout.addComponent(exportPeriodViewTable);
        exportPeriodViewTable.setRefresh(Boolean.FALSE);
        exportPeriodViewTable.setVisible(false);
        excelResultBean.setColumnProperties(fullHeader.getProperties());
        exportPeriodViewTable.setContainerDataSource(excelResultBean);
        exportPeriodViewTable.setVisibleColumns(fullHeader.getSingleColumns().toArray());
        exportPeriodViewTable.setColumnHeaders(fullHeader.getSingleHeaders().toArray(new String[fullHeader.getSingleHeaders().size()]));
        exportPeriodViewTable
                .setDoubleHeaderVisibleColumns(fullHeader.getDoubleColumns().toArray());
        exportPeriodViewTable
                .setDoubleHeaderColumnHeaders(fullHeader.getDoubleHeaders().toArray(new String[fullHeader.getDoubleHeaders().size()]));

        exportPeriodViewTable.setDoubleHeaderMap(fullHeader.getDoubleHeaderMaps());
    }

    private void expandCollapseLevelOption(boolean isExpand, Object value) {

        List<Object> levelHierarchy = CommonLogic.getLevelNoAndHierarchyNo(value);
        int levelNo = Integer.valueOf(String.valueOf(levelHierarchy.get(0)));
        if (levelNo > 0) {
            
                levelFilter.removeValueChangeListener(levelFilterChangeOption);
                levelFilter.setValue(null);
                levelFilter.setImmediate(true);
                projectionDTO.setIsFilter(false);
                tableLogic.clearAll();
                levelFilter.addValueChangeListener(levelFilterChangeOption);
           
            String hierarchyIndicator = String.valueOf(levelHierarchy.get(1));
            projectionDTO.setHierarchyIndicator(hierarchyIndicator);
            if (!isExpand) {
                levelNo--;
            }
            tableLogic.loadExpandData(levelNo);
        }
    }

    public void setProjectionSelection() {
        Map<Object, Object> map = CommonLogic.getNMProjectionSelection(projectionId, TAB_SALES_PROJECTION_RESULTS.getConstant());
        if (map != null && !map.isEmpty()) {
            Object value = map.get(Constant.FREQUENCY_SMALL);
            if (value != null) {
                frequency.setValue(value.toString());
            }
            value = map.get(Constant.HISTORY_CAPS);
            if (value != null) {
                history.setValue(value.toString());
            }
            value = map.get(Constant.PERIOD_ORDER);
            if (value != null) {
                periodOrder.setValue(value.toString());
            }
            value = map.get(Constant.PIVOT_SMALL);
            if (value != null) {
                pivotView.setValue(value.toString());
            }
            value = map.get("Actuals/Projections");
            if (value != null) {
                actualOrProj.setValue(value.toString());
            }
            value = map.get("Sales/Units");
            if (value != null) {
                salesOrUnits.setValue(value.toString());
            }
            value = map.get(Constant.PIVOT_SMALL);
            if (value != null) {
                view.setValue(value.toString());
            }
        }
    }

    public void pushUpdate(String indicator) {
        if (Constants.IndicatorConstants.INDICATOR_REFRESH_UPDATE.getConstant().equals(indicator)) {
            // Refresh with new temp values
        }
        if (Constants.IndicatorConstants.INDICATOR_TIME_PERIOD_CHANGED.getConstant().equals(indicator)) {
            // Refresh after from period change in DS
        }
    }

    public void loadGroupFilter() {
        LOGGER.info("loadGroupFilter initiated ");

        LOGGER.info("loadGroupFilter ends ");
    }

    public void loadGroupFilterOntabChange() {
        loadGroupFilter();
    }


    public void securityForButtons() {
        final StplSecurity stplSecurity = new StplSecurity();
        try {
            final Map<String, AppPermission> functionPsHM = stplSecurity.getBusinessFunctionPermissionForNm(String.valueOf(VaadinSession.getCurrent().getAttribute("businessRoleIds")), GlobalConstants.getCommercialConstant() + "," + UISecurityUtil.SALES_PROJECTION_RESULTS);

            if (!(functionPsHM.get(CommonUtils.GENERATE_BUTTON) != null && ((AppPermission) functionPsHM.get(CommonUtils.GENERATE_BUTTON)).isFunctionFlag())) {
                generateBtn.setVisible(Boolean.FALSE);
                expandBtn.setVisible(Boolean.FALSE);
                collapseBtn.setVisible(Boolean.FALSE);
                newBtn.setVisible(Boolean.FALSE);
                editBtn.setVisible(Boolean.FALSE);

            }

        } catch (PortalException | SystemException ex) {
            LoggerFactory.getLogger(NMSalesProjectionResults.class.getName()).error( StringUtils.EMPTY, ex);
        }

    }

    public void configureTabOrder() {
        frequency.focus();
        frequency.setTabIndex(1);
        history.setTabIndex(NumericConstants.TWO);
        salesOrUnits.setTabIndex(NumericConstants.THREE);
        actualOrProj.setTabIndex(NumericConstants.FOUR);
        periodOrder.setTabIndex(NumericConstants.FIVE);
        pivotView.setTabIndex(NumericConstants.SIX);
        generateBtn.setTabIndex(NumericConstants.SEVEN);
        resetBtn.setTabIndex(NumericConstants.EIGHT);
        level.setTabIndex(NumericConstants.NINE);
        expandBtn.setTabIndex(NumericConstants.TEN);
        collapseBtn.setTabIndex(NumericConstants.ELEVEN);
        levelFilter.setTabIndex(NumericConstants.THIRTEEN);
        view.setTabIndex(NumericConstants.FOURTEEN);
        newBtn.setTabIndex(NumericConstants.FIFTEEN);
        editBtn.setTabIndex(NumericConstants.SIXTEEN);
        excelBtn.setTabIndex(NumericConstants.SEVENTEEN);
        graphBtn.setTabIndex(NumericConstants.EIGHTEEN);
        AbstractForm.getBtnSave().setTabIndex(NumericConstants.NINETEEN);
        AbstractForm.getBtnPrev().setTabIndex(NumericConstants.TWENTY);
        AbstractForm.getBtnNext().setTabIndex(NumericConstants.TWENTY_ONE);
        AbstractForm.getBtnClose().setTabIndex(NumericConstants.TWENTY_TWO);
        AbstractForm.getBtnSubmit().setTabIndex(NumericConstants.TWENTY_THREE);
    }

    /**
     * Configures the field factory for the tables used Mandated and
     * Non-Mandated.
     */
    protected void configureTableFieldFactory() {

        if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projectionDTO.getScreenName())) {
            CommercialGroupFilter();
        }

    }

    public void CommercialGroupFilter() {
        leftTable.setFilterDecorator(new ExtDemoFilterDecorator());
        ExtFilterGenerator sprFilter = leftTable.getFilterGenerator();
        if (sprFilter == null) {
            sprFilter = new ExtFilterGenerator() {

                @Override
                public Container.Filter generateFilter(Object propertyId, Object value) {
                    return null;
                }

                @Override
                public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
                    return null;
                }

                @Override
                public AbstractField<?> getCustomFilterComponent(Object propertyId) {
                    if ("levelValue".equals(propertyId) || Constant.GROUP.equals(propertyId)) {
                          ComboBox  group = getComboBox();
                          group.setImmediate(true);
                        return group;
                    }
                    return null;
                }

                @Override
                public void filterRemoved(Object propertyId) {
                    return;
                }

                @Override
                public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
                    return;
                }

                @Override
                public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
                    return null;
                }
            };
        }
        leftTable.setFilterGenerator(sprFilter);
    }

    private void loadCombobox(ComboBox group) {
        group.removeAllItems();
        group.setNullSelectionAllowed(true);
        group.addItem(Constant.ALL_SALES_GROUP);
        group.setNullSelectionItemId(Constant.ALL_SALES_GROUP);
        List<String> groupList = CommonLogic.getSalesGroupDDLB(session);
        if (groupList != null && !groupList.isEmpty()) {
            for (String groups : groupList) {
                group.addItem(groups);
            }
        }
        group.select(Constant.ALL_SALES_GROUP);
    }

    private ComboBox getComboBox() {
        final ComboBox group = new ComboBox();
        group.removeAllItems();
        loadCombobox(group);
        setTradingNo();
        group.select(Constant.ALL_SALES_GROUP);
        group.setWidth("100%");
        return group;
    }

    public void configure() {
        if (flag) {
            configureFields();
            securityForButtons();
            flag = false;
        }
    }

    private void setTradingNo() {
        if (tradingPartnerNo == 0) {
            tradingPartnerNo = Utility.getTradingPartnerLevelNo(session.getProjectionId(), session);
        }
    }

    public void defaultFocus() {
        frequency.focus();
    }

    public boolean isIsTabVisible() {
        return isTabVisible;
    }

    public void setIsTabVisible(boolean isTabVisible) {
        this.isTabVisible = isTabVisible;
    }
    
    
}
