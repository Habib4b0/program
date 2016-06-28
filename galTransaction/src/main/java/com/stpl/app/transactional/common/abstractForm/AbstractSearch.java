package com.stpl.app.transactional.common.abstractForm;

import com.stpl.app.security.impl.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.transactional.common.dto.AccrualdetailsDTO;
import com.stpl.app.transactional.common.dto.DetailsDTO;
import com.stpl.app.transactional.common.dto.FilterGenerator;
import com.stpl.app.transactional.common.dto.ReturnsDTO;
import com.stpl.app.transactional.common.logic.TransactionalSearchLogic;
import com.stpl.app.transactional.common.logic.CommonLogic;
import com.stpl.app.transactional.common.logic.SearchLogic;
import com.stpl.app.transactional.common.util.SearchCriteria;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.util.AbstractNotificationUtils;
import com.stpl.app.util.ConstantUtil;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.app.util.ExcelExportUtil;
import com.stpl.app.util.HelperDTO;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.TableResultCustom;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
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
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyBeanItemContainer;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author sooriya.lakshmanan
 * @param <BEANTYPE>
 */
public abstract class AbstractSearch extends CustomComponent {

    List<List> searchList = new ArrayList<List>();
    List<String> headerList = new ArrayList<String>();
    Set<String> headerListInventoryRemove = new HashSet<String>();
    List<Object> columnList = new ArrayList<Object>();
    List<DetailsDTO> moduleDetailsValue = null;
    List<DetailsDTO> primaryDetailsValue = new ArrayList<DetailsDTO>();
    //protected LazyBeanItemContainer searchResultbeans;
    protected Object[] tableColumnArr;
    protected Object[] excelVisibleColumnArr;
    protected String[] tableHeaderArr;
    @UiField("mainLayout")
    VerticalLayout mainLayout;
    @UiField("cssLayout")
    protected CssLayout cssLayout;
    @UiField("buttonLayout")
    private HorizontalLayout buttonLayout;
//    @UiField("filterTable")
//    protected ExtFilterTable table;
    @UiField("excelBtn")
    private Button excelExport;
    @UiField("removeBtn")
    private Button removeBtn;
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
    final ThemeResource excelImage = new ThemeResource("../../icons/excel.png");
    protected Map<Object, Object> searchValues = new HashMap<Object, Object>();
    protected Map<Object, Object> tableValues = new HashMap<Object, Object>();

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
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(AbstractSearch.class);
    /**
     * The error msg.
     */
    @UiField("errorMsg")
    private ErrorLabel errorMsg;
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
    public ExtPagedTable table = new ExtPagedTable(tableLogic);
    List<DetailsDTO> primaryValueList = new ArrayList<>();
    String className = StringUtils.EMPTY;
    public boolean loadEnable = false;

    /**
     * Constructor
     *
     * @param moduleDetails
     * @param primaryValueList
     * @throws Exception
     */
    public AbstractSearch(final List<DetailsDTO> moduleDetails, final List<DetailsDTO> primaryValueList) throws Exception {
        LOGGER.info("Inside Abstarct Search");
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
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/ui/common/searchform.xml"), this));
        init();
        LOGGER.info("End of Abstarct Search");
    }

