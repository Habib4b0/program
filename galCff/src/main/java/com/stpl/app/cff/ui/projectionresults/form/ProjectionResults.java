
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.projectionresults.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.cff.abstractCff.ForecastProjectionResults;
import com.stpl.app.cff.ui.projectionresults.dto.ProjectionResultsDTO;
import com.stpl.app.cff.dto.SessionDTO;
import com.stpl.app.cff.lazyLoad.ResultsTableLogic;
import com.stpl.app.cff.logic.CommonLogic;
import com.stpl.app.cff.ui.ConsolidatedFinancialForecastUI;
import com.stpl.app.cff.ui.dataSelection.dto.ForecastDTO;
import com.stpl.app.cff.ui.projectionVariance.dto.PVParameters;
import com.stpl.app.cff.ui.projectionresults.logic.PRExcelLogic;
import com.stpl.app.cff.ui.projectionresults.logic.ProjectionResultsLogic;
import static com.stpl.app.cff.util.CommonUtils.BOTH;
import com.stpl.app.cff.util.Constants;
import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.vaadin.ui.ExtCustomTable;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTreeTable;
import static com.stpl.app.cff.util.Constants.CommonConstants.SELECT_ONE;
import static com.stpl.app.cff.util.Constants.CommonConstantsForChannels.ACTION_EDIT;
import static com.stpl.app.cff.util.Constants.CommonConstantsForChannels.ACTION_VIEW;
import static com.stpl.app.cff.util.Constants.VARIABLE;
import com.stpl.app.cff.util.DataSelectionUtil;
import com.stpl.app.cff.util.HeaderUtils;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;

/**
 *
 * @author mohamed.hameed
 */
public class ProjectionResults extends ForecastProjectionResults {

    SessionDTO session;
    List<List<String>> discountlist = new ArrayList<>();
    private final ProjectionResultsLogic projResLogic = new ProjectionResultsLogic();
   private final Map<String, List<ProjectionResultsDTO>> resultMap = new HashMap();
       boolean flag = false;
    
    private final Map<String, Object> excelParentRecords = new HashMap();
    private final List<String> hierarchyKeys = new ArrayList();
    private final List<String> tradingPartnerKeys = new ArrayList();
    private final List<String> discountKeys = new ArrayList();
    protected PVParameters parameterDto = new PVParameters();
    private final Map<String, List<List<ProjectionResultsDTO>>> discountMap=new HashMap<String, List<List<ProjectionResultsDTO>>>();
    private final PRExcelLogic excelLogic = new PRExcelLogic(resultMap, projectionSelectionDTO, hierarchyKeys, tradingPartnerKeys, discountKeys, parameterDto, discountMap);
    DataSelectionDTO dataSelectionDTO;

    public ProjectionResults(final SessionDTO session, final String screenName, final DataSelectionDTO dataSelectionDTO) throws Exception {
        super(session, screenName);
        this.session = session;
        this.dataSelectionDTO = dataSelectionDTO;
        tablePanel.setCaption(pivotViewOpg.getValue().toString() + " Pivot View");
        configureOnNonMandated();
    }

