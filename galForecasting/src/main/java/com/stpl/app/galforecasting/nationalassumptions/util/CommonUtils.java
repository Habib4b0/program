package com.stpl.app.galforecasting.nationalassumptions.util;

import com.stpl.app.galforecasting.dao.DataSelectionDAO;
import com.stpl.app.galforecasting.dao.impl.DataSelectionDAOImpl;
import com.stpl.app.galforecasting.nationalassumptions.dto.SessionDTO;
import com.stpl.app.galforecasting.nationalassumptions.logic.DataSelectionLogic;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.CommonConstants.*;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.FrequencyConstants.*;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.*;
import com.stpl.app.galforecasting.utils.Constant;
import static com.stpl.app.galforecasting.utils.Constant.CalculatePeriods.CALCULATE;
import static com.stpl.app.galforecasting.utils.Constant.ZERO;
import com.stpl.app.model.CompanyMaster;
import com.stpl.app.model.ForecastConfig;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.ItemMaster;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.ItemMasterLocalServiceUtil;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionList;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.stpl.portal.service.UserLocalServiceUtil;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.inflater.filter.AttributeFilter;
import static com.stpl.app.galforecasting.utils.Constant.DASH;

/**
 * The Class CommonUtils.
 *
 * @author Vinodhini
 */
public class CommonUtils {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(CommonUtils.class);
    /**
     * The current year.
     */
    static int currentYear;

    /**
     * The current month.
     */
    static int currentMonth;

    /**
     * The current quarter.
     */
    static int currentQuarter;

    /**
     * The current semi.
     */
    static int currentSemi;

    /**
     * The current date.
     */
    private static int currentDate;
    
    public static final String GLCOMP = "GLCOMP";
    public static final char CHAR_PERCENT = '%';
    public static final char CHAR_ASTERISK = '*';
   public static final String PERCENT = "%";
    public static final String DOLLAR = "$";
    /** UserMap - Contains User System ID and User Name */
    public static Map<Integer,String> userMap=new ConcurrentHashMap<Integer,String>();  
    /** UserMap - Contains User Name and User System ID  */
    public static Map<String,Integer> userIdMap=new ConcurrentHashMap<String,Integer>(); 
    public static final DecimalFormat CUR_FOUR = new DecimalFormat("$0.0000");
    public static String GROWTH ="([0-9|\\.|\\,])*";
    
    public static String GROWTH_VAL_MSG ="Growth can contain only digits";
    
    public static String specialCharacter="([0-9|a-z|A-Z|\\.|\\%|\\_|\\s])*";
    
    public static String specialCharacter_Message="Value can contain only digits,alphabets";
       
     public static SessionDTO sessionDto=new SessionDTO();
     public static final String BUSINESS_PROCESS_TYPE = "BUSINESS_PROCESS_TYPE";
    
    /**
     * Creates the clara.
     *
     * @param xmlClassResourceFileName the xml class resource file name
     * @param controller the controller
     * @param attributeFilters the attribute filters
     * @return the component
     */
    public static Component createClara(String xmlClassResourceFileName, Object controller, AttributeFilter... attributeFilters) {
        LOGGER.info("createClara method started");
        InputStream xml = null;
        try {
            String path = controller.getClass().getCanonicalName();
            String finalPath = path.substring(0, path.lastIndexOf("."));
            finalPath = finalPath.replaceAll("\\.", "\\" + File.separator);
            finalPath += xmlClassResourceFileName;
            LOGGER.info("Path to XML:" + finalPath);
            xml = Thread.currentThread().getContextClassLoader().getResource(finalPath).openStream();
            LOGGER.info("createClara method ends");
            return Clara.create(xml, controller, attributeFilters);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
        } finally {
            try {
                xml.close();
            } catch (IOException ex) {

            }
        }
        return null;
    }

    /**
     * Default load.
     *
     * @param comboBox the combo box
     */
    public static void defaultLoad(ComboBox... comboBox) {
        for (ComboBox comp : comboBox) {
            comp.addItem(SELECT_ONE);
        }
    }

