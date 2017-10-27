/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.hierarchybuilder.ui.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.adminconsole.util.StringConstantUtils;
import com.stpl.app.adminconsole.common.dto.SessionDTO;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import com.stpl.app.adminconsole.common.util.CommonUIUtil;
import com.stpl.app.adminconsole.common.util.CommonUtil;
import com.stpl.app.adminconsole.hierarchybuilder.dto.HierarchyBuilderDTO;
import com.stpl.app.adminconsole.hierarchybuilder.logic.HierarchyBuilderLogic;
import com.stpl.app.adminconsole.hierarchybuilder.ui.view.HierarchyBuilderAdd;
import com.stpl.app.adminconsole.hierarchybuilder.ui.view.HierarchyBuilderEdit;
import com.stpl.app.adminconsole.hierarchybuilder.ui.view.HierarchyBuilderView;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.ErrorCodeUtil;
import com.stpl.app.adminconsole.util.ErrorCodes;
import com.stpl.app.adminconsole.util.ResponsiveUtils;
import com.stpl.app.adminconsole.util.ValidationUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.Validator;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedFilterTable;

// TODO: Auto-generated Javadoc
/**
 * The Class HierarchyBuilderIndex.
 *
 * @author nisanthan
 */
public final class HierarchyBuilderIndex extends CustomComponent implements View {

    /**
     * The space.
     */
    private final Label space = new Label("&nbsp;", ContentMode.HTML);
    /**
     * The Constant NAME.
     */
    public static final String NAME = ConstantsUtils.EMPTY;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(HierarchyBuilderIndex.class);
    /**
     * The Constant NULLOBJECT.
     */
    private static final BeanItem<?> NULLOBJECT = null;
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
    public HierarchyBuilderDTO hierarchyBuilderDTO;
    /**
     * The hierarchy name.
     */
    private final TextField hierarchyName = new TextField();
    /**
     * The level values.
     */
    private final TextField levelValues = new TextField();
    /**
     * The level name.
     */
    private final TextField levelName = new TextField();
    /**
     * The hierarchy definition system id.
     */
    private final TextField hierarchyDefinitionSystemId = new TextField();
    /**
     * The created date from.
     */
    private final PopupDateField createdDateFrom = new PopupDateField();
    /**
     * The created date to.
     */
    private final PopupDateField createdDateTo = new PopupDateField();
    /**
     * The hierarchy type.
     */
    private final OptionGroup hierarchyType = new OptionGroup();
    /**
     * The excel export.
     */
    private final Button excelExport = new Button();
    /**
     * The result table.
     */
    private ExtPagedFilterTable resultFilterTable = new ExtPagedFilterTable();
    /**
     * The hierarchy builder dto bean.
     */
    private BeanItemContainer<HierarchyBuilderDTO> hierarchyBuilderDTOBean = new BeanItemContainer<>(HierarchyBuilderDTO.class);
    /**
     * The btn reset.
     */
    private final Button btnReset = new Button("RESET");
    /**
     * The btn add.
     */
    private final Button btnAdd = new Button("ADD");
    /**
     * The btn edit.
     */
    private final Button btnEdit = new Button("EDIT");
    /**
     * The btn view.
     */
    private final Button btnView = new Button("VIEW");
    /**
     * The btn delete.
     */
    private final Button btnDelete = new Button("DELETE");
    /**
     * The hierarchy category.
     */
    private ComboBox hierarchyCategory = new ComboBox();
    /**
     * The hierarchybuilderlogic.
     */
    private final HierarchyBuilderLogic hierarchybuilderlogic;
    /**
     * The version Number.
     */
    public int version;
    /**
     * searchCriteria
     */
    private String searchCriteria = ConstantsUtils.EMPTY;
    /**
     * The hierarchy value.
     */
    private Object hierarchyValue;
    /**
     *
     */
    public String heirarchyName;

    SessionDTO sessionDTO;

    /**
     * Gets the version.
     *
     * @return the version
     */
    public int getVersion() {
        return version;
    }

    /**
     * Sets the version.
     *
     * @param version the new version
     */
    public void setVersion(final int version) {
        this.version = version;
    }

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

    /**
     * Sets the hierarchy builder dto.
     *
     * @param hierarchyBuilderDTO the hierarchy builder dto
     */
    public void setHierarchyBuilderDTO(final HierarchyBuilderDTO hierarchyBuilderDTO) {
        this.hierarchyBuilderDTO = hierarchyBuilderDTO;
    }

