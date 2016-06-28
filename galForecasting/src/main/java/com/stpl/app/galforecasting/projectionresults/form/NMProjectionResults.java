/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.projectionresults.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.app.galforecasting.abstractforecast.ForecastProjectionResults;
import com.stpl.app.galforecasting.discountProjection.form.NMDiscountProjection;
import com.stpl.app.galforecasting.dto.ProjectionResultsDTO;
import com.stpl.app.galforecasting.logic.CommonLogic;
import com.stpl.app.galforecasting.logic.PPAProjectionLogic;
import com.stpl.app.galforecasting.projectionresults.dto.FilterGenerator;
import com.stpl.app.galforecasting.projectionresults.logic.NMProjectionResultsLogic;
import com.stpl.app.galforecasting.projectionresults.logic.NMProjectionResultsXLLogic;
import com.stpl.app.galforecasting.projectionresults.logic.tablelogic.ProjectionResultsTableLogic;
import com.stpl.app.galforecasting.projectionresults.utils.HeaderUtils;
import com.stpl.app.galforecasting.projectionvariance.dto.PVParameters;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.galforecasting.ui.ForecastUI;
import com.stpl.app.galforecasting.ui.form.ForecastForm;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import static com.stpl.app.galforecasting.utils.Constant.CommonConstants.SELECT_ONE;
import static com.stpl.app.galforecasting.utils.Constant.LabelConstants.*;
import com.stpl.app.galforecasting.utils.FunctionNameUtil;
import com.stpl.app.galforecasting.utils.TabNameUtil;
import com.stpl.app.galforecasting.utils.UISecurityUtil;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.utils.Constants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import static com.stpl.ifs.util.constants.GlobalConstants.*;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.ExtCustomTable;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTreeTable;
import org.jboss.logging.Logger;

/**
 *
 * @author sathyaseelan.v
 */
public class NMProjectionResults extends ForecastProjectionResults {

    final StplSecurity stplSecurity = new StplSecurity();

