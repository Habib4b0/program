/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.abstractforecast;

import com.stpl.app.gtnforecasting.projectionvariance.dto.ComparisonLookupDTO;
import com.stpl.app.gtnforecasting.projectionvariance.logic.tablelogic.ComparisonTableLogic;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.SELECT_ONE;
import com.stpl.app.gtnforecasting.utils.HeaderUtils;
import static com.stpl.app.utils.Constants.WindowMessagesName.CONFIRMATION;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.WorkflowConstants;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Window;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.util.IndexedContainer;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.DateField;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Jayaram
 */
public abstract class ForecastPVComparisonLookup extends Window{
    
    /**
     * The Constant serialVersionUID.
     */
    protected static final long serialVersionUID = 1L;
    /**
     * The work flow status.
     */
    @UiField("workFlowStatus")
    protected ComboBox workFlowStatus;
    /**
     * The market type.
     */
    @UiField("marketType")
    protected TextField marketType;
    /**
     * The brand.
     */
    @UiField("brand")
    protected TextField brand;
    /**
     * The projection name.
     */
    @UiField("projectionName")
    protected TextField projectionName;
    /**
     * The customer.
     */
    @UiField("customer")
    protected TextField customer;
    /**
     * The ndc.
     */
    @UiField("ndc")
    protected TextField ndc;
    /**
     * The description.
     */
    @UiField("description")
    protected TextField description;
    /**
     * The contract.
     */
    @UiField("contract")
    protected TextField contract;
    /**
     * The ndc name.
     */
    @UiField("ndcName")
    protected TextField ndcName;
    /**
     * The from date.
     */
    @UiField("fromDate")
    protected DateField fromDate;
    /**
     * The to date.
     */
    @UiField("toDate")
    protected DateField toDate;
    /**
     * The search btn.
     */
    @UiField("searchBtn")
    protected Button searchBtn;
    /**
     * The rest btn.
     */
    @UiField("resetBtn")
    protected Button resetBtn;
    /**
     * The submit btn.
     */
    @UiField("submitBtn")
    protected Button submitBtn;
    /**
     * The Projection rest btn.
     */
    @UiField("projectionResetBtn")
    protected Button projectionResetBtn;
    /**
     * The close btn.
     */
    @UiField("closeBtn")
    protected Button closeBtn;
    /**
     * The remove btn.
     */
    @UiField("removeBtn")
    protected Button removeBtn;
    /**
     * The add btn.
     */
    @UiField("addBtn")
    protected Button addBtn;
    /**
     * Panel
     */
    @UiField("selectedPanel")
    protected Panel selectedPanel;
    /**
     * Available VerticalLayout
     */
    @UiField("availableVertical")
    protected VerticalLayout availableVertical;
    /**
     * Selected verticalLayout
     */
    @UiField("seletedVertical")
    protected VerticalLayout selectedVertical;
    /**
     * The projection table.
     */
    @UiField("projectionTable")
    protected ExtFilterTable projectionTable;
    /**
     * Table logic
     */
    protected ComparisonTableLogic tableLogic = new ComparisonTableLogic();
    /**
     * The search result table.
     */
    protected ExtPagedTable resultsTable = new ExtPagedTable(tableLogic);
    /**
     * Visible columns for the tables
     */
    public final Object[] comparisonResultsColumns = new Object[]{Constant.PROJECTION_NAME, Constant.PROJECTIONDESCRIPTION, Constant.MARKET_TYPE, "customer", Constant.CONTRACT, Constant.BRAND, "createdDateFrom", "createdBy"};
    /**
     * The Constant COMPARISON_RESULTS_HEADER.
     */
    public final String[] comparisonResultsHeader = new String[]{"Projection Name", "Description", "Market Type", "Contract Holder", Constant.CONTRACT_SMALL, Constant.BRAND_CAPS, "Created Date", "Created By"};
    /**
     * Logger
     */
    private static final Logger LOGGER = Logger.getLogger(ForecastPVComparisonLookup.class);
    /**
     * Resultbean
     */
    protected final BeanItemContainer<ComparisonLookupDTO> resultsBean = new BeanItemContainer<>(ComparisonLookupDTO.class);
    /**
     * Selected result bean
     */
    protected final BeanItemContainer<ComparisonLookupDTO> selectedResultsBean = new BeanItemContainer<>(ComparisonLookupDTO.class);
    /**
     * Screen Name
     */
    String screenName = StringUtils.EMPTY;
    /**
     * Comparison custom text field
     */
    protected CustomTextField comparison;
    private final HeaderUtils headerUtils = new HeaderUtils();
    /**
     * 
     * @param screenName 
     */
    public ForecastPVComparisonLookup(String screenName,final CustomTextField comparison){
        super("Projection lookup");
        this.screenName = screenName;
        this.comparison = comparison;
        addStyleName(Constant.BOOTSTRAP_UI);
        addStyleName(Constant.BOOTSTRAP);
        addStyleName(Constant.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        setClosable(true);
        setModal(true);
        setWidth(NumericConstants.THOUSAND_FIVE_HUNDRED, Sizeable.Unit.PIXELS);
        setWidth("1400px");
        setHeight("955px");
        init();
    }
    
    /**
     * Init method
     */
    private void init(){
        setContent(Clara.create(getClass().getResourceAsStream("/abstractforecast/forecastPVComparisonLookup.xml"), this));
        configureFields();
    }
    
    /**
     * Configure Fields
     */
    private void configureFields(){
        try{
        loadWorkStatus();
        loadAvailableResults();
        loadSelectedResults();
        }catch(Exception ex){
          LOGGER.error(ex);
        }
    }
    
    /**
     * Loading workflow status ddlb
     */
    private void loadWorkStatus() {
        final IndexedContainer container = new IndexedContainer();
        container.addItem(SELECT_ONE);
        container.addItem(Constant.SAVED);
        container.addItem(Constant.SUBMITTED);
        container.addItem(WorkflowConstants.getApprovedStatus());
        container.addItem(WorkflowConstants.getRejectedStatus());
        workFlowStatus.setContainerDataSource(container);
        workFlowStatus.setImmediate(true);
    }
    
    private void loadAvailableResults(){
        LOGGER.debug("Inside abstract loadAvailableResults");
        availableVertical.setSizeFull();
        availableVertical.addComponent(resultsTable);
        HorizontalLayout layout = tableLogic.createControls();
        layout.setStyleName(Constant.RESPONSIVE_PAGED_TABLE);
        availableVertical.addComponent(layout);
        tableLogic.sinkItemPerPageWithPageLength(Boolean.FALSE);
        tableLogic.setContainerDataSource(resultsBean);
        resultsTable.setFilterBarVisible(true);
        resultsTable.markAsDirty();
        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        loadAvailableHeader();
        resultsTable.setSelectable(true);
        resultsTable.setMultiSelect(true);
        resultsTable.setFilterBarVisible(true);
        resultsTable.setWidth("1348px");
        resultsTable.setStyleName(Constant.FILTER_TABLE);
        resultsTable.setPageLength(NumericConstants.SEVEN);

        LOGGER.debug("Ending abstract loadAvailableResults");
    }

    private void loadSelectedResults() {
        LOGGER.debug("Inside abstract loadSelectedResults");
        selectedVertical.setSizeFull();
        selectedPanel.setSizeFull();
        projectionTable.setContainerDataSource(selectedResultsBean);
        projectionTable.setFilterBarVisible(true);
        projectionTable.markAsDirty();
        projectionTable.setFilterDecorator(new ExtDemoFilterDecorator());
        loadSelectedHeader();
        projectionTable.setSelectable(true);
        projectionTable.setMultiSelect(true);
        projectionTable.setFilterBarVisible(true);
        projectionTable.setHeight("400px");
        projectionTable.setWidth("1348px");
        projectionTable.setStyleName(Constant.FILTER_TABLE);
        projectionTable.setPageLength(NumericConstants.SEVEN);
        LOGGER.debug("Ending abstract loadSelectedResults");
    }
    
    /**
     * 
     */
    private void loadAvailableHeader(){
        if(screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)){
            resultsTable.setVisibleColumns(headerUtils.comparisonColumns);
            resultsTable.setColumnHeaders(headerUtils.comparisonHeader);
        }else{
            resultsTable.setVisibleColumns(comparisonResultsColumns);
            resultsTable.setColumnHeaders(comparisonResultsHeader);
        }
    }
    
