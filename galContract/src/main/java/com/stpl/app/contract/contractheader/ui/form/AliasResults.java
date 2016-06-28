package com.stpl.app.contract.contractheader.ui.form;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.OrderByColumn;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

import com.stpl.app.contract.contractheader.dto.ContractAliasMasterDTO;
import com.stpl.app.contract.contractheader.logic.ContractHeaderLogic;
import com.stpl.app.contract.contractheader.util.CommonUtils;
import com.stpl.app.contract.contractheader.util.UIUtils;
import com.stpl.app.contract.util.AbstractNotificationUtils;
import com.stpl.app.contract.util.CommonUIUtils;
import com.stpl.app.contract.util.Constants;
import com.stpl.app.contract.util.ErrorCodeUtil;
import com.stpl.app.contract.util.ErrorCodes;
import com.stpl.app.security.impl.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.app.contract.util.CHFunctionNameUtils;
import com.stpl.ifs.ui.CommonSecurityLogic;
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
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.util.Collection;

/**
 * Class for contract alias result form.
 *
 * @author
 */
public final class AliasResults extends CustomComponent {

    private final Map<Integer, Boolean> reloadVerticalLayoutTabThreeMap = new HashMap<Integer, Boolean>();
    /**
     * The Constant EMPTY.
     */
    public static final String EMPTY = "";
    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getLogger(AliasResults.class);
    /**
     * The error msg.
     */
    private final ErrorLabel errorMsg = new ErrorLabel();
    /**
     * The space.
     */
    /**
     * The binder.
     */
    private CustomFieldGroup binder;
    /**
     * Purposely for Error Display
     */
    private CustomFieldGroup systemBinder;
    /**
     * The alias results bean.
     */
    private BeanItemContainer<ContractAliasMasterDTO> aliasResultsBean;
    @UiField("cssLayout")
    private CssLayout cssLayout;
    @UiField("aliasInfoLayout")
    private VerticalLayout aliasInfoLayout;
    @UiField("contractAliasNo")
    private TextField contractAliasNo;
    @UiField("contractAliasName")
    private TextField contractAliasName;
    private TextField tradingPartner = new TextField();
    @UiField("contractAliasType")
    private ComboBox contractAliasType = new ComboBox();
    @UiField("tradingName")
    private CustomTextField tpCompanyMasterSid;
    TradingPartnerLookUp lookUp =null;

    public CustomTextField getTpCompanyMasterSid() {
        return tpCompanyMasterSid;
    }

    public void setTpCompanyMasterSid(CustomTextField tpCompanyMasterSid) {
        this.tpCompanyMasterSid = tpCompanyMasterSid;
    }
    @UiField("aliasStartDate")
    private PopupDateField aliasStartDate;
    @UiField("aliasEndDate")
    private PopupDateField aliasEndDate;
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
    @UiField("BtnLayout")
    private HorizontalLayout BtnLayout;
    @UiField("tablePanel")
    private Panel tablePanel;
    /**
     * The item id.
     */
    private Object itemId;
    /**
     * The remove.
     */
    @UiField("aliasRemove")
    private Button remove;
    /**
     * The contract alias type.
     */
    @UiField("aliasResultsTable")
    private ExtFilterTable table;
    /**
     * The common util.
     */
    private com.stpl.app.contract.abstractsearch.util.CommonUtils commonUtil = com.stpl.app.contract.abstractsearch.util.CommonUtils.getInstance();
    /**
     * The contract hl.
     */
    private final ContractHeaderLogic contractHL = new ContractHeaderLogic();
    CommonUIUtils commonUIUtils = new CommonUIUtils();
    CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();
    private String mode;
    @UiField("aliasExport")
    private Button excelExport;

    public CustomFieldGroup getSystemBinder() {
        return systemBinder;
    }

    public void setSystemBinder(CustomFieldGroup systemBinder) {
        this.systemBinder = systemBinder;
    }
    /**
     * Date Formatter
     */
    final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    /**
     * Gets the alias results bean.
     *
     * @return the alias results bean
     */
    public BeanItemContainer<ContractAliasMasterDTO> getAliasResultsBean() {
        return aliasResultsBean;
    }

    /**
     * Sets the alias results bean.
     *
     * @param aliasResultsBean the alias results bean
     */
    public void setAliasResultsBean(final BeanItemContainer<ContractAliasMasterDTO> aliasResultsBean) {
        this.aliasResultsBean = aliasResultsBean;
    }

