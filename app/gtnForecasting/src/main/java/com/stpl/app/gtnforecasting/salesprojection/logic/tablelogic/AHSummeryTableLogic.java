/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.salesprojection.logic.tablelogic;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.dto.SalesRowDto;
import com.stpl.app.gtnforecasting.salesprojection.logic.SalesLogic;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.ifs.ui.extfilteringtable.PageTreeTableLogic;
import com.stpl.ifs.ui.util.GtnSmallHashMap;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.v7.data.Container;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtTreeContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author maheshj
 */
public class AHSummeryTableLogic extends PageTreeTableLogic {

    private ProjectionSelectionDTO projSelDTO = new ProjectionSelectionDTO();
    private final SalesLogic salesLogic = new SalesLogic();
    private boolean firstGenerated = false;
    public static final Logger LOGGER = LoggerFactory.getLogger(AHSummeryTableLogic.class);
    private final ProjectionSelectionDTO initialProjSelDTO = new ProjectionSelectionDTO();

    /**
     * Loads the table in sales projection based on start and end index.
     *
     * @param start
     * @param offset
     * @return
     */
    @Override
    public GtnSmallHashMap loadData(int start, int offset) {
        LOGGER.debug("loadData initiated with firstGenerated= {}, and start= {}, offset= {} " , firstGenerated , start, offset);
        GtnSmallHashMap map = new GtnSmallHashMap();
        if (firstGenerated && offset > 0) {
            try {
                List<SalesRowDto> list = salesLogic.getConfiguredSalesProjection(getLastParent(), start, offset, projSelDTO);
                int i = start;
                for (SalesRowDto dto : list) {
                    map.put(i, dto);
                    i++;
                }
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        LOGGER.debug("loadData ended= {}  " , map.size());
        return map;
    }

    /**
     * Returns the available number of records based on the generate criteria.
     *
     * @return
     */
    @Override
    public int getCount() {
        LOGGER.debug("getCount initiated with firstGenerated= {}" , firstGenerated);
        int count = 0;
        if (firstGenerated) {
            count = salesLogic.getConfiguredSalesProjectionCount(getLastParent(), projSelDTO, true, initialProjSelDTO);
        }
        LOGGER.debug("getCount ended with count= {}" , count);
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
        ((ExtTreeContainer<SalesRowDto>) datasource).addBean(dto);
        if (dto.getParent() == 1) {
            ((ExtTreeContainer<SalesRowDto>) datasource).setChildrenAllowed(dto, true);
        } else {
            ((ExtTreeContainer<SalesRowDto>) datasource).setChildrenAllowed(dto, false);
        }
        return dto;
    }

    public void groupChange() {
        clearAll();
        setCurrentPage(1);
    }

    public void setProjectionResultsData(ProjectionSelectionDTO projSelDTO) {
        LOGGER.debug("setProjectionResultsData is called");
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

    @Override
    protected void createCurrentPageStart() {
        setCurrentPageProgress(true);
        setRefresh(false);
    }

    @Override
    protected void createCurrentPageEnd() {
        setCurrentPageProgress(false);
        setRefresh(true);
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
            int levelNo = projSelDTO.getTreeLevelNo();
            if (expandLevelNo >= levelNo) {

                List<SalesRowDto> levelList = null;
                    levelList = CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS.equals(projSelDTO.getScreenName()) ? salesLogic.getReturnsSalesResults(projSelDTO, 0, projSelDTO.getLevelCount()) : salesLogic.getSalesResults(projSelDTO, 0, projSelDTO.getLevelCount());
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
        } catch (PortalException | SystemException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public void loadExpandData(int levelNo) {
        try {
            recursivelyLoadExpandData(new Object(), StringUtils.EMPTY, levelNo);
            setRecordCount(getCalculatedTotalRecordCount());
            setCurrentPage(getTotalAmountOfPages());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

}
