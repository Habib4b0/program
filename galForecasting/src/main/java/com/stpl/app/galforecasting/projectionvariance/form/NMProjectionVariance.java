
package com.stpl.app.galforecasting.projectionvariance.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.app.galforecasting.abstractforecast.ForecastProjectionVariance;
import com.stpl.app.galforecasting.dao.DataSelectionDAO;
import com.stpl.app.galforecasting.dao.ProjectionVarianceDAO;
import com.stpl.app.galforecasting.dao.impl.DataSelectionDAOImpl;
import com.stpl.app.galforecasting.dao.impl.ProjectionVarianceDAOImpl;
import com.stpl.app.galforecasting.discountProjection.form.NMDiscountProjection;
import com.stpl.app.galforecasting.dto.PVSelectionDTO;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.app.galforecasting.dto.ProjectionVarianceDTO;
import com.stpl.app.galforecasting.logic.CommonLogic;
import com.stpl.app.galforecasting.projectionresults.dto.FilterGenerator;
import com.stpl.app.galforecasting.projectionvariance.dto.ComparisonLookupDTO;
import com.stpl.app.galforecasting.projectionvariance.dto.PVParameters;
import com.stpl.app.galforecasting.projectionvariance.form.lookup.NMComparisonLookup;
import com.stpl.app.galforecasting.projectionvariance.logic.NMPVExcelLogic;
import com.stpl.app.galforecasting.projectionvariance.logic.NMProjectionVarianceLogic;
import com.stpl.app.galforecasting.projectionvariance.logic.tablelogic.ProjectionVarianceTableLogic;
import com.stpl.app.model.ForecastConfig;
import com.stpl.app.galforecasting.queryUtils.PVQueryUtils;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.galforecasting.ui.ForecastUI;
import com.stpl.app.galforecasting.ui.form.ForecastForm;
import com.stpl.app.galforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import com.stpl.app.galforecasting.utils.Constant.PVVariables;
import com.stpl.app.galforecasting.utils.DataSelectionUtil;
import com.stpl.app.galforecasting.utils.FunctionNameUtil;
import com.stpl.app.galforecasting.utils.HeaderUtils;
import com.stpl.app.galforecasting.utils.PVChart;
import com.stpl.app.galforecasting.utils.PVGraphWindow;
import com.stpl.app.galforecasting.utils.UISecurityUtil;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.utils.Constants;
import static com.stpl.app.utils.Constants.LabelConstants.*;
import com.stpl.app.utils.UiUtils;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import static com.stpl.ifs.util.constants.GlobalConstants.*;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.container.ExtTreeContainer;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;
// TODO: Auto-generated Javadoc

/**
 * The Class ProjectionVariance.
 *
 * @author soundarrajan
 */
public class NMProjectionVariance extends ForecastProjectionVariance {

    final StplSecurity stplSecurity = new StplSecurity();
    final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constant.USER_ID));
    /**
     * The indicator.
     */
    String indicator = null;
    /**
     * The comparison projections.
     */
    List<Integer> comparisonProjections = null;
    /**
     * The group.
     */
    /**
     * The frequency.
     */
    String frequencyString = null;
    /**
     * The selected level.
     */
    String selectedLevel = null;
    /**
     * The discount level string.
     */
    String discountLevelString = null;
    /**
     * The projection id.
     */
    int projectionId;
    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    private SessionDTO session;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(NMProjectionVariance.class);
    /**
     * The table control Layout.
     */
    public HorizontalLayout controlLayout;
    /**
     * The trading partner level no.
     */
    int tradingPartnerNo = 0;
    /**
     * The excel export image.
     */
    private final Resource excelExportImage = new ThemeResource("../../icons/excel.png");
    /**
     * The graph image.
     */
    private final Resource graphImage = new ThemeResource("../../icons/chart.png");
    /**
     * The from bean.
     */
    final private BeanItemContainer<String> fromBean = new BeanItemContainer<String>(String.class);
    /**
     * The to bean.
     */
    final private BeanItemContainer<String> toBean = new BeanItemContainer<String>(String.class);
    /**
     * The period table id.
     */
    int hierarchyLevelNo = 0;
    /**
     * The result bean.
     */
    private ExtTreeContainer<ProjectionVarianceDTO> resultBean = new ExtTreeContainer<ProjectionVarianceDTO>(ProjectionVarianceDTO.class);
    CustomTableHeaderDTO rightHeaderPeriod = new CustomTableHeaderDTO();
    List<Leveldto> currentHierarchy = new ArrayList<Leveldto>();
    List<Leveldto> viewChangeHierarchy = new ArrayList<Leveldto>();
    boolean customHierarchy = false;
    List<ComparisonLookupDTO> selectedList = new ArrayList<ComparisonLookupDTO>();
    /**
     * The custom id.
     */
    /**
     * The custom id to select.
     */
    /**
     *
     */
    public static List<String> projNameList = new ArrayList<String>();
    public static List<Integer> projIdList = new ArrayList<Integer>();
    Map<Integer, String> projectionMap = new HashMap<Integer, String>();
    NMProjectionVarianceLogic logic = new NMProjectionVarianceLogic();
    ProjectionVarianceDAO projectionVarianceDAO = new ProjectionVarianceDAOImpl();
    PVQueryUtils queryUtils = new PVQueryUtils();
    ProjectionVarianceDTO valueGTS = new ProjectionVarianceDTO();
    ProjectionVarianceDTO variGTS = new ProjectionVarianceDTO();
    ProjectionVarianceDTO perGTS = new ProjectionVarianceDTO();
    ProjectionVarianceDTO valuePercBuisness = new ProjectionVarianceDTO();
    ProjectionVarianceDTO varpercBuisness = new ProjectionVarianceDTO();
    ProjectionVarianceDTO perPercBuisness = new ProjectionVarianceDTO();
    ProjectionVarianceDTO valueContractSales = new ProjectionVarianceDTO();
    ProjectionVarianceDTO varContractSales = new ProjectionVarianceDTO();
    ProjectionVarianceDTO perContractSales = new ProjectionVarianceDTO();
    ProjectionVarianceDTO valueUnitResult = new ProjectionVarianceDTO();
    ProjectionVarianceDTO varUnitResult = new ProjectionVarianceDTO();
    ProjectionVarianceDTO perUnitResult = new ProjectionVarianceDTO();
    ProjectionVarianceDTO valuediscountDollar = new ProjectionVarianceDTO();
    ProjectionVarianceDTO vardiscountDollar = new ProjectionVarianceDTO();
    ProjectionVarianceDTO perdiscountDollar = new ProjectionVarianceDTO();
    ProjectionVarianceDTO valuediscountPer = new ProjectionVarianceDTO();
    ProjectionVarianceDTO vardiscountPer = new ProjectionVarianceDTO();
    ProjectionVarianceDTO perdiscountPer = new ProjectionVarianceDTO();
    ProjectionVarianceDTO valueNetSales = new ProjectionVarianceDTO();
    ProjectionVarianceDTO variNetSales = new ProjectionVarianceDTO();
    ProjectionVarianceDTO perNetSales = new ProjectionVarianceDTO();
    ProjectionVarianceDTO valuePPARate = new ProjectionVarianceDTO();
    ProjectionVarianceDTO variPPARate = new ProjectionVarianceDTO();
    ProjectionVarianceDTO perPPARate = new ProjectionVarianceDTO();
    ProjectionVarianceDTO valuePPAAmount = new ProjectionVarianceDTO();
    ProjectionVarianceDTO variPPAAmount = new ProjectionVarianceDTO();
    ProjectionVarianceDTO perPPAAmount = new ProjectionVarianceDTO();
    ForecastForm nonMandatedForm;
    List<List<String>> discountlist = new ArrayList<List<String>>();
    Map<String, Object> selection = new HashMap<String, Object>();
    List<ProjectionVarianceDTO> projDTOList1 = new ArrayList<ProjectionVarianceDTO>();
    List<List> currentDiscount = new ArrayList<List>();
    List<List> currentSales = new ArrayList<List>();
    List<List> currentTotal = new ArrayList<List>();
    List<List> currentTotalDiscount = new ArrayList<List>();
    List<Integer> priorProjIdDiscountList = new ArrayList<Integer>();
    List<Integer> priorProjIdSalesList = new ArrayList<Integer>();
    List<Integer> priorProjIdTotalList = new ArrayList<Integer>();
    List<List> currentPivotGTSTotal = new ArrayList<List>();
    List<Integer> pivotPriorProjIdList = new ArrayList<Integer>();
