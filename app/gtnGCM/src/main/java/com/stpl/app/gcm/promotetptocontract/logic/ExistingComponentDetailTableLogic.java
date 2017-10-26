/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.promotetptocontract.logic;

import static com.stpl.app.gcm.discount.ui.form.ExistingDiscountTab.LOGGER;
import com.stpl.app.gcm.promotetptocontract.dto.ComponentInfoDTO;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author alok.v
 */
public class ExistingComponentDetailTableLogic extends PageTableLogic {

    ComponentInfoDTO newDiscountTabDto = new ComponentInfoDTO();
    PromoteTPLogic logic = new PromoteTPLogic();
    boolean generate = false;

    @Override
    public int getCount() {
        if (generate) {
            try {
                return logic.getItemsFromRsCount(newDiscountTabDto);
            } catch (Exception ex) {
               LOGGER.error(ex);
            }
        }
        return 0;
    }

    @Override
    public List loadData(int start, int offset) {
        newDiscountTabDto.setStartIndex(start);
        newDiscountTabDto.setEndIndex(offset);
        List<ComponentInfoDTO> resultList = new ArrayList<ComponentInfoDTO>();
        try {
            resultList = logic.getItemsFromRs(newDiscountTabDto);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return resultList;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        ComponentInfoDTO dto = (ComponentInfoDTO) object;
        ((BeanItemContainer<ComponentInfoDTO>) container).addBean(dto);
        return dto;
    }

    public boolean loadSetData(ComponentInfoDTO newDiscountTabDto) {
        this.newDiscountTabDto = newDiscountTabDto;
        clearAll();
        setRequiredCount(true);
        generate = true;
        setCurrentPage(1);
        return recordCount != 0;
    }
}
