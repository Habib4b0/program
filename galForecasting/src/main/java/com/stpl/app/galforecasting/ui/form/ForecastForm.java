package com.stpl.app.galforecasting.ui.form;

import com.stpl.app.bpm.dto.ForecastingRulesDTO;
import com.stpl.app.bpm.dto.WorkflowRuleDTO;
import com.stpl.app.galforecasting.abstractforecast.AbstractForm;
import com.stpl.app.galforecasting.additionalinformation.form.AdditionalInformationForm;
import com.stpl.app.galforecasting.bpm.logic.DSCalculationLogic;
import com.stpl.app.galforecasting.bpm.logic.VarianceCalculationLogic;
import com.stpl.app.galforecasting.bpm.service.BPMProcessBean;
import com.stpl.app.galforecasting.bpm.service.MailWorkItemHandler;
import com.stpl.app.galforecasting.bpm.util.MessageUtils;
import com.stpl.app.galforecasting.discountProjection.form.MSupplementalDiscountProjection;
import com.stpl.app.galforecasting.discountProjection.form.NMDiscountProjection;
import com.stpl.app.galforecasting.discountprojectionresults.form.MDiscountProjectionResults;
import com.stpl.app.galforecasting.discountprojectionresults.form.ManagedDiscountProjectionResult;
import com.stpl.app.galforecasting.discountprojectionresults.form.NMDiscountProjectionResults;
import com.stpl.app.galforecasting.logic.CommonLogic;
import com.stpl.app.galforecasting.logic.DataSelectionLogic;
import com.stpl.app.galforecasting.logic.DiscountProjectionLogic;
import com.stpl.app.galforecasting.logic.NonMandatedLogic;
import com.stpl.app.galforecasting.logic.SalesProjectionLogic;
import com.stpl.app.galforecasting.ppaprojection.form.PPAProjection;
import com.stpl.app.galforecasting.ppaprojection.logic.PPAProjectionLogic;
import com.stpl.app.galforecasting.pparesults.form.PPAProjectionResults;
import com.stpl.app.galforecasting.projectionresults.form.MProjectionResults;
import com.stpl.app.galforecasting.projectionresults.form.NMProjectionResults;
import com.stpl.app.galforecasting.projectionvariance.form.MProjectionVariance;
import com.stpl.app.galforecasting.projectionvariance.form.NMProjectionVariance;
import com.stpl.app.galforecasting.salesprojection.form.MSalesProjection;
import com.stpl.app.galforecasting.salesprojection.form.NMSalesProjection;
import com.stpl.app.galforecasting.salesprojection.form.ReturnsProjection;
import com.stpl.app.galforecasting.salesprojection.utils.SalesUtils;
import com.stpl.app.galforecasting.salesprojectionresults.form.MSalesProjectionResults;
import com.stpl.app.galforecasting.salesprojectionresults.form.NMSalesProjectionResults;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.galforecasting.ui.ForecastEditWindow;
import com.stpl.app.galforecasting.ui.ForecastMainView;
import com.stpl.app.galforecasting.ui.ForecastUI;
import com.stpl.app.galforecasting.ui.NonMandatedViewWindow;
import com.stpl.app.galforecasting.ui.form.lookups.WorkFlowNotesLookup;
import com.stpl.app.galforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import com.stpl.app.galforecasting.utils.DataSelectionUtil;
import com.stpl.app.galforecasting.utils.FunctionNameUtil;
import com.stpl.app.galforecasting.utils.NotificationUtils;
import com.stpl.app.galforecasting.workflow.dto.WorkflowMasterDTO;
import com.stpl.app.galforecasting.workflow.logic.WorkflowLogic;
import com.stpl.app.model.ProjectionMaster;
import com.stpl.app.model.WorkflowMaster;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.service.ProjectionMasterLocalServiceUtil;
import com.stpl.app.util.Constants;
import com.stpl.app.util.ConstantsUtils;

import static com.stpl.app.utils.Constants.ButtonConstants.*;
import static com.stpl.app.utils.Constants.CommonConstants.ACTION_EDIT;
import static com.stpl.app.utils.Constants.CommonConstants.ACTION_VIEW;
import static com.stpl.app.utils.Constants.IndicatorConstants.*;
import static com.stpl.app.utils.Constants.LabelConstants.*;

import static com.stpl.app.utils.Constants.WindowMessagesName.CONFIRMATION;
import com.stpl.app.utils.TableHeaderColumnsUtil;
import com.stpl.app.utils.UiUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.NotesDTO;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.util.CommonUIUtils;
import static com.stpl.ifs.util.constants.GlobalConstants.*;
import com.stpl.ifs.util.constants.WorkflowConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customwindow.MinimizeTray;
import org.asi.ui.extfilteringtable.ExtFilterTable;

// TODO: Auto-generated Javadoc
/**
 * Contains and Controls the tabsheet containing all the screen.
 *
 * @author soundarrajan
 */
