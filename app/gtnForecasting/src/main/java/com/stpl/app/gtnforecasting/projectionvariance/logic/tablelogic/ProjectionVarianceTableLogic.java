/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.projectionvariance.logic.tablelogic;

import com.stpl.app.gtnforecasting.dto.PVSelectionDTO;
import com.stpl.app.gtnforecasting.dto.ProjectionVarianceDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.projectionvariance.logic.NMProjectionVarianceLogic;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.ifs.ui.extfilteringtable.PageTreeTableLogic;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.GtnSmallHashMap;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.v7.data.Container;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtTreeContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author Jayaram
 */
public class ProjectionVarianceTableLogic extends PageTreeTableLogic {

    
    protected List<Leveldto> currentHierarchy = new ArrayList<>();
    protected PVSelectionDTO projSelDTO = new PVSelectionDTO();
    protected PVSelectionDTO baseVariables = new PVSelectionDTO();
    protected int levelNo;
    protected boolean firstGenerated = false;
    protected boolean isChild = false;
    public static final Logger LOGGER = LoggerFactory.getLogger(ProjectionVarianceTableLogic.class);
    private String screenName = StringUtils.EMPTY;
    protected NMProjectionVarianceLogic nmProjectionVarianceLogic = new NMProjectionVarianceLogic();

    /**
     * Load date method for loading container based on different modules
     *
     * @param start
     * @param offset
     * @return
     */
    @Override
    public GtnSmallHashMap loadData(int start, int offset) {
        LOGGER.debug("loadData initiated with firstGenerated= {}, and start= {}, and offset= {}" , firstGenerated, start, offset);
        GtnSmallHashMap map = new GtnSmallHashMap();
        if (firstGenerated && offset > 0) {
            List<String> indexList = new ArrayList<>();
            for (int i = 0; i < getNonFetchableData().size(); i++) {
                indexList.add(getNonFetchableData().getIndex(i).getKey().toString());
            }
            projSelDTO.setNonFetchableIndex(indexList);
            List<ProjectionVarianceDTO> list = null;
            if (!StringUtils.EMPTY.equals(getScreenName()) && getScreenName().equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
                list = nmProjectionVarianceLogic.getConfiguredProjectionVariance(getLastParent(), projSelDTO, baseVariables, start, offset);
            }
            int i = start;
            if (list != null) {
                for (ProjectionVarianceDTO dto : list) {
                    map.put(i, dto);
                    i++;
                }
            }
            projSelDTO.clearNonFetchableIndex();
        }

        LOGGER.debug("loadData ended map size= {}" , map.size());

        return map;
    }

    /**
     * Count method for returning the count of the records based on modules
     *
     * @return
     */
    @Override
    public int getCount() {
        int count = 0;
        if (firstGenerated) {
            if (!StringUtils.EMPTY.equals(getScreenName()) && getScreenName().equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
                projSelDTO.setTabName("Variance");
                count = nmProjectionVarianceLogic.getConfiguredProjectionVarianceCount(getLastParent(), projSelDTO, baseVariables, true);
            }
        }
        return count;
    }

    /**
     * Configure Container for loading the result into table
     *
     * @param object
     * @param datasource
     * @return
     */
    @Override
    public Object configureContainer(Object object, Container datasource) {
        ProjectionVarianceDTO dto = (ProjectionVarianceDTO) object;
        ((ExtTreeContainer<ProjectionVarianceDTO>) datasource).addBean(dto);
        if (dto.getParent() == 1) {
            ((ExtTreeContainer<ProjectionVarianceDTO>) datasource).setChildrenAllowed(dto, true);
        } else {
            ((ExtTreeContainer<ProjectionVarianceDTO>) datasource).setChildrenAllowed(dto, false);
        }
        return dto;
    }

    @Override
    public void setColumnIdToFilterValue(Object prop, Object value) {
        super.setColumnIdToFilterValue(prop, value);
        Object groupDdlb = getColumnIdToFilterValue(Constant.GROUP);
        if (groupDdlb != null) {
            if (projSelDTO.isIsCustomHierarchy() || !projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                projSelDTO.setGroupFilter(String.valueOf(groupDdlb));
            }

            clearAll();
        }
    }

