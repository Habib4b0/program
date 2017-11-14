package com.stpl.gtn.gtn2o.ws.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsMethodologyBean;
import com.stpl.gtn.gtn2o.ws.constants.GtnWsFrequencyConstants;

public class GtnWsPeriodCalculationService {

	private static volatile GtnWsPeriodCalculationService gtnPeriodCalculationService;

	private GtnWsPeriodCalculationService() {

	}

	public static synchronized GtnWsPeriodCalculationService getInstance() {
		if (gtnPeriodCalculationService == null) {
			gtnPeriodCalculationService = new GtnWsPeriodCalculationService();
		}
		return gtnPeriodCalculationService;
	}

	/**
	 * To return the start month and year of selected periods.
	 *
	 * @param frequency
	 * @param selectedPreiod
	 * @param isToCalculateEndDate
	 * @return
	 * @throws ParseException
	 */
	public Integer[] getStartMonthAndYear(final GtnWsFrequencyConstants frequency, final String selectedPreiod,
			boolean isToCalculateEndDate) throws Exception {
		Integer[] monthAndYear = new Integer[2];
		switch (frequency) {
		case ANNUALLY:
			monthAndYear[0] = 1;
			monthAndYear[1] = Integer.valueOf(selectedPreiod);
			break;
		case QUARTERLY:
			monthAndYear = calculateMonthAndYear(selectedPreiod, 3, "Q", isToCalculateEndDate);
			break;
		case SEMIANNUAL:
			monthAndYear = calculateMonthAndYear(selectedPreiod, 6, "S", isToCalculateEndDate);
			break;
		case MONTHLY:
			monthAndYear = getIntForMonthAndYear(selectedPreiod);
			break;
		default:
			break;
		}
		return monthAndYear;
	}

	/**
	 * To calculate the starting month and year based on the selected periods.
	 * Eg: Q2 2016 is return as 4th month and 2016 year
	 *
	 * @param selectedPeriod
	 * @param division
	 * @param valueToReplace
	 * @param isToCalculateEndDate
	 * @return
	 */
	private Integer[] calculateMonthAndYear(String selectedPeriod, int division, final String valueToReplace,
			boolean isToCalculateEndDate) {
		selectedPeriod = selectedPeriod.replace(valueToReplace, "");
		String[] monthAndYear = selectedPeriod.split(" ");
		int value = Integer.valueOf(monthAndYear[0]);
		int month = 0;
		if (isToCalculateEndDate) {
			month = ((value - 1) * division) + division;
		} else {
			month = ((value - 1) * division) + 1;
		}

		return new Integer[] { month, Integer.valueOf(monthAndYear[1]) };
	}

	/**
	 * To calculate the starting month and year based on the selected periods.
	 * Eg: Feb 2016 is return month as 2 and year as 2016
	 *
	 * @param selectedPreiod
	 * @return
	 * @throws ParseException
	 */
	private Integer[] getIntForMonthAndYear(String selectedPeriod) throws Exception {
		String[] monthAndYear = selectedPeriod.split(" ");
		int month;

		try {
			Date date;
			date = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(monthAndYear[0]);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			month = cal.get(Calendar.MONTH);
		} catch (ParseException e) {
			month = Integer.valueOf(monthAndYear[0].replace("M", ""));
		}

		return new Integer[] { month + 1, Integer.valueOf(monthAndYear[1]) };
	}

	/**
	 * To get the months. For Example, Q2 2014 is selected, it will return 4
	 * 2014, 5 2014 and 6 2014 Since 4th,5th and 6th month fall in Q2. It will
	 * used to get the data in file(In file we will month level data)
	 *
	 * @param frequency
	 * @param period
	 * @return
	 * @throws ParseException
	 */
	public List<Integer[]> getMonthFromPeriod(final GtnWsFrequencyConstants frequency, final String period)
			throws Exception {
		List<Integer[]> months = new ArrayList<>();
		Integer[] monthAndYear = getStartMonthAndYear(frequency, period, false);

		switch (frequency) {
		case ANNUALLY:
			monthAndYear[0] = 1;
			getSelectedMonth(monthAndYear, 12, months);
			break;
		case QUARTERLY:
			getSelectedMonth(monthAndYear, 3, months);
			break;
		case SEMIANNUAL:
			getSelectedMonth(monthAndYear, 6, months);
			break;
		case MONTHLY:
			months.add(monthAndYear);
			break;
		default:
			break;
		}
		return months;
	}

	/**
	 * It will calculate number of months in selected period(ie: Q2, S2 etc)
	 *
	 * @param monthAndYear
	 * @param numberOfMonths
	 * @param months
	 * @return
	 */
	private List<Integer[]> getSelectedMonth(Integer[] monthAndYear, int numberOfMonths, final List<Integer[]> months) {
		int startingMonth = monthAndYear[0];
		for (int j = 0; j < numberOfMonths; j++) {
			months.add(new Integer[] { startingMonth, monthAndYear[1] });
			startingMonth++;
		}
		return months;
	}

	public void getCalculationPeriods(final List<Integer[]> periods, GtnWsMethodologyBean gtnMethodologyBean)
			throws Exception {	

		Integer[] calculatedStartMonthAndYear = getStartMonthAndYear(gtnMethodologyBean.getFrequency(),
				gtnMethodologyBean.getCalculationStartPeriod(), false);
		
		Integer[] calculatedEndMonthAndYear = null;

		if (gtnMethodologyBean.getCalculationEndPeriod() == null
				|| "".equals(gtnMethodologyBean.getCalculationEndPeriod())) {

			calculatedEndMonthAndYear = new Integer[] {gtnMethodologyBean.getProjectionEndMonth()+1,
					gtnMethodologyBean.getProjectionEndYear()};
		} else {
			calculatedEndMonthAndYear = getStartMonthAndYear(gtnMethodologyBean.getFrequency(),
					gtnMethodologyBean.getCalculationEndPeriod(), true);
		}

		Calendar startPeriod = Calendar.getInstance();
		startPeriod.set(Calendar.MONTH, calculatedStartMonthAndYear[0] - 1);
		startPeriod.set(Calendar.YEAR, calculatedStartMonthAndYear[1]);
		startPeriod.set(Calendar.DATE,1);
		
		Calendar endPeriod = Calendar.getInstance();
		endPeriod.set(Calendar.MONTH, calculatedEndMonthAndYear[0]);
		endPeriod.set(Calendar.YEAR, calculatedEndMonthAndYear[1]);
		endPeriod.set(Calendar.DATE,1);		
		
		while (startPeriod.before(endPeriod)) {
			periods.add(new Integer[] { startPeriod.get(Calendar.MONTH) + 1, startPeriod.get(Calendar.YEAR)});
			startPeriod.add(Calendar.MONTH, 1);
		}

	}

}
