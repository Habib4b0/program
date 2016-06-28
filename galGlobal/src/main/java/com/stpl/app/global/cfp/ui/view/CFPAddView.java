package com.stpl.app.global.cfp.ui.view;

import java.util.Date;

import org.jboss.logging.Logger;

import com.stpl.app.global.cfp.dto.CFPCompanyDTO;
import com.stpl.app.global.cfp.logic.CFPSearchLogic;
import com.stpl.app.global.cfp.ui.form.CFPAddForm;
import com.stpl.app.global.cfp.ui.form.CFPInformation;
import com.stpl.app.global.cfp.util.CommonUtils;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonLazyUtilDTO;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import org.apache.commons.lang.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class CFPAddView.
 */
public class CFPAddView extends VerticalLayout implements View {

    /**
     * The Constant NAME.
     */
    public static final String NAME = ConstantsUtils.ADD;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(CFPAddView.class);
    private final CFPSearchLogic cfpLogic;
    SessionDTO sessionDTO;
    /**
     * The cfp master.
     */
    private static CFPCompanyDTO cfpMaster = new CFPCompanyDTO();
    /**
     * The binder.
     */
    private ErrorfulFieldGroup binder = new ErrorfulFieldGroup(new BeanItem<CFPCompanyDTO>(cfpMaster));
    CFPAddForm cfpAddForm;

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
     * @param binder the binder to set
     */
    public void setBinder(final ErrorfulFieldGroup binder) {
        this.binder = binder;
    }
    CFPInformation cfpInfo;

    /**
     * Constructor.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public CFPAddView(final SessionDTO sessionDTO) throws SystemException, PortalException, Exception {
        super();
        setSpacing(true);
        this.sessionDTO=sessionDTO;
        cfpLogic = new CFPSearchLogic(this.sessionDTO);
        this.removeAllComponents();
        setStyleName("bootstrap-company");
    }

    /**
     * This method is always called before the view is shown on screen.
     *
     * @param event the event
     */
    public void enter(final ViewChangeEvent event) {
        LOGGER.info("Entering CFPAddView enter");
        try {
            this.removeAllComponents();

            CommonLazyUtilDTO dto = new CommonLazyUtilDTO();
            
            sessionDTO.setUiSessionId(CommonUtils.getDateForSession());
            sessionDTO.setSessionDate(com.stpl.app.global.company.util.CommonUtils.getDateTime("yyyy/MM/dd", new Date()));
            
            if (sessionDTO.getMode().equals(ConstantsUtils.EDIT)) {
                final int systemId = sessionDTO.getSystemId();
                cfpMaster = cfpLogic.getCFPById(Integer.valueOf(systemId));
                binder = new ErrorfulFieldGroup(new BeanItem<CFPCompanyDTO>(cfpMaster));
                cfpLogic.addToTempCfpDetailsEdit(cfpMaster.getCompanyFamilyPlanSystemId());
       
                cfpAddForm = new CFPAddForm(cfpMaster, binder,sessionDTO);
                 binder.setItemDataSource(new BeanItem<CFPCompanyDTO>(cfpMaster));
                
              
                if (String.valueOf(cfpMaster.getCompanyFamilyPlanDesignation()) != null) {
                    if (String.valueOf(cfpMaster.getCompanyFamilyPlanDesignation()).equalsIgnoreCase("Child")) {
                        binder.getField("parentCompanyFamilyPlanName").setEnabled(true);
                        binder.getField("parentCompanyFamilyPlanId").setEnabled(true);
                        binder.getField("parentCompanyFamilyPlanName").setReadOnly(false);
                        binder.getField("parentCompanyFamilyPlanId").setReadOnly(false);
                    }
                } else {
                    binder.getField("parentCompanyFamilyPlanId").setReadOnly(true);
                    binder.getField("parentCompanyFamilyPlanId").setEnabled(false);
                    binder.getField("parentCompanyFamilyPlanName").setReadOnly(true);
                    binder.getField("parentCompanyFamilyPlanName").setEnabled(false);
                }
                 
                binder.getField("createdBy").setReadOnly(true);
                binder.getField("modifiedBy").setReadOnly(true);
                binder.getField("modifiedDate").setReadOnly(true);
                binder.getField("createdDate").setReadOnly(true);
               // binder.getField("salesInclusion").setReadOnly(true);

            } else if (sessionDTO.getMode().equals("View")) {
                binder = new ErrorfulFieldGroup(new BeanItem<CFPCompanyDTO>(cfpMaster));
                final int systemId = sessionDTO.getSystemId();
                // getting cfp master table object
                cfpMaster = cfpLogic.getCFPById(Integer.valueOf(systemId));


                LOGGER.info("Ending CFP VIEW FORM");

                cfpAddForm = new CFPAddForm(cfpMaster, binder,sessionDTO);
                binder.setItemDataSource(new BeanItem<CFPCompanyDTO>(cfpMaster));
                binder.setReadOnly(true);

                binder.getField("parentCompanyFamilyPlanName").setEnabled(false);
                binder.getField("parentCompanyFamilyPlanId").setEnabled(false);

                //VIEW

            } else {
                //ADD
                binder = new ErrorfulFieldGroup(new BeanItem<CFPCompanyDTO>(new CFPCompanyDTO()));
                dto.setUserId(String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID)));
                dto.setSessionId(sessionDTO.getUiSessionId());
                dto.setDate(sessionDTO.getSessionDate());
                cfpLogic.deleteAllTempCFPDetailsInEdit(dto);
                cfpMaster = new CFPCompanyDTO();
                binder.setItemDataSource(new BeanItem<CFPCompanyDTO>(cfpMaster));
                cfpAddForm = new CFPAddForm(cfpMaster, binder,sessionDTO);

            }
            
            addComponent(cfpAddForm);

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
        } catch (Exception pe) {
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
        }
        LOGGER.info("Ending CFPAddView enter");
    }
}
