/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.discount.logic;

import com.stpl.app.gcm.discount.dto.RemoveDiscountDto;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author pvinoth
 */
public class RSInfoTableLogic extends PageTableLogic{

    private static final Logger LOGGER = Logger.getLogger(RSInfoTableLogic.class);
    DiscountLogic logic = new DiscountLogic();
    RemoveDiscountDto removeDiscountDto =null;
    
    /**
     * 
     * @return 
     */
    @Override
    public int getCount() {
        int count=0;
        try {
            if (removeDiscountDto!=null) {
                count= logic.getItemsCount(removeDiscountDto, 0 , 0);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return count;
    }

    /**
     * 
     * @param start
     * @param offset
     * @return 
     */
    @Override
    public List loadData(int start, int offset) {
         List<RemoveDiscountDto> list= new ArrayList<>();
        try {
            list= logic.getItems(removeDiscountDto, start, offset, false);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return list;
    }

    /**
     * 
     * @param object
     * @param container
     * @return 
     */
    @Override
    public Object configureContainer(Object object, Container container) {
        RemoveDiscountDto dto = (RemoveDiscountDto) object;
        ((BeanItemContainer<RemoveDiscountDto>) container).addBean(dto);
        return dto;
    }
    
    /**
     * Method to setting up the data which we pass to logic
     *
     * @param removeDiscountDto
     */
    public void loadSetData(RemoveDiscountDto removeDiscountDto) {
        this.clearAll();
        this.removeDiscountDto=removeDiscountDto;
        this.setRequiredCount(true);
        this.setCurrentPage(1);
    }
    
}
