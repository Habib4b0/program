/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.forecastconfiguration.util;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import com.stpl.gtn.gtn2o.ws.constants.common.GtnWsNumericConstants;
import com.stpl.gtn.gtn2o.ws.forecastconfiguration.constants.GtnWsForecastConfigurationConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnWsForecastConfigurationUtil {
	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsForecastConfigurationUtil.class);

	private GtnWsForecastConfigurationUtil() {

	}

	private static final Map<String, Integer> frequencyIntervalMap = new HashMap<>();

	public static Map<String, Integer> getFrequencyIntervalMap() {
		if (frequencyIntervalMap.isEmpty()) {
			frequencyIntervalMap.put(GtnWsForecastConfigurationConstants.ANNUAL, GtnWsNumericConstants.TWELVE);
			frequencyIntervalMap.put(GtnWsForecastConfigurationConstants.SEMI_ANNUAL, GtnWsNumericConstants.SIX);
			frequencyIntervalMap.put(GtnWsForecastConfigurationConstants.QUARTER, GtnWsNumericConstants.THREE);
			frequencyIntervalMap.put(GtnWsForecastConfigurationConstants.MONTHLY, GtnWsNumericConstants.ONE);
		}
		return frequencyIntervalMap;
	}

	public static String getHistoryDetail(int historyNum, String frequency, Date fromDate) {
		List<Integer> list = getCurrentDetails(frequency, fromDate);
		int frequencyDivision = list.get(0);
		int endPeriod = list.get(1);
		int endYear = list.get(GtnWsNumericConstants.TWO);
		int startFreq = endPeriod + 1;
		int startYear = endYear;
		LOGGER.info("startYear==>>" + startYear);
		LOGGER.info("startFreq==>>" + startFreq);
		LOGGER.info("endPeriod==>>" + endPeriod);
		LOGGER.info("endYear==>>" + endYear);
		LOGGER.info("historyNum==>>" + historyNum);
		if (frequencyDivision == 1) {
			startYear = startYear - historyNum;
			return Integer.toString(startYear);
		} else {
			int tempFreq = historyNum - endPeriod + 1;
			startFreq = startFreq - historyNum - 1;
			LOGGER.info("tempFreq===================" + tempFreq);
			if (tempFreq > 0) {
				startYear = startYear - (tempFreq / frequencyDivision);
				startFreq = 1;
				if (tempFreq % frequencyDivision > 0) {
					startYear = startYear - 1;
					startFreq = frequencyDivision - (tempFreq % frequencyDivision) + 1;
				}
			}
		}
		String ret = "";
		if (frequencyDivision == GtnWsNumericConstants.FOUR) {
			ret = "Q" + startFreq + " " + startYear;
		} else if (frequencyDivision == GtnWsNumericConstants.TWO) {
			ret = "S" + startFreq + " " + startYear;
		} else if (frequencyDivision == GtnWsNumericConstants.TWELVE) {
			if (startFreq >= 0 && startFreq <= GtnWsNumericConstants.ELEVEN) {
				ret = getMonthForInt(startFreq) + " " + startYear;
			} else {
				ret = getMonthForInt(startFreq) + " " + (startYear + 1);
			}
		}
		return ret;
	}

	public static String getFutureDetail(int futureNum, String frequency, Date fromDate) {
		List<Integer> list = getCurrentDetails(frequency, fromDate);
		int frequencyDivision = list.get(0);
		int futurePeriod = (frequencyDivision == 4) ? list.get(1) - 1 : list.get(1);
		int endYear = list.get(GtnWsNumericConstants.TWO);
		int futureFreq = futurePeriod + 1;
		int futureYear = endYear;
		LOGGER.info("startYear==>>" + futureYear);
		LOGGER.info("futureFreq==>>" + futureFreq);
		LOGGER.info("futurePeriod==>>" + futurePeriod);
		LOGGER.info("endYear==>>" + endYear);
		LOGGER.info("futureNum==>>" + futureNum);
		if (frequencyDivision == 1) {
			futureYear = futureYear + futureNum;
			LOGGER.info("startYear = startYear - historyNum;" + futureYear);
			return Integer.toString(futureYear);
		}

		int futtempFreq = futureNum + futurePeriod + 1;
		futureFreq = futureFreq + futureNum - 1;
		List<Integer> calculateForecastPeriod = new ArrayList<>();
		if (frequencyDivision == 12) {
			calculateForecastPeriod = getCalForecastPeriodMonthly(futtempFreq, frequencyDivision, futureFreq,
					futureYear);
		}
		if (frequencyDivision == 2 || frequencyDivision == 4) {
			calculateForecastPeriod = getCalForecastPeriodQuartSemmiAnnually(futtempFreq, frequencyDivision, futureFreq,
					futureYear);
		}

		frequencyDivision = calculateForecastPeriod.get(0);
		futureFreq = calculateForecastPeriod.get(1);
		futureYear = calculateForecastPeriod.get(2);

		return returnYearFormat(frequencyDivision, futureFreq, futureYear);
	}

	private static String returnYearFormat(int frequencyDivision, int futureFreq, int futureYear) {
		String ret = "";
		if (frequencyDivision == GtnWsNumericConstants.FOUR) {
			ret = "Q" + (futureFreq + 1) + " " + futureYear;
		} else if (frequencyDivision == GtnWsNumericConstants.TWO) {
			ret = "S" + (futureFreq) + " " + futureYear;
		} else if (frequencyDivision == GtnWsNumericConstants.TWELVE) {
			if (futureFreq >= 0 && futureFreq <= GtnWsNumericConstants.ELEVEN) {
				ret = getMonthForInt(futureFreq) + " " + futureYear;
			} else {
				ret = getMonthForInt(futureFreq) + " " + (futureYear + 1);
			}
		}
		return ret;
	}

	public static String getMonthForInt(int num) {
		String month;
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] months = dfs.getShortMonths();
		if (num >= 0 && num <= GtnWsNumericConstants.ELEVEN) {
			month = months[num];
		} else {
			month = months[0];
		}
		return month;
	}

	static int getPeriodFromDate(Date date, int division) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return (cal.get(Calendar.MONTH) / division) + 1;
	}

	static List<Integer> getCurrentDetails(String frequency, Date fromDate) {
		int frequencyDivision = 1;
		int endPeriod = 1;
		Calendar cal = Calendar.getInstance();
		cal.setTime(fromDate);
		if (frequency.equals(GtnWsForecastConfigurationConstants.QUARTER)) {
			frequencyDivision = GtnWsNumericConstants.FOUR;
			endPeriod = getPeriodFromDate(fromDate, GtnWsNumericConstants.THREE);
		} else if (frequency.equals(GtnWsForecastConfigurationConstants.SEMI_ANNUAL)) {
			frequencyDivision = GtnWsNumericConstants.TWO;
			endPeriod = getPeriodFromDate(fromDate, GtnWsNumericConstants.SIX);
		} else if (frequency.equals(GtnWsForecastConfigurationConstants.MONTHLY)) {
			frequencyDivision = GtnWsNumericConstants.TWELVE;
			endPeriod = cal.get(Calendar.MONTH) + 1;
		} else if (frequency.equals(GtnWsForecastConfigurationConstants.ANNUAL)) {
			frequencyDivision = 1;
			endPeriod = cal.get(Calendar.YEAR);
		}
		List<Integer> list = new ArrayList<>();
		list.add(frequencyDivision);
		list.add(endPeriod);
		list.add(cal.get(Calendar.YEAR));
		return list;
	}

	private static void calculateFromDate(Calendar cal, int interval, int periodOffset, int monthOffset) {
		int periodConstant = GtnWsNumericConstants.TWELVE / periodOffset;
		int monthDifference = interval * periodConstant;
		int currentMonth = cal.get(Calendar.MONTH);
		int period = (currentMonth / periodConstant) + 1;
		int firstMonth = ((period - 1) * periodConstant) - monthOffset;
		cal.set(Calendar.MONTH, firstMonth);
		cal.add(Calendar.MONTH, -monthDifference);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
	}

	private static void calculateToDate(Calendar cal, int interval, int periodOffset, int monthOffset) {
		int periodConstant = GtnWsNumericConstants.TWELVE / periodOffset;
		int monthDifference = interval * periodConstant;
		int currentMonth = cal.get(Calendar.MONTH);
		int period = (currentMonth / periodConstant) + 1;
		int lastMonth = ((period * periodConstant) - 1) - monthOffset;
		cal.set(Calendar.MONTH, lastMonth);
		cal.add(Calendar.MONTH, +monthDifference);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
	}

	public static Calendar convertPeriod(final int flag, final String frequency, final int interval) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getTimeZone("GMT"));
		int month = flag * interval * getFrequencyIntervalMap().get(frequency);
		cal.add(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.MILLISECOND, cal.getActualMinimum(Calendar.MILLISECOND));
		cal.set(Calendar.SECOND, cal.getActualMinimum(Calendar.SECOND));
		cal.set(Calendar.MINUTE, cal.getActualMinimum(Calendar.MINUTE));
		cal.set(Calendar.HOUR, cal.getActualMinimum(Calendar.HOUR));
		return cal;
	}

	public static Date convertToPeriod(final int flag, final String frequency, final int interval) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getTimeZone("GMT"));
		int monthOffset = GtnWsForecastConfigurationConstants.MONTHLY.equalsIgnoreCase(frequency) ? 1 : 0;
		int periodOffset = GtnWsNumericConstants.TWELVE / getFrequencyIntervalMap().get(frequency);
		if (flag == 1) {
			calculateToDate(cal, interval, periodOffset, monthOffset);
		} else {
			calculateFromDate(cal, interval, periodOffset, monthOffset);
		}
		return cal.getTime();
	}

	private static List<Integer> getCalForecastPeriodMonthly(int futtempFreq, int frequencyDivision, int futureFreq,
			int futureYear) {
		int newFutureYear = futureYear;
		int newfutureFreq = futureFreq;
		if ((futtempFreq > frequencyDivision)) {// monthly
			newFutureYear = newFutureYear + (futtempFreq / frequencyDivision);
			newfutureFreq = 1;
			if (futtempFreq % frequencyDivision > 0) {
				newfutureFreq = (futtempFreq % frequencyDivision) - 1;
			} else {
				newfutureFreq = 11;
				newFutureYear = newFutureYear - 1;
			}

		}
		List<Integer> list = new ArrayList<>();
		list.add(frequencyDivision);
		list.add(newfutureFreq);
		list.add(newFutureYear);
		return list;
	}

	private static List<Integer> getCalForecastPeriodQuartSemmiAnnually(int futtempFreq, int frequencyDivision,
			int futureFreq, int futureYear) {
		int newFutureYear = futureYear;
		int newfutureFreq = futureFreq;
		if ((futtempFreq > frequencyDivision) && frequencyDivision != 2) {
			newFutureYear = newFutureYear + (futtempFreq / frequencyDivision);
			newfutureFreq = 1;
			if (futtempFreq % frequencyDivision > 0) {
				newfutureFreq = (futtempFreq % frequencyDivision) - 1;
			} else if ((futtempFreq % frequencyDivision == 0)) {// quarter
				newfutureFreq = 3;
				newFutureYear = newFutureYear - 1;
			}
		} else {// semi-annual
			newFutureYear = newFutureYear + (futtempFreq / frequencyDivision) - 1;
			newfutureFreq = 1;
			if (futtempFreq % frequencyDivision > 0) {
				newfutureFreq = (futtempFreq % frequencyDivision) + 1;
			}
		}
		List<Integer> list = new ArrayList<>();
		list.add(frequencyDivision);
		list.add(newfutureFreq);
		list.add(newFutureYear);
		return list;
	}

}
