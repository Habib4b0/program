
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.projectionresults.form;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.cff.abstractCff.ForecastProjectionResults;
import com.stpl.app.cff.dto.SessionDTO;
import com.stpl.app.cff.lazyLoad.ResultsTableLogic;
import com.stpl.app.cff.logic.CommonLogic;
import com.stpl.app.cff.security.StplSecurity;
import com.stpl.app.cff.ui.ConsolidatedFinancialForecastUI;
import com.stpl.app.cff.ui.dataSelection.dto.ForecastDTO;
import com.stpl.app.cff.ui.projectionresults.dto.ProjectionResultsDTO;
import com.stpl.app.cff.ui.projectionresults.logic.PRExcelLogic;
import com.stpl.app.cff.ui.projectionresults.logic.ProjectionResultsLogic;
import static com.stpl.app.cff.util.CommonUtils.BOTH;
import com.stpl.app.cff.util.Constants;
import static com.stpl.app.cff.util.Constants.CommonConstants.SELECT_ONE;
import static com.stpl.app.cff.util.Constants.CommonConstantsForChannels.ACTION_EDIT;
import static com.stpl.app.cff.util.Constants.CommonConstantsForChannels.ACTION_VIEW;
import static com.stpl.app.cff.util.Constants.CommonConstantsForChannels.CUSTOM;
import static com.stpl.app.cff.util.Constants.VARIABLE_LABEL;
import com.stpl.app.cff.util.ConstantsUtil;
import static com.stpl.app.cff.util.ConstantsUtil.MONTHLY;
import static com.stpl.app.cff.util.ConstantsUtil.QUARTERLY;
import com.stpl.app.cff.util.DataSelectionUtil;
import com.stpl.app.cff.util.HeaderUtils;
import com.stpl.app.cff.util.StringConstantsUtil;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.vaadin.server.VaadinSession;
import com.vaadin.v7.data.Property;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTreeTable;
import org.jboss.logging.Logger;

/**
 *
 * @author mohamed.hameed
 */
public class ProjectionResults extends ForecastProjectionResults {

    public static final Logger LOGGER = Logger.getLogger(ProjectionResults.class);
    private final SessionDTO session;
    private List<List<String>> discountlist = new ArrayList<>();
    private final ProjectionResultsLogic projResLogic = new ProjectionResultsLogic();
    private final Map<String, List<ProjectionResultsDTO>> resultMap = new HashMap();

    private final Map<String, Object> excelParentRecords = new HashMap();
    private final List<String> hierarchyKeys = new ArrayList();
    private final List<String> tradingPartnerKeys = new ArrayList();
    private final List<String> discountKeys = new ArrayList();
    private final Map<String, List<List<ProjectionResultsDTO>>> discountMap = new HashMap<>();
    private final PRExcelLogic excelLogic = new PRExcelLogic(resultMap, projectionSelectionDTO, hierarchyKeys, tradingPartnerKeys, discountKeys, discountMap);
    private final DataSelectionDTO dataSelectionDTO;

    public ProjectionResults(final SessionDTO session, final String screenName, final DataSelectionDTO dataSelectionDTO) throws PortalException, SystemException {
        super(session, screenName);
        this.session = session;
        this.dataSelectionDTO = dataSelectionDTO;
        tablePanel.setCaption(pivotViewOpg.getValue().toString() + " Pivot View");
        configureOnNonMandated();
    }

