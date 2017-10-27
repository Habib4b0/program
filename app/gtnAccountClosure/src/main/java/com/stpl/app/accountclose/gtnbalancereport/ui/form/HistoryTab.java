/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.gtnbalancereport.ui.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.accountclose.common.CommonLogic;
import com.stpl.app.accountclose.gtnbalancereport.dto.BalanceReportDTO;
import com.stpl.app.accountclose.gtnbalancereport.dto.HistoryTabDTO;
import com.stpl.app.accountclose.gtnbalancereport.logic.HistoryTabLogic;
import com.stpl.app.accountclose.gtnbalancereport.logic.tableLogic.BalanceReportLogic;
import com.stpl.app.accountclose.gtnbalancereport.logic.tableLogic.HistoryTabTableLogic;
import com.stpl.app.accountclose.gtnbalancereport.utils.BRCommonUtils;
import static com.stpl.app.accountclose.gtnbalancereport.utils.Constants.VariableConstants.BRAND;
import static com.stpl.app.accountclose.gtnbalancereport.utils.Constants.VariableConstants.CUSTOMER;
import static com.stpl.app.accountclose.gtnbalancereport.utils.Constants.VariableConstants.ITEM;
import com.stpl.app.accountclose.gtnbalancereport.utils.HeaderUtils;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import com.stpl.app.accountclose.utils.AbstractNotificationUtils;
import com.stpl.app.accountclose.utils.Constants;
import static com.stpl.app.accountclose.utils.Constants.ResourceConstants.EXCEL_IMAGE_PATH;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.Property;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author santanukumar
 */
public class HistoryTab extends CustomComponent{
    
    public static final Logger LOGGER = Logger.getLogger(HistoryTab.class);
    
    SessionDTO session;
    private HistoryTabDTO historyTabDTO;
    @UiField("liabilitySummaryBtn")
    public Button liabilitySummaryBtn;
    @UiField("exportBtn")
    public Button exportBtn;
    @UiField("tableVerticalLayout")
    public VerticalLayout tableVerticalLayout;
    @UiField("asOfDate")
    public PopupDateField asOfDate;
    @UiField("viewDdlb")
    public ComboBox viewDdlb;
    @UiField("timePeriodDdlb")
    public ComboBox timePeriodDdlb;
    @UiField("itemIdentifierDdlb")
    public ComboBox itemIdentifierDdlb;
    @UiField("populateBtn")
    public Button populateBtn;
    @UiField("resetBtn")
    public Button resetBtn;
    @UiField("filterTypeDdlb")
    public ComboBox filterTypeDdlb;
    @UiField("valueDdlb")
    public ComboBox valueDdlb;
     @UiField("customMenuBar")
    protected CustomMenuBar customMenuBar;

    protected CustomMenuBar.CustomMenuItem customMenuItem;
    
    private ExtTreeContainer<BalanceReportDTO> resultsContainer = new ExtTreeContainer<BalanceReportDTO>(BalanceReportDTO.class, ExtContainer.DataStructureMode.LIST);
    CustomTableHeaderDTO headerDTO;
    /**
     * The excel export image.
     */
    HistoryTabTableLogic tableLogic = new HistoryTabTableLogic();
    public FreezePagedTreeTable resultsTable = new FreezePagedTreeTable(tableLogic);
    private ExtFilterTreeTable leftTable;
    private ExtFilterTreeTable rightTable;
    ExtFilterTreeTable excelTable = new ExtFilterTreeTable();
    ExtTreeContainer<BalanceReportDTO> excelContainer = new ExtTreeContainer<BalanceReportDTO>(BalanceReportDTO.class, ExtContainer.DataStructureMode.LIST);
    private HistoryTabLogic historytablogic=new HistoryTabLogic() ;
    /**
     * The max split position.
     */
    private final float maxSplitPosition = 1000;

    /**
     * The min split position.
     */
    private final float minSplitPosition = 200;

    /**
     * The split position.
     */
    private final float splitPosition = 300;
    
    CustomTableHeaderDTO leftHeader = new CustomTableHeaderDTO();
    CustomTableHeaderDTO rightHeader = new CustomTableHeaderDTO();
    CustomTableHeaderDTO fullHeader = new CustomTableHeaderDTO();
    List<String> doubleHeaderList=new ArrayList<String>();
    List<String> selectedVariables=new ArrayList<String>();
    BalanceReportDTO balanceReportDTO=new BalanceReportDTO();
     BRCommonUtils commonUtils = new BRCommonUtils();
    public HistoryTab(final SessionDTO session, final HistoryTabDTO historyTabDTO) {
        this.session = session;
        this.historyTabDTO = historyTabDTO;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/gtnBalanceHistory.xml"), this));
        configureFields();
    }

