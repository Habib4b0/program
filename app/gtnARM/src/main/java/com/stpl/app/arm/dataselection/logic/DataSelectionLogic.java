
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.dataselection.logic;

import static com.stpl.app.utils.CommonUtils.userMap;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.jboss.logging.Logger;

import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.dataselection.dto.DataSelectionDTO;
import com.stpl.app.arm.dataselection.dto.DeductionLevelDTO;
import com.stpl.app.arm.dataselection.dto.HierarchyLookupDTO;
import com.stpl.app.arm.dataselection.dto.LevelDTO;
import com.stpl.app.arm.dataselection.dto.SaveViewDTO;
import com.stpl.app.arm.dataselection.dto.ViewDTO;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.arm.utils.DataSelectionQueryUtils;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.ProjectionMasterLocalServiceUtil;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.app.utils.xmlparser.SQlUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.stpl.util.dao.orm.CustomSQLUtil;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TreeTable;

/**
 *
 * @author
 */
public class DataSelectionLogic {

    public static final Logger LOGGER = Logger.getLogger(DataSelectionLogic.class);
    boolean isNDC = false;
    private final HelperListUtil helperListUtil = HelperListUtil.getInstance();
    Map<String, String[]> levelMap = new HashMap<>();
    String filterString = StringUtils.EMPTY;
    List closedinput;
    List openinput;
    List input;
    List newClose;

    public DataSelectionLogic() {
        LOGGER.debug("Inside DataSelectionLogic COnstructor");

    }

