package com.stpl.app.adminconsole.hierarchybuilder.ui.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.adminconsole.common.dto.SessionDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import com.stpl.app.adminconsole.common.util.CommonUIUtil;
import com.stpl.app.adminconsole.hierarchybuilder.dto.HierarchyBuilderDTO;
import com.stpl.app.adminconsole.hierarchybuilder.dto.HierarchyBuilderLevelDTO;
import com.stpl.app.adminconsole.hierarchybuilder.dto.LevelDTO;
import com.stpl.app.adminconsole.hierarchybuilder.logic.HierarchyBuilderLogic;
import com.stpl.app.adminconsole.hierarchybuilder.ui.view.HierarchyBuilderEdit;
import com.stpl.app.adminconsole.hierarchybuilder.ui.view.HierarchyBuilderLandingView;
import com.stpl.app.model.RelationshipBuilder;
import com.stpl.app.service.RelationshipBuilderLocalServiceUtil;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.ErrorCodeUtil;
import com.stpl.app.adminconsole.util.ErrorCodes;
import com.stpl.app.adminconsole.util.ValidationUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.CustomGridLayout;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
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
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.Set;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;

// TODO: Auto-generated Javadoc
/**
 * The Class EditHierarchyBuilderDefinition.
 */
public final class EditHierarchyBuilderDefinition extends CustomComponent implements View {

    /**
     * The Constant NAME.
     */
    public static final String NAME = ConstantsUtils.EMPTY;
    /**
     * The error msg.
     */
    private final ErrorLabel errorMsg = new ErrorLabel();
    /**
     * The hierarchy builder binder.
     */
    private CustomFieldGroup hierarchyBuilderBinder;
    /**
     * The hierarchy builder dto.
     */
    private final HierarchyBuilderDTO hierarchyBuilderDTO;
    /**
     * The lvl def dto.
     */
    private HierarchyBuilderLevelDTO lvlDefDTO;
    /**
     * The hierarchy name.
     */
    private final TextField hierarchyName = new TextField();
    /**
     * The level number.
     */
    private final TextField levelNumber = new TextField();
    /**
     * The level name.
     */
    private final TextField levelName = new TextField();
    /**
     * The table name.
     */
    private final CustomTextField tableName = new CustomTextField();
    /**
     * The field name.
     */
    private final CustomTextField fieldName = new CustomTextField();
    /**
     * The no in level values.
     */
    private final TextField noInLevelValues = new TextField();
    /**
     * The hierarchy category.
     */
    private final ComboBox hierarchyCategory = new ComboBox();
    /**
     * The company value.
     */
    private Object companyValue;
    /**
     * The hierarchy.
     */
    private final TextField hierarchy = new TextField();
    /**
     * The panel.
     */
    private final Panel panel = new Panel();
    /**
     * The name in level values.
     */
    private final TextField nameInLevelValues = new TextField();
    /**
     * The hierarchy definition system id.
     */
    public TextField hierarchyDefinitionSystemId = new TextField();
    /**
     * The hierarchy type.
     */
    private final OptionGroup hierarchyType = new OptionGroup();
    /**
     * The level value reference.
     */
    private final OptionGroup levelValueReference = new OptionGroup();
    /**
     * The hierarchy builder level dto bean.
     */
    public BeanItemContainer<HierarchyBuilderLevelDTO> hierarchyBuilderLevelDTOBean = new BeanItemContainer<HierarchyBuilderLevelDTO>(HierarchyBuilderLevelDTO.class);
    /**
     * The level dto bean.
     */
    private final BeanItemContainer<LevelDTO> levelDTOBean = new BeanItemContainer<LevelDTO>(LevelDTO.class);
    /**
     * The excel export levels.
     */
    private final Button excelExportLevels = new Button();
    /**
     * The btn lev add.
     */
    private final Button btnLevAdd = new Button("ADD ROW");
    /**
     * The btn lev delete.
     */
    private final Button btnLevDelete = new Button("DELETE ROW");
    /**
     * The btn lev display.
     */
    private final Button btnLevDisplay = new Button("DISPLAY");
    /**
     * The reset button.
     */
    private final Button resetButton = new Button("RESET");
    /**
     * The logic.
     */
    private final HierarchyBuilderLogic logic = new HierarchyBuilderLogic();
    /**
     * The level definition table.
     */
    private final ExtFilterTable levelDefinitionTable = new ExtFilterTable();
    /**
     * The hierarchyBuilderId.
     */
    private HierarchyBuilderLevelDTO hierarchyBuilderId;
    /**
     * The btn lev values add.
     */
    private final Button btnLevValuesAdd = new Button("ADD ROW");
    /**
     * The btn lev values delete.
     */
    private final Button btnLevValuesDelete = new Button("DELETE ROW");
    /**
     * The level values table.
     */
    private final Table levelValuesTable = new Table();
    /**
     * The btn update.
     */
    private final Button btnUpdate = new Button("UPDATE");
    /**
     * The btn reset.
     */
    private final Button btnReset = new Button("RESET");
    /**
     * The btn delete.
     */
    private final Button btnDelete = new Button("DELETE");
    /**
     * The btn update level.
     */
    private final Button btnUpdateLevel = new Button("UPDATE ROW");
    /**
     * The back button.
     */
    private final Button backButton = new Button("BACK");
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(EditHierarchyBuilderDefinition.class);
    /**
     * The Constant ERROR_INFO.
     */
    public static final String ERROR_INFO = "Please Enter atleast one level value for the Level No ";
    /**
     * flag to delete flag
     */
    private static boolean deleteRowFlag = true;
    /**
     * display row
     */
    private static boolean displayRowFlag = true;
    /**
     * back button flag
     */
    private boolean backSaveCheck = false;

    SessionDTO sessionDTO;

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
     * Gets the lvl def dto.
     *
     * @return the lvl def dto
     */
    public HierarchyBuilderLevelDTO getLvlDefDTO() {
        return lvlDefDTO;
    }

    /**
     * Sets the lvl def dto.
     *
     * @param lvlDefDTO the lvl def dto
     */
    public void setLvlDefDTO(final HierarchyBuilderLevelDTO lvlDefDTO) {
        this.lvlDefDTO = lvlDefDTO;
    }

    /**
     * Gets the company value.
     *
     * @return the company value
     */
    public Object getCompanyValue() {
        return companyValue;
    }

    /**
     * Sets the company value.
     *
     * @param companyValue the company value
     */
    public void setCompanyValue(final Object companyValue) {
        this.companyValue = companyValue;
    }

    /**
     * Gets the hierarchy definition system id.
     *
     * @return the hierarchy definition system id
     */
    public TextField getHierarchyDefinitionSystemId() {
        return hierarchyDefinitionSystemId;
    }

