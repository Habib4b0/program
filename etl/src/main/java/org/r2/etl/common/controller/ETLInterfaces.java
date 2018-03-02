package org.r2.etl.common.controller;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.r2.etl.common.action.R2ETL;
import org.r2.etl.common.util.CommonUtils;
import org.r2.etl.common.util.Constants;
import org.r2.etl.common.util.FilePathUtil;

/**
 * This is the common class for the BPI ETL Interfaces Execution.
 * 
 * @author stpl
 *
 */
public final class ETLInterfaces {

	/**
	 * The variable used for logger.
	 */
	public static final Logger LOGGER = Logger.getLogger(ETLInterfaces.class);

	private static final R2ETL r2etl = new R2ETL();
	static String filename = null;

	private static String logpath = CommonUtils.getFilePath();

	private ETLInterfaces() {

	}

	/**
	 * This method is used to invoke the Item Hierarchy Definition Interface.
	 * 
	 * @return SUCCESS
	 * @throws IOException
	 * @throws EtlException
	 */
	public static String runItemHierarchyDefn() throws BPIETLException {

		LOGGER.info("The ItemHierarchyDefn started executing");
		setFileName(FilePathUtil.ITEM_HIERARCHY_DEF_INTERFACE);
		filename = logpath + FilePathUtil.ITEM_HIERARCHY_DEF_INTERFACE;
		r2etl.runJob(filename);
		return Constants.SUCCESS;

	}

	/**
	 * This method is used to invoke the Item Hierarchy Definition Interface.
	 * 
	 * @return SUCCESS
	 * @throws IOException
	 * @throws EtlException
	 */
	public static String runItemHierarchyDefnHistory() throws BPIETLException {

		LOGGER.info("The ItemHierarchyDefnHistory started executing");
		setFileName(FilePathUtil.ITEM_HIERARCHY_DEF_INTERFACE_HISTORY);
		r2etl.runJob(filename);
		return Constants.SUCCESS;

	}

	/**
	 * This method is used to invoke the Average Shelf life Interface.
	 * 
	 * @return SUCCESS
	 * @throws IOException
	 * @throws EtlException
	 */
	public static String runAverageShelfLife() throws BPIETLException {

		LOGGER.info("The AverageShelfLife started executing");
		setFileName(FilePathUtil.AVERAGE_SHELF_LIFE_INTERFACE);
		r2etl.runJob(filename);
		return Constants.SUCCESS;
	}

