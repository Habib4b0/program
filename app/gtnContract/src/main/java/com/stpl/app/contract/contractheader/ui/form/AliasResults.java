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
import com.stpl.app.serviceUtils.ConstantsUtils;
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
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

/**
 * Class for contract alias result form.
 *
 * @author
 */
public final class AliasResults extends CustomComponent {

    private final Map<Integer, Boolean> reloadVerticalLayoutTabThreeMap = new HashMap<>();
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
    public AliasResults(final CustomFieldGroup systemBinder, final BeanItemContainer<ContractAliasMasterDTO> aliasResultsBean,final String mode) throws SystemException, PortalException {
        super();
        LOGGER.debug("Entering AliasResults constructor ");
        this.systemBinder = systemBinder;
        this.aliasResultsBean = aliasResultsBean;
        this.mode=mode;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/declarative-ui/contract-header/contract-alias.xml"), this));

        this.binder = getBinder();
        init();
        LOGGER.debug(" AliasResults constructor ends");
    }

    public AliasResults() {
        this.binder = getBinder();
    }

    /**
     * method adds the content and configures the field to the UI.
     */
    private void init() throws SystemException, PortalException{
        LOGGER.debug("Entering init method ");

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
        LOGGER.debug(" init method ends");
    }

    private void addResponsivenessToComponents() throws PortalException, SystemException {

      
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final Map<String, AppPermission> fieldContract = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.CONTRACT_HEADER+Constants.COMMA+Constants.ALIAS,false);

