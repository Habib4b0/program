/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.discountProjection.logic.tableLogic;

import com.stpl.app.gtnforecasting.discountProjection.dto.DiscountProjectionDTO;
import com.stpl.app.gtnforecasting.discountProjection.logic.SupplementalDiscountProjectionLogic;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.v7.data.Container;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.paged.logic.PageTreeLogicBase;
import org.asi.ui.extfilteringtable.paged.logic.PageTreeTableLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Supplemental Table Logic .
 *
 * @author sooriya.lakshmanan
 */
public class SupplementalTableLogic extends PageTreeTableLogic {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SupplementalTableLogic.class);

    private boolean firstGenerated = false;
    private final SupplementalDiscountProjectionLogic sdpLogic = new SupplementalDiscountProjectionLogic();
    private ProjectionSelectionDTO projSelDTO = new ProjectionSelectionDTO();
   
    @Override
    public Map<Integer, Object> loadData(int start, int offset) {
        LOGGER.debug("loadData initiated with firstGenerated= {}, and start = {}, and offset= {} " , firstGenerated, start, offset);
        Map<Integer, Object> map = new HashMap<>();
        if (firstGenerated && offset > 0) {
            List<String> indexList = new ArrayList<>(getNonFetchableData().keySet());
            projSelDTO.setNonFetchableIndex(indexList);
            List<DiscountProjectionDTO> list = sdpLogic.getConfiguredSupplementalDiscount(getLastParent(), start, offset, projSelDTO);
            int i = start;
            for (DiscountProjectionDTO dto : list) {
                while (projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + i)) {
                    i++;
                }
                map.put(i, dto);
                i++;
            }
            projSelDTO.clearNonFetchableIndex();
        }
        LOGGER.debug("loadData ended  ");
        return map;
    }

    @Override
    public int getCount() {
        int count = 0;
        if (firstGenerated) {
            count = sdpLogic.getConfiguredSupplementalDiscountCount(getLastParent(), projSelDTO);
        }
        LOGGER.debug("Return Count= {} " , count);
        return count;
    }

    @Override
    public Object configureContainer(Object object, Container datasource) {
        DiscountProjectionDTO dto = (DiscountProjectionDTO) object;
        ((ExtTreeContainer<DiscountProjectionDTO>) datasource).addBean(dto);
        if (dto.getParent() == 1) {
            ((ExtTreeContainer<DiscountProjectionDTO>) datasource).setChildrenAllowed(dto, true);
        } else {
            ((ExtTreeContainer<DiscountProjectionDTO>) datasource).setChildrenAllowed(dto, false);
        }
        return dto;
    }

    public void setProjectionResultsData(ProjectionSelectionDTO projSelDTO) {
        clearAll();
        this.projSelDTO = projSelDTO;
        firstGenerated = true;
        setCurrentPage(1);
    }

    public PageTreeLogicBase.LevelMap getLevelMap(int levelValue, int count, int levelIndex) {
        int page = getPageForItem(count + levelIndex + levelValue);
        int index = getItemIndex(count + levelIndex + levelValue);
        int start = getStartIndex(count + levelIndex, index);
        int end = getPageLength();
        PageTreeLogicBase.LevelMap levelMap = new PageTreeLogicBase.LevelMap(start, end, page, getPageLength(), index, getColumnIdToFilterMap());
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

    public Object getExpandedTreeValues(String hierarchyNo) {
        return getExpandedTreeValue(hierarchyNo);
    }

    public Map<String, Object> getExpandedTreeLevelList() {
        return getExpandedTreeList();
    }

    protected void recursivelyLoadExpandData(Object parentId, String treeLevel, int expandLevelNo) {
        projSelDTO.setIsFilter(false);
        int count = sdpLogic.getConfiguredSupplementalDiscountCount(parentId, projSelDTO);
        PageTreeLogicBase.LevelMap levelMap = new PageTreeLogicBase.LevelMap(count, getColumnIdToFilterMap());
        addlevelMap(treeLevel, levelMap);
        if (expandLevelNo >= projSelDTO.getSupplementalLevelNo()) {
            List list = new ArrayList();
            int neededRecord = count;
            for (int j = 1; j <= count; j++) {
                String customTreeLevel = treeLevel + (j) + ".";
                Object ob = getCurrentPageData(customTreeLevel);
                if (ob != null) {
                    neededRecord--;
                    list.add(ob);
                }
            }
            if (neededRecord > 0) {
                List list1 = sdpLogic.getConfiguredSupplementalDiscount(parentId, count - neededRecord, neededRecord, projSelDTO);
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

    public void setHierarchyToRefresh(Set<String> finalHirarechyNo) {
        addExpandedTreeDataFetchable(finalHirarechyNo);
    }

    @Override
    public void setColumnIdToFilterValue(Object prop, Object value) {
        super.setColumnIdToFilterValue(prop, value);
        Object groupDdlb = getColumnIdToFilterValue(Constant.GROUP);
        if (groupDdlb != null) {
            projSelDTO.setGroupFilter(String.valueOf(groupDdlb));
            clearAll();
        } else {
            projSelDTO.setGroupFilter(StringUtils.EMPTY);
            clearAll();
        }
    }

}
