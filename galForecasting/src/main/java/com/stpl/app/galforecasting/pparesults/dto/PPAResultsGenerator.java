/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.pparesults.dto;

import com.stpl.app.galforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.logic.CommonLogic;
import com.stpl.app.galforecasting.utils.Constant;
import static com.stpl.app.galforecasting.utils.Constant.DASH;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;
import java.util.List;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;

/**
 *
 * @author mohamed.hameed
 */
public class PPAResultsGenerator implements ExtFilterGenerator {

    ProjectionSelectionDTO projectionSelectionDTO;

    public PPAResultsGenerator(ProjectionSelectionDTO projectionSelectionDTO) {
        this.projectionSelectionDTO = projectionSelectionDTO;
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
            try {
                ComboBox groupDdlb = new ComboBox();
                List<String> groupList = CommonLogic.getAllPPAGroup(projectionSelectionDTO.getProjectionId(), projectionSelectionDTO.getUserId(), projectionSelectionDTO.getSessionId());
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
                return groupDdlb;
            } catch (Exception ex) {

            }
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