    private void loadSelectedHeader(){
        if(screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)){
            projectionTable.setVisibleColumns(headerUtils.comparisonColumns);
            projectionTable.setColumnHeaders(headerUtils.comparisonHeader);
        }else{
            projectionTable.setVisibleColumns(comparisonResultsColumns);
            projectionTable.setColumnHeaders(comparisonResultsHeader);
        }
    }
    
    /**
     * Search btn logic.
     *
     * @param event the event
     */
    @UiHandler("searchBtn")
    public void searchBtn(Button.ClickEvent event) {
        LOGGER.debug("Inside searchBtnLogic");
        searchBtnLogic();
        LOGGER.debug("Ending searchBtnLogic");    
    }
    
    /**
     * Reset btn logic.
     *
     * @param event the event
     */
    @UiHandler("resetBtn")
    public void resetBtn(Button.ClickEvent event) {
        LOGGER.debug("Inside resetBtnLogic");
        resetBtnLogic();
        LOGGER.debug("Ending resetBtnLogic");            
    }
    
    /**
     * Adds the btn logic.
     *
     * @param event the event
     */
    @UiHandler("addBtn")
    public void addBtn(Button.ClickEvent event) {
        LOGGER.debug("Inside addBtnLogic");
        addBtnLogic(event);
        LOGGER.debug("Ending addBtnLogic");            
    }
    
    /**
     * Submit btn logic.
     *
     * @param event the event
     */
    @UiHandler("submitBtn")
    public void submitBtn(Button.ClickEvent event) {
        LOGGER.debug("Inside submitBtnLogic");
        submitBtnLogic();
        LOGGER.debug("Ending submitBtnLogic");            
    }
    
    /**
     * Projection reset btn logic.
     *
     * @param event the event
     */
    @UiHandler("projectionResetBtn")
    public void projectionResetBtn(Button.ClickEvent event) {
        LOGGER.debug("Inside projectionResetBtnLogic");
        projectionResetBtnLogic();
        LOGGER.debug("Ending projectionResetBtnLogic");            
    }
    
    /**
     * Close btn logic.
     *
     * @param event the event
     */
    @UiHandler("closeBtn")
    public void closeBtn(Button.ClickEvent event) {
        LOGGER.debug("Inside closeBtnLogic");
        closeBtnLogic();
        close();
        LOGGER.debug("Ending closeBtnLogic");            
    }
    
    /**
     * Removes the btn logic.
     *
     * @param event the event
     */
    @UiHandler("removeBtn")
    public void removeBtn(Button.ClickEvent event) {
        LOGGER.debug("Inside removeBtnLogic");
        removeBtnLogic(event);
        LOGGER.debug("Ending removeBtnLogic");            
    }
    
    protected abstract void searchBtnLogic();
    
    protected void resetBtnLogic(){
        new AbstractNotificationUtils() {
            public void noMethod() {
               return;
            }

            @Override
            /**
             * The method is triggered when Yes button of the message box is
             * pressed .
             *
             * @param buttonId The buttonId of the pressed button.
             */
            public void yesMethod() {
                workFlowStatus.select(SELECT_ONE);
                marketType.setValue(StringUtils.EMPTY);
                brand.setValue(StringUtils.EMPTY);
                projectionName.setValue(StringUtils.EMPTY);
                customer.setValue(StringUtils.EMPTY);
                ndc.setValue(StringUtils.EMPTY);
                description.setValue(StringUtils.EMPTY);
                contract.setValue(StringUtils.EMPTY);
                ndcName.setValue(StringUtils.EMPTY);
                fromDate.setValue(null);
                toDate.setValue(null);
            }
        }.getConfirmationMessage(CONFIRMATION.getConstant(), "Are you sure you want to reset the page to default/previous values?");
    }
    
    protected abstract void addBtnLogic(Button.ClickEvent event);
    
    protected void submitBtnLogic(){
        try{
        if (!projectionTable.getItemIds().isEmpty()) {
        List<ComparisonLookupDTO> selected = new ArrayList<>();
        ComparisonLookupDTO lookUpDTO = new ComparisonLookupDTO();
        Object[] itemIds = projectionTable.getItemIds().toArray();
        for (Object item : itemIds) {
            selected.add((ComparisonLookupDTO) item);
        }
        if (!selected.isEmpty()) {
            comparison.setReadOnly(false);
            if (selected.size() > 1) {
                comparison.setValue(Constant.MULTIPLE);
            } else {
                comparison.setValue(selected.get(0).getProjectionName());
            }
            lookUpDTO.setSelected(selected);
            comparison.setData(lookUpDTO);
            comparison.setReadOnly(true);
            close();
        }
        }else{
            MessageBox.showPlain(Icon.INFO, "Error", "No Data is available to submit", ButtonId.OK);
        }
        }catch(Exception ex){
            LOGGER.error(ex);
        }
    }
    
    protected abstract void projectionResetBtnLogic();
    
    protected abstract void closeBtnLogic();
    
    protected abstract void removeBtnLogic(Button.ClickEvent event);
    
}
