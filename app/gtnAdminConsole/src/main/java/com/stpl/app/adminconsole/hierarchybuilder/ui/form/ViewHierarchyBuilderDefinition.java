package com.stpl.app.adminconsole.hierarchybuilder.ui.form;

import com.stpl.app.adminconsole.util.StringConstantUtils;
import com.stpl.app.adminconsole.common.dto.SessionDTO;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

import com.stpl.app.adminconsole.common.util.CommonUIUtil;
import com.stpl.app.adminconsole.hierarchybuilder.dto.HierarchyBuilderDTO;
import com.stpl.app.adminconsole.hierarchybuilder.dto.HierarchyBuilderLevelDTO;
import com.stpl.app.adminconsole.hierarchybuilder.dto.LevelDTO;
import com.stpl.app.adminconsole.hierarchybuilder.logic.HierarchyBuilderLogic;
import static com.stpl.app.adminconsole.hierarchybuilder.ui.form.EditHierarchyBuilderDefinition.ERROR_INFO;
import com.stpl.app.adminconsole.hierarchybuilder.ui.view.HierarchyBuilderLandingView;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.ErrorCodeUtil;
import com.stpl.app.adminconsole.util.ErrorCodes;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.CustomGridLayout;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;

// TODO: Auto-generated Javadoc
/**
 * The Class ViewHierarchyBuilderDefinition.
 */
public final class ViewHierarchyBuilderDefinition extends CustomComponent implements View {

    private static final Logger LOGGER = Logger.getLogger(ViewHierarchyBuilderDefinition.class);

    public static final String NAME = ConstantsUtils.EMPTY;

    private final ErrorLabel errorMsg = new ErrorLabel();

    private CustomFieldGroup hierarchyBuilderBinder;

    private HierarchyBuilderDTO hierarchyBuilderDTO;

    private HierarchyBuilderLevelDTO lvlDefDTO;

    private final TextField hierarchyName = new TextField();

    private final TextField levelNumber = new TextField();

    private final TextField levelName = new TextField();

    private final TextField tableName = new TextField();

    private final TextField fieldName = new TextField();

    private final TextField noInLevelValues = new TextField();

    private final Panel panel = new Panel();

    private final TextField nameInLevelValues = new TextField();

    public TextField hierarchyDefinitionSystemId = new TextField();

    private final OptionGroup hierarchyType = new OptionGroup();

    private final OptionGroup levelValueReference = new OptionGroup();

    public BeanItemContainer<HierarchyBuilderLevelDTO> hierarchyBuilderLevelDTOBean = new BeanItemContainer<>(HierarchyBuilderLevelDTO.class);

    private final BeanItemContainer<LevelDTO> levelDTOBean = new BeanItemContainer<>(LevelDTO.class);

    private final Button btnLevAdd = new Button("ADD");

    private final Button btnLevDelete = new Button("DELETE");

    private final Button btnLevDisplay = new Button("DISPLAY");

    private final Button resetButton = new Button("RESET");

    private final HierarchyBuilderLogic logic = new HierarchyBuilderLogic();

    private final Table levelDefinitionTable = new Table();

    private HierarchyBuilderLevelDTO hierarchyBuilderId;

    private final Button btnLevValuesAdd = new Button("ADD ROW");

    private final Button btnLevValuesDelete = new Button("DELETE ROW");

    private final Table levelValuesTable = new Table();

    private final Button btnUpdate = new Button("UPDATE");

    private final Button btnReset = new Button("RESET");

    private final Button btnDelete = new Button("DELETE");

    private final Button btnUpdateLevel = new Button("UPDATE");

    private final Button backButton = new Button("BACK");

    private static boolean displayRowFlag = true;

    private final TextField hcTextField = new TextField();

    public CustomFieldGroup getHierarchyBuilderBinder() {
        return hierarchyBuilderBinder;
    }

    public void setHierarchyBuilderBinder(final CustomFieldGroup hierarchyBuilderBinder) {
        this.hierarchyBuilderBinder = hierarchyBuilderBinder;
    }

    public HierarchyBuilderDTO getHierarchyBuilderDTO() {
        return hierarchyBuilderDTO;
    }

    public void setHierarchyBuilderDTO(final HierarchyBuilderDTO hierarchyBuilderDTO) {
        this.hierarchyBuilderDTO = hierarchyBuilderDTO;
    }

    public HierarchyBuilderLevelDTO getLvlDefDTO() {
        return lvlDefDTO;
    }

    public void setLvlDefDTO(final HierarchyBuilderLevelDTO lvlDefDTO) {
        this.lvlDefDTO = lvlDefDTO;
    }

    public TextField getHierarchyDefinitionSystemId() {
        return hierarchyDefinitionSystemId;
    }

