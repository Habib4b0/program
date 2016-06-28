/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.nationalassumptions.logic.tablelogic;

import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.app.galforecasting.nationalassumptions.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.nationalassumptions.dto.TableDTO;
import com.stpl.app.galforecasting.nationalassumptions.logic.FcpResultsLogic;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.CommonConstants.SELECT_ONE;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.Container;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.PageTreeTableLogic;
import org.jboss.logging.Logger;

/**
 * The FCP Results Table Logic .
 *
 * @author Vinodhini
 */
public class FcpResultsTableLogic extends PageTreeTableLogic {

    int levelNo;
    String hierarchyNo;
    boolean firstGenerated = false;
    ProjectionSelectionDTO projSelDTO = new ProjectionSelectionDTO();
    private FcpResultsLogic fcpResLogic = new FcpResultsLogic();
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(FcpResultsTableLogic.class);

    @Override
    public Map<Integer, Object> loadData(int start, int offset) {
        LOGGER.info("loadData initiated with firstGenerated=" + firstGenerated + " and start=" + start + " and offset=" + offset);
        Map<Integer, Object> map = new HashMap<Integer, Object>();
        if (firstGenerated && offset > 0) {
            List<String> indexList = new ArrayList<String>(getNonFetchableData().keySet());
            projSelDTO.setNonFetchableIndex(indexList);
            List<TableDTO> list = fcpResLogic.getConfiguredFcpResults(getLastParent(), start, offset, projSelDTO);
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
        LOGGER.info("loadData ended");
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
        ((CustomTreeContainer<TableDTO>) datasource).addBean(dto);
        if (dto.getParent() == 1) {
            ((CustomTreeContainer<TableDTO>) datasource).setChildrenAllowed(dto, true);
        } else {
            ((CustomTreeContainer<TableDTO>) datasource).setChildrenAllowed(dto, false);
        }
        return dto;
    }

    public void setProjectionResultsData(ProjectionSelectionDTO projSelDTO) {
        this.projSelDTO = projSelDTO;       
        clearAll();
        firstGenerated = true;
        setCurrentPage(1);
    }

    public void loadExpandData(boolean isExpand, int rowIndex) {
        List<String> expandedList = new ArrayList<String>(getExpandedTreeList().keySet());
        clearAll();
        projSelDTO.setLevelNo(0);
        int count = fcpResLogic.getConfiguredFcpResultsCount(new Object(), projSelDTO);
        LevelMap levelMap = new LevelMap(count, getColumnIdToFilterMap());
        addlevelMap(StringUtils.EMPTY, levelMap);

        if (isExpand) {
            List<TableDTO> levelList = fcpResLogic.getConfiguredFcpResults(new Object(), 0, count, projSelDTO);
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
                List<TableDTO> levelList = fcpResLogic.getConfiguredFcpResults(new Object(), 0, count, projSelDTO);
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