    /**
     * Gets the item id.
     *
     * @return the item id
     */
    public Object getItemId() {
        return itemId;
    }

    /**
     * Sets the item id.
     *
     * @param itemId the item id
     */
    public void setItemId(final Object itemId) {
        this.itemId = itemId;
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
     * Gets the trading partner.
     *
     * @return the trading partner
     */
    public TextField getTradingPartner() {
        return tradingPartner;
    }

    /**
     * Gets the trading name.
     *
     * @return the trading name
     */
    /**
     * Gets the start date.
     *
     * @return the start date
     */
    public PopupDateField getAliasStartDate() {
        return aliasStartDate;
    }

    /**
     * Gets the end date.
     *
     * @return the end date
     */
    public PopupDateField getAliasEndDate() {
        return aliasEndDate;
    }

    /**
     * Gets the remove.
     *
     * @return the removes the
     */
    public Button getRemove() {
        return remove;
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
     * Gets the contract hl.
     *
     * @return the contract hl
     */
    public ContractHeaderLogic getContractHL() {
        return contractHL;
    }

    /**
     * Sets the binder.
     *
     * @param binder the binder
     */
    public void setBinder(final CustomFieldGroup binder) {
        this.binder = binder;
    }

    /**
     * Parameterized Constructor.
     *
     * @param systemBinder - BeanItemContainer
     * @param aliasResultsBean - BeanItemContainer
     */
    public AliasResults(final CustomFieldGroup systemBinder, final BeanItemContainer<ContractAliasMasterDTO> aliasResultsBean,final String mode) throws SystemException, PortalException, Exception {
        super();
        LOGGER.info("Entering AliasResults constructor ");
        this.systemBinder = systemBinder;
        this.aliasResultsBean = aliasResultsBean;
        this.mode=mode;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/declarative-ui/contract-header/contract-alias.xml"), this));

        this.binder = getBinder();
        init();
        LOGGER.info(" AliasResults constructor ends");
    }

    public AliasResults() {
        this.binder = getBinder();
    }

    /**
     * method adds the content and configures the field to the UI.
     */
    private void init() throws SystemException, PortalException, Exception {
        LOGGER.info("Entering init method ");

        addToTable();
        configureFields();
        validateFields();
        if (Constants.VIEW.equals(mode)) {
            aliasInfoLayout.setEnabled(false);
            btnAdd.setVisible(false);
            remove.setVisible(false);
            table.setReadOnly(true);
            binder.setReadOnly(true);
            tradingPartnerLabel.setReadOnly(true);


        }
        addResponsivenessToComponents();
        LOGGER.info(" init method ends");
    }

    private void addResponsivenessToComponents() throws PortalException, SystemException, Exception {

      
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final Map<String, AppPermission> fieldContract = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.CONTRACT_HEADER+Constants.COMMA+Constants.ALIAS,false);

        LOGGER.info("Entering configurePermission");
        try {
            List<Object> resultList = contractHL.getFieldsForSecurity(UISecurityUtil.CONTRACT_HEADER, Constants.ALIAS);
            commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout, fieldContract, mode);

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        final Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.CONTRACT_HEADER+Constants.COMMA+Constants.ALIAS);
        if (functionHM.get(CHFunctionNameUtils.Add_Alias) != null && ((AppPermission) functionHM.get(CHFunctionNameUtils.Add_Alias)).isFunctionFlag()) {
            populateButton();
        }else{
            btnAdd.setVisible(false);
        }
        if (functionHM.get(CHFunctionNameUtils.Remove_Alias) != null && ((AppPermission) functionHM.get(CHFunctionNameUtils.Remove_Alias)).isFunctionFlag()) {
            removeButton();
        }else{
             remove.setVisible(false);
        }
        LOGGER.info("Ending configurePermission");

    }

    /**
     * Binds the data holded by the field to DTO.
     *
     * @return CustomFieldGroup
     */
    public CustomFieldGroup getBinder() {
        LOGGER.info("Entering getBinder method ");
        final ContractAliasMasterDTO bean = new ContractAliasMasterDTO();
        binder = new CustomFieldGroup(new BeanItem<ContractAliasMasterDTO>(bean));
        binder.setBuffered(true);
        binder.bindMemberFields(this);
        binder.setErrorDisplay(errorMsg);
        errorMsg.setId("ErrorMessage");
        LOGGER.info(" getBinder method ends");
        return binder;
    }

