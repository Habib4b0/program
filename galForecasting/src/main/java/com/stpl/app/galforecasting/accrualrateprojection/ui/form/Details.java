/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.accrualrateprojection.ui.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.galforecasting.accrualrateprojection.dto.AccrualRateProjectionDTO;
import com.stpl.app.galforecasting.accrualrateprojection.logic.DSLogic;
import com.stpl.app.galforecasting.accrualrateprojection.dto.AccrualRateSelectionDTO;
import com.stpl.app.galforecasting.accrualrateprojection.logic.AccrualRateProjectionLogic;
import com.stpl.app.galforecasting.accrualrateprojection.logic.tablelogic.AccrualRateProjectionTableLogic;
import com.stpl.app.galforecasting.accrualrateprojection.utils.AccrualRateUtils;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.galforecasting.ui.ForecastUI;
import com.stpl.app.galforecasting.utils.Constant;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.vaadin.data.Property;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.asi.ui.custommenubar.CustomMenuBar.CustomMenuItem;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 * Class used to build and configure the Details Tab.
 * 
 * @author sibi
 */
public class Details extends CustomComponent {

    private static final Logger LOGGER = Logger.getLogger(Details.class);

    @UiField("frequencyDdlb")
    private ComboBox frequencyDdlb;

    @UiField("variablesMenuBar")
    protected CustomMenuBar variablesMenuBar;

    @UiField("brandDdlb")
    private ComboBox brandDdlb;

    @UiField("historyDdlb")
    private ComboBox historyDdlb;

    @UiField("customerDdlb")
    private ComboBox customerDdlb;

    @UiField("productDdlb")
    private ComboBox productDdlb;

    @UiField("fromDdlb")
    private ComboBox fromDdlb;

    @UiField("toDdlb")
    private ComboBox toDdlb;

    @UiField("resetBtn")
    private Button resetBtn;

    @UiField("generateBtn")
    private Button generateBtn;

    @UiField("tableVerticalLayout")
    private VerticalLayout tableVerticalLayout;

    private CustomMenuBar.CustomMenuItem customMenuItem;

    private AccrualRateProjectionTableLogic tableLogic = new AccrualRateProjectionTableLogic();

    private ExtContainer<AccrualRateProjectionDTO> resultBeanContainer;

    private FreezePagedTable table = new FreezePagedTable(tableLogic);

    private CustomTableHeaderDTO rightHeaderDTO;

    private CustomTableHeaderDTO leftHeaderDTO;
    
    SessionDTO session;
    
    DSLogic dsLogic=new DSLogic();

    private final AccrualRateSelectionDTO accrualRateSelectionDTO = new AccrualRateSelectionDTO();

    private final AccrualRateProjectionLogic accrualRateProjectionLogic = AccrualRateProjectionLogic.getInstance();
    
    public static ResourceBundle alertMsg = ResourceBundle.getBundle("properties.alertmessage");

    private boolean isFilterLoadNeeded = true;
    
    private final Map<String,String> map;


    public Details(final SessionDTO sessionDTO, final Map<String, String> map) {
        accrualRateProjectionLogic.setValuesInDTO(sessionDTO, accrualRateSelectionDTO);
        this.map = map;
        this.session=sessionDTO;        
        init();
    }

    /**
     * Methods loads the UI Components from xml and configures the components.
     */
    private void init() {
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/accrualrateprojection/details.xml"), this));
        configureFields();
        initializeResultTable();
        if (AccrualRateUtils.EDIT.equalsIgnoreCase(session.getAction()) || AccrualRateUtils.VIEW.equalsIgnoreCase(session.getAction())) {
            configureOnEditOrView();
            configureTable();
            loadGridOnEdit();
        }else{
            configureTable();
        }
        
        
    }

