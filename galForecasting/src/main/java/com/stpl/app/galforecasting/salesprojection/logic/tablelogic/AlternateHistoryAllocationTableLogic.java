/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.salesprojection.logic.tablelogic;


import com.stpl.app.galforecasting.dto.AlternateHistoryDTO;
import com.stpl.app.galforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.salesprojection.logic.AlternateHistoryLogic;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.vaadin.data.Container;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.asi.container.ExtContainer;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author sibi
 */
public class AlternateHistoryAllocationTableLogic extends PageTableLogic {

    ProjectionSelectionDTO projSelDTO = new ProjectionSelectionDTO();
    int levelNo;
    String hierarchyNo;
//    boolean firstGenerated = false;
    AlternateHistoryDTO altHistoryDTO;
    SessionDTO session=new SessionDTO();
    boolean isFirst=false;
    AlternateHistoryLogic logic = new AlternateHistoryLogic();
    public static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(AlternateHistoryAllocationTableLogic.class);
    private Set checkedCCPSet = new HashSet();
    
    /**
     * Loads the table in sales projection based on start and end index.
     *
     * @param start
     * @param offset
     * @return
     */
    @Override
    public List loadData(int start, int offset) {
        LOGGER.info("loadData initiated with firstGenerated= and start=" + start + " and offset=" + offset);
        Map<Integer, Object> map = new HashMap<>();
        List<AlternateHistoryDTO> list = null;
        if (offset > 0) {
            try {
                list = logic.alternateSelectionList(session, altHistoryDTO, getFilters(),start,offset,false,checkedCCPSet);
                int i = start;
                for (AlternateHistoryDTO dto : list) {
                    map.put(i, dto);
                    i++;
                }
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
        LOGGER.info("loadData ended  " + map.size());
        return list;
    }

    /**
     * Returns the available number of records based on the generate criteria.
     *
     * @return
     */
    @Override
    public int getCount() {
        LOGGER.info("getCount initiated with firstGenerated" );
        int count = 0;
        if(isFirst){
            count = logic.alternateSelectionCount(session, getFilters());
        }
        LOGGER.info("getCount ended with count=" + count);
        return count;
    }
    @Override
    public void handleFilterChange() {
        super.handleFilterChange();
    }
    /**
     *
     * @param object
     * @param datasource
     * @return
     */
    @Override
    public Object configureContainer(Object object, Container datasource) {
        AlternateHistoryDTO dto = (AlternateHistoryDTO) object;
        ((ExtContainer<AlternateHistoryDTO>) datasource).addBean(dto);
        return dto;
    }

    public void groupChange() {
        clearAll();
        setCurrentPage(1);
    }

    public void setProjectionResultsData(AlternateHistoryDTO altHistoryDTO, SessionDTO session, boolean isFirst, final Set checkedCCPSet) {
        LOGGER.info("setProjectionResultsData is called");
        this.altHistoryDTO = altHistoryDTO;
        this.session = session;
        this.isFirst = isFirst;
        this.checkedCCPSet = checkedCCPSet;
        this.setRequiredCount(true);
        setCurrentPage(1);
    }

}
