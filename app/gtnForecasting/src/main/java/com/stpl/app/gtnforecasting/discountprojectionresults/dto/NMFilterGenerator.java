/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.discountprojectionresults.dto;

import com.stpl.app.gtnforecasting.logic.DiscountProjectionResultsLogic;
import com.stpl.app.gtnforecasting.logic.GroupFilter;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.data.Container;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;
import java.util.Set;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;

/**
 *
 * @author vigneshkanna
 */
public class NMFilterGenerator implements ExtFilterGenerator {

    SessionDTO session;
    ComboBox groupDdlb = null;

    public NMFilterGenerator(SessionDTO session) {
        this.session = session;
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {
        return null;
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
        if (originatingField instanceof ComboBox || originatingField instanceof TextField) {
            if (originatingField.getValue() != null) {
                return new SimpleStringFilter(propertyId, String.valueOf(originatingField.getValue()), false, false);
            } else {
                return null;
            }
        }
        return null;
    }

    @Override
    public AbstractField<?> getCustomFilterComponent(Object propertyId) {
        if (Constant.GROUP.equals(propertyId)) {
            return getGroup();
        }
        return null;
    }

    @Override
    public void filterRemoved(Object propertyId) {
        return;

    }

    @Override
    public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
        return;

    }

    @Override
    public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {

        return null;
    }

    private ComboBox getGroup() {
        if (groupDdlb == null) {
            groupDdlb = new ComboBox();
            groupDdlb.setNullSelectionAllowed(true);
            groupDdlb.setNullSelectionItemId(Constant.DASH);
            IndexedContainer con = new IndexedContainer();
            con.removeAllItems();
            con.addItem(Constant.ALL_DISCOUNT_GROUP);
                GroupFilter.initdiscountMap(session);
            Set<String> list = session.getDiscountgroupSet();
            if (list != null && list.size() > 0) {
                for (String string : list) {
                    con.addItem(Constant.DISCOUNT + string);
                }
            }
            groupDdlb.setContainerDataSource(con);
            groupDdlb.select(Constant.ALL_DISCOUNT_GROUP);
            groupDdlb.addStyleName(Constant.FILTER_COMBOBOX);
            groupDdlb.setPageLength(NumericConstants.SEVEN);
            groupDdlb.setImmediate(true);
        }
        return this.groupDdlb;
    }

}
