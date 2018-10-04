/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.processscheduler.logic;

import static com.stpl.app.adminconsole.processscheduler.logic.ManualLogic.COLUMN_NAME;
import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.common.util.AbstractFilterLogic;
import com.stpl.app.adminconsole.dao.CommonDAO;
import com.stpl.app.adminconsole.dao.impl.CommonDAOImpl;
import com.stpl.app.adminconsole.processscheduler.dto.FtpProperties;
import com.stpl.app.adminconsole.processscheduler.dto.ProcessSchedulerDTO;
import com.stpl.app.adminconsole.processscheduler.util.CommonUtil;
import com.stpl.app.adminconsole.quartz.QuartzListener;
import com.stpl.app.adminconsole.util.CommonUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.GtnWsCallEtlService;
import com.stpl.app.adminconsole.util.StringConstantUtils;
import com.stpl.app.adminconsole.util.xmlparser.SQlUtil;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.WorkflowProfile;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.WorkflowProfileLocalServiceUtil;
import com.stpl.app.util.service.ArmSchedulerSynchronizer;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.GtnFileUtil;
import com.stpl.ifs.util.HelperDTO;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.stpl.app.service.HierarchyDefinitionLocalServiceUtil;

/**
 *
 * @author Jayaram
 */
public class ProcessSchedulerLogic {

