package com.stpl.app.global.cfp.ui.form;

import com.stpl.app.global.abstractsearch.view.AbstractSearchView;
import com.stpl.app.global.cfp.dto.CFPCompanyDTO;
import com.stpl.app.global.cfp.logic.CFPSearchLogic;
import com.stpl.app.global.cfp.ui.view.CFPAddView;
import com.stpl.app.global.cfp.util.FieldNameUtils;
import com.stpl.app.global.cfp.util.LabelUtils;
import com.stpl.app.global.common.ui.InformationLayout;
import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.dao.impl.CFPSearchLogicDAOImpl;
import com.stpl.app.model.CfpContract;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.model.CfpModel;
import com.stpl.app.service.ImtdCfpDetailsLocalServiceUtil;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.NotesTabForm;
import com.stpl.app.ui.StplCustomComponent;
import com.stpl.app.ui.StplR2Exception;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonLazyUtilDTO;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.app.util.FunctionNameUtil;
import com.stpl.app.util.TabNameUtil;
import com.stpl.app.util.UISecurityUtil;
import com.stpl.app.util.ValidationUtils;
import com.stpl.domain.global.companyfamilyplan.CompanyFamilyplanDAO;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 * The Class CFPAddForm.
 */
public final class CFPAddForm extends StplCustomComponent {

    /**
     * The tabsheet.
     */
    @UiField("tabSheet")
    TabSheet tabSheet;
    /**
     * The vLayout.
     */
    @UiField("vLayout")
    VerticalLayout vLayout;
    /**
     * Error label
     */
    @UiField("errorLB")
    private ErrorLabel errorMsg;
    /**
     * Form results
     */
    CFPAddForm results;
    /**
     * The logger.
     */

    private static final Logger LOGGER = Logger.getLogger(CFPAddForm.class);
    /**
     * The Constant NAME.
     */
    public static final String NAME = ConstantsUtils.VIEW;
    /**
     * The cfp logic.
     */
    private final CFPSearchLogic cfpLogic;
    CompanyFamilyplanDAO companyFamilyplanDAO = new CFPSearchLogicDAOImpl();
    /**
     * The space.
     */
    private final Label space = new Label(ConstantsUtils.SPACE, ContentMode.HTML);
    /**
     * Save Button
     */
    @UiField("saveBtn")
    private Button saveBtn;
    @UiField("backBtn")
    private Button backBtn;
    @UiField("resetBtn")
    private Button resetBtn;
    @UiField("deleteBtn")
    private Button deleteBtn;
    /**
     * The company details save bean is used to save the company details before
     * performing any activity it will get the object form lazy container while
     * user trying to edit or changing any value in the table and it is useful
     * for saving in temp table
     */

 
    CFPCompanies cfpCompanies;
    CFPCompanyAddition companyAddition;
    
    CommonUtil commonutil=CommonUtil.getInstance();
    /**
     * The map.
     */
    private final Map<String, String> map = new HashMap<String, String>();
    /**
     * The cfp master.
     */
    private final CFPCompanyDTO cfpMaster;
    /**
     * The binder.
     */
    private ErrorfulFieldGroup binder;
    /**
     * the used id
     */
    private String userId;
    /**
     * the session id
     */
    private String sessionId;
    /**
     * the temp table records created date
     */
    private String tempCreatedDate;
    /**
     * the DTO used in lazy container
     */
    CommonLazyUtilDTO lazyUtilDTO = new CommonLazyUtilDTO();

    public String tabName;
    NotesTabForm cfpAdditionalInfo;
    SessionDTO sessionDTO;
    

    /**
     * The Constructor.
     *
     * @param cfpMaster
     * @param binder
     * @throws SystemException
     * @throws PortalException
     */
    public CFPAddForm(final CFPCompanyDTO cfpMaster, final ErrorfulFieldGroup binder, final SessionDTO sessionDTO) throws SystemException, PortalException {
        super();
        this.cfpMaster = cfpMaster;
        this.binder = binder;
        this.sessionDTO = sessionDTO;
        cfpLogic = new CFPSearchLogic(this.sessionDTO);
        try{
        init();
        }
        catch(Exception e){
            LOGGER.error(e);
        }
    }

    /**
     * Initial method when the Constructor get calls.
     *
     * @throws StplR2Exception the stpl r2 exception
     */
    public void init() throws SystemException, PortalException {
        LOGGER.debug("Entering CFPAddForm init");
        space.setHeight(ConstantsUtils.HEIGHT);
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/declarativeui/company_family_plan/tabsheetform.xml"), this));
        vLayout.addComponent(new InformationLayout(ConstantsUtils.CFP,cfpMaster.getCompanyFamilyPlanId(),cfpMaster.getCompanyFamilyPlanNo(),cfpMaster.getCompanyFamilyPlanName()));
        addToContent();
        configureFields();
        LOGGER.debug("Ending init");
    }

