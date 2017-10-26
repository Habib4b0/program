/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dashboard.ui.lookup;

import com.stpl.app.contract.common.util.CommonUtil;
import com.stpl.app.contract.contractheader.util.UIUtils;
import com.stpl.app.contract.dashboard.dto.NepFormulaLookUpDTO;
import com.stpl.app.contract.dashboard.dto.NsfFilterGenerator;
import com.stpl.app.contract.dashboard.logic.NepFormulaTableLogic;
import com.stpl.app.contract.dashboard.util.ContractUtils;
import com.stpl.app.contract.ui.ErrorfulFieldGroup;
import com.stpl.app.contract.util.QueryUtil;
import com.stpl.app.contract.util.ResponsiveUtils;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import org.apache.commons.lang.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author sathyaseelan.v
 */
public class NetSalesFormulaLookup extends Window{
   
    private static final Logger LOGGER = Logger.getLogger(NetSalesFormulaLookup.class);
    
    @UiField("tablelayout")
    private VerticalLayout tablelayout;
    
    @UiField("controlLayout")
    private HorizontalLayout controlLayout;   
    
    @UiField("searchBtn")
    private Button searchBtn;

    @UiField("closeBtn")
    private Button closeBtn;
    
    @UiField("nepFormulaType")
    private ComboBox nepFormulaType;
    @UiField("nepFormulaName")
    private TextField nepFormulaName;
    @UiField("nepFormulaNo")
    private TextField nepFormulaNo;
    @UiField("nepFormulaID")
    private TextField nepFormulaID;
    @UiField("resetBtn")
    private Button resetBtn;
    private TextField createdDate=new TextField();
    private TextField modifiedDate=new TextField();
    NepFormulaTableLogic tableLogic = new NepFormulaTableLogic();

    private final ExtPagedTable resultsTable = new ExtPagedTable(tableLogic);
    
    BeanItemContainer<NepFormulaLookUpDTO> resultsContainer = new BeanItemContainer<>(NepFormulaLookUpDTO.class);    

    private NepFormulaLookUpDTO psNepFormulaDTO = new NepFormulaLookUpDTO();
    

    ErrorfulFieldGroup binder= new ErrorfulFieldGroup(new BeanItem(new NepFormulaLookUpDTO()));
    
    public boolean isSelected;
    
    /** The common util. */
    private CommonUtil commonUtil = CommonUtil.getInstance();
    CustomTextField netSalesNoField;

    boolean validatePPA=false;
    /**
     * Constructor with arguments to load the formula for Line level item in Rebate Setup tab.
     * @param itemId
     * @throws Exception 
     */
    public NetSalesFormulaLookup(String itemId) throws SystemException {
        init();
    }
    
    public NetSalesFormulaLookup(boolean validatePPA, CustomTextField netSalesNoField) throws SystemException {
        this.validatePPA = validatePPA;
        this.netSalesNoField = netSalesNoField;
        init();
    }
    
    /**
     *  Configures the Window
     */
    private void init() throws SystemException {
        this.setModal(true);
        this.setClosable(true);
        this.center();
        this.setWidth("800px");        
        setCaption("Net Sales Formula Lookup");
        addStyleName("bootstrap");
        addStyleName("bootstrap-bb");
        setContent(Clara.create(getClass().getResourceAsStream("/declarative-ui/contract-dashboard/nep-formula-lookup.xml"), this));        
        configureBinder();  
        configureFields();
        configureTable();
    }
    /**
     * Binds the search criteria field values to the DTO.
     */
    private void configureBinder() {
        binder.bindMemberFields(this);   
        binder.setBuffered(true);
    }
    
