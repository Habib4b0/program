package org.r2.etl.common.controller;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.r2.etl.common.action.R2ETL;
import org.r2.etl.common.util.CommonUtils;
import org.r2.etl.common.util.Constants;
import org.r2.etl.common.util.FilePathUtil;

/**
 * This class is used for execution of Global Interfaces.
 * 
 * @author stpl
 *
 */
public final class GlobalInterfaces {

	/**
	 * The variable used for logger.
	 */
	public static final Logger LOGGER = Logger.getLogger(GlobalInterfaces.class);

	private static final R2ETL r2etl = new R2ETL();
	static String filename = null;

	private static String logpath = CommonUtils.getFilePath();

	private GlobalInterfaces() {

	}

	/**
	 * This method is used to invoke the Company Master Incremental Interface.
	 * 
	 * @return SUCCESS
	 * @throws IOException
	 * @throws EtlException
	 * 
	 */
	public static String runCompanyMaster() throws BPIETLException {

		LOGGER.info("The Company Master started executing");
		setFileName(FilePathUtil.COMPANY_MASTER_INTERFACE);
		r2etl.runJob(filename);

		return Constants.SUCCESS;

	}

	public static String commonMetodtoLoadAllFile(String interFaceName, String fileName) throws BPIETLException {
		LOGGER.info("The " + interFaceName + " started executing");
		r2etl.runJob(logpath + fileName);

		return Constants.SUCCESS;

	}

	/**
	 * This method is used to invoke Company Master Historical Interface.
	 * 
	 * @return SUCCESS
	 * @throws IOException
	 * @throws EtlException
	 * 
	 */
	public static String runCompanyMasterHistory() throws BPIETLException {

		LOGGER.info("The Company Master History started executing");
		setFileName(FilePathUtil.COMPANY_MASTER_INTERFACE_HISTORY);
		r2etl.runJob(filename);
		return Constants.SUCCESS;

	}

	/**
	 * This method is used to invoke the Company Parent Details Interface.
	 * 
	 * @return SUCCESS
	 * @throws IOException
	 * @throws EtlException
	 */
	public static String runCompanyParent() throws BPIETLException {

		LOGGER.info("The Company Parent started executing");
		setFileName(FilePathUtil.COMPANY_PARENT_INTERFACE);
		r2etl.runJob(filename);
		return Constants.SUCCESS;
	}

	/**
	 * This method is used to invoke the Company Parent Details History
	 * Interface.
	 * 
	 * @return SUCCESS
	 * @throws IOException
	 * @throws EtlException
	 */
	public static String runCompanyParentHistory() throws BPIETLException {

		setFileName(FilePathUtil.COMPANY_PARENT_INTERFACE_HISTORY);
		r2etl.runJob(filename);
		return Constants.SUCCESS;
	}

	/**
	 * This method is used to invoke the Company Trade Class Interface.
	 * 
	 * @return SUCCESS
	 * @throws IOException
	 * @throws EtlException
	 * 
	 */
	public static String runCompanyTradeClass() throws BPIETLException {

		LOGGER.info("The Company Trade Class started executing");
		setFileName(FilePathUtil.COMPANY_TRADE_CLASS_INTERFACE);
		r2etl.runJob(filename);
		return Constants.SUCCESS;

	}

	/**
	 * This method is used to invoke the Company Trade Class Interface.
	 * 
	 * @return SUCCESS
	 * @throws IOException
	 * @throws EtlException
	 * 
	 */
	public static String runCompanyTradeClassHistory() throws BPIETLException {

		setFileName(FilePathUtil.COMPANY_TRADE_CLASS_INTERFACE_HISTORY);
		r2etl.runJob(filename);
		return Constants.SUCCESS;

	}

	/**
	 * This method is used to invoke Company Identifier Interface.
	 * 
	 * @return SUCCESS
	 * @throws IOException
	 * @throws EtlException
	 * 
	 */
	public static String runCompanyIdentifier() throws BPIETLException {

		LOGGER.info("The Company Identifier started executing");
		setFileName(FilePathUtil.COMPANY_IDENTIFIER_INTERFACE);
		r2etl.runJob(filename);
		return Constants.SUCCESS;

	}

	/**
	 * This method is used to invoke Company Identifier History Interface.
	 * 
	 * @return SUCCESS
	 * @throws IOException
	 * @throws EtlException
	 * 
	 */
	public static String runCompanyIdentifierHistory() throws BPIETLException {

		LOGGER.info("The Company Identifier started executing");
		setFileName(FilePathUtil.COMPANY_IDENTIFIER_INTERFACE_HISTORY);
		r2etl.runJob(filename);
		return Constants.SUCCESS;

	}

	/**
	 * This method is used to invoke Item Master Interface.
	 * 
	 * @return SUCCESS
	 * @throws IOException
	 * @throws EtlException
	 */
	public static String runItemMaster() throws BPIETLException {

		LOGGER.info("The ITEM Master Master started executing");
		setFileName(FilePathUtil.ITEM_MASTER_INTERFACE);
		r2etl.runJob(filename);
		return Constants.SUCCESS;

	}

	/**
	 * This method is used to invoke Item Master Interface.
	 * 
	 * @return SUCCESS
	 * @throws IOException
	 * @throws EtlException
	 */
	public static String runItemMasterHistory() throws BPIETLException {

		LOGGER.info("The ITEM Master Master started executing");
		setFileName(FilePathUtil.ITEM_MASTER_INTERFACE_HISTORY);
		r2etl.runJob(filename);
		return Constants.SUCCESS;

	}

	/**
	 * This method is used to invoke the Item Identifier Interface.
	 * 
	 * @return SUCCESS
	 * @throws IOException
	 * @throws EtlException
	 */
	public static String runItemIdentifier() throws BPIETLException {

		LOGGER.info("The Item Identifier started executing");
		setFileName(FilePathUtil.ITEM_IDENTIFIER_INTERFACE);
		r2etl.runJob(filename);
		return Constants.SUCCESS;

	}

	/**
	 * This method is used to invoke the Item Identifier Interface.
	 * 
	 * @return SUCCESS
	 * @throws IOException
	 * @throws EtlException
	 */
	public static String runItemIdentifierHistory() throws BPIETLException {

		LOGGER.info("The Item Identifier started executing");
		setFileName(FilePathUtil.ITEM_IDENTIFIER_INTERFACE_HISTORY);
		r2etl.runJob(filename);
		return Constants.SUCCESS;

	}

	/**
	 * This method is used to invoke Item Pricing Interface.
	 * 
	 * @return SUCCESS
	 * @throws IOException
	 * @throws EtlException
	 * 
	 */
	public static String runItemPricing() throws BPIETLException {

		LOGGER.info("The Item Pricing started executing");
		setFileName(FilePathUtil.ITEM_PRICING_INTERFACE);
		r2etl.runJob(filename);
		return Constants.SUCCESS;

	}

	/**
	 * This method is used to invoke Item Pricing Interface.
	 * 
	 * @return SUCCESS
	 * @throws IOException
	 * @throws EtlException
	 * 
	 */
	public static String runItemPricingHistory() throws BPIETLException {

		LOGGER.info("The Item Pricing started executing");
		setFileName(FilePathUtil.ITEM_PRICING_INTERFACE_HISTORY);
		r2etl.runJob(filename);
		return Constants.SUCCESS;

	}

	private static void setFileName(String input) {
		filename = logpath + input;
	}

}