public class ForecastForm extends AbstractForm {

    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(ForecastForm.class);
    /**
     * The data.
     */
    private DataSelection data;
    /**
     * Object for Sales Projection Tab.
     */
    private NMSalesProjection nmSalesProjection;
    /**
     * Object for Sales Projection Tab.
     */
    MSalesProjection salesProjectionForMandated;
    /**
     * Object for Sales Projection Tab.
     */
    private NMSalesProjectionResults salesProjectionResults;
    /**
     * Object for Sales Projection Tab.
     */
    private MSalesProjectionResults salesProjectionResultsForMandated;
    /**
     * Object for Sales Projection Tab.
     */
    private MSupplementalDiscountProjection supplementalDiscountProjectionForMandated;
    /**
     * Object for Discount Projection Tab.
     */
    private NMDiscountProjection discountProjection;
    /**
     * Object for Discount Projection Results Tab.
     */
    private NMDiscountProjectionResults discountProjectionResults;
    /**
     * Object for Discount Projection Results Tab.
     */
    private MDiscountProjectionResults discountProjectionResultsForMandated;
    /**
     * Object for PPA Projection Tab.
     */
    private PPAProjection ppaProjection;
    /**
     * Object for PPA Projection Results Tab.
     */
    private PPAProjectionResults ppaProjectionResults;
    /**
     * Object for Projection Results Tab.
     */
    MProjectionResults mandatedprojectionResults;
    NMProjectionResults nonmandatedprojectionResults;
    /**
     * Object for Projection Variance Tab.
     */
    private NMProjectionVariance projectionVariance;
    /**
     * Object for Projection Variance Tab.
     */
    private MProjectionVariance projectionVarianceForMandated;
    /**
     * Object for Additional Information Tab.
     */
    private AdditionalInformationForm additionalInformation;
    /**
     * Object for Additional Information Tab.
     */
    /**
     * Object for Additional Information Tab.
     */
    private ManagedDiscountProjectionResult mmdiscountProjectionResultsForMandated;
    public ReturnsProjection returnsProjection;
    /**
     * Tabsheet containing all the screens.
     */
    private TabSheet tabSheet;
    /**
     * The projection id.
     */
    private int projectionId;
    boolean validationFlag = true;
    /**
     * Map for lazy loading.
     */
    private final Map<Integer, Boolean> tabLazyLoadMap = new HashMap<Integer, Boolean>();
    private final Map<String, Boolean> pushMap = new HashMap<String, Boolean>();
    final StplSecurity stplSecurity = new StplSecurity();
    final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constant.USER_ID));
    /**
     * Position of the tab.
     */
    private int tabPosition;
    private CustomFieldGroup dataSelectionBinder;
    private DataSelectionDTO dataSelectionDTO;
    SessionDTO session;
    ForecastEditWindow editWindow;
    int lastPosition;
    private Button btnNext = new Button(BTN_NEXT.getConstant());
    private Button btnPrev = new Button(BTN_PREVIOUS.getConstant());
    private Button btnRefresh = new Button("REFRESH");
    ExtFilterTable resultTable;
    NonMandatedViewWindow viewWindow;
    public int tempTabPosition = 0;
    boolean dsFlag = true;
    String screenName;
    final DataSelectionForm dataSelectionForm;
    DataSelectionLogic dsLogic = new DataSelectionLogic();
    NonMandatedLogic logic = new NonMandatedLogic();
    public static ResourceBundle alertMsg = ResourceBundle.getBundle("properties.alertmessage");
    boolean discountFlag = true;
    boolean ppaFlag = true;

    public ForecastForm(CustomFieldGroup dataSelectionBinder, DataSelectionDTO dataSelectionDTO, SessionDTO session, ForecastEditWindow editWindow,
            final ExtFilterTable resultTable, final String screenName, final DataSelectionForm dataSelectionForm) throws SystemException, Exception {

        this.dataSelectionBinder = dataSelectionBinder;
        this.dataSelectionDTO = dataSelectionDTO;
        this.session = session;
        this.editWindow = editWindow;
        this.resultTable = resultTable;
        this.screenName = screenName;
        this.dataSelectionForm = dataSelectionForm;
        try {
            if (Constant.ADD_SMALL.equalsIgnoreCase(session.getAction())) {
                checkForActualSales();
            } else {
                init();
                addContent();
                if (Constant.EDIT.equalsIgnoreCase(session.getAction())) {
                    getBtnSave().setCaption("UPDATE");
                }
            }

        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    private void forecastDTOConfiguration() throws Exception {
        DataSelectionUtil.getForecastDTO(dataSelectionDTO, session);
    }

    private void init() throws SystemException, Exception {

        session.setPpaIndicator(true);
        forecastDTOConfiguration();
        loadMarketTypeValue();
        NonMandatedLogic logic = new NonMandatedLogic();
        this.data = new DataSelection(dataSelectionBinder, dataSelectionDTO, session, this, screenName, dataSelectionForm);
        if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {

            if (ACTION_EDIT.getConstant().equalsIgnoreCase(session.getAction()) || ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction())) {
                logic.clearTemp(session, "nm.clearTemp");
                logic.tempInsert(session, "nm.tempInsert");
                if (ACTION_EDIT.getConstant().equalsIgnoreCase(session.getAction())) {
                    dsLogic.callSalesInsertProcedure(session.getProjectionId(), session.getUserId(), session.getSessionId(), SalesUtils.NM_SALES_PRO_NAME);
                }
            }

            this.salesProjectionResults = new NMSalesProjectionResults(session, screenName);
            this.nmSalesProjection = new NMSalesProjection(session, screenName);
            this.discountProjection = new NMDiscountProjection(session, screenName);
            this.discountProjectionResults = new NMDiscountProjectionResults(session, screenName, this);
            this.ppaProjection = new PPAProjection(session);
            this.ppaProjectionResults = new PPAProjectionResults(session);
            this.projectionVariance = new NMProjectionVariance(this, session, screenName);
            this.nonmandatedprojectionResults = new NMProjectionResults(session, screenName, this);
            this.additionalInformation = new AdditionalInformationForm(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED, StringUtils.EMPTY, session.getProjectionId(), session.getAction());
        } else if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {
            if (ACTION_EDIT.getConstant().equalsIgnoreCase(session.getAction()) || ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction())) {
                logic.clearTemp(session);
                logic.tempInsert(session);
            }
            this.salesProjectionResultsForMandated = new MSalesProjectionResults(session, screenName);
            this.salesProjectionForMandated = new MSalesProjection(session, screenName);
            this.supplementalDiscountProjectionForMandated = new MSupplementalDiscountProjection(session, screenName);
            this.discountProjectionResultsForMandated = new MDiscountProjectionResults(session, screenName);
            this.mmdiscountProjectionResultsForMandated = new ManagedDiscountProjectionResult(session, screenName);
            this.mandatedprojectionResults = new MProjectionResults(session, screenName);
            this.additionalInformation = new AdditionalInformationForm(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED, StringUtils.EMPTY, session.getProjectionId(), session.getAction());
            this.projectionVarianceForMandated = new MProjectionVariance(session, screenName);

        } else if (CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS.equals(screenName)) {
            if (ACTION_EDIT.getConstant().equalsIgnoreCase(session.getAction()) || ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction())) {
                logic.tempInsertReturns(session, "RETURNS_EDIT_TEMP_INSERT_QUERY");
            }
            this.returnsProjection = new ReturnsProjection(session, screenName);

        }
    }

    /**
     * Adds the content to the screen.
     *
     * @throws Exception
     * @throws SystemException
     */
    private void addContent() throws SystemException, Exception {
        initializeTabs();
        initializeLazyTabLoad(tabLazyLoadMap, tabSheet.getComponentCount());
        buildScreen();

        if (session != null && (Constant.EDIT_SMALL.equalsIgnoreCase(session.getAction()) || Constant.VIEW.equalsIgnoreCase(session.getAction())) && session.getWorkflowId() != 0) {

            if (WorkflowConstants.getApproverConstant().equals(session.getWorkflowUserType())) {

                getBtnWithdraw().setVisible(false);
                getBtnSubmit().setVisible(false);

                boolean buttonVisiblity = WorkflowConstants.getPendingStatus().equalsIgnoreCase(session.getWorkflowStatus());
                getBtnApprove().setVisible(buttonVisiblity);
                getBtnReject().setVisible(buttonVisiblity);
                getBtnCancel().setVisible(buttonVisiblity);

            } else if (WorkflowConstants.getCreatorConstant().equals(session.getWorkflowUserType())) {

                getBtnApprove().setVisible(false);
                getBtnReject().setVisible(false);
                getBtnCancel().setVisible(false);

                if (WorkflowConstants.getPendingStatus().equalsIgnoreCase(session.getWorkflowStatus())) {
                    getBtnWithdraw().setVisible(true);
                    getBtnSubmit().setVisible(false);
                    getBtnCancel().setVisible(false);
                } else if (WorkflowConstants.getWithdrawnStatus().equalsIgnoreCase(session.getWorkflowStatus()) || WorkflowConstants.getRejectedStatus().equalsIgnoreCase(session.getWorkflowStatus())) {
                    getBtnWithdraw().setVisible(false);
                    getBtnSubmit().setVisible(true);
                    getBtnCancel().setVisible(false);
                } else if (WorkflowConstants.getApprovedStatus().equals(session.getWorkflowStatus()) || WorkflowConstants.getCancelledStatus().equals(session.getWorkflowStatus())) {
                    getBtnWithdraw().setVisible(false);
                    getBtnSubmit().setVisible(false);
                }
            }
        } else {
            getBtnApprove().setVisible(false);
            getBtnReject().setVisible(false);
            getBtnWithdraw().setVisible(false);
            getBtnCancel().setVisible(false);
        }

        if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS)) {
            getBtnSubmit().setVisible(Boolean.FALSE);
        } else {
            getBtnSubmit().setEnabled(true);
        }

        configureForView();
        setTabSecurity();
        setButtonSecurity();
        tabSheet.setSelectedTab(1);
    }

    protected void initializeLazyTabLoad(final Map<Integer, Boolean> tabLazyLoadMap, final int componentCount) {
        tabLazyLoadMap.clear();
        for (int i = 0, tabCount = componentCount; i < tabCount; i++) {
            if (i == 1 || i == 0) {
                tabLazyLoadMap.put(i, Boolean.TRUE);
            } else {
                tabLazyLoadMap.put(i, Boolean.FALSE);
            }
        }
        pushMap.put(INDICATOR_REFRESH_UPDATE.getConstant(), false);
        pushMap.put(INDICATOR_TIME_PERIOD_CHANGED.getConstant(), false);

    }

    /**
     * To initialize and configure the tabLazyLoadMap for lazy tab loading.
     */
    @Override
    protected void initializeTabs() {
        tabSheet = new TabSheet();
        tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
        tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        tabSheet.setImmediate(true);
        try {
            tabSheet.addTab(data, TAB_DATA_SELECTION.getConstant(), null, 0);
            if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
                tabSheet.addTab(nmSalesProjection, TAB_SALES_PROJECTION.getConstant(), null, 1);
                tabSheet.addTab(salesProjectionResults, TAB_SALES_PROJECTION_RESULTS.getConstant(), null, 2);
                tabSheet.addTab(discountProjection, TAB_DISCOUNT_PROJECTION.getConstant(), null, 3);
                tabSheet.addTab(discountProjectionResults, TAB_DISCOUNT_PROJECTION_RESULTS.getConstant(), null, 4);
//                tabSheet.addTab(ppaProjection, TAB_PPA_PROJECTION.getConstant(), null, 5);
                tabSheet.addTab(ppaProjectionResults, TAB_PPA_PROJECTION_RESULTS.getConstant(), null, 5);
                tabSheet.addTab(nonmandatedprojectionResults, TAB_PROJECTION_RESULTS.getConstant(), null, 6);
                tabSheet.addTab(projectionVariance, TAB_PROJECTION_VARIANCE.getConstant(), null, 7);
                tabSheet.addTab(additionalInformation, TAB_ADDITIONAL_INFORMATION.getConstant(), null, 8);
                ppaProjection.setVisible(false);
            } else if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {
                tabSheet.addTab(salesProjectionForMandated, "Sales Projection", null, 1);
                tabSheet.addTab(salesProjectionResultsForMandated, "Sales Projection Results", null, 2);
                tabSheet.addTab(supplementalDiscountProjectionForMandated, "Supplemental Discount Projection", null, 3);
                String definedOrUDValue = StringUtils.EMPTY;
                String definedValue = dsLogic.getDefinedValue(dataSelectionDTO.getCustomerHierSid());
                if ("User Defined".equalsIgnoreCase(definedValue)) {
                    definedOrUDValue = session.getMarketType();
                    session.setMarketTypeValue(definedOrUDValue);
                }
                if (Constant.MM.equalsIgnoreCase(session.getMarketTypeValue()) || Constant.Managed_Medicaid.equalsIgnoreCase(session.getMarketTypeValue())) {
                    tabSheet.addTab(mmdiscountProjectionResultsForMandated, "Discount Projection Results",
                            null, 4);
                } else {
                    tabSheet.addTab(discountProjectionResultsForMandated, "Discount Projection Results",
                            null, 4);
                }

                tabSheet.addTab(mandatedprojectionResults, "Projection Results", null, 5);
                tabSheet.addTab(projectionVarianceForMandated, "Projection Variance", null, 6);
                tabSheet.addTab(additionalInformation, "Additional Information", null, 7);
            } else if (CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS.equals(screenName)) {
                tabSheet.addTab(returnsProjection, "Returns Projection", null, 1);
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        attachTabChangeListener();
        if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
//            ppaProjection.setVisible(session.isCidtIndicator());
            ppaProjectionResults.setVisible(session.isCidtIndicator());
        }
    }

    private void attachTabChangeListener() {
        tabSheet.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
            @Override
            public void selectedTabChange(final TabSheet.SelectedTabChangeEvent event) {
                final Tab tab = (Tab) event.getTabSheet().getTab(event.getTabSheet().getSelectedTab());
                tabPosition = event.getTabSheet().getTabPosition(tab);
                buttonEnableLogic(tabPosition, tabSheet.getComponentCount() - 1);
                try {
                    if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
                        onTabChange(tabPosition);
                    } else if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {
                        onTabChangeForMandated(tabPosition);
                    } else if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS)) {
                        onTabChangeForReturns(tabPosition);
                    }
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        });
    }

    protected void buttonEnableLogic(int tabPosition, int i) {
        if (tabPosition != 0) {
            btnPrev.setVisible(true);
        } else if (tabPosition == 0) {
            btnPrev.setVisible(false);
        }
        if (tabPosition == i) {
            btnNext.setVisible(false);
            btnRefresh.setVisible(true);

        } else {
            btnNext.setVisible(true);
            btnRefresh.setVisible(false);
        }
        if (tabPosition <= i) {
            btnPrev.setEnabled(true);
        }
        if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
            if (tabPosition == 8) {
                btnRefresh.setVisible(false);
            }
        }
        if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS)) {
            if (tabPosition == 0 || tabPosition == 1) {
                btnRefresh.setVisible(false);
            }
        }
    }

    /**
     * Adds the tabsheet and footer buttons to the screen
     */
    @Override
    protected void buildScreen() {

        final VerticalLayout vLayout = (VerticalLayout) UiUtils.getLayout(VerticalLayout.class);
        vLayout.addComponent(tabSheet);
        vLayout.addComponent(addFooterButtons(btnNext, btnPrev, btnRefresh));
        configureFields();
        addComponent(vLayout);
        session.setSaveFlag(false);
        session.setSubmitFlag(false);

    }

    /**
     * Configure fields.
     */
    private void configureFields() {
        tabSheet.setImmediate(true);
        btnPrev.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                btnPreviousLogic();
            }
        });
        btnNext.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {

                if (data.isUpdateOnTabChange()) {
                    if (!data.isDataSelectionValid()) {
                        data.setUpdateOnTabChange(true);
                        dsFlag = true;
                        AbstractNotificationUtils.getErrorNotification("Selection Criteria",
                                "Not all required fields have been populated. Please try again.");
                        return;
                    } else {
                        new AbstractNotificationUtils() {
                            @Override
                            public void noMethod() {
                                data.configureOnTabLoad(session.getProjectionId(), dataSelectionDTO);
                            }

                            @Override
                            public void yesMethod() {
                                try {
                                    data.updateDataSelection();
                                    if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS)) {
                                        List<Integer> productListValue = DataSelection.getProductBeanLisTemp();
                                        data.updateDataSelectionSelectedProducts(productListValue);
                                        data.updateProjectionProdHierarchy();
                                        dsLogic.updateReturnDetails(session);
                                        session.setReturnsDetailsMap(dsLogic.getReturnDetails(null, session, true));
                                        dsLogic.callSalesInsertProcedure(session.getProjectionId(), session.getUserId(), session.getSessionId(), SalesUtils.RETURNS_SALES_INSERT_PRO_NAME);
                                    } else {
                                        data.updateDataSelectionProjectionTables(screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED) ? "GOVERMENT_TABLES" : "COMMERCIAL_TABLES");
                                    }
                                    pushUpdate(INDICATOR_REFRESH_UPDATE.getConstant());
                                    if (session.isFromDateChanged()) {
                                        DataSelectionUtil.getForecastDTO(dataSelectionDTO, session);
                                        pushUpdate(INDICATOR_TIME_PERIOD_CHANGED.getConstant());
                                        session.setFromDateChanged(false);
                                    }
                                    btnNextLogic();
                                    if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
                                        nmSalesProjection.init();
                                    } else if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {
                                        salesProjectionForMandated.init();

                                    } else if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS)) {
                                        returnsProjection.customContainer.removeAllItems();
                                        returnsProjection.init();
                                    }
                                } catch (Exception ex) {
                                    LOGGER.info(ex);
                                }

                            }
                        }.getConfirmationMessage("Update confirmation", "Data Selection values have changed. All other tabs will be updated and unsaved data will be lost. Continue?");
                    }

                    data.setUpdateOnTabChange(Boolean.FALSE);
                } else {
                    btnNextLogic();
                }
            }
        });
    }

    /**
     * Called on tab change.
     *
     * @param tabPosition the tab position
     */
    @Override
    protected void onTabChange(int tabPosition) {
        LOGGER.info("onTabChange starts");
        try {
            if (lastPosition == data.getTabNumber()) {

                if (data.isUpdateOnTabChange() && dsFlag) {
                    dsFlag = false;
                    tempTabPosition = tabPosition;
                    tabSheet.setSelectedTab(0);
                    new AbstractNotificationUtils() {
                        @Override
                        public void yesMethod() {
                            try {
                                if (data.isDataSelectionValid()) {
                                    data.updateDataSelection();
                                    data.updateDataSelectionProjectionTables("COMMERCIAL_TABLES");
//                                      salesProjection.initialTableLoad();
                                    pushUpdate(INDICATOR_REFRESH_UPDATE.getConstant());
                                    if (session.isFromDateChanged()) {
                                        DataSelectionUtil.getForecastDTO(dataSelectionDTO, session);
                                        pushUpdate(INDICATOR_TIME_PERIOD_CHANGED.getConstant());
                                        session.setFromDateChanged(false);
                                    }
                                    tabSheet.setSelectedTab(tempTabPosition);
                                    dsFlag = true;
                                    discountFlag = true;
                                    nmSalesProjection.init();
                                    discountProjection.getContent();
                                } else {

                                    Tab tabToReset = tabSheet.getTab(1);
                                    tabSheet.removeTab(tabToReset);
                                    tabSheet.addTab(nmSalesProjection, "Sales Projection", null, 1);
                                    dsFlag = true;
                                    data.setUpdateOnTabChange(Boolean.TRUE);
                                    tabSheet.setSelectedTab(0);
                                    lastPosition = 0;
                                    AbstractNotificationUtils.getErrorNotification("Selection Criteria",
                                            "Not all required fields have been populated. Please try again.");
                                }
                            } catch (Exception ex) {
                                LOGGER.error(ex);
                            }
                        }

                        @Override
                        public void noMethod() {

                            Tab tabToReset = tabSheet.getTab(1);
                            tabSheet.removeTab(tabToReset);
                            tabSheet.addTab(nmSalesProjection, "Sales Projection", null, 1);
                            dsFlag = true;
                            try {

                                data.configureOnTabLoad(session.getProjectionId(), dataSelectionDTO);
                            } catch (Exception ex) {
                                LOGGER.error(ex);
                            }
                            tabSheet.setSelectedTab(0);
                            lastPosition = 0;
                        }
                    }.getConfirmationMessage("Update confirmation", "Data Selection values have changed. All other tabs will be updated and unsaved data will be lost. Continue?");
                    data.setReloadAfterUpdate(Boolean.TRUE);
                    data.setUpdateOnTabChange(Boolean.FALSE);
                }

            }
            /**
             * Saving Projection selection for projection variance data load
             *
             */
            if (lastPosition == discountProjection.getTabNumber()) {
                discountProjection.saveSelections();
            }
            if (tabPosition == 2) {
                salesProjectionResults.configure();
            }
            if (tabPosition == discountProjection.getTabNumber()) {
                if (discountProjection.isListviewGenerated && discountFlag) {
                    discountProjection.saveDiscountProjectionScreen(false);
                    discountProjection.callDiscountProjectionProcedure();
                    discountFlag = false;
                }
            }

            if (tabPosition == nonmandatedprojectionResults.getTabNumber() && tabLazyLoadMap.get(nonmandatedprojectionResults.getTabNumber())) {
                session.setPpaIndicator(true);
                nonmandatedprojectionResults.loadGroupFilterOntabChange();
            }
            if (!tabLazyLoadMap.get(tabPosition)) {
                detachListeners(tabSheet);
                lazyLoadTab(tabPosition);
                tabLazyLoadMap.put(tabPosition, Boolean.TRUE);
                attachTabChangeListener();
            }
            if (tabPosition == projectionVariance.getTabNumber() && tabLazyLoadMap.get(projectionVariance.getTabNumber())) {
                projectionVariance.configure();
                projectionVariance.loadGroupFilterOntabChange();
            }
            if (tabPosition == discountProjectionResults.getTabNumber() && tabLazyLoadMap.get(discountProjectionResults.getTabNumber())) {
                discountProjectionResults.configure();
                discountProjection.callDiscountProjectionProcedure();
                discountProjectionResults.loadGroupFilter();
            }
//            if (tabPosition == ppaProjectionResults.getTabNumber()) {
//                PPAProjectionLogic.nonMandatedPPAProjectionCalculate(session.getProjectionId(), Integer.valueOf(session.getUserId()), Integer.valueOf(session.getSessionId()));
//            }
            push(tabPosition);

            lastPosition = tabPosition;
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.info("onTabChange ends");
    }

    private void onTabChangeForReturns(int tabPositionForM) throws PortalException, SystemException {
        try {

            if (lastPosition == data.getTabNumber()) {
                if (data.isUpdateOnTabChange() && dsFlag) {
                    dsFlag = false;
                    tempTabPosition = tabPosition;
                    tabSheet.setSelectedTab(0);
                    new AbstractNotificationUtils() {
                        @Override
                        public void yesMethod() {
                            try {
                                if (data.isReturnsDataSelectionValid()) {
                                    List<Integer> productListValue = DataSelection.getProductBeanLisTemp();

                                    data.updateDataSelection();
                                    data.updateDataSelectionSelectedProducts(productListValue);
                                    data.updateProjectionProdHierarchy();
                                    dsLogic.updateReturnDetails(session);
                                    session.setReturnsDetailsMap(dsLogic.getReturnDetails(null, session, true));
                                    dsLogic.callSalesInsertProcedure(session.getProjectionId(), session.getUserId(), session.getSessionId(), SalesUtils.RETURNS_SALES_INSERT_PRO_NAME);
                                    if (session.isFromDateChanged()) {
                                        DataSelectionUtil.getForecastDTO(dataSelectionDTO, session);
                                        session.setFromDateChanged(false);
                                    }
                                    tabSheet.setSelectedTab(tempTabPosition);
                                    tabPosition = tempTabPosition;
                                    dsFlag = true;

                                    returnsProjection.customContainer.removeAllItems();
                                    returnsProjection.init();

                                } else {

                                    try {
                                        Tab tabToReset = tabSheet.getTab(1);
                                        tabSheet.removeTab(tabToReset);
                                        tabSheet.addTab(returnsProjection, "Returns Projection", null, 1);
                                        tabSheet.setSelectedTab(0);
                                        data.configureOnLoading(session.getProjectionId(), dataSelectionDTO);
                                        tabPosition = 0;
                                        dsFlag = true;
                                    } catch (Exception e) {
                                        LOGGER.error(e);
                                    }

                                    AbstractNotificationUtils.getErrorNotification("Selection Criteria",
                                            "Not all required fields have been populated. Please try again.");
                                }
                            } catch (Exception ex) {
                                LOGGER.error(ex);
                            }
                        }

                        @Override
                        public void noMethod() {
                            try {
                                Tab tabToReset = tabSheet.getTab(1);
                                tabSheet.removeTab(tabToReset);
                                tabSheet.addTab(returnsProjection, "Returns Projection", null, 1);
                                tabSheet.setSelectedTab(0);
                                data.configureOnTabLoad(session.getProjectionId(), dataSelectionDTO);
                                tabPosition = 0;

                            } catch (Exception ex) {
                                LOGGER.error(ex);
                            }
                            dsFlag = true;
                        }
                    }.getConfirmationMessage("Update confirmation", "Data Selection values have changed. All other tabs will be updated and unsaved data will be lost. Continue?");
                    data.setReloadAfterUpdate(Boolean.FALSE);
                    data.setUpdateOnTabChange(Boolean.FALSE);
                }
            }
            if (tabPosition == data.getTabNumber()) {
                if (data.isReloadAfterUpdate()) {
                    try {

                        data.setReloadAfterUpdate(Boolean.FALSE);
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
            }

            if (tabPosition == data.getTabNumber()) {

                if (Constant.EDIT_SMALL.equals(session.getAction())) {
                }
            }

            lastPosition = tabPosition;
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    private void onTabChangeForMandated(int tabPositionForM) throws PortalException, SystemException {

        try {

            if (lastPosition == data.getTabNumber()) {
                if (data.isUpdateOnTabChange() && dsFlag) {
                    dsFlag = false;
                    tempTabPosition = tabPosition;
                    tabSheet.setSelectedTab(0);
                    new AbstractNotificationUtils() {
                        @Override
                        public void yesMethod() {
                            try {
                                if (data.isDataSelectionValid()) {
                                    data.updateDataSelection();
                                    data.updateDataSelectionProjectionTables("GOVERMENT_TABLES");
                                    dsLogic.callSalesInsertProcedure(session.getProjectionId(), session.getUserId(), session.getSessionId(), SalesUtils.MANDATED_PRO_NAME);

                                    if (session.isFromDateChanged()) {

                                        DataSelectionUtil.getForecastDTO(dataSelectionDTO, session);
                                        Object[] orderedArgs = {session.getProjectionId(), session.getMarketTypeValue(), session.getUserId(), session.getSessionId()};
                                       /// CommonLogic.callProcedureforUpdate(Constant.SUPPLEMENTAL_INSERT_PRC, orderedArgs);
                                        session.setFromDateChanged(false);
                                    }
                                    tabSheet.setSelectedTab(tempTabPosition);
                                    tabPosition = tempTabPosition;
                                    dsFlag = true;
                                    salesProjectionForMandated.init();
                                    salesProjectionResultsForMandated.resultBeanContainer.removeAllItems();
                                    discountProjectionResultsForMandated.resultBeanContainer.removeAllItems();
                                    projectionVarianceForMandated.resultBeanContainer.removeAllItems();
                                    mmdiscountProjectionResultsForMandated.resultBeanContainer.removeAllItems();
                                } else {

                                    try {
                                        Tab tabToReset = tabSheet.getTab(1);
                                        tabSheet.removeTab(tabToReset);
                                        tabSheet.addTab(salesProjectionForMandated, "Sales Projection", null, 1);
                                        tabSheet.setSelectedTab(0);
                                        data.configureOnLoading(session.getProjectionId(), dataSelectionDTO);
                                        tabPosition = 0;
                                        dsFlag = true;
                                    } catch (Exception e) {
                                        LOGGER.error(e);
                                    }

                                    AbstractNotificationUtils.getErrorNotification("Selection Criteria",
                                            "Not all required fields have been populated. Please try again.");
                                }
                            } catch (Exception ex) {
                                LOGGER.error(ex);
                            }
                        }

                        @Override
                        public void noMethod() {
                            try {
                                Tab tabToReset = tabSheet.getTab(1);
                                tabSheet.removeTab(tabToReset);
                                tabSheet.addTab(salesProjectionForMandated, "Sales Projection", null, 1);
                                tabSheet.setSelectedTab(0);
                                data.configureOnTabLoad(session.getProjectionId(), dataSelectionDTO);
                                tabPosition = 0;

                            } catch (Exception ex) {
                                LOGGER.error(ex);
                            }
                            dsFlag = true;
                        }
                    }.getConfirmationMessage("Update confirmation", "Data Selection values have changed. All other tabs will be updated and unsaved data will be lost. Continue?");
                    data.setReloadAfterUpdate(Boolean.FALSE);
                    data.setUpdateOnTabChange(Boolean.FALSE);
                }
            }
            if (tabPosition == data.getTabNumber()) {
                if (data.isReloadAfterUpdate()) {
                    try {

                        data.setReloadAfterUpdate(Boolean.FALSE);
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
            }

            if (tabPosition == data.getTabNumber()) {

                if (Constant.EDIT_SMALL.equals(session.getAction())) {
                }
            }
            if (tabPosition == 3) {
                if (validationFlag && Constant.ADD_FULL_SMALL.equalsIgnoreCase(session.getAction())) {
                    Object[] orderedArgs = {session.getProjectionId(), session.getMarketTypeValue(), session.getUserId(), session.getSessionId()};
                    CommonLogic.callProcedureforUpdate(Constant.SUPPLEMENTAL_INSERT_PRC, orderedArgs);
                    validationFlag = false;
                }
//                supplementalDiscountProjectionForMandated.load();
            }

            if (tabPosition == 4) {
                Object[] orderedArgsSupplemental = {session.getProjectionId(), session.getUserId(), session.getSessionId()};
                CommonLogic.callProcedure("PRC_M_SUPP_PROJECTION", orderedArgsSupplemental);
                if (Constant.ADD_FULL_SMALL.equalsIgnoreCase(session.getAction()) && !this.salesProjectionForMandated.isSalesCalculated()) {
                    Object[] orderedArgs = {session.getProjectionId(), session.getUserId(), "SPAP", session.getSessionId()};
                    CommonLogic.callProcedure("PRC_M_DISCOUNT_INSERT", orderedArgs);
                }
                supplementalDiscountProjectionForMandated.setDefaultAccess();
            }

            if (tabPosition == 7) {
            }

            lastPosition = tabPosition;
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * To implement tabsheet lazy loading.
     *
     * @param tabPosition current position of the tab
     */
    @Override
    protected void lazyLoadTab(final int tabPosition) {
        if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
            if (tabPosition == salesProjectionResults.getTabNumber()) {
                tabSheet.replaceComponent(salesProjectionResults, salesProjectionResults);
                salesProjectionResults.setProjectionSelection();
            } else if (tabPosition == discountProjection.getTabNumber()) {
                if (Constant.EDIT.equalsIgnoreCase(session.getAction())) {
                    new DiscountProjectionLogic().callDiscountProjectionProcedure(session);
                }
                discountProjection.getContent();
                tabSheet.replaceComponent(discountProjection, discountProjection);
            } else if (tabPosition == discountProjectionResults.getTabNumber()) {
                tabSheet.replaceComponent(discountProjectionResults, discountProjectionResults);
                discountProjectionResults.setProjectionSelection();
            } //            else if (tabPosition == ppaProjection.getTabNumber()) {
            //                tabSheet.replaceComponent(ppaProjection, ppaProjection.getContent());
            //            } 
            else if (tabPosition == ppaProjectionResults.getTabNumber()) {
                tabSheet.replaceComponent(ppaProjectionResults, ppaProjectionResults.getContent());
            } else if (tabPosition == nonmandatedprojectionResults.getTabNumber()) {
                tabSheet.replaceComponent(nonmandatedprojectionResults, nonmandatedprojectionResults);

            } else if (tabPosition == projectionVariance.getTabNumber()) {
                tabSheet.replaceComponent(projectionVariance, projectionVariance);
                projectionVariance.setProjectionSelection();
            } else if (tabPosition == 8) {
                tabSheet.replaceComponent(additionalInformation, additionalInformation);
                try {

                    additionalInformation.documentExporter();
                } catch (Exception e) {
                    LOGGER.error(e);
                }
            }
        }

    }

    public void detachListeners(TabSheet tabsheet) {
        List<TabSheet.SelectedTabChangeListener> listeners = new ArrayList<TabSheet.SelectedTabChangeListener>();

        listeners = (List<TabSheet.SelectedTabChangeListener>) tabsheet
                .getListeners(TabSheet.SelectedTabChangeEvent.class);

        for (TabSheet.SelectedTabChangeListener object : listeners) {
            tabsheet.removeSelectedTabChangeListener(object);

        }

    }

    /**
     * Logic to save the projection.
     */
    @Override
    protected void btnSaveLogic() {
        MessageBox
                .showPlain(Icon.QUESTION, "Save Confirmation", "Save record " + session.getProjectionName() + "?", new MessageBoxListener() {
                    public void buttonClicked(ButtonId buttonId) {
                        if (buttonId.name().equals(Constant.YES)) {
                            try {
                                saveProjection();
                                final Notification notif = new Notification(session.getProjectionName() + " has been successfully Saved", Notification.Type.HUMANIZED_MESSAGE);
                                notif.setPosition(Position.TOP_CENTER);
                                notif.setStyleName(ConstantsUtils.MY_STYLE);
                                notif.show(Page.getCurrent());
                                btnSave.setCaption("UPDATE");
                                session.setAction(Constant.EDIT_SMALL);
                            } catch (Exception ex) {

                                LOGGER.error(ex);
                            }
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);
    }

    /**
     * Moves to previous tab.
     */
    protected void btnPreviousLogic() {
        if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED) || screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
            int i = 1;
            while ((lastPosition - i) > 0 && !tabSheet.getTab(lastPosition - i).isVisible()) {
                i++;
            }
            tabSheet.setSelectedTab(lastPosition - i);
        } else {
            if (tabPosition != Constant.ZERO) {
                tabSheet.setSelectedTab(tabPosition - 1);
            }
        }
    }

    /**
     * Moves to next tab.
     */
    protected void btnNextLogic() {

        if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED) || screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED) || screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS)) {
            int i = 1;
            while ((lastPosition + i) < tabSheet.getComponentCount() && !tabSheet.getTab(lastPosition + i).isVisible()) {
                i++;
            }
            tabSheet.setSelectedTab(lastPosition + i);
        } else {
            tabSheet.setSelectedTab(tabPosition + 1);
        }

        UI.getCurrent().setFocusedComponent(UI.getCurrent());
    }

    /**
     * Closes the projection.
     */
    @Override
    protected void btnCloseLogic() {
        String msgTitle = "Close Confirmation";
        String msgContent = "Are you sure you want to close this Projection?";
        if ((Constant.EDIT_SMALL.equalsIgnoreCase(session.getAction()) || Constant.ADD_FULL_SMALL.equalsIgnoreCase(session.getAction()))) {
            msgTitle = "Save?";

            if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {

                msgContent = "Do you want to save the changes made to the projection < " + data.getProjectionName() + " >?";
            } else {
                msgContent = "Any unsaved information will not be saved. Do you want to proceed?";
            }
        } else {
            msgTitle = "Close Confirmation";
            msgContent = "Are you sure you want to close this Projection?";
        }

        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                if (session.getWorkflowId() != 0) {
                    Page.getCurrent().getJavaScript().execute("window.open('','_parent','');window.close(); ");
                } else {
                    if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {
                        if ((Constant.EDIT_SMALL.equalsIgnoreCase(session.getAction()) || Constant.ADD_FULL_SMALL.equalsIgnoreCase(session.getAction()))) {
                            try {
                                NonMandatedLogic logic = new NonMandatedLogic();
                                saveProjection();
                                logic.deleteTempBySession(session,screenName);

                                if (editWindow != null) {
                                    closeEditTray(editWindow);
                                    editWindow.close();
                                }
                                if (viewWindow != null) {
                                    closeViewTray(viewWindow);
                                    viewWindow.close();
                                }
                            } catch (Exception ex) {
                                LOGGER.error(ex);
                            }
                        } else {
                            checkSaveFlag(true);
                        }
                    }
                    if (!screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {
                        checkSaveFlag(true);

                    }
                }
            }

            @Override
            public void noMethod() {
                if (!screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION)) {
                    if (/*session.isSaveFlag() &&*/(Constant.EDIT_SMALL.equalsIgnoreCase(session.getAction()) || Constant.ADD_FULL_SMALL.equalsIgnoreCase(session.getAction()))) {
                        checkSaveFlag(false);
                    }
                }
            }
        }.getConfirmationMessage(msgTitle, msgContent);

    }

    private void checkSaveFlag(boolean flag) {
        final NonMandatedLogic logic = new NonMandatedLogic();
        if (!session.isProjectionSaveFlag()) {
            if (dataSelectionDTO != null && Constant.ADD_FULL_SMALL.equalsIgnoreCase(session.getAction())) {
                new AbstractNotificationUtils() {
                    @Override
                    public void yesMethod() {
                        try {
                            logic.deleteTempBySession(session, screenName);

                            logic.deleteCompleteProjection(session.getProjectionId(), screenName);
                            if (resultTable != null) {
                                resultTable.removeAllItems();
                                BeanItemContainer<DataSelectionDTO> tempContainer = new BeanItemContainer<DataSelectionDTO>(DataSelectionDTO.class);
                                resultTable.setContainerDataSource(tempContainer);
                                resultTable.setVisibleColumns(TableHeaderColumnsUtil.DATASELECTION_COLUMNS);
                                resultTable.setColumnHeaders(TableHeaderColumnsUtil.DATASELECTION_HEADERS);
                            }

                        } catch (PortalException ex) {
                            LOGGER.error(ex);
                        } catch (SystemException ex) {
                            LOGGER.error(ex);
                        } catch (Exception ex) {
                            LOGGER.error(ex);
                        }
                        if (editWindow != null) {
                            closeEditTray(editWindow);
                            editWindow.close();
                        }
                        if (viewWindow != null) {
                            closeViewTray(viewWindow);
                            viewWindow.close();
                        }

                    }

                    @Override
                    public void noMethod() {
                    }
                }.getConfirmationMessage("Close Confirmation", "Closing without saving will delete the projection permanently. Continue?");
            } else{
                if (flag) {
                if (editWindow != null) {
                    closeEditTray(editWindow);
                    editWindow.close();
                }
                if (viewWindow != null) {
                    closeViewTray(viewWindow);
                    viewWindow.close();
                }
            }
            }
        } else {
            try {
                logic.deleteTempBySession(session, screenName);
            } catch (SystemException ex) {
                LOGGER.error(ex);
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
            if (flag) {
                if (editWindow != null) {
                    closeEditTray(editWindow);
                    editWindow.close();
                }
                if (viewWindow != null) {
                    closeViewTray(viewWindow);
                    viewWindow.close();
                }
            }

        }
    }

    /**
     * Logic to save the projection.
     */
    @Override
    protected void btnSubmitLogic() {

        if ((Constant.EDIT_SMALL.equalsIgnoreCase(session.getAction()) || Constant.ADD_FULL_SMALL.equalsIgnoreCase(session.getAction()))) {
            if ((screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED))
                    || screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {

                new AbstractNotificationUtils() {
                    @Override
                    public void yesMethod() {

                        final WorkFlowNotesLookup popup = new WorkFlowNotesLookup();
                        getUI().getCurrent().addWindow(popup);
                        popup.addCloseListener(new Window.CloseListener() {
                            @Override
                            public void windowClose(Window.CloseEvent e) {
                                try {
                                    if (WorkFlowNotesLookup.submitFlag.equals("Success")) {
                                        submitProjection(popup.getNotes().getValue(), screenName, popup.getUploadedData());
                                        if ((Constant.EDIT_SMALL.equalsIgnoreCase(session.getAction()) || Constant.ADD_FULL_SMALL.equalsIgnoreCase(session.getAction())) && editWindow != null) {
                                            closeEditTray(editWindow);
                                            editWindow.close();
                                        } else if (viewWindow != null) {
                                            closeViewTray(viewWindow);
                                            viewWindow.close();
                                        }
                                        WorkFlowNotesLookup.submitFlag = ("Failed");
                                    }

                                } catch (SystemException ex) {
                                    LOGGER.error(ex);
                                } catch (PortalException ex) {
                                    LOGGER.error(ex);
                                } catch (Exception ex) {
                                    LOGGER.error(ex);
                                }
                            }
                        });
                    }

                    @Override
                    public void noMethod() {
                    }
                }.getConfirmationMessage("Confirm Submit", "Are you sure you want to submit the projection for approval?");

            } else {
                AbstractNotificationUtils.getErrorNotification("Missing Values", "The workflow cannot be submitted for approval.\n  Not all required fields have been completed.");
            }
        }

    }

    /**
     * Saves the projection.
     */
    private void saveProjection() {
        LOGGER.info("Entering SaveProjection method---->> " + session.getProjectionId());
        try {
            if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
                projectionVariance.savePvSelections();
                salesProjectionResults.saveSPResults();
                nmSalesProjection.saveSalesProjection();
                nmSalesProjection.saveSPSave();
                discountProjection.saveDiscountProjectionScreen(false);
                discountProjection.saveSelections();
                discountProjectionResults.saveButtonLogic();
                nonmandatedprojectionResults.saveProjectionResultsSelection();
//                ppaProjection.savePPAProjectionSelection();
                additionalInformation.saveNotesInformation(session.getProjectionId(), CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED);
            } else if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {
                salesProjectionForMandated.saveSalesProjection();
                salesProjectionResultsForMandated.saveSPResults();
                supplementalDiscountProjectionForMandated.saveSDP();
                discountProjectionResultsForMandated.saveDPR();
                mmdiscountProjectionResultsForMandated.saveDPR();
                mandatedprojectionResults.saveProjectionResultsSelection();
                projectionVarianceForMandated.savePvSelections();
                additionalInformation.saveNotesInformation(session.getProjectionId(), CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED);
            } else if (CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS.equals(screenName)) {
                returnsProjection.saveSPSave();
                returnsProjection.saveReturnsSave();
            }
            updateSaveFlag(session.getProjectionId());
        } catch (SystemException ex) {
            LOGGER.error(ex);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.info("Exiting SaveProjection method");
    }

    public void updateSaveFlag(final int projectionId) throws SystemException, Exception {
        ProjectionMaster projectionMaster = ProjectionMasterLocalServiceUtil.createProjectionMaster(0);
        if (!StringUtils.isEmpty(String.valueOf(projectionId)) && !Constant.NULL.equalsIgnoreCase(String.valueOf(projectionId))) {
            projectionMaster = ProjectionMasterLocalServiceUtil.getProjectionMaster(projectionId);
        }
        projectionMaster.setSaveFlag(true);
        projectionMaster.setModifiedDate(new Date());
        projectionMaster.setModifiedBy(UiUtils.parseStringToInteger(userId));
        ProjectionMasterLocalServiceUtil.updateProjectionMaster(projectionMaster);
    }

    /**
     * Submits the projection. Saves and calls the workflow
     */
    private void submitProjection(final String notes, final String screenName, final List<NotesDTO> getUploadedData) throws SystemException, Exception {

        if (Constant.EDIT_SMALL.equalsIgnoreCase(session.getAction()) || Constant.ADD_FULL_SMALL.equalsIgnoreCase(session.getAction()) || session.getWorkflowId() != 0) {
            NonMandatedLogic logic = new NonMandatedLogic();
            Map<String, Object> params = new HashMap<String, Object>();
            params.put(Constant.PROJECTION_ID, session.getProjectionId());
            saveProjection();
            if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {
                Object[] orderedArgs = {session.getProjectionId(), session.getUserId(), "SPAP", session.getSessionId()};
                CommonLogic.callProcedure("PRC_M_DISCOUNT_INSERT", orderedArgs);
            }
            if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
                pushUpdate(INDICATOR_REFRESH_UPDATE.getConstant());
            }
            List<ForecastingRulesDTO> list = DSCalculationLogic.getProjectionValues(session.getProjectionId(), session.getUserId(), session.getSessionId());
            for (ForecastingRulesDTO forecastingRulesDTO : list) {
                params.put("out_" + forecastingRulesDTO.getVariableName(), forecastingRulesDTO);
            }
            WorkflowRuleDTO dto = new WorkflowRuleDTO();
            params.put("out_workflowDTO", dto);            
            VarianceCalculationLogic.submitWorkflow(session.getUserId(), session.getProcessId(), params,"submit");
            String autoApproval = BPMProcessBean.getProcessVariableLog(session.getProcessId(), "Auto_Approval");
            String noOfUsers = BPMProcessBean.getProcessVariableLog(session.getProcessId(), "NoOfUsers");
            if(!autoApproval.isEmpty() && !noOfUsers.isEmpty()){
                LOGGER.info("autoApproval  : " + autoApproval);
                LOGGER.info("no of users : " + noOfUsers);
                String workflowId = submitToWorkflow(notes, Integer.parseInt(noOfUsers), screenName, getUploadedData);
                String approvedFlag = StringUtils.EMPTY;
                logic.deleteTempBySession(session, screenName);
                if (Constant.TRUE.equals(autoApproval)) {
                    WorkflowLogic wfLogic = new WorkflowLogic();
                    WorkflowMaster wm = wfLogic.getWorkflowMasterByProjectionId(session.getProjectionId());
                    WorkflowMasterDTO wfMasterDto = wfLogic.setWorkflowMasterDTO(session.getProjectionId(), Integer.valueOf(wm.getWorkflowMasterSid()), Integer.valueOf(session.getUserId()), WorkflowConstants.getApprovedStatus(), notes, session.getApprovalLevel());
                    workflowId = wfLogic.updateWorkflow(wfMasterDto);
                    approvedFlag = "Submitted and Approved";
                } else {
                    approvedFlag = Constant.SUBMITTED;
                }

                if (workflowId != null && !workflowId.trim().equals(CommonUtils.WORKFLOW_NOT_SAVED)) {
                    callWorkflowInboxRefresh();
                    MessageBox.showPlain(Icon.INFO, approvedFlag + " Successfully ", " Workflow Id: " + workflowId + "  ", new MessageBoxListener() {
                        /**
                         * The method is triggered when a button of the message box
                         * is pressed .
                         *
                         * @param buttonId The buttonId of the pressed button.
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            if (session.getWorkflowId() != 0) {
                                getBtnSave().setEnabled(false);
                                getBtnSubmit().setEnabled(false);
                            } else {
                                UI.getCurrent().getNavigator().navigateTo(ForecastMainView.NAME);
                            }
                        }
                    }, ButtonId.OK);

                } else {
                    NotificationUtils.getErrorNotification("Error", "The Data not saved properly");
                }
            } else {
                new AbstractNotificationUtils() {                
                    @Override
                    public void noMethod() {

                    }

                    @Override
                    public void yesMethod() {  
                        try {
                            submitProjection(notes, screenName, getUploadedData);
                        } catch (Exception ex) {
                            Logger.getLogger(ForecastForm.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                }.getConfirmationMessage("Error", "Something went wrong while submitting projection. Are you sure you want to proceed with submit?");
            }
        } else {
            NotificationUtils.getErrorNotification("Error", MessageUtils.WFP_SUBMIT_ERROR);
        }
    }

    /**
     * Called on submitting. Starts the workflow
     */
    private String submitToWorkflow(String notes, int noOfApprovals, String screenName, List<NotesDTO> getUploadedData) {
        NonMandatedLogic logic = new NonMandatedLogic();
        return logic.submitProjection(session.getProjectionId(), session.getUserId(), notes, noOfApprovals, screenName, getUploadedData, session.getDescription());
    }

    /**
     * Called on submitting. Starts the workflow
     */
    public void configureOnEnter(final int projectionId, final DataSelectionDTO dataSelectionDTO) {
        tabSheet.setSelectedTab(1);
        try {
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public NMSalesProjection getSalesProjection() {
        return nmSalesProjection;
    }

    public SessionDTO getSessions() {
        return session;
    }

    private void configureForView() {

        if (Constant.VIEW.equalsIgnoreCase(session.getAction())) {
            super.getBtnSave().setEnabled(false);
            super.getBtnSubmit().setEnabled(false);
        }

    }

    @Override
    protected void btnRefreshLogic() {

        saveProjection();
    }

    public void pushUpdate(String indicator) throws SystemException, SQLException {
        if (INDICATOR_REFRESH_UPDATE.getConstant().equals(indicator)) {
            try {
                SalesProjectionLogic logic = new SalesProjectionLogic();
                Object[] inputs = new Object[4];
                inputs[0] = session.getProjectionId();
                inputs[1] = session.getUserId();
                inputs[2] = session.getSessionId();
                logic.callSalesInsert(inputs);
                PPAProjectionLogic.nonMandatedPPAProjectionInsert(session.getProjectionId(), Integer.valueOf(session.getUserId()), Integer.valueOf(session.getSessionId()));
                new DiscountProjectionLogic().callDiscountActualsInsertProcedure(session.getProjectionId(), session.getUserId(), session.getSessionId());
                pushMap.put(INDICATOR_REFRESH_UPDATE.getConstant(), Boolean.TRUE);
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
        if (INDICATOR_TIME_PERIOD_CHANGED.getConstant().equals(indicator)) {
            try {

                pushMap.put(INDICATOR_TIME_PERIOD_CHANGED.getConstant(), Boolean.TRUE);
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
    }

    private void push(int tabPosition) {

        if (pushMap.get(INDICATOR_REFRESH_UPDATE.getConstant()) != null && pushMap.get(INDICATOR_REFRESH_UPDATE.getConstant())) {
            if (tabPosition == nmSalesProjection.getTabNumber()) {
            }
            if (tabPosition == salesProjectionResults.getTabNumber() && tabLazyLoadMap.get(salesProjectionResults.getTabNumber())) {
                salesProjectionResults.pushUpdate(INDICATOR_REFRESH_UPDATE.getConstant());
            }

            if (tabPosition == discountProjectionResults.getTabNumber() && tabLazyLoadMap.get(discountProjectionResults.getTabNumber())) {
                discountProjectionResults.pushUpdate(INDICATOR_REFRESH_UPDATE.getConstant());
            }
//            if (tabPosition == ppaProjection.getTabNumber() && tabLazyLoadMap.get(ppaProjection.getTabNumber())) {
//                ppaProjection.pushUpdate(INDICATOR_REFRESH_UPDATE.getConstant());
//            }
            if (tabPosition == ppaProjectionResults.getTabNumber() && tabLazyLoadMap.get(ppaProjectionResults.getTabNumber())) {
                ppaProjectionResults.pushUpdate(INDICATOR_REFRESH_UPDATE.getConstant());
            }

            if (tabPosition == projectionVariance.getTabNumber() && tabLazyLoadMap.get(projectionVariance.getTabNumber())) {
                projectionVariance.pushUpdate(INDICATOR_REFRESH_UPDATE.getConstant());
            }
            pushMap.put(INDICATOR_REFRESH_UPDATE.getConstant(), Boolean.FALSE);
        }

        if (pushMap.get(INDICATOR_TIME_PERIOD_CHANGED.getConstant()) != null && pushMap.get(INDICATOR_TIME_PERIOD_CHANGED.getConstant())) {
            if (tabPosition == nmSalesProjection.getTabNumber()) {
            }
            if (tabPosition == salesProjectionResults.getTabNumber() && tabLazyLoadMap.get(salesProjectionResults.getTabNumber())) {
                salesProjectionResults.pushUpdate(INDICATOR_TIME_PERIOD_CHANGED.getConstant());
            }

            if (tabPosition == discountProjectionResults.getTabNumber() && tabLazyLoadMap.get(discountProjectionResults.getTabNumber())) {
                discountProjectionResults.pushUpdate(INDICATOR_TIME_PERIOD_CHANGED.getConstant());
            }
            if (tabPosition == ppaProjection.getTabNumber() && tabLazyLoadMap.get(ppaProjection.getTabNumber())) {
                ppaProjection.pushUpdate(INDICATOR_TIME_PERIOD_CHANGED.getConstant());
            }
            if (tabPosition == ppaProjectionResults.getTabNumber() && tabLazyLoadMap.get(ppaProjectionResults.getTabNumber())) {
                ppaProjectionResults.pushUpdate(INDICATOR_TIME_PERIOD_CHANGED.getConstant());
            }

            if (tabPosition == projectionVariance.getTabNumber() && tabLazyLoadMap.get(projectionVariance.getTabNumber())) {
                projectionVariance.pushUpdate(INDICATOR_TIME_PERIOD_CHANGED.getConstant());
            }
            pushMap.put(INDICATOR_TIME_PERIOD_CHANGED.getConstant(), Boolean.FALSE);
        }

    }

    public void loadMarketTypeValue() {
        String definedOrUDValue = StringUtils.EMPTY;
        String definedValue = dsLogic.getDefinedValue(dataSelectionDTO.getCustomerHierSid());
        if ("User Defined".equalsIgnoreCase(definedValue)) {
            definedOrUDValue = session.getMarketType();
        } else {
            definedOrUDValue = dsLogic.getHelperValue(String.valueOf(session.getProjectionId()));
        }
    }

    public NMDiscountProjection getDiscountProjection() {
        return discountProjection;
    }

    /**
     * Tab Security
     *
     * @throws Exception
     */
    private void setTabSecurity() throws Exception {
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constant.USER_ID));
        if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {
            Map<String, AppPermission> functionPsHM = stplSecurity.getBusinessTabPermission(userId, getGovernmentConstant());
            if (functionPsHM.get(FunctionNameUtil.DATA_TAB) != null && !((AppPermission) functionPsHM.get(FunctionNameUtil.DATA_TAB)).isTabFlag()) {
                tabSheet.getTab(0).setVisible(Boolean.FALSE);
            }
            if (functionPsHM.get(FunctionNameUtil.M_SP_TAB) != null && !((AppPermission) functionPsHM.get(FunctionNameUtil.M_SP_TAB)).isTabFlag()) {
                tabSheet.getTab(1).setVisible(Boolean.FALSE);
            }
            if (functionPsHM.get(FunctionNameUtil.M_SPR_TAB) != null && !((AppPermission) functionPsHM.get(FunctionNameUtil.M_SPR_TAB)).isTabFlag()) {
                tabSheet.getTab(2).setVisible(Boolean.FALSE);
            }
            if (functionPsHM.get(FunctionNameUtil.M_SDP_TAB) != null && !((AppPermission) functionPsHM.get(FunctionNameUtil.M_SDP_TAB)).isTabFlag()) {
                tabSheet.getTab(3).setVisible(Boolean.FALSE);
            }
            if (functionPsHM.get(FunctionNameUtil.M_SDPR_TAB) != null && !((AppPermission) functionPsHM.get(FunctionNameUtil.M_SDPR_TAB)).isTabFlag()) {
                tabSheet.getTab(4).setVisible(Boolean.FALSE);
            }
            if (functionPsHM.get(FunctionNameUtil.M_PR_TAB) != null && !((AppPermission) functionPsHM.get(FunctionNameUtil.M_PR_TAB)).isTabFlag()) {
                tabSheet.getTab(5).setVisible(Boolean.FALSE);
            }
            if (functionPsHM.get(FunctionNameUtil.M_PV_TAB) != null && !((AppPermission) functionPsHM.get(FunctionNameUtil.M_PV_TAB)).isTabFlag()) {
                tabSheet.getTab(6).setVisible(Boolean.FALSE);
            }
            if (functionPsHM.get(FunctionNameUtil.M_ADDITIONAL_TAB) != null && !((AppPermission) functionPsHM.get(FunctionNameUtil.M_ADDITIONAL_TAB)).isTabFlag()) {
                tabSheet.getTab(7).setVisible(Boolean.FALSE);
            }

        } else if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
            Map<String, AppPermission> functionPsHM = stplSecurity.getBusinessTabPermission(userId, getCommercialConstant());
            if (functionPsHM.get(FunctionNameUtil.NM_DATA_TAB) != null && !((AppPermission) functionPsHM.get(FunctionNameUtil.NM_DATA_TAB)).isTabFlag()) {
                tabSheet.getTab(0).setVisible(Boolean.FALSE);
            }
            if (functionPsHM.get(FunctionNameUtil.NM_SP_TAB) != null && !((AppPermission) functionPsHM.get(FunctionNameUtil.NM_SP_TAB)).isTabFlag()) {
                tabSheet.getTab(1).setVisible(Boolean.FALSE);
            }
            if (functionPsHM.get(FunctionNameUtil.NM_SPR_TAB) != null && !((AppPermission) functionPsHM.get(FunctionNameUtil.NM_SPR_TAB)).isTabFlag()) {
                tabSheet.getTab(2).setVisible(Boolean.FALSE);
            }
            if (functionPsHM.get(FunctionNameUtil.NM_SDP_TAB) != null && !((AppPermission) functionPsHM.get(FunctionNameUtil.NM_SDP_TAB)).isTabFlag()) {
                tabSheet.getTab(3).setVisible(Boolean.FALSE);
            }
            if (functionPsHM.get(FunctionNameUtil.NM_SDPR_TAB) != null && !((AppPermission) functionPsHM.get(FunctionNameUtil.NM_SDPR_TAB)).isTabFlag()) {
                tabSheet.getTab(4).setVisible(Boolean.FALSE);
            }
//            if (functionPsHM.get(FunctionNameUtil.NM_PPA_TAB) != null && !((AppPermission) functionPsHM.get(FunctionNameUtil.NM_PPA_TAB)).isTabFlag()) {
//                tabSheet.getTab(5).setVisible(Boolean.FALSE);
//            }
            if (functionPsHM.get(FunctionNameUtil.NM_PPA_RESULTS_TAB) != null && !((AppPermission) functionPsHM.get(FunctionNameUtil.NM_PPA_RESULTS_TAB)).isTabFlag()) {
                tabSheet.getTab(5).setVisible(Boolean.FALSE);
            }
            if (functionPsHM.get(FunctionNameUtil.NM_PR_TAB) != null && !((AppPermission) functionPsHM.get(FunctionNameUtil.NM_PR_TAB)).isTabFlag()) {
                tabSheet.getTab(6).setVisible(Boolean.FALSE);
            }
            if (functionPsHM.get(FunctionNameUtil.NM_PV_TAB) != null && !((AppPermission) functionPsHM.get(FunctionNameUtil.NM_PV_TAB)).isTabFlag()) {
                tabSheet.getTab(7).setVisible(Boolean.FALSE);
            }
            if (functionPsHM.get(FunctionNameUtil.NM_ADDITIONAL_TAB) != null && !((AppPermission) functionPsHM.get(FunctionNameUtil.NM_ADDITIONAL_TAB)).isTabFlag()) {
                tabSheet.getTab(8).setVisible(Boolean.FALSE);
            }
        }
    }

    /**
     * Tab Security
     *
     * @throws Exception
     */
    private void setButtonSecurity() throws Exception {
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constant.USER_ID));
        Map<String, AppPermission> functionPsHM = stplSecurity.getBusinessFunctionPermission(userId, (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED) ? getCommercialConstant() : screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED) ? getGovernmentConstant() : CommonUtils.BUSINESS_PROCESS_TYPE_CHANNELS) + "," + "Common");
        if (functionPsHM.get("btnSave") != null && !((AppPermission) functionPsHM.get("btnSave")).isFunctionFlag()) {
            getBtnSave().setVisible(Boolean.FALSE);
        }
        if (functionPsHM.get("btnSubmit") != null && !((AppPermission) functionPsHM.get("btnSubmit")).isFunctionFlag()) {
            getBtnSubmit().setVisible(Boolean.FALSE);
        }
    }

    /**
     * Used to close the Edit tray
     *
     * @param editWindow
     */
    public void closeEditTray(ForecastEditWindow editWindow) {
        MinimizeTray tray = editWindow.getMinimizeTray();
        if (tray.getWindowItems().size() == 1) {
            tray.close();
        }
    }

    /**
     * Used to close the View tray
     *
     * @param viewWindow
     */
    private void closeViewTray(NonMandatedViewWindow viewWindow) {
        MinimizeTray tray = viewWindow.getMinimizeTray();
        if (tray.getWindowItems().size() == 1) {
            tray.close();
        }
    }

    /**
     * Method checks for the actual sales and displays the alert message. If Yes
     * selected, All the Trading Partners / Customers will be available in the
     * projection. If No selected, Trading Partners / Customers with no actuals
     * will be removed from the projection.
     *
     * @throws PortalException
     * @throws Exception
     */
    private void checkForActualSales() throws PortalException, Exception {

        if (logic.checkForZeroActuals(session.getProjectionId())) {
            new AbstractNotificationUtils() {
                @Override
                public void yesMethod() {
                    try {
                        callInsertProcedureOnGenerate(session, screenName);
                        init();
                        addContent();
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }

                @Override
                public void noMethod() {
                    try {
                        logic.removeTPOrCustomerFromProjection(session.getProjectionId());
                        callInsertProcedureOnGenerate(session, screenName);
                        init();
                        addContent();
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
            }.getConfirmationMessage(CONFIRMATION.getConstant(), alertMsg.getString("F_" + screenName.replaceAll("\\s", StringUtils.EMPTY).toUpperCase() + "_ACT_CHECK_MSG"));
        } else {
            callInsertProcedureOnGenerate(session, screenName);
            init();
            addContent();
        }
    }

    /**
     * Method calls the insert procedure while generating a projection based on
     * the forecast module.
     */
    private void callInsertProcedureOnGenerate(final SessionDTO session, final String screenName) {

        dsLogic.callSalesInsertProcedure(session.getProjectionId(), session.getUserId(), session.getSessionId(), screenName.equals(Constants.BUSINESS_PROCESS_TYPE_NONMANDATED) ? SalesUtils.NM_SALES_PRO_NAME : SalesUtils.MANDATED_PRO_NAME);
    }

    @Override
    protected void btnApproveLogic() {

        MessageBox.showPlain(Icon.QUESTION, "Confirm Approve", "Are you sure you want to approve the projection " + " ?", new MessageBoxListener() {
            public void buttonClicked(ButtonId buttonId) {
                if (buttonId.name().equals(Constant.YES)) {

                    final WorkFlowNotesLookup popup = new WorkFlowNotesLookup();
                    getUI().getCurrent().addWindow(popup);
                    popup.addCloseListener(new Window.CloseListener() {
                        @Override
                        public void windowClose(Window.CloseEvent e) {
                            try {
                                int projectionId = session.getProjectionId();
                                String userId = session.getUserId();
                                int userIdInt = Integer.parseInt(userId);
                                int workflowId = session.getWorkflowId();
                                WorkflowLogic wfLogic = new WorkflowLogic();
                                String workflowIdUpdate = StringUtils.EMPTY;
                                WorkflowMasterDTO wfMasterDto = new WorkflowMasterDTO();
                                wfMasterDto = wfLogic.setWorkflowMasterDTO(projectionId, workflowId, userIdInt, WorkflowConstants.getApprovedStatus(), popup.getNotes().getValue(), session.getApprovalLevel());
                                workflowIdUpdate = wfLogic.updateWorkflow(wfMasterDto);
                                if (session.getNoOfApproval() > session.getApprovalLevel()) {
                                    wfMasterDto = wfLogic.setWorkflowMasterDTO(projectionId, workflowId, userIdInt, WorkflowConstants.getPendingStatus(), StringUtils.EMPTY, session.getApprovalLevel() + 1);
                                    workflowIdUpdate = wfLogic.updateWorkflow(wfMasterDto);
                                }
                                if (workflowIdUpdate != null && !workflowIdUpdate.trim().equals(CommonUtils.WORKFLOW_NOT_SAVED)) {

                                    Map<String, Object> params = new HashMap<String, Object>();
                                    params.put("approveFlag", "approve");
                                    VarianceCalculationLogic.submitWorkflow(session.getUserId(), session.getProcessId(), params,"approve");

                                    callWorkflowInboxRefresh();
                                    AbstractNotificationUtils.getInfoNotification("Approved Information", "Workflow Id " + workflowIdUpdate + " approved successfully");
                                    StringBuffer sb = new StringBuffer("Hi,<br /><br />");
                                    sb.append("The workflow with workflow Id " + workflowIdUpdate + " is Approved Succesfully.");
                                    sb.append("<br /><br />Thanks,<br />BPI Technical Team");
                                    MailWorkItemHandler.sendMail("support@bpitechnologies.com", "Workflow Approved Succesfully", sb);
                                    getBtnApprove().setEnabled(false);
                                    getBtnWithdraw().setEnabled(false);
                                    getBtnCancel().setEnabled(false);
                                    getBtnReject().setEnabled(false);
                                } else {
                                    CommonUIUtils.getMessageNotification("The projection not approved properly");
                                }
                            } catch (Exception ex) {
                                LOGGER.error(ex);
                            }
                        }
                    });

                }
            }
        }, ButtonId.YES, ButtonId.NO);

    }

    @Override
    protected void btnRejectLogic() {
        MessageBox.showPlain(Icon.QUESTION, "Confirm Reject", "Are you sure you want to reject the projection " + " ?", new MessageBoxListener() {
            public void buttonClicked(ButtonId buttonId) {
                if (buttonId.name().equals(Constant.YES)) {
                    final WorkFlowNotesLookup popup = new WorkFlowNotesLookup();
                    getUI().getCurrent().addWindow(popup);
                    popup.addCloseListener(new Window.CloseListener() {
                        @Override
                        public void windowClose(Window.CloseEvent e) {
                            try {
                                int projectionId = session.getProjectionId();
                                String userId = session.getUserId();
                                int userIdInt = Integer.parseInt(userId);
                                int workflowId = session.getWorkflowId();
                                WorkflowLogic wfLogic = new WorkflowLogic();
                                WorkflowMasterDTO wfMasterDto = wfLogic.setWorkflowMasterDTO(projectionId, workflowId, userIdInt, WorkflowConstants.getRejectedStatus(), popup.getNotes().getValue(), session.getApprovalLevel());
                                String workflowIdUpdate = wfLogic.updateWorkflow(wfMasterDto);
                                if (workflowIdUpdate != null && !workflowIdUpdate.trim().equals(CommonUtils.WORKFLOW_NOT_SAVED)) {
                                    Map<String, Object> params = new HashMap<String, Object>();
                                    params.put("approveFlag", "reject-RWC");
                                    VarianceCalculationLogic.submitWorkflow(session.getUserId(), session.getProcessId(), params,"reject");
                                    callWorkflowInboxRefresh();
                                    AbstractNotificationUtils.getInfoNotification("Rejected Information ", "Workflow Id " + workflowIdUpdate + " rejected successfully");
                                    StringBuffer sb = new StringBuffer("Hi,<br /><br />");
                                    sb.append("The workflow with workflow Id " + workflowIdUpdate + " is Rejected Succesfully.");
                                    sb.append("<br /><br />Thanks,<br />BPI Technical Team");
                                    MailWorkItemHandler.sendMail("support@bpitechnologies.com", "Workflow Rejected Succesfully", sb);
                                    getBtnApprove().setEnabled(false);
                                    getBtnWithdraw().setEnabled(false);
                                    getBtnCancel().setEnabled(false);
                                    getBtnReject().setEnabled(false);
                                } else {
                                    CommonUIUtils.getMessageNotification("The projection not rejected properly");
                                }
                            } catch (Exception ex) {
                                LOGGER.error(ex);
                            }
                        }
                    });
                }
            }
        }, ButtonId.YES, ButtonId.NO);

    }

    @Override
    protected void btnWithdrawLogic() {

        MessageBox.showPlain(Icon.QUESTION, "Confirm Withdraw", "Are you sure you want to withdraw the projection " + " ?", new MessageBoxListener() {
            public void buttonClicked(ButtonId buttonId) {
                if (buttonId.name().equals(Constant.YES)) {
                    final WorkFlowNotesLookup popup = new WorkFlowNotesLookup();
                    getUI().getCurrent().addWindow(popup);
                    popup.addCloseListener(new Window.CloseListener() {
                        @Override
                        public void windowClose(Window.CloseEvent e) {
                            try {
                                int projectionId = session.getProjectionId();
                                String userId = session.getUserId();
                                int userIdInt = Integer.parseInt(userId);
                                int workflowId = session.getWorkflowId();
                                WorkflowLogic wfLogic = new WorkflowLogic();
                                WorkflowMasterDTO wfMasterDto = wfLogic.setWorkflowMasterDTO(projectionId, workflowId, userIdInt, WorkflowConstants.getWithdrawnStatus(), popup.getNotes().getValue(), session.getApprovalLevel());
                                String workflowIdUpdate = wfLogic.updateWorkflow(wfMasterDto);
                                if (workflowIdUpdate != null && !workflowIdUpdate.trim().equals(CommonUtils.WORKFLOW_NOT_SAVED)) {
                                    Map<String, Object> params = new HashMap<String, Object>();
                                    params.put("approveFlag", "withdraw-RWC");
                                    VarianceCalculationLogic.submitWorkflow(session.getUserId(), session.getProcessId(), params,"withdraw");

                                    callWorkflowInboxRefresh();
                                    AbstractNotificationUtils.getInfoNotification("Workflow withdrawn ", "Workflow Id " + workflowIdUpdate + " withdrawn successfully");

                                    StringBuffer sb = new StringBuffer("Hi,<br /><br />");
                                    sb.append("The workflow with workflow Id " + workflowIdUpdate + " is Withdrawn Succesfully.");
                                    sb.append("<br /><br />Thanks,<br />BPI Technical Team");
                                    MailWorkItemHandler.sendMail("support@bpitechnologies.com", "Workflow Withdrawn Succesfully", sb);
                                    getBtnApprove().setEnabled(false);
                                    getBtnWithdraw().setEnabled(false);
                                    getBtnCancel().setEnabled(false);
                                    getBtnReject().setEnabled(false);
                                } else {
                                    CommonUIUtils.getMessageNotification("The projection not withdrawn properly");
                                }
                            } catch (Exception ex) {
                                LOGGER.error(ex);
                            }
                        }
                    });
                }
            }
        }, ButtonId.YES, ButtonId.NO);

    }

    @Override
    protected void btnCancelLogic() {
        MessageBox.showPlain(Icon.QUESTION, "Confirm Cancel", "Are you sure you want to cancel the projection " + " ?", new MessageBoxListener() {
            public void buttonClicked(ButtonId buttonId) {
                if (buttonId.name().equals(Constant.YES)) {
                    final WorkFlowNotesLookup popup = new WorkFlowNotesLookup();
                    getUI().getCurrent().addWindow(popup);
                    popup.addCloseListener(new Window.CloseListener() {
                        @Override
                        public void windowClose(Window.CloseEvent e) {
                            try {
                                int projectionId = session.getProjectionId();
                                String userId = session.getUserId();
                                int userIdInt = Integer.parseInt(userId);
                                int workflowId = session.getWorkflowId();
                                WorkflowLogic wfLogic = new WorkflowLogic();
                                WorkflowMasterDTO wfMasterDto = wfLogic.setWorkflowMasterDTO(projectionId, workflowId, userIdInt, WorkflowConstants.getCancelledStatus(), popup.getNotes().getValue(), session.getApprovalLevel());
                                String workflowIdUpdate = wfLogic.updateWorkflow(wfMasterDto);
                                if (workflowIdUpdate != null && !workflowIdUpdate.trim().equals(CommonUtils.WORKFLOW_NOT_SAVED)) {

                                    Map<String, Object> params = new HashMap<String, Object>();
                                    params.put("approveFlag", "cancel-RWC");
                                    VarianceCalculationLogic.submitWorkflow(session.getUserId(), session.getProcessId(), params,"cancel");
                                    callWorkflowInboxRefresh();
                                    AbstractNotificationUtils.getInfoNotification("Cancel Information", "Workflow Id " + workflowIdUpdate + " cancelled successfully");

                                    StringBuffer sb = new StringBuffer("Hi,<br /><br />");
                                    sb.append("The workflow with workflow Id " + workflowIdUpdate + " is cancelled Succesfully.");
                                    sb.append("<br /><br />Thanks,<br />BPI Technical Team");
                                    MailWorkItemHandler.sendMail("support@bpitechnologies.com", "Workflow Cancelled Succesfully", sb);
                                    getBtnApprove().setEnabled(false);
                                    getBtnWithdraw().setEnabled(false);
                                    getBtnCancel().setEnabled(false);
                                    getBtnReject().setEnabled(false);
                                } else {
                                    CommonUIUtils.getMessageNotification("The projection not cancelled properly");
                                }
                            } catch (Exception ex) {
                                LOGGER.error(ex);
                            }
                        }
                    });
                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }

    private void callWorkflowInboxRefresh() {
        JavaScript.getCurrent().execute("localStorage.setItem('"+WorkflowConstants.getBusinessProcessNameForecast()+"', 'true');");
    }
}
