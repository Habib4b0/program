/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dashboard.logic;

import com.stpl.app.contract.dashboard.dto.NepFormulaLookUpDTO;
import com.stpl.app.contract.global.logic.IfpLogic;
import com.stpl.app.contract.ui.ErrorfulFieldGroup;
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
 * @author sathyaseelan.v
 */
public class NepFormulaTableLogic extends PageTableLogic{

    private ErrorfulFieldGroup binder;
    private boolean isGenerate;
    private final IfpLogic ifpLogic = new IfpLogic();
    
    private static final Logger LOGGER = Logger.getLogger(NepFormulaTableLogic.class);
    
    @Override
    public int getCount() {
        int count = 0;
        
        if (isGenerate) {
            try {                
                count = (Integer) ifpLogic.getNsfCount(binder,this.getFilters());
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List<NepFormulaLookUpDTO> resultList=new ArrayList<>();
        resultList = (List<NepFormulaLookUpDTO>) ifpLogic.loadNsfResults(binder,start,offset,this.getSortByColumns(),this.getFilters());
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
        NepFormulaLookUpDTO dto = (NepFormulaLookUpDTO) object;
        ((BeanItemContainer<NepFormulaLookUpDTO>) container).addBean(dto);
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
