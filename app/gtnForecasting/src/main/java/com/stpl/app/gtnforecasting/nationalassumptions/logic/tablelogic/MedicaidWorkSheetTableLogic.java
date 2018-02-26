/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.nationalassumptions.logic.tablelogic;

import com.stpl.app.gtnforecasting.nationalassumptions.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.nationalassumptions.dto.TableDTO;
import com.stpl.app.gtnforecasting.nationalassumptions.logic.MedicaidURAResultsLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
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
 *
 * @author Manasa
 */
public class MedicaidWorkSheetTableLogic extends PageTreeTableLogic {
    private boolean firstGenerated = false;
    private ProjectionSelectionDTO projSelDTO = new ProjectionSelectionDTO();
    private final MedicaidURAResultsLogic medicaidResLogic = new MedicaidURAResultsLogic();
    /**
     * The Constant LOGGER.
     */
    private final Logger LOGGER = LoggerFactory.getLogger(MasterFcpWorkSheetTableLogic.class);
    private SessionDTO sessionDTO;

    @Override
    public Map<Integer, Object> loadData(int start, int offset) {
        LOGGER.debug("loadData initiated with firstGenerated= {}, and start= {}, and offset= {}" , firstGenerated, start, offset);
        Map<Integer, Object> map = new HashMap<>();
        if (firstGenerated) {
            List<String> indexList = new ArrayList<>(getNonFetchableData().keySet());
            projSelDTO.setNonFetchableIndex(indexList);
            List<TableDTO> list = medicaidResLogic.getConfiguredMedicaidWorkSheetResults(getLastParent(), start, offset, projSelDTO,sessionDTO);
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
            count = medicaidResLogic.getConfiguredMedicaidWorkSheetCount(getLastParent(), projSelDTO);
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

}
