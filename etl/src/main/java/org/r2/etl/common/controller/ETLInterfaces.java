package org.r2.etl.common.controller;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.bpi.di.core.exception.EtlException;
import org.r2.etl.common.action.R2ETL;
import org.r2.etl.common.util.CommonUtils;
import org.r2.etl.common.util.Constants;
import org.r2.etl.common.util.FilePathUtil;


/**
 * This is the common class for the BPI ETL Interfaces Execution.
 * @author stpl
 *
 */
public final class ETLInterfaces {
	
	/**
	 * The variable used for logger.
	 */
	public static final Logger LOGGER = Logger.getLogger(ETLInterfaces.class);
	
	private	static final R2ETL r2etl = new R2ETL();
	static String filename=null;
	
	private static String logpath=CommonUtils.getFilePath();
	
private ETLInterfaces(){

	}

	
	/**
	 * This method is used to invoke the Item Hierarchy Definition Interface.
	 * @return SUCCESS
	 * @throws IOException 
	 * @throws EtlException 
	 */
		public static String runItemHierarchyDefn()throws BPIETLException{
			
			//final R2ETL r2etl = new R2ETL();
			//final String logpath = CommonUtils.getFilePath();
			LOGGER.info("The ItemHierarchyDefn started executing");
			 filename = logpath
					+ FilePathUtil.ITEM_HIERARCHY_DEF_INTERFACE;
			r2etl.runJob(filename);
			return Constants.SUCCESS;

		}
		
		/**
		 * This method is used to invoke the Item Hierarchy Definition Interface.
		 * @return SUCCESS
		 * @throws IOException 
		 * @throws EtlException 
		 */
			public static String runItemHierarchyDefnHistory()throws BPIETLException{
				
				//final R2ETL r2etl = new R2ETL();
				//final String logpath = CommonUtils.getFilePath();
				LOGGER.info("The ItemHierarchyDefnHistory started executing");
				 filename = logpath
						+ FilePathUtil.ITEM_HIERARCHY_DEF_INTERFACE_HISTORY;
				r2etl.runJob(filename);
				return Constants.SUCCESS;

			}
		
		
		/**
		 * This method is used to invoke the Average Shelf life Interface.
		 * @return SUCCESS
		 * @throws IOException 
		 * @throws EtlException 
		 */
		public static String runAverageShelfLife()throws BPIETLException{
			
			//final R2ETL r2etl = new R2ETL();
			//final String logpath = CommonUtils.getFilePath();
			LOGGER.info("The AverageShelfLife started executing");
			 filename = logpath
					+FilePathUtil.AVERAGE_SHELF_LIFE_INTERFACE;
			r2etl.runJob(filename);
			return Constants.SUCCESS;
		}
		/**
		 * This method is used to invoke the Average Shelf life Interface.
		 * @return SUCCESS
		 * @throws IOException 
		 * @throws EtlException 
		 */
		public static String runAverageShelfLifeHistory()throws BPIETLException{
			try {
			//final R2ETL r2etl = new R2ETL();
			//final String logpath = CommonUtils.getFilePath();
				LOGGER.info("The AverageShelfLife started executing");
			filename = logpath
					+FilePathUtil.AVERAGE_SHELF_LIFE_INTERFACE_HISTORY;
			r2etl.runJob(filename);
			} catch(Exception ex){ex.printStackTrace();}
			return Constants.SUCCESS;
		}
		/**
		 * This method is used to invoke the GL Cost Center Interface.
		 * @return SUCCESS
		 * @throws IOException 
		 * @throws EtlException 
		 */
		public static String runGLCostCenter()throws BPIETLException{
		
			//final R2ETL r2etl = new R2ETL();
			//final String logpath = CommonUtils.getFilePath();
			LOGGER.info("The GLCostCenter started executing");
		 filename = logpath
					+FilePathUtil.GL_COST_CENTER_INTERFACE;
			r2etl.runJob(filename);
			return Constants.SUCCESS;
		}
		/**
		 * This method is used to invoke the GL Cost Center Interface.
		 * @return SUCCESS
		 * @throws IOException 
		 * @throws EtlException 
		 */
		public static String runGLCostCenterHistory()throws BPIETLException{
		
			//final R2ETL r2etl = new R2ETL();
			//final String logpath = CommonUtils.getFilePath();
			LOGGER.info("The GLCostCenter started executing");
			 filename = logpath
					+FilePathUtil.GL_COST_CENTER_INTERFACE_HISTORY;
			r2etl.runJob(filename);
			return Constants.SUCCESS;
		}
		/**
		 * This method is used to invoke the Lot Master Interface.
		 * @return SUCCESS
		 * @throws IOException 
		 * @throws EtlException 
		 */
		public static String runLotMaster()throws BPIETLException{
		
			//final R2ETL r2etl = new R2ETL();
			//final String logpath = CommonUtils.getFilePath();
			LOGGER.info("The LotMaster started executing");
			filename = logpath
					+ FilePathUtil.LOT_MASTER_INTERFACE;
			r2etl.runJob(filename);
			return Constants.SUCCESS;
		}
		