    /**
     * Gets the space.
     *
     * @return the space
     */
    public Label getSpace() {
        return space;
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
     * Gets the hierarchy name.
     *
     * @return the hierarchy name
     */
    public TextField getHierarchyName() {
        return hierarchyName;
    }

    /**
     * Gets the level values.
     *
     * @return the level values
     */
    public TextField getLevelValues() {
        return levelValues;
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
     * Gets the hierarchy definition system id.
     *
     * @return the hierarchy definition system id
     */
    public TextField getHierarchyDefinitionSystemId() {
        return hierarchyDefinitionSystemId;
    }

    /**
     * Gets the created date from.
     *
     * @return the created date from
     */
    public PopupDateField getCreatedDateFrom() {
        return createdDateFrom;
    }

    /**
     * Gets the created date to.
     *
     * @return the created date to
     */
    public PopupDateField getCreatedDateTo() {
        return createdDateTo;
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
     * Gets the excel export.
     *
     * @return the excel export
     */
    public Button getExcelExport() {
        return excelExport;
    }

    /**
     * Gets the result filter table.
     *
     * @return the result filter table
     */
    public ExtPagedFilterTable getResultFilterTable() {
        return resultFilterTable;
    }

    /**
     * Gets the hierarchy builder dto bean.
     *
     * @return the hierarchy builder dto bean
     */
    public BeanItemContainer<HierarchyBuilderDTO> getHierarchyBuilderDTOBean() {
        return hierarchyBuilderDTOBean;
    }

    /**
     * Sets the hierarchy builder dto bean.
     *
     * @param hierarchyBuilderDTOBean the new hierarchy builder dto bean
     */
    public void setHierarchyBuilderDTOBean(final BeanItemContainer<HierarchyBuilderDTO> hierarchyBuilderDTOBean) {
        this.hierarchyBuilderDTOBean = hierarchyBuilderDTOBean;
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
     * Gets the btn add.
     *
     * @return the btn add
     */
    public Button getBtnAdd() {
        return btnAdd;
    }

    /**
     * Gets the btn edit.
     *
     * @return the btn edit
     */
    public Button getBtnEdit() {
        return btnEdit;
    }

    /**
     * Gets the btn view.
     *
     * @return the btn view
     */
    public Button getBtnView() {
        return btnView;
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
     * Gets the hierarchybuilderlogic.
     *
     * @return the hierarchybuilderlogic
     */
    public HierarchyBuilderLogic getHierarchybuilderlogic() {
        return hierarchybuilderlogic;
    }

    public ComboBox getHierarchyCategory() {
        return hierarchyCategory;
    }

    /**
     * Instantiates a new hierarchy builder index.
     *
     * @param hierarchyBuilderBinder the hierarchy builder binder
     * @param hierarchyBuilderDTO the hierarchy builder dto
     * @param hierarchyBuilderDTOBean the hierarchy builder dto bean
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public HierarchyBuilderIndex(final CustomFieldGroup hierarchyBuilderBinder, final HierarchyBuilderDTO hierarchyBuilderDTO, final SessionDTO sessionDTO) throws SystemException {
        super();
        LOGGER.debug("HierarchyBuilderIndex constructor started");
        this.hierarchyBuilderBinder = hierarchyBuilderBinder;
        this.hierarchyBuilderDTO = hierarchyBuilderDTO;
        this.sessionDTO = sessionDTO;
        hierarchybuilderlogic = new HierarchyBuilderLogic(sessionDTO);
        init();
        addResponsiveSearchTableCollapse(this.resultFilterTable);
        LOGGER.debug("HierarchyBuilderIndex constructor Ended");
    }

    /**
     * Inits the.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public void init() throws SystemException {
        LOGGER.debug("init method started");
        space.setHeight("20");
        addToContent();
        this.hierarchyBuilderBinder = getBinder();
        configureFields();
        LOGGER.debug("Init method Ended");
    }

    /**
     * Gets the binder.
     *
     * @return the binder
     */
    private CustomFieldGroup getBinder() {
        LOGGER.debug("getBinder method started");
        hierarchyBuilderBinder.bindMemberFields(this);
        hierarchyBuilderBinder.setItemDataSource(new BeanItem<>(new HierarchyBuilderDTO()));
        hierarchyBuilderBinder.setBuffered(true);
        hierarchyBuilderBinder.setErrorDisplay(errorMsg);
        LOGGER.debug("getBinder method returns getBinder");
        return hierarchyBuilderBinder;
    }

    /**
     * Adds the to content.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    private void addToContent() {
        LOGGER.debug("addToContent method started");
        space.setHeight("20");
        final VerticalLayout content = new VerticalLayout();
        content.addComponent(errorMsg);
        errorMsg.setStyleName("search-criteria-error-message");
        final Panel searchpanel = new Panel("Hierarchy Search Criteria");
        final Panel resultPanel = new Panel("Results");
        VerticalLayout layout = new VerticalLayout();

        layout.setSpacing(true);
        layout.setMargin(true);
        layout.addComponent(searchSection());
        layout.addComponent(addButtons());
        searchpanel.setContent(layout);
        content.addComponent(searchpanel);
        content.addComponent(ResponsiveUtils.addNaviButton(resultFilterTable));
        resultPanel.setContent(resultTable());
        content.addComponent(resultPanel);
        content.addComponent(ResponsiveUtils.getResponsiveControls(resultFilterTable.createControls()));
        content.addComponent(crudButtons());
        content.setStyleName("bootstrap-search-criteria");
        setCompositionRoot(content);
        LOGGER.debug("addToContent method Ended");
    }

    /**
     * Configure fields.
     */
    private void configureFields() throws SystemException {
        LOGGER.debug("configureFields method started");
        createdDateFrom.setDescription(ConstantsUtils.DATE_DES);
        createdDateTo.setDescription(ConstantsUtils.DATE_DES);
        excelExport.setDescription(ConstantsUtils.EXCEL_EXPORT);
        excelExport.setIconAlternateText(ConstantsUtils.EXCEL_EXPORT);
        hierarchyName.focus();
        btnReset.setEnabled(true);
        btnEdit.setEnabled(true);
        btnEdit.setImmediate(true);
        btnDelete.setImmediate(true);
        btnView.setImmediate(true);
        btnDelete.setEnabled(true);
        hierarchyType.setImmediate(true);
        hierarchyType.addItem(ConstantsUtils.PRIMARY);
        hierarchyType.addItem(ConstantsUtils.SECONDARY);
        hierarchyType.select(ConstantsUtils.PRIMARY);
        hierarchyName.focus();
        hierarchyName.setMaxLength(NumericConstants.FIFTY);
        hierarchyName.setImmediate(true);

        levelValues.addValidator(new RegexpValidator(ValidationUtils.ALPHA_NUMERIC, "level Values can contain only Alphabets and Numbers"));
        levelName.addValidator(new RegexpValidator(ValidationUtils.ALPHA_NUMERIC, "Level Name can contain only Alphabets and Numbers"));
        createdDateFrom.setDateFormat("MM/dd/yyyy");
        createdDateFrom.setValidationVisible(true);
        createdDateTo.setDateFormat("MM/dd/yyyy");
        createdDateTo.setValidationVisible(true);
        createdDateFrom.setImmediate(true);
        createdDateTo.setImmediate(true);

        hierarchyName.setImmediate(true);

        levelName.setMaxLength(NumericConstants.THIRTY_EIGHT);
        levelValues.setMaxLength(NumericConstants.THIRTY_EIGHT);
        levelValues.setImmediate(true);
        levelValues.setValidationVisible(true);
        levelName.setImmediate(true);
        levelName.setValidationVisible(true);

        hierarchyCategory = CommonUtil.getComboBox(hierarchyCategory, CommonUIUtil.HIERARCHY_CATEGORY);

        hierarchyCategory.setImmediate(true);
        VaadinSession.getCurrent().setAttribute(ConstantsUtils.SELECT_HIRARCHY_CATAGORY, 0);
        hierarchyCategory.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * This method listens to data source value changes and passes the
             * changes forwards.
             */
            @SuppressWarnings("PMD")
            public void valueChange(final Property.ValueChangeEvent event) {
                LOGGER.debug("In configureFields hierarchyCategory.addValueChangeListener Started");
                if (hierarchyCategory.getValue() == null) {
                    final String nullValue = null;
                    hierarchyValue = nullValue;
                    VaadinSession.getCurrent().setAttribute(ConstantsUtils.SELECT_HIRARCHY_CATAGORY, 0);
                } else {
                    hierarchyValue = ((HelperDTO) hierarchyCategory.getValue()).getId();
                    final int selectedHierarchySysId = Integer.valueOf(hierarchyValue.toString());
                    VaadinSession.getCurrent().setAttribute(ConstantsUtils.SELECT_HIRARCHY_CATAGORY, selectedHierarchySysId);

                }
                LOGGER.debug("In configureFields hierarchyCategory.addValueChangeListener Ended");
            }
        });
        btnReset.addClickListener(new ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("In configureFields btnReset.addClickListener Started");
                final AbstractNotificationUtils notificationUtils = new AbstractNotificationUtils() {
                    /**
                     * Called when a Button has been clicked.
                     */
                    @SuppressWarnings("PMD")
                    public void yesMethod() {
                        List<Object> collapsedColumns = new ArrayList<>();
                        for (Object item : resultFilterTable.getVisibleColumns()) {
                            if (resultFilterTable.isColumnCollapsed(item)) {

                                collapsedColumns.add(item);
                            }
                        }
                        hierarchyBuilderDTOBean.removeAllItems();

                        // This is used to reset paged filter table no of page to 1
                        resultFilterTable.firePagedChangedEvent();

                        hierarchyName.setValue(ConstantsUtils.EMPTY);
                        levelValues.setValue(ConstantsUtils.EMPTY);
                        levelName.setValue(ConstantsUtils.EMPTY);
                        hierarchyType.select(ConstantsUtils.PRIMARY);
                        createdDateFrom.setValue(null);
                        createdDateTo.setValue(null);
                        for (Object propertyId : collapsedColumns) {
                            resultFilterTable.setColumnCollapsed(propertyId, true);
                        }
                    }

                    /**
                     * Called when a Button has been clicked.
                     */
                    public void noMethod() {
                        return;
                    }
                };
                notificationUtils.getConfirmationMessage(ConstantsUtils.CONFORMATION, "Are you sure you want to reset the page to default/previous values?");
                LOGGER.debug("In configureFields btnReset.addClickListener Ended");
            }
        });

        btnAdd.addClickListener(new ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("In configureFields btnAdd.addClickListener Started");
                getUI().getNavigator().navigateTo(HierarchyBuilderAdd.NAME);
                LOGGER.debug("In configureFields btnAdd.addClickListener Ended");
            }
        });

