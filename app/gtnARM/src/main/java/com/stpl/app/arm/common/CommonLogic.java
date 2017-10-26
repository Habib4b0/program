/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.common;

import com.stpl.app.arm.adjustmentrateconfiguration.AdjustmentRateUI;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.pipelineinventory.dto.PipelineInventorySelectionDTO;
import com.stpl.app.arm.businessprocess.transaction6.dto.Trx6_SelectionDTO;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.dao.CommonDao;
import com.stpl.app.arm.dao.impl.CommonImpl;
import com.stpl.app.arm.dataselection.dto.DataSelectionDTO;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.utils.VariableConstants;
import com.stpl.app.utils.xmlparser.SQlUtil;
import com.stpl.ifs.ui.NotesDTO;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.QueryUtil;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.stpl.portal.NoSuchUserException;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.stpl.portal.service.UserLocalServiceUtil;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.UI;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.vaadin.alump.beforeunload.BeforeUnload;

/**
 *
 * @author
 */
public class CommonLogic {

    private static final Logger LOGGER = Logger.getLogger(CommonLogic.class);

    final static CommonDao ITEMDAO = CommonImpl.getInstance();

    public static String getComboDes(int adjustmentType, String listName) {
        List<HelperDTO> list = HelperListUtil.getInstance().getListNameMap().get(listName);
        if (list != null && !list.isEmpty()) {
            for (HelperDTO helperDTO : list) {
                if (helperDTO.getId() == adjustmentType) {
                    return helperDTO.getDescription();
                }
            }
        }
        return StringUtils.EMPTY;
    }

    public static boolean checkInt(int value) {
        return value == 0;
    }

    public static boolean checkString(String value) {
        return value != null && value.equals(StringUtils.EMPTY);
    }

    public static List<HelperDTO> getDataForAdjustMentType() {
        return HelperListUtil.getInstance().getListNameMap().get("ARM_ADJUSTMENT_TYPE");
    }

    private CommonLogic() {

    }
    static CommonDao DAO = CommonImpl.getInstance();
    static Map<String, List> helperListUtil = new HashMap();
    private static final CustomComparator sorter = new CustomComparator();
    public static HelperDTO DDLB_DEFAULT_VALUE = new HelperDTO(0, GlobalConstants.getSelectOne());
    public static HelperDTO DDLB_SHOW_ALL_VALUE = new HelperDTO(0, GlobalConstants.getShowAll());

    /**
     * For Loading Drop downs based on the SQL Query Results. The
     * companyBusiness Flag is for loading company / Business Drop down in setup
     * and configuration of ARM
     *
     * @param comboBox
     * @param sqlID
     * @param companyBusinessFlag
     */
    public static void configureDropDowns(ComboBox comboBox, String sqlID, boolean companyBusinessFlag) {
        comboBox.setImmediate(true);
        comboBox.addItem(0);
        comboBox.setItemCaption(0, GlobalConstants.getSelectOne());
        comboBox.setNullSelectionAllowed(false);
        loadReserveRateDropdowns(comboBox, companyBusinessFlag, sqlID);
        comboBox.select(0);
    }

    /**
     * Logic For Loading Drop downs based on the SQL Query Results. The
     * companyBusiness Flag is for loading company / Business Drop down in setup
     * and configuration of ARM
     *
     * @param comboBox
     * @param companyBusinessUnitFlag
     * @param sqlID
     */
    public static void loadReserveRateDropdowns(ComboBox comboBox, boolean companyBusinessUnitFlag, String sqlID) {
        String sqlQuery = SQlUtil.getQuery(sqlID);
        if (companyBusinessUnitFlag) {
            List<Object[]> arr = HelperTableLocalServiceUtil.executeSelectQuery(sqlQuery);

            for (Object[] obj : arr) {
                if (obj[1] != null && obj[NumericConstants.TWO] != null) {
                    comboBox.addItem((int) obj[0]);
                    comboBox.setItemCaption((int) obj[0], obj[1] + " - " + obj[NumericConstants.TWO]);
                }
            }
        } else {
            List<Object> arr = HelperTableLocalServiceUtil.executeSelectQuery(sqlQuery);
            comboBox.addItems(arr);
        }
    }

    public static ComboBox getComboBoxByListNameSorted(ComboBox comboBox, String listName, Boolean isFilter, Map<Integer, String[]> map) {
        BeanItemContainer<HelperDTO> container = new BeanItemContainer<>(HelperDTO.class);
        final HelperDTO defaultValue = isFilter ? DDLB_SHOW_ALL_VALUE : DDLB_DEFAULT_VALUE;
        String comboboxName = listName;
        comboBox.setValidationVisible(true);
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(true);
        comboBox.setNullSelectionItemId(defaultValue);
        comboBox.setContainerDataSource(container);
        comboBox.addItem(0);
        comboBox.setItemCaption(0, GlobalConstants.getSelectOne());
        if (helperListUtil.get(comboboxName) == null) {
            List input = new ArrayList();
            input.add(listName);
            List<Object[]> list = QueryUtils.getItemData(input, "ComboBox List Name Query", null);
            List<HelperDTO> resultList = new ArrayList<>();
            resultList.add(defaultValue);
            for (Object[] str : list) {
                if (!str[1].equals(String.valueOf(GlobalConstants.getSelectOne()))) {
                    HelperDTO dto = new HelperDTO();
                    dto.setId(str[0] == null ? 0 : Integer.valueOf(str[0].toString()));
                    dto.setDescription(str[1] == null ? ARMUtils.ZERO_STRING : String.valueOf(str[1]));
                    resultList.add(dto);
                }
            }
            helperListUtil.put(comboboxName, resultList);
            Collections.sort(resultList, sorter);
        }
        comboBox.select(defaultValue);
        comboBox.setImmediate(true);
        comboBox.setItemCaptionPropertyId("description");
        for (int i = 0; i < helperListUtil.get(listName).size(); i++) {
            HelperDTO helper = (HelperDTO) helperListUtil.get(listName).get(i);
            comboBox.addItem(helper.getId());
            comboBox.setItemCaption(helper.getId(), helper.getDescription());
            if (i == (helperListUtil.get(listName)).size() - 1) {
                map.put(NumericConstants.FOUR, new String[]{helper.getId() + StringUtils.EMPTY, helper.getDescription()});
            } else if (i > NumericConstants.THREE) {
                map.put(i + 1, new String[]{helper.getId() + StringUtils.EMPTY, helper.getDescription()});
            } else {
                map.put(i, new String[]{helper.getId() + StringUtils.EMPTY, helper.getDescription()});
            }
        }
        container.addAll(helperListUtil.get(listName));
        return comboBox;
    }

    static class CustomComparator implements Comparator<HelperDTO> {

        @Override
        public int compare(HelperDTO o1, HelperDTO o2) {
            return Integer.valueOf(o1.getId()).compareTo(o2.getId());
        }
    }