    public static Calendar getCalendar() {
        Date todayDate=new Date(); 
        Calendar now = Calendar.getInstance();
        now.setTime(todayDate);
        currentYear = now.get(Calendar.YEAR);
        currentMonth = now.get(Calendar.MONTH);
        currentDate = now.get(Calendar.DATE);        
        now.set(currentYear, currentMonth, currentDate);
        return now;

    }

    /**
     * Default select.
     *
     * @param comboBox the combo box
     */
    public static void defaultSelect(ComboBox... comboBox) {
        for (ComboBox comp : comboBox) {
            comp.select(SELECT_ONE.getConstant());
        }
    }
    
    public static ComboBox getBaselineStartPeriod(ComboBox select) throws Exception {
        SessionDTO startAndTodate = CommonUtils.sessionDto;
        Date startDate = startAndTodate.getFromDate();
        Date endDate = startAndTodate.getToDate();
        int startYear = startDate.getYear() + 1900;
        int endYear = endDate.getYear() + 1900;
        Calendar now = CommonUtils.getCalendar();
        int currentYr = now.get(Calendar.YEAR);
        int histYear = currentYr - 3;

        select.addItem(Constant.SELECT_ONE);
        if (histYear >= startYear) {
            int years = ((endYear - startYear) + 1);
            for (int i = 0; i < years; i++) {
                int year = (startYear) + i;
                for (int j = 1; j < 5; j++) {
                    select.addItem(Constant.Q + j + " " + year);
                }
            }
        } else {
            int years = ((endYear - histYear) + 1);
            for (int i = 0; i < years; i++) {
                int year = (histYear) + i;
                for (int j = 1; j < 5; j++) {
                    select.addItem(Constant.Q + j + " " + year);
                }
            }
        }
        return select;
    }
    public static ComboBox getRollingStartPeriod(ComboBox select) {
        Calendar now = CommonUtils.getCalendar();
        int currentYear = now.get(Calendar.YEAR);
        select.addItem(Constant.SELECT_ONE);
        for (int i = 0; i < 3; i++) {
            int year = (currentYear - 3) + i;
            for (int j = 1; j < 5; j++) {
                select.addItem(Constant.Q + j + " " + year);
            }
        }
       
        return select;
    }

      public static ComboBox getEffectivePeriods(ComboBox select) {
        SessionDTO startAndTodate = CommonUtils.sessionDto;
        Date startDate = startAndTodate.getFromDate();
        Date endDate = startAndTodate.getToDate();
        int startYear = startDate.getYear() + 1900;
        int endYear = endDate.getYear() + 1900;
        int lastPr=4;
        int endMonth=endDate.getMonth()+ 1;
        int endPeriod=getQuator(endMonth);
        int years = ((endYear - startYear) + 1);
        int startMonth=startDate.getMonth()+ 1;
        int startPeriod=getQuator(startMonth);
        
        select.addItem(Constant.SELECT_ONE);
        for (int i = 0; i < years; i++) {
            int year = (startYear) + i;
             if (year == endYear) {
                    lastPr = endPeriod;
                }
            for (int j = startPeriod; j <= lastPr; j++) {
                select.addItem(Constant.Q + j + " " + year);
            }
            startPeriod=1;
        }
        return select;
    }

    public static boolean isEndDateGreater(String startDate, String endDate) {
        int startYear = Integer.parseInt(startDate.substring(startDate.length() - 4));
        int endYear = Integer.parseInt(endDate.substring(endDate.length() - 4));
        boolean status = false;
        if (!(startYear > endYear)) {
            if (startYear == endYear) {

                status = (Integer.parseInt(String.valueOf(startDate.charAt(1))) < Integer.parseInt(String.valueOf(endDate.charAt(1)))) ? true : false;

            } else if (startYear < endYear) {
                status = true;
            }
        }

        return status;
    }

