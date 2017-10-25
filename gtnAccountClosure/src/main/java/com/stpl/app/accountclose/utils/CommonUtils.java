/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.utils;

import static com.stpl.app.accountclose.gtnbalancereport.utils.Constants.VariableConstants.FREQUENCY_DIVISION;
import static com.stpl.app.accountclose.gtnbalancereport.utils.Constants.VariableConstants.HISTORY_END_DAY;
import static com.stpl.app.accountclose.gtnbalancereport.utils.Constants.VariableConstants.HISTORY_END_MONTH;
import static com.stpl.app.accountclose.gtnbalancereport.utils.Constants.VariableConstants.HISTORY_END_PERIOD;
import static com.stpl.app.accountclose.gtnbalancereport.utils.Constants.VariableConstants.HISTORY_END_YEAR;
import static com.stpl.app.accountclose.gtnbalancereport.utils.Constants.VariableConstants.SPACE;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import com.stpl.app.accountclose.utils.Constants.FrequencyConstants;
import static com.stpl.app.accountclose.utils.Constants.FrequencyConstants.ANNUAL;
import static com.stpl.app.accountclose.utils.Constants.FrequencyConstants.ANNUALLY;
import static com.stpl.app.accountclose.utils.Constants.FrequencyConstants.MONTHLY;
import static com.stpl.app.accountclose.utils.Constants.FrequencyConstants.MONTHS;
import static com.stpl.app.accountclose.utils.Constants.FrequencyConstants.QUARTERLY;
import static com.stpl.app.accountclose.utils.Constants.FrequencyConstants.QUARTERS;
import static com.stpl.app.accountclose.utils.Constants.FrequencyConstants.SEMI_ANNUAL;
import static com.stpl.app.accountclose.utils.Constants.FrequencyConstants.SEMI_ANNUALLY;
import static com.stpl.app.accountclose.utils.Constants.FrequencyConstants.YEAR;
import static com.stpl.app.accountclose.utils.Constants.SELECT_ONE;
import com.vaadin.ui.ComboBox;
import static java.lang.Boolean.FALSE;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.portlet.PortletConfig;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author alok.v
 */
public class CommonUtils {

    /**
     * The log.
     */
    private static final Logger LOGGER = Logger.getLogger(CommonUtils.class);
    public static PortletConfig portletConfig;
    public final static String COMPANY_NAME = "companyName";
    public static final String BUSINESS_PROCESS_TYPE_BR = "BaseRate";
    public static final String BUSINESS_PROCESS_TYPE_FDA = "FIXED_DOLLAR";
    public static final String WORKFLOWSTATUS = "WorkFlowStatus";
    public static final String WORKFLOW_NOT_SAVED = "Not Saved";
    /**
     * The Constant CHAR_ASTERISK.
     */
    public static final char CHAR_ASTERISK = '*';
    /**
     * The Constant CHAR_PERCENT.
     */
    public static final char CHAR_PERCENT = '%';

    /**
     *
     * @param portletConfig
     */
    public static void setPortalConfig(final PortletConfig portletConfig) {
        CommonUtils.portletConfig = portletConfig;
    }

    public static List<Integer> convertStringListToInteger(List<String> stringList) {
        List<Integer> integerList = new ArrayList<Integer>();

        for (String sid : stringList) {
            integerList.add(Integer.parseInt(sid));
        }

        return integerList;
    }

    public int getFrequencyRange(String frequency) {
        if (FrequencyConstants.SEMI_ANNUALLY.getConstant().equals(frequency)) {
            frequency = FrequencyConstants.SEMI_ANNUALLY.getConstant();
        } else if (FrequencyConstants.ANNUALLY.getConstant().equals(frequency)) {
            frequency = FrequencyConstants.ANNUALLY.getConstant();
        }

        int frequencyRange = 0;
        if (FrequencyConstants.SEMI_ANNUALLY.getConstant().equalsIgnoreCase(frequency)) {
            frequencyRange = 6;
        } else if (FrequencyConstants.ANNUALLY.getConstant().equalsIgnoreCase(frequency)) {
            frequencyRange = 3;
        } else if (FrequencyConstants.QUARTERLY.getConstant().equalsIgnoreCase(frequency)) {
            frequencyRange = 12;
        } else if (FrequencyConstants.MONTHLY.getConstant().equalsIgnoreCase(frequency)) {
            frequencyRange = 36;
        }
        return frequencyRange;
    }

    public boolean getNull(String value) {
        boolean result = true;
        if (value != null && value != StringUtils.EMPTY && value != "null") {
            result = false;
        }
        return result;
    }

