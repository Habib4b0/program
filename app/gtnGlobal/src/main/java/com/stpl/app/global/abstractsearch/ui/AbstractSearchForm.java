package com.stpl.app.global.abstractsearch.ui;

import com.stpl.app.global.abstractsearch.dto.SearchCriteriaDTO;
import com.stpl.app.global.abstractsearch.dto.SearchResultsDTO;
import com.stpl.app.global.abstractsearch.logic.AbstractSearchLogic;
import com.stpl.app.global.abstractsearch.logic.tablelogic.AbstractSearchTableLogic;
import com.stpl.app.global.abstractsearch.util.CommonUtils;
import com.stpl.app.global.abstractsearch.util.ConstantUtil;
import com.stpl.app.global.abstractsearch.util.Message;
import com.stpl.app.global.abstractsearch.util.MessageUtil;
import com.stpl.app.global.abstractsearch.util.UIUtils;
import com.stpl.app.global.abstractsearch.util.ValidationUtil;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.compliancededuction.logic.CDRLogic;
import com.stpl.app.global.compliancededuction.ui.util.ComplianceDeductionFilterGenerator;
import com.stpl.app.global.compliancededuction.ui.view.CDRView;
import com.stpl.app.global.deductioncalendar.logic.SelectionLogic;
import com.stpl.app.global.deductioncalendar.ui.util.DeductionFilterGenerator;
import com.stpl.app.global.deductioncalendar.ui.view.DeductionCalendarView;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.app.util.FunctionNameUtil;
import com.stpl.app.util.ResponsiveUtils;
import com.stpl.app.util.UISecurityUtil;
import com.stpl.ifs.ui.DateToStringConverter;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.ui.util.converters.DataTypeConverter;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.vaadin.v7.data.Property.ValueChangeEvent;
import com.vaadin.v7.data.Validator;
import com.vaadin.v7.data.fieldgroup.FieldGroup;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.util.converter.StringToDateConverter;
import com.vaadin.v7.data.validator.AbstractValidator;
import com.vaadin.v7.data.validator.RegexpValidator;
import com.vaadin.v7.data.validator.StringLengthValidator;
import com.vaadin.v7.event.ItemClickEvent;
import com.vaadin.server.Resource;
import static com.vaadin.server.Sizeable.UNITS_PERCENTAGE;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 * The Class AbstractSearchForm.
 */
public final class AbstractSearchForm extends CustomComponent {

    /**
     * The Text Box.
     */
    @UiField("text1")
    private TextField text1;

    /**
     * The Text Box.
     */
    @UiField("text2")
    private TextField text2;

    /**
     * The Text Box.
     */
    @UiField("text3")
    private TextField text3;

    /**
     * The Text Box.
     */
    @UiField("text4")
    private TextField text4;

    /**
     * The Text Box.
     */
    @UiField("text5")
    private TextField text5;

    /**
     * The Combo Box.
     */
    @UiField("combo1")
    private ComboBox combo1;

    /**
     * The Combo Box.
     */
    @UiField("combo2")
    private ComboBox combo2;

    /**
     * The Text Field.
     */
    @UiField("text6")
    private TextField text6;

    /**
     * The Text Box.
     */
    @UiField("text8")
    private TextField text8;

    /**
     * The Text Box.
     */
    @UiField("text9")
    private TextField text9;

    /**
     * The Combo Box.
     */
    @UiField("combo3")
    private ComboBox combo3;

    /**
     * The Text Box.
     */
    @UiField("combo4")
    private ComboBox combo4;

    /**
     * The Text Box.
     */
    @UiField("combo5")
    private ComboBox combo5;

    /**
     * The Text Box.
     */
    @UiField("text7")
    private TextField text7;

    /**
     * The Combo Box.
     */
    @UiField("combo6")
    private ComboBox combo6;

    /**
     * The Combo Box.
     */
    @UiField("combo7")
    private ComboBox combo7;

    /**
     * The Combo Box.
     */
    @UiField("combo8")
    private ComboBox combo8;

    /**
     * The Button.
     */
    @UiField("searchBtn")
    private Button searchBtn;

    /**
     * The Button.
     */
    @UiField("resetBtn")
    private Button resetBtn;

    @UiField("addBtn")
    private Button addBtn;

    @UiField("editBtn")
    private Button editBtn;

    @UiField("viewBtn")
    private Button viewBtn;

    /**
     * The ErrorLabel.
     */
    @UiField("errorMsg")
    private ErrorLabel errorMsg;

    /**
     * The Label
     */
    @UiField("label1")
    private Label label1;

    /**
     * The Label
     */
    @UiField("label2")
    private Label label2;

    /**
     * The Label
     */
    @UiField("label3")
    private Label label3;

    /**
     * The Label
     */
    @UiField("label4")
    private Label label4;

    /**
     * The Label
     */
    @UiField("label5")
    private Label label5;


    /**
     * The Label
     */
    @UiField("label7")
    private Label label7;

    /**
     * The Label
     */
    @UiField("label8")
    private Label label8;

    /**
     * The Label
     */
    @UiField("label9")
    private Label label9;

    /**
     * The Label
     */
    @UiField("label10")
    private Label label10;

    /**
     * The Label
     */
    @UiField("label11")
    private Label label11;

    /**
     * The Label
     */
    @UiField("label12")
    private Label label12;

    /**
     * The Label
     */
    @UiField("label13")
    private Label label13;

    /**
     * The Label
     */
    @UiField("label14")
    private Label label14;

    /**
     * The Label
     */
    @UiField("label15")
    private Label label15;

    /**
     * The Label
     */
    @UiField("label16")
    private Label label16;

    /**
     * The Label
     */
    @UiField("label17")
    private Label label17;
    
    @UiField("label6")
    private Label label6;
    /**
     * Copy Button
     */
    @UiField("copyBtn")
    private Button copyBtn;
    @UiField("deleteBtn")
    private Button deleteBtn;

    @UiField("reset")
    private Button reset;

    @UiField("excel")
    private Button excel;

    @UiField("tableLayout")
    private VerticalLayout tableLayout;

    @UiField("controlLayout")
    private HorizontalLayout controlLayout;

    @UiField("exportBtn")
    private Button exportBtn;

