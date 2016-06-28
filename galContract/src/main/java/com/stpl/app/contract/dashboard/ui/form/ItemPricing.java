/**
 *
 */
package com.stpl.app.contract.dashboard.ui.form;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyBeanItemContainer;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

import com.stpl.addons.tableexport.TemporaryFileDownloadResource;
import com.stpl.app.contract.common.dto.SessionDTO;
import com.stpl.app.contract.common.util.CommonUtil;
import com.stpl.app.contract.common.util.HelperListUtil;
import com.stpl.app.contract.contractheader.logic.ContractHeaderLogic;
import com.stpl.app.contract.contractheader.util.CommonUtils;
import com.stpl.app.contract.contractheader.util.UIUtils;
import com.stpl.app.contract.dashboard.dto.ItemDetailsGenerator;
import com.stpl.app.contract.dashboard.dto.NepFormulaLookUpDTO;
import com.stpl.app.contract.dashboard.dto.PSFilterGenerator;
import com.stpl.app.contract.dashboard.dto.PriceScheduleDto;
import com.stpl.app.contract.dashboard.dto.PricingHistoryDto;
import com.stpl.app.contract.dashboard.dto.TempPricingDTO;
import com.stpl.app.contract.dashboard.logic.DashBoardLogic;
import com.stpl.app.contract.dashboard.logic.ItemDetailsTableLogic;
import com.stpl.app.contract.dashboard.logic.PriceProtectionTableLogic;
import com.stpl.app.contract.dashboard.logic.TempViewTableLogic;
import com.stpl.app.contract.dashboard.ui.lazyload.LazyLoadCriteria;
import com.stpl.app.contract.dashboard.ui.lazyload.PriceTypeCriteria;
import com.stpl.app.contract.dashboard.ui.lazyload.PriceTypeLazyContainer;
import com.stpl.app.contract.dashboard.ui.lookup.NetPriceTypeFormulaLookup;
import com.stpl.app.contract.dashboard.ui.lookup.NetSalesFormulaLookup;
import com.stpl.app.contract.dashboard.ui.lookup.PsParentLookup;
import com.stpl.app.contract.dashboard.util.ContractUtils;
import com.stpl.app.contract.dashboard.util.ExcelExportUtil;
import com.stpl.app.contract.global.dto.ItemMasterDTO;
import com.stpl.app.contract.global.dto.ItemPricingGenerator;
import com.stpl.app.contract.global.dto.PriceProtectionFieldFactory;
import com.stpl.app.contract.global.dto.VwContractPriceInfoDTO;
import com.stpl.app.contract.global.logic.IfpLogic;
import com.stpl.app.contract.ui.ErrorfulFieldGroup;
import com.stpl.app.contract.util.AbstractNotificationUtils;
import com.stpl.app.contract.util.CommonUIUtils;
import com.stpl.app.contract.util.Constants;
import com.stpl.app.contract.util.ErrorCodeUtil;
import com.stpl.app.contract.util.ResponsiveUtils;
import com.stpl.app.security.impl.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.CustomePagedFilterTable;
import com.stpl.app.contract.util.CHFunctionNameUtils;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodes;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.HelperDTO;
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
import com.vaadin.data.validator.RegexpValidator;
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
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.Date;
import org.apache.commons.lang.ArrayUtils;
import org.asi.ui.customcombobox.CustomComboBox;
import org.asi.ui.customtextfield.CustomTextField;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.vaadin.addons.lazycontainer.LazyContainer;

/**
 * @author sibi
 *
 */
public class ItemPricing extends CustomComponent {

    private final static Logger LOGGER = Logger.getLogger(ItemPricing.class);

    @UiField("hLayout")
    private HorizontalLayout hLayout;

    @UiField("cssLayout")
    private CssLayout cssLayout;

    @UiField("resultsTableLayout")
    private VerticalLayout resultsTableLayout;

    @UiField("massCheck")
    private OptionGroup massCheck;

    @UiField("massField")
    private ComboBox massField;

    @UiField("massDate")
    private PopupDateField massDate;

    @UiField("massValue")
    private ComboBox massValue;

    @UiField("massText")
    private TextField massText;

    @UiField("btnPopulate")
    private Button btnPopulate;

    @UiField("btnAllPopulate")
    private Button btnAllPopulate;

    ItemDetailsTableLogic itemDetailsTableLogic = new ItemDetailsTableLogic();

    ExtPagedTable itemDetailsTable = new ExtPagedTable(itemDetailsTableLogic);

    TempViewTableLogic itemDetailsViewTableLogic = new TempViewTableLogic();

    private ExtPagedTable viewItemDetailsTable = new ExtPagedTable(itemDetailsViewTableLogic);
    /**
     * Record checkbox
     */
    @UiField("record")
    private OptionGroup record;

    @UiField("level")
    private OptionGroup level;

    @UiField("view")
    private OptionGroup view;

    @UiField("detail")
    private VerticalLayout detail;

    @UiField("header")
    private VerticalLayout header;

    @UiField("priceScheduleStatus")
    private CustomComboBox priceScheduleStatus;

    @UiField("priceScheduleDesignation")
    private CustomComboBox priceScheduleDesignation;

    @UiField("priceScheduleType")
    private CustomComboBox priceScheduleType;

    @UiField("priceScheduleCategory")
    private CustomComboBox priceScheduleCategory;

    @UiField("tradeClass")
    private CustomComboBox tradeClass;

    @UiField("parentPriceScheduleId")
    private CustomTextField parentPriceScheduleId;

    @UiField("parentPriceScheduleName")
    private CustomTextField parentPriceScheduleName;

    @UiField("priceScheduleId")
    private TextField priceScheduleId;

    @UiField("priceScheduleNo")
    private TextField priceScheduleNo;

    @UiField("priceScheduleName")
    private TextField priceScheduleName;

    @UiField("priceScheduleStartDate")
    private PopupDateField priceScheduleStartDate;

    @UiField("priceScheduleEndDate")
    private PopupDateField priceScheduleEndDate;

    @UiField("createdBy")
    private TextField createdBy;

    @UiField("modifiedBy")
    private TextField modifiedBy;

    @UiField("createdDate")
    private PopupDateField createdDate;

    @UiField("modifiedDate")
    private PopupDateField modifiedDate;

    /**
     * The look up.
     */
    PsParentLookup lookUp = null;

    @UiField("viewLB")
    private Label viewLB;

    @UiField("priceProtection")
    private VerticalLayout priceProtection;

    /**
     * The mass check.
     */
    @UiField("ppMassCheck")
    private OptionGroup ppMassCheck;

    @UiField("ppCssLayout")
    private CssLayout ppCssLayout;

    /**
     * The mass field.
     */
    @UiField("ppMassField")
    private ComboBox ppMassField;

    /**
     * The mass date.
     */
    @UiField("ppMassDate")
    private PopupDateField priceProtectionMassDate;

    /**
     * The mass value.
     */
    @UiField("ppMassValue")
    private TextField priceProtectionMassValue;

    /**
     * The mass select.
     */
    @UiField("massSelect")
    private ComboBox massSelect;

    /**
     * The btn populate.
     */
    @UiField("ppBtnPopulate")
    private Button ppBtnPopulate;
    /**
     * The btn all populate.
     */
    @UiField("ppBtnAllPopulate")
    private Button ppBtnAllPopulate;

    /**
     * The control layout.
     */
    @UiField("controlLayout")
    private HorizontalLayout controlLayout;

    /**
     * The massLookup.
     */
    @UiField("massLookup")
    private CustomTextField massLookup;

    /**
     *
     */
    @UiField("massLayout")
    private HorizontalLayout massLayout;

    @UiField("excel")
    private Button excel;

    /**
     * Record check box
     */
    @UiField("ppRecord")
    private OptionGroup recordCheck;

    @UiField("ppResultsTableLayout")
    private VerticalLayout ppResultsTableLayout;

    PriceProtectionTableLogic ppTableLogic = new PriceProtectionTableLogic();
    private ExtPagedTable priceProtectionTable = new ExtPagedTable(ppTableLogic);

    @UiField("viewPriceProtectionTable")
    private ExtFilterTable viewPriceProtectionTable;

    @UiField("history")
    private VerticalLayout history;

    @UiField("historyPriceProtectionTable")
    private CustomePagedFilterTable historyPriceProtectionTable;

    @UiField("historyResultsTableLayout")
    private VerticalLayout historyResultsTableLayout;

    @UiField("cssLayoutHeader")
    private CssLayout cssLayoutHeader;

    private LazyBeanItemContainer ppTempLazyContainer;

    private BeanItemContainer<TempPricingDTO> ppSaveContainer = new BeanItemContainer<TempPricingDTO>(TempPricingDTO.class);

    /**
     * The binder.
     */
    private ErrorfulFieldGroup ppBinder;

    LazyLoadCriteria ppLazyLoadCriteria = new LazyLoadCriteria();

    private Map<String, String> listValueMap = new HashMap<String, String>();

    private static final Logger logger = Logger.getLogger(ItemPricing.class.getName());

    NetSalesFormulaLookup netSalesFormulaLookup;
    private String ppFieldMass = StringUtils.EMPTY;

    NetPriceTypeFormulaLookup formulaLookup;
    Map<Integer, HelperDTO> priceProtectionPriceType = new HashMap<Integer, HelperDTO>();

    /**
     * The available item result bean.
     */
    private BeanItemContainer<ItemMasterDTO> availableItemResultBean;
    /**
     * The selected item result bean.
     */
    private BeanItemContainer<ItemMasterDTO> selectedItemResultBean;
    /**
     * The item details results bean.
     */
    private BeanItemContainer<VwContractPriceInfoDTO> itemDetailsResultsBean;
    /**
     * The item map.
     */
    private Map<String, String> itemMap;

    private BeanItemContainer<TempPricingDTO> saveContainer = new BeanItemContainer<TempPricingDTO>(TempPricingDTO.class);

    private LazyBeanItemContainer tempLazyContainer;

    private Map<String, List> tempDate;
    private Map<String, List> ppTempDate;

    private final ContractHeaderLogic contractLogic = new ContractHeaderLogic();
    CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();
    final StplSecurity stplSecurity = new StplSecurity();

    LazyLoadCriteria lazyLoadCriteria = new LazyLoadCriteria();
    final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
    final boolean isEditable;

    /**
     * The ifp logic.
     */
    private final IfpLogic ifpLogic;

    /**
     * The field mass.
     */
    private String fieldMass = StringUtils.EMPTY;

    /**
     * The binder.
     */
    private CustomFieldGroup binder;

    /**
     * The map.
     */
    private final ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();

    private final Map<Integer, Boolean> reloadMap = new HashMap<Integer, Boolean>();

    /**
     * The ifp item list.
     */
    private final List<VwContractPriceInfoDTO> ifpItemList = new ArrayList<VwContractPriceInfoDTO>();

    private final DashBoardLogic dashBoardLogic;

