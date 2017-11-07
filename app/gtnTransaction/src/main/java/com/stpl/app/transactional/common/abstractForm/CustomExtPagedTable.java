/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.transactional.common.abstractForm;

import com.stpl.domain.transaction.base.HasItemId;
import com.vaadin.data.Property;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.data.util.converter.ConverterUtil;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author Vignesh.Kanna
 */
public class CustomExtPagedTable extends ExtPagedTable{

    public CustomExtPagedTable(PageTableLogic logic) {
        super(logic);
    }
    /**
     * Formats table cell property values. By default the property.toString()
     * and return a empty string for null properties.
     *
     * @param rowId the Id of the row (same as item Id).
     * @param colId the Id of the column.
     * @param property the Property to be formatted.
     * @return the String representation of property and its value.
     * @since 3.1
     */
    @Override
    protected String formatPropertyValue(Object rowId, Object colId,
            Property<?> property) {
        if (property == null) {
            return "";
        }
        Converter<String, Object> converter = null;

        if (hasConverter(colId)) {
            converter = getConverter(colId);
        } else {
            converter = (Converter) ConverterUtil.getConverter(String.class,
                    property.getType(), getSession());
        }
        Object value = property.getValue();
        if (converter != null) {
            if(converter instanceof HasItemId){
                ((HasItemId)converter).setItemId(rowId);
            }
            String retValue=converter.convertToPresentation(value, String.class,
                    getLocale());
            if(converter instanceof HasItemId){
                ((HasItemId)converter).setItemId(null);                
            }
            return retValue;
        }
        return (null != value) ? value.toString() : "";
    }
}