    final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constant.USER_ID));

    private static final Logger LOGGER = Logger.getLogger(NMProjectionResults.class);

    private final NMProjectionResultsLogic projResLogic = new NMProjectionResultsLogic();

    int tradingPartnerNo = 0;

    List<List<String>> discountlist = new ArrayList<>();
    ForecastForm form;
    SessionDTO session;
        protected PVParameters parameterDto = new PVParameters();
    private final Map<String, List<ProjectionResultsDTO>> resultMap = new HashMap();
    private final Map<String, Object> excelParentRecords = new HashMap();
    private final List<String> hierarchyKeys = new ArrayList();
    private final List<String> tradingPartnerKeys = new ArrayList();
    private final List<String> discountKeys = new ArrayList();
    private final NMProjectionResultsXLLogic excelLogic = new NMProjectionResultsXLLogic(resultMap, projectionSelectionDTO, hierarchyKeys, tradingPartnerKeys, discountKeys, parameterDto);

    /**
     * Constructors calls the super class to load and configure the common
     * components and logics and configures the changes specific to Commercial
     * Forecast.
     *
     * @param session SessionDTO
     * @param screenName Screen Name - Business Process Type as in Database.
     */
    public NMProjectionResults(final SessionDTO session, final String screenName, ForecastForm form) throws Exception {
        super(session, screenName);
        this.form = form;
        this.session = session;
        configureOnNonMandated();
        security();
    }

    @Override
    protected void generateButtonLogic() {
        PPAProjectionLogic.waitForPPAProcedure();
        generated = true;
        firstGenerated = true;
        int pageNo = tableLogic.getItemsPerPage();
        loadGroupFilter(sessionDTO.getProjectionId(), projectionSelectionDTO.getUserId(), projectionSelectionDTO.getSessionId(), false);
        if (loadProjectionSelection()) {
            loadGroupFilterOntabChange();
            tableVerticalLayout.removeAllComponents();
            tableLogic = new ProjectionResultsTableLogic();
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
            projectionSelectionDTO.setProjectionId(sessionDTO.getProjectionId());
            projectionSelectionDTO.setUserId(Integer.valueOf(sessionDTO.getUserId()));
            projectionSelectionDTO.setSessionId(Integer.valueOf(sessionDTO.getSessionId()));
            projectionSelectionDTO.setCustRelationshipBuilderSid(sessionDTO.getCustRelationshipBuilderSid());
            projectionSelectionDTO.setProdRelationshipBuilderSid(sessionDTO.getProdRelationshipBuilderSid());
            projectionSelectionDTO.setPpa(CommonLogic.isPPA(Boolean.TRUE, projectionSelectionDTO));
            projectionSelectionDTO.setReturns(CommonLogic.isReturns(Boolean.TRUE, projectionSelectionDTO));
            discountlist = new ArrayList<>();
            NMDiscountProjection dp = form.getDiscountProjection();
            if (dp != null) {
                String discountType = form.getDiscountProjection().getDiscountType();
                projectionSelectionDTO.setDiscountType(discountType);
                if (form.getDiscountProjection().isDiscountGenerated() && discountType != null) {
                    List<String> discountNames = form.getDiscountProjection().getDiscountNamesList();

                    discountlist = CommonLogic.getDiscountNoList(projectionId, discountNames, "Program".equals(discountType), session.getUserId(),session.getSessionId());
                } else if (Constant.EDIT_SMALL.equalsIgnoreCase(session.getAction()) || Constant.VIEW.equalsIgnoreCase(session.getAction())) {
                    fetchDiscountsFromSave();
                }
            }
            projectionSelectionDTO.setForecastDTO(sessionDTO.getForecastDTO());
            projectionSelectionDTO.setHistoryNum(historyNum);
            projectionSelectionDTO.setProjectionNum(CommonUtils.getProjectionNumber(projectionSelectionDTO.getFrequency(), sessionDTO));
            projectionSelectionDTO.setProjection(StringUtils.EMPTY + projectionSelectionDTO.getProjectionNum());

            projectionSelectionDTO.setActualsOrProjections(actualOrProjectionsOpg.getValue().toString());

            projectionSelectionDTO.setSalesOrUnit(salesOrUnitsOpg.getValue().toString());
            projectionSelectionDTO.setProjectionOrder(periodOrderOpg.getValue().toString());
            projectionSelectionDTO.setPivotView(pivotViewOpg.getValue().toString());
            tablePanel.setCaption(pivotViewOpg.getValue().toString() + " Pivot View");

            projectionSelectionDTO.setCustomId(customId);
            projectionSelectionDTO.setView(viewOpg.getValue().toString());
            projectionSelectionDTO.setDiscountList(new ArrayList<>(discountlist));
            projectionSelectionDTO.setCustomerLevelNo(Integer.valueOf(sessionDTO.getCustomerLevelNumber()));
            projectionSelectionDTO.setProductLevelNo(Integer.valueOf(sessionDTO.getProductLevelNumber()));
            viewChange(false);
            groupChange(false);
        }

        return toRet;
    }

    /**
     * Generate Logic for Commercial Projection Results.
     */
    @Override
    public void generateLogic() {
        levelFilterDdlb.removeValueChangeListener(levelFilterChangeOption);
        loadLevelAndFilterValue();
        projectionSelectionDTO.setIsFilter(false);
        loadResultTable(0, StringUtils.EMPTY);
        levelFilterDdlb.addValueChangeListener(levelFilterChangeOption);
    }

    @SuppressWarnings("serial")
    private void loadResultTable(int levelNo, String hierarchyNo) {
        projectionSelectionDTO.setFilterLevelNo(levelNo);
        projectionSelectionDTO.setFilterHierarchyNo(hierarchyNo);
        projectionSelectionDTO.setProductHierarchyNo(StringUtils.EMPTY);
        projectionSelectionDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
        projectionSelectionDTO.clearNonFetchableIndex();
        tableLogic.setProjectionResultsData(projectionSelectionDTO);
        periodTableId.getLeftFreezeAsTable().setFilterBarVisible(true);
        periodTableId.getLeftFreezeAsTable().setFilterDecorator(new ExtDemoFilterDecorator());
        projectionSelectionDTO.setProjectionId(sessionDTO.getProjectionId());
        periodTableId.getLeftFreezeAsTable().setFilterGenerator(new FilterGenerator(projectionSelectionDTO, Boolean.FALSE));
    }

    public void groupChange(boolean groupChange) {
        if (groupDdlb.getValue() != null && (projectionSelectionDTO.isIsCustomHierarchy() || !projectionSelectionDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY))) {
            projectionSelectionDTO.setGroupFilter(String.valueOf(groupDdlb.getValue()));
        }
        if (groupChange && firstGenerated && !generated && (projectionSelectionDTO.isIsCustomHierarchy() || !projectionSelectionDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY))) {
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
            loadExcelResultTable(levelNo, hierarchyNo);
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
        projectionSelectionDTO.setProductHierarchyNo(StringUtils.EMPTY);
        projectionSelectionDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
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
        projectionSelectionDTO.setFilterHierarchyNo(StringUtils.EMPTY);
        projectionSelectionDTO.setProductHierarchyNo(StringUtils.EMPTY);
        projectionSelectionDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
        int count = projResLogic.getConfiguredProjectionResultsCount(id, projectionSelectionDTO, true);
        List<ProjectionResultsDTO> resultList = projResLogic.getConfiguredProjectionResults(id, 0, count, projectionSelectionDTO);
        loadDataToContainer(resultList, id);
    }

    public void loadGroupFilterOntabChange() {
        loadGroupFilter(sessionDTO.getProjectionId(), projectionSelectionDTO.getUserId(), projectionSelectionDTO.getSessionId(), projectionSelectionDTO.isPpa());
    }

    public void loadGroupFilter(int projId, int userId, int sessionId, boolean isPPA) {
        LOGGER.info("loadGroupFilter initiated ");
        groupDdlb.removeAllItems();
        groupDdlb.setNullSelectionAllowed(false);
        List<String> groupList = CommonLogic.getAllGroup(projId, userId, sessionId, isPPA);
        if (groupList != null && !groupList.isEmpty()) {
            for (String groups : groupList) {
                groupDdlb.addItem(groups);
            }
            groupDdlb.select(groupList.get(0));
        }

        LOGGER.info("loadGroupFilter ends ");
    }

    @SuppressWarnings("serial")
    private void configureResultTable() {
        try{
        tableLogic.setTreeNodeMultiClick(false);
        tableLogic.setPageLength(100);
        tableLogic.sinkItemPerPageWithPageLength(false);
        List<Integer> pagelength = CommonLogic.getPageNumber();
        tableLogic.getControlConfig().setPageLengthsAndCaptions(pagelength);
        fullHeader = new CustomTableHeaderDTO();
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
        leftTable.setColumnHeaders(leftHeader.getSingleHeaders().toArray(new String[leftHeader.getSingleHeaders().size()]));
        rightTable.setVisibleColumns(rightHeader.getSingleColumns().toArray());
        rightTable.setColumnHeaders(rightHeader.getSingleHeaders().toArray(new String[rightHeader.getSingleHeaders().size()]));
        periodTableId.setHeight("650px");
        leftTable.setHeight("650px");
        rightTable.setHeight("650px");
        for (Object propertyId : rightTable.getVisibleColumns()) {
            rightTable.setColumnAlignment(propertyId, ExtCustomTable.Align.RIGHT);
        }

        periodTableId.setDoubleHeaderVisible(true);

        rightTable
                .setDoubleHeaderVisibleColumns(rightHeader.getDoubleColumns().toArray());
        rightTable
                .setDoubleHeaderColumnHeaders(rightHeader.getDoubleHeaders().toArray(new String[rightHeader.getDoubleHeaders().size()]));
        periodTableId.getRightFreezeAsTable().setDoubleHeaderMap(rightHeader.getDoubleHeaderMaps());
        leftTable.setFilterBarVisible(true);
        leftTable.setFilterDecorator(new ExtDemoFilterDecorator());
        projectionSelectionDTO.setProjectionId(sessionDTO.getProjectionId());
        leftTable.setFilterGenerator(new FilterGenerator(projectionSelectionDTO, Boolean.FALSE));
        //PPA
        List list = CommonLogic.getPPADiscountNameListPR(projectionSelectionDTO,Boolean.FALSE);
        int ppa= list!=null && !list.isEmpty() && !"null".equals(String.valueOf(list.get(0))) ? (Integer) list.get(0) : 0;
        int chk = projectionSelectionDTO.getDiscountNameList().size();
        chk = ppa;
        if (chk > 0 && projectionSelectionDTO.getPivotView().equals(VARIABLE.getConstant())) {
            rightTable.setColumnCollapsingAllowed(true);
            int st = 7;
            if (projectionSelectionDTO.getSalesOrUnit().equals(BOTH.getConstant())) {
                st = 8;
            }
            rightTable.setDoubleHeaderColumnExpandIcon(rightHeader.getDoubleColumns().get(st), false);
            setExpandIconAction(rightHeader.getDoubleColumns().get(st), true, projectionSelectionDTO.getDiscountNameList());
            int st1 = st + 1 + projectionSelectionDTO.getDiscountNameList().size();
            st1=st1+ppa;
            rightTable.setDoubleHeaderColumnExpandIcon(rightHeader.getDoubleColumns().get(st1), false);
            setExpandIconAction(rightHeader.getDoubleColumns().get(st1), true, projectionSelectionDTO.getDiscountNameList());
            int st2 = st1 + 1 + projectionSelectionDTO.getDiscountNameList().size();
            st2=st2+ppa;
            rightTable.setDoubleHeaderColumnExpandIcon(rightHeader.getDoubleColumns().get(st2), false);
            setExpandIconAction(rightHeader.getDoubleColumns().get(st2), true, projectionSelectionDTO.getDiscountNameList());
            rightTable.addDoubleHeaderColumnExpandIconListener(new ExtCustomTable.DoubleHeaderColumnExpandIconListener() {
                @Override
                public void doubleHeaderColumnExpandIcon(ExtCustomTable.DoubleHeaderColumnExpandIconEvent event) {
                    setExpandIconAction(event.getPropertyId(), !event.isExpanded(), projectionSelectionDTO.getDiscountNameList());
                }
            });
        }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void setExpandIconAction(Object propertuId, boolean collapsed, List<String> discountNames) {
        List<String> discountNames1 = new ArrayList<>(discountNames);
        //PPA
        List list = CommonLogic.getPPADiscountNameListPR(projectionSelectionDTO, Boolean.TRUE);
        if (list != null) {
            discountNames1.addAll(list);
        }
        ExtPagedTreeTable rightTable = periodTableId
                .getRightFreezeAsTable();
        for (int i = 0; i < discountNames1.size(); i++) {
            String commonColumn = propertuId + discountNames1.get(i).replace(" ", StringUtils.EMPTY);
            rightTable.setDoubleHeaderColumnCollapsed(commonColumn, collapsed);
        }
    }

    private void configureOnNonMandated() {

        loadFrequencyDdlb();
        historyDdlb.setValue(4);
        initializeResultTable();
        if (loadProjectionSelection()) {
            configureResultTable();
        }
        loadGroupFilterOntabChange();
        addResultTable();
        groupDdlb.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                groupChange(true);
            }
        });

    }

    @Override
    protected void excelExportLogic() {
        try {
            configureExcelResultTable();
//        levelFilterDdlbChangeOption(true);
            exportLogic();
            exceltable.setRefresh(Boolean.TRUE);
            ForecastUI.EXCEL_CLOSE = true;
            ExcelExport exp = new ExcelExport(new ExtCustomTableHolder(exceltable), "Projection Results", "Projection Results", "Projection Results.xls", false);
            exp.export();
            tableVerticalLayout.removeComponent(exceltable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void security() throws PortalException, SystemException, Exception {

        final Map<String, AppPermission> functionPsHM = stplSecurity.getBusinessFunctionPermission(userId, getCommercialConstant() + "," + UISecurityUtil.PROJECTION_RESULTS);

        if (!(functionPsHM.get(FunctionNameUtil.GENERATE) != null && ((AppPermission) functionPsHM.get(FunctionNameUtil.GENERATE)).isFunctionFlag())) {
            generateBtn.setVisible(Boolean.FALSE);
            collapseBtn.setVisible(Boolean.FALSE);
            expandBtn.setVisible(Boolean.FALSE);
            newBtn.setVisible(Boolean.FALSE);
            editBtn.setVisible(Boolean.FALSE);
        }
    }

    public void fetchDiscountsFromSave() {
        try {
            Map<Object, Object> map = CommonLogic.getNMProjectionSelection(projectionId, TabNameUtil.DISCOUNT_PROJECTION);
            if (map != null && !map.isEmpty()) {
                String discountType = String.valueOf(map.get("Level"));
                List<String> discountNames = new LinkedList<String>(Arrays.asList(String.valueOf(map.get("Selected Discounts")).split("\\s*,\\s*")));

                discountlist = CommonLogic.getDiscountNoList(projectionId, discountNames, "Program".equals(discountType), session.getUserId(),session.getSessionId());
            }
        } catch (Exception e) {
            LOGGER.error("fetchDiscountsFromSave" + e.getMessage());
        }
    }

    public int getTabNumber() {
        return Constant.SIX;
    }

    private void excelInputForProcedure() {
        
//        System.out.println("Group" + group.getValue());
        System.out.println("DTO+++++++++++++++++++++++++" + projectionSelectionDTO.getGroupFilter());
        String splitarr[] = ("All Sales Group".equalsIgnoreCase(projectionSelectionDTO.getGroupFilter())
                || "All Discount Group".equalsIgnoreCase(projectionSelectionDTO.getGroupFilter())
                || "All PPA Group".equalsIgnoreCase(projectionSelectionDTO.getGroupFilter()))
                ? projectionSelectionDTO.getGroupFilter().split(" ") : projectionSelectionDTO.getGroupFilter().split("-");
        String groupFilter = ("All Sales Group".equalsIgnoreCase(projectionSelectionDTO.getGroupFilter()) || "All Discount Group".equalsIgnoreCase(projectionSelectionDTO.getGroupFilter()) || "All PPA Group".equalsIgnoreCase(projectionSelectionDTO.getGroupFilter())) ? splitarr[1] : splitarr[0];
        String groupFilterValue = ("All Sales Group".equalsIgnoreCase(projectionSelectionDTO.getGroupFilter()) || "All Discount Group".equalsIgnoreCase(projectionSelectionDTO.getGroupFilter()) || "All PPA Group".equalsIgnoreCase(projectionSelectionDTO.getGroupFilter())) ? "All" : splitarr[1];
        String discountLevelValue = "Program";

        parameterDto.setUserId(sessionDTO.getUserId());
        //Session Id
        parameterDto.setSessionId(sessionDTO.getSessionId());
        //Discount Level
        parameterDto.setDiscountLevel(discountLevelValue);
        //Discount Id
//        parameters.put("Discount_ID", "");
        //Frequency Value
        parameterDto.setFrequency(projectionSelectionDTO.getFrequencyDivision() == 1 ? "ANNUALLY" : projectionSelectionDTO.getFrequencyDivision() == 2 ? "SEMI ANNUALLY" : projectionSelectionDTO.getFrequencyDivision() == 4 ? "QUARTERLY" : "MONTHLY");
        //Current Hierarchy Indicator Value
        parameterDto.setViewIndicator(projectionSelectionDTO.isIsCustomHierarchy()?"D":projectionSelectionDTO.getHierarchyIndicator());
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

        int customMasterSid = Integer.valueOf(customDdlb.getValue() == null ||!projectionSelectionDTO.isIsCustomHierarchy() ? "0" : customDdlb.getValue().toString());
        parameterDto.setCustomViewMasterSid(customMasterSid);
    }

    private void exportLogic() {
        excelInputForProcedure();
        excelLogic.getPVData();
        excelParentRecords.clear();

            long start = System.currentTimeMillis();
            long end;
            try{
            System.out.println("<=====================Inside Total=====================>");
                List<ProjectionResultsDTO> varibaleList = resultMap.get(Constants.LabelConstants.TOTAL.toString());
                if (varibaleList != null) {
                    for (Iterator<ProjectionResultsDTO> it1 = varibaleList.listIterator(); it1.hasNext();) {
                        ProjectionResultsDTO itemId = it1.next();
                        it1.remove();
                        excelResultBean.addBean(itemId);
                        excelResultBean.setChildrenAllowed(itemId, false);
                        if (!Constants.LabelConstants.TOTAL_DISCOUNT.toString().equals(projectionSelectionDTO.getDiscountLevel())) {
                            if (itemId.getGroup().startsWith("Total "+Constants.LabelConstants.DISCOUNT.toString()) || itemId.getGroup().startsWith("Total "+Constant.PVVariables.VAR_RPU.toString())) {
                                if (itemId.getGroup().startsWith("Total Discount $")) {
                                    excelResultBean.setChildrenAllowed(itemId, true);
                                    List<ProjectionResultsDTO> discountDollarList = resultMap.get("D$value");
                                    for (Iterator<ProjectionResultsDTO> it2 = discountDollarList.listIterator(); it2.hasNext();) {
                                        ProjectionResultsDTO childItem = it2.next();
                                        it2.remove();
                                        excelResultBean.addBean(childItem);
                                        excelResultBean.setChildrenAllowed(childItem, false);
                                        excelResultBean.setParent(childItem, itemId);
                                    }

                                }
                                if (itemId.getGroup().startsWith("Total Discount %")) {
                                    excelResultBean.setChildrenAllowed(itemId, true);
                                    List<ProjectionResultsDTO> discountDollarList = resultMap.get("D%value");
                                    for (Iterator<ProjectionResultsDTO> it5 = discountDollarList.listIterator(); it5.hasNext();) {
                                        ProjectionResultsDTO childItem = it5.next();
                                        it5.remove();
                                        excelResultBean.addBean(childItem);
                                        excelResultBean.setChildrenAllowed(childItem, false);
                                        excelResultBean.setParent(childItem, itemId);
                                    }
                                }

                                if (itemId.getGroup().startsWith("Total RPU")) {
                                    excelResultBean.setChildrenAllowed(itemId, true);
                                    List<ProjectionResultsDTO> discountDollarList = resultMap.get("RPU-value");
                                    for (Iterator<ProjectionResultsDTO> it8 = discountDollarList.listIterator(); it8.hasNext();) {
                                        ProjectionResultsDTO childItem = it8.next();
                                        it8.remove();
                                        excelResultBean.addBean(childItem);
                                        excelResultBean.setChildrenAllowed(childItem, false);
                                        excelResultBean.setParent(childItem, itemId);
                                    }
                                }
                            } else {
                                excelResultBean.setChildrenAllowed(itemId, false);
                            }
                        } else {
                            excelResultBean.setChildrenAllowed(itemId, false);
                        }

                    }
                }
                Collections.sort(hierarchyKeys);
                boolean tpAsParent = true;
                for (Iterator<String> it = hierarchyKeys.listIterator(); it.hasNext();) {
                    String key = it.next();
                    it.remove();
                    varibaleList = resultMap.get(key);
                    resultMap.remove(key);

                    Object parentItemId = null;
                    if (tradingPartnerKeys.contains(key)) {
                        int index = 0;
                        for (Iterator<ProjectionResultsDTO> it1 = varibaleList.listIterator(); it1.hasNext();) {
                            ProjectionResultsDTO itemId = it1.next();
                            it1.remove();
                            excelResultBean.addBean(itemId);
                            if (index++ == 0) {
                                String parentKey = key.substring(0, key.lastIndexOf('.'));
                                if (parentKey.lastIndexOf('.') >= 0) {
                                    parentKey = parentKey.substring(0, parentKey.lastIndexOf('.') + 1);
                                }
                                String groupParentKey = StringUtils.EMPTY;
                                if (tpAsParent) {
                                    groupParentKey = parentKey + projectionSelectionDTO.getGroupFilter();
                                    parentItemId = excelParentRecords.get(parentKey);
                                } else {
                                    groupParentKey = projectionSelectionDTO.getGroupFilter();
                                    parentItemId = excelParentRecords.get(parentKey);
                                }
                                Object gropuParentItemId = excelParentRecords.get(groupParentKey);

                                if (gropuParentItemId == null) {
                                    ProjectionResultsDTO tpGroup = new ProjectionResultsDTO() {
                                        {
                                            setGroup(projectionSelectionDTO.getGroupFilter());
                                        }
                                    };
                                    excelResultBean.addBean(tpGroup);
                                    gropuParentItemId = tpGroup;
                                    excelResultBean.setParent(gropuParentItemId, parentItemId);
                                }
                                if (parentItemId != null) {
                                    excelResultBean.setParent(itemId, gropuParentItemId);
                                } else {
                                    tpAsParent = false;
                                    parentItemId = gropuParentItemId;
                                }
                                excelParentRecords.put(key, itemId);
                                excelResultBean.setParent(itemId, gropuParentItemId);
                                excelResultBean.setParent(gropuParentItemId, parentItemId);
                                if (tpAsParent) {
                                    excelParentRecords.put(parentKey + projectionSelectionDTO.getGroupFilter(), gropuParentItemId);
                                } else {
                                    excelParentRecords.put(projectionSelectionDTO.getGroupFilter(), gropuParentItemId);
                                }
                                parentItemId = itemId;
                                excelResultBean.setChildrenAllowed(itemId, true);
                            } else {
                                excelResultBean.setChildrenAllowed(itemId, false);
                                excelResultBean.setParent(itemId, parentItemId);

                                if (!Constants.LabelConstants.TOTAL_DISCOUNT.toString().equals(projectionSelectionDTO.getDiscountLevel())) {

                                    if (itemId.getGroup().startsWith(Constants.LabelConstants.TOTAL_DISCOUNT.toString()) || itemId.getGroup().startsWith("Total " + Constant.PVVariables.VAR_RPU.toString())) {
                                        excelResultBean.setChildrenAllowed(itemId, true);
                                        excelParentRecords.put(key + itemId.getGroup(), itemId);
                                    }
                                }
                            }
                        }
                    } else {
                        int index = 0;
                        for (Iterator<ProjectionResultsDTO> it1 = varibaleList.listIterator(); it1.hasNext();) {
                            ProjectionResultsDTO itemId = it1.next();
                            it1.remove();
                            excelResultBean.addBean(itemId);
                            if (index++ == 0) {
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
                            } else {
//                            System.out.println("Inside Else Part:");
                                excelResultBean.setParent(itemId, parentItemId);
                                excelResultBean.setChildrenAllowed(itemId, false);
                                if (!Constants.LabelConstants.TOTAL_DISCOUNT.toString().equals(projectionSelectionDTO.getDiscountLevel())) {
                                    if (itemId.getGroup().startsWith(Constants.LabelConstants.TOTAL_DISCOUNT.toString()) || itemId.getGroup().startsWith("Total "+Constant.PVVariables.VAR_RPU.toString())) {
                                        excelResultBean.setChildrenAllowed(itemId, true);
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
                    varibaleList = resultMap.get(key);
                    resultMap.remove(key);
                    Object parentItemId = excelParentRecords.get(key);
                    for (Iterator<ProjectionResultsDTO> it1 = varibaleList.listIterator(); it1.hasNext();) {
                        ProjectionResultsDTO itemId = it1.next();
                        it1.remove();
                        excelResultBean.addBean(itemId);
                        excelResultBean.setParent(itemId, parentItemId);
                        excelResultBean.setChildrenAllowed(itemId, false);
                    }
                }
            end = System.currentTimeMillis();
            excelParentRecords.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