    private void init() {
        try {
            selectedInvalidModule = tableName;
            tableLayout.addComponent(table);

            propertyId();
            getDynamicComponent();
            getTableColumns();
            addToTable();
            //    configureTable();      
            exportButton();
            removeBtn();
            addPagination(tableLayout);
            tableLayout.setSpacing(true);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    private List<List> getDynamicComponent() throws Exception {
        if (tableName.equals(ConstantUtil.ACCURAL_MASTER) ){
//                || ConstantUtil.INVALID_ACCURAL_INBOUND.equals(invalidTableName)
               
            generateBtn.setVisible(true);
        } else {
            generateBtn.setVisible(false);
        }
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
            if (((((AppPermission) fieldForecastSalesHM.get(moduleProperty.getPropertyName())) == null) ? false
                    : ((AppPermission) fieldForecastSalesHM.get(moduleProperty.getPropertyName())).isSearchFlag()) || (ConstantUtil.Button.equals(moduleProperty.getCategoryName()))) {

                if (ConstantUtil.COLUMN.equals(moduleProperty.getCategoryName())) {
                    headerList.add(moduleProperty.getDisplayName());
                    columnList.add(moduleProperty.getPropertyName());
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
            headList.add("Demand Type");
            headList.add("Uncaptured Units");
            headList.add("Uncaptured Units Ratio");
            headList.add("Inventory Unit Change");
            List<String> columlist = new ArrayList<String>();
            columlist.add("forecastName");
            columlist.add("forecastVer");
            columlist.add("uncapturedUnits");
            columlist.add("uncapturedUnitsRatio");
            columlist.add("inventoryUnitChange");
            columlist.add("isForecast");
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
                headList.add("Company ID");
                headList.add("Company Name");

                columlist.add("companyId");
                columlist.add("companyName");
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
                headList.add("Price");
                headList.add(ConstantUtil.FORECASTING_NAME);
                headList.add(ConstantUtil.FORECAST_SPACE_VER);
                headList.add(ConstantUtil.ORGANIZATION_SPACE_KEY);

                columlist.add("price");
                columlist.add("forecastName");
                columlist.add("forecastVer");
                columlist.add("organizationKey");
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

    private Component componentCreater(final DetailsDTO moduleProperty) throws Exception {
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

                            addValidator(new StringLengthValidator(moduleProperty.getDisplayName() + " " + message, Integer.valueOf(strArr[0]), Integer.valueOf(strArr[1]), Boolean.valueOf(strArr[2])));
                        }
                        if (validationValue.length > 0) {
                            String regexvalue = getMessage(validationValue[1]);
                            message = getMessage(validationValue[1] + "msg");
                            if (regexvalue != null) {
                                addValidator(new RegexpValidator(regexvalue, moduleProperty.getDisplayName() + " " + message));
                            }
                        }
                        setImmediate(true);
                        setValidationVisible(true);
                    }
                        }
            };
        } else if (ConstantUtil.PopupDateField.equals(moduleProperty.getCategoryName())) {
            return new PopupDateField() {
                {
                    setData(moduleProperty.getPropertyName());
                    if (moduleProperty.getPropertyName().contains("accrualPeriod")) {
                        setStyleName("dateFieldCenter");
                        addStyleName("datefieldcentered");
                    }
                    setDateFormat("MM/dd/yyyy");
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
                    }
                     else if ((tableName.equals(ConstantUtil.ACCURAL_MASTER) || invalidTableName.equals(ConstantUtil.INVALID_ACCURAL_INBOUND)) 
                             && moduleProperty.getPropertyName().equalsIgnoreCase(ConstantUtil.ACCRUAL_TYPE)) {
                        setImmediate(true);
                        addItem(ConstantUtil.SELECT_ONE);
                        if(tableName.equals(ConstantUtil.ACCURAL_MASTER)) {
                            addItem("Base");
                            addItem("Fixed Dollar");
                            addItem("Recon");
                        }
                        addItem("Other");
                        setNullSelectionAllowed(true);
                        setNullSelectionItemId(ConstantUtil.SELECT_ONE);
                        select(ConstantUtil.SELECT_ONE);
                        setData(moduleProperty.getPropertyName());
                        addValueChangeListener(new ValueChangeListener() {

                            @Override
                            public void valueChange(Property.ValueChangeEvent event) {

                                if (!"Other".equals(String.valueOf(event.getProperty().getValue()))) {
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
                        for (String searchList1 : list) {
                            addItem(searchList1);
                        }
                        select(list.get(0));
                        demandTypeValue = list.get(0);
                        if (moduleProperty.getPropertyName().equalsIgnoreCase(ConstantUtil.TYPE)) {
                            setData("isForecast");
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
                                        LOGGER.error(ex.getMessage());
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

                    } else {
                        if (tableName.equalsIgnoreCase(ConstantUtil.INVALID_RECORD_COUNT)) {
                            primaryDetailsValue.addAll(moduleDetailsValue);
                            if ("table".equalsIgnoreCase(moduleProperty.getPropertyName())) {
                                setImmediate(true);
                                List table = logic.getTableName();
                                for (final Object name : table) {
                                    addItem(name);
                                }
                                setData(moduleProperty.getPropertyName());
                            }
                            setNullSelectionAllowed(true);
                            setNullSelectionItemId(ConstantUtil.SELECT_ONE);
                            select(ConstantUtil.SELECT_ONE);
                            addValueChangeListener(new ValueChangeListener() {

                                @Override
                                public void valueChange(Property.ValueChangeEvent event) {
                                    try {
                                        if ("table".equalsIgnoreCase(moduleProperty.getPropertyName())) {
                                            tableValue = ConstantUtil.INVENTORY_WITHDRAWAL_SUMMARY.equals(String.valueOf(event.getProperty().getValue()).replaceAll("\\s", StringUtils.EMPTY)) ? ConstantUtil.INVENTORY : String.valueOf(event.getProperty().getValue()).replaceAll("\\s", StringUtils.EMPTY);
                                            newList = new ArrayList();
                                            if (tableValue.equals("ProjectedGTS–CustomerLevel")) {
                                                tableValue = ConstantUtil.CUSTOMERSALES;
                                            } else if (tableValue.equals("ActualGTS–CustomerLevel")) {
                                                tableValue = ConstantUtil.ACTUALGTSCUSTOMERSALES;
                                            }else if(tableValue.equals("AccrualInboundDetails")){
                                                 tableValue = ConstantUtil.ACCRUAL_DETAILS;
                                            }
                                            EXCEL_NAME=tableValue;
                                            if (ConstantUtil.DEMAND.equals(tableValue)) {
                                                invalidDemand = true;

                                            }
                                            getComponentsForInvalidRecordCount();
                                            if(!"null".equals(tableValue)){
                                            getTableForInvalidRecordCount();
                                            }
                                            propertyId();
                                                }
                                        VaadinSession.getCurrent().setAttribute(ConstantUtil.VIEW_SID_NAME, viewNameSid);
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                        LOGGER.error(ex.getMessage());
                                    }
                                }
                            });
                        }
                        String listType = (tableName.equals(ConstantUtil.ACCURAL_MASTER) || (ConstantUtil.INVALID_ACCURAL_INBOUND).equals(invalidTableName)) 
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
                           listType=ConstantUtil.RS_CATEGORY;
                        } else if (moduleProperty.getPropertyName().equalsIgnoreCase(ConstantUtil.DEDUCTION_TYPE)) {
                           listType= ConstantUtil.RS_TYPE;
                        } else if (moduleProperty.getPropertyName().equalsIgnoreCase(ConstantUtil.DEDUCTION_PROGRAM_TYPE)) {
                           listType= ConstantUtil.REBATE_PROGRAM_TYPE;
                        }
                    }
                        final List<HelperDTO> helperList = logic.getHelperResults(listType);
                        for (int i = 0; i < helperList.size(); i++) {
                            final HelperDTO helperDTO = helperList.get(i);
                            if (!"businessUnit_accrual".equals(listType)) {
                                addItem(helperDTO.getDescription());
                                setNullSelectionAllowed(true);
                                setNullSelectionItemId(ConstantUtil.SELECT_ONE);
                                select(ConstantUtil.SELECT_ONE);
                            } else {
                                addItem(helperDTO.getIdentifier());
                                setItemCaption(helperDTO.getIdentifier(), helperDTO.getDescription());
                                setNullSelectionAllowed(true);
                                setNullSelectionItemId(0);
                                select(0);
                            }
                        }
                        setImmediate(true);
                        if (!moduleProperty.getPropertyName().equalsIgnoreCase(ConstantUtil.TYPE) && !moduleProperty.getPropertyName().equalsIgnoreCase(ConstantUtil.LEVEL)  ) {
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
                                    if (size >= 2 && forecastTypeValue.equalsIgnoreCase(helperList.get(1) == null ? StringUtils.EMPTY : helperList.get(1).getDescription())) {
                                        getIterativeComponents1(cssLayout, false, moduleProperty.getPropertyName());
                                       
                                    } else if (size >= 3 && forecastTypeValue.equalsIgnoreCase(helperList.get(2) == null ? StringUtils.EMPTY : helperList.get(2).getDescription())) {
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

                                    LOGGER.error(ex.getMessage());
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
            } else {
                cssLayout.addComponent(labelComponent);
                cssLayout.addComponent(fieldComponent);
            }
        }
    }

    protected void getIterativeComponents(AbstractLayout layout, List<Component> compList) {
        Iterator<Component> componentIterator = layout.getComponentIterator();
        while (componentIterator.hasNext()) {
            Component component = componentIterator.next();
            if (component instanceof TextField || component instanceof PopupDateField || component instanceof ComboBox) {
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
                if (!name.equalsIgnoreCase(ConstantUtil.LEVEL) && !name.equalsIgnoreCase("type")) {
                    if (component instanceof TextField) {
                        if (((TextField) component).getData().equals(ConstantUtil.FORECAST_NAME)) {

                            ((TextField) component).setEnabled(isEnable);
                        }
                        if (((TextField) component).getData().equals(ConstantUtil.FORECAST_VER)) {
                            ((TextField) component).setEnabled(isEnable);
                        }
                    }
                }
                if (name.equalsIgnoreCase(ConstantUtil.LEVEL)) {
                    if (component instanceof TextField) {
                        if (((TextField) component).getData().equals(ConstantUtil.COMPANY_ID)) {
                            ((TextField) component).setEnabled(isEnable);
                        }
                        if (((TextField) component).getData().equals(ConstantUtil.COMPANY_NAME)) {
                            ((TextField) component).setEnabled(isEnable);
                        }
                    }
                }
              
                        }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    public void clickListeners(Button.ClickEvent event) {
        try {
            if (ConstantUtil.SEARCH.equals(event.getButton().getCaption())) {
                searchValues.clear();
                List<Component> compList = new ArrayList<Component>();
                getIterativeComponents(searchFieldVerticalLayout, compList);
                for (Component component : compList) {
                    if (component instanceof TextField) {
                        if (!StringUtils.EMPTY.equals(((TextField) component).getValue())) {

                            if (((TextField) component).isValid()) {
                                String data = null;
                                 
                                if (ConstantUtil.ADJUSTED_DEMAND.equals(demandTypeValue) && String.valueOf(((TextField) component).getData()).equals("forecastMonth")) {
                                    data = "month";
                                } else if (ConstantUtil.ADJUSTED_DEMAND.equals(demandTypeValue) && String.valueOf(((TextField) component).getData()).equals("forecastYear")) {
                                    data = "year";
                                } else if (ConstantUtil.ADJUSTED_DEMAND.equals(demandTypeValue) && String.valueOf(((TextField) component).getData()).equals("forecastVer")) {
                                    data = "forecastVersion";
                                }
                                  else{
                                   data= String.valueOf(((TextField) component).getData());
                                  }
                                errorMsg.clearError();
                                searchValues.put(((TextField) component).getData().equals("invalidRecordId")
                                        ? generatedColumn : ((TextField) component).getData().equals("businessUnit") ? "businessUnitName" : data, ((TextField) component).getValue());

                            } else {
                                try {
                                    ((TextField) component).validate();
                                } catch (Validator.InvalidValueException e) {
                                    errorMsg.setValue(e.getMessage());
                                    errorMsg.setComponentError(new UserError(e.getMessage()));
                                    errorMsg.setVisible(true);
                                } catch (Exception e) {

                                    LOGGER.error(e.getMessage());
                                }
                                return;
                            }
                        }
                    } else if (component instanceof PopupDateField) {
                        if (((PopupDateField) component).getValue() != null) {
                            searchValues.put(((PopupDateField) component).getData() + "_Date", ((PopupDateField) component).getValue());
                        }
                    } else if (component instanceof ComboBox) {
                        Object comboValue = ((ComboBox) component).getValue();
                        if (comboValue != null && !String.valueOf(comboValue).equals(ConstantUtil.SELECT_ONE)
                                && !((ComboBox) component).getData().equals("table")
                                && !primaryDto.getValidation().equalsIgnoreCase(ConstantUtil.DEMAND)) {
                        
                            if (primaryDto.getValidation().equals("GL Balance")) {
                                if (((ComboBox) component).getValue().equals("Yes")) {
                                    searchValues.put(((ComboBox) component).getData() + "_boolean", 1);
                                }
                                if(((ComboBox) component).getValue().equals("No"))
                                searchValues.put(((ComboBox) component).getData() + "_boolean", 0);
                               // int value = ((ComboBox) component).getValue().equals("Yes") ? 1 : 0;
                             

                              
                            } else if (primaryDto.getValidation().equals(ConstantUtil.RETURNS)) {

                                if (!"0".equals(String.valueOf(comboValue))) {
                                    int value = ((ComboBox) component).getValue().equals("true") ? 1 : 0;
                                    searchValues.put(((ComboBox) component).getData() + "_boolean", value);
                                }

                            } else if (((String.valueOf(((ComboBox) component).getData()).equals(ConstantUtil.QUANTITY_INCLUSION)))) {
                                if (!"4".equals(String.valueOf(comboValue))) {
                                    searchValues.put(((ComboBox) component).getData(),String.valueOf(((ComboBox) component).getValue()));
                                }
                            } else if (String.valueOf(((ComboBox) component).getData()).equals(ConstantUtil.POSTING_INDICATOR) && !tableName.equals("AccrualMaster")) {
                                if (!"4".equals(String.valueOf(comboValue))) {
                                    searchValues.put(((ComboBox) component).getData() + "_boolean", Integer.valueOf(comboValue.toString()));
                                }
                            } else if (String.valueOf(((ComboBox) component).getData()).equals(ConstantUtil.POSTING_INDICATOR) && tableName.equals("AccrualMaster")) {
                                if ("Yes".equals(String.valueOf(comboValue))) {
                                    searchValues.put(((ComboBox) component).getData() + "_boolean", 1);
                                } else if ("No".equals(String.valueOf(comboValue))) {
                                    searchValues.put(((ComboBox) component).getData() + "_boolean", 0);
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
                            if (!((ComboBox) component).getData().equals(ConstantUtil.ORGANIZATION_KEY)) {
                                if (((ComboBox) component).getValue() == null || ConstantUtil.SELECT_ONE.equals(((ComboBox) component).getValue())) {
                                    String msgValue = "";
                                    if (ConstantUtil.DEMAND_TYPE.equals(((ComboBox) component).getData())) {
                                        msgValue = "Please Select a value for Demand Type ddlb";
                                    }
                                    if (ConstantUtil.IS_FORECAST.equals(((ComboBox) component).getData())) {
                                        msgValue = "Please Select a value for Forecast Type";
                                    }
                                    final MessageBox msg = MessageBox.showPlain(Icon.WARN, "Mandatory Error", msgValue, new MessageBoxListener() {

                                        @SuppressWarnings("PMD")
                                        public void buttonClicked(final ButtonId buttonId) {
                                        }
                                    }, ButtonId.OK);
                                    msg.getButton(ButtonId.OK).focus();
                                    return;
                                }
                            }
                            if (ConstantUtil.IS_FORECAST.equals(((ComboBox) component).getData())) {
                                int isForecast = ConstantUtil.PROJECTION.equalsIgnoreCase(String.valueOf(((ComboBox) component).getValue())) ? 1 : 0;
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
                                } else {
                                    if (!invalidDemand) {
                                        tableName = ConstantUtil.DEMANDVIEW_TABLE;
                                    } else {
                                        invalidTableName = ConstantUtil.IVLD_DEMANDVIEW_TABLE;
                                    }
                                }
                                propertyId();
                            }
                            if (((ComboBox) component).getData().equals(ConstantUtil.ORGANIZATION_KEY) && !(((ComboBox) component).getValue() == null || ConstantUtil.SELECT_ONE.equals(((ComboBox) component).getValue()))) {
                                searchValues.put(((ComboBox) component).getData(), ((ComboBox) component).getValue());
                            }
                        }

                        if (primaryDto.getValidation().equalsIgnoreCase(ConstantUtil.INVENTORY)
                                && ConstantUtil.IS_FORECAST.equals(((ComboBox) component).getData())) {
                            if (!(((ComboBox) component).getValue() != null && !ConstantUtil.SELECT_ONE.equals(((ComboBox) component).getValue()))) {
                                final MessageBox msg = MessageBox.showPlain(Icon.WARN, "Mandatory Error", "Please Select a value for Type", new MessageBoxListener() {

                                    @SuppressWarnings("PMD")
                                    public void buttonClicked(final ButtonId buttonId) {
                                    }
                                }, ButtonId.OK);
                                msg.getButton(ButtonId.OK).focus();
                                return;
                            } else {
                                int isForecast = ConstantUtil.PROJECTION.equalsIgnoreCase(String.valueOf(((ComboBox) component).getValue())) ? 1 : 0;
                                searchValues.put(((ComboBox) component).getData(),
                                        isForecast);
                                commonLogic.setIsForecast(isForecast);
                                removeListInventory();
                            }
                        }
                        if (((primaryDto.getValidation().equalsIgnoreCase(ConstantUtil.ACCURAL_MASTER))||(ConstantUtil.INVALID_ACCURAL_INBOUND.equals(invalidTableName)))
                                && "accrualType".equals(((ComboBox) component).getData())) {
                            if (!(((ComboBox) component).getValue() != null && !ConstantUtil.SELECT_ONE.equals(((ComboBox) component).getValue()))) {
                                final MessageBox msg = MessageBox.showPlain(Icon.WARN, "Mandatory Error", "Please Select Accrual Type", new MessageBoxListener() {

                                    @SuppressWarnings("PMD")
                                    public void buttonClicked(final ButtonId buttonId) {
                                    }
                                }, ButtonId.OK);
                                msg.getButton(ButtonId.OK).focus();
                                return;
                            }

                        }
                        if (primaryDto.getValidation().equalsIgnoreCase(ConstantUtil.INVENTORY)
                                && ConstantUtil.IS_MASTER.equals(((ComboBox) component).getData())) {
                            if (!(((ComboBox) component).getValue() != null && !ConstantUtil.SELECT_ONE.equals(((ComboBox) component).getValue()))) {
                                final MessageBox msg = MessageBox.showPlain(Icon.WARN, "Mandatory Error", "Please Select a value for Level", new MessageBoxListener() {

                                    @SuppressWarnings("PMD")
                                    public void buttonClicked(final ButtonId buttonId) {
                                    }
                                }, ButtonId.OK);
                                msg.getButton(ButtonId.OK).focus();
                                return;
                            } else {
                                  int isMaster = ConstantUtil.SUMMARY.equalsIgnoreCase(String.valueOf(((ComboBox) component).getValue())) ? 1 : 0;
                                searchValues.put(((ComboBox) component).getData(),
                                        isMaster);
                                commonLogic.setIsMaster(isMaster);
                                removeListInventory();
                            }
                        }
                        if (primaryDto.getValidation().equalsIgnoreCase(ConstantUtil.CPI_INDEX)) {
                            String value = String.valueOf(((ComboBox) component).getValue());
                            if (((ComboBox) component).getValue() != null && !ConstantUtil.SELECT_ONE.equals(((ComboBox) component).getValue())) {
                                if (ConstantUtil.STATUS.equals(((ComboBox) component).getData().toString())) {

                                    String data = String.valueOf(((ComboBox) component).getValue());
                                    if (!ConstantUtil.SELECT_ONE.equals(value)) {
                                        searchValues.put(((ComboBox) component).getData(), data);
                                    }
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
                            if (invalidTo != null && invalidFrom != null) {
                                if (invalidTo.before(invalidFrom)) {
                                    final MessageBox msg = MessageBox.showPlain(Icon.WARN, "Error", "Invalid To Date should be greater than Invalid From Date", new MessageBoxListener() {

                                        @SuppressWarnings("PMD")
                                        public void buttonClicked(final ButtonId buttonId) {
                                        }
                                    }, ButtonId.OK);
                                    msg.getButton(ButtonId.OK).focus();
                                    return;
                                }
                            }
                        }

                    }
                }
                LOGGER.info("searchValues map size is " + searchValues.size() + searchValues);

                btnSearchLogic();

            } else if (ConstantUtil.RESET.equals(event.getButton().getCaption())) {
                MessageBox.showPlain(Icon.QUESTION, "Confirmation", "Are you sure you want to reset the page to default/previous values " + " ?", new MessageBoxListener() {
                    /**
                     * Called when a Button has been clicked .
                     *
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals("YES")) {

                            try {
                                resetButtonLogic();
                                resetFields();

                            } catch (Exception ex) {
                                LOGGER.error(ex.getMessage());
                            }

                        }
                    }
                }, ButtonId.YES, ButtonId.NO);
            }
//             else if (ConstantUtil.REMOVE.equals(event.getButton().getCaption())) {

//                 if (table.getValue() != null) {

//                MessageBox.showPlain(Icon.QUESTION, "Confirmation", "Are you sure you want to Remove the selected record(s)?  Removed records will no longer be visible in the Invalid Table search Results" + " ?", new MessageBoxListener() {
//                    /**
//                     * Called when a Button has been clicked .
//                     *
//                     */
//                    @SuppressWarnings("PMD")
//                    public void buttonClicked(final ButtonId buttonId) {
//                        if (buttonId.name().equals("YES")) {

//                            try {
//                                removeBtn();
//                            } catch (Exception ex) {
//                                ex.printStackTrace();
//                                LOGGER.error(ex.getMessage());
//                            }
//
//                        }
//                    }
//                }, ButtonId.YES, ButtonId.NO);
//                } else {
//            AbstractNotificationUtils.getErrorNotification("Halt", "Please check mark at least one row from the Results list view.");
//            }
//            }

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Adds the to table.
     *
     * @return the table
     */
    private void addToTable() throws SystemException, PortalException, Exception {
        LOGGER.info("Entering addToTable" + tableName);
        try {
            tableLogic.setContainerDataSource(new BeanItemContainer(Object.class));
            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.USERID));
            final Map<String, AppPermission> fieldForecastSalesHM = stplSecurity.getBusinessFieldPermission(userId, primaryDto.getValidation());
            final TableResultCustom tableResultCustom = commonSecurityLogic.modifyTableResult(tableColumnArr, tableHeaderArr, fieldForecastSalesHM);
            table.markAsDirty();
            tableLogic.setPageLength(10);
            tableLogic.sinkItemPerPageWithPageLength(false);
            table.setComponentError(null);
            if (!tableName.equalsIgnoreCase(ConstantUtil.INVALID_RECORD_COUNT)) {
                if (tableName.equals("AccrualMaster") || tableName.equals("AccrualDetails")) {

                    tableLogic.setContainerDataSource(new BeanItemContainer(AccrualdetailsDTO.class));

                    table.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
                    table.addStyleName("table-header-normal");
                    table.setImmediate(true);

                } else if (tableName.equals("VwCustomerGtsForecast") || tableName.equals(ConstantUtil.CUSTOMER_GTS_ACTUAL)
                        || ConstantUtil.INVALID_ACCURAL_INBOUND.equals(tableName)) {
                    tableLogic.setContainerDataSource(new BeanItemContainer(Class.forName(ConstantUtil.TABLE_MODEL_PATH_TWO + tableName)));
                } else {
                    tableLogic.setContainerDataSource(new BeanItemContainer(Class.forName(ConstantUtil.TABLE_MODEL_PATH + tableName)));
                }
                table.setVisibleColumns(tableResultCustom.getObjResult());
                table.setColumnHeaders(tableResultCustom.getObjResultHeader());
                invalidButtonLayout.setVisible(false);
                table.setSelectable(primaryDto.getScreenCount() > 1);

            } else {
                resultsPanel.setVisible(false);
                excelExport.setVisible(false);
                invalidButtonLayout.setVisible(false);
                table.setSelectable(true);
                table.setMultiSelect(true);
            }
            table.setPageLength(5);
            table.setWidth(100, UNITS_PERCENTAGE);
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
            table.setImmediate(true);

            if (primaryDto.getValidation().equals("GL Balance") || primaryDto.getValidation().equals(ConstantUtil.ACCRUAL_MASTER) || primaryDto.getValidation().equals(ConstantUtil.CPI_INDEX)) {
                table.setFilterGenerator(new FilterGenerator());
            }

        } catch (Exception ec) {
            ec.printStackTrace();
        }
        LOGGER.info("Ends addToTable with table");
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

    protected abstract void resetButtonLogic() throws PortalException, Exception;

    protected abstract void btnSearchLogic() throws PortalException, SystemException, Exception;

    protected abstract void btnGenerateLogic();

    /**
     * Reset the Search criteria
     *
     */
    private void resetFields() {
        LOGGER.info("Inside resetFields");
        searchValues.clear();
        errorMsg.clearError();
        try {
            Iterator<Component> componentIterator = null;
            Iterator<Component> componentIterator2 = null;
            if (ConstantUtil.INVALID_RECORD_COUNT.equalsIgnoreCase(selectedInvalidModule)) {
                componentIterator = cssLayout1.getComponentIterator();
            } else {
                componentIterator2 = cssLayout1.getComponentIterator();
                componentIterator = cssLayout.getComponentIterator();
            }
            if(componentIterator2!=null){
            while (componentIterator2.hasNext()) {
                Component component = componentIterator2.next();
                if (component instanceof TextField) {
                    ((TextField) component).setValue(StringUtils.EMPTY);
                } else if (component instanceof PopupDateField) {
                    ((PopupDateField) component).setValue(null);
                } else if (component instanceof ComboBox) {
                    if (!((ComboBox) component).getData().equals("table")) {
                        ((ComboBox) component).select(ConstantUtil.SELECT_ONE);
                    }
                }
            }
            }
            while (componentIterator.hasNext()) {
                Component component = componentIterator.next();
                if (component instanceof TextField) {
                    ((TextField) component).setValue(StringUtils.EMPTY);
                } else if (component instanceof PopupDateField) {
                    ((PopupDateField) component).setValue(null);
                } else if (component instanceof ComboBox) {
                    if (!((ComboBox) component).getData().equals("table")) {
                       if("glCompanyMasterSid".equals(((ComboBox) component).getData())){
                             ((ComboBox) component).select(0);
                        }else{
                             ((ComboBox) component).select(ConstantUtil.SELECT_ONE);
                       }
                    }
                }
            }
              commonLogic.setAdjustDemand(false);
              commonLogic.setInvalidAdjustedDemand(false);
              AbstractComponentCreater.setAdjustDemand(false);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Export button.
     */
    private void exportButton() {
        LOGGER.info("Entering exportButton");
        excelExport.setCaption("");
        excelExport.setIcon(excelImage);
        excelExport.setVisible(true);
        excelExport.setStyleName("link");

        excelExport.setDescription("Export to Excel");
        mainLayout.addComponent(space);
        excelExport.setIconAlternateText("Excel export");
        excelExport.addClickListener(new Button.ClickListener() {
            /**
             * After clicking export button, function will be executed.
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final Button.ClickEvent event) {
                try {
                    LOGGER.info("Entering buttonClick with event for excelExport");
                    createWorkSheet();
                    LOGGER.info("Ends buttonClick for excelExport");
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

        LOGGER.info("Ends exportButton");
    }

    /**
     * Creates the work sheet.
     */
    private void createWorkSheet() throws SystemException, PortalException, Exception {
        LOGGER.info("Entering createWorkSheet");
        long recordCount = 0;
        final String[] header = table.getColumnHeaders();
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
            } else {
                if (ConstantUtil.INVENTORY.equals(EXCEL_NAME)) {
                    EXCEL_NAME = "Inventory Withdrawal Summary";
                } else if (ConstantUtil.CUSTOMERSALES.equals(EXCEL_NAME)) {
                    EXCEL_NAME = "Projected GTS Customer Level";
        } else if (ConstantUtil.ACTUALGTSCUSTOMERSALES.equals(EXCEL_NAME)) {
                    EXCEL_NAME = "Actual GTS Customer Level";
                } else if (ConstantUtil.ACCRUAL_DETAILS.equals(EXCEL_NAME)) {
                    EXCEL_NAME = "Accrual Inbound Details";
        }
        }
        if (table.size() != 0) {
            SearchLogic searchLogic = new SearchLogic();
            //   long recordCount = 0;
            if (tableName != null) {
                if (searchValues.isEmpty()) {
                    recordCount = (int) searchLogic.getActualsTotalCount(ConstantUtil.INVALID_RECORD_COUNT.equalsIgnoreCase(tableName) ? invalidTableName : tableName);
                } else {
                    if ("ActualsMaster".equals(tableName) || "IvldActualMaster".equals(tableName)) {
                    recordCount = searchLogic.getActualSearchResults(searchValues, null, ConstantUtil.INVALID_RECORD_COUNT.equalsIgnoreCase(tableName) ? invalidTableName : tableName);
                } else {
                    if (searchValues != null && searchValues.get("accrualType") != null && !searchValues.get("accrualType").equals("Other")
                            && !tableName.equalsIgnoreCase(ConstantUtil.INVALID_RECORD_COUNT)) {
                        tableName = "AccrualDetails";
                    }
                    recordCount = searchLogic.getDynamicQuerySearch(searchValues, null, ConstantUtil.INVALID_RECORD_COUNT.equalsIgnoreCase(tableName) ? invalidTableName : tableName);
                }
            }
            }
            //  final String[] header = table.getColumnHeaders();
            excelVisibleColumnArr = table.getVisibleColumns();
            if (invalidTableName.equals("IvldSalesMaster") || tableName.equals("IvldSalesMaster")) {
                EXCEL_NAME = "SalesMaster";
            }
        }
        ExcelExportforBB.createWorkSheet(header, recordCount, this, getUI().getCurrent(), EXCEL_NAME.trim().replace(" ", ""));
        //     }
        LOGGER.info("Ends createWorkSheet");
    }

    /**
     * This method used to write data in excel.
     *
     * @param start - start limit
     * @param end - end limit
     * @param printWriter the print writer
     */
    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws PortalException, SystemException, Exception {
        LOGGER.info("Entering createWorkSheetContent with start value  :::: " + start + ",  and end value  :::: " + end + "  and printWriter");
        try {
            primaryDto.setInvalidTableName(viewName);
            List<Object> salesList = new ArrayList<Object>();
            if (ConstantUtil.INVALID_RECORD_COUNT.equalsIgnoreCase(tableName)) {
                salesList = logic.getExcelList(searchValues, start, end, excelVisibleColumnArr, invalidTableName,primaryDto);
            } else {

                if (searchValues != null && searchValues.get("accrualType") != null && !searchValues.get("accrualType").equals("Other")) {
                    tableName = "AccrualDetails";
                }
                salesList = logic.getExcelList(searchValues, start, end, excelVisibleColumnArr, tableName,primaryDto);
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

                            printWriter.println(ob[i] == null ? " " : ob[i]);
                        }
                    } else if (ob[i] instanceof Date) {
                        if (ob[i] != null) {
                            printWriter.print(getFormattedDate(ob[i]) + ExcelExportUtil.COMMA);
                        } else {
                            printWriter.print(StringUtils.EMPTY + ExcelExportUtil.COMMA);
                        }
                    } else {

                        printWriter.print(ConstantUtil.QUOTE + (ob[i] == null ? " " : ob[i]) + ConstantUtil.QUOTE + ExcelExportUtil.COMMA);

                    }
                }
            }
            LOGGER.info("Ends createWorkSheetContent");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
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
            return null;
        }
    }

    /**
     * Gets the is active.
     *
     * @return the checks if is active
     */
    public List<HelperDTO> getIsActive() throws SystemException, PortalException, Exception {
        LOGGER.info("Entering getIsActive");
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        for (int i = 0; i < 3; i++) {
            HelperDTO dto;
            if (i == 0) {
                dto = new HelperDTO(i, ConstantUtil.SELECT_ONE);
            } else if (i == 1) {
                dto = new HelperDTO(i, ConstantUtil.YES);
            } else {
                dto = new HelperDTO(i, ConstantUtil.NO);
            }
            helperList.add(dto);

        }
        LOGGER.info(" Ends getIsActive with the  helperList size    ::::  " + helperList.size());

        return helperList;
    }

    public List<HelperDTO> getIsoutline() throws SystemException, PortalException, Exception {
        LOGGER.info("Entering getIsActive");
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        for (int i = 0; i < 3; i++) {
            HelperDTO dto;
            if (i == 0) {
                dto = new HelperDTO(i, ConstantUtil.SELECT_ONE);
            } else if (i == 1) {
                dto = new HelperDTO(i, "true");
            } else {
                dto = new HelperDTO(i, "false");
            }
            helperList.add(dto);

        }
        LOGGER.info(" Ends getIsoutline with the  helperList size    ::::  " + helperList.size());

        return helperList;
    }

    public void getComponentsForInvalidRecordCount() {
        if (tableValue!= null && tableValue!="null") {
            try {
                primaryDto.setValidation(tableValue);
                VaadinSession.getCurrent().setAttribute(ConstantUtil.DDLB_NAME, tableValue);
                VaadinSession.getCurrent().setAttribute(ConstantUtil.TABLE_NAME1, tableName);
                Object[] ob = commonLogic.getFiledNames((String) VaadinSession.getCurrent().getAttribute(ConstantUtil.DDLB_NAME), "Search");
                VaadinSession.getCurrent().setAttribute(ConstantUtil.TABCOUNT, Integer.valueOf(ob[2].toString()));

                newList = (List<DetailsDTO>) ob[0];
                List<DetailsDTO> primaryValueList = (List<DetailsDTO>) ob[1];
                moduleDetailsValue.clear();
                cssLayout.removeAllComponents();
                buttonLayout.removeAllComponents();
                moduleDetailsValue.addAll(newList);
                primaryDto = (DetailsDTO) primaryValueList.get(0);
                recordInvalidName = CommonLogic.getUpperCamelString(moduleDetailsValue.get(0).getTableName(), false);
                if (ob[3] != null) {
                    invalidTableName = CommonLogic.getUpperCamelString(ob[3].toString(), false);
                    VaadinSession.getCurrent().setAttribute(ConstantUtil.INVALID_TABLE_NAME1, ConstantUtil.INVALID_INVENTORYVIEW_TABLE.equals(invalidTableName) ? ConstantUtil.IVLD_INVENTORY_WITHDRAWAL_SUMMARY : invalidTableName);
                    VaadinSession.getCurrent().setAttribute(ConstantUtil.INVALID_TABLE_NAME, ob[3].toString());
                }
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
            }
            resultsPanel.setVisible(true);
            excelExport.setVisible(true);
            invalidButtonLayout.setVisible(true);

        } else {
            cssLayout.removeAllComponents();
            buttonLayout.removeAllComponents();
            resultsPanel.setVisible(false);
            excelExport.setVisible(false);
            invalidButtonLayout.setVisible(false);
        }
    }

    public void getTableForInvalidRecordCount() {
        try {
            LOGGER.info("Entering getTableForInvalidRecordCount");
            selectedInvalidModule = tableValue;
            getDynamicComponent();
            tableOnChange();
            //  configureTable();
            LOGGER.info("Ends getTableForInvalidRecordCount with table");
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }

    }

    protected void tableOnChange() {
        try {
            LOGGER.info("Inside Table On change " + invalidTableName);
            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.USERID));
            table.markAsDirty();
            getTableColumns();
            if (invalidTableName.equals("IvldCustomerGtsForecast") || ConstantUtil.IVLD_ADJUSTED_DEMAND_VIEW.equals(invalidTableName)
                    || ConstantUtil.INVALID_CUSTOMER_GTS_ACTUAL.equals(invalidTableName) || ConstantUtil.INVALID_ACCURAL_INBOUND.equals(invalidTableName)) {
                tableLogic.setContainerDataSource(new BeanItemContainer(Class.forName(ConstantUtil.TABLE_MODEL_PATH_TWO + invalidTableName)));
            } else {
                tableLogic.setContainerDataSource(new BeanItemContainer(Class.forName(ConstantUtil.TABLE_MODEL_PATH + invalidTableName)));
            }

            Object[] tableColumnArr1 = null;
            String[] tableHeaderArr1 = null;
            if (invalidTableName.equalsIgnoreCase("IvldActualMaster")) {
                List<Object> list = new ArrayList<Object>(Arrays.asList(tableColumnArr));
                list.removeAll(Arrays.asList("actualsMasterSid"));
                tableColumnArr1 = list.toArray();

                List<String> list1 = new ArrayList<String>(Arrays.asList(tableHeaderArr));
                list1.removeAll(Arrays.asList("Actuals Master ID"));
                tableHeaderArr1 = list1.toArray(new String[list1.size()]);
                table.setVisibleColumns(tableColumnArr1);
                table.setColumnHeaders(tableHeaderArr1);
            } else if ("IvldSalesMaster".equalsIgnoreCase(invalidTableName)) {
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
            table.setPageLength(5);
            table.setSelectable(true);
            table.setMultiSelect(true);
            table.setWidth(100, UNITS_PERCENTAGE);
            table.addStyleName("filterbar");
            table.setFilterBarVisible(true);
            table.setFilterDecorator(new ExtDemoFilterDecorator());
            table.setImmediate(true);
            if (primaryDto.getValidation().equals(ConstantUtil.GL_SPACE_BALANCE) || primaryDto.getValidation().equals(ConstantUtil.ACCRUAL_MASTER) || primaryDto.getValidation().equals(ConstantUtil.CPI_INDEX)) {
                table.setFilterGenerator(new FilterGenerator());
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        LOGGER.info("Inside Table On change ends" + invalidTableName);
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
                    generatedColumn = "demandIntSid";
                    viewName = "ivldDemandActualForecastSid";
                    stagingTable = "STAG_DEMAND_FORECAST";
                    viewNameSid = "IVLD_DEMAND_ACTUAL_FORECAST_SID";
                } else {
                    generatedColumn = "demandIntSid";
                    viewName = "ivldDemandActualForecastSid";
                    stagingTable = "STAG_DEMAND_ACTUAL";
                    viewNameSid = "IVLD_DEMAND_ACTUAL_FORECAST_SID";
                }
            } else {
                if (ConstantUtil.PROJECTION.equalsIgnoreCase(tableValue1)) {
                    generatedColumn = "adjustedDemandForecastIntfId";
                    viewName = "ivldAdjustedDemandForecastSid";
                    stagingTable = "STAG_ADJUSTED_DEMAND_FORECAST";
                    viewNameSid = "IVLD_ADJUSTED_DEMAND_FORECAST_SID";
                } else {
                    generatedColumn = "adjustedDemandForecastIntfId";
                    viewName = "ivldAdjustedDemandForecastSid";
                    stagingTable = "STAG_ADJUSTED_DEMAND_ACTUAL";
                    viewNameSid = "IVLD_ADJUSTED_DEMAND_FORECAST_SID";
                }
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

                if (table.getValue() != null && !((Set<?>) table.getValue()).isEmpty()) {
                    MessageBox.showPlain(Icon.QUESTION, "Confirmation", "Are you sure you want to Remove the selected record(s)?  Removed records will no longer be visible in the Invalid Table search Results" + " ?", new MessageBoxListener() {
                        /**
                         * Called when a Button has been clicked .
                         *
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            if (buttonId.name().equals("YES")) {
                                try {
                                    logic.updateFlagForReprocessedOrRemovedRecords(Integer.valueOf(removeId));
                                    table.removeAllItems();
                                    searchResultbeans.removeAllItems();
                                    tableLogic.groupChange();

                                } catch (Exception ex) {
                                    LOGGER.error(ex.getMessage());
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
        LOGGER.info("Enters removeList method");
        if (demandTypeValue != null && forecastTypeValue != null) {
            if (ConstantUtil.DEMAND.equalsIgnoreCase(demandTypeValue)) {
                if (ConstantUtil.PROJECTION.equalsIgnoreCase(forecastTypeValue)) {
                    if (columnList.contains(ConstantUtil.FORECASTVERSION)) {
                        columnList.remove(ConstantUtil.FORECASTVERSION);
                    }
                    if (!(headerList.contains(ConstantUtil.FORECASTING_NAME) || headerList.contains(ConstantUtil.FORECAST_SPACE_VERSION)
                            || headerList.contains("Uncaptured Units") || headerList.contains("Uncaptured Units Ratio") || headerList.contains("Uncaptured Units Ratio"))) {
                        headerList.add(0, ConstantUtil.FORECASTING_NAME);
                        headerList.add(1, ConstantUtil.FORECAST_SPACE_VERSION);
                        headerList.add(7, "Demand Type");
                        headerList.add(13, "Uncaptured Units");
                        headerList.add(14, "Uncaptured Units Ratio");
                        headerList.add(17, "Inventory Unit Change");
                    }
                    if (!(columnList.contains("forecastName") || columnList.contains("forecastVer")
                            || columnList.contains("uncapturedUnits") || columnList.contains("uncapturedUnitsRatio") || columnList.contains("inventoryUnitChange"))) {
                        columnList.add(0, "forecastName");
                        columnList.add(1, "forecastVer");
                        columnList.add(7, "isForecast");
                        columnList.add(13, "uncapturedUnits");
                        columnList.add(14, "uncapturedUnitsRatio");
                        columnList.add(17, "inventoryUnitChange");
                    }
                    if (!columnList.contains("forecastVer")) {
                        columnList.add(1, "forecastVer");
                    }
                } else {
                   if(headerList.contains(ConstantUtil.FORECASTING_NAME)){
                    headerList.remove(ConstantUtil.FORECASTING_NAME);
                   }
                   if(headerList.contains(ConstantUtil.FORECAST_SPACE_VERSION)){
                    headerList.remove(ConstantUtil.FORECAST_SPACE_VERSION);
                   }
                    headerList.remove("Demand Type");
                    headerList.remove("Uncaptured Units");
                    headerList.remove("Uncaptured Units Ratio");
                    headerList.remove("Inventory Unit Change");

        if (columnList.contains("forecastName")){
                    columnList.remove("forecastName");
             }
                    columnList.remove("isForecast");
                    columnList.remove("uncapturedUnits");
                    columnList.remove("uncapturedUnitsRatio");
                    columnList.remove("inventoryUnitChange");
                    if (columnList.contains(ConstantUtil.FORECASTVERSION)) {
                        columnList.remove(ConstantUtil.FORECASTVERSION);
                    } else if (columnList.contains(ConstantUtil.FORECAST_VER)) {
                        columnList.remove(ConstantUtil.FORECAST_VER);
                    }

                }
                getTableColumns();
                if (!(columnList.contains("forecastYear")
                        || columnList.contains("forecastMonth") || columnList.contains("totalDemandUnits") || columnList.contains("totalDemandAmount"))) {
                    columnList.set(columnList.indexOf("year"), "forecastYear");
                    columnList.set(columnList.indexOf("month"), "forecastMonth");
                    columnList.set(columnList.indexOf("totalAdjustedDemandUnits"), "totalDemandUnits");
                    columnList.set(columnList.indexOf("totalAdjustedDemandAmount"), "totalDemandAmount");
                }
                if (ConstantUtil.INVALID_RECORD_COUNT.equalsIgnoreCase(tableName)) {

                    if (!columnList.contains("demandIntSid")) {
                        columnList.set(columnList.indexOf("adjustedDemandForecastIntfId"), "demandIntSid");
                    }
                }
            } else {
                 
                if (columnList.contains(ConstantUtil.FORECAST_VER)) {
                    columnList.remove(ConstantUtil.FORECAST_VER);
                }
               if (ConstantUtil.PROJECTION.equalsIgnoreCase(forecastTypeValue)) {
                if (!headerList.contains(ConstantUtil.FORECASTING_NAME)) {
                    headerList.add(0, ConstantUtil.FORECASTING_NAME);
                }
                if (!headerList.contains(ConstantUtil.FORECAST_SPACE_VERSION)) {
                    headerList.add(1, ConstantUtil.FORECAST_SPACE_VERSION);
                }
                  if (!columnList.contains("forecastName")) {
                    columnList.add(0, "forecastName");
                }
                if (!columnList.contains(ConstantUtil.FORECASTVERSION)) {
                    columnList.add(1, ConstantUtil.FORECASTVERSION);
                }
                  }else{
                if (headerList.contains(ConstantUtil.FORECASTING_NAME)) {
                    headerList.remove(ConstantUtil.FORECASTING_NAME);
                }
                 if (headerList.contains(ConstantUtil.FORECAST_SPACE_VERSION)) {
                    headerList.remove(ConstantUtil.FORECAST_SPACE_VERSION);
                }
                  if (columnList.contains("forecastName")) {
                    columnList.remove("forecastName");
                }
                if (columnList.contains(ConstantUtil.FORECASTVERSION)) {
                    columnList.remove(ConstantUtil.FORECASTVERSION);
                }
                  }
               
                if (!headerList.contains("Uncaptured Units")) {
                    headerList.add(13, "Uncaptured Units");
                }
                if (!headerList.contains("Uncaptured Units Ratio")) {
                    headerList.add(14, "Uncaptured Units Ratio");
                }
                if (!headerList.contains("Inventory Unit Change")) {
                    headerList.add(17, "Inventory Unit Change");
                }
               
                if (!columnList.contains("uncapturedUnits")) {
                    columnList.add(13, "uncapturedUnits");
                }
                if (!columnList.contains("uncapturedUnitsRatio")) {
                    columnList.add(14, "uncapturedUnitsRatio");
                }
                if (!columnList.contains("inventoryUnitChange")) {
                    columnList.add(17, "inventoryUnitChange");
                }

                if (!(columnList.contains("year")
                        || columnList.contains("month") || columnList.contains("totalAdjustedDemandUnits") || columnList.contains("totalAdjustedDemandAmount"))) {
                    columnList.set(columnList.indexOf("forecastYear"), "year");
                    columnList.set(columnList.indexOf("forecastMonth"), "month");
                    columnList.set(columnList.indexOf("totalDemandUnits"), "totalAdjustedDemandUnits");
                    columnList.set(columnList.indexOf("totalDemandAmount"), "totalAdjustedDemandAmount");
                }
                if (ConstantUtil.INVALID_RECORD_COUNT.equalsIgnoreCase(tableName)) {
                    if (!columnList.contains("adjustedDemandForecastIntfId")) {
                        columnList.set(columnList.indexOf("demandIntSid"), "adjustedDemandForecastIntfId");
                    }
                }
            }
            getTableColumns();
        }
    }
    protected void removeListInventory() {
        if (ConstantUtil.INVENTORYVIEW_TABLE.equalsIgnoreCase(tableName) || ConstantUtil.INVALID_INVENTORYVIEW_TABLE.equalsIgnoreCase(invalidTableName)) {

            if (ConstantUtil.SUMMARY.equals(primaryDto.getLevelDdlb())) {
                headerList.remove("Company ID");
                headerList.remove("Company Name");
                headerListInventoryRemove.add("Company ID");
                headerListInventoryRemove.add("Company Name");

                columnList.remove("companyId");
                columnList.remove("companyName");

            } else {

                if (!(columnList.contains(ConstantUtil.COMPANY_ID) || columnList.contains(ConstantUtil.COMPANY_NAME))) {
                    columnList.add(4, ConstantUtil.COMPANY_ID);
                    columnList.add(5, ConstantUtil.COMPANY_NAME);

                }
                if (!(headerList.contains("Company ID") || headerList.contains("Company Name"))) {
                    headerList.add(4, "Company ID");
                    headerList.add(5, "Company Name");
                    headerListInventoryRemove.remove("Company ID");
                    headerListInventoryRemove.remove("Company Name");

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
                    columnList.add(index + 1, "price");
                    columnList.add(columnList.indexOf("source") + 1, ConstantUtil.FORECAST_NAME);
                    columnList.add(columnList.indexOf("source") + 2, ConstantUtil.FORECAST_VER);

                    columnList.add(columnList.indexOf("country") + 1, ConstantUtil.ORGANIZATION_KEY);

                }
                if (!(headerList.contains(ConstantUtil.PRICE_DUP) || headerList.contains(ConstantUtil.FORECASTING_NAME)
                        || headerList.contains(ConstantUtil.FORECAST_SPACE_VER) || headerList.contains(ConstantUtil.ORGANIZATION_SPACE_KEY))) {
                    headerList.add(index + 1, "Price");
                    headerList.add(headerList.indexOf("Source ID") + 1, ConstantUtil.FORECASTING_NAME);

                    headerList.add(headerList.indexOf("Source ID") + 2, ConstantUtil.FORECAST_SPACE_VER);
                    headerList.add(headerList.indexOf("Country") + 1, ConstantUtil.ORGANIZATION_SPACE_KEY);

                    headerListInventoryRemove.remove("Price");
                    headerListInventoryRemove.remove(ConstantUtil.FORECASTING_NAME);
                    headerListInventoryRemove.remove(ConstantUtil.FORECAST_SPACE_VER);
                    headerListInventoryRemove.remove(ConstantUtil.ORGANIZATION_SPACE_KEY);

                }

            } else if (ConstantUtil.ACTUALS.equals(primaryDto.getTypeDdlb())) {
                headerList.remove("Price");
                headerList.remove(ConstantUtil.FORECASTING_NAME);
                headerList.remove(ConstantUtil.FORECAST_SPACE_VER);
                headerList.remove(ConstantUtil.ORGANIZATION_SPACE_KEY);

                headerListInventoryRemove.add("Price");
                headerListInventoryRemove.add(ConstantUtil.FORECASTING_NAME);
                headerListInventoryRemove.add(ConstantUtil.FORECAST_SPACE_VER);
                headerListInventoryRemove.add(ConstantUtil.ORGANIZATION_SPACE_KEY);

                columnList.remove("price");
                columnList.remove("forecastName");
                columnList.remove("forecastVer");
                columnList.remove("organizationKey");
                int index = headerList.indexOf("Amount Withdrawn");
                if (!(columnList.contains(ConstantUtil.UNITS_ON_HAND) || columnList.contains(ConstantUtil.AMOUNT_ON_HAND)
                        || columnList.contains(ConstantUtil.QUANTITY_ON_ORDER) || columnList.contains(ConstantUtil.AMOUNT_ON_ORDER)
                        || columnList.contains(ConstantUtil.QUANTITY_RECEIVED) || columnList.contains(ConstantUtil.AMOUNT_RECEIVED))) {
                    columnList.add(index + 1, ConstantUtil.UNITS_ON_HAND);
                    columnList.add(index + 2, ConstantUtil.AMOUNT_ON_HAND);
                    columnList.add(index + 3, ConstantUtil.QUANTITY_ON_ORDER);
                    columnList.add(index + 4, ConstantUtil.AMOUNT_ON_ORDER);
                    columnList.add(index + 5, ConstantUtil.QUANTITY_RECEIVED);
                    columnList.add(index + 6, ConstantUtil.AMOUNT_RECEIVED);

                }
                if (!(headerList.contains(ConstantUtil.UNITS_ON_HANDS) || headerList.contains(ConstantUtil.AMOUNT_SPACE_ON_HAND)
                        || headerList.contains(ConstantUtil.QUANTITY_SPACE_ON_ORDER) || headerList.contains(ConstantUtil.AMOUNT_SPACE_ON_ORDER)
                        || headerList.contains(ConstantUtil.QUANTITY_SPACE_RECEIVED) || headerList.contains(ConstantUtil.AMOUNT_SPACE_RECEIVED))) {
                    headerList.add(index + 1, ConstantUtil.UNITS_ON_HANDS);
                    headerList.add(index + 2, ConstantUtil.AMOUNT_SPACE_ON_HAND);
                    headerList.add(index + 3, ConstantUtil.QUANTITY_SPACE_ON_ORDER);
                    headerList.add(index + 4, ConstantUtil.AMOUNT_SPACE_ON_ORDER);
                    headerList.add(index + 5, ConstantUtil.QUANTITY_SPACE_RECEIVED);
                    headerList.add(index + 6, ConstantUtil.AMOUNT_SPACE_RECEIVED);

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

        MessageBox.showPlain(Icon.QUESTION, "Confirmation", "Would you like to resend these accrual records to the General Ledger?" + " ?", new MessageBoxListener() {
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
                        LOGGER.error(ex.getMessage());
                    }

                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }
//    private void configureTable() throws Exception {
//
////        ResponsiveUtils.getResponsiveControls(tableLogic.createControls(), controlLayout);
//        
//        table.setValidationVisible(false);
//        table.setVisibleColumns(oo);
//        table.setColumnHeaders(jo);
//        table.markAsDirtyRecursive();
//        table.setWidth(100, UNITS_PERCENTAGE);
//    }

    protected void addPagination(VerticalLayout tableLayout) {
        if (tableLogic != null) {

            // tableLayout.addComponent(tableLogic.createControls());
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
        HorizontalLayout pageManagement = (HorizontalLayout) tempLayout.getComponent(1);

        CssLayout cssLayout = new CssLayout();
        cssLayout.setSizeFull();
        cssLayout.addComponent(pageSize.getComponent(0));
        cssLayout.addComponent(pageSize.getComponent(0));
        for (int index = 0; index < 8; index++) {
            cssLayout.addComponent(pageManagement.getComponent(0));
        }
        controlBar.addComponent(cssLayout);

    }

}