    private void configureFields() throws SystemException {
        commonUtil.loadComboBox(nepFormulaType, UIUtils.NS_FORMULA_TYPE, false);
        createdDate.setImmediate(Boolean.TRUE);
        modifiedDate.setImmediate(Boolean.TRUE);
    }
    /**
     * Configures the table logic and result table.
     */
    private void configureTable() {
        tableLogic.setContainerDataSource(resultsContainer);
        tableLogic.setPageLength(NumericConstants.TEN);
        tableLogic.sinkItemPerPageWithPageLength(false);    
        resultsTable.setImmediate(true);
        resultsTable.setSizeFull();
        resultsTable.setVisibleColumns(ContractUtils.NEP_FORMULA_LOOKUP);
        resultsTable.setColumnHeaders(ContractUtils.NEP_FORMULA_LOOKUP_HEADER);
        resultsTable.setFilterBarVisible(true);
        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultsTable.setFilterGenerator(new NsfFilterGenerator());
        resultsTable.setSelectable(true);
        tablelayout.addComponent(resultsTable);
        ResponsiveUtils.getResponsiveControls(tableLogic.createControls(), controlLayout);
        for (Object propertyId : resultsTable.getVisibleColumns()) {
                resultsTable.setColumnWidth(propertyId, NumericConstants.TWO_HUNDRED);
            }
        resultsTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {

            @Override
            public void itemClick(ItemClickEvent event) {
                itemselectLogic(event);
            }
        });
    }
    
     public void itemselectLogic(final ItemClickEvent event) {
        try {
            if (event.getItemId() != null) {
                psNepFormulaDTO = (NepFormulaLookUpDTO) event.getItemId();
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
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
     * Returns the selected Item from the table.
     * 
     * @return 
     */
    public NepFormulaLookUpDTO getSelectedItem(){
      return getBeanFromId(resultsTable.getValue());  
    }
    /**
     * 
     * @param obj
     * @return 
     */   

    public NepFormulaLookUpDTO getBeanFromId(Object obj) {
        BeanItem<?> targetItem = null;
        if (obj instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) obj;
        } else if (obj instanceof NepFormulaLookUpDTO) {
            targetItem = new BeanItem<NepFormulaLookUpDTO>(
                    (NepFormulaLookUpDTO) obj);
        }
        return (NepFormulaLookUpDTO) targetItem.getBean();
    }
    /**
    * Searches for the formula with the entered criteria.
    * @param event 
    */
    @UiHandler("searchBtn")
    public void searchLogic(Button.ClickEvent event){
        try {            
            binder.commit();
            tableLogic.setSearchData(binder);            
            tableLogic.setCurrentPage(1);  
            resultsTable.setFilterBarVisible(true);
            resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
            resultsTable.setFilterGenerator(new NsfFilterGenerator());
            resultsTable.addStyleName("filterbar");
        } catch (Exception ex) {
            tableLogic.clearAll();
            tableLogic.getFilters().clear();
            resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
            LOGGER.error(ex);
        }        
    }
    @UiHandler("selectBtn")
    public void selectBtn(Button.ClickEvent event) {
        if (resultsTable.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification("No formula Selected",
                    "You have not selected a formula. Please click a formula and try again.");
        } else {

            
            final NepFormulaLookUpDTO itemId = (NepFormulaLookUpDTO) resultsTable.getValue();
            List resultList = QueryUtil.getItemData(new ArrayList(){{add(itemId.getNepFormulaSystemID());}}, "net-sales-ppa-validation", null);
            boolean ppaCheck=resultList.get(0)!=null && !String.valueOf(resultList.get(0)).equals("null") && "1".equals(String.valueOf(resultList.get(0)));
            if (validatePPA && !ppaCheck) { 
                AbstractNotificationUtils.getErrorNotification("Error",
                        "Only formulas that have a Contract Selection of 'Existing Contract' \n"
                        + "and a Formula Type of 'Contract Deduction' may be used. \n"
                        + "Please select another formula and try again.");
                
            }else{
            netSalesNoField.setReadOnly(false);
            netSalesNoField.setValue(psNepFormulaDTO.getNepFormulaNo());
            netSalesNoField.setReadOnly(true);
            setNepFormulaDTO(itemId);
            isSelected = true;
            close();
            }
            
        }
        }
    public NepFormulaLookUpDTO getNepFormulaDTO() {
        return psNepFormulaDTO;
    }

    public void setNepFormulaDTO(NepFormulaLookUpDTO groupDTO) {
        this.psNepFormulaDTO = groupDTO;
    }
    @UiHandler("closeBtn")
    public void closeButtonLogic(Button.ClickEvent event) {
        close();
    }
    @UiHandler("resetBtn")
    public void resetButtonLogic(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
                    public void noMethod() {
                    }

                    @Override
                    /**
                     * The method is triggered when Yes button of the message
                     * box is pressed .
                     *
                     * @param buttonId The buttonId of the pressed button.
                     */
                    public void yesMethod() {
                        resultsTable.removeAllItems();
                        nepFormulaName.setValue(StringUtils.EMPTY);
                        nepFormulaNo.setValue(StringUtils.EMPTY);
                        nepFormulaID.setValue(StringUtils.EMPTY);
                        resultsTable.setFilterBarVisible(true);
                        tableLogic.clearAll();
                        tableLogic.getFilters().clear();
                        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
                        resultsTable.setFilterGenerator(new NsfFilterGenerator());
                        resultsTable.addStyleName("filterbar");
                        try {
                            commonUtil.loadComboBox(nepFormulaType, UIUtils.NS_FORMULA_TYPE, false);
                        } catch (Exception ex) {
                            java.util.logging.Logger.getLogger(NetSalesFormulaLookup.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }.getConfirmationMessage("Reset Confirmation", "Are you sure you want to reset the page to default values?");
    }
    
    /**
     * Sets the flag as true when an item is selected in the table.
     *
     * @param isSelected
     */
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}