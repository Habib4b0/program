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
import com.vaadin.data.Container;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.paged.logic.PageTreeTableLogic;
import org.jboss.logging.Logger;

/**
 * The Non FAMP Table Logic
 *
 * @author vinodhini
 * @param <T>
 */
public class NonFampTableLogic extends PageTreeTableLogic {

    int levelNo;
    String hierarchyNo;
    boolean firstGenerated = false;
    ProjectionSelectionDTO projSelDTO = new ProjectionSelectionDTO();
    private FcpResultsLogic fcpResLogic = new FcpResultsLogic();
    SessionDTO sessionDTO;
     /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(NonFampTableLogic.class);    
    
     @Override
     public Map<Integer, Object> loadData(int start, int offset) {
          LOGGER.debug("loadData initiated with firstGenerated=" + firstGenerated + " and start=" + start + " and offset=" + offset);
        Map<Integer, Object> map = new HashMap<Integer, Object>();
        if (firstGenerated && offset > 0) {
            List<String> indexList = new ArrayList<String>(getNonFetchableData().keySet());
            projSelDTO.setNonFetchableIndex(indexList);
            List<TableDTO> list =  fcpResLogic.getConfiguredNonFamp(getLastParent(), start, offset, projSelDTO, levelNo, hierarchyNo,sessionDTO);
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
    public int getCount(){
        int count = 0;

        if (firstGenerated) {
            try {
                count = fcpResLogic.getConfiguredNonFampCount(getLastParent(), projSelDTO, levelNo, hierarchyNo, true);
            } catch (Exception ex) {
              LOGGER.error(ex);
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

    public void setProjectionResultsData(ProjectionSelectionDTO projSelDTO, int levelNo, String hierarchyNo,SessionDTO sessionDTO) {
       
        this.projSelDTO = projSelDTO;
        this.levelNo = levelNo;
        this.hierarchyNo = hierarchyNo;
        this.sessionDTO=sessionDTO;
        clearAll();
        firstGenerated = true;
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
    @Override
    public void setColumnIdToFilterValue(Object prop, Object value) {

        HelperDTO helperDTO = (HelperDTO) value;        
        if (value != null && !"Show all".equals(helperDTO.toString()) && StringUtils.isNotBlank(helperDTO.getDescription())) {
            projSelDTO.setLevelNo(helperDTO.getId());
            this.levelNo = helperDTO.getId();
        } else {
            projSelDTO.setLevelNo(0);
            this.levelNo = 0;
        }
        clearAll();
    }
}