    private final Map<Integer, Boolean> reloadVerticalLayoutTabThreeMap = new HashMap<Integer, Boolean>();
    final Map<String, AppPermission> contractDashboard = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.CONTRACT_DASHBOARD + "," + "Item Pricing", false);

    List<Object> resultList = contractLogic.getFieldsForSecurity(UISecurityUtil.CONTRACT_DASHBOARD, "Item Pricing");
    List<Object> resultListHistory = contractLogic.getFieldsForSecurity(UISecurityUtil.CONTRACT_DASHBOARD, "Item Pricing History");
    final Map<String, AppPermission> contractDashboardHistory = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.CONTRACT_DASHBOARD + "," + "Item Pricing History", false);
    Object[] obj = ContractUtils.ITEM_DETAILS_COL;
    Object[] objView = ContractUtils.ITEM_DETAILS_VEIW_COL;
    Object[] objViewHistory = ContractUtils.PRICE_PROTECTION_HISTORY;
    /**
     * The contract price info dto.
     */
    private VwContractPriceInfoDTO contractPriceInfoDTO = new VwContractPriceInfoDTO();
    /**
     * The common util.
     */
    private CommonUtil commonUtil = CommonUtil.getInstance();

    HelperListUtil helperListUtil = HelperListUtil.getInstance();
    SessionDTO sessionDTO;

    Date[] dates = new Date[2];
    Object[] psDates = new Object[2];

    Map<Integer, String> priceType = new HashMap<>();

    private BeanItemContainer<PricingHistoryDto> historyLazyContainer = new BeanItemContainer<PricingHistoryDto>(PricingHistoryDto.class);

    /**
     * UserMap - Contains User System ID and User Name
     */
    public static Map<Integer, String> userMap = new ConcurrentHashMap<Integer, String>();

    /**
     * The contract master binder.
     */
    public CustomFieldGroup pricingBinderEdit;

    /**
     * The contract master.
     */
    private final PriceScheduleDto priceScheduleMaster;
    /**
     * Value change flag for listener
     */
    boolean valueChange = false;
    /**
     * Date format
     */
    SimpleDateFormat format = new SimpleDateFormat("MM/dd/YYYY");

    /**
     * Dummy lazy bean container to clear the Table
     */
    private final BeanItemContainer<TempPricingDTO> tableContainer = new BeanItemContainer<TempPricingDTO>(TempPricingDTO.class);

    /**
     * Dummy lazy bean container to clear the Table
     */
    private final BeanItemContainer<TempPricingDTO> ppTableContainer = new BeanItemContainer<TempPricingDTO>(TempPricingDTO.class);

    public PopupDateField getPriceScheduleStartDate() {
        return priceScheduleStartDate;
    }

    public void setPriceScheduleStartDate(PopupDateField priceScheduleStartDate) {
        this.priceScheduleStartDate = priceScheduleStartDate;
    }

    public PopupDateField getPriceScheduleEndDate() {
        return priceScheduleEndDate;
    }

    public void setPriceScheduleEndDate(PopupDateField priceScheduleEndDate) {
        this.priceScheduleEndDate = priceScheduleEndDate;
    }

    public BeanItemContainer<TempPricingDTO> getSaveContainer() {
        return saveContainer;
    }

    public void setSaveContainer(BeanItemContainer<TempPricingDTO> saveContainer) {
        this.saveContainer = saveContainer;
    }
    
    @UiField("excelBtn")
    private Button excelExport;
    List<Integer> pageLength = new ArrayList<Integer>();

    /**
     * The Constructor.
     *
     * @param availableItemResultBean the available item result bean
     * @param selectedItemResultBean the selected item result bean
     * @param itemDetailsResultsBean the item details results bean
     * @param itemMap the item map
     */
    public ItemPricing(final BeanItemContainer<ItemMasterDTO> availableItemResultBean, final BeanItemContainer<ItemMasterDTO> selectedItemResultBean, final BeanItemContainer<TempPricingDTO> saveContainer,
            final BeanItemContainer<VwContractPriceInfoDTO> itemDetailsResultsBean, final Map<String, String> itemMap, final boolean isEditable, final SessionDTO sessionDTO, final BeanItemContainer<TempPricingDTO> ppSaveContainer, final CustomFieldGroup pricingBinderEdit, final PriceScheduleDto priceScheduleMaster) throws SystemException, PortalException, Exception {
        super();
        LOGGER.info("Entering ItemsAndPricingTab");
        this.availableItemResultBean = availableItemResultBean;
        this.saveContainer = saveContainer;
        this.ppSaveContainer = ppSaveContainer;
        this.selectedItemResultBean = selectedItemResultBean;
        this.itemDetailsResultsBean = itemDetailsResultsBean;
        this.itemMap = itemMap;
        this.isEditable = isEditable;
        this.sessionDTO = sessionDTO;
        this.pricingBinderEdit = pricingBinderEdit;
        this.priceScheduleMaster = priceScheduleMaster;
        dashBoardLogic = new DashBoardLogic(this.sessionDTO);
        ifpLogic = new IfpLogic(this.sessionDTO);
        priceProtectionPriceType = ifpLogic.loadPriceProtection();
        this.setCompositionRoot(Clara.create(getClass().getResourceAsStream("/declarative-ui/contract-dashboard/item-pricing.xml"), this));
        init();
        LOGGER.info("End of ItemsAndPricingTab");
    }

    /**
     * Inits the.
     */
    private void init() throws SystemException, PortalException, Exception {
        LOGGER.info("Entering init method");
        priceType = ifpLogic.getItemPricingQualifiers();
        pageLength.add(10);
        pageLength.add(15);
        pageLength.add(20);
        pageLength.add(25);
        pageLength.add(50);
        pageLength.add(100);
        itemDetailsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
     /*    itemDetailsTable.setPageLength(6);
        itemDetailsTableLogic.sinkItemPerPageWithPageLength(false);
        itemDetailsTable.setImmediate(true);
        itemDetailsTable.addStyleName(Constants.FILTER_BAR);
        itemDetailsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE); */
        itemDetailsTable.setSizeFull();
        record.addItems(Constants.HISTORY, Constants.CURRENT, Constants.FUTURE, Constants.PENDING);
        record.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                String value = String.valueOf(record.getValue());
                if (!isEditable) {
                    loadTempIfpOnView(value);
                } else {
                    itemDetailsTableLogic.setRecord(value);
                    itemDetailsTableLogic.configureSearchData(itemDetailsTable, saveContainer, sessionDTO, priceType);
                }
            }
        });

        final Map<String, AppPermission> fieldContractDashboardHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.CONTRACT_DASHBOARD + Constants.COMMA + "Item Pricing", false);

        addResponsiveGrid(fieldContractDashboardHM);
        if (isEditable) {
            if (selectedItemResultBean.size() > Constants.ZERO) {
                for (int i = 0; i < selectedItemResultBean.size(); i++) {
                    final ItemMasterDTO item = selectedItemResultBean.getIdByIndex(i);
                    itemMap.put(String.valueOf(item.getItemSystemId()), String.valueOf(item.getItemSystemId()));
                }
            }
            configureButtons();
            configureFields();
//            configureBinder();
//            addItemDetailsTable();
//            configurePriceProtectionTable();
//            configureHistoryTable();
        } else {
            configureFieldsOnView();
//            configureBinder();
//            addItemDetailsTableForView();
//            viewItemDetailsTable.setReadOnly(true);
//            configurePriceProtectionViewTable();
//            configureHistoryViewTable();
//            priceProtectionTable.setReadOnly(true);
//            historyPriceProtectionTable.setReadOnly(true);
        }
        configureBinder();
        addItemDetailsTable();
        configurePriceProtectionTable();
        configureHistoryTable();
        ppAddBtnPopulate();
        ppAddBtnPopulateAll();
        btnExportLogic();
        valueChange = Boolean.TRUE;
        LOGGER.info("End of init method");
    }

    /**
     * Gets the binder.
     *
     * @return the binder
     */
    private void configureBinder() {
        LOGGER.info("Entering getBinder method");
        pricingBinderEdit.bindMemberFields(this);
        pricingBinderEdit.setItemDataSource(new BeanItem<PriceScheduleDto>(priceScheduleMaster));
        pricingBinderEdit.setBuffered(true);
        LOGGER.info("End of getBinder method");
    }

    private void configureFieldsOnView() throws Exception {
        hLayout.setVisible(false);
//        itemDetailsTable.setVisible(false);
        level.addItem(Constants.HEADER);
        level.addItem(Constants.DETAILS);
        level.addItem(Constants.PRICE_PROTECTION);
        level.setValue(Constants.HEADER);

        view.addItem(Constants.HISTORY);
        view.addItem(Constants.CURRENT);
        view.addItem(Constants.PENDING);
        view.setValue(Constants.CURRENT);

        detail.setVisible(false);
        priceProtection.setVisible(false);
        header.setVisible(true);
        history.setVisible(false);

        priceScheduleId.focus();
        priceScheduleId.setReadOnly(true);
        priceScheduleNo.setReadOnly(true);
        priceScheduleName.setReadOnly(true);
        parentPriceScheduleName.setReadOnly(true);
        parentPriceScheduleName.setEnabled(false);
        parentPriceScheduleId.setReadOnly(true);
        parentPriceScheduleId.setEnabled(false);

        createdBy.setReadOnly(false);
        createdBy.setEnabled(true);
        createdBy.setImmediate(true);
        createdBy.setReadOnly(true);
        createdBy.setEnabled(false);

        createdDate.setReadOnly(false);
        createdDate.setEnabled(true);
        createdDate.setValue(new Date());
        createdDate.setImmediate(true);
        createdDate.setReadOnly(true);
        createdDate.setEnabled(false);

        modifiedBy.setReadOnly(false);
        modifiedBy.setEnabled(true);
        modifiedBy.setImmediate(true);
        modifiedBy.setReadOnly(true);
        modifiedBy.setEnabled(false);

        modifiedDate.setReadOnly(false);
        modifiedDate.setEnabled(true);
        modifiedDate.setValue(new Date());
        modifiedDate.setImmediate(true);
        modifiedDate.setReadOnly(true);
        modifiedDate.setEnabled(false);

        commonUtil.loadComboBox(priceScheduleStatus, UIUtils.STATUS, false);
        commonUtil.loadComboBox(priceScheduleDesignation, UIUtils.PS_DESIGNATION, false);
        commonUtil.loadComboBox(priceScheduleType, UIUtils.PS_TYPE, false);
        commonUtil.loadComboBox(priceScheduleCategory, UIUtils.PS_CATEGORY, false);
        commonUtil.loadComboBox(tradeClass, UIUtils.PS_TRADE_CLASS, false);

        level.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Value change for PS Designation
             */
            public void valueChange(final ValueChangeEvent event) {
                excelExport.setVisible(true);
                if (Constants.DETAILS.equals(level.getValue())) {
                    view.setVisible(false);
                    viewLB.setVisible(false);
                    detail.setVisible(true);
                    header.setVisible(false);
                    priceProtection.setVisible(false);
               
                } else if (Constants.HEADER.equals(level.getValue())) {
                    view.setVisible(true);
                    viewLB.setVisible(true);
                    detail.setVisible(false);
                        header.setVisible(true);
                    priceProtection.setVisible(false);

                } else if (Constants.PRICE_PROTECTION.equals(level.getValue())) {
                    view.setVisible(false);
                    viewLB.setVisible(false);
                    detail.setVisible(false);
                    header.setVisible(false);
                    priceProtection.setVisible(true);
                }
                if (String.valueOf(level.getValue()).equals("Header") && !String.valueOf(view.getValue()).equals("History")) {
                    excelExport.setVisible(false);
                }
            }
        });

        view.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Value change for PS Designation
             */
            public void valueChange(final ValueChangeEvent event) {
                 excelExport.setVisible(true);
                if (Constants.HISTORY.equals(view.getValue())) {
                    history.setVisible(true);
                    header.setVisible(false);
                } else if (Constants.CURRENT.equals(view.getValue())) {
                    history.setVisible(false);
                    header.setVisible(true);
                } else if (Constants.PENDING.equals(view.getValue())) {
                    history.setVisible(false);
                    header.setVisible(true);
                }
                 if (String.valueOf(level.getValue()).equals("Header") && !String.valueOf(view.getValue()).equals("History")) {
                    excelExport.setVisible(false);
                }
            }
        });
        
        excelExport.setIcon(new ThemeResource(ExcelExportUtil.EXCEL_EXPORT_IMAGE));
        excelExport.setStyleName("link");
        excelExport.setDescription(Constants.EXCEL_EXPORT);
        excelExport.setIconAlternateText(Constants.EXCEL_EXPORT);
        btnExportLogic();
        excelExport.setVisible(false);
    }

    /**
     * Configure the buttons in the Item Pricing tab.Populate and Populate All
     * Button are configure.
     *
     * @throws PortalException
     * @throws SystemException
     * @throws Exception
     */
    private void configureButtons() throws PortalException, SystemException, Exception {

        final Map<String, AppPermission> functionContractDashboardHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.CONTRACT_DASHBOARD + Constants.COMMA + "Item Pricing");

        if (functionContractDashboardHM.get(CHFunctionNameUtils.ItemPopulate) != null && ((AppPermission) functionContractDashboardHM.get(CHFunctionNameUtils.ItemPopulate)).isFunctionFlag()) {
            addBtnPopulate();
        } else {
            btnPopulate.setVisible(false);
        }
        if (functionContractDashboardHM.get(CHFunctionNameUtils.ItemPopulateAll) != null && ((AppPermission) functionContractDashboardHM.get(CHFunctionNameUtils.ItemPopulateAll)).isFunctionFlag()) {
            addAllBtnPopulate();
        } else {
            btnAllPopulate.setVisible(false);
        }

    }

    /**
     * Adds and configures the populate button.
     *
     * @return the button
     */
    public Button addBtnPopulate() {
        LOGGER.info("Entering addBtnPopulate method");

        btnPopulate.setReadOnly(true);
        btnPopulate.setErrorHandler(new ErrorHandler() {
            /**
             * Method used for populate button logic and its listener.
             *
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error(event.toString());
            }
        });

        btnPopulate.addClickListener(new ClickListener() {
            /**
             * Method used to populate button logic and its listener.
             */
            public void buttonClick(final ClickEvent event) {
                LOGGER.info("Entering btnPopulate buttonClick method");
                String value = StringUtils.EMPTY;
                final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                if (massField.getValue() != null) {
                    try {
                        fieldMass = map.get(massField.getValue());
                        if (Constants.PRICE_START_DATE.equals(fieldMass)) {
                            if (massDate.getValue() == null) {
                                binder.getErrorDisplay().setError("Please provide date and try again.");
                            } else {
                                value = fmt.format(massDate.getValue());
                            }

                        } else if (Constants.PRICE_END_DATE.equals(fieldMass)) {
                            if (massDate.getValue() == null) {
                                binder.getErrorDisplay().setError("Please provide date and try again.");
                            } else {
                                value = fmt.format(massDate.getValue());
                            }
                        } else if (Constants.CP_START_DATE.equals(fieldMass)) {
                            if (priceScheduleStartDate.getValue() == null) {

                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Please provide Price Schedule Start date and try again.");

                                return;
                            } else if (massDate.getValue() == null) {
                                binder.getErrorDisplay().setError("Please provide date and try again.");
                            } else if (massDate.getValue().before(priceScheduleStartDate.getValue())) {

                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Start date cannot be before " + new SimpleDateFormat("MM/dd/YYYY").format(priceScheduleStartDate.getValue()));
                                return;
                            } else if (priceScheduleEndDate.getValue() != null && massDate.getValue().after(priceScheduleEndDate.getValue())) {
                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Start date cannot be after " + new SimpleDateFormat("MM/dd/YYYY").format(priceScheduleEndDate.getValue()));

                                return;
                            } else {
                                value = fmt.format(massDate.getValue());
                            }

                        } else if (Constants.CP_END_DATE.equals(fieldMass)) {
                            if (priceScheduleStartDate.getValue() == null) {

                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Please provide Price Schedule Start date and try again.");

                                return;
                            } else if (massDate.getValue() == null) {
                                binder.getErrorDisplay().setError("Please provide date and try again.");
                            } else if (priceScheduleEndDate.getValue() != null && massDate.getValue().after(priceScheduleEndDate.getValue())) {

                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "End date cannot be after " + new SimpleDateFormat("MM/dd/YYYY").format(priceScheduleEndDate.getValue()));
                                return;
                            } else if (massDate.getValue().before(priceScheduleStartDate.getValue())) {
                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "End date cannot be before " + new SimpleDateFormat("MM/dd/YYYY").format(priceScheduleStartDate.getValue()));

                                return;
                            } else {
                                value = fmt.format(massDate.getValue());
                            }
                        } else if (Constants.PRICE.equals(fieldMass)) {
                            if (StringUtils.isEmpty(massText.getValue())) {
                                binder.getErrorDisplay().setError("Price is mandatory and should be numeric  ");
                            } else {
                                value = massText.getValue();
                            }
                        } else if (Constants.BASE_PRICE.equals(fieldMass)) {
                            if (StringUtils.isEmpty(massText.getValue())) {
                                binder.getErrorDisplay().setError("Base Price is mandatory and should be numeric  ");
                            } else {
                                value = massText.getValue();
                            }
                        } else if (Constants.SUGGESTED_PRICE.equals(fieldMass)) {
                            if (StringUtils.isEmpty(massText.getValue())) {
                                binder.getErrorDisplay().setError("Suggested Price is mandatory and should be numeric  ");
                            } else {
                                value = massText.getValue();
                            }
                        } else if (Constants.PRICE_TOLERANCE.equals(fieldMass)) {

                            if (StringUtils.isEmpty(massText.getValue())) {
                                binder.getErrorDisplay().setError("Please Enter a value and try again. ");
                            } else {
                                value = massText.getValue();
                            }

                        } else if (Constants.PRICE_TYPE.equals(fieldMass)) {
                            value = String.valueOf(((HelperDTO) (massValue.getValue())).getId());

                        } else if (Constants.PRICE_TOLERANCE_TYPE.equals(fieldMass)) {
                            value = String.valueOf(((HelperDTO) (massValue.getValue())).getId());

                        } else if (Constants.PRICE_TOLERANCE_INTERVAL.equals(fieldMass)) {
                            if (massValue.getValue() == null) {

                                value = Constants.ZEROSTRING;
                            } else {
                                value = String.valueOf(((HelperDTO) (massValue.getValue())).getId());

                            }
                        } else if (Constants.ATTACHED_STATUS.equals(fieldMass)) {
                            if (massValue.getValue() == null) {
                                binder.getErrorDisplay().setError("Attached Status is mandatory ");
                            } else {
                                value = String.valueOf(((HelperDTO) (massValue.getValue())).getId());
                            }
                        } else if (fieldMass.equals(Constants.ITEM_STATUS)) {
                            if (massValue.getValue() == null) {

                                value = Constants.ZEROSTRING;
                            } else {
                                value = String.valueOf(((HelperDTO) (massValue.getValue())).getId());
                            }
                        } else if (Constants.PRICE_TOLERANCE_FREQUENCY.equals(fieldMass)) {
                            value = String.valueOf(((HelperDTO) (massValue.getValue())).getId());

                        }
                        IfpLogic.saveToTempIFP(saveContainer.getItemIds());
                        if (IfpLogic.isCheckedValidation("IMTD_ITEM_PRICE_REBATE_DETAILS", String.valueOf(sessionDTO.getUiSessionId()))) {
                            AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, Constants.POPULATE_MSG);
                            return;
                        }
                        saveContainer.removeAllItems();
                        ifpLogic.populateToTempIFP(massField.getValue(), value, Boolean.FALSE);
                        loadTempIfp();
                    } catch (SystemException ex) {
                        final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                        LOGGER.error(errorMsg);
                        AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                    } catch (PortalException ex) {
                        final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                        LOGGER.error(errorMsg);
                    }
                } else {

                    AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, Constants.POPULATE_MSG);
                }
                LOGGER.info("End of btnPopulate buttonClick method");
            }
        });
        LOGGER.info("End of addBtnPopulate method");
        return btnPopulate;
    }

    public void loadTempIfp() {

        try {
            itemDetailsTableLogic.configureSearchData(itemDetailsTable, saveContainer, sessionDTO, priceType);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

    }

    /**
     * Configures the Populate All button.
     *
     * @return the button
     */
    public Button addAllBtnPopulate() {
        LOGGER.info("Entering addAllBtnPopulate method");
        btnAllPopulate.setReadOnly(true);
        btnAllPopulate.setErrorHandler(new ErrorHandler() {
            /**
             * Method used to populate all button logic and its listener.
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error(event.toString());
            }
        });

        btnAllPopulate.addClickListener(new ClickListener() {
            /**
             * Method used to all button populate logic and its listener.
             */
            public void buttonClick(final ClickEvent event) {
                String value = StringUtils.EMPTY;
                final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                if (massField.getValue() != null) {
                    try {
                        fieldMass = map.get(massField.getValue());
                        if (fieldMass.equals(Constants.PRICE_START_DATE)) {
                            if (massDate.getValue() == null) {
                                binder.getErrorDisplay().setError("Please provide date and try again.");
                                return;
                            } else {
                                value = fmt.format(massDate.getValue());
                            }

                        } else if (fieldMass.equals(Constants.PRICE_END_DATE)) {
                            if (massDate.getValue() == null) {
                                binder.getErrorDisplay().setError("Please provide date and try again.");
                                return;
                            } else {
                                value = fmt.format(massDate.getValue());
                            }
                        } else if (fieldMass.equals(Constants.CP_START_DATE)) {
                            if (priceScheduleStartDate.getValue() == null) {

                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Please provide Price Schedule Start date and try again.");
                                return;
                            } else if (massDate.getValue() == null) {
                                binder.getErrorDisplay().setError("Please provide date and try again.");
                                return;
                            } else if (massDate.getValue().before(priceScheduleStartDate.getValue())) {

                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Start date cannot be before " + new SimpleDateFormat("MM/dd/YYYY").format(priceScheduleStartDate.getValue()));
                                return;
                            } else if (priceScheduleEndDate.getValue() != null && massDate.getValue().after(priceScheduleEndDate.getValue())) {
                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Start date cannot be after " + new SimpleDateFormat("MM/dd/YYYY").format(priceScheduleEndDate.getValue()));
                                return;
                            } else {
                                value = fmt.format(massDate.getValue());
                            }

                        } else if (fieldMass.equals(Constants.CP_END_DATE)) {
                            if (priceScheduleStartDate.getValue() == null) {

                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Please provide Price Schedule Start date and try again.");

                                return;
                            } else if (massDate.getValue() == null) {
                                binder.getErrorDisplay().setError("Please provide date and try again.");
                                return;
                            } else if (priceScheduleEndDate.getValue() != null && massDate.getValue().after(priceScheduleEndDate.getValue())) {

                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "End date cannot be after " + new SimpleDateFormat("MM/dd/YYYY").format(priceScheduleEndDate.getValue()));
                                return;
                            } else if (massDate.getValue().before(priceScheduleStartDate.getValue())) {
                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "End date cannot be before " + new SimpleDateFormat("MM/dd/YYYY").format(priceScheduleStartDate.getValue()));

                                return;
                            } else {
                                value = fmt.format(massDate.getValue());
                            }
                        } else if (fieldMass.equals(Constants.PRICE)) {
                            if (StringUtils.isEmpty(massText.getValue())) {
                                binder.getErrorDisplay().setError("Price is mandatory and should be numeric  ");
                                return;
                            } else {
                                value = massText.getValue();
                            }
                        } else if (fieldMass.equals(Constants.BASE_PRICE)) {
                            if (StringUtils.isEmpty(massText.getValue())) {
                                binder.getErrorDisplay().setError("Base Price is mandatory and should be numeric  ");
                                return;
                            } else {
                                value = massText.getValue();
                            }
                        } else if (fieldMass.equals(Constants.SUGGESTED_PRICE)) {
                            if (StringUtils.isEmpty(massText.getValue())) {
                                binder.getErrorDisplay().setError("Suggested Price is mandatory and should be numeric  ");
                            } else {
                                value = massText.getValue();
                            }
                        } else if (fieldMass.equals(Constants.PRICE_TOLERANCE)) {

                            if (StringUtils.isEmpty(massText.getValue())) {
                                binder.getErrorDisplay().setError("Please Enter a value and try again. ");
                                return;
                            } else {
                                value = massText.getValue();
                            }

                        } else if (fieldMass.equals(Constants.PRICE_TYPE)) {
                            value = String.valueOf(((HelperDTO) (massValue.getValue())).getId());

                        } else if (fieldMass.equals(Constants.PRICE_TOLERANCE_TYPE)) {
                            value = String.valueOf(((HelperDTO) (massValue.getValue())).getId());

                        } else if (fieldMass.equals(Constants.PRICE_TOLERANCE_INTERVAL)) {
                            if (massValue.getValue() == null) {

                                value = Constants.ZEROSTRING;
                            } else {
                                value = String.valueOf(((HelperDTO) (massValue.getValue())).getId());

                            }
                        } else if (fieldMass.equals(Constants.ATTACHED_STATUS)) {

                            if (massValue.getValue() == null) {

                            } else {
                                value = String.valueOf(((HelperDTO) (massValue.getValue())).getId());
                            }
                        } else if (fieldMass.equals(Constants.ITEM_STATUS)) {
                            if (massValue.getValue() == null) {

                                value = Constants.ZEROSTRING;
                            } else {
                                value = String.valueOf(((HelperDTO) (massValue.getValue())).getId());
                            }
                        } else if (fieldMass.equals(Constants.PRICE_TOLERANCE_FREQUENCY)) {
                            value = String.valueOf(((HelperDTO) (massValue.getValue())).getId());

                        }
                        IfpLogic.saveToTempIFP(saveContainer.getItemIds());
                        saveContainer.removeAllItems();
                        ifpLogic.populateToTempIFP(massField.getValue(), value, Boolean.TRUE);
                        loadTempIfp();
                    } catch (SystemException ex) {
                        LOGGER.error(ex.getMessage());;
                        final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                        LOGGER.error(errorMsg);
                        AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                    } catch (PortalException ex) {
                        LOGGER.error(ex.getMessage());;
                        final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                        LOGGER.error(errorMsg);
                    }
                } else {

                    AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, Constants.POPULATE_MSG);
                }
            }
        });
        LOGGER.info("End of addAllBtnPopulate method");
        return btnAllPopulate;
    }

    /**
     * To Configure all the Fields
     *
     * @throws SystemException
     */
    public void configureFields() throws SystemException, Exception {
        LOGGER.info("Entering configureFields method");

        priceScheduleStartDate.setValidationVisible(true);
        priceScheduleStartDate.setImmediate(true);
        priceScheduleStartDate.addValidator(new DateValidator("Start date should be before End date in Pricing Tab", "StartDate"));
        priceScheduleStartDate.setRequired(true);
        priceScheduleStartDate.setRequiredError("Start Date should be selected on Pricing tab");
        priceScheduleStartDate.setDateFormat(Constants.MM_DD_YYYY);
        modifiedDate.setDateFormat(Constants.MM_DD_YYYY);
        createdDate.setDateFormat(Constants.MM_DD_YYYY);
        priceScheduleEndDate.setDateFormat(Constants.MM_DD_YYYY);
        attachListeners(priceScheduleStartDate, "START_DATE");

        priceScheduleEndDate.setValidationVisible(true);
        priceScheduleEndDate.setImmediate(true);
        priceScheduleEndDate.addValidator(new DateValidator("End date should be greater than start date in Pricing Tab", "EndDate"));
        massCheck.addItem(Constants.ENABLE);
        massCheck.addItem(Constants.DISABLE);
        massCheck.setValue(Constants.DISABLE);
        massCheck.setImmediate(true);
        massCheck.setMultiSelect(false);

        massCheck.setImmediate(true);
        massCheck.focus();
        massField.setNullSelectionAllowed(true);
        massField.addItem(Constants.SELECT_ONE);
        massField.setNullSelectionItemId(Constants.SELECT_ONE);

        massField.addItem(Constants.CP_START_DATE_SP);
        massField.addItem(Constants.CP_END_DATE_SP);
        massField.addItem(Constants.ITEM_STATUS1);
        massField.addItem(Constants.PRICE_SP);
        massField.addItem(Constants.PRICE_TYPE1);
        massField.addItem(Constants.SUGGESTED_PRICE1);
        massField.setImmediate(true);
        massField.select(Constants.SELECT_ONE);
        massField.setEnabled(false);

        massText.setVisible(false);
        massText.addValidator(new RegexpValidator("([0-9]*.[0-9]{1,2})", "Please Enter Only digits with two decimal places"));
        massText.setImmediate(true);

        massValue.setImmediate(true);
        massValue.setVisible(false);
        massValue.select(Constants.SELECT_ONE);

        massDate.setImmediate(true);
        massDate.setVisible(false);
        massDate.setDateFormat(Constants.MM_DD_YYYY);
        massDate.setDescription(Constants.DATE);
        massDate.setId("ItemMassDate");
        
        excelExport.setIcon(new ThemeResource(ExcelExportUtil.EXCEL_EXPORT_IMAGE));
        excelExport.setStyleName("link");
        excelExport.setDescription(Constants.EXCEL_EXPORT);
        excelExport.setIconAlternateText(Constants.EXCEL_EXPORT);
        btnExportLogic();
        excelExport.setVisible(false);

        massDate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used to mass date logic and its listener.
             *
             */
            @SuppressWarnings("PMD")
            public void valueChange(final ValueChangeEvent event) {
                massDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(massDate.getValue()));
            }
        });
        massField.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used for mass field logic and its listener
             */
            public void valueChange(final ValueChangeEvent event) {
                try {

                    if (massField.getValue() != null) {
                        final String value = String.valueOf(massField.getValue());
                        if (Constants.IFP_START_DATE.equalsIgnoreCase(value) || Constants.IFP_END_DATE.equals(value)) {
                            massText.setVisible(false);
                            massValue.setVisible(false);
                            massDate.setVisible(true);
                            massDate.setValue(null);
                            btnPopulate.setReadOnly(false);
                            btnAllPopulate.setReadOnly(false);
                        }
                        if (Constants.CONTRACT_PRICE_UPPERCASE.equals(value) || Constants.PRICE.equals(value) || Constants.BASE_PRICE_SP.equals(value) || Constants.PRICE_TOLERENCE.equals(value)) {
                            massText.setVisible(true);
                            massText.setValue(StringUtils.EMPTY);
                            massValue.setVisible(false);
                            massDate.setVisible(false);
                            btnPopulate.setEnabled(true);
                            btnAllPopulate.setEnabled(true);
                        } else if (Constants.CP_START_DATE_SP.equals(value) || Constants.CP_END_DATE_SP.equals(value) || Constants.PRICE_PROTECTION_START_DATE_SP.equals(value)
                                || Constants.PRICE_PROTECTION_END_DATE_SP.equals(value) || Constants.START_DATE_SP.equals(value) || Constants.END_DATE_SP.equals(value)) {
                            massText.setVisible(false);
                            massValue.setVisible(false);
                            massDate.setVisible(true);
                            massDate.setValue(null);
                            btnPopulate.setEnabled(true);
                            btnAllPopulate.setEnabled(true);
                        } else if (Constants.PRICE_SP.equals(value) || Constants.SUGGESTED_PRICE1.equals(value)) {
                            massText.setVisible(true);
                            massText.setValue(StringUtils.EMPTY);
                            massValue.setVisible(false);
                            massDate.setVisible(false);
                            btnPopulate.setReadOnly(false);
                            btnAllPopulate.setReadOnly(false);

                        } else if (Constants.PRICE_TYPE1.equals(value)) {
                            massText.setVisible(false);
                            massValue.setVisible(true);
                            massDate.setVisible(false);
                            btnPopulate.setEnabled(true);
                            btnAllPopulate.setEnabled(true);
                            massValue.removeAllItems();
                            massValue.addItem(0);
                            massValue.setItemCaption(0, Constants.SELECT_ONE);
                            massValue.setNullSelectionAllowed(true);
                            massValue.setNullSelectionItemId(0);
                            massValue.setItemCaptionPropertyId(Constants.DESCRIPTION);
                            final LazyContainer priceTypeContainer = new LazyContainer(HelperDTO.class, new PriceTypeLazyContainer(null), new PriceTypeCriteria());
                            priceTypeContainer.setMinFilterLength(0);
                            massValue.setContainerDataSource(priceTypeContainer);
                            massValue.select(new HelperDTO(0, ConstantsUtils.SELECT_ONE));
                            new ContractUtils().getComboBox(massValue, new ContractUtils().getPriceType());
                            massValue.select(0);

                        } else if (Constants.PRICE_TOLERANCE_TYPE_VALUE.equals(value)) {
                            massText.setVisible(false);
                            massValue.setVisible(true);
                            massDate.setVisible(false);
                            massValue.removeAllItems();
                            btnPopulate.setEnabled(true);
                            btnAllPopulate.setEnabled(true);
                            massValue.addItem(0);
                            massValue.setItemCaption(0, Constants.SELECT_ONE);
                            massValue.setNullSelectionAllowed(true);
                            massValue.setNullSelectionItemId(0);

                            commonUtil.loadComboBox(massValue, ContractUtils.PRICE_TOLERANCE_TYPE, false);
                            massValue.select(0);
                        } else if (Constants.ITEM_STATUS1.equals(value)) {
                            massText.setVisible(false);
                            massValue.setVisible(true);
                            massDate.setVisible(false);
                            massValue.removeAllItems();
                            btnPopulate.setEnabled(true);
                            btnAllPopulate.setEnabled(true);
                            massValue.addItem(0);
                            massValue.setItemCaption(0, Constants.SELECT_ONE);
                            massValue.setNullSelectionAllowed(true);
                            massValue.setNullSelectionItemId(0);
                            new ContractUtils().getComboBox(massValue, new ContractUtils().getItemStatus());
                            massValue.select(0);
                        } else if (Constants.PRICE_TOLERANCE_INTERVAL_VALUE.equals(value)) {
                            massText.setVisible(false);
                            massValue.setVisible(true);
                            massDate.setVisible(false);
                            massValue.removeAllItems();
                            btnPopulate.setEnabled(true);
                            btnAllPopulate.setEnabled(true);
                            massValue.addItem(0);
                            massValue.setItemCaption(0, Constants.SELECT_ONE);
                            massValue.setNullSelectionAllowed(true);
                            massValue.setNullSelectionItemId(0);

                            commonUtil.loadComboBox(massValue, ContractUtils.PRICE_TOLERANCE_INTERVAL, false);
                            massValue.select(0);
                        } else if (Constants.PRICE_TOLERANCE_FREQUENCY_VALUE.equals(value)) {
                            massText.setVisible(false);
                            massValue.setVisible(true);
                            massDate.setVisible(false);
                            massValue.removeAllItems();
                            btnPopulate.setEnabled(true);
                            btnAllPopulate.setEnabled(true);
                            massValue.addItem(0);
                            massValue.setItemCaption(0, Constants.SELECT_ONE);
                            massValue.setNullSelectionAllowed(true);
                            massValue.setNullSelectionItemId(0);
                            commonUtil.loadComboBox(massValue, ContractUtils.PRICE_TOLERANCE_FRERQUENCY, false);
                            massValue.select(0);
                        } else if (Constants.ATTACHED_STATUS1.equals(value)) {
                            massText.setVisible(false);
                            massValue.setVisible(true);
                            massDate.setVisible(false);
                            btnPopulate.setReadOnly(false);
                            btnAllPopulate.setReadOnly(false);
                            massValue.addItem(0);
                            massValue.setItemCaption(0, Constants.SELECT_ONE);
                            CommonUtils.getSelectNull(massValue);
                            commonUtil.loadComboBox(massValue, UIUtils.STATUS, false);
                            massValue.select(0);

                        } else if (StringUtils.EMPTY.equals(value)) {
                            massText.setVisible(false);
                            massValue.setVisible(false);
                            massDate.setVisible(true);
                            btnPopulate.setEnabled(true);
                            btnAllPopulate.setEnabled(true);
                        }
                    } else {
                        massText.setVisible(false);
                        massValue.setVisible(false);
                        massDate.setVisible(false);
                    }
                } catch (SystemException ex) {
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                    LOGGER.error(errorMsg);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                } catch (Exception ex) {
                    LOGGER.error(ex.getMessage());
                }
            }
        });

        massCheck.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            /**
             * Method used to mass check logic and its listener.
             */
            public void valueChange(final ValueChangeEvent event) {
                if (massCheck.getValue().equals(Constants.DISABLE)) {
                    massField.setEnabled(false);
                    massValue.setValue(StringUtils.EMPTY);
                    massValue.setVisible(false);
                    massDate.setVisible(false);
                    btnPopulate.setEnabled(false);
                    btnPopulate.setReadOnly(true);
                    btnAllPopulate.setEnabled(false);
                    btnAllPopulate.setReadOnly(true);
                    if (itemDetailsResultsBean != null) {
                        final List<VwContractPriceInfoDTO> contractPriceDtoList = itemDetailsResultsBean.getItemIds();

                        for (int i = 0; i < contractPriceDtoList.size(); i++) {
                            final VwContractPriceInfoDTO dto = contractPriceDtoList.get(i);
                            if (dto.getCheckbox()) {
                                dto.setCheckbox(false);
                            }
                            ifpItemList.add(dto);
                        }
                        if (!ifpItemList.isEmpty()) {
                            itemDetailsResultsBean.removeAllItems();
                            itemDetailsResultsBean.addAll(ifpItemList);
                        }
                    }
                    markAsDirty();
                } else if (massCheck.getValue().equals(Constants.ENABLE)) {
                    massField.setEnabled(true);
                    btnPopulate.setEnabled(true);
                    btnAllPopulate.setEnabled(true);
                    markAsDirty();
                }
                massCheck.focus();
            }
        });
        map.put("IFP Start Date", Constants.IFP_START_DATE);
        map.put("IFP End Date", Constants.IFP_END_DATE);
        map.put("Price Protection Start Date", Constants.PRICE_START_DATE);
        map.put("Price Protection End Date", Constants.PRICE_END_DATE);
        map.put("Base Price", Constants.BASE_PRICE);
        map.put("Price Tolerance", Constants.PRICE_TOLERANCE);
        map.put(Constants.CP_START_DATE_SP, Constants.CP_START_DATE);
        map.put(Constants.CP_END_DATE_SP, Constants.CP_END_DATE);
        map.put(Constants.START_DATE_SP, Constants.START_DATE);
        map.put("End Date", Constants.END_DATE);
        map.put("Attached Date", "attachedDate");
        map.put(Constants.ATTACHED_STATUS1, Constants.ATTACHED_STATUS);
        map.put(Constants.ITEM_STATUS1, Constants.ITEM_STATUS);
        map.put(Constants.PRICE_SP, Constants.PRICE);
        map.put(Constants.PRICE_TYPE1, Constants.PRICE_TYPE);
        map.put(Constants.PRICE_TOLERANCE_TYPE_VALUE, Constants.PRICE_TOLERANCE_TYPE);
        map.put(Constants.PRICE_TOLERANCE_INTERVAL_VALUE, Constants.PRICE_TOLERANCE_INTERVAL);
        map.put(Constants.SUGGESTED_PRICE1, Constants.SUGGESTED_PRICE);

        level.addItem(Constants.HEADER);
        level.addItem(Constants.DETAILS);
        level.addItem(Constants.PRICE_PROTECTION);
        level.setValue(Constants.HEADER);

        view.addItem(Constants.HISTORY);
        view.addItem(Constants.CURRENT);
        view.addItem(Constants.PENDING);
        view.setValue(Constants.CURRENT);

        detail.setVisible(false);
        priceProtection.setVisible(false);
        header.setVisible(true);
        history.setVisible(false);

        commonUtil.loadComboBox(priceScheduleStatus, UIUtils.STATUS, false);
        commonUtil.loadComboBox(priceScheduleDesignation, UIUtils.PS_DESIGNATION, false);
        commonUtil.loadComboBox(priceScheduleType, UIUtils.PS_TYPE, false);
        commonUtil.loadComboBox(priceScheduleCategory, UIUtils.PS_CATEGORY, false);
        commonUtil.loadComboBox(tradeClass, UIUtils.PS_TRADE_CLASS, false);

        priceScheduleId.focus();
        priceScheduleId.setReadOnly(true);
        priceScheduleNo.setReadOnly(true);
        priceScheduleName.setReadOnly(true);
        parentPriceScheduleName.setReadOnly(true);
        parentPriceScheduleName.setEnabled(false);
        parentPriceScheduleId.setReadOnly(true);
        parentPriceScheduleId.setEnabled(false);

        createdBy.setReadOnly(false);
        createdBy.setEnabled(true);
        createdBy.setImmediate(true);
        createdBy.setReadOnly(true);
        createdBy.setEnabled(false);

        createdDate.setReadOnly(false);
        createdDate.setEnabled(true);
        createdDate.setValue(new Date());
        createdDate.setImmediate(true);
        createdDate.setReadOnly(true);
        createdDate.setEnabled(false);      

        modifiedBy.setReadOnly(false);
        modifiedBy.setEnabled(true);
        modifiedBy.setImmediate(true);
        modifiedBy.setReadOnly(true);
        modifiedBy.setEnabled(false);

        modifiedDate.setReadOnly(false);
        modifiedDate.setEnabled(true);
        modifiedDate.setValue(new Date());
        modifiedDate.setImmediate(true);
        modifiedDate.setReadOnly(true);
        modifiedDate.setEnabled(false);

        level.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Value change for PS Designation
             */
            public void valueChange(final ValueChangeEvent event) {
                excelExport.setVisible(true);
                if (Constants.DETAILS.equals(level.getValue())) {
                    view.setVisible(false);
                    viewLB.setVisible(false);
                    detail.setVisible(true);
                    header.setVisible(false);
                    priceProtection.setVisible(false);
                    psDates[0] = priceScheduleStartDate.getValue();
                    psDates[1] = priceScheduleEndDate.getValue();
                } else if (Constants.HEADER.equals(level.getValue())) {
                    view.setVisible(true);
                    viewLB.setVisible(true);
                    detail.setVisible(false);
                    header.setVisible(true);
                    priceProtection.setVisible(false);
                    psDates[0] = priceScheduleStartDate.getValue();
                    psDates[1] = priceScheduleEndDate.getValue();
                } else if (Constants.PRICE_PROTECTION.equals(level.getValue())) {
                    history.setVisible(false);
                    view.setVisible(false);
                    viewLB.setVisible(false);
                    detail.setVisible(false);
                    header.setVisible(false);
                    priceProtection.setVisible(true);
                    psDates[0] = priceScheduleStartDate.getValue();
                    psDates[1] = priceScheduleEndDate.getValue();
                }
                if (String.valueOf(level.getValue()).equals("Header") && !String.valueOf(view.getValue()).equals("History")) {
                    excelExport.setVisible(false);
                }
            }
        });

        view.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Value change for PS Designation
             */
            public void valueChange(final ValueChangeEvent event) {
                excelExport.setVisible(true);
                if (Constants.HISTORY.equals(view.getValue())) {
                    history.setVisible(true);
                    header.setVisible(false);
                } else if (Constants.CURRENT.equals(view.getValue())) {
                    history.setVisible(false);
                    header.setVisible(true);
                } else if (Constants.PENDING.equals(view.getValue())) {
                    history.setVisible(false);
                    header.setVisible(true);
                }
                if (String.valueOf(level.getValue()).equals("Header") && !String.valueOf(view.getValue()).equals("History")) {
                    excelExport.setVisible(false);
                }
            }
        });

        priceScheduleDesignation.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Value change for PS Designation
             */
            public void valueChange(final ValueChangeEvent event) {

                String value = StringUtils.EMPTY;
                if (!(priceScheduleDesignation.getValue() == null || ConstantsUtils.SELECT_ONE.equals(((HelperDTO) priceScheduleDesignation.getValue()).getDescription()))) {
                    value = ((HelperDTO) priceScheduleDesignation.getValue()).getDescription();
                }
                if ("Child".equalsIgnoreCase(value)) {
                    parentPriceScheduleName.setEnabled(true);
                    parentPriceScheduleName.setReadOnly(true);
                    parentPriceScheduleId.setEnabled(true);
                    parentPriceScheduleId.setReadOnly(true);
                } else {
                    parentPriceScheduleName.setEnabled(true);
                    parentPriceScheduleName.setReadOnly(false);
                    parentPriceScheduleName.setValue(StringUtils.EMPTY);
                    parentPriceScheduleName.setReadOnly(true);
                    parentPriceScheduleName.setEnabled(false);

                    parentPriceScheduleId.setEnabled(true);
                    parentPriceScheduleId.setReadOnly(false);
                    parentPriceScheduleId.setValue(StringUtils.EMPTY);
                    parentPriceScheduleId.setReadOnly(true);
                    parentPriceScheduleId.setEnabled(false);
                }
            }
        });

        parentPriceScheduleId.addStyleName("searchicon");
        parentPriceScheduleId.setReadOnly(true);
        parentPriceScheduleName.setReadOnly(true);
        parentPriceScheduleId.setImmediate(true);
        parentPriceScheduleName.setImmediate(true);

        parentPriceScheduleId.addClickListener(new CustomTextField.ClickListener() {
            /**
             * Focus listnerer for Parent PS Name
             */
            public void click(CustomTextField.ClickEvent event) {
                try {

                    if (lookUp == null) {
                        lookUp = new PsParentLookup();
                        UI.getCurrent().addWindow(lookUp);

                        lookUp.addCloseListener(new Window.CloseListener() {
                            /**
                             * Method used to Called when the user closes a
                             * window .
                             */
                            @SuppressWarnings("PMD")
                            public void windowClose(final Window.CloseEvent event) {
                                parentPriceScheduleName.setReadOnly(false);
                                parentPriceScheduleId.setReadOnly(false);

                                if (lookUp.getParentPriceScheduleNameValue() != null) {
                                    parentPriceScheduleName.setValue(lookUp.getParentPriceScheduleNameValue());
                                }

                                if (lookUp.getParentPriceScheduleIdValue() != null) {
                                    parentPriceScheduleId.setValue(lookUp.getParentPriceScheduleIdValue());
                                }

                                parentPriceScheduleName.setReadOnly(true);
                                parentPriceScheduleId.setReadOnly(true);
                                priceScheduleId.focus();
                                lookUp.refresh();
                                lookUp = null;
                            }
                        });
                    }
                } catch (SystemException ex) {

                    LOGGER.error(ex.getMessage());
                } catch (PortalException portException) {
                    LOGGER.error(portException.getMessage());
                } catch (Exception exception) {
                    LOGGER.error(exception.getMessage());
                }
            }
        });


        recordCheck.addItems(Constants.HISTORY, Constants.CURRENT, Constants.FUTURE, Constants.PENDING);
        recordCheck.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                String value = String.valueOf(recordCheck.getValue());
                ppTableLogic.setRecord(value);
                ppTableLogic.configureSearchData(priceProtectionTable, ppSaveContainer, sessionDTO, priceProtectionPriceType, isEditable);
                priceProtectionTable.setCurrentPage(1);
            }
        });

        ppMassCheck.addItem(ConstantsUtils.ENABLE);
        ppMassCheck.addItem(ConstantsUtils.DISABLE);
        ppMassCheck.setValue(ConstantsUtils.DISABLE);
        ppMassCheck.setDescription((String) ppMassCheck.getValue());
        setMassPopulateListName();
        massLookup.addStyleName("searchicon");
        ppMassCheck.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Value change Listener for Mass Check
             */
            public void valueChange(final Property.ValueChangeEvent event) {
                try {
                    ppMassCheck.setDescription((String) ppMassCheck.getValue());
                    ppMassCheck.focus();
                    ppMassField.select(ConstantsUtils.SELECT_ONE);
                    massCheckOnChangeEvent(event.getProperty().getValue());
                } catch (Exception exception) {
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1010), new MessageBoxListener() {
                        /**
                         * The method is triggered when a button of the message
                         * box is pressed .
                         *
                         * @param buttonId The buttonId of the pressed button.
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {

                        }
                    }, ButtonId.OK);
                    msg.getButton(ButtonId.OK).focus();
                    logger.error(exception.getMessage());
                }
                ppMassCheck.focus();
            }
        });
        ppMassField.setNullSelectionAllowed(true);
        ppMassField.markAsDirty();
        ppMassField.setPageLength(7);
        ppMassField.setInputPrompt(ConstantsUtils.SELECT_ONE);
        ppMassField.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        ppMassField.addItem(ConstantsUtils.SELECT_ONE);
        String[] massFieldOptions = new String[]{Constants.MASS_PRICE_PROTECTION_STATUS, Constants.MASS_PRICE_PROTECTION_START_DATE, Constants.MASS_PRICE_PROTECTION_END_DATE, Constants.MASS_PRICE_PROTECTION_PRICE_TYPE, Constants.MASS_NEP_FORMULA, Constants.MASS_BASE_PRICE, Constants.MASS_PRICE_TOLERANCE_INTERVAL,
            Constants.MASS_PRICE_TOLERANCE_FREQUENCY, Constants.MASS_PRICE_TOLERANCE_TYPE, Constants.MASS_MAX_INCREMENTAL_CHANGE, Constants.MASS_PRICE_TOLERENCE, Constants.MASS_RESET_ELIGIBLE, Constants.MASS_RESET_TYPE, Constants.MASS_RESET_DATE, Constants.MASS_RESET_INTERVAL, Constants.MASS_RESET_FREQUENCY, Constants.MASS_NET_PRICE_TYPE, Constants.MASS_NET_PRICE_TYPE_FORMULA
        };
        for (int i = 0; i < massFieldOptions.length; i++) {
            ppMassField.addItem(massFieldOptions[i]);
        }
        ppMassField.select(ConstantsUtils.SELECT_ONE);
        ppMassField.setEnabled(false);

        ppMassField.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used to mass field logic and its listener.
             */
            @Override
            public void valueChange(final Property.ValueChangeEvent event) {
                massLookup.setReadOnly(false);
                final String value = ppMassField.getValue() == null ? StringUtils.EMPTY : String.valueOf(ppMassField.getValue());
                if (Constants.MASS_PRICE_PROTECTION_STATUS.equals(value)
                        || Constants.MASS_PRICE_TOLERANCE_INTERVAL.equals(value) || Constants.MASS_PRICE_TOLERANCE_FREQUENCY.equals(value)
                        || Constants.MASS_PRICE_TOLERANCE_TYPE.equals(value) || Constants.MASS_RESET_TYPE.equals(value) || Constants.MASS_RESET_FREQUENCY.equals(value)
                        || Constants.MASS_RESET_INTERVAL.equals(value) || Constants.MASS_RESET_ELIGIBLE.equals(value)
                        || Constants.MASS_NET_PRICE_TYPE.equals(value)) {
                    try {
                        priceProtectionMassValue.setVisible(false);
                        priceProtectionMassDate.setVisible(false);
                        massSelect.setVisible(true);
                        massLookup.setVisible(false);
                        commonUtil.loadComboBox(massSelect, listValueMap.get(value), false);
                    } catch (Exception ex) {
                        logger.error(ex.getMessage());
                    }
                } else if (Constants.MASS_PRICE_PROTECTION_START_DATE.equals(value) || Constants.MASS_PRICE_PROTECTION_END_DATE.equals(value)
                        || Constants.MASS_RESET_DATE.equals(value)) {
                    priceProtectionMassDate.setValue(null);
                    priceProtectionMassValue.setVisible(false);
                    priceProtectionMassDate.setVisible(true);
                    massSelect.setVisible(false);
                    massLookup.setVisible(false);
                } else if ( Constants.MASS_BASE_PRICE.equals(value) || Constants.MASS_MAX_INCREMENTAL_CHANGE.equals(value)
                        || Constants.PRICE_TOLERENCE.equals(value)) {
                    priceProtectionMassValue.setValue(StringUtils.EMPTY);
                    priceProtectionMassValue.setVisible(true);
                    priceProtectionMassDate.setVisible(false);
                    massSelect.setVisible(false);
                    massLookup.setVisible(false);
                } else if (Constants.MASS_NEP_FORMULA.equals(value) || Constants.MASS_NET_PRICE_TYPE_FORMULA.equals(value)) {
                    massLookup.setValue(StringUtils.EMPTY);
                    priceProtectionMassValue.setVisible(false);
                    priceProtectionMassDate.setVisible(false);
                    massSelect.setVisible(false);
                    massLookup.setVisible(true);
                } else if (StringUtils.isBlank(value) || ConstantsUtils.SELECT_ONE.equals(value)) {
                    massSelect.setVisible(false);
                    priceProtectionMassValue.setVisible(false);
                    priceProtectionMassDate.setVisible(false);
                    massLookup.setVisible(false);
                } else if (Constants.MASS_PRICE_PROTECTION_PRICE_TYPE.equals(value)) {
                    priceProtectionMassValue.setVisible(false);
                    priceProtectionMassDate.setVisible(false);
                    massSelect.removeAllItems();
                    massSelect.setVisible(true);
                    massLookup.setVisible(false);
                    massSelect.setPageLength(7);
                    massSelect.setImmediate(true);
                    massSelect.setNullSelectionAllowed(true);
                    massSelect.setInputPrompt(ConstantsUtils.SELECT_ONE);
                    massSelect.setNullSelectionItemId(new HelperDTO(0, ConstantsUtils.SELECT_ONE));
                    massSelect.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
                    massSelect.markAsDirty();
                    final LazyContainer priceTypeContainer = new LazyContainer(HelperDTO.class, new PriceTypeLazyContainer(null), new PriceTypeCriteria());
                    priceTypeContainer.setMinFilterLength(0);
                    massSelect.setContainerDataSource(priceTypeContainer);
                    massSelect.select(new HelperDTO(0, ConstantsUtils.SELECT_ONE));
                }
            }
        });

        massLookup.addClickListener(new CustomTextField.ClickListener() {

            @Override
            public void click(CustomTextField.ClickEvent event) {
                try {
                    if (netSalesFormulaLookup == null) {
                        netSalesFormulaLookup = new NetSalesFormulaLookup(true, massLookup);
                        UI.getCurrent().addWindow(netSalesFormulaLookup);
                    }
                    netSalesFormulaLookup.addCloseListener(new Window.CloseListener() {
                        @Override
                        public void windowClose(Window.CloseEvent e) {
                            NepFormulaLookUpDTO psNepFormulaDTO = netSalesFormulaLookup.getNepFormulaDTO();
                            massLookup.setValue(psNepFormulaDTO.getNepFormulaNo());
                            final Map<String, String> map = new HashMap<>();
                            map.put("formulaNo", psNepFormulaDTO.getNepFormulaNo());
                            map.put("formulaName", psNepFormulaDTO.getNepFormulaName());
                            map.put("formulaID", psNepFormulaDTO.getNepFormulaID());

                            map.put(Constants.FORMULA_SYSTEM_SID, String.valueOf(psNepFormulaDTO.getNepFormulaSystemID()));
                            massLookup.setData(map);
                            netSalesFormulaLookup = null;
                        }
                    });
                } catch (Exception ex) {
                    logger.error(ex.getMessage());
                }

            }
        });

        massSelect.setVisible(false);
        priceProtectionMassValue.setVisible(false);
        priceProtectionMassDate.setDateFormat(Constants.MM_DD_YYYY);
        priceProtectionMassDate.setVisible(false);
        priceProtectionMassDate.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {

            }
        });

        priceProtectionMassValue.setValue(StringUtils.EMPTY);
        priceProtectionMassDate.setValue(null);
        massSelect.setVisible(false);
        ppMassField.setEnabled(false);
        massLookup.setVisible(false);
        priceProtectionMassValue.setVisible(false);
        priceProtectionMassDate.setVisible(false);
        ppBtnPopulate.setEnabled(false);
        ppBtnAllPopulate.setEnabled(false);
        viewItemDetailsTable.setVisible(false);

        LOGGER.info("End of configureFields method");
    }

    /**
     * Set key & values
     */
    private void setMassPopulateListName() {
        listValueMap.put(Constants.MASS_PRICE_PROTECTION_STATUS, Constants.DB_STATUS);
        listValueMap.put(Constants.MASS_RESET_INTERVAL, Constants.DB_PRICE_TOLERANCE_INTERVAL);
        listValueMap.put(Constants.MASS_RESET_FREQUENCY, Constants.DB_PRICE_TOLERANCE_FRERQUENCY);
        listValueMap.put(Constants.MASS_PRICE_PROTECTION_PRICE_TYPE, Constants.DB_STATUS);
        listValueMap.put(Constants.MASS_PRICE_TOLERANCE_INTERVAL, Constants.DB_PRICE_TOLERANCE_INTERVAL);
        listValueMap.put(Constants.MASS_PRICE_TOLERANCE_FREQUENCY, Constants.DB_PRICE_TOLERANCE_FRERQUENCY);
        listValueMap.put(Constants.MASS_PRICE_TOLERANCE_TYPE, Constants.DB_PRICE_TOLERANCE_TYPE);
        listValueMap.put(Constants.MASS_RESET_TYPE, Constants.DB_RESET_TYPE);
        listValueMap.put(Constants.MASS_RESET_ELIGIBLE, Constants.DB_LOCKED_STATUS);
        listValueMap.put(Constants.MASS_NET_PRICE_TYPE, Constants.DB_LOCKED_STATUS);
    }

    protected void massCheckOnChangeEvent(final Object value) throws Exception {

        if (!String.valueOf(value).isEmpty() && Constants.ENABLE.equals(String.valueOf(value))) {
            ppMassField.setEnabled(true);
            ppBtnPopulate.setEnabled(true);
            ppBtnAllPopulate.setEnabled(true);
        } else if (value != null && Constants.DISABLE.equals(value.toString())) {
            priceProtectionMassValue.setValue(StringUtils.EMPTY);
            priceProtectionMassDate.setValue(null);
            massSelect.setVisible(false);
            ppMassField.setEnabled(false);
            priceProtectionMassValue.setVisible(false);
            priceProtectionMassDate.setVisible(false);
            ppBtnPopulate.setEnabled(false);
            ppBtnAllPopulate.setEnabled(false);
        }

    }

    /**
     * Adds the item details table.
     *
     * @return the vertical layout
     */
    public void addItemDetailsTable() {

        try {
            LOGGER.info("Entering addItemDetailsTable method");
            TableResultCustom tableResultCustom;
             itemDetailsTableLogic.getControlConfig().setPageLengthsAndCaptions(pageLength);
            resultsTableLayout.addComponent(itemDetailsTable);
            HorizontalLayout layout = ResponsiveUtils.getResponsiveControls(itemDetailsTableLogic.createControls(), true);
            resultsTableLayout.addComponent(layout);
            itemDetailsTableLogic.setContainerDataSource(tableContainer);
            itemDetailsTableLogic.configureSearchData(itemDetailsTable, saveContainer, sessionDTO, priceType);
            if(isEditable) {
                tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, contractDashboard, Constants.EDIT);
            } else {
                tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, objView, contractDashboard, Constants.ViewMode);
            }
            itemDetailsTable.setVisibleColumns(tableResultCustom.getObjResult());
            itemDetailsTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
            itemDetailsTable.setColumnCheckBox(Constants.CHECK_BOX, true, false);
            itemDetailsTable.setPageLength(6);
            itemDetailsTableLogic.sinkItemPerPageWithPageLength(false);
            itemDetailsTable.setImmediate(true);
            itemDetailsTable.addStyleName(Constants.FILTER_BAR);
            itemDetailsTable.addStyleName("valo-theme-extfiltertable");
            itemDetailsTable.setSizeFull();
            if(isEditable) {
                itemDetailsTable.setSelectable(true);
                tempDate = new HashMap<String, List>();
                itemDetailsTable.setTableFieldFactory(new ItemPricingGenerator(saveContainer, dates, psDates, tempDate,priceType,sessionDTO));
                itemDetailsTable.setEditable(true);
                itemDetailsTable.setFilterBarVisible(true);
                itemDetailsTable.setFilterGenerator(new ItemDetailsGenerator());
                itemDetailsTable.setFilterDecorator(new ExtDemoFilterDecorator());
                itemDetailsTable.setFilterFieldVisible(Constants.CHECK_BOX, false);
                itemDetailsTable.addStyleName(Constants.TABLE_CHECK_BOX);
                itemDetailsTable.setErrorHandler(new ErrorHandler() {
                    /**
                     * Method used to itemDetailsTable error handling.
                     */
                    @SuppressWarnings("PMD")
                    public void error(final com.vaadin.server.ErrorEvent event) {

                    }
                });
                itemDetailsTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {

                    @Override
                    public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {

                        if (Constants.CHECK_BOX.equals(event.getPropertyId().toString())) {
                            if (event.isChecked()) {
                                try {
                                    IfpLogic.saveToTempIFP(saveContainer.getItemIds());
                                    saveContainer.removeAllItems();

                                    ifpLogic.populateToTempIFP(Constants.CHECK_BOX1, 1, Boolean.TRUE);
                                    itemDetailsTableLogic.configureSearchData(itemDetailsTable, saveContainer, sessionDTO, priceType);
                                    itemDetailsTable.setColumnCheckBox(Constants.CHECK_BOX, true, true);
                                } catch (PortalException ex) {
                                    LOGGER.error(ex.getMessage());
                                } catch (SystemException ex) {
                                    LOGGER.error(ex.getMessage());
                                }

                            } else {
                                try {
                                    IfpLogic.saveToTempIFP(saveContainer.getItemIds());
                                    saveContainer.removeAllItems();

                                    ifpLogic.populateToTempIFP(Constants.CHECK_BOX1, 0, Boolean.TRUE);
                                    itemDetailsTableLogic.configureSearchData(itemDetailsTable, saveContainer, sessionDTO, priceType);
                                    itemDetailsTable.setColumnCheckBox(Constants.CHECK_BOX, true, true);
                                } catch (PortalException ex) {
                                    LOGGER.error(ex.getMessage());
                                } catch (SystemException ex) {
                                    LOGGER.error(ex.getMessage());
                                }

                            }
                        }
                    }
                });

                itemDetailsTable.setColumnCollapsingAllowed(true);
            } else {
                itemDetailsTable.setReadOnly(true);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.info("End of addItemDetailsTable method");

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
            LOGGER.error(ex.getMessage());;
        }
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
                        // --> Disables reloading the container
                        for (Object propertyId : table.getVisibleColumns()) {
                            // table is refreshed when container reloading is disabled
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (Object propertyId : getCollapsibleColumns600Px(table)) {
                            // table is refreshed when container reloading is disabled
                            table.setColumnCollapsed(propertyId, true);
                        }
                        reloadMap.put(1516, true);
                        reloadMap.put(978, true);
                        reloadMap.put(600, true);
                        reloadMap.put(480, false);
                        reloadMap.put(320, true);
                        reloadMap.put(0, true);
                        lazyLoadCriteria.setCustomDirty(true);            // --> Enables reloading the container
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
            LOGGER.error(ex.getMessage());;
        }
    }

    private static Object[] getCollapsibleColumns480Px(CustomePagedFilterTable table) {
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

    private static Object[] getCollapsibleColumns978Px(CustomePagedFilterTable table) {
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

    private static String[] getCollapsibleColumns600Px(CustomePagedFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    private static String[] getCollapsibleColumnsDefault1515Px(CustomePagedFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
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
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<Object>(Arrays.asList(visibleColumns));
        for (int i = 0; i < 6; i++) {
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

    /**
     * Btn export logic.
     */
    public void btnExportLogic() throws SystemException, PortalException, Exception {
        LOGGER.info("Entering btnExportLogic method");
        excelExport.addClickListener(new ClickListener() {
            /**
             * Invoked when a button is clicked
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                 if (Constants.DETAILS.equals(level.getValue())) {
                     try {
                         List list = ifpLogic.getLazyItemPricingDeatils(0, 0, null, true, itemDetailsTableLogic.getRecord(), false, Boolean.FALSE);
                         int recordCount = 0;
                         if (list != null && !list.isEmpty()) {
                             recordCount = Integer.valueOf(String.valueOf(list.get(0)));
                         }
                         if (recordCount > 0) {
                             createWorkSheet("Items_Pricing_Details", itemDetailsTable, recordCount);
                         }
                     } catch (Exception ex) {
                         java.util.logging.Logger.getLogger(ItemPricing.class.getName()).log(Level.SEVERE, null, ex);
                     }

        } else if (Constants.PRICE_PROTECTION.equals(level.getValue())) {
                     try {
                         List list = ifpLogic.getLazyPriceProtectionDeatils(0, 0, null, true, ppTableLogic.getRecord(), null);
                         int recordCount = 0;
                         if (list != null && !list.isEmpty()) {
                             recordCount = Integer.valueOf(String.valueOf(list.get(0)));
                         }
                         if (recordCount > 0) {
                           createWorkSheet("Price_Protection_Details", priceProtectionTable, recordCount);
                         }
                     } catch (Exception ex) {
                         java.util.logging.Logger.getLogger(ItemPricing.class.getName()).log(Level.SEVERE, null, ex);
                     }

        } else if (Constants.HEADER.equals(level.getValue()) && Constants.HISTORY.equals(view.getValue())) {
                     try {
                         createWorkSheet(Constants.HISTORY, historyPriceProtectionTable, 0);
                     } catch (Exception ex) {
                         java.util.logging.Logger.getLogger(ItemPricing.class.getName()).log(Level.SEVERE, null, ex);
                     }
        } else {
            MessageBox.showPlain(Icon.INFO, "Excel Export", "Excel is not supported for Header Level", new MessageBoxListener() {
                /**
                 * After clicking button, function will be executed.
                 */
                @SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {

                }
            }, ButtonId.OK);
        }
            }
        });
       

        LOGGER.info("End of btnExportLogic method");
    }

    public void createWorkSheet(String moduleName, ExtCustomTable resultTable, int count) throws SystemException, PortalException, Exception {
        String[] header = resultTable.getColumnHeaders();

        header = (String[]) ArrayUtils.removeElement(header, StringUtils.EMPTY);
        ExcelExportforBB.createWorkSheet(header, count, this, UI.getCurrent(), moduleName.toUpperCase());
    }

    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws SystemException, PortalException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {

        LOGGER.info("Entering createWorkSheetContent method");

        if (Constants.DETAILS.equals(level.getValue())) {
            List<Object[]> returnList = ifpLogic.getLazyItemPricingDeatils(start, end, null, false, itemDetailsTableLogic.getRecord(), false, Boolean.FALSE);
            ifpLogic.setExcel(true);
            List exportCompany = ifpLogic.getCustomizedPricingDTO(returnList, priceType, false, itemDetailsTableLogic.getRecord());
            ifpLogic.setExcel(false);
            Object[] columns = itemDetailsTable.getVisibleColumns();

            columns = ArrayUtils.removeElement(columns, Constants.CHECK_BOX);

            ExcelExportforBB.createFileContent(columns, exportCompany, printWriter);
        } else if (Constants.PRICE_PROTECTION.equals(level.getValue())) {
            List<Object[]> returnList = ifpLogic.getLazyPriceProtectionDeatils(start, end, null, false, ppTableLogic.getRecord(), null);
            List<TempPricingDTO> exportCompany = ifpLogic.getCustomizedPriceProtectionDTO(returnList, priceProtectionPriceType, recordCheck.getCaption());
            Object[] columns = priceProtectionTable.getVisibleColumns();
            columns = ArrayUtils.removeElement(columns, Constants.CHECK_BOX);

            ExcelExportforBB.createFileContent(columns, exportCompany, printWriter);
        } else if (Constants.HEADER.equals(level.getValue()) && Constants.HISTORY.equals(view.getValue())) {

        }
        LOGGER.info("End of createWorkSheetContent method");
    }

    /**
     * Creates the header row.
     *
     * @param pwValue the pw value
     */
    public void createHeaderRow(final PrintWriter pwValue) {
        LOGGER.info("Entering createHeaderRow method");
        if (Constants.DETAILS.equals(level.getValue())) {
            final String[] header = itemDetailsTable.getColumnHeaders();
            for (int headerCount = 1; headerCount < header.length; headerCount++) {
                if (headerCount < header.length - 1) {
                    pwValue.print(header[headerCount] + ExcelExportUtil.COMMA);
                } else {
                    pwValue.println(header[headerCount]);
                }
            }
        } else if (Constants.PRICE_PROTECTION.equals(level.getValue())) {
            final String[] header = ContractUtils.PRICE_PROTECTION_COL_HEADER;
            for (int headerCount = 1; headerCount < header.length; headerCount++) {
                if (headerCount < header.length - 1) {
                    pwValue.print(header[headerCount] + ExcelExportUtil.COMMA);
                } else {
                    pwValue.println(header[headerCount]);
                }
            }

        } else if (Constants.HEADER.equals(level.getValue()) && Constants.HISTORY.equals(view.getValue())) {

        }
        LOGGER.info("End of createHeaderRow method");
    }

    /**
     * Send converted file to user.
     *
     * @param app the app
     * @param fileToExport the file to export
     * @param exportFileName the export file name
     * @return true, if send converted file to user
     * @throws java.io.FileNotFoundException
     */
    protected boolean sendConvertedFileToUser(final UI app, final File fileToExport, final String exportFileName) throws FileNotFoundException {
        LOGGER.info("Entering sendConvertedFileToUser method");

        TemporaryFileDownloadResource resource;
        resource = new TemporaryFileDownloadResource(app, exportFileName, ExcelExportUtil.EXCEL_MIME_TYPE, fileToExport);
        Page.getCurrent().open(resource, ExcelExportUtil.WINDOW_NAME, true);
        LOGGER.info("End of sendConvertedFileToUser method");
        return true;
    }

    public void focusMassCheck() {
        this.massCheck.focus();
    }

    public void removeItemsFromSaveContainer() {
        this.saveContainer.removeAllItems();
    }

    public void resetFields() {
        this.massField.setValue(null);
        this.massValue.setValue(StringUtils.EMPTY);
        this.massDate.setValue(null);
        this.massText.setValue(StringUtils.EMPTY);

        this.massCheck.setValue(Constants.DISABLE);
    }

    /**
     * Adds the item details table.
     *
     * @return the vertical layout
     */
    public void addItemDetailsTableForView() {

        LOGGER.info("Entering addItemDetailsTable method");
        TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, objView, contractDashboard, Constants.ViewMode);
        viewItemDetailsTable.setVisible(true);
        resultsTableLayout.addComponent(viewItemDetailsTable);
        HorizontalLayout tempLayout = ResponsiveUtils.getResponsiveControls(itemDetailsViewTableLogic.createControls());
        //ContractUtils.getCustomizedComboBox(
                //(ComboBox) ((CssLayout) tempLayout.getComponent(0)).getComponent(1));
        resultsTableLayout.addComponent(tempLayout);
        itemDetailsViewTableLogic.setContainerDataSource(itemDetailsResultsBean);
        viewItemDetailsTable.setVisibleColumns(tableResultCustom.getObjResult());
        viewItemDetailsTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
        viewItemDetailsTable.setImmediate(true);
        viewItemDetailsTable.setSelectable(true);
        viewItemDetailsTable.setSizeFull();
        itemDetailsViewTableLogic.setPageLength(6);
        itemDetailsViewTableLogic.sinkItemPerPageWithPageLength(false);
        viewItemDetailsTable.setErrorHandler(new ErrorHandler() {
            /**
             * Metod used to itemDetailsTable error handler.
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {

            }
        });
        LOGGER.info("End of addItemDetailsTable method");

    }

    public void loadTempIfpOnView(String value) {
        itemDetailsViewTableLogic.setRecord(value);
        itemDetailsViewTableLogic.configureSearchData(sessionDTO, priceType);
        TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, objView, contractDashboard, Constants.ViewMode);
        viewItemDetailsTable.setVisibleColumns(tableResultCustom.getObjResult());
        viewItemDetailsTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
    }

    private static Object[] getCollapsibleColumns480Px(ExtFilterTable table) {
        table.setColumnCollapsingAllowed(true);
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
        table.setColumnCollapsingAllowed(true);
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
        table.setColumnCollapsingAllowed(true);
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
            if (table.isColumnCollapsed(propertyId) == Page.getCurrent().getBrowserWindowWidth() < 800) {
                result = false;
            }
        }
        return result;
    }

    private static Object[] getCollapsibleOneColumn(ExtFilterTable table) {
        table.setColumnCollapsingAllowed(true);
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
        for (int i = 0, j = 8; i < j; i++) {
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

    private void addResponsiveGrid(Map<String, AppPermission> fieldContractDashboardHM) {
        try {

            List<Object> resultList = contractLogic.getFieldsForSecurity(UISecurityUtil.CONTRACT_DASHBOARD, "Item Pricing");
            List<Object> resultList1 = contractLogic.getFieldsForSecurity(UISecurityUtil.CONTRACT_DASHBOARD, "Item Pricing Header");

            final Map<String, AppPermission> fieldContractDashboardHM1 = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.CONTRACT_DASHBOARD + Constants.COMMA + "Item Pricing Header", false);

            commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout, fieldContractDashboardHM, Constants.EDIT);
            commonSecurityLogic.removeComponentOnPermission(resultList1, cssLayoutHeader, fieldContractDashboardHM1, Constants.EDIT);

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    private void configurePriceProtectionTable() {
        ppResultsTableLayout.addComponent(priceProtectionTable);
        HorizontalLayout tempLayout = ResponsiveUtils.getResponsiveControls(ppTableLogic.createControls());
        ContractUtils.getCustomizedComboBox(
                (ComboBox) ((CssLayout) tempLayout.getComponent(0)).getComponent(1));
        ppResultsTableLayout.addComponent(tempLayout);

        ppTableLogic.setContainerDataSource(ppTableContainer);
        priceProtectionTable.setSizeFull();
        ppTableLogic.configureSearchData(priceProtectionTable, ppSaveContainer, sessionDTO, priceProtectionPriceType, isEditable);
        if(isEditable) {
            priceProtectionTable.setVisibleColumns(ContractUtils.PRICE_PROTECTION_COL);
            priceProtectionTable.setColumnHeaders(ContractUtils.PRICE_PROTECTION_COL_HEADER);
        } else {
            priceProtectionTable.setVisibleColumns(ContractUtils.PRICE_PROTECTION_COL_VIEW);
            priceProtectionTable.setColumnHeaders(ContractUtils.PRICE_PROTECTION_COL_HEADER_VIEW);
        }

        priceProtectionTable.setColumnCheckBox(Constants.CHECK_BOX, true, false);
        ppTableLogic.setPageLength(6);
        ppTableLogic.sinkItemPerPageWithPageLength(false);
        priceProtectionTable.setImmediate(true);
        priceProtectionTable.addStyleName(Constants.TABLE_CHECK_BOX);
        priceProtectionTable.addStyleName(Constants.FILTER_BAR);
        priceProtectionTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        priceProtectionTable.setSizeFull();
        if(isEditable) {
            priceProtectionTable.setFilterGenerator(new PSFilterGenerator(priceProtectionPriceType));
            priceProtectionTable.setSelectable(true);
            priceProtectionTable.setEditable(true);
            ppTempDate = new HashMap<String, List>();
            priceProtectionTable.setTableFieldFactory(new PriceProtectionFieldFactory(priceProtectionTable, ppSaveContainer, psDates, ppTempDate,sessionDTO,pricingBinderEdit));
            priceProtectionTable.setFilterBarVisible(true);
            priceProtectionTable.setFilterDecorator(new ExtDemoFilterDecorator());
            priceProtectionTable.setFilterFieldVisible(Constants.CHECK_BOX, false);
            priceProtectionTable.setImmediate(true);
            priceProtectionTable.addStyleName(Constants.TABLE_CHECK_BOX);
            priceProtectionTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {

                @Override
                public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {

                    if (Constants.CHECK_BOX.equals(event.getPropertyId().toString())) {
                        if (event.isChecked()) {
                            try {
                                ifpLogic.populateToTempIFPforPP(Constants.CHECK_BOX1, 1, Boolean.TRUE);
                                ppTableLogic.configureSearchData(priceProtectionTable, ppSaveContainer, sessionDTO, priceProtectionPriceType, isEditable);
                                priceProtectionTable.setColumnCheckBox(Constants.CHECK_BOX, true, true);
                            } catch (Exception ex) {
                                logger.error(ex.getMessage());
                            }

                        } else {
                            try {

                                ifpLogic.populateToTempIFPforPP(Constants.CHECK_BOX1, 0, Boolean.TRUE);
                                ppTableLogic.configureSearchData(priceProtectionTable, ppSaveContainer, sessionDTO, priceProtectionPriceType, isEditable);
                                priceProtectionTable.setColumnCheckBox(Constants.CHECK_BOX, true, false);
                            } catch (Exception ex) {
                                logger.error(ex.getMessage());
                            }
                        }
                    }
                }
            });
        } else {
            massLayout.setVisible(false);
            priceProtectionTable.setReadOnly(true);
            recordCheck.addItems(Constants.HISTORY, Constants.CURRENT, Constants.FUTURE, Constants.PENDING);
        }
    }

    private void configurePriceProtectionViewTable() {
        LOGGER.info("Entering configurePriceProtectionViewTable method");
        massLayout.setVisible(false);
        priceProtectionTable.setVisible(true);
        recordCheck.addItems("HistoryConstants.COMMACurrent", Constants.FUTURE, Constants.PENDING);
        ppTableLogic.setContainerDataSource(ppTableContainer);
        priceProtectionTable.setSizeFull();
        ppTableLogic.configureSearchData(priceProtectionTable, ppSaveContainer, sessionDTO, priceProtectionPriceType, isEditable);
        priceProtectionTable.setVisibleColumns(ContractUtils.PRICE_PROTECTION_COL_VIEW);
        priceProtectionTable.setColumnHeaders(ContractUtils.PRICE_PROTECTION_COL_HEADER_VIEW);
        priceProtectionTable.setPageLength(7);
        priceProtectionTable.setImmediate(true);
        priceProtectionTable.setSelectable(true);
        priceProtectionTable.setSizeFull();
        priceProtectionTable.setErrorHandler(new ErrorHandler() {
            /**
             * Metod used to itemDetailsTable error handler.
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {

            }
        });
        LOGGER.info("End of configurePriceProtectionViewTable method");
    }

    private void configureHistoryTable() {
        TableResultCustom tableResultCustom;
        historyPriceProtectionTable.setContainerDataSource(historyLazyContainer);
        if(isEditable) {
            tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultListHistory, objViewHistory, contractDashboardHistory, Constants.EDIT);
        } else {
            tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultListHistory, objViewHistory, contractDashboardHistory, Constants.ViewMode);
        }
        historyPriceProtectionTable.setVisibleColumns(tableResultCustom.getObjResult());
        historyPriceProtectionTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
        historyPriceProtectionTable.markAsDirty();
        historyPriceProtectionTable.setPageLength(6);
        historyPriceProtectionTable.sinkItemPerPageWithPageLength(false);
        historyPriceProtectionTable.setImmediate(true);
        historyPriceProtectionTable.addStyleName(Constants.FILTER_BAR);
        historyPriceProtectionTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        historyPriceProtectionTable.setSizeFull();
        if(isEditable) {
            historyPriceProtectionTable.setSelectable(true);
            historyPriceProtectionTable.setEditable(true);
            historyPriceProtectionTable.setFilterBarVisible(true);
            historyPriceProtectionTable.setFilterDecorator(new ExtDemoFilterDecorator());
            historyPriceProtectionTable.setSizeFull();
            historyPriceProtectionTable.setColumnCollapsingAllowed(true);
        } else {
            historyPriceProtectionTable.setReadOnly(true);
        }
        HorizontalLayout tempLayout = ResponsiveUtils.getResponsiveControls(historyPriceProtectionTable.createControls());
        historyResultsTableLayout.addComponent(tempLayout);
        
    }

    private void configureHistoryViewTable() {
        LOGGER.info("Entering configurePriceProtectionViewTable method");
        historyPriceProtectionTable.setVisible(true);
        historyPriceProtectionTable.markAsDirty();
        historyPriceProtectionTable.setContainerDataSource(historyLazyContainer);
        TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultListHistory, objViewHistory, contractDashboardHistory, Constants.ViewMode);
        historyPriceProtectionTable.setVisibleColumns(tableResultCustom.getObjResult());
        historyPriceProtectionTable.setColumnHeaders(tableResultCustom.getObjResultHeader());

        historyPriceProtectionTable.setVisibleColumns(ContractUtils.PRICE_PROTECTION_HISTORY);
        historyPriceProtectionTable.setColumnHeaders(ContractUtils.PRICE_PROTECTION_HISTORY_HEADER);
        historyPriceProtectionTable.setPageLength(7);
        historyPriceProtectionTable.setImmediate(true);
        historyPriceProtectionTable.setSelectable(true);
        historyPriceProtectionTable.setSizeFull();
        historyPriceProtectionTable.setErrorHandler(new ErrorHandler() {
            /**
             * Metod used to itemDetailsTable error handler.
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {

            }
        });
        LOGGER.info("End of configurePriceProtectionViewTable method");
    }

    private Button ppAddBtnPopulate() {
        LOGGER.info("Entering addppBtnPopulate method");

        ppBtnPopulate.setReadOnly(true);
        ppBtnPopulate.setErrorHandler(new ErrorHandler() {
            /**
             * Method used for populate button logic and its listener.
             *
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error(event.toString());
            }
        });

        ppBtnPopulate.addClickListener(new ClickListener() {
            /**
             * Method used to populate button logic and its listener.
             */
            public void buttonClick(final ClickEvent event) {
                LOGGER.info("Entering ppBtnPopulate buttonClick method");
                String value = StringUtils.EMPTY;
                final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                if (ppMassField.getValue() != null) {
                    try {
                        ppFieldMass = String.valueOf(ppMassField.getValue());
                        if (ppFieldMass.equals(Constants.MASS_PRICE_PROTECTION_STATUS)
                                || ppFieldMass.equals(Constants.MASS_PRICE_TOLERANCE_INTERVAL)
                                || ppFieldMass.equals(Constants.MASS_PRICE_TOLERANCE_FREQUENCY)
                                || ppFieldMass.equals(Constants.MASS_PRICE_TOLERANCE_TYPE)
                                || ppFieldMass.equals(Constants.MASS_RESET_INTERVAL)
                                || ppFieldMass.equals(Constants.MASS_RESET_FREQUENCY)
                                || ppFieldMass.equals(Constants.MASS_NET_PRICE_TYPE)
                                || ppFieldMass.equals(Constants.MASS_RESET_TYPE)
                                || ppFieldMass.equals(Constants.MASS_RESET_ELIGIBLE)) {
                            if (massSelect.getValue() == null) {
                                value = Constants.ZEROSTRING;
                            } else {
                                value = String.valueOf(((HelperDTO) (massSelect.getValue())).getId());
                            }
                        } else if (ppFieldMass.equals(Constants.MASS_RESET_DATE)) {
                            if (priceProtectionMassDate.getValue() == null) {
                                ppBinder.getErrorDisplay().setError("Please provide date and try again.");
                            } else {
                                value = fmt.format(priceProtectionMassDate.getValue());
                            }
                        } else if (ppFieldMass.equals(Constants.MASS_PRICE_PROTECTION_START_DATE)) {
                            if (priceScheduleStartDate.getValue() == null) {
                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Please Provide Price schedule Start date and try again.");
                                return;
                            } else if (priceProtectionMassDate.getValue() == null) {
                                binder.getErrorDisplay().setError("Please provide date and try again.");
                            } else if (priceProtectionMassDate.getValue().before(priceScheduleStartDate.getValue())) {
                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Start date cannot be before " + new SimpleDateFormat("MM/dd/YYYY").format(priceScheduleStartDate.getValue()));
                                return;
                            } else if (priceScheduleEndDate.getValue() != null && priceProtectionMassDate.getValue().after(priceScheduleEndDate.getValue())) {
                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Start date cannot be after " + new SimpleDateFormat("MM/dd/YYYY").format(priceScheduleEndDate.getValue()));
                                return;
                            } else {
                                value = fmt.format(priceProtectionMassDate.getValue());
                            }

                        } else if (ppFieldMass.equals(Constants.MASS_PRICE_PROTECTION_END_DATE)) {
                            if (priceScheduleStartDate.getValue() == null) {
                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Please Provide Price schedule Start date and try again.");
                                return;
                            } else if (priceProtectionMassDate.getValue() == null) {
                                binder.getErrorDisplay().setError("Please provide date and try again.");
                            } else if (priceScheduleEndDate.getValue() != null && priceProtectionMassDate.getValue().after(priceScheduleEndDate.getValue())) {
                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "End date cannot be after " + new SimpleDateFormat("MM/dd/YYYY").format(priceScheduleEndDate.getValue()));
                                return;
                            } else if (priceProtectionMassDate.getValue().before(priceScheduleStartDate.getValue())) {
                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "End date cannot be before " + new SimpleDateFormat("MM/dd/YYYY").format(priceScheduleStartDate.getValue()));
                                return;
                            } else {
                                value = fmt.format(priceProtectionMassDate.getValue());
                            }
                        } else if (ppFieldMass.equals(Constants.MASS_NEP_FORMULA)) {
                            if (massLookup != null) {
                                Map<String, String> nepmap = (HashMap) massLookup.getData();
                                value = String.valueOf(nepmap.get(Constants.FORMULA_SYSTEM_SID));
                                nepmap.clear();
                            }
                        } else if (ppFieldMass.equals(Constants.MASS_NET_PRICE_TYPE_FORMULA)) {
                            if (massLookup != null) {
                                Map<String, String> nptmap = (HashMap) massLookup.getData();
                                value = String.valueOf(nptmap.get(Constants.FORMULA_SYSTEM_SID));
                                nptmap.clear();
                            }
                        } else if (ppFieldMass.equals(Constants.MASS_PRICE_PROTECTION_PRICE_TYPE)) {
                            if (massSelect.getValue() != null) {
                                value = ifpLogic.getIntegerForSelection(String.valueOf(massSelect.getValue()));
                            } else {
                                value = Constants.ZEROSTRING;
                            }
                        } else if (priceProtectionMassValue.getValue() != null) {
                            value = priceProtectionMassValue.getValue();
                        }
                        IfpLogic.saveToTempTable(ppSaveContainer.getItemIds());
                        ppSaveContainer.removeAllItems();
                        ifpLogic.populateToTempIFPforPP(ppMassField.getValue(), value, Boolean.FALSE);
                        loadPpTempIfp();
                    } catch (SystemException ex) {
                        final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                        LOGGER.error(errorMsg);
                        AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                    } catch (PortalException ex) {
                        final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                        LOGGER.error(errorMsg);
                    }
                } else {

                    AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, Constants.POPULATE_MSG);
                }
                LOGGER.info("End of btnPopulate buttonClick method");
            }
        });
        LOGGER.info("End of addBtnPopulate method");
        return ppBtnPopulate;
    }

    public void loadPpTempIfp() {
        try {
            ppTableLogic.configureSearchData(priceProtectionTable, ppSaveContainer, sessionDTO, priceProtectionPriceType, isEditable);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

    }

    private Button ppAddBtnPopulateAll() {
        LOGGER.info("Entering addBtnPopulate method");

        ppBtnAllPopulate.setReadOnly(true);
        ppBtnAllPopulate.setErrorHandler(new ErrorHandler() {
            /**
             * Method used for populate button logic and its listener.
             *
             */
            @SuppressWarnings("PMD")
            @Override
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error(event.toString());
            }
        });

        ppBtnAllPopulate.addClickListener(new ClickListener() {
            /**
             * Method used to populate button logic and its listener.
             */
            @Override
            public void buttonClick(final ClickEvent event) {
                LOGGER.info("Entering btnPopulate buttonClick method");
                String value = StringUtils.EMPTY;
                final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                if (ppMassField.getValue() != null) {
                    try {
                        ppFieldMass = String.valueOf(ppMassField.getValue());
                        if (ppFieldMass.equals(Constants.MASS_PRICE_PROTECTION_STATUS)
                                || ppFieldMass.equals(Constants.MASS_PRICE_TOLERANCE_INTERVAL)
                                || ppFieldMass.equals(Constants.MASS_PRICE_TOLERANCE_FREQUENCY)
                                || ppFieldMass.equals(Constants.MASS_PRICE_TOLERANCE_TYPE)
                                || ppFieldMass.equals(Constants.MASS_RESET_INTERVAL)
                                || ppFieldMass.equals(Constants.MASS_RESET_FREQUENCY)
                                || ppFieldMass.equals(Constants.MASS_NET_PRICE_TYPE)
                                || ppFieldMass.equals(Constants.MASS_RESET_TYPE)
                                || ppFieldMass.equals(Constants.MASS_RESET_ELIGIBLE)) {
                            if (massSelect.getValue() == null) {
                                value = Constants.ZEROSTRING;
                            } else {
                                value = String.valueOf(((HelperDTO) (massSelect.getValue())).getId());
                            }
                        } else if (ppFieldMass.equals(Constants.MASS_RESET_DATE)) {
                            if (priceProtectionMassDate.getValue() == null) {
                                ppBinder.getErrorDisplay().setError("Please provide date and try again.");
                            } else {
                                value = fmt.format(priceProtectionMassDate.getValue());
                            }
                        } else if (ppFieldMass.equals(Constants.MASS_PRICE_PROTECTION_START_DATE)) {
                            if (priceScheduleStartDate.getValue() == null) {

                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Please Provide Price schedule Start date and try again.");

                                return;
                            } else if (priceProtectionMassDate.getValue() == null) {
                                binder.getErrorDisplay().setError("Please provide date and try again.");
                            } else if (priceProtectionMassDate.getValue().before(priceScheduleStartDate.getValue())) {

                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Start date cannot be before " + new SimpleDateFormat("MM/dd/YYYY").format(priceScheduleStartDate.getValue()));
                                return;
                            } else if (priceScheduleEndDate.getValue() != null && priceProtectionMassDate.getValue().after(priceScheduleEndDate.getValue())) {
                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Start date cannot be after " + new SimpleDateFormat("MM/dd/YYYY").format(priceScheduleEndDate.getValue()));
                                return;
                            } else {
                                value = fmt.format(priceProtectionMassDate.getValue());
                            }

                        } else if (ppFieldMass.equals(Constants.MASS_PRICE_PROTECTION_END_DATE)) {
                            if (priceScheduleStartDate.getValue() == null) {

                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Please Provide Price schedule Start date and try again.");

                                return;
                            } else if (priceProtectionMassDate.getValue() == null) {
                                binder.getErrorDisplay().setError("Please provide date and try again.");
                            } else if (priceScheduleEndDate.getValue() != null && priceProtectionMassDate.getValue().after(priceScheduleEndDate.getValue())) {

                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "End date cannot be after " + new SimpleDateFormat("MM/dd/YYYY").format(priceScheduleEndDate.getValue()));
                                return;
                            } else if (priceProtectionMassDate.getValue().before(priceScheduleStartDate.getValue())) {
                                AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "End date cannot be before " + new SimpleDateFormat("MM/dd/YYYY").format(priceScheduleStartDate.getValue()));

                                return;
                            } else {
                                value = fmt.format(priceProtectionMassDate.getValue());
                            }
                        } else if (ppFieldMass.equals(Constants.MASS_NEP_FORMULA)) {
                            if (massLookup != null) {
                                Map<String, String> nepmap = (HashMap) massLookup.getData();

                                value = String.valueOf(nepmap.get(Constants.FORMULA_SYSTEM_SID));
                                nepmap.clear();
                            }
                        } else if (ppFieldMass.equals(Constants.MASS_NET_PRICE_TYPE_FORMULA)) {
                            if (massLookup != null) {
                                Map<String, String> nptmap = (HashMap) massLookup.getData();

                                value = String.valueOf(nptmap.get(Constants.FORMULA_SYSTEM_SID));
                                nptmap.clear();
                            }
                        } else if (ppFieldMass.equals(Constants.MASS_PRICE_PROTECTION_PRICE_TYPE)) {
                            if (massSelect.getValue() != null) {
                                value = ifpLogic.getIntegerForSelection(String.valueOf(massSelect.getValue()));
                            } else {

                                value = Constants.ZEROSTRING;
                            }
                        } else if (priceProtectionMassValue.getValue() != null) {
                            value = priceProtectionMassValue.getValue();
                        }
                        IfpLogic.saveToTempTable(ppSaveContainer.getItemIds());
                        ppSaveContainer.removeAllItems();
                        ifpLogic.populateToTempIFPforPP(ppMassField.getValue(), value, Boolean.TRUE);
                        loadPpTempIfp();
                    } catch (SystemException ex) {
                        final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                        LOGGER.error(errorMsg);
                        AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                    } catch (PortalException ex) {
                        final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                        LOGGER.error(errorMsg);
                    }
                } else {

                    AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, Constants.POPULATE_MSG);
                }
                LOGGER.info("End of btnPopulate buttonClick method");
            }
        });
        LOGGER.info("End of addBtnPopulate method");
        return ppBtnAllPopulate;
    }

    /**
     * Gets the contract master binder.
     *
     * @return the contract master binder
     */
    public CustomFieldGroup getPricingBinderEdit() {
        return pricingBinderEdit;
    }

    /**
     * Sets the contract master binder.
     *
     * @param contractMasterBinder the contract master binder
     */
    public void setPricingBinderEdit(final CustomFieldGroup pricingBinderEdit) {
        this.pricingBinderEdit = pricingBinderEdit;
    }

    /**
     * Gets the contract master.
     *
     * @return the contract master
     */
    public PriceScheduleDto getPriceScheduleMaster() {
        return priceScheduleMaster;
    }

    public void loadDates(final Date startDate, final Date endDate) {
        dates[0] = startDate;
        dates[1] = endDate;
        priceScheduleStartDate.validate();
        priceScheduleEndDate.validate();
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

                                    AbstractNotificationUtils.getWarningNotification(Constants.START_DATE_SP, "Start Date cannot be before " + format.format(contractSD));

                                    detachListeners(field);
                                    ((PopupDateField) field).setValue(null);
                                    attachListeners(field, "START_DATE");
                                } else if (dates[1] != null && ((PopupDateField) field).getValue().after((Date) dates[1])) {

                                    AbstractNotificationUtils.getWarningNotification(Constants.START_DATE_SP, "Start Date cannot be after " + format.format((Date) dates[1]));

                                    detachListeners(field);
                                    ((PopupDateField) field).setValue(null);
                                    attachListeners(field, "START_DATE");
                                }
                            }
                        } else if (component.equals("END_DATE")) {
                            if (event != null) {
                                Date contractSD = (Date) dates[0];
                                if (dates[1] == null && !((PopupDateField) field).getValue().before(contractSD)) {
                                    ((PopupDateField) field).setDescription(com.stpl.app.contract.global.util.CommonUtils.convertDateToString(((PopupDateField) field).getValue()));
                                } else if (((PopupDateField) field).getValue().before(contractSD)) {
                                    AbstractNotificationUtils.getWarningNotification("End Date", "End Date cannot be before " + format.format(contractSD));
                                    detachListeners(field);
                                    ((PopupDateField) field).setValue(null);
                                    attachListeners(field, "END_DATE");
                                } else if (dates[1] != null && ((PopupDateField) field).getValue().after(dates[1])) {
                                    AbstractNotificationUtils.getWarningNotification("End Date", "End Date cannot be after " + format.format(dates[1]));
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

    /**
     * The Class DateValidator.
     */
    @SuppressWarnings("rawtypes")
    public class DateValidator extends AbstractValidator {

        String field;
        /**
         * The Constant serialVersionUID.
         */
        private static final long serialVersionUID = 1L;

        /**
         * The Constructor.
         */
        public DateValidator() {

            super(StringUtils.EMPTY);
        }

        /**
         * The Constructor.
         *
         * @param errorMessage the error message
         */
        public DateValidator(final String errorMessage, final String field) {
            super(errorMessage);
            this.field = field;
        }

        /**
         * Method used to click the text field validate and its listener.
         *
         * @param value the value
         * @throws InvalidValueException the invalid value exception
         */
        @Override
        public void validate(final Object value) throws Validator.InvalidValueException {
            LOGGER.info("Entering validate method");

            if (priceScheduleStartDate.getValue() != null && priceScheduleEndDate.getValue() != null) {
                if (priceScheduleStartDate.getValue().after(priceScheduleEndDate.getValue())) {
                    throw new Validator.InvalidValueException("End date should be greater than start date in Rebate Schedule Information Tab");
                } else if (priceScheduleStartDate.getValue().equals(priceScheduleEndDate.getValue())) {
                    throw new Validator.InvalidValueException("Start date and End date are equal in Price Schedule Tab");
                }
            }
            if (dates.length > 0) {
                if ("StartDate".equals(field) && priceScheduleStartDate.getValue() != null) {
                    if (dates[0] != null && priceScheduleStartDate.getValue().before((Date) dates[0])) {
                        throw new Validator.InvalidValueException("Select PS Start date after " + format.format(dates[0]));
                    } else if (dates[1] != null && priceScheduleStartDate.getValue().after((Date) dates[1])) {
                        throw new Validator.InvalidValueException("Select PS Start date before " + format.format(dates[1]));
                    }
                }
                if ("EndDate".equals(field) && priceScheduleEndDate.getValue() != null) {
                    if (dates[1] != null && priceScheduleEndDate.getValue().after((Date) dates[1])) {
                        throw new Validator.InvalidValueException("Select PS End date before " + format.format(dates[1]));
                    }
                }
            }
            LOGGER.info("End of validate method");
        }

        /**
         * Method return boolean isValidValue .
         *
         * @param value the value
         * @return true, if is valid value
         */
        @Override
        protected boolean isValidValue(final Object value) {
            LOGGER.info("Entering isValidValue method");

            if (priceScheduleStartDate.getValue() != null && priceScheduleEndDate.getValue() != null) {
                return priceScheduleStartDate.getValue().compareTo(priceScheduleEndDate.getValue()) <= 0;
            }
            LOGGER.info("End of isValidValue method");
            return true;
        }

        /**
         * Method used to get type.
         *
         * @return the type
         */
        @Override
        public Class getType() {
            return null;
        }
    }
}