		/**
		 * This method is used to invoke the Lot Master Interface.
		 * @return SUCCESS
		 * @throws IOException 
		 * @throws EtlException 
		 */
		public static String runLotMasterHistory()throws BPIETLException{
		
			//final R2ETL r2etl = new R2ETL();
			//final String logpath = CommonUtils.getFilePath();
			LOGGER.info("The LotMaster started executing");
			 filename = logpath
					+ FilePathUtil.LOT_MASTER_INTERFACE_HISTORY;
			r2etl.runJob(filename);
			return Constants.SUCCESS;
		}
		
		/**
		 * This method is used to invoke the Best Price Interface.
		 * @return Success
		 * @throws IOException 
		 * @throws EtlException 
		 * 
		 */
			public static String runBestPrice() throws BPIETLException{
				
				//final R2ETL r2etl = new R2ETL();
				//final String logpath = CommonUtils.getFilePath();
				LOGGER.info("The BestPrice started executing");
			    filename = logpath
						+FilePathUtil.BEST_PRICE_INTERFACE;
				r2etl.runJob(filename);
				return Constants.SUCCESS;
				
			}
			 /* This method is used to invoke the Accrual Master Interface.
			 * @return SUCCESS
			 * @throws IOException 
			 * @throws EtlException 
			 */
	public static String runAccrualMaster()throws BPIETLException{
				
				//final R2ETL r2etl = new R2ETL();
				//final String logpath = CommonUtils.getFilePath();
				LOGGER.info("The Accrual Master started executing");
				 filename = logpath
						+ FilePathUtil.ACCRUAL_MASTER_INBOUND;
				r2etl.runJob(filename);
				return Constants.SUCCESS;
				
			}
	/**
	 * This method is used to invoke the ACCRUAL_DETAIL Interface.
	 * @return SUCCESS
	 * @throws IOException 
	 * @throws EtlException 
	 */
	public static String runAccrualOutbound()throws BPIETLException{
		
		//final R2ETL r2etl = new R2ETL();
		//final String logpath = CommonUtils.getFilePath();
		LOGGER.info("The Accrual Outbound started executing");
	 filename = logpath
				+ FilePathUtil.ACCRUAL_DETAIL_OUTBOUND;
		r2etl.runJob(filename);
		return Constants.SUCCESS;
		
	}
	/**
	 * This method is used to invoke the GL POSTING Interface.
	 * @return SUCCESS
	 * @throws IOException 
	 * @throws EtlException 
	 */
	public static String runGL_Posting()throws BPIETLException{
		
		//final R2ETL r2etl = new R2ETL();
		//final String logpath = CommonUtils.getFilePath();
	//	System.out.println("logpath  --- "+logpath);
		LOGGER.info("The Accrual Outbound started executing");
	 filename = logpath
				+ FilePathUtil.GL_POSTING_INTERFACE;
		//System.out.println("filename --- "+filename);
		r2etl.runJob(filename);
		return Constants.SUCCESS;
		
	}
	/**
	 * This method is used to invoke the DEMAND ACTUAL Interface.
	 * @return SUCCESS
	 * @throws IOException 
	 * @throws EtlException 
	 */
	public static String runDemandActual()throws BPIETLException{
		
		//final R2ETL r2etl = new R2ETL();
		//final String logpath = CommonUtils.getFilePath();
		//System.out.println("logpath  --- "+logpath);
		LOGGER.info("The Accrual Outbound started executing");
	 filename = logpath
				+ FilePathUtil.DEMAND_ACTUAL_INTERFACE;
		//System.out.println("filename --- "+filename);
		r2etl.runJob(filename);
		return Constants.SUCCESS;
		
	}
	/**
	 * This method is used to invoke the DEMAND FORECAST Interface.
	 * @return SUCCESS
	 * @throws IOException 
	 * @throws EtlException 
	 */
	public static String runDemandForecast()throws BPIETLException{
		
		//final R2ETL r2etl = new R2ETL();
		//final String logpath = CommonUtils.getFilePath();
		//System.out.println("logpath  --- "+logpath);
		LOGGER.info("The Accrual Outbound started executing");
	filename = logpath
				+ FilePathUtil.DEMAND_FORECAST_INTERFACE;
	//	System.out.println("filename --- "+filename);
		r2etl.runJob(filename);
		return Constants.SUCCESS;
		
	}
	/**
	 * This method is used to invoke the INVENTORY ACTUAL DETAIL Interface.
	 * @return SUCCESS
	 * @throws IOException 
	 * @throws EtlException 
	 */
	public static String runInventoryActualDetail()throws BPIETLException{
		
		//final R2ETL r2etl = new R2ETL();
		//final String logpath = CommonUtils.getFilePath();
	//	System.out.println("logpath  --- "+logpath);
		LOGGER.info("The INVENTORY_WITHDRAWAL_ACTUAL_DETAIL_INTERFACE executing");
		 filename = logpath
				+ FilePathUtil.INVENTORY_WITHDRAWAL_ACTUAL_DETAIL_INTERFACE;
		//System.out.println("filename --- "+filename);
		r2etl.runJob(filename);
		return Constants.SUCCESS;
		
	}
	/**
	 * This method is used to invoke the INVENTORY ACTUAL SUMMARY Interface.
	 * @return SUCCESS
	 * @throws IOException 
	 * @throws EtlException 
	 */
	public static String runInventoryActualSummary()throws BPIETLException{
		
		//final R2ETL r2etl = new R2ETL();
		//final String logpath = CommonUtils.getFilePath();
	//	System.out.println("logpath  --- "+logpath);
		LOGGER.info("TheINVENTORY_WITHDRAWAL_ACTUAL_SUMMARY_INTERFACE started executing");
		 filename = logpath
				+ FilePathUtil.INVENTORY_WITHDRAWAL_ACTUAL_SUMMARY_INTERFACE;
		//System.out.println("filename --- "+filename);
		r2etl.runJob(filename);
		return Constants.SUCCESS;
		
	}
		