	private static CommonDAO dao = new CommonDAOImpl();
	/**
	 * The Constant LOGGER.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessSchedulerLogic.class);
	public static final  String QUOTE = "\"";
	public static final  String FTP_PROPERTIES_PATH = "conf/BPI Configuration/FTPConfiguration.properties";
        public static final  String ETL_PROPERTIES_PATH = "etl/Interface_Job/EtlConfiguration.properties";
	public static final String JBOSS_HOME_FOLDER="jboss-7.1.1";

	public List getSearchResult(boolean count, int start, int offset, boolean scheduler,
			final List<SortByColumn> orderByColumns) {
		LOGGER.debug("Entering getSearchResult");
		try {
			boolean asc = false;
			String columnName;
			String dbColumnName = StringUtils.EMPTY;
			loadDbColumnName();
			if (!count) {
				for (final Iterator<SortByColumn> iterator = orderByColumns.iterator(); iterator.hasNext();) {
					final SortByColumn orderByColumn =  iterator.next();

					columnName = orderByColumn.getName();
					dbColumnName = getDBColumnName(columnName);
					if (orderByColumn.getType() == SortByColumn.Type.ASC) {
						asc = false;
					} else {
						asc = true;
					}
				}
			}
			String query = CommonUtil.workFlowQuery(start, offset, count, scheduler, dbColumnName, asc);
			LOGGER.debug("schedule query = {}" , query);
			List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
			if (count) {
				return list;
			} else {
				return getCustomizedSchedulerProcessing(list);
			}
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
			return Collections.emptyList();
		}
	}

	public static String getDBColumnName(String visibleColumnName) {
		return COLUMN_NAME.get(visibleColumnName);
	}

	public static Map<String, String> loadDbColumnName() {
		COLUMN_NAME.put("processDisplayName", "PROCESS_NAME");
		COLUMN_NAME.put("status", "ACTIVE_FLAG");
		COLUMN_NAME.put("startDate", "START_DATE");
		COLUMN_NAME.put("endDate", "END_DATE");
		COLUMN_NAME.put("frequencyRadio", "FREQUENCY");
		COLUMN_NAME.put("scheduleLastRun", "SCHEDULE_LAST_RUN");
		COLUMN_NAME.put("modifiedDate", "MODIFIED_DATE");
		COLUMN_NAME.put("modifiedBy", "USR.screenName");
		return COLUMN_NAME;
	}

	private List getCustomizedSchedulerProcessing(List list) throws SystemException {
		List<ProcessSchedulerDTO> returnList = new ArrayList<>();
		SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		SimpleDateFormat date1 = new SimpleDateFormat("MM/dd/yyyy");
		final HashMap<String, String> userInfoMap = (HashMap<String, String>) com.stpl.app.adminconsole.common.util.CommonUtil
				.getCreatedByUser();
		if (list != null && !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				ProcessSchedulerDTO dto = new ProcessSchedulerDTO();
				dto.setProcessSid(Integer.valueOf(String.valueOf(obj[0])));
				LOGGER.debug("obj[1]= {}" , obj[1]);
				dto.setProcessName(String.valueOf(obj[1]));
				dto.setStatus("Y".equals(String.valueOf(obj[NumericConstants.TWO])) ? ACTIVE_LABEL
						: "N".equals(String.valueOf(obj[NumericConstants.TWO])) ? "Inactive" : StringUtils.EMPTY);

				if (obj[NumericConstants.THREE] != null
						&& !ConstantsUtils.EMPTY.equals(String.valueOf(obj[NumericConstants.THREE]))
						&& !"null".equals(String.valueOf(obj[NumericConstants.THREE]))) {
					dto.setStartDate(new Date(date.format(obj[NumericConstants.THREE])));
				}
				if (obj[NumericConstants.FOUR] != null
						&& !ConstantsUtils.EMPTY.equals(String.valueOf(obj[NumericConstants.FOUR]))
						&& !"null".equals(String.valueOf(obj[NumericConstants.FOUR]))) {
					dto.setEndDate(new Date(date.format(obj[NumericConstants.FOUR])));
				}

				dto.setFrequencyRadio(String.valueOf(obj[NumericConstants.FIVE]));
				if (obj[NumericConstants.TEN] != null) {
					dto.setScheduleLastRun(date.format(obj[NumericConstants.TEN]));
				}
				if (obj[NumericConstants.SEVEN] != null) {
					dto.setModifiedDate(new Date(date1.format(obj[NumericConstants.SEVEN])));
				}
				dto.setModifiedBy(userInfoMap.get(String.valueOf(obj[NumericConstants.EIGHT])));
				dto.setProcessDisplayName(String.valueOf(obj[NumericConstants.NINE]));
				returnList.add(dto);
			}
		}

		LOGGER.debug("Ending getCustomizedSchedulerProcessing= {}" , returnList.size());
		return returnList;
	}

	public static final String ACTIVE_LABEL = "Active";

	public ProcessSchedulerDTO getProcessScheduleByID(int sid) {
		try {
			LOGGER.debug("Entering getProcessScheduleByID");
			ProcessSchedulerDTO dto = new ProcessSchedulerDTO();
			WorkflowProfile profile = WorkflowProfileLocalServiceUtil.getWorkflowProfile(sid);
			dto.setProcessSid(profile.getProcessSid());
			dto.setProcessName(profile.getProcessName());
			dto.setProcessDisplayName(profile.getProcessDisplayName());
			dto.setFrequencyRadio(profile.getFrequency());
			dto.setStatus("Y".equals(profile.getActiveFlag()) ? ACTIVE_LABEL
					: "N".equals(profile.getActiveFlag()) ? "Inactive" : StringUtils.EMPTY);

			if ("Interval".equals(dto.getFrequencyRadio())) {
				dto.setRunHours(String.valueOf(profile.getStartHour()));
				dto.setRunMinutes(String.valueOf(profile.getStartMinutes()));
			} else {
				dto.setHoursOne(String.valueOf(profile.getStartHour1()));
				dto.setMinutesOne(String.valueOf(profile.getStartMinutes1()));
				dto.setHoursTwo(String.valueOf(profile.getStartHour2()));
				dto.setMinutesTwo(String.valueOf(profile.getStartMinutes2()));
				dto.setHoursThree(String.valueOf(profile.getStartHour3()));
				dto.setMinutesThree(String.valueOf(profile.getStartMinutes3()));
			}
			dto.setStartDate(profile.getStartDate());
			dto.setEndDate(profile.getEndDate());
			LOGGER.debug("Ending getProcessScheduleByID");
			return dto;
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
			return null;
		}
	}

	public static void update(ProcessSchedulerDTO processDTO) {
		try {
			LOGGER.debug("Entering update");
			if (processDTO.getProcessSid() != 0) {

				WorkflowProfile profile = WorkflowProfileLocalServiceUtil
						.getWorkflowProfile(processDTO.getProcessSid());

				profile.setProcessName(processDTO.getProcessName());
				profile.setProcessDisplayName(processDTO.getProcessDisplayName());
				profile.setFrequency(processDTO.getFrequencyRadio());
				if (ACTIVE_LABEL.equals(processDTO.getStatus())) {
					profile.setActiveFlag("Y");
				} else {
					profile.setActiveFlag("N");
				}
				if ("Interval".equals(processDTO.getFrequencyRadio())) {
					profile.setStartHour(processDTO.getRunHours() == null || "null".equals(processDTO.getRunHours())
							? NumericConstants.TWENTY_FOUR : Integer.parseInt(processDTO.getRunHours()));
					profile.setStartMinutes(
							processDTO.getRunMinutes() == null || "null".equals(processDTO.getRunMinutes())
									? NumericConstants.SIXTY : Integer.parseInt(processDTO.getRunMinutes()));
					profile.setStartHour1(NumericConstants.TWENTY_FOUR);
					profile.setStartHour2(NumericConstants.TWENTY_FOUR);
					profile.setStartHour3(NumericConstants.TWENTY_FOUR);
					profile.setStartMinutes1(NumericConstants.SIXTY);
					profile.setStartMinutes2(NumericConstants.SIXTY);
					profile.setStartMinutes3(NumericConstants.SIXTY);

				} else {
					profile.setStartHour1(processDTO.getHoursOne() == null || "null".equals(processDTO.getHoursOne())
							? NumericConstants.TWENTY_FOUR : Integer.parseInt(processDTO.getHoursOne()));
					profile.setStartHour2(processDTO.getHoursTwo() == null || "null".equals(processDTO.getHoursTwo())
							? NumericConstants.TWENTY_FOUR : Integer.parseInt(processDTO.getHoursTwo()));
					profile.setStartHour3(
							processDTO.getHoursThree() == null || "null".equals(processDTO.getHoursThree())
									? NumericConstants.TWENTY_FOUR : Integer.parseInt(processDTO.getHoursThree()));
					profile.setStartMinutes1(
							processDTO.getMinutesOne() == null || "null".equals(processDTO.getMinutesOne())
									? NumericConstants.SIXTY : Integer.parseInt(processDTO.getMinutesOne()));
					profile.setStartMinutes2(
							processDTO.getMinutesTwo() == null || "null".equals(processDTO.getMinutesTwo())
									? NumericConstants.SIXTY : Integer.parseInt(processDTO.getMinutesTwo()));
					profile.setStartMinutes3(
							processDTO.getMinutesThree() == null || "null".equals(processDTO.getMinutesThree())
									? NumericConstants.SIXTY : Integer.parseInt(processDTO.getMinutesThree()));
					profile.setStartHour(NumericConstants.TWENTY_FOUR);
					profile.setStartMinutes(NumericConstants.SIXTY);
				}
				profile.setStartDate(processDTO.getStartDate());
				profile.setEndDate(processDTO.getEndDate());
				profile.setModifiedBy(Integer.parseInt(String.valueOf(profile.getModifiedBy())));
				profile.setModifiedDate(new Date());
				WorkflowProfileLocalServiceUtil.updateWorkflowProfile(profile);

				QuartzListener.createQuartzScheduler();
			}
			LOGGER.debug("Ending update");
		} catch (Exception ex) {

			LOGGER.error(ex.getMessage());
		}
	}

	public void runJob(FtpProperties ftpProperties, String scriptName) {
		try {
			LOGGER.debug("Script Name= {}" , scriptName);
			String jbossHome=getJbossHome();
			LOGGER.debug("jboss Home= {}" , jbossHome);
			if (!StringUtils.isBlank(jbossHome)) {
					java.util.Properties prop =getPropertyFile(getPropertyPath());
				    java.util.Properties prop1 = getPropertyFile(jbossHome.concat("/../").concat(prop.getProperty("EtlConfiguration.properties")));
					String etlInterfaceUri=buildUrl(scriptName,prop1);
					ftpProperties.setScripts(prop1.getProperty("scripts"));
					runShellScript(etlInterfaceUri);
			}
			LOGGER.debug("runShellScript===================>ends1");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		LOGGER.debug("runJob ends");
	}

	public void runShellScript(String scriptUrl) {
		LOGGER.info("Entering runShellScript ");
		GtnWsCallEtlService  etlService=new GtnWsCallEtlService();
		etlService.runShellScript(scriptUrl);
	}

	public static FtpProperties getFtpBundleValue() {
		LOGGER.debug("getFtpBundleValue===================>starts");
		FtpProperties ftpProperties = new FtpProperties();
		try {
			String jbossHome=getJbossHome();
			if (!StringUtils.isBlank(jbossHome)) {
					java.util.Properties prop =getPropertyFile(getPropertyPath());
					java.util.Properties prop1 = getPropertyFile(jbossHome.concat("/../").concat(prop.getProperty("EtlConfiguration.properties")));
					
					ftpProperties.setScripts(prop1.getProperty("scripts"));
			}
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
		LOGGER.debug("getFtpBundleValue===================>ends");
		return ftpProperties;
	}

	public static java.util.Properties getPropertyFile(String bpiPropLoc) {
		LOGGER.debug("getPropertyFile===================>starts");
		java.util.Properties prop = new java.util.Properties();
		try {
			FileInputStream fileIS = null;
			fileIS = GtnFileUtil.getFileInputStream(bpiPropLoc);
			prop.load(fileIS);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
		LOGGER.debug("getPropertyFile===================>ends");
		return prop;

	}

	public void updateLastRun(Integer processId, boolean schedulerFlag) {
		LOGGER.debug("Entering updateLastRun");
		if (processId != 0) {

			try {
				WorkflowProfile profile = WorkflowProfileLocalServiceUtil.getWorkflowProfile(processId);
				if (!schedulerFlag) {
					profile.setManualLastRun(new Date());
				} else {
					profile.setScheduleLastRun(new Date());
				}
				WorkflowProfileLocalServiceUtil.updateWorkflowProfile(profile);

			} catch (PortalException ex) {
				LOGGER.error(ex.getMessage());
			} 

			LOGGER.debug("ends updateLastRun");
		}
	}

	public int getSearchCount(final ProcessSchedulerDTO binderDto, SessionDTO sessionDTO) {
		int count = 0;
		List resultCountList;
		String countQuery = SQlUtil.getQuery("getCffSearchCount");
		countQuery = countQuery.replace("@USER_ID", sessionDTO.getUserId());
		countQuery = countQuery.replace("@SESSION_ID", sessionDTO.getSessionId());
		String filterQuery = AbstractFilterLogic.getAdminInstance()
				.filterQueryGenerator(binderDto.getFilters(), getFilterMap()).toString();
		if (filterQuery != null) {
			filterQuery = filterQuery.replace("where", "AND");
			countQuery = countQuery.replace(FILTERQUERY, filterQuery);
		}
		resultCountList = (List) HelperTableLocalServiceUtil.executeSelectQuery(countQuery);
		count = getCount(resultCountList);
		LOGGER.debug("Count For method= {}" , count);
		return count;
	}

	public static final String FILTERQUERY = "@FILTERQUERY";

	/**
	 * Gets the search results.
	 * 
	 * @param binderDto
	 * @param sessionDTO
	 * @param isCheckAll
	 * @return
	 * @throws Exception
	 */
	public List<ProcessSchedulerDTO> getSearchResults(final ProcessSchedulerDTO binderDto,
			final SessionDTO sessionDTO) {
		LOGGER.debug("Inside Search Results");
		String filterQuery = AbstractFilterLogic.getAdminInstance()
				.filterQueryGenerator(binderDto.getFilters(), getFilterMap()).toString();
		String orderBy = AbstractFilterLogic.getAdminInstance()
				.orderByQueryGenerator(binderDto.getOrderByColumns(), getFilterMap(), "CFF_OUTBOUND").toString();
		List<ProcessSchedulerDTO> cffMasterList;
		List<Object[]> resultList;
		String searchQuery = SQlUtil.getQuery("searchQuery");
		searchQuery = searchQuery.replace("@USER_ID", sessionDTO.getUserId());
		searchQuery = searchQuery.replace("@SESSION_ID", sessionDTO.getSessionId());
		searchQuery = searchQuery.replace("@START_INDEX", StringUtils.EMPTY + binderDto.getStartIndex());
		searchQuery = searchQuery.replace("@END_INDEX", StringUtils.EMPTY + binderDto.getEndIndex());
		if (filterQuery != null) {
			filterQuery = filterQuery.replace("where", "AND");
			searchQuery = searchQuery.replace(FILTERQUERY, filterQuery);
		} else {
			searchQuery = searchQuery.replace(FILTERQUERY, "");
		}
		if (orderBy != null) {
			searchQuery = searchQuery.replace("@ORDER_BY", orderBy);
		}
		resultList = (List) HelperTableLocalServiceUtil.executeSelectQuery(searchQuery);
		cffMasterList = getCustomizedSearchResults(resultList);
		return cffMasterList;
	}

