package org.r2.etl.common.controller;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.bpi.di.core.exception.EtlException;
import org.r2.etl.common.action.R2ETL;
import org.r2.etl.common.util.CommonUtils;
import org.r2.etl.common.util.Constants;
import org.r2.etl.common.util.FilePathUtil;

/**
 * This class contains method to execute Transaction Interfaces
 * @author stpl
 *
 */
public final class TransactionalInterfaces {
	
	/**
	 * The variable used for logger.
	 */
	public static final Logger LOGGER = Logger.getLogger(TransactionalInterfaces.class);

	
	private	static final R2ETL r2etl = new R2ETL();
	static String filename=null;
	
	private static String logpath=CommonUtils.getFilePath();
	
private TransactionalInterfaces(){

	}

		
	/**
	 * Constructor
	 */

	
	/**
	 * This method is used to invoke the Actual Master Interface.
	 * @return SUCCESS
	 * @throws IOException 
	 * @throws EtlException 
	 */
	public static String runActualMaster()throws BPIETLException{
			
			//final R2ETL r2etl = new R2ETL();
			//final String logpath = CommonUtils.getFilePath();
			 filename = logpath
					+ FilePathUtil.ACTUAL_MASTER_INTERFACE;
			r2etl.runJob(filename);
			return Constants.SUCCESS;
			
		}
	
	/**
	 * This method is used to invoke the Actual Master Interface.
	 * @return SUCCESS
	 * @throws IOException 
	 * @throws EtlException 
	 */
	public static String runActualMasterHistory()throws BPIETLException{
			
			//final R2ETL r2etl = new R2ETL();
			//final String logpath = CommonUtils.getFilePath();
			 filename = logpath
					+ FilePathUtil.ACTUAL_MASTER_INTERFACE_HISTORY;
			r2etl.runJob(filename);
			return Constants.SUCCESS;
			
		}
	/**
	 * This method is used to invoke the Item Hierarchy Interface.
	 * @return SUCCESS
	 * @throws IOException 
	 * @throws EtlException 
	 * 
	 */
		public static String runItemHierarchy()throws BPIETLException{
		
			//final R2ETL r2etl = new R2ETL();
			//final String logpath = CommonUtils.getFilePath();
			 filename = logpath
					+FilePathUtil.ITEM_HIERARCHY_INTERFACE;
			r2etl.runJob(filename);
			return Constants.SUCCESS;
		}
		
		/**
		 * This method is used to invoke the Item Hierarchy Interface.
		 * @return SUCCESS
		 * @throws IOException 
		 * @throws EtlException 
		 * 
		 */
			public static String runItemHierarchyHistory()throws BPIETLException{
			
				//final R2ETL r2etl = new R2ETL();
				//final String logpath = CommonUtils.getFilePath();
				 filename = logpath
						+FilePathUtil.ITEM_HIERARCHY_INTERFACE_HISTORY;
				r2etl.runJob(filename);
				return Constants.SUCCESS;
			}
	/**
	 * This method is used to invoke the Sales Master Interface.
	 * @return SUCCESS
	 * @throws IOException 
	 * @throws EtlException 
	 */
		public static String runSalesMaster()throws BPIETLException{
			
			//final R2ETL r2etl = new R2ETL();
			//final String logpath = CommonUtils.getFilePath();
			 filename = logpath
					+ FilePathUtil.SALES_MASTER_INTERFACE;
			r2etl.runJob(filename);
			return Constants.SUCCESS;
		}
		
		/**
		 * This method is used to invoke the Sales Master Interface.
		 * @return SUCCESS
		 * @throws IOException 
		 * @throws EtlException 
		 */
			public static String runSalesMasterHistory()throws BPIETLException{
				
				//final R2ETL r2etl = new R2ETL();
				//final String logpath = CommonUtils.getFilePath();
				 filename = logpath
						+ FilePathUtil.SALES_MASTER_INTERFACE_HISTORY;
				r2etl.runJob(filename);
				return Constants.SUCCESS;
			}
	/**
	 * This method is used to invoke the ForeCasting Sales Interface.
	 * @return SUCCESS
	 * @throws IOException 
	 * @throws EtlException 
	 */
		public static String runForecastingSales()throws BPIETLException{
			
			//final R2ETL r2etl = new R2ETL();
			//final String logpath = CommonUtils.getFilePath();
			 filename = logpath
					+ FilePathUtil.FORECASTING_MASTER_INTERFACE;
			r2etl.runJob(filename);
			return Constants.SUCCESS;
		}
		
