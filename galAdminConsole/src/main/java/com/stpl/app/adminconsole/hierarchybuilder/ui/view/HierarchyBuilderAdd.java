package com.stpl.app.adminconsole.hierarchybuilder.ui.view;

import com.stpl.app.adminconsole.common.dto.SessionDTO;
import org.jboss.logging.Logger;

import com.stpl.app.adminconsole.hierarchybuilder.dto.HierarchyBuilderDTO;
import com.stpl.app.adminconsole.hierarchybuilder.dto.HierarchyBuilderLevelDTO;
import com.stpl.app.adminconsole.hierarchybuilder.dto.LevelDTO;
import com.stpl.app.adminconsole.hierarchybuilder.logic.HierarchyBuilderLogic;
import com.stpl.app.adminconsole.hierarchybuilder.ui.form.HierarchyBuilderDefinition;
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
 * The Class HierarchyBuilderAdd.
 */
public class HierarchyBuilderAdd extends VerticalLayout implements View {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(HierarchyBuilderAdd.class);

    /**
     * The Constant NAME.
     */
    public static final String NAME = "HierarchyDefinitionADD";

    /**
     * The hierarchy builder dto.
     */
    private HierarchyBuilderDTO hierarchyBuilderDTO = new HierarchyBuilderDTO();

    /**
     * The hierarchy builder level dto bean.
     */
    private final BeanItemContainer<HierarchyBuilderLevelDTO> hierarchyBuilderLevelDTOBean = new BeanItemContainer<HierarchyBuilderLevelDTO>(HierarchyBuilderLevelDTO.class);

    private final BeanItemContainer<LevelDTO> levelDTOBean = new BeanItemContainer<LevelDTO>(LevelDTO.class);

    SessionDTO sessionDTO;

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
     * Instantiates a new hierarchy builder add.
     *
     * @param sessionDTO
     * @throws Exception
     * @throws PortalException
     * @throws SystemException
     */
    public HierarchyBuilderAdd(final SessionDTO sessionDTO) throws SystemException, PortalException, Exception {
        super();
        LOGGER.info("HierarchyBuilderAdd Constructor started");
        setSpacing(true);
        this.sessionDTO = sessionDTO;
        setStyleName("bootstrap");
        LOGGER.info("HierarchyBuilderAdd Constructor Ended");
    }

    /**
     * This method is always called before the view is shown on screen.
     *
     * @param event the event
     */
    public void enter(final ViewChangeEvent event) {
        LOGGER.info("In enter ViewChangeEvent Started");
        markAsDirty();
        String mode = sessionDTO.getMode();
        try {
            if ((ConstantsUtils.ADD).equals(mode)) {
                this.removeAllComponents();
                new HierarchyBuilderLogic().deleteTempLevelValues();
                hierarchyBuilderLevelDTOBean.removeAllItems();
                hierarchyBuilderDTO = new HierarchyBuilderDTO();
                sessionDTO.setSystemId(0);
                addComponent(new HierarchyBuilderDefinition(hierarchyBuilderDTO, hierarchyBuilderLevelDTOBean, sessionDTO));
                setStyleName("bootstrap");
            } else if ((ConstantsUtils.EDIT).equals(mode)) {
                this.removeAllComponents();
                final CustomFieldGroup hierarchyBuilderBinder = new CustomFieldGroup(new BeanItem<HierarchyBuilderDTO>(hierarchyBuilderDTO));
                final int systemId = sessionDTO.getSystemId();
                final HierarchyBuilderLogic hierarchyLogic = new HierarchyBuilderLogic();
                hierarchyLogic.deleteTempLevelValues();
                hierarchyBuilderDTO = hierarchyLogic.gethierarchyBuilderDTO(Integer.valueOf(systemId));
                hierarchyBuilderBinder.setItemDataSource(new BeanItem<HierarchyBuilderDTO>(hierarchyBuilderDTO));
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
                addComponent(new HierarchyBuilderDefinition(hierarchyBuilderDTO, hierarchyBuilderLevelDTOBean, sessionDTO));
            } else if ((ConstantsUtils.VIEW).equals(mode)) {
                this.removeAllComponents();
                final CustomFieldGroup hierarchyBuilderBinder = new CustomFieldGroup(new BeanItem<HierarchyBuilderDTO>(hierarchyBuilderDTO));
                final int systemId = sessionDTO.getSystemId();
                final HierarchyBuilderLogic hierarchyLogic = new HierarchyBuilderLogic();
                hierarchyLogic.deleteTempLevelValues();
                final String view = sessionDTO.getFromViewPage();
                if (view.equalsIgnoreCase(ConstantsUtils.SMALL_YES)) {
                    final int version = sessionDTO.getVersionNo();
                    hierarchyBuilderDTO = hierarchyLogic.getHistHierarchyBuilderDTO(systemId, version);
                    hierarchyBuilderBinder.setItemDataSource(new BeanItem<HierarchyBuilderDTO>(hierarchyBuilderDTO));
                    hierarchyBuilderLevelDTOBean.removeAllItems();
                    hierarchyBuilderLevelDTOBean.addAll(hierarchyLogic.getHistHierarchyLevelDTO(systemId, version));
                } else {
                    hierarchyBuilderDTO = hierarchyLogic.gethierarchyBuilderDTO(systemId);
                    hierarchyBuilderBinder.setItemDataSource(new BeanItem<HierarchyBuilderDTO>(hierarchyBuilderDTO));
                    hierarchyBuilderLevelDTOBean.removeAllItems();
                    hierarchyBuilderLevelDTOBean.addAll(hierarchyLogic.getHierarchyLevelDTOByDefinitionSystemId(systemId));
                }
                addComponent(new HierarchyBuilderDefinition(hierarchyBuilderDTO, hierarchyBuilderLevelDTOBean, sessionDTO));
            }
        } catch (SystemException ex) {
            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
            LOGGER.error(ex);
        } catch (Exception ex) {
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getErrorMessage(ex));
            LOGGER.error(ex);
        }
        LOGGER.info("In enter ViewChangeEvent Ended");
    }
}
