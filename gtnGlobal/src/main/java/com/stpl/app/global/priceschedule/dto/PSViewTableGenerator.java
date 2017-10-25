package com.stpl.app.global.priceschedule.dto;

import org.jboss.logging.Logger;

import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.vaadin.data.Container;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;

// TODO: Auto-generated Javadoc
/**
 * This class is used to customize the fields in Table Columns.
 *
 * @author manikanta
 */
public class PSViewTableGenerator extends DefaultFieldFactory {

    /**
     * Default generated serial ID.
     */
    private static final long serialVersionUID = 1L;
    
    /** The Constant LOGGER. */
    private static final Logger LOGGER = Logger.getLogger(PSViewTableGenerator.class.getName());

    /**
     * This method is used to create a Filed from the field factory.
     *
     * @param container the container
     * @param itemId the item id
     * @param propertyId the property id
     * @param uiContext the ui context
     * @return the field
     */
    public Field<?> createField(final Container container, final Object itemId,
            final Object propertyId, final Component uiContext) {
        try {
            if ("checkbox".equals(propertyId)) {
                final CheckBox checkbox = new CheckBox();
                checkbox.setReadOnly(false);
                checkbox.setEnabled(false);
                return checkbox;
            }
        } catch (Exception exception) {
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1010), new MessageBoxListener() {    
                /**            
                 * The method is triggered when a button of the message box is   
                 * pressed .        
                 *          
                 * @param buttonId The buttonId of the pressed button.     
                 */           
                @SuppressWarnings("PMD")           
                public void buttonClicked(final ButtonId buttonId) { 
                    // Do Nothing     
                }           
            }, ButtonId.OK);  
            msg.getButton(ButtonId.OK).focus();
            LOGGER.error(exception);

        }
        final Field<?> field = super.createField(container, itemId, propertyId,
                uiContext);
        field.setReadOnly(true);
        return field;
    }
}