    public void setHierarchyDefinitionSystemId(final TextField hierarchyDefinitionSystemId) {
        this.hierarchyDefinitionSystemId = hierarchyDefinitionSystemId;
    }

    public BeanItemContainer<HierarchyBuilderLevelDTO> getHierarchyBuilderLevelDTOBean() {
        return hierarchyBuilderLevelDTOBean;
    }

    public void setHierarchyBuilderLevelDTOBean(final BeanItemContainer<HierarchyBuilderLevelDTO> hierarchyBuilderLevelDTOBean) {
        this.hierarchyBuilderLevelDTOBean = hierarchyBuilderLevelDTOBean;
    }

    public HierarchyBuilderLevelDTO getHierarchyBuilderId() {
        return hierarchyBuilderId;
    }

    public void setHierarchyBuilderId(final HierarchyBuilderLevelDTO hierarchyBuilderId) {
        this.hierarchyBuilderId = hierarchyBuilderId;
    }

    public ErrorLabel getErrorMsg() {
        return errorMsg;
    }

    public TextField getHierarchyName() {
        return hierarchyName;
    }

    public TextField getLevelNumber() {
        return levelNumber;
    }

    public TextField getLevelName() {
        return levelName;
    }

    public TextField getTableName() {
        return tableName;
    }

    public TextField getFieldName() {
        return fieldName;
    }

    public TextField getNoInLevelValues() {
        return noInLevelValues;
    }

    public Panel getPanel() {
        return panel;
    }

    public TextField getNameInLevelValues() {
        return nameInLevelValues;
    }

    public OptionGroup getHierarchyType() {
        return hierarchyType;
    }

    public OptionGroup getLevelValueReference() {
        return levelValueReference;
    }

    public BeanItemContainer<LevelDTO> getLevelDTOBean() {
        return levelDTOBean;
    }

    public Button getBtnLevAdd() {
        return btnLevAdd;
    }

    public Button getBtnLevDelete() {
        return btnLevDelete;
    }

    public Button getBtnLevDisplay() {
        return btnLevDisplay;
    }

    public Button getResetButton() {
        return resetButton;
    }

    public HierarchyBuilderLogic getLogic() {
        return logic;
    }

    public Table getLevelDefinitionTable() {
        return levelDefinitionTable;
    }

    public Button getBtnLevValuesAdd() {
        return btnLevValuesAdd;
    }

    public Button getBtnLevValuesDelete() {
        return btnLevValuesDelete;
    }

    public Table getLevelValuesTable() {
        return levelValuesTable;
    }

    public Button getBtnUpdate() {
        return btnUpdate;
    }

    public Button getBtnReset() {
        return btnReset;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public Button getBtnUpdateLevel() {
        return btnUpdateLevel;
    }

    SessionDTO sessionDTO;

    /**
     * Instantiates a new view hierarchy builder definition.
     *
     * @param hierarchyBuilderLevelDTOBean the hierarchy builder level dto bean
     * @param hierarchyBuilderDTO the hierarchy builder dto
     */
    public ViewHierarchyBuilderDefinition(final BeanItemContainer<HierarchyBuilderLevelDTO> hierarchyBuilderLevelDTOBean, final HierarchyBuilderDTO hierarchyBuilderDTO) {
        super();
        this.hierarchyBuilderLevelDTOBean = hierarchyBuilderLevelDTOBean;
        this.hierarchyBuilderDTO = hierarchyBuilderDTO;
        init();
        getBinder();
    }

    /**
     * Inits the.
     */
    public void init() {
        addToContent();
        configureFields();
    }

    /**
     * Gets the binder.
     *
     * @return the binder
     * @throws Exception the exception
     */
    private CustomFieldGroup getBinder() {
        hierarchyBuilderBinder = new CustomFieldGroup(new BeanItem<>(hierarchyBuilderDTO));
        hierarchyBuilderBinder.setBuffered(true);
        hierarchyBuilderBinder.bindMemberFields(this);
        hierarchyBuilderBinder.setErrorDisplay(errorMsg);
        hierarchyBuilderBinder.setReadOnly(true);
        return hierarchyBuilderBinder;
    }

    /**
     * Adds the to content.
     *
     * @throws Exception the exception
     */
    private void addToContent() {
        final VerticalLayout content = new VerticalLayout();

        content.addComponent(errorMsg);

        content.setSpacing(true);
        content.setMargin(true);

        content.addComponent(hierarchyDefinitionSection());
        content.addComponent(addSpace());
        content.addComponent(levelDefinitionSection());
        content.addComponent(addSpace());
        content.addComponent(levelValueSection());
        content.addComponent(addSpace());
        setCompositionRoot(content);
    }

    /**
     * Configure fields.
     */
    private void configureFields() {
        hierarchyName.focus();
        hierarchyType.addItem(ConstantsUtils.PRIMARY);
        hierarchyType.addItem(ConstantsUtils.SECONDARY);
        hierarchyType.select(ConstantsUtils.PRIMARY);
        hierarchyType.addStyleName(ConstantsUtils.HORIZONTAL);
        levelValueReference.setImmediate(true);
        levelValueReference.addItem(ConstantsUtils.USER_DEFINED);
        levelValueReference.addItem(ConstantsUtils.LINKED);
        levelValueReference.select(ConstantsUtils.USER_DEFINED);
        hcTextField.setValue(hierarchyBuilderDTO.getHierarchyCategoryInString());
        hcTextField.setEnabled(false);
        levelValueReference.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * This method listens to data source value changes and passes the
             * changes forwards.
             */
            @SuppressWarnings("PMD")
            public void valueChange(final ValueChangeEvent event) {
                LOGGER.debug("In configureFields levelValueReference.addValueChangeListener Started");
                if (event != null) {
                    if (String.valueOf(event.getProperty().getValue()).equals(ConstantsUtils.USER_DEFINED)) {
                        tableName.setEnabled(false);
                        fieldName.setEnabled(false);
                        tableName.setValue(ConstantsUtils.EMPTY);
                        fieldName.setValue(ConstantsUtils.EMPTY);
                    } else {
                        tableName.setEnabled(true);
                        fieldName.setEnabled(true);
                    }
                }
                LOGGER.debug("In configureFields levelValueReference.addValueChangeListener Ended");
                levelValueReference.focus();
            }
        });

