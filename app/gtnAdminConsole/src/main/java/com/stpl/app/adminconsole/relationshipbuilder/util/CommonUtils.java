/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.relationshipbuilder.util;

import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.dao.HierarchyBuilderLogicDAO;
import com.stpl.app.adminconsole.dao.impl.HierarchyBuilderLogicDAOImpl;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jboss.logging.Logger;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.HierarchyDefinition;
import com.stpl.app.model.HistHierarchyDefinition;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.HistRelationshipBuilderLocalServiceUtil;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.Criterion;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionList;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.stpl.portal.service.UserLocalServiceUtil;
import com.vaadin.data.Property;
import com.vaadin.ui.ComboBox;
import java.text.ParseException;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;


/**
 * The Class CommonUtils.
 *
 * @author vishalakshi
 */
public final class CommonUtils {

   
    public static final char CHAR_PERCENT = '%';
   
    public static final char CHAR_ASTERISK = '*';
   
    public static final String MMDDYYYY = "MM/dd/yyyy";
    
    public static final String EMPTY = "";
   
    public static final String STRING_NULL = "null";
   
    private static HierarchyBuilderLogicDAO dao = new HierarchyBuilderLogicDAOImpl();
    
    private static final Logger LOGGER = Logger.getLogger(CommonUtils.class);
   
    private static final Date NULL_VALUE = null;
    
    public final static String COMPANY_MASTER = "COMPANY_MASTER";
    public final static String LEVEL_NO = "levelNo";
    public final static String BRAND_MASTER = "BRAND_MASTER";
    public final static String CONTRACT = "Contract";
    public final static String PRODUCT = "Product";
    public final static String CUSTOMER = "Customer";
    public final static String GL_COMPANY = "GL Company";
    public final static String TRADING_PARTNER = "Trading Partner";
    public final static String CONTAINS = "contains";
    public final static String CONTRACT_HOLDER = "Contract Holder";
    public final static String GROUP = "Group";
    public final static String EXCLUSION = "Exclusion";
    private CommonUtils() {

    }