    /**
     * Method for reloading the table
     *
     * @param currentHierarchy
     * @param projSelDTO
     * @param levelNo
     * @param hierarchyNo
     * @param baseVariables
     */
    public void setProjectionResultsData(List<Leveldto> currentHierarchy, PVSelectionDTO projSelDTO, int levelNo,PVSelectionDTO baseVariables) {
        this.currentHierarchy = currentHierarchy;
        this.projSelDTO = projSelDTO;
        this.levelNo = levelNo;
        this.baseVariables = baseVariables;
        firstGenerated = true;
        clearAll();
        setCurrentPage(1);
    }

    /**
     * Method for reloading the table
     *
     * @param currentHierarchy
     * @param projSelDTO
     * @param levelNo
     * @param hierarchyNo
     */
    public void setProjectionResultsData(List<Leveldto> currentHierarchy, PVSelectionDTO projSelDTO, int levelNo) {
        LOGGER.debug("setProjectionResultsData Starts");
        this.currentHierarchy = currentHierarchy;
        this.projSelDTO = projSelDTO;
        this.levelNo = levelNo;
        clearAll();
        firstGenerated = true;
        setCurrentPage(1);
    }

    /**
     * Method for reloading the table
     *
     * @param projSelDTO
     */
    public void setProjectionResultsData(PVSelectionDTO projSelDTO) {
        this.projSelDTO = projSelDTO;
        firstGenerated = true;
        clearAll();
        setCurrentPage(1);
    }

    public LevelMap getLevelMap(int levelValue, int count, int levelIndex) {
        int page = getPageForItem(count + levelIndex + levelValue);
        int index = getItemIndex(count + levelIndex + levelValue);
        int start = getStartIndex(count + levelIndex, index);
        int end = getPageLength();
        LevelMap levelMap = new LevelMap(start, end, page, getPageLength(), index, getColumnIdToFilterMap());
        return levelMap;
    }

    public int getPageForItem(int pos) {
        int curPage = ((pos - NumericConstants.TWO) / getPageLength()) + 1;
        return curPage;
    }

    public int getItemIndex(int pos) {
        int index = (pos - NumericConstants.TWO) % getPageLength();
        return index;
    }

    public int getStartIndex(int count, int index) {
        int start = count - index;
        return start;
    }

    public void loadExpandData(int levelNo) {
        recursivelyLoadExpandData(new Object(), StringUtils.EMPTY, levelNo);
        setRecordCount(getCalculatedTotalRecordCount());
        setCurrentPage(getTotalAmountOfPages());
    }

