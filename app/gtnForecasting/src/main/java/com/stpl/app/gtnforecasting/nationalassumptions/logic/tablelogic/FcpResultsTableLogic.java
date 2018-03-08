/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.nationalassumptions.logic.tablelogic;

import com.stpl.app.gtnforecasting.nationalassumptions.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.nationalassumptions.dto.TableDTO;
import com.stpl.app.gtnforecasting.nationalassumptions.logic.FcpResultsLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.v7.data.Container;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.paged.logic.PageTreeTableLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The FCP Results Table Logic .
 *
 * @author Vinodhini
 */
public class FcpResultsTableLogic extends PageTreeTableLogic {

    
    private boolean firstGenerated = false;
    private ProjectionSelectionDTO projSelDTO = new ProjectionSelectionDTO();
    private final FcpResultsLogic fcpResLogic = new FcpResultsLogic();
    private SessionDTO sessionDTO;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(FcpResultsTableLogic.class);

    @Override
    public Map<Integer, Object> loadData(int start, int offset) {
        LOGGER.debug("loadData initiated with firstGenerated= {}, and start= {}, and offser= {}" , firstGenerated, start, offset);
        Map<Integer, Object> map = new HashMap<>();
        if (firstGenerated && offset > 0) {
            List<String> indexList = new ArrayList<>(getNonFetchableData().keySet());
            projSelDTO.setNonFetchableIndex(indexList);
            List<TableDTO> list = fcpResLogic.getConfiguredFcpResults(getLastParent(), start, offset, projSelDTO,sessionDTO);
            int i = start;
            for (TableDTO dto : list) {
                while (projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + i)) {
                    i++;
                }
                map.put(i, dto);
                i++;
            }
            projSelDTO.clearNonFetchableIndex();
        }
        LOGGER.debug("loadData ended");
        return map;
    }

    @Override
    public int getCount() {
        int count = 0;
        if (firstGenerated) {
            count = fcpResLogic.getConfiguredFcpResultsCount(getLastParent(), projSelDTO);
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

    public void setProjectionResultsData(ProjectionSelectionDTO projSelDTO,SessionDTO sessionDTO) {
        this.projSelDTO = projSelDTO;
        this.sessionDTO=sessionDTO;
        clearAll();
        firstGenerated = true;
        setCurrentPage(1);
    }

    public void loadExpandData(boolean isExpand, int rowIndex) {
        List<String> expandedList = new ArrayList<>(getExpandedTreeList().keySet());
        clearAll();
        projSelDTO.setLevelNo(0);
        int count = fcpResLogic.getConfiguredFcpResultsCount(new Object(), projSelDTO);
        LevelMap levelMap = new LevelMap(count, getColumnIdToFilterMap());
        addlevelMap(StringUtils.EMPTY, levelMap);

        if (isExpand) {
            List<TableDTO> levelList = fcpResLogic.getConfiguredFcpResults(new Object(), 0, count, projSelDTO,sessionDTO);
            int size = levelList.size();
            int index = count - size + 1;
            for (int j = 0; j < size; j++) {
                String customTreeLevel = (index + j) + ".";
                String currentTreeLevel = (rowIndex) + ".";
                if (expandedList.contains(customTreeLevel) || currentTreeLevel.equals(customTreeLevel) || rowIndex == -1) {
                    TableDTO levelDto = levelList.get(j);
                    addExpandedTreeList(customTreeLevel, levelDto);
                    count = fcpResLogic.getConfiguredFcpResultsCount(levelDto, projSelDTO);
                    addlevelMap(customTreeLevel, new LevelMap(count, getColumnIdToFilterMap()));
                }
            }
        } else {
            if (rowIndex == -1) { // collapse all
                getExpandedTreeList().clear();
            } else if (!expandedList.isEmpty()) { // To collapse a single node
                List<TableDTO> levelList = fcpResLogic.getConfiguredFcpResults(new Object(), 0, count, projSelDTO,sessionDTO);
                int size = levelList.size();
                int index = count - size + 1;
                for (int j = 0; j < size; j++) {
                    String customTreeLevel = (index + j) + ".";
                    String currentTreeLevel = (rowIndex) + ".";
                    if (expandedList.contains(customTreeLevel) && !currentTreeLevel.equals(customTreeLevel)) {// expanding the already expanded nodes 
                        TableDTO levelDto = levelList.get(j);
                        addExpandedTreeList(customTreeLevel, levelDto);
                        count = fcpResLogic.getConfiguredFcpResultsCount(levelDto, projSelDTO);
                        addlevelMap(customTreeLevel, new LevelMap(count, getColumnIdToFilterMap()));
                    }
                }

            }
        }
        setRecordCount(getCalculatedTotalRecordCount());
        setCurrentPage(getTotalAmountOfPages());
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
    
    @Override
    public void setColumnIdToFilterValue(Object prop, Object value) {

        HelperDTO helperDTO = (HelperDTO) value;        
        if (value != null && !"Show all".equals(helperDTO.toString()) && StringUtils.isNotBlank(helperDTO.getDescription()) ) {
            projSelDTO.setLevelNo(helperDTO.getId());
        } else {
            projSelDTO.setLevelNo(0);
        }
        clearAll();
    }
    

}
