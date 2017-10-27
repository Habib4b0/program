/**
 *
 */
package com.stpl.app.contract.dashboard.ui.form;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyBeanItemContainer;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

import com.stpl.addons.tableexport.TemporaryFileDownloadResource;
import com.stpl.app.contract.abstractsearch.util.ConstantUtil;
import com.stpl.app.contract.common.dto.SessionDTO;
import com.stpl.app.contract.common.util.CommonUtil;
import com.stpl.app.contract.contractheader.logic.ContractHeaderLogic;
import com.stpl.app.contract.contractheader.util.UIUtils;
import com.stpl.app.contract.dashboard.dto.CfpDetailsFilterGenerator;
import com.stpl.app.contract.dashboard.logic.DashBoardLogic;
import com.stpl.app.contract.dashboard.ui.lazyload.LazyLoadCriteria;
import com.stpl.app.contract.dashboard.ui.lazyload.TempCompanyContainer;
import com.stpl.app.contract.dashboard.ui.lazyload.TempViewCompanyContainer;
import com.stpl.app.contract.dashboard.ui.lookup.ParentCFPIdLookup;
import com.stpl.app.contract.dashboard.util.ContractUtils;
import com.stpl.app.contract.dashboard.util.ExcelExportUtil;
import com.stpl.app.contract.global.dto.CFPCompanyDTO;
import com.stpl.app.contract.global.dto.CFPTableGenerator;
import com.stpl.app.contract.global.dto.CompanyMasterDTO;
import com.stpl.app.contract.global.logic.CFPSearchLogic;
import com.stpl.app.contract.global.logic.IfpLogic;
import com.stpl.app.contract.util.AbstractNotificationUtils;
import com.stpl.app.contract.util.CHFunctionNameUtils;
import com.stpl.app.contract.util.CommonUIUtils;
import com.stpl.app.contract.util.Constants;
import com.stpl.app.contract.util.ErrorCodeUtil;
import com.stpl.app.contract.util.ErrorCodes;
import com.stpl.app.contract.util.ResponsiveUtils;
import com.stpl.app.security.impl.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.CustomePagedFilterTable;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.TableResultCustom;
import com.stpl.ifs.util.UISecurityUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Validator;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.logging.Level;
import org.apache.commons.lang.ArrayUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 * @author sibi
 *
 */
public class CompaniesTab extends CustomComponent {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(CompaniesTab.class);


    /**
     * The mass check.
     */
    @UiField("massCheck")
    private OptionGroup massCheck;

    /**
     * The mass field.
     */
    @UiField("massField")
    private ComboBox massField;

    /**
     * The mass date.
     */
    @UiField("massDate")
    private PopupDateField massDate;

    /**
     * The mass value.
     */
    @UiField("massValue")
    private ComboBox massValue;

    /**
     * The map.
     */
    private Map<String, String> map = new HashMap<>();

    /**
     * The btn populate.
     */
    @UiField("btnPopulate")
    private Button btnPopulate;

    /**
     * The btn all populate.
     */
    @UiField("btnAllPopulate")
    private Button btnAllPopulate;

    /**
     * The company details table.
     */
    @UiField("companyDetailsTable")
    private CustomePagedFilterTable companyDetailsTable;

    @UiField("resultsTableLayout")
    private VerticalLayout resultsTableLayout;

    @UiField("resultPanel")
    private Panel resultPanel;

    @UiField("viewCompanyDetailsTable")
    private Table viewCompanyDetailsTable;

    @UiField("HeaderCompanyDetailsTable")
    private ExtFilterTable HeaderCompanyDetailsTable;

    @UiField("HeadertblLayout")
    private VerticalLayout HeadertblLayout;

    @UiField("record")
    private OptionGroup record;
    @UiField("cssLayout")
    private CssLayout cssLayout;
     @UiField("massPanel")
    private Panel massPanel;

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
    @UiField("detailsLayout")
    private VerticalLayout detailsLayout;
    @UiField("tablePanel")
    Panel tablePanel;
    @UiField("infoPanel")
    Panel infoPanel;
    @UiField("companyFamilyPlanId")
    private TextField companyFamilyPlanId;
    @UiField("companyFamilyPlanNo")
    private TextField companyFamilyPlanNo;
    @UiField("companyFamilyPlanName")
    private TextField companyFamilyPlanName;
    @UiField("cfpStatus")
    private ComboBox cfpStatus;

    @UiField("companyFamilyPlanStartDate")
    private PopupDateField companyFamilyPlanStartDate;
    @UiField("companyFamilyPlanEndDate")
    private PopupDateField companyFamilyPlanEndDate;

    @UiField("cfptype")
    private ComboBox cfptype;
    @UiField("cfpCategory")
    private ComboBox cfpCategory;
    @UiField("cfptrade")
    private ComboBox cfptrade;

    @UiField("cfpDesignation")
    private ComboBox cfpDesignation;
    @UiField("parentCfp")
    private CustomTextField parentCfp;
    @UiField("parentCfpName")
    private CustomTextField parentCfpName;
    @UiField("salesInclusion")
    private ComboBox salesInclusion;

    @UiField("createdBy")
    private TextField createdBy;
    @UiField("ModifiedBy")
    private TextField ModifiedBy;
    @UiField("createddate")
    private TextField createddate;
    @UiField("Modifieddate")
    private TextField Modifieddate;
    @UiField("btnRemove")
    Button btnRemove;
    @UiField("viewLB")
    private Label viewLB;

    private TextField parentFlagSID = new TextField();
    LazyLoadCriteria lazyLoadCriteria = new LazyLoadCriteria();
    CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();
    private final Map<Integer, Boolean> reloadMap = new HashMap<>();
    private final ContractHeaderLogic contractHL = new ContractHeaderLogic();

    final ContractHeaderLogic contractLogic = new ContractHeaderLogic();
    /**
     * The field mass.
     */
    private String fieldMass = StringUtils.EMPTY;

    /**
     * The cfpcompany list.
     */
    private final List<CFPCompanyDTO> cfpcompanyList = new ArrayList<>();
    /**
     * The common util.
     */
    private CommonUtil commonUtil = CommonUtil.getInstance();
    private com.stpl.app.contract.abstractsearch.util.CommonUtils commonUtils = com.stpl.app.contract.abstractsearch.util.CommonUtils.getInstance();

    private final Map<Integer, Boolean> reloadVerticalLayoutTabThreeMap = new HashMap<>();

    /**
     * The cfp logic.
     */
    private final CFPSearchLogic cfpLogic;

    /**
     * The dash board logic.
     */
    private final DashBoardLogic dashBoardLogic;

    /**
     * The cfp company dto.
     */
    private CFPCompanyDTO cfpCompanyDTO = new CFPCompanyDTO();

    private LazyBeanItemContainer tempLazyContainer;

    BeanItemContainer<CompanyMasterDTO> availableCompanyResultBean;

    BeanItemContainer<CompanyMasterDTO> selectedCompanyResultBean;

    BeanItemContainer<CFPCompanyDTO> cfpResultsBean;

    BeanItemContainer<CFPCompanyDTO> saveContainer;
    BeanItemContainer<CFPCompanyDTO> HeadertableBean;

    final Map<String, String> companyMap;

    public CustomFieldGroup cfpContractBinder;

    final boolean isEditable;
    SessionDTO sessionDTO;
    /**
     * TempCompanyContainer instance
     */
    TempCompanyContainer TempEditCompanyContainer;
    /**
     * TempViewCompanyContainer instance
     */
    TempViewCompanyContainer tempViewCompanyContainer;

    Date[] dates = new Date[NumericConstants.TWO];
    Object[] fieldFactoryDates = new Object[NumericConstants.TWO];

    private Map<String, List> tempDate;

