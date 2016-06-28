/**
 *
 */
package com.stpl.app.contract.dashboard.ui.form;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
import com.stpl.app.contract.common.util.HelperListUtil;
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
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
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
    private Map<String, String> map = new HashMap<String, String>();

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
    private final Map<Integer, Boolean> reloadMap = new HashMap<Integer, Boolean>();
    private final ContractHeaderLogic contractHL = new ContractHeaderLogic();

    final ContractHeaderLogic contractLogic = new ContractHeaderLogic();
    /**
     * The field mass.
     */
    private String fieldMass = StringUtils.EMPTY;

    /**
     * The cfpcompany list.
     */
    private final List<CFPCompanyDTO> cfpcompanyList = new ArrayList<CFPCompanyDTO>();
    /**
     * The common util.
     */
    private CommonUtil commonUtil = CommonUtil.getInstance();
    private com.stpl.app.contract.abstractsearch.util.CommonUtils commonUtils = com.stpl.app.contract.abstractsearch.util.CommonUtils.getInstance();

    private final Map<Integer, Boolean> reloadVerticalLayoutTabThreeMap = new HashMap<Integer, Boolean>();

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

    Date[] dates = new Date[2];
    Object[] fieldFactoryDates = new Object[2];

    private Map<String, List> tempDate;

    boolean valueChange = false;
    SimpleDateFormat format = new SimpleDateFormat("MM/dd/YYYY");
    final StplSecurity stplSecurity = new StplSecurity();
    String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
    private static HelperListUtil helperListUtil = HelperListUtil.getInstance();
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
    public CompaniesTab(final BeanItemContainer<CompanyMasterDTO> availableBean, final BeanItemContainer<CompanyMasterDTO> selectedBean, final BeanItemContainer<CFPCompanyDTO> cfpResultsBean, final BeanItemContainer<CFPCompanyDTO> saveContainer, final Map<String, String> companyMap, final CustomFieldGroup cfpContractBinder, final boolean isEditable, final SessionDTO sessionDTO, final CFPCompanyDTO cfpCompanyDTO, final CustomFieldGroup contractBinder) throws SystemException, PortalException, Exception {
        super();
        LOGGER.info("Entering CompaniesTab");

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
        LOGGER.info("End of CompaniesTab");

    }

    private void configureBinder() {
        LOGGER.info("Entering getBinder method");
        cfpContractBinder.bindMemberFields(this);
        cfpContractBinder.setItemDataSource(new BeanItem<CFPCompanyDTO>(cfpCompanyDTO));
        cfpContractBinder.setBuffered(true);
        companyFamilyPlanNo.setEnabled(false);
        companyFamilyPlanId.setEnabled(false);
        companyFamilyPlanName.setEnabled(false);

        createdBy.setEnabled(false);
        createddate.setEnabled(false);
        ModifiedBy.setEnabled(false);
        Modifieddate.setEnabled(false);
        LOGGER.info("End of getBinder method");

    }

    private void init() throws SystemException, Exception {
        final Map<String, AppPermission> contractDashboard = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.CONTRACT_DASHBOARD + Constants.COMMA + "Companies", false);
        final Map<String, AppPermission> funContractHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.CONTRACT_DASHBOARD + Constants.COMMA + "Companies");
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
                                try {
                                    removeList = cfpLogic.getLazySelectedCompaniesDeatils(0,count,true, null,null,false,TempEditCompanyContainer.getRecord(), Boolean.TRUE);
                                } catch (PortalException ex) {
                                    LOGGER.error(ex);
                                } catch (SystemException ex) {
                                    LOGGER.error(ex);
                                }
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
                                    companyDetailsTable.setCurrentPage(1);
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
     //   cfptype.setEnabled(false);
       // cfptrade.setEnabled(false);
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
                    companyDetailsTable.setCurrentPage(1);
                }

            }
        });
    }

    public void loadDates(Date startDate, Date endDate) {
        dates[0] = startDate;
        dates[1] = endDate;
        companyFamilyPlanStartDate.validate();
        companyFamilyPlanEndDate.validate();
    }

    private void configureFields() throws SystemException, Exception {

        LOGGER.info("Entering configureFields method");

        infoPanel.setVisible(true);
        tablePanel.setVisible(false);
        detailsLayout.setVisible(false);
        massCheck.addItem("Enable");
        massCheck.addItem("Disable");
        massCheck.setValue("Disable");
        massCheck.setImmediate(true);
        massCheck.setMultiSelect(false);

        massField.addItem(Constants.SELECT_ONE);
        massField.addItem("CFP Start Date");
        massField.addItem("CFP End Date");
        massField.addItem("CFP Status");
        massField.setNullSelectionAllowed(true);
        massField.setNullSelectionItemId(Constants.SELECT_ONE);
        massField.setImmediate(true);
        massField.select(Constants.SELECT_ONE);
        massField.markAsDirty();
        massField.setPageLength(7);
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
            }
        });

        massCheck.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            /**
             * Method used to mass check and its listener
             */
            public void valueChange(final ValueChangeEvent event) {
                if ("Disable".equals(massCheck.getValue())) {
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
        map.put("CFP Start Date", Constants.COMPANY_FAMILY_PLAN_START_DATE);
        map.put("CFP End Date", Constants.CFP_END_DATE);
        map.put("CFP Status", Constants.CFP_STATUS);
        LOGGER.info("End of configureFields method");
    }

    /**
     * Method used for mass update.
     *
     * @return the button
     */
    public Button addBtnPopulate() {
        try {
            LOGGER.info("Entering addBtnPopulate method");
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

                    LOGGER.info(" buttonClick ClickEvent event name=" + event.getButton().getCaption());
                    if (massField.getValue() != null) {
                        try {
                            String value = StringUtils.EMPTY;
                            final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                            fieldMass = map.get(massField.getValue()).toString();
                            if (Constants.COMPANY_FAMILY_PLAN_START_DATE.equals(fieldMass)) {
                                if (companyFamilyPlanStartDate.getValue() == null) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Please provide CFP Start date and try again.");
                                    return;
                                } else if (massDate.getValue() == null) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Please provide date and try again.");
                                } else if (massDate.getValue().before(companyFamilyPlanStartDate.getValue())) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "CFP Start date cannot be before " + new SimpleDateFormat("MM/dd/YYYY").format(companyFamilyPlanStartDate.getValue()));
                                    return;
                                } else if (companyFamilyPlanEndDate.getValue() != null && massDate.getValue().after(companyFamilyPlanEndDate.getValue())) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Start date cannot be after " + new SimpleDateFormat("MM/dd/YYYY").format(companyFamilyPlanEndDate.getValue()));
                                    return;
                                } else {
                                    value = fmt.format(massDate.getValue());
                                }
                            } else if (Constants.CFP_END_DATE.equals(fieldMass)) {
                                if (companyFamilyPlanStartDate.getValue() == null) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Please provide CFP Start date and try again.");
                                    return;
                                } else if (massDate.getValue() == null) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Please provide date and try again.");
                                    return;
                                } else if (companyFamilyPlanEndDate.getValue() != null && massDate.getValue().after(companyFamilyPlanEndDate.getValue())) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "CFP end date cannot be after " + new SimpleDateFormat("MM/dd/YYYY").format(companyFamilyPlanEndDate.getValue()));
                                    return;
                                } else if (massDate.getValue().before(companyFamilyPlanStartDate.getValue())) {
                                    AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "End date cannot be before " + new SimpleDateFormat("MM/dd/YYYY").format(companyFamilyPlanStartDate.getValue()));
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
                            loadTempCfp(false);
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
            LOGGER.info("End of addBtnPopulate method");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());;
        }
        return btnPopulate;
    }

    /**
     * Method used for add the all btn populate.
     *
     * @return the button
     */
    public Button addAllBtnPopulate() {
        LOGGER.info("Entering addAllBtnPopulate method");
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
                LOGGER.info("buttonClick(ClickEvent event) name=" + event.getButton().getCaption());
                if (massField.getValue() != null) {
                    try {
                        String value = StringUtils.EMPTY;
                        final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                        fieldMass = map.get(massField.getValue()).toString();
                        if (Constants.COMPANY_FAMILY_PLAN_START_DATE.equals(fieldMass)) {
                            if (companyFamilyPlanStartDate.getValue() == null) {
                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Please provide CFP Start date and try again.");
                                return;
                            } else if (massDate.getValue() == null) {
                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Please provide date and try again.");
                            } else if (massDate.getValue().before(companyFamilyPlanStartDate.getValue())) {
                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "CFP Start date cannot be before " + new SimpleDateFormat("MM/dd/YYYY").format(companyFamilyPlanStartDate.getValue()));
                                return;
                            } else if (companyFamilyPlanEndDate.getValue() != null && massDate.getValue().after(companyFamilyPlanEndDate.getValue())) {
                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Start date cannot be after " + new SimpleDateFormat("MM/dd/YYYY").format(companyFamilyPlanEndDate.getValue()));
                                return;
                            } else {
                                value = fmt.format(massDate.getValue());
                            }
                        } else if (Constants.CFP_END_DATE.equals(fieldMass)) {
                            if (companyFamilyPlanStartDate.getValue() == null) {
                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Please provide CFP Start date and try again.");
                                return;
                            } else if (massDate.getValue() == null) {
                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Please provide date and try again.");
                            } else if (companyFamilyPlanEndDate.getValue() != null && massDate.getValue().after(companyFamilyPlanEndDate.getValue())) {
                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "CFP end date cannot be after " + new SimpleDateFormat("MM/dd/YYYY").format(companyFamilyPlanEndDate.getValue()));
                                return;
                            } else if (massDate.getValue().before(companyFamilyPlanStartDate.getValue())) {
                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "End date cannot be before " + new SimpleDateFormat("MM/dd/YYYY").format(companyFamilyPlanStartDate.getValue()));
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
                        loadTempCfp(false);
                    } catch (SystemException ex) {
                        final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                        LOGGER.error(errorMsg);
                        AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                    } catch (PortalException ex) {
                        final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                        LOGGER.error(errorMsg);
                    } catch (Exception ex) {
                        LOGGER.error(ex.getMessage());
                    }
                } else {
                    AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, Constants.POPULATE_MSG);
                }
            }
        });
        LOGGER.info("End of addAllBtnPopulate method");
        return btnAllPopulate;
    }

    public void loadTempCfp(boolean flag) {
        tempLazyContainer = new LazyBeanItemContainer(CFPCompanyDTO.class, new TempCompanyContainer(companyDetailsTable, saveContainer, sessionDTO), lazyLoadCriteria);
        companyDetailsTable.setCurrentPage(companyDetailsTable.getCurrentPage());
    }

    public void btnExportLogic() throws SystemException, PortalException, FileNotFoundException {
            excelExport.addClickListener(new ClickListener() {
            /**
             * Invoked when a button is clicked
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                try {
                    LOGGER.info("Entering btnExportLogic method");
                    LOGGER.info("createWorkSheet()");
                    createWorkSheet("Companies_Details",companyDetailsTable,0);
                    LOGGER.info("End of btnExportLogic method");
                } catch (SystemException ex) {
                    java.util.logging.Logger.getLogger(CompaniesTab.class.getName()).log(Level.SEVERE, null, ex);
                } catch (PortalException ex) {
                    java.util.logging.Logger.getLogger(CompaniesTab.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
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
    public void createWorkSheet(String moduleName, ExtCustomTable resultTable, int count) throws SystemException, PortalException, Exception {
        String[] header = resultTable.getColumnHeaders();
        header = (String[]) ArrayUtils.removeElement(header, StringUtils.EMPTY);
        header = (String[]) ArrayUtils.removeElement(header, ConstantUtil.BLANK_SPACE);
        CFPSearchLogic.saveToTempCFP(saveContainer.getItemIds());
        cfpCompanyDTO = dashBoardLogic.getCfpDetails((Integer) sessionDTO.getContractSystemId(),
                (Integer) sessionDTO.getCfpSystemId());
        final long recordCount = cfpCompanyDTO.getCfpcompanyList().size();
        ExcelExportforBB.createWorkSheet(header, recordCount, this, UI.getCurrent(), moduleName.toUpperCase());
    }

    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws SystemException, PortalException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {

        LOGGER.info("Entering createWorkSheetContent method");
        if (String.valueOf(level.getValue()).equals(Constants.DETAILS)) {

            final List<CFPCompanyDTO> exportCompany = cfpCompanyDTO.getCfpcompanyList();

            Object[] columns = companyDetailsTable.getVisibleColumns();
            columns = ArrayUtils.removeElement(columns, Constants.CHECK_BOX);
            ExcelExportforBB.createFileContent(columns, exportCompany, printWriter);
        }

        LOGGER.info("End of createWorkSheetContent method");
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
        LOGGER.info("Entering sendConvertedFileToUser method");
        final TemporaryFileDownloadResource resource = new TemporaryFileDownloadResource(app, exportFileName, ExcelExportUtil.EXCEL_MIME_TYPE, fileToExport);
        Page.getCurrent().open(resource, ExcelExportUtil.WINDOW_NAME, true);
        LOGGER.info("End of sendConvertedFileToUser method");
        return true;
    }

    /**
     * Gets the status select.
     *
     * @param select the select
     * @return the status select
     */
    public ComboBox getStatusSelect(final ComboBox select) {
        LOGGER.info("Entering getStatusSelect method");
        select.addItem(Constants.SELECT_ONE);
        select.addItem(Constants.ACTIVE);
        select.addItem(Constants.IN_ACTIVE);
        LOGGER.info("End of getStatusSelect method");
        return select;
    }

    /**
     * Adds the item details table.
     *
     * @return the vertical layout
     */
    public void addItemDetailsTable() {
        try {
            LOGGER.info("Entering addItemDetailsTable method");
            Object[] objAvail = ContractUtils.CFP_ITEM_DETAILS_COL;
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
                    5);
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
            tempDate = new HashMap<String, List>();

            companyDetailsTable.setTableFieldFactory(
                    new CFPTableGenerator(saveContainer, fieldFactoryDates, tempDate));
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
                                        cfpLogic.populateToTempCFP("CheckBox", 1, Boolean.TRUE);
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
                                LOGGER.error(ex.getMessage());
                            }
                        }
                    }
            );
            HorizontalLayout tempLayout = ResponsiveUtils.getResponsiveControls(companyDetailsTable.createControls());

            companyDetailsTable.setColumnCollapsingAllowed(
                    true);
            ContractUtils.getCustomizedComboBox(
                    (ComboBox) ((CssLayout) tempLayout.getComponent(0)).getComponent(1));
            resultsTableLayout.addComponent(tempLayout);
        } catch (Exception ex) {
             LOGGER.error(ex.getMessage());
        }
    }

    public void setDefaultTableWidth(final CustomePagedFilterTable table) {

        try {
            table.setColumnCollapsingAllowed(true);
            int browserWidth = Page.getCurrent().getBrowserWindowWidth();
            if (browserWidth > 1516) {
                lazyLoadCriteria.setCustomDirty(false);
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getSixColumns(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
                lazyLoadCriteria.setCustomDirty(true);
            } else if (browserWidth < 1516 && browserWidth > 978) {
                lazyLoadCriteria.setCustomDirty(false);
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getCollapsibleColumnsDefault1515Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
                lazyLoadCriteria.setCustomDirty(true);
            } else if (browserWidth < 978 && browserWidth > 600) {
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
            } else if (browserWidth < 600 && browserWidth > 480) {
                lazyLoadCriteria.setCustomDirty(false);
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getCollapsibleColumns600Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
                lazyLoadCriteria.setCustomDirty(true);
            } else if (browserWidth < 480 && browserWidth > 320) {
                lazyLoadCriteria.setCustomDirty(false);
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getCollapsibleColumns480Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
                lazyLoadCriteria.setCustomDirty(true);
            } else if (browserWidth < 320) {
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
            LOGGER.error(ex.getMessage());
            
        }
    }

    private static Object[] getCollapsibleColumns480Px(CustomePagedFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class
        );
        List<Object> list = new ArrayList<Object>(Arrays.asList(propertyIds));

        list.remove(propertyIds[1]);
        list.remove(propertyIds[2]);
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
        List<Object> list = new ArrayList<Object>(Arrays.asList(propertyIds));

        list.remove(propertyIds[1]);
        list.remove(propertyIds[2]);
        list.remove(propertyIds[3]);
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
        List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));

        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    private static String[] getCollapsibleColumnsDefault1515Px(CustomePagedFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class
        );
        List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));

        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        list.remove(propertyIds[2]);
        list.remove(propertyIds[3]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    private static Object[] getSixColumns(CustomePagedFilterTable table) {
        table.setColumnCollapsingAllowed(true);
        table.setImmediate(true);
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class
        );
        List<Object> list = new ArrayList<Object>(Arrays.asList(visibleColumns));
        for (int i = 0;
                i < 6; i++) {
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
        reloadMap.put(1516, true);
        reloadMap.put(978, true);
        reloadMap.put(600, true);
        reloadMap.put(480, true);
        reloadMap.put(320, true);
        reloadMap.put(0, true);

        try {

            table.setColumnCollapsingAllowed(true);

            Page.getCurrent().addBrowserWindowResizeListener(new Page.BrowserWindowResizeListener() {
                @Override
                public void browserWindowResized(final Page.BrowserWindowResizeEvent event) {

                    int browserWidth = Page.getCurrent().getBrowserWindowWidth();
                    if (browserWidth > 1516 && reloadMap.get(1516)) {
                        lazyLoadCriteria.setCustomDirty(false);

                        for (Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (Object propertyId : getSixColumns(table)) {
                            table.setColumnCollapsed(propertyId, browserWidth > 1516);
                        }
                        reloadMap.put(1516, false);
                        reloadMap.put(978, true);
                        reloadMap.put(600, true);
                        reloadMap.put(480, true);
                        reloadMap.put(320, true);
                        reloadMap.put(0, true);
                        lazyLoadCriteria.setCustomDirty(true);
                    } else if (browserWidth < 1516 && browserWidth > 978 && reloadMap.get(978)) {
                        lazyLoadCriteria.setCustomDirty(false);
                        for (Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (Object propertyId : getCollapsibleColumnsDefault1515Px(table)) {
                            table.setColumnCollapsed(propertyId, browserWidth < 1516);
                        }

                        reloadMap.put(1516, true);
                        reloadMap.put(978, false);
                        reloadMap.put(600, true);
                        reloadMap.put(480, true);
                        reloadMap.put(320, true);
                        reloadMap.put(0, true);
                        lazyLoadCriteria.setCustomDirty(true);
                    } else if (browserWidth < 978 && browserWidth > 600 && reloadMap.get(600)) {
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

                        reloadMap.put(1516, true);
                        reloadMap.put(978, true);
                        reloadMap.put(600, false);
                        reloadMap.put(480, true);
                        reloadMap.put(320, true);
                        reloadMap.put(0, true);
                        lazyLoadCriteria.setCustomDirty(true);
                    } else if (browserWidth < 600 && browserWidth > 480 && reloadMap.get(480)) {
                        lazyLoadCriteria.setCustomDirty(false);       
                        for (Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (Object propertyId : getCollapsibleColumns600Px(table)) {
                            table.setColumnCollapsed(propertyId, true);
                        }
                        reloadMap.put(1516, true);
                        reloadMap.put(978, true);
                        reloadMap.put(600, true);
                        reloadMap.put(480, false);
                        reloadMap.put(320, true);
                        reloadMap.put(0, true);
                        lazyLoadCriteria.setCustomDirty(true);        
                    } else if (browserWidth < 480 && browserWidth > 320 && reloadMap.get(320)) {
                        lazyLoadCriteria.setCustomDirty(false);
                        for (Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (Object propertyId : getCollapsibleColumns480Px(table)) {
                            table.setColumnCollapsed(propertyId, browserWidth < 480);
                        }
                        reloadMap.put(1516, true);
                        reloadMap.put(978, true);
                        reloadMap.put(600, true);
                        reloadMap.put(480, true);
                        reloadMap.put(320, false);
                        reloadMap.put(0, true);
                        lazyLoadCriteria.setCustomDirty(true);
                    } else if (browserWidth < 320 && reloadMap.get(0)) {
                        lazyLoadCriteria.setCustomDirty(false);
                        for (Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (Object propertyId : getCollapsibleColumns480Px(table)) {
                            table.setColumnCollapsed(propertyId, browserWidth < 320);
                        }
                        reloadMap.put(1516, true);
                        reloadMap.put(978, true);
                        reloadMap.put(600, true);
                        reloadMap.put(480, true);
                        reloadMap.put(320, true);
                        reloadMap.put(0, false);
                        lazyLoadCriteria.setCustomDirty(true);
                    }

                }
            });
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
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

        LOGGER.info("Entering addItemDetailsTable method");
        viewCompanyDetailsTable.setVisible(true);
        viewCompanyDetailsTable.markAsDirty();

        viewCompanyDetailsTable.setPageLength(7);
        viewCompanyDetailsTable.setImmediate(true);
        viewCompanyDetailsTable.setSelectable(true);
        viewCompanyDetailsTable.setSizeFull();
        viewCompanyDetailsTable.setContainerDataSource(cfpResultsBean);
        Object[] objAvail = ContractUtils.CFP_ITEM_DETAILS_VIEW_COL;
        TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, objAvail, fieldContract, Constants.ViewMode);
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
            }
        });
        addResponsiveVerticalTabThreeLayout();
        ResponsiveUtils.addResponsiveTableCollapse(viewCompanyDetailsTable);
        LOGGER.info("End of addItemDetailsTable method");
    }

    public void loadTempCfpOnView(String value) {
        tempViewCompanyContainer = new TempViewCompanyContainer(sessionDTO);
        tempViewCompanyContainer.setRecord(value);
        tempLazyContainer
                = new LazyBeanItemContainer(CFPCompanyDTO.class, tempViewCompanyContainer, new LazyLoadCriteria());
        viewCompanyDetailsTable.setContainerDataSource(tempLazyContainer);
        Object[] objAvail = ContractUtils.CFP_ITEM_DETAILS_VIEW_COL;

        TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, objAvail, fieldContract, Constants.ViewMode);

        if (tableResultCustom.getObjResult().length > 0) {
            viewCompanyDetailsTable.setVisibleColumns(tableResultCustom.getObjResult());
            viewCompanyDetailsTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
        } else {
            resultPanel.setVisible(false);
        }
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

        Page.getCurrent().addBrowserWindowResizeListener(new Page.BrowserWindowResizeListener() {
            @Override
            public void browserWindowResized(final Page.BrowserWindowResizeEvent event) {

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
                    getCollapsibleColumnsDefault(viewCompanyDetailsTable);
                } else if (browserWidth < 1516 && browserWidth > 1300 && reloadVerticalLayoutTabThreeMap.get(1300)) {
                    reloadVerticalLayoutTabThreeMap.put(1516, true);
                    reloadVerticalLayoutTabThreeMap.put(1300, false);
                    reloadVerticalLayoutTabThreeMap.put(1024, true);
                    reloadVerticalLayoutTabThreeMap.put(978, true);
                    reloadVerticalLayoutTabThreeMap.put(800, true);
                    reloadVerticalLayoutTabThreeMap.put(480, true);
                    reloadVerticalLayoutTabThreeMap.put(320, true);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                    getCollapsibleColumnsDefault(viewCompanyDetailsTable);
                } else if (browserWidth < 1300 && browserWidth > 1024 && reloadVerticalLayoutTabThreeMap.get(1024)) {
                    reloadVerticalLayoutTabThreeMap.put(1516, true);
                    reloadVerticalLayoutTabThreeMap.put(1300, true);
                    reloadVerticalLayoutTabThreeMap.put(1024, false);
                    reloadVerticalLayoutTabThreeMap.put(978, true);
                    reloadVerticalLayoutTabThreeMap.put(800, true);
                    reloadVerticalLayoutTabThreeMap.put(480, true);
                    reloadVerticalLayoutTabThreeMap.put(320, true);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                    getCollapsibleColumns978Px(viewCompanyDetailsTable);
                } else if (browserWidth < 1024 && browserWidth > 978 && reloadVerticalLayoutTabThreeMap.get(978)) {
                    reloadVerticalLayoutTabThreeMap.put(1516, true);
                    reloadVerticalLayoutTabThreeMap.put(1300, true);
                    reloadVerticalLayoutTabThreeMap.put(1024, true);
                    reloadVerticalLayoutTabThreeMap.put(978, false);
                    reloadVerticalLayoutTabThreeMap.put(800, true);
                    reloadVerticalLayoutTabThreeMap.put(480, true);
                    reloadVerticalLayoutTabThreeMap.put(320, true);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                    getCollapsibleColumns978Px(viewCompanyDetailsTable);
                } else if (browserWidth < 978 && browserWidth > 800 && reloadVerticalLayoutTabThreeMap.get(800)) {
                    reloadVerticalLayoutTabThreeMap.put(1516, true);
                    reloadVerticalLayoutTabThreeMap.put(1300, true);
                    reloadVerticalLayoutTabThreeMap.put(1024, true);
                    reloadVerticalLayoutTabThreeMap.put(978, true);
                    reloadVerticalLayoutTabThreeMap.put(800, false);
                    reloadVerticalLayoutTabThreeMap.put(480, true);
                    reloadVerticalLayoutTabThreeMap.put(320, true);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                    if (viewCompanyDetailsTable.getItemIds().isEmpty()) {
                        getCollapsibleColumns978Px(viewCompanyDetailsTable);
                    } else {
                        getCollapsibleColumnsTwoColumns(viewCompanyDetailsTable);
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
                    if (viewCompanyDetailsTable.getItemIds().isEmpty()) {
                        getCollapsibleColumns978Px(viewCompanyDetailsTable);
                    } else {
                        getCollapsibleColumnsTwoColumns(viewCompanyDetailsTable);
                    }
                } else if (browserWidth < 480 && browserWidth > 320 && reloadVerticalLayoutTabThreeMap.get(320)) {
                    reloadVerticalLayoutTabThreeMap.put(1516, true);
                    reloadVerticalLayoutTabThreeMap.put(1300, true);
                    reloadVerticalLayoutTabThreeMap.put(1024, true);
                    reloadVerticalLayoutTabThreeMap.put(978, true);
                    reloadVerticalLayoutTabThreeMap.put(800, true);
                    reloadVerticalLayoutTabThreeMap.put(480, true);
                    reloadVerticalLayoutTabThreeMap.put(320, false);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                    getCollapsibleColumnsTwoColumns(viewCompanyDetailsTable);
                } else if (browserWidth < 320 && reloadVerticalLayoutTabThreeMap.get(0)) {
                    reloadVerticalLayoutTabThreeMap.put(1516, true);
                    reloadVerticalLayoutTabThreeMap.put(1300, true);
                    reloadVerticalLayoutTabThreeMap.put(1024, true);
                    reloadVerticalLayoutTabThreeMap.put(978, true);
                    reloadVerticalLayoutTabThreeMap.put(800, true);
                    reloadVerticalLayoutTabThreeMap.put(480, true);
                    reloadVerticalLayoutTabThreeMap.put(320, true);
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
        List<Object> list = new ArrayList<Object>(Arrays.asList(propertyIds));

        list.remove(propertyIds[1]);
        list.remove(propertyIds[2]);
        list.remove(propertyIds[3]);
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
        List<Object> list = new ArrayList<Object>(Arrays.asList(visibleColumns));
        for (int i = 0, j = 6;
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
        List<Object> list = new ArrayList<Object>(Arrays.asList(visibleColumns));
        for (int i = 0, j = 2;
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
        List<Object> list = new ArrayList<Object>(Arrays.asList(visibleColumns));
        for (int i = 0, j = 1;
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
            if (table.isColumnCollapsed(propertyId) == Page.getCurrent().getBrowserWindowWidth() < 800) {
                result = false;
            }
        }
        return result;
    }

    private void addResponsiveGrid(Map<String, AppPermission> contractDashboard) {
        final ContractHeaderLogic contractLogic = new ContractHeaderLogic();
        LOGGER.info("Entering configurePermission");
        try {

            List<Object> resultList = contractLogic.getFieldsForSecurity(UISecurityUtil.CONTRACT_DASHBOARD, "Companies");
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
            attachListeners(companyFamilyPlanStartDate, "START_DATE");

            companyFamilyPlanEndDate.setImmediate(true);
            companyFamilyPlanEndDate.setValidationVisible(true);
            companyFamilyPlanEndDate.setDateFormat(Constants.MM_DD_YYYY);
            companyFamilyPlanEndDate.setDescription(Constants.DATE);
            companyFamilyPlanEndDate.addValidator(new DateValidatorContractCFP("EndDate"));
            attachListeners(companyFamilyPlanEndDate, "END_DATE");

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
        public void validate(final Object value) throws Validator.InvalidValueException {
            LOGGER.info("Entering validate method");

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
                    } else if (dates[1] != null && companyFamilyPlanStartDate.getValue().after((Date) dates[1])) {
                        throw new Validator.InvalidValueException("Select CFP Start date before " + format.format(dates[1]));
                    }
                }
                if ("EndDate".equals(field) && companyFamilyPlanEndDate.getValue() != null) {
                    if (dates[1] != null && companyFamilyPlanEndDate.getValue().after((Date) dates[1])) {
                        throw new Validator.InvalidValueException("Select CFP End date before " + format.format(dates[1]));
                    }
                }
            }
            LOGGER.info("End of validate method");
        }

        /**
         * Method return boolean value validate isValidValue.
         *
         * @param value the value
         * @return true, if checks if is valid value
         */
        @Override
        protected boolean isValidValue(final Object value) {
            LOGGER.info("Entering isValidValue method");

            if (companyFamilyPlanStartDate.getValue() != null && companyFamilyPlanEndDate.getValue() != null) {
                return companyFamilyPlanStartDate.getValue().compareTo(companyFamilyPlanEndDate.getValue()) <= 0;
            }

            LOGGER.info("End of isValidValue method");
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
    public void levelchange(Property.ValueChangeEvent event) throws Exception {
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
            fieldFactoryDates[1] = companyFamilyPlanEndDate.getValue();
            HeaderCompanyDetailsTable.setVisible(false);
            
        }
        if (String.valueOf(level.getValue()).equals("Header") && String.valueOf(view.getValue()).equals("History")) {
            tablePanel.setVisible(true);
        }
        if (String.valueOf(level.getValue()).equals("Header") && !String.valueOf(view.getValue()).equals("History")) {
            excelExport.setVisible(false);
        }

    }

    @UiHandler("view")
    public void viewchange(Property.ValueChangeEvent event) throws Exception {
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
        if (String.valueOf(level.getValue()).equals("Header") && !String.valueOf(view.getValue()).equals("History")) {
            excelExport.setVisible(false);
        }

    }

    @UiHandler("cfpDesignation")
    public void cfpDesignationchange(Property.ValueChangeEvent event) throws Exception {
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
            HeadertableBean = new BeanItemContainer<CFPCompanyDTO>(CFPCompanyDTO.class
            );
            HeaderCompanyDetailsTable.markAsDirty();

            HeaderCompanyDetailsTable.setFilterBarVisible(
                    true);
            HeaderCompanyDetailsTable.setPageLength(
                    7);
            HeaderCompanyDetailsTable.setImmediate(
                    true);
            HeaderCompanyDetailsTable.setSelectable(
                    true);
            HeaderCompanyDetailsTable.setSizeFull();

            HeaderCompanyDetailsTable.setContainerDataSource(HeadertableBean);

            HeaderCompanyDetailsTable.setVisibleColumns(
                    "companyFamilyPlanId", "companyFamilyPlanNo", "companyFamilyPlanName", "cfpStatus", "companyFamilyPlanStartDate", "companyFamilyPlanEndDate", "cfptype", "cfpCategory", "cfptrade", "cfpDesignation", "parentCfp", "parentCfpName", "salesInclusion", "createdBy", "createdDate", "modifiedBy", "modifiedDate");
            HeaderCompanyDetailsTable.setColumnHeaders(
                    "CFP ID", "CFP No", "CFP Name", "CFP Status", "CFP Start Date", "CFP End Date", "CFP Type", "CFP Category", "CFP Trade Class", "CFP Designation", "Parent CFP ID", "Parent CFP Name", "Sales Inclusion", "Created By", "Created Date", "Modified By", "Modified Date");
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

    /*  public CustomTextField getParentCfp() {
     return parentCfp;
     }

     public void setParentCfp(CustomTextField parentCfp) {
     this.parentCfp = parentCfp;
     }

     public CustomTextField getParentCfpName() {
     return parentCfpName;
     }

     public void setParentCfpName(CustomTextField parentCfpName) {
     this.parentCfpName = parentCfpName;
     } */
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
                        if (component.equals("START_DATE")) {
                            if (event != null) {
                                Date contractSD = (Date) dates[0];
                                if (!((PopupDateField) field).getValue().before(contractSD) && dates[1] == null) {
                                    ((PopupDateField) field).setDescription(com.stpl.app.contract.global.util.CommonUtils.convertDateToString(((PopupDateField) field).getValue()));
                                } else if (((PopupDateField) field).getValue().before(contractSD)) {
                                    AbstractNotificationUtils.getWarningNotification("Start Date", "Start Date cannot be before " + format.format(contractSD));
                                    detachListeners(field);
                                    ((PopupDateField) field).setValue(null);
                                    attachListeners(field, "START_DATE");
                                } else if (dates[1] != null && ((PopupDateField) field).getValue().after((Date) dates[1])) {
                                    AbstractNotificationUtils.getWarningNotification("Start Date", "Start Date cannot be after " + format.format((Date) dates[1]));
                                    detachListeners(field);
                                    ((PopupDateField) field).setValue(null);
                                    attachListeners(field, "START_DATE");
                                }
                            }
                        }
                       
                        else if (component.equals("END_DATE")) {
                            if (event != null) {
                                Date contractSD = (Date) dates[0];
                                if ((Date) dates[1] == null && !((PopupDateField) field).getValue().before(contractSD)) {
                                    ((PopupDateField) field).setDescription(com.stpl.app.contract.global.util.CommonUtils.convertDateToString(((PopupDateField) field).getValue()));
                                } else if (((PopupDateField) field).getValue().before(contractSD)) {
                                    AbstractNotificationUtils.getWarningNotification("End Date", "End Date cannot be before " + format.format(contractSD));
                                    detachListeners(field);
                                    ((PopupDateField) field).setValue(null);
                                    attachListeners(field, "END_DATE");
                                } else if (dates[1] != null && ((PopupDateField) field).getValue().after((Date) dates[1])) {
                                    AbstractNotificationUtils.getWarningNotification("End Date", "End Date cannot be after " + format.format((Date) dates[1]));
                                    detachListeners(field);
                                    ((PopupDateField) field).setValue(null);
                                    attachListeners(field, "END_DATE");
                                }
                            }
                        }
                    }
                } catch (Exception ex) {
                    LOGGER.error(ex.getMessage());
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
}
