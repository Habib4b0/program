package com.stpl.app.global.priceschedule.ui.view;

import com.stpl.app.global.common.dto.SessionDTO;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jboss.logging.Logger;

import com.stpl.app.global.priceschedule.dto.PSDTO;
import com.stpl.app.global.priceschedule.dto.PSIFPDTO;
import com.stpl.app.global.priceschedule.logic.PSLogic;
import com.stpl.app.global.priceschedule.ui.form.PSTabsheetForm;
import com.stpl.app.global.priceschedule.util.FieldNameUtils;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.stpl.portal.service.UserLocalServiceUtil;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * View Class for Price Schedule ADD.
 *
 * @author manikanta
 */
public class PSView extends VerticalLayout implements View {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(PSView.class);

    /**
     * The available item result bean.
     */
    private final BeanItemContainer<PSIFPDTO> availableItemResultBean = new BeanItemContainer<PSIFPDTO>(PSIFPDTO.class);

    /**
     * The selected item result bean.
     */
    private final BeanItemContainer<PSIFPDTO> selectedItemResultBean = new BeanItemContainer<PSIFPDTO>(PSIFPDTO.class);

    /**
     * The item details results bean.
     */
    private final BeanItemContainer<PSIFPDTO> itemDetailsResultsBean = new BeanItemContainer<PSIFPDTO>(PSIFPDTO.class);

    /**
     * The ps master.
     */
    private PSDTO psMaster = new PSDTO();

    /**
     * The binder.
     */
    private ErrorfulFieldGroup binder = new ErrorfulFieldGroup(new BeanItem<PSDTO>(psMaster));

    /**
     * The Constant NAME.
     */
    public static final String NAME = ConstantsUtils.ADD;

    final PSLogic psLogic;

    /**
     * @return the binder
     */
    public ErrorfulFieldGroup getBinder() {
        return binder;
    }

    /**
     * @param binder the binder to set
     */
    public void setBinder(final ErrorfulFieldGroup binder) {
        this.binder = binder;
    }

    /**
     * @return the logger
     */
    public static Logger getLogger() {
        return LOGGER;
    }

    /**
     * @return the name
     */
    public static String getName() {
        return NAME;
    }

    /**
     * @return the availableItemResultBean
     */
    public BeanItemContainer<PSIFPDTO> getAvailableItemResultBean() {
        return availableItemResultBean;
    }

    /**
     * @return the selectedItemResultBean
     */
    public BeanItemContainer<PSIFPDTO> getSelectedItemResultBean() {
        return selectedItemResultBean;
    }

    /**
     * @return the itemDetailsResultsBean
     */
    public BeanItemContainer<PSIFPDTO> getItemDetailsResultsBean() {
        return itemDetailsResultsBean;
    }

    /**
     * @return the psMaster
     */
    public PSDTO getPsMaster() {
        return psMaster;
    }

    PSTabsheetForm addPS;
    SessionDTO sessionDTO;

    /**
     * The Constructor.
     */
    public PSView(final SessionDTO sessionDTO) throws PortalException, SystemException {
        super();
        setStyleName("bootstrap-company");
        this.removeAllComponents();
        this.sessionDTO=sessionDTO;
        psLogic = new PSLogic(this.sessionDTO);
        setSpacing(true);
    }

    /**
     * Entered when View is initialized.
     *
     * @param event the event
     */
    public void enter(final ViewChangeEvent event) {
        try {
            LOGGER.debug("inside PSView");
            final Date tempDate = new Date();
            final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            final SimpleDateFormat fmtID = new SimpleDateFormat("MMddyyyyhhmmssms");
            
            sessionDTO.setSessionDate(fmt.format(tempDate));
            sessionDTO.setUiSessionId(fmtID.format(tempDate));
            
            this.removeAllComponents();
            binder = new ErrorfulFieldGroup(new BeanItem<PSDTO>(new PSDTO()));
            String mode = (String) sessionDTO.getMode();
            
            
            String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            PSDTO dto = new PSDTO();
            User createdUserID = UserLocalServiceUtil.getUser(Long.valueOf(userId));
            dto.setCreatedBy(createdUserID.getFullName());
            dto.setModifiedBy(createdUserID.getFullName());
            selectedItemResultBean.removeAllItems();

            LOGGER.debug("Application logging to the mode :" + mode);

            if (ConstantsUtils.ADD.equals(mode)) {
                psMaster = new PSDTO();
                psMaster.setItemPricingQualifierMap(psLogic.getItemPricingQualifiers());
                addPS = new PSTabsheetForm(psMaster, binder, availableItemResultBean, selectedItemResultBean, itemDetailsResultsBean, mode, sessionDTO);
                binder.setItemDataSource(new BeanItem<PSDTO>(new PSDTO()));
                binder.getField(FieldNameUtils.PRICE_SCHEDULE_ID).focus();
                binder.getField(FieldNameUtils.INTERNAL_NOTES).setReadOnly(true);
                binder.getField("record").setReadOnly(true);
                addPS.addModeConfig();
                availableItemResultBean.removeAllItems();
                itemDetailsResultsBean.removeAllItems();

            } else if (ConstantsUtils.EDIT.equals(mode)) {
                final int systemId = (Integer) sessionDTO.getSystemId();
                psMaster = psLogic.getPriceschedulesById(systemId);
                psMaster.setItemPricingQualifierMap(psLogic.getItemPricingQualifiers());
                binder.setItemDataSource(new BeanItem<PSDTO>(psMaster));
                psLogic.addToTempPSDetailsEdit(psMaster.getPriceScheduleSystemId());
                availableItemResultBean.removeAllItems();
                selectedItemResultBean.addAll((List<PSIFPDTO>)psLogic.getSelectedItemListTable(systemId, false));
                addPS = new PSTabsheetForm(psMaster, binder, availableItemResultBean, selectedItemResultBean, itemDetailsResultsBean, mode, sessionDTO);
                addPS.editModeConfig();
                binder.getField(FieldNameUtils.PRICE_SCHEDULE_ID).focus();
                binder.getField(FieldNameUtils.INTERNAL_NOTES).setReadOnly(true);
            } else if (ConstantsUtils.VIEW.equals(mode)) {

                final int systemId = (Integer) sessionDTO.getSystemId();
                psMaster = psLogic.getPriceschedulesById(systemId);
                psMaster.setItemPricingQualifierMap(psLogic.getItemPricingQualifiers());
                binder.setItemDataSource(new BeanItem<PSDTO>(psMaster));
                selectedItemResultBean.addAll((List<PSIFPDTO>)psLogic.getSelectedItemListTable(systemId, false));
                psLogic.addToTempPSDetailsEdit(psMaster.getPriceScheduleSystemId());
                addPS = new PSTabsheetForm(psMaster, binder, availableItemResultBean, selectedItemResultBean, itemDetailsResultsBean, mode, sessionDTO);
                binder.setReadOnly(true);
                binder.getField("parentPriceScheduleId").setEnabled(false);
                binder.getField("parentPriceScheduleName").setEnabled(false);
                binder.getField("record").setReadOnly(false);
                addPS.viewModeConfig();
            }
            addComponent(addPS);
            addPS.getInfoTab().setDefaultFocus(mode);
        } catch (SystemException sysException) {    
            final String errorMsg = ErrorCodeUtil.getErrorMessage(sysException);
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
        } catch (PortalException portException) {   
            LOGGER.error(portException);
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
