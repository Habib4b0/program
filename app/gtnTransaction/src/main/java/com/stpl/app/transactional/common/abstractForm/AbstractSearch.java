package com.stpl.app.transactional.common.abstractForm;

import com.stpl.app.model.AccrualMaster;
import com.stpl.app.transactional.common.dto.AdjustmentDetailsFilterGenerator;
import com.stpl.app.security.impl.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.transactional.common.dto.ARPOutboundDTO;
import com.stpl.app.transactional.common.dto.AccrualdetailsDTO;
import com.stpl.app.transactional.common.dto.AdjustmentDetailsDTO;
import com.stpl.app.transactional.common.dto.ArmOutboundDTO;
import com.stpl.app.transactional.common.dto.DetailsDTO;
import com.stpl.app.transactional.common.dto.FilterGenerator;
import com.stpl.app.transactional.common.logic.TransactionalSearchLogic;
import com.stpl.app.transactional.common.logic.CommonLogic;
import com.stpl.app.transactional.common.logic.SearchLogic;
import com.stpl.app.transactional.common.ui.form.AdjustmentDetails.PrivateOrPublicView;
import com.stpl.app.transactional.common.ui.form.AdjustmentDetails.RedemptionPeriod;
import com.stpl.app.transactional.common.ui.form.AdjustmentDetails.SaveView;
import com.stpl.app.transactional.common.util.SearchCriteria;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.util.AbstractNotificationUtils;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.CommonUtils;
import com.stpl.app.util.BCPExcelUtility;
import com.stpl.app.util.ConstantUtil;
import com.stpl.app.util.DoubleToStringConverter;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.app.util.ExcelExportUtil;
import com.stpl.app.util.HelperListUtil;
import com.stpl.app.util.IntegerToStringConverter;
import com.stpl.app.util.QueryDataUtils;
import com.stpl.app.util.OnDemandFileDownloader;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExcelExportforBB;
import static com.stpl.ifs.util.ExcelExportforBB.QUOTE;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.TableResultCustom;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.Validator;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ItemClickEvent;
import static com.vaadin.server.Sizeable.UNITS_PERCENTAGE;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.UserError;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.ExtCustomTable.ColumnCheckListener;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author sooriya.lakshmanan
 * @param <BEANTYPE>
 */
public abstract class AbstractSearch extends CustomComponent {

    CustomTextField privateViewTextField = null;
    CustomTextField publicViewTextField = null;
    List<List> searchList = new ArrayList<List>();
    List<String> headerList = new ArrayList<String>();
    Set<String> headerListInventoryRemove = new HashSet<String>();
    List<Object> columnList = new ArrayList<Object>();
    List<DetailsDTO> moduleDetailsValue = null;
    List<DetailsDTO> primaryDetailsValue = new ArrayList<DetailsDTO>();
    protected Object[] tableColumnArr;
    protected Object[] excelVisibleColumnArr;
    protected String[] tableHeaderArr;
    @UiField("mainLayout")
    VerticalLayout mainLayout;
    @UiField("cssLayout")
    protected CssLayout cssLayout;
    @UiField("buttonLayout")
    private HorizontalLayout buttonLayout;
    @UiField("excelBtn")
    private Button excelExport;
    @UiField("removeBtn")
    private Button removeBtn;
    @UiField("reset")
    private Button reset;
    @UiField("invalidButtonLayout")
    private HorizontalLayout invalidButtonLayout;
    @UiField("searchFieldVerticalLayout")
    private VerticalLayout searchFieldVerticalLayout;
    @UiField("tableLayout")
    private VerticalLayout tableLayout;
    @UiField("resultsPanel")
    private Panel resultsPanel;
    @UiField("reprocessBtn")
    private Button reprocessBtn;
    @UiField("generateBtn")
    private Button generateBtn;
    private Label space = new Label(ConstantUtil.NBSP, ContentMode.HTML);
    List<DetailsDTO> newList = null;
    @UiField("cssLayout1")
    protected CssLayout cssLayout1;
    @UiField("cssLayout2")
    protected CssLayout cssLayout2;
    @UiField("gridLayout1")
    protected GridLayout gridLayout1;
    @UiField("gridLayout2")
    protected GridLayout gridLayout2;
    @UiField("searchFieldLayout2")
    protected HorizontalLayout searchFieldLayout2;
    @UiField("searchFieldLayout1")
    protected HorizontalLayout searchFieldLayout1;
    @UiField("summaryPanel")
    private Panel summaryPanel;
    @UiField("detailsPanel")
    private Panel detailsPanel;
    public boolean ispublicView = true;
    PrivateOrPublicView lookUp = null;
    protected PrivateOrPublicView privateLookUp;
    protected PrivateOrPublicView publicLookUp;
    @UiField("searchFieldVerticalLayout2")
    private VerticalLayout searchFieldVerticalLayout2;

    final ThemeResource excelImage = new ThemeResource("../../icons/excel.png");
    protected Map<Object, Object> searchValues = new HashMap<Object, Object>();
    protected Map<Object, Object> tableValues = new HashMap<Object, Object>();
    protected CustomMenuBar.CustomMenuItem deductionValueItem;
    protected String tableValue = null;
    protected String tableValue1 = null;
    protected String forecastTypeValue = null;
    protected boolean demandTypeChange = false;
    protected boolean invalidDemand = false;
    public String demandTypeValue = null;
    protected String optionvalue = null;
    public static ResourceBundle resouceBundle = ResourceBundle.getBundle("properties.validation");
    public static String EXCEL_NAME = "Forcast";
    CommonLogic logic = new CommonLogic();
    CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();
    public BeanItemContainer<DetailsDTO> container = new BeanItemContainer<DetailsDTO>(DetailsDTO.class);
    private static Map<String, Integer> excelOrderMap = new HashMap<String, Integer>();
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(AbstractSearch.class);
    /**
     * The error msg.
     */
    @UiField("errorMsg")
    public ErrorLabel errorMsg;
    protected String tableName = StringUtils.EMPTY;
    protected String invalidTableName = StringUtils.EMPTY;
    protected SearchCriteria searchCriteria = new SearchCriteria();
    protected DetailsDTO primaryDto;
    CommonLogic commonLogic = new CommonLogic();
    protected String recordInvalidName = StringUtils.EMPTY;
    protected BeanItemContainer itemCont;
    protected String generatedColumn = StringUtils.EMPTY;
    protected String viewName = StringUtils.EMPTY;
    protected String removeId = StringUtils.EMPTY;
    protected String tableId = StringUtils.EMPTY;
    protected String stagingTable = StringUtils.EMPTY;
    protected String viewNameSid = StringUtils.EMPTY;
    protected String selectedInvalidModule = StringUtils.EMPTY;
    String invalidInventoryTableName = StringUtils.EMPTY;
    public String Addcheckbox = "0";
    public BeanItemContainer searchResultbeans;
    public TransactionalSearchLogic tableLogic = new TransactionalSearchLogic();
    public CustomExtPagedTable table = new CustomExtPagedTable(tableLogic);
    List<DetailsDTO> primaryValueList = new ArrayList();
    String className = StringUtils.EMPTY;
    public boolean loadEnable = false;
    int arpRecordCount = 0;
    int j = 0;

    private CustomTextField redemptionPeriodToDate = new CustomTextField();

    public AdjustmentDetailsDTO adjustmentDetailsDTO = new AdjustmentDetailsDTO();
    
    public List<Integer> selectedRecords = new ArrayList<>();
    
    public int selectedRecordSid = 0;
    int arpCount;

    /**
     * Constructor
     *
     * @param moduleDetails
     * @param primaryValueList
     * @throws Exception
     */
    public AbstractSearch(final List<DetailsDTO> moduleDetails, final List<DetailsDTO> primaryValueList) {
        LOGGER.debug("Inside Abstarct Search");
        this.moduleDetailsValue = moduleDetails;
        this.primaryValueList = primaryValueList;
        primaryDto = (DetailsDTO) primaryValueList.get(0);
        tableLogic.configureSearchLogic(primaryDto);
        if (ConstantUtil.ACCRUAL_MASTER.equals(primaryDto.getValidation())) {
            EXCEL_NAME = "Accrual Details Master";
        } else if (ConstantUtil.CPI_INDEX.equals(primaryDto.getValidation())) {
            EXCEL_NAME = "Customer Price Index";
        } else if (ConstantUtil.GL_SPACE_BALANCE.equals(primaryDto.getValidation())) {
            EXCEL_NAME = "GL Balance Master";
        } else if (ConstantUtil.SALES_SPACE_MASTER.equals(primaryDto.getValidation())) {
            EXCEL_NAME = "Sales Forecast Master";
        } else {
            EXCEL_NAME = primaryDto.getValidation() == null || StringUtils.isBlank(primaryDto.getValidation()) ? "Transaction" : primaryDto.getValidation();
        }
        tableName = CommonLogic.getUpperCamelString(moduleDetails.get(0).getTableName(), false);
        VaadinSession.getCurrent().setAttribute("modName", moduleDetails.get(0).getTableName());

        if (ConstantUtil.ST_CFF_OUTBOUND.equals(tableName)) {
            HelperListUtil helperListUtil = HelperListUtil.getInstance();
            helperListUtil.loadValuesWithListName("CFF");
        }
        if (ConstantUtil.VW_CUSTOMER_GTS_FORECAST.equals(tableName)) {
            HelperListUtil helperListUtil = HelperListUtil.getInstance();
            helperListUtil.loadValuesWithListName("CUSTOMER_GTS_FORECAST");
        }

        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/ui/common/searchform.xml"), this));
        init();
        LOGGER.debug("End of Abstarct Search");
    }