        btnEdit.addClickListener(new Button.ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.debug("In configureFields btnEdit.addClickListener Started");
                if (hierarchyDefinitionSystemId.getValue() == null || ConstantsUtils.EMPTY.equals(hierarchyDefinitionSystemId.getValue())
                        || ConstantsUtils.ZERO.equals(hierarchyDefinitionSystemId.getValue())) {

                    MessageBox.showPlain(Icon.INFO, "Edit", StringConstantUtils.NO_HIERARCHY_DEFINITION_HAS_BEEN_SELECTED, ButtonId.OK);

                } else {
                    try {
                        final int systemId = Integer.parseInt(hierarchyDefinitionSystemId.getValue().replaceAll(ConstantsUtils.COMMA, ConstantsUtils.EMPTY));
                        final int latestVersion = hierarchybuilderlogic.getExistingVersion(systemId);
                        if (latestVersion == version) {
                            VaadinSession.getCurrent().setAttribute(ConstantsUtils.SYS_ID,
                                    Integer.parseInt(hierarchyDefinitionSystemId.getValue().replaceAll(ConstantsUtils.COMMA, ConstantsUtils.EMPTY)));
                            VaadinSession.getCurrent().setAttribute(ConstantsUtils.FRONT_VIEW_PAGE, "Yes");
                            getUI().getNavigator().navigateTo(HierarchyBuilderEdit.NAME);
                        } else {
                            MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Please select latest hierarchy  from the Results section to Edit.", ButtonId.OK);
                        }
                    } catch (SystemException ex) {
                        LOGGER.error(ex);
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
                LOGGER.debug("In configureFields btnEdit.addClickListener Ended");
            }
        });

