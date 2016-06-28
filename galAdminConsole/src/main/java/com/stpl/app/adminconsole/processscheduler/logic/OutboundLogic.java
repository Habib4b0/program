/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.processscheduler.logic;

import com.stpl.app.adminconsole.common.util.AbstractFilterLogic;
import com.stpl.app.adminconsole.common.util.CommonUIUtil;
import com.stpl.app.adminconsole.common.util.CommonUtil;
import com.stpl.app.adminconsole.processscheduler.dto.HierarchyDefinitionDTO;
import com.stpl.app.adminconsole.processscheduler.dto.OutboundTableDTO;
import com.stpl.app.adminconsole.processscheduler.dto.RelationshipOutboundDTO;
import static com.stpl.app.adminconsole.relationshipbuilder.logic.RelationBuilderLogic.dao;
import static com.stpl.app.adminconsole.relationshipbuilder.logic.RelationBuilderLogic.getDBColumnName;
import static com.stpl.app.adminconsole.relationshipbuilder.logic.RelationBuilderLogic.loadColumnName;
import com.stpl.app.adminconsole.relationshipbuilder.util.CommonUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.HelperListUtil;
import com.stpl.app.model.HierarchyDefinition;
import com.stpl.app.model.RelationshipBuilder;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.service.RsModelLocalServiceUtil;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.ConstantUtil;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionList;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.util.dao.orm.CustomSQLUtil;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.jboss.logging.Logger;

/**
 *
 * @author vinodhini
 */
public class OutboundLogic {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(OutboundLogic.class);
    HelperListUtil helperListUtil = HelperListUtil.getInstance();
    public final SimpleDateFormat DBDate = new SimpleDateFormat("yyyy-MM-dd");
    static HashMap<String, String> hierarchySearchCriteria = new HashMap<>();
    static HashMap<String, String> hierarchyFilterMap = new HashMap<>();
    static HashMap<String, String> hierarchyTypeMap = new HashMap<>();
    SimpleDateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");
    static HashMap<String, String> hierarchyCheckAllMap = new HashMap<>();
    static HashMap<String, String> rbMap = new HashMap<>();

    public int getHierarchyDefinitionCount(final ErrorfulFieldGroup searchFields, final Set<Container.Filter> filterSet, String hierType) throws Exception {
        int count = 0;
        StringBuilder queryBuilder = buildHierarchyDefinitionSearchQuery(searchFields, true, hierType);
        getHdFilterQuery(filterSet, queryBuilder);


        List<Object> masterData = (List<Object>) RsModelLocalServiceUtil.executeSelectQuery(queryBuilder.toString(), StringUtils.EMPTY, StringUtils.EMPTY);
        if (masterData != null && !masterData.isEmpty()) {
            Object ob = masterData.get(0);
            count += Integer.valueOf(String.valueOf(ob));
        }
        LOGGER.info(" getHierarchyDefinitionCount ===>" + count);
        return count;
    }

    public List<HierarchyDefinitionDTO> loadHierarchyDefinitionResults(
            final ErrorfulFieldGroup searchFields, final int start, final int end, final List<SortByColumn> columns, final Set<Container.Filter> filterSet, String hierType, boolean isCheckAll) throws SystemException, Exception {
        LOGGER.info("Entering loadHierarchyDefinitionResults with start of=" + start + "and endIndex of= " + end + "  Column Size +" + ((columns == null) ? columns : columns.size()));
        StringBuilder queryBuilder = buildHierarchyDefinitionSearchQuery(searchFields, false, hierType);
        getHdFilterQuery(filterSet, queryBuilder);
        getHdOrderQuery(queryBuilder, columns, start, end);

        final List list = (List) RsModelLocalServiceUtil.executeSelectQuery(queryBuilder.toString(), StringUtils.EMPTY, StringUtils.EMPTY);
        List<HierarchyDefinitionDTO> searchList = getCustomizedHdDTO(list, isCheckAll);
        LOGGER.info("End of loadHierarchyDefinitionResults");
        return searchList;
    }

    private void loadHdSearchCriteriaMap() {
        if (hierarchySearchCriteria.isEmpty()) {
        hierarchySearchCriteria.clear();
        hierarchySearchCriteria.put(ConstantsUtils.HIERARCHY_NAME, "HD.HIERARCHY_NAME");
        hierarchySearchCriteria.put("hierarchyCategory", "HD.HIERARCHY_CATEGORY");        
        hierarchySearchCriteria.put(ConstantsUtils.CREATED_DATE_FROM, "HD.CREATED_DATE");
        hierarchySearchCriteria.put(ConstantsUtils.CREATED_DATE_TO, "HD.CREATED_DATE");
        }
    }

