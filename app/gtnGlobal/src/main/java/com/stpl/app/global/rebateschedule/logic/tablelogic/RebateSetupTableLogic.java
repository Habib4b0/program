/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.global.rebateschedule.logic.tablelogic;

import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.rebateschedule.dto.ItemDetailsDTO;
import com.stpl.app.global.rebateschedule.logic.RebateScheduleLogic;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author Sibi
 */
public class RebateSetupTableLogic extends PageTableLogic{

    BeanItemContainer<ItemDetailsDTO> itemDetailsResultSaveBean;
    private boolean isGenerate;    
    final RebateScheduleLogic rebateScheduleLogic;
    private final static Logger LOGGER = Logger.getLogger(RebateSetupTableLogic.class);
    private String record = StringUtils.EMPTY;
    SessionDTO sessionDTO;
    
    public RebateSetupTableLogic(final SessionDTO sessionDTO){
    this.sessionDTO=sessionDTO;
    rebateScheduleLogic = new RebateScheduleLogic(this.sessionDTO);
    }
    
    @Override
    public int getCount() {
        int count = 0;        
        if (isGenerate) {
            try {
                count = (Integer)rebateScheduleLogic.getLazyItemDetailsResults(0, 0, null, this.getFilters(),true,record);
            } catch (PortalException | SystemException ex) {
                LOGGER.error(ex);
            }
        }        
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        try {            
            if (itemDetailsResultSaveBean.size() > 0) {
                rebateScheduleLogic.saveToTemp(itemDetailsResultSaveBean.getItemIds(), false);
                itemDetailsResultSaveBean.removeAllItems();
            }
            return (List<ItemDetailsDTO>)rebateScheduleLogic.getLazyItemDetailsResults(start, offset, this.getSortByColumns(), this.getFilters(),false,record);
        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex);
        }
        return Collections.emptyList();
    }

    /**
     * 
     * @param object
     * @param container
     * @return 
     */
    @Override
    public Object configureContainer(Object object, Container container) {
        ItemDetailsDTO dto = (ItemDetailsDTO) object;
        ((BeanItemContainer<ItemDetailsDTO>) container).addBean(dto);
        return dto;
    }
    
   /**
    * 
    * @param itemDetailsResultSaveBean 
    */
    public void setSearchData(final BeanItemContainer<ItemDetailsDTO> itemDetailsResultSaveBean,final String record){        
        clearAll();
        
        this.itemDetailsResultSaveBean = itemDetailsResultSaveBean;       
        
        setRequiredCount(true);
        isGenerate = true;          
        this.record = record;
    }
    
}
