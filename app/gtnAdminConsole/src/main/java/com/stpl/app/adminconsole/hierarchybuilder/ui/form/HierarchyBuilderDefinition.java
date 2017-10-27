/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.hierarchybuilder.ui.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.adminconsole.common.dto.SessionDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import com.stpl.app.adminconsole.common.util.CommonUIUtil;
import com.stpl.app.adminconsole.common.util.CommonUtil;
import com.stpl.app.adminconsole.hierarchybuilder.dto.HierarchyBuilderDTO;
import com.stpl.app.adminconsole.hierarchybuilder.dto.HierarchyBuilderLevelDTO;
import com.stpl.app.adminconsole.hierarchybuilder.dto.LevelDTO;
import com.stpl.app.adminconsole.hierarchybuilder.dto.TableFieldLookUpDTO;
import com.stpl.app.adminconsole.hierarchybuilder.logic.HierarchyBuilderLogic;
import com.stpl.app.adminconsole.hierarchybuilder.logic.tableLogic.TableFieldLogic;
import com.stpl.app.adminconsole.hierarchybuilder.ui.view.HierarchyBuilderLandingView;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.ErrorCodeUtil;
import com.stpl.app.adminconsole.util.ErrorCodes;
import com.stpl.app.adminconsole.util.ResponsiveUtils;
import com.stpl.app.model.RelationshipBuilder;
import com.stpl.app.service.RelationshipBuilderLocalServiceUtil;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.ui.util.NumericConstants;
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
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
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
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

// TODO: Auto-generated Javadoc
/**
 * The Class HierarchyBuilderDefinition.
 *
 * @author nisanthan
 */
public class HierarchyBuilderDefinition extends CustomComponent implements View {

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
     * The hierarchyBuilderId.
     */
    private HierarchyBuilderLevelDTO hierarchyBuilderId;
    /**
     * The hierarchy builder dto.
     */
    private HierarchyBuilderDTO hierarchyBuilderDTO;
    /**
     * The logic.
     */
    private final HierarchyBuilderLogic logic;
    /**
     * The levelValuesPanel.
     */
    @UiField("")
    private Panel levelValuesPanel;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(HierarchyBuilderDefinition.class);
    /**
     * The hierarchy category.
     */
    @UiField("hierarchyCategory")
    private ComboBox hierarchyCategory;
    /**
     * The hierarchy name.
     */
    @UiField("hierarchyName")
    private TextField hierarchyName;
    /**
     * The level number.
     */
    @UiField("levelNo")
    private TextField levelNo;
    /**
     * The level name.
     */
    @UiField("levelName")
    private TextField levelName;
    /**
     * The table name.
     */
    @UiField("tableName")
    private CustomTextField tableName;
    /**
     * The field name.
     */
    @UiField("fieldName")
    private CustomTextField fieldName;
    /**
     * The no in level values.
     */
    @UiField("displayLevelNo")
    private TextField displayLevelNo;
    /**
     * The name in level values.
     */
    @UiField("displayLevelName")
    private TextField displayLevelName;
    /**
     * The hierarchy type.
     */
    @UiField("hierarchyType")
    private OptionGroup hierarchyType;
    /**
     * The level value reference.
     */
    @UiField("userDefinedOrLinked")
    private OptionGroup userDefinedOrLinked;
    /**
     * The hierarchy builder level dto bean.
     */
    public BeanItemContainer<HierarchyBuilderLevelDTO> hierarchyBuilderLevelDTOBean = new BeanItemContainer<>(HierarchyBuilderLevelDTO.class);
    /**
     * The level dto bean.
     */
    private final BeanItemContainer<LevelDTO> levelDTOBean = new BeanItemContainer<>(LevelDTO.class);
    private final BeanItemContainer<TableFieldLookUpDTO> tableFieldLookUpDTOBean = new BeanItemContainer<>(TableFieldLookUpDTO.class);

    /**
     * The excel export levels.
     */
    @UiField("excelExportLevels")
    private Button excelExportLevels;
    /**
     * The btn lev add.
     */
    @UiField("addRowBtn")
    private Button addRowBtn;
    /**
     * The btn lev delete.
     */
    @UiField("deleteRowBtn")
    private Button deleteRowBtn;
    /**
     * The btn lev display.
     */
    @UiField("displayBtn")
    private Button displayBtn;
    /**
     * The reset button.
     */
    @UiField("resetButton")
    private Button resetButton;
    /**
     * The hierarchy definition system id.
     */
    public TextField hierarchyDefinitionSystemId = new TextField();

    /**
     * The level definition table.
     */
    private final ExtFilterTable levelDefinitionTable = new ExtFilterTable();
    /**
     * The btn update level.
     */
    @UiField("updateRowBtn")
    private Button updateRowBtn;
    /**
     * The company value.
     */
    private Object companyValue;
    /**
     * The hierarchy.
     */
    private final TextField hierarchy = new TextField();
    /**
     * The btn lev values add.
     */
    @UiField("displayAddRowBtn")
    private Button displayAddRowBtn;
    /**
     * The btn lev values delete.
     */
    @UiField("displayDeleteRowBtn")
    private Button displayDeleteRowBtn;
    /**
     * The lvl def dto.
     */
    private HierarchyBuilderLevelDTO lvlDefDTO;
    /**
     * The level values table.
     */

    private final Table levelValuesTable = new Table();

    TableFieldLogic tableLogic = new TableFieldLogic();
    protected ExtPagedTable levelValuesPagedTable = new ExtPagedTable(tableLogic);

    /**
     * The btn save.
     */
    @UiField("saveBtn")
    private Button saveBtn;
    /**
     * The btn reset.
     */
    @UiField("resetBtn")
    private Button resetBtn;
    /**
     * The back button.
     */
    @UiField("backBtn")
    private Button backBtn;

    @UiField("viewBackBtn")
    private Button viewBackBtn;
    /**
     * flag to delete flag
     */
    private static boolean deleteRowFlag = true;
    /**
     * flag to display flag
     */
    private static boolean displayRowFlag = true;

    @UiField("resultsLayout")
    private VerticalLayout resultsLayout;

