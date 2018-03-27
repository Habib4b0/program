
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.tablelogic;

import com.stpl.app.gcm.copycontract.dto.ContractSelectionDTO;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.tp.dto.ContractResultDTO;
import com.stpl.app.gcm.tp.logic.CommmonLogic;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author mohamed.hameed
 */
public class CurrentContractTableLogic extends PageTableLogic {

    private ContractSelectionDTO conSelDTO = new ContractSelectionDTO();
    private final CommmonLogic logic = new CommmonLogic();
    private boolean firstTime = true;
    private SessionDTO session;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CurrentContractTableLogic.class);

    public CurrentContractTableLogic() {
        super();
    }

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
            return Collections.emptyList();
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
