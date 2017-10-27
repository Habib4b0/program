/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.ifp.logic;

import com.stpl.app.global.ifp.dto.ItemFamilyplanSearchDTO;
import com.stpl.app.global.ifp.ui.form.ParentIFPIdLookup;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author saranya.sekar
 */
public class IFPParentLookupTableLogic extends PageTableLogic {

    private final IFPLogic ifpLogic = new IFPLogic();
    private ErrorfulFieldGroup binder;
    private boolean binderflag;
    private boolean isReset = false;

    @Override
    public int getCount() {
        int count = 0;

        try {
            if (binderflag) {
                count = (int) ifpLogic.getLookUpSearchCount(binder, this.getFilters());
            }

            count = isReset ? 0 : count;

        } catch (Exception ex) {

        }
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List<ItemFamilyplanSearchDTO> salesList = new ArrayList();
        try {

            if (binderflag) {
                salesList = ifpLogic.lookupSearchIFP(binder, start, offset, this.getSortByColumns(), this.getFilters());

                return salesList;
            }

        } catch (Exception ex) {

        }
        return salesList;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        ItemFamilyplanSearchDTO dto = (ItemFamilyplanSearchDTO) object;
        ((BeanItemContainer<ItemFamilyplanSearchDTO>) container).addBean(dto);
        return dto;
    }

    public void configureSearchData(final ErrorfulFieldGroup binder,final Boolean isGenerate) {
        this.clearAll();
        this.getFilters().clear();
        this.binder = binder;
        binderflag = isGenerate;

        this.setRequiredCount(true);
        this.setCurrentPage(1);
    }

    public void setReset(boolean isReset) {
        this.isReset = isReset;
    }
}