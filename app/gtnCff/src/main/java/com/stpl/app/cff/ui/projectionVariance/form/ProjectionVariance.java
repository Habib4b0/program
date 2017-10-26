/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.projectionVariance.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.cff.abstractCff.AbstractProjectionVariance;
import com.stpl.app.cff.dao.DataSelectionDAO;
import com.stpl.app.cff.dao.impl.DataSelectionDAOImpl;
import com.stpl.app.cff.dto.PVChart;
import com.stpl.app.cff.dto.PVGraphWindow;
import com.stpl.app.cff.dto.PVSelectionDTO;
import com.stpl.app.cff.dto.SessionDTO;
import com.stpl.app.cff.lazyLoad.VarianceTableLogic;
import com.stpl.app.cff.logic.CommonLogic;
import com.stpl.app.cff.security.StplSecurity;
import com.stpl.app.cff.ui.ConsolidatedFinancialForecastUI;
import com.stpl.app.cff.ui.dataSelection.dto.ForecastDTO;
import com.stpl.app.cff.ui.fileSelection.Util.ConstantsUtils;
import com.stpl.app.cff.ui.projectionVariance.dto.ComparisonLookupDTO;
import com.stpl.app.cff.ui.projectionVariance.dto.FilterGenerator;
import com.stpl.app.cff.ui.projectionVariance.dto.PVParameters;
import com.stpl.app.cff.ui.projectionVariance.dto.ProjectionVarianceDTO;
import com.stpl.app.cff.ui.projectionVariance.logic.PVExcelLogic;
import com.stpl.app.cff.ui.projectionVariance.logic.ProjectionVarianceLogic;
import static com.stpl.app.cff.ui.projectionresults.form.ProjectionResults.LOGGER;
import com.stpl.app.cff.util.AbstractNotificationUtils;
import com.stpl.app.cff.util.CommonUtils;
import com.stpl.app.cff.util.Constants;
import static com.stpl.app.cff.util.Constants.LabelConstants.*;
import com.stpl.app.cff.util.ConstantsUtil;
import com.stpl.app.cff.util.DataSelectionUtil;
import com.stpl.app.cff.util.HeaderUtils;
import com.stpl.app.cff.util.PVQueryUtils;
import com.stpl.app.cff.util.UiUtils;
import com.stpl.app.model.ForecastConfig;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import static com.stpl.app.serviceUtils.ConstantUtil.SELECT_ONE;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import static com.stpl.ifs.util.constants.GlobalConstants.getCommercialConstant;
import static com.stpl.ifs.util.constants.GlobalConstants.getSelectOne;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTreeTable;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 * @author mohamed.hameed
 */
public class ProjectionVariance extends AbstractProjectionVariance {

    boolean editFlag = false;
    List<ComparisonLookupDTO> selectedList = new ArrayList<ComparisonLookupDTO>();
    PVQueryUtils queryUtils = new PVQueryUtils();
    /**
     * The excel export image.
     */
    private final Resource excelExportImage = new ThemeResource("../../icons/excel.png");
    List<Leveldto> viewChangeHierarchy = new ArrayList<Leveldto>();
    boolean firstGenerated = false;
    List<Leveldto> currentHierarchy = new ArrayList<Leveldto>();
    Map<Integer, String> projectionMap = new HashMap<Integer, String>();
    public  List<String> projNameList = new ArrayList<String>();
    public  List<Integer> projIdList = new ArrayList<Integer>();
    ExtPagedTreeTable leftTable;
    ExtPagedTreeTable rightTable;
    ProjectionVarianceLogic pvLogic = new ProjectionVarianceLogic();
    public ExtTreeContainer<ProjectionVarianceDTO> resultBeanContainer = new ExtTreeContainer<ProjectionVarianceDTO>(ProjectionVarianceDTO.class, ExtContainer.DataStructureMode.MAP);
    int tradingPartnerNo = 0;
    /**
     * The graph image.
     */
    private final Resource graphImage = new ThemeResource("../../icons/chart.png");
    private boolean isComparisonLookupOpened;
    CustomTableHeaderDTO rightHeaderPeriod = new CustomTableHeaderDTO();
    public List<Integer> comparisonProjId = new ArrayList<Integer>();
    PVSelectionDTO baseVariables = new PVSelectionDTO();

    private final Map<String, List<ProjectionVarianceDTO>> resultMap = new HashMap();
    private final Map<String, Object> excelParentRecords = new HashMap();
    private final List<String> hierarchyKeys = new ArrayList();
    private final List<String> tradingPartnerKeys = new ArrayList();
    private final List<String> discountKeys = new ArrayList();
    protected PVParameters parameterDto = new PVParameters();

    private final Map<String, List<ProjectionVarianceDTO>> discountMap = new HashMap<String, List<ProjectionVarianceDTO>>();
    private final Map<String, List<List<ProjectionVarianceDTO>>> discountMapDetails = new HashMap<String, List<List<ProjectionVarianceDTO>>>();

    private final PVExcelLogic excelLogic = new PVExcelLogic(resultMap, pvSelectionDTO, hierarchyKeys, tradingPartnerKeys, discountKeys, parameterDto, discountMap, discountMapDetails);

    String toDateValue;

    DataSelectionDTO dataSelectionDTO;
     int columnSize = 0;

    public ProjectionVariance(SessionDTO sessionDTO, final DataSelectionDTO dataSelectionDTO) {
        super(sessionDTO);
        LOGGER.debug("Inside Projection Varaince Constructor");
        this.sessionDTO = sessionDTO;
        this.dataSelectionDTO = dataSelectionDTO;
        configureFields();
    }

    /**
     * Configure fields.
     */
    private void configureFields() {
        configurePermission();
        frequency.addItem(ConstantsUtil.SELECT_ONE);
        frequency.addItem(ConstantsUtil.ANNUALLY);
        frequency.addItem(ConstantsUtil.SEMI_ANNUALLY);
        frequency.addItem(ConstantsUtil.QUARTERLY);
        frequency.addItem(ConstantsUtil.MONTHLY);
        frequency.setValue(ConstantsUtil.QUARTERLY);
        variables.setVisible(false);
        String[] variableValues = ConstantsUtil.PVVariables.names();
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
        
        String[] variableCategoryValues = ConstantsUtil.PVVariableCategory.names();

        variableCategoryCustomMenuItem = variableCategoryCustomMenuBar.addItem("-Select Variables-", null);
        CustomMenuBar.CustomMenuItem[] variableCategoryCustomItem = new CustomMenuBar.CustomMenuItem[variableCategoryValues.length];
        for (int i = 0; i < variableCategoryValues.length; i++) {
            variableCategoryCustomItem[i] = variableCategoryCustomMenuItem.addItem(variableCategoryValues[i].trim(), null);
            variableCategoryCustomItem[i].setCheckable(true);
            variableCategoryCustomItem[i].setItemClickable(true);

            variableCategoryCustomItem[i].setItemClickNotClosable(true);
//            if (i == 0) {
//                variableCategoryCustomItem[i].setCheckAll(true);
//            }
        }

        excelBtn.setIcon(excelExportImage);
        graphBtn.setIcon(graphImage);
        loadProjectionSelection();
        configureTable();
        if ("edit".equals(sessionDTO.getAction()) || "view".equals(sessionDTO.getAction())) {
            setProjectionSelection();
        }
    }