    boolean valueChange = false;
    SimpleDateFormat format = new SimpleDateFormat(ConstantUtil.DATE_FORMAT);
    final StplSecurity stplSecurity = new StplSecurity();
    String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
    final Map<String, AppPermission> fieldContract = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.CONTRACT_DASHBOARD + Constants.COMMA + "Companies Detail", false);
    List<Object> resultList = contractHL.getFieldsForSecurity(UISecurityUtil.CONTRACT_DASHBOARD, "Companies Detail");
    ParentCFPIdLookup lookUp = null;
    final CustomFieldGroup contractBinder;
    
    @UiField("excelBtn")
    private Button excelExport;

    public CFPCompanyDTO getCfpCompanyDTO() {
        return cfpCompanyDTO;
    }

    public void setCfpCompanyDTO(CFPCompanyDTO cfpCompanyDTO) {
        this.cfpCompanyDTO = cfpCompanyDTO;
    }

    public CustomFieldGroup getCfpContractBinder() {
        return cfpContractBinder;
    }

    public void setCfpContractBinder(CustomFieldGroup cfpContractBinder) {
        this.cfpContractBinder = cfpContractBinder;
    }

    /**
     * The Constructor.
     *
     * @param availableBean the available bean
     * @param selectedBean the selected bean
     * @param cfpResultsBean the cfp results bean
     * @param companyMap the company map
     */
    public CompaniesTab(final BeanItemContainer<CompanyMasterDTO> availableBean, final BeanItemContainer<CompanyMasterDTO> selectedBean, final BeanItemContainer<CFPCompanyDTO> cfpResultsBean, final BeanItemContainer<CFPCompanyDTO> saveContainer, final Map<String, String> companyMap, final CustomFieldGroup cfpContractBinder, final boolean isEditable, final SessionDTO sessionDTO, final CFPCompanyDTO cfpCompanyDTO, final CustomFieldGroup contractBinder) throws SystemException, PortalException{
        super();
        LOGGER.debug("Entering CompaniesTab");

        this.availableCompanyResultBean = availableBean;
        this.selectedCompanyResultBean = selectedBean;
        this.cfpResultsBean = cfpResultsBean;
        this.saveContainer = saveContainer;
        this.companyMap = companyMap;
        this.cfpContractBinder = cfpContractBinder;
        this.isEditable = isEditable;
        this.sessionDTO = sessionDTO;
        dashBoardLogic = new DashBoardLogic(this.sessionDTO);
        cfpLogic = new CFPSearchLogic(this.sessionDTO);
        this.cfpCompanyDTO = cfpCompanyDTO;
        this.contractBinder = contractBinder;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/declarative-ui/contract-dashboard/companies.xml"), this));
        init();
        getcfpHeader();
        configureBinder();
        valueChange = Boolean.TRUE;
        LOGGER.debug("End of CompaniesTab");

    }

    private void configureBinder() {
        LOGGER.debug("Entering getBinder method");
        cfpContractBinder.bindMemberFields(this);
        cfpContractBinder.setItemDataSource(new BeanItem<>(cfpCompanyDTO));
        cfpContractBinder.setBuffered(true);
        companyFamilyPlanNo.setEnabled(true);
        companyFamilyPlanId.setEnabled(false);
        companyFamilyPlanName.setEnabled(true);

        createdBy.setEnabled(false);
        createddate.setEnabled(false);
        ModifiedBy.setEnabled(false);
        Modifieddate.setEnabled(false);
        LOGGER.debug("End of getBinder method");

    }

    private void init() throws SystemException , PortalException{
        final Map<String, AppPermission> contractDashboard = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.CONTRACT_DASHBOARD + Constants.COMMA + ConstantUtil.COMPANIES, false);
        final Map<String, AppPermission> funContractHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.CONTRACT_DASHBOARD + Constants.COMMA + ConstantUtil.COMPANIES);
        addResponsiveGrid(contractDashboard);
        configureHeadertable();
        if (isEditable) {
            record();
            configureFields();
            if (!(funContractHM.get(CHFunctionNameUtils.POPULATE) != null) || !((AppPermission) funContractHM.get(CHFunctionNameUtils.POPULATE)).isFunctionFlag()) {
                btnPopulate.setVisible(false);
            } else {
                addBtnPopulate();
            }
            if (!(funContractHM.get(CHFunctionNameUtils.POPULATE_ALL) != null) || !((AppPermission) funContractHM.get(CHFunctionNameUtils.POPULATE_ALL)).isFunctionFlag()) {
                btnAllPopulate.setVisible(false);
            } else {
                addAllBtnPopulate();
            }
            addItemDetailsTable();
        } else {
            record();
            configureFieldsOnFields();
            addItemDetailsTableFromView();
            viewCompanyDetailsTable.setReadOnly(true);
            massPanel.setVisible(false);
            btnRemove.setVisible(false);
        }
        
        excelExport.setIcon(new ThemeResource(ExcelExportUtil.EXCEL_EXPORT_IMAGE));
        excelExport.setStyleName("link");
        excelExport.setDescription(Constants.EXCEL_EXPORT);
        excelExport.setIconAlternateText(Constants.EXCEL_EXPORT);
        btnExportLogic();
        excelExport.setVisible(false);
        view.addItems(Constants.HISTORY, Constants.CURRENT, Constants.PENDING);
        view.select(Constants.CURRENT);
        level.addItems(Constants.HEADER, Constants.DETAILS);
        level.select(Constants.HEADER);
        setLevel(level);
        setView(view);
        parentCfp.addStyleName("searchicon");
        parentCfpName.addStyleName("searchicon");
        parentCfp.setReadOnly(true);
        parentCfpName.setReadOnly(true);
        parentCfp.setImmediate(true);
        parentCfpName.setImmediate(true);
        parentCfp.addClickListener(new CustomTextField.ClickListener() {
            @Override
            public void click(CustomTextField.ClickEvent event) {
                try {
                    int cfpSystemId = sessionDTO.getCfpSystemId();
                    if (lookUp == null) {
                        lookUp = new ParentCFPIdLookup(parentCfp, parentCfpName, cfpSystemId, parentFlagSID, sessionDTO);
                        UI.getCurrent().addWindow(lookUp);
                    }
                    lookUp.addCloseListener(new Window.CloseListener() {
                        @SuppressWarnings("PMD")
                        public void windowClose(final Window.CloseEvent e) {
                            parentCfp.setReadOnly(true);
                            parentCfpName.setReadOnly(true);
                            companyFamilyPlanId.focus();
                            lookUp = null;
                        }
                    });
                } catch (Exception se) {
                       LOGGER.debug(se);

                }
            }
        });
        parentCfpName.addClickListener(new CustomTextField.ClickListener() {
            @Override
            public void click(CustomTextField.ClickEvent event) {
                try {
                    int cfpSystemId = sessionDTO.getCfpSystemId();
                    if (lookUp == null) {
                        lookUp = new ParentCFPIdLookup(parentCfp, parentCfpName, cfpSystemId, parentFlagSID, sessionDTO);
                        UI.getCurrent().addWindow(lookUp);
                    }
                    lookUp.addCloseListener(new Window.CloseListener() {
                        @SuppressWarnings("PMD")
                        public void windowClose(final Window.CloseEvent e) {
                            parentCfp.setReadOnly(true);
                            parentCfpName.setReadOnly(true);
                            cfpCompanyDTO.setParentFlagSID(parentFlagSID.getValue());
                            companyFamilyPlanId.focus();
                            lookUp = null;
                        }
                    });
                } catch (Exception e) {

                    LOGGER.error(e);

                }
            }
        });
        btnRemove.addClickListener(new ClickListener() {
            public void buttonClick(final ClickEvent event) {
                try {
                    CFPSearchLogic.saveToTempCFP(saveContainer.getItemIds());
                } catch (PortalException ex) {
                    LOGGER.error(ex);
                } catch (SystemException ex) {
                    LOGGER.error(ex);
                }
                final int count = cfpLogic.isEmpty(sessionDTO);
                if (count > 0) {
                    MessageBox.showPlain(Icon.QUESTION, "Remove Confirmation", "Are you sure you want to remove the record from the Contract?", new MessageBoxListener() {

                        @Override
                        public void buttonClicked(ButtonId buttonId) {
                            if (ButtonId.YES.equals(buttonId)) {
                                List<CFPCompanyDTO> removeList = null;
                                removeList = cfpLogic.getLazySelectedCompaniesDeatils(0,count,true, null,null,false,TempEditCompanyContainer.getRecord(), Boolean.TRUE);
                                for (CFPCompanyDTO temp : removeList) {
                                    if (dashBoardLogic.validateCCPActuals(temp.getCompanySystemId()) != 0) {
                                        AbstractNotificationUtils.getErrorNotification("Halt", "The selected record " + temp.getCompanyName() + " cannot be removed as there are Actuals associated to it.");
                                        return;
                                    }
                                }
                                for (CFPCompanyDTO temp : removeList) {
                                    try {
                                        if (saveContainer.size() > 0) {
                                            CFPSearchLogic.saveToTempCFP(saveContainer.getItemIds());
                                            saveContainer.removeAllItems();
                                            }
                                        cfpLogic.removeCompany(Integer.valueOf(temp.getTempCFPSystemID()));
                                    } catch (Exception e) {
                                        LOGGER.error(e);
                                    }
                                    companyDetailsTable.setCurrentPage(NumericConstants.ONE);
                                }
                            }
                        }

                    }, ButtonId.YES, ButtonId.NO);
                } else {
                    AbstractNotificationUtils.getErrorNotification("Halt", "Please check mark a row for removal");
                }
            }
        });
    }

    private void configureFieldsOnFields() {
        companyDetailsTable.setVisible(false);
        btnPopulate.setEnabled(false);
        btnAllPopulate.setEnabled(false);
        massField.setEnabled(false);
        massDate.setEnabled(false);
        massValue.setEnabled(false);
        btnRemove.setEnabled(false);
    }

    private void record() {
        record.addItems(Constants.CURRENT, Constants.HISTORY, Constants.FUTURE);
        record.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                String value = String.valueOf(record.getValue());
                if (!isEditable) {
                    massPanel.setVisible(false);
                    loadTempCfpOnView(value);
                } else {
                    TempEditCompanyContainer.setRecord(value);
                    companyDetailsTable.setCurrentPage(NumericConstants.ONE);
                }

            }
        });
    }

    public void loadDates(Date startDate, Date endDate) {
        dates[0] = startDate;
        dates[NumericConstants.ONE] = endDate;
        companyFamilyPlanStartDate.validate();
        companyFamilyPlanEndDate.validate();
    }

    private void configureFields() {

        LOGGER.debug("Entering configureFields method");

        infoPanel.setVisible(true);
        tablePanel.setVisible(false);
        detailsLayout.setVisible(false);
        massCheck.addItem("Enable");
        massCheck.addItem(ConstantUtil.DISABLE);
        massCheck.setValue(ConstantUtil.DISABLE);
        massCheck.setImmediate(true);
        massCheck.setMultiSelect(false);

        massField.addItem(Constants.SELECT_ONE);
        massField.addItem(ConstantUtil.CFP_START_DATE);
        massField.addItem(ConstantUtil.CFP_END_DATE);
        massField.addItem(ConstantUtil.CFP_STATUS);
        massField.setNullSelectionAllowed(true);
        massField.setNullSelectionItemId(Constants.SELECT_ONE);
        massField.setImmediate(true);
        massField.select(Constants.SELECT_ONE);
        massField.markAsDirty();
        massField.setPageLength(NumericConstants.SEVEN);
        massField.setEnabled(false);

        massDate.setImmediate(true);
        massDate.setVisible(false);
        massDate.setDateFormat(Constants.MM_DD_YYYY);
        massDate.setDescription(Constants.DATE);
        massDate.setId("CompanyMassDate");
        massDate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used for Value Change Event in mass update and its
             * listener
             */
            @SuppressWarnings("PMD")
            public void valueChange(final ValueChangeEvent event) {
                massDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(massDate.getValue()));
            }
        });

        commonUtil.loadComboBox(massValue, com.stpl.app.contract.global.util.CommonUtils.STATUS, false);
        massValue.addItem(Constants.SELECT_ONE);
        massValue.addItem(Constants.ACTIVE);
        massValue.addItem(Constants.IN_ACTIVE);
        massValue.setEnabled(false);
        btnPopulate.setEnabled(false);
        btnAllPopulate.setEnabled(false);
        massField.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used for Value Change Event in mass Field
             */
            public void valueChange(final ValueChangeEvent event) {
                if (massField.getValue() != null) {
                    final String value = massField.getValue().toString();
                    if (Constants.CFP_START_DATE.equals(value) || Constants.CFP_END_DATE_CF.equals(value)) {
                        massDate.setVisible(true);
                        btnPopulate.setEnabled(true);
                        btnAllPopulate.setEnabled(true);
                        massValue.setVisible(false);
                    } else {
                        massDate.setVisible(false);
                        btnPopulate.setEnabled(true);
                        btnAllPopulate.setEnabled(true);
                        massValue.setVisible(true);
                    }
                } else {
                    massDate.setVisible(false);
                    massValue.setVisible(false);
                    massField.setValue(null);
                }
            }
        });
        massCheck.setErrorHandler(new ErrorHandler() {
            /**
             * Method used for handling error
             */
            public void error(final com.vaadin.server.ErrorEvent event) {
                return;
            }
        });

        massCheck.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            /**
             * Method used to mass check and its listener
             */
            public void valueChange(final ValueChangeEvent event) {
                if (ConstantUtil.DISABLE.equals(massCheck.getValue())) {
                    massValue.setValue(StringUtils.EMPTY);
                    massValue.setVisible(false);
                    massField.setEnabled(false);
                    massDate.setVisible(false);
                    btnPopulate.setEnabled(false);
                    btnPopulate.setReadOnly(true);
                    btnAllPopulate.setEnabled(false);
                    btnAllPopulate.setReadOnly(true);
                    if (cfpResultsBean != null) {
                        final List<CFPCompanyDTO> cfpCompanyDTOList = cfpResultsBean.getItemIds();
                        for (int i = 0; i < cfpCompanyDTOList.size(); i++) {
                            final CFPCompanyDTO dto = cfpCompanyDTOList.get(i);
                            if (dto.getCheckbox()) {
                                dto.setCheckbox(false);
                            }

                            cfpcompanyList.add(dto);
                        }
                        if (!cfpcompanyList.isEmpty()) {
                            cfpResultsBean.removeAllItems();
                            cfpResultsBean.addAll(cfpcompanyList);
                        }
                    }
                    markAsDirty();

                } else if ("Enable".equals(massCheck.getValue())) {
                    massField.setEnabled(true);
                    btnPopulate.setEnabled(true);
                    btnAllPopulate.setEnabled(true);
                    markAsDirty();
                }
                massCheck.focus();
            }
        });
        map.put(ConstantUtil.CFP_START_DATE, Constants.COMPANY_FAMILY_PLAN_START_DATE);
        map.put(ConstantUtil.CFP_END_DATE, Constants.CFP_END_DATE);
        map.put(ConstantUtil.CFP_STATUS, Constants.CFP_STATUS);
        LOGGER.debug("End of configureFields method");
    }

    /**
     * Method used for mass update.
     *
     * @return the button
     */
    public Button addBtnPopulate() {
        try {
            LOGGER.debug("Entering addBtnPopulate method");
            btnPopulate.setReadOnly(true);
            btnPopulate.setErrorHandler(new ErrorHandler() {
                /**
                 * Method used for handling error
                 */
                @SuppressWarnings("PMD")
                public void error(final com.vaadin.server.ErrorEvent event) {
                    LOGGER.error(event.toString());
                }
            });

            btnPopulate.addClickListener(new ClickListener() {
                /**
                 * Method used for update the table value
                 */
                public void buttonClick(final ClickEvent event) {

                    LOGGER.debug(" buttonClick ClickEvent event name=" + event.getButton().getCaption());
                    if (massField.getValue() != null) {
                        try {
                            String value = StringUtils.EMPTY;
                            final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                            fieldMass = map.get(massField.getValue()).toString();
                            if (Constants.COMPANY_FAMILY_PLAN_START_DATE.equals(fieldMass)) {
                                if (companyFamilyPlanStartDate.getValue() == null) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, ConstantUtil.PLEASE_PROVIDE_CFP_START_DATE_AND_TRY_AGAIN);
                                    return;
                                } else if (massDate.getValue() == null) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, ConstantUtil.PLEASE_PROVIDE_DATE_AND_TRY_AGAIN);
                                } else if (massDate.getValue().before(companyFamilyPlanStartDate.getValue())) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "CFP Start date cannot be before " + new SimpleDateFormat(ConstantUtil.DATE_FORMAT).format(companyFamilyPlanStartDate.getValue()));
                                    return;
                                } else if (companyFamilyPlanEndDate.getValue() != null && massDate.getValue().after(companyFamilyPlanEndDate.getValue())) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Start date cannot be after " + new SimpleDateFormat(ConstantUtil.DATE_FORMAT).format(companyFamilyPlanEndDate.getValue()));
                                    return;
                                } else {
                                    value = fmt.format(massDate.getValue());
                                }
                            } else if (Constants.CFP_END_DATE.equals(fieldMass)) {
                                if (companyFamilyPlanStartDate.getValue() == null) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, ConstantUtil.PLEASE_PROVIDE_CFP_START_DATE_AND_TRY_AGAIN);
                                    return;
                                } else if (massDate.getValue() == null) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, ConstantUtil.PLEASE_PROVIDE_DATE_AND_TRY_AGAIN);
                                    return;
                                } else if (companyFamilyPlanEndDate.getValue() != null && massDate.getValue().after(companyFamilyPlanEndDate.getValue())) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "CFP end date cannot be after " + new SimpleDateFormat(ConstantUtil.DATE_FORMAT).format(companyFamilyPlanEndDate.getValue()));
                                    return;
                                } else if (massDate.getValue().before(companyFamilyPlanStartDate.getValue())) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "End date cannot be before " + new SimpleDateFormat(ConstantUtil.DATE_FORMAT).format(companyFamilyPlanStartDate.getValue()));
                                    return;
                                } else {
                                    value = fmt.format(massDate.getValue());
                                }
                            } else if (Constants.CFP_STATUS.equals(fieldMass)) {
                                if (massValue.getValue() == null || Constants.SELECT_ONE.equals(massValue.getValue())) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Please provide CFP Status and try again.");
                                } else {
                                    String tempValue = String.valueOf(massValue.getValue());
                                    value = String.valueOf(DashBoardLogic.getHelperCode(Constants.STATUS, tempValue));
                                }
                            }
                            CFPSearchLogic.saveToTempCFP(saveContainer.getItemIds());
                            if (IfpLogic.isCheckedValidation("IMTD_CFP_DETAILS", String.valueOf(sessionDTO.getUiSessionId()))) {
                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, Constants.POPULATE_MSG);
                                return;
                            }
                            saveContainer.removeAllItems();
                            cfpLogic.populateToTempCFP(massField.getValue(), value, Boolean.FALSE);
                            loadTempCfp();
                        } catch (SystemException ex) {

                            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                            LOGGER.error(errorMsg);
                            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                        } catch (PortalException ex) {
                            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                            LOGGER.error(errorMsg);
                        } catch (Exception ex) {

                            LOGGER.error(ex);

                        }
                    } else {
                        AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, Constants.POPULATE_MSG);
                    }

                }
            });
            LOGGER.debug("End of addBtnPopulate method");
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return btnPopulate;
    }

    /**
     * Method used for add the all btn populate.
     *
     * @return the button
     */
    public Button addAllBtnPopulate() {
        LOGGER.debug("Entering addAllBtnPopulate method");
        btnAllPopulate.setReadOnly(true);
        btnAllPopulate.setErrorHandler(new ErrorHandler() {
            /**
             * Method used for handling error
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error(event.toString());
            }
        });

        btnAllPopulate.addClickListener(new ClickListener() {
            /**
             * Method used for mass update for all table value
             */
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("buttonClick(ClickEvent event) name=" + event.getButton().getCaption());
                if (massField.getValue() != null) {
                    try {
                        String value = StringUtils.EMPTY;
                        final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                        fieldMass = map.get(massField.getValue()).toString();
                        if (Constants.COMPANY_FAMILY_PLAN_START_DATE.equals(fieldMass)) {
                            if (companyFamilyPlanStartDate.getValue() == null) {
                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, ConstantUtil.PLEASE_PROVIDE_CFP_START_DATE_AND_TRY_AGAIN);
                                return;
                            } else if (massDate.getValue() == null) {
                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, ConstantUtil.PLEASE_PROVIDE_DATE_AND_TRY_AGAIN);
                            } else if (massDate.getValue().before(companyFamilyPlanStartDate.getValue())) {
                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "CFP Start date cannot be before " + new SimpleDateFormat(ConstantUtil.DATE_FORMAT).format(companyFamilyPlanStartDate.getValue()));
                                return;
                            } else if (companyFamilyPlanEndDate.getValue() != null && massDate.getValue().after(companyFamilyPlanEndDate.getValue())) {
                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Start date cannot be after " + new SimpleDateFormat(ConstantUtil.DATE_FORMAT).format(companyFamilyPlanEndDate.getValue()));
                                return;
                            } else {
                                value = fmt.format(massDate.getValue());
                            }
                        } else if (Constants.CFP_END_DATE.equals(fieldMass)) {
                            if (companyFamilyPlanStartDate.getValue() == null) {
                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, ConstantUtil.PLEASE_PROVIDE_CFP_START_DATE_AND_TRY_AGAIN);
                                return;
                            } else if (massDate.getValue() == null) {
                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, ConstantUtil.PLEASE_PROVIDE_DATE_AND_TRY_AGAIN);
                            } else if (companyFamilyPlanEndDate.getValue() != null && massDate.getValue().after(companyFamilyPlanEndDate.getValue())) {
                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "CFP end date cannot be after " + new SimpleDateFormat(ConstantUtil.DATE_FORMAT).format(companyFamilyPlanEndDate.getValue()));
                                return;
                            } else if (massDate.getValue().before(companyFamilyPlanStartDate.getValue())) {
                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "End date cannot be before " + new SimpleDateFormat(ConstantUtil.DATE_FORMAT).format(companyFamilyPlanStartDate.getValue()));
                                return;
                            } else {
                                value = fmt.format(massDate.getValue());
                            }
                        } else if (Constants.CFP_STATUS.equals(fieldMass)) {
                            if (massValue.getValue() == null || Constants.SELECT_ONE.equals(massValue.getValue()) || StringUtils.EMPTY.equals(massValue.getValue())) {
                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Please provide CFP Status and try again.");
                            } else {
                                String tempValue = String.valueOf(massValue.getValue());
                                value = String.valueOf(DashBoardLogic.getHelperCode(Constants.STATUS, tempValue));
                            }
                        }
                        CFPSearchLogic.saveToTempCFP(saveContainer.getItemIds());
                        saveContainer.removeAllItems();
                        cfpLogic.populateToTempCFP(massField.getValue(), value, Boolean.TRUE);
                        loadTempCfp();
                    } catch (SystemException ex) {
                        final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                        LOGGER.error(errorMsg);
                        AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                    } catch (PortalException ex) {
                        final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                        LOGGER.error(errorMsg);
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                } else {
                    AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, Constants.POPULATE_MSG);
                }
            }
        });
        LOGGER.debug("End of addAllBtnPopulate method");
        return btnAllPopulate;
    }

    public void loadTempCfp() {
        tempLazyContainer = new LazyBeanItemContainer(CFPCompanyDTO.class, new TempCompanyContainer(companyDetailsTable, saveContainer, sessionDTO), lazyLoadCriteria);
        companyDetailsTable.setCurrentPage(companyDetailsTable.getCurrentPage());
    }

    public void btnExportLogic() {
            excelExport.addClickListener(new ClickListener() {
            /**
             * Invoked when a button is clicked
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                try {
                    LOGGER.debug("Entering btnExportLogic method");
                    LOGGER.debug("createWorkSheet()");
                    createWorkSheet("Companies_Details",companyDetailsTable);
                    LOGGER.debug("End of btnExportLogic method");
                } catch (SystemException ex) {
                    java.util.logging.Logger.getLogger(CompaniesTab.class.getName()).log(Level.SEVERE, null, ex);
                } catch (PortalException ex) {
                    java.util.logging.Logger.getLogger(CompaniesTab.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                      LOGGER.debug(ex);
                }
            }
        });
        
    }

    /**
     * Method used for Creates the work sheet.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public void createWorkSheet(String moduleName, ExtCustomTable resultTable)  throws SystemException, PortalException, NoSuchMethodException, IllegalAccessException,  InvocationTargetException {
        String[] header = resultTable.getColumnHeaders();
        header = (String[]) ArrayUtils.removeElement(header, StringUtils.EMPTY);
        header = (String[]) ArrayUtils.removeElement(header, ConstantUtil.BLANK_SPACE);
        CFPSearchLogic.saveToTempCFP(saveContainer.getItemIds());
        cfpCompanyDTO = dashBoardLogic.getCfpDetails((Integer) sessionDTO.getContractSystemId(),
                (Integer) sessionDTO.getCfpSystemId());
        final long recordCount = cfpCompanyDTO.getCfpcompanyList().size();
        ExcelExportforBB.createWorkSheet(header, recordCount, this, UI.getCurrent(), moduleName.toUpperCase());
    }

    public void createWorkSheetContent(final PrintWriter printWriter) throws  NoSuchFieldException,  IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        LOGGER.debug("Entering createWorkSheetContent method");
        if (String.valueOf(level.getValue()).equals(Constants.DETAILS)) {

            final List<CFPCompanyDTO> exportCompany = cfpCompanyDTO.getCfpcompanyList();

            Object[] columns = companyDetailsTable.getVisibleColumns();
            columns = ArrayUtils.removeElement(columns, Constants.CHECK_BOX);
            ExcelExportforBB.createFileContent(columns, exportCompany, printWriter);
        }

        LOGGER.debug("End of createWorkSheetContent method");
    }

    /**
     * Method used for Creates the header row.
     *
     * @param pwValue the pw value
     */

    /**
     * Creates the work sheet content.
     *
     * @param pwValue the pw value
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    /**
     * Method used Send converted file to user.
     *
     * @param app the app
     * @param fileToExport the file to export
     * @param exportFileName the export file name
     * @return true, if send converted file to user
     */
    public boolean sendConvertedFileToUser(final UI app, final File fileToExport, final String exportFileName) throws FileNotFoundException {
        LOGGER.debug("Entering sendConvertedFileToUser method");
        final TemporaryFileDownloadResource resource = new TemporaryFileDownloadResource(app, exportFileName, ExcelExportUtil.EXCEL_MIME_TYPE, fileToExport);
        Page.getCurrent().open(resource, ExcelExportUtil.WINDOW_NAME, true);
        LOGGER.debug("End of sendConvertedFileToUser method");
        return true;
    }

    /**
     * Gets the status select.
     *
     * @param select the select
     * @return the status select
     */
    public ComboBox getStatusSelect(final ComboBox select) {
        LOGGER.debug("Entering getStatusSelect method");
        select.addItem(Constants.SELECT_ONE);
        select.addItem(Constants.ACTIVE);
        select.addItem(Constants.IN_ACTIVE);
        LOGGER.debug("End of getStatusSelect method");
        return select;
    }

    /**
     * Adds the item details table.
     *
     * @return the vertical layout
     */
    public void addItemDetailsTable() {
        try {
            LOGGER.debug("Entering addItemDetailsTable method");
            Object[] objAvail = ContractUtils.getInstance().cfpItemDetailsCol;
            TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, objAvail, fieldContract, Constants.EDIT);

            companyDetailsTable.markAsDirty();
            TempEditCompanyContainer = new TempCompanyContainer(companyDetailsTable, saveContainer, sessionDTO);
            tempLazyContainer
                    = new LazyBeanItemContainer(CFPCompanyDTO.class, TempEditCompanyContainer, lazyLoadCriteria);
            if (tableResultCustom.getObjResult().length > 0) {
                companyDetailsTable.setContainerDataSource(tempLazyContainer);
                companyDetailsTable.setVisibleColumns(tableResultCustom.getObjResult());
                companyDetailsTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
            } else {
                resultPanel.setVisible(false);
            }

            companyDetailsTable.setPageLength(
                    NumericConstants.FIVE);
            companyDetailsTable.sinkItemPerPageWithPageLength(
                    false);
            companyDetailsTable.setImmediate(
                    true);
            companyDetailsTable.addStyleName(
                    "valo-theme-extfiltertable");
            companyDetailsTable.addStyleName(
                    Constants.FILTER_BAR);
            companyDetailsTable.setSelectable(
                    true);
            companyDetailsTable.setSizeFull();

            companyDetailsTable.setColumnCheckBox(
                    Constants.CHECK_BOX, true, false);
            tempDate = new HashMap<>();

            companyDetailsTable.setTableFieldFactory(
                    new CFPTableGenerator(saveContainer, fieldFactoryDates, tempDate,companyDetailsTable,sessionDTO));
            companyDetailsTable.setFilterBarVisible(
                    true);
            companyDetailsTable.setFilterGenerator(
                    new CfpDetailsFilterGenerator());
            companyDetailsTable.setFilterDecorator(
                    new ExtDemoFilterDecorator());
            companyDetailsTable.setFilterFieldVisible(
                    Constants.CHECK_BOX, false);
            companyDetailsTable.setEditable(
                    true);
            companyDetailsTable.addStyleName(
                    "TableCheckBox");
            companyDetailsTable.setErrorHandler(
                    new ErrorHandler() {
                        /**
                         * Method used for handling error
                         */
                        @SuppressWarnings("PMD")
                        public void error(final com.vaadin.server.ErrorEvent event
                        ) {
                            return;
                        }
                    });
            companyDetailsTable.addColumnCheckListener(
                    new ExtCustomTable.ColumnCheckListener() {

                        @Override
                        public void columnCheck(ExtCustomTable.ColumnCheckEvent event
                        ) {
                            try {
                                if (Constants.CHECK_BOX.equals(event.getPropertyId().toString())) {
                                    if (event.isChecked()) {
                                        for (CFPCompanyDTO dto : saveContainer.getItemIds()) {
                                            dto.setCheckbox(Boolean.TRUE);
                                        }
                                        CFPSearchLogic.saveToTempCFP(saveContainer.getItemIds());
                                        saveContainer.removeAllItems();
                                        cfpLogic.populateToTempCFP("CheckBox", NumericConstants.ONE, Boolean.TRUE);
                                        companyDetailsTable.setCurrentPage(companyDetailsTable.getCurrentPage());
                                        companyDetailsTable.setColumnCheckBox(Constants.CHECK_BOX, true, true);
                                    } else {
                                        for (CFPCompanyDTO dto : saveContainer.getItemIds()) {
                                            dto.setCheckbox(Boolean.FALSE);
                                        }
                                        CFPSearchLogic.saveToTempCFP(saveContainer.getItemIds());
                                        saveContainer.removeAllItems();
                                        cfpLogic.populateToTempCFP("CheckBox", 0, Boolean.TRUE);
                                        companyDetailsTable.setCurrentPage(companyDetailsTable.getCurrentPage());
                                        companyDetailsTable.setColumnCheckBox(Constants.CHECK_BOX, true, false);
                                    }
                                }
                            } catch (Exception ex) {
                                LOGGER.error(ex);
                            }
                        }
                    }
            );
            HorizontalLayout tempLayout = ResponsiveUtils.getResponsiveControls(companyDetailsTable.createControls());

            companyDetailsTable.setColumnCollapsingAllowed(
                    true);
            ContractUtils.getCustomizedComboBox(
                    (ComboBox) ((CssLayout) tempLayout.getComponent(0)).getComponent(NumericConstants.ONE));
            resultsTableLayout.addComponent(tempLayout);
        } catch (Exception ex) {
             LOGGER.error(ex);
        }
    }

    public void setDefaultTableWidth(final CustomePagedFilterTable table) {

        try {
            table.setColumnCollapsingAllowed(true);
            int browserWidth = Page.getCurrent().getBrowserWindowWidth();
            if (browserWidth > NumericConstants.ONE_FIVE_ONE_SIX) {
                lazyLoadCriteria.setCustomDirty(false);
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getSixColumns(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
                lazyLoadCriteria.setCustomDirty(true);
            } else if (browserWidth < NumericConstants.ONE_FIVE_ONE_SIX && browserWidth > NumericConstants.NINE_SEVEN_EIGHT) {
                lazyLoadCriteria.setCustomDirty(false);
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getCollapsibleColumnsDefault1515Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
                lazyLoadCriteria.setCustomDirty(true);
            } else if (browserWidth < NumericConstants.NINE_SEVEN_EIGHT && browserWidth > NumericConstants.SIX_HUNDRED) {
                lazyLoadCriteria.setCustomDirty(false);
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }

                if (tempLazyContainer != null && tempLazyContainer.getItemIds().isEmpty()) {
                    for (Object propertyId : getCollapsibleColumns978Px(table)) {
                        table.setColumnCollapsed(propertyId, true);
                    }
                } else {
                    for (Object propertyId : getCollapsibleColumns600Px(table)) {
                        table.setColumnCollapsed(propertyId, true);
                    }
                }
                lazyLoadCriteria.setCustomDirty(true);
            } else if (browserWidth < NumericConstants.SIX_HUNDRED && browserWidth > NumericConstants.FOUR_EIGHT_ZERO) {
                lazyLoadCriteria.setCustomDirty(false);
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getCollapsibleColumns600Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
                lazyLoadCriteria.setCustomDirty(true);
            } else if (browserWidth < NumericConstants.FOUR_EIGHT_ZERO && browserWidth > NumericConstants.THREE_TWO_ZERO) {
                lazyLoadCriteria.setCustomDirty(false);
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getCollapsibleColumns480Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
                lazyLoadCriteria.setCustomDirty(true);
            } else if (browserWidth < NumericConstants.THREE_TWO_ZERO) {
                lazyLoadCriteria.setCustomDirty(false);
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getCollapsibleColumns480Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
                lazyLoadCriteria.setCustomDirty(true);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
            
        }
    }

    private static Object[] getCollapsibleColumns480Px(CustomePagedFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class
        );
        List<Object> list = new ArrayList<>(Arrays.asList(propertyIds));

        list.remove(propertyIds[NumericConstants.ONE]);
        list.remove(propertyIds[NumericConstants.TWO]);
        propertyIds = list.toArray(new Object[list.size()]);

        for (Object propertyId
                : table.getVisibleColumns()) {
            table.setColumnCollapsed(propertyId, false);
        }

        for (Object propertyId : propertyIds) {
            table.setColumnCollapsed(propertyId, true);
        }

        return propertyIds;
    }

    private static Object[] getCollapsibleColumns978Px(CustomePagedFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class
        );
        List<Object> list = new ArrayList<>(Arrays.asList(propertyIds));

        list.remove(propertyIds[NumericConstants.ONE]);
        list.remove(propertyIds[NumericConstants.TWO]);
        list.remove(propertyIds[NumericConstants.THREE]);
        propertyIds = list.toArray(new Object[list.size()]);
        for (Object propertyId
                : table.getVisibleColumns()) {
            table.setColumnCollapsed(propertyId, false);
        }

        for (Object propertyId : propertyIds) {
            table.setColumnCollapsed(propertyId, true);
        }
        return propertyIds;
    }

    private static String[] getCollapsibleColumns600Px(CustomePagedFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class
        );
        List<String> list = new ArrayList<>(Arrays.asList(propertyIds));

        list.remove(propertyIds[0]);
        list.remove(propertyIds[NumericConstants.ONE]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    private static String[] getCollapsibleColumnsDefault1515Px(CustomePagedFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class
        );
        List<String> list = new ArrayList<>(Arrays.asList(propertyIds));

        list.remove(propertyIds[0]);
        list.remove(propertyIds[NumericConstants.ONE]);
        list.remove(propertyIds[NumericConstants.TWO]);
        list.remove(propertyIds[NumericConstants.THREE]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    private static Object[] getSixColumns(CustomePagedFilterTable table) {
        table.setColumnCollapsingAllowed(true);
        table.setImmediate(true);
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class
        );
        List<Object> list = new ArrayList<>(Arrays.asList(visibleColumns));
        for (int i = 0;
                i < NumericConstants.SIX; i++) {
            list.remove(propertyIds[i]);
        }
        propertyIds = list.toArray(new Object[list.size()]);

        for (Object propertyId
                : table.getVisibleColumns()) {
            table.setColumnCollapsed(propertyId, false);
        }

        for (Object propertyId : propertyIds) {
            table.setColumnCollapsed(propertyId, true);
        }

        return propertyIds;
    }

    public void addResponsiveSearchTableCollapse(final CustomePagedFilterTable table) {
        reloadMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
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
                    if (browserWidth > NumericConstants.ONE_FIVE_ONE_SIX && reloadMap.get(NumericConstants.ONE_FIVE_ONE_SIX)) {
                        lazyLoadCriteria.setCustomDirty(false);

                        for (Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (Object propertyId : getSixColumns(table)) {
                            table.setColumnCollapsed(propertyId, browserWidth > NumericConstants.ONE_FIVE_ONE_SIX);
                        }
                        reloadMap.put(NumericConstants.ONE_FIVE_ONE_SIX, false);
                        reloadMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                        reloadMap.put(NumericConstants.SIX_HUNDRED, true);
                        reloadMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                        reloadMap.put(NumericConstants.THREE_TWO_ZERO, true);
                        reloadMap.put(0, true);
                        lazyLoadCriteria.setCustomDirty(true);
                    } else if (browserWidth < NumericConstants.ONE_FIVE_ONE_SIX && browserWidth > NumericConstants.NINE_SEVEN_EIGHT && reloadMap.get(NumericConstants.NINE_SEVEN_EIGHT)) {
                        lazyLoadCriteria.setCustomDirty(false);
                        for (Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (Object propertyId : getCollapsibleColumnsDefault1515Px(table)) {
                            table.setColumnCollapsed(propertyId, browserWidth < NumericConstants.ONE_FIVE_ONE_SIX);
                        }

                        reloadMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                        reloadMap.put(NumericConstants.NINE_SEVEN_EIGHT, false);
                        reloadMap.put(NumericConstants.SIX_HUNDRED, true);
                        reloadMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                        reloadMap.put(NumericConstants.THREE_TWO_ZERO, true);
                        reloadMap.put(0, true);
                        lazyLoadCriteria.setCustomDirty(true);
                    } else if (browserWidth < NumericConstants.NINE_SEVEN_EIGHT && browserWidth > NumericConstants.SIX_HUNDRED && reloadMap.get(NumericConstants.SIX_HUNDRED)) {
                        lazyLoadCriteria.setCustomDirty(false);
                        for (Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }

                        if (tempLazyContainer != null && tempLazyContainer.getItemIds().isEmpty()) {
                            for (Object propertyId : getCollapsibleColumns978Px(table)) {
                                table.setColumnCollapsed(propertyId, true);
                            }
                        } else {
                            for (Object propertyId : getCollapsibleColumns600Px(table)) {
                                table.setColumnCollapsed(propertyId, true);
                            }
                        }

                        reloadMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                        reloadMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                        reloadMap.put(NumericConstants.SIX_HUNDRED, false);
                        reloadMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                        reloadMap.put(NumericConstants.THREE_TWO_ZERO, true);
                        reloadMap.put(0, true);
                        lazyLoadCriteria.setCustomDirty(true);
                    } else if (browserWidth < NumericConstants.SIX_HUNDRED && browserWidth > NumericConstants.FOUR_EIGHT_ZERO && reloadMap.get(NumericConstants.FOUR_EIGHT_ZERO)) {
                        lazyLoadCriteria.setCustomDirty(false);       
                        for (Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (Object propertyId : getCollapsibleColumns600Px(table)) {
                            table.setColumnCollapsed(propertyId, true);
                        }
                        reloadMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                        reloadMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                        reloadMap.put(NumericConstants.SIX_HUNDRED, true);
                        reloadMap.put(NumericConstants.FOUR_EIGHT_ZERO, false);
                        reloadMap.put(NumericConstants.THREE_TWO_ZERO, true);
                        reloadMap.put(0, true);
                        lazyLoadCriteria.setCustomDirty(true);        
                    } else if (browserWidth < NumericConstants.FOUR_EIGHT_ZERO && browserWidth > NumericConstants.THREE_TWO_ZERO && reloadMap.get(NumericConstants.THREE_TWO_ZERO)) {
                        lazyLoadCriteria.setCustomDirty(false);
                        for (Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (Object propertyId : getCollapsibleColumns480Px(table)) {
                            table.setColumnCollapsed(propertyId, browserWidth < NumericConstants.FOUR_EIGHT_ZERO);
                        }
                        reloadMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                        reloadMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                        reloadMap.put(NumericConstants.SIX_HUNDRED, true);
                        reloadMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                        reloadMap.put(NumericConstants.THREE_TWO_ZERO, false);
                        reloadMap.put(0, true);
                        lazyLoadCriteria.setCustomDirty(true);
                    } else if (browserWidth < NumericConstants.THREE_TWO_ZERO && reloadMap.get(0)) {
                        lazyLoadCriteria.setCustomDirty(false);
                        for (Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (Object propertyId : getCollapsibleColumns480Px(table)) {
                            table.setColumnCollapsed(propertyId, browserWidth < NumericConstants.THREE_TWO_ZERO);
                        }
                        reloadMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                        reloadMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                        reloadMap.put(NumericConstants.SIX_HUNDRED, true);
                        reloadMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                        reloadMap.put(NumericConstants.THREE_TWO_ZERO, true);
                        reloadMap.put(0, false);
                        lazyLoadCriteria.setCustomDirty(true);
                    }

                }
            });
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Focus the Mass Populate fields on tab change.
     */
    public void focusMassCheck() {
        this.massCheck.focus();
    }

    /**
     * Removes the items from the save container.
     */
    public void removeItemsFromSaveContainer() {
        this.saveContainer.removeAllItems();
    }

    /**
     * Method used for Adds the item details table.
     *
     * @return the vertical layout
     */
    public void addItemDetailsTableFromView() {

        LOGGER.debug("Entering addItemDetailsTable method");
        viewCompanyDetailsTable.setVisible(true);
        viewCompanyDetailsTable.markAsDirty();

        viewCompanyDetailsTable.setPageLength(NumericConstants.SEVEN);
        viewCompanyDetailsTable.setImmediate(true);
        viewCompanyDetailsTable.setSelectable(true);
        viewCompanyDetailsTable.setSizeFull();
        viewCompanyDetailsTable.setContainerDataSource(cfpResultsBean);
        Object[] objAvail = ContractUtils.getInstance().cfpItemDetailsViewCol;
        TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, objAvail, fieldContract, Constants.VIEW_MODE);
        if (tableResultCustom.getObjResult().length > 0) {

            viewCompanyDetailsTable.setVisibleColumns(tableResultCustom.getObjResult());
            viewCompanyDetailsTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
        } else {
            resultPanel.setVisible(false);
        }
        viewCompanyDetailsTable.setErrorHandler(new ErrorHandler() {
            /**
             * Method used for Handling error
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                return;
            }
        });
        addResponsiveVerticalTabThreeLayout();
        ResponsiveUtils.addResponsiveTableCollapse(viewCompanyDetailsTable);
        LOGGER.debug("End of addItemDetailsTable method");
    }

    public void loadTempCfpOnView(String value) {
        tempViewCompanyContainer = new TempViewCompanyContainer(sessionDTO);
        tempViewCompanyContainer.setRecord(value);
        tempLazyContainer
                = new LazyBeanItemContainer(CFPCompanyDTO.class, tempViewCompanyContainer, new LazyLoadCriteria());
        viewCompanyDetailsTable.setContainerDataSource(tempLazyContainer);
        Object[] objAvail = ContractUtils.getInstance().cfpItemDetailsViewCol;

        TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, objAvail, fieldContract, Constants.VIEW_MODE);

        if (tableResultCustom.getObjResult().length > 0) {
            viewCompanyDetailsTable.setVisibleColumns(tableResultCustom.getObjResult());
            viewCompanyDetailsTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
        } else {
            resultPanel.setVisible(false);
        }
    }

    public void addResponsiveVerticalTabThreeLayout() {

        reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
        reloadVerticalLayoutTabThreeMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
        reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
        reloadVerticalLayoutTabThreeMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
        reloadVerticalLayoutTabThreeMap.put(NumericConstants.EIGHT_HUNDRED, true);
        reloadVerticalLayoutTabThreeMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
        reloadVerticalLayoutTabThreeMap.put(NumericConstants.THREE_TWO_ZERO, true);
        reloadVerticalLayoutTabThreeMap.put(0, true);

        Page.getCurrent().addBrowserWindowResizeListener(new Page.BrowserWindowResizeListener() {
            @Override
            public void browserWindowResized(final Page.BrowserWindowResizeEvent event) {

                int browserWidth = Page.getCurrent().getBrowserWindowWidth();
                if (browserWidth > NumericConstants.ONE_FIVE_ONE_SIX && reloadVerticalLayoutTabThreeMap.get(NumericConstants.ONE_FIVE_ONE_SIX)) {
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_FIVE_ONE_SIX, false);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.EIGHT_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THREE_TWO_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                    getCollapsibleColumnsDefault(viewCompanyDetailsTable);
                } else if (browserWidth < NumericConstants.ONE_FIVE_ONE_SIX && browserWidth > NumericConstants.THOUSAND_THREE_HUNDRED && reloadVerticalLayoutTabThreeMap.get(NumericConstants.THOUSAND_THREE_HUNDRED)) {
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, false);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.EIGHT_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THREE_TWO_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                    getCollapsibleColumnsDefault(viewCompanyDetailsTable);
                } else if (browserWidth < NumericConstants.THOUSAND_THREE_HUNDRED && browserWidth > NumericConstants.ONE_ZERO_TWO_FOUR && reloadVerticalLayoutTabThreeMap.get(NumericConstants.ONE_ZERO_TWO_FOUR)) {
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, false);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.EIGHT_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THREE_TWO_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                    getCollapsibleColumns978Px(viewCompanyDetailsTable);
                } else if (browserWidth < NumericConstants.ONE_ZERO_TWO_FOUR && browserWidth > NumericConstants.NINE_SEVEN_EIGHT && reloadVerticalLayoutTabThreeMap.get(NumericConstants.NINE_SEVEN_EIGHT)) {
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.NINE_SEVEN_EIGHT, false);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.EIGHT_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THREE_TWO_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                    getCollapsibleColumns978Px(viewCompanyDetailsTable);
                } else if (browserWidth < NumericConstants.NINE_SEVEN_EIGHT && browserWidth > NumericConstants.EIGHT_HUNDRED && reloadVerticalLayoutTabThreeMap.get(NumericConstants.EIGHT_HUNDRED)) {
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.EIGHT_HUNDRED, false);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THREE_TWO_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                    if (viewCompanyDetailsTable.getItemIds().isEmpty()) {
                        getCollapsibleColumns978Px(viewCompanyDetailsTable);
                    } else {
                        getCollapsibleColumnsTwoColumns(viewCompanyDetailsTable);
                    }
                } else if (browserWidth <= NumericConstants.EIGHT_HUNDRED && browserWidth > NumericConstants.FOUR_EIGHT_ZERO && reloadVerticalLayoutTabThreeMap.get(NumericConstants.FOUR_EIGHT_ZERO)) {
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.EIGHT_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.FOUR_EIGHT_ZERO, false);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THREE_TWO_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                    if (viewCompanyDetailsTable.getItemIds().isEmpty()) {
                        getCollapsibleColumns978Px(viewCompanyDetailsTable);
                    } else {
                        getCollapsibleColumnsTwoColumns(viewCompanyDetailsTable);
                    }
                } else if (browserWidth < NumericConstants.FOUR_EIGHT_ZERO && browserWidth > NumericConstants.THREE_TWO_ZERO && reloadVerticalLayoutTabThreeMap.get(NumericConstants.THREE_TWO_ZERO)) {
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.EIGHT_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THREE_TWO_ZERO, false);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                    getCollapsibleColumnsTwoColumns(viewCompanyDetailsTable);
                } else if (browserWidth < NumericConstants.THREE_TWO_ZERO && reloadVerticalLayoutTabThreeMap.get(0)) {
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.EIGHT_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THREE_TWO_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(0, false);
                    getCollapsibleColumnsOneColumn(viewCompanyDetailsTable);
                }

            }
        });
    }

    private static Object[] getCollapsibleColumns978Px(Table table) {
        table.setColumnCollapsingAllowed(true);
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class
        );
        List<Object> list = new ArrayList<>(Arrays.asList(propertyIds));

        list.remove(propertyIds[NumericConstants.ONE]);
        list.remove(propertyIds[NumericConstants.TWO]);
        list.remove(propertyIds[NumericConstants.THREE]);
        propertyIds = list.toArray(new Object[list.size()]);
        for (Object propertyId
                : table.getVisibleColumns()) {
            table.setColumnCollapsed(propertyId, false);
        }

        for (Object propertyId : propertyIds) {
            table.setColumnCollapsed(propertyId, true);
        }
        return propertyIds;
    }

    private static Object[] getCollapsibleColumnsDefault(Table table) {
        table.setColumnCollapsingAllowed(true);
        table.setImmediate(true);
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class
        );
        List<Object> list = new ArrayList<>(Arrays.asList(visibleColumns));
        for (int i = 0, j = NumericConstants.SIX;
                i < j;
                i++) {
            list.remove(propertyIds[i]);
        }
        propertyIds = list.toArray(new Object[list.size()]);

        for (Object propertyId
                : table.getVisibleColumns()) {
            table.setColumnCollapsed(propertyId, false);
        }

        for (Object propertyId : propertyIds) {
            table.setColumnCollapsed(propertyId, true);
        }

        return propertyIds;
    }

    private static Object[] getCollapsibleColumnsTwoColumns(Table table) {
        table.setColumnCollapsingAllowed(true);
        table.setImmediate(true);
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class
        );
        List<Object> list = new ArrayList<>(Arrays.asList(visibleColumns));
        for (int i = 0, j = NumericConstants.TWO;
                i < j;
                i++) {
            list.remove(propertyIds[i]);
        }
        propertyIds = list.toArray(new Object[list.size()]);

        for (Object propertyId
                : table.getVisibleColumns()) {
            table.setColumnCollapsed(propertyId, false);
        }

        for (Object propertyId : propertyIds) {
            table.setColumnCollapsed(propertyId, true);
        }

        return propertyIds;
    }

    private static Object[] getCollapsibleColumnsOneColumn(Table table) {
        table.setColumnCollapsingAllowed(true);
        table.setImmediate(true);
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class
        );
        List<Object> list = new ArrayList<>(Arrays.asList(visibleColumns));
        for (int i = 0, j = NumericConstants.ONE;
                i < j;
                i++) {
            list.remove(propertyIds[i]);
        }
        propertyIds = list.toArray(new Object[list.size()]);

        for (Object propertyId
                : table.getVisibleColumns()) {
            table.setColumnCollapsed(propertyId, false);
        }

        for (Object propertyId : propertyIds) {
            table.setColumnCollapsed(propertyId, true);
        }

        return propertyIds;
    }

    private static boolean defaultColumnsVisible(Table table) {
        boolean result = true;
        for (Object propertyId : getCollapsibleColumnsOneColumn(table)) {
            if (table.isColumnCollapsed(propertyId) == Page.getCurrent().getBrowserWindowWidth() < NumericConstants.EIGHT_HUNDRED) {
                result = false;
            }
        }
        return result;
    }

    private void addResponsiveGrid(Map<String, AppPermission> contractDashboard) {
        final ContractHeaderLogic contractLogic = new ContractHeaderLogic();
        LOGGER.debug("Entering configurePermission");
        try {

            List<Object> resultList = contractLogic.getFieldsForSecurity(UISecurityUtil.CONTRACT_DASHBOARD, ConstantUtil.COMPANIES);
            commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout, contractDashboard, Constants.EDIT);

        } catch (Exception ex) {

            LOGGER.error(ex);

        }

    }

    void getcfpHeader() {

        try {

            commonUtils.loadComboBox(cfpStatus, UIUtils.STATUS, false);

            commonUtils.loadComboBox(cfptype, UIUtils.CFP_TYPE, false);

            commonUtils.loadComboBox(cfpCategory, UIUtils.CFP_CATEGORY, false);

            commonUtils.loadComboBox(cfptrade, UIUtils.CFP_TRADE_CLASS, false);

            commonUtils.loadComboBox(cfpDesignation, UIUtils.CFP_DESIGNATION, false);

            commonUtils.loadComboBox(salesInclusion, UIUtils.LOCKED_STATUS, false);
            cfpStatus.setImmediate(true);
            cfpStatus.setValidationVisible(true);
            cfpStatus.setRequired(true);
            cfpStatus.setRequiredError("CFP status should be selected on Companies tab");
            salesInclusion.setValidationVisible(true);
            salesInclusion.setImmediate(true);
            salesInclusion.setRequired(true);
            salesInclusion.setRequiredError("Sales Inclusion should be selected on Companies tab");

            companyFamilyPlanStartDate.setValidationVisible(true);
            companyFamilyPlanStartDate.setImmediate(true);
            companyFamilyPlanStartDate.setRequired(true);
            companyFamilyPlanStartDate.setRequiredError("Start Date should  be selected on Companies tab");
            companyFamilyPlanStartDate.setDateFormat(Constants.MM_DD_YYYY);
            companyFamilyPlanStartDate.setDescription(Constants.DATE);
            companyFamilyPlanStartDate.addValidator(new DateValidatorContractCFP("StartDate"));
            attachListeners(companyFamilyPlanStartDate, ConstantUtil.START_DATE);

            companyFamilyPlanEndDate.setImmediate(true);
            companyFamilyPlanEndDate.setValidationVisible(true);
            companyFamilyPlanEndDate.setDateFormat(Constants.MM_DD_YYYY);
            companyFamilyPlanEndDate.setDescription(Constants.DATE);
            companyFamilyPlanEndDate.addValidator(new DateValidatorContractCFP("EndDate"));
            attachListeners(companyFamilyPlanEndDate, ConstantUtil.END_DATE);

        } catch (Exception ex) {


            LOGGER.error(ex);

        }

    }
    

    @SuppressWarnings("rawtypes")
    public class DateValidatorContractCFP extends AbstractValidator {

        String field;

        /**
         * The Constructor.
         */
        public DateValidatorContractCFP() {
            super("");
        }

        /**
         * The Constructor.
         *
         * @param errorMessage the error message
         */
        public DateValidatorContractCFP(final String field) {
            super("");
            this.field = field;
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

            if (companyFamilyPlanStartDate.getValue() != null && companyFamilyPlanEndDate.getValue() != null) {
                if (companyFamilyPlanStartDate.getValue().after(companyFamilyPlanEndDate.getValue())) {
                    throw new Validator.InvalidValueException("CFP End Date should be greater than CFP Start Date");
                } else if (companyFamilyPlanStartDate.getValue().equals(companyFamilyPlanEndDate.getValue())) {
                    throw new Validator.InvalidValueException("Start date and End date are equal");
                }
            }
            if (dates.length > 0) {
                if ("StartDate".equals(field) && companyFamilyPlanStartDate.getValue() != null) {
                    if (dates[0] != null && companyFamilyPlanStartDate.getValue().before(dates[0])) {
                        throw new Validator.InvalidValueException("Select CFP Start date after " + format.format(dates[0]));
                    } else if (dates[NumericConstants.ONE] != null && companyFamilyPlanStartDate.getValue().after((Date) dates[NumericConstants.ONE])) {
                        throw new Validator.InvalidValueException("Select CFP Start date before " + format.format(dates[NumericConstants.ONE]));
                    }
                }
                if ("EndDate".equals(field) && companyFamilyPlanEndDate.getValue() != null && dates[NumericConstants.ONE] != null && companyFamilyPlanEndDate.getValue().after((Date) dates[NumericConstants.ONE])) {
                        throw new Validator.InvalidValueException("Select CFP End date before " + format.format(dates[NumericConstants.ONE]));
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

            if (companyFamilyPlanStartDate.getValue() != null && companyFamilyPlanEndDate.getValue() != null) {
                return companyFamilyPlanStartDate.getValue().compareTo(companyFamilyPlanEndDate.getValue()) <= 0;
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

    @UiHandler("level")
    public void levelchange(Property.ValueChangeEvent event) {
        excelExport.setVisible(true);
        setLevel(level);
        String value = String.valueOf(level.getValue());
        if (Constants.HEADER.equalsIgnoreCase(value)) {

            if (Constants.HISTORY.equals(view.getValue().toString())) {
                HeaderCompanyDetailsTable.setVisible(true);
                configureHeadertable();
                 infoPanel.setVisible(false);

            } else {
                HeaderCompanyDetailsTable.setVisible(false);
                infoPanel.setVisible(true);
            }
            detailsLayout.setVisible(false);
            viewLB.setVisible(true);
            view.setVisible(true);
            tablePanel.setVisible(false);

        } else {
            infoPanel.setVisible(false);
            detailsLayout.setVisible(true);
            viewLB.setVisible(false);
            view.setVisible(false);
            tablePanel.setVisible(false);
            fieldFactoryDates[0] = companyFamilyPlanStartDate.getValue();
            fieldFactoryDates[NumericConstants.ONE] = companyFamilyPlanEndDate.getValue();
            HeaderCompanyDetailsTable.setVisible(false);
            
        }
        if (String.valueOf(level.getValue()).equals(ConstantUtil.HEADER) && String.valueOf(view.getValue()).equals(ConstantUtil.HISTORY)) {
            tablePanel.setVisible(true);
        }
        if (String.valueOf(level.getValue()).equals(ConstantUtil.HEADER) && !String.valueOf(view.getValue()).equals(ConstantUtil.HISTORY)) {
            excelExport.setVisible(false);
        }

    }

    @UiHandler("view")
    public void viewchange(Property.ValueChangeEvent event) {
        excelExport.setVisible(true);
        setView(view);
        if (level.getValue() != null && view.getValue() != null && Constants.HEADER.equalsIgnoreCase(level.getValue().toString()) && Constants.HISTORY.equals(view.getValue().toString())) {
            HeaderCompanyDetailsTable.setVisible(true);
            configureHeadertable();
            infoPanel.setVisible(false);
            tablePanel.setVisible(true);

        } else {
            HeaderCompanyDetailsTable.setVisible(false);
            infoPanel.setVisible(true);
            tablePanel.setVisible(false);
        }
        if (String.valueOf(level.getValue()).equals(ConstantUtil.HEADER) && !String.valueOf(view.getValue()).equals(ConstantUtil.HISTORY)) {
            excelExport.setVisible(false);
        }

    }

    @UiHandler("cfpDesignation")
    public void cfpDesignationchange(Property.ValueChangeEvent event) {
        String caption = cfpDesignation.getItemCaption(cfpDesignation.getValue());
        cfpDesignation.setDescription(caption);
        if ("Child".equalsIgnoreCase(caption)) {
            parentCfp.setEnabled(true);
            parentCfpName.setEnabled(true);
            parentCfp.setReadOnly(true);
            parentCfpName.setReadOnly(true);
        } else {
            parentCfp.setReadOnly(false);
            parentCfp.setValue(StringUtils.EMPTY);
            parentCfp.setEnabled(false);
            parentCfpName.setReadOnly(false);
            parentCfpName.setValue(StringUtils.EMPTY);
            parentCfpName.setEnabled(false);

        }
    }

    public
            void configureHeadertable() {
        try {
            HeadertableBean = new BeanItemContainer<>(CFPCompanyDTO.class
            );
            HeaderCompanyDetailsTable.markAsDirty();

            HeaderCompanyDetailsTable.setFilterBarVisible(
                    true);
            HeaderCompanyDetailsTable.setPageLength(
                    NumericConstants.SEVEN);
            HeaderCompanyDetailsTable.setImmediate(
                    true);
            HeaderCompanyDetailsTable.setSelectable(
                    true);
            HeaderCompanyDetailsTable.setSizeFull();

            HeaderCompanyDetailsTable.setContainerDataSource(HeadertableBean);

            HeaderCompanyDetailsTable.setVisibleColumns(
                    "companyFamilyPlanId", "companyFamilyPlanNo", "companyFamilyPlanName", "cfpStatus", "companyFamilyPlanStartDate", "companyFamilyPlanEndDate", "cfptype", "cfpCategory", "cfptrade", "cfpDesignation", "parentCfp", "parentCfpName", "salesInclusion", "createdBy", "createdDate", "modifiedBy", "modifiedDate");
            HeaderCompanyDetailsTable.setColumnHeaders("CFP ID", "CFP No", "CFP Name", ConstantUtil.CFP_STATUS, ConstantUtil.CFP_START_DATE, ConstantUtil.CFP_END_DATE, "CFP Type", "CFP Category", "CFP Trade Class", "CFP Designation", "Parent CFP ID", "Parent CFP Name", "Sales Inclusion", "Created By", "Created Date", "Modified By", "Modified Date");
            HeaderCompanyDetailsTable.setWidth(
                    "100%");
            HeaderCompanyDetailsTable.setHeight(
                    "100%");
        } catch (Exception e) {

             LOGGER.error(e);

        }
    }

    public ComboBox getCfpStatus() {
        return cfpStatus;
    }

    public void setCfpStatus(ComboBox cfpStatus) {
        this.cfpStatus = cfpStatus;
    }

    public TextField getCompanyFamilyPlanId() {
        return companyFamilyPlanId;
    }

    public void setCompanyFamilyPlanId(TextField companyFamilyPlanId) {
        this.companyFamilyPlanId = companyFamilyPlanId;
    }

    public TextField getCompanyFamilyPlanNo() {
        return companyFamilyPlanNo;
    }

    public void setCompanyFamilyPlanNo(TextField companyFamilyPlanNo) {
        this.companyFamilyPlanNo = companyFamilyPlanNo;
    }

    public TextField getCompanyFamilyPlanName() {
        return companyFamilyPlanName;
    }

    public void setCompanyFamilyPlanName(TextField companyFamilyPlanName) {
        this.companyFamilyPlanName = companyFamilyPlanName;
    }

    public PopupDateField getCompanyFamilyPlanStartDate() {
        return companyFamilyPlanStartDate;
    }

    public void setCompanyFamilyPlanStartDate(PopupDateField companyFamilyPlanStartDate) {
        this.companyFamilyPlanStartDate = companyFamilyPlanStartDate;
    }

    public PopupDateField getCompanyFamilyPlanEndDate() {
        return companyFamilyPlanEndDate;
    }

    public void setCompanyFamilyPlanEndDate(PopupDateField companyFamilyPlanEndDate) {
        this.companyFamilyPlanEndDate = companyFamilyPlanEndDate;
    }

    public ComboBox getCfptype() {
        return cfptype;
    }

    public void setCfptype(ComboBox cfptype) {
        this.cfptype = cfptype;
    }

    public ComboBox getCfpCategory() {
        return cfpCategory;
    }

    public void setCfpCategory(ComboBox cfpCategory) {
        this.cfpCategory = cfpCategory;
    }

    public ComboBox getCfptrade() {
        return cfptrade;
    }

    public void setCfptrade(ComboBox cfptrade) {
        this.cfptrade = cfptrade;
    }

    public ComboBox getCfpDesignation() {
        return cfpDesignation;
    }

    public void setCfpDesignation(ComboBox cfpDesignation) {
        this.cfpDesignation = cfpDesignation;
    }

    public TextField getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(TextField createdBy) {
        this.createdBy = createdBy;
    }

    public TextField getCreateddate() {
        return createddate;
    }

    public void setCreateddate(TextField createddate) {
        this.createddate = createddate;
    }

    public TextField getModifiedBy() {
        return ModifiedBy;
    }

    public void setModifiedBy(TextField ModifiedBy) {
        this.ModifiedBy = ModifiedBy;
    }

    public TextField getModifieddate() {
        return Modifieddate;
    }

    public void setModifieddate(TextField Modifieddate) {
        this.Modifieddate = Modifieddate;
    }

    public ComboBox getSalesInclusion() {
        return salesInclusion;
    }

    public void setSalesInclusion(ComboBox salesInclusion) {
        this.salesInclusion = salesInclusion;
    }

    public OptionGroup getLevel() {
        return level;
    }

    public void setLevel(OptionGroup level) {
        this.level = level;
    }

    public OptionGroup getView() {
        return view;
    }

    public void setView(OptionGroup view) {
        this.view = view;
    }

    public void attachListeners(final AbstractField field, final String component) {
        field.setImmediate(true);
        field.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                try {
                    if (valueChange) {
                        if (component.equals(ConstantUtil.START_DATE)) {
                            if (event != null) {
                                Date contractSD = (Date) dates[0];
                                if (!((PopupDateField) field).getValue().before(contractSD) && dates[NumericConstants.ONE] == null) {
                                    ((PopupDateField) field).setDescription(com.stpl.app.contract.global.util.CommonUtils.convertDateToString(((PopupDateField) field).getValue()));
                                } else if (((PopupDateField) field).getValue().before(contractSD)) {
                                    AbstractNotificationUtils.getWarningNotification("Start Date", "Start Date cannot be before " + format.format(contractSD));
                                    detachListeners(field);
                                    ((PopupDateField) field).setValue(null);
                                    attachListeners(field, ConstantUtil.START_DATE);
                                } else if (dates[NumericConstants.ONE] != null && ((PopupDateField) field).getValue().after((Date) dates[NumericConstants.ONE])) {
                                    AbstractNotificationUtils.getWarningNotification("Start Date", "Start Date cannot be after " + format.format((Date) dates[NumericConstants.ONE]));
                                    detachListeners(field);
                                    ((PopupDateField) field).setValue(null);
                                    attachListeners(field, ConstantUtil.START_DATE);
                                }
                            }
                        }
                       
                        else if (component.equals(ConstantUtil.END_DATE) && event != null) {
                                Date contractSD = (Date) dates[0];
                                if ((Date) dates[NumericConstants.ONE] == null && !((PopupDateField) field).getValue().before(contractSD)) {
                                    ((PopupDateField) field).setDescription(com.stpl.app.contract.global.util.CommonUtils.convertDateToString(((PopupDateField) field).getValue()));
                                } else if (((PopupDateField) field).getValue().before(contractSD)) {
                                    AbstractNotificationUtils.getWarningNotification("End Date", "End Date cannot be before " + format.format(contractSD));
                                    detachListeners(field);
                                    ((PopupDateField) field).setValue(null);
                                    attachListeners(field, ConstantUtil.END_DATE);
                                } else if (dates[NumericConstants.ONE] != null && ((PopupDateField) field).getValue().after((Date) dates[NumericConstants.ONE])) {
                                    AbstractNotificationUtils.getWarningNotification("End Date", "End Date cannot be after " + format.format((Date) dates[NumericConstants.ONE]));
                                    detachListeners(field);
                                    ((PopupDateField) field).setValue(null);
                                    attachListeners(field, ConstantUtil.END_DATE);
                                }
                        }
                    }
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        });
    }

    /**
     * method for detaching listener.
     *
     * @param field
     */
    public void detachListeners(final AbstractField field) {

        List<Property.ValueChangeListener> listeners;

        listeners = (List<Property.ValueChangeListener>) field.getListeners(Property.ValueChangeEvent.class);
        for (final Property.ValueChangeListener object : listeners) {
            field.removeValueChangeListener(object);
        }
    }
    
    public String textFieldEmptyChecks() {
        List<String> textFieldName = new ArrayList<>();
        String footerMsg = " should be entered on Companies tab";
        if (companyFamilyPlanNo.getValue().isEmpty()) {
            textFieldName.add("CFP No");
        }
        if (companyFamilyPlanName.getValue().isEmpty()) {
            textFieldName.add("CFP Name");
        }
        String value = StringUtils.join(textFieldName, ",");
        return !textFieldName.isEmpty() ? value + footerMsg : StringUtils.EMPTY;
    }
}
