/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.global.rebateschedule.logic.tablelogic;

import com.stpl.app.global.rebateschedule.dto.NetSalesFormulaDTO;
import com.stpl.app.global.rebateschedule.logic.RebateScheduleLogic;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author Asha
 */
public class NetSalesFormulaTableLogic extends PageTableLogic {
    
    private boolean isGenerate;
    private final static Logger LOGGER = Logger.getLogger(NetSalesFormulaTableLogic.class);
    private NetSalesFormulaDTO netSalesDTO;
    private final RebateScheduleLogic rebateScheduleLogic = new RebateScheduleLogic();
    ErrorfulFieldGroup binder;
    private boolean isReset = false;
    private boolean isResultsEmpty;
    
    @Override
    public int getCount() {
        int count = 0;        
        
            try {    
                if (isGenerate) {
                count = (Integer) rebateScheduleLogic.getNsfCount(binder, this.getFilters());
                }
                isResultsEmpty = (count == 0);
                count = isReset ? 0 : count;
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List<NetSalesFormulaDTO> resultList = null;   
        if (isGenerate) {
        try {
            resultList = (List<NetSalesFormulaDTO>) rebateScheduleLogic.loadNsfResults(binder, start, start + offset, this.getSortByColumns(), this.getFilters());
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
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
        NetSalesFormulaDTO dto = (NetSalesFormulaDTO) object;
        ((BeanItemContainer<NetSalesFormulaDTO>) container).addBean(dto);
        return dto;
    }
    
   /**
    * 
     * @param netSalesDTO 
     * @param binder 
    */
    public void setSearchData(final NetSalesFormulaDTO netSalesDTO, final ErrorfulFieldGroup binder){  
        isGenerate = true;    
        isReset = false;
        clearAll();
        this.netSalesDTO = netSalesDTO;
        this.binder = binder;
        setRequiredCount(true);
        this.setCurrentPage(1);
    }
    
     public boolean isResultsEmpty() {
        return isResultsEmpty;
    }
    
     public void setReset(boolean isReset) {
        this.isReset = isReset;
    }
    
}
