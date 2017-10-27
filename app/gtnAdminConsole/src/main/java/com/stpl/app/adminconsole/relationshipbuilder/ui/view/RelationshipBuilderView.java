/**
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package com.stpl.app.adminconsole.relationshipbuilder.ui.view;

import com.stpl.app.adminconsole.common.dto.SessionDTO;
import org.jboss.logging.Logger;
import com.stpl.app.adminconsole.relationshipbuilder.dto.RelationshipBuilderDTO;
import com.stpl.app.adminconsole.relationshipbuilder.logic.RelationBuilderLogic;
import com.stpl.app.adminconsole.relationshipbuilder.ui.form.RelationshipBuilderTree;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.ErrorCodeUtil;
import com.stpl.app.adminconsole.util.ErrorCodes;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class RelationshipBuilderView.
 *
 * @author vishalakshi
 */
public class RelationshipBuilderView extends VerticalLayout implements View {

    private static final Logger LOGGER = Logger.getLogger(RelationshipBuilderView.class);

    public static final String NAME = "RelationBuilder";

    private RelationshipBuilderDTO relationshipBuilderDTO = new RelationshipBuilderDTO();

    private CustomFieldGroup relationshipBuilderBinder = new CustomFieldGroup(new BeanItem<>(relationshipBuilderDTO));

    private RelationshipBuilderTree relationBuilderInfo;

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

    public RelationshipBuilderTree getRelationBuilderInfo() {
        return relationBuilderInfo;
    }

    public void setRelationBuilderInfo(final RelationshipBuilderTree relationBuilderInfo) {
        this.relationBuilderInfo = relationBuilderInfo;
    }

    /**
     * Instantiates a new relationship builder view.
     *
     * @param sessionDTO
     * @throws Exception
     * @throws SystemException
     */
    public RelationshipBuilderView(final SessionDTO sessionDTO) throws SystemException {
        super();
        try {
            this.sessionDTO = sessionDTO;
            relationBuilderInfo = new RelationshipBuilderTree(relationshipBuilderBinder, relationshipBuilderDTO, sessionDTO);
            setSpacing(true);
            addComponent(relationBuilderInfo);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * This method is always called before the view is shown on screen.
     */
    public void enter(final ViewChangeListener.ViewChangeEvent event) {
        LOGGER.debug("enter ViewChangeListener Started");
        final int rbSystemId = sessionDTO.getSystemId();
        try {
            this.removeAllComponents();
            relationshipBuilderDTO = new RelationshipBuilderDTO();
            relationshipBuilderBinder = new CustomFieldGroup(new BeanItem<>(relationshipBuilderDTO));
            relationBuilderInfo = new RelationshipBuilderTree(relationshipBuilderBinder, relationshipBuilderDTO, sessionDTO);
            addComponent(relationBuilderInfo);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        if (rbSystemId == ConstantsUtils.ZERO_NUM) {
            relationshipBuilderBinder = new CustomFieldGroup(new BeanItem<>(relationshipBuilderDTO));

        } else {

            try {
                relationshipBuilderDTO = new RelationBuilderLogic().getRelationBuilderInfo(sessionDTO);
                relationshipBuilderBinder = new CustomFieldGroup(new BeanItem<>(relationshipBuilderDTO));
            } catch (SystemException ex) {
                final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                LOGGER.error(ex);
            } catch (PortalException ex) {
                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4011));
                LOGGER.error(ex);

            } catch (Exception ex) {
                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4011));
                LOGGER.error(ex);
            }
        }
        relationBuilderInfo.entry();
        relationBuilderInfo.setFocus();
        LOGGER.debug("enter ViewChangeListener Ended");
    }
}