    @UiField("resultsControlLayout")
    private HorizontalLayout resultsControlLayout;

    @UiField("levelResultsLayout")
    private VerticalLayout levelResultsLayout;

    @UiField("levelResultsControlLayout")
    private HorizontalLayout levelResultsControlLayout;

    @UiField("hLayout1")
    private HorizontalLayout hLayout1;

    @UiField("hLayout2")
    private HorizontalLayout hLayout2;

    public static final String ERROR_INFO = "Please Enter atleast one level value for the Level No ";

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
     * Sets the hierarchy builder level dto bean.
     *
     * @param hierarchyBuilderLevelDTOBean the hierarchy builder level dto bean
     */
    public void setHierarchyBuilderLevelDTOBean(final BeanItemContainer<HierarchyBuilderLevelDTO> hierarchyBuilderLevelDTOBean) {
        this.hierarchyBuilderLevelDTOBean = hierarchyBuilderLevelDTOBean;
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
     * Gets the logic.
     *
     * @return the logic
     */
    public HierarchyBuilderLogic getLogic() {
        return logic;
    }

    /**
     * Gets the levelValuesPanel.
     *
     * @return the levelValuesPanel
     */
    public Panel getLevelValuesPanel() {
        return levelValuesPanel;
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
    public TextField getLevelNo() {
        return levelNo;
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
    public TextField getDisplayLevelNo() {
        return displayLevelNo;
    }

    /**
     * Gets the name in level values.
     *
     * @return the name in level values
     */
    public TextField getDisplayLevelName() {
        return displayLevelName;
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
    public OptionGroup getUserDefinedOrLinked() {
        return userDefinedOrLinked;
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
     * Gets the btn lev add.
     *
     * @return the btn lev add
     */
    public Button getAddRowBtn() {
        return addRowBtn;
    }

    /**
     * Gets the btn lev delete.
     *
     * @return the btn lev delete
     */
    public Button getDeleteRowBtn() {
        return deleteRowBtn;
    }

    /**
     * Gets the btn lev display.
     *
     * @return the btn lev display
     */
    public Button getDisplayBtn() {
        return displayBtn;
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
     * Gets the level definition table.
     *
     * @return the level definition table
     */
    public ExtFilterTable getLevelDefinitionTable() {
        return levelDefinitionTable;
    }

    /**
     * Gets the btn update level.
     *
     * @return the btn update level
     */
    public Button getUpdateRowBtn() {
        return updateRowBtn;
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
     * Gets the btn lev values add.
     *
     * @return the btn lev values add
     */
    public Button getDisplayAddRowBtn() {
        return displayAddRowBtn;
    }

    /**
     * Gets the btn lev values delete.
     *
     * @return the btn lev values delete
     */
    public Button getDisplayDeleteRowBtn() {
        return displayDeleteRowBtn;
    }

    /**
     * Gets the btn save.
     *
     * @return the btn save
     */
    public Button getSaveBtn() {
        return saveBtn;
    }

    /**
     * Gets the btn reset.
     *
     * @return the btn reset
     */
    public Button getResetBtn() {
        return resetBtn;
    }

    SessionDTO sessionDTO;

    /**
     * Instantiates a new hierarchy builder definition.
     *
     * @param hierarchyBuilderDTO the hierarchy builder dto
     * @param hierarchyBuilderLevelDTOBean the hierarchy builder level dto bean
     * @param sessionDTO
     * @throws Exception
     * @throws PortalException
     * @throws SystemException
     */
    public HierarchyBuilderDefinition(final HierarchyBuilderDTO hierarchyBuilderDTO, final BeanItemContainer<HierarchyBuilderLevelDTO> hierarchyBuilderLevelDTOBean,
            final SessionDTO sessionDTO) throws SystemException {
        super();
        LOGGER.debug("HierarchyBuilderDefinition constructor started");
        this.hierarchyBuilderDTO = hierarchyBuilderDTO;
        this.hierarchyBuilderLevelDTOBean = hierarchyBuilderLevelDTOBean;
        this.sessionDTO = sessionDTO;
        logic = new HierarchyBuilderLogic(sessionDTO);
        init();
        LOGGER.debug("HierarchyBuilderDefinition constructor Ended");
    }

    /**
     * Inits the.
     *
     * @throws Exception
     * @throws PortalException
     * @throws SystemException
     */
    public final void init() throws SystemException {
        LOGGER.debug("Entering init()");
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/clara/hierarchyDefinition.xml"), this));
        configureFields();
        getBinder();
        levelDefinitionListTable();
        levelValuesListTable();
        if (sessionDTO.getMode().equals(ConstantsUtils.EDIT)) {
            saveBtn.setCaption("UPDATE");
            addUpdateButton();
        }
        if (sessionDTO.getMode().equals(ConstantsUtils.ADD)) {
            addSaveButton();
        }
        if (sessionDTO.getMode().equals(ConstantsUtils.VIEW)) {
            disableComponentsOnViewMode();
        }
        LOGGER.debug("Ending init()");
    }

    /**
     * Gets the binder.
     *
     * @return the binder
     * @throws Exception the exception
     */
    private CustomFieldGroup getBinder() {
        LOGGER.debug("getBinder Method started");
        hierarchyBuilderBinder = new CustomFieldGroup(new BeanItem<>(hierarchyBuilderDTO));
        hierarchyBuilderBinder.setBuffered(true);
        hierarchyBuilderBinder.bindMemberFields(this);
        hierarchyBuilderBinder.setErrorDisplay(errorMsg);
        LOGGER.debug("getBinder Method returns hierarchyBuilderBinder");
        return hierarchyBuilderBinder;
    }

    /**
     * Configure fields.
     *
     * @throws Exception
     * @throws SystemException
     */
    private void configureFields() throws SystemException {
        LOGGER.debug("configureFields Method started");
        excelExportLevels.setIcon(new ThemeResource("../../icons/excel.png"));
        excelExportLevels.setStyleName("link");
        excelExportLevels.setDescription(ConstantsUtils.EXCEL_EXPORT);
        excelExportLevels.setIconAlternateText(ConstantsUtils.EXCEL_EXPORT);
        hierarchyName.focus();
        hierarchyName.setRequired(true);
        hierarchyName.setImmediate(true);
        hierarchyName.setRequiredError("Please enter Hierarchy Name");

        hierarchyName.setValue(StringUtils.EMPTY);
        levelName.setValue(StringUtils.EMPTY);
        tableName.setValue(StringUtils.EMPTY);
        fieldName.setValue(StringUtils.EMPTY);

        hierarchyType.setImmediate(true);
        hierarchyType.setValidationVisible(true);
        hierarchyType.addItem(ConstantsUtils.PRIMARY);
        hierarchyType.addItem(ConstantsUtils.SECONDARY);
        hierarchyType.select(ConstantsUtils.PRIMARY);
        hierarchyType.setNullSelectionAllowed(true);
        hierarchyType.setNullSelectionItemId(ConstantsUtils.PRIMARY);

        hierarchyCategory = CommonUtil.getComboBox(hierarchyCategory, CommonUIUtil.HIERARCHY_CATEGORY);
        hierarchyCategory.setNullSelectionAllowed(false);

        hierarchyCategory.setValue(null);

        hierarchyCategory.setImmediate(true);
        hierarchyCategory.setRequired(true);
        hierarchyCategory.setRequiredError("Please select Hierarchy Category");
        hierarchyCategory.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * This method listens to data source value changes and passes the
             * changes forwards.
             */
            @SuppressWarnings("PMD")
            public void valueChange(final ValueChangeEvent event) {
                LOGGER.debug("In configureFields hierarchyCategory.addValueChangeListener Started");
                if (hierarchyCategory.getValue() == null) {
                    final String nullValue = null;
                    companyValue = nullValue;
                    hierarchy.setValue(null);
                } else {
                    companyValue = ((HelperDTO) hierarchyCategory.getValue()).getId();
                    hierarchy.setValue(((HelperDTO) hierarchyCategory.getValue()).getDescription());
                    final int selectedHierarchySysId = Integer.valueOf(companyValue.toString());
                    sessionDTO.setSelectedHierarchyCategory(selectedHierarchySysId);

                }
                LOGGER.debug("In configureFields hierarchyCategory.addValueChangeListener Ended");
            }
        });

        levelNo.setImmediate(true);
        levelName.setImmediate(true);

        levelName.setValidationVisible(true);
        levelName.setMaxLength(NumericConstants.THIRTY_EIGHT);

        userDefinedOrLinked.setImmediate(true);
        userDefinedOrLinked.addItem(ConstantsUtils.USER_DEFINED);
        userDefinedOrLinked.addItem(ConstantsUtils.LINKED);
        userDefinedOrLinked.select(ConstantsUtils.USER_DEFINED);
        userDefinedOrLinked.addStyleName("horizontaladd");
        userDefinedOrLinked.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * This method listens to data source value changes and passes the
             * changes forwards.
             */
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
                userDefinedOrLinked.focus();
                LOGGER.debug("In configureFields levelValueReference.addValueChangeListener Ended");
            }
        });

        levelValuesPanel.setVisible(false);
        updateRowBtn.setVisible(false);
        displayLevelNo.setReadOnly(true);
        displayLevelName.setReadOnly(true);

        if (hierarchyBuilderLevelDTOBean == null) {
            levelNo.setReadOnly(false);
            levelNo.setValue(String.valueOf(1));
            levelNo.setReadOnly(true);
        } else {
            levelNo.setReadOnly(false);
            levelNo.setValue(String.valueOf(hierarchyBuilderLevelDTOBean.size() + 1));
            levelNo.setReadOnly(true);
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
                     * Called when close event occurs.
                     */
                    @SuppressWarnings("PMD")
                    public void windowClose(final Window.CloseEvent e) {
                        addRowBtn.focus();
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
                     * Called when close Button has been clicked.
                     */
                    @SuppressWarnings("PMD")
                    public void windowClose(final Window.CloseEvent e) {
                        addRowBtn.focus();
                    }
                });
            }
        });
        viewBackBtn.setVisible(false);
        LOGGER.debug("configureFields Method Ended");

    }

    @UiHandler("excelExportLevels")
    public void excelButtonLogic(Button.ClickEvent event) {
        LOGGER.debug("excelButtonLogic Started");
        if (levelDefinitionTable.size() > ConstantsUtils.ZERO_NUM) {
            try {
                List<List> exportList;
                final List<String> dollarList = new ArrayList();
                dollarList.add("AdminConsole");
                exportList = new ArrayList<>();
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
        LOGGER.debug("excelButtonLogic Ended");
    }

    /**
     * Adds the update button.
     *
     * @return the button
     */
    public void addUpdateButton() {

        saveBtn.addClickListener(new Button.ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("In addUpdateButton btnUpdate.addClickListener started");
                updateButtonLogic(event, true);
            }
        });
    }

    public void addSaveButton() {
        saveBtn.addClickListener(new Button.ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("In addSaveButton btnUpdate.addClickListener started");
                saveBtnLogic(event);
            }
        });
    }

    /**
     * Adds the display button.
     *
     * @return the button
     */
    @UiHandler("displayBtn")
    public void displayBtnLogic(Button.ClickEvent event) {
        LOGGER.debug("displayBtnLogic Started");
        if (levelDefinitionTable.getValue() == null || displayRowFlag) {
            MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Please select a row to display.", ButtonId.OK);
        } else {
            try {
                displayRowFlag = true;
                hierarchyBuilderBinder.getErrorDisplay().clearError();

                levelValuesPanel.setVisible(true);
                if (hierarchyBuilderId != null && hierarchyBuilderId.getUserDefinedOrLinked().equals(ConstantsUtils.USER_DEFINED)) {

                    new HierarchyBuilderLogic().saveTempData(hierarchyBuilderId.getLevelNo(), hierarchyBuilderId.getLevelName(), levelDTOBean);
                }

                hierarchyBuilderId = lvlDefDTO;
                displayLevelNo.setReadOnly(false);
                displayLevelNo.setValue(String.valueOf(hierarchyBuilderId.getLevelNo()));
                displayLevelNo.setReadOnly(true);
                displayLevelName.setReadOnly(false);
                displayLevelName.setValue(hierarchyBuilderId.getLevelName());
                displayLevelName.setReadOnly(true);
                levelDTOBean.removeAllItems();

                if (hierarchyBuilderId.getLevelList() != null && hierarchyBuilderId.getUserDefinedOrLinked().equals(ConstantsUtils.USER_DEFINED)) {
                    levelDTOBean.addAll(new HierarchyBuilderLogic().getLevelList(hierarchyBuilderId.getLevelNo()));
                    levelValuesTable.setVisible(true);
                    levelValuesTable.setEditable(true);
                    displayAddRowBtn.setEnabled(true);
                    displayDeleteRowBtn.setEnabled(true);
                    levelValuesPagedTable.setVisible(false);
                    levelResultsControlLayout.setVisible(false);
                } else {
                    final List hierDTO = new ArrayList();
                    hierDTO.add(hierarchyType.getValue() == null ? ConstantsUtils.PRIMARY : hierarchyType.getValue().toString());
                    hierDTO.add(hierarchy.getValue().toString());
                    hierDTO.add(hierarchyBuilderId.getLevelNo());
                    TableFieldLookUpDTO dto = new TableFieldLookUpDTO();
                    dto.setTableName(hierarchyBuilderId.getTableName());
                    dto.setFieldName(hierarchyBuilderId.getFieldName());
                    tableLogic.fireSetData(dto, false, ConstantsUtils.DISPLAY);
                    levelValuesTable.setEditable(false);
                    displayAddRowBtn.setEnabled(false);
                    displayDeleteRowBtn.setEnabled(false);
                    levelValuesTable.setVisible(false);
                    levelValuesPagedTable.setVisible(true);
                    levelResultsControlLayout.setVisible(true);
                }
            } catch (SystemException ex) {
                final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                LOGGER.error(ex);
            } catch (Exception ex) {
                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4012));
                LOGGER.error(ex);
            }
        }
        LOGGER.debug("displayBtnLogic Ended");
    }

    /**
     * Adds the save button.
     *
     * @return the button
     */
    public void saveBtnLogic(Button.ClickEvent event) {
        LOGGER.debug("addSaveButton Method started");
        saveButtonLogic(event, true);
        LOGGER.debug("addSaveButton Method Ended");
    }

    /**
     * Adds the reset page.
     *
     * @return the component
     */
    @UiHandler("resetBtn")
    public void resetBtnLogic(Button.ClickEvent event) {
        LOGGER.debug("resetBtnLogic Started");
        final String mode = sessionDTO.getMode();
        if (event != null) {
            final AbstractNotificationUtils notificationUtils = new AbstractNotificationUtils() {
                /**
                 * Called when yes has been clicked.
                 */
                @SuppressWarnings("PMD")
                public void yesMethod() {
                    levelDefinitionTable.removeAllItems();
                    if ((ConstantsUtils.EDIT).equalsIgnoreCase(mode)) {
                        final HierarchyBuilderLogic hierarchyLogic = new HierarchyBuilderLogic();
                        final int systemId = sessionDTO.getSystemId();
                        try {
                            hierarchyBuilderDTO = hierarchyLogic.gethierarchyBuilderDTO(Integer.valueOf(systemId));
                            hierarchyName.setValue(hierarchyBuilderDTO.getHierarchyName());
                            hierarchyType.setValue(hierarchyBuilderDTO.getHierarchyType());
                            hierarchyCategory.setValue(hierarchyBuilderDTO.getHierarchyCategory());
                            hierarchyBuilderLevelDTOBean.addAll(hierarchyLogic.getHierarchyLevelDTOByDefinitionSystemId(systemId));
                            levelNo.setReadOnly(false);
                            levelNo.setValue(String.valueOf(hierarchyBuilderLevelDTOBean.size() + 1));
                            levelNo.setReadOnly(true);
                            levelName.setValue(ConstantsUtils.EMPTY);
                            userDefinedOrLinked.select(ConstantsUtils.USER_DEFINED);
                            tableName.setValue(ConstantsUtils.EMPTY);
                            fieldName.setValue(ConstantsUtils.EMPTY);
                        } catch (Exception ex) {
                            LOGGER.error(ex);
                        }
                    } else {
                        addRowBtn.setVisible(true);
                        updateRowBtn.setVisible(false);
                        levelNo.setReadOnly(false);
                        levelNo.setValue(String.valueOf(hierarchyBuilderLevelDTOBean.size() + 1));
                        levelNo.setReadOnly(true);
                        levelName.setValue(ConstantsUtils.EMPTY);
                        userDefinedOrLinked.select(ConstantsUtils.USER_DEFINED);
                        tableName.setValue(ConstantsUtils.EMPTY);
                        fieldName.setValue(ConstantsUtils.EMPTY);
                        hierarchyName.setValue(ConstantsUtils.EMPTY);
                        hierarchyType.setValue(ConstantsUtils.PRIMARY);
                        hierarchyCategory.setValue(null);
                    }

                }

                /**
                 * Called when no has been clicked.
                 */
                public void noMethod() {
                    return;
                }
            };
            notificationUtils.getConfirmationMessage(ConstantsUtils.CONFORMATION, "Are you sure you want to reset the page to default/previous values?");

        }
        LOGGER.debug("resetBtnLogic Ended");
    }

    /**
     * Gets the back button.
     *
     * @return the back button
     */
    @UiHandler("backBtn")
    public void backBtnLogic(Button.ClickEvent event) {
        LOGGER.debug("In getBackButton backButton.addClickListener Started");
        MessageBox.showPlain(Icon.QUESTION, "Confirmation", "Any unsaved information will not be saved. Do you want to proceed?", new MessageBoxListener() {
            /**
             * Called when a Button has been clicked .
             *
             */
            @SuppressWarnings("PMD")
            public void buttonClicked(final ButtonId buttonId) {
                if (buttonId.name().equals("YES")) {
                    LOGGER.debug("Entering back button method from Add");
                    getUI().getNavigator().navigateTo(HierarchyBuilderLandingView.NAME);
                    LOGGER.debug("Ending back button method from Add");
                }
            }
        }, ButtonId.YES, ButtonId.NO);
        LOGGER.debug("In getBackButton backButton.addClickListener Ended");
    }

    @UiHandler("viewBackBtn")
    public void viewBackBtnLogic(Button.ClickEvent event) {
        LOGGER.debug("In getBackButton backButton.addClickListener Started");
        getUI().getNavigator().navigateTo(HierarchyBuilderLandingView.NAME);
        LOGGER.debug("In getBackButton backButton.addClickListener Ended");
    }

    /**
     * Adds the reset button.
     *
     * @return the button
     */
    @UiHandler("resetButton")
    public void resetButtonLogic(Button.ClickEvent event) {
        LOGGER.debug("In addResetButton resetButton.addClickListener Started");
        if (event != null) {
            for (int i = 0; i < levelDTOBean.size(); i++) {
                if (levelDTOBean.getIdByIndex(i).getLevelValues() != null && StringUtils.isNotEmpty(levelDTOBean.getIdByIndex(i).getLevelValues().trim())) {
                } else {
                    MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Please Enter the values for the Level Values", ButtonId.OK);
                    return;
                }
            }
            final AbstractNotificationUtils notificationUtils = new AbstractNotificationUtils() {
                /**
                 * Called when yes has been clicked.
                 */
                @SuppressWarnings("PMD")
                public void yesMethod() {
                    addRowBtn.setVisible(true);
                    updateRowBtn.setVisible(false);
                    levelNo.setReadOnly(false);
                    levelNo.setValue(String.valueOf(hierarchyBuilderLevelDTOBean.size() + 1));
                    levelNo.setReadOnly(true);
                    levelName.setValue(ConstantsUtils.EMPTY);
                    userDefinedOrLinked.select(ConstantsUtils.USER_DEFINED);
                    tableName.setValue(ConstantsUtils.EMPTY);
                    fieldName.setValue(ConstantsUtils.EMPTY);
                    levelDefinitionTable.setValue(null);

                }

                /**
                 * Called when no has been clicked.
                 */
                public void noMethod() {
                    return;
                }
            };
            notificationUtils.getConfirmationMessage(ConstantsUtils.CONFORMATION, "Are you sure you want to reset the page to default/previous values?");

        }
        LOGGER.debug("In addResetButton resetButton.addClickListener Ended");
    }

    /**
     * Adds the update level button.
     *
     * @return the button
     */
    @UiHandler("updateRowBtn")
    public void updateRowBtnLogic(Button.ClickEvent event) {
        LOGGER.debug("In addUpdateLevelButton btnUpdateLevel.addClickListener Started");
        if (event != null) {
            hierarchyBuilderBinder.getErrorDisplay().clearError();
            final List<HierarchyBuilderLevelDTO> list = new ArrayList<>();

            List<HierarchyBuilderLevelDTO> finallist = new ArrayList<>();
            List<HierarchyBuilderLevelDTO> updatelist = hierarchyBuilderLevelDTOBean.getItemIds();
            for (HierarchyBuilderLevelDTO hierarchyBuilderLevelDTO : updatelist) {
                finallist.add(hierarchyBuilderLevelDTO);
            }
            if (finallist.contains(lvlDefDTO)) {
                finallist.remove(lvlDefDTO);
            }
            HashMap listMap = new HashMap();
            for (final HierarchyBuilderLevelDTO hblisy : finallist) {
                listMap.put(hblisy.getLevelNo(), hblisy.getLevelName());
            }
            for (int i = 0; i < hierarchyBuilderLevelDTOBean.size(); i++) {
                final HierarchyBuilderLevelDTO dto = hierarchyBuilderLevelDTOBean.getIdByIndex(i);
                if (dto.getLevelNo() == lvlDefDTO.getLevelNo()) {
                    if (levelName.getValue() != null && StringUtils.isNotEmpty(String.valueOf(levelName.getValue()))) {
                        if (listMap.containsValue(levelName.getValue().trim())) {
                            MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Entered Level Name already exists.Please enter different Level Name", ButtonId.OK);
                            return;
                        } else {
                            dto.setLevelName(String.valueOf(levelName.getValue()));
                        }

                    } else {
                        MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Please Enter Value for the Level Name", ButtonId.OK);
                        return;
                    }
                    if (userDefinedOrLinked.getValue() != null && !ConstantsUtils.EMPTY.equals(String.valueOf(userDefinedOrLinked.getValue()))) {
                        dto.setUserDefinedOrLinked(String.valueOf(userDefinedOrLinked.getValue()));

                    }
                    if (userDefinedOrLinked.getValue().equals(ConstantsUtils.LINKED)) {
                        if (tableName.getValue() != null && StringUtils.isNotEmpty(String.valueOf(tableName.getValue()))) {
                            final int levelNo = Integer.valueOf(HierarchyBuilderDefinition.this.levelNo.getValue());
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
            levelDefinitionTable.setValue(null);
            addRowBtn.setVisible(true);
            updateRowBtn.setVisible(false);
        }
        LOGGER.debug("In addUpdateLevelButton btnUpdateLevel.addClickListener Ended");
    }

    /**
     * Adds the row.
     *
     * @return the button
     */
    @UiHandler("addRowBtn")
    public void addRowBtnLogic(Button.ClickEvent event) {
        LOGGER.debug("addRowBtnLogic Started");
        if (event != null) {
            hierarchyBuilderBinder.getErrorDisplay().clearError();
            final HierarchyBuilderLevelDTO dto = new HierarchyBuilderLevelDTO();
            List<HierarchyBuilderLevelDTO> list;
            list = hierarchyBuilderLevelDTOBean.getItemIds();
            final HashMap levelNameMap = new HashMap();
            final List<String> tableList = new ArrayList<>();
            for (final HierarchyBuilderLevelDTO levelDTO : list) {
                levelNameMap.put(levelDTO.getLevelNo(), levelDTO.getLevelName());
                if (userDefinedOrLinked.getValue().equals(ConstantsUtils.LINKED)) {
                    tableList.add(levelDTO.getTableName() + "-" + levelDTO.getFieldName());
                }
            }

            if (StringUtils.isBlank(levelName.getValue())) {
                MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Please Enter Value for the Level Name", ButtonId.OK);
                return;

            } else {
                if (levelNameMap.containsValue(levelName.getValue().trim())) {
                    MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Entered Level Name already exists.Please enter different Level Name", ButtonId.OK);
                    return;
                } else {
                    dto.setLevelName(String.valueOf(levelName.getValue().trim()));
                }

            }
            if (userDefinedOrLinked.getValue() != null && StringUtils.isNotEmpty(String.valueOf(userDefinedOrLinked.getValue()))) {
                dto.setUserDefinedOrLinked(String.valueOf(userDefinedOrLinked.getValue()));
            }

            if (userDefinedOrLinked.getValue().equals(ConstantsUtils.LINKED)) {
                if (tableList.contains(tableName.getValue().toString() + "-" + fieldName.getValue().toString())) {
                    MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Selected Table/Field Name already exists.Please select different Table/Field Name", ButtonId.OK);
                    return;
                } else {
                    if (tableName.getValue() != null && StringUtils.isNotEmpty(String.valueOf(tableName.getValue()))) {
                        final int levelNo = Integer.valueOf(HierarchyBuilderDefinition.this.levelNo.getValue());
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
            }

            dto.setLevelNo(hierarchyBuilderLevelDTOBean.size() + 1);
            hierarchyBuilderLevelDTOBean.addBean(dto);
            levelNo.setReadOnly(false);
            levelNo.setValue(String.valueOf(hierarchyBuilderLevelDTOBean.size() + 1));
            levelNo.setReadOnly(true);
            levelName.setValue(StringUtils.EMPTY);
            tableName.setValue(StringUtils.EMPTY);
            fieldName.setValue(StringUtils.EMPTY);
            levelDefinitionTable.setValue(null);
        }
        LOGGER.debug("addRowBtnLogic Ended");
    }

    /**
     * Adds the button level values.
     *
     * @return the button
     */
    @UiHandler("displayAddRowBtn")
    public void displayAddRowBtnLogic(Button.ClickEvent event) {
        LOGGER.debug("displayAddRowBtn Started");
        if (event != null) {
            hierarchyBuilderBinder.getErrorDisplay().clearError();
            if (levelDTOBean == null) {
                final LevelDTO dto = new LevelDTO();
                levelDTOBean.addBean(dto);
            } else {
                final List<LevelDTO> tempList = levelDTOBean.getItemIds();
                final List<LevelDTO> list = new ArrayList<>();
                for (int i = 0; i < tempList.size(); i++) {
                    if (tempList.get(i).getLevelValues() == null && ConstantsUtils.EMPTY.equals(tempList.get(i).getLevelValues())) {
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
            }
        }
        LOGGER.debug("displayAddRowBtn Ended");
    }

    /**
     * Delete button level values.
     *
     * @return the button
     */
    @UiHandler("displayDeleteRowBtn")
    public void displayDeleteRowBtnLogic(Button.ClickEvent event) {
        LOGGER.debug("In deleteButtonLevelValues btnLevValuesDelete.addClickListener Started");
        if (levelValuesTable.getValue() == null) {
            MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Please select a row to delete", ButtonId.OK);
        } else {
            final AbstractNotificationUtils notificationUtils = new AbstractNotificationUtils() {
                /**
                 * Called when yes has been clicked.
                 */
                @SuppressWarnings("PMD")
                public void yesMethod() {
                    levelDTOBean.removeItem(levelValuesTable.getValue());
                }

                /**
                 * Called when no has been clicked.
                 */
                public void noMethod() {
                    return;
                }
            };

            notificationUtils.getConfirmationMessage("Delete Confirmation", "You are about to delete the row.  Please click Yes if you would like to continue.");
        }
        LOGGER.debug("In deleteButtonLevelValues btnLevValuesDelete.addClickListener Ended");
    }

    /**
     * Level definition list table.
     *
     * @return the filter table
     */
    public ExtFilterTable levelDefinitionListTable() {
        LOGGER.debug("levelDefinitionListTable Method started");

        resultsLayout.addComponent(levelDefinitionTable);

        resultsLayout.addComponent(resultsControlLayout);

        levelDefinitionTable.setFilterBarVisible(true);
        levelDefinitionTable.markAsDirty();
        levelDefinitionTable.setFilterDecorator(new ExtDemoFilterDecorator());
        levelDefinitionTable.setContainerDataSource(hierarchyBuilderLevelDTOBean);
        levelDefinitionTable.setVisibleColumns(CommonUIUtil.getInstance().acHbLevelDefnitionColumns);

        levelDefinitionTable.setColumnHeaders(CommonUIUtil.getInstance().acHbLevelDefnitionHeader);
        levelDefinitionTable.setPageLength(NumericConstants.SEVEN);
        levelDefinitionTable.setImmediate(true);
        levelDefinitionTable.setSelectable(true);
        levelDefinitionTable.setMultiSelect(true);
        levelDefinitionTable.setSizeFull();
        levelDefinitionTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            @SuppressWarnings("PMD")
            public void itemClick(final ItemClickEvent event) {
                LOGGER.debug("In levelDefinitionListTable levelDefinitionTable.addClickListener Started");
                if (event != null) {
                    deleteRowFlag = false;
                    displayRowFlag = false;
                    updateRowBtn.setVisible(true);
                    addRowBtn.setVisible(false);
                    lvlDefDTO = (HierarchyBuilderLevelDTO) event.getItemId();
                    if (lvlDefDTO.getLevelNo() != ConstantsUtils.ZERO_NUM) {
                        levelNo.setReadOnly(false);
                        levelNo.setValue(String.valueOf(lvlDefDTO.getLevelNo()));
                        levelNo.setReadOnly(true);

                    }
                    levelName.setValue(lvlDefDTO.getLevelName());
                    userDefinedOrLinked.select(lvlDefDTO.getUserDefinedOrLinked());
                    tableName.setValue(lvlDefDTO.getTableName());
                    fieldName.setValue(lvlDefDTO.getFieldName());

                }
                LOGGER.debug("In levelDefinitionListTable levelDefinitionTable.addClickListener Ended");
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
                LOGGER.debug("In addResultsTable ValueChange.resultsCustomerClick Started");
                final Set<HierarchyBuilderDTO> companyDetailsList = (Set<HierarchyBuilderDTO>) levelDefinitionTable.getValue();
                if (!companyDetailsList.isEmpty()) {
                    updateRowBtn.setVisible(true);
                    addRowBtn.setVisible(false);
                } else {
                    updateRowBtn.setVisible(false);
                    addRowBtn.setVisible(true);
                    levelNo.setReadOnly(false);
                    levelNo.setValue(String.valueOf(hierarchyBuilderLevelDTOBean.size() + 1));
                    levelNo.setReadOnly(true);
                    levelName.setValue(ConstantsUtils.EMPTY);
                    userDefinedOrLinked.select(ConstantsUtils.USER_DEFINED);
                    tableName.setValue(ConstantsUtils.EMPTY);
                    fieldName.setValue(ConstantsUtils.EMPTY);
                    levelDefinitionTable.setValue(null);
                }
                LOGGER.debug("In addResultsTable ValueChange.resultsCustomerClick Ended");
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
        LOGGER.debug("levelDefinitionListTable Method retuns levelDefinitionTable");
        return levelDefinitionTable;
    }

    @UiHandler("deleteRowBtn")
    public void deleteRowBtnLogic(Button.ClickEvent event) {
        LOGGER.debug("deleteRowBtnLogic Started");
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
                        levelDefinitionTable.setVisibleColumns(CommonUIUtil.getInstance().acHbLevelDefnitionColumns);
                        levelDefinitionTable.setColumnHeaders(CommonUIUtil.getInstance().acHbLevelDefnitionHeader);
                        levelNo.setReadOnly(false);
                        levelNo.setValue(String.valueOf(hierarchyBuilderLevelDTOBean.size() + 1));
                        levelNo.setReadOnly(true);
                        addRowBtn.setVisible(true);
                        updateRowBtn.setVisible(false);
                        levelName.setValue(ConstantsUtils.EMPTY);
                        tableName.setValue(ConstantsUtils.EMPTY);
                        fieldName.setValue(ConstantsUtils.EMPTY);

                    }

                    /**
                     * Called when no has been clicked .
                     */
                    public void noMethod() {
                        return;
                    }
                };
                notificationUtils.getConfirmationMessage("Delete Confirmation", "You are about to delete the row.  Please click Yes if you would like to continue.");
              
            }
        } else {
            MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "No rows available to delete", ButtonId.OK);
        }
        LOGGER.debug("deleteRowBtnLogic Ended");
    }

    /**
     * Level values list table.
     *
     * @return the table
     */
    public Table levelValuesListTable() {
        LOGGER.debug("levelValuesListTable Method started");

        levelResultsLayout.addComponent(levelValuesTable);
        
        levelResultsLayout.addComponent(levelValuesPagedTable);
        ResponsiveUtils.getResponsiveControls(tableLogic.createControls(), levelResultsControlLayout);
        levelResultsLayout.addComponent(levelResultsControlLayout);
        tableLogic.setContainerDataSource(tableFieldLookUpDTOBean);
        levelValuesPagedTable.setSelectable(true);
        tableLogic.setPageLength(NumericConstants.SEVEN);
        levelValuesPagedTable.setCaption("Results");
        levelValuesPagedTable.setVisibleColumns(CommonUIUtil.getInstance().acHbLevelValuesColumns);
        levelValuesPagedTable.setColumnHeaders(CommonUIUtil.getInstance().acHbLevelValuesHeader);
        levelValuesPagedTable.setFilterBarVisible(true);
        levelValuesPagedTable.setSizeFull();
        levelValuesPagedTable.setImmediate(true);
        levelValuesPagedTable.setPageLength(NumericConstants.SEVEN);
        levelValuesPagedTable.setFilterDecorator(new ExtDemoFilterDecorator());
        levelValuesPagedTable.addStyleName("filtertable");
        levelValuesPagedTable.addStyleName("table-header-normal");
        levelValuesPagedTable.setWidth("350px");

        levelValuesTable.markAsDirty();
        levelValuesTable.setContainerDataSource(levelDTOBean);
        levelValuesTable.setVisibleColumns(CommonUIUtil.getInstance().acHbLevelValuesColumns);
        levelValuesTable.setCaption("Results");
        levelValuesTable.setColumnHeaders(CommonUIUtil.getInstance().acHbLevelValuesHeader);

        levelValuesTable.setPageLength(NumericConstants.SEVEN);
        levelValuesTable.setImmediate(true);
        levelValuesTable.setSelectable(true);
        levelValuesTable.setWidth("350px");
        levelValuesTable.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs.
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                return;
            }
        });
        LOGGER.debug("levelValuesListTable Method returns levelValuesTable");
        return levelValuesTable;
    }

    public void saveButtonLogic(final ClickEvent event, final boolean flag) {
        try {
            List<HierarchyBuilderLevelDTO> levelvalues;
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
            } 
            else {
                if (event != null) {
                    hierarchyBuilderBinder.getErrorDisplay().clearError();
                    hierarchyBuilderBinder.commit();
                    final List<HierarchyBuilderDTO> list = logic.getHierachyDefinitionList();
                    for (int i = 0; i < list.size(); i++) {
                        HierarchyBuilderDTO ob = list.get(i);
                        if (ob.getHierarchyName().equals(hierarchyName.getValue().trim())) {
                            MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Hierarchy Name already exists! Please enter a different Hierarchy Name.", ButtonId.OK);
                            return;
                        }
                    }
                    if (hierarchyBuilderId != null && hierarchyBuilderId.getUserDefinedOrLinked().equals(ConstantsUtils.USER_DEFINED)) {

                        for (int i = 0; i < levelDTOBean.size(); i++) {
                            if (levelDTOBean.getIdByIndex(i).getLevelValues() != null && StringUtils.isNotEmpty(levelDTOBean.getIdByIndex(i).getLevelValues().trim())) {
                            } else {
                                MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Please Enter the values for the Level Values.", ButtonId.OK);
                                return;
                            }
                        }
                        new HierarchyBuilderLogic().saveTempData(hierarchyBuilderId.getLevelNo(), hierarchyBuilderId.getLevelName(), levelDTOBean);

                    }
                    for (int i = 0; i < hierarchyBuilderLevelDTOBean.size(); i++) {
                        if (hierarchyBuilderLevelDTOBean.getIdByIndex(i).getUserDefinedOrLinked().equals(ConstantsUtils.USER_DEFINED)) {
                            int count = 0;
                            count = logic.levelCheckCount(hierarchyBuilderLevelDTOBean.getIdByIndex(i).getLevelNo());
                            if (count > ConstantsUtils.ZERO_NUM) {
                            } else {
                                MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Please Enter atleast one level value for the Level No "
                                        + hierarchyBuilderLevelDTOBean.getIdByIndex(i).getLevelNo(), ButtonId.OK);
                                return;
                            }

                        }
                    }
                    final String selectedHierarchy = (String) hierarchy.getValue();
                    final String hierType = (String) hierarchyType.getValue();
                    if (selectedHierarchy != null && selectedHierarchy.equals(ConstantsUtils.DATA_SELECTION) && ConstantsUtils.PRIMARY.equals(hierType)) {
                        if (!levelvalues.isEmpty() && levelvalues.size() < NumericConstants.FOUR) {
                            MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "A minimum of four levels is required when the Hierarchy Category is Data Selection", ButtonId.OK);
                            return;
                        }

                    } else if (!levelvalues.isEmpty() && levelvalues.size() < NumericConstants.TWO) {
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
                                            getUI().getNavigator().navigateTo(HierarchyBuilderLandingView.NAME);
                                            final Notification notif = new Notification(
                                                    ConstantsUtils.HIERARCHY + hierarchyBuilderBinder.getField(ConstantsUtils.HIERARCHY_NAME).getValue() + "Saved successfully",
                                                    Notification.Type.HUMANIZED_MESSAGE);

                                            notif.setDelayMsec(NumericConstants.TWO_THOUSAND);
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
                                    } catch (Exception ex) {
                                        AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4002));
                                        LOGGER.error(ex);
                                    }

                                }
                            }
                        }, ButtonId.YES, ButtonId.NO);
                    } else {
                        logic.saveHierarchy(hierarchyBuilderBinder, hierarchyBuilderLevelDTOBean, sessionDTO);
                    }
                }
            }
            LOGGER.debug("In addSaveButton btnSave.addClickListener Ended");
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

    public void updateButtonLogic(final ClickEvent event, final boolean flag) {

        try {

            int systemId = sessionDTO.getSystemId();
            if (systemId != 0) {
                final DynamicQuery levelValuesDynamicQuery = DynamicQueryFactoryUtil.forClass(RelationshipBuilder.class);
                levelValuesDynamicQuery.add(RestrictionsFactoryUtil.eq("hierarchyDefinitionSid", systemId));
                List<RelationshipBuilder> levelValuesList = RelationshipBuilderLocalServiceUtil.dynamicQuery(levelValuesDynamicQuery);
                if (levelValuesList != null && levelValuesList.size() > 0) {
                    MessageBox.showPlain(Icon.ERROR, "Edit", "Cannot Edit the Hierarchy which is already associated with existing Relationship", ButtonId.OK);
                    return;
                }
            }

            List<HierarchyBuilderLevelDTO> levelvalues;
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

                        if (!levelvalues.isEmpty() && levelvalues.size() < NumericConstants.FOUR) {
                            MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "A minimum of four levels is required when the Hierarchy Category is Data Selection", ButtonId.OK);
                            return;
                        }

                    } else if (levelvalues.size() < ConstantsUtils.TWO_NUM) {
                        MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "A minimum of two levels is required", ButtonId.OK);
                        return;
                    }
                    if (flag) {
                        MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION, "Update record " + hierarchyName.getValue() + " ?", new MessageBoxListener() {
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

                                            notif.setDelayMsec(NumericConstants.TWO_THOUSAND);
                                            notif.setPosition(Position.BOTTOM_CENTER);
                                            notif.show(Page.getCurrent());

                                        } else {
                                            hierarchyBuilderBinder.getErrorDisplay().setError(ConstantsUtils.ERROR_OCCURED);
                                        }
                                    } catch (SystemException ex) {
                                        final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                                        AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                                        LOGGER.error(ex);
                                    } catch (Exception ex) {
                                        AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4002));
                                        LOGGER.error(ex);
                                    }

                                }
                            }
                        }, ButtonId.YES, ButtonId.NO);
                    } else {
                        logic.saveHierarchy(hierarchyBuilderBinder, hierarchyBuilderLevelDTOBean, sessionDTO);
                    }

                }
            }
            LOGGER.debug("In addUpdateButton btnUpdate.addClickListener Ended");
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
     * Gets the bean from id.
     *
     * @param obj the obj
     * @return the bean from id
     */
    public HierarchyBuilderLevelDTO getBeanFromId(final Object obj) {
        BeanItem<?> targetItem = null;
        if (obj instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) obj;
        } else if (obj instanceof HierarchyBuilderLevelDTO) {
            targetItem = new BeanItem<>((HierarchyBuilderLevelDTO) obj);
        }
        return (HierarchyBuilderLevelDTO) targetItem.getBean();
    }

    public void disableComponentsOnViewMode() {
        hierarchyName.setReadOnly(true);
        hierarchyType.setReadOnly(true);
        hierarchyCategory.setReadOnly(true);
        hLayout1.setVisible(false);
        hLayout2.setVisible(false);
        excelExportLevels.setVisible(false);
        viewBackBtn.setVisible(true);
        displayAddRowBtn.setVisible(false);
        displayDeleteRowBtn.setVisible(false);
        backBtn.setVisible(false);
        saveBtn.setVisible(false);
        resetBtn.setVisible(false);
        deleteRowBtn.setVisible(false);
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
