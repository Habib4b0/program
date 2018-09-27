package com.stpl.app.gtnforecasting.ui;

import static com.stpl.ifs.util.constants.GlobalConstants.getAccrualConstant;
import static com.stpl.ifs.util.constants.GlobalConstants.getCommercialConstant;
import static com.stpl.ifs.util.constants.GlobalConstants.getReturnsConstant;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletConfig;
import javax.portlet.PortletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.alump.beforeunload.BeforeUnload;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.JavaConstants;
import com.stpl.app.gtnforecasting.accrualrateprojection.logic.DSLogic;
import com.stpl.app.gtnforecasting.accrualrateprojection.ui.view.AccrualRateProjectionView;
import com.stpl.app.gtnforecasting.bpm.persistance.WorkflowPersistance;
import com.stpl.app.gtnforecasting.dao.DataSelectionDAO;
import com.stpl.app.gtnforecasting.dao.impl.DataSelectionDAOImpl;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.logic.DataSelectionLogic;
import com.stpl.app.gtnforecasting.logic.NonMandatedLogic;
import com.stpl.app.gtnforecasting.logic.RelationShipFilterLogic;
import com.stpl.app.gtnforecasting.projectionvariance.logic.NMProjectionVarianceLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.sessionutils.SessionUtil;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.DataSelectionUtil;
import com.stpl.app.gtnforecasting.utils.HelperListUtil;
import com.stpl.app.model.ProjectionMaster;
import com.stpl.app.security.StplSecurity;
import static com.stpl.app.utils.Constants.LabelConstants.TAB_DISCOUNT_PROJECTION;
import com.stpl.app.utils.QueryUtils;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.stpl.ifs.util.constants.WorkflowConstants;
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

/**
 * UI class of Non-Mandated.
 *
 * @author soundarrajan
 * @version 1.0
 */

public class ForecastUI extends UI {

