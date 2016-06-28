/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.global.priceschedule.logic;

import com.stpl.app.global.priceschedule.dto.PSNepFormulaDTO;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author Sibi
 */
public class NepFormulaTableLogic extends PageTableLogic{

    private ErrorfulFieldGroup binder;
    private boolean isGenerate;
    private final PSLogic psLogic = new PSLogic();
    
    private static final Logger LOGGER = Logger.getLogger(NepFormulaTableLogic.class);
    
    @Override
    public int getCount() {
        int count = 0;
        
        if (isGenerate) {
            try {                
                count = (Integer) psLogic.getNsfCount(binder,this.getFilters());
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List<PSNepFormulaDTO> resultList=new ArrayList<PSNepFormulaDTO>();
        try {
            resultList = (List<PSNepFormulaDTO>) psLogic.loadNsfResults(binder,start,offset,this.getSortByColumns(),this.getFilters());
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(NepFormulaTableLogic.class.getName()).log(Level.SEVERE, null, ex);
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
        PSNepFormulaDTO dto = (PSNepFormulaDTO) object;
        ((BeanItemContainer<PSNepFormulaDTO>) container).addBean(dto);
        return dto;
    }
    
   /**
    * 
    * @param rsFormulaDTO 
    */
    public void setSearchData(final ErrorfulFieldGroup binder){        
        clearAll();
        this.binder = binder;
        setRequiredCount(true);
        isGenerate = true;          
    }
    
}
