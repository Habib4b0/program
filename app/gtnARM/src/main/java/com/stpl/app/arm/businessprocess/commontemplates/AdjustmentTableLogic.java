/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.commontemplates;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.supercode.Criteria;
import com.stpl.app.arm.supercode.DataResult;
import com.stpl.app.arm.supercode.HasLogic;
import com.stpl.app.arm.supercode.LogicAble;
import com.stpl.app.arm.supercode.SelectionCriteria;
import com.stpl.app.arm.supercode.TableLogicAble;
import com.vaadin.v7.data.Container;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.paged.logic.PageTreeTableLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class AdjustmentTableLogic<T extends AdjustmentDTO> extends PageTreeTableLogic implements TableLogicAble<T>, HasLogic<T> {

    private LogicAble<T> logic;
    private AbstractSelectionDTO selection;
    private boolean isGenerate = Boolean.TRUE;
    public static final Logger LOGGER = LoggerFactory.getLogger(AdjustmentTableLogic.class);

    public AdjustmentTableLogic(LogicAble<T> logic, AbstractSelectionDTO selection) {
        this.logic = logic;
        this.selection = selection;
    }

    @Override
    public int getCount() {
        LOGGER.debug("Inside getcount");
        int count = 0;
        if (!isGenerate) {
            SelectionCriteria criteria = new SelectionCriteria();
            criteria.setFilters(getFilters());
            criteria.setSelectionDto(selection);
            criteria.setParent(getLastParent());
            criteria.setCurrentPage(getCurrentPage());
            criteria.setLastPage(getTotalAmountOfPages());
            count = getCount(criteria);
        }
        LOGGER.debug("count-->>" + count);
        return count;
    }

    @Override
    public Object configureContainer(Object object, Container container) {

        AdjustmentDTO dto = (AdjustmentDTO) object;
        try {
            if (container != null) {
                ((ExtTreeContainer<AdjustmentDTO>) container).addBean(dto);
                ((ExtTreeContainer<AdjustmentDTO>) container).setChildrenAllowed(dto, dto.getChildrenAllowed());
            }

        } catch (Exception e) {
            LOGGER.error("Error in configureContainer :" + e);
        }
        return dto;
    }

    @Override
    public Map<Integer, Object> loadData(int start, int offset) {
        Map<Integer, Object> finalMap = new HashMap<>();
        SelectionCriteria criteria = new SelectionCriteria();
        criteria.setFilters(getFilters());
        criteria.setSortByColumns(getSortByColumns());
        criteria.setSelectionDto(selection);
        criteria.setParent(getLastParent());
        criteria.setCurrentPage(getCurrentPage());
        criteria.setLastPage(getTotalAmountOfPages());
        criteria.setSiblingCount(getSiblingCount());
        criteria.setStart(start);
        criteria.setOffset(offset);
        List<AdjustmentDTO> list = getData(criteria).getDataResults();
        int i = start;
        for (AdjustmentDTO dto : list) {
            finalMap.put(i, dto);
            i++;

        }
        return finalMap;
    }

    public boolean loadSetData(boolean isReset) {
        this.clearAll();
        isGenerate = isReset;
        setCurrentPage(1);
        return Boolean.TRUE;
    }

    protected int getSiblingCount() {
        return getLevelMap(getTempParentTreeLevel()).getCount();
    }

    protected void recursivelyLoadExpandData(Object parentId, String treeLevel, int expandLevelNo) {
        LOGGER.debug("recursivelyLoadExpandData for treeLevel=" + treeLevel + " and expandLevelNo=" + expandLevelNo);
        try {
            selection.setLevelNo(selection.getLevelNo() + 1);
            SelectionCriteria criteria = new SelectionCriteria();
            criteria.setSelectionDto(selection);
            criteria.setParent(parentId);
            int count = getCount(criteria);
            LOGGER.debug("recursivelyLoadExpandData count=" + count);
            LevelMap levelMap = new LevelMap(count, getColumnIdToFilterMap());
            addlevelMap(treeLevel, levelMap);
            if (count > 0) {
                int levelNo = selection.getLevelNo();
                if (expandLevelNo >= levelNo) {

                    List<AdjustmentDTO> levelList = null;
                    criteria.setStart(0);
                    criteria.setOffset(count);
                    criteria.setSiblingCount(count);
                    levelList = getData(criteria).getDataResults();

                    if (levelList != null) {
                        int size = levelList.size();
                        int index = count - size + 1;
                        for (int j = 0; j < size; j++) {
                            selection.setLevelNo(levelNo);
                            AdjustmentDTO levelDto = levelList.get(j);
                            if (levelDto.getChildrenAllowed()) {
                                String customTreeLevel = treeLevel + (index + j) + ".";
                                addExpandedTreeList(customTreeLevel, levelDto);
                                recursivelyLoadExpandData(levelDto, customTreeLevel, expandLevelNo);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error in recursivelyLoadExpandData :" + e);
        }
    }

    public void loadExpandData(int levelNo) {
        try {
            int tempCurrentPage = getCurrentPage();
            clearAll();
            selection.setLevelNo(0);
            recursivelyLoadExpandData(new Object(), StringUtils.EMPTY, levelNo);
            setRecordCount(getCalculatedTotalRecordCount());
//            setCurrentPage(getTotalAmountOfPages()); // Commented for GALUAT-572
            //commented code
            setCurrentPage(tempCurrentPage);
        } catch (Exception e) {
            LOGGER.error("Error in loadExpandData :" + e);
        }
    }

    @Override
    public int getCount(Criteria criteria) {
        return getSummaryLogic().getCount(criteria);
    }

    @Override
    public DataResult getData(Criteria criteria) {
        return getSummaryLogic().getData(criteria);
    }

    @Override
    public LogicAble<T> getSummaryLogic() {
        return logic;
    }

    public boolean isIsGenerate() {
        return isGenerate;
    }

    public void setIsGenerate(boolean isGenerate) {
        this.isGenerate = isGenerate;
    }

    public Object getExpandedTreeValues(String hierarchyNo) {
        return getExpandedTreeValue(hierarchyNo);
    }

    public void setHierarchyToRefresh(Set<String> finalHirarechyNo) {
        addExpandedTreeDataFetchable(finalHirarechyNo);
    }

}
