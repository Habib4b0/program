/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction_7.logic.tablelogic;

import com.stpl.app.arm.adjustmentrateconfiguration.dto.ViewLookupDTO;
import com.stpl.app.arm.businessprocess.transaction_7.logic.Trx7ExclusionDetailsLogic;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author Gopinath.Mathiyalaga
 */
public class Trx7PublicPrivateLookupTableLogic extends PageTableLogic {
    
    public static final Logger LOGGER = Logger.getLogger(Trx7PublicPrivateLookupTableLogic.class);
    ViewLookupDTO exRateDTO = new ViewLookupDTO();
    Trx7ExclusionDetailsLogic logic = new Trx7ExclusionDetailsLogic();
    boolean isGenerate = false;

    @Override
    public int getCount() {
        try {
            if(isGenerate){
            List<Object> count = logic.getSavedViewList(exRateDTO, true, 0, 0, null, this.getFilters());
            return Integer.valueOf(count.get(0).toString());
            }
            return 0;
        } catch (Exception ex) {
           LOGGER.error(ex);
            return 0;
        }
    }

    @Override
    public List loadData(int start, int offset) {
        try {
                List<ViewLookupDTO> list = logic.getSavedViewList(exRateDTO,false, start, offset, this.getSortByColumns(), this.getFilters());
                return list;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        }
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        ViewLookupDTO dto = (ViewLookupDTO) object;
        ((BeanItemContainer<ViewLookupDTO>) container).addBean(dto);
        return dto;
    }

    public void configureSearchData(ViewLookupDTO exRateDTO, boolean isGenerate) {
        try {
            this.isGenerate = isGenerate;
            this.exRateDTO = exRateDTO;
                 clearAll();
                setRequiredCount(true);
                setCurrentPage(1);

        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    
}
