
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.ui.form;

import org.asi.container.ExtTreeContainer;
import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.common.CommonUtil;
import com.stpl.app.gcm.copycontract.dto.ContractSelectionDTO;
import com.stpl.app.gcm.discount.ui.form.CfpLookUp;
import com.stpl.app.gcm.discount.ui.form.IfpLookUp;
import com.stpl.app.gcm.discount.ui.form.PsLookUp;
import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
//import static com.stpl.app.gcm.itemmanagement.add.form.Summary.LOGGER;
import static com.stpl.app.gcm.itemmanagement.itemabstract.form.AbstractContractSearch.LOGGER;
import com.stpl.app.gcm.itemmanagement.itemabstract.logic.AbstractLogic;
import com.stpl.app.gcm.itemmanagement.itemabstract.queryutils.ItemQueries;
import com.stpl.app.gcm.promotetptocontract.form.TPContractHolderLookUp;
import com.stpl.app.gcm.security.StplSecurity;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.tp.dto.ComponentInformationDTO;
import com.stpl.app.gcm.tp.dto.ContractResultDTO;
import com.stpl.app.gcm.tp.dto.IdDescriptionDTO;
import com.stpl.app.gcm.tp.logic.CommmonLogic;
import com.stpl.app.gcm.tp.logic.CompanySearchLogic;
import com.stpl.app.gcm.tp.logic.ContractSelectionLogic;
import com.stpl.app.gcm.tp.tablelogic.CompanyComponentTableLogic;
import com.stpl.app.gcm.tp.tablelogic.CurrentContractTableLogic;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.CommonUtils;
import com.stpl.app.gcm.util.Constants;
import static com.stpl.app.gcm.util.Constants.ID;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.ADD_TRADING_PARTNER;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.COMPANY_FAMILY_PLAN;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.DISABLE;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.ENABLE;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.EXCEL_IMAGE_PATH;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.ITEM_FAMILY_PLAN;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.NO;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.PRICE_SCHEDULE;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.PROJECTION_DETAILS_TRANSFER;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.REBATE_SCHEDULE;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.SELECT_ONE;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.TAB_CURRENT_CONTRACT;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.TAB_TRANSFER_CONTRACT;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.TRADING_PARTNER_REMOVE;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.TRADING_PARTNER_UPDATE;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.TRANSFER_TRADING_PARTNER;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.YES;
import static com.stpl.app.gcm.util.Constants.NAME;
import static com.stpl.app.gcm.util.Constants.NUMBER;
import static com.stpl.app.gcm.util.Converters.formatDate;
import com.stpl.app.gcm.util.CustomerFilterGenerator;
import com.stpl.app.gcm.util.UiUtils;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customcombobox.CustomComboBox;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author lokeshwari
 */
public class UpdatedContractSelection extends VerticalLayout {

    @UiField("contractNo")
    private TextField contractNo;
    @UiField("contractName")
    private TextField contractName;
    @UiField("contractHolder")
    private CustomTextField contractHolder;
    @UiField("rebateScheduleId")
    private TextField rebateScheduleId;
    @UiField("rebateScheduleName")
    private TextField rebateScheduleName;
    @UiField("rebateScheduleNo")
    private TextField rebateScheduleNo;
    @UiField("rebateScheduleAlias")
    private TextField rebateScheduleAlias;

    @UiField("marketType")
    public ComboBox marketType;
    @UiField("rebateScheduleType")
    public ComboBox rebateScheduleType;
    @UiField("rarCategory")
    public ComboBox rarCategory;
    @UiField("rebateScheduleCategory")
    public ComboBox rebateScheduleCategory;
    @UiField("rebateProgramType")
    public ComboBox rebateProgramType;
    @UiField("fieldDdlb")
    public ComboBox fieldDdlb;

    @UiField("massUpdateRadio")
    public OptionGroup massUpdateEnableDisable;
    @UiField("valueDdlb")
    private ComboBox valueDdlb;
    @UiField("valueTxt")
    private TextField valueTxt;
    @UiField("startPeriod")
    private PopupDateField startPeriod;
    @UiField("endPeriod")
    private PopupDateField endPeriod;
    @UiField("populate")
    private Button populateBtn;

    @UiField("tradingPartnerPanel")
    private Panel tradingPartnerPanel;
    @UiField("transferSalesProjectionLayout")
    private HorizontalLayout transferSalesProjectionOptionLayout;
    @UiField("transferSalesProjection")
    private OptionGroup transferSalesProjectionOption;
    @UiField("currentTradingPartnerTableLayout")
    public VerticalLayout currentTradingPartnerTableLayout;

    @UiField("componentSelection")
    public ComboBox componentSelection;
    @UiField("cNumber")
    private TextField cNumber;
    @UiField("status")
    private TextField status;
    @UiField("rebateFrequency")
    private TextField rebateFrequency;
    @UiField("startDate")
    private TextField startDate;
    @UiField("cId")
    private TextField cId;
    @UiField("cName")
    private TextField cName;
    @UiField("type")
    private TextField type;
    @UiField("basis")
    private TextField basis;
    @UiField("endDate")
    private TextField endDate;

    @UiField("cNumberLabel")
    private Label cNumberLabel;
    @UiField("statusLabel")
    private Label statusLabel;
    @UiField("rebateFrequencyLabel")
    private Label rebateFrequencyLabel;
    @UiField("startDateLabel")
    private Label startDateLabel;
    @UiField("cIdLabel")
    private Label cIdLabel;
    @UiField("cNameLabel")
    private Label cNameLabel;
    @UiField("typeLabel")
    private Label typeLabel;
    @UiField("basisLabel")
    private Label basisLabel;
    @UiField("endDateLabel")
    private Label endDateLabel;

    // Components related to RS Component Ddlb
    @UiField("rsTypeLabel")
    private Label rsTypeLabel;
    @UiField("rsType")
    private TextField rsType;

    @UiField("rsProgramTypeLabel")
    private Label rsProgramTypeLabel;
    @UiField("rsProgramType")
    private TextField rsProgramType;

    @UiField("rsCategoryLabel")
    private Label rsCategoryLabel;
    @UiField("rsCategory")
    private TextField rsCategory;

    @UiField("paymentFrequencyLabel")
    private Label paymentFrequencyLabel;
    @UiField("paymentFrequency")
    private TextField paymentFrequency;

    @UiField("rebatePlanLevelLabel")
    private Label rebatePlanLevelLabel;
    @UiField("rebatePlanLevel")
    private TextField rebatePlanLevel;

    @UiField("NextBtn")
    private Button NextBtn;

    @UiField("closeBtn")
    private Button closeBtn;

    @UiField("componentInformationTableLayout")
    public VerticalLayout componentInformationTableLayout;

    /**
     * The excel btn
     */
    @UiField("excelBtn")
    public Button excelBtn;

    /* The excel btn */
    @UiField("excelBtnInfo")
    public Button excelBtnInfo;

    @UiField("valueDdlbLabel")
    public Label valueDdlbLabel;

    @UiField("comStartDateLabel")
    public Label comStartDateLabel;

    @UiField("comEndDateLabel")
    public Label comEndDateLabel;

    @UiField("submitBtn")
    public Button submitBtn;
    @UiField("tpResetBtn")
    public Button tpResetBtn;

    @UiField("previousBtn")
    public Button previousBtn;

    @UiField("searchBtn")
    Button searchBtn;

    @UiField("resetBtn")
    public Button resetBtn;

//    @UiField("searchBtn")
//    public Button searchBtn;
//    @UiField("NextBtn")
//    public Button NextBtn;
//    @UiField("closeBtn")
//    public Button closeBtn;

    final public BeanItemContainer<String> statusBean = new BeanItemContainer<String>(String.class);
    private String screenName = StringUtils.EMPTY;

    @UiField("massUpdateSection")
    public Panel massUpdateSection;
    @UiField("cfpNo")
    public CustomTextField cfpNo;
    @UiField("ifpNo")
    public CustomTextField ifpNo;
    @UiField("psNo")
    public CustomTextField psNo;
    @UiField("allCustomers")
    public Button allCustomers;
    @UiField("allCustomer")
    public ComboBox allCustomer;
    @UiField("removeProjectionDetails")
    public CheckBox removeProjectionDetails;
    final StplSecurity stplSecurity = new StplSecurity();
    Map<String, AppPermission> functionHM = new HashMap<String, AppPermission>();
    /**
     * The excel export image
     */
    private final Resource excelExportImage = new ThemeResource(EXCEL_IMAGE_PATH.getConstant());
    UiUtils UIUtils = new UiUtils();
    CurrentContractTableLogic ContractTableLogic = new CurrentContractTableLogic();
    public ExtPagedTable pagedTable = new ExtPagedTable(ContractTableLogic);
    private BeanItemContainer<ContractResultDTO> pagedContainer = new BeanItemContainer<ContractResultDTO>(ContractResultDTO.class);
    private BeanItemContainer<ComponentInformationDTO> componentInformationContainer = new BeanItemContainer<ComponentInformationDTO>(ComponentInformationDTO.class);
    ContractSelectionDTO contractSeletion = null;
    List<IdDescriptionDTO> statusResultList = new ArrayList<IdDescriptionDTO>();
    ExtTreeContainer<ContractResultDTO> tradingPartnerDetailsContainer = new ExtTreeContainer<ContractResultDTO>(ContractResultDTO.class);
    boolean summaryRefreshed;
    private ExtTreeContainer<ComponentInformationDTO> excelResultBean = new ExtTreeContainer<ComponentInformationDTO>(ComponentInformationDTO.class);
    public List<ComponentInformationDTO> componentInformation = new ArrayList<ComponentInformationDTO>();
    CompanyComponentTableLogic tablelogic = new CompanyComponentTableLogic();
    public ExtPagedTable componentInformationTable = new ExtPagedTable(tablelogic);
    AbstractLogic abstractLogic = AbstractLogic.getInstance();
    CommonUtil commonMsg = CommonUtil.getInstance();
    SelectionDTO selection;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(UpdatedContractSelection.class);

    private ContractSelectionLogic logic = new ContractSelectionLogic();

    boolean isToBeMassUpdated = false;

    public RemoveTPForm removeTpForm;
    public TransferTPForm transferTpForm;
    public AddTPForm addTpForm;
    public UpdateTPForm updateTPForm;
    SessionDTO session;
    String excelName = "Rebate Schedule Information";
    String nonAssociatedProducts = StringUtils.EMPTY;
    int timeGap;
    String[] excelComponentId = new String[NumericConstants.FIVE];
    String excelSelectionValue;

    boolean isTableUpdate = false;

    boolean isComponentInformationExport = false;

    public UpdatedContractSelection(SessionDTO session, AddTPForm form) {
        this.addTpForm = form;
        initContractSelection(session);
        configureAddTpScreen();
        configureCurrentTradingPartnerTable();
        configureComponentInformationTable();
        configureSecurityPermissionsAddTransfer();
    }

