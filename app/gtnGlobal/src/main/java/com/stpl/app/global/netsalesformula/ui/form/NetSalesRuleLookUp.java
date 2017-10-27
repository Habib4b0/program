/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.netsalesformula.ui.form;

import com.stpl.app.global.abstractsearch.util.ConstantUtil;
import com.stpl.app.global.abstractsearch.util.Message;
import com.stpl.app.global.abstractsearch.util.MessageUtil;
import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.common.util.HelperListUtil;
import com.stpl.app.global.netsalesformula.dto.NetSalesRuleLookupDto;
import com.stpl.app.global.netsalesformula.dto.NsfFilterGenerator;
import com.stpl.app.global.netsalesformula.logic.tablelogic.NetSalesRuleTableLogic;
import com.stpl.app.global.netsalesformula.logic.tablelogic.NsrDetailsTableLogic;
import com.stpl.app.global.netsalesformula.utils.UIUtils;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.ConstantsUtils;
import static com.stpl.app.util.ResponsiveUtils.getResponsiveControls;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author karthikraja.k
 */
public class NetSalesRuleLookUp extends Window {

    /**
     * LOGGER
     */
    private static final Logger LOGGER = Logger.getLogger(NetSalesRuleLookUp.class);
    @UiField("ruleType")
    public ComboBox ruleType;
    /**
     * The Rule No.
     */
    @UiField("ruleNo")
 public   TextField ruleNo;
    /**
     * The Rule name.
     */
    @UiField("ruleName")
  public  TextField ruleName;
    /**
     * The rule Category.
     */
    @UiField("ruleCategory")
 public   ComboBox ruleCategory;
    /**
     * The search btn.
     */
    @UiField("searchBtn")
    Button searchBtn;
    /**
     * The reset btn.
     */
    @UiField("resetBtn")
    Button resetBtn;
   
    /**
     * The submit btn.
     */
    @UiField("selectBtn")
    Button selectBtn;
    /**
     * The close btn.
     */
    @UiField("closeBtn")
    public Button closeBtn;
    /**
     * The Details btn.
     */
    @UiField("detailsBtn")
    Button detailsBtn;
    /**
     * The Error Label.
     */
    @UiField("resultsPanel")
    Panel resultsPanel;
    @UiField("errorLB")
    public ErrorLabel errorMsg;
    @UiField("tableLayout")
    VerticalLayout tableLayout;
    HorizontalLayout controlLayout=new HorizontalLayout();
    @UiField("rdTableLayout")
    VerticalLayout rdTableLayout;
    HorizontalLayout rdControlLayout=new HorizontalLayout();
    
    @UiField("searchLayout")
    GridLayout searchLayout;
    @UiField("searchPanel")	
    Panel searchPanel;
    public boolean isSelected=false;
    public boolean isClosed=false;
    /**
     * The binder.
     */
    private ErrorfulFieldGroup binder;
   NetSalesRuleTableLogic tableLogic= new NetSalesRuleTableLogic();
    private  ExtPagedTable resultTable = new ExtPagedTable(tableLogic);
    public BeanItemContainer<NetSalesRuleLookupDto> resultBean = new BeanItemContainer<>(NetSalesRuleLookupDto.class);
    NsrDetailsTableLogic detailsTableLogic = new NsrDetailsTableLogic();        
    private final ExtPagedTable detailsTable = new ExtPagedTable(detailsTableLogic);
    public  BeanItemContainer<NetSalesRuleLookupDto> detailsBean = new BeanItemContainer<>(NetSalesRuleLookupDto.class);
    private HelperDTO rtDto = new HelperDTO(ConstantsUtils.SELECT_ONE);
    NetSalesRuleLookupDto detailsDto=new NetSalesRuleLookupDto();
    NetSalesRuleLookupDto selectDto;
    CustomTextField netSalesRule;
    HelperDTO helperDto=null;
    HelperListUtil helperListUtil = HelperListUtil.getInstance();
    String tabName=StringUtils.EMPTY;
    String moduleName=StringUtils.EMPTY;
    
