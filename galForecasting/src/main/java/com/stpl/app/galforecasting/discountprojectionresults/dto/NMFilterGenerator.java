/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.discountprojectionresults.dto;

import static com.stpl.app.galforecasting.discountprojectionresults.logic.NMDPRLogic.executeSelectQuery;
import com.stpl.app.galforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.logic.DiscountProjectionResultsLogic;
import com.stpl.app.galforecasting.utils.Constant;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;

/**
 *
 * @author vigneshkanna
 */
public class NMFilterGenerator implements ExtFilterGenerator {

    ProjectionSelectionDTO projectionSelectionDTO;

    public NMFilterGenerator(ProjectionSelectionDTO projectionSelectionDTO) {
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
                DiscountProjectionResultsLogic logic = new DiscountProjectionResultsLogic();
                IndexedContainer con = new IndexedContainer();
                ComboBox groupDdlb = new ComboBox();
                con.removeAllItems();
                String query = logic.getGroup(projectionSelectionDTO.getProjectionId(), projectionSelectionDTO.getSessionId());
                List<Object> list = (List<Object>) executeSelectQuery(query, null, null);
                if (list != null && list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        con.addItem(Constant.DISCOUNT + list.get(i));
                    }
                }
                groupDdlb.setContainerDataSource(con);
                groupDdlb.setImmediate(true);
                groupDdlb.setNullSelectionAllowed(true);
                groupDdlb.setNullSelectionItemId(Constant.DASH);
                groupDdlb.addStyleName(Constant.FILTER_COMBOBOX);
                groupDdlb.setPageLength(7);
                return groupDdlb;
            } catch (SystemException ex) {
                Logger.getLogger(NMFilterGenerator.class.getName()).log(Level.SEVERE, null, ex);
            } catch (PortalException ex) {
                Logger.getLogger(NMFilterGenerator.class.getName()).log(Level.SEVERE, null, ex);
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
