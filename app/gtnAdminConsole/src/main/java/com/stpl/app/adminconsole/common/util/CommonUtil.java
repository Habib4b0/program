/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.common.util;

import static com.stpl.app.adminconsole.common.util.CommonUtil.EMPTY;
import static com.stpl.app.adminconsole.common.util.CommonUtil.MMDDYYYY;
import static com.stpl.app.adminconsole.common.util.CommonUtil.STRING_NULL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;
import com.stpl.app.adminconsole.dao.HelperTableDAO;
import com.stpl.app.adminconsole.dao.impl.HelperTableDAOImpl;
import com.stpl.app.model.BrandMaster;
import com.stpl.app.model.HelperTable;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.TableResultCustom;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.util.HelperDTO;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.NativeSelect;
import com.vaadin.v7.ui.VerticalLayout;
import java.util.Arrays;
import com.stpl.app.adminconsole.util.HelperListUtil;
import com.stpl.app.adminconsole.dao.CommonDAO;
import com.stpl.app.adminconsole.dao.impl.CommonDAOImpl;
import com.stpl.ifs.ui.util.NumericConstants;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import static com.stpl.app.adminconsole.common.util.CommonUtil.convertStringToDate;
import com.stpl.app.adminconsole.service.AdminConsoleImpl;
import com.stpl.app.adminconsole.util.xmlparser.SQlUtil;
import com.stpl.app.service.BrandMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalService;
import com.stpl.app.service.HelperTableLocalServiceUtil;

/**
 * The Class CommonUtil.
 *
 * @author elangovan
 */
public final class CommonUtil {

    public static final char CHAR_PERCENT = '%';

    public static final char CHAR_ASTERISK = '*';

    public static final String MMDDYYYY = "MM/dd/yyyy";

    public static final String EMPTY = ConstantsUtils.EMPTY;

    public static final String STRING_NULL = "null";

    static CommonDAO DAO = new CommonDAOImpl();

    private static HelperTableDAO helperTableDAO = new HelperTableDAOImpl();

    private static final Logger LOGGER = Logger.getLogger(CommonUtil.class);

    private static final Date NULLOBJECT = null;
    
    public static final String VERSION = "Version";
    public static final String LISTNAME = "listName";
    public static final String DESCRIPTION = "description";
    public static final String DISCOUNT_DESC = "discountDesc";
    public static final String DEDUCTION_GROUP_NAME = "deductionGroupName";
    public static final String DEDUCTION_GROUP_SID = "deductionGroupSid";
    public static final String DISCOUNT_NAME = "discountName";
    public static final String VERSION_NO = "versionNo";
    public static final String DEDUCTION_GROUP_DESCRIPTION = "deductionGroupDescription";
    public static final String DISCOUNT_NO = "discountNo";
    public static final String DEDUCTION_GROUP_NO = "deductionGroupNo";
    public static final String NOT_ALL_REQUIRED_FIELDS = "Not all required fields have been populated.  Please complete all required fields and try again. ";
    public static final String ONE_FORTY_PIXELS = "140px";
    public static final String LOGIC = "logic";
    public static final String ERROR = "Error";
    public static final String TABLECHECKBOX = "TableCheckBox";
    public static final String FORECAST_YEAR = "forcastYear";
    public static final String CHECK = "check";
    public static final String DEDUCTION_GROUPING = "Deduction Grouping";
    public static final String FILE_MANAGEMENT = "File Management";
    public static final String LANDING_SCREEN = "Landing Screen";
    public static final String PROGRAM_TRACKING_LIST_VIEW = "Program Tracking List view";
    public CommonUtil() {
        LOGGER.debug("CommonUtil");
    }

