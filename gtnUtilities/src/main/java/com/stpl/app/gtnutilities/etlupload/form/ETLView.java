/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnutilities.etlupload.form;

import com.stpl.app.gtnutilities.util.Constants;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;
import org.jboss.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 *
 * @author Karthik.Raja
 */
public class ETLView extends VerticalLayout implements View {

    /**
     * String name.
     */
    public static final String NAME = Constants.EMPTY;

    /**
     * Constructor reference for FileUploadForm.
     */
    private FileUploadForm fileUploadForm;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(FileUploadForm.class); 

    /**
     * constructor ETLView.
     */
    public ETLView()  {
      
        super();
        LOGGER.debug("Inside ETLView");
        fileUploadForm = new FileUploadForm();
        setSpacing(true);
        addComponent(fileUploadForm);
        setStyleName("bootstrap");

    }

    /**
     * enter method.
     *
     * @param event the event
     */
    public void enter(final ViewChangeListener.ViewChangeEvent event) {
        try {
            this.removeAllComponents();
            fileUploadForm = new FileUploadForm();
            addComponent(fileUploadForm);

        } catch (Exception ex) {
             LOGGER.error(ex);
        }
    }
}