    /**
     *
     */
    protected void configureFields() {
        try {
            asOfDate.setDateFormat("MM/dd/yyyy");
            asOfDate.addStyleName("dateField-align-center");
            String[] variableValues = Constants.HistoryVariables.names();            
            itemIdentifierDdlb.setImmediate(true);
            itemIdentifierDdlb.setEnabled(false);
            itemIdentifierDdlb.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            itemIdentifierDdlb.setNullSelectionAllowed(true);
            itemIdentifierDdlb.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            itemIdentifierDdlb.addItem(Constants.IndicatorConstants.NDC8.getConstant());
            itemIdentifierDdlb.addItem(Constants.IndicatorConstants.NDC10.getConstant());
            itemIdentifierDdlb.addItem(Constants.IndicatorConstants.NDC11.getConstant());
            itemIdentifierDdlb.setValue(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            
            
            
            viewDdlb.setImmediate(true);
            viewDdlb.addItem(Constants.IndicatorConstants.CUSTOMER.getConstant());
            viewDdlb.addItem(Constants.IndicatorConstants.BRAND.getConstant());
            viewDdlb.addItem(Constants.IndicatorConstants.ITEM.getConstant());
            viewDdlb.setValue(Constants.IndicatorConstants.CUSTOMER.getConstant());
            timePeriodDdlb.setNewItemsAllowed(false);
            selectedVariables.add("Accruals");
            selectedVariables.add("Deductions");
            selectedVariables.add("Variance - Accruals to Deductions");
            
            filterTypeDdlb.setImmediate(true);
            filterTypeDdlb.setNullSelectionAllowed(false);
            filterTypeDdlb.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            filterTypeDdlb.addItem(CUSTOMER.getConstant());
            filterTypeDdlb.addItem(BRAND.getConstant());
            filterTypeDdlb.addItem(ITEM.getConstant());
            filterTypeDdlb.setValue(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            valueDdlb.setImmediate(true);
             customMenuBar.setImmediate(true);
            customMenuBar.setWidth("182px");
            customMenuItem = customMenuBar.addItem("-Select Variables-", null);
            CustomMenuBar.CustomMenuItem[] customItem = new CustomMenuBar.CustomMenuItem[variableValues.length];
            for (int i = 0; i < variableValues.length; i++) {
                customItem[i] = customMenuItem.addItem(variableValues[i].trim(), null);
                customItem[i].setCheckable(true);
                customItem[i].setItemClickable(true);

                customItem[i].setItemClickNotClosable(true);
                if (i == 0) {
                    customItem[i].setCheckAll(true);
                }
            }
            loadSelectionCriteria();
            getCheckedValues();
            initializeResultTable();
            configureResultTable();
            addResultTable();
            
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Configure Result Table.
     */
    @SuppressWarnings("serial")
    private void configureResultTable() {
        try {
            tableLogic.setPageLength(10);
            tableLogic.sinkItemPerPageWithPageLength(false);
            fullHeader = new CustomTableHeaderDTO();
            initializeDualTable();
            configureSelectionCriteria();
            leftHeader = HeaderUtils.getHistoryTabLeftTableColumns(fullHeader);
            String doubleheader=StringUtils.EMPTY;
            if(!"null".equals(String.valueOf(timePeriodDdlb.getValue()))){
                doubleheader=String.valueOf(timePeriodDdlb.getValue());
            }
            rightHeader = HeaderUtils.getHistoryRightTableColumns(balanceReportDTO,doubleheader, fullHeader, selectedVariables,asOfDate.getValue());
            resultsContainer.setColumnProperties(leftHeader.getProperties());
            resultsContainer.setColumnProperties(rightHeader.getProperties());
            List<Object> recordHeader = new ArrayList<Object>(rightHeader.getSingleColumns());
            recordHeader.add(0, "customer.0");
            resultsContainer.setRecordHeader(recordHeader);
            tableLogic.setContainerDataSource(resultsContainer);
            leftTable.setVisibleColumns(leftHeader.getSingleColumns().toArray());
            leftTable.setColumnHeaders(leftHeader.getSingleHeaders().toArray(new String[leftHeader.getSingleHeaders().size()]));
            leftTable.setDoubleHeaderVisible(true);
            leftTable.setDoubleHeaderVisibleColumns(leftHeader.getDoubleColumns().toArray());
            leftTable.setDoubleHeaderColumnHeaders(leftHeader.getDoubleHeaders().toArray(new String[leftHeader.getDoubleHeaders().size()]));
            leftTable.setDoubleHeaderMap(leftHeader.getDoubleHeaderMaps());
            leftTable.setColumnCheckBox("check.0", true);
            
            rightTable.setVisibleColumns(rightHeader.getSingleColumns().toArray());
            rightTable.setColumnHeaders(rightHeader.getSingleHeaders().toArray(new String[rightHeader.getSingleHeaders().size()]));
            rightTable.setDoubleHeaderVisible(true);
            rightTable.setDoubleHeaderVisibleColumns(rightHeader.getDoubleColumns().toArray());
            rightTable.setDoubleHeaderColumnHeaders(rightHeader.getDoubleHeaders().toArray(new String[rightHeader.getDoubleHeaders().size()]));
            rightTable.setDoubleHeaderMap(rightHeader.getDoubleHeaderMaps());
            
            for (Object obj : rightTable.getDoubleHeaderVisibleColumns()) {
            rightTable.setDoubleHeaderColumnAlignment(obj, ExtCustomTable.Align.CENTER);
            }
            if (asOfDate.getValue() != null) {
                rightTable.addGeneratedColumn(balanceReportDTO.getHypertLink(), new ExtCustomTable.ColumnGenerator() {
                    @Override
                    public Object generateCell(final ExtCustomTable source, final Object itemId, Object columnId) {
                        final BalanceReportDTO brdto=(BalanceReportDTO) itemId;
                        String caption = StringUtils.EMPTY;
                        Object caption1 = source.getContainerProperty(itemId, balanceReportDTO.getHypertLink()).getValue();
                        if (caption1 != null) {
                            caption = caption1.toString();
                        }
                        Button ndcLink = new Button(caption);
                        ndcLink.addStyleName(Reindeer.BUTTON_LINK);
                        ndcLink.setImmediate(true);
                        ndcLink.addClickListener(new Button.ClickListener() {
                            private static final long serialVersionUID = 1L;

                            @Override
                            public void buttonClick(Button.ClickEvent event) {
                                ActualVariance infoPopup = new ActualVariance(session,brdto);
                                UI.getCurrent().addWindow(infoPopup);
                            }
                        });
                        return ndcLink;
                    }
                });
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

    }
    
    @UiHandler("liabilitySummaryBtn")
    public void liabilitySummaryBtn(Button.ClickEvent event) throws Exception {
        LOGGER.info("Entering liabilitySummaryBtn method");
        
            final LiabilitySummaryPopup summaryPopup = new LiabilitySummaryPopup(session);
            UI.getCurrent().addWindow(summaryPopup);
        
    }
    @UiHandler("viewDdlb")
    public void loadItemIdentifier(Property.ValueChangeEvent event) throws Exception {
        LOGGER.info("Entering loadItemIdentifier method");
        if(Constants.IndicatorConstants.ITEM.getConstant().equals(String.valueOf(viewDdlb.getValue()))){
             itemIdentifierDdlb.setEnabled(true);
        }else{
             itemIdentifierDdlb.setEnabled(false);
        }
        LOGGER.info("End of loadItemIdentifier method");
    }
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        
    }
    private void loadSelectionCriteria() throws Exception {
        LOGGER.info("From and To date in Balance REport::::"+session.getDsFromDate()+" :::::: "+session.getDsToDate());
        
        
    }
     /**
     * Initialize Result Table.
     */
    @SuppressWarnings("serial")
    private void initializeResultTable() {
        resultsTable.markAsDirty();
        resultsTable.setSelectable(false);
        resultsTable.setImmediate(true);
        resultsTable.setSplitPosition(splitPosition, Sizeable.Unit.PIXELS);
        resultsTable.setMinSplitPosition(minSplitPosition, Sizeable.Unit.PIXELS);
        resultsTable.setMaxSplitPosition(maxSplitPosition, Sizeable.Unit.PIXELS);
        resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        resultsTable.addStyleName("center-check");
    }
    private void initializeDualTable() {
        leftTable = resultsTable.getLeftFreezeAsTable();
        rightTable = resultsTable.getRightFreezeAsTable();
        leftTable.markAsDirty();
        rightTable.markAsDirty();
        leftTable.setImmediate(true);
        rightTable.setImmediate(true);

    }
    /**
     * Add Result Table.
     */
    @SuppressWarnings("serial")
    private void addResultTable() {
        tableVerticalLayout.removeAllComponents();
        tableVerticalLayout.addComponent(resultsTable);
        HorizontalLayout controls = tableLogic.createControls();
        HorizontalLayout controlLayout = CommonLogic.getResponsiveControls(controls);
        tableVerticalLayout.addComponent(controlLayout);
    }
    private void configureSelectionCriteria() {
        String hisTimePeriod = String.valueOf(timePeriodDdlb.getValue());
        String asOfDate=StringUtils.EMPTY;
        if(this.asOfDate.getValue()!=null){
            asOfDate=this.asOfDate.getValue().toString();
        }
        String historyView=String.valueOf(viewDdlb.getValue());
       balanceReportDTO.setHisTimePeriod(hisTimePeriod);
       balanceReportDTO.setAsOfDate(asOfDate);
       balanceReportDTO.setHistoryView(historyView);
        
    }

    public void setSelectionCriteria() {
        this.balanceReportDTO=session.getBalanceReportDTO();
        this.doubleHeaderList=session.getSelectedPeriods();
        timePeriodDdlb.setContainerDataSource(new IndexedContainer(doubleHeaderList));
        timePeriodDdlb.select(session.getChosenPeriod());
        viewDdlb.select(balanceReportDTO.getViewType());
        initializeResultTable();
        configureResultTable();
        addResultTable();
        loadResultTable();
    }
    @UiHandler("populateBtn")
    public void populateBtn(Button.ClickEvent event) throws Exception {
        LOGGER.info("Entering populateBtn method");
        try {
            tableLogic.clearAll();
            tableLogic.getControlTable().getContainerDataSource().removeAllItems();
            tableVerticalLayout.removeAllComponents();
            tableLogic = new HistoryTabTableLogic();
            resultsTable = new FreezePagedTreeTable(tableLogic);
            getCheckedValues();
            initializeResultTable();
            configureResultTable();
            addResultTable();
             
            populateBtnLogic();
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("Ending populateBtn method");
    }
    private void populateBtnLogic() {
        loadResultTable();
    }
    private void loadResultTable() {
        try{
        Collections.sort(selectedVariables);
        if(StringUtils.isBlank(balanceReportDTO.getAsOfDate())){
            balanceReportDTO.setQuery("gtnmultipleccpquery");
        }else{
            balanceReportDTO.setQuery("asofdatequery");
        }
        tableLogic.setData(session,balanceReportDTO,selectedVariables,false);
        }catch(Exception e){
            LOGGER.error(e);
        }
    }
    @UiHandler("exportBtn")
      public void excelBtnClick(Button.ClickEvent event) {
        try{
            boolean firstGenerated = false;


            tableVerticalLayout.addComponent(excelTable);
            excelTable.setVisible(false);
            excelContainer = new  ExtTreeContainer<BalanceReportDTO>(BalanceReportDTO.class, ExtContainer.DataStructureMode.LIST);

            excelContainer.setColumnProperties(fullHeader.getProperties());
            excelTable.setContainerDataSource(excelContainer);

            excelTable.setVisibleColumns(fullHeader.getSingleColumns().toArray());
            excelTable.setColumnHeaders(fullHeader.getSingleHeaders().toArray(new String[fullHeader.getSingleHeaders().size()]));
            excelTable.setDoubleHeaderVisible(true);
            excelTable
                    .setDoubleHeaderVisibleColumns(fullHeader.getDoubleColumns().toArray());
            excelTable
                    .setDoubleHeaderColumnHeaders(fullHeader.getDoubleHeaders().toArray(new String[fullHeader.getDoubleHeaders().size()]));

            excelTable.setDoubleHeaderMap(fullHeader.getDoubleHeaderMaps());
            balanceReportDTO.setLevelNo(1);

            int count1 = historytablogic.getBalanceReportCount(new Object(), session, balanceReportDTO, selectedVariables, true);

            excelContainer.setColumnProperties(leftHeader.getProperties());
            excelContainer.setColumnProperties(rightHeader.getProperties());

            List<BalanceReportDTO> report = historytablogic.getBalanceReportResults(new Object(), 0, count1, session, balanceReportDTO, selectedVariables, true);
            excelContainer.removeAllItems();
            loadDataToContainer(report, null);

            Map<String, String> formatter = new HashMap<String, String>();
            formatter.put("currencyTwoDecimal", "Amount");
            formatter.put("percentTwoDecimal", "Rate");
            ExcelExport export = new ExcelExport(new ExtCustomTableHolder(excelTable), "History", "History", "History.xls", false);

            export.export();
            tableVerticalLayout.removeComponent(excelTable);
        }
        catch(Exception e){
            LOGGER.error(e);
        }
                
    }
     public void loadDataToContainer(List<BalanceReportDTO> report, Object parentId) {
        try {
            LOGGER.info("Inside loadDataToContainer"+report.size());
            for (BalanceReportDTO dto : report) {
                
                excelContainer.addBean(dto);
                if (parentId != null) {
                    excelContainer.setParent(dto, parentId);
                }
            if (dto.getParent() == 1) {
                excelContainer.setChildrenAllowed(dto, true);
                addLowerLevelsForExport(dto);
            } else {
                excelContainer.setChildrenAllowed(dto, false);
            }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("Ended loadDataToContainer");
        
    }

    public void addLowerLevelsForExport(Object id) {
        LOGGER.info("Inside addLowerLevelsForExport");
    
          int count1 = historytablogic.getBalanceReportCount(id, session, balanceReportDTO, selectedVariables, true);
           List<BalanceReportDTO> report= historytablogic.getBalanceReportResults(id,0,count1, session,balanceReportDTO,selectedVariables, true);
          loadDataToContainer(report, id);
           
       
        LOGGER.info("Ended addLowerLevelsForExport");
    } 
    @UiHandler("resetBtn")
    public void resetBtn(Button.ClickEvent event) throws Exception {
        LOGGER.info("Entering resetBtn method");
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                try {
                    timePeriodDdlb.select(session.getChosenPeriod());
                    itemIdentifierDdlb.setValue(Constants.IndicatorConstants.SELECT_ONE.getConstant());
                    viewDdlb.select(balanceReportDTO.getViewType());
                    asOfDate.setValue(null);
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }

            @Override
            public void noMethod() {
            }
        }.getConfirmationMessage("Reset Confirmation", "Are you sure you want to reset screen ?\n");
            
        LOGGER.info("Ending resetBtn method");
    }
    @UiHandler("filterTypeDdlb")
    public void loadFilterValueDdlb(Property.ValueChangeEvent event) throws Exception {
        LOGGER.info("Loading the Filter Value Ddlb initiated ");
        detachListeners(valueDdlb);
        String filterType=String.valueOf(filterTypeDdlb.getValue());
        valueDdlb=commonLoadingDdlb(valueDdlb,filterType);
        attachListeners(valueDdlb);
        LOGGER.info("Loading the Filter Value Ddlb ends ");
    }
    public ComboBox commonLoadingDdlb(ComboBox comboBox, final String filterType) throws Exception {
        if (!Constants.IndicatorConstants.SELECT_ONE.getConstant().equals(filterType)) {
            BalanceReportLogic balanceRepLogic = new BalanceReportLogic();
            List<HelperDTO> dtos = balanceRepLogic.getFilterValues(filterType, session);
            comboBox = CommonLogic.getComboBox(comboBox, dtos);
            comboBox.setNullSelectionAllowed(false);
        }
        return comboBox;
    }
    @UiHandler("valueDdlb")
    public void expandTreeLogic(Property.ValueChangeEvent event) throws Exception {
        String filterValue = String.valueOf(valueDdlb.getValue());
        String view = String.valueOf(viewDdlb.getValue());
        if (String.valueOf(filterValue).equals("0")) {
            loadResultTable();
        } else {
            String filterType = String.valueOf(filterTypeDdlb.getValue());
           
            try {
                tableLogic.loadExpandData(view, filterType,filterValue);
            } catch (Exception e) {
                LOGGER.error(e);
            }

        }
    }
    public void attachListeners(final AbstractField field) {
        field.setImmediate(true);
        field.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                try {
                    expandTreeLogic(event);
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        });
    }
    public void detachListeners(final AbstractField field) {
        List<Property.ValueChangeListener> listeners;
        listeners = (List<Property.ValueChangeListener>) field.getListeners(Property.ValueChangeEvent.class);
        for (final Property.ValueChangeListener object : listeners) {
            field.removeValueChangeListener(object);
        }
    }
      protected void getCheckedValues() {
        selectedVariables.clear();
        selectedVariables = commonUtils.getCurrentCheckValue(selectedVariables, customMenuItem);
    }
}
