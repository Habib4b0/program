/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.salesprojectionresults.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.app.galforecasting.abstractforecast.ForecastSalesProjectionResults;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.app.galforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.dto.SalesProjectionResultsDTO;
import com.stpl.app.galforecasting.logic.CommonLogic;
import com.stpl.app.galforecasting.salesprojectionresults.logic.NMSalesProjectionResultsLogic;
import com.stpl.app.galforecasting.salesprojectionresults.logic.SPRCommonLogic;
import com.stpl.app.galforecasting.salesprojectionresults.logic.tablelogic.NMSalesProjectionResultsTableLogic;
import com.stpl.app.model.CustomViewMaster;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.galforecasting.ui.ForecastUI;
import com.stpl.app.galforecasting.ui.form.lookups.CustomTreeBuild;
import com.stpl.app.galforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import com.stpl.app.galforecasting.utils.HeaderUtils;
import com.stpl.app.galforecasting.utils.NmSPRGraphWindow;
import com.stpl.app.galforecasting.utils.SPRChart;
import com.stpl.app.galforecasting.utils.UISecurityUtil;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.utils.Constants;
import static com.stpl.app.utils.Constants.CalculatePeriods.CALCULATE;
import static com.stpl.app.utils.Constants.CommonConstants.*;
import static com.stpl.app.utils.Constants.FrequencyConstants.*;
import static com.stpl.app.utils.Constants.LabelConstants.TAB_SALES_PROJECTION_RESULTS;
import static com.stpl.app.utils.Constants.ResourceConstants.*;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Notification;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import org.jboss.logging.Logger;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.ExtCustomTreeTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;

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
    public static final Logger LOGGER = Logger.getLogger(NMSalesProjectionResults.class);

    List<Object> possibleKeyList = new ArrayList<Object>();
    boolean sales;
    boolean units;

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
    final private BeanItemContainer<String> historyBean = new BeanItemContainer<String>(
            String.class);
    /**
     * The map left visible columns.
     */
    private Map<Object, Object[]> mapLeftVisibleColumns = new HashMap<Object, Object[]>();
    /**
     * The map right visible columns.
     */
    private Map<Object, Object[]> mapRightVisibleColumns = new HashMap<Object, Object[]>();
    ExtFilterTreeTable leftTable;
    ExtFilterTreeTable rightTable;
    SessionDTO session;
    List<Object> headerList;
    List<CustomViewMaster> customViewList = new ArrayList<CustomViewMaster>();
    int customIdToSelect = 0;
    private ExtCustomTreeTable exportPeriodViewTable;
    public static final String SALES_RESULTS = "Sales Projection Results";
    int customId = 0;

    ProjectionSelectionDTO projectionDTO = new ProjectionSelectionDTO();
    private NMSalesProjectionResultsLogic excelLogic = new NMSalesProjectionResultsLogic();
    CustomTableHeaderDTO fullHeader = new CustomTableHeaderDTO();
    boolean firstGenerated = false;
    boolean generated = false;
    int tradingPartnerNo = 0;
    Property.ValueChangeListener levelFilterChangeOption = new Property.ValueChangeListener() {

        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            levelFilterDdlbChangeOption(false);
        }
    };
    /**
     * The projection id.
     */
    int projectionId;
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
    public HorizontalLayout controlLayout;

    String screenName = StringUtils.EMPTY;

    /**
     * The period table id.
     */
    /**
     * The result bean.
     */
    private CustomTreeContainer<SalesProjectionResultsDTO> resultBean = new CustomTreeContainer<SalesProjectionResultsDTO>(
            SalesProjectionResultsDTO.class);
    private CustomTreeContainer<SalesProjectionResultsDTO> excelResultBean = new CustomTreeContainer<SalesProjectionResultsDTO>(
            SalesProjectionResultsDTO.class);
    CustomTableHeaderDTO rightDTO;
    NMSalesProjectionResultsTableLogic tableLogic = new NMSalesProjectionResultsTableLogic();
    private SPRCommonLogic sprCommonLogic = new SPRCommonLogic();
    FreezePagedTreeTable resultsTable = new FreezePagedTreeTable(tableLogic);
    private Integer hierarchyLevelNo = 0;
    boolean flag = false;

    /**
     * Instantiates a new SALES_SMALL projection results.
     */
    public NMSalesProjectionResults(SessionDTO session, String screenName) {
        super();
        LOGGER.info("SalesProjectionResults Constructor initiated ");
        this.session = session;
        this.screenName = screenName;
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

        history.addItem("4 Quarters");
        history.setValue("4 Quarters");

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
        view.addItem(Constant.PRODUCT);
        view.addItem(Constant.CUSTOM);
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
        List<String> history = new ArrayList<String>();
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
                historyConstant = "4 Quarters";
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
    public void enter(ViewChangeEvent event) {
        // TODO Auto-generated method stub
    }

    /**
     * Configure table.
     */
    public void configureTable() {
        LOGGER.info("configureTable method starts");

        fullHeader = new CustomTableHeaderDTO();
        Map<String, String> selection = new HashMap<String, String>();
        selection.put(Constant.FREQUENCY, frequency.getValue().toString());
        selection.put(Constant.HISTORY, history.getValue().toString());
        selection.put(Constant.ORDER, periodOrder.getValue().toString());
        selection.put("projectFrequency", history.getValue().toString());
        selection.put(Constant.SALESORUNITS, salesOrUnits.getValue().toString());
        selection.put("screen", "spr");
        selection.put(Constant.ACTUALSORPROJECTIONS, actualOrProj.getValue().toString());
        selection.put(Constant.VIEW, pivotView.getValue().toString());
        List<Date> startAndTodate = CommonUtils.getStartandTodate();
        Date startDate = startAndTodate.get(0);
        Date endDate = startAndTodate.get(1);

        if (startDate != null && endDate != null) {
            selection.put("projectionNum", String.valueOf(CommonUtils.getProjections(new Date(), endDate, frequency.getValue().toString())));
        }
        String[] his = history.getValue().toString().split(" ");
        selection.put("historyNum", String.valueOf(Integer.valueOf(his[0])));
        CustomTableHeaderDTO leftDTO = HeaderUtils.getSalesProjectionResultsLeftTableColumns(selection, fullHeader);
        projectionDTO.setProjTabName("SPR");
        rightDTO = HeaderUtils.getSalesProjectionResultsRightTableColumns(projectionDTO, session, fullHeader);
        projectionDTO.setProjTabName(StringUtils.EMPTY);
        resultBean = new CustomTreeContainer<SalesProjectionResultsDTO>(SalesProjectionResultsDTO.class);
        resultBean.setColumnProperties(leftDTO.getProperties());
        resultBean.setColumnProperties(rightDTO.getProperties());
        tableLogic.setTreeNodeMultiClick(false);

        tableLogic.setPageLength(20);
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
        resultsTable.setHeight("650px");
        leftTable.setHeight("650px");
        rightTable.setHeight("650px");
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
                    public void doubleHeaderColumnCheck(
                            ExtCustomTable.DoubleHeaderColumnCheckEvent event) {
                                Notification.show("Current Value: " + event.isChecked()
                                        + "\nPrropertyId: " + event.getPropertyId());
                            }
                });
        rightTable
                .addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
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

            }

            @Override
            public void yesMethod() {
                frequency.setValue(Constant.QUARTERLY);
                history.setValue("4 Quarters");
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
            tableLogic = new NMSalesProjectionResultsTableLogic();
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

    public CustomTreeContainer<SalesProjectionResultsDTO> loadTable(List<Object> headerList, boolean excelFlag, int levelNo, CustomTreeContainer<SalesProjectionResultsDTO> container, String indicator) {
        LOGGER.info("loadTable method starts");
        try {
            String salesUnits = salesOrUnits.getValue().toString();
            setBaseVariables(salesUnits);
            addProjectionTotal(container, indicator, levelNo);
            List<List> list;
            NMSalesProjectionResultsLogic logic = new NMSalesProjectionResultsLogic();
            Object[] selection = new Object[9];
            selection[0] = session.getProjectionId();
            selection[1] = frequency.getValue().toString();
            selection[2] = levelNo;
            selection[3] = view.getValue().toString();
            selection[4] = history.getValue().toString();
            selection[5] = StringUtils.EMPTY;
            selection[6] = indicator;
            selection[7] = session.getUserId();
            selection[8] = session.getSessionId();
            list = logic.generateSalesProjectionResults(selection, String.valueOf(salesOrUnits.getValue()), String.valueOf(actualOrProj.getValue()), headerList, pivotView.getValue().toString());
            if (!list.isEmpty()) {
                List<SalesProjectionResultsDTO> levelList = list.get(0);
                List<SalesProjectionResultsDTO> salesList = list.get(1);
                List<SalesProjectionResultsDTO> unitList = list.get(2);
                if ("period".equalsIgnoreCase(pivotView.getValue().toString())) {

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
                        addLowerLevels(obj[obj.length - 3], excelFlag, container, StringUtils.EMPTY, StringUtils.EMPTY);
                    } else {
                        addLowerLevels(obj[obj.length - 2], excelFlag, container, StringUtils.EMPTY, StringUtils.EMPTY);
                    }
                }
            }
            LOGGER.info("loadTable method ends");
        } catch (Exception e) {
            LOGGER.error(e);
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

    public void addLowerLevels(Object id, boolean excelFlag, CustomTreeContainer<SalesProjectionResultsDTO> container, String indicator, String levelNo) {
        try {
            LOGGER.info("addLowerLevels method starts");
            NMSalesProjectionResultsLogic logic = new NMSalesProjectionResultsLogic();
            SalesProjectionResultsDTO levelDto = getBeanFromId(id);
            List<List> resulList = new ArrayList<List>();
            Object[] selections = new Object[9];
            selections[0] = session.getProjectionId();
            selections[1] = frequency.getValue().toString();
            if ("custom".equalsIgnoreCase(String.valueOf(view.getValue()))) {
                selections[2] = levelNo;
                selections[6] = indicator;
            } else {
                selections[2] = levelDto.getLevelNo() + 1;
                selections[6] = StringUtils.EMPTY;
            }
            selections[3] = view.getValue().toString();
            selections[4] = history.getValue().toString();
            selections[5] = levelDto.getHierarchyNo();
            selections[7] = session.getUserId();
            selections[8] = session.getSessionId();
            resulList = logic.generateSalesProjectionResults(selections, String.valueOf(salesOrUnits.getValue()), String.valueOf(actualOrProj.getValue()), headerList, pivotView.getValue().toString());
            if (!resulList.isEmpty()) {
                List<SalesProjectionResultsDTO> resultList = resulList.get(0);
                List<SalesProjectionResultsDTO> salesList = resulList.get(1);
                List<SalesProjectionResultsDTO> unitList = resulList.get(2);
                if ("period".equalsIgnoreCase(pivotView.getValue().toString())) {
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
                            addLowerLevels(obj1[obj1.length - 3], excelFlag, container, StringUtils.EMPTY, StringUtils.EMPTY);
                        } else {
                            addLowerLevels(obj1[obj1.length - 2], excelFlag, container, StringUtils.EMPTY, StringUtils.EMPTY);
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
            LOGGER.error(e);
        }
    }

    public SalesProjectionResultsDTO getBeanFromId(Object obj) {
        LOGGER.info("getBeanFromId method starts");
        BeanItem<?> targetItem = null;
        if (obj instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) obj;
        } else if (obj instanceof SalesProjectionResultsDTO) {
            targetItem = new BeanItem<SalesProjectionResultsDTO>(
                    (SalesProjectionResultsDTO) obj);
        }
        LOGGER.info("getBeanFromId method ends");
        return (SalesProjectionResultsDTO) targetItem.getBean();
    }

    public void saveSPResults() {
        LOGGER.info("saveSPResults method starts");
        try {
            Map map = new HashMap();
            map.put(Constant.FREQUENCY_SMALL, frequency.getValue() != null ? frequency.getValue().toString() : StringUtils.EMPTY);
            map.put(Constant.HISTORY_CAPS, history.getValue() != null ? history.getValue().toString() : StringUtils.EMPTY);
            map.put("Sales/Units", salesOrUnits.getValue() != null ? salesOrUnits.getValue().toString() : StringUtils.EMPTY);
            map.put("Actuals/Projections", actualOrProj.getValue() != null ? actualOrProj.getValue().toString() : StringUtils.EMPTY);
            map.put(Constant.PERIOD_ORDER, periodOrder.getValue() != null ? periodOrder.getValue().toString() : StringUtils.EMPTY);
            map.put("Pivot", pivotView.getValue() != null ? pivotView.getValue().toString() : StringUtils.EMPTY);
            sprCommonLogic.saveNMSRPSelection(map, session.getProjectionId(), "Sales Projection Results");
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("saveSPResults method ends");
    }

    /**
     * Removes the items recursively.
     *
     * @param item the item
     */
    private void removeItemsRecursively(Object item) {
        LOGGER.info("removeItemsRecursively method starts");
        Collection<?> children = resultsTable.getLeftFreezeAsTable().getChildren(item);
        if (children != null && children.size() > 0) {
            CustomTreeContainer<SalesProjectionResultsDTO> tempBean = new CustomTreeContainer<SalesProjectionResultsDTO>(SalesProjectionResultsDTO.class);
            LinkedList<Object> children2 = new LinkedList<Object>();
            for (Iterator<?> i = children.iterator(); i.hasNext();) {
                children2.add((Object) i.next());
            }
            for (Iterator<Object> i = children2.iterator(); i.hasNext();) {
                Object child = i.next();
                SalesProjectionResultsDTO beanItem = getBeanFromId(child);
                tempBean.addBean(beanItem);
                if (resultsTable.getLeftFreezeAsTable().areChildrenAllowed(child)) {
                    resultBean.removeItemRecursively(child);
                }
            }
        }
        LOGGER.info("removeItemsRecursively method ends");
    }

    public void addProjectionTotal(CustomTreeContainer<SalesProjectionResultsDTO> container, String indicator, int levelNo) {
        LOGGER.info("addProjectionTotal method starts");
        String salesUnits = salesOrUnits.getValue().toString();
        setBaseVariables(salesUnits);
        SalesProjectionResultsDTO sprDTO = new SalesProjectionResultsDTO();
        sprDTO.setLevelValue(Constant.PROJECTION_TOTAL);
        List<List> list;
        NMSalesProjectionResultsLogic logic = new NMSalesProjectionResultsLogic();
        Object[] selection = new Object[9];
        selection[0] = session.getProjectionId();
        selection[1] = frequency.getValue().toString();
        selection[2] = levelNo;
        selection[3] = view.getValue().toString();
        selection[4] = history.getValue().toString();
        selection[5] = StringUtils.EMPTY;
        selection[6] = indicator;
        selection[7] = session.getUserId();
        selection[8] = session.getSessionId();
        list = logic.generateSalesProjectionResults(selection, String.valueOf(salesOrUnits.getValue()), String.valueOf(actualOrProj.getValue()), headerList, pivotView.getValue().toString());
        if (!list.isEmpty()) {
            List<SalesProjectionResultsDTO> salesList = list.get(1);
            List<SalesProjectionResultsDTO> unitList = list.get(2);
            List<SalesProjectionResultsDTO> gts = logic.getGTSResult(session.getProjectionId(), session.getSessionId(), session.getUserId(), headerList, selection, pivotView.getValue().toString());
            if ("period".equalsIgnoreCase(pivotView.getValue().toString())) {
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
        final CustomTreeBuild customTree = new CustomTreeBuild(Constant.ADD_FULL_SMALL, session);
        customTree.addCloseListener(new Window.CloseListener() {

            @Override
            public void windowClose(Window.CloseEvent e) {
                if (customTree.isIsSelect()) {
                    customIdToSelect = customTree.getCustomId();
                }
                loadCustomDDLB();
            }
        });
        UI.getCurrent().addWindow(customTree);
        LOGGER.info("newCustomHierarchhy clickEvent method ends");
    }

    public void loadCustomDDLB() {
        LOGGER.info("loadCustomDDLB initiated ");
        customDdlb.setEnabled(true);

        if (!generated) {
            customDdlb.removeAllItems();
            customDdlb.addItem(SELECT_ONE);
            customDdlb.setNullSelectionItemId(SELECT_ONE);
            customViewList = CommonLogic.getCustomViewList(session.getProjectionId());
            if (customViewList != null) {
                Object select = null;
                for (CustomViewMaster obj : customViewList) {
                    int customSid = obj.getCustomViewMasterSid();
                    Object itemId = customSid;
                    if (customIdToSelect == customSid) {
                        select = itemId;
                    }
                    customDdlb.addItem(itemId);
                    customDdlb.setItemCaption(itemId, obj.getViewName());
                }
            }
            if (customIdToSelect == 0) {
                level.setEnabled(false);
                customDdlb.setValue(SELECT_ONE);
                if (!generated && firstGenerated) {
                    generateLogic();
                }
            } else {
                level.setEnabled(true);
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
        ForecastUI.EXCEL_CLOSE=true;
        ExcelExport exp = new ExcelExport(new ExtCustomTableHolder(exportPeriodViewTable), SALES_RESULTS, SALES_RESULTS, "Sales Projection Results.xls", false);
        exp.export();
        layout.removeComponent(exportPeriodViewTable);
    }

    @Override
    public void editHierarchyBtnLogic() {
        LOGGER.info("editHierarchyBtn clickEvent method starts");
        if (CommonLogic.editButtonValidation(customDdlb, customViewList)) {
            final CustomTreeBuild customTree = new CustomTreeBuild(Constant.EDIT, session, customId);
            customTree.addCloseListener(new Window.CloseListener() {

                @Override
                public void windowClose(Window.CloseEvent e) {
                    customIdToSelect = customTree.getCustomId();
                    loadCustomDDLB();
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
        for (SalesProjectionResultsDTO dto : resultBean.getItemIds()) {
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
        resultsTable.setImmediate(true);
        resultsTable.setPageLength(19);
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
        if (freq != null) {
            if (!SELECT_ONE.equals(freq.toString())) {
                toFreq = true;
                projectionDTO.setFrequency(freq.toString());
            }
        }
        Object hist = history.getValue();
        boolean toHist = false;
        if (hist != null) {
            if (!SELECT_ONE.equals(hist.toString())) {
                toHist = true;
                projectionDTO.setHistory(hist.toString());
                String[] array = projectionDTO.getHistory().split(" ");
                historyNum = Integer.valueOf(array[0]);
            }
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
            if (Constant.CUSTOM.equals(String.valueOf(view.getValue()))) {
                projectionDTO.setHierarchyIndicator(StringUtils.EMPTY);
                projectionDTO.setIsCustomHierarchy(true);
                if (firstGenerated && !generated) {
                    tableLogic.clearAll();
                    tableLogic.getControlTable().getContainerDataSource().removeAllItems();
                }
                levelFilter.setEnabled(false);
                loadCustomDDLB();

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
                } else if (Constant.PRODUCT.equals(String.valueOf(view.getValue()))) {
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
        levelFilter.removeAllItems();
        levelFilter.addItem(SELECT_ONE);
        levelFilter.setNullSelectionItemId(SELECT_ONE);
        levelFilter.setValue(SELECT_ONE);
        List<Leveldto> hierarchy = null;
        if (projectionDTO.isIsCustomHierarchy()) {
            hierarchy = CommonLogic.getCustomTree(customId);
        } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projectionDTO.getHierarchyIndicator())) {
            hierarchy = CommonLogic.getCustomerHierarchy(projectionId, projectionDTO.getCustomerLevelNo());
        } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projectionDTO.getHierarchyIndicator())) {
            hierarchy = CommonLogic.getProductHierarchy(projectionId, projectionDTO.getProductLevelNo());
        }
        if (hierarchy != null) {
            for (Leveldto levelDto : hierarchy) {
                String levelFiterSid = levelDto.getTreeLevelNo() + "~" + levelDto.getHierarchyIndicator();
                String caption = Constant.LEVEL + levelDto.getTreeLevelNo() + " - " + levelDto.getLevel();
                Object itemId = levelFiterSid;
                level.addItem(itemId);
                level.setItemCaption(itemId, caption);
                levelFilter.addItem(itemId);
                levelFilter.setItemCaption(itemId, caption);
            }
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
        tableLogic.setProjectionResultsData(projectionDTO, "SPR");
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
        excelLogic = new NMSalesProjectionResultsLogic();
        excelResultBean.removeAllItems();
        projectionDTO.setFilterLevelNo(levelNo);
        projectionDTO.setFilterHierarchyNo(hierarchyNo);
        projectionDTO.setProductHierarchyNo(StringUtils.EMPTY);
        projectionDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
        projectionDTO.clearNonFetchableIndex();
        int count = excelLogic.getConfiguredSalesProjectionResultsCount(new Object(), projectionDTO, true);
        List<SalesProjectionResultsDTO> resultList = excelLogic.getConfiguredSalesProjectionResults(new Object(), 0, count, projectionDTO);
        loadDataToContainer(resultList, null);
    }

    public void addLowerLevelsForExport(Object id) {
        projectionDTO.setFilterLevelNo(0);
        projectionDTO.setFilterHierarchyNo(StringUtils.EMPTY);
        projectionDTO.setProductHierarchyNo(StringUtils.EMPTY);
        projectionDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
        int count = excelLogic.getConfiguredSalesProjectionResultsCount(id, projectionDTO, true);
        List<SalesProjectionResultsDTO> resultList = excelLogic.getConfiguredSalesProjectionResults(id, 0, count, projectionDTO);
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
        excelResultBean = new CustomTreeContainer<SalesProjectionResultsDTO>(SalesProjectionResultsDTO.class);

        Map<String, String> selection = new HashMap<String, String>();
        selection.put(Constant.FREQUENCY, frequency.getValue().toString());
        selection.put(Constant.HISTORY, history.getValue().toString());
        selection.put(Constant.ORDER, periodOrder.getValue().toString());
        selection.put("projectFrequency", history.getValue().toString());
        selection.put(Constant.SALESORUNITS, salesOrUnits.getValue().toString());
        selection.put("screen", "spr");
        selection.put(Constant.ACTUALSORPROJECTIONS, actualOrProj.getValue().toString());
        selection.put(Constant.VIEW, pivotView.getValue().toString());
        List<Date> startAndTodate = CommonUtils.getStartandTodate();
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
            if (projectionDTO.isIsFilter()) {
                levelFilter.removeValueChangeListener(levelFilterChangeOption);
                levelFilter.setValue(SELECT_ONE);
                projectionDTO.setIsFilter(false);
                tableLogic.clearAll();
                levelFilter.addValueChangeListener(levelFilterChangeOption);
            } else {
                tableLogic.clearAllExceptCurrentPage();
            }
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
            value = map.get("Pivot");
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
            value = map.get("Pivot");
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

    public void loadGroupFilter(int projectionID, int userID, int sessionID) {
        LOGGER.info("loadGroupFilter initiated ");

        LOGGER.info("loadGroupFilter ends ");
    }

    public void loadGroupFilterOntabChange() {
        loadGroupFilter(session.getProjectionId(), projectionDTO.getUserId(), projectionDTO.getSessionId());
    }

    public void groupChange(boolean groupChange, ComboBox groupFilter) {
        if (groupFilter.getValue() != null && (projectionDTO.isIsCustomHierarchy() || !projectionDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY))) {
            projectionDTO.setGroupFilter(String.valueOf(groupFilter.getValue()));
        }
        if (groupChange && firstGenerated && !generated && (projectionDTO.isIsCustomHierarchy() || !projectionDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY))) {
            tableLogic.groupChange();
        }
    }

    public void securityForButtons() {
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constant.USER_ID));
        try {
            final Map<String, AppPermission> functionPsHM = stplSecurity.getBusinessFunctionPermission(userId, GlobalConstants.getCommercialConstant() + "," + UISecurityUtil.SALES_PROJECTION_RESULTS);

            if (!(functionPsHM.get(CommonUtils.GENERATE_BUTTON_SALES) != null && ((AppPermission) functionPsHM.get(CommonUtils.GENERATE_BUTTON_SALES)).isFunctionFlag())) {
                generateBtn.setVisible(Boolean.FALSE);
                expandBtn.setVisible(Boolean.FALSE);
                collapseBtn.setVisible(Boolean.FALSE);
                newBtn.setVisible(Boolean.FALSE);
                editBtn.setVisible(Boolean.FALSE);

            }

        } catch (PortalException ex) {
            java.util.logging.Logger.getLogger(NMSalesProjectionResults.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            java.util.logging.Logger.getLogger(NMSalesProjectionResults.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void configureTabOrder() {
        frequency.focus();
        frequency.setTabIndex(1);
        history.setTabIndex(2);
        salesOrUnits.setTabIndex(3);
        actualOrProj.setTabIndex(4);
        periodOrder.setTabIndex(5);
        pivotView.setTabIndex(6);
        generateBtn.setTabIndex(7);
        resetBtn.setTabIndex(8);
        level.setTabIndex(9);
        expandBtn.setTabIndex(10);
        collapseBtn.setTabIndex(11);
        levelFilter.setTabIndex(13);
        view.setTabIndex(14);
        newBtn.setTabIndex(15);
        editBtn.setTabIndex(16);
        excelBtn.setTabIndex(17);
        graphBtn.setTabIndex(18);
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
        leftTable.setFilterGenerator(new ExtFilterGenerator() {

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
                    final ComboBox group = new ComboBox();
                    group.removeAllItems();
                    group.addItem("All Sales Groups");
                    group.setNullSelectionItemId("All Sales Groups");
                    group.setValue("All Sales Groups");
                    List<String> groupList = CommonLogic.getSalesGroupDDLB(session.getProjectionId(), projectionDTO.getUserId(), projectionDTO.getSessionId());
                    if (groupList != null && !groupList.isEmpty()) {
                        for (String groups : groupList) {
                            group.addItem(groups);
                        }
                        group.select(groupList.get(0));
                    }

                    tradingPartnerNo = CommonLogic.getTradingPartnerLevelNo(false, session.getProjectionId());
                    group.addValueChangeListener(new Property.ValueChangeListener() {
                        @Override
                        public void valueChange(Property.ValueChangeEvent event) {
                            groupChange(true, group);
                        }
                    });
                    group.setNullSelectionAllowed(true);
                    group.setNullSelectionItemId(Constant.SHOW_ALL_GROUPS);
                    group.select(Constant.SHOW_ALL_GROUPS);
                    group.setWidth("100%");
                    return group;
                }
                return null;
            }

            @Override
            public void filterRemoved(Object propertyId) {
            }

            @Override
            public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
            }

            @Override
            public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
                return null;
            }
        });
    }

    public void configure() {
        if (flag) {
            configureFields();
            configureTable();
            securityForButtons();
            flag = false;
        }
    }

}