    public static ComboBox getComboBoxByListNameForTable(ComboBox comboBox, String listName) {
        String comboboxName = listName;
        comboBox.setValidationVisible(true);
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(true);
        comboBox.setNullSelectionItemId(0);
        comboBox.addItem(0);
        comboBox.setItemCaption(0, GlobalConstants.getSelectOne());
        if (helperListUtil.get(comboboxName) == null) {
            List input = new ArrayList();
            input.add(listName);
            List<Object[]> list = QueryUtils.getItemData(input, "ComboBox List Name Query", null);
            List<HelperDTO> resultList = new ArrayList<>();
            for (Object[] str : list) {
                if (!str[1].equals(String.valueOf(GlobalConstants.getSelectOne()))) {
                    HelperDTO dto = new HelperDTO();
                    dto.setId(str[0] == null ? 0 : Integer.valueOf(str[0].toString()));
                    dto.setDescription(str[1] == null ? ARMUtils.ZERO_STRING : String.valueOf(str[1]));
                    resultList.add(dto);
                }
            }

            helperListUtil.put(comboboxName, resultList);
        }
        comboBox.select(null);
        comboBox.setImmediate(true);
        for (int i = 0; i < helperListUtil.get(listName).size(); i++) {
            HelperDTO helper = (HelperDTO) helperListUtil.get(listName).get(i);
            comboBox.addItem(helper.getId());
            comboBox.setItemCaption(helper.getId(), helper.getDescription());
        }
        return comboBox;
    }

    public static ComboBox getComboBoxByQueryName(ComboBox comboBox, String queryName) {
        BeanItemContainer<HelperDTO> container = new BeanItemContainer<>(HelperDTO.class);
        String comboboxName = queryName;
        comboBox.setValidationVisible(true);
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(Boolean.FALSE);
        comboBox.addItem(0);
        comboBox.setItemCaption(0, GlobalConstants.getSelectOne());
        if (helperListUtil.get(comboboxName) == null) {
            List<Object[]> list = QueryUtils.getItemData(Collections.EMPTY_LIST, queryName, null);
            List<HelperDTO> resultList = new ArrayList<>();
            for (Object[] str : list) {
                if (!str[1].equals(String.valueOf(GlobalConstants.getSelectOne()))) {
                    String description;
                    HelperDTO dto = new HelperDTO();
                    dto.setId(str[0] == null ? 0 : Integer.valueOf(str[0].toString()));
                    description = str[1] == null ? ARMUtils.ZERO_STRING : String.valueOf(str[1]);
                    if (str.length > NumericConstants.TWO) {
                        description += " - ";
                        description += str[NumericConstants.TWO] == null ? ARMUtils.ZERO_STRING : String.valueOf(str[NumericConstants.TWO]);
                    }
                    dto.setDescription(description);
                    resultList.add(dto);
                }
            }

            helperListUtil.put(comboboxName, resultList);
        } else {
            container.addAll(helperListUtil.get(queryName));
        }
        comboBox.select(0);
        comboBox.setImmediate(true);
        for (int i = 0; i < helperListUtil.get(comboboxName).size(); i++) {
            HelperDTO helper = (HelperDTO) helperListUtil.get(comboboxName).get(i);
            comboBox.addItem(helper.getId());
            comboBox.setItemCaption(helper.getId(), helper.getDescription());
        }
        return comboBox;
    }

    public static ComboBox getComboBoxByQueryNameForARM(ComboBox comboBox, String queryName, Boolean isFilter) {
        BeanItemContainer<HelperDTO> container = new BeanItemContainer<>(HelperDTO.class);
        final HelperDTO defaultValue = isFilter ? DDLB_SHOW_ALL_VALUE : DDLB_DEFAULT_VALUE;
        String comboboxName = queryName;
        comboBox.setValidationVisible(true);
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(true);
        comboBox.setNullSelectionItemId(defaultValue);
        comboBox.setContainerDataSource(container);
        container.addBean(defaultValue);
        if (helperListUtil.get(comboboxName) == null) {
            List<Object[]> list = QueryUtils.getItemData(Collections.EMPTY_LIST, queryName, null);
            List<HelperDTO> resultList = new ArrayList<HelperDTO>();
            resultList.add(defaultValue);
            for (Object[] str : list) {
                if (!str[1].equals(String.valueOf(GlobalConstants.getSelectOne()))) {
                    String description;
                    HelperDTO dto = new HelperDTO();
                    dto.setId(str[0] == null ? 0 : Integer.valueOf(str[0].toString()));
                    description = str[1] == null ? ARMUtils.ZERO_STRING : String.valueOf(str[1]);
                    description += str[NumericConstants.TWO] == null ? ARMUtils.ZERO_STRING : String.valueOf(str[NumericConstants.TWO]);
                    dto.setDescription(description);
                    resultList.add(dto);
                }
            }

            helperListUtil.put(comboboxName, resultList);
        } else {
            container.addAll(helperListUtil.get(queryName));
        }
        comboBox.select(defaultValue);
        comboBox.setImmediate(true);
        comboBox.setItemCaptionPropertyId("description");
        container.addAll(helperListUtil.get(queryName));
        return comboBox;
    }

    public static Date parseDate(String value) throws ParseException {
        Date date = null;
        value = convertNullToEmpty(value);
        SimpleDateFormat parse = new SimpleDateFormat(ARMUtils.YYYY_MM_DD);
        if (value != null && !StringUtils.EMPTY.equals(value) && !ARMUtils.NULL.equals(value)) {
            date = parse.parse(value);
        }
        return date;
    }

    public static String convertNullToEmpty(Object value) {
        String returnValue;
        if (value == null || ARMUtils.NULL.equals(String.valueOf(value))) {
            returnValue = StringUtils.EMPTY;
        } else {
            returnValue = String.valueOf(value);
        }
        return returnValue;
    }

    public static int getCount(List<Object[]> list) {
        if (!list.isEmpty()) {
            Object obj = list.get(0);
            int count = obj == null ? 0 : (Integer) obj;
            return count;
        }

        return 0;
    }

    public static Boolean getCountValue(List<Object[]> list) {
        if (!list.isEmpty()) {
            Object obj = list.get(0);
            Boolean count = obj == null ? null : (Integer) obj == 1;
            return count;
        }
        return null;

    }

    public static Boolean getduplicateCount(List<Object[]> list) {
        if (!list.isEmpty()) {
            Object obj = list.get(0);
            Boolean count = obj == null ? null : (Integer) obj > 0;
            return count;
        }
        return null;

    }

    public static int parseStringToInteger(final String value) {
        // max length of integer that can be parsed without error is 9
        return value.equals(StringUtils.EMPTY) ? 0 : Integer.parseInt(value.replaceAll(ARMUtils.REGEX_EXTRACT_DIGITS, StringUtils.EMPTY));
    }

    public static List<String> convertIngeterListToString(List<Integer> integetList) {
        List<String> stringList = new ArrayList<String>();

        for (int sid : integetList) {
            stringList.add(String.valueOf(sid));
        }

        return stringList;
    }

