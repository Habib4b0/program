/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.global.rebateschedule.logic.tablelogic;

import com.stpl.app.global.rebateschedule.dto.RebateScheduleSearchDTO;
import com.stpl.app.global.rebateschedule.logic.RebateScheduleLogic;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;
/**
 *
 * @author sibi
 */
public class ParentLookUpTableLogic extends PageTableLogic {

    private boolean isGenerate;
    private final RebateScheduleLogic rebateScheduleLogic = new RebateScheduleLogic();
    private ErrorfulFieldGroup binder;
    private final static Logger LOGGER = Logger.getLogger(ParentLookUpTableLogic.class);
    boolean isReset = false;
     boolean loadData = false;

    /**
     * 
     * @return 
     */
    @Override
    public int getCount() {
        int count = 0;
        if (isGenerate) {
            try {
                count = (int) rebateScheduleLogic.RSLookUpSearchCount(binder, false, this.getFilters());
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
        count = isReset ? 0 : count;
        return count;
    }

    /**
     * 
     * @param start
     * @param offset
     * @return 
     */    
    @Override
    public List<RebateScheduleSearchDTO> loadData(int start, int offset) {
        List<RebateScheduleSearchDTO> resultList = new ArrayList<>();
        try {
            resultList = rebateScheduleLogic.searchRSLookup(binder, start, offset, this.getSortByColumns(), false, this.getFilters());            
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return resultList;
    }

    /**
     * 
     * @param object
     * @param container
     * @return 
     */
    @Override
    public Object configureContainer(Object object, Container container) {
        RebateScheduleSearchDTO dto = (RebateScheduleSearchDTO) object;
        ((BeanItemContainer<RebateScheduleSearchDTO>) container).addBean(dto);
        return dto;
    }

   /**
    * 
    * @param binder 
    */
    public void setSearchData(final ErrorfulFieldGroup binder) {
        clearAll();
        isReset = false;
        this.binder = binder;
        setRequiredCount(true);
        isGenerate = true;
    }

    public void setReset(boolean isReset) {
        this.isReset = isReset;
    }
     public boolean fireSetData(ErrorfulFieldGroup binder, boolean isReset) {
        this.binder = binder;
        clearAll();
        setRequiredCount(true);
        loadData = !isReset;
        setCurrentPage(1);
        return getRecordCount() != 0;
    }
}
