/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.nationalassumptions.logic.tablelogic;

import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.app.galforecasting.nationalassumptions.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.nationalassumptions.dto.TableDTO;
import com.stpl.app.galforecasting.nationalassumptions.logic.MedicaidURAResultsLogic;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.CommonConstants.SELECT_ONE;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.CommonConstants.SHOW_ALL;
import com.stpl.app.galforecasting.utils.Constant;
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
 *
 * @author Manasa
 */
public class MedicaidURAResultsTableLogic extends PageTreeTableLogic {
    int levelNo;
    String hierarchyNo;
    boolean firstGenerated = false;
    ProjectionSelectionDTO projSelDTO = new ProjectionSelectionDTO();
    private MedicaidURAResultsLogic medResLogic = new MedicaidURAResultsLogic();
     /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(MedicaidURAResultsTableLogic.class);    
  
    public void setProjectionResultsData(ProjectionSelectionDTO projSelDTO, int levelNo, String hierarchyNo) {
        this.projSelDTO = projSelDTO;
        this.levelNo = levelNo;
        this.hierarchyNo = hierarchyNo;
        clearAll();
        firstGenerated = true;
        setCurrentPage(1);
    }

    @Override
     public Map<Integer, Object> loadData(int start, int offset) {
          LOGGER.info("loadData initiated with firstGenerated=" + firstGenerated + " and start=" + start + " and offset=" + offset);
        Map<Integer, Object> map = new HashMap<Integer, Object>();
        if (firstGenerated && offset > 0) {
            List<String> indexList = new ArrayList<String>(getNonFetchableData().keySet());
            projSelDTO.setNonFetchableIndex(indexList);                
            List<TableDTO> list =  medResLogic.getConfiguredMedicaidResults(getLastParent(), start, offset, projSelDTO,this.getFilters());
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
          
            try {
                count = medResLogic.getConfiguredMedicaidResultsCount(getLastParent(), projSelDTO,this.getFilters());
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
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
 public void loadExpandData(boolean isExpand, int rowIndex)  {
        List<String> expandedList = new ArrayList<String>(getExpandedTreeList().keySet());
        clearAll();
        projSelDTO.setLevelNo(0);
        int count = medResLogic.getConfiguredMedicaidResultsCount(new Object(), projSelDTO,null);
        LevelMap levelMap = new LevelMap(count, getColumnIdToFilterMap());
        addlevelMap(StringUtils.EMPTY, levelMap);

        if (isExpand) {
            List<TableDTO> levelList = medResLogic.getConfiguredMedicaidResults(new Object(), 0, count, projSelDTO,null);
            int size = levelList.size();
            int index = count - size + 1;
            for (int j = 0; j < size; j++) {
                String customTreeLevel = (index + j) + ".";
                String currentTreeLevel = (rowIndex) + ".";
                if (expandedList.contains(customTreeLevel) || currentTreeLevel.equals(customTreeLevel) || rowIndex == -1) {
                    TableDTO levelDto = levelList.get(j);
                    addExpandedTreeList(customTreeLevel, levelDto);
                    count =  medResLogic.getConfiguredMedicaidResultsCount(levelDto, projSelDTO,null);
                    addlevelMap(customTreeLevel, new LevelMap(count, getColumnIdToFilterMap()));
                }
            }
        } else {
            if (rowIndex == -1) { // collapse all
                getExpandedTreeList().clear();
            } else if (!expandedList.isEmpty()) { // To collapse a single node
                List<TableDTO> levelList =  medResLogic.getConfiguredMedicaidResults(new Object(), 0, count, projSelDTO,null);
                int size = levelList.size();
                int index = count - size + 1;
                for (int j = 0; j < size; j++) {
                    String customTreeLevel = (index + j) + ".";
                    String currentTreeLevel = (rowIndex) + ".";
                    if (expandedList.contains(customTreeLevel) && !currentTreeLevel.equals(customTreeLevel)) {// expanding the already expanded nodes 
                        TableDTO levelDto = levelList.get(j);
                        addExpandedTreeList(customTreeLevel, levelDto);
                        count =  medResLogic.getConfiguredMedicaidResultsCount(levelDto, projSelDTO,null);
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
        if (Constant.GROUP.equals(prop)) {
            if (StringUtils.isBlank(String.valueOf(value)) || Constant.NULL.equals(value) || SHOW_ALL.getConstant().equals(String.valueOf(value))) {
                if (columnIdToFilterValue.containsKey(prop)) {
                    columnIdToFilterValue.remove(prop);
                }
                projSelDTO.setLevelValue(StringUtils.EMPTY);
            } else {
                columnIdToFilterValue.put(prop, value);
                String ndc9 = String.valueOf(value);
                projSelDTO.setMedicaidSelectedNdc(ndc9);
                if (!SELECT_ONE.getConstant().equals(ndc9)) {
                    String[] ndcArr = ndc9.split(",");
                    if (ndcArr.length > 0) {
                        if (ndcArr.length > 1) {
                            ndc9 = ndcArr[1].trim();
                        } else {
                            ndc9 = ndcArr[0].trim();
                        }
                    }
                    projSelDTO.setLevelValue(ndc9);
                } else {
                    projSelDTO.setLevelValue(StringUtils.EMPTY);
                }
            }
            clearAll();
        }
    }
    
}