	/**
	 * Filter map
	 * 
	 * @return
	 */
	private Map<String, String> getFilterMap() {
		Map<String, String> filterMap = new HashMap<>(NumericConstants.SIXTY);
		filterMap.put(StringConstantUtils.FINANCIAL_FORECAST_ID, "FINANCIAL_FORECAST_ID");
		filterMap.put(StringConstantUtils.FINANCIAL_FORECAST_NAME, "FINANCIAL_FORECAST_NAME");
		filterMap.put("typeDesc", "TYPE");
		filterMap.put("projectID", "PROJECT_ID");
		filterMap.put(StringConstantUtils.PROJECTION_NAME, "PROJECTION_NAME");
		filterMap.put("year", "YEAR");
		filterMap.put("month", "MONTH");
		filterMap.put("contractId", "CONTRACT_ID");
		filterMap.put(StringConstantUtils.CONTRACT_NO, "CONTRACT_NO");
		filterMap.put(StringConstantUtils.CONTRACT_NAME, "CONTRACT_NAME");
		filterMap.put("contractType", "CONTRACT_TYPE");
		filterMap.put("contractHolderId", "CONTRACT_HOLDER_ID");
		filterMap.put("contractHolderNo", "CONTRACT_HOLDER_NO");
		filterMap.put("contractHolderName", "CONTRACT_HOLDER_NAME");
		filterMap.put("customerId", "CUSTOMER_ID");
		filterMap.put(StringConstantUtils.CUSTOMER_NO, "CUSTOMER_NO");
		filterMap.put(StringConstantUtils.CUSTOMER_NAME, "CUSTOMER_NAME");
		filterMap.put("itemId", "ITEM_ID");
		filterMap.put(StringConstantUtils.ITEM_NO_PROPERTY, "ITEM_NO");
		filterMap.put(StringConstantUtils.ITEM_NAME, "ITEM_NAME");
		filterMap.put("salesDollars", "SALES_DOLLARS");
		filterMap.put("salesUnits", "SALES_UNITS");
		filterMap.put("salesInclusion", "SALES_INCLUSION");
		filterMap.put("deductionId", "DEDUCTION_ID");
		filterMap.put("deductionNo", "DEDUCTION_NO");
		filterMap.put("deductionName", "DEDUCTION_NAME");
		filterMap.put("deductionCategory", "DEDUCTION_CATEGORY");
		filterMap.put("deductionType", "DEDUCTION_TYPE");
		filterMap.put("deductionProgram", "DEDUCTION_PROGRAM");
		filterMap.put("deductionInclusion", "DEDUCTION_INCLUSION");
		filterMap.put("deductionCategoryOne", "DEDUCTION_CATEGORY1");
		filterMap.put("deductionCategoryTwo", "DEDUCTION_CATEGORY2");
		filterMap.put("deductionCategoryThree", "DEDUCTION_CATEGORY3");
		filterMap.put("deductionCategoryFour", "DEDUCTION_CATEGORY4");
		filterMap.put("deductionCategoryFive", "DEDUCTION_CATEGORY5");
		filterMap.put("deductionCategorySix", "DEDUCTION_CATEGORY6");
		filterMap.put("deductionDollars", "DEDUCTION_DOLLARS");
		filterMap.put("deductionRate", "DEDUCTION_RATE");
		filterMap.put("deductionPerUnit", "DEDUCTION_PER_UNIT");
		filterMap.put("netSalesDollar", "NET_SALES_DOLLAR");
		filterMap.put("cogsAmount", "COGS_AMOUNT");
		filterMap.put("cogsPerUnit", "COGS_PER_UNIT");
		filterMap.put("netProfitDollars", "NET_PROFIT_DOLLARS");
		filterMap.put("netProfitPerUnit", "NET_PROFIT_PER_UNIT");
		filterMap.put("companyId", "COMPANY_ID");
		filterMap.put("companyNo", "COMPANY_NO");
		filterMap.put("companyName", "COMPANY_NAME");
		filterMap.put("businessUnitId", "BUSINESS_UNIT_ID");
		filterMap.put("businessUnitNo", "BUSINESS_UNIT_NO");
		filterMap.put("businessUnitName", "BUSINESS_UNIT_NAME");
		filterMap.put("financialForecastCreationDate", "FINANCIAL_FORECAST_CREATION_DATE");
		filterMap.put("financialForecastApprovalDate", "FINANCIAL_FORECAST_APPROVAL_DATE");
		filterMap.put("outboundStatus", "OUTBOUND_STATUS");
		filterMap.put("originalBatchId", "ORIGINAL_BATCH_ID");
		return filterMap;
	}

