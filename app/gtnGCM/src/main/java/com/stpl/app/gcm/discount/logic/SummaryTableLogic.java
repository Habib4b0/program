/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.discount.logic;

import org.asi.container.ExtTreeContainer;
import com.stpl.app.gcm.discount.dto.DiscountDTO;
import com.stpl.app.gcm.discount.dto.RemoveDiscountDto;
import com.vaadin.data.Container;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.asi.ui.extfilteringtable.paged.logic.PageTreeTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author mohamed.hameed
 */
public class SummaryTableLogic extends PageTreeTableLogic {

    RemoveDiscountDto removeDiscountDto;
    DiscountLogic itemLogic = new DiscountLogic();
    boolean generate = false;
    private static final Logger LOGGER = Logger.getLogger(SummaryTableLogic.class);

    @Override
    public Map<Integer, Object> loadData(int start, int offset) {
        Map<Integer, Object> map = new HashMap<>();
        removeDiscountDto.setStartIndex(start);
        removeDiscountDto.setOffSet(offset);
        List<DiscountDTO> list = itemLogic.getConfigureSales(getLastParent(), removeDiscountDto);
        int i = start;
        for (DiscountDTO dto : list) {
            map.put(i, dto);
            i++;
        }
        return map;
    }

    @Override
    public int getCount() {
        if (generate) {
            int count = itemLogic.getConfigureSalesCount(getLastParent(), removeDiscountDto);
            LOGGER.debug("count" + count);
            return count;
        }
        return 0;
    }

    @Override
    public Object configureContainer(Object object, Container datasource) {
        DiscountDTO dto = (DiscountDTO) object;
        ((ExtTreeContainer<DiscountDTO>) datasource).addBean(dto);
        if (dto.getParent() == 1) {
            ((ExtTreeContainer<DiscountDTO>) datasource).setChildrenAllowed(dto, true);
        } else {
            ((ExtTreeContainer<DiscountDTO>) datasource).setChildrenAllowed(dto, false);
        }
        return dto;
    }

    public void setData(RemoveDiscountDto removeDiscountDto) {
        this.removeDiscountDto = removeDiscountDto;
        generate = true;
        clearAll();
        setCurrentPage(1);
    }
}