        excelExport.setIcon(new ThemeResource("../../icons/excel.png"));
        excelExport.setStyleName("link");
        excelExport.addClickListener(new ClickListener() {
            /**
             * Called when icon has been clicked.
             */
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("In configureFields excelExport.addClickListener Started");
                ExcelExport excel = new ExcelExport(new ExtCustomTableHolder(resultFilterTable), "Hieararchy Builder Search Results", "Hieararchy Builder", "Hieararchy Builder Search Results.xls", false);
                excel.export();
                LOGGER.debug("In configureFields excelExport.addClickListener Ended");
            }
        });

        btnView.addClickListener(new Button.ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.debug("In configureFields btnView.addClickListener Started");

                if (hierarchyDefinitionSystemId.getValue() == null || ConstantsUtils.EMPTY.equals(hierarchyDefinitionSystemId.getValue())
                        || ConstantsUtils.ZERO.equals(hierarchyDefinitionSystemId.getValue())) {
                    MessageBox.showPlain(Icon.INFO, "View", StringConstantUtils.NO_HIERARCHY_DEFINITION_HAS_BEEN_SELECTED, ButtonId.OK);
                } else {
                    try {
                        VaadinSession.getCurrent().setAttribute(ConstantsUtils.SYS_ID,
                                Integer.parseInt(hierarchyDefinitionSystemId.getValue().toString().replaceAll(ConstantsUtils.COMMA, ConstantsUtils.EMPTY)));
                        VaadinSession.getCurrent().setAttribute(ConstantsUtils.FRONT_VIEW_PAGE, "Yes");

                        VaadinSession.getCurrent().setAttribute(ConstantsUtils.VERSION_NO, version);
                        getUI().getNavigator().navigateTo(HierarchyBuilderView.NAME);
                    } catch (Exception ex) {
                        AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4009));
                        LOGGER.error(ex);
                    }
                }
                LOGGER.debug("In configureFields btnView.addClickListener Ended");
            }
        });

        btnDelete.addClickListener(new Button.ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.debug("In configureFields btnDelete.addClickListener Started");
                deleteButtonClickLogic();
                LOGGER.debug("In configureFields btnDelete.addClickListener Ended");

            }
        });
        createdDateTo.addValidator(new DateValidator("Created Date From should be before Created Date To"));
        LOGGER.debug("configureFields method Ended");
    }

    /**
     * Adds the search buttons.
     *
     * @return the horizontal layout
     * @throws Exception the exception
     */
    private HorizontalLayout addButtons() {
        LOGGER.debug("addButtons method Started");
        final HorizontalLayout searchButtons = new HorizontalLayout();
        searchButtons.setStyleName(StringConstantUtils.RESPONSIVE_GRID);
        searchButtons.addComponent(addSearchButton());
        searchButtons.addComponent(addAuditSearchButton());
        searchButtons.setSpacing(true);
        LOGGER.debug("addButtons method Ended");
        return searchButtons;
    }

    /**
     * Adds the search button.
     *
     * @return the button
     */
    public Button addSearchButton() {
        LOGGER.debug("addSearchButton method Started");
        final Button btnSearch = new Button("SEARCH");
        btnSearch.addClickListener(new Button.ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                if (event != null) {
                    LOGGER.debug("In addSearchButton btnSearch.addClickListener Started");

                    hierarchyBuilderDTOBean.removeAllItems();

                    if (!StringUtils.isBlank(hierarchyName.getValue()) || (!StringUtils.isBlank(levelName.getValue()))
                            || (!StringUtils.isBlank(levelValues.getValue()))
                            || (createdDateFrom.getValue() != null) || (createdDateTo.getValue() != null)
                            || ((hierarchyCategory.getValue() != null) && !String.valueOf(hierarchyCategory.getValue()).equals(ConstantsUtils.SELECT_ONE))) {
                        searchButtonClickLogic(ConstantsUtils.SEARCH);
                    } else {
                        MessageBox.showPlain(Icon.INFO, "Search", "Please enter at least one value/selection to search upon.", ButtonId.OK);
                    }

                    LOGGER.debug("In addSearchButton btnSearch.addClickListener Ended");
                }
            }
        });
        LOGGER.debug("addSearchButton method Ended");
        return btnSearch;
    }

    /**
     * Adds the audit search button.
     *
     * @return the button
     */
    public Button addAuditSearchButton() {
        LOGGER.debug("addAuditSearchButton method Started");
        final Button auditSearch = new Button("AUDIT SEARCH");
        auditSearch.addClickListener(new Button.ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                if (event != null) {
                    LOGGER.debug("In addAuditSearchButton auditSearch.addClickListener Started");

                    if (!StringUtils.isBlank(hierarchyName.getValue()) || !StringUtils.isBlank(levelName.getValue()) || !StringUtils.isBlank(levelValues.getValue())
                            || createdDateFrom.getValue() != null || createdDateTo.getValue() != null
                            || ((hierarchyCategory.getValue() != null) && !String.valueOf(hierarchyCategory.getValue()).equals(ConstantsUtils.SELECT_ONE))) {
                        searchButtonClickLogic(ConstantsUtils.AUDIT_SEARCH);
                    } else {
                        MessageBox.showPlain(Icon.INFO, "Search", "Please enter at least one value/selection to search upon.", ButtonId.OK);
                    }

                    LOGGER.debug("In addAuditSearchButton auditSearch.addClickListener Ended");
                }
            }
        });
        LOGGER.debug("addAuditSearchButton method Ended");
        return auditSearch;
    }

    /**
     * Search button click logic.
     *
     * @param event the event
     * @param flag the flag
     */
    protected void searchButtonClickLogic(final String flag) {
        LOGGER.debug("searchButtonClickLogic Method started");

        try {
            hierarchyBuilderBinder.getErrorDisplay().clearError();
            hierarchyBuilderBinder.getFields();
            hierarchyBuilderBinder.commit();
            hierarchyBuilderDTOBean.removeAllItems();
            searchCriteria = flag;
            List<HierarchyBuilderDTO> searchList = new ArrayList<>();

            if (searchList.size() > ConstantsUtils.ZERO_NUM) {
                hierarchyBuilderDTOBean.addAll(searchList);
                resultFilterTable.firePagedChangedEvent();
                resultFilterTable.setPageLength(resultFilterTable.getPageLength() + 1);
                resultFilterTable.setPageLength(resultFilterTable.getPageLength() - 1);
                enableActionButton();
                CommonUIUtils.getMessageNotification("Search Completed");
            } else {
                MessageBox.showPlain(Icon.ERROR, "No Matching Records", "There were no records matching the search Criteria. Please try Again", ButtonId.OK);
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
        }

        LOGGER.debug("searchButtonClickLogic Method Ended");
    }


    /**
     * Search section.
     *
     * @return the panel
     */
    private HorizontalLayout searchSection() {
        LOGGER.debug("searchSection method Started");
        final HorizontalLayout hlayout1 = new HorizontalLayout();
        hlayout1.setStyleName(StringConstantUtils.RESPONSIVE_GRID);
        hlayout1.addStyleName("minWidth50px");
        final CssLayout cssLayout = new CssLayout();
        cssLayout.setSizeFull();
        cssLayout.addComponent(new Label("Hierarchy Name:"));
        cssLayout.addComponent(hierarchyName);

        cssLayout.addComponent(new Label("Hierarchy Type:"));
        cssLayout.addComponent(hierarchyType);
        hierarchyType.setStyleName("horizontal");

        cssLayout.addComponent(new Label("Level Name:"));
        cssLayout.addComponent(levelName);

        cssLayout.addComponent(new Label("Level Value:"));
        cssLayout.addComponent(levelValues);

        cssLayout.addComponent(new Label("Creation Date From:"));
        cssLayout.addComponent(createdDateFrom);

        Label field = new Label("Creation Date To:");
        field.addStyleName("lableright");
        field.setWidth("187px");
        cssLayout.addComponent(field);
        cssLayout.addComponent(createdDateTo);
        cssLayout.addComponent(new Label("Hierarchy Category:"));
        cssLayout.addComponent(hierarchyCategory);

        hlayout1.addComponent(cssLayout);
        LOGGER.debug("searchSection method returns panel");
        return hlayout1;
    }

    /**
     * Result table.
     *
     * @return the filter table
     */
    public ExtFilterTable resultTable() {
        LOGGER.debug("resultTable method Started");
        resultFilterTable.markAsDirty();
        resultFilterTable.setFilterBarVisible(true);
        resultFilterTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultFilterTable.setContainerDataSource(hierarchyBuilderDTOBean);
        resultFilterTable.setVisibleColumns(CommonUIUtil.getInstance().acHbSearchResultColumns);
        resultFilterTable.setColumnHeaders(CommonUIUtil.getInstance().acHbSearchResultHeader);
        resultFilterTable.setPageLength(NumericConstants.FIVE);
        resultFilterTable.sinkItemPerPageWithPageLength(false);
        resultFilterTable.setImmediate(true);
        resultFilterTable.setSelectable(true);
        resultFilterTable.setSizeFull();

        resultFilterTable.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Notifies this listener that the Property's value has changed.
             *
             *
             */
            @SuppressWarnings("PMD")
            public void valueChange(final Property.ValueChangeEvent event) {
                LOGGER.debug("In addResultsTable ValueChange.resultsCustomerClick Started");
                resultsCustomerClick(event.getProperty().getValue());
                LOGGER.debug("In addResultsTable ValueChange.resultsCustomerClick Ended");
            }
        });

        resultFilterTable.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs.
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                return;
            }
        });
        LOGGER.debug("resultTable method returns resultTable");
        return resultFilterTable;
    }


    /**
     * Crud buttons.
     *
     * @return the grid layout
     */
    private HorizontalLayout crudButtons() {
        LOGGER.debug("crudButtons method Started");
        final HorizontalLayout hLayout = new HorizontalLayout();
        hLayout.setStyleName(StringConstantUtils.RESPONSIVE_GRID);
        hLayout.setSpacing(true);
        hLayout.addComponent(btnReset);
        hLayout.addComponent(btnAdd);
        hLayout.addComponent(btnEdit);
        hLayout.addComponent(btnView);
        hLayout.addComponent(btnDelete);
        hLayout.addComponent(excelExport);
        LOGGER.debug("crudButtons method returns gridlayout");
        return hLayout;
    }

    /**
     * Results item click.
     *
     * @param event the event
     */
    /**
     * Delete button click logic.
     *
     * @param event the event
     */
    protected void deleteButtonClickLogic() {

        LOGGER.debug("deleteButtonClickLogic method Started");
        if (hierarchyDefinitionSystemId.getValue() == null || ConstantsUtils.EMPTY.equals(hierarchyDefinitionSystemId.getValue()) || ConstantsUtils.ZERO.equals(hierarchyDefinitionSystemId.getValue())) {
            MessageBox.showPlain(Icon.INFO, "Delete", StringConstantUtils.NO_HIERARCHY_DEFINITION_HAS_BEEN_SELECTED, ButtonId.OK);
            return;
        } else {
            MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION, "Are you sure you want to delete record " + heirarchyName + "?", new MessageBoxListener() {
                /**
                 * Called when a Button has been clicked.
                 */
                @SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                    if (buttonId.name().equals("YES")) {
                        try {
                            final String deletedHrName = hierarchybuilderlogic.deleteHierarchy(Integer.parseInt(hierarchyDefinitionSystemId.getValue().toString()
                                    .replaceAll(ConstantsUtils.COMMA, ConstantsUtils.EMPTY)));

                            if (deletedHrName.equalsIgnoreCase("failure")) {
                                CommonUIUtils.getMessageNotification("Hierarchy Can't delete");
                            } else {
                                CommonUIUtils.getMessageNotification("'" + heirarchyName + "' has been deleted Successfully");
                                hierarchyBuilderDTOBean.removeAllItems();
                                final List<HierarchyBuilderDTO> hierarchyInfo = new ArrayList<>();

                                hierarchyBuilderDTOBean.addAll(hierarchyInfo);
                            }

                        } catch (SystemException ex) {
                            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                            LOGGER.error(ex);
                        } catch (Exception ex) {
                            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4004));
                            LOGGER.error(ex);
                        }
                    }
                }
            }, ButtonId.YES, ButtonId.NO);
        }
        LOGGER.debug("deleteButtonClickLogic method Ended");
    }

    /**
     * Results customer click.
     *
     * @param systemId the system id
     */
    protected void resultsCustomerClick(final Object systemId) {
        LOGGER.debug("resultsCustomerClick method started");
        if (systemId == null) {
            hierarchyDefinitionSystemId.setValue(null);
            version = 0;
            searchCriteria = ConstantsUtils.EMPTY;
            heirarchyName = ConstantsUtils.EMPTY;
        } else {
            enableActionButton();
            BeanItem<?> targetCustomer;
            if (systemId instanceof BeanItem<?>) {
                targetCustomer = (BeanItem<?>) systemId;
            } else if (systemId instanceof HierarchyBuilderDTO) {
                targetCustomer = new BeanItem<>((HierarchyBuilderDTO) systemId);
            } else {
                targetCustomer = NULLOBJECT;
            }
            final int customerGroupSysId = ((HierarchyBuilderDTO) targetCustomer.getBean()).getHierarchyDefinitionSystemId();
            version = ((HierarchyBuilderDTO) targetCustomer.getBean()).getVersionNo();
            hierarchyDefinitionSystemId.setValue(String.valueOf(customerGroupSysId));
            heirarchyName = ((HierarchyBuilderDTO) targetCustomer.getBean()).getHierarchyName();

        }

        LOGGER.debug("resultsCustomerClick method started");
    }

    /**
     * This method is always called before the view is shown on screen.
     *
     * @param event the event
     */
    public void enter(final ViewChangeListener.ViewChangeEvent event) {
        return;
    }

    /**
     * gets the null bean item.
     *
     * @return Null beanitem
     */
    public BeanItem<?> getNULLOBJECT() {
        return NULLOBJECT;
    }

    /**
     * The Class DateValidator.
     */
    @SuppressWarnings("rawtypes")
    private class DateValidator extends AbstractValidator {

        /**
         * Instantiates a new date validator.
         */
        public DateValidator() {
            super(ConstantsUtils.EMPTY);
        }

        /**
         * Instantiates a new date validator.
         *
         * @param errorMessage the error message
         */
        public DateValidator(final String errorMessage) {
            super(errorMessage);
        }

        /**
         * (non-Javadoc).
         *
         * @param value the value
         * @throws InvalidValueException the invalid value exception
         * @see
         * com.vaadin.data.validator.AbstractValidator#validate(java.lang.Object)
         */
        @Override
        public void validate(final Object value) {
            LOGGER.debug("validate Method started");
            if (createdDateFrom.getValue() != null && createdDateTo.getValue() != null) {
                if (createdDateFrom.getValue().after(createdDateTo.getValue())) {
                    throw new Validator.InvalidValueException("Created Date To should be greater than Created Date From");
                } else if (createdDateFrom.getValue().equals(createdDateTo.getValue())) {
                    throw new Validator.InvalidValueException("Created Date From and Created Date To should not be equal");
                }
            }
            LOGGER.debug("validate Method Ended");
        }

        /**
         * (non-Javadoc).
         *
         * @param value the value
         * @return true, if is valid value
         * @see
         * com.vaadin.data.validator.AbstractValidator#isValidValue(java.lang.Object)
         */
        @Override
        protected boolean isValidValue(final Object value) {
            LOGGER.debug("isValidValue Method started");
            boolean flag;
            if (createdDateFrom.getValue() == null || createdDateTo.getValue() == null) {
                flag = true;
            } else {
                flag = createdDateFrom.getValue().compareTo(createdDateTo.getValue()) <= 0;
            }
            LOGGER.debug("isValidValue Method returns true");
            return flag;
        }

        /**
         * (non-Javadoc).
         *
         * @return the type
         * @see com.vaadin.data.validator.AbstractValidator#getType()
         */
        @Override
        public Class getType() {
            return null;
        }
    }

    /**
     * Gets the bean from id.
     *
     * @param obj the obj
     * @return the bean from id
     */
    public HierarchyBuilderDTO getBeanFromId(final Object obj) {
        LOGGER.debug("Entering getBeanFromId method");

        BeanItem<?> targetItem = null;
        if (obj instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) obj;
        } else if (obj instanceof HierarchyBuilderDTO) {
            targetItem = new BeanItem<>((HierarchyBuilderDTO) obj);
        }
        LOGGER.debug("End of getBeanFromId method");
        return (HierarchyBuilderDTO) targetItem.getBean();
    }

    /**
     * Enable the action.
     */
    private void enableActionButton() {
        if (!searchCriteria.equalsIgnoreCase(ConstantsUtils.EMPTY) && searchCriteria.equalsIgnoreCase(ConstantsUtils.SEARCH)) {
            btnDelete.setEnabled(true);
            btnView.setEnabled(true);
            btnEdit.setEnabled(true);
        } else if (!searchCriteria.equalsIgnoreCase(ConstantsUtils.EMPTY) && searchCriteria.equalsIgnoreCase(ConstantsUtils.AUDIT_SEARCH)) {
            btnDelete.setEnabled(false);
            btnView.setEnabled(true);
            btnEdit.setEnabled(false);
        }
    }

    public void addResponsiveSearchTableCollapse(final ExtFilterTable table) {
        final Map<Integer, Boolean> reloadMap = new HashMap<>();
        reloadMap.put(NumericConstants.ONE_THREE_FIVE_ZERO, true);
        reloadMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
        reloadMap.put(NumericConstants.SIX_HUNDRED, true);
        reloadMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
        reloadMap.put(NumericConstants.THREE_TWO_ZERO, true);
        reloadMap.put(0, true);

        try {

            table.setColumnCollapsingAllowed(true);

            Page.getCurrent().addBrowserWindowResizeListener(new Page.BrowserWindowResizeListener() {
                @Override
                public void browserWindowResized(final Page.BrowserWindowResizeEvent event) {

                    int browserWidth = Page.getCurrent().getBrowserWindowWidth();
                    if (browserWidth > NumericConstants.ONE_THREE_FIVE_ZERO && reloadMap.get(NumericConstants.ONE_THREE_FIVE_ZERO)) {
                        LOGGER.debug("CONDITION 1");

                        for (Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (Object propertyId : getCollapsibleColumnsDefault(table)) {
                            table.setColumnCollapsed(propertyId, browserWidth > NumericConstants.ONE_THREE_FIVE_ZERO);
                        }
                        reloadMap.put(NumericConstants.ONE_THREE_FIVE_ZERO, false);
                        reloadMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                        reloadMap.put(NumericConstants.SIX_HUNDRED, true);
                        reloadMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                        reloadMap.put(NumericConstants.THREE_TWO_ZERO, true);
                        reloadMap.put(0, true);

                    } else if (browserWidth < NumericConstants.ONE_THREE_FIVE_ZERO && browserWidth > NumericConstants.NINE_SEVEN_EIGHT && reloadMap.get(NumericConstants.NINE_SEVEN_EIGHT)) {

                        LOGGER.debug("CONDITION 2");

                        for (Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (Object propertyId : getCollapsibleColumnsDefault1515Px(table)) {
                            table.setColumnCollapsed(propertyId, browserWidth < NumericConstants.ONE_THREE_FIVE_ZERO);
                        }

                        reloadMap.put(NumericConstants.ONE_THREE_FIVE_ZERO, true);
                        reloadMap.put(NumericConstants.NINE_SEVEN_EIGHT, false);
                        reloadMap.put(NumericConstants.SIX_HUNDRED, true);
                        reloadMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                        reloadMap.put(NumericConstants.THREE_TWO_ZERO, true);
                        reloadMap.put(0, true);

                    } else if (browserWidth < NumericConstants.NINE_SEVEN_EIGHT && browserWidth > NumericConstants.SIX_HUNDRED && reloadMap.get(NumericConstants.SIX_HUNDRED)) {

                        LOGGER.debug("CONDITION 3");
                        for (Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }

                        if (table != null && table.getItemIds().isEmpty()) {
                            for (Object propertyId : getCollapsibleColumns978Px(table)) {
                                table.setColumnCollapsed(propertyId, true);
                            }
                        } else {
                            for (Object propertyId : getCollapsibleColumns600Px(table)) {
                                table.setColumnCollapsed(propertyId, true);
                            }
                        }

                        reloadMap.put(NumericConstants.ONE_THREE_FIVE_ZERO, true);
                        reloadMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                        reloadMap.put(NumericConstants.SIX_HUNDRED, false);
                        reloadMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                        reloadMap.put(NumericConstants.THREE_TWO_ZERO, true);
                        reloadMap.put(0, true);

                    } else if (browserWidth < NumericConstants.SIX_HUNDRED && browserWidth > NumericConstants.FOUR_EIGHT_ZERO && reloadMap.get(NumericConstants.FOUR_EIGHT_ZERO)) {
                        LOGGER.debug("CONDITION 4");

                        for (Object propertyId : table.getVisibleColumns()) {
                            // table is refreshed when container reloading is disabled
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (Object propertyId : getCollapsibleColumns600Px(table)) {
                            // table is refreshed when container reloading is disabled
                            table.setColumnCollapsed(propertyId, true);
                        }
                        reloadMap.put(NumericConstants.ONE_THREE_FIVE_ZERO, true);
                        reloadMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                        reloadMap.put(NumericConstants.SIX_HUNDRED, true);
                        reloadMap.put(NumericConstants.FOUR_EIGHT_ZERO, false);
                        reloadMap.put(NumericConstants.THREE_TWO_ZERO, true);
                        reloadMap.put(0, true);
                        //  customerSearchCriteria.setCustomDirty(true);            // --> Enables reloading the container
                    } else if (browserWidth < NumericConstants.FOUR_EIGHT_ZERO && browserWidth > NumericConstants.THREE_TWO_ZERO && reloadMap.get(NumericConstants.THREE_TWO_ZERO)) {
                        LOGGER.debug("CONDITION 5");

                        for (Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (Object propertyId : getCollapsibleColumns480Px(table)) {
                            table.setColumnCollapsed(propertyId, browserWidth < NumericConstants.FOUR_EIGHT_ZERO);
                        }
                        reloadMap.put(NumericConstants.ONE_THREE_FIVE_ZERO, true);
                        reloadMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                        reloadMap.put(NumericConstants.SIX_HUNDRED, true);
                        reloadMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                        reloadMap.put(NumericConstants.THREE_TWO_ZERO, false);
                        reloadMap.put(0, true);

                    } else if (browserWidth < NumericConstants.THREE_TWO_ZERO && reloadMap.get(0)) {
                        LOGGER.debug("CONDITION 6");

                        for (Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (Object propertyId : getCollapsibleColumns480Px(table)) {
                            table.setColumnCollapsed(propertyId, browserWidth < NumericConstants.THREE_TWO_ZERO);
                        }
                        reloadMap.put(NumericConstants.ONE_THREE_FIVE_ZERO, true);
                        reloadMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                        reloadMap.put(NumericConstants.SIX_HUNDRED, true);
                        reloadMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                        reloadMap.put(NumericConstants.THREE_TWO_ZERO, true);
                        reloadMap.put(0, false);

                    }

                }
            });
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    private static String[] getCollapsibleColumns600Px(final ExtFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    private static String[] getCollapsibleColumns480Px(final ExtFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    private static String[] getCollapsibleColumns978Px(final ExtFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        list.remove(propertyIds[NumericConstants.TWO]);
        list.remove(propertyIds[NumericConstants.THREE]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    private static String[] getCollapsibleColumnsDefault1515Px(final ExtFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        list.remove(propertyIds[NumericConstants.TWO]);
        list.remove(propertyIds[NumericConstants.THREE]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    private static String[] getCollapsibleColumnsDefault(final ExtFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<>(Arrays.asList(propertyIds));
        for (int i = 0; i < NumericConstants.TEN && !list.isEmpty(); i++) {
            list.remove(propertyIds[i]);
        }
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }
}