        LOGGER.debug("Entering configurePermission");
        try {
            List<Object> resultList = contractHL.getFieldsForSecurity(UISecurityUtil.CONTRACT_HEADER, Constants.ALIAS);
            commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout, fieldContract, mode);

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        final Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.CONTRACT_HEADER+Constants.COMMA+Constants.ALIAS);
        if (functionHM.get(CHFunctionNameUtils.ADD_ALIAS) != null && ((AppPermission) functionHM.get(CHFunctionNameUtils.ADD_ALIAS)).isFunctionFlag()) {
            populateButton();
        }else{
            btnAdd.setVisible(false);
        }
        if (functionHM.get(CHFunctionNameUtils.REMOVE_ALIAS) != null && ((AppPermission) functionHM.get(CHFunctionNameUtils.REMOVE_ALIAS)).isFunctionFlag()) {
            removeButton();
        }else{
             remove.setVisible(false);
        }
        LOGGER.debug("Ending configurePermission");

    }

    /**
     * Binds the data holded by the field to DTO.
     *
     * @return CustomFieldGroup
     */
    public CustomFieldGroup getBinder() {
        LOGGER.debug("Entering getBinder method ");
        final ContractAliasMasterDTO bean = new ContractAliasMasterDTO();
        binder = new CustomFieldGroup(new BeanItem<>(bean));
        binder.setBuffered(true);
        binder.bindMemberFields(this);
        binder.setErrorDisplay(errorMsg);
        errorMsg.setId("ErrorMessage");
        LOGGER.debug(" getBinder method ends");
        return binder;
    }

    /**
     * Sets the data source and adds the table to the vertical layout.
     *
     * @return Table
     */
    private ExtFilterTable addToTable() throws PortalException,SystemException {

       final StplSecurity stplSecurity = new StplSecurity();
       String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
     final Map<String, AppPermission> fieldContract = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.CONTRACT_HEADER+Constants.COMMA+Constants.ALIAS,false);
  List<Object> resultList = contractHL.getFieldsForSecurity(UISecurityUtil.CONTRACT_HEADER, Constants.ALIAS);

      
        Object[] obj = UIUtils.getInstance().aliasColumns; 
    TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, fieldContract, Constants.VIEW.equals(mode)?"view":mode);    
       if(tableResultCustom.getObjResult().length > Constants.ZERO){
            table.markAsDirty();
          table.setContainerDataSource(aliasResultsBean);
            table.setVisibleColumns(tableResultCustom.getObjResult());
            table.setColumnHeaders(tableResultCustom.getObjResultHeader());
          }else{
              tablePanel.setVisible(false);
              BtnLayout.setVisible(false);
          }

        LOGGER.debug("Entering addToTable method ");
        table.setSelectable(true);
        table.setPageLength(NumericConstants.THREE);
        table.setFilterBarVisible(true);
        table.setFilterDecorator(new ExtDemoFilterDecorator());
        table.setImmediate(true);
        table.setSizeFull();

        table.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                LOGGER.debug(" table item click listener called");
                if (event.getProperty().getValue() != null) {
                    itemId = event.getProperty().getValue();
                } else {
                    itemId = null;
                }
                LOGGER.debug(" table item click listener ends");
            }
        });
        LOGGER.debug(" addToTable method ends by returning table");
        return table;
    }

    /**
     * Configures and customizes the populateButton.
     *
     * @return - Button
     */
    public Button populateButton() {


        LOGGER.debug("Entering populateButton method ");
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
                return;
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
                    LOGGER.debug("entering btnAdd click listener ");
                    systemBinder.getErrorDisplay().clearError();
                    binder.getErrorDisplay().clearError();
                    binder.getFields();
                    binder.commit();
                    final ContractAliasMasterDTO identForm = new ContractAliasMasterDTO();
                    if (binder.getField(Constants.CONTRACT_ALIAS_TYPE).getValue() == null || ((com.stpl.ifs.util.HelperDTO) binder.getField(Constants.CONTRACT_ALIAS_TYPE).getValue()).getId() == Constants.ZERO) {

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
                        identForm.setAliasStartDate(String.valueOf(dateFormat.format((Date) binder.getField(Constants.ALIAS_START_DATE).getValue())));
                        identForm.setStartDate(String.valueOf(dateFormat.format((Date) binder.getField(Constants.ALIAS_START_DATE).getValue())));
                    }
                    if (binder.getField(Constants.ALIAS_END_DATE).getValue() != null) {
                        identForm.setAliasEndDate(String.valueOf(dateFormat.format((Date) binder.getField(Constants.ALIAS_END_DATE).getValue())));
                        identForm.setEndDate(String.valueOf(dateFormat.format((Date) binder.getField(Constants.ALIAS_END_DATE).getValue())));
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
                    HelperDTO helperDto = new HelperDTO(Constants.ZERO, Constants.SELECT_ONE);
                    contractAliasType.select(helperDto);
                    LOGGER.debug(" btnAdd click listener ends ");
                } catch (CommitException ex) {

                    LOGGER.error(ex);
                    systemBinder.getErrorDisplay().setError(ex.getCause().getMessage());
                    if (ex.getCause().getMessage().equals(ConstantsUtils.END_DATE_AFTER_START_DATE)) {
                        binder.getErrorDisplay().clearError();
                        systemBinder.getErrorDisplay().setError(ConstantsUtils.END_DATE_AFTER_START_DATE);
                        return;
                    }
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        });
        LOGGER.debug(" populateButton method ends by returning btnAdd");
        return btnAdd;
    }

    /**
     * Configures and customizes the remove button.
     *
     * @return Button.
     */
    public Button removeButton() {
        LOGGER.debug("Entering removeButton method ");
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
                LOGGER.debug("entering btnRemove click listener ");
                Object selectedValue = table.getValue();
                boolean flag = table.isSelected(selectedValue);
                if (!flag || itemId == null) {
                    AbstractNotificationUtils.getErrorNotification("Remove Error", "Select a row to remove");
                } else {
                    aliasResultsBean.removeItem(itemId);
                    itemId = null;
                }
                LOGGER.debug(" btnRemove click listener ends ");
            }
        });
        LOGGER.debug(" removeButton method ends by returning btnRemove");
        return remove;
    }

    /**
     * Adds the button to the HorizontalLayout.
     *
     * @return the horizontal layout
     */
    public HorizontalLayout addButtons() {
        final HorizontalLayout layout = new HorizontalLayout();
        LOGGER.debug("Entering addButtons method ");
        layout.setSpacing(true);
        layout.addComponent(remove);
        LOGGER.debug(" returns layout and addButtons method ends ");
        return layout;

    }

    /**
     * Configures the fields in that page.
     */
    public void configureFields() {
        LOGGER.debug("Entering configure method ");
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
                LOGGER.debug("contractAliasNo on Value Change--- " + contractAliasNo.getValue());
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
                LOGGER.debug("contractAliasName on Value Change--- " + contractAliasName.getValue());
            }
        });

        tpCompanyMasterSid.addValidator(new StringLengthValidator("Trading Partner Name should be less than 100 characters", Constants.ZERO, NumericConstants.HUNDRED, true));
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
                LOGGER.debug("tpCompanyMasterSid on Value Change--- " + tpCompanyMasterSid.getValue());
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

            }
        });
        commonUtil.loadComboBox(contractAliasType, UIUtils.CONTRACT_ALIAS_TYPE, false);
        aliasStartDate.setDescription(Constants.DATE);
        aliasEndDate.setDescription(Constants.DATE);
        LOGGER.debug(" configure method ended ");

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

    protected void excelExportLogic() throws SystemException, PortalException, NoSuchMethodException, IllegalAccessException,  InvocationTargetException  {
        LOGGER.debug("Entering excelExportLogic");
        createWorkSheet();
        LOGGER.debug("Ending excelExportLogic");
    }

    private void createWorkSheet() throws SystemException, PortalException, NoSuchMethodException, IllegalAccessException,  InvocationTargetException {
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
     */   
    public void createWorkSheetContent(final Integer start,final Integer end, final PrintWriter printWriter) throws NoSuchFieldException,  IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final List<ContractAliasMasterDTO> searchList = aliasResultsBean.getItemIds();
            if (end != Constants.ZERO) {
                ExcelExportforBB.createFileContent(table.getVisibleColumns(), searchList, printWriter);
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
        public void validate(final Object value) {
            LOGGER.debug(" entering validate method  ");
            if (aliasStartDate.getValue() != null && aliasEndDate.getValue() != null) {
                if (aliasStartDate.getValue().after(aliasEndDate.getValue())) {
                    throw new InvalidValueException(ConstantsUtils.END_DATE_AFTER_START_DATE);
                } else if (aliasStartDate.getValue().equals(aliasEndDate.getValue())) {
                    throw new InvalidValueException("Start date and End date are equal");
                }
            }
            LOGGER.debug("  validate method ends ");
        }

        /**
         * compares the date field and returns the boolean value.
         *
         * @param value - Object
         * @return boolean
         */
        @Override
        protected boolean isValidValue(final Object value) {
            LOGGER.debug(" entering isValidValue method  ");
            boolean isValid;
            if (aliasStartDate.getValue() != null && aliasEndDate.getValue() != null) {
                isValid = aliasStartDate.getValue().compareTo(aliasEndDate.getValue()) <= Constants.ZERO;
                LOGGER.debug("  isValidValue method ends with returning isValid value:" + isValid);
                return isValid;
            }
            LOGGER.debug("  isValidValue method with returning isValid value: true");
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


    private static Object[] getCollapsibleColumns480Px(ExtFilterTable table) {
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

    private static Object[] getCollapsibleColumns978Px(ExtFilterTable table) {
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

    private static Object[] getCollapsibleColumnsTwoData(ExtFilterTable table) {
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

    private static boolean defaultColumnsVisible(ExtFilterTable table) {
        boolean result = true;
        for (Object propertyId : getCollapsibleOneColumn(table)) {
            if (table.isColumnCollapsed(propertyId) == Page.getCurrent()
                    .getBrowserWindowWidth() < NumericConstants.EIGHT_HUNDRED) {
                result = false;
            }
        }
        return result;
    }

    private static Object[] getCollapsibleOneColumn(ExtFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<>(Arrays.asList(propertyIds));
        list.remove(propertyIds[Constants.ZERO]);
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
        List<Object> list = new ArrayList<>(Arrays.asList(visibleColumns));
        for (int i = Constants.ZERO, j = list.size(); i < j; i++) {
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
        LOGGER.debug("Entering addResponsiveVerticalTabThreeLayout");

        reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
        reloadVerticalLayoutTabThreeMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
        reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
        reloadVerticalLayoutTabThreeMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
        reloadVerticalLayoutTabThreeMap.put(NumericConstants.EIGHT_HUNDRED, true);
        reloadVerticalLayoutTabThreeMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
        reloadVerticalLayoutTabThreeMap.put(NumericConstants.THREE_TWO_ZERO, true);
        reloadVerticalLayoutTabThreeMap.put(Constants.ZERO, true);

        Page.getCurrent().addBrowserWindowResizeListener(
                new Page.BrowserWindowResizeListener() {
            @Override
            public void browserWindowResized(
                    final Page.BrowserWindowResizeEvent event) {

                int browserWidth = Page.getCurrent().getBrowserWindowWidth();
                if (browserWidth > NumericConstants.ONE_FIVE_ONE_SIX && reloadVerticalLayoutTabThreeMap.get(NumericConstants.ONE_FIVE_ONE_SIX)) {
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_FIVE_ONE_SIX, false);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.EIGHT_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THREE_TWO_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(Constants.ZERO, true);
                    getCollapsibleColumnsDefault(table);
                } else if (browserWidth < NumericConstants.ONE_FIVE_ONE_SIX && browserWidth > NumericConstants.THOUSAND_THREE_HUNDRED && reloadVerticalLayoutTabThreeMap.get(NumericConstants.THOUSAND_THREE_HUNDRED)) {
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, false);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.EIGHT_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THREE_TWO_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(Constants.ZERO, true);
                    getCollapsibleColumnsDefault(table);
                } else if (browserWidth < NumericConstants.THOUSAND_THREE_HUNDRED && browserWidth > NumericConstants.ONE_ZERO_TWO_FOUR && reloadVerticalLayoutTabThreeMap.get(NumericConstants.ONE_ZERO_TWO_FOUR)) {
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, false);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.EIGHT_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THREE_TWO_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(Constants.ZERO, true);
                    getCollapsibleColumns978Px(table);
                } else if (browserWidth < NumericConstants.ONE_ZERO_TWO_FOUR && browserWidth > NumericConstants.NINE_SEVEN_EIGHT && reloadVerticalLayoutTabThreeMap.get(NumericConstants.NINE_SEVEN_EIGHT)) {
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.NINE_SEVEN_EIGHT, false);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.EIGHT_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THREE_TWO_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(Constants.ZERO, true);
                    getCollapsibleColumns978Px(table);
                } else if (browserWidth < NumericConstants.NINE_SEVEN_EIGHT && browserWidth > NumericConstants.EIGHT_HUNDRED && reloadVerticalLayoutTabThreeMap.get(NumericConstants.EIGHT_HUNDRED)) {
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.EIGHT_HUNDRED, false);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THREE_TWO_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(Constants.ZERO, true);
                    if (table.getItemIds().isEmpty()) {
                        getCollapsibleColumns978Px(table);
                    } else {
                        getCollapsibleColumnsTwoData(table);
                    }
                } else if (browserWidth <= NumericConstants.EIGHT_HUNDRED && browserWidth > NumericConstants.FOUR_EIGHT_ZERO && reloadVerticalLayoutTabThreeMap.get(NumericConstants.FOUR_EIGHT_ZERO)) {
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.EIGHT_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.FOUR_EIGHT_ZERO, false);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THREE_TWO_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(Constants.ZERO, true);
                    getCollapsibleColumns480Px(table);
                } else if (browserWidth < NumericConstants.FOUR_EIGHT_ZERO && browserWidth > NumericConstants.THREE_TWO_ZERO && reloadVerticalLayoutTabThreeMap.get(NumericConstants.THREE_TWO_ZERO)) {
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.EIGHT_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THREE_TWO_ZERO, false);
                    reloadVerticalLayoutTabThreeMap.put(Constants.ZERO, true);
                    getCollapsibleOneColumn(table);
                } else if (browserWidth < NumericConstants.THREE_TWO_ZERO && reloadVerticalLayoutTabThreeMap.get(Constants.ZERO)) {
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.EIGHT_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THREE_TWO_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(Constants.ZERO, false);
                    getCollapsibleOneColumn(table);
                }

            }
        });
        LOGGER.debug("Ending addResponsiveVerticalTabThreeLayout");
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
        binder.setItemDataSource(new BeanItem<>(new ContractAliasMasterDTO()));
        table.removeAllItems();
    }
}
