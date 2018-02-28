package com.stpl.app.gtnforecasting.projectionvariance.form;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.gtnforecasting.abstractforecast.ForecastProjectionVariance;
import com.stpl.app.gtnforecasting.dao.DataSelectionDAO;
import com.stpl.app.gtnforecasting.dao.impl.DataSelectionDAOImpl;
import com.stpl.app.gtnforecasting.discountProjection.form.NMDiscountProjection;
import static com.stpl.app.gtnforecasting.discountProjection.form.NMDiscountProjection.ANULL;
import com.stpl.app.gtnforecasting.dto.PVSelectionDTO;
import com.stpl.app.gtnforecasting.dto.ProjectionVarianceDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.logic.DataSelectionLogic;
import com.stpl.app.gtnforecasting.logic.Utility;
import com.stpl.app.gtnforecasting.projectionresults.dto.FilterGenerator;
import com.stpl.app.gtnforecasting.projectionvariance.dto.ComparisonLookupDTO;
import com.stpl.app.gtnforecasting.projectionvariance.dto.PVParameters;
import com.stpl.app.gtnforecasting.projectionvariance.form.lookup.NMComparisonLookup;
import com.stpl.app.gtnforecasting.projectionvariance.logic.NMPVExcelLogic;
import com.stpl.app.gtnforecasting.projectionvariance.logic.NMProjectionVarianceLogic;
import com.stpl.app.gtnforecasting.projectionvariance.logic.tablelogic.ProjectionVarianceTableLogic;
import com.stpl.app.gtnforecasting.queryUtils.PVQueryUtils;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.ui.ForecastUI;
import com.stpl.app.gtnforecasting.ui.form.ForecastForm;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.ChangeCustomMenuBarValueUtil;
import com.stpl.app.gtnforecasting.utils.CommonUtil;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import static com.stpl.app.gtnforecasting.utils.CommonUtils.isInteger;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.Constant.PVVariables;
import com.stpl.app.gtnforecasting.utils.DataSelectionUtil;
import com.stpl.app.gtnforecasting.utils.FunctionNameUtil;
import com.stpl.app.gtnforecasting.utils.HeaderUtils;
import com.stpl.app.gtnforecasting.utils.PVChart;
import com.stpl.app.gtnforecasting.utils.PVGraphWindow;
import com.stpl.app.gtnforecasting.utils.UISecurityUtil;
import com.stpl.app.model.ForecastConfig;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.service.ForecastConfigLocalServiceUtil;
import com.stpl.app.utils.Constants;
import static com.stpl.app.utils.Constants.CommonConstants.SELECT_ONE;
import static com.stpl.app.utils.Constants.HeaderConstants.HEADER_LEVEL;
import static com.stpl.app.utils.Constants.LabelConstants.*;
import com.stpl.app.utils.UiUtils;
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
import com.vaadin.ui.Window;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItem;
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

import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.asi.ui.custommenubar.MenuItemDTO;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 * The Class ProjectionVariance.
 *
 * @author soundarrajan
 */
public class NMProjectionVariance extends ForecastProjectionVariance {

    private final StplSecurity stplSecurity = new StplSecurity();
    public static final String CAPTION = "CAPTION";
    public static final String GROUP_PROPERTY = "group";
    /**
     * The projection id.
     */
    private int projectionId;
    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    private SessionDTO session;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(NMProjectionVariance.class);

    private CommonLogic commonLogic = new CommonLogic();
    /**
     * The trading partner level no.
     */
    private int tradingPartnerNo = 0;
    /**
     * The excel export image.
     */
    private final Resource excelExportImage = new ThemeResource("img/excel.png");
    /**
     * The graph image.
     */
    private final Resource graphImage = new ThemeResource("img/chart.png");
    /**
     * The result bean.
     */
    private List<Leveldto> currentHierarchy = new ArrayList<>();
    private List<Leveldto> viewChangeHierarchy = new ArrayList<>();
    private List<ComparisonLookupDTO> selectedList = new ArrayList<>();
    private static List<String> oldDiscountNameList = new ArrayList<>();
    /**
     * The custom id.
     */
    /**
     * The custom id to select.
     */
    /**
     *
     */
    private List<String> projNameList = new ArrayList<>();
    private List<Integer> projIdList = new ArrayList<>();
    private Map<Integer, String> projectionMap = new HashMap<>();
    private final NMProjectionVarianceLogic logic = new NMProjectionVarianceLogic();
    private final PVQueryUtils queryUtils = new PVQueryUtils();
    private final ForecastForm nonMandatedForm;
    private List<List<String>> discountlist = new ArrayList<>();
    private boolean firstGenerated = false;
    private final List<Integer> comparisonProjId = new ArrayList<>();
    private List<String> comparisonProjName = new ArrayList<>();
    private boolean editFlag = false;
    public static final String SID = "SID";

    /**
     * The selected projection.
     */
    private final String scrnName;
    private boolean isComparisonLookupOpened;
    private final Map<String, List<ProjectionVarianceDTO>> resultMap = new HashMap();
    private boolean flag = false;

    private final Map<String, Object> excelParentRecords = new HashMap();
    private final List<String> hierarchyKeys = new ArrayList();
    private final List<String> tradingPartnerKeys = new ArrayList();
    private final List<String> discountKeys = new ArrayList();
    protected PVParameters parameterDto = new PVParameters();
    private final NMPVExcelLogic excelLogic = new NMPVExcelLogic(resultMap, pvSelectionDTO, hierarchyKeys, tradingPartnerKeys, discountKeys, parameterDto);
    private int columnSize = 0;
    private final DataSelectionLogic dsLogic = new DataSelectionLogic();
    public static final String EACH = "EACH";

    private final CustomMenuBar.SubMenuCloseListener deductionlistener = new CustomMenuBar.SubMenuCloseListener() {
        @Override
        public void subMenuClose(CustomMenuBar.SubMenuCloseEvent event) {
            String menuItemValue = ChangeCustomMenuBarValueUtil.getMenuItemToDisplay(deductionFilterValues);
            ChangeCustomMenuBarValueUtil.setMenuItemToDisplay(deductionFilterDdlb, menuItemValue);
            generateDiscountToBeLoaded = commonLogic.getFilterValues(deductionFilterValues).get(SID);
            generateDiscountNamesToBeLoaded = commonLogic.getFilterValues(deductionFilterValues).get(CAPTION);
            pvSelectionDTO.setIsdeductionFirst(!generateDiscountToBeLoaded.isEmpty());
            loadCustomerLevelFilter(ANULL.equals(String.valueOf(customerlevelDdlb.getValue())) ? StringUtils.EMPTY : String.valueOf(customerlevelDdlb.getValue()));
            loadProductLevelFilter(ANULL.equals(String.valueOf(productlevelDdlb.getValue())) ? StringUtils.EMPTY : String.valueOf(productlevelDdlb.getValue()));

        }

    };
    private final CustomMenuBar.SubMenuCloseListener productlistener = new CustomMenuBar.SubMenuCloseListener() {
        @Override
        public void subMenuClose(CustomMenuBar.SubMenuCloseEvent event) {
            String menuItemValue = ChangeCustomMenuBarValueUtil.getMenuItemToDisplay(productFilterValues);
            ChangeCustomMenuBarValueUtil.setMenuItemToDisplay(productFilterDdlb, menuItemValue);
            generateProductToBeLoaded = commonLogic.getFilterValues(productFilterValues).get(SID);
            pvSelectionDTO.setIsproductFirst(!generateProductToBeLoaded.isEmpty());
            loadCustomerLevelFilter(ANULL.equals(String.valueOf(customerlevelDdlb.getValue())) ? StringUtils.EMPTY : String.valueOf(customerlevelDdlb.getValue()));
            loadDeductionLevelFilter(ANULL.equals(String.valueOf(deductionlevelDdlb.getValue())) ? StringUtils.EMPTY : String.valueOf(deductionlevelDdlb.getValue()));
        }
    };

    private final CustomMenuBar.SubMenuCloseListener customerlistener = new CustomMenuBar.SubMenuCloseListener() {
        @Override
        public void subMenuClose(CustomMenuBar.SubMenuCloseEvent event) {
            String menuItemValue = ChangeCustomMenuBarValueUtil.getMenuItemToDisplay(customerFilterValues);
            ChangeCustomMenuBarValueUtil.setMenuItemToDisplay(customerFilterDdlb, menuItemValue);
            generateCustomerToBeLoaded = commonLogic.getFilterValues(customerFilterValues).get(SID);
            pvSelectionDTO.setIscustomerFirst(!generateCustomerToBeLoaded.isEmpty());
            loadDeductionLevelFilter(ANULL.equals(String.valueOf(deductionlevelDdlb.getValue())) ? StringUtils.EMPTY : String.valueOf(deductionlevelDdlb.getValue()));
            loadProductLevelFilter(ANULL.equals(String.valueOf(productlevelDdlb.getValue())) ? StringUtils.EMPTY : String.valueOf(productlevelDdlb.getValue()));
        }
    };
    protected CustomMenuBar.SubMenuCloseListener deductionInclusionListener = new CustomMenuBar.SubMenuCloseListener() {
        @Override
        public void subMenuClose(CustomMenuBar.SubMenuCloseEvent event) {
            String menuItemValue = ChangeCustomMenuBarValueUtil.getInclusionMenuItemToDisplay(deductionInclusionValues);
            ChangeCustomMenuBarValueUtil.setMenuItemToDisplay(deductionInclusionDdlb, menuItemValue);
        }
    };
    protected CustomMenuBar.SubMenuCloseListener salesInclusionListener = new CustomMenuBar.SubMenuCloseListener() {
        @Override
        public void subMenuClose(CustomMenuBar.SubMenuCloseEvent event) {
            String menuItemValue = ChangeCustomMenuBarValueUtil.getInclusionMenuItemToDisplay(salesInclusionValues);
            ChangeCustomMenuBarValueUtil.setMenuItemToDisplay(salesInclusionDdlb, menuItemValue);
        }
    };
    protected CustomMenuBar.SubMenuCloseListener displayFormatListener = new CustomMenuBar.SubMenuCloseListener() {
        @Override
        public void subMenuClose(CustomMenuBar.SubMenuCloseEvent event) {
            String menuItemValue = ChangeCustomMenuBarValueUtil.getInclusionMenuItemToDisplay(displayFormatValues);
            ChangeCustomMenuBarValueUtil.setMenuItemToDisplay(displayFormatDdlb, menuItemValue);
        }
    };
    protected CustomMenuBar.SubMenuCloseListener customMenuBarListener = new CustomMenuBar.SubMenuCloseListener() {
        @Override
        public void subMenuClose(CustomMenuBar.SubMenuCloseEvent event) {
            String menuItemValue = ChangeCustomMenuBarValueUtil.getMenuItemToDisplay(customMenuItem);
            ChangeCustomMenuBarValueUtil.setMenuItemToDisplay(customMenuBar, menuItemValue);
        }
    };

    protected CustomMenuBar.SubMenuCloseListener variableCategoryCustomMenuBarListener = new CustomMenuBar.SubMenuCloseListener() {
        @Override
        public void subMenuClose(CustomMenuBar.SubMenuCloseEvent event) {
            String menuItemValue = ChangeCustomMenuBarValueUtil.getMenuItemToDisplay(variableCategoryCustomMenuItem);
            ChangeCustomMenuBarValueUtil.setMenuItemToDisplay(variableCategoryCustomMenuBar, menuItemValue);

        }
    };

