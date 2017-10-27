package com.stpl.app.global.rebateschedule.ui.view;

import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.rebateschedule.dto.IFPDetailsDTO;
import com.stpl.app.global.rebateschedule.dto.ItemDetailsDTO;
import com.stpl.app.global.rebateschedule.dto.RebateScheduleMasterDTO;
import com.stpl.app.global.rebateschedule.logic.RebateScheduleLogic;
import com.stpl.app.global.rebateschedule.ui.form.RebateScheduleAddForm;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class to implement the Rebate Schedule Add View.
 */
public class RebateScheduleAddView extends VerticalLayout implements View {

    /**
     * The Constant NAME.
     */
    public static final String NAME = ConstantsUtils.ADD;

    /**
     * The item family plan masters.
     */
    private final BeanItemContainer<IFPDetailsDTO> selectedItemResultBean = new BeanItemContainer<>(
            IFPDetailsDTO.class);

    /**
     * The item masters.
     */
    private final BeanItemContainer<ItemDetailsDTO> itemResultBean = new BeanItemContainer<>(
            ItemDetailsDTO.class);

    /**
     * The rebate schedule master.
     */
    private RebateScheduleMasterDTO rebateScheduleMaster = new RebateScheduleMasterDTO();

    /**
     * The binder.
     */
    private ErrorfulFieldGroup binder = new ErrorfulFieldGroup(
            new BeanItem<>(rebateScheduleMaster));

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(RebateScheduleAddView.class.getName());
    RebateScheduleAddForm rsAddForm;

     RebateScheduleLogic rebateScheduleLogic;
    SessionDTO sessionDTO;

    /**
     * Instantiates a new rebate schedule add view.
     */
    public RebateScheduleAddView(final SessionDTO sessionDTO) {
        super();
        setSpacing(true);
try{
        this.sessionDTO=sessionDTO;
        rebateScheduleLogic = new RebateScheduleLogic(this.sessionDTO);

} catch (Exception e) {
            LOGGER.error(e);
        }
    }

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
     * @return the selectedItemResultBean
     */
    public BeanItemContainer<IFPDetailsDTO> getItemFamilyPlanMasters() {
        return selectedItemResultBean;
    }

    /**
     * @return the itemResultBean
     */
    public BeanItemContainer<ItemDetailsDTO> getItemMasters() {
        return itemResultBean;
    }

    /**
     * @return the rebateScheduleMaster
     */
    public RebateScheduleMasterDTO getRebateScheduleMaster() {
        return rebateScheduleMaster;
    }

    /**
     * Method to update the item and rebind all fields to the properties in the
     * new item using binder.
     *
     * @param event the event
     */
    public void enter(final ViewChangeEvent event) {
        try {
            String mode = String.valueOf(sessionDTO.getMode());
            LOGGER.debug("Enters enter method of add mode :" + mode);
            this.removeAllComponents();
            final Date tempDate = new Date();
            final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            final SimpleDateFormat fmtID = new SimpleDateFormat("MMddyyyyhhmmssms");
            
            sessionDTO.setSessionDate(fmt.format(tempDate));
            sessionDTO.setUiSessionId(fmtID.format(tempDate));
            
            binder = new ErrorfulFieldGroup(new BeanItem<>(
                    new RebateScheduleMasterDTO()));
            selectedItemResultBean.removeAllItems();
            itemResultBean.removeAllItems();
            rebateScheduleMaster = new RebateScheduleMasterDTO();

            if (ConstantsUtils.ADD.equals(mode)) {
                rebateScheduleLogic.removeAllFromTempTable(true);
                rsAddForm = new RebateScheduleAddForm(rebateScheduleMaster, binder, selectedItemResultBean, itemResultBean, mode, sessionDTO);
                
            } else if (ConstantsUtils.VIEW.equals(mode)) {
                final String idValue = String.valueOf(sessionDTO.getSystemId());
                final int systemId = Integer.valueOf(idValue);
                rebateScheduleLogic.loadFormulaToImtdRsdFr(Integer.valueOf(idValue));
                rebateScheduleMaster = rebateScheduleLogic.getRebateScheduleMasterById(systemId);
                binder.setItemDataSource(new BeanItem<>(rebateScheduleMaster));
                selectedItemResultBean.addAll(rebateScheduleLogic.getItemFamilyPlanFromRSID(systemId));

                itemResultBean.addAll(rebateScheduleLogic.getItemDetails(systemId,StringUtils.EMPTY));
                rsAddForm = new RebateScheduleAddForm(rebateScheduleMaster, binder, selectedItemResultBean, itemResultBean, mode, sessionDTO);                
                
                binder.setReadOnly(true);
                binder.getField("calculationRule").setEnabled(false);
                binder.getField("rebateScheduleTransRefNo").setEnabled(false);
                binder.getField("evaluationRuleAssociation").setEnabled(false);
            } else if (ConstantsUtils.EDIT.equals(mode) || (ConstantsUtils.COPY).equals(mode)) {

                final String idValue = String.valueOf(sessionDTO.getSystemId());
                final int systemId = Integer.valueOf(idValue);
                rebateScheduleLogic.loadItemDetailsFromIfp(idValue);
                rebateScheduleMaster = rebateScheduleLogic.getRebateScheduleMasterById(systemId);
                binder.setItemDataSource(new BeanItem<>(rebateScheduleMaster));
                selectedItemResultBean.addAll(rebateScheduleLogic.getItemFamilyPlanFromRSID(systemId));
                rsAddForm = new RebateScheduleAddForm(rebateScheduleMaster, binder, selectedItemResultBean, itemResultBean, mode, sessionDTO);
                if (ConstantsUtils.COPY.equals(sessionDTO.getMode())) {
                    rebateScheduleMaster.setRebateScheduleId(StringUtils.EMPTY);
                    rebateScheduleMaster.setRebateScheduleName(StringUtils.EMPTY);
                    rebateScheduleMaster.setRebateScheduleNo(StringUtils.EMPTY);
                    rebateScheduleMaster.setModifiedBy(StringUtils.EMPTY);
                }

            }                
            addComponent(rsAddForm);
            rsAddForm.getInfoTab().setDefaultFocus();
            binder.getField("internalNotes").setEnabled(false);
            if (ConstantsUtils.VIEW.equals(mode)) binder.getField("parentRebateScheduleId").setEnabled(false);         
            LOGGER.debug(" Ends enter method of add view");
        } catch (SystemException ex) {
            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
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
        } catch (PortalException ex) { 
            LOGGER.error(ex);
        } catch (Exception ex) {  
            LOGGER.error(ex);
        }
    }

}