    private void configureOnNonMandated() throws Exception{

        loadFrequencyDdlb();
        historyDdlb.setValue(4);
        initializeResultTable();
        if (loadProjectionSelection()) {
            configureResultTable();
        }
    //    loadGroupFilterOntabChange();
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
       // loadGroupFilter(sessionDTO.getProjectionId(), projectionSelectionDTO.getUserId(), projectionSelectionDTO.getSessionId(), false);
        if (loadProjectionSelection()) {
           // loadGroupFilterOntabChange();
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
        if (freq != null) {
            if (!SELECT_ONE.getConstant().equals(freq.toString())) {
                toFreq = true;
                projectionSelectionDTO.setFrequency(freq.toString());
            }
        }
        Object hist = historyDdlb.getValue();
        boolean toHist = false;
        if (hist != null) {
            if (!SELECT_ONE.getConstant().equals(hist.toString())) {
                toHist = true;
                projectionSelectionDTO.setHistory(hist.toString());
                String[] his = historyDdlb.getValue().toString().split(" ");
                historyNum = Integer.valueOf(his[0]);
            }
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
            projectionSelectionDTO.setProjectionNum(2);
            projectionSelectionDTO.setProjection("" + projectionSelectionDTO.getProjectionNum());

            projectionSelectionDTO.setActualsOrProjections(actualOrProjectionsOpg.getValue().toString());

            projectionSelectionDTO.setProjectionId(sessionDTO.getProjectionId());
            projectionSelectionDTO.setSalesOrUnit(salesOrUnitsOpg.getValue().toString());
            projectionSelectionDTO.setProjectionOrder(periodOrderOpg.getValue().toString());
            projectionSelectionDTO.setPivotView(pivotViewOpg.getValue().toString());
            tablePanel.setCaption(pivotViewOpg.getValue().toString() + " Pivot View");

            projectionSelectionDTO.setCustomId(customId);
            projectionSelectionDTO.setView(viewOpg.getValue().toString());
            projectionSelectionDTO.setDiscountList(new ArrayList<>(discountlist));
            projectionSelectionDTO.setCustomerLevelNo(Integer.valueOf(2));
            projectionSelectionDTO.setProductLevelNo(Integer.valueOf(2));
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
         //   loadExcelResultTable(levelNo, hierarchyNo);
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

//    public void loadGroupFilterOntabChange() {
//        loadGroupFilter(sessionDTO.getProjectionId(), projectionSelectionDTO.getUserId(), projectionSelectionDTO.getSessionId(), projectionSelectionDTO.isPpa());
//    }

    @SuppressWarnings("serial")
    private void configureResultTable() {
        try {
            LOGGER.info("inside Configure results of RESULTS");
            tableLogic.setTreeNodeMultiClick(false);
           // tableLogic.setPageLength(100);
            tableLogic.sinkItemPerPageWithPageLength(false);
            List<Integer> pagelength = CommonLogic.getPageNumber();
            tableLogic.getControlConfig().setPageLengthsAndCaptions(pagelength);
            fullHeader = new CustomTableHeaderDTO();
            projectionSelectionDTO.setForecastDTO(getHistoricalPeriods(dataSelectionDTO));
            leftHeader = HeaderUtils.getProjectionResultsLeftTableColumns(projectionSelectionDTO, fullHeader);
            rightHeader = HeaderUtils.getProjectionResultsRightTableColumns(projectionSelectionDTO, fullHeader);
            resultBeanContainer = new CustomTreeContainer<>(ProjectionResultsDTO.class);
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
            periodTableId.setHeight("680px");
            leftTable.setHeight("680px");
            rightTable.setHeight("680px");
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
            if (chk > 0 && projectionSelectionDTO.getPivotView().equals(VARIABLE)) {
                rightTable.setColumnCollapsingAllowed(true);
                int st = 13;
                if (projectionSelectionDTO.getSalesOrUnit().equals(BOTH)) {
                    st = 14;
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
                rightTable.addDoubleHeaderColumnExpandIconListener(new ExtCustomTable.DoubleHeaderColumnExpandIconListener() {
                    @Override
                    public void doubleHeaderColumnExpandIcon(ExtCustomTable.DoubleHeaderColumnExpandIconEvent event) {
                        setExpandIconAction(event.getPropertyId(), !event.isExpanded(), projectionSelectionDTO.getDiscountNameCFF());
                    }
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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

//    public void loadGroupFilter(int projId, int userId, int sessionId, boolean isPPA) {
//        LOGGER.info("loadGroupFilter initiated ");
//        groupDdlb.removeAllItems();
//        groupDdlb.setNullSelectionAllowed(false);
//        List<String> groupList = CommonLogic.getAllGroup(projId, userId, sessionId, isPPA);
//        if (groupList != null && !groupList.isEmpty()) {
//            for (String groups : groupList) {
//                groupDdlb.addItem(groups);
//            }
//            groupDdlb.select(groupList.get(0));
//        }
//
//        LOGGER.info("loadGroupFilter ends ");
//    }
    /**
     * For Header Setting in Results Table 
     * @return 
     */
       private ForecastDTO getHistoricalPeriods(DataSelectionDTO dataSelectionDTO) {
        Date dbDateTO = new Date();
        SimpleDateFormat format = new SimpleDateFormat(Constants.CommonConstants.DATE_FORMAT.getConstant());
        if(dataSelectionDTO == null) {
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
            endDbDate.set(Calendar.MONTH, ((dbDateTO.getMonth()) - 1));

            try {
                dbDateFrom = format.parse(format.format(dbDateFrom));
                dbDateTO = format.parse(format.format(dbDateTO));
            } catch(ParseException pe) {
                LOGGER.error(pe);
            }
            dataSelectionDTO.setFromDate(dbDateFrom);
            dataSelectionDTO.setToDate(dbDateTO);
        }
        ForecastDTO dto = new ForecastDTO();
        try {
            dto = DataSelectionUtil.getForecastDTO(dataSelectionDTO, session);
        } catch(SystemException se) {
            LOGGER.error(se);
        } catch (Exception exp) {
            LOGGER.error(exp);
        }
        LOGGER.info("getHistoricalPeriods method Ends");
        return dto;
    }

    @Override
    protected void excelExportLogic() {
        ConsolidatedFinancialForecastUI.EXCEL_CLOSE = true;
        configureExcelResultTable();
        levelFilterDdlbChangeOption(true);
        exceltable.setRefresh(Boolean.TRUE);

        //   setPvSelection(String.valueOf(variableCategory.getValue()), variablesValue);
        excelForCommercial();
          //  long start = System.currentTimeMillis();
        //   exceltable.setRefresh(Boolean.TRUE);
        // long end = System.currentTimeMillis();
        //  LOGGER.info("Time taken to refresh table :" + (end - start));
        //  start = end;
        //  ForecastUI.EXCEL_CLOSE=true;

        ExcelExport exp = new ExcelExport(new ExtCustomTableHolder(exceltable), "Projection Results", "Projection Results", "Projection Results.xls", false);
        exp.export();
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
    }

     public void saveProjectionResultsSelection(SessionDTO sessionDTO) throws Exception{
        LOGGER.info("save Projection Results method starts");
        Map map = new HashMap();
        map.put("Frequency", frequencyDdlb.getValue().toString());
        map.put("History", historyDdlb.getValue().toString());
        map.put("Sales/Units", salesOrUnitsOpg.getValue().toString());
        map.put("Actuals/Projections", actualOrProjectionsOpg.getValue().toString());
        map.put("Period Order", periodOrderOpg.getValue().toString());
        map.put("Pivot", pivotViewOpg.getValue().toString());

        CommonLogic.saveProjectionSelection(map,"Projection Results",sessionDTO.getProjectionId());
        LOGGER.info("save Projection Results method ends");
    }
     public void saveSelectiononEdit(int projectionid,String screenName){
    
    }
    
    public void excelForCommercial() {
        try {
            // configureExcelTable();
            //   getExcelProcedureInput();
            configureExcelResultTable();
            excelLogic.getPVData();
            excelParentRecords.clear();
            List<ProjectionResultsDTO> varibaleList = resultMap.get(Constants.LabelConstants.TOTAL.toString());
          
                String totalKey="Total";
            if (varibaleList != null) {
                for (Iterator<ProjectionResultsDTO> it1 = varibaleList.listIterator(); it1.hasNext();) {
                    ProjectionResultsDTO itemId = it1.next();
                    excelResultBean.addBean(itemId);
                    if (itemId.getGroup().startsWith("Total Discount $") || itemId.getGroup().startsWith("Total Discount %") || itemId.getGroup().startsWith("Total RPU")) {
                    excelResultBean.setChildrenAllowed(itemId, true);
                        if (itemId.getGroup().startsWith("Total")) {
                        excelResultBean.setChildrenAllowed(itemId, true);
                              if (itemId.getGroup().startsWith("Total Discount $")) {
                                    List<List<ProjectionResultsDTO>> discDollar = discountMap.get(totalKey);
                                  if(discDollar!=null && discDollar.size()>0){
                                    List<ProjectionResultsDTO> totalList = discDollar.get(0);
                                    if (totalList != null && totalList.size() > 0) {
                                        for (Iterator<ProjectionResultsDTO> itr2 = totalList.listIterator(); itr2.hasNext();) {
                                            ProjectionResultsDTO discItemId = itr2.next();
                                                itr2.remove();
                                            excelResultBean.addBean(discItemId);
                                            excelResultBean.setChildrenAllowed(discItemId, false);
                                            excelResultBean.setParent(discItemId, itemId);
                                        }
                                    }
                                  }
                                } if (itemId.getGroup().startsWith("Total Discount %")) {
                                    List<List<ProjectionResultsDTO>> discDollar = discountMap.get(totalKey);
                                     if(discDollar!=null && discDollar.size()>0){
                                    List<ProjectionResultsDTO> totalList = discDollar.get(1);
                                    if (totalList != null && totalList.size() > 0) {
                                        for (Iterator<ProjectionResultsDTO> itr2 = totalList.listIterator(); itr2.hasNext();) {
                                            ProjectionResultsDTO discItemId = itr2.next();
                                                itr2.remove();
                                            excelResultBean.addBean(discItemId);
                                            excelResultBean.setChildrenAllowed(discItemId, false);
                                            excelResultBean.setParent(discItemId, itemId);
                                        }
                                    }
                                    }
                                }   if (itemId.getGroup().startsWith("Total RPU")) {
                                    List<List<ProjectionResultsDTO>> discDollar = discountMap.get(totalKey);
                                    if(discDollar!=null && discDollar.size()>0){
                                    List<ProjectionResultsDTO> totalList = discDollar.get(2);
                                    if (totalList != null && totalList.size() > 0) {
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
            hierarchyKeys.remove("Total");
            Collections.sort(hierarchyKeys);
          
            for (Iterator<String> it = hierarchyKeys.listIterator(); it.hasNext();) {
                String key = it.next();
                it.remove();
                List<ProjectionResultsDTO> varibaleListDetails = resultMap.get(key);
                
                resultMap.remove(key);
                Object parentItemId = null;
                //   Collections.sort(hierarchyKeys);
                int index = 0;
                for (Iterator<ProjectionResultsDTO> it1 = varibaleListDetails.listIterator(); it1.hasNext();) {
                    ProjectionResultsDTO itemId = it1.next();
                    it1.remove();
                    excelResultBean.addBean(itemId);
                    if (index == 0) {
                        String parentKey = key.substring(0, key.lastIndexOf('.'));
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
                        if (itemId.getGroup().startsWith("Total Discount $") || itemId.getGroup().startsWith("Total Discount %") || itemId.getGroup().startsWith("Total RPU")) {
                            if (itemId.getGroup().startsWith("Total Discount $")) {
                                List<List<ProjectionResultsDTO>> discDollar = discountMap.get(key);
                                 if (discDollar != null && discDollar.size() > 0) {
                                List<ProjectionResultsDTO> totalList = discDollar.get(0);
                                if (totalList != null && totalList.size() > 0) {
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
                            if (itemId.getGroup().startsWith("Total Discount %")) {
                                List<List<ProjectionResultsDTO>> discDollar = discountMap.get(key);
                                 if (discDollar != null && discDollar.size() > 0) {
                                List<ProjectionResultsDTO> totalList = discDollar.get(1);
                                if (totalList != null && totalList.size() > 0) {
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
                            if (itemId.getGroup().startsWith("Total RPU")) {
                                List<List<ProjectionResultsDTO>> discDollar = discountMap.get(key);
                                 if (discDollar != null && discDollar.size() > 0) {
                                List<ProjectionResultsDTO> totalList = discDollar.get(2);
                                if (totalList != null && totalList.size() > 0) {
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
            e.printStackTrace();
        }
        excelParentRecords.clear();
    }
    }
