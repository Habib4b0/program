/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.projectionvariance.logic.tablelogic;

import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.app.galforecasting.dto.PVSelectionDTO;
import com.stpl.app.galforecasting.dto.ProjectionVarianceDTO;
import com.stpl.app.galforecasting.logic.CommonLogic;
import com.stpl.app.galforecasting.projectionvariance.logic.MProjectionVarianceLogic;
import com.stpl.app.galforecasting.projectionvariance.logic.NMProjectionVarianceLogic;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.vaadin.data.Container;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.asi.ui.extfilteringtable.paged.logic.PageTreeTableLogic;
import org.drools.core.util.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author Jayaram
 */
public class ProjectionVarianceTableLogic extends PageTreeTableLogic {

    List<Leveldto> currentHierarchy = new ArrayList<Leveldto>();
    PVSelectionDTO projSelDTO = new PVSelectionDTO();
    PVSelectionDTO baseVariables = new PVSelectionDTO();
    int levelNo;
    String hierarchyNo;
    boolean firstGenerated = false;
    boolean isChild = false;
    public static final Logger LOGGER = Logger.getLogger(ProjectionVarianceTableLogic.class);
    private String screenName = StringUtils.EMPTY;
    String productHierarchyNo = StringUtils.EMPTY;
    String customerHierarchyNo = StringUtils.EMPTY;
    NMProjectionVarianceLogic nmProjectionVarianceLogic = new NMProjectionVarianceLogic();
    MProjectionVarianceLogic mProjectionVarianceLogic = new MProjectionVarianceLogic();

