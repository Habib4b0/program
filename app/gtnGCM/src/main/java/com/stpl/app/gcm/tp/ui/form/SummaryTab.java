/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.ui.form;

import com.stpl.app.gcm.common.AppDataUtils;
import com.stpl.app.gcm.common.CommonLogic;
import static com.stpl.app.gcm.discount.ui.form.ExistingDiscountTab.LOGGER;
import com.stpl.app.gcm.security.StplSecurity;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.tp.dto.ContractResultDTO;
import com.stpl.app.gcm.tp.logic.CommmonLogic;
import com.stpl.app.gcm.tp.logic.LoadTabLogic;
import com.stpl.app.gcm.tp.ui.layout.CustomTPDetailsLayout;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.CommonUtils;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.*;
import com.stpl.app.gcm.util.Message;
import com.stpl.app.gcm.util.MessageUtil;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Lokeshwari
 */
public class SummaryTab extends VerticalLayout {

    @UiField("summaryTableLayout")
    private VerticalLayout summaryTableLayout;

    @UiField("functionalButton")
    private Button functionalButton;

    @UiField("previous")
    public Button previous;
    @UiField("close")
    public Button close;
    private Panel selCriteriaPanel = new Panel("Selection Criteria");
    private GridLayout selCriteriaGrid = new GridLayout(NumericConstants.TEN, 1);
    private HorizontalLayout selCriteriaLayout = new HorizontalLayout();
    private Label companyNo = new Label("Company No:");
    private TextField companyNumber = new TextField();
    private Label companyName = new Label("Company Name:");
    private TextField companyNameField = new TextField();
    private Label companyType = new Label("Company Type:");
    private TextField companyTypeField = new TextField();
    private Label companyCategory = new Label("Company Category:");
    private TextField companyCategoryField = new TextField();
    private Label tradeClass = new Label("Trade Class:");
    private TextField tradeClassField = new TextField();
    private final StplSecurity stplSecurity = new StplSecurity();
    
    public static final String FUNCTIONAL_BUTTON = "functionalButton";
    public static final String CLOSE_BTN = "close";
    public static final String GCM_CUSTOMER_MANAGEMENT = "GCM-Customer Management";
    public static final String PREVIOUS_BTN = "previous";
    public static final String ONE_THIRTY_PX = "130px";
    public static final String USER_ID = "userId";
    public static final String AND_PROJECTION_NAME = " \n and Projection Name - ";
    public static final String NEW_PROJECTION_CREATED_WITH_FORECASTING = "'\n new Projection created with forecasting type -";
    
    private final BeanItemContainer<ContractResultDTO> currentTPDetailsContainer = new BeanItemContainer<>(ContractResultDTO.class);
    private final BeanItemContainer<ContractResultDTO> transferTPDetailsContainer = new BeanItemContainer<>(ContractResultDTO.class);

    private TransferTPForm ttpForm;
    private RemoveTPForm rtpform;
    private AddTPForm addTpForm;
    private UpdateTPForm updateTPForm;
    private CustomTPDetailsLayout layout;
    private SessionDTO session;
    private boolean isLoad = false;
    private boolean isRebateLoad = false;
    private String idString = ButtonId.OK.name();
    private int projectionId = 0;

    /**
     * The history bean.
     */
    public SummaryTab(SessionDTO session, RemoveTPForm form) {
        this.rtpform = form;
        initSummaryTab(session);
    }

    public SummaryTab(SessionDTO session, TransferTPForm form) {
        this.ttpForm = form;
        initSummaryTab(session);
        configureSecurityPermissionsForTransfer();
    }

    public SummaryTab(SessionDTO session, AddTPForm form) {
        this.addTpForm = form;
        initSummaryTab(session);
    }

    public SummaryTab(SessionDTO session, UpdateTPForm form) {
        this.updateTPForm = form;
        initSummaryTab(session);
        configureSecurityPermissionsUpdate();
    }

    private void initSummaryTab(SessionDTO session) {
        this.session = session;
        addComponent(Clara.create(getClass().getResourceAsStream("/TradingPartner/summaryTab.xml"), this));
        configureFields();
        configureSecurityPermissions();
    }