    /**
     * Gets the hierarchy.
     *
     * @return the hierarchy
     * @throws com.stpl.portal.kernel.exception.SystemException
     * @throws java.lang.Exception
     */
    @SuppressWarnings("unchecked")
    public static ComboBox getHierarchy(final ComboBox select) throws SystemException {
        LOGGER.debug("Entering getHierarchy method");
        List<Object[]> resultList;
        final List<HelperDTO> results = new ArrayList<>();
        final DynamicQuery companyDynamicQuery = DynamicQueryFactoryUtil.forClass(HierarchyDefinition.class);
        final ProjectionList projList = ProjectionFactoryUtil.projectionList();
        projList.add(ProjectionFactoryUtil.property("hierarchyDefinitionSid"));
        projList.add(ProjectionFactoryUtil.property(ConstantsUtils.HIERARCHY_NAME));
        companyDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));
        companyDynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.HIERARCHY_NAME));

        resultList = dao.getHierachyDefinitionList(companyDynamicQuery);

        for (int i = 0; i < resultList.size(); i++) {
            final Object[] obj = resultList.get(i);
            final HelperDTO dto = new HelperDTO();
            dto.setId((Integer) obj[0]);
            dto.setDescription(String.valueOf(obj[1]));
            results.add(dto);
        }
        select.setPageLength(NumericConstants.SEVEN);
        select.addItem(new HelperDTO(ConstantsUtils.SELECT_ONE));
        for (final Iterator<HelperDTO> iterator = results.iterator(); iterator.hasNext();) {
            final HelperDTO helperDTO = iterator.next();
            select.addItem(helperDTO);
        }
        select.select(0);
        select.addValueChangeListener(new Property.ValueChangeListener() {
          
            public void valueChange(final Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue()) || ConstantsUtils.NULL.equals(event.getProperty().getValue()))) {

                    select.select(ConstantsUtils.SELECT_ONE);
                }
            }
        });
        LOGGER.debug("getHierarchy return List<HelperDTO> results size " + results.size());
        return select;
    }

    /**
     * Gets the hierarchy.
     *
     * @return the hierarchy
     * @throws com.stpl.portal.kernel.exception.SystemException
     * @throws java.lang.Exception
     */
    @SuppressWarnings("unchecked")
    public static ComboBox getHistHierarchy(final ComboBox select, SessionDTO sessionDTO) throws SystemException {
        LOGGER.debug("Entering getHierarchy method");
        final int hierarchyVersion = sessionDTO.getHierarchyVersion();
        List<Object[]> resultList;
        final List<HelperDTO> results = new ArrayList<>();
        final DynamicQuery companyDynamicQuery = DynamicQueryFactoryUtil.forClass(HistHierarchyDefinition.class);
        final ProjectionList projList = ProjectionFactoryUtil.projectionList();
        projList.add(ProjectionFactoryUtil.property("primaryKey.hierarchyDefinitionSid"));
        projList.add(ProjectionFactoryUtil.property(ConstantsUtils.HIERARCHY_NAME));
        companyDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));
        companyDynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.HIERARCHY_NAME));
        companyDynamicQuery.add(RestrictionsFactoryUtil.eq("primaryKey.versionNo", hierarchyVersion));

        resultList = dao.getHistHierachyDefinitionList(companyDynamicQuery);

        for (int i = 0; i < resultList.size(); i++) {
            final Object[] obj = resultList.get(i);

            final HelperDTO dto = new HelperDTO();
            dto.setId((Integer) obj[0]);
            dto.setDescription(String.valueOf(obj[1]));
            results.add(dto);
        }
        select.setPageLength(NumericConstants.SEVEN);
        select.addItem(new HelperDTO(ConstantsUtils.SELECT_ONE));
        for (final Iterator<HelperDTO> iterator = results.iterator(); iterator.hasNext();) {
            final HelperDTO helperDTO = iterator.next();
            select.addItem(helperDTO);
        }
        select.select(0);
        select.addValueChangeListener(new Property.ValueChangeListener() {
           
            public void valueChange(final Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue()) || ConstantsUtils.NULL.equals(event.getProperty().getValue()))) {

                    select.select(ConstantsUtils.SELECT_ONE);
                }
            }
        });
        LOGGER.debug("getHierarchy return List<HelperDTO> results size " + results.size());
        return select;
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
        resultList = dao.getHierachyDefinitionList(companyDynamicQuery);
        for (int i = 0; i < resultList.size(); i++) {
            final Object[] obj = resultList.get(i);
            results.put(String.valueOf(obj[0]), String.valueOf(obj[1]));
        }
        LOGGER.debug("getHierarchyInfo return HashMap results size:" + results.size());
        return results;
    }

    /**
     * Gets the hierarchy info.
     *
     * @return the hierarchy info
     * @throws com.stpl.portal.kernel.exception.SystemException
     */
    public static Map getRelationShipHierarchyInfo() throws SystemException {
        LOGGER.debug("Entering getHierarchyInfo ");
        List<Object[]> resultList;
        final HashMap results = new HashMap();
        final DynamicQuery companyDynamicQuery = DynamicQueryFactoryUtil.forClass(HistHierarchyDefinition.class);
        final ProjectionList projList = ProjectionFactoryUtil.projectionList();
        projList.add(ProjectionFactoryUtil.property("primaryKey.hierarchyDefinitionSid"));
        projList.add(ProjectionFactoryUtil.property("hierarchyName"));
        companyDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));
        resultList = dao.getHierachyDefinitionHistoryList(companyDynamicQuery);
        for (int i = 0; i < resultList.size(); i++) {
            final Object[] obj = resultList.get(i);
            results.put(String.valueOf(obj[0]), String.valueOf(obj[1]));
        }
        LOGGER.debug("getHierarchyInfo return HashMap results size:" + results.size());
        return results;
    }

    /**
     * Convert date to string.
     *
     * @param aDate the a date
     * @return the string
     * @throws java.lang.Exception
     */
    public static String convertDateToString(final Date aDate) {
        return getDateTime(MMDDYYYY, aDate);
    }

    /*
     * @param aMask - Date format input
     * 
     * @param aDate - The Date object to be converted to string
     * 
     * @return String - date in string format
     */
    /**
     * Gets the date time.
     *
     * @param aMask the a mask
     * @param aDate the a date
     * @return the date time
     * @throws java.lang.Exception
     */
    public static String getDateTime(final String aMask, final Date aDate) {
        SimpleDateFormat dateFormat;
        String returnValue;
        LOGGER.debug("Entering getDateTime  with P1:String aMask=" + aMask + " and P2:Date aDate=" + aDate);
        if (aDate == null) {
            returnValue = EMPTY;
        } else {
            dateFormat = new SimpleDateFormat(aMask);
            returnValue = dateFormat.format(aDate);
            LOGGER.debug("getDateTime return String returnValue=" + returnValue);
        }
        return returnValue;
    }

    /**
     * to convert the 12/29/13 to 12/29/2013 format i.e 2 digit year format
     * entered by user to 4 digit year format
     *
     * @param enteredDate the entered date
     * @return 4 digit year format
     * @throws com.stpl.portal.kernel.exception.SystemException
     */
    public static Date convert2DigitTo4DigitYearFormat(final Date enteredDate) throws SystemException {
        Date returnDate;
        LOGGER.debug("convert2DigitTo4DigitYearFormat started with P1:Date enteredDate=" + enteredDate);
        if (enteredDate == null || enteredDate.equals(ConstantsUtils.EMPTY)) {
            returnDate = enteredDate;
        } else {
            try {
                if (enteredDate.toString().length() == NumericConstants.TWENTY_EIGHT) {
                    returnDate = enteredDate;
                } else {
                    final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
                    final SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy");
                    final Calendar cal = Calendar.getInstance();
                    cal.add(Calendar.YEAR, -(NumericConstants.FOURTEEN));
                    sdf.set2DigitYearStart(cal.getTime());
                    final String datesVal = sdf.format(enteredDate);
                    final Date temp = CommonUtils.convertStringToDate(fmt.format(sdf.parse(datesVal)));
                    returnDate = temp;
                }
            } catch (Exception e) {
                LOGGER.error(e);
                throw new SystemException(e);
            }
        }
        LOGGER.debug("convert2DigitTo4DigitYearFormat return Date enteredDate=" + enteredDate);
        return returnDate;
    }

    public static void main(String[] args) {
        LOGGER.debug("Thu Nov 11 00:00:00 GMT 93".length());
    }

    /*
     * @param strDate - input date in string format
     * 
     * @return Date - The Date object of the strDate in the format of
     * "MM/dd/yyyy"
     */
    /**
     * Convert string to date.
     *
     * @param strDate the str date
     * @return the date
     * @throws java.lang.Exception
     */
    public static Date convertStringToDate(final String strDate) throws ParseException{
        Date aDate;
        LOGGER.debug("Entering convertStringToDate started with P1:String strDate=" + strDate);
        if (strDate == null || strDate.equals(EMPTY) || strDate.equals(STRING_NULL)) {
            aDate = NULL_VALUE;
        } else {
            aDate = convertStringToDate(MMDDYYYY, strDate);
        }
        LOGGER.debug("convertStringToDate return Date aDate" + aDate);
        return aDate;
    }

    /*
     * @param aMask - Date format input
     * 
     * @param strDate - input date in string format
     * 
     * @return Date - The Date object of the strDate
     */
    /**
     * Convert string to date.
     *
     * @param aMask the a mask
     * @param strDate the str date
     * @return the date
     * @throws java.lang.Exception
     */
    public static Date convertStringToDate(final String aMask, final String strDate) throws ParseException {
        Date date;
        LOGGER.debug("convertStringToDate started with P1:String aMask=" + aMask + " and P2:String strDate" + strDate);
        SimpleDateFormat dateFormat;
        dateFormat = new SimpleDateFormat(aMask);
        date = dateFormat.parse(strDate);
        LOGGER.debug("convertStringToDate return Date date" + date);
        return date;
    }

    public static String getHelperDescription(int code) throws PortalException, SystemException {
        HelperTable helperTable = HelperTableLocalServiceUtil.getHelperTable(code);
        return helperTable.getDescription();
    }

    public static String getTableSystemId(String tableName) {
        String sqlQuery = "SELECT DISTINCT PRIMARY_KEY_COLUMN_NAME FROM VW_HELPER_LIST WHERE ACTUAL_TABLE_NAME = '" + tableName + "'";
        List list = HistRelationshipBuilderLocalServiceUtil.executeQuery(sqlQuery);
        String systemIdColumnName = "'0'";
        if (list != null && !list.isEmpty()) {
            systemIdColumnName = String.valueOf(list.get(0));
        }
        return systemIdColumnName;
    }
    public static List<String> getCreatedBy(String filterString) {
                 List<String> strList = new ArrayList<>();
        try {
                    final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(User.class);
            if (!filterString.contains(" ")) {
                Criterion criterion = RestrictionsFactoryUtil.ilike("firstName", filterString);
                Criterion criterion1 = RestrictionsFactoryUtil.ilike("lastName", filterString);
                dynamicQuery.add(RestrictionsFactoryUtil.or(criterion, criterion1));
                final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
                productProjectionList.add(ProjectionFactoryUtil.property("userId"));
                dynamicQuery.setProjection(productProjectionList);
                strList = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
            } else {
                if (filterString.indexOf(" ") != 1) {
                    String[] filter = filterString.split(" ");
                    if ((filter.length - 1) == 1) {
                        Criterion criterion = RestrictionsFactoryUtil.ilike("lastName", filter[0] + "%");
                        Criterion criterion1 = RestrictionsFactoryUtil.ilike("firstName",  "%" + filter[1]);
                        dynamicQuery.add(RestrictionsFactoryUtil.and(criterion, criterion1));
                        final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
                        productProjectionList.add(ProjectionFactoryUtil.property("userId"));
                        dynamicQuery.setProjection(productProjectionList);
                        strList = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
                    }
                }
            }
        } catch (SystemException ex) {
          LOGGER.error(ex);
        }
         return strList;
    }
}
