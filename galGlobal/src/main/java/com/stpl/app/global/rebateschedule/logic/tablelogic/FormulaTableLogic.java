/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.global.rebateschedule.logic.tablelogic;

import com.stpl.app.global.abstractsearch.util.ConstantUtil;
import com.stpl.app.global.rebateschedule.dto.RSFormulaDTO;
import com.stpl.app.global.rebateschedule.logic.RebateScheduleLogic;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.Constants;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author Sibi
 */
public class FormulaTableLogic extends PageTableLogic{

    private RSFormulaDTO rsFormulaDTO;
    private boolean isGenerate;
    CustomFieldGroup binder;
    private boolean isReset = false;
    private final RebateScheduleLogic rebateScheduleLogic = new RebateScheduleLogic();
    private boolean isResultsEmpty;
    private static final Logger LOGGER = Logger.getLogger(FormulaTableLogic.class);
    
    @Override
    public int getCount() {
        int count = 0;
        
        if (isGenerate) {
            try {                
                count = (Integer) rebateScheduleLogic.loadRSFormula(rsFormulaDTO,0,0,true, this.getFilters(), null);
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
        isResultsEmpty = (count == 0);
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List<RSFormulaDTO> resultList = null;        
        try {
            resultList = (List<RSFormulaDTO>) rebateScheduleLogic.loadRSFormula(rsFormulaDTO,start,offset,false, this.getFilters(), this.getSortByColumns());
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
        RSFormulaDTO dto = (RSFormulaDTO) object;
        ((BeanItemContainer<RSFormulaDTO>) container).addBean(dto);
        return dto;
    }
    
   /**
    * 
    * @param rsFormulaDTO 
    */
    public void setSearchData(final RSFormulaDTO rsFormulaDTO,final CustomFieldGroup binder){    
        isGenerate = true;
        isReset = false;
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
