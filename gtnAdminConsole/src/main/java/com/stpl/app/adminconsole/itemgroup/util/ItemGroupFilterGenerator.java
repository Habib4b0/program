
package com.stpl.app.adminconsole.itemgroup.util;

import com.stpl.app.serviceUtils.ConstantsUtils;
import com.vaadin.data.Container;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.jboss.logging.Logger;
import com.stpl.app.adminconsole.util.CommonUtils;

/**
 *
 * @author sriram
 */
public class ItemGroupFilterGenerator implements ExtFilterGenerator{
private static Logger LOGGER = Logger.getLogger(ItemGroupFilterGenerator.class);
    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {
        return null;
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
        return null;
    }

    @Override
    public AbstractField<?> getCustomFilterComponent(Object propertyId) {
        try {
            if ("companyDdlb".equals(propertyId)) {
                ComboBox comboBox = new ComboBox();
                comboBox.setImmediate(true);
                comboBox.addItem(ConstantsUtils.SHOW_ALL);
                comboBox = CommonUtils.getCompany(comboBox);
                comboBox.setNullSelectionAllowed(true);
                comboBox.setNullSelectionItemId(ConstantsUtils.SHOW_ALL);
                return comboBox;
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
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
        LOGGER.error("Item group filterGeneratorFailed");
        return null;
    }
    
}
