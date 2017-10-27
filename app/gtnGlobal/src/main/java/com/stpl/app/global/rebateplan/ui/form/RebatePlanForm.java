package com.stpl.app.global.rebateplan.ui.form;

import com.stpl.app.global.abstractsearch.view.AbstractSearchView;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.common.ui.InformationLayout;
import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.rebateplan.dto.RebatePlanMasterDTO;
import com.stpl.app.global.rebateplan.dto.RebatePlanTierResults;
import com.stpl.app.global.rebateplan.logic.RebatePlanSearchLogic;
import com.stpl.app.global.rebateplan.ui.view.RebatePlanView;
import com.stpl.app.global.rebateplan.util.FieldNameUtils;
import com.stpl.app.global.rebateplan.util.LabelUtils;
import com.stpl.app.model.RebatePlanMaster;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.NotesTabForm;
import com.stpl.app.ui.StplCustomComponent;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.GeneralCommonUtils;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.app.util.FunctionNameUtil;
import com.stpl.app.util.TabNameUtil;
import com.stpl.app.util.UISecurityUtil;
import com.stpl.domain.global.base.AddBaseForm;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 * The Class RebatePlanAddForm.
 */
public final class RebatePlanForm extends StplCustomComponent implements AddBaseForm {

    /**
     * Serial Version Id
     */
    private static final long serialVersionUID = 1L;
    /**
     * The Constant NAME.
     */
    public static final String NAME = ConstantsUtils.VIEW;
    /**
     * The error msg.
     */
    @UiField("errorMsg")
    private ErrorLabel errorMsg;
    /**
     * The rebate plan logic.
     */
    private RebatePlanSearchLogic rebatePlanLogic = new RebatePlanSearchLogic();

    /**
     * The special character.
     */
    public static String specialCharacter = "([0-9|a-z|A-Z|\\.|\\,|\\_|\\-|\\@|\\#|\\$|\\+|\\'|\\&|\\%|\\s|\\/|\\(|\\!|\\)])*";

    /**
     * The binder.
     */
    private ErrorfulFieldGroup binder;

    /**
     * The search resulbeans.
     */
    private BeanItemContainer<RebatePlanTierResults> searchResultbeans;

    /**
     * The rebate plan master dto.
     */
    private RebatePlanMasterDTO rebatePlanMasterDTO;

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(RebatePlanForm.class);
    /**
     * The Rebate Plan Calculation
     */
    RebatePlanCalculation rebatePlanCalculation;
    /**
     * The Rebate plan Information
     */
    RebatePlanInformation rebatePlanInformation;
    /**
     * The Notes Tab Form.
     */
    NotesTabForm notesTabForm;
    /**
     * The TabSheet
     */
    @UiField("tabSheet")
    TabSheet tabsheet;
    /**
     * The HorizontalLayout
     */
    @UiField("buttonLayout")
    HorizontalLayout buttonLayout;
    /**
     * The Save Button
     */
    @UiField("saveBtn")
    Button saveButton;
    /**
     * The Back Button
     */
    @UiField("backBtn")
    Button backButton;
    /**
     * The Delete Button
     */
    @UiField("deleteBtn")
    Button deleteButton;
    /**
     * The Reset Button
     */
    @UiField("resetBtn")
    Button resetButton;

    @UiField("informationLayout")
    private VerticalLayout informationLayout;

    CommonUtil commonMsg = CommonUtil.getInstance();
    SessionDTO sessionDTO;
    String mode;