    public static String stringListToString(List stringList) {
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

    public static String listToString(List stringList) {
        StringBuilder builder = new StringBuilder(StringUtils.EMPTY);
        if (stringList != null && !stringList.isEmpty()) {
            for (int loop = 0, limit = stringList.size(); loop < limit; loop++) {
                builder.append(stringList.get(loop));
                if (loop != (limit - 1)) {
                    builder.append(", ");
                }
            }
        }
        return builder.toString();
    }

    public static List<String> getTempTableList() {
        List<String> tempTables = new ArrayList<String>();
        tempTables.add("ST_NM_SALES_PROJECTION");
        tempTables.add("ST_NM_ACTUAL_SALES");
        tempTables.add("ST_NM_SALES_PROJECTION_MASTER");
        tempTables.add("ST_NM_DISCOUNT_PROJECTION");
        tempTables.add("ST_NM_ACTUAL_DISCOUNT");
        tempTables.add("ST_NM_DISCOUNT_PROJ_MASTER");
        tempTables.add("ST_NM_PPA_PROJECTION");
        tempTables.add("ST_NM_ACTUAL_PPA");
        tempTables.add("ST_NM_PPA_PROJECTION_MASTER");
        tempTables.add("ST_NM_ASSUMPTIONS");
        tempTables.add("NM_SALES_PROJECTION");
        tempTables.add("NM_ACTUAL_SALES");
        tempTables.add("NM_SALES_PROJECTION_MASTER");
        tempTables.add("NM_DISCOUNT_PROJECTION");
        tempTables.add("NM_ACTUAL_DISCOUNT");
        tempTables.add("NM_DISCOUNT_PROJ_MASTER");
        tempTables.add("NM_PPA_PROJECTION");
        tempTables.add("NM_ACTUAL_PPA");
        tempTables.add("NM_PPA_PROJECTION_MASTER");
        tempTables.add("NM_ASSUMPTIONS");
        tempTables.add("PROJECTION_DETAILS");
        return tempTables;
    }

    public static String formatDate(String value) throws ParseException {
        String date = StringUtils.EMPTY;
        SimpleDateFormat parse = new SimpleDateFormat(ARMUtils.YYYY_MM_DD_HH_MM_SS_SSS);
        SimpleDateFormat format = new SimpleDateFormat(ARMUtils.MM_dd_yyyy_hh_mm_ss);
        if (value != null && !StringUtils.EMPTY.equals(value) && !ARMUtils.NULL.equals(value)) {
            date = format.format(parse.parse(value));
        }
        return date;
    }

    public static User getUser(final String userId) throws PortalException, SystemException {
        User loggedUserDetails = null;

        try {
            loggedUserDetails = UserLocalServiceUtil.getUser(Long.valueOf(userId));
        } catch (NoSuchUserException noSuchUserException) {
            LOGGER.error(noSuchUserException);
            loggedUserDetails = null;
        }

        return loggedUserDetails;
    }

    public static ComboBox loadCompanyAndBusinessUnit(ComboBox comboBox, String QueryName) {
        String query = SQlUtil.getQuery(QueryName);
        List<Object[]> arr = HelperTableLocalServiceUtil.executeSelectQuery(query);
        for (Object[] obj : arr) {
            if (obj[0] != null && obj[1] != null) {
                comboBox.addItem((int) obj[0]);
                comboBox.setItemCaption((int) obj[0], obj[1].toString());
            }
        }
        return comboBox;
    }

    public static Map<String, Integer> loadDeductionLevelMapForInteger(Map<String, Integer> deductionLevelMapForInteger) {
        deductionLevelMapForInteger.clear();
        deductionLevelMapForInteger.put(ARMConstants.getDeductionCategory(), 1);
        deductionLevelMapForInteger.put(ARMConstants.getDeductionType(), NumericConstants.TWO);
        deductionLevelMapForInteger.put(ARMConstants.getDeductionProgram(), NumericConstants.THREE);
        deductionLevelMapForInteger.put(ARMConstants.getDeductionCategory2(), NumericConstants.FIVE);
        deductionLevelMapForInteger.put(ARMConstants.getDeductionCategory3(), NumericConstants.SIX);
        deductionLevelMapForInteger.put(ARMConstants.getDeductionCategory4(), NumericConstants.SEVEN);
        deductionLevelMapForInteger.put(ARMConstants.getDeductionCategory5(), NumericConstants.EIGHT);
        deductionLevelMapForInteger.put(ARMConstants.getDeductionCategory6(), NumericConstants.NINE);
        deductionLevelMapForInteger.put(ARMConstants.getDeduction(), NumericConstants.FOUR);
        return deductionLevelMapForInteger;
    }

    public static Map<String, Object> getVariableMap(Map<String, Object> salesResultMap) {
        salesResultMap.clear();
        salesResultMap.put("Total Units", "totalUnits");
        salesResultMap.put("Total Sales", "totalSales");
        salesResultMap.put("Excluded Units", "excludedUnits");
        salesResultMap.put("Excluded Sales", "excludedSales");
        salesResultMap.put("Net Units", "netUnits");
        salesResultMap.put("Net Sales", "netSales");
        salesResultMap.put("Price", "price");
        salesResultMap.put("Price Override", "priceOverride");
        salesResultMap.put("Net Calculated Sales", "netCalculatedSales");
        salesResultMap.put("Sales Variance", "salesVariance");
        salesResultMap.put("Sales Variance %", "salesVariancepercent");
        return salesResultMap;
    }

    /**
     * <h1>Before Unload function</h1>
     * <p>
     * Purpose : Fix for the Communication Error <br>
     * The Before Unload function is used to Close the Vaadin UI when the UI is
     * unloaded from the browser , so that the data will not retained when the
     * user navigates back to same UI. <br>
     *
     * Comments about Code : Action performed after the listener is fix for the
     * Refresh problem <br>
     * Refresh Problems Faced : As the Vaadin Session Values has been changed to
     * Sesison DTO, on refresh the Id gets flushed and Screen collapse. The
     * Actions performed after the Unload listener is to maintain the required
     * ID
     *
     *
     * @param uI - UI Object
     * @param sessionDTO - SessionDTO Object
     * @return
     */
    public static final void beforeUnloadCloseUi(final UI uI) {
        BeforeUnload ob = BeforeUnload.closeBeforeUnload(uI);
        ob.addUnloadListener(new BeforeUnload.UnloadListener() {

            @Override
            public void unload(BeforeUnload.UnloadEvent event) {
                if (AdjustmentRateUI.EXCEL_CLOSE) { // Fix to avoid blank page issue while excel export
                    AdjustmentRateUI.EXCEL_CLOSE = false;
                } else {
                    uI.close();
    }
            }
        });
    }

    public static ComboBox loadDropdowns(ComboBox comboBox, String sqlId) {
        String query = SQlUtil.getQuery(sqlId);
        List<Object[]> list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        comboBox.removeAllItems();
        comboBox.setImmediate(true);
        comboBox.addItem(0);
        comboBox.setItemCaption(0, GlobalConstants.getSelectOne());
        comboBox.setNullSelectionAllowed(true);
        comboBox.setNullSelectionItemId(0);
        for (Object[] obj : list) {
            comboBox.addItem((int) obj[0]);
            comboBox.setItemCaption((int) obj[0], (String) obj[1]);
        }
        return comboBox;
    }

    /**
     * Method is used for saving the notes entered .
     *
     * @param projectionId - projectionId to be saved .
     * @param createdBy - Created User name .
     * @param notes - Entering Notes .
     * @param moduleName - Module Name like NM.
     * @return boolean value .
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public static void saveNotes(final int projectionId, final String createdBy, final List<String> notes, final String moduleName, final String reasonCode) throws SystemException {
        LOGGER.debug("Entering saveNotes method with with projectionId " + projectionId + " createdBy " + createdBy + " notes " + notes + " moduleName " + moduleName);
        String baseQuery = SQlUtil.getQuery("insertAdditionalNotes");
        Date date = new Date();
        String param = "(" + projectionId + " , '" + moduleName + "' , " + createdBy + " , '" + new Timestamp(date.getTime()) + "' , " + "@NOTES" + " , '" + reasonCode + "')";
        String parameters = "(" + projectionId + " , '" + moduleName + "' , '" + createdBy + "' , '" + new Timestamp(date.getTime()) + "' , '" + notes.get(0) + "', '" + reasonCode + "' )";
        for (int i = 1; i < notes.size(); i++) {
            parameters += ", ";
            parameters += (param.replace("@NOTES", "'" + notes.get(i) + "'"));
        }
        HelperTableLocalServiceUtil.executeUpdateQuery(baseQuery + parameters);
        LOGGER.debug("End of saveNotes method");
    }

    /**
     * Method o Delete the Uploaded File
     *
     * @param docDetailsId
     * @param moduleName
     * @param fileName
     * @return true or false
     * @throws SystemException
     * @throws PortalException
     */
    public static Boolean deleteUploadedFile(List<NotesDTO> docDetailsIds) throws PortalException, SystemException {
        String deleteQuery = SQlUtil.getQuery("removeUploadDocs");
        String param = docDetailsIds.get(0).getDocDetailsId() + "";
        for (int i = 1; i < docDetailsIds.size(); i++) {
            param = ", " + docDetailsIds.get(i).getDocDetailsId();
        }
        deleteQuery = deleteQuery.replace("@PROJECTION_IDS", param);
        CommonImpl.getInstance().executeUpdate(deleteQuery);
        return true;
    }

    public static void saveUploadedFile(int projectionId, List<NotesDTO> fileNames, String uploadedBy, int fileSize, String moduleName) throws SystemException, PortalException {
        final DecimalFormat formatter = new DecimalFormat("#.#");
        String deleteQuery = SQlUtil.getQuery("deleteUploadDocs");
        deleteQuery = deleteQuery.replace("@PROJECTION_IDS", projectionId + "");

        String insertQuery = SQlUtil.getQuery("insertUploadDocs");
        String fileName = fileNames.get(0).getDocumentName();
        String fileType = StringUtils.EMPTY;
        if (fileName.indexOf('.') == -1) {
            fileName = fileName + ".";
        } else {
            fileType = fileName.substring(fileName.lastIndexOf('.') + 1);
            fileName = fileName.substring(0, fileName.indexOf('.'));
        }
        Date date = new Date();
        String param = "( '" + fileName + "' , '" + fileType + "' , '" + formatter.format(fileSize) + "' , "
                + "'" + uploadedBy + "' , '" + moduleName + "' , '" + new Timestamp(date.getTime()) + "' , " + projectionId + ")";
        for (int i = 1; i < fileNames.size(); i++) {
            fileName = fileNames.get(i).getDocumentName();
            fileType = StringUtils.EMPTY;
            if (fileName.indexOf('.') == -1) {
                fileName = fileName + ".";
            } else {
                fileType = fileName.substring(fileName.lastIndexOf('.') + 1);
                fileName = fileName.substring(0, fileName.indexOf('.'));
            }
            param = param + " , " + "( '" + fileName + "' , '" + fileType + "' , '" + formatter.format(fileSize) + "' , "
                    + "'" + uploadedBy + "' , '" + moduleName + "' , '" + new Timestamp(date.getTime()) + "' , " + projectionId + ")";
        }
        insertQuery = (insertQuery.replace("@Values", param)) + ";";
        HelperTableLocalServiceUtil.executeUpdateQuery(deleteQuery + "  " + insertQuery);
        LOGGER.debug("End of saveUploadedFile method");
    }

    /**
     * Method is to save the adjustment selection in all the modules need to use
     * module based adjustment selection DTO to get selection values and call
     * this method
     *
     * @param projectionId
     * @param moduleName
     * @param values
     * @return
     */
    private static boolean saveAdjustmentSelection(int projectionId, String moduleName, Map<String, String> values) {
        boolean saveSuccess = true;
        try {
            String query = QueryUtils.build_adjustment_selection_save_query(projectionId, moduleName, values);
            DAO.executeUpdate(query);
        } catch (Exception e) {
            LOGGER.error(e);
            saveSuccess = false;
        }

        return saveSuccess;

    }

    /**
     * Method is to delete the adjustment selection
     *
     * @param projectionId
     * @return
     */
    private static boolean deleteAdjustmentSelection(int projectionId) {
        boolean deleteSuccess = true;
        try {
            String query = QueryUtils.build_adjustment_selection_delete_query(projectionId);
            DAO.executeUpdate(query);
        } catch (Exception e) {
            LOGGER.error(e);
            deleteSuccess = false;
        }

        return deleteSuccess;

    }

    public static boolean savePipeAccrualSelection(int projectionId, String moduleName, AbstractSelectionDTO dto) {
        StringBuilder builder = new StringBuilder();
        String var = null;
        Map<String, String> pipeLineMap = new HashMap<String, String>();
//-----------------------SALES--------------------------------------------------
        if (dto.getSales_variables() != null && !dto.getSales_variables().isEmpty()) {
            for (String s[] : dto.getSales_variables()) {
                if (builder.length() == 0) {
                    builder.append(s[0]);
                } else {
                    builder.append(",").append(s[0]);
                }
            }
            var = builder.toString();

            pipeLineMap.put(VariableConstants.SALES_VARIABLE, var);
            builder = new StringBuilder();
        }
        if (dto.getDateType() != null) {
            pipeLineMap.put(VariableConstants.DATE_TYPE, dto.getDateType());
        }
        if (!"null".equals(dto.getPrice()) || !StringUtils.isBlank(dto.getPrice())) {
            pipeLineMap.put(VariableConstants.PRICE, dto.getPrice());
        }
//-----------------------------------RATES--------------------------------------
        if (dto.getRate_Basis() != 0) {
            pipeLineMap.put(VariableConstants.RATE_BASIS, String.valueOf(dto.getRate_Basis()));
        }
        if (dto.getRate_Frequency() != 0) {
            pipeLineMap.put(VariableConstants.RATE_FREQUENCY, String.valueOf(dto.getRate_Frequency()));
        }
        if (dto.getRate_DeductionLevel() != 0) {
            pipeLineMap.put(VariableConstants.RATE_DEDUCTION_LEVEL, String.valueOf(dto.getRate_DeductionLevel()));
        }
        if (!"null".equals(dto.getRate_Period()) || !StringUtils.isBlank(dto.getRate_Period())) {
            pipeLineMap.put(VariableConstants.RATE_PERIOD, String.valueOf(dto.getRate_Period()));
        }
        if (!"null".equals(dto.getRate_DeductionValue()) || !StringUtils.isBlank(dto.getRate_DeductionValue())) {
            pipeLineMap.put(VariableConstants.RATE_DEDUCTION_VALUE, String.valueOf(dto.getRate_DeductionValue().replace("'", "")));
        }
//----------------------------------ADJUSTMENT SUMMARY--------------------------
        if (dto.getSummary_deductionLevel() != 0) {
            pipeLineMap.put(VariableConstants.SUMMARY_DEDUCTION_LEVEL, String.valueOf(dto.getSummary_deductionLevel()));
        }
        if (!"null".equals(dto.getSummary_deductionValues()) || !StringUtils.isBlank(dto.getSummary_deductionValues())) {
            pipeLineMap.put(VariableConstants.SUMMARY_DEDUCTION_VALUE, String.valueOf(dto.getSummary_deductionValues().replace("'", "")));
        }

        if (!"null".equals(dto.getSummary_glDate()) || !StringUtils.isBlank(dto.getSummary_glDate())) {
            pipeLineMap.put(VariableConstants.SUMMARY_GL_DATE, String.valueOf(dto.getSummary_glDate()));
        }

        if (dto.getSummary_variables() != null && !dto.getSummary_variables().isEmpty()) {
            for (String s[] : dto.getSummary_variables()) {
                if (builder.length() == 0) {
                    builder.append(s[0]);
                } else {
                    builder.append(",").append(s[0]);
                }
            }
            var = builder.toString();
            pipeLineMap.put(VariableConstants.SUMMARY_VARIABLES, var);
            builder = new StringBuilder();
        }

//--------------------------------ADJUSTMENT DETAILS----------------------------
        if (!"null".equals(dto.getDetail_Level()) || !StringUtils.isBlank(dto.getDetail_Level())) {
            pipeLineMap.put(VariableConstants.DETAIL_LEVEL, String.valueOf(dto.getDetail_Level()));
        }
        if (dto.getSave_detail_variables() != null && !dto.getSave_detail_variables().isEmpty()) {
            for (String s : dto.getSave_detail_variables()) {
                if (builder.length() == 0) {
                    builder.append(s);
                } else {
                    builder.append(",").append(s);
                }
            }
            var = builder.toString();

            pipeLineMap.put(VariableConstants.DETAIL_VARIABLE, var);
            builder = new StringBuilder();
        }

        if (dto.getDetail_reserveAcount() != null && !dto.getDetail_reserveAcount().isEmpty()) {
            for (String s : dto.getDetail_reserveAcount()) {
                if (builder.length() == 0) {
                    builder.append(s);
                } else {
                    builder.append(",").append(s);
                }
            }
            var = builder.toString();

            pipeLineMap.put(VariableConstants.DETAIL_RESERVER_ACCOUNT, var);
            builder = new StringBuilder();
        }

        if (dto.getDetail_amountFilter() != null && !dto.getDetail_amountFilter().isEmpty()) {
            for (String s : dto.getDetail_amountFilter()) {
                if (builder.length() == 0) {
                    builder.append(s);
                } else {
                    builder.append(",").append(s);
                }
            }
            var = builder.toString();

            pipeLineMap.put(VariableConstants.DETAIL_AMOUNT_FILTER, var);
            builder = new StringBuilder();
        }

        deleteAdjustmentSelection(projectionId);
        boolean saveSuccess = saveAdjustmentSelection(projectionId, moduleName, pipeLineMap);

        return saveSuccess;
    }

    public static void getDataSelectionForWorkFlow(DataSelectionDTO result) throws ParseException {
        String query = SQlUtil.getQuery("fetchDataSelectionForWorkflow");
        query = query.replace("?", result.getProjectionId() + "");
        List<Object[]> list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        Object[] obj = list.get(0);
        result.setProjectionDescription(CommonLogic.convertNullToEmpty(String.valueOf(obj[0])));
        result.setCompanyMasterSid((int) obj[NumericConstants.TWO]);
        result.setBu_companyMasterSid((int) obj[NumericConstants.THREE]);
        result.setDeductionLevel((int) obj[NumericConstants.FOUR]);
        result.setCustomerHierarchySid((int) obj[NumericConstants.FIVE]);
        result.setCustomerHierarchyName(CommonLogic.convertNullToEmpty(String.valueOf(obj[NumericConstants.SIX])));
        result.setCustRelationshipBuilderSid((int) obj[NumericConstants.SEVEN]);
        result.setCustomerHierarchyLevel(CommonLogic.convertNullToEmpty(String.valueOf(obj[NumericConstants.EIGHT])));
        result.setProductHierarchySid((int) obj[NumericConstants.NINE]);
        result.setProductHierarchyName(CommonLogic.convertNullToEmpty(String.valueOf(obj[NumericConstants.TEN])));
        result.setProdRelationshipBuilderSid((int) obj[NumericConstants.ELEVEN]);
        result.setProductHierarchyLevel(CommonLogic.convertNullToEmpty(String.valueOf(obj[NumericConstants.TWELVE])));
        result.setFromPeriod(String.valueOf(obj[NumericConstants.THIRTEEN]));
        result.setToPeriod(String.valueOf(obj[NumericConstants.FOURTEEN]));
        //Dont Delete
        result.setFromDate(CommonLogic.parseDate(CommonLogic.convertNullToEmpty(String.valueOf(obj[NumericConstants.THIRTEEN]))));
        result.setToDate(CommonLogic.parseDate(CommonLogic.convertNullToEmpty(String.valueOf(obj[NumericConstants.FOURTEEN]))));
    }

    public static List<Object[]> loadPipelineAccrual(int projectionId) {
        String query = SQlUtil.getQuery("getPipelineSelection");
        query = query.replace("@projectionId", projectionId + "");
        List<Object[]> list = QueryUtils.executeSelect(query);
        return list;
    }

    public static List<Object[]> loadCustomerOptionGroup(int projectionId) {
        String query = SQlUtil.getQuery("getCustomerOptionGroup");
        query = query.replace("@projectionId", projectionId + "");
        List<Object[]> list = QueryUtils.executeSelect(query);
        return list;
    }

    public static void saveToTemp(SessionDTO sessionDTO, String adjType) {
        String query = SQlUtil.getQuery(adjType + "_Wf_Edit_Query");
        query = query.replaceAll("@PROJECTION_MASTER_SID", sessionDTO.getProjectionId() + "");
        query = replaceTableNames(query, sessionDTO.getCurrentTableNames());
        ITEMDAO.executeUpdate(query);
    }

    public static void saveTempToMain(int projId, Map<String, String> currentTableNames, String adjType) {
        adjType = adjType.trim().replace(" ", "_");
        String query = SQlUtil.getQuery(adjType + "_Wf_Save_Query");
        query = query.replaceAll("@PROJECTION_MASTER_SID", projId + "");
        query = QueryUtil.replaceTableNames(query, currentTableNames);
        ITEMDAO.executeUpdate(query);
    }

    public static List<NotesDTO> retrieveNotesInfo(SessionDTO selection) {
        List<NotesDTO> returnlist = new ArrayList<>();
        String sql = SQlUtil.getQuery("retrieveNotesInfo");
        sql = sql.replace("@PROJECTION_ID", selection.getProjectionId() + "");
        List<Object[]> list = QueryUtils.executeSelect(sql);
        for (Object[] obj : list) {
            NotesDTO dto = new NotesDTO();
            dto.setNotesHistory(String.valueOf(obj[0]));
            dto.setDocumentName(obj[1] + "." + obj[NumericConstants.TWO]);
            dto.setFileType(String.valueOf(obj[NumericConstants.TWO]));
            dto.setUserName(String.valueOf(obj[NumericConstants.THREE]));
            dto.setDateAdded(String.valueOf(obj[NumericConstants.FOUR]));
            dto.setReasonCode(String.valueOf(obj[NumericConstants.FIVE]));
            returnlist.add(dto);
        }
        return returnlist;
    }

    public static void saveDemandPaymentsReforcastSelection(int projectionId, String adjustmentType, AbstractSelectionDTO dto) {
        StringBuilder builder = new StringBuilder();
        String var = null;
        Map<String, String> pipeLineMap = new HashMap<String, String>();
        //-------------Adjustment Summary Variables--------------
        if (dto.getSummary_frequency() != 0) {
            pipeLineMap.put(VariableConstants.SUMMARY_FREQUENCY, String.valueOf(dto.getSummary_frequency()));
        }
        if (dto.getSummary_demand_view() != null && !dto.getSummary_viewType().isEmpty()) {
            pipeLineMap.put(VariableConstants.SUMMARY_VIEW_TYPE, String.valueOf(dto.getSummary_demand_view()));
        }
        if (dto.getSummary_glDate() != null && !dto.getSummary_glDate().isEmpty()) {
            pipeLineMap.put(VariableConstants.SUMMARY_GL_DATE, String.valueOf(dto.getSummary_glDate()));
        }
        if (dto.getSummary_demand_fromDate() != null && !dto.getSummary_demand_fromDate().isEmpty()) {
            pipeLineMap.put(VariableConstants.SUMMARY_FROM_DATE, String.valueOf(dto.getSummary_demand_fromDate()));
        }
        if (dto.getSummary_demand_toDate() != null && !dto.getSummary_demand_toDate().isEmpty()) {
            pipeLineMap.put(VariableConstants.SUMMARY_TO_DATE, String.valueOf(dto.getSummary_demand_toDate()));
        }
        if (dto.getSummary_deductionLevel() != 0) {
            pipeLineMap.put(VariableConstants.SUMMARY_DEDUCTION_LEVEL, String.valueOf(dto.getSummary_deductionLevel()));
        }
        if (!"null".equals(dto.getSummary_deductionValues()) || !StringUtils.isBlank(dto.getSummary_deductionValues())) {
            pipeLineMap.put(VariableConstants.SUMMARY_DEDUCTION_VALUE, String.valueOf(dto.getSummary_deductionValues().replace("'", "")));
        }

        if (dto.getSummary_variables() != null && !dto.getSummary_variables().isEmpty()) {
            for (String s[] : dto.getSummary_variables()) {
                if (builder.length() == 0) {
                    builder.append(s[0]);
                } else {
                    builder.append(",").append(s[0]);
                }
            }
            var = builder.toString();
            pipeLineMap.put(VariableConstants.SUMMARY_VARIABLES, var);
            builder = new StringBuilder();
        }
        //--------------------------------ADJUSTMENT DETAILS----------------------------
        if (!"null".equals(dto.getDetail_Level()) || !StringUtils.isBlank(dto.getDetail_Level())) {
            pipeLineMap.put(VariableConstants.DETAIL_LEVEL, String.valueOf(dto.getDetail_Level()));
        }
        if (dto.getSave_detail_variables() != null && !dto.getSave_detail_variables().isEmpty()) {
            for (String s : dto.getSave_detail_variables()) {
                if (builder.length() == 0) {
                    builder.append(s);
                } else {
                    builder.append(",").append(s);
                }
            }
            var = builder.toString();

            pipeLineMap.put(VariableConstants.DETAIL_VARIABLE, var);
            builder = new StringBuilder();
        }

        if (dto.getDetail_reserveAcount() != null && !dto.getDetail_reserveAcount().isEmpty()) {
            for (String s : dto.getDetail_reserveAcount()) {
                if (builder.length() == 0) {
                    builder.append(s);
                } else {
                    builder.append(",").append(s);
                }
            }
            var = builder.toString();

            pipeLineMap.put(VariableConstants.DETAIL_RESERVER_ACCOUNT, var);
            builder = new StringBuilder();
        }
        if (dto.getDetail_amountFilter() != null && !dto.getDetail_amountFilter().isEmpty()) {
            for (String s : dto.getDetail_amountFilter()) {
                if (builder.length() == 0) {
                    builder.append(s);
                } else {
                    builder.append(",").append(s);
                }
            }
            var = builder.toString();

            pipeLineMap.put(VariableConstants.DETAIL_AMOUNT_FILTER, var);
            builder = new StringBuilder();
        }
        deleteAdjustmentSelection(projectionId);
        saveAdjustmentSelection(projectionId, adjustmentType, pipeLineMap);
    }

    public static boolean savePipelineInventorySelection(int projectionId, String moduleName, PipelineInventorySelectionDTO dto) {
        StringBuilder builder = new StringBuilder();
        String var = null;
        Map<String, String> pipeLineMap = new HashMap<String, String>();
//-----------------------INVENTORY--------------------------------------------------
        if (dto.getInventory_Details() != null) {
            pipeLineMap.put(VariableConstants.INVENTORY_DETAIL, String.valueOf(dto.getInventory_Details()));
        }
        if (!"null".equals(dto.getInventoryOptionGroup()) || !StringUtils.isBlank(dto.getInventoryOptionGroup())) {
            pipeLineMap.put(VariableConstants.INVENTORY_OPTION_GROUP, String.valueOf(dto.getInventoryOptionGroup()));
        }
        if (!"null".equals(dto.getPrice()) || !StringUtils.isBlank(dto.getPrice())) {
            pipeLineMap.put(VariableConstants.PRICE, dto.getPrice());
        }
        if (dto.getInventory_reserveDate() != null) {
            pipeLineMap.put(VariableConstants.INVENTORY_RESERVE_DATE, String.valueOf(dto.getInventory_reserveDate()));
        }
        if (dto.getSales_variables() != null && !dto.getSales_variables().isEmpty()) {
            for (String s[] : dto.getSales_variables()) {
                if (builder.length() == 0) {
                    builder.append(s[0]);
                } else {
                    builder.append(",").append(s[0]);
                }
            }
            var = builder.toString();
            pipeLineMap.put(VariableConstants.SALES_VARIABLE, var);
            builder = new StringBuilder();
        }
//-----------------------------------RATES--------------------------------------
        if (dto.getRate_Basis() != 0) {
            pipeLineMap.put(VariableConstants.RATE_BASIS, String.valueOf(dto.getRate_Basis()));
        }
        if (dto.getRate_Frequency() != 0) {
            pipeLineMap.put(VariableConstants.RATE_FREQUENCY, String.valueOf(dto.getRate_Frequency()));
        }
        if (dto.getRate_DeductionLevel() != 0) {
            pipeLineMap.put(VariableConstants.RATE_DEDUCTION_LEVEL, String.valueOf(dto.getRate_DeductionLevel()));
        }
        if (!"null".equals(dto.getRate_Period()) || !StringUtils.isBlank(dto.getRate_Period())) {
            pipeLineMap.put(VariableConstants.RATE_PERIOD, String.valueOf(dto.getRate_Period()));
        }
        if (!"null".equals(dto.getRate_DeductionValue()) || !StringUtils.isBlank(dto.getRate_DeductionValue())) {
            pipeLineMap.put(VariableConstants.RATE_DEDUCTION_VALUE, String.valueOf(dto.getRate_DeductionValue().replace("'", "")));
        }
//----------------------------------ADJUSTMENT SUMMARY--------------------------
        if (dto.getSummary_deductionLevel() != 0) {
            pipeLineMap.put(VariableConstants.SUMMARY_DEDUCTION_LEVEL, String.valueOf(dto.getSummary_deductionLevel()));
        }
        if (!"null".equals(dto.getSummary_deductionValues()) && !StringUtils.isBlank(dto.getSummary_deductionValues()) && dto.getSummary_deductionValues() != null) {
            pipeLineMap.put(VariableConstants.SUMMARY_DEDUCTION_VALUE, String.valueOf(dto.getSummary_deductionValues().replace("'", "")));
        }

        if (dto.getSummary_variables() != null && !dto.getSummary_variables().isEmpty()) {
            for (String s[] : dto.getSummary_variables()) {
                if (builder.length() == 0) {
                    builder.append(s[0]);
                } else {
                    builder.append(",").append(s[0]);
                }
            }
            var = builder.toString();
            pipeLineMap.put(VariableConstants.SUMMARY_VARIABLES, var);
            builder = new StringBuilder();
        }

        if (dto.getSummary_glDate() != null && !dto.getSummary_glDate().isEmpty()) {
            pipeLineMap.put(VariableConstants.SUMMARY_GL_DATE, String.valueOf(dto.getSummary_glDate()));
        }

//--------------------------------ADJUSTMENT DETAILS----------------------------
        if (!"null".equals(dto.getDetail_Level()) || !StringUtils.isBlank(dto.getDetail_Level())) {
            pipeLineMap.put(VariableConstants.DETAIL_LEVEL, String.valueOf(dto.getDetail_Level()));
        }
        if (dto.getSave_detail_variables() != null && !dto.getSave_detail_variables().isEmpty()) {
            for (String s : dto.getSave_detail_variables()) {
                if (builder.length() == 0) {
                    builder.append(s);
                } else {
                    builder.append(",").append(s);
                }
            }
            var = builder.toString();

            pipeLineMap.put(VariableConstants.DETAIL_VARIABLE, var);
            builder = new StringBuilder();
        }

        if (dto.getDetail_reserveAcount() != null && !dto.getDetail_reserveAcount().isEmpty()) {
            for (String s : dto.getDetail_reserveAcount()) {
                if (builder.length() == 0) {
                    builder.append(s);
                } else {
                    builder.append(",").append(s);
                }
            }
            var = builder.toString();

            pipeLineMap.put(VariableConstants.DETAIL_RESERVER_ACCOUNT, var);
            builder = new StringBuilder();
        }
        if (dto.getDetail_amountFilter() != null && !dto.getDetail_amountFilter().isEmpty()) {
            for (String s : dto.getDetail_amountFilter()) {
                if (builder.length() == 0) {
                    builder.append(s);
                } else {
                    builder.append(",").append(s);
                }
            }
            var = builder.toString();

            pipeLineMap.put(VariableConstants.DETAIL_AMOUNT_FILTER, var);
            builder = new StringBuilder();
        }

        deleteAdjustmentSelection(projectionId);
        boolean saveSuccess = saveAdjustmentSelection(projectionId, moduleName, pipeLineMap);

        return saveSuccess;
    }

    /**
     * Used to load the combo box with respect to the query Name
     *
     * @param comboBox
     * @param queryName - Name of the query to get from resources
     * @param input - Input to replace the '?' with values
     *
     * @return combo box loaded with values as id and caption
     */
    public static void setComboBoxItemIDAndCaption(ComboBox comboBox, String queryName, final List input) {
        comboBox.setValidationVisible(true);
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(true);
        comboBox.setNullSelectionItemId(0);
        comboBox.addItem(0);
        comboBox.setItemCaption(0, GlobalConstants.getSelectOne());
        List<Object[]> list = QueryUtils.getItemData(input, queryName, null);
        comboBox.select(null);
        comboBox.setImmediate(true);
        for (int i = 0; i < list.size(); i++) {
            Object[] helper = (Object[]) list.get(i);
            HelperDTO dto = new HelperDTO();
            dto.setId(Integer.valueOf(helper[0].toString()));
            dto.setDescription(helper[1].toString());
            comboBox.addItem(dto);
            comboBox.setItemCaption(dto, String.valueOf(helper[NumericConstants.TWO]));
        }
    }

    /**
     * This method is to load transaction name of adjustment config value in
     * helper list map
     */
    public static void loadTransactionName() {
        List<Object[]> list = QueryUtils.getItemData(Collections.EMPTY_LIST, "Load_Transaction_names", null);
        List helperList = new ArrayList();
        for (Object[] str : list) {
            HelperDTO dto = new HelperDTO();
            dto.setId(str[0] == null ? 0 : Integer.valueOf(str[0].toString()));
            dto.setDescription(str[1] == null ? StringUtils.EMPTY : str[1].toString());
            helperList.add(dto);
        }
        HelperListUtil.getInstance().getListNameMap().put("ARM_ADJUSTMENT_TYPE", helperList);
    }

    public static void loadComboBox(ComboBox comboBox, String listName) {
        String query = "SELECT * FROM dbo.HELPER_TABLE where LIST_NAME ='" + listName + "';";
        List<Object[]> list = (List<Object[]>) QueryUtils.executeSelect(query);
        comboBox.setImmediate(true);
        for (int i = 0; i < list.size(); i++) {
            Object[] helper = (Object[]) list.get(i);
            HelperDTO dto = new HelperDTO();
            dto.setId(Integer.valueOf(helper[0].toString()));
            dto.setDescription(helper[1].toString());
            comboBox.addItem(dto);
            comboBox.setItemCaption(dto, String.valueOf(helper[1]));
        }
    }

    public static void loadComboBoxNonHelperDto(ComboBox comboBox, String listName) {
        String query = "SELECT * FROM dbo.HELPER_TABLE where LIST_NAME ='" + listName + "';";
        List<Object[]> list = (List<Object[]>) QueryUtils.executeSelect(query);
        comboBox.setImmediate(true);
        for (int i = 0; i < list.size(); i++) {
            Object[] helper = (Object[]) list.get(i);
            comboBox.addItem(String.valueOf(helper[0].toString()));
            comboBox.setItemCaption(String.valueOf(helper[0].toString()), helper[1].toString());
        }
    }

    /**
     * Gets the customized results.
     *
     * @param resultList the result list
     * @return the customized results
     */
    public static List<Date> getFromAndTo(List rset) {
        final List<Date> forecastList = new ArrayList<>();
        try {
            Date currentDate = new Date();
            Date fromDate = new Date();
            Date fromDefDate = new Date();
            Date toDate = new Date();
            Date toDefDate = new Date();

            if (rset != null && rset.size() > 0) {
                Object[] obj = (Object[]) rset.get(0);

                String periodViewName = String.valueOf(obj[0]);
                String fromModeName = String.valueOf(obj[1]);
                if (fromModeName.equals("Auto")) {

                    fromDate = getDate(Integer.parseInt(convertNullToEmpty(obj[NumericConstants.THREE])), true);

                } else {
                    fromDate = parsetDate(convertNullToEmpty(obj[NumericConstants.FOUR]));

                }

                String fromDefModeName = convertNullToEmpty(obj[NumericConstants.FIVE]);
                if (fromDefModeName.equals("Auto")) {
                    fromDefDate = getDate(Integer.parseInt(convertNullToEmpty(obj[NumericConstants.SEVEN])), true);
                } else {
                    fromDefDate = parsetDate(convertNullToEmpty(obj[NumericConstants.EIGHT]));
                }

                if (periodViewName.equals("Multiple")) {
                    String toModeName = convertNullToEmpty(obj[NumericConstants.NINE]);

                    if (toModeName.equals("Auto")) {
                        toDate = getDate(Integer.parseInt(convertNullToEmpty(obj[NumericConstants.ELEVEN])), false);
                    } else {
                        toDate = parsetDate(convertNullToEmpty(obj[NumericConstants.TWELVE]));

                    }

                    String toDefModeName = convertNullToEmpty(obj[NumericConstants.THIRTEEN]);

                    if (toDefModeName.equals("Auto")) {
                        toDefDate = getDate(Integer.parseInt(convertNullToEmpty(obj[NumericConstants.FIFTEEN])), false);
                    } else {
                        toDefDate = parsetDate(convertNullToEmpty(obj[NumericConstants.SIXTEEN]));
                    }
                }

                if (toDate.before(currentDate)) {

                    toDate = currentDate;
                }
                if (fromDefDate.before(fromDate)) {
                    fromDefDate = fromDate;
                }

                if (toDate.before(toDefDate)) {
                    toDefDate = toDate;
                }

            }

            forecastList.add(fromDate);
            forecastList.add(fromDefDate);
            forecastList.add(toDate);
            forecastList.add(toDefDate);

        } catch (Exception e) {
            LOGGER.error(e);
        }

        return forecastList;
    }

    private static Date parsetDate(String value) throws java.text.ParseException {
        Date date = null;
        String tempDate;
        SimpleDateFormat parse = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        if (value != null && !"".equals(value) && !"null".equals(value)) {
            tempDate = format.format(parse.parse(value));
            date = format.parse(tempDate);
        }

        return date;
    }

    private static Date getDate(int inputPeriod, boolean isFrom) {
        int months = inputPeriod % NumericConstants.TWELVE;
        int year = inputPeriod / NumericConstants.TWELVE;
        Date date = new Date();
        date.setYear(date.getYear() + year);

        date.setMonth(date.getMonth() + months);
        if (isFrom) {
            date.setDate(01);
        } else {
            date.setMonth(date.getMonth() + 1);
            date.setDate(01);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.add(Calendar.DATE, -1);
            date = calendar.getTime();
        }

        return date;
    }

    public static List<Date> getPeriodList(String moduleName, int buscinessProcess, int companyId, int buCompanySid) {
        List list = Collections.EMPTY_LIST;
        String baseQuery = SQlUtil.getQuery("periodLoadQuery");
        String sql = baseQuery + " WHERE MODULES.DESCRIPTION='" + moduleName + "' AND PCM.BUSINESS_PROCESS_TYPE='" + buscinessProcess + "' AND PCM.COMPANY_MASTER_SID='" + companyId + "' AND PCM.BU_COMPANY_MASTER_SID='" + buCompanySid + "'\n"
                + "ORDER BY VERSION_NO DESC";
        list = (List<Object[]>) QueryUtils.executeSelect(sql.toString());

        return getFromAndTo(list);
    }

    public static void loadDebitAndCrditName() {
        Map helperUtilMap = HelperListUtil.getInstance().getListNameMap();
        List helperList = new ArrayList();
        HelperDTO positive = new HelperDTO();
        HelperDTO negative = new HelperDTO();
        positive.setId(-1);
        positive.setDescription(ARMUtils.POSITIVE);
        negative.setId(1);
        negative.setDescription(ARMUtils.NEGATIVE);
        helperList.add(positive);
        helperList.add(negative);
        helperUtilMap.put("CRDIT_DEBIT_INDICATOR", helperList);
    }

    /**
     * Method to save the selections in Transaction 6
     *
     * @param projectionId
     * @param moduleName
     * @param dto
     * @return
     */
    public static boolean saveInflationAdjustmentSelection(int projectionId, String moduleName, Trx6_SelectionDTO dto) {
        StringBuilder builder = new StringBuilder();
        String var = null;
        Map<String, String> inflationAdjMap = new HashMap<String, String>();
        //-----------------------INVENTORY--------------------------------------------------
        if (dto.getInventory_Details() != null) {
            inflationAdjMap.put(VariableConstants.INVENTORY_DETAILS_DDLB, String.valueOf(dto.getInventory_Details()));
        }
        if (!"null".equals(dto.getPrice()) || !StringUtils.isBlank(dto.getPrice())) {
            inflationAdjMap.put(VariableConstants.TRX6_BASELINE_PRICE, dto.getPrice());
        }
        if (dto.getAdjustedPrice() != null) {
            inflationAdjMap.put(VariableConstants.ADJUSTED_PRICE, dto.getAdjustedPrice());
        }
        if (dto.getSales_variables() != null && !dto.getSales_variables().isEmpty()) {
            for (String s[] : dto.getSales_variables()) {
                if (builder.length() == 0) {
                    builder.append(s[0]);
                } else {
                    builder.append(",").append(s[0]);
                }
            }
            var = builder.toString();
            inflationAdjMap.put(VariableConstants.SALES_VARIABLE, var);
            builder = new StringBuilder();
        }

        //----------------------------------ADJUSTMENT SUMMARY--------------------------
        if (dto.getSummary_deductionLevel() != 0) {
            inflationAdjMap.put(VariableConstants.SUMMARY_DEDUCTION_LEVEL, String.valueOf(dto.getSummary_deductionLevel()));
        }
        if (!"null".equals(dto.getSummary_deductionValues()) && !StringUtils.isBlank(dto.getSummary_deductionValues()) && dto.getSummary_deductionValues() != null) {
            inflationAdjMap.put(VariableConstants.SUMMARY_DEDUCTION_VALUE, String.valueOf(dto.getSummary_deductionValues().replace("'", "")));
        }

        if (!"null".equals(dto.getSummary_glDate()) || !StringUtils.isBlank(dto.getSummary_glDate())) {
            inflationAdjMap.put(VariableConstants.SUMMARY_GL_DATE, String.valueOf(dto.getSummary_glDate()));
        }

        if (dto.getSummary_variables() != null && !dto.getSummary_variables().isEmpty()) {
            for (String s[] : dto.getSummary_variables()) {
                if (builder.length() == 0) {
                    builder.append(s[0]);
                } else {
                    builder.append(",").append(s[0]);
                }
            }
            var = builder.toString();
            inflationAdjMap.put(VariableConstants.SUMMARY_VARIABLES, var);
            builder = new StringBuilder();
        }

        //--------------------------------ADJUSTMENT DETAILS----------------------------
        if (!"null".equals(dto.getDetail_Level()) || !StringUtils.isBlank(dto.getDetail_Level())) {
            inflationAdjMap.put(VariableConstants.DETAIL_LEVEL, String.valueOf(dto.getDetail_Level()));
        }
        if (dto.getSave_detail_variables() != null && !dto.getSave_detail_variables().isEmpty()) {
            for (String s : dto.getSave_detail_variables()) {
                if (builder.length() == 0) {
                    builder.append(s);
                } else {
                    builder.append(",").append(s);
                }
            }
            var = builder.toString();

            inflationAdjMap.put(VariableConstants.DETAIL_VARIABLE, var);
            builder = new StringBuilder();
        }

        if (dto.getDetail_reserveAcount() != null && !dto.getDetail_reserveAcount().isEmpty()) {
            for (String s : dto.getDetail_reserveAcount()) {
                if (builder.length() == 0) {
                    builder.append(s);
                } else {
                    builder.append(",").append(s);
                }
            }
            var = builder.toString();

            inflationAdjMap.put(VariableConstants.DETAIL_RESERVER_ACCOUNT, var);
            builder = new StringBuilder();
        }
        if (dto.getDetail_amountFilter() != null && !dto.getDetail_amountFilter().isEmpty()) {
            for (String s : dto.getDetail_amountFilter()) {
                if (builder.length() == 0) {
                    builder.append(s);
                } else {
                    builder.append(",").append(s);
                }
            }
            var = builder.toString();

            inflationAdjMap.put(VariableConstants.DETAIL_AMOUNT_FILTER, var);
            builder = new StringBuilder();
        }

        deleteAdjustmentSelection(projectionId);
        boolean saveSuccess = saveAdjustmentSelection(projectionId, moduleName, inflationAdjMap);

        return saveSuccess;
    }

    /**
     * To drop the temp tables created in dynamically for the given user id and
     * session id. Query is placed in the Other
     * sources->src/main/resources->sqlresources->deleteTempQueries.xml
     *
     * @param userId
     * @param sessionId
     */
    public static void dropDynamicTables(String userId, String sessionId) {
        Map<String, String> inputs = new HashMap<>();
        inputs.put("@USER_ID", userId);
        inputs.put("@SESSION_ID", sessionId);
        QueryUtils.updateDataFromMap(inputs, "Drop.DynamicTempTables");
    }

    /**
     * Used to replace all the tableName with dynamic table
     *
     * @param query -- Query where we need to replace
     * @param tableNameMap -- to get the table name as key and dynmaic table
     * name as value
     * @return replaced query with dynamic tableName
     */
    public static String replaceTableNames(String query, final Map<String, String> tableNameMap) {
        for (Map.Entry<String, String> entry : tableNameMap.entrySet()) {
            query = query.replaceAll("(?i:\\b" + entry.getKey() + "\\b)", entry.getValue());
        }
        return query;
    }

    public static Boolean isButtonVisibleAccess(String id, Map<String, AppPermission> functionHM) {
        if (functionHM.get(id) != null && !((AppPermission) functionHM.get(id)).isFunctionFlag()) {
            return false;
        }
        return true;
    }
}
