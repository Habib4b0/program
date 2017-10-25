package org.r2.etl.common.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.bpi.di.core.EtlEnvironment;
import org.bpi.di.core.encryption.Encr;
import org.bpi.di.core.exception.EtlException;
import org.bpi.di.job.Job;
import org.bpi.di.job.JobMeta;
import org.r2.etl.common.controller.BPIETLException;
import org.r2.etl.common.util.CommonUtils;
import org.r2.etl.common.util.FilePathUtil;

/**
 * This class is used to execute the BPI ETL job.
 * @author stpl
 *
 */
public class R2ETL {


	/**
	 * The variable used for logger
	 */
	private static final Logger LOGGER = Logger.getLogger(R2ETL.class);
	
	/**
	 * The variable used to get the error count if 
	 * any error happen in execution of ETL code.
	 */
	private static final int  ERRORCOUNT=0;
	
	
	/**
	 * The method is used to set environment variable , 
	 * get values from properties file etc.
	 * @param filename
	 * @throws Exception
	 */
	public void runJob(final String filename) throws BPIETLException{
		
		try{
		final File file = new File("");
		String logpath = "";
		logpath = file.getCanonicalPath();
		final Properties dbResouce = new Properties();
		final String path = logpath +FilePathUtil.DATABASE_CONFIGURATION_FILE_NAME;
		dbResouce.load(new FileInputStream(path));
		LOGGER.info("The Database Configuration files exist in the path");
//    	final Properties mailResource = new Properties();
//		final String path1 = logpath + FilePathUtil.MAIL_CONFIGURATION_FILE_NAME;
//	    mailResource.load(new FileInputStream(path1));
	//	LOGGER.info("The Mail Configuration files exist in the path");
//		final Properties messageResource=new Properties();
//		final String path2 = logpath + FilePathUtil.CONTENT_CONFIGURATION_FILE_NAME;
//		messageResource.load(new FileInputStream(path2));
//		LOGGER.info("The Content Configuration files exist in the path");
		EtlEnvironment.init();
		final JobMeta jobMeta = new JobMeta(filename, null);
		final Job job = new Job(null, jobMeta);
//		final String encryptedpassword = mailResource
//				.getProperty("AUTHENTICATION_PASSWORD");
//		final String mailPassword = Encr
//				.decryptPassword(encryptedpassword);
		final String bpiPassword = Encr
				.decryptPassword(dbResouce.getProperty("BPI_PASSWORD"));
		final String stagingPassword = Encr
				.decryptPassword(dbResouce.getProperty("STAGING_PASSWORD"));
		final String sysPassword = Encr
				.decryptPassword(dbResouce.getProperty("BPI_SYS_PASSWORD"));
		final String fileNameExtension[]=getFileName(filename,dbResouce.getProperty("INBOUND_FILE_PATH"));
		//System.out.println("fileNameExtension "+fileNameExtension[0]);
		

			int index = fileNameExtension[0].indexOf(".");
			String[] fileArr = new String[2];
			if (index != -1) {
				fileArr[0] = fileNameExtension[0].substring(0, index);

				fileArr[1] = fileNameExtension[0].substring(index + 1,
						fileNameExtension[0].length());
			} else {
				fileArr[0] = fileNameExtension[0];
			}
			
		
		//System.out.println("Length-->>>>>>>>>>>>>>> "+fileArr.length);
		// Setting database variables
		job.setVariable("BPI_SYS_HOST", dbResouce.getProperty("BPI_SYS_HOST"));
		job.setVariable("BPI_SYS_DB", dbResouce.getProperty("BPI_SYS_DB"));
		job.setVariable("BPI_SYS_USER", dbResouce.getProperty("BPI_SYS_USER"));
		job.setVariable("BPI_SYS_PASSWORD", dbResouce.getProperty("BPI_SYS_PASSWORD"));		
		job.setVariable("FILE_LOCAL_PATH", dbResouce.getProperty("FILE_LOCAL_PATH"));
		job.setVariable("MAIL_FILE_PATH", dbResouce.getProperty("MAIL_FILE_PATH"));
		
		job.setVariable("OUTBOUND_PURGE_DAYS", dbResouce.getProperty("OUTBOUND_PURGE_DAYS"));
		job.setVariable("LOG_ZIP_DAYS", dbResouce.getProperty("LOG_ZIP_DAYS"));
		job.setVariable("ETL_LOG_PATH", dbResouce.getProperty("ETL_LOG_PATH"));
		
		job.setVariable("FILE_NAME_EXT", fileNameExtension[0]);
		job.setVariable("INTERFACE_PROCESS_NAME", fileNameExtension[1]);
		
		//job.setVariable("ORG_FILE_NAME", getFileNameWithDateFormat(fileArr, "ORG"));
		//job.setVariable("ERROR_FILE_NAME", getFileNameWithDateFormat(fileArr, "ERROR"));
	//	job.setVariable("REJECT_FILE_NAME", getFileNameWithDateFormat(fileArr, "REJECT"));
		
		job.setVariable("DELIMITER", dbResouce.getProperty("DELIMITER"));
		
		job.setVariable("INBOUND_FILE_PATH", dbResouce.getProperty("INBOUND_FILE_PATH"));
		job.setVariable("OUTBOUND_FILE_PATH", dbResouce.getProperty("OUTBOUND_FILE_PATH"));
		job.setVariable("SERVER_NAME", dbResouce.getProperty("SERVER_NAME"));
		job.setVariable("SERVER_PASSWORD", dbResouce.getProperty("SERVER_PASSWORD"));
		job.setVariable("SERVER_PORT", dbResouce.getProperty("SERVER_PORT"));
		job.setVariable("SERVER_USERNAME", dbResouce.getProperty("SERVER_USERNAME"));
		job.setVariable("BPI_HOST", dbResouce.getProperty("BPI_HOST"));
		job.setVariable("BPI_DB", dbResouce.getProperty("BPI_DB"));
		job.setVariable("BPI_USER", dbResouce.getProperty("BPI_USER"));
		job.setVariable("BPI_PASSWORD", bpiPassword);
		job.setVariable("STAGING_HOST", dbResouce.getProperty("STAGING_HOST"));
		job.setVariable("STAGING_DB", dbResouce.getProperty("STAGING_DB"));
		job.setVariable("STAGING_USER", dbResouce.getProperty("STAGING_USER"));
		job.setVariable("STAGING_PASSWORD", stagingPassword);
		job.setVariable("BPI_SYS_PASSWORD", sysPassword);
	//	System.out.println("JOB IN BETWEEN :");
		// Setting Mail variables
//		job.setVariable("MAIL_SERVER", mailResource.getProperty("MAIL_SERVER"));
//		job.setVariable("MAIL_PORT", mailResource.getProperty("MAIL_PORT"));
//		job.setVariable("DESTINATION_CC",
//				mailResource.getProperty("DESTINATION_CC"));
//		job.setVariable("DESTINATION_ADDRESS",
//				mailResource.getProperty("DESTINATION_ADDRESS"));
//		job.setVariable("SENDER_NAME", mailResource.getProperty("SENDER_NAME"));
//		job.setVariable("SENDER_ADDRESS",
//				mailResource.getProperty("SENDER_ADDRESS"));
//		job.setVariable("AUTHENTICATION_USER",
//				mailResource.getProperty("AUTHENTICATION_USER"));
//		job.setVariable("AUTHENTICATION_PASSWORD", mailPassword);
		//System.out.println("JOB IN BETWEEN :");
        // Setting the date variables
		job.setVariable("DATE",new SimpleDateFormat("yyyy-MM-dd HH:mm",Locale.getDefault()).format(new Date()));
		// Setting Content Configuration Variable
		
		 //Company Master Content Configuration START 

//       job.setVariable("FAILURE_COMPANY_MASTER_INTERFACE_SUBJECT", messageResource.getProperty("FAILURE_COMPANY_MASTER_INTERFACE_SUBJECT"));
//        job.setVariable("COMPANY_MASTER_INITIAL_PROCESS_FAIL", messageResource.getProperty("COMPANY_MASTER_INITIAL_PROCESS_FAIL"));
//        job.setVariable("COMPANY_MASTER_VALIDATION_FAILS", messageResource.getProperty("COMPANY_MASTER_VALIDATION_FAILS"));
//        job.setVariable("COMPANY_MASTER_TEMPTABLE_TRUNCATE_FAIL", messageResource.getProperty("COMPANY_MASTER_TEMPTABLE_TRUNCATE_FAIL"));
//        job.setVariable("COMPANY_MASTER_STAGINGTABLE_TRUNCATE_FAIL", messageResource.getProperty("COMPANY_MASTER_STAGINGTABLE_TRUNCATE_FAIL"));
//        job.setVariable("COMPANY_MASTER_SUCCESS_SUBJECT", messageResource.getProperty("COMPANY_MASTER_SUCCESS_SUBJECT"));
//        job.setVariable("COMPANY_MASTER_SUCCESS_MESSAGE", messageResource.getProperty("COMPANY_MASTER_SUCCESS_MESSAGE"));
        
        //Company Master Content Configuration END   
        

        //Company Identifier Content Configuration START 
        
//        job.setVariable("FAILURE_COMPANY_IDENTIFIER_INTERFACE_SUBJECT", messageResource.getProperty("FAILURE_COMPANY_IDENTIFIER_INTERFACE_SUBJECT"));
//        job.setVariable("COMPANY_IDENTIFIER_INITIAL_PROCESS_FAIL", messageResource.getProperty("COMPANY_IDENTIFIER_INITIAL_PROCESS_FAIL"));
//        job.setVariable("COMPANY_IDENTIFIER_VALIDATION_FAILS", messageResource.getProperty("COMPANY_IDENTIFIER_VALIDATION_FAILS"));
//        job.setVariable("COMPANY_IDENTIFIER_TEMPTABLE_TRUNCATE_FAIL", messageResource.getProperty("COMPANY_IDENTIFIER_TEMPTABLE_TRUNCATE_FAIL"));
//        job.setVariable("COMPANY_IDENTIFIER_STAGINGTABLE_TRUNCATE_FAIL", messageResource.getProperty("COMPANY_IDENTIFIER_STAGINGTABLE_TRUNCATE_FAIL"));
//        job.setVariable("COMPANY_IDENTIFIER_SUCCESS_SUBJECT", messageResource.getProperty("COMPANY_IDENTIFIER_SUCCESS_SUBJECT"));
//        job.setVariable("COMPANY_IDENTIFIER_SUCCESS_MESSAGE", messageResource.getProperty("COMPANY_IDENTIFIER_SUCCESS_MESSAGE"));
//        
        //Company Identifier Content Configuration END  
     
       //Item Master Content Configuration START 
//       job.setVariable("FAILURE_ITEM_MASTER_INTERFACE_SUBJECT", messageResource.getProperty("FAILURE_ITEM_MASTER_INTERFACE_SUBJECT"));
//       job.setVariable("ITEM_MASTER_INITIAL_PROCESS_FAIL", messageResource.getProperty("ITEM_MASTER_INITIAL_PROCESS_FAIL"));
//       job.setVariable("ITEM_MASTER_VALIDATION_FAILS", messageResource.getProperty("ITEM_MASTER_VALIDATION_FAILS"));
//       job.setVariable("ITEM_MASTER_TEMPTABLE_TRUNCATE_FAIL", messageResource.getProperty("ITEM_MASTER_TEMPTABLE_TRUNCATE_FAIL"));
//       job.setVariable("ITEM_MASTER_STAGINGTABLE_TRUNCATE_FAIL", messageResource.getProperty("ITEM_MASTER_STAGINGTABLE_TRUNCATE_FAIL"));
//       job.setVariable("ITEM_MASTER_SUCCESS_SUBJECT", messageResource.getProperty("ITEM_MASTER_SUCCESS_SUBJECT"));
//       job.setVariable("ITEM_MASTER_SUCCESS_MESSAGE", messageResource.getProperty("ITEM_MASTER_SUCCESS_MESSAGE"));
//       
         //Item Master Content Configuration END 
       
       //Item Identifier Content Configuration START 
//       job.setVariable("FAILURE_ITEM_IDENTIFIER_INTERFACE_SUBJECT", messageResource.getProperty("FAILURE_ITEM_IDENTIFIER_INTERFACE_SUBJECT"));
//       job.setVariable("ITEM_IDENTIFIER_INITIAL_PROCESS_FAIL", messageResource.getProperty("ITEM_IDENTIFIER_INITIAL_PROCESS_FAIL"));
//       job.setVariable("ITEM_IDENTIFIER_VALIDATION_FAILS", messageResource.getProperty("ITEM_IDENTIFIER_VALIDATION_FAILS"));
//       job.setVariable("ITEM_IDENTIFIER_TEMPTABLE_TRUNCATE_FAIL", messageResource.getProperty("ITEM_IDENTIFIER_TEMPTABLE_TRUNCATE_FAIL"));
//       job.setVariable("ITEM_IDENTIFIER_STAGINGTABLE_TRUNCATE_FAIL", messageResource.getProperty("ITEM_IDENTIFIER_STAGINGTABLE_TRUNCATE_FAIL"));
//       job.setVariable("ITEM_IDENTIFIER_SUCCESS_SUBJECT", messageResource.getProperty("ITEM_IDENTIFIER_SUCCESS_SUBJECT"));
//       job.setVariable("ITEM_IDENTIFIER_SUCCESS_MESSAGE", messageResource.getProperty("ITEM_IDENTIFIER_SUCCESS_MESSAGE"));
//          //Item Identifier Content Configuration END 
       
     //Item Pricing Content Configuration START 
//       job.setVariable("FAILURE_ITEM_PRICING_INTERFACE_SUBJECT", messageResource.getProperty("FAILURE_ITEM_PRICING_INTERFACE_SUBJECT"));
//       job.setVariable("ITEM_PRICING_INITIAL_PROCESS_FAIL", messageResource.getProperty("ITEM_PRICING_INITIAL_PROCESS_FAIL"));
//       job.setVariable("ITEM_PRICING_VALIDATION_FAILS", messageResource.getProperty("ITEM_PRICING_VALIDATION_FAILS"));
//       job.setVariable("ITEM_PRICING_TEMPTABLE_TRUNCATE_FAIL", messageResource.getProperty("ITEM_PRICING_TEMPTABLE_TRUNCATE_FAIL"));
//       job.setVariable("ITEM_PRICING_STAGINGTABLE_TRUNCATE_FAIL", messageResource.getProperty("ITEM_PRICING_STAGINGTABLE_TRUNCATE_FAIL"));
//       job.setVariable("ITEM_PRICING_SUCCESS_SUBJECT", messageResource.getProperty("ITEM_PRICING_SUCCESS_SUBJECT"));
//       job.setVariable("ITEM_PRICING_SUCCESS_MESSAGE", messageResource.getProperty("ITEM_PRICING_SUCCESS_MESSAGE"));
//      
  //Item Pricing Content Configuration END 
//       
//       //CONTRACT HEADER START
//       job.setVariable("FAILURE_CONTRACT_HEADER_INTERFACE_SUBJECT", messageResource.getProperty("FAILURE_CONTRACT_HEADER_INTERFACE_SUBJECT"));
//       job.setVariable("CONTRACT_HEADER_INITIAL_PROCESS_FAIL", messageResource.getProperty("CONTRACT_HEADER_INITIAL_PROCESS_FAIL"));
//       job.setVariable("CONTRACT_HEADER_VALIDATION_FAILS", messageResource.getProperty("CONTRACT_HEADER_VALIDATION_FAILS"));
//       job.setVariable("CONTRACT_HEADER_TEMPTABLE_TRUNCATE_FAIL", messageResource.getProperty("CONTRACT_HEADER_TEMPTABLE_TRUNCATE_FAIL"));
//       job.setVariable("CONTRACT_HEADER_STAGINGTABLE_TRUNCATE_FAIL", messageResource.getProperty("CONTRACT_HEADER_STAGINGTABLE_TRUNCATE_FAIL"));
//       job.setVariable("CONTRACT_HEADER_SUCCESS_SUBJECT", messageResource.getProperty("CONTRACT_HEADER_SUCCESS_SUBJECT"));
//       job.setVariable("CONTRACT_HEADER_SUCCESS_MESSAGE", messageResource.getProperty("CONTRACT_HEADER_SUCCESS_MESSAGE"));
//       //END
//       
//       // COMPANY FAMILY PLAN Configuration START
//       job.setVariable("CFP_SUCCESS_SUBJECT", messageResource.getProperty("CFP_SUCCESS_SUBJECT"));
//       job.setVariable("CFP_SUCCESS_MESSAGE", messageResource.getProperty("CFP_SUCCESS_MESSAGE"));
//       job.setVariable("FAILURE_CFP_INTERFACE_SUBJECT", messageResource.getProperty("FAILURE_CFP_INTERFACE_SUBJECT"));
//       job.setVariable("CFP_INITIAL_PROCESS_FAIL", messageResource.getProperty("CFP_INITIAL_PROCESS_FAIL"));
//       job.setVariable("CFP_VALIDATION_FAILS", messageResource.getProperty("CFP_VALIDATION_FAILS"));
//       job.setVariable("CFP_TEMPTABLE_TRUNCATE_FAIL", messageResource.getProperty("CFP_TEMPTABLE_TRUNCATE_FAIL"));
//       job.setVariable("CFP_STAGINGTABLE_TRUNCATE_FAIL", messageResource.getProperty("CFP_STAGINGTABLE_TRUNCATE_FAIL"));
//       // COMPANY FAMILY PLAN Configuration END
//       
//       //ITEM FAMILY PLAN Configuration START
//       job.setVariable("IFP_SUCCESS_SUBJECT", messageResource.getProperty("IFP_SUCCESS_SUBJECT"));
//       job.setVariable("IFP_SUCCESS_MESSAGE", messageResource.getProperty("IFP_SUCCESS_MESSAGE"));
//       job.setVariable("FAILURE_IFP_INTERFACE_SUBJECT", messageResource.getProperty("FAILURE_IFP_INTERFACE_SUBJECT"));
//       job.setVariable("IFP_INITIAL_PROCESS_FAIL", messageResource.getProperty("IFP_INITIAL_PROCESS_FAIL"));
//       job.setVariable("IFP_VALIDATION_FAILS", messageResource.getProperty("IFP_VALIDATION_FAILS"));
//       job.setVariable("IFP_TEMPTABLE_TRUNCATE_FAIL", messageResource.getProperty("IFP_TEMPTABLE_TRUNCATE_FAIL"));
//       job.setVariable("IFP_STAGINGTABLE_TRUNCATE_FAIL", messageResource.getProperty("IFP_STAGINGTABLE_TRUNCATE_FAIL"));
//     //ITEM FAMILY PLAN Configuration END
//       
//       //PRICE SCHEDULE configuration START
//       job.setVariable("PS_SUCCESS_SUBJECT", messageResource.getProperty("PS_SUCCESS_SUBJECT"));
//       job.setVariable("PS_SUCCESS_MESSAGE", messageResource.getProperty("PS_SUCCESS_MESSAGE"));
//       job.setVariable("FAILURE_PS_INTERFACE_SUBJECT", messageResource.getProperty("FAILURE_PS_INTERFACE_SUBJECT"));
//       job.setVariable("PS_INITIAL_PROCESS_FAIL", messageResource.getProperty("PS_INITIAL_PROCESS_FAIL"));
//       job.setVariable("PS_VALIDATION_FAILS", messageResource.getProperty("PS_VALIDATION_FAILS"));
//       job.setVariable("PS_TEMPTABLE_TRUNCATE_FAIL", messageResource.getProperty("PS_TEMPTABLE_TRUNCATE_FAIL"));
//       job.setVariable("PS_STAGINGTABLE_TRUNCATE_FAIL", messageResource.getProperty("PS_STAGINGTABLE_TRUNCATE_FAIL"));
//       //PRICE SCHEDULE configuration END
//       
//       //REBATE SCHEDULE Configuration Start
//       job.setVariable("RS_SUCCESS_SUBJECT", messageResource.getProperty("RS_SUCCESS_SUBJECT"));
//       job.setVariable("RS_SUCCESS_MESSAGE", messageResource.getProperty("RS_SUCCESS_MESSAGE"));
//       job.setVariable("FAILURE_RS_INTERFACE_SUBJECT", messageResource.getProperty("FAILURE_RS_INTERFACE_SUBJECT"));
//       job.setVariable("RS_INITIAL_PROCESS_FAIL", messageResource.getProperty("RS_INITIAL_PROCESS_FAIL"));
//       job.setVariable("RS_VALIDATION_FAILS", messageResource.getProperty("RS_VALIDATION_FAILS"));
//       job.setVariable("RS_TEMPTABLE_TRUNCATE_FAIL", messageResource.getProperty("RS_TEMPTABLE_TRUNCATE_FAIL"));
//       job.setVariable("RS_STAGINGTABLE_TRUNCATE_FAIL", messageResource.getProperty("RS_STAGINGTABLE_TRUNCATE_FAIL"));
//     //REBATE SCHEDULE Configuration END
//       
//       //REBATE PLAN START
//       job.setVariable("RP_SUCCESS_SUBJECT", messageResource.getProperty("RP_SUCCESS_SUBJECT"));
//       job.setVariable("RP_SUCCESS_MESSAGE", messageResource.getProperty("RP_SUCCESS_MESSAGE"));
//       job.setVariable("FAILURE_RP_INTERFACE_SUBJECT", messageResource.getProperty("FAILURE_RP_INTERFACE_SUBJECT"));
//       job.setVariable("RP_INITIAL_PROCESS_FAIL", messageResource.getProperty("RP_INITIAL_PROCESS_FAIL"));
//       job.setVariable("RP_VALIDATION_FAILS", messageResource.getProperty("RP_VALIDATION_FAILS"));
//       job.setVariable("RP_TEMPTABLE_TRUNCATE_FAIL", messageResource.getProperty("RP_TEMPTABLE_TRUNCATE_FAIL"));
//       job.setVariable("RP_STAGINGTABLE_TRUNCATE_FAIL", messageResource.getProperty("RP_STAGINGTABLE_TRUNCATE_FAIL"));
//       //END
//       
//       //ACTUAL START
//       job.setVariable("ACTUAL_MASTER_SUCCESS_SUBJECT", messageResource.getProperty("ACTUAL_MASTER_SUCCESS_SUBJECT"));
//       job.setVariable("ACTUAL_MASTER_SUCCESS_MESSAGE", messageResource.getProperty("ACTUAL_MASTER_SUCCESS_MESSAGE"));
//       job.setVariable("FAILURE_ACTUAL_MASTER_INTERFACE_SUBJECT", messageResource.getProperty("FAILURE_ACTUAL_MASTER_INTERFACE_SUBJECT"));
//       job.setVariable("ACTUAL_MASTER_INITIAL_PROCESS_FAIL", messageResource.getProperty("ACTUAL_MASTER_INITIAL_PROCESS_FAIL"));
//       job.setVariable("ACTUAL_MASTER_VALIDATION_FAILS", messageResource.getProperty("ACTUAL_MASTER_VALIDATION_FAILS"));
//       job.setVariable("ACTUAL_MASTER_TEMPTABLE_TRUNCATE_FAIL", messageResource.getProperty("ACTUAL_MASTER_TEMPTABLE_TRUNCATE_FAIL"));
//       job.setVariable("ACTUAL_MASTER_STAGINGTABLE_TRUNCATE_FAIL", messageResource.getProperty("ACTUAL_MASTER_STAGINGTABLE_TRUNCATE_FAIL"));
//       //END
//       
//       //ITEM HIERARCHY START
//       job.setVariable("ITEM_HIERARCHY_SUCCESS_SUBJECT", messageResource.getProperty("ITEM_HIERARCHY_SUCCESS_SUBJECT"));
//       job.setVariable("ITEM_HIERARCHY_SUCCESS_MESSAGE", messageResource.getProperty("ITEM_HIERARCHY_SUCCESS_MESSAGE"));
//       job.setVariable("FAILURE_ITEM_HIERARCHY_INTERFACE_SUBJECT", messageResource.getProperty("FAILURE_ITEM_HIERARCHY_INTERFACE_SUBJECT"));
//       job.setVariable("ITEM_HIERARCHY_INITIAL_PROCESS_FAIL", messageResource.getProperty("ITEM_HIERARCHY_INITIAL_PROCESS_FAIL"));
//       job.setVariable("ITEM_HIERARCHY_VALIDATION_FAILS", messageResource.getProperty("ITEM_HIERARCHY_VALIDATION_FAILS"));
//       job.setVariable("ITEM_HIERARCHY_TEMPTABLE_TRUNCATE_FAIL", messageResource.getProperty("ITEM_HIERARCHY_TEMPTABLE_TRUNCATE_FAIL"));
//       job.setVariable("ITEM_HIERARCHY_STAGINGTABLE_TRUNCATE_FAIL", messageResource.getProperty("ITEM_HIERARCHY_STAGINGTABLE_TRUNCATE_FAIL"));
//       //END
//       
//       //SALES START
//       job.setVariable("SALES_MASTER_SUCCESS_SUBJECT", messageResource.getProperty("SALES_MASTER_SUCCESS_SUBJECT"));
//       job.setVariable("SALES_MASTER_SUCCESS_MESSAGE", messageResource.getProperty("SALES_MASTER_SUCCESS_MESSAGE"));
//       job.setVariable("FAILURE_SALES_MASTER_INTERFACE_SUBJECT", messageResource.getProperty("FAILURE_SALES_MASTER_INTERFACE_SUBJECT"));
//       job.setVariable("SALES_MASTER_INITIAL_PROCESS_FAIL", messageResource.getProperty("SALES_MASTER_INITIAL_PROCESS_FAIL"));
//       job.setVariable("SALES_MASTER_VALIDATION_FAILS", messageResource.getProperty("SALES_MASTER_VALIDATION_FAILS"));
//       job.setVariable("SALES_MASTER_TEMPTABLE_TRUNCATE_FAIL", messageResource.getProperty("SALES_MASTER_TEMPTABLE_TRUNCATE_FAIL"));
//       job.setVariable("SALES_MASTER_STAGINGTABLE_TRUNCATE_FAIL", messageResource.getProperty("SALES_MASTER_STAGINGTABLE_TRUNCATE_FAIL"));
//       //END
//       
//       //FORECASTING START
//       job.setVariable("FORECASTING_MASTER_SUCCESS_SUBJECT", messageResource.getProperty("FORECASTING_MASTER_SUCCESS_SUBJECT"));
//       job.setVariable("FORECASTING_MASTER_SUCCESS_MESSAGE", messageResource.getProperty("FORECASTING_MASTER_SUCCESS_MESSAGE"));
//       job.setVariable("FAILURE_FORECASTING_MASTER_INTERFACE_SUBJECT", messageResource.getProperty("FAILURE_FORECASTING_MASTER_INTERFACE_SUBJECT"));
//       job.setVariable("FORECASTING_MASTER_INITIAL_PROCESS_FAIL", messageResource.getProperty("FORECASTING_MASTER_INITIAL_PROCESS_FAIL"));
//       job.setVariable("FORECASTING_MASTER_VALIDATION_FAILS", messageResource.getProperty("FORECASTING_MASTER_VALIDATION_FAILS"));
//       job.setVariable("FORECASTING_MASTER_TEMPTABLE_TRUNCATE_FAIL", messageResource.getProperty("FORECASTING_MASTER_TEMPTABLE_TRUNCATE_FAIL"));
//       job.setVariable("FORECASTING_MASTER_STAGINGTABLE_TRUNCATE_FAIL", messageResource.getProperty("FORECASTING_MASTER_STAGINGTABLE_TRUNCATE_FAIL"));
//       //END
//       
//       //GL BALANCE START
//       job.setVariable("GL_BALANCE_SUCCESS_SUBJECT", messageResource.getProperty("GL_BALANCE_SUCCESS_SUBJECT"));
//       job.setVariable("GL_BALANCE_SUCCESS_MESSAGE", messageResource.getProperty("GL_BALANCE_SUCCESS_MESSAGE"));
//       job.setVariable("FAILURE_GL_BALANCE_INTERFACE_SUBJECT", messageResource.getProperty("FAILURE_GL_BALANCE_INTERFACE_SUBJECT"));
//       job.setVariable("GL_BALANCE_INITIAL_PROCESS_FAIL", messageResource.getProperty("GL_BALANCE_INITIAL_PROCESS_FAIL"));
//       job.setVariable("GL_BALANCE_VALIDATION_FAILS", messageResource.getProperty("GL_BALANCE_VALIDATION_FAILS"));
//       job.setVariable("GL_BALANCE_TEMPTABLE_TRUNCATE_FAIL", messageResource.getProperty("GL_BALANCE_TEMPTABLE_TRUNCATE_FAIL"));
//       job.setVariable("GL_BALANCE_STAGINGTABLE_TRUNCATE_FAIL", messageResource.getProperty("GL_BALANCE_STAGINGTABLE_TRUNCATE_FAIL"));
//      // END
//       
//       //CPI INDEX START
//       job.setVariable("CPI_INDEX_SUCCESS_SUBJECT", messageResource.getProperty("CPI_INDEX_SUCCESS_SUBJECT"));
//       job.setVariable("CPI_INDEX_SUCCESS_MESSAGE", messageResource.getProperty("CPI_INDEX_SUCCESS_MESSAGE"));
//       job.setVariable("FAILURE_CPI_INDEX_INTERFACE_SUBJECT", messageResource.getProperty("FAILURE_CPI_INDEX_INTERFACE_SUBJECT"));
//       job.setVariable("CPI_INDEX_INITIAL_PROCESS_FAIL", messageResource.getProperty("CPI_INDEX_INITIAL_PROCESS_FAIL"));
//       job.setVariable("CPI_INDEX_VALIDATION_FAILS", messageResource.getProperty("CPI_INDEX_VALIDATION_FAILS"));
//       job.setVariable("CPI_INDEX_TEMPTABLE_TRUNCATE_FAIL", messageResource.getProperty("CPI_INDEX_TEMPTABLE_TRUNCATE_FAIL"));
//       job.setVariable("CPI_INDEX_STAGINGTABLE_TRUNCATE_FAIL", messageResource.getProperty("CPI_INDEX_STAGINGTABLE_TRUNCATE_FAIL"));
//       //END
//       
//       //FORMULA DETAILS START 
//       job.setVariable("FORMULA_DETAILS_SUCCESS_SUBJECT", messageResource.getProperty("FORMULA_DETAILS_SUCCESS_SUBJECT"));
//       job.setVariable("FORMULA_DETAILS_SUCCESS_MESSAGE", messageResource.getProperty("FORMULA_DETAILS_SUCCESS_MESSAGE"));
//       job.setVariable("FAILURE_FORMULA_DETAILS_INTERFACE_SUBJECT", messageResource.getProperty("FAILURE_FORMULA_DETAILS_INTERFACE_SUBJECT"));
//       job.setVariable("FORMULA_DETAILS_INITIAL_PROCESS_FAIL", messageResource.getProperty("FORMULA_DETAILS_INITIAL_PROCESS_FAIL"));
//       job.setVariable("FORMULA_DETAILS_VALIDATION_FAILS", messageResource.getProperty("FORMULA_DETAILS_VALIDATION_FAILS"));
//       job.setVariable("FORMULA_DETAILS_TEMPTABLE_TRUNCATE_FAIL", messageResource.getProperty("FORMULA_DETAILS_TEMPTABLE_TRUNCATE_FAIL"));
//       job.setVariable("FORMULA_DETAILS_STAGINGTABLE_TRUNCATE_FAIL", messageResource.getProperty("FORMULA_DETAILS_STAGINGTABLE_TRUNCATE_FAIL"));
//       //END
//       
//       //MASTER DATA START
//       job.setVariable("MASTER_DATA_ATTRB_SUCCESS_SUBJECT", messageResource.getProperty("MASTER_DATA_ATTRB_SUCCESS_SUBJECT"));
//       job.setVariable("MASTER_DATA_ATTRB_SUCCESS_MESSAGE", messageResource.getProperty("MASTER_DATA_ATTRB_SUCCESS_MESSAGE"));
//       job.setVariable("FAILURE_MASTER_DATA_INTERFACE_SUBJECT", messageResource.getProperty("FAILURE_MASTER_DATA_INTERFACE_SUBJECT"));
//       job.setVariable("MASTER_DATA_INITIAL_PROCESS_FAIL", messageResource.getProperty("MASTER_DATA_INITIAL_PROCESS_FAIL"));
//       job.setVariable("MASTER_DATA_VALIDATION_FAILS", messageResource.getProperty("MASTER_DATA_VALIDATION_FAILS"));
//       job.setVariable("MASTER_DATA_TEMPTABLE_TRUNCATE_FAIL", messageResource.getProperty("MASTER_DATA_TEMPTABLE_TRUNCATE_FAIL"));
//       job.setVariable("MASTER_DATA_STAGINGTABLE_TRUNCATE_FAIL", messageResource.getProperty("MASTER_DATA_STAGINGTABLE_TRUNCATE_FAIL"));
//       //END
//       
//       //ITEM HIERARCHY DEF START
//       job.setVariable("ITEM_HIERARCHY_DEF_SUCCESS_SUBJECT", messageResource.getProperty("ITEM_HIERARCHY_DEF_SUCCESS_SUBJECT"));
//       job.setVariable("ITEM_HIERARCHY_DEF_SUCCESS_MESSAGE", messageResource.getProperty("ITEM_HIERARCHY_DEF_SUCCESS_MESSAGE"));
//       job.setVariable("FAILURE_ITEM_HIERARCHY_DEF_INTERFACE_SUBJECT", messageResource.getProperty("FAILURE_ITEM_HIERARCHY_DEF_INTERFACE_SUBJECT"));
//       job.setVariable("ITEM_HIERARCHY_DEF_INITIAL_PROCESS_FAIL", messageResource.getProperty("ITEM_HIERARCHY_DEF_INITIAL_PROCESS_FAIL"));
//       job.setVariable("ITEM_HIERARCHY_DEF_VALIDATION_FAILS", messageResource.getProperty("ITEM_HIERARCHY_DEF_VALIDATION_FAILS"));
//       job.setVariable("ITEM_HIERARCHY_DEF_TEMPTABLE_TRUNCATE_FAIL", messageResource.getProperty("ITEM_HIERARCHY_DEF_TEMPTABLE_TRUNCATE_FAIL"));
//       job.setVariable("ITEM_HIERARCHY_DEF_STAGINGTABLE_TRUNCATE_FAIL", messageResource.getProperty("ITEM_HIERARCHY_DEF_STAGINGTABLE_TRUNCATE_FAIL"));
//       //END
//       
//       //BEST PRICE START
//       job.setVariable("BESTPRICE_MASTER_SUCCESS_SUBJECT", messageResource.getProperty("BESTPRICE_MASTER_SUCCESS_SUBJECT"));
//       job.setVariable("BESTPRICE_MASTER_SUCCESS_MESSAGE", messageResource.getProperty("BESTPRICE_MASTER_SUCCESS_MESSAGE"));
//       job.setVariable("FAILURE_BESTPRICE_MASTER_INTERFACE_SUBJECT", messageResource.getProperty("FAILURE_BESTPRICE_MASTER_INTERFACE_SUBJECT"));
//       job.setVariable("BESTPRICE_MASTER_INITIAL_PROCESS_FAIL", messageResource.getProperty("BESTPRICE_MASTER_INITIAL_PROCESS_FAIL"));
//       job.setVariable("BESTPRICE_MASTER_VALIDATION_FAILS", messageResource.getProperty("BESTPRICE_MASTER_VALIDATION_FAILS"));
//       job.setVariable("BESTPRICE_MASTER_TEMPTABLE_TRUNCATE_FAIL", messageResource.getProperty("BESTPRICE_MASTER_TEMPTABLE_TRUNCATE_FAIL"));
//       job.setVariable("BESTPRICE_MASTER_STAGINGTABLE_TRUNCATE_FAIL", messageResource.getProperty("BESTPRICE_MASTER_STAGINGTABLE_TRUNCATE_FAIL"));
//       //END
//       
//       //GL COST CENTER INTERFACE START
//       job.setVariable("GL_COST_CENTER_SUCCESS_SUBJECT", messageResource.getProperty("GL_COST_CENTER_SUCCESS_SUBJECT"));
//       job.setVariable("GL_COST_CENTER_SUCCESS_MESSAGE", messageResource.getProperty("GL_COST_CENTER_SUCCESS_MESSAGE"));
//       job.setVariable("FAILURE_GL_COST_CENTER_INTERFACE_SUBJECT", messageResource.getProperty("FAILURE_GL_COST_CENTER_INTERFACE_SUBJECT"));
//       job.setVariable("GL_COST_CENTER_INITIAL_PROCESS_FAIL", messageResource.getProperty("GL_COST_CENTER_INITIAL_PROCESS_FAIL"));
//       job.setVariable("GL_COST_CENTER_VALIDATION_FAILS", messageResource.getProperty("GL_COST_CENTER_VALIDATION_FAILS"));
//       job.setVariable("GL_COST_CENTER_TEMPTABLE_TRUNCATE_FAIL", messageResource.getProperty("GL_COST_CENTER_TEMPTABLE_TRUNCATE_FAIL"));
//       job.setVariable("GL_COST_CENTER_STAGINGTABLE_TRUNCATE_FAIL", messageResource.getProperty("GL_COST_CENTER_STAGINGTABLE_TRUNCATE_FAIL"));
//       //END
//       
//       //LOT MASTER INTERFACE START
//       job.setVariable("LOT_MASTER_SUCCESS_SUBJECT", messageResource.getProperty("LOT_MASTER_SUCCESS_SUBJECT"));
//       job.setVariable("LOT_MASTER_SUCCESS_MESSAGE", messageResource.getProperty("LOT_MASTER_SUCCESS_MESSAGE"));
//       job.setVariable("FAILURE_LOT_MASTER_INTERFACE_SUBJECT", messageResource.getProperty("FAILURE_LOT_MASTER_INTERFACE_SUBJECT"));
//       job.setVariable("LOT_MASTER_INITIAL_PROCESS_FAIL", messageResource.getProperty("LOT_MASTER_INITIAL_PROCESS_FAIL"));
//       job.setVariable("LOT_MASTER_VALIDATION_FAILS", messageResource.getProperty("LOT_MASTER_VALIDATION_FAILS"));
//       job.setVariable("LOT_MASTER_TEMPTABLE_TRUNCATE_FAIL", messageResource.getProperty("LOT_MASTER_TEMPTABLE_TRUNCATE_FAIL"));
//       job.setVariable("LOT_MASTER_STAGINGTABLE_TRUNCATE_FAIL", messageResource.getProperty("LOT_MASTER_STAGINGTABLE_TRUNCATE_FAIL"));
//       //END
//       
//       //AVERAGE SHELF LIFE START
//       job.setVariable("AVERAGE_SHELF_LIFE_SUCCESS_SUBJECT", messageResource.getProperty("AVERAGE_SHELF_LIFE_SUCCESS_SUBJECT"));
//       job.setVariable("AVERAGE_SHELF_LIFE_SUCCESS_MESSAGE", messageResource.getProperty("AVERAGE_SHELF_LIFE_SUCCESS_MESSAGE"));
//       job.setVariable("FAILURE_AVERAGE_SHELF_LIFE_INTERFACE_SUBJECT", messageResource.getProperty("FAILURE_AVERAGE_SHELF_LIFE_INTERFACE_SUBJECT"));
//       job.setVariable("AVERAGE_SHELF_LIFE_INITIAL_PROCESS_FAIL", messageResource.getProperty("AVERAGE_SHELF_LIFE_INITIAL_PROCESS_FAIL"));
//       job.setVariable("AVERAGE_SHELF_LIFE_VALIDATION_FAILS", messageResource.getProperty("AVERAGE_SHELF_LIFE_VALIDATION_FAILS"));
//       job.setVariable("AVERAGE_SHELF_LIFE_TEMPTABLE_TRUNCATE_FAIL", messageResource.getProperty("AVERAGE_SHELF_LIFE_TEMPTABLE_TRUNCATE_FAIL"));
//       job.setVariable("AVERAGE_SHELF_LIFE_STAGINGTABLE_TRUNCATE_FAIL", messageResource.getProperty("AVERAGE_SHELF_LIFE_STAGINGTABLE_TRUNCATE_FAIL"));
//       //END
//       //COMPANY PARENT DETAILS START
//       job.setVariable("COMPANY_PARENT_SUCCESS_SUBJECT", messageResource.getProperty("COMPANY_PARENT_SUCCESS_SUBJECT"));
//       job.setVariable("COMPANY_PARENT_SUCCESS_MESSAGE", messageResource.getProperty("COMPANY_PARENT_SUCCESS_MESSAGE"));
//       job.setVariable("FAILURE_COMPANY_PARENT_INTERFACE_SUBJECT", messageResource.getProperty("FAILURE_COMPANY_PARENT_INTERFACE_SUBJECT"));
//       job.setVariable("COMPANY_PARENT_INITIAL_PROCESS_FAIL", messageResource.getProperty("COMPANY_PARENT_INITIAL_PROCESS_FAIL"));
//       job.setVariable("COMPANY_PARENT_VALIDATION_FAILS", messageResource.getProperty("COMPANY_PARENT_VALIDATION_FAILS"));
//       job.setVariable("COMPANY_PARENT_TEMPTABLE_TRUNCATE_FAIL", messageResource.getProperty("COMPANY_PARENT_TEMPTABLE_TRUNCATE_FAIL"));
//       job.setVariable("COMPANY_PARENT_STAGINGTABLE_TRUNCATE_FAIL", messageResource.getProperty("COMPANY_PARENT_STAGINGTABLE_TRUNCATE_FAIL"));
//		//END
//       
//     //COMPANY TRADE CLASS START
//       job.setVariable("COMPANY_TRADECLASS_SUCCESS_SUBJECT", messageResource.getProperty("COMPANY_TRADECLASS_SUCCESS_SUBJECT"));
//       job.setVariable("COMPANY_TRADECLASS_SUCCESS_MESSAGE", messageResource.getProperty("COMPANY_TRADECLASS_SUCCESS_MESSAGE"));
//       job.setVariable("FAILURE_COMPANY_TRADECLASS_INTERFACE_SUBJECT", messageResource.getProperty("FAILURE_COMPANY_TRADECLASS_INTERFACE_SUBJECT"));
//       job.setVariable("COMPANY_TRADECLASS_INITIAL_PROCESS_FAIL", messageResource.getProperty("COMPANY_TRADECLASS_INITIAL_PROCESS_FAIL"));
//       job.setVariable("COMPANY_TRADECLASS_VALIDATION_FAILS", messageResource.getProperty("COMPANY_TRADECLASS_VALIDATION_FAILS"));
//       job.setVariable("COMPANY_TRADECLASS_TEMPTABLE_TRUNCATE_FAIL", messageResource.getProperty("COMPANY_TRADECLASS_TEMPTABLE_TRUNCATE_FAIL"));
//       job.setVariable("COMPANY_TRADECLASS_STAGINGTABLE_TRUNCATE_FAIL", messageResource.getProperty("COMPANY_TRADECLASS_STAGINGTABLE_TRUNCATE_FAIL"));
//		//END
//		
		job.initializeVariablesFrom(null);
		job.getJobMeta().setInternalKettleVariables(job);

		job.getJobMeta().getLogTables();
		job.execute(0, null);
		job.waitUntilFinished();
		
		
	//	System.out.println("job.getErrors()---"+job.getErrors());
//		System.out.println("job.getErrors()---"+job.getAllStackTraces());
//		job.
	    if (job.getErrors() > ERRORCOUNT) {
	    //	System.out.println("getting error while executing job :"+job.getErrors());
			LOGGER.error("There were errors during job execution.");
			LOGGER.info("The Email with error notification has been sent successfully");
//			CommonUtils.createlog();
//			CommonUtils.createErrorlog();
////		//	LOGGER.info("The log file has been created.");
//			CommonUtils.cleartemp();
//			LOGGER.info("Ends "+fileNameExtension[1].toLowerCase());
//			System.out.print("Ends "+fileNameExtension[1].toLowerCase());
			throw new BPIETLException(fileNameExtension[1].toLowerCase());
//			
			
		}else{
			LOGGER.info(fileNameExtension[1].toLowerCase()+" executed successfully ");
			LOGGER.info("The Email notification has been sent successfully ");
			 
		}
	    LOGGER.info("Ends "+fileNameExtension[1].toLowerCase());
	    System.out.print("Ends "+fileNameExtension[1].toLowerCase());
	    
	}
		catch (EtlException ex) {
			LOGGER.error(ex.getMessage());
			ex.printStackTrace();
			throw new BPIETLException(ex);
		}
		catch ( FileNotFoundException ex) {
			LOGGER.error(ex.getMessage());
			ex.printStackTrace();
			throw new BPIETLException(ex);
		}
		catch (IOException ex) {
			LOGGER.error(ex.getMessage());
			ex.printStackTrace();
			throw new BPIETLException(ex);
		}
	//	System.out.println("JOB IS ENDING ");
		
	}
	private String[] getFileName(String interfaceName,String folderPath){
		String []fileName=new String[2];
		try{
	
		//System.out.println("Enters interface: "+interfaceName);
		String subKey[]=interfaceName.split("/");
		String interface1=subKey[subKey.length-2];
		//System.out.println(interface1);
		interfaceName=interfaceName.substring(interfaceName.indexOf('/')+1,interfaceName.lastIndexOf('/'));
		String folderName=folderPath+"/"+FileNameUtil.getMap().get(subKey[subKey.length-2])+"/Input";
		
//		 fileName=;
		System.out.println("Enters Interface:"+folderName);
		fileName[0] = getFolderWithFile(folderName);
		fileName[1] = FileNameUtil.getInterfaceName().get(interface1);
				
		}catch(Exception e){
			e.printStackTrace();
		}
		return fileName; 
	}
	