    /**
     * The Constant AMOUNT.
     */
    /**
     * The Constant RATE.
     */
    /**
     * Constructor for ProjectionVariance.
     */
    public NMProjectionVariance(ForecastForm form, SessionDTO sessionDTO, String screenName) {
        super(sessionDTO, screenName);
        this.scrnName = screenName;
        LOGGER.info("ProjectionVariance Constructor initiated ");
        nonMandatedForm = form;
        session = nonMandatedForm.getSessions();
        projectionId = session.getProjectionId();
        pvSelectionDTO.setUserId(Integer.parseInt(session.getUserId()));
        pvSelectionDTO.setSessionId(Integer.parseInt(session.getSessionId()));
        pvSelectionDTO.setSessionDTO(sessionDTO);
        excelTable.setVisible(false);
        if (flag) {
            configure();
        }
        flag = true;
        CommonUtil commonUtils = CommonUtil.getInstance();
        commonUtils.loadConvertionFactorComboBox(conversionFactorDdlb, Constant.CONVERSION_FACTOR);

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

        frequency.setNullSelectionItemId(Constant.SELECT_ONE);
        frequency.setNullSelectionAllowed(true);

        List<String> returnList = loadVariablesDdlb();
        String[] variableValues = returnList.toArray(new String[0]);

        customMenuBar.removeSubMenuCloseListener(customMenuBarListener);
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
        customMenuBar.addSubMenuCloseListener(customMenuBarListener);

        String[] variableCategoryValues = Constant.PVVariableCategory.names();

        variableCategoryCustomMenuBar.removeSubMenuCloseListener(variableCategoryCustomMenuBarListener);
        variableCategoryCustomMenuItem = variableCategoryCustomMenuBar.addItem("-Select Variables-", null);
        CustomMenuBar.CustomMenuItem[] variableCategoryCustomItem = new CustomMenuBar.CustomMenuItem[variableCategoryValues.length];
        for (int i = 0; i < variableCategoryValues.length; i++) {
            variableCategoryCustomItem[i] = variableCategoryCustomMenuItem.addItem(variableCategoryValues[i].trim(), null);
            variableCategoryCustomItem[i].setCheckable(true);
            variableCategoryCustomItem[i].setItemClickable(true);

            variableCategoryCustomItem[i].setItemClickNotClosable(true);
        }
        variableCategoryCustomMenuBar.addSubMenuCloseListener(variableCategoryCustomMenuBarListener);
        discountLevel.addItem(Constants.LabelConstants.TOTAL_DISCOUNT.toString());
        discountLevel.addItem(PROGRAM_CATEGORY.getConstant());
        discountLevel.addItem("Program");
        discountLevel.select(Constants.LabelConstants.TOTAL_DISCOUNT.toString());
        discountLevel.addStyleName(Constant.HORIZONTAL);
        if (CommonUtil.isValueEligibleForLoading()) {
            loadCustomerLevel();
            loadCustomerLevelFilter(StringUtils.EMPTY);
            loadProductLevel();
            loadProductLevelFilter(StringUtils.EMPTY);
            loadDedutionLevel();
            loadDeductionLevelFilter(StringUtils.EMPTY);
            loadDeductionInclusion();
            loadSalesInclusion();
            commonLogic.loadUnitOfMeasureDdlb(uomDdlb, session);
        }
        excelBtn.setIcon(excelExportImage);
        graphBtn.setIcon(graphImage);
        initialConfig(Boolean.FALSE);
        addResultTable();
        loadGroupFilterOntabChange();
        group.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                groupChange(true);
            }
        });
        if (Constant.EDIT_SMALL.equals(session.getAction()) || Constant.VIEW.equals(session.getAction())) {
            setProjectionSelection(false);
            initialConfig(Boolean.TRUE);
        }
        frequency.setValue(Constant.QUARTERLY);
        loadDisplayFormatDdlb();
    }

    private void initialConfig(Boolean flag) {
        getCheckedValues();
        getCheckedVariableCategoryValues();
        loadProjectionSelection();
        if (flag) {
            loadVariables();
            configureTable();
        }
    }

    /**
     * Configure table.
     */
    public void configureTable() {
        fullHeader = new CustomTableHeaderDTO();
        leftHeader = HeaderUtils.getVarianceLeftTableColumns(fullHeader);
        List<Object> HeaderPropertyIds = HeaderUtils.getVarianceRightTableColumns(pvSelectionDTO, fullHeader);
        rightHeader = (CustomTableHeaderDTO) HeaderPropertyIds.get(0);
        CustomTableHeaderDTO rightHeaderPeriod = (CustomTableHeaderDTO) HeaderPropertyIds.get(0);
        pvSelectionDTO.setRightHeaderPeriod(rightHeaderPeriod);

        alignRight();
        resultBeanContainer = new ExtTreeContainer<>(ProjectionVarianceDTO.class, ExtContainer.DataStructureMode.MAP);

        resultBeanContainer.setColumnProperties(leftHeader.getProperties());
        resultBeanContainer.setColumnProperties(rightHeader.getProperties());
        tableLogic.setContainerDataSource(resultBeanContainer);
        tableLogic.setScreenName(scrnName);
        tableLogic.setPageLength(NumericConstants.TWENTY);
        tableLogic.sinkItemPerPageWithPageLength(false);
        List<Integer> pagelength = CommonLogic.getPageNumber();
        tableLogic.getControlConfig().setPageLengthsAndCaptions(pagelength);
        tableLogic.setTreeNodeMultiClick(false);
        final ExtFilterTreeTable leftTable = resultsTable.getLeftFreezeAsTable();
        final ExtFilterTreeTable rightTable = resultsTable.getRightFreezeAsTable();
        leftTable.setImmediate(true);
        leftTable.reConstruct(true);
        rightTable.setImmediate(true);
        rightTable.reConstruct(true);
        resultsTable.setHeight(Constant.SIX_FIFTY_PX);
        leftTable.setHeight(Constant.SIX_FIFTY_PX);
        rightTable.setHeight(Constant.SIX_FIFTY_PX);
        leftTable.setVisibleColumns(leftHeader.getSingleColumns().toArray());
        leftTable.setColumnHeaders(leftHeader.getSingleHeaders().toArray(new String[leftHeader.getSingleHeaders().size()]));

        rightTable.setVisibleColumns(rightHeader.getSingleColumns().toArray());
        rightTable.setColumnHeaders(rightHeader.getSingleHeaders().toArray(new String[rightHeader.getSingleHeaders().size()]));
        rightTable.setDoubleHeaderVisible(true);
        rightTable.setDoubleHeaderVisibleColumns(rightHeader.getDoubleColumns().toArray());
        rightTable.setDoubleHeaderColumnHeaders(rightHeader.getDoubleHeaders().toArray(new String[rightHeader.getDoubleHeaders().size()]));

        rightTable.setDoubleHeaderMap(rightHeader.getDoubleHeaderMaps());
        for (Object columns : rightHeader.getDoubleColumns()) {

            Map<Object, Object[]> doubleHeaderMap;
            doubleHeaderMap = rightHeader.getDoubleHeaderMaps();
            columnSize = doubleHeaderMap.get(columns).length;
        }
        resultsTable.getLeftFreezeAsTable().setFilterBarVisible(true);
        resultsTable.getLeftFreezeAsTable().setFilterDecorator(new ExtDemoFilterDecorator());
        pvSelectionDTO.setProjectionId(projectionId);
        pvSelectionDTO.setTreeLevelNo(Integer.parseInt(getSessionDTO().getCustomerLevelNumber()));
        pvSelectionDTO.setPpa(CommonLogic.isPPA(Boolean.TRUE, pvSelectionDTO));
        pvSelectionDTO.setReturns(CommonLogic.isReturns(Boolean.TRUE, pvSelectionDTO));
        resultsTable.getLeftFreezeAsTable().setFilterGenerator(new FilterGenerator(session, CommonUtil.isValueEligibleForLoading()));

        UiUtils.setExtFilterTreeTableColumnWidth(rightTable, NumericConstants.ONE_SEVEN_ZERO, TAB_PROJECTION_VARIANCE.getConstant());
        if (fromDate.getValue() != null && !"null".equals(String.valueOf(fromDate.getValue())) && !Constant.SELECT_ONE.equals(String.valueOf(fromDate.getValue()))) {
            List<String> periodList = new ArrayList<>(pvSelectionDTO.getPeriodHeaderList());
            Object toDateString;

            if (String.valueOf(projectionPeriodOrder.getValue()).equals("Descending")) {
                toDateString = toDate.getValue() == null ? pvSelectionDTO.getPeriodListMap().get(periodList.get(0)) : toDate.getValue();
            } else {
                toDateString = toDate.getValue() == null ? pvSelectionDTO.getPeriodListMap().get(periodList.get(periodList.size() - 1)) : toDate.getValue();
            }
            if (pivotView.getValue().equals("Period")) {
                fullHeader = logic.getDateRangeHeaders(rightTable, rightHeader, fullHeader, fromDate.getValue(), toDateString);
            } else {
                if (String.valueOf(projectionPeriodOrder.getValue()).equals("Descending")) {
                    Collections.reverse(periodList);
                }

                List<String> pivotList = new ArrayList<>();
                String fromPeriod = fromDate.getValue().toString().replace(" ", StringUtils.EMPTY);
                String toPeriod = toDate.getValue().toString().replace(" ", StringUtils.EMPTY);
                if (rightHeader.getFrequencyDivision() == NumericConstants.TWELVE) {
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
        if (("Variable".equals(pivotView.getValue().toString())) && (!discountLevel.getValue().equals(Constants.LabelConstants.TOTAL_DISCOUNT.toString())
                && (pvSelectionDTO.isVarDisAmount() || pvSelectionDTO.isVarDisRate() || pvSelectionDTO.isVarRPU() || pvSelectionDTO.isDiscountPerExFactory()))) {
            //Discount
            final List<String> discountNames = new ArrayList(pvSelectionDTO.getDeductionLevelCaptions());
            // PPA.
            List listObj = CommonLogic.getPPADiscountNameList(pvSelectionDTO);
            if (listObj != null && !listObj.isEmpty()) {
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

    /**
     * Load frequency.
     *
     * @param event the event
     */
    @Override
    protected void loadFrequency() {

        LOGGER.info("ProjectionVariance ValueChangeEvent initiated with frequency -->= {}", frequency.getValue());
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
        pvSelectionDTO.setDeductionHierarchyNo(StringUtils.EMPTY);
        tableLogic.setProjectionResultsData(currentHierarchy, pvSelectionDTO, levelNo, pvSelectionDTO);
        resultsTable.getLeftFreezeAsTable().setFilterBarVisible(true);
        resultsTable.getLeftFreezeAsTable().setFilterDecorator(new ExtDemoFilterDecorator());
        pvSelectionDTO.setProjectionId(projectionId);
        resultsTable.getLeftFreezeAsTable().setFilterGenerator(new FilterGenerator(session, CommonUtil.isValueEligibleForLoading()));
    }

    private void loadExcelResultTable(int levelNo, String hierarchyNo) {
        LOGGER.info("Inside loadExcelResultTable");
        resultExcelContainer.removeAllItems();
        pvSelectionDTO.setFilterLevelNo(levelNo);
        pvSelectionDTO.setFilterHierarchyNo(hierarchyNo);
        pvSelectionDTO.setProductHierarchyNo(StringUtils.EMPTY);
        pvSelectionDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
        pvSelectionDTO.setDeductionHierarchyNo(StringUtils.EMPTY);
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
            } catch (PortalException | SystemException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        final NMComparisonLookup comparisonLookupWindow = new NMComparisonLookup(comparison, projectionId, selectedList, scrnName);
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
                    comparison.setReadOnly(true);
                }
                isComparisonLookupOpened = true;
                loadComparison();
                if (!pvSelectionDTO.getProjIdList().isEmpty()) {
                    for (int j = 0; j < pvSelectionDTO.getProjIdList().size(); j++) {
                        comparisonBasis.addItem(j);
                        comparisonBasis.setItemCaption(j, pvSelectionDTO.getProjectionMap().get(pvSelectionDTO.getProjIdList().get(j)));
                        comparisonBasis.select(CURRENT_PROJECTION);
                    }
                }
            }
        });

        LOGGER.info("Comparision lookup ends");
    }
    public static final String CURRENT_PROJECTION = "Current Projection";

    /**
     * Excel export button click method.
     *
     * @param event the event
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    @Override
    protected void excelBtnLogic() {
        try {
            excelTable.setRefresh(Boolean.FALSE);
            setPvSelection(variableCategoryValue, variablesValue);
            excelForCommercial();
            long start = System.currentTimeMillis();
            excelTable.setRefresh(Boolean.TRUE);
            long end = System.currentTimeMillis();
            LOGGER.info("Time taken to refresh table = {}", (end - start));
            start = end;
            ForecastUI.setEXCEL_CLOSE(true);
            int leftcolumnsize = NumericConstants.ONE;
            int ColSize = 252;
            int maxColSize = ColSize % columnSize == NumericConstants.ZERO ? 252 : 250;
            Object[] leftColumns = new Object[leftcolumnsize + maxColSize];
            String[] leftHeaders = new String[leftcolumnsize + maxColSize];
            System.arraycopy(fullHeader.getSingleColumns().toArray(), NumericConstants.ZERO, leftColumns, NumericConstants.ZERO, NumericConstants.ONE);
            System.arraycopy(fullHeader.getSingleHeaders().toArray(), NumericConstants.ZERO, leftHeaders, NumericConstants.ZERO, NumericConstants.ONE);
            List<Object> fullColumns = fullHeader.getSingleColumns();
            int totalDoubleHeaderSize = (fullHeader.getDoubleHeaderMaps().size() - NumericConstants.ONE) * columnSize + NumericConstants.ONE;
            Map<Object, Object[]> doubleheadermap = new HashMap<>();
            int oldDoubleHeaderSize = 0;
            if (totalDoubleHeaderSize >= 255) {
                ExcelExport export = null;
                int sheetCount = 0;
                for (int i = leftcolumnsize; i < totalDoubleHeaderSize; i += (maxColSize)) {
                    int remainingColumnSize = totalDoubleHeaderSize - (sheetCount * maxColSize + NumericConstants.ONE);
                    int currentColumnSize = remainingColumnSize > maxColSize + NumericConstants.ONE ? maxColSize : remainingColumnSize;
                    int doubleHeaderSize = currentColumnSize / columnSize;
                    Object[] leftDoubleColumns = new Object[doubleHeaderSize + NumericConstants.ONE];
                    String[] leftDoubleHeaders = new String[doubleHeaderSize + NumericConstants.ONE];

                    if (remainingColumnSize < maxColSize + 1) {
                        currentColumnSize = remainingColumnSize;
                        leftColumns = new Object[leftcolumnsize + remainingColumnSize];
                        leftHeaders = new String[leftcolumnsize + remainingColumnSize];
                        leftDoubleColumns = new Object[doubleHeaderSize + NumericConstants.ONE];
                        leftDoubleHeaders = new String[doubleHeaderSize + NumericConstants.ONE];
                        System.arraycopy(fullHeader.getSingleColumns().toArray(), NumericConstants.ZERO, leftColumns, NumericConstants.ZERO, NumericConstants.ONE);
                        System.arraycopy(fullHeader.getSingleHeaders().toArray(), NumericConstants.ZERO, leftHeaders, NumericConstants.ZERO, NumericConstants.ONE);
                    }

                    System.arraycopy(fullHeader.getDoubleColumns().toArray(), NumericConstants.ZERO, leftDoubleColumns, NumericConstants.ZERO, NumericConstants.ONE);
                    System.arraycopy(fullHeader.getDoubleHeaders().toArray(), NumericConstants.ZERO, leftDoubleHeaders, NumericConstants.ZERO, NumericConstants.ONE);

                    System.arraycopy(fullColumns.toArray(), i, leftColumns, leftcolumnsize, currentColumnSize);
                    System.arraycopy(fullHeader.getSingleHeaders().toArray(), i, leftHeaders, leftcolumnsize, currentColumnSize);
                    System.arraycopy(fullHeader.getDoubleColumns().toArray(), i == leftcolumnsize ? leftcolumnsize : oldDoubleHeaderSize,
                            leftDoubleColumns, leftcolumnsize, doubleHeaderSize);
                    System.arraycopy(fullHeader.getDoubleHeaders().toArray(), i == leftcolumnsize ? leftcolumnsize : oldDoubleHeaderSize,
                            leftDoubleHeaders, leftcolumnsize, doubleHeaderSize);
                    doubleheadermap.clear();
                    for (Object leftColumn : leftDoubleColumns) {
                        for (Map.Entry<Object, Object[]> entry : fullHeader.getDoubleHeaderMaps().entrySet()) {
                            Object key = entry.getKey();
                            Object[] value = entry.getValue();
                            if (leftColumn.toString().startsWith(key.toString())) {
                                doubleheadermap.put(key, value);
                            }
                        }
                    }
                    excelTable.setVisibleColumns(leftColumns);
                    excelTable.setColumnHeaders(leftHeaders);
                    excelTable.setRefresh(true);
                    excelTable.setDoubleHeaderVisible(true);
                    excelTable.setDoubleHeaderVisibleColumns(leftDoubleColumns);
                    excelTable.setDoubleHeaderColumnHeaders(leftDoubleHeaders);
                    excelTable.setDoubleHeaderMap(doubleheadermap);
                    oldDoubleHeaderSize = doubleHeaderSize + NumericConstants.ONE;
                    String sheetName = "Projection Variance " + sheetCount;
                    if (i == leftcolumnsize) {
                        export = new ExcelExport(new ExtCustomTableHolder(excelTable), TAB_PROJECTION_VARIANCE.getConstant(), TAB_PROJECTION_VARIANCE.getConstant(), "ProjectionVariance.xls", false);
                    } else {
                        export.setNextTableHolder(new ExtCustomTableHolder(excelTable), sheetName);
                    }
                    sheetCount++;
                    export.export();
                }
            } else {
                Object[] singleHeader = fullHeader.getDoubleHeaderMaps().get(GROUP_PROPERTY);
                List<Object> listHeaders = new ArrayList(Arrays.asList(singleHeader));
                listHeaders.remove(GROUP_PROPERTY);
                fullHeader.getDoubleHeaderMaps().put(GROUP_PROPERTY, listHeaders.toArray());
                fullHeader.getSingleColumns().remove(GROUP_PROPERTY);
                fullHeader.getSingleHeaders().remove(0);

                Object[] displayFormatIndex = CommonUtil.getDisplayFormatSelectedValues(displayFormatValues);
                if (displayFormatIndex.length == 1) {
                    for (int i = 0; i < displayFormatIndex.length; i++) {
                        LOGGER.info("obj--------------= {}", i);
                        int index = (Integer) displayFormatIndex[i];
                        if (index == 0) {
                            listHeaders.remove("dfLevelName");
                            fullHeader.getDoubleHeaderMaps().put(GROUP_PROPERTY, listHeaders.toArray());
                            fullHeader.getSingleColumns().remove("dfLevelName");
                            fullHeader.getSingleHeaders().remove(1);
                        } else {
                            listHeaders.remove("dfLevelNumber");
                            fullHeader.getDoubleHeaderMaps().put(GROUP_PROPERTY, listHeaders.toArray());
                            fullHeader.getSingleColumns().remove("dfLevelNumber");
                            fullHeader.getSingleHeaders().remove(0);
                        }

                    }
                }

                excelTable.setVisibleColumns(fullHeader.getSingleColumns().toArray());
                excelTable.setColumnHeaders(fullHeader.getSingleHeaders().toArray(new String[fullHeader.getSingleHeaders().size()]));
                excelTable.setDoubleHeaderVisible(true);
                excelTable.setDoubleHeaderVisibleColumns(fullHeader.getDoubleColumns().toArray());
                excelTable.setDoubleHeaderColumnHeaders(fullHeader.getDoubleHeaders().toArray(new String[fullHeader.getDoubleHeaders().size()]));
                excelTable.setDoubleHeaderMap(fullHeader.getDoubleHeaderMaps());
                ExcelExport export = new ExcelExport(new ExtCustomTableHolder(excelTable), TAB_PROJECTION_VARIANCE.getConstant(), TAB_PROJECTION_VARIANCE.getConstant(), "Projection_Variance.xls", false);
                export.export();
                end = System.currentTimeMillis();
                LOGGER.info("Time taken to export = {}", (end - start));
            }
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
    @Override
    protected void graphBtnLogic() {

        List chartiLst = new ArrayList();
        for (Object obj : resultsTable.getRightFreezeAsTable().getContainerDataSource().getItemIds()) {
            ProjectionVarianceDTO dto = (ProjectionVarianceDTO) obj;
            if (dto.getLevelNo() != null && dto.getParent() == 0) {
                chartiLst.add(dto);
            }
        }
        final PVChart chart = new PVChart(chartiLst, String.valueOf(frequency.getValue()), StringUtils.EMPTY, fullHeader, pvSelectionDTO);
        final PVGraphWindow salesGraphWindow = new PVGraphWindow(chart.getChart(), TAB_PROJECTION_VARIANCE.getConstant());
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
                discountLevel.select(Constants.LabelConstants.TOTAL_DISCOUNT.toString());
                projectionPeriodOrder.setValue(Constant.ASCENDING);
                pivotView.setValue(Constant.PERIOD);
                fromDate.setValue(getSelectOne());
                toDate.setValue(getSelectOne());
                comparison.setReadOnly(false);
                comparison.setValue(Constant.SELECT_ONE);
                comparison.setData(null);
                comparison.setReadOnly(true);
                getUnCheckedVariableMenuItem(variableCategoryCustomMenuItem);

                variables.unselect(CommonUtils.VAR_GTS);
                variables.unselect(CommonUtils.VAR_PERCENTAGE);
                variables.unselect(CommonUtils.VAR_CONTRACT_SALES);
                variables.unselect(CommonUtils.VAR_CONTRACT_UNITS);
                variables.unselect(CommonUtils.VAR_DIS_AMOUNT);
                variables.unselect(CommonUtils.VAR_DIS_RATE);
                variables.unselect(CommonUtils.VAR_NETSALES);
                getUnCheckedVariableMenuItem(customMenuItem);
                variables.setImmediate(true);

                uomDdlb.select(EACH);
                loadDisplayFormatDdlb();
                if (Constant.EDIT_SMALL.equalsIgnoreCase(session.getAction()) || Constant.VIEW.equalsIgnoreCase(session.getAction())) {
                    setProjectionSelection(true);
                    loadSalesInclusion();
                    loadDeductionInclusion();
                    initialConfig(Boolean.TRUE);
                } else {
                    resetForAdd();
                }
            }
        }.getConfirmationMessage("Confirm Reset", "Are you sure you want to reset the page to default values?");

    }

    public void resetForAdd() throws IllegalStateException {
        pvSelectionDTO.setDeductionLevelFilter(Collections.EMPTY_LIST);
        pvSelectionDTO.setDeductionLevelCaptions(Collections.EMPTY_LIST);
        pvSelectionDTO.setProductLevelFilter(Collections.EMPTY_LIST);
        pvSelectionDTO.setCustomerLevelFilter(Collections.EMPTY_LIST);
        CommonLogic.unCheckMultiSelect(productFilterValues);
        CommonLogic.unCheckMultiSelect(customerFilterValues);
        CommonLogic.unCheckMultiSelect(deductionFilterValues);
        customerlevelDdlb.select(Constant.SELECT_ONE);
        productlevelDdlb.select(Constant.SELECT_ONE);
        deductionlevelDdlb.select(Constant.SELECT_ONE);
        loadSalesInclusion();
        loadDeductionInclusion();
    }

    @Override
    protected void expandCollapseLevelOption(boolean isExpand, Object value) {
        List<Object> levelHierarchy = CommonLogic.getLevelNoAndHierarchyNo(value);
        int levelNo = Integer.parseInt(String.valueOf(levelHierarchy.get(0)));
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
        if (customId != 0) {
            session.setCustomId(customId);
            Utility.loadCustomHierarchyList(session);
        }
        if (CommonUtil.isValueEligibleForLoading()) {
            session.setDeductionLevelDetails(dsLogic.getRelationshipDetailsDeduction(session, session.getDedRelationshipBuilderSid(), true));
        }
        viewChangeHierarchy = session.getCustomHierarchyMap().get(customId);
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
    @Override
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
            Object[] displayValidation = CommonUtil.getDisplayFormatSelectedValues(displayFormatValues);
            if (!CommonUtil.nullCheck(displayValidation) && displayValidation.length == 0) {
                AbstractNotificationUtils.getErrorNotification("No Display Format Selected", "Please select value(s) from the Display Format field");
            } else {
                pvSelectionDTO.setCustomerLevelFilter((List) (generateCustomerToBeLoaded != null ? generateCustomerToBeLoaded : new ArrayList<>()));
                pvSelectionDTO.setProductLevelFilter((List) (generateProductToBeLoaded != null ? generateProductToBeLoaded : new ArrayList<>()));
                pvSelectionDTO.setDeductionLevelFilter((List) (generateDiscountToBeLoaded != null ? generateDiscountToBeLoaded : new ArrayList<>()));
                pvSelectionDTO.setDeductionLevelCaptions((List) (generateDiscountNamesToBeLoaded != null ? generateDiscountNamesToBeLoaded : new ArrayList<>()));

                getDiscount();
                if (pivotView.getValue().equals("Variable")) {
                    pivotPanel.setCaption("Variable Pivot View");
                } else {
                    pivotPanel.setCaption("Period Pivot View");
                }
                generated = true;
                firstGenerated = true;
                getCheckedValues();
                getCheckedVariableCategoryValues();
                loadVariables();
                loadProjectionSelection();
                List<String> checkedValues = getCheckedSalesInclusionValues();
                pvSelectionDTO.setSalesInclusion(null);
                List<String> checkedValuesDeduction = getCheckedDeductionInclusionValues();
                pvSelectionDTO.getSessionDTO().setDeductionInclusion(null);
                if (checkedValues.size() == 1) {
                    pvSelectionDTO.setSalesInclusion(checkedValues.get(0).equalsIgnoreCase("Yes") ? "1" : "0");
                }
                if (checkedValuesDeduction.size() == 1) {
                    pvSelectionDTO.getSessionDTO().setDeductionInclusion(checkedValuesDeduction.get(0).equalsIgnoreCase("Yes") ? "1" : "0");
                }
                pvSelectionDTO.setUomCode(uomDdlb.getValue() == null ? EACH : String.valueOf(uomDdlb.getValue()));
                if (deductionlevelDdlb.getValue() != null) {
                    session.setSelectedDeductionLevelNoPv(Integer.parseInt(String.valueOf(deductionlevelDdlb.getValue())));
                }
                CommonLogic.updateForFilter(pvSelectionDTO, "DEDUCTION", true);
                session.setDiscountRSlist(logic.getRsIdsForDiscountAndUdcs(session));

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
            }
        } catch (NumberFormatException ex) {
            LOGGER.error(ex.getMessage());
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
            group.setEnabled(false);
            if (!pvSelectionDTO.isIsCustomHierarchy()) {
                levelFilter.setEnabled(true);
            } else {
                levelFilter.setEnabled(false);
            }
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
                newLevelList = CommonLogic.getAllHierarchyLevels(pvSelectionDTO.getCustomerLevelNo(), projectionId, Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY, pvSelectionDTO.getGroupFilter(), pvSelectionDTO.getUserId(), pvSelectionDTO.getSessionId(), pvSelectionDTO.getCustRelationshipBuilderSid(), pvSelectionDTO.getSessionDTO().getAction());
            } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(pvSelectionDTO.getHierarchyIndicator())) {
                newLevelList = CommonLogic.getAllHierarchyLevels(pvSelectionDTO.getProductLevelNo(), projectionId, Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY, pvSelectionDTO.getGroupFilter(), pvSelectionDTO.getUserId(), pvSelectionDTO.getSessionId(), pvSelectionDTO.getProdRelationshipBuilderSid(), pvSelectionDTO.getSessionDTO().getAction());
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
            targetItem = new BeanItem<>(
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
        pvSelectionDTO.setCurrentProjectionName(CURRENT_PROJECTION);
        pvSelectionDTO.setProjIdList(projIdList);
        pvSelectionDTO.setProjectionMap(projectionMap);
        pvSelectionDTO.setVariableCategory(variableCategoryValue);
        pvSelectionDTO.setUserId(Integer.parseInt(session.getUserId()));
        pvSelectionDTO.setSessionId(Integer.parseInt(session.getSessionId()));
        pvSelectionDTO.setCustRelationshipBuilderSid(session.getCustRelationshipBuilderSid());
        pvSelectionDTO.setProdRelationshipBuilderSid(session.getProdRelationshipBuilderSid());
        pvSelectionDTO.setForecastDTO(session.getForecastDTO());
        pvSelectionDTO.setProjectionId(projectionId);
        pvSelectionDTO.setCurrentProjId(projectionId);
        pvSelectionDTO.setProjectionNum(CommonUtils.getProjectionNumber(String.valueOf(frequency.getValue()), session));
        pvSelectionDTO.setProjectionOrder(String.valueOf(projectionPeriodOrder.getValue()));
        pvSelectionDTO.setCustomerLevelNo(Integer.parseInt(session.getCustomerLevelNumber()));
        pvSelectionDTO.setProductLevelNo(Integer.parseInt(session.getProductLevelNumber()));
        pvSelectionDTO.setVariables(variablesValue);
        pvSelectionDTO.setHistoryNum(CommonUtils.getHistoryProjectionNum(String.valueOf(frequency.getValue()), session));
        pvSelectionDTO.setCustomId(customId);
        pvSelectionDTO.setTabName(scrnName);
        pvSelectionDTO.setComparisonBasis(String.valueOf(comparisonBasis.getValue()));
        pvSelectionDTO.setView(String.valueOf(view.getValue()));
        pvSelectionDTO.setDisplayFormat(CommonUtil.getDisplayFormatSelectedValues(displayFormatValues));
        pvSelectionDTO.setConversionFactor(conversionFactorDdlb.getValue());
        viewChange(false);
        groupChange(false);
        setCurrentHierarchy(new ArrayList<>(viewChangeHierarchy));
    }

    public void addLowerLevelsForExport(Object id) {
        pvSelectionDTO.setFilterLevelNo(0);
        pvSelectionDTO.setFilterHierarchyNo(StringUtils.EMPTY);
        pvSelectionDTO.setProductHierarchyNo(StringUtils.EMPTY);
        pvSelectionDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
        pvSelectionDTO.setDeductionHierarchyNo(StringUtils.EMPTY);
        int count = logic.getConfiguredProjectionVarianceCount(id, pvSelectionDTO, pvSelectionDTO, true);
        List<ProjectionVarianceDTO> resultList = logic.getConfiguredProjectionVariance(id, pvSelectionDTO, pvSelectionDTO, 0, count);
        loadDataToContainer(resultList, id);
    }

    public List<Leveldto> getCurrentHierarchy() {
        return currentHierarchy == null ? currentHierarchy : new ArrayList<>(currentHierarchy);
    }

    public void setCurrentHierarchy(List<Leveldto> currentHierarchy) {
        this.currentHierarchy = currentHierarchy == null ? currentHierarchy : new ArrayList<>(currentHierarchy);
    }

    public void generateLogic() {

        levelFilter.removeValueChangeListener(levelFilterChangeOption);
        loadLevelFilter();
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
        pvSelectionDTO.setNetSalesExFactory(false);
        pvSelectionDTO.setDiscountPerExFactory(false);
        pvSelectionDTO.setNetExFactorySales(false);
        pvSelectionDTO.setNetExFactorySalesPerExFactory(false);

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
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constant.PVVariables.NET_SALES_PER_EX_FACTORY.toString()))) {
                pvSelectionDTO.setNetSalesExFactory(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constant.PVVariables.DISCOUNT_PER_EX_FACTORY.toString()))) {
                pvSelectionDTO.setDiscountPerExFactory(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constant.PVVariables.NET_EX_FACTORY_SALES.toString()))) {
                pvSelectionDTO.setNetExFactorySales(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constant.PVVariables.NET_EX_FACTORY_SALES_PER_EX_FACTORY.toString()))) {
                pvSelectionDTO.setNetExFactorySalesPerExFactory(true);
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
        List<String> periodList = new ArrayList<>(pvSelectionDTO.getPeriodHeaderList());
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
        List<String> periodList = new ArrayList<>(pvSelectionDTO.getPeriodHeaderList());
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
            projectionMap = new HashMap<>();
            projNameList = new ArrayList<>();
            projIdList = new ArrayList<>();
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

    public void loadGroupFilterOntabChange() {
        loadGroupFilter(true);
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
                pvSelectionDTO.setHierarchyIndicator("D");
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
                pvSelectionDTO.setTpLevel(session.getTradingPartner());
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

    public void loadGroupFilter(boolean isPPA) {
        LOGGER.info("loadGroupFilter initiated ");
        group.setEnabled(true);
        group.removeAllItems();
        group.setNullSelectionAllowed(false);
        List<String> groupList = CommonLogic.getAllGroup(session, isPPA);
        if (groupList != null && !groupList.isEmpty()) {
            for (String groups : groupList) {
                group.addItem(groups);
            }
            group.select(groupList.get(0));
        }

        LOGGER.info("loadGroupFilter ends ");
    }

    public static List<Date> getStartandTodate() {
        List<Date> result = new ArrayList<>();
        ForecastConfig forecastConfig = getTimePeriod();
        Date fromDate = null;
        Date toDate = null;
        if (forecastConfig != null) {
            if (forecastConfig.getProcessMode()) {
                if (Constant.MONTH1.equalsIgnoreCase(String.valueOf(forecastConfig.getHistFreq()))) {
                    fromDate = DataSelectionUtil.calculateHistory(Constant.MONTH1, forecastConfig.getHistValue());
                    toDate = DataSelectionUtil.calculateProjection(Constant.MONTH1, forecastConfig.getProjValue());
                } else if (Constant.QUARTER1.equalsIgnoreCase(String.valueOf(forecastConfig.getHistFreq()))) {
                    fromDate = DataSelectionUtil.calculateHistory(Constant.QUARTER1, forecastConfig.getHistValue());
                    toDate = DataSelectionUtil.calculateProjection(Constant.QUARTER1, forecastConfig.getProjValue());
                } else if (Constant.SEMI_ANNUAL_SMALL.equalsIgnoreCase(String.valueOf(forecastConfig.getHistFreq()))) {
                    fromDate = DataSelectionUtil.calculateHistory(Constant.SEMI_ANNUAL_SMALL, forecastConfig.getHistValue());
                    toDate = DataSelectionUtil.calculateProjection(Constant.SEMI_ANNUAL_SMALL, forecastConfig.getProjValue());
                } else if (Constant.ANNUAL.equalsIgnoreCase(String.valueOf(forecastConfig.getHistFreq()))) {
                    fromDate = DataSelectionUtil.calculateHistory(Constant.ANNUAL, forecastConfig.getHistValue());
                    toDate = DataSelectionUtil.calculateProjection(Constant.ANNUAL, forecastConfig.getProjValue());
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
        } catch (PortalException | SystemException ex) {
            LoggerFactory.getLogger(NMProjectionVariance.class.getName()).error(StringUtils.EMPTY, ex);
        }
        DynamicQuery dynamicQuery = ForecastConfigLocalServiceUtil.dynamicQuery();
        dynamicQuery.add(RestrictionsFactoryUtil.eq("businessProcessType", businessProcessType));
        dynamicQuery.addOrder(OrderFactoryUtil.desc(Constant.VERSION_NO));
        try {
            resultList = dataSelectionDao.getForecastConfig(dynamicQuery);
        } catch (SystemException ex) {
            LoggerFactory.getLogger(CommonUtils.class.getName()).error(StringUtils.EMPTY, ex);
        }
        ForecastConfig forecastConfig = null;
        if (resultList != null && !resultList.isEmpty()) {
            forecastConfig = (ForecastConfig) resultList.get(0);
        }
        return forecastConfig;
    }

    public static int getProjections(Date endDate, String frequency) {

        if (frequency.equals(Constant.ANNUALLY)) {
            return endDate.getYear() - new Date().getYear();
        } else {
            Calendar startCalendar = new GregorianCalendar();
            startCalendar.setTime(new Date());
            Calendar endCalendar = new GregorianCalendar();
            endCalendar.setTime(endDate);
            int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
            int diffMonth = diffYear * NumericConstants.TWELVE + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
            if (frequency.equals(Constant.QUARTERLY)) {
                if (diffMonth % NumericConstants.THREE == 0) {
                    return diffMonth / NumericConstants.THREE;
                } else {
                    return (diffMonth / NumericConstants.THREE) + 1;
                }

            } else if (frequency.equals(Constant.SEMIANNUALLY)) {
                if (diffMonth % NumericConstants.SIX == 0) {
                    return diffMonth / NumericConstants.SIX;
                } else {
                    return (diffMonth / NumericConstants.SIX) + 1;
                }
            } else if (frequency.equals(Constant.MONTHLY)) {
                return diffMonth;
            }
            return 0;
        }
    }

    public void fetchDiscountsFromSave() {
        Map<Object, Object> map = CommonLogic.getNMProjectionSelection(projectionId, TAB_DISCOUNT_PROJECTION.getConstant());
        try {
            if (map != null && map.size() > 0) {
                String discountType = map.get(HEADER_LEVEL.getConstant()).toString();
                if (PROGRAM.getConstant().equals(discountType)) {
                    List<String> discountNames1 = new LinkedList<>(Arrays.asList(String.valueOf(map.get("Selected Discounts")).split("\\s*,\\s*")));
                    if (PROGRAM_CATEGORY.getConstant().equals(discountLevel.getValue())) {
                        List<List<String>> discList = null;
                        if (!compareDiscountList(discountNames1, oldDiscountNameList)) {
                            discList = CommonLogic.getPriceGroupTypeList(discountNames1, pvSelectionDTO.getSessionDTO());
                            session.setDiscountlist(discountlist);
                        } else {
                            discList = session.getDiscountlist();
                        }
                        oldDiscountNameList = discountNames1;
                        discountlist.add(discList.get(0));
                        discountlist.add(discList.get(1));
                    } else {
                        discountlist = CommonLogic.getDiscountNoList(discountNames1, true, pvSelectionDTO.getSessionDTO());
                    }
                } else if (PROGRAM_CATEGORY.getConstant().equals(discountType)) {
                    List<String> priceGroupType = new LinkedList<>(Arrays.asList(String.valueOf(map.get("Selected Discounts")).split("\\s*,\\s*")));
                    if (PROGRAM_CATEGORY.getConstant().equals(discountLevel.getValue())) {
                        List<List<String>> discList = CommonLogic.getDiscountNoList(priceGroupType, false, pvSelectionDTO.getSessionDTO());
                        discountlist.add(discList.get(0));
                        discountlist.add(priceGroupType);
                    } else {
                        discountlist = CommonLogic.getDiscountNoList(priceGroupType, false, pvSelectionDTO.getSessionDTO());
                    }
                }
            }
            session.setDiscountRSlistUpdated(true);
            session.setDiscountRSlist(discountlist);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    public void getDiscount() {
        NMDiscountProjection dp = nonMandatedForm.getDiscountProjection();
        if (dp != null) {
            String discountType = nonMandatedForm.getDiscountProjection().getDiscountType();
            if (dp.getResultBeanContainer().size() > 0 && discountType != null && session.isDiscountRSlistUpdated()) {
                discountlist = new ArrayList<>();
                if (PROGRAM_CATEGORY.getConstant().equals(discountType)) {
                    List<String> priceGroupType = nonMandatedForm.getDiscountProjection().getDiscountNamesList();
                    if (discountLevel.getValue().equals(PROGRAM_CATEGORY.getConstant())) {
                        List<List<String>> discList = session.getDiscountRSlist();
                        discountlist.add(discList.get(0));
                        discountlist.add(priceGroupType);
                    } else {
                        discountlist = session.getDiscountRSlist();
                    }
                } else if (PROGRAM.getConstant().equals(discountType)) {
                    List<List<String>> discList = null;
                    List<String> discountNameList = nonMandatedForm.getDiscountProjection().getDiscountNamesList();
                    for (int i = 0; i < discountNameList.size(); i++) {
                        discountNameList.set(i, discountNameList.get(i).split("~")[0]);
                    }
                    List<List<String>> rsList = session.getDiscountRSlist();
                    if (discountLevel.getValue().equals(PROGRAM_CATEGORY.getConstant())) {
                        if (!compareDiscountList(discountNameList, oldDiscountNameList)) {
                            discList = CommonLogic.getPriceGroupTypeList(discountNameList, pvSelectionDTO.getSessionDTO());
                            session.setDiscountlist(discList);
                        } else {
                            discList = session.getDiscountlist();
                        }
                        oldDiscountNameList = discountNameList;
                        if (!rsList.isEmpty()) {
                            discountlist.add(rsList.get(0));
                        }
                        discountlist.add(discList.get(1));
                    } else {
                        discountlist = session.getDiscountRSlist();
                    }
                }
            }
        }
        pvSelectionDTO.setDiscountList(new ArrayList<>(discountlist));
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

    }

    public void loadVariables() {
        loadComparison();
        setBaseVariables(variableCategoryValue, variablesValue);
    }

    private void alignRight() {
        for (Object obj : rightHeader.getSingleColumns()) {
            resultsTable.getRightFreezeAsTable().setColumnAlignment(obj, ExtCustomTable.Align.RIGHT);
        }
    }

    public int getTabNumber() {
        return Constant.EIGHT;
    }

    public void pushUpdate(String indicator) {
        if (Constants.IndicatorConstants.INDICATOR_REFRESH_UPDATE.getConstant().equals(indicator)) {
            tradingPartnerNo = CommonLogic.getTradingPartnerLevelNo(false, projectionId);
            session.setTradingPartner(tradingPartnerNo);
        }
        if (Constants.IndicatorConstants.INDICATOR_TIME_PERIOD_CHANGED.getConstant().equals(indicator)) {
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        projectionId = session.getProjectionId();
        pvSelectionDTO.setUserId(Integer.parseInt(session.getUserId()));
        pvSelectionDTO.setSessionId(Integer.parseInt(session.getSessionId()));
        tradingPartnerNo = Utility.getTradingPartnerLevelNo(projectionId, session);
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
            map.put(HEADER_LEVEL.getConstant(), level.getValue().toString());
            map.put("Projection Period Order", projectionPeriodOrder.getValue().toString());
            map.put("From", fromDate.getValue() != null ? fromDate.getValue().toString() : StringUtils.EMPTY);
            map.put("To", toDate.getValue() != null ? toDate.getValue().toString() : StringUtils.EMPTY);
            map.put(Constant.FREQUENCY_SMALL, frequency.getValue() != null ? frequency.getValue().toString() : StringUtils.EMPTY);
            map.put("Discount Level", discountLevel.getValue() != null ? discountLevel.getValue().toString() : StringUtils.EMPTY);
            map.put("Pivot View", pivotView.getValue() != null ? pivotView.getValue().toString() : StringUtils.EMPTY);
            map.put("Variable Category", getCheckedVariableCategoryValues());
            map.put(Constant.VARIABLES, getCheckedValues());
            map.put("Comparison Basis", comparisonBasis.getValue() != null ? comparisonBasis.getValue().toString() : StringUtils.EMPTY);
            map.put(Constant.DISPLAY_FORMAT_SAVE, StringUtils.join(CommonUtil.getDisplayFormatSelectedValues(displayFormatValues), CommonUtil.COMMA));
            map.put(Constant.CUSTOMER_LEVEL_DDLB, customerlevelDdlb.getValue());
            map.put(Constant.CUSTOMER_LEVEL_VALUE, StringUtils.join(commonLogic.getFilterValues(customerFilterValues).get(SID), CommonUtil.COMMA));
            map.put(Constant.PRODUCT_LEVEL_DDLB, productlevelDdlb.getValue());
            map.put(Constant.PRODUCT_LEVEL_VALUE, StringUtils.join(commonLogic.getFilterValues(productFilterValues).get(SID), CommonUtil.COMMA));
            map.put(Constant.DEDUCTION_LEVEL_DDLB, deductionlevelDdlb.getValue());
            map.put(Constant.DEDUCTION_LEVEL_VALUE, StringUtils.join(commonLogic.getFilterValues(deductionFilterValues).get(SID), CommonUtil.COMMA));
            map.put(Constant.SALES_INCLUSION_DDLB, StringUtils.join(CommonUtil.getDisplayFormatSelectedValues(salesInclusionValues), CommonUtil.COMMA));
            map.put(Constant.DEDUCTION_INCLUSION_DDLB, StringUtils.join(CommonUtil.getDisplayFormatSelectedValues(deductionInclusionValues), CommonUtil.COMMA));
            logic.saveNMPVSelection(map, projectionId, TAB_PROJECTION_VARIANCE.getConstant());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.info("savePVSelections method ends");

    }

    public void setProjectionSelection(boolean isReset) {
        Map<Object, Object> map = CommonLogic.getNMProjectionSelection(projectionId, TAB_PROJECTION_VARIANCE.getConstant());
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

            value = map.get(HEADER_LEVEL.getConstant());
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
                    if (i == Constant.ZERO) {
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
            String variableCategoryItemValue = ChangeCustomMenuBarValueUtil.getMenuItemToDisplay(variableCategoryCustomMenuItem);
            ChangeCustomMenuBarValueUtil.setMenuItemToDisplay(variableCategoryCustomMenuBar, variableCategoryItemValue);
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
            String variableMenuItemValue = ChangeCustomMenuBarValueUtil.getMenuItemToDisplay(customMenuItem);
            ChangeCustomMenuBarValueUtil.setMenuItemToDisplay(customMenuBar, variableMenuItemValue);
            value = map.get(Constant.DISPLAY_FORMAT_SAVE);
            if (!CommonUtil.nullCheck(value)) {
                CommonUtil.setCustomMenuBarValuesInEdit(value, displayFormatValues);
            }
            String displayFormatMenuItemValue = ChangeCustomMenuBarValueUtil.getMenuItemToDisplay(displayFormatValues);
            ChangeCustomMenuBarValueUtil.setMenuItemToDisplay(displayFormatDdlb, displayFormatMenuItemValue);
            if (isReset) {
                CommonLogic.unCheckMultiSelect(customerFilterValues);
                CommonLogic.unCheckMultiSelect(productFilterValues);
                CommonLogic.unCheckMultiSelect(deductionFilterValues);
            }
            value = map.get(Constant.CUSTOMER_LEVEL_DDLB);
            customerlevelDdlb.setValue(CommonUtil.nullCheck(value) || CommonUtil.stringNullCheck(value) ? SELECT_ONE.getConstant() : Integer.parseInt(value.toString()));
            value = map.get(Constant.CUSTOMER_LEVEL_VALUE);
            if (!CommonUtil.nullCheck(value)) {
                CommonUtil.setCustomMenuBarValuesInEdit(value, customerFilterValues);
                generateCustomerToBeLoaded = commonLogic.getFilterValues(customerFilterValues).get(SID);
                pvSelectionDTO.setCustomerLevelFilter((List) generateCustomerToBeLoaded);
            }
            String customerMenuItemValue = ChangeCustomMenuBarValueUtil.getMenuItemToDisplay(customerFilterValues);
            ChangeCustomMenuBarValueUtil.setMenuItemToDisplay(customerFilterDdlb, customerMenuItemValue);
            value = map.get(Constant.PRODUCT_LEVEL_DDLB);
            productlevelDdlb.setValue(CommonUtil.nullCheck(value) || CommonUtil.stringNullCheck(value) ? SELECT_ONE.getConstant() : Integer.parseInt(value.toString()));
            value = map.get(Constant.PRODUCT_LEVEL_VALUE);
            if (!CommonUtil.nullCheck(value)) {
                CommonUtil.setCustomMenuBarValuesInEdit(value, productFilterValues);
                generateProductToBeLoaded = commonLogic.getFilterValues(productFilterValues).get(SID);
                pvSelectionDTO.setProductLevelFilter((List) generateProductToBeLoaded);
            }
            String productMenuItemValue = ChangeCustomMenuBarValueUtil.getMenuItemToDisplay(productFilterValues);
            ChangeCustomMenuBarValueUtil.setMenuItemToDisplay(productFilterDdlb, productMenuItemValue);
            value = map.get(Constant.DEDUCTION_LEVEL_DDLB);
            deductionlevelDdlb.setValue(CommonUtil.nullCheck(value) || CommonUtil.stringNullCheck(value) ? SELECT_ONE.getConstant() : Integer.parseInt(value.toString()));
            value = map.get(Constant.DEDUCTION_LEVEL_VALUE);
            if (!CommonUtil.nullCheck(value)) {
                CommonUtil.setCustomMenuBarValuesInEdit(value, deductionFilterValues);
                generateDiscountToBeLoaded = commonLogic.getFilterValues(deductionFilterValues).get(SID);
                generateDiscountNamesToBeLoaded = commonLogic.getFilterValues(deductionFilterValues).get(CAPTION);
                pvSelectionDTO.setDeductionLevelFilter((List) generateDiscountToBeLoaded);
                pvSelectionDTO.setDeductionLevelCaptions((List) generateDiscountToBeLoaded);
            }
            String deductionMenuItemValue = ChangeCustomMenuBarValueUtil.getMenuItemToDisplay(deductionFilterValues);
            ChangeCustomMenuBarValueUtil.setMenuItemToDisplay(deductionFilterDdlb, deductionMenuItemValue);
            value = map.get(Constant.SALES_INCLUSION_DDLB);
            if (!CommonUtil.nullCheck(value)) {
                CommonUtil.setCustomMenuBarValuesInEdit(value, salesInclusionValues);
            }
            String salesInclusionValue = ChangeCustomMenuBarValueUtil.getInclusionMenuItemToDisplay(salesInclusionValues);
            ChangeCustomMenuBarValueUtil.setMenuItemToDisplay(salesInclusionDdlb, salesInclusionValue);
            value = map.get(Constant.DEDUCTION_INCLUSION_DDLB);
            if (!CommonUtil.nullCheck(value)) {
                CommonUtil.setCustomMenuBarValuesInEdit(value, deductionInclusionValues);
            }
            String deductionInclusionValue = ChangeCustomMenuBarValueUtil.getInclusionMenuItemToDisplay(deductionInclusionValues);
            ChangeCustomMenuBarValueUtil.setMenuItemToDisplay(deductionInclusionDdlb, deductionInclusionValue);
        }
        if (Constant.EDIT_SMALL.equalsIgnoreCase(session.getAction()) || Constant.VIEW.equalsIgnoreCase(session.getAction())) {
            fetchDiscountsFromSave();
        }
    }

    public void getUnCheckedVariableMenuItem(CustomMenuBar.CustomMenuItem customMenuItem) {

        if (customMenuItem != null) {
            for (CustomMenuBar.CustomMenuItem string : customMenuItem.getChildren()) {
                string.setChecked(false);
            }
        }
    }

    private void security() throws PortalException, SystemException {

        final Map<String, AppPermission> functionPsHM = stplSecurity.getBusinessFunctionPermissionForNm(String.valueOf(VaadinSession.getCurrent().getAttribute("businessRoleIds")), getCommercialConstant() + "," + UISecurityUtil.PROJECTION_RESULTS);

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
            if (!pvSelectionDTO.getProjIdList().isEmpty()) {
                for (int j = 0; j < pvSelectionDTO.getProjIdList().size(); j++) {
                    comparisonBasis.addItem(j);
                    comparisonBasis.setItemCaption(j, pvSelectionDTO.getProjectionMap().get(pvSelectionDTO.getProjIdList().get(j)));
                    comparisonBasis.select(CURRENT_PROJECTION);
                }
            }
        }
    }

    public void configure() {
        if (flag) {
            try {
                configureFields();
                security();
                flag = false;
            } catch (PortalException | SystemException ex) {
                LoggerFactory.getLogger(NMProjectionVariance.class.getName()).error(StringUtils.EMPTY, ex);
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
        pvSelectionDTO.setNetSalesExFactory(false);
        pvSelectionDTO.setDiscountPerExFactory(false);
        pvSelectionDTO.setNetExFactorySales(false);
        pvSelectionDTO.setNetExFactorySalesPerExFactory(false);

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
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constant.PVVariables.NET_SALES_PER_EX_FACTORY.toString()))) {
                pvSelectionDTO.setNetSalesExFactory(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constant.PVVariables.DISCOUNT_PER_EX_FACTORY.toString()))) {
                pvSelectionDTO.setDiscountPerExFactory(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constant.PVVariables.NET_EX_FACTORY_SALES.toString()))) {
                pvSelectionDTO.setNetExFactorySales(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constant.PVVariables.NET_EX_FACTORY_SALES_PER_EX_FACTORY.toString()))) {
                pvSelectionDTO.setNetExFactorySalesPerExFactory(true);
            }
        }
    }

    private void excelForCommercial() {
        try {
            configureTable();
            configureExcelTable();
            getExcelProcedureInput();
            excelLogic.getPVData();
            excelParentRecords.clear();

            if (pvSelectionDTO.getLevel().equals(Constants.LabelConstants.TOTAL.toString())) {

                List<ProjectionVarianceDTO> varibaleList = resultMap.get(Constants.LabelConstants.TOTAL.toString());

                if (varibaleList != null) {
                    for (Iterator<ProjectionVarianceDTO> it1 = varibaleList.listIterator(); it1.hasNext();) {
                        ProjectionVarianceDTO itemId = it1.next();
                        it1.remove();
                        resultExcelContainer.addBean(itemId);
                        resultExcelContainer.setChildrenAllowed(itemId, false);
                        if (!Constants.LabelConstants.TOTAL_DISCOUNT.toString().equals(pvSelectionDTO.getDiscountLevel())) {
                            if (itemId.getGroup().startsWith(Constants.LabelConstants.DISCOUNT.toString())
                                    || itemId.getGroup().startsWith(Constant.DISCOUNT_PER_OF_EX_FACTORY_HEADER)
                                    || itemId.getGroup().startsWith(PVVariables.VAR_RPU.toString())) {
                                if (itemId.getGroup().startsWith("Discount $ Value")) {
                                    resultExcelContainer.setChildrenAllowed(itemId, true);
                                    List<ProjectionVarianceDTO> discountDollarList = resultMap.get("D$value");
                                    if (discountDollarList != null) {
                                        for (Iterator<ProjectionVarianceDTO> it2 = discountDollarList.listIterator(); it2.hasNext();) {
                                            ProjectionVarianceDTO childItem = it2.next();
                                            it2.remove();
                                            resultExcelContainer.addBean(childItem);
                                            resultExcelContainer.setChildrenAllowed(childItem, false);
                                            resultExcelContainer.setParent(childItem, itemId);
                                        }

                                    }
                                }
                                if (itemId.getGroup().startsWith("Discount $ Variance")) {
                                    resultExcelContainer.setChildrenAllowed(itemId, true);
                                    List<ProjectionVarianceDTO> discountvarianceList = resultMap.get("D$variance");
                                    if (discountvarianceList != null) {
                                        for (Iterator<ProjectionVarianceDTO> it3 = discountvarianceList.listIterator(); it3.hasNext();) {
                                            ProjectionVarianceDTO childItem = it3.next();
                                            it3.remove();
                                            resultExcelContainer.addBean(childItem);
                                            resultExcelContainer.setChildrenAllowed(childItem, false);
                                            resultExcelContainer.setParent(childItem, itemId);
                                        }
                                    }
                                }
                                if (itemId.getGroup().startsWith("Discount $ %Change")) {
                                    resultExcelContainer.setChildrenAllowed(itemId, true);
                                    List<ProjectionVarianceDTO> discountDollarList = resultMap.get("D$change");
                                    if (discountDollarList != null) {
                                        for (Iterator<ProjectionVarianceDTO> it4 = discountDollarList.listIterator(); it4.hasNext();) {
                                            ProjectionVarianceDTO childItem = it4.next();
                                            it4.remove();
                                            resultExcelContainer.addBean(childItem);
                                            resultExcelContainer.setChildrenAllowed(childItem, false);
                                            resultExcelContainer.setParent(childItem, itemId);
                                        }
                                    }
                                }
                                if (itemId.getGroup().startsWith("Discount % Value")) {
                                    resultExcelContainer.setChildrenAllowed(itemId, true);
                                    List<ProjectionVarianceDTO> discountDollarList = resultMap.get("D%value");
                                    if (discountDollarList != null) {
                                        for (Iterator<ProjectionVarianceDTO> it5 = discountDollarList.listIterator(); it5.hasNext();) {
                                            ProjectionVarianceDTO childItem = it5.next();
                                            it5.remove();
                                            resultExcelContainer.addBean(childItem);
                                            resultExcelContainer.setChildrenAllowed(childItem, false);
                                            resultExcelContainer.setParent(childItem, itemId);
                                        }
                                    }
                                }
                                if (itemId.getGroup().startsWith("Discount % Variance")) {
                                    resultExcelContainer.setChildrenAllowed(itemId, true);
                                    List<ProjectionVarianceDTO> discountDollarList = resultMap.get("D%variance");
                                    if (discountDollarList != null) {
                                        for (Iterator<ProjectionVarianceDTO> it6 = discountDollarList.listIterator(); it6.hasNext();) {
                                            ProjectionVarianceDTO childItem = it6.next();
                                            it6.remove();
                                            resultExcelContainer.addBean(childItem);
                                            resultExcelContainer.setChildrenAllowed(childItem, false);
                                            resultExcelContainer.setParent(childItem, itemId);
                                        }
                                    }
                                }
                                if (itemId.getGroup().startsWith("Discount % %Change")) {
                                    resultExcelContainer.setChildrenAllowed(itemId, true);
                                    List<ProjectionVarianceDTO> discountDollarList = resultMap.get("D%change");
                                    if (discountDollarList != null) {
                                        for (Iterator<ProjectionVarianceDTO> it7 = discountDollarList.listIterator(); it7.hasNext();) {
                                            ProjectionVarianceDTO childItem = it7.next();
                                            it7.remove();
                                            resultExcelContainer.addBean(childItem);
                                            resultExcelContainer.setChildrenAllowed(childItem, false);
                                            resultExcelContainer.setParent(childItem, itemId);
                                        }
                                    }
                                }

                                if (itemId.getGroup().startsWith("RPU Value")) {
                                    resultExcelContainer.setChildrenAllowed(itemId, true);
                                    List<ProjectionVarianceDTO> discountDollarList = resultMap.get("RPU-value");
                                    if (discountDollarList != null) {
                                        for (Iterator<ProjectionVarianceDTO> it8 = discountDollarList.listIterator(); it8.hasNext();) {
                                            ProjectionVarianceDTO childItem = it8.next();
                                            it8.remove();
                                            resultExcelContainer.addBean(childItem);
                                            resultExcelContainer.setChildrenAllowed(childItem, false);
                                            resultExcelContainer.setParent(childItem, itemId);
                                        }
                                    }
                                }
                                if (itemId.getGroup().startsWith("RPU Variance")) {
                                    resultExcelContainer.setChildrenAllowed(itemId, true);
                                    List<ProjectionVarianceDTO> discountDollarList = resultMap.get("RPU-variance");
                                    if (discountDollarList != null) {
                                        for (Iterator<ProjectionVarianceDTO> it9 = discountDollarList.listIterator(); it9.hasNext();) {
                                            ProjectionVarianceDTO childItem = it9.next();
                                            it9.remove();
                                            resultExcelContainer.addBean(childItem);
                                            resultExcelContainer.setChildrenAllowed(childItem, false);
                                            resultExcelContainer.setParent(childItem, itemId);
                                        }
                                    }
                                }
                                if (itemId.getGroup().startsWith("RPU %Change")) {
                                    resultExcelContainer.setChildrenAllowed(itemId, true);
                                    List<ProjectionVarianceDTO> discountDollarList = resultMap.get("RPU-change");
                                    if (discountDollarList != null) {
                                        for (Iterator<ProjectionVarianceDTO> it10 = discountDollarList.listIterator(); it10.hasNext();) {
                                            ProjectionVarianceDTO childItem = it10.next();
                                            it10.remove();
                                            resultExcelContainer.addBean(childItem);
                                            resultExcelContainer.setChildrenAllowed(childItem, false);
                                            resultExcelContainer.setParent(childItem, itemId);
                                        }
                                    }
                                }

                                if (itemId.getGroup().startsWith("Discount % of Ex-Factory Value")) {
                                    resultExcelContainer.setChildrenAllowed(itemId, true);
                                    List<ProjectionVarianceDTO> discountDollarList = resultMap.get("Dis%Exvalue");
                                    if (discountDollarList != null) {
                                        for (Iterator<ProjectionVarianceDTO> it8 = discountDollarList.listIterator(); it8.hasNext();) {
                                            ProjectionVarianceDTO childItem = it8.next();
                                            it8.remove();
                                            resultExcelContainer.addBean(childItem);
                                            resultExcelContainer.setChildrenAllowed(childItem, false);
                                            resultExcelContainer.setParent(childItem, itemId);
                                        }
                                    }
                                }
                                if (itemId.getGroup().startsWith("Discount % of Ex-Factory Variance")) {
                                    resultExcelContainer.setChildrenAllowed(itemId, true);
                                    List<ProjectionVarianceDTO> discountDollarList = resultMap.get("Dis%Exvariance");
                                    if (discountDollarList != null) {
                                        for (Iterator<ProjectionVarianceDTO> it9 = discountDollarList.listIterator(); it9.hasNext();) {
                                            ProjectionVarianceDTO childItem = it9.next();
                                            it9.remove();
                                            resultExcelContainer.addBean(childItem);
                                            resultExcelContainer.setChildrenAllowed(childItem, false);
                                            resultExcelContainer.setParent(childItem, itemId);
                                        }
                                    }
                                }
                                if (itemId.getGroup().startsWith("Discount % of Ex-Factory %Change")) {
                                    resultExcelContainer.setChildrenAllowed(itemId, true);
                                    List<ProjectionVarianceDTO> discountDollarList = resultMap.get("Dis%Exchange");
                                    if (discountDollarList != null) {
                                        for (Iterator<ProjectionVarianceDTO> it10 = discountDollarList.listIterator(); it10.hasNext();) {
                                            ProjectionVarianceDTO childItem = it10.next();
                                            it10.remove();
                                            resultExcelContainer.addBean(childItem);
                                            resultExcelContainer.setChildrenAllowed(childItem, false);
                                            resultExcelContainer.setParent(childItem, itemId);
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
            } else {
                if (!pvSelectionDTO.isIsCustomHierarchy()) {
                    Collections.sort(hierarchyKeys);
                }
                boolean tpAsParent = true;
                List<ProjectionVarianceDTO> varibaleList = new ArrayList<>();
                for (Iterator<String> it = hierarchyKeys.listIterator(); it.hasNext();) {
                    String key = it.next();
                    it.remove();

                    if (resultMap.containsKey(key)) {
                        varibaleList = resultMap.get(key);
                        resultMap.remove(key);
                    }

                    Object parentItemId = null;
                    if (tradingPartnerKeys.contains(key)) {
                        int index = 0;
                        for (Iterator<ProjectionVarianceDTO> it1 = varibaleList.listIterator(); it1.hasNext();) {
                            ProjectionVarianceDTO itemId = it1.next();
                            it1.remove();
                            resultExcelContainer.addBean(itemId);
                            if (index++ == 0) {
                                String parentKey = StringUtils.EMPTY;
                                if (!pvSelectionDTO.isIsCustomHierarchy()) {
                                    parentKey = key.substring(0, key.lastIndexOf('.'));
                                } else {
                                    parentKey = getParentKeyforCustom(itemId, key, parentKey);
                                }
                                if (parentKey.lastIndexOf('.') >= 0) {
                                    parentKey = parentKey.substring(0, parentKey.lastIndexOf('.') + 1);
                                }
                                String groupParentKey;
                                if (tpAsParent) {
                                    groupParentKey = parentKey + pvSelectionDTO.getGroupFilter();
                                    parentItemId = excelParentRecords.get(parentKey);
                                } else {
                                    groupParentKey = pvSelectionDTO.getGroupFilter();
                                    parentItemId = excelParentRecords.get(parentKey);
                                }
                                Object gropuParentItemId = excelParentRecords.get(groupParentKey);

                                if (gropuParentItemId == null) {
                                    ProjectionVarianceDTO tpGroup = new ProjectionVarianceDTO() {
                                        {
                                            setGroup(pvSelectionDTO.getGroupFilter());
                                            setDfLevelNumber(pvSelectionDTO.getGroupFilter());
                                            setDfLevelName(pvSelectionDTO.getGroupFilter());
                                        }
                                    };
                                    resultExcelContainer.addBean(tpGroup);
                                    gropuParentItemId = tpGroup;
                                    resultExcelContainer.setParent(gropuParentItemId, parentItemId);
                                }
                                if (parentItemId != null) {
                                    resultExcelContainer.setParent(itemId, gropuParentItemId);
                                } else {
                                    tpAsParent = false;
                                    parentItemId = gropuParentItemId;
                                }
                                excelParentRecords.put(key, itemId);
                                resultExcelContainer.setParent(itemId, gropuParentItemId);
                                resultExcelContainer.setParent(gropuParentItemId, parentItemId);
                                if (tpAsParent) {
                                    excelParentRecords.put(parentKey + pvSelectionDTO.getGroupFilter(), gropuParentItemId);
                                } else {
                                    excelParentRecords.put(pvSelectionDTO.getGroupFilter(), gropuParentItemId);
                                }
                                parentItemId = itemId;
                                resultExcelContainer.setChildrenAllowed(itemId, true);
                            } else {
                                resultExcelContainer.setChildrenAllowed(itemId, false);
                                resultExcelContainer.setParent(itemId, parentItemId);

                                if ((!Constants.LabelConstants.TOTAL_DISCOUNT.toString().equals(pvSelectionDTO.getDiscountLevel())) && (itemId.getGroup().startsWith(Constants.LabelConstants.DISCOUNT.toString()) || itemId.getGroup().startsWith(PVVariables.VAR_RPU.toString()) || itemId.getGroup().startsWith(PVVariables.DISCOUNT_PER_EX_FACTORY.toString()))) {
                                    resultExcelContainer.setChildrenAllowed(itemId, true);
                                    excelParentRecords.put(key + itemId.getGroup(), itemId);

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
                                String parentKey = StringUtils.EMPTY;
                                if (!pvSelectionDTO.isIsCustomHierarchy()) {
                                    parentKey = key.substring(0, key.lastIndexOf('.'));
                                } else {
                                    parentKey = getParentKeyforCustom(itemId, key, parentKey);
                                }
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
                                if ((!Constants.LabelConstants.TOTAL_DISCOUNT.toString().equals(pvSelectionDTO.getDiscountLevel())) && (itemId.getGroup().startsWith(Constants.LabelConstants.DISCOUNT.toString()) || itemId.getGroup().startsWith(PVVariables.VAR_RPU.toString()) || itemId.getGroup().startsWith(PVVariables.DISCOUNT_PER_EX_FACTORY.toString()))) {
                                    resultExcelContainer.setChildrenAllowed(itemId, true);
                                    excelParentRecords.put(key + itemId.getGroup(), itemId);
                                }
                            }
                        }
                    }
                }
                for (ListIterator<String> iterator = discountKeys.listIterator(); iterator.hasNext();) {
                    String newKey = iterator.next();
                    iterator.remove();

                    if (resultMap.containsKey(newKey)) {
                        varibaleList = resultMap.get(newKey);
                        resultMap.remove(newKey);
                    }
                    String key;
                    if (newKey.contains("@")) {
                        key = (newKey.split("\\@"))[1];
                    } else {
                        key = newKey;
                    }
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

            excelParentRecords.clear();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    public void getExcelProcedureInput() {

        String splitarr[] = (Constant.ALL_SALES_GROUP1.equalsIgnoreCase(pvSelectionDTO.getGroupFilter())
                || Constant.ALL_DISCOUNT_GROUP1.equalsIgnoreCase(pvSelectionDTO.getGroupFilter())
                || Constant.ALL_GROUP.equalsIgnoreCase(pvSelectionDTO.getGroupFilter()))
                ? pvSelectionDTO.getGroupFilter().split(" ") : pvSelectionDTO.getGroupFilter().split("-");
        String groupFilter = (Constant.ALL_SALES_GROUP1.equalsIgnoreCase(pvSelectionDTO.getGroupFilter()) || Constant.ALL_DISCOUNT_GROUP1.equalsIgnoreCase(pvSelectionDTO.getGroupFilter()) || Constant.ALL_GROUP.equalsIgnoreCase(pvSelectionDTO.getGroupFilter())) ? splitarr[1] : splitarr[0];
        String groupFilterValue = (Constant.ALL_SALES_GROUP1.equalsIgnoreCase(pvSelectionDTO.getGroupFilter()) || Constant.ALL_DISCOUNT_GROUP1.equalsIgnoreCase(pvSelectionDTO.getGroupFilter()) || Constant.ALL_GROUP.equalsIgnoreCase(pvSelectionDTO.getGroupFilter())) ? "All" : splitarr[1];
        String discountLevelValue = Constants.LabelConstants.TOTAL_DISCOUNT.toString().equalsIgnoreCase(pvSelectionDTO.getDiscountLevel()) ? "Program" : pvSelectionDTO.getDiscountLevel();

        //User Id
        parameterDto.setUserId(getSessionDTO().getUserId());
        //Session Id
        parameterDto.setSessionId(getSessionDTO().getSessionId());
        //Discount Level
        parameterDto.setDiscountLevel(discountLevelValue);
        //Frequency Value
        parameterDto.setFrequency(pvSelectionDTO.getFrequencyDivision() == 1 ? "A" : pvSelectionDTO.getFrequencyDivision() == NumericConstants.TWO ? "S" : pvSelectionDTO.getFrequencyDivision() == NumericConstants.FOUR ? "Q" : "M");
        //Current Hierarchy Indicator Value
        parameterDto.setViewIndicator(pvSelectionDTO.isIsCustomHierarchy() ? "D" : pvSelectionDTO.getHierarchyIndicator());
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
        parameterDto.setPirorProjectionIds(pvSelectionDTO.getProjIdList());
        parameterDto.setView(pivotView.getValue().toString());
    }

    public boolean compareDiscountList(List<String> discountNameList, List<String> oldDiscountList) {
        boolean matched = false;
        if (discountNameList != null && oldDiscountList != null && (oldDiscountList.size() == discountNameList.size())) {
            oldDiscountList.removeAll(discountNameList);
            if (oldDiscountList.isEmpty()) {
                matched = true;
            } else {
                matched = false;
            }
        }
        return matched;
    }

    private String getParentKeyforCustom(ProjectionVarianceDTO itemId, String key, String parentKey) {
        if (itemId.getParentHierarchyNo() == null) {
            parentKey = key.substring(0, key.lastIndexOf('.'));
        } else {
            parentKey = itemId.getParentHierarchyNo();
            if (pvSelectionDTO.isIsCustomHierarchy()) {
                String var;
                if (parentKey.contains("~")) {
                    String[] str = parentKey.split("~");
                    var = str[str.length - 1] + "$";
                    parentKey = var + parentKey.substring(0, parentKey.lastIndexOf('~'));
                } else {
                    parentKey = key.substring(key.lastIndexOf('$') + 1);
                }
            } else if (parentKey.contains("~")) {
                parentKey = parentKey.substring(parentKey.lastIndexOf('~') + 1);
                if (!pvSelectionDTO.isIsCustomHierarchy() || !Constants.LabelConstants.PERIOD.toString().equalsIgnoreCase(pvSelectionDTO.getPivotView())) {
                    parentKey = parentKey.substring(parentKey.indexOf('-') + 1);
                }
            }
        }
        return parentKey;
    }

    private void loadDisplayFormatDdlb() throws IllegalStateException {
        List<Object[]> displayFormatFilter = new ArrayList<>();
        displayFormatDdlb.removeSubMenuCloseListener(displayFormatListener);
        displayFormatFilter.addAll(commonLogic.displayFormatValues());
        displayFormatDdlb.removeItems();
        displayFormatValues = displayFormatDdlb.addItem(SELECT_VALUES_LABEL, null);
        commonLogic.loadDisplayFormat(displayFormatFilter, displayFormatValues);
        displayFormatDdlb.setScrollable(true);
        String displayFormatMenuItemValue = ChangeCustomMenuBarValueUtil.getInclusionMenuItemToDisplay(displayFormatValues);
        ChangeCustomMenuBarValueUtil.setMenuItemToDisplay(displayFormatDdlb, displayFormatMenuItemValue);
        displayFormatDdlb.addSubMenuCloseListener(displayFormatListener);
    }
    public static final String SELECT_VALUES_LABEL = "-Select Values-";

    private void loadProductLevel() {

        int hierarchyLevelNo = isInteger(session.getProductLevelNumber()) ? Integer.parseInt(session.getProductLevelNumber()) : 0;
        currentHierarchy = CommonLogic.getProductHierarchy(session.getProjectionId(), hierarchyLevelNo, session.getProdRelationshipBuilderSid());
        Utility.loadDdlbForLevelFilterOption(productlevelDdlb, currentHierarchy, StringUtils.EMPTY);
        productlevelDdlb.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                generateProductToBeLoaded = Collections.emptyList();
                if (event.getProperty().getValue() != null) {
                    String productlevelDdlbValue = String.valueOf(event.getProperty().getValue());
                    productlevelDdlbValue = Constant.NULL.equals(productlevelDdlbValue) ? StringUtils.EMPTY : productlevelDdlbValue;
                    loadProductLevelFilter(productlevelDdlbValue);
                } else {
                    loadProductLevelFilter(StringUtils.EMPTY);
                }
            }
        });
    }

    private void loadDedutionLevel() {
        List<String[]> deductionLevel = CommonLogic.getDeductionLevel(session.getProjectionId());
        Utility.loadDdlbForDeduction(deductionlevelDdlb, deductionLevel);
        deductionlevelDdlb.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty().getValue() != null) {
                    String deductionLevelDdlbValue = String.valueOf(event.getProperty().getValue());
                    pvSelectionDTO.setSelectedDeductionLevelName(deductionlevelDdlb.getItemCaption(deductionlevelDdlb.getValue()));
                    deductionLevelDdlbValue = Constant.NULL.equals(deductionLevelDdlbValue) ? StringUtils.EMPTY : deductionLevelDdlbValue;
                    loadDeductionLevelFilter(deductionLevelDdlbValue);
                } else {
                    loadDeductionLevelFilter(StringUtils.EMPTY);
                }
            }
        });
    }

    private void loadProductLevelFilter(String levelNo) {
        List<Object[]> productLevelFilter = new ArrayList<>();

        productFilterDdlb.removeSubMenuCloseListener(productlistener);

        productFilterDdlb.removeItems();
        productFilterValues = productFilterDdlb.addItem(SELECT_LEVEL_LABEL, null);

        if (!levelNo.isEmpty()) {
            productLevelFilter.add(0, new Object[]{0, SELECT_ALL});
            productLevelFilter.addAll(commonLogic.getProductLevelValues(session.getProjectionId(), levelNo, pvSelectionDTO, generateCustomerToBeLoaded, generateDiscountToBeLoaded, String.valueOf(session.getProductRelationVersion())));
            CommonLogic.loadCustomMenuBar(productLevelFilter, productFilterValues);
        }

        productFilterDdlb.setScrollable(true);
        productFilterDdlb.setPageLength(NumericConstants.TEN);
        CommonLogic.loadMenuBar((List) generateProductToBeLoaded, productFilterValues);
        String productMenuItemValue = ChangeCustomMenuBarValueUtil.getMenuItemToDisplay(productFilterValues);
        ChangeCustomMenuBarValueUtil.setMenuItemToDisplay(productFilterDdlb, productMenuItemValue);
        productFilterDdlb.addSubMenuCloseListener(productlistener);
    }
    public static final String SELECT_LEVEL_LABEL = "-Select Level-";

    private void loadDeductionLevelFilter(String levelNo) {
        List<Object[]> deductionLevelFilter = new ArrayList<>();
        if (levelNo.isEmpty()) {
            generateDiscountToBeLoaded.clear();
        }
        deductionFilterDdlb.removeSubMenuCloseListener(deductionlistener);
        deductionFilterDdlb.removeItems();
        deductionFilterValues = deductionFilterDdlb.addItem(SELECT_LEVEL_LABEL, null);

        if (!levelNo.isEmpty()) {
            deductionLevelFilter.add(0, new Object[]{0, SELECT_ALL});
            deductionLevelFilter.addAll(commonLogic.getDeductionLevelValues(session.getProjectionId(), levelNo, pvSelectionDTO, generateProductToBeLoaded, generateCustomerToBeLoaded));
            if (CommonUtil.isValueEligibleForLoading() && Constant.TEN_STRING.equals(levelNo)) {
                CommonLogic.loadCustomMenuBarFoScheduleID(deductionLevelFilter, deductionFilterValues);
            } else {
                CommonLogic.loadCustomMenuBar(deductionLevelFilter, deductionFilterValues);
            }
        }

        deductionFilterDdlb.setScrollable(true);
        deductionFilterDdlb.setPageLength(NumericConstants.TEN);
        CommonLogic.loadMenuBar((List) generateDiscountToBeLoaded, deductionFilterValues);
        String deductionMenuItemValue = ChangeCustomMenuBarValueUtil.getMenuItemToDisplay(deductionFilterValues);
        ChangeCustomMenuBarValueUtil.setMenuItemToDisplay(deductionFilterDdlb, deductionMenuItemValue);
        deductionFilterDdlb.addSubMenuCloseListener(deductionlistener);
    }
    public static final String SELECT_ALL = "Select All";

    private void loadCustomerLevel() {
        int hierarchyNo = isInteger(session.getCustomerLevelNumber()) ? Integer.parseInt(session.getCustomerLevelNumber()) : 0;
        currentHierarchy = CommonLogic.getCustomerHierarchy(session.getProjectionId(), hierarchyNo, session.getCustRelationshipBuilderSid());
        Utility.loadDdlbForLevelFilterOption(customerlevelDdlb, currentHierarchy, StringUtils.EMPTY);

        customerlevelDdlb.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                generateCustomerToBeLoaded = Collections.emptyList();
                if (event.getProperty().getValue() != null) {
                    String customerlevelDdlbValue = String.valueOf(customerlevelDdlb.getValue());
                    customerlevelDdlbValue = Constant.NULL.equals(customerlevelDdlbValue) ? StringUtils.EMPTY : customerlevelDdlbValue;
                    loadCustomerLevelFilter(customerlevelDdlbValue);
                } else {
                    loadCustomerLevelFilter(StringUtils.EMPTY);
                }
            }
        });
    }

    private void loadCustomerLevelFilter(String levelNo) {
        List<Object[]> customerLevelFilter = new ArrayList<>();

        customerFilterDdlb.removeSubMenuCloseListener(customerlistener);
        customerFilterDdlb.removeItems();
        customerFilterValues = customerFilterDdlb.addItem(SELECT_LEVEL_LABEL, null);
        if (!levelNo.isEmpty()) {
            customerLevelFilter.add(0, new Object[]{0, SELECT_ALL});
            customerLevelFilter.addAll(commonLogic.getCustomerLevelValues(session.getProjectionId(), levelNo, pvSelectionDTO, generateProductToBeLoaded, generateDiscountToBeLoaded, String.valueOf(session.getCustomerRelationVersion())));
            CommonLogic.loadCustomMenuBar(customerLevelFilter, customerFilterValues);
        }
        customerFilterDdlb.setScrollable(true);
        customerFilterDdlb.setPageLength(NumericConstants.TEN);
        CommonLogic.loadMenuBar((List) generateCustomerToBeLoaded, customerFilterValues);
        String customerMenuItemValue = ChangeCustomMenuBarValueUtil.getMenuItemToDisplay(customerFilterValues);
        ChangeCustomMenuBarValueUtil.setMenuItemToDisplay(customerFilterDdlb, customerMenuItemValue);
        customerFilterDdlb.addSubMenuCloseListener(customerlistener);
    }

    private void loadDeductionInclusion() throws IllegalStateException {
        String[] deductionValues = {"Yes", "No"};
        deductionInclusionDdlb.removeSubMenuCloseListener(deductionInclusionListener);
        deductionInclusionDdlb.removeItems();
        deductionInclusionValues = deductionInclusionDdlb.addItem(SELECT_VALUES_LABEL, null);
        CustomMenuBar.CustomMenuItem[] deductionInclusionCustomItem = new CustomMenuBar.CustomMenuItem[deductionValues.length];
        for (int i = 0; i < deductionValues.length; i++) {
            MenuItemDTO dto = new MenuItemDTO(i, deductionValues[i].trim());
            deductionInclusionCustomItem[i] = deductionInclusionValues.addItem(dto, null);
            deductionInclusionCustomItem[i].setCheckable(true);
            deductionInclusionCustomItem[i].setItemClickable(true);
            deductionInclusionCustomItem[i].setItemClickNotClosable(true);
        }
        deductionInclusionDdlb.addSubMenuCloseListener(deductionInclusionListener);
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

    protected List getCheckedDeductionInclusionValues() {
        List<String> results = new ArrayList<>();
        if (deductionInclusionValues != null && deductionInclusionValues.getSize() > 0) {
            List<CustomMenuBar.CustomMenuItem> items = deductionInclusionValues.getChildren();
            for (Iterator<CustomMenuBar.CustomMenuItem> it = items.iterator(); it.hasNext();) {
                CustomMenuBar.CustomMenuItem customMenuItem1 = it.next();
                if (customMenuItem1.isChecked()) {
                    results.add(customMenuItem1.getMenuItem().getCaption());
                }
            }
        }
        return results;
    }

    private void loadSalesInclusion() throws IllegalStateException {
        String[] variablesalesInclusion = {"Yes", "No"};
        salesInclusionDdlb.removeSubMenuCloseListener(salesInclusionListener);
        salesInclusionDdlb.removeItems();
        salesInclusionValues = salesInclusionDdlb.addItem(SELECT_VALUES_LABEL, null);
        CustomMenuBar.CustomMenuItem[] salesInclusionCustomItem = new CustomMenuBar.CustomMenuItem[variablesalesInclusion.length];
        for (int i = 0; i < variablesalesInclusion.length; i++) {
            MenuItemDTO dto = new MenuItemDTO(i, variablesalesInclusion[i].trim());
            salesInclusionCustomItem[i] = salesInclusionValues.addItem(dto, null);
            salesInclusionCustomItem[i].setCheckable(true);
            salesInclusionCustomItem[i].setItemClickable(true);
            salesInclusionCustomItem[i].setItemClickNotClosable(true);

        }
        salesInclusionDdlb.addSubMenuCloseListener(salesInclusionListener);
    }

    public List<String> getComparisonProjName() {
        return comparisonProjName;
    }

    public void setComparisonProjName(List<String> comparisonProjName) {
        this.comparisonProjName = comparisonProjName;
    }

}