    /**
     * Adds all contents.
     */
    public void addToContent() throws SystemException, PortalException {
        LOGGER.debug("Entering addToContent");
        binder = getBinder();
        addTabSheet();
         final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final Map<String, AppPermission> functionCfpHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.COMPANY_FAMILY_PLAN+ ConstantsUtils.COMMA + "Common");
        if (functionCfpHM.get(FunctionNameUtil.SAVE) != null && ((AppPermission) functionCfpHM.get(FunctionNameUtil.SAVE)).isFunctionFlag()) {
            addButton();
            saveBtn.setVisible(true);
        }else{
            saveBtn.setVisible(false);
        }
        if (functionCfpHM.get(FunctionNameUtil.RESET) != null && ((AppPermission) functionCfpHM.get(FunctionNameUtil.RESET)).isFunctionFlag()) {
            resetButton();
             resetBtn.setVisible(true);
        }else{
            resetBtn.setVisible(false);
        }
        if (functionCfpHM.get(FunctionNameUtil.DELETE) != null && ((AppPermission) functionCfpHM.get(FunctionNameUtil.DELETE)).isFunctionFlag()) {
            deleteButton();
             deleteBtn.setVisible(true);
        }else{
            deleteBtn.setVisible(false);
        }
        backButton();
        backBtn.setEnabled(true);
        if (sessionDTO.getMode().equals(ConstantsUtils.VIEW_BTN)) {
            saveBtn.setVisible(false);
            resetBtn.setVisible(false);
            deleteBtn.setVisible(false);
        } else if (sessionDTO.getMode().equals(ConstantsUtils.EDIT)) {
            saveBtn.setEnabled(true);
            saveBtn.setCaption("UPDATE");
            resetBtn.setEnabled(true);
            deleteBtn.setEnabled(true);
            
           
        } else {
            deleteBtn.setVisible(false);
            saveBtn.setEnabled(true);
            resetBtn.setEnabled(true);
        }
        LOGGER.debug("Ending addToContent");
    }

    private ErrorfulFieldGroup getBinder() {
        binder.setItemDataSource(new BeanItem<CFPCompanyDTO>(cfpMaster));
        binder.setBuffered(true);
        binder.setErrorDisplay(errorMsg);
        errorMsg.setId("ErrorMessage");
        return binder;
    }


    /**
     * Adds the tab sheet.
     *
     * @return the tab sheet
     * @throws StplR2Exception the stpl r2 exception
     */
    public void addTabSheet() {
        LOGGER.debug("Entering addTabSheet");
        try {
            tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
            tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
            tabSheet.setId("responsive-tab");
            tabSheet.setReadOnly(true);
            userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            sessionId = sessionDTO.getUiSessionId();
            tempCreatedDate = sessionDTO.getSessionDate();


                CFPInformation cfpInfo = new CFPInformation(binder,sessionDTO);
                cfpInfo.setCaption(TabNameUtil.CFP_INFO);

                tabSheet.addTab(cfpInfo, TabNameUtil.CFP_INFO);
                cfpInfo.companyFamilyPlanId.focus();
                cfpCompanies = new CFPCompanies(binder, cfpMaster, sessionDTO);
                cfpCompanies.setCaption(TabNameUtil.CFP_COMPANIES);
               companyAddition = new CFPCompanyAddition(binder, cfpMaster, sessionDTO,cfpCompanies);
                companyAddition.setCaption(TabNameUtil.CFP_COMPANY_ADDITION);
                tabSheet.addTab(companyAddition, TabNameUtil.CFP_COMPANY_ADDITION);
                tabSheet.addTab(cfpCompanies, TabNameUtil.CFP_COMPANIES);

                cfpAdditionalInfo = new NotesTabForm(this.binder, "Company Family Plan", "CFP_MODEL", "companyFamilyPlanSystemId",ConstantsUtils.VIEW_BTN.equals(sessionDTO.getMode())?"view":sessionDTO.getMode());
                if (sessionDTO.getMode().equals(ConstantsUtils.VIEW_BTN)) {
                    cfpAdditionalInfo.removeAndDisablingComponents();
                }
                if (sessionDTO.getMode().equals(ConstantsUtils.EDIT)) {
                    cfpAdditionalInfo.readOnlyNotesHistory(true);
                }
                tabSheet.addTab(cfpAdditionalInfo, TabNameUtil.NOTES);
                cfpAdditionalInfo.refreshTable();
                cfpAdditionalInfo.focusNewNote();
            tabSheet.setErrorHandler(new ErrorHandler() {
                /**
                 * Invoked when an error occurs.
                 */
                @SuppressWarnings("PMD")
                public void error(final com.vaadin.server.ErrorEvent event) {
                    LOGGER.debug(ConstantsUtils.ERROR_IN_SEARCH);
                    }
            });
            tabSheet.addSelectedTabChangeListener(new SelectedTabChangeListener() {
                /**
                 * Selected tab in tab sheet has has been changed.
                 */
                public void selectedTabChange(final SelectedTabChangeEvent event) {

                    tabName= event.getTabSheet().getSelectedTab().getCaption();
                    if (TabNameUtil.CFP_INFO.equals(tabName)) {
                        binder.getErrorDisplay().clearError();
                        binder.getField("companyFamilyPlanId").focus();
                    } else if (TabNameUtil.CFP_COMPANY_ADDITION.equals(tabName)) {
                        binder.getErrorDisplay().clearError();
                    
                    } else if (TabNameUtil.CFP_COMPANIES.equals(tabName)) {
                        binder.getErrorDisplay().clearError();

                        lazyUtilDTO.setCfpNo(binder.getField(ConstantsUtils.CFP_NO).getValue() != null ? binder.getField(ConstantsUtils.CFP_NO).getValue().toString() : StringUtils.EMPTY);
                        lazyUtilDTO.setCfpName(binder.getField(ConstantsUtils.CFP_NAME).getValue() != null ? binder.getField(ConstantsUtils.CFP_NAME).getValue().toString() : StringUtils.EMPTY);
                        cfpCompanies.configureInTabChange(lazyUtilDTO);
                    } else {
                        binder.getErrorDisplay().clearError();
                        cfpAdditionalInfo.focusNewNote();
                        cfpAdditionalInfo.callJavaScriptForButton();
                        cfpAdditionalInfo.focusUploaderField();
                        if (sessionDTO.getMode().equals(ConstantsUtils.VIEW_BTN)) {
                            cfpAdditionalInfo.removeAndDisablingComponents();
                        }
                        if (sessionDTO.getMode().equals(ConstantsUtils.EDIT)) {
                            cfpAdditionalInfo.readOnlyNotesHistory(true);
                        }
                    }
                }
            });
            LOGGER.debug("Ending addTabSheet");
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
        } catch (SystemException se) {
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
        } catch (Exception ex) {
            LOGGER.error(ex);
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
    }

    /**
     * Configure fields.
     */
    public void configureFields() {
        LOGGER.debug("Entering inside ConfigureFields");

        lazyUtilDTO.setUserId(userId);
        lazyUtilDTO.setSessionId(sessionId);
        lazyUtilDTO.setDate(tempCreatedDate);
        lazyUtilDTO.setSearchFlag(true);
        map.put("CFP Start Date", ConstantsUtils.CFP_START_DATE);
        map.put("CFP End Date", ConstantsUtils.CFP_END_DATE);
        map.put("CFP Status", ConstantsUtils.CFP_STATUS);
        LOGGER.debug("Ending  ConfigureFields");

    }

    /**
     * logic for Add button.
     *
     * @throws StplR2Exception the stpl r2 exception
     */
    private void addButton() {
        saveBtn.addClickListener(new ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                try {
                    LOGGER.debug("Entering SAVE method");
                    binder.getFields();
                    binder.getField(ConstantsUtils.PARENT_CFP_ID).setReadOnly(false);
                    binder.commit();
                    boolean flag = false;
                    String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
                    String sessionId = sessionDTO.getUiSessionId();
                    String tempCreatedDate = sessionDTO.getSessionDate();
                    List<Object> companyIdList = cfpLogic.validateNull(userId, sessionId, tempCreatedDate, "tempCount");
                    StringBuilder errorMessage = new StringBuilder("Information for the following Mandatory fields need to be provided:" + "<br>");
                    if (StringUtils.EMPTY.equals(binder.getField(FieldNameUtils.COMPANYFAMILYPLANID).getValue())
                            || StringUtils.EMPTY.equals(binder.getField(FieldNameUtils.COMPANYFAMILYPLANID).getValue().toString().trim())) {
                        if (flag) {
                            errorMessage.append(ConstantsUtils.COMMA);
                        }
                        errorMessage.append(LabelUtils.COMPANYFAMILYPLANID);
                        flag = true;
                    }
                    if (StringUtils.EMPTY.equals(binder.getField(FieldNameUtils.COMPANYFAMILYPLANNO).getValue())
                            || StringUtils.EMPTY.equals(binder.getField(FieldNameUtils.COMPANYFAMILYPLANNO).getValue().toString().trim())) {
                        if (flag) {
                            errorMessage.append(ConstantsUtils.COMMA);
                        }
                        errorMessage.append(LabelUtils.COMPANYFAMILYPLANNO);
                        flag = true;
                    }
                    if (StringUtils.EMPTY.equals(binder.getField(FieldNameUtils.COMPANYFAMILYPLANNAME).getValue())
                            || StringUtils.EMPTY.equals(binder.getField(FieldNameUtils.COMPANYFAMILYPLANNAME).getValue().toString().trim())) {
                        if (flag) {
                            errorMessage.append(ConstantsUtils.COMMA);
                        }
                        errorMessage.append(LabelUtils.COMPANYFAMILYPLANNAME);
                        flag = true;
                    }

                    if (binder.getField(FieldNameUtils.COMPANYFAMILYPLANSTATUS).getValue() == null || ConstantsUtils.SELECT_ONE.equals(binder.getField(FieldNameUtils.COMPANYFAMILYPLANSTATUS).getValue()) || ((HelperDTO) binder.getField(FieldNameUtils.COMPANYFAMILYPLANSTATUS).getValue()).getId() == 0) {
                        if (flag) {
                            errorMessage.append(ConstantsUtils.COMMA);
                        }
                        errorMessage.append(LabelUtils.COMPANYFAMILYPLANSTATUS);
                        flag = true;
                    }
                    if (binder.getField(FieldNameUtils.COMPANYFAMILYPLANSTARTDATE).getValue() == null) {
                        if (flag) {
                            errorMessage.append(ConstantsUtils.COMMA);
                        }
                        errorMessage.append(LabelUtils.COMPANYFAMILYPLANSTARTDATE);
                        flag = true;
                    }
                    if (binder.getField(FieldNameUtils.SALESINCLUSION).getValue() == null || ConstantsUtils.SELECT_ONE.equals(binder.getField(FieldNameUtils.SALESINCLUSION).getValue()) || ((HelperDTO) binder.getField(FieldNameUtils.SALESINCLUSION).getValue()).getId() == 0) {
                        if (flag) {
                            errorMessage.append(ConstantsUtils.COMMA);
                        }
                        errorMessage.append(LabelUtils.SALESINCLUSION);
                        flag = true;
                    }
                    if (flag) {
                        errorMessage.append("<br>");
                    }
                    if (((Integer) companyIdList.get(0)) == ConstantsUtils.ZERO_INT) {
                        errorMessage.append("Add at least one company in Company Addition tab for CFP");
                        flag = true;
                    }
                    if (flag) {
                        binder.getErrorDisplay().setError(errorMessage.toString());
                        return;
                    }

                    cfpCompanies.saveRecordsInTempTable();

                    companyIdList = cfpLogic.validateNull(userId, sessionId, tempCreatedDate, "startDateNull");
                    if (companyIdList.size() > 0) {
                        Object companyNo = companyIdList.get(0);

                        if (companyIdList.size() > 1) {
                            binder.getErrorDisplay().setError("Start Date required for selected companies");
                            return;
                        } else {
                            binder.getErrorDisplay().setError("Start Date required for Company No " + companyNo + ConstantsUtils.IN_COMPANIES_TAB);
                            return;
                        }
                    }
                    companyIdList = cfpLogic.validateNull(userId, sessionId, tempCreatedDate, "StatusNull");
                    if (companyIdList.size() > 0) {
                        Object companyNo = companyIdList.get(0);

                        if (companyIdList.size() > 1) {
                            binder.getErrorDisplay().setError("Status required for selected companies");
                            return;
                        } else {
                            binder.getErrorDisplay().setError("Status required for Company No " + companyNo + ConstantsUtils.IN_COMPANIES_TAB);
                            return;
                        }
                    }
                    companyIdList = cfpLogic.validateNull(userId, sessionId, tempCreatedDate, "startDateGreaterThanEndDate");
                    if (companyIdList.size() > 0) {
                        Object companyNo = companyIdList.get(0);

                        if (companyIdList.size() > 1) {
                            int companyCount = companyIdList.size() - 1;
                            binder.getErrorDisplay().setError("CFP Start Date is less than CFP End date for Company No " + companyNo + " and " + companyCount + " companies in Companies tab");
                            return;
                        } else {
                            binder.getErrorDisplay().setError("CFP Start Date is less than CFP End date for Company No " + companyNo + ConstantsUtils.IN_COMPANIES_TAB);
                            return;
                        }
                    }
                    companyIdList = cfpLogic.validateNull(userId, sessionId, tempCreatedDate, "companyDuplicationCheck");
                    if (companyIdList.size() > 0) {
                        Object companyNo = companyIdList.get(0);

                        if (companyIdList.size() > 1) {
                            int companyCount = companyIdList.size() - 1;
                            binder.getErrorDisplay().setError("Please enter different Start Date. The selected Trading Partner and Start Date Combination already exists for Company No "
                                    + companyNo + " and " + companyCount + " more company/companies in Companies tab");
                            return;
                        } else {
                            binder.getErrorDisplay().setError("Please enter different Start Date.Since selected Trading Partner and Start Date Combination already exists for Company No " + companyNo + ConstantsUtils.IN_COMPANIES_TAB);
                            return;
                        }
                    }
                    // this is check atleast company is selected in third tab
                    companyIdList = cfpLogic.validateNull(userId, sessionId, tempCreatedDate, "checkRecord");
                    if (((Integer) companyIdList.get(0)) == ConstantsUtils.ZERO_INT) {

                        binder.getErrorDisplay().setError("Check Aleast one Company to save in Companies tab");
                        return;
                    }

                    MessageBox.showPlain(Icon.QUESTION, commonutil.getHeaderMessage(), commonutil.getSaveMessage((String)  binder.getField(FieldNameUtils.COMPANYFAMILYPLANNAME).getValue()), new MessageBoxListener() {

                        /**
                         * Called when a Button has been clicked .
                         *
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            if (buttonId.name().equals(ConstantsUtils.YES)) {
                                String msg = StringUtils.EMPTY;
                                try {
                                    msg = cfpLogic.saveCompanyMaster(binder, cfpAdditionalInfo.getUploadedData(), cfpAdditionalInfo.getAddedNotes(), cfpAdditionalInfo.removeDetailsList());
                                    if (sessionDTO.getMode().equals(ConstantsUtils.EDIT)) {
                                    sessionDTO.setIsSave("Y");
                                    }
                                } catch (Exception ex) {
                                    LOGGER.error(ex);
                                    final MessageBox msgBox = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1010), new MessageBoxListener() {
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
                                    msgBox.getButton(ButtonId.OK).focus();
                                }
                                if (ConstantsUtils.SUCCESS.equals(msg)) {
                                    final Notification notif = new Notification(binder.getField(FieldNameUtils.COMPANYFAMILYPLANID).getValue() + ", " + binder.getField(FieldNameUtils.COMPANYFAMILYPLANNAME).getValue() + " has been successfully Saved", Notification.Type.HUMANIZED_MESSAGE);
                                    notif.setPosition(Position.MIDDLE_CENTER);
                                    notif.setStyleName(ConstantsUtils.MY_STYLE);
                                    notif.show(Page.getCurrent());
                                    sessionDTO.setMode(ConstantsUtils.EDIT);
                                    getUI().getNavigator().navigateTo(CFPAddView.NAME);
                                } else if (ConstantsUtils.DUPLICATE.equals(msg)) {
                                    binder.getErrorDisplay().setError(
                                            "Company Family Plan ID already exists.");
                                } else if (ConstantsUtils.DUPLICATENO.equals(msg)) {
                                    binder.getErrorDisplay().setError(
                                            "Company Family Plan No already exists.");
                                }
                            }
                        }
                    }, ButtonId.YES, ButtonId.NO);

                } catch (FieldGroup.CommitException commitEx) {
                    LOGGER.error(commitEx);
                } catch (Exception ex) {
                    
                    LOGGER.error(ex);
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
                LOGGER.debug("Ending SAVE  method ");

            }
        });
    }

    /**
     * logic for back button.
     *
     * @throws StplR2Exception the stpl r2 exception
     */
    private void backButton() {

        backBtn.addClickListener(new ClickListener() {

            /**
             * Called when a Button has been clicked.
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                if (sessionDTO.getMode().equals(ConstantsUtils.VIEW_BTN)) {
                    try {
                        CommonLazyUtilDTO lazyUtilDTO = new CommonLazyUtilDTO();
                        lazyUtilDTO.setUserId(String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID)));
                        lazyUtilDTO.setSessionId(sessionDTO.getUiSessionId());
                        lazyUtilDTO.setDate(sessionDTO.getSessionDate());
                        cfpLogic.deleteTempDetails(lazyUtilDTO);
                        AbstractSearchView.flag = false;
                        getUI().getNavigator().navigateTo(AbstractSearchView.NAME);
                    } catch (PortalException pe) {
                        LOGGER.error(pe);
                        final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1010), new MessageBoxListener() {
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
                    } catch (SystemException se) {
                        final String errorMsg = ErrorCodeUtil.getErrorMessage(se);
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
                    }
                } else {
                    MessageBox.showPlain(Icon.QUESTION, commonutil.getHeaderMessage(), commonutil.getBackMessage(), new MessageBoxListener() {
                        @Override
                        public void buttonClicked(ButtonId buttonId) {
                            if (buttonId.name().equals(ConstantsUtils.YES)) {
                                LOGGER.debug("Entering CFPAddActionButtonLayout  BACK  method ");
                                try {
                                    CommonLazyUtilDTO lazyUtilDTO = new CommonLazyUtilDTO();
                                    lazyUtilDTO.setUserId(String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID)));
                                    lazyUtilDTO.setSessionId(sessionDTO.getUiSessionId());
                                    lazyUtilDTO.setDate(sessionDTO.getSessionDate());
                                    cfpLogic.deleteTempDetails(lazyUtilDTO);
                                    AbstractSearchView.flag = false;
                                    getUI().getNavigator().navigateTo(AbstractSearchView.NAME);
                                } catch (PortalException pe) {
                                    LOGGER.error(pe);
                                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1010), new MessageBoxListener() {
                                        /**
                                         * The method is triggered when a button
                                         * of the message box is pressed .
                                         *
                                         * @param buttonId The buttonId of the
                                         * pressed button.
                                         */
                                        @SuppressWarnings("PMD")
                                        public void buttonClicked(final ButtonId buttonId) {
                                            // Do Nothing
                                        }
                                    }, ButtonId.OK);
                                    msg.getButton(ButtonId.OK).focus();
                                } catch (SystemException se) {
                                    final String errorMsg = ErrorCodeUtil.getErrorMessage(se);
                                    LOGGER.error(errorMsg);
                                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg, new MessageBoxListener() {
                                        /**
                                         * The method is triggered when a button
                                         * of the message box is pressed .
                                         *
                                         * @param buttonId The buttonId of the
                                         * pressed button.
                                         */
                                        @SuppressWarnings("PMD")
                                        public void buttonClicked(final ButtonId buttonId) {
                                            // Do Nothing
                                        }
                                    }, ButtonId.OK);
                                    msg.getButton(ButtonId.OK).focus();
                                }
                            }
                        }

                    }, ButtonId.YES, ButtonId.NO);
                }

                LOGGER.debug("Ending CFPAddActionButtonLayout BACK  method ");
            }
        });

        backBtn.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs.
             */
            public void error(final com.vaadin.server.ErrorEvent event) {
                //empty method
            }
        });
    }
    
            /**
     * reset button.
     */
    private void resetButton() {
        try {
            resetBtn.addClickListener(new ClickListener() {
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
                    MessageBox.showPlain(Icon.QUESTION, commonutil.getHeaderMessage(), commonutil.getResetMessage(), new MessageBoxListener() {

                        /**
                         * Called when a Button has been clicked .
                         *
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            if (buttonId.name().equals(ConstantsUtils.YES)) {
                                try {
                                    LOGGER.debug("Entering inside  reset  method from ADD ");
                                    if (TabNameUtil.CFP_INFO.equals(tabSheet.getSelectedTab().getCaption())) {
                                         final HelperDTO defaultValue=new HelperDTO( 0,  ConstantsUtils.SELECT_ONE);
                                        TextField id = (TextField) binder.getField(FieldNameUtils.COMPANYFAMILYPLANID);
                                        id.setValue(StringUtils.EMPTY);
                                        TextField no = (TextField) binder.getField(FieldNameUtils.COMPANYFAMILYPLANNO);
                                        no.setValue(StringUtils.EMPTY);
                                        TextField name = (TextField) binder.getField(FieldNameUtils.COMPANYFAMILYPLANNAME);
                                        name.setValue(StringUtils.EMPTY);
                                        ComboBox status = (ComboBox) binder.getField(FieldNameUtils.COMPANYFAMILYPLANSTATUS);
                                        status.setValue(defaultValue);
                                        PopupDateField startDate = (PopupDateField) binder.getField(FieldNameUtils.COMPANYFAMILYPLANSTARTDATE);
                                        startDate.setValue(null);
                                        PopupDateField endDate = (PopupDateField) binder.getField(FieldNameUtils.COMPANYFAMILYPLANENDDATE);
                                        endDate.setValue(null);
                                        ComboBox type = (ComboBox) binder.getField(FieldNameUtils.COMPANYFAMILYPLANTYPE);
                                         type.setValue(defaultValue);
                                        ComboBox tradeClass = (ComboBox) binder.getField(FieldNameUtils.COMPANYFAMILYPLANTRADECLASS);
                                        tradeClass.select(defaultValue);
                                        ComboBox category = (ComboBox) binder.getField(FieldNameUtils.COMPANYFAMILYPLANCATEGORY);
                                        category.select(defaultValue);
                                        ComboBox designation = (ComboBox) binder.getField(FieldNameUtils.COMPANYFAMILYPLANDESIGNATION);
                                        designation.select(defaultValue);
                                        CustomTextField parentCompany = (CustomTextField) binder.getField(ConstantsUtils.PARENT_CFP_ID);
                                        parentCompany.setReadOnly(false);
                                        parentCompany.setValue(StringUtils.EMPTY);
                                        parentCompany.setReadOnly(true);
                                        TextField parentName = (TextField) binder.getField(FieldNameUtils.PARENTCOMPANYFAMILYPLANNAME);
                                        parentName.setReadOnly(false);
                                        parentName.setValue(StringUtils.EMPTY);
                                        parentName.setReadOnly(true);
                                        ComboBox salesInclusion = (ComboBox) binder.getField(FieldNameUtils.SALESINCLUSION);
                                        salesInclusion.select(defaultValue);
                                       
                                        if (sessionDTO.getMode().equals(ConstantsUtils.EDIT)) {
                                            id.setValue(cfpMaster.getCompanyFamilyPlanId());
                                            no.setValue(cfpMaster.getCompanyFamilyPlanNo());
                                            name.setValue(cfpMaster.getCompanyFamilyPlanName());
                                            status.setValue(cfpMaster.getCompanyFamilyPlanStatus());
                                            startDate.setValue(cfpMaster.getCompanyFamilyPlanStartDate());
                                            endDate.setValue(cfpMaster.getCompanyFamilyPlanEndDate());
                                            type.setValue(cfpMaster.getCompanyFamilyPlanType());
                                            tradeClass.setValue(cfpMaster.getCompanyFamilyPlanTradeClass());
                                            category.setValue(cfpMaster.getCompanyFamilyPlanCategory());
                                            designation.setValue(cfpMaster.getCompanyFamilyPlanDesignation());
                                            parentCompany.setReadOnly(false);
                                            parentCompany.setValue(String.valueOf(cfpMaster.getParentCompanyFamilyPlanId()));
                                            parentCompany.setReadOnly(true);
                                            parentName.setReadOnly(false);
                                            parentName.setValue(cfpMaster.getParentCompanyFamilyPlanName());
                                            parentName.setReadOnly(true);
                                            salesInclusion.setValue(cfpMaster.getSalesInclusion());
                                        }
                                    } else if (TabNameUtil.CFP_COMPANY_ADDITION.equals(tabSheet.getSelectedTab().getCaption())) {
                                        TextField value = (TextField) binder.getField("searchValue");
                                        value.setValue(StringUtils.EMPTY);
                                        ComboBox field = (ComboBox) binder.getField("searchFields");
                                        field.setValue(ConstantsUtils.SELECT_ONE);
                                        companyAddition.resetAvailableTable();
                                        if (sessionDTO.getMode().equals(ConstantsUtils.EDIT)) {
                                            ImtdCfpDetailsLocalServiceUtil.deleteAll(userId, sessionId, null, null, null, null, ConstantsUtils.DELETE1, null);
                                            cfpLogic.addToTempCfpDetailsEdit(cfpMaster.getCompanyFamilyPlanSystemId());
                                            companyAddition.selectedTableLoad();
                                        } else {
                                            companyAddition.removeAllCompanyButtonClick(null);
                                        }

                                    }
                                    if (TabNameUtil.CFP_COMPANIES.equals(tabSheet.getSelectedTab().getCaption())) {
                                        OptionGroup massCheck = (OptionGroup) binder.getField("massCheck");
                                        massCheck.setValue(ConstantsUtils.DISABLE);
                                        cfpCompanies.getMassField().setValue(ConstantsUtils.SELECT_ONE);
                                        if (sessionDTO.getMode().equals(ConstantsUtils.EDIT)) {
                                            cfpCompanies.saveRecordsInTempTable();
                                            ImtdCfpDetailsLocalServiceUtil.deleteAll(userId, sessionId, null, null, null, null, ConstantsUtils.DELETE1, null);
                                            cfpLogic.addToTempCfpDetailsEdit(cfpMaster.getCompanyFamilyPlanSystemId());
                                            cfpCompanies.loadDetailsTable();
                                        } else {
                                            ImtdCfpDetailsLocalServiceUtil.deleteAll(userId, sessionId, null, null, null, null, ConstantsUtils.DELETE1, null);
                                            cfpCompanies.loadDetailsTable();
                                        }

                                    } else {
                                        cfpAdditionalInfo.resetBtnLogic(StringUtils.EMPTY);
                                    }

                                } catch (Exception e) {
                                    LOGGER.error(e);

                                }
                            }
                        }
                    }, ButtonId.YES, ButtonId.NO);

                }
            });
        } catch (Exception e) {
            LOGGER.error(e);
            
        }
    }

    private void deleteButton() {

        deleteBtn.setWidth(ConstantsUtils.BTN_WIDTH);
        deleteBtn.addClickListener(new ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {

                binder.getFields();
                LOGGER.debug("Entering CFPEditActionButtonLayout DELETE  method ");
                final int cfpId = Integer.valueOf(binder.getField(ConstantsUtils.CFP_SYSTEM_ID).getValue().toString().replace(ConstantsUtils.COMMA, StringUtils.EMPTY));
                try {
                    final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil
                            .forClass(CfpModel.class);
                    cfpDynamicQuery.add(RestrictionsFactoryUtil.eq("parentCfpId",
                            cfpId));
                    cfpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS, "D"));
                    final List<CfpModel> cfpDetailsList = companyFamilyplanDAO.getCompanyFamilyplanMasterList(cfpDynamicQuery);
                    if (!cfpDetailsList.isEmpty()) {
                        final MessageBox msg = MessageBox.showPlain(Icon.WARN, ValidationUtils.CANNOT_DELETE, "Company Family Plan cannot be deleted, associated as parent to another Company Family Plan", new MessageBoxListener() {
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
                    if (cfpDetailsList.isEmpty()) {                        
                       
                        List<CfpContract> cfpContractList = new ArrayList<>();
                            final DynamicQuery contractDynamicQuery = DynamicQueryFactoryUtil.forClass(CfpContract.class);
                            contractDynamicQuery.add(RestrictionsFactoryUtil.eq("cfpModelSid", cfpId));
                            contractDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS, "D"));
                            cfpContractList = companyFamilyplanDAO.getCfpContractList(contractDynamicQuery);
                         
                            if (cfpContractList != null && !cfpContractList.isEmpty() && cfpContractList.size() > 0) {
                                final MessageBox msg = MessageBox.showPlain(Icon.WARN, ValidationUtils.CANNOT_DELETE, "Company Family Plan cannot be deleted as it is associated with Contract", new MessageBoxListener() {

                                    @SuppressWarnings("PMD")
                                    public void buttonClicked(final ButtonId buttonId) {
                                        // Do Nothing
                                    }
                                }, ButtonId.OK);
                                msg.getButton(ButtonId.OK).focus();
                  
                            }else{
                        
                        
                        MessageBox.showPlain(Icon.QUESTION, commonutil.getHeaderMessage(), commonutil.getDeleteMessage(binder.getField(FieldNameUtils.COMPANYFAMILYPLANNAME).toString()), new MessageBoxListener() {
                            /**
                             * Called when a Button has been clicked.
                             */
                            @SuppressWarnings("PMD")
                            public void buttonClicked(final ButtonId buttonId) {

                                if (ConstantsUtils.YES.equals(buttonId.name())) {
                                    final int cfpSystemId = Integer.valueOf(binder.getField(ConstantsUtils.CFP_SYSTEM_ID).getValue().toString().replace(ConstantsUtils.COMMA, StringUtils.EMPTY));
                                    cfpLogic.deleteNotesTabAttachment(cfpSystemId);
                                    if (cfpSystemId == 0 && cfpSystemId == Integer.valueOf(ConstantsUtils.ZERO)) {

                                        final Notification notif = new Notification("Selected CFP is already deleted", Notification.Type.HUMANIZED_MESSAGE);
                                        notif.setPosition(Position.MIDDLE_CENTER);
                                        notif.setStyleName(ConstantsUtils.MY_STYLE);
                                        notif.show(Page.getCurrent());
                                         AbstractSearchView.flag=true;
                                        getUI().getNavigator().navigateTo(AbstractSearchView.NAME);
                                    } else {
                                        try {
                                            CfpModel master;
                                            master = cfpLogic.deleteCFPMasterById(cfpSystemId);

                                            final Notification notif = new Notification(master.getCfpId() + ", " + master.getCfpName() + " has been successfully deleted", Notification.Type.HUMANIZED_MESSAGE);
                                            notif.setPosition(Position.MIDDLE_CENTER);
                                            notif.setStyleName(ConstantsUtils.MY_STYLE);
                                            notif.show(Page.getCurrent());
                                             AbstractSearchView.flag=true;
                                            getUI().getNavigator().navigateTo(AbstractSearchView.NAME);

                                        } catch (SystemException ex) {
                                            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                                            LOGGER.error(errorMsg);
                                            final MessageBox msg = MessageBox.showPlain(Icon.WARN, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg, new MessageBoxListener() {
              
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
                                            final MessageBox msg = MessageBox.showPlain(Icon.WARN, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1012), new MessageBoxListener() {
              
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
                                        } catch (Exception ex) {
                                            LOGGER.error(ex);
                                            final MessageBox msg = MessageBox.showPlain(Icon.WARN, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1012), new MessageBoxListener() {
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
                                }
                            }
                        }, ButtonId.YES, ButtonId.NO);
                    }
                    }
                    LOGGER.debug("Ending CFPEditActionButtonLayout DELETE  method ");
                } catch (SystemException ex) {
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                    LOGGER.error(errorMsg);
                    final MessageBox msg = MessageBox.showPlain(Icon.WARN, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg, new MessageBoxListener() {
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
                    final MessageBox msg = MessageBox.showPlain(Icon.WARN, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1012), new MessageBoxListener() {
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
        });

    }
}