    /**
     * Load date method for loading container based on different modules
     *
     * @param start
     * @param offset
     * @return
     */
    @Override
    public Map<Integer, Object> loadData(int start, int offset) {
        LOGGER.info("loadData initiated with firstGenerated=" + firstGenerated + " and start=" + start + " and offset=" + offset);
        Map<Integer, Object> map = new HashMap<Integer, Object>();
        if (firstGenerated && offset > 0) {
            List<String> indexList = new ArrayList<String>(getNonFetchableData().keySet());
            projSelDTO.setNonFetchableIndex(indexList);
            List<ProjectionVarianceDTO> list = null;
            if (!StringUtils.EMPTY.equals(getScreenName()) && getScreenName().equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
                list = nmProjectionVarianceLogic.getConfiguredProjectionVariance(getLastParent(), projSelDTO, baseVariables, start, offset);
            } else if (!StringUtils.EMPTY.equals(getScreenName()) && getScreenName().equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {
                list = mProjectionVarianceLogic.getConfiguredProjectionVariance(getLastParent(), projSelDTO, start, offset);
            }
            int i = start;
            for (ProjectionVarianceDTO dto : list) {
                map.put(i, dto);
                i++;
            }
            projSelDTO.clearNonFetchableIndex();
        }
        LOGGER.info("loadData ended map size="+map.size());
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
                count = nmProjectionVarianceLogic.getConfiguredProjectionVarianceCount(getLastParent(), projSelDTO, baseVariables, true);
            } else if (!StringUtils.EMPTY.equals(getScreenName()) && getScreenName().equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {
                count = mProjectionVarianceLogic.getConfiguredProjectionVarianceCount(getLastParent(), projSelDTO, true);
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
        ((CustomTreeContainer<ProjectionVarianceDTO>) datasource).addBean(dto);
        if (dto.getParent() == 1) {
            ((CustomTreeContainer<ProjectionVarianceDTO>) datasource).setChildrenAllowed(dto, true);
        } else {
            ((CustomTreeContainer<ProjectionVarianceDTO>) datasource).setChildrenAllowed(dto, false);
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
    public void setProjectionResultsData(List<Leveldto> currentHierarchy, PVSelectionDTO projSelDTO, int levelNo, String hierarchyNo, PVSelectionDTO baseVariables) {
        this.currentHierarchy = currentHierarchy;
        this.projSelDTO = projSelDTO;
        this.levelNo = levelNo;
        this.hierarchyNo = hierarchyNo;
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
    public void setProjectionResultsData(List<Leveldto> currentHierarchy, PVSelectionDTO projSelDTO, int levelNo, String hierarchyNo) {
        LOGGER.info("setProjectionResultsData Starts");
        this.currentHierarchy = currentHierarchy;
        this.projSelDTO = projSelDTO;
        this.levelNo = levelNo;
        this.hierarchyNo = hierarchyNo;
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
        int curPage = ((pos - 2) / getPageLength()) + 1;
        return curPage;
    }

    public int getItemIndex(int pos) {
        int index = (pos - 2) % getPageLength();
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
        setRefresh(Boolean.FALSE);
    }

    @Override
    protected void createCurrentPageEnd() {
        setCurrentPageProgress(false);
        setRefresh(Boolean.TRUE);
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
                count = new NMProjectionVarianceLogic().getConfiguredProjectionVarianceCount(parentId, projSelDTO, baseVariables, true);
                LevelMap levelMap = new LevelMap(count, getColumnIdToFilterMap());
                addlevelMap(treeLevel, levelMap);
                productHierarchyNo = projSelDTO.getProductHierarchyNo();
                customerHierarchyNo = projSelDTO.getCustomerHierarchyNo();
                String hierarchyNo = projSelDTO.getHierarchyNo();
                int levelNo = projSelDTO.getTreeLevelNo();
                if (expandLevelNo >= levelNo) {
                    if (projSelDTO.isGroupCount()) {
                        String customTreeLevel = treeLevel + count+".";
                        ProjectionVarianceDTO dto = new ProjectionVarianceDTO();
                        dto.setLevelNo(projSelDTO.getLevelNo());
                        dto.setTreeLevelNo(projSelDTO.getTreeLevelNo());
                        dto.setParentNode(projSelDTO.getParentNode());
                        dto.setLevelValue(projSelDTO.getLevelValue());
                        dto.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
                        dto.setHierarchyNo(hierarchyNo);
                        if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                            dto.setCustomerHierarchyNo(dto.getHierarchyNo());
                            dto.setProductHierarchyNo(productHierarchyNo);
                        } else if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                            dto.setProductHierarchyNo(dto.getHierarchyNo());
                            dto.setCustomerHierarchyNo(customerHierarchyNo);
                        }
                        dto.setGroup(projSelDTO.getGroupFilter());
                        dto.setGroupParent(1);
                        dto.setParent(1);
                        projSelDTO.setGroupCount(false);
                        addExpandedTreeList(customTreeLevel, dto);
                        recursivelyLoadExpandData(dto, customTreeLevel, expandLevelNo);
                    } else {
                        if (projSelDTO.getLevelCount() != 0) {
                            levelList = CommonLogic.getConditionalLevelList(projSelDTO.getProjectionId(), 0, projSelDTO.getLevelCount(), projSelDTO.getHierarchyIndicator(), levelNo, hierarchyNo, productHierarchyNo, customerHierarchyNo, false, false, projSelDTO.isIsCustomHierarchy(), projSelDTO.getCustomId(), projSelDTO.getGroupFilter(), projSelDTO.getUserId(), projSelDTO.getSessionId(), projSelDTO.getCustRelationshipBuilderSid(), projSelDTO.getProdRelationshipBuilderSid(), false, true);
                            customizeResult(levelList, count, treeLevel, expandLevelNo, true);
                        }
                    }
                }
            } else if (CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(getScreenName())) {
                count = new MProjectionVarianceLogic().getConfiguredProjectionVarianceCount(parentId, projSelDTO, true);
                LevelMap levelMap = new LevelMap(count, getColumnIdToFilterMap());
                addlevelMap(treeLevel, levelMap);
                productHierarchyNo = projSelDTO.getProductHierarchyNo();
                customerHierarchyNo = projSelDTO.getCustomerHierarchyNo();
                String hierarchyNo = projSelDTO.getHierarchyNo();
                int levelNo = projSelDTO.getTreeLevelNo();
                if (expandLevelNo >= levelNo) {
                    levelList = new MProjectionVarianceLogic().getConditionalLevelList(projSelDTO.getSession().getProjectionId(), 0, projSelDTO.getLevelCount(), projSelDTO.getHierarchyIndicator(), levelNo, hierarchyNo, productHierarchyNo, customerHierarchyNo, false, false, projSelDTO.isIsCustomHierarchy(), projSelDTO.getCustomId(), projSelDTO);
                    customizeResult(levelList, count, treeLevel, expandLevelNo, true);
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Common for all three modules
     *
     * @param levelList
     * @param count
     * @param treeLevel
     * @param expandLevelNo
     * @param flag
     * @throws Exception
     */
    private void customizeResult(List<Leveldto> levelList, int count, String treeLevel, int expandLevelNo, boolean flag) throws Exception {
        LOGGER.info("Inside customizeResult with levelList size=== = = = "+levelList.size());
        int size = levelList != null ? levelList.size() : 0;
        int index = count - size+1;
        for (int j = 0; j < size; j++) {
            Leveldto levelDto = levelList.get(j);
            String customTreeLevel = treeLevel + (index+j) + ".";
            ProjectionVarianceDTO dto = new ProjectionVarianceDTO();
            dto.setLevelNo(flag ? levelDto.getLevelNo() : projSelDTO.getLevelNo());
            dto.setTreeLevelNo(flag ? levelDto.getTreeLevelNo() : projSelDTO.getTreeLevelNo());
            dto.setParentNode(flag ? levelDto.getParentNode() : projSelDTO.getParentNode());
            dto.setLevelValue(flag ? levelDto.getRelationshipLevelValue() : projSelDTO.getLevelValue());
            dto.setHierarchyIndicator(flag ? levelDto.getHierarchyIndicator() : projSelDTO.getHierarchyIndicator());
            dto.setHierarchyNo(flag ? levelDto.getHierarchyNo() : hierarchyNo);
            if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                dto.setCustomerHierarchyNo(dto.getHierarchyNo());
                dto.setProductHierarchyNo(productHierarchyNo);
            } else if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                dto.setProductHierarchyNo(dto.getHierarchyNo());
                dto.setCustomerHierarchyNo(customerHierarchyNo);
            }
            if (CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(getScreenName())) {
                dto.setOnExpandTotalRow(1);
                dto.setRelationshipLevelName(levelDto.getRelationshipLevelName());
                dto.setGroup(levelDto.getRelationshipLevelName());
            } else {
                dto.setGroup(flag ? projSelDTO.getSessionDTO().getLevelValueDiscription(levelDto.getHierarchyNo(), levelDto.getHierarchyIndicator()) : projSelDTO.getGroupFilter());
            }
            dto.setParent(1);
            addExpandedTreeList(customTreeLevel, dto);
            recursivelyLoadExpandData(dto, customTreeLevel, expandLevelNo);
        }
        LOGGER.info("Ending customizeResult");
    }
    
    void customize(List<Leveldto> levelList,String treeLevel, int expandLevelNo, boolean flag, int count, Leveldto levelDto){
            String customTreeLevel = treeLevel + (count) + ".";
            ProjectionVarianceDTO dto = new ProjectionVarianceDTO();
            dto.setLevelNo(flag ? levelDto.getLevelNo() : projSelDTO.getLevelNo());
            dto.setTreeLevelNo(flag ? levelDto.getTreeLevelNo() : projSelDTO.getTreeLevelNo());
            dto.setParentNode(flag ? levelDto.getParentNode() : projSelDTO.getParentNode());
            dto.setLevelValue(flag ? levelDto.getRelationshipLevelValue() : projSelDTO.getLevelValue());
            dto.setHierarchyIndicator(flag ? levelDto.getHierarchyIndicator() : projSelDTO.getHierarchyIndicator());
             dto.setHierarchyNo(flag ? levelDto.getHierarchyNo() : hierarchyNo);
            if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                dto.setCustomerHierarchyNo(dto.getHierarchyNo());
                dto.setProductHierarchyNo(productHierarchyNo);
            } else if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                dto.setProductHierarchyNo(dto.getHierarchyNo());
                dto.setCustomerHierarchyNo(customerHierarchyNo);
            }
            if (CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(getScreenName())) {
                dto.setOnExpandTotalRow(1);
                dto.setRelationshipLevelName(levelDto.getRelationshipLevelName());
                dto.setGroup(levelDto.getRelationshipLevelName());
            } else {
                dto.setGroup(flag ? projSelDTO.getSessionDTO().getLevelValueDiscription(levelDto.getHierarchyNo(), levelDto.getHierarchyIndicator()) : projSelDTO.getGroupFilter());
            }
            dto.setParent(1);
            addExpandedTreeList(customTreeLevel, dto);
            recursivelyLoadExpandData(dto, customTreeLevel, expandLevelNo);
    }
    
    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

   
    
}