	/**
	 * This method is used to invoke the INVENTORY PROJECTED DETAIL Interface.
	 * @return SUCCESS
	 * @throws IOException 
	 * @throws EtlException 
	 */
	public static String runInventoryProjectedDetail()throws BPIETLException{
		
		//final R2ETL r2etl = new R2ETL();
		//final String logpath = CommonUtils.getFilePath();
	//	System.out.println("logpath  --- "+logpath);
		LOGGER.info("The INVENTORY_WITHDRAWAL_PROJECTED_DETAIL_INTERFACE started executing");
		 filename = logpath
				+ FilePathUtil.INVENTORY_WITHDRAWAL_PROJECTED_DETAIL_INTERFACE;
	//	System.out.println("filename --- "+filename);
		r2etl.runJob(filename);
		return Constants.SUCCESS;
		
	}
		
	/**
	 * This method is used to invoke the INVENTORY PROJECTED SUMMARY Interface.
	 * @return SUCCESS
	 * @throws IOException 
	 * @throws EtlException 
	 */
	public static String runInventoryProjectedSummary()throws BPIETLException{
		
		//final R2ETL r2etl = new R2ETL();
		//final String logpath = CommonUtils.getFilePath();
	//	System.out.println("logpath  --- "+logpath);
		LOGGER.info("The INVENTORY_WITHDRAWAL_PROJECTED_SUMMARY_INTERFACE started executing");
	 filename = logpath
				+ FilePathUtil.INVENTORY_WITHDRAWAL_PROJECTED_SUMMARY_INTERFACE;
	//	System.out.println("filename --- "+filename);
		r2etl.runJob(filename);
		return Constants.SUCCESS;
		
	}
		
