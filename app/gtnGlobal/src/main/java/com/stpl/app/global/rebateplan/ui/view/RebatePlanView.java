package com.stpl.app.global.rebateplan.ui.view;

import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.rebateplan.dto.RebatePlanMasterDTO;
import com.stpl.app.global.rebateplan.dto.RebatePlanTierResults;
import com.stpl.app.global.rebateplan.logic.RebatePlanSearchLogic;
import com.stpl.app.global.rebateplan.ui.form.RebatePlanForm;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
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
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class RebatePlanAddView.
 */
public class RebatePlanView extends VerticalLayout implements View {

    /**
     * The Constant NAME.
     */
    public static final String NAME = ConstantsUtils.ADD;

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(RebatePlanView.class);

    /**
     * The rebate plan master.
     */
    public static RebatePlanMasterDTO rebatePlanMaster = new RebatePlanMasterDTO();

    /**
     * The binder.
     */
    private ErrorfulFieldGroup binder = new ErrorfulFieldGroup(new BeanItem<RebatePlanMasterDTO>(new RebatePlanMasterDTO()));
    /**
     * The rebate plan tier results.
     */
    private final BeanItemContainer<RebatePlanTierResults> rebatePlanTierResults = new BeanItemContainer<RebatePlanTierResults>(RebatePlanTierResults.class);
    /**
     * The rebate plan master dto.
     */
    private RebatePlanMasterDTO rebatePlanMasterDTO = new RebatePlanMasterDTO();
    /**
     * The rebate plan Form
     */

    private RebatePlanForm rebatePlanAddForm;

    private int value;

