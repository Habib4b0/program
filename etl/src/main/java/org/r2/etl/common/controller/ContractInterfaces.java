package org.r2.etl.common.controller;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.bpi.di.core.exception.EtlException;
import org.r2.etl.common.action.R2ETL;
import org.r2.etl.common.util.CommonUtils;
import org.r2.etl.common.util.Constants;
import org.r2.etl.common.util.FilePathUtil;

/**
 * This class is used for Contract Interface execution. 
 * @author stpl
 *
 */
public final class ContractInterfaces {
	
	/**
	 * The variable used for logger.
	 */
	public static final Logger LOGGER = Logger.getLogger(ContractInterfaces.class);
	private	static final R2ETL r2etl = new R2ETL();
	static String filename=null;
	
	private static String logpath=CommonUtils.getFilePath();
	
private ContractInterfaces(){

		
	}


	/**
	 */

	
	/**
	 * This method is used to invoke the Contract Header Interface.
	 * @return SUCCESS
	 * @throws IOException 
	 * @throws EtlException 
	 * 
	 */
	public static String runContractHeader()throws BPIETLException{
			
			//final R2ETL r2etl = new R2ETL();
			//final String logpath = CommonUtils.getFilePath();
			LOGGER.info("The Contract Master started executing");
			 filename = logpath
					+ FilePathUtil.CONTRACT_HEADER_INTERFACE;
			r2etl.runJob(filename);
			return Constants.SUCCESS;
			

		}
		/**
		 * This method is used to invoke the Contract Header Historical Interface.
		 * @return SUCCESS
		 * @throws IOException 
		 * @throws EtlException 
		 * 
		 */
	public static String runContractMasterHistory()throws BPIETLException{
			
			//final R2ETL r2etl = new R2ETL();
			//final String logpath = CommonUtils.getFilePath();
			LOGGER.info("The Contract Master History started executing");
			 filename = logpath
					+ FilePathUtil.CONTRACT_HEADER_INTERFACE_HISTORY;
			r2etl.runJob(filename);
			return Constants.SUCCESS;
			
		}
		/**
		 * This method is used to invoke the Company Family Plan Interface.
		 * @return SUCCESS
		 * @throws IOException 
		 * @throws EtlException 
		 * 
		 */