        panel.setVisible(false);
        btnUpdateLevel.setVisible(false);
        noInLevelValues.setReadOnly(true);
        nameInLevelValues.setReadOnly(true);

        if (hierarchyBuilderLevelDTOBean == null) {
            levelNumber.setReadOnly(false);
            levelNumber.setValue(String.valueOf(1));
            levelNumber.setReadOnly(true);
        } else {
            levelNumber.setReadOnly(false);
            levelNumber.setValue(String.valueOf(hierarchyBuilderLevelDTOBean.size() + 1));
            levelNumber.setReadOnly(true);
        }
        tableName.setEnabled(false);
        tableName.setImmediate(true);
        tableName.setStyleName("searchicon");
        tableName.addFocusListener(new FocusListener() {
            /**
             * This method listens to data source when it gets focus
             */
            public void focus(final FocusEvent event) {
                LOGGER.debug("In configureFields tableName.addFocusListener Started");
                final TableFieldNameLookUp lookUp = new TableFieldNameLookUp(tableName, fieldName);
                UI.getCurrent().addWindow(lookUp);
                lookUp.addCloseListener(new Window.CloseListener() {
                    /**
                     * Called when a Button has been clicked.
                     */
                    @SuppressWarnings("PMD")
                    public void windowClose(final Window.CloseEvent e) {
                        btnLevAdd.focus();
                    }
                });
                LOGGER.debug("In configureFields tableName.addFocusListener Ended");
            }
        });
        fieldName.setEnabled(false);
        fieldName.setImmediate(true);
        fieldName.setStyleName("searchicon");
        fieldName.addFocusListener(new FocusListener() {
            /**
             * This method listens to data source when it gets focus
             */
            public void focus(final FocusEvent event) {
                LOGGER.debug("In configureFields fieldName.addFocusListener Started");
                final TableFieldNameLookUp lookUp = new TableFieldNameLookUp(tableName, fieldName);
                UI.getCurrent().addWindow(lookUp);
                lookUp.addCloseListener(new Window.CloseListener() {
                    @SuppressWarnings("PMD")
                    public void windowClose(final Window.CloseEvent e) {
                        btnLevAdd.focus();
                    }
                });
                LOGGER.debug("In configureFields fieldName.addFocusListener Ended");
            }
        });
    }

    /**
     * Adds the space.
     *
     * @return the custom grid layout
     * @throws Exception the exception
     */
    private static CustomGridLayout addSpace() {
        final CustomGridLayout gridLayout = new CustomGridLayout(1, 1);
        gridLayout.addComponent(new Label(ConstantsUtils.EMPTY));
        return gridLayout;
    }

    /**
     * Hierarchy definition section.
     *
     * @return the panel
     * @throws Exception the exception
     */
    private Panel hierarchyDefinitionSection() {
        LOGGER.debug("hierarchyDefinitionSection Method started");
        final HorizontalLayout hlayout = new HorizontalLayout();
        final Panel panel = new Panel();
        hlayout.setMargin(true);
        hlayout.addComponent(new Label("Hierarchy Name:"));
        hlayout.addComponent(hierarchyName);
        hlayout.addComponent(new Label("Hierarchy Type:"));
        hlayout.addComponent(hierarchyType);
        hlayout.addComponent(new Label("Hierarchy Category:"));
        hlayout.addComponent(hcTextField);
        panel.setContent(hlayout);
        panel.setCaption("Hierarchy Definition ");
        LOGGER.debug("hierarchyDefinitionSection Method returns panel");
        return panel;
    }

    /**
     * Level definition section.
     *
     * @return the panel
     * @throws Exception the exception
     */
    private Panel levelDefinitionSection() {
        final Panel panel = new Panel();
        panel.setCaption("Level Definition ");
        panel.setSizeFull();
        final VerticalLayout content = new VerticalLayout();
        final CssLayout gridLayout = new CssLayout();

        gridLayout.addComponent(new Label("Level #:"));
        gridLayout.addComponent(levelNumber);
        gridLayout.addComponent(new Label("Level Name:"));
        gridLayout.addComponent(levelName);
        gridLayout.addComponent(new Label("User Defined/ Linked:"));
        gridLayout.addComponent(levelValueReference);

        gridLayout.addComponent(new Label("Table Name"));
        gridLayout.addComponent(tableName);
        gridLayout.addComponent(new Label("Field Name"));
        gridLayout.addComponent(fieldName);

        content.setMargin(true);
        content.setSpacing(true);
        final Panel tablePanel = new Panel(StringConstantUtils.RESULTS);
        tablePanel.setContent(levelDefinitionListTable());
        content.addComponent(tablePanel);
        content.addComponent(addButtons());
        panel.setContent(addSpace());
        panel.setContent(content);
        return panel;
    }

    /**
     * Adds the buttons.
     *
     * @return the horizontal layout
     */
    public HorizontalLayout addButtons() {
        final HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.setMargin(true);
        layout.addComponent(getBackButton());
        layout.addComponent(addDisplayButton());
        return layout;
    }

    public Button addDisplayButton() {
        btnLevDisplay.addClickListener(new Button.ClickListener() {

            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("In addDisplayButton btnLevDisplay.addClickListener started");
                try {
                    if (lvlDefDTO == null || displayRowFlag) {
                        MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Please select a row to display.", ButtonId.OK);
                    } else if (lvlDefDTO != null) {
                        hierarchyBuilderBinder.getErrorDisplay().clearError();
                        panel.setVisible(true);
                        if (hierarchyBuilderId != null && hierarchyBuilderId.getUserDefinedOrLinked().equals(ConstantsUtils.USER_DEFINED)) {
                            if (levelDTOBean.size() > ConstantsUtils.ZERO_NUM) {
                                for (int i = 0; i < levelDTOBean.size(); i++) {
                                    if (levelDTOBean.getIdByIndex(i).getLevelValues() != null && StringUtils.isNotEmpty(levelDTOBean.getIdByIndex(i).getLevelValues().trim())) {
                                    } else {
                                        MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Please Enter the values for the Level Values", ButtonId.OK);
                                        return;
                                    }
                                }
                                new HierarchyBuilderLogic().saveTempData(hierarchyBuilderId.getLevelNo(), hierarchyBuilderId.getLevelName(), levelDTOBean);
                            } else {
                                for (int i = 0; i < hierarchyBuilderLevelDTOBean.size(); i++) {
                                    if (hierarchyBuilderLevelDTOBean.getIdByIndex(i).getUserDefinedOrLinked().equals(ConstantsUtils.USER_DEFINED)) {
                                        int count = 0;
                                        count = logic.updateLevelCheckCount(hierarchyBuilderLevelDTOBean.getIdByIndex(i).getLevelSystemId(), hierarchyBuilderLevelDTOBean.getIdByIndex(i).getLevelNo());
                                        if (count > ConstantsUtils.ZERO_NUM) {
                                        } else {
                                            MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, ERROR_INFO + hierarchyBuilderLevelDTOBean.getIdByIndex(i).getLevelNo(), ButtonId.OK);
                                            return;
                                        }

                                    }
                                }
                            }
                        }
                        hierarchyBuilderId = lvlDefDTO;
                        noInLevelValues.setReadOnly(false);
                        noInLevelValues.setValue(String.valueOf(hierarchyBuilderId.getLevelNo()));
                        noInLevelValues.setReadOnly(true);
                        nameInLevelValues.setReadOnly(false);
                        nameInLevelValues.setValue(hierarchyBuilderId.getLevelName());
                        nameInLevelValues.setReadOnly(true);
                        levelDTOBean.removeAllItems();
                        if (hierarchyBuilderId.getLevelList() != null && hierarchyBuilderId.getUserDefinedOrLinked().equals(ConstantsUtils.USER_DEFINED)) {

                            levelDTOBean.addAll(hierarchyBuilderId.getLevelList());
                            levelValuesTable.setEditable(false);
                            btnLevValuesAdd.setEnabled(true);
                            btnLevValuesDelete.setEnabled(true);
                        } else {
                            final List hierDTO = new ArrayList();
                            hierDTO.add(hierarchyType.getValue().toString());
                            hierDTO.add(ConstantsUtils.EMPTY);
                            hierDTO.add(hierarchyBuilderId.getLevelNo());
                            levelValuesTable.setEditable(false);
                            btnLevValuesAdd.setEnabled(false);
                            btnLevValuesDelete.setEnabled(false);
                        }
                    }
                    LOGGER.debug("In addDisplayButton btnLevDisplay.addClickListener Ended");
                } catch (SystemException ex) {
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                    LOGGER.error(ex);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);

                } catch (Exception ex) {
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4012));
                    LOGGER.error(ex);
                }
            }
        });

        return btnLevDisplay;
    }

    /**
     * Gets the back button.
     *
     * @return the back button
     */
    public Button getBackButton() {
        backButton.setWidth("75");
        backButton.addClickListener(new ClickListener() {

            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("In getBackButton backButton.addClickListener navigateTo HierarchyBuilderIndex");
                getUI().getNavigator().navigateTo(HierarchyBuilderIndex.NAME);
            }
        });
        return backButton;
    }

    /**
     * Save reset buttons.
     *
     * @return the horizontal layout
     */
    public HorizontalLayout saveResetButtons() {
        final HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.setMargin(true);
        layout.addComponent(addUpdateButton());
        layout.addComponent(addDeleteButton());
        layout.addComponent(btnReset);
        return layout;

    }

    /**
     * Adds the update button.
     *
     * @return the button
     */
    public Button addUpdateButton() {

        btnUpdate.addClickListener(new Button.ClickListener() {

            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                if (event != null) {
                    LOGGER.debug("In addUpdateButton btnUpdate.addClickListener Started");
                    try {
                        hierarchyBuilderBinder.getErrorDisplay().clearError();
                        if (hierarchyBuilderId != null) {
                            new HierarchyBuilderLogic().saveTempData(hierarchyBuilderId.getLevelNo(), hierarchyBuilderId.getLevelName(), levelDTOBean);
                        }
                        final String msg = logic.saveHierarchy(hierarchyBuilderBinder, hierarchyBuilderLevelDTOBean, sessionDTO);

                        if (msg.equals(ConstantsUtils.SUCCESS)) {

                            getUI().getNavigator().navigateTo(HierarchyBuilderLandingView.NAME);

                            final Notification notif = new Notification(ConstantsUtils.HIERARCHY + hierarchyBuilderBinder.getField(ConstantsUtils.HIERARCHY_NAME).getValue() + "Updated successfully",
                                    Notification.Type.HUMANIZED_MESSAGE);

                            notif.setPosition(Position.MIDDLE_CENTER);
                            notif.setStyleName("mystyle");
                            notif.show(Page.getCurrent());

                        } else {
                            hierarchyBuilderBinder.getErrorDisplay().setError(ConstantsUtils.ERROR_OCCURED);
                        }
                    } catch (SystemException ex) {
                        final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                        AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                        LOGGER.error(ex);
                    } catch (PortalException ex) {
                        AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4003));
                        LOGGER.error(ex);
                    } catch (Exception ex) {
                        AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4003));
                        LOGGER.error(ex);
                    }
                    LOGGER.debug("In addUpdateButton btnUpdate.addClickListener Ended");
                }
            }
        });

        return btnUpdate;
    }

    /**
     * Adds the row.
     *
     * @return the button
     */
    public Button addRow() {

        btnLevAdd.addClickListener(new Button.ClickListener() {

            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                if (event != null) {
                    LOGGER.debug("In addRow btnLevAdd.addClickListener Started");
                    hierarchyBuilderBinder.getErrorDisplay().clearError();
                    final HierarchyBuilderLevelDTO dto = new HierarchyBuilderLevelDTO();
                    if (levelName.getValue() != null && StringUtils.isNotEmpty(String.valueOf(levelName.getValue()))) {
                        dto.setLevelName(String.valueOf(levelName.getValue()));
                    } else {
                        hierarchyBuilderBinder.getErrorDisplay().setError("Please Enter Value for the Level Name");
                        return;
                    }
                    if (levelValueReference.getValue() != null && !ConstantsUtils.EMPTY.equals(String.valueOf(levelValueReference.getValue()))) {
                        dto.setUserDefinedOrLinked(String.valueOf(levelValueReference.getValue()));
                    }
                    if (levelValueReference.getValue().equals(ConstantsUtils.LINKED)) {
                        if (tableName.getValue() != null && StringUtils.isNotEmpty(String.valueOf(tableName.getValue()))) {
                            dto.setTableName(String.valueOf(tableName.getValue()));
                        } else {
                            hierarchyBuilderBinder.getErrorDisplay().setError("Please Enter Value for the table Name");
                            return;
                        }
                        if (fieldName.getValue() != null && StringUtils.isNotEmpty(String.valueOf(fieldName.getValue()))) {
                            dto.setFieldName(String.valueOf(fieldName.getValue()));
                        } else {
                            hierarchyBuilderBinder.getErrorDisplay().setError("Please Enter Value for the field Name");
                            return;
                        }
                    }
                    dto.setLevelNo(hierarchyBuilderLevelDTOBean.size() + 1);
                    hierarchyBuilderLevelDTOBean.addBean(dto);
                    levelNumber.setReadOnly(false);
                    levelNumber.setValue(String.valueOf(hierarchyBuilderLevelDTOBean.size() + 1));
                    levelNumber.setReadOnly(true);
                    LOGGER.debug("In addRow btnLevAdd.addClickListener Ended");
                }
            }
        });

        return btnLevAdd;
    }

    /**
     * Adds the delete button.
     *
     * @return the button
     */
    public Button addDeleteButton() {

        btnDelete.addClickListener(new Button.ClickListener() {

            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                if (event != null) {
                    LOGGER.debug("In addDeleteButton btnDelete.addClickListener Started");
                    try {
                        hierarchyBuilderBinder.getErrorDisplay().clearError();
                        String systemId = hierarchyBuilderBinder.getField(ConstantsUtils.HIERARCHY_DEFINITION_ID).getValue() == null
                                || (ConstantsUtils.NULL).equals(hierarchyBuilderBinder.getField(ConstantsUtils.HIERARCHY_DEFINITION_ID).getValue()) ? ConstantsUtils.EMPTY : String
                                        .valueOf(hierarchyBuilderBinder.getField(ConstantsUtils.HIERARCHY_DEFINITION_ID).getValue());

                        systemId = systemId.replace(ConstantsUtils.COMMA, ConstantsUtils.EMPTY);
                        final int hierarchyId = Integer.valueOf(systemId);
                        final String msg = logic.deleteHierarchy(hierarchyId);

                        if (msg.equals(ConstantsUtils.SUCCESS)) {

                            getUI().getNavigator().navigateTo(HierarchyBuilderLandingView.NAME);

                            final Notification notif = new Notification(ConstantsUtils.HIERARCHY + hierarchyBuilderBinder.getField(ConstantsUtils.HIERARCHY_NAME).getValue()
                                    + ConstantsUtils.DELETED_SUCEESSFULLY, Notification.Type.HUMANIZED_MESSAGE);

                            notif.setPosition(Position.MIDDLE_CENTER);
                            notif.setStyleName("mystyle");
                            notif.show(Page.getCurrent());

                        } else {
                            hierarchyBuilderBinder.getErrorDisplay().setError(ConstantsUtils.ERROR_OCCURED);
                        }
                    } catch (SystemException ex) {
                        final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                        AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                        LOGGER.error(ex);
                    } catch (Exception ex) {
                        AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4004));
                        LOGGER.error(ex);
                    }
                    LOGGER.debug("In addDeleteButton btnDelete.addClickListener Ended");
                }
            }
        });

        return btnDelete;
    }

    /**
     * Adds the level buttons.
     *
     * @return the horizontal layout
     */
    public HorizontalLayout addLevelButtons() {
        final HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.setMargin(true);
        layout.addComponent(addRow());
        layout.addComponent(addUpdateLevelButton());
        layout.addComponent(addResetButton());
        return layout;

    }

    /**
     * Adds the reset button.
     *
     * @return the button
     */
    public Button addResetButton() {

        resetButton.addClickListener(new Button.ClickListener() {

            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                if (event != null) {
                    LOGGER.debug("In addResetButton resetButton.addClickListener Started");
                    btnLevAdd.setVisible(true);
                    btnUpdateLevel.setVisible(false);
                    levelNumber.setReadOnly(false);
                    levelNumber.setValue(String.valueOf(hierarchyBuilderLevelDTOBean.size() + 1));
                    levelNumber.setReadOnly(true);
                    levelName.setValue(ConstantsUtils.EMPTY);
                    levelValueReference.select(ConstantsUtils.USER_DEFINED);
                    tableName.setValue(ConstantsUtils.EMPTY);
                    fieldName.setValue(ConstantsUtils.EMPTY);
                    LOGGER.debug("In addResetButton resetButton.addClickListener Ended");
                }
            }
        });

        return resetButton;
    }

    /**
     * Adds the update level button.
     *
     * @return the button
     */
    public Button addUpdateLevelButton() {

        btnUpdateLevel.addClickListener(new Button.ClickListener() {

            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("In addUpdateLevelButton btnUpdateLevel.addClickListener Started");
                if (event != null) {
                    hierarchyBuilderBinder.getErrorDisplay().clearError();
                    final List<HierarchyBuilderLevelDTO> list = new ArrayList<>();
                    for (int i = 0; i < hierarchyBuilderLevelDTOBean.size(); i++) {
                        final HierarchyBuilderLevelDTO dto = hierarchyBuilderLevelDTOBean.getIdByIndex(i);
                        if (dto.getLevelNo() == lvlDefDTO.getLevelNo()) {
                            if (levelName.getValue() != null && StringUtils.isNotEmpty(String.valueOf(levelName.getValue()))) {
                                dto.setLevelName(String.valueOf(levelName.getValue()));

                            } else {
                                hierarchyBuilderBinder.getErrorDisplay().setError("Please Enter Value for the Level Name");
                                return;
                            }
                            if (levelValueReference.getValue() != null && !ConstantsUtils.EMPTY.equals(String.valueOf(levelValueReference.getValue()))) {
                                dto.setUserDefinedOrLinked(String.valueOf(levelValueReference.getValue()));
                            }
                            if (levelValueReference.getValue().equals(ConstantsUtils.LINKED)) {
                                if (tableName.getValue() != null && StringUtils.isNotEmpty(String.valueOf(tableName.getValue()))) {
                                    dto.setTableName(String.valueOf(tableName.getValue()));
                                } else {
                                    hierarchyBuilderBinder.getErrorDisplay().setError("Please Enter Value for the table Name");
                                    return;
                                }
                                if (fieldName.getValue() != null && StringUtils.isNotEmpty(String.valueOf(fieldName.getValue()))) {
                                    dto.setFieldName(String.valueOf(fieldName.getValue()));
                                } else {
                                    hierarchyBuilderBinder.getErrorDisplay().setError("Please Enter Value for the field Name");
                                    return;
                                }
                            }
                        }
                        list.add(dto);
                    }
                    hierarchyBuilderLevelDTOBean.removeAllItems();
                    hierarchyBuilderLevelDTOBean.addAll(list);
                }
                LOGGER.debug("In addUpdateLevelButton btnUpdateLevel.addClickListener Ended");
            }
        });

        return btnUpdateLevel;
    }

    /**
     * Level value section.
     *
     * @return the panel
     * @throws Exception the exception
     */
    private Panel levelValueSection() {
        panel.setCaption("Level Values ");
        panel.setSizeFull();
        final VerticalLayout layout = new VerticalLayout();
        final HorizontalLayout content = new HorizontalLayout();
        final CustomGridLayout gridLayout = new CustomGridLayout(NumericConstants.TWO, 3);
        gridLayout.setMargin(true);
        gridLayout.setSpacing(true);
        gridLayout.addComponent(new Label("Level #:"));
        gridLayout.addComponent(noInLevelValues);
        gridLayout.addComponent(new Label("Level Name:"));
        gridLayout.addComponent(nameInLevelValues);
        content.setMargin(true);
        content.setSpacing(true);
        content.addComponent(gridLayout);
        layout.addComponent(levelValuesListTable());
        content.addComponent(layout);
        panel.setContent(addSpace());
        panel.setContent(content);
        return panel;
    }

    /**
     * Adds the value buttons.
     *
     * @return the horizontal layout
     * @throws Exception the exception
     */
    public HorizontalLayout addValueButtons() {
        final HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.setMargin(true);
        layout.addComponent(addLevelValues());
        layout.addComponent(deleteLevelValues());
        return layout;

    }

    /**
     * Adds the level values.
     *
     * @return the button
     */
    public Button addLevelValues() {
        btnLevValuesAdd.addClickListener(new Button.ClickListener() {

            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                if (event != null) {
                    LOGGER.debug("In addLevelValues btnLevValuesAdd.addClickListener Started");
                    hierarchyBuilderBinder.getErrorDisplay().clearError();
                    if (levelDTOBean == null) {
                        final LevelDTO dto = new LevelDTO();
                        levelDTOBean.addBean(dto);
                    } else {
                        final List<LevelDTO> tempList = levelDTOBean.getItemIds();
                        final List<LevelDTO> list = new ArrayList<>();
                        for (int i = 0; i < tempList.size(); i++) {

                            if (tempList.get(i).getLevelValues() == null && ConstantsUtils.EMPTY.equals(tempList.get(i).getLevelValues())) {
                                hierarchyBuilderBinder.getErrorDisplay().setError("Please Enter Value for the previous Level Value");
                                return;
                            } else {
                                list.add(tempList.get(i));

                            }

                        }
                        final LevelDTO dto = new LevelDTO();
                        list.add(dto);
                        levelDTOBean.removeAllItems();
                        levelDTOBean.addAll(list);
                    }
                }
                LOGGER.debug("In addLevelValues btnLevValuesAdd.addClickListener Ended");
            }
        });

        return btnLevValuesAdd;
    }

    /**
     * Delete level values.
     *
     * @return the button
     */
    public Button deleteLevelValues() {

        btnLevValuesDelete.addClickListener(new Button.ClickListener() {

            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("In deleteLevelValues btnLevValuesDelete.addClickListener Started");
                if (event != null) {
                    levelDTOBean.removeItem(levelValuesTable.getValue());
                }
                LOGGER.debug("In deleteLevelValues btnLevValuesDelete.addClickListener Ended");
            }
        });

        return btnLevValuesDelete;
    }

    /**
     * Level definition list table.
     *
     * @return the table
     */
    public Table levelDefinitionListTable() {
        levelDefinitionTable.markAsDirty();
        levelDefinitionTable.setContainerDataSource(hierarchyBuilderLevelDTOBean);
        levelDefinitionTable.setVisibleColumns(CommonUIUtil.getInstance().acHbLevelDefnitionColumns);
        levelDefinitionTable.setCaption(StringConstantUtils.RESULTS);
        levelDefinitionTable.setColumnHeaders(CommonUIUtil.getInstance().acHbLevelDefnitionHeader);
        levelDefinitionTable.setPageLength(NumericConstants.SEVEN);
        levelDefinitionTable.setImmediate(true);
        levelDefinitionTable.setSelectable(true);
        levelDefinitionTable.setSizeFull();
        levelDefinitionTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            @SuppressWarnings("PMD")
            public void itemClick(final ItemClickEvent event) {
                if (event != null) {
                    LOGGER.debug("In levelDefinitionListTable levelDefinitionTable.addClickListener Started");
                    displayRowFlag = false;
                    btnUpdateLevel.setVisible(true);
                    btnLevAdd.setVisible(false);
                    lvlDefDTO = (HierarchyBuilderLevelDTO) event.getItemId();
                    if (lvlDefDTO.getLevelNo() != ConstantsUtils.ZERO_NUM) {
                        levelNumber.setReadOnly(false);
                        levelNumber.setValue(String.valueOf(lvlDefDTO.getLevelNo()));
                        levelNumber.setReadOnly(true);

                    }
                    levelName.setValue(lvlDefDTO.getLevelName());
                    levelValueReference.select(lvlDefDTO.getUserDefinedOrLinked());
                    tableName.setValue(lvlDefDTO.getTableName());
                    fieldName.setValue(lvlDefDTO.getFieldName());
                    LOGGER.debug("In levelDefinitionListTable levelDefinitionTable.addClickListener Ended");
                }
            }
        });

        levelDefinitionTable.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs.
             */
            public void error(final com.vaadin.server.ErrorEvent event) {
                return;
            }
        });
        levelDefinitionTable.addValueChangeListener(new Property.ValueChangeListener() {

            public void valueChange(final ValueChangeEvent event) {
                LOGGER.debug("In levelDefinitionListTable levelDefinitionTable.addValueChangeListener Started");
                if (hierarchyBuilderLevelDTOBean.isLastId(event.getProperty().getValue())) {
                    btnLevDelete.setEnabled(true);
                } else {
                    btnLevDelete.setEnabled(false);
                }
                LOGGER.debug("In levelDefinitionListTable levelDefinitionTable.addValueChangeListener Ended");
            }
        });
        return levelDefinitionTable;
    }

    /**
     * Level values list table.
     *
     * @return the table
     */
    public Table levelValuesListTable() {
        levelValuesTable.markAsDirty();
        levelValuesTable.setContainerDataSource(levelDTOBean);
        levelValuesTable.setVisibleColumns(CommonUIUtil.getInstance().acHbLevelValuesColumns);
        levelValuesTable.setCaption(StringConstantUtils.RESULTS);
        levelValuesTable.setColumnHeaders(CommonUIUtil.getInstance().acHbLevelValuesHeader);
        levelValuesTable.setPageLength(NumericConstants.SEVEN);
        levelValuesTable.setImmediate(true);
        levelValuesTable.setSelectable(true);
        levelValuesTable.setWidth("350px");
        levelValuesTable.setErrorHandler(new ErrorHandler() {

            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                return;
            }
        });
        return levelValuesTable;
    }

    /**
     * This method is always called before the view is shown on screen.
     *
     * @param event the event
     */
    public void enter(final ViewChangeListener.ViewChangeEvent event) {
        return;
    }
}
