/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.common;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.stpl.app.arm.adjustmentrateconfiguration.AdjustmentRateUI;
import com.stpl.app.arm.balancesummaryreport.BalanceSummaryReportUI;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractBPLogic;
import com.stpl.app.arm.businessprocess.pipelineinventory.dto.PipelineInventorySelectionDTO;
import com.stpl.app.arm.businessprocess.transaction6.dto.Trx6SelectionDTO;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.dao.CommonDao;
import com.stpl.app.arm.dao.impl.CommonImpl;
import com.stpl.app.arm.dataselection.dto.DataSelectionDTO;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.utils.CommonUtils;
import com.stpl.app.utils.VariableConstants;
import com.stpl.app.utils.xmlparser.SQlUtil;
import com.stpl.ifs.ui.NotesDTO;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.QueryUtil;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.ui.UI;
import java.sql.Timestamp;
import java.text.DateFormat;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.alump.beforeunload.BeforeUnload;

/**
 *
 * @author
 */
public class CommonLogic {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonLogic.class);

    private static final CommonDao ITEMDAO = CommonImpl.getInstance();
    private final List<String> variableLists = new ArrayList<>();
    private final List<String> pipelineList = new ArrayList<>();
    private final List<String> totalPipelineList = new ArrayList<>();
    private final List<String> demandList = new ArrayList<>();
    private final List<String> totalDemandList = new ArrayList<>();
    private final List<String> singleLiablityList = new ArrayList<>();
    private final List<String> totalSingleLiablityList = new ArrayList<>();
    private final List<String> returnReserveList = new ArrayList<>();
    private final List<String> totalReturnReserveList = new ArrayList<>();

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
    private static CommonDao dao = CommonImpl.getInstance();
    private final Map<String, List> helperListUtil = new HashMap();
    private static final CustomComparator sorter = new CustomComparator();
    public static final HelperDTO DDLB_DEFAULT_VALUE = new HelperDTO(0, GlobalConstants.getSelectOne());
    public static final HelperDTO DDLB_SHOW_ALL_VALUE = new HelperDTO(0, GlobalConstants.getShowAll());

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
     * For Loading Drop downs based on the SQL Query Results. The
     * companyBusiness Flag is for loading company / Business Drop down in setup
     * and configuration of ARM
     *
     * @param comboBox
     * @param sqlID
     * @param companyBusinessFlag
     */
    public static void configureDropDownsForDeduction(ComboBox comboBox, String sqlID) {
        comboBox.setImmediate(true);
        comboBox.addItem(0);
        comboBox.setItemCaption(0, GlobalConstants.getSelectOne());
        comboBox.setNullSelectionAllowed(false);
        loadReserveRateDropdownsForDeduction(comboBox, sqlID);
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
            LOGGER.info("QUERY----------------{}", sqlQuery);
            List<Object[]> arr = HelperTableLocalServiceUtil.executeSelectQuery(sqlQuery);

            for (Object[] obj : arr) {
                if (obj[1] != null && obj[NumericConstants.TWO] != null) {
                    comboBox.addItem((Integer) obj[0]);
                    comboBox.setItemCaption((Integer) obj[0], obj[1] + " - " + obj[NumericConstants.TWO]);
                }
            }
        } else {
            List<Object> arr = HelperTableLocalServiceUtil.executeSelectQuery(sqlQuery);
            comboBox.addItems(arr);
        }
    }

    public static void loadReserveRateDropdownsForDeduction(ComboBox comboBox, String sqlID) {
        String sqlQuery = SQlUtil.getQuery(sqlID);
        List<Object[]> arr = HelperTableLocalServiceUtil.executeSelectQuery(sqlQuery);

        for (Object[] obj : arr) {
            if (obj[1] != null) {
                comboBox.addItem((Integer) obj[0]);
                comboBox.setItemCaption((Integer) obj[0], (String) obj[1]);
            }
        }

    }

    public ComboBox getComboBoxByListNameSorted(ComboBox comboBox, String listName, Boolean isFilter, Map<Integer, String[]> map) {
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
                    dto.setId(str[0] == null ? 0 : ARMUtils.getIntegerValue(str[0].toString()));
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

    public ComboBox getComboBoxByListNameForTable(ComboBox comboBox, String listName) {
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
                    dto.setId(str[0] == null ? 0 : ARMUtils.getIntegerValue(str[0].toString()));
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

    public ComboBox getComboBoxByQueryName(ComboBox comboBox, String queryName) {
        BeanItemContainer<HelperDTO> container = new BeanItemContainer<>(HelperDTO.class);
        String comboboxName = queryName;
        comboBox.setValidationVisible(true);
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.addItem(0);
        comboBox.setItemCaption(0, GlobalConstants.getSelectOne());
        if (helperListUtil.get(comboboxName) == null) {
            List<Object[]> list = QueryUtils.getItemData(Collections.emptyList(), queryName, null);
            List<HelperDTO> resultList = new ArrayList<>();
            for (Object[] str : list) {
                if (!str[1].equals(String.valueOf(GlobalConstants.getSelectOne()))) {
                    StringBuilder description;
                    HelperDTO dto = new HelperDTO();
                    dto.setId(str[0] == null ? 0 : ARMUtils.getIntegerValue(str[0].toString()));
                    description = new StringBuilder(str[1] == null ? ARMUtils.ZERO_STRING : String.valueOf(str[1]));
                    if (str.length > NumericConstants.TWO) {
                        description.append(" - ");
                        description.append(str[NumericConstants.TWO] == null ? ARMUtils.ZERO_STRING : String.valueOf(str[NumericConstants.TWO]));
                    }
                    dto.setDescription(description.toString());
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

    public ComboBox getComboBoxByQueryNameForARM(ComboBox comboBox, String queryName, Boolean isFilter) {
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
            List<Object[]> list = QueryUtils.getItemData(Collections.emptyList(), queryName, null);
            List<HelperDTO> resultList = new ArrayList<>();
            resultList.add(defaultValue);
            for (Object[] str : list) {
                if (!str[1].equals(String.valueOf(GlobalConstants.getSelectOne()))) {
                    StringBuilder description;
                    HelperDTO dto = new HelperDTO();
                    dto.setId(str[0] == null ? 0 : ARMUtils.getIntegerValue(str[0].toString()));
                    description = new StringBuilder();
                    description.append(str[1] == null ? ARMUtils.ZERO_STRING : String.valueOf(str[1]));
                    description.append(str[NumericConstants.TWO] == null ? ARMUtils.ZERO_STRING : String.valueOf(str[NumericConstants.TWO]));
                    dto.setDescription(description.toString());
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

    public static Date parseDate(String values) throws ParseException {
        Date date = null;
        String value = convertNullToEmpty(values);
        SimpleDateFormat parse = new SimpleDateFormat(ARMUtils.YYYY_MM_DD);
        if (value != null && !StringUtils.EMPTY.equals(value) && !ARMUtils.NULL.equals(value) && !value.equals("0")) {
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
            return (obj == null ? 0 : (Integer) obj);
        }

        return 0;
    }

    public static Boolean getCountValue(List<Object[]> list) {
        if (!list.isEmpty()) {
            Object obj = list.get(0);
            return (obj == null ? null : (Integer) obj == 1);
        }
        return Boolean.FALSE;

    }

    public static Boolean getCountValueForSave(List<Object[]> list) {
        if (!list.isEmpty()) {
            Object obj = list.get(0);
            return (obj == null ? null : ((Integer) obj) > 0);
        }
        return Boolean.FALSE;

    }

    public static Boolean getduplicateCount(List<Object[]> list) {
        if (!list.isEmpty()) {
            Object obj = list.get(0);
            return (obj == null ? null : (Integer) obj > 0);
        }
        return Boolean.FALSE;

    }

    public static int parseStringToInteger(final String value) {
        // max length of integer that can be parsed without error is 9
        return value.equals(StringUtils.EMPTY) ? 0 : Integer.parseInt(value.replaceAll(ARMUtils.REGEX_EXTRACT_DIGITS, StringUtils.EMPTY));
    }

    public static List<String> convertIngeterListToString(List<Integer> integetList) {
        List<String> stringList = new ArrayList<>();

        for (int sid : integetList) {
            stringList.add(String.valueOf(sid));
        }

        return stringList;
    }

    public static String stringListToString(List stringList) {
        StringBuilder builder = new StringBuilder();
        if (stringList != null && !stringList.isEmpty()) {
            for (int loop = 0, limit = stringList.size(); loop < limit; loop++) {
                builder.append(ARMUtils.SINGLE_QUOTES);
                builder.append(stringList.get(loop));
                builder.append(ARMUtils.SINGLE_QUOTES);
                if (loop != (limit - 1)) {
                    builder.append(", ");
                }
            }
        }
        return builder.toString();
    }

    public static String listToString(List stringList) {
        StringBuilder builder = new StringBuilder();
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
        List<String> tempTables = new ArrayList<>();
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
        SimpleDateFormat format = new SimpleDateFormat(ARMUtils.MM_DD_YYYY_HH_MM_SS);
        if (value != null && !StringUtils.EMPTY.equals(value) && !ARMUtils.NULL.equals(value)) {
            date = format.format(parse.parse(value));
        }
        return date;
    }

    public static User getUser(final String userId) {
        User loggedUserDetails = null;

        try {
            loggedUserDetails = UserLocalServiceUtil.getUser(Long.parseLong(userId));
        } catch (PortalException | SystemException noSuchUserException) {
            LOGGER.error("Error in getUser :", noSuchUserException);
            loggedUserDetails = null;
        }

        return loggedUserDetails;
    }

    public static ComboBox loadCompanyAndBusinessUnit(ComboBox comboBox, String queryName) {
        String query = SQlUtil.getQuery(queryName);
        List<Object[]> arr = HelperTableLocalServiceUtil.executeSelectQuery(query);
        for (Object[] obj : arr) {
            if (obj[0] != null && obj[1] != null) {
                comboBox.addItem((Integer) obj[0]);
                comboBox.setItemCaption((Integer) obj[0], obj[1].toString());
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
                if (AdjustmentRateUI.getExcelClose()) { // Fix to avoid blank page issue while excel export
                    AdjustmentRateUI.setExcelClose(false);
                } else {
                    uI.close();
                }
            }
        });
    }

    public static final void beforeUnloadCloseUiBSR(final UI uI) {
        BeforeUnload ob = BeforeUnload.closeBeforeUnload(uI);
        ob.addUnloadListener(new BeforeUnload.UnloadListener() {

            @Override
            public void unload(BeforeUnload.UnloadEvent event) {
                if (BalanceSummaryReportUI.getExcelClose()) { // Fix to avoid blank page issue while excel export
                    BalanceSummaryReportUI.setExcelClose(false);
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
            comboBox.addItem((Integer) obj[0]);
            comboBox.setItemCaption((Integer) obj[0], (String) obj[1]);
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
    public static void saveNotes(final int projectionId, final String createdBy, final List<String> notes, final String moduleName, final String reasonCode) {
        LOGGER.debug("Entering saveNotes method with with projectionId {}", projectionId);
        String baseQuery = SQlUtil.getQuery("insertAdditionalNotes");
        Date date = new Date();
        String param = ARMUtils.OPEN_PARANTHESIS + projectionId + " , '" + moduleName + "' , " + createdBy + " , '" + new Timestamp(date.getTime()) + "' , " + "@NOTES" + " , '" + reasonCode + "')";
        StringBuilder parameters = new StringBuilder();
        parameters.append(ARMUtils.OPEN_PARANTHESIS).append(projectionId).append(" , '").append(moduleName);
        parameters.append(CommonConstant.COMMA).append(createdBy).append(CommonConstant.COMMA).append(new Timestamp(date.getTime()));
        parameters.append(CommonConstant.COMMA).append(notes.get(0)).append("', '").append(reasonCode).append("' )");
        for (int i = 1; i < notes.size(); i++) {
            parameters.append(", ");
            parameters.append((param.replace("@NOTES", ARMUtils.SINGLE_QUOTES + notes.get(i) + ARMUtils.SINGLE_QUOTES)));
        }
        HelperTableLocalServiceUtil.executeUpdateQuery(baseQuery + parameters.toString());
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
    public static Boolean deleteUploadedFile(List<NotesDTO> docDetailsIds) {
        String deleteQuery = SQlUtil.getQuery("removeUploadDocs");
        String param = docDetailsIds.get(0).getDocDetailsId() + "";
        for (int i = 1; i < docDetailsIds.size(); i++) {
            param = ", " + docDetailsIds.get(i).getDocDetailsId();
        }
        deleteQuery = deleteQuery.replace("@PROJECTION_IDS", param);
        CommonImpl.getInstance().executeUpdate(deleteQuery);
        return Boolean.TRUE;
    }

    public static void saveUploadedFile(int projectionId, List<NotesDTO> fileNames, String uploadedBy, int fileSize, String moduleName) {
        final DecimalFormat formatter = new DecimalFormat("#.#");
        String deleteQuery = SQlUtil.getQuery("deleteUploadDocs");
        deleteQuery = deleteQuery.replace("@PROJECTION_IDS", Integer.toString(projectionId) + "");

        String insertQuery = SQlUtil.getQuery("insertUploadDocs");
        StringBuilder fileName = new StringBuilder(fileNames.get(0).getDocumentName());
        String fileType = StringUtils.EMPTY;
        if (fileName.indexOf(ARMUtils.DOT) == -1) {
            fileName.append(ARMUtils.DOT);
        } else {
            fileType = fileName.toString().substring(fileName.lastIndexOf(ARMUtils.DOT) + 1);
            String fileNameStr = fileName.toString().substring(0, fileName.indexOf(ARMUtils.DOT));
            fileName = new StringBuilder();
            fileName.append(fileNameStr);
        }
        Date date = new Date();
        StringBuilder param = new StringBuilder();
        param.append("( '").append(fileName).append(CommonConstant.COMMA).append(fileType).append(CommonConstant.COMMA);
        param.append(formatter.format(fileSize)).append("' , ").append(ARMUtils.SINGLE_QUOTES).append(uploadedBy).append(CommonConstant.COMMA);
        param.append(moduleName).append(CommonConstant.COMMA).append(new Timestamp(date.getTime())).append("' , ").append(projectionId).append(ARMUtils.CLOSE_PARANTHESIS);

        for (int i = 1; i < fileNames.size(); i++) {
            String fileNameStr = fileNames.get(i).getDocumentName();
            fileName = new StringBuilder();
            fileName.append(fileNameStr);
            fileType = StringUtils.EMPTY;
            if (fileName.indexOf(ARMUtils.DOT) == -1) {
                fileName.append(ARMUtils.DOT);
            } else {
                fileType = fileName.toString().substring(fileName.lastIndexOf(ARMUtils.DOT) + 1);
                String fileNamestr = fileName.substring(0, fileName.indexOf(ARMUtils.DOT));
                fileName = new StringBuilder();
                fileName.append(fileNamestr);
            }

            param.append(" , ").append("( '").append(fileName.toString()).append(CommonConstant.COMMA);

            param.append(fileType).append(CommonConstant.COMMA).append(formatter.format(fileSize)).append("' , ").append(ARMUtils.SINGLE_QUOTES);
            param.append(uploadedBy).append(CommonConstant.COMMA).append(moduleName).append(CommonConstant.COMMA).append(new Timestamp(date.getTime()));
            param.append("' , ").append(projectionId).append(ARMUtils.CLOSE_PARANTHESIS);

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
    private static boolean saveAdjustmentSelection(int projectionId, String moduleName, Map<String, String> values, List<String> keys) {
        boolean saveSuccess = true;
        try {
            String query = QueryUtils.buildadjustmentselectionsavequery(projectionId, moduleName, values, keys);
            dao.executeUpdate(query);
        } catch (Exception e) {
            LOGGER.error("Error in saveAdjustmentSelection :", e);
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
            String query = QueryUtils.buildadjustmentselectiondeletequery(projectionId);
            dao.executeUpdate(query);
        } catch (Exception e) {
            LOGGER.error("Error in deleteAdjustmentSelection :", e);
            deleteSuccess = false;
        }

        return deleteSuccess;

    }

    public static boolean savePipeAccrualSelection(int projectionId, String moduleName, AbstractSelectionDTO dto) {
        StringBuilder builder = new StringBuilder();
        String var = null;
        Map<String, String> pipeLineAccrualMap = new HashMap<>();
        List<String> pipeLineList = new ArrayList<>();
//-----------------------SALES--------------------------------------------------
        if (dto.getSalesVariables() != null && !dto.getSalesVariables().isEmpty()) {
            for (String[] s : dto.getSalesVariables()) {
                if (builder.length() == 0) {
                    builder.append(s[0]);
                } else {
                    builder.append(ARMUtils.COMMA_CHAR).append(s[0]);
                }
            }
            var = builder.toString();

            pipeLineAccrualMap.put(VariableConstants.SALES_VARIABLE, var);
            pipeLineList.add(VariableConstants.SALES_VARIABLE);
            builder = new StringBuilder();
        }
        if (dto.getDateType() != null) {
            pipeLineAccrualMap.put(VariableConstants.DATE_TYPE, dto.getDateType());
            pipeLineList.add(VariableConstants.DATE_TYPE);
        }
        if (!"null".equals(dto.getPrice()) || !StringUtils.isBlank(dto.getPrice())) {
            pipeLineAccrualMap.put(VariableConstants.PRICE, dto.getPrice());
            pipeLineList.add(VariableConstants.PRICE);
        }
//-----------------------------------RATES--------------------------------------
        if (dto.getRateDeductionLevel() != 0) {
            pipeLineAccrualMap.put(VariableConstants.RATE_DEDUCTION_LEVEL, String.valueOf(dto.getRateDeductionLevel()));
            pipeLineList.add(VariableConstants.RATE_DEDUCTION_LEVEL);
        }
        if (!"null".equals(dto.getRateDeductionValue()) || !StringUtils.isBlank(dto.getRateDeductionValue())) {
            pipeLineAccrualMap.put(VariableConstants.RATE_DEDUCTION_VALUE, String.valueOf(dto.getRateDeductionValue().replace(ARMUtils.SINGLE_QUOTES,ARMUtils.EMPTY_CHARACTER )));
            pipeLineList.add(VariableConstants.RATE_DEDUCTION_VALUE);
        }
        if (dto.getRateBasisValue() != 0) {
            pipeLineAccrualMap.put(VariableConstants.RATE_BASIS, String.valueOf(dto.getRateBasisValue()));
            pipeLineList.add(VariableConstants.RATE_BASIS);
        }
        if (dto.getRateFrequencyValue() != 0) {
            pipeLineAccrualMap.put(VariableConstants.RATE_FREQUENCY, String.valueOf(dto.getRateFrequencyValue()));
            pipeLineList.add(VariableConstants.RATE_FREQUENCY);
        }
        if (!"null".equals(dto.getRatePeriodValue()) || !StringUtils.isBlank(dto.getRatePeriodValue())) {
            pipeLineAccrualMap.put(VariableConstants.RATE_PERIOD, String.valueOf(dto.getRatePeriodValue()));
            pipeLineList.add(VariableConstants.RATE_PERIOD);
        }
        pipeLineAccrualMap.put(VariableConstants.RATES_OVERRIDE_FLAG, String.valueOf(dto.getRatesOverrideFlag()));
        pipeLineList.add(VariableConstants.RATES_OVERRIDE_FLAG);
//----------------------------------ADJUSTMENT SUMMARY--------------------------
        if (dto.getSummarydeductionLevel() != 0) {
            pipeLineAccrualMap.put(VariableConstants.SUMMARY_DEDUCTION_LEVEL, String.valueOf(dto.getSummarydeductionLevel()));
            pipeLineList.add(VariableConstants.SUMMARY_DEDUCTION_LEVEL);
        }
        if (!"null".equals(dto.getSummarydeductionValues()) || !StringUtils.isBlank(dto.getSummarydeductionValues())) {
            pipeLineAccrualMap.put(VariableConstants.SUMMARY_DEDUCTION_VALUE, String.valueOf(dto.getSummarydeductionValues().replace(ARMUtils.SINGLE_QUOTES,ARMUtils.EMPTY_CHARACTER )));
            pipeLineList.add(VariableConstants.SUMMARY_DEDUCTION_VALUE);
        }

        if (dto.getSummaryvariables() != null && !dto.getSummaryvariables().isEmpty()) {
            for (String[] s : dto.getSummaryvariables()) {
                if (builder.length() == 0) {
                    builder.append(s[0]);
                } else {
                    builder.append(ARMUtils.COMMA_CHAR).append(s[0]);
                }
            }
            var = builder.toString();
            pipeLineAccrualMap.put(VariableConstants.SUMMARY_VARIABLES, var);
            pipeLineList.add(VariableConstants.SUMMARY_VARIABLES);
            builder = new StringBuilder();
        }

        if (!"null".equals(dto.getSummaryglDate()) || !StringUtils.isBlank(dto.getSummaryglDate())) {
            pipeLineAccrualMap.put(VariableConstants.SUMMARY_GL_DATE, String.valueOf(dto.getSummaryglDate()));
            pipeLineList.add(VariableConstants.SUMMARY_GL_DATE);

        }
//--------------------------------ADJUSTMENT DETAILS----------------------------
        if (!"null".equals(dto.getDetailLevel()) || !StringUtils.isBlank(dto.getDetailLevel())) {
            pipeLineAccrualMap.put(VariableConstants.DETAIL_LEVEL, String.valueOf(dto.getDetailLevel()));
            pipeLineList.add(VariableConstants.DETAIL_LEVEL);
        }
        if (dto.getSavedetailvariables() != null && !dto.getSavedetailvariables().isEmpty()) {
            for (String s : dto.getSavedetailvariables()) {
                if (builder.length() == 0) {
                    builder.append(s);
                } else {
                    builder.append(ARMUtils.COMMA_CHAR).append(s);
                }
            }
            var = builder.toString();

            pipeLineAccrualMap.put(VariableConstants.DETAIL_VARIABLE, var);
            pipeLineList.add(VariableConstants.DETAIL_VARIABLE);
            builder = new StringBuilder();
        }

        if (dto.getDetailreserveAcount() != null && !dto.getDetailreserveAcount().isEmpty()) {
            for (String s : dto.getDetailreserveAcount()) {
                if (builder.length() == 0) {
                    builder.append(s);
                } else {
                    builder.append(ARMUtils.COMMA_CHAR).append(s);
                }
            }
            var = builder.toString();

            pipeLineAccrualMap.put(VariableConstants.DETAIL_RESERVER_ACCOUNT, var);
            pipeLineList.add(VariableConstants.DETAIL_RESERVER_ACCOUNT);
            builder = new StringBuilder();
        }

        if (dto.getDetailamountFilter() != null && !dto.getDetailamountFilter().isEmpty()) {
            for (String s : dto.getDetailamountFilter()) {
                if (builder.length() == 0) {
                    builder.append(s);
                } else {
                    builder.append(ARMUtils.COMMA_CHAR).append(s);
                }
            }
            var = builder.toString();

            pipeLineAccrualMap.put(VariableConstants.DETAIL_AMOUNT_FILTER, var);
            pipeLineList.add(VariableConstants.DETAIL_AMOUNT_FILTER);
        }

        deleteAdjustmentSelection(projectionId);

        return saveAdjustmentSelection(projectionId, moduleName, pipeLineAccrualMap, pipeLineList);
    }

    public static void getDataSelectionForWorkFlow(DataSelectionDTO result) throws ParseException {
        String query = SQlUtil.getQuery("fetchDataSelectionForWorkflow");
        query = query.replace(ARMUtils.CHAR_QUS, Integer.toString(result.getProjectionId()) + "");
        List<Object[]> list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        Object[] obj = list.get(0);
        result.setProjectionDescription(CommonLogic.convertNullToEmpty(String.valueOf(obj[0])));
        result.setCompanyMasterSid((int) obj[NumericConstants.TWO]);
        result.setBucompanyMasterSid((int) obj[NumericConstants.THREE]);
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
        result.setCustomerHierarchyVersionNo((int) obj[NumericConstants.FIFTEEN]);
        result.setProductHierarchyVersionNo((int) obj[NumericConstants.SIXTEEN]);
    }

    public static List<Object[]> loadPipelineAccrual(int projectionId) {
        String query = SQlUtil.getQuery("getPipelineSelection");
        query = query.replace(VariableConstants.PROJECTION_ID, Integer.toString(projectionId) + "");
        return QueryUtils.executeSelect(query);
    }

    public static List<Object[]> loadReturnReserve(int projectionId, String fieldNames) {
        String query = SQlUtil.getQuery("getReturnReserveSelection");
        query = query.replace(VariableConstants.PROJECTION_ID, Integer.toString(projectionId));
        query = query.replace("@fieldname", fieldNames);
        return QueryUtils.executeSelect(query);
    }

    public static List<Object[]> loadCustomerOptionGroup(int projectionId) {
        String query = SQlUtil.getQuery("getCustomerOptionGroup");
        query = query.replace(VariableConstants.PROJECTION_ID, Integer.toString(projectionId) + "");
        return QueryUtils.executeSelect(query);
    }

    public static void saveToTemp(SessionDTO sessionDTO, String adjType) {
        String adjTypes = adjType.replace('~', ARMUtils.UNDERSCORE);
        String query = SQlUtil.getQuery(adjTypes + "_Wf_Edit_Query");
        query = query.replaceAll("@PROJECTION_MASTER_SID", Integer.toString(sessionDTO.getProjectionId()) + "");
        query = replaceTableNames(query, sessionDTO.getCurrentTableNames());
        ITEMDAO.executeUpdate(query);
    }

    public static void saveTempToMain(int projId, Map<String, String> currentTableNames, String adjType) {
        String adjTypes = adjType.trim().replace(ARMUtils.SPACE.toString(), String.valueOf(ARMUtils.UNDERSCORE));
        String query = SQlUtil.getQuery(adjTypes + "_Wf_Save_Query");
        query = query.replaceAll("@PROJECTION_MASTER_SID", Integer.toString(projId) + "");
        query = QueryUtil.replaceTableNames(query, currentTableNames);
        ITEMDAO.executeUpdate(query);
    }

    public static List<NotesDTO> retrieveNotesInfo(SessionDTO selection) {
        List<NotesDTO> returnlist = new ArrayList<>();
        String sql = SQlUtil.getQuery("retrieveNotesInfo");
        sql = sql.replace("@PROJECTION_ID", Integer.toString(selection.getProjectionId()) + "");
        List<Object[]> list = QueryUtils.executeSelect(sql);
        for (Object[] obj : list) {
            NotesDTO dto = new NotesDTO();
            dto.setNotesHistory(String.valueOf(obj[0]));
            dto.setDocumentName(obj[1] + ARMUtils.DOT + obj[NumericConstants.TWO]);
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
        Map<String, String> demandPaymentsMap = new HashMap<>();
        List<String> pipeLineList = new ArrayList<>();
        //-------------Adjustment Summary Variables--------------
        if (dto.getSummaryfrequency() != 0) {
            demandPaymentsMap.put(VariableConstants.SUMMARY_FREQUENCY, String.valueOf(dto.getSummaryfrequency()));
            pipeLineList.add(VariableConstants.SUMMARY_FREQUENCY);
        }
        if (dto.getSummarydeductionLevel() != 0) {
            demandPaymentsMap.put(VariableConstants.SUMMARY_DEDUCTION_LEVEL, String.valueOf(dto.getSummarydeductionLevel()));
            pipeLineList.add(VariableConstants.SUMMARY_DEDUCTION_LEVEL);
        }
        if (!"null".equals(dto.getSummarydeductionValues()) || !StringUtils.isBlank(dto.getSummarydeductionValues())) {
            demandPaymentsMap.put(VariableConstants.SUMMARY_DEDUCTION_VALUE, String.valueOf(dto.getSummarydeductionValues().replace(ARMUtils.SINGLE_QUOTES,ARMUtils.EMPTY_CHARACTER )));
            pipeLineList.add(VariableConstants.SUMMARY_DEDUCTION_VALUE);
        }
        if (dto.getSummaryvariables() != null && !dto.getSummaryvariables().isEmpty()) {
            for (String[] s : dto.getSummaryvariables()) {
                if (builder.length() == 0) {
                    builder.append(s[0]);
                } else {
                    builder.append(ARMUtils.COMMA_CHAR).append(s[0]);
                }
            }
            var = builder.toString();
            demandPaymentsMap.put(VariableConstants.SUMMARY_VARIABLES, var);
            pipeLineList.add(VariableConstants.SUMMARY_VARIABLES);
            builder = new StringBuilder();
        }
        if (dto.getSummarydemandview() != null && !dto.getSummaryviewType().isEmpty()) {
            demandPaymentsMap.put(VariableConstants.SUMMARY_VIEW_TYPE, String.valueOf(dto.getSummarydemandview()));
            pipeLineList.add(VariableConstants.SUMMARY_VIEW_TYPE);
        }
        if (dto.getSummaryglDate() != null && !dto.getSummaryglDate().isEmpty()) {
            demandPaymentsMap.put(VariableConstants.SUMMARY_GL_DATE, String.valueOf(dto.getSummaryglDate()));
            pipeLineList.add(VariableConstants.SUMMARY_GL_DATE);
        }
        if (dto.getSummarydemandfromDate() != null && !dto.getSummarydemandfromDate().isEmpty()) {
            demandPaymentsMap.put(VariableConstants.SUMMARY_FROM_DATE, String.valueOf(dto.getSummarydemandfromDate()));
            pipeLineList.add(VariableConstants.SUMMARY_FROM_DATE);
        }
        if (dto.getSummarydemandtoDate() != null && !dto.getSummarydemandtoDate().isEmpty()) {
            demandPaymentsMap.put(VariableConstants.SUMMARY_TO_DATE, String.valueOf(dto.getSummarydemandtoDate()));
            pipeLineList.add(VariableConstants.SUMMARY_TO_DATE);
        }
        //--------------------------------ADJUSTMENT DETAILS----------------------------
        if (!"null".equals(dto.getDetailLevel()) || !StringUtils.isBlank(dto.getDetailLevel())) {
            demandPaymentsMap.put(VariableConstants.DETAIL_LEVEL, String.valueOf(dto.getDetailLevel()));
            pipeLineList.add(VariableConstants.DETAIL_LEVEL);
        }
        if (dto.getSavedetailvariables() != null && !dto.getSavedetailvariables().isEmpty()) {
            for (String s : dto.getSavedetailvariables()) {
                if (builder.length() == 0) {
                    builder.append(s);
                } else {
                    builder.append(ARMUtils.COMMA_CHAR).append(s);
                }
            }
            var = builder.toString();
            pipeLineList.add(VariableConstants.DETAIL_VARIABLE);
            demandPaymentsMap.put(VariableConstants.DETAIL_VARIABLE, var);
            builder = new StringBuilder();
        }

        if (dto.getDetailreserveAcount() != null && !dto.getDetailreserveAcount().isEmpty()) {
            for (String s : dto.getDetailreserveAcount()) {
                if (builder.length() == 0) {
                    builder.append(s);
                } else {
                    builder.append(ARMUtils.COMMA_CHAR).append(s);
                }
            }
            var = builder.toString();
            pipeLineList.add(VariableConstants.DETAIL_RESERVER_ACCOUNT);
            demandPaymentsMap.put(VariableConstants.DETAIL_RESERVER_ACCOUNT, var);
            builder = new StringBuilder();
        }
        if (dto.getDetailamountFilter() != null && !dto.getDetailamountFilter().isEmpty()) {
            for (String s : dto.getDetailamountFilter()) {
                if (builder.length() == 0) {
                    builder.append(s);
                } else {
                    builder.append(ARMUtils.COMMA_CHAR).append(s);
                }
            }
            var = builder.toString();
            pipeLineList.add(VariableConstants.DETAIL_AMOUNT_FILTER);
            demandPaymentsMap.put(VariableConstants.DETAIL_AMOUNT_FILTER, var);
        }
        deleteAdjustmentSelection(projectionId);
        saveAdjustmentSelection(projectionId, adjustmentType, demandPaymentsMap, pipeLineList);
    }

    public static boolean savePipelineInventorySelection(int projectionId, String moduleName, PipelineInventorySelectionDTO dto) {
        StringBuilder builder = new StringBuilder();
        String var = null;
        Map<String, String> pipeLineinventoryMap = new HashMap<>();
        List<String> pipeLineList = new ArrayList<>();
//-----------------------INVENTORY--------------------------------------------------
        if (dto.getInventoryDetails() != null) {
            pipeLineinventoryMap.put(VariableConstants.INVENTORY_DETAIL, String.valueOf(dto.getInventoryDetails()));
            pipeLineList.add(VariableConstants.INVENTORY_DETAIL);
        }
        if (!"null".equals(dto.getInventoryOptionGroup()) || !StringUtils.isBlank(dto.getInventoryOptionGroup())) {
            pipeLineinventoryMap.put(VariableConstants.INVENTORY_OPTION_GROUP, String.valueOf(dto.getInventoryOptionGroup()));
            pipeLineList.add(VariableConstants.INVENTORY_OPTION_GROUP);
        }
        if (!"null".equals(dto.getPrice()) || !StringUtils.isBlank(dto.getPrice())) {
            pipeLineinventoryMap.put(VariableConstants.PRICE, dto.getPrice());
            pipeLineList.add(VariableConstants.PRICE);
        }
        if (dto.getInventoryreserveDate() != null) {
            pipeLineinventoryMap.put(VariableConstants.INVENTORY_RESERVE_DATE, String.valueOf(dto.getInventoryreserveDate()));
            pipeLineList.add(VariableConstants.INVENTORY_RESERVE_DATE);
        }
        if (dto.getSalesVariables() != null && !dto.getSalesVariables().isEmpty()) {
            for (String[] s : dto.getSalesVariables()) {
                if (builder.length() == 0) {
                    builder.append(s[0]);
                } else {
                    builder.append(ARMUtils.COMMA_CHAR).append(s[0]);
                }
            }
            var = builder.toString();
            pipeLineinventoryMap.put(VariableConstants.SALES_VARIABLE, var);
            pipeLineList.add(VariableConstants.SALES_VARIABLE);
            builder = new StringBuilder();
        }
//-----------------------------------RATES--------------------------------------
        if (dto.getRateDeductionLevel() != 0) {
            pipeLineinventoryMap.put(VariableConstants.RATE_DEDUCTION_LEVEL, String.valueOf(dto.getRateDeductionLevel()));
            pipeLineList.add(VariableConstants.RATE_DEDUCTION_LEVEL);
        }
        if (!"null".equals(dto.getRateDeductionValue()) || !StringUtils.isBlank(dto.getRateDeductionValue())) {
            pipeLineinventoryMap.put(VariableConstants.RATE_DEDUCTION_VALUE, String.valueOf(dto.getRateDeductionValue().replace(ARMUtils.SINGLE_QUOTES,ARMUtils.EMPTY_CHARACTER )));
            pipeLineList.add(VariableConstants.RATE_DEDUCTION_VALUE);
        }
        if (dto.getRateBasisValue() != 0) {
            pipeLineinventoryMap.put(VariableConstants.RATE_BASIS, String.valueOf(dto.getRateBasisValue()));
            pipeLineList.add(VariableConstants.RATE_BASIS);
        }
        if (dto.getRateFrequencyValue() != 0) {
            pipeLineinventoryMap.put(VariableConstants.RATE_FREQUENCY, String.valueOf(dto.getRateFrequencyValue()));
            pipeLineList.add(VariableConstants.RATE_FREQUENCY);
        }

        if (!"null".equals(dto.getRatePeriodValue()) || !StringUtils.isBlank(dto.getRatePeriodValue())) {
            pipeLineinventoryMap.put(VariableConstants.RATE_PERIOD, String.valueOf(dto.getRatePeriodValue()));
            pipeLineList.add(VariableConstants.RATE_PERIOD);
        }

        pipeLineinventoryMap.put(VariableConstants.RATES_OVERRIDE_FLAG, String.valueOf(dto.getRatesOverrideFlag()));
        pipeLineList.add(VariableConstants.RATES_OVERRIDE_FLAG);
//----------------------------------ADJUSTMENT SUMMARY--------------------------
        if (dto.getSummarydeductionLevel() != 0) {
            pipeLineinventoryMap.put(VariableConstants.SUMMARY_DEDUCTION_LEVEL, String.valueOf(dto.getSummarydeductionLevel()));
            pipeLineList.add(VariableConstants.SUMMARY_DEDUCTION_LEVEL);
        }
        if (!"null".equals(dto.getSummarydeductionValues()) && !StringUtils.isBlank(dto.getSummarydeductionValues()) && dto.getSummarydeductionValues() != null) {
            pipeLineinventoryMap.put(VariableConstants.SUMMARY_DEDUCTION_VALUE, String.valueOf(dto.getSummarydeductionValues().replace(ARMUtils.SINGLE_QUOTES,ARMUtils.EMPTY_CHARACTER )));
            pipeLineList.add(VariableConstants.SUMMARY_DEDUCTION_VALUE);
        }

        if (dto.getSummaryvariables() != null && !dto.getSummaryvariables().isEmpty()) {
            for (String[] s : dto.getSummaryvariables()) {
                if (builder.length() == 0) {
                    builder.append(s[0]);
                } else {
                    builder.append(ARMUtils.COMMA_CHAR).append(s[0]);
                }
            }
            var = builder.toString();
            pipeLineinventoryMap.put(VariableConstants.SUMMARY_VARIABLES, var);
            pipeLineList.add(VariableConstants.SUMMARY_VARIABLES);
            builder = new StringBuilder();
        }

        if (dto.getSummaryglDate() != null && !dto.getSummaryglDate().isEmpty()) {
            pipeLineinventoryMap.put(VariableConstants.SUMMARY_GL_DATE, String.valueOf(dto.getSummaryglDate()));
            pipeLineList.add(VariableConstants.SUMMARY_GL_DATE);
        }

//--------------------------------ADJUSTMENT DETAILS----------------------------
        if (!"null".equals(dto.getDetailLevel()) || !StringUtils.isBlank(dto.getDetailLevel())) {
            pipeLineinventoryMap.put(VariableConstants.DETAIL_LEVEL, String.valueOf(dto.getDetailLevel()));
            pipeLineList.add(VariableConstants.DETAIL_LEVEL);
        }
        if (dto.getSavedetailvariables() != null && !dto.getSavedetailvariables().isEmpty()) {
            for (String s : dto.getSavedetailvariables()) {
                if (builder.length() == 0) {
                    builder.append(s);
                } else {
                    builder.append(ARMUtils.COMMA_CHAR).append(s);
                }
            }
            var = builder.toString();

            pipeLineinventoryMap.put(VariableConstants.DETAIL_VARIABLE, var);
            pipeLineList.add(VariableConstants.DETAIL_VARIABLE);
            builder = new StringBuilder();
        }

        if (dto.getDetailreserveAcount() != null && !dto.getDetailreserveAcount().isEmpty()) {
            for (String s : dto.getDetailreserveAcount()) {
                if (builder.length() == 0) {
                    builder.append(s);
                } else {
                    builder.append(ARMUtils.COMMA_CHAR).append(s);
                }
            }
            var = builder.toString();

            pipeLineinventoryMap.put(VariableConstants.DETAIL_RESERVER_ACCOUNT, var);
            pipeLineList.add(VariableConstants.DETAIL_RESERVER_ACCOUNT);
            builder = new StringBuilder();
        }
        if (dto.getDetailamountFilter() != null && !dto.getDetailamountFilter().isEmpty()) {
            for (String s : dto.getDetailamountFilter()) {
                if (builder.length() == 0) {
                    builder.append(s);
                } else {
                    builder.append(ARMUtils.COMMA_CHAR).append(s);
                }
            }
            var = builder.toString();
            pipeLineList.add(VariableConstants.DETAIL_AMOUNT_FILTER);
            pipeLineinventoryMap.put(VariableConstants.DETAIL_AMOUNT_FILTER, var);
        }

        deleteAdjustmentSelection(projectionId);
        return saveAdjustmentSelection(projectionId, moduleName, pipeLineinventoryMap, pipeLineList);
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
            Object[] helper = list.get(i);
            HelperDTO dto = new HelperDTO();
            dto.setId(ARMUtils.getIntegerValue(helper[0].toString()));
            dto.setDescription((helper[1].toString()));
            comboBox.addItem(dto);
            String desc = ((String.valueOf(helper[NumericConstants.TWO])).replace("Balance Summary", StringUtils.EMPTY)).trim();
            comboBox.setItemCaption(dto, desc);
        }
    }

    /**
     * This method is to load transaction name of adjustment config value in
     * helper list map
     */
    public static void loadTransactionName() {
        List<Object[]> list = QueryUtils.getItemData(Collections.emptyList(), "Load_Transaction_names", null);
        List helperList = new ArrayList();
        for (Object[] str : list) {
            HelperDTO dto = new HelperDTO();
            dto.setId(str[0] == null ? 0 : ARMUtils.getIntegerValue(str[0].toString()));
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
            Object[] helper = list.get(i);
            HelperDTO dto = new HelperDTO();
            dto.setId(ARMUtils.getIntegerValue(helper[0].toString()));
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
            Object[] helper = list.get(i);
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
            Date fromDate;
            Date fromDefDate;
            Date toDate = new Date();
            Date toDefDate = new Date();

            if (rset != null && !rset.isEmpty()) {
                Object[] obj = (Object[]) rset.get(0);

                String periodViewName = String.valueOf(obj[0]);
                String fromModeName = String.valueOf(obj[1]);
                if ("Auto".equals(fromModeName)) {

                    fromDate = getDate(Integer.parseInt(convertNullToEmpty(obj[NumericConstants.THREE])), true);

                } else {
                    fromDate = parsetDate(convertNullToEmpty(obj[NumericConstants.FOUR]));

                }

                String fromDefModeName = convertNullToEmpty(obj[NumericConstants.FIVE]);
                if ("Auto".equals(fromDefModeName)) {
                    fromDefDate = getDate(Integer.parseInt(convertNullToEmpty(obj[NumericConstants.SEVEN])), true);
                } else {
                    fromDefDate = parsetDate(convertNullToEmpty(obj[NumericConstants.EIGHT]));
                }

                if ("Multiple".equals(periodViewName)) {
                    String toModeName = convertNullToEmpty(obj[NumericConstants.NINE]);

                    if ("Auto".equals(toModeName)) {
                        toDate = getDate(Integer.parseInt(convertNullToEmpty(obj[NumericConstants.ELEVEN])), false);
                    } else {
                        toDate = parsetDate(convertNullToEmpty(obj[NumericConstants.TWELVE]));

                    }

                    String toDefModeName = convertNullToEmpty(obj[NumericConstants.THIRTEEN]);

                    if ("Auto".equals(toDefModeName)) {
                        toDefDate = getDate(Integer.parseInt(convertNullToEmpty(obj[NumericConstants.FIFTEEN])), false);
                    } else {
                        toDefDate = parsetDate(convertNullToEmpty(obj[NumericConstants.SIXTEEN]));
                    }
                }

                if (fromDefDate != null && fromDefDate.before(fromDate)) {
                    fromDefDate = fromDate;
                }

                if (toDate != null && toDate.before(toDefDate)) {
                    toDefDate = toDate;
                }
                forecastList.add(fromDate);
                forecastList.add(fromDefDate);
                forecastList.add(toDate);
                forecastList.add(toDefDate);
            }
        } catch (Exception e) {
            LOGGER.error("Error in getFromAndTo :", e);
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
            date.setDate(Integer.parseInt(ARMConstants.getOctalValue()));
        } else {
            date.setMonth(date.getMonth() + 1);
            date.setDate(Integer.parseInt(ARMConstants.getOctalValue()));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.add(Calendar.DATE, -1);
            date = calendar.getTime();
        }

        return date;
    }

    public static List<Date> getPeriodList(String moduleName, int buscinessProcess, int companyId, int buCompanySid) {
        List list;
        String baseQuery = SQlUtil.getQuery("periodLoadQuery");
        String sql = baseQuery + " WHERE MODULES.DESCRIPTION='" + moduleName + "' AND PCM.BUSINESS_PROCESS_TYPE='" + buscinessProcess + "' AND PCM.COMPANY_MASTER_SID='" + companyId + "' AND PCM.BU_COMPANY_MASTER_SID='" + buCompanySid + "'\n"
                + "ORDER BY VERSION_NO DESC";
        list = QueryUtils.executeSelect(sql);

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

    public static void loadReportIndicator() {
        Map helperUtilMap = HelperListUtil.getInstance().getListNameMap();
        List helperList = new ArrayList();
        HelperDTO positive = new HelperDTO();
        HelperDTO negative = new HelperDTO();
        positive.setId(-1);
        positive.setDescription(ARMUtils.REPORT_INDICATOR_YES);
        negative.setId(1);
        negative.setDescription(ARMUtils.REPORT_INDICATOR_NO);
        helperList.add(positive);
        helperList.add(negative);
        helperUtilMap.put("REPORT_INDICATOR", helperList);
    }

    /**
     * Method to save the selections in Transaction 6
     *
     * @param projectionId
     * @param moduleName
     * @param dto
     * @return
     */
    public static boolean saveInflationAdjustmentSelection(int projectionId, String moduleName, Trx6SelectionDTO dto) {
        StringBuilder builder = new StringBuilder();
        String var = null;
        Map<String, String> inflationAdjMap = new HashMap<>();
        List<String> pipeLineList = new ArrayList<>();
        //-----------------------INVENTORY--------------------------------------------------
        if (dto.getInventoryDetails() != null) {
            inflationAdjMap.put(VariableConstants.INVENTORY_DETAILS_DDLB, String.valueOf(dto.getInventoryDetails()));
            pipeLineList.add(VariableConstants.INVENTORY_DETAILS_DDLB);
        }
        if (!"null".equals(dto.getPrice()) || !StringUtils.isBlank(dto.getPrice())) {
            inflationAdjMap.put(VariableConstants.TRX6_BASELINE_PRICE, dto.getPrice());
            pipeLineList.add(VariableConstants.TRX6_BASELINE_PRICE);
        }
        if (dto.getAdjustedPrice() != null) {
            inflationAdjMap.put(VariableConstants.ADJUSTED_PRICE, dto.getAdjustedPrice());
            pipeLineList.add(VariableConstants.ADJUSTED_PRICE);
        }
        if (dto.getSalesVariables() != null && !dto.getSalesVariables().isEmpty()) {
            for (String[] s : dto.getSalesVariables()) {
                if (builder.length() == 0) {
                    builder.append(s[0]);
                } else {
                    builder.append(ARMUtils.COMMA_CHAR).append(s[0]);
                }
            }
            var = builder.toString();
            inflationAdjMap.put(VariableConstants.SALES_VARIABLE, var);
            pipeLineList.add(VariableConstants.SALES_VARIABLE);
            builder = new StringBuilder();
        }

        //----------------------------------ADJUSTMENT SUMMARY--------------------------
        if (dto.getSummarydeductionLevel() != 0) {
            inflationAdjMap.put(VariableConstants.SUMMARY_DEDUCTION_LEVEL, String.valueOf(dto.getSummarydeductionLevel()));
            pipeLineList.add(VariableConstants.SUMMARY_DEDUCTION_LEVEL);
        }
        if (!"null".equals(dto.getSummarydeductionValues()) && !StringUtils.isBlank(dto.getSummarydeductionValues()) && dto.getSummarydeductionValues() != null) {
            inflationAdjMap.put(VariableConstants.SUMMARY_DEDUCTION_VALUE, String.valueOf(dto.getSummarydeductionValues().replace(ARMUtils.SINGLE_QUOTES,ARMUtils.EMPTY_CHARACTER )));
            pipeLineList.add(VariableConstants.SUMMARY_DEDUCTION_VALUE);
        }

        if (!"null".equals(dto.getSummaryglDate()) || !StringUtils.isBlank(dto.getSummaryglDate())) {
            inflationAdjMap.put(VariableConstants.SUMMARY_GL_DATE, String.valueOf(dto.getSummaryglDate()));
            pipeLineList.add(VariableConstants.SUMMARY_GL_DATE);
        }

        if (dto.getSummaryvariables() != null && !dto.getSummaryvariables().isEmpty()) {
            for (String[] s : dto.getSummaryvariables()) {
                if (builder.length() == 0) {
                    builder.append(s[0]);
                } else {
                    builder.append(ARMUtils.COMMA_CHAR).append(s[0]);
                }
            }
            var = builder.toString();
            inflationAdjMap.put(VariableConstants.SUMMARY_VARIABLES, var);
            pipeLineList.add(VariableConstants.SUMMARY_VARIABLES);
            builder = new StringBuilder();
        }

        //--------------------------------ADJUSTMENT DETAILS----------------------------
        if (!"null".equals(dto.getDetailLevel()) || !StringUtils.isBlank(dto.getDetailLevel())) {
            inflationAdjMap.put(VariableConstants.DETAIL_LEVEL, String.valueOf(dto.getDetailLevel()));
            pipeLineList.add(VariableConstants.DETAIL_LEVEL);
        }
        if (dto.getSavedetailvariables() != null && !dto.getSavedetailvariables().isEmpty()) {
            for (String s : dto.getSavedetailvariables()) {
                if (builder.length() == 0) {
                    builder.append(s);
                } else {
                    builder.append(ARMUtils.COMMA_CHAR).append(s);
                }
            }
            var = builder.toString();

            inflationAdjMap.put(VariableConstants.DETAIL_VARIABLE, var);
            pipeLineList.add(VariableConstants.DETAIL_VARIABLE);
            builder = new StringBuilder();
        }

        if (dto.getDetailreserveAcount() != null && !dto.getDetailreserveAcount().isEmpty()) {
            for (String s : dto.getDetailreserveAcount()) {
                if (builder.length() == 0) {
                    builder.append(s);
                } else {
                    builder.append(ARMUtils.COMMA_CHAR).append(s);
                }
            }
            var = builder.toString();

            inflationAdjMap.put(VariableConstants.DETAIL_RESERVER_ACCOUNT, var);
            pipeLineList.add(VariableConstants.DETAIL_RESERVER_ACCOUNT);
            builder = new StringBuilder();
        }
        if (dto.getDetailamountFilter() != null && !dto.getDetailamountFilter().isEmpty()) {
            for (String s : dto.getDetailamountFilter()) {
                if (builder.length() == 0) {
                    builder.append(s);
                } else {
                    builder.append(ARMUtils.COMMA_CHAR).append(s);
                }
            }
            var = builder.toString();

            inflationAdjMap.put(VariableConstants.DETAIL_AMOUNT_FILTER, var);
            pipeLineList.add(VariableConstants.DETAIL_AMOUNT_FILTER);
        }

        deleteAdjustmentSelection(projectionId);
        return saveAdjustmentSelection(projectionId, moduleName, inflationAdjMap, pipeLineList);
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
        String queryValue = query;
        for (Map.Entry<String, String> entry : tableNameMap.entrySet()) {
            queryValue = queryValue.replaceAll("(?i:\\b" + entry.getKey() + "\\b)", entry.getValue());
        }
        return queryValue;
    }

    public static Boolean isButtonVisibleAccess(String id, Map<String, AppPermission> functionHM) {
        return !(functionHM.get(id) != null && !(functionHM.get(id)).isFunctionFlag());
    }

    public static boolean saveReturnReserveSelection(int projectionId, String moduleName, AbstractSelectionDTO dto) {
        StringBuilder builder = new StringBuilder();
        String var = null;
        Map<String, String> returnReserveMap = new HashMap<>();
        List<String> returnReserveList = new ArrayList<>();
        //-----------------------Returns Data--------------------------------------------------
        if (dto.getReturnsdataSelectedvariables() != null && !dto.getReturnsdataSelectedvariables().isEmpty()) {
            for (String s : dto.getReturnsdataSelectedvariables()) {
                if (builder.length() == 0) {
                    builder.append(s);
                } else {
                    builder.append(ARMUtils.COMMA_CHAR).append(s);
                }
            }
            var = builder.toString();
            returnReserveList.add(VariableConstants.RETURNS_DATA_VARIABLES);
            returnReserveMap.put(VariableConstants.RETURNS_DATA_VARIABLES, var);
            builder = new StringBuilder();
        }
//-----------------------------------RATES--------------------------------------
        if (dto.getRateBasisValue() != 0) {
            returnReserveMap.put(VariableConstants.RATE_BASIS, String.valueOf(dto.getRateBasisValue()));
            returnReserveList.add(VariableConstants.RATE_BASIS);
        }
        if (dto.getRateDeductionLevel() != 0) {
            returnReserveMap.put(VariableConstants.RATE_DEDUCTION_LEVEL, String.valueOf(dto.getRateDeductionLevel()));
            returnReserveList.add(VariableConstants.RATE_DEDUCTION_LEVEL);
        }
        if (!"null".equals(dto.getRateDeductionValue()) || !StringUtils.isBlank(dto.getRateDeductionValue())) {
            returnReserveMap.put(VariableConstants.RATE_DEDUCTION_VALUE, String.valueOf(dto.getRateDeductionValue().replace(ARMUtils.SINGLE_QUOTES,ARMUtils.EMPTY_CHARACTER )));
            returnReserveList.add(VariableConstants.RATE_DEDUCTION_VALUE);
        }
//-----------------------------------RETURN RESERVE DATA--------------------------------------
        if (dto.getReturnReserveDataVariables() != null && !dto.getReturnReserveDataVariables().isEmpty()) {
            for (Object s : dto.getReturnReserveDataVariables().get(1)) {
                if (builder.length() == 0) {
                    builder.append(s);
                } else {
                    builder.append(ARMUtils.COMMA_CHAR).append(s);
                }
            }
            var = builder.toString();

            returnReserveMap.put(VariableConstants.RETURN_RESERVE_DATA_VARIABLES, var);
            returnReserveList.add(VariableConstants.RETURN_RESERVE_DATA_VARIABLES);
            builder = new StringBuilder();
        }

        if (!"null".equals(dto.getOriginalSaleLimiterVal()) || !StringUtils.isBlank(dto.getOriginalSaleLimiterVal())) {
            returnReserveMap.put(VariableConstants.ORIGINAL_SALE_LIMITER_VAL, String.valueOf(dto.getOriginalSaleLimiterVal()));
            returnReserveList.add(VariableConstants.ORIGINAL_SALE_LIMITER_VAL);
        }

        returnReserveMap.put(VariableConstants.REMOVE_CLOSED_BATCHES, String.valueOf(dto.getRemoveClosedBatches()));
        returnReserveList.add(VariableConstants.REMOVE_CLOSED_BATCHES);
        returnReserveMap.put(VariableConstants.EXCLUDE_BASED_ON_LOE_DATE, String.valueOf(dto.getExcludeBasedOnLoeDate()));
        returnReserveList.add(VariableConstants.EXCLUDE_BASED_ON_LOE_DATE);
//----------------------------------ADJUSTMENT SUMMARY--------------------------
        if (dto.getSummarydeductionLevel() != 0) {
            returnReserveMap.put(VariableConstants.SUMMARY_DEDUCTION_LEVEL, String.valueOf(dto.getSummarydeductionLevel()));
            returnReserveList.add(VariableConstants.SUMMARY_DEDUCTION_LEVEL);
        }
        if (!"null".equals(dto.getSummarydeductionValues()) || !StringUtils.isBlank(dto.getSummarydeductionValues())) {
            returnReserveMap.put(VariableConstants.SUMMARY_DEDUCTION_VALUE, String.valueOf(dto.getSummarydeductionValues().replace(ARMUtils.SINGLE_QUOTES,ARMUtils.EMPTY_CHARACTER )));
            returnReserveList.add(VariableConstants.SUMMARY_DEDUCTION_VALUE);
        }

        if (!"null".equals(dto.getSummaryglDate()) || !StringUtils.isBlank(dto.getSummaryglDate())) {
            returnReserveMap.put(VariableConstants.SUMMARY_GL_DATE, String.valueOf(dto.getSummaryglDate()));
            returnReserveList.add(VariableConstants.SUMMARY_GL_DATE);
        }

        if (dto.getSummaryvariables() != null && !dto.getSummaryvariables().isEmpty()) {
            for (String[] s : dto.getSummaryvariables()) {
                if (builder.length() == 0) {
                    builder.append(s[0]);
                } else {
                    builder.append(ARMUtils.COMMA_CHAR).append(s[0]);
                }
            }
            var = builder.toString();
            returnReserveMap.put(VariableConstants.SUMMARY_VARIABLES, var);
            returnReserveList.add(VariableConstants.SUMMARY_VARIABLES);
            builder = new StringBuilder();
        }

//--------------------------------ADJUSTMENT DETAILS----------------------------
        if (!"null".equals(dto.getDetailLevel()) || !StringUtils.isBlank(dto.getDetailLevel())) {
            returnReserveMap.put(VariableConstants.DETAIL_LEVEL, String.valueOf(dto.getDetailLevel()));
            returnReserveList.add(VariableConstants.DETAIL_LEVEL);
        }
        if (dto.getSavedetailvariables() != null && !dto.getSavedetailvariables().isEmpty()) {
            for (String s : dto.getSavedetailvariables()) {
                if (builder.length() == 0) {
                    builder.append(s);
                } else {
                    builder.append(ARMUtils.COMMA_CHAR).append(s);
                }
            }
            var = builder.toString();

            returnReserveMap.put(VariableConstants.DETAIL_VARIABLE, var);
            returnReserveList.add(VariableConstants.DETAIL_VARIABLE);
            builder = new StringBuilder();
        }

        if (dto.getDetailreserveAcount() != null && !dto.getDetailreserveAcount().isEmpty()) {
            for (String s : dto.getDetailreserveAcount()) {
                if (builder.length() == 0) {
                    builder.append(s);
                } else {
                    builder.append(ARMUtils.COMMA_CHAR).append(s);
                }
            }
            var = builder.toString();

            returnReserveMap.put(VariableConstants.DETAIL_RESERVER_ACCOUNT, var);
            returnReserveList.add(VariableConstants.DETAIL_RESERVER_ACCOUNT);
            builder = new StringBuilder();
        }

        if (dto.getDetailamountFilter() != null && !dto.getDetailamountFilter().isEmpty()) {
            for (String s : dto.getDetailamountFilter()) {
                if (builder.length() == 0) {
                    builder.append(s);
                } else {
                    builder.append(ARMUtils.COMMA_CHAR).append(s);
                }
            }
            var = builder.toString();

            returnReserveMap.put(VariableConstants.DETAIL_AMOUNT_FILTER, var);
            returnReserveList.add(VariableConstants.DETAIL_AMOUNT_FILTER);
        }

        deleteAdjustmentSelection(projectionId);

        return saveAdjustmentSelection(projectionId, moduleName, returnReserveMap, returnReserveList);
    }

    private static CommonLogic object;

    public static synchronized CommonLogic getInstance() {
        if (object == null) {
            object = new CommonLogic();
        }
        return object;
    }

    public List getVariablesList() {
        if (variableLists.isEmpty()) {
            variableLists.add(VariableConstants.DETAIL_VARIABLE);
            variableLists.add(VariableConstants.DETAIL_RESERVER_ACCOUNT);
            variableLists.add(VariableConstants.RATE_DEDUCTION_VALUE);
            variableLists.add(VariableConstants.SALES_VARIABLE);
            variableLists.add(VariableConstants.SUMMARY_DEDUCTION_VALUE);
            variableLists.add(VariableConstants.SUMMARY_VARIABLES);
            variableLists.add(VariableConstants.DETAIL_AMOUNT_FILTER);
            variableLists.add(VariableConstants.RETURN_RESERVE_DATA_VARIABLES);
            variableLists.add(VariableConstants.RETURNS_DATA_VARIABLES);
        }
        return new ArrayList<>(variableLists);
    }

    public static String getFromAndToPeriodBasedonFrequency(Date fromDate, String frequency) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String newDateStr = simpleDateFormat.format(fromDate);
        String[] str = newDateStr.split("/");
        String year = str[2];
        if (ARMConstants.getAnnually().equals(frequency)) {
            return year;
        } else if (ARMConstants.getSemiAnnually().equals(frequency)) {
            int freq = (Integer.parseInt(str[0])) % 6 != 0 ? (((Integer.parseInt(str[0])) / 6) + 1) : ((Integer.parseInt(str[0])) / 6);
            return "S" + freq + ARMUtils.SPACE + year;
        } else if (ARMConstants.getQuarterly().equals(frequency)) {
            int freq = (Integer.parseInt(str[0])) % 4 != 0 ? (((Integer.parseInt(str[0])) / 4) + 1) : ((Integer.parseInt(str[0])) / 4);
            return "Q" + freq + ARMUtils.SPACE + year;
        } else {
            return AbstractBPLogic.getMonthName(Integer.parseInt(str[0])) + ARMUtils.SPACE + year;
        }
    }

    public static String getFromDateFilter(String value, String frequency) {
        DecimalFormat format = new DecimalFormat("#00");
        if (ARMConstants.getAnnually().equals(frequency)) {
            return value + value;
        } else if (ARMConstants.getSemiAnnually().equals(frequency)) {
            String[] str = value.split(VariableConstants.SLASH_SPACE);
            str[0] = str[0].replace("S", "");
            return str[1].trim() + format.format(Integer.valueOf(str[0]));
        } else if (ARMConstants.getQuarterly().equals(frequency)) {
            String[] str = value.split(VariableConstants.SLASH_SPACE);
            str[0] = str[0].replace("Q", "");
            return str[1].trim() + format.format(Integer.valueOf(str[0]));
        } else {
            String[] str = value.split(VariableConstants.SLASH_SPACE);
            return str[1].trim() + format.format(CommonUtils.getMonthNo(str[0].trim()));
        }
    }

    public List<String> getPipelineColumns() {
        if (pipelineList.isEmpty()) {
            pipelineList.add("StartingBalance");
            pipelineList.add("PipelineAccrual");
            pipelineList.add("DemandAccrual");
            pipelineList.add("PipelineInventoryTrueUp");
            pipelineList.add("DemandReforecast");
            pipelineList.add("TotalPeriodAdjustment");
            pipelineList.add("EndingBalance");
        }
        return new ArrayList<>(pipelineList);
    }

    public List<String> getReturnReserve() {
        if (returnReserveList.isEmpty()) {
            returnReserveList.add("StartingBalance");
            returnReserveList.add("ReturnReserve");
            returnReserveList.add("EndingBalance");
        }
        return new ArrayList<>(returnReserveList);
    }

    public List<String> getTotalReturnReserveColumns() {
        if (totalReturnReserveList.isEmpty()) {
            totalReturnReserveList.add("TotalStartingBalance");
            totalReturnReserveList.add("ReturnReserve");
            totalReturnReserveList.add("TotalEndingBalance");
        }
        return new ArrayList<>(totalReturnReserveList);
    }

    public List<String> getTotalPipelineColumns() {
        if (totalPipelineList.isEmpty()) {
            totalPipelineList.add("TotalStartingBalance");
            totalPipelineList.add("TotalPipelineAccrual");
            totalPipelineList.add("TotalDemandAccrual");
            totalPipelineList.add("TotalPipelineInventoryTrueUp");
            totalPipelineList.add("TotalDemandReforecast");
            totalPipelineList.add("TotalTotalPeriodAdjustment");
            totalPipelineList.add("TotalEndingBalance");
        }
        return new ArrayList<>(totalPipelineList);
    }

    public List<String> getDemandColumns() {
        if (demandList.isEmpty()) {
            demandList.add(VariableConstants.DEMAND_ACCRUAL);
            demandList.add(VariableConstants.DEMAND_REFORECAST);
            demandList.add(VariableConstants.PAYMENT_TRUE_UP);
            demandList.add(VariableConstants.ACTUAL_PAYMENTS);
            demandList.add(VariableConstants.PERIOD_BALANCE);
            demandList.add(VariableConstants.PAYMENT_RATIO);
        }
        return new ArrayList<>(demandList);
    }

    public List<String> getTotalDemandColumns() {
        if (totalDemandList.isEmpty()) {
            totalDemandList.add(VariableConstants.TOTAL_DEMAND_ACCRUAL);
            totalDemandList.add(VariableConstants.TOTAL_DEMAND_REFORECAST);
            totalDemandList.add(VariableConstants.TOTAL_PAYMENT_TRUE_UP);
            totalDemandList.add(VariableConstants.TOTAL_ACTUAL_PAYMENTS);
            totalDemandList.add(VariableConstants.TOTAL_PERIOD_BALANCE);
            totalDemandList.add(VariableConstants.TOTAL_PAYMENT_RATIO);
        }
        return new ArrayList<>(totalDemandList);
    }

    public List<String> getSingleLiablityColumns() {
        if (singleLiablityList.isEmpty()) {
            singleLiablityList.add(VariableConstants.FEES_ACCRUAL);
            singleLiablityList.add(VariableConstants.INFLATION_ADJUSTMENT);
            singleLiablityList.add(VariableConstants.CREDIT_CARD_FEES);
            singleLiablityList.add(VariableConstants.OTHER_FIXED_DOLLAR_FEES);
            singleLiablityList.add(VariableConstants.INVENTORY_VALUATION);
            singleLiablityList.add(VariableConstants.PAYMENT_TRUE_UP);
            singleLiablityList.add(VariableConstants.PAYMENTS);
            singleLiablityList.add(VariableConstants.PERIOD_BALANCE);
        }
        return new ArrayList<>(singleLiablityList);
    }

    public List<String> getTotalSingleLiablityColumns() {
        if (totalSingleLiablityList.isEmpty()) {
            totalSingleLiablityList.add(VariableConstants.TOTAL_FEES_ACCRUAL);
            totalSingleLiablityList.add(VariableConstants.TOTAL_INFLATION_ADJUSTMENT);
            totalSingleLiablityList.add(VariableConstants.TOTAL_CREDIT_CARD_FEES);
            totalSingleLiablityList.add(VariableConstants.TOTAL_OTHER_FIXED_DOLLAR_FEES);
            totalSingleLiablityList.add(VariableConstants.TOTAL_INVENTORY_VALUATION);
            totalSingleLiablityList.add(VariableConstants.TOTAL_PAYMENT_TRUE_UP);
            totalSingleLiablityList.add(VariableConstants.TOTAL_PAYMENTS);
            totalSingleLiablityList.add(VariableConstants.TOTAL_PERIOD_BALANCE);
        }
        return new ArrayList<>(totalSingleLiablityList);
    }

    public static int getSummaryPeriods(String fromPeriod, String toPeriod, String frequency) {
        if (ARMConstants.getAnnually().equals(frequency)) {
            return (Integer.valueOf(toPeriod) - Integer.parseInt(fromPeriod)) + 1;
        } else {
            List inputs = new ArrayList();
            String column = ARMConstants.getSemiAnnually().equals(frequency) ? "SEMI_ANNUAL" : frequency.replace("ly", StringUtils.EMPTY);
            inputs.add(column);
            inputs.addAll(getFromToPeriods(fromPeriod, frequency));
            inputs.add(column);
            inputs.addAll(getFromToPeriods(toPeriod, frequency));
            inputs.add(column);
            List list = QueryUtils.getItemData(inputs, "getPeriodsforBSR", StringUtils.EMPTY);
            return (Integer) list.get(0);
        }
    }

    public static List getFromToPeriods(String value, String frequency) {
        List list = new ArrayList();
        String[] str;
        if (ARMConstants.getSemiAnnually().equals(frequency)) {
            str = value.split(VariableConstants.SLASH_SPACE);
            str[0] = str[0].replace(ARMUtils.S, "");
        } else if (ARMConstants.getQuarterly().equals(frequency)) {
            str = value.split(VariableConstants.SLASH_SPACE);
            str[0] = str[0].replace(ARMUtils.Q, "");
        } else {
            str = value.split(VariableConstants.SLASH_SPACE);
            str[0] = String.valueOf(CommonUtils.getMonthNo(str[0].trim()));
        }
        list.add(str[0]);
        list.add(str[1]);
        return list;
    }

    public static List getPeriodSidsList(String fromPeriod, String toPeriod, String frequency) {
        String[] fromStr;
        String[] toStr;
        List inputs;
        if (ARMConstants.getAnnually().equals(frequency)) {
            inputs = addInputs(StringUtils.EMPTY, fromPeriod, StringUtils.EMPTY, toPeriod);
        } else if (ARMConstants.getSemiAnnually().equals(frequency)) {
            fromStr = fromPeriod.split(VariableConstants.SLASH_SPACE);
            fromStr[0] = fromStr[0].replace(ARMUtils.S, "");
            toStr = toPeriod.split(VariableConstants.SLASH_SPACE);
            toStr[0] = toStr[0].replace(ARMUtils.S, "");
            inputs = addInputs("SEMI_ANNUAL = " + fromStr[0] + VariableConstants.AND, fromStr[1], "SEMI_ANNUAL = " + toStr[0] + VariableConstants.AND, toStr[1]);
        } else if (ARMConstants.getQuarterly().equals(frequency)) {
            fromStr = fromPeriod.split(VariableConstants.SLASH_SPACE);
            fromStr[0] = fromStr[0].replace(ARMUtils.Q, "");
            toStr = toPeriod.split(VariableConstants.SLASH_SPACE);
            toStr[0] = toStr[0].replace(ARMUtils.Q, "");
            inputs = addInputs("QUARTER = " + fromStr[0] + VariableConstants.AND, fromStr[1], "QUARTER = " + toStr[0] + VariableConstants.AND, toStr[1]);
        } else {
            fromStr = fromPeriod.split(VariableConstants.SLASH_SPACE);
            fromStr[0] = String.valueOf(CommonUtils.getMonthNo(fromStr[0].trim()));
            toStr = toPeriod.split(VariableConstants.SLASH_SPACE);
            toStr[0] = String.valueOf(CommonUtils.getMonthNo(toStr[0].trim()));
            inputs = addInputs("MONTH = " + fromStr[0] + VariableConstants.AND, fromStr[1], "MONTH = " + toStr[0] + VariableConstants.AND, toStr[1]);
        }

        return QueryUtils.getItemData(inputs, "getPeriodSidsforBSR", StringUtils.EMPTY);
    }

    public static List addInputs(String val1, String val2, String val3, String val4) {
        List input = new ArrayList();
        input.add(val1);
        input.add(val2);
        input.add(val3);
        input.add(val4);
        return new ArrayList(input);
    }

    public Date getDates(Date date) {
        return date == null ? null : (Date) date.clone();
    }

    public String[] getStringArrayCloned(String[] string) {
        return string == null ? null : string.clone();
    }

    public Object[] getObjectArrayCloned(Object[] object) {
        return object == null ? null : object.clone();
    }

    public List getArrayListCloned(List list) {
        return list == null ? null : new ArrayList<>(list);
    }

    public static int getIntegerValue(int index, Object[] str) {
        return str[index] == null ? 0 : (Integer) (str[index]);
    }

    public static String getStringValue(int index, Object[] str) {
        return str[index] == null ? StringUtils.EMPTY : String.valueOf(str[index]);
    }

    public static String getDateValue(int index, Object[] str, DateFormat dateFormat) {
        return str[index] == null ? null : dateFormat.format((Date) str[index]);
    }
}