    /**
     * Gets the message notification.
     *
     * @param message the message
     * @return the message notification
     */
    public static void getMessageNotification(final String message) {
        final Notification notif = new Notification(message, Notification.Type.HUMANIZED_MESSAGE);
        notif.setPosition(Position.MIDDLE_CENTER);
        notif.setStyleName(Constant.MY_STYLE);
        notif.show(Page.getCurrent());
    }
    
    /**
     * Loads Manufacturer Drop down
     *
     * @return - List to load Manufacturer Drop down
     */
    @SuppressWarnings("unchecked")
    public static List<HelperDTO> getManufacturesWithIds() {
        LOGGER.info("getManufacturesWithIds() starts");
        List<HelperDTO> results = new ArrayList<HelperDTO>();
        try {
            DynamicQuery helper = DynamicQueryFactoryUtil.forClass(HelperTable.class);
            final ProjectionList helperProjectionList = ProjectionFactoryUtil.projectionList();
            helperProjectionList.add(ProjectionFactoryUtil.property(Constant.HELPER_TABLE_SID));
            helper.add(RestrictionsFactoryUtil.eq(Constant.LIST_NAME, "COMPANY_TYPE"));
            helper.add(RestrictionsFactoryUtil.like(Constant.DESCRIPTION, "Glcomp"));
            helper.setProjection(ProjectionFactoryUtil.distinct(helperProjectionList));
            List<Object[]> companyTypeIds = HelperTableLocalServiceUtil.dynamicQuery(helper);
            int companyTypeId = 0;
            if (!companyTypeIds.isEmpty()) {
                companyTypeId = Integer.valueOf(String.valueOf(companyTypeIds.get(0)));
            }
        DynamicQuery companyDynamicQuery = DynamicQueryFactoryUtil
                .forClass(CompanyMaster.class);

        companyDynamicQuery.add(RestrictionsFactoryUtil.eq("companyType",
               companyTypeId));
        ProjectionList projList = ProjectionFactoryUtil.projectionList();
        projList.add(ProjectionFactoryUtil.property(Constant.COMPANYMASTERSID));
        projList.add(ProjectionFactoryUtil.property(Constant.COMPANY_NAME));
        companyDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));
        List<Object[]> resultList = new ArrayList<Object[]>();
        
