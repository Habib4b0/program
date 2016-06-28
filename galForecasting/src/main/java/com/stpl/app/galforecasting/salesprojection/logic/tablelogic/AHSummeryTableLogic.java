/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.salesprojection.logic.tablelogic;

import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.app.galforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.dto.SalesRowDto;
import com.stpl.app.galforecasting.salesprojection.logic.SalesLogic;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.vaadin.data.Container;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.PageTreeTableLogic;

/**
 *
 * @author maheshj
 */
public class AHSummeryTableLogic extends PageTreeTableLogic {

    ProjectionSelectionDTO projSelDTO = new ProjectionSelectionDTO();
    private final SalesLogic salesLogic = new SalesLogic();
    int levelNo;
    String hierarchyNo;
    boolean firstGenerated = false;
    public static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(AHSummeryTableLogic.class);
    ProjectionSelectionDTO initialProjSelDTO = new ProjectionSelectionDTO();

    /**
     * Loads the table in sales projection based on start and end index.
     *
     * @param start
     * @param offset
     * @return
     */
    @Override
    public Map<Integer, Object> loadData(int start, int offset) {
        LOGGER.info("loadData initiated with firstGenerated=" + firstGenerated + " and start=" + start + " and offset=" + offset);
        Map<Integer, Object> map = new HashMap<>();
        if (firstGenerated && offset > 0) {
            try {
                List<SalesRowDto> list = salesLogic.getConfiguredSalesProjection(getLastParent(), start, offset, projSelDTO);
                int i = start;
                for (SalesRowDto dto : list) {
                    map.put(i, dto);
                    i++;
                }
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
        LOGGER.info("loadData ended  " + map.size());
        return map;
    }

    /**
     * Returns the available number of records based on the generate criteria.
     *
     * @return
     */
    @Override
    public int getCount() {
        LOGGER.info("getCount initiated with firstGenerated=" + firstGenerated);
        int count = 0;
        if (firstGenerated) {
            count = salesLogic.getConfiguredSalesProjectionCount(getLastParent(), projSelDTO, true, initialProjSelDTO);
        }
        LOGGER.info("getCount ended with count=" + count);
        return count;
    }

    /**
     *
     * @param object
     * @param datasource
     * @return
     */
    @Override
    public Object configureContainer(Object object, Container datasource) {
        SalesRowDto dto = (SalesRowDto) object;
        ((CustomTreeContainer<SalesRowDto>) datasource).addBean(dto);
        if (dto.getParent() == 1) {
            ((CustomTreeContainer<SalesRowDto>) datasource).setChildrenAllowed(dto, true);
        } else {
            ((CustomTreeContainer<SalesRowDto>) datasource).setChildrenAllowed(dto, false);
        }
        return dto;
    }

    public void groupChange() {
        clearAll();
        setCurrentPage(1);
    }

    public void setProjectionResultsData(ProjectionSelectionDTO projSelDTO) {
        LOGGER.info("setProjectionResultsData is called");
        this.projSelDTO = projSelDTO;
        clearAll();
        initialProjSelDTO.setCustomerLevelNo(projSelDTO.getCustomerLevelNo());
        initialProjSelDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        initialProjSelDTO.setProductLevelNo(projSelDTO.getProductLevelNo());
        initialProjSelDTO.setGroup(projSelDTO.getGroup());
        firstGenerated = true;
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

    public Object getExpandedTreeValues(String treeLevel) {
        return getExpandedTreeValue(treeLevel);
    }

    public void forRefresh(Set<String> hierachyNos) {
        addExpandedTreeDataFetchable(hierachyNos);
    }

    protected void recursivelyLoadExpandData(Object parentId, String treeLevel, int expandLevelNo) {
        try {
            int count = salesLogic.getConfiguredSalesProjectionCount(parentId, projSelDTO, true, projSelDTO);
            LevelMap levelMap = new LevelMap(count, getColumnIdToFilterMap());
            addlevelMap(treeLevel, levelMap);

            if (projSelDTO.isIsCustomHierarchy()) {
                String checkFalg = salesLogic.getCustomViewHierarchyIndicator(projSelDTO.getCustomId(), projSelDTO.getTreeLevelNo());
                projSelDTO.setHierarchyIndicator(StringUtils.EMPTY + checkFalg);
            }
            String hierarchyNo = projSelDTO.getHierarchyNo();
            int levelNo = projSelDTO.getTreeLevelNo();
            if (expandLevelNo >= levelNo) {

                List<SalesRowDto> levelList = null;
                try {
                    levelList = CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS.equals(projSelDTO.getScreenName()) ? salesLogic.getReturnsSalesResults(projSelDTO, 0, projSelDTO.getLevelCount()) : salesLogic.getSalesResults(projSelDTO, 0, projSelDTO.getLevelCount());

                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
                if (levelList != null) {
                    int size = levelList.size();
                    int index = count - size + 1;
                    for (int j = 0; j < size; j++) {
                        SalesRowDto levelDto = levelList.get(j);
                        String customTreeLevel = treeLevel + (index + j) + ".";
                        addExpandedTreeList(customTreeLevel, levelDto);
                        recursivelyLoadExpandData(levelDto, customTreeLevel, expandLevelNo);

                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public void loadExpandData(int levelNo) {
        try {
            recursivelyLoadExpandData(new Object(), StringUtils.EMPTY, levelNo);
            setRecordCount(getCalculatedTotalRecordCount());
            setCurrentPage(getTotalAmountOfPages());
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

}
