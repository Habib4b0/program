/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.index.util;

import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.index.dto.ForecastDTO;
import com.vaadin.v7.ui.ComboBox;
import java.util.List;
import static com.stpl.app.gcm.itemmanagement.index.util.ConstantsUtil.FrequencyConstants.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static com.stpl.app.gcm.itemmanagement.index.util.ConstantsUtil.ProjectionConstants.FREQUENCY_DIVISION;
import static com.stpl.app.gcm.itemmanagement.index.util.ConstantsUtil.ProjectionConstants.HISTORY_END_YEAR;
import static com.stpl.app.gcm.itemmanagement.index.util.ConstantsUtil.ProjectionConstants.HISTORY_END_MONTH;
import static com.stpl.app.gcm.itemmanagement.index.util.ConstantsUtil.ProjectionConstants.HISTORY_END_PERIOD;
import static com.stpl.app.gcm.itemmanagement.index.util.ConstantsUtil.ProjectionConstants.HISTORY_END_DAY;
import com.stpl.ifs.ui.util.NumericConstants;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

public class CommonUtils {

    public static void frequenceValueChange(Object value, ComboBox history, SelectionDTO session) {
        if (value == null || SELECT_ONE.getConstant().equals(value.toString())) {
            history.removeAllItems();
            history.addItem(SELECT_ONE.getConstant());
            history.setNullSelectionItemId(SELECT_ONE.getConstant());
            history.setValue(SELECT_ONE.getConstant());
        } else {
            int historyValue = 0;
            List<String> list = null;
            if (ANNUALLY.getConstant().equals(String.valueOf(value))) {
                history.removeAllItems();
                list = loadHistory(ANNUALLY.getConstant(), YEAR.getConstant(), session);
                historyValue = 1;

            } else if (SEMI_ANNUALLY.getConstant().equals(String.valueOf(value))) {
                history.removeAllItems();
                list = loadHistory(SEMI_ANNUALLY.getConstant(), SEMI_ANNUAL.getConstant(), session);
                historyValue = NumericConstants.TWO;

            } else if (QUARTERLY.getConstant().equals(String.valueOf(value))) {
                history.removeAllItems();
                historyValue = NumericConstants.FOUR;
                list = loadHistory(QUARTERLY.getConstant(), QUARTERS.getConstant(), session);
            } else if (MONTHLY.getConstant().equals(String.valueOf(value))) {
                history.removeAllItems();
                list = loadHistory(MONTHLY.getConstant(), MONTHS.getConstant(), session);
                historyValue = NumericConstants.TWELVE;
            }
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    int j = i + 1;
                    Object itemId = j;
                    history.addItem(itemId);
                    history.setItemCaption(itemId, list.get(i));
                }
                history.setValue(historyValue);
            }
        }
    }

    public static List<String> loadHistory(String frequency, String period, SelectionDTO session) {

        List<String> history;
        history = session.getFrequencyAndQuaterValue(frequency);
        Integer endValue = 0;
        if (history == null || history.isEmpty()) {
            Map<String, Integer> historyEndDetails = getHistoryEndDetails(session, frequency);
            endValue = getProjections(session.getForecastDTO().getHistoryStartDate(), getDate(historyEndDetails.get(HISTORY_END_MONTH.getConstant()), historyEndDetails.get(HISTORY_END_YEAR.getConstant())), frequency);
            history = CommonUtils.getHistoryDdlbList(endValue, period);
            session.addFrequencyAndQuater(frequency, history);
        }
        return history;
    }

    public static int getProjections(Date startDate, Date endDate, String frequency) {
        if (endDate.after(startDate)) {
            if (frequency.equals(ANNUALLY.getConstant())) {
                return endDate.getYear() - startDate.getYear();
            } else {
                Calendar startCalendar = Calendar.getInstance();
                startCalendar.setTime(startDate);
                Calendar endCalendar = Calendar.getInstance();
                endCalendar.setTime(endDate);
                int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
                int diffMonth = diffYear * NumericConstants.TWELVE + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
                if (frequency.equals(QUARTERLY.getConstant())) {
                    return diffMonth / NumericConstants.THREE;
                } else if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
                    return diffMonth / NumericConstants.SIX;
                } else if (frequency.equals(MONTHLY.getConstant())) {
                    return diffMonth;
                }
                return 0;

            }
        }
        return 0;
    }

    public static List getHistoryDdlbList(int endValue, String period) {
        List history = new ArrayList();
        if (period.equals("Year")) {
            period = "Years";
        }
        for (int i = 1; i <= endValue; i++) {
            if ((i == 1)
                    && (QUARTERS.getConstant().equals(period) || MONTHS
                    .getConstant().equals(period) || "Years".equals(period))) {
                String freq = period.replace("s", StringUtils.EMPTY);
                history.add(String.valueOf(i) + ConstantsUtil.SPACE + freq);
            } else {
                history.add(i + ConstantsUtil.SPACE + period);
            }
        }
        return history;
    }

    public static Date getDate(int month, int year) {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(year, month, 1);
        return startCalendar.getTime();
    }

    static int getPeriod(int monthNo, int division) {
        return ((monthNo - 1) / division) + 1;
    }

    static int getEndDay(int monthNo, int year) {
        Calendar ob = Calendar.getInstance();
        ob.set(year, monthNo - 1, 1);
        return ob.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static Map<String, Integer> getHistoryEndDetails(SelectionDTO session, String frequency) {
        Map<String, Integer> history;
        history = session.getHistoryEndDetails(frequency);
        if (history == null || history.isEmpty()) {
            history = getHistoryEndDetail(session, frequency);
            session.addHistoryEndDetails(frequency, history);
        }
        return history;
    }

    public static ForecastDTO setHistoryLimit() {
        ForecastDTO forecastDTO = new ForecastDTO();
        Date tempDate = new Date();
        tempDate.setMonth(tempDate.getMonth() - 1);
        forecastDTO.setHistoryEndYear(tempDate.getYear() + NumericConstants.ONE_NINE_ZERO_ZERO);
        forecastDTO.setHistoryEndMonth(tempDate.getMonth() + 1);
        forecastDTO.setHistoryEndDate(tempDate);
        Date tempStartDate = new Date();
        tempStartDate.setYear(tempStartDate.getYear() - NumericConstants.THREE);
        tempStartDate.setMonth(0);
        forecastDTO.setHistoryStartYear(tempStartDate.getYear() + NumericConstants.ONE_NINE_ZERO_ZERO);
        forecastDTO.setHistoryStartMonth(tempStartDate.getMonth() + 1);
        forecastDTO.setHistoryStartDate(tempStartDate);

        return forecastDTO;
    }

    private static Map<String, Integer> getHistoryEndDetail(SelectionDTO session, String frequency) {
        Map<String, Integer> history = new HashMap<>();
        session.setForecastDTO(setHistoryLimit());
        int frequencyDivision = 1;
        int historyEndPeriod = 1;
        int historyEndMonth = 1;
        int historyEndYear = 1;
        int forecastStartPeriod = 1;
        if (frequency.equals(QUARTERLY.getConstant())) {
            frequencyDivision = NumericConstants.FOUR;
            historyEndPeriod = getPeriod(session.getForecastDTO().getHistoryEndMonth(), NumericConstants.THREE);
        } else if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
            frequencyDivision = NumericConstants.TWO;
            historyEndPeriod = getPeriod(session.getForecastDTO().getHistoryEndMonth(), NumericConstants.SIX);
        } else if (frequency.equals(MONTHLY.getConstant())) {
            frequencyDivision = NumericConstants.TWELVE;
            historyEndPeriod = session.getForecastDTO().getHistoryEndMonth();
        } else if (frequency.equals(ANNUALLY.getConstant())) {
            frequencyDivision = 1;
            historyEndPeriod = session.getForecastDTO().getHistoryEndYear();
        }
        historyEndMonth = session.getForecastDTO().getHistoryEndMonth();
        historyEndYear = session.getForecastDTO().getHistoryEndYear();
        if (historyEndPeriod == forecastStartPeriod) {
            historyEndPeriod--;
            if (frequencyDivision == 1) {
                historyEndYear = historyEndYear - 1;
                historyEndMonth = NumericConstants.TWELVE;
            } else if (historyEndPeriod < 1) {
                historyEndPeriod = frequencyDivision;
                historyEndMonth = NumericConstants.TWELVE;
                historyEndYear = historyEndYear - 1;
            } else {
                historyEndMonth = (NumericConstants.TWELVE / frequencyDivision) * historyEndPeriod;
            }
        }
        history.put(FREQUENCY_DIVISION.getConstant(), frequencyDivision);
        history.put(HISTORY_END_YEAR.getConstant(), historyEndYear);
        history.put(HISTORY_END_MONTH.getConstant(), historyEndMonth);
        history.put(HISTORY_END_PERIOD.getConstant(), historyEndPeriod);
        history.put(HISTORY_END_DAY.getConstant(), getEndDay(historyEndMonth, historyEndYear));
        return history;
    }
}
