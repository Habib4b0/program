/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.nationalassumptions.logic.tablelogic;

import com.stpl.app.gtnforecasting.nationalassumptions.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.nationalassumptions.dto.TableDTO;
import com.stpl.app.gtnforecasting.nationalassumptions.logic.PhsResultsLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.v7.data.Container;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.paged.logic.ContainerLogic;
import org.asi.ui.extfilteringtable.paged.logic.PageTreeTableLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Nadhiya
 */
public class PhsResultsTableLogic extends PageTreeTableLogic {

    private boolean firstGenerated = false;
    private ProjectionSelectionDTO projSelDTO = new ProjectionSelectionDTO();
    private PhsResultsLogic phsResLogic = new PhsResultsLogic();
    private SessionDTO sessionDTO;
    public static final Logger LOGGER = LoggerFactory.getLogger(PhsResultsTableLogic.class);
    
    @Override
    public int getCount() {
        int count = 0;

        if (firstGenerated) {
            try {
                count = phsResLogic.getConfiguredPhsResultsCount(getLastParent(), projSelDTO);
            } catch (Exception ex) {
               LOGGER.error(ex.getMessage());
            }
        }
        return count;
    }

    @Override
    public Object configureContainer(Object object, Container datasource) {
        TableDTO dto = (TableDTO) object;
        ((ExtTreeContainer<TableDTO>) datasource).addBean(dto);
        if (dto.getParent() == 1) {
            ((ExtTreeContainer<TableDTO>) datasource).setChildrenAllowed(dto, true);
        } else {
            ((ExtTreeContainer<TableDTO>) datasource).setChildrenAllowed(dto, false);
        }
        return dto;
    }

    @Override
    public Map<Integer, Object> loadData(int start, int offset) {
        Map<Integer, Object> map = new HashMap<>();
        List<TableDTO> list = new ArrayList<>();
        if (firstGenerated) {
            list = phsResLogic.getConfiguredPhsResults(getLastParent(), start, offset, projSelDTO,sessionDTO);
        }
        
        int i = start;
        for (TableDTO dto : list) {

            map.put(i, dto);
            i++;
        }
        return map;
    }

    public ContainerLogic.LevelMap getLevelMap(int levelValue, int count, int levelIndex) {
        int page = getPageForItem(count + levelIndex + levelValue);
        int index = getItemIndex(count + levelIndex + levelValue);
        int start = getStartIndex(count + levelIndex, index);
        int end = getPageLength();
       return new ContainerLogic.LevelMap(start, end, page, getPageLength(), index, getColumnIdToFilterMap());
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

    public void setProjectionResultsData(ProjectionSelectionDTO selection,SessionDTO sessionDTO) {

        firstGenerated = true;
        clearAll();

        this.projSelDTO = selection;
        this.sessionDTO=sessionDTO;
        phsResLogic = new PhsResultsLogic();
        firstGenerated = true;
        setCurrentPage(1);
    }

    public boolean isFirstGenerated() {
        return firstGenerated;
    }

    public void setFirstGenerated(boolean firstGenerated) {
        this.firstGenerated = firstGenerated;
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

    public void loadExpandData(boolean isExpand, int rowIndex) {
        List<String> expandedList = new ArrayList<>(getExpandedTreeList().keySet());
        clearAll();
        projSelDTO.setLevelNo(0);
        int count = phsResLogic.getConfiguredPhsResultsCount(new Object(), projSelDTO);
        LevelMap levelMap = new LevelMap(count, getColumnIdToFilterMap());
        addlevelMap(StringUtils.EMPTY, levelMap);

        if (isExpand) {
            expandLoadData(count, rowIndex, expandedList);
        } else {
            if (rowIndex == -1) {
                getExpandedTreeList().clear();
            } else if (!expandedList.isEmpty()) {
                collapseLoadData(count, rowIndex, expandedList);
            }
        }
        setRecordCount(getCalculatedTotalRecordCount());
        setCurrentPage(getTotalAmountOfPages());
    }

    public void collapseLoadData(int count, int rowIndex, List<String> expandedList) {
        List<TableDTO> levelList = phsResLogic.getConfiguredPhsResults(new Object(), 0, count, projSelDTO,sessionDTO);
        int size = levelList.size();
        int index = count - size + 1;
        for (int j = 0; j < size; j++) {
            String customTreeLevel = (index + j) + ".";
            String customTreeLevel1 = (rowIndex) + ".";
            if (expandedList.contains(customTreeLevel) && !customTreeLevel1.equals(customTreeLevel)) {
                TableDTO levelDto = levelList.get(j);
                addExpandedTreeList(customTreeLevel, levelDto);
                int collapseLoadDataCount = phsResLogic.getConfiguredPhsResultsCount(levelDto, projSelDTO);
                addlevelMap(customTreeLevel, new LevelMap(collapseLoadDataCount, getColumnIdToFilterMap()));
            }
        }
    }

    public void expandLoadData(int count, int rowIndex, List<String> expandedList) {
        List<TableDTO> levelList = phsResLogic.getConfiguredPhsResults(new Object(), 0, count, projSelDTO,sessionDTO);
        int size = levelList.size();
        int index = count - size + 1;
        for (int j = 0; j < size; j++) {
            String customTreeLevel = (index + j) + ".";
            String customTreeLevel1 = (rowIndex) + ".";
            if (expandedList.contains(customTreeLevel) || customTreeLevel1.equals(customTreeLevel) || rowIndex == -1) {
                TableDTO levelDto = levelList.get(j);
                addExpandedTreeList(customTreeLevel, levelDto);
                int expandLoadDataCount = phsResLogic.getConfiguredPhsResultsCount(levelDto, projSelDTO);
                addlevelMap(customTreeLevel, new LevelMap(expandLoadDataCount, getColumnIdToFilterMap()));
            }
        }
    }
    
    @Override
    public void setColumnIdToFilterValue(Object prop, Object value) {

        HelperDTO helperDTO = (HelperDTO) value;        
        if (value != null && !"Show all".equals(helperDTO.toString()) && StringUtils.isNotBlank(helperDTO.getDescription())) {
            projSelDTO.setLevelNo(helperDTO.getId());
        } else {
            projSelDTO.setLevelNo(0);
        }
        clearAll();
    }
}
