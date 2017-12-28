
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.compliancededuction.ui.form;

import com.stpl.app.global.abstractsearch.view.AbstractSearchView;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.compliancededuction.dto.CDRDto;
import com.stpl.app.global.compliancededuction.logic.CDRLogic;
import com.stpl.app.global.compliancededuction.ui.view.CDRView;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.NotesTabForm;
import com.stpl.app.ui.StplCustomComponent;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.UISecurityUtil;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.themes.ValoTheme;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 *
 * @author sooriya.lakshmanan
 */
public class CDRForm extends StplCustomComponent {

    /**
     * The Constant NAME.
     */
    public static final String NAME = "Add";
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(CDRForm.class);
    /**
     * The TabSheet
     */
    @UiField("tabSheet")
    TabSheet mainTab;
    /**
     * The HorizontalLayout
     */
    @UiField("buttonLayout")
    HorizontalLayout buttonLayout;
    /**
     * The Save Button
     */
    @UiField("saveBtn")
    Button saveBtn;
    /**
     * The Back Button
     */
    @UiField("backBtn")
    Button backBtn;
    /**
     * The Delete Button
     */
    @UiField("deleteBtn")
    Button deleteButton;
    /**
     * The Reset Button
     */
    @UiField("resetBtn")
    Button resetBtn;
    RuleInformation ruleInfo = new RuleInformation();
    CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();
    CommonUIUtils commonUIUtils = new CommonUIUtils();
    NotesTabForm notesTabForm;
    ErrorfulFieldGroup binder;
    SessionDTO sessionDTO;
    int selectedTabIndex = 0;

