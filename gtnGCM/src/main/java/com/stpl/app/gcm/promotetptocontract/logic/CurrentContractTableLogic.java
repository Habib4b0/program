
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.promotetptocontract.logic;

import com.stpl.app.gcm.promotetptocontract.dto.CurrentContractDTO;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author alok.v
 */
public class CurrentContractTableLogic extends PageTableLogic {

    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(CurrentContractTableLogic.class);
    CurrentContractDTO currConDTO = new CurrentContractDTO();
    PromoteTPLogic logic = new PromoteTPLogic();
    boolean firstTime = true;
    SessionDTO session;

    /**
     * Record count logic
     *
     * @return integer as count
     */
    @Override
    public int getCount() {
        if (!firstTime) {
            currConDTO.setIsCount(false);
            return logic.getSelectedTPContractCount(currConDTO, session.getUserId(), session.getSessionId());
        }
        return 0;
    }

    /**
     * Loading Grid
     *
     * @param start
     * @param offset
     * @return List of results
     */
    @Override
    public List loadData(int start, int offset) {
        try {
            return logic.getSelectedTPContractResults(logic.getContractQuery(currConDTO, session.getUserId(), session.getSessionId(), start, offset, true));
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        }
    }

    /**
     * Configure container
     *
     * @param object
     * @param container
     * @return Object
     */
    @Override
    public Object configureContainer(Object object, Container container) {
        CurrentContractDTO dto = (CurrentContractDTO) object;
        ((BeanItemContainer<CurrentContractDTO>) container).addBean(dto);
        return dto;
    }

    /**
     * Method to setting up the data which we pass to logic
     *
     * @param binderDto
     */
    public boolean loadSetData(CurrentContractDTO currConDTO, SessionDTO session) {
        this.currConDTO = currConDTO;
        this.session = session;
        firstTime = currConDTO.getReset();
        clearAll();
        setRequiredCount(true);
        setCurrentPage(1);
        return getRecordCount() != 0;
    }
}

