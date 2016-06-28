/*
 * 
 */
package com.stpl.app.galforecasting.lookups;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.app.galforecasting.dto.ProjectionSelectionDTO;;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.galforecasting.logic.CommonLogic;
import com.stpl.app.galforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.galforecasting.utils.MandatedChartUtils;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.utils.Constants.FrequencyConstants;
import static com.stpl.app.utils.Constants.ResourceConstants.*;
import static com.stpl.app.utils.Constants.WindowMessagesName.RESET_CONFIRMATION;
import com.stpl.app.galforecasting.utils.MandatedGraphWindow;
import com.stpl.app.galforecasting.salesprojection.utils.HeaderUtils;
import com.stpl.app.galforecasting.lookups.dto.MPmpyDTO;
import com.stpl.app.galforecasting.lookups.logic.PmpyLogic;
import com.stpl.app.galforecasting.salesprojection.utils.SalesUtils;
import com.stpl.app.galforecasting.ui.ForecastUI;
import com.stpl.app.galforecasting.utils.Constant;
import static com.stpl.app.galforecasting.utils.Constant.DASH;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 * The Class MPmpyCalculator.
 * 
 */
public class MPmpyCalculator extends Window {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(MPmpyCalculator.class);

    /**
     * The excel btn.
     */
    @UiField("excelBtn")
    public Button excelBtn;

    /**
     * The excel btn.
     */
    @UiField("nextBtn")
    public Button moveRight;

    /**
     * The excel btn.
     */
    @UiField("previousBtn")
    public Button moveLeft;

    /**
     * The graph btn.
     */
    @UiField("graphBtn")
    public Button graphBtn;

    /**
     * The excel calc btn.
     */
    @UiField("excelCalcBtn")
    public Button excelCalcBtn;

    @UiField("availableProducts")
    public ExtFilterTable availableProductsTable;

    @UiField("selectedProducts")
    public ExtFilterTable selectedProductsTable;

    public ExtFilterTable pmpyTable=new ExtFilterTable();
    
    public ExtFilterTable pmpyExportTable=new ExtFilterTable();
    
    ExtFilterTreeTable excelTable = new ExtFilterTreeTable();

    @UiField("customerDdlb")
    public ComboBox customerDDLB;

    @UiField("contractDdlb")
    public ComboBox contractDDLB;

    @UiField("frequencyDdlb")
    public ComboBox frequencyDDLB;

    @UiField("generateBtn")
    public Button generateBtn;

    @UiField("resetBtn")
    public Button resetBtn;

    @UiField("allBtn")
    public Button allBtn;

    private BeanItemContainer<MPmpyDTO> availableProductsBean = new BeanItemContainer<MPmpyDTO>(MPmpyDTO.class);

    private BeanItemContainer<MPmpyDTO> selectedProductsBean = new BeanItemContainer<MPmpyDTO>(MPmpyDTO.class);

    /**
     * The excel export image.
     */
    private final Resource excelExportImage = new ThemeResource(EXCEL_IMAGE_PATH.getConstant());

    /**
     * The graph image.
     */
    private final Resource graphImage = new ThemeResource(GRAPH_IMAGE_PATH.getConstant());

    private PmpyLogic pmpyLogic = new PmpyLogic();

    private SessionDTO sessionDTO;

    private CustomTreeContainer<MPmpyDTO> pmpyContainer = new CustomTreeContainer<MPmpyDTO>(MPmpyDTO.class);

    private Object itemId;
    
    CustomTableHeaderDTO headerDTO;
    
    @UiField("sales")
    public  TextField salesField;
    
    @UiField("lives")
    private  TextField livesField; 
    
    @UiField("valuePerLife")
    private  TextField valuePerLifeField;
    
    @UiField("totalLives")
    private  TextField totalLivesField;
    
    @UiField("totalSales")
    private  TextField totalSalesField;
    
    @UiField("projPeriodTotal")
    private  TextField projPeriodTotalField;
    
    @UiField("livesCalcBtn")
    private  Button livesCalcBtn;
    
    @UiField("totLivesCalcBtn")
    private  Button totLivesCalcBtn;            
    
    @UiField("tableLayout")
    public VerticalLayout tableLayout;
    
    @UiField("closeBtn")
    public Button closeBtn;
    /**
     * The importBtn.
     */
    @UiField("importBtn")
    private Button importBtn ;
    
    @UiField("effectivePeriod")
    private ComboBox effectivePeriodDDLB;
    
    @UiField("SalesOrUnits")
    private OptionGroup salesOrUnits;
    
    @UiField("populate")
    private Button populateBtn;
    
    public boolean isImport = false;
    /**
     * Sales - true 
     * Units - false
     */
    public boolean isSalesOrUnits = true;
    
    List<MPmpyDTO> pmpyList = new ArrayList<MPmpyDTO>();
    
    Map<String,Double> pmpySalesMap = new HashMap<String, Double>();
    Map<String,Integer> pmpyUnitsMap = new HashMap<String, Integer>();
    
    DecimalFormat doubleDecimalFormat = new DecimalFormat("#0.00");
    
    DecimalFormat noDecimalFormat = new DecimalFormat("#");   
    /**
     * The calendar.
     */
    private Calendar calendar = CommonUtils.getCalendar();
     /**
     * The df sales.
     */
    private DecimalFormat dfSales = new DecimalFormat("#");

    List<String> projectedPeriodList = new ArrayList<String>();
    
    @UiField("resetCalcBtn")
    private Button resetCalcBtn;
    
    @UiField("buttonLayout")
    private HorizontalLayout buttonLayout;
    
    @UiField("mainLayout")
    private VerticalLayout mainLayout;
    
    private static final String DEFAULT_SALES = "$0.00";
    
    private static final String ZERO = DASH;
    