    /**
     * Sets the hierarchy definition system id.
     *
     * @param hierarchyDefinitionSystemId the hierarchy definition system id
     */
    public void setHierarchyDefinitionSystemId(final TextField hierarchyDefinitionSystemId) {
        this.hierarchyDefinitionSystemId = hierarchyDefinitionSystemId;
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
     * Sets the hierarchy builder level dto bean.
     *
     * @param hierarchyBuilderLevelDTOBean the hierarchy builder level dto bean
     */
    public void setHierarchyBuilderLevelDTOBean(final BeanItemContainer<HierarchyBuilderLevelDTO> hierarchyBuilderLevelDTOBean) {
        this.hierarchyBuilderLevelDTOBean = hierarchyBuilderLevelDTOBean;
    }

    /**
     * Gets the hierarchy builder id.
     *
     * @return the hierarchy builder id
     */
    public HierarchyBuilderLevelDTO getHierarchyBuilderId() {
        return hierarchyBuilderId;
    }

    /**
     * Sets the hierarchy builder id.
     *
     * @param hierarchyBuilderId the hierarchy builder id
     */
    public void setHierarchyBuilderId(final HierarchyBuilderLevelDTO hierarchyBuilderId) {
        this.hierarchyBuilderId = hierarchyBuilderId;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public static String getName() {
        return NAME;
    }

    /**
     * Gets the error msg.
     *
     * @return the error msg
     */
    public ErrorLabel getErrorMsg() {
        return errorMsg;
    }

    /**
     * Gets the hierarchy builder dto.
     *
     * @return the hierarchy builder dto
     */
    public HierarchyBuilderDTO getHierarchyBuilderDTO() {
        return hierarchyBuilderDTO;
    }

    /**
     * Gets the hierarchy name.
     *
     * @return the hierarchy name
     */
    public TextField getHierarchyName() {
        return hierarchyName;
    }

    /**
     * Gets the level number.
     *
     * @return the level number
     */
    public TextField getLevelNumber() {
        return levelNumber;
    }

    /**
     * Gets the level name.
     *
     * @return the level name
     */
    public TextField getLevelName() {
        return levelName;
    }

    /**
     * Gets the table name.
     *
     * @return the table name
     */
    public TextField getTableName() {
        return tableName;
    }

    /**
     * Gets the field name.
     *
     * @return the field name
     */
    public TextField getFieldName() {
        return fieldName;
    }

    /**
     * Gets the no in level values.
     *
     * @return the no in level values
     */
    public TextField getNoInLevelValues() {
        return noInLevelValues;
    }

    /**
     * Gets the hierarchy category.
     *
     * @return the hierarchy category
     */
    public ComboBox getHierarchyCategory() {
        return hierarchyCategory;
    }

    /**
     * Gets the hierarchy.
     *
     * @return the hierarchy
     */
    public TextField getHierarchy() {
        return hierarchy;
    }

    /**
     * Gets the panel.
     *
     * @return the panel
     */
    public Panel getPanel() {
        return panel;
    }

    /**
     * Gets the name in level values.
     *
     * @return the name in level values
     */
    public TextField getNameInLevelValues() {
        return nameInLevelValues;
    }

    /**
     * Gets the hierarchy type.
     *
     * @return the hierarchy type
     */
    public OptionGroup getHierarchyType() {
        return hierarchyType;
    }

    /**
     * Gets the level value reference.
     *
     * @return the level value reference
     */
    public OptionGroup getLevelValueReference() {
        return levelValueReference;
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
     * Gets the excel export levels.
     *
     * @return the excel export levels
     */
    public Button getExcelExportLevels() {
        return excelExportLevels;
    }

    /**
     * Gets the btn lev add.
     *
     * @return the btn lev add
     */
    public Button getBtnLevAdd() {
        return btnLevAdd;
    }

    /**
     * Gets the btn lev delete.
     *
     * @return the btn lev delete
     */
    public Button getBtnLevDelete() {
        return btnLevDelete;
    }

    /**
     * Gets the btn lev display.
     *
     * @return the btn lev display
     */
    public Button getBtnLevDisplay() {
        return btnLevDisplay;
    }

    /**
     * Gets the reset button.
     *
     * @return the reset button
     */
    public Button getResetButton() {
        return resetButton;
    }

    /**
     * Gets the logic.
     *
     * @return the logic
     */
    public HierarchyBuilderLogic getLogic() {
        return logic;
    }

    /**
     * Gets the level definition table.
     *
     * @return the level definition table
     */
    public ExtFilterTable getLevelDefinitionTable() {
        return levelDefinitionTable;
    }

    /**
     * Gets the btn lev values add.
     *
     * @return the btn lev values add
     */
    public Button getBtnLevValuesAdd() {
        return btnLevValuesAdd;
    }

    /**
     * Gets the btn lev values delete.
     *
     * @return the btn lev values delete
     */
    public Button getBtnLevValuesDelete() {
        return btnLevValuesDelete;
    }

    /**
     * Gets the level values table.
     *
     * @return the level values table
     */
    public Table getLevelValuesTable() {
        return levelValuesTable;
    }

    /**
     * Gets the btn update.
     *
     * @return the btn update
     */
    public Button getBtnUpdate() {
        return btnUpdate;
    }

    /**
     * Gets the btn reset.
     *
     * @return the btn reset
     */
    public Button getBtnReset() {
        return btnReset;
    }

    /**
     * Gets the btn delete.
     *
     * @return the btn delete
     */
    public Button getBtnDelete() {
        return btnDelete;
    }

    /**
     * Gets the btn update level.
     *
     * @return the btn update level
     */
    public Button getBtnUpdateLevel() {
        return btnUpdateLevel;
    }

    /**
     * Gets the error info.
     *
     * @return the error info
     */
    public static String getErrorInfo() {
        return ERROR_INFO;
    }

    /**
     * Instantiates a new edits the hierarchy builder definition.
     *
     * @param hierarchyBuilderLevelDTOBean the hierarchy builder level dto bean
     * @param hierarchyBuilderDTO the hierarchy builder dto
     * @throws Exception
     * @throws PortalException
     * @throws SystemException
     */
    public EditHierarchyBuilderDefinition(final BeanItemContainer<HierarchyBuilderLevelDTO> hierarchyBuilderLevelDTOBean, final HierarchyBuilderDTO hierarchyBuilderDTO) throws SystemException,
            PortalException, Exception {
        super();
        LOGGER.info("EditHierarchyBuilderDefinition constructor initiated ");
        this.hierarchyBuilderLevelDTOBean = hierarchyBuilderLevelDTOBean;
        this.hierarchyBuilderDTO = hierarchyBuilderDTO;
        init();
        getBinder();
        LOGGER.info("EditHierarchyBuilderDefinition constructor Ended ");
    }

    /**
     * Inits the.
     *
     * @throws PortalException
     * @throws SystemException
     *
     * @throws Exception
     */
    public void init() throws SystemException, PortalException, Exception {
        LOGGER.info("Init method started ");
        addToContent();
        configureFields();
        LOGGER.info("Init method ended ");
    }

    /**
     * Gets the binder.
     *
     * @return the binder
     * @throws Exception the exception
     */
    private CustomFieldGroup getBinder() {
        LOGGER.info("getBinder method started ");
        hierarchyBuilderBinder = new CustomFieldGroup(new BeanItem<HierarchyBuilderDTO>(hierarchyBuilderDTO));
        hierarchyBuilderBinder.setBuffered(true);
        hierarchyBuilderBinder.bindMemberFields(this);
        hierarchyBuilderBinder.setErrorDisplay(errorMsg);
        LOGGER.info("getBinder method RETURNS hierarchyBuilderBinder-Binder ");
        return hierarchyBuilderBinder;
    }

    /**
     * Adds the to content.
     *
     * @throws Exception the exception
     */
    private void addToContent() {
        final VerticalLayout content = new VerticalLayout();
        LOGGER.info("getBinder method started ");
        content.addComponent(errorMsg);
        content.setSpacing(true);
        content.setMargin(true);
        content.addComponent(hierarchyDefinitionSection());
        content.addComponent(addSpace());
        content.addComponent(levelDefinitionSection());
        content.addComponent(addSpace());
        content.addComponent(levelValueSection());
        content.addComponent(addSpace());
        content.addComponent(saveResetButtons());
        setCompositionRoot(content);
        LOGGER.info("getBinder method Ended ");
    }

    /**
     * Configure fields.
     *
     * @throws Exception
     * @throws SystemException
     */
    private void configureFields() throws SystemException, PortalException, Exception {
        LOGGER.info("configureFields Entering ");
        excelExportLevels.setDescription(ConstantsUtils.EXCEL_EXPORT);
        excelExportLevels.setIconAlternateText(ConstantsUtils.EXCEL_EXPORT);
        hierarchyName.focus();
        hierarchyName.setRequired(true);
        hierarchyName.setImmediate(true);
        hierarchyType.setRequired(true);
        btnLevValuesDelete.setWidth("100");
        hierarchyType.addItem(ConstantsUtils.PRIMARY);
        hierarchyType.addItem(ConstantsUtils.SECONDARY);
        hierarchyType.select(ConstantsUtils.PRIMARY);
        hierarchyType.addStyleName(ConstantsUtils.HORIZONTAL);
        hierarchyCategory.setNullSelectionAllowed(true);
        hierarchyCategory.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        hierarchyCategory.setImmediate(true);
        List<HelperDTO> result;
        result = logic.getHierachyCategory(CommonUIUtil.HIERARCHY_CATEGORY);
        hierarchyCategory.setContainerDataSource(new IndexedContainer(result));
        hierarchyCategory.setRequired(true);
        hierarchyCategory.setRequiredError("Please select Hierarchy Category");
        hierarchyCategory.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * This method listens to data source value changes and passes the
             * changes forwards.
             */
            @SuppressWarnings("PMD")
            public void valueChange(final ValueChangeEvent event) {
                LOGGER.info("In configureFields hierarchyCategory.addValueChangeListener started");
                if (hierarchyCategory.getValue() == null) {
                    final String nullValue = null;
                    companyValue = nullValue;
                    hierarchy.setValue(null);
                } else {
                    companyValue = ((HelperDTO) hierarchyCategory.getValue()).getId();
                    hierarchy.setValue(((HelperDTO) hierarchyCategory.getValue()).getDescription());
                    final int selectedHierarchySysId = Integer.valueOf(companyValue.toString());
                    VaadinSession.getCurrent().setAttribute(ConstantsUtils.SELECT_HIRARCHY_CATAGORY, selectedHierarchySysId);

                }
                LOGGER.info("In configureFields hierarchyCategory.addValueChangeListener Ended");
            }
        });
        levelValueReference.setImmediate(true);
        levelValueReference.addItem(ConstantsUtils.USER_DEFINED);
        levelValueReference.addItem(ConstantsUtils.LINKED);
        levelValueReference.select(ConstantsUtils.USER_DEFINED);
        levelValueReference.addStyleName(ConstantsUtils.HORIZONTAL);
        levelValueReference.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * This method listens to data source value changes and passes the
             * changes forwards.
             */
            public void valueChange(final ValueChangeEvent event) {
                LOGGER.info("In configureFields levelValueReference.addValueChangeListener started");
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
                levelValueReference.focus();
                LOGGER.info("In configureFields levelValueReference.addValueChangeListener Ended");
            }
        });

