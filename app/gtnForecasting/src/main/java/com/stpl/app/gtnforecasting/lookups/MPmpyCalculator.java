/*
 * 
 */
package com.stpl.app.gtnforecasting.lookups;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.lookups.dto.MPmpyDTO;
import com.stpl.app.gtnforecasting.lookups.logic.PmpyLogic;
import com.stpl.app.gtnforecasting.salesprojection.utils.HeaderUtils;
import com.stpl.app.gtnforecasting.salesprojection.utils.SalesUtils;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.ui.ForecastUI;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.DASH;
import com.stpl.app.gtnforecasting.utils.MandatedChartUtils;
import com.stpl.app.gtnforecasting.utils.MandatedGraphWindow;
import com.stpl.app.utils.Constants.FrequencyConstants;
import static com.stpl.app.utils.Constants.ResourceConstants.*;
import static com.stpl.app.utils.Constants.WindowMessagesName.RESET_CONFIRMATION;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.event.ItemClickEvent;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.OptionGroup;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.VerticalLayout;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 * The Class MPmpyCalculator.
 * 
 */
public class MPmpyCalculator extends Window {

    /**
     * @return the isSalesOrUnits
     */
    public boolean isIsSalesOrUnits() {
        return isSalesOrUnits;
    }

    /**
     * @return the isImport
     */
    public boolean isIsImport() {
        return isImport;
    }

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MPmpyCalculator.class);

    /**
     * The excel btn.
     */
    @UiField("excelBtn")
    private Button excelBtn;

    /**
     * The excel btn.
     */
    @UiField("nextBtn")
    private Button moveRight;

    /**
     * The excel btn.
     */
    @UiField("previousBtn")
    private Button moveLeft;

    /**
     * The graph btn.
     */
    @UiField("graphBtn")
    private Button graphBtn;

    /**
     * The excel calc btn.
     */
    @UiField("excelCalcBtn")
    private Button excelCalcBtn;

    @UiField("availableProducts")
    private ExtFilterTable availableProductsTable;

    @UiField("selectedProducts")
    private ExtFilterTable selectedProductsTable;

    private ExtFilterTable pmpyTable=new ExtFilterTable();
    
    @UiField("customerDdlb")
    private ComboBox customerDDLB;

    @UiField("contractDdlb")
    private ComboBox contractDDLB;

    @UiField("frequencyDdlb")
    private ComboBox frequencyDDLB;

    @UiField("generateBtn")
    private Button generateBtn;

    @UiField("resetBtn")
    private Button resetBtn;

    @UiField("allBtn")
    private Button allBtn;

    private final BeanItemContainer<MPmpyDTO> availableProductsBean = new BeanItemContainer<>(MPmpyDTO.class);

    private final BeanItemContainer<MPmpyDTO> selectedProductsBean = new BeanItemContainer<>(MPmpyDTO.class);

    /**
     * The excel export image.
     */
    private final Resource excelExportImage = new ThemeResource(EXCEL_IMAGE_PATH.getConstant());

    /**
     * The graph image.
     */
    private final Resource graphImage = new ThemeResource(GRAPH_IMAGE_PATH.getConstant());

    private final PmpyLogic pmpyLogic = new PmpyLogic();

    private final SessionDTO sessionDTO;

    private ExtTreeContainer<MPmpyDTO> pmpyContainer = new ExtTreeContainer<>(MPmpyDTO.class,ExtContainer.DataStructureMode.MAP);

    private Object itemId;
    
    private CustomTableHeaderDTO headerDTO;
    
    @UiField("sales")
    private  TextField salesField;
    
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
    private VerticalLayout tableLayout;
    
    @UiField("closeBtn")
    private Button closeBtn;
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
    
    private boolean isImport = false;
    /**
     * Sales - true 
     * Units - false
     */
    private boolean isSalesOrUnits = true;
    
    private List<MPmpyDTO> pmpyList = new ArrayList<>();
    
    private final Map<String,Double> pmpySalesMap = new HashMap<>();
    private final Map<String,Integer> pmpyUnitsMap = new HashMap<>();
    
    private final DecimalFormat doubleDecimalFormat = new DecimalFormat("#0.00");
    
    private List<String> projectedPeriodList = new ArrayList<>();
    
    @UiField("resetCalcBtn")
    private Button resetCalcBtn;
    
    @UiField("buttonLayout")
    private HorizontalLayout buttonLayout;
    
    @UiField("mainLayout")
    private VerticalLayout mainLayout;
    
    private static final String DEFAULT_SALES = "$0.00";
    
    private static final String ZERO = DASH;
    
    private  static final String TEXT_RIGHT_ALIGN = "txtRightAlign";
    
    private final Map<Object, Boolean> chtCheckBoxMap = new HashMap<>();    
    
    private String updateValue;
    
    private String updatePeriod;
    /**
     * Instantiates a new pmpy calculator.
     * @param sessionDTO
     * @param projectedPeriodList
     * @param salesRowDTO
     */
    public MPmpyCalculator(SessionDTO sessionDTO,List<String> projectedPeriodList) {
        super(Constant.PMPY_CALCULATOR);
        LOGGER.debug("PmpyCalculator Constructor initiated ");
        addStyleName(Constant.BOOTSTRAP_UI);
        addStyleName(Constant.BOOTSTRAP);
        addStyleName(Constant.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        setClosable(true);
        center();
        setModal(true);
        this.sessionDTO = sessionDTO;
        this.projectedPeriodList = projectedPeriodList == null ? projectedPeriodList : new ArrayList<>(projectedPeriodList);        
        init();
        LOGGER.debug("PmpyCalculator Constructor ends ");
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
            availableProductsTable.setVisibleColumns(HeaderUtils.getInstance().getPmpyProductColumns());
            availableProductsTable.setColumnHeaders(HeaderUtils.getInstance().getPmpyProductHeader());
            availableProductsTable.setColumnAlignment(HeaderUtils.getInstance().getPmpyProductColumns()[0], ExtCustomTable.Align.RIGHT);
            availableProductsTable.setFilterBarVisible(true);
            availableProductsTable.setFilterDecorator(new ExtDemoFilterDecorator());
            availableProductsTable.setStyleName(Constant.FILTER_TABLE);

            selectedProductsTable.setContainerDataSource(selectedProductsBean);
            selectedProductsTable.setVisibleColumns(HeaderUtils.getInstance().getPmpyProductColumns());
            selectedProductsTable.setColumnHeaders(HeaderUtils.getInstance().getPmpyProductHeader());
            selectedProductsTable.setColumnAlignment(HeaderUtils.getInstance().getPmpyProductColumns()[0], ExtCustomTable.Align.RIGHT);
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

                @Override
                public void buttonClick(Button.ClickEvent event) {

                    List chartList = new ArrayList();
                    ProjectionSelectionDTO projectionDTO = new ProjectionSelectionDTO();
                    for (Object obj : pmpyTable.getContainerDataSource().getItemIds()) {
                        MPmpyDTO dto = (MPmpyDTO) obj;                        
                            chartList.add(dto);                        
                    }
                    final MandatedChartUtils chart = new MandatedChartUtils(chartList, String.valueOf(frequencyDDLB.getValue()), StringUtils.EMPTY, headerDTO, "PMPYCalculator", projectionDTO);
                    final MandatedGraphWindow pmpyGraphWindow = new MandatedGraphWindow(chart.getChart(), Constant.PMPY_CALCULATOR);
                    UI.getCurrent().addWindow(pmpyGraphWindow);
                    pmpyGraphWindow.focus();

                }
            });
            
            salesOrUnits.addValueChangeListener(new Property.ValueChangeListener() {

                @Override
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

                @Override
                public void buttonClick(Button.ClickEvent event) {
                    calculateSalesOrUnits(salesOrUnits.getValue().toString());
                }
            });            
            
            resetCalcBtn.addClickListener(new Button.ClickListener() {

                @Override
                public void buttonClick(Button.ClickEvent event) {
                    
                    new AbstractNotificationUtils() {
                        @Override
                        public void noMethod() {
                            return;
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

                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    try {
                        loadContractDDLB(Integer.parseInt(String.valueOf(event.getProperty().getValue())));
                        availableProductsBean.removeAllItems();
                        selectedProductsBean.removeAllItems();
                    } catch (PortalException | SystemException | NumberFormatException ex) {
                        LOGGER.error(ex.getMessage());
                    }
                }
            });

            availableProductsTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {

                @Override
                public void itemClick(ItemClickEvent event) {
                    
                    itemId = event.getItemId();
                }
            });

            selectedProductsTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {

                @Override
                public void itemClick(ItemClickEvent event) {
                    
                    itemId = event.getItemId();
                }
            });

            contractDDLB.addValueChangeListener(new Property.ValueChangeListener() {

                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    try {
                        int customerSid = Integer.parseInt(String.valueOf(customerDDLB.getValue()));
                        int contractSid = Integer.parseInt(String.valueOf(contractDDLB.getValue()));
                        availableProductsBean.removeAllItems();
                        availableProductsBean.addAll(loadProductsTable(customerSid, contractSid));
                        selectedProductsBean.removeAllItems();
                    } catch (PortalException | SystemException | NumberFormatException ex) {
                        LOGGER.error(ex.getMessage());
                    }
                }
            });

            allBtn.addClickListener(new Button.ClickListener() {

                @Override
                public void buttonClick(Button.ClickEvent event) {
                    try {
                        moveAllProducts();
                    } catch (PortalException | SystemException ex) {
                        LOGGER.error(ex.getMessage());
                    }

                }
            });

            moveRight.addClickListener(new Button.ClickListener() {

                @Override
                public void buttonClick(Button.ClickEvent event) {
                    moveProductsRight();
                }
            });

            moveLeft.addClickListener(new Button.ClickListener() {

                @Override
                public void buttonClick(Button.ClickEvent event) {
                    moveProductsLeft();
                }
            });

            generateBtn.addClickListener(new Button.ClickListener() {

                @Override
                public void buttonClick(Button.ClickEvent event) {
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
                }
            });
            importBtn.addClickListener(new Button.ClickListener() {
                /**
                 * Default method.
                 */
                @Override
                public void buttonClick(final Button.ClickEvent event) {
                        importButtonLogic();

                }
            });
            livesCalcBtn.addClickListener(new Button.ClickListener() {

                @Override
                public void buttonClick(Button.ClickEvent event) {                    
                    final boolean chValue = isContractHistorySelected();
                    if (chValue) {
                        String valuePerLife ;
                        if(isIsSalesOrUnits()){
                            valuePerLife = SalesUtils.valueToCurrency(calculateValuePerLife(Integer.parseInt(livesField.getValue())), doubleDecimalFormat);  
                        }else{
                            valuePerLife = calculateValuePerLife(Integer.parseInt(livesField.getValue()));  
                        }                    
                    valuePerLifeField.setValue(valuePerLife);
                    } else {
                        AbstractNotificationUtils.getErrorNotification("No Periods Selected",
                                "Please select at least one time period along with either ‘Actuals’ or ‘Projections’.");
                    }
                   
                }
            });
            
            totLivesCalcBtn.addClickListener(new Button.ClickListener() {

                @Override
                public void buttonClick(Button.ClickEvent event) {

                    String tempVPL = isIsSalesOrUnits() ? String.valueOf(SalesUtils.currencyToValue(valuePerLifeField.getValue())) : valuePerLifeField.getValue();
                    int valuePerlife = Double.valueOf(tempVPL).intValue();
                    String totalSales = calculateTotalSales(valuePerlife, Integer.parseInt(totalLivesField.getValue()));
                    totalSalesField.setValue(isIsSalesOrUnits() ? SalesUtils.valueToCurrency(totalSales, doubleDecimalFormat) : totalSales);

                    int totalSalesAmount = Double.valueOf(totalSales).intValue();
                    int projectionPeriodTotal = calculateProjectionPeriodTotal(totalSalesAmount, String.valueOf(frequencyDDLB.getValue()));
                    projPeriodTotalField.setValue(isIsSalesOrUnits() ? SalesUtils.valueToCurrency(String.valueOf(projectionPeriodTotal), doubleDecimalFormat) : String.valueOf(projectionPeriodTotal));
                    projPeriodTotalField.setData(projPeriodTotalField.getValue());
                }
            });
            
            closeBtn.addClickListener(new Button.ClickListener() {

                @Override
                public void buttonClick(Button.ClickEvent event) {
                    String messageHeader = "Close Confirmation";
                    String messageBody = "Are you sure you want to Close the Worksheet?";

                    new AbstractNotificationUtils() {
                        @Override
                        public void noMethod() {
                            return;
                        }

                        @Override
                        public void yesMethod() {
                             close();
                        }
                    }.getConfirmationMessage(messageHeader, messageBody);
                  
                }
            });
            
            livesField.addValueChangeListener(new Property.ValueChangeListener() {

                @Override
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

                @Override
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

                @Override
                public void buttonClick(Button.ClickEvent event) {
                    String messageHeader = RESET_CONFIRMATION.getConstant();
                    String messageBody = "Are you sure you want to reset the page to default/previous values?";

                    new AbstractNotificationUtils() {
                        @Override
                        public void noMethod() {
                            return;
                        }

                        @Override
                        public void yesMethod() {
                            resetSelectionFields();
                        }
                    }.getConfirmationMessage(messageHeader, messageBody);

                }
            });
            
            excelCalcBtn.addClickListener(new Button.ClickListener() {

                @Override
                public void buttonClick(Button.ClickEvent event) {
                    exportCalculatedExcel();
                }
            });
            
            excelBtn.addClickListener(new Button.ClickListener() {

                @Override
                public void buttonClick(Button.ClickEvent event) {

                        ExtFilterTable excelFilterTable = new ExtFilterTable();
                        excelFilterTable.setVisible(false);
                        tableLayout.addComponent(excelFilterTable);
                        final ExtTreeContainer<MPmpyDTO> tempPMPYContainer = new ExtTreeContainer<>(MPmpyDTO.class,ExtContainer.DataStructureMode.MAP);
                        
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
                        excelFilterTable.setContainerDataSource(tempPMPYContainer);
                        
                        excelFilterTable.setVisibleColumns(excelHeaderDTO.getSingleColumns().toArray());
                        excelFilterTable.setColumnHeaders(excelHeaderDTO.getSingleHeaders().toArray(new String[excelHeaderDTO.getSingleHeaders().size()]));
                        ForecastUI.setEXCEL_CLOSE(true);
                        ExcelExport export = new ExcelExport(new ExtCustomTableHolder(excelFilterTable), Constant.PMPY_CALCULATOR, Constant.PMPY_CALCULATOR, "PMPYCalculator.xls", false);
                        export.export();
                        tableLayout.removeComponent(excelFilterTable);

                    
                }
            });
           
        } catch (PortalException | SystemException | Property.ReadOnlyException | UnsupportedOperationException ex) {            
            LOGGER.error(ex.getMessage());
        }

    }

    public void loadCustomerDDLB() throws PortalException, SystemException  {
        customerDDLB.setImmediate(true);
        customerDDLB.addItem(Constant.SELECT_ONE);
        customerDDLB.setNullSelectionAllowed(true);
        customerDDLB.setNullSelectionItemId(Constant.SELECT_ONE);
        List list = pmpyLogic.loadCustomer();
        for (int i = 0; i < list.size(); i++) {
            Object[] obj = (Object[]) list.get(i);            
            customerDDLB.addItem(String.valueOf(obj[0]));
            customerDDLB.setItemCaption(String.valueOf(obj[0]), String.valueOf(obj[1]));
        }
    }

    public void loadContractDDLB(int customerSid) throws PortalException, SystemException {
        contractDDLB.removeAllItems();
        contractDDLB.addItem(Constant.SELECT_ONE);
        contractDDLB.setNullSelectionAllowed(true);
        contractDDLB.setNullSelectionItemId(Constant.SELECT_ONE);
        List list = pmpyLogic.loadContract(customerSid);
        for (int i = 0; i < list.size(); i++) {
            Object[] obj = (Object[]) list.get(i);            
            contractDDLB.addItem(String.valueOf(obj[0]));
            contractDDLB.setItemCaption(String.valueOf(obj[0]), String.valueOf(obj[1]));
        }
    }

    public List<MPmpyDTO> loadProductsTable(int customerSid, int contractSid) throws PortalException, SystemException  {
        List list = pmpyLogic.loadProduct( customerSid, contractSid);
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

    public void moveAllProducts() throws PortalException, SystemException {
        
        int customerSid = Integer.parseInt(String.valueOf(customerDDLB.getValue()));
        int contractSid = Integer.parseInt(String.valueOf(contractDDLB.getValue()));

        if (availableProductsBean.size() == 0) {
            if (selectedProductsBean.size() != 0) {
                availableProductsBean.addAll(loadProductsTable(customerSid, contractSid));
                selectedProductsBean.removeAllItems();
            }
        } else if ((selectedProductsBean.size() == 0) && (availableProductsBean.size() != 0)) {
                selectedProductsBean.addAll(loadProductsTable(customerSid, contractSid));
                availableProductsBean.removeAllItems();
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
        frequencyRange=NumericConstants.SIX;
        }
        else if(FrequencyConstants.ANNUALLY.getConstant().equalsIgnoreCase(frequency)){
        frequencyRange=NumericConstants.THREE;
        }
        else if(FrequencyConstants.QUARTERLY.getConstant().equalsIgnoreCase(frequency)){
           frequencyRange=NumericConstants.TWELVE; 
        }
        else if(FrequencyConstants.MONTHLY.getConstant().equalsIgnoreCase(frequency)){
           frequencyRange=NumericConstants.THIRTY_SIX; 
        }

        headerDTO = HeaderUtils.getPMPYTableColumns(frequency, frequencyRange,false);
       
      
        pmpyTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        pmpyTable.setWidth("800px");
        pmpyTable.setPageLength(NumericConstants.FIVE);
        
        pmpyContainer = new ExtTreeContainer<>(MPmpyDTO.class,ExtContainer.DataStructureMode.MAP);
        pmpyContainer.setColumnProperties(headerDTO.getProperties());        
        pmpyTable.setContainerDataSource(pmpyContainer);
        
        pmpyTable.setVisibleColumns(headerDTO.getSingleColumns().toArray());
        pmpyTable.setColumnHeaders(headerDTO.getSingleHeaders().toArray(new String[headerDTO.getSingleHeaders().size()]));
        
        for(Object visibleColumns:headerDTO.getSingleColumns()){
        pmpyTable.setColumnWidth(visibleColumns,NumericConstants.ONE_FIVE_ZERO);
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

            @Override
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

            @Override
            public void doubleHeaderColumnRadioCheck(ExtCustomTable.DoubleHeaderColumnRadioCheckEvent event) {
                
                if(pmpyTable.getColumnCheckBox(event.getRadioButtonName())){
                    
                    String key = event.getRadioButtonName();                                                    
                    getDataForCalculation(key, false);
                }
            }
        });
        
        pmpyTable.addTripleHeaderColumnRadioCheckListener(new ExtCustomTable.TripleHeaderColumnRadioCheckListener() {

            @Override
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

        if (isIsSalesOrUnits()) {
            Double tempSalesAmount = (Double) SalesUtils.currencyToValue(salesField.getValue());
            int salesAmount = tempSalesAmount.intValue();
            if (lives != 0 && salesAmount != 0) {
                valuePerLife = tempSalesAmount / lives;
            }
        } else {
            int salesAmount = Integer.parseInt(salesField.getValue());
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
                 projectionPeriodTotal = totalSales/NumericConstants.FOUR;
             }else if(FrequencyConstants.SEMI_ANNUALLY.getConstant().equals(frequency)){
                 projectionPeriodTotal = totalSales/NumericConstants.TWO;
             }else if(FrequencyConstants.MONTHLY.getConstant().equals(frequency)){
                 projectionPeriodTotal = totalSales * NumericConstants.THREE;
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
            targetItem = new BeanItem<>(
                    (MPmpyDTO) obj);
        }

        return (MPmpyDTO) targetItem.getBean();
    }

    public void loadPMPYTable(boolean isExcelExport, ExtTreeContainer<MPmpyDTO> excelContainer)  {

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
            excelContainer.addAll(pmpyLogic.convertPMPYResults(list, String.valueOf(frequencyDDLB.getValue())));
        } else {
            pmpyContainer.addAll(pmpyLogic.convertPMPYResults(list, String.valueOf(frequencyDDLB.getValue())));
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
        pmpyList = pmpyContainer.getBeans();
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

                String tempUnitsValue = String.valueOf(pmpyList.get(NumericConstants.TWO).getPropertyValue(propertyId));
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

                String tempUnitsValue = String.valueOf(pmpyList.get(NumericConstants.THREE).getPropertyValue(propertyId));
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
        if (isIsSalesOrUnits()) {
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
            LOGGER.debug("Entering of exportCalculatedExcel method");
            final MPmpyDTO exporterDto = new MPmpyDTO();
            exporterDto.setSales(String.valueOf(SalesUtils.currencyToValue(salesField.getValue())));
            exporterDto.setLives(livesField.getValue());
            exporterDto.setValuePerLife(String.valueOf(SalesUtils.currencyToValue(valuePerLifeField.getValue())));
            exporterDto.setTotalLives(totalLivesField.getValue());
            exporterDto.setTotalSales(String.valueOf(SalesUtils.currencyToValue(totalSalesField.getValue())));
            exporterDto.setProjectionPeriodTotal(String.valueOf(SalesUtils.currencyToValue(projPeriodTotalField.getValue())));
            pmpyLogic.export(exporterDto);
            LOGGER.debug("End of exportCalculatedExcel method");
        }  catch (Exception e) {
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
    public void importButtonLogic()  {

        LOGGER.debug("Entering of importButtonLogic method");
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

        LOGGER.debug("End of importButtonLogic method");
    }
     
    /**
     * Gets the divided value.
     *
     * @param actualValue the actual value
     * @return the divided value
     */
    public double getDividedValue(final Double actualValue) {
        double returnValue = 0;

        LOGGER.debug("Entering of getDividedValue method");
        final DecimalFormat formatter = new DecimalFormat("#.000000");
        if (actualValue != 0) {
            Double temp = actualValue / NumericConstants.THREE;

            temp = Double.valueOf(formatter.format(temp));
            returnValue = temp.isInfinite() || temp.isNaN() ? 0 : temp;

        }
        LOGGER.debug("End of getDividedValue method");

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
