/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.util;

import com.stpl.app.gcm.itemmanagement.itemabstract.queryutils.ItemQueries;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.util.Constants.IndicatorConstants;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.portlet.PortletConfig;
import org.apache.commons.lang.StringUtils;
import org.vaadin.alump.beforeunload.BeforeUnload;

/**
 *
 * @author alok.v
 */
public class CommonUtils {

    /**
     * The log.
     */
	private static PortletConfig portletConfig;
    public final static String COMPANY_NAME = "companyName";
    public final static String QUOTES = "'";
    public static final SimpleDateFormat DBDate = new SimpleDateFormat(Constants.DBDATE_FORMAT);
    public static final SimpleDateFormat commonDate = new SimpleDateFormat("MM-dd-yyy");
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
    public static final String MMDDYYYY = "MM/dd/yyyy";
    /**
     * The Constant CHAR_ASTERISK.
     */
    public static final char CHAR_ASTERISK = '*';
    /**
     * The Constant CHAR_PERCENT.
     */
    public static final char CHAR_PERCENT = '%';
    public static final String THERAPEUTIC_CLASS_PROPERTY = "therapeuticClass";

    /**
     *
     * @param portletConfig
     */
    public static void setPortalConfig(final PortletConfig portletConfig) {
        CommonUtils.setPortletConfig(portletConfig);
    }

    public static List<Integer> convertStringListToInteger(List<String> stringList) {
        List<Integer> integerList = new ArrayList<>();

        for (String sid : stringList) {
            integerList.add(Integer.parseInt(sid));
        }

        return integerList;
    }

    /**
     * To convert List<String> into a comma separated String
     *
     * @param collectionOfString
     * @param toAddQuote
     * @return
     */
    public static String CollectionToString(Collection<?> collectionOfString, boolean toAddQuote) {
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
    public static String CollectionToString(Collection<?> collectionOfString, boolean toAddQuote, boolean toRemoveSpace) {

        String framedString = "";
        if (collectionOfString != null && !collectionOfString.isEmpty()) {
            if (toAddQuote) {
                framedString += Arrays.toString(collectionOfString.toArray()).replace("[", "'").replace("]", "'").replace(", ", "','");
            } else {
                framedString += Arrays.toString(collectionOfString.toArray()).replace("[", "").replace("]", "");
            }

            if (toRemoveSpace) {
                framedString.replace(", ", "");
            }
        }
        return framedString;
    }

    /**
     * To check whether the given string is integer or not
     *
     * @param s
     * @return
     */
    public static int convertToInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            try {
                Double.valueOf(s).intValue();
            } catch (Exception ex) {
                return 0;
            }
            return Double.valueOf(s).intValue();
        }
        return Integer.parseInt(s);
    }

    /**
     * To check whether the given string is double or not
     *
     * @param str
     * @return
     */
    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static SessionDTO attachSessionId(SessionDTO session) {
        session.setSessionId(createSessionId());
        session.setUserId(String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID)));
        return session;
    }

    public static String getListToString(Collection hirarechyNos) {
        StringBuilder result = new StringBuilder();
        if (hirarechyNos != null && !hirarechyNos.isEmpty()) {
            for (Object hirarechyNo : hirarechyNos) {
                result.append("'" + hirarechyNo + "' ,");
            }
            result.deleteCharAt(result.length() - 1);
            return result.toString();
        }
        return "''";
    }

    public static String getPureValue(final String inputString) {
        return StringUtils.isBlank(inputString) || Constants.NULL.equals(inputString) || Constants.SELECT_ONE.equals(inputString) ? "" : inputString;
    }

    public static String createSessionId() {
        final Date tempDate = new Date();
        final SimpleDateFormat fmtID = new SimpleDateFormat("hhmmssms");
        return fmtID.format(tempDate);
    }

    public static String getCurrentTimestamp() {
        DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return DATE_FORMAT.format(new Date());
    }

    public static Object getDBinput(String identifier) {
        return identifier.replace(Constants.PERCENT, "[%]").replace(IndicatorConstants.CHAR_ASTERISK.getConstant(), "%").replace("_", "[_]");
    }

    public static Object getDBinputForFilter(String identifier) {
        return identifier.replace("_", "[_]");
    }

    /**
     * Converts the date in string from the given input format to the given
     * output format.
     *
     * @param stringDate
     * @param inputFormat
     * @param outputFormat
     * @return - formatted Date in String object
     * @throws ParseException
     */
    public String convertStringToDate(String stringDate, String inputFormat, String outputFormat) throws ParseException {
        SimpleDateFormat inputDateFormatter = new SimpleDateFormat(inputFormat);
        SimpleDateFormat outputDateFormatter = new SimpleDateFormat(outputFormat);
        Date date = inputDateFormatter.parse(stringDate);
        return outputDateFormatter.format(date);
    }

    public static int getDataCount(String QueryName, final List<String> input) {
        List<Object[]> list = ItemQueries.getItemData(input, QueryName, null);
        if (!list.isEmpty()) {
            Object obj = list.get(0);
            int count = obj == null ? 0 : (Integer) obj;
            return count;
        }
        return 0;
    }

    public static int getDdlbCountThroughList(final List<Object[]> list) {
        if (!list.isEmpty()) {
            Object obj = list.get(0);
            int count = obj == null ? 0 : (Integer) obj;
            return count;
        }
        return 0;
    }

    /**
     * Convert the date to "MM/dd/yyyy" format.
     *
     * @param aDate - The Date object to be converted to string
     * @return String - date in string format in the format of "MM/dd/yyyy"
     */
    public static final String convertDateToString(final Date aDate) {

        return getDateTime(MMDDYYYY, aDate);
    }

    /**
     * Get the date in string on given format.
     *
     * @param aMask - Date format input
     * @param aDate - The Date object to be converted to string
     * @return String - date in string format
     */
    public static final String getDateTime(final String aMask, final Date aDate) {
        if (aDate == null) {
        } else {
            final SimpleDateFormat dateFormat = new SimpleDateFormat(aMask);
            final String returnValue = dateFormat.format(aDate);
            return returnValue;
        }
        return StringUtils.EMPTY;
    }

    public static final void beforeUnloadCloseUi(final UI uI) {
        BeforeUnload ob = BeforeUnload.closeBeforeUnload(uI);
        ob.addUnloadListener(new BeforeUnload.UnloadListener() {
            @Override
            public void unload(BeforeUnload.UnloadEvent event) {
               if (!Constants.TRUE.equals(VaadinSession.getCurrent().getAttribute(ConstantsUtils.EXCEL_CLOSE))) {
                    uI.close();
                } 
            }
        });

    }

    public static PortletConfig getPortletConfig() {
            return portletConfig;
    }

    public static void setPortletConfig(PortletConfig portletConfig) {
            CommonUtils.portletConfig = portletConfig;
    }
}