    /**
     * to convert the 12/29/13 to 12/29/2013 format i.e 2 digit year format
     * entered by user to 4 digit year format
     *
     * @param enteredDate the entered date
     * @return 4 digit year format
     * @throws ParseException the parse exception
     */
    public static Date convert2DigitTo4DigitYearFormat(final Date enteredDate) throws ParseException {
        Date temp;
        if (enteredDate == null || EMPTY.equals(enteredDate)) {
            temp = enteredDate;
        } else {
            LOGGER.debug("entering convert2DigitTo4DigitYearFormat with P1:Date enteredDate" + enteredDate);
            final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
            final SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy");
            final Calendar cal = Calendar.getInstance();
            cal.add(Calendar.YEAR, -(NumericConstants.FOURTEEN));
            sdf.set2DigitYearStart(cal.getTime());
            final String datesVal = sdf.format(enteredDate);
            temp = CommonUtil.convertStringToDate(fmt.format(sdf.parse(datesVal)));
            LOGGER.debug("convert2DigitTo4DigitYearFormat return enteredDate" + enteredDate);
        }
        return temp;
    }

    /**
     * Convert string to date.
     *
     * @param strDate - input date in string format
     * @return date - The Date object of the strDate in the format of
     * "MM/dd/yyyy"
     * @throws ParseException the parse exception
     */
    public static Date convertStringToDate(final String strDate) throws ParseException {
        Date aDate;
        LOGGER.debug("Entering convertStringToDate with P1:String strDate=" + strDate);
        if (strDate == null || strDate.equals(EMPTY) || strDate.equals(STRING_NULL)) {
            LOGGER.debug("convertStringToDate return null");
            aDate = NULLOBJECT;
        } else {
            aDate = convertStringToDate(MMDDYYYY, strDate);
            LOGGER.debug("convertStringToDate return aDate" + aDate);
        }

        return aDate;
    }

    /**
     * Convert string to date.
     *
     * @param aMask - Date format input
     * @param strDate - input date in string format
     * @return Date - The Date object of the strDate
     * @throws ParseException the parse exception
     */
    public static Date convertStringToDate(final String aMask, final String strDate) throws ParseException {
        LOGGER.debug("Entering convertStringToDate Started with p1:aMask =" + aMask + ", p2:strDate = " + strDate);
        final SimpleDateFormat dateFormat = new SimpleDateFormat(aMask);
        final Date date = dateFormat.parse(strDate);
        LOGGER.debug("Return converted String Date " + date);
        return date;
    }

    /**
     * Gets the formatted table.
     *
     * @param table the table
     * @param formatCols the format cols
     * @return the formatted table
     */
    public static ExtFilterTable getFormattedTable(final ExtFilterTable table, final Object... formatCols) {
        LOGGER.debug("Entering getFormattedTable with p1:table ,p2:formatCols length" + formatCols.length);
        for (int i = 0; i < formatCols.length; i++) {
            table.setColumnAlignment(formatCols[i], ExtFilterTable.Align.CENTER);
        }
        LOGGER.debug("getFormattedTable Returning Table ");

        return table;
    }
     /**
     * Gets the formatted table.
     *
     * @param table the table
     * @param formatCols the format cols
     * @return the formatted table
     */
    public static ExtPagedTable getFormattedTable(final ExtPagedTable table, final Object... formatCols) {
        LOGGER.debug("Entering getFormattedTable with p1:table ,p2:formatCols length" + formatCols.length);
        for (int i = 0; i < formatCols.length; i++) {
            table.setColumnAlignment(formatCols[i], ExtPagedTable.Align.CENTER);
        }
        LOGGER.debug("getFormattedTable Returning Table ");

        return table;
    }