    public NetSalesRuleLookUp(CustomTextField netSalesRule,String tabName) {
        super("Net Sales Rule Lookup");
        center();
        this.netSalesRule=netSalesRule;
        this.tabName=tabName;
        configureHelperDto();
        setModal(true);
        addStyleName(ConstantsUtils.BOOSTRAP_UI);
        addStyleName(ConstantsUtils.BOOTSTRAP);
        addStyleName(ConstantsUtils.BOOSTRAP_FORECAST_NM);
        setWidth(NumericConstants.FLOAT_SEVENTY, Sizeable.Unit.PERCENTAGE);
        setHeight(NumericConstants.FLOAT_THIRTY_THREE, Sizeable.Unit.PERCENTAGE);
        init();
        
    }
    
    public NetSalesRuleLookUp(HelperDTO helperDto,String lookUpName,String moduleName) {
        super(lookUpName);
        center();
        this.netSalesRule=netSalesRule;
        this.detailsDto=detailsDto;
        this.helperDto=helperDto;
        this.moduleName=moduleName;
        setModal(true);
        addStyleName(ConstantsUtils.BOOSTRAP_UI);
        addStyleName(ConstantsUtils.BOOTSTRAP);
        addStyleName(ConstantsUtils.BOOSTRAP_FORECAST_NM);
        setWidth(NumericConstants.FLOAT_SEVENTY, Sizeable.Unit.PERCENTAGE);
        setHeight(NumericConstants.FLOAT_SIXTY, Sizeable.Unit.PERCENTAGE);
        init();
        searchPanel.setCaption(lookUpName.replace("Lookup","Search"));
    }
    
     public NetSalesRuleLookUp(CustomTextField netSalesRule,HelperDTO helperDto,String lookUpName,String moduleName) {
        super(lookUpName);
        center();
        this.netSalesRule=netSalesRule;
        this.helperDto=helperDto;
        this.moduleName=moduleName;
        setModal(true);
        addStyleName(ConstantsUtils.BOOSTRAP_UI);
        addStyleName(ConstantsUtils.BOOTSTRAP);
        addStyleName(ConstantsUtils.BOOSTRAP_FORECAST_NM);
        setWidth(NumericConstants.FLOAT_SEVENTY, Sizeable.Unit.PERCENTAGE);
        setHeight(NumericConstants.FLOAT_SIXTY, Sizeable.Unit.PERCENTAGE);
        init();
        searchPanel.setCaption(lookUpName.replace("Lookup","Search"));
    }
     