    /**
     * Method configures the fields and loads the values.
     */
    private void configureFields() {

        frequencyDdlb.setImmediate(true);
        frequencyDdlb.addItem(Constant.MONTHLY);
        frequencyDdlb.setNullSelectionAllowed(false);
        frequencyDdlb.select(Constant.MONTHLY);
        frequencyDdlb.setReadOnly(true);

        historyDdlb.setImmediate(true);
        historyDdlb.addItems(AccrualRateUtils.HISTORY_PERIODS);
        historyDdlb.setNullSelectionAllowed(true);
        historyDdlb.setNullSelectionItemId(Constant.SELECT_ONE);
        historyDdlb.select(null);

        String[] variables = AccrualRateUtils.DetailsVariables.toArray();
        customMenuItem = variablesMenuBar.addItem(AccrualRateUtils.SELECT_VARIABLES, null);
        CustomMenuBar.CustomMenuItem[] customItem = new CustomMenuBar.CustomMenuItem[variables.length];
        for (int i = 0; i < variables.length; i++) {
            customItem[i] = customMenuItem.addItem(variables[i].trim(), null);
            customItem[i].setCheckable(true);
            customItem[i].setItemClickable(true);
            customItem[i].setItemClickNotClosable(true);
            if (i == 0) {
                customItem[i].setCheckAll(true);
            }
        }

        loadDDLB(customerDdlb, AccrualRateUtils.CUSTOMER, null);
        loadDDLB(brandDdlb, AccrualRateUtils.BRAND, null);
        loadDDLB(productDdlb, AccrualRateUtils.PRODUCT, null);

        productDdlb.setImmediate(true);
        productDdlb.addItem(Constant.SELECT_ONE);
        productDdlb.addItem(AccrualRateUtils.ALL_PRODUCTS);
        productDdlb.setNullSelectionAllowed(true);
        productDdlb.setNullSelectionItemId(Constant.SELECT_ONE);
        productDdlb.select(null);
        
        addListenersToDDLB();

    }

    /**
     * Method used to configure the table and load headers.
     */
    private void configureTable() {
        
        ExtPagedTable leftTable = table.getLeftFreezeAsTable();
        ExtPagedTable rightTable = table.getRightFreezeAsTable();
        rightHeaderDTO = new CustomTableHeaderDTO();
        leftHeaderDTO = new CustomTableHeaderDTO();
        AccrualRateUtils.getRightHeadersForAccrual(rightHeaderDTO, historyDdlb.getValue(), accrualRateSelectionDTO,AccrualRateUtils.DETAILS, null);
        AccrualRateUtils.getLeftHeadersForDetails(leftHeaderDTO);

        rightTable.setSortEnabled(false);
        leftTable.setSortEnabled(false);
        tableLogic.sinkItemPerPageWithPageLength(false);
        tableLogic.setPageLength(10);
        resultBeanContainer = new ExtContainer<>(AccrualRateProjectionDTO.class, ExtContainer.DataStructureMode.LIST);
        tableLogic.setContainerDataSource(resultBeanContainer);
        resultBeanContainer.setRecordHeader(rightHeaderDTO.getSingleColumns());
        resultBeanContainer.setColumnProperties(rightHeaderDTO.getProperties());        
        leftTable.setVisibleColumns(leftHeaderDTO.getSingleColumns().toArray());
        leftTable.setColumnHeaders(leftHeaderDTO.getSingleHeaders().toArray(new String[leftHeaderDTO.getSingleHeaders().size()]));
        leftTable.setDoubleHeaderVisibleColumns(leftHeaderDTO.getDoubleColumns().toArray());
        leftTable.setDoubleHeaderColumnHeaders(leftHeaderDTO.getDoubleHeaders().toArray(new String[leftHeaderDTO.getDoubleHeaders().size()]));
        leftTable.setDoubleHeaderMap(leftHeaderDTO.getDoubleHeaderMaps());

        rightTable.setVisibleColumns(rightHeaderDTO.getSingleColumns().toArray());
        rightTable.setColumnHeaders(rightHeaderDTO.getSingleHeaders().toArray(new String[rightHeaderDTO.getSingleHeaders().size()]));
        rightTable.setDoubleHeaderVisibleColumns(rightHeaderDTO.getDoubleColumns().toArray());
        rightTable.setDoubleHeaderColumnHeaders(rightHeaderDTO.getDoubleHeaders().toArray(new String[rightHeaderDTO.getDoubleHeaders().size()]));
        rightTable.setDoubleHeaderMap(rightHeaderDTO.getDoubleHeaderMaps());
        for (Object propertyId : rightTable.getVisibleColumns()) {
            rightTable.setColumnAlignment(propertyId, ExtCustomTable.Align.RIGHT);
        }
        table.setDoubleHeaderMap(leftHeaderDTO.getDoubleHeaderMaps(), rightHeaderDTO.getDoubleHeaderMaps());
        table.setDoubleHeaderVisible(true);
        loadFromAndToPeriods();
    }

