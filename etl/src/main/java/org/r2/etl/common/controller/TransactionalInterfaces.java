package org.r2.etl.common.controller;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.bpi.di.core.exception.EtlException;
import org.r2.etl.common.action.R2ETL;
import static org.r2.etl.common.controller.ContractInterfaces.LOGGER;
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
	
			 filename = logpath
					+ FilePathUtil.ACTUAL_MASTER_INTERFACE;
			r2etl.runJob(filename);
			return Constants.SUCCESS;
			
		}
	
        
          public static String commonMetodtoLoadAllFile(String interFaceName, String  fileName) throws BPIETLException {
 System.out.println("The " + interFaceName + " started executing");
        LOGGER.info("The " + interFaceName + " started executing");
        r2etl.runJob(logpath + fileName);

        return Constants.SUCCESS;

    }
	/**
	 * This method is used to invoke the Actual Master Interface.
	 * @return SUCCESS
	 * @throws IOException 
	 * @throws EtlException 
	 */
	public static String runActualMasterHistory()throws BPIETLException{

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

				 filename = logpath
						+ FilePathUtil.MASTER_DATA_INTERFACE_HISTORY;
				r2etl.runJob(filename);
				return Constants.SUCCESS;
			}
	

}
