package com.stpl.app.contract.dashboard.ui.form;

import com.stpl.app.contract.abstractsearch.util.ConstantUtil;
import com.stpl.app.contract.common.util.CommonUtil;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

import com.stpl.app.contract.contractheader.dto.ContractAliasMasterDTO;
import com.stpl.app.contract.contractheader.logic.ContractHeaderLogic;
import com.stpl.app.contract.contractheader.ui.form.TradingPartnerLookUp;
import com.stpl.app.contract.contractheader.util.UIUtils;
import com.stpl.app.contract.util.AbstractNotificationUtils;
import com.stpl.app.contract.util.CommonUIUtils;
import com.stpl.app.contract.util.Constants;
import com.stpl.app.contract.util.ErrorCodeUtil;
import com.stpl.app.contract.util.ErrorCodes;
import com.stpl.app.contract.util.ResponsiveUtils;

import com.stpl.app.security.impl.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.CustomePagedFilterTable;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.app.contract.util.CHFunctionNameUtils;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.TabNameUtil;
import com.stpl.ifs.util.TableResultCustom;
import com.stpl.ifs.util.UISecurityUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
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
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.lang.reflect.InvocationTargetException;
import org.jboss.logging.Logger;

/**
 * This class used for Alias Tab.
 *
 * @author gopinath
 */
public final class AliasTab extends CustomComponent implements View {

    TradingPartnerLookUp lookUp = null;

    @UiField("aliasResultsTable")
    private CustomePagedFilterTable table;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(AliasTab.class);
    /**
     * The space.
     */
    private final Label space = new Label(Constants.SPACE, ContentMode.HTML);
    /**
     * The error msg.
     */
    @UiField("errorLB")
    private ErrorLabel errorMsg;
    /**
     * The cpntract header logic.
     */
    private final ContractHeaderLogic contractLogic = new ContractHeaderLogic();
    /**
     * The binder.
     */
    private CustomFieldGroup binder = new CustomFieldGroup(new BeanItem<>(new ContractAliasMasterDTO()));
    /**
     * The alias results bean.
     */
    private final BeanItemContainer<ContractAliasMasterDTO> aliasResultsBean;

    @UiField("cssLayout")
    private CssLayout cssLayout;

    @UiField("aliasInfoLayout")
    private VerticalLayout aliasInfoLayout;

    @UiField("aliasTableLayout")
    private VerticalLayout aliasTableLayout;

    @UiField("aliasPanel")
    private Panel aliasPanel;

    /**
     * The contract alias no.
     */
    @UiField("contractAliasNo")
    private TextField contractAliasNo;

    /**
     * The contract alias name.
     */
    @UiField("contractAliasName")
    private TextField contractAliasName;

    /**
     * The trading name.
     */
    @UiField("tradingName")
    private CustomTextField tradingName;

    /**
     * The trading partner.
     */
    private final TextField tradingPartner = new TextField();

    /**
     * The start date.
     */
    @UiField("startDate")
    private PopupDateField startDate;

    /**
     * The end date.
     */
    @UiField("endDate")
    private PopupDateField endDate;

    /**
     * The contract alias type.
     */
    @UiField("contractAliasType")
    private ComboBox contractAliasType;

    @UiField("tradingPartnerLabel")
    private Label tradingPartnerLabel;

    @UiField("contractAliasNoLabel")
    private Label contractAliasNoLabel;

    @UiField("startDateLabel")
    private Label startDateLabel;

    @UiField("contractAliasTypeLabel")
    private Label contractAliasTypeLabel;

    @UiField("contractAliasNameLabel")
    private Label contractAliasNameLabel;

    @UiField("endDateLabel")
    private Label endDateLabel;

    @UiField("aliasAdd")
    private Button btnAdd;

    @UiField("aliasRemove")
    private Button btnRemove;

    @UiField("tablePanel")
    private Panel tablePanel;

    @UiField("mainLayout")
    private VerticalLayout mainLayout;

    @UiField("errorLB")
    private ErrorLabel errorLabel;

    CommonUIUtils commonUIUtils = new CommonUIUtils();
    CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();
    /**
     * Date Formatter
     */
    final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    final boolean isEditable;

    /**
     * The id.
     *
     * @return the space
     */
    private Object idValue;
    /**
     * The contract alias click dto.
     */
    public ContractAliasMasterDTO contractAliasDTO = new ContractAliasMasterDTO();