    /**
     * Sets the data source and adds the table to the vertical layout.
     *
     * @return Table
     */
    private ExtFilterTable addToTable() throws SystemException, Exception {

       final StplSecurity stplSecurity = new StplSecurity();
       String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
     final Map<String, AppPermission> fieldContract = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.CONTRACT_HEADER+Constants.COMMA+Constants.ALIAS,false);
  List<Object> resultList = contractHL.getFieldsForSecurity(UISecurityUtil.CONTRACT_HEADER, Constants.ALIAS);

      
        Object[] obj = UIUtils.ALIAS_COLUMNS; 
    TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, fieldContract, Constants.VIEW.equals(mode)?"view":mode);    
       if(tableResultCustom.getObjResult().length > 0){
            table.markAsDirty();
          table.setContainerDataSource(aliasResultsBean);
            table.setVisibleColumns(tableResultCustom.getObjResult());
            table.setColumnHeaders(tableResultCustom.getObjResultHeader());
          }else{
              tablePanel.setVisible(false);
              BtnLayout.setVisible(false);
          }

        LOGGER.info("Entering addToTable method ");
        table.setSelectable(true);;
        table.setPageLength(3);
        table.setFilterBarVisible(true);
        table.setFilterDecorator(new ExtDemoFilterDecorator());
        table.setImmediate(true);
        table.setSizeFull();

        table.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                LOGGER.info(" table item click listener called");
                if (event.getProperty().getValue() != null) {
                    itemId = event.getProperty().getValue();
                } else {
                    itemId = null;
                }
                LOGGER.info(" table item click listener ends");
            }
        });
        LOGGER.info(" addToTable method ends by returning table");
        return table;
    }

    /**
     * Configures and customizes the populateButton.
     *
     * @return - Button
     */
    public Button populateButton() {


        LOGGER.info("Entering populateButton method ");
        btnAdd.setImmediate(true);
        btnAdd.setWidth("85");
        btnAdd.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs.
             *
             * @param event - ErrorEvent
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
            }
        });
        btnAdd.addClickListener(new ClickListener() {
            /**
             * Invoked when the button is clicked.
             *
             * @param event
             */
            public void buttonClick(final ClickEvent event) {
                try {
                    LOGGER.info("entering btnAdd click listener ");
                    systemBinder.getErrorDisplay().clearError();
                    binder.getErrorDisplay().clearError();
                    binder.getFields();
                    binder.commit();
                    final ContractAliasMasterDTO identForm = new ContractAliasMasterDTO();
                    if (binder.getField(Constants.CONTRACT_ALIAS_TYPE).getValue() == null || ((com.stpl.ifs.util.HelperDTO) binder.getField(Constants.CONTRACT_ALIAS_TYPE).getValue()).getId() == 0) {

                        systemBinder.getErrorDisplay().setError("Contract Alias Type should be selected on Alias tab");
                        return;
                    } else {
                        identForm.setContractAliasType((com.stpl.ifs.util.HelperDTO) binder.getField(Constants.CONTRACT_ALIAS_TYPE).getValue());
                        identForm.setContractAliasDesc(((com.stpl.ifs.util.HelperDTO) binder.getField(Constants.CONTRACT_ALIAS_TYPE).getValue()).getDescription());

                    }
                    if (StringUtils.isEmpty(String.valueOf(binder.getField(Constants.CONTRACT_ALIAS_NO).getValue()))) {
                       
                        systemBinder.getErrorDisplay().setError("Contract Alias No is Mandatory");
                        return;

                    } else {
                        identForm.setContractAliasNo(String.valueOf(binder.getField(Constants.CONTRACT_ALIAS_NO).getValue()));
                    }
                    identForm.setContractAliasName(String.valueOf(binder.getField(Constants.CONTRACT_ALIAS_NAME).getValue()));
                    if (binder.getField(Constants.ALIAS_START_DATE).getValue() == null) {
                        systemBinder.getErrorDisplay().setError("Alias Start Date should  be selected in Alias tab");
                        return;
                    } else {
                        identForm.setAliasStartDate(String.valueOf(dateFormat.format(((Date) binder.getField(Constants.ALIAS_START_DATE).getValue()))));
                        identForm.setStartDate(String.valueOf(dateFormat.format(((Date) binder.getField(Constants.ALIAS_START_DATE).getValue()))));
                    }
                    if (binder.getField(Constants.ALIAS_END_DATE).getValue() != null) {
                        identForm.setAliasEndDate(String.valueOf(dateFormat.format(((Date) binder.getField(Constants.ALIAS_END_DATE).getValue()))));
                        identForm.setEndDate(String.valueOf(dateFormat.format(((Date) binder.getField(Constants.ALIAS_END_DATE).getValue()))));
                    }
                    identForm.setContractAliasType((com.stpl.ifs.util.HelperDTO) binder.getField(Constants.CONTRACT_ALIAS_TYPE).getValue());
                    if (binder.getField(Constants.TRADING_PARTNER).getValue() != null) {

                        String tradingId;
                        tradingId = String.valueOf(binder.getField(Constants.TRADING_PARTNER).getValue()).replaceAll(Constants.COMMA, EMPTY);
                        identForm.setTradingPartner(Integer.parseInt(tradingId));
                        identForm.setTpCompanyMasterSid(String.valueOf(binder.getField("tpCompanyMasterSid").getValue()));
                    }
                    table.clearFilters();
                    aliasResultsBean.addBean(identForm);
                    binder = getBinder();
                    HelperDTO helperDto = new HelperDTO(0, Constants.SELECT_ONE);
                    contractAliasType.select(helperDto);
                    LOGGER.info(" btnAdd click listener ends ");
                } catch (CommitException ex) {

                    LOGGER.error(ex);
                    systemBinder.getErrorDisplay().setError(ex.getCause().getMessage());
                    if (ex.getCause().getMessage().equals("End date should be after Start date")) {
                        binder.getErrorDisplay().clearError();
                        systemBinder.getErrorDisplay().setError("End date should be after Start date");
                        return;
                    }
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        });
        LOGGER.info(" populateButton method ends by returning btnAdd");
        return btnAdd;
    }

    /**
     * Configures and customizes the remove button.
     *
     * @return Button.
     */
    public Button removeButton() {
        LOGGER.info("Entering removeButton method ");
        remove.setImmediate(true);
        remove.setWidth("85");
        remove.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs.
             *
             * @param event - ErrorEvent
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                // Added for error handling
            }
        });
        remove.addClickListener(new ClickListener() {
            /**
             * Invoked when the button is clicked.
             *
             * @param event - ClickEvent
             */
            public void buttonClick(final ClickEvent event) {
                LOGGER.info("entering btnRemove click listener ");
                Object selectedValue = table.getValue();
                boolean flag = table.isSelected(selectedValue);
                if (!flag || itemId == null) {
                    AbstractNotificationUtils.getErrorNotification("Remove Error", "Select a row to remove");
                } else {
                    aliasResultsBean.removeItem(itemId);
                    itemId = null;
                }
                LOGGER.info(" btnRemove click listener ends ");
            }
        });
        LOGGER.info(" removeButton method ends by returning btnRemove");
        return remove;
    }

    /**
     * Adds the button to the HorizontalLayout.
     *
     * @return the horizontal layout
     */
    public HorizontalLayout addButtons() {
        final HorizontalLayout layout = new HorizontalLayout();
        LOGGER.info("Entering addButtons method ");
        layout.setSpacing(true);
        layout.addComponent(remove);
        LOGGER.info(" returns layout and addButtons method ends ");
        return layout;

    }

    /**
     * Configures the fields in that page.
     */
    public void configureFields() throws Exception {
        LOGGER.info("Entering configure method ");
        aliasStartDate.setImmediate(true);
        aliasStartDate.setValidationVisible(true);
        aliasStartDate.setDateFormat(Constants.MM_DD_YYYY);
        aliasStartDate.setId("AliasStartDate");
        aliasStartDate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Invoked when the value of the field is changed.
             *
             * @param event - ValueChangeEvent
             */
            @SuppressWarnings("PMD")
            public void valueChange(final ValueChangeEvent event) {
                aliasStartDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(aliasStartDate.getValue()));
            }
        });
        aliasEndDate.setImmediate(true);
        aliasEndDate.setValidationVisible(true);
        aliasEndDate.setDateFormat(Constants.MM_DD_YYYY);
        aliasEndDate.addValidator(new DateValidatorContract("End date should be greater than Start date"));
        aliasEndDate.setId("AliasEndDate");
        aliasEndDate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Invoked when the value of the field is changed.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                aliasEndDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(aliasEndDate.getValue()));
            }
        });
        contractAliasNo.setImmediate(true);
        contractAliasNo.setValidationVisible(true);
        contractAliasNo.setData("maxlengthvalidationfifty,maxlengthvalidationcontractaliasno,null,null");
        contractAliasNo.setDescription(contractAliasNo.getValue());
        contractAliasNo.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Invoked when the value of the field is changed.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                contractAliasNo.setDescription(contractAliasNo.getValue());
                LOGGER.info("contractAliasNo on Value Change--- " + contractAliasNo.getValue());
            }
        });
        contractAliasName.setImmediate(true);
        contractAliasName.setValidationVisible(true);

        contractAliasName.setData("maxlengthvalidationfifty,maxlengthvalidationcontractaliasname,null,null");
        contractAliasName.setDescription(contractAliasName.getValue());
        contractAliasName.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Invoked when the value of the field is changed.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                contractAliasName.setDescription(contractAliasName.getValue());
                LOGGER.info("contractAliasName on Value Change--- " + contractAliasName.getValue());
            }
        });

        tpCompanyMasterSid.addValidator(new StringLengthValidator("Trading Partner Name should be less than 100 characters", 0, 100, true));
        tpCompanyMasterSid.addValidator(new RegexpValidator("([0-9|a-z|A-Z|\\.|\\,|\\_|\\-|\\@|\\#|\\$|\\&|\\%|\\s|\\/|\\(|\\!|\\)])*",
                "Trading Partner allows Special Characters like @,#,.,%,$,&,_,-,(,),/,!"));
        tpCompanyMasterSid.setValidationVisible(true);
        tpCompanyMasterSid.setImmediate(true);
        tpCompanyMasterSid.setDescription(tpCompanyMasterSid.getValue());
        tpCompanyMasterSid.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Invoked when the value of the field is changed.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                tpCompanyMasterSid.setDescription(tpCompanyMasterSid.getValue());
                LOGGER.info("tpCompanyMasterSid on Value Change--- " + tpCompanyMasterSid.getValue());
            }
        });
        tpCompanyMasterSid.addStyleName(Constants.SEARCH_ICON_STYLE);

        tpCompanyMasterSid.addClickListener(new CustomTextField.ClickListener() {
            /**
             * Invoked when the focus is on that field.
             *
             * @param event - ValueChangeEvent
             */
            public void click(final CustomTextField.ClickEvent event) {
                try {
                    if(lookUp ==null){
                    lookUp = new TradingPartnerLookUp(tradingPartner, tpCompanyMasterSid);
                    UI.getCurrent().addWindow(lookUp);
                    }
                    lookUp.addCloseListener(new Window.CloseListener() {
                        /**
                         * This method listens to close event
                         */
                        @SuppressWarnings("PMD")
                        public void windowClose(final Window.CloseEvent e) {
                            contractAliasNo.focus();
                            lookUp =null;
                        }
                    });
                } catch (SystemException ex) {
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                    LOGGER.error(errorMsg);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                }

            }
        });
        commonUtil.loadComboBox(contractAliasType, UIUtils.CONTRACT_ALIAS_TYPE, false);
        aliasStartDate.setDescription(Constants.DATE);
        aliasEndDate.setDescription(Constants.DATE);
        LOGGER.info(" configure method ended ");

        excelExport.setIcon(new ThemeResource("../../icons/excel.png"));
        excelExport.setStyleName("link");
        excelExport.setDescription("Export to excel");
        excelExport.setIconAlternateText("Excel export");
        excelExport.setHtmlContentAllowed(true);
        excelExport.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs
             *
             * @param event - ErrorEvent
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                // parses the error.
            }
        });
        excelExport.addClickListener(new ClickListener() {
            /**
             * calls excelExportLogic method on button click
             *
             * @param event - Mouse Click event
             */
            public void buttonClick(final ClickEvent event) {
                try {
                    LOGGER.info("Entering EXCEL Export Button Click :::: ");
                    binder.getFields();
                    excelExportLogic();
                    LOGGER.info(" Ends  EXCEL Export Button Click ::::  ");

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

    protected void excelExportLogic() throws SystemException, PortalException, Exception {
        LOGGER.info("Entering excelExportLogic");
        createWorkSheet();
        LOGGER.info("Ending excelExportLogic");
    }

    private void createWorkSheet() throws SystemException, PortalException, Exception {
        LOGGER.info("Entering createWorkSheet");
        final long recordCount = table.getContainerDataSource().size();
        ExcelExportforBB.createWorkSheet(table.getColumnHeaders(), recordCount, this, getUI(), TabNameUtil.Alias);
        LOGGER.info("Ending createWorkSheet");
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
    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws SystemException, PortalException, Exception {
        final List<ContractAliasMasterDTO> searchList = aliasResultsBean.getItemIds();
         try {
            if (end != 0) {
                ExcelExportforBB.createFileContent(table.getVisibleColumns(), searchList, printWriter);
            }
        } catch (Exception e) {

            LOGGER.error(e.getMessage());
        }

    }

    /**
     * Inner Class DateValidatorContract that extends AbstractValidator to
     * validate the DateFields.
     */
    @SuppressWarnings("rawtypes")
    public class DateValidatorContract extends AbstractValidator {

        /**
         * Parameterized Constructor.
         *
         * @param errorMessage - String
         */
        public DateValidatorContract(final String errorMessage) {
            super(errorMessage);
        }

        /**
         * Validates the startDate and endDate field for equals dates or
         * mismatching startDate and EndDate.
         *
         * @param value - Object
         * @throws InvalidValueException the invalid value exception
         */
        @Override
        public void validate(final Object value) throws InvalidValueException {
            LOGGER.info(" entering validate method  ");
            if (aliasStartDate.getValue() != null && aliasEndDate.getValue() != null) {
                if (aliasStartDate.getValue().after(aliasEndDate.getValue())) {
                    throw new InvalidValueException("End date should be after Start date");
                } else if (aliasStartDate.getValue().equals(aliasEndDate.getValue())) {
                    throw new InvalidValueException("Start date and End date are equal");
                }
            }
            LOGGER.info("  validate method ends ");
        }

        /**
         * compares the date field and returns the boolean value.
         *
         * @param value - Object
         * @return boolean
         */
        @Override
        protected boolean isValidValue(final Object value) {
            LOGGER.info(" entering isValidValue method  ");
            boolean isValid;
            if (aliasStartDate.getValue() != null && aliasEndDate.getValue() != null) {
                isValid = aliasStartDate.getValue().compareTo(aliasEndDate.getValue()) <= 0;
                LOGGER.info("  isValidValue method ends with returning isValid value:" + isValid);
                return isValid;
            }
            LOGGER.info("  isValidValue method with returning isValid value: true");
            return true;
        }

        /**
         * Over ridded abstract method when extending AbstractValidator.
         *
         * @return the type
         */
        @Override
        public Class getType() {
            return null;
        }
    }

    private void setDefaultResolution(ExtFilterTable table) {
        int browserWidth = Page.getCurrent().getBrowserWindowWidth();
        if (browserWidth > 1516) {
            getCollapsibleColumnsDefault(table);
        } else if (browserWidth < 1516 && browserWidth > 1300) {
            getCollapsibleColumnsDefault(table);
        } else if (browserWidth < 1300 && browserWidth > 1024) {
            getCollapsibleColumnsDefault(table);
        } else if (browserWidth < 1024 && browserWidth > 978) {
            getCollapsibleColumns978Px(table);
        } else if (browserWidth < 978 && browserWidth > 800) {
            if (table.getItemIds().isEmpty()) {
                getCollapsibleColumns978Px(table);
            } else {
                getCollapsibleColumnsTwoData(table);
            }
        } else if (browserWidth < 800 && browserWidth > 480) {
            if (table.getItemIds().isEmpty()) {
                getCollapsibleColumns978Px(table);
            } else {
                getCollapsibleColumnsTwoData(table);
            }
        } else if (browserWidth < 480 && browserWidth > 380) {
            getCollapsibleColumns480Px(table);
        } else if (browserWidth < 380) {
            if (defaultColumnsVisible(table)) {
                for (Object propertyId : getCollapsibleOneColumn(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
            }
        }

    }

    private static Object[] getCollapsibleColumns480Px(ExtFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<Object>(Arrays.asList(propertyIds));
        list.remove(propertyIds[1]);
        propertyIds = list.toArray(new Object[list.size()]);

        for (Object propertyId : table.getVisibleColumns()) {
            table.setColumnCollapsed(propertyId, false);
        }

        for (Object propertyId : propertyIds) {
            table.setColumnCollapsed(propertyId, true);
        }

        return propertyIds;
    }

    private static Object[] getCollapsibleColumns978Px(ExtFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<Object>(Arrays.asList(propertyIds));
        list.remove(propertyIds[1]);
        list.remove(propertyIds[2]);
        list.remove(propertyIds[3]);
        propertyIds = list.toArray(new Object[list.size()]);
        for (Object propertyId : table.getVisibleColumns()) {
            table.setColumnCollapsed(propertyId, false);
        }

        for (Object propertyId : propertyIds) {
            table.setColumnCollapsed(propertyId, true);
        }
        return propertyIds;
    }

    private static Object[] getCollapsibleColumnsTwoData(ExtFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<Object>(Arrays.asList(propertyIds));
        list.remove(propertyIds[1]);
        list.remove(propertyIds[2]);
        propertyIds = list.toArray(new Object[list.size()]);
        for (Object propertyId : table.getVisibleColumns()) {
            table.setColumnCollapsed(propertyId, false);
        }

        for (Object propertyId : propertyIds) {
            table.setColumnCollapsed(propertyId, true);
        }
        return propertyIds;
    }

    private static boolean defaultColumnsVisible(ExtFilterTable table) {
        boolean result = true;
        for (Object propertyId : getCollapsibleOneColumn(table)) {
            if (table.isColumnCollapsed(propertyId) == Page.getCurrent()
                    .getBrowserWindowWidth() < 800) {
                result = false;
            }
        }
        return result;
    }

    private static Object[] getCollapsibleOneColumn(ExtFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<Object>(Arrays.asList(propertyIds));
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

    private static Object[] getCollapsibleColumnsDefault(ExtFilterTable table) {
        table.setColumnCollapsingAllowed(true);
        table.setImmediate(true);
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<Object>(Arrays.asList(visibleColumns));
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

    public void addResponsiveVerticalTabThreeLayout() {

        reloadVerticalLayoutTabThreeMap.put(1516, true);
        reloadVerticalLayoutTabThreeMap.put(1300, true);
        reloadVerticalLayoutTabThreeMap.put(1024, true);
        reloadVerticalLayoutTabThreeMap.put(978, true);
        reloadVerticalLayoutTabThreeMap.put(800, true);
        reloadVerticalLayoutTabThreeMap.put(480, true);
        reloadVerticalLayoutTabThreeMap.put(320, true);
        reloadVerticalLayoutTabThreeMap.put(0, true);

        Page.getCurrent().addBrowserWindowResizeListener(
                new Page.BrowserWindowResizeListener() {
            @Override
            public void browserWindowResized(
                    final Page.BrowserWindowResizeEvent event) {

                int browserWidth = Page.getCurrent().getBrowserWindowWidth();
                if (browserWidth > 1516 && reloadVerticalLayoutTabThreeMap.get(1516)) {
                    reloadVerticalLayoutTabThreeMap.put(1516, false);
                    reloadVerticalLayoutTabThreeMap.put(1300, true);
                    reloadVerticalLayoutTabThreeMap.put(1024, true);
                    reloadVerticalLayoutTabThreeMap.put(978, true);
                    reloadVerticalLayoutTabThreeMap.put(800, true);
                    reloadVerticalLayoutTabThreeMap.put(480, true);
                    reloadVerticalLayoutTabThreeMap.put(320, true);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                    getCollapsibleColumnsDefault(table);
                } else if (browserWidth < 1516 && browserWidth > 1300 && reloadVerticalLayoutTabThreeMap.get(1300)) {
                    reloadVerticalLayoutTabThreeMap.put(1516, true);
                    reloadVerticalLayoutTabThreeMap.put(1300, false);
                    reloadVerticalLayoutTabThreeMap.put(1024, true);
                    reloadVerticalLayoutTabThreeMap.put(978, true);
                    reloadVerticalLayoutTabThreeMap.put(800, true);
                    reloadVerticalLayoutTabThreeMap.put(480, true);
                    reloadVerticalLayoutTabThreeMap.put(320, true);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                    getCollapsibleColumnsDefault(table);
                } else if (browserWidth < 1300 && browserWidth > 1024 && reloadVerticalLayoutTabThreeMap.get(1024)) {
                    reloadVerticalLayoutTabThreeMap.put(1516, true);
                    reloadVerticalLayoutTabThreeMap.put(1300, true);
                    reloadVerticalLayoutTabThreeMap.put(1024, false);
                    reloadVerticalLayoutTabThreeMap.put(978, true);
                    reloadVerticalLayoutTabThreeMap.put(800, true);
                    reloadVerticalLayoutTabThreeMap.put(480, true);
                    reloadVerticalLayoutTabThreeMap.put(320, true);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                    getCollapsibleColumns978Px(table);
                } else if (browserWidth < 1024 && browserWidth > 978 && reloadVerticalLayoutTabThreeMap.get(978)) {
                    reloadVerticalLayoutTabThreeMap.put(1516, true);
                    reloadVerticalLayoutTabThreeMap.put(1300, true);
                    reloadVerticalLayoutTabThreeMap.put(1024, true);
                    reloadVerticalLayoutTabThreeMap.put(978, false);
                    reloadVerticalLayoutTabThreeMap.put(800, true);
                    reloadVerticalLayoutTabThreeMap.put(480, true);
                    reloadVerticalLayoutTabThreeMap.put(320, true);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                    getCollapsibleColumns978Px(table);
                } else if (browserWidth < 978 && browserWidth > 800 && reloadVerticalLayoutTabThreeMap.get(800)) {
                    reloadVerticalLayoutTabThreeMap.put(1516, true);
                    reloadVerticalLayoutTabThreeMap.put(1300, true);
                    reloadVerticalLayoutTabThreeMap.put(1024, true);
                    reloadVerticalLayoutTabThreeMap.put(978, true);
                    reloadVerticalLayoutTabThreeMap.put(800, false);
                    reloadVerticalLayoutTabThreeMap.put(480, true);
                    reloadVerticalLayoutTabThreeMap.put(320, true);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                    if (table.getItemIds().isEmpty()) {
                        getCollapsibleColumns978Px(table);
                    } else {
                        getCollapsibleColumnsTwoData(table);
                    }
                } else if (browserWidth <= 800 && browserWidth > 480 && reloadVerticalLayoutTabThreeMap.get(480)) {
                    reloadVerticalLayoutTabThreeMap.put(1516, true);
                    reloadVerticalLayoutTabThreeMap.put(1300, true);
                    reloadVerticalLayoutTabThreeMap.put(1024, true);
                    reloadVerticalLayoutTabThreeMap.put(978, true);
                    reloadVerticalLayoutTabThreeMap.put(800, true);
                    reloadVerticalLayoutTabThreeMap.put(480, false);
                    reloadVerticalLayoutTabThreeMap.put(320, true);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                    getCollapsibleColumns480Px(table);
                } else if (browserWidth < 480 && browserWidth > 320 && reloadVerticalLayoutTabThreeMap.get(320)) {
                    reloadVerticalLayoutTabThreeMap.put(1516, true);
                    reloadVerticalLayoutTabThreeMap.put(1300, true);
                    reloadVerticalLayoutTabThreeMap.put(1024, true);
                    reloadVerticalLayoutTabThreeMap.put(978, true);
                    reloadVerticalLayoutTabThreeMap.put(800, true);
                    reloadVerticalLayoutTabThreeMap.put(480, true);
                    reloadVerticalLayoutTabThreeMap.put(320, false);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                    getCollapsibleOneColumn(table);
                } else if (browserWidth < 320 && reloadVerticalLayoutTabThreeMap.get(0)) {
                    reloadVerticalLayoutTabThreeMap.put(1516, true);
                    reloadVerticalLayoutTabThreeMap.put(1300, true);
                    reloadVerticalLayoutTabThreeMap.put(1024, true);
                    reloadVerticalLayoutTabThreeMap.put(978, true);
                    reloadVerticalLayoutTabThreeMap.put(800, true);
                    reloadVerticalLayoutTabThreeMap.put(480, true);
                    reloadVerticalLayoutTabThreeMap.put(320, true);
                    reloadVerticalLayoutTabThreeMap.put(0, false);
                    getCollapsibleOneColumn(table);
                }

            }
        });
    }

    public void validateFields() {
        Collection<Field<?>> collection = binder.getFields();
        CommonUtils commmonUtil = CommonUtils.getInstance();
        for (Field field : collection) {
            if (field instanceof TextField) {
                TextField textField = (TextField) field;
                commmonUtil.textValidation(textField, textField.getData());
            }
        }
    }

    public void reset() {
        binder.setItemDataSource(new BeanItem<ContractAliasMasterDTO>(new ContractAliasMasterDTO()));
    }
}
