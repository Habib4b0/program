/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.fileSelection.dto;

import com.stpl.app.cff.dto.SessionDTO;
import com.stpl.app.cff.ui.fileSelection.FileManagementLookup.FileManagementLookup;
import com.stpl.app.cff.ui.fileSelection.Util.ConstantsUtils;
import com.stpl.app.cff.util.Constants;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.DateField;
import com.vaadin.v7.ui.DefaultFieldFactory;
import com.vaadin.v7.ui.Field;
import org.asi.ui.customtextfield.CustomTextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author porchelvi.g
 */
public class FileSelectionTableGenerator extends DefaultFieldFactory {

    private final BeanItemContainer searchContainer;
    private CustomTextField fileName;
    private final SessionDTO session;
    private final String businessUnit;
    private static final Logger LOGGER = LoggerFactory.getLogger(FileSelectionTableGenerator.class);

    public FileSelectionTableGenerator(BeanItemContainer searchContainer,SessionDTO sessionDTO,String businessUnit ){
        this.searchContainer = searchContainer;
        this.session=sessionDTO;
        this.businessUnit=businessUnit;

    }

    @Override
    public Field<?> createField(final Container container, final Object itemId, final Object propertyId, final Component uiContext) {

        if (propertyId.equals("fileName")) {
            fileName = new CustomTextField();
            fileName.setImmediate(true);
            fileName.addStyleName(ConstantsUtils.SEARCH_ICON);
            if (!session.getAction().equals(Constants.ADD)) {
                fileName.setEnabled(false);
            }
            fileName.addClickListener(new CustomTextField.ClickListener() {
                /**
                 * Method used for formulaNo
                 */
                @Override
                public void click(final CustomTextField.ClickEvent event) {
                    String fileType = String.valueOf(searchContainer.getContainerProperty(itemId, "fileType").getValue());

                    try {
                        final FileManagementLookup lookUp = new FileManagementLookup(session, false, fileType, searchContainer, itemId,businessUnit);
                        lookUp.init();

                        UI.getCurrent().addWindow(lookUp);
                        /**
                         * the CloseListener to add.
                         */
                        lookUp.addCloseListener(new Window.CloseListener() {
                            /**
                             * Executed by clicking Close .
                             *
                             */
                            @Override
                            public void windowClose(final Window.CloseEvent e) {
                                return;
                            }
                        });

                    } catch (IllegalArgumentException | NullPointerException ex) {
                        LOGGER.error(ex.getMessage());
                    }
                }
            });
            return fileName;
        }
        final Field field = super.createField(container, itemId, propertyId, uiContext);
        field.setStyleName("align-center");
        field.setWidth("280px");
        field.setReadOnly(true);
        if(propertyId.toString().equals("activeFromDate")||propertyId.toString().equals("activeToDate")){
            DateField d=(DateField)field;
            d.setDateFormat("MM/dd/yyyy");
        }
        // Otherwise use the default field factory
        return field;
    }
}
