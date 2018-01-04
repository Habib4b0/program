/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.projectionresults.dto;

import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.DASH;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.TextField;
import java.util.List;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;

/**
 *
 * @author sooriya.lakshmanan
 */
public class FilterGenerator implements ExtFilterGenerator {

    protected SessionDTO session;
    protected boolean isTotal;
    protected ComboBox group = null;

    public FilterGenerator(SessionDTO session, boolean isTotal) {
        this.session = session;
        this.isTotal = isTotal;
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
            ComboBox groupDdlb = getCombobox();
            if (!isTotal) {
                groupDdlb.setEnabled(true);
            } else {
                groupDdlb.setEnabled(false);
            }
            return groupDdlb;
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

    private ComboBox getCombobox() {
        if (group == null) {
            group = new ComboBox();
            List<String> groupList = CommonLogic.getAllGroup(session, false);
            if (groupList != null && !groupList.isEmpty()) {
                for (String groups : groupList) {
                    group.addItem(groups);
                }
                group.select(groupList.get(0));
            }
            group.setImmediate(true);
            group.setNullSelectionAllowed(true);
            group.setNullSelectionItemId(DASH);
            group.addStyleName(Constant.FILTER_COMBOBOX);
            group.setPageLength(NumericConstants.SEVEN);
        }
        return group;
    }
}
