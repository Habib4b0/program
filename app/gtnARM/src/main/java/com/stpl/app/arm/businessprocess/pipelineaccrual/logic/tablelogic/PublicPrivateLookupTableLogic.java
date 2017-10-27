/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.pipelineaccrual.logic.tablelogic;

import com.stpl.app.arm.adjustmentrateconfiguration.dto.ViewLookupDTO;
import com.stpl.app.arm.businessprocess.pipelineaccrual.logic.ExclusionDetailsLogic;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;

import java.util.Collections;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author Gopinath.Mathiyalaga
 */
public class PublicPrivateLookupTableLogic extends PageTableLogic {

    ViewLookupDTO exRateDTO = new ViewLookupDTO();
    ExclusionDetailsLogic logic = new ExclusionDetailsLogic();
    boolean isGenerate = false;
    public static final Logger LOGGER = Logger.getLogger(PublicPrivateLookupTableLogic.class);

    @Override
    public int getCount() {
        try {
            if (isGenerate) {
                List<Object> count = logic.getSavedViewList(exRateDTO, true, 0, 0, null, this.getFilters());
                return Integer.valueOf(count.get(0).toString());
            }
            return 0;
        } catch (Exception ex) {
            LOGGER.error("Error in getCount :"+ex);
            return 0;
        }
    }

    @Override
    public List loadData(int start, int offset) {
        try {
            return logic.getSavedViewList(exRateDTO, false, start, offset, this.getSortByColumns(), this.getFilters());
        } catch (Exception ex) {
            LOGGER.error("Error in loadData :"+ex);
            return Collections.emptyList();
        }
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        ViewLookupDTO dto = (ViewLookupDTO) object;
        ((BeanItemContainer<ViewLookupDTO>) container).addBean(dto);
        return dto;
    }

    public void configureSearchData(ViewLookupDTO exRateDTO, boolean isGenerate, String viewCategory) {
        try {
            this.isGenerate = isGenerate;
            this.exRateDTO = exRateDTO;
            exRateDTO.setViewCategory(viewCategory);
            clearAll();
            setRequiredCount(true);
            setCurrentPage(1);

        } catch (Exception e) {
            LOGGER.error("Error in configureSearchData :"+e);
        }
    }

}