    public void configureFields() {

        if (ADD_TRADING_PARTNER.getConstant().equalsIgnoreCase(session.getModuleName())) {
            layout = new ContractProcessingDashboard(currentTPDetailsContainer, session);
            summaryTableLayout.addComponent(addSelectionCriteriaLayout());
            companyNumber.setValue(session.getCompanyNo());
            companyNameField.setValue(session.getCompanyName());
            companyTypeField.setValue(session.getCompanyType());
            companyCategoryField.setValue(session.getCompanyCategory());
            tradeClassField.setValue(session.getTradeClass());
            summaryTableLayout.addComponent(layout);

            functionalButton.setCaption("PROCESS");

        } else if (TRANSFER_TRADING_PARTNER.getConstant().equals(session.getModuleName()) || PROJECTION_DETAILS_TRANSFER.getConstant().equals(session.getModuleName())
                || TRADING_PARTNER_REMOVE.getConstant().equals(session.getModuleName()) || TRADING_PARTNER_UPDATE.getConstant().equals(session.getModuleName())) {

            final TabSheet tabSheet = new TabSheet();
            tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
            tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
            final SalesTab salesTab = new SalesTab(session, false);
            final RebateTab rebateTab = new RebateTab(session, false);
            final SalesRebateTab salesRebateTab = new SalesRebateTab(session, false);

            tabSheet.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {

                @Override
                public void selectedTabChange(SelectedTabChangeEvent event) {
                    final Tab tab = (Tab) event.getTabSheet().getTab(event.getTabSheet().getSelectedTab());
                    int tabPosition = event.getTabSheet().getTabPosition(tab);
                    String tabName = tabSheet.getTab(tabPosition).getCaption();
                    if (projectionId == 0) {
                        projectionId = CommonLogic.getProjectionIdForSubmittedContract(session.getSessionId(), false);
                    }
                    LoadTabLogic logic = new LoadTabLogic();
                    if (isLoad && (SALES_TRANSFER.getConstant().equals(tabName) || SALES_PROJECTION.getConstant().equals(tabName))) {
                        if (projectionId == 0) {
                            projectionId = CommonLogic.getProjectionIdForSubmittedContract(session.getSessionId(), false);
                        } else {
                            int tempId = CommonLogic.getProjectionIdForSubmittedContract(session.getSessionId(), false);
                            if (tempId != 0 && tempId != projectionId) {
                                projectionId = tempId;
                            }
                        }
                        session.setProjectionId(projectionId);
                        session.setContMasteSid(CommonLogic.getSelectedContractSid(session.getSessionId(), false));
                        logic.getProjectionList(session);
                        salesTab.loadResultTable(session, projectionId);
                        isLoad = false;
                    }
                    if (isRebateLoad && (REBATES_PROJECTION.getConstant().equals(tabName))) {
                        session.setProjectionId(projectionId);
                        session.setContMasteSid(CommonLogic.getSelectedContractSid(session.getSessionId(), false));
                        logic.getProjectionList(session);
                        salesTab.loadResultTable(session, projectionId);
                        rebateTab.loadResultTable(session, projectionId);
                        isRebateLoad = false;
                    }
                    if (SALES_AND_REBATES_PROJECTION.getConstant().equals(tabName)) {
                        session.setProjectionId(projectionId);
                        session.setContMasteSid(CommonLogic.getSelectedContractSid(session.getSessionId(), false));
                        logic.getProjectionList(session);
                        salesTab.loadResultTable(session, projectionId);
                        salesRebateTab.loadTable(projectionId);
                    }
                }
            });

            if (TRADING_PARTNER_REMOVE.getConstant().equals(session.getModuleName()) || TRADING_PARTNER_UPDATE.getConstant().equals(session.getModuleName())) {

                layout = new TradingpartnerDetails(currentTPDetailsContainer, session);
                tabSheet.addTab(layout, TRADING_PARTNER_DETAILS.getConstant());
                tabSheet.addTab(salesTab, SALES_PROJECTION.getConstant(), null, 1);
                tabSheet.addTab(rebateTab, REBATES_PROJECTION.getConstant(), null, NumericConstants.TWO);
                tabSheet.addTab(salesRebateTab, SALES_AND_REBATES_PROJECTION.getConstant(), null, NumericConstants.THREE);
                if (TRADING_PARTNER_UPDATE.getConstant().equals(session.getModuleName())) {
                    functionalButton.setCaption("UPDATE");

                }

            } else if (PROJECTION_DETAILS_TRANSFER.getConstant().equals(session.getModuleName()) || TRANSFER_TRADING_PARTNER.getConstant().equals(session.getModuleName())) {
                layout = new TransferTpTradingpartnerDetails(session, currentTPDetailsContainer, transferTPDetailsContainer, ttpForm);
                tabSheet.addTab(layout, TRADING_PARTNER_DETAILS.getConstant());
                tabSheet.addTab(salesTab, SALES_TRANSFER.getConstant());
                tabSheet.addTab(rebateTab, REBATES_PROJECTION.getConstant(), null, NumericConstants.TWO);
                tabSheet.addTab(salesRebateTab, SALES_AND_REBATES_PROJECTION.getConstant(), null, NumericConstants.THREE);
                functionalButton.setCaption("TRANSFER");
            }

            summaryTableLayout.addComponent(tabSheet);
        }

    }

    @UiHandler("close")
    public void closeBtnLogic(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            @Override
            public void noMethod() {
                // do nothing
            }

            @Override
            public void yesMethod() {
                try {
                    if (session.getModuleName().equalsIgnoreCase(TRADING_PARTNER_REMOVE.getConstant())) {
                        rtpform.close();
                    } else if (session.getModuleName().equalsIgnoreCase(ADD_TRADING_PARTNER.getConstant())) {
                        addTpForm.close();
                    } else if (session.getModuleName().equals(TRADING_PARTNER_UPDATE.getConstant())) {
                        updateTPForm.close();
                    } else {
                        ttpForm.close();
                    }

                    List input = new ArrayList();
                    input.add(session.getSessionId());
                    AppDataUtils.dataUpdate(input, "Close button delete");

                } catch (Exception ex) {
                    LOGGER.error("",ex);
                }
            }
        }.getConfirmationMessage(MessageUtil.getErrorCode(Message.CLOSE_CONFIRM), MessageUtil.getErrorCode(Message.CLOSE_CONFIRM_MSG));

    }

    @UiHandler("functionalButton")
    public void functionalButtonLogic(Button.ClickEvent event) {
        ButtonId idOne = ButtonId.OK;
        ButtonId idTwo;
        idString = idOne.name();
        String confirmContent;
        String comfirmHeader;

        if (session.getModuleName().equalsIgnoreCase(TRADING_PARTNER_REMOVE.getConstant()) || session.getModuleName().equalsIgnoreCase(ADD_TRADING_PARTNER.getConstant())
                || TRADING_PARTNER_UPDATE.getConstant().equals(session.getModuleName())) {

            if (currentTPDetailsContainer != null && currentTPDetailsContainer.size() > 0) {
                if (session.getModuleName().equalsIgnoreCase(TRADING_PARTNER_REMOVE.getConstant()) && !session.isRemoveCheck()) {
                    AbstractNotificationUtils.getErrorNotification("No record selected", "Please select a value to remove. Then try again with OK button");
                    return;
                }

            } else {
                AbstractNotificationUtils.getErrorNotification(MessageUtil.getErrorCode(Message.NO_RECORD_SUBMIT), MessageUtil.getErrorCode(Message.NO_RECORD_SUBMIT_MSG));
                return;

            }

        } else {
            if (currentTPDetailsContainer != null && currentTPDetailsContainer.size() > 0) {

            } else {
                AbstractNotificationUtils.getErrorNotification(MessageUtil.getErrorCode(Message.NO_RECORD_SUBMIT), MessageUtil.getErrorCode(Message.NO_VALID_CUSTOMER));
                return;
            }

            if (transferTPDetailsContainer != null && transferTPDetailsContainer.size() > 0) {

            } else {
                AbstractNotificationUtils.getErrorNotification(MessageUtil.getErrorCode(Message.NO_RECORD_SUBMIT), MessageUtil.getErrorCode(Message.NO_VALID_TRANSFER));
                return;
            }

        }
        if (session.getModuleName().equalsIgnoreCase(TRADING_PARTNER_REMOVE.getConstant())) {
            idOne = ButtonId.OK;
            idTwo = ButtonId.CANCEL;
            comfirmHeader = MessageUtil.getErrorCode(Message.PROCESS_CONFIRM);
            confirmContent = MessageUtil.getErrorCode(Message.END_DATE_SURE);
        } else if (session.getModuleName().equalsIgnoreCase(ADD_TRADING_PARTNER.getConstant())) {
            idOne = ButtonId.YES;
            idTwo = ButtonId.NO;
            idString = idOne.name();
            comfirmHeader = MessageUtil.getErrorCode(Message.PROCESS_CONFIRM);
            confirmContent = MessageUtil.getErrorCode(Message.ADD_SELECT_CUST);
        } else if (session.getModuleName().equalsIgnoreCase(TRADING_PARTNER_UPDATE.getConstant())) {
            idOne = ButtonId.YES;
            idTwo = ButtonId.NO;
            idString = idOne.name();
            comfirmHeader = MessageUtil.getErrorCode(Message.PROCESS_CONFIRM);
            confirmContent = MessageUtil.getErrorCode(Message.UPDATE_SELECT_CUST);
        } else if (session.getModuleName().equalsIgnoreCase(TRANSFER_TRADING_PARTNER.getConstant())) {
            idOne = ButtonId.YES;
            idTwo = ButtonId.NO;
            idString = idOne.name();
            comfirmHeader = MessageUtil.getErrorCode(Message.TRANSFER_CONFIRM);
            confirmContent = MessageUtil.getErrorCode(Message.TRANSFER_SELECT);
        } else {
            idOne = ButtonId.YES;
            idTwo = ButtonId.NO;
            idString = idOne.name();
            comfirmHeader = "Transfer Projection Details Confirmation";
            confirmContent = "Are you sure you want to Transfer the projection details from the selected Contract(s)to the selected Contract(s)";
        }
        MessageBox.showPlain(Icon.QUESTION, comfirmHeader, confirmContent
                + "?", new MessageBoxListener() {
                    /**
                     * Called when reset button is clicked
                     */
                    @SuppressWarnings("PMD")
                    @Override
                    public void buttonClicked(final ButtonId buttonId) {
                        List<String> tempList = new ArrayList<>();
                        List<String> tempTransferList = new ArrayList<>();
                        boolean projectionCreationFlag = false;
                        if (buttonId.name().equals(idString)) {
                            CommmonLogic logic = new CommmonLogic();
                            String msgHeader;
                            String msgContent;

                            try {
                                if (!PROJECTION_DETAILS_TRANSFER.getConstant().equals(session.getModuleName())) {
                                    logic.updateCfpDetails(session);
                                    if (TRANSFER_TRADING_PARTNER.getConstant().equals(session.getModuleName())) {
                                        logic.updateIfpDetailsForCfp(session);
                                    }
                                }

                                CommonLogic common = new CommonLogic();

                                int projectionId = CommonLogic.getProjectionIdForSubmittedContract(session.getSessionId(), session.getModuleName().equals(ADD_TRADING_PARTNER.getConstant()));
                                int toProjectionId = CommonLogic.getProjectionIdForSubmittedContract(session.getSessionId(), session.getModuleName().equals(ADD_TRADING_PARTNER.getConstant()));
                                session.setFromProjectionId(projectionId);
                                session.setToProjectionId(toProjectionId);
                                boolean transferSalesFlag = true;
                                if (ttpForm != null) {
                                    transferSalesFlag = ttpForm.isSalesCopyFlag();
                                }

                                if (projectionId != 0) {
                                    ContractResultDTO dto = currentTPDetailsContainer.getItemIds().get(0);
                                    session.setContractMasterSid(dto.getContractMasterSid());
                                    session.setContractType(dto.getContractType());
                                    if (session.getModuleName().equalsIgnoreCase(TRADING_PARTNER_REMOVE.getConstant()) || session.getModuleName().equalsIgnoreCase(TRADING_PARTNER_UPDATE.getConstant())) {
                                        tempList = common.createNewProjection(projectionId, session.getCompanyMasterSids(), session);
                                        if (tempList != null && !tempList.isEmpty()) {
                                            projectionCreationFlag = true;
                                        }

                                    } else if (session.getModuleName().equalsIgnoreCase(ADD_TRADING_PARTNER.getConstant())) {
                                        tempList = common.generateNewProjection(session.getUserId(), session.getSessionId(), projectionId, session.getCompanyMasterSids(), true, true,session);
                                        if (tempList != null && !tempList.isEmpty()) {
                                            projectionCreationFlag = true;
                                        }

                                    } else if (session.getModuleName().equalsIgnoreCase(TRANSFER_TRADING_PARTNER.getConstant()) || PROJECTION_DETAILS_TRANSFER.getConstant().equals(session.getModuleName())) {
                                        int fromProjectionId = projectionId;                                        
                                        session.setFromProjectionId(fromProjectionId);
                                        tempList = common.copyTempProjection(fromProjectionId, false, null, null, null,session);
                                        if (tempList != null && !tempList.isEmpty()) {
                                            int copyFromProjectionId = Integer.parseInt(tempList.get(NumericConstants.TWO));
                                            
                                            int copyToProjectionId = 0;
                                            if (toProjectionId != 0) {
                                                if (session.getModuleName().equalsIgnoreCase(TRANSFER_TRADING_PARTNER.getConstant())) {
                                                    tempTransferList = common.generateNewProjection(session.getUserId(), session.getSessionId(), toProjectionId, session.getCompanyMasterSids(), true, false,session);
                                                } else {
                                                    tempTransferList = common.copyProjection(toProjectionId, false, null, null, null,session);
                                                }
                                                copyToProjectionId = Integer.parseInt(tempTransferList.get(NumericConstants.TWO));
                                            } else {
                                            }
                                            int sourceContract = CommonLogic.getSelectedContractSid(session.getSessionId(), false);
                                            int destinationContract = CommonLogic.getSelectedContractSid(session.getSessionId(), true);
                                            String fromCustomerEndDate = CommonLogic.getDateForSubmittedContract(session.getSessionId(), false, false, true);
                                            String toCustomerStartDate = CommonLogic.getDateForSubmittedContract(session.getSessionId(), true, true, false);
                                            String companies;
                                            if (session.getModuleName().equalsIgnoreCase(TRANSFER_TRADING_PARTNER.getConstant())) {
                                                companies = CommonUtils.CollectionToString(session.getCompanyMasterSids(), false);
                                            } else {
                                                companies = CommmonLogic.generateCustomerMappings(session.getCompanyMasterSids(), session.getPhCompanyMasterSids());
                                            }
                                            CommonLogic.insertInputsBeforeTranfer(fromProjectionId, copyFromProjectionId, toProjectionId, copyToProjectionId, sourceContract,
                                                    destinationContract, companies, fromCustomerEndDate, toCustomerStartDate, ttpForm.isSalesRemoveFlag(), session.getSessionId(), transferSalesFlag);

                                            if (session.getModuleName().equalsIgnoreCase(TRANSFER_TRADING_PARTNER.getConstant())) {
                                                CommonLogic.callPrcFeSalesTransfer(session.getSessionId());
                                            } else {
                                                CommonLogic.callPrcFeProjectionDetailsTransfer(session.getSessionId());
                                            }

                                            projectionCreationFlag = true;
                                        } else {
                                        }

                                    } else {

                                    }
                                } else {
                                }
                            } catch (Exception e) {
                                LOGGER.error("",e);
                            }

                            if (projectionCreationFlag) {
                                if (session.getModuleName().equalsIgnoreCase(TRADING_PARTNER_REMOVE.getConstant())) {
                                    msgHeader = MessageUtil.getErrorCode(Message.DELETE_SUCCESS);
                                    msgContent = "The selected Customer has been deleted successfully";
                                    if (tempList != null && !tempList.isEmpty()) {
                                        msgContent = msgContent + NEW_PROJECTION_CREATED_WITH_FORECASTING + tempList.get(0)
                                        + AND_PROJECTION_NAME + tempList.get(1) + " ";
                                    }
                                } else if (session.getModuleName().equalsIgnoreCase(ADD_TRADING_PARTNER.getConstant())) {
                                    msgHeader = MessageUtil.getErrorCode(Message.ADD_SUCCESS);
                                    msgContent = "The selected Customer has been added successfully";
                                    if (tempList != null && !tempList.isEmpty()) {
                                        msgContent = msgContent + NEW_PROJECTION_CREATED_WITH_FORECASTING + tempList.get(0)
                                        + AND_PROJECTION_NAME + tempList.get(1) + " ";
                                    }
                                } else if (session.getModuleName().equalsIgnoreCase(TRADING_PARTNER_UPDATE.getConstant())) {
                                    msgHeader = MessageUtil.getErrorCode(Message.UPDATE_SUCCESS);
                                    msgContent = "The selected Customer has been updated successfully.";
                                    if (tempList != null && !tempList.isEmpty()) {
                                        msgContent = msgContent + NEW_PROJECTION_CREATED_WITH_FORECASTING + tempList.get(0)
                                        + AND_PROJECTION_NAME + tempList.get(1) + " ";
                                    }
                                } else {
                                    msgHeader = MessageUtil.getErrorCode(Message.TRANSFER_SUCCESS);
                                    msgContent = "The selected Customer has been transferred successfully";
                                    String forecastType;
                                    if (tempList.get(0).equals(tempTransferList.get(0))) {
                                        forecastType = tempTransferList.get(0);
                                    } else {
                                        forecastType = tempList.get(0) + " , " + tempTransferList.get(0);
                                    }
                                    if (tempList != null && !tempList.isEmpty()) {
                                        msgContent = msgContent + "\n New Projections are created with forecasting type -" + forecastType
                                        + " \n and Projection Names - " + tempList.get(1) + " ," + tempTransferList.get(1) + "";
                                    }
                                }

                                MessageBox.showPlain(Icon.INFO, msgHeader, msgContent, new MessageBoxListener() {
                                    /**
                                     * The method is triggered when a button of
                                     * the message box is pressed.
                                     */
                                    @SuppressWarnings("PMD")
                                    @Override
                                    public void buttonClicked(final ButtonId buttonId) {
                                        if (session.getModuleName().equals(TRADING_PARTNER_REMOVE.getConstant())) {
                                            rtpform.close();
                                        } else if (session.getModuleName().equals(ADD_TRADING_PARTNER.getConstant())) {
                                            addTpForm.close();

                                        } else if (session.getModuleName().equals(TRADING_PARTNER_UPDATE.getConstant())) {
                                            updateTPForm.close();

                                        } else {
                                            ttpForm.close();
                                        }
                                    }
                                }, ButtonId.OK);
                            } else {
                                AbstractNotificationUtils.getErrorNotification("No Projection Created", "There is some error in creating projection. "
                                        + "\n kindly check whether the selected Projection has relationship that is set to Automatic");
                            }

                        }
                    }
                }, idOne, idTwo);
    }

    @UiHandler("previous")
    public void previousButtonLogic(Button.ClickEvent event) {
        if (TRADING_PARTNER_REMOVE.getConstant().equalsIgnoreCase(session.getModuleName())) {
            rtpform.getTabSheet().setSelectedTab(rtpform.getTabPosition() - 1);

        } else if (ADD_TRADING_PARTNER.getConstant().equalsIgnoreCase(session.getModuleName())) {
            addTpForm.getTabSheet().setSelectedTab(0);

        } else if (session.getModuleName().equals(TRADING_PARTNER_UPDATE.getConstant())) {
            updateTPForm.getTabSheet().setSelectedTab(updateTPForm.getTabPosition() - 1);

        } else {
            ttpForm.getTabSheet().setSelectedTab(ttpForm.getTabPosition() - 1);
        }
    }

    public void refreshCurrentTPDetails(String screenName) {
        CommmonLogic logic = new CommmonLogic();
        currentTPDetailsContainer.removeAllItems();
        List<ContractResultDTO> checkedContractList = logic.getContractResults(CommmonLogic.getSubmittedRecords(session, screenName, false));
        currentTPDetailsContainer.addAll(checkedContractList);
        isLoad = true;
        isRebateLoad = true;
    }

    public void refreshTransferTPDetails() {
        CommmonLogic logic = new CommmonLogic();
        transferTPDetailsContainer.removeAllItems();
        List<ContractResultDTO> checkedContractList = logic.getContractResults(CommmonLogic.getSubmittedRecords(session, TAB_TRANSFER_CONTRACT.getConstant(), false));
        transferTPDetailsContainer.addAll(checkedContractList);
    }

    public boolean isCurrentContractRefresh() {
        return layout.isCurrentContractRefresh();
    }

    public void setCurrentContractRefresh(boolean currentContractRefresh) {
        layout.setCurrentContractRefresh(currentContractRefresh);
    }

    public boolean isTransferContractRefresh() {
        return layout.isTransferContractRefresh();
    }

    public void setTransferContractRefresh(boolean transferContractRefresh) {
        layout.setTransferContractRefresh(transferContractRefresh);
    }

    /**
     * Load frequency.
     *
     * @param event the event
     */
    private Panel addSelectionCriteriaLayout() {
        selCriteriaGrid.addComponent(companyNo);
        companyNo.setWidth(ONE_THIRTY_PX);
        companyNumber.setEnabled(false);
        selCriteriaGrid.addComponent(companyNumber);
        selCriteriaGrid.addComponent(companyName);
        companyName.setWidth(ONE_THIRTY_PX);
        companyNameField.setEnabled(false);
        selCriteriaGrid.addComponent(companyNameField);
        selCriteriaGrid.addComponent(companyType);
        companyType.setWidth(ONE_THIRTY_PX);
        companyTypeField.setEnabled(false);
        selCriteriaGrid.addComponent(companyTypeField);
        selCriteriaGrid.addComponent(companyCategory);
        companyCategory.setWidth("155px");
        companyCategoryField.setEnabled(false);
        selCriteriaGrid.addComponent(companyCategoryField);
        selCriteriaGrid.addComponent(tradeClass);
        tradeClass.setWidth(ONE_THIRTY_PX);
        tradeClassField.setEnabled(false);
        selCriteriaGrid.addComponent(tradeClassField);
        selCriteriaLayout.setMargin(true);
        selCriteriaLayout.addComponent(selCriteriaGrid);
        selCriteriaPanel.setContent(selCriteriaLayout);
        return selCriteriaPanel;
    }

    private void configureSecurityPermissions() {
        try {
            String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(USER_ID));
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, GCM_CUSTOMER_MANAGEMENT, "Add Customer", "Add Customer Screen");
            previous.setVisible(CommonLogic.isButtonVisibleAccess(PREVIOUS_BTN, functionHM));
            close.setVisible(CommonLogic.isButtonVisibleAccess(CLOSE_BTN, functionHM));
            functionalButton.setVisible(CommonLogic.isButtonVisibleAccess(FUNCTIONAL_BUTTON, functionHM));

        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
    }

    private void configureSecurityPermissionsForTransfer() {
        try {
            String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(USER_ID));
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, GCM_CUSTOMER_MANAGEMENT, "Transfer Customer", "TranferContractTab");
            previous.setVisible(CommonLogic.isButtonVisibleAccess(PREVIOUS_BTN, functionHM));
            close.setVisible(CommonLogic.isButtonVisibleAccess(CLOSE_BTN, functionHM));
            functionalButton.setVisible(CommonLogic.isButtonVisibleAccess(FUNCTIONAL_BUTTON, functionHM));

            } catch (Exception ex) {
            LOGGER.error("",ex);
        }
    }
    private void configureSecurityPermissionsUpdate() {
        try {
            String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(USER_ID));
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, GCM_CUSTOMER_MANAGEMENT, "UpdateCustomer", "SummaryTab");
            previous.setVisible(CommonLogic.isButtonVisibleAccess(PREVIOUS_BTN, functionHM));
            close.setVisible(CommonLogic.isButtonVisibleAccess(CLOSE_BTN, functionHM));
            functionalButton.setVisible(CommonLogic.isButtonVisibleAccess(FUNCTIONAL_BUTTON, functionHM));
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
    }
}