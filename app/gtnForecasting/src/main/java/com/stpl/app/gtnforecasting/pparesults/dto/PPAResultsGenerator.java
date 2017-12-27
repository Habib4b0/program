/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.pparesults.dto;

import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import static com.stpl.app.gtnforecasting.logic.CommonLogic.LOGGER;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.DASH;
import com.stpl.ifs.ui.util.NumericConstants;
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
                List<String> groupList = CommonLogic.getAllPPAGroup();
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
                groupDdlb.setPageLength(NumericConstants.SEVEN);
                return groupDdlb;
            } catch (UnsupportedOperationException ex) {
                LOGGER.error(ex);
            }
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
