package com.stpl.app.contract.dashboard.ui.form;

import com.stpl.app.contract.abstractsearch.util.ConstantUtil;
import java.util.Date;
import java.util.Map;

import org.asi.ui.customtextfield.CustomTextField;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyContainer;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

import com.stpl.app.contract.contractheader.dto.ContractMasterDTO;
import com.stpl.app.contract.contractheader.logic.ContractHeaderLogic;
import com.stpl.app.contract.contractheader.ui.form.CompanyNameLookUp;
import com.stpl.app.contract.contractheader.ui.form.TradingPartnerLookUp;
import com.stpl.app.contract.contractheader.util.CommonUtils;
import com.stpl.app.contract.contractheader.util.UIUtils;
import com.stpl.app.contract.global.lazyload.ManufacturerCriteria;
import com.stpl.app.contract.global.lazyload.ManufacturerDAO;
import com.stpl.app.contract.util.AbstractNotificationUtils;
import com.stpl.app.contract.util.CommonUIUtils;
import com.stpl.app.contract.util.Constants;
import com.stpl.app.contract.util.ErrorCodeUtil;
import com.stpl.app.contract.util.ErrorCodes;
import com.stpl.app.contract.common.util.CommonUtil;
import com.stpl.app.contract.dashboard.util.ContractUtils;
import com.stpl.app.contract.dashboard.util.ExcelExportUtil;
import com.stpl.app.security.impl.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.UISecurityUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.OptionGroup;
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
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.ExtPagedFilterTable;


/**
 * The Class ContractInfoTab.
 */