		/**
		 * This method is used to invoke the ForeCasting Sales Interface.
		 * @return SUCCESS
		 * @throws IOException 
		 * @throws EtlException 
		 */
			public static String runForecastingSalesHistory()throws BPIETLException{
				
				//final R2ETL r2etl = new R2ETL();
				//final String logpath = CommonUtils.getFilePath();
				 filename = logpath
						+ FilePathUtil.FORECASTING_MASTER_INTERFACE_HISTORY;
				r2etl.runJob(filename);
				return Constants.SUCCESS;
			}
	/**
	 * This method is used to invoke the GL Balance Interface.
	 * @return SUCCESS
	 * @throws IOException 
	 * @throws EtlException 
	 */
		public static String runGLBalance()throws BPIETLException{
			//final R2ETL r2etl = new R2ETL();
			//final String logpath = CommonUtils.getFilePath();
			 filename = logpath
					+ FilePathUtil.GL_BALANCE_INTERFACE;
			r2etl.runJob(filename);
			return Constants.SUCCESS;
			
		}
		/**
		 * This method is used to invoke the GL Balance Interface.
		 * @return SUCCESS
		 * @throws IOException 
		 * @throws EtlException 
		 */
			public static String runGLBalanceHistory()throws BPIETLException{
				//final R2ETL r2etl = new R2ETL();
				//final String logpath = CommonUtils.getFilePath();
				 filename = logpath
						+ FilePathUtil.GL_BALANCE_INTERFACE_HISTORY;
				r2etl.runJob(filename);
				return Constants.SUCCESS;
				
			}
	/**
	 * This method is used to invoke the CPI Index Interface.
	 * @return SUCCESS
	 * @throws IOException 
	 * @throws EtlException 
	 *
	 */
		public static String runCPIIndex()throws BPIETLException{
		
			//final R2ETL r2etl = new R2ETL();
			//final String logpath = CommonUtils.getFilePath();
			 filename = logpath
					+ FilePathUtil.CPI_INDEX_INTERFACE;
			r2etl.runJob(filename);
			return Constants.SUCCESS;
		}
		
		/**
		 * This method is used to invoke the CPI Index Interface.
		 * @return SUCCESS
		 * @throws IOException 
		 * @throws EtlException 
		 *
		 */
			public static String runCPIIndexHistory()throws BPIETLException{
			
				//final R2ETL r2etl = new R2ETL();
				//final String logpath = CommonUtils.getFilePath();
				 filename = logpath
						+ FilePathUtil.CPI_INDEX_INTERFACE_HISTORY;
				r2etl.runJob(filename);
				return Constants.SUCCESS;
			}
	/**
	 * This method is used to invoke the Formula Details Interface.
	 * @return SUCCESS
	 * @throws IOException 
	 * @throws EtlException 
	 */
		public static String runFormulaDetails()throws BPIETLException{
			
			//final R2ETL r2etl = new R2ETL();
			//final String logpath = CommonUtils.getFilePath();
			 filename = logpath
					+ FilePathUtil.FORMULA_DETAILS_INTERFACE;
			r2etl.runJob(filename);
			return Constants.SUCCESS;
		}
		/**
		 * This method is used to invoke the Formula Details Historical Load.
		 * @return SUCCESS
		 * @throws IOException 
		 * @throws EtlException 
		 */
		public static String runFormulaDetailsHistory()throws BPIETLException{
			
			//final R2ETL r2etl = new R2ETL();
			//final String logpath = CommonUtils.getFilePath();
			 filename = logpath
					+ FilePathUtil.FORMULA_DETAILS_INTERFACE_HISTORY;
			r2etl.runJob(filename);
			return Constants.SUCCESS;

		}
	/**
	 * This method is used to invoke Master Data Attribute Interface.
	 * @return SUCCESS
	 * @throws IOException 
	 * @throws EtlException 
	 */
		public static String runMasterDataAttrb()throws BPIETLException{
			
			//final R2ETL r2etl = new R2ETL();
			//final String logpath = CommonUtils.getFilePath();
			 filename = logpath
					+ FilePathUtil.MASTER_DATA_INTERFACE;
			r2etl.runJob(filename);
			return Constants.SUCCESS;
		}
		
		/**
		 * This method is used to invoke Master Data Attribute Interface.
		 * @return SUCCESS
		 * @throws IOException 
		 * @throws EtlException 
		 */
			public static String runMasterDataAttrbHistory()throws BPIETLException{
				
				//final R2ETL r2etl = new R2ETL();
				//final String logpath = CommonUtils.getFilePath();
				 filename = logpath
						+ FilePathUtil.MASTER_DATA_INTERFACE_HISTORY;
				r2etl.runJob(filename);
				return Constants.SUCCESS;
			}
	

}
