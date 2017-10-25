/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentrateconfiguration.logic.tablelogic;

import com.stpl.app.arm.adjustmentrateconfiguration.dto.LookUpDTO;
import com.stpl.app.arm.adjustmentrateconfiguration.logic.AdjustmentRateLogic;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.Collections;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author 
 */
public class ExclustionLookupTableLogic extends PageTableLogic {

    LookUpDTO exRateDTO=new LookUpDTO();
    AdjustmentRateLogic logic=new AdjustmentRateLogic();
    boolean isGenerate = false;
    private static final Logger LOGGER = Logger.getLogger(ExclustionLookupTableLogic.class);
    
    @Override
    public int getCount() {
       try {
            List<Object> count = logic.searchLogicForExclusionLookUp(exRateDTO, true, 0, 0, null, this.getFilters());
            return Integer.valueOf(count.get(0).toString());
        } catch (Exception ex) {
            LOGGER.error(ex);
            return 0;
        }
    }

    @Override
    public List loadData(int start, int offset) {
      try {
            if (isGenerate) {
                List<LookUpDTO> list = logic.searchLogicForExclusionLookUp(exRateDTO, false, start, offset, this.getSortByColumns(), this.getFilters());
                return list;
            }else{
                return Collections.EMPTY_LIST;
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        }
    }

    @Override
    public Object configureContainer(Object object, Container container) {
       LookUpDTO dto = (LookUpDTO) object;
        ((BeanItemContainer<LookUpDTO>) container).addBean(dto);
        return dto;
    }

      public boolean configureSearchData(LookUpDTO exRateDTO, boolean isGenerate) {
        this.isGenerate = isGenerate;
        this.exRateDTO = exRateDTO;
        clearAll();
        setRequiredCount(true);
        setCurrentPage(1);
        return getRecordCount() != 0;
    }
    
}