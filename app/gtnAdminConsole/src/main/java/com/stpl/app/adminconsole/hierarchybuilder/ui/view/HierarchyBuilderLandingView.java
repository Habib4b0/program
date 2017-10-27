/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.hierarchybuilder.ui.view;

import com.stpl.app.adminconsole.abstractsearch.ui.AbstractSearchForm;
import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.hierarchybuilder.dto.HierarchyBuilderDTO;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;
import org.jboss.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class HierarchyBuilderLandingView.
 *
 * @author nisanthan
 */
public class HierarchyBuilderLandingView extends VerticalLayout implements View {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(HierarchyBuilderLandingView.class);
    /**
     * The Constant NAME.
     */
    public static final String NAME = ConstantsUtils.EMPTY;
    /**
     * The hierarchy builder dto.
     */
    private HierarchyBuilderDTO hierarchyBuilderDTO = new HierarchyBuilderDTO();
    /**
     * The hierarchy builder binder.
     */
    private CustomFieldGroup hierarchyBuilderBinder = new CustomFieldGroup(new BeanItem<>(hierarchyBuilderDTO));

    /**
     * Gets the hierarchy builder binder.
     *
     * @return the hierarchy builder binder
     */
    public CustomFieldGroup getHierarchyBuilderBinder() {
        return hierarchyBuilderBinder;
    }

    /**
     * Sets the hierarchy builder binder.
     *
     * @param hierarchyBuilderBinder the hierarchy builder binder
     */
    public void setHierarchyBuilderBinder(final CustomFieldGroup hierarchyBuilderBinder) {
        this.hierarchyBuilderBinder = hierarchyBuilderBinder;
    }

    /**
     * Gets the hierarchy builder dto.
     *
     * @return the hierarchy builder dto
     */
    public HierarchyBuilderDTO getHierarchyBuilderDTO() {
        return hierarchyBuilderDTO;
    }

    SessionDTO sessionDTO;

    /**
     * Instantiates a new hierarchy builder landing view.
     *
     * @param sessionDTO
     * @throws Exception
     * @throws PortalException
     * @throws SystemException
     */
    public HierarchyBuilderLandingView(final SessionDTO sessionDTO) throws SystemException, PortalException {
        super();
        setStyleName("bootstrap");
        setSpacing(true);
        this.sessionDTO = sessionDTO;
        addComponent(new AbstractSearchForm(ConstantsUtils.HIERARCHY_DEF, sessionDTO));

    }

    /**
     * This method is always called before the view is shown on screen.
     *
     * @param event the event
     */
    public void enter(final ViewChangeEvent event) {

        try {
            LOGGER.debug("enter method started");
            setStyleName("bootstrap");
            LOGGER.debug("enter method ended");
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }
}
