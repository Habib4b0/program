/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction7.logic.tablelogic;

import com.stpl.app.arm.adjustmentrateconfiguration.dto.ViewLookupDTO;
import com.stpl.app.arm.businessprocess.transaction7.logic.Trx7ExclusionDetailsLogic;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import java.util.Collections;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Gopinath.Mathiyalaga
 */
public class Trx7PublicPrivateLookupTableLogic extends PageTableLogic {

    public static final Logger LOGGER = LoggerFactory.getLogger(Trx7PublicPrivateLookupTableLogic.class);
    private ViewLookupDTO exRateDTO = new ViewLookupDTO();
    private Trx7ExclusionDetailsLogic tr7logic = new Trx7ExclusionDetailsLogic();
    private boolean isPrivateGenerate = false;

    @Override
    public int getCount() {
        try {
            if (isPrivateGenerate) {
                List<Object> count = tr7logic.getSavedViewList(exRateDTO, true, 0, 0, null, this.getFilters());
                return Integer.valueOf(count.get(0).toString());
            }
            return 0;
        } catch (Exception ex) {
            LOGGER.error("Error in getCount :", ex);
            return 0;
        }
    }

    @Override
    public List loadData(int start, int offset) {
        try {
            return tr7logic.getSavedViewList(exRateDTO, false, start, offset, this.getSortByColumns(), this.getFilters());
        } catch (Exception ex) {
            LOGGER.error("Error in loadData :", ex);
            return Collections.emptyList();
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
            this.isPrivateGenerate = isGenerate;
            this.exRateDTO = exRateDTO;
            clearAll();
            setRequiredCount(true);
            setCurrentPage(1);

        } catch (Exception e) {
            LOGGER.error("Error in configureSearchData :", e);
        }
    }

}
