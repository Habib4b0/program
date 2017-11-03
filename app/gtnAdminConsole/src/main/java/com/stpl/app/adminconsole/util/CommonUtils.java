/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.util;

import com.stpl.app.adminconsole.abstractsearch.util.ValidationUtil;
import com.stpl.app.adminconsole.dao.CommonDAO;
import com.stpl.app.adminconsole.dao.FileManagementLogicDAO;
import com.stpl.app.adminconsole.dao.impl.CommonDAOImpl;
import com.stpl.app.adminconsole.dao.impl.FileManagementLogicDAOImpl;
import com.stpl.app.model.CompanyMaster;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.HierarchyDefinition;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionList;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.Role;
import com.stpl.portal.model.User;
import com.stpl.portal.service.RoleLocalServiceUtil;
import com.stpl.portal.service.UserLocalServiceUtil;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyContainer;

/**
 *
 * @author Nandhakumar
 */
public class CommonUtils {

    public static final int ZERO = 0;

    /**
     * The Constant CHAR_PERCENT.
     */
    public static final char CHAR_PERCENT = '%';

    /**
     * The Constant CHAR_ASTERISK.
     */
    public static final char CHAR_ASTERISK = '*';

    final private static FileManagementLogicDAO DAO = new FileManagementLogicDAOImpl();
    
    static CommonDAO commonDao = new CommonDAOImpl();

    private static CommonUtils object;
    /**
     * The WorkFlowStatus list name.
     */
    public final static String WORKFLOW_STATUS = "WorkFlowStatus";
    private static HashMap<Long, String> userMap = new HashMap<Long, String>();
    
    public final static String SUCCESSFULLY_DELETED = " has been deleted Successfully";
    public final static String PRIMARY = "Primary";
    public final static String FORECAST = "Forecast";
    public final static String ARCHIVE_ERROR = "Archive Error";
    public final static String USER_DEFINED = "User Defined";
    public final static String SEARCH_ERROR = "Search Error";
    public final static String PLEASE_CLICK_RECORD = "Please click on a record within the results list view";
    public final static String LEVELVALUE_LISTENER = "In configureFields levelValueReference.addValueChangeListener started";
    public final static String DETAILS_ERROR = "Details Error";
    public final static String LEVELVALUE_BLURLISTENER = "In configureFields levelValueReference.addBlurListener started";
    public final static String ONLY_NUMBERSAREALLOWED = "Only Numbers are allowed";
    public final static String REMOVE_ERROR = "Remove Error";
    public final static String HISTORY = "History";
    public final static String ENTERED_FUTUREPERIOD = "The entered Future period is beyond the GTS file. All periods beyond the GTS file will display zeros in the Forecast";
    public final static String MODIFIED_DATE = "modifiedDate";
    public final static String CREATED_DATE = "createdDate";
    public static final String VERSION = "version";
    public static final String FROM_PERIOD = "fromPeriod";
    public static final String FORECAST_NAME = "forecastName";
    public static final String TO_PERIOD = "toPeriod";
    public static final String COUNTRY = "country";
    /**
     * The Constant MMDDYYYY.
     */
    public static final String MMDDYYYY = "MM/dd/yyyy";
    private static final Logger LOGGER = Logger.getLogger(CommonUtils.class);

    /**
     * The helper list util.
     */
    HelperListUtil helperListUtil = HelperListUtil.getInstance();

    public static CommonUtils getInstance() {
        if (object == null) {
            object = new CommonUtils();
        }
        return object;
    }

