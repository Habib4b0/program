/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.filemanagement.ui.view;

import com.stpl.app.adminconsole.common.dto.SessionDTO;
import org.jboss.logging.Logger;

import com.stpl.app.adminconsole.filemanagement.ui.form.FileManagementIndex;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;

// TODO: Auto-generated Javadoc
/**
 * The Class FileManagementIndexView.
 *
 * @author Elangovan
 */
public class FileManagementIndexView extends VerticalLayout implements View {

    /**
     * The Constant NAME.
     */
    public static final String NAME = ConstantsUtils.EMPTY;
    /**
     * The Constant LOGGER.
     */
    public static final Logger LOGGER = Logger.getLogger(FileManagementIndexView.class);
    SessionDTO sessionDTO;

    /**
     * Instantiates a new file management index view.
     */
    public FileManagementIndexView(final SessionDTO sessionDTO) {
        super();
        LOGGER.debug("FileManagementIndexView Constructor Entered");
        setSpacing(true);
        this.sessionDTO = sessionDTO;
        try{
        addComponent(new FileManagementIndex(this.sessionDTO));
        LOGGER.debug("FileManagementIndexView Constructor Ended");
        }
        catch(Exception e){
            LOGGER.error(e);
        }
    }

    /**
     * Event received by the listener for attempted and executed view changes.
     */
    public void enter(final ViewChangeListener.ViewChangeEvent event) {
        markAsDirty();
    }
}