    private StringBuilder buildHierarchyDefinitionSearchQuery(ErrorfulFieldGroup searchFields, boolean isCount, String hierType) {
        if (hierarchyTypeMap.isEmpty()) {
            loadHierarchyTypeMap();
        }
        StringBuilder queryBuilder = new StringBuilder(isCount ? CustomSQLUtil.get("getHierarchyDefinitionCount") : CustomSQLUtil.get("getHierarchyDefinitionResults"));
        queryBuilder.append(hierarchyTypeMap.get(hierType));
        loadHdSearchCriteriaMap();
          
        Set<String> keys = hierarchySearchCriteria.keySet();
        for (String fields : keys) {
            if (searchFields.getField(fields) != null) {
                if (searchFields.getField(fields).getValue() != null && !ConstantUtil.SELECT_ONE.equals(searchFields.getField(fields).getValue().toString()) && !searchFields.getField(fields).getValue().toString().trim().isEmpty()) {
                    if ("hierarchyCategory".equalsIgnoreCase(fields)) {

                        queryBuilder.append(" AND ").append(hierarchySearchCriteria.get(fields)).append(" = '").append(((HelperDTO) searchFields.getField(fields).getValue()).getId()).append("'");

                    } else if (ConstantsUtils.CREATED_DATE_FROM.equalsIgnoreCase(fields)) {
                        queryBuilder.append(" AND ").append(hierarchySearchCriteria.get(fields)).append(" > '").append(DBDate.format((Date) searchFields.getField(fields).getValue())).append("'");
                    } else if (ConstantsUtils.CREATED_DATE_TO.equalsIgnoreCase(fields)) {
                        queryBuilder.append(" AND ").append(hierarchySearchCriteria.get(fields)).append(" < '").append(DBDate.format((Date) searchFields.getField(fields).getValue())).append("'");
                    } else {
                        queryBuilder.append(" AND ").append(hierarchySearchCriteria.get(fields)).append(" LIKE '").append(String.valueOf(searchFields.getField(fields).getValue()).trim().replace("*", "%")).append("'");
                    }
                }
            }
        }

        return queryBuilder;
    }

