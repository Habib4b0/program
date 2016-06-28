/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.salesprojectionresults.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.app.galforecasting.abstractforecast.ForecastSalesProjectionResults;
import com.stpl.app.galforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.dto.SalesProjectionResultsDTO;
import com.stpl.app.galforecasting.logic.CommonLogic;
import com.stpl.app.galforecasting.salesprojectionresults.dto.ComparisonFilterGenerator;
import com.stpl.app.galforecasting.salesprojectionresults.logic.MSalesProjectionResultsLogic;
import com.stpl.app.galforecasting.salesprojectionresults.logic.NMSalesProjectionResultsLogic;
import com.stpl.app.galforecasting.salesprojectionresults.logic.SPRCommonLogic;
import com.stpl.app.galforecasting.salesprojectionresults.logic.tablelogic.MSalesProjectionResultsTableLogic;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.galforecasting.ui.ForecastUI;
import com.stpl.app.galforecasting.ui.form.lookups.CustomTreeBuild;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import static com.stpl.app.galforecasting.utils.Constant.ANNUALLY;
import static com.stpl.app.galforecasting.utils.Constant.FrequencyConstants.QUARTERS;
import static com.stpl.app.galforecasting.utils.Constant.MONTHLY;
import static com.stpl.app.galforecasting.utils.Constant.QUARTERLY;
import static com.stpl.app.galforecasting.utils.Constant.SELECT_ONE;
import static com.stpl.app.galforecasting.utils.Constant.YEAR;
import com.stpl.app.galforecasting.utils.FunctionNameUtil;
import com.stpl.app.galforecasting.utils.HeaderUtils;
import com.stpl.app.galforecasting.utils.MandatedChartUtils;
import com.stpl.app.galforecasting.utils.MandatedGraphWindow;
import com.stpl.app.galforecasting.utils.UISecurityUtil;
import com.stpl.app.model.CustomViewMaster;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import static com.stpl.app.utils.Constants.FrequencyConstants.MONTHS;
import static com.stpl.app.utils.Constants.FrequencyConstants.SEMI_ANNUALLY;
import static com.stpl.app.utils.Constants.ResourceConstants.EXCEL_IMAGE_PATH;
import static com.stpl.app.utils.Constants.ResourceConstants.GRAPH_IMAGE_PATH;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import static com.stpl.ifs.util.constants.GlobalConstants.*;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.ExtCustomTreeTable;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTreeTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 * The Class SalesProjectionResults.
 *
 * @author Vinodhini
 */
