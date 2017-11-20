package com.stpl.gtn.gtn2o.ws.module.companymaster.quartz;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterFinancialCloseBean;
import com.stpl.gtn.gtn2o.ws.controller.GtnWsGeneralController;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnCmQuartzListener {
	public GtnCmQuartzListener() {
		/**
		 * empty constructor
		 */

	}

	private Scheduler scheduler = null;
	private String actionJobDataMapKey = "jobData";
	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnCmQuartzListener.class);

	/**
	 * This method is used for initialising scheduling process. This method will be
	 * called from listner from web.xml Creates jobs ans triggers for ETL process
	 *
	 * @param servletContext
	 */

	private GtnWsGeneralController gtnGeneralServiceController;

	public GtnWsGeneralController getGtnGeneralServiceController() {
		return gtnGeneralServiceController;
	}

	public void setGtnGeneralServiceController(GtnWsGeneralController gtnGeneralServiceController) {
		this.gtnGeneralServiceController = gtnGeneralServiceController;
	}

	public void contextInitialized(SessionFactory sessionFactory) {
		LOGGER.info("Context Initialized - - ");

		try {
			scheduler = STPLQuartzScheduler.getIntance().getScheduler();
			scheduler.start();
			// Setup the Job class and the Job group
			scheduleCompanyFinancialClose();
			deleteTempTableScheduler(sessionFactory);
		} catch (SchedulerException e) {
			LOGGER.error("Error in Context listner", e);
		}
	}

	/**
	 * Method to schedule scheduler for company master
	 */
	public void scheduleCompanyFinancialClose() {
		try {
			scheduler = STPLQuartzScheduler.getIntance().getScheduler();
			scheduler.start();
			List<GtnCMasterFinancialCloseBean> list = getCustomziedDTO(getCompanyAuto());
			for (GtnCMasterFinancialCloseBean financialCloseDTO : list) {
				killJob(String.valueOf(financialCloseDTO.getCompanyMasterSid()));
				Calendar cal = getTime(financialCloseDTO, Calendar.getInstance().get(Calendar.MONTH) + 1);
				Date dateForquartz = cal.getTime();
				financialCloseDTO.setMonth(String.valueOf(cal.get(Calendar.MONTH)));
				financialCloseDTO.setYear(String.valueOf(cal.get(Calendar.YEAR)));
				JobDataMap jobDataMap = new JobDataMap();
				jobDataMap.put(String.valueOf(financialCloseDTO.getCompanyMasterSid()), financialCloseDTO);
				JobDetail job1 = JobBuilder.newJob(GtnCmQuartzJob.class).setJobData(jobDataMap)
						.withIdentity(String.valueOf(financialCloseDTO.getCompanyMasterSid())).storeDurably().build();
				scheduler.addJob(job1, false);
				LOGGER.info("date is" + dateForquartz);

				Trigger trigg = TriggerBuilder.newTrigger().forJob(job1)
						.withIdentity(String.valueOf(financialCloseDTO.getCompanyMasterSid())).startAt(dateForquartz)
						.build();
				scheduler.scheduleJob(trigg);
				LOGGER.info("Calendar is scheduled for comapny master sid :" + financialCloseDTO.getCompanyMasterSid()
						+ " at :" + dateForquartz);
			}
		} catch (Exception ex) {
			LOGGER.error("Error in Scheduling Company close", ex);
		}
	}

	/**
	 * Getting company informations from temp table
	 *
	 * @param list
	 * @return List<FinancialCloseBean>
	 */
	public List<GtnCMasterFinancialCloseBean> getCustomziedDTO(List<Object[]> list) {
		List<GtnCMasterFinancialCloseBean> finalList = new ArrayList<>();
		for (Object[] obj : list) {
			try {
				GtnCMasterFinancialCloseBean dto = new GtnCMasterFinancialCloseBean();
				dto.setCompanyMasterSid(obj[0] != null ? Integer.valueOf(String.valueOf(obj[0])) : 0);
				dto.setBusinessDayDdlb(obj[1] != null ? String.valueOf(obj[1]) : StringUtils.EMPTY);
				dto.setHourDdlb(obj[2] != null ? String.valueOf(obj[2]) : StringUtils.EMPTY);
				dto.setMinuteValue(obj[3] != null ? String.valueOf(obj[3]) : StringUtils.EMPTY);
				dto.setCalenderDdlb(obj[4] != null ? Integer.valueOf(String.valueOf(obj[4])) : 0);
				dto.setCreatedBy(obj[5] != null ? String.valueOf(obj[5]) : StringUtils.EMPTY);
				finalList.add(dto);
			} catch (Exception ex) {
				LOGGER.error(ex.getMessage());
			}
		}
		return finalList;
	}

	/**
	 * This is the method to kill Jobs whenever used do automatic save
	 *
	 */
	public void killJob(String companyMasterSid) {
		try {
			JobKey jobKey = new JobKey(companyMasterSid);
			TriggerKey trigger = new TriggerKey(companyMasterSid);
			if (scheduler != null) {
				scheduler.deleteJob(jobKey);
				scheduler.unscheduleJob(trigger);
			}
		} catch (SchedulerException ex) {
			LOGGER.error("Error in killing Job--" + companyMasterSid, ex);
		}
	}

	/**
	 * This method is used to attach the trigger from job but does not delete job
	 * from scheduler. Job will be present in scheduler but trigger will be removed
	 * from scheduler
	 *
	 * @param trigger
	 *            trigger which should be de attached from Specific job
	 */
	@SuppressWarnings("unused")
	private void killTrigger() {
		try {
			List<TriggerKey> triggerKey = new ArrayList<>();
			for (String groupName : scheduler.getTriggerGroupNames()) {
				for (TriggerKey jobKey : scheduler.getTriggerKeys(GroupMatcher.triggerGroupEquals(groupName))) {
					triggerKey.add(jobKey);
				}
			}
			scheduler.unscheduleJobs(triggerKey);
		} catch (SchedulerException ex) {
			LOGGER.error("Error in killing trigger ", ex);
		}

	}

	public Calendar getTime(GtnCMasterFinancialCloseBean dto, int month) {
		List<Object> currMonthHolidays = getHolidaysForCurrentMonth(dto.getCalenderDdlb(), month);
		List<Object> noOfDaysInMonth = new ArrayList<>();
		for (int i = 1; i <= 31; i++) {
			noOfDaysInMonth.add(i);
		}
		noOfDaysInMonth.removeAll(currMonthHolidays);
		int workingday = Integer
				.parseInt(String.valueOf(noOfDaysInMonth.get(Integer.parseInt(dto.getBusinessDayDdlb()) - 1)));
		Calendar calendar = Calendar.getInstance();

		Calendar date;
		if (workingday >= calendar.get(Calendar.DATE)) {
			date = findScheduleDate(Integer.valueOf(dto.getBusinessDayDdlb()), currMonthHolidays, 5);
		} else {
			date = findScheduleDate(Integer.valueOf(dto.getBusinessDayDdlb()),
					getHolidaysForCurrentMonth(dto.getCalenderDdlb(), month + 1), 5);
		}
		date.set(Calendar.HOUR_OF_DAY, Integer.valueOf(dto.getHourDdlb())); // Changed
		// to
		// work
		// in
		// 24
		// hour,
		// hour
		// selection
		date.set(Calendar.MINUTE, Integer.valueOf(dto.getMinuteValue()));
		LOGGER.info("date.getTime() : " + date.getTime());
		return date;
	}

	public Calendar findScheduleDate(int businessDay, List<Object> holiday, int checkMonth) {
		Calendar cal = null;
		if (businessDay > 0) {
			cal = Calendar.getInstance();
			cal.set(Calendar.DATE, 1);
			cal = findNextScheduleDate(cal, businessDay, holiday, 0, true, 0, checkMonth);
		}
		return cal;
	}

	public Calendar findNextScheduleDate(Calendar cal, int businessDay, List<Object> holiday, int noOfDay,
			boolean start, int monthChange, int checkMonth) {
		int totalnoOfDays = noOfDay;
		int changeInMonth = monthChange;
		Calendar calendar = cal;
		if (totalnoOfDays == businessDay && calendar.getTime().before(Calendar.getInstance().getTime())) {
			calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
			calendar.set(Calendar.DATE, 1);
			totalnoOfDays = 0;
			changeInMonth++;
		}
		if (changeInMonth < checkMonth) {
			if (totalnoOfDays < businessDay) {
				int day = calendar.get(Calendar.DATE);
				boolean isHoliday = holiday.contains(Integer.valueOf(day));
				if (!isHoliday) {
					totalnoOfDays++;
				}
				int oldMonth = calendar.get(Calendar.MONTH);
				calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
				int newMonth = calendar.get(Calendar.MONTH);
				if (oldMonth != newMonth) {
					calendar.set(Calendar.DATE, 1);
					totalnoOfDays = 0;
					changeInMonth++;
				}
				calendar = findNextScheduleDate(calendar, businessDay, holiday, totalnoOfDays, false, changeInMonth,
						checkMonth);
			} else if (!start) {
				calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
			}
		} else {
			calendar = null;
		}
		return calendar;
	}

	public void updateCompany(final GtnCMasterFinancialCloseBean dto, final String userId) {
		Object[] updateData = new Object[] { userId, dto.getCalenderDdlb(), dto.getMonth(), dto.getYear(),
				dto.getCompanyMasterSid() };
		updateCompanyFinancial(updateData);

	}

	private void deleteTempTableScheduler(SessionFactory sessionFactory) {

		try {
			scheduler = STPLQuartzScheduler.getIntance().getScheduler();
			scheduler.start();
			String time = "23hrs59";
			LOGGER.debug("time" + time);
			String[] hrs = time.split("hrs");
			JobDataMap jobDataMap = new JobDataMap();
			jobDataMap.put(actionJobDataMapKey, "Delete");
			jobDataMap.put("Session", sessionFactory);
			JobDetail job1 = JobBuilder.newJob(GtnCmdeleteJob.class).setJobData(jobDataMap)
					.withIdentity("job" + "deleteJob", "Group").storeDurably().build();
			LOGGER.debug(hrs[0] + "hrs" + hrs[1]);
			scheduler.addJob(job1, false);
			Trigger trigger2 = TriggerBuilder.newTrigger().forJob(job1).withIdentity("trigger" + "deleteTrigger")
					.withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(Integer.parseInt(hrs[0]),
							Integer.parseInt(hrs[1])))
					.build();
			scheduler.scheduleJob(trigger2);
		} catch (SchedulerException ex) {
			LOGGER.error("SchedulerException", ex);
		}
	}

	@SuppressWarnings({ "unchecked" })
	public List<Object> getHolidaysForCurrentMonth(Integer masterSID, int month) {
		String stringQuery = getholidaysForCurrentMonthQuery();
		GtnFrameworkDataType[] dataHeader = { GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER };
		List<Object> dbData = null;
		try {
			dbData = gtnGeneralServiceController.executeQuery(stringQuery, new Object[] { masterSID, month },
					dataHeader);
		} catch (GtnFrameworkGeneralException ex) {
			LOGGER.error("Exception in executig query-", ex);
		}
		List<Object> list = new ArrayList<>();
		for (Object sqldate : dbData) {
			Calendar sqlCalendar = Calendar.getInstance();
			sqlCalendar.setTimeInMillis(((java.sql.Timestamp) sqldate).getTime());
			list.add(sqlCalendar.get(Calendar.DAY_OF_MONTH));
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getCompanyAuto() throws GtnFrameworkGeneralException {
		String stringQuery = getCompanyAutoQuery();
		List<Object[]> dbData = null;
		GtnFrameworkSqlQueryEngine sqlQueryEngine = new GtnFrameworkSqlQueryEngine();
		dbData = (List<Object[]>) sqlQueryEngine.executeSelectQuery(stringQuery);
		return dbData;
	}

	public void updateCompanyFinancial(Object[] updateData) {

		String stringQuery = getupdateCompanyFinancialQuery();
		GtnFrameworkDataType[] dataHeader = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.INTEGER,
				GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING, GtnFrameworkDataType.INTEGER };
		try {
			gtnGeneralServiceController.executeUpdateQuery(stringQuery, updateData, dataHeader);
		} catch (GtnFrameworkGeneralException ex) {
			LOGGER.error("Exception in executig query-", ex);
		}

	}

	private String getholidaysForCurrentMonthQuery() {
		return " SELECT " + "HOLIDAYS_PERIOD_DATE " + "FROM CALENDAR_CONFIG_DETAILS "
				+ "where CALENDAR_CONFIG_MASTER_SID = ? and MONTH(HOLIDAYS_PERIOD_DATE)=?";
	}

	private String getCompanyAutoQuery() {
		return "select" + " CFAC.COMPANY_MASTER_SID, CFAC.BUSINESS_DAY, CFAC.\"HOUR\", "
				+ "CFAC.\"MINUTE\", CFAC.CALENDAR, cm.CREATED_BY " + "from dbo.COMPANY_FINANCIAL_CLOSE_AUTO CFAC\n"
				+ "join COMPANY_MASTER cm " + "on cm.COMPANY_MASTER_SID = CFAC.COMPANY_MASTER_SID";
	}

	private String getupdateCompanyFinancialQuery() {
		return "INSERT\n" + "    INTO\n" + "        dbo.COMPANY_FINANCIAL_CLOSE(\n"
				+ "            COMPANY_MASTER_SID,\n" + "            MODE,\n" + "            CALENDAR,\n"
				+ "            PERIOD_SID,\n" + "            BUSINESS_DAY,\n" + "            HOUR,\n"
				+ "            MINUTE,\n" + "            STATUS,\n" + "            STATUS_PERIOD_DATE,\n"
				+ "            CREATED_BY,\n" + "            CREATED_DATE\n" + "        ) SELECT\n"
				+ "            CMA.COMPANY_MASTER_SID,\n" + "            HT1.HELPER_TABLE_SID AS MODE,\n"
				+ "            CCG.CALENDAR_CONFIG_MASTER_SID AS CALENDAR,\n" + "            PD.PERIOD_SID AS PERIOD,\n"
				+ "            cma.BUSINESS_DAY,\n" + "            cma.\"HOUR\",\n" + "            cma.\"MINUTE\",\n"
				+ "            HT3.HELPER_TABLE_SID AS STATUS,\n" + "            getdate() AS crea,\n"
				+ "            ?,\n" + "            getdate()\n" + "        FROM\n"
				+ "            dbo.COMPANY_FINANCIAL_CLOSE_AUTO CMA,\n" + "            dbo.HELPER_TABLE HT1,\n"
				+ "            dbo.CALENDAR_CONFIG_MASTER CCG,\n" + "            dbo.HELPER_TABLE HT3,\n"
				+ "            PERIOD PD\n" + "        WHERE\n" + "            HT1.DESCRIPTION = 'AUTO'\n"
				+ "            AND HT1.LIST_NAME = 'ARM_MODE'\n"
				+ "            AND CCG.CALENDAR_CONFIG_MASTER_SID = ?\n"
				+ "            AND HT3.DESCRIPTION = 'CLOSED'\n" + "            AND HT3.LIST_NAME = 'ARM_STATUS'\n"
				+ "            AND PD.\"MONTH\" = ?\n" + "            AND PD.\"YEAR\" = ?\n"
				+ "            AND  CMA.COMPANY_MASTER_SID= ?";
	}

}
