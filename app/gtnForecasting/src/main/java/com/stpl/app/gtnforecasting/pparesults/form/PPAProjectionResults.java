/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.pparesults.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.logic.Utility;
import com.stpl.app.gtnforecasting.pparesults.dto.PPAProjectionResultsDTO;
import com.stpl.app.gtnforecasting.pparesults.dto.PPAResultsGenerator;
import com.stpl.app.gtnforecasting.pparesults.logic.PPAProjectionResultsLogic;
import com.stpl.app.gtnforecasting.pparesults.logic.tablelogic.PPAResultsTableLogic;
import com.stpl.app.gtnforecasting.salesprojectionresults.logic.SPRCommonLogic;
import com.stpl.app.gtnforecasting.projectionvariance.logic.NMProjectionVarianceLogic;
import com.stpl.app.gtnforecasting.salesprojection.utils.SalesUtils;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.ui.ForecastUI;
import com.stpl.app.gtnforecasting.ui.form.lookups.CustomTreeBuild;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.CommonUtil;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.SELECT_ONE;
import com.stpl.app.gtnforecasting.utils.FunctionNameUtil;
import com.stpl.app.gtnforecasting.utils.HeaderUtils;
import com.stpl.app.gtnforecasting.utils.NmPPAGraphWindow;
import com.stpl.app.gtnforecasting.utils.PPAChart;
import com.stpl.app.gtnforecasting.utils.UISecurityUtil;
import com.stpl.app.model.CustomViewMaster;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.utils.Constants;
import static com.stpl.app.utils.Constants.ResourceConstants.EXCEL_IMAGE_PATH;
import static com.stpl.app.utils.Constants.ResourceConstants.GRAPH_IMAGE_PATH;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.converters.DataFormatConverter;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import static com.stpl.ifs.util.constants.GlobalConstants.*;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.ExtCustomTreeTable;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
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
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import com.stpl.ifs.ui.extfilteringtable.FreezePagedTreeTable;
import com.stpl.ifs.ui.util.NumericConstants;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author gopinath
 */
public class PPAProjectionResults extends CustomComponent implements View {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -5336410124626810633L;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(PPAProjectionResults.class);
    /**
     * The frequency.
     */
    @UiField("frequency")
    public ComboBox frequency;
    /**
     * The period order.
     */
    @UiField("periodOrder")
    public OptionGroup periodOrder;
    /**
     * The actual or proj.
     */
    @UiField("actualOrProj")
    public OptionGroup actualOrProj;
    /**
     * The pivot view.
     */
    @UiField("pivotView")
    public OptionGroup pivotView;
    /**
     * The view.
     */
    @UiField("view")
    public OptionGroup view;
    /**
     * The excel btn.
     */
    @UiField("excelBtn")
    public Button excelBtn;
    /**
     * The graph btn.
     */
    @UiField("graphBtn")
    public Button graphBtn;
    /**
     * The history.
     */
    @UiField("history")
    public ComboBox history;
    /**
     * The level.
     */
    @UiField("level")
    public ComboBox level;
    /**
     * The level filter.
     */
    @UiField("levelFilter")
    public ComboBox levelFilter;
    /**
     * The custom ddlb.
     */
    @UiField("customDdlb")
    public ComboBox customDdlb;
    /**
     * The reset.
     */
    @UiField("resetBtn")
    public Button reset;
    /**
     * The reset.
     */
    @UiField("expandBtn")
    public Button expandBtn;
    /**
     * The reset.
     */
    @UiField("collapseBtn")
    public Button collapseBtn;
    /**
     * the generate button.
     */
    @UiField("generateBtn")
    public Button generateBtn;
    /**
     * the verticalLayout.
     */
    @UiField("verticalLayout")
    public VerticalLayout verticalLayout;
    @UiField("editBtn")
    public Button editBtn;
    @UiField("newBtn")
    public Button newBtn;
    @UiField("resultsCaption")
    public Panel resultsCaption;
    @UiField("group")
    public ComboBox group;
    @UiField("groupLabel")
    public Label groupLabel;
    @UiField("detailsBtn")
    public Button detailsBtn;
    int tradingPartnerNo = 0;

    final StplSecurity stplSecurity = new StplSecurity();

