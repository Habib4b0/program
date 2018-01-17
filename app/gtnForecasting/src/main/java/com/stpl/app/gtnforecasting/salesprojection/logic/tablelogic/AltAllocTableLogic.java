/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.salesprojection.logic.tablelogic;

import com.stpl.app.gtnforecasting.dto.AlternateHistoryDTO;
import com.stpl.app.gtnforecasting.salesprojection.logic.AlternateHistoryLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.vaadin.v7.data.Container;
import java.util.Collections;
import java.util.List;
import org.asi.container.ExtContainer;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author vigneshkanna
 */
public class AltAllocTableLogic extends PageTableLogic {

    private boolean firstTime = false;
    private AlternateHistoryDTO altHistoryDTO = new AlternateHistoryDTO();
    private final AlternateHistoryLogic logic = new AlternateHistoryLogic();
    private SessionDTO session = new SessionDTO();
    private boolean isAvailable = Boolean.FALSE;
    private boolean addToQueue = Boolean.FALSE;
    private static final Logger LOGGER = LoggerFactory.getLogger(AltAllocTableLogic.class);

    @Override
    public int getCount() {

        int count = 0;
        try {

            if (firstTime) {
                count = logic.getCount_allocationTab(session, addToQueue, getFilters());
                if(!addToQueue){
                    count++;
                }
 
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        try {
            return logic.getAlloc(altHistoryDTO, session, addToQueue, getFilters(), start, offset, Boolean.FALSE);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public Object configureContainer(Object object, Container container
    ) {
        AlternateHistoryDTO dto = (AlternateHistoryDTO) object;
        ((ExtContainer<AlternateHistoryDTO>) container).addBean(dto);
        return dto;
    }

    /**
     * Setting up the datas which we pass to the Logic
     *
     * @param searchBinder
     * @param SearchBinder
     * @param isAddAll
     *
     * @param altHistoryDTO
     * @param session
     * @return boolean - count is 0 or not
     */
    public void loadSetData(AlternateHistoryDTO altHistoryDTO, SessionDTO session, boolean addToQueue) {
        this.session = session;
        this.addToQueue = addToQueue;
        this.altHistoryDTO = altHistoryDTO;
        firstTime = altHistoryDTO.getReset();
        clearAll();
        setRequiredCount(true);
        setCurrentPage(1);
    }

    public boolean isRecordPresent() {
        setCurrentPage(1);
        return getRecordCount() != 0;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

}