public class ContractInfoTab extends CustomComponent {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ContractInfoTab.class);
    /**
     * The contract master binder.
     */
    public CustomFieldGroup contractMasterBinder;
    /**
     * The contract master.
     */
    private final ContractMasterDTO contractMaster;

    @UiField("cssLayout")
    private CssLayout cssLayout;

    @UiField("insideOwner")
    private TextField insideOwner;

    @UiField("insidePhone")
    private TextField insidePhone;

    @UiField("insideAuthor")
    private TextField insideAuthor;

    @UiField("insideAdditional")
    private TextField insideAdditional;

    @UiField("insideAdditionalName")
    private TextField insideAdditionalName;

    @UiField("insideAdditionalPhone")
    private TextField insideAdditionalPhone;

    @UiField("outsideOwner")
    private TextField outsideOwner;

    @UiField("outsidePhone")
    private TextField outsidePhone;

    @UiField("outsideAuthor")
    private TextField outsideAuthor;

    @UiField("outsideAdditional")
    private TextField outsideAdditional;

    @UiField("outsideAdditionalName")
    private TextField outsideAdditionalName;

    @UiField("outsideAdditionalPhone")
    private TextField outsideAdditionalPhone;

    @UiField("advanceNoticeDays")
    private TextField advanceNoticeDays;

    @UiField("affiliatedContractInfo")
    private TextField affiliatedContractInfo;

    @UiField("shippingTerms")
    private TextField shippingTerms;

    @UiField("priceEscalationClause")
    private TextField priceEscalationClause;

    @UiField("exemptFromLowPrice")
    private TextField exemptFromLowPrice;

    @UiField("cancellationClause")
    private TextField cancellationClause;

    @UiField("mostFavoredNation")
    private TextField mostFavoredNation;

    @UiField("category")
    private TextField category;

    @UiField("currency")
    private TextField currency;

    @UiField("minimumOrder")
    private TextField minimumOrder;

    @UiField("proposalStartDate")
    private PopupDateField proposedStartDate;

    @UiField("proposalEndDate")
    private PopupDateField proposedEndDate;

    @UiField("originalStartDate")
    private PopupDateField originalStartDate;

    @UiField("originalEndDate")
    private PopupDateField originalEndDate;

    @UiField("lastUpdatedDate")
    private PopupDateField lastUpdatedDate;

    @UiField("awardStatus")
    private ComboBox awardStatus;

    @UiField("priceResetIndicator")
    private ComboBox priceResetIndicator;

    @UiField("paymentTerms")
    private ComboBox paymentTerms;

    CompanyNameLookUp lookUp = null;
    TradingPartnerLookUp tradingLookUp = null;
    /**
     * The contract id.
     */
    @UiField("contractId")
    private TextField contractId;

    /**
     * The contract no.
     */
    @UiField("contractNo")
    private TextField contractNo;

    /**
     * The contract name.
     */
    @UiField("contractName")
    private TextField contractName;

    /**
     * The contract type.
     */
    @UiField("contractType")
    private ComboBox contractType;
    
    /**
     * The contract status.
     */
    @UiField("contractStatus")
    private ComboBox contractStatus;
    
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
     * The doc type.
     */
    @UiField("docType")
    private ComboBox docType;
    
    /**
     * The doc class.
     */
    @UiField("docClass")
    private ComboBox docClass;
    
    /**
     * The company name.
     */
    private final TextField companyName = new TextField();
    
    /**
     * The company label.
     */
    @UiField("companyName")
    private CustomTextField companyLabel;
   
    /**
     * The trade class.
     */
    @UiField("tradeClass")
    private ComboBox tradeClass;
    
    /**
     * The term.
     */
    @UiField("term")
    private TextField term;
    
    /**
     * The renegotiation start date.
     */
    @UiField("renegotiationStartDate")
    private PopupDateField renegotiationStartDate;
    
    /**
     * The renegotiation end date.
     */
    @UiField("renegotiationEndDate")
    private PopupDateField renegotiationEndDate;
    
    /**
     * The priceprotection start date.
     */
    @UiField("priceprotectionStartDate")
    private PopupDateField priceprotectionStartDate;
    
    /**
     * The priceprotection end date.
     */
    @UiField("priceprotectionEndDate")
    private PopupDateField priceprotectionEndDate;
    
    /**
     * The company system id.
     */
    @UiField("companySystemId")
    private ComboBox companySystemId;
    
    /**
     * The trading partner name.
     */
    @UiField("tradingPartnerName")
    private CustomTextField tradingPartnerName;
    /**
     * View Option group
     */
    @UiField("view")
    private OptionGroup view;
    /**
     * Level option group
     */
    @UiField("level")
    private OptionGroup level;    
    
    /**
     * Contract Header HorizontalLayout
     */
    @UiField("contractHeader")
    private HorizontalLayout contractHeader;
    
    /**
     * Contract Information HorizontalLayout
     */
    @UiField("contractInfo")
    private HorizontalLayout contractInfo;
    /**
     * Contract Header Table
     */
    @UiField("contractHeaderTable")
    private VerticalLayout contractHeaderTable;
    
    @UiField("table")
    private ExtPagedFilterTable table;
    
    @UiField("excelBtn")
    private Button excelExport;
    
    private BeanItemContainer<ContractMasterDTO> container = new BeanItemContainer<>(ContractMasterDTO.class);
    /**
     * HEADER_COLUMNS
     */
    private static Object[] HEADER_COLUMNS = {"contractId","contractNo","contractName","contractType","contractStatus","docType","startDate","endDate",
                                                "docClass","companyName","tradeClass","term","tradingPartnerName","renegotiationStartDate","renegotiationEndDate",
                                                "priceprotectionStartDate","priceprotectionEndDate","companySystemId","modifiedDate"};
    /**
     * HEADER_HEADER
     */
    private static String[] HEADER_HEADER = {"Contract Id","Contract No","Contract Name","Contract Type","Contract Status","Document Type","Start Date","End Date",
                                                "Document Class","Company Name","Trade Class","Term","Trading Partner","Re-Negotiation Start Date","Re-Negotiation End Date",
                                                "Price Protection Start Date","Price Protection End Date","Manufacturer No","Modified Date"};
    /**
     * DETAIL_COLUMNS
     */
    private static Object[] DETAIL_COLUMNS = {"insideOwner","insidePhone","insideAuthor","insideAdditional","insideAdditionalName","insideAdditionalPhone","outsideOwner","outsidePhone",
                                                "outsideAuthor","outsideAdditional","outsideAdditionalName","outsideAdditionalPhone","advanceNoticeDays","affiliatedContractInfo",
                                                "shippingTerms","proposedStartDate","proposedEndDate","originalStartDate","originalEndDate","awardStatus","lastUpdatedDate",
                                                "priceEscalationClause","exemptFromLowPrice","priceResetIndicator","cancellationClause","mostFavoredNation","category",
                                                "currency","minimumOrder","paymentTerms","modifiedDate"};
    /**
     * DETAIL_HEADER
     */
    private static String[] DETAIL_HEADER = {"Inside Owner","Inside Phone","Inside Author","Inside Additional","Inside Additional Name","Inside Additional Phone","Outside Owner",
                                                "Outside Phone","Outside Author","Outside Additional","Outside Additional Name","Outside Additional Phone","Advance Notice Days",
                                                "Affiliated Contract Info","Shipping Terms","Proposal Start Date","Proposal End Date","Original Start Date","Original End Date",
                                                "Award Status","Last Updated Date","Price Escalation Clause","Exempt From Low Price","Price Reset Indicator","Cancelation Clause",
                                                "Most Favored Nation","Category","Currency","Minimum Order","Payment Terms","Modified Date"};
    
    private final TextField contractSystemId = new TextField();
    
    
    /**
     * The trading partner system id.
     */
    private final TextField tradingPartnerSystemId = new TextField();
    
    CommonUIUtils commonUIUtils= new CommonUIUtils();
    CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();
    
    private HelperDTO dto = new HelperDTO(Constants.SELECT_ONE);

    /**
     * Gets the contract master binder.
     *
     * @return the contract master binder
     */
    public CustomFieldGroup getContractMasterBinder() {
        return contractMasterBinder;
    }

    /**
     * Sets the contract master binder.
     *
     * @param contractMasterBinder the contract master binder
     */
    public void setContractMasterBinder(final CustomFieldGroup contractMasterBinder) {
        this.contractMasterBinder = contractMasterBinder;
    }

    /**
     * Gets the contract master.
     *
     * @return the contract master
     */
    public ContractMasterDTO getContractMaster() {
        return contractMaster;
    }

    /**
     * Gets the contract id.
     *
     * @return the contract id
     */
    public TextField getContractId() {
        return contractId;
    }

    /**
     * Gets the contract no.
     *
     * @return the contract no
     */
    public TextField getContractNo() {
        return contractNo;
    }

    /**
     * Gets the contract name.
     *
     * @return the contract name
     */
    public TextField getContractName() {
        return contractName;
    }

    /**
     * Gets the contract type.
     *
     * @return the contract type
     */
    public ComboBox getContractType() {
        return contractType;
    }

    /**
     * Gets the contract status.
     *
     * @return the contract status
     */
    public ComboBox getContractStatus() {
        return contractStatus;
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
     * Gets the doc type.
     *
     * @return the doc type
     */
    public ComboBox getDocType() {
        return docType;
    }

    /**
     * Gets the doc class.
     *
     * @return the doc class
     */
    public ComboBox getDocClass() {
        return docClass;
    }

    /**
     * Gets the company name.
     *
     * @return the company name
     */
    public TextField getCompanyName() {
        return companyName;
    }

    /**
     * Gets the company label.
     *
     * @return the company label
     */
    public CustomTextField getCompanyLabel() {
        return companyLabel;
    }

    /**
     * Gets the trade class.
     *
     * @return the trade class
     */
    public ComboBox getTradeClass() {
        return tradeClass;
    }

    /**
     * Gets the term.
     *
     * @return the term
     */
    public TextField getTerm() {
        return term;
    }

    /**
     * Gets the renegotiation start date.
     *
     * @return the renegotiation start date
     */
    public PopupDateField getRenegotiationStartDate() {
        return renegotiationStartDate;
    }

    /**
     * Gets the renegotiation end date.
     *
     * @return the renegotiation end date
     */
    public PopupDateField getRenegotiationEndDate() {
        return renegotiationEndDate;
    }

    /**
     * Gets the priceprotection start date.
     *
     * @return the priceprotection start date
     */
    public PopupDateField getPriceprotectionStartDate() {
        return priceprotectionStartDate;
    }

    /**
     * Gets the priceprotection end date.
     *
     * @return the priceprotection end date
     */
    public PopupDateField getPriceprotectionEndDate() {
        return priceprotectionEndDate;
    }

    /**
     * Gets the company system id.
     *
     * @return the company system id
     */
    public ComboBox getCompanySystemId() {
        return companySystemId;
    }

    /**
     * Gets the trading partner name.
     *
     * @return the trading partner name
     */
    public CustomTextField getTradingPartnerName() {
        return tradingPartnerName;
    }

    /**
     * Gets the trading partner system id.
     *
     * @return the trading partner system id
     */
    public TextField getTradingPartnerSystemId() {
        return tradingPartnerSystemId;
    }
    /** The common util. */
    private CommonUtil commonUtil = CommonUtil.getInstance();    

    /**
     * The Constructor.
     *
     * @param contractMaster the contract master
     * @param contractBinder the contract master binder
     * @throws Exception 
     * @throws SystemException 
     * @throws PortalException 
     */
    public ContractInfoTab(final ContractMasterDTO contractMaster, final CustomFieldGroup contractBinder) throws PortalException, SystemException {
        super();
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/declarative-ui/contract-dashboard/contract-header.xml"),this));
        this.contractMaster = contractMaster;
        this.contractMasterBinder = contractBinder;    
        init();        
    }

    /**
     * Gets the binder.
     *
     * @return the binder
     */
    private void configureBinder() {
        LOGGER.debug("Entering getBinder method");        
        contractMasterBinder.bindMemberFields(this);
        contractMasterBinder.setItemDataSource(new BeanItem<>(contractMaster));
        contractMasterBinder.setBuffered(true);        
        LOGGER.debug("End of getBinder method");
        
    }
    
    private void init() throws PortalException, SystemException{
    	addResponsivenessToFields();    	
    	configureFields();
    	configureBinder();
        configureTable();
        validateFields();
        }

   
    private void addResponsivenessToFields() throws PortalException, SystemException {
        final ContractHeaderLogic contractLogic = new ContractHeaderLogic();
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final Map<String, AppPermission> fieldContract = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.CONTRACT_DASHBOARD+Constants.COMMA+"Contract Information",false);

        LOGGER.debug("Entering configurePermission");
        try {

            List<Object> resultList = contractLogic.getFieldsForSecurity(UISecurityUtil.CONTRACT_DASHBOARD, "Contract Information");

            commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout, fieldContract, Constants.EDIT);

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("Ending configurePermission");
    }
    
        
    /**
     * Table for Historical data
     */
    private void configureTable(){
        table.setVisible(true);
        table.markAsDirty();
        table.setContainerDataSource(container);
        table.setPageLength(NumericConstants.SEVEN);
        table.setImmediate(true);
        table.setReadOnly(Boolean.TRUE);
        table.setVisibleColumns(HEADER_COLUMNS);
        table.setColumnHeaders(HEADER_HEADER);
        table.setFilterBarVisible(Boolean.TRUE);
    }

    /**
     * Configure fields
     * @throws Exception 
     */
    public void configureFields() {
        
        contractSystemId.setImmediate(Boolean.TRUE);                
        view.addItems(ConstantUtil.HISTORY, ConstantUtil.CURRENT, "Pending");
        view.select(ConstantUtil.CURRENT);

        level.addItems(ConstantUtil.HEADER, ConstantUtil.DETAIL);
        level.select(ConstantUtil.HEADER);

        contractHeader.setVisible(Boolean.TRUE);
        contractInfo.setVisible(Boolean.FALSE);
        contractHeaderTable.setVisible(Boolean.FALSE);
        excelExportLogic();
        excelExport.setVisible(false);
        view.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(ValueChangeEvent event) {
                excelExport.setVisible(true);
                if (ConstantUtil.HISTORY.equals(view.getValue().toString())) {
                    if (ConstantUtil.HEADER.equals(level.getValue().toString())) {
                        contractHeader.setVisible(Boolean.FALSE);
                        contractInfo.setVisible(Boolean.FALSE);
                        contractHeaderTable.setVisible(Boolean.TRUE);
                        table.setVisibleColumns(HEADER_COLUMNS);
                        table.setColumnHeaders(HEADER_HEADER);
                    } else if (ConstantUtil.DETAIL.equals(level.getValue().toString())) {
                        contractHeader.setVisible(Boolean.FALSE);
                        contractInfo.setVisible(Boolean.FALSE);
                        contractHeaderTable.setVisible(Boolean.TRUE);
                        table.setVisibleColumns(DETAIL_COLUMNS);
                        table.setColumnHeaders(DETAIL_HEADER);
                    }
                } else if (ConstantUtil.CURRENT.equals(view.getValue().toString()) || "Pending".equals(view.getValue().toString())) {
                    if (ConstantUtil.HEADER.equals(level.getValue().toString())) {
                        contractHeader.setVisible(Boolean.TRUE);
                        contractInfo.setVisible(Boolean.FALSE);
                        contractHeaderTable.setVisible(Boolean.FALSE);
                    } else if (ConstantUtil.DETAIL.equals(level.getValue().toString())) {
                        contractHeader.setVisible(Boolean.FALSE);
                        contractInfo.setVisible(Boolean.TRUE);
                        contractHeaderTable.setVisible(Boolean.FALSE);
                    }
                }
                 if (!String.valueOf(view.getValue()).equals(ConstantUtil.HISTORY)) {
                    excelExport.setVisible(false);
                }
            }
        });

        level.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(ValueChangeEvent event) {
                excelExport.setVisible(true);
                if (ConstantUtil.HEADER.equals(level.getValue().toString())) {
                    view.select(ConstantUtil.CURRENT);
                    contractHeader.setVisible(Boolean.TRUE);
                    contractInfo.setVisible(Boolean.FALSE);
                    contractHeaderTable.setVisible(Boolean.FALSE);
                } else if (ConstantUtil.DETAIL.equals(level.getValue().toString())) {
                    view.select(ConstantUtil.CURRENT);
                    contractHeader.setVisible(Boolean.FALSE);
                    contractInfo.setVisible(Boolean.TRUE);
                    contractHeaderTable.setVisible(Boolean.FALSE);
                }
                 if (!String.valueOf(view.getValue()).equals(ConstantUtil.HISTORY)) {
                    excelExport.setVisible(false);
                }
            }
        });
        
        proposedStartDate.setDateFormat(ContractUtils.MMDDYYYY);
        proposedEndDate.setDateFormat(ContractUtils.MMDDYYYY);
        originalStartDate.setDateFormat(ContractUtils.MMDDYYYY);
        originalEndDate.setDateFormat(ContractUtils.MMDDYYYY);
        lastUpdatedDate.setDateFormat(ContractUtils.MMDDYYYY);
                
        commonUtil.loadComboBox(awardStatus, UIUtils.STATUS, false);
        commonUtil.loadComboBox(priceResetIndicator, UIUtils.PRICE_RESET_INDICATOR, false);
        commonUtil.loadComboBox(paymentTerms, UIUtils.PAYMENT_TERMS_DDLB, false);

        companySystemId.setImmediate(true);
        companySystemId.setNullSelectionAllowed(true);
        companySystemId.setNullSelectionItemId(dto);
        companySystemId.setItemCaptionPropertyId(Constants.DESCRIPTION);
        companySystemId.setInputPrompt(Constants.SELECT_ONE);
        companySystemId.markAsDirty();
        LazyContainer manufacturerContainer = new LazyContainer(HelperDTO.class, new ManufacturerDAO(contractMaster.getCompanySystemId()), new ManufacturerCriteria());
        manufacturerContainer.setMinFilterLength(0);

        companySystemId.setContainerDataSource(manufacturerContainer);
        companySystemId.setPageLength(NumericConstants.SEVEN);
        companySystemId.setValue(dto);
     
        companySystemId.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used to set description name by using value change
             * listener for ContractID
             */
            @SuppressWarnings("PMD")
            public void valueChange(final ValueChangeEvent event) {
                LOGGER.debug("valueChange(ValueChangeEvent event) contractId=" + companySystemId.getValue());
                
                CommonUtils.setNullValue(companySystemId);
             
            }
        });

        contractId.addValidator(new StringLengthValidator("Contract ID length should be less than 38 characters", 0, NumericConstants.THIRTY_EIGHT, true));
        contractId.setImmediate(true);
        contractId.setValidationVisible(true);
        contractId.setRequired(true);
        contractId.setRequiredError("Contract ID should  be entered on Contract Information tab");
        contractId.setDescription(contractId.getValue());
        
        contractId.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used to set description name by using value change
             * listener for ContractID
             */
            @SuppressWarnings("PMD")
            public void valueChange(final ValueChangeEvent event) {
                LOGGER.debug("valueChange(ValueChangeEvent event) contractId=" + contractId.getValue());

                contractId.setDescription(contractId.getValue());
            }
        });

        contractName.addValidator(new StringLengthValidator("Contract Name should be less than 100 characters", 0, NumericConstants.HUNDRED, true));
        contractName.setImmediate(true);
        contractName.setValidationVisible(true);
        contractName.setDescription(contractName.getValue());
        contractName.setDescription(contractName.getValue());
        contractName.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used for Value Change listener of contract name
             */
            public void valueChange(final ValueChangeEvent event) {
                LOGGER.debug("valueChange(ValueChangeEvent event) contractName=" + contractName.getValue());
                contractName.setDescription(contractName.getValue());
            }
        });

        contractNo.addValidator(new StringLengthValidator("Contract No should be less than 50 characters", 0, NumericConstants.FIFTY, true));
        contractNo.setImmediate(true);
        contractNo.setValidationVisible(true);
        contractNo.setRequired(true);
        contractNo.setRequiredError("Contract No. should be selected on Contract Information tab");
        contractNo.setDescription(contractNo.getValue());
        contractNo.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used to Value Change Listener for contract no
             */
            public void valueChange(final ValueChangeEvent event) {
                LOGGER.debug("valueChange(ValueChangeEvent event) contractNo=" + contractNo.getValue());
                contractNo.setDescription(contractNo.getValue());
            }
        });
        
        commonUtil.loadComboBox(contractType, UIUtils.CONTRACT_TYPE, false);

        commonUtil.loadComboBox(contractStatus, UIUtils.STATUS, false);

        startDate.setValidationVisible(true);
        startDate.setImmediate(true);
        startDate.setRequired(true);
        startDate.setRequiredError("Start Date should  be selected on Contract Information tab");
        startDate.setDateFormat(Constants.MM_DD_YYYY);
        startDate.setDescription(Constants.DATE);
        startDate.setId("ContractStartDate");
        startDate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used to convert 2 Digit year to 4 Digit format by using
             * Value Change Listener for start date
             */
            public void valueChange(final ValueChangeEvent event) {
                    startDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(startDate.getValue()));
            }
        });
        startDate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the difference between dates in the term TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                termSetValue();
            }
        });
        endDate.setImmediate(true);
        endDate.setValidationVisible(true);
        endDate.setDateFormat(Constants.MM_DD_YYYY);
        endDate.setDescription(Constants.DATE);
        endDate.addValidator(new DateValidatorContract("Contract End Date should be greater than Contract Start Date"));
        endDate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used to Value Change Listener for endDate
             */
            public void valueChange(final ValueChangeEvent event) {
                endDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(endDate.getValue()));                                
            }
        });
        endDate.addListener(new Property.ValueChangeListener() {
            /**
             * Method used to value change listner for end date
             */
            public void valueChange(final ValueChangeEvent event) {

                LOGGER.debug("valueChange(ValueChangeEvent event) endDate=" + endDate.getValue());
                termSetValue();

            }
        });


        tradingPartnerName.setValidationVisible(true);
        tradingPartnerName.setImmediate(true);
        tradingPartnerName.addStyleName("searchicon");
        tradingPartnerName.setRequiredError("Trading Partner should be selected on Contract Information tab");

        tradingPartnerName.setDescription(tradingPartnerName.getValue());
        tradingPartnerName.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used to set description name by using Value Change
             * Listener for tpName
             */
            public void valueChange(final ValueChangeEvent event) {
                tradingPartnerName.setDescription(tradingPartnerName.getValue());
            }
        });

        renegotiationStartDate.setImmediate(true);
        renegotiationStartDate.setDateFormat(ContractUtils.MMDDYYYY);
        renegotiationStartDate.setDescription(Constants.DATE);
        renegotiationStartDate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used to Value Change Listener for reStartDate
             */
            public void valueChange(final ValueChangeEvent event) {
                renegotiationStartDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(renegotiationStartDate.getValue()));
            }
        });

        renegotiationEndDate.setImmediate(true);
        renegotiationEndDate.setValidationVisible(true);
        renegotiationEndDate.setDescription(Constants.DATE);
        renegotiationEndDate.addValidator(new DateValidatorReNegotiation("Renegotiation End Date should be greater than Renegotiation Start Date"));
        renegotiationEndDate.setDateFormat(ContractUtils.MMDDYYYY);
        renegotiationEndDate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used for Value change listener for reEndDate
             */
            public void valueChange(final ValueChangeEvent event) {
                renegotiationEndDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(renegotiationEndDate.getValue()));
            }
        });

        priceprotectionStartDate.setValidationVisible(true);
        priceprotectionStartDate.setImmediate(true);
        priceprotectionStartDate.setDateFormat(Constants.MM_DD_YYYY);
        priceprotectionStartDate.setDescription(Constants.DATE);
        priceprotectionStartDate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used for Value Change Listener for prStartDate
             */
            public void valueChange(final ValueChangeEvent event) {
                    priceprotectionStartDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(priceprotectionStartDate.getValue()));
            }
        });

        priceprotectionEndDate.setImmediate(true);
        priceprotectionEndDate.setValidationVisible(true);
        priceprotectionEndDate.setDescription(Constants.DATE);
        priceprotectionEndDate.addValidator(new DateValidatorPriceProtection("Price Protection End Date should be greater than Price Protection Start Date"));
        priceprotectionEndDate.setDateFormat(ContractUtils.MMDDYYYY);
        priceprotectionEndDate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used to Value Change Listener for prEndDate
             */
            public void valueChange(final ValueChangeEvent event) {
                    priceprotectionEndDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(priceprotectionEndDate.getValue()));
            }
        });

        commonUtil.loadComboBox(docType, UIUtils.DOCUMENT_TYPE, false);
        
        commonUtil.loadComboBox(docClass, UIUtils.DOCUMENT_CLASS, false);
        companyLabel.setImmediate(true);
        companyLabel.addStyleName(Constants.SEARCH_ICON_STYLE);
        companyLabel.addClickListener(new CustomTextField.ClickListener() {
            /**
             * Method used to focus listener for companyLabel text field
             */
            public void click(final CustomTextField.ClickEvent event) {
                if(lookUp==null){
                lookUp = new CompanyNameLookUp(companyName, companyLabel);
                UI.getCurrent().addWindow(lookUp);
                lookUp.addCloseListener(new Window.CloseListener() {
                    /**
                     * Method used for company Label text field and its listener
                     */
                    @SuppressWarnings("PMD")
                    public void windowClose(final Window.CloseEvent event) {
                        tradeClass.focus();
                        lookUp=null;
                    }
                });
                }
            }
        });
        term.setImmediate(true);
        term.addValidator(new StringLengthValidator("Term length should be less than 50 characters", 0, NumericConstants.FIFTY, true));
        term.setDescription(term.getValue());
        term.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used for value change event for term
             */
            public void valueChange(final ValueChangeEvent event) {
                term.setDescription(term.getValue());
            }
        });
        term.setReadOnly(true);
        commonUtil.loadComboBox(tradeClass, UIUtils.TRADE_CLASS, false);

        tradingPartnerName.addClickListener(new CustomTextField.ClickListener() {
            /**
             * Method used for focus listener event to tPName
             */
            public void click(final CustomTextField.ClickEvent event) {
                if(tradingLookUp==null){
                    tradingLookUp = new TradingPartnerLookUp(tradingPartnerSystemId, tradingPartnerName);
                    UI.getCurrent().addWindow(tradingLookUp);
                    tradingLookUp.addCloseListener(new Window.CloseListener() {
                        /**
                         * Method used for Trading Partner name textField and
                         * its focus listener
                         */
                        @SuppressWarnings("PMD")
                        public void windowClose(final Window.CloseEvent event) {
                            renegotiationStartDate.focus();
                            tradingLookUp=null;
                        }
                    });
                }
            }
        });
        
        excelExport.setIcon(new ThemeResource(ExcelExportUtil.EXCEL_EXPORT_IMAGE));
        excelExport.setStyleName("link");
        excelExport.setDescription(Constants.EXCEL_EXPORT);
        excelExport.setIconAlternateText(Constants.EXCEL_EXPORT);
        
        ValidationforDetailRadioButton();
        
        LOGGER.debug("End of getFirstTab method");
       
    }
    
    private void ValidationforDetailRadioButton() {

        insideOwner.setData("maxlengthvalidationfifty,maxlengthvalidationinsideowner,null,null");
        insideOwner.setImmediate(true);
        insideOwner.setValidationVisible(true);
        insideOwner.setDescription(insideOwner.getValue());
        insideOwner.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the description for insideOwner TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                insideOwner.setDescription(insideOwner.getValue());
            }
        });

        insidePhone.setData("maxlengthvalidationfifty,maxlengthvalidationinsidephone,numericvalidation,specialcharvalidationinsidephone");
        insidePhone.setImmediate(true);
        insidePhone.setValidationVisible(true);
        insidePhone.setDescription(insidePhone.getValue());
        insidePhone.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the description for insidePhone TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                insidePhone.setDescription(insidePhone.getValue());
            }
        });

        insideAuthor.setData("maxlengthvalidationfifty,maxlengthvalidationinsideauthor,null,null");
        insideAuthor.setImmediate(true);
        insideAuthor.setValidationVisible(true);
        insideAuthor.setDescription(insideAuthor.getValue());
        insideAuthor.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the description for insideAuthor TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                insideAuthor.setDescription(insideAuthor.getValue());
            }
        });

        insideAdditional.setData("maxlengthvalidationfifty,maxlengthvalidationinsideadditional,null,null");
        insideAdditional.setImmediate(true);
        insideAdditional.setValidationVisible(true);
        insideAdditional.setDescription(insideAdditional.getValue());
        insideAdditional.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the description for insideAdditional TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                insideAdditional.setDescription(insideAdditional.getValue());
            }
        });

        insideAdditionalName.setData("maxlengthvalidationfifty,maxlengthvalidationinsideadditionalname,null,null");
        insideAdditionalName.setImmediate(true);
        insideAdditionalName.setValidationVisible(true);
        insideAdditionalName.setDescription(insideAdditionalName.getValue());
        insideAdditionalName.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the description for insideAdditionalName TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                insideAdditionalName.setDescription(insideAdditionalName.getValue());
            }
        });

        insideAdditionalPhone.setData("maxlengthvalidationfifty,maxlengthvalidationinsideadditionalphone,numericvalidation,specialcharvalidationinsideadditionalphone");
        insideAdditionalPhone.setImmediate(true);
        insideAdditionalPhone.setValidationVisible(true);
        insideAdditionalPhone.setDescription(insideAdditionalPhone.getValue());
        insideAdditionalPhone.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the description for insideAdditionalPhone TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                insideAdditionalPhone.setDescription(insideAdditionalPhone.getValue());
            }
        });

        outsideOwner.setData("maxlengthvalidationfifty,maxlengthvalidationoutsideowner,null,null");
        outsideOwner.setImmediate(true);
        outsideOwner.setValidationVisible(true);
        outsideOwner.setDescription(outsideOwner.getValue());
        outsideOwner.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the description for outsideOwner TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                outsideOwner.setDescription(outsideOwner.getValue());
            }
        });

        outsidePhone.setData("maxlengthvalidationfifty,maxlengthvalidationoutsidephone,numericvalidation,specialcharvalidationoutsidephone");
        outsidePhone.setImmediate(true);
        outsidePhone.setValidationVisible(true);
        outsidePhone.setDescription(outsidePhone.getValue());
        outsidePhone.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the description for outsidePhone TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                outsidePhone.setDescription(outsidePhone.getValue());
            }
        });

        outsideAuthor.setData("maxlengthvalidationfifty,maxlengthvalidationoutsideauthor,null,null");
        outsideAuthor.setImmediate(true);
        outsideAuthor.setValidationVisible(true);
        outsideAuthor.setDescription(outsideAuthor.getValue());
        outsideAuthor.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the description for outsideAuthor TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                outsideAuthor.setDescription(outsideAuthor.getValue());
            }
        });

        outsideAdditional.setData("maxlengthvalidationfifty,maxlengthvalidationoutsideadditional,null,null");
        outsideAdditional.setImmediate(true);
        outsideAdditional.setValidationVisible(true);
        outsideAdditional.setDescription(outsideAdditional.getValue());
        outsideAdditional.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the description for outsideAdditional TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                outsideAdditional.setDescription(outsideAdditional.getValue());
            }
        });

        outsideAdditionalName.setData("maxlengthvalidationfifty,maxlengthvalidationoutsideadditionalname,null,null");
        outsideAdditionalName.setImmediate(true);
        outsideAdditionalName.setValidationVisible(true);
        outsideAdditionalName.setDescription(outsideAdditionalName.getValue());
        outsideAdditionalName.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the description for outsideAdditionalName TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                outsideAdditionalName.setDescription(outsideAdditionalName.getValue());
            }
        });

        outsideAdditionalPhone.setData("maxlengthvalidationfifty,maxlengthvalidationoutsideadditionalphone,numericvalidation,specialcharvalidationoutsideadditionalphone");
        outsideAdditionalPhone.setImmediate(true);
        outsideAdditionalPhone.setValidationVisible(true);
        outsideAdditionalPhone.setDescription(outsideAdditionalPhone.getValue());
        outsideAdditionalPhone.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the description for outsideAdditionalPhone TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                outsideAdditionalPhone.setDescription(outsideAdditionalPhone.getValue());
            }
        });

        affiliatedContractInfo.setData("maxlengthvalidationfifty,maxlengthvalidationaffiliatedcontractinfo,null,null");
        affiliatedContractInfo.setImmediate(true);
        affiliatedContractInfo.setValidationVisible(true);
        affiliatedContractInfo.setDescription(affiliatedContractInfo.getValue());
        affiliatedContractInfo.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the description for affiliatedContractInfo TextField.
             *
             * @param event - ValueChangeEvent
             */
            @SuppressWarnings("PMD")
            public void valueChange(final ValueChangeEvent event) {
                affiliatedContractInfo.setDescription(affiliatedContractInfo.getValue());
            }
        });

        shippingTerms.setData("maxlengthvalidationfifty,maxlengthvalidationshippingterms,null,null");
        shippingTerms.setImmediate(true);
        shippingTerms.setValidationVisible(true);
        shippingTerms.setDescription(shippingTerms.getValue());
        shippingTerms.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the description for shippingTerms TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                shippingTerms.setDescription(shippingTerms.getValue());
            }
        });

        priceEscalationClause.setData("maxlengthvalidationfifty,maxlengthvalidationpriceescalationclause,null,null");
        priceEscalationClause.setImmediate(true);
        priceEscalationClause.setValidationVisible(true);
        priceEscalationClause.setDescription(priceEscalationClause.getValue());
        priceEscalationClause.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the description for priceEscalationClause TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                priceEscalationClause.setDescription(priceEscalationClause.getValue());
            }
        });

        cancellationClause.setData("maxlengthvalidationfifty,maxlengthvalidationcancellationclause,null,null");
        cancellationClause.setImmediate(true);
        cancellationClause.setValidationVisible(true);
        cancellationClause.setDescription(cancellationClause.getValue());
        cancellationClause.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the description for cancellationClause TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                cancellationClause.setDescription(cancellationClause.getValue());
            }
        });

        mostFavoredNation.setData("maxlengthvalidationfifty,maxlengthvalidationmostfavorednation,null,null");
        mostFavoredNation.setImmediate(true);
        mostFavoredNation.setValidationVisible(true);
        mostFavoredNation.setDescription(mostFavoredNation.getValue());
        mostFavoredNation.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the description for mostFavoredNation TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                mostFavoredNation.setDescription(mostFavoredNation.getValue());
            }
        });

        category.setData("maxlengthvalidationfifty,maxlengthvalidationcategory,null,null");
        category.setImmediate(true);
        category.setValidationVisible(true);
        category.setDescription(category.getValue());
        category.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the description for category TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                category.setDescription(category.getValue());
            }
        });

        minimumOrder.setData("maxlengthvalidationfifty,maxlengthvalidationminimumorder,null,null");
        minimumOrder.setImmediate(true);
        minimumOrder.setValidationVisible(true);
        minimumOrder.setDescription(minimumOrder.getValue());
        minimumOrder.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the description for minimumOrder TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                minimumOrder.setDescription(minimumOrder.getValue());
            }
        });

        proposedStartDate.setValidationVisible(true);
        proposedStartDate.setImmediate(true);
        proposedStartDate.setDateFormat(Constants.MM_DD_YYYY);
        proposedStartDate.setDescription(Constants.DATE);
        proposedStartDate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used for Value Change Listener for prStartDate
             */
            public void valueChange(final ValueChangeEvent event) {
                proposedStartDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(proposedStartDate.getValue()));
            }
        });
        proposedEndDate.setValidationVisible(true);
        proposedEndDate.setImmediate(true);
        proposedEndDate.setDateFormat(Constants.MM_DD_YYYY);
        proposedEndDate.setDescription(Constants.DATE);
        proposedEndDate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used for Value Change Listener for prStartDate
             */
            public void valueChange(final ValueChangeEvent event) {
                proposedEndDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(proposedEndDate.getValue()));
            }
        });
        originalStartDate.setValidationVisible(true);
        originalStartDate.setImmediate(true);
        originalStartDate.setDateFormat(Constants.MM_DD_YYYY);
        originalStartDate.setDescription(Constants.DATE);
        originalStartDate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used for Value Change Listener for prStartDate
             */
            public void valueChange(final ValueChangeEvent event) {
                originalStartDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(originalStartDate.getValue()));
            }
        });
        originalEndDate.setValidationVisible(true);
        originalEndDate.setImmediate(true);
        originalEndDate.setDateFormat(Constants.MM_DD_YYYY);
        originalEndDate.setDescription(Constants.DATE);
        originalEndDate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used for Value Change Listener for prStartDate
             */
            public void valueChange(final ValueChangeEvent event) {
                originalEndDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(originalEndDate.getValue()));
            }
        });
        lastUpdatedDate.setValidationVisible(true);
        lastUpdatedDate.setImmediate(true);
        lastUpdatedDate.setDateFormat(Constants.MM_DD_YYYY);
        lastUpdatedDate.setDescription(Constants.DATE);
        lastUpdatedDate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used for Value Change Listener for prStartDate
             */
            public void valueChange(final ValueChangeEvent event) {
                lastUpdatedDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(lastUpdatedDate.getValue()));
            }
        });

        exemptFromLowPrice.setData("maxlengthvalidationfifty,maxlengthvalidationexemptFromLowPrice,numericvalidation,specialcharvalidationexemptFromLowPrice");
        exemptFromLowPrice.setImmediate(true);
        exemptFromLowPrice.setValidationVisible(true);
        exemptFromLowPrice.setDescription(exemptFromLowPrice.getValue());
        exemptFromLowPrice.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the description for exemptFromLowPrice TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                exemptFromLowPrice.setDescription(exemptFromLowPrice.getValue());
            }
        });

        advanceNoticeDays.setImmediate(true);
        advanceNoticeDays.setData("maxlengthvalidationfifty,maxlengthvalidationAdvanceNoticeDays,numericvalidation,numericvalidationAdvanceNoticeDays");
        advanceNoticeDays.setValidationVisible(true);
        advanceNoticeDays.setDescription(advanceNoticeDays.getValue());
        advanceNoticeDays.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the description for advanceNoticeDays TextField.
             *
             * @param event - ValueChangeEvent
             */
            @SuppressWarnings("PMD")
            @Override
            public void valueChange(final ValueChangeEvent event) {
                String advanceNoticeDay = advanceNoticeDays.getValue();
                if (advanceNoticeDay != null && StringUtils.isNotBlank(advanceNoticeDay) && !"null".equals(advanceNoticeDay)) {
                    advanceNoticeDays.setDescription(advanceNoticeDays.getValue());
                } else {
                    advanceNoticeDays.setValue(StringUtils.EMPTY);
                }

            }
        });
        
    }
    
    /**
     * The Class DateValidatorContract.
     */
    @SuppressWarnings("rawtypes")
    public class DateValidatorContract extends AbstractValidator {

        /**
         * The Constructor.
         */
        public DateValidatorContract() {
            super(StringUtils.EMPTY);
        }

        /**
         * The Constructor.
         *
         * @param errorMessage the error message
         */
        public DateValidatorContract(final String errorMessage) {
            super(errorMessage);
        }

        /**
         * Method used for validate validation of start date.
         *
         * @param value the value
         * @throws InvalidValueException the invalid value exception
         */
        @Override
        public void validate(final Object value) {
            LOGGER.debug("Entering validate method");

            if (startDate.getValue() != null && endDate.getValue() != null) {
                if (startDate.getValue().after(endDate.getValue())) {
                    throw new InvalidValueException("Contract End Date should be greater than Contract Start Date");
                } else if (startDate.getValue().equals(endDate.getValue())) {
                    throw new InvalidValueException("Start date and End date are equal");
                }
            }
            LOGGER.debug("End of validate method");
        }

        /**
         * Method return boolean value validate isValidValue.
         *
         * @param value the value
         * @return true, if checks if is valid value
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
         * Method used for get type.
         *
         * @return the type
         */
        @Override
        public Class getType() {
            return null;
        }
    }

    /**
     * The Class DateValidatorPriceProtection.
     */
    @SuppressWarnings("rawtypes")
    public class DateValidatorPriceProtection extends AbstractValidator {

        /**
         * The Constructor.
         */
        public DateValidatorPriceProtection() {
            super(StringUtils.EMPTY);
        }

        /**
         * The Constructor.
         *
         * @param errorMessage the error message
         */
        public DateValidatorPriceProtection(final String errorMessage) {
            super(errorMessage);
        }

        /**
         * Method used for validate validation of start date.
         *
         * @param value the value
         * @throws InvalidValueException the invalid value exception
         */
        @Override
        public void validate(final Object value) {
            LOGGER.debug("Entering validate method");

            if (priceprotectionStartDate.getValue() != null && priceprotectionEndDate.getValue() != null) {
                if (priceprotectionStartDate.getValue().after(priceprotectionEndDate.getValue())) {
                    throw new InvalidValueException("Price Protection End Date should be greater than Price Protection Start Date");
                } else if (priceprotectionStartDate.getValue().equals(priceprotectionEndDate.getValue())) {
                    throw new InvalidValueException("Price Protection Start Date and Price Protection End Date are equal");
                }
            }            
            LOGGER.debug("End of validate method");
        }

        /**
         * Method return boolean value validate isValidValue.
         *
         * @param value the value
         * @return true, if checks if is valid value
         */
        @Override
        protected boolean isValidValue(final Object value) {
            LOGGER.debug("Entering isValidValue method");

            if (priceprotectionStartDate.getValue() != null && priceprotectionEndDate.getValue() != null) {
                return priceprotectionStartDate.getValue().compareTo(priceprotectionEndDate.getValue()) <= 0;
            }
            LOGGER.debug("End of isValidValue method");
            return true;
        }

        /**
         * Method used for get the type.
         *
         * @return the type
         */
        @Override
        public Class getType() {
            return null;
        }
    }

    /**
     * The Class DateValidatorReNegotiation.
     */
    @SuppressWarnings("rawtypes")
    public class DateValidatorReNegotiation extends AbstractValidator {

        /**
         * The Constructor.
         */
        public DateValidatorReNegotiation() {
            super(StringUtils.EMPTY);
        }

        /**
         * The Constructor.
         *
         * @param errorMessage the error message
         */
        public DateValidatorReNegotiation(final String errorMessage) {
            super(errorMessage);
        }

        /**
         * Method used for validate validation of start date.
         *
         * @param value the value
         * @throws InvalidValueException the invalid value exception
         */
        @Override
        public void validate(final Object value) {
            LOGGER.debug("Entering validate()");

            if (renegotiationStartDate.getValue() != null && renegotiationEndDate.getValue() != null) {
                if (renegotiationStartDate.getValue().after(renegotiationEndDate.getValue())) {
                    throw new InvalidValueException("Renegotiation End Date should be greater than Renegotiation Start Date");
                } else if (renegotiationStartDate.getValue().equals(renegotiationEndDate.getValue())) {
                    throw new InvalidValueException("Renegotiation Start Date and Renegotiation End Date are equal");
                }
            }             
            LOGGER.debug("End of validate()");
        }

        /**
         * Method return boolean value validate isValidValue.
         *
         * @param value the value
         * @return true, if checks if is valid value
         */
        @Override
        protected boolean isValidValue(final Object value) {
            LOGGER.debug("Entering isValidValue ()");

            if (renegotiationStartDate.getValue() != null && renegotiationEndDate.getValue() != null) {
                return renegotiationStartDate.getValue().compareTo(renegotiationEndDate.getValue()) <= 0;
            }
            LOGGER.debug("End of isValidValue()");
            return true;
        }

        /**
         * Method used for get type.
         *
         * @return the type
         */
        @Override
        public Class getType() {
            return null;
        }
    }
    

    /**
     * Sets the focus on contract id.
     *
     * @param value the focus on contract id
     */
    public void setFocusOnContractId() {
        LOGGER.debug("Entering setFocusOnContractId method");
        contractId.focus();
        LOGGER.debug("End of setFocusOnContractId method");
    }
    
     /**
     * Sets the value for the term TexField based on the StartDate and EndDate.
     */
    public void termSetValue() {
        LOGGER.debug("Entering termSetValue method ");
        if (startDate.getValue() == null || endDate.getValue() == null) {
            term.setReadOnly(false);
            term.setValue("0");
            term.setReadOnly(true);

        } else {
            term.setReadOnly(false);
            final Date dateObj1 = startDate.getValue();
            final Date dateObj2 = endDate.getValue();
            final long diff = dateObj2.getTime() - dateObj1.getTime();
            final int diffDays = (int) (diff / (24 * 1000 * 60 * 60));
            if (diffDays >= Constants.ZERO) {
                term.setValue(String.valueOf(diffDays));
            }
            term.setReadOnly(true);
        }
        LOGGER.debug("termSetValue method ends ");
    }
    
    /**
     * Excel Export Logic
     * 
     * @throws PortalException
     * @throws SystemException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws Exception 
     */
    public void excelExportLogic() {
        LOGGER.debug("Entering excelExportLogic");
        try {
                excelExport.addClickListener(new Button.ClickListener() {
            /**
             * Invoked when a button is clicked
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final Button.ClickEvent event) {
                if(ConstantUtil.HEADER.equals(level.getValue().toString()) || ConstantUtil.DETAIL.equals(level.getValue().toString())){
                if(ConstantUtil.HISTORY.equals(view.getValue())){
                    try {
                        createWorkSheet();
                    } catch (Exception ex) {
                        java.util.logging.Logger.getLogger(ContractInfoTab.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    MessageBox.showPlain(Icon.INFO, "Excel Export", "Excel is not supported for "+view.getValue().toString(), new MessageBoxListener() {
                        /**
                         * After clicking button, function will be executed.
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            return;
                        }
                    }, ButtonId.OK);
                }
            }
            }
        });
            
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("Ending excelExportLogic");
    }

    /**
     * This method is used for creating worksheet
     * 
     * @throws PortalException
     * @throws SystemException
     * @throws IllegalArgumentException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws Exception 
     */
    private void createWorkSheet() throws SystemException, PortalException, NoSuchMethodException, IllegalAccessException,  InvocationTargetException {
        LOGGER.debug("Entering createWorkSheet");
        int recordCount = 0;
        ExcelExportforBB.createWorkSheet(table.getColumnHeaders(), recordCount, this, getUI(), "Contract Info");
        LOGGER.debug("Ending createWorkSheet");
    }

    /**
     * 
     * @param start
     * @param end
     * @param printWriter
     * @throws PortalException
     * @throws SystemException 
     */
    public void createWorkSheetContent() {
        LOGGER.debug("Entering createWorkSheetContent");

        LOGGER.debug("Ending createWorkSheetContent");
    }
    
      public void validateFields() {
        Collection<Field<?>> collection = contractMasterBinder.getFields();
        CommonUtils commmonUtil = CommonUtils.getInstance();
        for (Field field : collection) {
            if (field instanceof TextField) {
                TextField textField = (TextField) field;
                commmonUtil.textValidation(textField, textField.getData());
                LOGGER.debug("key ---->" + String.valueOf(textField.getData()));
            }
        }
    }
}