            resultList = CompanyMasterLocalServiceUtil.dynamicQuery(companyDynamicQuery);
       int i=0;
        for (Object[] obj : resultList) {
            if(i++==0){
                results.add(new HelperDTO(Constant.SELECTONE));
            }
            HelperDTO dto = new HelperDTO();
            dto.setId((Integer) obj[0]);
            dto.setDescription(String.valueOf(obj[1]));
            results.add(dto);
        }
         } catch (SystemException e) {
            LOGGER.error(e.getMessage());
        }
        return results;
    }
  /**
     * To check whether the given string is integer or not
     *
     * @param s
     * @return
     */
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
}
        return true;
    }

    public static int getIntegerForMonth(String month) {
        String[] array = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        return Arrays.asList(array).indexOf(month) + 1;
    }
    
     public static String[] objectListToStringArray(List<Object> objectList) {
        String[] stringArray = {};
        if (objectList != null) {
            stringArray = new String[objectList.size()];
            stringArray = objectList.toArray(stringArray);
        }
        return stringArray;
    }
     
   public static List<String> objectListToStringList(List<Object> objectList) {
        return Arrays.asList(objectListToStringArray(objectList));
    } 
   /**
     * Loads Therapeutic class Drop down
     *
     * @return - List to load Therapeutic Drop down
     */
    @SuppressWarnings("unchecked")
    public static List<HelperDTO> getTherapeuticClass() {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemMaster.class);
        dynamicQuery.add(RestrictionsFactoryUtil.isNotNull("therapeuticClass"));
        dynamicQuery.add(RestrictionsFactoryUtil.ne("therapeuticClass",0));
        dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property("therapeuticClass")));
        List<Integer> resultList = Collections.EMPTY_LIST;
        List<HelperDTO> finalList = new ArrayList<HelperDTO>();
        try {
            resultList = ItemMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
        } catch (SystemException e) {
            LOGGER.error(e.getMessage());
        }
       
        HelperDTO hDTO = null;
        if(resultList!=null){
        for (Integer resultList1 : resultList) {            
            hDTO = new HelperDTO(resultList1,getDescription(resultList1));
            finalList.add(hDTO);
        }
          }
        finalList.add(0, new HelperDTO(Constant.SELECTONE)); 
        return finalList;
    }
    public static String getDescription(int id) {
         HelperTable helperTable=HelperTableLocalServiceUtil.createHelperTable(0);
        try {
            if (id != 0) {
                 helperTable = HelperTableLocalServiceUtil.getHelperTable(id);
            }
            return id == 0 ? StringUtils.EMPTY : helperTable.getDescription();
        } catch (PortalException ex) {
            LOGGER.error(ex.getMessage());
            return StringUtils.EMPTY;
        } catch (SystemException ex) {
            LOGGER.error(ex.getMessage());
            return StringUtils.EMPTY;
        }

    }
    /**
     * To convert List<String> into a comma separated String
     *
     * @param collectionOfString
     * @param toAddQuote
     * @return
     */
    public static String CollectionToString(Collection<?> collectionOfString, boolean toAddQuote) {
        return CollectionToString(collectionOfString,toAddQuote, false);
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

        String framedString = StringUtils.EMPTY;
        if (collectionOfString != null && !collectionOfString.isEmpty()) {
            if (toAddQuote) {
                framedString += Arrays.toString(collectionOfString.toArray()).replace("[", "'").replace("]", "'").replace(", ", "','");
            } else {
                framedString += Arrays.toString(collectionOfString.toArray()).replace("[", StringUtils.EMPTY).replace("]", StringUtils.EMPTY);
            }
            
            if(toRemoveSpace){
                framedString.replace(", ", StringUtils.EMPTY);
            }
        }
        return framedString;
    }
    public static void getStartandTodate() {
        try {
//        List<Date> result = new ArrayList<Date>();
            ForecastConfig forecastConfig = DataSelectionLogic.getTimePeriod();
            Date fromDate = null;
            Date toDate = null;
            if (forecastConfig != null) {
                fromDate = forecastConfig.getFromDate();
                toDate = forecastConfig.getToDate();
            }

//        result.add(fromDate);
//        result.add(toDate);
            sessionDto.setFromDate(fromDate);
            sessionDto.setToDate(toDate);

//        return result;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }

    }
    public static int getProjections(Date startDate, Date endDate, String frequency) {

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
            return 0;

        }
    }

    public static int getQuator(int month) {
        if (month < 4) {
            return 1;
        }
        if (month < 7) {
            return 2;
        }
        if (month < 10) {
            return 3;
        }
        return 4;
    }
      /**
     * Retrieves all the user name and stores that in the Concurrent Hash Map.
     * 
     * @return the Map
     * @throws com.stpl.portal.kernel.exception.SystemException
     */      
    public static Map<Integer, String> getUserName() throws SystemException {
        LOGGER.info("Enters getUserName method");
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(User.class);
        List<User> userList = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
        for (User user : userList) {
            String formattedUN= user.getLastName()+", "+user.getFirstName();
            userMap.put(Long.valueOf(user.getUserId()).intValue(),formattedUN);
            userIdMap.put(formattedUN,Long.valueOf(user.getUserId()).intValue());
        }
        LOGGER.info("End of getUserName method");
        return userMap;
    } 
       public static String filterUser(String filter) {
        List<String> keys = new ArrayList<String>();
        String userIds = StringUtils.EMPTY;
        
        if (userMap != null) {
            for (Map.Entry<Integer, String> entry : userMap.entrySet()) {
                if ((String.valueOf(entry.getValue()).toLowerCase().trim()).contains(filter.toLowerCase().trim())) {
                    keys.add(String.valueOf(entry.getKey()));
                }
            }
        }
        if (!keys.isEmpty()) {
            userIds = stringListToString(keys);
        } else {
            userIds = DASH;
        }
        return userIds;
    }
             public static String stringListToString(List<String> stringList) {
        StringBuilder builder = new StringBuilder(StringUtils.EMPTY);
        if (stringList != null && !stringList.isEmpty()) {
            for (int loop = 0, limit = stringList.size(); loop < limit; loop++) {
                builder.append("'");
                builder.append(stringList.get(loop));
                builder.append("'");
                if (loop != (limit - 1)) {
                    builder.append(", ");
                }
            }
        }
        return builder.toString();
    }

    /**
     * Get User Name By User Id
     * @param userIdString
     * @return
     */
    public static String getUserNameById(String userIdString){
        String userName=StringUtils.EMPTY;
        int userId=Integer.parseInt(StringUtils.isBlank(userIdString)?DASH:userIdString);
            if(userId!=0){   
            userName = userMap.get(userId);     
            }
        return userName;
    }
     public static String getFormattedValue(DecimalFormat FORMAT, String value) {
        if (value.contains(Constant.NULL) || StringUtils.isBlank(value)) {
            value = StringUtils.EMPTY;
        } else if (value.contains("- -")){
             value = "- -";
        }else {
            Double newValue = Double.valueOf(value);
            if (FORMAT.toPattern().contains(Constant.PERCENT)) {
                newValue = newValue / 100;
            }
            value = FORMAT.format(newValue);
        }
        return value;
    }

  /**
     * Load history.
     *
     * @param frequency the frequency
     * @return the list
     */
    public static final List<String> loadHistory(String frequency) {
        List<String> history = new ArrayList();
        int currentPeriod=getQuator(currentMonth+1)-1;
        int endValue = 0;
        String freq = StringUtils.EMPTY;
        if (ANNUAL.getConstant().equals(frequency)) {
            endValue = CALCULATE.getAnnualCount();
            freq = YEARS.getConstant();
        } else if (QUARTERLY.getConstant().equals(frequency)) {
            endValue = ((4 * Constant.CalendarConstants.HISTORY_YEAR_COUNT.getConstant()) + (Constant.CalendarConstants.CURRENT_MONTH.getConstant() / 3));
            freq = QUARTERS.getConstant();
        } 

        for (int i = 1; i <= endValue; i++) {
            if ((i == 1)) {
                String period = freq.replace(Constant.S_SMALL, StringUtils.EMPTY);
                history.add(String.valueOf(i) + SPACE.getConstant() + period);
            } else {
                history.add(String.valueOf(i) + SPACE.getConstant() + freq);
            }
        }
        return history;
    }
    public static double getDoubleValue(String value) {
        double doubleValue = 0;
        boolean doubleFlag = false;

        value = value.replace("$", StringUtils.EMPTY);
        if (StringUtils.isNotBlank(value)) {
            if (value.matches("^\\d{0,5}\\.?\\d{0,4}$")) {
                doubleFlag = true;

            }
        }

        if (doubleFlag) {
            doubleValue = Double.valueOf(value);

        } else {
            doubleValue = 0;
        }

        return doubleValue;
    }
     /**
     * Get User Name By User Id
     * @param userNameString
     * @return
     */
    public static int getUserIdByName(String userNameString){
        int useridFromName=0;     
            if(StringUtils.isNotBlank(userNameString)){   
            useridFromName = userIdMap.get(userNameString);     
            }
        return useridFromName;
    }
    
    public static int getHelperCode(String listName, String description) throws PortalException, SystemException {
        final DataSelectionDAO DAO = new DataSelectionDAOImpl();
        int code = 0;
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
        dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.LIST_NAME, listName));
        dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.DESCRIPTION, description));
        dynamicQuery.setProjection(ProjectionFactoryUtil.property(ConstantsUtils.HELPER_TABLE_SID));
        List result = DAO.getHelperTableList(dynamicQuery);
        if (result != null && !result.isEmpty()) {
            code = Integer.valueOf(result.get(ZERO).toString());
        }
        return code;
    }
}
