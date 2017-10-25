/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.company.dto;

import com.stpl.app.global.item.util.CommonUtils;
import com.vaadin.data.Container;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;


// TODO: Auto-generated Javadoc
/**/
/**
 * The Class ParentCompanyTableGenerator.
 */
public class ParentCompanyTableGenerator extends DefaultFieldFactory {

    /** The commons util. */
    public CommonUtils commonsUtil = new CommonUtils();
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
   
   

    /** (non-Javadoc)
     * 
     */
    public Field<?> createField(final Container container, final Object itemId,
            final Object propertyId, final Component uiContext) {



        if ("parentCompanyNo".equals(propertyId)) {

            final TextField parentCompanyNo = new TextField();
            parentCompanyNo.setReadOnly(true);
       

            return parentCompanyNo;
        }
        if ("parentStartDate".equals(propertyId)) {

           final PopupDateField startDate = new PopupDateField();
            startDate.setDateFormat("MM/dd/yyyy");

            startDate.setRequiredError("Start Date should  be present");

            return startDate;
        }
        if ("parentEndDate".equals(propertyId)) {

           final PopupDateField endDate = new PopupDateField();
            endDate.setDateFormat("MM/dd/yyyy");

            return endDate;
        }

        @SuppressWarnings("rawtypes")
        final Field field = super.createField(container, itemId, propertyId,
                uiContext);
        field.setReadOnly(true);
        field.setSizeFull();
        // Otherwise use the default field factory
        return field;
    }
}
