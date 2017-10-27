/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.projectionVariance.dto;

import com.stpl.app.cff.logic.CommonLogic;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;
import java.util.List;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import com.stpl.app.cff.dto.ProjectionSelectionDTO;
import com.stpl.ifs.ui.util.NumericConstants;

/**
 *
 * @author sooriya.lakshmanan
 */
public class FilterGenerator implements ExtFilterGenerator {

    ProjectionSelectionDTO projectionSelectionDTO;
    boolean isTotal;

    public FilterGenerator(ProjectionSelectionDTO projectionSelectionDTO, boolean isTotal) {
        this.projectionSelectionDTO = projectionSelectionDTO;
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
        if ("group".equals(propertyId)) {
            ComboBox groupDdlb = new ComboBox();
            if (!isTotal) {
                List<String> groupList = CommonLogic.getAllGroup(projectionSelectionDTO.getProjectionId(), projectionSelectionDTO.getUserId(), projectionSelectionDTO.getSessionId(), projectionSelectionDTO.isPpa());
                if (groupList != null && !groupList.isEmpty()) {
                    for (String groups : groupList) {
                        groupDdlb.addItem(groups);
                    }
                    groupDdlb.select(groupList.get(0));
                }
                groupDdlb.setImmediate(true);
                groupDdlb.setNullSelectionAllowed(true);
                groupDdlb.setNullSelectionItemId("0");
                groupDdlb.addStyleName("filterCombobox");
                groupDdlb.setPageLength(NumericConstants.SEVEN);
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
}
