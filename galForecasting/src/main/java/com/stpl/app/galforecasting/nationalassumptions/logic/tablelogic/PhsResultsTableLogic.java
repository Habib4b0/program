/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.nationalassumptions.logic.tablelogic;

import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.app.galforecasting.nationalassumptions.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.nationalassumptions.dto.TableDTO;
import com.stpl.app.galforecasting.nationalassumptions.logic.PhsResultsLogic;
import com.stpl.app.galforecasting.nationalassumptions.logic.ProductGroupLogic;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.Container;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.ContainerLogic;
import org.asi.ui.extfilteringtable.paged.logic.PageTreeTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author Nadhiya
 */
public class PhsResultsTableLogic extends PageTreeTableLogic {

    int levelNo;
    String hierarchyNo;
    boolean firstGenerated = false;
    ProjectionSelectionDTO projSelDTO = new ProjectionSelectionDTO();
    private PhsResultsLogic phsResLogic = new PhsResultsLogic();
 public static final Logger LOGGER = Logger.getLogger(PhsResultsTableLogic.class);
    @Override
    public int getCount() {
        int count = 0;

        if (firstGenerated) {
            try {
                count = phsResLogic.getConfiguredPhsResultsCount(getLastParent(), projSelDTO);
            } catch (Exception ex) {
               LOGGER.error(ex);
            }
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

    @Override
    public Map<Integer, Object> loadData(int start, int offset) {
        Map<Integer, Object> map = new HashMap<Integer, Object>();
        List<TableDTO> list = new ArrayList<TableDTO>();
        if (firstGenerated) {
            list = (List<TableDTO>) phsResLogic.getConfiguredPhsResults(getLastParent(), start, offset, projSelDTO);
        }
        levelNo = 0;

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
        ContainerLogic.LevelMap levelMap = new ContainerLogic.LevelMap(start, end, page, getPageLength(), index, getColumnIdToFilterMap());
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

    public void setProjectionResultsData(ProjectionSelectionDTO selection, boolean isTotal) {

        firstGenerated = true;
        clearAll();

        this.projSelDTO = selection;
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

    public void loadExpandData(boolean isExpand, int itemMasterSID, int rowIndex, String ndcParent) {
        List<String> expandedList = new ArrayList<String>(getExpandedTreeList().keySet());
        clearAll();
        projSelDTO.setLevelNo(0);
        int count = phsResLogic.getConfiguredPhsResultsCount(new Object(), projSelDTO);
        LevelMap levelMap = new LevelMap(count, getColumnIdToFilterMap());
        addlevelMap(StringUtils.EMPTY, levelMap);

        if (isExpand) {
            List<TableDTO> levelList = phsResLogic.getConfiguredPhsResults(new Object(), 0, count, projSelDTO);
            int size = levelList.size();
            int index = count - size + 1;
            for (int j = 0; j < size; j++) {
                String customTreeLevel = (index + j) + ".";
                String customTreeLevel1 = (rowIndex) + ".";
                if (expandedList.contains(customTreeLevel) || customTreeLevel1.equals(customTreeLevel) || rowIndex == -1) {
                    TableDTO levelDto = levelList.get(j);
                    addExpandedTreeList(customTreeLevel, levelDto);
                    count = phsResLogic.getConfiguredPhsResultsCount(levelDto, projSelDTO);
                    addlevelMap(customTreeLevel, new LevelMap(count, getColumnIdToFilterMap()));
                }
            }
        } else {
             if (rowIndex == -1) {
                getExpandedTreeList().clear();
             }else if (!expandedList.isEmpty()) {
                List<TableDTO> levelList = phsResLogic.getConfiguredPhsResults(new Object(), 0, count, projSelDTO);
                int size = levelList.size();
                int index = count - size + 1;
                for (int j = 0; j < size; j++) {
                    String customTreeLevel = (index + j) + ".";
                    String customTreeLevel1 = (rowIndex) + ".";
                    if (expandedList.contains(customTreeLevel) && !customTreeLevel1.equals(customTreeLevel)) {
                        TableDTO levelDto = levelList.get(j);
                        addExpandedTreeList(customTreeLevel, levelDto);
                        count = phsResLogic.getConfiguredPhsResultsCount(levelDto, projSelDTO);
                        addlevelMap(customTreeLevel, new LevelMap(count, getColumnIdToFilterMap()));
                    }
                }

            }
        }
        setRecordCount(getCalculatedTotalRecordCount());
        setCurrentPage(getTotalAmountOfPages());
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