    public void init() {
        try {
            setContent(Clara.create(getClass().getResourceAsStream("/declarativeui/nsf/NetSalesRuleLookUp.xml"), this));
            binder = getBinder();
            configureFields();
            configureTable();
            configureDetailsTable();
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
    
  /**
     * Gets the binder.
     *
     * @return the binder
     */
    public ErrorfulFieldGroup getBinder() {
   
        final ErrorfulFieldGroup nsfBinder;
        nsfBinder = new ErrorfulFieldGroup(new BeanItem<>(detailsDto));
        nsfBinder.setBuffered(true);
        nsfBinder.bindMemberFields(this);
        nsfBinder.setErrorDisplay(errorMsg);
        return nsfBinder;
    }
    
    /**
     * Configure fields.
     */
    private void configureFields() {
        errorMsg.setVisible(false);
        ruleCategory.setValidationVisible(true);
        ruleName.setValidationVisible(true);
        ruleNo.setImmediate(true);
        ruleType.setImmediate(true);
        ruleCategory.setImmediate(true);

        ruleCategory.setNullSelectionAllowed(true);
        ruleCategory.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        ruleCategory.addItem(ConstantsUtils.SELECT_ONE);
        ruleCategory.select(ConstantsUtils.SELECT_ONE);

         resultTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {

            @Override
            public void itemClick(ItemClickEvent event) {
                itemselectLogic(event);
            }
        });
        
        CommonUtil commonUtil = CommonUtil.getInstance();
        commonUtil.loadComboBox(ruleType, com.stpl.app.global.abstractsearch.util.UIUtils.RULE_TYPE, false);
        commonUtil.loadComboBox(ruleCategory, com.stpl.app.global.abstractsearch.util.UIUtils.RULE_CATEGORY, false);
        
            ruleType.setEnabled(true);
            ruleType.setValue(helperDto);


    }
    
    @UiHandler("searchBtn")
    public void btnSearchLogic(Button.ClickEvent event) {
        binder.getErrorDisplay().clearError();
         try {
            
            if (!tableLogic.checkSearchCriteria(binder)) {
            final MessageBox msg = MessageBox.showPlain(Icon.WARN, MessageUtil.getMessage(Message.SEARCH_CRITERIA_HEADER), MessageUtil.getMessage(Message.SEARCH_CRITERIA_MESSAGE), new MessageBoxListener() {

                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                @SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                    // Do Nothing
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
            } else{
            binder.commit();
            loadDetailsGrid("0");
            loadGrid();
            if (tableLogic.isResultsEmpty()) {
                final MessageBox msg = MessageBox.showPlain(Icon.WARN, "No Records Found", "There are no values that match the entered search criteria. ", new MessageBoxListener() {
                    /**
                     * The method is triggered when a button of the message box
                     * is pressed .
                     *
                     * @param buttonId The buttonId of the pressed button.
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        // Do Nothing
                    }
                }, ButtonId.OK);
                msg.getButton(ButtonId.OK).focus();

            } else {
                CommonUIUtils.successNotification(ConstantUtil.SEARCH_COMPLETED);
            }

            resultTable.markAsDirtyRecursive();
            }
        } catch (FieldGroup.CommitException commit) {
            tableLogic.clearAll();
            tableLogic.getFilters().clear();
            resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
            LOGGER.error(commit);
        } catch (Exception exception) {
            LOGGER.error(exception);
        }
    }

    /**
     * Reset Button Listener
     *
     * @param event
     */
    @UiHandler("resetBtn")
    public void btnResetLogic(Button.ClickEvent event) {

        MessageBox.showPlain(Icon.QUESTION, "Reset Confirmation", "Are you sure you want to reset the values in the Search Criteria group box?", new MessageBoxListener() {
            /**
             * Called when a Button has been clicked .
             *
             */
            @SuppressWarnings("PMD")
            public void buttonClicked(final ButtonId buttonId) {
                if (buttonId.name().equalsIgnoreCase("YES")) {
                    LOGGER.debug("Entering Reset operation");
                    binder.getErrorDisplay().clearError();
                    binder.setItemDataSource(new BeanItem<>(new NetSalesRuleLookupDto()));
                    
                    ruleType.setValue(helperDto);
                    tableLogic.clearAll();
                    tableLogic.setReset(true);
                    tableLogic.setRequiredCount(true);
                    tableLogic.setCurrentPage(1);
                    tableLogic.setContainerDataSource(resultBean);
                    tableLogic.setPageLength(NumericConstants.TEN);
                    tableLogic.sinkItemPerPageWithPageLength(false);

                    setTableDefaultConfig();
                    resultTable.setSelectable(true);
                    resultTable.markAsDirty();        
                    resultTable.setComponentError(null);
                    resultTable.setFilterBarVisible(true);
                    resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
                    resultTable.setFilterGenerator(new NsfFilterGenerator(rtDto));
                    resultTable.setValidationVisible(false);
                    resultTable.addStyleName(ConstantsUtils.FILTER_BAR);
                    
                    loadDetailsGrid("0");
                    
                    LOGGER.debug("Ending Reset operation");
                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }

    
    private void configureTable() {      
        tableLayout.addComponent(resultTable);
        getResponsiveControls(tableLogic.createControls(), controlLayout);
        tableLayout.addComponent(controlLayout);

        tableLogic.setContainerDataSource(resultBean);
        tableLogic.setPageLength(NumericConstants.TEN);
        tableLogic.sinkItemPerPageWithPageLength(false);
        setTableDefaultConfig();
        
        resultTable.setSelectable(true);
        resultTable.markAsDirty();

        resultTable.setComponentError(null);
        resultTable.setFilterBarVisible(true);
        resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultTable.setFilterGenerator(new NsfFilterGenerator(rtDto));
        resultTable.setValidationVisible(false);
        resultTable.addStyleName(ConstantsUtils.FILTER_BAR);

    }

    public void setTableDefaultConfig()  {
            resultTable.setWidth("470px");
            resultTable.setVisibleColumns(UIUtils.NSR_COLUMN);
            resultTable.setColumnHeaders(UIUtils.NSR_HEADER);
            resultTable.markAsDirtyRecursive();
            resultTable.setImmediate(true);
            resultTable.setWidth(NumericConstants.NINTY_NINE, UNITS_PERCENTAGE);
            resultTable.setHeight("250px");
            resultTable.addStyleName(ConstantsUtils.LEFT_ALIGN_IMPORTANT);
    }
  /**
     * TO load the grid
     */
    private void loadGrid() {
        try {
            tableLogic.configureSearchData(binder,tabName);
            resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
            resultTable.setFilterGenerator(new NsfFilterGenerator(rtDto));
            if(helperDto!=null && StringUtils.isBlank(tabName)) {
            resultTable.getFilterField("ruleType").setVisible(false);
            }
            resultTable.setImmediate(true);
            resultTable.setWidth(NumericConstants.NINTY_NINE, UNITS_PERCENTAGE);
            resultTable.addStyleName(ConstantsUtils.TABLE_CHECK_BOX);
            resultTable.setSelectable(true);
            resultTable.markAsDirtyRecursive();
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }
    
    
     private void configureDetailsTable() {
      
        rdTableLayout.addComponent(detailsTable);
        getResponsiveControls(detailsTableLogic.createControls(), rdControlLayout);
        rdTableLayout.addComponent(rdControlLayout);

        detailsTableLogic.setContainerDataSource(detailsBean);
        detailsTableLogic.setPageLength(NumericConstants.TEN);
        detailsTableLogic.sinkItemPerPageWithPageLength(false);
        setDetailsTableDefaultConfig();
        
        detailsTable.setSelectable(true);
        detailsTable.markAsDirty();

        detailsTable.setComponentError(null);
        detailsTable.setFilterBarVisible(true);
        detailsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        detailsTable.setFilterGenerator(new NsfFilterGenerator(rtDto));
        detailsTable.setValidationVisible(false);
        detailsTable.addStyleName(ConstantsUtils.FILTER_BAR);
        detailsTable.addStyleName(ConstantsUtils.LEFT_ALIGN_IMPORTANT);

    }

    public void setDetailsTableDefaultConfig()  {
       
            detailsTable.setVisibleColumns(UIUtils.NSR_DETAILS_COLUMN);
            detailsTable.setColumnHeaders(UIUtils.NSR_DETAILS_HEADER);
            detailsTable.markAsDirtyRecursive();
            detailsTable.setImmediate(true);
            detailsTable.setWidth(NumericConstants.NINTY_NINE, UNITS_PERCENTAGE);
            detailsTable.setHeight("250px");
        
    }
  /**
     * TO load the grid
     */
    private void loadDetailsGrid(String ruleId) {
        try {
            detailsTableLogic.configureSearchData(ruleId);
            detailsTable.setFilterDecorator(new ExtDemoFilterDecorator());
            detailsTable.setFilterGenerator(new NsfFilterGenerator(rtDto));
            detailsTable.setImmediate(true);
            detailsTable.setWidth(NumericConstants.NINTY_NINE, UNITS_PERCENTAGE);
            detailsTable.addStyleName(ConstantsUtils.TABLE_CHECK_BOX);
            detailsTable.addStyleName(ConstantsUtils.LEFT_ALIGN_IMPORTANT);
            detailsTable.setSelectable(true);
            detailsTable.markAsDirtyRecursive();
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

      public void itemselectLogic(final ItemClickEvent event) {
        try {
            if (event.getItemId() != null) {
                 detailsDto = (NetSalesRuleLookupDto) event.getItemId();
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * Details Button Listener
     *
     * @param event
     */
    @UiHandler("detailsBtn")
    public void detailsLogic(Button.ClickEvent event) {
        LOGGER.debug("Entering detailsBtn operation");
        if (resultTable.getValue() != null) {
            loadDetailsGrid(detailsDto.getRuleSystemId());
        }else{
         final MessageBox msg = MessageBox.showPlain(Icon.WARN, "Select Error", "Please select a row from the Results list view to proceed", new MessageBoxListener() {

                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                @SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                    // Do Nothing
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
        }
        LOGGER.debug("Ending detailsBtn operation");
             
    }

    /**
     * Close Button Listener
     *
     * @param event
     */
    @UiHandler("closeBtn")
    public void closeBtnLogic(Button.ClickEvent event) {
        isClosed=true;
        close();
    }
    
    /**
     * Select Button Listener
     *
     * @param event
     */
    @UiHandler("selectBtn")
    public void selectBtnLogic(Button.ClickEvent event) {
        if (resultTable.getValue() != null && detailsDto!=null) {
            if (!moduleName.equals("Rebate Schedule")) {
                netSalesRule.setValue(detailsDto.getRuleNo());
            }
            isSelected=true;
            close();
           
        }else{
         final MessageBox msg = MessageBox.showPlain(Icon.WARN, "Select Error", "Please select a row from the Results list view to proceed", new MessageBoxListener() {

                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                @SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                    // Do Nothing
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
        }
    }


    public NetSalesRuleLookupDto getDetailsDto() {
        return detailsDto;
    }

    public void setDetailsDto(NetSalesRuleLookupDto detailsDto) {
        this.detailsDto = detailsDto;
    }

    
    public NetSalesRuleLookupDto getNetSalesRuleLookupDto(){
        return detailsDto;
    }

            
    public void configureSearchdata(String systemId)
    {
     
       loadDetailsGrid(systemId);
      
        
    }

   public ExtPagedTable getResultTable()
   {
      
       return resultTable;
   }
    public void disableComponents(boolean isEnable)
   {
       resultsPanel.setVisible(isEnable);
       searchLayout.setEnabled(isEnable);
       detailsTable.setSizeFull();
       searchBtn.setVisible(isEnable);
       detailsBtn.setVisible(isEnable);
       resetBtn.setVisible(isEnable);
       selectBtn.setVisible(isEnable);
   }
    
     /**
     * Returns the flag.
     * true - if item is selected in the table.
     * false - if item is not selected.
     * @return 
     */
    public boolean isSelected() {
        return isSelected;
    }

    /**
     * Sets the flag as true when an item is selected in the table.
     * @param isSelected 
     */
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
    
    /**
     * Returns the selected Item from the table.
     * @return 
     */
    public NetSalesRuleLookupDto getSelectedItem(){
      return getBeanFromId(resultTable.getValue());  
    }
    
    /**
     * 
     * @param obj
     * @return 
     */   
    public NetSalesRuleLookupDto getBeanFromId(Object obj) {

        BeanItem<?> targetItem = null;
        if(obj!=null){
            if (obj instanceof BeanItem<?>) {
                targetItem = (BeanItem<?>) obj;
            } else if (obj instanceof NetSalesRuleLookupDto) {
                targetItem = new BeanItem<>(
                        (NetSalesRuleLookupDto) obj);
            }
        }
        return obj!=null?(NetSalesRuleLookupDto) targetItem.getBean():new NetSalesRuleLookupDto();
    }
    
    public void configureHelperDto(){
       List<HelperDTO> defaultValue=helperListUtil.getListNameMap().get(com.stpl.app.global.abstractsearch.util.UIUtils.RULE_TYPE);
        for(HelperDTO helperDto:defaultValue){
            if("Net Sales".equalsIgnoreCase(helperDto.getDescription())){
                this.helperDto=helperDto;
            }
        }
    
    }

}