    public UpdatedContractSelection(SessionDTO session, RemoveTPForm form) {
        this.removeTpForm = form;
        initContractSelection(session);
        configureCurrentTradingPartnerTable();
        configureComponentInformationTable();
        configureSecurityPermissionsRemoveTransfer();
    }

    public UpdatedContractSelection(SessionDTO session, TransferTPForm form, boolean isTransfer) {
        this.transferTpForm = form;
        initContractSelection(session);
        configureFieldsForTransferTP(isTransfer);
        configureCurrentTradingPartnerTable();
        configureComponentInformationTable();
        configureSecurityPermissionsForTransfer(isTransfer);
    }

    public UpdatedContractSelection(SessionDTO session, UpdateTPForm form) {
        this.updateTPForm = form;
        initContractSelection(session);
        configureAddTpScreen();
        configureCurrentTradingPartnerTable();
        configureComponentInformationTable();
        configureSecurityPermissions();
    }

    private void initContractSelection(SessionDTO session) {
        this.session = session;
        addComponent(Clara.create(getClass().getResourceAsStream("/TradingPartner/contractSelectionUpdated.xml"), this));
        configureFields();
        contractSeletion = new ContractSelectionDTO();
        contractSeletion.setCompanyMasterSids(session.getCompanyMasterSids());
        contractSeletion.setPhCompanyMasterSids(session.getPhCompanyMasterSids());
        contractSeletion.setSearchInverse(false);
        if (session.getModuleName().equals(ADD_TRADING_PARTNER.getConstant())
                || (session.getModuleName().equals(TRANSFER_TRADING_PARTNER.getConstant()) && TAB_TRANSFER_CONTRACT.getConstant().equals(screenName))) {
            contractSeletion.setSearchInverse(true);
        }

        if (session.getModuleName().equals(TRANSFER_TRADING_PARTNER.getConstant()) || session.getModuleName().equals(PROJECTION_DETAILS_TRANSFER.getConstant())) {
            contractSeletion.setScreenName(screenName);
        }
        contractSeletion.setSearch(true);
        contractSeletion.setReset(false);
    }

    private void configureFieldsForTransferTP(boolean isTransfer) {
        if (session.getModuleName().equals(PROJECTION_DETAILS_TRANSFER.getConstant())) {
            previousBtn.setVisible(true);
        }

        if (isTransfer) {
            tradingPartnerPanel.setCaption("Transfer - Customer Details");
            previousBtn.setVisible(isTransfer);
            fieldDdlb.addItem("Status");
            fieldDdlb.addItem("Company Start Date");
            screenName = TAB_TRANSFER_CONTRACT.getConstant();

        } else {
            screenName = TAB_CURRENT_CONTRACT.getConstant();
            transferSalesProjectionOptionLayout.setVisible(true);
            transferSalesProjectionOption.addItem(YES.getConstant());
            transferSalesProjectionOption.addItem(NO.getConstant());
            transferSalesProjectionOption.select(YES.getConstant());
            transferSalesProjectionOption.addValueChangeListener(new Property.ValueChangeListener() {

                public void valueChange(Property.ValueChangeEvent event) {
                    if (transferSalesProjectionOption.getValue().equals(YES.getConstant())) {
                        transferTpForm.setSalesCopyFlag(true);
                    } else {
                        transferTpForm.setSalesCopyFlag(false);
                    }
                }
            });
            removeProjectionDetails.addValueChangeListener(new Property.ValueChangeListener() {

                public void valueChange(Property.ValueChangeEvent event) {
                    transferTpForm.setSalesRemoveFlag(removeProjectionDetails.getValue());
                }
            });
        }
    }

