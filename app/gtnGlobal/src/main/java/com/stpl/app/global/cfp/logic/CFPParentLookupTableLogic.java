/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.cfp.logic;

import com.stpl.app.global.cfp.dto.CFPSearchDTO;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.Constants;
import com.stpl.app.util.ConstantsUtils;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author saranya.sekar
 */
public class CFPParentLookupTableLogic extends PageTableLogic {

    private static CFPSearchLogic cfpSearchLogic = new CFPSearchLogic();
    private ErrorfulFieldGroup binder;
    private boolean binderflag;
    private boolean isReset = false;
    SessionDTO sessionDTO;

    @Override
    public int getCount() {
        try {
            int count = 0;
            if (binderflag) {
                Object listSize = ((List) cfpSearchLogic.getListForParentLookUp(binder, this.getFilters(), null, false, 0, 0, ConstantsUtils.COUNT)).get(0);
                count = Integer.valueOf(String.valueOf(listSize));

            }
            count = isReset ? 0 : count;
            return count;
        } catch (Exception ex) {
        }
        return Constants.ZERO;
    }

    @Override
    public List loadData(int start, int offset) {
        List<CFPSearchDTO> salesList1;
        try {
            if (binderflag) {
                salesList1 = cfpSearchLogic.searchCFPForParentLookUp(binder, start, offset, this.getSortByColumns(), this.getFilters());
                return salesList1;
            }
        } catch (Exception ex) {
        }
        return new ArrayList<CFPSearchDTO>();
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        CFPSearchDTO dto = (CFPSearchDTO) object;
        ((BeanItemContainer< CFPSearchDTO>) container).addBean(dto);
        return dto;
    }

    public void configureSearchData(final ErrorfulFieldGroup binder, final SessionDTO sessionDTO,final Boolean isGenerate) {
        isReset = false;
        this.clearAll();
        this.getFilters().clear();
        this.binder = binder;
        binderflag = isGenerate;
        this.sessionDTO = sessionDTO;
       
        this.setRequiredCount(true);
        this.setCurrentPage(1);
    }

    public void setReset(boolean isReset) {
        this.isReset = isReset;
    }

}