    SessionDTO sessionDTO;
    /**
     * The Constructor.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public RebatePlanView(final SessionDTO sessionDTO) throws SystemException, PortalException {
        super();
        setStyleName("bootstrap-company");
        setSpacing(true);
        this.sessionDTO=sessionDTO;
    }

    /**
     * Default Method.
     *
     * @param event the event
     */
    public void enter(final ViewChangeEvent event) {
        try {            
            LOGGER.debug("Enter Method");
            this.removeAllComponents();
            binder = new ErrorfulFieldGroup(new BeanItem<RebatePlanMasterDTO>(new RebatePlanMasterDTO()));
            if ((ConstantsUtils.VIEW).equals(sessionDTO.getMode())) {
                LOGGER.debug("View Module");
                final RebatePlanSearchLogic rebatePlanLogic = new RebatePlanSearchLogic();
                final String idValue = String.valueOf(sessionDTO.getSystemId());
                final int systemId = Integer.valueOf(idValue.replaceAll("\\,", ""));
                LOGGER.debug("getRebatePlanMasterById method Initiated, systemId= " + systemId);
                rebatePlanMasterDTO = rebatePlanLogic.getRebatePlanMasterById(systemId);
                binder.setItemDataSource(new BeanItem<RebatePlanMasterDTO>(rebatePlanMasterDTO));
                rebatePlanMasterDTO.setItemPricingQualifierMap(RebatePlanSearchLogic.getItemPricingQualifiers());
                rebatePlanTierResults.removeAllItems();
                rebatePlanTierResults.addAll(rebatePlanLogic.getRebatePlanTiersfromId(rebatePlanMasterDTO.getRebatePlanSystemId(),rebatePlanMasterDTO));
                if (rebatePlanTierResults != null && rebatePlanTierResults.lastItemId() != null && rebatePlanTierResults.lastItemId().getTierTo() != null) {
                    BigDecimal tierToValue = rebatePlanTierResults.lastItemId().getTierTo();
                    rebatePlanMasterDTO.setTierFrom((tierToValue.add(BigDecimal.valueOf(NumericConstants.DOUBLE_ZERO_ZERO_ONE))).setScale(NumericConstants.TWO, RoundingMode.HALF_UP).toPlainString());
                }
                binder.setReadOnly(true);
                addComponent(new RebatePlanForm(rebatePlanMasterDTO, binder, rebatePlanTierResults,sessionDTO));
            } else if (ConstantsUtils.EDIT.equals(sessionDTO.getMode())||ConstantsUtils.COPY.equals(sessionDTO.getMode())) {
                LOGGER.debug("Edit Module");
                this.removeAllComponents();
                final RebatePlanSearchLogic rebatePlanLogic = new RebatePlanSearchLogic();
                final String idValue = String.valueOf(sessionDTO.getSystemId());
                final int rebatePlanMasterId = Integer.valueOf(idValue);
                rebatePlanMasterDTO = rebatePlanLogic.getRebatePlanMasterById(rebatePlanMasterId);
                if (rebatePlanMasterDTO.getFormulaType().equals(StringUtils.EMPTY) || rebatePlanMasterDTO.getFormulaType() == null) {
                    rebatePlanMasterDTO.setFormulaType("Simple");
                }
                if (ConstantsUtils.COPY.equals(sessionDTO.getMode())) {
                    rebatePlanMasterDTO.setRebatePlanSystemId(ConstantsUtils.ZERO_INT);
                    rebatePlanMasterDTO.setRebatePlanId(StringUtils.EMPTY);
                    rebatePlanMasterDTO.setRebatePlanStatus(new HelperDTO(0, ConstantsUtils.SELECT_ONE));
                    rebatePlanMasterDTO.setRebatePlanName(StringUtils.EMPTY);
                    rebatePlanMasterDTO.setRebatePlanNo(StringUtils.EMPTY);
                    rebatePlanMasterDTO.setFormulaType("Simple");
                }
                binder.setItemDataSource(new BeanItem<RebatePlanMasterDTO>(rebatePlanMasterDTO));
                rebatePlanMasterDTO.setItemPricingQualifierMap(RebatePlanSearchLogic.getItemPricingQualifiers());
                rebatePlanTierResults.removeAllItems();
                rebatePlanTierResults.addAll(rebatePlanLogic.getRebatePlanTiersfromId(rebatePlanMasterId,rebatePlanMasterDTO));
                if (rebatePlanTierResults != null && rebatePlanTierResults.lastItemId() != null && rebatePlanTierResults.lastItemId().getTierTo() != null) {
                    BigDecimal tierToValue = rebatePlanTierResults.lastItemId().getTierTo();
                    rebatePlanMasterDTO.setTierFrom((tierToValue.add(BigDecimal.valueOf(NumericConstants.DOUBLE_ZERO_ZERO_ONE))).setScale(NumericConstants.TWO, RoundingMode.HALF_UP).toPlainString());
                }
                value = rebatePlanTierResults.size() + 1;
                binder = new ErrorfulFieldGroup(new BeanItem<RebatePlanMasterDTO>(rebatePlanMasterDTO));
                rebatePlanMasterDTO.setTierLevel(value);
                rebatePlanAddForm = new RebatePlanForm(rebatePlanMasterDTO, binder, rebatePlanTierResults,sessionDTO);
                addComponent(rebatePlanAddForm);
                rebatePlanAddForm.setDefaultFocus();
            } else {
                rebatePlanTierResults.removeAllItems();
                binder.setItemDataSource(new BeanItem<RebatePlanMasterDTO>(new RebatePlanMasterDTO()));
                RebatePlanMasterDTO dto = new RebatePlanMasterDTO();
                dto.setItemPricingQualifierMap(RebatePlanSearchLogic.getItemPricingQualifiers());
                rebatePlanAddForm = new RebatePlanForm(dto, binder, rebatePlanTierResults,sessionDTO);
                addComponent(rebatePlanAddForm);
                rebatePlanAddForm.setDefaultFocus();
            }
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
            LOGGER.error(ex);            
        } catch (Exception ex) {
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
            LOGGER.error(ex);            
        }

    }

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
}