    public List searchLogicForHierarchy(HierarchyLookupDTO hierarchyLookupDTO, boolean isCount, final int startIndex, final int endIndex, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet) {
        LOGGER.debug("--Inside searchLogicForHierarchy--");
        List<HierarchyLookupDTO> resultList = new ArrayList<>();
        String sqlQuery;
        String hierName = hierarchyLookupDTO.getHierarchySearchName();
        String hierType = hierarchyLookupDTO.getHierarchySearchType();
        String lookupName = hierarchyLookupDTO.getLookupSearchName();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        if (isCount) {
            sqlQuery = SQlUtil.getQuery("getCountCustAndProdHier");
        } else {
            sqlQuery = SQlUtil.getQuery("getCustAndProdHier");
        }
        if (hierName.isEmpty()) {
            hierName = "%";
        }
        if (hierType.isEmpty()) {
            hierType = "%";
        }
        if (lookupName.isEmpty()) {
            lookupName = "%";
        }
        sqlQuery = sqlQuery.replace("$Hierarchy$", lookupName);
        sqlQuery = sqlQuery.replace("$Hierarchy_Type$", hierType);
        if (hierarchyLookupDTO.getHierarchySearchName().contains(ARMUtils.CHAR_ASTERISK)) {
            hierName = hierarchyLookupDTO.getHierarchySearchName().replace(ARMUtils.CHAR_ASTERISK, ARMUtils.CHAR_PERCENT);
        }
        if (hierarchyLookupDTO.getHierarchySearchName().contains(ARMUtils.CHAR_ASTERISK)) {
            hierName = hierarchyLookupDTO.getHierarchySearchName().replace(ARMUtils.CHAR_ASTERISK, ARMUtils.CHAR_PERCENT);
        }
        sqlQuery = sqlQuery.replace("$Hierarchy_Name$", hierName);
        StringBuilder filterQuery = new StringBuilder(StringUtils.EMPTY);
        HashMap<String, String> detailsColumn = new HashMap<>();
        detailsColumn.put("hierarchyName", "c.HIERARCHY_NAME");
        detailsColumn.put("highestLevel", "a.LEVEL_NO");
        detailsColumn.put("lowestLevel", "a.LEVEL_NO");
        detailsColumn.put("createdDate", "c.CREATED_DATE");
        detailsColumn.put("modifiedDate", "c.MODIFIED_DATE");

        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = "%" + stringFilter.getFilterString() + "%";
                    filterQuery.append(filterQuery).append(CommonConstant.AND).append(detailsColumn.get(String.valueOf(stringFilter.getPropertyId())));
                    filterQuery.append(" like '").append(filterString).append("'");
                }
            }
        }
        String finalQuery;
        String order = StringUtils.EMPTY;
        if (!isCount) {
            boolean sortOrder = false;
            String columnName = null;
            String orderByColumn = null;
            if (sortByColumns != null) {
                for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                    final SortByColumn sortByColumn = iterator.next();
                    columnName = sortByColumn.getName();
                    orderByColumn = detailsColumn.get(columnName);
                    if (sortByColumn.getType() == SortByColumn.Type.ASC) {
                        sortOrder = false;
                    } else {
                        sortOrder = true;
                    }
                }
            }
            if (orderByColumn == null || StringUtils.EMPTY.equals(orderByColumn)) {
                order = order + " ORDER BY c.HIERARCHY_NAME ";
            } else {
                order = order + " ORDER BY " + orderByColumn + ((!sortOrder) ? " ASC " : " DESC ");
            }
            order = order + " " + "OFFSET ";
            order = order + startIndex;
            order = order + " ROWS FETCH NEXT " + endIndex;
            order = order + " ROWS ONLY;";
        }
        if (isCount) {
            finalQuery = sqlQuery + filterQuery.toString();
        } else {
            finalQuery = sqlQuery + filterQuery.toString() + order;
        }
        LOGGER.debug("finalQuery --   " + finalQuery);
        if (isCount) {
            return HelperTableLocalServiceUtil.executeSelectQuery(finalQuery);
        }
        List<Object[]> list = HelperTableLocalServiceUtil.executeSelectQuery(finalQuery);
        for (Object[] obj : list) {
            HierarchyLookupDTO hdto = new HierarchyLookupDTO();
            hdto.setHierarchyId(Integer.valueOf(obj[0].toString()));
            hdto.setHierarchyName(String.valueOf(obj[1].toString()));
            hdto.setHighestLevel(String.valueOf(obj[NumericConstants.THREE].toString()));
            hdto.setLowestLevel(String.valueOf(obj[NumericConstants.FOUR].toString()));
            if (obj[NumericConstants.FIVE] == null) {
                hdto.setCreatedDate(ConstantsUtils.EMPTY);
            } else {
                try {
                    hdto.setCreatedDate(df.format(format.parse(obj[NumericConstants.FIVE].toString())));
                } catch (Exception ex) {
                    LOGGER.error("Errro in searchLogicForHierarchy setcreateddate :" + ex);
                }
            }
            if (obj[NumericConstants.SIX] == null) {
                hdto.setModifiedDate(ConstantsUtils.EMPTY);
            } else {
                try {
                    hdto.setModifiedDate(df.format(format.parse(obj[NumericConstants.SIX].toString())));
                } catch (Exception ex) {
                    LOGGER.error("Errro in searchLogicForHierarchy modifiedDate :" + ex);
                }
            }
            hdto.setVersionNo(Integer.parseInt(String.valueOf(obj[NumericConstants.SEVEN].toString())));
            resultList.add(hdto);
        }
        LOGGER.debug("--Ending searchLogicForHierarchy--" + resultList.size());
        return resultList;
    }

    public void loadCustomerRelation(ComboBox comboBox, int hierSid) {
        String sqlQuery = SQlUtil.getQuery("getCustRelation").concat(StringUtils.EMPTY + hierSid);
        LOGGER.debug("--Inside loadCustomerRelation--");
        LOGGER.debug(CommonConstant.SQL_QUERY + sqlQuery);
        List<Object[]> list = HelperTableLocalServiceUtil.executeSelectQuery(sqlQuery);
        comboBox.removeAllItems();
        comboBox.addItem(GlobalConstants.getSelectOne());
        comboBox.setNullSelectionAllowed(true);
        comboBox.setNullSelectionItemId(GlobalConstants.getSelectOne());
        for (Object[] obj : list) {
            comboBox.addItem(obj[0]);
            comboBox.setItemCaption(obj[0], String.valueOf(obj[1]));
        }
        LOGGER.debug("--End of  loadCustomerRelation--");
    }

    public void loadProductRelation(ComboBox comboBox, int hierSid, int glComp) {
        LOGGER.debug("--Inside loadProductRelation--");
        String sqlQuery = SQlUtil.getQuery("getProdRelation").concat(StringUtils.EMPTY + hierSid);
        if (glComp != 0) {
            sqlQuery += " " + SQlUtil.getQuery("joinGLComp").concat(StringUtils.EMPTY + glComp);
        }
        LOGGER.debug(CommonConstant.SQL_QUERY + sqlQuery);
        List<Object[]> list = HelperTableLocalServiceUtil.executeSelectQuery(sqlQuery);
        comboBox.removeAllItems();
        comboBox.addItem(GlobalConstants.getSelectOne());
        comboBox.setNullSelectionAllowed(true);
        comboBox.setNullSelectionItemId(GlobalConstants.getSelectOne());
        for (Object[] obj : list) {
            comboBox.addItem(obj[0]);
            comboBox.setItemCaption(obj[0], String.valueOf(obj[1]));
        }
    }

    public void loadCustoProdLevels(ComboBox comboBox, int hierSid) {
        LOGGER.debug("--Inside loadCustoProdLevels--");
        String customSql = CustomSQLUtil.get("getLevelsFromHierarchy");
        if (StringUtils.isNotEmpty(String.valueOf(hierSid))
                && StringUtils.isNotBlank(String.valueOf(hierSid))) {
            customSql += String.valueOf(hierSid).trim();
        }
        customSql += CustomSQLUtil.get("getLevelsFromHierarchy2");
        LOGGER.debug(CommonConstant.SQL_QUERY + customSql);
        List<Object[]> list = HelperTableLocalServiceUtil.executeSelectQuery(customSql);
        comboBox.removeAllItems();
        comboBox.addItem(GlobalConstants.getSelectOne());
        comboBox.setNullSelectionAllowed(true);
        comboBox.setNullSelectionItemId(GlobalConstants.getSelectOne());
        for (Object[] obj : list) {
            comboBox.addItem(obj[1]);
            comboBox.setItemCaption(obj[1], ARMUtils.LEVEL + ARMUtils.SPACE + obj[1] + ARMUtils.SPACE + ARMUtils.HIPHEN + ARMUtils.SPACE + obj[0]);
        }
        LOGGER.debug("--End loadCustoProdLevels--");
    }

    public void insertToCcpMap(final String hierarchyIndicator, final String tempTableName, final String hierarchyDefinitionSid, final String relationshipBuilderSid) {
        DataSelectionQueryUtils.insertIntoCcpMap(hierarchyIndicator, tempTableName, hierarchyDefinitionSid, relationshipBuilderSid);
    }

    public Map<String, String> getLevelValueMap(String relationshipBuilderSID) {
        Map<String, Object> input = new HashMap<>();
        input.put("?RBSID", relationshipBuilderSID);
        return (Map<String, String>) DataSelectionQueryUtils.tempOperation(input);
    }

    public List<LevelDTO> loadCustomerForecastLevel(int hierarchyId, String hierarchyName) {
        LOGGER.debug("--Inside loadCustomerForecastLevel--");
        List<LevelDTO> resultList = new ArrayList<>();
        LevelDTO leveldto;
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("hierarchyId", hierarchyId);
        if (!StringUtils.EMPTY.equals(hierarchyName)) {
            parameters.put("hierarchyName", hierarchyName);
        }
        try {
            List<Object[]> returnlist = DataSelectionQueryUtils.getLevelsFromHierarchy(parameters);
            for (Object[] object : returnlist) {
                leveldto = new LevelDTO();
                leveldto.setLevel(object[0] == null ? StringUtils.EMPTY : String.valueOf(object[0]));
                leveldto.setLevelNo(CommonLogic.parseStringToInteger(String.valueOf(object[1])));
                leveldto.setLevelValueReference(object[NumericConstants.TWO] == null ? StringUtils.EMPTY : String.valueOf(object[NumericConstants.TWO]));
                leveldto.setTableName(object[NumericConstants.THREE] == null ? StringUtils.EMPTY : String.valueOf(object[NumericConstants.THREE]));
                leveldto.setFieldName(object[NumericConstants.FOUR] == null ? StringUtils.EMPTY : String.valueOf(object[NumericConstants.FOUR]));
                if (object[NumericConstants.THREE] != null && !StringUtils.isEmpty(String.valueOf(object[NumericConstants.THREE])) && !StringUtils.isBlank(String.valueOf(object[NumericConstants.THREE]))) {
                    if (ARMUtils.COMPANY_MASTER.equalsIgnoreCase(String.valueOf(object[NumericConstants.THREE]))) {
                        leveldto.setFromCompany(true);
                    } else if (ARMUtils.CONTRACT_MASTER.equalsIgnoreCase(String.valueOf(object[NumericConstants.THREE]))) {
                        leveldto.setFromContract(true);
                    } else if (ARMUtils.ITEM_MASTER.equalsIgnoreCase(String.valueOf(object[NumericConstants.THREE]))) {
                        leveldto.setFromItem(true);
                    } else {
                        leveldto.setFromCompany(false);
                        leveldto.setFromContract(false);
                        leveldto.setFromItem(false);
                    }
                }
                resultList.add(leveldto);
            }
        } catch (Exception e) {
            LOGGER.error(e + " in DSLogic - loadCustomerForecastLevel");
        }
        LOGGER.debug("--End of loadCustomerForecastLevel--");
        return resultList;
    }

    public List<LevelDTO> loadCustomerInnerLevel(final String relationshipSid, int levelNo, int hierarchyId, List<Integer> rsContractSidList,
            final Map<String, String> descriptionMap, String tempCCPTable) {
        LOGGER.debug("--Inside loadCustomerInnerLevel--");
        List<LevelDTO> values = new ArrayList<>();
        List result;

        result = DataSelectionQueryUtils.getCustomerInnerLevel(relationshipSid, levelNo, hierarchyId, rsContractSidList, tempCCPTable);
        if (result != null && !result.isEmpty()) {
            LevelDTO dto;
            for (int i = 0; i < result.size(); i++) {
                dto = new LevelDTO();
                Object[] obj = (Object[]) result.get(i);
                dto.setRelationshipLevelValue(String.valueOf(obj[0]));
                dto.setLevelNo(Integer.parseInt(String.valueOf(obj[1])));
                dto.setParentNode(String.valueOf(obj[NumericConstants.TWO]));
                dto.setRelationshipLevelSid(Integer.parseInt(String.valueOf(obj[NumericConstants.THREE])));
                dto.setTableName(String.valueOf(obj[NumericConstants.FOUR]));
                dto.setFieldName(String.valueOf(obj[NumericConstants.FIVE]));
                dto.setHierarchyNo(String.valueOf(obj[NumericConstants.SIX]));
                dto.setRelationShipBuilderId(String.valueOf(obj[NumericConstants.SEVEN]));
                dto.setLevel(String.valueOf(obj[NumericConstants.EIGHT]));
                if (obj[NumericConstants.FOUR] != null && !StringUtils.isEmpty(String.valueOf(obj[NumericConstants.FOUR])) && !StringUtils.isBlank(String.valueOf(obj[NumericConstants.FOUR]))) {
                    if (ARMUtils.COMPANY_MASTER.equalsIgnoreCase(String.valueOf(obj[NumericConstants.FOUR]))) {
                        dto.setFromCompany(true);
                    } else if (ARMUtils.CONTRACT_MASTER.equalsIgnoreCase(String.valueOf(obj[NumericConstants.FOUR]))) {
                        dto.setFromContract(true);
                    } else if (ARMUtils.ITEM_MASTER.equalsIgnoreCase(String.valueOf(obj[NumericConstants.FOUR]))) {
                        dto.setFromItem(true);
                    } else {
                        dto.setFromCompany(false);
                        dto.setFromContract(false);
                        dto.setFromItem(false);
                    }
                }

                if (descriptionMap != null) {
                    dto.setDisplayValue(descriptionMap.get(dto.getHierarchyNo()));
                }

                values.add(dto);
            }
        }
        LOGGER.debug("--End of loadCustomerInnerLevel--");
        return values;
    }

    public List<LevelDTO> loadProductInnerLevel(final String prodRelationshipSid, int prodLevelNo, int prodHierarchyId,
            List<Integer> rsContractSidList, List<Integer> customerSidList, int busenessUnitVal,
            int glCompSid, boolean isNdc, final String custRelationshipSid, int custLevelNo, int custhierarchyId, final Map<String, String> descriptionMap) {
        LOGGER.debug("--Inside loadProductInnerLevel--");
        List<LevelDTO> values = new ArrayList<>();
        List result = DataSelectionQueryUtils.getProductInnerLevel(prodRelationshipSid, prodLevelNo, prodHierarchyId,
                rsContractSidList, customerSidList, busenessUnitVal,
                glCompSid, isNdc, custRelationshipSid, custLevelNo, custhierarchyId);

        if (result != null && !result.isEmpty()) {
            LevelDTO dto;
            for (int i = 0; i < result.size(); i++) {
                dto = new LevelDTO();
                Object[] obj = (Object[]) result.get(i);
                dto.setRelationshipLevelValue(String.valueOf(obj[0]));
                dto.setLevelNo(Integer.parseInt(String.valueOf(obj[1])));
                dto.setParentNode(String.valueOf(obj[NumericConstants.TWO]));
                dto.setRelationshipLevelSid(Integer.parseInt(String.valueOf(obj[NumericConstants.THREE])));
                dto.setTableName(String.valueOf(obj[NumericConstants.FOUR]));
                dto.setFieldName(String.valueOf(obj[NumericConstants.FIVE]));
                dto.setHierarchyNo(String.valueOf(obj[NumericConstants.SIX]));
                dto.setRelationShipBuilderId(String.valueOf(obj[NumericConstants.SEVEN]));
                dto.setLevel(String.valueOf(obj[NumericConstants.EIGHT]));
                if (obj[NumericConstants.FOUR] != null && !StringUtils.isEmpty(String.valueOf(obj[NumericConstants.FOUR])) && !StringUtils.isBlank(String.valueOf(obj[NumericConstants.FOUR]))) {
                    if (ARMUtils.COMPANY_MASTER.equalsIgnoreCase(String.valueOf(obj[NumericConstants.FOUR]))) {
                        dto.setFromCompany(true);
                    } else if (ARMUtils.CONTRACT_MASTER.equalsIgnoreCase(String.valueOf(obj[NumericConstants.FOUR]))) {
                        dto.setFromContract(true);
                    } else if (ARMUtils.ITEM_MASTER.equalsIgnoreCase(String.valueOf(obj[NumericConstants.FOUR]))) {
                        dto.setFromItem(true);
                    } else {
                        dto.setFromCompany(false);
                        dto.setFromContract(false);
                        dto.setFromItem(false);
                    }
                }

                if (isNdc) {
                    dto.setNdc(String.valueOf(obj[0]));
                    dto.setForm(String.valueOf(obj[NumericConstants.NINE]));
                    dto.setStrength(String.valueOf(obj[NumericConstants.TEN]));
                }

                if (descriptionMap != null) {
                    dto.setDisplayValue(descriptionMap.get(dto.getHierarchyNo()));
                }

                values.add(dto);
            }
        }
        LOGGER.debug("--End of  loadProductInnerLevel--");
        return values;
    }

    public List<LevelDTO> getParentLevelsWithHierarchyNo(final String hierarchyNos, final Map<String, String> descriptionMap) {
        LOGGER.debug("--Inside getParentLevelsWithHierarchyNo--");
        List resultss;
        List<LevelDTO> resultList = null;
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("hierarchyNos", hierarchyNos);
        parameters.put(ARMUtils.INDICATOR, "getParentLevelsWithHierarchyNo");
        LevelDTO dto;

        try {
            resultss = DataSelectionQueryUtils.getParentLevels(0, parameters);

            if (resultss != null) {
                resultList = new ArrayList<>();
                for (int loop = 0, limit = resultss.size(); loop < limit; loop++) {
                    Object[] objects = (Object[]) resultss.get(loop);
                    dto = new LevelDTO();
                    dto.setLevelNo(Integer.parseInt(String.valueOf(objects[0])));
                    dto.setRelationshipLevelValue(String.valueOf(objects[1]));
                    dto.setParentNode(String.valueOf(objects[NumericConstants.TWO]));
                    dto.setLevel(String.valueOf(objects[NumericConstants.THREE]));
                    dto.setLevelValueReference(String.valueOf(objects[NumericConstants.FOUR]));
                    dto.setTableName(String.valueOf(objects[NumericConstants.FIVE]));
                    dto.setFieldName(String.valueOf(objects[NumericConstants.SIX]));
                    dto.setRelationshipLevelSid(Integer.parseInt(String.valueOf(objects[NumericConstants.SEVEN])));
                    dto.setHierarchyNo(String.valueOf(objects[NumericConstants.EIGHT]));
                    dto.setRelationShipBuilderId(String.valueOf(objects[NumericConstants.NINE]));
                    if (descriptionMap != null) {
                        dto.setDisplayValue(descriptionMap.get(String.valueOf(objects[NumericConstants.EIGHT])));
                    }
                    resultList.add(dto);
                }
            }
        } catch (Exception ex) {
            LOGGER.error("Error in getParentLevelsWithHierarchyNo :" + ex);
        }
        LOGGER.debug("--End of  getParentLevelsWithHierarchyNo--");
        return resultList;
    }

    public List<LevelDTO> getChildLevelsWithHierarchyNo(String hierarchyNo, int lowestLevelNo, final Map<String, String> descriptionMap) {
        LOGGER.debug("--Inside getChildLevelsWithHierarchyNo--");
        List resultss;
        List<LevelDTO> resultList = null;
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(ARMUtils.HIERARACHY_NO_PROPERTY, hierarchyNo);
        parameters.put("lowestLevelNo", lowestLevelNo);
        parameters.put(ARMUtils.INDICATOR, "getChildLevelsWithHierarchyNo");
        LevelDTO dto;

        try {
            resultss = DataSelectionQueryUtils.getChildLevels(parameters);

            if (resultss != null) {
                resultList = new ArrayList<>();
                for (int loop = 0, limit = resultss.size(); loop < limit; loop++) {
                    Object[] objects = (Object[]) resultss.get(loop);
                    dto = new LevelDTO();
                    dto.setLevelNo(Integer.parseInt(String.valueOf(objects[0])));
                    dto.setRelationshipLevelValue(String.valueOf(objects[1]));
                    dto.setParentNode(String.valueOf(objects[NumericConstants.TWO]));
                    dto.setLevel(String.valueOf(objects[NumericConstants.THREE]));
                    dto.setLevelValueReference(String.valueOf(objects[NumericConstants.FOUR]));
                    dto.setTableName(String.valueOf(objects[NumericConstants.FIVE]));
                    dto.setFieldName(String.valueOf(objects[NumericConstants.SIX]));
                    dto.setRelationshipLevelSid(Integer.parseInt(String.valueOf(objects[NumericConstants.SEVEN])));
                    dto.setHierarchyNo(String.valueOf(objects[NumericConstants.EIGHT]));
                    dto.setRelationShipBuilderId(String.valueOf(objects[NumericConstants.NINE]));
                    if (descriptionMap != null) {
                        dto.setDisplayValue(descriptionMap.get(String.valueOf(objects[NumericConstants.EIGHT])));
                    }
                    resultList.add(dto);
                }
            }
        } catch (Exception ex) {
            LOGGER.error("Error in getChildLevelsWithHierarchyNo :" + ex);
        }
        LOGGER.debug("--End of  getChildLevelsWithHierarchyNo--");
        return resultList;
    }

    public List getFSValue(String relationshipLevelValue, final String fieldName) {
        List list = new ArrayList();
        LOGGER.debug("--Inside getFSValue--");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(ARMUtils.INDICATOR, "getFSValue");
        parameters.put("relationshipLevelValue", relationshipLevelValue);
        parameters.put(ARMUtils.FIELD_NAME, fieldName);
        try {
            list = DataSelectionQueryUtils.executeQuery(parameters);
            LOGGER.debug("--End of getFSValue--");
            return list;
        } catch (Exception e) {
            LOGGER.error(e + "in getFSValue");
            return null;
        }
    }

    /**
     * Used to change the alignment of Selected Product table
     *
     * @param selectedProduct
     * @param selectedProductContainer
     */
    public static void selectedProductTableAlignmentChange(TreeTable selectedProduct, ExtTreeContainer<LevelDTO> selectedProductContainer) {
        LOGGER.debug("--Inside selectedProductTableAlignmentChange--");
        int length = 0;
        for (LevelDTO dto : selectedProductContainer.getItemIds()) {
            if (length < dto.getDisplayValue().length()) {
                length = dto.getDisplayValue().length();
            }
        }
        if (length > NumericConstants.FIFTY) {
            if (length <= NumericConstants.SIXTY) {
                selectedProduct.setColumnWidth(CommonConstant.DISPLAY_VALUE, NumericConstants.FIVE_ONE_ZERO);
            } else if (length <= NumericConstants.SEVENTY) {
                selectedProduct.setColumnWidth(CommonConstant.DISPLAY_VALUE, NumericConstants.FIVE_SEVEN_ZERO);
            } else if (length <= NumericConstants.EIGHTY) {
                selectedProduct.setColumnWidth(CommonConstant.DISPLAY_VALUE, NumericConstants.SIX_THREE_ZERO);
            } else if (length <= NumericConstants.NINETY) {
                selectedProduct.setColumnWidth(CommonConstant.DISPLAY_VALUE, NumericConstants.SEVEN_THREE_ZERO);
            } else if (length <= NumericConstants.HUNDRED) {
                selectedProduct.setColumnWidth(CommonConstant.DISPLAY_VALUE, NumericConstants.EIGHT_ONE_ZERO);
            }
        } else {
            selectedProduct.setColumnWidth(CommonConstant.DISPLAY_VALUE, -1);
        }
        LOGGER.debug("--End of selectedProductTableAlignmentChange--");
    }

    public int searhViewCount(final String viewName, final String viewType, final String adjustmentType, boolean isCount, Set<Container.Filter> filters, List<SortByColumn> sortByColumns) {
        LOGGER.debug("Entering searhViewCount method");
        List list = null;
        final String userId = (String) VaadinSession.getCurrent().getAttribute(ARMUtils.USER_ID);
        String viewValue = StringUtils.EMPTY;
        if (viewName.isEmpty()) {
            viewValue = ARMUtils.CHAR_PERCENT;
        }
        if (StringUtils.isNotBlank(viewName)) {
            viewValue = viewName.replace(ARMUtils.CHAR_ASTERISK, ARMUtils.CHAR_PERCENT);
        }
        if (!"Calculation Profile".equals(adjustmentType)) {
            list = DataSelectionQueryUtils.findViewByName(viewValue, adjustmentType, userId, viewType, isCount, filters, sortByColumns, 0, 0);
        } else {
            list = DataSelectionQueryUtils.loadCalculationViewSearch(viewValue, adjustmentType, userId, viewType, isCount, filters, sortByColumns, 0, 0);
        }
        int count = 0;
        if (list != null && !list.isEmpty()) {
            count = Integer.parseInt(list.get(0) + "");
        }
        LOGGER.debug("End of searhViewCount method");
        return count;
    }

    public List searhView(final String viewName, final String viewType, final String adjustmentType, boolean isCount, Set<Container.Filter> filters, List<SortByColumn> sortByColumns, int start, int offset) {
        LOGGER.debug("Entering searchView method");
        List list = null;
        final String userId = (String) VaadinSession.getCurrent().getAttribute(ARMUtils.USER_ID);
        String viewValue = StringUtils.EMPTY;
        if (StringUtils.isNotBlank(viewName)) {
            viewValue = viewName.replace(ARMUtils.CHAR_ASTERISK, ARMUtils.CHAR_PERCENT);
        }
        if (!"Calculation Profile".equals(adjustmentType)) {
            list = DataSelectionQueryUtils.findViewByName(viewValue, adjustmentType, userId, viewType, isCount, filters, sortByColumns, start, offset);
        } else {
            list = DataSelectionQueryUtils.loadCalculationViewSearch(viewValue, adjustmentType, userId, viewType, isCount, filters, sortByColumns, start, offset);
        }
        LOGGER.debug("End of searchView method");
        return list;
    }

    public List<ViewDTO> getCustomizedViews(final List list) {
        final List<ViewDTO> results = new ArrayList<>();
        LOGGER.debug("Entering getCustomizedViews method with list size  " + list.size());
        Map<Integer, HelperDTO> helperMap = helperListUtil.getIdHelperDTOMap();
        for (int i = 0; i < list.size(); i++) {
            final Object[] obj = (Object[]) list.get(i);
            final ViewDTO result = new ViewDTO();
            result.setProjectionDescription(convertNullToEmpty(String.valueOf(obj[0])));
            result.setAdjustmentId(obj[1] != null ? (int) obj[1] : 0);
            result.setAdjustmentType(convertNullToEmpty(String.valueOf(obj[NumericConstants.TWO])));
            result.setCompanyMasterSid(obj[NumericConstants.THREE] == null ? 0 : (int) obj[NumericConstants.THREE] == 0 ? 0 : (int) obj[NumericConstants.THREE]);
            result.setBucompanyMasterSid(obj[NumericConstants.FOUR] == null || (int) obj[NumericConstants.FOUR] == 0 ? 0 : (int) obj[NumericConstants.FOUR]);
            result.setDeductionLevel(obj[NumericConstants.FIVE] == null || (int) obj[NumericConstants.FIVE] == 0 ? 0 : (int) obj[NumericConstants.FIVE]);
            result.setCustomerHierarchySid(convertNullToEmpty(String.valueOf(obj[NumericConstants.SIX])));
            result.setCustomerHierarchyName(convertNullToEmpty(String.valueOf(obj[NumericConstants.SEVEN])));
            result.setCustRelationshipBuilderSid(convertNullToEmpty(String.valueOf(obj[NumericConstants.EIGHT])));
            result.setCustomerHierarchyLevel(convertNullToEmpty(String.valueOf(obj[NumericConstants.NINE])));
            result.setProductHierarchySid(convertNullToEmpty(String.valueOf(obj[NumericConstants.TEN])));
            result.setProductHierarchyName(convertNullToEmpty(String.valueOf(obj[NumericConstants.ELEVEN])));
            result.setProdRelationshipBuilderSid(convertNullToEmpty(String.valueOf(obj[NumericConstants.TWELVE])));
            result.setProductHierarchyLevel(convertNullToEmpty(String.valueOf(obj[NumericConstants.THIRTEEN])));
            result.setViewName(convertNullToEmpty(String.valueOf(obj[NumericConstants.FOURTEEN])));
            result.setCompanyName(convertNullToEmpty(String.valueOf(obj[NumericConstants.FIFTEEN])));
            result.setProjectionId(convertNullToEmpty(String.valueOf(obj[NumericConstants.SIXTEEN])));
            result.setViewId(convertNullToEmpty(String.valueOf(obj[NumericConstants.SEVENTEEN])));
            result.setCustomerHierVers(obj[NumericConstants.EIGHTEEN] == null || (int) obj[NumericConstants.EIGHTEEN] == 0 ? 0 : (int) obj[NumericConstants.EIGHTEEN]);
            result.setCustomerHierHL(convertNullToEmpty(String.valueOf(obj[NumericConstants.NINETEEN])));
            result.setProductHierVers(obj[NumericConstants.TWENTY] == null || (int) obj[NumericConstants.TWENTY] == 0 ? 0 : (int) obj[NumericConstants.TWENTY]);
            result.setProductHierHL(convertNullToEmpty(String.valueOf(obj[NumericConstants.TWENTY_ONE])));
            String crBy = convertNullToEmpty(String.valueOf(obj[NumericConstants.TWENTY_TWO]));
            result.setViewCreatedBy(StringUtils.isNotBlank(crBy) ? Integer.parseInt(crBy) : 0);
            result.setViewType(convertNullToEmpty(String.valueOf(obj[NumericConstants.TWENTY_THREE])));
            result.setBuCompanyName(convertNullToEmpty(String.valueOf(obj[NumericConstants.TWENTY_FOUR])));
            Date date = (Date) (obj[NumericConstants.TWENTY_FIVE]);
            boolean cond = ARMUtils.DEFAULT_FROM_TO_DATE.equals((ARMUtils.getInstance().getDbDate().format(date)));
            result.setFromPeriod(cond ? null : (Date) (obj[NumericConstants.TWENTY_FIVE]));
            date = (Date) (obj[NumericConstants.TWENTY_SIX]);
            cond = ARMUtils.DEFAULT_FROM_TO_DATE.equals((ARMUtils.getInstance().getDbDate().format(date)));
            result.setToPeriod(cond ? null : (Date) obj[NumericConstants.TWENTY_SIX]);
            result.setCreatedByString((result.getViewCreatedBy() != 0) ? userMap.get(result.getViewCreatedBy()) : StringUtils.EMPTY);
            result.setDeductionLevels((result.getDeductionLevel() != 0) ? helperMap.get(result.getDeductionLevel()) != null ? (helperMap.get(result.getDeductionLevel())).getDescription() : StringUtils.EMPTY : StringUtils.EMPTY);
            result.setCustomerRelationship(convertNullToEmpty(String.valueOf(obj[29])));
            result.setProductRelationship(convertNullToEmpty(String.valueOf(obj[30])));

            result.setCreatedDate((Date) (obj[NumericConstants.TWENTY_SEVEN]));
            result.setModifiedDate((Date) (obj[NumericConstants.TWENTY_EIGHT]));

            results.add(result);

        }
        LOGGER.debug("End of getCustomizedViews method");
        return results;
    }

    public List<ViewDTO> getCalculationCustomizedViews(final List list) {
        final List<ViewDTO> results = new ArrayList<>();
        LOGGER.debug("Entering getCalculationCustomizedViews method with list size  " + list.size());
        for (int i = 0; i < list.size(); i++) {
            final Object[] obj = (Object[]) list.get(i);
            final ViewDTO result = new ViewDTO();
            result.setViewId(CommonLogic.convertNullToEmpty(String.valueOf(obj[0])));
            result.setViewName(CommonLogic.convertNullToEmpty(String.valueOf(obj[1])));
            result.setViewType(CommonLogic.convertNullToEmpty(String.valueOf(obj[2])));
            String crBy = CommonLogic.convertNullToEmpty(String.valueOf(obj[6]));
            result.setViewCreatedBy(StringUtils.isNotBlank(crBy) ? Integer.parseInt(crBy) : 0);
            result.setCreatedByString((result.getViewCreatedBy() != 0) ? userMap.get(result.getViewCreatedBy()) : StringUtils.EMPTY);
            result.setCreatedDate(obj[3] != null ? (Date) (obj[3]) : null);
            result.setModifiedDate(obj[4] != null ? (Date) (obj[4]) : null);

            results.add(result);

        }
        LOGGER.debug("End of getCalculationCustomizedViews method");
        return results;
    }

    public static String getUserFLName(String userId) throws PortalException, SystemException {
        String name = StringUtils.EMPTY;
        User userInfo = CommonLogic.getUser(userId);
        if (userInfo != null) {
            name = userInfo.getLastName() + ", " + userInfo.getFirstName();
        }
        return name;
    }

    public List loadAvailableDeductions(HelperDTO valueLevel) {
        LOGGER.debug("Entering loadAvailableDeductions method");
        List<DeductionLevelDTO> resultList = new ArrayList<>();
        String sqlQuery = SQlUtil.getQuery("getDeductionAvailableLevels");
        String[] select = ARMUtils.getLevelMap(valueLevel.getDescription());

        sqlQuery = sqlQuery.replace("@PARAMETERS", select[0]);
        if ("UDC".equalsIgnoreCase(select[1])) {
            sqlQuery = sqlQuery.replace("--@UDCS", CommonConstant.AND + select[NumericConstants.TWO]);
        }
        sqlQuery = sqlQuery + select[1];
        LOGGER.debug(CommonConstant.SQL_QUERY + sqlQuery);
        List<Object[]> list = QueryUtils.executeSelect(sqlQuery);
        for (Object[] obj : list) {
            DeductionLevelDTO level = new DeductionLevelDTO();
            int id = obj[0] == null ? 0 : (int) obj[0];
            String desc = obj[1] == null ? "0" : obj[1] + "";
            level.setLevelSid(id);
            level.setLevelValue(desc);
            level.setLevelName(valueLevel.getDescription());
            resultList.add(level);
        }
        LOGGER.debug("Entering loadAvailableDeductions method");
        return resultList;
    }
    public void saveCustomerHierarchyLogic(final List<LevelDTO> levelList, final List<String> endLevelSids, final int projectionId, final List<String> addLevels, final String indicator) {
        LOGGER.debug("saveCustomerHierarchyLogic endLevelSids size:" + endLevelSids.size() + " projectionId " + projectionId);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(ARMUtils.INDICATOR, "getChildLevelRLSid");
        parameters.put(ARMUtils.PROJECTION_ID, projectionId);
        parameters.put("rlSids", endLevelSids);
        parameters.put("tableName", "PROJECTION_CUST_HIERARCHY");
        List<Object> endLevels = null;
        if (!endLevelSids.isEmpty()) {
            endLevels = DataSelectionQueryUtils.executeQuery(parameters);
        }
        String sbQuery = SQlUtil.getQuery("insertcustHierarchy");
        StringBuilder values = new StringBuilder(StringUtils.EMPTY);
        try {
            if (endLevels != null && !endLevels.isEmpty()) {
                for (Object relationshipLevelSid : endLevels) {
                    if (values.length() == 0) {
                        values.append("(").append(projectionId).append(ARMUtils.COMMA).append(CommonLogic.parseStringToInteger(String.valueOf(relationshipLevelSid))).append(")");
                    } else {
                        values.append(ARMUtils.COMMA).append(" (").append(projectionId).append(ARMUtils.COMMA).append(CommonLogic.parseStringToInteger(String.valueOf(relationshipLevelSid))).append(")");
                    }
                }
            }
            if (ARMUtils.UPDATE.equals(indicator)) {
                for (String rsId : addLevels) {
                    if (values.length() == 0) {
                        values.append("(").append(projectionId).append(ARMUtils.COMMA).append(CommonLogic.parseStringToInteger(String.valueOf(rsId))).append(")");

                    } else {
                        values.append(ARMUtils.COMMA).append(" (").append(projectionId).append(ARMUtils.COMMA).append(CommonLogic.parseStringToInteger(String.valueOf(rsId))).append(")");

                    }
                }
            } else if (ARMUtils.SAVE.equals(indicator)) {
                for (LevelDTO dto : levelList) {
                    if (values.length() == 0) {
                        values.append("(").append(projectionId).append(ARMUtils.COMMA).append(dto.getRelationshipLevelSid()).append(")");

                    } else {
                        values.append(ARMUtils.COMMA).append(" (").append(projectionId).append(ARMUtils.COMMA).append(dto.getRelationshipLevelSid()).append(")");

                    }
                }
            }
            sbQuery = sbQuery.replace("$$$$VALUES$$$", values.toString());
            LOGGER.debug("sbQuery--" + sbQuery);
            if (values.length() != 0) {
                HelperTableLocalServiceUtil.executeUpdateQuery(sbQuery);
            }
        } catch (Exception e) {
            LOGGER.error(e + " in saveCustomerHierarchyLogic");
        }
    }

    public void saveAdjustmentMaster(int projectionId, DataSelectionDTO dataSelectionDTO) {
        LOGGER.debug("--Inside saveAdjustmentMaster--" + projectionId);
        try {
            if (projectionId != 0) {
                String saveAdjusMasQuery = SQlUtil.getQuery("ARM_ADJUSTMENT_MASTER_SAVED_PROJECTION");
                saveAdjusMasQuery = saveAdjusMasQuery.replace(CommonConstant.PROJECTION_MASTER_SID, String.valueOf(projectionId));
                saveAdjusMasQuery = saveAdjusMasQuery.replace("@BU_COMPANY_MASTER_SID", dataSelectionDTO.getBucompanyMasterSid() == 0 ? String.valueOf(dataSelectionDTO.getDefaultCompanyMasterSid()) : String.valueOf(dataSelectionDTO.getBucompanyMasterSid()));
                saveAdjusMasQuery = saveAdjusMasQuery.replace("@DEDUCTION_LEVEL", String.valueOf(dataSelectionDTO.getDeductionLevel()));
                saveAdjusMasQuery = saveAdjusMasQuery.replace("@ADJ_TYPE", dataSelectionDTO.getAdjustmentId() == 0 ? ARMUtils.NULL : String.valueOf(dataSelectionDTO.getAdjustmentId()));
                saveAdjusMasQuery = saveAdjusMasQuery.replace(CommonConstant.CALCULATION_PROFILE_MASTER_SID, "''");
                HelperTableLocalServiceUtil.executeUpdateQuery(saveAdjusMasQuery);
            }
        } catch (Exception e) {
            LOGGER.error(e + " in saveAdjustmentMaster");
        }
    }

    public void saveProductHierarchyLogic(final List<LevelDTO> levelList, final List<String> endLevelSids, final int projectionId, final List<String> addLevels, final String indicator) {
        LOGGER.debug("saveProductHierarchyLogic endLevelSids size:" + endLevelSids.size() + " projectionId " + projectionId);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(ARMUtils.INDICATOR, "getChildLevelRLSid");
        parameters.put("rlSids", endLevelSids);
        parameters.put(ARMUtils.PROJECTION_ID, projectionId);
        parameters.put("tableName", "PROJECTION_PROD_HIERARCHY");
        List<Object> endLevels = null;
        if (!endLevelSids.isEmpty()) {
            endLevels = DataSelectionQueryUtils.executeQuery(parameters);
        }
        String sbQuery = SQlUtil.getQuery("insertProdHierarchy");
        StringBuilder values = new StringBuilder(StringUtils.EMPTY);
        try {
            if (ARMUtils.UPDATE.equals(indicator)) {
                for (String rsId : addLevels) {
                    if (values.length() == 0) {
                        values.append("(").append(projectionId).append(ARMUtils.COMMA).append(CommonLogic.parseStringToInteger(String.valueOf(rsId))).append(")");

                    } else {
                        values.append(ARMUtils.COMMA).append(" (").append(projectionId).append(ARMUtils.COMMA).append(CommonLogic.parseStringToInteger(String.valueOf(rsId))).append(")");

                    }
                }
            } else if (ARMUtils.SAVE.equals(indicator)) {
                for (LevelDTO dto : levelList) {
                    if (values.length() == 0) {
                        values.append("(").append(projectionId).append(ARMUtils.COMMA).append(dto.getRelationshipLevelSid()).append(")");

                    } else {
                        values.append(ARMUtils.COMMA).append(" (").append(projectionId).append(ARMUtils.COMMA).append(dto.getRelationshipLevelSid()).append(")");

                    }
                }
            }
            if (endLevels != null && !endLevels.isEmpty()) {
                for (Object relationshipLevelSid : endLevels) {
                    if (values.length() == 0) {
                        values.append("(").append(projectionId).append(ARMUtils.COMMA).append(CommonLogic.parseStringToInteger(String.valueOf(relationshipLevelSid))).append(")");

                    } else {
                        values.append(ARMUtils.COMMA).append(" (").append(projectionId).append(ARMUtils.COMMA).append(CommonLogic.parseStringToInteger(String.valueOf(relationshipLevelSid))).append(")");

                    }
                }
            }
            sbQuery = sbQuery.replace("$$$$VALUES$$$", values);
            LOGGER.debug("sbQuery --" + sbQuery);
            if (values.length() != 0) {
                HelperTableLocalServiceUtil.executeUpdateQuery(sbQuery);
            }
        } catch (Exception e) {
            LOGGER.debug(e + " saveProductHierarchyLogic");
        }
    }

    public void saveViewLogic(SaveViewDTO viewDTO) {
        try {
            String viewQuery = "Insert into dbo.FORECASTING_VIEW_MASTER (VIEW_NAME,VIEW_TYPE,CREATED_BY,CREATED_DATE,PROJECTION_ID)\n"
                    + "Values('@VIEW_NAME','@VIEW_TYPE','@CREATED_BY','@CREATED_DATE',@PROJECTION_ID)";
            viewQuery = viewQuery.replace("@VIEW_NAME", viewDTO.getViewName());
            viewQuery = viewQuery.replace("@VIEW_TYPE", viewDTO.getViewType());
            viewQuery = viewQuery.replace("@CREATED_BY", String.valueOf(viewDTO.getCreatedBy()));
            viewQuery = viewQuery.replace("@CREATED_DATE", viewDTO.getCreatedDate());
            viewQuery = viewQuery.replace("@PROJECTION_ID",String.valueOf(viewDTO.getProjectionSid()));
            LOGGER.debug("viewQuery --" + viewQuery);
            HelperTableLocalServiceUtil.executeUpdateQuery(viewQuery);
        } catch (Exception ex) {
            LOGGER.debug(ex + " saveViewLogic");
        }

    }

    public void saveDeductionLogic(Set<Integer> rsModelSidList, int projectionSid) {
        LOGGER.debug("Inside saveDeductionLogic --projectionSid --" + projectionSid + "--rsModelSidList--" + rsModelSidList);
        try {
            StringBuilder insertDeductionQuery = new StringBuilder("");
            if (!rsModelSidList.isEmpty()) {
                insertDeductionQuery.append(SQlUtil.getQuery("DEDUCTION_SAVED_PROJECTION"));
                for (Integer rsModelSid : rsModelSidList) {
                    insertDeductionQuery.append("(").append(projectionSid).append(",").append(rsModelSid).append("),");
                }
                insertDeductionQuery.replace(insertDeductionQuery.length() - 1, insertDeductionQuery.length(), "");
            }
            LOGGER.debug("insertDeductionQuery --" + insertDeductionQuery);
            HelperTableLocalServiceUtil.executeUpdateQuery(insertDeductionQuery.toString());
        } catch (Exception ex) {
            LOGGER.debug(ex + " save Deduction Logic");
        }

    }

    public void saveCcp(final String tempTableName, final String projectionId) throws Exception {
        LOGGER.debug("Inside saveCcp--" + projectionId);
        String query = StringUtils.EMPTY;
        try {
            String sqlQuery = SQlUtil.getQuery("ccpMergeQuery");

            query = sqlQuery;
            query = query.replace("[$ST_CCP_HIERARCHY]", tempTableName);
            query = query.replace("[$PROJECTION_MASTER_SID]", projectionId);
            LOGGER.debug("query --" + query);
            HelperTableLocalServiceUtil.executeUpdateQuery(query);
        } catch (Exception e) {
            LOGGER.error("Error in saveCcp :" + e);
            LOGGER.error(query);
        }
    }

    public void updateSavedViewProjection(DataSelectionDTO dto) {
        try {
            String updateQuery = SQlUtil.getQuery("updateProjectionMaster");
            updateQuery = updateQuery.replace("@PROJECTION_DESCRIPTION", "'" + dto.getProjectionDescription() + "'");
            updateQuery = updateQuery.replace("@FORECASTING_TYPE", "'" + dto.getForecastingType() + "'");
            updateQuery = updateQuery.replace("@COMPANY_MASTER_SID", dto.getCompanyMasterSid() == 0 ? ARMUtils.NULL_CAPS : "'" + dto.getCompanyMasterSid() + "'");
            updateQuery = updateQuery.replace("@CUSTOMER_HIERARCHY_SID", dto.getCustomerHierarchySid() == 0 ? ARMUtils.NULL_CAPS : "'" + dto.getCustomerHierarchySid() + "'");
            updateQuery = updateQuery.replace("@PRODUCT_HIERARCHY_SID", dto.getProductHierarchySid() == 0 ? ARMUtils.NULL_CAPS : "'" + dto.getProductHierarchySid() + "'");
            updateQuery = updateQuery.replace("@CUSTOMER_HIERARCHY_LEVEL", "0".equals(dto.getCustomerHierarchyLevel()) ? ARMUtils.NULL_CAPS : "'" + dto.getCustomerHierarchyLevel() + "'");
            updateQuery = updateQuery.replace("@PRODUCT_HIERARCHY_LEVEL", "0".equals(dto.getProductHierarchyLevel()) ? ARMUtils.NULL_CAPS : "'" + dto.getProductHierarchyLevel() + "'");
            updateQuery = updateQuery.replace("@CUST_RELATIONSHIP_BUILDER_SID", dto.getCustRelationshipBuilderSid() == 0 ? ARMUtils.NULL_CAPS : "'" + dto.getCustRelationshipBuilderSid() + "'");
            updateQuery = updateQuery.replace("@PROD_RELATIONSHIP_BUILDER_SID", dto.getProdRelationshipBuilderSid() == 0 ? ARMUtils.NULL_CAPS : "'" + dto.getProdRelationshipBuilderSid() + "'");
            updateQuery = updateQuery.replace("@FROM_DATE", dto.getFromDate() == null  ? "''" : "'" + ARMUtils.getInstance().getDateFormatter().format(dto.getFromDate()) + "'");
            updateQuery = updateQuery.replace("@TO_DATE", dto.getToDate() == null ?  "''" :  "'" +ARMUtils.getInstance().getDateFormatter().format(dto.getToDate()) + "'");
            updateQuery = updateQuery.replace(CommonConstant.PROJECTION_MASTER_SID, String.valueOf(dto.getProjectionId()));
            LOGGER.debug("updateQuery --" + updateQuery);
            HelperTableLocalServiceUtil.executeUpdateQuery(updateQuery);
        } catch (Exception ex) {
            LOGGER.error("Error in updateSavedViewProjection :" + ex);
        }
    }

    public int saveProjection(final DataSelectionDTO dataSelectionDTO) {
        LOGGER.debug("Inside saveProjection -- " + dataSelectionDTO.getProjectionId());
        String finalQuery = SQlUtil.getQuery("insertProjectionMaster");
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("'").append(dataSelectionDTO.getProjectionName()).append("'").append(ARMUtils.COMMA);
        queryBuilder.append("'").append(dataSelectionDTO.getProjectionDescription()).append("'").append(ARMUtils.COMMA);
        queryBuilder.append("'").append(dataSelectionDTO.getForecastingType()).append("'").append(ARMUtils.COMMA);
        queryBuilder.append(dataSelectionDTO.getCreatedBy()).append(ARMUtils.COMMA);
        queryBuilder.append("'").append(new Timestamp(new Date().getTime())).append("'").append(ARMUtils.COMMA);
        queryBuilder.append(dataSelectionDTO.getCustomerHierarchySid() == 0 ? ARMUtils.NULL_CAPS : dataSelectionDTO.getCustomerHierarchySid()).append(ARMUtils.COMMA);
        queryBuilder.append(dataSelectionDTO.getProductHierarchySid() == 0 ? ARMUtils.NULL_CAPS : dataSelectionDTO.getProductHierarchySid()).append(ARMUtils.COMMA);
        queryBuilder.append(dataSelectionDTO.getCustomerHierarchyLevel().equals("0") ? ARMUtils.NULL_CAPS : dataSelectionDTO.getCustomerHierarchyLevel()).append(ARMUtils.COMMA);
        queryBuilder.append(dataSelectionDTO.getProductHierarchyLevel().equals("0") ? ARMUtils.NULL_CAPS : dataSelectionDTO.getProductHierarchyLevel()).append(ARMUtils.COMMA);
        queryBuilder.append(dataSelectionDTO.getCustomerHierarchyVersionNo() == 0 ? ARMUtils.NULL_CAPS : dataSelectionDTO.getCustomerHierarchyVersionNo()).append(ARMUtils.COMMA);
        queryBuilder.append(dataSelectionDTO.getProductHierarchyVersionNo() == 0 ? ARMUtils.NULL_CAPS : dataSelectionDTO.getProductHierarchyVersionNo()).append(ARMUtils.COMMA);
        queryBuilder.append(dataSelectionDTO.getCompanyMasterSid() == 0 ? ARMUtils.NULL_CAPS : dataSelectionDTO.getCompanyMasterSid()).append(ARMUtils.COMMA);
        queryBuilder.append("'").append(ARMUtils.NULL.equals(String.valueOf(dataSelectionDTO.getFromPeriod())) ? ARMUtils.DEFAULT_FROM_TO_DATE : dataSelectionDTO.getFromPeriod()).append("'").append(ARMUtils.COMMA);
        queryBuilder.append("'").append(ARMUtils.NULL.equals(String.valueOf(dataSelectionDTO.getToPeriod())) ? ARMUtils.DEFAULT_FROM_TO_DATE : dataSelectionDTO.getToPeriod()).append("'").append(ARMUtils.COMMA);
        queryBuilder.append("'").append(dataSelectionDTO.getSaveFlag()).append("'").append(ARMUtils.COMMA);
        queryBuilder.append(dataSelectionDTO.getCustRelationshipBuilderSid() == 0 ? ARMUtils.NULL_CAPS : dataSelectionDTO.getCustRelationshipBuilderSid()).append(ARMUtils.COMMA);
        queryBuilder.append(dataSelectionDTO.getProdRelationshipBuilderSid() == 0 ? ARMUtils.NULL_CAPS : dataSelectionDTO.getProdRelationshipBuilderSid());
        finalQuery = finalQuery.replace("$$$$VALUE$$$$", queryBuilder.toString());
        LOGGER.debug("finalQuery - -" + finalQuery);
        List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(finalQuery);
        return Integer.valueOf(list.get(0) + "");
    }
    public int saveProjectionForView(final DataSelectionDTO dataSelectionDTO) {
        LOGGER.debug("Inside saveProjection -- " + dataSelectionDTO.getProjectionId());
        String finalQuery = SQlUtil.getQuery("insertProjectionMaster");
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("'").append(dataSelectionDTO.getProjectionName()).append("'").append(ARMUtils.COMMA);
        queryBuilder.append("'").append(dataSelectionDTO.getProjectionDescription()).append("'").append(ARMUtils.COMMA);
        queryBuilder.append("'").append(dataSelectionDTO.getForecastingType()).append("'").append(ARMUtils.COMMA);
        queryBuilder.append(dataSelectionDTO.getCreatedBy()).append(ARMUtils.COMMA);
        queryBuilder.append("'").append(new Timestamp(new Date().getTime())).append("'").append(ARMUtils.COMMA);
        queryBuilder.append(dataSelectionDTO.getCustomerHierarchySid() == 0 ? ARMUtils.NULL_CAPS : dataSelectionDTO.getCustomerHierarchySid()).append(ARMUtils.COMMA);
        queryBuilder.append(dataSelectionDTO.getProductHierarchySid() == 0 ? ARMUtils.NULL_CAPS : dataSelectionDTO.getProductHierarchySid()).append(ARMUtils.COMMA);
        queryBuilder.append(dataSelectionDTO.getCustomerHierarchyLevel().equals("0") ? ARMUtils.NULL_CAPS : dataSelectionDTO.getCustomerHierarchyLevel()).append(ARMUtils.COMMA);
        queryBuilder.append(dataSelectionDTO.getProductHierarchyLevel().equals("0") ? ARMUtils.NULL_CAPS : dataSelectionDTO.getProductHierarchyLevel()).append(ARMUtils.COMMA);
        queryBuilder.append(dataSelectionDTO.getCustomerHierarchyVersionNo() == 0 ? ARMUtils.NULL_CAPS : dataSelectionDTO.getCustomerHierarchyVersionNo()).append(ARMUtils.COMMA);
        queryBuilder.append(dataSelectionDTO.getProductHierarchyVersionNo() == 0 ? ARMUtils.NULL_CAPS : dataSelectionDTO.getProductHierarchyVersionNo()).append(ARMUtils.COMMA);
        queryBuilder.append(dataSelectionDTO.getCompanyMasterSid() == 0 ? ARMUtils.NULL_CAPS : dataSelectionDTO.getCompanyMasterSid()).append(ARMUtils.COMMA);
        queryBuilder.append("'").append(ARMUtils.NULL.equals(String.valueOf(dataSelectionDTO.getFromDate())) ? ARMUtils.DEFAULT_FROM_TO_DATE : ARMUtils.getInstance().getDateFormatter().format(dataSelectionDTO.getFromDate())).append("'").append(ARMUtils.COMMA);
        queryBuilder.append("'").append(ARMUtils.NULL.equals(String.valueOf(dataSelectionDTO.getToDate())) ? ARMUtils.DEFAULT_FROM_TO_DATE : ARMUtils.getInstance().getDateFormatter().format(dataSelectionDTO.getToDate())).append("'").append(ARMUtils.COMMA);
        queryBuilder.append("'").append(dataSelectionDTO.getSaveFlag()).append("'").append(ARMUtils.COMMA);
        queryBuilder.append(dataSelectionDTO.getCustRelationshipBuilderSid() == 0 ? ARMUtils.NULL_CAPS : dataSelectionDTO.getCustRelationshipBuilderSid()).append(ARMUtils.COMMA);
        queryBuilder.append(dataSelectionDTO.getProdRelationshipBuilderSid() == 0 ? ARMUtils.NULL_CAPS : dataSelectionDTO.getProdRelationshipBuilderSid());
        finalQuery = finalQuery.replace("$$$$VALUE$$$$", queryBuilder.toString());
        LOGGER.debug("finalQuery  --" + finalQuery);
        List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(finalQuery);
        return Integer.valueOf(list.get(0) + "");
    }

    public void saveAdjustmentMaster(DataSelectionDTO dataSelectionDTO) {
        LOGGER.debug("Inside saveAdjustmentMaster -- " + dataSelectionDTO.getProjectionId());
        String sqlQuery = SQlUtil.getQuery("insertArmAdjustmentMaster");
        String values = dataSelectionDTO.getProjectionId() + ARMUtils.COMMA
                + dataSelectionDTO.getBucompanyMasterSid() + ARMUtils.COMMA
                + dataSelectionDTO.getDeductionLevel() + ARMUtils.COMMA
                + ((dataSelectionDTO.getAdjustmentId() == 0) ? null : dataSelectionDTO.getAdjustmentId());
        LOGGER.debug(CommonConstant.SQL_QUERY + sqlQuery);
        sqlQuery = sqlQuery.replace("$$$$VALUES$$$$", values);
        HelperTableLocalServiceUtil.executeUpdateQuery(sqlQuery);
    }

    public boolean isDuplicateView(String viewName, String viewType, DataSelectionDTO dataSelectionDTO) {
        LOGGER.debug("Inside isDuplicateView -- " + viewName);
        try {
            String sqlQuery = SQlUtil.getQuery("duplicateViewCheck");
            sqlQuery = sqlQuery.replace("$$$VN$$$", viewName);
            sqlQuery = sqlQuery.replace("@VIEW_TYPE", viewType);
            LOGGER.info("dataSelectionDTO.getForecastingType()------" + dataSelectionDTO.getForecastingType());
            sqlQuery = sqlQuery.replace("@VIEWMODE", "Balance Summary Report".equals(dataSelectionDTO.getForecastingType()) 
                    ? "'Balance Summary Report'" : "'Pipeline Accrual','Demand Accrual','Pipeline Inventory True-Up','Demand Payments Recon','Demand Reforecast True-up','ARM'");
            LOGGER.info(CommonConstant.SQL_QUERY + sqlQuery);
            List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(sqlQuery);
            return !list.isEmpty();
        } catch (Exception ex) {
            LOGGER.error("Error in View Name Duplicated" + ex);
            return true;
        }
    }

    public DataSelectionDTO getViewDetails(String projectionId) {
        LOGGER.debug("Inside getViewDetails -- " + projectionId);
        String sqlQuery = SQlUtil.getQuery("FETCH_SAVED_PROJECTION");
        sqlQuery = sqlQuery.replace("[$PROJECTION_MASTER_SID]", projectionId);
        LOGGER.debug(CommonConstant.SQL_QUERY + sqlQuery);
        List<Object[]> list = HelperTableLocalServiceUtil.executeSelectQuery(sqlQuery);
        list = list == null ? Collections.EMPTY_LIST : list;
        return customizeViewDetails(list);
    }

    public DataSelectionDTO customizeViewDetails(List<Object[]> list) {
        DataSelectionDTO dto = new DataSelectionDTO();
        if (list.size() == 1) {
            Object[] obj = list.get(0);
            int index = 0;
            dto.setProjectionDescription(obj[index] == null ? StringUtils.EMPTY : obj[index++].toString());
            dto.setForecastingType(obj[index] == null ? StringUtils.EMPTY : obj[index++].toString());
            dto.setCompanyMasterSid(obj[index] == null ? 0 : (int) obj[index++]);
            dto.setBucompanyMasterSid(obj[index] == null ? 0 : (int) obj[index++]);
            dto.setDeductionLevel(obj[index] == null ? 0 : (int) obj[index++]);
            dto.setCustomerHierarchySid(obj[index] == null ? 0 : (int) obj[index++]);
            dto.setCustomerHierarchyName(obj[index] == null ? StringUtils.EMPTY : obj[index++].toString());
            dto.setCustRelationshipBuilderSid(obj[index] == null ? 0 : (int) obj[index++]);
            dto.setCustomerHierarchyLevel(obj[index] == null ? StringUtils.EMPTY : obj[index++].toString());
            dto.setProductHierarchySid(obj[index] == null ? 0 : (int) obj[index++]);
            dto.setProductHierarchyName(obj[index] == null ? StringUtils.EMPTY : obj[index++].toString());
            dto.setProdRelationshipBuilderSid(obj[index] == null ? 0 : (int) obj[index++]);
            dto.setProductHierarchyLevel(obj[index] == null ? StringUtils.EMPTY : obj[index++].toString());
            LOGGER.debug("index " + index);
        }
        return dto;
    }

    /**
     * Gets the relation ship values.
     *
     * @param projectionId
     * @param indicator
     * @return the relation ship values
     */
    public List getRelationShipValues(final int projectionId, final String indicator, final Object levelNo, final Map<String, String> descriptionMap) {
        LOGGER.debug("Inside getRelationShipValues ==>" + projectionId + "--indicator --" + indicator + "descriptionMap" + descriptionMap.size());
        List resultss;
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("projectionId", projectionId);
        parameters.put("indicator", indicator);
        if (levelNo == null) {
            parameters.put("restrictLevelNumber", null);
            parameters.put("levelNo", null);
        } else {
            parameters.put("restrictLevelNumber", Boolean.TRUE);
            parameters.put("levelNo", String.valueOf(levelNo));
        }

        List<LevelDTO> resultList = new ArrayList<>();
        LevelDTO dto;

        try {
            resultss = ProjectionMasterLocalServiceUtil.getRelationShipValues(parameters);
            for (int loop = 0, limit = resultss.size(); loop < limit; loop++) {
                dto = new LevelDTO();
                Object[] objects = (Object[]) resultss.get(loop);
                dto.setLevelNo(Integer.parseInt(String.valueOf(objects[0])));
                dto.setRelationshipLevelValue(String.valueOf(objects[1]));
                dto.setParentNode(String.valueOf(objects[NumericConstants.TWO]));
                dto.setLevel(String.valueOf(objects[NumericConstants.THREE]));
                dto.setRelationshipLevelSid(Integer.parseInt(String.valueOf(objects[NumericConstants.FOUR])));
                dto.setTableName(String.valueOf(objects[NumericConstants.FIVE]));
                dto.setFieldName(String.valueOf(objects[NumericConstants.SIX]));
                dto.setHierarchyNo(String.valueOf(objects[NumericConstants.SEVEN]));
                if (objects[NumericConstants.FIVE] != null && !StringUtils.isEmpty(String.valueOf(objects[NumericConstants.FIVE])) && !StringUtils.isBlank(String.valueOf(objects[NumericConstants.FIVE]))) {
                    if ("COMPANY_MASTER".equalsIgnoreCase(String.valueOf(objects[NumericConstants.FIVE]))) {
                        dto.setFromCompany(true);
                    } else if ("CONTRACT_MASTER".equalsIgnoreCase(String.valueOf(objects[NumericConstants.FIVE]))) {
                        dto.setFromContract(true);
                    } else if ("ITEM_MASTER".equalsIgnoreCase(String.valueOf(objects[NumericConstants.FIVE]))) {
                        dto.setFromItem(true);
                    } else {
                        dto.setFromCompany(false);
                        dto.setFromContract(false);
                        dto.setFromItem(false);
                    }
                }

                dto.setDisplayValue(descriptionMap.get(String.valueOf(objects[NumericConstants.SEVEN])));
                LOGGER.debug("End of getRelationShipValues :: resultList ==>" + resultList.size());
                resultList.add(dto);
            }
        } catch (Exception ex) {
            LOGGER.error(ex + " in getRelationShipValues");
        }
        return resultList;
    }

    public List<Integer> getRSContractMasterSid(int projectionSid) {
        List<Integer> rsModelSid = new ArrayList<>();
        try {
            String rsQuery = "Select Distinct RS_CONTRACT_SID from ARM_DEDUCTION_SELECTION where PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID ;";
            rsQuery = rsQuery.replace(CommonConstant.PROJECTION_MASTER_SID, String.valueOf(projectionSid));
            LOGGER.debug("rsQuery --" + rsQuery + " in getRSContractMasterSid");
            List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(rsQuery);
            for (int i = 0; i < list.size(); i++) {
                rsModelSid.add((Integer) list.get(i));
            }
            LOGGER.debug("rsModelSid --" + rsModelSid.size());
            return rsModelSid;
        } catch (Exception e) {
            LOGGER.error("Error in getRSContractMasterSid :" + e);
            return Collections.emptyList();
        }
    }

    public void updateSaveViewLogic(DataSelectionDTO dtoValue) {
        LOGGER.debug("--Inside updateSaveViewLogic --");
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        try {
            String updateARMAdjustQuery = SQlUtil.getQuery("updateSelectedView");
            updateARMAdjustQuery = updateARMAdjustQuery.replace("@DEDUCTION_LEVEL", String.valueOf(dtoValue.getDeductionLevel()));
            updateARMAdjustQuery = updateARMAdjustQuery.replace("@ADJ_TYPE", String.valueOf(dtoValue.getAdjustmentId()));
            updateARMAdjustQuery = updateARMAdjustQuery.replace(CommonConstant.CALCULATION_PROFILE_MASTER_SID, "");
            updateARMAdjustQuery = updateARMAdjustQuery.replace("@BU_COMPANY_MASTER_SID", dtoValue.getBucompanyMasterSid() == 0 ? String.valueOf(dtoValue.getDefaultCompanyMasterSid()) : String.valueOf(dtoValue.getBucompanyMasterSid()));
            updateARMAdjustQuery = updateARMAdjustQuery.replace(CommonConstant.PROJECTION_MASTER_SID, String.valueOf(dtoValue.getProjectionId()));
            updateARMAdjustQuery = updateARMAdjustQuery.replace("@MODIFIED_BY", String.valueOf(dtoValue.getCreatedBy()));
            updateARMAdjustQuery = updateARMAdjustQuery.replace("@VIEW_ID", dtoValue.getViewId());
            updateARMAdjustQuery = updateARMAdjustQuery.replace("@MODIFIED_DATE", dateFormat.format(new Date()));
            LOGGER.debug("updateARMAdjustQuery --" + updateARMAdjustQuery);
            HelperTableLocalServiceUtil.executeUpdateQuery(updateARMAdjustQuery);
            LOGGER.debug("--Exit updateSaveViewLogic --");
        } catch (Exception ex) {
            LOGGER.error(ex + " in updateSaveViewLogic");
        }

    }

    public boolean deleteViewLogic(int projectionId) {
        LOGGER.debug("--Inside deleteViewLogic --");
        String query = SQlUtil.getQuery("deletePrivatePublicViews");
        query = query.replace(CommonConstant.PROJECTION_MASTER_SID, String.valueOf(projectionId));
        LOGGER.debug("--query --" + query);
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
        LOGGER.debug("--Exit deleteViewLogic --");
        return true;
    }
    protected static final String[] MONTHS = new DateFormatSymbols().getShortMonths();

    public String getDuplicateFilters(LevelDTO dedval, Map<Integer, String[]> deductionMap, List<Integer> rsContractSidList) {
        LOGGER.debug("--Inside getDuplicateFilters --");
        String rsContractIdFilters;
        rsContractIdFilters = SQlUtil.getQuery("getDeductionAvailableLevelsWithWhere");
        rsContractIdFilters = rsContractIdFilters.replace("@PARAMETERS", " RS_CONTRACT_SID ");
        String filter = ARMUtils.getLevelMap(deductionMap.get(1)[1])[0] + CommonConstant.IN + dedval.getRsCategory() + "') AND "
                + ARMUtils.getLevelMap(deductionMap.get(NumericConstants.TWO)[1])[0] + " in ( '" + dedval.getRsType() + "' ) AND "
                + ARMUtils.getLevelMap(deductionMap.get(NumericConstants.THREE)[1])[0] + CommonConstant.IN + dedval.getRsProg() + "')"
                + "AND RS_CONTRACT_SID " + CommonConstant.IN + dedval.getActualValue() + "')";
        rsContractIdFilters = rsContractIdFilters.replace("$$$FILTER$$$", filter);
        List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(rsContractIdFilters);
        for (Object obj : list) {
            rsContractSidList.add((int) obj);
        }

        String retLitString = " RS_CONTRACT_SID IN (" + StringUtils.join(list.toArray(), ",") + ") ";
        LOGGER.debug("--Exit getDuplicateFilters --" + retLitString);
        return retLitString;
    }

    public Map<String, DeductionLevelDTO> getDeductionTree(Map<String, Set<Integer>> map, Set<Integer> rsContractSids, List<String> hierarchyKeys) {
        LOGGER.debug("--Inside getDeductionTree --");
        try {
            String query = SQlUtil.getQuery("LoadSelectedDeductions");
            String rsContractQuery = SQlUtil.getQuery("LoadSelectedRSContract");
            if (!map.isEmpty()) {
                StringBuilder filterQuery = new StringBuilder("");

                Map<String, String> filterSelect = ARMUtils.getDeductionValuesMapForLevel();
                for (Map.Entry<String, Set<Integer>> entrySet : map.entrySet()) {
                    String key = entrySet.getKey();
                    Set<Integer> value = entrySet.getValue();
                    filterQuery.append(filterSelect.get(key));
                    filterQuery.append(" IN ").append(value.toString().replace(", ", "','").replace("[", "('").replace("]", "')"));
                }
                map.clear();
                rsContractQuery += filterQuery.toString();
                List<Object> returnList = QueryUtils.executeSelect(rsContractQuery);
                rsContractSids.addAll(Arrays.asList(Arrays.copyOf(returnList.toArray(), returnList.size(), Integer[].class)));
            }
            if (rsContractSids.isEmpty()) {
                query = query.replace("@RS_CONTRACT_SID", rsContractSids.toString().replace("[", "'").replace("]", "'"));
            } else {
                query = query.replace("@RS_CONTRACT_SID", rsContractSids.toString().replace("[", "").replace("]", ""));
            }
            LOGGER.debug("--query --" + query);
            List<Object[]> returnQueryList = QueryUtils.executeSelect(query);
            LOGGER.debug("--Exit getDeductionTree --");
            return cutomizeSelectedDecuction(returnQueryList, hierarchyKeys);
        } catch (Exception ex) {
            LOGGER.error("Error in getDeductionTree :" + ex);
            return Collections.emptyMap();
        }

    }

    private Map<String, DeductionLevelDTO> cutomizeSelectedDecuction(List<Object[]> returnList, List<String> hierarchyKeys) {
        LOGGER.debug("--Inside cutomizeSelectedDecuction --");
        try {
            Map<String, String> parentLevelKeys = new HashMap<>();
            Map<String, DeductionLevelDTO> levelKeys = new HashMap<>();
            Map<String, Integer> columnIncrementer = new HashMap<>();
            for (Object[] obj : returnList) {
                String parentKey = "";
                for (int keyIndex = 1; keyIndex <= NumericConstants.NINE; keyIndex++) {
                    String value = obj[keyIndex - 1].toString();
                    String[] valArry = value.split("~");
                    Integer id = Integer.valueOf(valArry[0]);
                    String desc = valArry[1];
                    String mapKey = parentKey + "~" + desc;
                    Integer keyBegins = columnIncrementer.get(parentKey);
                    keyBegins = keyBegins == null ? 1 : keyBegins;
                    String currentColumnPrevKey = parentLevelKeys.get(mapKey);
                    String currentKey;
                    if (currentColumnPrevKey == null) {
                        currentKey = parentKey.isEmpty() ? (keyBegins++) + "." : parentKey + (keyBegins++) + ".";
                        columnIncrementer.put(parentKey, keyBegins);
                    } else {
                        currentKey = currentColumnPrevKey;
                    }
                    parentLevelKeys.put(mapKey, currentKey);
                    String deductionLevel = ARMUtils.deductionLevelMap().get(keyIndex);
                    DeductionLevelDTO levelDto = new DeductionLevelDTO(id, desc, deductionLevel);
                    String[] rsContractIds = String.valueOf(obj[NumericConstants.NINE]).split(",");
                    levelDto.setRsContractSids(new HashSet(Arrays.asList(rsContractIds)));
                    DeductionLevelDTO oldLevelDto = levelKeys.put(currentKey, levelDto);
                    hierarchyKeys.add(currentKey);
                    if (oldLevelDto != null) {
                        levelDto.getRsContractSids().addAll(oldLevelDto.getRsContractSids());
                    }
                    parentKey = currentKey;
                }
            }
            LOGGER.debug("--Exit cutomizeSelectedDecuction --" + levelKeys.size());
            return levelKeys;
        } catch (Exception ex) {
            LOGGER.error("Error in cutomizeSelectedDecuction :" + ex);
            return null;
        }
    }

    public List<Integer> getRsContractSids(int projectionId) {
        LOGGER.debug("--Inside getRsContractSids --" + projectionId);
        List input = new ArrayList();
        List<Integer> listRSContractsids = new ArrayList();
        input.add(projectionId);
        List<Object> rawList = QueryUtils.getItemData(input, "LoadRsContractSid", null);
        for (int i = 0; i < rawList.size(); i++) {
            listRSContractsids.add(Integer.valueOf(rawList.get(i).toString()));
        }
        LOGGER.debug("--Exit getRsContractSids --" + listRSContractsids.size());
        return listRSContractsids;
    }

    public static void getDates(int buscinessProcess, int companySystemId, int buSid, ComboBox fromPeriod, ComboBox toPeriod, boolean checkFlag, DataSelectionDTO selection) {
        LOGGER.debug("--Inside getDates --buscinessProcess - - " + buscinessProcess + "- - companySystemId - -" + companySystemId + "- - buSid - -" + buSid + "- - checkFlag - " + checkFlag);
        List<Date> periodDates = CommonLogic.getPeriodList("ARM", buscinessProcess, companySystemId, buSid);
        List input = new ArrayList();
        input.add(buscinessProcess);
        if (periodDates != null && !periodDates.isEmpty()) {
            Date currentDate = new Date();
            Date fromDate = periodDates.get(0);
            Date fromDefDate = periodDates.get(1);
            Date toDate = periodDates.get(NumericConstants.TWO);
            Date toDefDate = periodDates.get(NumericConstants.THREE);
            selection.setEndDate(toDate);
            selection.setDefaultToDate(toDefDate);
            selection.setStartDate(fromDate);
            getPeriods(fromDate, toDate, fromPeriod);
            if (checkFlag) {
                getPeriods(fromDate, toDate, toPeriod);
            } else {
                getPeriods(fromDate, currentDate, toPeriod);
            }
            fromPeriod.select(getPeriod(fromDefDate));
            toPeriod.select(getPeriod(toDefDate));
        }
        fromPeriod.setImmediate(true);
        toPeriod.setImmediate(true);
        LOGGER.debug("Exit getDates");
    }

    /**
     *
     * @param startDate
     * @param endDate
     * @param period
     */
    public static void getPeriods(final Date startDate, final Date endDate, final ComboBox period) {
        LOGGER.debug("Inside getPeriods -- " + startDate + " - - endDate - -" + endDate + "- period- " + period);
        SimpleDateFormat getMonth = new SimpleDateFormat("yyyy-MM-dd");
        Calendar startCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();
        Calendar tempCal = Calendar.getInstance();
        startCal.setTime(startDate);
        endCal.setTime(endDate);
        tempCal.set(Calendar.DATE, 1);
        int startmonth = startCal.get(Calendar.MONTH);
        int startYear = startCal.get(Calendar.YEAR);

        int endMonth = endCal.get(Calendar.MONTH);
        int endYear = endCal.get(Calendar.YEAR);
        boolean isNotEnd = true;
        String item = "";
        String caption = "";
        while (isNotEnd) {

            tempCal.set(Calendar.MONTH, startmonth);
            tempCal.set(Calendar.YEAR, startYear);
            tempCal.set(Calendar.HOUR, 0);
            tempCal.set(Calendar.MINUTE, 0);
            tempCal.set(Calendar.SECOND, 0);
            item = getMonth.format(tempCal.getTime());
            caption = MONTHS[startmonth] + " " + startYear;
            period.addItem(item);
            period.setItemCaption(item, caption);
            if (startmonth == endMonth && startYear == endYear) {
                isNotEnd = false;
            } else {
                int tempStartmonth = (startmonth + 1) % NumericConstants.TWELVE;
                if (tempStartmonth == 0) {
                    if (startYear != endYear) {
                        startYear++;
                    }
                    tempCal.set(Calendar.MONTH, tempStartmonth);
                    tempCal.set(Calendar.YEAR, startYear);
                    item = getMonth.format(tempCal.getTime());
                    caption = MONTHS[tempStartmonth] + " " + startYear;
                    period.addItem(item);
                    period.setItemCaption(item, caption);
                    if (endMonth == 0 && (startYear == endYear)) {
                        break;
                    }
                    startmonth = 0;
                } else {
                    startmonth++;
                }

            }

        }

    }

    /**
     *
     * @param date
     * @return
     */
    public static String getPeriod(Date date) {
        SimpleDateFormat getMonth = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return getMonth.format(calendar.getTime());

    }

    /**
     *
     * @param fromPeriod
     * @param toPeriod
     */
    public static void getDates(ComboBox fromPeriod, ComboBox toPeriod) {
        SimpleDateFormat getMonth = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<String[]> list = new ArrayList<>();
        Calendar sdfd = Calendar.getInstance();
        int eMonth = sdfd.get(Calendar.MONTH);
        for (int i = -eMonth; i < eMonth + 1; i++) {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.MONTH, i);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            int month = cal.get(Calendar.MONTH);
            int year = cal.get(Calendar.YEAR);
            String item = getMonth.format(cal.getTime());
            String caption = MONTHS[month] + " " + year;
            fromPeriod.addItem(item);
            fromPeriod.setItemCaption(item, caption);
            toPeriod.addItem(item);
            toPeriod.setItemCaption(item, caption);
            list.add(new String[]{item, caption});
        }
        fromPeriod.select(list.get(0)[0]);
        toPeriod.select(list.get(list.size() - 1)[0]);
        fromPeriod.setImmediate(true);
        toPeriod.setImmediate(true);
    }

    public static String getPeriodViewForAdjustmentType(int adjustmentType, int companyMasterSid, int businessProcess) { // Added for GALUAT-711
        List input = new ArrayList();
        String periodView = StringUtils.EMPTY;
        if (adjustmentType != 0 && companyMasterSid != 0 && businessProcess != 0) {
            input.add(adjustmentType);
            input.add(companyMasterSid);
            input.add(businessProcess);
            List<Object> periodViewList = QueryUtils.getItemData(input, "loadFromAndToBasedOnPeriodConfig", null);
            if (!periodViewList.isEmpty()) {
                periodView = String.valueOf(periodViewList.get(0));
            }
        }
        return periodView;
    } //Ends here

    public static void getDates(int buscinessProcess, int companySystemId, int buSid, ComboBox toPeriod) { // Added for GALUAT-711
        List<Date> periodDates = CommonLogic.getPeriodList("ARM", buscinessProcess, companySystemId, buSid);
        if (periodDates != null && !periodDates.isEmpty()) {
            Date currentDate = new Date();
            Date fromDate = periodDates.get(0);
            Date fromDefDate = periodDates.get(1);
            getPeriods(fromDate, currentDate, toPeriod);
            toPeriod.select(getPeriod(fromDefDate));
        }
        toPeriod.setImmediate(true);
    } //Ends here

    public boolean dateCheckforGLCompAndBu(DataSelectionDTO dataselectionDTO, boolean approveFlag) {
        closedinput = new ArrayList();
        openinput = new ArrayList();
        input = new ArrayList();
        newClose = new ArrayList();
        input.add(dataselectionDTO.getCompanyMasterSid());
        getGlCompAndBuClosedList(); // for GL comp Company
        clearLists();
        input.add(dataselectionDTO.getBucompanyMasterSid());
        getGlCompAndBuClosedList(); // for Business Unit Company
        clearLists();
        input.add(dataselectionDTO.getProjectionId());
        dataselectionDTO.setNewClosedSummaryglList(newClose);
        return approveFlag ? checkGLlistClose(input, newClose) : false;

    }

    public void getGlCompAndBuClosedList() {
        List<Object[]> glList = QueryUtils.getItemData(input, "glImpactdateList", null);
        openCloseGLCompAndBU(glList);
        for (Object close : closedinput) {
            if (!openinput.contains(close)) {
                newClose.add(close);
            } else {
                int openOccurrences = Collections.frequency(openinput, close);
                int closedOccurrences = Collections.frequency(closedinput, close);
                LOGGER.debug("openOccurrences" + openOccurrences);
                LOGGER.debug("closedOccurrences" + closedOccurrences);
                if (openOccurrences > closedOccurrences) {
                    newClose.add(close);
                }
            }
        }
    }

    public void openCloseGLCompAndBU(List<Object[]> list) {
        if ((list != null && !list.isEmpty()) && (list.size() > 1)) {
            for (Object[] closedPeriod : list) {
                if ("0".equals(closedPeriod[1].toString())) { //0 for Open Status and 1 for Close status
                    Date closedDate = (Date) closedPeriod[0];
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(closedDate);
                    closedinput.add(cal.get(Calendar.MONTH) + 1 + "-" + cal.get(Calendar.YEAR));
                } else {
                    Date openDate = (Date) closedPeriod[0];
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(openDate);
                    openinput.add(cal.get(Calendar.MONTH) + 1 + "-" + cal.get(Calendar.YEAR));
                }
            }
        }
    }

    public boolean checkGLlistClose(List input, List newClose) {
        List<Object> glList = QueryUtils.getItemData(input, "glImpactdateApprove", null);
        Calendar cal = Calendar.getInstance();
        if (glList.get(0) != null) {
            cal.setTime((Date) glList.get(0));
        } else {
            cal.setTime(new Date());
        }
        Object glString = cal.get(Calendar.MONTH) + 1 + "-" + cal.get(Calendar.YEAR);
        return !newClose.contains(glString);
    }

    public void clearLists() {
        closedinput.clear();
        openinput.clear();
        input.clear();
    }

    public boolean getVersionCount(String date) {
        String query = "SELECT COUNT(*) FROM RETURNS_MASTER WHERE VERSION LIKE '" + date + "'";
        LOGGER.debug("query --" + query);
        int count = (int) ((HelperTableLocalServiceUtil.executeSelectQuery(query)).get(0));
        return !date.equals(GlobalConstants.getSelectOne()) ? count == 0 : false;
    }

    public Integer getCompanyId() {
        String query = "SELECT TOP(1) COMPANY_MASTER_SID  FROM COMPANY_MASTER CM JOIN dbo.HELPER_TABLE HT ON HT.HELPER_TABLE_SID = CM.COMPANY_TYPE WHERE HT.DESCRIPTION NOT IN ('GLcomp','Business Unit')";
        List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return (Integer) list.get(0);
    }
    private String convertNullToEmpty(Object value) {
        String returnValue;
        if (value == null || ARMUtils.NULL.equals(String.valueOf(value)) || "0".equals(String.valueOf(value))) {
            returnValue = StringUtils.EMPTY;
        } else {
            returnValue = String.valueOf(value);
        }
        return returnValue;
    }
}
