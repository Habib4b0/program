package com.stpl.app.gcm.discount.logic;

import com.stpl.app.gcm.discount.dto.ContractsDetailsDto;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author santanukumar
 */
public class NewDiscountTableLogic extends PageTableLogic {

    ContractsDetailsDto binderDto = new ContractsDetailsDto();
    DiscountLogic logic = new DiscountLogic();
    boolean generate = false;
    SessionDTO sessionDTO;
    private static final Logger LOGGER = Logger.getLogger(NewDiscountTableLogic.class);

    /**
     * Record count logic
     *
     * @return integer as count
     */
    @Override
    public int getCount() {
        if (generate) {
            try {
                return logic.getCommonCountForNewTab(binderDto, sessionDTO);
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
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
        binderDto.setStartIndex(start);
        binderDto.setEndIndex(offset);
        List<ContractsDetailsDto> resultList = new ArrayList<ContractsDetailsDto>();
        try {
            resultList = logic.getCommonSearchList(binderDto, sessionDTO);
        } catch (Exception ex) {
           LOGGER.error(ex);
        }
        return resultList;
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
        ContractsDetailsDto dto = (ContractsDetailsDto) object;
        ((BeanItemContainer<ContractsDetailsDto>) container).addBean(dto);
        return dto;
    }

    /**
     * Method to setting up the data which we pass to logic
     *
     * @param binderDto
     * @param sessionDTO
     * @param reset 
     * @return true if data is loaded properly
     */
    public boolean loadSetData(ContractsDetailsDto binderDto, SessionDTO sessionDTO, boolean reset) {
        this.binderDto = binderDto;
        this.sessionDTO = sessionDTO;
        resetAndLoadData(reset);
        return recordCount != 0;
    }
    
    /**
     * To Reset the table and load it
     *
     * @param reset Data load will be prevented if reset flag is true
     */
    public void resetAndLoadData(boolean reset) {
        clearAll();
        getFilters().clear();
        setRequiredCount(true);
        for(ExtPagedTable table:this.getTables()){
            table.setValue(null);
        }
        generate = !reset;
        setCurrentPage(1);
    }
    
}
