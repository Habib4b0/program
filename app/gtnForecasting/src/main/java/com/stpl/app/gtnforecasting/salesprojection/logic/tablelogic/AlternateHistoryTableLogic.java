
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.salesprojection.logic.tablelogic;

import com.stpl.app.gtnforecasting.dto.AlternateHistoryDTO;
import com.stpl.app.gtnforecasting.salesprojection.logic.AlternateHistoryLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.Collections;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author nandhakumar
 */
public class AlternateHistoryTableLogic extends PageTableLogic {

    private boolean firstTime = true;
    private CustomFieldGroup searchBinder;
    private AlternateHistoryDTO altHistoryDTO;
    private final AlternateHistoryLogic logic = new AlternateHistoryLogic();
    private SessionDTO session;
    private boolean isAvailable = Boolean.FALSE;
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(AlternateHistoryTableLogic.class);
  

    @Override
    public int getCount() {
        int count = 0;
        try {
            if (!firstTime) {
                if (altHistoryDTO.getScreenName().equals("Customer_Selection")) {
                    if (isAvailable) {
                        return logic.companySearchCount(altHistoryDTO, getFilters(), session);
                    } else {
                        return (Integer) logic.getCompanyList(0, 0, getFilters(), Boolean.TRUE, session).get(0);
                    }
                } else if (altHistoryDTO.getScreenName().equals("Item_Selection")) {
                    if (isAvailable) {
                        return logic.itemsSearchCount(altHistoryDTO, getFilters(), session);
                    } else {
                        return logic.getCheckedItemsCount( altHistoryDTO, getFilters(), session);
                    }
                } else {
                    return 0;
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }

        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        try {
            if (altHistoryDTO.getScreenName().equals("Customer_Selection")) {
                if (isAvailable) {
                    return logic.searchCompany( altHistoryDTO, getFilters(), start, offset,  session);
                } else {
                    return logic.getCompanyList(start, offset, getFilters(), Boolean.FALSE, session);
                }
            } else if (altHistoryDTO.getScreenName().equals("Item_Selection")) {
                if (isAvailable) {
                    return logic.searchItems(altHistoryDTO, getFilters(), start, offset, session);
                } else {
                    return logic.getCheckedItemsFromTemptable( altHistoryDTO, getFilters(), start, offset, session);
                }
            } else {
                return Collections.emptyList();
            } 
        } catch (Exception ex) {
            LOGGER.error(ex);
            return Collections.emptyList();
        }
      
    }

    @Override
    public void handleFilterChange() {
        super.handleFilterChange();
    }

    @Override
    public Object configureContainer(Object object, Container container
    ) {
        AlternateHistoryDTO dto = (AlternateHistoryDTO) object;
        ((BeanItemContainer<AlternateHistoryDTO>) container).addBean(dto);
        
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
    public boolean loadSetData(CustomFieldGroup searchBinder, AlternateHistoryDTO altHistoryDTO, SessionDTO session, boolean isAvailable) {
        this.searchBinder = searchBinder;
        this.altHistoryDTO = altHistoryDTO;

        this.isAvailable = isAvailable;
        this.session = session;
        firstTime = altHistoryDTO.getReset();
        clearAll();
        setRequiredCount(true);
        setCurrentPage(1);
        return getRecordCount() != 0;
    }

    /**
     *
     * @param altHistoryDTO
     * @param session
     */
    public void setFireData(AlternateHistoryDTO altHistoryDTO, SessionDTO session) {
        this.altHistoryDTO = altHistoryDTO;
        firstTime = altHistoryDTO.getReset();
        this.session = session;
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