        panel.setVisible(false);
        btnUpdateLevel.setVisible(false);
        noInLevelValues.setReadOnly(true);
        nameInLevelValues.setReadOnly(true);

        excelExportLevels.setIcon(new ThemeResource("../../icons/excel.png"));
        excelExportLevels.setStyleName("link");
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
        tableName.addClickListener(new CustomTextField.ClickListener() {
            /**
             * Called when a Button has been clicked .
             *
             */
            @SuppressWarnings("PMD")
            public void click(CustomTextField.ClickEvent event) {
                final TableFieldNameLookUp lookUp = new TableFieldNameLookUp(tableName, fieldName);
                UI.getCurrent().addWindow(lookUp);
                lookUp.addCloseListener(new Window.CloseListener() {
                    /**
                     * This method listens to the event
                     */
                    @SuppressWarnings("PMD")
                    public void windowClose(final Window.CloseEvent e) {
                        btnLevAdd.focus();
                    }
                });
            }
        });
        fieldName.setEnabled(false);
        fieldName.setImmediate(true);
        fieldName.addClickListener(new CustomTextField.ClickListener() {
            /**
             * Called when a Button has been clicked .
             *
             */
            @SuppressWarnings("PMD")
            public void click(CustomTextField.ClickEvent event) {
                final TableFieldNameLookUp lookUp = new TableFieldNameLookUp(tableName, fieldName);
                UI.getCurrent().addWindow(lookUp);
                lookUp.addCloseListener(new Window.CloseListener() {
                    /**
                     * This method listens to close event
                     */
                    @SuppressWarnings("PMD")
                    public void windowClose(final Window.CloseEvent e) {
                        btnLevAdd.focus();
                    }
                });
            }
        });
        excelExportLevels.addClickListener(new ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            public void buttonClick(final ClickEvent event) {
                LOGGER.info("In configureFields excelExportLevels.addClickListener started");
                /*
                 * CustomExcelExport excelExport = new CustomExcelExport(
                 * levelDefinitionTable, "Hierarchy Level Definition",
                 * "Hierarchy Level Definition",
                 * "HierarchyLevelDefinition.xls"); excelExport.export();
                 */
                if (levelDefinitionTable.size() > ConstantsUtils.ZERO_NUM) {
                    try {
                        List<List> exportList;
                        final List<String> dollarList = new ArrayList();
                        dollarList.add("AdminConsole");
                        exportList = new ArrayList<List>();
                        exportList.add(dollarList);
                        exportList.add(null);
                        exportList.add(null);
                        ExcelExport excel = new ExcelExport(new ExtCustomTableHolder(levelDefinitionTable), "Level Definition Results", "Level Definition Results", "Level Definition Results.xls", false);
                        excel.export();
                    } catch (Exception e) {
                        LOGGER.error(e);
                    }
                } else {
                    MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "No results to Export", ButtonId.OK);
                }
                LOGGER.info("In configureFields excelExportLevels.addClickListener Ended");
            }
        });
        LOGGER.info("ConfigureFields Ended");
    }

    /**
     * Adds the space.
     *
     * @return the custom grid layout
     * @throws Exception the exception
     */
    private static CustomGridLayout addSpace() {
        final CustomGridLayout gridLayout = new CustomGridLayout(1, 1);
        LOGGER.info("addSpace method Started ");
        gridLayout.addComponent(new Label(ConstantsUtils.EMPTY));
        LOGGER.info("addSpace method Ended ");
        return gridLayout;
    }

    /**
     * Hierarchy definition section.
     *
     * @return the panel
     * @throws Exception the exception
     */
    private Panel hierarchyDefinitionSection() {
        LOGGER.info("hierarchyDefinitionSection Method started");
        final HorizontalLayout hlayout = new HorizontalLayout();

        final Panel panel = new Panel();
        hlayout.setMargin(true);

        hlayout.addComponent(new Label("Hierarchy Name:"));
        hlayout.addComponent(hierarchyName);
        hlayout.addComponent(new Label("Hierarchy Type:"));
        hlayout.addComponent(hierarchyType);
        hlayout.addComponent(new Label("Hierarchy Category:"));
        hlayout.addComponent(hierarchyCategory);
        panel.setContent(hlayout);
        panel.setCaption("Hierarchy Definition ");
        LOGGER.info("hierarchyDefinitionSection Method returns panel");
        return panel;
    }

    /**
     * Level definition section.
     *
     * @return the panel
     * @throws Exception the exception
     */
    private Panel levelDefinitionSection() {
        LOGGER.info("levelDefinitionSection Method started");
        final Panel panel = new Panel();
        final Panel tablePanel = new Panel("Results");
        panel.setCaption("Level Definition ");
        final VerticalLayout content = new VerticalLayout();
        final HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addStyleName(ConstantsUtils.RESPONSIVE_GRID);
        final CssLayout gridLayout = new CssLayout();
        gridLayout.addStyleName("minWidth50px");
        gridLayout.setSizeFull();
        gridLayout.addComponent(new Label("Level #:"));
        gridLayout.addComponent(levelNumber);
        gridLayout.addComponent(new Label("Level Name:"));
        gridLayout.addComponent(levelName);
        gridLayout.addComponent(new Label("User Defined / Linked:"));
        gridLayout.addComponent(levelValueReference);

        gridLayout.addComponent(new Label("Table Name"));
        tableName.addStyleName("searchText");
        gridLayout.addComponent(tableName);
        Label dommy = new Label("          ");
        dommy.setWidth("199px");
        gridLayout.addComponent(dommy);

        Label field = new Label("Field Name:");
        field.addStyleName("lableright");
        field.setWidth("187px");

        gridLayout.addComponent(field);
        fieldName.addStyleName("searchText");
        gridLayout.addComponent(fieldName);

        content.setMargin(true);
        content.setSpacing(true);
        horizontalLayout.addComponent(gridLayout);
        content.addComponent(horizontalLayout);
        content.addComponent(addLevelButtons());
        tablePanel.setContent(levelDefinitionListTable());
        content.addComponent(tablePanel);
        content.addComponent(addButtons());

        panel.setContent(content);
        LOGGER.info("levelDefinitionSection Method returns panel");
        return panel;
    }

    /**
     * Adds the buttons.
     *
     * @return the horizontal layout
     */
    public HorizontalLayout addButtons() {
        final HorizontalLayout layout = new HorizontalLayout();
        LOGGER.info("addButtons method Started ");
        layout.setSpacing(true);
        layout.setMargin(true);
        layout.addComponent(removeButton());
        layout.addComponent(addDisplayButton());
        layout.addComponent(excelExportLevels);
        LOGGER.info("addButtons method Ended ");
        return layout;

    }

    /**
     * Adds the display button.
     *
     * @return the button
     */
    public Button addDisplayButton() {
        btnLevDisplay.addClickListener(new Button.ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                LOGGER.info("In addDisplayButton btnLevDisplay.addClickListener started");
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
                            levelDTOBean.addAll(new HierarchyBuilderLogic().getLevelList(hierarchyBuilderId.getLevelNo()));
                            levelValuesTable.setEditable(true);
                            btnLevValuesAdd.setEnabled(true);
                            btnLevValuesDelete.setEnabled(true);
                        } else {
                            final List hierDTO = new ArrayList();
                            hierDTO.add(hierarchyType.getValue().toString());
                            hierDTO.add(hierarchy.getValue().toString());
                            hierDTO.add(hierarchyBuilderId.getLevelNo());
                            levelValuesTable.setEditable(false);
                            btnLevValuesAdd.setEnabled(false);
                            btnLevValuesDelete.setEnabled(false);
                        }
                    }
                    LOGGER.info("In addDisplayButton btnLevDisplay.addClickListener Ended");
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
     * Save reset buttons.
     *
     * @return the horizontal layout
     */
    public HorizontalLayout saveResetButtons() {
        final HorizontalLayout layout = new HorizontalLayout();
        try {
            LOGGER.info("In saveResetButtons mehod Started ");
            layout.setSpacing(true);
            layout.setMargin(true);
            layout.addComponent(getBackButton());
            layout.addComponent(addUpdateButton());
            layout.addComponent(addrstButton());
            LOGGER.info("In saveResetButtons mehod Ended ");
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return layout;

    }

    /**
     * Gets the back button.
     *
     * @return the back button
     */
    public Button getBackButton() {
        LOGGER.info("getBackButton Method started");
        backButton.setWidth("75");
        backButton.addClickListener(new ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                LOGGER.info("In getBackButton backButton.addClickListener Started");
                getUI().getNavigator().navigateTo(HierarchyBuilderIndex.NAME);
                LOGGER.info("In getBackButton backButton.addClickListener Ended");
            }
        });
        LOGGER.info("getBackButton Method Ended");
        return backButton;
    }

    /**
     * Adds the update button.
     *
     * @return the button
     */
    public Button addUpdateButton() {

        btnUpdate.addClickListener(new Button.ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                LOGGER.info("In addUpdateButton btnUpdate.addClickListener started");
                updateButtonLogic(event, true);
            }
        });

        return btnUpdate;
    }

    /**
     * Addrst button.
     *
     * @return the component
     */
    private Component addrstButton() {
        // To change body of generated methods, choose Tools | Templates.
        btnReset.addClickListener(new Button.ClickListener() {
            /**
             * Called when a Button has been clicked .
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                LOGGER.info("In addrstButton btnReset.addClickListener Started");
                if (event != null) {
                    final AbstractNotificationUtils notificationUtils = new AbstractNotificationUtils() {
                        /**
                         * Called when yes has been clicked.
                         */
                        @SuppressWarnings("PMD")
                        public void yesMethod() {
                            final int systemId = Integer.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.SYS_ID).toString());
                            getUI().getNavigator().navigateTo(HierarchyBuilderEdit.NAME);
                        }

                        /**
                         * Called when a no has been clicked .
                         */
                        public void noMethod() {

                        }
                    };
                    notificationUtils.getConfirmationMessage(ConstantsUtils.CONFORMATION, "Are you sure you want to reset the page to default/previous values?");
                }
                LOGGER.info("In addrstButton btnReset.addClickListener Ended");
            }
        });

        return btnReset;
    }

    /**
     * Adds the row .
     *
     * @return the button
     */
    public Button addRow() {

        btnLevAdd.addClickListener(new Button.ClickListener() {
            /**
             * Called when a Button has been clicked .
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                LOGGER.info("In addRow btnLevAdd.addClickListener Started");
                try {
                    if (event != null) {

                        hierarchyBuilderBinder.getErrorDisplay().clearError();
                        final HierarchyBuilderLevelDTO dto = new HierarchyBuilderLevelDTO();
                        List<HierarchyBuilderLevelDTO> list = new ArrayList<HierarchyBuilderLevelDTO>();
                        list = hierarchyBuilderLevelDTOBean.getItemIds();
                        final HashMap levelNameMap = new HashMap();
                        final List<String> tableList = new ArrayList<String>();
                        for (final HierarchyBuilderLevelDTO levelDTO : list) {
                            levelNameMap.put(levelDTO.getLevelNo(), levelDTO.getLevelName());
                            if (levelValueReference.getValue().equals(ConstantsUtils.LINKED)) {
                                tableList.add(levelDTO.getTableName() + "-" + levelDTO.getFieldName());
                            }
                        }
                        if (!StringUtils.isBlank(levelName.getValue())) {
                            if (levelNameMap.containsValue(levelName.getValue().trim())) {
                                MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Level Name already exists! Please enter a different Level Name", ButtonId.OK);
                                return;
                            } else if (!levelName.getValue().matches(ValidationUtils.ALPHA_NUMERIC)) {
                                MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Please enter Alpha Numeric Value for the Level Name.", ButtonId.OK);
                                return;
                            } else {
                                dto.setLevelName(String.valueOf(levelName.getValue().trim()));
                            }
                        } else {
                            MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Please Enter Value for the Level Name", ButtonId.OK);
                            return;
                        }
                        if (levelValueReference.getValue() != null && !ConstantsUtils.EMPTY.equals(String.valueOf(levelValueReference.getValue()))) {
                            dto.setUserDefinedOrLinked(String.valueOf(levelValueReference.getValue()));
                        }
                        if (levelValueReference.getValue().equals(ConstantsUtils.LINKED)) {
                            if (tableList.contains(tableName.getValue().toString() + "-" + fieldName.getValue().toString())) {
                                MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Selected Table/Field Name already exists.Please select different Table/Field Name", ButtonId.OK);
                                return;
                            } else {
                                if (tableName.getValue() != null && StringUtils.isNotEmpty(String.valueOf(tableName.getValue()))) {
                                    final int levelNo = Integer.valueOf(levelNumber.getValue());
                                    if (levelNo == 1 && ConstantsUtils.DATA_SELECTION.equals(hierarchy.getValue()) && ConstantsUtils.PRIMARY.equals(hierarchyType.getValue())) {
                                        MessageBox.showPlain(Icon.INFO, ConstantsUtils.NOTIFICATION, "Companies of type 'GLCOMP' only display for this level.", ButtonId.OK);
                                    }
                                    dto.setTableName(String.valueOf(tableName.getValue()));
                                } else {
                                    MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Please Enter Value for the table Name", ButtonId.OK);
                                    return;
                                }
                                if (fieldName.getValue() != null && StringUtils.isNotEmpty(String.valueOf(fieldName.getValue()))) {
                                    dto.setFieldName(String.valueOf(fieldName.getValue()));
                                } else {
                                    MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Please Enter Value for the field Name", ButtonId.OK);
                                    return;
                                }
                            }
                        } else {
                            if (hierarchyBuilderLevelDTOBean.size() > ConstantsUtils.ZERO_NUM) {
                                if (levelDTOBean.size() > ConstantsUtils.ZERO_NUM) {
                                } else {
                                    for (int i = 0; i < hierarchyBuilderLevelDTOBean.size(); i++) {
                                        if (hierarchyBuilderLevelDTOBean.getIdByIndex(i).getUserDefinedOrLinked().equals(ConstantsUtils.USER_DEFINED)) {
                                            int count = 0;
                                            count = logic.updateLevelCheckCount(hierarchyBuilderLevelDTOBean.getIdByIndex(i).getLevelSystemId(), hierarchyBuilderLevelDTOBean.getIdByIndex(i)
                                                    .getLevelNo());
                                            if (count > ConstantsUtils.ZERO_NUM) {
                                            } else {
                                                MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, ERROR_INFO + hierarchyBuilderLevelDTOBean.getIdByIndex(i).getLevelNo(), ButtonId.OK);
                                                return;
                                            }

                                        }
                                    }
                                }
                            }
                        }
                        dto.setLevelNo(hierarchyBuilderLevelDTOBean.size() + 1);
                        hierarchyBuilderLevelDTOBean.addBean(dto);
                        levelNumber.setReadOnly(false);
                        levelNumber.setValue(String.valueOf(hierarchyBuilderLevelDTOBean.size() + 1));
                        levelNumber.setReadOnly(true);
                        levelName.setValue(StringUtils.EMPTY);
                        tableName.setValue(StringUtils.EMPTY);
                        fieldName.setValue(StringUtils.EMPTY);
                        backSaveCheck = true;
                        levelDefinitionTable.setValue(null);

                    }
                    LOGGER.info("In addRow btnLevAdd.addClickListener Ended");
                } catch (SystemException ex) {
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                    LOGGER.error(ex);
                } catch (PortalException ex) {
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4013));
                    LOGGER.error(ex);
                } catch (Exception ex) {
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4013));
                    LOGGER.error(ex);
                }
            }
        });

        return btnLevAdd;
    }

    /**
     * Adds the delete button .
     *
     * @return the button
     */
    public Button addDeleteButton() {

        btnDelete.addClickListener(new Button.ClickListener() {
            /**
             * Called when a Button has been clicked .
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                LOGGER.info("In addDeleteButton btnDelete.addClickListener Started");
                if (event != null) {
                    final AbstractNotificationUtils notificationUtils = new AbstractNotificationUtils() {
                        /**
                         * Called when yes has been clicked .
                         */
                        @SuppressWarnings("PMD")
                        public void yesMethod() {
                            try {
                                hierarchyBuilderBinder.getErrorDisplay().clearError();
                                String systemId = hierarchyBuilderBinder.getField(ConstantsUtils.HIERARCHY_DEFINITION_ID).getValue() == null
                                        || (ConstantsUtils.NULL).equals(hierarchyBuilderBinder.getField(ConstantsUtils.HIERARCHY_DEFINITION_ID).getValue()) ? ConstantsUtils.EMPTY : String
                                                .valueOf(hierarchyBuilderBinder.getField(ConstantsUtils.HIERARCHY_DEFINITION_ID).getValue());
                                systemId = systemId.replace(ConstantsUtils.COMMA, ConstantsUtils.EMPTY);
                                int hierarchyId = Integer.valueOf(systemId);
                                String msg = logic.deleteHierarchy(hierarchyId);

                                if (ConstantsUtils.SUCCESS.equals(msg)) {

                                    getUI().getNavigator().navigateTo(HierarchyBuilderLandingView.NAME);

                                    Notification notif = new Notification(ConstantsUtils.HIERARCHY + hierarchyBuilderBinder.getField(ConstantsUtils.HIERARCHY_NAME).getValue()
                                            + ConstantsUtils.DELETED_SUCEESSFULLY, Notification.Type.HUMANIZED_MESSAGE);

                                    notif.setPosition(Position.BOTTOM_CENTER);
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
                                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4004));
                                LOGGER.error(ex);
                            } catch (Exception ex) {
                                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4004));
                                LOGGER.error(ex);
                            }

                        }

                        /**
                         * Called when no has been clicked .
                         */
                        public void noMethod() {

                        }
                    };
                    notificationUtils.getConfirmationMessage("Delete Confirmation", "You are about to delete the row.  Please click Yes if you would like to continue.");

                }
                LOGGER.info("In addDeleteButton btnDelete.addClickListener Ended");
            }
        });

        return btnDelete;
    }

    /**
     * Adds the level buttons .
     *
     * @return the horizontal layout
     */
    public HorizontalLayout addLevelButtons() {
        LOGGER.info("addLevelButtons method started ");
        final HorizontalLayout layout = new HorizontalLayout();
        layout.addStyleName(ConstantsUtils.RESPONSIVE_GRID);
        layout.setSpacing(true);
        layout.setMargin(true);
        layout.addComponent(addRow());
        layout.addComponent(addUpdateLevelButton());
        layout.addComponent(addResetButton());
        LOGGER.info("addLevelButtons method Returns Layout ");
        return layout;

    }

    /**
     * Adds the reset button .
     *
     * @return the button
     */
    public Button addResetButton() {

        resetButton.addClickListener(new Button.ClickListener() {
            /**
             * Called when a Button has been clicked .
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                LOGGER.info("In addResetButton resetButton.addClickListener Started");
                if (event != null) {
                    final AbstractNotificationUtils notificationUtils = new AbstractNotificationUtils() {
                        /**
                         * Called when yes has been clicked .
                         */
                        @SuppressWarnings("PMD")
                        public void yesMethod() {
                            btnLevAdd.setVisible(true);
                            btnUpdateLevel.setVisible(false);
                            levelNumber.setReadOnly(false);
                            levelNumber.setValue(String.valueOf(hierarchyBuilderLevelDTOBean.size() + 1));
                            levelNumber.setReadOnly(true);
                            levelName.setValue(ConstantsUtils.EMPTY);
                            levelValueReference.select(ConstantsUtils.USER_DEFINED);
                            tableName.setValue(ConstantsUtils.EMPTY);
                            fieldName.setValue(ConstantsUtils.EMPTY);
                            levelDefinitionTable.setValue(null);

                        }

                        /**
                         * Called when no has been clicked .
                         */
                        public void noMethod() {

                        }
                    };
                    notificationUtils.getConfirmationMessage(ConstantsUtils.CONFORMATION, "Are you sure you want to reset the page to default/previous values?");
                }
                LOGGER.info("In addResetButton resetButton.addClickListener Ended");
            }
        });

        return resetButton;
    }

    /**
     * Adds the update level button .
     *
     * @return the button
     */
    public Button addUpdateLevelButton() {

        btnUpdateLevel.addClickListener(new Button.ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                LOGGER.info("In addUpdateLevelButton btnUpdateLevel.addClickListener Started");
                if (event != null) {
                    hierarchyBuilderBinder.getErrorDisplay().clearError();
                    List<HierarchyBuilderLevelDTO> finalList = new ArrayList<HierarchyBuilderLevelDTO>();
                    List<HierarchyBuilderLevelDTO> updatelist = hierarchyBuilderLevelDTOBean.getItemIds();
                    for (HierarchyBuilderLevelDTO hierarchyBuilderLevelDTO : updatelist) {
                        finalList.add(hierarchyBuilderLevelDTO);
                    }
                    if (finalList.contains(lvlDefDTO)) {
                        finalList.remove(lvlDefDTO);
                    }
                    final HashMap levelNameMap = new HashMap();
                    for (final HierarchyBuilderLevelDTO levelDTO : finalList) {
                        levelNameMap.put(levelDTO.getLevelNo(), levelDTO.getLevelName());
                    }
                    final List<HierarchyBuilderLevelDTO> list = new ArrayList<HierarchyBuilderLevelDTO>();
                    for (int i = 0; i < hierarchyBuilderLevelDTOBean.size(); i++) {
                        final HierarchyBuilderLevelDTO dto = hierarchyBuilderLevelDTOBean.getIdByIndex(i);
                        if (dto.getLevelNo() == lvlDefDTO.getLevelNo()) {
                            if (levelName.getValue() != null && StringUtils.isNotEmpty(String.valueOf(levelName.getValue()))) {
                                if (levelName.getValue().trim().matches(ValidationUtils.ALPHA_NUMERIC)) {
                                    if (levelNameMap.containsValue(levelName.getValue().trim())) {
                                        MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Level Name already exists! Please enter a different Level Name", ButtonId.OK);
                                        return;
                                    } else {
                                        dto.setLevelName(String.valueOf(levelName.getValue()));
                                    }
                                } else {
                                    MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Please enter Alpha Numeric Value for the Level Name.", ButtonId.OK);
                                }
                            } else {
                                MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Please Enter Value for the Level Name", ButtonId.OK);
                                return;
                            }
                            if (levelValueReference.getValue() != null && !ConstantsUtils.EMPTY.equals(String.valueOf(levelValueReference.getValue()))) {
                                dto.setUserDefinedOrLinked(String.valueOf(levelValueReference.getValue()));
                            }
                            if (levelValueReference.getValue().equals(ConstantsUtils.LINKED)) {
                                if (tableName.getValue() != null && StringUtils.isNotEmpty(String.valueOf(tableName.getValue()))) {
                                    final int levelNo = Integer.valueOf(levelNumber.getValue());
                                    if (levelNo == 1 && ConstantsUtils.DATA_SELECTION.equals(hierarchy.getValue()) && ConstantsUtils.PRIMARY.equals(hierarchyType.getValue())) {
                                        MessageBox.showPlain(Icon.INFO, ConstantsUtils.NOTIFICATION, "Companies of type 'GLCOMP' only display for this level.", ButtonId.OK);
                                    }
                                    dto.setTableName(String.valueOf(tableName.getValue()));
                                } else {
                                    MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Please Enter Value for the table Name", ButtonId.OK);
                                    return;
                                }
                                if (fieldName.getValue() != null && StringUtils.isNotEmpty(String.valueOf(fieldName.getValue()))) {
                                    dto.setFieldName(String.valueOf(fieldName.getValue()));
                                } else {
                                    MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Please Enter Value for the field Name", ButtonId.OK);
                                    return;
                                }
                            } else {
                                dto.setTableName(StringUtils.EMPTY);
                                dto.setFieldName(StringUtils.EMPTY);
                            }
                        }
                        list.add(dto);
                    }
                    hierarchyBuilderLevelDTOBean.removeAllItems();
                    hierarchyBuilderLevelDTOBean.addAll(list);
                    backSaveCheck = true;
                    levelDefinitionTable.setValue(null);
                    btnLevAdd.setVisible(true);
                    btnUpdateLevel.setVisible(false);
                }
                LOGGER.info("In addUpdateLevelButton btnUpdateLevel.addClickListener Ended");
            }
        });

        return btnUpdateLevel;
    }

    /**
     * Level value section .
     *
     * @return the panel
     * @throws Exception the exception
     */
    private Panel levelValueSection() {
        LOGGER.info("levelValueSection method started ");
        panel.setCaption("Level Values ");
        panel.setSizeFull();
        final VerticalLayout layout = new VerticalLayout();
        final HorizontalLayout content = new HorizontalLayout();
        final CustomGridLayout gridLayout = new CustomGridLayout(2, 3);
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

        layout.addComponent(addValueButtons());
        content.addComponent(layout);

        panel.setContent(addSpace());

        panel.setContent(content);
        LOGGER.info("levelValueSection method returns Panel");
        return panel;
    }

    /**
     * Adds the value buttons .
     *
     * @return the horizontal layout
     */
    public HorizontalLayout addValueButtons() {
        LOGGER.info("addValueButtons method started ");
        final HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.setMargin(true);
        layout.addComponent(addLevelValues());
        layout.addComponent(deleteLevelValues());
        LOGGER.info("addValueButtons method returns layout ");
        return layout;

    }

    /**
     * Adds the level values .
     *
     * @return the button
     */
    public Button addLevelValues() {

        btnLevValuesAdd.addClickListener(new Button.ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                LOGGER.info("In addLevelValues btnLevValuesAdd.addClickListener Started");
                if (event != null) {
                    hierarchyBuilderBinder.getErrorDisplay().clearError();
                    if (levelDTOBean == null) {
                        final LevelDTO dto = new LevelDTO();
                        levelDTOBean.addBean(dto);
                    } else {
                        final List<LevelDTO> tempList = levelDTOBean.getItemIds();
                        final List<LevelDTO> list = new ArrayList<LevelDTO>();
                        for (int i = 0; i < tempList.size(); i++) {
                            if (tempList.get(i).getLevelValues() == null || ConstantsUtils.EMPTY.equals(tempList.get(i).getLevelValues())) {
                                MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Please Enter Value for the previous Level Value", ButtonId.OK);
                                return;
                            } else {
                                list.add(tempList.get(i));
                            }
                        }
                        final LevelDTO dto = new LevelDTO();
                        list.add(dto);
                        levelDTOBean.removeAllItems();
                        levelDTOBean.addAll(list);
                        backSaveCheck = true;
                    }
                }
                LOGGER.info("In addLevelValues btnLevValuesAdd.addClickListener Ended");

            }
        });

        return btnLevValuesAdd;
    }

    /**
     * Delete level values .
     *
     * @return the button
     */
    public Button deleteLevelValues() {
        btnLevValuesDelete.addClickListener(new Button.ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                LOGGER.info("In deleteLevelValues btnLevValuesDelete.addClickListener Started");
                if (levelValuesTable.getValue() == null) {

                    MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Please select a row to delete", ButtonId.OK);
                } else {

                    final AbstractNotificationUtils notificationUtils = new AbstractNotificationUtils() {
                        /**
                         * Called when yes has been clicked.
                         */
                        @SuppressWarnings("PMD")
                        public void yesMethod() {

                            if (levelDTOBean.size() > ConstantsUtils.ONE) {
                                levelDTOBean.removeItem(levelValuesTable.getValue());
                                backSaveCheck = true;

                            } else {
                                AbstractNotificationUtils.getAlertNotification("Delete Error", "Atleast one Level Value must be there for a User defined Level");
                            }

                        }

                        /**
                         * Called when no has been clicked.
                         */
                        public void noMethod() {

                        }
                    };

                    notificationUtils.getConfirmationMessage("Delete Confirmation", "You are about to delete the row.  Please click Yes if you would like to continue.");
                }
                LOGGER.info("In deleteLevelValues btnLevValuesDelete.addClickListener Ended");

            }
        });

        return btnLevValuesDelete;
    }

    /**
     * Level definition list table .
     *
     * @return the filter table
     */
    public ExtFilterTable levelDefinitionListTable() {
        LOGGER.info("levelDefinitionListTable method started ");
        levelDefinitionTable.markAsDirty();
        levelDefinitionTable.setFilterBarVisible(true);
        levelDefinitionTable.setFilterDecorator(new ExtDemoFilterDecorator());
        levelDefinitionTable.setContainerDataSource(hierarchyBuilderLevelDTOBean);
        levelDefinitionTable.setVisibleColumns(CommonUIUtil.AC_HB_LEVEL_DEFINITION_COLUMNS);

        levelDefinitionTable.setColumnHeaders(CommonUIUtil.AC_HB_LEVEL_DEFINITION_HEADER);
        levelDefinitionTable.setPageLength(7);
        levelDefinitionTable.setImmediate(true);
        levelDefinitionTable.setSelectable(true);
        levelDefinitionTable.setMultiSelect(true);

        levelDefinitionTable.setSizeFull();
        levelDefinitionTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            /**
             * Called when a Button has been clicked .
             */
            @SuppressWarnings("PMD")
            public void itemClick(final ItemClickEvent event) {

                if (event != null) {
                    LOGGER.info("In levelDefinitionListTable levelDefinitionTable.addItemClickListener Started");
                    deleteRowFlag = false;
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
                    LOGGER.info("In levelDefinitionListTable levelDefinitionTable.addItemClickListener Ended");
                }
            }
        });
        levelDefinitionTable.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Notifies this listener that the Property's value has changed.
             *
             *
             */
            @SuppressWarnings("PMD")
            public void valueChange(final Property.ValueChangeEvent event) {
                LOGGER.info("In addResultsTable ValueChange.resultsCustomerClick Started");
                final Set<HierarchyBuilderDTO> companyDetailsList = (Set<HierarchyBuilderDTO>) levelDefinitionTable.getValue();
                if (!companyDetailsList.isEmpty()) {
                    btnUpdateLevel.setVisible(true);
                    btnLevAdd.setVisible(false);
                } else {
                    btnUpdateLevel.setVisible(false);
                    btnLevAdd.setVisible(true);
                    levelNumber.setReadOnly(false);
                    levelNumber.setValue(String.valueOf(hierarchyBuilderLevelDTOBean.size() + 1));
                    levelNumber.setReadOnly(true);
                    levelName.setValue(ConstantsUtils.EMPTY);
                    levelValueReference.select(ConstantsUtils.USER_DEFINED);
                    tableName.setValue(ConstantsUtils.EMPTY);
                    fieldName.setValue(ConstantsUtils.EMPTY);
                    levelDefinitionTable.setValue(null);
                }
                LOGGER.info("In addResultsTable ValueChange.resultsCustomerClick Ended");
            }
        });
        levelDefinitionTable.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs .
             */
            public void error(final com.vaadin.server.ErrorEvent event) {

            }
        });
        levelDefinitionTable.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * This method listens to data source value changes and passes the
             * changes forwards .
             */
            public void valueChange(final ValueChangeEvent event) {

            }
        });
        return levelDefinitionTable;
    }

    private Button removeButton() {
        btnLevDelete.addClickListener(new ClickListener() {
            /**
             * Called when a Button has been clicked .
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                LOGGER.info("In RemoveButton btnLevDelete.addClickListener Started");
                if (hierarchyBuilderLevelDTOBean.size() > ConstantsUtils.ZERO_NUM) {
                    if (deleteRowFlag) {
                        MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "No rows is selected to delete", ButtonId.OK);
                    } else {
                        deleteRowFlag = true;
                        final AbstractNotificationUtils notificationUtils = new AbstractNotificationUtils() {
                            /**
                             * Called when yes has been clicked .
                             */
                            @SuppressWarnings("PMD")
                            public void yesMethod() {
                                java.util.Set<HierarchyBuilderLevelDTO> selectedLevelsList = (java.util.Set<HierarchyBuilderLevelDTO>) levelDefinitionTable.getValue();
                                for (HierarchyBuilderLevelDTO item : selectedLevelsList) {
                                    hierarchyBuilderLevelDTOBean.removeItem(item);
                                    try {
                                        logic.deleteHierarchyLevelValues(item.getLevelSystemId());
                                    } catch (SystemException ex) {
                                        final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                                        AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                                        LOGGER.error(ex);
                                    } catch (PortalException ex) {
                                        AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4002));
                                        LOGGER.error(ex);
                                    } catch (Exception ex) {
                                        AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4002));
                                        LOGGER.error(ex);
                                    }
                                }
                                final List<HierarchyBuilderLevelDTO> itemIds = hierarchyBuilderLevelDTOBean.getItemIds();
                                for (int i = 0; i < itemIds.size(); i++) {
                                    final HierarchyBuilderLevelDTO beanItem = itemIds.get(i);
                                    levelDefinitionTable.getContainerProperty(beanItem, "levelNo").setValue(i + 1);
                                }
                                levelDefinitionTable.setContainerDataSource(hierarchyBuilderLevelDTOBean);
                                levelDefinitionTable.setVisibleColumns(CommonUIUtil.AC_HB_LEVEL_DEFINITION_COLUMNS);
                                levelDefinitionTable.setColumnHeaders(CommonUIUtil.AC_HB_LEVEL_DEFINITION_HEADER);
                                levelNumber.setReadOnly(false);
                                levelNumber.setValue(String.valueOf(hierarchyBuilderLevelDTOBean.size() + 1));
                                levelNumber.setReadOnly(true);
                                btnLevAdd.setVisible(true);
                                btnUpdateLevel.setVisible(false);
                                levelName.setValue(ConstantsUtils.EMPTY);
                                tableName.setValue(ConstantsUtils.EMPTY);
                                fieldName.setValue(ConstantsUtils.EMPTY);
                                backSaveCheck = true;

                            }

                            /**
                             * Called when no has been clicked .
                             */
                            public void noMethod() {

                            }
                        };
                        notificationUtils.getConfirmationMessage("Delete Confirmation", "You are about to delete the row.  Please click Yes if you would like to continue.");

                    }
                } else {
                    MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "No rows available to delete", ButtonId.OK);
                }
                LOGGER.info("In RemoveButton btnLevDelete.addClickListener Ended");
            }
        });
        return btnLevDelete;
    }

    /**
     * Level values list table .
     *
     * @return the table
     */
    public Table levelValuesListTable() {
        levelValuesTable.markAsDirty();
        levelValuesTable.setContainerDataSource(levelDTOBean);
        levelValuesTable.setVisibleColumns(CommonUIUtil.AC_HB_LEVEL_VALUES_COLUMNS);
        levelValuesTable.setCaption("Results");
        levelValuesTable.setColumnHeaders(CommonUIUtil.AC_HB_LEVEL_VALUES_HEADER);
        levelValuesTable.setPageLength(7);
        levelValuesTable.setImmediate(true);
        levelValuesTable.setSelectable(true);
        levelValuesTable.setWidth("350px");
        levelValuesTable.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs.
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {

            }
        });
        return levelValuesTable;
    }

    public void updateButtonLogic(final ClickEvent event, final boolean flag) {
        try {

            String systemId = hierarchyBuilderBinder.getField("hierarchyDefinitionSystemId").getValue() == null
                    || ConstantsUtils.NULL.equals(hierarchyBuilderBinder.getField("hierarchyDefinitionSystemId").getValue()) ? ConstantsUtils.EMPTY : String.valueOf(hierarchyBuilderBinder
                                    .getField("hierarchyDefinitionSystemId").getValue());
            systemId = systemId.replace(",", ConstantsUtils.EMPTY);
            if (!systemId.equals("null") && !systemId.equals("0")) {
                int id = Integer.valueOf(systemId);
                final DynamicQuery levelValuesDynamicQuery = DynamicQueryFactoryUtil.forClass(RelationshipBuilder.class);
                levelValuesDynamicQuery.add(RestrictionsFactoryUtil.eq("hierarchyDefinitionSid", id));
                List<RelationshipBuilder> levelValuesList = RelationshipBuilderLocalServiceUtil.dynamicQuery(levelValuesDynamicQuery);
                if (levelValuesList != null && levelValuesList.size() > 0) {
                    MessageBox.showPlain(Icon.ERROR, "Edit", "Cannot Edit the Hierarchy which is already associated with existing Relationship", ButtonId.OK);
                    return;
                }
            }

            List<HierarchyBuilderLevelDTO> levelvalues = new ArrayList<HierarchyBuilderLevelDTO>();
            levelvalues = hierarchyBuilderLevelDTOBean.getItemIds();
            if (ConstantsUtils.EMPTY.equals(hierarchyType.getValue()) && ConstantsUtils.EMPTY.equals(hierarchyName.getValue().trim())) {

                MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Please enter  Hierarchy Name & Hierarchy Type.", ButtonId.OK);
            } else if (ConstantsUtils.EMPTY.equals(hierarchyType.getValue())) {
                MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Please enter a Hierarchy Type.", ButtonId.OK);
            } else if (ConstantsUtils.EMPTY.equals(hierarchyName.getValue().trim())) {
                MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Please enter a Hierarchy Name.", ButtonId.OK);
            } else if (ConstantsUtils.EMPTY.equals(hierarchy.getValue())) {
                MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Please select the Hierarchy Category.", ButtonId.OK);
            } else if (levelvalues.isEmpty()) {
                MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Please add levels for the Hierarchy.", ButtonId.OK);
            } else {
                if (event != null) {
                    hierarchyBuilderBinder.getErrorDisplay().clearError();
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
                                    count = logic.updateLevelCheckCount(hierarchyBuilderLevelDTOBean.getIdByIndex(i).getLevelSystemId(), hierarchyBuilderLevelDTOBean.getIdByIndex(i)
                                            .getLevelNo());
                                    if (count > ConstantsUtils.ZERO_NUM) {
                                    } else {
                                        MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, ERROR_INFO + hierarchyBuilderLevelDTOBean.getIdByIndex(i).getLevelNo(), ButtonId.OK);
                                        return;
                                    }

                                }
                            }
                        }
                    }

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
                    final String selectedHierarchy = (String) hierarchy.getValue();
                    final String hierType = (String) hierarchyType.getValue();
                    if (selectedHierarchy != null && selectedHierarchy.equals(ConstantsUtils.DATA_SELECTION) && ConstantsUtils.PRIMARY.equals(hierType)) {

                        if (!levelvalues.isEmpty() && levelvalues.size() < 4) {
                            MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "A minimum of four levels is required when the Hierarchy Category is Data Selection", ButtonId.OK);
                            return;
                        }

                    } else if (levelvalues.size() < ConstantsUtils.TWO_NUM) {
                        MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "A minimum of two levels is required", ButtonId.OK);
                        return;
                    }
                    if (flag) {
                        MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION, "Save record " + hierarchyName.getValue() + " ?", new MessageBoxListener() {
                            /**
                             * save button click logic
                             */
                            @SuppressWarnings("PMD")
                            public void buttonClicked(final ButtonId buttonId) {
                                if (buttonId.name().equals("YES")) {
                                    try {
                                        final String msg = logic.saveHierarchy(hierarchyBuilderBinder, hierarchyBuilderLevelDTOBean, sessionDTO);

                                        if (msg.equals(ConstantsUtils.SUCCESS)) {

                                            final Notification notif = new Notification(
                                                    ConstantsUtils.HIERARCHY + StringUtils.EMPTY + hierarchyBuilderBinder.getField(ConstantsUtils.HIERARCHY_NAME).getValue()
                                                    + StringUtils.EMPTY + " Updated successfully",
                                                    Notification.Type.HUMANIZED_MESSAGE);

                                            notif.setDelayMsec(2000);
                                            notif.setPosition(Position.BOTTOM_CENTER);

                                            notif.show(Page.getCurrent());

                                            backSaveCheck = false;

                                        } else {
                                            hierarchyBuilderBinder.getErrorDisplay().setError(ConstantsUtils.ERROR_OCCURED);
                                        }
                                    } catch (SystemException ex) {
                                        final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                                        AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                                        LOGGER.error(ex);
                                    } catch (Exception ex) {
                                        LOGGER.error(ex);
                                        AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4002));
                                        LOGGER.error(ex);
                                    }

                                }
                            }
                        }, ButtonId.YES, ButtonId.NO);
                    } else {
                        final String msg = logic.saveHierarchy(hierarchyBuilderBinder, hierarchyBuilderLevelDTOBean, sessionDTO);
                    }

                }
            }
            LOGGER.info("In addUpdateButton btnUpdate.addClickListener Ended");
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
    }

    /**
     * This method is always called before the view is shown on screen .
     *
     * @param event the event
     */
    public void enter(final ViewChangeListener.ViewChangeEvent event) {

    }
}
