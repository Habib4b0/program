
package com.stpl.app.contract.dashboard.ui.form;

import com.stpl.app.contract.abstractsearch.util.ConstantUtil;
import com.stpl.app.contract.bpm.dto.WorkflowMasterDTO;
import com.stpl.app.contract.bpm.logic.BPMLogic;
import com.stpl.app.contract.bpm.logic.WorkflowLogic;
import com.stpl.app.contract.common.dto.SessionDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

import com.stpl.app.contract.common.util.CommonUtil;
import com.stpl.app.contract.common.util.HelperListUtil;
import com.stpl.app.contract.contractheader.dto.ContractAliasMasterDTO;
import com.stpl.app.contract.contractheader.dto.ContractMasterDTO;
import com.stpl.app.contract.dao.impl.ContractHeaderLogicDAOImpl;
import com.stpl.app.contract.dashboard.dto.PriceScheduleDto;
import com.stpl.app.contract.dashboard.dto.TempPricingDTO;
import com.stpl.app.contract.dashboard.dto.TempRebateDTO;
import com.stpl.app.contract.dashboard.logic.DashBoardLogic;
import com.stpl.app.contract.dashboard.ui.view.DashboardView;
import com.stpl.app.contract.dashboard.util.ExcelExportUtil;
import com.stpl.app.contract.global.dto.CFPCompanyDTO;
import com.stpl.app.contract.global.dto.CompanyMasterDTO;
import com.stpl.app.contract.global.dto.ItemMasterDTO;
import com.stpl.app.contract.global.dto.RsItemDetailsDTO;
import com.stpl.app.contract.global.dto.VwContractPriceInfoDTO;
import com.stpl.app.contract.global.logic.CFPSearchLogic;
import com.stpl.app.contract.global.logic.IfpLogic;
import com.stpl.app.contract.global.logic.NotesTabLogic;
import com.stpl.app.contract.ui.NotesTabForm;
import com.stpl.app.contract.util.AbstractNotificationUtils;
import com.stpl.app.contract.util.CommonUIUtils;
import com.stpl.app.contract.util.Constants;
import com.stpl.app.contract.util.ErrorCodeUtil;
import com.stpl.app.contract.util.ErrorCodes;
import com.stpl.app.model.ContractMaster;
import com.stpl.domain.contract.base.EditBaseForm;
import com.stpl.domain.contract.contractheader.ContractHeaderDAO;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.constants.WorkflowConstants;
import com.stpl.ifs.util.constants.WorkflowMessages;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.stpl.portal.service.UserLocalServiceUtil;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.themes.ValoTheme;

import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.text.SimpleDateFormat;
import org.kie.api.runtime.process.ProcessInstance;

/**
 * The Class ContractDashboardEditForm.
 */