    private List<HierarchyDefinitionDTO> getCustomizedHdDTO(List list, boolean isCheckAll) {
        final List<HierarchyDefinitionDTO> searchResultsList = new ArrayList<>();
        try {
            if (list != null) {
                Map<Integer, String> userMap = StplSecurity.userMap;
                if (userMap.isEmpty()) {
                    userMap = StplSecurity.getUserName();
                }

                for (int i = 0; i < list.size(); i++) {
                    final HierarchyDefinitionDTO hierarchyBuilderDTO = new HierarchyDefinitionDTO();
                    final Object[] object = (Object[]) list.get(i);

                    hierarchyBuilderDTO.setHierarchyDefinitionSystemId(!"null".equals(String.valueOf(object[0])) && StringUtils.isNotBlank(String.valueOf(object[0])) ? String.valueOf(object[0]) : "0");
                    hierarchyBuilderDTO.setHierarchyName(!"null".equals(String.valueOf(object[1])) && StringUtils.isNotBlank(String.valueOf(object[1])) ? String.valueOf(object[1]) : StringUtils.EMPTY);
                    hierarchyBuilderDTO.setHierarchyTypeDto(helperListUtil.getIdHelperDTOMap().get(object[2] != null ? Integer.valueOf(String.valueOf(object[2])) : 0));
                    hierarchyBuilderDTO.setHierarchyCategory((helperListUtil.getIdHelperDTOMap().get(object[3] != null ? Integer.valueOf(String.valueOf(object[3])) : 0)));
                    hierarchyBuilderDTO.setNoOfLevels(!"null".equals(String.valueOf(object[4])) && StringUtils.isNotBlank(String.valueOf(object[4])) ? String.valueOf(object[4]) : StringUtils.EMPTY);
                    hierarchyBuilderDTO.setCreatedBy(object[5] != null ? userMap.get(Integer.valueOf(String.valueOf(object[5]))) : StringUtils.EMPTY);
                    if (object[6] != null) {
                        Date createdDate = dateformat.parse(com.stpl.app.adminconsole.util.CommonUtils.convertDateToString((Date) (object[6])));
                        hierarchyBuilderDTO.setCreatedDate(createdDate);
                    }
                    if (object[8] != null) {
                        Date modifiedDate = dateformat.parse(com.stpl.app.adminconsole.util.CommonUtils.convertDateToString((Date) (object[8])));
                        hierarchyBuilderDTO.setModifiedDate(modifiedDate);
                    }
                    hierarchyBuilderDTO.setVersionNo(String.valueOf(object[9]));
                    hierarchyBuilderDTO.setCheck(isCheckAll);
                    searchResultsList.add(hierarchyBuilderDTO);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return searchResultsList;
    }

    void loadHdFilterMap() {
        if (hierarchyFilterMap.isEmpty()) {
            hierarchyFilterMap.put(ConstantsUtils.HIERARCHY_NAME, "HD.HIERARCHY_NAME");
            hierarchyFilterMap.put("hierarchyTypeDto", "HD.HIERARCHY_TYPE");
            hierarchyFilterMap.put("hierarchyCategory", "HD.HIERARCHY_CATEGORY");
            hierarchyFilterMap.put("noOfLevels", "HD.NO_OF_LEVELS");
            hierarchyFilterMap.put("versionNo", "HD.VERSION_NO");
            hierarchyFilterMap.put("createdBy", "HD.CREATED_BY");
            hierarchyFilterMap.put("createdDate", "HD.CREATED_DATE");
            hierarchyFilterMap.put("modifiedDate", "HD.MODIFIED_DATE");
        }
    }

    private StringBuilder getHdFilterQuery(final Set<Container.Filter> filterSet, final StringBuilder stringBuilder) throws ParseException {
        loadHdFilterMap();
        if (filterSet != null) {
            stringBuilder.append(AbstractFilterLogic.getInstance().filterQueryGenerator(filterSet, hierarchyFilterMap).toString().replace("where", " AND"));
        }
        return stringBuilder;
    }

    private StringBuilder getHdOrderQuery(StringBuilder stringBuilder, final List<SortByColumn> sortByColumns, final int startIndex, final int endIndex) {
        boolean sortOrder = false;
        String orderByColumn = null;
        loadHdFilterMap();
        if (sortByColumns != null) {
            for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                final SortByColumn sortByColumn = (SortByColumn) iterator.next();
                orderByColumn = hierarchyFilterMap.get(sortByColumn.getName());
                sortOrder = sortByColumn.getType() != SortByColumn.Type.ASC;
            }
        }
        if (orderByColumn == null || StringUtils.EMPTY.equals(orderByColumn)) {
            stringBuilder.append(" ORDER BY HD.HIERARCHY_DEFINITION_SID ");
        } else {
            stringBuilder.append(" ORDER BY ").append(orderByColumn).append((!sortOrder) ? " ASC " : " DESC ");
        }

        stringBuilder.append(" OFFSET ").append(startIndex);
        stringBuilder.append(" ROWS FETCH NEXT ").append(endIndex).append(" ROWS ONLY;");

        return stringBuilder;
    }

    public void loadHierarchyTypeMap() {
        List<HelperDTO> hierarchyTypeList = helperListUtil.getListNameMap().get(CommonUIUtil.RELATIONSHIP_TYPE);
        for (HelperDTO hierarchyType : hierarchyTypeList) {
            hierarchyTypeMap.put(hierarchyType.getDescription(), String.valueOf(hierarchyType.getId()));
        }
    }

    /**
     * Check search criteria.
     *
     * @param binder the binder
     * @return true, if successful
     */
    public boolean checkSearchCriteria(final ErrorfulFieldGroup binder) {
        boolean isvalid = false;
        for (Object object : binder.getFields()) {
            if (object != null && object instanceof TextField && ((TextField) object).isVisible()) {
                if (StringUtils.isNotBlank(((TextField) object).getValue())) {
                    isvalid = true;
                    break;
                }
            } else if (object != null && object instanceof ComboBox && ((ComboBox) object).isVisible()) {

                if (!ConstantsUtils.SELECT_ONE.equals(((ComboBox) object).getValue().toString())) {
                    isvalid = true;
                    break;
                }
            }
        }
        return isvalid;
    }

    /**
     * Gets the Total Number of records present based on the search criteria and
     * filter.
     *
     * @param binder
     * @param start
     * @param offset
     * @param isCount
     * @param columns
     * @param filterSet
     * @return
     * @throws Exception
     */
    public int getRelationshipSearchCount(ErrorfulFieldGroup binder, int start, int offset, final boolean isCount, final List<SortByColumn> columns,
            final Set<Container.Filter> filterSet) throws Exception {
        int count;
        count = (Integer) loadRelationshipBuilderLogic(binder, false, start, offset, isCount, columns, filterSet);

     
        return count;
    }

    /**
     * Gets the records based on the search criteria ,filter,start and offset
     * values.
     *
     * @param binder - Contains the Search criteria for the relationship builder 
     * @param start
     * @param offset
     * @param isCount
     * @param columns
     * @param filterSet
     * @param isCheckAll - To set check the check box in table when check all check box is clicked
     * @return
     * @throws Exception
     */
    public List getRelationshipSearchResults(ErrorfulFieldGroup binder, int start, int offset, final boolean isCount, final List<SortByColumn> columns,
            final Set<Container.Filter> filterSet, final boolean isCheckAll) throws Exception {
        List list = (List) loadRelationshipBuilderLogic(binder, isCheckAll, start, offset, isCount, columns, filterSet);
        return list;
    }

    private Object loadRelationshipBuilderLogic(ErrorfulFieldGroup binder, final boolean isCheckAll, int start, int offset, final boolean isCount, final List<SortByColumn> columns, final Set<Container.Filter> filterSet) throws Exception {

        List list;
        if (isCount) {
            int count = (Integer) getSearchResults(binder, isCheckAll, false, start, start + offset, columns, filterSet, isCount);
            return count;
        } else {
            list = (List<RelationshipOutboundDTO>) getSearchResults(binder, isCheckAll, false, start, start + offset, columns, filterSet, isCount);
            return list;
        }
    }

    /**
     * Gets the search results.
     *
     * @param relationBuilderForm Contains the Search criteria for the relationship builder form
     * @param isCheckAll - To set check the check box in table when check all check box is clicked
     * @param isCheckQuery - To get check All query when check all check box is clicked
     * @param startIndex
     * @param endIndex
     * @param sortByColumns
     * @param filterSet
     * @param isCount
     * @return
     * @throws SystemException
     * @throws Exception 
     */
    public Object getSearchResults(final ErrorfulFieldGroup relationBuilderForm, final boolean isCheckAll, final boolean isCheckQuery, final int startIndex,
            final int endIndex, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet, boolean isCount) throws SystemException, Exception {
        LOGGER.info("getSearchResults started with P1:CustomFieldGroup relationBuilderForm");
        List<RelationshipBuilder> resultList = new ArrayList<>();
        List<RelationshipOutboundDTO> itemGroupList = new ArrayList<>();
        Date startDateFrom;
        Date startDateTo;
        Date creationDateFrom;
        Date creationDateTo;
        try {
            final DynamicQuery relationBuilderDynamicQuery = DynamicQueryFactoryUtil.forClass(RelationshipBuilder.class);

            if (relationBuilderForm.getField("relationshipType").getValue() != null
                    && StringUtils.isNotBlank(relationBuilderForm.getField("relationshipType").getValue().toString())) {
                final String relationshiptype = relationBuilderForm.getField("relationshipType").getValue().toString().trim();
                int relationShipType = CommonUtil.getIDFromHelper(relationshiptype, "RELATIONSHIP_TYPE");

                relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.RELATIONSHIP_TYPE, relationShipType));

            }

            if (relationBuilderForm.getField("relationshipName").getValue() != null
                    && StringUtils.isNotBlank(relationBuilderForm.getField("relationshipName").getValue().toString())) {
                final String relationshipName = relationBuilderForm.getField("relationshipName").getValue().toString().trim();
                final String relationshipName1 = relationshipName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);

                relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.RELATIONSHIP_NAME, relationshipName1));

            }
            if (relationBuilderForm.getField("relationshipDescription").getValue() != null
                    && StringUtils.isNotBlank(relationBuilderForm.getField("relationshipDescription").getValue().toString())) {
                final String relationshipDescription = relationBuilderForm.getField("relationshipDescription").getValue().toString().trim();
                final String relationshipDesc1 = relationshipDescription.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
                relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.ilike("relationshipDescription", relationshipDesc1));

            }
            if (relationBuilderForm.getField("hierarchyNameDDLB").getValue() != null && StringUtils.isNotBlank(relationBuilderForm.getField("hierarchyNameDDLB").getValue().toString())
                    && ((HelperDTO) relationBuilderForm.getField("hierarchyNameDDLB").getValue()).getId() != 0) {
                relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.HIERARCHY_DEFINATION_SID, ((HelperDTO) relationBuilderForm.getField("hierarchyNameDDLB").getValue()).getId()));

            }
         
            if (relationBuilderForm.getField("startDateFrom").getValue() != null ) {
                startDateFrom = (Date) relationBuilderForm.getField("startDateFrom").getValue();
                relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.START_DATE, startDateFrom));
            }
            if (relationBuilderForm.getField("startDateTo").getValue() != null) {
                startDateTo = (Date) relationBuilderForm.getField("startDateTo").getValue();
                relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.le(ConstantsUtils.START_DATE, startDateTo));
            }
            
            if (relationBuilderForm.getField("creationDateFrom").getValue() != null) {
                creationDateFrom = (Date) relationBuilderForm.getField("creationDateFrom").getValue();
                relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.ge("createdDate", creationDateFrom));
            }
            if (relationBuilderForm.getField("creationDateTo").getValue() != null) {
                creationDateTo = (Date) relationBuilderForm.getField("creationDateTo").getValue();
                relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.le("createdDate", creationDateTo));
            }
            if (filterSet != null) {
                for (Container.Filter filter : filterSet) {
                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        String filterString = "%" + stringFilter.getFilterString() + "%";
                        if (ConstantsUtils.RELATIONSHIP_TYPE.equals(stringFilter.getPropertyId())) {
                            filterString = filterString.replace("%", StringUtils.EMPTY);
                            if (StringUtils.isNotBlank(filterString)) {
                                relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.RELATIONSHIP_TYPE, Integer.valueOf(filterString)));
                            }
                        }
                        if (ConstantsUtils.RELATIONSHIP_DESC.equals(stringFilter.getPropertyId())) { 
                            relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.ilike("relationshipDescription", filterString));
                        }
                        if (ConstantsUtils.RELATIONSHIP_NAME.equals(stringFilter.getPropertyId())) {
                            relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.RELATIONSHIP_NAME, filterString));
                        }
                        if (ConstantsUtils.CREATED_BY.equals(stringFilter.getPropertyId())) {
                            filterString = filterString.replace("%", StringUtils.EMPTY);
                            if (StringUtils.isNotBlank(filterString)) {
                                relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.CREATED_BY, Integer.valueOf(filterString)));
                            }
                        }
                        if ("hierarchyName".equals(stringFilter.getPropertyId())) {
                            final DynamicQuery hierarchyDynamicQuery = DynamicQueryFactoryUtil.forClass(HierarchyDefinition.class);
                            final ProjectionList projList = ProjectionFactoryUtil.projectionList();
                            projList.add(ProjectionFactoryUtil.property(ConstantsUtils.HIERARCHY_DEFINATION_SID));
                            hierarchyDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.HIERARCHY_NAME, filterString));
                            hierarchyDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));
                            List hdResultList = dao.getHierachyDefinitionList(hierarchyDynamicQuery);
                            if (hdResultList.size() > 0) {
                                relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.in(ConstantsUtils.HIERARCHY_DEFINATION_SID, hdResultList));
                            } else {
                                relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.HIERARCHY_DEFINATION_SID, 0));
                            }
                        }
                    } else if (filter instanceof Compare) {
                        Compare compare = (Compare) filter;
                        Compare.Operation operation = compare.getOperation();
                         
                        if (ConstantsUtils.VERSION_NO.equalsIgnoreCase(String.valueOf(compare.getPropertyId()))) {
                            int value = Integer.valueOf(String.valueOf(compare.getValue()));
                            if (operation.GREATER.toString().equalsIgnoreCase(operation.name())) {
                                relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.gt(ConstantsUtils.VERSION_NO, value));
                            }
                            if (operation.LESS.toString().equalsIgnoreCase(operation.name())) {
                                relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantsUtils.VERSION_NO, value));
                            }
                            if (operation.EQUAL.toString().equalsIgnoreCase(operation.name())) {
                                relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.VERSION_NO, value));
                            }
                        }
                         if (compare.getValue() instanceof Date) {
                             Date value = (Date) compare.getValue();
                             if (operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {

                                 if (ConstantsUtils.CREATED_DATE.equals(compare.getPropertyId())) {
                                     relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.gt(ConstantsUtils.CREATED_DATE, value));
                                 }
                                 if (ConstantsUtils.MODIFIED_DATE.equals(compare.getPropertyId())) {
                                     relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.gt(ConstantsUtils.MODIFIED_DATE, value));
                                 }
                                 if (ConstantsUtils.START_DATE.equals(compare.getPropertyId())) {
                                     relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.gt(ConstantsUtils.START_DATE, value));
                                 }
                            } else {
                                if (ConstantsUtils.CREATED_DATE.equals(compare.getPropertyId())) {
                                    relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantsUtils.CREATED_DATE, value));
                                 }
                                 if (ConstantsUtils.MODIFIED_DATE.equals(compare.getPropertyId())) {
                                     relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantsUtils.MODIFIED_DATE, value));
                                 }
                                 if (ConstantsUtils.START_DATE.equals(compare.getPropertyId())) {
                                     relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantsUtils.START_DATE, value));
                                 }
                            }
                        }
                    } else if (filter instanceof Between) {

                        Between stringFilter = (Between) filter;
                        Date filterString = (Date) stringFilter.getStartValue();
                        Date filterString1 = (Date) stringFilter.getEndValue();

                        if (ConstantsUtils.CREATED_DATE.equals(stringFilter.getPropertyId())) {
                            relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.CREATED_DATE, filterString));
                            relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantsUtils.CREATED_DATE, filterString1));
                        }
                        if (ConstantsUtils.MODIFIED_DATE.equals(stringFilter.getPropertyId())) {
                            relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.MODIFIED_DATE, filterString));
                            relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantsUtils.MODIFIED_DATE, filterString1));
                        }
                        if (ConstantsUtils.START_DATE.equals(stringFilter.getPropertyId())) {
                            relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.START_DATE, filterString));
                            relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantsUtils.START_DATE, filterString1));
                        }
                    }
                }
            }

            loadColumnName();
            if (sortByColumns != null) {
                for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                    final SortByColumn orderByColumn = (SortByColumn) iterator.next();
                    if(!"check".equals(orderByColumn.getName())){
                    if (orderByColumn.getType() == SortByColumn.Type.ASC) {
                        relationBuilderDynamicQuery.addOrder(OrderFactoryUtil.asc(getDBColumnName(orderByColumn.getName())));
                    } else {
                        relationBuilderDynamicQuery.addOrder(OrderFactoryUtil.desc(getDBColumnName(orderByColumn.getName())));
                    }
                    }
                }
            }

            if (!isCount) {
                relationBuilderDynamicQuery.setLimit(startIndex, endIndex);
            }
            resultList = dao.getRelationshipBuilderList(relationBuilderDynamicQuery);

        } catch (Exception e) {
            LOGGER.error(e);
        }
        if (isCheckQuery) {
            return resultList;
        } else if (isCount) {
            return resultList.size();
        } else {
            if (resultList != null && !resultList.isEmpty()) {
                itemGroupList = getCustomizedResults(resultList, isCheckAll);
            }
            LOGGER.info("getSearchResults return List<RelationshipBuilderDTO> itemGroupList=" + itemGroupList.size());
            return itemGroupList;
        }

    }

    /**
     * Gets the customized results.
     *
     * @param resultList the result list
     * @return the customized results
     */
    private List<RelationshipOutboundDTO> getCustomizedResults(final List<RelationshipBuilder> resultList, boolean isCheckAll) throws SystemException, Exception {
        LOGGER.info("getCustomizedResults started with P1:List<RelationshipBuilder> resultList");
        final List<RelationshipOutboundDTO> relationBuilderList = new ArrayList<>();
        final Map hierarchyInfo = CommonUtils.getHierarchyInfo();
        final HashMap<String, String> userInfoMap = (HashMap<String, String>) CommonUtil.getCreatedByUser();

        for (int i = 0; i < resultList.size(); i++) {
            String hierarchyName;
            final RelationshipBuilder relationBuilder = resultList.get(i);
            final RelationshipOutboundDTO relationBuilderDTO = new RelationshipOutboundDTO();
            relationBuilderDTO.setRelationshipName(relationBuilder.getRelationshipName());
            relationBuilderDTO.setRelationshipDesc(relationBuilder.getRelationshipDescription());
            relationBuilderDTO.setRelationshipType((helperListUtil.getIdHelperDTOMap().get(relationBuilder.getRelationshipType())).getDescription());
            relationBuilderDTO.setHierarchyVersionNo(relationBuilder.getHierarchyVersion());
            hierarchyName = String.valueOf(hierarchyInfo.get(String.valueOf(relationBuilder.getHierarchyDefinitionSid())));
            relationBuilderDTO.setVersionNo(relationBuilder.getVersionNo());
            if (hierarchyName != null && !hierarchyName.equals(ConstantsUtils.NULL)) {
                relationBuilderDTO.setHierarchyName(hierarchyName);
            }
            relationBuilderDTO.setStartDate(parsetDate(String.valueOf(relationBuilder.getStartDate())));
            relationBuilderDTO.setCreatedBy(userInfoMap.get(String.valueOf(relationBuilder.getCreatedBy())));
            relationBuilderDTO.setCreatedDate(parsetDate(String.valueOf(relationBuilder.getCreatedDate())));
            if (relationBuilder.getModifiedDate() != null && !relationBuilder.getModifiedDate().equals(ConstantsUtils.NULL)) {
                relationBuilderDTO.setModifiedDate(parsetDate(String.valueOf(relationBuilder.getModifiedDate())));
            }
            relationBuilderDTO.setRbSystemId(String.valueOf(relationBuilder.getRelationshipBuilderSid()));
            relationBuilderDTO.setSystemID(relationBuilder.getRelationshipBuilderSid());
            relationBuilderDTO.setCheck(isCheckAll);
            relationBuilderList.add(relationBuilderDTO);
        }
        LOGGER.info("getCustomizedResults return List<RelationshipBuilderDTO> relationBuilderList=" + relationBuilderList.size());
        return relationBuilderList;
    }

    public List hierarchyQueryResults(String ids, String queryName,boolean isScheduled) {
        Map<String, String> input = null;
        if (!isScheduled) {
            input = new HashMap<>();
            input.put("?HIERARCHY_DEFINITION_SID", ids);
        }
        List list = (List) RsModelLocalServiceUtil.executeSelectQuery(CommonUtil.replacedQuery(input, queryName), StringUtils.EMPTY, StringUtils.EMPTY);
        return list;
    }
   
    /**
     * To get the Hierarchy Definition id when check all is selected
     *
     * @param searchFields
     * @param hierType
     * @return
     */
    public List<OutboundTableDTO> getHierarchyCheckedAllResults(ErrorfulFieldGroup searchFields, String hierType) {
       if (hierarchyTypeMap.isEmpty()) {
            loadHierarchyTypeMap();
        }
      
        StringBuilder queryBuilder = new StringBuilder(StringUtils.EMPTY);
        queryBuilder.append(hierarchyTypeMap.get(hierType));
       
        if (hierarchySearchCriteria.isEmpty()) {
            loadHdSearchCriteriaMap();
        }
        Set<String> keys = hierarchyCheckAllMap.keySet();
        for (String fields : keys) {
            if (searchFields.getField(fields) != null) {
                if (searchFields.getField(fields).getValue() != null && !ConstantUtil.SELECT_ONE.equals(searchFields.getField(fields).getValue().toString()) && !searchFields.getField(fields).getValue().toString().trim().isEmpty()) {
                    if ("hierarchyCategory".equalsIgnoreCase(fields)) {
                        queryBuilder.append(" AND ").append(hierarchySearchCriteria.get(fields)).append(" = '").append(((HelperDTO) searchFields.getField(fields).getValue()).getId()).append("'");
                    } else if (ConstantsUtils.CREATED_DATE_FROM.equalsIgnoreCase(fields)) {
                        queryBuilder.append(" AND ").append(hierarchySearchCriteria.get(fields)).append(" > '").append(DBDate.format((Date) searchFields.getField(fields).getValue())).append("'");
                    } else if (ConstantsUtils.CREATED_DATE_TO.equalsIgnoreCase(fields)) {
                        queryBuilder.append(" AND ").append(hierarchySearchCriteria.get(fields)).append(" < '").append(DBDate.format((Date) searchFields.getField(fields).getValue())).append("'");
                    } else {
                        queryBuilder.append(" AND ").append(hierarchySearchCriteria.get(fields)).append(" LIKE '").append(String.valueOf(searchFields.getField(fields).getValue()).trim().replace("*", "%")).append("'");
                    }
                }
            }
        }

        Map<String, String> input = new HashMap<>();
        input.put("?CONDITIONS", queryBuilder.toString());
        List list = (List) RsModelLocalServiceUtil.executeSelectQuery(CommonUtil.replacedQuery(input, "getHierarchyDefinitionOuboundCheckAllResults"), StringUtils.EMPTY, StringUtils.EMPTY);
       
         List<OutboundTableDTO> resultList = getCustomizedOutboundTableDTO(list);
        return resultList;
        
    }
    
   /**
    * To get the result of hierarchy Definition Outbound Process for loading the excel outbound table
    * @param checkedIds - ids checked in table 
    * @param queryName - name of the query to get the data from db
     * @param isScheduled
    * @return 
    */
    public List<OutboundTableDTO> hierarchyDefinitionOutbound(String checkedIds, String queryName,boolean isScheduled) {
        List queryList = hierarchyQueryResults(checkedIds, queryName,isScheduled);
        List<OutboundTableDTO> resultList = getCustomizedOutboundTableDTO(queryList);
        return resultList;
    }

    /**
     * Gets the customized results.
     *
     * @param resultList the result list
     * @return the customized results
     */
    private List<OutboundTableDTO> getCustomizedOutboundTableDTO(final List resultList) {
        LOGGER.info("getCustomizedOutboundTableDTO started :List resultList");
        final List<OutboundTableDTO> OutboundTableResults = new ArrayList<>();
        try {
            if (resultList != null) {
                int totalLevel;
                int resultsSize = resultList.size();
                for (int i = 0; i < resultsSize;) {
                    final OutboundTableDTO outboundTableDTO = new OutboundTableDTO();
                    Object[] object = (Object[]) resultList.get(i);

                    outboundTableDTO.setHierarchyDefinitionSystemId(!"null".equals(String.valueOf(object[0])) && StringUtils.isNotBlank(String.valueOf(object[0])) ? String.valueOf(object[0]) : "0");
                    outboundTableDTO.setHierarchyName(getValidatedStringValue(object[1]));
                    outboundTableDTO.setHierarchyType(getValidatedStringValue(object[2]));
                    outboundTableDTO.setHierarchyCategory(getValidatedStringValue(object[3]));
                    outboundTableDTO.setNooflevels(!"null".equals(String.valueOf(object[4])) && StringUtils.isNotBlank(String.valueOf(object[4])) ? String.valueOf(object[4]) : "0");
                    outboundTableDTO.setVersion(getValidatedStringValue(object[5]));
                    totalLevel = Integer.parseInt(outboundTableDTO.getNooflevels());

                    for (int j = 1; j <= totalLevel && i < resultsSize; j++) {
                        object = (Object[]) resultList.get(i);
                        String commonColumn = "level" + j;
                        Object levelColumn = commonColumn + "LevelName";
                        String value = getValidatedStringValue(object[6]);
                        outboundTableDTO.addStringProperties(levelColumn, value);

                        value = getValidatedStringValue(object[8]);
                        levelColumn = commonColumn + "LevelValueReference";
                        outboundTableDTO.addStringProperties(levelColumn, value);

                        value = getValidatedStringValue(object[9]);
                        levelColumn = commonColumn + "TableName";
                        outboundTableDTO.addStringProperties(levelColumn, value);

                        value = getValidatedStringValue(object[10]);
                        levelColumn = commonColumn + "FieldName";
                        outboundTableDTO.addStringProperties(levelColumn, value);

                        value = getValidatedStringValue(object[11]);
                        levelColumn = commonColumn + "InclusionRuleType";
                        outboundTableDTO.addStringProperties(levelColumn, value);

                        value = getValidatedStringValue(object[12]);
                        levelColumn = commonColumn + "InclusionRule";
                        outboundTableDTO.addStringProperties(levelColumn, value);

                        value = getValidatedStringValue(object[13]);
                        levelColumn = commonColumn + "InclusionCondition";
                        outboundTableDTO.addStringProperties(levelColumn, value);

                        value = getValidatedStringValue(object[14]);
                        levelColumn = commonColumn + "ExclusionRuleType";
                        outboundTableDTO.addStringProperties(levelColumn, value);

                        value = getValidatedStringValue(object[15]);
                        levelColumn = commonColumn + "ExclusionRule";
                        outboundTableDTO.addStringProperties(levelColumn, value);

                        value = getValidatedStringValue(object[16]);
                        levelColumn = commonColumn + "ExclusionCondition";
                        outboundTableDTO.addStringProperties(levelColumn, value);
                        i++;
                    }
                    OutboundTableResults.add(outboundTableDTO);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("getCustomizedOutboundTableDTO return List<OutboundTableDTO> OutboundTableResults=" + OutboundTableResults.size());
        return OutboundTableResults;
    }
    /**
     * To validate a null object value and return a string value  
     * @param object
     * @return String value after validation
     */
    public String getValidatedStringValue(Object object) {
       return !"null".equals(String.valueOf(object)) && StringUtils.isNotBlank(String.valueOf(object)) ? String.valueOf(object) : StringUtils.EMPTY;
    }
    /**
     * To get RelationShip Builder Outbound Results in excel
     *
     * @param ids - ids checked in table
     * @return
     */
    public List<OutboundTableDTO> getRelationShipOutboundResults(String ids) {
        List<OutboundTableDTO> rbOutboundList = new ArrayList<>();
        try {
            Map<String, String> input = new HashMap<>();
            input.put("?RELATIONSHIP_BUILDER_SID", ids);
            List list = (List) RsModelLocalServiceUtil.executeSelectQuery(CommonUtil.replacedQuery(input, "getRelationshipBuilderOutbound"), StringUtils.EMPTY, StringUtils.EMPTY);
            rbOutboundList=getCustomizedRbOutbound(list);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return rbOutboundList;
    } 
   
    /**
     * To change Date format from DB date format
     * @param value 
     * @return - formatted date 
     * @throws java.text.ParseException
     */
      private static Date parsetDate(String value) throws java.text.ParseException {
        Date date = null;
        String tempDate;
        SimpleDateFormat parse = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        if (value != null && !StringUtils.EMPTY.equals(value) && !"null".equals(value)) {
            tempDate = format.format(parse.parse(value));
            date = (format.parse(tempDate));
        }

        return date;
    }
         
    public Set getAllCffIds() {
        String query = CustomSQLUtil.get("getCffOuboundSearchResults");
        Set checkedCffsSet = new HashSet();

        List list = (List) RsModelLocalServiceUtil.executeSelectQuery(query, StringUtils.EMPTY, StringUtils.EMPTY);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                checkedCffsSet.add(String.valueOf(list.get(i)));
            }
        }
        return checkedCffsSet;
    }

    public String getFormattedIds(String query) {
        StringBuilder allIds = new StringBuilder(StringUtils.EMPTY);
        List list = (List) RsModelLocalServiceUtil.executeSelectQuery(query, StringUtils.EMPTY, StringUtils.EMPTY);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                allIds.append(",").append(String.valueOf(list.get(i)));
            }
        }
        return allIds.toString().replaceFirst(",", "");
    }
    
  List<OutboundTableDTO>  getCustomizedRbOutbound(List list){
            List<OutboundTableDTO> rbOutboundList = new ArrayList<>();
      int listSize = list.size();
            for (int i = 0; i < listSize;) {
                Object[] obj = (Object[]) list.get(i);
                OutboundTableDTO rbOuboundDTO = new OutboundTableDTO();

                rbOuboundDTO.setRelationshipName(getValidatedStringValue(obj[3]));
                rbOuboundDTO.setRelationshipDescription(getValidatedStringValue(obj[4]));
                rbOuboundDTO.setRelationshipType(getValidatedStringValue(obj[5]));
                rbOuboundDTO.setHierarchyName(getValidatedStringValue(obj[6]));

                rbOuboundDTO.setHierarchyVersionNo(getValidatedStringValue(obj[8]));
                
                if (obj[9] != null) {
                    String startDate = dateformat.format((Date) (obj[9]));
                    rbOuboundDTO.setStartDate(startDate);
                }
                rbOuboundDTO.setBuildType(getValidatedStringValue(obj[10]));
                int sid = Integer.parseInt(String.valueOf(obj[1] != null ? obj[1] : "0"));
                int nextId = sid;
                for (; i < listSize && (sid == nextId); i++) {
                    obj = (Object[]) list.get(i);
                    if (listSize != (i + 1)) {
                        Object[] nextObj = (Object[]) list.get(i + 1);
                        nextId = Integer.parseInt(String.valueOf(nextObj[1] != null ? nextObj[1] : "0"));
                    }
                    rbOuboundDTO.setRelationshipTree(getValidatedStringValue(obj[2]));
                    rbOuboundDTO.setHierarchyLevelName(getValidatedStringValue(obj[7]));
                    rbOutboundList.add(rbOuboundDTO);
                    rbOuboundDTO = new OutboundTableDTO();
                }

            }
            return rbOutboundList;
    }
    
    /**
     * To get RelationShip Builder Outbound Results in excel for Scheduled Process
     *
     * @return
     */
    public List<OutboundTableDTO> getRelationShipSheduledResults() {
        List<OutboundTableDTO> rbOutboundList = new ArrayList<>();
        try {
            String rbQuery=CustomSQLUtil.get("getRelationshipOuboundScheduleResults");
            List list = (List) RsModelLocalServiceUtil.executeSelectQuery(rbQuery, StringUtils.EMPTY, StringUtils.EMPTY);
            rbOutboundList=getCustomizedRbOutbound(list);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return rbOutboundList;
    } 
    
    
    /**
     * To get RelationShip Builder Outbound Results in excel
     *
     * @param searchFields - ids checked in table
     * @return
     */
    public List<OutboundTableDTO> getRelationShipOutboundCheckAllResults(ErrorfulFieldGroup searchFields) {
        List<OutboundTableDTO> rbOutboundList = new ArrayList<>();
        try {
            StringBuilder queryBuilder = new StringBuilder(StringUtils.EMPTY);
            if (hierarchyTypeMap.isEmpty()) {
                loadHierarchyTypeMap();
            }
            if (rbMap.isEmpty()) {
                rbMap.clear();
                rbMap.put("relationshipName", "R.RELATIONSHIP_NAME");
                rbMap.put("relationshipType", "RELATIONSHIP_TYPE");
                rbMap.put("relationshipDescription", "R.RELATIONSHIP_DESCRIPTION");
                rbMap.put("hierarchyNameDDLB", "HD.HIERARCHY_NAME");
                rbMap.put("startDateFrom", "R.START_DATE");
                rbMap.put("startDateTo", "R.START_DATE");
                rbMap.put("creationDateFrom", "R.CREATED_DATE");
                rbMap.put("creationDateTo", "R.CREATED_DATE");
            }
            Set<String> keys = rbMap.keySet();
            for (String fields : keys) {
                if (searchFields.getField(fields) != null) {
                    if ("hierarchyNameDDLB".equalsIgnoreCase(fields)) {
                        if (searchFields.getField("hierarchyNameDDLB").getValue() != null && StringUtils.isNotBlank(searchFields.getField("hierarchyNameDDLB").getValue().toString())
                                && ((HelperDTO) searchFields.getField("hierarchyNameDDLB").getValue()).getId() != 0) {
                            queryBuilder.append(" AND ").append(rbMap.get(fields)).append(" = '").append(((HelperDTO) searchFields.getField(fields).getValue()).getDescription()).append("'");
                        }
                    } else if (searchFields.getField(fields).getValue() != null && !String.valueOf(searchFields.getField(fields).getValue()).trim().isEmpty()) {
                        if ("relationshipType".equalsIgnoreCase(fields)) {
                            queryBuilder.append(" AND ").append(rbMap.get(fields)).append(" = '").append(hierarchyTypeMap.get(String.valueOf(searchFields.getField(fields).getValue()))).append("'");
                        } else if ("startDateFrom".equalsIgnoreCase(fields) || "creationDateFrom".equalsIgnoreCase(fields)) {
                            queryBuilder.append(" AND ").append(rbMap.get(fields)).append(" > '").append(DBDate.format((Date) searchFields.getField(fields).getValue())).append("'");
                        } else if ("startDateTo".equalsIgnoreCase(fields) || "creationDateTo".equalsIgnoreCase(fields)) {
                            queryBuilder.append(" AND ").append(rbMap.get(fields)).append(" < '").append(DBDate.format((Date) searchFields.getField(fields).getValue())).append("'");
                        } else {
                            queryBuilder.append(" AND ").append(rbMap.get(fields)).append(" LIKE '").append(String.valueOf(searchFields.getField(fields).getValue()).trim().replace("*", "%")).append("'");
                        }
                    }
                }
            }

            Map<String, String> input = new HashMap<>();
            input.put("?ANDQUERY", queryBuilder.toString());
            input.put("?LWHEREQUERY", queryBuilder.toString().replaceFirst("AND", "WHERE"));

            List list = (List) RsModelLocalServiceUtil.executeSelectQuery(CommonUtil.replacedQuery(input, "getRelationshipOuboundCheckAllResults"), StringUtils.EMPTY, StringUtils.EMPTY);
            rbOutboundList = getCustomizedRbOutbound(list);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return rbOutboundList;
    }
  
}