    public static int frequenceValueChange(Object value, ComboBox history, ComboBox toComboBox, SessionDTO session) {
        int historyValue = 0;
        if (value == null || SELECT_ONE.equals(value.toString())) {
            history.removeAllItems();
            history.addItem(0);
            history.setItemCaption(0, SELECT_ONE);
            history.select(0);
        } else {
            List<String> list = null;
            if (ANNUALLY.getConstant().equals(String.valueOf(value)) || ANNUAL.getConstant().equalsIgnoreCase(String.valueOf(value))) {
                history.removeAllItems();
                toComboBox.removeAllItems();
                list = loadHistory(ANNUALLY.getConstant(), YEAR.getConstant(), session);
                historyValue = 1;

            } else if (SEMI_ANNUALLY.getConstant().equals(String.valueOf(value)) || SEMI_ANNUAL.getConstant().equalsIgnoreCase(String.valueOf(value))) {
                history.removeAllItems();
                toComboBox.removeAllItems();
                list = loadHistory(SEMI_ANNUALLY.getConstant(), SEMI_ANNUAL.getConstant(), session);
                historyValue = 2;

            } else if (QUARTERLY.getConstant().equals(String.valueOf(value))) {
                history.removeAllItems();
                toComboBox.removeAllItems();
                historyValue = 2;
                list = loadHistory(QUARTERLY.getConstant(), QUARTERS.getConstant(), session);
            } else if (MONTHLY.getConstant().equals(String.valueOf(value))) {
                history.removeAllItems();
                toComboBox.removeAllItems();
                list = loadHistory(MONTHLY.getConstant(), MONTHS.getConstant(), session);
                historyValue = 12;
            }
            if (list != null) {
                history.setNullSelectionAllowed(FALSE);
                toComboBox.setNullSelectionAllowed(FALSE);
                for (int i = 0; i < list.size(); i++) {
                    history.addItem(i + 1);
                    history.setItemCaption(i + 1, list.get(i));
                    toComboBox.addItem(i + 1);
                    toComboBox.setItemCaption(i + 1, list.get(i));
                }
                history.setValue(historyValue);
                toComboBox.setValue(historyValue);
            }
        }
        return historyValue;
    }

    public static List<String> loadHistory(String frequency, String period, SessionDTO session) {
        LOGGER.info("Entering loadHistory method");
        List<String> history = new ArrayList<String>();
        history = session.getFrequencyAndQuaterValue(frequency);
        Integer endValue = 0;
        if (history == null || history.isEmpty()) {
            Map<String, Integer> historyEndDetails = getHistoryEndDetails(session, frequency);
            endValue = getProjections(session.getDsFromDate(), getDate(historyEndDetails.get(HISTORY_END_MONTH.getConstant()), historyEndDetails.get(HISTORY_END_YEAR.getConstant())), frequency);

            history = CommonUtils.getHistoryDdlbList(endValue, frequency, period);
            session.addFrequencyAndQuater(frequency, history);
        }
        LOGGER.info("End of loadHistory method");
        return history;
    }

    public static Map<String, Integer> getHistoryEndDetails(SessionDTO session, String frequency) {
        Map<String, Integer> history = new HashMap<String, Integer>();
        history = session.getHistoryEndDetails(frequency);
        if (history == null || history.isEmpty()) {
            history = getHistoryEndDetail(session, frequency);
            session.addHistoryEndDetails(frequency, history);
        }
        return history;
    }

