/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.discountProjection.logic.tableLogic;

import com.stpl.app.gtnforecasting.discountProjection.form.NMDiscountProjection;
import com.stpl.app.gtnforecasting.discountProjection.logic.DiscountQueryBuilder;
import com.stpl.app.gtnforecasting.dto.DiscountProjectionDTO;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.logic.DiscountProjectionLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.CommonUtil;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.utils.Constants;
import com.stpl.ifs.ui.extfilteringtable.PageTreeTableLogic;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.GtnSmallHashMap;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.v7.data.Container;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtTreeContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sriram
 */
public class NMDiscountTableLoadLogic extends PageTreeTableLogic {

    
    private CustomTableHeaderDTO rightDto = new CustomTableHeaderDTO();
    private SessionDTO session;
    private String frequency;
    private String history;
    private String projectionPeriodorder;
    private boolean isProgram;
    private List<String> discountList = new ArrayList<>();
    private List<Integer> startAndEndPeriods = new ArrayList<>();
    private String year;
    private int levelNo;
    private boolean isParent;
    private List<Leveldto> currentHierarchy;
    private boolean isCustomHierarchy;
    private String hierarchyIndicator;
    private boolean dataLoad = false;
    private boolean isChildrenAllowed = true;
    private int lastLevelNo = 0;
    private String userGroup = StringUtils.EMPTY;
    private int customId = 0;
    protected boolean checkAll = false;
    private String relationshipBuilderSid = StringUtils.EMPTY;
    private final DiscountProjectionLogic logic = new DiscountProjectionLogic();
    private final NMDiscountProjection nmDiscountProjection;
    private ProjectionSelectionDTO projectionSelection;
    private List<String> forecastConfigList;
    private int maxExpandLevelNo = 0;
    protected String refreshHierarchyNumbers = StringUtils.EMPTY;

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(NMDiscountTableLoadLogic.class);

    protected CommonLogic commonLogic = new CommonLogic();
    protected DiscountQueryBuilder queryBuilder = new DiscountQueryBuilder();