    private void configureOnNonMandated() throws PortalException, SystemException {
        configurePermission();
        loadFrequencyDdlb();
        historyDdlb.setValue(NumericConstants.FOUR);
        initializeResultTable();
        if (loadProjectionSelection()) {
            configureResultTable();
        }
        addResultTable();
        groupDdlb.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                groupChange(true);
            }
        });
        if (ACTION_EDIT.getConstant().equalsIgnoreCase(sessionDTO.getAction()) || ACTION_VIEW.getConstant().equalsIgnoreCase(sessionDTO.getAction())) {
            loadOnEdit();
        }
       }

    @Override
    protected void generateButtonLogic() {
        generated = true;
        firstGenerated = true;
        int pageNo = tableLogic.getItemsPerPage();
        if (loadProjectionSelection()) {
            tableVerticalLayout.removeAllComponents();
            tableLogic = new ResultsTableLogic();
            periodTableId = new FreezePagedTreeTable(tableLogic);
            initializeResultTable();
            configureResultTable();
            addResultTable();
            tableLogic.setItemsPerPage(pageNo);
            generateLogic();
        } else {
            MessageBox.showPlain(Icon.INFO, "Error", "Please select a frequency and/or history and try again.", ButtonId.OK);
        }
        generated = false;
    }

    /**
     * Generate Logic for Commercial Projection Results.
     */
    @Override
    public void generateLogic() {
        levelFilterDdlb.removeValueChangeListener(levelFilterChangeOption);
        loadLevelAndFilterValue();
        projectionSelectionDTO.setIsFilter(false);
        loadResultTable(0, "");
        levelFilterDdlb.addValueChangeListener(levelFilterChangeOption);
    }

    @SuppressWarnings("serial")
    private void loadResultTable(int levelNo, String hierarchyNo) {
        projectionSelectionDTO.setFilterLevelNo(levelNo);
        projectionSelectionDTO.setFilterHierarchyNo(hierarchyNo);
        projectionSelectionDTO.setProductHierarchyNo("");
        projectionSelectionDTO.setCustomerHierarchyNo("");
        projectionSelectionDTO.clearNonFetchableIndex();
        periodTableId.getLeftFreezeAsTable().setDoubleHeaderColumnHeader(leftHeader.getDoubleColumns().get(0), projectionSelectionDTO.getView());
        tableLogic.setProjectionResultsData(projectionSelectionDTO);
        projectionSelectionDTO.setProjectionId(sessionDTO.getProjectionId());
    }

    public boolean loadProjectionSelection() {
        boolean toRet = false;
        Object freq = frequencyDdlb.getValue();
        boolean toFreq = false;
        int historyNum = 0;
        if (freq != null && !SELECT_ONE.getConstant().equals(freq.toString())) {
            toFreq = true;
            projectionSelectionDTO.setFrequency(freq.toString());
        }
        Object hist = historyDdlb.getValue();
        boolean toHist = false;
        if (hist != null && !SELECT_ONE.getConstant().equals(hist.toString())) {
            toHist = true;
            projectionSelectionDTO.setHistory(hist.toString());
            String[] his = historyDdlb.getValue().toString().split(" ");
            historyNum = Integer.valueOf(his[0]);
        }
        if (toFreq && toHist) {
            toRet = true;
            projectionSelectionDTO.setSessionDTO(sessionDTO);
            projectionId = sessionDTO.getProjectionId();
            projectionSelectionDTO.setUserId(Integer.valueOf(sessionDTO.getUserId()));
            projectionSelectionDTO.setSessionId(Integer.valueOf(sessionDTO.getSessionId()));
            projectionSelectionDTO.setCustRelationshipBuilderSid(sessionDTO.getCustRelationshipBuilderSid());
            projectionSelectionDTO.setProdRelationshipBuilderSid(sessionDTO.getProdRelationshipBuilderSid());
            projectionSelectionDTO.setPpa(sessionDTO.isPpaIndicator());
            discountlist = new ArrayList<>();
            projectionSelectionDTO.setForecastDTO(sessionDTO.getForecastDTO());
            projectionSelectionDTO.setHistoryNum(historyNum);
            projectionSelectionDTO.setProjectionNum(NumericConstants.TWO);
            projectionSelectionDTO.setProjection("" + Integer.toString(projectionSelectionDTO.getProjectionNum()));

            projectionSelectionDTO.setActualsOrProjections(actualOrProjectionsOpg.getValue().toString());

            projectionSelectionDTO.setProjectionId(sessionDTO.getProjectionId());
            projectionSelectionDTO.setSalesOrUnit(salesOrUnitsOpg.getValue().toString());
            projectionSelectionDTO.setProjectionOrder(periodOrderOpg.getValue().toString());
            projectionSelectionDTO.setPivotView(pivotViewOpg.getValue().toString());
            tablePanel.setCaption(pivotViewOpg.getValue().toString() + " Pivot View");

            projectionSelectionDTO.setCustomId(customId);
            projectionSelectionDTO.setView(viewOpg.getValue().toString());
            projectionSelectionDTO.setDiscountList(new ArrayList<>(discountlist));
            projectionSelectionDTO.setCustomerLevelNo(Integer.valueOf(NumericConstants.TWO));
            projectionSelectionDTO.setProductLevelNo(Integer.valueOf(NumericConstants.TWO));
            configureProjectionDTO();
            viewChange(false);
            groupChange(false);
        }

        return toRet;
    }

    public void groupChange(boolean groupChange) {
        if (groupDdlb.getValue() != null && (projectionSelectionDTO.isIsCustomHierarchy() || !projectionSelectionDTO.getHierarchyIndicator().equals("P"))) {
            projectionSelectionDTO.setGroupFilter(String.valueOf(groupDdlb.getValue()));
        }
        if (groupChange && firstGenerated && !generated && (projectionSelectionDTO.isIsCustomHierarchy() || !projectionSelectionDTO.getHierarchyIndicator().equals("P"))) {
            tableLogic.groupChange();
        }
    }

    @Override
    public void levelFilterDdlbChangeOption(boolean excelExport) {
        List<Object> levelHierarchy = CommonLogic.getLevelNoAndHierarchyNo(levelFilterDdlb.getValue());

        int levelNo = Integer.valueOf(String.valueOf(levelHierarchy.get(0)));
        if (levelNo < 0) {
            levelNo = 0;
        }
        String hierarchyNo = String.valueOf(levelHierarchy.get(1));
        projectionSelectionDTO.setIsFilter(true);
        if (levelNo == 0) {
            projectionSelectionDTO.setIsFilter(false);
        }
        if (excelExport) {
        } else {
            levelDdlb.setValue(SELECT_ONE);
            loadResultTable(levelNo, hierarchyNo);
        }

    }

    @Override
    public void loadExcelResultTable(int levelNo, String hierarchyNo) {
        excelResultBean.removeAllItems();
        projectionSelectionDTO.setFilterLevelNo(levelNo);
        projectionSelectionDTO.setFilterHierarchyNo(hierarchyNo);
        projectionSelectionDTO.setProductHierarchyNo("");
        projectionSelectionDTO.setCustomerHierarchyNo("");
        projectionSelectionDTO.clearNonFetchableIndex();
        int count = projResLogic.getConfiguredProjectionResultsCount(new Object(), projectionSelectionDTO, true);
        List<ProjectionResultsDTO> resultList = projResLogic.getConfiguredProjectionResults(new Object(), 0, count, projectionSelectionDTO);
        loadDataToContainer(resultList, null);
    }

    public void loadDataToContainer(List<ProjectionResultsDTO> resultList, Object parentId) {
        for (ProjectionResultsDTO dto : resultList) {
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
        projectionSelectionDTO.setFilterLevelNo(0);
        projectionSelectionDTO.setFilterHierarchyNo("");
        projectionSelectionDTO.setProductHierarchyNo("");
        projectionSelectionDTO.setCustomerHierarchyNo("");
        int count = projResLogic.getConfiguredProjectionResultsCount(id, projectionSelectionDTO, true);
        List<ProjectionResultsDTO> resultList = projResLogic.getConfiguredProjectionResults(id, 0, count, projectionSelectionDTO);
        loadDataToContainer(resultList, id);
    }

    @SuppressWarnings("serial")
    private void configureResultTable() {
        try {
            LOGGER.debug("inside Configure results of RESULTS");
            tableLogic.setTreeNodeMultiClick(false);
            tableLogic.sinkItemPerPageWithPageLength(false);
            List<Integer> pagelength = CommonLogic.getPageNumber();
            tableLogic.getControlConfig().setPageLengthsAndCaptions(pagelength);
            fullHeader = new CustomTableHeaderDTO();
            projectionSelectionDTO.setForecastDTO(getHistoricalPeriods(dataSelectionDTO));
            leftHeader = HeaderUtils.getProjectionResultsLeftTableColumns(projectionSelectionDTO, fullHeader);
            rightHeader = HeaderUtils.getProjectionResultsRightTableColumns(projectionSelectionDTO, fullHeader);
            resultBeanContainer = new ExtTreeContainer<>(ProjectionResultsDTO.class, ExtContainer.DataStructureMode.MAP);
            resultBeanContainer.setColumnProperties(fullHeader.getProperties());
            tableLogic.setContainerDataSource(resultBeanContainer);
            final ExtPagedTreeTable leftTable = periodTableId
                    .getLeftFreezeAsTable();
            final ExtPagedTreeTable rightTable = periodTableId
                    .getRightFreezeAsTable();
            leftTable.setImmediate(true);
            rightTable.setImmediate(true);
            leftTable.setVisibleColumns(leftHeader.getSingleColumns().toArray());
            leftTable.setColumnHeaders("");
            rightTable.setVisibleColumns(rightHeader.getSingleColumns().toArray());
            rightTable.setColumnHeaders(rightHeader.getSingleHeaders().toArray(new String[rightHeader.getSingleHeaders().size()]));
            periodTableId.setHeight(StringConstantsUtil.SIX_EIGHTY_PX);
            leftTable.setHeight(StringConstantsUtil.SIX_EIGHTY_PX);
            rightTable.setHeight(StringConstantsUtil.SIX_EIGHTY_PX);
            for (Object propertyId : rightTable.getVisibleColumns()) {
                rightTable.setColumnAlignment(propertyId, ExtCustomTable.Align.RIGHT);
            }

            periodTableId.setDoubleHeaderVisible(true);
            leftTable
                    .setDoubleHeaderVisibleColumns(leftHeader.getDoubleColumns().toArray());
            leftTable
                    .setDoubleHeaderColumnHeaders(leftHeader.getDoubleHeaders().toArray(new String[leftHeader.getDoubleHeaders().size()]));
            rightTable
                    .setDoubleHeaderVisibleColumns(rightHeader.getDoubleColumns().toArray());
            rightTable
                    .setDoubleHeaderColumnHeaders(rightHeader.getDoubleHeaders().toArray(new String[rightHeader.getDoubleHeaders().size()]));
            periodTableId.getRightFreezeAsTable().setDoubleHeaderMap(rightHeader.getDoubleHeaderMaps());
            periodTableId.getLeftFreezeAsTable().setDoubleHeaderMap(leftHeader.getDoubleHeaderMaps());
            periodTableId.setDoubleHeaderMap(leftHeader.getDoubleHeaderMaps(), rightHeader.getDoubleHeaderMaps());
            //For List View Filter
            leftTable.setFilterDecorator(new ExtDemoFilterDecorator());
            projectionSelectionDTO.setProjectionId(sessionDTO.getProjectionId());
            int chk = projectionSelectionDTO.getDiscountNameCFF().size();
            if (chk > 0 && projectionSelectionDTO.getPivotView().equals(VARIABLE_LABEL)) {
                rightTable.setColumnCollapsingAllowed(true);
                int st = NumericConstants.THIRTEEN;
                if (projectionSelectionDTO.getSalesOrUnit().equals(BOTH)) {
                    st = NumericConstants.FOURTEEN;
                }
                rightTable.setDoubleHeaderColumnExpandIcon(rightHeader.getDoubleColumns().get(st), false);
                setExpandIconAction(rightHeader.getDoubleColumns().get(st), true, projectionSelectionDTO.getDiscountNameCFF());
                int st1 = st + projectionSelectionDTO.getDiscountNameCFF().size();
                st1++;
                rightTable.setDoubleHeaderColumnExpandIcon(rightHeader.getDoubleColumns().get(st1), false);
                setExpandIconAction(rightHeader.getDoubleColumns().get(st1), true, projectionSelectionDTO.getDiscountNameCFF());
                int st2 = st1 + projectionSelectionDTO.getDiscountNameCFF().size();
                st2++;
                rightTable.setDoubleHeaderColumnExpandIcon(rightHeader.getDoubleColumns().get(st2), false);
                setExpandIconAction(rightHeader.getDoubleColumns().get(st2), true, projectionSelectionDTO.getDiscountNameCFF());
                int st3 = st2 + projectionSelectionDTO.getDiscountNameCFF().size();
                st3++;
                rightTable.setDoubleHeaderColumnExpandIcon(rightHeader.getDoubleColumns().get(st3), false);
                setExpandIconAction(rightHeader.getDoubleColumns().get(st3), true, projectionSelectionDTO.getDiscountNameCFF());
                rightTable.addDoubleHeaderColumnExpandIconListener(new ExtCustomTable.DoubleHeaderColumnExpandIconListener() {
                    @Override
                    public void doubleHeaderColumnExpandIcon(ExtCustomTable.DoubleHeaderColumnExpandIconEvent event) {
                        setExpandIconAction(event.getPropertyId(), !event.isExpanded(), projectionSelectionDTO.getDiscountNameCFF());
                    }
                });
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public void setExpandIconAction(Object propertuId, boolean collapsed, List<String> discountNames) {
        List<String> discountNames1 = new ArrayList<>(discountNames);
        ExtPagedTreeTable rightTable = periodTableId.getRightFreezeAsTable();
        for (int i = 0; i < discountNames1.size(); i++) {
            String commonColumn = propertuId + discountNames1.get(i).replace(" ", "");
            rightTable.setDoubleHeaderColumnCollapsed(commonColumn, collapsed);
        }
    }

    /**
     * For Header Setting in Results Table
     *
     * @return
     */
    private ForecastDTO getHistoricalPeriods(DataSelectionDTO dataSelectionDTO) {
        Date dbDateTO;
        SimpleDateFormat format = new SimpleDateFormat(Constants.CommonConstants.DATE_FORMAT.getConstant());
        if (dataSelectionDTO == null) {
            dataSelectionDTO = new DataSelectionDTO();
        }

        String sql = "SELECT\n"
                + "    top 1 FROM_DATE,TO_DATE\n"
                + "    FROM\n"
                + "    dbo.FORECAST_CONFIG FG JOIN HELPER_TABLE HT\n"
                + "        ON HT.HELPER_TABLE_SID = FG.BUSINESS_PROCESS_TYPE\n"
                + "    AND HT.list_name = 'BUSINESS_PROCESS_TYPE'\n"
                + "    AND DESCRIPTION LIKE 'Consolidated Financial Forecast'\n"
                + "    ORDER BY FG.VERSION_NO DESC";

        List list;
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
            endDbDate.set(Calendar.MONTH, (dbDateTO.getMonth()) - 1);

            try {
                dbDateFrom = format.parse(format.format(dbDateFrom));
                dbDateTO = format.parse(format.format(dbDateTO));
            } catch (ParseException pe) {
                LOGGER.error(pe);
            }
            dataSelectionDTO.setFromDate(dbDateFrom);
            dataSelectionDTO.setToDate(dbDateTO);
        }
        dataSelectionDTO.setFromDate(fromDateIsNull(dataSelectionDTO.getFromDate()));
        dataSelectionDTO.setToDate(toDateIsNull(dataSelectionDTO.getToDate()));
        ForecastDTO dto = new ForecastDTO();
        try {
                dto = DataSelectionUtil.getForecastDTO(dataSelectionDTO, session);
        } catch (Exception exp) {
            LOGGER.error(exp);
        }
        LOGGER.debug("getHistoricalPeriods method Ends");
        return dto;
    }

    @Override
    protected void excelExportLogic() {
        ConsolidatedFinancialForecastUI.EXCEL_CLOSE = true;
        configureExcelResultTable();
        levelFilterDdlbChangeOption(true);
        exceltable.setRefresh(Boolean.TRUE);
        excelForCommercial();

        ExcelExport exp = null;
        int exportAt = projectionSelectionDTO.getHeaderMapForExcel().size() - 1;
        if ("Period".equals(String.valueOf(pivotViewOpg.getValue())) && (QUARTERLY.equals(String.valueOf(frequencyDdlb.getValue())) || MONTHLY.equals(String.valueOf(frequencyDdlb.getValue())))) {
            for (int i = 0; i < projectionSelectionDTO.getHeaderMapForExcel().size(); i++) {
                exceltable.setVisibleColumns(((List<Object>) projectionSelectionDTO.getHeaderMapForExcel().get(i).get(0)).toArray());
                Object[] header = ((List<Object>) projectionSelectionDTO.getHeaderMapForExcel().get(i).get(1)).toArray();
                exceltable.setColumnHeaders(Arrays.copyOf(header, header.length, String[].class));
                exceltable
                        .setDoubleHeaderVisibleColumns(((List<Object>) projectionSelectionDTO.getHeaderMapForExcel().get(i).get(NumericConstants.THREE)).toArray());
                Object[] doubleHeader = ((List<Object>) projectionSelectionDTO.getHeaderMapForExcel().get(i).get(NumericConstants.FOUR)).toArray();
                exceltable
                        .setDoubleHeaderColumnHeaders(Arrays.copyOf(doubleHeader, doubleHeader.length, String[].class));

                exceltable.setDoubleHeaderMap((Map<Object, Object[]>) projectionSelectionDTO.getHeaderMapForExcel().get(i).get(NumericConstants.FIVE));
                exceltable.setRefresh(true);
                String sheetName = "Year " + String.valueOf(projectionSelectionDTO.getHeaderMapForExcel().get(i).get(NumericConstants.TWO));
                if (i == 0) {
                    exp = new ExcelExport(new ExtCustomTableHolder(exceltable), sheetName, StringConstantsUtil.PROJECTION_RESULTS, "ProjectionResults.xls", false);
                } else {
                    exp.setNextTableHolder(new ExtCustomTableHolder(exceltable), sheetName);
                }
                if (i == exportAt) {
                    exp.exportMultipleTabs(true);
                } else {
                    exp.exportMultipleTabs(false);
                }
            }
        } else {
            exp = new ExcelExport(new ExtCustomTableHolder(exceltable), StringConstantsUtil.PROJECTION_RESULTS, StringConstantsUtil.PROJECTION_RESULTS, "ProjectionResults.xls", false);
            exp.export();
        }
        tableVerticalLayout.removeComponent(exceltable);
    }

    private void configureProjectionDTO() {

        projectionSelectionDTO.setScreenName(screenName);
        projectionSelectionDTO.setCustRelationshipBuilderSid(sessionDTO.getCustRelationshipBuilderSid());
        projectionSelectionDTO.setProdRelationshipBuilderSid(sessionDTO.getProdRelationshipBuilderSid());
        projectionSelectionDTO.setCustomerLevelNo(StringUtils.isBlank(sessionDTO.getCustomerLevelNumber()) || "null".equals(sessionDTO.getCustomerLevelNumber())
                ? 1 : Integer.valueOf(sessionDTO.getCustomerLevelNumber()));
        projectionSelectionDTO.setProductLevelNo(StringUtils.isBlank(sessionDTO.getProductLevelNumber()) || "null".equals(sessionDTO.getProductLevelNumber())
                ? 1 : Integer.valueOf(sessionDTO.getProductLevelNumber()));
        projectionSelectionDTO.setProjectionId(sessionDTO.getProjectionId());
        projectionSelectionDTO.setUserId(Integer.valueOf(projectionSelectionDTO.getSessionDTO().getUserId()));
        projectionSelectionDTO.setSessionId(Integer.valueOf(projectionSelectionDTO.getSessionDTO().getSessionId()));
        projectionSelectionDTO.setFrequency(String.valueOf(frequencyDdlb.getValue()));
        projectionSelectionDTO.setProjectionOrder(String.valueOf(periodOrderOpg.getValue()));
        projectionSelectionDTO.setActualsOrProjections(String.valueOf(actualOrProjectionsOpg.getValue()));
        sessionDTO.setCustomId(customId);
    }

    public void saveProjectionResultsSelection(SessionDTO sessionDTO) throws PortalException, SystemException {
        LOGGER.debug("save Projection Results method starts");
        Map map = new HashMap();
        map.put("Frequency", frequencyDdlb.getValue().toString());
        map.put("History", historyDdlb.getValue().toString());
        map.put("Sales/Units", salesOrUnitsOpg.getValue().toString());
        map.put("Actuals/Projections", actualOrProjectionsOpg.getValue().toString());
        map.put("Period Order", periodOrderOpg.getValue().toString());
        map.put("Pivot", pivotViewOpg.getValue().toString());

        CommonLogic.saveProjectionSelection(map, StringConstantsUtil.PROJECTION_RESULTS, sessionDTO.getProjectionId());
        LOGGER.debug("save Projection Results method ends");
    }

    public void excelForCommercial() {
        try {
            configureExcelResultTable();
            excelLogic.getPVData();
            excelParentRecords.clear();
            List<ProjectionResultsDTO> varibaleList = resultMap.get(Constants.LabelConstants.TOTAL.toString());

            String totalKey = StringConstantsUtil.TOTAL;
            if (varibaleList != null) {
                for (Iterator<ProjectionResultsDTO> it1 = varibaleList.listIterator(); it1.hasNext();) {
                    ProjectionResultsDTO itemId = it1.next();
                    excelResultBean.addBean(itemId);
                    if (itemId.getGroup().startsWith(StringConstantsUtil.TOTAL_DISCOUNT_DOLLAR) || itemId.getGroup().startsWith(StringConstantsUtil.TOTAL_DISCOUNT_PERCENT) || itemId.getGroup().startsWith(StringConstantsUtil.TOTAL_RPU) || itemId.getGroup().equals(Constants.LabelConstants.DISCOUNT_PERCENTAGE_EXFACTORY.getConstant())) {
                        excelResultBean.setChildrenAllowed(itemId, true);
                        if (itemId.getGroup().startsWith(StringConstantsUtil.TOTAL) || itemId.getGroup().equals(Constants.LabelConstants.DISCOUNT_PERCENTAGE_EXFACTORY.getConstant())) {
                            excelResultBean.setChildrenAllowed(itemId, true);
                            if (itemId.getGroup().startsWith(StringConstantsUtil.TOTAL_DISCOUNT_DOLLAR)) {
                                List<List<ProjectionResultsDTO>> discDollar = discountMap.get(totalKey);
                                if (discDollar != null && !discDollar.isEmpty()) {
                                    List<ProjectionResultsDTO> totalList = discDollar.get(0);
                                    if (totalList != null && !totalList.isEmpty()) {
                                        for (Iterator<ProjectionResultsDTO> itr2 = totalList.listIterator(); itr2.hasNext();) {
                                            ProjectionResultsDTO discItemId = itr2.next();
                                            itr2.remove();
                                            excelResultBean.addBean(discItemId);
                                            excelResultBean.setChildrenAllowed(discItemId, false);
                                            excelResultBean.setParent(discItemId, itemId);
                                        }
                                    }
                                }
                            }
                            if (itemId.getGroup().startsWith(StringConstantsUtil.TOTAL_DISCOUNT_PERCENT)) {
                                List<List<ProjectionResultsDTO>> discDollar = discountMap.get(totalKey);
                                if (discDollar != null && !discDollar.isEmpty()) {
                                    List<ProjectionResultsDTO> totalList = discDollar.get(1);
                                    if (totalList != null && !totalList.isEmpty()) {
                                        for (Iterator<ProjectionResultsDTO> itr2 = totalList.listIterator(); itr2.hasNext();) {
                                            ProjectionResultsDTO discItemId = itr2.next();
                                            itr2.remove();
                                            excelResultBean.addBean(discItemId);
                                            excelResultBean.setChildrenAllowed(discItemId, false);
                                            excelResultBean.setParent(discItemId, itemId);
                                        }
                                    }
                                }
                            }
                            if (itemId.getGroup().startsWith(StringConstantsUtil.TOTAL_RPU)) {
                                List<List<ProjectionResultsDTO>> discDollar = discountMap.get(totalKey);
                                if (discDollar != null && !discDollar.isEmpty()) {
                                    List<ProjectionResultsDTO> totalList = discDollar.get(NumericConstants.TWO);
                                    if (totalList != null && !totalList.isEmpty()) {
                                        for (Iterator<ProjectionResultsDTO> itr2 = totalList.listIterator(); itr2.hasNext();) {
                                            ProjectionResultsDTO discItemId = itr2.next();
                                            itr2.remove();
                                            excelResultBean.addBean(discItemId);
                                            excelResultBean.setChildrenAllowed(discItemId, false);
                                            excelResultBean.setParent(discItemId, itemId);
                                        }
                                    }
                                }
                            }
                            if (itemId.getGroup().equals(Constants.LabelConstants.DISCOUNT_PERCENTAGE_EXFACTORY.getConstant())) {
                                List<List<ProjectionResultsDTO>> discDollar = discountMap.get(totalKey);
                                if (discDollar != null && !discDollar.isEmpty()) {
                                    List<ProjectionResultsDTO> totalList = discDollar.get(NumericConstants.THREE);
                                    if (totalList != null && !totalList.isEmpty()) {
                                        for (Iterator<ProjectionResultsDTO> itr2 = totalList.listIterator(); itr2.hasNext();) {
                                            ProjectionResultsDTO discItemId = itr2.next();
                                            itr2.remove();
                                            excelResultBean.addBean(discItemId);
                                            excelResultBean.setChildrenAllowed(discItemId, false);
                                            excelResultBean.setParent(discItemId, itemId);
                                        }
                                    }
                                }
                            }

                        } else {
                            excelResultBean.setChildrenAllowed(itemId, false);
                        }
                    }
                }
            }
            hierarchyKeys.remove(StringConstantsUtil.TOTAL);
            if (!projectionSelectionDTO.getView().equals(CUSTOM.getConstant())) {
                Collections.sort(hierarchyKeys);
            }
            
            for (Iterator<String> it = hierarchyKeys.listIterator(); it.hasNext();) {
                String key = it.next();
                it.remove();
                List<ProjectionResultsDTO> varibaleListDetails = resultMap.get(key);
                resultMap.remove(key);
                Object parentItemId = null;
                int index = 0;
                for (Iterator<ProjectionResultsDTO> it1 = varibaleListDetails.listIterator(); it1.hasNext();) {
                    ProjectionResultsDTO itemId = it1.next();
                    it1.remove();
                    excelResultBean.addBean(itemId);
                    if (index == 0) {
                        String parentKey = StringUtils.EMPTY;
                        if (!projectionSelectionDTO.isIsCustomHierarchy()) {
                            parentKey = key.substring(0, key.lastIndexOf('.'));
                        } else {
                            parentKey = getParentKeyforCustom(itemId, key, parentKey);
                        }
                        if (parentKey.lastIndexOf('.') >= 0) {
                            parentKey = parentKey.substring(0, parentKey.lastIndexOf('.') + 1);
                        }
                        parentItemId = excelParentRecords.get(parentKey);
                        if (parentItemId != null) {
                            excelResultBean.setParent(itemId, parentItemId);
                        }
                        parentItemId = itemId;
                        excelParentRecords.put(key, itemId);
                        excelResultBean.setChildrenAllowed(itemId, true);
                        index = 1;
                    } else {
                        excelResultBean.setParent(itemId, parentItemId);
                        if (itemId.getGroup().startsWith(StringConstantsUtil.TOTAL_DISCOUNT_DOLLAR) || itemId.getGroup().startsWith(StringConstantsUtil.TOTAL_DISCOUNT_PERCENT) || itemId.getGroup().startsWith(StringConstantsUtil.TOTAL_RPU) || itemId.getGroup().equals(Constants.LabelConstants.DISCOUNT_PERCENTAGE_EXFACTORY.getConstant())) {
                            if (itemId.getGroup().startsWith(StringConstantsUtil.TOTAL_DISCOUNT_DOLLAR)) {
                                List<List<ProjectionResultsDTO>> discDollar = discountMap.get(key);
                                if (discDollar != null && !discDollar.isEmpty()) {
                                    List<ProjectionResultsDTO> totalList = discDollar.get(0);
                                    if (totalList != null && !totalList.isEmpty()) {
                                        for (Iterator<ProjectionResultsDTO> itr2 = totalList.listIterator(); itr2.hasNext();) {
                                            ProjectionResultsDTO discItemId = itr2.next();
                                            itr2.remove();
                                            excelResultBean.addBean(discItemId);
                                            excelResultBean.setChildrenAllowed(discItemId, false);
                                            excelResultBean.setParent(discItemId, itemId);
                                        }
                                    }
                                }
                            }
                            if (itemId.getGroup().startsWith(StringConstantsUtil.TOTAL_DISCOUNT_PERCENT)) {
                                List<List<ProjectionResultsDTO>> discDollar = discountMap.get(key);
                                if (discDollar != null && !discDollar.isEmpty()) {
                                    List<ProjectionResultsDTO> totalList = discDollar.get(1);
                                    if (totalList != null && !totalList.isEmpty()) {
                                        for (Iterator<ProjectionResultsDTO> itr2 = totalList.listIterator(); itr2.hasNext();) {
                                            ProjectionResultsDTO discItemId = itr2.next();
                                            itr2.remove();
                                            excelResultBean.addBean(discItemId);
                                            excelResultBean.setChildrenAllowed(discItemId, false);
                                            excelResultBean.setParent(discItemId, itemId);
                                        }
                                    }
                                }
                            }
                            if (itemId.getGroup().startsWith(StringConstantsUtil.TOTAL_RPU)) {
                                List<List<ProjectionResultsDTO>> discDollar = discountMap.get(key);
                                if (discDollar != null && !discDollar.isEmpty()) {
                                    List<ProjectionResultsDTO> totalList = discDollar.get(NumericConstants.TWO);
                                    if (totalList != null && !totalList.isEmpty()) {
                                        for (Iterator<ProjectionResultsDTO> itr2 = totalList.listIterator(); itr2.hasNext();) {
                                            ProjectionResultsDTO discItemId = itr2.next();
                                            itr2.remove();
                                            excelResultBean.addBean(discItemId);
                                            excelResultBean.setChildrenAllowed(discItemId, false);
                                            excelResultBean.setParent(discItemId, itemId);
                                        }
                                    }
                                }
                            }
                            if (itemId.getGroup().equals(Constants.LabelConstants.DISCOUNT_PERCENTAGE_EXFACTORY.getConstant())) {
                                List<List<ProjectionResultsDTO>> discDollar = discountMap.get(key);
                                if (discDollar != null && !discDollar.isEmpty()) {
                                    List<ProjectionResultsDTO> totalList = discDollar.get(NumericConstants.THREE);
                                    if (totalList != null && !totalList.isEmpty()) {
                                        for (Iterator<ProjectionResultsDTO> itr2 = totalList.listIterator(); itr2.hasNext();) {
                                            ProjectionResultsDTO discItemId = itr2.next();
                                            itr2.remove();
                                            excelResultBean.addBean(discItemId);
                                            excelResultBean.setChildrenAllowed(discItemId, false);
                                            excelResultBean.setParent(discItemId, itemId);
                                        }
                                    }
                                }
                            }

                        } else {
                            excelResultBean.setChildrenAllowed(itemId, false);
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        excelParentRecords.clear();
    }

    private String getParentKeyforCustom(ProjectionResultsDTO itemId, String key, String parentKey) {
        if (itemId.getParentHierarchyNo() == null) {
            parentKey = key.substring(0, key.lastIndexOf('.'));
        } else {
            parentKey = itemId.getParentHierarchyNo();
            if (projectionSelectionDTO.isIsCustomHierarchy()) {
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
                if (!projectionSelectionDTO.isIsCustomHierarchy() || !Constants.LabelConstants.PERIOD.toString().equalsIgnoreCase(projectionSelectionDTO.getPivotView())) {
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
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "Consolidated Financial Forecast" + "," + "Results Tab");
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
                expandBtn.setVisible(false);
            } else {
                expandBtn.setVisible(true);
            }
            if (functionHM.get("collapseLvlBtn") != null && !((AppPermission) functionHM.get("collapseLvlBtn")).isFunctionFlag()) {
                collapseBtn.setVisible(false);
            } else {
                collapseBtn.setVisible(true);
            }
            if (functionHM.get("addViewBtn") != null && !((AppPermission) functionHM.get("addViewBtn")).isFunctionFlag()) {
                newBtn.setVisible(false);
            } else {
                newBtn.setVisible(true);
            }
            if (functionHM.get("editViewBtn") != null && !((AppPermission) functionHM.get("editViewBtn")).isFunctionFlag()) {
                editBtn.setVisible(false);
            } else {
                editBtn.setVisible(true);
            }
           
        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex);
        }
    }
    private Date fromDateIsNull(Date fromDate) {
        if (fromDate == null) {
            Calendar calendarFromPeriod = Calendar.getInstance();
            calendarFromPeriod.set(Calendar.YEAR, (calendarFromPeriod.get(Calendar.YEAR) - 3));
            calendarFromPeriod.set(Calendar.MONTH, 0);
            calendarFromPeriod.set(Calendar.DAY_OF_MONTH, 1);
            return calendarFromPeriod.getTime();
        }
        return fromDate;
    }
    private Date toDateIsNull(Date toDate) {
        if (toDate == null) {
            Calendar calendatToPeriod = Calendar.getInstance();
            calendatToPeriod.set(Calendar.YEAR, (calendatToPeriod.get(Calendar.YEAR) + 3));
            calendatToPeriod.set(Calendar.MONTH, 11);
            calendatToPeriod.set(Calendar.DAY_OF_MONTH, calendatToPeriod.getActualMaximum(Calendar.DAY_OF_MONTH));
          return calendatToPeriod.getTime();
        }
        return toDate;
    }
}