    /**
     * Purposely for Error Display
     */
    private CustomFieldGroup systemBinder;
    /**
     * Excel Icon
     */
    final Button excelIcon = new Button(StringUtils.EMPTY);

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
     * Gets the contract logic.
     *
     * @return the contract logic
     */
    public ContractHeaderLogic getContractLogic() {
        return contractLogic;
    }

    /**
     * Gets the alias results bean.
     *
     * @return the alias results bean
     */
    public BeanItemContainer<ContractAliasMasterDTO> getAliasResultsBean() {
        return aliasResultsBean;
    }

    /**
     * Gets the contract alias no.
     *
     * @return the contract alias no
     */
    public TextField getContractAliasNo() {
        return contractAliasNo;
    }

    /**
     * Gets the contract alias name.
     *
     * @return the contract alias name
     */
    public TextField getContractAliasName() {
        return contractAliasName;
    }

    /**
     * Gets the trading name.
     *
     * @return the trading name
     */
    public CustomTextField getTpCompanyMasterSid() {
        return tradingName;
    }

    /**
     * Gets the trading partner.
     *
     * @return the trading partner
     */
    public TextField getTradingPartner() {
        return tradingPartner;
    }

    /**
     * Gets the start date.
     *
     * @return the start date
     */
    public PopupDateField getStartDate() {
        return startDate;
    }

    /**
     * Gets the end date.
     *
     * @return the end date
     */
    public PopupDateField getEndDate() {
        return endDate;
    }

    /**
     * Gets the contract alias type.
     *
     * @return the contract alias type
     */
    public ComboBox getContractAliasType() {
        return contractAliasType;
    }

    /**
     * Gets the id value.
     *
     * @return the id value
     */
    public Object getIdValue() {
        return idValue;
    }

    /**
     * Sets the id value.
     *
     * @param idValue the id value
     */
    public void setIdValue(final Object idValue) {
        this.idValue = idValue;
    }

    /**
     * Gets the contract alias dto.
     *
     * @return the contract alias dto
     */
    public ContractAliasMasterDTO getContractAliasDTO() {
        return contractAliasDTO;
    }

    /**
     * Sets the contract alias dto.
     *
     * @param contractAliasDTO the contract alias dto
     */
    public void setContractAliasDTO(final ContractAliasMasterDTO contractAliasDTO) {
        this.contractAliasDTO = contractAliasDTO;
    }

    /**
     * Sets the binder.
     *
     * @param binder the binder
     */
    public void setBinder(final CustomFieldGroup binder) {
        this.binder = binder;
    }

    public CustomFieldGroup getSystemBinder() {
        return systemBinder;
    }

    public void setSystemBinder(CustomFieldGroup systemBinder) {
        this.systemBinder = systemBinder;
    }
    /**
     * The common util.
     */
    private CommonUtil commonUtil = CommonUtil.getInstance();

    /**
     * The Constructor for AliasTab Class.
     *
     * @param aliasResultsBean the alias results bean
     */
    public AliasTab(final CustomFieldGroup systemBinder, final BeanItemContainer<ContractAliasMasterDTO> aliasResultsBean, final boolean isEditable) throws SystemException, PortalException{
        super();
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/declarative-ui/contract-dashboard/contract-alias.xml"), this));
        this.systemBinder = systemBinder;
        this.aliasResultsBean = aliasResultsBean;
        this.binder = getBinder();
        this.isEditable = isEditable;
        LOGGER.debug("Entering AliasTab");
        init();
        LOGGER.debug("End of AliasTab");
    }

    /**
     * Inits method load while calling Constructor.
     */
    private void init() throws SystemException, PortalException {
        LOGGER.debug("Entering init method");
        space.setHeight("20");
        binder.bindMemberFields(this);
        if (!isEditable) {
            addToContentForView();
        } else {
            addToContent();
            configureFields();
        }
        LOGGER.debug("End of init method");
    }

    /**
     * Gets the binder used to bind dto and fields .
     *
     * @return the binder
     */
    private CustomFieldGroup getBinder() {
        LOGGER.debug("Entering getBinder method");
        final ContractAliasMasterDTO bean = new ContractAliasMasterDTO();
        final CustomFieldGroup binder = new CustomFieldGroup(new BeanItem<>(bean));
        binder.setBuffered(true);
        binder.bindMemberFields(this);
        binder.setErrorDisplay(errorMsg);
        LOGGER.debug("End of getBinder method");
        return binder;
    }