    public void setDiscountVariablesForLogic(SessionDTO session, ProjectionSelectionDTO projectionSelection, List<Integer> startAndEndPeriods, boolean isProgram, List<String> discountList,
            int levelNo, boolean isParent, CustomTableHeaderDTO rightDto, String hierarchyIndicator, String userGroup, List<Leveldto> currentHierarchy,
            boolean isCustomHierarchy, int customId, String relationshipBuilderSid) {
        this.rightDto = rightDto;
        this.session = session;
        this.frequency = projectionSelection.getFrequency();
        this.history = projectionSelection.getHistory();
        this.projectionPeriodorder = projectionSelection.getProjectionOrder();
        this.isProgram = isProgram;
        this.discountList = discountList == null ? discountList : new ArrayList<>(discountList);
        this.year = projectionSelection.getYear();
        this.isCustomHierarchy = isCustomHierarchy;
        this.levelNo = levelNo;
        this.hierarchyIndicator = hierarchyIndicator;
        this.currentHierarchy = currentHierarchy == null ? currentHierarchy : new ArrayList<>(currentHierarchy);
        this.isParent = isParent;
        this.startAndEndPeriods = startAndEndPeriods == null ? startAndEndPeriods : new ArrayList<>(startAndEndPeriods);
        this.customId = customId;
        this.userGroup = userGroup;
        this.relationshipBuilderSid = relationshipBuilderSid;
        this.forecastConfigList = projectionSelection.getForecastConfigPeriods();
        this.projectionSelection = projectionSelection;
        if (!isCustomHierarchy) {
            if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(hierarchyIndicator)) {
                lastLevelNo = session.getLowerMostCustomerLevelNo();
            } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(hierarchyIndicator)) {
                lastLevelNo = session.getLowerMostProductLevelNo();
            } else {
                lastLevelNo = 0;
            }
        } else {
            lastLevelNo = currentHierarchy != null ? currentHierarchy.size() : 0;
        }
        setCurrentPage(1);
    }

    public void filterLevels(int levelNo) {
        setLevelNo(levelNo);
        setCurrentPage(1);
    }

    public void setLevelNo(int levelNo) {
        this.levelNo = levelNo;
    }

    public void setCheckAll(boolean checkAll) {
        this.checkAll = checkAll;
    }

    public void setChildrenAllowed(boolean isChildrenAllowed) {
        this.isChildrenAllowed = isChildrenAllowed;
    }

    public int getMaxExpandLevelNo() {
        return maxExpandLevelNo;
    }

    public void setMaxExpandLevelNo(int maxExpandLevelNo) {
        this.maxExpandLevelNo = maxExpandLevelNo;
    }

    public NMDiscountTableLoadLogic(NMDiscountProjection nmDiscountProjection) {
        this.nmDiscountProjection = nmDiscountProjection;
    }

    @Override
    public GtnSmallHashMap loadData(int start, int offset) {
        GtnSmallHashMap map = new GtnSmallHashMap();
        List<DiscountProjectionDTO> list;
        list = loadLevelData(getLastParent(), start, offset);

        int i = start;
        for (DiscountProjectionDTO dto : list) {

            map.put(i, dto);
            i++;
        }
        return map;
    }

    private List<DiscountProjectionDTO> loadLevelData(Object parentId, int start, int offset) {
        LOGGER.debug("inside discount projection loadData method ");
        DiscountProjectionLogic logicDiscountProj = new DiscountProjectionLogic();
        List list = new ArrayList();

        try {
            int levelNumber = 0;
            String hierarchyNo = StringUtils.EMPTY;
            String tempHierarchyIndicator = StringUtils.EMPTY;
            String customerHierarchyNo = StringUtils.EMPTY;
            String productHierarchyNo = StringUtils.EMPTY;
            String deductionHierarchyNo = StringUtils.EMPTY;

            int treeLevelNo = 0;
            List customDetailsList = new ArrayList();
            LOGGER.debug(" Custom hierarcht is bool= {} " , isCustomHierarchy);

            boolean isParentChecked = false;
            if (parentId instanceof DiscountProjectionDTO) {
                // For child
                DiscountProjectionDTO dto = (DiscountProjectionDTO) parentId;
                LOGGER.debug(" dto.getLevelNo()= {}, dto.getLevelName()= {} " , dto.getTreeLevelNo(),  dto.getLevelName());
                isParentChecked = (Boolean) dto.getPropertyValue("checkRecord");
                Leveldto levelDto = CommonLogic.getNextLevel(dto.getTreeLevelNo() + 1, currentHierarchy);
                if (levelDto != null) {
                    if (isCustomHierarchy) {
                        hierarchyNo = dto.getHierarchyNo();
                        tempHierarchyIndicator = levelDto.getHierarchyIndicator();
                        levelNumber = levelDto.getLevelNo();
                        treeLevelNo = levelDto.getTreeLevelNo();
                        if (Constant.INDICATOR_LOGIC_DEDUCTION_HIERARCHY.equals(dto.getHierarchyIndicator())) {
                            customerHierarchyNo = dto.getCustomerHierarchyNo();
                            productHierarchyNo = dto.getProductHierarchyNo();
                            deductionHierarchyNo = dto.getHierarchyNo();
                        } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(dto.getHierarchyIndicator())) {
                            customerHierarchyNo = dto.getHierarchyNo();
                            productHierarchyNo = dto.getProductHierarchyNo();
                            deductionHierarchyNo = dto.getDeductionHierarchyNo();
                        } else {
                            customerHierarchyNo = dto.getCustomerHierarchyNo();
                            productHierarchyNo = dto.getHierarchyNo();
                            deductionHierarchyNo = dto.getDeductionHierarchyNo();
                        }

                    } else {
                        levelNumber = levelDto.getLevelNo();
                        hierarchyNo = dto.getHierarchyNo();
                        treeLevelNo = levelNumber;
                        tempHierarchyIndicator = levelDto.getHierarchyIndicator();
                        LOGGER.debug(" This is not custom hierarchy child");
                    }
                }

            } else // For parent
            {
                if (currentHierarchy != null) {
                    LOGGER.debug(" This is parent");
                    LOGGER.debug(" level No= {}" , levelNo);
                    Leveldto levelDto = CommonLogic.getNextLevel(levelNo, currentHierarchy);

                    if (levelDto != null) {
                        tempHierarchyIndicator = levelDto.getHierarchyIndicator();
                        levelNumber = levelDto.getLevelNo();
                        if (isCustomHierarchy) {
                            treeLevelNo = levelDto.getTreeLevelNo();
                            customerHierarchyNo = StringUtils.EMPTY;
                            productHierarchyNo = StringUtils.EMPTY;
                            deductionHierarchyNo = StringUtils.EMPTY;
                        } else {
                            treeLevelNo = levelNumber;
                        }
                    }
                }
            }

            customDetailsList.add(levelNumber);
            customDetailsList.add(hierarchyNo);
            customDetailsList.add(treeLevelNo);

            List<String> customViewDetails = new ArrayList<>();
            if (isCustomHierarchy) {
                String customerLevelNo;
                String productLevelNo;
                String deductionLevelNo;

                if (Constant.INDICATOR_LOGIC_DEDUCTION_HIERARCHY.equals(tempHierarchyIndicator)) {
                    customerLevelNo = Constant.PERCENT;
                    productLevelNo = Constant.PERCENT;
                    deductionLevelNo = String.valueOf(treeLevelNo);
                } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(tempHierarchyIndicator)) {
                    customerLevelNo = String.valueOf(treeLevelNo);
                    productLevelNo = Constant.PERCENT;
                    deductionLevelNo = Constant.PERCENT;
                } else {
                    customerLevelNo = Constant.PERCENT;
                    deductionLevelNo = Constant.PERCENT;
                    productLevelNo = String.valueOf(treeLevelNo);
                }

                LOGGER.debug(" Custom hierarchy --- \n customId= {} " , customId);
                LOGGER.debug(" customerLevelNo\n= {} " , customerLevelNo);
                LOGGER.debug(" customerHierarchyNo\n= {} " , customerHierarchyNo);
                LOGGER.debug(" productLevelNo= {} " , productLevelNo);
                LOGGER.debug(" productHierarchyNo= {} " , productHierarchyNo);
                customViewDetails.add(StringUtils.EMPTY + customId);
                customViewDetails.add(customerLevelNo);
                customViewDetails.add(customerHierarchyNo);
                customViewDetails.add(productLevelNo);
                customViewDetails.add(productHierarchyNo);
                customViewDetails.add(session.getCustRelationshipBuilderSid());
                customViewDetails.add(session.getProdRelationshipBuilderSid());
                customViewDetails.add(session.getDedRelationshipBuilderSid());
                customViewDetails.add(deductionLevelNo);
                customViewDetails.add(deductionHierarchyNo);
            } else {
                LOGGER.debug(" Hierarchy No === {} " , hierarchyNo);
                LOGGER.debug(" level No === {}" , levelNumber);
                LOGGER.debug(" Hierarchy indicator === {}" , tempHierarchyIndicator);
            }
            LOGGER.debug(" customTreeLevelNo  === {}" , treeLevelNo);

            
            if (levelNumber != 0 && treeLevelNo != 0) {
                list = logicDiscountProj.getDiscountProjection(session, frequency, startAndEndPeriods,
                        history, tempHierarchyIndicator, projectionPeriodorder, userGroup,
                        isProgram, discountList, year, customDetailsList, isParent, isCustomHierarchy, rightDto, start, offset, false, isParentChecked, customViewDetails, false, false,
                        StringUtils.EMPTY, relationshipBuilderSid, false, Collections.emptyList(), false, StringUtils.EMPTY, StringUtils.EMPTY, Collections.emptyList(), new HashMap<String, String>(), forecastConfigList, projectionSelection);
            }

        } catch (Exception ex) {
            refreshHierarchyNumbers = StringUtils.EMPTY;
            LOGGER.error(ex.getMessage());
        }
        refreshHierarchyNumbers = StringUtils.EMPTY;
        return list;
    }

    @Override
    public int getCount() {
        return getLevelCount(getLastParent());
    }

    public int getLevelCount(Object parentId) {
        if (!dataLoad) {
            return 0;
        }
        LOGGER.debug("inside discount projection getCount method");
        DiscountProjectionLogic logicdisProjection = new DiscountProjectionLogic();
        try {
            int levelNumber = 0;
            String hierarchyNo = StringUtils.EMPTY;
            String tempHierarchyIndicator = StringUtils.EMPTY;
            String customerHierarchyNo = StringUtils.EMPTY;
            String productHierarchyNo = StringUtils.EMPTY;
            String deductionHierarchyNo = StringUtils.EMPTY;

            int treeLevelNo = 0;
            List customDetailsList = new ArrayList();
            LOGGER.debug(" Custom hierarcht is bool= {} " , isCustomHierarchy);

            if (parentId instanceof DiscountProjectionDTO) {
                // For child
                DiscountProjectionDTO dto = (DiscountProjectionDTO) parentId;
                LOGGER.debug(" dto.getLevelNo()= {} " , dto.getTreeLevelNo());
                Leveldto levelDto = CommonLogic.getNextLevel(dto.getTreeLevelNo() + 1, currentHierarchy);
                if (levelDto != null) {
                    if (isCustomHierarchy) {
                        hierarchyNo = dto.getHierarchyNo();
                        tempHierarchyIndicator = levelDto.getHierarchyIndicator();
                        levelNumber = levelDto.getLevelNo();
                        treeLevelNo = levelDto.getTreeLevelNo();

                        if (Constant.INDICATOR_LOGIC_DEDUCTION_HIERARCHY.equals(dto.getHierarchyIndicator())) {
                            customerHierarchyNo = dto.getCustomerHierarchyNo();
                            productHierarchyNo = dto.getProductHierarchyNo();
                            deductionHierarchyNo = dto.getHierarchyNo();
                        } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(dto.getHierarchyIndicator())) {
                            customerHierarchyNo = dto.getHierarchyNo();
                            productHierarchyNo = dto.getProductHierarchyNo();
                            deductionHierarchyNo = dto.getDeductionHierarchyNo();
                        } else {
                            customerHierarchyNo = dto.getCustomerHierarchyNo();
                            productHierarchyNo = dto.getHierarchyNo();
                            deductionHierarchyNo = dto.getDeductionHierarchyNo();
                        }

                    } else {
                        levelNumber = levelDto.getLevelNo();
                        hierarchyNo = dto.getHierarchyNo();
                        treeLevelNo = levelNumber;
                        tempHierarchyIndicator = levelDto.getHierarchyIndicator();
                        LOGGER.debug(" This is not custom hierarchy child");
                    }
                }

            } else // For parent
            {
                if (currentHierarchy != null) {
                    LOGGER.debug(" This is parent");
                    LOGGER.debug(" level No= {} " , levelNo);
                    Leveldto levelDto = CommonLogic.getNextLevel(levelNo, currentHierarchy);

                    if (levelDto != null) {
                        tempHierarchyIndicator = levelDto.getHierarchyIndicator();
                        levelNumber = levelDto.getLevelNo();
                        if (isCustomHierarchy) {
                            treeLevelNo = levelDto.getTreeLevelNo();
                            customerHierarchyNo = StringUtils.EMPTY;
                            productHierarchyNo = StringUtils.EMPTY;
                            deductionHierarchyNo = StringUtils.EMPTY;
                        } else {
                            treeLevelNo = levelNumber;
                        }
                    }
                }
            }

            customDetailsList.add(levelNumber);
            customDetailsList.add(hierarchyNo);
            customDetailsList.add(treeLevelNo);
            LOGGER.debug(" Hierarchy No === {} " , hierarchyNo);
            LOGGER.debug(" level No === {}" , levelNumber);
            LOGGER.debug(" Hierarchy indicator === {}" , tempHierarchyIndicator);
            LOGGER.debug(" customTreeLevelNo === {}" , treeLevelNo);

            List<String> customViewDetails = new ArrayList<>();
            if (isCustomHierarchy) {
                String customerLevelNo;
                String productLevelNo;
                String deductionLevelNo;

                if (Constant.INDICATOR_LOGIC_DEDUCTION_HIERARCHY.equals(tempHierarchyIndicator)) {
                    customerLevelNo = Constant.PERCENT;
                    productLevelNo = Constant.PERCENT;
                    deductionLevelNo = String.valueOf(treeLevelNo);
                } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(tempHierarchyIndicator)) {
                    customerLevelNo = String.valueOf(treeLevelNo);
                    productLevelNo = Constant.PERCENT;
                    deductionLevelNo = Constant.PERCENT;
                } else {
                    customerLevelNo = Constant.PERCENT;
                    deductionLevelNo = Constant.PERCENT;
                    productLevelNo = String.valueOf(treeLevelNo);
                }

                LOGGER.debug(" Custom hierarchy --- \n customId= {} " , customId);
                LOGGER.debug(" customerLevelNo= {} " , customerLevelNo);
                LOGGER.debug(" customerHierarchyNo= {} " , customerHierarchyNo);
                LOGGER.debug(" productLevelNo= {} " , productLevelNo);
                LOGGER.debug(" productHierarchyNo= {} " , productHierarchyNo);
                customViewDetails.add(StringUtils.EMPTY + customId);
                customViewDetails.add(customerLevelNo);
                customViewDetails.add(customerHierarchyNo);
                customViewDetails.add(productLevelNo);
                customViewDetails.add(productHierarchyNo);
                customViewDetails.add(session.getCustRelationshipBuilderSid());
                customViewDetails.add(session.getProdRelationshipBuilderSid());
                customViewDetails.add(session.getDedRelationshipBuilderSid());
                customViewDetails.add(deductionLevelNo);
                customViewDetails.add(deductionHierarchyNo);
            }

            if (levelNumber != 0 && treeLevelNo != 0) {
                setMaxExpandLevelNo(treeLevelNo);
                if (isCustomHierarchy) {
                    return logicdisProjection.getDiscountCustomCount(session, tempHierarchyIndicator, levelNumber, userGroup,customViewDetails,isCustomHierarchy,customDetailsList);
                } else {

                    return logicdisProjection.getDiscountCount(session, hierarchyNo, treeLevelNo, tempHierarchyIndicator, isProgram, discountList, userGroup);
                }

            }
        } catch (Exception ex) {
            LOGGER.error("getCount -= {} " , ex);
        }
        return 0;
    }

    public LevelMap getLevelMap(int levelValue, int count, int levelIndex) {
        int page = getPageForItem(count + levelIndex + levelValue);
        int index = getItemIndex(count + levelIndex + levelValue);
        int start = getStartIndex(count + levelIndex, index);
        int end = getPageLength();
        return new LevelMap(start, end, page, getPageLength(), index, getColumnIdToFilterMap());
    }

    public int getPageForItem(int pos) {
        return ((pos - NumericConstants.TWO) / getPageLength()) + 1;
    }

    public int getItemIndex(int pos) {
        return (pos - NumericConstants.TWO) % getPageLength();
    }

    public int getStartIndex(int count, int index) {
        return count - index;
    }

    protected void recursivelyLoadExpandData(Object parentId, String treeLevel, int expandLevelNo) {
        int count = getLevelCount(parentId);
        LevelMap levelMap = new LevelMap(count, getColumnIdToFilterMap());
        addlevelMap(treeLevel, levelMap);

        if (expandLevelNo >= getMaxExpandLevelNo()) {

            List list = new ArrayList();
            int neededRecord = count;
            for (int j = 1; j <= count; j++) {
                String customTreeLevel = treeLevel + (j) + ".";
                Object ob = getCurrentPageData(customTreeLevel);
                if (ob != null) {
                    LOGGER.debug("data is there");
                    neededRecord--;
                    list.add(ob);
                }

            }
            if (neededRecord > 0) {
                List list1 = loadLevelData(parentId, count - neededRecord, neededRecord);
                list.addAll(list1);
            }

            int size = list.size();
            int index = count - size + 1;
            for (int j = 0; j < size; j++) {
                DiscountProjectionDTO dto = (DiscountProjectionDTO) (list.get(j));
                String customTreeLevel = treeLevel + (index + j) + ".";
                addExpandedTreeList(customTreeLevel, dto);
                recursivelyLoadExpandData(dto, customTreeLevel, expandLevelNo);
            }
        }
    }

    public void loadExpandData(int levelNo) {
        super.saveCurrentPage();
        recursivelyLoadExpandData(new Object(), StringUtils.EMPTY, levelNo);
        setRecordCount(getCalculatedTotalRecordCount());
        setCurrentPage(getTotalAmountOfPages());
    }

    @Override
    public Object configureContainer(Object object, Container datasource) {
        DiscountProjectionDTO dto = (DiscountProjectionDTO) object;
        ((ExtTreeContainer<DiscountProjectionDTO>) datasource).addBean(dto);
        if (dto.getTreeLevelNo() == lastLevelNo) {
            ((ExtTreeContainer<DiscountProjectionDTO>) datasource).setChildrenAllowed(dto, false);
        } else {
            ((ExtTreeContainer<DiscountProjectionDTO>) datasource).setChildrenAllowed(dto, isChildrenAllowed);
        }
        return dto;
    }

    @Override
    protected void saveCurrentPage() {
        LOGGER.debug(" Inside saveCurrentPage ");
        super.saveCurrentPage();
        if (nmDiscountProjection != null) {
            nmDiscountProjection.saveDiscountProjectionListview();
        }
        LOGGER.debug(" Exiting saveCurrentPage ");
    }

    public boolean isDataLoad() {
        return dataLoad;
    }

    public void setDataLoad(boolean dataLoad) {
        this.dataLoad = dataLoad;
    }

    @Override
    protected void createCurrentPageStart() {
        setCurrentPageProgress(true);
        setRefresh(BooleanConstant.getFalseFlag());
    }

    @Override
    protected void createCurrentPageEnd() {
        setCurrentPageProgress(false);
        setRefresh(BooleanConstant.getTrueFlag());
    }

    @Override
    protected void expandCollapseStart(boolean isExpand) {
        setExpandCollapseProgress(true);
    }

    @Override
    protected void expandCollapseEnd(boolean isExpand) {
        setExpandCollapseProgress(false);
    }

    public Object getExpandedTreeValues(String hierarchyNo) {
        return getExpandedTreeValue(hierarchyNo);
    }

    public void setHierarchyToRefresh(Set<String> finalHirarechyNo) {
        addExpandedTreeDataFetchable(finalHirarechyNo);
    }

    @Override
    public GtnSmallHashMap loadBulkData(GtnSmallHashMap bulkDataMap) {
        GtnSmallHashMap tempMap = new GtnSmallHashMap();
        List<String> hiearchyNoList = new ArrayList<>();
        for (int i = 0; i < bulkDataMap.size(); i++) {
            String tempLevelValue;
            DiscountProjectionDTO dto = (DiscountProjectionDTO) bulkDataMap.getIndex(i).getValue();
            tempLevelValue = dto.getHierarchyNo();
            tempMap.put(tempLevelValue, bulkDataMap.getIndex(i).getKey());
            hiearchyNoList.add(tempLevelValue);
        }

        String hierarchyNumbers = CommonUtils.collectionToStringMethod(hiearchyNoList, true);

        List<String> customViewDetails = new ArrayList<>();
        List customDetailsList = new ArrayList();
        customDetailsList.add(0);
        customDetailsList.add(StringUtils.EMPTY);
        customDetailsList.add(0);
        boolean isCustomHierarchyBulkData = !CommonUtil.isValueEligibleForLoading() ? Constants.IndicatorConstants.INDICATOR_LOGIC_CUSTOM_HIERARCHY.getConstant().equals(hierarchyIndicator) : Constant.INDICATOR_LOGIC_DEDUCTION_HIERARCHY.equals(hierarchyIndicator);
        List<DiscountProjectionDTO> refreshedDataList = logic.getDiscountProjection(session, frequency, startAndEndPeriods,
                history, hierarchyIndicator, projectionPeriodorder, userGroup,
                isProgram, discountList, year,
                customDetailsList, BooleanConstant.getTrueFlag(), isCustomHierarchyBulkData, rightDto, 0, 0, BooleanConstant.getFalseFlag(), BooleanConstant.getFalseFlag(), 
                customViewDetails, BooleanConstant.getFalseFlag(), BooleanConstant.getTrueFlag(), hierarchyNumbers,
                relationshipBuilderSid, false, Collections.emptyList(), false, StringUtils.EMPTY, StringUtils.EMPTY, Collections.emptyList(), new HashMap<String, String>(), forecastConfigList, projectionSelection);

        for (DiscountProjectionDTO dto : refreshedDataList) {
            bulkDataMap.put(tempMap.get(dto.getHierarchyNo()), dto);

        }
        return bulkDataMap;
    }

    public void forRefresh(Set<String> hierachyNos) {
        addExpandedTreeDataFetchable(hierachyNos);
    }

    @Override
    public void setColumnIdToFilterValue(Object prop, Object value) {
        super.setColumnIdToFilterValue(prop, value);
        Object groupDdlb = getColumnIdToFilterValue(Constant.GROUP);
        projectionSelection = getProjectionSelection();
        if (groupDdlb != null) {
            if ((projectionSelection != null) && (projectionSelection.isIsCustomHierarchy() || !projectionSelection.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY))) {
                projectionSelection.setGroupFilter(String.valueOf(groupDdlb));
            }
            clearAll();
        }
    }

    public ProjectionSelectionDTO getProjectionSelection() {
        return projectionSelection;
    }

    public void setProjectionSelection(ProjectionSelectionDTO projectionSelection) {
        this.projectionSelection = projectionSelection;
    }

    public CustomTableHeaderDTO getRightDto() {
        return rightDto;
    }

    public String getFrequency() {
        return frequency;
    }

    public List<String> getDiscountList() {
        return discountList == null ? discountList : new ArrayList<>(discountList);
    }

    public List<Integer> getStartAndEndPeriods() {
        return startAndEndPeriods == null ? startAndEndPeriods : new ArrayList<>(startAndEndPeriods);
    }

    public int getCustomId() {
        return customId;
    }

    public String getRelationshipBuilderSid() {
        return relationshipBuilderSid;
    }

    public List<Leveldto> getCurrentHierarchy() {
        return currentHierarchy == null ? currentHierarchy : new ArrayList<>(currentHierarchy);
    }

    public boolean isIsCustomHierarchy() {
        return isCustomHierarchy;
    }

    public String getHierarchyIndicator() {
        return hierarchyIndicator;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public int getLevelNo() {
        return levelNo;
    }

    /**
     * To set the Hierarchy to refresh
     *
     * @param tableLevelNo
     * @param b
     */
    public void setRefreshHierarchyNo(String hierarchyNo) {
        this.refreshHierarchyNumbers = hierarchyNo;
    }

}
