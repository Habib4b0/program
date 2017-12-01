package com.stpl.app.gtnforecasting.bpm.logic;

import com.liferay.portal.kernel.model.Role;
import com.stpl.app.bpm.dto.ForecastingRulesDTO;
import com.stpl.app.gtnforecasting.bpm.persistance.WorkflowPersistance;
import com.stpl.app.gtnforecasting.bpm.service.BPMProcessBean;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.stpl.ifs.util.DroolsProperties;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.model.TaskSummary;

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

			LOGGER.debug("userName :" + userModel.getScreenName());
			taskSummary = BPMProcessBean.getAvailableTask(processIntanceId);
                        if(taskSummary == null){
                           LOGGER.debug("taskSummary id:" + taskSummary.getId());                        
                           return true;
                        }

			LOGGER.debug("taskSummary : " + taskSummary.getName());
			List<String> userRoles = BPMProcessBean.getPotentialOwners(taskSummary.getId(), roleList);
			LOGGER.debug("userRoles :" + userRoles);
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
                    LOGGER.error(e);
		}

		return returnflag;
	}

	

	public static ProcessInstance startWorkflow() {
		ProcessInstance processInstance = null;
		try {
			String workflowId = properties.getProperty("Forecasting_WorkflowId", "ForecastingWorkflow.SubmissionWorkflow");
			processInstance = BPMProcessBean.startProcess(workflowId, null);
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return processInstance;
	}
        
        public static ProcessInstance startARPWorkflow() {
		ProcessInstance processInstance = null;
		try {
			String workflowId = properties.getProperty("ARP_WorkflowId", "ARPWorkflow.ARPWorkflow");
			processInstance = BPMProcessBean.startProcess(workflowId, null);
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return processInstance;
	}

	public static TaskSummary startAndCompleteTask(User userModel, int projectionId, long processInstanceId) {
		TaskSummary taskSummary = null;
		try {
			LOGGER.debug("userId :" + userModel.getUserId());
			LOGGER.debug("userName :" + userModel.getScreenName());
			taskSummary = BPMProcessBean.getAvailableTask(processInstanceId);
			LOGGER.debug("taskSummary :" + taskSummary.getName());
			LOGGER.debug("taskSummary :" + taskSummary.getId());
			BPMProcessBean.startTask(taskSummary.getId(), userModel.getScreenName());
			BPMProcessBean.completeTask(taskSummary.getId(), userModel.getScreenName(), null);
			WorkflowPersistance.insertWFInstanceInfo(projectionId, processInstanceId);
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return taskSummary;
	}

    public static List<ForecastingRulesDTO> getProjectionValues(int projectionId, String userId, String sessionId, String screenName,SessionDTO sessionDto) {
        List<ForecastingRulesDTO> list = new ArrayList<>();
        try {
            List<Object[]> returnList = WorkflowPersistance.getProjectionRecords(projectionId, userId, sessionId, screenName,sessionDto);
            for (int i = 0; i < returnList.size(); i++) {
                Object[] obj = returnList.get(i);
                if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS)) {
                    ForecastingRulesDTO retRate = new ForecastingRulesDTO("Projected_Return_Percent");
                    retRate.setAmountLowest(Double.valueOf(String.valueOf(obj[0])));
                    retRate.setAmountGreatest(Double.valueOf(String.valueOf(obj[1])));
                    retRate.setPercentLowest(Double.valueOf(String.valueOf(obj[NumericConstants.TWO])));
                    retRate.setPercentGreatest(Double.valueOf(String.valueOf(obj[NumericConstants.THREE])));
                    list.add(retRate);
                } else {
                    ForecastingRulesDTO sales = new ForecastingRulesDTO("Projected_Contract_Sales_Dollars");
                    sales.setAmountLowest(Double.valueOf(String.valueOf(obj[0])));
                    sales.setAmountGreatest(Double.valueOf(String.valueOf(obj[1])));
                    sales.setPercentLowest(Double.valueOf(String.valueOf(obj[NumericConstants.TWELVE])));
                    sales.setPercentGreatest(Double.valueOf(String.valueOf(obj[NumericConstants.THIRTEEN])));

                    ForecastingRulesDTO units = new ForecastingRulesDTO("Projected_Contract_Sales_Units");
                    units.setAmountLowest(Double.valueOf(String.valueOf(obj[NumericConstants.TWO])));
                    units.setAmountGreatest(Double.valueOf(String.valueOf(obj[NumericConstants.THREE])));
                    units.setPercentLowest(Double.valueOf(String.valueOf(obj[NumericConstants.FOURTEEN])));
                    units.setPercentGreatest(Double.valueOf(String.valueOf(obj[NumericConstants.FIFTEEN])));

                    ForecastingRulesDTO discount = new ForecastingRulesDTO("Projected_Discount_Dollars");
                    discount.setAmountLowest(Double.valueOf(String.valueOf(obj[NumericConstants.FOUR])));
                    discount.setAmountGreatest(Double.valueOf(String.valueOf(obj[NumericConstants.FIVE])));
                    discount.setPercentLowest(Double.valueOf(String.valueOf(obj[NumericConstants.SIXTEEN])));
                    discount.setPercentGreatest(Double.valueOf(String.valueOf(obj[NumericConstants.SEVENTEEN])));

                    ForecastingRulesDTO rate = new ForecastingRulesDTO("Projected_Discount_Rate");
                    rate.setAmountLowest(Double.valueOf(String.valueOf(obj[NumericConstants.SIX])));
                    rate.setAmountGreatest(Double.valueOf(String.valueOf(obj[NumericConstants.SEVEN])));
                    rate.setPercentLowest(Double.valueOf(String.valueOf(obj[NumericConstants.EIGHTEEN])));
                    rate.setPercentGreatest(Double.valueOf(String.valueOf(obj[NumericConstants.NINETEEN])));

                    ForecastingRulesDTO netSales = new ForecastingRulesDTO("Net_Sales");
                    netSales.setAmountLowest(Double.valueOf(String.valueOf(obj[NumericConstants.EIGHT])));
                    netSales.setAmountGreatest(Double.valueOf(String.valueOf(obj[NumericConstants.NINE])));
                    netSales.setPercentLowest(Double.valueOf(String.valueOf(obj[NumericConstants.TWENTY])));
                    netSales.setPercentGreatest(Double.valueOf(String.valueOf(obj[NumericConstants.TWENTY_ONE])));

                    ForecastingRulesDTO netProfit = new ForecastingRulesDTO("Net_Profit");
                    netProfit.setAmountLowest(Double.valueOf(String.valueOf(obj[NumericConstants.TEN])));
                    netProfit.setAmountGreatest(Double.valueOf(String.valueOf(obj[NumericConstants.ELEVEN])));
                    netProfit.setPercentLowest(Double.valueOf(String.valueOf(obj[NumericConstants.TWENTY_TWO])));
                    netProfit.setPercentGreatest(Double.valueOf(String.valueOf(obj[NumericConstants.TWENTY_THREE])));
                    
                    list.add(sales);
                    list.add(units);
                    list.add(discount);
                    list.add(rate);
                    list.add(netSales);
                    list.add(netProfit);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return list;
    }
        
        public static List<ForecastingRulesDTO> getProjectionValuesForAccrual(int projectionId, String userId, String sessionId) {
		List<ForecastingRulesDTO> list = new ArrayList<>();
		try {
			List<Object[]> returnList = WorkflowPersistance.getProjectionRecordsForAccrual(projectionId, userId, sessionId);

			for (int i = 0; i < returnList.size(); i++) {
				Object[] obj = returnList.get(i);
				ForecastingRulesDTO accrualRate = new ForecastingRulesDTO("Accrual_Rate_Projection");
				accrualRate.setAmountLowest(Double.valueOf(String.valueOf(obj[NumericConstants.TWO])));
				accrualRate.setAmountGreatest(Double.valueOf(String.valueOf(obj[NumericConstants.THREE])));
				accrualRate.setPercentLowest(Double.valueOf(String.valueOf(obj[NumericConstants.SIX])));
				accrualRate.setPercentGreatest(Double.valueOf(String.valueOf(obj[NumericConstants.SEVEN])));
                                
				list.add(accrualRate);
			}
		} catch (Exception e) {
                    LOGGER.error(e);
		}
		return list;
	}
}
