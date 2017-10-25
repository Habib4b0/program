
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.tablelogic;

import com.stpl.app.gcm.copycontract.dto.ContractSelectionDTO;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.tp.dto.ContractResultDTO;
import com.stpl.app.gcm.tp.logic.CommmonLogic;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author mohamed.hameed
 */
public class CurrentContractTableLogic extends PageTableLogic {

    ContractSelectionDTO conSelDTO = new ContractSelectionDTO();
    List<ContractResultDTO> itemList = new ArrayList<ContractResultDTO>();
    CommmonLogic logic = new CommmonLogic();
    boolean firstTime = true;
    SessionDTO session;
    /**
     * The Constant LOGGER.
     */
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(CurrentContractTableLogic.class);

    /**
     * Record count method
     *
     * @return Integer
     */
    @Override
    public int getCount() {
        if (!firstTime) {
            try {
                int count = logic.buildContractCountQuery(conSelDTO, session.getUserId(), session.getSessionId(), getFilters());
                return count;
            } catch (ParseException pe) {
                LOGGER.error(pe.getMessage());
            }
        }
        return 0;
    }

    /**
     * Loading the grid
     *
     * @param start
     * @param offset
     * @return List of results items
     */
    @Override
    public List loadData(int start, int offset) {
        LOGGER.debug("Loading current Contract data");
        try {
            List list = logic.getContractSelectionResults(logic.buildContractSearchQuery(conSelDTO, session.getUserId(), session.getSessionId(), start, offset, getFilters()), false);
            return list;
        } catch (ParseException pe) {
            LOGGER.error(pe.getMessage());
            return null;
        }
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        ContractResultDTO dto = (ContractResultDTO) object;
        ((BeanItemContainer<ContractResultDTO>) container).addBean(dto);
        return dto;
    }

    /**
     * Setting up the datas which we pass to the Logic
     *
     * @param binderDto
     * @return boolean - count is 0 or not
     */
    public boolean loadSetData(ContractSelectionDTO conSelDTO, SessionDTO session) {
        this.conSelDTO = conSelDTO;
        this.session = session;
        firstTime = conSelDTO.isReset();
        clearAll();
        setRequiredCount(true);
        setCurrentPage(1);
        return getRecordCount() != 0;
    }

    @Override
    public void handleFilterChange() {
        setRequiredCount(true);
        setCurrentPage(1);
    }

}