    /**
     *
     */
    private void addToContentForView() {
        addToTable();
        table.setReadOnly(true);
        btnRemove.setVisible(false);
        aliasInfoLayout.setVisible(false);
        aliasPanel.setVisible(false);
    }

    /**
     * Method used for Add to content.
     *
     * @return the vertical layout
     */
    public void addToContent() throws PortalException, SystemException {
        LOGGER.debug("Entering addToContent method");
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final Map<String, AppPermission> contractDashboard = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.CONTRACT_DASHBOARD + Constants.COMMA + Constants.CONTRACT_ALAIS, false);
        final Map<String, AppPermission> funContractDashboard = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.CONTRACT_DASHBOARD + Constants.COMMA + Constants.CONTRACT_ALAIS);
        addResponsiveGrid(contractDashboard);
        if (!(funContractDashboard.get(CHFunctionNameUtils.ALIAS_POPULATE) != null) && !((AppPermission) funContractDashboard.get(CHFunctionNameUtils.ALIAS_POPULATE)).isFunctionFlag()) {
            btnAdd.setVisible(false);
        } else {
            populateButton();
        }
        removeButton();
        addToTable();
        LOGGER.debug("End of addToContent method");
    }

    private void addResponsiveGrid(final Map<String, AppPermission> contractDashboard) {

        LOGGER.debug("Entering configurePermission");
        try {

            List<Object> resultList = contractLogic.getFieldsForSecurity(UISecurityUtil.CONTRACT_DASHBOARD, Constants.CONTRACT_ALAIS);

            commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout, contractDashboard, Constants.EDIT);

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Method used for Adds the table.
     *
     * @return the table
     */
    private void addToTable() {

        try {
            LOGGER.debug("Entering addToTable method");
            final ContractHeaderLogic contractLogic = new ContractHeaderLogic();
            final StplSecurity stplSecurity = new StplSecurity();
            String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
            final Map<String, AppPermission> contractDashboard = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.CONTRACT_DASHBOARD + Constants.COMMA + Constants.CONTRACT_ALAIS, false);
            List<Object> resultList = contractLogic.getFieldsForSecurity(UISecurityUtil.CONTRACT_DASHBOARD, Constants.CONTRACT_ALAIS);
            Object[] obj = UIUtils.getInstance().dashboardAliasColumns;
            TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, contractDashboard, Constants.EDIT);
            if (tableResultCustom.getObjResult().length > 0) {

                table.setContainerDataSource(aliasResultsBean);
                table.setVisibleColumns(tableResultCustom.getObjResult());
                table.setColumnHeaders(tableResultCustom.getObjResultHeader());
            } else {
                tablePanel.setVisible(false);
                btnRemove.setVisible(false);
            }
            table.setPageLength(NumericConstants.FIVE);
            table.sinkItemPerPageWithPageLength(false);
            table.setImmediate(true);
            table.addStyleName("valo-theme-extfiltertable");
            table.addStyleName("filterbar");
            table.setFilterBarVisible(true);
            table.addStyleName("TableCheckBox");
            table.setFilterDecorator(new ExtDemoFilterDecorator());
            table.setSizeFull();
            table.setSelectable(true);
            table.setColumnCollapsingAllowed(true);
            table.addItemClickListener(new ItemClickListener() {
                /**
                 * Method used for item click event in the table
                 */
                @SuppressWarnings("PMD")
                public void itemClick(final ItemClickEvent event) {
                    idValue = event.getItemId();
                    contractAliasDTO = (ContractAliasMasterDTO) aliasResultsBean.getItem(event.getItemId()).getBean();
                }
            });
            LOGGER.debug("End of addToTable method");
            HorizontalLayout tempLayout = ResponsiveUtils.getResponsiveControls(table.createControls());
            aliasTableLayout.addComponent(tempLayout);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

    }

    private void configureFields() {
        LOGGER.debug("Entering configureFields method");

        startDate.setValidationVisible(true);
        startDate.setImmediate(true);
        startDate.setDateFormat(Constants.MM_DD_YYYY);
        startDate.setDescription(Constants.DATE);
        startDate.setId("AliasStartDate");
        startDate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used for start date value change listener in UI
             */
            @SuppressWarnings("PMD")
            @Override
            public void valueChange(final ValueChangeEvent event) {
                startDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(startDate.getValue()));
            }
        });
        endDate.setImmediate(true);
        endDate.setValidationVisible(true);
        endDate.setDateFormat(Constants.MM_DD_YYYY);
        endDate.setDescription(Constants.DATE);
        endDate.addValidator(new DateValidatorContract("End Date should be greater than Start Date in Alias Tab"));
        endDate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used for end date value change listener in UI
             */
            @Override
            public void valueChange(final ValueChangeEvent event) {
                endDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(endDate.getValue()));
            }
        });
        contractAliasNo.setImmediate(true);
        contractAliasNo.setValidationVisible(true);
        contractAliasNo.addValidator(new StringLengthValidator("Contract Alias No should be less than 38 characters", 0, NumericConstants.THIRTY_EIGHT, true));
        contractAliasNo.addValidator(new RegexpValidator("([0-9|a-z|A-Z|\\_|\\*|\\:|\\.|\\,|\\'|\\;|\\-|\\ |\\/])*", "Contract Alias No allowed Special Characters are *,:,.,(,),',;,-,/,_"));
        contractAliasNo.setDescription(contractAliasNo.getValue());
        contractAliasNo.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used for Value Change listener for Contract Alias NO
             */
            @Override
            public void valueChange(final ValueChangeEvent event) {
                contractAliasNo.setDescription(contractAliasNo.getValue());
            }
        });
        /**
         * Method used for Value Change listener for Contract Alias Name
         */
        contractAliasName.setImmediate(true);
        contractAliasName.setDescription(contractAliasName.getValue());
        contractAliasName.setValidationVisible(true);
        contractAliasName.addValidator(new StringLengthValidator("Contract Alias Name length should be less than 50 characters", 0,NumericConstants.FIFTY, true));
        contractAliasName.addValidator(new RegexpValidator("([0-9|a-z|A-Z|\\_|\\*|\\:|\\.|\\,|\\'|\\;|\\-|\\ |\\/])*", "Contract ID allows Special Characters like *,:,.,(,),',;,-,/,_"));
        contractAliasName.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(final ValueChangeEvent event) {
                contractAliasName.setDescription(contractAliasName.getValue());
            }
        });

        tradingName.addValidator(new StringLengthValidator("Trading Partner Name should be less than 100 characters", 0,NumericConstants.HUNDRED, true));
        tradingName.addValidator(new RegexpValidator("([0-9|a-z|A-Z|\\ |\\_|\\*|\\:|\\.|\\,|\\'|\\;|\\-|\\\"])*", "Trading Partner Name  can contain only digits,alphabets"));
        tradingName.setValidationVisible(true);
        tradingName.setImmediate(true);
        tradingName.setDescription(tradingName.getValue());
        tradingName.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used for Value Change listener for Trading name
             */
            @Override
            public void valueChange(final ValueChangeEvent event) {
                tradingName.setDescription(tradingName.getValue());
            }
        });
        tradingName.addStyleName(Constants.SEARCH_ICON_STYLE);

        tradingName.addClickListener(new CustomTextField.ClickListener() {
            /**
             * Method used for focus listener for Trading name
             */
            @Override
            public void click(final CustomTextField.ClickEvent event) {
                if (lookUp == null) {
                    lookUp = new TradingPartnerLookUp(tradingPartner, tradingName);
                    UI.getCurrent().addWindow(lookUp);
                    lookUp.addCloseListener(new Window.CloseListener() {
                        /**
                         * Method used to Trading name and its listener
                         */
                        @SuppressWarnings("PMD")
                        public void windowClose(final Window.CloseEvent e) {
                            contractAliasName.focus();
                            lookUp = null;
                        }
                    });
                }

            }
        });

        commonUtil.loadComboBox(contractAliasType, UIUtils.CONTRACT_ALIAS_TYPE, false);

        binder.getField("tradingName").focus();
        LOGGER.debug("End of configureFields method");

        excelIcon.setIcon(new ThemeResource("../../icons/excel.png"));
        excelIcon.setStyleName("link");
        excelIcon.setDescription("Export to excel");
        excelIcon.setIconAlternateText("Excel export");
        excelIcon.setHtmlContentAllowed(true);
        excelIcon.setErrorHandler(new ErrorHandler() {

            /**
             * Invoked when an error occurs
             *
             * @param event - ErrorEvent
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                return;
            }
        });
        excelIcon.addClickListener(new ClickListener() {
            /**
             * calls excelExportLogic method on button click
             *
             * @param event - Mouse Click event
             */
            public void buttonClick(final ClickEvent event) {
                try {
                    LOGGER.debug("Entering EXCEL Export Button Click :::: ");
                    binder.getFields();
                    excelExportLogic();
                    LOGGER.debug(" Ends  EXCEL Export Button Click ::::  ");

                } catch (SystemException sysException) {
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(sysException);
                    LOGGER.error(errorMsg);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                } catch (PortalException portException) {
                    LOGGER.error(portException);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1011));
                } catch (Exception exception) {
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1011));
                    LOGGER.error(exception);

                } 
            }
        });

    }

    public void excelExportLogic() throws SystemException, PortalException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        LOGGER.debug("Entering excelExportLogic");
        createWorkSheet();
        LOGGER.debug("Ending excelExportLogic");
    }

    public void createWorkSheet() throws SystemException, PortalException, NoSuchMethodException, IllegalAccessException, InvocationTargetException  {
        LOGGER.debug("Entering createWorkSheet");
        final long recordCount = table.getContainerDataSource().size();
        ExcelExportforBB.createWorkSheet(table.getColumnHeaders(), recordCount, this, getUI(), TabNameUtil.Alias);
        LOGGER.debug("Ending createWorkSheet");
    }

    /**
     * Logic for creating worksheet content.
     *
     * @param start the start
     * @param end the end
     * @param printWriter the print writer
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public void createWorkSheetContent(final PrintWriter printWriter) {
        ContractAliasMasterDTO dto;
        final List<ContractAliasMasterDTO> searchList = aliasResultsBean.getItemIds();
        for (int rowCount = 0; rowCount < searchList.size(); rowCount++) {

            dto = searchList.get(rowCount);
            printWriter.print(UIUtils.QUOTE + dto.getContractAliasType().getDescription() + UIUtils.QUOTE + UIUtils.COMMA);

            printWriter.print(UIUtils.QUOTE + dto.getTpCompanyMasterSid() + UIUtils.QUOTE + UIUtils.COMMA);

            printWriter.print(UIUtils.QUOTE + dto.getContractAliasNo() + UIUtils.QUOTE + UIUtils.COMMA);

            printWriter.print(UIUtils.QUOTE + dto.getContractAliasName() + UIUtils.QUOTE + UIUtils.COMMA);


            printWriter.print(UIUtils.QUOTE + dto.getStartDate() + UIUtils.QUOTE + UIUtils.COMMA);

            printWriter.println(UIUtils.QUOTE + dto.getEndDate() + UIUtils.QUOTE);

        }

    }

    /**
     * Populate button used for update the value.
     *
     * @return the button
     */
    public void populateButton() {

        LOGGER.debug("Entering populateButton method");

        btnAdd.setImmediate(true);
        btnAdd.setWidth("85");
        btnAdd.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs.
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                return;
            }
        });
        btnAdd.addClickListener(new ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            @Override
            public void buttonClick(final ClickEvent event) {
                try {
                    LOGGER.debug("Entering btnPopulaooote buttonClick method ");
                    systemBinder.getErrorDisplay().clearError();
                    binder.getErrorDisplay().clearError();
                    binder.getFields();
                    binder.commit();
                    final ContractAliasMasterDTO identForm = new ContractAliasMasterDTO();
                    if (binder.getField(Constants.CONTRACT_ALIAS_TYPE).getValue() == null
                            || ((com.stpl.ifs.util.HelperDTO) binder.getField(Constants.CONTRACT_ALIAS_TYPE).getValue()).getId() == 0) {
                        systemBinder.getErrorDisplay().setError("Contract Alias Type is Mandatory");
                        return;
                    } else {

                        identForm.setContractAliasType((HelperDTO) (binder.getField(Constants.CONTRACT_ALIAS_TYPE).getValue()));

                        identForm.setContractAliasDesc(String.valueOf(contractAliasType.getItemCaption(contractAliasType.getValue())));
                    }
                    if (StringUtils.isEmpty(binder.getField(Constants.CONTRACT_ALIAS_NO).getValue().toString())) {

                        systemBinder.getErrorDisplay().setError("Contract Alias No is Mandatory");
                        return;
                    } else {
                        identForm.setContractAliasNo(String.valueOf(binder.getField(Constants.CONTRACT_ALIAS_NO).getValue()));
                    }

                    identForm.setContractAliasType((HelperDTO) (binder.getField(Constants.CONTRACT_ALIAS_TYPE).getValue()));
                    identForm.setContractAliasNo(String.valueOf(binder.getField(Constants.CONTRACT_ALIAS_NO).getValue()).trim());
                    identForm.setContractAliasName(String.valueOf(binder.getField(Constants.CONTRACT_ALIAS_NAME).getValue()).trim());
                    if (binder.getField(Constants.START_DATE).getValue() == null) {
                        systemBinder.getErrorDisplay().setError(ConstantUtil.ALIAS_START_DATE_SHOULD_BE_SELECTED);
                        return;
                    } else {
                        identForm.setStartDate(String.valueOf(dateFormat.format((Date) binder.getField(Constants.START_DATE).getValue())));
                    }
                    if (binder.getField(Constants.END_DATE).getValue() != null) {
                        identForm.setEndDate(String.valueOf(dateFormat.format((Date) binder.getField(Constants.END_DATE).getValue())));
                    }
                    identForm.setContractAliasType((HelperDTO) (binder.getField(Constants.CONTRACT_ALIAS_TYPE).getValue()));
                    identForm.setContractAliasDesc(identForm.getContractAliasType().getDescription());
                    if (binder.getField(Constants.TRADING_PARTNER).getValue() != null) {
                        final String tradingId = String.valueOf(binder.getField(Constants.TRADING_PARTNER).getValue()).replaceAll(Constants.COMMA, StringUtils.EMPTY);
                        identForm.setTradingPartner(Integer.parseInt(tradingId));
                        identForm.setTpCompanyMasterSid(String.valueOf(binder.getField(Constants.TRADING_NAME).getValue()).trim());
                        identForm.setTradingName(String.valueOf(binder.getField(Constants.TRADING_NAME).getValue()).trim());
                    }
                    table.clearFilters();
                    aliasResultsBean.addBean(identForm);
                    binder = getBinder();
                    LOGGER.debug("End of btnPopulate buttonClick method ");
                } catch (CommitException ex) {
                    LOGGER.error(ex);
                    if (ex.getCause().getMessage().equals("Contract Alias No is Mandatory in Alias Tab")) {
                        binder.getErrorDisplay().clearError();
                        systemBinder.getErrorDisplay().setError("Contract Alias No is Mandatory in Alias Tab");
                        return;
                    }
                    if (ex.getCause().getMessage().equals("End Date should be greater than Start Date")) {
                        binder.getErrorDisplay().clearError();
                        systemBinder.getErrorDisplay().setError("End Date should be greater than Start Date in Alias Tab");
                        return;
                    }
                    if (ex.getCause().getMessage().equals("Contract Alias No should be less than 38 characters")) {
                        binder.getErrorDisplay().clearError();
                        systemBinder.getErrorDisplay().setError("Contract Alias No should be less than 38 characters in Alias Tab");
                        return;
                    }
                    if (ex.getCause().getMessage().equals("Contract Alias No allowed Special Characters are *,:,.,(,),',;,-,/,_")) {
                        binder.getErrorDisplay().clearError();
                        systemBinder.getErrorDisplay().setError("Contract Alias No allowed Special Characters are *,:,.,(,),',;,-,/,_ in Alias Tab");
                        return;
                    }
                    if (ex.getCause().getMessage().equals("Contract Alias Name length should be less than 50 characters")) {
                        binder.getErrorDisplay().clearError();
                        systemBinder.getErrorDisplay().setError("Contract Alias Name length should be less than 50 characters in Alias Tab");
                        return;
                    }
                    if (ex.getCause().getMessage().equals("Contract Alias Name allows Special Characters like *,:,.,(,),',;,-,/,_")) {
                        binder.getErrorDisplay().clearError();
                        systemBinder.getErrorDisplay().setError("Contract ID allows Special Characters like *,:,.,(,),',;,-,/,_ in Alias Tab");
                        return;
                    }
                    if (ex.getCause().getMessage().equals(ConstantUtil.ALIAS_START_DATE_SHOULD_BE_SELECTED)) {
                        binder.getErrorDisplay().clearError();
                        systemBinder.getErrorDisplay().setError(ConstantUtil.ALIAS_START_DATE_SHOULD_BE_SELECTED);
                        return;
                    }
                    if (ex.getCause().getMessage().equals("Contract Alias Type should be selected on Alias tab")) {
                        binder.getErrorDisplay().clearError();
                        systemBinder.getErrorDisplay().setError("Contract Alias Type should be selected on Alias tab");
                        return;
                    }
                }
            }
            
        });
        LOGGER.debug("End of populateButton method");
    }

    /**
     * Removes button used for remove the value.
     *
     * @return the button
     */
    public Button removeButton() {
        LOGGER.debug("Entering removeButton method");

        btnRemove.setImmediate(true);
        btnRemove.setWidth("85");
        btnRemove.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs.
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                return;
            }
        });
        btnRemove.addClickListener(new ClickListener() {
            /**
             * Method used for Click event for Remove button
             */
            public void buttonClick(final ClickEvent event) {
                if (table.getValue() != null) {
                    LOGGER.debug(" buttonClick(ClickEvent event) name=" + event.getButton().getCaption());
                    MessageBox.showPlain(Icon.QUESTION, commonUtil.getHeaderMessage(), commonUtil.getRemoveComponentMessage(), new MessageBoxListener() {
                        /**
                         * Called when a Button has been clicked .
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            if (buttonId.name().equals(Constants.YES)) {
                                try {
                                    aliasResultsBean.removeItem(idValue);
                                    idValue = null;
                                    table.setValue(null);
                                } catch (Exception exception) {
                                    LOGGER.error(exception);

                                }
                            }
                        }
                    }, ButtonId.YES, ButtonId.NO);
                } else {
                    MessageBox.showPlain(Icon.ERROR, "No Records Selected", "Please select an alias to remove.", ButtonId.OK);
                }

            }
        });
        LOGGER.debug("End of removeButton method");
        return btnRemove;
    }

    /**
     * This Class used for Date Validate Contract.
     */
    @SuppressWarnings("rawtypes")
    public class DateValidatorContract extends AbstractValidator {

        /**
         * The Constructor for Date Validate Contract.
         */
        public DateValidatorContract() {
            super(StringUtils.EMPTY);
        }

        /**
         * The Constructor used for Date Validate Contract..
         *
         * @param errorMessage the error message
         */
        public DateValidatorContract(final String errorMessage) {
            super(errorMessage);
        }

        /**
         * Method used for validation.
         *
         * @param value the value
         * @throws InvalidValueException the invalid value exception
         */
        @Override
        public void validate(final Object value) {
            LOGGER.debug("Entering validate method");

            if (startDate.getValue() != null && endDate.getValue() != null) {
                if (startDate.getValue().after(endDate.getValue())) {
                    throw new InvalidValueException("End Date should be greater than Start Date");
                } else if (startDate.getValue().equals(endDate.getValue())) {
                    throw new InvalidValueException("Start date and End date are equal");
                }
            }
            LOGGER.debug("End of validate method");
        }

        /**
         * Method return boolean value of validation.
         *
         * @param value the value
         * @return true, if is valid value
         */
        @Override
        protected boolean isValidValue(final Object value) {
            LOGGER.debug("Entering isValidValue method");
            if (startDate.getValue() != null && endDate.getValue() != null) {
                return startDate.getValue().compareTo(endDate.getValue()) <= 0;
            }
            LOGGER.debug("End of isValidValue method");
            return true;
        }

        /**
         * Method used for get Type.
         *
         * @return the type
         */
        @Override
        public Class getType() {
            return null;
        }
    }

    /**
     * This method is always called before the view is shown on screen.
     *
     * @param event the event
     */
    @Override
    public void enter(final ViewChangeEvent event) {
        return;
    }

    private void setDefaultResolution(CustomePagedFilterTable table) {
        int browserWidth = Page.getCurrent().getBrowserWindowWidth();
        if (browserWidth > NumericConstants.ONE_FIVE_ONE_SIX) {
            getCollapsibleColumnsDefault(table);
        } else if (browserWidth < NumericConstants.ONE_FIVE_ONE_SIX && browserWidth > NumericConstants.THOUSAND_THREE_HUNDRED) {
            getCollapsibleColumnsDefault(table);
        } else if (browserWidth < NumericConstants.THOUSAND_THREE_HUNDRED && browserWidth > NumericConstants.ONE_ZERO_TWO_FOUR) {
            getCollapsibleColumnsDefault(table);
        } else if (browserWidth < NumericConstants.ONE_ZERO_TWO_FOUR && browserWidth > NumericConstants.NINE_SEVEN_EIGHT) {
            getCollapsibleColumns978Px(table);
        } else if (browserWidth < NumericConstants.NINE_SEVEN_EIGHT && browserWidth > NumericConstants.EIGHT_HUNDRED) {
            if (table.getItemIds().isEmpty()) {
                getCollapsibleColumns978Px(table);
            } else {
                getCollapsibleColumnsTwoData(table);
            }
        } else if (browserWidth < NumericConstants.EIGHT_HUNDRED && browserWidth > NumericConstants.FOUR_EIGHT_ZERO) {
            if (table.getItemIds().isEmpty()) {
                getCollapsibleColumns978Px(table);
            } else {
                getCollapsibleColumnsTwoData(table);
            }
        } else if (browserWidth < NumericConstants.FOUR_EIGHT_ZERO && browserWidth > NumericConstants.THREE_EIGHT_ZERO) {
            getCollapsibleColumns480Px(table);
        } else if (browserWidth < NumericConstants.THREE_EIGHT_ZERO && defaultColumnsVisible(table)) {
                for (Object propertyId : getCollapsibleOneColumn(table)) {
                    table.setColumnCollapsed(propertyId, true);
            }
        }

    }

    private static Object[] getCollapsibleColumns480Px(CustomePagedFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<>(Arrays.asList(propertyIds));
        list.remove(propertyIds[NumericConstants.ONE]);
        propertyIds = list.toArray(new Object[list.size()]);

        for (Object propertyId : table.getVisibleColumns()) {
            table.setColumnCollapsed(propertyId, false);
        }

        for (Object propertyId : propertyIds) {
            table.setColumnCollapsed(propertyId, true);
        }

        return propertyIds;
    }

    private static Object[] getCollapsibleColumns978Px(CustomePagedFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<>(Arrays.asList(propertyIds));
        list.remove(propertyIds[NumericConstants.ONE]);
        list.remove(propertyIds[NumericConstants.TWO]);
        list.remove(propertyIds[NumericConstants.THREE]);
        propertyIds = list.toArray(new Object[list.size()]);
        for (Object propertyId : table.getVisibleColumns()) {
            table.setColumnCollapsed(propertyId, false);
        }

        for (Object propertyId : propertyIds) {
            table.setColumnCollapsed(propertyId, true);
        }
        return propertyIds;
    }

    private static Object[] getCollapsibleColumnsTwoData(CustomePagedFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<>(Arrays.asList(propertyIds));
        list.remove(propertyIds[NumericConstants.ONE]);
        list.remove(propertyIds[NumericConstants.TWO]);
        propertyIds = list.toArray(new Object[list.size()]);
        for (Object propertyId : table.getVisibleColumns()) {
            table.setColumnCollapsed(propertyId, false);
        }

        for (Object propertyId : propertyIds) {
            table.setColumnCollapsed(propertyId, true);
        }
        return propertyIds;
    }

    private static boolean defaultColumnsVisible(CustomePagedFilterTable table) {
        boolean result = true;
        for (Object propertyId : getCollapsibleOneColumn(table)) {
            if (table.isColumnCollapsed(propertyId) == Page.getCurrent()
                    .getBrowserWindowWidth() < NumericConstants.EIGHT_HUNDRED) {
                result = false;
            }
        }
        return result;
    }

    private static Object[] getCollapsibleOneColumn(CustomePagedFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        propertyIds = list.toArray(new Object[list.size()]);
        for (Object propertyId : table.getVisibleColumns()) {
            table.setColumnCollapsed(propertyId, false);
        }

        for (Object propertyId : propertyIds) {
            table.setColumnCollapsed(propertyId, true);
        }

        return propertyIds;
    }

    private static Object[] getCollapsibleColumnsDefault(CustomePagedFilterTable table) {
        table.setColumnCollapsingAllowed(true);
        table.setImmediate(true);
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<>(Arrays.asList(visibleColumns));
        for (int i = 0, j = list.size(); i < j; i++) {
            list.remove(propertyIds[i]);
        }
        propertyIds = list.toArray(new Object[list.size()]);

        for (Object propertyId : table.getVisibleColumns()) {
            table.setColumnCollapsed(propertyId, false);
        }

        for (Object propertyId : propertyIds) {
            table.setColumnCollapsed(propertyId, true);
        }

        return propertyIds;
    }
}