    public ComboBox loadComboBox(final ComboBox select, String listName) throws SystemException, PortalException {

        final HelperDTO defaultValue = new HelperDTO(0, ConstantsUtils.SELECT_ONE);
        select.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        select.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
        BeanItemContainer<HelperDTO> resultContainer = new BeanItemContainer<>(HelperDTO.class);
        List<HelperDTO> helperList = getHelperResults(listName);
        resultContainer.addAll(helperList);
        select.setContainerDataSource(resultContainer);
        select.select(defaultValue);
        select.markAsDirty();

        select.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue()) || ConstantsUtils.NULL.equals(event.getProperty().getValue()))) {
                    select.select(defaultValue);
                }
            }
        });
        return select;
    }

    public ComboBox loadLazyComboBox(final ComboBox select, LazyContainer lazyContainer) {

        final HelperDTO defaultValue = new HelperDTO(ConstantsUtils.SELECT_ONE);
        select.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        select.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
        lazyContainer.setMinFilterLength(ZERO);
        select.setContainerDataSource(lazyContainer);
        select.select(defaultValue);
        select.markAsDirty();

        select.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue()) || ConstantsUtils.NULL.equals(event.getProperty().getValue()))) {
                    select.select(defaultValue);
                }
            }
        });
        return select;
    }

    public List<HelperDTO> getHelperResults(final String listType) throws SystemException, PortalException {

        final List<HelperDTO> helperList = new ArrayList<>();
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil
                .forClass(HelperTable.class);
        cfpDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.LIST_NAME,
                listType));
        cfpDynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.DESCRIPTION));
        final List<HelperTable> list = DAO.getHelperTableList(cfpDynamicQuery);
        helperList.add(new HelperDTO(0, ConstantsUtils.SELECT_ONE));
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable
                        .getDescription()));

            }
        }
        return helperList;
    }

    /**
     * Load company values
     *
     * @param select
     * @param tempFilterText
     * @return
     * @throws SystemException
     */
    public static ComboBox getCompany(final ComboBox select) throws SystemException {

        final List<HelperDTO> helperList = new ArrayList<>();
        try {
            List<CompanyMaster> resultList;
            final DynamicQuery companyDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
            final DynamicQuery helperDynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
            helperDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.DESCRIPTION, "GLCOMP"));
            helperDynamicQuery.add(RestrictionsFactoryUtil.eq("listName", "COMPANY_TYPE"));
            List<HelperTable> helperTableList = HelperTableLocalServiceUtil.dynamicQuery(helperDynamicQuery);
            if (helperTableList != null) {
                int id = helperTableList.get(0).getHelperTableSid();
                companyDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COMPANY_TYPE, id));
            }
            resultList = DAO.getCompanyMasterList(companyDynamicQuery);

            if (resultList != null) {
                for (int i = 0; i < resultList.size(); i++) {
                    final CompanyMaster company = (CompanyMaster) resultList.get(i);
                    helperList.add(new HelperDTO(company.getCompanyMasterSid(), company.getCompanyName()));
                }
            }
            select.setPageLength(NumericConstants.SEVEN);
            for (final Iterator<HelperDTO> iterator = helperList.iterator(); iterator.hasNext();) {
                final HelperDTO helperDTO = iterator.next();
                select.addItem(helperDTO);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return select;
    }

    /**
     * To get the company native select.
     *
     * @param select the select
     * @param helperList the helper list
     * @return the native select
     */
    public ComboBox getNativeSelect(final ComboBox select, final List<HelperDTO> helperList) {
        select.addItem(0);
        select.setItemCaption(0, ConstantsUtils.SELECT_ONE);
        for (int i = 0; i < helperList.size(); i++) {
            final HelperDTO helperDTO = helperList.get(i);
            select.addItem(helperDTO.getId());
            select.setItemCaption(helperDTO.getId(), helperDTO.getDescription());
        }
        return select;
    }

    public Date getCurrentGTSToDate(String fileType) throws SystemException {

        int year;
        int month;
        int day;
        Date date = new Date();
        List<Object[]> fileNameList = getFileName(fileType);
        if (!fileNameList.isEmpty()) {
            Object[] fileNameObject = fileNameList.get(0);
            String forecastName = fileNameObject[0] != null ? fileNameObject[0].toString() : "";
            String version = fileNameObject[1] != null ? fileNameObject[1].toString() : "";
            String finalVersion;
            String etlVersion;
            String selectedVersion = version;
            if (selectedVersion.contains(".")) {
                String[] array = selectedVersion.split("\\.");
                etlVersion = array[0];
                finalVersion = etlVersion + "~" + selectedVersion;
            } else {
                finalVersion = selectedVersion;
            }
            List<Object[]> list = getForecastYear(finalVersion, forecastName, fileType);
            Object[] object = list.get(0);
            year = object[0] != null ? Integer.valueOf(object[0].toString()) : 0;
            month = object[1] != null ? Integer.valueOf(object[1].toString()) : 0;
            Calendar cal = Calendar.getInstance();

            date.setMonth(month - 1);
            date.setYear(year - NumericConstants.ONE_NINE_ZERO_ZERO);
            cal.setTime(date);
            day = cal.getActualMaximum(Calendar.DATE);
            date.setDate(day);
        }
        return date;
    }

    private List<Object[]> getFileName(String fileType) throws SystemException {
        String sqlString = "";
        String query1 = "";
        query1 = "select HELPER_TABLE_SID FROM HELPER_TABLE Where DESCRIPTION like '" + fileType + "' AND LIST_NAME like 'FILE_TYPE'";
        List<Object[]> helperList = (List<Object[]>) HelperTableLocalServiceUtil.executeSelectQuery(query1);
        Object obj = helperList.get(0);
        sqlString = sqlString.concat("select top 1 forecast_name,version FROM dbo.FILE_MANAGEMENT WHERE FILE_TYPE like '" + String.valueOf(obj) + "' order by CREATED_DATE desc");
        List<Object[]> list = (List<Object[]>) DAO.executeSelectQuery(sqlString, null, null);
        return list;
    }

    private List<Object[]> getForecastYear(final String version, final String forecastName, final String fileType) throws SystemException {
        String sqlString = "";
        if (fileType.equals(ConstantsUtils.EX_FACTORY_SALES)) {
            sqlString = sqlString.concat("SELECT DISTINCT FM.FORECAST_YEAR,FM.FORECAST_MONTH FROM FORECASTING_MASTER FM WHERE ");
        } else if (fileType.equals(ConstantsUtils.DEMAND)) {
            sqlString = sqlString.concat("SELECT DISTINCT FM.FORECAST_YEAR,FM.FORECAST_MONTH FROM DEMAND_FORECAST FM WHERE ");
        } else if (fileType.equals(ConstantsUtils.ADJUSTED_DEMAND)) {
            sqlString = sqlString.concat("SELECT DISTINCT FM.YEAR,FM.MONTH FROM ADJUSTED_DEMAND_FORECAST FM WHERE ");
        } else if (fileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY)) {
            sqlString = sqlString.concat("SELECT DISTINCT FM.YEAR,FM.MONTH FROM INVENTORY_WD_PROJ_MAS FM WHERE ");
        } else if (fileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL)) {
            sqlString = sqlString.concat("SELECT DISTINCT FM.YEAR,FM.MONTH FROM INVENTORY_WD_PROJ_DT FM WHERE ");
        } else if (fileType.equals(ConstantsUtils.CUSTOMERGTS)) {
            sqlString = sqlString.concat("SELECT DISTINCT FM.FORECAST_YEAR,FM.FORECAST_MONTH FROM CUSTOMER_GTS_FORECAST FM WHERE ");
        }
        if (version.contains("~")) {
            String[] versionArray = version.split("~");
            sqlString = sqlString.concat(" (FM.FORECAST_VER='").concat(versionArray[0]).concat("' or FM.FORECAST_VER='").concat(versionArray[1]).concat("')");
        } else {
            sqlString = sqlString.concat(" FM.FORECAST_VER='").concat(version).concat("'");
        }
        sqlString = sqlString.concat("AND FM.FORECAST_NAME='").concat(forecastName).concat("'");
        if (fileType.equals(ConstantsUtils.EX_FACTORY_SALES) || fileType.equals(ConstantsUtils.DEMAND)) {
            sqlString = sqlString.concat(" ORDER BY FM.FORECAST_YEAR DESC,FM.FORECAST_MONTH DESC");
        } else if (fileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY) || fileType.equals(ConstantsUtils.ADJUSTED_DEMAND)) {
            sqlString = sqlString.concat(" ORDER BY FM.YEAR DESC,FM.MONTH DESC");
        }
        List<Object[]> list = (List<Object[]>) DAO.executeSelectQuery(sqlString, null, null);
        return list;
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
        if (frequency.equals(ConstantsUtils.QUARTER)) {
            frequencyDivision = NumericConstants.FOUR;
            endPeriod = getPeriodFromDate(fromDate, NumericConstants.THREE);
        } else if (frequency.equals(ConstantsUtils.SEMI_ANNUAL)) {
            frequencyDivision = NumericConstants.TWO;
            endPeriod = getPeriodFromDate(fromDate, NumericConstants.SIX);
        } else if (frequency.equals(ConstantsUtils.MONTHLY)) {
            frequencyDivision = NumericConstants.TWELVE;
            endPeriod = cal.get(Calendar.MONTH) + 1;
        } else if (frequency.equals(ConstantsUtils.ANNUAL)) {
            frequencyDivision = 1;
            endPeriod = cal.get(Calendar.YEAR);
        }
        List<Integer> list = new ArrayList<>();
        list.add(frequencyDivision);
        list.add(endPeriod);
        list.add(cal.get(Calendar.YEAR));
        return list;
    }

    /**
     * To get Start year, Start period and Start month
     *
     * @param projSelDTO
     * @return
     */
    public static String getHistoryDetail(int historyNum, String frequency, Date fromDate) {
        List<Integer> list = getCurrentDetails(frequency, fromDate);
        int frequencyDivision = list.get(0);
        int endPeriod = list.get(1);
        int endYear = list.get(NumericConstants.TWO);
        int startFreq = endPeriod + 1;
        int startYear = endYear;
        if (frequencyDivision == 1) {
            startYear = startYear - historyNum;
            startFreq = startYear;
        } else {
            int tempFreq = historyNum - endPeriod + 1;
            if (tempFreq > 0) {
                startYear = startYear - (tempFreq / frequencyDivision);
                startFreq = 1;
                if (tempFreq % frequencyDivision > 0) {
                    startYear = startYear - 1;
                    startFreq = frequencyDivision - (tempFreq % frequencyDivision) + 1;
                }
            } else {
                startFreq = startFreq - historyNum - 1;
            }
        }
        String ret = "";
        if (frequencyDivision == 1) {
            ret = "" + startYear;
        } else if (frequencyDivision == NumericConstants.FOUR) {
            ret = "Q" + startFreq + " " + startYear;
        } else if (frequencyDivision == NumericConstants.TWO) {
            ret = "S" + startFreq + " " + startYear;
        } else if (frequencyDivision == NumericConstants.TWELVE) {
            if (startFreq >= 0 && startFreq <= NumericConstants.ELEVEN) {
                ret = getMonthForInt(startFreq) + " " + startYear;
            } else {
                ret = getMonthForInt(startFreq) + " " + (startYear + 1);
            }
        }
        return ret;
    }

    public static String getMonthForInt(int num) {
        String month;
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getShortMonths();
        if (num >= 0 && num <= NumericConstants.ELEVEN) {
            month = months[num];
        } else {
            month = months[0];
        }
        return month;
    }

    public boolean checkETLUser(int userId) {

        try {
            return checkETL(String.valueOf(userId));
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
        return false;

    }

    public boolean checkETL(String userId) throws SystemException {
        List<Role> userRoles = RoleLocalServiceUtil.getUserRoles(Long.valueOf(userId));
        List roleList = new ArrayList();
        for (Role role : userRoles) {
            roleList.add(role.getRoleId());
        }
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(Role.class);
        query.add(RestrictionsFactoryUtil.eq("name", "ETL"));
        query.add(RestrictionsFactoryUtil.in("roleId", roleList));
        List<Role> userList = RoleLocalServiceUtil.dynamicQuery(query);
        if (!userList.isEmpty() && userList.size() > 0) {
            return true;
        }
        return false;
    }

    public void textValidation(Object obj, Object key) {
        try {
            if (obj != null && key != null && !"null".equals(key) && obj instanceof TextField) {
                TextField tempObj = (TextField) obj;
                String[] rules = String.valueOf(key).split(",");
                if (rules[0] != null && rules[0] != "null" && ValidationUtil.getMessage(rules[0]) != null && StringUtils.isNotEmpty(ValidationUtil.getMessage(rules[0]))) {
                    String[] temp = ValidationUtil.getMessage(rules[0]).split(",");
                    tempObj.addValidator(new StringLengthValidator(ValidationUtil.getMessage(rules[1]), Integer.valueOf(temp[0]), Integer.valueOf(temp[1]), Boolean.valueOf(temp[NumericConstants.TWO])));
                }
                if (rules[NumericConstants.TWO] != null && !"null".equals(rules[NumericConstants.TWO]) && ValidationUtil.getMessage(rules[NumericConstants.TWO]) != null && StringUtils.isNotEmpty(ValidationUtil.getMessage(rules[NumericConstants.TWO]))) {
                    tempObj.addValidator(new RegexpValidator(ValidationUtil.getMessage(rules[NumericConstants.TWO]), ValidationUtil.getMessage(rules[NumericConstants.THREE])));
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public List getFilterValueFromHelper(String filterString) {
        List resultList = new ArrayList();
        try {
            final DynamicQuery helperDynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
            helperDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.DESCRIPTION, filterString));
            helperDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.LIST_NAME, "RELATIONSHIP_TYPE"));
            helperDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property("helperTableSid")));
            resultList = DAO.getHelperTableList(helperDynamicQuery);

        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
        return resultList;
    }

    /**
     * To get the combo box select.
     *
     * @param select the select
     * @param listName the list name
     * @param isFilter the is filter
     * @return the native select
     * @throws Exception the exception
     */
    public ComboBox loadComboBox(final ComboBox select,
            String listName, boolean isFilter) {
        select.removeAllItems();
        final HelperDTO defaultValue = new HelperDTO(0, isFilter ? ConstantsUtils.SHOW_ALL : ConstantsUtils.SELECT_ONE);
        select.setValidationVisible(true);
        select.setImmediate(true);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(defaultValue);
        select.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
        select.setData(listName);
        List<HelperDTO> helperList = new ArrayList<>();
        helperList.add(defaultValue);
        BeanItemContainer<HelperDTO> resultContainer = new BeanItemContainer<>(HelperDTO.class);
        if (helperListUtil.getListNameMap().get(listName) != null) {
            helperList.addAll(helperListUtil.getListNameMap().get(listName));
        }
        resultContainer.addAll(helperList);
        select.setContainerDataSource(resultContainer);
        select.select(defaultValue);
        select.markAsDirty();
        select.setDescription((String) (select.getValue() == null ? ConstantsUtils.SELECT_ONE : select.getValue()));
        select.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue()) || ConstantsUtils.NULL.equals(event.getProperty().getValue()))) {
                    select.select(defaultValue);
                }
                select.setDescription((String) (select.getValue() == null ? ConstantsUtils.SELECT_ONE : ((HelperDTO) select.getValue()).getDescription()));
            }
        });
        return select;
    }

    public ComboBox loadHistoricalAndFutureComboBox(final ComboBox select,
            String listName, boolean isFilter) {
        select.removeAllItems();
        final HelperDTO defaultValue = new HelperDTO(0, isFilter ? ConstantsUtils.SHOW_ALL : ConstantsUtils.SELECT_ONE);
        select.setValidationVisible(true);
        select.setImmediate(true);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(defaultValue);
        select.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
        select.setData(listName);
        List<HelperDTO> helperList = new ArrayList<>();
        helperList.add(defaultValue);
        BeanItemContainer<HelperDTO> resultContainer = new BeanItemContainer<>(HelperDTO.class);
        List<Object[]> resultList = QueryReader.getAppData(Collections.EMPTY_LIST, "loadComboBox_Where_Desc_Not_Ends_with_LY", null);
        if (resultList != null && !resultList.isEmpty()) {
            for (Object[] objects : resultList) {
                HelperDTO helperDto = new HelperDTO();
                helperDto.setId(objects[0] != null ? (int) objects[0] : 0);
                helperDto.setDescription(objects[1] != null ? (String) objects[1] : StringUtils.EMPTY);
                helperList.add(helperDto);
            }
        }
        resultContainer.addAll(helperList);
        select.setContainerDataSource(resultContainer);
        select.select(defaultValue);
        select.markAsDirty();
        select.setDescription((String) (select.getValue() == null ? ConstantsUtils.SELECT_ONE : select.getValue()));
        select.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue()) || ConstantsUtils.NULL.equals(event.getProperty().getValue()))) {
                    select.select(defaultValue);
                }
                select.setDescription((String) (select.getValue() == null ? ConstantsUtils.SELECT_ONE : ((HelperDTO) select.getValue()).getDescription()));
            }
        });
        return select;
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

    public static boolean isEmailValid(String email) {

        boolean isValid = false;
        String expression = ValidationUtils.MAIL_PATTERN;
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    /**
     * Criteria check
     *
     * @param value
     * @return
     */
    public static boolean isValidCriteria(String value) {
        boolean isValid = false;
        if (value != null && !"null".equals(String.valueOf(value)) && !StringUtils.EMPTY.equals(String.valueOf(value))
                && !StringUtils.isEmpty(String.valueOf(value)) && !StringUtils.isBlank(String.valueOf(value))) {
            isValid = true;
        } else {
            isValid = false;
        }
        return isValid;
    }

    /**
     * To get the company native select.
     *
     * @param select the select
     * @param helperList the helper list
     * @param selectedValue
     * @return the native select
     * @throws java.lang.Exception
     */
    public ComboBox getNativeSelect(final ComboBox select, final List<HelperDTO> helperList, final String selectedValue) {
        for (int i = 0; i < helperList.size(); i++) {
            final HelperDTO helperDTO = helperList.get(i);
            select.addItem(helperDTO);
            if (!StringUtils.EMPTY.equals(selectedValue) && selectedValue.equals(helperDTO.getDescription())) {
                select.select(helperDTO);
            }
        }

        return select;
    }

    public static void successNotification(final String message) {
        try {
            final Notification notif = new Notification(message,
                    Notification.Type.HUMANIZED_MESSAGE);
            notif.setPosition(Position.MIDDLE_CENTER);
            notif.setStyleName("mystyle");
            notif.setDelayMsec(NumericConstants.THOUSAND);
            notif.show(Page.getCurrent());
        } catch (Exception e) {

            LOGGER.error(e);
        }
    }
    public static HashMap<Long, String> setUserInfo() {

        List<User> users = new ArrayList<>();
        DynamicQuery userGroupDynamicQuery = DynamicQueryFactoryUtil.forClass(User.class);
        try {
            users = UserLocalServiceUtil.dynamicQuery(userGroupDynamicQuery);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }

        for (User user : users) {
            userMap.put(user.getUserId(), user.getLastName() + " " + user.getFirstName());
        }
        return userMap;
    }
    public static String getUserInfo(String userId) {
        try {
            Long.valueOf(userId);
        } catch (NumberFormatException nfe) {
            return StringUtils.EMPTY;
        }
        return getUserInfo(Long.valueOf(userId));
    }
    public static String getUserInfo(Long userId) {
        return userMap.get(userId) != null ? userMap.get(userId) : StringUtils.EMPTY;
    }
    public static HashMap<Long, String> getUserMap() {
        return userMap;
    }
    /**
     * Gets the hierarchy info.
     *
     * @return the hierarchy info
     * @throws com.stpl.portal.kernel.exception.SystemException
     */
    public static Map getHierarchyInfo() throws SystemException {
        LOGGER.debug("Entering getHierarchyInfo ");
        List<Object[]> resultList;
        final HashMap results = new HashMap();
        final DynamicQuery companyDynamicQuery = DynamicQueryFactoryUtil.forClass(HierarchyDefinition.class);
        final ProjectionList projList = ProjectionFactoryUtil.projectionList();
        projList.add(ProjectionFactoryUtil.property("hierarchyDefinitionSid"));
        projList.add(ProjectionFactoryUtil.property("hierarchyName"));

        companyDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));
        resultList = commonDao.getHierachyDefinitionList(companyDynamicQuery);
        for (int i = 0; i < resultList.size(); i++) {
            final Object[] obj = resultList.get(i);
            results.put(String.valueOf(obj[0]), String.valueOf(obj[1]));
        }
        LOGGER.debug("getHierarchyInfo return HashMap results size:" + results.size());
        return results;
    }
}
