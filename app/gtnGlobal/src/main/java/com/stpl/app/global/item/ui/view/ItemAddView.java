package com.stpl.app.global.item.ui.view;

import com.stpl.app.global.common.dto.SessionDTO;
import org.jboss.logging.Logger;

import com.stpl.app.global.item.dto.ItemIrtIdentifierDTO;
import com.stpl.app.global.item.dto.ItemMasterDTO;
import com.stpl.app.global.item.dto.ItemPricingDTO;
import com.stpl.app.global.item.logic.ItemSearchLogic;
import com.stpl.app.global.item.ui.form.AddForm;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;

// TODO: Auto-generated Javadoc
/**
 * The Class ItemAddView.
 */
public class ItemAddView extends VerticalLayout implements View {

    /**
     * The Constant NAME.
     */
    public static final String NAME = ConstantsUtils.ADD;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ItemAddView.class);
    /**
     * The identifier results bean.
     */
    private final BeanItemContainer<ItemIrtIdentifierDTO> identifierResultsBean = new BeanItemContainer<>(ItemIrtIdentifierDTO.class);
    /**
     * The pricing results bean.
     */
    private final BeanItemContainer<ItemPricingDTO> pricingResultsBean = new BeanItemContainer<>(ItemPricingDTO.class);
    /**
     * The binder.
     */
    private ErrorfulFieldGroup binder = new ErrorfulFieldGroup(new BeanItem<>(new ItemMasterDTO()));
    /**
     * The item master dto.
     */
    private ItemMasterDTO itemMasterDTO = new ItemMasterDTO();
    
    final ItemSearchLogic itemLogic = new ItemSearchLogic();
    
    SessionDTO sessionDTO;

    /**
     * Gets the binder.
     *
     * @return the binder
     */
    public ErrorfulFieldGroup getBinder() {
        return binder;
    }

    /**
     * Sets the binder.
     *
     * @param binder the new binder
     */
    public void setBinder(final ErrorfulFieldGroup binder) {
        this.binder = binder;
    }

    /**
     * Gets the identifier results bean.
     *
     * @return the identifier results bean
     */
    public BeanItemContainer<ItemIrtIdentifierDTO> getIdentifierResultsBean() {
        return identifierResultsBean;
    }

    /**
     * Gets the pricing results bean.
     *
     * @return the pricing results bean
     */
    public BeanItemContainer<ItemPricingDTO> getPricingResultsBean() {
        return pricingResultsBean;
    }

    /**
     * The Constructor.
     * 
     * @throws PortalException
     * @throws SystemException
     * @throws Exception 
     */
    public ItemAddView(final SessionDTO sessionDTO) {
        super();
        setStyleName("bootstrap-company");
        setSpacing(true);
        this.sessionDTO=sessionDTO;
    }

    /**
     * Enter method.
     *
     * @param event the event
     */
    public void enter(final ViewChangeEvent event) {
        try {
            String mode = sessionDTO.getMode();
            LOGGER.debug("Entering Item Add view with mode :" + mode);
            this.removeAllComponents();
            binder = new ErrorfulFieldGroup(new BeanItem<>(new ItemMasterDTO()));
            identifierResultsBean.removeAllItems();
            pricingResultsBean.removeAllItems();
            
            if (ConstantsUtils.VIEW.equals(mode)) {
                final int systemId = sessionDTO.getSystemId();
                itemMasterDTO = itemLogic.getItemMasterById(systemId);
                identifierResultsBean.addAll(itemMasterDTO.getItemIdentifierList());
                pricingResultsBean.addAll(itemMasterDTO.getPricingIdentifierList());
                addComponent(new AddForm(itemMasterDTO, binder, identifierResultsBean, pricingResultsBean,mode,sessionDTO));
                binder.setItemDataSource(new BeanItem<>(itemMasterDTO));
              
                binder.setReadOnly(true);
            }
            else if (ConstantsUtils.EDIT.equals(mode)) {
                final int systemId = sessionDTO.getSystemId();
                itemMasterDTO = itemLogic.getItemMasterById(systemId);
                identifierResultsBean.addAll(itemMasterDTO.getItemIdentifierList());
                pricingResultsBean.addAll(itemMasterDTO.getPricingIdentifierList());
                addComponent(new AddForm(itemMasterDTO, binder, identifierResultsBean, pricingResultsBean,mode,sessionDTO));
                binder.setItemDataSource(new BeanItem<>(itemMasterDTO));
                binder.getField("createdDate").setReadOnly(true);
                binder.getField("createdBy").setReadOnly(true);
                binder.getField("modifiedDate").setReadOnly(true);
                binder.getField("systemId").setReadOnly(true);
                binder.getField("modifiedBy").setReadOnly(true); 
                binder.getField("brandId").setReadOnly(true); 
                binder.getField("displayBrand").setReadOnly(true);
                binder.getField("itemBatchId").setReadOnly(true);
                //Added for GAL-9095
                boolean authorizedGeneric = "No".equalsIgnoreCase(String.valueOf(binder.getField("authorizedGeneric").getValue()));
                binder.getField("authorizedGenericStartDate").setEnabled(!authorizedGeneric);
                binder.getField("authorizedGenericEndDate").setEnabled(!authorizedGeneric);
                boolean newFormulationIndicator = "No".equalsIgnoreCase(String.valueOf(binder.getField("newFormulationIndicator").getValue()));
                binder.getField("newFormulationStartDate").setEnabled(!newFormulationIndicator);
                binder.getField("newFormulationEndDate").setEnabled(!newFormulationIndicator);
                boolean pediatricExclusiveIndicator = "No".equalsIgnoreCase(String.valueOf(binder.getField("pediatricExclusiveIndicator").getValue()));
                binder.getField("pediatricExclusiveStartDate").setEnabled(!pediatricExclusiveIndicator);
                binder.getField("pediatricExclusiveEndDate").setEnabled(!pediatricExclusiveIndicator);
                boolean clottingFactorIndicator = "No".equalsIgnoreCase(String.valueOf(binder.getField("clottingFactorIndicator").getValue()));
                binder.getField("clottingFactorStartDate").setEnabled(!clottingFactorIndicator);
                binder.getField("clottingFactorEndDate").setEnabled(!clottingFactorIndicator); // Ends Here
            }else{
                   itemMasterDTO=new ItemMasterDTO();
                   binder.setItemDataSource(new BeanItem<>(new ItemMasterDTO()));
                  addComponent(new AddForm(itemMasterDTO, binder, identifierResultsBean, pricingResultsBean,mode,sessionDTO));
            }
        } catch (SystemException se) {
            LOGGER.error(se);
            final String errorMsg = ErrorCodeUtil.getErrorMessage(se);
            LOGGER.error(errorMsg);
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg, new MessageBoxListener() {     
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
        } catch (PortalException pe) {
            LOGGER.error(pe);
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1010), new MessageBoxListener() {     
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
        } catch (Exception exception) {

            LOGGER.error(exception);
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1010), new MessageBoxListener() {     
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
            LOGGER.error(exception);
        }
    }
}