	private String getFolderWithFile(String folderName) {
		String fileName="";
		File dir =new File(folderName);
		if(dir.isDirectory()){
			File[] files= dir.listFiles();
			for (int i = 0; i < files.length; i++) {
				File file = files[i];
				fileName=file.getName();
			}
			//System.out.println(Arrays.toString(dir.listFiles()));
		}
	//	System.out.println("File Name :"+fileName);
		
		return fileName; 
	}

	
	/**
	 * used to get the data in specified format
	 * 
	 * @param format
	 * @return
	 */
	private String getDateFormat(final String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date d = new Date();
		return sdf.format(d);
	}
	
	/**
	 * USED TO APPEND THE FILEnAME WITH DATE
	 * 
	 * @param fileName
	 * @param appendValue
	 * @return
	 */
	private String getFileNameWithDateFormat(final String[] fileName,final String appendValue) {
	//	System.out.println("Length-->>>>>>>>>>>>>>>4 "+fileName.length);
		
		StringBuilder builder = new StringBuilder();
		builder.append(fileName[0]).append("_").append(getDateFormat("yyyyMMdd"))
			.append("_").append(getDateFormat("HHmmssSSS")).append("-").append(appendValue);
	//	System.out.println("new name "+fileName[1]);
		if(fileName.length==2){
			if(fileName[1]!= null){
				builder.append(".").append(fileName[1]);
			}
			
		}
				
		return builder.toString();
	}

}
