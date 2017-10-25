/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dashboard.logic;

import com.stpl.app.contract.dashboard.dto.PriceProtectionFormulaDTO;
import com.stpl.app.contract.global.logic.IfpLogic;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author sathyaseelan.v
 */
public class FormulaTableLogic extends PageTableLogic{

    private PriceProtectionFormulaDTO rsFormulaDTO;
    private boolean isGenerate;
    CustomFieldGroup binder;
    private final IfpLogic ifpLogic = new IfpLogic();
    private boolean isResultsEmpty;
    private static final Logger LOGGER = Logger.getLogger(FormulaTableLogic.class);
    
    @Override
    public int getCount() {
        int count = 0;
        
        if (isGenerate) {
            try {                
                count = (Integer) ifpLogic.loadRSFormula(rsFormulaDTO,0,0,true, this.getFilters(), null);
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
        isResultsEmpty = count == 0;
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List<PriceProtectionFormulaDTO> resultList = null;        
        try {
            resultList = (List<PriceProtectionFormulaDTO>) ifpLogic.loadRSFormula(rsFormulaDTO,start,offset,false, this.getFilters(), this.getSortByColumns());
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
        PriceProtectionFormulaDTO dto = (PriceProtectionFormulaDTO) object;
        ((BeanItemContainer<PriceProtectionFormulaDTO>) container).addBean(dto);
        return dto;
    }
    
   /**
    * 
    * @param rsFormulaDTO 
    */
    public void setSearchData(final PriceProtectionFormulaDTO rsFormulaDTO,final CustomFieldGroup binder){    
        isGenerate = true;
        clearAll();
        this.rsFormulaDTO = rsFormulaDTO;
        this.binder = binder;
        setRequiredCount(true);

        this.setCurrentPage(1);
    }
   
    public boolean isResultsEmpty() {
        return isResultsEmpty;
    }

}