    final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constant.USER_ID));

    /**
     * The excel export image.
     */
    private final Resource excelExportImage = new ThemeResource(EXCEL_IMAGE_PATH.getConstant());
    /**
     * The graph image.
     */
    private final Resource graphImage = new ThemeResource(GRAPH_IMAGE_PATH.getConstant());
    /* The bean for loading history drop down */
    /**
     * The history bean.
     */
    /**
     * The period table id.
     */
    PPAResultsTableLogic tableLogic = new PPAResultsTableLogic();
    FreezePagedTreeTable periodTableId;
    /**
     * The result bean.
     */
    private ExtTreeContainer<PPAProjectionResultsDTO> resultBean = new ExtTreeContainer<PPAProjectionResultsDTO>(PPAProjectionResultsDTO.class,ExtContainer.DataStructureMode.MAP);
    List<Leveldto> viewChangeHierarchy = new ArrayList<Leveldto>();
    ExtCustomTreeTable excelTable;
    /**
     * Instantiates a new SALES_SMALL projection results.
     */
    ExtFilterTreeTable leftTable;
    ExtFilterTreeTable rightTable;
    Integer projectionId;
    CustomTableHeaderDTO ridhtdto;
    CustomTableHeaderDTO leftdto;
    SessionDTO session;
    private int hierarchyLevelNo = 1;
    /**
     * The map right visible columns.
     */
    /**
     * The map left visible columns.
     */
    /**
     * The split position.
     */
    private final float splitPosition = 300;
    /**
     * The max split position.
     */
    private final float maxSplitPosition = 1000;
    /**
     * The min split position.
     */
    private final float minSplitPosition = 200;
    int customIdToSelect = 0;
    boolean customHierarchy = false;
    int customId = 0;
    List<CustomViewMaster> customViewList = new ArrayList<CustomViewMaster>();
    ProjectionSelectionDTO selection = new ProjectionSelectionDTO();
    boolean isGenerated;
    PPAProjectionResultsLogic logic = new PPAProjectionResultsLogic();
    public static final DataFormatConverter percentFormat = new DataFormatConverter("#,##0.00", DataFormatConverter.INDICATOR_PERCENT);
    ExtTreeContainer<PPAProjectionResultsDTO> excelContainer = new ExtTreeContainer<>(PPAProjectionResultsDTO.class,ExtContainer.DataStructureMode.MAP);
    String[] pivotHeaderForActuals = {"Group", "Discount $ Per Unit Actuals", "Discount % Actuals", "Unit Volume Actulas", "Total Discount Amount Actulas"};
    String[] pivotHeaderForProjections = {"Group", "Discount $ Per Unit Projections", "Discount % Projections", "Unit Volume Projections", "Total Discount Amount Projections"};
    String[] pivotHeaderForBoth = {"Group", "Discount $ Per Unit Actuals", "Discount $ Per Unit Projections", "Discount % Actuals", "Discount % Projections", "Unit Volume Actulas", "Unit Volume Projections", "Total Discount Amount Actulas", "Total Discount Amount Projections"};
    Property.ValueChangeListener levelFilterChangeOption = new Property.ValueChangeListener() {
        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            try {
                levelFilterDdlbChangeOption(false);
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
    };
    List historyList = null;
    boolean firstGenerated = false;
    boolean generated = false;
    CustomTableHeaderDTO fullHeader = new CustomTableHeaderDTO();
    PPADetailsLookup ppaDetailsLookup = null;

    /**
     * Instantiates a new PPA projection results.
     */
    public PPAProjectionResults(SessionDTO session) {
        LOGGER.debug("PPAProjectionResults Constructor initiated ");
        this.session = session;
        projectionId = session.getProjectionId();
        this.session = session;
        selection.setScreenName(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED);
        selection.setTabName("PPA Projection Results");
        tableLogic.setSession(session);
        tableLogic.setTempPageLength(NumericConstants.TWENTY);
        tableLogic.setPageLength(NumericConstants.TWENTY);
        List<Integer> pagelength = CommonLogic.getPageNumber();
        tableLogic.getControlConfig().setPageLengthsAndCaptions(pagelength);
        periodTableId = new FreezePagedTreeTable(tableLogic);
        LOGGER.debug("PPAProjectionResults Constructor ends ");
    }

    public Component getContent() {
        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(Clara.create(getClass().getResourceAsStream("/PPAProjectionResults.xml"), this));
        configureFields();
        Panel panel = new Panel();
        panel.setContent(layout);
        return panel;
    }

    /**
     * Configure fields.
     */
    private void configureFields() {
        try {
            LOGGER.debug("Entering  configurefields method");
            level.addStyleName(Constant.POPUPCONTENTCOMBOSIZE);
            level.setImmediate(true);
            periodTableId.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
            selection.setProjectionId(projectionId);
            resultsCaption.setImmediate(true);
            rightTable = periodTableId.getRightFreezeAsTable();
            leftTable = periodTableId.getLeftFreezeAsTable();
            history.removeAllItems();
            history.addItem(SELECT_ONE);
            history.setNullSelectionItemId(SELECT_ONE);
            history.setValue(SELECT_ONE);
            history.setImmediate(true);
            history.focus();
            verticalLayout.addComponent(periodTableId);
            selection.setProjectionId(projectionId);
            selection.setUserId(Integer.valueOf(session.getUserId()));
            selection.setUserId(Integer.valueOf(session.getUserId()));
            HorizontalLayout controls = periodTableId.createControls();
            HorizontalLayout cssControls = CommonLogic.getResponsiveControls(controls);
            verticalLayout.addComponent(cssControls);
            frequency.addItem(SELECT_ONE);
            frequency.setNullSelectionItemId(SELECT_ONE);
            frequency.addItem(Constant.ANNUALLY);
            frequency.addItem(Constant.SEMIANNUALLY);
            frequency.addItem(Constant.QUARTERLY);
            frequency.addItem(Constant.MONTHLY);
            frequency.setValue(Constant.QUARTERLY);
            periodOrder.addItem(Constant.ASCENDING);
            periodOrder.addItem(Constant.DESCENDING);
            periodOrder.setValue(Constant.ASCENDING);
            actualOrProj.addItem(Constant.ACTUALS);
            actualOrProj.addItem(Constant.PROJECTIONS);
            actualOrProj.addItem(Constant.BOTH);
            actualOrProj.setValue(Constant.ACTUALS);
            pivotView.addItem(Constant.PERIOD);
            pivotView.addItem(Constant.VARIABLE);
            pivotView.setValue(Constant.PERIOD);
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
            periodOrder.addStyleName(Constant.HORIZONTAL);
            pivotView.addStyleName(Constant.HORIZONTAL);
            actualOrProj.addStyleName(Constant.HORIZONTAL);
            view.addStyleName(Constant.HORIZONTAL);
            excelBtn.setIcon(excelExportImage);
            graphBtn.setIcon(graphImage);
            group.setImmediate(true);
            group.removeAllItems();
            group.setNullSelectionAllowed(Boolean.FALSE);
            setProjectionSelection();
            group.setVisible(false);
            groupLabel.setVisible(false);
            LOGGER.debug("End of configurefields method");
            group.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    try {
                        groupChange(true);
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
            });
            loadProjectionSelection();
            configureTable();
            security();

        } catch (Exception ex) {

            LOGGER.error(ex);
        }

    }

    public void groupChange(boolean groupChange) {
        if (group.getValue() != null && (selection.isIsCustomHierarchy() || !selection.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY))) {
            selection.setGroupFilter(String.valueOf(group.getValue()));
        }
        if (groupChange && firstGenerated && !generated && (selection.isIsCustomHierarchy() || !selection.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY))) {
            tableLogic.groupChange();
        }
    }

    /**
     * Load history.
     *
     * @param frequency the frequency
     * @return the list
     */
    protected final List<String> loadHistory(String frequency, String period)  {
        LOGGER.debug("Entering loadHistory method");
        List<String> history = new ArrayList<String>();
        history = session.getFrequencyAndQuaterValue(frequency);
        Integer endValue = 0;
        if (history == null || history.isEmpty()) {
            endValue = CommonUtils.getProjections(session.getFromDate(), new Date(), frequency);
            history = CommonUtils.getHistoryDdlbList(endValue, frequency, period);
        }

        session.addFrequencyAndQuater(frequency, history);
        LOGGER.debug("End of loadHistory method");
        return history;
    }

    /**
     * Load frequency.
     *
     * @param event the event
     */
    @UiHandler("frequency")
    public void loadFrequency(Property.ValueChangeEvent event) {
        LOGGER.debug("PPAProjectionResults ValueChangeEvent initiated with frequency -->" + frequency.getValue());
        CommonUtils.frequenceValueChange(frequency.getValue(), history, session);
        LOGGER.debug("PPAProjectionResults ValueChangeEvent ends ");
    }

    /**
     * Generate button logic.
     *
     * @param event the event
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    @UiHandler("generateBtn")
    public void generateButtonLogic(Button.ClickEvent event) throws SystemException, PortalException{
        LOGGER.debug("Entering Generate button Logic");
        session.setIsPPAUpdated(true);
     try {       
         if(!Constant.VIEW.equals(session.getAction())){
             CommonUtil.getInstance().waitsForOtherThreadsToComplete(session.getFutureValue(Constant.PPA_PROCEDURE_CALL));
             logic.callPPAGenerateProcedures(selection);
             CommonUtil.getInstance().waitsForOtherThreadsToComplete(session.getFutureValue(Constant.PRC_PPA_GENERATE_CALL));
         }
            if (frequency.getValue() != null && history.getValue() != null) {
                generated = true;
                firstGenerated = true;

                if (pivotView.getValue().toString().equals(Constant.VARIABLE)) {
                    resultsCaption.setCaption("Pivot View");
                } else {
                    resultsCaption.setCaption("Period View");
                }

                isGenerated = Boolean.TRUE;
                tableLogic.setFirstGenerated(false);
                periodTableId.constructRightFreeze(true);
                rightTable = periodTableId.getRightFreezeAsTable();
                loadProjectionSelection();
                if (Constant.ACTUALS.equals(selection.getActualsOrProjections())) {/* As per GAL-11186*/
                    selection.setActualsOrProjections(Constant.BOTH);
                }
                configureTable();
                generateButtonLogic();
            } else {
                MessageBox.showPlain(Icon.INFO, "Error", "Please select Frequency and History for generate.", ButtonId.OK);
            }
            LOGGER.debug("End of Generate Button Click");
        } catch (Exception e) {
            LOGGER.error(e);
        }
        generated = false;
    }

    private void configureTable() {
        LOGGER.debug("Starting configure table ");
        List<Object> visibleColumn = new ArrayList<Object>();
        List<String> columnHeader;
        List<Object> doubleVisibleColumn = new ArrayList<Object>();
        List<String> doubleColumnHeader;
        fullHeader = new CustomTableHeaderDTO();
        leftdto = HeaderUtils.getPPAProjectionResultsLeftTableColumns(fullHeader);
        ridhtdto = HeaderUtils.getCalculatedPPAProjectionResultsColumns(selection, fullHeader);
        selection.setStartDate(getDate(selection.getStartDay(), selection.getStartMonth() - 1, selection.getStartYear()));
        selection.setToDates(getDate(selection.getEndDay(), selection.getEndMonth() - 1, selection.getEndYear()));
        alignRight();
        resultBean.removeAllItems();
        resultBean.setColumnProperties(ridhtdto.getProperties());
        periodTableId.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        tableLogic.sinkItemPerPageWithPageLength(false);
        tableLogic.setContainerDataSource(resultBean);
        tableLogic.setTreeNodeMultiClick(false);
        periodTableId.markAsDirty();
        periodTableId.setImmediate(true);
        periodTableId.setPageLength(NumericConstants.TWENTY);
        periodTableId.setSelectable(true);
        periodTableId.setSizeFull();
        periodTableId.setDoubleHeaderVisible(true);
        visibleColumn = ridhtdto.getSingleColumns();
        columnHeader = ridhtdto.getSingleHeaders();
        doubleVisibleColumn = ridhtdto.getDoubleColumns();
        doubleColumnHeader = ridhtdto.getDoubleHeaders();

        resultBean.setColumnProperties(leftdto.getProperties());
        resultBean.setColumnProperties(ridhtdto.getProperties());
        leftTable.setVisibleColumns(leftdto.getSingleColumns().toArray());

        leftTable.setColumnHeaders(leftdto.getSingleHeaders().toArray(new String[leftdto.getSingleHeaders().size()]));
        rightTable.setVisibleColumns(visibleColumn.toArray());

        rightTable.setColumnHeaders(columnHeader.toArray(new String[columnHeader.size()]));
        periodTableId.setDoubleHeaderVisible(true);
        periodTableId.setHeight("650px");
        leftTable.setHeight("650px");
        rightTable.setHeight("650px");
        rightTable
                .setDoubleHeaderVisibleColumns(doubleVisibleColumn.toArray());
        rightTable
                .setDoubleHeaderColumnHeaders(doubleColumnHeader.toArray(new String[doubleColumnHeader.size()]));
        periodTableId.setDoubleHeaderMap(leftdto.getDoubleHeaderMaps(), ridhtdto.getDoubleHeaderMaps());
        selection.setProjectionId(session.getProjectionId());
        selection.setUserId(session.getUserId() != null ? Integer.valueOf(session.getUserId()) : 0);
        selection.setSessionId(session.getSessionId() != null ? Integer.valueOf(session.getSessionId()) : 0);
        leftTable.setFilterBarVisible(true);
        leftTable.setFilterDecorator(new ExtDemoFilterDecorator());
        leftTable.setFilterGenerator(new PPAResultsGenerator(selection));
        initializeResultTable();
        LOGGER.debug("Ending configure table ");
    }

    /**
     * Reset button logic.
     *
     * @param event the event
     */
    @UiHandler("resetBtn")
    public void resetButtonLogic(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            @Override
            public void noMethod() {
            }

            @Override
            public void yesMethod() {
                if (frequency.getValue().equals(Constant.MONTHLY)) {
                    history.setValue(NumericConstants.TWELVE);
                } else if (frequency.getValue().equals(Constant.QUARTERLY)) {
                    history.setValue(NumericConstants.FOUR);
                } else if (frequency.getValue().equals(Constant.ANNUALLY)) {
                    history.setValue(1);
                } else {
                    history.setValue(NumericConstants.TWO);
                }
                frequency.setValue(Constant.QUARTERLY);
                periodOrder.setValue(Constant.ASCENDING);
                actualOrProj.setValue(Constant.ACTUALS);
                pivotView.setValue(Constant.PERIOD);
            }
        }.getConfirmationMessage("Confirm Reset", "Are you sure you want to reset the page to default values?");
    }

    /**
     * Enter.
     *
     * @param event the event
     */
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    /**
     * Collapse lvl btn.
     *
     * @param event the event
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    @UiHandler("collapseBtn")
    public void collapseLvlBtn(Button.ClickEvent event) throws SystemException, PortalException {
        expandCollapseLevelOption(Boolean.FALSE, level.getValue());
    }

    /**
     * Expand lvl btn.
     *
     * @param event the event
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    @UiHandler("expandBtn")
    public void expandLvlBtn(Button.ClickEvent event) throws SystemException, PortalException {
        expandCollapseLevelOption(Boolean.TRUE, level.getValue());
    }

    private void expandCollapseLevelOption(boolean isExpand, Object value)  {
        if (value != null) {
            List<Object> levelHierarchy = CommonLogic.getLevelNoAndHierarchyNo(value);
            int levelNo = Integer.valueOf(String.valueOf(levelHierarchy.get(0)));
            if (levelNo > 0) {
                if (selection.isIsFilter()) {
                    levelFilter.removeValueChangeListener(levelFilterChangeOption);
                    levelFilter.setValue(SELECT_ONE);
                    selection.setIsFilter(false);
                    tableLogic.clearAll();
                    levelFilter.addValueChangeListener(levelFilterChangeOption);
                } else {
                    tableLogic.clearAllExceptCurrentPage();
                }
                String hierarchyIndicator = String.valueOf(levelHierarchy.get(1));
                selection.setHierarchyIndicator(hierarchyIndicator);
                if (!isExpand) {
                    levelNo--;
                }
                tableLogic.loadExpandData(levelNo);
            }
        } else {
            isGenerated = Boolean.FALSE;
            generateButtonLogic();
        }
    }

    /**
     * Excel export logic.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    private void excelExportLogic() throws SystemException, PortalException {

        excelTable = new ExtCustomTreeTable();
        verticalLayout.addComponent(excelTable);
        excelTable.setRefresh(Boolean.FALSE);
        excelTable.setVisible(Boolean.FALSE);
        List visibleColums = fullHeader.getSingleColumns();
        String[] visibleHeaders = fullHeader.getSingleHeaders().toArray(new String[fullHeader.getSingleHeaders().size() - 1]);
        excelContainer.setColumnProperties(leftdto.getProperties());
        excelContainer.setColumnProperties(ridhtdto.getProperties());
        excelTable.setContainerDataSource(excelContainer);
        excelTable.setVisibleColumns(visibleColums.toArray());
        excelTable.setColumnHeaders(visibleHeaders);
        levelFilterDdlbChangeOption(true);
        excelTable.setRefresh(Boolean.TRUE);
        ForecastUI.EXCEL_CLOSE = true;
        ExcelExport export = new ExcelExport(new ExtCustomTableHolder(excelTable), "PPA Projection Results", "PPA Projection Results", "PPA_Projection_Results.xls", false);
        export.export();
        verticalLayout.removeComponent(excelTable);
    }

    /**
     * This is listener for excel export.
     *
     * @param event
     * @throws SystemException
     * @throws PortalException
     * @throws Exception
     */
    @UiHandler("excelBtn")
    public void excelBtn(Button.ClickEvent event) throws SystemException, PortalException {
        if (!selection.isIsCustomHierarchy()) {
            excelExportLogic();
        } else {
            excelLogic();
        }
    }

    /**
     * This is listener for chart icon.
     *
     * @param event the event
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    @UiHandler("graphBtn")
    public void graphButtonLogic(Button.ClickEvent event) throws SystemException, PortalException {
        chartExportLogic();
    }

    /**
     * Gets the SALES_SMALL chart.
     *
     * @return the SALES_SMALL chart
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    private void chartExportLogic() throws SystemException, PortalException {

        List chartiLst = new ArrayList();
        for (Object obj : tableLogic.getLogic().getChartList()) {
            PPAProjectionResultsDTO dto = (PPAProjectionResultsDTO) obj;
            if (dto.getLevelNo() != null && dto.getLevelNo() == 0 && !dto.getIsTotalColumn()) {
                chartiLst.add(dto);
            }

        }

        final PPAChart chart = new PPAChart(chartiLst, String.valueOf(frequency.getValue()), String.valueOf(history.getValue()), selection, fullHeader);
        final NmPPAGraphWindow salesGraphWindow = new NmPPAGraphWindow(chart.getChart(), Constant.PPA_PROJECTION_RESULT);
        UI.getCurrent().addWindow(salesGraphWindow);
        salesGraphWindow.focus();

    }

    /**
     * new the hierarchy btn.
     *
     * @param event the event
     */
    @UiHandler("newBtn")
    public void newHierarchyBtn(Button.ClickEvent event) {
        final CustomTreeBuild customTree = new CustomTreeBuild(Constant.ADD_SMALL, session);
        customTree.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                try {
                    if (customTree.isIsSelect()) {
                        customIdToSelect = customTree.getCustomId();
                    }
                    if (!generated)
                     customViewList = CommonLogic.getCustomViewList(projectionId);    
                    session.setCustomerViewList(customViewList);
                    loadCustomDDLB();
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        });
        UI.getCurrent().addWindow(customTree);
    }

    /**
     * edit the hierarchy btn.
     *
     * @param event the event
     */
    @UiHandler("editBtn")
    public void editHierarchyBtn(Button.ClickEvent event) {
        if (CommonLogic.editButtonValidation(customDdlb, customViewList)) {
            final CustomTreeBuild customTree = new CustomTreeBuild(Constant.EDIT, session, customId);
            customTree.addCloseListener(new Window.CloseListener() {
                @Override
                public void windowClose(Window.CloseEvent e) {
                    try {
                        customIdToSelect = customTree.getCustomId();
                        customViewList = CommonLogic.getCustomViewList(projectionId);    
                    session.setCustomerViewList(customViewList);
                        loadCustomDDLB();
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
            });
            UI.getCurrent().addWindow(customTree);
        }
    }

    /**
     * value change of view.
     *
     * @param event the event
     */
    @UiHandler("view")
    public void viewChangeOption(Property.ValueChangeEvent event) {
        LOGGER.debug("viewChangeOption ValueChangeEvent initiated ");
        try {
            viewChange(true);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("viewChangeOption ValueChangeEvent ends ");
    }

    public void viewChange(boolean viewChange) {
        session.setIsPPAUpdated(true);
        selection.setIsCustomHierarchy(Boolean.FALSE);
        customDdlb.setEnabled(Boolean.FALSE);
        editBtn.setEnabled(Boolean.FALSE);
        newBtn.setEnabled(Boolean.FALSE);
        if (view.getValue() != null) {
            if (Constant.CUSTOM.equals(String.valueOf(view.getValue()))) {
                selection.setHierarchyIndicator(StringUtils.EMPTY);
                selection.setIsCustomHierarchy(Boolean.TRUE);
                levelFilter.setEnabled(Boolean.FALSE);
                group.setEnabled(Boolean.TRUE);
                if (firstGenerated && !generated) {
                    tableLogic.clearAll();
                    tableLogic.getControlTable().getContainerDataSource().removeAllItems();
                   
                }
                loadCustomDDLB();                
                                
            } else {
                level.setEnabled(true);
                customIdToSelect = customId;
                levelFilter.setEnabled(Boolean.TRUE);
                selection.setIsCustomHierarchy(Boolean.FALSE);
                selection.setTpLevel(Utility.getTradingPartnerLevelNo(projectionId,session));
                if (Constant.CUSTOMER_SMALL.equals(String.valueOf(view.getValue()))) {
                    selection.setHierarchyIndicator(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
                    selection.setRelationshipBuilderSid(selection.getCustRelationshipBuilderSid());
                    hierarchyLevelNo = Integer.valueOf(session.getCustomerLevelNumber());
                    group.setEnabled(Boolean.TRUE);
                    if (viewChange && firstGenerated) {
                        try {
                            isGenerated = Boolean.FALSE;
                            generateButtonLogic();
                        } catch (Exception ex) {
                            java.util.logging.Logger.getLogger(PPAProjectionResults.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else if (Constant.PRODUCT.equals(String.valueOf(view.getValue()))) {
                    selection.setHierarchyIndicator(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
                    selection.setRelationshipBuilderSid(selection.getProdRelationshipBuilderSid());
                    hierarchyLevelNo = Integer.valueOf(session.getProductLevelNumber());
                    group.setEnabled(Boolean.FALSE);
                    if (viewChange && firstGenerated) {
                        try {
                            generateButtonLogic();
                        } catch (Exception ex) {
                            java.util.logging.Logger.getLogger(PPAProjectionResults.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
    }

    public void loadCustomDDLB() {
        LOGGER.debug("projection results loadCustomDDLB initiated ");
        customDdlb.setEnabled(true);
        newBtn.setEnabled(true);
        LOGGER.debug("projection results customViewList start");
        if (!generated) {
            if(session.getCustomerViewList().isEmpty()){
            customViewList = CommonLogic.getCustomViewList(projectionId);
            session.setCustomerViewList(customViewList);
            }
            else {
            customViewList = session.getCustomerViewList();
            }
            LOGGER.debug("projection results customViewList end");
            if (customViewList != null) {
                customDdlb.removeAllItems();
                customDdlb.addItem(SELECT_ONE);
                customDdlb.setNullSelectionItemId(SELECT_ONE);
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
                if (select != null) {
                    level.setEnabled(true);
                    customDdlb.select(customIdToSelect);
                } else {
                    level.setEnabled(false);
                    customDdlb.setValue(SELECT_ONE);
                    resultBean.removeAllItems();
                }
            }
        }
        LOGGER.debug("projection results loadCustomDDLB ends ");
    }

    /**
     * value change of view.
     *
     * @param event the event
     */
    @UiHandler("customDdlb")
    public void customDdlbChangeOption(Property.ValueChangeEvent event) {
        LOGGER.debug("customDdlbChangeOption ValueChangeEvent initiated ");
        customId = CommonLogic.customDdlbOptionChange(customDdlb, editBtn, level);
        selection.setCustomId(customId);
        level.setEnabled(customId != 0);
        int tpNo = CommonLogic.getTradingPartnerLevelNo(true, customId);
        selection.setTpLevel(tpNo);
         if (customId != 0) {
            session.setCustomId(customId);
            Utility.loadCustomHierarchyList(session);
              viewChangeHierarchy=session.getCustomHierarchyMap().get(customId);
        }
        if (!generated && firstGenerated && !"null".equals(String.valueOf(customDdlb.getValue()))) {
            try {
                isGenerated = Boolean.FALSE;
                generateButtonLogic();
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
        if ("null".equals(String.valueOf(customDdlb.getValue()))) {
            resultBean.removeAllItems();
        }
        LOGGER.debug("customDdlbChangeOption ValueChangeEvent ends ");
    }

    public void loadProjectionSelection()  {
        selection.setSessionDTO(session);
        selection.setFrequency(frequency.getValue().toString());
        selection.setHistoryNum(Integer.valueOf(history.getValue().toString()));
        selection.setHistory(history.getItemCaption(history.getValue()));
        selection.setProjectionOrder(periodOrder.getValue().toString());
        selection.setActualsOrProjections(actualOrProj.getValue().toString());
        selection.setPivotView(pivotView.getValue().toString());
        selection.setView(view.getValue().toString());
        selection.setProjectionId(projectionId);
        selection.setSessionId(Integer.valueOf(session.getSessionId()));
        selection.setIsProjectionTotal(Boolean.TRUE);
        selection.setForecastDTO(session.getForecastDTO());
        selection.setUserId(Integer.valueOf(session.getUserId()));
        selection.setCustomerLevelNo(Integer.valueOf(session.getCustomerLevelNumber()));
        selection.setProductLevelNo(Integer.valueOf(session.getProductLevelNumber()));
        selection.setCustRelationshipBuilderSid(session.getCustRelationshipBuilderSid());
        selection.setProdRelationshipBuilderSid(session.getProdRelationshipBuilderSid());
        viewChange(Boolean.FALSE);
        selection.setLevelNo(hierarchyLevelNo);

        tableLogic.setSelection(selection);
        tableLogic.setSession(session);

        if (session.getForecastDTO().getForecastStartDate() != null && session.getForecastDTO().getForecastStartDate() != null) {
            selection.setProjectionNum(CommonUtils.getProjectionNumber(selection.getFrequency(), session));
        }
        selection.setStartDate(logic.getStartForData(frequency.getValue().toString(), Integer.valueOf(history.getValue().toString()) + 1, history.getItemCaption(history.getValue()), session));
        loadGroupFilter(selection.getProjectionId(), selection.getUserId(), selection.getSessionId());
        groupChange(false);
    }

    public void setProjectionSelection(){

        Map<Object, Object> map = new NMProjectionVarianceLogic().getNMProjectionSelection(projectionId, Constant.PPA_PROJECTION_RESULT);
        if (map != null && map.size() > 0) {
            frequency.select(map.get(Constant.FREQUENCY));
            history.select(Integer.valueOf(map.get(Constant.HISTORY).toString()));
            periodOrder.select(map.get(Constant.PERIOD_ORDER));
            pivotView.select(map.get("Pivot View"));
            actualOrProj.select(map.get("Actual/projection"));
        }
    }

    public void loadGroupFilter(int projId, int userId, int sessionId)  {
        LOGGER.debug("loadGroupFilter initiated ");
        group.setEnabled(true);
        group.removeAllItems();
        List<String> groupList = CommonLogic.getAllPPAGroup();
        if (groupList != null && !groupList.isEmpty()) {
            for (String groups : groupList) {
                group.addItem(groups);
            }
            group.select(groupList.get(0));
        }

        LOGGER.debug("loadGroupFilter ends ");
    }

    public void loadGroupFilterOntabChange()  {
        loadGroupFilter(session.getProjectionId(), selection.getUserId(), selection.getSessionId());
    }

    public void saveButtonLogic() {
        SPRCommonLogic sprCommonLogic = new SPRCommonLogic();
        Map map = new HashMap();
        map.put(Constant.FREQUENCY, frequency.getValue().toString());
        map.put(Constant.HISTORY, history.getValue().toString());
        map.put(Constant.PERIOD_ORDER, periodOrder.getValue().toString());
        map.put("Pivot View", pivotView.getValue().toString());
        map.put("Actual/projection", actualOrProj.getValue().toString());
        sprCommonLogic.saveNMSRPSelection(map, projectionId, Constant.PPA_PROJECTION_RESULT);
    }

    public void loadDataToContainer(List<PPAProjectionResultsDTO> resultList, Object parentId, boolean isRecursive,int maxLevelNo)  {
        for (PPAProjectionResultsDTO dto : resultList) {
            excelContainer.addBean(dto);
            if (parentId != null) {
                excelContainer.setParent(dto, parentId);
            }
            if (dto.getLevelNo() != null && dto.getLevelNo() != 0) {
                excelContainer.setChildrenAllowed(dto, true);
                if (isRecursive && dto.getLevelNo() <= maxLevelNo) {
                    addLowerLevelsForExport(dto, maxLevelNo);
                }
            } else {
                excelContainer.setChildrenAllowed(dto, false);
            }
        }
    }

    public void addLowerLevelsForExport(PPAProjectionResultsDTO dto,int maxLevelNo) {
        try {
            selection.setIsProjectionTotal(false);
            List<PPAProjectionResultsDTO> levelList = logic.getConfiguredPPAProjectionResults1(dto, 0, NumericConstants.TEN_LAKH, selection, session);
            loadDataToContainer(levelList, dto, true, maxLevelNo);           
            excelTable.setCollapsed(dto, false);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public void loadLevelFilter() {
        LOGGER.debug("loadLevelFilter initiated ");
        level.setEnabled(true);
        List<Leveldto> hierarchy = null;
       String view = StringUtils.EMPTY;
        if (selection.isIsCustomHierarchy()) {
            view = Constant.CUSTOM;
            if (!session.getCustomHierarchyMap().containsKey(customId)) {
            Utility.loadCustomHierarchyList(session);
            }
            if (session.getCustomHierarchyMap().containsKey(customId)) {
            hierarchy = session.getCustomHierarchyMap().get(customId);
            }
        } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(selection.getHierarchyIndicator())) {
            hierarchy = session.getCustomerHierarchyList();
        } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(selection.getHierarchyIndicator())) {
            hierarchy = session.getProductHierarchyList();
        }
         Utility.loadLevelDDlbValue(level, levelFilter, null, hierarchy, view);

        LOGGER.debug("loadLevelFilter ends ");
        }

    public void levelFilterDdlbChangeOption(boolean excelExport)  {
        if (levelFilter.getValue() != null) {
            List<Object> levelHierarchy = CommonLogic.getLevelNoAndHierarchyNo(levelFilter.getValue());
            int levelNo = Integer.valueOf(String.valueOf(levelHierarchy.get(0)));
            if (levelNo < 0) {
                levelNo = 0;
            }
            String hierarchyNo = String.valueOf(levelHierarchy.get(1));
            selection.setIsFilter(true);
            if (levelNo == 0) {
                selection.setIsFilter(false);
            }
            selection.setHierarchyNo(StringUtils.EMPTY);
            if (excelExport) {
                loadExcelResultTable(levelNo, hierarchyNo);
            } else {
                loadResultTable(levelNo, hierarchyNo);
            }
        } else if (excelExport) {
            loadExcelResultTable(hierarchyLevelNo, StringUtils.EMPTY);
        } else {
            isGenerated = Boolean.FALSE;
            generateButtonLogic();
        }
    }

    @SuppressWarnings("serial")
    private void loadExcelResultTable(int levelNo, String hierarchyNo)  {
        try {
            excelContainer.removeAllItems();
            selection.setFilterLevelNo(levelNo);
            selection.setFilterHierarchyNo(hierarchyNo);
            selection.setProductHierarchyNo(StringUtils.EMPTY);
            selection.setCustomerHierarchyNo(StringUtils.EMPTY);
            selection.clearNonFetchableIndex();
            List<PPAProjectionResultsDTO> resultList = logic.getConfiguredPPAProjectionResultsTotal(0, NumericConstants.ONE_CRORE, selection, session);
            resultList.addAll(logic.getConfiguredPPAProjectionResults1(new Object(), 0, NumericConstants.TEN_LAKH, selection, session));

            int maxLevelNo = Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(selection.getHierarchyIndicator()) ? session.getMaximumCustomerLevel() : session.getMaximumProductLevel();
            loadDataToContainer(resultList, null, Boolean.TRUE, maxLevelNo);
        } catch (Exception ex) {

            LOGGER.error(ex);
        }
    }

    /**
     * Loads the results.
     */
    @SuppressWarnings("serial")
    private void loadResultTable(int levelNo, String hierarchyNo)  {
        LOGGER.debug("Insie loadresults table ");
        selection.setFilterLevelNo(levelNo);
        selection.setFilterHierarchyNo(hierarchyNo);
        selection.setProductHierarchyNo(StringUtils.EMPTY);
        selection.setCustomerHierarchyNo(StringUtils.EMPTY);
        tableLogic.setProjectionResultsData(selection, isGenerated);
        periodTableId.getLeftFreezeAsTable().setFilterBarVisible(true);
        periodTableId.getLeftFreezeAsTable().setFilterDecorator(new ExtDemoFilterDecorator());
        selection.setProjectionId(session.getProjectionId());
        selection.setUserId(session.getUserId() != null ? Integer.valueOf(session.getUserId()) : 0);
        selection.setSessionId(session.getSessionId() != null ? Integer.valueOf(session.getSessionId()) : 0);
        periodTableId.getLeftFreezeAsTable().setFilterGenerator(new PPAResultsGenerator(selection));
        LOGGER.debug("End loadresults table ");
    }

    public int getTabNumber() {
        return Constant.SIX;
    }

    private void alignRight()  {
        for (Object obj : ridhtdto.getSingleColumns()) {
            rightTable.setColumnAlignment(obj, ExtCustomTable.Align.RIGHT);
        }
    }

    private void generateButtonLogic()  {

        levelFilter.removeValueChangeListener(levelFilterChangeOption);
        loadLevelFilter();
        levelFilter.addValueChangeListener(levelFilterChangeOption);
        selection.setIsFilter(Boolean.FALSE);
        loadResultTable(hierarchyLevelNo, StringUtils.EMPTY);

    }

    public void pushUpdate(String indicator) {
        if (Constants.IndicatorConstants.INDICATOR_REFRESH_UPDATE.getConstant().equals(indicator)) {
            // Refresh on generate
        }
        if (Constants.IndicatorConstants.INDICATOR_TIME_PERIOD_CHANGED.getConstant().equals(indicator)) {
            // Refresh after from period change in DS
        }
    }

    private Date getDate(int day, int month, int year) {
        Date date = new Date();
        date.setDate(day);
        date.setMonth(month);
        date.setYear(year - NumericConstants.ONE_NINE_ZERO_ZERO);
        return date;
    }

    public void security() throws PortalException, SystemException{

        final Map<String, AppPermission> functionPsHM = stplSecurity.getBusinessFunctionPermission(userId, getCommercialConstant() + "," + UISecurityUtil.PPA_PROJECTION_RESULTS);
        if (!(functionPsHM.get(FunctionNameUtil.GENERATE) != null && ((AppPermission) functionPsHM.get(FunctionNameUtil.GENERATE)).isFunctionFlag())) {
            generateBtn.setVisible(Boolean.FALSE);
            collapseBtn.setVisible(Boolean.FALSE);
            expandBtn.setVisible(Boolean.FALSE);
            editBtn.setVisible(false);
            newBtn.setVisible(false);

        }
    }

    /**
     * details btn.
     *
     * @param event the event
     */
    @UiHandler("detailsBtn")
    public void detailsBtnLogic(Button.ClickEvent event) {
        if (ppaDetailsLookup == null) {
            try {
                ppaDetailsLookup = new PPADetailsLookup(projectionId, session);
                UI.getCurrent().addWindow(ppaDetailsLookup);
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
        ppaDetailsLookup.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                ppaDetailsLookup = null;
            }
        });
    }

    @SuppressWarnings("serial")
    protected void initializeResultTable() {
        periodTableId.markAsDirty();
        periodTableId.setSelectable(false);
        periodTableId.setImmediate(true);
        periodTableId.setSplitPosition(splitPosition, Sizeable.Unit.PIXELS);
        periodTableId.setMinSplitPosition(minSplitPosition, Sizeable.Unit.PIXELS);
        periodTableId.setMaxSplitPosition(maxSplitPosition, Sizeable.Unit.PIXELS);
        periodTableId.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
    }

    /**
     * Excel export logic.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    private void excelLogic()  {
        try {
            excelTable = new ExtCustomTreeTable();
            verticalLayout.addComponent(excelTable);
            excelTable.setRefresh(Boolean.FALSE);
            excelTable.setVisible(Boolean.FALSE);
            excelContainer = new ExtTreeContainer<>(PPAProjectionResultsDTO.class,ExtContainer.DataStructureMode.MAP);
            List visibleColums = fullHeader.getSingleColumns();
            String[] visibleHeaders = fullHeader.getSingleHeaders().toArray(new String[fullHeader.getSingleHeaders().size() - 1]);
            excelContainer.setColumnProperties(leftdto.getProperties());
            excelContainer.setColumnProperties(ridhtdto.getProperties());
            excelTable.setContainerDataSource(excelContainer);
            excelTable.setVisibleColumns(visibleColums.toArray());
            excelTable.setColumnHeaders(visibleHeaders);
            excelContainer = logic.getLoadedExcelContainer(selection, excelContainer);
            ForecastUI.EXCEL_CLOSE = true;
            ExcelExport export = new ExcelExport(new ExtCustomTableHolder(excelTable), "PPA Projection Results", "PPA Projection Results", "PPA_Projection_Results.xls", false);
            export.export();
            verticalLayout.removeComponent(excelTable);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

}