    private static Map<String, Integer> getHistoryEndDetail(SessionDTO session, String frequency) {
        Map<String, Integer> history = new HashMap<String, Integer>();
        int frequencyDivision = 1;
        int historyEndPeriod = 1;
        int historyEndMonth = 1;
        int historyEndYear = 1;
        int forecastStartPeriod = 1;
        if (frequency.equals(QUARTERLY.getConstant())) {
            frequencyDivision = 4;
            historyEndPeriod = getPeriod(session.getDsToDate().getMonth() + 1, 3);
            forecastStartPeriod = getPeriod(session.getDsFromDate().getMonth() + 1, 3);
        } else if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
            frequencyDivision = 2;
            historyEndPeriod = getPeriod(session.getDsToDate().getMonth() + 1, 6);
            forecastStartPeriod = getPeriod(session.getDsFromDate().getMonth() + 1, 6);
        } else if (frequency.equals(MONTHLY.getConstant())) {
            frequencyDivision = 12;
            historyEndPeriod = session.getDsToDate().getMonth() + 1;
            forecastStartPeriod = session.getDsFromDate().getMonth() + 1;
        } else if (frequency.equals(ANNUALLY.getConstant())) {
            frequencyDivision = 1;
            historyEndPeriod = session.getDsToDate().getYear() + 1900;
            forecastStartPeriod = session.getDsFromDate().getYear() + 1900;
        }
        historyEndMonth = session.getDsToDate().getMonth() + 1;
        historyEndYear = session.getDsToDate().getYear() + 1900;
        if (historyEndPeriod == forecastStartPeriod && historyEndYear == session.getDsFromDate().getYear() + 1900) {
            historyEndPeriod--;
            if (frequencyDivision == 1) {
                historyEndYear = historyEndYear - 1;
                historyEndMonth = 12;
            } else if (historyEndPeriod < 1) {
                historyEndPeriod = frequencyDivision;
                historyEndMonth = 12;
                historyEndYear = historyEndYear - 1;
            } else {
                historyEndMonth = (12 / frequencyDivision) * historyEndPeriod;
            }
        }
        history.put(FREQUENCY_DIVISION.getConstant(), frequencyDivision);
        history.put(HISTORY_END_YEAR.getConstant(), historyEndYear);
        history.put(HISTORY_END_MONTH.getConstant(), historyEndMonth);
        history.put(HISTORY_END_PERIOD.getConstant(), historyEndPeriod);
        history.put(HISTORY_END_DAY.getConstant(), getEndDay(historyEndMonth, historyEndYear));
        return history;
    }

    static int getEndDay(int monthNo, int year) {
        Calendar ob = Calendar.getInstance();
        ob.set(year, monthNo - 1, 1);
        int daysInMonth = ob.getActualMaximum(Calendar.DAY_OF_MONTH);
        return daysInMonth;
    }

    static int getPeriod(int monthNo, int division) {
        return ((monthNo - 1) / division) + 1;
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
                int diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
                if (frequency.equals(QUARTERLY.getConstant())) {
                    if (diffMonth % 3 == 0) {
                        return diffMonth / 3;
                    } else {
                        return (diffMonth / 3) + 1;
                    }

                } else if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
                    if (diffMonth % 6 == 0) {
                        return diffMonth / 6;
                    } else {
                        return (diffMonth / 6) + 1;
                    }
                } else if (frequency.equals(MONTHLY.getConstant())) {
                    return diffMonth;
                }
            }
        }
        return 0;
    }

    public static List getHistoryDdlbList(int endValue, String frequency, String period) {
        List history = new ArrayList();
        if (period.equals("Year")) {
            period = "Years";
        }
        for (int i = 1; i <= endValue; i++) {
            if ((i == 1)
                    && (QUARTERS.getConstant().equals(period) || MONTHS
                    .getConstant().equals(period) || "Years".equals(period))) {
                String freq = period.replace("s", StringUtils.EMPTY);
                history.add(String.valueOf(i) + SPACE.getConstant() + freq);
            } else {
                history.add(i + SPACE.getConstant() + period);
            }
        }
        return history;
    }

    public static Date getDate(int month, int year) {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(year, month, 1);
        return startCalendar.getTime();
    }

    /**
     * To convert List<String> into a comma separated String
     *
     * @param collectionOfString
     * @param toAddQuote
     * @return
     */
    public String CollectionToString(Collection<?> collectionOfString, boolean toAddQuote) {
        return CollectionToString(collectionOfString, toAddQuote, false);
    }

    /**
     * To convert List<String> into a comma separated String
     *
     * @param collectionOfString
     * @param toAddQuote
     * @param toRemoveSpace
     * @return
     */
    public String CollectionToString(Collection<?> collectionOfString, boolean toAddQuote, boolean toRemoveSpace) {

        String framedString = StringUtils.EMPTY;
        if (collectionOfString != null && !collectionOfString.isEmpty()) {
            if (toAddQuote) {
                framedString += Arrays.toString(collectionOfString.toArray()).replace("[", "'").replace("]", "'").replace(", ", "','");
            } else {
                framedString += Arrays.toString(collectionOfString.toArray()).replace("[", StringUtils.EMPTY).replace("]", StringUtils.EMPTY);
            }

            if (toRemoveSpace) {
                framedString.replace(", ", StringUtils.EMPTY);
            }
        }
        return framedString;
    }

    public int getMonthNo(String mon) {
        int num = 0;
        try {
            Date dat = new SimpleDateFormat("MMM").parse(mon);
            Calendar cal = Calendar.getInstance();
            cal.setTime(dat);
            num = cal.get(Calendar.MONTH);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return num;
    }

    public static String workFlowQuery() {
        String query;
        query = "SELECT WP.PROCESS_SID, WP.PROCESS_NAME, WP.ACTIVE_FLAG AS STATUS, WP.START_DATE, WP.END_DATE, WP.SLA_CALENDAR_MASTER_SID, WP.FREQUENCY, WP.MANUAL_LAST_RUN, WP.MODIFIED_DATE, WP.MODIFIED_BY, SCM.CALENDAR_NAME\n"
                + "FROM WORKFLOW_PROFILE WP \n"
                + "LEFT JOIN HELPER_TABLE HTF ON HTF.HELPER_TABLE_SID = WP.FREQUENCY\n"
                + "LEFT JOIN SLA_CALENDAR_MASTER SCM ON SCM.SLA_CALENDAR_MASTER_SID = WP.SLA_CALENDAR_MASTER_SID";
        return query;
    }

    public static String manualQuery() {
        String query;
        query = "SELECT WP.PROCESS_NAME,WP.MODIFIED_DATE FROM WORKFLOW_PROFILE WP";
        return query;
    }

    public static String isNull(final String value) {
        if (value != null && !"null".equals(value) && !StringUtils.EMPTY.equals(value)&& !Constants.IndicatorConstants.SELECT_ONE.getConstant().equals(value)) {
            return value;
        }
        return "%";
    }
}
