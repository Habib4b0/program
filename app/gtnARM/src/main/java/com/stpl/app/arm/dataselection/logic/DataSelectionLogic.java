
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.dataselection.logic;

import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.dataselection.dto.DataSelectionDTO;
import com.stpl.app.arm.dataselection.dto.DeductionLevelDTO;
import com.stpl.app.arm.dataselection.dto.HierarchyLookupDTO;
import com.stpl.app.arm.dataselection.dto.LevelDTO;
import com.stpl.app.arm.dataselection.dto.SaveViewDTO;
import com.stpl.app.arm.dataselection.dto.ViewDTO;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.DataSelectionQueryUtils;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.ProjectionMasterLocalServiceUtil;
import com.stpl.app.serviceUtils.ConstantsUtils;
import static com.stpl.app.utils.CommonUtils.userMap;
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
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
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

    }

    public List searchLogicForHierarchy(HierarchyLookupDTO hierarchyLookupDTO, boolean isCount, final int startIndex, final int endIndex, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet) {
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
        String filterQuery = StringUtils.EMPTY;
        HashMap<String, String> detailsColumn = new HashMap<String, String>();
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
                    filterQuery = filterQuery + " AND " + detailsColumn.get(String.valueOf(stringFilter.getPropertyId())) + " like '" + filterString + "'";
                }
            }
        }
        String finalQuery = StringUtils.EMPTY;
        String order = StringUtils.EMPTY;
        if (!isCount) {
            boolean sortOrder = false;
            String columnName = null;
            String orderByColumn = null;
            if (sortByColumns != null) {
                for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                    final SortByColumn sortByColumn = (SortByColumn) iterator.next();
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
            finalQuery = sqlQuery + filterQuery;
        } else {
            finalQuery = sqlQuery + filterQuery + order;
        }
        if (isCount) {
            List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(finalQuery);
            return list;
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
                    LOGGER.error(ex);
                }
            }
            if (obj[NumericConstants.SIX] == null) {
                hdto.setModifiedDate(ConstantsUtils.EMPTY);
            } else {
                try {
                    hdto.setModifiedDate(df.format(format.parse(obj[NumericConstants.SIX].toString())));
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
            hdto.setVersionNo(Integer.parseInt(String.valueOf(obj[NumericConstants.SEVEN].toString())));
            resultList.add(hdto);
        }
        return resultList;
    }

    public void loadCustomerRelation(ComboBox comboBox, int hierSid) {
        String sqlQuery = SQlUtil.getQuery("getCustRelation").concat(StringUtils.EMPTY + hierSid);
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

    public void loadProductRelation(ComboBox comboBox, int hierSid, int glComp) {
        String sqlQuery = SQlUtil.getQuery("getProdRelation").concat(StringUtils.EMPTY + hierSid);
        if (glComp != 0) {
            sqlQuery += " " + SQlUtil.getQuery("joinGLComp").concat(StringUtils.EMPTY + glComp);
        }
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
        String customSql = CustomSQLUtil.get("getLevelsFromHierarchy");
        if (StringUtils.isNotEmpty(String.valueOf(hierSid))
                && StringUtils.isNotBlank(String.valueOf(hierSid))) {
            customSql += String.valueOf(hierSid).trim();
        }
        customSql += CustomSQLUtil.get("getLevelsFromHierarchy2");
        List<Object[]> list = HelperTableLocalServiceUtil.executeSelectQuery(customSql);
        comboBox.removeAllItems();
        comboBox.addItem(GlobalConstants.getSelectOne());
        comboBox.setNullSelectionAllowed(true);
        comboBox.setNullSelectionItemId(GlobalConstants.getSelectOne());
        for (Object[] obj : list) {
            comboBox.addItem(obj[1]);
            comboBox.setItemCaption(obj[1], ARMUtils.LEVEL + ARMUtils.SPACE + obj[1] + ARMUtils.SPACE + ARMUtils.HIPHEN + ARMUtils.SPACE + obj[0]);
        }
    }

    public void insertToCcpMap(final String hierarchyIndicator, final String tempTableName, final String hierarchyDefinitionSid, final String relationshipBuilderSid) {
        DataSelectionQueryUtils.insertIntoCcpMap(hierarchyIndicator, tempTableName, hierarchyDefinitionSid, relationshipBuilderSid);
    }

    public Map<String, String> getLevelValueMap(String relationshipBuilderSID) {
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("?RBSID", relationshipBuilderSID);
        return (Map<String, String>) DataSelectionQueryUtils.TempOperation(input);
    }

    public List<LevelDTO> loadCustomerForecastLevel(int hierarchyId, String hierarchyName) {
        List<LevelDTO> resultList = new ArrayList<LevelDTO>();
        LevelDTO leveldto;
        Map<String, Object> parameters = new HashMap<String, Object>();
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
        return resultList;
    }

    public List<LevelDTO> loadCustomerInnerLevel(final String relationshipSid, int levelNo, int hierarchyId, List<Integer> rsContractSidList,
            final Map<String, String> descriptionMap, String tempCCPTable) {
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

        return values;
    }

    public List<LevelDTO> loadProductInnerLevel(final String relationshipSid, int levelNo, int hierarchyId, List<Integer> rsContractSidList,
            List<Integer> selectedCustomerSids, int businessSid, int glCompSid, String custRelationshipSid, final Map<String, String> descriptionMap,
            boolean isNdc, String tempCCPTable) {

        List<LevelDTO> values = new ArrayList<>();
        List result = DataSelectionQueryUtils.getProductInnerLevel(relationshipSid, levelNo, hierarchyId, rsContractSidList, selectedCustomerSids, businessSid, glCompSid, custRelationshipSid, isNdc, tempCCPTable);

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

        return values;
    }

    public List<LevelDTO> getParentLevelsWithHierarchyNo(final String hierarchyNos, final Map<String, String> descriptionMap) {

        List resultss;
        List<LevelDTO> resultList = null;
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("hierarchyNos", hierarchyNos);
        parameters.put(ARMUtils.INDICATOR, "getParentLevelsWithHierarchyNo");
        LevelDTO dto;

        try {
            resultss = DataSelectionQueryUtils.getParentLevels(0, parameters);

            if (resultss != null) {
                resultList = new ArrayList<LevelDTO>();
                for (int loop = 0, limit = resultss.size(); loop < limit; loop++) {
                    Object objects[] = (Object[]) resultss.get(loop);
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
            LOGGER.error(ex);
        }
        return resultList;
    }

    public List<LevelDTO> getChildLevelsWithHierarchyNo(String hierarchyNo, int lowestLevelNo, final Map<String, String> descriptionMap) {

        List resultss;
        List<LevelDTO> resultList = null;
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(ARMUtils.HIERARACHY_NO_PROPERTY, hierarchyNo);
        parameters.put("lowestLevelNo", lowestLevelNo);
        parameters.put(ARMUtils.INDICATOR, "getChildLevelsWithHierarchyNo");
        LevelDTO dto;

        try {
            resultss = DataSelectionQueryUtils.getChildLevels(parameters);

            if (resultss != null) {
                resultList = new ArrayList<LevelDTO>();
                for (int loop = 0, limit = resultss.size(); loop < limit; loop++) {
                    Object objects[] = (Object[]) resultss.get(loop);
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
            LOGGER.error(ex);
        }
        return resultList;
    }

    public List getFSValue(String relationshipLevelValue, final String fieldName) {
        List list = new ArrayList();
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(ARMUtils.INDICATOR, "getFSValue");
        parameters.put("relationshipLevelValue", relationshipLevelValue);
        parameters.put(ARMUtils.FIELD_NAME, fieldName);
        try {
            list = (List) DataSelectionQueryUtils.executeQuery(parameters);
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
        int length = 0;
        for (LevelDTO dto : selectedProductContainer.getItemIds()) {
            if (length < dto.getDisplayValue().length()) {
                length = dto.getDisplayValue().length();
            }
        }
        if (length > NumericConstants.FIFTY) {
            if (length <= NumericConstants.SIXTY) {
                selectedProduct.setColumnWidth("displayValue", NumericConstants.FIVE_ONE_ZERO);
            } else if (length <= NumericConstants.SEVENTY) {
                selectedProduct.setColumnWidth("displayValue", NumericConstants.FIVE_SEVEN_ZERO);
            } else if (length <= NumericConstants.EIGHTY) {
                selectedProduct.setColumnWidth("displayValue", NumericConstants.SIX_THREE_ZERO);
            } else if (length <= NumericConstants.NINETY) {
                selectedProduct.setColumnWidth("displayValue", NumericConstants.SEVEN_THREE_ZERO);
            } else if (length <= NumericConstants.HUNDRED) {
                selectedProduct.setColumnWidth("displayValue", NumericConstants.EIGHT_ONE_ZERO);
            }
        } else {
            selectedProduct.setColumnWidth("displayValue", -1);
        }
    }

    public int searhViewCount(final String viewName, final String viewType, final String adjustmentType, boolean isCount, Set<Container.Filter> filters, List<SortByColumn> sortByColumns)
            throws SystemException, PortalException {
        LOGGER.debug("Entering searchView method");
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
        LOGGER.debug("End of searchView method");
        return count;
    }

    public List searhView(final String viewName, final String viewType, final String adjustmentType, boolean isCount, Set<Container.Filter> filters, List<SortByColumn> sortByColumns, int start, int offset)
            throws SystemException, PortalException {
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

    public List<ViewDTO> getCustomizedViews(final List list) throws ParseException, PortalException, SystemException {
        final List<ViewDTO> results = new ArrayList<>();
        LOGGER.debug("Entering getCustomizedViews method with list size  " + list.size());
        Map<Integer, HelperDTO> helperMap = helperListUtil.getIdHelperDTOMap();
        for (int i = 0; i < list.size(); i++) {
            final Object[] obj = (Object[]) list.get(i);
            final ViewDTO result = new ViewDTO();
            result.setProjectionDescription(CommonLogic.convertNullToEmpty(String.valueOf(obj[0])));
            result.setAdjustmentId(obj[1] != null ? (int) obj[1] : 0);
            result.setAdjustmentType(CommonLogic.convertNullToEmpty(String.valueOf(obj[NumericConstants.TWO])));
            result.setCompanyMasterSid((int) obj[NumericConstants.THREE] == 0 ? 0 : (int) obj[NumericConstants.THREE]);
            result.setBu_companyMasterSid((int) obj[NumericConstants.FOUR] == 0 ? 0 : (int) obj[NumericConstants.FOUR]);
            result.setDeductionLevel((int) obj[NumericConstants.FIVE] == 0 ? 0 : (int) obj[NumericConstants.FIVE]);
            result.setCustomerHierarchySid(CommonLogic.convertNullToEmpty(String.valueOf(obj[NumericConstants.SIX])));
            result.setCustomerHierarchyName(CommonLogic.convertNullToEmpty(String.valueOf(obj[NumericConstants.SEVEN])));
            result.setCustRelationshipBuilderSid(CommonLogic.convertNullToEmpty(String.valueOf(obj[NumericConstants.EIGHT])));
            result.setCustomerHierarchyLevel(CommonLogic.convertNullToEmpty(String.valueOf(obj[NumericConstants.NINE])));
            result.setProductHierarchySid(CommonLogic.convertNullToEmpty(String.valueOf(obj[NumericConstants.TEN])));
            result.setProductHierarchyName(CommonLogic.convertNullToEmpty(String.valueOf(obj[NumericConstants.ELEVEN])));
            result.setProdRelationshipBuilderSid(CommonLogic.convertNullToEmpty(String.valueOf(obj[NumericConstants.TWELVE])));
            result.setProductHierarchyLevel(CommonLogic.convertNullToEmpty(String.valueOf(obj[NumericConstants.THIRTEEN])));
            result.setViewName(CommonLogic.convertNullToEmpty(String.valueOf(obj[NumericConstants.FOURTEEN])));
            result.setCompanyName(CommonLogic.convertNullToEmpty(String.valueOf(obj[NumericConstants.FIFTEEN])));
            result.setProjectionId(CommonLogic.convertNullToEmpty(String.valueOf(obj[NumericConstants.SIXTEEN])));
            result.setViewId(CommonLogic.convertNullToEmpty(String.valueOf(obj[NumericConstants.SEVENTEEN])));
            result.setCustomerHierVers((int) obj[NumericConstants.EIGHTEEN] == 0 ? 0 : (int) obj[NumericConstants.EIGHTEEN]);
            result.setCustomerHierHL(CommonLogic.convertNullToEmpty(String.valueOf(obj[NumericConstants.NINETEEN])));
            result.setProductHierVers((int) obj[NumericConstants.TWENTY] == 0 ? 0 : (int) obj[NumericConstants.TWENTY]);
            result.setProductHierHL(CommonLogic.convertNullToEmpty(String.valueOf(obj[NumericConstants.TWENTY_ONE])));
            String crBy = CommonLogic.convertNullToEmpty(String.valueOf(obj[NumericConstants.TWENTY_TWO]));
            result.setViewCreatedBy(StringUtils.isNotBlank(crBy) ? Integer.parseInt(crBy) : 0);
            result.setViewType(CommonLogic.convertNullToEmpty(String.valueOf(obj[NumericConstants.TWENTY_THREE])));
            result.setBu_CompanyName(CommonLogic.convertNullToEmpty(String.valueOf(obj[NumericConstants.TWENTY_FOUR])));
            result.setFromPeriod((Date) (obj[NumericConstants.TWENTY_FIVE]));
            result.setToPeriod((Date) obj[NumericConstants.TWENTY_SIX]);
            result.setCreatedByString((result.getViewCreatedBy() != 0) ? userMap.get(result.getViewCreatedBy()) : StringUtils.EMPTY);
            result.setDeductionLevels((result.getDeductionLevel() != 0) ? helperMap.get(result.getDeductionLevel()) != null ? (helperMap.get(result.getDeductionLevel())).getDescription() : StringUtils.EMPTY : StringUtils.EMPTY);
            result.setCustomerRelationship(CommonLogic.convertNullToEmpty(String.valueOf(obj[29])));
            result.setProductRelationship(CommonLogic.convertNullToEmpty(String.valueOf(obj[30])));
            result.setCalculationProfileMasterSid(CommonLogic.convertNullToEmpty(String.valueOf(obj[32])));
            result.setCalculationProfileName(CommonLogic.convertNullToEmpty(String.valueOf(obj[33])));

            result.setCreatedDate((Date) (obj[NumericConstants.TWENTY_SEVEN]));
            result.setModifiedDate((Date) (obj[NumericConstants.TWENTY_EIGHT]));

            results.add(result);

        }
        LOGGER.debug("End of getCustomizedViews method");
        return results;
    }

    public List<ViewDTO> getCalculationCustomizedViews(final List list) throws ParseException, PortalException, SystemException {
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
            result.setCalculationProfileMasterSid(CommonLogic.convertNullToEmpty(String.valueOf(obj[7])));
            result.setCalculationProfileName(CommonLogic.convertNullToEmpty(String.valueOf(obj[8])));
            result.setCalculationProfileDescrition(CommonLogic.convertNullToEmpty(String.valueOf(obj[NumericConstants.NINE])));

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
        List<DeductionLevelDTO> resultList = new ArrayList<>();
        String sqlQuery = SQlUtil.getQuery("getDeductionAvailableLevels");
        String[] select = ARMUtils.getLevelMap(valueLevel.getDescription());

        sqlQuery = sqlQuery.replace("@PARAMETERS", select[0]);
        if (select[1].equalsIgnoreCase("UDC")) {
            sqlQuery = sqlQuery.replace("--@UDCS", " AND " + select[NumericConstants.TWO]);
        }
        sqlQuery = sqlQuery + select[1];
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

        return resultList;
    }

    public String getFilterStringforRsSidFilter(int levelNo, Map<Integer, String[]> dedLevelMap) {

        String filterStrings;
        filterStrings = (ARMUtils.getLevelMap((dedLevelMap.get(levelNo))[1]))[0] + " in ( '" + "$$$$VALUE$$$$" + "' )";
        return filterStrings;
    }

    public void saveCustomerHierarchyLogic(final List<LevelDTO> levelList, final List<String> endLevelSids, final int projectionId, final List<String> addLevels, final String indicator) {
        LOGGER.debug("saveCustomerHierarchyLogic endLevelSids size:" + endLevelSids.size() + " projectionId " + projectionId);
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(ARMUtils.INDICATOR, "getChildLevelRLSid");
        parameters.put(ARMUtils.PROJECTION_ID, projectionId);
        parameters.put("rlSids", endLevelSids);
        parameters.put("tableName", "PROJECTION_CUST_HIERARCHY");
        List<Object> endLevels = null;
        if (!endLevelSids.isEmpty()) {
            endLevels = DataSelectionQueryUtils.executeQuery(parameters);
        }
        String sbQuery = SQlUtil.getQuery("insertcustHierarchy");
        String values = StringUtils.EMPTY;
        try {
            if (endLevels != null && !endLevels.isEmpty()) {
                for (Object relationshipLevelSid : endLevels) {
                    if (values.isEmpty()) {
                        values = "(" + projectionId + ARMUtils.COMMA + CommonLogic.parseStringToInteger(String.valueOf(relationshipLevelSid)) + ")";
                    } else {
                        values += ARMUtils.COMMA + " (" + projectionId + ARMUtils.COMMA + CommonLogic.parseStringToInteger(String.valueOf(relationshipLevelSid)) + ") ";
                    }
                }
            }
            if (ARMUtils.UPDATE.equals(indicator)) {
                for (String rsId : addLevels) {
                    if (values.isEmpty()) {
                        values = "(" + projectionId + ARMUtils.COMMA + CommonLogic.parseStringToInteger(String.valueOf(rsId)) + ")";
                    } else {
                        values += ARMUtils.COMMA + " (" + projectionId + ARMUtils.COMMA + CommonLogic.parseStringToInteger(String.valueOf(rsId)) + ") ";
                    }
                }
            } else if (ARMUtils.SAVE.equals(indicator)) {
                for (LevelDTO dto : levelList) {
                    if (values.isEmpty()) {
                        values = "(" + projectionId + ARMUtils.COMMA + dto.getRelationshipLevelSid() + ")";
                    } else {
                        values += ARMUtils.COMMA + " (" + projectionId + ARMUtils.COMMA + dto.getRelationshipLevelSid() + ") ";
                    }
                }
            }
            sbQuery = sbQuery.replace("$$$$VALUES$$$", values);
            HelperTableLocalServiceUtil.executeUpdateQuery(sbQuery);
        } catch (Exception e) {
            LOGGER.error(e + " in saveCustomerHierarchyLogic");
        }
    }

    public void saveAdjustmentMaster(int projectionId, DataSelectionDTO dataSelectionDTO) {
        try {
            if (projectionId != 0 && dataSelectionDTO.getBu_companyMasterSid() != 0 && dataSelectionDTO.getDeductionLevel() != 0) {
                String saveAdjusMasQuery = SQlUtil.getQuery("ARM_ADJUSTMENT_MASTER_SAVED_PROJECTION");
                saveAdjusMasQuery = saveAdjusMasQuery.replace("@PROJECTION_MASTER_SID", "" + projectionId);
                saveAdjusMasQuery = saveAdjusMasQuery.replace("@BU_COMPANY_MASTER_SID", "" + dataSelectionDTO.getBu_companyMasterSid());
                saveAdjusMasQuery = saveAdjusMasQuery.replace("@DEDUCTION_LEVEL", "" + dataSelectionDTO.getDeductionLevel());
                saveAdjusMasQuery = saveAdjusMasQuery.replace("@ADJ_TYPE", dataSelectionDTO.getAdjustmentId() == 0 ? ARMUtils.NULL : "" + dataSelectionDTO.getAdjustmentId());
                if (dataSelectionDTO.getScreenname().equals("BSR")) {
                saveAdjusMasQuery = saveAdjusMasQuery.replace("@CALCULATION_PROFILE_MASTER_SID", "0".equals(dataSelectionDTO.getCalculationProfileMasterSid()) ? ARMUtils.NULL : "" + dataSelectionDTO.getCalculationProfileMasterSid());
                } else {
                    saveAdjusMasQuery = saveAdjusMasQuery.replace("@CALCULATION_PROFILE_MASTER_SID", "''");
                }
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
        String values = StringUtils.EMPTY;
        try {
            if (ARMUtils.UPDATE.equals(indicator)) {
                for (String rsId : addLevels) {
                    if (values.isEmpty()) {
                        values = "(" + projectionId + ARMUtils.COMMA + CommonLogic.parseStringToInteger(String.valueOf(rsId)) + ")";
                    } else {
                        values += ARMUtils.COMMA + " (" + projectionId + ARMUtils.COMMA + CommonLogic.parseStringToInteger(String.valueOf(rsId)) + ") ";
                    }
                }
            } else if (ARMUtils.SAVE.equals(indicator)) {
                for (LevelDTO dto : levelList) {
                    if (values.isEmpty()) {
                        values = "(" + projectionId + ARMUtils.COMMA + dto.getRelationshipLevelSid() + ")";
                    } else {
                        values += ARMUtils.COMMA + " (" + projectionId + ARMUtils.COMMA + dto.getRelationshipLevelSid() + ") ";
                    }
                }
            }
            if (endLevels != null && !endLevels.isEmpty()) {
                for (Object relationshipLevelSid : endLevels) {
                    if (values.isEmpty()) {
                        values = "(" + projectionId + ARMUtils.COMMA + CommonLogic.parseStringToInteger(String.valueOf(relationshipLevelSid)) + ")";
                    } else {
                        values += ARMUtils.COMMA + " (" + projectionId + ARMUtils.COMMA + CommonLogic.parseStringToInteger(String.valueOf(relationshipLevelSid)) + ") ";
                    }
                }
            }
            sbQuery = sbQuery.replace("$$$$VALUES$$$", values);
            HelperTableLocalServiceUtil.executeUpdateQuery(sbQuery);
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
            viewQuery = viewQuery.replace("@CREATED_BY", "" + viewDTO.getCreatedBy());
            viewQuery = viewQuery.replace("@CREATED_DATE", viewDTO.getCreatedDate());
            viewQuery = viewQuery.replace("@PROJECTION_ID", "" + viewDTO.getProjectionSid());
            HelperTableLocalServiceUtil.executeUpdateQuery(viewQuery);
        } catch (Exception ex) {
            LOGGER.debug(ex + " saveViewLogic");
        }

    }

    public void saveDeductionLogic(Set<Integer> rsModelSidList, int projectionSid) {
        try {
            StringBuilder insertDeductionQuery = new StringBuilder("");
            if (!rsModelSidList.isEmpty()) {
                insertDeductionQuery.append(SQlUtil.getQuery("DEDUCTION_SAVED_PROJECTION"));
                for (Integer rsModelSid : rsModelSidList) {
                    insertDeductionQuery.append("(").append(projectionSid).append(",").append(rsModelSid).append("),");
                }
                insertDeductionQuery.replace(insertDeductionQuery.length() - 1, insertDeductionQuery.length(), "");
            }
            HelperTableLocalServiceUtil.executeUpdateQuery(insertDeductionQuery.toString());
        } catch (Exception ex) {
            LOGGER.debug(ex + " save Deduction Logic");
        }

    }

    public void saveCcp(final String tempTableName, final String projectionId) throws Exception {
        String query = StringUtils.EMPTY;
        try {
            String sqlQuery = SQlUtil.getQuery("ccpMergeQuery");

            query = sqlQuery;
            query = query.replace("[$ST_CCP_HIERARCHY]", tempTableName);
            query = query.replace("[$PROJECTION_MASTER_SID]", projectionId);
            HelperTableLocalServiceUtil.executeUpdateQuery(query);
        } catch (Exception e) {
            LOGGER.error(e);
            LOGGER.error(query);
        }
    }

    public void updateSavedViewProjection(DataSelectionDTO dto) {
        try {
            String updateQuery = SQlUtil.getQuery("updateProjectionMaster");
            updateQuery = updateQuery.replace("@PROJECTION_DESCRIPTION", dto.getProjectionDescription());
            updateQuery = updateQuery.replace("@FORECASTING_TYPE", dto.getForecastingType());
            updateQuery = updateQuery.replace("@COMPANY_MASTER_SID", "" + dto.getCompanyMasterSid());
            updateQuery = updateQuery.replace("@CUSTOMER_HIERARCHY_SID", "" + dto.getCustomerHierarchySid());
            updateQuery = updateQuery.replace("@PRODUCT_HIERARCHY_SID", "" + dto.getProductHierarchySid());
            updateQuery = updateQuery.replace("@CUSTOMER_HIERARCHY_LEVEL", dto.getCustomerHierarchyLevel());
            updateQuery = updateQuery.replace("@PRODUCT_HIERARCHY_LEVEL", dto.getProductHierarchyLevel());
            updateQuery = updateQuery.replace("@CUST_RELATIONSHIP_BUILDER_SID", "" + dto.getCustRelationshipBuilderSid());
            updateQuery = updateQuery.replace("@PROD_RELATIONSHIP_BUILDER_SID", "" + dto.getProdRelationshipBuilderSid());
            updateQuery = updateQuery.replace("@PROJECTION_MASTER_SID", "" + dto.getProjectionId());
            HelperTableLocalServiceUtil.executeUpdateQuery(updateQuery);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public int saveProjection(final DataSelectionDTO dataSelectionDTO) throws SystemException {
        String finalQuery = SQlUtil.getQuery("insertProjectionMaster");
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("'").append(dataSelectionDTO.getProjectionName()).append("'").append(ARMUtils.COMMA);
        queryBuilder.append("'").append(dataSelectionDTO.getProjectionDescription()).append("'").append(ARMUtils.COMMA);
        queryBuilder.append("'").append(dataSelectionDTO.getForecastingType()).append("'").append(ARMUtils.COMMA);
        queryBuilder.append(dataSelectionDTO.getCreatedBy()).append(ARMUtils.COMMA);
        queryBuilder.append("'").append(new Timestamp(new Date().getTime())).append("'").append(ARMUtils.COMMA);
        queryBuilder.append(dataSelectionDTO.getCustomerHierarchySid()).append(ARMUtils.COMMA);
        queryBuilder.append(dataSelectionDTO.getProductHierarchySid()).append(ARMUtils.COMMA);
        queryBuilder.append(dataSelectionDTO.getCustomerHierarchyLevel()).append(ARMUtils.COMMA);
        queryBuilder.append(dataSelectionDTO.getProductHierarchyLevel()).append(ARMUtils.COMMA);
        queryBuilder.append(dataSelectionDTO.getCustomerHierarchyVersionNo()).append(ARMUtils.COMMA);
        queryBuilder.append(dataSelectionDTO.getProductHierarchyVersionNo()).append(ARMUtils.COMMA);
        queryBuilder.append(dataSelectionDTO.getCompanyMasterSid()).append(ARMUtils.COMMA);
        queryBuilder.append("'").append(dataSelectionDTO.getFromPeriod()).append("'").append(ARMUtils.COMMA);
        queryBuilder.append("'").append(dataSelectionDTO.getToPeriod()).append("'").append(ARMUtils.COMMA);
        queryBuilder.append("'").append(dataSelectionDTO.getSaveFlag()).append("'").append(ARMUtils.COMMA);
        queryBuilder.append(dataSelectionDTO.getCustRelationshipBuilderSid()).append(ARMUtils.COMMA);
        queryBuilder.append(dataSelectionDTO.getProdRelationshipBuilderSid());
        finalQuery = finalQuery.replace("$$$$VALUE$$$$", queryBuilder.toString());
        List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(finalQuery);
        return Integer.valueOf(list.get(0) + "");
    }

    private void loadValueFields(LevelDTO selectedLevel, Map<Integer, String> map) {
        map.clear();
        map.put(1, selectedLevel.getRsCategory());
        map.put(2, selectedLevel.getRsType());
        map.put(3, selectedLevel.getRsProg());
        map.put(4, selectedLevel.getRsModelSID());
        map.put(5, selectedLevel.getUdc2());
        map.put(6, selectedLevel.getUdc3());
        map.put(7, selectedLevel.getUdc4());
    }

    public void saveAdjustmentMaster(DataSelectionDTO dataSelectionDTO) {
        String sqlQuery = SQlUtil.getQuery("insertArmAdjustmentMaster");
        String values = dataSelectionDTO.getProjectionId() + ARMUtils.COMMA
                + dataSelectionDTO.getBu_companyMasterSid() + ARMUtils.COMMA
                + dataSelectionDTO.getDeductionLevel() + ARMUtils.COMMA
                + ((dataSelectionDTO.getAdjustmentId() == 0) ? null : dataSelectionDTO.getAdjustmentId());
        sqlQuery = sqlQuery.replace("$$$$VALUES$$$$", values);
        HelperTableLocalServiceUtil.executeUpdateQuery(sqlQuery);
    }

    public boolean isDuplicateView(String viewName) {
        try {
            String sqlQuery = SQlUtil.getQuery("duplicateViewCheck");
            sqlQuery = sqlQuery.replace("$$$VN$$$", viewName);
            List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(sqlQuery);
            return !list.isEmpty();
        } catch (Exception ex) {
            LOGGER.error("Error in View Name Duplicated" + ex);
            return true;
        }
    }

    public DataSelectionDTO getViewDetails(String projectionId) {
        String sqlQuery = SQlUtil.getQuery("FETCH_SAVED_PROJECTION");
        sqlQuery = sqlQuery.replace("[$PROJECTION_MASTER_SID]", projectionId);
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
            dto.setBu_companyMasterSid(obj[index] == null ? 0 : (int) obj[index++]);
            dto.setDeductionLevel(obj[index] == null ? 0 : (int) obj[index++]);
            dto.setCustomerHierarchySid(obj[index] == null ? 0 : (int) obj[index++]);
            dto.setCustomerHierarchyName(obj[index] == null ? StringUtils.EMPTY : obj[index++].toString());
            dto.setCustRelationshipBuilderSid(obj[index] == null ? 0 : (int) obj[index++]);
            dto.setCustomerHierarchyLevel(obj[index] == null ? StringUtils.EMPTY : obj[index++].toString());
            dto.setProductHierarchySid(obj[index] == null ? 0 : (int) obj[index++]);
            dto.setProductHierarchyName(obj[index] == null ? StringUtils.EMPTY : obj[index++].toString());
            dto.setProdRelationshipBuilderSid(obj[index] == null ? 0 : (int) obj[index++]);
            dto.setProductHierarchyLevel(obj[index] == null ? StringUtils.EMPTY : obj[index++].toString());
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
        List resultss;
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("projectionId", projectionId);
        parameters.put("indicator", indicator);
        if (levelNo == null) {
            parameters.put("restrictLevelNumber", null);
            parameters.put("levelNo", null);
        } else {
            parameters.put("restrictLevelNumber", Boolean.TRUE);
            parameters.put("levelNo", String.valueOf(levelNo));
        }

        List<LevelDTO> resultList = new ArrayList<LevelDTO>();
        LevelDTO dto;

        try {
            resultss = ProjectionMasterLocalServiceUtil.getRelationShipValues(parameters);
            for (int loop = 0, limit = resultss.size(); loop < limit; loop++) {
                dto = new LevelDTO();
                Object objects[] = (Object[]) resultss.get(loop);
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
                if (descriptionMap != null) {
                    dto.setDisplayValue(descriptionMap.get(String.valueOf(objects[NumericConstants.SEVEN])));
                }

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
            rsQuery = rsQuery.replace("@PROJECTION_MASTER_SID", "" + projectionSid);
            List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(rsQuery);
            for (int i = 0; i < list.size(); i++) {
                rsModelSid.add((Integer) list.get(i));
            }
            return rsModelSid;
        } catch (Exception e) {
            LOGGER.error(e);
            return Collections.EMPTY_LIST;
        }
    }

    public void updateSaveViewLogic(DataSelectionDTO dtoValue) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        try {
            String updateARMAdjustQuery = SQlUtil.getQuery("updateSelectedView");
            updateARMAdjustQuery = updateARMAdjustQuery.replace("@DEDUCTION_LEVEL", "" + dtoValue.getDeductionLevel());
            updateARMAdjustQuery = updateARMAdjustQuery.replace("@ADJ_TYPE", "" + dtoValue.getAdjustmentId());
            if(!StringUtils.EMPTY.equals(dtoValue.getCalculationProfileMasterSid())) {
             updateARMAdjustQuery = updateARMAdjustQuery.replace("@CALCULATION_PROFILE_MASTER_SID", "" + " ,CALCULATION_PROFILE_MASTER_SID = " +dtoValue.getCalculationProfileMasterSid());
            } else {
             updateARMAdjustQuery = updateARMAdjustQuery.replace("@CALCULATION_PROFILE_MASTER_SID", "");   
            }
            updateARMAdjustQuery = updateARMAdjustQuery.replace("@BU_COMPANY_MASTER_SID", "" + dtoValue.getBu_companyMasterSid());
            updateARMAdjustQuery = updateARMAdjustQuery.replace("@PROJECTION_MASTER_SID", "" + dtoValue.getProjectionId());
            updateARMAdjustQuery = updateARMAdjustQuery.replace("@MODIFIED_BY", "" + dtoValue.getCreatedBy());
            updateARMAdjustQuery = updateARMAdjustQuery.replace("@VIEW_ID", dtoValue.getViewId());
            updateARMAdjustQuery = updateARMAdjustQuery.replace("@MODIFIED_DATE", dateFormat.format(new Date()));
            HelperTableLocalServiceUtil.executeUpdateQuery(updateARMAdjustQuery);
        } catch (Exception ex) {
            LOGGER.error(ex + " in updateSaveViewLogic");
        }

    }

    public boolean deleteViewLogic(int projectionId) {
        String query = SQlUtil.getQuery("deletePrivatePublicViews");
        query = query.replace("@PROJECTION_MASTER_SID", "" + projectionId);
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
        return true;
    }
    public static final String[] MONTHS = new DateFormatSymbols().getShortMonths();

    public String getDuplicateFilters(LevelDTO dedval, Map<Integer, String[]> deductionMap, List<Integer> rsContractSidList) {
        String rsContractIdFilters;
        rsContractIdFilters = SQlUtil.getQuery("getDeductionAvailableLevelsWithWhere");
        rsContractIdFilters = rsContractIdFilters.replace("@PARAMETERS", " RS_CONTRACT_SID ");
        String filter = ARMUtils.getLevelMap(deductionMap.get(1)[1])[0] + " in ('" + dedval.getRsCategory() + "') AND "
                + ARMUtils.getLevelMap(deductionMap.get(NumericConstants.TWO)[1])[0] + " in ( '" + dedval.getRsType() + "' ) AND "
                + ARMUtils.getLevelMap(deductionMap.get(NumericConstants.THREE)[1])[0] + " in ('" + dedval.getRsProg() + "')"
                + "AND RS_CONTRACT_SID " + " in ('" + dedval.getActualValue() + "')";
        rsContractIdFilters = rsContractIdFilters.replace("$$$FILTER$$$", filter);
        List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(rsContractIdFilters);
        for (Object obj : list) {
            rsContractSidList.add((int) obj);
        }

        String retLitString = " RS_CONTRACT_SID IN (" + StringUtils.join(list.toArray(), ",") + ") ";
        return retLitString;
    }

    public Map<String, DeductionLevelDTO> getDeductionTree(Map<String, Set<Integer>> map, Set<Integer> rsContractSids, List<String> hierarchyKeys) {
        try {
            String query = SQlUtil.getQuery("LoadSelectedDeductions");
            String rsContractQuery = SQlUtil.getQuery("LoadSelectedRSContract");
            if (!map.isEmpty()) {
                String filterQuery = "";

                Map<String, String> filterSelect = ARMUtils.getDeductionValuesMapForLevel();
                int index = 0;
                for (Map.Entry<String, Set<Integer>> entrySet : map.entrySet()) {
                    String key = entrySet.getKey();
                    Set<Integer> value = entrySet.getValue();
                    filterQuery += index == 0 ? filterSelect.get(key) : " AND " + filterSelect.get(key);
                    filterQuery += " IN " + value.toString().replace(", ", "','").replace("[", "('").replace("]", "')");
                }
                map.clear();
                rsContractQuery += filterQuery;
                List<Object> returnList = QueryUtils.executeSelect(rsContractQuery);
                rsContractSids.addAll(Arrays.asList(Arrays.copyOf(returnList.toArray(), returnList.size(), Integer[].class)));
            }
             if (rsContractSids.isEmpty()) {
                query = query.replace("@RS_CONTRACT_SID", rsContractSids.toString().replace("[", "'").replace("]", "'"));
            } else {
                query = query.replace("@RS_CONTRACT_SID", rsContractSids.toString().replace("[", "").replace("]", ""));
            }
            List<Object[]> returnQueryList = QueryUtils.executeSelect(query);
            return cutomizeSelectedDecuction(returnQueryList, hierarchyKeys);
        } catch (Exception ex) {
            LOGGER.error(ex);
            return Collections.EMPTY_MAP;
        }

    }

    private Map<String, DeductionLevelDTO> cutomizeSelectedDecuction(List<Object[]> returnList, List<String> hierarchyKeys) {
        try {
            Map<String, String> parentLevelKeys = new HashMap<>();
            Map<String, DeductionLevelDTO> levelKeys = new HashMap<>();
            Map<String, Integer> columnIncrementer = new HashMap<>();
            for (Object[] obj : returnList) {
                String parentKey = "";
                for (int keyIndex = 1; keyIndex <= NumericConstants.NINE; keyIndex++) {
                    String value = obj[keyIndex - 1].toString();
                    String valArry[] = value.split("~");
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
                    String rsContractIds[] = String.valueOf(obj[NumericConstants.NINE]).split(",");
                    levelDto.setRsContractSids(new HashSet(Arrays.asList(rsContractIds)));
                    DeductionLevelDTO oldLevelDto = levelKeys.put(currentKey, levelDto);
                    hierarchyKeys.add(currentKey);
                    if (oldLevelDto != null) {
                        levelDto.getRsContractSids().addAll(oldLevelDto.getRsContractSids());
                    }
                    parentKey = currentKey;
                }
            }
            return levelKeys;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        }
    }

    public List<Integer> getRsContractSids(int projectionId) {
        List input = new ArrayList();
        List<Integer> listRSContractsids = new ArrayList();
        input.add(projectionId);
        List<Object> rawList = QueryUtils.getItemData(input, "LoadRsContractSid", null);
        for (int i = 0; i < rawList.size(); i++) {
            listRSContractsids.add(Integer.valueOf(rawList.get(i).toString()));
        }
        return listRSContractsids;
    }

    public static void getDates(int buscinessProcess, int companySystemId, int buSid, ComboBox fromPeriod, ComboBox toPeriod, boolean checkFlag) {

        List<Date> periodDates = CommonLogic.getPeriodList("ARM", buscinessProcess, companySystemId, buSid);
        Date currentDate = new Date();
        Date fromDate = periodDates.get(0);
        Date fromDefDate = periodDates.get(1);
        Date toDate = periodDates.get(NumericConstants.TWO);
        Date toDefDate = periodDates.get(NumericConstants.THREE);
        getPeriods(fromDate, currentDate, fromPeriod);
        if (checkFlag) {
            getPeriods(currentDate, toDate, toPeriod);
        } else {
            getPeriods(fromDate, currentDate, toPeriod);
        }
        fromPeriod.select(getPeriod(fromDefDate));
        toPeriod.select(getPeriod(toDefDate));
        fromPeriod.setImmediate(true);
        toPeriod.setImmediate(true);
    }

    /**
     *
     * @param startDate
     * @param endDate
     * @param period
     */
    private static void getPeriods(final Date startDate, final Date endDate, final ComboBox period) {
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
    private static String getPeriod(Date date) {
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
        Date currentDate = new Date();
        Date fromDate = periodDates.get(0);
        Date fromDefDate = periodDates.get(1);
        getPeriods(fromDate, currentDate, toPeriod);
        toPeriod.select(getPeriod(fromDefDate));
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
        input.add(dataselectionDTO.getBu_companyMasterSid());
        getGlCompAndBuClosedList(); // for Business Unit Company
        clearLists();
        input.add(dataselectionDTO.getProjectionId());
        dataselectionDTO.setNewClosedSummary_glList(newClose);
        return approveFlag ? checkGLlistClose(input, newClose) : false;

    }

    public void getGlCompAndBuClosedList() {
        List<Object[]> Gllist = QueryUtils.getItemData(input, "glImpactdateList", null);
        openCloseGLCompAndBU(Gllist);
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
}
