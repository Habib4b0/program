/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.ui;

import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.navigator.View;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author vinoth
 */
public abstract class AbstractSearchForm extends CustomComponent implements View {
	/** versionId */
    private static final long serialVersionUID = 1L;
    /** Space label */
    private Label space = new Label("&nbsp;", ContentMode.HTML);

    /**
     *
     * @param searchResultbeans the results that will be displayed in table
     * @param table
     */
    public AbstractSearchForm() {
        init();
    }

    /**
     * method to set space height and configures and adds the content of the
     * form
     */
    private void init() {
        space.setHeight("30");
    }

    /**
     * method sets space between the component vertically
     *
     * @return
     */
    protected VerticalLayout addToContentAbstract() {
        final VerticalLayout content = new VerticalLayout();        
        setCompositionRoot(content);
        return content;
    }

    /**
     * Grid Layout to define your form in a grid Layout. Add your form
     * components here
     *
     * @return GridLayout
     */
    protected abstract GridLayout addToGrid() throws SystemException, PortalException, Exception;

    /**
     * Configure your form fields here
     */
    protected abstract void configureFields() throws SystemException;
    
    /**
     * Gets the space.
     *
     * @return the space
     */
    public Label getSpace() {
        return space;
    }

    /**
     * Sets the space
     *
     */
    public void setSpace(final Label space) {
        this.space = space;
    }      
}