    /**
     * Gets the error msg.
     *
     * @return the error msg
     */
    public ErrorLabel getErrorMsg() {
        return errorMsg;
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
     * Gets the rebate plan logic.
     *
     * @return the rebatePlanLogic
     */
    public RebatePlanSearchLogic getRebatePlanLogic() {
        return rebatePlanLogic;
    }

    /**
     * Sets the rebate plan logic.
     *
     * @param rebatePlanLogic the rebatePlanLogic to set
     */
    public void setRebatePlanLogic(final RebatePlanSearchLogic rebatePlanLogic) {
        this.rebatePlanLogic = rebatePlanLogic;
    }

    /**
     * Gets the search result beans.
     *
     * @return the searchResulbeans
     */
    public BeanItemContainer<RebatePlanTierResults> getSearchResulbeans() {
        return searchResultbeans;
    }

    /**
     * Sets the search resultbeans.
     *
     * @param searchResulbeans the searchResulbeans to set
     */
    public void setSearchResulbeans(final BeanItemContainer<RebatePlanTierResults> searchResulbeans) {
        this.searchResultbeans = searchResulbeans;
    }

    /**
     * The Constructor.
     *
     * @param rebatePlanMasterDTO the rebate plan master dto
     * @param binder the binder
     * @param rebatePlanTierResults
     * @param sessionDTO
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public RebatePlanForm(final RebatePlanMasterDTO rebatePlanMasterDTO, final ErrorfulFieldGroup binder, final BeanItemContainer<RebatePlanTierResults> rebatePlanTierResults, final SessionDTO sessionDTO) throws SystemException, PortalException {
        super();
        this.rebatePlanMasterDTO = rebatePlanMasterDTO;
        this.binder = binder;
        this.sessionDTO = sessionDTO;
        this.mode = this.sessionDTO.getMode();
        this.searchResultbeans = rebatePlanTierResults;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/clara/common/tabsheetform.xml"), this));
        this.rebatePlanInformation = new RebatePlanInformation(binder, rebatePlanMasterDTO, this.sessionDTO);
        this.rebatePlanCalculation = new RebatePlanCalculation(binder, searchResultbeans, this.sessionDTO, rebatePlanMasterDTO);
        init();
    }

    /**
     * Inits the.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    @Override
    public void init() throws SystemException, PortalException {
        addToContent();
        configureOnMode();
        configureBinder();
        configureFields();
    }

    /**
     * Adds the to content.
     *
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public void addToContent() throws PortalException, SystemException {
        informationLayout.addComponent(new InformationLayout("rebate_Plan", rebatePlanMasterDTO.getRebatePlanId(), rebatePlanMasterDTO.getRebatePlanNo(), rebatePlanMasterDTO.getRebatePlanName()));
        addTabSheet();
    }

    /**
     * Displaying Error Message.
     */
    private void configureBinder() {
        binder.setErrorDisplay(errorMsg);
        errorMsg.setId("ErrorMessage");
    }

    /**
     * Adds the tab sheet.
     *
     * @return the tab sheet
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public void addTabSheet() throws PortalException, SystemException {

        tabsheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
        tabsheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        rebatePlanInformation.setCaption(TabNameUtil.REBATE_PLAN_INFO);
        tabsheet.addTab(rebatePlanInformation, TabNameUtil.REBATE_PLAN_INFO);
        rebatePlanCalculation.setCaption(TabNameUtil.RP_CALCULATION);
        tabsheet.addTab(rebatePlanCalculation, TabNameUtil.RP_CALCULATION);
        notesTabForm = new NotesTabForm(binder, "Rebate Plan", "REBATE_PLAN_MASTER", "rebatePlanSystemId", mode);
        notesTabForm.setCaption(TabNameUtil.NOTES);
        tabsheet.addTab(notesTabForm, TabNameUtil.NOTES);
        notesTabForm.refreshTable();

        tabsheet.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
            /**
             * After changing the selected tab, function will be executed.
             */
            @Override
            public void selectedTabChange(final TabSheet.SelectedTabChangeEvent event) {
                try {
                    final String tabname = event.getTabSheet().getSelectedTab().getCaption();
                    if (TabNameUtil.REBATE_PLAN_INFO.equals(tabname)) {
                        rebatePlanInformation.focusRebatePlanId();
                    }
                    if (TabNameUtil.RP_CALCULATION.equals(tabname)) {
                        rebatePlanCalculation.focusRebateBasedOn();
                    }
                    if (TabNameUtil.NOTES.equals(tabname)) {
                        notesTabForm.focusNewNote();
                        notesTabForm.callJavaScriptForButton();
                    }
                } catch (Exception exception) {
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1010), new MessageBoxListener() {
                        /**
                         * The method is triggered when a button of the message
                         * box is pressed .
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
        });

    }

    /**
     * Configure fields.
     *
     * @throws SystemException the system exception.
     * @throws Exception the exception.
     */
    @Override
    public void configureFields() throws SystemException, PortalException {
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final Map<String, AppPermission> functionRpHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.REBATE_PLAN + ConstantsUtils.COMMA + "Common");
        if (functionRpHM.get(FunctionNameUtil.SAVE) != null && ((AppPermission) functionRpHM.get(FunctionNameUtil.SAVE)).isFunctionFlag()) {
            saveButton.setVisible(true);
            configureSaveButton();
        } else {
            saveButton.setVisible(false);
        }

        if (functionRpHM.get(FunctionNameUtil.RESET) != null && ((AppPermission) functionRpHM.get(FunctionNameUtil.RESET)).isFunctionFlag()) {
            resetButton.setVisible(true);
            configureResetButton();
        } else {
            resetButton.setVisible(false);
        }

        if (functionRpHM.get(FunctionNameUtil.DELETE) != null && ((AppPermission) functionRpHM.get(FunctionNameUtil.DELETE)).isFunctionFlag()) {
            deleteButton.setVisible(true);
            configureDeleteButton();
        } else {
            deleteButton.setVisible(false);
        }

        configureBackButton();

    }

    @Override
    public void addLogic() {
        //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Gets the rebate plan master dto.
     *
     * @return the rebate plan master dto
     */
    public RebatePlanMasterDTO getRebatePlanMasterDTO() {
        return rebatePlanMasterDTO;
    }

    /**
     * Sets the rebate plan master dto.
     *
     * @param rebatePlanMasterDTO the new rebate plan master dto
     */
    public void setRebatePlanMasterDTO(final RebatePlanMasterDTO rebatePlanMasterDTO) {
        this.rebatePlanMasterDTO = rebatePlanMasterDTO;
    }

    /**
     * Setting Default Focus based on Rebate Plan Id
     */
    public void setDefaultFocus() {
        rebatePlanInformation.focusRebatePlanId();
    }

    /**
     * Back button.
     */
    private void configureBackButton() {

        backButton.setWidth(ConstantsUtils.BTN_WIDTH);
        backButton.addClickListener(new ClickListener() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            /**
             * Listener is called when an Button is clicked
             *
             */
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("Entering RebatePlan back button operation from Add");
                if (mode.equals(ConstantsUtils.VIEW)) {
                    AbstractSearchView.flag = false;
                    getUI().getNavigator().navigateTo(AbstractSearchView.NAME);
                } else {
                    try {
                        MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFIRMATION, commonMsg.getBackMessage(), new MessageBoxListener() {
                            /**
                             * Called when a Button has been clicked .
                             *
                             */
                            public void buttonClicked(final ButtonId buttonId) {
                                if (buttonId.name().equals(ConstantsUtils.YES)) {
                                    LOGGER.debug("NavigateTo RebatePlanSearchView page");
                                    AbstractSearchView.flag = false;
                                    getUI().getNavigator().navigateTo(AbstractSearchView.NAME);
                                }
                            }
                        }, ButtonId.YES, ButtonId.NO);
                    } catch (Exception exception) {
                        final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1009), new MessageBoxListener() {
                            /**
                             * The method is triggered when a button of the
                             * message box is pressed .
                             *
                             * @param buttonId The buttonId of the pressed
                             * button.
                             */
                            @SuppressWarnings("PMD")
                            public void buttonClicked(final ButtonId buttonId) {
                                // Do Nothing    
                            }
                        }, ButtonId.OK);
                        msg.getButton(ButtonId.OK).focus();
                        LOGGER.error(exception);
                    }
                    LOGGER.debug("Ending RebatePlan back button operation from Add");
                }
            }
        });
        backButton.setErrorHandler(new ErrorHandler() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            /**
             * Back button UI error handler
             */
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error(String.valueOf(event));
            }
        });
    }

    /**
     * Adds the button.
     */
    private void configureSaveButton() {

        // Commit button
        saveButton.setWidth(ConstantsUtils.BTN_WIDTH);
        saveButton.addClickListener(new ClickListener() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            /**
             * Adds the button click listener.
             */
            public void buttonClick(final ClickEvent event) {
                try {
                    if ("Add".equals(mode) || "Copy".equals(mode)) {
                        LOGGER.debug("Entering RebatePlan Save operation from Add");

                        binder.getErrorDisplay().clearError();
                        binder.getFields();
                        binder.commit();
                        boolean flag = false;

                        StringBuilder errorMessage = new StringBuilder("Information for the following Mandatory fields need to be provided:" + "<br>");
                        if (binder.getField(FieldNameUtils.REBATE_PLAN_ID).getValue().toString().trim().isEmpty()) {
                            if (flag) {
                                errorMessage.append(", ");
                            }
                            errorMessage.append(LabelUtils.REBATE_PLAN_ID);
                            flag = true;
                        }
                        if (binder.getField(FieldNameUtils.REBATE_PLAN_NO).getValue().toString().trim().isEmpty()) {
                            if (flag) {
                                errorMessage.append(", ");
                            }
                            errorMessage.append(LabelUtils.REBATE_PLAN_NO);
                            flag = true;
                        }
                        /**
                        * cel-211
                        */
                        if (binder.getField(FieldNameUtils.REBATE_PLAN_TYPE).getValue() == null
                                || ((HelperDTO) binder.getField(FieldNameUtils.REBATE_PLAN_TYPE).getValue()).getId() == 0) {
                            if (flag) {
                                errorMessage.append(", ");
                            }
                            errorMessage.append(LabelUtils.REBATE_PLAN_TYPE);
                            flag = true;
                        }
                        if (binder.getField(FieldNameUtils.REBATE_PLAN_NAME).getValue().toString().trim().isEmpty()) {
                            if (flag) {
                                errorMessage.append(", ");
                            }
                            errorMessage.append(LabelUtils.REBATE_PLAN_NAME);
                            flag = true;
                        }
                        if (binder.getField(FieldNameUtils.REBATE_PLAN_STATUS).getValue() == null
                                || ((HelperDTO) binder.getField(FieldNameUtils.REBATE_PLAN_STATUS).getValue()).getId() == 0) {
                            if (flag) {
                                errorMessage.append(", ");
                            }
                            errorMessage.append(LabelUtils.REBATE_PLAN_STATUS);
                            flag = true;
                        }
                        if (binder.getField(FieldNameUtils.REBATE_BASED_ON).getValue() == null || ((HelperDTO) binder.getField(FieldNameUtils.REBATE_BASED_ON).getValue()).getId() == 0) {
                            if (flag) {
                                errorMessage.append(", ");
                            }
                            errorMessage.append(LabelUtils.REBATE_BASED_ON);
                            flag = true;
                        }
                        if (binder.getField(FieldNameUtils.REBATE_STRUCTURE).getValue() == null || ((HelperDTO) binder.getField(FieldNameUtils.REBATE_STRUCTURE).getValue()).getId() == 0) {
                            if (flag) {
                                errorMessage.append(", ");
                            }
                            errorMessage.append(LabelUtils.REBATE_STRUCTURE);
                            flag = true;
                        }
                        if (binder.getField(FieldNameUtils.REBATE_RANGE_BASED_ON).getValue() == null || ((HelperDTO) binder.getField(FieldNameUtils.REBATE_RANGE_BASED_ON).getValue()).getId() == 0) {
                            if (flag) {
                                errorMessage.append(", ");
                            }
                            errorMessage.append(LabelUtils.REBATE_RANGE_BASED_ON);
                            flag = true;
                        }

                        if (flag) {
                            errorMessage.append("<br>");
                        }
                        if (searchResultbeans != null && searchResultbeans.size() == 0) {
                            errorMessage.append("Attach atleast One Tier");
                            flag = true;
                        } else if (searchResultbeans.size() < GeneralCommonUtils.ONE) {
                            errorMessage.append("Atleast one Tier should be added");
                            flag = true;
                        }
                        binder.getFields();
                        binder.commit();
                        if (flag) {
                            binder.getErrorDisplay().setError(errorMessage.toString());
                            return;
                        }
                        LOGGER.debug("Save Method to save Rebate plan by passing beanItem =" + searchResultbeans + " ," + "binder =" + binder);
                        /**
                         * After clicking delete button, function will be
                         * executed.
                         */

                        MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFIRMATION, commonMsg.getSaveMessage(binder.getField(ConstantsUtils.REBATE_PLAN_NAME).getValue().toString()), new MessageBoxListener() {
                            /**
                             * Called when a Button has been clicked .
                             *
                             */
                            public void buttonClicked(final ButtonId buttonId) {
                                if (buttonId.name().equals(ConstantsUtils.YES)) {
                                    saveRebatePlanYesMethodForAddorCopyMode();
                                }
                            }
                        }, ButtonId.YES, ButtonId.NO);

                        LOGGER.debug("Ending RebatePlan Save operation from Add");
                    } else if ("Edit".equals(mode)) {
                        LOGGER.debug("Entering Rebate Plan Update opertion from Edit");

                        binder.getErrorDisplay().clearError();

                        binder.getFields();
                        binder.commit();
                        boolean flag = false;

                        StringBuilder errorMsg = new StringBuilder("Information for the following Mandatory fields need to be provided:" + "<br>");
                        if (binder.getField(FieldNameUtils.REBATE_PLAN_ID).getValue().toString().trim().isEmpty()) {
                            if (flag) {
                                errorMsg.append(ConstantsUtils.COMMA);
                            }
                            errorMsg.append(LabelUtils.REBATE_PLAN_ID);
                            flag = true;
                        }
                        if (binder.getField(FieldNameUtils.REBATE_PLAN_NO).getValue().toString().trim().isEmpty()) {
                            if (flag) {
                                errorMsg.append(ConstantsUtils.COMMA);
                            }
                            errorMsg.append(LabelUtils.REBATE_PLAN_NO);
                            flag = true;
                        }
                         if (binder.getField(FieldNameUtils.REBATE_PLAN_TYPE).getValue() == null || ((HelperDTO) binder.getField(FieldNameUtils.REBATE_PLAN_TYPE).getValue()).getId() == 0) {
                            if (flag) {
                                errorMsg.append(ConstantsUtils.COMMA);
                            }
                            errorMsg.append(LabelUtils.REBATE_PLAN_TYPE);
                            flag = true;
                        }
                        if (binder.getField(FieldNameUtils.REBATE_PLAN_NAME).getValue().toString().trim().isEmpty()) {
                            if (flag) {
                                errorMsg.append(ConstantsUtils.COMMA);
                            }
                            errorMsg.append(LabelUtils.REBATE_PLAN_NAME);
                            flag = true;
                        }
                        if (binder.getField(FieldNameUtils.REBATE_PLAN_STATUS).getValue() == null || ((HelperDTO) binder.getField(FieldNameUtils.REBATE_PLAN_STATUS).getValue()).getId() == 0) {
                            if (flag) {
                                errorMsg.append(ConstantsUtils.COMMA);
                            }
                            errorMsg.append(LabelUtils.REBATE_PLAN_STATUS);
                            flag = true;
                        }
                        if (binder.getField(FieldNameUtils.REBATE_BASED_ON).getValue() == null || ((HelperDTO) binder.getField(FieldNameUtils.REBATE_BASED_ON).getValue()).getId() == 0) {
                            if (flag) {
                                errorMsg.append(ConstantsUtils.COMMA);
                            }
                            errorMsg.append(LabelUtils.REBATE_BASED_ON);
                            flag = true;
                        }
                        if (binder.getField(FieldNameUtils.REBATE_STRUCTURE).getValue() == null || ((HelperDTO) binder.getField(FieldNameUtils.REBATE_STRUCTURE).getValue()).getId() == 0) {
                            if (flag) {
                                errorMsg.append(ConstantsUtils.COMMA);
                            }
                            errorMsg.append(LabelUtils.REBATE_STRUCTURE);
                            flag = true;
                        }
                        if (binder.getField(FieldNameUtils.REBATE_RANGE_BASED_ON).getValue() == null || ((HelperDTO) binder.getField(FieldNameUtils.REBATE_RANGE_BASED_ON).getValue()).getId() == 0) {
                            if (flag) {
                                errorMsg.append(ConstantsUtils.COMMA);
                            }
                            errorMsg.append(LabelUtils.REBATE_RANGE_BASED_ON);
                            flag = true;
                        }

                        if (flag) {
                            errorMsg.append("<br>");
                        }
                        if (searchResultbeans != null && searchResultbeans.size() == 0) {
                            errorMsg.append("Attach atleast One Tier");
                            flag = true;
                        } else if (searchResultbeans.size() < GeneralCommonUtils.ONE) {
                            errorMsg.append("Atleast one Tier should be added");
                            flag = true;
                        }

                        if (flag) {
                            binder.getErrorDisplay().setError(errorMsg.toString());
                            return;
                        }

                        /**
                         * After clicking delete button, function will be
                         * executed.
                         */
                        MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFIRMATION, commonMsg.getSaveMessage(binder.getField(ConstantsUtils.REBATE_PLAN_NAME).getValue().toString()), new MessageBoxListener() {
                            /**
                             * Called when a Button has been clicked .
                             *
                             */
                            @SuppressWarnings("PMD")
                            public void buttonClicked(final ButtonId buttonId) {
                                if (buttonId.name().equals(ConstantsUtils.YES)) {
                                    saveRebatePlanYesMethodForEdit();
                                }
                            }
                        }, ButtonId.YES, ButtonId.NO);
                    }
                } catch (FieldGroup.CommitException cmtException) {
                    LOGGER.error(cmtException);
                } catch (Exception exception) {
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1007), new MessageBoxListener() {
                        /**
                         * The method is triggered when a button of the message
                         * box is pressed .
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
        });

    }

    /**
     * reset button.
     */
    private void configureResetButton() {

        if (mode.equals(ConstantsUtils.EDIT) || mode.equals(ConstantsUtils.COPY)) {
            resetButton.addClickListener(new ClickListener() {
                @SuppressWarnings("PMD")
                /**
                 * Constant SerialID
                 */
                private static final long serialVersionUID = 1L;

                /**
                 * Logic for back button.
                 *
                 * @param event
                 */
                @SuppressWarnings("PMD")
                public void buttonClick(final ClickEvent event) {
                    MessageBox.showPlain(Icon.QUESTION,ConstantsUtils.CONFIRMATION, commonMsg.getResetMessage(), new MessageBoxListener() {
                        /**
                         * Called when a Button has been clicked .
                         *
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            if (buttonId.name().equals(ConstantsUtils.YES)) {
                                try {
                                    LOGGER.debug("Entering inside  reset  method from ADD ");
                                    binder.getErrorDisplay().clearError();
                                    final String idValue = String.valueOf(sessionDTO.getSystemId());
                                    final int rebatePlanMasterId = Integer.valueOf(idValue);
                                    rebatePlanMasterDTO = rebatePlanLogic.getRebatePlanMasterById(Integer.valueOf(rebatePlanMasterId));
                                    if (TabNameUtil.REBATE_PLAN_INFO.equals(tabsheet.getSelectedTab().getCaption())) {
                                        TextField id = (TextField) binder.getField(FieldNameUtils.REBATE_PLAN_ID);
                                        id.setValue(rebatePlanMasterDTO.getRebatePlanId());
                                        TextField no = (TextField) binder.getField(FieldNameUtils.REBATE_PLAN_NO);
                                        no.setValue(rebatePlanMasterDTO.getRebatePlanNo());
                                        TextField name = (TextField) binder.getField(FieldNameUtils.REBATE_PLAN_NAME);
                                        name.setValue(rebatePlanMasterDTO.getRebatePlanName());
                                        ComboBox status = (ComboBox) binder.getField(FieldNameUtils.REBATE_PLAN_STATUS);
                                        status.setValue(rebatePlanMasterDTO.getRebatePlanStatus());
                                        ComboBox type = (ComboBox) binder.getField(FieldNameUtils.REBATE_PLAN_TYPE);
                                        type.setValue(rebatePlanMasterDTO.getRebatePlanType());
                                        OptionGroup formulaType = (OptionGroup) binder.getField(FieldNameUtils.FORMULA_TYPE);
                                        formulaType.setValue(rebatePlanMasterDTO.getFormulaType());
                                    }
                                    if (TabNameUtil.RP_CALCULATION.equals(tabsheet.getSelectedTab().getCaption())) {
                                        CustomTextField netSalesFormula = (CustomTextField) binder.getField("netSalesFormulaName");
                                        netSalesFormula.setValue(rebatePlanMasterDTO.getNetSalesFormulaName());
                                        CustomTextField netSalesRule = (CustomTextField) binder.getField("netSalesRule");
                                        netSalesRule.setValue(rebatePlanMasterDTO.getNetSalesRule());
                                        TextField tierFormula = (TextField) binder.getField("tierFormula");
                                        tierFormula.setValue(rebatePlanMasterDTO.getTierFormula());
                                        CustomTextField rpNo = (CustomTextField) binder.getField(FieldNameUtils.SECONDARY_REBATE_PLAN_NO);
                                        rpNo.setValue(StringUtils.EMPTY);
                                        PopupDateField selffrom = (PopupDateField) binder.getField(FieldNameUtils.SELF_GROWTH_FROM);
                                        selffrom.setValue(rebatePlanMasterDTO.getSelfGrowthFrom());
                                        PopupDateField selfto = (PopupDateField) binder.getField(FieldNameUtils.SELF_GROWTH_TO);
                                        selfto.setValue(rebatePlanMasterDTO.getSelfGrowthTo());
                                        PopupDateField shareFrom = (PopupDateField) binder.getField(FieldNameUtils.MARKET_SHARE_FROM);
                                        shareFrom.setValue(rebatePlanMasterDTO.getMarketShareFrom());
                                        PopupDateField shareTo = (PopupDateField) binder.getField(FieldNameUtils.MARKET_SHARE_TO);
                                        shareTo.setValue(rebatePlanMasterDTO.getMarketShareTo());
                                        TextField indicator = (TextField) binder.getField(FieldNameUtils.SELF_GROWTH_INDICATOR);
                                        indicator.setValue(rebatePlanMasterDTO.getSelfGrowthIndicator());
                                        TextField reference = (TextField) binder.getField(FieldNameUtils.SELF_GROWTH_REFERENCE);
                                        reference.setValue(rebatePlanMasterDTO.getSelfGrowthReference());
                                        TextField marketShare = (TextField) binder.getField(FieldNameUtils.MARKET_SHARE_INDICATOR);
                                        marketShare.setValue(rebatePlanMasterDTO.getMarketShareIndicator());
                                        TextField shareReference = (TextField) binder.getField(FieldNameUtils.MARKET_SHARE_REFERENCE);
                                        shareReference.setValue(rebatePlanMasterDTO.getMarketShareReference());
                                        ComboBox rebateBased = (ComboBox) binder.getField(FieldNameUtils.REBATE_BASED_ON);
                                        rebateBased.setValue(rebatePlanMasterDTO.getRebateBasedOn());
                                        ComboBox structure = (ComboBox) binder.getField(FieldNameUtils.REBATE_STRUCTURE);
                                        structure.setValue(rebatePlanMasterDTO.getRebateStructure());
                                        ComboBox rangeBased = (ComboBox) binder.getField(FieldNameUtils.REBATE_RANGE_BASED_ON);
                                        rangeBased.setValue(rebatePlanMasterDTO.getRebateRangeBasedOn());
                                        TextField tolerance = (TextField) binder.getField(FieldNameUtils.TIER_TOLERANCE);
                                        tolerance.setValue(rebatePlanMasterDTO.getTierTolerance());
                                        TextField from = (TextField) binder.getField(FieldNameUtils.TIER_FROM);
                                        TextField to = (TextField) binder.getField(FieldNameUtils.TIER_TO);
                                        to.setValue(rebatePlanMasterDTO.getTierTo());
                                        TextField value = (TextField) binder.getField(FieldNameUtils.TIER_VALUE);
                                        value.setValue(rebatePlanMasterDTO.getTierValue());
                                        ComboBox operator = (ComboBox) binder.getField(FieldNameUtils.TIER_OPERATOR);
                                        operator.setValue(rebatePlanMasterDTO.getTierValue());
                                        ComboBox valueDDLB = (ComboBox) binder.getField("valueDDLB");
                                        valueDDLB.setValue(rebatePlanMasterDTO.getTierValue());
                                        searchResultbeans.removeAllItems();
                                        searchResultbeans.addAll(rebatePlanLogic.getRebatePlanTiersfromId(rebatePlanMasterId, rebatePlanMasterDTO));
                                        rebatePlanCalculation.tempBeansList.removeAllItems();
                                        rebatePlanCalculation.tempBeansList.addAll(searchResultbeans.getItemIds());
                                        if (rebatePlanMasterDTO.getFormulaType().equals(StringUtils.EMPTY) || rebatePlanMasterDTO.getFormulaType() == null) {
                                            rebatePlanMasterDTO.setFormulaType(ConstantsUtils.SIMPLE);
                                        }
                                        if (ConstantsUtils.COPY.equals(sessionDTO.getMode())) {
                                            rebatePlanMasterDTO.setRebatePlanSystemId(ConstantsUtils.ZERO_INT);
                                            rebatePlanMasterDTO.setRebatePlanId(StringUtils.EMPTY);
                                            rebatePlanMasterDTO.setRebatePlanStatus(new HelperDTO(0, ConstantsUtils.SELECT_ONE));
                                            rebatePlanMasterDTO.setRebatePlanName(StringUtils.EMPTY);
                                            rebatePlanMasterDTO.setRebatePlanNo(StringUtils.EMPTY);
                                            rebatePlanMasterDTO.setFormulaType(ConstantsUtils.SIMPLE);
                                        }
                                        BigDecimal toToValue = searchResultbeans.lastItemId().getTierTo();
                                        if (toToValue != null && !toToValue.equals(BigDecimal.ZERO)) {
                                            from.setReadOnly(false);
                                            to.setReadOnly(false);
                                            toToValue.add(BigDecimal.ONE);
                                            from.setValue(toToValue.setScale(NumericConstants.TWO, RoundingMode.HALF_UP).toPlainString());
                                            from.setReadOnly(true);
                                        } else {
                                            from.setReadOnly(false);
                                            from.setValue(StringUtils.EMPTY);
                                            from.setReadOnly(true);
                                            to.setReadOnly(false);
                                        }

                                    }
                                    if (TabNameUtil.NOTES.equals(tabsheet.getSelectedTab().getCaption())) {
                                        notesTabForm.resetBtnLogic(rebatePlanMasterDTO.getInternalNotes());
                                    }
                                } catch (Exception e) {
                                    LOGGER.error(e);
                                }
                            }
                        }
                    }, ButtonId.YES, ButtonId.NO);

                }

            });
        } else {

            resetButton.setWidth(ConstantsUtils.BTN_WIDTH);
            resetButton.addClickListener(new ClickListener() {

                /**
                 * Constant SerialID
                 */
                private static final long serialVersionUID = 1L;

                /**
                 * Logic for back button.
                 *
                 * @param event
                 */
                public void buttonClick(final ClickEvent event) {
                    MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFIRMATION, commonMsg.getResetMessage(), new MessageBoxListener() {
                        /**
                         * Called when a Button has been clicked .
                         *
                         */

                        public void buttonClicked(final ButtonId buttonId) {
                            if (buttonId.name().equals(ConstantsUtils.YES)) {
                                try {
                                    LOGGER.debug("Entering inside  reset  method from ADD ");

                                    binder.getErrorDisplay().clearError();
                                    if (TabNameUtil.REBATE_PLAN_INFO.equals(tabsheet.getSelectedTab().getCaption())) {
                                        TextField id = (TextField) binder.getField(FieldNameUtils.REBATE_PLAN_ID);
                                        id.setValue(StringUtils.EMPTY);
                                        TextField no = (TextField) binder.getField(FieldNameUtils.REBATE_PLAN_NO);
                                        no.setValue(StringUtils.EMPTY);
                                        TextField name = (TextField) binder.getField(FieldNameUtils.REBATE_PLAN_NAME);
                                        name.setValue(StringUtils.EMPTY);
                                        ComboBox status = (ComboBox) binder.getField(FieldNameUtils.REBATE_PLAN_STATUS);
                                        status.setValue(null);
                                        ComboBox type = (ComboBox) binder.getField(FieldNameUtils.REBATE_PLAN_TYPE);
                                        type.setValue(null);
                                        OptionGroup formulaType = (OptionGroup) binder.getField(FieldNameUtils.FORMULA_TYPE);
                                        formulaType.setValue(ConstantsUtils.SIMPLE);
                                    }
                                    if (TabNameUtil.RP_CALCULATION.equals(tabsheet.getSelectedTab().getCaption())) {
                                        TextField rpId = (TextField) binder.getField(FieldNameUtils.SECONDARY_REBATE_PLAN_NO);
                                        rpId.setValue(StringUtils.EMPTY);
                                        CustomTextField netSalesFormula = (CustomTextField) binder.getField("netSalesFormulaName");
                                        netSalesFormula.setValue(StringUtils.EMPTY);
                                        CustomTextField netSalesRule = (CustomTextField) binder.getField("netSalesRule");
                                        netSalesRule.setValue(StringUtils.EMPTY);
                                        TextField tierFormula = (TextField) binder.getField("tierFormula");
                                        tierFormula.setValue(StringUtils.EMPTY);
                                        TextField indicator = (TextField) binder.getField(FieldNameUtils.SELF_GROWTH_INDICATOR);
                                        indicator.setValue(StringUtils.EMPTY);
                                        TextField reference = (TextField) binder.getField(FieldNameUtils.SELF_GROWTH_REFERENCE);
                                        reference.setValue(StringUtils.EMPTY);
                                        TextField marketShare = (TextField) binder.getField(FieldNameUtils.MARKET_SHARE_INDICATOR);
                                        marketShare.setValue(StringUtils.EMPTY);
                                        TextField shareReference = (TextField) binder.getField(FieldNameUtils.MARKET_SHARE_REFERENCE);
                                        shareReference.setValue(StringUtils.EMPTY);
                                        PopupDateField selffrom = (PopupDateField) binder.getField(FieldNameUtils.SELF_GROWTH_FROM);
                                        selffrom.setValue(null);
                                        PopupDateField selfto = (PopupDateField) binder.getField(FieldNameUtils.SELF_GROWTH_TO);
                                        selfto.setValue(null);
                                        PopupDateField shareFrom = (PopupDateField) binder.getField(FieldNameUtils.MARKET_SHARE_FROM);
                                        shareFrom.setValue(null);
                                        PopupDateField shareTo = (PopupDateField) binder.getField(FieldNameUtils.MARKET_SHARE_TO);
                                        shareTo.setValue(null);
                                        ComboBox rebateBased = (ComboBox) binder.getField(FieldNameUtils.REBATE_BASED_ON);
                                        rebateBased.setValue(null);
                                        ComboBox structure = (ComboBox) binder.getField(FieldNameUtils.REBATE_STRUCTURE);
                                        structure.setValue(null);
                                        ComboBox rangeBased = (ComboBox) binder.getField(FieldNameUtils.REBATE_RANGE_BASED_ON);

                                        TextField tierLevel = (TextField) binder.getField(FieldNameUtils.TIER_LEVEL);
                                        tierLevel.setReadOnly(false);
                                        tierLevel.setValue("1");
                                        tierLevel.setReadOnly(true);
                                        rangeBased.setValue(null);
                                        TextField tolerance = (TextField) binder.getField(FieldNameUtils.TIER_TOLERANCE);
                                        tolerance.setValue(StringUtils.EMPTY);
                                        TextField from = (TextField) binder.getField(FieldNameUtils.TIER_FROM);
                                        from.setReadOnly(false);
                                        from.setValue(StringUtils.EMPTY);
                                        TextField to = (TextField) binder.getField(FieldNameUtils.TIER_TO);
                                        to.setValue(StringUtils.EMPTY);
                                        ComboBox operator = (ComboBox) binder.getField(FieldNameUtils.TIER_OPERATOR);
                                        operator.setValue(null);
                                        searchResultbeans.removeAllItems();
                                        rebatePlanCalculation.tempBeansList.removeAllItems();
                                    }
                                    if (TabNameUtil.NOTES.equals(tabsheet.getSelectedTab().getCaption())) {
                                        notesTabForm.resetAddMode();
                                    }
                                } catch (Exception e) {
                                    LOGGER.error(e);
                                }
                            }
                        }
                    }, ButtonId.YES, ButtonId.NO);

                }

            });

        }
    }

    /**
     * Removal of the Buttons for the view module
     */
    public void removeButtonForView() {
        buttonLayout.removeComponent(saveButton);
        buttonLayout.removeComponent(resetButton);
        buttonLayout.removeComponent(deleteButton);
    }

    /**
     * Update Button of the Edit tab in Rebate Plan
     */
    private void configureDeleteButton() {
        deleteButton.setErrorHandler(new ErrorHandler() {
            /**
             * Delete button UI error handler
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error(String.valueOf(event));
            }
        });
        deleteButton.addClickListener(new ClickListener() {
            /**
             * Adds the button click listener.
             */
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("Entering Rebate Plan Delete opertion from Edit");
                binder.getFields();

                MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFIRMATION, commonMsg.getDeleteMessage(binder.getField(ConstantsUtils.REBATE_PLAN_NAME).getValue().toString()), new MessageBoxListener() {
                    /**
                     * Adds the button click listener.
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        try {
                            if (buttonId.name().equals(ConstantsUtils.YES)) {
                                final int idValue = Integer.valueOf(binder.getField("rebatePlanSystemId").getValue().toString().replaceAll(ConstantsUtils.COMMA, StringUtils.EMPTY));
                                LOGGER.debug("Entering deleteRebatePlanById P1:" + idValue);
                                final RebatePlanMaster master = rebatePlanLogic.deleteRebatePlanById(idValue, rebatePlanMasterDTO);

                                final Notification notif = new Notification(commonMsg.getDeletedSuccessfulMessage(master.getRebatePlanId(), master.getRebatePlanName()), Notification.Type.HUMANIZED_MESSAGE);

                                notif.setPosition(Position.MIDDLE_CENTER);
                                notif.setStyleName(ConstantsUtils.MY_STYLE);
                                notif.show(Page.getCurrent());
                                LOGGER.debug("After deleting  navigateTo AbstractSearchView");
                                AbstractSearchView.flag = true;
                                getUI().getNavigator().navigateTo(AbstractSearchView.NAME);
                            }
                        } catch (SystemException sysException) {
                            final String errorMsg = ErrorCodeUtil.getErrorMessage(sysException);
                            LOGGER.error(errorMsg);
                            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg, new MessageBoxListener() {
                                /**
                                 * The method is triggered when a button of the
                                 * message box is pressed .
                                 *
                                 * @param buttonId The buttonId of the pressed
                                 * button.
                                 */
                                @SuppressWarnings("PMD")
                                public void buttonClicked(final ButtonId buttonId) {
                                    // Do Nothing    
                                }
                            }, ButtonId.OK);
                            msg.getButton(ButtonId.OK).focus();

                        } catch (PortalException portException) {
                            LOGGER.error(portException);
                            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1012), new MessageBoxListener() {
                                /**
                                 * The method is triggered when a button of the
                                 * message box is pressed .
                                 *
                                 * @param buttonId The buttonId of the pressed
                                 * button.
                                 */
                                @SuppressWarnings("PMD")
                                public void buttonClicked(final ButtonId buttonId) {
                                    // Do Nothing    
                                }
                            }, ButtonId.OK);
                            msg.getButton(ButtonId.OK).focus();
                        } catch (Exception exception) {
                            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1012), new MessageBoxListener() {
                                /**
                                 * The method is triggered when a button of the
                                 * message box is pressed .
                                 *
                                 * @param buttonId The buttonId of the pressed
                                 * button.
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
                }, ButtonId.YES, ButtonId.NO);

                LOGGER.debug("Ending Rebate Plan Delete opertion from Edit");
            }
        });
    }

    /**
     * Adds or Remove button based on the mode of operation.
     *
     * @throws Exception
     */
    public void configureOnMode() {
        if (mode.equals(ConstantsUtils.VIEW)) {
            notesTabForm.removeAndDisablingComponents();
            removeButtonForView();
            rebatePlanCalculation.configureOnView();

        } else if (mode.equals(ConstantsUtils.EDIT)) {
            saveButton.setCaption("Update");
            rebatePlanCalculation.configureOnEdit();
        } else {
            buttonLayout.removeComponent(deleteButton);
        }
        if (mode.equals(ConstantsUtils.COPY)) {
            rebatePlanCalculation.configureOnEdit();
        }
    }

    public void saveRebatePlanYesMethodForAddorCopyMode() {
        try {
            final String msg = rebatePlanLogic.saveRebatePlan(binder, searchResultbeans, notesTabForm.getUploadedData(), notesTabForm.getAddedNotes(),
                    notesTabForm.removeDetailsList(), sessionDTO);
            LOGGER.debug("After Saving rebate plans ,Return message = " + msg);
            if ("success".equals(msg)) {
                CommonUIUtils.successNotification(commonMsg.getSavedSuccessfulMessage(binder.getField(ConstantsUtils.REBATE_PLAN_ID).getValue().toString(), binder.getField(ConstantsUtils.REBATE_PLAN_NAME).getValue().toString()));
                LOGGER.debug("NavigateTo RebatePlanSearchView page");
                sessionDTO.setMode(ConstantsUtils.EDIT);
                getUI().getNavigator().navigateTo(RebatePlanView.NAME);
            } else if (msg.equals(ConstantsUtils.DUPLICATE)) {
                binder.getErrorDisplay().setError("Rebate Plan ID already exists, Please enter different Rebate Plan ID");
            } else if (ConstantsUtils.DUPLICATENO.equals(msg)) {
                binder.getErrorDisplay().setError("Rebate Plan No already exists, Please enter different Rebate Plan No");
            } else if (ConstantsUtils.DUPLICATENAME.equals(msg)) {
                binder.getErrorDisplay().setError("Rebate Plan Name already exists, Please enter different Rebate Plan Name");
            }
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
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1007), new MessageBoxListener() {
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
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1007), new MessageBoxListener() {
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

    public void saveRebatePlanYesMethodForEdit() {
        try {
            final String msg = rebatePlanLogic.saveRebatePlan(binder, searchResultbeans, notesTabForm.getUploadedData(), notesTabForm.getAddedNotes(),
                    notesTabForm.removeDetailsList(), sessionDTO);
            if (msg.equals(ConstantsUtils.SUCCESS)) {
                final Notification notif = new Notification(commonMsg.getSavedSuccessfulMessage(binder.getField(ConstantsUtils.REBATE_PLAN_ID).getValue().toString(), binder.getField(ConstantsUtils.REBATE_PLAN_NAME).getValue().toString()), Notification.Type.HUMANIZED_MESSAGE);

                notif.setPosition(Position.MIDDLE_CENTER);
                notif.setStyleName(ConstantsUtils.MY_STYLE);
                notif.show(Page.getCurrent());
                getUI().getNavigator().navigateTo(RebatePlanView.NAME);
            } else if (msg.equals(ConstantsUtils.DUPLICATE)) {
                binder.getErrorDisplay().setError("Rebate Plan ID already exists, Please enter different Rebate Plan ID");
            } else if (ConstantsUtils.DUPLICATENO.equals(msg)) {
                binder.getErrorDisplay().setError("Rebate Plan No already exists, Please enter different Rebate Plan No");
            } else if (ConstantsUtils.DUPLICATENAME.equals(msg)) {
                binder.getErrorDisplay().setError("Rebate Plan Name already exists, Please enter different Rebate Plan Name");
            }
            LOGGER.debug("Ending Rebate Plan Update opertion from Edit");
        } catch (Exception exception) {
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1008), new MessageBoxListener() {
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