	/**
	 * This method is used to invoke the RETURNS Interface.
	 * @return SUCCESS
	 * @throws IOException 
	 * @throws EtlException 
	 */
	public static String runReturns()throws BPIETLException{
		
		//final R2ETL r2etl = new R2ETL();
		//final String logpath = CommonUtils.getFilePath();
		//System.out.println("logpath  --- "+logpath);
		LOGGER.info("The Returns started executing");
	filename = logpath
				+ FilePathUtil.RETURNS_INTERFACE;
	//	System.out.println("filename --- "+filename);
		r2etl.runJob(filename);
		return Constants.SUCCESS;
		
	}

	/**
	 * This method is used to invoke the ADJUSTED_DEMAND_ACTUAL_INTERFACE Interface.
	 * @return SUCCESS
	 * @throws IOException 
	 * @throws EtlException 
	 */
	public static String runAdjustedDemandActualInterface()throws BPIETLException{
		
		//final R2ETL r2etl = new R2ETL();
		//final String logpath = CommonUtils.getFilePath();
	//	System.out.println("logpath  --- "+logpath);
		LOGGER.info("The ADJUSTED_DEMAND_ACTUAL_INTERFACE started executing");
	filename = logpath
				+ FilePathUtil.ADJUSTED_DEMAND_ACTUAL_INTERFACE;
	//	System.out.println("filename --- "+filename);
		r2etl.runJob(filename);
		return Constants.SUCCESS;
		
	}
		
	/**
	 * This method is used to invoke the ADJUSTED_DEMAND_FORECASTINTERFACE Interface.
	 * @return SUCCESS
	 * @throws IOException 
	 * @throws EtlException 
	 */
	public static String runAdjustedDemandForecastInterface()throws BPIETLException{
		
		//final R2ETL r2etl = new R2ETL();
		//final String logpath = CommonUtils.getFilePath();
		//System.out.println("logpath  --- "+logpath);
		LOGGER.info("The ADJUSTED_DEMAND_FORECASTINTERFACE started executing");
	 filename = logpath
				+ FilePathUtil.ADJUSTED_DEMAND_FORECASTINTERFACE;
	//	System.out.println("filename --- "+filename);
		r2etl.runJob(filename);
		return Constants.SUCCESS;
		
	}
		
	/**
	 * This method is used to invoke the CUSTOMER_GTS_ACTUAL_INTERFACE Interface.
	 * @return SUCCESS
	 * @throws IOException 
	 * @throws EtlException 
	 */
	public static String runCustomerGtsActualInterface()throws BPIETLException{
		
		//final R2ETL r2etl = new R2ETL();
		//final String logpath = CommonUtils.getFilePath();
	//	System.out.println("logpath  --- "+logpath);
		LOGGER.info("The CUSTOMER_GTS_ACTUAL_INTERFACE started executing");
	 filename = logpath
				+ FilePathUtil.CUSTOMER_GTS_ACTUAL_INTERFACE;
	//	System.out.println("filename --- "+filename);
		r2etl.runJob(filename);
		return Constants.SUCCESS;
		
	}
		
	/**
	 * This method is used to invoke the CUSTOMER_GTS_FORECAST_INTERFACE Interface.
	 * @return SUCCESS
	 * @throws IOException 
	 * @throws EtlException 
	 */
	public static String runCustomerGtsForecastInterface()throws BPIETLException{
		
		//final R2ETL r2etl = new R2ETL();
		//final String logpath = CommonUtils.getFilePath();
	//	System.out.println("logpath  --- "+logpath);
		LOGGER.info("The CUSTOMER_GTS_FORECAST_INTERFACE started executing");
	 filename = logpath
				+ FilePathUtil.CUSTOMER_GTS_FORECAST_INTERFACE;
	//	System.out.println("filename --- "+filename);
		r2etl.runJob(filename);
		return Constants.SUCCESS;
		
	}
		