    public CDRForm(final ErrorfulFieldGroup binder, final SessionDTO sessionDTO) {
        try {
            this.binder = binder;
            this.sessionDTO = sessionDTO;
            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute("userId"));
            final Map<String, AppPermission> fieldCompanyHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.COMPLIANCE_DEDUCTION_RULES + ConstantsUtils.COMMA + ConstantsUtils.RULE_INFORMATION, false);
            final Map<String, AppPermission> functionCompanyHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.COMPLIANCE_DEDUCTION_RULES + ConstantsUtils.COMMA + ConstantsUtils.RULE_INFORMATION);

            setCompositionRoot(Clara.create(getClass().getResourceAsStream("/clara/common/tabsheetform.xml"), this));
            configurePermission(fieldCompanyHM, functionCompanyHM);
            componentConfiguration();
            configureFields();
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    private void configureFields() throws SystemException, PortalException {
        mainTab.addStyleName(ValoTheme.TABSHEET_FRAMED);
        mainTab.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        mainTab.markAsDirty();
        mainTab.markAsDirtyRecursive();
        mainTab.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
            public void selectedTabChange(TabSheet.SelectedTabChangeEvent event) {
                Component component = event.getTabSheet().getSelectedTab();
                selectedTabIndex = event.getTabSheet().getTabPosition(event.getTabSheet().getTab(component));
            }
        });
        mainTab.addTab(ruleInfo.getContent(binder, sessionDTO), "Rule Information", null, 0);
        notesTabForm = new NotesTabForm(binder, "Compliance Deduction Rules", "CDR_MODEL", sessionDTO.getSystemId() == 0 ? "0" : String.valueOf(sessionDTO.getSystemId()), sessionDTO.getMode());

        mainTab.addTab(notesTabForm, "Notes", null, 1);
        mainTab.setSizeFull();
        LOGGER.debug("sessionDTO ...CRDForm.>>>>" + sessionDTO.getUserId());
        LOGGER.debug("sessionDTO ...CRDForm.>>>>" + sessionDTO.getMode());
        if (sessionDTO.getMode().equals(ConstantsUtils.EDIT) || sessionDTO.getMode().equals(ConstantsUtils.COPY)) {

            notesTabForm.readOnlyNotesHistory(false);
            notesTabForm.setNotesHistoryValue(ruleInfo.getNoteshistory());
            notesTabForm.readOnlyNotesHistory(true);
            notesTabForm.refreshTable();
            saveBtn.setCaption(ConstantsUtils.UPDATE);
        }
        if (sessionDTO.getMode().equals(ConstantsUtils.COPY)) {
            saveBtn.setCaption(ConstantsUtils.SAVE);
        }
        if (sessionDTO.getMode().equals(ConstantsUtils.VIEW)) {
            notesTabForm.readOnlyNotesHistory(false);
            notesTabForm.setNotesHistoryValue(ruleInfo.getNoteshistory());
            notesTabForm.readOnlyNotesHistory(true);
            notesTabForm.refreshTable();
            enableDisableFields(false);
        } else {
            enableDisableFields(true);
        }
    }

    private void componentConfiguration() {
        deleteButton.setVisible(false);
    }

    public void backBtn() {
        backBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (ConstantsUtils.VIEW.equals(sessionDTO.getMode())) {
                    getUI().getNavigator().navigateTo(AbstractSearchView.NAME);
                } else {
                    MessageBox.showPlain(Icon.QUESTION, "Back Confirmation", "Are you sure you want to navigate back to the Deduction Rules landing screen? \n"
                            + "You will lose all unsaved data if you proceed " + " ?", new MessageBoxListener() {
                        /**
                         * Called when a Button has been clicked .
                         *
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            if (buttonId.name().equals("YES")) {
                                getUI().getNavigator().navigateTo(AbstractSearchView.NAME);
                            }
                        }
                    }, ButtonId.YES, ButtonId.NO);
                }
            }

        });
    }

    public void resetBtn() {
        resetBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                LOGGER.debug(" Reset Method starts");

                MessageBox.showPlain(Icon.QUESTION, "Reset Confirmation", "Are you sure you want to reset all values?", new MessageBoxListener() {
                    /**
                     * Called when a Button has been clicked .
                     *
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals("YES")) {
                            try {
                                if (ConstantsUtils.ADD.equals(sessionDTO.getMode())) {
                                    if (selectedTabIndex == 0) {
                                        ruleInfo.resetMethod();
                                    } else {
                                        notesTabForm.resetAddMode();
                                    }
                                } else {
                                    ruleInfo.preLoadData();
                                    notesTabForm.resetBtnLogic(ruleInfo.getNoteshistory());
                                }
                            } catch (Exception ex) {
                                java.util.logging.Logger.getLogger(CDRForm.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);

                LOGGER.debug(" Reset Method ends");

            }
        });
    }

    public void saveBtn() {
        saveBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                final List ruleInfoList = ruleInfo.getRuleInformations();
                CDRDto binderDto = (CDRDto) ruleInfoList.get(0);
                CDRLogic logic = new CDRLogic();
                if (mandatoryCheck(binderDto)) {
                    int operatorint = 1;
                    if (ruleInfo.getRuleInformations().get(1) != null && !((List) ruleInfo.getRuleInformations().get(1)).isEmpty()) {
                        List<CDRDto> cdrList = (List<CDRDto>) ruleInfo.getRuleInformations().get(1);
                        CDRDto cdrDto = cdrList.get(cdrList.size() - 1);
                        operatorint = cdrDto.getLogicalOperatorDdlb() == null ? 0 : cdrDto.getLogicalOperatorDdlb().getId();
                    }
                    if (operatorint != 0) {
                        AbstractNotificationUtils.getInfoNotification("Cannot Save Rule", "Last rule cannot have an Operator. \n");
                    } else if (logic.isSameRuleNoAndName(binderDto) && !ConstantsUtils.EDIT.equals(sessionDTO.getMode())) {
                        AbstractNotificationUtils.getInfoNotification("Cannot Save Rule", "Same Rule No or Rule Name already exists. \n");
                    } else {
                        MessageBox.showPlain(Icon.QUESTION, "Save Confirmation", "Save record " + String.valueOf(binder.getField("ruleNo").getValue()) + " ?", new MessageBoxListener() {
                            /**
                             * Called when a Button has been clicked .
                             *
                             */
                            @SuppressWarnings("PMD")
                            public void buttonClicked(final ButtonId buttonId) {
                                if (buttonId.name().equals("YES")) {
                                    try {
                                        CDRLogic logic = new CDRLogic();
                                        if (ConstantsUtils.EDIT.equals(sessionDTO.getMode()) && sessionDTO.getSystemId() != 0) {
                                            logic.deleteRules(ruleInfo.getDeletedRuleInformations(), "CDR_DETAILS_SID");
                                        } else {
                                            saveBtn.setCaption(ConstantsUtils.UPDATE);
                                        }
                                        logic.updateRuleModelEditLogic((CDRDto) ruleInfoList.get(0), sessionDTO, notesTabForm.getAddedNotes());
                                        LOGGER.debug("id after save" + sessionDTO.getSystemId());

                                        logic.updateRulesDetailsEditLogic(ruleInfoList, sessionDTO);
                                        logic.saveOrUpdateNotesTab(notesTabForm.getUploadedData(), notesTabForm.removeDetailsList(), sessionDTO.getSystemId());
                                        sessionDTO.setMode(ConstantsUtils.EDIT);
                                        ruleInfo.loadSavedRulesDetails(CDRLogic.getSavedRuleDetails(sessionDTO.getSystemId()));
                                        ruleInfo.setDeletedRuleInformations(new ArrayList());
                                        notesTabForm.setMasterTableSid(String.valueOf(sessionDTO.getSystemId()));
                                        getUI().getNavigator().navigateTo(CDRView.NAME);
                                        final Notification notif = new Notification(String.valueOf(binder.getField("ruleNo").getValue()) + " has been successfully Saved", Notification.Type.HUMANIZED_MESSAGE);
                                        notif.setPosition(Position.MIDDLE_CENTER);
                                        notif.setStyleName(ConstantsUtils.MY_STYLE);
                                        notif.show(Page.getCurrent());

                                    } catch (Exception ex) {
                                        LOGGER.error(ex);
                                    }
                                }
                            }
                        }, ButtonId.YES, ButtonId.NO);
                    }
                }
            }
        });
    }

    /**
     * Mandatory fields Check
     *
     * @param binderDto
     * @return boolean
     */
    boolean mandatoryCheck(final CDRDto binderDto) {
        boolean flag = false;
        if (binderDto.getRuleType_DTO() != null && !binderDto.getRuleNo().isEmpty() && !binderDto.getRuleName().isEmpty()) {

            if (ruleInfo.getRuleInformations().get(1) == null || ((List) ruleInfo.getRuleInformations().get(1)).isEmpty()) {
                AbstractNotificationUtils.getErrorNotification("Missing Required Fields", "Add atleast one Rule Detail. \n");
                flag = false;
            } else {
                flag = true;
            }
        } else {
            AbstractNotificationUtils.getErrorNotification("Missing Required Fields", "You cannot save this record until all required fields have been populated. \n");
        }

        return flag;
    }

    /**
     * Set Enable and Disable based on the mode
     *
     * @param isEnable
     */
    private void enableDisableFields(final boolean isEnable) {
        saveBtn.setEnabled(isEnable);
        resetBtn.setEnabled(isEnable);
    }

    private void configurePermission(final Map<String, AppPermission> fieldCompanyHM, final Map<String, AppPermission> functionHM) {

        LOGGER.debug("Entering configurePermission");
        try {
            String mode = sessionDTO.getMode();

            List<Object> resultList = commonUIUtils.getFieldsForSecurity(UISecurityUtil.COMPLIANCE_DEDUCTION_RULES, ConstantsUtils.RULE_INFORMATION);
            commonSecurityLogic.removeComponentOnPermission(resultList, buttonLayout, fieldCompanyHM, mode);
            if (functionHM.get("saveBtn") != null && ((AppPermission) functionHM.get("saveBtn")).isFunctionFlag()) {
                saveBtn();
            } else {
                saveBtn.setVisible(false);
            }
            if (functionHM.get("resetBtn") != null && ((AppPermission) functionHM.get("resetBtn")).isFunctionFlag()) {
                resetBtn();
            } else {
                resetBtn.setVisible(false);
            }
            if (functionHM.get("backBtn") != null && ((AppPermission) functionHM.get("backBtn")).isFunctionFlag()) {
                backBtn();
            } else {
                backBtn.setVisible(false);
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("Ending configurePermission");
    }
}
