/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.ui.form.lookups;

import com.stpl.app.forecastabstract.lookups.AbstractHistoryLookup;
import com.stpl.app.gtnforecasting.dto.AlternateHistoryDTO;
import com.stpl.app.gtnforecasting.logic.NonMandatedLogic;
import com.stpl.app.gtnforecasting.logic.SalesProjectionLogic;
import com.stpl.app.gtnforecasting.salesprojection.logic.SalesLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.AlternateLookupSource;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.STRING_EMPTY;

import com.stpl.app.utils.UiUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.NativeSelect;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class AlternateHistoryLookup.
 *
 * @author soundarrajan
 */
public class AlternateHistoryLookup extends AbstractHistoryLookup {

    /** Results Table for customer search. */
    private ExtFilterTable contractResults=new ExtFilterTable();

    /** Customer DDLB. */
    private NativeSelect customer;
    
    /** Results Table for brand search. */
    private ExtFilterTable brandResults=new ExtFilterTable();

    /** Container for contractResults table. */
    private BeanItemContainer<AlternateHistoryDTO> contractContainer;

    /** Container for contractResults table. */
    private BeanItemContainer<AlternateHistoryDTO> brandContainer;

    /** The logger. */
    private static final Logger LOGGER = Logger.getLogger(AlternateHistoryLookup.class);

    /** The search binder. */
    private CustomFieldGroup searchBinder;

    /** The logic. */
    private final NonMandatedLogic logic = new NonMandatedLogic();
    
    private final SalesLogic saleslogic = new SalesLogic();
    
   
    
    /** The error msg. */
    private final ErrorLabel errorMsg = new ErrorLabel();
        
    private final SessionDTO session;
        
    private String type=STRING_EMPTY;
     
    private String hierarchyNo=StringUtils.EMPTY;
     
    private static String importedContract=StringUtils.EMPTY;
     
    private static String importedBrand=StringUtils.EMPTY;

    private CustomTextField contract=null;
    private CustomTextField brand=null;
     
    /**
     * Constructor for AlternateHistoryLookup.
     *
     * @param windowName Window name of the lookup
     * @param moduleIndicator indicates whether the module is NonMandated or
     * Mandated or Channels
     */
    public AlternateHistoryLookup(final String windowName, final String moduleIndicator,final SessionDTO session,final String type,final String hierarchyNo,final CustomTextField contract,final CustomTextField brand) {
        super(windowName, moduleIndicator);
        this.type=type;
        
        buildLookup();
        getBinder();
        this.session=session;
        this.hierarchyNo=hierarchyNo;
        this.contract=contract;
        this.brand=brand;
        configureFields();
        
    }

    /**
     * Builds the lookup.
     */
    private void buildLookup() {
        initializeComponents();
        setContent(buildHistoryLookup(contractResults, brandResults, customer));
    }

    /**
     * Initializes the components.
     */
    private void initializeComponents() {
        setContractResults(contractResults);
        setBrandResults(brandResults);
        setCustomer(UiUtils.addDefaultNativeSelect());
        contractContainer = new BeanItemContainer<>(AlternateHistoryDTO.class);
        contractResults.setSelectable(true);
        brandContainer = new BeanItemContainer<>(AlternateHistoryDTO.class);
        brandResults.setSelectable(true);
        contractResults.setContainerDataSource(contractContainer);
        brandResults.setContainerDataSource(brandContainer);
    }