public final class ContractDashboardEditForm extends CustomComponent implements EditBaseForm {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ContractDashboardEditForm.class);

    /**
     * The tabsheet.
     */
    @UiField("tabSheet")
    private TabSheet tabsheet;
    
    @UiField("errorLB")
    private ErrorLabel errorLabel;

    @UiField("saveBtn")
    private Button btnUpdate; // it will not visible in screen (as per issue GAL-2906)

    @UiField("deleteBtn")
    private Button deleteButton;

    @UiField("backBtn")
    private Button backButton;

    @UiField("resetBtn")
    private Button resetButton;

    @UiField("excelBtn")
    private Button excelExport;

    @UiField("submitBtn")
    private Button submitButton;
    @UiField("approveBtn")
    private Button approveButton;
    @UiField("rejectBtn")
    private Button rejectButton;
    @UiField("cancelBtn")
    private Button cancelButton;
    @UiField("closeBtn")
    private Button closeButton;
    
    @UiField("withdrawBtn")
    private Button withdrawButton;

    /**
     * The item map.
     */
    private final Map<String, String> itemMap = new HashMap<>();
    /**
     * The item details map.
     */
    private final Map<String, String> itemDetailsMap = new HashMap<>();
    /**
     * The company details map.
     */
    private final Map<String, String> companyDetailsMap = new HashMap<>();
    /**
     * The company map.
     */
    private final Map<String, String> companyMap = new HashMap<>();

    /**
     * The rebate map.
     */
    private final Map<String, String> rebateMap = new HashMap<>();
    /**
     * The contract master.
     */
    private final ContractMasterDTO contractMaster;

    private final CFPCompanyDTO cfpCompanyDTO;
    /**
     * The contract master binder.
     */
    private CustomFieldGroup contractMasterBinder;

    private CustomFieldGroup cfpContractBinder;
    /**
     * The alias master results beans.
     */
    private final BeanItemContainer<ContractAliasMasterDTO> aliasMasterResultsbeans;
    /**
     * The selected company result bean.
     */
    private final BeanItemContainer<CompanyMasterDTO> selectedCompanyResultBean;
    /**
     * The cfp results bean.
     */
    private final BeanItemContainer<CFPCompanyDTO> cfpResultsBean;

    private BeanItemContainer<CFPCompanyDTO> saveContainer;

    private BeanItemContainer<TempPricingDTO> saveIFPContainer;

    private BeanItemContainer<TempPricingDTO> savePSContainer;
    /**
     * The selected item result bean.
     */
    private final BeanItemContainer<ItemMasterDTO> selectedItemResultBean;
    /**
     * The item details results bean.
     */
    private final BeanItemContainer<VwContractPriceInfoDTO> itemDetailsResultsBean;
    /**
     * The rs details results bean.
     */
    private final BeanItemContainer<TempRebateDTO> rsDetailsResultsBean;
    /**
     * The rebate binder.
     */
    private final CustomFieldGroup rebateBinder;
    /**
     * The space.
     */
    private final Label space = new Label("&nbsp;", ContentMode.HTML);
    /**
     * The contract info tab.
     */
    private final ContractInfoTab contractInfoTab;
    /**
     * Company Addition Tab.
     */
    private final CompanyAdditionTab companyAdditionTab;

    private final CompaniesTab companiesTab;

    private final ItemAdditionTab itemAdditionTab;

    private final ItemPricing itemPricing;


    private final RebateScheduleInformation rebateScheduleInformation;

    private boolean isEditable;

    RsItemDetailsDTO rsItemDetailsDTO;

    /**
     * The dash board logic.
     */
    private final DashBoardLogic dashBoardLogic;

    private NotesTabLogic notesTabLogic = new NotesTabLogic();

    private CommonUtil util = new CommonUtil();
    /**
     * The alias tab.
     */
    private final AliasTab aliasTab;
    /**
     * The Items Tab
     */
    private final Items itemsTab;

    private final CFPSearchLogic cfpLogic;

    private final IfpLogic ifpLogic;

    private final ContractHeaderDAO daoImpl = new ContractHeaderLogicDAOImpl();

    SessionDTO sessionDTO;

    NotesTabForm notesTabForm;

    SessionDTO session;

    final CustomFieldGroup pricingBinderEdit;

    final PriceScheduleDto priceScheduleMaster;

    private static final WorkflowLogic WORKFLOW_LOGIC = new WorkflowLogic();

    private final User userModel;
    private final Integer[] contractStructure;
    private final List<String> roleList = new ArrayList();
    private final Map<Integer, String> idDescMap = HelperListUtil.getInstance().getIdDescMap();
    final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
    
    private boolean cfpModifiedDateUpdateFalg = false;

    /**
     * Gets the contract binder.
     *
     * @return the contract binder
     */
    public CustomFieldGroup getContractMasterBinder() {
        return contractMasterBinder;
    }

    /**
     * Sets the contract binder.
     *
     * @param contractBinder the contract binder
     */
    public void setContractBinder(final CustomFieldGroup contractBinder) {
        this.contractMasterBinder = contractBinder;
    }

    public CustomFieldGroup getCfpContractBinder() {
        return cfpContractBinder;
    }

    public void setCfpContractBinder(CustomFieldGroup cfpContractBinder) {
        this.cfpContractBinder = cfpContractBinder;
    }

    /**
     * Gets the item map.
     *
     * @return the item map
     */
    public Map<String, String> getItemMap() {
        return itemMap;
    }

    /**
     * Gets the item details map.
     *
     * @return the item details map
     */
    public Map<String, String> getItemDetailsMap() {
        return itemDetailsMap;
    }

    /**
     * Gets the company details map.
     *
     * @return the company details map
     */
    public Map<String, String> getCompanyDetailsMap() {
        return companyDetailsMap;
    }

    /**
     * Gets the company map.
     *
     * @return the company map
     */
    public Map<String, String> getCompanyMap() {
        return companyMap;
    }

    /**
     * Gets the rebate map.
     *
     * @return the rebate map
     */
    public Map<String, String> getRebateMap() {
        return rebateMap;
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
     * Gets the alias master resultsbeans.
     *
     * @return the alias master resultsbeans
     */
    public BeanItemContainer<ContractAliasMasterDTO> getAliasMasterResultsbeans() {
        return aliasMasterResultsbeans;
    }

    /**
     * Gets the selected company result bean.
     *
     * @return the selected company result bean
     */
    public BeanItemContainer<CompanyMasterDTO> getSelectedCompanyResultBean() {
        return selectedCompanyResultBean;
    }

    /**
     * Gets the cfp results bean.
     *
     * @return the cfp results bean
     */
    public BeanItemContainer<CFPCompanyDTO> getCfpResultsBean() {
        return cfpResultsBean;
    }

    /**
     * Gets the selected item result bean.
     *
     * @return the selected item result bean
     */
    public BeanItemContainer<ItemMasterDTO> getSelectedItemResultBean() {
        return selectedItemResultBean;
    }

    /**
     * Gets the item details results bean.
     *
     * @return the item details results bean
     */
    public BeanItemContainer<VwContractPriceInfoDTO> getItemDetailsResultsBean() {
        return itemDetailsResultsBean;
    }

    /**
     * Gets the rs details results bean.
     *
     * @return the rs details results bean
     */
    public BeanItemContainer<TempRebateDTO> getRsDetailsResultsBean() {
        return rsDetailsResultsBean;
    }

    /**
     * Gets the rebate binder.
     *
     * @return the rebate binder
     */
    public CustomFieldGroup getRebateBinder() {
        return rebateBinder;
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
     * Gets the contract info tab.
     *
     * @return the contract info tab
     */
    public ContractInfoTab getContractInfoTab() {
        return contractInfoTab;
    }

    /**
     * Gets the alias tab.
     *
     * @return the alias tab
     */
    public AliasTab getAliasTab() {
        return aliasTab;
    }

    /**
     * Gets the tabsheet.
     *
     * @return the tabsheet
     */
    public TabSheet getTabsheet() {
        return tabsheet;
    }

    /**
     * The Constructor for ContractDashboardEditForm.
     *
     * @param contractMaster the contract master
     * @param contractBinder the contract master binder
     * @param aliasMRBeans the alias master results beans
     * @param availableBean the available company result bean
     * @param selectedComBean the selected com bean
     * @param cfpResultsBean the cfp results bean
     * @param saveContainer
     * @param availableIRBean the available item result bean
     * @param selectedIRBean the selected ir bean,
     * @param itemDRBean the item dr bean
     * @param saveIFPContainer the save ifp container
     * @param rsItemDetailsDTO the dto
     * @param rebateBinder the rebate binder
     * @param rsDetailsBean the rs details results bean
     * @param isEditable the flag
     */
    public ContractDashboardEditForm(final ContractMasterDTO contractMaster, final CustomFieldGroup contractBinder, final CFPCompanyDTO cfpCompanyDTO, final CustomFieldGroup cfpContractBinderedit, final BeanItemContainer<ContractAliasMasterDTO> aliasMRBeans,
            final BeanItemContainer<CompanyMasterDTO> availableBean, final BeanItemContainer<CompanyMasterDTO> selectedComBean, final BeanItemContainer<CFPCompanyDTO> cfpResultsBean, final BeanItemContainer<CFPCompanyDTO> saveContainer,
            final BeanItemContainer<ItemMasterDTO> availableIRBean, final BeanItemContainer<ItemMasterDTO> selectedIRBean, final BeanItemContainer<TempPricingDTO> saveIFPContainer, final BeanItemContainer<TempPricingDTO> savePSContainer, final BeanItemContainer<VwContractPriceInfoDTO> itemDRBean,
            final RsItemDetailsDTO rsItemDetailsDTO, final CustomFieldGroup rebateBinder, final BeanItemContainer<TempRebateDTO> rsDetailsBean, final boolean isEditable, final SessionDTO session, final CustomFieldGroup pricingBinderEdit, final PriceScheduleDto priceScheduleMaster) throws SystemException,
            PortalException{
        super();
        LOGGER.debug("Entering ContractDashboardEditForm");
        this.contractMaster = contractMaster;
        this.cfpCompanyDTO = cfpCompanyDTO;
        this.contractMasterBinder = contractBinder;
        this.cfpContractBinder = cfpContractBinderedit;
        this.aliasMasterResultsbeans = aliasMRBeans;
        this.selectedCompanyResultBean = selectedComBean;
        this.cfpResultsBean = cfpResultsBean;
        this.saveContainer = saveContainer;
        this.saveIFPContainer = saveIFPContainer;
        this.savePSContainer = savePSContainer;
        this.selectedItemResultBean = selectedIRBean;
        this.itemDetailsResultsBean = itemDRBean;
        this.rebateBinder = rebateBinder;
        this.rsDetailsResultsBean = rsDetailsBean;
        this.rsItemDetailsDTO = rsItemDetailsDTO;
        this.sessionDTO = session;
        this.pricingBinderEdit = pricingBinderEdit;
        this.priceScheduleMaster = priceScheduleMaster;
        dashBoardLogic = new DashBoardLogic(this.sessionDTO);
        cfpLogic = new CFPSearchLogic(this.sessionDTO);
        ifpLogic = new IfpLogic(this.sessionDTO);
        this.contractInfoTab = new ContractInfoTab(contractMaster, contractBinder);
        this.itemAdditionTab = new ItemAdditionTab(saveIFPContainer, itemDRBean, itemMap, isEditable, this.sessionDTO);
        this.itemPricing = new ItemPricing(selectedIRBean, saveIFPContainer, itemDRBean, itemMap, isEditable, this.sessionDTO, savePSContainer, pricingBinderEdit, priceScheduleMaster);
        this.companyAdditionTab = new CompanyAdditionTab(availableBean, selectedComBean, cfpResultsBean, saveContainer, companyMap, contractBinder, isEditable, this.sessionDTO);
        this.companiesTab = new CompaniesTab(availableBean, selectedComBean, cfpResultsBean, saveContainer, companyMap, cfpContractBinder, isEditable, this.sessionDTO, this.cfpCompanyDTO, contractBinder);
        this.aliasTab = new AliasTab(contractBinder, aliasMRBeans, isEditable);
        this.rebateScheduleInformation = new RebateScheduleInformation(rsItemDetailsDTO, rebateBinder, rsDetailsBean, contractBinder, isEditable, this.sessionDTO);
        this.itemsTab = new Items(saveIFPContainer, itemDRBean,isEditable, this.sessionDTO);
        this.notesTabForm = new NotesTabForm(contractBinder, "Contract Dashboard", "CONTRACT_MASTER", "contractSystemId", isEditable ? "Edit" : "View");
        this.isEditable = isEditable;
        userModel = UserLocalServiceUtil.getUser(Long.parseLong(sessionDTO.getUserId()));
        contractStructure = new Integer[]{sessionDTO.getCfpSystemId(), sessionDTO.getIfpSystemId(), sessionDTO.getPsSystemId(), sessionDTO.getRsSystemId()};
        sessionDTO.setWorkflow(WORKFLOW_LOGIC.getWorkflowMasterByContractSid(sessionDTO.getContractSystemId(), contractStructure));
        init();
        LOGGER.debug("End of ContractDashboardEditForm");
    }

    /**
     * Inits Method load while calling constructor.
     */
    public void init() throws SystemException, PortalException {
        LOGGER.debug("Entering init method");
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/declarative-ui/tab-sheet-form.xml"), this));
        addToTabbar();
        configureTabSheet();
        configureBinder();
        configureFields();
        configureButtons();
        LOGGER.debug("End of init method");
    }

    /**
     * @throws Exception
     * @throws SystemException
     * @throws PortalException
     *
     */
    private void configureTabSheet() {


        final int contractSystemId = (Integer) (sessionDTO.getContractSystemId());
        final int cfpContractSId = (Integer) (sessionDTO.getCfpSystemId());
        final int ifpSystemId = (Integer) (sessionDTO.getIfpSystemId());
        final int psSystemId = (Integer) (sessionDTO.getPsSystemId());
        final int rsSystemId = (Integer) (sessionDTO.getRsSystemId());
        tabsheet.getTab(NumericConstants.TWO).setVisible(false);
        tabsheet.getTab(NumericConstants.THREE).setVisible(false);
        tabsheet.getTab(NumericConstants.FOUR).setVisible(false);
        tabsheet.getTab(NumericConstants.FIVE).setVisible(false);
        tabsheet.getTab(NumericConstants.SIX).setVisible(false);

        tabsheet.getTab(NumericConstants.SEVEN).setVisible(false);
        if (cfpContractSId != Constants.ZERO) {

            tabsheet.getTab(NumericConstants.TWO).setVisible(true);

            tabsheet.getTab(NumericConstants.THREE).setVisible(true);
        }
        if (ifpSystemId != 0) {
            tabsheet.getTab(NumericConstants.FOUR).setVisible(true);
            tabsheet.getTab(NumericConstants.FIVE).setVisible(true);
        }
        if (psSystemId != 0) {
            LOGGER.debug(" Logic.getContractPriceDetails(final int contractSystemId=" + contractSystemId + ", int cfpId="
                    + cfpContractSId + ",int ifpId=" + ifpSystemId + ",int psId"
                    + psSystemId + ", int rsId=" + rsSystemId + ") ");

            if (!tabsheet.getTab(NumericConstants.FOUR).isVisible()) {
                tabsheet.getTab(NumericConstants.FOUR).setVisible(true);
            }

            tabsheet.getTab(NumericConstants.SIX).setVisible(true);

        }
        if (rsSystemId != Constants.ZERO) {
            LOGGER.debug(" Logic.getRsDetails(final int contractSystemId=" + contractSystemId + ", int cfpId="
                    + cfpContractSId + ",int ifpId=" + ifpSystemId + ",int psId"
                    + psSystemId + ", int rsId=" + rsSystemId + ") ");

            if (!tabsheet.getTab(NumericConstants.FOUR).isVisible()) {
                tabsheet.getTab(NumericConstants.FOUR).setVisible(true);
            }
            tabsheet.getTab(NumericConstants.SEVEN).setVisible(true);
            }

    }
    /**
     * Gets the binder.
     *
     * @return the binder
     */
    private CustomFieldGroup configureBinder() {
        LOGGER.debug("Entering getBinder method");
        contractMasterBinder.bindMemberFields(this);
        contractMasterBinder.setItemDataSource(new BeanItem<>(contractMaster));
        contractMasterBinder.setBuffered(true);
        contractMasterBinder.setErrorDisplay(errorLabel);
        rebateBinder.setErrorDisplay(errorLabel);
        cfpContractBinder.setErrorDisplay(errorLabel);
        itemsTab.getBinder().setErrorDisplay(errorLabel);
        itemPricing.getPricingBinderEdit().setErrorDisplay(errorLabel);
        LOGGER.debug("End of getBinder method");
        return contractMasterBinder;
    }

    /**
     * Method used for Adds to tabbar.
     *
     * @return the tab sheet
     */
    public void addToTabbar() {

        LOGGER.debug("Entering addToTabbar method");

        tabsheet.markAsDirty();
        tabsheet.markAsDirtyRecursive();
        tabsheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
        tabsheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);

        contractInfoTab.setCaption(ConstantUtil.CONTRACT_INFORMATION);
        tabsheet.addTab(contractInfoTab, ConstantUtil.CONTRACT_INFORMATION);

        aliasTab.setCaption(ConstantUtil.ALIAS);
        tabsheet.addTab(aliasTab, ConstantUtil.ALIAS);

        companyAdditionTab.setCaption(ConstantUtil.COMPANY_ADDITION);
        tabsheet.addTab(companyAdditionTab, ConstantUtil.COMPANY_ADDITION);

        companiesTab.setCaption(ConstantUtil.COMPANIES);
        tabsheet.addTab(companiesTab, ConstantUtil.COMPANIES);

        itemAdditionTab.setCaption(ConstantUtil.ITEM_ADDITION);
        tabsheet.addTab(itemAdditionTab, ConstantUtil.ITEM_ADDITION);

        itemsTab.setCaption(Constants.ITEMS);
        tabsheet.addTab(itemsTab, Constants.ITEMS);
        itemPricing.setCaption(ConstantUtil.PRICING);
        tabsheet.addTab(itemPricing, ConstantUtil.PRICING);
        rebateScheduleInformation.setCaption(ConstantUtil.REBATE);
        tabsheet.addTab(rebateScheduleInformation, ConstantUtil.REBATE);

        notesTabForm.setCaption(ConstantUtil.NOTES);
        tabsheet.addTab(notesTabForm, ConstantUtil.NOTES);
        notesTabForm.refreshTable();

        tabsheet.addSelectedTabChangeListener(new SelectedTabChangeListener() {
            /**
             * Method used for selected Tab Change
             */
            @SuppressWarnings("PMD")
            public void selectedTabChange(final SelectedTabChangeEvent event) {
                try {
                    final String tabname = event.getTabSheet().getSelectedTab().getCaption();
                    contractMasterBinder.getErrorDisplay().clearError();
                    if ("contractInfo".equals(tabname)) {
                        contractMasterBinder.getField("contractId").focus();
                    }
                    if (ConstantUtil.ALIAS.equals(tabname)) {
                        aliasTab.getTpCompanyMasterSid().focus();
                    }
                    if ((Integer) sessionDTO.getCfpSystemId() != Constants.ZERO) {

                        if (ConstantUtil.COMPANY_ADDITION.equals(tabname)) {
                            companyAdditionTab.focusSearchFields();
                            companyAdditionTab.refreshTable();
                        }

                        if (ConstantUtil.COMPANIES.equals(tabname)) {
                            if (isEditable) {
                                loadEffectiveDates("CFP");
                                companiesTab.focusMassCheck();
                                if (!cfpModifiedDateUpdateFalg) {
                                    cfpModifiedDateUpdateFalg = cfpLogic.updateCFPModifiedDate(sessionDTO.getUserId(), sessionDTO.getUiSessionId());
                                }
                                companiesTab.removeItemsFromSaveContainer();
                                companiesTab.loadTempCfp();
                            } else {
                                companiesTab.loadTempCfpOnView(StringUtils.EMPTY);
                            }
                        }
                    }
                    if ((Integer) sessionDTO.getIfpSystemId() != 0 || (Integer) sessionDTO.getPsSystemId() != 0) {
                        if (ConstantUtil.ITEM_ADDITION.equals(tabname)) {
                            itemAdditionTab.focusSearchField();
                            itemAdditionTab.refreshTable();
                        }
                        if (Constants.ITEMS.equals(tabname)) {
                            if (isEditable) {
                                loadEffectiveDates("IFP");
                                itemsTab.loadTempIfp();
                            } else {
                                pricingBinderEdit.getField("priceScheduleStatus").setReadOnly(true);
                                pricingBinderEdit.getField("priceScheduleStartDate").setReadOnly(true);
                                pricingBinderEdit.getField("priceScheduleEndDate").setReadOnly(true);
                                pricingBinderEdit.getField("priceScheduleDesignation").setReadOnly(true);
                                pricingBinderEdit.getField("priceScheduleType").setReadOnly(true);
                                pricingBinderEdit.getField("priceScheduleCategory").setReadOnly(true);
                                pricingBinderEdit.getField("tradeClass").setReadOnly(true);
                            }
                        }
                        if (ConstantUtil.PRICING.equals(tabname)) {
                            itemPricing.focusMassCheck();
                            itemPricing.removeItemsFromSaveContainer();
                            itemPricing.loadTempIfp();
                            itemPricing.loadPpTempIfp();
                            loadEffectiveDates("PS");
                        }
                        
                    }
                    if ((Integer) sessionDTO.getRsSystemId() != Constants.ZERO && ConstantUtil.REBATE.equals(tabname)) {
                            if (Constants.DETAILS.equals((rebateScheduleInformation.level).getValue().toString())) {
                                rebateScheduleInformation.loadRebateSetupTab();
                            }
                            loadEffectiveDates("RS");
                    }

                    if (ConstantUtil.NOTES.equals(tabname)) {
                        notesTabForm.callJavaScriptForButton();
                        notesTabForm.focusNewNote();
                        notesTabForm.setUploaderValue(StringUtils.EMPTY);
                    }

                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        });
        LOGGER.debug("End of addToTabbar method");
    }

    private void loadEffectiveDates(String value) {
        try {
            Date startDate;
            Date endDate;
            switch (value) {
                case "CFP":
                    startDate = (Date) contractMasterBinder.getField(Constants.START_DATE).getValue() == null ? null : fmt.parse(fmt.format((Date) contractMasterBinder.getField(Constants.START_DATE).getValue()));
                    endDate = (Date) contractMasterBinder.getField(Constants.END_DATE).getValue() == null ? null : fmt.parse(fmt.format((Date) contractMasterBinder.getField(Constants.END_DATE).getValue()));
                    companiesTab.loadDates(startDate, endDate);
                    break;
                case "IFP":
                    if ((Integer) sessionDTO.getCfpSystemId() != Constants.ZERO) {
                        startDate = companiesTab.getCompanyFamilyPlanStartDate().getValue() == null ? null : fmt.parse(fmt.format(companiesTab.getCompanyFamilyPlanStartDate().getValue()));
                        endDate = companiesTab.getCompanyFamilyPlanEndDate().getValue() == null ? null : fmt.parse(fmt.format(companiesTab.getCompanyFamilyPlanEndDate().getValue()));
                        itemsTab.loadDates(startDate, endDate);
                    } else {
                        startDate = (Date) contractMasterBinder.getField(Constants.START_DATE).getValue() == null ? null : fmt.parse(fmt.format((Date) contractMasterBinder.getField(Constants.START_DATE).getValue()));
                        endDate = (Date) contractMasterBinder.getField(Constants.END_DATE).getValue() == null ? null : fmt.parse(fmt.format((Date) contractMasterBinder.getField(Constants.END_DATE).getValue()));
                        itemsTab.loadDates(startDate, endDate);
                    }
                    break;
                case "PS":
                    if ((Integer) sessionDTO.getIfpSystemId() != Constants.ZERO) {
                        startDate = itemsTab.getStartDate().getValue() == null ? null : fmt.parse(fmt.format(itemsTab.getStartDate().getValue()));
                        endDate = itemsTab.getEndDate().getValue() == null ? null : fmt.parse(fmt.format(itemsTab.getEndDate().getValue()));
                        itemPricing.loadDates(startDate, endDate);
                    } else if ((Integer) sessionDTO.getCfpSystemId() != Constants.ZERO) {
                        startDate = companiesTab.getCompanyFamilyPlanStartDate().getValue() == null ? null : fmt.parse(fmt.format(companiesTab.getCompanyFamilyPlanStartDate().getValue()));
                        endDate = companiesTab.getCompanyFamilyPlanEndDate().getValue() == null ? null : fmt.parse(fmt.format(companiesTab.getCompanyFamilyPlanEndDate().getValue()));
                        itemPricing.loadDates(startDate, endDate);
                    } else {
                        startDate = (Date) contractMasterBinder.getField(Constants.START_DATE).getValue() == null ? null : fmt.parse(fmt.format((Date) contractMasterBinder.getField(Constants.START_DATE).getValue()));
                        endDate = (Date) contractMasterBinder.getField(Constants.END_DATE).getValue() == null ? null : fmt.parse(fmt.format((Date) contractMasterBinder.getField(Constants.END_DATE).getValue()));
                        itemPricing.loadDates(startDate, endDate);
                    }
                    break;
                case "RS":
                    if ((Integer) sessionDTO.getPsSystemId() != Constants.ZERO) {
                        startDate = itemPricing.getPriceScheduleStartDate().getValue() == null ? null : fmt.parse(fmt.format(itemPricing.getPriceScheduleStartDate().getValue()));
                        endDate = itemPricing.getPriceScheduleEndDate().getValue() == null ? null : fmt.parse(fmt.format(itemPricing.getPriceScheduleEndDate().getValue()));
                        rebateScheduleInformation.loadDates(startDate, endDate);
                    } else if ((Integer) sessionDTO.getIfpSystemId() != Constants.ZERO) {
                        startDate = itemsTab.getStartDate().getValue() == null ? null : fmt.parse(fmt.format(itemsTab.getStartDate().getValue()));
                        endDate = itemsTab.getEndDate().getValue() == null ? null : fmt.parse(fmt.format(itemsTab.getEndDate().getValue()));
                        rebateScheduleInformation.loadDates(startDate, endDate);
                    } else if ((Integer) sessionDTO.getCfpSystemId() != Constants.ZERO) {
                        startDate = companiesTab.getCompanyFamilyPlanStartDate().getValue() == null ? null : fmt.parse(fmt.format(companiesTab.getCompanyFamilyPlanStartDate().getValue()));
                        endDate = companiesTab.getCompanyFamilyPlanEndDate().getValue() == null ? null : fmt.parse(fmt.format(companiesTab.getCompanyFamilyPlanEndDate().getValue()));
                        rebateScheduleInformation.loadDates(startDate, endDate);
                    } else {
                        startDate = (Date) contractMasterBinder.getField(Constants.START_DATE).getValue() == null ? null : fmt.parse(fmt.format((Date) contractMasterBinder.getField(Constants.START_DATE).getValue()));
                        endDate = (Date) contractMasterBinder.getField(Constants.END_DATE).getValue() == null ? null : fmt.parse(fmt.format((Date) contractMasterBinder.getField(Constants.END_DATE).getValue()));
                        rebateScheduleInformation.loadDates(startDate, endDate);
                    }
                    break;
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Method used for Configure value in the Configure fields.
     */
    public void configureFields() {
        LOGGER.debug("Entering configureFields method");
        contractInfoTab.setFocusOnContractId();
        LOGGER.debug("End of configureFields method");
    }

    public void configureButtons() {
        LOGGER.debug("Entering  DashboardEditActionButtonLayout init method");
        btnUpdate.setVisible(false); // it will not visible in screen (as per issue GAL-2906)
        resetButton.setVisible(false);
        deleteButton.setVisible(false);
        backButton();
        excelExport.setVisible(false);
        excelExport();
        /**
         * if sessionDTO.getWorkflow() is null means that contract is not
         * submitted
         */
        submitButton();
        approveButton();
        rejectButton();
        cancelButton();
        closeButton();
        withdrawButton();
        submitButton.setVisible(true);
        approveButton.setVisible(true);
        rejectButton.setVisible(true);
        cancelButton.setVisible(true);
        closeButton.setVisible(true);
        withdrawButton.setVisible(true);

        if (sessionDTO.getWorkflowStatus() != null) {
            backButton.setVisible(false);
            if (WorkflowConstants.getApproverConstant().equals(sessionDTO.getUserType())) {
                submitButton.setVisible(false);
                withdrawButton.setVisible(false);
                boolean buttonVisiblity = WorkflowConstants.getPendingStatus().equalsIgnoreCase(sessionDTO.getWorkflowStatus());
                approveButton.setVisible(buttonVisiblity);
                rejectButton.setVisible(buttonVisiblity);
                cancelButton.setVisible(buttonVisiblity);
                closeButton.setVisible(true);
            } else if (WorkflowConstants.getCreatorConstant().equals(sessionDTO.getUserType())) {
                approveButton.setVisible(false);
                rejectButton.setVisible(false);
                cancelButton.setVisible(false);
                closeButton.setVisible(true);
                if (WorkflowConstants.getPendingStatus().equalsIgnoreCase(sessionDTO.getWorkflowStatus())
                        || WorkflowConstants.getApprovedStatus().equals(sessionDTO.getWorkflowStatus())
                        || WorkflowConstants.getCancelledStatus().equals(sessionDTO.getWorkflowStatus())) {
                    submitButton.setVisible(false);
                    withdrawButton.setVisible(true);
                    cancelButton.setVisible(false);
                } else if (WorkflowConstants.getWithdrawnStatus().equalsIgnoreCase(sessionDTO.getWorkflowStatus()) || WorkflowConstants.getRejectedStatus().equalsIgnoreCase(sessionDTO.getWorkflowStatus())) {
                    submitButton.setVisible(true);
                    withdrawButton.setVisible(false);
                    cancelButton.setVisible(false);
                } 
            }
        } else {
            approveButton.setVisible(false);
            rejectButton.setVisible(false);
            cancelButton.setVisible(false);
            closeButton.setVisible(false);
            submitButton.setVisible(false);
            withdrawButton.setVisible(false);
            if (sessionDTO.getWorkflow() == null) {
                submitButton.setVisible(true);
            } else {
                submitButton.setVisible(true);
                if (WorkflowConstants.getPendingStatus().equalsIgnoreCase(idDescMap.get(sessionDTO.getWorkflow().getWorkflowStatusId()))) {
                    submitButton.setEnabled(false);
                }
            }
        }

        LOGGER.debug("End of DashboardEditActionButtonLayout init method");
    }

    private boolean updateBtnValidation() throws CommitException, SystemException, PortalException{
        try{
            CFPSearchLogic cfpSearchLogic = new CFPSearchLogic(sessionDTO);
                    LOGGER.debug("Entering btnUpdate buttonClick method");
                    final int contractSystemId = (Integer) sessionDTO.getContractSystemId();
                    contractMasterBinder.getErrorDisplay().clearError();
                    if (StringUtils.isBlank(String.valueOf(contractMasterBinder.getField(Constants.CONTRACT_ID).getValue()))) {
                        contractMasterBinder.getErrorDisplay().setError("Contract ID should be entered on Contract Header tab");
                        return false;
                    } else if (StringUtils.isBlank(String.valueOf(contractMasterBinder.getField(Constants.CONTRACT_NO).getValue()))) {
                        contractMasterBinder.getErrorDisplay().setError("Contract No should be entered on Contract Header tab");
                        return false;
                    } else if (StringUtils.isBlank(String.valueOf(contractMasterBinder.getField(Constants.CONTRACT_NAME).getValue()))) {
                        contractMasterBinder.getErrorDisplay().setError("Contract Name should be entered on Contract Header tab");
                        return false;
                    } else if (StringUtils.isBlank(String.valueOf(contractMasterBinder.getField(Constants.CONTRACT_STATUS).getValue())) || 
                            (String.valueOf(contractMasterBinder.getField(Constants.CONTRACT_STATUS).getValue()).equals("0")) || String.valueOf(contractMasterBinder.getField(Constants.CONTRACT_STATUS).getValue()).equals(Constants.NULL)) {
                        contractMasterBinder.getErrorDisplay().setError("Contract Status should be entered on Contract Header tab");
                        return false;
                    } else if (StringUtils.isBlank(String.valueOf(contractMasterBinder.getField(Constants.CONTRACT_TYPE).getValue())) ||
                            (String.valueOf(contractMasterBinder.getField(Constants.CONTRACT_TYPE).getValue()).equals("0")) || String.valueOf(contractMasterBinder.getField(Constants.CONTRACT_TYPE).getValue()).equals(Constants.NULL)) {
                        contractMasterBinder.getErrorDisplay().setError("Contract Type should be entered on Contract Header tab");
                        return false;
                    } else if (contractMasterBinder.getField(Constants.START_DATE).getValue() == null) {
                        contractMasterBinder.getErrorDisplay().setError("Start Date should be entered on Contract Header tab");
                        return false;
                    } else if (contractMasterBinder.getField(Constants.TERM).getValue() == null) {
                        contractMasterBinder.getErrorDisplay().setError("Start Date should be entered on Contract Header tab");
                        return false;

                    } else if (contractMasterBinder.getField(Constants.TRADING_PARTNER_NAME).getValue() == null || contractMasterBinder.getField(Constants.TRADING_PARTNER_NAME).getValue().equals(StringUtils.EMPTY)) {
                        contractMasterBinder.getErrorDisplay().setError("Trading Partner should be selected on Contract Information tab");
                        return false;

                    }
                    
                    else {
                        cfpSearchLogic.saveToTempCFP(saveContainer.getItemIds());

                        String contractID = contractMasterBinder.getField(Constants.CONTRACT_ID).getValue().toString().trim();
                        String contractNo = contractMasterBinder.getField(Constants.CONTRACT_NO).getValue().toString().trim();

                        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(ContractMaster.class);
                        cfpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CONTRACT_ID, contractID));
                        cfpDynamicQuery.add(RestrictionsFactoryUtil.ne(Constants.CONTRACT_MASTER_SID, contractSystemId));

                        final DynamicQuery contractDynamicQuery = DynamicQueryFactoryUtil.forClass(ContractMaster.class);
                        contractDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CONTRACT_NO, contractNo));
                        contractDynamicQuery.add(RestrictionsFactoryUtil.ne(Constants.CONTRACT_MASTER_SID, contractSystemId));

                        final List<ContractMaster> contractMaster = daoImpl.getContractMasterList(cfpDynamicQuery);
                        final List<ContractMaster> contractNoMaster = daoImpl.getContractMasterList(contractDynamicQuery);
                        if (contractMaster.size() > 0) {
                            contractMasterBinder.getErrorDisplay().setError("Please enter different Contract ID since Contract ID already exists");
                            return false;
                        }
                        if (contractNoMaster.size() > 0) {
                            contractMasterBinder.getErrorDisplay().setError("Please enter different Contract No since Contract No already exists");
                            return false;
                        }

                        String errMsg;
                if ((Integer) sessionDTO.getCfpSystemId() != Constants.ZERO) {
                    errMsg = companiesTab.textFieldEmptyChecks();
                    if (!errMsg.isEmpty()) {
                        contractMasterBinder.getErrorDisplay().setError(errMsg);
                        return false;
                    }
                    cfpContractBinder.getErrorDisplay().clearError();
                    cfpContractBinder.commit();
                    if (cfpContractBinder.getField("salesInclusion").getValue() == null || (Constants.SELECT_ONE).equals(String.valueOf(((HelperDTO) cfpContractBinder.getField("salesInclusion").getValue()).getDescription()))) {
                        cfpContractBinder.getErrorDisplay().setError("Sales Inclusion is mandatory in Companies Tab.");
                        return false;
                    }

                    Integer countTemp = cfpSearchLogic.getLazySelectedCompaniesCount(null, StringUtils.EMPTY);
                    cfpLogic.saveToTempCFP(saveContainer.getItemIds());
                    if (cfpLogic.cfpValidation()) {
                        contractMasterBinder.getErrorDisplay().setError("Select atleast one company for CFP  in Companies Tab.");
                        return false;
                    }

                    if (cfpLogic.companyNullVerification("CFP_DETAILS_ATTACHED_STATUS")) {
                        contractMasterBinder.getErrorDisplay().setError("Status is mandatory for all Company in Companies Tab.");
                        return false;
                    }

                    if (cfpLogic.companyNullVerification("CFP_DETAILS_START_DATE")) {
                        contractMasterBinder.getErrorDisplay().setError("CFP start date is mandatory for all Company in Companies Tab.");
                        return false;
                    }
                    final List duplicateCheckInGrid = new ArrayList();
                    List<CFPCompanyDTO> tempList = cfpSearchLogic.getLazySelectedCompaniesDeatils(0, countTemp, Boolean.TRUE, null, null, false, StringUtils.EMPTY, Boolean.FALSE);
                    final List overlappedCompanies = dashBoardLogic.getOverlappedCompanyList(tempList, contractSystemId);
                    for (int i = 0; i < tempList.size(); i++) {
                        CFPCompanyDTO list = tempList.get(i);
                        boolean checkRecord = list.getCheckbox();
                        String sDate = StringUtils.trimToEmpty(String.valueOf(list.getCompanyFamilyPlanStartDate()));
                        String eDate = StringUtils.trimToEmpty(String.valueOf(list.getCompanyFamilyPlanEndDate()));
                        if (checkRecord) {
                            if (StringUtils.isEmpty(sDate) || Constants.NULL.equals(sDate)) {
                                contractMasterBinder.getErrorDisplay().setError("Start Date required for Company No " + list.getCompanyNo());
                                return false;
                            }
                            if (StringUtils.isNotBlank(sDate) && !Constants.NULL.equals(sDate) && StringUtils.isNotBlank(eDate) && !Constants.NULL.equals(eDate)
                                    && list.getCompanyFamilyPlanStartDate().after(list.getCompanyFamilyPlanEndDate())) {
                                contractMasterBinder.getErrorDisplay().setError("CFP start date should be before CFP end date  in Companies Tab.");
                                return false;
                            }
                            duplicateCheckInGrid.add(String.valueOf(list.getCompanySystemId() + "`" + list.getCompanyFamilyPlanStartDate()));

                            LOGGER.debug(" contractSystemId=" + contractSystemId);
                            contractMasterBinder.getErrorDisplay().clearError();

                            if (!overlappedCompanies.isEmpty()) {

                                String cfpCheck = StringUtils.EMPTY;
                                if (!overlappedCompanies.isEmpty()) {
                                    final StringBuffer cfpCheckTemp = new StringBuffer(StringUtils.EMPTY);
                                    for (int k = 0; k < overlappedCompanies.size(); k++) {
                                        if (k == Constants.ZERO) {
                                            cfpCheck = overlappedCompanies.get(k).toString();
                                        } else {

                                            final String temp = Constants.COMMA + overlappedCompanies.get(k).toString();
                                            cfpCheckTemp.delete(Constants.ZERO, cfpCheckTemp.length());
                                            cfpCheckTemp.append(cfpCheck);
                                            cfpCheckTemp.append(temp);
                                            cfpCheck = cfpCheckTemp.toString();
                                        }

                                    }
                                }
                                contractMasterBinder.getErrorDisplay().setError("For the Companies \"" + cfpCheck + "\" Dates Overlap");
                                return false;
                            }

                        }

                    }
                }
                if ((Integer) sessionDTO.getIfpSystemId() != Constants.ZERO || (Integer) sessionDTO.getPsSystemId() != Constants.ZERO) {
                    if ((Integer) sessionDTO.getIfpSystemId() != Constants.ZERO) {
                        errMsg = itemsTab.textFieldEmptyChecks();
                        if (!errMsg.isEmpty()) {
                            contractMasterBinder.getErrorDisplay().setError(errMsg);
                            return false;
                        }
                        itemsTab.getBinder().commit();
                        if (itemsTab.getBinder().getField("itemFamilyplanStatus").getValue() == null) {
                            contractMasterBinder.getErrorDisplay().setError("Item Family Plan Status is mandatory in Item  Tab.");
                            return false;
                        }
                        if (itemsTab.getBinder().getField(Constants.START_DATE).getValue() == null) {
                            contractMasterBinder.getErrorDisplay().setError("Start Date is mandatory in Items Tab.");
                            return false;
                        }
                        IfpLogic.saveToTempIFP(saveIFPContainer.getItemIds(), isEditable);
                        if (ifpLogic.ifpValidation()) {
                            contractMasterBinder.getErrorDisplay().setError("Add or select atleast one Item for IFP in Items Tab.");
                            return false;
                        }
                        if (ifpLogic.itemNullVerification("ATTACHED_STATUS")) {
                            contractMasterBinder.getErrorDisplay().setError("Status is mandatory in Items.");
                            return false;
                        }
                        if (ifpLogic.itemNullVerification("START_DATE")) {
                            contractMasterBinder.getErrorDisplay().setError("Start Date is mandatory in Items.");
                            return false;
                        }
                    }
                    if ((Integer) sessionDTO.getPsSystemId() != Constants.ZERO) {
                        errMsg = itemPricing.textFieldEmptyChecks();
                        if (!errMsg.isEmpty()) {
                            contractMasterBinder.getErrorDisplay().setError(errMsg);
                            return false;
                        }
                        itemPricing.getPricingBinderEdit().getErrorDisplay().clearError();
                        itemPricing.getPricingBinderEdit().commit();

                        if (ifpLogic.itemNullVerification("CONTRACT_PRICE_START_DATE")) {
                            contractMasterBinder.getErrorDisplay().setError("CP Start Date  is mandatory in Item Pricing Tab.");
                            return false;
                        }
                        if (ifpLogic.verifyPriceOrPriceType()) {
                            contractMasterBinder.getErrorDisplay().setError("Price Type is mandatory in Pricing Tab.");
                            return false;
                        }
                        if (ifpLogic.verifyPrice()) {
                            contractMasterBinder.getErrorDisplay().setError("Price is mandatory in Pricing Tab when price type is Contract Price.");
                            return false;
                        }
                        if (ifpLogic.itemNullVerification("PS_STATUS")) {
                            contractMasterBinder.getErrorDisplay().setError("Status is mandatory in Pricing Tab.");
                            return false;
                        }

                        IfpLogic.saveToTempTable(savePSContainer.getItemIds(), isEditable,sessionDTO);
                    }

                }

                if ((Integer) sessionDTO.getRsSystemId() != Constants.ZERO) {
                    errMsg = rebateScheduleInformation.textFieldEmptyChecks();
                     if (rebateBinder.getField(Constants.REBATE_SCHEDULE_STATUS).getValue() == null ||rebateBinder.getField(Constants.REBATE_SCHEDULE_STATUS).getValue().equals(Constants.SELECT_ONE) || (Constants.SELECT_ONE).equals(String.valueOf(((HelperDTO) rebateBinder.getField(Constants.REBATE_SCHEDULE_STATUS).getValue()).getDescription()))) {
                        contractMasterBinder.getErrorDisplay().setError("Rebate Schedule Status is mandatory in Rebate Tab.");
                        return false;
                    }
                    if (rebateBinder.getField(Constants.REBATE_SCHEDULE_TYPE).getValue() == null ||rebateBinder.getField(Constants.REBATE_SCHEDULE_TYPE).getValue().equals(Constants.SELECT_ONE) || (Constants.SELECT_ONE).equals(String.valueOf(((HelperDTO) rebateBinder.getField(Constants.REBATE_SCHEDULE_TYPE).getValue()).getDescription()))) {
                        contractMasterBinder.getErrorDisplay().setError("Rebate Schedule Type is mandatory in Rebate Tab.");
                        return false;
                    }if (rebateBinder.getField(Constants.REBATE_PROGRAM_TYPE).getValue() == null ||rebateBinder.getField(Constants.REBATE_PROGRAM_TYPE).getValue().equals(Constants.SELECT_ONE) || (Constants.SELECT_ONE).equals(String.valueOf(((HelperDTO) rebateBinder.getField(Constants.REBATE_PROGRAM_TYPE).getValue()).getDescription()))) {
                        contractMasterBinder.getErrorDisplay().setError("Rebate Program Type is mandatory in Rebate Tab.");
                        return false;
                    }if (rebateBinder.getField(Constants.RS_CATEGORY).getValue() == null ||rebateBinder.getField(Constants.RS_CATEGORY).getValue().equals(Constants.SELECT_ONE) || (Constants.SELECT_ONE).equals(String.valueOf(((HelperDTO) rebateBinder.getField(Constants.RS_CATEGORY).getValue()).getDescription()))) {
                        contractMasterBinder.getErrorDisplay().setError("Rebate Schedule Category is mandatory in Rebate Tab.");
                        return false;
                    }
                     if (rebateBinder.getField(Constants.ITEM_REBATE_START_DATE).getValue() == null ) {
                        contractMasterBinder.getErrorDisplay().setError("RS Start Date is mandatory in Rebate Tab.");
                        return false;
                    }if (rebateBinder.getField(ConstantUtil.DEDUCTION_INCLUSION).getValue() == null ||rebateBinder.getField(ConstantUtil.DEDUCTION_INCLUSION).getValue().equals(Constants.SELECT_ONE) || (Constants.SELECT_ONE).equals(String.valueOf(((HelperDTO) rebateBinder.getField(ConstantUtil.DEDUCTION_INCLUSION).getValue()).getDescription()))) {
                        contractMasterBinder.getErrorDisplay().setError("Deduction Inclusion is mandatory in Rebate Tab.");
                        return false;
                    }if (rebateBinder.getField(Constants.REBATE_FREQUENCY).getValue() == null ||rebateBinder.getField(Constants.REBATE_FREQUENCY).getValue().equals(Constants.SELECT_ONE) || (Constants.SELECT_ONE).equals(String.valueOf(((HelperDTO) rebateBinder.getField(Constants.REBATE_FREQUENCY).getValue()).getDescription()))) {
                        contractMasterBinder.getErrorDisplay().setError("Rebate Frequency is mandatory in Rebate Tab.");
                        return false;
                    }if (rebateBinder.getField(Constants.PAYMENT_FREQUENCY).getValue() == null ||rebateBinder.getField(Constants.PAYMENT_FREQUENCY).getValue().equals(Constants.SELECT_ONE) || (Constants.SELECT_ONE).equals(String.valueOf(((HelperDTO) rebateBinder.getField(Constants.PAYMENT_FREQUENCY).getValue()).getDescription()))) {
                        contractMasterBinder.getErrorDisplay().setError("Payment Frequency is mandatory in Rebate Tab.");
                        return false;
                    }if (rebateBinder.getField(Constants.PAYMENT_METHOD).getValue() == null ||rebateBinder.getField(Constants.PAYMENT_METHOD).getValue().equals(Constants.SELECT_ONE) || (Constants.SELECT_ONE).equals(String.valueOf(((HelperDTO) rebateBinder.getField(Constants.PAYMENT_METHOD).getValue()).getDescription()))) {
                        contractMasterBinder.getErrorDisplay().setError("Payment Method is mandatory in Rebate Tab.");
                        return false;
                    }
                    if (rebateBinder.getField(Constants.CALCULATION_TYPE).getValue() == null ||rebateBinder.getField(Constants.CALCULATION_TYPE).getValue().equals(Constants.SELECT_ONE) || (Constants.SELECT_ONE).equals(String.valueOf(((HelperDTO) rebateBinder.getField(Constants.CALCULATION_TYPE).getValue()).getDescription()))) {
                        contractMasterBinder.getErrorDisplay().setError("Calculation Type is mandatory in Rebate Tab.");
                        return false;
                    }
                    if (rebateBinder.getField(Constants.CALCULATION_LEVEL).getValue() == null ||rebateBinder.getField(Constants.CALCULATION_LEVEL).getValue().equals(Constants.SELECT_ONE) || (Constants.SELECT_ONE).equals(String.valueOf(((HelperDTO) rebateBinder.getField(Constants.CALCULATION_LEVEL).getValue()).getDescription()))) {
                        contractMasterBinder.getErrorDisplay().setError("Calculation Level is mandatory in Rebate Tab.");
                        return false;
                    }
                    if (!errMsg.isEmpty()) {
                        contractMasterBinder.getErrorDisplay().setError(errMsg);
                        return false;
                    }
                    rebateBinder.getErrorDisplay().clearError();
                    rebateBinder.commit();
                    if (rebateBinder.getField(ConstantUtil.DEDUCTION_INCLUSION).getValue() == null || (Constants.SELECT_ONE).equals(String.valueOf(((HelperDTO) rebateBinder.getField(ConstantUtil.DEDUCTION_INCLUSION).getValue()).getDescription()))) {
                        contractMasterBinder.getErrorDisplay().setError("Deduction Inclusion is mandatory in Rebate Tab.");
                        return false;
                    }
                    IfpLogic.saveToTempRebate(rsDetailsResultsBean.getItemIds(), false);
                    if (ifpLogic.rsValidation()) {
                        contractMasterBinder.getErrorDisplay().setError("Add or select atleast one Item for IFP in Rebate Setup Tab.");
                        return false;
                    }

                    for (TempRebateDTO tempRebateDTO : rsDetailsResultsBean.getItemIds()) {
                        if (tempRebateDTO.getCheckbox()) {
                            if (CommonUIUtils.isValid(tempRebateDTO.getBundleNo(), Constants.alphaNumericChars)) {
                                contractMasterBinder.getErrorDisplay().setError("Bundle# can contain only Alphanumeric values in Rebate Setup Tab.");
                                return false;
                            }
                            if (tempRebateDTO.getRebateStartDate() == null) {
                                contractMasterBinder.getErrorDisplay().setError("Start Date is mandatory in Rebate Setup.");
                                return false;
                            }

                            if (tempRebateDTO.getAttachedStatus() == null || Constants.SELECT_ONE.equalsIgnoreCase(tempRebateDTO.getAttachedStatus().getDescription())) {
                                contractMasterBinder.getErrorDisplay().setError("Status is mandatory in Rebate Setup.");
                                return false;
                            }

                            if (tempRebateDTO.getRebateStartDate() != null && tempRebateDTO.getRebateEndDate() != null) {
                                Date ob = (Date) tempRebateDTO.getRebateStartDate();
                                Date ob1 = (Date) tempRebateDTO.getRebateEndDate();
                                if (ob.after(ob1)) {
                                    contractMasterBinder.getErrorDisplay().setError("End Date should be greater than Start Date in Rebate Setup Tab.");
                                    return false;
                                } else if (ob.equals(ob1)) {
                                    contractMasterBinder.getErrorDisplay().setError("Start Date should not be equal to End Date in Rebate Setup Tab.");
                                    return false;
                                }
                            }

                        }
                        ifpLogic.saveToTempRebate(rsDetailsResultsBean.getItemIds(), false);
                        if (rebateBinder.getField(ConstantUtil.DEDUCTION_INCLUSION).getValue() == null || (Constants.SELECT_ONE).equals(String.valueOf(((HelperDTO) rebateBinder.getField(ConstantUtil.DEDUCTION_INCLUSION).getValue()).getDescription()))) {
                            contractMasterBinder.getErrorDisplay().setError("Payment Method is mandatory in Rebate Tab.");
                            return false;
                        }

                        if (rebateBinder.getField(Constants.PAYMENT_METHOD).getValue() == null || (Constants.SELECT_ONE).equals(String.valueOf(((HelperDTO) rebateBinder.getField(Constants.PAYMENT_METHOD).getValue()).getDescription()))) {
                            contractMasterBinder.getErrorDisplay().setError("Payment Method is mandatory in Rebate Tab.");
                            return false;
                        }
                         // cel-211
                        if (rebateBinder.getField(Constants.REBATE_FREQUENCY).getValue() == null || (Constants.SELECT_ONE).equals(String.valueOf(((HelperDTO) rebateBinder.getField(Constants.REBATE_FREQUENCY).getValue()).getDescription()))) {
                            contractMasterBinder.getErrorDisplay().setError("Rebate Frequency is mandatory in Rebate Tab.");
                            return false;
                        }

                        if (rebateBinder.getField(Constants.PAYMENT_FREQUENCY).getValue() == null || (Constants.SELECT_ONE).equals(String.valueOf(((HelperDTO) rebateBinder.getField(Constants.PAYMENT_FREQUENCY).getValue()).getDescription()))) {
                            contractMasterBinder.getErrorDisplay().setError("Payment Frequency is mandatory in Rebate Tab.");
                            return false;
                        }
                         if (rebateBinder.getField(Constants.CALCULATION_TYPE).getValue() == null || (Constants.SELECT_ONE).equals(String.valueOf(((HelperDTO) rebateBinder.getField(Constants.CALCULATION_TYPE).getValue()).getDescription()))) {
                            contractMasterBinder.getErrorDisplay().setError("Calculation Type is mandatory in Rebate Tab.");
                            return false;
                        }
                         if (rebateBinder.getField(Constants.CALCULATION_LEVEL).getValue() == null || (Constants.SELECT_ONE).equals(String.valueOf(((HelperDTO) rebateBinder.getField(Constants.CALCULATION_LEVEL).getValue()).getDescription()))) {
                            contractMasterBinder.getErrorDisplay().setError("Calculation level is mandatory in Rebate Tab.");
                            return false;
                        }
                        if (ifpLogic.itemNullVerification("ITEM_REBATE_START_DATE")) {
                            contractMasterBinder.getErrorDisplay().setError("Start Date  is mandatory in Rebate Setup Tab");
                            return false;
                        }
                        if (!ifpLogic.dateVerification("rebate")) {
                            contractMasterBinder.getErrorDisplay().setError("Rebate End date should be greater than Start Date.");
                            return false;
                        }
                    }
                }

                notesTabLogic.deleteUploadedFile(notesTabForm.removeDetailsList());

            }

        } catch (CommitException e) {
            LOGGER.error(e);
        }catch(SystemException e){
            LOGGER.error(e);
        }catch(PortalException e){
            LOGGER.error(e);
        }
          return true;
    }

    /**
     * Back button.
     */
    private void backButton() {
        LOGGER.debug("Entering backButton method");
        backButton.setWidth("75");
        backButton.addClickListener(new ClickListener() {
            /**
             * Invoked when a button is clicked
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("Entering backButton buttonClick");
                MessageBox.showPlain(Icon.QUESTION, util.getHeaderMessage(), util.getBackMessage(), new MessageBoxListener() {
                    /**
                     * Called when a Button has been clicked .
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals(Constants.YES)) {

                            cfpLogic.clearTempCFP();
                            ifpLogic.clearTempIFP();
                            getUI().getNavigator().navigateTo(DashboardView.NAME);
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);
                LOGGER.debug("End of backButton buttonClick");
            }
        });
        backButton.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs.
             */
            public void error(final com.vaadin.server.ErrorEvent event) {
                return;
            }
        });
        LOGGER.debug("End of backButton method");
    }
    private void excelExport() {
        LOGGER.debug("Entering excelExport method");

        excelExport.setIcon(new ThemeResource(ExcelExportUtil.EXCEL_EXPORT_IMAGE));
        excelExport.setStyleName("link");
        excelExport.setDescription(Constants.EXCEL_EXPORT);
        excelExport.setIconAlternateText(Constants.EXCEL_EXPORT);

        excelExport.addClickListener(new ClickListener() {
            /**
             * Invoked when a button is clicked
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("Entering excelExport buttonClick");
                if (ConstantUtil.COMPANIES.equals(tabsheet.getSelectedTab().getCaption())) {
                    if ((companiesTab.getLevel().getValue().toString().equals("Header") && companiesTab.getView().getValue().toString().equals("History")) || companiesTab.getLevel().getValue().toString().equals("Detail")) {
                        try {
                            companiesTab.btnExportLogic();
                        } catch (Exception ex) {
                            LOGGER.error(ex);
                        }
                    } else {
                        MessageBox.showPlain(Icon.INFO, ConstantUtil.EXCEL_EXPORT_HEAD, "Excel is not supported for Companies tab", new MessageBoxListener() {
                            /**
                             * After clicking button, function will be executed.
                             */
                            @SuppressWarnings("PMD")
                            public void buttonClicked(final ButtonId buttonId) {
                                return;
                            }
                        }, ButtonId.OK);

                    }
                } else if ("Items".equals(tabsheet.getSelectedTab().getCaption())) {
                    try {
                        if (String.valueOf(itemsTab.level.getValue()).equals("Header") && (String.valueOf(itemsTab.view.getValue()).equals("Current") || String.valueOf(itemsTab.view.getValue()).equals("Pending"))) {
                        } else {
                            itemsTab.btnExportLogic();
                        }
                    } catch (Exception ex) {

                        LOGGER.error(ex);
                    }
                } else if (ConstantUtil.PRICING.equals(tabsheet.getSelectedTab().getCaption())) {
                    try {
                        itemPricing.btnExportLogic();
                    } catch (Exception ex) {

                        LOGGER.error(ex);
                    }
                } else if (ConstantUtil.REBATE.equals(tabsheet.getSelectedTab().getCaption())) {
                    rebateScheduleInformation.btnExportLogic();
                } else if (ConstantUtil.CONTRACT_INFORMATION.equals(tabsheet.getSelectedTab().getCaption())) {
                    contractInfoTab.excelExportLogic();
                } else if (ConstantUtil.ALIAS.equals(tabsheet.getSelectedTab().getCaption())) {
                    try {
                        aliasTab.excelExportLogic();
                    } catch (PortalException pe) {
                        LOGGER.error(pe);
                    } catch (SystemException e) {
                        final String errorMsg = ErrorCodeUtil.getErrorMessage(e);
                        LOGGER.error(errorMsg);
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                } else if (ConstantUtil.COMPANY_ADDITION.equals(tabsheet.getSelectedTab().getCaption())) {
                    MessageBox.showPlain(Icon.INFO, ConstantUtil.EXCEL_EXPORT_HEAD, "Excel is not supported for Company Addition tab", new MessageBoxListener() {
                        /**
                         * After clicking button, function will be executed.
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            return;
                        }
                    }, ButtonId.OK);
                } else if (ConstantUtil.ITEM_ADDITION.equals(tabsheet.getSelectedTab().getCaption())) {
                    MessageBox.showPlain(Icon.INFO, ConstantUtil.EXCEL_EXPORT_HEAD, "Excel is not supported for Item Addition tab", new MessageBoxListener() {
                        /**
                         * After clicking button, function will be executed.
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            return;
                        }
                    }, ButtonId.OK);
                } else if ("Rebate Schedule Information".equals(tabsheet.getSelectedTab().getCaption())) {
                    MessageBox.showPlain(Icon.INFO, ConstantUtil.EXCEL_EXPORT_HEAD, "Excel is not supported for Rebate Schedule Information tab", new MessageBoxListener() {
                        /**
                         * After clicking button, function will be executed.
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            return;
                        }
                    }, ButtonId.OK);
                } else if (ConstantUtil.NOTES.equals(tabsheet.getSelectedTab().getCaption())) {
                    MessageBox.showPlain(Icon.INFO, ConstantUtil.EXCEL_EXPORT_HEAD, "Excel is not supported for Notes tab", new MessageBoxListener() {
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

        });
        excelExport.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs.
             */
            public void error(final com.vaadin.server.ErrorEvent event) {
                return;
            }
        });
        LOGGER.debug("End of excel export method");
    }


    @Override
    public void editLogic() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void deleteLogic() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void submitButton() {
        submitButton.addClickListener(new ClickListener() {
            /**
             * Invoked when a button is clicked
             */
            @SuppressWarnings("PMD")
            @Override
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("Entering submitButton buttonClick");
                try {
                    if (sessionDTO.isHasPermission() == null) {
                        sessionDTO.setHasPermission(checkPermission());
                    }
                    LOGGER.debug("is user " + userModel.getScreenName() + " is Valid :" + sessionDTO.isHasPermission());
                    if (!sessionDTO.isHasPermission()) {
                        AbstractNotificationUtils.getErrorNotification("Authentication Error", "User dont have persmission to Submit");
                        return;
                    }
                    if (!updateBtnValidation()) {
                        return;
                    }
                  
                    MessageBox.showPlain(Icon.QUESTION, WorkflowMessages.getCW_SubmitConfirmationHeader(), WorkflowMessages.getCW_SubmitConfirmationMsg(), new MessageBoxListener() {
                        /**
                         * Called when a Button has been clicked .
                         */
                        @SuppressWarnings("PMD")
                        @Override
                        public void buttonClicked(final ButtonId buttonId) {
                            if (buttonId.name().equals("YES")) {
                                try {
                                    final String success = dashBoardLogic.saveContractDashBoard(contractMasterBinder, aliasMasterResultsbeans.getItemIds(),rebateBinder,
                                            notesTabForm.getUploadedData(), notesTabForm.getAddedNotes(), pricingBinderEdit, cfpContractBinder, itemsTab.getBinder());
                                    
                                    if (!Constants.SUCCESS.equals(success)) {
                                        return;
                                    }
                                   
                                    String workflowID = WORKFLOW_LOGIC.submitContract(sessionDTO.getContractSystemId(), sessionDTO.getUserId(), contractStructure,
                                            sessionDTO.getWorkflow() == null, sessionDTO.getProcessIntanceId(),
                                            sessionDTO.getWorkflow() == null ? 0 : sessionDTO.getWorkflow().getWorkflowMasterSid(), sessionDTO.getWorkflow() == null ? StringUtils.EMPTY : sessionDTO.getWorkflow().getWorkflowId());
                                  
                                    if(sessionDTO.getWorkflow() != null) {
                                        callWorkflowInboxRefresh();
                                    }
                                    if (!Constants.WORKFLOW_NOT_SAVED.equals(workflowID)) {
                                        BPMLogic.startAndCompleteTask(userModel, sessionDTO.getContractSystemId(), sessionDTO.getProcessIntanceId(), contractStructure, sessionDTO.getWorkflow() == null);
                                        BPMLogic.submitWorkflow(userModel, sessionDTO.getProcessIntanceId(), null,"submit");
                                        AbstractNotificationUtils.getInfoNotification(WorkflowMessages.getCW_SubmitSuccessHeader(), WorkflowMessages.getCW_SubmitSuccessMsg().replace("[WORKFLOW_ID]", workflowID));
                                        submitButton.setEnabled(Boolean.FALSE);
                                    }
                                } catch (Exception ex) {
                                   LOGGER.error(ex);
                                } 
                            }
                        }
                    }, ButtonId.YES, ButtonId.NO);
                    LOGGER.debug("End of backButton buttonClick");
                } catch (Exception e) {
                    LOGGER.error(e);
                }
            }
        });
        submitButton.setErrorHandler(handler);
    }

    private void approveButton() {
        approveButton.addClickListener(new ClickListener() {
            /**
             * Invoked when a button is clicked
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                btnApproveLogic();

            }
        });
    }

    private void rejectButton() {
        rejectButton.addClickListener(new ClickListener() {
            /**
             * Invoked when a button is clicked
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                btnRejectLogic();

            }
        });
    }

    private void cancelButton() {
        cancelButton.addClickListener(new ClickListener() {
            /**
             * Invoked when a button is clicked
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                btnCancelLogic();

            }
        });
    }

    private void closeButton() {
        closeButton.addClickListener(new ClickListener() {
            /**
             * Invoked when a button is clicked
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                getUI().close();
                Page.getCurrent().getJavaScript().execute("window.close()");
            }
        });
    }
    
    private void withdrawButton() {
        withdrawButton.addClickListener(new ClickListener() {
            /**
             * Invoked when a button is clicked
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                btnWithdrawButton();

            }
        });
    }
//    @Override

    private void btnApproveLogic() {
        MessageBox.showPlain(Icon.QUESTION, WorkflowMessages.getCW_ApproveConfirmationHeader(), WorkflowMessages.getCW_ApproveConfirmationMsg(), new MessageBoxListener() {
            @Override
            public void buttonClicked(ButtonId buttonId) {
                if (buttonId.name().equals("YES")) {
                    try {
                        int contractSid = sessionDTO.getContractSystemId();
                        int userIdInt = Integer.parseInt(sessionDTO.getUserId());
                        int workflowId = sessionDTO.getWorkflow().getWorkflowMasterSid();
                     
                        String workflowIdUpdate;
                        WorkflowMasterDTO wfMasterDto = WORKFLOW_LOGIC.setWorkflowMasterDTO(contractSid, workflowId, userIdInt, WorkflowConstants.getApprovedStatus(), StringUtils.EMPTY, sessionDTO.getWorkflow().getApprovalLevel());
                        workflowIdUpdate = WORKFLOW_LOGIC.updateWorkflow(wfMasterDto, contractStructure);
                        if (workflowIdUpdate != null && !workflowIdUpdate.trim().equals(Constants.WORKFLOW_NOT_SAVED)) {
                            Map<String, Object> params = new HashMap();
                            params.put(ConstantUtil.APPROVE_FLAG, "approve");
                            BPMLogic.submitWorkflow(userModel, sessionDTO.getProcessIntanceId(), params,"approve");
                            callWorkflowInboxRefresh();
                            AbstractNotificationUtils.getInfoNotification("Approved Information", ConstantUtil.WORKFLOW_ID + workflowIdUpdate + " approved successfully");
                            approveButton.setEnabled(false);
                            rejectButton.setEnabled(false);
                            cancelButton.setEnabled(false);
                        } else {
                            AbstractNotificationUtils.getInfoNotification("Contract Approval", "The Contract not approved properly");
                        }
                    } catch (Exception e) {
                        LOGGER.error(e);
                    }
                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }

    protected void btnRejectLogic() {
        MessageBox.showPlain(Icon.QUESTION, "Confirm Reject", "Are you sure you want to reject the Contract " + " ?", new MessageBoxListener() {
            @Override
            public void buttonClicked(ButtonId buttonId) {
                if (buttonId.name().equals("YES")) {
                    int contractSid = sessionDTO.getContractSystemId();
                    String userId = sessionDTO.getUserId();
                    int userIdInt = Integer.parseInt(userId);
                    int workflowId = sessionDTO.getWorkflow().getWorkflowMasterSid();

                    WorkflowMasterDTO wfMasterDto = WORKFLOW_LOGIC.setWorkflowMasterDTO(contractSid, workflowId, userIdInt, WorkflowConstants.getRejectedStatus(), StringUtils.EMPTY, sessionDTO.getWorkflow().getApprovalLevel());
                    String workflowIdUpdate = WORKFLOW_LOGIC.updateWorkflow(wfMasterDto, contractStructure);
//                                Are you sure you want to Reject this Workflow?
                    if (workflowIdUpdate != null && !workflowIdUpdate.trim().equals(Constants.WORKFLOW_NOT_SAVED)) {
                        Map<String, Object> params = new HashMap();
                        params.put(ConstantUtil.APPROVE_FLAG, "Reject-RC");
                        BPMLogic.submitWorkflow(userModel, sessionDTO.getProcessIntanceId(), params,"reject");
                        callWorkflowInboxRefresh();
                        AbstractNotificationUtils.getInfoNotification("Rejected Information ", ConstantUtil.WORKFLOW_ID + workflowIdUpdate + " rejected successfully");
                        approveButton.setEnabled(false);
                        rejectButton.setEnabled(false);
                        cancelButton.setEnabled(false);
                    } else {
                        AbstractNotificationUtils.getInfoNotification("Contract Rejection", "The Contract not rejected properly");
                    }
                }
            }
        }, ButtonId.YES, ButtonId.NO);

    }

//    @Override
    protected void btnCancelLogic() {
        MessageBox.showPlain(Icon.QUESTION, "Confirm Cancel", "Are you sure you want to cancel the Contract " + " ?", new MessageBoxListener() {
            @Override
            public void buttonClicked(ButtonId buttonId) {
                if (buttonId.name().equals("YES")) {
                    int contractSid = sessionDTO.getContractSystemId();
                    String userId = sessionDTO.getUserId();
                    int userIdInt = Integer.parseInt(userId);
                    int workflowId = sessionDTO.getWorkflow().getWorkflowMasterSid();
                    WorkflowMasterDTO wfMasterDto = WORKFLOW_LOGIC.setWorkflowMasterDTO(contractSid, workflowId, userIdInt, WorkflowConstants.getCancelledStatus(), StringUtils.EMPTY, sessionDTO.getWorkflow().getApprovalLevel());
                    String workflowIdUpdate = WORKFLOW_LOGIC.updateWorkflow(wfMasterDto, contractStructure);
                    if (workflowIdUpdate != null && !workflowIdUpdate.trim().equals(Constants.WORKFLOW_NOT_SAVED)) {
                        Map<String, Object> params = new HashMap();
                        params.put(ConstantUtil.APPROVE_FLAG, "Cancel-RC");
                        BPMLogic.submitWorkflow(userModel, sessionDTO.getProcessIntanceId(), params,"cancel");
                        callWorkflowInboxRefresh();
                        AbstractNotificationUtils.getInfoNotification("Cancel Information", ConstantUtil.WORKFLOW_ID + workflowIdUpdate + " cancelled successfully");
                        approveButton.setEnabled(false);
                        rejectButton.setEnabled(false);
                        cancelButton.setEnabled(false);
                    } else {
                        AbstractNotificationUtils.getInfoNotification("Contract Cancel", "The Contract not cancelled properly");
                    }
                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }

    private void callWorkflowInboxRefresh() {
            JavaScript.getCurrent().execute("localStorage.setItem('"+WorkflowConstants.getBusinessProcessNameContract()+"', 'true');");
    }
    ErrorHandler handler = new ErrorHandler() {
        /**
         * Invoked when an error occurs.
         */
        public void error(final com.vaadin.server.ErrorEvent event) {
            return;
        }
    };

    private boolean checkPermission() {
        ProcessInstance processInstance = BPMLogic.startWorkflow();
       
        sessionDTO.setProcessIntanceId(processInstance.getId());
        return BPMLogic.isValidWorkflowUser(userModel, roleList, processInstance.getId());
    }
    
    protected void btnWithdrawButton() {
        MessageBox.showPlain(Icon.QUESTION, "Confirm Withdraw", "Are you sure you want to withdraw the projection " + " ?", new MessageBoxListener() {
            @Override
            public void buttonClicked(ButtonId buttonId) {
                if (buttonId.name().equals("YES")) {
                    int contractSid = sessionDTO.getContractSystemId();
                    String userId = sessionDTO.getUserId();
                    int userIdInt = Integer.parseInt(userId);
                    int workflowId = sessionDTO.getWorkflow().getWorkflowMasterSid();
                    WorkflowMasterDTO wfMasterDto = WORKFLOW_LOGIC.setWorkflowMasterDTO(contractSid, workflowId, userIdInt, WorkflowConstants.getWithdrawnStatus(), StringUtils.EMPTY, sessionDTO.getWorkflow().getApprovalLevel());

                    String workflowIdUpdate = WORKFLOW_LOGIC.updateWorkflow(wfMasterDto, contractStructure);
                    if (workflowIdUpdate != null && !workflowIdUpdate.trim().equals(Constants.WORKFLOW_NOT_SAVED)) {
                        Map<String, Object> params = new HashMap();
                        params.put(ConstantUtil.APPROVE_FLAG, "Withdraw-RC"); 
                        BPMLogic.submitWorkflow(userModel, sessionDTO.getProcessIntanceId(), params, "withdraw");
                        callWorkflowInboxRefresh();
                      
                        AbstractNotificationUtils.getInfoNotification("Workflow withdrawn ", ConstantUtil.WORKFLOW_ID + workflowIdUpdate + " withdrawn successfully");
                        approveButton.setEnabled(false);
                        rejectButton.setEnabled(false);
                        cancelButton.setEnabled(false);
                        withdrawButton.setEnabled(false);
                    } else {
                        AbstractNotificationUtils.getInfoNotification("Contract Withdrawn", "The Contract not withdrawn properly");
                    }
                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }

    @Override
    public void addToContent() throws SystemException, PortalException {
        return;
    }
}

