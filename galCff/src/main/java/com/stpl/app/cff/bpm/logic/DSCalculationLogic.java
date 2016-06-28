package com.stpl.app.cff.bpm.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.model.TaskSummary;

import com.stpl.app.bpm.dto.ForecastingRulesDTO;
import com.stpl.app.bpm.utils.DroolsProperties;
import com.stpl.app.cff.bpm.persistance.WorkflowPersistance;
import com.stpl.app.cff.bpm.service.BPMProcessBean;
import com.stpl.portal.model.Role;
import com.stpl.portal.model.User;
import com.stpl.portal.service.RoleLocalServiceUtil;

public class DSCalculationLogic {

	/**
	 * The Constant LOGGER.
	 */
	private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(DSCalculationLogic.class);
	private static final Properties properties = DroolsProperties.getPropertiesData();

	


	public static boolean isValidWorkflowUser(User userModel, List<String> roleList, long processIntanceId) {
		boolean returnflag = false;
		TaskSummary taskSummary = null;
		try {

                    LOGGER.info("userName :" + userModel.getScreenName());
			taskSummary = BPMProcessBean.getAvailableTask(processIntanceId, userModel.getScreenName());
                        if(taskSummary == null){
//                           String notiMsg = "Something went wrong while submitting this projection. Please submit again";
//                           NotificationUtils.getAlertNotification("Error", notiMsg);                           
                           LOGGER.info("taskSummary id:" + taskSummary.getId());                        
                           return true;
                        }
                        LOGGER.info("taskSummary :" + taskSummary.getName());
			LOGGER.info("taskSummary :" + taskSummary.getId());
			List<String> userRoles = BPMProcessBean.getPotentialOwners(taskSummary.getId(), roleList);
			LOGGER.info("userRoles :" + userRoles);
			List<Role> roles = RoleLocalServiceUtil.getUserRoles(userModel.getUserId());
			if (userRoles == null || userRoles.isEmpty()) {
				return returnflag;
			}
			for (Role role : roles) {
				if (userRoles.contains(role.getName())) {
					returnflag = true;
					break;
				}
			}
		} catch (Exception e) {
                    LOGGER.error(e.getMessage());
		}

		return returnflag;
	}

	

