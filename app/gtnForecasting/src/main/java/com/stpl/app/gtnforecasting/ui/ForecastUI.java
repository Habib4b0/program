package com.stpl.app.gtnforecasting.ui;

import static com.stpl.ifs.util.constants.GlobalConstants.getAccrualConstant;
import static com.stpl.ifs.util.constants.GlobalConstants.getCommercialConstant;
import static com.stpl.ifs.util.constants.GlobalConstants.getGovernmentConstant;
import static com.stpl.ifs.util.constants.GlobalConstants.getReturnsConstant;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.portlet.PortletConfig;
import javax.portlet.PortletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.vaadin.alump.beforeunload.BeforeUnload;

import com.stpl.app.gtnforecasting.accrualrateprojection.logic.DSLogic;
import com.stpl.app.gtnforecasting.accrualrateprojection.ui.view.AccrualRateProjectionView;
import com.stpl.app.gtnforecasting.bpm.persistance.WorkflowPersistance;
import com.stpl.app.gtnforecasting.bpm.service.BPMManagerBean;
import com.stpl.app.gtnforecasting.dao.DataSelectionDAO;
import com.stpl.app.gtnforecasting.dao.impl.DataSelectionDAOImpl;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.logic.DataSelectionLogic;
import com.stpl.app.gtnforecasting.logic.NonMandatedLogic;
import com.stpl.app.gtnforecasting.logic.RelationShipFilterLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.sessionutils.SessionUtil;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.DataSelectionUtil;
import com.stpl.app.gtnforecasting.utils.HelperListUtil;
import com.stpl.app.model.ProjectionMaster;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.utils.QueryUtils;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.util.constants.WorkflowConstants;
import com.stpl.portal.kernel.util.JavaConstants;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;

import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;

// TODO: Auto-generated Javadoc
/**
 * UI class of Non-Mandated.
 *
 * @author soundarrajan
 * @version 1.0
 */
public class ForecastUI extends UI {

    /**
     * Navigator to navigate through screens *.
     */
    private Navigator navigator;

    protected String pageParameters = null;
    protected final StplSecurity stplSecurity = new StplSecurity();
    protected DataSelectionDAO dataSelectionDao = new DataSelectionDAOImpl();
    protected SessionDTO sessionDto = new SessionDTO();
    private final RelationShipFilterLogic relationLogic = RelationShipFilterLogic.getInstance();
    private List<Leveldto> productHierarchyLevelDefinitionList = Collections.emptyList();
    private List<Leveldto> customerHierarchyLevelDefinitionList = Collections.emptyList();
    protected final NonMandatedLogic nmLogic = new NonMandatedLogic();
    /**
     * Logger
     */
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(ForecastUI.class);

    public static boolean EXCEL_CLOSE = false;

