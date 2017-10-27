package com.stpl.app.global.cfp.util;

import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.HelperDTO;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.ComboBox;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 * The Class CommonUtils.
 */
public class CommonUtils {

    /** The logger. */
    private static final Logger LOGGER = Logger.getLogger(CommonUtils.class);
    
    /** The Constant STRING_ASTERISK. */
    public static final String STRING_ASTERISK = "*";
    
    /** The Constant MMDDYYYY. */
    public static final String MMDDYYYY = ConstantsUtils.DATE_FORMAT;
    
    /** The Constant MMDDYYYYHHMMMSS. */
    public static final String MMDDYYYYHHMMMSS = "yyyy-MM-dd HH:mm:ss";
    
    /** The Constant STRING_NULL. */
    public static final String STRING_NULL = ConstantsUtils.NULL;
    
    /** The Constant STRING_ZERO. */
    public static final String STRING_ZERO = ConstantsUtils.ZERO;
    
    /** The Constant CHAR_PERCENT. */
    public static final char CHAR_PERCENT = '%';
    
    /** The Constant CHAR_ASTERISK. */
    public static final char CHAR_ASTERISK = '*';
      /** The Constant Active. */
    public static final String ACTIVE = "Active";
      /** The Constant Inactive. */
    public static final String IN_ACTIVE = "InActive";

    /**
     * Method loads option to NativeSelect.
     *
     * @param select the select
     * @param helperList the helper list
     * @return the native select
     */
    public ComboBox getNativeSelect(final ComboBox select, final List<HelperDTO> helperList) {
        select.setPageLength(NumericConstants.SEVEN);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        select.addItem(ConstantsUtils.SELECT_ONE);
        for (int i = 0; i < helperList.size(); i++) {
			final HelperDTO helperDTO = helperList.get(i);
			select.addItem(helperDTO.getDescription());
		}
        select.select(ConstantsUtils.SELECT_ONE);
        select.addValueChangeListener(new Property.ValueChangeListener() {
            
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue()!=null && StringUtils.EMPTY.equals(event.getProperty().getValue())) {
                    select.select(ConstantsUtils.SELECT_ONE);
                }
            }
        });
        return select;
    }


    /**
     * Method loads search option to NativeSelect.
     *
     * @return the container
     */
    public Container searchFields() {
        LOGGER.debug("Entering CommonUtils searchFields");
        final List<String> list = new ArrayList<>();
        list.add(ConstantsUtils.SELECT_ONE);        
		list.add("Company Name");
                list.add("Company No");
		list.add("Company Status");
		list.add("Company Type");                
        LOGGER.debug("return Container");
        return new IndexedContainer(list);
    }

    /**
     * Method sets NativeSelect to null.
     *
     * @param select the select
     * @return the selet null
     */
    public static ComboBox getSeletNull(final ComboBox select) {
        LOGGER.debug("Entering CommonUtils getSeletNull");
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        LOGGER.debug("return NativeSelect");
        return select;
    }

    
        public static String getDateForSession() {
        SimpleDateFormat dt = new SimpleDateFormat("yyyyymmddhhmmssms");
        return dt.format(new Date());
    }
         public ComboBox getNativeSelect1(final ComboBox select, final List<HelperDTO> helperList) {
            
        select.addItem(0);
        select.setItemCaption(0, ConstantsUtils.SELECT_ONE);
       for (int i = 0; i < helperList.size(); i++) {
            final HelperDTO helperDTO = helperList.get(i);
            select.addItem(helperDTO.getId());
            select.setItemCaption(helperDTO.getId(), helperDTO.getDescription());
        }
        return select;
            
    }
         /**
     * To get the combo box select.
     *
     * @param select the select
     * @param helperList the helper list
     * @return the native select
     */
    public ComboBox getComboBox(final ComboBox select,
            final List<HelperDTO> helperList) {
        select.setPageLength(NumericConstants.SEVEN);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(ConstantsUtils.ZERO_INT);
        select.addItem(0);
        select.setItemCaption(0, ConstantsUtils.SELECT_ONE);
        for (int i = 0; i < helperList.size(); i++) {
            final HelperDTO helperDTO = helperList.get(i);
            select.addItem(helperDTO.getSystemId());
            select.setItemCaption(helperDTO.getSystemId(), helperDTO.getDescription());
        }
        select.addValueChangeListener(new Property.ValueChangeListener() {
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue()) || ConstantsUtils.NULL.equals(event.getProperty().getValue()))) {
                    select.select(ConstantsUtils.SELECT_ONE);
                }
            }
        });
        return select;
    }
    
    public ComboBox getNativeSelectForFilter(final ComboBox select, final List<HelperDTO> helperList) {
            
        select.addItem(0);
        select.setItemCaption(0,ConstantsUtils.SHOW_ALL_CAPS);
       for (int i = 0; i < helperList.size(); i++) {
            final HelperDTO helperDTO = helperList.get(i);
            select.addItem(helperDTO.getId());
            select.setItemCaption(helperDTO.getId (), helperDTO.getDescription());
        }
        return select;
            
    }
    public ComboBox getSelectedNativeSelect(final ComboBox select, final HelperDTO helperDTO) {

        select.addItem(0);
        select.setItemCaption(0, ConstantsUtils.SHOW_ALL_CAPS);
        select.addItem(helperDTO.getId());
        select.setItemCaption(helperDTO.getId(), helperDTO.getDescription());
        return select;

    }
}
