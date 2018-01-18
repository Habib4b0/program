/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.gtnforecasting.logic.tablelogic;

import com.stpl.app.gtnforecasting.dto.RSFormulaDTO;
import com.stpl.app.gtnforecasting.logic.PPAProjectionLogic;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Sibi
 */
public class FormulaTableLogic extends PageTableLogic{

    private RSFormulaDTO rsFormulaDTO;
    private int formulaSid;
    private boolean isGenerate;
    private final PPAProjectionLogic ppaLogic = new PPAProjectionLogic();
    private boolean isReset = false;
    private boolean isDetails = false;
    private static final Logger LOGGER = LoggerFactory.getLogger(FormulaTableLogic.class);
    
    @Override
    public int getCount() {
        int count = 0;
        if (isGenerate) {
            try {
                if (isDetails) {
                    count = (Integer) ppaLogic.loadDetailsTable(formulaSid, 0, 0, true, this.getFilters());
                } else {
                    count = (Integer) ppaLogic.loadRSFormula(rsFormulaDTO, 0, 0, true, this.getFilters());
                }
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        count = isReset ? 0 : count;
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List<RSFormulaDTO> resultList = null;
        if (isDetails) {
            resultList = (List<RSFormulaDTO>) ppaLogic.loadDetailsTable(formulaSid, start, offset, false, this.getFilters());
        } else {
            resultList = (List<RSFormulaDTO>) ppaLogic.loadRSFormula(rsFormulaDTO, start, offset, false, this.getFilters());
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
        RSFormulaDTO dto = (RSFormulaDTO) object;
        ((BeanItemContainer<RSFormulaDTO>) container).addBean(dto);
        return dto;
    }
    
   /**
    * 
    * @param rsFormulaDTO 
    */
    public void setSearchData(final RSFormulaDTO rsFormulaDTO){        
        this.clearAll();
        this.rsFormulaDTO = rsFormulaDTO;
        setRequiredCount(true);
        isGenerate = true; 
        isReset = false;
        isDetails = false;
        setCurrentPage(1);
    }
    
      /**
    * 
    * @param rsFormulaDTO 
    */
    public void setSearchDataForDetails(final int formulaSid){        
        this.clearAll();
        this.formulaSid = formulaSid;
        setRequiredCount(true);
        isGenerate = true; 
        isReset = false;
        isDetails = true;
        setCurrentPage(1);
    }

    public void setReset(boolean isReset) {
        this.isReset = isReset;
    }
    
}
