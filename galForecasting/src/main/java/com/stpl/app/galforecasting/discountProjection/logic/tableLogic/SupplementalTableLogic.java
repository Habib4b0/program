/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.discountProjection.logic.tableLogic;

import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.app.galforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.discountProjection.dto.DiscountProjectionDTO;
import com.stpl.app.galforecasting.discountProjection.logic.SupplementalDiscountProjectionLogic;
import com.stpl.app.galforecasting.utils.Constant;
import com.vaadin.data.Container;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.PageTreeLogicBase;
import org.asi.ui.extfilteringtable.paged.logic.PageTreeTableLogic;
import org.jboss.logging.Logger;

/**
 * The Supplemental Table Logic .
 *
 * @author sooriya.lakshmanan
 */
public class SupplementalTableLogic extends PageTreeTableLogic {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(SupplementalTableLogic.class);

    int levelNo;
    String hierarchyNo;
    boolean firstGenerated = false;
    SupplementalDiscountProjectionLogic sdpLogic = new SupplementalDiscountProjectionLogic();
    ProjectionSelectionDTO projSelDTO = new ProjectionSelectionDTO();
    Object expandedValue;
    String tempArr[] = new String[3];
    int selectedSupplementalLevelNo;
    int selectedParentID;
    String selectedSupplementalLevelName = StringUtils.EMPTY;
    int supplementalLevelNo;
    int parentID;
    String supplementalLevelName = StringUtils.EMPTY;

    @Override
    public Map<Integer, Object> loadData(int start, int offset) {
        LOGGER.info("loadData initiated with firstGenerated=" + firstGenerated + " and start=" + start + " and offset=" + offset);
        Map<Integer, Object> map = new HashMap<Integer, Object>();
        if (firstGenerated && offset > 0) {
            List<String> indexList = new ArrayList<String>(getNonFetchableData().keySet());
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
        LOGGER.info("loadData ended  ");
        return map;
    }

    @Override
    public int getCount() {
        int count = 0;
        if (firstGenerated) {
            count = sdpLogic.getConfiguredSupplementalDiscountCount(getLastParent(), projSelDTO, true);
        }
        LOGGER.info("Return Count " + count);
        return count;
    }

    @Override
    public Object configureContainer(Object object, Container datasource) {
        DiscountProjectionDTO dto = (DiscountProjectionDTO) object;
        ((CustomTreeContainer<DiscountProjectionDTO>) datasource).addBean(dto);
        if (dto.getParent() == 1) {
            ((CustomTreeContainer<DiscountProjectionDTO>) datasource).setChildrenAllowed(dto, true);
        } else {
            ((CustomTreeContainer<DiscountProjectionDTO>) datasource).setChildrenAllowed(dto, false);
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

    public Object getExpandedTreeValues(String hierarchyNo) {
        return getExpandedTreeValue(hierarchyNo);
    }

    public Map<String, Object> getExpandedTreeLevelList() {
        return getExpandedTreeList();
    }

    protected void recursivelyLoadExpandData(Object parentId, String treeLevel, int expandLevelNo) {
        projSelDTO.setIsFilter(false);
        int count = sdpLogic.getConfiguredSupplementalDiscountCount(parentId, projSelDTO, true);
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