    private AbstractSearchTableLogic tableLogic = new AbstractSearchTableLogic();
    private ExtPagedTable resultTable = new ExtPagedTable(tableLogic);
    private BeanItemContainer<SearchResultsDTO> resultBean = new BeanItemContainer<>(SearchResultsDTO.class);
    private final Resource excelExportImage = new ThemeResource("img/excel.png");
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractSearchForm.class);
    private final AbstractSearchLogic searchLogic = new AbstractSearchLogic();
    private static ResourceBundle columnBundle = ResourceBundle.getBundle("properties.tableColumns");
    private String moduleName = StringUtils.EMPTY;
    private final CommonUtils commonsUtil = new CommonUtils();
    /**
     * The SessionDTO.
     */
    private SessionDTO sessionDTO;
    /**
     * The binder.
     */
    private final ErrorfulFieldGroup binder;

    /**
     * The Constructor.
     *
     * @param moduleName
     * @throws com.stpl.portal.kernel.exception.SystemException
     */
    public AbstractSearchForm(String moduleName) throws SystemException {
        super();
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/abstractsearchform.xml"), this));
        binder = getBinder();
        try {
            this.moduleName = moduleName;
            init();
        } catch (PortalException | SystemException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public AbstractSearchForm(String moduleName, SessionDTO sessionDTO) throws SystemException {
        super();

        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/abstractsearchform.xml"), this));
        binder = getBinder();
        this.moduleName = moduleName;
        this.sessionDTO = sessionDTO;
        try {
            init();
        } catch (PortalException | SystemException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Inits the.
     *
     * @throws com.stpl.portal.kernel.exception.PortalException
     * @throws com.stpl.portal.kernel.exception.SystemException
     */
    public void init() throws PortalException, SystemException {
        configureFields();
        configureTable();
        securityForAllScreens();
    }

    /**
     * Gets the binder.
     *
     * @return the binder
     */
    public ErrorfulFieldGroup getBinder() {
        final SearchCriteriaDTO bean = new SearchCriteriaDTO();
        final ErrorfulFieldGroup errFieldBinder = new ErrorfulFieldGroup(new BeanItem<>(bean));
        errFieldBinder.setBuffered(true);
        errFieldBinder.bindMemberFields(this);
        errFieldBinder.setErrorDisplay(errorMsg);
        return errFieldBinder;
    }

    /**
     * Method has configuration for the fields.
     */
    public void configureFields() throws SystemException {
        try {
            LOGGER.debug(" Enters  configureFields() ");
            configureLayout(moduleName);
            fieldValidation(moduleName);
            loadComponents(moduleName);
            for (java.lang.reflect.Field field : this.getClass().getDeclaredFields()) {
                if (field.get(this) instanceof Label && ((Label) field.get(this)).isVisible()) {
                    setLabelName(field.get(this), field.getName() + "_" + moduleName);
                    ResponsiveUtils.makeLabel((Label) field.get(this), false);

                } else if (field.get(this) instanceof TextField && ((TextField) field.get(this)).isVisible()) {

                    textValidation(field.get(this), ((TextField) field.get(this)).getData());
                    ((TextField) field.get(this)).setValidationVisible(true);

                }
            }

            excel.setIcon(excelExportImage);
            excel.setDescription("Export to excel");
            excel.setIconAlternateText("Excel export");
            excel.setHtmlContentAllowed(true);
            LOGGER.debug(" Exits configureFields() ");
        } catch (PortalException | SystemException | IllegalAccessException | IllegalArgumentException | SecurityException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void configureTable() {
        List<Integer> pageLength = new ArrayList<>();
        pageLength.add(NumericConstants.TEN);
        pageLength.add(NumericConstants.FIFTEEN);
        pageLength.add(NumericConstants.TWENTY);
        pageLength.add(NumericConstants.TWENTY_FIVE);
        pageLength.add(NumericConstants.FIFTY);
        pageLength.add(NumericConstants.HUNDRED);
        tableLogic.getControlConfig().setPageLengthsAndCaptions(pageLength);
        tableLayout.addComponent(resultTable);
        ResponsiveUtils.getResponsiveControls(tableLogic.createControls(), controlLayout);
        tableLayout.addComponent(controlLayout);

        tableLogic.setContainerDataSource(resultBean);
        tableLogic.setPageLength(NumericConstants.TEN);
        tableLogic.sinkItemPerPageWithPageLength(false);

        setTableDefaultConfig(resultTable, ConstantUtil.ITEM_MASTER.equalsIgnoreCase(moduleName) || ConstantUtil.COMPANY_MAST.equalsIgnoreCase(moduleName) ? moduleName + "W" : moduleName);
        resultTable.setSelectable(true);
        resultTable.markAsDirty();
        resultTable.setComponentError(null);
        resultTable.setFilterBarVisible(true);
        resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultTable.setFilterGenerator(getFilterGeneratorClass(moduleName));
        resultTable.setValidationVisible(false);
        resultTable.addStyleName(ConstantsUtils.FILTER_BAR);
        resultTable.setConverter("dcCreationDate", new StringToDateConverter() {
            @Override
            public DateFormat getFormat(Locale locale) {
                return new SimpleDateFormat("MM/dd/yyyy");
            }
        });
        resultTable.setConverter("dcModifiedDate", new StringToDateConverter() {
            @Override
            public DateFormat getFormat(Locale locale) {
                return new SimpleDateFormat("MM/dd/yyyy");
            }
        });

        resultTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {

            @Override
            public void itemClick(ItemClickEvent event) {
                itemselectLogic(event);
            }
        });
    }

    public void itemselectLogic(final ItemClickEvent event) {
        try {
            if (event != null) {
                if (event.getItemId() != null) {
                    SearchResultsDTO searchForm = (SearchResultsDTO) event.getItemId();
                    if (event.isDoubleClick()) {

                        if (searchForm.isRecordLockStatus()) {
                            if (itemStatusCheck()) {
                                sessionDTO.setMode(ConstantsUtils.VIEW);
                                if (ConstantUtil.COMPLIANCE_DEDUCTION_RULES.equals(moduleName)) {
                                    getUI().getNavigator().navigateTo(CDRView.NAME);
                                } else {
                                    getUI().getNavigator().navigateTo(ConstantsUtils.ADD);
                                }
                                editLogic();
                            } else {
                                final MessageBox msg = MessageBox.showPlain(Icon.INFO, "Access Denied", "You do not have permission to edit this record", new MessageBoxListener() {
                                    /**
                                     * The method is triggered when a button of
                                     * the message box is pressed .
                                     *
                                     * @param buttonId The buttonId of the
                                     * pressed button.
                                     */
                                    @SuppressWarnings("PMD")
                                    @Override
                                    public void buttonClicked(final ButtonId buttonId) {
                                        // Do Nothing
                                    }
                                }, ButtonId.OK);
                                msg.getButton(ButtonId.OK).focus();
                            }
                        } else {
                            sessionDTO.setMode(ConstantsUtils.EDIT);
                            if (ConstantUtil.COMPLIANCE_DEDUCTION_RULES.equals(moduleName)) {
                                getUI().getNavigator().navigateTo(CDRView.NAME);
                            } else {
                                getUI().getNavigator().navigateTo(ConstantsUtils.ADD);
                            }
                            editLogic();
                        }

                    }
                    sessionDTO.setSystemId(Integer.parseInt(searchForm.getSystemID()));
                }
                resultTable.setValue(null);
            }
        } catch (Property.ReadOnlyException | NumberFormatException e) {
            LOGGER.error(e.getMessage());

        }
    }

    public boolean itemStatusCheck() {
        try {

            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            return commonsUtil.checkETLUser(Integer.parseInt(userId));
        } catch (NumberFormatException ex) {
            LOGGER.error(ex.getMessage());
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1015), new MessageBoxListener() {
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                @SuppressWarnings("PMD")
                @Override
                public void buttonClicked(final ButtonId buttonId) {
                    // Do Nothing
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
        }
        return false;
    }

    @UiHandler("searchBtn")
    public void btnSearchLogic(Button.ClickEvent event) {
        binder.getErrorDisplay().clearError();
        List<Object> collapsedColumns = new ArrayList<>();
        for (Object item : resultTable.getVisibleColumns()) {
            if (resultTable.isColumnCollapsed(item)) {
                collapsedColumns.add(item);
            }
        }
        if (!searchLogic.checkSearchCriteria(binder)) {
            final MessageBox msg = MessageBox.showPlain(Icon.WARN, MessageUtil.getMessage(Message.SEARCH_CRITERIA_HEADER), MessageUtil.getMessage(Message.SEARCH_CRITERIA_MESSAGE), new MessageBoxListener() {

                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                @SuppressWarnings("PMD")
                @Override
                public void buttonClicked(final ButtonId buttonId) {
                    // Do Nothing
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
        } else {

            try {
                binder.commit();
                tableLogic.configureSearchData(binder, this.moduleName, this);
                resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
                resultTable.setFilterGenerator(getFilterGeneratorClass(moduleName));
                resultTable.setImmediate(true);
                resultTable.setWidth(NumericConstants.NINTY_NINE, UNITS_PERCENTAGE);
                resultTable.addStyleName(ConstantsUtils.TABLE_CHECK_BOX);
                resultTable.setSelectable(true);
                if (tableLogic.isResultsEmpty()) {
                    CommonUIUtils.successNotification(ConstantUtil.NO_RESULTS_COMPLETED);
                } else {
                    CommonUIUtils.successNotification(ConstantUtil.SEARCH_COMPLETED);
                }

                resultTable.markAsDirtyRecursive();

            } catch (FieldGroup.CommitException commit) {
                tableLogic.clearAll();
                tableLogic.getFilters().clear();
                resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
                LOGGER.error(commit.getMessage());
            } catch (Exception exception) {
                final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1004), new MessageBoxListener() {
                    /**
                     * The method is triggered when a button of the message box
                     * is pressed .
                     *
                     * @param buttonId The buttonId of the pressed button.
                     */
                    @SuppressWarnings("PMD")
                    @Override
                    public void buttonClicked(final ButtonId buttonId) {
                        // Do Nothing
                    }
                }, ButtonId.OK);
                msg.getButton(ButtonId.OK).focus();
                LOGGER.error(exception.getMessage());
            }
        }

    }

    /**
     * Reset Button Listener
     *
     * @param event
     */
    @UiHandler("resetBtn")
    public void btnResetLogic(Button.ClickEvent event) {

        MessageBox.showPlain(Icon.QUESTION, ConstantUtil.CONFORMATION_MSG, "Are you sure you want to reset the values in the Search Criteria group box?", new MessageBoxListener() {
            /**
             * Called when a Button has been clicked .
             *
             */
            @SuppressWarnings("PMD")
            @Override
            public void buttonClicked(final ButtonId buttonId) {
                if (buttonId.name().equalsIgnoreCase(ConstantUtil.YES_VARIABLE)) {
                    LOGGER.debug("Entering Reset operation");
                    binder.getErrorDisplay().clearError();
                    binder.setItemDataSource(new BeanItem<>(new SearchCriteriaDTO()));
                    if (ConstantUtil.ITEM_MASTER.equalsIgnoreCase(moduleName)) {
                        binder.getField("text7").setEnabled(false);
                    }
                    if (ConstantUtil.NET_SALES_FORMULA.equalsIgnoreCase(moduleName) || ConstantUtil.COMPLIANCE_DEDUCTION_RULES.equalsIgnoreCase(moduleName) || ConstantUtil.REBATE_PLAN.equalsIgnoreCase(moduleName) || ConstantUtil.DEDUCTION_CALENDAR.equalsIgnoreCase(moduleName)) {
                        binder.getErrorDisplay().clearError();
                        binder.setItemDataSource(new BeanItem<>(new SearchCriteriaDTO()));
                    } else {
                        tableLogic.clearAll();
                        tableLogic.setReset(true);
                        tableLogic.setRequiredCount(true);
                        tableLogic.setCurrentPage(NumericConstants.ONE);
                        tableLogic.setContainerDataSource(resultBean);
                        tableLogic.setPageLength(NumericConstants.TEN);
                        tableLogic.sinkItemPerPageWithPageLength(false);

                        setTableDefaultConfig(resultTable, ConstantUtil.ITEM_MASTER.equalsIgnoreCase(moduleName) || ConstantUtil.COMPANY_MAST.equalsIgnoreCase(moduleName) ? moduleName + "W" : moduleName);
                        resultTable.setSelectable(true);
                        resultTable.markAsDirty();
                        resultTable.setComponentError(null);
                        resultTable.setFilterBarVisible(true);
                        resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
                        resultTable.setFilterGenerator(getFilterGeneratorClass(moduleName));
                        resultTable.setValidationVisible(false);
                        resultTable.addStyleName(ConstantsUtils.FILTER_BAR);
                        LOGGER.debug("Ending Reset operation");
                    }
                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }

    @UiHandler("reset")
    public void ResetLogic(Button.ClickEvent event) {

        MessageBox.showPlain(Icon.QUESTION, ConstantUtil.CONFORMATION_MSG, "Are you sure you want to reset "
                + "the listview to default/previous values?", new MessageBoxListener() {
            /**
             * Called when a Button has been clicked .
             *
             */
            @SuppressWarnings("PMD")
            @Override
            public void buttonClicked(final ButtonId buttonId) {
                if (buttonId.name().equalsIgnoreCase(ConstantUtil.YES_VARIABLE)) {
                    LOGGER.debug("Entering Reset operation");
                    binder.getErrorDisplay().clearError();
                    tableLogic.clearAll();
                    tableLogic.setReset(true);
                    tableLogic.setRequiredCount(true);
                    tableLogic.setCurrentPage(1);
                    tableLogic.setPageLength(NumericConstants.TEN);
                    tableLogic.sinkItemPerPageWithPageLength(false);

                    LOGGER.debug("Ending Reset operation");
                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }

    @UiHandler("text7")
    public void valueChangeItemIdentifier(final ValueChangeEvent event) {
        if (ConstantUtil.ITEM_MASTER.equalsIgnoreCase(moduleName) && text7.getValidators() != null) {
            combo5.setValidationVisible(false);
        }
    }

    @UiHandler("text8")
    public void valueChangeCompanyIdentifier(final ValueChangeEvent event) {
        if (ConstantUtil.COMPANY_MAST.equalsIgnoreCase(moduleName) && text8.getValidators() != null) {
            combo6.setValidationVisible(false);
        }
    }

    @UiHandler("combo5")
    public void valueChangeItemIrtQualifierNameDDLB(final ValueChangeEvent event) {

        if (ConstantUtil.ITEM_MASTER.equalsIgnoreCase(moduleName)) {
            if (event.getProperty() != null && event.getProperty().getValue() != null && StringUtils.EMPTY.equals(event.getProperty().getValue())) {
                combo6.select(ConstantUtil.SELECT_ONE);
            }
            if (combo5.getValue() == null || ConstantUtil.SELECT_ONE.equals(combo5.getValue().toString())) {
                text7.setValidationVisible(false);
                text7.setValue(StringUtils.EMPTY);
                text7.setEnabled(false);

            } else {
                text7.setEnabled(true);
            }
        }
    }

    @UiHandler("combo6")
    public void valueChangeCompanyCrtQualifierNameDDLB(final ValueChangeEvent event) {

        if (ConstantUtil.COMPANY_MAST.equalsIgnoreCase(moduleName)) {
            if (combo6.getValue() == null || ConstantUtil.SELECT_ONE.equals(combo6.getValue().toString())) {
                text8.setValidationVisible(false);
                text8.setValue(StringUtils.EMPTY);
                text8.setEnabled(false);

            } else {
                text8.setEnabled(true);
            }
        }
    }

    /**
     * To Validate Text Fields
     *
     * @param obj
     * @param key
     */
    private void textValidation(Object obj, Object key) {
        try {
            if (obj != null && key != null && !ConstantUtil.NULL.equals(key) && obj instanceof TextField) {
                TextField tempObj = (TextField) obj;
                String[] rules = String.valueOf(key).split(ConstantUtil.COMMA);
                if (rules[0] != null && ValidationUtil.getMessage(rules[0]) != null && StringUtils.isNotEmpty(ValidationUtil.getMessage(rules[0]))) {
                    String[] temp = ValidationUtil.getMessage(rules[0]).split(ConstantUtil.COMMA);
                    tempObj.addValidator(new StringLengthValidator(ValidationUtil.getMessage(rules[1]), 
                            DataTypeConverter.convertStringToInteger(temp[0]), DataTypeConverter.convertStringToInteger(temp[1]), DataTypeConverter.convertStringToBoolean(temp[NumericConstants.TWO])));
                }
                if (!ConstantUtil.NULL.equalsIgnoreCase(rules[NumericConstants.TWO]) && ValidationUtil.getMessage(rules[NumericConstants.TWO]) != null && StringUtils.isNotEmpty(ValidationUtil.getMessage(rules[NumericConstants.TWO]))) {
                    tempObj.addValidator(new RegexpValidator(ValidationUtil.getMessage(rules[NumericConstants.TWO]), ValidationUtil.getMessage(rules[NumericConstants.THREE])));
                }
            }
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * To Set Label Value
     *
     * @param obj
     * @param key
     */
    private void setLabelName(Object obj, String key) {
        try {
            Label tempObj = (Label) obj;
            if (ValidationUtil.getLabel(key) != null && StringUtils.isNotEmpty(ValidationUtil.getLabel(key))) {
                tempObj.setValue(ValidationUtil.getLabel(key));
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * To get Visible Columns & Visible Headers
     *
     * @param isColumns
     * @param key
     * @return
     */
    private Object[] getColumns(boolean isColumns, String key) {
        return (columnBundle.getString(isColumns ? "columns" + key : "headers" + key)).split(ConstantUtil.COMMA);

    }

    /**
     *
     * @param resultTable
     * @param key
     */
    public void setTableDefaultConfig(ExtPagedTable resultTable, String key) {
        resultTable.setVisibleColumns(getColumns(true, ConstantUtil.ITEM_MASTER.equalsIgnoreCase(moduleName) || ConstantUtil.COMPANY_MAST.equalsIgnoreCase(moduleName) ? key : moduleName));
        resultTable.setColumnHeaders(Arrays.copyOf(getColumns(false, ConstantUtil.ITEM_MASTER.equalsIgnoreCase(moduleName) || ConstantUtil.COMPANY_MAST.equalsIgnoreCase(moduleName) ? key : moduleName), getColumns(false, ConstantUtil.ITEM_MASTER.equalsIgnoreCase(moduleName) || ConstantUtil.COMPANY_MAST.equalsIgnoreCase(moduleName) ? key : moduleName).length, String[].class));
        resultTable.markAsDirtyRecursive();
        resultTable.setImmediate(true);
        resultTable.setWidth(NumericConstants.HUNDRED, UNITS_PERCENTAGE);
        configureAlignment(key);
    }

    private void configureLayout(String moduleName) {

        if (ConstantUtil.DEDUCTION_CALENDAR.equals(moduleName)) {
            label4.setVisible(false);
            text4.setVisible(false);
            label5.setVisible(false);
            text5.setVisible(false);
            label7.setVisible(false);
            combo2.setVisible(false);
            label8.setVisible(false);
            text6.setVisible(false);
            label9.setVisible(false);
            combo3.setVisible(false);
            label10.setVisible(false);
            combo4.setVisible(false);
            label11.setVisible(false);
            combo5.setVisible(false);
            label12.setVisible(false);
            text7.setVisible(false);
            label13.setVisible(false);
            combo6.setVisible(false);
            label14.setVisible(false);
            combo7.setVisible(false);
            label15.setVisible(false);
            combo8.setVisible(false);
            label16.setVisible(false);
            text8.setVisible(false);
            label17.setVisible(false);
            text9.setVisible(false);
            excel.setVisible(true);
            copyBtn.setVisible(true);
            deleteBtn.setVisible(true);
            reset.setVisible(true);
            exportBtn.setVisible(false);
            label6.setVisible(true);

        } else if (ConstantUtil.COMPLIANCE_DEDUCTION_RULES.equals(moduleName)) {
            label1.setVisible(false);
            text1.setVisible(false);
            label2.setVisible(false);
            text2.setVisible(false);
            label3.setVisible(false);
            text3.setVisible(false);
            label4.setVisible(false);
            text4.setVisible(false);
            label5.setVisible(false);
            text5.setVisible(false);
            label6.setVisible(true);
            label7.setVisible(false);
            combo2.setVisible(false);
            label9.setVisible(false);
            combo3.setVisible(false);
            label10.setVisible(false);
            combo4.setVisible(false);
            label11.setVisible(false);
            combo5.setVisible(false);
            label14.setVisible(false);
            combo7.setVisible(false);
            label15.setVisible(false);
            combo8.setVisible(false);
            label16.setVisible(false);
            text8.setVisible(false);
            label17.setVisible(false);
            text9.setVisible(false);
            copyBtn.setVisible(true);
            deleteBtn.setVisible(true);
            reset.setVisible(true);
        }
    }

    private void loadComponents(String moduleName) throws SystemException, PortalException {
        if (ConstantUtil.DEDUCTION_CALENDAR.equals(moduleName)) {
            commonsUtil.loadComboBox(combo1, UIUtils.DEDUCTION_CALENDAR_CATEGORY);
        } else if (ConstantUtil.COMPLIANCE_DEDUCTION_RULES.equals(moduleName)) {
            commonsUtil.loadComboBox(combo1, UIUtils.RULE_TYPE);
            commonsUtil.loadComboBox(combo6, UIUtils.RULE_CATEGORY);
        }
    }

    private void fieldValidation(String moduleName) {
        if (ConstantUtil.COMPLIANCE_DEDUCTION_RULES.equals(moduleName)) {
            combo1.focus();
            text6.setData(ConstantUtil.NULL_DATA);
            text7.setData(ConstantUtil.NULL_DATA);
        }

    }

    private void configureAlignment(String key) {
        if (moduleName.equals(ConstantUtil.DEDUCTION_CALENDAR)) {
            List<Object> coloumnList;
            coloumnList = Arrays.asList(getColumns(true, ConstantUtil.ITEM_MASTER.equalsIgnoreCase(moduleName) || ConstantUtil.COMPANY_MAST.equalsIgnoreCase(moduleName) ? key : moduleName));
            for (Object object : coloumnList) {
                if (object.equals("dcCreationDate") || object.equals("dcModifiedDate")) {
                    resultTable.setColumnAlignment(object.toString(), ExtPagedTable.Align.CENTER);
                }
            }
        } else if (moduleName.equals(ConstantUtil.COMPLIANCE_DEDUCTION_RULES) || moduleName.equals(ConstantUtil.NET_SALES_FORMULA) || moduleName.equals(ConstantUtil.REBATE_PLAN)) {
            List<Object> coloumnList;
            coloumnList = Arrays.asList(getColumns(true, ConstantUtil.ITEM_MASTER.equalsIgnoreCase(moduleName) || ConstantUtil.COMPANY_MAST.equalsIgnoreCase(moduleName) ? key : moduleName));
            for (Object object : coloumnList) {
                if (object.equals("cdrCreatedDate") || object.equals("cdrModifiedDate")) {
                    resultTable.setConverter(object.toString(), new DateToStringConverter());
                    resultTable.setColumnAlignment(object.toString(), ExtPagedTable.Align.CENTER);
                }
                if (object.equals("nsfcreatedDate") || object.equals("nsfmodifiedDate")) {
                    resultTable.setConverter(object.toString(), new DateToStringConverter());
                    resultTable.setColumnAlignment(object.toString(), ExtPagedTable.Align.CENTER);
                }
                if (object.equals("rpCreatedDate") || object.equals("rpModifiedDate")) {
                    resultTable.setConverter(object.toString(), new DateToStringConverter());
                    resultTable.setColumnAlignment(object.toString(), ExtPagedTable.Align.CENTER);
                }
            }
        }
    }

    /**
     * The Class IdentifierValidator.
     */
    @SuppressWarnings(ConstantUtil.RAWTYPES)
    private class IdentifierValidator extends AbstractValidator {

        /**
         * The Constructor.
         */
        public IdentifierValidator() {
            super("");
        }

        /**
         * The Constructor.
         *
         * @param errorMessage the error message
         */
        public IdentifierValidator(final String errorMessage) {
            super(errorMessage);
        }

        /**
         * Validates the value.
         *
         * @param value the value
         * @throws InvalidValueException the invalid value exception
         */
        @Override
        public void validate(final Object value) {

            markAsDirty();
            if (!isValidValue(value) && hasValue(combo5.getValue())) {
                throw new Validator.InvalidValueException("Both item Identifier and item IRT Qualifier Name should be present");
            }
        }

        /**
         * Validates the value.
         *
         * @param value the value
         * @return true, if checks if is valid value
         */
        @Override
        protected boolean isValidValue(final Object value) {

            if (hasValue(combo5.getValue())) {
                return hasValue(text7.getValue());
            } else {
                return !hasValue(text7.getValue());
            }

        }

        /**
         * Checks for value.
         *
         * @param obj the obj
         * @return true, if checks for value
         */
        private boolean hasValue(final Object obj) {
            LOGGER.debug("Enters hasValue() {}" , obj);

            if (obj == null || ConstantUtil.SELECT_ONE.equals(obj.toString())) {
                return false;
            } else {
                return !StringUtils.isEmpty(obj.toString());

            }
        }

        /**
         * Default method.
         *
         * @return the type
         */
        @Override
        public Class getType() {
            return null;
        }
    }

    @UiHandler("addBtn")
    public void btnAddLogic(Button.ClickEvent event) {
        VaadinSession.getCurrent().setAttribute(ConstantsUtils.GLOBAL_FILES_MODE, ConstantsUtils.ADD);
        sessionDTO.setMode(ConstantsUtils.ADD);
        if (ConstantUtil.DEDUCTION_CALENDAR.equals(moduleName)) {
            sessionDTO.setMode(ConstantUtil.ADD);
            getUI().getNavigator().navigateTo(DeductionCalendarView.NAME);
        }
        if (ConstantUtil.COMPLIANCE_DEDUCTION_RULES.equals(moduleName)) {
            sessionDTO.setMode(ConstantsUtils.ADD);
            sessionDTO.setSystemId(0);
            getUI().getNavigator().navigateTo(CDRView.NAME);
        }
    }

    @UiHandler("editBtn")
    public void btnEditLogic(Button.ClickEvent event) {

        if (resultTable.getValue() == null) {
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "Edit Error", "Please select a record to edit", new MessageBoxListener() {
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                @SuppressWarnings("PMD")
                @Override
                public void buttonClicked(final ButtonId buttonId) {
                    // Do Nothing
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
        } else {
            SearchResultsDTO searchForm = (SearchResultsDTO) resultTable.getValue();
            if (searchForm.isRecordLockStatus()) {
                if (itemStatusCheck()) {
                    editLogic();
                    resultTable.unselect(resultTable.getValue());
                } else {
                    final MessageBox msg = MessageBox.showPlain(Icon.INFO, "Access Denied", "You do not have permission to edit this record", new MessageBoxListener() {
                        /**
                         * The method is triggered when a button of the message
                         * box is pressed .
                         *
                         * @param buttonId The buttonId of the pressed button.
                         */
                        @SuppressWarnings("PMD")
                        @Override
                        public void buttonClicked(final ButtonId buttonId) {
                            // Do Nothing
                        }
                    }, ButtonId.OK);
                    msg.getButton(ButtonId.OK).focus();
                }
            } else {
                editLogic();
                resultTable.unselect(resultTable.getValue());
            }
        }
    }

    public void editLogic() {
        itemselectLogic(null);
        sessionDTO.setMode(ConstantsUtils.EDIT);
        if (ConstantUtil.DEDUCTION_CALENDAR.equals(moduleName)) {
            getUI().getNavigator().navigateTo(DeductionCalendarView.NAME);
        }
        if (ConstantUtil.COMPLIANCE_DEDUCTION_RULES.equals(moduleName)) {
            VaadinSession.getCurrent().setAttribute(ConstantsUtils.GLOBAL_FILES_MODE, ConstantUtil.EDIT);
            sessionDTO.setMode(ConstantsUtils.EDIT);
            getUI().getNavigator().navigateTo(CDRView.NAME);
        }
    }

    @UiHandler("viewBtn")
    public void btnViewLogic(Button.ClickEvent event) {

        if (resultTable.getValue() == null) {

            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "View Error", "Please select a record to view", new MessageBoxListener() {
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                @SuppressWarnings("PMD")
                @Override
                public void buttonClicked(final ButtonId buttonId) {
                    // Do Nothing
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
        } else {
            itemselectLogic(null);
            sessionDTO.setMode(ConstantsUtils.VIEW);
            if (ConstantUtil.DEDUCTION_CALENDAR.equals(moduleName)) {
                getUI().getNavigator().navigateTo(DeductionCalendarView.NAME);
            }
            if (ConstantUtil.COMPLIANCE_DEDUCTION_RULES.equals(moduleName)) {
                VaadinSession.getCurrent().setAttribute(ConstantsUtils.GLOBAL_FILES_MODE, ConstantUtil.VIEW_BTN);
                getUI().getNavigator().navigateTo(CDRView.NAME);
            }
        }
        resultTable.unselect(resultTable.getValue());
    }

    /**
     * Excel button logic.
     *
     * @param event the event
     */
    @UiHandler("excel")
    public void excelButtonLogic(Button.ClickEvent event) {
        try {
            binder.getErrorDisplay().clearError();
            binder.commit();
            searchLogic.excelExportLogic(moduleName, resultTable, this, binder);
        } catch (FieldGroup.CommitException | PortalException | SystemException | IllegalAccessException | NoSuchMethodException | InvocationTargetException commit) {
            LOGGER.error(commit.getMessage());
        }

    }

    /**
     * return the filter generator instance
     *
     * @param moduleName
     * @return
     */
    private ExtFilterGenerator getFilterGeneratorClass(String moduleName) {
        if (ConstantUtil.DEDUCTION_CALENDAR.equals(moduleName)) {
            return new DeductionFilterGenerator();
        } else if (ConstantUtil.COMPLIANCE_DEDUCTION_RULES.equals(moduleName)) {
            return new ComplianceDeductionFilterGenerator();
        }

        return null;

    }

    /**
     *
     * @param key
     */
    public void setTableDefaultConfig1(String key) {
        resultTable.setVisibleColumns(getColumns(true, ConstantUtil.ITEM_MASTER.equalsIgnoreCase(moduleName) || ConstantUtil.COMPANY_MAST.equalsIgnoreCase(moduleName) ? key : moduleName));
        resultTable.setColumnHeaders(Arrays.copyOf(getColumns(false, ConstantUtil.ITEM_MASTER.equalsIgnoreCase(moduleName) || ConstantUtil.COMPANY_MAST.equalsIgnoreCase(moduleName) ? key : moduleName), getColumns(false, ConstantUtil.ITEM_MASTER.equalsIgnoreCase(moduleName) || ConstantUtil.COMPANY_MAST.equalsIgnoreCase(moduleName) ? key : moduleName).length, String[].class));
        resultTable.markAsDirtyRecursive();
        resultTable.setImmediate(true);
        resultTable.setWidth(NumericConstants.HUNDRED, UNITS_PERCENTAGE);
    }

    @UiHandler("copyBtn")
    public void copyButtonLogic(Button.ClickEvent event) {
        VaadinSession.getCurrent().setAttribute(ConstantsUtils.GLOBAL_FILES_MODE, ConstantUtil.COPY);
        if (resultTable.getValue() == null) {
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "Copy Error", "Please select a record to Copy", new MessageBoxListener() {
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                @SuppressWarnings("PMD")
                @Override
                public void buttonClicked(final ButtonId buttonId) {
                    // Do Nothing
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
        } else if (ConstantUtil.DEDUCTION_CALENDAR.equals(moduleName)) {
            if (resultTable.getValue() == null) {
                final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "No Record Selected to Copy", "Please select a Deduction Calendar record. ", new MessageBoxListener() {
                    /**
                     * The method is triggered when a button of the message box
                     * is pressed .
                     *
                     * @param buttonId The buttonId of the pressed button.
                     */
                    @SuppressWarnings("PMD")
                    @Override
                    public void buttonClicked(final ButtonId buttonId) {
                        // Do Nothing
                    }
                }, ButtonId.OK);
                msg.getButton(ButtonId.OK).focus();
            } else {
                MessageBox.showPlain(Icon.QUESTION, "Copy Confirmation", "Are you sure you want to copy the selected Deduction Calendar record? ", new MessageBoxListener() {
                    /**
                     * The method is triggered when a button of the message box
                     * is pressed .
                     *
                     * @param buttonId The buttonId of the pressed button.
                     */
                    @SuppressWarnings("PMD")
                    @Override
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equalsIgnoreCase(ConstantUtil.YES_VARIABLE)) {
                            sessionDTO.setMode(ConstantUtil.COPY);
                            getUI().getNavigator().navigateTo(DeductionCalendarView.NAME);
                            resultTable.unselect(resultTable.getValue());
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);
            }

        } else if (ConstantUtil.COMPLIANCE_DEDUCTION_RULES.equals(moduleName)) {
            VaadinSession.getCurrent().setAttribute(ConstantsUtils.GLOBAL_FILES_MODE, ConstantUtil.COPY);
            sessionDTO.setMode(ConstantUtil.COPY);
            getUI().getNavigator().navigateTo(CDRView.NAME);
            resultTable.unselect(resultTable.getValue());
        } 
    }

    @UiHandler("deleteBtn")
    public void deleteBtnLogic(Button.ClickEvent event) {
        LOGGER.debug("deleteButtonClickLogic method Started ");
        if (ConstantUtil.COMPLIANCE_DEDUCTION_RULES.equals(moduleName)) {
            if (resultTable.getValue() == null) {
                final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "Delete Error", "Please select a record to Delete", new MessageBoxListener() {
                    /**
                     * The method is triggered when a button of the message box
                     * is pressed .
                     *
                     * @param buttonId The buttonId of the pressed button.
                     */
                    @SuppressWarnings("PMD")
                    @Override
                    public void buttonClicked(final ButtonId buttonId) {
                        // Do Nothing
                    }
                }, ButtonId.OK);
                msg.getButton(ButtonId.OK).focus();
            } else {
                final SearchResultsDTO searchForm = (SearchResultsDTO) resultTable.getValue();
                final MessageBox msg = MessageBox.showPlain(Icon.INFO, ConstantUtil.CONFORMATION_MSG, "Are you sure you want to Delete the selected record?. ", new MessageBoxListener() {
                    /**
                     * The method is triggered when a button of the message box
                     * is pressed .
                     *
                     * @param buttonId The buttonId of the pressed button.
                     */
                    @Override
                    public void buttonClicked(final ButtonId buttonId) {
                        // Do Nothing
                        try {
                            if (ConstantUtil.DEDUCTION_CALENDAR.equals(moduleName)) {
                                SelectionLogic selLogic = new SelectionLogic();
                                resultTable.removeItem(resultTable.getValue());
                                selLogic.deleteDedutionCalendar(Integer.parseInt(searchForm.getSystemID()));
                            }
                            if (ConstantUtil.COMPLIANCE_DEDUCTION_RULES.equals(moduleName)) {
                                CDRLogic cdrLogic = new CDRLogic();
                                resultTable.removeItem(resultTable.getValue());
                                cdrLogic.deleteCDRRecords(Integer.valueOf(searchForm.getSystemID()));
                            }
                        } catch (NumberFormatException e) {
                            LOGGER.error(e.getMessage());
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);
                msg.getButton(ButtonId.YES).focus();
            }
        } else if (ConstantUtil.DEDUCTION_CALENDAR.equals(moduleName)) {
            if (resultTable.getValue() == null) {
                final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "No Record Selected to Delete", "Please select an existing Deduction calendar record to delete. ", new MessageBoxListener() {
                    /**
                     * The method is triggered when a button of the message box
                     * is pressed .
                     *
                     * @param buttonId The buttonId of the pressed button.
                     */
                    @SuppressWarnings("PMD")
                    @Override
                    public void buttonClicked(final ButtonId buttonId) {
                        // Do Nothing
                    }
                }, ButtonId.OK);
                msg.getButton(ButtonId.OK).focus();
            } else {
                final SearchResultsDTO searchForm = (SearchResultsDTO) resultTable.getValue();
                final MessageBox msg = MessageBox.showPlain(Icon.INFO, ConstantUtil.DELETE_CONFORMATION, "Are you sure you want to delete record " + searchForm.getDeductionCalendarname() + "?", new MessageBoxListener() {
                    /**
                     * The method is triggered when a button of the message box
                     * is pressed .
                     *
                     * @param buttonId The buttonId of the pressed button.
                     */
                    @Override
                    public void buttonClicked(final ButtonId buttonId) {
                        try {
                            if (buttonId.name().equalsIgnoreCase(ConstantUtil.YES_VARIABLE)) {
                                SelectionLogic selLogic = new SelectionLogic();
                                resultTable.removeItem(resultTable.getValue());
                                selLogic.deleteDedutionCalendar(Integer.parseInt(searchForm.getSystemID()));
                            }
                        } catch (NumberFormatException e) {
                            LOGGER.error(e.getMessage());
                        }

                    }
                }, ButtonId.YES, ButtonId.NO);
                msg.getButton(ButtonId.YES).focus();
            }
        }
    }

    public void securityForAllScreens() throws PortalException, SystemException {
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent()
                .getAttribute(ConstantsUtils.USER_ID));

        if (ConstantUtil.COMPANY_MAST.equalsIgnoreCase(moduleName)) {

            final Map<String, AppPermission> functionCfpHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.COMPANY_MASTER + ConstantUtil.COMMA + ConstantsUtils.SEARCH_SCREEN);
            if (functionCfpHM.get(FunctionNameUtil.RESET) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.RESET)).isFunctionFlag()) {
                resetBtn.setVisible(false);
            }
            if (functionCfpHM.get(FunctionNameUtil.ADD) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.ADD)).isFunctionFlag()) {
                addBtn.setVisible(false);
            }
            if (functionCfpHM.get(FunctionNameUtil.EDIT) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.EDIT)).isFunctionFlag()) {
                editBtn.setVisible(false);
            }
            if (functionCfpHM.get(FunctionNameUtil.VIEW) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.VIEW)).isFunctionFlag()) {
                viewBtn.setVisible(false);
            }

        }
        if (ConstantUtil.ITEM_MASTER.equalsIgnoreCase(moduleName)) {
            final Map<String, AppPermission> functionCfpHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.ITEM_MASTER + ConstantUtil.COMMA + ConstantsUtils.SEARCH_SCREEN);
            if (functionCfpHM.get(FunctionNameUtil.RESET) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.RESET)).isFunctionFlag()) {
                resetBtn.setVisible(false);
            }
            if (functionCfpHM.get(FunctionNameUtil.ADD) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.ADD)).isFunctionFlag()) {
                addBtn.setVisible(false);
            }
            if (functionCfpHM.get(FunctionNameUtil.EDIT) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.EDIT)).isFunctionFlag()) {
                editBtn.setVisible(false);
            }
            if (functionCfpHM.get(FunctionNameUtil.VIEW) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.VIEW)).isFunctionFlag()) {
                viewBtn.setVisible(false);
            }
        }
        if (ConstantUtil.COMPANY_FAMILY_PLAN.equalsIgnoreCase(moduleName)) {
            final Map<String, AppPermission> functionCfpHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.COMPANY_FAMILY_PLAN + ConstantUtil.COMMA + ConstantsUtils.SEARCH_SCREEN);
            if (functionCfpHM.get(FunctionNameUtil.RESET) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.RESET)).isFunctionFlag()) {
                resetBtn.setVisible(false);
            }
            if (functionCfpHM.get(FunctionNameUtil.ADD) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.ADD)).isFunctionFlag()) {
                addBtn.setVisible(false);
            }
            if (functionCfpHM.get(FunctionNameUtil.EDIT) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.EDIT)).isFunctionFlag()) {
                editBtn.setVisible(false);
            }
            if (functionCfpHM.get(FunctionNameUtil.VIEW) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.VIEW)).isFunctionFlag()) {
                viewBtn.setVisible(false);
            }
        }
        if (ConstantUtil.ITEM_FAMILY_PLAN.equalsIgnoreCase(moduleName)) {
            final Map<String, AppPermission> functionCfpHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.ITEM_FAMILY_PLAN + ConstantUtil.COMMA + ConstantsUtils.SEARCH_SCREEN);
            if (functionCfpHM.get(FunctionNameUtil.RESET) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.RESET)).isFunctionFlag()) {
                resetBtn.setVisible(false);
            }
            if (functionCfpHM.get(FunctionNameUtil.ADD) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.ADD)).isFunctionFlag()) {
                addBtn.setVisible(false);
            }
            if (functionCfpHM.get(FunctionNameUtil.EDIT) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.EDIT)).isFunctionFlag()) {
                editBtn.setVisible(false);
            }
            if (functionCfpHM.get(FunctionNameUtil.VIEW) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.VIEW)).isFunctionFlag()) {
                viewBtn.setVisible(false);
            }
            if (functionCfpHM.get(ConstantUtil.DELETE_BUTTON) != null && !((AppPermission) functionCfpHM.get(ConstantUtil.DELETE_BUTTON)).isFunctionFlag()) {
                deleteBtn.setVisible(false);
            }
            if (functionCfpHM.get(ConstantUtil.COPY_BUTTON) != null && !((AppPermission) functionCfpHM.get(ConstantUtil.COPY_BUTTON)).isFunctionFlag()) {
                copyBtn.setVisible(false);
            }
            if (functionCfpHM.get(ConstantUtil.TABLE_RESET_BUTTON) != null && !((AppPermission) functionCfpHM.get(ConstantUtil.TABLE_RESET_BUTTON)).isFunctionFlag()) {
                reset.setVisible(false);
            }
        }
        if (ConstantUtil.PRICE_SCHEDULE_MASTER.equalsIgnoreCase(moduleName)) {
            final Map<String, AppPermission> functionCfpHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.PRICE_SCHEDULE + ConstantUtil.COMMA + ConstantsUtils.SEARCH_SCREEN);
            if (functionCfpHM.get(FunctionNameUtil.RESET) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.RESET)).isFunctionFlag()) {
                resetBtn.setVisible(false);
            }
            if (functionCfpHM.get(FunctionNameUtil.ADD) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.ADD)).isFunctionFlag()) {
                addBtn.setVisible(false);
            }
            if (functionCfpHM.get(FunctionNameUtil.EDIT) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.EDIT)).isFunctionFlag()) {
                editBtn.setVisible(false);
            }
            if (functionCfpHM.get(FunctionNameUtil.VIEW) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.VIEW)).isFunctionFlag()) {
                viewBtn.setVisible(false);
            }
            if (functionCfpHM.get(ConstantUtil.DELETE_BUTTON) != null && !((AppPermission) functionCfpHM.get(ConstantUtil.DELETE_BUTTON)).isFunctionFlag()) {
                deleteBtn.setVisible(false);
            }
            if (functionCfpHM.get(ConstantUtil.COPY_BUTTON) != null && !((AppPermission) functionCfpHM.get(ConstantUtil.COPY_BUTTON)).isFunctionFlag()) {
                copyBtn.setVisible(false);
            }
            if (functionCfpHM.get(ConstantUtil.TABLE_RESET_BUTTON) != null && !((AppPermission) functionCfpHM.get(ConstantUtil.TABLE_RESET_BUTTON)).isFunctionFlag()) {
                reset.setVisible(false);
            }
        }
        if (ConstantUtil.REBATE_SCHEDULE_MASTER.equalsIgnoreCase(moduleName)) {
            final Map<String, AppPermission> functionCfpHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.REBATE_SCHEDULE + ConstantUtil.COMMA + ConstantsUtils.SEARCH_SCREEN);
            if (functionCfpHM.get(FunctionNameUtil.RESET) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.RESET)).isFunctionFlag()) {
                resetBtn.setVisible(false);
            }
            if (functionCfpHM.get(FunctionNameUtil.ADD) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.ADD)).isFunctionFlag()) {
                addBtn.setVisible(false);
            }
            if (functionCfpHM.get(FunctionNameUtil.EDIT) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.EDIT)).isFunctionFlag()) {
                editBtn.setVisible(false);
            }
            if (functionCfpHM.get(FunctionNameUtil.VIEW) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.VIEW)).isFunctionFlag()) {
                viewBtn.setVisible(false);
            }
            if (functionCfpHM.get(ConstantUtil.DELETE_BUTTON) != null && !((AppPermission) functionCfpHM.get(ConstantUtil.DELETE_BUTTON)).isFunctionFlag()) {
                deleteBtn.setVisible(false);
            }
            if (functionCfpHM.get(ConstantUtil.COPY_BUTTON) != null && !((AppPermission) functionCfpHM.get(ConstantUtil.COPY_BUTTON)).isFunctionFlag()) {
                copyBtn.setVisible(false);
            }
            if (functionCfpHM.get(ConstantUtil.TABLE_RESET_BUTTON) != null && !((AppPermission) functionCfpHM.get(ConstantUtil.TABLE_RESET_BUTTON)).isFunctionFlag()) {
                reset.setVisible(false);
            }
        }
        if (ConstantUtil.REBATE_PLAN.equalsIgnoreCase(moduleName)) {
            final Map<String, AppPermission> functionCfpHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.REBATE_PLAN + ConstantUtil.COMMA + ConstantsUtils.SEARCH_SCREEN);
            if (functionCfpHM.get(FunctionNameUtil.RESET) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.RESET)).isFunctionFlag()) {
                resetBtn.setVisible(false);
            }
            if (functionCfpHM.get(FunctionNameUtil.ADD) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.ADD)).isFunctionFlag()) {
                addBtn.setVisible(false);
            }
            if (functionCfpHM.get(FunctionNameUtil.EDIT) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.EDIT)).isFunctionFlag()) {
                editBtn.setVisible(false);
            }
            if (functionCfpHM.get(FunctionNameUtil.VIEW) != null && !((AppPermission) functionCfpHM.get(FunctionNameUtil.VIEW)).isFunctionFlag()) {
                viewBtn.setVisible(false);
            }
        }

        if (ConstantUtil.COMPLIANCE_DEDUCTION_RULES.equalsIgnoreCase(moduleName)) {
            final Map<String, AppPermission> functionCfpHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.COMPLIANCE_DEDUCTION_RULES + ConstantUtil.COMMA + "Compliance Deduction Landing Screen");
            if (functionCfpHM.get(ConstantUtil.RESET_BUTTON) != null && !((AppPermission) functionCfpHM.get(ConstantUtil.RESET_BUTTON)).isFunctionFlag()) {
                resetBtn.setVisible(false);
            }
            if (functionCfpHM.get(ConstantUtil.ADD_BUTTON) != null && !((AppPermission) functionCfpHM.get(ConstantUtil.ADD_BUTTON)).isFunctionFlag()) {
                addBtn.setVisible(false);
            }
            if (functionCfpHM.get(ConstantUtil.EDIT_BUTTON) != null && !((AppPermission) functionCfpHM.get(ConstantUtil.EDIT_BUTTON)).isFunctionFlag()) {
                editBtn.setVisible(false);
            }
            if (functionCfpHM.get(ConstantUtil.VIEW_BUTTON) != null && !((AppPermission) functionCfpHM.get(ConstantUtil.VIEW_BUTTON)).isFunctionFlag()) {
                viewBtn.setVisible(false);
            }
            if (functionCfpHM.get(ConstantUtil.SEARCH_BUTTON) != null && !((AppPermission) functionCfpHM.get(ConstantUtil.SEARCH_BUTTON)).isFunctionFlag()) {
                searchBtn.setVisible(false);
            }
            if (functionCfpHM.get(ConstantUtil.DELETE_BUTTON) != null && !((AppPermission) functionCfpHM.get(ConstantUtil.DELETE_BUTTON)).isFunctionFlag()) {
                deleteBtn.setVisible(false);
            }
            if (functionCfpHM.get(ConstantUtil.COPY_BUTTON) != null && !((AppPermission) functionCfpHM.get(ConstantUtil.COPY_BUTTON)).isFunctionFlag()) {
                copyBtn.setVisible(false);
            }
            if (functionCfpHM.get(ConstantUtil.TABLE_RESET_BUTTON) != null && !((AppPermission) functionCfpHM.get(ConstantUtil.TABLE_RESET_BUTTON)).isFunctionFlag()) {
                reset.setVisible(false);
            }
            if (functionCfpHM.get("exportBtn") != null && !((AppPermission) functionCfpHM.get("exportBtn")).isFunctionFlag()) {
                exportBtn.setVisible(false);
            }
        }
    }
}