public class MSalesProjectionResults extends ForecastSalesProjectionResults {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -5336410124626810633L;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger
            .getLogger(MSalesProjectionResults.class);
    /**
     * The table control Layout.
     */
    public HorizontalLayout controlLayout;
    SessionDTO session;
    List<Leveldto> viewChangeHierarchy = new ArrayList<Leveldto>();
    private ExtCustomTreeTable exceltable;
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
    MSalesProjectionResultsTableLogic tableLogic = new MSalesProjectionResultsTableLogic();
    FreezePagedTreeTable resultsTable = new FreezePagedTreeTable(tableLogic);
    private final NMSalesProjectionResultsLogic projResLogic = new NMSalesProjectionResultsLogic();
    private final MSalesProjectionResultsLogic excelLogic = new MSalesProjectionResultsLogic();
    private CustomTreeContainer<SalesProjectionResultsDTO> excelResultBean = new CustomTreeContainer<SalesProjectionResultsDTO>(
            SalesProjectionResultsDTO.class);
    CustomTableHeaderDTO fullHeader = new CustomTableHeaderDTO();
    CustomTableHeaderDTO leftHeader = new CustomTableHeaderDTO();
    CustomTableHeaderDTO rightHeader = new CustomTableHeaderDTO();
    private SPRCommonLogic sprCommonLogic = new SPRCommonLogic();
    public CustomTreeContainer<SalesProjectionResultsDTO> resultBeanContainer = new CustomTreeContainer<SalesProjectionResultsDTO>(SalesProjectionResultsDTO.class);
    ExtPagedTreeTable leftTable;
    ExtPagedTreeTable rightTable;
    Property.ValueChangeListener levelFilterChangeOption = new Property.ValueChangeListener() {
        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            levelFilterDdlbChangeOption(false);
        }
    };
    @UiField("tableVerticalLayout")
    VerticalLayout layout;
    List<Object> headerList;
    List<CustomViewMaster> customViewList = new ArrayList<CustomViewMaster>();
    List<Leveldto> currentHierarchy = new ArrayList<Leveldto>();

    /* Map for Mapping double Header right Visible columns */
    Map<Object, Object[]> mapRightVisibleColumns = new HashMap<Object, Object[]>();

    /* Map for Mapping double Header Left Visible columns */
    Map<Object, Object[]> mapLeftVisibleColumns = new HashMap<Object, Object[]>();
    boolean firstGenerated = false;
    boolean generated = false;
    ProjectionSelectionDTO projectionDTO = new ProjectionSelectionDTO();
    /**
     * The projection id.
     */
    int projectionId;
    int customIdToSelect = 0;
    int customId = 0;
    private Integer hierarchyLevelNo = 0;
    CommonUtils commonUtils = new CommonUtils();
    String screenName = StringUtils.EMPTY;

    /**
     * Instantiates a new sales projection results.
     *
     * @param session
     */
    public MSalesProjectionResults(SessionDTO session, String screenName) throws Exception {
        super();

        LOGGER.info("SalesProjectionResults Constructor initiated ");
        this.session = session;
        this.screenName = screenName;
        projectionDTO.setSessionDTO(session);
        configureFields();
        setButtonSecurity();
        LOGGER.info("SalesProjectionResults Constructor ends ");
    }

    /**
     * Configure fields.
     */
    private void configureFields() {

        frequency.addItem(SELECT_ONE);
        frequency.setNullSelectionItemId(SELECT_ONE);
        frequency.addItem(ANNUALLY);
        frequency.addItem(SEMI_ANNUALLY);
        frequency.addItem(QUARTERLY);
        frequency.addItem(MONTHLY);
        frequency.setValue(QUARTERLY);

        history.addItem("4 Quarters");
        history.setValue("4 Quarters");
        salesOrUnits.setImmediate(true);
        salesOrUnits.addStyleName(Constant.HORIZONTAL);
        salesOrUnits.addStyleName(Constant.OPTION_GROUP_WIDTH);
        salesOrUnits.addItem(Constant.SALES_SMALL);
        salesOrUnits.addItem(Constant.UNITS_SMALL);
        salesOrUnits.addItem(Constant.BOTH);
        salesOrUnits.setValue(Constant.SALES_SMALL);
        periodOrder.setImmediate(true);
        periodOrder.addStyleName(Constant.HORIZONTAL);
        periodOrder.addStyleName(Constant.OPTION_GROUP_WIDTH);
        periodOrder.addItem(Constant.ASCENDING);
        periodOrder.addItem(Constant.DESCENDING);
        periodOrder.setValue(Constant.ASCENDING);
        actualOrProj.setImmediate(true);
        actualOrProj.addStyleName(Constant.HORIZONTAL);
        actualOrProj.addStyleName(Constant.OPTION_GROUP_WIDTH);
        actualOrProj.addItem(Constant.ACTUALS);
        actualOrProj.addItem(Constant.PROJECTIONS);
        actualOrProj.addItem(Constant.BOTH);
        actualOrProj.setValue(Constant.ACTUALS);
        pivotView.setImmediate(true);
        pivotView.addStyleName(Constant.HORIZONTAL);
        pivotView.addStyleName(Constant.OPTION_GROUP_WIDTH);
        pivotView.addItem(Constant.PERIOD);
        pivotView.addItem("Variable");
        pivotView.setValue(Constant.PERIOD);

        excelBtn.setIcon(excelExportImage);
        graphBtn.setIcon(graphImage);
        view.setImmediate(true);
        view.addStyleName(Constant.HORIZONTAL);
        view.addStyleName(Constant.OPTION_GROUP_WIDTH);
        view.addItem(Constant.CUSTOMER_SMALL);
        view.addItem(Constant.PRODUCT);
        view.addItem(Constant.CUSTOM);
        view.setValue(Constant.CUSTOMER_SMALL);

        level.addItem(SELECT_ONE);
        level.setNullSelectionItemId(SELECT_ONE);
        level.setValue(SELECT_ONE);
        levelFilter.addItem(SELECT_ONE);
        levelFilter.setNullSelectionItemId(SELECT_ONE);
        levelFilter.setValue(SELECT_ONE);
        customDdlb.addItem(SELECT_ONE);
        customDdlb.setNullSelectionItemId(SELECT_ONE);
        customDdlb.setValue(SELECT_ONE);

        excelBtn.setIcon(excelExportImage);
        graphBtn.setIcon(graphImage);

        editBtn.setEnabled(false);
        panelpivot.setCaption("Period Pivot View");
        initializeResultTable();
        if (loadProjectionSelection()) {
            configureResultTable();
        }
        if (Constant.EDIT_SMALL.equals(session.getAction()) || Constant.VIEW.equals(session.getAction())) {
            setProjectionSelection();
        }
        addResultTable();
        configureTabOrder();
    }

    /**
     * Configure Result Table.
     */
    private void configureResultTable() {
        tableLogic.setPageLength(20);
        tableLogic.sinkItemPerPageWithPageLength(false);
        List<Integer> pagelength = CommonLogic.getPageNumber();
        tableLogic.getControlConfig().setPageLengthsAndCaptions(pagelength);
        tableLogic.setTreeNodeMultiClick(false);
        fullHeader = new CustomTableHeaderDTO();
        leftHeader = HeaderUtils.getSalesProjectionResultsLeftTableColumns(projectionDTO, fullHeader);
        projectionDTO.setProjTabName("SPR");
        rightHeader = HeaderUtils.getSalesProjectionResultsRightTableColumns(projectionDTO, fullHeader);
        projectionDTO.setProjTabName(StringUtils.EMPTY);
        resultBeanContainer = new CustomTreeContainer<SalesProjectionResultsDTO>(SalesProjectionResultsDTO.class);
        resultBeanContainer.setColumnProperties(fullHeader.getProperties());
        tableLogic.setContainerDataSource(resultBeanContainer);

        leftTable = resultsTable.getLeftFreezeAsTable();
        rightTable = resultsTable.getRightFreezeAsTable();

        leftTable.setImmediate(true);
        rightTable.setImmediate(true);
        resultsTable.setHeight("650px");
        leftTable.setHeight("650px");
        rightTable.setHeight("650px");
        leftTable.setColumnWidth(Constant.GROUP, 300);
        leftTable.setDoubleHeaderColumnWidth(Constant.GROUP, 300);
        leftTable.setVisibleColumns(leftHeader.getSingleColumns().toArray());
        leftTable.setColumnHeaders(leftHeader.getSingleHeaders().toArray(new String[leftHeader.getSingleHeaders().size()]));
        leftTable.setFilterBarVisible(true);
        leftTable.setFilterDecorator(new ExtDemoFilterDecorator());
        leftTable.setFilterGenerator(new ComparisonFilterGenerator(projectionDTO, tableLogic));
        rightTable.setVisibleColumns(rightHeader.getSingleColumns().toArray());
        rightTable.setColumnHeaders(rightHeader.getSingleHeaders().toArray(new String[rightHeader.getSingleHeaders().size()]));
        for (Object propertyId : rightTable.getVisibleColumns()) {
            rightTable.setColumnAlignment(propertyId, ExtCustomTable.Align.RIGHT);
        }

        resultsTable.setDoubleHeaderVisible(true);

        rightTable
                .setDoubleHeaderVisibleColumns(rightHeader.getDoubleColumns().toArray());
        rightTable
                .setDoubleHeaderColumnHeaders(rightHeader.getDoubleHeaders().toArray(new String[rightHeader.getDoubleHeaders().size()]));
        resultsTable.getRightFreezeAsTable().setDoubleHeaderMap(rightHeader.getDoubleHeaderMaps());
        for (int i = 0; i < rightHeader.getDoubleColumns().size(); i++) {
            rightTable.setColumnAlignment(rightHeader.getDoubleColumns().get(i), ExtCustomTable.Align.CENTER);
        }
    }

    /**
     * Load history.
     *
     * @param frequency the frequency
     * @return the list
     */
    protected final List<String> loadHistory(String frequency, String period) {
        LOGGER.info("Entering loadHistory method");
        List<String> history = new ArrayList<String>();
        history = session.getFrequencyAndQuaterValue(frequency);
        Integer endValue = 0;
        if (history == null || history.isEmpty()) {
            Map<String, Integer> historyEndDetails = commonUtils.getHistoryEndDetails(session, frequency);
            endValue = commonUtils.getProjections(session.getForecastDTO().getHistoryStartDate(), commonUtils.getDate(historyEndDetails.get("historyEndMonth"), historyEndDetails.get("historyEndYear")), frequency);

            history = CommonUtils.getHistoryDdlbList(endValue, frequency, period);
            session.addFrequencyAndQuater(frequency, history);
        }
        LOGGER.info("End of loadHistory method");
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
            history.addItem(SELECT_ONE);
            history.setNullSelectionItemId(SELECT_ONE);
        } else {
            history.removeAllItems();
            history.addItem(SELECT_ONE);
            history.setNullSelectionItemId(SELECT_ONE);
            String historyConstant = StringUtils.EMPTY;
            if (Constant.ANNUALLY.equals(String.valueOf(frequency.getValue()))) {
                historyBean.addAll(loadHistory(ANNUALLY, YEAR));
                if (historyBean.size() >= 1) {
                    historyConstant = "1 Year";
                } else {
                    historyConstant = SELECT_ONE;
                }
            } else if (Constant.SEMI_ANNUALLY.equals(String.valueOf(frequency
                    .getValue()))) {
                historyBean.addAll(loadHistory(SEMI_ANNUALLY.getConstant(), "Semi-Annual"));
                if (historyBean.size() >= 2) {
                    historyConstant = "2 Semi-Annual";
                } else {
                    historyConstant = SELECT_ONE;
                }
            } else if (QUARTERLY.equals(
                    String.valueOf(frequency.getValue()))) {
                historyBean.addAll(loadHistory(QUARTERLY, QUARTERS.getConstant()));
                if (historyBean.size() >= 4) {
                    historyConstant = "4 Quarters";
                } else {
                    historyConstant = SELECT_ONE;
                }

            } else if (MONTHLY.equals(
                    String.valueOf(frequency.getValue()))) {
                historyBean.addAll(loadHistory(MONTHLY, MONTHS.getConstant()));
                if (historyBean.size() >= 12) {
                    historyConstant = "12 Months";
                } else {
                    historyConstant = SELECT_ONE;
                }
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
     * Generate btn logic.
     *
     * @param event the event
     */
    @Override
    public void generateButtonLogic() {
        generated = true;
        firstGenerated = true;
        projectionDTO.setCustomFlag(false);
        if (loadProjectionSelection()) {
            layout.removeComponent(resultsTable);
            layout.removeComponent(controlLayout);
            if ("variable".equalsIgnoreCase(pivotView.getValue().toString())) {
                panelpivot.setCaption("Variable Pivot View");
            } else {
                panelpivot.setCaption("Period Pivot View");
            }
            tableLogic = new MSalesProjectionResultsTableLogic();
            resultsTable = new FreezePagedTreeTable(tableLogic);
            initializeResultTable();
            configureResultTable();
            addResultTable();
            generateLogic();
        } else {
            MessageBox.showPlain(Icon.INFO, "Error", "Please select a frequency and/or history and try again.", ButtonId.OK);
        }
        generated = false;
        }

    public void initializeResultTable() {

        resultsTable.markAsDirty();
        resultsTable.setSelectable(false);
        resultsTable.setImmediate(true);
        resultsTable.setSplitPosition(splitPosition, Sizeable.Unit.PIXELS);
        resultsTable.setMinSplitPosition(minSplitPosition, Sizeable.Unit.PIXELS);
        resultsTable.setMaxSplitPosition(maxSplitPosition, Sizeable.Unit.PIXELS);
        resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
    }

    public boolean loadProjectionSelection() {
        boolean toRet = false;
        int historyNum = 0;
        Object freq = frequency.getValue();
        boolean toFreq = false;
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
                if (freq.equals(QUARTERLY)) {
                    historyNum = Integer.valueOf(String.valueOf(hist).replace("Quarter", StringUtils.EMPTY).replace(Constant.S_SMALL, StringUtils.EMPTY).trim());
                } else if (freq.equals(SEMI_ANNUALLY)) {
                    historyNum = Integer.valueOf(String.valueOf(hist).replace("Semi-Annual Periods", StringUtils.EMPTY).trim());

                } else if (freq.equals(MONTHLY)) {
                    historyNum = Integer.valueOf(String.valueOf(hist).replace("Month", StringUtils.EMPTY).replace(Constant.S_SMALL, StringUtils.EMPTY).trim());

                } else if (freq.equals(ANNUALLY)) {
                    historyNum = Integer.valueOf(String.valueOf(hist).replace(Constant.YEAR, StringUtils.EMPTY).replace(Constant.S_SMALL, StringUtils.EMPTY).trim());
                }
                projectionDTO.setHistory(hist.toString());
                projectionDTO.setProjection(hist.toString());
            }
        }
        if (toFreq && toHist) {
            toRet = true;
            projectionId = session.getProjectionId();
            projectionDTO.setScreenName(screenName);
            projectionDTO.setHistoryNum(historyNum);
            projectionDTO.setUserId(Integer.valueOf(session.getUserId()));
            projectionDTO.setSessionId(Integer.valueOf(session.getSessionId()));
            projectionDTO.setActualsOrProjections(actualOrProj.getValue().toString());
            projectionDTO.setProjectionId(projectionId);
            projectionDTO.setSalesOrUnit(salesOrUnits.getValue().toString());
            projectionDTO.setProjectionOrder(periodOrder.getValue().toString());
            projectionDTO.setPivotView(pivotView.getValue().toString());
            projectionDTO.setCustomId(customId);
            projectionDTO.setView(view.getValue().toString());

            projectionDTO.setCustomerLevelNo(Integer.valueOf(session.getCustomerLevelNumber()));
            projectionDTO.setProductLevelNo(Integer.valueOf(session.getProductLevelNumber()));
            projectionDTO.setStartDate(session.getFromDate());
            projectionDTO.setStartDate(session.getToDate());
            if (session.getFromDate() != null && session.getToDate() != null) {
                projectionDTO.setProjectionNum(CommonUtils.getProjections(new Date(), session.getToDate(), projectionDTO.getFrequency()));
            }
            projectionDTO.setForecastDTO(session.getForecastDTO());
            viewChange(false);

        }

        return toRet;
    }

    public void viewChange(boolean viewChange) {
        projectionDTO.setIsCustomHierarchy(false);
        customDdlb.setEnabled(false);
        editBtn.setEnabled(false);
        final ExtFilterTreeTable leftTable = resultsTable
                .getLeftFreezeAsTable();
        if (view.getValue() != null) {
            if (Constant.CUSTOM.equals(String.valueOf(view.getValue()))) {
                projectionDTO.setHierarchyIndicator(StringUtils.EMPTY);
                projectionDTO.setIsCustomHierarchy(true);
                expandBtn.setEnabled(true);
                collapseBtn.setEnabled(true);
                level.setValue(SELECT_ONE);
                level.setEnabled(false);
                levelFilter.setValue(SELECT_ONE);
                levelFilter.setEnabled(false);
                projectionDTO.setCustomFlag(true);
                projectionDTO.setView(Constant.CUSTOM);
                if (firstGenerated && !generated) {
                    tableLogic.clearAll();
                    tableLogic.getControlTable().getContainerDataSource().removeAllItems();
                }
                customIdToSelect = 0;
                loadCustomDDLB();
                level.setEnabled(false);
                levelFilter.setEnabled(false);
                leftTable.setColumnHeader("levelValue", Constant.CUSTOMER_SMALL);
                leftTable.setFilterBarVisible(true);
                leftTable.setFilterDecorator(new ExtDemoFilterDecorator());
                leftTable.setFilterGenerator(new ComparisonFilterGenerator(projectionDTO, tableLogic));
            } else {

                customIdToSelect = customId;
                customDdlb.setValue(SELECT_ONE);
                expandBtn.setEnabled(true);
                collapseBtn.setEnabled(true);
                projectionDTO.setIsCustomHierarchy(false);
                levelFilter.setEnabled(true);
                projectionDTO.setCustomFlag(false);
                level.setEnabled(true);
                if (Constant.CUSTOMER_SMALL.equals(String.valueOf(view.getValue()))) {
                    projectionDTO.setHierarchyIndicator(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
                    projectionDTO.setView(Constant.CUSTOMER_SMALL);
                    if (viewChange && firstGenerated) {
                        leftTable.setColumnHeader("levelValue", Constant.CUSTOMER_SMALL);
                        generateLogic();

                    }
                } else if (Constant.PRODUCT.equals(String.valueOf(view.getValue()))) {
                    projectionDTO.setHierarchyIndicator(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
                    projectionDTO.setView(Constant.PRODUCT);
                    if (viewChange && firstGenerated) {
                        leftTable.setColumnHeader("levelValue", Constant.BRAND_CAPS);
                        generateLogic();
                    }
                }
            }
        }
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
                customDdlb.setValue(SELECT_ONE);
            } else {
                level.setEnabled(true);
                customDdlb.select(customIdToSelect);
            }
        }
        LOGGER.info("loadCustomDDLB ends ");
    }

    public void setCurrentHierarchy(List<Leveldto> currentHierarchy) {
        this.currentHierarchy = currentHierarchy;
    }

    public void generateLogic() {
        levelFilter.removeValueChangeListener(levelFilterChangeOption);
        loadLevelAndFilterValue();
        levelFilter.addValueChangeListener(levelFilterChangeOption);
        tableLogic.clearAll();
        tableLogic.setRefresh(false);
        projectionDTO.setIsFilter(false);
        loadResultTable(0, StringUtils.EMPTY, true);
        tableLogic.setRefresh(true);
    }

    /**
     * Add Result Table.
     */
    @SuppressWarnings("serial")
    private void addResultTable() {
        layout.addComponent(resultsTable);
        controlLayout = tableLogic.createControls();
        controlLayout.setMargin(true);
        tableLogic.sinkItemPerPageWithPageLength(false);
        layout.addComponent(controlLayout);
    }

    public void levelFilterDdlbChangeOption(boolean excelExport) {
        LOGGER.info("levelFilterDdlbChangeOption inititated");
        List<Object> levelHierarchy = CommonLogic.getLevelNoAndHierarchyNo(levelFilter.getValue(), true);

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
            projectionDTO.setCustomFlag(false);
            tableLogic.clearAll();
            loadResultTable(levelNo, hierarchyNo, false);
        }
        LOGGER.info("levelFilterDdlbChangeOption ends");
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

        int count = projResLogic.getConfiguredSalesProjectionResultsCountMandated(new Object(), projectionDTO, true);
        List<SalesProjectionResultsDTO> resultList = projResLogic.getConfiguredSalesProjectionResultsMandated(new Object(), 0, count, projectionDTO);

        loadDataToContainer(resultList, null);
    }

    /**
     * Loads the results.
     */
    @SuppressWarnings("serial")
    private void loadResultTable(int levelNo, String hierarchyNo, boolean flag) {
        projectionDTO.setFilterLevelNo(levelNo);
        projectionDTO.setFilterHierarchyNo(hierarchyNo);
        projectionDTO.setProductHierarchyNo(StringUtils.EMPTY);
        projectionDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
        projectionDTO.setFilterDdlb(false);

        tableLogic.setRefresh(false);
        projectionDTO.clearNonFetchableIndex();
        tableLogic.setProjectionResultsData(projectionDTO, flag, "SPR");
        tableLogic.setRefresh(true);
        leftTable.setFilterBarVisible(true);
        leftTable.setFilterDecorator(new ExtDemoFilterDecorator());
        leftTable.setFilterGenerator(new ComparisonFilterGenerator(projectionDTO, tableLogic));
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

    public void addLowerLevelsForExport(Object id) {
        projectionDTO.setFilterLevelNo(0);
        projectionDTO.setFilterHierarchyNo(StringUtils.EMPTY);
        projectionDTO.setProductHierarchyNo(StringUtils.EMPTY);
        projectionDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
        int count = projResLogic.getConfiguredSalesProjectionResultsCountMandated(id, projectionDTO, true);
        List<SalesProjectionResultsDTO> resultList = projResLogic.getConfiguredSalesProjectionResultsMandated(id, 0, count, projectionDTO);
        loadDataToContainer(resultList, id);
    }

    @Override
    public void expandButtonLogic() {
        if (!Constant.NULL.equals(String.valueOf(level.getValue()))) {
            tableLogic.setRefresh(false);
            expandCollapseLevelOption(true, level.getValue());
            tableLogic.setRefresh(true);
        } else {
            AbstractNotificationUtils.getErrorNotification("No Level Selected", "Please select a Level from the drop down.");
        }
    }

    @Override
    public void collapseButtonLogic() {
        tableLogic.setRefresh(false);
        expandCollapseLevelOption(false, level.getValue());
        tableLogic.setRefresh(true);
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

    @Override
    public void newCustomHierarchyLogic() {
        LOGGER.info("newCustomHierarchhy clickEvent method starts");

        Object obj = null;
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

    /**
     * value change of view.
     *
     * @param event the event
     */
    @Override
    public void customDdlbChangeOptionLogic() {
        LOGGER.info("customDdlbChangeOption ValueChangeEvent initiated ");
        customId = CommonLogic.customDdlbOptionChange(customDdlb, editBtn);
        projectionDTO.setCustomId(customId);
        if (String.valueOf(customDdlb.getValue()) != null || !SELECT_ONE.equalsIgnoreCase(String.valueOf(customDdlb.getValue()))) {
            editBtn.setEnabled(true);
            level.setEnabled(true);
            expandBtn.setEnabled(true);
            collapseBtn.setEnabled(true);

            if (!generated && firstGenerated) {
                projectionDTO.setCustomFlag(true);
                generateLogic();
            }
        }
        LOGGER.info("customDdlbChangeOption ValueChangeEvent ends ");
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

    @UiHandler(Constant.VIEW)
    public void viewChangeOption(Property.ValueChangeEvent event) {
        viewChange(true);
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
        ForecastUI.EXCEL_CLOSE=true;
        ExcelExport exp = new ExcelExport(new ExtCustomTableHolder(exceltable), "Sales Projection Results", "Sales Projection Results", "Sales Projection Results.xls", false);
        exp.export();
        layout.removeComponent(exceltable);
    }

    private void configureExcelResultTable() {
        excelResultBean = new CustomTreeContainer<SalesProjectionResultsDTO>(SalesProjectionResultsDTO.class);
        excelResultBean.setColumnProperties(fullHeader.getProperties());
        exceltable = new ExtFilterTreeTable();
        layout.addComponent(exceltable);
        exceltable.setVisible(false);
        exceltable.setRefresh(false);
        exceltable.setContainerDataSource(excelResultBean);
        exceltable.setVisibleColumns(fullHeader.getSingleColumns().toArray());
        exceltable.setColumnHeaders(fullHeader.getSingleHeaders().toArray(new String[fullHeader.getSingleHeaders().size()]));
        exceltable.setDoubleHeaderVisible(true);
        exceltable
                .setDoubleHeaderVisibleColumns(fullHeader.getDoubleColumns().toArray());
        exceltable
                .setDoubleHeaderColumnHeaders(fullHeader.getDoubleHeaders().toArray(new String[fullHeader.getDoubleHeaders().size()]));

        exceltable.setDoubleHeaderMap(fullHeader.getDoubleHeaderMaps());
        exceltable.setRefresh(true);
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
        for (SalesProjectionResultsDTO dto : resultBeanContainer.getItemIds()) {
            if (dto.getLevelNo() != null) {
                chartList.add(dto);
            }
        }

        final MandatedChartUtils chart = new MandatedChartUtils(chartList, String.valueOf(frequency.getValue()), String.valueOf(history.getValue()), fullHeader, "Sales Projection Results", projectionDTO);
        final MandatedGraphWindow salesGraphWindow = new MandatedGraphWindow(chart.getChart(), "Sales Projection Results");
        UI.getCurrent().addWindow(salesGraphWindow);
        salesGraphWindow.focus();
        LOGGER.info("graphExportLogic method ends");
    }

    public void saveSPResults() {
        try {
            LOGGER.info("saveSPResults method starts");
            final CommonLogic logic = new CommonLogic();
            Map map = new HashMap();
            map.put(Constant.FREQUENCY_SMALL, frequency.getValue() != null ? frequency.getValue().toString() : StringUtils.EMPTY);
            map.put(Constant.HISTORY_CAPS, history.getValue() != null ? history.getValue().toString() : StringUtils.EMPTY);
            map.put("Sales/Units", salesOrUnits.getValue().toString());
            map.put("Actuals/Projections", actualOrProj.getValue().toString());
            map.put(Constant.PERIOD_ORDER, periodOrder.getValue().toString());
            map.put("Pivot", pivotView.getValue().toString());
            sprCommonLogic.saveMandatedSRPSelection(map, session.getProjectionId(), "Sales Projection Results");
            LOGGER.info("saveSPResults method ends");
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    private void setProjectionSelection() {
        Map<Object, Object> map = CommonLogic.getMProjectionSelection(projectionId, "Sales Projection Results");
        if (map != null && !map.isEmpty()) {
            Object value = map.get(Constant.FREQUENCY_SMALL);
            if (value != null) {
                frequency.setValue(String.valueOf(value));
            }
            value = map.get(Constant.HISTORY_CAPS);
            if (value != null) {
                history.setValue(String.valueOf(value));
            }
            value = map.get(Constant.PERIOD_ORDER);
            if (value != null) {
                periodOrder.setValue(String.valueOf(value));
            }
            value = map.get("Pivot");
            if (value != null) {
                pivotView.setValue(String.valueOf(value));
            }
            value = map.get("Actuals/Projections");
            if (value != null) {
                actualOrProj.setValue(String.valueOf(value));
            }
            value = map.get("Sales/Units");
            if (value != null) {
                salesOrUnits.setValue(String.valueOf(value));
            }
        }
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

    public void loadLevelAndFilterValue() {
        LOGGER.info("loadLevelAndFilterValue initiated ");
        level.setEnabled(true);
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
                String caption = Constant.LEVEL+ levelDto.getTreeLevelNo() + " - " + levelDto.getLevel();
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
     *
     *
     * @param moduleAndTabName
     * @throws Exception
     */
    public void setButtonSecurity() throws Exception {
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constant.USER_ID));
        final Map<String, AppPermission> functionPsHM = stplSecurity.getBusinessFunctionPermission(userId, getGovernmentConstant() + "," + UISecurityUtil.SALES_PROJECTION_RESULTS);
        if (functionPsHM.get(FunctionNameUtil.GENERATE) != null && !((AppPermission) functionPsHM.get(FunctionNameUtil.GENERATE)).isFunctionFlag()) {
            generateBtn.setVisible(Boolean.FALSE);
            expandBtn.setVisible(Boolean.FALSE);
            collapseBtn.setVisible(Boolean.FALSE);
            newBtn.setVisible(Boolean.FALSE);
            editBtn.setVisible(Boolean.FALSE);
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
        levelFilter.setTabIndex(12);
        view.setTabIndex(13);
        newBtn.setTabIndex(14);
        editBtn.setTabIndex(15);
        excelBtn.setTabIndex(16);
        graphBtn.setTabIndex(17);
    }
}