	public static ProcessInstance startWorkflow() {
		ProcessInstance processInstance = null;
		try {
			String workflowId = properties.getProperty("CFF_WorkflowId", "CFFWorkflow.CFFWorkflow");
			processInstance = BPMProcessBean.startProcess(workflowId, null);
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return processInstance;
	}

	public static TaskSummary startAndCompleteTask(User userModel, int projectionId, long processInstanceId) {
		TaskSummary taskSummary = null;
		try {
			LOGGER.info("userId :" + userModel.getUserId());
			LOGGER.info("userName :" + userModel.getScreenName());
			taskSummary = BPMProcessBean.getAvailableTask(processInstanceId, userModel.getScreenName());
			LOGGER.info("taskSummary :" + taskSummary.getName());
			LOGGER.info("taskSummary :" + taskSummary.getId());
			BPMProcessBean.startTask(taskSummary.getId(), userModel.getScreenName());
			BPMProcessBean.completeTask(taskSummary.getId(), userModel.getScreenName(), null);
			WorkflowPersistance.insertWFInstanceInfo(projectionId, processInstanceId);
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return taskSummary;
	}

	public static List<ForecastingRulesDTO> getProjectionValues(int projectionId, String userId, String sessionId) {
		List<ForecastingRulesDTO> list = new ArrayList<ForecastingRulesDTO>();
		try {
			List<Object[]> returnList = WorkflowPersistance.getProjectionRecords(projectionId, userId, sessionId);

			for (int i = 0; i < returnList.size(); i++) {
				Object[] obj = returnList.get(i);
				ForecastingRulesDTO sales = new ForecastingRulesDTO("Projected_Contract_Sales_Dollars");
				sales.setAmountLowest(Double.valueOf(String.valueOf(obj[0])));
				sales.setAmountGreatest(Double.valueOf(String.valueOf(obj[1])));
				sales.setPercentLowest(Double.valueOf(String.valueOf(obj[12])));
				sales.setPercentGreatest(Double.valueOf(String.valueOf(obj[13])));

				ForecastingRulesDTO units = new ForecastingRulesDTO("Projected_Contract_Sales_Units");
				units.setAmountLowest(Double.valueOf(String.valueOf(obj[2])));
				units.setAmountGreatest(Double.valueOf(String.valueOf(obj[3])));
				units.setPercentLowest(Double.valueOf(String.valueOf(obj[14])));
				units.setPercentGreatest(Double.valueOf(String.valueOf(obj[15])));

				ForecastingRulesDTO discount = new ForecastingRulesDTO("Projected_Discount_Dollars");
				discount.setAmountLowest(Double.valueOf(String.valueOf(obj[4])));
				discount.setAmountGreatest(Double.valueOf(String.valueOf(obj[5])));
				discount.setPercentLowest(Double.valueOf(String.valueOf(obj[16])));
				discount.setPercentGreatest(Double.valueOf(String.valueOf(obj[17])));

				ForecastingRulesDTO rate = new ForecastingRulesDTO("Projected_Discount_Rate");
				rate.setAmountLowest(Double.valueOf(String.valueOf(obj[6])));
				rate.setAmountGreatest(Double.valueOf(String.valueOf(obj[7])));
				rate.setPercentLowest(Double.valueOf(String.valueOf(obj[18])));
				rate.setPercentGreatest(Double.valueOf(String.valueOf(obj[19])));

				ForecastingRulesDTO netSales = new ForecastingRulesDTO("Net_Sales");
				netSales.setAmountLowest(Double.valueOf(String.valueOf(obj[8])));
				netSales.setAmountGreatest(Double.valueOf(String.valueOf(obj[9])));
				netSales.setPercentLowest(Double.valueOf(String.valueOf(obj[20])));
				netSales.setPercentGreatest(Double.valueOf(String.valueOf(obj[21])));

                                ForecastingRulesDTO netProfit = new ForecastingRulesDTO("Net_Profit");
                                netProfit.setAmountLowest(Double.valueOf(String.valueOf(obj[10])));
				netProfit.setAmountGreatest(Double.valueOf(String.valueOf(obj[11])));
				netProfit.setPercentLowest(Double.valueOf(String.valueOf(obj[22])));
				netProfit.setPercentGreatest(Double.valueOf(String.valueOf(obj[23]))); 
                                
				list.add(sales);
				list.add(units);
				list.add(discount);
				list.add(rate);
				list.add(netSales);
                                list.add(netProfit);
			}
		} catch (Exception e) {
                    LOGGER.error(e.getMessage());
		}
		return list;
	}
        
        public static List<ForecastingRulesDTO> getProjectionValuesForAccrual(int projectionId, String userId, String sessionId) {
		List<ForecastingRulesDTO> list = new ArrayList<ForecastingRulesDTO>();
		try {
			List<Object[]> returnList = WorkflowPersistance.getProjectionRecordsForAccrual(projectionId, userId, sessionId);

			for (int i = 0; i < returnList.size(); i++) {
				Object[] obj = returnList.get(i);
				ForecastingRulesDTO accrualRate = new ForecastingRulesDTO("Accrual_Rate_Projection");
				accrualRate.setAmountLowest(Double.valueOf(String.valueOf(obj[2])));
				accrualRate.setAmountGreatest(Double.valueOf(String.valueOf(obj[3])));
				accrualRate.setPercentLowest(Double.valueOf(String.valueOf(obj[6])));
				accrualRate.setPercentGreatest(Double.valueOf(String.valueOf(obj[7])));
                                
				list.add(accrualRate);
			}
		} catch (Exception e) {
                    LOGGER.error(e.getMessage());
		}
		return list;
	}
}