    /**
     * Gets the drop down.
     *
     * @param select the select
     * @param listName the list name
     * @return the drop down
     * @throws SystemException the system exception
     */
    public static NativeSelect getDropDown(final NativeSelect select, final String listName) throws SystemException {

        final List<HelperDTO> helperList = new ArrayList<>();
        LOGGER.debug("Entering getDropDown P1:select  and P2:listName=" + listName);
        final DynamicQuery cfpDynamicQuery = HelperTableLocalServiceUtil.dynamicQuery();

        cfpDynamicQuery.add(RestrictionsFactoryUtil.like(CommonUtil.LISTNAME, listName));
        cfpDynamicQuery.addOrder(OrderFactoryUtil.asc(CommonUtil.DESCRIPTION));
        final List<HelperTable> list = DAO.getHelperTableList(cfpDynamicQuery);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable.getDescription()));
            }
        }

        for (final Iterator<HelperDTO> iterator = helperList.iterator(); iterator.hasNext();) {
            final HelperDTO helperDTO = iterator.next();
            select.addItem(helperDTO.getDescription());
        }
        LOGGER.debug("getDropDown RETURN NativeSelect select");
        return select;
    }

    /**
     * Gets the drop down.
     *
     * @param select the select
     * @param listName the list name
     * @return the drop down
     * @throws SystemException the system exception
     */
    public static ComboBox getComboBox(final ComboBox select, final String listName) throws SystemException {

        final List<HelperDTO> helperList = new ArrayList<>();
        LOGGER.debug("Entering getDropDown P1:select and P2:listName=" + listName);
        final DynamicQuery cfpDynamicQuery = HelperTableLocalServiceUtil.dynamicQuery();

        cfpDynamicQuery.add(RestrictionsFactoryUtil.like(CommonUtil.LISTNAME, listName));
        cfpDynamicQuery.addOrder(OrderFactoryUtil.asc(CommonUtil.DESCRIPTION));
        final List<HelperTable> list = DAO.getHelperTableList(cfpDynamicQuery);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable.getDescription()));
            }
        }
        select.setPageLength(NumericConstants.SEVEN);

        for (final Iterator<HelperDTO> iterator = helperList.iterator(); iterator.hasNext();) {
            final HelperDTO helperDTO = iterator.next();
            select.addItem(helperDTO);
        }
        select.select(0);
        LOGGER.debug("getDropDown RETURN NativeSelect select" + select.size());

        return select;
    }

    public static ComboBox getFileTypeComboBox(final ComboBox select, final String listName, List<HelperDTO> fileTypeList) throws SystemException {

        final List<HelperDTO> helperList = new ArrayList<>();
        LOGGER.debug("Entering getDropDown  P1:select and P2:listName=" + listName);
        final DynamicQuery cfpDynamicQuery = HelperTableLocalServiceUtil.dynamicQuery();
        final HelperDTO fileTypeNullDto = new HelperDTO(0, ConstantsUtils.SELECT_ONE);
        Map<Integer, String> aliasNameMap  = new HashMap<>();
        helperList.add(fileTypeNullDto);
        cfpDynamicQuery.add(RestrictionsFactoryUtil.like(CommonUtil.LISTNAME, listName));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.ne(CommonUtil.DESCRIPTION, ConstantsUtils.INVENTORY_WITHDRAWAL));
        cfpDynamicQuery.addOrder(OrderFactoryUtil.asc(CommonUtil.DESCRIPTION));
        final List<HelperTable> list = DAO.getHelperTableList(cfpDynamicQuery);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                aliasNameMap.put(0,"");
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable.getDescription()));
                aliasNameMap.put(helperTable.getHelperTableSid(),helperTable.getAliasName());
            }
        }
        select.setPageLength(NumericConstants.SEVEN);
        for (final Iterator<HelperDTO> iterator = helperList.iterator(); iterator.hasNext();) {
            final HelperDTO helperDTO = iterator.next();
            select.addItem(helperDTO.getId());
            if(aliasNameMap.get(helperDTO.getId()).isEmpty()){
            select.setItemCaption(helperDTO.getId(), helperDTO.getDescription());
            }else {
            select.setItemCaption(helperDTO.getId(), aliasNameMap.get(helperDTO.getId()));
            }
            fileTypeList.add(helperDTO);
        }
        select.select(0);
        LOGGER.debug("getDropDown RETURN NativeSelect  select" + select.size());

        return select;
    }
    
    /**
     * Gets the created by user.
     *
     * @return the created by user
     * @throws SystemException the system exception
     */
    public static Map<String, String> getCreatedByUser() throws SystemException {

        LOGGER.debug("Entering getCreatedByUser()");
        final HashMap<String, String> userMap = new HashMap<>();
        final DynamicQuery userGroupDynamicQuery = UserLocalServiceUtil.dynamicQuery();
        final List<User> users = DAO.getUsersList(userGroupDynamicQuery);

        for (final Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
            final User user = iterator.next();
            userMap.put(String.valueOf(user.getUserId()), user.getLastName() + " " + user.getFirstName());
        }
        LOGGER.debug("getCreatedByUser return HashMap<String, String> userMap size" + userMap.size());
        return userMap;
    }

    /**
     * Gets the Description.
     *
     * @param helperTableSid the helper table sid
     * @return the created by user
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public static String getDescriptionFromHelper(final int helperTableSid) throws SystemException, PortalException {

        String description = ConstantsUtils.EMPTY;

        if (helperTableSid != ConstantsUtils.ZERO_NUM) {
            final HelperTable helperTable = helperTableDAO.getHelperTable(helperTableSid);
            description = helperTable.getDescription();

        }

        return description;
    }

    /**
     * Gets the ID.
     *
     * @param listName the list name
     * @return the ID from helper
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public static int getIDFromHelper(final String listName) throws SystemException {

        LOGGER.debug("Entering getIDFromHelper() --->> " + listName);
        int helperId = 0;
        final DynamicQuery dynamicQuery = HelperTableLocalServiceUtil.dynamicQuery();
        final ProjectionList projList = ProjectionFactoryUtil.projectionList();
        projList.add(ProjectionFactoryUtil.property("helperTableSid"));
        if (listName != null && !listName.isEmpty()) {
            dynamicQuery.add(RestrictionsFactoryUtil.like(CommonUtil.DESCRIPTION, listName));
        }
        dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));
        final List ids = helperTableDAO.getHelperID(dynamicQuery);
        for (int i = 0; i < ids.size(); i++) {
            helperId = (Integer) ids.get(i);
        }
        LOGGER.debug("getIDFromHelper return ID  " + helperId);
        return helperId;
    }

    /**
     * Gets the brand drop down.
     *
     * @param select the select
     * @return the brand drop down
     * @throws SystemException the system exception
     */
    public static NativeSelect getBrandDropDown(final NativeSelect select) throws SystemException {

        final List<HelperDTO> helperList = new ArrayList<>();

        final DynamicQuery brandDynamicQuery = BrandMasterLocalServiceUtil.dynamicQuery();
        final ProjectionList projList = ProjectionFactoryUtil.projectionList();
        projList.add(ProjectionFactoryUtil.property("brandMasterSid"));
        projList.add(ProjectionFactoryUtil.property(ConstantsUtils.BRAND_NAME));
        brandDynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.BRAND_NAME));
        final List<BrandMaster> list = DAO.getBrandNameandId(brandDynamicQuery);
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                final BrandMaster values = list.get(i);

                helperList.add(new HelperDTO(values.getBrandMasterSid(), values.getBrandName()));
            }
        }

        for (final Iterator<HelperDTO> iterator = helperList.iterator(); iterator.hasNext();) {
            final HelperDTO helperDTO = iterator.next();
            select.addItem(helperDTO);
        }
        LOGGER.debug("getBrandDropDown RETURN NativeSelect select");
        return select;
    }

    /**
     * Gets the brand combo box.
     *
     * @param select the select
     * @return the brand combo box
     * @throws SystemException the system exception
     */
    public static ComboBox getBrandComboBox(final ComboBox select) throws SystemException {

        final List<HelperDTO> helperList = new ArrayList<>();

        final DynamicQuery brandDynamicQuery = BrandMasterLocalServiceUtil.dynamicQuery();
        final ProjectionList projList = ProjectionFactoryUtil.projectionList();
        projList.add(ProjectionFactoryUtil.property("brandMasterSid"));
        projList.add(ProjectionFactoryUtil.property("brandName"));
        brandDynamicQuery.addOrder(OrderFactoryUtil.asc("brandName"));
        final List<BrandMaster> list = DAO.getBrandNameandId(brandDynamicQuery);
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                final BrandMaster values = list.get(i);
                if (!values.getBrandName().isEmpty() && !values.getBrandName().equals(ConstantsUtils.SELECT_ONE)) {
                    helperList.add(new HelperDTO(values.getBrandMasterSid(), values.getBrandName()));
                }
            }
        }
        select.setPageLength(NumericConstants.SEVEN);

        for (final Iterator<HelperDTO> iterator = helperList.iterator(); iterator.hasNext();) {
            final HelperDTO helperDTO = iterator.next();
            select.addItem(helperDTO);
        }

        select.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             *
             */
            public void valueChange(final Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue()) || ConstantsUtils.NULL.equals(event.getProperty().getValue()))) {

                }
            }
        });
        LOGGER.debug("getBrandDropDown RETURN NativeSelect select");
        return select;
    }

    /**
     * Gets the ID.
     *
     * @param description the list name
     * @return the ID from helper
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public static int getIDFromHelper(final String description, final String listName) throws SystemException {

        LOGGER.debug("Entering getIDFromHelper()");
        int helperId = 0;
        final DynamicQuery dynamicQuery = HelperTableLocalServiceUtil.dynamicQuery();
        final ProjectionList projList = ProjectionFactoryUtil.projectionList();
        projList.add(ProjectionFactoryUtil.property("helperTableSid"));
        if (description != null && !description.isEmpty()) {
            dynamicQuery.add(RestrictionsFactoryUtil.like(CommonUtil.DESCRIPTION, description));
        }
        if (listName != null && !listName.isEmpty() ) {
            dynamicQuery.add(RestrictionsFactoryUtil.like(CommonUtil.LISTNAME, listName));
        }
        dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));
        final List ids = helperTableDAO.getHelperID(dynamicQuery);
        for (int i = 0; i < ids.size(); i++) {
            helperId = (Integer) ids.get(i);
        }
        LOGGER.debug("getIDFromHelper return ID  " + helperId);
        return helperId;
    }

    public static List<Object> getFieldsForSecurity(String moduleName, String tabName) {
        List<Object> resultList = new ArrayList<>();

        try {
            resultList = new AdminConsoleImpl().fetchFieldsForSecurity(moduleName, tabName, null, null, null);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return resultList;
    }

    public void removeComponentOnPermission(List<Object> resultList, Object cssLayout, Map<String, AppPermission> fieldIfpHM, String mode) {
        try {
            int listSize = resultList.size();
            for (int i = 0; i < listSize; i++) {
                Object[] obj = (Object[]) resultList.get(i);
                getPermissionAndRemoveComponent(cssLayout, String.valueOf(obj[0]), String.valueOf(obj[1]), fieldIfpHM, mode);

            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    private void getPermissionAndRemoveComponent(Object cssLayout, String labelStr, String fieldStr, Map<String, AppPermission> fieldHM,
            String mode) {
        boolean appPermission = true;
        try {
            if (fieldStr != null) {
                if (ConstantsUtils.ADD.equals(mode)) {
                    appPermission = ((AppPermission) fieldHM.get(fieldStr)).isAddFlag();
                } else if (ConstantsUtils.EDIT.equals(mode)) {
                    appPermission = ((AppPermission) fieldHM.get(fieldStr)).isEditFlag();
                } else if (ConstantsUtils.VIEW.equals(mode)) {
                    appPermission = ((AppPermission) fieldHM.get(fieldStr)).isViewFlag();
                } else if (ConstantsUtils.SEARCH.equals(mode)) {
                    if (((AppPermission) fieldHM.get(fieldStr)).isViewFlag() && ((AppPermission) fieldHM.get(fieldStr)).isEditFlag() && ((AppPermission) fieldHM.get(fieldStr)).isAddFlag()) {
                        appPermission = true;
                    } else {
                        appPermission = false;
                    }
                }
                if (appPermission == false && labelStr != null) {
                        removeComponents(cssLayout, labelStr, fieldStr);
                }
            }
        } catch (Exception e) {

            LOGGER.error(e);
        }
    }

    public TableResultCustom getTableColumnsPermission(List<Object> resultList, Object[] obj, Map<String, AppPermission> fieldIfpHM, String mode) {
        TableResultCustom tableResultCustom;
        List<Object> strList = Arrays.asList(obj);
        List<String> columnList = new ArrayList<>();
        List<Object> columnList1 = new ArrayList<>();
        List<String> headerList = new ArrayList<>();
        List<String> headerList2 = new ArrayList<>();
        for (int i = 0; i < resultList.size(); i++) {
            Object[] objResult = (Object[]) resultList.get(i);
            String value = objResult[1].toString();
            if (strList.contains(value)) {
                columnList.add(value);
                headerList.add(objResult[0].toString());
            }
        }
        for (Object headerList1 : strList) {
            if (columnList.contains(headerList1.toString())) {
                columnList1.add(headerList1);
                headerList2.add(headerList.get(columnList.indexOf(headerList1.toString())));
            }
        }
        String[] headerArray = new String[headerList2.size()];
        headerArray = headerList2.toArray(headerArray);
        tableResultCustom = modifyTableResultSecurity(columnList1.toArray(), headerArray, fieldIfpHM, mode);
        return tableResultCustom;
    }

    public static TableResultCustom modifyTableResultSecurity(final Object[] obj, final String[] header, final Map<String, AppPermission> fieldHM, String mode)  {

        final TableResultCustom tblResultCustom = new TableResultCustom();
        boolean appPerm = false;

        String str = "";
        final List<Object> objResultList = new ArrayList();
        final List<String> objResultHeaderList = new ArrayList();
        if (mode != null) {
            for (int i = 0; i < obj.length; i++) {
                str = String.valueOf(obj[i]);
                if (fieldHM.get(str) != null) {
                    final AppPermission appPermission = fieldHM.get(str);
                    if ((ConstantsUtils.ADD).equals(mode)) {
                        appPerm = appPermission.isAddFlag();
                    }
                    if ((ConstantsUtils.EDIT).equals(mode)) {
                        appPerm = appPermission.isEditFlag();
                    }
                    if ((ConstantsUtils.VIEW).equals(mode)) {
                        appPerm = appPermission.isViewFlag();
                    }
                    if (ConstantsUtils.SEARCH.equals(mode)) {
                        if (appPermission.isViewFlag() && appPermission.isEditFlag() && appPermission.isAddFlag()) {
                            appPerm = true;
                        } else {
                            appPerm = false;
                        }
                    }

                    if (appPerm == true) {
                        objResultList.add(obj[i]);
                        objResultHeaderList.add(header[i]);
                    }
                }
            }
        }
        Object[] objResult = new Object[objResultList.size()];
        String[] objResultHeader = new String[objResultList.size()];
        for (int i = 0; i < objResultList.size(); i++) {
            objResult[i] = objResultList.get(i);
            objResultHeader[i] = objResultHeaderList.get(i);
        }
        tblResultCustom.setObjResult(objResult);
        tblResultCustom.setObjResultHeader(objResultHeader);

        return tblResultCustom;
    }

    public static TableResultCustom modifySearchTableResult(final Object[] obj, final String[] header, final Map<String, AppPermission> fieldHM) {

        final TableResultCustom tblResultCustom = new TableResultCustom();
        String str;
        final List<Object> objResultList = new ArrayList();
        final List<String> objResultHeaderList = new ArrayList();

        LOGGER.debug("Entering modifyTableResult with obj length:" + obj.length + ", header length:" + header.length + ", fieldHM size:" + fieldHM.size());
        for (int i = 0; i < obj.length; i++) {
            str = String.valueOf(obj[i]);
            if (fieldHM.get(str) != null) {
                final AppPermission appPermission = fieldHM.get(str);

                if (appPermission.isSearchFlag() && !objResultList.contains(obj[i])) {
                        objResultList.add(obj[i]);
                        objResultHeaderList.add(header[i]);
                }
            }
        }
        Object[] objResult = new Object[objResultList.size()];
        String[] objResultHeader = new String[objResultList.size()];
        for (int i = 0; i < objResultList.size(); i++) {
            objResult[i] = objResultList.get(i);
            objResultHeader[i] = objResultHeaderList.get(i);
        }
        tblResultCustom.setObjResult(objResult);
        tblResultCustom.setObjResultHeader(objResultHeader);

        LOGGER.debug("Ends modifyTableResult with tblResultCustom");
        return tblResultCustom;
    }

    public void removeSearchComponentOnPermission(List<Object> resultList, CssLayout cssLayout, Map<String, AppPermission> fieldIfpHM) {
        try {
            int listSize = resultList.size();
            for (int i = 0; i < listSize; i++) {
                Object[] obj = (Object[]) resultList.get(i);
                getSearchPermissionAndRemoveComponent(cssLayout, String.valueOf(obj[0]), String.valueOf(obj[1]), fieldIfpHM);

            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    private void getSearchPermissionAndRemoveComponent(CssLayout cssLayout, String labelStr, String fieldStr, Map<String, AppPermission> fieldHM
    ) {
        boolean appPermission = true;
        try {
            if (fieldStr != null) {

                appPermission = ((AppPermission) fieldHM.get(fieldStr)).isSearchFlag();

                if (appPermission == false && labelStr != null) {
                        removeComponents(cssLayout, labelStr, fieldStr);
                    }
            }
        } catch (Exception e) {

            LOGGER.error(e);
        }
    }

    void removeComponents(Object cssLayout, String labelStr, String fieldStr) {
        if (cssLayout instanceof com.vaadin.ui.CssLayout) {
            CssLayout layout = (CssLayout) cssLayout;
            for (int j = 0; j < layout.getComponentCount(); j++) {
                if (((layout.getComponentCount() >= 1) && (layout.getComponent(j).getClass().isInstance(new VerticalLayout()))) || layout.getComponent(j).getClass().isInstance(new HorizontalLayout()) || layout.getComponent(j).getClass().isInstance(new GridLayout())) {
                    removeComponents(layout.getComponent(j), labelStr, fieldStr);
                } else {
                    int i = j;
                    if (layout.getComponent(i) != null && layout.getComponent(i).getClass().isInstance(new Label())) {
                        Label l = (Label) layout.getComponent(i);

                        if (labelStr.equals(l.getValue())) {
                            layout.removeComponent(layout.getComponent(i));
                        }
                    }
                    if (layout.getComponent(i) != null && layout.getComponent(i).getId() != null && layout.getComponent(i).getId().equals(fieldStr)) {
                        layout.removeComponent(layout.getComponent(i));
                    }
                }
            }
        } else if (cssLayout instanceof com.vaadin.ui.VerticalLayout) {
            VerticalLayout layout = (VerticalLayout) cssLayout;
            for (int j = 0; j < layout.getComponentCount(); j++) {
                if (((layout.getComponentCount() >= 1) && (layout.getComponent(j).getClass().isInstance(new VerticalLayout()))) || layout.getComponent(j).getClass().isInstance(new HorizontalLayout()) || layout.getComponent(j).getClass().isInstance(new GridLayout())) {
                    removeComponents(layout.getComponent(j), labelStr, fieldStr);
                } else {
                    int i = j;
                    if (layout.getComponent(i) != null && layout.getComponent(i).getClass().isInstance(new Label())) {
                        Label l = (Label) layout.getComponent(i);

                        if (labelStr.equals(l.getValue())) {

                            layout.removeComponent(layout.getComponent(i));
                        }
                    }
                    if (layout.getComponent(i).getId() != null && layout.getComponent(i).getId().equals(fieldStr)) {
                        layout.removeComponent(layout.getComponent(i));
                    }

                }
            }
        } else if (cssLayout instanceof com.vaadin.ui.HorizontalLayout) {
            HorizontalLayout layout = (HorizontalLayout) cssLayout;
            for (int j = 0; j < layout.getComponentCount(); j++) {

                if (((layout.getComponentCount() >= 1) && (layout.getComponent(j).getClass().isInstance(new VerticalLayout()))) || layout.getComponent(j).getClass().isInstance(new HorizontalLayout()) || layout.getComponent(j).getClass().isInstance(new GridLayout())) {
                    removeComponents(layout.getComponent(j), labelStr, fieldStr);
                } else {
                    int i = j;
                    if (layout.getComponent(i) != null && layout.getComponent(i).getClass().isInstance(new Label()) && labelStr.equals(((Label) layout.getComponent(i)).getValue())) {
                            layout.removeComponent(layout.getComponent(i));
                    }
                    if (layout.getComponent(i) != null && layout.getComponent(i).getId() != null && layout.getComponent(i).getId().equals(fieldStr)) {
                        layout.removeComponent(layout.getComponent(i));
                    }
                }
            }
        } else if (cssLayout instanceof com.vaadin.ui.GridLayout) {
            GridLayout layout = (GridLayout) cssLayout;
            for (int j = 0; j < layout.getComponentCount(); j++) {
                if (layout.getComponentCount() >= 1) {
                    for (int r = 0; r < layout.getRows(); r++) {
                        for (int c = 0; c < layout.getColumns(); c++) {
                            if (layout.getComponent(r, c) != null) {
                                if ((layout.getComponent(r, c).getClass().isInstance(new VerticalLayout())) || layout.getComponent(r, c).getClass().isInstance(new HorizontalLayout()) || layout.getComponent(r, c).getClass().isInstance(new GridLayout())) {
                                    removeComponents(layout.getComponent(r, c), labelStr, fieldStr);
                                } else {
                                    if (layout.getComponent(r, c) != null && (layout.getComponent(r, c).getClass().isInstance(new Label()))) {
                                        Label l = (Label) layout.getComponent(r, c);

                                        if (labelStr.equals(l.getValue())) {
                                            layout.removeComponent(layout.getComponent(r, c));
                                        }
                                    }
                                    if (layout.getComponent(r, c) != null && (layout.getComponent(r, c).getId() != null && layout.getComponent(r, c).getId().equals(fieldStr))) {
                                        layout.removeComponent(layout.getComponent(r, c));
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }
    }

    public static String replacedQuery(Map<String, String> input, String queryName) {
        StringBuilder queryString = new StringBuilder();
        try {
            queryString = new StringBuilder(SQlUtil.getQuery(queryName));
            if (input != null) {
                for (Map.Entry<String, String> entry : input.entrySet()) {
                    final String string = entry.getKey();
                    final String string1 = entry.getValue();
                    while (queryString.toString().contains(string)) {
                        queryString.replace(queryString.indexOf(string), queryString.indexOf(string) + string.length(), String.valueOf(string1));
                    }
                }
            }

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return queryString.toString();
    }

    public static HelperDTO getSelectedFileType(ComboBox fileType) {
        HelperDTO selectedFileType = null;
        if(fileType.getValue() != null && !String.valueOf(fileType.getValue()).equals("0")) {
            String Desc = HelperListUtil.getInstance().getIdHelperDTOMap().get(fileType.getValue()).getDescription();
            selectedFileType = new HelperDTO(Integer.valueOf(String.valueOf(fileType.getValue())),Desc);
        }
        return selectedFileType;
    }
    
}
