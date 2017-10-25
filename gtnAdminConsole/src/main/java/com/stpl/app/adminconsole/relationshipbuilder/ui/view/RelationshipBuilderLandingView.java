/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.relationshipbuilder.ui.view;

import com.stpl.app.adminconsole.abstractsearch.ui.AbstractSearchForm;
import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.relationshipbuilder.dto.RelationshipBuilderDTO;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;
import org.jboss.logging.Logger;

/**
 * The Class RelationshipBuilderLandingView.
 *
 * @author nisanthan
 */
public class RelationshipBuilderLandingView extends VerticalLayout implements View {

    private static final Logger LOGGER = Logger.getLogger(RelationshipBuilderLandingView.class);

    public static final String NAME = "";

    private RelationshipBuilderDTO relationshipBuilderDTO = new RelationshipBuilderDTO();

    private CustomFieldGroup relationshipBuilderBinder = new CustomFieldGroup(new BeanItem<RelationshipBuilderDTO>(relationshipBuilderDTO));

    SessionDTO sessionDTO;

    public RelationshipBuilderDTO getRelationshipBuilderDTO() {
        return relationshipBuilderDTO;
    }

    public void setRelationshipBuilderDTO(final RelationshipBuilderDTO relationshipBuilderDTO) {
        this.relationshipBuilderDTO = relationshipBuilderDTO;
    }

    public CustomFieldGroup getRelationshipBuilderBinder() {
        return relationshipBuilderBinder;
    }

    public void setRelationshipBuilderBinder(final CustomFieldGroup relationshipBuilderBinder) {
        this.relationshipBuilderBinder = relationshipBuilderBinder;
    }

    /**
     * Instantiates a new relationship builder landing view.
     *
     * @param sessionDTO
     * @throws Exception
     * @throws SystemException
     */
    public RelationshipBuilderLandingView(final SessionDTO sessionDTO) throws SystemException, PortalException {
        super();
        this.sessionDTO = sessionDTO;
        setSpacing(true);
        addComponent(new AbstractSearchForm(ConstantsUtils.RELATIONSHIP_BUILDER, sessionDTO));
        setStyleName("bootstrap");
    }

    public void enter(final ViewChangeListener.ViewChangeEvent event) {

        try {
            setStyleName("bootstrap");
            setSpacing(true);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

    }

}