	public static String runCompanyFamilyPlan()throws BPIETLException{
			
			//final R2ETL r2etl = new R2ETL();
			//final String logpath = CommonUtils.getFilePath();
			LOGGER.info("The CFP started executing");
			 filename = logpath
					+ FilePathUtil.COMPANY_FAMILYPLAN_INTERFACE;
			r2etl.runJob(filename);
			return Constants.SUCCESS;
			
		}
	/**
	 * This method is used to invoke the Company Family Plan Interface.
	 * @return SUCCESS
	 * @throws IOException 
	 * @throws EtlException 
	 * 
	 */

public static String runCompanyFamilyPlanHistory()throws BPIETLException{
		
		//final R2ETL r2etl = new R2ETL();
		//final String logpath = CommonUtils.getFilePath();
		 filename = logpath
				+ FilePathUtil.COMPANY_FAMILYPLAN_INTERFACE_HISTORY;
		r2etl.runJob(filename);
		return Constants.SUCCESS;
		
	}
	/**
	 * This method is used to invoke the Item Family Plan Interface.
	 * @return SUCCESS
	 * @throws IOException 
	 * @throws EtlException 
	 */
	public static String runItemFamilyPlan()throws BPIETLException{
		
			//final R2ETL r2etl = new R2ETL();
			//final String logpath = CommonUtils.getFilePath();
			LOGGER.info("The IFP started executing");
			 filename = logpath
					+ FilePathUtil.ITEM_FAMILY_PLAN_INTERFACE;
			r2etl.runJob(filename);
			return Constants.SUCCESS;
			
		}
	/**
	 * This method is used to invoke the Item Family Plan Interface.
	 * @return SUCCESS
	 * @throws IOException 
	 * @throws EtlException 
	 */
	public static String runItemFamilyPlanHistory()throws BPIETLException{
		
			//final R2ETL r2etl = new R2ETL();
			//final String logpath = CommonUtils.getFilePath();
			LOGGER.info("The IFP started executing");
			 filename = logpath
					+ FilePathUtil.ITEM_FAMILY_PLAN_INTERFACE_HISTORY;
			r2etl.runJob(filename);
			return Constants.SUCCESS;
			
		}
	/**
	 * This method is used to invoke the Price Schedule Interface.
	 * @return SUCCESS
	 * @throws IOException 
	 * @throws EtlException 
	 * 
	 */
	public static String runPriceSchedule()throws BPIETLException{
			
			//final R2ETL r2etl = new R2ETL();
			//final String logpath = CommonUtils.getFilePath();
			LOGGER.info("The PS started executing");
			 filename = logpath
					+ FilePathUtil.PRICE_SCHEDULE_INTERFACE;
			r2etl.runJob(filename);
			return Constants.SUCCESS;
			
		}
	/**
	 * This method is used to invoke the Price Schedule Interface.
	 * @return SUCCESS
	 * @throws IOException 
	 * @throws EtlException 
	 * 
	 */
	public static String runPriceScheduleHistory()throws BPIETLException{
			
			//final R2ETL r2etl = new R2ETL();
			//final String logpath = CommonUtils.getFilePath();
			LOGGER.info("The PS started executing");
			 filename = logpath
					+ FilePathUtil.PRICE_SCHEDULE_INTERFACE_HISTORY;
			r2etl.runJob(filename);
			return Constants.SUCCESS;
			
		}
	/**
	 * This method is used to invoke the Rebate Schedule Interface.
	 * @return SUCCESS
	 * @throws IOException 
	 * @throws EtlException 
	 * 
	 */
	public static String runRebateSchedule()throws BPIETLException{
			
			//final R2ETL r2etl = new R2ETL();
			//final String logpath = CommonUtils.getFilePath();
			LOGGER.info("The RS started executing");
			 filename = logpath
					+ FilePathUtil.REBATE_SCHEDULE_INTERFACE;
			r2etl.runJob(filename);
			return Constants.SUCCESS;
			
		}
	/**
	 * This method is used to invoke the Rebate Schedule Interface.
	 * @return SUCCESS
	 * @throws IOException 
	 * @throws EtlException 
	 * 
	 */
	public static String runRebateScheduleHistory()throws BPIETLException{
			
			//final R2ETL r2etl = new R2ETL();
			//final String logpath = CommonUtils.getFilePath();
			LOGGER.info("The RS started executing");
			 filename = logpath
					+ FilePathUtil.REBATE_SCHEDULE_INTERFACE_HISTORY;
			r2etl.runJob(filename);
			return Constants.SUCCESS;
			
		}
	/**
	 * This method is used to invoke the Rebate Plan Interface.
	 * @return SUCCESS
	 * @throws IOException 
	 * @throws EtlException 
	 */
	public static String runRebatePlan()throws BPIETLException{
			
			//final R2ETL r2etl = new R2ETL();
			//final String logpath = CommonUtils.getFilePath();
			 filename = logpath
					+ FilePathUtil.REBATE_PLAN_INTERFACE;
			r2etl.runJob(filename);
			return Constants.SUCCESS;
			
		}
	/**
	 * This method is used to invoke the Rebate Plan Interface.
	 * @return SUCCESS
	 * @throws IOException 
	 * @throws EtlException 
	 */
	public static String runRebatePlanHistory()throws BPIETLException{
			
			//final R2ETL r2etl = new R2ETL();
			//final String logpath = CommonUtils.getFilePath();
			 filename = logpath
					+ FilePathUtil.REBATE_PLAN_INTERFACE_HISTORY;
			r2etl.runJob(filename);
			return Constants.SUCCESS;
			
		}

}