    /**
     * Comparison lookup listener.
     *
     * @param event there event
     */
    @Override
    protected void comparisonLookupLogic() {
        LOGGER.debug("Comparision lookup started");
        if (editFlag) {
            editFlag = false;
            try {
                List list = (List) CommonLogic.executeSelectQuery(queryUtils.getPVComparisonProjections(comparisonProjId), null, null);
                selectedList = pvLogic.getCustomizedPVComparisonList(list);
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
        final ComparisonLookup comparisonLookupWindow = new ComparisonLookup(comparison, sessionDTO.getProjectionId(), selectedList);
        UI.getCurrent().addWindow(comparisonLookupWindow);
        comparisonLookupWindow.addCloseListener(new Window.CloseListener() {
            /**
             * Default method.
             */
            @Override
            public void windowClose(final Window.CloseEvent event) {
                if (comparisonLookupWindow.getSelectedProjection().getItemIds().isEmpty()) {
                    comparison.setReadOnly(false);
                    comparison.setValue(Constants.SELECT_ONE);
                    comparison.setData(null);
                    comparison.setImmediate(true);
                    comparison.setReadOnly(true);
                }
                isComparisonLookupOpened = true;
                loadComparison();
            }
        });

        LOGGER.debug("Comparision lookup ends");
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

    private void loadProjectionSelection() {
        try {
            LOGGER.debug("Before loading DTo");
            pvSelectionDTO.setSessionDTO(sessionDTO);
            pvSelectionDTO.setFrequency(String.valueOf(frequency.getValue()));
            pvSelectionDTO.setLevel(String.valueOf(level.getValue()));
            pvSelectionDTO.setProjectionPeriodOrder(String.valueOf(projectionPeriodOrder.getValue()));
            pvSelectionDTO.setDiscountLevel(String.valueOf(discountLevel.getValue()));
            pvSelectionDTO.setPivotView(String.valueOf(pivotView.getValue()));
            pvSelectionDTO.setFromDate(String.valueOf(fromDate.getValue()));
            pvSelectionDTO.setCurrentProjectionName("Current Projection");
            pvSelectionDTO.setProjIdList(projIdList);
            pvSelectionDTO.setProjectionMap(projectionMap);
            pvSelectionDTO.setVariableCategory(variableCategoryValue);
            pvSelectionDTO.setUserId(Integer.valueOf(sessionDTO.getUserId()));
            pvSelectionDTO.setSessionId(Integer.valueOf(sessionDTO.getSessionId()));
            pvSelectionDTO.setCustRelationshipBuilderSid(sessionDTO.getCustRelationshipBuilderSid());
            pvSelectionDTO.setProdRelationshipBuilderSid(sessionDTO.getProdRelationshipBuilderSid());
            pvSelectionDTO.setForecastDTO(sessionDTO.getForecastDTO());
            pvSelectionDTO.setProjectionId(sessionDTO.getProjectionId());
            pvSelectionDTO.setCurrentProjId(sessionDTO.getProjectionId());
            pvSelectionDTO.setProjectionNum(CommonUtils.getProjectionNumber(String.valueOf(frequency.getValue()), sessionDTO));
            pvSelectionDTO.setProjectionOrder(String.valueOf(projectionPeriodOrder.getValue()));
            pvSelectionDTO.setCustomerLevelNo(StringUtils.isBlank(pvSelectionDTO.getSessionDTO().getCustomerLevelNumber()) || "null".equals(pvSelectionDTO.getSessionDTO().getCustomerLevelNumber())
                    ? 1 : Integer.valueOf(pvSelectionDTO.getSessionDTO().getCustomerLevelNumber()));
            pvSelectionDTO.setProductLevelNo(StringUtils.isBlank(pvSelectionDTO.getSessionDTO().getProductLevelNumber()) || "null".equals(pvSelectionDTO.getSessionDTO().getProductLevelNumber())
                    ? 1 : Integer.valueOf(pvSelectionDTO.getSessionDTO().getProductLevelNumber()));
            pvSelectionDTO.setVariables(variablesValue);
            pvSelectionDTO.setHistoryNum(CommonUtils.getHistoryProjectionNum(String.valueOf(frequency.getValue()), sessionDTO));
            pvSelectionDTO.setCustomId(customId);
            pvSelectionDTO.setForecastDTO(getHistoricalPeriods(dataSelectionDTO));
            pvSelectionDTO.setComparisonBasis(String.valueOf(comparisonBasis.getValue()));
            viewChange(false);
            groupChange(false);
            setCurrentHierarchy(new ArrayList<Leveldto>(viewChangeHierarchy));
            LOGGER.debug("After loading DTo");
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public void setCurrentHierarchy(List<Leveldto> currentHierarchy) {
        this.currentHierarchy = currentHierarchy;
    }

    public void groupChange(boolean groupChange) {
        if (group.getValue() != null && (pvSelectionDTO.isIsCustomHierarchy() || !pvSelectionDTO.getHierarchyIndicator().equals("P"))) {
            pvSelectionDTO.setGroupFilter(String.valueOf(group.getValue()));
        }
        if (groupChange && firstGenerated && !generated && (pvSelectionDTO.isIsCustomHierarchy() || !pvSelectionDTO.getHierarchyIndicator().equals("P"))) {
            tableLogic.groupChange();
        }
    }

    /**
     * Configure table.
     */
    public void configureTable() {
        try {
            fullHeader = new CustomTableHeaderDTO();
            leftHeader = HeaderUtils.getVarianceLeftTableColumns(fullHeader);
            pvSelectionDTO.setForecastDTO(getHistoricalPeriods(dataSelectionDTO));
            List<Object> HeaderPropertyIds = HeaderUtils.getVarianceRightTableColumns(pvSelectionDTO, fullHeader);
            rightHeader = (CustomTableHeaderDTO) HeaderPropertyIds.get(0);
            rightHeaderPeriod = (CustomTableHeaderDTO) HeaderPropertyIds.get(0);
            pvSelectionDTO.setRightHeaderPeriod(rightHeaderPeriod);
            alignRight();
            resultBeanContainer = new ExtTreeContainer<ProjectionVarianceDTO>(ProjectionVarianceDTO.class, ExtContainer.DataStructureMode.MAP);
            resultBeanContainer.setColumnProperties(leftHeader.getProperties());
            resultBeanContainer.setColumnProperties(rightHeader.getProperties());
            tableLogic.setScreenName(screenName);
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
            leftTable.setDoubleHeaderVisible(true);
            leftTable.setDoubleHeaderVisibleColumns(leftHeader.getDoubleColumns().toArray());
            leftTable.setDoubleHeaderColumnHeaders(leftHeader.getDoubleHeaders().toArray(new String[leftHeader.getDoubleHeaders().size()]));
            leftTable.setDoubleHeaderMap(leftHeader.getDoubleHeaderMaps());
            rightTable.setVisibleColumns(rightHeader.getSingleColumns().toArray());
            rightTable.setColumnHeaders(rightHeader.getSingleHeaders().toArray(new String[rightHeader.getSingleHeaders().size()]));
            rightTable.setDoubleHeaderVisible(true);
            rightTable.setDoubleHeaderVisibleColumns(rightHeader.getDoubleColumns().toArray());
            rightTable.setDoubleHeaderColumnHeaders(rightHeader.getDoubleHeaders().toArray(new String[rightHeader.getDoubleHeaders().size()]));

            rightTable.setDoubleHeaderMap(rightHeader.getDoubleHeaderMaps());
            for (Object columns : rightHeader.getDoubleColumns()) {
               
                Map<Object, Object[]> doubleHeaderMap = new HashMap<Object, Object[]>();
                doubleHeaderMap = rightHeader.getDoubleHeaderMaps();
                columnSize = doubleHeaderMap.get(columns).length;
        }
            pvSelectionDTO.setPpa(true);
            pvSelectionDTO.setProjectionId(sessionDTO.getProjectionId());
            
            UiUtils.setExtFilterTreeTableColumnWidth(rightTable, NumericConstants.ONE_SEVEN_ZERO, TAB_PROJECTION_VARIANCE.getConstant());
            toDateValue = String.valueOf(toDate.getValue());
            if (fromDate.getValue() != null && !"null".equals(String.valueOf(fromDate.getValue())) && !Constants.SELECT_ONE.equals(String.valueOf(fromDate.getValue()))
                    && toDate.getValue() != null && !"null".equals(String.valueOf(toDate.getValue())) && !Constants.SELECT_ONE.equals(String.valueOf(toDate.getValue()))) {
                if (pivotView.getValue().equals("Period")) {
                    fullHeader = pvLogic.getDateRangeHeaders(rightTable, rightHeader, fullHeader, fromDate.getValue(), toDate.getValue());
                } else {
                    List<String> periodList = pvSelectionDTO.getPeriodList();
                    List<String> pivotList = new ArrayList<String>();
                    int start = 0;
                    int end = 0;
                    if ("Descending".equalsIgnoreCase(String.valueOf(projectionPeriodOrder.getValue()))) {
                        start = periodList.indexOf(toDate.getValue().toString().replace(" ", StringUtils.EMPTY));
                        end = periodList.indexOf(fromDate.getValue().toString().replace(" ", StringUtils.EMPTY));
                    } else {
                        start = periodList.indexOf(fromDate.getValue().toString().replace(" ", StringUtils.EMPTY));
                        end = periodList.indexOf(toDate.getValue().toString().replace(" ", StringUtils.EMPTY));
                    }
                    for (int i = start; i <= end; i++) {
                        pivotList.add(periodList.get(i));
                    }
                    pvSelectionDTO.setPeriodList(pivotList);
                }
            }
            if ("Variable".equals(pivotView.getValue().toString())) {
                try {
                    if (!discountLevel.getValue().equals("Total Discount")) {
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
                                setExpandIconAction(event.getPropertyId(), !event.isExpanded(), pvSelectionDTO);
                            }
                        });
                    }
                } catch (Exception e) {
                    LOGGER.error(e);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    private void alignRight() {
        for (Object obj : rightHeader.getSingleColumns()) {
            resultsTable.getRightFreezeAsTable().setColumnAlignment(obj, ExtCustomTable.Align.RIGHT);
        }
    }

    public void setExpandIconAction(Object propertyId, boolean collapsed, PVSelectionDTO projSel) {
        ExtFilterTreeTable rightTable = resultsTable
                .getRightFreezeAsTable();
        List<Integer> projList = projSel.getProjIdList();
        List<String> discountNames = discountLevel.getValue().equals("Program") ? pvSelectionDTO.getDiscountNameList() : pvSelectionDTO.getDiscountNameCFF();
        String commonColumn = String.valueOf(propertyId);
        if (!discountNames.isEmpty()) {
            for (int i = 0; i < discountNames.size(); i++) {
                String commonHeader = discountNames.get(i);
                String commonCol = commonColumn + commonHeader.replace(" ", "") + i;
                rightTable.setDoubleHeaderColumnCollapsed(commonCol, collapsed);
                rightTable.setColumnCollapsed(commonCol + "Current" + projSel.getCurrentProjId(), collapsed);
                if (!projList.isEmpty()) {
                    for (int j = 0; j < projList.size(); j++) {
                        rightTable.setColumnCollapsed(commonCol + projList.get(j), collapsed);
                    }
                }
            }
        }
//        String commonCol1 = commonColumn + ConstantsUtils.RETURNS;
//        rightTable.setDoubleHeaderColumnCollapsed(commonCol1, collapsed);
//        rightTable.setColumnCollapsed(commonCol1 + "Current" + projSel.getCurrentProjId(), collapsed);
//        if (!projList.isEmpty()) {
//            for (int j = 0; j < projList.size(); j++) {
//                rightTable.setColumnCollapsed(commonCol1 + projList.get(j), collapsed);
//            }
//        }
//        String commonCol2 = commonColumn + Constants.LabelConstants.PPA.getConstant();
//        rightTable.setDoubleHeaderColumnCollapsed(commonCol2, collapsed);
//        rightTable.setColumnCollapsed(commonCol2 + "Current" + projSel.getCurrentProjId(), collapsed);
//        if (!projList.isEmpty()) {
//            for (int j = 0; j < projList.size(); j++) {
//                rightTable.setColumnCollapsed(commonCol2 + projList.get(j), collapsed);
//            }
//        }
//        String commonCol3 = commonColumn + Constants.LabelConstants.MANDATED.getConstant();
//        rightTable.setDoubleHeaderColumnCollapsed(commonCol3, collapsed);
//        rightTable.setColumnCollapsed(commonCol3 + "Current" + projSel.getCurrentProjId(), collapsed);
//        if (!projList.isEmpty()) {
//            for (int j = 0; j < projList.size(); j++) {
//                rightTable.setColumnCollapsed(commonCol3 + projList.get(j), collapsed);
//            }
//        }
//        String commonCol4 = commonColumn + Constants.LabelConstants.SUPPLEMENTAL.getConstant();
//        rightTable.setDoubleHeaderColumnCollapsed(commonCol4, collapsed);
//        rightTable.setColumnCollapsed(commonCol4 + "Current" + projSel.getCurrentProjId(), collapsed);
//        if (!projList.isEmpty()) {
//            for (int j = 0; j < projList.size(); j++) {
//                rightTable.setColumnCollapsed(commonCol4 + projList.get(j), collapsed);
//            }
//        }
    }

    /**
     * Configure Result Table.
     *
     * @param isExpand
     * @param value
     */
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
    protected void callTableLogic(int levelNo, String hierarchyNo, boolean excelFlag) {
        loadVariables();
        if (excelFlag) {
        } else {
            tableLogic.clearAll();
            loadResultTable(levelNo, hierarchyNo);
        }
    }

    @UiHandler("fromDate")
    public void fromDateDdlbChange(Property.ValueChangeEvent event) {
        if (fromDate.getValue() != null && !"null".equals(String.valueOf(fromDate.getValue())) && !"".equals(String.valueOf(fromDate.getValue()))
                && !Constants.SELECT_ONE.equals(String.valueOf(fromDate.getValue()))) {
            loadToPeriod(fromDate.getValue().toString());
        } else {
            fromDate.setValue(Constants.SELECT_ONE);
            loadToPeriod(Constants.SELECT_ONE);
        }
    }

    @Override
    protected void getGenerateCall(boolean excelFlag) {
        try {
            if (discountLevel.getValue().equals("Program")) {
                List<List<String>> discountList = ProjectionVarianceLogic.loadDiscounts(sessionDTO.getProjectionId());
                pvSelectionDTO.setDiscountList(discountList);
            } else {
                pvSelectionDTO.setDiscountNameCFF(ProjectionVarianceLogic.loadProgramCategory(sessionDTO.getProjectionId()));
            }
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
            getCheckedVariableCategoryValues();
            loadVariables();
            loadProjectionSelection();
            if (excelFlag) {
                configureExcelTable();
            } else {
                tableVerticalLayout.removeAllComponents();
                tableLogic.sinkItemPerPageWithPageLength(false);
                tableLogic = new VarianceTableLogic();
                resultsTable = new FreezePagedTreeTable(tableLogic);
                initializeResultTable();
                configureTable();
                addResultTable();
                loadFromPeriod(String.valueOf(fromDate.getValue()));
                loadToPeriod(String.valueOf(fromDate.getValue()));
                generateLogic();
            }
            generated = false;
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public void loadVariables() {
        loadComparison();
        setBaseVariables(variableCategoryValue , variablesValue);
    }

    public void setBaseVariables(String columns, String varriables) {
        LOGGER.debug("Entering setBaseVariables method");

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
        pvSelectionDTO.setVarExFacCustomer(false);
        pvSelectionDTO.setVarPerExFacCustomer(false);
        pvSelectionDTO.setVarAdjDemand(false);
        pvSelectionDTO.setVarPerAdjDemand(false);
        pvSelectionDTO.setVarIwDetails(false);
        pvSelectionDTO.setVarPerIwDetails(false);
        pvSelectionDTO.setNetSalesExFactory(false);
        pvSelectionDTO.setDiscountPerExFactory(false);

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
            if (value.equalsIgnoreCase(StringUtils.trim(Constants.PVVariables.EX_FACTORY_PRODUCT.toString()))) {
                pvSelectionDTO.setVarExFacSales(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constants.PVVariables.DEMAND_SALES.toString()))) {
                pvSelectionDTO.setVarDemandSales(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constants.PVVariables.INVENTORY_SUMMARY.toString()))) {
                pvSelectionDTO.setVarInvSales(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constants.PVVariables.PER_EX_FACTORY_PRODUCT.toString()))) {
                pvSelectionDTO.setVarPerExFacSales(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constants.PVVariables.PER_DEMAND.toString()))) {
                pvSelectionDTO.setVarPerDemandSales(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constants.PVVariables.PER_INVENORY_WITHDRAW_SUMMARY.toString()))) {
                pvSelectionDTO.setVarPerInvSales(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constants.PVVariables.VAR_CONTRACT_SALES.toString()))) {
                pvSelectionDTO.setVarContractsales(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constants.PVVariables.VAR_CONTRACT_UNITS.toString()))) {
                pvSelectionDTO.setVarContractUnits(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constants.PVVariables.VAR_DIS_AMOUNT.toString()))) {
                pvSelectionDTO.setVarDisAmount(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constants.PVVariables.VAR_DIS_RATE.toString()))) {
                pvSelectionDTO.setVarDisRate(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constants.PVVariables.VAR_RPU.toString()))) {
                pvSelectionDTO.setVarRPU(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constants.PVVariables.VAR_NETSALES.toString()))) {
                pvSelectionDTO.setVarNetSales(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constants.PVVariables.VAR_COGS.toString()))) {
                pvSelectionDTO.setVarCOGC(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constants.PVVariables.VAR_NET_PROFITE.toString()))) {
                pvSelectionDTO.setVarNetProfit(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString()))) {
                pvSelectionDTO.setVarExFacCustomer(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constants.PVVariables.PER_EX_FACTORY_CUSTOMER.toString()))) {
                pvSelectionDTO.setVarPerExFacCustomer(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constants.PVVariables.ADJUSTED_DEMAND.toString()))) {
                pvSelectionDTO.setVarAdjDemand(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constants.PVVariables.PER_ADJUSTED_DEMAND.toString()))) {
                pvSelectionDTO.setVarPerAdjDemand(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constants.PVVariables.INVENTORY_DETAILS.toString()))) {
                pvSelectionDTO.setVarIwDetails(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constants.PVVariables.PER_INVENORY_WITHDRAW_DETAILS.toString()))) {
                pvSelectionDTO.setVarPerIwDetails(true);
            }else if (value.equalsIgnoreCase(StringUtils.trim(Constants.PVVariables.NET_SALES_PER_EX_FACTORY.toString()))) {
                pvSelectionDTO.setNetSalesExFactory(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constants.PVVariables.DISCOUNT_PER_EX_FACTORY.toString()))) {
                pvSelectionDTO.setDiscountPerExFactory(true);
            }
        }

        LOGGER.debug("End of setBaseVariables method");
    }

    private void loadFromPeriod(String fromDateVal) {
        try {
            fromDate.removeAllItems();
            fromDate.addItem(Constants.SELECT_ONE);
            Map<String, String> listMap = new HashMap<String, String>();
            List<String> periodList = new ArrayList<String>(pvSelectionDTO.getPeriodHeaderList());
            if (String.valueOf(projectionPeriodOrder.getValue()).equals("Descending")) {
                Collections.reverse(periodList);
            }
            for (int i = 0; i < periodList.size(); i++) {
                periodList.set(i, String.valueOf(periodList.get(i)).toLowerCase());
            }
            for (Map.Entry<String, String> entry : pvSelectionDTO.getPeriodListMap().entrySet()) {
                listMap.put(entry.getKey().toLowerCase(), entry.getValue());
            }
            if (!periodList.isEmpty()) {
                for (int i = 0; i < periodList.size(); i++) {
                    String header = listMap.get(periodList.get(i));
                    fromDate.addItem(header);
                }
            }
            fromDate.setValue(fromDateVal);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    private void loadToPeriod(String fromDateVal) {
        try {
            Object toDateVal = toDate.getValue();
            toDate.removeAllItems();
            toDate.addItem(Constants.SELECT_ONE);
            int start = 0;
            Map<String, String> listMap = new HashMap<String, String>();
            List<String> periodList = new ArrayList<String>(pvSelectionDTO.getPeriodHeaderList());
            if (String.valueOf(pvSelectionDTO.getProjectionPeriodOrder()).equals("Descending")) {
                Collections.reverse(periodList);
            }
            for (int i = 0; i < periodList.size(); i++) {
                periodList.set(i, String.valueOf(periodList.get(i)).toLowerCase());
            }
            for (Map.Entry<String, String> entry : pvSelectionDTO.getPeriodListMap().entrySet()) {
                listMap.put(entry.getKey().toLowerCase(), entry.getValue());
            }
            if (fromDate.getValue() != null && !"null".equals(String.valueOf(fromDate.getValue())) && !"".equals(String.valueOf(fromDate.getValue()))
                    && !Constants.SELECT_ONE.equals(String.valueOf(fromDate.getValue())) && !fromDateVal.equals(Constants.SELECT_ONE)) {
                String fromVal = fromDateVal.replace(" ", "");
                fromVal = fromVal.toLowerCase();
                start = periodList.indexOf(fromVal);
            }
            int end = periodList.size() - 1;
            for (int i = start; i <= end; i++) {
                toDate.addItem(listMap.get(periodList.get(i)));
            }
            toDate.setValue(toDateVal);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    @Override
    protected void setDisableFields() {

    }

    @Override
    protected void viewChange(boolean viewChange) {
        pvSelectionDTO.setIsCustomHierarchy(false);
        customDdlb.setEnabled(false);
        editViewBtn.setEnabled(false);
        addViewBtn.setEnabled(false);
        if (view.getValue() != null) {
            if (CUSTOM.getConstant().equals(String.valueOf(view.getValue()))) {
                pvSelectionDTO.setHierarchyIndicator("");
                pvSelectionDTO.setIsCustomHierarchy(true);

                if (firstGenerated && !generated) {
                    tableLogic.clearAll();
                    tableLogic.getControlTable().getContainerDataSource().removeAllItems();
                }
                loadCustomDDLB();
                levelFilter.setEnabled(false);
            } else {
                if ("Total".equalsIgnoreCase(String.valueOf(level.getValue()))) {
                    levelFilter.setEnabled(false);
                } else {
                    levelFilter.setEnabled(true);
                    levelDdlb.setEnabled(true);
                }
                level.setEnabled(true);
                customIdToSelect = customId;
                pvSelectionDTO.setIsCustomHierarchy(false);
                pvSelectionDTO.setTpLevel(tradingPartnerNo);
                if (CUSTOMER.getConstant().equals(String.valueOf(view.getValue()))) {
                    pvSelectionDTO.setHierarchyIndicator("C");
                    pvSelectionDTO.setRelationshipBuilderSid(pvSelectionDTO.getCustRelationshipBuilderSid());
                    if (viewChange) {
                        callGenerateLogic();
                    }
                } else if (PRODUCT.getConstant().equals(String.valueOf(view.getValue()))) {
                    pvSelectionDTO.setHierarchyIndicator("P");
                    pvSelectionDTO.setRelationshipBuilderSid(pvSelectionDTO.getProdRelationshipBuilderSid());
                    if (viewChange) {
                        callGenerateLogic();
                    }
                }
            }
        }
    }

    @Override
    protected void customDdlbChangeOption() {
        LOGGER.debug("customDdlbChangeOption ValueChangeEvent initiated ");
        customId = CommonLogic.customDdlbOptionChange(customDdlb, editViewBtn, levelDdlb);
        pvSelectionDTO.setCustomId(customId);
        levelDdlb.setEnabled(customId != 0);
        int tpNo = CommonLogic.getTradingPartnerLevelNo(true, customId);
        pvSelectionDTO.setTpLevel(tpNo);
        viewChangeHierarchy = CommonLogic.getCustomTree(customId);
        sessionDTO.setCustomId(customId);
        CommonLogic.loadCustomHierarchyList(sessionDTO);
        if (customId != 0) {
            callGenerateLogic();
        } else {
            tableLogic.clearAll();
            tableLogic.getControlTable().getContainerDataSource().removeAllItems();
        }
        LOGGER.debug("customDdlbChangeOption ValueChangeEvent ends ");
    }

    @Override
    protected void loadFrequency() {
        LOGGER.debug("ProjectionVariance ValueChangeEvent initiated with frequency -->" + frequency.getValue());
        if (frequency.getValue() != null && !Constants.NULL.equals(String.valueOf(frequency.getValue())) && !StringUtils.EMPTY.equals(String.valueOf(frequency.getValue()))) {
            loadProjectionSelection();
            fullHeader = new CustomTableHeaderDTO();
            HeaderUtils.getVarianceRightTableColumns(pvSelectionDTO, fullHeader);
            loadFromPeriod(Constants.SELECT_ONE);
            loadToPeriod(Constants.SELECT_ONE);
            toDate.setValue(Constants.SELECT_ONE);
        }
        LOGGER.debug("ProjectionVariance ValueChangeEvent ends ");
    }

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
                comparison.setReadOnly(false);
                comparison.setValue(SELECT_ONE);
                comparison.setData(null);
                comparison.setReadOnly(true);
                level.select(Constants.LabelConstants.TOTAL);
                projectionPeriodOrder.select(Constants.CommonConstantsForChannels.ASCENDING);
                fromDate.select(SELECT_ONE);
                toDate.select(SELECT_ONE);
                frequency.select(ConstantsUtil.QUARTERLY);
                discountLevel.select("Total Discount");
                pivotView.select(Constants.PERIOD);
                customMenuBar.removeItems();
                String[] variableValues = ConstantsUtil.PVVariables.names();
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
//                variableCategory.setValue(null);
                getUnCheckedVariableMenuItem(customMenuItem);
            }
        }.getConfirmationMessage("Confirmation",
                "Are you sure you want to reset the values in the Variance Selection section to the previous values?");
    }

   /**
     * excelBtnLogic
     */
    @Override
    protected void excelBtnLogic() {
        try {
            ConsolidatedFinancialForecastUI.EXCEL_CLOSE = true;
            excelTable.setRefresh(Boolean.FALSE);
            levelFilterDdlbChangeOption(true);
            excelForCFFProjectionVariance();
            excelTable.setRefresh(Boolean.TRUE);
            int leftcolumnsize = NumericConstants.ONE;
            int ColSize = 252;
            int maxColSize = ColSize % columnSize == 0 ? 252 : 250;
            Object[] leftColumns = new Object[leftcolumnsize + maxColSize];
            String[] leftHeaders = new String[leftcolumnsize + maxColSize];
            System.arraycopy(fullHeader.getSingleColumns().toArray(), 0, leftColumns, 0, NumericConstants.ONE);
            System.arraycopy(fullHeader.getSingleHeaders().toArray(), 0, leftHeaders, 0, NumericConstants.ONE);
            List<Object> fullColumns = fullHeader.getSingleColumns();
            if (fullColumns.size() >= 255) {
                ExcelExport export = null;
                int sheetCount = 0;
                for (int i = leftcolumnsize; i < fullColumns.toArray().length; i += (maxColSize)) {
                    int remainingColumnSize = fullColumns.toArray().length - (sheetCount * maxColSize + 1);
                     
                    int currentColumnSize = remainingColumnSize > maxColSize + 1 ? maxColSize : remainingColumnSize;
                      if( remainingColumnSize < maxColSize + 1 ){
                         currentColumnSize= remainingColumnSize;
                          leftColumns = new Object[ leftcolumnsize + remainingColumnSize];
                          leftHeaders = new String[ leftcolumnsize + remainingColumnSize];
                        System.arraycopy(fullHeader.getSingleColumns().toArray(), 0, leftColumns, 0, NumericConstants.ONE);
                        System.arraycopy(fullHeader.getSingleHeaders().toArray(), 0, leftHeaders, 0, NumericConstants.ONE);
                     }
                    System.arraycopy(fullColumns.toArray(), i, leftColumns, leftcolumnsize, currentColumnSize);
                    System.arraycopy(fullHeader.getSingleHeaders().toArray(), i, leftHeaders, leftcolumnsize, currentColumnSize);
                    excelTable.setVisibleColumns(leftColumns);
                    excelTable.setColumnHeaders(leftHeaders);
                    excelTable.setRefresh(true);
                    String sheetName = "Projection Variance " + sheetCount;
                    if (i == leftcolumnsize) {
                        export = new ExcelExport(new ExtCustomTableHolder(excelTable), "Projection Variance", "Projection Variance", "ProjectionVariance.xls", false);
                    } else {
                        export.setNextTableHolder(new ExtCustomTableHolder(excelTable), sheetName);
                    }
                    sheetCount++;
                    export.export();
        }
        }else{
                ExcelExport export = new ExcelExport(new ExtCustomTableHolder(excelTable), "Projection Variance", "Projection Variance", "ProjectionVariance.xls", false);
                export.export();
        }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

    }

    @Override
    protected void graphBtnLogic() {
        List chartiLst = new ArrayList();
        for (Object obj : resultsTable.getRightFreezeAsTable().getContainerDataSource().getItemIds()) {
            ProjectionVarianceDTO dto = (ProjectionVarianceDTO) obj;
            if (dto.getLevelNo() != null && dto.getParent() == 0) {
                chartiLst.add(dto);
            }
        }
        final PVChart chart = new PVChart(chartiLst, String.valueOf(frequency.getValue()), "", fullHeader, pvSelectionDTO);
        final PVGraphWindow salesGraphWindow = new PVGraphWindow(chart.getChart(), "Projection Variance");
        UI.getCurrent().addWindow(salesGraphWindow);
        salesGraphWindow.focus();
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
            java.util.logging.Logger.getLogger(ProjectionVariance.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            java.util.logging.Logger.getLogger(ProjectionVariance.class.getName()).log(Level.SEVERE, null, ex);
        }
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ForecastConfig.class);
        dynamicQuery.add(RestrictionsFactoryUtil.eq("businessProcessType", businessProcessType));
        dynamicQuery.addOrder(OrderFactoryUtil.desc("versionNo"));
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

    public void callGenerateLogic() {
        if (firstGenerated && !generated) {
            generateLogic();
        }
    }

    public void setProjectionSelection() {
        Map<Object, Object> map = CommonLogic.getNMProjectionSelection(sessionDTO.getProjectionId(), "Projection Variance");
        if (map != null && !map.isEmpty()) {

            Object value = map.get("Frequency");
            if (value != null) {
                frequency.setValue(String.valueOf(value));
            }

            value = map.get("Comparison");
            if (value != null && StringUtils.isNotBlank(value.toString()) && !Constants.NULL.equals(value.toString())) {
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
            if (value != null && StringUtils.isNotBlank(String.valueOf(value))) {
                fromDate.setValue(String.valueOf(value));
            }
            value = map.get("To");
            if (value != null && StringUtils.isNotBlank(String.valueOf(value))) {
                toDate.setValue(String.valueOf(value));
            }
            value = map.get("Comparison Basis");
            if (value != null && StringUtils.isBlank(String.valueOf(value))) {
                comparisonBasis.setValue(String.valueOf(value));
            }
            value = map.get("Variable Category");
            if (value != null && variableCategoryCustomMenuItem != null && value.toString().length() > 0) {
                String val = value.toString();
                val = val.substring(1, val.length() - 1);
                final String[] col = val.split(",");
                for (int i = 0; i < col.length; i++) {
                    if (i == 0) {
                        for (CustomMenuBar.CustomMenuItem string : variableCategoryCustomMenuItem.getChildren()) {
                            if (string.getText().equals(String.valueOf(col[i]).trim())) {
                                string.setChecked(true);
                            }
                        }
                    } else {
                        for (CustomMenuBar.CustomMenuItem string : variableCategoryCustomMenuItem.getChildren()) {
                            if (string.getText().equals(String.valueOf(col[i]).substring(1, col[i].length()).trim())) {
                                string.setChecked(true);
                            }
                        }
                    }
                }
            }
            value = map.get("Variables");
            if (value != null) {
                String val = value.toString();
                val = val.substring(1, val.length() - 1);
                final String[] col = val.split(",");
                for (int i = 0; i < col.length; i++) {
                    if (i == 0) {
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

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        pvSelectionDTO.setUserId(Integer.valueOf(sessionDTO.getUserId()));
        pvSelectionDTO.setSessionId(Integer.valueOf(sessionDTO.getSessionId()));
        tradingPartnerNo = CommonLogic.getTradingPartnerLevelNo(false, sessionDTO.getProjectionId());
    }

    public void generateLogic() {
        sessionDTO.setProjectionId(sessionDTO.getProjectionId());
        levelFilter.removeValueChangeListener(levelFilterChangeOption);
        loadLevelAndFilterValue();
        pvSelectionDTO.setIslevelFiler(false);
        loadResultTable(0, "");
        levelFilter.addValueChangeListener(levelFilterChangeOption);
    }

    @SuppressWarnings("serial")
    private void loadResultTable(int levelNo, String hierarchyNo) {
        pvSelectionDTO.setFilterLevelNo(levelNo);
        pvSelectionDTO.setFilterHierarchyNo(hierarchyNo);
        pvSelectionDTO.setProductHierarchyNo("");
        pvSelectionDTO.setCustomerHierarchyNo("");
        tableLogic.setProjectionResultsData(pvSelectionDTO);
        pvSelectionDTO.setPpa(true);
        pvSelectionDTO.setProjectionId(sessionDTO.getProjectionId());
        resultsTable.getLeftFreezeAsTable().setFilterGenerator(new FilterGenerator(pvSelectionDTO, Constants.LabelConstants.TOTAL.equals(level.getValue().toString())));
    }

    protected void loadLevelAndFilterValue() {
        LOGGER.debug("loadLevelAndFilterValue initiated ");
        levelDdlb.removeAllItems();
        levelDdlb.addItem(SELECT_ONE);
        levelDdlb.setNullSelectionItemId(SELECT_ONE);
        levelDdlb.setValue(SELECT_ONE);
        levelFilter.removeAllItems();
        levelFilter.addItem(SELECT_ONE);
        levelFilter.setNullSelectionItemId(SELECT_ONE);
        levelFilter.setValue(SELECT_ONE);
        List<Leveldto> hierarchy = null;
        if (pvSelectionDTO.isIsCustomHierarchy()) {
            hierarchy = CommonLogic.getCustomTree(customId);
        } else if ("C".equals(pvSelectionDTO.getHierarchyIndicator())) {
            hierarchy = CommonLogic.getCustomerHierarchyMandated(sessionDTO.getProjectionId(), pvSelectionDTO.getCustomerLevelNo());
        } else if ("P".equals(pvSelectionDTO.getHierarchyIndicator())) {
            hierarchy = CommonLogic.getProductHierarchyMandated(sessionDTO.getProjectionId(), pvSelectionDTO.getProductLevelNo());
        }
        if (hierarchy != null) {
            for (Leveldto levelDto : hierarchy) {
                String levelFiterSid = levelDto.getTreeLevelNo() + "~" + levelDto.getHierarchyIndicator();
                String caption = "Level " + levelDto.getTreeLevelNo() + " - " + levelDto.getLevel();
                Object itemId = levelFiterSid;
                levelDdlb.addItem(itemId);
                levelDdlb.setItemCaption(itemId, caption);
                levelFilter.addItem(itemId);
                levelFilter.setItemCaption(itemId, caption);
            }
        }
        LOGGER.debug("loadLevelAndFilterValue ends ");
    }

    public void savePvSelections(SessionDTO sessionDTO) {
        LOGGER.debug("savePVSelections method starts");
        Map map = new HashMap();
        try {
            String priorProjectionIds = projIdList == null || projIdList.isEmpty() ? StringUtils.EMPTY : projIdList.toString();
            map.put("Comparison", priorProjectionIds);
            map.put("Level", level.getValue().toString());
            map.put("Projection Period Order", projectionPeriodOrder.getValue().toString());
            map.put("From", fromDate.getValue() != null ? fromDate.getValue().toString() : "");
            map.put("To", toDate.getValue() != null ? toDate.getValue().toString() : "");
            map.put("Frequency", frequency.getValue() != null ? frequency.getValue().toString() : "");
            map.put("Discount Level", discountLevel.getValue().toString());
            map.put("Pivot View", pivotView.getValue().toString());
            map.put("Variable Category", getCheckedVariableCategoryValues());
            map.put("Variables", getCheckedValues());
            map.put("Comparison Basis", comparisonBasis.getValue() != null ? comparisonBasis.getValue().toString() : StringUtils.EMPTY);
            logic.saveNMPVSelection(map, sessionDTO.getProjectionId(), "Projection Variance");
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.debug("savePVSelections method ends");

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
            comparison.setValue(projNameList.size() > 1 ? "Multiple" : projNameList.get(0));
            pvSelectionDTO.setProjIdList(projIdList);
            pvSelectionDTO.setProjectionMap(projectionMap);
        }
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
        pvSelectionDTO.setFilterHierarchyNo("");
        pvSelectionDTO.setProductHierarchyNo("");
        pvSelectionDTO.setCustomerHierarchyNo("");
        int count = logic.getConfiguredProjectionVarianceCount(id, pvSelectionDTO, true);
        List<ProjectionVarianceDTO> resultList = logic.getConfiguredProjectionVariance(id, pvSelectionDTO, 0, count);
        loadDataToContainer(resultList, id);
    }

    private ForecastDTO getHistoricalPeriods(DataSelectionDTO dataSelectionDTO) {

        Date dbDateTO = new Date();
        SimpleDateFormat format = new SimpleDateFormat(Constants.CommonConstants.DATE_FORMAT.getConstant());
        if (dataSelectionDTO == null) {
            dataSelectionDTO = new DataSelectionDTO();
        }

        String sql = "SELECT\n"
                + "    top 1 FROM_DATE,TO_DATE\n"
                + "     FROM\n"
                + "    dbo.FORECAST_CONFIG FG JOIN HELPER_TABLE HT\n"
                + "        ON HT.HELPER_TABLE_SID = FG.BUSINESS_PROCESS_TYPE\n"
                + "    AND HT.list_name = 'BUSINESS_PROCESS_TYPE'\n"
                + "    AND DESCRIPTION LIKE 'Consolidated Financial Forecast'\n"
                + "    ORDER BY FG.VERSION_NO DESC";

        List list = new ArrayList();
        list = HelperTableLocalServiceUtil.executeSelectQuery(sql);
        for (int i = 0; i < list.size(); i++) {
            final Object[] obj = (Object[]) list.get(i);
            Date dbDateFrom = (Date) obj[0];
            dbDateTO = (Date) obj[1];

            Calendar startDbDate = Calendar.getInstance();
            startDbDate.set(Calendar.YEAR, dbDateFrom.getYear());
            startDbDate.set(Calendar.MONTH, dbDateFrom.getMonth());

            Calendar endDbDate = Calendar.getInstance();
            endDbDate.set(Calendar.YEAR, dbDateTO.getYear());
            endDbDate.set(Calendar.MONTH, dbDateTO.getMonth() - 1);

            try {
                dbDateFrom = format.parse(format.format(dbDateFrom));
                dbDateTO = format.parse(format.format(dbDateTO));
            } catch (ParseException pe) {
                LOGGER.error(pe);
            }
            dataSelectionDTO.setFromDate(dbDateFrom);
            dataSelectionDTO.setToDate(dbDateTO);
        }
        ForecastDTO dto = new ForecastDTO();
        try {
            dto = DataSelectionUtil.getForecastDTO(dataSelectionDTO, sessionDTO);
        } catch (Exception exp) {
            LOGGER.error(exp);
        }
        LOGGER.debug("getHistoricalPeriods method Ends");
        return dto;
    }

    public void excelForCFFProjectionVariance() {
        LOGGER.debug("==inside excelForCFFProjectionVariance================");
        try {
            if (pvSelectionDTO.isIsCustomHierarchy()) {
                pvSelectionDTO.setHierarchyIndicator("");
            }
            if (levelFilter.getValue() != null) {
                pvSelectionDTO.setExcelFilterLevelNo(pvSelectionDTO.getLevelNo());
            } else {
                pvSelectionDTO.setExcelFilterLevelNo(0);
            }
            getDate();
            configureExcelTable();
            excelLogic.getPVData();
            excelParentRecords.clear();

            List<ProjectionVarianceDTO> discDollar = new ArrayList<ProjectionVarianceDTO>();
            List<ProjectionVarianceDTO> varibaleList = resultMap.get(Constants.LabelConstants.TOTAL.toString());
            if (varibaleList != null) {
                for (Iterator<ProjectionVarianceDTO> it1 = varibaleList.listIterator(); it1.hasNext();) {
                    ProjectionVarianceDTO itemId = it1.next();
                    resultExcelContainer.addBean(itemId);
                    if (!"Total Discount".equals(pvSelectionDTO.getDiscountLevel())) {
                        if (itemId.getGroup().startsWith("Discount") || itemId.getGroup().startsWith("RPU")) {
                            resultExcelContainer.setChildrenAllowed(itemId, true);

                            if (itemId.getGroup().startsWith("Discount $ Value")) {
                                discDollar = discountMap.get("discountDollar");
                                if (discDollar != null && discDollar.size() > 0) {
                                    for (Iterator<ProjectionVarianceDTO> itr2 = discDollar.listIterator(); itr2.hasNext();) {
                                        ProjectionVarianceDTO discItemId = itr2.next();
                                        resultExcelContainer.addBean(discItemId);
                                        resultExcelContainer.setChildrenAllowed(discItemId, false);
                                        resultExcelContainer.setParent(discItemId, itemId);
                                    }
                                }
                            }
                            if (itemId.getGroup().startsWith("Discount $ Variance")) {
                                discDollar = discountMap.get("discountDollarVariance");
                                if (discDollar != null && discDollar.size() > 0) {
                                    for (Iterator<ProjectionVarianceDTO> itr2 = discDollar.listIterator(); itr2.hasNext();) {
                                        ProjectionVarianceDTO discItemId = itr2.next();
                                        resultExcelContainer.addBean(discItemId);
                                        resultExcelContainer.setChildrenAllowed(discItemId, false);
                                        resultExcelContainer.setParent(discItemId, itemId);
                                    }
                                }
                            }

                            if (itemId.getGroup().startsWith("Discount $ %Change")) {
                                discDollar = discountMap.get("discountDollarPercent");
                                if (discDollar != null && discDollar.size() > 0) {
                                    for (Iterator<ProjectionVarianceDTO> itr2 = discDollar.listIterator(); itr2.hasNext();) {
                                        ProjectionVarianceDTO discItemId = itr2.next();
                                        resultExcelContainer.addBean(discItemId);
                                        resultExcelContainer.setChildrenAllowed(discItemId, false);
                                        resultExcelContainer.setParent(discItemId, itemId);
                                    }
                                }
                            }
                            if (itemId.getGroup().startsWith("Discount % Value")) {
                                List<ProjectionVarianceDTO> discDollarPer = discountMap.get("discountPervalue");
                                if (discDollarPer != null && discDollarPer.size() > 0) {
                                    for (Iterator<ProjectionVarianceDTO> itr2 = discDollarPer.listIterator(); itr2.hasNext();) {
                                        ProjectionVarianceDTO discItemId = itr2.next();
                                        resultExcelContainer.addBean(discItemId);
                                        resultExcelContainer.setChildrenAllowed(discItemId, false);
                                        resultExcelContainer.setParent(discItemId, itemId);
                                    }
                                }
                            }

                            if (itemId.getGroup().startsWith("Discount % Variance")) {
                                List<ProjectionVarianceDTO> discDollarPer = discountMap.get("discountPervariance");
                                if (discDollarPer != null && discDollarPer.size() > 0) {
                                    for (Iterator<ProjectionVarianceDTO> itr2 = discDollarPer.listIterator(); itr2.hasNext();) {
                                        ProjectionVarianceDTO discItemId = itr2.next();
                                        resultExcelContainer.addBean(discItemId);
                                        resultExcelContainer.setChildrenAllowed(discItemId, false);
                                        resultExcelContainer.setParent(discItemId, itemId);
                                    }
                                }
                            }

                            if (itemId.getGroup().startsWith("Discount % %Change")) {
                                List<ProjectionVarianceDTO> discDollarPer = discountMap.get("discountPerPercent");
                                if (discDollarPer != null && discDollarPer.size() > 0) {
                                    for (Iterator<ProjectionVarianceDTO> itr2 = discDollarPer.listIterator(); itr2.hasNext();) {
                                        ProjectionVarianceDTO discItemId = itr2.next();
                                        resultExcelContainer.addBean(discItemId);
                                        resultExcelContainer.setChildrenAllowed(discItemId, false);
                                        resultExcelContainer.setParent(discItemId, itemId);
                                    }
                                }
                            }

                            if (itemId.getGroup().startsWith("RPU Value")) {
                                List<ProjectionVarianceDTO> rpuList = discountMap.get("rpuValue");
                                if (rpuList != null && rpuList.size() > 0) {
                                    for (Iterator<ProjectionVarianceDTO> itr2 = rpuList.listIterator(); itr2.hasNext();) {
                                        ProjectionVarianceDTO discItemId = itr2.next();
                                        resultExcelContainer.addBean(discItemId);
                                        resultExcelContainer.setChildrenAllowed(discItemId, false);
                                        resultExcelContainer.setParent(discItemId, itemId);
                                    }
                                }
                            }

                            if (itemId.getGroup().startsWith("RPU Variance")) {
                                List<ProjectionVarianceDTO> rpuList = discountMap.get("rpuVariance");
                                if (rpuList != null && rpuList.size() > 0) {
                                    for (Iterator<ProjectionVarianceDTO> itr2 = rpuList.listIterator(); itr2.hasNext();) {
                                        ProjectionVarianceDTO discItemId = itr2.next();
                                        resultExcelContainer.addBean(discItemId);
                                        resultExcelContainer.setChildrenAllowed(discItemId, false);
                                        resultExcelContainer.setParent(discItemId, itemId);
                                    }
                                }
                            }

                            if (itemId.getGroup().startsWith("RPU %Change")) {
                                List<ProjectionVarianceDTO> rpuList = discountMap.get("rpuPercent");
                                if (rpuList != null && rpuList.size() > 0) {
                                    for (Iterator<ProjectionVarianceDTO> itr2 = rpuList.listIterator(); itr2.hasNext();) {
                                        ProjectionVarianceDTO discItemId = itr2.next();
                                        resultExcelContainer.addBean(discItemId);
                                        resultExcelContainer.setChildrenAllowed(discItemId, false);
                                        resultExcelContainer.setParent(discItemId, itemId);
                                    }
                                }
                            }
                            
                            
                             if (itemId.getGroup().startsWith("Discount % of Ex-Factory Value")) {
                                 System.out.println("itemId.getGroup().startsWith(\"Discount % Of Ex-Factory Value\")");
                                List<ProjectionVarianceDTO> rpuList = discountMap.get("discountPerExfacvalue");
                                if (rpuList != null && rpuList.size() > 0) {
                                    System.out.println("rpuList != null && rpuList.size() > 0");
                                    for (Iterator<ProjectionVarianceDTO> itr2 = rpuList.listIterator(); itr2.hasNext();) {
                                        ProjectionVarianceDTO discItemId = itr2.next();
                                        resultExcelContainer.addBean(discItemId);
                                        resultExcelContainer.setChildrenAllowed(discItemId, false);
                                        resultExcelContainer.setParent(discItemId, itemId);
                                    }
                                }
                            }

                            if (itemId.getGroup().startsWith("Discount % of Ex-Factory Variance")) {
                                List<ProjectionVarianceDTO> rpuList = discountMap.get("discountPerExfacvariance");
                                if (rpuList != null && rpuList.size() > 0) {
                                    for (Iterator<ProjectionVarianceDTO> itr2 = rpuList.listIterator(); itr2.hasNext();) {
                                        ProjectionVarianceDTO discItemId = itr2.next();
                                        resultExcelContainer.addBean(discItemId);
                                        resultExcelContainer.setChildrenAllowed(discItemId, false);
                                        resultExcelContainer.setParent(discItemId, itemId);
                                    }
                                }
                            }

                            if (itemId.getGroup().startsWith("Discount % of Ex-Factory %Change")) {
                                List<ProjectionVarianceDTO> rpuList = discountMap.get("discountPerExfacPercent");
                                if (rpuList != null && rpuList.size() > 0) {
                                    for (Iterator<ProjectionVarianceDTO> itr2 = rpuList.listIterator(); itr2.hasNext();) {
                                        ProjectionVarianceDTO discItemId = itr2.next();
                                        resultExcelContainer.addBean(discItemId);
                                        resultExcelContainer.setChildrenAllowed(discItemId, false);
                                        resultExcelContainer.setParent(discItemId, itemId);
                                    }
                                }
                            }
//                                 
                        } else {
                            resultExcelContainer.setChildrenAllowed(itemId, false);
                        }
                    } else {
                        resultExcelContainer.setChildrenAllowed(itemId, false);
                    }
                }
            }
            hierarchyKeys.remove("Total");
            if (!pvSelectionDTO.isIsCustomHierarchy()) {
                Collections.sort(hierarchyKeys);
            }
            for (Iterator<String> it = hierarchyKeys.listIterator(); it.hasNext();) {
                String newKey = it.next();
                it.remove();
//                String key;
//                if (newKey.contains("$")) {
//                    key = (newKey.split("\\$"))[0];
//                } else {
//                    key = newKey;
//                }
                List<ProjectionVarianceDTO> varibaleListDetails = resultMap.get(newKey);
                resultMap.remove(newKey);
                Object parentItemId = null;
                int index = 0;
                for (Iterator<ProjectionVarianceDTO> it1 = varibaleListDetails.listIterator(); it1.hasNext();) {
                    ProjectionVarianceDTO itemId = it1.next();
                    it1.remove();
                    resultExcelContainer.addBean(itemId);
                    if (index == 0) {
                        String parentKey = StringUtils.EMPTY;
                        if (!pvSelectionDTO.isIsCustomHierarchy()) {
                            parentKey = newKey.substring(0, newKey.lastIndexOf('.'));
                        } else {
                            parentKey = getParentKeyforCustom(itemId, newKey, parentKey);
                        }
                        if (parentKey.lastIndexOf('.') >= 0) {
                            parentKey = parentKey.substring(0, parentKey.lastIndexOf('.') + 1);
                        }
                        parentItemId = excelParentRecords.get(parentKey);
                        if (parentItemId != null) {
                            resultExcelContainer.setParent(itemId, parentItemId);
                        }
                        parentItemId = itemId;
                        excelParentRecords.put(newKey, itemId);
                        resultExcelContainer.setChildrenAllowed(itemId, true);
                        index = 1;
                    } else {
                        resultExcelContainer.setParent(itemId, parentItemId);
                        if (!pvSelectionDTO.getDiscountLevel().equals("Total Discount")) {
                            if (itemId.getGroup().startsWith("Discount") || itemId.getGroup().startsWith("RPU")) {
                                resultExcelContainer.setChildrenAllowed(itemId, true);
                                 if(pvSelectionDTO.isIsCustomHierarchy()){
//                                     key = key.substring(key.lastIndexOf('-') + 1);
                                 }
                                if (itemId.getGroup().startsWith("Discount $ Value")) {
                                    List<List<ProjectionVarianceDTO>> discDollarList = discountMapDetails.get(newKey);
                                    if (discDollarList != null) {
                                        List<ProjectionVarianceDTO> totalList = discDollarList.get(0);
                                        if (totalList != null && totalList.size() > 0) {
                                            for (Iterator<ProjectionVarianceDTO> itr2 = totalList.listIterator(); itr2.hasNext();) {
                                                ProjectionVarianceDTO discItemId = itr2.next();
                                                itr2.remove();
                                                resultExcelContainer.addBean(discItemId);
                                                resultExcelContainer.setChildrenAllowed(discItemId, false);
                                                resultExcelContainer.setParent(discItemId, itemId);
                                            }
                                        }
                                    }
                                }

                                if (itemId.getGroup().startsWith("Discount $ Variance")) {
                                    List<List<ProjectionVarianceDTO>> discDollarList = discountMapDetails.get(newKey);
                                    if (discDollarList != null) {
                                        List<ProjectionVarianceDTO> totalList = discDollarList.get(1);
                                        if (totalList != null && totalList.size() > 0) {
                                            for (Iterator<ProjectionVarianceDTO> itr2 = totalList.listIterator(); itr2.hasNext();) {
                                                ProjectionVarianceDTO discItemId = itr2.next();
                                                itr2.remove();
                                                resultExcelContainer.addBean(discItemId);
                                                resultExcelContainer.setChildrenAllowed(discItemId, false);
                                                resultExcelContainer.setParent(discItemId, itemId);
                                            }
                                        }
                                    }
                                }

                                if (itemId.getGroup().startsWith("Discount $ %Change")) {
                                    List<List<ProjectionVarianceDTO>> discDollarList = discountMapDetails.get(newKey);
                                    if (discDollarList != null) {
                                        List<ProjectionVarianceDTO> totalList = discDollarList.get(NumericConstants.TWO);
                                        if (totalList != null && totalList.size() > 0) {
                                            for (Iterator<ProjectionVarianceDTO> itr2 = totalList.listIterator(); itr2.hasNext();) {
                                                ProjectionVarianceDTO discItemId = itr2.next();
                                                itr2.remove();
                                                resultExcelContainer.addBean(discItemId);
                                                resultExcelContainer.setChildrenAllowed(discItemId, false);
                                                resultExcelContainer.setParent(discItemId, itemId);
                                            }
                                        }
                                    }
                                }

                                if (itemId.getGroup().startsWith("Discount % Value")) {
                                    List<List<ProjectionVarianceDTO>> discDollarList = discountMapDetails.get(newKey);
                                    if (discDollarList != null) {
                                        List<ProjectionVarianceDTO> totalList = discDollarList.get(NumericConstants.THREE);
                                        if (totalList != null && totalList.size() > 0) {
                                            for (Iterator<ProjectionVarianceDTO> itr2 = totalList.listIterator(); itr2.hasNext();) {
                                                ProjectionVarianceDTO discItemId = itr2.next();
                                                itr2.remove();
                                                resultExcelContainer.addBean(discItemId);
                                                resultExcelContainer.setChildrenAllowed(discItemId, false);
                                                resultExcelContainer.setParent(discItemId, itemId);
                                            }
                                        }
                                    }
                                }

                                if (itemId.getGroup().startsWith("Discount % Variance")) {
                                    List<List<ProjectionVarianceDTO>> discDollarList = discountMapDetails.get(newKey);
                                    if (discDollarList != null) {
                                        List<ProjectionVarianceDTO> totalList = discDollarList.get(NumericConstants.FOUR);
                                        if (totalList != null && totalList.size() > 0) {
                                            for (Iterator<ProjectionVarianceDTO> itr2 = totalList.listIterator(); itr2.hasNext();) {
                                                ProjectionVarianceDTO discItemId = itr2.next();
                                                itr2.remove();
                                                resultExcelContainer.addBean(discItemId);
                                                resultExcelContainer.setChildrenAllowed(discItemId, false);
                                                resultExcelContainer.setParent(discItemId, itemId);
                                            }
                                        }
                                    }
                                }

                                if (itemId.getGroup().startsWith("Discount % %Change")) {
                                    List<List<ProjectionVarianceDTO>> discDollarList = discountMapDetails.get(newKey);
                                    if (discDollarList != null) {
                                        List<ProjectionVarianceDTO> totalList = discDollarList.get(NumericConstants.FIVE);
                                        if (totalList != null && totalList.size() > 0) {
                                            for (Iterator<ProjectionVarianceDTO> itr2 = totalList.listIterator(); itr2.hasNext();) {
                                                ProjectionVarianceDTO discItemId = itr2.next();
                                                itr2.remove();
                                                resultExcelContainer.addBean(discItemId);
                                                resultExcelContainer.setChildrenAllowed(discItemId, false);
                                                resultExcelContainer.setParent(discItemId, itemId);
                                            }
                                        }
                                    }
                                }

                                if (itemId.getGroup().startsWith("RPU Value")) {
                                    List<List<ProjectionVarianceDTO>> discDollarList = discountMapDetails.get(newKey);
                                    if (discDollarList != null) {
                                        List<ProjectionVarianceDTO> totalList = discDollarList.get(NumericConstants.SIX);
                                        if (totalList != null && totalList.size() > 0) {
                                            for (Iterator<ProjectionVarianceDTO> itr2 = totalList.listIterator(); itr2.hasNext();) {
                                                ProjectionVarianceDTO discItemId = itr2.next();
                                                itr2.remove();
                                                resultExcelContainer.addBean(discItemId);
                                                resultExcelContainer.setChildrenAllowed(discItemId, false);
                                                resultExcelContainer.setParent(discItemId, itemId);
                                            }
                                        }
                                    }
                                }

                                if (itemId.getGroup().startsWith("RPU Variance")) {
                                    List<List<ProjectionVarianceDTO>> discDollarList = discountMapDetails.get(newKey);
                                    if (discDollarList != null) {
                                        List<ProjectionVarianceDTO> totalList = discDollarList.get(NumericConstants.SEVEN);
                                        if (totalList != null && totalList.size() > 0) {
                                            for (Iterator<ProjectionVarianceDTO> itr2 = totalList.listIterator(); itr2.hasNext();) {
                                                ProjectionVarianceDTO discItemId = itr2.next();
                                                itr2.remove();
                                                resultExcelContainer.addBean(discItemId);
                                                resultExcelContainer.setChildrenAllowed(discItemId, false);
                                                resultExcelContainer.setParent(discItemId, itemId);
                                            }
                                        }
                                    }
                                }
                                if (itemId.getGroup().startsWith("RPU %Change")) {
                                    List<List<ProjectionVarianceDTO>> discDollarList = discountMapDetails.get(newKey);
                                    if (discDollarList != null) {
                                        List<ProjectionVarianceDTO> totalList = discDollarList.get(NumericConstants.EIGHT);
                                        if (totalList != null && totalList.size() > 0) {
                                            for (Iterator<ProjectionVarianceDTO> itr2 = totalList.listIterator(); itr2.hasNext();) {
                                                ProjectionVarianceDTO discItemId = itr2.next();
                                                itr2.remove();
                                                resultExcelContainer.addBean(discItemId);
                                                resultExcelContainer.setChildrenAllowed(discItemId, false);
                                                resultExcelContainer.setParent(discItemId, itemId);
                                            }
                                        }
                                    }
                                }
                                
                                if (itemId.getGroup().startsWith("Discount % of Ex-Factory Value")) {
                                    List<List<ProjectionVarianceDTO>> discDollarList = discountMapDetails.get(newKey);
                                    if (discDollarList != null) {
                                        List<ProjectionVarianceDTO> totalList = discDollarList.get(NumericConstants.NINE);
                                        if (totalList != null && totalList.size() > 0) {
                                            for (Iterator<ProjectionVarianceDTO> itr2 = totalList.listIterator(); itr2.hasNext();) {
                                                ProjectionVarianceDTO discItemId = itr2.next();
                                                itr2.remove();
                                                resultExcelContainer.addBean(discItemId);
                                                resultExcelContainer.setChildrenAllowed(discItemId, false);
                                                resultExcelContainer.setParent(discItemId, itemId);
                                            }
                                        }
                                    }
                                }

                                if (itemId.getGroup().startsWith("Discount % of Ex-Factory Variance")) {
                                    List<List<ProjectionVarianceDTO>> discDollarList = discountMapDetails.get(newKey);
                                    if (discDollarList != null) {
                                        List<ProjectionVarianceDTO> totalList = discDollarList.get(NumericConstants.TEN);
                                        if (totalList != null && totalList.size() > 0) {
                                            for (Iterator<ProjectionVarianceDTO> itr2 = totalList.listIterator(); itr2.hasNext();) {
                                                ProjectionVarianceDTO discItemId = itr2.next();
                                                itr2.remove();
                                                resultExcelContainer.addBean(discItemId);
                                                resultExcelContainer.setChildrenAllowed(discItemId, false);
                                                resultExcelContainer.setParent(discItemId, itemId);
                                            }
                                        }
                                    }
                                }

                                if (itemId.getGroup().startsWith("Discount % of Ex-Factory %Change")) {
                                    List<List<ProjectionVarianceDTO>> discDollarList = discountMapDetails.get(newKey);
                                    if (discDollarList != null) {
                                        List<ProjectionVarianceDTO> totalList = discDollarList.get(NumericConstants.ELEVEN);
                                        if (totalList != null && totalList.size() > 0) {
                                            for (Iterator<ProjectionVarianceDTO> itr2 = totalList.listIterator(); itr2.hasNext();) {
                                                ProjectionVarianceDTO discItemId = itr2.next();
                                                itr2.remove();
                                                resultExcelContainer.addBean(discItemId);
                                                resultExcelContainer.setChildrenAllowed(discItemId, false);
                                                resultExcelContainer.setParent(discItemId, itemId);
                                            }
                                        }
                                    }
                                }
                            } else {
                                resultExcelContainer.setChildrenAllowed(itemId, false);
                            }
                        } else {
                            resultExcelContainer.setChildrenAllowed(itemId, false);
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        excelParentRecords.clear();
    }

    private void getDate() {
        String startDate = null;
        String freq = String.valueOf(frequency.getValue());
        if (fromDate.getValue() != null) {
            if (freq.equals(ConstantsUtil.QUARTERLY)) {
                String from = String.valueOf(fromDate.getValue());
                String[] fromDateValue = from.split(" ");
                String year = fromDateValue[1];
                String querter = fromDateValue[0].replace("Q", "");
                if (querter.equals("1")) {
                    startDate = year + "-1-" + "1";
                } else if (querter.equals("2")) {
                    startDate = year + "-4-" + "1";
                } else if (querter.equals("3")) {
                    startDate = year + "-7-" + "1";
                } else if (querter.equals("4")) {
                    startDate = year + "-10-" + "1";
                }
            }

            if (freq.equals(ConstantsUtil.ANNUAL)) {
                String year = String.valueOf(fromDate.getValue());
                startDate = year + "-1-1";
            }
            if (freq.equals(ConstantsUtil.SEMI_ANNUALLY)) {
                String from = String.valueOf(fromDate.getValue());
                String[] fromDateValue = from.split(" ");
                String year = fromDateValue[1];
                String querter = fromDateValue[0].replace("S", StringUtils.EMPTY);
                if (querter.equals("1")) {
                    startDate = year + "-1-" + "1";
                } else if (querter.equals("2")) {
                    startDate = year + "-7-" + "1";
                }
            }

        }
        pvSelectionDTO.setPivotStartDate(startDate);
    }

//    private String getParentKeyforCustom(ProjectionVarianceDTO itemId, String key, String parentKey) {
//        if (itemId.getParentHierarchyNo() == null) {
////            parentKey = key.substring(0, key.lastIndexOf('.'));
//            parentKey = key;
//        } else {
//            parentKey = itemId.getParentHierarchyNo();
//            if (parentKey.contains("~")) {
//                parentKey = parentKey.substring(parentKey.lastIndexOf('~') + 1);
////                System.out.println("parentKey = " + parentKey);
////                System.out.println("parentKey.indexOf('-') = " + parentKey.indexOf('-'));
////                parentKey = parentKey.substring(parentKey.indexOf('-') + 1);
////                System.out.println("parentKey -------------------= " + parentKey);
////  parentKey = parentKey.substring(0, parentKey.lastIndexOf('.'));
//            }
//        }
//        return parentKey;
//    }

    
    
      private String getParentKeyforCustom(ProjectionVarianceDTO itemId, String key, String parentKey) {
        if (itemId.getParentHierarchyNo() == null) {
//            parentKey = key.substring(0, key.lastIndexOf('.'));
            parentKey = key;
        } else {
            parentKey = itemId.getParentHierarchyNo();
                if (pvSelectionDTO.isIsCustomHierarchy()) {
                String var;
                if (parentKey.contains("~")) {
                    String[] str = parentKey.split("~");
                    var = str[str.length - 1] + "$";
                    parentKey = var + parentKey.substring(0, parentKey.toString().lastIndexOf('~'));
                } else {
                    parentKey = key.substring(key.lastIndexOf('$') + 1);
                }
            } else if (parentKey.toString().contains("~")) {
                parentKey = parentKey.toString().substring(parentKey.toString().lastIndexOf('~') + 1);
                if (!pvSelectionDTO.isIsCustomHierarchy() || !Constants.LabelConstants.PERIOD.toString().equalsIgnoreCase(pvSelectionDTO.getPivotView())) {
                    parentKey = parentKey.substring(parentKey.indexOf('-') + 1);
                }
            }
        }
        return parentKey;
    }

    public void configurePermission() {
        try {
            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtil.USER_ID));
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "Consolidated Financial Forecast" + "," + "Variance Tab");
            if (functionHM.get("generateBtn") != null && !((AppPermission) functionHM.get("generateBtn")).isFunctionFlag()) {
                generateBtn.setVisible(false);
            } else {
                generateBtn.setVisible(true);
            }
            if (functionHM.get("resetBtn") != null && !((AppPermission) functionHM.get("resetBtn")).isFunctionFlag()) {
                resetBtn.setVisible(false);
            } else {
                resetBtn.setVisible(true);
            }
            if (functionHM.get("expandLvlBtn") != null && !((AppPermission) functionHM.get("expandLvlBtn")).isFunctionFlag()) {
                expandLvlBtn.setVisible(false);
            } else {
                expandLvlBtn.setVisible(true);
            }
            if (functionHM.get("collapseLvlBtn") != null && !((AppPermission) functionHM.get("collapseLvlBtn")).isFunctionFlag()) {
                collapseLvlBtn.setVisible(false);
            } else {
                collapseLvlBtn.setVisible(true);
            }
            if (functionHM.get("addViewBtn") != null && !((AppPermission) functionHM.get("addViewBtn")).isFunctionFlag()) {
                addViewBtn.setVisible(false);
            } else {
                addViewBtn.setVisible(true);
            }
            if (functionHM.get("editViewBtn") != null && !((AppPermission) functionHM.get("editViewBtn")).isFunctionFlag()) {
                editViewBtn.setVisible(false);
            } else {
                editViewBtn.setVisible(true);
            }

        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
    }
    
    public void getUnCheckedVariableMenuItem(CustomMenuBar.CustomMenuItem customMenuItem) {

        if (customMenuItem != null) {
            for (CustomMenuBar.CustomMenuItem string : customMenuItem.getChildren()) {
                string.setChecked(false);
            }
        }
    }
    
}