	/**
	 * This method is used to invoke the RETURN_RESERVE_INTERFACE Interface.
	 * @return SUCCESS
	 * @throws IOException 
	 * @throws EtlException 
	 */
	public static String runReturnReserveInterface()throws BPIETLException{
		
		//final R2ETL r2etl = new R2ETL();
		//final String logpath = CommonUtils.getFilePath();
	//	System.out.println("logpath  --- "+logpath);
		LOGGER.info("The RETURN_RESERVE_INTERFACE started executing");
		 filename = logpath
				+ FilePathUtil.RETURN_RESERVE_INTERFACE;
	//	System.out.println("filename --- "+filename);
		r2etl.runJob(filename);
		return Constants.SUCCESS;
		
	}
		
	/**
	 * This method is used to invoke the CFF_OUTBOUND_INTERFACE Interface.
	 * @return SUCCESS
	 * @throws IOException 
	 * @throws EtlException 
	 */
	public static String runCffOutboundInterface()throws BPIETLException{
		
		//final R2ETL r2etl = new R2ETL();
		//final String logpath = CommonUtils.getFilePath();
	//	System.out.println("logpath  --- "+logpath);
		LOGGER.info("The CFF_OUTBOUND_INTERFACE started executing");
	 filename = logpath
				+ FilePathUtil.CFF_OUTBOUND_INTERFACE;
	//	System.out.println("filename --- "+filename);
		r2etl.runJob(filename);
		return Constants.SUCCESS;
		
	}
		
	/**
	 * This method is used to invoke the ARP_OUTBOUND_INTERFACE Interface.
	 * @return SUCCESS
	 * @throws IOException 
	 * @throws EtlException 
	 */
	public static String runArpOutboundInterface()throws BPIETLException{
		
		//final R2ETL r2etl = new R2ETL();
		//final String logpath = CommonUtils.getFilePath();
		//System.out.println("logpath  --- "+logpath);
		LOGGER.info("The ARP_OUTBOUND_INTERFACE started executing");
		filename = logpath
				+ FilePathUtil.ARP_OUTBOUND_INTERFACE;
		//System.out.println("filename --- "+filename);
		r2etl.runJob(filename);
		return Constants.SUCCESS;
		
	}
	
	/**
	 * This method is used to invoke the ADJUSTMENT_GTN_DETAIL_OUTBOUND_INTERFACE Interface.
	 * @return SUCCESS
	 * @throws IOException 
	 * @throws EtlException 
	 */
	public static String runAdjustmentGtnDetailInterface()throws BPIETLException{
		
		//final R2ETL r2etl = new R2ETL();
		//final String logpath = CommonUtils.getFilePath();
	//	System.out.println("logpath  --- "+logpath);
		LOGGER.info("The ARP_OUTBOUND_INTERFACE started executing");
	 filename = logpath
				+ FilePathUtil.ADJUSTMENT_GTN_DETAIL_OUTBOUND_INTERFACE;
	//	System.out.println("filename --- "+filename);
		r2etl.runJob(filename);
		return Constants.SUCCESS;
		
	}
	/**
	 * This method is used to invoke the ADJUSTMENT_RESERVE_DETAIL_OUTBOUND_INTERFACE Interface.
	 * @return SUCCESS
	 * @throws IOException 
	 * @throws EtlException 
	 */
	public static String runAdjustmentReserveDetailInterface()throws BPIETLException{
		
		//final R2ETL r2etl = new R2ETL();
		//final String logpath = CommonUtils.getFilePath();
	//	System.out.println("logpath  --- "+logpath);
		LOGGER.info("The ARP_OUTBOUND_INTERFACE started executing");
	filename = logpath
				+ FilePathUtil.ADJUSTMENT_RESERVE_DETAIL_OUTBOUND_INTERFACE;
	//	System.out.println("filename --- "+filename);
		r2etl.runJob(filename);
		return Constants.SUCCESS;
		
	}
	
			/**
			 * This method is used to invoke the Best Price Interface.
			 * @return Success
			 * @throws IOException 
			 * @throws EtlException 
			 * 
			 */
				public static String runBestPriceHistory() throws BPIETLException{
					
					//final R2ETL r2etl = new R2ETL();
					//final String logpath = CommonUtils.getFilePath();
				 filename = logpath
							+FilePathUtil.BEST_PRICE_INTERFACE_HISTORY;
					r2etl.runJob(filename);
					return Constants.SUCCESS;
					
				}
	
}
