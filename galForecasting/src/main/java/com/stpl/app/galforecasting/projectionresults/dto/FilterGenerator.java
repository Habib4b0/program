/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.projectionresults.dto;

import com.stpl.app.galforecasting.logic.CommonLogic;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;
import java.util.List;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import com.stpl.app.galforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.utils.Constant;
import static com.stpl.app.galforecasting.utils.Constant.DASH;

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
        if (Constant.GROUP.equals(propertyId)) {
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
                groupDdlb.setNullSelectionItemId(DASH);
                groupDdlb.addStyleName(Constant.FILTER_COMBOBOX);
                groupDdlb.setPageLength(7);
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
       
    }

    @Override
    public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
      
    }

    @Override
    public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
      
        return null;
    }
}
