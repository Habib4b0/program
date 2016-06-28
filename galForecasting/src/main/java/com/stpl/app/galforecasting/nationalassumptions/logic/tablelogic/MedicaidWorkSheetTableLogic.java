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
public class MedicaidWorkSheetTableLogic extends PageTreeTableLogic {
    int levelNo;
    String hierarchyNo;
    boolean firstGenerated = false;
    ProjectionSelectionDTO projSelDTO = new ProjectionSelectionDTO();
    private MedicaidURAResultsLogic medicaidResLogic = new MedicaidURAResultsLogic();
    /**
     * The Constant LOGGER.
     */
    private final Logger LOGGER = Logger.getLogger(MasterFcpWorkSheetTableLogic.class);

    @Override
    public Map<Integer, Object> loadData(int start, int offset) {
        LOGGER.info("loadData initiated with firstGenerated=" + firstGenerated + " and start=" + start + " and offset=" + offset);
        Map<Integer, Object> map = new HashMap<Integer, Object>();
        if (firstGenerated) {
            List<String> indexList = new ArrayList<String>(getNonFetchableData().keySet());
            projSelDTO.setNonFetchableIndex(indexList);
            List<TableDTO> list = medicaidResLogic.getConfiguredMedicaidWorkSheetResults(getLastParent(), start, offset, projSelDTO);
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
            count = medicaidResLogic.getConfiguredMedicaidWorkSheetCount(getLastParent(), projSelDTO);
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

    public void setProjectionResultsData(ProjectionSelectionDTO projSelDTO, int levelNo, String hierarchyNo) {

        this.projSelDTO = projSelDTO;
        this.levelNo = levelNo;
        this.hierarchyNo = hierarchyNo;
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

}