    /**
     * This method is used to register the navigations for different views.
     *
	 * @param request
	 *            the request
     */
    @Override
    protected void init(VaadinRequest request) {
        addStyleName("bootstrap bootstrap-ui bootstrap-forecast bootstrap-nm");
        final String userId = request.getRemoteUser();


        beforeUnloadCloseUi();
        PortletConfig portletConfig = (PortletConfig) request.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
        PortletRequest portletRequest = (PortletRequest) request.getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);
        CommonUtils.setPortletSession(portletRequest.getPortletSession());
        CommonUtils.setPortalConfig(portletConfig);
        VaadinSession.getCurrent().setAttribute(Constant.PORTLET_NAME, portletConfig.getPortletName());
        String screenName = (String) VaadinSession.getCurrent().getAttribute(Constant.PORTLET_NAME);
        final String sessionId = (String) request.getWrappedSession().getId();
        VaadinSession.getCurrent().setAttribute(Constant.SESSION_ID, sessionId);
        VaadinSession.getCurrent().setAttribute(Constant.USER_ID, userId);
        sessionDto.setUserId(userId);
        sessionDto.setSessionId(sessionId);
        LOGGER.info("USER_ID: " + userId);
        LOGGER.info("SESSION_ID: " + sessionId);
        try {
            Collection<Object> userGroupId = stplSecurity.getUserGroupId(Long.parseLong(userId));
            VaadinSession.getCurrent().setAttribute("businessRoleIds", stplSecurity.getBusinessRoleIds(userGroupId));
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

        try {
            DataSelectionUtil.mapUsers();
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        pageParameters = Page.getCurrent().getLocation().getQuery();
        String projectionId = null;
        String workflowId = null;
        String workflowStatus = null;
        String userType = null;
        String noOfApprovals = null;
        String approvalLevels = null;
        ForecastEditWindow editWindow = null;
        AccrualRateProjectionView arpView = null;
        String customerHierSid = StringUtils.EMPTY;
        String customerHierarchyLevel = StringUtils.EMPTY;
        String custRelationshipBuilderSid = StringUtils.EMPTY;
        String productHierarchyLevel = StringUtils.EMPTY;
        String prodRelationshipBuilderSid = StringUtils.EMPTY;
        String productHierSid = StringUtils.EMPTY;
        if (pageParameters != null) {

            String[] parameters = pageParameters.split("&");

            HashMap<String, String> hm = new HashMap<>();

            for (String para : parameters) {
                String[] paraStr = para.split(Constant.EQUAL);
                hm.put(paraStr[0], paraStr[1]);
            }

            projectionId = hm.get("projectionIdFromWorkflow");
            workflowId = hm.get("workflowId");
            workflowStatus = hm.get("workflowStatus");
            userType = hm.get("userType");
            noOfApprovals = hm.get("noOfApprovals");
            approvalLevels = hm.get("approvalLevel");
            if (getGovernmentConstant().equalsIgnoreCase(hm.get(Constant.PORTLET_NAME_PROPERTY))) {
                screenName = CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED;
                CommonLogic.setScreenName(screenName);
            } else if (getCommercialConstant().equalsIgnoreCase(hm.get(Constant.PORTLET_NAME_PROPERTY))) {
                screenName = CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED;
                customerHierSid = hm.get("customerHierSid");
                customerHierarchyLevel = hm.get("customerHierarchyLevel");
                custRelationshipBuilderSid = hm.get("custRelationshipBuilderSid");
                productHierarchyLevel = hm.get("productHierarchyLevel");
                prodRelationshipBuilderSid = hm.get("prodRelationshipBuilderSid");
                productHierSid = hm.get("productHierSid");
            } else if (getAccrualConstant().equalsIgnoreCase(hm.get(Constant.PORTLET_NAME_PROPERTY))) {
                screenName = CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION;
            }
            sessionDto = SessionUtil.createSession();
            List list = WorkflowPersistance.selectWFInstanceInfo(Integer.valueOf(projectionId));
            Long processId = 0L;
            if (list != null && !list.isEmpty()) {
                processId = Long.valueOf(list.get(0).toString());
            }

            sessionDto.setProcessId(processId);
            sessionDto.setProjectionId(Integer.valueOf(projectionId));
            RelationShipFilterLogic logic = RelationShipFilterLogic.getInstance();
            ProjectionMaster temp = null;
            String projectionName = StringUtils.EMPTY;
            try {
                temp = dataSelectionDao.getProjectionMaster(Integer.valueOf(projectionId));
                if (!getReturnsConstant().equalsIgnoreCase(hm.get(Constant.PORTLET_NAME_PROPERTY))) {
                    int hierarchySid = Integer.valueOf(temp.getCustomerHierarchySid());
                    sessionDto.setCustomerDescription(
                            logic.getLevelValueMap(temp.getCustRelationshipBuilderSid(), hierarchySid,
                                    temp.getCustomerHierVersionNo(), temp.getProjectionCustVersionNo()));
                }
                int hierarchySid = Integer.valueOf(temp.getProductHierarchySid());
                sessionDto.setProductDescription(
                        logic.getLevelValueMap(temp.getProdRelationshipBuilderSid(), hierarchySid,
                                temp.getProductHierVersionNo(), temp.getProjectionProdVersionNo()));
                projectionName = temp.getProjectionName();
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
            sessionDto.setWorkflowId(Integer.valueOf(workflowId));
            sessionDto.setWorkflowStatus(workflowStatus);
            sessionDto.setWorkflowUserType(userType);
            sessionDto.setUserId(userId);
            sessionDto.setNoOfApproval(Integer.parseInt(noOfApprovals));
            sessionDto.setApprovalLevel(Integer.parseInt(approvalLevels));
            try {
                if (!WorkflowConstants.getWithdrawnStatus().equals(workflowStatus)
                        && !WorkflowConstants.getRejectedStatus().equals(workflowStatus)) {
                    sessionDto.setAction(Constant.VIEW);
                } else {
                    sessionDto.setAction(Constant.EDIT_SMALL);
                }
                if (!CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equalsIgnoreCase(screenName)) {
                    if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
                        DataSelectionLogic dsLogic = new DataSelectionLogic();
                        DataSelectionDTO dto = new DataSelectionDTO();
                        dto.setCustomerHierSid(customerHierSid);
                        dto.setCustomerHierarchyLevel(customerHierarchyLevel);
                        dto.setCustRelationshipBuilderSid(custRelationshipBuilderSid);
                        dto.setProductHierarchyLevel(productHierarchyLevel);
                        dto.setProdRelationshipBuilderSid(prodRelationshipBuilderSid);
                        dto.setProdHierSid(productHierSid);
                        dto.setProjectionId(Integer.parseInt(projectionId));
                        dto.setDeductionLevel(StringUtils.EMPTY);
                        dto.setCustomerHierVersionNo(temp.getCustomerHierVersionNo());
                        dto.setProductHierVersionNo(temp.getProductHierVersionNo());
                        dto.setProductRelationShipVersionNo(temp.getProjectionProdVersionNo());
                        dto.setCustomerRelationShipVersionNo(temp.getProjectionCustVersionNo());
                        sessionDto.setCustomerHierarchyVersion(dto.getCustomerHierVersionNo());
                        sessionDto.setProductHierarchyVersion(dto.getProductHierVersionNo());
                        sessionDto.setCustomerRelationVersion(dto.getCustomerRelationShipVersionNo());
                        sessionDto.setProductRelationVersion(dto.getProductRelationShipVersionNo());
                        sessionDto.setScreenName(screenName);
                        sessionDto.setProductRelationId(Integer.valueOf(dto.getProdRelationshipBuilderSid()));
                        sessionDto.setProductLevelNumber(dto.getProductHierarchyLevel());
                        QueryUtils.createTempTables(sessionDto);

                        Map<String, String> tempCustomerDescriptionMap;
                        Map<String, String> tempProductDescriptionMap;
                                                int custHierarchyVersionNo = temp!=null ? temp.getCustomerHierVersionNo() : 0;
                        tempCustomerDescriptionMap = relationLogic.getLevelValueMap(dto.getCustRelationshipBuilderSid(),
                                Integer.parseInt(dto.getCustomerHierSid()), custHierarchyVersionNo,
                                dto.getCustomerRelationShipVersionNo());
                                                int prodHierarchyVersionNo = temp!=null ? temp.getProductHierVersionNo() : 0;   
                        tempProductDescriptionMap = relationLogic.getLevelValueMap(dto.getProdRelationshipBuilderSid(),
                                Integer.parseInt(dto.getProdHierSid()), prodHierarchyVersionNo,
                                dto.getProductRelationShipVersionNo());
                        int customerSelectedLevel = Integer.parseInt(customerHierarchyLevel);
                        int productSelectedLeve = Integer.parseInt(productHierarchyLevel);
                        List<Leveldto> customerItemIds = relationLogic.getRelationShipValues(dto.getProjectionId(),
                                Boolean.TRUE, customerSelectedLevel, tempCustomerDescriptionMap);
                        List<Leveldto> productItemIds = relationLogic.getRelationShipValues(dto.getProjectionId(),
                                Boolean.FALSE, productSelectedLeve, tempProductDescriptionMap);

                        customerHierarchyLevelDefinitionList = relationLogic
                                .getHierarchyLevelDefinition(Integer.parseInt(dto.getCustomerHierSid()), custHierarchyVersionNo);
                        productHierarchyLevelDefinitionList = relationLogic
                                .getHierarchyLevelDefinition(Integer.parseInt(dto.getProdHierSid()), prodHierarchyVersionNo);

                        relationLogic.ccpHierarchyInsert(sessionDto.getCurrentTableNames(), customerItemIds,
                                productItemIds, customerHierarchyLevelDefinitionList,
                                productHierarchyLevelDefinitionList, dto.getCustomerRelationShipVersionNo(),
                                dto.getProductRelationShipVersionNo());
                        sessionDto.setCustomerLevelDetails(
                                dsLogic.getLevelValueDetails(sessionDto, dto.getCustRelationshipBuilderSid(), true));
                        sessionDto.setProductLevelDetails(
                                dsLogic.getLevelValueDetails(sessionDto, dto.getProdRelationshipBuilderSid(), false));
                        Object[] obj = nmLogic.deductionRelationBuilderId(dto.getProdRelationshipBuilderSid());
                        sessionDto.setDedRelationshipBuilderSid(obj[0].toString());
                    }
                    editWindow = new ForecastEditWindow(projectionName, sessionDto, null, screenName, null);
                } else if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equalsIgnoreCase(screenName)) {
                    DSLogic dSLogic = new DSLogic();
                    Object[] obj = DataSelectionLogic.getAccrualSelection(Integer.parseInt(projectionId)).get(0);
                    sessionDto.setDeductionLevel(String.valueOf(obj[0]));
                    sessionDto.setDeductionValue(String.valueOf(obj[1]));
                    if (sessionDto.getCurrentTableNames().isEmpty()
                            && !sessionDto.getAction().equalsIgnoreCase(Constant.VIEW)) {
                        sessionDto.setScreenName(screenName);
                        QueryUtils.createTempTables(sessionDto);
                    }
                    if (Constant.EDIT_SMALL.equalsIgnoreCase(sessionDto.getAction())) {
                        sessionDto.setIsFileNotChanged(DSLogic.getFileStatus(sessionDto.getProjectionId()));
                        if (!sessionDto.isFileNotChanged()) {
                            MessageBox.showPlain(Icon.QUESTION, "Alert",
                                    "A new Customer Gross Trade Sales file has been activated since this workflow was last saved. Would you like this workflow to be updated based on the new active file?",
                                    new MessageBoxListener() {
                                @SuppressWarnings("PMD")
                                @Override
                                public void buttonClicked(final ButtonId buttonId) {
                                    if (buttonId.name().equals(Constant.YES)) {
                                        sessionDto.setIsNewFileCalculationNeeded(true);
                                    } else {
                                        sessionDto.setIsNewFileCalculationNeeded(false);
                                    }
                                }
                            }, ButtonId.YES, ButtonId.NO);
                        } else {
                            sessionDto.setIsNewFileCalculationNeeded(false);
                        }
                    } else {
                        sessionDto.setIsFileNotChanged(true);
                        sessionDto.setIsNewFileCalculationNeeded(false);
                    }
                    if (!Constant.VIEW.equalsIgnoreCase(sessionDto.getAction())) {
                        dSLogic.insertAccrualProjTemp(sessionDto);
                        dSLogic.insertAccrualTemp(sessionDto);
                    }
                    arpView = new AccrualRateProjectionView(projectionId, sessionDto, screenName, null, true);

                }
            } catch (Exception ex) {
                Logger.getLogger(ForecastUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        navigator = new Navigator(this, this);
        HelperListUtil.getInstance().loadValuesWithListName(getCommercialConstant());
        try {

            if (editWindow != null) {
                navigator.addView(ForecastWorkflowView.NAME, new ForecastWorkflowView(editWindow));
            }
            if (arpView != null) {
                navigator.addView(AccrualRateProjectionView.ARP_VIEW, arpView);
            }
            ExecutorService serviec = Executors.newSingleThreadExecutor();
            serviec.submit(new BPMJob());
            if (projectionId != null
                    && !CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equalsIgnoreCase(screenName)) {
				getUI().getNavigator().navigateTo(ForecastWorkflowView.NAME + "/" + pageParameters);
            } else if (projectionId != null
                    && CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equalsIgnoreCase(screenName)) {
                getUI().getNavigator().navigateTo(AccrualRateProjectionView.ARP_VIEW + "/" + pageParameters);
            } else {
                ForecastMainView view = new ForecastMainView();
                navigator.addView(ForecastMainView.NAME, view);
                navigator.setErrorView(view);
            }
        } catch (Exception ex) {
            Logger.getLogger(ForecastUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Configure the error handler for the UI
        
           UI.getCurrent().setErrorHandler(new DefaultErrorHandler() {
            @Override
            public void error(com.vaadin.server.ErrorEvent event) {
                // Find the final cause
                String cause = "The Exception occured because of: ";
                for (Throwable t = event.getThrowable(); t != null; t = t.getCause()) {
                    if (t.getCause() == null) // We're at final cause
                    {
                        cause += t.getClass().getName();

    }
        
                    LOGGER.error(t.getMessage());
                }

                LOGGER.error(cause);
                // Do the default error handling (optional)
            }
        });
        

    }

    class BPMJob implements Runnable {

        @Override
        public void run() {
            LOGGER.info("Intailizing BPM Engine:");
            BPMManagerBean.getEngine().getRuntimeEngine().getKieSession();
        }

    }

    /**
     * Used to invalidate the session
     *
     */
    public static void makeSessionInValidate() {
        if (EXCEL_CLOSE) { // Fix to avoid blank page issue while excel export
            EXCEL_CLOSE = false;
        } else {
            UI.getCurrent().close();
        }
    }

    /**
     * Used to close the session in browser closer listener
     *
     */
    private void beforeUnloadCloseUi() {
        BeforeUnload ob = BeforeUnload.closeBeforeUnload(this);
        ob.addUnloadListener(new BeforeUnload.UnloadListener() {
            @Override
            public void unload(BeforeUnload.UnloadEvent event) {
                makeSessionInValidate();
            }
        });
    }

}
