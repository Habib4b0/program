
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.lazyLoad;
 
import com.stpl.app.cff.dto.PVSelectionDTO;
import com.stpl.app.cff.logic.CommonLogic;
import com.stpl.app.cff.ui.projectionVariance.dto.ProjectionVarianceDTO;
import com.stpl.app.cff.ui.projectionVariance.logic.ProjectionVarianceLogic;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.data.Container;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.paged.logic.PageTreeTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author manikandaprabu.g
 */
public class VarianceTableLogic extends PageTreeTableLogic{

    List<Leveldto> currentHierarchy = new ArrayList<Leveldto>();
    PVSelectionDTO projSelDTO = new PVSelectionDTO();
    int levelNo;
    String hierarchyNo;
    boolean firstGenerated = false;
    boolean isChild = false;
    public static final Logger LOGGER = Logger.getLogger(VarianceTableLogic.class);
    private String screenName = StringUtils.EMPTY;
    String productHierarchyNo = StringUtils.EMPTY;
    String customerHierarchyNo = StringUtils.EMPTY;
    ProjectionVarianceLogic nmProjectionVarianceLogic = new ProjectionVarianceLogic();
    /**
     * Load date method for loading container based on different modules
     *
     * @param start
     * @param offset
     * @return
     */
    @Override
    public Map<Integer, Object> loadData(int start, int offset) {
        LOGGER.debug("loadData initiated with firstGenerated=" + firstGenerated + " and start=" + start + " and offset=" + offset);
        Map<Integer, Object> map = new HashMap<>();
        if (firstGenerated && offset > 0) {
            List<String> indexList = new ArrayList<>(getNonFetchableData().keySet());
            projSelDTO.setNonFetchableIndex(indexList);
            List<ProjectionVarianceDTO> list = null;
            list = nmProjectionVarianceLogic.getConfiguredProjectionVariance(getLastParent(), projSelDTO, start, offset);
            LOGGER.debug("list in load" + list.size());
            int i = start;
            for (ProjectionVarianceDTO dto : list) {
                map.put(i, dto);
                i++;
            }
            projSelDTO.clearNonFetchableIndex();
        }
        LOGGER.debug("loadData ended");
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
            count = nmProjectionVarianceLogic.getConfiguredProjectionVarianceCount(getLastParent(), projSelDTO, true);
        }
        LOGGER.debug("Count - - - - " + count);
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
        Object groupDdlb = getColumnIdToFilterValue("group");
        if (groupDdlb != null) {
            if (projSelDTO.isIsCustomHierarchy() || !projSelDTO.getHierarchyIndicator().equals("P")) {
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
     */
    public void setProjectionResultsData(List<Leveldto> currentHierarchy, PVSelectionDTO projSelDTO, int levelNo, String hierarchyNo) {
        LOGGER.debug("setProjectionResultsData Starts");
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
        recursivelyLoadExpandData(new Object(), "", levelNo);
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
            count = new ProjectionVarianceLogic().getConfiguredProjectionVarianceCount(parentId, projSelDTO, true);
            LevelMap levelMap = new LevelMap(count, getColumnIdToFilterMap());
            addlevelMap(treeLevel, levelMap);
            String productHierarchyNo = projSelDTO.getProductHierarchyNo();
            String customerHierarchyNo = projSelDTO.getCustomerHierarchyNo();
            String hierarchyNo = projSelDTO.getHierarchyNo();
            int levelNo = projSelDTO.getTreeLevelNo();
            String indicator = projSelDTO.getHierarchyIndicator();
            if (expandLevelNo >= levelNo) {
                if (projSelDTO.isGroupCount()) {
                    customizeResult(levelList, count, treeLevel, expandLevelNo, false);
                } else if (projSelDTO.getLevelCount() != 0) {
                        CommonLogic commonLogic = new CommonLogic();
                        
                        if (projSelDTO.isIsCustomHierarchy()) {
                            String hierarchyIndicator = commonLogic.getHiearchyIndicatorFromCustomView(projSelDTO);
                            Map<String, List> relationshipLevelDetailsMap = projSelDTO.getSessionDTO().getHierarchyLevelDetails();
                            List<String> list = commonLogic.getHiearchyNoForCustomView(projSelDTO, 0, projSelDTO.getLevelCount());

                            int size = list.size();
                            int index = count - size + 1;
                            for (int j = 0; j < size; j++) {
                                projSelDTO.setProductHierarchyNo(productHierarchyNo);
                                projSelDTO.setCustomerHierarchyNo(customerHierarchyNo);
                                projSelDTO.setHierarchyNo(hierarchyNo);
                                projSelDTO.setHierarchyIndicator(indicator);
                                projSelDTO.setTreeLevelNo(levelNo);
                                String customTreeLevel = treeLevel + (index + j) + ".";
                                ProjectionVarianceDTO dto = new ProjectionVarianceLogic().configureDetailsInDTO(projSelDTO, list.get(j), hierarchyIndicator, projSelDTO.getTreeLevelNo(), relationshipLevelDetailsMap.get(list.get(j)));
                                addExpandedTreeList(customTreeLevel, dto);
                                recursivelyLoadExpandData(dto, customTreeLevel, expandLevelNo);
                            }
                } else {
                            Map<String, List> relationshipLevelDetailsMap = projSelDTO.getSessionDTO().getHierarchyLevelDetails();
                            List<String> list = commonLogic.getHiearchyNoAsList(projSelDTO, 0, projSelDTO.getLevelCount());
                            int size = list.size();
                            int index = count - size + 1;
                            for (int j = 0; j < size; j++) {
                                projSelDTO.setProductHierarchyNo(productHierarchyNo);
                                projSelDTO.setCustomerHierarchyNo(customerHierarchyNo);
                                projSelDTO.setHierarchyNo(hierarchyNo);
                                projSelDTO.setHierarchyIndicator(indicator);
                                projSelDTO.setTreeLevelNo(levelNo);
                                String customTreeLevel = treeLevel + (index + j) + ".";
                                ProjectionVarianceDTO dto = new ProjectionVarianceLogic().configureDetailsInDTO(projSelDTO, list.get(j), projSelDTO.getHierarchyIndicator(), Integer.valueOf(relationshipLevelDetailsMap.get(list.get(j)).get(NumericConstants.TWO).toString()), relationshipLevelDetailsMap.get(list.get(j)));
                                addExpandedTreeList(customTreeLevel, dto);
                                recursivelyLoadExpandData(dto, customTreeLevel, expandLevelNo);
                }
            }
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
    private void customizeResult(List<Leveldto> levelList, int count, String treeLevel, int expandLevelNo, boolean flag) {
        LOGGER.debug("Inside customizeResult");
        int size = levelList != null ? levelList.size() : 0;
        int index = count - size + 1;
        for (int j = 0; j < size; j++) {
            Leveldto levelDto = levelList.get(j);
            String customTreeLevel = treeLevel + (index + j) + ".";
            ProjectionVarianceDTO dto = new ProjectionVarianceDTO();
            dto.setLevelNo(flag ? levelDto.getLevelNo() : projSelDTO.getLevelNo());
            dto.setTreeLevelNo(flag ? levelDto.getTreeLevelNo() : projSelDTO.getTreeLevelNo());
            dto.setParentNode(flag ? levelDto.getParentNode() : projSelDTO.getParentNode());
            dto.setLevelValue(flag ? levelDto.getRelationshipLevelValue() : projSelDTO.getLevelValue());
            dto.setHierarchyIndicator(flag ? levelDto.getHierarchyIndicator() : projSelDTO.getHierarchyIndicator());
            dto.setHierarchyNo(flag ? levelDto.getHierarchyNo() : hierarchyNo);
            if (dto.getHierarchyIndicator().equals("C")) {
                dto.setCustomerHierarchyNo(dto.getHierarchyNo());
                dto.setProductHierarchyNo(productHierarchyNo);
            } else if (dto.getHierarchyIndicator().equals("P")) {
                dto.setProductHierarchyNo(dto.getHierarchyNo());
                dto.setCustomerHierarchyNo(customerHierarchyNo);
            }
            dto.setGroup(flag ? projSelDTO.getSessionDTO().getLevelValueDiscription(levelDto.getHierarchyNo(), levelDto.getHierarchyIndicator()) : projSelDTO.getGroupFilter());
            dto.setParent(1);
            addExpandedTreeList(customTreeLevel, dto);
            recursivelyLoadExpandData(dto, customTreeLevel, expandLevelNo);
        }
        LOGGER.debug("Ending customizeResult");
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }
}