    protected void configureFields() {
        try {
            CommmonLogic logic = new CommmonLogic();

            if (TRADING_PARTNER_REMOVE.getConstant().equals(session.getModuleName())) {
                massUpdateSection.setVisible(false);
            } else {
                massUpdateSection.setVisible(true);
            }

            marketType.addItem(SELECT_ONE.getConstant());
            marketType.setNullSelectionAllowed(true);
            marketType.setNullSelectionItemId(SELECT_ONE.getConstant());

            rebateScheduleType.addItem(SELECT_ONE.getConstant());
            rebateScheduleType.setNullSelectionAllowed(true);
            rebateScheduleType.setNullSelectionItemId(SELECT_ONE.getConstant());

            rarCategory.addItem(SELECT_ONE.getConstant());
            rarCategory.setNullSelectionAllowed(true);
            rarCategory.setNullSelectionItemId(SELECT_ONE.getConstant());

            rebateScheduleCategory.addItem(SELECT_ONE.getConstant());
            rebateScheduleCategory.setNullSelectionAllowed(true);
            rebateScheduleCategory.setNullSelectionItemId(SELECT_ONE.getConstant());

            rebateProgramType.addItem(SELECT_ONE.getConstant());
            rebateProgramType.setNullSelectionAllowed(true);
            rebateProgramType.setNullSelectionItemId(SELECT_ONE.getConstant());

            fieldDdlb.addItem(SELECT_ONE.getConstant());
            fieldDdlb.setNullSelectionAllowed(true);
            fieldDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
            fieldDdlb.addItem("Company End Date");
            valueDdlb.setVisible(true);
            startPeriod.setVisible(false);
            startPeriod.setDateFormat("MM/dd/yyyy");
            endPeriod.setDateFormat("MM/dd/yyyy");
            comStartDateLabel.setVisible(false);

            endPeriod.setVisible(false);
            comEndDateLabel.setVisible(false);
            componentSelection.addItem(SELECT_ONE.getConstant());
            componentSelection.addItem(COMPANY_FAMILY_PLAN.getConstant());
            componentSelection.addItem(ITEM_FAMILY_PLAN.getConstant());
            componentSelection.addItem(PRICE_SCHEDULE.getConstant());
            componentSelection.addItem(REBATE_SCHEDULE.getConstant());

            componentSelection.setNullSelectionAllowed(true);
            componentSelection.setNullSelectionItemId(SELECT_ONE.getConstant());
            componentSelection.select(REBATE_SCHEDULE.getConstant());

            componentSelection.addValueChangeListener(new Property.ValueChangeListener() {

                public void valueChange(Property.ValueChangeEvent event) {
                    loadComponentInformation(String.valueOf(event.getProperty().getValue()), pagedTable.getValue());
                    componentInformationTable.setFilterBarVisible(true);
                    componentInformationTable.setFilterDecorator(new ExtDemoFilterDecorator());
                    componentInformationTable.setFilterGenerator(new CustomerFilterGenerator());
                    componentInformationTable.addStyleName("filtertable");
                    componentInformationTable.addStyleName("table-header-normal");
                }
            });

            contractHolder.setStyleName("searchicon");
            currentTradingPartnerTableLayout.addComponent(pagedTable);
            HorizontalLayout hLayout = new HorizontalLayout();
            hLayout = ContractTableLogic.createControls();
            currentTradingPartnerTableLayout.addComponent(hLayout);

            componentInformationTableLayout.addComponent(componentInformationTable);
            hLayout = tablelogic.createControls();
            componentInformationTableLayout.addComponent(hLayout);
            excelBtn.setIcon(excelExportImage);
            excelBtnInfo.setIcon(excelExportImage);

            massUpdateEnableDisable.addItem(ENABLE.getConstant());
            massUpdateEnableDisable.addItem(DISABLE.getConstant());
            massUpdateEnableDisable.select(DISABLE.getConstant());
            statusResultList = logic.loadDdlbs("STATUS");
            logic.setIdDescription(statusResultList, valueDdlb);

            CompanySearchLogic cslogic = new CompanySearchLogic();
            List<IdDescriptionDTO> resultList = cslogic.loadDdlbs("CONTRACT_TYPE");
            cslogic.setIdDescription(resultList, marketType);
            resultList.clear();
            resultList = cslogic.loadDdlbs("RS_TYPE");
            cslogic.setIdDescription(resultList, rebateScheduleType);
            resultList.clear();
            resultList = cslogic.loadDdlbs("RS_CATEGORY");
            cslogic.setIdDescription(resultList, rebateScheduleCategory);
            resultList.clear();
            resultList = cslogic.loadDdlbs("REBATE_PROGRAM_TYPE");
            cslogic.setIdDescription(resultList, rebateProgramType);
            resultList.clear();
            resultList = cslogic.loadDdlbs("RS_UDC2");
            cslogic.setIdDescription(resultList, rarCategory);
            resultList.clear();
            allCustomer.addItem("Yes");
            allCustomer.addItem("No");
            allCustomer.select("No");
            allCustomer.setReadOnly(Boolean.TRUE);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public void configureCurrentTradingPartnerTable() {
        final CommmonLogic logic = new CommmonLogic();

        pagedTable.setFilterBarVisible(true);
        pagedTable.setFilterGenerator(new ExtFilterGenerator() {

            @Override
            public AbstractField<?> getCustomFilterComponent(Object propertyId) {
                if (propertyId.equals("status")) {
                    try {
                        CustomComboBox status = new CustomComboBox();
                        logic.setIdDescription(statusResultList, status);
                        return status;
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }

                }
                if (propertyId.equals("checkRecord")) {
                    TextField checkRec = new TextField();
                    checkRec.setEnabled(false);
                    checkRec.setWidth(String.valueOf(NumericConstants.HUNDRED));
                    return checkRec;
                }
                if ("rARCategory".equals(propertyId)) {
                    try {
                        final ComboBox rarCategory = new ComboBox();
                        commonMsg.loadComboBoxForGCM(rarCategory, "RS_UDC2", true);
                        rarCategory.select(0);
                        return rarCategory;
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
                return null;
            }

            @Override
            public void filterRemoved(Object propertyId) {
            }

            @Override
            public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
            }

            @Override
            public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
                return null;
            }

            public Container.Filter generateFilter(Object propertyId, Object value) {
                return null;
            }

            public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
                if (originatingField instanceof ComboBox) {
                    if (originatingField.getValue() != null) {
                        return new SimpleStringFilter(propertyId, String.valueOf(originatingField.getValue()), false, false);
                    } else {
                        return null;
                    }
                }
                return null;
            }
        });

        pagedTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        ContractTableLogic.setContainerDataSource(pagedContainer);

        if (ADD_TRADING_PARTNER.getConstant().equals(session.getModuleName())) {
            pagedTable.setVisibleColumns(Constants.CONTRACT_SELECTION_COLUMNS);
            pagedTable.setColumnHeaders(Constants.CONTRACT_SELECTION_HEADERS);
        } else if (TRANSFER_TRADING_PARTNER.getConstant().equals(session.getModuleName()) && screenName.equals(TAB_TRANSFER_CONTRACT.getConstant())) {
            pagedTable.setVisibleColumns(Constants.TRANSFER_CUSTOMER_COLUMNS);
            pagedTable.setColumnHeaders(Constants.TRANSFER_CUSTOMER_HEADERS);
        } else if (PROJECTION_DETAILS_TRANSFER.getConstant().equals(session.getModuleName())) {
            pagedTable.setVisibleColumns(Constants.TRANSFER_CUSTOMER_COLUMNS);
            pagedTable.setColumnHeaders(Constants.TRANSFER_CUSTOMER_HEADERS);
        } else {
            pagedTable.setVisibleColumns(Constants.REMOVE_TP_CONTRACT_SELECTION_COLUMNS);
            pagedTable.setColumnHeaders(Constants.REMOVE_TP_SELECTION_HEADERS);
        }
        pagedTable.setColumnAlignment("contStartDate", ExtCustomTable.Align.CENTER);
        pagedTable.setColumnAlignment("contEndDate", ExtCustomTable.Align.CENTER);
        pagedTable.setColumnAlignment("compStartDate", ExtCustomTable.Align.CENTER);
        pagedTable.setColumnAlignment("compEndDate", ExtCustomTable.Align.CENTER);
        pagedTable.setImmediate(true);
        pagedTable.setSizeFull();
        pagedTable.setSelectable(true);
        pagedTable.setPageLength(NumericConstants.FIVE);
        pagedTable.setEditable(true);

        pagedTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {

            public void itemClick(ItemClickEvent event) {
                String componentSelectionValue = String.valueOf(componentSelection.getValue());
                if (!SELECT_ONE.getConstant().equals(componentSelectionValue)) {
                    loadComponentInformation(componentSelectionValue, event.getItemId());
                }
            }
        }
        );
        ContractTableLogic.sinkItemPerPageWithPageLength(false);
        pagedTable.addColumnCheckListener(checkListener);
        pagedTable.setColumnWidth("checkRecord", NumericConstants.HUNDRED);
        for (Object object : pagedTable.getVisibleColumns()) {
            if (String.valueOf(object).contains("Date")) {
                pagedTable.setColumnAlignment(object, ExtCustomTable.Align.CENTER);
            }
        }
        pagedTable.setFilterDecorator(new ExtDemoFilterDecorator());

        pagedTable.addGeneratedColumn("projectionIdLink", new ExtCustomTable.ColumnGenerator() {
            @Override
            public Object generateCell(ExtCustomTable source, Object itemId, Object columnId) {
                final ContractResultDTO dto = (ContractResultDTO) itemId;
                final Button projectionId = new Button(dto.getProjectionId());
                if (!dto.getWorkflowStatus().equals(StringUtils.EMPTY)) {
                    projectionId.setCaption(dto.getProjectionId()); // for setting revision date in excel
                    projectionId.setData(dto);
                    projectionId.setImmediate(true);
                    projectionId.setStyleName(Reindeer.BUTTON_LINK);
                    projectionId.addClickListener(new Button.ClickListener() {
                        public void buttonClick(Button.ClickEvent event) {

                            WorkFlowLookup wLookUp = new WorkFlowLookup(session, dto.getProjectionId());
                            UI.getCurrent().addWindow(wLookUp);
                            wLookUp.addCloseListener(new Window.CloseListener() {

                                public void windowClose(Window.CloseEvent e) {
                                    ContractTableLogic.loadSetData(contractSeletion, session);
                                }
                            });
                        }
                    });
                    dto.setProjectionIdLink(dto.getProjectionId());
                    return projectionId;
                } else {
                    return null;
                }
            }

        });

        pagedTable.setTableFieldFactory(new TableFieldFactory() {
            public Field<?> createField(Container container, final Object itemId, Object propertyId, Component uiContext) {
                final ContractResultDTO dto = (ContractResultDTO) itemId;

                if (propertyId.equals("checkRecord")) {
                    final ExtCustomCheckBox check = new ExtCustomCheckBox();
                    if (!dto.getWorkflowStatus().trim().isEmpty()) {
                        check.setVisible(false);
                    } else {
                        check.setImmediate(true);
                        check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                            public void click(ExtCustomCheckBox.ClickEvent event) {
                                logic.callCheckRecUpdate(check.getValue(), (ContractResultDTO) itemId, screenName, session);
                                if (!check.getValue()) {

                                    pagedTable.removeColumnCheckListener(checkListener);
                                    pagedTable.setColumnCheckBox("check", true, false);
                                    pagedTable.addColumnCheckListener(checkListener);

                                }
                            }
                        });
                    }
                    return check;
                }

                if (propertyId.equals("compStartDate") && !isTableUpdate) {
                    final PopupDateField compStartDate = new PopupDateField();
                    compStartDate.setStyleName("dateFieldCenter");
                    compStartDate.addStyleName("datefieldcentered");
                    compStartDate.setDateFormat("MM/dd/yyyy");
                    if (dto.getWorkflowStatus().trim().isEmpty() && (screenName.equals(TAB_TRANSFER_CONTRACT.getConstant()) || TRADING_PARTNER_UPDATE.getConstant().equals(session.getModuleName()) || session.getModuleName().equals(ADD_TRADING_PARTNER.getConstant()))) {
                        compStartDate.setImmediate(true);
                        compStartDate.setData(((ContractResultDTO) itemId).getCompStartDate());

                        compStartDate.addFocusListener(new FieldEvents.FocusListener() {

                            @Override
                            public void focus(FieldEvents.FocusEvent event) {
                                Property.ValueChangeListener valueChangeListner = new Property.ValueChangeListener() {

                                    public void valueChange(Property.ValueChangeEvent event) {
                                        if (!isTableUpdate && compStartDate != null && compStartDate.getValue() != null) {
                                            Date enteredDate = compStartDate.getValue();
                                            Date maxEndDate = new Date(1, 1, NumericConstants.ONE_NINE_ZERO_ZERO);
                                            if (screenName.equals(TAB_TRANSFER_CONTRACT.getConstant())) {
                                                String endDate = CommonLogic.getDateForSubmittedContract(session.getSessionId(), false, false, true);
                                                maxEndDate = new Date(CommonLogic.convertDateFormat(endDate, "yyyy-MM-dd HH:mm:ss.SSS", "MM/dd/yyyy"));
                                            }

                                            if (enteredDate == null || (maxEndDate != null && enteredDate.after(maxEndDate))) {
                                                if (dto.getCheckRecord()) {
                                                    performMassUpdate("Company Start Date", enteredDate);
                                                } else {
                                                    logic.callDateUpdate(enteredDate, dto, session, screenName, "START_DATE");
                                                }
                                            } else {
                                                Date oldStartDate = null;
                                                if (compStartDate.getData() != null) {
                                                    oldStartDate = (Date) compStartDate.getData();
                                                }
                                                isTableUpdate = true;
                                                compStartDate.setValue(oldStartDate);
                                                isTableUpdate = false;
                                                AbstractNotificationUtils.getErrorNotification("Update Error", "The Start Date must come after the End Date in current contract. Please reenter the Start Date. ");
                                            }
                                        }
                                    }
                                };
                                compStartDate.addValueChangeListener(valueChangeListner);
                                valueChangeListner.valueChange(null);
                                compStartDate.removeFocusListener(this);
                            }
                        });

                    } else {
                        compStartDate.setEnabled(false);
                    }

                    return compStartDate;
                }

                if (propertyId.equals("compEndDate")) {
                    final PopupDateField compEndDate = new PopupDateField();
                    compEndDate.setDateFormat("MM/dd/yyyy");
                    compEndDate.setStyleName("dateFieldCenter");
                    compEndDate.addStyleName("datefieldcentered");
                    if (dto.getWorkflowStatus().trim().isEmpty()) {
                        compEndDate.setImmediate(true);
                        compEndDate.setData(((ContractResultDTO) itemId).getCompEndDate());
                        compEndDate.addFocusListener(new FieldEvents.FocusListener() {

                            @Override
                            public void focus(FieldEvents.FocusEvent event) {
                                Property.ValueChangeListener valueChangeListner = new Property.ValueChangeListener() {
                                    public void valueChange(Property.ValueChangeEvent event) {
                                        if (!isTableUpdate) {
                                            try {
                                                Date compStartDate = ((ContractResultDTO) itemId).getCompStartDate();
                                                if (compStartDate != null) {
                                                    Date enteredDate = null;
                                                    if (compEndDate != null && compEndDate.getValue() != null) {
                                                        enteredDate = compEndDate.getValue();
                                                    }
                                                    SimpleDateFormat dateFormater = new SimpleDateFormat("MM-dd-yyyy");
                                                    if (enteredDate == null
                                                            || (enteredDate.after(compStartDate) && !ContractSelectionLogic.isStartDateGreaterThanEndDate(session.getUserId(), session.getSessionId(), dateFormater.format((Date) enteredDate)))) {
                                                        if (dto.getCheckRecord()) {
                                                            performMassUpdate("Company End Date", enteredDate);
                                                        } else {
                                                            logic.callDateUpdate(enteredDate, dto, session, screenName, "END_DATE");
                                                        }
                                                    } else {
                                                        Date newEndDate = null;
                                                        if (compEndDate.getData() != null) {
                                                            newEndDate = (Date) compEndDate.getData();
                                                        }
                                                        isTableUpdate = true;
                                                        compEndDate.setValue(newEndDate);
                                                        isTableUpdate = false;
                                                        AbstractNotificationUtils.getErrorNotification("Update Error", "The End Date must come after the Start Date. Please reenter the End Date. ");
                                                    }
                                                } else {
                                                    AbstractNotificationUtils.getErrorNotification("Update Error", "Please enter a Start Date before Entering an End Date");
                                                }

                                            } catch (Exception e) {
                                                LOGGER.error(e);
                                            }
                                        }
                                    }
                                };
                                compEndDate.addValueChangeListener(valueChangeListner);
                                valueChangeListner.valueChange(null);
                                compEndDate.removeFocusListener(this);
                            }
                        });
                    } else {
                        compEndDate.setEnabled(false);
                    }
                    return compEndDate;
                }

                if (String.valueOf(propertyId).equals("status")) {
                    final CustomComboBox status = new CustomComboBox();
                    status.setImmediate(true);
                    try {
                        CommmonLogic.loaDDLBForListLoading(status, UIUtils.STATUS, false);
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                    if (screenName.equals(TAB_TRANSFER_CONTRACT.getConstant())
                            || (session.getModuleName().equals(ADD_TRADING_PARTNER.getConstant()) && dto.getWorkflowStatus().trim().isEmpty())
                            || (session.getModuleName().equals(TRADING_PARTNER_UPDATE.getConstant()) && dto.getWorkflowStatus().trim().isEmpty())) {
                        status.addFocusListener(new FieldEvents.FocusListener() {

                            @Override
                            public void focus(FieldEvents.FocusEvent event) {
                                status.addValueChangeListener(new Property.ValueChangeListener() {

                                    @Override
                                    public void valueChange(Property.ValueChangeEvent event) {
                                        if (!isTableUpdate) {
                                            HelperDTO helperDto = null;
                                            if (status.getValue() != null) {
                                                helperDto = (HelperDTO) status.getValue();
                                                int stat = helperDto.getId();
                                                if (stat != 0) {
                                                    int count = 0;
                                                    if (dto.getCheckRecord()) {
                                                        performMassUpdate("Status", stat);
                                                    } else {
                                                        count = logic.callStatusUpdate(stat, (ContractResultDTO) itemId, session, screenName);
                                                        if (count == 0) {
                                                            logic.callStatusInsert(stat, (ContractResultDTO) itemId, session, screenName);
                                                        }
                                                    }
                                                }
                                            }
                                        }

                                    }

                                });
                                status.removeFocusListener(this);
                            }
                        });

                    } else {
                        status.setEnabled(false);
                    }
                    return status;

                }

                if (propertyId.equals(
                        "contEndDate")) {
                    final PopupDateField contEndDate = new PopupDateField();
                    contEndDate.setDateFormat("MM/dd/yyyy");
                    contEndDate.setStyleName("dateFieldCenter");
                    contEndDate.addStyleName("datefieldcentered");
                    contEndDate.setEnabled(false);

                    return contEndDate;
                }

                if (propertyId.equals(
                        "contStartDate")) {
                    final PopupDateField contStartDate = new PopupDateField();
                    contStartDate.setDateFormat("MM/dd/yyyy");
                    contStartDate.setStyleName("dateFieldCenter");
                    contStartDate.addStyleName("datefieldcentered");
                    contStartDate.setEnabled(false);
                    return contStartDate;
                }

                return null;
            }
        });

    }

    public void configureComponentInformationTable() {
        componentInformationTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        componentInformationTable.addStyleName("projectionTable");

        componentInformationTable.setWidth(NumericConstants.HUNDRED, Sizeable.Unit.PERCENTAGE);
        componentInformationTable.setHeight(NumericConstants.FOUR_HUNDRED, Sizeable.Unit.PIXELS);
        componentInformationTable.setPageLength(NumericConstants.FIVE);
        tablelogic.setContainerDataSource(componentInformationContainer);

        componentInformationTable.setVisibleColumns(Constants.TP_COMPONENT_INFORMATION_COLUMNS_RS);
        componentInformationTable.setColumnHeaders(Constants.TP_COMPONENT_INFORMATION_HEADERS_RS);
        componentInformationTable.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
        componentInformationTable.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);

        componentInformationTable.setFilterBarVisible(true);
        componentInformationTable.setFilterDecorator(new ExtDemoFilterDecorator());
        componentInformationTable.setFilterGenerator(new CustomerFilterGenerator());
        componentInformationTable.addStyleName("filtertable");
        componentInformationTable.addStyleName("table-header-normal");
    }

    private void loadComponentInformation(String componentSelectionValue, Object selectedItem) {
        LOGGER.debug("Inside loadComponentInformation");
        if (selectedItem != null) {
            String[] id = new String[NumericConstants.FIVE];
            ContractResultDTO crDTO = (ContractResultDTO) selectedItem;
            id[0] = String.valueOf(crDTO.getContractMasterSid());
            id[1] = String.valueOf(crDTO.getCfpContSid());
            id[NumericConstants.TWO] = String.valueOf(crDTO.getIfpContSid());
            id[NumericConstants.THREE] = String.valueOf(crDTO.getPsContSid());
            id[NumericConstants.FOUR] = String.valueOf(crDTO.getRsContSid());
            excelComponentId = id;
            excelSelectionValue = componentSelectionValue;
            if (!componentSelectionValue.equals(Constants.NULL) && !SELECT_ONE.getConstant().equals(componentSelectionValue)) {
                changeComponents(componentSelectionValue);
                loadComponentInformationFields(Arrays.asList(logic.getComponentInformationData(componentSelectionValue, id, false, false, 0, 0, null).get(0)));
                loadComponentInformationTable(componentSelectionValue);
                tablelogic.loadSetData(componentSelectionValue, id, Boolean.TRUE);
            } else {
                tablelogic.loadSetData(componentSelectionValue, id, false);
                loadComponentInformationFields(new ArrayList<Object>());

            }
        }
        LOGGER.debug("Exiting loadComponentInformation");
    }

    /**
     * To load the Fields inside the component information panel
     *
     * @param componentSelectionValue
     * @param fieldData
     */
    private void loadComponentInformationFields(List<Object> fieldData) {
        if (fieldData != null && !fieldData.isEmpty()) {

            try {
                cId.setValue(getFromList(fieldData, 0));
                cNumber.setValue(getFromList(fieldData, 1));
                cName.setValue(getFromList(fieldData, NumericConstants.TWO));
                status.setValue(getFromList(fieldData, NumericConstants.THREE));
                startDate.setValue(formatDate(getFromList(fieldData, NumericConstants.FOUR)));
                endDate.setValue(formatDate(getFromList(fieldData, NumericConstants.FIVE)));
                startDate.addStyleName("align-center");
                endDate.addStyleName("align-center");
                type.setValue(getFromList(fieldData, NumericConstants.SIX));
                rebateFrequency.setValue(getFromList(fieldData, NumericConstants.SEVEN));
                basis.setValue(getFromList(fieldData, NumericConstants.EIGHT));
                rsType.setValue(getFromList(fieldData, NumericConstants.NINE));
                rsProgramType.setValue(getFromList(fieldData, NumericConstants.TEN));
                rsCategory.setValue(getFromList(fieldData, NumericConstants.ELEVEN));
                paymentFrequency.setValue(getFromList(fieldData, NumericConstants.TWELVE));
                rebatePlanLevel.setValue(getFromList(fieldData, NumericConstants.THIRTEEN));

            } catch (ParseException ex) {
                LOGGER.error(ex);
            }
        }
    }

    /**
     * To load the Component Information table
     *
     * @param componentSelectionValue
     * @param tableData
     */
    private void loadComponentInformationTable(String componentSelectionValue) {

        if (REBATE_SCHEDULE.getConstant().equals(componentSelectionValue)) {
            componentInformationTable.setVisibleColumns(Constants.TP_COMPONENT_INFORMATION_COLUMNS_RS);
            componentInformationTable.setColumnHeaders(Constants.TP_COMPONENT_INFORMATION_HEADERS_RS);
            componentInformationTable.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
            componentInformationTable.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);
            componentInformationTable.setColumnAlignment("attachedDate", ExtCustomTable.Align.CENTER);
        } else if (PRICE_SCHEDULE.getConstant().equals(componentSelectionValue)) {
            componentInformationTable.setVisibleColumns(Constants.TP_COMPONENT_INFORMATION_COLUMNS_PS);
            componentInformationTable.setColumnHeaders(Constants.TP_COMPONENT_INFORMATION_HEADERS_PS);
            componentInformationTable.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
            componentInformationTable.setColumnAlignment(Constants.END_DATE, ExtCustomTable.Align.CENTER);
            componentInformationTable.setColumnAlignment("priceProtectionStartDate", ExtCustomTable.Align.CENTER);
            componentInformationTable.setColumnAlignment("priceProtectionEndDate", ExtCustomTable.Align.CENTER);
            componentInformationTable.setColumnAlignment("resetDate", ExtCustomTable.Align.CENTER);
            componentInformationTable.setColumnAlignment("attachedDate", ExtCustomTable.Align.CENTER);
        } else if (COMPANY_FAMILY_PLAN.getConstant().equals(componentSelectionValue)) {
            componentInformationTable.setVisibleColumns(Constants.TP_COMPONENT_INFORMATION_COLUMNS_CFP);
            componentInformationTable.setColumnHeaders(Constants.TP_COMPONENT_INFORMATION_HEADERS_CFP);
            componentInformationTable.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
            componentInformationTable.setColumnAlignment("attachedDate", ExtCustomTable.Align.CENTER);
        } else if (ITEM_FAMILY_PLAN.getConstant().equals(componentSelectionValue)) {
            componentInformationTable.setVisibleColumns(Constants.TP_COMPONENT_INFORMATION_COLUMNS_IFP);
            componentInformationTable.setColumnHeaders(Constants.TP_COMPONENT_INFORMATION_HEADERS_IFP);
            componentInformationTable.setColumnAlignment(Constants.START_DATE, ExtCustomTable.Align.CENTER);
            componentInformationTable.setColumnAlignment("attachedDate", ExtCustomTable.Align.CENTER);
        }

    }

    @UiHandler("NextBtn")
    public void nextButtonLogic(Button.ClickEvent event) {
        LOGGER.debug("Contract selection submitAndNextLogic initiated");
        List<String> checkData = logic.getSubmitValidationData(session.getUserId(), session.getSessionId(), screenName, "check");

        if (logic.isAnyDataSubmitted(session.getUserId(), session.getSessionId(), session.getModuleName(), screenName)) {
            if (checkData.size() <= 0 || checkData.get(0).equals(Constants.ZEROSTRING)) {
                changeTab();
            } else {
                submitAndNextLogic(true);
            }
        } else {
            submitAndNextLogic(true);
        }
    }

    @UiHandler("previousBtn")
    public void previousButtonLogic(Button.ClickEvent event) {
        if (session.getModuleName().equals(TRADING_PARTNER_REMOVE.getConstant())) {
            removeTpForm.getTabSheet().setSelectedTab(removeTpForm.getTabPosition() - 1);

        } else if (session.getModuleName().equals(ADD_TRADING_PARTNER.getConstant())) {
            addTpForm.getTabSheet().setSelectedTab(0);

        } else if (session.getModuleName().equals(TRADING_PARTNER_UPDATE.getConstant())) {
            updateTPForm.getTabSheet().setSelectedTab(updateTPForm.getTabPosition() - 1);

        } else {
            transferTpForm.getTabSheet().setSelectedTab(transferTpForm.getTabPosition() - 1);
        }
    }

    @UiHandler("closeBtn")
    public void closeBtnLogic(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            public void noMethod() {
                // do nothing
            }

            @Override
            public void yesMethod() {
                try {
                    if (session.getModuleName().equals(TRADING_PARTNER_REMOVE.getConstant())) {
                        removeTpForm.close();
                    } else if (session.getModuleName().equals(ADD_TRADING_PARTNER.getConstant())) {
                        addTpForm.close();
                    } else if (session.getModuleName().equals(TRADING_PARTNER_UPDATE.getConstant())) {
                        updateTPForm.close();
                    } else {
                        transferTpForm.close();
                    }
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        }.getConfirmationMessage("Close confirmation", "Are you sure you want to close out? \n No values will be saved. ");

    }

    @UiHandler("searchBtn")
    public void searchButtonLogic(Button.ClickEvent event) {
        LOGGER.debug(" Inside searchButtonLogic");
        contractSeletion = new ContractSelectionDTO();
        boolean searCriteria = false;
        contractSeletion.setCompanyMasterSids(session.getCompanyMasterSids());
        contractSeletion.setPhCompanyMasterSids(session.getPhCompanyMasterSids());
        contractSeletion.setModuleName(session.getModuleName());

        if (session.getModuleName().equals(ADD_TRADING_PARTNER.getConstant())
                || (session.getModuleName().equals(TRANSFER_TRADING_PARTNER.getConstant()) && TAB_TRANSFER_CONTRACT.getConstant().equals(screenName))) {
            contractSeletion.setSearchInverse(true);
        }

        if (session.getModuleName().equals(TRANSFER_TRADING_PARTNER.getConstant()) || session.getModuleName().equals(PROJECTION_DETAILS_TRANSFER.getConstant())) {
            contractSeletion.setScreenName(screenName);
        }

        if (!contractNo.getValue().equals(StringUtils.EMPTY) && !contractNo.getValue().equals(Constants.NULL)) {
            contractSeletion.setContractNo(contractNo.getValue());
            searCriteria = true;
        }
        if (!contractName.getValue().equals(StringUtils.EMPTY) && !contractName.getValue().equals(Constants.NULL)) {
            contractSeletion.setContractName(contractName.getValue());
            searCriteria = true;
        }
        if (!contractHolder.getValue().equals(StringUtils.EMPTY) && !contractHolder.getValue().equals(Constants.NULL)) {
            contractSeletion.setContractHolder(String.valueOf(contractHolder.getData()));
            searCriteria = true;
        }
        if (marketType.getValue() != null) {
            contractSeletion.setMarketType(String.valueOf(marketType.getValue()));
            searCriteria = true;
        }
        if (!rebateScheduleId.getValue().equals(StringUtils.EMPTY) && !rebateScheduleId.getValue().equals(Constants.NULL)) {
            contractSeletion.setRebateScheduleId(rebateScheduleId.getValue());
            searCriteria = true;
        }
        if (!rebateScheduleName.getValue().equals(StringUtils.EMPTY) && !rebateScheduleName.getValue().equals(Constants.NULL)) {
            contractSeletion.setRebateScheduleName(rebateScheduleName.getValue());
            searCriteria = true;
        }
        if (rebateScheduleType.getValue() != null) {
            contractSeletion.setRebateScheduleType(String.valueOf(rebateScheduleType.getValue()));
            searCriteria = true;
        }
        if (rarCategory.getValue() != null) {
            contractSeletion.setRarCategory(String.valueOf(rarCategory.getValue()));
            searCriteria = true;
        }
        if (!rebateScheduleNo.getValue().equals(StringUtils.EMPTY) && !rebateScheduleNo.getValue().equals(Constants.NULL)) {
            contractSeletion.setRebateScheduleNo(rebateScheduleNo.getValue());
            searCriteria = true;
        }
        if (rebateScheduleCategory.getValue() != null) {
            contractSeletion.setRebateScheduleCategory(String.valueOf(rebateScheduleCategory.getValue()));
            searCriteria = true;
        }
        if (rebateProgramType.getValue() != null) {
            contractSeletion.setRebateProgramType(String.valueOf(rebateProgramType.getValue()));
            searCriteria = true;
        }
        if (!rebateScheduleAlias.getValue().equals(StringUtils.EMPTY) && !rebateScheduleAlias.getValue().equals(Constants.NULL)) {
            contractSeletion.setRebateScheduleAlias(rebateScheduleAlias.getValue());
            searCriteria = true;
        }
        if (!cfpNo.getValue().equals(StringUtils.EMPTY) && !cfpNo.getValue().equals(Constants.NULL)) {
            contractSeletion.setCfpNo(cfpNo.getValue());
            searCriteria = true;
        }
        if (!ifpNo.getValue().equals(StringUtils.EMPTY) && !ifpNo.getValue().equals(Constants.NULL)) {
            contractSeletion.setIfpNo(ifpNo.getValue());
            searCriteria = true;
        }
        if (!psNo.getValue().equals(StringUtils.EMPTY) && !psNo.getValue().equals(Constants.NULL)) {
            contractSeletion.setPsNo(psNo.getValue());
            searCriteria = true;
        }
        if (!searCriteria) {
            AbstractNotificationUtils.getAlertNotification(Constants.MessageConstants.NO_SEARCH_CRITERIA_TITLE.getConstant(),
                    Constants.MessageConstants.NO_SEARCH_CRITERIA_BODY.getConstant());
            return;

        } else {
            contractSeletion.setSearch(true);

        }
        contractSeletion.setReset(false);
        if (!ContractTableLogic.loadSetData(contractSeletion, session)) {
            AbstractNotificationUtils.getErrorNotification("No Records",
                    "There are no Company records that match the search criteria. Please try again.");
        } else {
            CommonUIUtils.getMessageNotification("Search Completed");
        }

    }

    private String getFromList(List<Object> fieldList, int number) {
        String fieldvalue = StringUtils.EMPTY;
        try {
            if (fieldList != null && fieldList.get(number) != null) {
                fieldvalue = String.valueOf(fieldList.get(number));
                if (Constants.SELECT_ONE.equals(fieldvalue)) {
                    fieldvalue = StringUtils.EMPTY;
                }
            }
        } catch (IndexOutOfBoundsException ie) {
            LOGGER.error(ie);

        }
        return fieldvalue;
    }

    /**
     * Mass Update Functionality
     *
     * @param event
     */
    @UiHandler("massUpdateRadio")
    public void massUpdateEnDisLogic(Property.ValueChangeEvent event) {
        LOGGER.debug(" massUpdate ValueChangeEvent initiated ");
        if ("Disable".equals(massUpdateEnableDisable.getValue())) {
            enableOrDisable(false);
        } else {
            enableOrDisable(true);
        }
        LOGGER.debug("massUpdate ValueChangeEvent ends ");
    }

    /**
     * Enable and Disable Logic
     *
     * @param value
     */
    private void enableOrDisable(boolean value) {
        fieldDdlb.setEnabled(value);
        valueDdlb.setEnabled(value);
        populateBtn.setEnabled(value);
        startPeriod.setEnabled(value);
        endPeriod.setEnabled(value);
    }

    @UiHandler("fieldDdlb")
    public void fieldDdlbValueChange(Property.ValueChangeEvent event) {
        LOGGER.debug(" FieldDdlb ValueChangeEvent initiated ");

        if ("Status".equals(String.valueOf(fieldDdlb.getValue()))) {

            valueDdlb.setVisible(true);
            valueDdlbLabel.setVisible(true);
            startPeriod.setVisible(false);
            comStartDateLabel.setVisible(false);

            endPeriod.setVisible(false);
            comEndDateLabel.setVisible(false);
        } else if ("Company Start Date".equals(String.valueOf(fieldDdlb.getValue()))) {
            valueDdlb.setVisible(false);
            valueDdlbLabel.setVisible(false);
            startPeriod.setVisible(true);
            comStartDateLabel.setVisible(true);
            endPeriod.setVisible(false);
            comEndDateLabel.setVisible(false);

        } else if ("Company End Date".equals(String.valueOf(fieldDdlb.getValue()))) {
            valueDdlb.setVisible(false);
            valueDdlbLabel.setVisible(false);
            startPeriod.setVisible(false);
            comStartDateLabel.setVisible(false);
            endPeriod.setVisible(true);
            comEndDateLabel.setVisible(true);
        }

        LOGGER.debug("FieldDdlb ValueChangeEvent ends ");
    }

    @UiHandler("populate")
    public void populateButtonLogic(Button.ClickEvent event) {
        final CommmonLogic logic = new CommmonLogic();
        String fieldValue = String.valueOf(fieldDdlb.getValue());

        if (!this.logic.isAnyRecordSelected(session.getUserId(), session.getSessionId(), screenName)) {
            AbstractNotificationUtils.getErrorNotification("Mass Update Error", "Please select at least one contract to apply the Mass Update to");
            return;
        }
        if (Constants.NULL.equals(fieldValue)) {
            AbstractNotificationUtils.getErrorNotification("Mass Update Error", "Please select a Field to Mass Update");
            return;
        }

        Object massUpdateValue = StringUtils.EMPTY;

        if ("Status".equals(String.valueOf(fieldDdlb.getValue()))) {
            if (!String.valueOf(valueDdlb.getValue()).isEmpty() && Constants.NULL.equals(String.valueOf(valueDdlb.getValue()))) {
                AbstractNotificationUtils.getErrorNotification("Mass Update Error", "Please enter any value to Mass Update.");
                return;
            }
            massUpdateValue = valueDdlb.getValue();
        } else if ("Company Start Date".equals(String.valueOf(fieldDdlb.getValue()))) {

            if (!String.valueOf(startPeriod.getValue()).isEmpty() && Constants.NULL.equals(String.valueOf(startPeriod.getValue()))) {
                AbstractNotificationUtils.getErrorNotification("Mass Update Error", "Please enter a Start Date to Mass Update.");
                return;
            }
            String endDate = StringUtils.EMPTY;
            if (session.getModuleName().equals(TRANSFER_TRADING_PARTNER.getConstant()) && TAB_TRANSFER_CONTRACT.getConstant().equals(screenName)) {
                endDate = CommonLogic.getDateForSubmittedContract(session.getSessionId(), false, false, true);

            }
            if (endDate != StringUtils.EMPTY) {
                SimpleDateFormat inputDateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

                String startdate = StringUtils.EMPTY;
                startdate = String.valueOf(inputDateFormatter.format(startPeriod.getValue()));

                if (!ContractSelectionLogic.isTCStartDateGreaterThanEndDate(session.getUserId(), session.getSessionId(), endDate, startdate)) {
                    AbstractNotificationUtils.getErrorNotification("Mass Update Error", "The Start Date must come after the End Date in current contract. Please reenter the Start Date.");
                    return;
                }
            }
            massUpdateValue = startPeriod.getValue();
        } else if ("Company End Date".equals(String.valueOf(fieldDdlb.getValue()))) {
            if (!String.valueOf(endPeriod.getValue()).isEmpty() && Constants.NULL.equals(String.valueOf(endPeriod.getValue()))) {
                AbstractNotificationUtils.getErrorNotification("Mass Update Error", "Please enter an End Date to Mass Update.");
                return;
            }

            SimpleDateFormat dateFormater = new SimpleDateFormat("MM-dd-yyyy");
            String endDate = dateFormater.format((Date) endPeriod.getValue());

            if (ContractSelectionLogic.isStartDateGreaterThanEndDate(session.getUserId(), session.getSessionId(), endDate)) {
                AbstractNotificationUtils.getErrorNotification("Mass Update Error", "The End Date must come after the Start Date. Please reenter the End Date.");
                return;
            }
            massUpdateValue = endPeriod.getValue();
        }

        final Object massUpdateValueTemp = massUpdateValue;
        if (logic.isValuesPresentAlready(String.valueOf(fieldDdlb.getValue()), session.getUserId(), session.getSessionId(), screenName)) {
            new AbstractNotificationUtils() {
                public void noMethod() {

                }

                @Override
                /**
                 * The method is triggered when Yes button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                public void yesMethod() {
                    performMassUpdate(String.valueOf(fieldDdlb.getValue()), massUpdateValueTemp);
                }

            }.getConfirmationMessage("Mass Update Confirmation", "There are values in these fields already.\n Are you sure you want to replace them?");
        } else {
            performMassUpdate(String.valueOf(fieldDdlb.getValue()), massUpdateValueTemp);
        }

    }

    private void performMassUpdate(String fieldValue, Object massUpdateValue) {
        LOGGER.debug("Entering performMassUpdate");
        final CommmonLogic logic = new CommmonLogic();
        logic.massUpdate(fieldValue, session.getUserId(), session.getSessionId(), screenName, massUpdateValue);
        List<ContractResultDTO> containerList = pagedContainer.getItemIds();

        isTableUpdate = true;
        for (ContractResultDTO dto : containerList) {
            if (dto.getCheckRecord()) {
                if ("Status".equals(fieldValue)) {
                    HelperDTO helper = new HelperDTO();
                    helper.setId((Integer) massUpdateValue);
                    helper.setDescription(valueDdlb.getItemCaption(massUpdateValue));
                    pagedTable.getContainerProperty(dto, "status").setValue(helper);

                } else if ("Company Start Date".equals(fieldValue)) {
                    pagedTable.getContainerProperty(dto, "compStartDate").setValue(massUpdateValue);

                } else if ("Company End Date".equals(fieldValue)) {
                    pagedTable.getContainerProperty(dto, "compEndDate").setValue(massUpdateValue);
                }

            }
        }
        isTableUpdate = false;
        LOGGER.debug("Exiting performMassUpdate");
    }

    @UiHandler("submitBtn")
    public void submitButtonlogic(Button.ClickEvent event) {
        submitAndNextLogic(false);
    }

    ExtCustomTable.ColumnCheckListener checkListener = new ExtCustomTable.ColumnCheckListener() {
        @Override
        public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
            if (event.isChecked()) {

                checkClearAll(event.isChecked());
            }
        }

    };

    public void checkClearAll(boolean checkValue) {

        List<ContractResultDTO> containerList = pagedContainer.getItemIds();

        for (ContractResultDTO dto : containerList) {
            pagedTable.getContainerProperty(dto, "checkRecord").setValue(checkValue);
        }
    }

    public void refreshContractSelectionTable() {
        LOGGER.debug("Contract Selection Refreshed");
        ContractTableLogic.setCurrentPage(ContractTableLogic.getCurrentPage());
    }

    public boolean isSummaryRefreshed() {
        return summaryRefreshed;
    }

    public void setSummaryRefreshed(boolean summaryRefreshed) {
        this.summaryRefreshed = summaryRefreshed;
    }

    @UiHandler("tpResetBtn")
    public void tpResetBtnLogic(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            public void noMethod() {
                // do nothing
            }

            @Override
            /**
             * The method is triggered when Yes button of the message box is
             * pressed .
             *
             * @param buttonId The buttonId of the pressed button.
             */
            public void yesMethod() {
                try {
                    contractNo.setValue(StringUtils.EMPTY);
                    contractName.setValue(StringUtils.EMPTY);
                    contractHolder.setValue(StringUtils.EMPTY);
                    rebateScheduleId.setValue(StringUtils.EMPTY);
                    rebateScheduleName.setValue(StringUtils.EMPTY);
                    rebateScheduleNo.setValue(StringUtils.EMPTY);
                    rebateScheduleAlias.setValue(StringUtils.EMPTY);
                    marketType.setValue(Constants.SELECT_ONE);
                    rebateScheduleType.setValue(Constants.SELECT_ONE);
                    rarCategory.setValue(Constants.SELECT_ONE);
                    rebateScheduleCategory.setValue(Constants.SELECT_ONE);
                    rebateProgramType.setValue(Constants.SELECT_ONE);
                    cfpNo.setValue(StringUtils.EMPTY);
                    ifpNo.setValue(StringUtils.EMPTY);
                    psNo.setValue(StringUtils.EMPTY);
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        }.getConfirmationMessage("Reset Confirmation", "Are you sure you want to reset the Search values?");
    }

    @UiHandler("resetBtn")
    public void resetBtnLogic(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            public void noMethod() {
                // do nothing
            }

            @Override
            /**
             * The method is triggered when Yes button of the message box is
             * pressed .
             *
             * @param buttonId The buttonId of the pressed button.
             */
            public void yesMethod() {
                try {

                    pagedTable.resetFilters();
                    componentInformationTable.resetFilters();
                    contractSeletion.setReset(true);
                    CommmonLogic.resetContractListView(session.getSessionId(), screenName);

                    ContractTableLogic.loadSetData(contractSeletion, session);
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        }.getConfirmationMessage("Reset Confirmation", "Are you sure you want to reset the values in the Available Contracts list view?");
    }

    @UiHandler("excelBtn")
    public void excelExport(Button.ClickEvent event) {
        try {
            createWorkSheet("Available_Contracts", pagedTable);

        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public void createWorkSheet(String fileName, ExtFilterTable resultTable) throws ParseException, SystemException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, PortalException {

        long recordCount = 0;
        CommmonLogic logic = new CommmonLogic();
        if (resultTable.size() != 0) {
            recordCount = logic.buildContractCountQuery(contractSeletion, session.getUserId(), session.getSessionId(), null);
        }
        isComponentInformationExport = false;
        String[] headers;
        if (!TRADING_PARTNER_REMOVE.getConstant().equals(session.getModuleName())) {
            headers = Constants.EXCEL_CONTRACT_SELECTION_HEADERS;
        } else {
            headers = Constants.EXCEL_REMOVE_TP_SELECTION_HEADERS;
        }
        ExcelExportforBB.createWorkSheet(headers, recordCount, this, UI.getCurrent(), fileName);

    }

    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) {
        LOGGER.debug("Entering createWorkSheetContent with start " + start + " end " + end);
        try {
            CommmonLogic logic = new CommmonLogic();
            List searchList = new ArrayList();
            Object[] visibleColumns = new Object[0];
            if (end > 0) {
                if (!isComponentInformationExport) {
                    searchList = logic.getContractSelectionResults(logic.buildContractSearchQuery(contractSeletion, session.getUserId(), session.getSessionId(),
                            0, end, ContractTableLogic.getFilters()), true);

                    if (!TRADING_PARTNER_REMOVE.getConstant().equals(session.getModuleName())) {
                        visibleColumns = Constants.EXCEL_CONTRACT_SELECTION_COLUMNS;
                    } else {
                        visibleColumns = Constants.EXCEL_REMOVE_TP_CONTRACT_SELECTION_COLUMNS;
                    }
                } else {
                    ContractSelectionLogic contractSelectionLogic = new ContractSelectionLogic();
                    searchList = contractSelectionLogic.getComponentInformation(excelSelectionValue, excelComponentId, 0, end, tablelogic.getFilters());
                    visibleColumns = componentInformationTable.getVisibleColumns();
                }
            }

            ExcelExportforBB.createFileContent(visibleColumns, searchList, printWriter);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    @UiHandler("excelBtnInfo")
    public void infoExcelExport(Button.ClickEvent event) {
        try {
            if (componentInformationContainer.size() > 0) {
                createWorkSheetInfo("Component_Information", componentInformationTable);
            }
        } catch (Exception e) {
            LOGGER.error(e + "at excel export");
        }
    }

    public void createWorkSheetInfo(String fileName, ExtFilterTable resultTable) throws SystemException, PortalException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        long recordCount = 0;
        if (resultTable.size() != 0) {
            ContractSelectionLogic logic = new ContractSelectionLogic();
            recordCount = logic.getComponentInformationCount(excelSelectionValue, excelComponentId, null);
        }
        isComponentInformationExport = true;
        Object[] headers = resultTable.getColumnHeaders();
        headers = ArrayUtils.removeElement(headers, StringUtils.EMPTY);
        ExcelExportforBB.createWorkSheet((String[]) headers, recordCount, this, UI.getCurrent(), fileName);

    }

    public void changeComponents(String componentSelection) {

        if (componentSelection.equals(COMPANY_FAMILY_PLAN.getConstant())) {
            cIdLabel.setCaption("CFP " + ID);
            cNumberLabel.setCaption("CFP " + NUMBER);
            cNameLabel.setCaption("CFP " + NAME);
            setComponentInformationVisibility(false);
            excelName = "Company Family Plan Information";
            excelResultBean.removeAllItems();
            componentInformation.clear();
        } else if (componentSelection.equals(ITEM_FAMILY_PLAN.getConstant())) {
            cIdLabel.setCaption("IFP " + ID);
            cNumberLabel.setCaption("IFP " + NUMBER);
            cNameLabel.setCaption("IFP " + NAME);
            setComponentInformationVisibility(false);
            excelName = "Item Family Plan Information";
            excelResultBean.removeAllItems();
            componentInformation.clear();
        } else if (componentSelection.equals(PRICE_SCHEDULE.getConstant())) {
            cIdLabel.setCaption("PS " + ID);
            cNumberLabel.setCaption("PS " + NUMBER);
            cNameLabel.setCaption("PS " + NAME);
            setComponentInformationVisibility(false);
            excelName = "Price Schedule Information";
            excelResultBean.removeAllItems();
            componentInformation.clear();
        } else if (componentSelection.equals(REBATE_SCHEDULE.getConstant())) {
            cIdLabel.setCaption("RS " + ID);
            cNumberLabel.setCaption("RS " + NUMBER);
            cNameLabel.setCaption("RS " + NAME);
            setComponentInformationVisibility(true);
            excelName = "Rebate Schedule Information";
            excelResultBean.removeAllItems();
            componentInformation.clear();
        }

    }

    private void setComponentInformationVisibility(boolean value) {
        basisLabel.setVisible(value);
        basis.setVisible(value);

        rebateFrequencyLabel.setVisible(value);
        rebateFrequency.setVisible(value);

        rsTypeLabel.setVisible(value);
        rsType.setVisible(value);

        rsProgramTypeLabel.setVisible(value);
        rsProgramType.setVisible(value);

        rsCategoryLabel.setVisible(value);
        rsCategory.setVisible(value);

        paymentFrequencyLabel.setVisible(value);
        paymentFrequency.setVisible(value);

        rebatePlanLevelLabel.setVisible(value);
        rebatePlanLevel.setVisible(value);
    }

    @UiHandler("contractHolder")
    public void contractHolderLookup(CustomTextField.ClickEvent event) {

        final TPContractHolderLookUp contractHolderLookUpWindow = new TPContractHolderLookUp("Contract Holder", contractHolder, StringUtils.EMPTY);
        contractHolderLookUpWindow.setWidth("1320px");
        contractHolderLookUpWindow.setHeight("830px");
        UI.getCurrent().addWindow(contractHolderLookUpWindow);
    }

    /**
     * Alternate contract lookup.
     *
     * @param event the event
     */
    @UiHandler("cfpNo")
    public void cfpLookup(CustomTextField.ClickEvent event) {
        LOGGER.debug("Entered cfpLookup method");
        CfpLookUp cfpLookup = new CfpLookUp(cfpNo, true);
        getUI().addWindow(cfpLookup);
        LOGGER.debug("Ending cfpLookup method");
    }

    /**
     * Alternate contract lookup.
     *
     * @param event the event
     */
    @UiHandler("ifpNo")
    public void ifpLookup(CustomTextField.ClickEvent event) {
        LOGGER.debug("Entered ifpLookup method");
        IfpLookUp ifpLookup = new IfpLookUp(ifpNo, true);
        getUI().addWindow(ifpLookup);
        LOGGER.debug("Ending ifpLookup method");
    }

    /**
     * Alternate contract lookup.
     *
     * @param event the event
     */
    @UiHandler("psNo")
    public void psLookup(CustomTextField.ClickEvent event) {
        LOGGER.debug("Entered psLookup method");
        PsLookUp psLookup = new PsLookUp(psNo, true);
        getUI().addWindow(psLookup);
        LOGGER.debug("Ending psLookup method");
    }

    @UiHandler("allCustomers")
    public void allCustomers(Button.ClickEvent event) {
        LOGGER.debug("Entered allCustomers method");
        AllCustomers psLookup = new AllCustomers(session.getCompanyMasterSids());
        getUI().addWindow(psLookup);
        LOGGER.debug("Ending allCustomers method");
    }

    public void configureAddTpScreen() {
        if (!TRADING_PARTNER_UPDATE.getConstant().equalsIgnoreCase(session.getModuleName().trim())) {
            fieldDdlb.addItem("Status");
        }
        fieldDdlb.addItem("Company Start Date");
    }

    public boolean submitAndNextLogic(final boolean isNextButtonClicked) {
        LOGGER.debug("Contract selection submitAndNextLogic initiated");
        List<String> checkData = logic.getSubmitValidationData(session.getUserId(), session.getSessionId(), screenName, "check");
        if (checkData.size() > 0 && checkData.get(0).equals(Constants.ZEROSTRING)) {
            AbstractNotificationUtils.getErrorNotification("Submit error", "Please select any record to submit");
            return false;
        }
        List<String> contractLists = logic.getSubmitValidationData(session.getUserId(), session.getSessionId(), screenName, "status");
        if (((session.getModuleName().equals(ADD_TRADING_PARTNER.getConstant())) || (session.getModuleName().equals(TRADING_PARTNER_UPDATE.getConstant()))) && contractLists != null && !contractLists.isEmpty()) {
            AbstractNotificationUtils.getErrorNotification("Submit error", "Please select Status for the following Contract records: " + CommonUtils.CollectionToString(contractLists, false));
            return false;
        }
        if (session.getModuleName().equals(TRANSFER_TRADING_PARTNER.getConstant())) {
            if (contractLists != null && !contractLists.isEmpty()) {
                AbstractNotificationUtils.getErrorNotification("Submit error", "Please enter a Status and Start Date for the following Contracts: " + CommonUtils.CollectionToString(contractLists, false) + ". Then try again.");
                return false;
            }
            if (!singleContractCheck("single contract check for customere check")) {
                AbstractNotificationUtils.getErrorNotification("One Contract can be selected", "Please select only one contract to proceed.");
                return false;
            }
        }

        List<String> contractList = logic.getSubmitValidationData(session.getUserId(), session.getSessionId(), screenName, Constants.START_DATE);
        if (session.getModuleName().equals(TRANSFER_TRADING_PARTNER.getConstant())) {
            if (contractList != null && !contractList.isEmpty()) {
                AbstractNotificationUtils.getErrorNotification("Submit error", "Please enter a Status and Start Date for the following Contracts: " + CommonUtils.CollectionToString(contractList, false) + ". Then try again.");
                return false;
            }
//        } else {
//            if (contractList != null && !contractList.isEmpty()) {
//                AbstractNotificationUtils.getErrorNotification("Submit error", "Please enter an Start Date for the following Contract records: " + CommonUtils.CollectionToString(contractList, false));
//                return false;
//            }
        } else if (contractList != null && !contractList.isEmpty()) {
            AbstractNotificationUtils.getErrorNotification("Submit error", "Please enter an Start Date for the following Contract records: " + CommonUtils.CollectionToString(contractList, false));
            return false;
        }
        if ((session.getModuleName().equals(TRADING_PARTNER_UPDATE.getConstant())) || (session.getModuleName().equals(TRANSFER_TRADING_PARTNER.getConstant())
                || session.getModuleName().equals(PROJECTION_DETAILS_TRANSFER.getConstant())) && TAB_CURRENT_CONTRACT.getConstant().equals(screenName)) {
            contractList = logic.getSubmitValidationData(session.getUserId(), session.getSessionId(), screenName, Constants.END_DATE);
            if (contractList != null && !contractList.isEmpty()) {
                AbstractNotificationUtils.getErrorNotification("Submit error", "Please enter an End Date for the following Contract records: " + CommonUtils.CollectionToString(contractList, false));
                return false;
            }
        }

        if ((session.getModuleName().equals(ADD_TRADING_PARTNER.getConstant())) || (session.getModuleName().equals(TRADING_PARTNER_UPDATE.getConstant()))) {
            CommmonLogic commonLogic = new CommmonLogic();
            String contractCustomer = StringUtils.EMPTY;

            if (!commonLogic.getActualPaidList(session, "count").get(0).getTempVarOne().equals(Constants.ZEROSTRING)) {

                List<ContractResultDTO> contractCustomerList = commonLogic.getActualPaidList(session, "contractCustomer");

                for (ContractResultDTO dto : contractCustomerList) {
                    if (contractCustomer.equals(StringUtils.EMPTY)) {
                        contractCustomer = dto.getTempVarOne() + " -- " + dto.getTempVarTwo();
                    } else {
                        contractCustomer = dto.getTempVarOne() + " -- " + dto.getTempVarTwo() + ",";
                    }
                }
                List<ContractResultDTO> tempDateList = commonLogic.getActualPaidList(session, "tempDate");
                String tempDate = StringUtils.EMPTY;
                for (ContractResultDTO dto : tempDateList) {
                    if (tempDate.equals(StringUtils.EMPTY)) {
                        tempDate = dto.getTempVarOne() + " -- " + dto.getTempVarTwo();
                    } else {
                        tempDate = dto.getTempVarOne() + " -- " + dto.getTempVarTwo() + ",";
                    }
                }
                List<ContractResultDTO> tempActualDateList = commonLogic.getActualPaidList(session, "tempActualDate");
                String tempActualDate = StringUtils.EMPTY;
                for (ContractResultDTO dto : tempActualDateList) {
                    tempActualDate = dto.getTempVarOne() + " -- " + dto.getTempVarTwo();
                }
                AbstractNotificationUtils.getErrorNotification("Submit error", "You cannot proceed with this Update.\n The following Contract/Customer record: " + contractCustomer + " has actuals \n that should be excluded with the following Company Start/End Date " + tempDate + ".\n The following Contract/Customer record: " + contractCustomer + " \n has actual payments that exist between these time periods: " + tempActualDate + " . \n"
                        + "Please make sure the updated Company Start and End Dates do not exclude any of these periods.  ");
                return false;
            }
        }

        if (session.getModuleName().equals(TRANSFER_TRADING_PARTNER.getConstant()) || session.getModuleName().equals(PROJECTION_DETAILS_TRANSFER.getConstant())) {
            if (TAB_CURRENT_CONTRACT.getConstant().equals(screenName)) {
                int fromProjectionId = CommonLogic.getProjectionIdForCheckedContract(session.getSessionId(), screenName, true);
                String fromFlavour = CommonLogic.getForecastFlavour(fromProjectionId);
                int toContractSid = CommonLogic.getCheckedContractSid(session.getSessionId(), screenName, true);
                String fromCustomerEndDate = CommonLogic.getDateForCheckedContract(session.getSessionId(), false, false, true);
                if (!logic.isSalesPresentAlready(fromProjectionId, fromCustomerEndDate, session.getCompanyMasterSids(), toContractSid, fromFlavour)) {
                    AbstractNotificationUtils.getAlertNotification("No Values to Transfer", "The Contract/Company combination do not have any projected sales or units to transfer.");
                    return false;
                } else {
                    submition(isNextButtonClicked);
                }
            } else {
                LOGGER.debug("Entering Transfer submit next checks");
                logic.updateSubmitFlagWithoutCheckRecord(session.getModuleName(), screenName, session.getUserId(), session.getSessionId(), true);
                checkNonAssociatedProducts(isNextButtonClicked, session.getModuleName().equals(PROJECTION_DETAILS_TRANSFER.getConstant()));
            }
        } else {
            submition(isNextButtonClicked);
        }

        LOGGER.debug("Contract selection submitAndNextLogic ends ");
        return true;
    }

    private void checkNonAssociatedProducts(final boolean isNextButtonClicked, final boolean isProjectionDetailsTransferModule) {
        LOGGER.debug("checkNonAssociatedProducts starts ");
        String sourceContract = CommonLogic.getSelectedContractName(session.getSessionId(), false);
        String destinationContract = CommonLogic.getSelectedContractName(session.getSessionId(), true);

        if (isProjectionDetailsTransferModule) {
            int sourceContractId = CommonLogic.getSelectedContractSid(session.getSessionId(), false);
            int destContractId = CommonLogic.getSelectedContractSid(session.getSessionId(), true);
            int sourceProjectionId = CommonLogic.getProjectionIdForSubmittedContract(session.getSessionId(), false);
            int destProjectionId = CommonLogic.getProjectionIdForSubmittedContract(session.getSessionId(), true);

            if (logic.isHavingAnyCommonProducts(session.getCompanyMasterSids(), session.getPhCompanyMasterSids(), sourceProjectionId, destProjectionId, sourceContractId, destContractId)) {

                new AbstractNotificationUtils() {
                    public void noMethod() {

                        logic.updateSubmitFlagWithoutCheckRecord(session.getModuleName(), screenName, session.getUserId(), session.getSessionId(), false);
                        ContractTableLogic.handleFilterChange();
                    }

                    @Override
                    public void yesMethod() {
                        checkTimeGap(isNextButtonClicked);
                    }
                }.getConfirmationMessage("Transfer confirmation", "You are about to transfer Projection Details "
                        + "from " + sourceContract + " to " + destinationContract + ". \n Some of the products are not associated.\n"
                        + "Are you sure you want to continue with this transfer process? ");
            } else {
                logic.updateSubmitFlagWithoutCheckRecord(session.getModuleName(), screenName, session.getUserId(), session.getSessionId(), false);
                ContractTableLogic.handleFilterChange();
                LOGGER.debug("No common products");
            }
        } else if (isHavingDifferentProducts()) {
            String companies = CommonLogic.getSelectedCompanyNames(session.getCompanyMasterSids());
            new AbstractNotificationUtils() {
                public void noMethod() {
                    logic.updateSubmitFlagWithoutCheckRecord(session.getModuleName(), screenName, session.getUserId(), session.getSessionId(), false);
                    ContractTableLogic.handleFilterChange();
                }

                @Override
                public void yesMethod() {
                    checkTimeGap(isNextButtonClicked);
                }
            }.getConfirmationMessage("Transfer confirmation", "You are about to transfer " + companies + " "
                    + "from " + sourceContract + " to " + destinationContract + ". \n The following products are not associated to  " + destinationContract + " : " + nonAssociatedProducts + ".\n"
                    + "Are you sure you want to continue with this transfer process? ");
        } else {
            checkTimeGap(isNextButtonClicked);
        }

        LOGGER.debug("checkNonAssociatedProducts ends ");
    }

    private void checkTimeGap(final boolean isNextButtonClicked) {
        LOGGER.debug("Entering checkTimeGap");
        if (isTimeGapPresent()) {
            new AbstractNotificationUtils() {
                public void noMethod() {
                    logic.updateSubmitFlagWithoutCheckRecord(session.getModuleName(), screenName, session.getUserId(), session.getSessionId(), false);
                }

                @Override
                public void yesMethod() {
                    checkProjectionSales(isNextButtonClicked);
                }
            }.getConfirmationMessage("Time Gap confirmation", "Are you sure you want to proceed with this " + timeGap + " months time gap? ");
        } else {
            checkProjectionSales(isNextButtonClicked);
        }
        LOGGER.debug("Exiting checkTimeGap");
    }

    private void checkProjectionSales(final boolean isNextButtonClicked) {
        LOGGER.debug("Entering checkProjectionSales");
        int toProjectionId = CommonLogic.getProjectionIdForSubmittedContract(session.getSessionId(), true);
        String toFlavour = CommonLogic.getForecastFlavour(toProjectionId);
        int toContractSid = CommonLogic.getSelectedContractSid(session.getSessionId(), true);
        String toCustomerStartDate = CommonLogic.getDateForCheckedContract(session.getSessionId(), true, true, false);
        if (logic.isSalesPresentAlready(toProjectionId, toCustomerStartDate, session.getCompanyMasterSids(), toContractSid, toFlavour)) {
            new AbstractNotificationUtils() {
                public void noMethod() {
                    logic.updateSubmitFlagWithoutCheckRecord(session.getModuleName(), screenName, session.getUserId(), session.getSessionId(), false);
                }

                @Override
                public void yesMethod() {
                    submition(isNextButtonClicked);
                }
            }.getConfirmationMessage("Transfer confirmation", "Are you sure want to override this preexisting projection data for the contract/Company intersection? ");

        } else {
            submition(isNextButtonClicked);
        }
        LOGGER.debug("Exiting checkProjectionSales");
    }

    private boolean isTimeGapPresent() {
        LOGGER.debug("Inside isTimeGapPresent");
        String endDate = CommonLogic.getDateForSubmittedContract(session.getSessionId(), false, false, true);
        Date maxEndDate = new Date(CommonLogic.convertDateFormat(endDate, "yyyy-MM-dd HH:mm:ss.SSS", "MM/dd/yyyy"));

        String startDate = CommonLogic.getDateForCheckedContract(session.getSessionId(), true, true, false);
        Date minStartDate = new Date(CommonLogic.convertDateFormat(startDate, "yyyy-MM-dd HH:mm:ss.SSS", "MM/dd/yyyy"));

        Calendar startCalendar = new GregorianCalendar();
        startCalendar.setTime(maxEndDate);
        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(minStartDate);

        int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        int diffMonth = diffYear * NumericConstants.TWELVE + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
        timeGap = diffMonth - NumericConstants.TWO;
        LOGGER.debug("Exiting isTimeGapPresent");
        if (timeGap > 0) {
            return true;
        } else {
            return false;
        }

    }

    private void submition(boolean isNextButtonClicked) {
        LOGGER.debug("Inside Submition");
        summaryRefreshed = true;
        logic.updateSubmitFlag(session.getModuleName(), screenName, session.getUserId(), session.getSessionId(), true);
        ContractTableLogic.setCurrentPage(ContractTableLogic.getCurrentPage());
        if (!isNextButtonClicked) {
            AbstractNotificationUtils.getAlertNotification("Submit Details", "Selected Contract Holder has been submitted successfully.");
        }
        changeTab();
        LOGGER.debug("Exiting submition");
    }

    private boolean isHavingDifferentProducts() {
        LOGGER.debug("Inside isHavingDifferentProducts");
        List<String> nonAssociatedProductsList = logic.getNonAssociatedProducts(session.getSessionId());
        if (nonAssociatedProductsList != null && !nonAssociatedProductsList.isEmpty()) {
            nonAssociatedProducts = CommonUtils.CollectionToString(nonAssociatedProductsList, false);
            LOGGER.debug("Exiting isHavingDifferentProducts");
            return true;
        } else {
            LOGGER.debug("Exiting isHavingDifferentProducts");
            return false;
        }
    }

    private void changeTab() {
        if (session.getModuleName().equals(TRADING_PARTNER_REMOVE.getConstant())) {
            removeTpForm.getTabSheet().setSelectedTab(removeTpForm.getTabPosition() + 1);

        } else if (session.getModuleName().equals(ADD_TRADING_PARTNER.getConstant())) {
            addTpForm.getTabSheet().setSelectedTab(addTpForm.getTabPosition() + 1);

        } else if (session.getModuleName().equals(TRADING_PARTNER_UPDATE.getConstant())) {
            updateTPForm.getTabSheet().setSelectedTab(updateTPForm.getTabPosition() + 1);

        } else {
            transferTpForm.getTabSheet().setSelectedTab(transferTpForm.getTabPosition() + 1);
        }
    }

    private boolean singleContractCheck(String queryName) {
        List input = new ArrayList<>();
        input.add(session.getUserId());
        input.add(session.getSessionId());
        input.add(screenName);
        List<Object[]> contractList = ItemQueries.getItemData(input, queryName, null);
        if (AbstractLogic.getCount(contractList) == 1) {
            return true;
        }
        return false;
    }

    private void configureSecurityPermissionsAddTransfer() {
        try {
            String userId = String.valueOf(VaadinSession.getCurrent().getAttribute("userId"));
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "GCM-Customer Management", "Add Customer", "ContractSelectionTab");
            searchBtn.setVisible(CommonLogic.isButtonVisibleAccess("searchBtn", functionHM));
            tpResetBtn.setVisible(CommonLogic.isButtonVisibleAccess("tpResetBtn", functionHM));
            populateBtn.setVisible(CommonLogic.isButtonVisibleAccess("populateBtn", functionHM));
            excelBtn.setVisible(CommonLogic.isButtonVisibleAccess("excelBtn", functionHM));
            submitBtn.setVisible(CommonLogic.isButtonVisibleAccess("submitBtn", functionHM));
            resetBtn.setVisible(CommonLogic.isButtonVisibleAccess("resetBtn", functionHM));
            excelBtnInfo.setVisible(CommonLogic.isButtonVisibleAccess("excelBtnInfo", functionHM));
        }
            catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    private void configureSecurityPermissions() {
        try {
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(String.valueOf(session.getUserId()), "GCM-Customer Management", "UpdateCustomer", "CurrentContractTab");
            searchBtn.setVisible(CommonLogic.isButtonVisibleAccess("searchBtn", functionHM));
            tpResetBtn.setVisible(CommonLogic.isButtonVisibleAccess("tpResetBtn", functionHM));
            populateBtn.setVisible(CommonLogic.isButtonVisibleAccess("populateBtn", functionHM));
            submitBtn.setVisible(CommonLogic.isButtonVisibleAccess("submitBtn", functionHM));
            resetBtn.setVisible(CommonLogic.isButtonVisibleAccess("resetBtn", functionHM));
            NextBtn.setVisible(CommonLogic.isButtonVisibleAccess("NextBtn", functionHM));
            closeBtn.setVisible(CommonLogic.isButtonVisibleAccess("closeBtn", functionHM));
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    private void configureSecurityPermissionsForTransfer(boolean isTransfer) {
        try {
            if (!isTransfer) {
                String userId = String.valueOf(VaadinSession.getCurrent().getAttribute("userId"));
                Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "GCM-Customer Management", "Transfer Customer", "CurrentContractTab");
                searchBtn.setVisible(CommonLogic.isButtonVisibleAccess("searchBtn", functionHM));
                tpResetBtn.setVisible(CommonLogic.isButtonVisibleAccess("tpResetBtn", functionHM));
                populateBtn.setVisible(CommonLogic.isButtonVisibleAccess("populateBtn", functionHM));
                excelBtn.setVisible(CommonLogic.isButtonVisibleAccess("excelBtn", functionHM));
                submitBtn.setVisible(CommonLogic.isButtonVisibleAccess("submitBtn", functionHM));
                resetBtn.setVisible(CommonLogic.isButtonVisibleAccess("resetBtn", functionHM));
                excelBtnInfo.setVisible(CommonLogic.isButtonVisibleAccess("excelBtnInfo", functionHM));                
            } else {
                String userId = String.valueOf(VaadinSession.getCurrent().getAttribute("userId"));
                Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "GCM-Customer Management", "Transfer Customer", "TranferContractTab");
                searchBtn.setVisible(CommonLogic.isButtonVisibleAccess("searchBtn", functionHM));
                tpResetBtn.setVisible(CommonLogic.isButtonVisibleAccess("tpResetBtn", functionHM));
                populateBtn.setVisible(CommonLogic.isButtonVisibleAccess("populateBtn", functionHM));
                excelBtn.setVisible(CommonLogic.isButtonVisibleAccess("excelBtn", functionHM));
                submitBtn.setVisible(CommonLogic.isButtonVisibleAccess("submitBtn", functionHM));
                resetBtn.setVisible(CommonLogic.isButtonVisibleAccess("resetBtn", functionHM));
                excelBtnInfo.setVisible(CommonLogic.isButtonVisibleAccess("excelBtnInfo", functionHM));

            }

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }
    private void configureSecurityPermissionsRemoveTransfer() {
        try {
            String userId = String.valueOf(VaadinSession.getCurrent().getAttribute("userId"));
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "GCM-Customer Management", "Remove Customer", "ContractSelectionTab");
            searchBtn.setVisible(CommonLogic.isButtonVisibleAccess("searchBtn", functionHM));
            tpResetBtn.setVisible(CommonLogic.isButtonVisibleAccess("tpResetBtn", functionHM));
            populateBtn.setVisible(CommonLogic.isButtonVisibleAccess("populateBtn", functionHM));
            excelBtn.setVisible(CommonLogic.isButtonVisibleAccess("excelBtn", functionHM));
            submitBtn.setVisible(CommonLogic.isButtonVisibleAccess("submitBtn", functionHM));
            resetBtn.setVisible(CommonLogic.isButtonVisibleAccess("resetBtn", functionHM));
            excelBtnInfo.setVisible(CommonLogic.isButtonVisibleAccess("excelBtnInfo", functionHM));
        }
            catch (Exception ex) {
            LOGGER.error(ex);
        }
    }
}