    private  static final String TEXT_RIGHT_ALIGN = "txtRightAlign";
    
    /**
     * The df units.
     */
    private DecimalFormat dfUnits = new DecimalFormat("#.0");
    
    private boolean importEvent=false;
    
    int projectionDetailsId = 0;
    
    private Map<Object, Boolean> chtCheckBoxMap = new HashMap<Object, Boolean>();    
    
    String updateValue;
    
    String updatePeriod;
    /**
     * Instantiates a new pmpy calculator.
     * @param sessionDTO
     * @param projectedPeriodList
     * @param salesRowDTO
     */
    public MPmpyCalculator(SessionDTO sessionDTO,List<String> projectedPeriodList) {
        super("PMPY Calculator");
        LOGGER.info("PmpyCalculator Constructor initiated ");
        addStyleName(Constant.BOOTSTRAP_UI);
        addStyleName(Constant.BOOTSTRAP);
        addStyleName(Constant.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        setClosable(true);
        center();
        setModal(true);
        this.sessionDTO = sessionDTO;
        this.projectedPeriodList = projectedPeriodList;        
        init();
        LOGGER.info("PmpyCalculator Constructor ends ");
    }

    private void init(){
        setContent(Clara.create(getClass().getResourceAsStream("/mandated/PmpyCalculator.xml"), this));
        setWidth("1600px");
        setHeight("950px");
        configureFields();
    }
    
    /**
     * Configure fields.
     */
    private void configureFields() {

        try {
            
            customerDDLB.focus();  
            
            mainLayout.setComponentAlignment(buttonLayout, Alignment.BOTTOM_RIGHT);
            buttonLayout.setSizeUndefined();
            buttonLayout.setComponentAlignment(effectivePeriodDDLB, Alignment.MIDDLE_RIGHT);
            buttonLayout.setComponentAlignment(resetCalcBtn, Alignment.MIDDLE_RIGHT);
            buttonLayout.setComponentAlignment(importBtn, Alignment.MIDDLE_RIGHT);
            buttonLayout.setComponentAlignment(closeBtn, Alignment.MIDDLE_RIGHT);
            buttonLayout.setComponentAlignment(excelCalcBtn, Alignment.MIDDLE_RIGHT);            
            
            salesField.addStyleName(TEXT_RIGHT_ALIGN);
            livesField.addStyleName(TEXT_RIGHT_ALIGN);
            valuePerLifeField.addStyleName(TEXT_RIGHT_ALIGN);
            totalLivesField.addStyleName(TEXT_RIGHT_ALIGN);
            totalSalesField.addStyleName(TEXT_RIGHT_ALIGN);
            projPeriodTotalField.addStyleName(TEXT_RIGHT_ALIGN);

            moveRight.setWidth("52px");
            moveLeft.setWidth("52px");
            excelBtn.setIcon(excelExportImage);
            graphBtn.setIcon(graphImage);
            excelCalcBtn.setIcon(excelExportImage);
            
            salesField.setImmediate(true);
            salesField.setEnabled(false);
            salesField.markAsDirty();
            salesField.setValue("0.0");
            
            valuePerLifeField.setImmediate(true);
            valuePerLifeField.setEnabled(false);
            
            totalSalesField.setImmediate(true);
            totalSalesField.setEnabled(false);
            
            projPeriodTotalField.setImmediate(true);
            projPeriodTotalField.setEnabled(false);   

            availableProductsTable.setImmediate(true);
            selectedProductsTable.setImmediate(true);
            availableProductsTable.markAsDirty();
            selectedProductsTable.markAsDirty();          

            availableProductsTable.setContainerDataSource(availableProductsBean);
            availableProductsTable.setVisibleColumns(HeaderUtils.PMPY_PRODUCT_COLUMNS);
            availableProductsTable.setColumnHeaders(HeaderUtils.PMPY_PRODUCT_HEADER);
            availableProductsTable.setColumnAlignment(HeaderUtils.PMPY_PRODUCT_COLUMNS[0], ExtCustomTable.Align.RIGHT);
            availableProductsTable.setFilterBarVisible(true);
            availableProductsTable.setFilterDecorator(new ExtDemoFilterDecorator());
            availableProductsTable.setStyleName(Constant.FILTER_TABLE);

            selectedProductsTable.setContainerDataSource(selectedProductsBean);
            selectedProductsTable.setVisibleColumns(HeaderUtils.PMPY_PRODUCT_COLUMNS);
            selectedProductsTable.setColumnHeaders(HeaderUtils.PMPY_PRODUCT_HEADER);
            selectedProductsTable.setColumnAlignment(HeaderUtils.PMPY_PRODUCT_COLUMNS[0], ExtCustomTable.Align.RIGHT);
            selectedProductsTable.setFilterBarVisible(true);
            selectedProductsTable.setFilterDecorator(new ExtDemoFilterDecorator());
            selectedProductsTable.setStyleName(Constant.FILTER_TABLE);

            loadCustomerDDLB();
            
            frequencyDDLB.addItem(Constant.SELECT_ONE);
            frequencyDDLB.setNullSelectionAllowed(true);
            frequencyDDLB.setNullSelectionItemId(Constant.SELECT_ONE);

            frequencyDDLB.addItem(FrequencyConstants.ANNUALLY.getConstant());
            frequencyDDLB.addItem(FrequencyConstants.SEMI_ANNUALLY.getConstant());
            frequencyDDLB.addItem(FrequencyConstants.QUARTERLY.getConstant());
            frequencyDDLB.addItem(FrequencyConstants.MONTHLY.getConstant());

            frequencyDDLB.setValue(FrequencyConstants.QUARTERLY.getConstant());
            
            contractDDLB.addItem(Constant.SELECT_ONE);
            contractDDLB.setNullSelectionAllowed(true);
            contractDDLB.setNullSelectionItemId(Constant.SELECT_ONE);            
            
            salesOrUnits.addItem("Sales Dollars");
            salesOrUnits.addItem(Constant.UNIT_VOLUME);
            salesOrUnits.select("Sales Dollars");
            
            projectedPeriodList.add(0, Constant.SELECT_ONE);
            effectivePeriodDDLB.addItems(projectedPeriodList);            
            effectivePeriodDDLB.setNullSelectionAllowed(true);
            effectivePeriodDDLB.setNullSelectionItemId(Constant.SELECT_ONE);
            effectivePeriodDDLB.select(Constant.SELECT_ONE);
            
            resetCalculationFields();            
            
            graphBtn.addClickListener(new Button.ClickListener() {

                public void buttonClick(Button.ClickEvent event) {

                    List chartList = new ArrayList();
                    ProjectionSelectionDTO projectionDTO = new ProjectionSelectionDTO();
                    for (Object obj : pmpyTable.getContainerDataSource().getItemIds()) {
                        MPmpyDTO dto = (MPmpyDTO) obj;                        
                            chartList.add(dto);                        
                    }
                    final MandatedChartUtils chart = new MandatedChartUtils(chartList, String.valueOf(frequencyDDLB.getValue()), StringUtils.EMPTY, headerDTO, "PMPYCalculator", projectionDTO);
                    final MandatedGraphWindow pmpyGraphWindow = new MandatedGraphWindow(chart.getChart(), "PMPY Calculator");
                    UI.getCurrent().addWindow(pmpyGraphWindow);
                    pmpyGraphWindow.focus();

                }
            });
            
            salesOrUnits.addValueChangeListener(new Property.ValueChangeListener() {

                public void valueChange(Property.ValueChangeEvent event) {                    
                    if(event.getProperty().getValue().toString().contains(Constant.SALES_SMALL)){
                        isSalesOrUnits = true;
                    }else{
                        isSalesOrUnits = false;
                    }
                    resetCalculationFields();
                }
            });
            
            populateBtn.addClickListener(new Button.ClickListener() {

                public void buttonClick(Button.ClickEvent event) {
                    calculateSalesOrUnits(salesOrUnits.getValue().toString());
                }
            });            
            
            resetCalcBtn.addClickListener(new Button.ClickListener() {

                public void buttonClick(Button.ClickEvent event) {
                    
                    new AbstractNotificationUtils() {
                        @Override
                        public void noMethod() {
                        }
                        @Override
                        public void yesMethod() {
                             resetCalculationFields();
                        }
                    }.getConfirmationMessage("Confirmation", "Are you sure you want to reset the Calculator group box back to its default settings?");
                    
                }
            });
            
            tableLayout.addComponent(pmpyTable);
            configurePMPYTable();           
            
            customerDDLB.addValueChangeListener(new Property.ValueChangeListener() {

                public void valueChange(Property.ValueChangeEvent event) {
                    try {
                        loadContractDDLB(Integer.valueOf(String.valueOf(event.getProperty().getValue())));
                        availableProductsBean.removeAllItems();
                        selectedProductsBean.removeAllItems();
                    } catch (Exception ex) {
                        LOGGER.error(ex.getMessage());
                    }
                }
            });

            availableProductsTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {

                public void itemClick(ItemClickEvent event) {
                    
                    itemId = event.getItemId();
                }
            });

            selectedProductsTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {

                public void itemClick(ItemClickEvent event) {
                    
                    itemId = event.getItemId();
                }
            });

            contractDDLB.addValueChangeListener(new Property.ValueChangeListener() {

                public void valueChange(Property.ValueChangeEvent event) {
                    try {
                        int customerSid = Integer.valueOf(String.valueOf(customerDDLB.getValue()));
                        int contractSid = Integer.valueOf(String.valueOf(contractDDLB.getValue()));
                        availableProductsBean.removeAllItems();
                        availableProductsBean.addAll(loadProductsTable(customerSid, contractSid));
                        selectedProductsBean.removeAllItems();
                    } catch (Exception ex) {
                        LOGGER.error(ex.getMessage());
                    }
                }
            });

            allBtn.addClickListener(new Button.ClickListener() {

                public void buttonClick(Button.ClickEvent event) {
                    try {
                        moveAllProducts();
                    } catch (Exception ex) {
                        LOGGER.error(ex.getMessage());
                    }

                }
            });

            moveRight.addClickListener(new Button.ClickListener() {

                public void buttonClick(Button.ClickEvent event) {
                    moveProductsRight();
                }
            });

            moveLeft.addClickListener(new Button.ClickListener() {

                public void buttonClick(Button.ClickEvent event) {
                    moveProductsLeft();
                }
            });

            generateBtn.addClickListener(new Button.ClickListener() {

                public void buttonClick(Button.ClickEvent event) {
                    try {
                        if(checkSelection()){
                        tableLayout.removeAllComponents();
                        pmpyTable=new ExtFilterTable();
                        tableLayout.addComponent(pmpyTable);
                        configurePMPYTable();
                        loadPMPYTable(false,null);
                        getItemsforCalculation();
                        resetCalculationFields();
                        pmpySalesMap.clear();
                        pmpyUnitsMap.clear();
                        }
                    } catch (SystemException ex) {
                        LOGGER.error(ex.getMessage());
                    } catch (SQLException ex) {
                        LOGGER.error(ex.getMessage());
                    }
                }
            });
            importBtn.addClickListener(new Button.ClickListener() {
                /**
                 * Default method.
                 */
                public void buttonClick(final Button.ClickEvent event) {
                    try {
                        importButtonLogic();
                    } catch (IllegalAccessException e) {                       
                        LOGGER.error(e.getMessage());
                    } catch (InvocationTargetException e) {                       
                        LOGGER.error(e.getMessage());
                    } catch (NoSuchMethodException e) {                     
                        LOGGER.error(e.getMessage());
                    }

                }
            });
            livesCalcBtn.addClickListener(new Button.ClickListener() {

                public void buttonClick(Button.ClickEvent event) {                    
                    final boolean chValue = isContractHistorySelected();
                    if (chValue) {
                        String valuePerLife ;
                        if(isSalesOrUnits){
                            valuePerLife = SalesUtils.valueToCurrency(calculateValuePerLife(Integer.valueOf(livesField.getValue())), doubleDecimalFormat);  
                        }else{
                            valuePerLife = calculateValuePerLife(Integer.valueOf(livesField.getValue()));  
                        }                    
                    valuePerLifeField.setValue(valuePerLife);
                    } else {
                        AbstractNotificationUtils.getErrorNotification("No Periods Selected",
                                "Please select at least one time period along with either ‘Actuals’ or ‘Projections’.");
                    }
                   
                }
            });
            
            totLivesCalcBtn.addClickListener(new Button.ClickListener() {

                public void buttonClick(Button.ClickEvent event) {

                    String tempVPL = isSalesOrUnits ? String.valueOf(SalesUtils.currencyToValue(valuePerLifeField.getValue())) : valuePerLifeField.getValue();
                    int valuePerlife = Double.valueOf(tempVPL).intValue();
                    String totalSales = calculateTotalSales(valuePerlife, Integer.valueOf(totalLivesField.getValue()));
                    totalSalesField.setValue(isSalesOrUnits ? SalesUtils.valueToCurrency(totalSales, doubleDecimalFormat) : totalSales);

                    int totalSalesAmount = Double.valueOf(totalSales).intValue();
                    int projectionPeriodTotal = calculateProjectionPeriodTotal(totalSalesAmount, String.valueOf(frequencyDDLB.getValue()));
                    projPeriodTotalField.setValue(isSalesOrUnits ? SalesUtils.valueToCurrency(String.valueOf(projectionPeriodTotal), doubleDecimalFormat) : String.valueOf(projectionPeriodTotal));
                    projPeriodTotalField.setData(projPeriodTotalField.getValue());
                }
            });
            
            closeBtn.addClickListener(new Button.ClickListener() {

                public void buttonClick(Button.ClickEvent event) {
                    String messageHeader = "Close Confirmation";
                    String messageBody = "Are you sure you want to Close the Worksheet?";

                    new AbstractNotificationUtils() {
                        @Override
                        public void noMethod() {

                        }

                        @Override
                        public void yesMethod() {
                             close();
                        }
                    }.getConfirmationMessage(messageHeader, messageBody);
                  
                }
            });
            
            livesField.addValueChangeListener(new Property.ValueChangeListener() {

                public void valueChange(Property.ValueChangeEvent event) {
                    String tempLives = String.valueOf(event.getProperty().getValue());
                      if(StringUtils.isNotBlank(tempLives)){
                    Double lives = Double.valueOf(tempLives);                 
                       livesField.setValue(String.valueOf(lives.intValue()));
                    }else{
                         livesField.setValue(DASH); 
                     }
                  
                }
            });

            totalLivesField.addValueChangeListener(new Property.ValueChangeListener() {

                public void valueChange(Property.ValueChangeEvent event) {
                    String tempTotalLives = String.valueOf(event.getProperty().getValue());
                    if(StringUtils.isNotBlank(tempTotalLives)){
                    Double totalLives = Double.valueOf(tempTotalLives);
                    totalLivesField.setValue(String.valueOf(totalLives.intValue()));
                     }else{
                         totalLivesField.setValue(DASH); 
                     }
                }
                
            });
            
            resetBtn.addClickListener(new Button.ClickListener() {

                public void buttonClick(Button.ClickEvent event) {
                    String messageHeader = RESET_CONFIRMATION.getConstant();
                    String messageBody = "Are you sure you want to reset the page to default/previous values?";

                    new AbstractNotificationUtils() {
                        @Override
                        public void noMethod() {

                        }

                        @Override
                        public void yesMethod() {
                            resetSelectionFields();
                        }
                    }.getConfirmationMessage(messageHeader, messageBody);

                }
            });
            
            excelCalcBtn.addClickListener(new Button.ClickListener() {

                public void buttonClick(Button.ClickEvent event) {
                    exportCalculatedExcel();
                }
            });
            
            excelBtn.addClickListener(new Button.ClickListener() {

                public void buttonClick(Button.ClickEvent event) {

                    try {
                        ExtFilterTable excelTable = new ExtFilterTable();
                        excelTable.setVisible(false);
                        tableLayout.addComponent(excelTable);
                        final CustomTreeContainer<MPmpyDTO> tempPMPYContainer = new CustomTreeContainer<MPmpyDTO>(MPmpyDTO.class);
                        
                        String frequency = String.valueOf(frequencyDDLB.getValue());
                        
                        if (FrequencyConstants.SEMI_ANNUALLY.getConstant().equals(frequency)) {
                            frequency = FrequencyConstants.SEMI_ANNUALLY.getConstant();
                        } else if (FrequencyConstants.ANNUALLY.getConstant().equals(frequency)) {
                            frequency = FrequencyConstants.ANNUALLY.getConstant();
                        }
                        
                        int frequencyRange = CommonUtils.getProjections(sessionDTO.getFromDate(), new Date(), frequency);
                        
                        CustomTableHeaderDTO excelHeaderDTO = HeaderUtils.getPMPYTableColumns(frequency, frequencyRange, true);
                        
                        tempPMPYContainer.setColumnProperties(excelHeaderDTO.getProperties());
                        loadPMPYTable(true, tempPMPYContainer);
                        excelTable.setContainerDataSource(tempPMPYContainer);
                        
                        excelTable.setVisibleColumns(excelHeaderDTO.getSingleColumns().toArray());
                        excelTable.setColumnHeaders(excelHeaderDTO.getSingleHeaders().toArray(new String[excelHeaderDTO.getSingleHeaders().size()]));
                        ForecastUI.EXCEL_CLOSE=true;
                        ExcelExport export = new ExcelExport(new ExtCustomTableHolder(excelTable), "PMPY Calculator", "PMPY Calculator", "PMPYCalculator.xls", false);
                        export.export();
                        tableLayout.removeComponent(excelTable);

                    } catch (SystemException ex) {
                        LOGGER.error(ex.getMessage());
                    } catch (SQLException ex) {
                        LOGGER.error(ex.getMessage());
                    }
                    
                }
            });
           
        } catch (Exception ex) {            
            LOGGER.error(ex.getMessage());
        }

    }

    public void loadCustomerDDLB() throws Exception {
        customerDDLB.setImmediate(true);
        customerDDLB.addItem(Constant.SELECT_ONE);
        customerDDLB.setNullSelectionAllowed(true);
        customerDDLB.setNullSelectionItemId(Constant.SELECT_ONE);
        List list = pmpyLogic.loadCustomer(sessionDTO.getCustomerHierarchyId(), sessionDTO.getProductHierarchyId());
        for (int i = 0; i < list.size(); i++) {
            Object[] obj = (Object[]) list.get(i);            
            customerDDLB.addItem(String.valueOf(obj[0]));
            customerDDLB.setItemCaption(String.valueOf(obj[0]), String.valueOf(obj[1]));
        }
    }

    public void loadContractDDLB(int customerSid) throws Exception {
        contractDDLB.removeAllItems();
        contractDDLB.addItem(Constant.SELECT_ONE);
        contractDDLB.setNullSelectionAllowed(true);
        contractDDLB.setNullSelectionItemId(Constant.SELECT_ONE);
        List list = pmpyLogic.loadContract(sessionDTO.getCustomerHierarchyId(), sessionDTO.getProductHierarchyId(), customerSid);
        for (int i = 0; i < list.size(); i++) {
            Object[] obj = (Object[]) list.get(i);            
            contractDDLB.addItem(String.valueOf(obj[0]));
            contractDDLB.setItemCaption(String.valueOf(obj[0]), String.valueOf(obj[1]));
        }
    }

    public List<MPmpyDTO> loadProductsTable(int customerSid, int contractSid) throws Exception {
        List list = pmpyLogic.loadProduct(sessionDTO.getCustomerHierarchyId(), sessionDTO.getProductHierarchyId(), customerSid, contractSid);
        return list;
    }

    public void moveProductsLeft() {                        
        availableProductsBean.addBean(getBeanFromId(itemId));        
        selectedProductsTable.removeItem(itemId);                
    }

    public void moveProductsRight() {                
        availableProductsTable.removeItem(itemId);        
        selectedProductsBean.addBean(getBeanFromId(itemId));        
    }

    public void moveAllProducts() throws Exception {
        
        int customerSid = Integer.valueOf(String.valueOf(customerDDLB.getValue()));
        int contractSid = Integer.valueOf(String.valueOf(contractDDLB.getValue()));

        if (availableProductsBean.size() == 0) {
            if (selectedProductsBean.size() != 0) {
                availableProductsBean.addAll(loadProductsTable(customerSid, contractSid));
                selectedProductsBean.removeAllItems();
            }
        } else if (selectedProductsBean.size() == 0) {
            if (availableProductsBean.size() != 0) {
                selectedProductsBean.addAll(loadProductsTable(customerSid, contractSid));
                availableProductsBean.removeAllItems();
            }
        }

    }

    public void configurePMPYTable() {

        String frequency = String.valueOf(frequencyDDLB.getValue());

        if (FrequencyConstants.SEMI_ANNUALLY.getConstant().equals(frequency)) {
            frequency = FrequencyConstants.SEMI_ANNUALLY.getConstant();
        } else if (FrequencyConstants.ANNUALLY.getConstant().equals(frequency)) {
            frequency = FrequencyConstants.ANNUALLY.getConstant();
        }
     
        
        int frequencyRange = 0;
        
        if(FrequencyConstants.SEMI_ANNUALLY.getConstant().equalsIgnoreCase(frequency)){
        frequencyRange=6;
        }
        else if(FrequencyConstants.ANNUALLY.getConstant().equalsIgnoreCase(frequency)){
        frequencyRange=3;
        }
        else if(FrequencyConstants.QUARTERLY.getConstant().equalsIgnoreCase(frequency)){
           frequencyRange=12; 
        }
        else if(FrequencyConstants.MONTHLY.getConstant().equalsIgnoreCase(frequency)){
           frequencyRange=36; 
        }

        headerDTO = HeaderUtils.getPMPYTableColumns(frequency, frequencyRange,false);
       
      
        pmpyTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        pmpyTable.setWidth("800px");
        pmpyTable.setPageLength(5);
        
        pmpyContainer = new CustomTreeContainer<MPmpyDTO>(MPmpyDTO.class);
        pmpyContainer.setColumnProperties(headerDTO.getProperties());        
        pmpyTable.setContainerDataSource(pmpyContainer);
        
        pmpyTable.setVisibleColumns(headerDTO.getSingleColumns().toArray());
        pmpyTable.setColumnHeaders(headerDTO.getSingleHeaders().toArray(new String[headerDTO.getSingleHeaders().size()]));
        
        for(Object visibleColumns:headerDTO.getSingleColumns()){
        pmpyTable.setColumnWidth(visibleColumns,150);
        }
  
        pmpyTable.setDoubleHeaderVisible(true);
        pmpyTable.setDoubleHeaderVisibleColumns(headerDTO.getDoubleColumns().toArray());
        pmpyTable.setDoubleHeaderColumnHeaders(headerDTO.getDoubleHeaders().toArray(new String[headerDTO.getDoubleHeaders().size()]));
        pmpyTable.setDoubleHeaderMap(headerDTO.getDoubleHeaderMaps());
        
        pmpyTable.setTripleHeaderVisible(true);
        pmpyTable.setTripleHeaderVisibleColumns(headerDTO.getTripleColumns().toArray());
        pmpyTable.setTripleHeaderColumnHeaders(headerDTO.getTripleHeader().toArray(new String[headerDTO.getTripleHeader().size()]));        
        pmpyTable.setTripleHeaderMap(headerDTO.getTripleHeaderMaps());
        
        for(Object obj:headerDTO.getSingleColumns()){
            pmpyTable.setColumnCheckBox(obj, true);     
            chtCheckBoxMap.put(obj, Boolean.FALSE);
        }
      
        for(Object obj:headerDTO.getDoubleColumns()){
            pmpyTable.setDoubleHeaderColumnRadioButton(obj, obj.toString());            
        }
        
        for(Object obj:headerDTO.getTripleColumns()){
            pmpyTable.setTripleHeaderColumnRadioButton(obj, obj.toString());
            
        }
        
        pmpyTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {

            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                
                if (event.isChecked()) {
                    getDataForCalculation(event.getPropertyId().toString(), true);
                    chtCheckBoxMap.put(event.getPropertyId(), Boolean.TRUE);
                }else{
                    pmpySalesMap.remove(event.getPropertyId().toString()); 
                    pmpyUnitsMap.remove(event.getPropertyId().toString());
                    chtCheckBoxMap.put(event.getPropertyId(), Boolean.FALSE);
                }
            }
        });
        
        pmpyTable.addDoubleHeaderColumnRadioCheckListener(new ExtCustomTable.DoubleHeaderColumnRadioCheckListener() {

            public void doubleHeaderColumnRadioCheck(ExtCustomTable.DoubleHeaderColumnRadioCheckEvent event) {
                
                if(pmpyTable.getColumnCheckBox(event.getRadioButtonName())){
                    
                    String key = event.getRadioButtonName();                                                    
                    getDataForCalculation(key, false);
                }
            }
        });
        
        pmpyTable.addTripleHeaderColumnRadioCheckListener(new ExtCustomTable.TripleHeaderColumnRadioCheckListener() {

            public void tripleHeaderColumnRadioCheck(ExtCustomTable.TripleHeaderColumnRadioCheckEvent event) {
                
                if(pmpyTable.getColumnCheckBox(event.getRadioButtonName())){
                    
                    String key = event.getRadioButtonName();                                                                           
                    getDataForCalculation(key, true);
                }
            }
        });
        
    }
    
    
    public int getHistoryRange(String frequency) {
        
        if (FrequencyConstants.SEMI_ANNUALLY.equals(frequency)) {
            frequency = FrequencyConstants.SEMI_ANNUALLY.getConstant();
        } else if (FrequencyConstants.ANNUALLY.equals(frequency)) {
            frequency = FrequencyConstants.ANNUALLY.getConstant();
        }
        return CommonUtils.getProjections(sessionDTO.getFromDate(), new Date(), frequency);
        
    }

    public String calculateValuePerLife(int lives) {
        double valuePerLife = 0;

        if (isSalesOrUnits) {
            Double tempSalesAmount = (Double) SalesUtils.currencyToValue(salesField.getValue());
            int salesAmount = tempSalesAmount.intValue();
            if (lives != 0 && salesAmount != 0) {
                valuePerLife = tempSalesAmount / lives;
            }
        } else {
            int salesAmount = Integer.valueOf(salesField.getValue());
            if (lives != 0 && salesAmount != 0) {
                valuePerLife = salesAmount / lives;
            }
        }
        return String.valueOf(valuePerLife);
    }
    
     public String calculateTotalSales(int valuePerLife,int totalLives) {
        int totalSales = 0;        
        if(valuePerLife != 0 && totalLives !=0){
            totalSales=valuePerLife * totalLives;
        }        
        return String.valueOf(totalSales);
    }
     
     public int calculateProjectionPeriodTotal(int totalSales,String frequency){        
         int projectionPeriodTotal = 0;         
         if(totalSales != 0){
             if(FrequencyConstants.ANNUALLY.getConstant().equals(frequency)){
                 projectionPeriodTotal = totalSales/4;
             }else if(FrequencyConstants.SEMI_ANNUALLY.getConstant().equals(frequency)){
                 projectionPeriodTotal = totalSales/2;
             }else if(FrequencyConstants.MONTHLY.getConstant().equals(frequency)){
                 projectionPeriodTotal = totalSales * 3;
             }else if(FrequencyConstants.QUARTERLY.getConstant().equals(frequency)){
                 projectionPeriodTotal = totalSales;
             }
         }                  
         return projectionPeriodTotal;
         
     }
    
     /**
     * Gets the bean from id.
     *
     * @param obj the obj
     * @return the bean from id
     */
    public MPmpyDTO getBeanFromId(Object obj) {

        BeanItem<?> targetItem = null;
        if (obj instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) obj;
        } else if (obj instanceof MPmpyDTO) {
            targetItem = new BeanItem<MPmpyDTO>(
                    (MPmpyDTO) obj);
        }

        return (MPmpyDTO) targetItem.getBean();
    }

    public void loadPMPYTable(boolean isExcelExport, CustomTreeContainer<MPmpyDTO> excelContainer) throws SystemException, SQLException {

        String productIds = StringUtils.EMPTY;
        String customerId = StringUtils.EMPTY.equals(String.valueOf(customerDDLB.getValue())) || Constant.NULL.equals(String.valueOf(customerDDLB.getValue())) ? DASH : String.valueOf(customerDDLB.getValue());
        String contractId = StringUtils.EMPTY.equals(String.valueOf(contractDDLB.getValue())) || Constant.NULL.equals(String.valueOf(contractDDLB.getValue())) ? DASH : String.valueOf(contractDDLB.getValue());
        
        if (selectedProductsTable.size() != 0) {
            productIds = getSelectedProducts();
        }
        String frequency = "quarterly";
        if (FrequencyConstants.ANNUALLY.getConstant().equals(frequencyDDLB.getValue())) {
            frequency = "yearly";
        } else if (FrequencyConstants.SEMI_ANNUALLY.getConstant().equals(frequencyDDLB.getValue())) {
            frequency = "SEMIANNUAL";
        } else if (FrequencyConstants.MONTHLY.getConstant().equals(frequencyDDLB.getValue())) {
            frequency = "monthly";
        } else if (FrequencyConstants.QUARTERLY.getConstant().equals(frequencyDDLB.getValue())) {
            frequency = "quarterly";
        }

        Object[] selection = new Object[]{contractId, customerId, productIds, frequency};

        String procedureName = "M_SALES_PMPY";
        List<Object[]> list = CommonLogic.callProcedure(procedureName, selection);
        if (isExcelExport) {
            excelContainer.addAll(pmpyLogic.convertPMPYResults(list, String.valueOf(frequencyDDLB.getValue()), true));
        } else {
            pmpyContainer.addAll(pmpyLogic.convertPMPYResults(list, String.valueOf(frequencyDDLB.getValue()), false));
        }

    }
    
    public String getSelectedProducts() {
        
        StringBuilder productIds = new StringBuilder();
        boolean flag = false;
        List<MPmpyDTO> list = (List) selectedProductsTable.getItemIds();
        for (MPmpyDTO pmpyDTO : list) {
            if (flag) {
                productIds.append(",");                
            }
            flag = true;
            
            productIds.append(pmpyDTO.getProductSid());
        }
        
        return productIds.toString();
        
    }
    
    public void getItemsforCalculation() {
        pmpyList = pmpyContainer.getItemIds();
    }

    public void getDataForCalculation(Object propertyId, boolean actualsOrProjections) {
        if (!pmpyList.isEmpty()) {

            if (pmpySalesMap.containsKey(propertyId.toString()) && pmpyUnitsMap.containsKey(propertyId.toString())) {
                pmpySalesMap.remove(propertyId.toString());
                pmpyUnitsMap.remove(propertyId.toString());
            }

            if (actualsOrProjections) {
                String tempSalesValue = String.valueOf(pmpyList.get(0).getPropertyValue(propertyId));
                if (StringUtils.EMPTY.equals(tempSalesValue) || Constant.NULL.equals(tempSalesValue) || "-".equals(tempSalesValue)) {
                    pmpySalesMap.put(propertyId.toString(), 0.00);
                } else {
                    Double actual = (Double) SalesUtils.currencyToValue(tempSalesValue);
                    pmpySalesMap.put(propertyId.toString(), actual);
                }

                String tempUnitsValue = String.valueOf(pmpyList.get(2).getPropertyValue(propertyId));
                if (StringUtils.EMPTY.equals(tempUnitsValue) || Constant.NULL.equals(tempUnitsValue) || "-".equals(tempUnitsValue)) {
                    pmpyUnitsMap.put(propertyId.toString(), 0);
                } else {
                    Integer actual = Integer.valueOf(tempUnitsValue);
                    pmpyUnitsMap.put(propertyId.toString(), actual);
                }

            } else {
                String tempSalesvalue = String.valueOf(pmpyList.get(1).getPropertyValue(propertyId));
                if (StringUtils.EMPTY.equals(tempSalesvalue) || Constant.NULL.equals(tempSalesvalue) || "-".equals(tempSalesvalue)) {
                    pmpySalesMap.put(propertyId.toString(), 0.00);
                } else {
                    Double projection = (Double) SalesUtils.currencyToValue(tempSalesvalue);
                    pmpySalesMap.put(propertyId.toString(), projection);
                }

                String tempUnitsValue = String.valueOf(pmpyList.get(3).getPropertyValue(propertyId));
                if (StringUtils.EMPTY.equals(tempUnitsValue) || Constant.NULL.equals(tempUnitsValue) || "-".equals(tempUnitsValue)) {
                    pmpyUnitsMap.put(propertyId.toString(), 0);
                } else {
                    Integer projection = Integer.valueOf(tempUnitsValue);
                    pmpyUnitsMap.put(propertyId.toString(), projection);
                }

            }
        }
    }

    public void calculateSalesOrUnits(String salesOrUnits) {
        
        if (salesOrUnits.contains(Constant.SALES_SMALL)) {
            double totalSalesAmount = 0;
            for (double tempSalesAmount : pmpySalesMap.values()) {
                totalSalesAmount += tempSalesAmount;
            }
            String formattedValue = SalesUtils.valueToCurrency(String.valueOf(totalSalesAmount), doubleDecimalFormat);
            salesField.setValue(formattedValue);
        } else {
            int totalUnitVolume = 0;            
            for (int tempUnitVolume : pmpyUnitsMap.values()) {
                totalUnitVolume += tempUnitVolume;
            }            
            salesField.setValue(String.valueOf(totalUnitVolume));
        }       
        
    }
    
    public boolean checkSelection() {
        String customer = String.valueOf(customerDDLB.getValue());
        String contract = String.valueOf(contractDDLB.getValue());
        String freqency = String.valueOf(frequencyDDLB.getValue());
        boolean check = true;
        if (StringUtils.EMPTY.equals(customer) || Constant.NULL.equals(customer)) {
            check = false;
            AbstractNotificationUtils.getErrorNotification("No Customer selected", "Please select a Customer");
        } else if (StringUtils.EMPTY.equals(contract) || Constant.NULL.equals(contract)) {
            check = false;
            AbstractNotificationUtils.getErrorNotification("No Contract Selected selected", "Please select a Contract");
        } else if (StringUtils.EMPTY.equals(freqency) || Constant.NULL.equals(freqency)) {
            check = false;
            AbstractNotificationUtils.getErrorNotification("No Frequency selected", "Please select a Frequency");
        } else if (selectedProductsBean.size() == 0) {
            check = false;
            AbstractNotificationUtils.getErrorNotification("No Available Product selected", "Please select an Available Product from the list view");
        }
        return check;
    }
    
    public void resetCalculationFields() {
        
        livesField.setValue(ZERO);
        totalLivesField.setValue(ZERO);
        if (isSalesOrUnits) {
            salesField.setValue(DEFAULT_SALES);
            valuePerLifeField.setValue(DEFAULT_SALES);
            totalSalesField.setValue(DEFAULT_SALES);
            projPeriodTotalField.setValue(DEFAULT_SALES);
            projPeriodTotalField.setData(StringUtils.EMPTY);
        } else {
            salesField.setValue(ZERO);
            valuePerLifeField.setValue(ZERO);
            totalSalesField.setValue(ZERO);
            projPeriodTotalField.setValue(ZERO);
            projPeriodTotalField.setData(StringUtils.EMPTY);
        }
        
    }
    
     public void resetSelectionFields(){
        customerDDLB.setValue(null);
        contractDDLB.setValue(null);
        frequencyDDLB.setValue(null);
        availableProductsBean.removeAllItems();
        selectedProductsBean.removeAllItems();
    }

     /**
     * Export calculated excel.
     */
    private void exportCalculatedExcel() {
        try {
            LOGGER.info("Entering of exportCalculatedExcel method");
            final MPmpyDTO exporterDto = new MPmpyDTO();
            exporterDto.setSales(String.valueOf(SalesUtils.currencyToValue(salesField.getValue())));
            exporterDto.setLives(livesField.getValue());
            exporterDto.setValuePerLife(String.valueOf(SalesUtils.currencyToValue(valuePerLifeField.getValue())));
            exporterDto.setTotalLives(totalLivesField.getValue());
            exporterDto.setTotalSales(String.valueOf(SalesUtils.currencyToValue(totalSalesField.getValue())));
            exporterDto.setProjectionPeriodTotal(String.valueOf(SalesUtils.currencyToValue(projPeriodTotalField.getValue())));
            pmpyLogic.export(exporterDto);
            LOGGER.info("End of exportCalculatedExcel method");
        } catch (SystemException e) {
            LOGGER.error(e.getMessage());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
    
    /**
     * Import button logic.
     *
     * @throws IllegalAccessException the illegal access exception
     * @throws InvocationTargetException the invocation target exception
     * @throws NoSuchMethodException the no such method exception
     */
    public void importButtonLogic() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        LOGGER.info("Entering of importButtonLogic method");
        String tempUpdateValue = String.valueOf(projPeriodTotalField.getData());
        String tempUpdatePeriod = String.valueOf(effectivePeriodDDLB.getValue());
  
        if (StringUtils.isNotBlank(tempUpdateValue) && StringUtils.isNotEmpty(tempUpdateValue)) {
            if (tempUpdatePeriod != null && StringUtils.isNotBlank(tempUpdatePeriod) && StringUtils.isNotEmpty(tempUpdatePeriod)&& !Constant.NULL.equals(tempUpdatePeriod)) {
                this.setUpdateValue(tempUpdateValue.contains("$") ? tempUpdateValue.replace("$", StringUtils.EMPTY) : tempUpdateValue);
                this.setUpdatePeriod(tempUpdatePeriod);
                this.isImport = true;
                this.close();
            } else {
                AbstractNotificationUtils.getErrorNotification("No Projection Period Total", "Please select a Effective Period for IMPORT.");
            }

        } else {
            AbstractNotificationUtils.getErrorNotification("No Projection Period Total", "Please complete the PMPY calculation before clicking IMPORT.");
        }

        LOGGER.info("End of importButtonLogic method");
    }
     
    /**
     * Gets the divided value.
     *
     * @param actualValue the actual value
     * @return the divided value
     */
    public double getDividedValue(final Double actualValue) {
        double returnValue = 0;

        LOGGER.info("Entering of getDividedValue method");
        final DecimalFormat formatter = new DecimalFormat("#.000000");
        if (actualValue != 0) {
            Double temp = actualValue / 3;

            temp = Double.valueOf(formatter.format(temp));
            returnValue = temp.isInfinite() || temp.isNaN() ? 0 : temp;

        }
        LOGGER.info("End of getDividedValue method");

        return returnValue;
    }
     /**
     * To check if any period of the contract history is checked.
     *
     * @return true if any of the check box in contract history header is checked
     */
    public boolean isContractHistorySelected() {    
        for (Object key : chtCheckBoxMap.keySet()) {

            if (chtCheckBoxMap.get(key)) {
                return true;
            }
        }
        return false;
    }    

    public String getUpdateValue() {
        return updateValue;
    }

    public void setUpdateValue(String updateValue) {
        this.updateValue = updateValue;
    }    

    public String getUpdatePeriod() {
        return updatePeriod;
    }

    public void setUpdatePeriod(String updatePeriod) {
        this.updatePeriod = updatePeriod;
    }
        
}
