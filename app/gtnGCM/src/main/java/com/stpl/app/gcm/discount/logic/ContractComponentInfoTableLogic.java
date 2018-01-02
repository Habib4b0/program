/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.discount.logic;

import com.stpl.app.gcm.discount.dto.CFPComponentDetailsDTO;
import com.stpl.app.gcm.discount.dto.PSComponentDetailsDTO;
import com.stpl.app.gcm.util.Constants;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author lokeshwari
 */
public class ContractComponentInfoTableLogic extends PageTableLogic {

    private final DiscountLogic logic = new DiscountLogic();
    private Object parent = null;
    private String value = StringUtils.EMPTY;

    @Override
    public int getCount() {
        int count = 0;
        count = logic.getComponentCount(parent, value);
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List<PSComponentDetailsDTO> result = new ArrayList<>();
        if (Constants.CFP.equalsIgnoreCase(value)) {
            List<CFPComponentDetailsDTO> list = logic.getFromCfpCD(parent, start, offset);
            return list;
        } else {
            if (Constants.IFP.equalsIgnoreCase(value)) {
                result = logic.getFromIfpCD(parent, start, offset);
            } else if (Constants.PS.equalsIgnoreCase(value)) {
                result = logic.getFromPsCD(parent, start, offset);
            }
            if (Constants.RS.equalsIgnoreCase(value)) {
                result = logic.getFromRsCD(parent, start, offset);
            }
            return result;
        }
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        if (Constants.CFP.equalsIgnoreCase(value)) {
            CFPComponentDetailsDTO dto = (CFPComponentDetailsDTO) object;
            ((BeanItemContainer<CFPComponentDetailsDTO>) container).addBean(dto);
            return dto;
        } else {
            PSComponentDetailsDTO dto = (PSComponentDetailsDTO) object;
            ((BeanItemContainer<PSComponentDetailsDTO>) container).addBean(dto);
            return dto;
        }
    }

    /**
     * Method to setting up the data which we pass to logic
     *
     * @param parent
     * @param value
     */
    public void loadSetData(Object parent, String value) {
        this.parent = parent;
        this.value = value;
        this.clearAll();
        this.setRequiredCount(true);
        this.setCurrentPage(1);
    }
}