    /**
     * Customize Customer search logic.
     */
    @Override
    protected void btnCustomerSearchLogic() {
        try {
            searchBinder.commit();
        } catch (CommitException ex) {
            java.util.logging.Logger.getLogger(AlternateHistoryLookup.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    	if (StringUtils.isEmpty(getContractHolder().getValue())&&StringUtils.isEmpty(getCustomerId().getValue())) {
         
        AbstractNotificationUtils.getErrorNotification("No Search Value Entered", "There is no search value entered. Please enter a search value and try again.");
           
        } else {
             try {
               
                contractContainer.removeAllItems();
                final AlternateLookupSource alternate = logic.searchAlternateCustomerAndBrand(searchBinder, Constant.TP,false);
                final List<AlternateHistoryDTO> tpResult = alternate.getCustomersList();
                 if (tpResult.isEmpty()) {
                AbstractNotificationUtils.getInfoNotification("No Results", "There were no results found that match the entered search criteria. \nPlease try again.");

            } else {
               contractContainer.addAll(tpResult);
               CommonUIUtils.getMessageNotification("Search Completed");
            }
               
                
            } catch (SystemException sysException) {
               
                LOGGER.error(sysException);
            } catch (Exception exception) {
              
                LOGGER.error(exception);
            }
           
        }
    }

    /**
     * Customize Brand search logic.
     */
    @Override
    protected void btnBrandSearchLogic() {
        try {
            if (StringUtils.isEmpty(searchBinder.getField(Constant.BRAND_SEARCH).getValue().toString())) {
                AbstractNotificationUtils.getErrorNotification("No Search Value Entered", "There is no search value entered. Please enter a search value and try again.");
            } else {
                searchBinder.commit();
                brandContainer.removeAllItems();
                final AlternateLookupSource alternate = logic.searchAlternateCustomerAndBrand(searchBinder, Constant.BRAND_CAPS,false);
                final List<AlternateHistoryDTO> brandResult = alternate.getBrandList();
                   if (brandResult.isEmpty()) {
                AbstractNotificationUtils.getInfoNotification("No Results", "There were no results found that match the entered search criteria. \nPlease try again.");

            } else {
               brandContainer.addAll(brandResult);
               CommonUIUtils.getMessageNotification("Search Completed");
            }
              
            }
        } catch (CommitException e) {
          
            LOGGER.error(e);
        } catch (SystemException e) {
           
            LOGGER.error(e);
        } catch (Exception e) {
            
            LOGGER.error(e);
        }
    }

    /**
     * Sets the contract results.
     *
     * @param contractResults the contract results
     */
    public void setContractResults(final ExtFilterTable contractResults) {
        this.contractResults = contractResults;
    }

    /**
     * Sets the brand results.
     *
     * @param brandResults the brand results
     */
    public void setBrandResults(final ExtFilterTable brandResults) {
        this.brandResults = brandResults;
    }

    /**
     * Sets the customer.
     *
     * @param customer the customer
     */
    public void setCustomer(final NativeSelect customer) {
        this.customer = customer;
    }

    /**
     * Gets the binder.
     *
     * @return the binder
     */
    private CustomFieldGroup getBinder() {
        LOGGER.debug("Entering getBinder");
        final AlternateHistoryDTO bean = new AlternateHistoryDTO();

        searchBinder = new CustomFieldGroup(new BeanItem<>(bean));
        searchBinder.setBuffered(true);
        searchBinder.bindMemberFields(this);
        searchBinder.setErrorDisplay(errorMsg);
        LOGGER.debug("Ends getBinder");
        return searchBinder;
    }

    @Override
    protected void btnImportLogic() {
        Object selected;
      if(!type.equals(Constant.BRAND)){
     selected= contractResults.getValue();
      }else{
      selected=brandResults.getValue();
      }
      if(selected!=null){
      
         AlternateHistoryDTO contrtactDto=null;
          AlternateHistoryDTO brandDto=null;
         Object[] inputs=new Object[NumericConstants.SEVEN];
         inputs[0]=0;
         inputs[1]=0;
         inputs[NumericConstants.TWO]=0;
         inputs[NumericConstants.THREE]=0;
          if (contractResults.getValue() != null) {
              contrtactDto = getBeanFromId(contractResults.getValue());

              contract.setValue(contrtactDto.getContractHolder());
          } 
          if (brandResults.getValue() != null) {
              brandDto = getBeanFromId(brandResults.getValue());
              brand.setValue(brandDto.getBrandSearch());

          }
        if(contrtactDto!=null){
        inputs[NumericConstants.TWO]=contrtactDto.getContractSid();
          
        }
         if(brandDto!=null){
        inputs[NumericConstants.THREE]=brandDto.getBrandSid();
            
        }
          inputs[NumericConstants.FOUR] = session.getProjectionId();
          inputs[NumericConstants.FIVE] = session.getSessionId();
          inputs[NumericConstants.SIX] = session.getUserId();
          if (type.equals(Constant.BRAND)) {
              inputs[0] = null;
              inputs[1] = hierarchyNo;
          } else {
              inputs[0] = hierarchyNo;
              inputs[1] = null;
          }
         
       
        SalesProjectionLogic salesProjLogic=new SalesProjectionLogic();
        try {
            
            salesProjLogic.callAlternateHistoryProcedure(inputs);
    
        } catch (SystemException | SQLException ex) {
            java.util.logging.Logger.getLogger(AlternateHistoryLookup.class.getName()).log(Level.SEVERE, null, ex);
        } 
        close();
        
    }else{
          
        AbstractNotificationUtils.getErrorNotification("Error", "There are no selected results.\n Please select a record and try again.");
      
      }
        }
    
    /**
     * Gets the bean from id.
     *
     * @param obj the obj
     * @return the bean from id
     */
    public AlternateHistoryDTO getBeanFromId(Object obj) {

        BeanItem<?> targetItem = null;
        if (obj instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) obj;
        } else if (obj instanceof AlternateHistoryDTO) {
            targetItem = new BeanItem<>((AlternateHistoryDTO) obj);
        }
        return (AlternateHistoryDTO) targetItem.getBean();
    }

    private void configureFields() {
    contractName.setValue(StringUtils.EMPTY);
    if(type.equals(Constant.BRAND)){
    getContractHolder().setReadOnly(true);
    getCustomerId().setReadOnly(true);
    getBtnCustomerReset().setEnabled(false);
    getBtnCustomerSearch().setEnabled(false);
    }else{
    getBrandSearch().setReadOnly(true);
     getBtnBrandSearch().setEnabled(false);
     getBtnBrandReset().setEnabled(false);
    }
     loadCustomerDDLB(customer);
    
        if(type.equals(Constant.BRAND)){
        
          try {
               
                contractContainer.removeAllItems();
                final AlternateLookupSource alternate = logic.searchAlternateCustomerAndBrand(searchBinder, Constant.TP,true);
                final List<AlternateHistoryDTO> tpResult = alternate.getCustomersList();
                 if (!tpResult.isEmpty()) {
              contractContainer.addAll(tpResult);

            } 
                
            } catch (SystemException sysException) {
                  
               
                LOGGER.error(sysException);
            } catch (Exception exception) {
                 
                LOGGER.error(exception);
            }
        }
    contract.setValue(StringUtils.EMPTY);
    }
     public void loadCustomerDDLB(NativeSelect customerDDLB)  {
         try{
        customerDDLB.addItem(Constant.SELECT_ONE);
        customerDDLB.setNullSelectionAllowed(true);
        customerDDLB.setNullSelectionItemId(Constant.SELECT_ONE);
        List<Object[]> list = saleslogic.loadAlternateCustomer();
        for (Object[] obj : list) {
            customerDDLB.addItem(obj[0]);
            customerDDLB.setItemCaption(obj[0], String.valueOf(obj[1]));
        }
         }catch(Exception e){
         LOGGER.error(e);
         }
    }

    @Override
    protected void btnCloseLogic() {
          new AbstractNotificationUtils() {
            @Override
            public void noMethod() {
                // do nothing
                LOGGER.debug("Inside Overriden method: do nothing");
            }

            @Override
            /**
             * The method is triggered when Yes button of the message box is
             * pressed .
             *
             * @param buttonId The buttonId of the pressed button.
             */
            public void yesMethod() {
               close();  
            }
        }.getConfirmationMessage("Confirmation",
        "Closing the popup will not submit an alternate Contract Holder and/or Brand back to the Sales Projection screen.\n  Are you sure you want to continue?");
    }
    
    
      public static String getImportedContract() {
        return importedContract;
    }

    public static void setImportedContract(String importedContract) {
        AlternateHistoryLookup.importedContract = importedContract;
    }

    public static String getImportedBrand() {
        return importedBrand;
    }

    public static void setImportedBrand(String importedBrand) {
        AlternateHistoryLookup.importedBrand = importedBrand;
    }

    @Override
    protected void configureResultTable(ExtPagedTable results, String indicator) {
        LOGGER.debug("Inside Overriden method: do nothing");
    }
     

}

