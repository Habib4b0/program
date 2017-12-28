/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.global.deductioncalendar.logic.tablelogic;

import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.deductioncalendar.dto.DeductionDetailsDTO;
import com.stpl.app.global.deductioncalendar.dto.TableDTO;
import com.stpl.app.global.deductioncalendar.logic.DeductionDetailsLogic;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.v7.data.Container;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.paged.logic.PageTreeTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author gopinath
 */
public class DeductionTableLogic extends PageTreeTableLogic {
    boolean firstGenerated = false;
    DeductionDetailsDTO deductionDTO;
    SessionDTO sessionDTO;
    TableDTO tableDTO ;
  
     /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(DeductionTableLogic.class); 
    /**
     * Deduction details logic instance
     */
    private DeductionDetailsLogic logic = new DeductionDetailsLogic();
    /**
     * 
     * @param levelNo
     * @param hierarchyNo 
     */
    public void setProjectionResultsData(DeductionDetailsDTO deductionDTO,SessionDTO sessionDTO) {
        this.deductionDTO = deductionDTO;
        this.sessionDTO = sessionDTO;
        clearAll();
        firstGenerated = true;
        setCurrentPage(1);
    }

    @Override
     public Map<Integer, Object> loadData(int start, int offset) {
          LOGGER.debug("loadData initiated with firstGenerated=" + firstGenerated + " and start=" + start + " and offset=" + offset);
          Map<Integer, Object> finalMap = new HashMap<>();
            List<TableDTO> list = null;
            try {
                list = logic.getDeductionDetailsData(getLastParent(), deductionDTO, getTableDTO(), start, offset,sessionDTO);
                if(list!=null){
                    int i = start;
                    for (TableDTO dto : list) {
                        finalMap.put(i, dto);
                        i++;
                    }
          }
        } catch (Exception e) {
           LOGGER.error(e);
        }
        LOGGER.debug("loadData ended");
        return finalMap;
       
    }

    @Override
    public int getCount() {
        LOGGER.debug("Inside Deduction details records Count");
        int count = 0;
        if (firstGenerated) {
            try {
                count = logic.getDeductionDetailsCount(getLastParent(), deductionDTO, getTableDTO(),sessionDTO);
            } catch (Exception ex) {
                LOGGER.error(ex);
            } 
        }
        LOGGER.debug("Ending Deduction details records Count");
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
    public void loadExpandData()  {
        return;
      
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

    public TableDTO getTableDTO() {
        return tableDTO;
    }

    public void setTableDTO(TableDTO tableDTO) {
        this.tableDTO = tableDTO;
    }

    public Object getExpandedTreeValues(String level) {
        return getExpandedTreeValue(level);
    }
    
    public void loadExpandData(DeductionDetailsDTO deductionDTO) {
        this.deductionDTO = deductionDTO;
        firstGenerated = true;
        clearAll();
        recursivelyLoadExpandData(new Object(),"",NumericConstants.FOUR,deductionDTO);
        setRecordCount(getCalculatedTotalRecordCount());
        setCurrentPage(getTotalAmountOfPages());
    }
    protected void recursivelyLoadExpandData(Object parentId, String treeLevel,  int expandLevelNo,DeductionDetailsDTO deductionDTO) {
        int count = logic.getDeductionDetailsCount(parentId, deductionDTO, tableDTO,sessionDTO);
        LevelMap levelMap = new LevelMap(count, getColumnIdToFilterMap());
        addlevelMap(treeLevel, levelMap);
        int levelNo = 0;
        if (parentId instanceof TableDTO) {
            levelNo = ((TableDTO) parentId).getLevelNo();
        }
        if ((expandLevelNo) > (levelNo)) {
            List<TableDTO> list = logic.getDeductionDetailsData(parentId, deductionDTO, tableDTO, 0, count, sessionDTO);
            if (list != null && !list.isEmpty()) {
                int size = list.size();
                int index = 1;
                for (int j = 0; j < size; j++) {
                    String customTreeLevel = treeLevel + (index + j) + ".";
                    TableDTO dto = (TableDTO) list.get(j);
                    addExpandedTreeList(customTreeLevel, dto);
                    recursivelyLoadExpandData(dto, customTreeLevel, expandLevelNo,deductionDTO);
                }
            }
        }
    }
    
}