    protected String pageParameters = null;
    protected final StplSecurity stplSecurity = new StplSecurity();
    protected DataSelectionDAO dataSelectionDao = new DataSelectionDAOImpl();
    protected SessionDTO sessionDto = new SessionDTO();
    private final RelationShipFilterLogic relationLogic = RelationShipFilterLogic.getInstance();
    protected final NonMandatedLogic nmLogic = new NonMandatedLogic();
    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ForecastUI.class);

    private static boolean EXCEL_CLOSE = false;

    public ForecastUI() {
        super();
    }

    /**
     * This method is used to register the navigations for different views.
     *
     * @param request the request
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
        LOGGER.info("USER_ID= {} " , userId);
        LOGGER.info("SESSION_ID= {} " , sessionId);
        try {
            Collection<Object> userGroupId = stplSecurity.getUserGroupId(Long.parseLong(userId));
            VaadinSession.getCurrent().setAttribute("businessRoleIds", stplSecurity.getBusinessRoleIds(userGroupId));
        } catch (PortalException | SystemException | NumberFormatException ex) {
            LOGGER.error(ex.getMessage());
        }

        try {
            DataSelectionUtil.mapUsers();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
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

            try {
                
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
                    if (getCommercialConstant().equalsIgnoreCase(hm.get(Constant.PORTLET_NAME_PROPERTY))) {
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
                List list = WorkflowPersistance.selectWFInstanceInfo(Integer.parseInt(projectionId));
                Long processId = 0L;
                if (list != null && !list.isEmpty()) {
                    processId = Long.valueOf(list.get(0).toString());
                }
                
                sessionDto.setProcessId(processId);
                sessionDto.setProjectionId(Integer.parseInt(projectionId));
                RelationShipFilterLogic logic = RelationShipFilterLogic.getInstance();
                ProjectionMaster temp = dataSelectionDao.getProjectionMaster(Integer.parseInt(projectionId));
                if (!getReturnsConstant().equalsIgnoreCase(hm.get(Constant.PORTLET_NAME_PROPERTY))) {
                    int hierarchySid = Integer.parseInt(temp.getCustomerHierarchySid());
                    sessionDto.setCustomerDescription(
                            logic.getLevelValueMap(temp.getCustRelationshipBuilderSid(), hierarchySid,
                                    temp.getCustomerHierVersionNo(), temp.getProjectionCustVersionNo()));
                }
                int hierarchySid = Integer.parseInt(temp.getProductHierarchySid());
                sessionDto.setProductDescription(
                        logic.getLevelValueMap(temp.getProdRelationshipBuilderSid(), hierarchySid,
                                temp.getProductHierVersionNo(), temp.getProjectionProdVersionNo()));
                String projectionName = temp.getProjectionName();
                sessionDto.setWorkflowId(Integer.parseInt(workflowId));
                sessionDto.setWorkflowStatus(workflowStatus);
                sessionDto.setWorkflowUserType(userType);
                sessionDto.setUserId(userId);
                sessionDto.setNoOfApproval(Integer.parseInt(noOfApprovals));
                sessionDto.setApprovalLevel(Integer.parseInt(approvalLevels));
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
                            Map<Object, Object> map = new NMProjectionVarianceLogic().getNMProjectionSelection(Integer.parseInt(projectionId), TAB_DISCOUNT_PROJECTION.getConstant());
                            Object mapValue = map.get("frequency");
                            Object deductionValue = map.get("DeductionLevel");
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
                            sessionDto.setDataSelectionDeductionLevel(String.valueOf(deductionValue));
                            sessionDto.setDataSelectionDeductionLevelCaption(getDeductionCaptionWithSid(deductionValue,sessionDto));
                            sessionDto.setProductRelationVersion(dto.getProductRelationShipVersionNo());
                            sessionDto.setScreenName(screenName);
                            sessionDto.setProductRelationId(Integer.parseInt(dto.getProdRelationshipBuilderSid()));
                            sessionDto.setProductLevelNumber(dto.getProductHierarchyLevel());
                            sessionDto.setFunctionMode("E");
                            sessionDto.setCustomRelationShipSid(dto.getCustomRelationShipSid());
                            sessionDto.setDsFrequency(mapValue.toString());
                            QueryUtils.createTempTables(sessionDto);
                            
                            Map<String, String> tempCustomerDescriptionMap;
                            Map<String, String> tempProductDescriptionMap;
                            int custHierarchyVersionNo = temp.getCustomerHierVersionNo();
                            tempCustomerDescriptionMap = relationLogic.getLevelValueMap(dto.getCustRelationshipBuilderSid(),
                                    Integer.parseInt(dto.getCustomerHierSid()), custHierarchyVersionNo,
                                    dto.getCustomerRelationShipVersionNo());
                            int prodHierarchyVersionNo = temp.getProductHierVersionNo();
                            tempProductDescriptionMap = relationLogic.getLevelValueMap(dto.getProdRelationshipBuilderSid(),
                                    Integer.parseInt(dto.getProdHierSid()), prodHierarchyVersionNo,
                                    dto.getProductRelationShipVersionNo());
                            int customerSelectedLevel = Integer.parseInt(customerHierarchyLevel);
                            int productSelectedLeve = Integer.parseInt(productHierarchyLevel);
                            List<Leveldto> customerItemIds = relationLogic.getRelationShipValues(dto.getProjectionId(),
                                    BooleanConstant.getTrueFlag(), customerSelectedLevel, tempCustomerDescriptionMap);
                            List<Leveldto> productItemIds = relationLogic.getRelationShipValues(dto.getProjectionId(),
                                    BooleanConstant.getFalseFlag(), productSelectedLeve, tempProductDescriptionMap);
                            relationLogic.ccpHierarchyInsert(sessionDto.getCurrentTableNames(), customerItemIds,
                                    productItemIds, dto);
                            sessionDto.setCustomerLevelDetails(
                                    dsLogic.getLevelValueDetails(sessionDto, dto.getCustRelationshipBuilderSid(), true));
                            sessionDto.setProductLevelDetails(
                                    dsLogic.getLevelValueDetails(sessionDto, dto.getProdRelationshipBuilderSid(), false));
                            Object[] obj = nmLogic.deductionRelationBuilderId(dto.getProdRelationshipBuilderSid());
                            sessionDto.setDedRelationshipBuilderSid(obj[0].toString());
                            List<Integer> versionNoList = nmLogic.getDeductionVersionNoList(sessionDto.getDedRelationshipBuilderSid());
                            if (versionNoList != null && !versionNoList.isEmpty()) {
                            sessionDto.setDeductionRelationVersion((int) versionNoList.get(0));
                            }
                            List<Object[]> dataList=new CommonLogic().getCustomViewSids(Integer.parseInt(projectionId));
                            
                            sessionDto.setCustomRelationShipSid(dataList!=null && !dataList.isEmpty()&&  dataList.get(0)[0]!=null? Integer.parseInt(String.valueOf(dataList.get(0)[0])) : 0);
                            sessionDto.setCustomDeductionRelationShipSid(dataList!=null && !dataList.isEmpty() &&dataList.get(0)[1]!=null ? Integer.parseInt(String.valueOf(dataList.get(0)[1])) : 0);
                            sessionDto.setSalesHierarchyLevelDetails(dsLogic.getRelationshipDetailsCustom(sessionDto, String.valueOf(sessionDto.getCustomRelationShipSid())));
                            sessionDto.setDiscountCustomerProductLevelDetails(dsLogic.getRelationshipDetailsCustom(sessionDto, String.valueOf(sessionDto.getCustomDeductionRelationShipSid()))); 
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
                LOGGER.error(ex.getMessage());
            }
        }

        Navigator navigator = new Navigator(this, this);
        HelperListUtil.getInstance().loadValuesWithListName(getCommercialConstant());
        try {

            if (editWindow != null) {
                navigator.addView(ForecastWorkflowView.NAME, new ForecastWorkflowView(editWindow));
            }
            if (arpView != null) {
                navigator.addView(AccrualRateProjectionView.ARP_VIEW, arpView);
            }
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
            LoggerFactory.getLogger(ForecastUI.class.getName()).error(StringUtils.EMPTY, ex);
        }

        UI.getCurrent().setErrorHandler(new DefaultErrorHandler() {
            @Override
            public void error(com.vaadin.server.ErrorEvent event) {
                LOGGER.error("Error catched in UI ", event.getThrowable());
            }
        });
        
    }

    /**
     * Used to invalidate the session
     *
     */
    public static void makeSessionInValidate() {
        if (isEXCELCLOSE()) { // Fix to avoid blank page issue while excel export
            setEXCELCLOSE(false);
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

	public static boolean isEXCELCLOSE() {
		return EXCEL_CLOSE;
	}

	public static void setEXCELCLOSE(boolean excelClose) {
		EXCEL_CLOSE = excelClose;
	}

    private String getDeductionCaptionWithSid(Object deductionValue,SessionDTO session) {
        Map<String,String> levelCaption=new HashMap<>();
        List<String[]> deductionLevel = CommonLogic.getDeductionLevel(session.getProjectionId());
        for (Object[] strings : deductionLevel) {
            levelCaption.put(String.valueOf(strings[0]), String.valueOf(strings[1]));
        }
     return levelCaption.get(String.valueOf(deductionValue));
    }

}
