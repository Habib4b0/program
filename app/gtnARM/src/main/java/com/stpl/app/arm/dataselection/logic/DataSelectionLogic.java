/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.dataselection.logic;

import com.liferay.portal.kernel.model.User;
import java.sql.Timestamp;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
import static com.stpl.app.arm.utils.DataSelectionQueryUtils.getGsnWsSecurityToken;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.utils.CommonUtils;
import com.stpl.app.utils.xmlparser.SQlUtil;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.arm.dataselection.bean.GtnARMHierarchyInputBean;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnFrameworkRelationshipLevelDefintionBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.arm.GtnWsArmRequest;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.filter.Between;
import com.vaadin.v7.data.util.filter.Compare;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import com.vaadin.server.VaadinSession;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.TreeTable;
import java.text.ParseException;

/**
 *
 * @author
 */
public class DataSelectionLogic {

    public static final Logger LOGGER = LoggerFactory.getLogger(DataSelectionLogic.class);
    private final HelperListUtil helperListUtil = HelperListUtil.getInstance();
    private List closedinput;
    private List openinput;
    private List input;
    private List newClose;

    public DataSelectionLogic() {
        LOGGER.debug("Inside DataSelectionLogic COnstructor");

    }

    public List searchLogicForHierarchy(HierarchyLookupDTO hierarchyLookupDTO, boolean isCount, final int startIndex, final int endIndex, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet) {
        LOGGER.debug("--Inside searchLogicForHierarchy--");
        List<HierarchyLookupDTO> resultList = new ArrayList<>();
        String filterString;
        String sqlQuery;
        String hierName = hierarchyLookupDTO.getHierarchySearchName();
        String hierType = hierarchyLookupDTO.getHierarchySearchType();
        String lookupName = hierarchyLookupDTO.getLookupSearchName();

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
        StringBuilder filterQuery = new StringBuilder();
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
                    filterString = "%" + stringFilter.getFilterString() + "%";
                    filterQuery.append(filterQuery).append(CommonConstant.AND).append(detailsColumn.get(String.valueOf(stringFilter.getPropertyId())));
                    filterQuery.append(" like '").append(filterString).append(ARMUtils.SINGLE_QUOTES);
                } else if (filter instanceof Between) {
                    Between dsBetweenFilter = (Between) filter;
                    StringBuilder dsDateStartstr = new StringBuilder("AND ( * >='?')");
                    StringBuilder dsDateEndstr = new StringBuilder("AND ( * <='?')");
                    if (!detailsColumn.get(dsBetweenFilter.getPropertyId().toString()).isEmpty()) {
                        Date startValue = (Date) dsBetweenFilter.getStartValue();
                        Date endValue = (Date) dsBetweenFilter.getEndValue();
                        StringBuilder initialStart = new StringBuilder("AND  ( * >= '?' )");
                        StringBuilder initialEnd = new StringBuilder("AND  ( * <= '?' )");
                        if (!dsBetweenFilter.getStartValue().toString().isEmpty()) {
                            StringBuilder dsTempStart;
                            if (filterQuery.length() == 0) {
                                dsTempStart = new StringBuilder(initialStart);
                            } else {
                                dsTempStart = new StringBuilder(dsDateStartstr);
                            }
                            dsTempStart.replace(dsTempStart.indexOf(ARMUtils.CHAR_ASTERISK), dsTempStart.indexOf(ARMUtils.CHAR_ASTERISK) + 1, detailsColumn.get(dsBetweenFilter.getPropertyId().toString()));
                            dsTempStart.replace(dsTempStart.indexOf(ARMUtils.CHAR_QUS), dsTempStart.indexOf(ARMUtils.CHAR_QUS) + 1, ARMUtils.getInstance().getDbDate().format(startValue));
                            filterQuery.append(dsTempStart);
                        }
                        if (!dsBetweenFilter.getEndValue().toString().isEmpty()) {
                            StringBuilder dsTempEnd;
                            if (filterQuery.length() == 0) {
                                dsTempEnd = new StringBuilder(initialEnd);
                            } else {
                                dsTempEnd = new StringBuilder(dsDateEndstr);
                            }

                            dsTempEnd.replace(dsTempEnd.indexOf(ARMUtils.CHAR_ASTERISK), dsTempEnd.indexOf(ARMUtils.CHAR_ASTERISK) + 1, detailsColumn.get(dsBetweenFilter.getPropertyId().toString()));
                            dsTempEnd.replace(dsTempEnd.indexOf(ARMUtils.CHAR_QUS), dsTempEnd.indexOf(ARMUtils.CHAR_QUS) + 1, ARMUtils.getInstance().getDbDate().format(endValue));
                            filterQuery.append(dsTempEnd);
                        }
                    }
                } else if (filter instanceof Compare) {
                    Compare dsStringFilter = (Compare) filter;
                    if (!detailsColumn.get(dsStringFilter.getPropertyId().toString()).isEmpty()) {
                        Compare.Operation dsCompareOperation = dsStringFilter.getOperation();
                        if (Compare.Operation.EQUAL.toString().equals(dsCompareOperation.name())) {
                            StringBuilder dsstartStr = new StringBuilder("AND ( * ='?')");
                            StringBuilder dsIntStartstr = new StringBuilder("AND ( * = '?' )");
                            StringBuilder dsTempStart;
                            String dsValue;
                            if (((Integer) dsStringFilter.getValue()) == 0) {
                                dsValue = String.valueOf(dsStringFilter.getValue());
                            } else {
                                int val = (Integer) dsStringFilter.getValue();
                                dsValue = String.valueOf(val);
                            }
                            if (!dsValue.isEmpty()) {
                                if (filterQuery.length() == 0) {
                                    dsTempStart = new StringBuilder(dsIntStartstr);
                                } else {
                                    dsTempStart = new StringBuilder(dsstartStr);
                                }
                                dsTempStart.replace(dsTempStart.indexOf(ARMUtils.CHAR_ASTERISK), dsTempStart.indexOf(ARMUtils.CHAR_ASTERISK) + 1, detailsColumn.get(dsStringFilter.getPropertyId().toString()));
                                dsTempStart.replace(dsTempStart.indexOf(ARMUtils.CHAR_QUS), dsTempStart.indexOf(ARMUtils.CHAR_QUS) + 1, dsValue);
                                filterQuery.append(dsTempStart);
                            }
                        }
                        if (Compare.Operation.GREATER.toString().equals(dsCompareOperation.name())) {
                            StringBuilder greaterTempStart;
                            String value = StringUtils.EMPTY;
                            int val = (Integer) dsStringFilter.getValue();
                            if (val < 0) {
                                if (filterQuery.length() == 0) {
                                    greaterTempStart = new StringBuilder("AND ( * >'?' or * = '0')");
                                } else {
                                    greaterTempStart = new StringBuilder("AND ( * > '?' or * = '0')");
                                }
                                greaterTempStart.replace(greaterTempStart.indexOf(ARMUtils.CHAR_ASTERISK), greaterTempStart.indexOf(ARMUtils.CHAR_ASTERISK) + 1, detailsColumn.get(dsStringFilter.getPropertyId().toString()));
                                greaterTempStart.replace(greaterTempStart.indexOf(ARMUtils.CHAR_QUS), greaterTempStart.indexOf(ARMUtils.CHAR_QUS) + 1, value);
                                filterQuery.append(greaterTempStart);
                            } else {
                                if (filterQuery.length() == 0) {
                                    greaterTempStart = new StringBuilder("AND ( * >'?')");
                                } else {
                                    greaterTempStart = new StringBuilder("AND ( * > '?')");
                                }
                                greaterTempStart.replace(greaterTempStart.indexOf(ARMUtils.CHAR_ASTERISK), greaterTempStart.indexOf(ARMUtils.CHAR_ASTERISK) + 1, detailsColumn.get(dsStringFilter.getPropertyId().toString()));
                                greaterTempStart.replace(greaterTempStart.indexOf(ARMUtils.CHAR_QUS), greaterTempStart.indexOf(ARMUtils.CHAR_QUS) + 1, value);
                                filterQuery.append(greaterTempStart);
                            }
                        }
                        if (dsCompareOperation.LESS.toString().equals(dsCompareOperation.name())) {
                            int val = (Integer) dsStringFilter.getValue();
                            StringBuilder tempStart;
                            String value = StringUtils.EMPTY;
                            if (val > 0) {
                                if (filterQuery.length() == 0) {
                                    tempStart = new StringBuilder("AND ( * <'?' or * = '0')");
                                } else {
                                    tempStart = new StringBuilder("AND ( * < '?' or * = '0')");
                                }
                                tempStart.replace(tempStart.indexOf(ARMUtils.CHAR_ASTERISK), tempStart.indexOf(ARMUtils.CHAR_ASTERISK) + 1, detailsColumn.get(dsStringFilter.getPropertyId().toString()));
                                tempStart.replace(tempStart.indexOf(ARMUtils.CHAR_QUS), tempStart.indexOf(ARMUtils.CHAR_QUS) + 1, value);
                                filterQuery.append(tempStart);
                            } else {
                                if (filterQuery.length() == 0) {
                                    tempStart = new StringBuilder("AND ( * <'?')");
                                } else {
                                    tempStart = new StringBuilder("AND ( * < '?')");
                                }
                                tempStart.replace(tempStart.indexOf(ARMUtils.CHAR_ASTERISK), tempStart.indexOf(ARMUtils.CHAR_ASTERISK) + 1, detailsColumn.get(dsStringFilter.getPropertyId().toString()));
                                tempStart.replace(tempStart.indexOf(ARMUtils.CHAR_QUS), tempStart.indexOf(ARMUtils.CHAR_QUS) + 1, value);
                                filterQuery.append(tempStart);
                            }
                        }
                        if (dsStringFilter.getValue() instanceof Date) {
                            Date value = (Date) dsStringFilter.getValue();
                            StringBuilder tempStart;
                            if (Compare.Operation.GREATER_OR_EQUAL.toString().equals(dsCompareOperation.name())) {
                                if (filterQuery.length() == 0) {
                                    tempStart = new StringBuilder("AND ( * >= '?')");
                                } else {
                                    tempStart = new StringBuilder("AND ( * >='?')");
                                }
                            } else {
                                if (filterQuery.length() == 0) {
                                    tempStart = new StringBuilder("AND ( * <='?')");
                                } else {
                                    tempStart = new StringBuilder("AND ( * <='?' )");
                                }
                            }
                            tempStart.replace(tempStart.indexOf(ARMUtils.CHAR_ASTERISK), tempStart.indexOf(ARMUtils.CHAR_ASTERISK) + 1, detailsColumn.get(dsStringFilter.getPropertyId().toString()));
                            tempStart.replace(tempStart.indexOf(ARMUtils.CHAR_QUS), tempStart.indexOf(ARMUtils.CHAR_QUS) + 1, ARMUtils.getInstance().getDbDate().format(value));
                            filterQuery.append(tempStart);
                        }
                    }
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
            order = order + ARMUtils.SPACE + "OFFSET ";
            order = order + startIndex;
            order = order + " ROWS FETCH NEXT " + endIndex;
            order = order + " ROWS ONLY;";
        }
        if (isCount) {
            finalQuery = sqlQuery + filterQuery.toString();
        } else {
            finalQuery = sqlQuery + filterQuery.toString() + order;
        }
        LOGGER.debug("finalQuery -- {}  ", finalQuery);
        if (isCount) {
            return HelperTableLocalServiceUtil.executeSelectQuery(finalQuery);
        }
        List<Object[]> list = HelperTableLocalServiceUtil.executeSelectQuery(finalQuery);
        for (Object[] obj : list) {
            HierarchyLookupDTO hdto = new HierarchyLookupDTO();
            hdto.setHierarchyId(ARMUtils.getIntegerValue(obj[0].toString()));
            hdto.setHierarchyName(String.valueOf(obj[1].toString()));
            hdto.setHighestLevel(String.valueOf(obj[NumericConstants.THREE].toString()));
            hdto.setLowestLevel(String.valueOf(obj[NumericConstants.FOUR].toString()));
            hdto.setCreatedDate(parseDate(obj[NumericConstants.FIVE]));
            hdto.setModifiedDate(parseDate(obj[NumericConstants.SIX]));
            hdto.setVersionNo(Integer.parseInt(String.valueOf(obj[NumericConstants.SEVEN].toString())));
            resultList.add(hdto);
        }
        LOGGER.debug("--Ending searchLogicForHierarchy--{}", resultList.size());
        return resultList;
    }

    private static Date parseDate(Object value) {
        Date date = null;
        SimpleDateFormat parseDate = new SimpleDateFormat(ARMUtils.YYYY_MM_DD_HH_MM_SS_SSS);
        if (value != null && !StringUtils.EMPTY.equals(value) && !ARMUtils.NULL.equals(value)) {
            try {
                return parseDate.parse(String.valueOf(value));
            } catch (ParseException ex) {
                LOGGER.error("Errro in searchLogicForHierarchy Date :", ex);
            }
        }
        return date;
    }

    public Map<Integer, Integer> loadCustomerRelation(ComboBox crComboBox, int hierSid) {

        String sqlQuery = SQlUtil.getQuery("getCustRelation").concat(StringUtils.EMPTY + hierSid);
        LOGGER.debug("--Inside loadCustomerRelation--");
        LOGGER.debug(CommonConstant.SQL_QUERY, sqlQuery);
        List<Object[]> list = HelperTableLocalServiceUtil.executeSelectQuery(sqlQuery);
        return getComboBoxMap(crComboBox, list);
    }

    public Map<Integer, Integer> loadProductRelation(ComboBox prcomboBox, int hierSid, int glComp) {
        LOGGER.debug("--Inside loadProductRelation--");
        String sqlQuery = SQlUtil.getQuery("getProdRelation").concat(StringUtils.EMPTY + hierSid);
        if (glComp != 0) {
            sqlQuery += ARMUtils.SPACE + SQlUtil.getQuery("joinGLComp").concat(StringUtils.EMPTY + glComp);
        }
        LOGGER.debug(CommonConstant.SQL_QUERY, sqlQuery);
        List<Object[]> list = HelperTableLocalServiceUtil.executeSelectQuery(sqlQuery);
        return getComboBoxMap(prcomboBox, list);
    }

    private Map<Integer, Integer> getComboBoxMap(ComboBox comboBox, List<Object[]> list) {
        Map<Integer, Integer> map = new HashMap<>();
        comboBox.removeAllItems();
        comboBox.addItem(GlobalConstants.getSelectOne());
        comboBox.setNullSelectionAllowed(true);
        comboBox.setNullSelectionItemId(GlobalConstants.getSelectOne());
        for (Object[] obj : list) {
            comboBox.addItem(obj[0]);
            comboBox.setItemCaption(obj[0], String.valueOf(obj[1]));
            map.put((Integer) obj[0], (Integer) obj[2]);
        }
        return map;
    }

    public List<Integer> loadCustoProdLevels(ComboBox comboBox, int hierSid) {
        LOGGER.debug("--Inside loadCustoProdLevels--");
        String customSql = SQlUtil.getQuery("getLevelsFromHierarchy");
        if (StringUtils.isNotEmpty(String.valueOf(hierSid))
                && StringUtils.isNotBlank(String.valueOf(hierSid))) {
            customSql = customSql.replace(ARMUtils.CHAR_QUS, String.valueOf(hierSid).trim());
        }
        LOGGER.debug(CommonConstant.SQL_QUERY, customSql);
        List<Integer> integerList = new ArrayList<>();
        List<Object[]> list = HelperTableLocalServiceUtil.executeSelectQuery(customSql);
        comboBox.removeAllItems();
        comboBox.addItem(GlobalConstants.getSelectOne());
        comboBox.setNullSelectionAllowed(true);
        comboBox.setNullSelectionItemId(GlobalConstants.getSelectOne());
        for (Object[] obj : list) {
            comboBox.addItem(obj[1]);
            comboBox.setItemCaption(obj[1], ARMUtils.LEVEL + ARMUtils.SPACE + obj[1] + ARMUtils.SPACE + ARMUtils.HIPHEN + ARMUtils.SPACE + obj[0]);
            integerList.add((Integer) obj[5]);
        }
        LOGGER.debug("--End loadCustoProdLevels--");
        return integerList;
    }

    public void insertToCcpMap(final String hierarchyIndicator, final String tempTableName, final String hierarchyDefinitionSid, final String relationshipBuilderSid) {
        DataSelectionQueryUtils.insertIntoCcpMap(hierarchyIndicator, tempTableName, hierarchyDefinitionSid, relationshipBuilderSid);
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
                leveldto.setHierarchyLevelDefnId(object[NumericConstants.FIVE] == null ? StringUtils.EMPTY : String.valueOf(object[NumericConstants.FIVE]));
                resultList.add(leveldto);
            }
        } catch (Exception e) {
            LOGGER.error(" in DSLogic - loadCustomerForecastLevel", e);
        }
        LOGGER.debug("--End of loadCustomerForecastLevel--");
        return resultList;
    }

    public List<LevelDTO> loadCustomerInnerLevel(GtnARMHierarchyInputBean inputBean, final Map<String, String> descriptionMap, LevelDTO selectedHierarchyLevelDto) {
        LOGGER.debug("--Inside loadCustomerInnerLevel--");
        List<LevelDTO> values = new ArrayList<>();
        List result = DataSelectionQueryUtils.getCustomerInnerLevel(inputBean);
        if (result != null && !result.isEmpty()) {
            LevelDTO dto;
            for (int i = 0; i < result.size(); i++) {
                dto = new LevelDTO(selectedHierarchyLevelDto);
                Object[] obj = (Object[]) result.get(i);
                dto.setDisplayValue(descriptionMap.get(obj[NumericConstants.FOUR].toString()));
                dto.setLevel(descriptionMap.get(obj[NumericConstants.FOUR].toString()));
                dto.setRelationshipLevelValue(String.valueOf(obj[NumericConstants.ZERO]));
                dto.setLevelNo(Integer.parseInt(String.valueOf(obj[NumericConstants.ONE])));
                dto.setParentNode(String.valueOf(obj[NumericConstants.TWO]));
                dto.setRelationshipLevelSid(Integer.parseInt(String.valueOf(obj[NumericConstants.THREE])));
                dto.setHierarchyNo(String.valueOf(obj[NumericConstants.FOUR]));
                dto.setRelationShipBuilderId(String.valueOf(obj[NumericConstants.FIVE]));
                if (inputBean.isIsNdc()) {
                    dto.setStrength(String.valueOf(obj[NumericConstants.SIX]));
                    dto.setForm(String.valueOf(obj[NumericConstants.SEVEN]));
                }
                dto.setRelationShipVersionNo(inputBean.getRelationVersionNo());
                values.add(dto);
            }
        }
        LOGGER.debug("--End of loadCustomerInnerLevel--");
        return values;
    }

    public List<LevelDTO> loadProductInnerLevel(GtnARMHierarchyInputBean inputBean, final Map<String, String> descriptionMap) {
        LOGGER.debug("--Inside loadProductInnerLevel--");
        List<LevelDTO> values = new ArrayList<>();
        List result = DataSelectionQueryUtils.getProductInnerLevel(inputBean);

        if (result != null && !result.isEmpty()) {
            LevelDTO dto;
            for (int i = 0; i < result.size(); i++) {
                dto = new LevelDTO();
                Object[] obj = (Object[]) result.get(i);
                dto.setRelationshipLevelValue(String.valueOf(obj[NumericConstants.ZERO]));
                dto.setLevelNo(Integer.parseInt(String.valueOf(obj[NumericConstants.ONE])));
                dto.setParentNode(String.valueOf(obj[NumericConstants.TWO]));
                dto.setRelationshipLevelSid(Integer.parseInt(String.valueOf(obj[NumericConstants.THREE])));
                dto.setHierarchyNo(String.valueOf(obj[NumericConstants.FOUR]));
                dto.setRelationShipBuilderId(String.valueOf(obj[NumericConstants.FIVE]));
                dto.setLevel(descriptionMap != null ? descriptionMap.get(obj[NumericConstants.ONE].toString()) : StringUtils.EMPTY);
                if (inputBean.isIsNdc()) {
                    dto.setNdc(String.valueOf(obj[NumericConstants.ZERO]));
                    dto.setForm(String.valueOf(obj[NumericConstants.SIX]));
                    dto.setStrength(String.valueOf(obj[NumericConstants.SEVEN]));
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

    public List<LevelDTO> getParentLevelsWithHierarchyNo(final String hierarchyNos, final Map<String, String> descriptionMap, String relationBuilderSid) {
        LOGGER.debug("--Inside getParentLevelsWithHierarchyNo--");
        List resultss;
        List<LevelDTO> parentList = null;
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("hierarchyNos", hierarchyNos);
        parameters.put(ARMUtils.INDICATOR, "getParentLevelsWithHierarchyNo");
        LevelDTO parentDto;

        try {
            resultss = DataSelectionQueryUtils.getParentLevels(0, parameters, relationBuilderSid);

            if (resultss != null) {
                parentList = new ArrayList<>();
                for (int loop = 0, limit = resultss.size(); loop < limit; loop++) {
                    Object[] objects = (Object[]) resultss.get(loop);
                    parentDto = new LevelDTO();
                    parentDto.setLevelNo(Integer.parseInt(String.valueOf(objects[0])));
                    parentDto.setRelationshipLevelValue(String.valueOf(objects[1]));
                    parentDto.setParentNode(String.valueOf(objects[NumericConstants.TWO]));
                    parentDto.setLevel(String.valueOf(objects[NumericConstants.THREE]));
                    parentDto.setLevelValueReference(String.valueOf(objects[NumericConstants.FOUR]));
                    parentDto.setTableName(String.valueOf(objects[NumericConstants.FIVE]));
                    parentDto.setFieldName(String.valueOf(objects[NumericConstants.SIX]));
                    parentDto.setRelationshipLevelSid(Integer.parseInt(String.valueOf(objects[NumericConstants.SEVEN])));
                    parentDto.setHierarchyNo(String.valueOf(objects[NumericConstants.EIGHT]));
                    parentDto.setRelationShipBuilderId(String.valueOf(objects[NumericConstants.NINE]));
                    parentDto.setRelationShipVersionNo((int) objects[NumericConstants.TEN]);

                    if (descriptionMap != null) {
                        parentDto.setDisplayValue(descriptionMap.get(String.valueOf(objects[NumericConstants.EIGHT])));
                    }
                    parentList.add(parentDto);
                }
            }
        } catch (Exception ex) {
            LOGGER.error("Error in getParentLevelsWithHierarchyNo :", ex);
        }
        LOGGER.debug("--End of  getParentLevelsWithHierarchyNo--");
        return parentList;
    }

    public List<LevelDTO> getChildLevelsWithHierarchyNo(String hierarchyNo, int lowestLevelNo, final Map<String, String> descriptionMap) {
        LOGGER.debug("--Inside getChildLevelsWithHierarchyNo--");
        List resultss;
        List<LevelDTO> resultList = null;
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(ARMUtils.HIERARACHY_NO_PROPERTY, hierarchyNo);
        parameters.put("lowestLevelNo", lowestLevelNo);
        parameters.put(ARMUtils.INDICATOR, "getChildLevelsWithHierarchyNo");
        LevelDTO childDto;

        try {
            resultss = DataSelectionQueryUtils.getChildLevels(parameters);

            if (resultss != null) {
                resultList = new ArrayList<>();
                for (int loop = 0, limit = resultss.size(); loop < limit; loop++) {
                    Object[] objects = (Object[]) resultss.get(loop);
                    childDto = new LevelDTO();
                    childDto.setLevelNo(Integer.parseInt(String.valueOf(objects[0])));
                    childDto.setRelationshipLevelValue(String.valueOf(objects[1]));
                    childDto.setParentNode(String.valueOf(objects[NumericConstants.TWO]));
                    childDto.setLevel(String.valueOf(objects[NumericConstants.THREE]));
                    childDto.setLevelValueReference(String.valueOf(objects[NumericConstants.FOUR]));
                    childDto.setTableName(String.valueOf(objects[NumericConstants.FIVE]));
                    childDto.setFieldName(String.valueOf(objects[NumericConstants.SIX]));
                    childDto.setRelationshipLevelSid(Integer.parseInt(String.valueOf(objects[NumericConstants.SEVEN])));
                    childDto.setHierarchyNo(String.valueOf(objects[NumericConstants.EIGHT]));
                    childDto.setRelationShipBuilderId(String.valueOf(objects[NumericConstants.NINE]));
                    if (descriptionMap != null) {
                        childDto.setDisplayValue(descriptionMap.get(String.valueOf(objects[NumericConstants.EIGHT])));
                    }
                    resultList.add(childDto);
                }
            }
        } catch (Exception ex) {
            LOGGER.error("Error in getChildLevelsWithHierarchyNo :", ex);
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
            LOGGER.error("in getFSValue", e);
            return Collections.emptyList();
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
        List<String> searchViewList = new ArrayList<>();
        searchViewList.add(0, viewName);
        searchViewList.add(1, viewType);
        searchViewList.add(2, adjustmentType);
        final String userId = (String) VaadinSession.getCurrent().getAttribute(ARMUtils.USER_ID);
        searchViewList.add(3, userId);
        String viewValue = StringUtils.EMPTY;
        if (viewName.isEmpty()) {
            viewValue = ARMUtils.CHAR_PERCENT;
        }
        if (StringUtils.isNotBlank(viewName)) {
            viewValue = viewName.replace(ARMUtils.CHAR_ASTERISK, ARMUtils.CHAR_PERCENT);
        }
        if (!"Calculation Profile".equals(adjustmentType)) {
            list = DataSelectionQueryUtils.findViewByName(searchViewList, viewValue, isCount, filters, sortByColumns, 0, 0);
        } else {
            list = DataSelectionQueryUtils.loadCalculationViewSearch(searchViewList, viewValue, isCount, filters, sortByColumns, 0, 0);
        }
        int count = 0;
        if (list != null && !list.isEmpty()) {
            count = Integer.parseInt(list.get(0) + StringUtils.EMPTY);
        }
        LOGGER.debug("End of searhViewCount method");
        return count;
    }

    public List searhView(List<String> viewInputs, boolean isCount, Set<Container.Filter> filters, List<SortByColumn> sortByColumns, int start, int offset) {
        LOGGER.debug("Entering searchView method");
        List list = null;
        final String userId = (String) VaadinSession.getCurrent().getAttribute(ARMUtils.USER_ID);
        viewInputs.add(3, userId);
        String viewValue = StringUtils.EMPTY;
        if (StringUtils.isNotBlank(viewInputs.get(0))) {
            viewValue = viewInputs.get(0).replace(ARMUtils.CHAR_ASTERISK, ARMUtils.CHAR_PERCENT);
        }
        if (!"Calculation Profile".equals(viewInputs.get(2))) {
            list = DataSelectionQueryUtils.findViewByName(viewInputs, viewValue, isCount, filters, sortByColumns, start, offset);
        } else {
            list = DataSelectionQueryUtils.loadCalculationViewSearch(viewInputs, viewValue, isCount, filters, sortByColumns, start, offset);
        }
        LOGGER.debug("End of searchView method");
        return list;
    }

    public List<ViewDTO> getCustomizedViews(final List list) {
        final List<ViewDTO> results = new ArrayList<>();
        LOGGER.debug("Entering getCustomizedViews method with list size {} ", list.size());
        Map<Integer, HelperDTO> helperMap = helperListUtil.getIdHelperDTOMap();
        for (int i = 0; i < list.size(); i++) {
            final Object[] obj = (Object[]) list.get(i);
            final ViewDTO result = new ViewDTO();
            result.setProjectionDescription(convertNullToEmpty(String.valueOf(obj[0])));
            result.setAdjustmentId(obj[1] != null ? (int) obj[1] : 0);
            result.setAdjustmentType(convertNullToEmpty(String.valueOf(obj[NumericConstants.TWO])));
            result.setCompanyMasterSid(obj[NumericConstants.THREE] == null ? 0 : (int) obj[NumericConstants.THREE]);
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
            result.setCreatedByString((result.getViewCreatedBy() != 0) ? CommonUtils.getUserMap().get(result.getViewCreatedBy()) : StringUtils.EMPTY);
            if ((helperMap.get(result.getDeductionLevel()) != null) && result.getDeductionLevel() != 0) {
                result.setDeductionLevels((helperMap.get(result.getDeductionLevel())).getDescription());
            } else {
                result.setDeductionLevels(StringUtils.EMPTY);
            }
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
        LOGGER.debug("Entering getCalculationCustomizedViews method with list size  {}", list.size());
        for (int i = 0; i < list.size(); i++) {
            final Object[] obj = (Object[]) list.get(i);
            final ViewDTO result = new ViewDTO();
            result.setViewId(CommonLogic.convertNullToEmpty(String.valueOf(obj[0])));
            result.setViewName(CommonLogic.convertNullToEmpty(String.valueOf(obj[1])));
            result.setViewType(CommonLogic.convertNullToEmpty(String.valueOf(obj[2])));
            String crBy = CommonLogic.convertNullToEmpty(String.valueOf(obj[6]));
            result.setViewCreatedBy(StringUtils.isNotBlank(crBy) ? Integer.parseInt(crBy) : 0);
            result.setCreatedByString((result.getViewCreatedBy() != 0) ? CommonUtils.getUserMap().get(result.getViewCreatedBy()) : StringUtils.EMPTY);
            result.setCreatedDate(obj[3] != null ? (Date) (obj[3]) : null);
            result.setModifiedDate(obj[4] != null ? (Date) (obj[4]) : null);

            results.add(result);

        }
        LOGGER.debug("End of getCalculationCustomizedViews method");
        return results;
    }

    public static String getUserFLName(String userId) {
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
        LOGGER.debug(CommonConstant.SQL_QUERY, sqlQuery);
        List<Object[]> list = QueryUtils.executeSelect(sqlQuery);
        for (Object[] obj : list) {
            DeductionLevelDTO level = new DeductionLevelDTO();
            int id = obj[0] == null ? 0 : (int) obj[0];
            String desc = obj[1] == null ? "0" : obj[1] + StringUtils.EMPTY;
            level.setLevelSid(id);
            level.setLevelValue(desc);
            level.setLevelName(valueLevel.getDescription());
            resultList.add(level);
        }
        LOGGER.debug("Entering loadAvailableDeductions method");
        return resultList;
    }

    public void saveCustomerHierarchyLogic(final List<LevelDTO> levelList, final List<String> endLevelSids, final int projectionId, final List<String> addLevels, final String indicator) {
        LOGGER.debug("saveCustomerHierarchyLogic endLevelSids size:{}", endLevelSids.size());
        LOGGER.debug("saveCustomerHierarchyLogic Projection Id:{}", projectionId);
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
        StringBuilder values = new StringBuilder();
        try {
            if (endLevels != null && !endLevels.isEmpty()) {
                for (Object relationshipLevelSid : endLevels) {
                    if (values.length() == 0) {
                        values.append(ARMUtils.OPEN_PARANTHESIS).append(projectionId).append(ARMUtils.COMMA).append(CommonLogic.parseStringToInteger(String.valueOf(relationshipLevelSid))).append(ARMUtils.CLOSE_PARANTHESIS);
                    } else {
                        values.append(ARMUtils.COMMA).append(" (").append(projectionId).append(ARMUtils.COMMA).append(CommonLogic.parseStringToInteger(String.valueOf(relationshipLevelSid))).append(ARMUtils.CLOSE_PARANTHESIS);
                    }
                }
            }
            saveUpdateLogic(indicator, addLevels, values, projectionId, levelList);
            sbQuery = sbQuery.replace("$$$$VALUES$$$", values.toString());
            LOGGER.debug("sbQuery--{}", sbQuery);
            if (values.length() != 0) {
                HelperTableLocalServiceUtil.executeUpdateQuery(sbQuery);
            }
        } catch (Exception e) {
            LOGGER.error(" in saveCustomerHierarchyLogic", e);
        }
    }

    public void saveAdjustmentMaster(int projectionId, DataSelectionDTO dataSelectionDTO) {
        LOGGER.debug("--Inside saveAdjustmentMaster--{}", projectionId);
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
            LOGGER.error(" in saveAdjustmentMaster", e);
        }
    }

    public void saveProductHierarchyLogic(final List<LevelDTO> levelList, final List<String> endLevelSids, final int projectionId, final List<String> addLevels, final String indicator) {
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
        StringBuilder values = new StringBuilder();
        try {
            saveUpdateLogic(indicator, addLevels, values, projectionId, levelList);
            if (endLevels != null && !endLevels.isEmpty()) {
                for (Object relationshipLevelSid : endLevels) {
                    if (values.length() == 0) {
                        values.append(ARMUtils.OPEN_PARANTHESIS).append(projectionId).append(ARMUtils.COMMA).append(CommonLogic.parseStringToInteger(String.valueOf(relationshipLevelSid))).append(ARMUtils.CLOSE_PARANTHESIS);

                    } else {
                        values.append(ARMUtils.COMMA).append(" (").append(projectionId).append(ARMUtils.COMMA).append(CommonLogic.parseStringToInteger(String.valueOf(relationshipLevelSid))).append(ARMUtils.CLOSE_PARANTHESIS);

                    }
                }
            }
            sbQuery = sbQuery.replace("$$$$VALUES$$$", values);
            LOGGER.debug("sbQuery --{}", sbQuery);
            if (values.length() != 0) {
                HelperTableLocalServiceUtil.executeUpdateQuery(sbQuery);
            }
        } catch (Exception e) {
            LOGGER.debug(" saveProductHierarchyLogic", e);
        }
    }

    private void saveUpdateLogic(final String indicator, final List<String> addLevels, StringBuilder values, final int projectionId, final List<LevelDTO> levelList) {
        if (ARMUtils.UPDATE.equals(indicator)) {
            for (String rsId : addLevels) {
                if (values.length() == 0) {
                    values.append(ARMUtils.OPEN_PARANTHESIS).append(projectionId).append(ARMUtils.COMMA).append(CommonLogic.parseStringToInteger(String.valueOf(rsId))).append(ARMUtils.CLOSE_PARANTHESIS);

                } else {
                    values.append(ARMUtils.COMMA).append(" (").append(projectionId).append(ARMUtils.COMMA).append(CommonLogic.parseStringToInteger(String.valueOf(rsId))).append(ARMUtils.CLOSE_PARANTHESIS);

                }
            }
        } else if (ARMUtils.SAVE.equals(indicator)) {
            for (LevelDTO dto : levelList) {
                if (values.length() == 0) {
                    values.append(ARMUtils.OPEN_PARANTHESIS).append(projectionId).append(ARMUtils.COMMA).append(dto.getRelationshipLevelSid()).append(ARMUtils.CLOSE_PARANTHESIS);

                } else {
                    values.append(ARMUtils.COMMA).append(" (").append(projectionId).append(ARMUtils.COMMA).append(dto.getRelationshipLevelSid()).append(ARMUtils.CLOSE_PARANTHESIS);

                }
            }
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
            viewQuery = viewQuery.replace("@PROJECTION_ID", String.valueOf(viewDTO.getProjectionSid()));
            LOGGER.debug("viewQuery --{}", viewQuery);
            HelperTableLocalServiceUtil.executeUpdateQuery(viewQuery);
        } catch (Exception ex) {
            LOGGER.debug(" saveViewLogic", ex);
        }

    }

    public void saveDeductionLogic(Set<Integer> rsModelSidList, int projectionSid) {
        try {
            StringBuilder insertDeductionQuery = new StringBuilder();
            if (!rsModelSidList.isEmpty()) {
                insertDeductionQuery.append(SQlUtil.getQuery("DEDUCTION_SAVED_PROJECTION"));
                for (Integer rsModelSid : rsModelSidList) {
                    insertDeductionQuery.append(ARMUtils.OPEN_PARANTHESIS).append(projectionSid).append(ARMUtils.COMMA_CHAR).append(rsModelSid).append("),");
                }
                insertDeductionQuery.replace(insertDeductionQuery.length() - 1, insertDeductionQuery.length(), StringUtils.EMPTY);
            }
            LOGGER.debug("insertDeductionQuery --{}", insertDeductionQuery);
            HelperTableLocalServiceUtil.executeUpdateQuery(insertDeductionQuery.toString());
        } catch (Exception ex) {
            LOGGER.debug(" save Deduction Logic", ex);
        }

    }

    public void saveCcp(final String tempTableName, final String projectionId) {
        LOGGER.debug("Inside saveCcp--{}", projectionId);
        String query = StringUtils.EMPTY;
        try {
            String sqlQuery = SQlUtil.getQuery("ccpMergeQuery");

            query = sqlQuery;
            query = query.replace("[$ST_CCP_HIERARCHY]", tempTableName);
            query = query.replace("[$PROJECTION_MASTER_SID]", projectionId);
            LOGGER.debug("query --{}", query);
            HelperTableLocalServiceUtil.executeUpdateQuery(query);
        } catch (Exception e) {
            LOGGER.error("Error in saveCcp :", e);
            LOGGER.error(query);
        }
    }

    public void updateSavedViewProjection(DataSelectionDTO dto) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(ARMUtils.YYYY_M_MDD_H_HMMSS_S);
        try {
            String updateQuery = SQlUtil.getQuery("updateProjectionMaster");
            updateQuery = updateQuery.replace("@PROJECTION_DESCRIPTION", ARMUtils.SINGLE_QUOTES + dto.getProjectionDescription() + ARMUtils.SINGLE_QUOTES);
            updateQuery = updateQuery.replace("@FORECASTING_TYPE", ARMUtils.SINGLE_QUOTES + dto.getForecastingType() + ARMUtils.SINGLE_QUOTES);
            updateQuery = updateQuery.replace("@COMPANY_MASTER_SID", dto.getCompanyMasterSid() == 0 ? ARMUtils.NULL_CAPS : StringUtils.EMPTY + ARMUtils.SINGLE_QUOTES + dto.getCompanyMasterSid() + ARMUtils.SINGLE_QUOTES);
            updateQuery = updateQuery.replace("@CUSTOMER_HIERARCHY_SID", dto.getCustomerHierarchySid() == 0 ? ARMUtils.NULL_CAPS : StringUtils.EMPTY + ARMUtils.SINGLE_QUOTES + dto.getCustomerHierarchySid() + ARMUtils.SINGLE_QUOTES);
            updateQuery = updateQuery.replace("@PRODUCT_HIERARCHY_SID", dto.getProductHierarchySid() == 0 ? ARMUtils.NULL_CAPS : StringUtils.EMPTY + ARMUtils.SINGLE_QUOTES + dto.getProductHierarchySid() + ARMUtils.SINGLE_QUOTES);
            updateQuery = updateQuery.replace("@CUSTOMER_HIERARCHY_LEVEL", "0".equals(dto.getCustomerHierarchyLevel()) ? ARMUtils.NULL_CAPS : StringUtils.EMPTY + ARMUtils.SINGLE_QUOTES + dto.getCustomerHierarchyLevel() + ARMUtils.SINGLE_QUOTES);
            updateQuery = updateQuery.replace("@PRODUCT_HIERARCHY_LEVEL", "0".equals(dto.getProductHierarchyLevel()) ? ARMUtils.NULL_CAPS : StringUtils.EMPTY + ARMUtils.SINGLE_QUOTES + dto.getProductHierarchyLevel() + ARMUtils.SINGLE_QUOTES);
            updateQuery = updateQuery.replace("@CUST_RELATIONSHIP_BUILDER_SID", dto.getCustRelationshipBuilderSid() == 0 ? ARMUtils.NULL_CAPS : StringUtils.EMPTY + ARMUtils.SINGLE_QUOTES + dto.getCustRelationshipBuilderSid() + ARMUtils.SINGLE_QUOTES);
            updateQuery = updateQuery.replace("@PROD_RELATIONSHIP_BUILDER_SID", dto.getProdRelationshipBuilderSid() == 0 ? ARMUtils.NULL_CAPS : StringUtils.EMPTY + ARMUtils.SINGLE_QUOTES + dto.getProdRelationshipBuilderSid() + ARMUtils.SINGLE_QUOTES);
            updateQuery = updateQuery.replace("@FROM_DATE", dto.getFromDate() == null ? "''" : ARMUtils.SINGLE_QUOTES + ARMUtils.getInstance().getDateFormatter().format(dto.getFromDate()) + ARMUtils.SINGLE_QUOTES);
            updateQuery = updateQuery.replace("@TO_DATE", dto.getToDate() == null ? "''" : ARMUtils.SINGLE_QUOTES + ARMUtils.getInstance().getDateFormatter().format(dto.getToDate()) + ARMUtils.SINGLE_QUOTES);
            updateQuery = updateQuery.replace(CommonConstant.PROJECTION_MASTER_SID, String.valueOf(dto.getProjectionId()));
            updateQuery = updateQuery.replace("@MODIFIED_DATE", dateFormat.format(new Date()));
            LOGGER.debug("updateQuery --{}", updateQuery);
            HelperTableLocalServiceUtil.executeUpdateQuery(updateQuery);
        } catch (Exception ex) {
            LOGGER.error("Error in updateSavedViewProjection :", ex);
        }
    }

    public int saveProjection(final DataSelectionDTO dataSelectionDTO) {
        LOGGER.debug("Inside saveProjection --{} ", dataSelectionDTO.getProjectionId());
        String finalQuery = SQlUtil.getQuery("insertProjectionMaster");
        StringBuilder queryBuilder = getSaveQuery(dataSelectionDTO);
        queryBuilder.append(ARMUtils.SINGLE_QUOTES).append(ARMUtils.NULL.equals(String.valueOf(dataSelectionDTO.getFromPeriod())) ? ARMUtils.DEFAULT_FROM_TO_DATE : dataSelectionDTO.getFromPeriod()).append(ARMUtils.SINGLE_QUOTES).append(ARMUtils.COMMA);
        queryBuilder.append(ARMUtils.SINGLE_QUOTES).append(ARMUtils.NULL.equals(String.valueOf(dataSelectionDTO.getToPeriod())) ? ARMUtils.DEFAULT_FROM_TO_DATE : dataSelectionDTO.getToPeriod()).append(ARMUtils.SINGLE_QUOTES).append(ARMUtils.COMMA);
        queryBuilder.append(ARMUtils.SINGLE_QUOTES).append(dataSelectionDTO.getSaveFlag()).append(ARMUtils.SINGLE_QUOTES).append(ARMUtils.COMMA);
        queryBuilder.append(dataSelectionDTO.getCustRelationshipBuilderSid() == 0 ? ARMUtils.NULL_CAPS : dataSelectionDTO.getCustRelationshipBuilderSid()).append(ARMUtils.COMMA);
        queryBuilder.append(dataSelectionDTO.getProdRelationshipBuilderSid() == 0 ? ARMUtils.NULL_CAPS : dataSelectionDTO.getProdRelationshipBuilderSid());
        finalQuery = finalQuery.replace("$$$$VALUE$$$$", queryBuilder.toString());
        LOGGER.debug("finalQuery - -{}", finalQuery);
        List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(finalQuery);
        LOGGER.info(list.get(0) + "");
        return Integer.parseInt(list.get(0) + StringUtils.EMPTY);
    }

    private StringBuilder getSaveQuery(final DataSelectionDTO dataSelectionDTO) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(ARMUtils.SINGLE_QUOTES).append(dataSelectionDTO.getProjectionName()).append(ARMUtils.SINGLE_QUOTES).append(ARMUtils.COMMA);
        queryBuilder.append(ARMUtils.SINGLE_QUOTES).append(dataSelectionDTO.getProjectionDescription()).append(ARMUtils.SINGLE_QUOTES).append(ARMUtils.COMMA);
        queryBuilder.append(ARMUtils.SINGLE_QUOTES).append(dataSelectionDTO.getForecastingType()).append(ARMUtils.SINGLE_QUOTES).append(ARMUtils.COMMA);
        queryBuilder.append(dataSelectionDTO.getCreatedBy()).append(ARMUtils.COMMA);
        queryBuilder.append(ARMUtils.SINGLE_QUOTES).append(new Timestamp(new Date().getTime())).append(ARMUtils.SINGLE_QUOTES).append(ARMUtils.COMMA);
        queryBuilder.append(dataSelectionDTO.getCustomerHierarchySid() == 0 ? ARMUtils.NULL_CAPS : dataSelectionDTO.getCustomerHierarchySid()).append(ARMUtils.COMMA);
        queryBuilder.append(dataSelectionDTO.getProductHierarchySid() == 0 ? ARMUtils.NULL_CAPS : dataSelectionDTO.getProductHierarchySid()).append(ARMUtils.COMMA);
        queryBuilder.append(dataSelectionDTO.getCustomerHierarchyLevel().equals("0") ? ARMUtils.NULL_CAPS : dataSelectionDTO.getCustomerHierarchyLevel()).append(ARMUtils.COMMA);
        queryBuilder.append(dataSelectionDTO.getProductHierarchyLevel().equals("0") ? ARMUtils.NULL_CAPS : dataSelectionDTO.getProductHierarchyLevel()).append(ARMUtils.COMMA);
        queryBuilder.append(dataSelectionDTO.getCustomerHierarchyVersionNo() == 0 ? ARMUtils.NULL_CAPS : dataSelectionDTO.getCustomerHierarchyVersionNo()).append(ARMUtils.COMMA);
        queryBuilder.append(dataSelectionDTO.getProductHierarchyVersionNo() == 0 ? ARMUtils.NULL_CAPS : dataSelectionDTO.getProductHierarchyVersionNo()).append(ARMUtils.COMMA);
        queryBuilder.append(dataSelectionDTO.getCompanyMasterSid() == 0 ? ARMUtils.NULL_CAPS : dataSelectionDTO.getCompanyMasterSid()).append(ARMUtils.COMMA);
        return queryBuilder;
    }

    public int saveProjectionForView(final DataSelectionDTO dataSelectionDTO) {
        LOGGER.debug("Inside saveProjection -- {}", dataSelectionDTO.getProjectionId());
        String finalQuery = SQlUtil.getQuery("insertProjectionMaster");
        StringBuilder queryBuilder = getSaveQuery(dataSelectionDTO);
        queryBuilder.append(ARMUtils.SINGLE_QUOTES).append(ARMUtils.NULL.equals(String.valueOf(dataSelectionDTO.getFromDate())) ? ARMUtils.DEFAULT_FROM_TO_DATE : ARMUtils.getInstance().getDateFormatter().format(dataSelectionDTO.getFromDate())).append(ARMUtils.SINGLE_QUOTES).append(ARMUtils.COMMA);
        queryBuilder.append(ARMUtils.SINGLE_QUOTES).append(ARMUtils.NULL.equals(String.valueOf(dataSelectionDTO.getToDate())) ? ARMUtils.DEFAULT_FROM_TO_DATE : ARMUtils.getInstance().getDateFormatter().format(dataSelectionDTO.getToDate())).append(ARMUtils.SINGLE_QUOTES).append(ARMUtils.COMMA);
        queryBuilder.append(ARMUtils.SINGLE_QUOTES).append(dataSelectionDTO.getSaveFlag()).append(ARMUtils.SINGLE_QUOTES).append(ARMUtils.COMMA);
        queryBuilder.append(dataSelectionDTO.getCustRelationshipBuilderSid() == 0 ? ARMUtils.NULL_CAPS : dataSelectionDTO.getCustRelationshipBuilderSid()).append(ARMUtils.COMMA);
        queryBuilder.append(dataSelectionDTO.getProdRelationshipBuilderSid() == 0 ? ARMUtils.NULL_CAPS : dataSelectionDTO.getProdRelationshipBuilderSid());
        finalQuery = finalQuery.replace("$$$$VALUE$$$$", queryBuilder.toString());
        LOGGER.debug("finalQuery  --{}", finalQuery);
        List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(finalQuery);
        return Integer.parseInt(list.get(0) + StringUtils.EMPTY);
    }

    public void saveAdjustmentMaster(DataSelectionDTO dataSelectionDTO) {
        LOGGER.debug("Inside saveAdjustmentMaster -- {}", dataSelectionDTO.getProjectionId());
        String sqlQuery = SQlUtil.getQuery("insertArmAdjustmentMaster");
        String values = dataSelectionDTO.getProjectionId() + ARMUtils.COMMA
                + dataSelectionDTO.getBucompanyMasterSid() + ARMUtils.COMMA
                + dataSelectionDTO.getDeductionLevel() + ARMUtils.COMMA
                + ((dataSelectionDTO.getAdjustmentId() == 0) ? null : dataSelectionDTO.getAdjustmentId());
        LOGGER.debug(CommonConstant.SQL_QUERY, sqlQuery);
        sqlQuery = sqlQuery.replace("$$$$VALUES$$$$", values);
        HelperTableLocalServiceUtil.executeUpdateQuery(sqlQuery);
    }

    public boolean isDuplicateView(String viewName, String viewType, DataSelectionDTO dataSelectionDTO) {
        LOGGER.debug("Inside isDuplicateView -- {}", viewName);
        try {
            String sqlQuery = SQlUtil.getQuery("duplicateViewCheck");
            sqlQuery = sqlQuery.replace("$$$VN$$$", viewName);
            sqlQuery = sqlQuery.replace("@VIEW_TYPE", viewType);
            sqlQuery = sqlQuery.replace("@VIEWMODE", "Balance Summary Report".equals(dataSelectionDTO.getForecastingType())
                    ? "'Balance Summary Report'" : "'Pipeline Accrual','Demand Accrual','Pipeline Inventory True-Up','Demand Payments Recon','Demand Reforecast True-up','ARM'");
            List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(sqlQuery);
            return !list.isEmpty();
        } catch (Exception ex) {
            LOGGER.error("Error in View Name Duplicated", ex);
            return true;
        }
    }

    public DataSelectionDTO getViewDetails(String projectionId) {
        LOGGER.debug("Inside getViewDetails -- {}", projectionId);
        String sqlQuery = SQlUtil.getQuery("FETCH_SAVED_PROJECTION");
        sqlQuery = sqlQuery.replace("[$PROJECTION_MASTER_SID]", projectionId);
        LOGGER.debug(CommonConstant.SQL_QUERY, sqlQuery);
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
            LOGGER.debug("index {}", index);
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
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("projectionId", projectionId);
        parameters.put(INDICATOR, indicator);
        if (levelNo == null) {
            parameters.put(RESTRICT_LEVEL_NUMBER, null);
            parameters.put(LEVEL_NO, null);
        } else {
            parameters.put(RESTRICT_LEVEL_NUMBER, Boolean.TRUE);
            parameters.put(LEVEL_NO, String.valueOf(levelNo));
        }

        List<LevelDTO> resultList = new ArrayList<>();
        LevelDTO dto;

        try {
            resultss = getRelationShipValues(parameters);
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
                dto.setDisplayValue(descriptionMap.get(String.valueOf(objects[NumericConstants.SEVEN])));
                dto.setHierarchyNo(String.valueOf(objects[NumericConstants.SEVEN]));
                dto.setLevelValueReference(String.valueOf(objects[NumericConstants.EIGHT]));
                dto.setRelationShipVersionNo((Integer) (objects[NumericConstants.NINE]));
                dto.setRelationShipBuilderId(String.valueOf(objects[NumericConstants.TEN]));
                LOGGER.debug("End of getRelationShipValues :: resultList ==>{}", resultList.size());
                resultList.add(dto);
            }
        } catch (Exception ex) {
            LOGGER.error(" in getRelationShipValues", ex);
        }
        return resultList;
    }
    private static final String INDICATOR = "indicator";
    private static final String LEVEL_NO = "levelNo";
    private static final String RESTRICT_LEVEL_NUMBER = "restrictLevelNumber";

    public List<Integer> getRSContractMasterSid(int projectionSid) {
        List<Integer> rsModelSid = new ArrayList<>();
        try {
            String rsQuery = "Select Distinct RS_CONTRACT_SID from ARM_DEDUCTION_SELECTION where PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID ;";
            rsQuery = rsQuery.replace(CommonConstant.PROJECTION_MASTER_SID, String.valueOf(projectionSid));
            LOGGER.debug("rsQuery --{}", rsQuery + " in getRSContractMasterSid");
            List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(rsQuery);
            for (int i = 0; i < list.size(); i++) {
                rsModelSid.add((Integer) list.get(i));
            }
            LOGGER.debug("rsModelSid --{}", rsModelSid.size());
            return rsModelSid;
        } catch (Exception e) {
            LOGGER.error("Error in getRSContractMasterSid :", e);
            return Collections.emptyList();
        }
    }

    public void updateSaveViewLogic(DataSelectionDTO dtoValue) {
        LOGGER.debug("--Inside updateSaveViewLogic --");
        SimpleDateFormat dateFormat = new SimpleDateFormat(ARMUtils.YYYY_M_MDD_H_HMMSS_S);
        try {
            String updateARMAdjustQuery = SQlUtil.getQuery("updateSelectedView");
            updateARMAdjustQuery = updateARMAdjustQuery.replace("@DEDUCTION_LEVEL", String.valueOf(dtoValue.getDeductionLevel()));
            updateARMAdjustQuery = updateARMAdjustQuery.replace("@ADJ_TYPE", String.valueOf(dtoValue.getAdjustmentId()));
            updateARMAdjustQuery = updateARMAdjustQuery.replace(CommonConstant.CALCULATION_PROFILE_MASTER_SID, StringUtils.EMPTY);
            updateARMAdjustQuery = updateARMAdjustQuery.replace("@BU_COMPANY_MASTER_SID", dtoValue.getBucompanyMasterSid() == 0 ? String.valueOf(dtoValue.getDefaultCompanyMasterSid()) : String.valueOf(dtoValue.getBucompanyMasterSid()));
            updateARMAdjustQuery = updateARMAdjustQuery.replace(CommonConstant.PROJECTION_MASTER_SID, String.valueOf(dtoValue.getProjectionId()));
            updateARMAdjustQuery = updateARMAdjustQuery.replace("@MODIFIED_BY", String.valueOf(dtoValue.getCreatedBy()));
            updateARMAdjustQuery = updateARMAdjustQuery.replace("@VIEW_ID", dtoValue.getViewId());
            updateARMAdjustQuery = updateARMAdjustQuery.replace("@MODIFIED_DATE", dateFormat.format(new Date()));
            LOGGER.debug("updateARMAdjustQuery --{}", updateARMAdjustQuery);
            HelperTableLocalServiceUtil.executeUpdateQuery(updateARMAdjustQuery);
            LOGGER.debug("--Exit updateSaveViewLogic --");
        } catch (Exception ex) {
            LOGGER.error(" in updateSaveViewLogic", ex);
        }

    }

    public boolean deleteViewLogic(int projectionId) {
        LOGGER.debug("--Inside deleteViewLogic --");
        String query = SQlUtil.getQuery("deletePrivatePublicViews");
        query = query.replace(CommonConstant.PROJECTION_MASTER_SID, String.valueOf(projectionId));
        LOGGER.debug("--query --{}", query);
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
        LOGGER.debug("--Exit getDuplicateFilters --{}", retLitString);
        return retLitString;
    }

    public Map<String, DeductionLevelDTO> getDeductionTree(Map<String, Set<Integer>> map, Set<Integer> rsContractSids, List<String> hierarchyKeys) {
        LOGGER.debug("--Inside getDeductionTree --");
        try {
            String query = SQlUtil.getQuery("LoadSelectedDeductions");
            String rsContractQuery = SQlUtil.getQuery("LoadSelectedRSContract");
            if (!map.isEmpty()) {
                StringBuilder filterQuery = new StringBuilder();

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
                query = query.replace("@RS_CONTRACT_SID", rsContractSids.toString().replace('[', ARMUtils.SINGLE_QUOTES).replace(']', ARMUtils.SINGLE_QUOTES));
            } else {
                query = query.replace("@RS_CONTRACT_SID", rsContractSids.toString().replace("[", StringUtils.EMPTY).replace("]", StringUtils.EMPTY));
            }
            LOGGER.debug("--query --{}", query);
            List<Object[]> returnQueryList = QueryUtils.executeSelect(query);
            LOGGER.debug("--Exit getDeductionTree --");
            return cutomizeSelectedDecuction(returnQueryList, hierarchyKeys);
        } catch (Exception ex) {
            LOGGER.error("Error in getDeductionTree :", ex);
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
                String parentKey = StringUtils.EMPTY;
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
                        currentKey = parentKey.isEmpty() ? (keyBegins++) + ARMUtils.DOT : parentKey + (keyBegins++) + ARMUtils.DOT;
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
                        Set set = levelDto.getRsContractSids();
                        set.addAll(oldLevelDto.getRsContractSids());
                        levelDto.setRsContractSids(set);
                    }
                    parentKey = currentKey;
                }
            }
            LOGGER.debug("--Exit cutomizeSelectedDecuction --{}", levelKeys.size());
            return levelKeys;
        } catch (Exception ex) {
            LOGGER.error("Error in cutomizeSelectedDecuction :", ex);
            return null;
        }
    }

    public List<Integer> getRsContractSids(int projectionId) {
        LOGGER.debug("--Inside getRsContractSids --{}", projectionId);
        List rsContractInput = new ArrayList();
        List<Integer> listRSContractsids = new ArrayList();
        rsContractInput.add(projectionId);
        List<Object> rawList = QueryUtils.getItemData(rsContractInput, "LoadRsContractSid", null);
        for (int i = 0; i < rawList.size(); i++) {
            listRSContractsids.add(ARMUtils.getIntegerValue(rawList.get(i).toString()));
        }
        LOGGER.debug("--Exit getRsContractSids --{}", listRSContractsids.size());
        return listRSContractsids;
    }

    public static void getDates(int buscinessProcess, int companySystemId, int buSid, ComboBox fromPeriod, ComboBox toPeriod, boolean checkFlag, DataSelectionDTO selection) {
        List<Date> periodDates = CommonLogic.getPeriodList("ARM", buscinessProcess, companySystemId, buSid);
        List datesInput = new ArrayList();
        datesInput.add(buscinessProcess);
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
        String item;
        String caption;
        while (isNotEnd) {

            tempCal.set(Calendar.MONTH, startmonth);
            tempCal.set(Calendar.YEAR, startYear);
            tempCal.set(Calendar.HOUR, 0);
            tempCal.set(Calendar.MINUTE, 0);
            tempCal.set(Calendar.SECOND, 0);
            item = getMonth.format(tempCal.getTime());
            caption = MONTHS[startmonth] + ARMUtils.SPACE + startYear;
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
                    caption = MONTHS[tempStartmonth] + ARMUtils.SPACE + startYear;
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
            String caption = MONTHS[month] + ARMUtils.SPACE + year;
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
        List inputs = new ArrayList();
        String periodView = StringUtils.EMPTY;
        if (adjustmentType != 0 && companyMasterSid != 0 && businessProcess != 0) {
            inputs.add(adjustmentType);
            inputs.add(companyMasterSid);
            inputs.add(businessProcess);
            List<Object> periodViewList = QueryUtils.getItemData(inputs, "loadFromAndToBasedOnPeriodConfig", null);
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
                LOGGER.debug("openOccurrences{}", openOccurrences);
                LOGGER.debug("closedOccurrences{}", closedOccurrences);
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
        String query = "SELECT COUNT(*) FROM RETURNS_MASTER WHERE VERSION LIKE '" + date + ARMUtils.SINGLE_QUOTES;
        LOGGER.debug("query --{}", query);
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

    public void updateModifiedDateLogic(int projectionId) {
        String query = "UPDATE FORECASTING_VIEW_MASTER SET MODIFIED_DATE = GETDATE() WHERE PROJECTION_ID  =" + projectionId;
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
    }

    public void updateProjectionMaster(Date fromDate, Date toDate, int projectionId) {
        String query = "update PROJECTION_MASTER set FROM_DATE = '" + ARMUtils.getInstance().getDbDate().format(fromDate) + "' ,TO_DATE = '" + ARMUtils.getInstance().getDbDate().format(toDate) + "'  where PROJECTION_MASTER_SID = " + projectionId;
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
    }

    public List getRelationShipValues(final Map<String, Object> parameters) {
        StringBuilder queryString = new StringBuilder();
        try {
            LOGGER.debug("Entering getRelationShipValues method");
            queryString.append(SQlUtil.getQuery("getRelationshipValues"));
            if ("customer".equalsIgnoreCase(String.valueOf(parameters.get(INDICATOR)))) {
                queryString.replace(queryString.indexOf(ARMUtils.CHAR_QUS), queryString.indexOf(ARMUtils.CHAR_QUS) + 1, " Projection_Cust_Hierarchy ");
            } else if ("product".equalsIgnoreCase(String.valueOf(parameters.get(INDICATOR)))) {
                queryString.replace(queryString.indexOf(ARMUtils.CHAR_QUS), queryString.indexOf(ARMUtils.CHAR_QUS) + 1, " Projection_Prod_Hierarchy ");
            }
            queryString.append(" WHERE PH.PROJECTION_MASTER_SID='");
            queryString.append(String.valueOf(parameters.get("projectionId")));
            if (parameters.get(RESTRICT_LEVEL_NUMBER) != null && "true".equalsIgnoreCase(String.valueOf(parameters.get(RESTRICT_LEVEL_NUMBER)))) {
                queryString.append("' AND RLD.LEVEL_NO <= '");
                queryString.append(String.valueOf(parameters.get(LEVEL_NO)));
            }
            queryString.append("' ORDER by RLD.level_No");
            return HelperTableLocalServiceUtil.executeSelectQuery(queryString.toString());
        } catch (Exception e) {
            LOGGER.error("getRelationShipValues  -->{}", e.getMessage());
            LOGGER.error(queryString.toString());
            return Collections.emptyList();
        }
    }

    public List<LevelDTO> getHierarchyLevelDefinition(int hierarchyDefinitionSid, int hierarchyVersionNo) {
        List<String> inputLevel = new ArrayList<>();
        List<LevelDTO> resultDtoList;
        inputLevel.add(String.valueOf(hierarchyDefinitionSid));
        inputLevel.add(String.valueOf(hierarchyVersionNo));
        List<Object[]> results = QueryUtils.getItemData(inputLevel, "getHierarchyLevelVlaues", null);
        resultDtoList = cutomizeHierarchyBean(results, hierarchyVersionNo);
        Collections.sort(resultDtoList);
        return resultDtoList;
    }

    public List<LevelDTO> cutomizeHierarchyBean(List<Object[]> results, int hierarchyVersionNo) {
        List<LevelDTO> resultDtoList = new ArrayList<>();
        for (Object[] objects : results) {
            LevelDTO levelDto = new LevelDTO();
            levelDto.setLevelNo(getIntegerValue(objects, 0));
            levelDto.setLevelValueReference(getStringValue(objects, 1));
            levelDto.setTableName(objects[2] == null ? StringUtils.EMPTY : objects[2].toString());
            levelDto.setFieldName(objects[3] == null ? StringUtils.EMPTY : objects[3].toString());
            levelDto.setLevel(objects[4] == null ? StringUtils.EMPTY : objects[4].toString());
            levelDto.setHierarchyLevelDefnId(objects[5] == null ? "0" : objects[5].toString());
            levelDto.setHierarchyId(ARMUtils.getIntegerValue(objects[6] == null ? "0" : objects[6].toString()));
            levelDto.setHierarchyType(objects[7] == null ? StringUtils.EMPTY : objects[7].toString());
            levelDto.setHierarchyVersionNo(hierarchyVersionNo);
            resultDtoList.add(levelDto);
        }
        return resultDtoList;
    }

    private String getStringValue(Object[] objects, int index) {
        return objects[index] == null ? StringUtils.EMPTY : objects[index].toString();
    }

    private Integer getIntegerValue(Object[] objects, int index) {
        return ARMUtils.getIntegerValue(objects[index] == null ? "0" : objects[index].toString());
    }

    public void ccpHierarchyInsert(Map<String, String> tempTableNames, List<LevelDTO> selectedCustomerContractList,
            List<LevelDTO> selectedProductList, DataSelectionDTO dto) {
        GtnARMHierarchyInputBean inputBean = createInputBeanForCCPInsert(tempTableNames,
                selectedCustomerContractList, selectedProductList, dto);
        insertToCCp(inputBean);
    }

    private GtnARMHierarchyInputBean createInputBeanForCCPInsert(final Map<String, String> tempTableNames,
            final List<LevelDTO> selectedCustomerContractList, final List<LevelDTO> selectedProductList,
            DataSelectionDTO dto) {

        GtnARMHierarchyInputBean inputBean = new GtnARMHierarchyInputBean();
        inputBean.setTempTableMap(tempTableNames);
        inputBean.setSelectedCustomerList(convetToRelationBean(selectedCustomerContractList));
        inputBean.setSelectedProductList(convetToRelationBean(selectedProductList));
        inputBean.setSelectedCustomerRelationShipBuilderVersionNo(dto.getCustomerRelationshipVersionNo());
        inputBean.setSelectedProductRelationShipBuilderVersionNo(dto.getProductRelationshipVersionNo());
        inputBean.setSelectedCustomerHierarcySid(dto.getCustomerHierarchySid());
        inputBean.setSelectedProductHierarcySid(dto.getProductHierarchySid());
        inputBean.setSelectedProductRelationShipBuilderSid(dto.getProdRelationshipBuilderSid());
        inputBean.setSelectedCustomerHierarchyVersionNo(dto.getCustomerHierarchyVersionNo());
        inputBean.setSelectedProductHierarchyVersionNo(dto.getProductHierarchyVersionNo());
        inputBean.setProjectionId(dto.getProjectionId());
        return inputBean;
    }

    private void insertToCCp(GtnARMHierarchyInputBean inputBean) {
        GtnWsArmRequest forecastRequest = new GtnWsArmRequest();
        forecastRequest.setInputBean(inputBean);
        GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
        GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
        request.setGtnWsArmRequest(forecastRequest);
        client.callGtnWebServiceUrl(
                GtnWebServiceUrlConstants.GTN_DATASELCTION_ARM_EDIT_SERVICE + GtnWebServiceUrlConstants.GTN_CCP_INSERT, request,
                getGsnWsSecurityToken());
    }

    public static List<GtnFrameworkRelationshipLevelDefintionBean> convetToRelationBean(
            List<LevelDTO> selectedCustomerContractList) {
        List<GtnFrameworkRelationshipLevelDefintionBean> finalForecastList = new ArrayList<>();
        for (LevelDTO levelDto : selectedCustomerContractList) {
            GtnFrameworkRelationshipLevelDefintionBean relationBean = convertLevelDtoToRelationBean(levelDto);
            finalForecastList.add(relationBean);
        }
        return finalForecastList;
    }

    public static GtnFrameworkRelationshipLevelDefintionBean convertLevelDtoToRelationBean(LevelDTO levelDto) {
        GtnFrameworkRelationshipLevelDefintionBean forecast = new GtnFrameworkRelationshipLevelDefintionBean();
        forecast.setLevelName(levelDto.getLevel());
        forecast.setLevelNo(levelDto.getLevelNo());
        forecast.setRelationshipLevelSid(levelDto.getRelationshipLevelSid());
        forecast.setRelationShipLevelValue(levelDto.getRelationshipLevelValue() == null ? 0
                : Integer.parseInt(levelDto.getRelationshipLevelValue()));
        forecast.setTableName(levelDto.getTableName());
        forecast.setFieldName(levelDto.getFieldName());
        forecast.setHierarchyNo(levelDto.getHierarchyNo());
        forecast.setHierarchyDefinitionSid(levelDto.getHierarchyId());
        forecast.setHierarchyLevelDefinitionSid(levelDto.getHierarchyLevelDefnId() == null ? 0
                : Integer.parseInt(levelDto.getHierarchyLevelDefnId()));
        forecast.setLevelValueReference(levelDto.getLevelValueReference());
        forecast.setRelationshipBuilderSid(levelDto.getRelationShipBuilderId() == null ? 0
                : Integer.parseInt(levelDto.getRelationShipBuilderId()));
        forecast.setRelationshipVersionNo(levelDto.getRelationShipVersionNo());
        forecast.setHierarchyVersionNo(levelDto.getHierarchyVersionNo());
        forecast.setHierarchyCategory(levelDto.getHierarchyType());
        return forecast;
    }
}