    public void groupChange() {
        clearAll();
        setCurrentPage(1);
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

    /**
     * Method for loading data for expanded row
     *
     * @param parentId
     * @param treeLevel
     * @param expandLevelNo
     */
    protected void recursivelyLoadExpandData(Object parentId, String treeLevel, int expandLevelNo) {
        try {
            int count = 0;
            List<Leveldto> levelList = null;
            if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(getScreenName())) {
                count = nmProjectionVarianceLogic.getConfiguredProjectionVarianceCount(parentId, projSelDTO, baseVariables, true);
                LevelMap levelMap = new LevelMap(count, getColumnIdToFilterMap());
                addlevelMap(treeLevel, levelMap);
                String productHierarchyNo = projSelDTO.getProductHierarchyNo();
                String customerHierarchyNo = projSelDTO.getCustomerHierarchyNo();
                String deductionHierarchyNo = projSelDTO.getDeductionHierarchyNo();
                String hierarchyNo = projSelDTO.getHierarchyNo();

                String indicator = projSelDTO.getHierarchyIndicator();
                int levelNoExpandData = projSelDTO.getTreeLevelNo();
                if (expandLevelNo >= levelNoExpandData) {
                    if (projSelDTO.isGroupCount()) {
                        String customTreeLevel = treeLevel + count + ".";
                        ProjectionVarianceDTO dto = new ProjectionVarianceDTO();
                        dto.setLevelNo(projSelDTO.getLevelNo());
                        dto.setTreeLevelNo(projSelDTO.getTreeLevelNo());
                        dto.setParentNode(projSelDTO.getParentNode());
                        dto.setLevelValue(projSelDTO.getLevelValue());
                        dto.setHierarchyIndicator(indicator);
                        dto.setHierarchyNo(hierarchyNo);
                        if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                            dto.setCustomerHierarchyNo(dto.getHierarchyNo());
                            dto.setProductHierarchyNo(productHierarchyNo);
                            dto.setDeductionHierarchyNo(deductionHierarchyNo);
                        } else if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                            dto.setProductHierarchyNo(dto.getHierarchyNo());
                            dto.setCustomerHierarchyNo(customerHierarchyNo);
                            dto.setDeductionHierarchyNo(deductionHierarchyNo);
                        } else{
                            dto.setProductHierarchyNo(productHierarchyNo);
                            dto.setCustomerHierarchyNo(customerHierarchyNo);
                            dto.setDeductionHierarchyNo(dto.getHierarchyNo());
                        }
                        dto.setGroup(projSelDTO.getGroupFilter());
                        dto.setGroupParent(1);
                        dto.setParent(1);
                        projSelDTO.setGroupCount(false);
                        addExpandedTreeList(customTreeLevel, dto);
                        recursivelyLoadExpandData(dto, customTreeLevel, expandLevelNo);
                    } else if (projSelDTO.getLevelCount() != 0) {
                        CommonLogic commonLogic = new CommonLogic();
                        
                        if (projSelDTO.isIsCustomHierarchy()) {
                            String hierarchyIndicator = commonLogic.getHiearchyIndicatorFromCustomView(projSelDTO);
                            Map<String, List> relationshipLevelDetailsMap = projSelDTO.getSessionDTO().getDiscountHierarchyLevelDetails();
                            List<String> list = nmProjectionVarianceLogic.getHiearchyNoForCustomView(projSelDTO, 0, projSelDTO.getLevelCount());

                            int size = list.size();
                            int index = count - size + 1;
                            for (int j = 0; j < size; j++) {
                                projSelDTO.setProductHierarchyNo(productHierarchyNo);
                                projSelDTO.setCustomerHierarchyNo(customerHierarchyNo);
                                projSelDTO.setDeductionHierarchyNo(deductionHierarchyNo);
                                projSelDTO.setHierarchyNo(hierarchyNo);
                                projSelDTO.setHierarchyIndicator(indicator);
                                projSelDTO.setTreeLevelNo(levelNoExpandData);
                                String customTreeLevel = treeLevel + (index + j) + ".";
                                ProjectionVarianceDTO dto = nmProjectionVarianceLogic.configureDetailsInDTO(projSelDTO, list.get(j), hierarchyIndicator, projSelDTO.getTreeLevelNo(), relationshipLevelDetailsMap.get(list.get(j)));
                                addExpandedTreeList(customTreeLevel, dto);
                                recursivelyLoadExpandData(dto, customTreeLevel, expandLevelNo);
                            }

                        } else {
                            Map<String, List> relationshipLevelDetailsMap = projSelDTO.getSessionDTO().getHierarchyLevelDetails();
                            List<String> list =  nmProjectionVarianceLogic.getHiearchyNoAsList(projSelDTO, 0, projSelDTO.getLevelCount());
                            int size = list.size();
                            int index = count - size + 1;
                            for (int j = 0; j < size; j++) {
                                projSelDTO.setProductHierarchyNo(productHierarchyNo);
                                projSelDTO.setCustomerHierarchyNo(customerHierarchyNo);
                                projSelDTO.setHierarchyNo(hierarchyNo);
                                projSelDTO.setHierarchyIndicator(indicator);
                                projSelDTO.setTreeLevelNo(levelNoExpandData);
                                String customTreeLevel = treeLevel + (index + j) + ".";
                                ProjectionVarianceDTO dto = nmProjectionVarianceLogic.configureDetailsInDTO(projSelDTO, list.get(j), projSelDTO.getHierarchyIndicator(), Integer.parseInt(relationshipLevelDetailsMap.get(list.get(j)).get(NumericConstants.TWO).toString()), relationshipLevelDetailsMap.get(list.get(j)));
                                addExpandedTreeList(customTreeLevel, dto);
                                recursivelyLoadExpandData(dto, customTreeLevel, expandLevelNo);
                            }
                        }
                    }
                }
            }
        } catch (NumberFormatException ex) {
            LOGGER.error(ex.getMessage());
        }
    }


    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    }