    /**
     * Adds value change listeners to Customer and BrandDDLB.
     */
    private void addListenersToDDLB() {

        customerDdlb.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                loadDDLB(brandDdlb, AccrualRateUtils.BRAND, event.getProperty().getValue());
            }
        });

        brandDdlb.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                loadDDLB(productDdlb, AccrualRateUtils.PRODUCT, event.getProperty().getValue());
            }
        });

    }

    /**
     * Method loads the Customer,Brand and Product DDLB based on other values.
     *
     * @param comboBox
     * @param ddlbName
     * @param filterValue
     */
    private void loadDDLB(final ComboBox comboBox, final String ddlbName, final Object filterValue) {

        comboBox.removeAllItems();
        comboBox.setImmediate(true);
        switch (ddlbName) {
            case AccrualRateUtils.CUSTOMER:
                comboBox.addItem(AccrualRateUtils.ALL_CUSTOMERS);
                comboBox.setNullSelectionAllowed(true);
                comboBox.setNullSelectionItemId(AccrualRateUtils.ALL_CUSTOMERS);
                accrualRateProjectionLogic.loadCustomers(comboBox, accrualRateSelectionDTO.getProjectionId());
                comboBox.select(null);
                break;
            case AccrualRateUtils.BRAND:
                comboBox.addItem(AccrualRateUtils.ALL_BRANDS);
                comboBox.setNullSelectionAllowed(true);
                comboBox.setNullSelectionItemId(AccrualRateUtils.ALL_BRANDS);
                accrualRateProjectionLogic.loadBrands(comboBox, filterValue, accrualRateSelectionDTO.getProjectionId());
                comboBox.select(null);
                break;
            case AccrualRateUtils.PRODUCT:
                comboBox.addItem(Constant.SELECT_ONE);
                comboBox.addItem(AccrualRateUtils.ALL_PRODUCTS);
                comboBox.setNullSelectionAllowed(true);
                comboBox.setNullSelectionItemId(Constant.SELECT_ONE);
                accrualRateProjectionLogic.loadProducts(comboBox, customerDdlb.getValue(), filterValue, accrualRateSelectionDTO.getProjectionId());
                comboBox.select(null);
                break;
        }

    }

    /**
     * Click Listener for reset button.
     *
     * @param event
     */
    @UiHandler("resetBtn")
    public void resetBtnLogic(Button.ClickEvent event) {
        LOGGER.info("Inside Reset Click Listener");
        new AbstractNotificationUtils() {
            @Override
            public void noMethod() {

            }

            @Override
            public void yesMethod() {
                historyDdlb.select(null);
                customerDdlb.select(null);
                brandDdlb.select(null);
                productDdlb.select(null);
                for(CustomMenuItem item : customMenuItem.getChildren()){
                    item.setChecked(false);
                }                
                fromDdlb.select(null);
                toDdlb.select(null);
            }
        }.getOkCancelMessage(AccrualRateUtils.RESET_CONFIRMATION, alertMsg.getString("ACR_MSG_ID_04"));
    }

    /**
     * Click Listener for generate button.
     *
     * @param event
     */
    @UiHandler("generateBtn")
    public void generateBtnLogic(Button.ClickEvent event) {
        LOGGER.info("Inside Generate Click Listener");
        try {
            boolean isNotValidFilter = accrualRateProjectionLogic.checkInvalidFromToPeriods(fromDdlb.getValue(), toDdlb.getValue(), accrualRateSelectionDTO);
            AccrualRateUtils.getCurrentCheckValue(accrualRateSelectionDTO.getVariableList(), customMenuItem, AccrualRateUtils.SALES);
            if (isNotValidFilter) {
                AbstractNotificationUtils.getErrorNotification("Time Period conflict", alertMsg.getString("ACR_MSG_ID_06"));
            } else if (accrualRateSelectionDTO.getVariableList().isEmpty()) {
                AbstractNotificationUtils.getErrorNotification("Not all Required Fields are Populated", alertMsg.getString("ACR_MSG_ID_05"));
            } else {
                if (AccrualRateUtils.EDIT.equalsIgnoreCase(session.getAction()) && !session.isFileNotChanged()
                        && !session.isNewFileCalculationNeeded() && !map.containsKey(Constant.IS_SALES_GENERATED) && !map.containsKey(Constant.IS_RATES_GENERATED)) {
                    AbstractNotificationUtils.getInfoNotification("Error", alertMsg.getString("ACR_MSG_ID_09"));
                    return;
                } else if (AccrualRateUtils.EDIT.equalsIgnoreCase(session.getAction()) || AccrualRateUtils.VIEW.equalsIgnoreCase(session.getAction())) {
                    if (!session.isFileNotChanged() && !session.isNewFileCalculationNeeded()) {
                        AbstractNotificationUtils.getInfoNotification("Confirmation", alertMsg.getString("ACR_MSG_ID_07"));
                    }
                }  
                accrualRateSelectionDTO.setPeriodBasis(map.containsKey("Period Basis") ? map.get("Period Basis") : StringUtils.EMPTY);
                accrualRateSelectionDTO.setRateBasis(map.containsKey("Rate Basis") ? map.get("Rate Basis") : StringUtils.EMPTY);
                accrualRateSelectionDTO.setIsFilterValid(!isNotValidFilter && StringUtils.isNotBlank((String) fromDdlb.getValue()));
                tableVerticalLayout.removeAllComponents();
                tableLogic = new AccrualRateProjectionTableLogic();
                table = new FreezePagedTable(tableLogic);
                tableLogic.setRequiredCount(false);
                configureTable();
                initializeResultTable();
                if (!AccrualRateUtils.VIEW.equalsIgnoreCase(session.getAction())) {
                    callDetailsProcedure();
                }
                generateLogic();
                loadFromAndToPeriods();
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    /**
     * Sets the Initial Configuration for the table.
     */
    protected void initializeResultTable() {
        table.markAsDirty();
        table.setDoubleHeaderVisible(true);
        table.setSelectable(false);
        table.setImmediate(true);
        table.setSplitPosition(AccrualRateUtils.SPLIT_POSITION, Sizeable.Unit.PIXELS);
        table.setMinSplitPosition(AccrualRateUtils.MIN_SPLIT_POSITION, Sizeable.Unit.PIXELS);
        table.setMaxSplitPosition(AccrualRateUtils.MAX_SPLIT_POSITION, Sizeable.Unit.PIXELS);
        table.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        addResultTable();
    }

    /**
     * Adds the table and pagination controls to the layout.
     */
    private void addResultTable() {
        tableVerticalLayout.addComponent(table);
        tableVerticalLayout.addComponent(tableLogic.createControls());
    }

    /**
     * Method used to trigger the table logic to retrieve the data from data
     * base.
     */
    private void generateLogic() {         
        
        accrualRateSelectionDTO.setCustomer(customerDdlb.getValue());
        accrualRateSelectionDTO.setBrand(brandDdlb.getValue());
        accrualRateSelectionDTO.setProduct(productDdlb.getValue());
        tableLogic.setRequiredData(AccrualRateUtils.DETAILS, accrualRateSelectionDTO, null);
    }

    /**
     * Loads the From and To Period DDLB based on the selection in Details tab.
     */
    private void loadFromAndToPeriods() {
        if (isFilterLoadNeeded) {
            fromDdlb.removeAllItems();
            fromDdlb.setImmediate(true);
            fromDdlb.addItem(Constant.SELECT_ONE);
            fromDdlb.setNullSelectionAllowed(true);
            fromDdlb.setNullSelectionItemId(Constant.SELECT_ONE);

            toDdlb.removeAllItems();
            toDdlb.setImmediate(true);
            toDdlb.addItem(Constant.SELECT_ONE);
            toDdlb.setNullSelectionAllowed(true);
            toDdlb.setNullSelectionItemId(Constant.SELECT_ONE);

            for (int i = 0; i < rightHeaderDTO.getDoubleColumns().size(); i++) {
                Object object = rightHeaderDTO.getDoubleColumns().get(i);
                if (accrualRateSelectionDTO.getFilterList().contains(object)) {
                    fromDdlb.addItem(object);
                    fromDdlb.setItemCaption(object, rightHeaderDTO.getDoubleHeaders().get(i));

                    toDdlb.addItem(object);
                    toDdlb.setItemCaption(object, rightHeaderDTO.getDoubleHeaders().get(i));
                }
            }
            isFilterLoadNeeded = false;
        }

    }

    /**
     * Method used to export the data in the list view to excel.
     *
     * @param event
     */
    @UiHandler("excelBtn")
    public void excelBtnLogic(Button.ClickEvent event) {
        try {
            final ExtCustomTable excelTable = new ExtCustomTable();
            final ExtContainer<AccrualRateProjectionDTO> excelContainer = new ExtContainer<>(AccrualRateProjectionDTO.class, ExtContainer.DataStructureMode.LIST);
            configureAndLoadDataForExcel(excelTable, excelContainer);
            if (excelTable.size() > 0) {
                ForecastUI.EXCEL_CLOSE=true;
                ExcelExport exp = new ExcelExport(new ExtCustomTableHolder(excelTable), AccrualRateUtils.DETAILS, AccrualRateUtils.DETAILS, "Details.xls", false);
                exp.export();
            }
            tableVerticalLayout.removeComponent(excelTable);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());            
        }
    }

    /**
     * Method used to load the data for excel export.
     *
     * @param excelTable
     * @param excelContainer
     */
    public void configureAndLoadDataForExcel(final ExtCustomTable excelTable, final ExtContainer<AccrualRateProjectionDTO> excelContainer) {

        excelTable.setImmediate(true);
        excelTable.setVisible(false);
        tableVerticalLayout.addComponent(excelTable);

        final CustomTableHeaderDTO excelHeaderDTO = new CustomTableHeaderDTO();
        AccrualRateUtils.getLeftHeadersForDetails(excelHeaderDTO);
        AccrualRateUtils.getRightHeadersForAccrual(excelHeaderDTO, historyDdlb.getValue(), accrualRateSelectionDTO,AccrualRateUtils.DETAILS,null);        
        
        List singleVisibleColumns = new ArrayList(excelHeaderDTO.getSingleColumns());        
        List<String> singleClumnHeaders = new ArrayList(excelHeaderDTO.getSingleHeaders());
        List doubleVisibleColumns = new ArrayList(excelHeaderDTO.getDoubleColumns());
        List<String> doubleColumnHeaders = new ArrayList(excelHeaderDTO.getDoubleHeaders());        
        excelContainer.setRecordHeader(excelHeaderDTO.getSingleColumns());
        excelContainer.setColumnProperties(excelHeaderDTO.getProperties());        
        excelTable.setContainerDataSource(excelContainer);
                
        excelTable.setVisibleColumns(singleVisibleColumns.toArray());
        excelTable.setColumnHeaders(singleClumnHeaders.toArray(new String[excelHeaderDTO.getSingleHeaders().size()]));
        excelTable.setDoubleHeaderVisibleColumns(doubleVisibleColumns.toArray());
        excelTable.setDoubleHeaderColumnHeaders(doubleColumnHeaders.toArray(new String[excelHeaderDTO.getDoubleHeaders().size()]));
        excelTable.setDoubleHeaderMap(excelHeaderDTO.getDoubleHeaderMaps());
        excelTable.setDoubleHeaderVisible(true);
        excelContainer.addAll(accrualRateProjectionLogic.getDataForDetails(accrualRateSelectionDTO,0,0,true));
    }
    
     public void saveTabSelection() {
        Map map = new HashMap();
        String actionValue = StringUtils.EMPTY;
        map.put(Constant.FREQUENCY_SMALL, String.valueOf(frequencyDdlb.getValue()));
        map.put(Constant.HISTORY_CAPS, String.valueOf(historyDdlb.getValue()));
        map.put(Constant.CUSTOMER_SMALL, String.valueOf(customerDdlb.getValue()));
        map.put(Constant.BRAND_CAPS, String.valueOf(brandDdlb.getValue()));
        map.put(Constant.PRODUCT, String.valueOf(productDdlb.getValue()));
        map.put("FromDDLB", String.valueOf(fromDdlb.getValue()));
        map.put("TODDLB", String.valueOf(toDdlb.getValue()));
        String varValue = StringUtils.EMPTY;
        actionValue = (Constant.EDIT_CAPS.equalsIgnoreCase(session.getAction()) || Constant.VIEW_CAPS.equalsIgnoreCase(session.getAction())) ? "Update" : "Save";
        
        for (String variables : accrualRateSelectionDTO.getVariableList()) {
            if (StringUtils.EMPTY.equals(varValue)) {
                varValue += variables;
            } else {                
                varValue += "," + variables;
            }
        }
        map.put(Constant.VARIABLES, varValue);
        dsLogic.saveScreenSelection(session.getProjectionId(), map, "Details", actionValue);
    }
     
    private void configureOnEditOrView() {
        Map<Object, Object> map=null;
        
        map=dsLogic.getProjectionSelection(session.getProjectionId(),"Details");
        if (map != null && !map.isEmpty()) {
            Object value = map.get(Constant.FREQUENCY_SMALL);
            if (value != null) {
                frequencyDdlb.setValue(map.get(Constant.FREQUENCY_SMALL));
                frequencyDdlb.setImmediate(true);
            }
            value = map.get(Constant.HISTORY_CAPS);
            if (value != null) {
                historyDdlb.setValue(String.valueOf(value));
                historyDdlb.setImmediate(true);
            }
            value = map.get(Constant.CUSTOMER_SMALL);
            if (value != null) {
                customerDdlb.setValue(String.valueOf(value));
            }
            value = map.get(Constant.PRODUCT);
            if (value != null) {
                productDdlb.setValue(String.valueOf(value));
            }
            value = map.get(Constant.BRAND_CAPS);
            if (value != null) {
                brandDdlb.setValue(String.valueOf(value));
            }
           
            value = map.get(Constant.VARIABLES);
            if (value != null) {
                String val = value.toString();
                
                final String[] col = val.split(",");
                for (int i = 0; i < col.length; i++) {                    
                    if (i == Constant.ZERO) {
                        for (CustomMenuBar.CustomMenuItem string : customMenuItem.getChildren()) {
                            if (string.getText().equals(String.valueOf(col[i]).trim())) {
                                string.setChecked(true);
                            }
                        }
                    } else {
                        for (CustomMenuBar.CustomMenuItem string : customMenuItem.getChildren()) {
                            if (string.getText().equals(col[i])) {
                                string.setChecked(true);
                            }
                        }
                    }
                }
            }
             value = map.get("FromDDLB");
             if (value != null) {
                fromDdlb.setValue(String.valueOf(value));
            }
             value = map.get("TODDLB");
             if (value != null) {
                toDdlb.setValue(String.valueOf(value));
            }

        }
        AccrualRateUtils.getCurrentCheckValue(accrualRateSelectionDTO.getVariableList(), customMenuItem, AccrualRateUtils.SALES);        
     }  

    public void callDetailsProcedure() {
        final Object[] parameters = {accrualRateSelectionDTO.getProjectionId(), map.get("Period Basis"), accrualRateSelectionDTO.getUserId(), accrualRateSelectionDTO.getSessionId()};
        accrualRateProjectionLogic.callARPProcedure(parameters, "PRC_ACCRUAL_DETAILS");
    }    
    
    private void loadGridOnEdit() {
        if (session.isFileNotChanged()) {
            generateLogic();
        } else {
            if (session.isNewFileCalculationNeeded()) {
                callDetailsProcedure();
                generateLogic();
            } else {
                generateLogic();
            }
        }
        }

}
