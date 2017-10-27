/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.fileSelection.dto;

import com.stpl.app.cff.dto.SessionDTO;
import com.stpl.app.cff.lazyLoad.FileSelectionTableLogic;
import com.stpl.app.cff.ui.fileSelection.FileManagementLookup.FileManagementLookup;
import com.stpl.app.cff.ui.fileSelection.Util.ConstantsUtils;
import com.stpl.app.cff.util.Constants;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;

/**
 *
 * @author porchelvi.g
 */
public class FileSelectionTableGenerator extends DefaultFieldFactory {

    private BeanItemContainer searchContainer;
    private CustomTextField fileName;
    SessionDTO session;
    String businessUnit;
    private static final Logger LOGGER = Logger.getLogger(FileSelectionTableGenerator.class);

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
                public void click(final CustomTextField.ClickEvent event) {
                    String fileType = String.valueOf(searchContainer.getContainerProperty(itemId, "fileType").getValue());
                    String country = String.valueOf(searchContainer.getContainerProperty(itemId, "country").getValue());

                    try {
                        final FileManagementLookup lookUp = new FileManagementLookup("", session, false, fileType, country, searchContainer, itemId,businessUnit);
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
                            public void windowClose(final Window.CloseEvent e) {
                                return;
                            }
                        });

                    } catch (Exception ex) {
                        LOGGER.error(ex);
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