    private void init() {
        try {
            selectedInvalidModule = tableName;
            tableLayout.addComponent(table);

            propertyId();
            getDynamicComponent();
            getTableColumns();
            addToTable();
            exportButton();
            removeBtn();
            addPagination(tableLayout);
            configDeductionLevel();
            tableLayout.setSpacing(true);
            if (tableName.equalsIgnoreCase(ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL) || tableName.equalsIgnoreCase(ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL)) {
                summaryPanel.setVisible(true);
                detailsPanel.setCaption("Search Criteria Detail");
            } else {
                summaryPanel.setVisible(false);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * getDynamicComponent() Creating Dynamic fields
     *
     * @throws PortalException,SystemException
     */
    private List<List> getDynamicComponent() throws PortalException, SystemException {
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.USERID));
        final Map<String, AppPermission> fieldForecastSalesHM;

        if (!tableName.equalsIgnoreCase(ConstantUtil.INVALID_RECORD_COUNT)) {
            fieldForecastSalesHM = stplSecurity.getFieldOrColumnPermission(userId, primaryDto.getValidation(), false);
        } else {
            fieldForecastSalesHM = stplSecurity.getFieldOrColumnPermission(userId,
                    ConstantUtil.INVALID_INVENTORYVIEW_TABLE.equalsIgnoreCase(invalidTableName) ? ConstantUtil.IVLD_INVENTORY_WITHDRAWAL_SUMMARY : invalidTableName, false);
        }
        headerList = new ArrayList<String>();
        columnList = new ArrayList<Object>();
        for (final DetailsDTO moduleProperty : moduleDetailsValue) {
            if (ConstantUtil.IVLD_RETURN_RESERVE.equals(invalidTableName) && moduleProperty.getPropertyName().equals("brandId")) {
                focus();
            }
            if (((((AppPermission) fieldForecastSalesHM.get(moduleProperty.getPropertyName())) == null) ? false
                    : ((AppPermission) fieldForecastSalesHM.get(moduleProperty.getPropertyName())).isSearchFlag()) || (ConstantUtil.Button.equals(moduleProperty.getCategoryName()))) {

                if (ConstantUtil.COLUMN.equals(moduleProperty.getCategoryName())) {
                    if (ConstantUtil.IVLD_RETURN_RESERVE.equals(invalidTableName)) {
                        if (moduleProperty.getDisplayName().equals("ADD CHG DEL INDICATOR")) {
                            moduleProperty.setPropertyName("addChgDelIndicator");
                        }
                        if (moduleProperty.getPropertyName().equals("returnReserveIntfId")) {
                            moduleProperty.setDisplayName("Invalid Record ID");
                        }
                    }
                    if ("checkRecord".equals(moduleProperty.getPropertyName())) {
                        headerList.add(0, moduleProperty.getDisplayName());
                        columnList.add(0, moduleProperty.getPropertyName());
                    } else {
                        headerList.add(moduleProperty.getDisplayName());
                        columnList.add(moduleProperty.getPropertyName());
                    }
                } else {
                    singleFieldSet(new Label() {
                        {
                            setValue(moduleProperty.getDisplayName());
                        }
                    },
                            componentCreater(moduleProperty));
                }
            }

        }
        if (ConstantUtil.INVALID_GTS_CUSTOMER.equals(invalidTableName)) {
            List<String> headList = new ArrayList<String>();
            List<String> columlist = new ArrayList<String>();
            headList.add("Forecast Interface ID");
            columlist.add("customerGtsForecastIntfId");
            for (String headlist1 : headList) {
                headerList.remove(headlist1);
            }
            for (String collist1 : columlist) {
                columnList.remove(collist1);
            }
        }
        if (ConstantUtil.INVALID_CUSTOMER_GTS_ACTUAL.equals(invalidTableName)) {
            List<String> headList = new ArrayList<String>();
            List<String> columlist = new ArrayList<String>();
            headList.add("Interface ID");
            columlist.add("customerGtsActualIntfId");
            for (String headlist1 : headList) {
                headerList.remove(headlist1);
            }
            for (String collist1 : columlist) {
                columnList.remove(collist1);
            }
        }
        if (ConstantUtil.DEMANDVIEW_TABLE.equalsIgnoreCase(tableName) || ConstantUtil.IVLD_DEMANDVIEW_TABLE.equalsIgnoreCase(invalidTableName)) {
            List<String> headList = new ArrayList<String>();
            headList.add(ConstantUtil.FORECASTING_NAME);
            headList.add(ConstantUtil.FORECAST_SPACE_VERSION);
            headList.add(ConstantUtil.DEMANDTYPE);
            headList.add(ConstantUtil.UNCAPTURED_UNIT);
            headList.add(ConstantUtil.UNCAPTURED_UNIT_RATIO);
            headList.add(ConstantUtil.INVENTORY_UNITS_CHANGE);
            List<String> columlist = new ArrayList<String>();
            columlist.add(ConstantUtil.FORECAST_NAME);
            columlist.add(ConstantUtil.FORECAST_VER);
            columlist.add(ConstantUtil.UN_CAPTURED_UNITS);
            columlist.add(ConstantUtil.UN_CAPTURED_UNITS_RATIO);
            columlist.add(ConstantUtil.INVENTORYUNITCHANGE);
            columlist.add(ConstantUtil.IS_FORECAST);
            for (String headlist1 : headList) {
                headerList.remove(headlist1);
            }
            for (String collist1 : columlist) {
                columnList.remove(collist1);
            }
        }
        if (ConstantUtil.INVENTORYVIEW_TABLE.equalsIgnoreCase(tableName) || ConstantUtil.INVALID_INVENTORYVIEW_TABLE.equalsIgnoreCase(tableName)) {
            List<String> headList = new ArrayList<String>();
            List<String> columlist = new ArrayList<String>();
            if (ConstantUtil.SUMMARY.equalsIgnoreCase(primaryDto.getLevelDdlb())) {
                headList.add(ConstantUtil.COMPANYID);
                headList.add(ConstantUtil.COMPANYNAME);

                columlist.add(ConstantUtil.COMPANY_ID);
                columlist.add(ConstantUtil.COMPANY_NAME);
            }
            if (ConstantUtil.PROJECTION.equals(primaryDto.getTypeDdlb())) {
                headList.add(ConstantUtil.UNITS_ON_HANDS);
                headList.add(ConstantUtil.AMOUNT_SPACE_ON_HAND);
                headList.add(ConstantUtil.QUANTITY_SPACE_ON_ORDER);
                headList.add(ConstantUtil.AMOUNT_SPACE_ON_ORDER);
                headList.add(ConstantUtil.QUANTITY_SPACE_RECEIVED);
                headList.add(ConstantUtil.AMOUNT_SPACE_RECEIVED);

                columlist.add(ConstantUtil.UNITS_ON_HAND);
                columlist.add(ConstantUtil.AMOUNT_ON_HAND);
                columlist.add(ConstantUtil.QUANTITY_ON_ORDER);
                columlist.add(ConstantUtil.AMOUNT_ON_ORDER);
                columlist.add(ConstantUtil.QUANTITY_RECEIVED);
                columlist.add(ConstantUtil.AMOUNT_RECEIVED);
            }
            if (ConstantUtil.ACTUALS.equals(primaryDto.getTypeDdlb())) {
                headList.add(ConstantUtil.PRICE_DUP);
                headList.add(ConstantUtil.FORECASTING_NAME);
                headList.add(ConstantUtil.FORECAST_SPACE_VER);
                headList.add(ConstantUtil.ORGANIZATION_SPACE_KEY);

                columlist.add(ConstantUtil.PRICE);
                columlist.add(ConstantUtil.FORECAST_NAME);
                columlist.add(ConstantUtil.FORECAST_VER);
                columlist.add(ConstantUtil.ORGANIZATION_KEY);
            }
            for (String headlist1 : headList) {
                headerList.remove(headlist1);
            }
            for (String collist1 : columlist) {
                columnList.remove(collist1);
            }
        }
        searchList.add(headerList);
        searchList.add(columnList);
        return searchList;
    }

    private Component componentCreater(final DetailsDTO moduleProperty) throws PortalException, SystemException {
        if (ConstantUtil.TextField.equals(moduleProperty.getCategoryName())) {
            return new TextField() {
                {
                    setData(moduleProperty.getPropertyName());
                    if (!moduleProperty.getValidation().isEmpty()) {
                        String[] validationValue = moduleProperty.getValidation().split(ConstantUtil.COMMA);
                        String temp = getMessage(validationValue[0]);
                        String[] strArr = temp == null ? null : temp.split(",");
                        String message = StringUtils.EMPTY;
                        if (strArr != null) {
                            message = getMessage(validationValue[0] + "msg");

                            addValidator(new StringLengthValidator(moduleProperty.getDisplayName() + " " + message, Integer.valueOf(strArr[0]), Integer.valueOf(strArr[NumericConstants.ONE]), Boolean.valueOf(strArr[NumericConstants.TWO])));
                        }
                        if (validationValue.length > 0) {
                            String regexvalue = getMessage(validationValue[NumericConstants.ONE]);
                            message = getMessage(validationValue[NumericConstants.ONE] + "msg");
                            if (regexvalue != null) {
                                addValidator(new RegexpValidator(regexvalue, moduleProperty.getDisplayName() + " " + message));
                            }
                        }
                        setImmediate(true);
                        setValidationVisible(true);
                    }
                    System.out.println("tableName-------" + tableName);
                    switch (tableName) {
                        case ConstantUtil.ST_CFF_OUTBOUND:
                            if (moduleProperty.getPropertyName().equals("financialForecastId")) {
                                focus();
                            }
                            break;
                        case ConstantUtil.ARP_OUTBOUND:
                            if (moduleProperty.getPropertyName().equals("arpId")) {
                                focus();
                            }
                            break;
                        case ConstantUtil.RETURN_RESERVE:
                            if (moduleProperty.getPropertyName().equals("brandId")) {
                                focus();
                            }
                            break;
                        case ConstantUtil.VW_ITEM_MASTER:
                            if (moduleProperty.getPropertyName().equals(ConstantUtil.ITEMID)) {
                                focus();
                            }
                            break;
                        case ConstantUtil.VW_ITEM_IDENTIFIER:
                            if (moduleProperty.getPropertyName().equals(ConstantUtil.ITEMID)) {
                                focus();
                            }
                            break;
                        case ConstantUtil.VW_ITEM_PRICING:
                            if (moduleProperty.getPropertyName().equals(ConstantUtil.ITEMID)) {
                                focus();
                            }
                            break;
                        case ConstantUtil.VW_COMPANY_MASTER:
                            if (moduleProperty.getPropertyName().equals(ConstantUtil.COMPANY_ID)) {
                                focus();
                            }
                            break;
                        case ConstantUtil.VW_COMPANY_IDENTIFIER:
                            if (moduleProperty.getPropertyName().equals(ConstantUtil.COMPANY_ID)) {
                                focus();
                            }
                            break;
                        case ConstantUtil.VW_COMPANY_PARENT_DETAILS:
                            if (moduleProperty.getPropertyName().equals(ConstantUtil.COMPANY_ID)) {
                                focus();
                            }
                            break;
                        case ConstantUtil.VW_COMPANY_TRADE_CLASS:
                            if (moduleProperty.getPropertyName().equals(ConstantUtil.COMPANY_ID)) {
                                focus();
                            }
                            break;
                    }
                }
            };
        } else if (ConstantUtil.CustomTextField.equals(moduleProperty.getCategoryName())) {
            if (ConstantUtil.REDEMPTION_PERIOD.equalsIgnoreCase(moduleProperty.getPropertyName())) {
                return new CustomTextField() {
                    {
                        final CustomTextField tempRedemptionPeriod = this;
                        setData(moduleProperty.getPropertyName());
                        addClickListener(new ClickListener() {

                            @Override
                            public void click(CustomTextField.ClickEvent event) {
                                RedemptionPeriod redemptionPeriod = new RedemptionPeriod(tempRedemptionPeriod, redemptionPeriodToDate);
                                UI.getCurrent().addWindow(redemptionPeriod);
                            }
                        });

                    }
                };
            } else if (ConstantUtil.PRIVATE_VIEW.equalsIgnoreCase(moduleProperty.getPropertyName())) {
                return new CustomTextField() {
                    {
                        privateViewTextField = this;
                        addClickListener(new ClickListener() {

                            @Override
                            public void click(CustomTextField.ClickEvent event) {
                                privateLookUp = new PrivateOrPublicView(ConstantUtil.PRIVATE, privateViewTextField, publicViewTextField);
                                getUI().addWindow(privateLookUp);
                                privateLookupCloseListener();
                            }
                        });
                        setData(moduleProperty.getPropertyName());
                    }
                };
            } else if (ConstantUtil.PUBLIC_VIEW.equalsIgnoreCase(moduleProperty.getPropertyName())) {
                return new CustomTextField() {
                    {
                        addStyleName("searchIcon");
                        publicViewTextField = this;
                        addClickListener(new ClickListener() {

                            @Override
                            public void click(CustomTextField.ClickEvent event) {

                                publicLookUp = new PrivateOrPublicView(ConstantUtil.PUBLIC, privateViewTextField, publicViewTextField);
                                publicLookupCloseListener();
                                getUI().addWindow(publicLookUp);
                            }

                        });
                        setData(moduleProperty.getPropertyName());
                    }
                };
            }
        } else if (ConstantUtil.CustomMenuBar.equals(moduleProperty.getCategoryName())) {
            return new CustomMenuBar() {
                {
                    setScrollable(true);
                    setPageLength(NumericConstants.TEN);
                    setData(moduleProperty.getPropertyName());
                    setStyleName("customMenuBarWidth-199");
                }
            };
        } else if (ConstantUtil.PopupDateField.equals(moduleProperty.getCategoryName())) {
            return new PopupDateField() {
                {
                    setData(moduleProperty.getPropertyName());
                    if (moduleProperty.getPropertyName().contains("accrualPeriod")) {
                        setStyleName("dateFieldCenter");
                        addStyleName("datefieldcentered");
                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase("invalidFromDate")) {
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_CENTER);
                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase("invalidToDate")) {
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_CENTER);
                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase(ConstantUtil.START_DATE)) {
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_CENTER);
                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase("START_DATE")) {
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_CENTER);
                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase("END_DATE")) {
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_CENTER);
                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase("CREATED_DATE")) {
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_CENTER);
                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase("MODIFIED_DATE")) {
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_CENTER);
                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase("parentStartDate")) {
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_CENTER);
                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase("parentEndDate")) {
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_CENTER);
                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase("priorParentStartDate")) {
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_CENTER);

                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase("PARENT_START_DATE")) {
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_CENTER);

                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase("PARENT_END_DATE")) {
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_CENTER);

                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase("PRIOR_PARENT_START_DATE")) {
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_CENTER);

                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase("LAST_UPDATED_DATE")) {
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_CENTER);

                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase("COMPANY_START_DATE")) {
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_CENTER);

                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase("COMPANY_END_DATE")) {
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_CENTER);

                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase("tradeClassStartDate")) {
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_CENTER);

                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase("tradeClassEndDate")) {
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_CENTER);

                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase("priorTradeClassStartDate")) {
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_CENTER);

                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase("TRADE_CLASS_START_DATE")) {
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_CENTER);

                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase("TRADE_CLASS_END_DATE")) {
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_CENTER);

                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase("PRIOR_TRADE_CLASS_START_DATE")) {
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_CENTER);

                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase("ITEM_START_DATE")) {
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_CENTER);

                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase("ITEM_END_DATE")) {
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_CENTER);

                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase("PACKAGE_SIZE_INTRO_DATE")) {
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_CENTER);

                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase("ACQUISITION_DATE")) {
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_CENTER);

                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase("AUTHORIZED_GENERIC_START_DATE")) {
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_CENTER);

                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase("AUTHORIZED_GENERIC_END_DATE")) {
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_CENTER);

                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase("FIRST_SALE_DATE")) {
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_CENTER);

                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase("MARKET_TERMINATION_DATE")) {
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_CENTER);

                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase("NEW_FORMULATION_START_DATE")) {
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_CENTER);

                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase("NEW_FORMULATION_END_DATE")) {
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_CENTER);

                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase("PEDIATRIC_EXCLUSIVE_START_DATE")) {
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_CENTER);

                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase("PEDIATRIC_EXCLUSIVE_END_DATE")) {
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_CENTER);

                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase("CLOTTING_FACTOR_START_DATE")) {
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_CENTER);

                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase("CLOTTING_FACTOR_END_DATE")) {
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_CENTER);

                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase("DISCONTINUATION_DATE")) {
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_CENTER);

                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase("LAST_LOT_EXPIRATION_DATE")) {
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_CENTER);

                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase("DIVESTITURE_DATE")) {
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_CENTER);

                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase("NON_FEDERAL_EXPIRATION_DATE")) {
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_CENTER);
                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase("arpCreationDate")) {
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_CENTER);
                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase(ConstantUtil.CREATED_DATE)) {
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_CENTER);
                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase(ConstantUtil.GL_DATE)) {
                        addStyleName(ValoTheme.DATEFIELD_ALIGN_CENTER);

                    }
                    setDateFormat(ConstantUtil.MMDDYYYY);
                }
            };
        } else if (ConstantUtil.COMBOBOX.equals(moduleProperty.getCategoryName())) {
            return new ComboBox() {
                {
                    if (primaryDto.getValidation().equals(ConstantUtil.GL_SPACE_BALANCE)) {
                        setImmediate(true);
                        final List<HelperDTO> list = getIsActive();
                        for (final HelperDTO helperDTO : list) {
                            addItem(helperDTO.getDescription());
                        }
                        setNullSelectionAllowed(true);
                        setNullSelectionItemId(ConstantUtil.SELECT_ONE);
                        select(ConstantUtil.SELECT_ONE);
                        setData(moduleProperty.getPropertyName());
                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase(ConstantUtil.TYPE) && ConstantUtil.ST_CFF_OUTBOUND.equals(tableName)) {
                        final List<HelperDTO> helperList = logic.getHelperResults(ConstantUtil.CFF_TYPE);
                        for (int i = 0; i < helperList.size(); i++) {
                            final HelperDTO helperDTO = helperList.get(i);

                            addItem(helperDTO.getDescription());
                            setNullSelectionAllowed(true);
                            setNullSelectionItemId(ConstantUtil.SELECT_ONE);
                            select(ConstantUtil.SELECT_ONE);
                            setData(moduleProperty.getPropertyName());
                        }
                    } else if (tableName.equals(ConstantUtil.ARP_OUTBOUND)) {
                        String listType = StringUtils.EMPTY;
                        if (moduleProperty.getPropertyName().equals(ConstantUtil.ACCOUNT_TYPE)) {
                            listType = "ACCOUNT_TYPE";
                        } else {
                            listType = "ACCOUNT";
                        }
                        setImmediate(true);
                        addItem(ConstantUtil.SELECT_ONE);
                        final List<HelperDTO> helperList = logic.getHelperResults(listType);
                        for (int i = 0; i < helperList.size(); i++) {
                            final HelperDTO helperDTO = helperList.get(i);
                            addItem(helperDTO.getDescription());
                            setNullSelectionAllowed(true);
                            setNullSelectionItemId(ConstantUtil.SELECT_ONE);
                            select(ConstantUtil.SELECT_ONE);
                        }
                    } else if ((tableName.equals(ConstantUtil.ACCURAL_MASTER) || invalidTableName.equals(ConstantUtil.INVALID_ACCURAL_INBOUND))
                            && moduleProperty.getPropertyName().equalsIgnoreCase(ConstantUtil.ACCRUAL_TYPE)) {
                        setImmediate(true);
                        addItem(ConstantUtil.SELECT_ONE);
                        if (tableName.equals(ConstantUtil.ACCURAL_MASTER)) {
                            addItem("Base");
                            addItem("Fixed Dollar");
                            addItem("Recon");
                        }
                        addItem(ConstantUtil.OTHER);
                        setNullSelectionAllowed(true);
                        setNullSelectionItemId(ConstantUtil.SELECT_ONE);
                        select(ConstantUtil.SELECT_ONE);
                        setData(moduleProperty.getPropertyName());
                        addValueChangeListener(new ValueChangeListener() {

                            @Override
                            public void valueChange(Property.ValueChangeEvent event) {

                                if (!ConstantUtil.OTHER.equals(String.valueOf(event.getProperty().getValue()))) {
                                    Addcheckbox = "1";

                                } else {
                                    Addcheckbox = "0";
                                }
                            }
                        });

                    } else if ((moduleProperty.getPropertyName().equalsIgnoreCase(ConstantUtil.DEMAND_TYPE))
                            || (moduleProperty.getPropertyName().equalsIgnoreCase(ConstantUtil.TYPE))
                            || (moduleProperty.getPropertyName().equalsIgnoreCase(ConstantUtil.LEVEL))) {
                        addItem(ConstantUtil.SELECT_ONE);
                        setNullSelectionAllowed(true);
                        setNullSelectionItemId(ConstantUtil.SELECT_ONE);

                        final List<String> list = Arrays.asList(moduleProperty.getValidation().split(","));
                        Collections.sort(list);
                        for (String searchList1 : list) {
                            addItem(searchList1);
                        }
                        select(list.get(0));
                        demandTypeValue = list.get(0);
                        if (moduleProperty.getPropertyName().equalsIgnoreCase(ConstantUtil.TYPE) && (primaryDto.getValidation().equalsIgnoreCase(ConstantUtil.INVENTORY)
                                || primaryDto.getValidation().equalsIgnoreCase(ConstantUtil.INVENTORY))) {
                            setData(ConstantUtil.IS_FORECAST);
                        } else if (moduleProperty.getPropertyName().equalsIgnoreCase(ConstantUtil.LEVEL)) {
                            setData("isMaster");
                        } else {
                            setData(moduleProperty.getPropertyName());
                        }
                        if (moduleProperty.getPropertyName().equalsIgnoreCase(ConstantUtil.DEMAND_TYPE)) {
                            addValueChangeListener(new ValueChangeListener() {

                                @Override
                                public void valueChange(Property.ValueChangeEvent event) {
                                    demandTypeValue = String.valueOf(event.getProperty().getValue());
                                    demandTypeChange = true;
                                    commonLogic.setInvalidAdjustedDemand(false);
                                    if (ConstantUtil.DEMAND.equals(demandTypeValue)) {
                                        commonLogic.setAdjustDemand(false);
                                        AbstractComponentCreater.setAdjustDemand(false);
                                        if (invalidDemand) {
                                            propertyId();
                                            commonLogic.setInvalidAdjustedDemand(false);
                                        }
                                    } else {
                                        commonLogic.setAdjustDemand(true);
                                        AbstractComponentCreater.setAdjustDemand(true);
                                        if (invalidDemand) {
                                            propertyId();
                                            commonLogic.setInvalidAdjustedDemand(true);
                                        }
                                    }
                                }
                            });
                        }
                        if ((moduleProperty.getPropertyName().equalsIgnoreCase(ConstantUtil.TYPE)) || (moduleProperty.getPropertyName().equalsIgnoreCase(ConstantUtil.LEVEL))) {
                            addValueChangeListener(new ValueChangeListener() {

                                @Override
                                public void valueChange(Property.ValueChangeEvent event) {
                                    try {
                                        tableValue1 = String.valueOf(event.getProperty().getValue());
                                        AbstractComponentCreater.setTableValue(tableValue1);
                                        commonLogic.setTableValue(tableValue1);
                                        if (tableValue1.equalsIgnoreCase(ConstantUtil.ACTUALS)) {
                                            if (moduleProperty.getPropertyName().equalsIgnoreCase(ConstantUtil.TYPE)) {
                                                primaryDto.setTypeDdlb(tableValue1);
                                                commonLogic.setInventoryTableValue(tableValue1);
                                            } else {
                                                getIterativeComponents1(cssLayout, false, moduleProperty.getPropertyName());
                                            }
                                        } else if (tableValue1.equalsIgnoreCase(ConstantUtil.PROJECTION)) {
                                            if (moduleProperty.getPropertyName().equalsIgnoreCase(ConstantUtil.TYPE)) {

                                                primaryDto.setTypeDdlb(tableValue1);
                                                commonLogic.setInventoryTableValue(tableValue1);
                                            } else {
                                                getIterativeComponents1(cssLayout, true, moduleProperty.getPropertyName());
                                            }
                                        } else if (tableValue1.equalsIgnoreCase(ConstantUtil.SUMMARY)) {
                                            primaryDto.setLevelDdlb(tableValue1);
                                            getIterativeComponents1(cssLayout, false, moduleProperty.getPropertyName());

                                        } else if (tableValue1.equalsIgnoreCase(ConstantUtil.DETAILS)) {
                                            primaryDto.setLevelDdlb(tableValue1);
                                            getIterativeComponents1(cssLayout, true, moduleProperty.getPropertyName());
                                        }
                                    } catch (Exception ex) {
                                        LOGGER.error(ex);
                                    }
                                }
                            });
                        }

                    }

                    if (moduleProperty.getPropertyName().equalsIgnoreCase(ConstantUtil.ORGANIZATION_KEY)
                            && !demandTypeValue.isEmpty()) {
                        setImmediate(true);
                        addItem(ConstantUtil.SELECT_ONE);
                        final List list1 = commonLogic.organisationKeyList();
                        Collections.sort(list1);
                        for (final Object val : list1) {
                            addItem(String.valueOf(val));
                        }
                        setNullSelectionAllowed(true);
                        setNullSelectionItemId(ConstantUtil.SELECT_ONE);
                        select(ConstantUtil.SELECT_ONE);
                        setData(moduleProperty.getPropertyName());

                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase(ConstantUtil.OUT_LINER)) {
                        setImmediate(true);
                        final List<HelperDTO> list = getIsoutline();
                        for (final HelperDTO helperDTO : list) {
                            addItem(helperDTO.getDescription());
                        }

                        setNullSelectionAllowed(true);
                        setNullSelectionItemId(ConstantUtil.SELECT_ONE);
                        select(ConstantUtil.SELECT_ONE);
                        setData(moduleProperty.getPropertyName());

                    } else if (moduleProperty.getPropertyName().equalsIgnoreCase(ConstantUtil.ORGANIZATION_KEY)
                            && primaryDto.getValidation().equalsIgnoreCase(ConstantUtil.INVENTORY)) {
                        setImmediate(true);
                        addItem(ConstantUtil.SELECT_ONE);
                        final List list = commonLogic.organisationKeyList();
                        for (final Object val : list) {
                            addItem(String.valueOf(val));
                        }
                        setNullSelectionAllowed(true);
                        setNullSelectionItemId(ConstantUtil.SELECT_ONE);
                        select(ConstantUtil.SELECT_ONE);
                        setData(moduleProperty.getPropertyName());
                    } else if (moduleProperty.getPropertyName().equals("ndc9") || moduleProperty.getPropertyName().equals("ndc8") || moduleProperty.getPropertyName().equals("brand") || moduleProperty.getPropertyName().equals("pricingCodeQualifierName") || moduleProperty.getPropertyName().equals("itemQualifier") || moduleProperty.getPropertyName().equals("identifierCodeQualifierName") || moduleProperty.getPropertyName().equals("priorTradeClass")) {
                        String sql = StringUtils.EMPTY;
                        List<String> ndcList = new ArrayList<>();
                        setImmediate(true);
                        addItem(ConstantUtil.SELECT_ONE);
                        setNullSelectionAllowed(true);
                        setNullSelectionItemId(ConstantUtil.SELECT_ONE);
                        select(ConstantUtil.SELECT_ONE);
                        setData(moduleProperty.getPropertyName());

                        if (moduleProperty.getPropertyName().equals("itemQualifier")) {
                            sql = "select DISTINCT IQ.ITEM_QUALIFIER_NAME from ITEM_QUALIFIER IQ WHERE IQ.ITEM_QUALIFIER_NAME IS NOT NULL";
                            List<Object> list = new ArrayList<>();
                            list = HelperTableLocalServiceUtil.executeSelectQuery(sql);
                            for (Object obj : list) {
                                ndcList.add(obj.toString());
                            }

                        } else if (moduleProperty.getPropertyName().equals("identifierCodeQualifierName")) {
                            sql = "select DISTINCT IQ.ITEM_QUALIFIER_NAME from  ITEM_QUALIFIER IQ WHERE IQ.ITEM_QUALIFIER_NAME IS NOT NULL";
                            List<Object> list = new ArrayList<>();
                            list = HelperTableLocalServiceUtil.executeSelectQuery(sql);
                            for (Object obj : list) {
                                ndcList.add(obj.toString());
                            }
                        } else if (moduleProperty.getPropertyName().equals("pricingCodeQualifierName")) {
                            sql = "select DISTINCT IQ.ITEM_PRICING_QUALIFIER_NAME from ITEM_PRICING_QUALIFIER IQ WHERE IQ.ITEM_PRICING_QUALIFIER_NAME IS NOT NULL";
                            List<Object> list = new ArrayList<>();
                            list = HelperTableLocalServiceUtil.executeSelectQuery(sql);
                            for (Object obj : list) {
                                ndcList.add(obj.toString());
                            }
                        } else if (moduleProperty.getPropertyName().equals("priorTradeClass")) {
                            sql = "select DISTINCT PT.PRIOR_TRADE_CLASS from COMPANY_TRADE_CLASS PT WHERE PT.PRIOR_TRADE_CLASS IS NOT NULL";
                            List<Object> list = new ArrayList<>();
                            list = HelperTableLocalServiceUtil.executeSelectQuery(sql);
                            for (Object obj : list) {
                                ndcList.add(obj.toString());
                            }
                        }

                        addItems(ndcList);
                    } else {
                        if (tableName.equalsIgnoreCase(ConstantUtil.INVALID_RECORD_COUNT)) {
                            primaryDetailsValue.addAll(moduleDetailsValue);
                            if (ConstantUtil.TABLE.equalsIgnoreCase(moduleProperty.getPropertyName())) {
                                setImmediate(true);
                                List table = logic.getTableName();
                                for (final Object name : table) {
                                    addItem(name);
                                }
                                setData(moduleProperty.getPropertyName());
                            }
                            focus();

                            setNullSelectionAllowed(true);
                            setNullSelectionItemId(ConstantUtil.SELECT_ONE);
                            select(ConstantUtil.SELECT_ONE);
                            addValueChangeListener(new ValueChangeListener() {

                                @Override
                                public void valueChange(Property.ValueChangeEvent event) {
                                    try {
                                        if (ConstantUtil.TABLE.equalsIgnoreCase(moduleProperty.getPropertyName())) {
                                            tableValue = ConstantUtil.INVENTORY_WITHDRAWAL_SUMMARY.equals(String.valueOf(event.getProperty().getValue()).replaceAll("\\s", StringUtils.EMPTY)) ? ConstantUtil.INVENTORY : String.valueOf(event.getProperty().getValue()).replaceAll("\\s", StringUtils.EMPTY);
                                            newList = new ArrayList();
                                            if (tableValue.equals("ProjectedGTS–CustomerLevel")) {
                                                tableValue = ConstantUtil.CUSTOMERSALES;
                                            } else if (tableValue.equals("ActualGTS–CustomerLevel")) {
                                                tableValue = ConstantUtil.ACTUALGTSCUSTOMERSALES;
                                            } else if (tableValue.equals("AccrualInboundDetails")) {
                                                tableValue = ConstantUtil.ACCRUAL_DETAILS;
                                            }
                                            EXCEL_NAME = tableValue;
                                            if (ConstantUtil.DEMAND.equals(tableValue)) {
                                                invalidDemand = true;

                                            }
                                            getComponentsForInvalidRecordCount();
                                            if (!"null".equals(tableValue)) {
                                                getTableForInvalidRecordCount();
                                            }
                                            propertyId();
                                            Iterator<Component> componentIterator = cssLayout.getComponentIterator();
                                            for (final DetailsDTO moduleProperty : moduleDetailsValue) {
                                                if ((ConstantUtil.IBID_ITEM_MASTER.equals(invalidTableName) || ConstantUtil.IVID_ITEM_IDENTIFIER.equals(invalidTableName) || ConstantUtil.IVID_ITEM_PRICING.equals(invalidTableName))
                                                        && moduleProperty.getPropertyName().equals(ConstantUtil.ITEMID)) {
                                                    Component component = componentIterator.next();
                                                    if (component instanceof TextField) {
                                                        ((TextField) component).focus();
                                                    }
                                                } else if ((ConstantUtil.INVALID_COMPANY_MASTER.equals(invalidTableName) || ConstantUtil.INVALID_COMPANY_IDENTIFIER.equals(invalidTableName)
                                                        || ConstantUtil.INVALID_COMPANY_PARENT.equals(invalidTableName) || ConstantUtil.INVALID_COMPANY_TRADE_CLASS.equals(invalidTableName))
                                                        && moduleProperty.getPropertyName().equals(ConstantUtil.COMPANY_ID)) {
                                                    Component component = componentIterator.next();
                                                    if (component instanceof TextField) {
                                                        ((TextField) component).focus();
                                                    }
                                                } else if ("VwIvldDemandForecastActual".equals(invalidTableName)
                                                        && moduleProperty.getPropertyName().equals("demandType")) {
                                                    Component component = componentIterator.next();
                                                    if (component instanceof ComboBox) {
                                                        ((ComboBox) component).focus();
                                                    }
                                                } else if ("VwIvldInventoryWdActualProjMas".equals(invalidTableName)
                                                        && moduleProperty.getPropertyName().equals("type")) {
                                                    Component component = componentIterator.next();
                                                    if (component instanceof ComboBox) {
                                                        ((ComboBox) component).focus();
                                                    }
                                                } else if (ConstantUtil.IVID_ACTUAL_MASTER.equals(invalidTableName)
                                                        && moduleProperty.getPropertyName().equals("contractNo")) {
                                                    Component component = componentIterator.next();
                                                    if (component instanceof TextField) {
                                                        ((TextField) component).focus();
                                                    }
                                                }
                                                switch (invalidTableName) {
                                                    case "IvldCustomerGtsActual":
                                                        if (moduleProperty.getPropertyName().equals(ConstantUtil.ORGANIZATION_KEY)) {
                                                            Component component = componentIterator.next();
                                                            if (component instanceof TextField) {
                                                                System.out.println("" + ((TextField) component).getId());
                                                                ((TextField) component).focus();
                                                            }
                                                        }
                                                        break;
                                                    case ConstantUtil.IVID_ACTUAL_MASTER:
                                                        if (moduleProperty.getPropertyName().equals("contractNo")) {
                                                            Component component = componentIterator.next();
                                                            if (component instanceof TextField) {
                                                                ((TextField) component).focus();
                                                            }
                                                        }
                                                        break;
                                                    case "IvldAverageShelfLife":
                                                        if (moduleProperty.getPropertyName().equals(ConstantUtil.ITEMID)) {
                                                            Component component = componentIterator.next();
                                                            if (component instanceof TextField) {
                                                                ((TextField) component).focus();
                                                            }
                                                        }
                                                        break;
                                                    case "IvldBestPrice":
                                                        if (moduleProperty.getPropertyName().equals(ConstantUtil.ITEMID)) {
                                                            Component component = componentIterator.next();
                                                            if (component instanceof TextField) {
                                                                ((TextField) component).focus();
                                                            }
                                                        }
                                                        break;
                                                    case "VwIvldDemandForecastActual":
                                                        if (moduleProperty.getPropertyName().equals("demandType")) {
                                                            Component component = componentIterator.next();
                                                            if (component instanceof ComboBox) {
                                                                ((ComboBox) component).focus();
                                                            }
                                                        }
                                                        break;
                                                    case "IvldGlBalance":
                                                        if (moduleProperty.getPropertyName().equals("accountId")) {
                                                            Component component = componentIterator.next();
                                                            if (component instanceof TextField) {
                                                                ((TextField) component).focus();
                                                            }
                                                        }
                                                        break;
                                                    case "IvldGlCostCenter":
                                                        if (moduleProperty.getPropertyName().equals("companyCode")) {
                                                            Component component = componentIterator.next();
                                                            if (component instanceof TextField) {
                                                                ((TextField) component).focus();
                                                            }
                                                        }
                                                        break;
                                                    case "VwIvldInventoryWdActualProjMas":
                                                        if (moduleProperty.getPropertyName().equals("type")) {
                                                            Component component = componentIterator.next();
                                                            if (component instanceof ComboBox) {
                                                                ((ComboBox) component).focus();
                                                            }
                                                        }
                                                        break;
                                                    case "IvldItemHierarchy":
                                                        if (moduleProperty.getPropertyName().equals("level0")) {
                                                            Component component = componentIterator.next();
                                                            if (component instanceof TextField) {
                                                                ((TextField) component).focus();
                                                            }
                                                        }
                                                        break;
                                                    case "IvldItemHierarchyDefinition":
                                                        if (moduleProperty.getPropertyName().equals("member")) {
                                                            Component component = componentIterator.next();
                                                            if (component instanceof TextField) {
                                                                ((TextField) component).focus();
                                                            }
                                                        }
                                                        break;
                                                    case "IvldMasterDataAttribute":
                                                        if (moduleProperty.getPropertyName().equals("masterId")) {
                                                            Component component = componentIterator.next();
                                                            if (component instanceof TextField) {
                                                                ((TextField) component).focus();
                                                            }
                                                        }
                                                        break;
                                                    case "IvldCustomerGtsForecast":
                                                        if (moduleProperty.getPropertyName().equals(ConstantUtil.ITEMID)) {
                                                            Component component = componentIterator.next();
                                                            if (component instanceof TextField) {
                                                                ((TextField) component).focus();
                                                            }
                                                        }
                                                        break;
                                                    case "IvldReturns":
                                                        System.out.println("moduleProperty.getPropertyName()---" + moduleProperty.getPropertyName());
                                                        if (moduleProperty.getPropertyName().equals("sku")) {
                                                            Component component = componentIterator.next();
                                                            if (component instanceof TextField) {
                                                                ((TextField) component).focus();
                                                            }
                                                        }
                                                        break;
                                                    case ConstantUtil.IVID_SALES_MASTER:
                                                        if (moduleProperty.getPropertyName().equals("salesId")) {
                                                            Component component = componentIterator.next();
                                                            if (component instanceof TextField) {
                                                                ((TextField) component).focus();
                                                            }
                                                        }
                                                        break;
                                                }
                                            }
                                        }
                                        VaadinSession.getCurrent().setAttribute(ConstantUtil.VIEW_SID_NAME, viewNameSid);
                                    } catch (Exception ex) {
                                        LOGGER.error(ex);
                                    }
                                }
                            });
                        } else if (tableName.equalsIgnoreCase(ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL) || tableName.equalsIgnoreCase(ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL)) {
                            if (moduleProperty.getPropertyName().equals(ConstantUtil.TRANSACTOIN_LEVEL)) {
                                try {
                                    commonLogic.getFiledNames("AdjustmentDetails", ConstantUtil.SEARCH);
                                } catch (ClassNotFoundException ex) {
                                    java.util.logging.Logger.getLogger(AbstractSearch.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (NoSuchMethodException ex) {
                                    java.util.logging.Logger.getLogger(AbstractSearch.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (IllegalAccessException ex) {
                                    java.util.logging.Logger.getLogger(AbstractSearch.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (IllegalArgumentException ex) {
                                    java.util.logging.Logger.getLogger(AbstractSearch.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (InvocationTargetException ex) {
                                    java.util.logging.Logger.getLogger(AbstractSearch.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (InstantiationException ex) {
                                    java.util.logging.Logger.getLogger(AbstractSearch.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                final List<HelperDTO> helperList = logic.getHelperResults(ConstantUtil.TRANSACTION_LEVEL_DDLB);
                                for (int i = 0; i < helperList.size(); i++) {
                                    final HelperDTO helperDTO = helperList.get(i);
                                    addItem(helperDTO.getId());
                                    setItemCaption(helperDTO.getId(), helperDTO.getDescription());
                                }
                                for (Object item : getItemIds()) {
                                    if (getItemCaption(item).equalsIgnoreCase(ConstantUtil.RESERVE_DETAIL)) {
                                        select(item);
                                        tableName = ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL;
                                        if (tableName.equals(ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL) || tableName.equals(ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL)) { // Added this condition to resolve nullpointer exception
                                            List<?> colHeaders = commonLogic.getColumns(tableName.equals(ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL) ? "N" : "Y");
                                            tableColumnArr = (Object[]) colHeaders.get(0);
                                            tableHeaderArr = (String[]) colHeaders.get(NumericConstants.ONE);
                                            table.setFilterGenerator(new AdjustmentDetailsFilterGenerator());
                                        }
                                        addToTable();

                                    }
                                }

                                setNullSelectionAllowed(false);
                                removeItem(0);
                                setImmediate(true);

                                addValueChangeListener(new ValueChangeListener() {

                                    @Override
                                    public void valueChange(Property.ValueChangeEvent event) {
                                        try {
                                            String ddlbValue = "";
                                            Label adDDLB = null;
                                            ComboBox alablel = null;
                                            Iterator<Component> components = gridLayout2.iterator(); // Changed from cssLayout->gridLayout2 for GAL-6070
                                            for (int i = 0; i < gridLayout2.getComponentCount(); i++) {// Changed from cssLayout->gridLayout2 for GAL-6070
                                                Component dlb = components.next();
                                                if (dlb instanceof Label) {
                                                    adDDLB = (Label) dlb;
                                                    if (adDDLB.getValue().equalsIgnoreCase(ConstantUtil.ADJUSTMENT_LEVEL)) {
                                                        alablel = (ComboBox) components.next();
                                                        break;
                                                    }
                                                }
                                            }

                                            if (event.getProperty().getValue() != null) {
                                                int id = Integer.valueOf(String.valueOf(event.getProperty().getValue()));
                                                ddlbValue = HelperListUtil.getInstance().getDescriptionByID(id);
                                                ddlbValue = ddlbValue.trim();
                                            }
                                            if (ConstantUtil.RESERVE_DETAIL.equalsIgnoreCase(ddlbValue)) {
                                                AdjustmentresetFields();
                                                VaadinSession.getCurrent().setAttribute("getName2", ConstantUtil.RESERVE_DETAIL);
                                                tableName = ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL;
                                                addToTable();
                                                alablel.removeAllItems();
                                                final List<HelperDTO> helperList = logic.getHelperResults(ConstantUtil.TRANSCATION_LEVEL_RESERVE);
                                                for (int i = 0; i < helperList.size(); i++) {
                                                    final HelperDTO helperDTO = helperList.get(i);
                                                    alablel.addItem(helperDTO.getId());
                                                    alablel.setItemCaption(helperDTO.getId(), helperDTO.getDescription());
                                                }
                                                alablel.setNullSelectionAllowed(true);
                                                alablel.setNullSelectionItemId(0);
                                                alablel.select(0);

                                                setData(moduleProperty.getPropertyName());
                                                disableorEnable(gridLayout2, false); // Changed from cssLayout->gridLayout2 for GAL-6070

                                            } else if (ConstantUtil.GTN_DETAIL.equalsIgnoreCase(ddlbValue)) {
                                                AdjustmentresetFields();
                                                VaadinSession.getCurrent().setAttribute("getName2", ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL);
                                                tableName = ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL;
                                                addToTable();
                                                alablel.removeAllItems();
                                                final List<HelperDTO> helperList = logic.getHelperResults(ConstantUtil.TRANSCATION_LEVEL_GTN);

                                                for (int i = 0; i < helperList.size(); i++) {
                                                    final HelperDTO helperDTO = helperList.get(i);
                                                    alablel.addItem(helperDTO.getId());
                                                    alablel.setItemCaption(helperDTO.getId(), helperDTO.getDescription());
                                                }
                                                alablel.setNullSelectionAllowed(true);
                                                alablel.setNullSelectionItemId(0);
                                                alablel.select(0);
                                                setData(moduleProperty.getPropertyName());
                                                disableorEnable(gridLayout2, true); // Changed from cssLayout->gridLayout2 for GAL-6070

                                            }

                                        } catch (Exception ex) {
                                            LOGGER.error(ex);
                                        }
                                    }
                                });

                                setData(moduleProperty.getPropertyName());
                            } else if (ConstantUtil.ADJUSTMENTS_TYPE.equals(moduleProperty.getPropertyName())) {
                                focus();
                                customizeHelperDtoDdlb(this);
                                loadAndConfigureDDlb("LoadAdjustmentType", this);
                            }

                        }
                        String listType = (tableName.equals(ConstantUtil.ACCURAL_MASTER) || (ConstantUtil.INVALID_ACCURAL_INBOUND).equals(invalidTableName) || (ConstantUtil.INVALID_COMPANY_MASTER).equals(invalidTableName) || ConstantUtil.INVALID_COMPANY_IDENTIFIER.equals(invalidTableName) || ConstantUtil.INVALID_COMPANY_PARENT.equals(invalidTableName) || ConstantUtil.INVALID_COMPANY_TRADE_CLASS.equals(invalidTableName) || invalidTableName.equals(ConstantUtil.IBID_ITEM_MASTER) || invalidTableName.equals(ConstantUtil.IVID_ITEM_IDENTIFIER) || invalidTableName.equals(ConstantUtil.IVID_ITEM_PRICING) || ConstantUtil.VW_ITEM_IDENTIFIER.equals(tableName) || ConstantUtil.VW_ITEM_PRICING.equals(tableName) || ConstantUtil.VW_COMPANY_IDENTIFIER.equals(tableName)
                                || ConstantUtil.VW_COMPANY_PARENT_DETAILS.equals(tableName) || ConstantUtil.VW_COMPANY_TRADE_CLASS.equals(tableName) || ConstantUtil.VW_ITEM_MASTER.equals(tableName)
                                || ConstantUtil.VW_COMPANY_MASTER.equals(tableName) || tableName.equalsIgnoreCase(ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL) || tableName.equalsIgnoreCase(ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL))
                                ? moduleProperty.getPropertyName() : moduleProperty.getValidation();
                        if (ConstantUtil.PROGRAM_TYPE.equals(listType)) {
                            listType = "RS_UDC2";
                        }
                        if ("glCompanyMasterSid".equals(listType)) {
                            listType = "businessUnit_accrual";
                        }
                        if (primaryDto.getValidation().equals(ConstantUtil.CUSTOMER_SALES)) {
                            setImmediate(true);
                            if (moduleProperty.getPropertyName().equalsIgnoreCase(ConstantUtil.DEDUCTION_CATEGORY)) {
                                listType = ConstantUtil.RS_CATEGORY;
                            } else if (moduleProperty.getPropertyName().equalsIgnoreCase(ConstantUtil.DEDUCTION_TYPE)) {
                                listType = ConstantUtil.RS_TYPE;
                            } else if (moduleProperty.getPropertyName().equalsIgnoreCase(ConstantUtil.DEDUCTION_PROGRAM_TYPE)) {
                                listType = ConstantUtil.REBATE_PROGRAM_TYPE;
                            }
                        }
                        if ("companyCategory".equals(listType)) {
                            listType = ConstantUtil.COMPANY_CATEGORY;
                        }
                        if ("companyType".equals(listType)) {
                            listType = ConstantUtil.COMPANY_TYPE;
                        }
                        if ("companyStatus".equals(listType)) {
                            listType = ConstantUtil.COMPANY_STATUS;
                        }
                        if ("companyGroup".equals(listType)) {
                            listType = ConstantUtil.COMPANY_GROUP;
                        }
                        if ("tradeClass".equals(listType) || "companyTradeClass".equals(listType)) {
                            listType = ConstantUtil.COMPANY_TRADE_CLASS;
                        }
                        if (listType.equals("itemType")) {
                            listType = ConstantUtil.ITEM_TYPE;
                        }
                        if (listType.equals("pricingCodeStatus")) {
                            listType = ConstantUtil.ITEMSTATUS;
                        }
                        if (listType.equals("itemUom")) {
                            listType = ConstantUtil.ITEMUOM;
                        }
                        if (listType.equals("therapeuticClass")) {
                            listType = ConstantUtil.ITEMTHERAPEUTICCLASS;
                        }
                        if (listType.equals("itemCategory")) {
                            listType = ConstantUtil.ITEMTITEMCATEGORY;
                        }
                        if (listType.equals(ConstantUtil.ADJUSTMENTS_TYPE)) {
                            listType = StringUtils.EMPTY;
                        }
                        if (listType.equals(ConstantUtil.DEDUCTION_LEVEL)) {
                            listType = ConstantUtil.DEDUCTION_LEVEL;
                        }
                        if (listType.equals(ConstantUtil.ACCOUNT_TYPE)) {
                            listType = ConstantUtil.ARM_ACCOUNT_TYPE;
                        }
                        if (listType.equals(ConstantUtil.ACC_CATEGORY)) {
                            listType = ConstantUtil.ACCOUNT_CATEGORY;
                        }
                        if (listType.equals(ConstantUtil.POSTING_INDICATOR)) {
                            listType = ConstantUtil.ARM_POSTING_INDICATOR;
                        }
                        final List<HelperDTO> helperList = StringUtils.isBlank(listType) ? Collections.EMPTY_LIST : logic.getHelperResults(listType);
                        for (int i = 0; i < helperList.size(); i++) {
                            final HelperDTO helperDTO = helperList.get(i);
                            if (!"businessUnit_accrual".equals(listType)
                                    && !ConstantUtil.ADJUSTMENT_TYPE.equals(listType) && !ConstantUtil.DEDUCTION_LEVEL.equals(listType)
                                    && !ConstantUtil.ARM_ACCOUNT_TYPE.equals(listType) && !ConstantUtil.ACCOUNT_CATEGORY.equals(listType) && !ConstantUtil.ARM_POSTING_INDICATOR.equals(listType)
                                    && !ConstantUtil.BUSINESS_UNIT.equals(listType) && !ConstantUtil.GL_COMP.equals(listType) && !ConstantUtil.TRANSACTOIN_LEVEL.equals(listType)) {
                                addItem(helperDTO.getDescription());
                                setNullSelectionAllowed(true);
                                setNullSelectionItemId(ConstantUtil.SELECT_ONE);
                                select(ConstantUtil.SELECT_ONE);
                            } else if (!ConstantUtil.TRANSACTOIN_LEVEL.equals(listType)) {
                                addItem(helperDTO.getId());
                                setItemCaption(helperDTO.getId(), helperDTO.getDescription());
                                setNullSelectionAllowed(true);
                                setNullSelectionItemId(0);
                                select(0);
                            }
                        }
                        setImmediate(true);
                        if (!moduleProperty.getPropertyName().equalsIgnoreCase(ConstantUtil.TYPE) && !moduleProperty.getPropertyName().equalsIgnoreCase(ConstantUtil.LEVEL)) {
                            setData(moduleProperty.getPropertyName());

                        }
                        if ("forecastType".equalsIgnoreCase(moduleProperty.getPropertyName())) {
                            addValueChangeListener(new ValueChangeListener() {

                                @Override
                                public void valueChange(Property.ValueChangeEvent event) {
                                    forecastTypeValue = String.valueOf(event.getProperty().getValue());
                                    tableValue1 = String.valueOf(event.getProperty().getValue());
                                    AbstractComponentCreater.setTableValue(tableValue1);
                                    commonLogic.setTableValue(tableValue1);
                                    int size = helperList.size();
                                    if (size >= NumericConstants.TWO && forecastTypeValue.equalsIgnoreCase(helperList.get(NumericConstants.ONE) == null ? StringUtils.EMPTY : helperList.get(NumericConstants.ONE).getDescription())) {
                                        getIterativeComponents1(cssLayout, false, moduleProperty.getPropertyName());

                                    } else if (size >= NumericConstants.THREE && forecastTypeValue.equalsIgnoreCase(helperList.get(NumericConstants.TWO) == null ? StringUtils.EMPTY : helperList.get(NumericConstants.TWO).getDescription())) {
                                        getIterativeComponents1(cssLayout, true, moduleProperty.getPropertyName());
                                    }
                                }
                            });
                            setData(ConstantUtil.IS_FORECAST);
                        }
                    }
                }
            };
        } else if (ConstantUtil.Button.equals(moduleProperty.getCategoryName())) {
            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.USERID));

            final Map<String, AppPermission> functionForecastSalesHM;
            if (!tableName.equalsIgnoreCase(ConstantUtil.INVALID_RECORD_COUNT)) {
                functionForecastSalesHM = stplSecurity.getBusinessFunctionPermission(userId, primaryDto.getValidation());
            } else {

                functionForecastSalesHM = stplSecurity.getBusinessFunctionPermission(userId,
                        ConstantUtil.INVALID_INVENTORYVIEW_TABLE.equalsIgnoreCase(invalidTableName)
                        ? ConstantUtil.IVLD_INVENTORY_WITHDRAWAL_SUMMARY : invalidTableName);
            }
            if ((((AppPermission) functionForecastSalesHM.get(moduleProperty.getPropertyName())) == null) ? false
                    : ((AppPermission) functionForecastSalesHM.get(moduleProperty.getPropertyName())).isFunctionFlag()) {
                buttonLayout.addComponent(new Button() {
                    {
                        addClickListener(new Button.ClickListener() {

                            public void buttonClick(Button.ClickEvent event) {
                                try {
                                    clickListeners(event);
                                } catch (Exception ex) {

                                    LOGGER.error(ex);
                                }
                            }
                        });
                        setCaption(moduleProperty.getDisplayName());
                    }
                });
            }

        }
        return null;
    }

    public void singleFieldSet(final Component labelComponent, final Component fieldComponent) {
        if (fieldComponent != null) {
            if (selectedInvalidModule.equalsIgnoreCase(ConstantUtil.INVALID_RECORD_COUNT)) {
                cssLayout1.addComponent(labelComponent);
                cssLayout1.addComponent(fieldComponent);
            } else if (tableName.equals(ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL) || tableName.equals(ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL)) {
                if (labelComponent.toString().equalsIgnoreCase("Private Views") || labelComponent.toString().equalsIgnoreCase("Public View") || (labelComponent.toString().equalsIgnoreCase("Adjustment Type")) || (labelComponent.toString().equalsIgnoreCase("Transaction Level"))
                        || (labelComponent.toString().equalsIgnoreCase("GL Company") || labelComponent.toString().equalsIgnoreCase("Business Unit") || (labelComponent.toString().equalsIgnoreCase("Workflow  ID")) || (labelComponent.toString().equalsIgnoreCase("Workflow Name")))) {
                    summaryPanel.setVisible(true);
                    searchFieldLayout2.removeStyleName("responsiveGrid"); // Added for GAL-6070
                    searchFieldVerticalLayout.addStyleName(ConstantUtil.WIDTH_AUTO);
                    searchFieldVerticalLayout2.addStyleName(ConstantUtil.WIDTH_AUTO);
                    gridLayout1.setVisible(true);
                    cssLayout2.setVisible(false); // Ends here
                    cssLayout2.addComponent(labelComponent);
                    cssLayout2.addComponent(fieldComponent);
                    gridLayout1.addComponent(labelComponent); // Added for GAL-6070
                    gridLayout1.addComponent(fieldComponent);
                    if (labelComponent.toString().equalsIgnoreCase("Private Views") || labelComponent.toString().equalsIgnoreCase("Public View")) {
                        fieldComponent.addStyleName("searchicon");
                    } else if (labelComponent.toString().equalsIgnoreCase("Adjustment Type")) {
                        fieldComponent.setWidth("208px");
                    }
                } else {
                    if (fieldComponent instanceof ComboBox || fieldComponent instanceof CustomMenuBar) {
                        fieldComponent.addStyleName("margin-bottom-7px");
                    }
                    gridLayout2.setVisible(true); //Added for GAL-6070
                    searchFieldLayout1.removeStyleName("responsiveGrid");
                    searchFieldVerticalLayout.addStyleName(ConstantUtil.WIDTH_AUTO);
                    searchFieldVerticalLayout2.addStyleName(ConstantUtil.WIDTH_AUTO);
                    cssLayout.setVisible(false); //Ends here
                    cssLayout.addComponent(labelComponent);
                    cssLayout.addComponent(fieldComponent);
                    gridLayout2.addComponent(labelComponent); //Added for GAL-6070
                    gridLayout2.addComponent(fieldComponent);
                    if (labelComponent.toString().equalsIgnoreCase(ConstantUtil.ADJUSTMENT_LEVEL)) {
                        loadAdjustmentLevel();
                    }
                    if (tableName.equals(ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL) || tableName.equals(ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL)) {
                        disableorEnable(gridLayout2, false); // Changed from cssLayout->gridLayout2 for GAL-6070
                    }
                }
            } else {
                if (fieldComponent instanceof ComboBox || fieldComponent instanceof CustomMenuBar) {
                    fieldComponent.addStyleName("margin-bottom-7px");
                    if (labelComponent.toString().equalsIgnoreCase("Trade Class")) {
                        fieldComponent.addStyleName("tradeClassDdlb");
                    }
                }
                cssLayout.addComponent(labelComponent);
                cssLayout.addComponent(fieldComponent);
                if (labelComponent.toString().equalsIgnoreCase(ConstantUtil.ADJUSTMENT_LEVEL)) {
                    loadAdjustmentLevel();
                }
                if (tableName.equals(ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL) || tableName.equals(ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL)) {
                    disableorEnable(gridLayout2, false); // Changed from cssLayout->gridLayout2 for GAL-6070
                }
            }
        }
    }

    protected void getIterativeComponents(AbstractLayout layout, List<Component> compList) {
        Iterator<Component> componentIterator = layout.getComponentIterator();
        while (componentIterator.hasNext()) {
            Component component = componentIterator.next();
            if (component instanceof TextField || component instanceof PopupDateField || component instanceof ComboBox || component instanceof CustomMenuBar) {
                compList.add(component);
            } else if (component instanceof AbstractLayout) {
                getIterativeComponents((AbstractLayout) component, compList);
            }
        }
    }

    /*
     For Making Fields setEnable False in
     Search Criteria
     */
    protected void getIterativeComponents1(CssLayout layout, boolean isEnable, String name) {
        try {
            Iterator<Component> componentIterator = layout.getComponentIterator();
            while (componentIterator.hasNext()) {
                Component component = componentIterator.next();
                if (!name.equalsIgnoreCase(ConstantUtil.LEVEL) && !name.equalsIgnoreCase("type") && component instanceof TextField) {
                    if (((TextField) component).getData().equals(ConstantUtil.FORECAST_NAME)) {

                        ((TextField) component).setEnabled(isEnable);
                    }
                    if (((TextField) component).getData().equals(ConstantUtil.FORECAST_VER)) {
                        ((TextField) component).setEnabled(isEnable);
                    }
                }
                if (name.equalsIgnoreCase(ConstantUtil.LEVEL) && component instanceof TextField) {
                    if (((TextField) component).getData().equals(ConstantUtil.COMPANY_ID)) {
                        ((TextField) component).setEnabled(isEnable);
                    }
                    if (((TextField) component).getData().equals(ConstantUtil.COMPANY_NAME)) {
                        ((TextField) component).setEnabled(isEnable);
                    }
                }

            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
    protected void disableorEnable(CssLayout layout, boolean isEnable) {
        try {
            Iterator<Component> componentIterator = layout.getComponentIterator();
            while (componentIterator.hasNext()) {
                Component component = componentIterator.next();
                if (component instanceof Label) {
                    Label label = (Label) component;
                    if (label.getValue().equalsIgnoreCase("Customer No") || label.getValue().equalsIgnoreCase("Item No")
                            || label.getValue().equalsIgnoreCase("Customer Name") || label.getValue().equalsIgnoreCase("Item Name")) {
                        ((TextField) componentIterator.next()).setEnabled(isEnable);
                    } else if (label.getValue().equalsIgnoreCase(ConstantUtil.DEDUCTIONLEVELS)) {
                        ((ComboBox) componentIterator.next()).setEnabled(isEnable);
                    } else if (label.getValue().equalsIgnoreCase(ConstantUtil.DEDUCTIONVALUE)) {
                        ((CustomMenuBar) componentIterator.next()).setEnabled(isEnable);
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    protected void disableorEnable(GridLayout layout, boolean isEnable) { //Added for GAL-6070
        try {
            Iterator<Component> componentIterator = layout.getComponentIterator();
            while (componentIterator.hasNext()) {
                Component component = componentIterator.next();
                if (component instanceof Label) {
                    Label label = (Label) component;
                    if (label.getValue().equalsIgnoreCase("Customer No") || label.getValue().equalsIgnoreCase("Item No")
                            || label.getValue().equalsIgnoreCase("Customer Name") || label.getValue().equalsIgnoreCase("Item Name")) {
                        ((TextField) componentIterator.next()).setEnabled(isEnable);
                    } else if (label.getValue().equalsIgnoreCase(ConstantUtil.DEDUCTIONLEVELS)) {
                        ((ComboBox) componentIterator.next()).setEnabled(isEnable);
                    } else if (label.getValue().equalsIgnoreCase(ConstantUtil.DEDUCTIONVALUE)) {
                        ((CustomMenuBar) componentIterator.next()).setEnabled(isEnable);
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public void clickListeners(Button.ClickEvent event) {
        try {
            if (ConstantUtil.SEARCH.equals(event.getButton().getCaption())) {
                searchValues.clear();
                List<Component> compList = new ArrayList<Component>();
                getIterativeComponents(searchFieldVerticalLayout, compList);
                List<Component> compList2 = new ArrayList<Component>();
                getIterativeComponents(searchFieldVerticalLayout2, compList2);

                for (Component component : compList) {
                    if (component instanceof TextField) {
                        if (!StringUtils.EMPTY.equals(((TextField) component).getValue())) {

                            if (((TextField) component).isValid()) {
                                String data = null;
                                Object value = ((TextField) component).getValue();
                                if (ConstantUtil.ADJUSTED_DEMAND.equals(demandTypeValue) && String.valueOf(((TextField) component).getData()).equals(ConstantUtil.FORECAST_MONTH)) {
                                    data = ConstantUtil.MONTH;
                                } else if (ConstantUtil.ADJUSTED_DEMAND.equals(demandTypeValue) && String.valueOf(((TextField) component).getData()).equals(ConstantUtil.FORECAST_YEAR)) {
                                    data = "year";
                                } else if (ConstantUtil.ADJUSTED_DEMAND.equals(demandTypeValue) && String.valueOf(((TextField) component).getData()).equals(ConstantUtil.FORECAST_VER)) {
                                    data = "forecastVersion";
                                } else if (String.valueOf(((TextField) component).getData()).equals("businessUnit") && (!ConstantUtil.RETURN_RESERVE.equals(tableName) && !ConstantUtil.IVLD_RETURN_RESERVE.equals(invalidTableName))) {
                                    data = "businessUnitName";
                                } else if (ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName) || ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL.equals(tableName)) {

                                    if (String.valueOf(((TextField) component).getData()).equals(ConstantUtil.ARP_ACCOUNT)) {
                                        data = ConstantUtil.ARP_ACCOUNT;
                                    } else if (String.valueOf(((TextField) component).getData()).equals(ConstantUtil.WORKFLOW_ID)) {
                                        data = ConstantUtil.WORKFLOW_ID;
                                    } else if (String.valueOf(((TextField) component).getData()).equals(ConstantUtil.COMPANY_NO)) {
                                        searchValues.put(((TextField) component).getData(), ((TextField) component).getValue());
                                    } else if (String.valueOf(((TextField) component).getData()).equals(ConstantUtil.COMPANY_NAME)) {
                                        searchValues.put(((TextField) component).getData(), ((TextField) component).getValue());
                                    } else if (String.valueOf(((TextField) component).getData()).equals(ConstantUtil.ITEMNO)) {
                                        searchValues.put(((TextField) component).getData(), ((TextField) component).getValue());
                                    } else if (String.valueOf(((TextField) component).getData()).equals(ConstantUtil.ITEM_NAME)) {
                                        searchValues.put(((TextField) component).getData(), ((TextField) component).getValue());
                                    } else if (String.valueOf(((TextField) component).getData()).equals("brandName")) {
                                        searchValues.put(((TextField) component).getData(), ((TextField) component).getValue());
                                    } else if (String.valueOf(((TextField) component).getData()).equals("originalBatchId")) {
                                        searchValues.put(((TextField) component).getData(), ((TextField) component).getValue());
                                    } else if (String.valueOf(((TextField) component).getData()).equals(ConstantUtil.REDEMPTION_PERIOD)) {
                                        data = ConstantUtil.REDEMPTION_PERIOD;
                                        value = String.valueOf(((TextField) component).getValue());

                                        SimpleDateFormat dateFormat = new SimpleDateFormat(ConstantUtil.MMDDYYYY);
                                        Date toDate = dateFormat.parse(String.valueOf(value));
                                        Timestamp redemptionPeriod = new Timestamp(toDate.getTime());
                                        Object redemptionPeriodToDatevalue = redemptionPeriodToDate.getValue();

                                        searchValues.put(data, redemptionPeriod);

                                        if (!StringUtils.EMPTY.equals(redemptionPeriodToDatevalue)) {
                                            Date endDate = dateFormat.parse(String.valueOf(redemptionPeriodToDatevalue));
                                            Timestamp redemptionPeriodEndDate = new Timestamp(endDate.getTime());
                                            searchValues.put(ConstantUtil.REDEMPTION_TO_DATE, redemptionPeriodEndDate);
                                        } else {
                                            searchValues.put(ConstantUtil.REDEMPTION_TO_DATE, null);
                                        }
                                    }

                                } else {
                                    data = String.valueOf(((TextField) component).getData());
                                }
                                errorMsg.clearError();
                                if (!(((TextField) component).getData()).equals(ConstantUtil.REDEMPTION_PERIOD)) {
                                    searchValues.put(((TextField) component).getData().equals("invalidRecordId")
                                            ? generatedColumn : data, value);
                                }

                            } else {
                                try {
                                    ((TextField) component).validate();
                                } catch (Validator.InvalidValueException e) {
                                    errorMsg.setValue(String.valueOf(e));
                                    errorMsg.setComponentError(new UserError(e.getMessage()));
                                    errorMsg.setVisible(true);
                                } catch (Exception e) {

                                    LOGGER.error(e);
                                }
                                return;
                            }
                        }
                    } else if (component instanceof PopupDateField) {
                        if (((PopupDateField) component).getValue() != null) {
                            if (tableName.equals(ConstantUtil.ARP_OUTBOUND)) {
                                SimpleDateFormat sdf = new SimpleDateFormat(ConstantUtil.DATE_FORMAT);
                                Date date = ((PopupDateField) component).getValue();
                                searchValues.put(((PopupDateField) component).getData() + "_Date", sdf.format(date));
                            } else if (tableName.equals("Invalidrecordcount")) {

                                SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy hh:mmaa");
                                Date date = ((PopupDateField) component).getValue();
                                searchValues.put(((PopupDateField) component).getData(), (int) sdf.format(date).charAt(NumericConstants.FOUR) == NumericConstants.FORTY_EIGHT ? sdf.format(date).replaceFirst("[0]", " ") : sdf.format(date));
                            } else if (ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName) || ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL.equals(tableName)) {
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
                                Date date = ((PopupDateField) component).getValue();
                                searchValues.put(((PopupDateField) component).getData(), sdf.format(date));

                            } else {
                                searchValues.put(((PopupDateField) component).getData() + "_Date", ((PopupDateField) component).getValue());
                            }
                        }

                    } else if (component instanceof CustomMenuBar) {
                        if (ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName)) {

                            CustomMenuBar tempCustomMenuBar = (CustomMenuBar) component;

                            if (tempCustomMenuBar.getData() != null) {
                                String propId = tempCustomMenuBar.getData().toString();
                                if (ConstantUtil.DEDUCTION_VALUE.equals(propId) && deductionValueItem != null && deductionValueItem.getChildren() != null) {
                                    StringBuilder builder = new StringBuilder("");
                                    for (CustomMenuBar.CustomMenuItem menuItem : deductionValueItem.getChildren()) {
                                        if (menuItem.isChecked()) {
                                            if (!builder.toString().isEmpty()) {
                                                builder.append(",");
                                            }
                                            builder.append(menuItem.getMenuItem().getId());
                                        }
                                    }
                                    searchValues.put(propId, builder.toString());
                                }
                            }
                        }
                    } else if (component instanceof ComboBox) {
                        Object comboValue = ((ComboBox) component).getValue();
                        if (ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL.equals(tableName) || ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName)) {
                            if (String.valueOf(((ComboBox) component).getData()).equals(ConstantUtil.ACCOUNT_TYPE)) {
                                String value = String.valueOf(((ComboBox) component).getValue());
                                if (value != null && !ConstantUtil.NULL.equals(value) && !ConstantUtil.STRING_ZERO.equals(value)) {
                                    searchValues.put(((ComboBox) component).getData(), value);
                                }
                            } else if (String.valueOf(((ComboBox) component).getData()).equals(ConstantUtil.ACC_CATEGORY)) {
                                String value = String.valueOf(((ComboBox) component).getValue());
                                if (value != null && !ConstantUtil.NULL.equals(value) && !ConstantUtil.STRING_ZERO.equals(value)) {
                                    searchValues.put(((ComboBox) component).getData(), value);
                                }
                            } else if (String.valueOf(((ComboBox) component).getData()).equals(ConstantUtil.ADJ_LEVEL)) {
                                String value = String.valueOf(((ComboBox) component).getValue());
                                if (value != null && !ConstantUtil.NULL.equals(value) && !ConstantUtil.STRING_ZERO.equals(value)) {
                                    searchValues.put(((ComboBox) component).getData(), value);
                                }
                            } else if (String.valueOf(((ComboBox) component).getData()).equals(ConstantUtil.POSTING_INDICATOR)) {
                                String value = String.valueOf(((ComboBox) component).getValue());
                                if (value != null && !ConstantUtil.NULL.equals(value) && !ConstantUtil.STRING_ZERO.equals(value)) {
                                    searchValues.put(((ComboBox) component).getData(), "N".equals(logic.getHelperTableSidforPostingIndicator(value)) ? "0" : "1");
                                }
                            } else if (String.valueOf(((ComboBox) component).getData()).equals(ConstantUtil.DEDUCTION_LEVEL)) {
                                String value = String.valueOf(((ComboBox) component).getValue());
                                if (value != null && !ConstantUtil.NULL.equals(value) && !ConstantUtil.STRING_ZERO.equals(value)) {
                                    searchValues.put(((ComboBox) component).getData(), value);
                                }
                            }
                        } else {
                            if (comboValue != null && !String.valueOf(comboValue).equals(ConstantUtil.SELECT_ONE)
                                    && !((ComboBox) component).getData().equals(ConstantUtil.TABLE)
                                    && !primaryDto.getValidation().equalsIgnoreCase(ConstantUtil.DEMAND)) {

                                if (primaryDto.getValidation().equals("GL Balance")) {
                                    if (((ComboBox) component).getValue().equals("Yes")) {
                                        searchValues.put(((ComboBox) component).getData() + ConstantUtil.BOOLEAN, NumericConstants.ONE);
                                    }
                                    if (((ComboBox) component).getValue().equals("No")) {
                                        searchValues.put(((ComboBox) component).getData() + ConstantUtil.BOOLEAN, 0);
                                    }
                                } else if (primaryDto.getValidation().equals(ConstantUtil.RETURNS)) {

                                    if (!"0".equals(String.valueOf(comboValue))) {
                                        int value = ((ComboBox) component).getValue().equals("true") ? NumericConstants.ONE : 0;
                                        searchValues.put(((ComboBox) component).getData() + ConstantUtil.BOOLEAN, value);
                                    }

                                } else if (String.valueOf(((ComboBox) component).getData()).equals(ConstantUtil.QUANTITY_INCLUSION)) {
                                    if (!"4".equals(String.valueOf(comboValue))) {
                                        searchValues.put(((ComboBox) component).getData(), String.valueOf(((ComboBox) component).getValue()));
                                    }
                                } else if (String.valueOf(((ComboBox) component).getData()).equals(ConstantUtil.POSTING_INDICATOR) && !tableName.equals(ConstantUtil.ACCURAL_MASTER)) {
                                    if (!"4".equals(String.valueOf(comboValue))) {
                                        searchValues.put(((ComboBox) component).getData() + ConstantUtil.BOOLEAN, Integer.valueOf(comboValue.toString()));
                                    }
                                } else if (String.valueOf(((ComboBox) component).getData()).equals(ConstantUtil.POSTING_INDICATOR) && tableName.equals(ConstantUtil.ACCURAL_MASTER)) {
                                    if ("Yes".equals(String.valueOf(comboValue))) {
                                        searchValues.put(((ComboBox) component).getData() + ConstantUtil.BOOLEAN, NumericConstants.ONE);
                                    } else if ("No".equals(String.valueOf(comboValue))) {
                                        searchValues.put(((ComboBox) component).getData() + ConstantUtil.BOOLEAN, 0);
                                    }

                                } else if (!"0".equals(String.valueOf(comboValue))) {
                                    searchValues.put(((ComboBox) component).getData(), ((ComboBox) component).getValue().toString());
                                }
                            } else if (primaryDto.getValidation().equalsIgnoreCase(ConstantUtil.CUSTOMER_SALES)) {
                                String value = String.valueOf(((ComboBox) component).getValue());
                                if (((ComboBox) component).getValue() != null && !ConstantUtil.SELECT_ONE.equals(((ComboBox) component).getValue())) {
                                    if (ConstantUtil.DEDUCTION_CATEGORY.equals(((ComboBox) component).getData().toString())) {

                                        String data = String.valueOf(((HelperDTO) ((ComboBox) component).getValue()).getId());
                                        if (!ConstantUtil.SELECT_ONE.equals(value)) {
                                            searchValues.put(((ComboBox) component).getData(), data);
                                        }
                                    }
                                    if (ConstantUtil.DEDUCTION_PROGRAM_TYPE.equals(((ComboBox) component).getData().toString())) {
                                        String data = String.valueOf(((HelperDTO) ((ComboBox) component).getValue()).getId());
                                        if (!ConstantUtil.SELECT_ONE.equals(value)) {
                                            searchValues.put(((ComboBox) component).getData(), data);
                                        }
                                    }
                                    if (ConstantUtil.DEDUCTION_TYPE.equals(((ComboBox) component).getData().toString())) {
                                        String data = String.valueOf(((HelperDTO) ((ComboBox) component).getValue()).getId());
                                        if (!ConstantUtil.SELECT_ONE.equals(value)) {
                                            searchValues.put(((ComboBox) component).getData(), data);
                                        }
                                    }
                                }
                            } else if (primaryDto.getValidation().equalsIgnoreCase(ConstantUtil.DEMAND)) {
                                if (!((ComboBox) component).getData().equals(ConstantUtil.ORGANIZATION_KEY) && (((ComboBox) component).getValue() == null || ConstantUtil.SELECT_ONE.equals(((ComboBox) component).getValue()))) {
                                    String msgValue = "";
                                    if (ConstantUtil.DEMAND_TYPE.equals(((ComboBox) component).getData())) {
                                        msgValue = "Please Select a value for Demand Type ddlb";
                                    }
                                    if (ConstantUtil.IS_FORECAST.equals(((ComboBox) component).getData())) {
                                        msgValue = "Please Select a value for Forecast Type";
                                    }
                                    final MessageBox msg = MessageBox.showPlain(Icon.WARN, ConstantUtil.MANDATORY_ERROR, msgValue, new MessageBoxListener() {

                                        @SuppressWarnings("PMD")
                                        public void buttonClicked(final ButtonId buttonId) {
                                        }
                                    }, ButtonId.OK);
                                    msg.getButton(ButtonId.OK).focus();
                                    return;
                                }
                                if (ConstantUtil.IS_FORECAST.equals(((ComboBox) component).getData())) {
                                    int isForecast = ConstantUtil.PROJECTION.equalsIgnoreCase(String.valueOf(((ComboBox) component).getValue())) ? NumericConstants.ONE : 0;
                                    commonLogic.setIsForecastDemand(isForecast);
                                    searchValues.put(((ComboBox) component).getData(),
                                            isForecast);
                                    removeList();
                                }
                                if (ConstantUtil.DEMAND_TYPE.equals(((ComboBox) component).getData())) {
                                    if (demandTypeValue.equals(ConstantUtil.ADJUSTED_DEMAND)) {
                                        if (!invalidDemand) {
                                            tableName = ConstantUtil.ADJUSTED_DEMAND_VIEW;
                                        } else {
                                            invalidTableName = ConstantUtil.IVLD_ADJUSTED_DEMAND_VIEW;
                                        }
                                    } else if (!invalidDemand) {
                                        tableName = ConstantUtil.DEMANDVIEW_TABLE;
                                    } else {
                                        invalidTableName = ConstantUtil.IVLD_DEMANDVIEW_TABLE;
                                    }
                                    propertyId();
                                }
                                if (((ComboBox) component).getData().equals(ConstantUtil.ORGANIZATION_KEY) && !(((ComboBox) component).getValue() == null || ConstantUtil.SELECT_ONE.equals(((ComboBox) component).getValue()))) {
                                    searchValues.put(((ComboBox) component).getData(), ((ComboBox) component).getValue());
                                }
                            }
                        }

                        if (primaryDto.getValidation().equalsIgnoreCase(ConstantUtil.INVENTORY)
                                && ConstantUtil.IS_FORECAST.equals(((ComboBox) component).getData())) {
                            if (!(((ComboBox) component).getValue() != null && !ConstantUtil.SELECT_ONE.equals(((ComboBox) component).getValue()))) {
                                final MessageBox msg = MessageBox.showPlain(Icon.WARN, ConstantUtil.MANDATORY_ERROR, "Please Select a value for Type", new MessageBoxListener() {

                                    @SuppressWarnings("PMD")
                                    public void buttonClicked(final ButtonId buttonId) {
                                    }
                                }, ButtonId.OK);
                                msg.getButton(ButtonId.OK).focus();
                                return;
                            } else {
                                int isForecast = ConstantUtil.PROJECTION.equalsIgnoreCase(String.valueOf(((ComboBox) component).getValue())) ? NumericConstants.ONE : 0;
                                searchValues.put(((ComboBox) component).getData(),
                                        isForecast);
                                commonLogic.setIsForecast(isForecast);
                                removeListInventory();
                            }
                        }
                        if ((((primaryDto.getValidation().equalsIgnoreCase(ConstantUtil.ACCURAL_MASTER)) || (ConstantUtil.INVALID_ACCURAL_INBOUND.equals(invalidTableName)))
                                && ConstantUtil.ACCRUAL_TYPE.equals(((ComboBox) component).getData())) && (!(((ComboBox) component).getValue() != null && !ConstantUtil.SELECT_ONE.equals(((ComboBox) component).getValue())))) {
                            final MessageBox msg = MessageBox.showPlain(Icon.WARN, ConstantUtil.MANDATORY_ERROR, "Please Select Accrual Type", new MessageBoxListener() {

                                @SuppressWarnings("PMD")
                                public void buttonClicked(final ButtonId buttonId) {
                                }
                            }, ButtonId.OK);
                            msg.getButton(ButtonId.OK).focus();
                            return;
                        }
                        if (primaryDto.getValidation().equalsIgnoreCase(ConstantUtil.INVENTORY)
                                && ConstantUtil.IS_MASTER.equals(((ComboBox) component).getData())) {
                            if (!(((ComboBox) component).getValue() != null && !ConstantUtil.SELECT_ONE.equals(((ComboBox) component).getValue()))) {
                                final MessageBox msg = MessageBox.showPlain(Icon.WARN, ConstantUtil.MANDATORY_ERROR, "Please Select a value for Level", new MessageBoxListener() {

                                    @SuppressWarnings("PMD")
                                    public void buttonClicked(final ButtonId buttonId) {
                                    }
                                }, ButtonId.OK);
                                msg.getButton(ButtonId.OK).focus();
                                return;
                            } else {
                                int isMaster = ConstantUtil.SUMMARY.equalsIgnoreCase(String.valueOf(((ComboBox) component).getValue())) ? NumericConstants.ONE : 0;
                                searchValues.put(((ComboBox) component).getData(),
                                        isMaster);
                                commonLogic.setIsMaster(isMaster);
                                removeListInventory();
                            }
                        }
                        if (primaryDto.getValidation().equalsIgnoreCase(ConstantUtil.CPI_INDEX)) {
                            String value = String.valueOf(((ComboBox) component).getValue());
                            if (((ComboBox) component).getValue() != null && !ConstantUtil.SELECT_ONE.equals(((ComboBox) component).getValue()) && ConstantUtil.STATUS.equals(((ComboBox) component).getData().toString())) {
                                String data = String.valueOf(((ComboBox) component).getValue());
                                if (!ConstantUtil.SELECT_ONE.equals(value)) {
                                    searchValues.put(((ComboBox) component).getData(), data);
                                }
                            }

                        }
                        if (primaryDto.getValidation().equalsIgnoreCase(ConstantUtil.ARP_OUTBOUND) && ((ComboBox) component).getValue() != null && !ConstantUtil.SELECT_ONE.equals(((ComboBox) component).getValue())) {
                            if (ConstantUtil.ACCOUNT_TYPE.equals(((ComboBox) component).getData().toString())) {

                                String data = String.valueOf(((HelperDTO) ((ComboBox) component).getValue()).getId());
                                if (!"0".equals(data)) {
                                    searchValues.put(((ComboBox) component).getData(), data);
                                }
                            }
                            if (ConstantUtil.ARP_ACCOUNT.equals(((ComboBox) component).getData().toString())) {
                                String data = String.valueOf(((HelperDTO) ((ComboBox) component).getValue()).getId());
                                if (!"0".equals(data)) {
                                    searchValues.put(((ComboBox) component).getData(), data);
                                }
                            }
                        }
                        if (searchValues.containsKey(ConstantUtil.INVALID_TO_DATE) || searchValues.containsKey(ConstantUtil.INVALID_TO_DATE)) {
                            Date invalidTo = null;
                            Date invalidFrom = null;
                            for (int i = 0; i < searchValues.size(); i++) {
                                if (searchValues.containsKey(ConstantUtil.INVALID_TO_DATE)) {
                                    invalidTo = (Date) searchValues.get(ConstantUtil.INVALID_TO_DATE);
                                } else {
                                    invalidTo = null;
                                }
                                if (searchValues.containsKey(ConstantUtil.INVALID_FROM_DATE)) {
                                    invalidFrom = (Date) searchValues.get(ConstantUtil.INVALID_FROM_DATE);
                                } else {
                                    invalidFrom = null;
                                }
                            }
                            if (invalidTo != null && invalidFrom != null && invalidTo.before(invalidFrom)) {
                                final MessageBox msg = MessageBox.showPlain(Icon.WARN, "Error", "Invalid To Date should be greater than Invalid From Date", new MessageBoxListener() {

                                    @SuppressWarnings("PMD")
                                    public void buttonClicked(final ButtonId buttonId) {
                                    }
                                }, ButtonId.OK);
                                msg.getButton(ButtonId.OK).focus();
                                return;
                            }
                        }
                        if (primaryDto.getValidation().equalsIgnoreCase(ConstantUtil.CFF)) {
                            String value = String.valueOf(((ComboBox) component).getValue());
                            if (((ComboBox) component).getValue() != null && !ConstantUtil.SELECT_ONE.equals(((ComboBox) component).getValue()) && !ConstantUtil.SELECT_ONE.equals(value)) {
                                String data = String.valueOf(((ComboBox) component).getValue());
                                searchValues.put(((ComboBox) component).getData(), data);
                            }
                        }
                        if (primaryDto.getValidation().equalsIgnoreCase(ConstantUtil.ITEM_MASTER) && ConstantUtil.STRENGTH.equals(((ComboBox) component).getData().toString())) {
                            String value = String.valueOf(((ComboBox) component).getValue());
                            Object CompValue = ((ComboBox) component).getValue();
                            if (CompValue != null && !ConstantUtil.SELECT_ONE.equals(CompValue) && !ConstantUtil.SELECT_ONE.equals(value)) {
                                String strength = value.replaceAll(ConstantUtil.STRING_PERCENT, ConstantUtil.PERCENT);
                                searchValues.put(((ComboBox) component).getData(), strength);
                            }
                        }
                    }
                }
                for (Component component : compList2) {
                    if (component instanceof TextField) {
                        if (!StringUtils.EMPTY.equals(((TextField) component).getValue())) {

                            if (((TextField) component).isValid()) {
                                String data = null;

                                if (String.valueOf(((TextField) component).getData()).equals(ConstantUtil.WORKFLOW_ID) && (ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName) || ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL.equals(tableName))) {
                                    data = ConstantUtil.WORKFLOW_ID;
                                } else {
                                    data = String.valueOf(((TextField) component).getData());
                                }
                                errorMsg.clearError();
                                searchValues.put(((TextField) component).getData().equals("invalidRecordId")
                                        ? generatedColumn : data, ((TextField) component).getValue());

                            } else {
                                try {
                                    ((TextField) component).validate();
                                } catch (Validator.InvalidValueException e) {
                                    errorMsg.setValue(String.valueOf(e));
                                    errorMsg.setComponentError(new UserError(e.getMessage()));
                                    errorMsg.setVisible(true);
                                } catch (Exception e) {

                                    LOGGER.error(e);
                                }
                                return;
                            }
                        }
                    } else if (component instanceof ComboBox) {
                        Object comboValue = ((ComboBox) component).getData();
                        if (ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL.equals(tableName) || ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName)) {
                            if (String.valueOf(((ComboBox) component).getData()).equals(ConstantUtil.ADJUSTMENTS_TYPE)) {
                                String value = String.valueOf(((ComboBox) component).getValue());
                                if (value != null && !ConstantUtil.NULL.equals(value) && !ConstantUtil.STRING_ZERO.equals(value)) {
                                    searchValues.put(((ComboBox) component).getData(), value);
                                }
                            } else if (String.valueOf(((ComboBox) component).getData()).equals("glCompanyName")) {
                                String value = String.valueOf(((ComboBox) component).getValue());
                                if (value != null && !ConstantUtil.NULL.equals(value) && !ConstantUtil.STRING_ZERO.equals(value)) {
                                    searchValues.put(comboValue, value);
                                }
                            } else if (String.valueOf(((ComboBox) component).getData()).equals("businessUnitId")) {
                                String value = String.valueOf(((ComboBox) component).getValue());
                                if (value != null && !ConstantUtil.NULL.equals(value) && !ConstantUtil.STRING_ZERO.equals(value)) {
                                    searchValues.put(((ComboBox) component).getData(), value);
                                }
                            } else if (String.valueOf(((ComboBox) component).getData()).equals(ConstantUtil.TRANSACTOIN_LEVEL)) {
                                String value = String.valueOf(((ComboBox) component).getValue());
                                if (value != null && !ConstantUtil.NULL.equals(value) && !ConstantUtil.STRING_ZERO.equals(value)) {
                                    searchValues.put(((ComboBox) component).getData(), value.equals(logic.getHelperTableSidforReserveGTN()) ? "0" : "1");
                                }
                            }
                        }
                    }
                }
                LOGGER.debug("searchValues map size is " + searchValues.size() + searchValues);

                if (tableName.equals(ConstantUtil.ARP_OUTBOUND) && searchValues.size() == 0) {
                    final MessageBox msg = MessageBox.showPlain(Icon.WARN, "Error", "Please enter/select search criteria", new MessageBoxListener() {

                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                        }
                    }, ButtonId.OK);
                    msg.getButton(ButtonId.OK).focus();
                    return;

                }
                if (tableName.equalsIgnoreCase("VwReturnReserve") && searchValues.size() == 0) {
                    final MessageBox msg = MessageBox.showPlain(Icon.WARN, ConstantUtil.SEARCH, "There are no Accrual Please enter/select search criteria.", new MessageBoxListener() {

                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                        }
                    }, ButtonId.OK);
                    msg.getButton(ButtonId.OK).focus();
                    return;

                }
                btnSearchLogic();

                arpCount = tableLogic.getArpCount();
            } else if (ConstantUtil.RESET.equals(event.getButton().getCaption())) {
                String msg = StringUtils.EMPTY;
                if (!ConstantUtil.ST_CFF_OUTBOUND.equals(tableName) && !ConstantUtil.ARP_OUTBOUND.equals(tableName) && !ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName)
                        && !ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL.equals(tableName)) {
                    msg = "Are you sure you want to reset the page to default/previous values ";
                } else {
                    msg = "Are you sure you want to reset the Search section to its default state ";
                }
                MessageBox.showPlain(Icon.QUESTION, ConstantUtil.CONFORMATION, msg + " ?", new MessageBoxListener() {

                    /**
                     *
                     * Called when a Button has been clicked .
                     *
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals("YES")) {

                            try {
                                if (!ConstantUtil.ST_CFF_OUTBOUND.equals(tableName) && !ConstantUtil.ARP_OUTBOUND.equals(tableName)
                                        && !ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName)
                                        && !ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL.equals(tableName)) {
                                    resetButtonLogic();
                                }
                                 tableLogic.setItemsPerPage(10);
                                resetFields();

                            } catch (Exception ex) {
                                LOGGER.error(ex);
                            }

                        }
                    }
                }, ButtonId.YES, ButtonId.NO);

            } else if ("RESET".equals(event.getButton().getCaption())) {
                MessageBox.showPlain(Icon.QUESTION, ConstantUtil.CONFORMATION, "Are you sure you want to reset the page to default/previous values " + " ?", new MessageBoxListener() {
                    /**
                     * Called when a Button has been clicked .
                     *
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals("YES")) {

                            try {
                                if (ConstantUtil.ST_CFF_OUTBOUND.equals(tableName) || ConstantUtil.ARP_OUTBOUND.equals(tableName)
                                        || ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName)
                                        || ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL.equals(tableName)) {
                                    resetButtonLogic();
                                }

                            } catch (Exception ex) {
                                LOGGER.error(ex);
                            }

                        }
                    }
                }, ButtonId.YES, ButtonId.NO);
            } else if (ConstantUtil.SAVE_VIEW.equals(event.getButton().getCaption())) {
                try {
                    String[] arrayProperties = SearchLogic.getPropertyNames();
                    bindValues(searchFieldVerticalLayout2, arrayProperties);
                    bindValues(searchFieldVerticalLayout, arrayProperties);
                    SaveView SaveViewLookup = new SaveView(adjustmentDetailsDTO);
                    UI.getCurrent().addWindow(SaveViewLookup);
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }

            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * Adds the to table.
     *
     * @return the table
     */
    private void addToTable() throws SystemException, PortalException {
        LOGGER.debug("Entering addToTable" + tableName);
        try {
            tableLogic.setContainerDataSource(new BeanItemContainer(Object.class));
            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.USERID));
            final Map<String, AppPermission> fieldForecastSalesHM = stplSecurity.getBusinessFieldPermission(userId, primaryDto.getValidation());
            final TableResultCustom tableResultCustom = commonSecurityLogic.modifyTableResult(tableColumnArr, tableHeaderArr, fieldForecastSalesHM);
            table.markAsDirty();
            tableLogic.setPageLength(NumericConstants.TEN);
            tableLogic.sinkItemPerPageWithPageLength(false);
            table.setComponentError(null);
            if (!tableName.equalsIgnoreCase(ConstantUtil.INVALID_RECORD_COUNT)) {
                   if (tableName.equals(ConstantUtil.ACCURAL_MASTER)) {
                    tableLogic.setContainerDataSource(new BeanItemContainer(AccrualMaster.class));

                    table.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
                    table.addStyleName("table-header-normal");
                    table.setImmediate(true);

                } else if (tableName.equals(ConstantUtil.ACCURAL_DETAILS)) {

                    tableLogic.setContainerDataSource(new BeanItemContainer(AccrualdetailsDTO.class));

                    table.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
                    table.addStyleName("table-header-normal");
                    table.setImmediate(true);

                } else if (tableName.equals(ConstantUtil.ARP_OUTBOUND)) {
                    tableLogic.setContainerDataSource(new BeanItemContainer(ARPOutboundDTO.class));
                    table.setColumnCheckBox(ConstantUtil.CHECKED_RECORD, true, false);
                    arpColumnFormat();
                    table.addColumnCheckListener(new ColumnCheckListener() {

                        public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {

                            boolean check = event.isChecked();
                            String checkValue = StringUtils.EMPTY;
                            if (check) {
                                checkValue = "1";
                            } else {
                                checkValue = "0";
                            }
                            SearchLogic.checkAll(checkValue);
                            for (Object item : table.getItemIds()) {
                                tableLogic.getContainerDataSource().getContainerProperty(item, ConstantUtil.CHECKED_RECORD).setValue(check);
                            }
                        }
                    });
                    table.setTableFieldFactory(new TableFieldFactory() {
                        public Field<?> createField(Container container, final Object itemId, Object propertyId, Component uiContext) {
                            Field field = null;

                            if (ConstantUtil.CHECKED_RECORD.equals(propertyId.toString())) {
                                final ExtCustomCheckBox customCheckBox = new ExtCustomCheckBox();
                                customCheckBox.setImmediate(true);
                                customCheckBox.setEnabled(true);
                                customCheckBox.addClickListener(new ExtCustomCheckBox.ClickListener() {
                                    @Override
                                    public void click(ExtCustomCheckBox.ClickEvent event) {
                                        String check = StringUtils.EMPTY;
                                        if (customCheckBox.getValue()) {
                                            check = "1";
                                            ++arpCount;
                                            if (arpCount != 0 && arpCount == tableLogic.getArpCount()) {
                                                table.setColumnCheckBox(ConstantUtil.CHECKED_RECORD, true, true);
                                            }
                                        } else {
                                            check = "0";
                                            --arpCount;
                                            if (table.getColumnCheckBox(ConstantUtil.CHECKED_RECORD)) {
                                                table.setColumnCheckBox(ConstantUtil.CHECKED_RECORD, true, false);
                                            }
                                        }
                                        String arpId = String.valueOf(table.getContainerProperty(itemId, "arp_Id").getValue());
                                        String companySid = String.valueOf(table.getContainerProperty(itemId, "companyMasterSid").getValue());
                                        String itemSid = String.valueOf(table.getContainerProperty(itemId, "itemMasterSid").getValue());
                                        String brandSid = String.valueOf(table.getContainerProperty(itemId, "brandMasterSid").getValue());
                                        String rsMoldelSid = String.valueOf(table.getContainerProperty(itemId, "rsMoldelSid").getValue());
                                        String account = String.valueOf(table.getContainerProperty(itemId, ConstantUtil.ARP_ACCOUNT).getValue());
                                        String account_Type = String.valueOf(table.getContainerProperty(itemId, "account_Type").getValue());
                                        String program = String.valueOf(table.getContainerProperty(itemId, "program").getValue());
                                        String type = String.valueOf(table.getContainerProperty(itemId, "type").getValue());
                                        String category = String.valueOf(table.getContainerProperty(itemId, "category").getValue());
                                        String account_Category = String.valueOf(table.getContainerProperty(itemId, "account_category").getValue());
                                        String account_Group = String.valueOf(table.getContainerProperty(itemId, "account_group").getValue());

                                        ARPOutboundDTO dto = new ARPOutboundDTO();
                                        dto.setArp_Id(arpId);
                                        dto.setCompanyMasterSid(companySid);
                                        dto.setItemMasterSid(itemSid);
                                        dto.setBrandMasterSid(brandSid);
                                        dto.setRsMoldelSid(rsMoldelSid);
                                        dto.setAccount(account);
                                        dto.setAccount_Type(account_Type);
                                        dto.setCheckedValue(check);
                                        dto.setCategory(category);
                                        dto.setType(type);
                                        dto.setProgram(program);
                                        dto.setAccount_category(account_Category);
                                        dto.setAccount_group(account_Group);
                                        SearchLogic.updateTempTable(dto);

                                    }
                                });
                                field = customCheckBox;
                            }
                            return field;
                        }
                    });
                } else if (tableName.equals("VwCustomerGtsForecast") || tableName.equals(ConstantUtil.CUSTOMER_GTS_ACTUAL)
                        || ConstantUtil.INVALID_ACCURAL_INBOUND.equals(tableName) || ConstantUtil.ST_CFF_OUTBOUND.equals(tableName) || ConstantUtil.VW_ITEM_IDENTIFIER.equals(tableName) || ConstantUtil.VW_ITEM_PRICING.equals(tableName) || ConstantUtil.VW_COMPANY_IDENTIFIER.equals(tableName)
                        || ConstantUtil.VW_COMPANY_PARENT_DETAILS.equals(tableName) || ConstantUtil.VW_COMPANY_TRADE_CLASS.equals(tableName) || ConstantUtil.VW_ITEM_MASTER.equals(tableName)
                        || ConstantUtil.VW_COMPANY_MASTER.equals(tableName) || ConstantUtil.RETURN_RESERVE.equals(tableName) || ConstantUtil.IVLD_RETURN_RESERVE.equals(tableName)) {
                    tableLogic.setContainerDataSource(new BeanItemContainer(Class.forName(ConstantUtil.TABLE_MODEL_PATH_TWO + tableName)));
                } else if (tableName.equals(ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL) || tableName.equals(ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL)) {
                    tableLogic.setContainerDataSource(new BeanItemContainer(ArmOutboundDTO.class));
                } else {
                    tableLogic.setContainerDataSource(new BeanItemContainer(Class.forName(ConstantUtil.TABLE_MODEL_PATH + tableName)));
                }
                if (tableName.equals(ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL) || tableName.equals(ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL)) {
                    List<?> colHeaders = commonLogic.getColumns(tableName.equals(ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL) ? "N" : "Y");
                    tableColumnArr = (Object[]) colHeaders.get(0);
                    tableResultCustom.setObjResult((Object[]) colHeaders.get(0));
                    tableHeaderArr = (String[]) colHeaders.get(NumericConstants.ONE);
                    tableResultCustom.setObjResultHeader((String[]) colHeaders.get(NumericConstants.ONE));
                    table.setFilterGenerator(new AdjustmentDetailsFilterGenerator());
                    table.setConverter("glYear", new IntegerToStringConverter());

                }

                table.setVisibleColumns(tableResultCustom.getObjResult());
                if (tableName.equals(ConstantUtil.ST_CFF_OUTBOUND)) {
                    table.setFilterFieldVisible(ConstantUtil.CHECK_RECORD, false);
                    table.setColumnCheckBox(ConstantUtil.CHECK_RECORD, true);
                    table.setEditable(Boolean.TRUE);
                    table.setVisibleColumns(tableResultCustom.getObjResult());
                    table.setColumnHeaders(tableResultCustom.getObjResultHeader());
                    table.setColumnAlignment("financialForecastApprovalDate", ExtCustomTable.Align.CENTER);
                    table.setColumnAlignment("financialForecastCreationDate", ExtCustomTable.Align.CENTER);
                    table.setColumnAlignment("salesDollars", ExtCustomTable.Align.RIGHT);
                    table.setColumnAlignment("salesUnits", ExtCustomTable.Align.RIGHT);

                    table.setColumnAlignment("deductionDollars", ExtCustomTable.Align.RIGHT);
                    table.setColumnAlignment("deductionRate", ExtCustomTable.Align.RIGHT);
                    table.setColumnAlignment("deductionPerUnit", ExtCustomTable.Align.RIGHT);
                    table.setColumnAlignment("netSalesDollar", ExtCustomTable.Align.RIGHT);
                    table.setColumnAlignment("cogsPerUnit", ExtCustomTable.Align.RIGHT);
                    table.setColumnAlignment("netProfitDollars", ExtCustomTable.Align.RIGHT);
                    table.setColumnAlignment("netProfitPerUnit", ExtCustomTable.Align.RIGHT);
                    final SearchLogic searchLogic = new SearchLogic();
                    table.addColumnCheckListener(new ColumnCheckListener() {

                        public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                            String userId = StringUtils.EMPTY;
                            String sessionId = StringUtils.EMPTY;

                            for (Object item : table.getItemIds()) {
                                searchResultbeans.getContainerProperty(item, ConstantUtil.CHECK_RECORD).setValue(event.isChecked());
                                userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.USERID));
                                sessionId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.SESSIONID));
                            }
                            searchLogic.updateAllCheckRecord(String.valueOf(event.isChecked()).equals("true") ? "1" : "0", userId, sessionId);

                        }
                    });
                    table.setTableFieldFactory(new TableFieldFactory() {
                        public Field<?> createField(Container container, final Object itemId, Object propertyId, Component uiContext) {
                            Field field = null;

                            if (ConstantUtil.CHECK_RECORD.equals(propertyId.toString())) {
                                final ExtCustomCheckBox customCheckbox = new ExtCustomCheckBox();
                                customCheckbox.setImmediate(true);
                                customCheckbox.setEnabled(true);
                                customCheckbox.addClickListener(new ExtCustomCheckBox.ClickListener() {
                                    @Override
                                    public void click(ExtCustomCheckBox.ClickEvent event) {
                                        String check = StringUtils.EMPTY;
                                        if (customCheckbox.getValue()) {
                                            check = "1";
                                            table.setColumnCheckBox(ConstantUtil.CHECK_RECORD, true, true);
                                        } else {
                                            check = "0";
                                            if (table.getColumnCheckBox(ConstantUtil.CHECK_RECORD)) {
                                                table.setColumnCheckBox(ConstantUtil.CHECK_RECORD, true, false);
                                            }

                                        }

                                        String cffId = String.valueOf(table.getContainerProperty(itemId, "cffDetailsSid").getValue());
                                        String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.USERID));
                                        String periodSid = String.valueOf(table.getContainerProperty(itemId, "periodSid").getValue());
                                        String sessionId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.SESSIONID));
                                        String deductionId = String.valueOf(table.getContainerProperty(itemId, "rsModelSid").getValue());
                                        searchLogic.updateCheckRecord(check, cffId, userId, periodSid, sessionId, deductionId);
                                    }
                                });
                                field = customCheckbox;
                            }
                            return field;
                        }
                    });
                } else {

                    table.setColumnHeaders(tableResultCustom.getObjResultHeader());
                }
                if (tableName.equals(ConstantUtil.RETURN_RESERVE)) {
                    commonLogic.tableAlign(table);
                }
                if (ConstantUtil.ST_CFF_OUTBOUND.equals(tableName)) {
                    invalidButtonLayout.setVisible(true);
                    reset.setEnabled(true);
                    reset.setVisible(true);
                    removeBtn.setVisible(false);
                } else if (tableName.equals(ConstantUtil.ARP_OUTBOUND)) {
                    String[] header = commonLogic.getArpOutBoundHeader(tableResultCustom.getObjResultHeader());
                    table.setColumnHeaders(header);
                    invalidButtonLayout.setVisible(true);
                    reset.setVisible(true);
                    reset.setEnabled(true);
                    removeBtn.setVisible(false);
                    table.setFilterFieldVisible(ConstantUtil.CHECKED_RECORD, false);
                    table.setColumnCheckBox(ConstantUtil.CHECKED_RECORD, true);

                } else if (tableName.equals(ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL) || tableName.equals(ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL)) {
                    reset.setVisible(true);
                    reset.setEnabled(true);
                    removeBtn.setVisible(false);
                    table.setFilterFieldVisible(ConstantUtil.CHECK_RECORD, false);
                    table.setColumnCheckBox(ConstantUtil.CHECK_RECORD, true);
                } else {
                    invalidButtonLayout.setVisible(false);

                }
                table.setSelectable(primaryDto.getScreenCount() > NumericConstants.ONE);

            } else {
                resultsPanel.setVisible(false);
                excelExport.setVisible(false);
                invalidButtonLayout.setVisible(false);
                table.setEditable(true);                
                table.setColumnCheckBox("checkRecord", true, false);
                table.addColumnCheckListener(new ColumnCheckListener() {

                    public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {

                        boolean check = event.isChecked();
                        String checkValue = StringUtils.EMPTY;
                        if (check) {
                            checkValue = "1";
                        } else {
                            checkValue = "0";
                        }                                          
                        selectedRecords = new ArrayList<>();
                        SearchLogic searchLogic = new SearchLogic();
                        List<Object> checkList = new ArrayList<>();
                        try {
                            Double d = tableLogic.getRecordCount();
                            if ("IvldActualMaster".equals(invalidTableName)) {
                                checkList = searchLogic.searchFindForActualMaster(searchValues, 0, d.intValue(), tableLogic.getSortByColumns(), tableLogic.getFilters(), invalidTableName, false, null, primaryDto);
                            } else {
                                if (searchValues != null && searchValues.get("accrualType") != null 
                                        && !searchValues.get("accrualType").equals("Other") 
                                        && !invalidTableName.equals(ConstantUtil.INVALID_ACCURAL_INBOUND)) {
                                    invalidTableName = "AccrualDetails";
                                }
                                checkList = searchLogic.searchFind(searchValues, 0, d.intValue(), tableLogic.getSortByColumns(), tableLogic.getFilters(), invalidTableName,false, null, null,primaryDto);
                            }
                        } catch (Exception ex) {
                            java.util.logging.Logger.getLogger(AbstractSearch.class.getName()).log(Level.SEVERE, null, ex);
                        } 
                        for (Object item : checkList) {
                                try {
                                    String sidName = viewName.substring(0, 1).toUpperCase() + viewName.substring(1);
                                    Method method = item.getClass().getMethod("get"+sidName, null);   
                                    Object obj = method.invoke(item, null);
                                    selectedRecords.add(Integer.valueOf(String.valueOf(obj)));
                                } catch (Exception ex) {
                                    java.util.logging.Logger.getLogger(AbstractSearch.class.getName()).log(Level.SEVERE, null, ex);
                                } 
                        }
                        String invalidTableName = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.INVALID_TABLE_NAME));      
                        commonLogic.updateAllInInvalidTableOnCheckAll(selectedRecords,viewNameSid,invalidTableName,checkValue,false,StringUtils.EMPTY);
                        
                        for (Object item : table.getItemIds()) {
                            tableLogic.getContainerDataSource().getContainerProperty(item, "checkRecord").setValue(check);
                        }
                    }
                });
                table.setTableFieldFactory(new TableFieldFactory() {
                    public Field<?> createField(Container container, final Object itemId, Object propertyId, Component uiContext) {
                        Field field = null;

                        if ("checkRecord".equals(propertyId.toString())) {
                            final ExtCustomCheckBox customCheckBox = new ExtCustomCheckBox();
                            customCheckBox.setImmediate(true);
                            customCheckBox.setEnabled(true);
                            customCheckBox.addClickListener(new ExtCustomCheckBox.ClickListener() {
                                @Override
                                public void click(ExtCustomCheckBox.ClickEvent event) {                                    
                                    selectedRecordSid = (int) tableLogic.getContainerDataSource().getContainerProperty(itemId, viewName).getValue();
                                    selectedRecords.add(selectedRecordSid);
                                    String invalidTableName = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.INVALID_TABLE_NAME));                                    
                                    commonLogic.updateInvalidTable(selectedRecordSid, viewNameSid, invalidTableName, customCheckBox.getValue() ? 1 : 0);
                                }
                            });
                            field = customCheckBox;
                        }
                        return field;
                    }
                });
            }
            if (tableName.equals("Invalidrecordcount")
                    || ConstantUtil.VW_ITEM_IDENTIFIER.equals(tableName) || ConstantUtil.VW_ITEM_PRICING.equals(tableName) || ConstantUtil.VW_COMPANY_IDENTIFIER.equals(tableName)
                    || ConstantUtil.VW_COMPANY_PARENT_DETAILS.equals(tableName) || ConstantUtil.VW_COMPANY_TRADE_CLASS.equals(tableName) || ConstantUtil.VW_ITEM_MASTER.equals(tableName)
                    || ConstantUtil.VW_COMPANY_MASTER.equals(tableName)) {
                table.setColumnAlignment("companyStartDate", ExtCustomTable.Align.CENTER);
                table.setColumnAlignment("companyEndDate", ExtCustomTable.Align.CENTER);
                table.setColumnAlignment(ConstantUtil.LAST_UPDATEED_DATE, ExtCustomTable.Align.CENTER);
                table.setColumnAlignment(ConstantUtil.CREATED_DATE, ExtCustomTable.Align.CENTER);
                table.setColumnAlignment("modifiedDate", ExtCustomTable.Align.CENTER);
                table.setColumnAlignment(ConstantUtil.START_DATE, ExtCustomTable.Align.CENTER);
                table.setColumnAlignment(ConstantUtil.END_DATE, ExtCustomTable.Align.CENTER);
                table.setColumnAlignment("parentStartDate", ExtCustomTable.Align.CENTER);
                table.setColumnAlignment("parentEndDate", ExtCustomTable.Align.CENTER);
                table.setColumnAlignment("priorParentStartDate", ExtCustomTable.Align.CENTER);
                table.setColumnAlignment(ConstantUtil.LAST_UPDATEED_DATE, ExtCustomTable.Align.CENTER);
                table.setColumnAlignment("tradeClassStartDate", ExtCustomTable.Align.CENTER);
                table.setColumnAlignment("tradeClassEndDate", ExtCustomTable.Align.CENTER);
                table.setColumnAlignment("priorTradeClassStartDate", ExtCustomTable.Align.CENTER);
                table.setColumnAlignment(ConstantUtil.LAST_UPDATEED_DATE, ExtCustomTable.Align.CENTER);
                table.setColumnAlignment("itemStartDate", ExtCustomTable.Align.CENTER);
                table.setColumnAlignment("itemEndDate", ExtCustomTable.Align.CENTER);
                table.setColumnAlignment("packageSizeIntroDate", ExtCustomTable.Align.CENTER);
                table.setColumnAlignment("acquisitionDate", ExtCustomTable.Align.CENTER);
                table.setColumnAlignment("authorizedGenericStartDate", ExtCustomTable.Align.CENTER);
                table.setColumnAlignment("authorizedGenericEndDate", ExtCustomTable.Align.CENTER);
                table.setColumnAlignment("firstSaleDate", ExtCustomTable.Align.CENTER);
                table.setColumnAlignment("marketTerminationDate", ExtCustomTable.Align.CENTER);
                table.setColumnAlignment("newFormulationStartDate", ExtCustomTable.Align.CENTER);
                table.setColumnAlignment("newFormulationEndDate", ExtCustomTable.Align.CENTER);
                table.setColumnAlignment("pediatricExclusiveStartDate", ExtCustomTable.Align.CENTER);
                table.setColumnAlignment("pediatricExclusiveEndDate", ExtCustomTable.Align.CENTER);
                table.setColumnAlignment("clottingFactorStartDate", ExtCustomTable.Align.CENTER);
                table.setColumnAlignment("clottingFactorEndDate", ExtCustomTable.Align.CENTER);
                table.setColumnAlignment("clottingFactorEndDate", ExtCustomTable.Align.CENTER);
                table.setColumnAlignment("discontinuationDate", ExtCustomTable.Align.CENTER);
                table.setColumnAlignment("lastLotExpirationDate", ExtCustomTable.Align.CENTER);
                table.setColumnAlignment("divestitureDate", ExtCustomTable.Align.CENTER);
                table.setColumnAlignment("nonFederalExpirationDate", ExtCustomTable.Align.CENTER);
                table.setColumnAlignment(ConstantUtil.START_DATE, ExtCustomTable.Align.CENTER);
                table.setColumnAlignment(ConstantUtil.END_DATE, ExtCustomTable.Align.CENTER);
                table.setColumnAlignment(ConstantUtil.START_DATE, ExtCustomTable.Align.CENTER);
                table.setColumnAlignment(ConstantUtil.END_DATE, ExtCustomTable.Align.CENTER);
                table.setColumnAlignment("itemPrice", ExtCustomTable.Align.RIGHT);
                table.setColumnAlignment("acquiredAmp", ExtCustomTable.Align.RIGHT);
                table.setColumnAlignment("acquiredBamp", ExtCustomTable.Align.RIGHT);
                table.setColumnAlignment("obraBamp", ExtCustomTable.Align.RIGHT);
                table.setColumnAlignment("baselineAmp", ExtCustomTable.Align.RIGHT);
                table.setColumnAlignment("baseCpi", ExtCustomTable.Align.RIGHT);
            } else if (tableName.equals(ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL) || tableName.equals(ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL)) {
                table.setColumnAlignment("debit", ExtCustomTable.Align.RIGHT);
                table.setColumnAlignment("credit", ExtCustomTable.Align.RIGHT);
                table.setColumnAlignment("currency", ExtCustomTable.Align.RIGHT);
                table.setColumnAlignment("accountingDate", ExtCustomTable.Align.CENTER);
                table.setColumnAlignment(ConstantUtil.REDEMPTION_PERIOD, ExtCustomTable.Align.CENTER);
                table.setColumnAlignment("reversalPeriodDate", ExtCustomTable.Align.CENTER);
                table.setColumnAlignment("adjustmentCreatedDate", ExtCustomTable.Align.CENTER);
                table.setColumnAlignment("workflowCreatedDate", ExtCustomTable.Align.CENTER);
                table.setColumnAlignment("workflowApprovedDate", ExtCustomTable.Align.CENTER);
                table.setColumnAlignment(ConstantUtil.GL_DATE, ExtCustomTable.Align.CENTER);
                table.setColumnAlignment("deductionAmount", ExtCustomTable.Align.RIGHT);
                table.setColumnAlignment("deductionRate", ExtCustomTable.Align.RIGHT);
            }
            table.setPageLength(NumericConstants.FIVE);
            table.setWidth(NumericConstants.HUNDRED, UNITS_PERCENTAGE);
            table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
                @SuppressWarnings("PMD")
                public void itemClick(final ItemClickEvent event) {

                    if (table.isSelectable()) {
                        itemClickListener(event);
                    }
                }

            });
            table.addStyleName("filterbar");
            table.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
            table.setFilterBarVisible(true);
            table.setFilterDecorator(new ExtDemoFilterDecorator());
            table.setFilterFieldVisible(ConstantUtil.CHECK_RECORD, false);
            table.setFilterFieldVisible(ConstantUtil.CHECKED_RECORD, false);
            table.setImmediate(true);

            if (primaryDto.getValidation().equals("GL Balance") || primaryDto.getValidation().equals(ConstantUtil.ACCRUAL_MASTER) || primaryDto.getValidation().equals(ConstantUtil.CPI_INDEX) || primaryDto.getValidation().equals("GlobalFilesItemPricing")
                    || primaryDto.getValidation().equals("GlobalFilesCompanyMaster") || primaryDto.getValidation().equals("GlobalFilesCompanyIdentifier") || primaryDto.getValidation().equals("GlobalFilesCompanyParent") || primaryDto.getValidation().equals("GlobalFilesCompanyTradeClass")
                    || primaryDto.getValidation().equals("GlobalFilesItemMaster") || primaryDto.getValidation().equals("GlobalFilesItemIdentifier") || primaryDto.getValidation().equals("Return Reserve Actual")) {
                table.setFilterGenerator(new FilterGenerator());
            }

        } catch (Exception ec) {
            LOGGER.error(ec);
        }
        LOGGER.debug("Ends addToTable with table");
    }

    /**
     * To get table columns
     */
    private void getTableColumns() {
        tableHeaderArr = new String[headerList.size()];
        tableHeaderArr = headerList.toArray(tableHeaderArr);
        tableColumnArr = new Object[columnList.size()];
        tableColumnArr = columnList.toArray(tableColumnArr);
    }

    protected abstract void itemClickListener(ItemClickEvent event);

    protected abstract void resetButtonLogic() throws PortalException, ClassNotFoundException;

    protected abstract void btnSearchLogic() throws PortalException, SystemException;

    protected abstract void btnGenerateLogic();

    /**
     * Reset the Search criteria
     *
     */
    private void resetFields() {
        LOGGER.debug("Inside resetFields");
        searchValues.clear();
        errorMsg.clearError();
        try {
            Iterator<Component> componentIterator = null;
            Iterator<Component> componentIterator2 = null;
            Iterator<Component> componentIterator3 = null;
            if (ConstantUtil.INVALID_RECORD_COUNT.equalsIgnoreCase(selectedInvalidModule)) {
                componentIterator = cssLayout1.getComponentIterator();
            } else {
                componentIterator2 = cssLayout1.getComponentIterator();
                if (tableName.equals(ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL) || tableName.equals(ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL)) { //Added for GAL-6070
                    componentIterator = gridLayout2.getComponentIterator();
                    componentIterator3 = gridLayout1.getComponentIterator();
                } else { //Ends here
                    componentIterator = cssLayout.getComponentIterator();
                    componentIterator3 = cssLayout2.getComponentIterator();
                }
            }
            if (componentIterator2 != null) {
                while (componentIterator2.hasNext()) {
                    Component component = componentIterator2.next();
                    if (component instanceof TextField) {
                        ((TextField) component).setValue(StringUtils.EMPTY);
                    } else if (component instanceof PopupDateField) {
                        ((PopupDateField) component).setValue(null);
                    } else if (component instanceof ComboBox && !((ComboBox) component).getData().equals(ConstantUtil.TABLE)) {
                        ((ComboBox) component).select(ConstantUtil.SELECT_ONE);
                    }
                }
            }
            while (componentIterator.hasNext()) {
                Component component = componentIterator.next();
                if (component instanceof TextField) {
                    ((TextField) component).setValue(StringUtils.EMPTY);
                } else if (component instanceof PopupDateField) {
                    ((PopupDateField) component).setValue(null);
                } else if (component instanceof ComboBox && !((ComboBox) component).getData().equals(ConstantUtil.TABLE)) {
                    if ("glCompanyMasterSid".equals(((ComboBox) component).getData()) || ConstantUtil.ACC_CATEGORY.equals(((ComboBox) component).getData())
                            || ConstantUtil.POSTING_INDICATOR.equals(((ComboBox) component).getData())
                            || ConstantUtil.DEDUCTION_LEVEL.equals(((ComboBox) component).getData()) || ConstantUtil.ADJ_LEVEL.equals(((ComboBox) component).getData())) {
                        ((ComboBox) component).select(0);
                    } else {
                        ((ComboBox) component).select(ConstantUtil.SELECT_ONE);
                    }
                }
            }
            while (componentIterator3.hasNext()) {
                Component component = componentIterator3.next();
                if (component instanceof TextField) {
                    ((TextField) component).setValue(StringUtils.EMPTY);
                } else if (component instanceof PopupDateField) {
                    ((PopupDateField) component).setValue(null);
                } else if (component instanceof ComboBox) {
                    if (ConstantUtil.ADJUSTMENTS_TYPE.equals(((ComboBox) component).getData()) || ConstantUtil.GL_COMP.equals(((ComboBox) component).getData())
                            || ConstantUtil.BUSINESS_UNIT.equals(((ComboBox) component).getData())) {
                        ((ComboBox) component).select(0);
                    } else if (ConstantUtil.TRANSACTOIN_LEVEL.equals(((ComboBox) component).getData())) {
                        for (Object reserveDetail : ((ComboBox) component).getItemIds()) {
                            if (((ComboBox) component).getItemCaption(reserveDetail).equals("Reserve Detail")) {
                                ((ComboBox) component).select(reserveDetail);
                            }
                        }
                    } else {
                        ((ComboBox) component).select(ConstantUtil.SELECT_ONE);
                    }
                } else if (component instanceof CustomTextField) {
                    ((CustomTextField) component).setValue(StringUtils.EMPTY);
                }
            }
            commonLogic.setAdjustDemand(false);
            commonLogic.setInvalidAdjustedDemand(false);
            AbstractComponentCreater.setAdjustDemand(false);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    private void AdjustmentresetFields() {
        LOGGER.debug("Inside AdjustmentresetFields");
        searchValues.clear();
        errorMsg.clearError();
        try {
            resetButtonLogic();
            Iterator<Component> componentIterator = null;
            if (tableName.equals(ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL) || tableName.equals(ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL)) { //Added for GAL-6070
                componentIterator = gridLayout2.getComponentIterator();
            } else { //Ends here
                componentIterator = cssLayout.getComponentIterator();
            }
            while (componentIterator.hasNext()) {
                Component component = componentIterator.next();
                if (component instanceof TextField) {
                    if (ConstantUtil.COMPANY_NO.equals(((TextField) component).getData()) || ConstantUtil.COMPANY_NAME.equals(((TextField) component).getData())
                            || ConstantUtil.ITEMNO.equals(((TextField) component).getData()) || ConstantUtil.ITEM_NAME.equals(((TextField) component).getData())) {
                        ((TextField) component).setValue(StringUtils.EMPTY);
                    }
                } else if (component instanceof ComboBox && ConstantUtil.DEDUCTION_LEVEL.equals(((ComboBox) component).getData())) {
                    ((ComboBox) component).select(0);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * Export button.
     */
    private void exportButton() throws IOException {
        LOGGER.debug("Entering exportButton");
        excelExport.setCaption("");
        excelExport.setIcon(excelImage);
        excelExport.setVisible(true);
        excelExport.setStyleName("link");

        excelExport.setDescription("Export to Excel");
        mainLayout.addComponent(space);
        excelExport.setIconAlternateText("Excel export");

        if (!ConstantUtil.ARP_OUTBOUND.equals(tableName) && !ConstantUtil.ST_CFF_OUTBOUND.equals(tableName)
                && !tableName.equals(ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL) && !tableName.equals(ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL) && !tableName.equals(ConstantUtil.VW_CUSTOMER_GTS_FORECAST)) {
            excelExport.addClickListener(new Button.ClickListener() {
                /**
                 * After clicking export button, function will be executed.
                 */
                @SuppressWarnings("PMD")
                public void buttonClick(final Button.ClickEvent event) {
                    try {
                        LOGGER.debug("Entering buttonClick with event for excelExport");
                        createWorkSheet();
                        LOGGER.debug("Ends buttonClick for excelExport");
                    } catch (SystemException ex) {
                        final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                        LOGGER.error(errorMsg);
                        AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                    } catch (PortalException ex) {
                        LOGGER.error(ErrorCodeUtil.getErrorMessage(ex));
                        AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_3006));
                    } catch (Exception ex) {
                        LOGGER.error(ErrorCodeUtil.getErrorMessage(ex));
                        AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_3006));
                    }
                }
            });
        } else {

            Downloader downloader = new Downloader(null, "");
            OnDemandFileDownloader ondfd = new OnDemandFileDownloader(downloader);

            ondfd.extend(excelExport);
        }
        LOGGER.debug("Ends exportButton");
    }

    /**
     * Creates the work sheet.
     */
    class Downloader implements OnDemandFileDownloader.OnDemandStreamResource {

        String fileName;
        String[] header;
        String query;
        File file;

        public Downloader(String[] header, String query) {
            this.header = header;
            this.query = query;
        }

        public void setHeader(String[] header) {
            this.header = header;
        }

        public void setQuery(String query) {
            this.query = query;
        }

        @Override
        public String getFilename() {
            LOGGER.debug("Inside get file Name method :");
            if (table.size() != 0) {
                String[] header = table.getColumnHeaders();
                primaryDto = (DetailsDTO) primaryValueList.get(0);
                List<Object> queryList = null;
                String moduleName = StringUtils.EMPTY;
                String dirName = StringUtils.EMPTY;
                String outputFilePath = StringUtils.EMPTY;
                try {
                    if (ConstantUtil.ARP_OUTBOUND.equals(tableName)) {
                        queryList = logic.getARPExcelList(searchValues);
                        moduleName = "ARP";
                        dirName = "ARP_EXPORT_FILE_DIR";
                        outputFilePath = "Accrual_Rate_Projection.csv";
                    } else if (ConstantUtil.ST_CFF_OUTBOUND.equals(tableName)) {
                        queryList = SearchLogic.excelSelectQuery(tableLogic.getFilters(), tableLogic.getSortByColumns(), tableName);
                        moduleName = "CFF_OUTBOUND";
                        dirName = "CFF_EXPORT_FILE_DIR";
                        outputFilePath = "CFF_Outbound.csv";
                    } else if (tableName.equals(ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL) || tableName.equals(ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL)) {
                        queryList = SearchLogic.gtnSearch(searchValues, Boolean.FALSE, tableLogic.getFilters(), tableLogic.getSortByColumns(), 0, 0, Boolean.FALSE, tableName, Boolean.TRUE);
                        moduleName = "ADJUSTMENT_DETAILS";
                        dirName = "ADJUSTMENT_DETAILS_EXPORT_FILE_DIR";
                        outputFilePath = "AdjustmentDetails.csv";
                    } else if (ConstantUtil.VW_CUSTOMER_GTS_FORECAST.equals(tableName)) {
                        queryList = SearchLogic.excelQueryForGTSForecast(tableLogic.getFilters(), tableLogic.getSortByColumns(), tableName, searchValues);
                        moduleName = "GTS_FORECAST";
                        dirName = "GTS_FORECAST_DIR";
                        outputFilePath = "GTS_Forecast.csv";
                    }
                    if (!ConstantUtil.VW_CUSTOMER_GTS_FORECAST.equals(tableName)) {
                        List header1 = new ArrayList();
                        for (int i = 0; i < header.length; i++) {
                            if (i != 0) {
                                header1.add(header[i]);
                            }
                        }
                        header = (String[]) header1.toArray(new String[0]);
                    }
                    String query = String.valueOf(queryList.get(0));
                    long exportBeginTime = System.currentTimeMillis();
                    fileName = BCPExcelUtility.excelExport_bcpUtility(moduleName, header, query, outputFilePath);
                    long exportEndTime = System.currentTimeMillis();
                    LOGGER.debug("BCP Export took " + (exportEndTime - exportBeginTime) + " milliseconds");
                    file = new File(fileName);
                    List<String> fileList = (List) VaadinSession.getCurrent().getAttribute(dirName);
                    if (fileList == null) {
                        fileList = new ArrayList<>();
                    }
                    String tempFileName = file.getAbsolutePath();
                    tempFileName = tempFileName.substring(0, tempFileName.lastIndexOf(File.separator) + NumericConstants.ONE);
                    fileList.add(tempFileName);
                    VaadinSession.getCurrent().setAttribute(dirName, fileList);
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
                return file.getName();
            }
            return null;
        }

        @Override
        public InputStream getStream() {

            LOGGER.debug("Getting Stream to Export :");
            try {

                if (file != null) {
                    return new FileInputStream(file);
                } else {
                    return null;
                }
            } catch (FileNotFoundException ex) {
                LOGGER.error(ex);
            }
            return null;
        }

    }

    private void createWorkSheet() throws SystemException, PortalException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InstantiationException, IllegalArgumentException, InvocationTargetException {
        try{
        LOGGER.debug("Entering createWorkSheet");
        long recordCount = 0;
        String[] header = table.getColumnHeaders();
        primaryDto = (DetailsDTO) primaryValueList.get(0);
        if (!ConstantUtil.INVALID_RECORD_COUNT.equalsIgnoreCase(tableName)) {
            if (ConstantUtil.ACCRUAL_MASTER.equals(primaryDto.getValidation())) {
                EXCEL_NAME = "Accrual Details Master";
            } else if (ConstantUtil.CPI_INDEX.equals(primaryDto.getValidation())) {
                EXCEL_NAME = "Consumer Price Index";
            } else if (ConstantUtil.GL_SPACE_BALANCE.equals(primaryDto.getValidation())) {
                EXCEL_NAME = "GL Balance Master";
            } else if (ConstantUtil.SALES_SPACE_MASTER.equals(primaryDto.getValidation())) {
                EXCEL_NAME = "Sales Actual";
            } else if (ConstantUtil.ACTUAL_GTS_CUSTOMER_PRODUCT.equals(primaryDto.getValidation())) {
                EXCEL_NAME = "GTS Actual";
            } else if (ConstantUtil.CUSTOMER_SALES.equals(primaryDto.getValidation())) {
                EXCEL_NAME = "GTS Forecast";
            } else if (ConstantUtil.FORECAST_SALES.equals(primaryDto.getValidation())) {
                EXCEL_NAME = "Sales Forecast";
            } else if (ConstantUtil.INVENTORY.equals(primaryDto.getValidation())) {
                EXCEL_NAME = "Inventory Withdrawals";
            } else if (ConstantUtil.ACTUAL_MASTER.equals(primaryDto.getValidation())) {
                EXCEL_NAME = "Payments";
            } else if (ConstantUtil.AVERAGE_SHELF_LIFE_MASTER.equals(primaryDto.getValidation())) {
                EXCEL_NAME = "Average Shelf Life";
            } else {
                EXCEL_NAME = primaryDto.getValidation() == null || StringUtils.isBlank(primaryDto.getValidation()) ? "Transaction" : primaryDto.getValidation();
            }
        } else if (ConstantUtil.INVENTORY.equals(EXCEL_NAME)) {
            EXCEL_NAME = "Inventory Withdrawal Summary";
        } else if (ConstantUtil.CUSTOMERSALES.equals(EXCEL_NAME)) {
            EXCEL_NAME = "Projected GTS Customer Level";
        } else if (ConstantUtil.ACTUALGTSCUSTOMERSALES.equals(EXCEL_NAME)) {
            EXCEL_NAME = "Actual GTS Customer Level";
        } else if (ConstantUtil.ACCRUAL_DETAILS.equals(EXCEL_NAME)) {
            EXCEL_NAME = "Accrual Inbound Details";
        }
        if (table.size() != 0) {

                SearchLogic searchLogic = new SearchLogic();
                if (tableName != null) {
                    if (!ConstantUtil.ARP_OUTBOUND.equals(tableName)) {
                        if (searchValues.isEmpty()) {
                            recordCount = (int) searchLogic.getActualsTotalCount(ConstantUtil.INVALID_RECORD_COUNT.equalsIgnoreCase(tableName) ? invalidTableName : tableName);
                        } else if ("ActualsMaster".equals(tableName) || ConstantUtil.IVID_ACTUAL_MASTER.equals(invalidTableName)) {
                            recordCount = searchLogic.getActualSearchResults(searchValues, null, ConstantUtil.INVALID_RECORD_COUNT.equalsIgnoreCase(tableName) ? invalidTableName : tableName);
                        } else {
                            if (searchValues != null && searchValues.get(ConstantUtil.ACCRUAL_TYPE) != null && !searchValues.get(ConstantUtil.ACCRUAL_TYPE).equals(ConstantUtil.OTHER)
                                    && !tableName.equalsIgnoreCase(ConstantUtil.INVALID_RECORD_COUNT)) {
                                tableName = ConstantUtil.ACCURAL_DETAILS;
                            }
                            recordCount = searchLogic.getDynamicQuerySearch(searchValues, null, ConstantUtil.INVALID_RECORD_COUNT.equalsIgnoreCase(tableName) ? invalidTableName : tableName);
                        }
                    }
                    List tableColumnList = new ArrayList(Arrays.asList(table.getVisibleColumns()));
                    List tableHeaderList = new ArrayList<String>(Arrays.asList(header));
                    if (tableColumnList.contains("checkRecord")) {
                        tableColumnList.remove("checkRecord");
                        tableHeaderList = tableHeaderList.subList(1, tableHeaderList.size());
                    }
                    excelVisibleColumnArr = tableColumnList.toArray();
                    header = (String[]) tableHeaderList.toArray(new String[0]);
                    if (invalidTableName.equals(ConstantUtil.IVID_SALES_MASTER) || tableName.equals(ConstantUtil.IVID_SALES_MASTER)) {
                        EXCEL_NAME = "SalesMaster";
                    }
                }
                if (!ConstantUtil.ARP_OUTBOUND.equals(tableName) && !ConstantUtil.ST_CFF_OUTBOUND.equals(tableName)
                        && !tableName.equals(ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL) && !tableName.equals(ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL)
                        && !tableName.equals(ConstantUtil.VW_CUSTOMER_GTS_FORECAST)) {
                    ExcelExportforBB.createWorkSheet(header, recordCount, this, getUI().getCurrent(), EXCEL_NAME.trim().replace(" ", ""));
                }

                LOGGER.debug("Ends createWorkSheet");

            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * This method used to write data in excel.
     *
     * @param start - start limit
     * @param end - end limit
     * @param printWriter the print writer
     */
    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws PortalException, SystemException {
        LOGGER.debug("Entering createWorkSheetContent with start value  :::: " + start + ",  and end value  :::: " + end + "  and printWriter");
        try {
            primaryDto.setInvalidTableName(viewName);
            List<Object> salesList = new ArrayList<Object>();
            if (ConstantUtil.INVALID_RECORD_COUNT.equalsIgnoreCase(tableName)) {
                salesList = logic.getExcelList(searchValues, start, end, excelVisibleColumnArr, invalidTableName, primaryDto);
            } else {

                if (searchValues != null && searchValues.get(ConstantUtil.ACCRUAL_TYPE) != null && !searchValues.get(ConstantUtil.ACCRUAL_TYPE).equals(ConstantUtil.OTHER)) {
                    tableName = ConstantUtil.ACCURAL_DETAILS;
                }
                salesList = logic.getExcelList(searchValues, start, end, excelVisibleColumnArr, tableName, primaryDto);
            }
            for (int rowCount = 0; rowCount < salesList.size(); rowCount++) {
                Object[] ob = (Object[]) salesList.get(rowCount);
                for (int i = 0; i < ob.length; i++) {
                    if (i == (ob.length - 1)) {
                        if (ob[i] instanceof Date) {

                            if (ob[i] != null) {
                                printWriter.println(getFormattedDate(ob[i]));
                            } else {
                                printWriter.println(StringUtils.EMPTY);
                            }
                        } else {

                            printWriter.println(ExcelExportUtil.replaceDoubleQuotes(ob[i]));
                        }
                    } else if (ob[i] instanceof Date) {
                        if (ob[i] != null) {
                            printWriter.print(getFormattedDate(ob[i]) + ExcelExportUtil.COMMA);
                        } else {
                            printWriter.print(StringUtils.EMPTY + ExcelExportUtil.COMMA);
                        }
                    } else {

                        printWriter.print(ConstantUtil.QUOTE + ConstantUtil.TAB + ExcelExportUtil.replaceDoubleQuotes(ob[i]) + ConstantUtil.QUOTE + ExcelExportUtil.COMMA);

                    }
                }
            }
            LOGGER.debug("Ends createWorkSheetContent");
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    private String getFormattedDate(Object ob) {
        String date;
        final SimpleDateFormat dateFormat = new SimpleDateFormat(ExcelExportUtil.DATE_FORMAT);
        if (ob == null) {
            date = StringUtils.EMPTY;
        } else {
            date = dateFormat.format((Date) ob);
        }
        return date;
    }

    public static String getMessage(final String key) {
        try {
            return resouceBundle.getString(key);
        } catch (MissingResourceException e) {
            LOGGER.debug(e);
            return null;
        }
    }

    /**
     * Gets the is active.
     *
     * @return the checks if is active
     */
    public List<HelperDTO> getIsActive() throws SystemException, PortalException {
        LOGGER.debug("Entering getIsActive");
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        for (int i = 0; i < NumericConstants.THREE; i++) {
            HelperDTO dto;
            if (i == 0) {
                dto = new HelperDTO(i, ConstantUtil.SELECT_ONE);
            } else if (i == NumericConstants.ONE) {
                dto = new HelperDTO(i, ConstantUtil.YES);
            } else {
                dto = new HelperDTO(i, ConstantUtil.NO);
            }
            helperList.add(dto);
            Collections.sort(helperList);
        }
        LOGGER.debug(" Ends getIsActive with the  helperList size    ::::  " + helperList.size());

        return helperList;
    }

    public List<HelperDTO> getIsoutline() throws SystemException, PortalException {
        LOGGER.debug("Entering getIsActive");
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        for (int i = 0; i < NumericConstants.THREE; i++) {
            HelperDTO dto;
            if (i == 0) {
                dto = new HelperDTO(i, ConstantUtil.SELECT_ONE);
            } else if (i == NumericConstants.ONE) {
                dto = new HelperDTO(i, "true");
            } else {
                dto = new HelperDTO(i, "false");
            }
            helperList.add(dto);

        }
        LOGGER.debug(" Ends getIsoutline with the  helperList size    ::::  " + helperList.size());

        return helperList;
    }

    public void getComponentsForInvalidRecordCount() {
        if (tableValue != null && tableValue != "null") {
            try {
                primaryDto.setValidation(tableValue);
                VaadinSession.getCurrent().setAttribute(ConstantUtil.DDLB_NAME, tableValue);
                VaadinSession.getCurrent().setAttribute(ConstantUtil.TABLE_NAME1, tableName);
                Object[] ob = commonLogic.getFiledNames((String) VaadinSession.getCurrent().getAttribute(ConstantUtil.DDLB_NAME), ConstantUtil.SEARCH);
                VaadinSession.getCurrent().setAttribute(ConstantUtil.TABCOUNT, Integer.valueOf(ob[NumericConstants.TWO].toString()));

                newList = (List<DetailsDTO>) ob[0];
                List<DetailsDTO> primaryValueList = (List<DetailsDTO>) ob[NumericConstants.ONE];
                moduleDetailsValue.clear();
                cssLayout.removeAllComponents();
                buttonLayout.removeAllComponents();
                moduleDetailsValue.addAll(newList);
                primaryDto = (DetailsDTO) primaryValueList.get(0);
                recordInvalidName = CommonLogic.getUpperCamelString(moduleDetailsValue.get(0).getTableName(), false);
                if (ob[NumericConstants.THREE] != null) {
                    invalidTableName = CommonLogic.getUpperCamelString(ob[NumericConstants.THREE].toString(), false);
                    VaadinSession.getCurrent().setAttribute(ConstantUtil.INVALID_TABLE_NAME1, ConstantUtil.INVALID_INVENTORYVIEW_TABLE.equals(invalidTableName) ? ConstantUtil.IVLD_INVENTORY_WITHDRAWAL_SUMMARY : invalidTableName);
                    VaadinSession.getCurrent().setAttribute(ConstantUtil.INVALID_TABLE_NAME, ob[NumericConstants.THREE].toString());
                }
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
            resultsPanel.setVisible(true);
            excelExport.setVisible(true);
            invalidButtonLayout.setVisible(true);
            reset.setVisible(false);

        } else {
            cssLayout.removeAllComponents();
            buttonLayout.removeAllComponents();
            resultsPanel.setVisible(false);
            excelExport.setVisible(false);
            if (!ConstantUtil.ST_CFF_OUTBOUND.equals(tableName)) {
                invalidButtonLayout.setVisible(false);
            } else {
                invalidButtonLayout.setVisible(true);
                reset.setEnabled(true);
                removeBtn.setVisible(false);
            }
        }
    }

    public void getTableForInvalidRecordCount() {
        try {
            LOGGER.debug("Entering getTableForInvalidRecordCount");
            selectedInvalidModule = tableValue;
            getDynamicComponent();
            tableOnChange();
            LOGGER.debug("Ends getTableForInvalidRecordCount with table");
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

    }

    protected void tableOnChange() {
        try {
            LOGGER.debug("Inside Table On change " + invalidTableName);
            table.markAsDirty();
            getTableColumns();

            if (invalidTableName.equals("IvldCustomerGtsForecast") || ConstantUtil.IVLD_ADJUSTED_DEMAND_VIEW.equals(invalidTableName) || ConstantUtil.ST_CFF_OUTBOUND.equals(tableName)
                    || ConstantUtil.INVALID_CUSTOMER_GTS_ACTUAL.equals(invalidTableName) || ConstantUtil.INVALID_ACCURAL_INBOUND.equals(invalidTableName) || ConstantUtil.INVALID_COMPANY_MASTER.equals(invalidTableName) || ConstantUtil.INVALID_COMPANY_IDENTIFIER.equals(invalidTableName) || ConstantUtil.INVALID_COMPANY_PARENT.equals(invalidTableName) || ConstantUtil.INVALID_COMPANY_TRADE_CLASS.equals(invalidTableName) || invalidTableName.equals(ConstantUtil.IBID_ITEM_MASTER) || invalidTableName.equals(ConstantUtil.IVID_ITEM_IDENTIFIER) || invalidTableName.equals(ConstantUtil.IVID_ITEM_PRICING) || ConstantUtil.VW_ITEM_IDENTIFIER.equals(tableName) || ConstantUtil.VW_ITEM_PRICING.equals(tableName) || ConstantUtil.VW_COMPANY_IDENTIFIER.equals(tableName)
                    || ConstantUtil.VW_COMPANY_PARENT_DETAILS.equals(tableName) || ConstantUtil.VW_COMPANY_TRADE_CLASS.equals(tableName) || ConstantUtil.VW_ITEM_MASTER.equals(tableName)
                    || ConstantUtil.VW_COMPANY_MASTER.equals(tableName) || ConstantUtil.IVLD_RETURN_RESERVE.equals(invalidTableName)) {
                tableLogic.setContainerDataSource(new BeanItemContainer(Class.forName(ConstantUtil.TABLE_MODEL_PATH_TWO + invalidTableName)));
            } else {
                tableLogic.setContainerDataSource(new BeanItemContainer(Class.forName(ConstantUtil.TABLE_MODEL_PATH + invalidTableName)));
            }

            Object[] tableColumnArr1 = null;
            String[] tableHeaderArr1 = null;
            if (invalidTableName.equalsIgnoreCase(ConstantUtil.IVID_ACTUAL_MASTER)) {
                List<Object> list = new ArrayList<Object>(Arrays.asList(tableColumnArr));
                list.removeAll(Arrays.asList("actualsMasterSid"));
                tableColumnArr1 = list.toArray();

                List<String> list1 = new ArrayList<String>(Arrays.asList(tableHeaderArr));
                list1.removeAll(Arrays.asList("Actuals Master ID"));
                tableHeaderArr1 = list1.toArray(new String[list1.size()]);
                table.setVisibleColumns(tableColumnArr1);
                table.setColumnHeaders(tableHeaderArr1);
            } else if (ConstantUtil.IVID_SALES_MASTER.equalsIgnoreCase(invalidTableName)) {
                for (int i = 0; i < tableColumnArr.length; i++) {
                    if (tableColumnArr[i].toString().equals("itemParentNo")) {
                        tableColumnArr[i] = "parentItemNo";
                    }
                }
                table.setVisibleColumns(tableColumnArr);
                table.setColumnHeaders(tableHeaderArr);
            } else {
                table.setVisibleColumns(tableColumnArr);
                table.setColumnHeaders(tableHeaderArr);

            }
            table.setPageLength(NumericConstants.FIVE);
            table.setSelectable(true);
            table.setMultiSelect(true);
            table.setWidth(NumericConstants.HUNDRED, UNITS_PERCENTAGE);
            table.addStyleName("filterbar");
            table.setFilterBarVisible(true);
            table.setFilterDecorator(new ExtDemoFilterDecorator());
            table.setImmediate(true);
            if (primaryDto.getValidation().equals(ConstantUtil.GL_SPACE_BALANCE) || primaryDto.getValidation().equals(ConstantUtil.ACCRUAL_MASTER) || primaryDto.getValidation().equals(ConstantUtil.CPI_INDEX)) {
                table.setFilterGenerator(new FilterGenerator());
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("Inside Table On change ends" + invalidTableName);
    }

    protected void propertyId() {

        String screen = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.DDLB_NAME));
        if (screen.equals("ActualsMaster")) {
            generatedColumn = "actualIntfid";
            viewName = "ivldActualMasterSid";
            stagingTable = "STAG_ACTUAL_MASTER";
            viewNameSid = "IVLD_ACTUAL_MASTER_SID";
        } else if (screen.equals("AverageShelfLife")) {
            generatedColumn = "averageShelfLifeIntfid";
            viewName = "ivldAverageShelfLifeSid";
            stagingTable = "STAG_AVERAGE_SHELF_LIFE";
            viewNameSid = "IVLD_AVERAGE_SHELF_LIFE_SID";
        } else if (screen.equals("BestPrice")) {
            generatedColumn = "bestPriceIntfid";
            viewName = "ivldBestPriceSid";
            stagingTable = "STAG_BEST_PRICE";
            viewNameSid = "IVLD_BEST_PRICE_SID";
        } else if (screen.equals("CPIMaster")) {
            generatedColumn = "cpiIntfid";
            viewName = "ivldCpiSid";
            stagingTable = "STAG_CPI";
            viewNameSid = "IVLD_CPI_SID";
        } else if (screen.equals("ForecastSales")) {
            generatedColumn = "forecastIntfid";
            viewName = "ivldForecastSalesSid";
            stagingTable = "STAG_FORECAST_SALES";
            viewNameSid = "IVLD_FORECAST_SALES_SID";
        } else if (screen.equals("FormulaDetails")) {
            generatedColumn = "formulaDetailsIntfid";
            viewName = "ivldFormulaDetailsSid";
            viewNameSid = "IVLD_FORMULA_DETAILS_SID";
            stagingTable = "STAG_FORMULA_DETAILS";
        } else if (screen.equals("GLBalanceMaster")) {
            generatedColumn = "glBalanceIntfid";
            viewName = "ivldGlBalanceSid";
            stagingTable = "STAG_GL_BALANCE";
            viewNameSid = "IVLD_GL_BALANCE_SID";
        } else if (screen.equals("GlCostCenterMaster")) {
            generatedColumn = "glCostCenterIntfid";
            viewName = "ivldGlCostCenterSid";
            stagingTable = "STAG_GL_COST_CENTER";
            viewNameSid = "IVLD_GL_COST_CENTER_SID";
        } else if (screen.equals("ItemHierarchy")) {
            generatedColumn = "itemHierarchyIntfid";
            viewName = "ivldItemHierarchySid";
            stagingTable = "STAG_ITEM_HIERARCHY";
            viewNameSid = "IVLD_ITEM_HIERARCHY_SID";
        } else if (screen.equals("ItemHierarchyDefinition")) {
            generatedColumn = "itemHierarchyDefnIntfid";
            viewName = "ivldItemHierarchyDefinitionSid";
            viewNameSid = "IVLD_ACTUAL_MASTER_SID";
            stagingTable = "IVLD_ITEM_HIERARCHY_DEFINITION_SID";
        } else if (screen.equals("LotMaster")) {
            generatedColumn = "lotMasterIntfid";
            viewName = "ivldLotMasterSid";
            stagingTable = "STAG_LOT_MASTER";
            viewNameSid = "IVLD_LOT_MASTER_SID";
        } else if (screen.equals("MasterDataAttributeMaster")) {
            generatedColumn = "dataAttributeIntfid";
            viewName = "ivldMasterDataAtbteSid";
            stagingTable = "STAG_MASTER_DATA_ATTRIBUTE";
            viewNameSid = "IVLD_MASTER_DATA_ATBTE_SID";
        } else if (screen.equals("SalesMaster")) {
            generatedColumn = "salesIntfid";
            viewName = "ivldSalesMasterSid";
            stagingTable = "STAG_SALES_MASTER";
            viewNameSid = "IVLD_SALES_MASTER_SID";

        } else if (screen.equals("Inventory")) {
            if (ConstantUtil.PROJECTION.equalsIgnoreCase(primaryDto.getTypeDdlb())) {
                generatedColumn = "inventoryWdActualProjMasIntfId";
                viewName = "ivldInventoryWdActualProjMasSid";
                stagingTable = "STAG_INVENTORY_WD_ACTUAL_MAS";
                viewNameSid = "IVLD_INVENTORY_WD_ACTUAL_PROJ_MAS_SID";
            } else {
                generatedColumn = "inventoryWdActualProjMasIntfId";
                viewName = "ivldInventoryWdActualProjMasSid";
                stagingTable = "STAG_INVENTORY_WD_PROJ_MAS";
                viewNameSid = "IVLD_INVENTORY_WD_ACTUAL_PROJ_MAS_SID";
            }

        } else if (screen.equals(ConstantUtil.DEMAND)) {
            if (ConstantUtil.DEMAND.equals(demandTypeValue)) {
                if (ConstantUtil.PROJECTION.equalsIgnoreCase(tableValue1)) {
                    generatedColumn = ConstantUtil.DEMAND_INT_SID;
                    viewName = "ivldDemandActualForecastSid";
                    stagingTable = "STAG_DEMAND_FORECAST";
                    viewNameSid = "IVLD_DEMAND_ACTUAL_FORECAST_SID";
                } else {
                    generatedColumn = ConstantUtil.DEMAND_INT_SID;
                    viewName = "ivldDemandActualForecastSid";
                    stagingTable = "STAG_DEMAND_ACTUAL";
                    viewNameSid = "IVLD_DEMAND_ACTUAL_FORECAST_SID";
                }
            } else if (ConstantUtil.PROJECTION.equalsIgnoreCase(tableValue1)) {
                generatedColumn = ConstantUtil.ADJ_DEMAND_FORECAST_INT;
                viewName = "ivldAdjustedDemandForecastSid";
                stagingTable = "STAG_ADJUSTED_DEMAND_FORECAST";
                viewNameSid = "IVLD_ADJUSTED_DEMAND_FORECAST_SID";
            } else {
                generatedColumn = ConstantUtil.ADJ_DEMAND_FORECAST_INT;
                viewName = "ivldAdjustedDemandForecastSid";
                stagingTable = "STAG_ADJUSTED_DEMAND_ACTUAL";
                viewNameSid = "IVLD_ADJUSTED_DEMAND_FORECAST_SID";
            }
        } else if (screen.equals(ConstantUtil.RETURNS)) {
            generatedColumn = "rreserveInterfaceId";
            viewName = "ivldReturnsSid";
            stagingTable = "STAG_RETURNS";
            viewNameSid = "IVLD_RETURNS_SID";

        } else if (screen.equals(ConstantUtil.CUSTOMERSALES)) {
            generatedColumn = "customerGtsForecastIntfId";
            viewName = "ivldCustomerGtsForecastSid";
            stagingTable = "STAG_CUSTOMER_GTS_FORECAST";
            viewNameSid = "IVLD_CUSTOMER_GTS_FORECAST_SID";

        } else if (screen.equals(ConstantUtil.ACTUALGTSCUSTOMERSALES)) {
            generatedColumn = "customerGtsActualIntfId";
            viewName = "ivldCustomerGtsActualSid";
            stagingTable = "STAG_CUSTOMER_GTS_ACTUAL";
            viewNameSid = "IVLD_CUSTOMER_GTS_ACTUAL_SID";

        } else if (screen.equals(ConstantUtil.ACCURAL_DETAILS)) {
            generatedColumn = "accrualIntfid";
            viewName = "ivldAccrualInboundSid";
            stagingTable = "STAG_ACCRUAL_INBOUND";
            viewNameSid = "IVLD_ACCRUAL_INBOUND_SID";

        } else if (screen.equals("GlobalFilesCompanyMaster")) {
            generatedColumn = "companyMasterIntfid";
            viewName = "ivldCompanyMasterSid";
            stagingTable = "STAG_COMPANY_MASTER";
            viewNameSid = "IVLD_COMPANY_MASTER_SID";

        } else if (screen.equals("GlobalFilesCompanyIdentifier")) {
            generatedColumn = "companyIdentifierIntfid";
            viewName = "ivldCompanyIdentifierSid";
            stagingTable = "STAG_COMPANY_IDENTIFIER";
            viewNameSid = "IVLD_COMPANY_IDENTIFIER_SID";

        } else if (screen.equals("GlobalFilesCompanyParent")) {
            generatedColumn = "parentDetailsIntfid";
            viewName = "ivldCompanyParentSid";
            stagingTable = "STAG_COMPANY_PARENT";
            viewNameSid = "IVLD_COMPANY_PARENT_SID";

        } else if (screen.equals("GlobalFilesCompanyTradeClass")) {
            generatedColumn = "tradeClassIntfid";
            viewName = "ivldCompanyTradeClassSid";
            stagingTable = "STAG_COMPANY_TRADE_CLASS";
            viewNameSid = "IVLD_COMPANY_TRADE_CLASS_SID";

        } else if (screen.equals(ConstantUtil.ITEM_MASTER)) {
            generatedColumn = "itemMasterIntfid";
            viewName = "ivldItemMasterSid";
            stagingTable = "STAG_ITEM_MASTER";
            viewNameSid = "IVLD_ITEM_MASTER_SID";

        } else if (screen.equals(ConstantUtil.ITEM_IDENTIFIER)) {
            generatedColumn = "itemIdentifierIntfid";
            viewName = "ivldItemIdentifierSid";
            stagingTable = "STAG_ITEM_IDENTIFIER";
            viewNameSid = "IVLD_ITEM_IDENTIFIER_SID";

        } else if (screen.equals(ConstantUtil.ITEM_PRICING)) {
            generatedColumn = "itemPricingIntfid";
            viewName = "ivldItemPricingSid";
            stagingTable = "STAG_ITEM_PRICING";
            viewNameSid = "IVLD_ITEM_PRICING_SID";

        } else if (screen.equals(ConstantUtil.RETURN_RESERVE_ACTUAL)) {
            generatedColumn = "returnReserveIntfId";
            viewName = "ivldReturnReserveSid";
            stagingTable = "STAG_RETURN_RESERVE";
            viewNameSid = "IVLD_RETURN_RESERVE_SID";

        }
        primaryDto.setInvalidTableName(viewName);
        tableLogic.configureSearchLogic(primaryDto);
    }

    private void removeBtn() {
        removeBtn.addClickListener(new Button.ClickListener() {
            /**
             * After clicking remove button, function will be executed.
             */
            @SuppressWarnings("PMD")

            public void buttonClick(final Button.ClickEvent event) {

                    if (commonLogic.isRecordChecked(String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.INVALID_TABLE_NAME)))) {
                    System.out.println("check Record "+commonLogic.isRecordChecked(String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.INVALID_TABLE_NAME))));
                    MessageBox.showPlain(Icon.QUESTION, ConstantUtil.CONFORMATION, "Are you sure you want to Remove the selected record(s)?  Removed records will no longer be visible in the Invalid Table search Results" + " ?", new MessageBoxListener() {
                        /**
                         * Called when a Button has been clicked .
                         *
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            if (buttonId.name().equals("YES")) {
                                try {
                                    logic.updateFlagForReprocessedOrRemovedRecords();
                                    commonLogic.updateAllInInvalidTable(String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.INVALID_TABLE_NAME)), "0");
                                    table.removeAllItems();
                                    searchResultbeans.removeAllItems();
                                    tableLogic.groupChange();

                                } catch (Exception ex) {
                                    LOGGER.error(ex);
                                }

                            }
                        }
                    }, ButtonId.YES, ButtonId.NO);
                } else {
                    AbstractNotificationUtils.getErrorNotification("Halt", "Please check mark at least one row from the Results list view.");
                }

            }
        });
    }

    protected void removeList() {
        LOGGER.debug("Enters removeList method");
        if (demandTypeValue != null && forecastTypeValue != null) {
            if (ConstantUtil.DEMAND.equalsIgnoreCase(demandTypeValue)) {
                if (ConstantUtil.PROJECTION.equalsIgnoreCase(forecastTypeValue)) {
                    if (columnList.contains(ConstantUtil.FORECASTVERSION)) {
                        columnList.remove(ConstantUtil.FORECASTVERSION);
                    }
                    if (!(headerList.contains(ConstantUtil.FORECASTING_NAME) || headerList.contains(ConstantUtil.FORECAST_SPACE_VERSION)
                            || headerList.contains(ConstantUtil.UNCAPTURED_UNIT) || headerList.contains(ConstantUtil.UNCAPTURED_UNIT_RATIO) || headerList.contains(ConstantUtil.UNCAPTURED_UNIT_RATIO))) {
                        headerList.add(1, ConstantUtil.FORECASTING_NAME);
                        headerList.add(NumericConstants.TWO, ConstantUtil.FORECAST_SPACE_VERSION);
                        headerList.add(NumericConstants.SEVEN, ConstantUtil.DEMANDTYPE);
                        headerList.add(NumericConstants.THIRTEEN, ConstantUtil.UNCAPTURED_UNIT);
                        headerList.add(NumericConstants.FOURTEEN, ConstantUtil.UNCAPTURED_UNIT_RATIO);
                        headerList.add(NumericConstants.SEVENTEEN, ConstantUtil.INVENTORY_UNITS_CHANGE);
                    }
                    if (!(columnList.contains(ConstantUtil.FORECAST_NAME) || columnList.contains(ConstantUtil.FORECAST_VER)
                            || columnList.contains(ConstantUtil.UN_CAPTURED_UNITS) || columnList.contains(ConstantUtil.UN_CAPTURED_UNITS_RATIO) || columnList.contains(ConstantUtil.INVENTORYUNITCHANGE))) {
                        columnList.add(1, ConstantUtil.FORECAST_NAME);
                        columnList.add(NumericConstants.TWO, ConstantUtil.FORECAST_VER);
                        columnList.add(NumericConstants.SEVEN, ConstantUtil.IS_FORECAST);
                        columnList.add(NumericConstants.THIRTEEN, ConstantUtil.UN_CAPTURED_UNITS);
                        columnList.add(NumericConstants.FOURTEEN, ConstantUtil.UN_CAPTURED_UNITS_RATIO);
                        columnList.add(NumericConstants.SEVENTEEN, ConstantUtil.INVENTORYUNITCHANGE);
                    }
                    if (!columnList.contains(ConstantUtil.FORECAST_VER)) {
                        columnList.add(NumericConstants.ONE, ConstantUtil.FORECAST_VER);
                    }
                } else {
                    if (headerList.contains(ConstantUtil.FORECASTING_NAME)) {
                        headerList.remove(ConstantUtil.FORECASTING_NAME);
                    }
                    if (headerList.contains(ConstantUtil.FORECAST_SPACE_VERSION)) {
                        headerList.remove(ConstantUtil.FORECAST_SPACE_VERSION);
                    }
                    headerList.remove(ConstantUtil.DEMANDTYPE);
                    headerList.remove(ConstantUtil.UNCAPTURED_UNIT);
                    headerList.remove(ConstantUtil.UNCAPTURED_UNIT_RATIO);
                    headerList.remove(ConstantUtil.INVENTORY_UNITS_CHANGE);

                    if (columnList.contains(ConstantUtil.FORECAST_NAME)) {
                        columnList.remove(ConstantUtil.FORECAST_NAME);
                    }
                    columnList.remove(ConstantUtil.IS_FORECAST);
                    columnList.remove(ConstantUtil.UN_CAPTURED_UNITS);
                    columnList.remove(ConstantUtil.UN_CAPTURED_UNITS_RATIO);
                    columnList.remove(ConstantUtil.INVENTORYUNITCHANGE);
                    if (columnList.contains(ConstantUtil.FORECASTVERSION)) {
                        columnList.remove(ConstantUtil.FORECASTVERSION);
                    } else if (columnList.contains(ConstantUtil.FORECAST_VER)) {
                        columnList.remove(ConstantUtil.FORECAST_VER);
                    }

                }
                getTableColumns();
                if (!(columnList.contains(ConstantUtil.FORECAST_YEAR)
                        || columnList.contains(ConstantUtil.FORECAST_MONTH) || columnList.contains(ConstantUtil.TOTAL_DEMAND_UNITS) || columnList.contains(ConstantUtil.TOTAL_DEMAND_AMT))) {
                    columnList.set(columnList.indexOf("year"), ConstantUtil.FORECAST_YEAR);
                    columnList.set(columnList.indexOf(ConstantUtil.MONTH), ConstantUtil.FORECAST_MONTH);
                    columnList.set(columnList.indexOf(ConstantUtil.TOTAL_ADJ_DEMAND_UNITS), ConstantUtil.TOTAL_DEMAND_UNITS);
                    columnList.set(columnList.indexOf(ConstantUtil.TOTAL_ADJ_DEMAND_AMT), ConstantUtil.TOTAL_DEMAND_AMT);
                }
                if (ConstantUtil.INVALID_RECORD_COUNT.equalsIgnoreCase(tableName) && !columnList.contains(ConstantUtil.DEMAND_INT_SID)) {
                    columnList.set(columnList.indexOf(ConstantUtil.ADJ_DEMAND_FORECAST_INT), ConstantUtil.DEMAND_INT_SID);
                }
            } else {

                if (columnList.contains(ConstantUtil.FORECAST_VER)) {
                    columnList.remove(ConstantUtil.FORECAST_VER);
                }
                if (ConstantUtil.PROJECTION.equalsIgnoreCase(forecastTypeValue)) {
                    if (!headerList.contains(ConstantUtil.FORECASTING_NAME)) {
                        headerList.add(1, ConstantUtil.FORECASTING_NAME);
                    }
                    if (!headerList.contains(ConstantUtil.FORECAST_SPACE_VERSION)) {
                        headerList.add(NumericConstants.TWO, ConstantUtil.FORECAST_SPACE_VERSION);
                    }
                    if (!columnList.contains(ConstantUtil.FORECAST_NAME)) {
                        columnList.add(1, ConstantUtil.FORECAST_NAME);
                    }
                    if (!columnList.contains(ConstantUtil.FORECASTVERSION)) {
                        columnList.add(NumericConstants.TWO, ConstantUtil.FORECASTVERSION);
                    }
                } else {
                    if (headerList.contains(ConstantUtil.FORECASTING_NAME)) {
                        headerList.remove(ConstantUtil.FORECASTING_NAME);
                    }
                    if (headerList.contains(ConstantUtil.FORECAST_SPACE_VERSION)) {
                        headerList.remove(ConstantUtil.FORECAST_SPACE_VERSION);
                    }
                    if (columnList.contains(ConstantUtil.FORECAST_NAME)) {
                        columnList.remove(ConstantUtil.FORECAST_NAME);
                    }
                    if (columnList.contains(ConstantUtil.FORECASTVERSION)) {
                        columnList.remove(ConstantUtil.FORECASTVERSION);
                    }
                }

                if (!headerList.contains(ConstantUtil.UNCAPTURED_UNIT)) {
                    headerList.add(NumericConstants.THIRTEEN, ConstantUtil.UNCAPTURED_UNIT);
                }
                if (!headerList.contains(ConstantUtil.UNCAPTURED_UNIT_RATIO)) {
                    headerList.add(NumericConstants.FOURTEEN, ConstantUtil.UNCAPTURED_UNIT_RATIO);
                }
                if (!headerList.contains(ConstantUtil.INVENTORY_UNITS_CHANGE)) {
                    headerList.add(NumericConstants.SEVENTEEN, ConstantUtil.INVENTORY_UNITS_CHANGE);
                }

                if (!columnList.contains(ConstantUtil.UN_CAPTURED_UNITS)) {
                    columnList.add(NumericConstants.THIRTEEN, ConstantUtil.UN_CAPTURED_UNITS);
                }
                if (!columnList.contains(ConstantUtil.UN_CAPTURED_UNITS_RATIO)) {
                    columnList.add(NumericConstants.FOURTEEN, ConstantUtil.UN_CAPTURED_UNITS_RATIO);
                }
                if (!columnList.contains(ConstantUtil.INVENTORYUNITCHANGE)) {
                    columnList.add(NumericConstants.SEVENTEEN, ConstantUtil.INVENTORYUNITCHANGE);
                }

                if (!(columnList.contains("year")
                        || columnList.contains(ConstantUtil.MONTH) || columnList.contains(ConstantUtil.TOTAL_ADJ_DEMAND_UNITS) || columnList.contains(ConstantUtil.TOTAL_ADJ_DEMAND_AMT))) {
                    columnList.set(columnList.indexOf(ConstantUtil.FORECAST_YEAR), "year");
                    columnList.set(columnList.indexOf(ConstantUtil.FORECAST_MONTH), ConstantUtil.MONTH);
                    columnList.set(columnList.indexOf(ConstantUtil.TOTAL_DEMAND_UNITS), ConstantUtil.TOTAL_ADJ_DEMAND_UNITS);
                    columnList.set(columnList.indexOf(ConstantUtil.TOTAL_DEMAND_AMT), ConstantUtil.TOTAL_ADJ_DEMAND_AMT);
                }
                if (ConstantUtil.INVALID_RECORD_COUNT.equalsIgnoreCase(tableName) && !columnList.contains(ConstantUtil.ADJ_DEMAND_FORECAST_INT)) {
                    columnList.set(columnList.indexOf(ConstantUtil.DEMAND_INT_SID), ConstantUtil.ADJ_DEMAND_FORECAST_INT);
                }
            }
            getTableColumns();
        }
    }

    protected void removeListInventory() {
        if (ConstantUtil.INVENTORYVIEW_TABLE.equalsIgnoreCase(tableName) || ConstantUtil.INVALID_INVENTORYVIEW_TABLE.equalsIgnoreCase(invalidTableName)) {

            if (ConstantUtil.SUMMARY.equals(primaryDto.getLevelDdlb())) {
                headerList.remove(ConstantUtil.COMPANYID);
                headerList.remove(ConstantUtil.COMPANYNAME);
                headerListInventoryRemove.add(ConstantUtil.COMPANYID);
                headerListInventoryRemove.add(ConstantUtil.COMPANYNAME);

                columnList.remove(ConstantUtil.COMPANY_ID);
                columnList.remove(ConstantUtil.COMPANY_NAME);

            } else {

                if (!(columnList.contains(ConstantUtil.COMPANY_ID) || columnList.contains(ConstantUtil.COMPANY_NAME))) {
                    columnList.add(NumericConstants.FOUR, ConstantUtil.COMPANY_ID);
                    columnList.add(NumericConstants.FIVE, ConstantUtil.COMPANY_NAME);

                }
                if (!(headerList.contains(ConstantUtil.COMPANYID) || headerList.contains(ConstantUtil.COMPANYNAME))) {
                    headerList.add(NumericConstants.FOUR, ConstantUtil.COMPANYID);
                    headerList.add(NumericConstants.FIVE, ConstantUtil.COMPANYNAME);
                    headerListInventoryRemove.remove(ConstantUtil.COMPANYID);
                    headerListInventoryRemove.remove(ConstantUtil.COMPANYNAME);

                }

            }
            if (ConstantUtil.PROJECTION.equals(primaryDto.getTypeDdlb())) {
                headerList.remove(ConstantUtil.UNITS_ON_HANDS);
                headerList.remove(ConstantUtil.AMOUNT_SPACE_ON_HAND);
                headerList.remove(ConstantUtil.QUANTITY_SPACE_ON_ORDER);
                headerList.remove(ConstantUtil.AMOUNT_SPACE_ON_ORDER);
                headerList.remove(ConstantUtil.QUANTITY_SPACE_RECEIVED);
                headerList.remove(ConstantUtil.AMOUNT_SPACE_RECEIVED);

                headerListInventoryRemove.add(ConstantUtil.UNITS_ON_HANDS);
                headerListInventoryRemove.add(ConstantUtil.AMOUNT_SPACE_ON_HAND);
                headerListInventoryRemove.add(ConstantUtil.QUANTITY_SPACE_ON_ORDER);
                headerListInventoryRemove.add(ConstantUtil.AMOUNT_SPACE_ON_ORDER);
                headerListInventoryRemove.add(ConstantUtil.QUANTITY_SPACE_RECEIVED);
                headerListInventoryRemove.add(ConstantUtil.AMOUNT_SPACE_RECEIVED);

                columnList.remove(ConstantUtil.UNITS_ON_HAND);
                columnList.remove(ConstantUtil.AMOUNT_ON_HAND);
                columnList.remove(ConstantUtil.QUANTITY_ON_ORDER);
                columnList.remove(ConstantUtil.AMOUNT_ON_ORDER);
                columnList.remove(ConstantUtil.QUANTITY_RECEIVED);
                columnList.remove(ConstantUtil.AMOUNT_RECEIVED);
                int index = headerList.indexOf("Amount Withdrawn");
                if (!(columnList.contains(ConstantUtil.PRICE) || columnList.contains(ConstantUtil.FORECAST_NAME)
                        || columnList.contains(ConstantUtil.FORECAST_VER) || columnList.contains(ConstantUtil.ORGANIZATION_KEY))) {
                    columnList.add(index + NumericConstants.ONE, ConstantUtil.PRICE);
                    columnList.add(columnList.indexOf("source") + NumericConstants.ONE, ConstantUtil.FORECAST_NAME);
                    columnList.add(columnList.indexOf("source") + NumericConstants.TWO, ConstantUtil.FORECAST_VER);

                    columnList.add(columnList.indexOf("country") + NumericConstants.ONE, ConstantUtil.ORGANIZATION_KEY);

                }
                if (!(headerList.contains(ConstantUtil.PRICE_DUP) || headerList.contains(ConstantUtil.FORECASTING_NAME)
                        || headerList.contains(ConstantUtil.FORECAST_SPACE_VER) || headerList.contains(ConstantUtil.ORGANIZATION_SPACE_KEY))) {
                    headerList.add(index + NumericConstants.ONE, ConstantUtil.PRICE_DUP);
                    headerList.add(headerList.indexOf("Source ID") + NumericConstants.ONE, ConstantUtil.FORECASTING_NAME);

                    headerList.add(headerList.indexOf("Source ID") + NumericConstants.TWO, ConstantUtil.FORECAST_SPACE_VER);
                    headerList.add(headerList.indexOf("Country") + NumericConstants.ONE, ConstantUtil.ORGANIZATION_SPACE_KEY);

                    headerListInventoryRemove.remove(ConstantUtil.PRICE_DUP);
                    headerListInventoryRemove.remove(ConstantUtil.FORECASTING_NAME);
                    headerListInventoryRemove.remove(ConstantUtil.FORECAST_SPACE_VER);
                    headerListInventoryRemove.remove(ConstantUtil.ORGANIZATION_SPACE_KEY);

                }

            } else if (ConstantUtil.ACTUALS.equals(primaryDto.getTypeDdlb())) {
                headerList.remove(ConstantUtil.PRICE_DUP);
                headerList.remove(ConstantUtil.FORECASTING_NAME);
                headerList.remove(ConstantUtil.FORECAST_SPACE_VER);
                headerList.remove(ConstantUtil.ORGANIZATION_SPACE_KEY);

                headerListInventoryRemove.add(ConstantUtil.PRICE_DUP);
                headerListInventoryRemove.add(ConstantUtil.FORECASTING_NAME);
                headerListInventoryRemove.add(ConstantUtil.FORECAST_SPACE_VER);
                headerListInventoryRemove.add(ConstantUtil.ORGANIZATION_SPACE_KEY);

                columnList.remove(ConstantUtil.PRICE);
                columnList.remove(ConstantUtil.FORECAST_NAME);
                columnList.remove(ConstantUtil.FORECAST_VER);
                columnList.remove(ConstantUtil.ORGANIZATION_KEY);
                int index = headerList.indexOf("Amount Withdrawn");
                if (!(columnList.contains(ConstantUtil.UNITS_ON_HAND) || columnList.contains(ConstantUtil.AMOUNT_ON_HAND)
                        || columnList.contains(ConstantUtil.QUANTITY_ON_ORDER) || columnList.contains(ConstantUtil.AMOUNT_ON_ORDER)
                        || columnList.contains(ConstantUtil.QUANTITY_RECEIVED) || columnList.contains(ConstantUtil.AMOUNT_RECEIVED))) {
                    columnList.add(index + NumericConstants.ONE, ConstantUtil.UNITS_ON_HAND);
                    columnList.add(index + NumericConstants.TWO, ConstantUtil.AMOUNT_ON_HAND);
                    columnList.add(index + NumericConstants.THREE, ConstantUtil.QUANTITY_ON_ORDER);
                    columnList.add(index + NumericConstants.FOUR, ConstantUtil.AMOUNT_ON_ORDER);
                    columnList.add(index + NumericConstants.FIVE, ConstantUtil.QUANTITY_RECEIVED);
                    columnList.add(index + NumericConstants.SIX, ConstantUtil.AMOUNT_RECEIVED);

                }
                if (!(headerList.contains(ConstantUtil.UNITS_ON_HANDS) || headerList.contains(ConstantUtil.AMOUNT_SPACE_ON_HAND)
                        || headerList.contains(ConstantUtil.QUANTITY_SPACE_ON_ORDER) || headerList.contains(ConstantUtil.AMOUNT_SPACE_ON_ORDER)
                        || headerList.contains(ConstantUtil.QUANTITY_SPACE_RECEIVED) || headerList.contains(ConstantUtil.AMOUNT_SPACE_RECEIVED))) {
                    headerList.add(index + NumericConstants.ONE, ConstantUtil.UNITS_ON_HANDS);
                    headerList.add(index + NumericConstants.TWO, ConstantUtil.AMOUNT_SPACE_ON_HAND);
                    headerList.add(index + NumericConstants.THREE, ConstantUtil.QUANTITY_SPACE_ON_ORDER);
                    headerList.add(index + NumericConstants.FOUR, ConstantUtil.AMOUNT_SPACE_ON_ORDER);
                    headerList.add(index + NumericConstants.FIVE, ConstantUtil.QUANTITY_SPACE_RECEIVED);
                    headerList.add(index + NumericConstants.SIX, ConstantUtil.AMOUNT_SPACE_RECEIVED);

                    headerListInventoryRemove.remove(ConstantUtil.UNITS_ON_HANDS);
                    headerListInventoryRemove.remove(ConstantUtil.AMOUNT_SPACE_ON_HAND);
                    headerListInventoryRemove.remove(ConstantUtil.QUANTITY_SPACE_ON_ORDER);
                    headerListInventoryRemove.remove(ConstantUtil.AMOUNT_SPACE_ON_ORDER);
                    headerListInventoryRemove.remove(ConstantUtil.QUANTITY_SPACE_RECEIVED);
                    headerListInventoryRemove.remove(ConstantUtil.AMOUNT_SPACE_RECEIVED);

                }
            }
            getTableColumns();
            AbstractComponentCreater.setHeaderList(headerListInventoryRemove);
        }
    }

    @UiHandler("generateBtn")
    public void generateBtnLogic(Button.ClickEvent event) {

        MessageBox.showPlain(Icon.QUESTION, ConstantUtil.CONFORMATION, "Would you like to resend these accrual records to the General Ledger?" + " ?", new MessageBoxListener() {
            /**
             * Called when a Button has been clicked .
             *
             */
            @SuppressWarnings("PMD")
            public void buttonClicked(final ButtonId buttonId) {
                if (buttonId.name().equals("YES")) {

                    try {

                        btnGenerateLogic();
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }

                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }

    protected void addPagination(VerticalLayout tableLayout) {
        if (tableLogic != null) {
            getResponsiveControls(tableLogic.createControls(), tableLayout);
        }
    }

    public boolean isLoadEnable() {
        return loadEnable;
    }

    public void setLoadEnable(boolean loadEnable) {
        this.loadEnable = loadEnable;
    }

    public void getResponsiveControls(HorizontalLayout tempLayout, VerticalLayout controlBar) {

        controlBar.setStyleName("responsivePagedTable");
        HorizontalLayout pageSize = (HorizontalLayout) tempLayout.getComponent(0);
        HorizontalLayout pageManagement = (HorizontalLayout) tempLayout.getComponent(NumericConstants.ONE);

        CssLayout cssLayout = new CssLayout();
        cssLayout.setSizeFull();
        cssLayout.addComponent(pageSize.getComponent(0));
        cssLayout.addComponent(pageSize.getComponent(0));
        for (int index = 0; index < NumericConstants.EIGHT; index++) {
            cssLayout.addComponent(pageManagement.getComponent(0));
        }
        controlBar.addComponent(cssLayout);

    }

    public static void createFileContent(Object[] visibleColumns, List searchList, PrintWriter printWriter) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        final SimpleDateFormat dateFormat = new SimpleDateFormat(ConstantUtil.MMDDYYYY);
        tableMap();
        for (Object value : searchList) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < visibleColumns.length; i++) {
                String methodStringValue;
                Object methodValue = getFieldValue(value, visibleColumns[i].toString());
                if (methodValue == null || "null".equals(methodValue)) {
                    methodValue = StringUtils.EMPTY;
                }
                if (methodValue != null && (methodValue.getClass().equals(Timestamp.class) || methodValue.getClass().equals(Date.class))) {
                    methodStringValue = dateFormat.format(methodValue);
                } else if (methodValue != null && StringUtils.isNotBlank(String.valueOf(methodValue)) && !"-Select One-".equals(String.valueOf(methodValue))) {
                    methodStringValue = String.valueOf(methodValue);
                } else {
                    methodStringValue = StringUtils.EMPTY;
                }

                if (visibleColumns.length == i) {
                    builder.append(QUOTE).append(methodStringValue).append(QUOTE);
                } else {
                    builder.append(QUOTE).append(methodStringValue).append(QUOTE).append(com.stpl.ifs.util.ExcelExportUtil.COMMA);
                }
            }
            printWriter.println(builder);
        }
    }

    private static Object getFieldValue(Object myDTO, String variable) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Object value = null;
        Object[] obj = (Object[]) myDTO;

        if (excelOrderMap.get(variable) == null || "null".equals(excelOrderMap.get(variable))) {
            if (variable.startsWith("current_Year")) {
                value = "0";
            } else {
                value = StringUtils.EMPTY;
            }
        } else {
            int i = excelOrderMap.get(variable);
            if (i == NumericConstants.TWENTY_FOUR || i == NumericConstants.TWENTY_FIVE) {
                final SimpleDateFormat dateFormat = new SimpleDateFormat(ConstantUtil.MMDDYYYY);
                String createdDate = dateFormat.format(obj[i]);
                return createdDate;
            } else if (i == NumericConstants.TWENTY_SIX) {
                String outboundStatus = "N";
                if (obj[i] != null) {
                    outboundStatus = (Boolean) obj[i] == true ? "Y" : "N";
                }
                return outboundStatus;

            } else if (obj[i] == null) {
                if (variable.startsWith("current_Year")) {
                    value = "0";
                }
            } else {
                value = String.valueOf(obj[i]);
            }

        }
        return value;
    }

    private static void tableMap() {
        int i = NumericConstants.TWENTY_SEVEN;
        excelOrderMap.put("arp_Id", NumericConstants.ONE);
        excelOrderMap.put("arp_Name", NumericConstants.TWO);
        excelOrderMap.put("company_Id", NumericConstants.FIVE);
        excelOrderMap.put("company_No", NumericConstants.SIX);
        excelOrderMap.put("company_Name", NumericConstants.SEVEN);
        excelOrderMap.put("item_Id", NumericConstants.EIGHT);
        excelOrderMap.put("item_No", NumericConstants.NINE);
        excelOrderMap.put("item_Name", NumericConstants.TEN);
        excelOrderMap.put("brand_Id", NumericConstants.ELEVEN);
        excelOrderMap.put("brand_Name", NumericConstants.TWELVE);
        excelOrderMap.put(ConstantUtil.ARP_ACCOUNT, NumericConstants.SIXTEEN);
        excelOrderMap.put("account_Type", NumericConstants.SEVENTEEN);
        excelOrderMap.put("category", NumericConstants.EIGHTEEN);
        excelOrderMap.put("type", NumericConstants.NINETEEN);
        excelOrderMap.put("program", NumericConstants.FOUR);
        excelOrderMap.put("current_Year_Jan", i + NumericConstants.ONE);
        excelOrderMap.put("current_Year_Feb", i + NumericConstants.TWO);
        excelOrderMap.put("current_Year_Mar", i + NumericConstants.THREE);
        excelOrderMap.put("current_Year_Apr", i + NumericConstants.FOUR);
        excelOrderMap.put("current_Year_May", i + NumericConstants.FIVE);
        excelOrderMap.put("current_Year_June", i + NumericConstants.SIX);
        excelOrderMap.put("current_Year_July", i + NumericConstants.SEVEN);
        excelOrderMap.put("current_Year_Aug", i + NumericConstants.EIGHT);
        excelOrderMap.put("current_Year_Sep", i + NumericConstants.NINE);
        excelOrderMap.put("current_Year_Oct", i + NumericConstants.TEN);
        excelOrderMap.put("current_Year_Nov", i + NumericConstants.ELEVEN);
        excelOrderMap.put("Current_Year_Dec", i + NumericConstants.TWELVE);

        excelOrderMap.put("current_Year_1_Jan", i + NumericConstants.THIRTEEN);
        excelOrderMap.put("current_Year_1_Feb", i + NumericConstants.FOURTEEN);
        excelOrderMap.put("current_Year_1_Mar", i + NumericConstants.FIFTEEN);
        excelOrderMap.put("current_Year_1_Apr", i + NumericConstants.SIXTEEN);
        excelOrderMap.put("current_Year_1_May", i + NumericConstants.SEVENTEEN);
        excelOrderMap.put("current_Year_1_June", i + NumericConstants.EIGHTEEN);
        excelOrderMap.put("current_Year_1_July", i + NumericConstants.NINETEEN);
        excelOrderMap.put("current_Year_1_Aug", i + NumericConstants.TWENTY);
        excelOrderMap.put("current_Year_1_Sep", i + NumericConstants.TWENTY_ONE);
        excelOrderMap.put("current_Year_1_Oct", i + NumericConstants.TWENTY_TWO);
        excelOrderMap.put("current_Year_1_Nov", i + NumericConstants.TWENTY_THREE);
        excelOrderMap.put("current_Year_1_Dec", i + NumericConstants.TWENTY_FOUR);

        excelOrderMap.put("current_Year_2_Jan", i + NumericConstants.TWENTY_FIVE);
        excelOrderMap.put("current_Year_2_Feb", i + NumericConstants.TWENTY_SIX);
        excelOrderMap.put("current_Year_2_Mar", i + NumericConstants.TWENTY_SEVEN);
        excelOrderMap.put("current_Year_2_Apr", i + NumericConstants.TWENTY_EIGHT);
        excelOrderMap.put("current_Year_2_May", i + NumericConstants.TWENTY_NINE);
        excelOrderMap.put("current_Year_2_June", i + NumericConstants.THIRTY);
        excelOrderMap.put("current_Year_2_July", i + NumericConstants.THIRTY_ONE);
        excelOrderMap.put("current_Year_2_Aug", i + NumericConstants.THIRTY_TWO);
        excelOrderMap.put("current_Year_2_Sep", i + NumericConstants.THIRTY_THREE);
        excelOrderMap.put("current_Year_2_Oct", i + NumericConstants.THIRTY_FOUR);
        excelOrderMap.put("current_Year_2_Nov", i + NumericConstants.THIRTY_FIVE);
        excelOrderMap.put("current_Year_2_Dec", i + NumericConstants.THIRTY_SIX);

        excelOrderMap.put("arp_Creation_Date", NumericConstants.TWENTY_FOUR);
        excelOrderMap.put("arp_Approval_Date", NumericConstants.TWENTY_FIVE);
        excelOrderMap.put("outbound_Status", NumericConstants.TWENTY_SIX);
        excelOrderMap.put("original_Batch_ID", NumericConstants.TWENTY_SEVEN);

    }

    public void privateLookupCloseListener() {
        privateLookUp.addCloseListener(closeListener);
    }

    public void publicLookupCloseListener() {
        publicLookUp.addCloseListener(closeListener);
    }

    Window.CloseListener closeListener = new Window.CloseListener() {

        @Override
        public void windowClose(Window.CloseEvent e) {
            try {
                PrivateOrPublicView lookUP = (PrivateOrPublicView) e.getWindow();
                if (lookUP.isSelected()) {
                    if (lookUP.getCaption().equalsIgnoreCase(ConstantUtil.PRIVATE)) {
                    } else if (lookUP.getCaption().equalsIgnoreCase(ConstantUtil.PUBLIC)) {
                    }
                    setViewDetails(lookUP.getAdjustmentDetailsDTO());
                }
            } catch (Exception ex) {
                LOGGER.debug(ex);
            }
        }
    };

    private void setViewDetails(AdjustmentDetailsDTO adjustmentDetailsDTO) {
        try {
            this.adjustmentDetailsDTO = adjustmentDetailsDTO;
            this.adjustmentDetailsDTO.setMode(true);
            unBindValues(searchFieldVerticalLayout2, adjustmentDetailsDTO);
            unBindValues(searchFieldVerticalLayout, adjustmentDetailsDTO);
        } catch (ParseException ex) {
            LOGGER.debug(ex);
        }
    }

    /**
     *
     * bindValues
     *
     */
    public Map<String, Object> bindValues(AbstractLayout layout, String[] stringArray) {
        Iterator<Component> componentIterator = layout.getComponentIterator();
        Map<String, Object> map = new HashMap<>();
        while (componentIterator.hasNext()) {
            Component component = componentIterator.next();
            if (component instanceof TextField) {
                String propId = ((TextField) component).getData() != null ? ((TextField) component).getData().toString() : StringUtils.EMPTY;
                String value = ((TextField) component).getValue();
                if (Arrays.toString(stringArray).contains(propId)) {
                    map.put(propId, value);
                }
            } else if (component instanceof PopupDateField) {
                String propId = ((PopupDateField) component).getData() != null ? ((PopupDateField) component).getData().toString() : StringUtils.EMPTY;
                Date value = ((PopupDateField) component).getValue();
                if (Arrays.toString(stringArray).contains(propId)) {
                    map.put(propId, value);
                }
            } else if (component instanceof ComboBox) {
                String propId = ((ComboBox) component).getData() != null ? ((ComboBox) component).getData().toString() : StringUtils.EMPTY;
                Object value = ((ComboBox) component).getValue();
                if ("DEDUCTION_LEVELS".equals(propId)) {
                    propId = ConstantUtil.DEDUCTION_LEVEL;
                }
                if (Arrays.toString(stringArray).contains(propId)) {
                    map.put(propId, value);
                } else {
                }
            } else if (component instanceof AbstractLayout) {
                bindValues((AbstractLayout) component, stringArray);
            } else if (component instanceof CustomMenuBar) {
                CustomMenuBar tempCustomMenuBar = (CustomMenuBar) component;
                if (tempCustomMenuBar.getData() != null) {
                    String propId = tempCustomMenuBar.getData().toString();
                    if (ConstantUtil.DEDUCTION_VALUE.equals(propId) && deductionValueItem != null && deductionValueItem.getChildren() != null) {
                        StringBuilder builder = new StringBuilder("");
                        for (CustomMenuBar.CustomMenuItem menuItem : deductionValueItem.getChildren()) {
                            if (menuItem.isChecked()) {
                                if (!builder.toString().isEmpty()) {
                                    builder.append(CommonUtils.TILD_SEPARATOR);
                                }
                                builder.append(menuItem.getText());
                            }
                        }
                        map.put(propId, builder.toString());
                    }
                }
            }
        }
        //redemption to period
        map.put("redemptionPeriodToDate", StringUtils.EMPTY.equals(redemptionPeriodToDate.getValue()) ? null : redemptionPeriodToDate.getValue());

        adjustmentDetailsDTO.setValueMap(map);
        return map;
    }

    private void configDeductionLevel() {
        Label currentLabel = null;
        ComboBox deductionLevelComponent = null;
        CustomMenuBar customMenuBarComponent = null;
        Iterator<Component> components = gridLayout2.iterator(); //Changed from cssLayout->gridLayout2 for GAL-6070
        for (int i = 0; i < gridLayout2.getComponentCount(); i++) {// Changed from cssLayout->gridLayout2 for GAL-6070
            Component dlb = components.next();
            if (dlb instanceof Label) {
                currentLabel = (Label) dlb;
                if (currentLabel.getValue().trim().equalsIgnoreCase(ConstantUtil.DEDUCTIONLEVELS) && deductionLevelComponent == null) {
                    deductionLevelComponent = (ComboBox) components.next();
                } else if (currentLabel.getValue().trim().equalsIgnoreCase(ConstantUtil.DEDUCTIONVALUE) && customMenuBarComponent == null) {
                    customMenuBarComponent = (CustomMenuBar) components.next();
                }
                if (deductionLevelComponent != null && customMenuBarComponent != null) {
                    break;
                }
            }
        }
        if (deductionLevelComponent != null && customMenuBarComponent != null) {
            deductionValueItem = CommonUIUtils.loadDeductionsDdlb(deductionLevelComponent, customMenuBarComponent);
        }
    }

    public void unBindValues(AbstractLayout layout, AdjustmentDetailsDTO adjustmentDetailsDTO) throws ParseException {
        Iterator<Component> componentIterator = layout.getComponentIterator();
        while (componentIterator.hasNext()) {
            Component component = componentIterator.next();
            if (component instanceof TextField) {
                TextField tempTextField = (TextField) component;
                if (tempTextField.getData() != null) {
                    String propId = tempTextField.getData().toString();
                    if (ConstantUtil.WORKFLOW_ID.equals(propId)) {
                        tempTextField.setValue(adjustmentDetailsDTO.getWorkflowId());
                    } else if (ConstantUtil.REDEMPTION_PERIOD.equals(propId)) {
                        SimpleDateFormat parser = new SimpleDateFormat(CommonUtils.YYYYMMDDHHMMSSS);
                        SimpleDateFormat format = new SimpleDateFormat(CommonUtils.MMDDYYYY);
                        Date redemptionDate = parser.parse(adjustmentDetailsDTO.getRedemptionPeriod());
                        tempTextField.setValue(format.format(redemptionDate));
                    } else if ("workflowName".equals(propId)) {
                        tempTextField.setValue(adjustmentDetailsDTO.getWorkflowName());
                    } else if (ConstantUtil.COMPANY_NO.equals(propId)) {
                        tempTextField.setValue(adjustmentDetailsDTO.getCustomerNo());
                    } else if (ConstantUtil.ITEMNO.equals(propId)) {
                        tempTextField.setValue(adjustmentDetailsDTO.getItemNo());
                    } else if (ConstantUtil.COMPANY_NAME.equals(propId)) {
                        tempTextField.setValue(adjustmentDetailsDTO.getCustomerName());
                    } else if (ConstantUtil.ITEM_NAME.equals(propId)) {
                        tempTextField.setValue(adjustmentDetailsDTO.getItemName());
                    } else if ("originalBatchId".equals(propId)) {
                        tempTextField.setValue(adjustmentDetailsDTO.getBatchId());
                    } else if ("brandName".equals(propId)) {
                        tempTextField.setValue(adjustmentDetailsDTO.getBrandName());
                    } else if (ConstantUtil.ARP_ACCOUNT.equals(propId)) {
                        tempTextField.setValue(adjustmentDetailsDTO.getAccount());
                    }
                }
            } else if (component instanceof PopupDateField) {
                PopupDateField tempPopupDateField = (PopupDateField) component;
                if (tempPopupDateField.getData() != null) {
                    String propId = tempPopupDateField.getData().toString();
                    if (ConstantUtil.GL_DATE.equals(propId)) {
                        tempPopupDateField.setValue(adjustmentDetailsDTO.getGlDate());
                    } else if (ConstantUtil.CREATED_DATE.equals(propId)) {
                        tempPopupDateField.setValue(adjustmentDetailsDTO.getCreationDate());
                    }
                }
            } else if (component instanceof ComboBox) {
                ComboBox tempComboBox = (ComboBox) component;
                if (tempComboBox.getData() != null) {
                    String propId = tempComboBox.getData().toString();
                    if ("glCompanyName".equals(propId)) {
                        tempComboBox.setValue(adjustmentDetailsDTO.getCompanyMasterSid());
                    } else if ("businessUnitId".equals(propId)) {
                        tempComboBox.setValue(adjustmentDetailsDTO.getBuCompanyMasterSid());
                    } else if (ConstantUtil.ACCOUNT_TYPE.equals(propId)) {
                        tempComboBox.setValue(adjustmentDetailsDTO.getAccountType());
                    } else if (ConstantUtil.ACC_CATEGORY.equals(propId)) {
                        tempComboBox.setValue(adjustmentDetailsDTO.getAccountCategory());
                    } else if (ConstantUtil.ADJUSTMENTS_TYPE.equals(propId)) {
                        tempComboBox.setValue(adjustmentDetailsDTO.getAdjustmentType());
                    } else if (ConstantUtil.TRANSACTOIN_LEVEL.equals(propId)) {
                        tempComboBox.setValue(adjustmentDetailsDTO.getTransactionLevelId());
                    } else if ("DEDUCTION_LEVELS".equals(propId) || ConstantUtil.DEDUCTION_LEVEL.equals(propId)) {
                        tempComboBox.setValue(adjustmentDetailsDTO.getDeductionLevel());
                    } else if (ConstantUtil.POSTING_INDICATOR.equals(propId)) {
                        tempComboBox.setValue(adjustmentDetailsDTO.getPostingIndicator());
                    } else if (ConstantUtil.ADJ_LEVEL.equals(propId)) {
                        tempComboBox.setValue(adjustmentDetailsDTO.getAdjustmentLevel());
                    }
                }
            } else if (component instanceof CustomMenuBar) {
                CustomMenuBar tempCustomMenuBar = (CustomMenuBar) component;
                if (tempCustomMenuBar.getData() != null) {
                    String propId = tempCustomMenuBar.getData().toString();
                    if (ConstantUtil.DEDUCTION_VALUE.equals(propId) && deductionValueItem != null && deductionValueItem.getChildren() != null && adjustmentDetailsDTO.getDeductionValue() != null) {
                        String[] splitted = adjustmentDetailsDTO.getDeductionValue().split(CommonUtils.TILD_SEPARATOR);
                        if (splitted != null) {
                            List<String> splitList = Arrays.asList(splitted);
                            for (CustomMenuBar.CustomMenuItem menuItem : deductionValueItem.getChildren()) {
                                menuItem.setChecked(false);
                                if (splitList.contains(menuItem.getText())) {
                                    menuItem.setChecked(true);
                                }
                            }
                        }
                    }
                }
            } else if (component instanceof AbstractLayout) {
                unBindValues((AbstractLayout) component, adjustmentDetailsDTO);
            }
        }

        redemptionPeriodToDate.setValue(adjustmentDetailsDTO.getRedemptionPeriodEndDate());

    }

    private void loadAdjustmentLevel() {

        try {
            Label adDDLB = null;
            ComboBox alablel = null;
            Iterator<Component> components = gridLayout2.iterator(); //Changed from cssLayout->gridLayout2 for GAL-6070
            for (int i = 0; i < gridLayout2.getComponentCount(); i++) {// Changed from cssLayout->gridLayout2 for GAL-6070
                Component dlb = components.next();
                if (dlb instanceof Label) {
                    adDDLB = (Label) dlb;
                    if (adDDLB.getValue().equalsIgnoreCase(ConstantUtil.ADJUSTMENT_LEVEL)) {
                        alablel = (ComboBox) components.next();
                        break;
                    }
                }
            }
            if (alablel != null) {

                alablel.removeAllItems();
                final List<HelperDTO> list = logic.getHelperResults(ConstantUtil.TRANSCATION_LEVEL_RESERVE);
                for (int i = 0; i < list.size(); i++) {
                    final HelperDTO helperDTO = list.get(i);
                    alablel.addItem(helperDTO.getId());
                    alablel.setItemCaption(helperDTO.getId(), helperDTO.getDescription());
                }
                alablel.setNullSelectionAllowed(true);
                alablel.setNullSelectionItemId(0);
                alablel.select(0);
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Used to customize the helperDto comboBox
     *
     * @param compositionRoot
     */
    private void customizeHelperDtoDdlb(ComboBox comboBox) {
        comboBox.setValidationVisible(true);
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(true);
        comboBox.setNullSelectionItemId(0);
        comboBox.addItem(0);
        comboBox.setItemCaption(0, GlobalConstants.getSelectOne());
        comboBox.select(null);
        comboBox.setImmediate(true);
    }

    /**
     * Used to load the DDld with custom Query
     *
     * @param loadAdjustmentType
     * @param compositionRoot
     */
    private void loadAndConfigureDDlb(String loadAdjustmentType, ComboBox comboBox) {
        List<Object[]> list = QueryDataUtils.getAppData(Collections.EMPTY_LIST, loadAdjustmentType, StringUtils.EMPTY);
        for (int i = 0; i < list.size(); i++) {
            Object[] helper = (Object[]) list.get(i);
            comboBox.addItem(Integer.valueOf(helper[0].toString()));
            comboBox.setItemCaption(Integer.valueOf(helper[0].toString()), String.valueOf(helper[NumericConstants.TWO]));
        }
    }

    private void arpColumnFormat() {
        table.setConverter("current_Year_Jan", new DoubleToStringConverter());
        table.setConverter("current_Year_Feb", new DoubleToStringConverter());
        table.setConverter("current_Year_Mar", new DoubleToStringConverter());
        table.setConverter("current_Year_Apr", new DoubleToStringConverter());
        table.setConverter("current_Year_May", new DoubleToStringConverter());
        table.setConverter("current_Year_June", new DoubleToStringConverter());
        table.setConverter("current_Year_July", new DoubleToStringConverter());
        table.setConverter("current_Year_Aug", new DoubleToStringConverter());
        table.setConverter("current_Year_Sep", new DoubleToStringConverter());
        table.setConverter("current_Year_Oct", new DoubleToStringConverter());
        table.setConverter("current_Year_Nov", new DoubleToStringConverter());
        table.setConverter("current_Year_Dec", new DoubleToStringConverter());

        table.setConverter("current_Year_1_Jan", new DoubleToStringConverter());
        table.setConverter("current_Year_1_Feb", new DoubleToStringConverter());
        table.setConverter("current_Year_1_Mar", new DoubleToStringConverter());
        table.setConverter("current_Year_1_Apr", new DoubleToStringConverter());
        table.setConverter("current_Year_1_May", new DoubleToStringConverter());
        table.setConverter("current_Year_1_June", new DoubleToStringConverter());
        table.setConverter("current_Year_1_July", new DoubleToStringConverter());
        table.setConverter("current_Year_1_Aug", new DoubleToStringConverter());
        table.setConverter("current_Year_1_Sep", new DoubleToStringConverter());
        table.setConverter("current_Year_1_Oct", new DoubleToStringConverter());
        table.setConverter("current_Year_1_Nov", new DoubleToStringConverter());
        table.setConverter("current_Year_1_Dec", new DoubleToStringConverter());

        table.setConverter("current_Year_2_Jan", new DoubleToStringConverter());
        table.setConverter("current_Year_2_Feb", new DoubleToStringConverter());
        table.setConverter("current_Year_2_Mar", new DoubleToStringConverter());
        table.setConverter("current_Year_2_Apr", new DoubleToStringConverter());
        table.setConverter("current_Year_2_May", new DoubleToStringConverter());
        table.setConverter("current_Year_2_June", new DoubleToStringConverter());
        table.setConverter("current_Year_2_July", new DoubleToStringConverter());
        table.setConverter("current_Year_2_Aug", new DoubleToStringConverter());
        table.setConverter("current_Year_2_Sep", new DoubleToStringConverter());
        table.setConverter("current_Year_2_Oct", new DoubleToStringConverter());
        table.setConverter("current_Year_2_Nov", new DoubleToStringConverter());
        table.setConverter("current_Year_2_Dec", new DoubleToStringConverter());
    }

}
