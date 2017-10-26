/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.hierarchybuilder.ui.view;

import org.jboss.logging.Logger;

import com.stpl.app.adminconsole.hierarchybuilder.dto.HierarchyBuilderDTO;
import com.stpl.app.adminconsole.hierarchybuilder.dto.HierarchyBuilderLevelDTO;
import com.stpl.app.adminconsole.hierarchybuilder.logic.HierarchyBuilderLogic;
import com.stpl.app.adminconsole.hierarchybuilder.ui.form.ViewHierarchyBuilderDefinition;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.ErrorCodeUtil;
import com.stpl.app.adminconsole.util.ErrorCodes;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.VerticalLayout;

// TODO: Auto-generated Javadoc
/**
 * The Class HierarchyBuilderView.
 *
 * @author nisanthan
 */
public class HierarchyBuilderView extends VerticalLayout implements View {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(HierarchyBuilderView.class);

    /**
     * The Constant NAME.
     */
    public static final String NAME = "HierarchyDefinitionVIEW";

    /**
     * The hierarchy builder dto.
     */
    private HierarchyBuilderDTO hierarchyBuilderDTO = new HierarchyBuilderDTO();

    /**
     * The hierarchy builder level dto bean.
     */
    private final BeanItemContainer<HierarchyBuilderLevelDTO> hierarchyBuilderLevelDTOBean = new BeanItemContainer<HierarchyBuilderLevelDTO>(HierarchyBuilderLevelDTO.class);

    /**
     * Gets the hierarchy builder dto.
     *
     * @return the hierarchy builder dto
     */
    public HierarchyBuilderDTO getHierarchyBuilderDTO() {
        return hierarchyBuilderDTO;
    }

    /**
     * Sets the hierarchy builder dto.
     *
     * @param hierarchyBuilderDTO the hierarchy builder dto
     */
    public void setHierarchyBuilderDTO(final HierarchyBuilderDTO hierarchyBuilderDTO) {
        this.hierarchyBuilderDTO = hierarchyBuilderDTO;
    }

    /**
     * Gets the hierarchy builder level dto bean.
     *
     * @return the hierarchy builder level dto bean
     */
    public BeanItemContainer<HierarchyBuilderLevelDTO> getHierarchyBuilderLevelDTOBean() {
        return hierarchyBuilderLevelDTOBean;
    }

    /**
     * Instantiates a new hierarchy builder view.
     */
    public HierarchyBuilderView() {
        super();
        LOGGER.debug("HierarchyBuilderView Constructor started");
        setSpacing(true);
        addComponent(new ViewHierarchyBuilderDefinition(hierarchyBuilderLevelDTOBean, hierarchyBuilderDTO));
        setStyleName("bootstrap");
        LOGGER.debug("HierarchyBuilderView Constructor started");
    }

    /**
     * This method is always called before the view is shown on screen.
     *
     * @param event the event
     */
    public void enter(final ViewChangeEvent event) {
        LOGGER.debug("In enter ViewChangeEvent Started");
        try {
            markAsDirty();
            this.removeAllComponents();
            final CustomFieldGroup hierarchyBuilderBinder = new CustomFieldGroup(new BeanItem<HierarchyBuilderDTO>(hierarchyBuilderDTO));
            final int systemId = Integer.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.SYS_ID).toString());
            final HierarchyBuilderLogic hierarchyLogic = new HierarchyBuilderLogic();
            hierarchyLogic.deleteTempLevelValues();
            final String view = VaadinSession.getCurrent().getAttribute(ConstantsUtils.FRONT_VIEW_PAGE).toString();
            if (view.equalsIgnoreCase(ConstantsUtils.YES)) {
                final int version = (Integer) VaadinSession.getCurrent().getAttribute(ConstantsUtils.VERSION_NO);
                hierarchyBuilderDTO = hierarchyLogic.getHistHierarchyBuilderDTO(Integer.valueOf(systemId), version);
                hierarchyBuilderBinder.setItemDataSource(new BeanItem<HierarchyBuilderDTO>(hierarchyBuilderDTO));
                hierarchyBuilderLevelDTOBean.removeAllItems();
                hierarchyBuilderLevelDTOBean.addAll(hierarchyLogic.getHistHierarchyLevelDTO(hierarchyBuilderDTO.getHierarchyDefinitionSystemId(), version));
            } else {
                hierarchyBuilderDTO = hierarchyLogic.gethierarchyBuilderDTO(Integer.valueOf(systemId));
                hierarchyBuilderBinder.setItemDataSource(new BeanItem<HierarchyBuilderDTO>(hierarchyBuilderDTO));
                hierarchyBuilderLevelDTOBean.removeAllItems();
                hierarchyBuilderLevelDTOBean.addAll(hierarchyLogic.getHierarchyLevelDTOByDefinitionSystemId(hierarchyBuilderDTO.getHierarchyDefinitionSystemId()));
            }
            addComponent(new ViewHierarchyBuilderDefinition(hierarchyBuilderLevelDTOBean, hierarchyBuilderDTO));
            setStyleName("bootstrap");
            LOGGER.debug("In enter ViewChangeEvent Ended");
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
}
