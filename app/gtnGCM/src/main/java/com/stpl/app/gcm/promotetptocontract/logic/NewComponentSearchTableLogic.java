
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
public class NewComponentSearchTableLogic extends PageTableLogic {

    private ComponentInfoDTO componentInfoDTO = new ComponentInfoDTO();
    private final PromoteTPLogic logic = new PromoteTPLogic();
    private boolean generate = false;

    @Override
    public int getCount() {
        if (generate) {
            try {
                return logic.getComponentItemSearchCount(componentInfoDTO);
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
        return 0;
    }

    @Override
    public List loadData(int start, int offset) {
        componentInfoDTO.setStartIndex(start);
        componentInfoDTO.setEndIndex(offset);
        List<ComponentInfoDTO> resultList = new ArrayList<>();
        try {
            resultList = logic.getComponentItemSearchResult(componentInfoDTO);
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

    public boolean loadItemData(ComponentInfoDTO compInfoDTO) {
        this.componentInfoDTO = compInfoDTO;
        clearAll();
        setRequiredCount(true);
        generate = true;
        setCurrentPage(1);
        return recordCount != 0;
    }


}
