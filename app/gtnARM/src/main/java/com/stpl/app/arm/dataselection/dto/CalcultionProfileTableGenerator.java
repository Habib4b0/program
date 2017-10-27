package com.stpl.app.arm.dataselection.dto;

import org.jboss.logging.Logger;

import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.app.utils.CommonUtils;
import static com.stpl.app.utils.CommonUtils.DASH;
import com.vaadin.data.Container;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;

/**
 * The Class CalcultionProfileTableGenerator to Generate Field Factory.
 */
public class CalcultionProfileTableGenerator extends DefaultFieldFactory {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(CalcultionProfileTableGenerator.class);

    /**
     * The common util.
     */
    /**
     * To create the fields for the particular Table cell.
     *
     * @param container
     * @param itemId
     * @param propertyId
     * @param uiContext
     * @return
     */
    @Override
    public Field<?> createField(final Container container, final Object itemId, final Object propertyId, final Component uiContext) {
        Field<?> field = null;

        if ("include".equals(propertyId)) {
            final CheckBox checkbox = new CheckBox();
            checkbox.setReadOnly(false);
            checkbox.setImmediate(true);
            field = checkbox;
        }
        if ("accountType".equals(propertyId)) {
            ComboBox indicator = new ComboBox();
            CommonUtils.loadComboBoxWithIntegerForComboBox(indicator, "ARM_ACCOUNT_TYPE", false);
            field = indicator;
        }
        if ("indicator".equals(propertyId)) {
            ComboBox indicator = new ComboBox();
            loadComboBoxWithIntegerForIndicator(indicator, false);
            field = indicator;
        }

        // Otherwise use the default field factory
        return field;
    }

    public static ComboBox loadComboBoxWithIntegerForIndicator(final ComboBox select, boolean isFilter) {
        try {
            select.setImmediate(true);
            select.setNullSelectionAllowed(false);
            select.addItem(0);
            select.setItemCaption(0, isFilter ? ConstantsUtils.SHOW_ALL : ConstantsUtils.SELECT_ONE);

            select.addItem(1);
            select.setItemCaption(1, "+");

            select.addItem(-1);
            select.setItemCaption(-1, "-");

            select.select(0);
            select.markAsDirty();
            select.setDescription((String) (select.getValue() == DASH ? ConstantsUtils.SELECT_ONE : select.getItemCaption(select.getValue())));
        } catch (Exception e) {
            LOGGER.error("Error while loading Drop down with :" + e);
        }
        return select;
    }

}