	/**
	 * This method is used to invoke the Average Shelf life Interface.
	 * 
	 * @return SUCCESS
	 * @throws IOException
	 * @throws EtlException
	 */
	public static String runAverageShelfLifeHistory() throws BPIETLException {
		try {

			LOGGER.info("The AverageShelfLife started executing");
			setFileName(FilePathUtil.AVERAGE_SHELF_LIFE_INTERFACE_HISTORY);
			r2etl.runJob(filename);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
		return Constants.SUCCESS;
	}

	/**
	 * This method is used to invoke the GL Cost Center Interface.
	 * 
	 * @return SUCCESS
	 * @throws IOException
	 * @throws EtlException
	 */
	public static String runGLCostCenter() throws BPIETLException {

		LOGGER.info("The GLCostCenter started executing");
		setFileName(FilePathUtil.GL_COST_CENTER_INTERFACE);
		r2etl.runJob(filename);
		return Constants.SUCCESS;
	}

	/**
	 * This method is used to invoke the GL Cost Center Interface.
	 * 
	 * @return SUCCESS
	 * @throws IOException
	 * @throws EtlException
	 */
	public static String runGLCostCenterHistory() throws BPIETLException {

		LOGGER.info("The GLCostCenter started executing");
		setFileName(FilePathUtil.GL_COST_CENTER_INTERFACE_HISTORY);
		r2etl.runJob(filename);
		return Constants.SUCCESS;
	}

	/**
	 * This method is used to invoke the Lot Master Interface.
	 * 
	 * @return SUCCESS
	 * @throws IOException
	 * @throws EtlException
	 */
	public static String runLotMaster() throws BPIETLException {

		LOGGER.info("The LotMaster started executing");
		setFileName(FilePathUtil.LOT_MASTER_INTERFACE);
		r2etl.runJob(filename);
		return Constants.SUCCESS;
	}

	/**
	 * This method is used to invoke the Lot Master Interface.
	 * 
	 * @return SUCCESS
	 * @throws IOException
	 * @throws EtlException
	 */
	public static String runLotMasterHistory() throws BPIETLException {

		LOGGER.info("The LotMaster started executing");
		setFileName(FilePathUtil.LOT_MASTER_INTERFACE_HISTORY);
		r2etl.runJob(filename);
		return Constants.SUCCESS;
	}

	/**
	 * This method is used to invoke the Best Price Interface.
	 * 
	 * @return Success
	 * @throws IOException
	 * @throws EtlException
	 * 
	 */
	public static String runBestPrice() throws BPIETLException {

		LOGGER.info("The BestPrice started executing");
		setFileName(FilePathUtil.BEST_PRICE_INTERFACE);
		r2etl.runJob(filename);
		return Constants.SUCCESS;

	}

	/*
	 * This method is used to invoke the Accrual Master Interface.
	 * 
	 * @return SUCCESS
	 * 
	 * @throws IOException
	 * 
	 * @throws EtlException
	 */
	public static String runAccrualMaster() throws BPIETLException {

		LOGGER.info("The Accrual Master started executing");
		setFileName(FilePathUtil.ACCRUAL_MASTER_INBOUND);
		r2etl.runJob(filename);
		return Constants.SUCCESS;

	}

	/**
	 * This method is used to invoke the ACCRUAL_DETAIL Interface.
	 * 
	 * @return SUCCESS
	 * @throws IOException
	 * @throws EtlException
	 */
	public static String runAccrualOutbound() throws BPIETLException {

		LOGGER.info("The Accrual Outbound started executing");
		setFileName(FilePathUtil.ACCRUAL_DETAIL_OUTBOUND);
		r2etl.runJob(filename);
		return Constants.SUCCESS;

	}

	/**
	 * This method is used to invoke the GL POSTING Interface.
	 * 
	 * @return SUCCESS
	 * @throws IOException
	 * @throws EtlException
	 */
	public static String runGLPosting() throws BPIETLException {

		LOGGER.info("The GL POSTING started executing");
		setFileName(FilePathUtil.GL_POSTING_INTERFACE);

		r2etl.runJob(filename);
		return Constants.SUCCESS;

	}

	/**
	 * This method is used to invoke the DEMAND ACTUAL Interface.
	 * 
	 * @return SUCCESS
	 * @throws IOException
	 * @throws EtlException
	 */
	public static String runDemandActual() throws BPIETLException {

		LOGGER.info("The DEMAND ACTUAL started executing");
		setFileName(FilePathUtil.DEMAND_ACTUAL_INTERFACE);

		r2etl.runJob(filename);
		return Constants.SUCCESS;

	}

	/**
	 * This method is used to invoke the DEMAND FORECAST Interface.
	 * 
	 * @return SUCCESS
	 * @throws IOException
	 * @throws EtlException
	 */
	public static String runDemandForecast() throws BPIETLException {

		LOGGER.info("The DEMAND FORECAST started executing");
		setFileName(FilePathUtil.DEMAND_FORECAST_INTERFACE);

		r2etl.runJob(filename);
		return Constants.SUCCESS;

	}

	/**
	 * This method is used to invoke the INVENTORY ACTUAL DETAIL Interface.
	 * 
	 * @return SUCCESS
	 * @throws IOException
	 * @throws EtlException
	 */
	public static String runInventoryActualDetail() throws BPIETLException {

		LOGGER.info("The INVENTORY_WITHDRAWAL_ACTUAL_DETAIL_INTERFACE executing");
		setFileName(FilePathUtil.INVENTORY_WITHDRAWAL_ACTUAL_DETAIL_INTERFACE);
		r2etl.runJob(filename);
		return Constants.SUCCESS;

	}

	/**
	 * This method is used to invoke the INVENTORY ACTUAL SUMMARY Interface.
	 * 
	 * @return SUCCESS
	 * @throws IOException
	 * @throws EtlException
	 */
	public static String runInventoryActualSummary() throws BPIETLException {

		LOGGER.info("TheINVENTORY_WITHDRAWAL_ACTUAL_SUMMARY_INTERFACE started executing");
		setFileName(FilePathUtil.INVENTORY_WITHDRAWAL_ACTUAL_SUMMARY_INTERFACE);

		r2etl.runJob(filename);
		return Constants.SUCCESS;

	}

	/**
	 * This method is used to invoke the INVENTORY PROJECTED DETAIL Interface.
	 * 
	 * @return SUCCESS
	 * @throws IOException
	 * @throws EtlException
	 */
	public static String runInventoryProjectedDetail() throws BPIETLException {

		LOGGER.info("The INVENTORY_WITHDRAWAL_PROJECTED_DETAIL_INTERFACE started executing");
		setFileName(FilePathUtil.INVENTORY_WITHDRAWAL_PROJECTED_DETAIL_INTERFACE);

		r2etl.runJob(filename);
		return Constants.SUCCESS;

	}

	/**
	 * This method is used to invoke the INVENTORY PROJECTED SUMMARY Interface.
	 * 
	 * @return SUCCESS
	 * @throws IOException
	 * @throws EtlException
	 */
	public static String runInventoryProjectedSummary() throws BPIETLException {

		LOGGER.info("The INVENTORY_WITHDRAWAL_PROJECTED_SUMMARY_INTERFACE started executing");
		setFileName(FilePathUtil.INVENTORY_WITHDRAWAL_PROJECTED_SUMMARY_INTERFACE);

		r2etl.runJob(filename);
		return Constants.SUCCESS;

	}

	/**
	 * This method is used to invoke the RETURNS Interface.
	 * 
	 * @return SUCCESS
	 * @throws IOException
	 * @throws EtlException
	 */
	public static String runReturns() throws BPIETLException {

		LOGGER.info("The Returns started executing");
		setFileName(FilePathUtil.RETURNS_INTERFACE1);

		r2etl.runJob(filename);
		return Constants.SUCCESS;

	}

	/**
	 * This method is used to invoke the ADJUSTED_DEMAND_ACTUAL_INTERFACE
	 * Interface.
	 * 
	 * @return SUCCESS
	 * @throws IOException
	 * @throws EtlException
	 */
	public static String runAdjustedDemandActualInterface() throws BPIETLException {

		LOGGER.info("The ADJUSTED_DEMAND_ACTUAL_INTERFACE started executing");
		setFileName(FilePathUtil.ADJUSTED_DEMAND_ACTUAL_INTERFACE1);

		r2etl.runJob(filename);
		return Constants.SUCCESS;

	}

	/**
	 * This method is used to invoke the ADJUSTED_DEMAND_FORECASTINTERFACE
	 * Interface.
	 * 
	 * @return SUCCESS
	 * @throws IOException
	 * @throws EtlException
	 */
	public static String runAdjustedDemandForecastInterface() throws BPIETLException {

		LOGGER.info("The ADJUSTED_DEMAND_FORECASTINTERFACE started executing");
		setFileName(FilePathUtil.ADJUSTED_DEMAND_FORECASTINTERFACE1);

		r2etl.runJob(filename);
		return Constants.SUCCESS;

	}

	/**
	 * This method is used to invoke the CUSTOMER_GTS_ACTUAL_INTERFACE
	 * Interface.
	 * 
	 * @return SUCCESS
	 * @throws IOException
	 * @throws EtlException
	 */
	public static String runCustomerGtsActualInterface() throws BPIETLException {

		LOGGER.info("The CUSTOMER_GTS_ACTUAL_INTERFACE started executing");
		setFileName(FilePathUtil.CUSTOMER_GTS_ACTUAL_INTERFACE1);

		r2etl.runJob(filename);
		return Constants.SUCCESS;

	}

	/**
	 * This method is used to invoke the CUSTOMER_GTS_FORECAST_INTERFACE
	 * Interface.
	 * 
	 * @return SUCCESS
	 * @throws IOException
	 * @throws EtlException
	 */
	public static String runCustomerGtsForecastInterface() throws BPIETLException {

		LOGGER.info("The CUSTOMER_GTS_FORECAST_INTERFACE started executing");
		setFileName(FilePathUtil.CUSTOMER_GTS_FORECAST_INTERFACE1);

		r2etl.runJob(filename);
		return Constants.SUCCESS;

	}

	/**
	 * This method is used to invoke the RETURN_RESERVE_INTERFACE Interface.
	 * 
	 * @return SUCCESS
	 * @throws IOException
	 * @throws EtlException
	 */
	public static String runReturnReserveInterface() throws BPIETLException {

		LOGGER.info("The RETURN_RESERVE_INTERFACE started executing");
		setFileName(FilePathUtil.RETURN_RESERVE_INTERFACE1);

		r2etl.runJob(filename);
		return Constants.SUCCESS;

	}

	/**
	 * This method is used to invoke the CFF_OUTBOUND_INTERFACE Interface.
	 * 
	 * @return SUCCESS
	 * @throws IOException
	 * @throws EtlException
	 */
	public static String runCffOutboundInterface() throws BPIETLException {

		LOGGER.info("The CFF_OUTBOUND_INTERFACE started executing");
		setFileName(FilePathUtil.CFF_OUTBOUND_INTERFACE1);

		r2etl.runJob(filename);
		return Constants.SUCCESS;

	}

	/**
	 * This method is used to invoke the ARP_OUTBOUND_INTERFACE Interface.
	 * 
	 * @return SUCCESS
	 * @throws IOException
	 * @throws EtlException
	 */
	public static String runArpOutboundInterface() throws BPIETLException {

		LOGGER.info("The ARP_OUTBOUND_INTERFACE started executing");
		setFileName(FilePathUtil.ARP_OUTBOUND_INTERFACE1);

		r2etl.runJob(filename);
		return Constants.SUCCESS;

	}

	/**
	 * This method is used to invoke the
	 * ADJUSTMENT_GTN_DETAIL_OUTBOUND_INTERFACE Interface.
	 * 
	 * @return SUCCESS
	 * @throws IOException
	 * @throws EtlException
	 */
	public static String runAdjustmentGtnDetailInterface() throws BPIETLException {

		LOGGER.info("The ADJUSTMENT_GTN_DETAIL_OUTBOUND_INTERFACE started executing");
		setFileName(FilePathUtil.ADJUSTMENT_RESERVE_DETAIL_OUTBOUND_INTERFACE1);

		r2etl.runJob(filename);
		return Constants.SUCCESS;

	}

	/**
	 * This method is used to invoke the
	 * ADJUSTMENT_RESERVE_DETAIL_OUTBOUND_INTERFACE Interface.
	 * 
	 * @return SUCCESS
	 * @throws IOException
	 * @throws EtlException
	 */
	public static String runAdjustmentReserveDetailInterface() throws BPIETLException {

		LOGGER.info("The ADJUSTMENT_RESERVE_DETAIL_OUTBOUND_INTERFACE started executing");
		setFileName(FilePathUtil.ADJUSTMENT_RESERVE_DETAIL_OUTBOUND_INTERFACE1);

		r2etl.runJob(filename);
		return Constants.SUCCESS;

	}

	/**
	 * This method is used to invoke the Best Price Interface.
	 * 
	 * @return Success
	 * @throws IOException
	 * @throws EtlException
	 * 
	 */
	public static String runBestPriceHistory() throws BPIETLException {

		setFileName(FilePathUtil.BEST_PRICE_INTERFACE_HISTORY);
		r2etl.runJob(filename);
		return Constants.SUCCESS;

	}

	private static void setFileName(String input) {
		filename = logpath + input;
	}

}
