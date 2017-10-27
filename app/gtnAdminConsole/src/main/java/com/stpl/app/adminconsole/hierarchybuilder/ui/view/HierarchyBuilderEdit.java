package com.stpl.app.adminconsole.hierarchybuilder.ui.view;

import org.jboss.logging.Logger;

import com.stpl.app.adminconsole.hierarchybuilder.dto.HierarchyBuilderDTO;
import com.stpl.app.adminconsole.hierarchybuilder.dto.HierarchyBuilderLevelDTO;
import com.stpl.app.adminconsole.hierarchybuilder.dto.LevelDTO;
import com.stpl.app.adminconsole.hierarchybuilder.logic.HierarchyBuilderLogic;
import com.stpl.app.adminconsole.hierarchybuilder.ui.form.EditHierarchyBuilderDefinition;
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
 * The Class HierarchyBuilderEdit.
 */
public class HierarchyBuilderEdit extends VerticalLayout implements View {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(HierarchyBuilderEdit.class);

    /**
     * The Constant NAME.
     */
    public static final String NAME = "HierarchyDefinitionEDIT";

    /**
     * The hierarchy builder dto.
     */
    private HierarchyBuilderDTO hierarchyBuilderDTO = new HierarchyBuilderDTO();

    /**
     * The hierarchy builder binder.
     */
    /**
     * The hierarchy builder level dto bean.
     */
    private final BeanItemContainer<HierarchyBuilderLevelDTO> hierarchyBuilderLevelDTOBean = new BeanItemContainer<>(HierarchyBuilderLevelDTO.class);

    /**
     * The level dto bean.
     */
    private final BeanItemContainer<LevelDTO> levelDTOBean = new BeanItemContainer<>(LevelDTO.class);

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
     * Gets the level dto bean.
     *
     * @return the level dto bean
     */
    public BeanItemContainer<LevelDTO> getLevelDTOBean() {
        return levelDTOBean;
    }

    /**
     * Instantiates a new hierarchy builder edit.
     *
     * @throws Exception
     * @throws PortalException
     * @throws SystemException
     */
    public HierarchyBuilderEdit() throws SystemException, PortalException {
        super();
        LOGGER.debug("HierarchyBuilderEdit Constructor started");
        setSpacing(true);
        addComponent(new EditHierarchyBuilderDefinition(hierarchyBuilderLevelDTOBean, hierarchyBuilderDTO));
        setStyleName("bootstrap");
        LOGGER.debug("HierarchyBuilderEdit Constructor Ended");
    }

    /**
     * This method is always called before the view is shown on screen.
     *
     * @param event the event
     */
    public void enter(final ViewChangeEvent event) {
        try {
            LOGGER.debug("In enter ViewChangeEvent Started");
            markAsDirty();
            this.removeAllComponents();
            final CustomFieldGroup hierarchyBuilderBinder = new CustomFieldGroup(new BeanItem<>(hierarchyBuilderDTO));
            final int systemId = Integer.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.SYS_ID).toString());
            final HierarchyBuilderLogic hierarchyLogic = new HierarchyBuilderLogic();
            hierarchyLogic.deleteTempLevelValues();
            hierarchyBuilderDTO = hierarchyLogic.gethierarchyBuilderDTO(Integer.valueOf(systemId));
            hierarchyBuilderBinder.setItemDataSource(new BeanItem<>(hierarchyBuilderDTO));
            hierarchyBuilderLevelDTOBean.removeAllItems();
            hierarchyBuilderLevelDTOBean.addAll(hierarchyLogic.getHierarchyLevelDTOByDefinitionSystemId(hierarchyBuilderDTO.getHierarchyDefinitionSystemId()));
            if (hierarchyBuilderLevelDTOBean.size() != ConstantsUtils.ZERO_NUM) {
                for (int i = 0; i < hierarchyBuilderLevelDTOBean.size(); i++) {
                    final HierarchyBuilderLevelDTO dto = hierarchyBuilderLevelDTOBean.getIdByIndex(i);
                    levelDTOBean.removeAllItems();
                    levelDTOBean.addAll(dto.getLevelList());
                    hierarchyLogic.saveTempData(dto.getLevelNo(), dto.getLevelName(), levelDTOBean);

                }
            }
            addComponent(new EditHierarchyBuilderDefinition(hierarchyBuilderLevelDTOBean, hierarchyBuilderDTO));
            setStyleName("bootstrap");
            LOGGER.debug("In enter ViewChangeEvent Ended");
        } catch (SystemException ex) {
            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
            LOGGER.error(ex);
        } catch (PortalException ex) {
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4011));
            LOGGER.error(ex);
        } catch (Exception e) {
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4011));
            LOGGER.error(e);

        }
    }
}
