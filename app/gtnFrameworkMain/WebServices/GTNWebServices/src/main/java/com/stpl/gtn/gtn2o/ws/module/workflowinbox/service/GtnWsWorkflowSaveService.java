package com.stpl.gtn.gtn2o.ws.module.workflowinbox.service;

import java.util.Date;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.ws.config.GtnWsAllListConfig;
import com.stpl.gtn.gtn2o.ws.entity.HelperTable;
import com.stpl.gtn.gtn2o.ws.entity.workflow.WorkflowInbox;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.filemanagement.constants.GtnWsFileManagementConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.workflow.bean.GtnWsWorkflowInboxBean;

@Service()
@Scope(value = "singleton")

public class GtnWsWorkflowSaveService {
	public GtnWsWorkflowSaveService() {
		/**
		 * empty constructor
		 */
	}

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsWorkflowSaveService.class);

	@Autowired
	private GtnWsAllListConfig gtnWebServiceAllListConfig;

	public int saveWorkflowQuery(GtnWsWorkflowInboxBean bean, GtnUIFrameworkWebserviceRequest generalWSRequest) {

		Session session = gtnWebServiceAllListConfig.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			WorkflowInbox masterData = generateSaveProfileModel(bean, generalWSRequest.getGtnWsGeneralRequest(),
					session);
			Integer workflowSystemId = (Integer) session.save(masterData);
			tx.commit();
			return workflowSystemId;
		} catch (Exception e) {
			logger.error(e.getMessage());
			tx.rollback();

		} finally {
			session.close();
		}
		return 0;
	}

	private WorkflowInbox generateSaveProfileModel(GtnWsWorkflowInboxBean masterbean,
			GtnWsGeneralRequest generalWSRequest, Session session) {

		int userId = Integer.parseInt(generalWSRequest.getUserId().trim());
		WorkflowInbox workflowModel = new WorkflowInbox();
		try {
			workflowModel.setBrandId(masterbean.getBrandIdArm());
			workflowModel.setAdjustmentType(String.valueOf(masterbean.getAdjustmentType()));
			workflowModel.setCreatedBy(userId);
			Map<Integer, String> map = gtnWebServiceAllListConfig.getUserIdNameMap();
			String createdBy = getKeyFromValue(map, masterbean.getCreatedByPrivate());
			String approvedBy = getKeyFromValue(map, masterbean.getApprovedBy());
			if (createdBy != null) {
				workflowModel.setWfCreatedBy(Integer.valueOf(createdBy));
			}
			if (approvedBy != null) {
				workflowModel.setApprovedBy(Integer.valueOf(approvedBy));
			}
			workflowModel.setCreatedDate(new Date());
			workflowModel.setModifiedBy(userId);
			workflowModel.setModifiedDate(new Date());
			workflowModel.setApprovedBy(userId);
			setWorkFlowInboxModel(workflowModel, masterbean, session);

		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

		return workflowModel;
	}

	private HelperTable getHelperTable(Integer systemId, Session session) {
		return session.load(HelperTable.class, systemId == null ? 0 : systemId);
	}

	public static String getKeyFromValue(Map<Integer, String> hm, String value) {
		for (Map.Entry<Integer, String> object : hm.entrySet()) {
			String[] valueArray = value.trim().split(" ");
			if (valueArray.length > 1 && object.getValue().contains(valueArray[0])
					&& object.getValue().contains(valueArray[1])) {
				return String.valueOf(object.getKey());
			}
		}
		return null;
	}

	public void updateSaveProfileQuery(GtnUIFrameworkWebserviceRequest generalWSRequest,
			GtnWsWorkflowInboxBean masterbean) throws GtnFrameworkGeneralException {

		Session session = gtnWebServiceAllListConfig.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		try {

			WorkflowInbox workflowModel = session.get(WorkflowInbox.class, masterbean.getWorkflowSid());

			workflowModel.setBrandId(masterbean.getBrandIdArm());
			workflowModel.setAdjustmentType(String.valueOf(masterbean.getAdjustmentType()));
			workflowModel.setCreatedBy(Integer.parseInt(generalWSRequest.getGtnWsGeneralRequest().getUserId()));
			Map<Integer, String> map = gtnWebServiceAllListConfig.getUserIdNameMap();
			String createdBy = getKeyFromValue(map, masterbean.getCreatedByPrivate());
			String approvedBy = getKeyFromValue(map, masterbean.getApprovedBy());
			if (createdBy != null) {
				workflowModel.setWfCreatedBy(Integer.valueOf(createdBy));
			}
			if (approvedBy != null) {
				workflowModel.setApprovedBy(Integer.valueOf(approvedBy));
			}
			workflowModel.setCreatedDate(new Date());
			workflowModel.setModifiedBy(Integer.valueOf(generalWSRequest.getGtnWsGeneralRequest().getUserId()));
			workflowModel.setModifiedDate(new Date());
			workflowModel.setApprovedBy(Integer.valueOf(generalWSRequest.getGtnWsGeneralRequest().getUserId()));
			setWorkFlowInboxModel(workflowModel, masterbean, session);
			session.saveOrUpdate(workflowModel);
			tx.commit();

		} catch (Exception e) {
			logger.error(e.getMessage());
			tx.rollback();
			throw new GtnFrameworkGeneralException("Exception in updateCfpQuery ", e);
		} finally {
			session.close();
		}

	}

	private void setWorkFlowInboxModel(WorkflowInbox workflowModel, GtnWsWorkflowInboxBean masterbean,
			Session session) {
		workflowModel.setApprovedDateFrom(masterbean.getApprovedFrom());
		workflowModel.setApprovedDateTo(masterbean.getApprovedTo());
		workflowModel.setBrandName(masterbean.getBrandNameArm());
		workflowModel.setBusinessUnit(masterbean.getBusinessUnitARM());
		workflowModel.setCompanyId(masterbean.getCompanyID());
		workflowModel.setCompanyName(masterbean.getCompanyName());
		workflowModel.setCompanyNo(masterbean.getCompanyNo());
		workflowModel.setContractType(Integer.valueOf(String.valueOf(masterbean.getContractType())));
		workflowModel.setCreationFromDate(masterbean.getCreatedFrom());
		workflowModel.setCreationToDate(masterbean.getCreatedTo());
		workflowModel.setDeductionName(masterbean.getDeductionNameArm());
		workflowModel.setDeductionNo(masterbean.getDeductionNoArm());
		workflowModel.setGlDate(masterbean.getGlDateArm());
		workflowModel.setViewName(masterbean.getViewName());
		workflowModel.setViewType(masterbean.getViewType());
		workflowModel.setWorkflowDescription(masterbean.getWorkflowDesc());
		workflowModel.setWorkflowId(masterbean.getWorkflowId());
		workflowModel.setWorkflowName(masterbean.getWorkflowName());
		workflowModel.setWorkflowStatus(masterbean.getWorkflowStatusArm());
		workflowModel.setBusinessProcess(
				getHelperTable(Integer.valueOf(masterbean.getBusinessProcess()), session).getDescription());
		if ("Returns"
				.equals(getHelperTable(Integer.valueOf(masterbean.getBusinessProcess()), session).getDescription())) {
			workflowModel.setBusinessunitid(masterbean.getBusinessUnitIdReturns());
			workflowModel.setBusinessunitno(masterbean.getBusinessUnitNoReturns());
			workflowModel.setBusinessunitname(masterbean.getBusinessUnitNameReturns());
			workflowModel.setItemId(masterbean.getItemIdReturns());
			workflowModel.setItemName(masterbean.getItemNameReturns());
			workflowModel.setItemNo(masterbean.getItemNoReturns());
		}
		if (getHelperTable(Integer.valueOf(masterbean.getBusinessProcess()), session).getDescription()
				.equals(GtnWsFileManagementConstants.FORECASTING)
				|| getHelperTable(Integer.valueOf(masterbean.getBusinessProcess()), session).getDescription()
						.equals(GtnWsFileManagementConstants.ACCRUAL_RATE_PROJECTION)) {
			workflowModel.setBusinessunitid(masterbean.getBusinessUnitId());
			workflowModel.setBusinessunitno(masterbean.getBusinessUnitNo());
			workflowModel.setBusinessunitname(masterbean.getBusinessUnitName());
			workflowModel.setItemId(masterbean.getItemId());
			workflowModel.setItemName(masterbean.getItemName());
			workflowModel.setItemNo(masterbean.getItemNo());
		}
		if ("ARM".equals(getHelperTable(Integer.valueOf(masterbean.getBusinessProcess()), session).getDescription())) {
			workflowModel.setContractId(masterbean.getContractIdArm());
			workflowModel.setContractNo(masterbean.getContractNoArm());
			workflowModel.setContractName(masterbean.getContractNameArm());
			workflowModel.setItemName(masterbean.getItemNameArm());
			workflowModel.setItemNo(masterbean.getItemNoArm());
			workflowModel.setCompanyNo(masterbean.getCustomerNoArm());
			workflowModel.setContractName(masterbean.getCustomerNameArm());
			workflowModel.setCompanyName(String.valueOf(masterbean.getCompanyARM()).isEmpty() ? "0"
					: String.valueOf(masterbean.getCompanyARM()));
		} else {
			workflowModel.setContractId(masterbean.getContractId());
			workflowModel.setContractNo(masterbean.getContractNo());
			workflowModel.setContractName(masterbean.getContractName());
		}
		if (getHelperTable(Integer.valueOf(masterbean.getBusinessProcess()), session).getDescription()
				.equals(GtnWsFileManagementConstants.FORECASTING)
				|| getHelperTable(Integer.valueOf(masterbean.getBusinessProcess()), session).getDescription()
						.equals(GtnWsFileManagementConstants.ACCRUAL_RATE_PROJECTION)) {
			workflowModel.setDeductionLevel(String.valueOf(masterbean.getForecastdeductionLevel()));
			workflowModel.setDeductionValue(Integer.valueOf(String.valueOf(masterbean.getForecastdeductionValue())));
		} else {
			workflowModel.setDeductionLevel(String.valueOf(masterbean.getDeductionLevelArm()));
			workflowModel.setDeductionValue(Integer.valueOf(String.valueOf(masterbean.getDeductionValueArm())));
		}
	}

}