	private int getCount(List<Object[]> list) {
		if (!list.isEmpty()) {
			Object obj = list.get(0);
			return obj == null ? 0 : (Integer) obj;
		}
		return 0;
	}

	/**
	 * Gets the drop down list based on list name.
	 *
	 * @param listName
	 *            the list name
	 * @return the drop down list
	 * @throws SystemException
	 *             the system exception
	 */
	public static List<HelperDTO> getDropDownList(final String listName) throws SystemException {
		LOGGER.debug("Entering getDropDownList p1= {}" , listName);
		final List<HelperDTO> helperList = new ArrayList<>();
                DynamicQuery dynamicQuery = HelperTableLocalServiceUtil.dynamicQuery();
                dynamicQuery.add(RestrictionsFactoryUtil.eq("listName", listName));
                final List<HelperTable> list = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				final HelperTable helperTable = (HelperTable) list.get(i);
				helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable.getDescription()));

			}
		}
		LOGGER.debug("return DropDownList = {}" , helperList.size());
		return helperList;
	}

	/**
	 * Gets the customized search results.
	 *
	 * @param resultList
	 *            the result list
	 * @return the customized search results
	 */
	public List<ProcessSchedulerDTO> getCustomizedSearchResults(List<Object[]> resultList) {
		List<ProcessSchedulerDTO> cffSearchDTOs = new ArrayList<>();
		ProcessSchedulerDTO cffSearchDTOLoop;
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		for (Object[] obj : resultList) {
			cffSearchDTOLoop = new ProcessSchedulerDTO();
			cffSearchDTOLoop.setFinancialForecastId(String.valueOf(obj[0]));
			cffSearchDTOLoop.setFinancialForecastName(isValid(obj[1]) ? String.valueOf(obj[1]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setTypeDesc(
					isValid(obj[NumericConstants.TWO]) ? obj[NumericConstants.TWO].toString() : StringUtils.EMPTY);
			cffSearchDTOLoop.setProjectionId(isValid(obj[NumericConstants.THREE])
					? String.valueOf(obj[NumericConstants.THREE]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setProjectionName(isValid(obj[NumericConstants.FOUR])
					? String.valueOf(obj[NumericConstants.FOUR]) : StringUtils.EMPTY);
			cffSearchDTOLoop
					.setYear(isValid(obj[NumericConstants.FIVE]) ? String.valueOf(obj[NumericConstants.FIVE]) : "0");
			cffSearchDTOLoop.setMonth(isValid(obj[NumericConstants.SIX])
					? Integer.parseInt(String.valueOf(obj[NumericConstants.SIX])) : 0);
			cffSearchDTOLoop.setContractId(isValid(obj[NumericConstants.SEVEN])
					? String.valueOf(obj[NumericConstants.SEVEN]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setContractNo(isValid(obj[NumericConstants.EIGHT])
					? String.valueOf(obj[NumericConstants.EIGHT]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setContractName(isValid(obj[NumericConstants.NINE])
					? String.valueOf(obj[NumericConstants.NINE]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setContractType(
					isValid(obj[NumericConstants.TEN]) ? String.valueOf(obj[NumericConstants.TEN]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setContractHolderId(isValid(obj[NumericConstants.ELEVEN])
					? String.valueOf(obj[NumericConstants.ELEVEN]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setContractHolderNo(isValid(obj[NumericConstants.TWELVE])
					? String.valueOf(obj[NumericConstants.TWELVE]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setContractHolderName(isValid(obj[NumericConstants.THIRTEEN])
					? String.valueOf(obj[NumericConstants.THIRTEEN]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setCustomerId(isValid(obj[NumericConstants.FOURTEEN])
					? String.valueOf(obj[NumericConstants.FOURTEEN]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setCustomerNo(isValid(obj[NumericConstants.FIFTEEN])
					? String.valueOf(obj[NumericConstants.FIFTEEN]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setCustomerName(isValid(obj[NumericConstants.SIXTEEN])
					? String.valueOf(obj[NumericConstants.SIXTEEN]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setItemId(isValid(obj[NumericConstants.SEVENTEEN])
					? String.valueOf(obj[NumericConstants.SEVENTEEN]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setItemNo(isValid(obj[NumericConstants.EIGHTEEN])
					? String.valueOf(obj[NumericConstants.EIGHTEEN]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setItemName(isValid(obj[NumericConstants.NINETEEN])
					? String.valueOf(obj[NumericConstants.NINETEEN]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setSalesDollars(isValid(obj[NumericConstants.TWENTY])
					? String.valueOf(obj[NumericConstants.TWENTY]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setSalesUnits(isValid(obj[NumericConstants.TWENTY_ONE])
					? String.valueOf(obj[NumericConstants.TWENTY_ONE]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setSalesInclusion(isValid(obj[NumericConstants.TWENTY_TWO])
					? String.valueOf(obj[NumericConstants.TWENTY_TWO]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setDeductionId(isValid(obj[NumericConstants.TWENTY_THREE])
					? String.valueOf(obj[NumericConstants.TWENTY_THREE]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setDeductionNo(isValid(obj[NumericConstants.TWENTY_FOUR])
					? String.valueOf(obj[NumericConstants.TWENTY_FOUR]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setDeductionName(isValid(obj[NumericConstants.TWENTY_FIVE])
					? String.valueOf(obj[NumericConstants.TWENTY_FIVE]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setDeductionCategory(isValid(obj[NumericConstants.TWENTY_SIX])
					? String.valueOf(obj[NumericConstants.TWENTY_SIX]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setDeductionType(isValid(obj[NumericConstants.TWENTY_SEVEN])
					? String.valueOf(obj[NumericConstants.TWENTY_SEVEN]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setDeductionProgram(isValid(obj[NumericConstants.TWENTY_EIGHT])
					? String.valueOf(obj[NumericConstants.TWENTY_EIGHT]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setDeductionInclusion(isValid(obj[NumericConstants.TWENTY_NINE])
					? String.valueOf(obj[NumericConstants.TWENTY_NINE]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setDeductionCategoryOne(isValid(obj[NumericConstants.THIRTY])
					? String.valueOf(obj[NumericConstants.THIRTY]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setDeductionCategoryTwo(isValid(obj[NumericConstants.THIRTY_ONE])
					? String.valueOf(obj[NumericConstants.THIRTY_ONE]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setDeductionCategoryThree(isValid(obj[NumericConstants.THIRTY_TWO])
					? String.valueOf(obj[NumericConstants.THIRTY_TWO]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setDeductionCategoryFour(isValid(obj[NumericConstants.THIRTY_THREE])
					? String.valueOf(obj[NumericConstants.THIRTY_THREE]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setDeductionCategoryFive(isValid(obj[NumericConstants.THIRTY_FOUR])
					? String.valueOf(obj[NumericConstants.THIRTY_FOUR]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setDeductionCategorySix(isValid(obj[NumericConstants.THIRTY_FIVE])
					? String.valueOf(obj[NumericConstants.THIRTY_FIVE]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setDeductionDollars(isValid(obj[NumericConstants.THIRTY_SIX])
					? String.valueOf(obj[NumericConstants.THIRTY_SIX]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setDeductionRate(isValid(obj[NumericConstants.THIRTY_SEVEN])
					? String.valueOf(obj[NumericConstants.THIRTY_SEVEN]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setDeductionPerUnit(isValid(obj[NumericConstants.THIRTY_EIGHT])
					? String.valueOf(obj[NumericConstants.THIRTY_EIGHT]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setNetSalesDollar(isValid(obj[NumericConstants.THIRTY_NINE])
					? String.valueOf(obj[NumericConstants.THIRTY_NINE]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setCogsAmount(isValid(obj[NumericConstants.FORTY])
					? String.valueOf(obj[NumericConstants.FORTY]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setCogsPerUnit(isValid(obj[NumericConstants.FORTY_ONE])
					? String.valueOf(obj[NumericConstants.FORTY_ONE]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setNetProfitDollars(isValid(obj[NumericConstants.FORTY_TWO])
					? String.valueOf(obj[NumericConstants.FORTY_TWO]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setNetProfitPerUnit(isValid(obj[NumericConstants.FORTY_THREE])
					? String.valueOf(obj[NumericConstants.FORTY_THREE]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setCompanyId(isValid(obj[NumericConstants.FORTY_FOUR])
					? String.valueOf(obj[NumericConstants.FORTY_FOUR]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setCompanyNo(isValid(obj[NumericConstants.FORTY_FIVE])
					? String.valueOf(obj[NumericConstants.FORTY_FIVE]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setCompanyName(isValid(obj[NumericConstants.FORTY_SIX])
					? String.valueOf(obj[NumericConstants.FORTY_SIX]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setBusinessUnitId(isValid(obj[NumericConstants.FORTY_SEVEN])
					? String.valueOf(obj[NumericConstants.FORTY_SEVEN]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setBusinessUnitNo(isValid(obj[NumericConstants.FORTY_EIGHT])
					? String.valueOf(obj[NumericConstants.FORTY_EIGHT]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setBusinessUnitName(isValid(obj[NumericConstants.FORTY_NINE])
					? String.valueOf(obj[NumericConstants.FORTY_NINE]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setFinancialForecastCreationDate(isValid(obj[NumericConstants.FIFTY])
					? format.format(obj[NumericConstants.FIFTY]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setFinancialForecastApprovalDate(isValid(obj[NumericConstants.FIFTY_ONE])
					? format.format(obj[NumericConstants.FIFTY_ONE]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setOutboundStatus(isValid(obj[NumericConstants.FIFTY_TWO])
					? String.valueOf(obj[NumericConstants.FIFTY_TWO]) : StringUtils.EMPTY);
			cffSearchDTOLoop.setOriginalBatchId(isValid(obj[NumericConstants.FIFTY_THREE])
					? String.valueOf(obj[NumericConstants.FIFTY_THREE]) : StringUtils.EMPTY);

			cffSearchDTOLoop.setPeriodSid(isValid(obj[NumericConstants.FIFTY_FOUR])
					? Integer.parseInt(String.valueOf(obj[NumericConstants.FIFTY_FOUR])) : 0);
			cffSearchDTOLoop.setCffDetailSid(isValid(obj[NumericConstants.FIFTY_FIVE])
					? Integer.parseInt(String.valueOf(obj[NumericConstants.FIFTY_FIVE])) : 0);
			cffSearchDTOLoop.setRsModelSid(isValid(obj[NumericConstants.FIFTY_SIX])
					? Integer.parseInt(String.valueOf(obj[NumericConstants.FIFTY_SIX])) : 0);
			if (isValid(obj[NumericConstants.FIFTY_SEVEN])) {
				cffSearchDTOLoop.setCheckRecord((Boolean) obj[NumericConstants.FIFTY_SEVEN]);
			}

			cffSearchDTOs.add(cffSearchDTOLoop);
		}
		return cffSearchDTOs;
	}

	private Boolean isValid(Object obj) {
		if (obj == null || "null".equals(String.valueOf(obj)) || "-Select One".equals(String.valueOf(obj))
				|| StringUtils.isBlank(String.valueOf(obj)) || "0".equals(String.valueOf(obj))) {
			return Boolean.FALSE;
		} else {
			return Boolean.TRUE;
		}
	}

	public static List<Object[]> cffOutboundProcedure(String ids) throws NamingException, SQLException {
		DataSource datasource = null;
		ResultSet resultSet;
		List<Object[]> objectList = new ArrayList<>();
		try {
			Context initialContext = new InitialContext();
			datasource = (DataSource) initialContext.lookup("java:jboss/datasources/jdbc/appDataPool");
                } catch (NamingException ex)
                {
                    LOGGER.debug(ex.getMessage());
                }
			if (datasource != null) {
                            try (Connection connection = datasource.getConnection();
                                    CallableStatement statement = connection.prepareCall("{call " + "PRC_CFF_OUTBOUND" + "(?)}"))
                            {
					statement.setString(1, ids);
					resultSet = statement.executeQuery();
					objectList = convertResultSetToList(resultSet);
					LOGGER.debug("After Converting objectList size= {} " , objectList.size());
				} catch (SQLException ex)
                                {
                                    LOGGER.debug(ex.getMessage());
                                }
			}
		return objectList;
	}

	private static List<Object[]> convertResultSetToList(ResultSet rs) throws SQLException {
		List<Object[]> objList = new ArrayList<>();

		try {
			while (rs.next()) {
				ResultSetMetaData metadata = rs.getMetaData();
				int numberOfColumns = metadata.getColumnCount();
				Object[] obj = new Object[numberOfColumns];
				for (int i = 1; i <= numberOfColumns; i++) {
					obj[i - 1] = rs.getObject(i);
				}
				objList.add(obj);
			}

		} finally {

			rs.close();

		}
		return objList;
	}

	/**
	 * Retrieves all the user name and stores that in the Concurrent Hash Map.
	 *
	 * @return the Map
	 * @throws com.liferay.portal.kernel.exception.SystemException
	 */
	public static Map<Integer, String> getUserName() throws SystemException {
		LOGGER.debug("Enters getUserName method");
		Map<Integer, String> userMap = StplSecurity.getUserMap();
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(User.class);
		List<User> userList = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
		for (User user : userList) {
			userMap.put(Integer.valueOf(String.valueOf(user.getUserId())), user.getLastName() + ", " + user.getFirstName());
		}
		LOGGER.debug("End of getUserName method");
		return userMap;
	}

	/**
	 * To delete the records from the main table and truncate temp tables
	 * 
	 * @param deleteQuery
	 */
	public void deleteUnsavedProjections(String deleteQueryParam) {
            String deleteQuery = deleteQueryParam;
		try {
                        
			deleteQuery = SQlUtil.getQuery(deleteQuery);
			HelperTableLocalServiceUtil.executeUpdateQuery(deleteQuery);
		} catch (Exception ex) {
			LOGGER.debug("Intial Session Delete QUERY ERROR= {} " , deleteQuery);
			LOGGER.error(ex.getMessage());
		}
	}

	/**
	 * To delete all the records from temp table before searching based on user
	 * id and session id
	 * 
	 * @param sessionDTO
	 */
	public void deleteTempCffOutbound(SessionDTO sessionDTO, Boolean isScheduler) {
		LOGGER.debug("Enters deleteTempCffOutbound method");
		try {
			String query = "";
			if (!isScheduler) {
				query = "DELETE FROM ST_CFF_OUTBOUND_MASTER WHERE USER_ID = " + sessionDTO.getUserId() + AND_SESSION_ID
						+ sessionDTO.getSessionId() + ";";
			} else {
				query = "DELETE FROM ST_CFF_OUTBOUND_MASTER WHERE USER_ID = 1 AND SESSION_ID = 1;";
			}
			HelperTableLocalServiceUtil.executeUpdateQuery(query);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
		LOGGER.debug("End of deleteTempCffOutbound method");
	}

	public static final String AND_SESSION_ID = " AND SESSION_ID = ";

	/**
	 * Check record update query
	 * 
	 * @param dto
	 * @param sessionDTO
	 * @param isCheckAll
	 * @param check
	 */
	public void updateTempCffOutbound(ProcessSchedulerDTO dto, SessionDTO sessionDTO, Boolean isCheckAll,
			Boolean check) {
		LOGGER.debug("Enters updateTempCffOutbound method");
		int value = check ? 1 : 0;
		String query;
		if (isCheckAll) {
			query = "UPDATE ST_CFF_OUTBOUND_MASTER SET CHECK_RECORD = " + value + " \n" + "WHERE USER_ID = "
					+ sessionDTO.getUserId() + AND_SESSION_ID + sessionDTO.getSessionId() + " ;";
		} else {
			query = "UPDATE ST_CFF_OUTBOUND_MASTER SET CHECK_RECORD = " + value + " \n" + "WHERE USER_ID = "
					+ sessionDTO.getUserId() + AND_SESSION_ID + sessionDTO.getSessionId() + " AND CFF_DETAILS_SID = "
					+ dto.getCffDetailSid() + " " + "AND RS_MODEL_SID = " + dto.getRsModelSid() + " AND PERIOD_SID = "
					+ dto.getPeriodSid() + ";";
		}
		HelperTableLocalServiceUtil.executeUpdateQuery(query);
		LOGGER.debug("End of updateTempCffOutbound method");
	}

	/**
	 * Method for searching CFF detail sids based on entered criteria
	 * 
	 * @param cffSearchBinder
	 * @return
	 */
	public String getSearchCriteria(CustomFieldGroup cffSearchBinder) {
		LOGGER.debug("Enters getCFFDetailsSid method");
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String value;
		String query = StringUtils.EMPTY;

		if (CommonUtils
				.isValidCriteria(String.valueOf(cffSearchBinder.getField(StringConstantUtils.FINANCIAL_FORECAST_ID)))) {
			value = String.valueOf(cffSearchBinder.getField(StringConstantUtils.FINANCIAL_FORECAST_ID).getValue())
					.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
			query += " AND CFFOM.FINANCIAL_FORECAST_ID LIKE '" + value + "' ";
		}
		if (CommonUtils.isValidCriteria(String.valueOf(cffSearchBinder.getField("projectionId")))) {
			value = String.valueOf(cffSearchBinder.getField("projectionId").getValue())
					.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
			query += " AND CFFOM.PROJECT_ID LIKE '" + value + "' ";
		}
		if (CommonUtils.isValidCriteria(String.valueOf(cffSearchBinder.getField(StringConstantUtils.CUSTOMER_NO)))) {
			value = String.valueOf(cffSearchBinder.getField(StringConstantUtils.CUSTOMER_NO).getValue())
					.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
			query += " AND CFFOM.CUSTOMER_NO LIKE '" + value + "' ";
		}
		if (CommonUtils.isValidCriteria(
				String.valueOf(cffSearchBinder.getField(StringConstantUtils.FINANCIAL_FORECAST_NAME)))) {
			value = String.valueOf(cffSearchBinder.getField(StringConstantUtils.FINANCIAL_FORECAST_NAME).getValue())
					.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
			query += " AND CFFOM.FINANCIAL_FORECAST_NAME LIKE '" + value + "' ";
		}
		if (CommonUtils
				.isValidCriteria(String.valueOf(cffSearchBinder.getField(StringConstantUtils.PROJECTION_NAME)))) {
			value = String.valueOf(cffSearchBinder.getField(StringConstantUtils.PROJECTION_NAME).getValue())
					.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
			query += " AND CFFOM.PROJECTION_NAME LIKE '" + value + "' ";
		}
		if (CommonUtils.isValidCriteria(String.valueOf(cffSearchBinder.getField(StringConstantUtils.CUSTOMER_NAME)))) {
			value = String.valueOf(cffSearchBinder.getField(StringConstantUtils.CUSTOMER_NAME).getValue())
					.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
			query += " AND CFFOM.CUSTOMER_NAME LIKE '" + value + "' ";
		}
		if (CommonUtils.isValidCriteria(String.valueOf(cffSearchBinder.getField("typeDdlb")))) {
			value = String.valueOf(((HelperDTO) cffSearchBinder.getField("typeDdlb").getValue()).getId());
			query += " AND CFFOM.TYPE = " + value + " ";
		}
		if (CommonUtils.isValidCriteria(String.valueOf(cffSearchBinder.getField(StringConstantUtils.CONTRACT_NO)))) {
			value = String.valueOf(cffSearchBinder.getField(StringConstantUtils.CONTRACT_NO).getValue())
					.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
			query += " AND CFFOM.CONTRACT_NO LIKE '" + value + "'";
		}
		if (CommonUtils
				.isValidCriteria(String.valueOf(cffSearchBinder.getField(StringConstantUtils.ITEM_NO_PROPERTY)))) {
			value = String.valueOf(cffSearchBinder.getField(StringConstantUtils.ITEM_NO_PROPERTY).getValue())
					.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
			query += " AND CFFOM.ITEM_NO LIKE '" + value + "' ";
		}
		if (CommonUtils.isValidCriteria(String.valueOf(cffSearchBinder.getField(StringConstantUtils.CONTRACT_NAME)))) {
			value = String.valueOf(cffSearchBinder.getField(StringConstantUtils.CONTRACT_NAME).getValue())
					.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
			query += " AND CFFOM.CONTRACT_NAME LIKE '" + value + "' ";
		}
		if (CommonUtils.isValidCriteria(String.valueOf(cffSearchBinder.getField(StringConstantUtils.ITEM_NAME)))) {
			value = String.valueOf(cffSearchBinder.getField(StringConstantUtils.ITEM_NAME).getValue())
					.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
			query += " AND CFFOM.ITEM_NAME LIKE '" + value + "' ";
		}
		if (CommonUtils.isValidCriteria(String.valueOf(cffSearchBinder.getField("cffCreationDateFrom")))) {
			value = sdf.format(cffSearchBinder.getField("cffCreationDateFrom").getValue());
			query += " AND CFFOM.FINANCIAL_FORECAST_CREATION_DATE >= '" + value + "' ";
		}
		if (CommonUtils.isValidCriteria(String.valueOf(cffSearchBinder.getField("cffCreationDateTo")))) {
			value = sdf.format(cffSearchBinder.getField("cffCreationDateTo").getValue());
			query += " AND CFFOM.FINANCIAL_FORECAST_CREATION_DATE <= '" + value + "' ";
		}
		if (CommonUtils.isValidCriteria(String.valueOf(cffSearchBinder.getField("cffApprovalDateFrom")))) {
			value = sdf.format(cffSearchBinder.getField("cffApprovalDateFrom").getValue());
			query += " AND CFFOM.FINANCIAL_FORECAST_APPROVAL_DATE >= '" + value + "' ";
		}
		if (CommonUtils.isValidCriteria(String.valueOf(cffSearchBinder.getField("cffApprovalDateTo")))) {
			value = sdf.format(cffSearchBinder.getField("cffApprovalDateTo").getValue());
			query += " AND CFFOM.FINANCIAL_FORECAST_APPROVAL_DATE <= '" + value + "' ";
		}
		LOGGER.debug("End of getCFFDetailsSid method");
		return query;
	}

	/**
	 * Method for setting ETL check records as true for performing Outbound
	 * 
	 * @param sessionDTO
	 * @return
	 */
	public Boolean checkETLRecords(final SessionDTO sessionDTO) {
		String query = "UPDATE ST_CFF_OUTBOUND_MASTER SET ETL_CHECK_RECORD = 1 WHERE USER_ID = "
				+ sessionDTO.getUserId() + AND_SESSION_ID + sessionDTO.getSessionId() + " AND CHECK_RECORD = 1";
		int count = HelperTableLocalServiceUtil.executeUpdateQueryCount(query);
		return count > 0;
	}

	/**
	 * Method is for running procedure based on the CFF id's
	 * 
	 * @param userId
	 * @param sessionId
	 * @param cffIds
	 */
	public void cffOutboundInsertProc(final String userId, final String sessionId, final List cffIds) {

		if (cffIds != null && !cffIds.isEmpty()) {
			try {
				LOGGER.debug("Inside cffOutboundInsertProc with cffids= {}" , cffIds.size());
				StringBuilder sb = new StringBuilder(
						"DECLARE @CFF_DETAILS_SID UDT_ITEM\n" + "INSERT INTO  @CFF_DETAILS_SID\n");
				for (int i = 0; i < cffIds.size(); i++) {
					sb.append(" SELECT ").append(cffIds.get(i));
					if (i != cffIds.size() - 1) {
						sb.append(" UNION ALL \n");
					}
				}
				sb.append(" \n EXEC PRC_CFF_OUTBOUND @CFF_DETAILS_SID,").append(userId).append(',').append(sessionId)
						.append(" , 'SCHEDULER' ").append(';');
				HelperTableLocalServiceUtil.executeUpdateQuery(sb.toString());
				LOGGER.debug("Ending cffOutboundInsertProc");
			} catch (Exception ex) {
				LOGGER.error(ex.getMessage());
			}
		}
	}

	/**
	 * Method for returning select statement for search
	 * 
	 * @return
	 */
	private String getCffSelect() {
		return "SELECT DISTINCT D.CFF_DETAILS_SID\n" + "	FROM CFF_MASTER C\n"
				+ "	INNER JOIN CFF_DETAILS D ON C.CFF_MASTER_SID = D.CFF_MASTER_SID\n"
				+ "	INNER JOIN PROJECTION_MASTER P ON P.PROJECTION_MASTER_SID = D.PROJECTION_MASTER_SID\n"
				+ "	INNER JOIN CCP_DETAILS CD ON D.CCP_DETAILS_SID = CD.CCP_DETAILS_SID\n"
				+ "	INNER JOIN CONTRACT_MASTER CO ON CO.CONTRACT_MASTER_SID = CD.CONTRACT_MASTER_SID\n"
				+ "	INNER JOIN COMPANY_MASTER CM ON CM.COMPANY_MASTER_SID = CD.COMPANY_MASTER_SID\n"
				+ "	INNER JOIN ITEM_MASTER IM ON IM.ITEM_MASTER_SID = CD.ITEM_MASTER_SID\n"
				+ "        INNER JOIN PROJECTION_DETAILS PD ON D.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID\n"
				+ "		AND D.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
				+ "        JOIN CFF_APPROVAL_DETAILS CAF ON  CAF.CFF_MASTER_SID = C.CFF_MASTER_SID   \n"
				+ "   	LEFT JOIN HELPER_TABLE H ON    H.HELPER_TABLE_SID = CAF.APPROVAL_STATUS \n"
				+ "		WHERE DESCRIPTION = 'Approved' ";
	}

	/**
	 * Method for performing CFF outbound process during automatic run
	 */
	public void schedulerInsert() {
		LOGGER.debug("Inside schedulerInsert");
		StringBuilder sb = new StringBuilder(getCffSelect());
		List cffIds = HelperTableLocalServiceUtil.executeSelectQuery(sb.toString());
		cffOutboundInsertProc("1", "1", cffIds);

		sb = new StringBuilder();
		sb.append("UPDATE ST_CFF_OUTBOUND_MASTER SET ETL_CHECK_RECORD = 1 WHERE USER_ID = 1 AND SESSION_ID = 1 ;");
		HelperTableLocalServiceUtil.executeUpdateQuery(sb.toString());

		LOGGER.debug("Ending schedulerInsert");
	}

	public void schedulerInsertForArp(String userId, String sessionId) {
		LOGGER.debug("Inside arp schedulerInsert");

		// Removed the procedure for ARP Out Bound.
		String finalQuery = SQlUtil.getQuery("insert-arp-outbound");
		finalQuery = finalQuery.replace("[?USER_ID]", userId);
		finalQuery = finalQuery.replace("[?SESSION_ID]", sessionId);

		HelperTableLocalServiceUtil.executeUpdateQuery(finalQuery);
		LOGGER.debug("Ending arp schedulerInsert");
	}

	/**
	 * method for find when record exists for the particular session
	 * 
	 * @param sessionDTO
	 * @return
	 */
	public Boolean existsQuery(final String userId, final String sessionId) {
		String query = "SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END FROM ST_CFF_OUTBOUND_MASTER WHERE USER_ID = "
				+ userId + AND_SESSION_ID + sessionId + " AND ETL_CHECK_RECORD = 1";
		List count = HelperTableLocalServiceUtil.executeSelectQuery(query);
		int c = Integer.parseInt(String.valueOf(count.get(0)));
		return c > 0;
	}

	public Boolean existsQueryArp(final String userId, final String sessionId) {
		String query = "SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END FROM ST_ARP_OUTBOUND WHERE USER_ID = '" + userId
				+ "' AND SESSION_ID = " + sessionId + " AND ETL_CHECK_RECORD = 1";
		List count = HelperTableLocalServiceUtil.executeSelectQuery(query);
		int c = Integer.parseInt(String.valueOf(count.get(0)));
		return c > 0;
	}

	public void callSriptForArp(String userId, String sessionId) {
		ArmSchedulerSynchronizer arpProcess = ArmSchedulerSynchronizer.getInstance(" Automatic Scheduler ");
		try {
			LOGGER.debug("Enters callSriptForArp method");

			arpProcess.lock();
			new ProcessSchedulerLogic().schedulerInsertForArp(userId, sessionId);
			runJob(getFtpBundleValue(), "Arp_Outbound_Intf.sh");
			LOGGER.debug("Ends callSriptForArp method");
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		} finally {
			arpProcess.unlock();
		}

	}

	public void deleteTempArpOutbound(String sessionId, String userId, Boolean isScheduler) {
		LOGGER.debug("Enters deleteTempARPOutbound method");
		String query = "";
		if (!isScheduler) {
			query = "DELETE FROM ST_ARP_OUTBOUND WHERE USER_ID = '" + userId + "' AND SESSION_ID = " + sessionId + "";
		} else {
			query = "DELETE FROM ST_ARP_OUTBOUND WHERE USER_ID = '1' AND SESSION_ID = 1;";
		}

		HelperTableLocalServiceUtil.executeUpdateQuery(query);
		LOGGER.debug("End of deleteTempARPOutbound method");
	}

	public void deleteTempTable(SessionDTO sessionDTO) {
		String query = "DELETE FROM ST_ARP_OUTBOUND WHERE USER_ID='" + sessionDTO.getUserId() + "' AND SESSION_ID="
				+ sessionDTO.getArpSessionId();
		HelperTableLocalServiceUtil.executeUpdateQuery(query);
	}

	/**
	 *
	 * @param sessionDTO
	 */
	public void insertCFFOnSearch(SessionDTO sessionDto, CustomFieldGroup cffSearchBinder) {
		try {
			String query = SQlUtil.getQuery("getCffSearchInsert");
			query = query.replace("?USERID", sessionDto.getUserId());
			query = query.replace("?SESSIONID", sessionDto.getSessionId());
			query = query.replace("?SEARCHFILTER", getSearchCriteria(cffSearchBinder));
			HelperTableLocalServiceUtil.executeUpdateQueryCount(query);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
	}

	public void updateCheckRecord(String processName, String userId, String sessionId) {
		String query = StringUtils.EMPTY;
		try {
			if (ConstantsUtils.ADJ_RESERVER_DETAIL_INTERFACE.equals(processName)) {
				query = SQlUtil.getQuery("insert-adj_Rserve_Details");
			} else {
				query = SQlUtil.getQuery("insert-adj_gtn_Details");
			}
			query = query.replace("[?USER_ID]", userId);
			query = query.replace("[?SESSION_ID]", sessionId);
			HelperTableLocalServiceUtil.executeUpdateQuery(query);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
	}

	/*
	 * Checking for process is already running
	 */
	public boolean checkForRunning(String processName) {
		String query = SQlUtil.getQuery("getRunningStatus");
		query = query.replace("@PROCESS", processName);
		List status = HelperTableLocalServiceUtil.executeSelectQuery(query);
		if (status != null && !status.isEmpty()) {
			return String.valueOf(status.get(0)).equalsIgnoreCase("Y");
		}
		return false;
	}

	/**
	 * get count for hierarchy name
	 *
	 * @param filterText
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 */
	public static int getLazyHierarchyNameCount(final String filterText) throws SystemException {
		final String filterText1 = StringUtils.trimToEmpty(filterText) + "%";
		LOGGER.debug("Entering getLazyCompanyQualifierNameCount method with filterText= {}" , filterText1);
		final DynamicQuery dynamicQuery = HierarchyDefinitionLocalServiceUtil.dynamicQuery();
		dynamicQuery.setProjection(ProjectionFactoryUtil.count(ConstantsUtils.HIERARCHY_DEFINITION_ID));
		dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.HIERARCHY_NAME, filterText1));
		dynamicQuery.add(RestrictionsFactoryUtil
				.not(RestrictionsFactoryUtil.like(ConstantsUtils.HIERARCHY_NAME, StringUtils.EMPTY)));
		dynamicQuery.add(RestrictionsFactoryUtil.isNotNull(ConstantsUtils.HIERARCHY_NAME));
		final List<Object[]> list = dao.getHierachyDefinitionList(dynamicQuery);
		return Integer.parseInt(String.valueOf(list.get(0)));
	}

	/**
	 * getting results for CompanyQualifierName
	 *
	 * @param start
	 * @param end
	 * @param filteredText
	 * @param filterText
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 */
	public static List<HelperDTO> getLazyHierarchyNameResults(final int start, final int end, final String filteredText)
			throws SystemException {
		final String filterText = StringUtils.trimToEmpty(filteredText) + "%";
		LOGGER.debug("Entering getLazyHierarchyNameResults method with filterText= {}" , filterText);
		final List<HelperDTO> list = new ArrayList<>();

		final DynamicQuery dynamicQuery = HierarchyDefinitionLocalServiceUtil.dynamicQuery();
		dynamicQuery.setLimit(start, end);
		final ProjectionList projectionList = ProjectionFactoryUtil.projectionList();
		projectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.HIERARCHY_DEFINITION_ID));
		projectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.HIERARCHY_NAME));
		dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projectionList));
		dynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.HIERARCHY_NAME));
		dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.HIERARCHY_NAME, filterText));
		dynamicQuery.add(RestrictionsFactoryUtil
				.not(RestrictionsFactoryUtil.like(ConstantsUtils.HIERARCHY_NAME, StringUtils.EMPTY)));
		dynamicQuery.add(RestrictionsFactoryUtil.isNotNull(ConstantsUtils.HIERARCHY_NAME));
		final List<Object[]> returnList = dao.getHierachyDefinitionList(dynamicQuery);

		HelperDTO dto;
		if (start == ConstantsUtils.ZERO_NUM) {
			dto = new HelperDTO(ConstantsUtils.SELECT_ONE);
			list.add(dto);
		}
		for (final Iterator<Object[]> iterator = returnList.iterator(); iterator.hasNext();) {
			final Object[] value = iterator.next();
			dto = new HelperDTO(StringUtils.EMPTY);
			dto.setId(value[0] == null ? 0 : Integer.parseInt(value[0].toString()));
			dto.setDescription(value[1] == null ? StringUtils.EMPTY : value[1].toString());
			if (!StringUtils.EMPTY.equals(dto.getDescription())) {
				list.add(dto);
			}
		}

		LOGGER.debug("return getLazyHierarchyNameResults size ={}" , list.size());
		return list;
	}
	public String buildUrl(String scriptName, Properties prop) {
		String interfaceUri = getInterFaceUri(scriptName);
		String portNo = prop.getProperty("ETL_PORT_NO");
		return "http://localhost:" + portNo + "/" + interfaceUri;
	}
	private String getInterFaceUri(String scriptName) {
		String jbossHome=getJbossHome();
		java.util.Properties interfaceUriProperties = getPropertyFile(getPropertyPath());
		java.util.Properties interfacename=getPropertyFile(jbossHome.concat("/../").concat(interfaceUriProperties.getProperty("Interfaceuri.properties")));
		return interfacename.getProperty(scriptName).trim();
	}
	private static String getJbossHome() {
		return System.getProperty("jboss.home.dir");
	}
	private static String getPropertyPath() {
		return System.getProperty("com.stpl.gtnframework.base.path.property");
	}
}