//    PVSelectionDTO baseVariables = new PVSelectionDTO();
    boolean firstGenerated = false;
    public List<Integer> comparisonProjId = new ArrayList<Integer>();
    public List<String> comparisonProjName = new ArrayList<String>();
    boolean editFlag = false;
    /**
     * The selected projection.
     */
    private final ExtFilterTable selectedProjection = new ExtFilterTable();
    String screenName;
    private boolean isComparisonLookupOpened;
    private final Map<String, List<ProjectionVarianceDTO>> resultMap = new HashMap();
    boolean flag = false;

    private final Map<String, Object> excelParentRecords = new HashMap();
    private final List<String> hierarchyKeys = new ArrayList();
    private final List<String> tradingPartnerKeys = new ArrayList();
    private final List<String> discountKeys = new ArrayList();
    protected PVParameters parameterDto = new PVParameters();
    private final NMPVExcelLogic excelLogic = new NMPVExcelLogic(resultMap, pvSelectionDTO, hierarchyKeys, tradingPartnerKeys, discountKeys, parameterDto);

    /**
     * The Constant AMOUNT.
     */
    /**
     * The Constant RATE.
     */
    /**
     * Constructor for ProjectionVariance.
     */
    public NMProjectionVariance(ForecastForm form, SessionDTO sessionDTO, String screenName) throws PortalException, SystemException, Exception {
        super(sessionDTO, screenName);
        this.screenName = screenName;
        LOGGER.info("ProjectionVariance Constructor initiated ");
        nonMandatedForm = form;
        session = nonMandatedForm.getSessions();
        projectionId = session.getProjectionId();
        pvSelectionDTO.setUserId(Integer.valueOf(session.getUserId()));
        pvSelectionDTO.setSessionId(Integer.valueOf(session.getSessionId()));
        tradingPartnerNo = CommonLogic.getTradingPartnerLevelNo(false, projectionId);
        excelTable.setVisible(false);
        if (flag) {
            configure();
        }
        flag = true;

        LOGGER.info("ProjectionVariance Constructor ends ");
    }

    /**
     * Configure fields.
     */
    private void configureFields() {
        session = nonMandatedForm.getSessions();
        projectionId = nonMandatedForm.getSessions().getProjectionId();

        frequency.addItem(Constant.SELECT_ONE);
        frequency.addItem(Constant.ANNUALLY);
        frequency.addItem(Constant.SEMI_ANNUALLY);
        frequency.addItem(Constant.QUARTERLY);
        frequency.addItem(Constant.MONTHLY);

        frequency.setValue(Constant.QUARTERLY);
        frequency.setNullSelectionItemId(Constant.SELECT_ONE);
        frequency.setNullSelectionAllowed(true);

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
        discountLevel.addItem("Total Discount");
        discountLevel.addItem("Program Category");
        discountLevel.addItem("Program");
        discountLevel.select("Total Discount");
        discountLevel.addStyleName(Constant.HORIZONTAL);

        excelBtn.setIcon(excelExportImage);
        graphBtn.setIcon(graphImage);
        initialConfig();
        addResultTable();
        loadGroupFilterOntabChange();
        group.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                groupChange(true);
            }
        });
        if (Constant.EDIT_SMALL.equals(session.getAction()) || Constant.VIEW.equals(session.getAction())) {
            setProjectionSelection();
        }
    }

    private void initialConfig() {
        getCheckedValues();
        loadProjectionSelection();

        loadVariables();
        configureTable();
    }

    /**
     * Configure table.
     */
    public void configureTable() {
        fullHeader = new CustomTableHeaderDTO();
        leftHeader = HeaderUtils.getVarianceLeftTableColumns(fullHeader);
        List<Object> HeaderPropertyIds = HeaderUtils.getVarianceRightTableColumns(pvSelectionDTO, fullHeader);
        rightHeader = (CustomTableHeaderDTO) HeaderPropertyIds.get(0);
        rightHeaderPeriod = (CustomTableHeaderDTO) HeaderPropertyIds.get(0);
        pvSelectionDTO.setRightHeaderPeriod(rightHeaderPeriod);

        alignRight();
        resultBeanContainer = new CustomTreeContainer<ProjectionVarianceDTO>(ProjectionVarianceDTO.class);

        resultBeanContainer.setColumnProperties(leftHeader.getProperties());
        resultBeanContainer.setColumnProperties(rightHeader.getProperties());
        tableLogic.setScreenName(screenName);
        tableLogic.setPageLength(20);
        tableLogic.sinkItemPerPageWithPageLength(false);
        List<Integer> pagelength = CommonLogic.getPageNumber();
        tableLogic.getControlConfig().setPageLengthsAndCaptions(pagelength);
        tableLogic.setContainerDataSource(resultBeanContainer);
        tableLogic.setTreeNodeMultiClick(false);
        final ExtFilterTreeTable leftTable = resultsTable.getLeftFreezeAsTable();
        final ExtFilterTreeTable rightTable = resultsTable.getRightFreezeAsTable();
        leftTable.setImmediate(true);
        leftTable.reConstruct(true);
        rightTable.setImmediate(true);
        rightTable.reConstruct(true);
        resultsTable.setHeight("650px");
        leftTable.setHeight("650px");
        rightTable.setHeight("650px");
        leftTable.setVisibleColumns(leftHeader.getSingleColumns().toArray());
        leftTable.setColumnHeaders(leftHeader.getSingleHeaders().toArray(new String[leftHeader.getSingleHeaders().size()]));

        rightTable.setVisibleColumns(rightHeader.getSingleColumns().toArray());
        rightTable.setColumnHeaders(rightHeader.getSingleHeaders().toArray(new String[rightHeader.getSingleHeaders().size()]));
        rightTable.setDoubleHeaderVisible(true);
        rightTable.setDoubleHeaderVisibleColumns(rightHeader.getDoubleColumns().toArray());
        rightTable.setDoubleHeaderColumnHeaders(rightHeader.getDoubleHeaders().toArray(new String[rightHeader.getDoubleHeaders().size()]));

        rightTable.setDoubleHeaderMap(rightHeader.getDoubleHeaderMaps());
        resultsTable.getLeftFreezeAsTable().setFilterBarVisible(true);
        resultsTable.getLeftFreezeAsTable().setFilterDecorator(new ExtDemoFilterDecorator());
        pvSelectionDTO.setProjectionId(projectionId);
        pvSelectionDTO.setPpa(CommonLogic.isPPA(Boolean.TRUE, pvSelectionDTO));
        pvSelectionDTO.setReturns(CommonLogic.isReturns(Boolean.TRUE, pvSelectionDTO));
        resultsTable.getLeftFreezeAsTable().setFilterGenerator(new FilterGenerator(pvSelectionDTO, Constant.TOTAL.equals(level.getValue().toString())));

        UiUtils.setExtFilterTreeTableColumnWidth(rightTable, 170, TAB_PROJECTION_VARIANCE.getConstant());
        if (fromDate.getValue() != null && !"null".equals(String.valueOf(fromDate.getValue())) && !Constant.SELECT_ONE.equals(String.valueOf(fromDate.getValue()))) {
            List<String> periodList = new ArrayList<String>(pvSelectionDTO.getPeriodHeaderList());
            Object toDateString;

            if (String.valueOf(projectionPeriodOrder.getValue()).equals("Descending")) {
                toDateString = toDate.getValue() == null ? pvSelectionDTO.getPeriodListMap().get(periodList.get(0)) : toDate.getValue();
            } else {
                toDateString = toDate.getValue() == null ? pvSelectionDTO.getPeriodListMap().get(periodList.get(periodList.size() - 1)) : toDate.getValue();
            }
            if (pivotView.getValue().equals("Period")) {
                fullHeader = logic.getDateRangeHeaders(rightTable, rightHeader, fullHeader, fromDate.getValue(), toDateString);
            } else {
                List<String> f = new ArrayList<String>(pvSelectionDTO.getPeriodList());
                if (String.valueOf(projectionPeriodOrder.getValue()).equals("Descending")) {
                    Collections.reverse(periodList);
                }

                List<String> pivotList = new ArrayList<String>();
                String fromPeriod = fromDate.getValue().toString().replace(" ", StringUtils.EMPTY);
                String toPeriod = toDate.getValue().toString().replace(" ", StringUtils.EMPTY);
                if (rightHeader.getFrequencyDivision() == 12) {
                    fromPeriod = fromPeriod.toLowerCase();
                    toPeriod = toPeriod.toLowerCase();
                }

                int start = periodList.indexOf(fromPeriod);
                int end = periodList.indexOf(toPeriod);
                for (int i = start; i <= end; i++) {
                    pivotList.add(periodList.get(i));
                }
                if (String.valueOf(projectionPeriodOrder.getValue()).equals(Constant.DESCENDING)) {
                    Collections.reverse(pivotList);
                }
                pvSelectionDTO.setPeriodList(pivotList);
            }
        }
        if ("Variable".equals(pivotView.getValue().toString())) {
            if (!discountLevel.getValue().equals("Total Discount") 
                    && (pvSelectionDTO.isVarDisAmount() || pvSelectionDTO.isVarDisRate() || pvSelectionDTO.isVarRPU())) {
                //Discount
                final List<String> discountNames = new ArrayList(pvSelectionDTO.getDiscountNameList());
                // PPA.
                List listObj = CommonLogic.getPPADiscountNameList(pvSelectionDTO);
                if(listObj != null){
                discountNames.addAll(listObj);
                }

                resultsTable.getRightFreezeAsTable().setColumnCollapsingAllowed(true);
                for (Object object : pvSelectionDTO.getHeaderMap().values()) {
                    List<String> list = (List<String>) object;
                    for (int i = 0; i < list.size(); i++) {
                        rightTable.setDoubleHeaderColumnCollapsed(list.get(i), true);
                    }
                }
                for (Object object : pvSelectionDTO.getHeaderMap().keySet()) {
                    rightTable.setDoubleHeaderColumnExpandIcon(object, false);
                }
                rightTable.addDoubleHeaderColumnExpandIconListener(new ExtCustomTable.DoubleHeaderColumnExpandIconListener() {
                    @Override
                    public void doubleHeaderColumnExpandIcon(ExtCustomTable.DoubleHeaderColumnExpandIconEvent event) {
                        setExpandIconAction(event.getPropertyId(), !event.isExpanded(), pvSelectionDTO, discountNames);
                    }
                });
            }
        }
    }

    /**
     * Load frequency.
     *
     * @param event the event
     */
    @Override
    protected void loadFrequency() {

        LOGGER.info("ProjectionVariance ValueChangeEvent initiated with frequency -->" + frequency.getValue());
        if (frequency.getValue() != null && !Constant.NULL.equals(String.valueOf(frequency.getValue())) && !StringUtils.EMPTY.equals(String.valueOf(frequency.getValue()))) {
            loadProjectionSelection();
            fullHeader = new CustomTableHeaderDTO();
            HeaderUtils.getVarianceRightTableColumns(pvSelectionDTO, fullHeader);
            loadFromPeriod(Constant.SELECT_ONE);
            loadToPeriod(Constant.SELECT_ONE);
            toDate.setValue(Constant.SELECT_ONE);
        }
        LOGGER.info("ProjectionVariance ValueChangeEvent ends ");
    }

    /**
     * /**
     * Loads the results.
     */
    @SuppressWarnings("serial")
    private void loadResultTable(int levelNo, String hierarchyNo) {
        pvSelectionDTO.setFilterLevelNo(levelNo);
        pvSelectionDTO.setFilterHierarchyNo(hierarchyNo);
        pvSelectionDTO.setProductHierarchyNo(StringUtils.EMPTY);
        pvSelectionDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
        tableLogic.setProjectionResultsData(currentHierarchy, pvSelectionDTO, levelNo, hierarchyNo, pvSelectionDTO);
        resultsTable.getLeftFreezeAsTable().setFilterBarVisible(true);
        resultsTable.getLeftFreezeAsTable().setFilterDecorator(new ExtDemoFilterDecorator());
        pvSelectionDTO.setProjectionId(projectionId);
        resultsTable.getLeftFreezeAsTable().setFilterGenerator(new FilterGenerator(pvSelectionDTO, Constant.TOTAL.equals(level.getValue().toString())));
    }

    private void loadExcelResultTable(int levelNo, String hierarchyNo) {
        LOGGER.info("Inside loadExcelResultTable");
        resultExcelContainer.removeAllItems();
        pvSelectionDTO.setFilterLevelNo(levelNo);
        pvSelectionDTO.setFilterHierarchyNo(hierarchyNo);
        pvSelectionDTO.setProductHierarchyNo(StringUtils.EMPTY);
        pvSelectionDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
        int count = logic.getConfiguredProjectionVarianceCount(new Object(), pvSelectionDTO, pvSelectionDTO, true);
        List<ProjectionVarianceDTO> resultList = logic.getConfiguredProjectionVariance(new Object(), pvSelectionDTO, pvSelectionDTO, 0, count);
        loadDataToContainer(resultList, null);
        LOGGER.info("Ending loadExcelResultTable");
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

    /**
     * Comparison lookup listener.
     *
     * @param event the event
     */
    @Override
    protected void comparisonLookupLogic() {
        LOGGER.info("Comparision lookup started");
        if (editFlag || !comparisonProjId.isEmpty()) {
            editFlag = false;
            try {                
                List list = (List) CommonLogic.executeSelectQuery(queryUtils.getPVComparisonProjections(comparisonProjId), null, null);
                selectedList = logic.getCustomizedPVComparisonList(list);   
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        final NMComparisonLookup comparisonLookupWindow = new NMComparisonLookup("Comparison lookup", "NM", comparison, projectionId, selectedList, screenName);
        UI.getCurrent().addWindow(comparisonLookupWindow);
        comparisonLookupWindow.addCloseListener(new Window.CloseListener() {
            /**
             * Default method.
             */
            @Override
            public void windowClose(final Window.CloseEvent event) {
                if (comparisonLookupWindow.getSelectedProjection().getItemIds().isEmpty()) {
                    comparison.setReadOnly(false);
                    comparison.setValue(Constant.SELECT_ONE);
                    comparison.setData(null);
                    comparison.setImmediate(true);
                    comparison.setReadOnly(true);
                }
                isComparisonLookupOpened = true;
                loadComparison();
            }
        });

        LOGGER.info("Comparision lookup ends");
    }

    /**
     * Excel export button click method.
     *
     * @param event the event
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    protected void excelBtnLogic() {
        try {
            excelTable.setRefresh(Boolean.FALSE);
            setPvSelection(String.valueOf(variableCategory.getValue()), variablesValue);
            excelForCommercial();
            long start = System.currentTimeMillis();
            excelTable.setRefresh(Boolean.TRUE);
            long end = System.currentTimeMillis();
            LOGGER.info("Time taken to refresh table :" + (end - start));
            start = end;
            ForecastUI.EXCEL_CLOSE = true;
            ExcelExport export = new ExcelExport(new ExtCustomTableHolder(excelTable), "Projection Variance", "Projection Variance", "Projection Variance.xls", false);
            export.export();
            end = System.currentTimeMillis();
            LOGGER.info("Time taken to export :" + (end - start));
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    /**
     * Graph button click listener.
     *
     * @param event the event
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    protected void graphBtnLogic() {

        List chartiLst = new ArrayList();
        for (Object obj : resultsTable.getRightFreezeAsTable().getContainerDataSource().getItemIds()) {
            ProjectionVarianceDTO dto = (ProjectionVarianceDTO) obj;
            if (dto.getLevelNo() != null && dto.getParent() == 0) {
                chartiLst.add(dto);
            }
        }
        final PVChart chart = new PVChart(chartiLst, String.valueOf(frequency.getValue()), StringUtils.EMPTY, fullHeader, pvSelectionDTO);
        final PVGraphWindow salesGraphWindow = new PVGraphWindow(chart.getChart(), "Projection Variance");
        UI.getCurrent().addWindow(salesGraphWindow);
        salesGraphWindow.focus();
    }

    /**
     * Reset btn.
     *
     * @param event the event
     */
    @Override
    protected void resetBtnLogic() {
        new AbstractNotificationUtils() {
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
                discountLevel.select("Total Discount");
                projectionPeriodOrder.setValue(Constant.ASCENDING);
                pivotView.setValue(Constant.PERIOD);
                fromDate.setValue(getSelectOne());
                toDate.setValue(getSelectOne());
                comparison.setReadOnly(false);
                comparison.setValue(Constant.SELECT_ONE);
                comparison.setData(null);
                comparison.setReadOnly(true);
                variableCategory.unselect(CommonUtils.COL_VALUE);
                variableCategory.unselect(CommonUtils.COL_VARIANCE);
                variableCategory.unselect(CommonUtils.COL_PERCENTAGE);
                variableCategory.setImmediate(true);

                variables.unselect(CommonUtils.VAR_GTS);
                variables.unselect(CommonUtils.VAR_PERCENTAGE);
                variables.unselect(CommonUtils.VAR_CONTRACT_SALES);
                variables.unselect(CommonUtils.VAR_CONTRACT_UNITS);
                variables.unselect(CommonUtils.VAR_DIS_AMOUNT);
                variables.unselect(CommonUtils.VAR_DIS_RATE);
                variables.unselect(CommonUtils.VAR_NETSALES);
                variables.setImmediate(true);
            }
        }.getConfirmationMessage("Confirm Reset", "Are you sure you want to reset the page to default values?");

    }

    @Override
    protected void expandCollapseLevelOption(boolean isExpand, Object value) {
        List<Object> levelHierarchy = CommonLogic.getLevelNoAndHierarchyNo(value);
        int levelNo = Integer.valueOf(String.valueOf(levelHierarchy.get(0)));
        if (levelNo > 0) {
            if (pvSelectionDTO.isIslevelFiler()) {
                levelFilter.removeValueChangeListener(levelFilterChangeOption);
                levelFilter.setValue(getSelectOne());
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

    @Override
    protected void customDdlbChangeOption() {
        LOGGER.info("customDdlbChangeOption ValueChangeEvent initiated ");
        customId = CommonLogic.customDdlbOptionChange(customDdlb, editViewBtn, levelDdlb);
        pvSelectionDTO.setCustomId(customId);
        levelDdlb.setEnabled(customId != 0);
        int tpNo = CommonLogic.getTradingPartnerLevelNo(true, customId);
        pvSelectionDTO.setTpLevel(tpNo);
        viewChangeHierarchy = CommonLogic.getCustomTree(customId);
        if (customId != 0) {
            callGenerateLogic();
        } else {
            tableLogic.clearAll();
            tableLogic.getControlTable().getContainerDataSource().removeAllItems();
        }
        LOGGER.info("customDdlbChangeOption ValueChangeEvent ends ");
    }

    /**
     * Load frequency.
     *
     * @param event the event
     */
    @UiHandler("fromDate")
    public void fromDateDdlbChange(Property.ValueChangeEvent event) {
        if (fromDate.getValue() != null && !Constant.NULL.equals(String.valueOf(fromDate.getValue())) && !StringUtils.EMPTY.equals(String.valueOf(fromDate.getValue()))
                && !Constant.SELECT_ONE.equals(String.valueOf(fromDate.getValue()))) {
            loadToPeriod(fromDate.getValue().toString());
        } else {
            fromDate.setValue(Constant.SELECT_ONE);
            loadToPeriod(Constant.SELECT_ONE);
        }
    }

    @Override
    protected void getGenerateCall(boolean excelFlag) {
        try {
            getDiscount();
            if (pivotView.getValue().equals("Variable")) {
                pivotPanel.setCaption("Variable Pivot View");
                pivotPanel.setImmediate(true);
            } else {
                pivotPanel.setCaption("Period Pivot View");
                pivotPanel.setImmediate(true);
            }
            generated = true;
            firstGenerated = true;
            getCheckedValues();
            loadVariables();
            loadProjectionSelection();
            if (excelFlag) {
                configureExcelTable();
            } else {
                tableVerticalLayout.removeAllComponents();
                tableLogic.sinkItemPerPageWithPageLength(false);
                tableLogic = new ProjectionVarianceTableLogic();
                resultsTable = new FreezePagedTreeTable(tableLogic);
                initializeResultTable();
                configureTable();
                addResultTable();
                loadFromPeriod(String.valueOf(fromDate.getValue()));
                loadToPeriod(String.valueOf(fromDate.getValue()));
                generateLogic();
            }
            editViewBtn.setEnabled(CUSTOM.getConstant().equals(String.valueOf(view.getValue()))
                    && (!Constant.NULL.equals(customDdlb.getValue()) && !Constant.SELECT_ONE.equals(customDdlb.getValue())));
            generated = false;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Disable for total
     */
    @Override
    protected void setDisableFields() {
        if (level.getValue().equals(Constant.TOTAL)) {
            levelDdlb.setEnabled(false);
            group.setEnabled(false);
            levelFilter.setEnabled(false);
            view.setEnabled(false);
        } else {
            levelDdlb.setEnabled(true);
            group.setEnabled(true);
            levelFilter.setEnabled(true);
            view.setEnabled(true);
        }
    }

    public void loadAllLevelValue() {
        LOGGER.info("loadAllLevelValue initiated ");
        levelDdlb.setEnabled(true);
        levelDdlb.removeAllItems();
        levelDdlb.addItem(getSelectOne());
        levelDdlb.setNullSelectionItemId(getSelectOne());
        levelDdlb.setValue(getSelectOne());
        if (pvSelectionDTO.isIsCustomHierarchy()) {
            levelDdlb.setEnabled(false);
        } else {
            List<Leveldto> newLevelList = null;
            if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(pvSelectionDTO.getHierarchyIndicator())) {
                newLevelList = CommonLogic.getAllHierarchyLevels(pvSelectionDTO.getCustomerLevelNo(), projectionId, Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY, pvSelectionDTO.getGroupFilter(), Integer.valueOf(pvSelectionDTO.getUserId()), Integer.valueOf(pvSelectionDTO.getSessionId()), pvSelectionDTO.getCustRelationshipBuilderSid());
            } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(pvSelectionDTO.getHierarchyIndicator())) {
                newLevelList = CommonLogic.getAllHierarchyLevels(pvSelectionDTO.getProductLevelNo(), projectionId, Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY, pvSelectionDTO.getGroupFilter(), Integer.valueOf(pvSelectionDTO.getUserId()), Integer.valueOf(pvSelectionDTO.getSessionId()), pvSelectionDTO.getProdRelationshipBuilderSid());
            }
            if (newLevelList != null) {
                for (Leveldto levelDto : newLevelList) {
                    String levelFiterSid = levelDto.getLevelNo() + "~" + levelDto.getHierarchyNo();
                    String caption = levelDto.getRelationshipLevelValue();
                    Object itemId = levelFiterSid;
                    levelDdlb.addItem(itemId);
                    levelDdlb.setItemCaption(itemId, caption);
                }
            }
        }

        LOGGER.info("loadAllLevelValue ends ");
    }

    /**
     * Gets the bean from id.
     *
     * @param obj the obj
     * @return the bean from id
     */
    public ProjectionVarianceDTO getBeanFromId(Object obj) {

        BeanItem<?> targetItem = null;
        if (obj instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) obj;
        } else if (obj instanceof ProjectionVarianceDTO) {
            targetItem = new BeanItem<ProjectionVarianceDTO>(
                    (ProjectionVarianceDTO) obj);
        }
        return (ProjectionVarianceDTO) targetItem.getBean();
    }

    private void loadProjectionSelection() {
        pvSelectionDTO.setSessionDTO(session);
        pvSelectionDTO.setFrequency(String.valueOf(frequency.getValue()));
        pvSelectionDTO.setLevel(String.valueOf(level.getValue()));
        pvSelectionDTO.setProjectionPeriodOrder(String.valueOf(projectionPeriodOrder.getValue()));
        pvSelectionDTO.setDiscountLevel(String.valueOf(discountLevel.getValue()));
        pvSelectionDTO.setPivotView(String.valueOf(pivotView.getValue()));
        pvSelectionDTO.setFromDate(String.valueOf(fromDate.getValue()));
        pvSelectionDTO.setCurrentProjectionName("Current Projection");
        pvSelectionDTO.setProjIdList(projIdList);
        pvSelectionDTO.setProjectionMap(projectionMap);
        pvSelectionDTO.setVariableCategory(String.valueOf(variableCategory.getValue()));
        pvSelectionDTO.setUserId(Integer.valueOf(session.getUserId()));
        pvSelectionDTO.setSessionId(Integer.valueOf(session.getSessionId()));
        pvSelectionDTO.setCustRelationshipBuilderSid(session.getCustRelationshipBuilderSid());
        pvSelectionDTO.setProdRelationshipBuilderSid(session.getProdRelationshipBuilderSid());
        pvSelectionDTO.setForecastDTO(session.getForecastDTO());
        pvSelectionDTO.setProjectionId(projectionId);
        pvSelectionDTO.setCurrentProjId(projectionId);
        pvSelectionDTO.setProjectionNum(CommonUtils.getProjectionNumber(String.valueOf(frequency.getValue()), session));
        pvSelectionDTO.setProjectionOrder(String.valueOf(projectionPeriodOrder.getValue()));
        pvSelectionDTO.setCustomerLevelNo(Integer.valueOf(session.getCustomerLevelNumber()));
        pvSelectionDTO.setProductLevelNo(Integer.valueOf(session.getProductLevelNumber()));
        pvSelectionDTO.setVariables(variablesValue);
        pvSelectionDTO.setHistoryNum(CommonUtils.getHistoryProjectionNum(String.valueOf(frequency.getValue()), session));
        pvSelectionDTO.setCustomId(customId);
        viewChange(false);
        groupChange(false);
        setCurrentHierarchy(new ArrayList<Leveldto>(viewChangeHierarchy));
    }

    public void addLowerLevelsForExport(Object id) {
        pvSelectionDTO.setFilterLevelNo(0);
        pvSelectionDTO.setFilterHierarchyNo(StringUtils.EMPTY);
        pvSelectionDTO.setProductHierarchyNo(StringUtils.EMPTY);
        pvSelectionDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
        int count = logic.getConfiguredProjectionVarianceCount(id, pvSelectionDTO, pvSelectionDTO, true);
        List<ProjectionVarianceDTO> resultList = logic.getConfiguredProjectionVariance(id, pvSelectionDTO, pvSelectionDTO, 0, count);
        loadDataToContainer(resultList, id);
    }

    public List<Leveldto> getCurrentHierarchy() {
        return currentHierarchy;
    }

    public void setCurrentHierarchy(List<Leveldto> currentHierarchy) {
        this.currentHierarchy = currentHierarchy;
    }

    public void generateLogic() {

        levelFilter.removeValueChangeListener(levelFilterChangeOption);
        loadLevelAndFilterValue();
        pvSelectionDTO.setIslevelFiler(false);
        loadResultTable(0, StringUtils.EMPTY);
        levelFilter.addValueChangeListener(levelFilterChangeOption);
    }

    /**
     * Sets the base variables.
     *
     * @param columns the columns
     * @param varriables the varriables
     */
    public void setBaseVariables(String columns, String varriables) {
        LOGGER.info("Entering setBaseVariables method");

        pvSelectionDTO.setColValue(false);
        pvSelectionDTO.setColVariance(false);
        pvSelectionDTO.setColPercentage(false);
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
        varriables = varriables.substring(1, varriables.length() - 1);
        final String[] col = columns.split(",");
        final String[] var = varriables.split(",");
        for (String value : col) {

            value = StringUtils.trim(value);
            if (value.equalsIgnoreCase(StringUtils.trim(CommonUtils.COL_VALUE))) {
                pvSelectionDTO.setColValue(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(CommonUtils.COL_VARIANCE))) {
                pvSelectionDTO.setColVariance(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(CommonUtils.COL_PERCENTAGE))) {
                pvSelectionDTO.setColPercentage(true);
            }
        }

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

        LOGGER.info("End of setBaseVariables method");
    }

    /**
     * Load date range from and to
     *
     * @param doubleColumns
     */
    @Override
    public void loadFromPeriod(String fromDateVal) {
        fromDate.removeAllItems();
        fromDate.addItem(Constant.SELECT_ONE);
        List<String> periodList = new ArrayList<String>(pvSelectionDTO.getPeriodHeaderList());
        if (String.valueOf(projectionPeriodOrder.getValue()).equals(Constant.DESCENDING)) {
            Collections.reverse(periodList);
        }
        if (!periodList.isEmpty()) {
            for (int i = 0; i < periodList.size(); i++) {
                String header = pvSelectionDTO.getPeriodListMap().get(periodList.get(i));
                fromDate.addItem(header);
            }
        }
        fromDate.setValue(fromDateVal);
    }

    /**
     * load To DATE DDLB period
     *
     * @param fromDateVal
     * @param toDateVal
     */
    @Override
    public void loadToPeriod(String fromDateVal) {
        Object toDateVal = toDate.getValue();
        toDate.removeAllItems();
        toDate.addItem(Constant.SELECT_ONE);
        int start = 0;
        List<String> periodList = new ArrayList<String>(pvSelectionDTO.getPeriodHeaderList());
        if (String.valueOf(projectionPeriodOrder.getValue()).equals(Constant.DESCENDING)) {
            Collections.reverse(periodList);
        }
        if (fromDate.getValue() != null && !Constant.NULL.equals(String.valueOf(fromDate.getValue())) && !StringUtils.EMPTY.equals(String.valueOf(fromDate.getValue()))
                && !Constant.SELECT_ONE.equals(String.valueOf(fromDate.getValue())) && !fromDateVal.equals(Constant.SELECT_ONE)) {
            String fromVal = fromDateVal.replace(" ", StringUtils.EMPTY);
            if (pivotView.getValue().equals(Constant.PERIOD)) {
                fromVal = fromVal.toLowerCase();
            } else if (frequency.getValue().toString().equals(Constant.MONTHLY)) {
                fromVal = fromVal.toLowerCase();
            }
            start = periodList.indexOf(fromVal);
        }
        int end = periodList.size() - 1;

        for (int i = start; i <= end; i++) {
            toDate.addItem(pvSelectionDTO.getPeriodListMap().get(periodList.get(i)));
        }
        toDate.setValue(toDateVal);
    }

    private void loadComparison() {
        if (isComparisonLookupOpened) {
            ComparisonLookupDTO comparisonLookup = (ComparisonLookupDTO) comparison.getData();
            projectionMap = new HashMap<Integer, String>();
            projNameList = new ArrayList<String>();
            projIdList = new ArrayList<Integer>();
            if (comparisonLookup != null) {
                if (!comparisonLookup.getSelected().isEmpty()) {
                    for (ComparisonLookupDTO object : comparisonLookup.getSelected()) {
                        projNameList.add(object.getProjectionName());
                        projectionMap.put(object.getProjectionId(), object.getProjectionName());

                        projIdList.add(object.getProjectionId());
                    }
                } else if (!comparisonLookup.getProjectionMap().isEmpty()) {
                    for (Map.Entry<Integer, String> entry : comparisonLookup.getProjectionMap().entrySet()) {
                        Integer projId = entry.getKey();
                        String projName = entry.getValue();
                        projectionMap.put(projId, projName);
                        projIdList.add(projId);

                    }
                }
            }
            pvSelectionDTO.setProjIdList(projIdList);
            pvSelectionDTO.setProjectionMap(projectionMap);
        }
    }

    public void loadLevelFilterDDLB() {
        LOGGER.info("loadCustomDDLB initiated ");
        if (currentHierarchy != null) {
            levelFilter.removeAllItems();
            levelFilter.addItem(getSelectOne());
            levelFilter.setNullSelectionItemId(getSelectOne());
            for (Leveldto obj : currentHierarchy) {
                int levelFiterSid = obj.getTreeLevelNo();
                Object itemId = levelFiterSid;
                levelFilter.addItem(itemId);
                levelFilter.setItemCaption(itemId, obj.getLevel());
            }
            levelFilter.setValue(getSelectOne());
        }
        LOGGER.info("loadCustomDDLB ends ");
    }

    public void loadGroupFilterOntabChange() {
        loadGroupFilter(session.getProjectionId(), Integer.valueOf(pvSelectionDTO.getUserId()), Integer.valueOf(pvSelectionDTO.getSessionId()), true);
    }

    public void groupChange(boolean groupChange) {
        if (group.getValue() != null && (pvSelectionDTO.isIsCustomHierarchy() || !pvSelectionDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY))) {
            pvSelectionDTO.setGroupFilter(String.valueOf(group.getValue()));
        }
        if (groupChange && firstGenerated && !generated && (pvSelectionDTO.isIsCustomHierarchy() || !pvSelectionDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY))) {
            tableLogic.groupChange();
        }
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
            } else {
                levelFilter.setEnabled(true);
                level.setEnabled(true);
                customIdToSelect = customId;
                pvSelectionDTO.setIsCustomHierarchy(false);
                pvSelectionDTO.setTpLevel(tradingPartnerNo);
                if (CUSTOMER.getConstant().equals(String.valueOf(view.getValue()))) {
                    pvSelectionDTO.setHierarchyIndicator(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
                    pvSelectionDTO.setRelationshipBuilderSid(pvSelectionDTO.getCustRelationshipBuilderSid());
                    if (viewChange) {
                        callGenerateLogic();
                    }
                } else if (PRODUCT.getConstant().equals(String.valueOf(view.getValue()))) {
                    pvSelectionDTO.setHierarchyIndicator(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
                    pvSelectionDTO.setRelationshipBuilderSid(pvSelectionDTO.getProdRelationshipBuilderSid());
                    if (viewChange) {
                        callGenerateLogic();
                    }
                }
            }
        }
    }

    public void callGenerateLogic() {
        if (firstGenerated && !generated) {
            generateLogic();
        }
    }

    public void loadGroupFilter(int projId, int userId, int sessionId, boolean isPPA) {
        LOGGER.info("loadGroupFilter initiated ");
        group.setEnabled(true);
        group.removeAllItems();
        group.setNullSelectionAllowed(false);
        List<String> groupList = CommonLogic.getAllGroup(projId, userId, sessionId, isPPA);
        if (groupList != null && !groupList.isEmpty()) {
            for (String groups : groupList) {
                group.addItem(groups);
            }
            group.select(groupList.get(0));
        }

        LOGGER.info("loadGroupFilter ends ");
    }

    public static List<Date> getStartandTodate() {
        List<Date> result = new ArrayList<Date>();
        ForecastConfig forecastConfig = getTimePeriod();
        Date fromDate = null;
        Date toDate = null;
        if (forecastConfig != null) {
            if (forecastConfig.getProcessMode()) {
                if ("Month".equalsIgnoreCase(String.valueOf(forecastConfig.getHistFreq()))) {
                    fromDate = DataSelectionUtil.calculateHistory("Month", forecastConfig.getHistValue());
                    toDate = DataSelectionUtil.calculateProjection("Month", forecastConfig.getProjValue());
                } else if ("Quarter".equalsIgnoreCase(String.valueOf(forecastConfig.getHistFreq()))) {
                    fromDate = DataSelectionUtil.calculateHistory("Quarter", forecastConfig.getHistValue());
                    toDate = DataSelectionUtil.calculateProjection("Quarter", forecastConfig.getProjValue());
                } else if ("SemiAnnual".equalsIgnoreCase(String.valueOf(forecastConfig.getHistFreq()))) {
                    fromDate = DataSelectionUtil.calculateHistory("SemiAnnual", forecastConfig.getHistValue());
                    toDate = DataSelectionUtil.calculateProjection("SemiAnnual", forecastConfig.getProjValue());
                } else if ("Annual".equalsIgnoreCase(String.valueOf(forecastConfig.getHistFreq()))) {
                    fromDate = DataSelectionUtil.calculateHistory("Annual", forecastConfig.getHistValue());
                    toDate = DataSelectionUtil.calculateProjection("Annual", forecastConfig.getProjValue());
                }
            } else {
                fromDate = forecastConfig.getFromDate();
                toDate = forecastConfig.getToDate();
            }
        }
        result.add(fromDate);
        result.add(toDate);
        return result;

    }

    public static ForecastConfig getTimePeriod() {
        DataSelectionDAO dataSelectionDao = new DataSelectionDAOImpl();
        List resultList = null;
        int businessProcessType = 0;
        try {
            businessProcessType = CommonUtils.getHelperCode(CommonUtils.BUSINESS_PROCESS_TYPE, getCommercialConstant());
        } catch (PortalException ex) {
            java.util.logging.Logger.getLogger(NMProjectionVariance.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            java.util.logging.Logger.getLogger(NMProjectionVariance.class.getName()).log(Level.SEVERE, null, ex);
        }
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ForecastConfig.class);
        dynamicQuery.add(RestrictionsFactoryUtil.eq("businessProcessType", businessProcessType));
        dynamicQuery.addOrder(OrderFactoryUtil.desc(Constant.VERSION_NO));
        try {
            resultList = dataSelectionDao.getForecastConfig(dynamicQuery);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(CommonUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        ForecastConfig forecastConfig = null;
        if (resultList != null && !resultList.isEmpty()) {
            forecastConfig = (ForecastConfig) resultList.get(0);
        }
        return forecastConfig;
    }

    public static int getProjections(Date startDate, Date endDate, String frequency) {

        if (frequency.equals(Constant.ANNUALLY)) {
            return endDate.getYear() - new Date().getYear();
        } else {
            Calendar startCalendar = new GregorianCalendar();
            startCalendar.setTime(new Date());
            Calendar endCalendar = new GregorianCalendar();
            endCalendar.setTime(endDate);
            int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
            int diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
            if (frequency.equals(Constant.QUARTERLY)) {
                if (diffMonth % 3 == 0) {
                    return diffMonth / 3;
                } else {
                    return (diffMonth / 3) + 1;
                }

            } else if (frequency.equals(Constant.SEMIANNUALLY)) {
                if (diffMonth % 6 == 0) {
                    return diffMonth / 6;
                } else {
                    return (diffMonth / 6) + 1;
                }
            } else if (frequency.equals(Constant.MONTHLY)) {
                return diffMonth;
            }
            return 0;
        }
    }

    public void fetchDiscountsFromSave() throws Exception {
        Map<Object, Object> map = CommonLogic.getNMProjectionSelection(projectionId, TAB_DISCOUNT_PROJECTION.getConstant());
        if (map != null && map.size() > 0) {
            String discountType = map.get("Level").toString();
            if (PROGRAM.getConstant().equals(discountType)) {
                List<String> discountNames1 = new LinkedList<String>(Arrays.asList(String.valueOf(map.get("Selected Discounts")).split("\\s*,\\s*")));
                if (discountLevel.getValue().equals("Program Category")) {
                    List<List<String>> discList = CommonLogic.getPriceGroupTypeList(projectionId, discountNames1, String.valueOf(pvSelectionDTO.getUserId()), String.valueOf(pvSelectionDTO.getSessionId()));
                    discountlist.add(discList.get(0));
                    discountlist.add(discList.get(1));
                } else {
                    discountlist = CommonLogic.getDiscountNoList(projectionId, discountNames1, true, String.valueOf(pvSelectionDTO.getUserId()), String.valueOf(pvSelectionDTO.getSessionId()));
                }
            } else if (PROGRAM_CATEGORY.getConstant().equals(discountType)) {
                List<String> priceGroupType = new LinkedList<String>(Arrays.asList(String.valueOf(map.get("Selected Discounts")).split("\\s*,\\s*")));
                if (discountLevel.getValue().equals("Program Category")) {
                    List<List<String>> discList = CommonLogic.getDiscountNoList(projectionId, priceGroupType, false, String.valueOf(pvSelectionDTO.getUserId()), String.valueOf(pvSelectionDTO.getSessionId()));
                    discountlist.add(discList.get(0));
                    discountlist.add(priceGroupType);
                } else {
                    discountlist = CommonLogic.getDiscountNoList(projectionId, priceGroupType, false, String.valueOf(pvSelectionDTO.getUserId()), String.valueOf(pvSelectionDTO.getSessionId()));
                }
            }
        }
    }

    public void getDiscount() throws Exception {
        NMDiscountProjection dp = nonMandatedForm.getDiscountProjection();
        if (dp != null) {
            String discountType = nonMandatedForm.getDiscountProjection().getDiscountType();
            discountlist = new ArrayList<List<String>>();
            if (nonMandatedForm.getDiscountProjection().isDiscountGenerated() && discountType != null) {
                if (PROGRAM_CATEGORY.getConstant().equals(discountType)) {
                    List<String> priceGroupType = nonMandatedForm.getDiscountProjection().getDiscountNamesList();
                    if (discountLevel.getValue().equals("Program Category")) {
                        List<List<String>> discList = CommonLogic.getDiscountNoList(projectionId, priceGroupType, false,String.valueOf(pvSelectionDTO.getUserId()),String.valueOf(pvSelectionDTO.getSessionId()));
                        discountlist.add(discList.get(0));
                        discountlist.add(priceGroupType);
                    } else {
                        discountlist = CommonLogic.getDiscountNoList(projectionId, priceGroupType, false,String.valueOf(pvSelectionDTO.getUserId()),String.valueOf(pvSelectionDTO.getSessionId()));
                    }
                } else if (PROGRAM.getConstant().equals(discountType)) {
                    List<String> discountNameList = nonMandatedForm.getDiscountProjection().getDiscountNamesList();
                    if (discountLevel.getValue().equals("Program Category")) {
                        List<List<String>> discList = CommonLogic.getPriceGroupTypeList(projectionId, discountNameList,String.valueOf(pvSelectionDTO.getUserId()),String.valueOf(pvSelectionDTO.getSessionId()));
                        discountlist.add(discList.get(0));
                        discountlist.add(discList.get(1));
                    } else {
                        discountlist = CommonLogic.getDiscountNoList(projectionId, discountNameList, true,String.valueOf(pvSelectionDTO.getUserId()),String.valueOf(pvSelectionDTO.getSessionId()));
                    }
                }
            } else if (Constant.EDIT_SMALL.equalsIgnoreCase(session.getAction()) || Constant.VIEW.equalsIgnoreCase(session.getAction())) {
                fetchDiscountsFromSave();
            }
        }
        pvSelectionDTO.setDiscountList(new ArrayList<List<String>>(discountlist));
        pvSelectionDTO.setSession(session);
    }

    public void setExpandIconAction(Object propertyId, boolean collapsed, PVSelectionDTO projSel, List<String> discountNames) {
        ExtFilterTreeTable rightTable = resultsTable
                .getRightFreezeAsTable();
        List<Integer> projList = projSel.getProjIdList();
        String commonColumn = String.valueOf(propertyId);
        if (!discountNames.isEmpty()) {
            for (int i = 0; i < discountNames.size(); i++) {
                String commonHeader = discountNames.get(i);
                String commonCol = commonColumn + commonHeader.replace(" ", StringUtils.EMPTY) + i;
                rightTable.setDoubleHeaderColumnCollapsed(commonCol, collapsed);
                rightTable.setColumnCollapsed(commonCol + Constant.CURRENT + projSel.getCurrentProjId(), collapsed);
                if (!projList.isEmpty()) {
                    for (int j = 0; j < projList.size(); j++) {
                        rightTable.setColumnCollapsed(commonCol + projList.get(j), collapsed);
                    }
                }
            }
        }

        // Removed PPA and Returns static lines as per GALUAT-321 issue
        /*
         String commonCol = StringUtils.EMPTY;
         if (pvSelectionDTO.isPpa()) {
         commonCol = commonColumn + Constant.PPA_SMALL;
         rightTable.setDoubleHeaderColumnCollapsed(commonCol, collapsed);
         rightTable.setColumnCollapsed(commonCol + Constant.CURRENT + projSel.getCurrentProjId(), collapsed);
         if (!projList.isEmpty()) {
         for (int j = 0; j < projList.size(); j++) {
         rightTable.setColumnCollapsed(commonCol + projList.get(j), collapsed);
         }
         }
         }
         if (pvSelectionDTO.isReturns()) {
         commonCol = commonColumn + "Returns";
         rightTable.setDoubleHeaderColumnCollapsed(commonCol, collapsed);
         rightTable.setColumnCollapsed(commonCol + Constant.CURRENT + projSel.getCurrentProjId(), collapsed);
         if (!projList.isEmpty()) {
         for (int j = 0; j < projList.size(); j++) {
         rightTable.setColumnCollapsed(commonCol + projList.get(j), collapsed);
         }
         }
         }
         */
        /*  for (int i = 0; i < discountNames.size(); i++) {
         String commonColumn = propertyId + discountNames.get(i).replace(" ", StringUtils.EMPTY);
         rightTable.setDoubleHeaderColumnCollapsed(commonColumn, collapsed);
         }*/
    }

    public void loadVariables() {
        loadComparison();
        setBaseVariables(String.valueOf(variableCategory.getValue()), variablesValue);
    }

    private void alignRight() {
        for (Object obj : rightHeader.getSingleColumns()) {
            resultsTable.getRightFreezeAsTable().setColumnAlignment(obj, ExtCustomTable.Align.RIGHT);
        }
    }

    public int getTabNumber() {
        return Constant.SEVEN;
    }

    public void pushUpdate(String indicator) {
        if (Constants.IndicatorConstants.INDICATOR_REFRESH_UPDATE.getConstant().equals(indicator)) {
            tradingPartnerNo = CommonLogic.getTradingPartnerLevelNo(false, projectionId);

        }
        if (Constants.IndicatorConstants.INDICATOR_TIME_PERIOD_CHANGED.getConstant().equals(indicator)) {
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        projectionId = session.getProjectionId();
        pvSelectionDTO.setUserId(Integer.valueOf(session.getUserId()));
        pvSelectionDTO.setSessionId(Integer.valueOf(session.getSessionId()));

        tradingPartnerNo = CommonLogic.getTradingPartnerLevelNo(false, projectionId);
    }

    @Override
    protected void callTableLogic(int levelNo, String hierarchyNo, final boolean excelFlag) {

        loadVariables();
        if (excelFlag) {
            loadExcelResultTable(levelNo, hierarchyNo);
        } else {
            tableLogic.clearAll();
            loadResultTable(levelNo, hierarchyNo);
        }
    }

    public void savePvSelections() {

        LOGGER.info("savePVSelections method starts");
        Map map = new HashMap();
        try {
            String priorProjectionIds = projIdList == null || projIdList.isEmpty() ? StringUtils.EMPTY : projIdList.toString();

            map.put("Comparison", priorProjectionIds);
            map.put("Level", level.getValue().toString());
            map.put("Projection Period Order", projectionPeriodOrder.getValue().toString());
            map.put("From", fromDate.getValue() != null ? fromDate.getValue().toString() : StringUtils.EMPTY);
            map.put("To", toDate.getValue() != null ? toDate.getValue().toString() : StringUtils.EMPTY);
            map.put(Constant.FREQUENCY_SMALL, frequency.getValue() != null ? frequency.getValue().toString() : StringUtils.EMPTY);
            map.put("Discount Level", discountLevel.getValue() != null ? discountLevel.getValue().toString() : StringUtils.EMPTY);
            map.put("Pivot View", pivotView.getValue() != null ? pivotView.getValue().toString() : StringUtils.EMPTY);
            map.put("Variable Category", variableCategory.getValue() != null ? variableCategory.getValue().toString() : StringUtils.EMPTY);
            map.put(Constant.VARIABLES, getCheckedValues());
            logic.saveNMPVSelection(map, projectionId, "Projection Variance");
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("savePVSelections method ends");

    }

    public void setProjectionSelection() {
        Map<Object, Object> map = CommonLogic.getNMProjectionSelection(projectionId, "Projection Variance");        
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
            if (value != null && customMenuItem != null && value.toString().length() > 0) {
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

    private void security() throws PortalException, SystemException, Exception {

        final Map<String, AppPermission> functionPsHM = stplSecurity.getBusinessFunctionPermission(userId, getCommercialConstant() + "," + UISecurityUtil.PROJECTION_RESULTS);

        if (!(functionPsHM.get(FunctionNameUtil.GENERATE) != null && ((AppPermission) functionPsHM.get(FunctionNameUtil.GENERATE)).isFunctionFlag())) {
            generateBtn.setVisible(Boolean.FALSE);
            editViewBtn.setVisible(Boolean.FALSE);
            collapseLvlBtn.setVisible(Boolean.FALSE);
            expandLvlBtn.setVisible(Boolean.FALSE);
            addViewBtn.setVisible(Boolean.FALSE);
        }
    }

    private void loadComparisonOnEdit(String projectionIds) {
        List<List> resultList = logic.getComparisonProjections(projectionIds);
        if (resultList != null && !resultList.isEmpty()) {
            projIdList = resultList.get(0);
            projNameList = resultList.get(1);
            projectionMap = new HashMap<>();
            for (int i = 0; i < projIdList.size(); i++) {
                projectionMap.put(projIdList.get(i), projNameList.get(i));
            }
            comparison.setValue(projNameList.size() > 1 ? Constant.MULTIPLE : projNameList.get(0));
            pvSelectionDTO.setProjIdList(projIdList);
            pvSelectionDTO.setProjectionMap(projectionMap);
            comparisonProjId.addAll(projIdList);
        }
    }

    public void configure() {
        if (flag) {
            try {
                configureFields();
                security();
                flag = false;
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(NMProjectionVariance.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setPvSelection(String columns, String varriables) {
        pvSelectionDTO.setColValue(false);
        pvSelectionDTO.setColVariance(false);
        pvSelectionDTO.setColPercentage(false);
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
        varriables = varriables.substring(1, varriables.length() - 1);
        final String[] col = columns.split(",");
        final String[] var = varriables.split(",");
        for (String value : col) {

            value = StringUtils.trim(value);
            if (value.equalsIgnoreCase(StringUtils.trim(CommonUtils.COL_VALUE))) {
                pvSelectionDTO.setColValue(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(CommonUtils.COL_VARIANCE))) {
                pvSelectionDTO.setColVariance(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(CommonUtils.COL_PERCENTAGE))) {
                pvSelectionDTO.setColPercentage(true);
            }
        }

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

    private void excelForCommercial() {
        try {
            configureExcelTable();
            getExcelProcedureInput();
            excelLogic.getPVData();
            excelParentRecords.clear();

            long start = System.currentTimeMillis();
            long end;
          
            if (pvSelectionDTO.getLevel().equals(Constants.LabelConstants.TOTAL.toString())) {

                List<ProjectionVarianceDTO> varibaleList = resultMap.get(Constants.LabelConstants.TOTAL.toString());
               
                if (varibaleList != null) {
                    for (Iterator<ProjectionVarianceDTO> it1 = varibaleList.listIterator(); it1.hasNext();) {
                        ProjectionVarianceDTO itemId = it1.next();
                        it1.remove();
                        resultExcelContainer.addBean(itemId);
                        resultExcelContainer.setChildrenAllowed(itemId, false);
                        if (!Constants.LabelConstants.TOTAL_DISCOUNT.toString().equals(pvSelectionDTO.getDiscountLevel())) {
                            if (itemId.getGroup().startsWith(Constants.LabelConstants.DISCOUNT.toString()) || itemId.getGroup().startsWith(PVVariables.VAR_RPU.toString())) {
                                if (itemId.getGroup().startsWith("Discount $ Value")) {
                                    resultExcelContainer.setChildrenAllowed(itemId, true);
                                    List<ProjectionVarianceDTO> discountDollarList = resultMap.get("D$value");
                                    for (Iterator<ProjectionVarianceDTO> it2 = discountDollarList.listIterator(); it2.hasNext();) {
                                        ProjectionVarianceDTO childItem = it2.next();
                                        it2.remove();
                                        resultExcelContainer.addBean(childItem);
                                        resultExcelContainer.setChildrenAllowed(childItem, false);
                                        resultExcelContainer.setParent(childItem, itemId);
                                    }

                                }
                                if (itemId.getGroup().startsWith("Discount $ Variance")) {
                                    resultExcelContainer.setChildrenAllowed(itemId, true);
                                    List<ProjectionVarianceDTO> discountvarianceList = resultMap.get("D$variance");
                                    for (Iterator<ProjectionVarianceDTO> it3 = discountvarianceList.listIterator(); it3.hasNext();) {
                                        ProjectionVarianceDTO childItem = it3.next();
                                        it3.remove();
                                        resultExcelContainer.addBean(childItem);
                                        resultExcelContainer.setChildrenAllowed(childItem, false);
                                        resultExcelContainer.setParent(childItem, itemId);
                                    }
                                }
                                if (itemId.getGroup().startsWith("Discount $ %Change")) {
                                    resultExcelContainer.setChildrenAllowed(itemId, true);
                                    List<ProjectionVarianceDTO> discountDollarList = resultMap.get("D$change");
                                    for (Iterator<ProjectionVarianceDTO> it4 = discountDollarList.listIterator(); it4.hasNext();) {
                                        ProjectionVarianceDTO childItem = it4.next();
                                        it4.remove();
                                        resultExcelContainer.addBean(childItem);
                                        resultExcelContainer.setChildrenAllowed(childItem, false);
                                        resultExcelContainer.setParent(childItem, itemId);
                                    }
                                }
                                if (itemId.getGroup().startsWith("Discount % Value")) {
                                    resultExcelContainer.setChildrenAllowed(itemId, true);
                                    List<ProjectionVarianceDTO> discountDollarList = resultMap.get("D%value");
                                    for (Iterator<ProjectionVarianceDTO> it5 = discountDollarList.listIterator(); it5.hasNext();) {
                                        ProjectionVarianceDTO childItem = it5.next();
                                        it5.remove();
                                        resultExcelContainer.addBean(childItem);
                                        resultExcelContainer.setChildrenAllowed(childItem, false);
                                        resultExcelContainer.setParent(childItem, itemId);
                                    }
                                }
                                if (itemId.getGroup().startsWith("Discount % Variance")) {
                                    resultExcelContainer.setChildrenAllowed(itemId, true);
                                    List<ProjectionVarianceDTO> discountDollarList = resultMap.get("D%variance");
                                    for (Iterator<ProjectionVarianceDTO> it6 = discountDollarList.listIterator(); it6.hasNext();) {
                                        ProjectionVarianceDTO childItem = it6.next();
                                        it6.remove();
                                        resultExcelContainer.addBean(childItem);
                                        resultExcelContainer.setChildrenAllowed(childItem, false);
                                        resultExcelContainer.setParent(childItem, itemId);
                                    }
                                }
                                if (itemId.getGroup().startsWith("Discount % %Change")) {
                                    resultExcelContainer.setChildrenAllowed(itemId, true);
                                    List<ProjectionVarianceDTO> discountDollarList = resultMap.get("D%change");
                                    for (Iterator<ProjectionVarianceDTO> it7 = discountDollarList.listIterator(); it7.hasNext();) {
                                        ProjectionVarianceDTO childItem = it7.next();
                                        it7.remove();
                                        resultExcelContainer.addBean(childItem);
                                        resultExcelContainer.setChildrenAllowed(childItem, false);
                                        resultExcelContainer.setParent(childItem, itemId);
                                    }
                                }

                                if (itemId.getGroup().startsWith("RPU Value")) {
                                    resultExcelContainer.setChildrenAllowed(itemId, true);
                                    List<ProjectionVarianceDTO> discountDollarList = resultMap.get("RPU-value");
                                    for (Iterator<ProjectionVarianceDTO> it8 = discountDollarList.listIterator(); it8.hasNext();) {
                                        ProjectionVarianceDTO childItem = it8.next();
                                        it8.remove();
                                        resultExcelContainer.addBean(childItem);
                                        resultExcelContainer.setChildrenAllowed(childItem, false);
                                        resultExcelContainer.setParent(childItem, itemId);
                                    }
                                }
                                if (itemId.getGroup().startsWith("RPU Variance")) {
                                    resultExcelContainer.setChildrenAllowed(itemId, true);
                                    List<ProjectionVarianceDTO> discountDollarList = resultMap.get("RPU-variance");
                                    for (Iterator<ProjectionVarianceDTO> it9 = discountDollarList.listIterator(); it9.hasNext();) {
                                        ProjectionVarianceDTO childItem = it9.next();
                                        it9.remove();
                                        resultExcelContainer.addBean(childItem);
                                        resultExcelContainer.setChildrenAllowed(childItem, false);
                                        resultExcelContainer.setParent(childItem, itemId);
                                    }
                                }
                                if (itemId.getGroup().startsWith("RPU %Change")) {
                                    resultExcelContainer.setChildrenAllowed(itemId, true);
                                    List<ProjectionVarianceDTO> discountDollarList = resultMap.get("RPU-change");
                                    for (Iterator<ProjectionVarianceDTO> it10 = discountDollarList.listIterator(); it10.hasNext();) {
                                        ProjectionVarianceDTO childItem = it10.next();
                                        it10.remove();
                                        resultExcelContainer.addBean(childItem);
                                        resultExcelContainer.setChildrenAllowed(childItem, false);
                                        resultExcelContainer.setParent(childItem, itemId);
                                    }
                                }
//                            resultExcelContainer.setChildrenAllowed(itemId, true);
                            } else {
                                resultExcelContainer.setChildrenAllowed(itemId, false);
                            }
                        } else {
                            resultExcelContainer.setChildrenAllowed(itemId, false);
                        }

                    }
                }
            } else {
                Collections.sort(hierarchyKeys);

                for (Iterator<String> it = hierarchyKeys.listIterator(); it.hasNext();) {
                    String key = it.next();
                    it.remove();

                    List<ProjectionVarianceDTO> varibaleList = resultMap.get(key);

                    resultMap.remove(key);

                    Object parentItemId = null;
                    if (tradingPartnerKeys.contains(key)) {
                        int index = 0;
                        for (Iterator<ProjectionVarianceDTO> it1 = varibaleList.listIterator(); it1.hasNext();) {
                            ProjectionVarianceDTO itemId = it1.next();
                            it1.remove();
                            resultExcelContainer.addBean(itemId);
                            if (index++ == 0) {
                                String parentKey = key.substring(0, key.lastIndexOf('.'));
                                if (parentKey.lastIndexOf('.') >= 0) {
                                    parentKey = parentKey.substring(0, parentKey.lastIndexOf('.') + 1);
                                }
                                String groupParentKey = parentKey + pvSelectionDTO.getGroupFilter();

                                parentItemId = excelParentRecords.get(parentKey);
                                Object gropuParentItemId = excelParentRecords.get(groupParentKey);

                                if (gropuParentItemId == null) {
                                    ProjectionVarianceDTO tpGroup = new ProjectionVarianceDTO() {
                                        {
                                            setGroup(pvSelectionDTO.getGroupFilter());
                                        }
                                    };
                                    resultExcelContainer.addBean(tpGroup);
                                    gropuParentItemId = tpGroup;
                                    resultExcelContainer.setParent(gropuParentItemId, parentItemId);
                                }
                                if (parentItemId != null) {
                                    resultExcelContainer.setParent(itemId, gropuParentItemId);
                                } else {
                                    parentItemId = gropuParentItemId;
                                }
                                excelParentRecords.put(key, itemId);
                                resultExcelContainer.setParent(itemId, gropuParentItemId);
                                resultExcelContainer.setParent(gropuParentItemId, parentItemId);
                                excelParentRecords.put(parentKey + pvSelectionDTO.getGroupFilter(), gropuParentItemId);
                                parentItemId = itemId;
                                resultExcelContainer.setChildrenAllowed(itemId, true);
                            } else {
                                resultExcelContainer.setChildrenAllowed(itemId, false);
                                resultExcelContainer.setParent(itemId, parentItemId);

                                if (!Constants.LabelConstants.TOTAL_DISCOUNT.toString().equals(pvSelectionDTO.getDiscountLevel())) {

                                    if (itemId.getGroup().startsWith(Constants.LabelConstants.DISCOUNT.toString()) || itemId.getGroup().startsWith(PVVariables.VAR_RPU.toString())) {
                                        resultExcelContainer.setChildrenAllowed(itemId, true);
                                        excelParentRecords.put(key + itemId.getGroup(), itemId);

                                    }
                                }
                            }
                        }
                    } else {
                        int index = 0;
                        for (Iterator<ProjectionVarianceDTO> it1 = varibaleList.listIterator(); it1.hasNext();) {
                            ProjectionVarianceDTO itemId = it1.next();
                            it1.remove();
                            resultExcelContainer.addBean(itemId);

                            if (index++ == 0) {
                                String parentKey = key.substring(0, key.lastIndexOf('.'));
                                if (parentKey.lastIndexOf('.') >= 0) {
                                    parentKey = parentKey.substring(0, parentKey.lastIndexOf('.') + 1);
                                }

                                parentItemId = excelParentRecords.get(parentKey);

                                if (parentItemId != null) {
                                    resultExcelContainer.setParent(itemId, parentItemId);
                                }
                                parentItemId = itemId;
                                excelParentRecords.put(key, itemId);
                                resultExcelContainer.setChildrenAllowed(itemId, true);
                            } else {

                                resultExcelContainer.setParent(itemId, parentItemId);
                                resultExcelContainer.setChildrenAllowed(itemId, false);
                                if (!Constants.LabelConstants.TOTAL_DISCOUNT.toString().equals(pvSelectionDTO.getDiscountLevel())) {
                                    if (itemId.getGroup().startsWith(Constants.LabelConstants.DISCOUNT.toString()) || itemId.getGroup().startsWith(PVVariables.VAR_RPU.toString())) {
                                        resultExcelContainer.setChildrenAllowed(itemId, true);
                                        excelParentRecords.put(key + itemId.getGroup(), itemId);
                                    }
                                }
                            }
                        }
                    }
                }
                for (ListIterator<String> iterator = discountKeys.listIterator(); iterator.hasNext();) {
                    String key = iterator.next();
                    iterator.remove();
                    
                    List<ProjectionVarianceDTO> varibaleList = resultMap.get(key);
                    resultMap.remove(key);
                    Object parentItemId = excelParentRecords.get(key);

                    for (Iterator<ProjectionVarianceDTO> it1 = varibaleList.listIterator(); it1.hasNext();) {
                        ProjectionVarianceDTO itemId = it1.next();
                        it1.remove();
                        resultExcelContainer.addBean(itemId);
                        resultExcelContainer.setParent(itemId, parentItemId);
                        resultExcelContainer.setChildrenAllowed(itemId, false);
                    }
                }
            }
            end = System.currentTimeMillis();
           
            excelParentRecords.clear();
        } catch (Exception e) {
           LOGGER.error(e);
        }
    }

    public void getExcelProcedureInput() {
      
        String splitarr[] = ("All Sales Group".equalsIgnoreCase(pvSelectionDTO.getGroupFilter())
                || "All Discount Group".equalsIgnoreCase(pvSelectionDTO.getGroupFilter())
                || "All PPA Group".equalsIgnoreCase(pvSelectionDTO.getGroupFilter()))
                ? pvSelectionDTO.getGroupFilter().split(" ") : pvSelectionDTO.getGroupFilter().split("-");
        String groupFilter = ("All Sales Group".equalsIgnoreCase(pvSelectionDTO.getGroupFilter()) || "All Discount Group".equalsIgnoreCase(pvSelectionDTO.getGroupFilter()) || "All PPA Group".equalsIgnoreCase(pvSelectionDTO.getGroupFilter())) ? splitarr[1] : splitarr[0];
        String groupFilterValue = ("All Sales Group".equalsIgnoreCase(pvSelectionDTO.getGroupFilter()) || "All Discount Group".equalsIgnoreCase(pvSelectionDTO.getGroupFilter()) || "All PPA Group".equalsIgnoreCase(pvSelectionDTO.getGroupFilter())) ? "All" : splitarr[1];
        String discountLevelValue = "Total Discount".equalsIgnoreCase(pvSelectionDTO.getDiscountLevel()) ? "" : pvSelectionDTO.getDiscountLevel();

//   Map<String,String> excelInput=new HashMap();
        //User Id
        parameterDto.setUserId(sessionDTO.getUserId());
        //Session Id
        parameterDto.setSessionId(sessionDTO.getSessionId());
        //Discount Level
        parameterDto.setDiscountLevel(discountLevelValue);
        //Discount Id
//        parameters.put("Discount_ID", "");
        //Frequency Value
        parameterDto.setFrequency(pvSelectionDTO.getFrequencyDivision() == 1 ? "A" : pvSelectionDTO.getFrequencyDivision() == 2 ? "S" : pvSelectionDTO.getFrequencyDivision() == 4 ? "Q" : "M");
        //Current Hierarchy Indicator Value
        parameterDto.setViewIndicator(pvSelectionDTO.getHierarchyIndicator());
        //Group Filter Value
        parameterDto.setGroupFilter(groupFilter);
        //Group Filter Value
        parameterDto.setGroupFilterValue(groupFilterValue);
        //Hierarchy No
        parameterDto.setHierarchyNo("");
        //Level Filte Level No Value
        parameterDto.setLevelNo("0");
        //Level Filte Level No Value
        parameterDto.setViewName("DETAIL_TOTAL_DISCOUNT");
        int customMasterSid = Integer.valueOf(customDdlb.getValue() == null ? "0" : customDdlb.getValue().toString());
        parameterDto.setCustomViewMasterSid(customMasterSid);
        // setting pirror projection ids
        parameterDto.getPirorProjectionIds().clear();
        parameterDto.getPirorProjectionIds().addAll(pvSelectionDTO.getProjIdList());
        parameterDto.setView(pivotView.getValue().toString());
}
}
