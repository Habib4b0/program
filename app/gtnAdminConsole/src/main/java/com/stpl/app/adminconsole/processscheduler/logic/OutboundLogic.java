/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.processscheduler.logic;

import com.stpl.app.adminconsole.util.StringConstantUtils;
import com.stpl.app.adminconsole.common.util.AbstractFilterLogic;
import com.stpl.app.adminconsole.common.util.CommonUtil;
import com.stpl.app.adminconsole.dao.CommonDAO;
import com.stpl.app.adminconsole.dao.impl.CommonDAOImpl;
import com.stpl.app.adminconsole.processscheduler.dto.HierarchyDefinitionDTO;
import com.stpl.app.adminconsole.processscheduler.dto.OutboundTableDTO;
import com.stpl.app.adminconsole.processscheduler.dto.RelationshipOutboundDTO;
import com.stpl.app.adminconsole.util.CommonUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.HelperListUtil;
import com.stpl.app.model.RelationshipBuilder;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.adminconsole.common.util.CommonUIUtil;
import com.stpl.app.adminconsole.util.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.HierarchyDefinitionLocalServiceUtil;
import com.stpl.app.service.RelationshipBuilderLocalServiceUtil;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.filter.Between;
import com.vaadin.v7.data.util.filter.Compare;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.TextField;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author vinodhini
 */
public class OutboundLogic {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(OutboundLogic.class);
    private static final HelperListUtil helperListUtil = HelperListUtil.getInstance();
    public final SimpleDateFormat dbDate = new SimpleDateFormat("yyyy-MM-dd");
    private static HashMap<String, String> hierarchySearchCriteria = new HashMap<>();
    private static HashMap<String, String> hierarchyFilterMap = new HashMap<>();
    private static HashMap<String, String> hierarchyTypeMap = new HashMap<>();
    private SimpleDateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");
    private static List<String> hierarchyCheckAllMap = new ArrayList<>();
    private static HashMap<String, String> rbMap = new HashMap<>();
    private static CommonDAO dao = new CommonDAOImpl();
    private static HashMap<String, String> columnNames = new HashMap<>();
    public static final String RELATIONSHIP_DESCRIPTION = "relationshipDescription";
    
    static {
        hierarchyFilterMap.put(ConstantsUtils.HIERARCHY_NAME, StringConstantUtils.HDHIERARCHY_NAME);
        hierarchyFilterMap.put("hierarchyTypeDto", "HD.HIERARCHY_TYPE");
        hierarchyFilterMap.put(StringConstantUtils.HIERARCHY_CATEGORY_PROPERTY, StringConstantUtils.HDHIERARCHY_CATEGORY);
        hierarchyFilterMap.put("noOfLevels", "HD.NO_OF_LEVELS");
        hierarchyFilterMap.put("versionNo", "HD.VERSION_NO");
        hierarchyFilterMap.put(ConstantsUtils.CREATED_BY, "HD.CREATED_BY");
        hierarchyFilterMap.put(StringConstantUtils.CREATED_DATE_PROPERTY, StringConstantUtils.HDCREATED_DATE);
        hierarchyFilterMap.put(ConstantsUtils.MODIFIED_DATE, "HD.MODIFIED_DATE");
    }
       static {
        List<HelperDTO> hierarchyTypeList = helperListUtil.getListNameMap().get(CommonUIUtil.RELATIONSHIP_TYPE);
        for (HelperDTO hierarchyType : hierarchyTypeList) {
            hierarchyTypeMap.put(hierarchyType.getDescription(), String.valueOf(hierarchyType.getId()));
        }
    }
    public int getHierarchyDefinitionCount(final ErrorfulFieldGroup searchFields, final Set<Container.Filter> filterSet, String hierType) {
        int count = 0;
        StringBuilder queryBuilder = buildHierarchyDefinitionSearchQuery(searchFields, true, hierType);
        getHdFilterQuery(filterSet, queryBuilder);


        List<Object> masterData = (List<Object>) HelperTableLocalServiceUtil.executeSelectQuery(queryBuilder.toString());
        if (masterData != null && !masterData.isEmpty()) {
            Object ob = masterData.get(0);
            count += Integer.parseInt(String.valueOf(ob));
        }
        LOGGER.debug(" getHierarchyDefinitionCount= {}" , count);
        return count;
    }

    public List<HierarchyDefinitionDTO> loadHierarchyDefinitionResults(
            final ErrorfulFieldGroup searchFields, final int start, final int end, final List<SortByColumn> columns, final Set<Container.Filter> filterSet, String hierType, boolean isCheckAll) {
        LOGGER.debug("Entering loadHierarchyDefinitionResults with start of= {}, endIndex of= {}, Column Size= {}, " , start, end, ((columns == null) ? columns : columns.size()));
        StringBuilder queryBuilder = buildHierarchyDefinitionSearchQuery(searchFields, false, hierType);
        getHdFilterQuery(filterSet, queryBuilder);
        getHdOrderQuery(queryBuilder, columns, start, end);

        final List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(queryBuilder.toString());
        List<HierarchyDefinitionDTO> searchList = getCustomizedHdDTO(list, isCheckAll);
        LOGGER.debug("End of loadHierarchyDefinitionResults");
        return searchList;
    }

    private void loadHdSearchCriteriaMap() {
        if (hierarchySearchCriteria.isEmpty()) {
        hierarchySearchCriteria.clear();
        hierarchySearchCriteria.put(ConstantsUtils.HIERARCHY_NAME, StringConstantUtils.HDHIERARCHY_NAME);
        hierarchySearchCriteria.put(StringConstantUtils.HIERARCHY_CATEGORY_PROPERTY, StringConstantUtils.HDHIERARCHY_CATEGORY);        
        hierarchySearchCriteria.put(ConstantsUtils.CREATED_DATE_FROM, StringConstantUtils.HDCREATED_DATE);
        hierarchySearchCriteria.put(ConstantsUtils.CREATED_DATE_TO, StringConstantUtils.HDCREATED_DATE);
        }
    }

    private StringBuilder buildHierarchyDefinitionSearchQuery(ErrorfulFieldGroup searchFields, boolean isCount, String hierType) {
       
        StringBuilder queryBuilder = new StringBuilder(isCount ? SQlUtil.getQuery("getHierarchyDefinitionCount") : SQlUtil.getQuery("getHierarchyDefinitionResults"));
        queryBuilder.append(hierarchyTypeMap.get(hierType));
        loadHdSearchCriteriaMap();
          
        Set<Map.Entry<String, String>> keys = hierarchySearchCriteria.entrySet();
        for (Map.Entry<String, String> entry : keys) {
            String fields = entry.getKey();
            String value = entry.getValue();
            if (searchFields.getField(fields) != null && searchFields.getField(fields).getValue() != null && !ConstantsUtils.SELECT_ONE.equals(searchFields.getField(fields).getValue().toString()) && !searchFields.getField(fields).getValue().toString().trim().isEmpty()) {
                    if (StringConstantUtils.HIERARCHY_CATEGORY_PROPERTY.equalsIgnoreCase(fields)) {

                        queryBuilder.append(StringConstantUtils.AND_SPACE).append(value).append(" = '").append(((HelperDTO) searchFields.getField(fields).getValue()).getId()).append('\'');

                    } else if (ConstantsUtils.CREATED_DATE_FROM.equalsIgnoreCase(fields)) {
                        queryBuilder.append(StringConstantUtils.AND_SPACE).append(value).append(" > '").append(dbDate.format((Date) searchFields.getField(fields).getValue())).append('\'');
                    } else if (ConstantsUtils.CREATED_DATE_TO.equalsIgnoreCase(fields)) {
                        queryBuilder.append(StringConstantUtils.AND_SPACE).append(value).append(" < '").append(dbDate.format((Date) searchFields.getField(fields).getValue())).append('\'');
                    } else {
                        queryBuilder.append(StringConstantUtils.AND_SPACE).append(value).append(StringConstantUtils.LIKE_QUOTE).append(String.valueOf(searchFields.getField(fields).getValue()).trim().replace('*', '%')).append('\'');
                    }
            }
        }

        return queryBuilder;
    }

    private List<HierarchyDefinitionDTO> getCustomizedHdDTO(List list, boolean isCheckAll) {
        final List<HierarchyDefinitionDTO> searchResultsList = new ArrayList<>();
        try {
            if (list != null) {
                Map<Integer, String> userMap = StplSecurity.getUserMap();
                if (userMap.isEmpty()) {
                    userMap = StplSecurity.getUserName();
                }

                for (int i = 0; i < list.size(); i++) {
                    final HierarchyDefinitionDTO hierarchyBuilderDTO = new HierarchyDefinitionDTO();
                    final Object[] object = (Object[]) list.get(i);

                    hierarchyBuilderDTO.setHierarchyDefinitionSystemId(!"null".equals(String.valueOf(object[0])) && StringUtils.isNotBlank(String.valueOf(object[0])) ? String.valueOf(object[0]) : "0");
                    hierarchyBuilderDTO.setHierarchyName(!"null".equals(String.valueOf(object[1])) && StringUtils.isNotBlank(String.valueOf(object[1])) ? String.valueOf(object[1]) : StringUtils.EMPTY);
                    hierarchyBuilderDTO.setHierarchyTypeDto(helperListUtil.getIdHelperDTOMap().get(object[NumericConstants.TWO] != null ? Integer.parseInt(String.valueOf(object[NumericConstants.TWO])) : 0));
                    hierarchyBuilderDTO.setHierarchyCategory(helperListUtil.getIdHelperDTOMap().get(object[NumericConstants.THREE] != null ? Integer.parseInt(String.valueOf(object[NumericConstants.THREE])) : 0));
                    hierarchyBuilderDTO.setNoOfLevels(!"null".equals(String.valueOf(object[NumericConstants.FOUR])) && StringUtils.isNotBlank(String.valueOf(object[NumericConstants.FOUR])) ? String.valueOf(object[NumericConstants.FOUR]) : StringUtils.EMPTY);
                    hierarchyBuilderDTO.setCreatedBy(object[NumericConstants.FIVE] != null ? userMap.get(Integer.valueOf(String.valueOf(object[NumericConstants.FIVE]))) : StringUtils.EMPTY);
                    if (object[NumericConstants.SIX] != null) {
                        Date createdDate = dateformat.parse(com.stpl.app.adminconsole.util.CommonUtils.convertDateToString((Date) (object[NumericConstants.SIX])));
                        hierarchyBuilderDTO.setCreatedDate(createdDate);
                    }
                    if (object[NumericConstants.EIGHT] != null) {
                        Date modifiedDate = dateformat.parse(com.stpl.app.adminconsole.util.CommonUtils.convertDateToString((Date) (object[NumericConstants.EIGHT])));
                        hierarchyBuilderDTO.setModifiedDate(modifiedDate);
                    }
                    hierarchyBuilderDTO.setVersionNo(String.valueOf(object[NumericConstants.NINE]));
                    hierarchyBuilderDTO.setCheck(isCheckAll);
                    searchResultsList.add(hierarchyBuilderDTO);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return searchResultsList;
    }


    private StringBuilder getHdFilterQuery(final Set<Container.Filter> filterSet, final StringBuilder stringBuilder) {
        if (filterSet != null) {
            stringBuilder.append(AbstractFilterLogic.getAdminInstance().filterQueryGenerator(filterSet, hierarchyFilterMap).toString().replace("where", " AND"));
        }
        return stringBuilder;
    }

    private StringBuilder getHdOrderQuery(StringBuilder stringBuilder, final List<SortByColumn> sortByColumns, final int startIndex, final int endIndex) {
        boolean sortOrder = false;
        String orderByColumn = null;
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

//    public void loadHierarchyTypeMap() {
//      
//    }

    /**
     * Check search criteria.
     *
     * @param binder the binder
     * @return true, if successful
     */
    public boolean checkSearchCriteria(final ErrorfulFieldGroup binder) {
        boolean isvalid = false;
        for (Object object : binder.getFields()) {
            if (object instanceof TextField && ((TextField) object).isVisible()) {
                if (StringUtils.isNotBlank(((TextField) object).getValue())) {
                    isvalid = true;
                    break;
                }
            } else if (object instanceof ComboBox && ((ComboBox) object).isVisible() && !ConstantsUtils.SELECT_ONE.equals(((ComboBox) object).getValue().toString())) {
                    isvalid = true;
                    break;
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
            final Set<Container.Filter> filterSet) throws SystemException, ParseException{
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
            final Set<Container.Filter> filterSet, final boolean isCheckAll) throws SystemException, ParseException {
        return (List) loadRelationshipBuilderLogic(binder, isCheckAll, start, offset, isCount, columns, filterSet);
    }

    private Object loadRelationshipBuilderLogic(ErrorfulFieldGroup binder, final boolean isCheckAll, int start, int offset, final boolean isCount, final List<SortByColumn> columns, final Set<Container.Filter> filterSet) throws SystemException, ParseException {

        List list;
        if (isCount) {
            return (Integer) getSearchResults(binder, isCheckAll, false, start, start + offset, columns, filterSet, isCount);
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
            final int endIndex, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet, boolean isCount) throws SystemException, ParseException {
        LOGGER.debug("getSearchResults started with P1:CustomFieldGroup relationBuilderForm");
        List<RelationshipBuilder> resultList = new ArrayList<>();
        List<RelationshipOutboundDTO> itemGroupList = new ArrayList<>();
        Date startDateFrom;
        Date startDateTo;
        Date creationDateFrom;
        Date creationDateTo;
        try {
            final DynamicQuery relationBuilderDynamicQuery = RelationshipBuilderLocalServiceUtil.dynamicQuery();

            if (relationBuilderForm.getField(StringConstantUtils.RELATIONSHIP_TYPE_PROPERTY).getValue() != null
                    && StringUtils.isNotBlank(relationBuilderForm.getField(StringConstantUtils.RELATIONSHIP_TYPE_PROPERTY).getValue().toString())) {
                final String relationshiptype = relationBuilderForm.getField(StringConstantUtils.RELATIONSHIP_TYPE_PROPERTY).getValue().toString().trim();
                int relationShipType = CommonUtil.getIDFromHelper(relationshiptype, "RELATIONSHIP_TYPE");

                relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.RELATIONSHIP_TYPE, relationShipType));

            }

            if (relationBuilderForm.getField(StringConstantUtils.RELATIONSHIP_NAME).getValue() != null
                    && StringUtils.isNotBlank(relationBuilderForm.getField(StringConstantUtils.RELATIONSHIP_NAME).getValue().toString())) {
                final String relationshipName = relationBuilderForm.getField(StringConstantUtils.RELATIONSHIP_NAME).getValue().toString().trim();
                final String relationshipName1 = relationshipName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);

                relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.RELATIONSHIP_NAME, relationshipName1));

            }
            if (relationBuilderForm.getField(StringConstantUtils.RELATIONSHIP_DESCRIPTION_PROPERTY).getValue() != null
                    && StringUtils.isNotBlank(relationBuilderForm.getField(StringConstantUtils.RELATIONSHIP_DESCRIPTION_PROPERTY).getValue().toString())) {
                final String relationshipDescription = relationBuilderForm.getField(StringConstantUtils.RELATIONSHIP_DESCRIPTION_PROPERTY).getValue().toString().trim();
                final String relationshipDesc1 = relationshipDescription.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
                relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.ilike(StringConstantUtils.RELATIONSHIP_DESCRIPTION_PROPERTY, relationshipDesc1));

            }
            if (relationBuilderForm.getField(StringConstantUtils.HIERARCHY_NAME_DDLB).getValue() != null && StringUtils.isNotBlank(relationBuilderForm.getField(StringConstantUtils.HIERARCHY_NAME_DDLB).getValue().toString())
                    && ((HelperDTO) relationBuilderForm.getField(StringConstantUtils.HIERARCHY_NAME_DDLB).getValue()).getId() != 0) {
                relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.HIERARCHY_DEFINATION_SID, ((HelperDTO) relationBuilderForm.getField(StringConstantUtils.HIERARCHY_NAME_DDLB).getValue()).getId()));

            }
         
            if (relationBuilderForm.getField(StringConstantUtils.START_DATE_FROM).getValue() != null ) {
                startDateFrom = (Date) relationBuilderForm.getField(StringConstantUtils.START_DATE_FROM).getValue();
                relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.START_DATE, startDateFrom));
            }
            if (relationBuilderForm.getField(StringConstantUtils.START_DATE_TO).getValue() != null) {
                startDateTo = (Date) relationBuilderForm.getField(StringConstantUtils.START_DATE_TO).getValue();
                relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.le(ConstantsUtils.START_DATE, startDateTo));
            }
            
            if (relationBuilderForm.getField(StringConstantUtils.CREATION_DATE_FROM).getValue() != null) {
                creationDateFrom = (Date) relationBuilderForm.getField(StringConstantUtils.CREATION_DATE_FROM).getValue();
                relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.ge(StringConstantUtils.CREATED_DATE_PROPERTY, creationDateFrom));
            }
            if (relationBuilderForm.getField(StringConstantUtils.CREATION_DATE_TO).getValue() != null) {
                creationDateTo = (Date) relationBuilderForm.getField(StringConstantUtils.CREATION_DATE_TO).getValue();
                relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.le(StringConstantUtils.CREATED_DATE_PROPERTY, creationDateTo));
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
                            relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.ilike(StringConstantUtils.RELATIONSHIP_DESCRIPTION_PROPERTY, filterString));
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
                            final DynamicQuery hierarchyDynamicQuery = HierarchyDefinitionLocalServiceUtil.dynamicQuery();
                            final ProjectionList projList = ProjectionFactoryUtil.projectionList();
                            projList.add(ProjectionFactoryUtil.property(ConstantsUtils.HIERARCHY_DEFINATION_SID));
                            hierarchyDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.HIERARCHY_NAME, filterString));
                            hierarchyDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));
                            List hdResultList = dao.getHierachyDefinitionList(hierarchyDynamicQuery);
                            if (hdResultList.isEmpty()) {
                                relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.HIERARCHY_DEFINATION_SID, 0));
                            } else {
                                relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.in(ConstantsUtils.HIERARCHY_DEFINATION_SID, hdResultList));
                            }
                        }
                    } else if (filter instanceof Compare) {
                        Compare compare = (Compare) filter;
                        Compare.Operation operation = compare.getOperation();
                         
                        if (ConstantsUtils.VERSION_NO.equalsIgnoreCase(String.valueOf(compare.getPropertyId()))) {
                            int value = Integer.parseInt(String.valueOf(compare.getValue()));
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
            LOGGER.error(e.getMessage());
        }
        if (isCheckQuery) {
            return resultList;
        } else if (isCount) {
            return resultList.size();
        } else {
            if (resultList != null && !resultList.isEmpty()) {
                itemGroupList = getCustomizedResults(resultList, isCheckAll);
            }
            LOGGER.debug("getSearchResults return List<RelationshipBuilderDTO> itemGroupList= {}" , itemGroupList.size());
            return itemGroupList;
        }

    }

    /**
     * Gets the customized results.
     *
     * @param resultList the result list
     * @return the customized results
     */
    private List<RelationshipOutboundDTO> getCustomizedResults(final List<RelationshipBuilder> resultList, boolean isCheckAll) throws SystemException, ParseException {
        LOGGER.debug("getCustomizedResults started with P1:List<RelationshipBuilder> resultList");
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
            if (relationBuilder.getModifiedDate() != null) {
                relationBuilderDTO.setModifiedDate(parsetDate(String.valueOf(relationBuilder.getModifiedDate())));
            }
            relationBuilderDTO.setRbSystemId(String.valueOf(relationBuilder.getRelationshipBuilderSid()));
            relationBuilderDTO.setSystemID(relationBuilder.getRelationshipBuilderSid());
            relationBuilderDTO.setCheck(isCheckAll);
            relationBuilderList.add(relationBuilderDTO);
        }
        LOGGER.debug("getCustomizedResults return List<RelationshipBuilderDTO> relationBuilderList= {}" , relationBuilderList.size());
        return relationBuilderList;
    }

    public List hierarchyQueryResults(String ids, String queryName,boolean isScheduled) {
        Map<String, String> input = null;
        if (!isScheduled) {
            input = new HashMap<>();
            input.put("?HIERARCHY_DEFINITION_SID", ids);
        }
        return (List) HelperTableLocalServiceUtil.executeSelectQuery(CommonUtil.replacedQuery(input, queryName));
    }
   
    /**
     * To get the Hierarchy Definition id when check all is selected
     *
     * @param searchFields
     * @param hierType
     * @return
     */
    public List<OutboundTableDTO> getHierarchyCheckedAllResults(ErrorfulFieldGroup searchFields, String hierType) {
       
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(hierarchyTypeMap.get(hierType));
       
        if (hierarchySearchCriteria.isEmpty()) {
            loadHdSearchCriteriaMap();
        }
        for (String fields : hierarchyCheckAllMap) {
            if (searchFields.getField(fields) != null && searchFields.getField(fields).getValue() != null && !ConstantsUtils.SELECT_ONE.equals(searchFields.getField(fields).getValue().toString()) && !searchFields.getField(fields).getValue().toString().trim().isEmpty()) {
                    if (StringConstantUtils.HIERARCHY_CATEGORY_PROPERTY.equalsIgnoreCase(fields)) {
                        queryBuilder.append(StringConstantUtils.AND_SPACE).append(hierarchySearchCriteria.get(fields)).append(" = '").append(((HelperDTO) searchFields.getField(fields).getValue()).getId()).append('\'');
                    } else if (ConstantsUtils.CREATED_DATE_FROM.equalsIgnoreCase(fields)) {
                        queryBuilder.append(StringConstantUtils.AND_SPACE).append(hierarchySearchCriteria.get(fields)).append(" > '").append(dbDate.format((Date) searchFields.getField(fields).getValue())).append('\'');
                    } else if (ConstantsUtils.CREATED_DATE_TO.equalsIgnoreCase(fields)) {
                        queryBuilder.append(StringConstantUtils.AND_SPACE).append(hierarchySearchCriteria.get(fields)).append(" < '").append(dbDate.format((Date) searchFields.getField(fields).getValue())).append('\'');
                    } else {
                        queryBuilder.append(StringConstantUtils.AND_SPACE).append(hierarchySearchCriteria.get(fields)).append(StringConstantUtils.LIKE_QUOTE).append(String.valueOf(searchFields.getField(fields).getValue()).trim().replace('*', '%')).append('\'');
                    }
            }
        }

        Map<String, String> input = new HashMap<>();
        input.put("?CONDITIONS", queryBuilder.toString());
        List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(CommonUtil.replacedQuery(input, "getHierarchyDefinitionOuboundCheckAllResults"));
        return getCustomizedOutboundTableDTO(list);
        
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
        return getCustomizedOutboundTableDTO(queryList);
    }

    /**
     * Gets the customized results.
     *
     * @param resultList the result list
     * @return the customized results
     */
    private List<OutboundTableDTO> getCustomizedOutboundTableDTO(final List resultList) {
        LOGGER.debug("getCustomizedOutboundTableDTO started :List resultList");
        final List<OutboundTableDTO> outboundTableResults = new ArrayList<>();
        try {
            if (resultList != null) {
                int totalLevel;
                int resultsSize = resultList.size();
                for (int i = 0; i < resultsSize;) {
                    final OutboundTableDTO outboundTableDTO = new OutboundTableDTO();
                    Object[] object = (Object[]) resultList.get(i);

                    outboundTableDTO.setHierarchyDefinitionSystemId(!"null".equals(String.valueOf(object[0])) && StringUtils.isNotBlank(String.valueOf(object[0])) ? String.valueOf(object[0]) : "0");
                    outboundTableDTO.setHierarchyName(getValidatedStringValue(object[1]));
                    outboundTableDTO.setHierarchyType(getValidatedStringValue(object[NumericConstants.TWO]));
                    outboundTableDTO.setHierarchyCategory(getValidatedStringValue(object[NumericConstants.THREE]));
                    outboundTableDTO.setNooflevels(!"null".equals(String.valueOf(object[NumericConstants.FOUR])) && StringUtils.isNotBlank(String.valueOf(object[NumericConstants.FOUR])) ? String.valueOf(object[NumericConstants.FOUR]) : "0");
                    outboundTableDTO.setVersion(getValidatedStringValue(object[NumericConstants.FIVE]));
                    totalLevel = Integer.parseInt(outboundTableDTO.getNooflevels());

                    for (int j = 1; j <= totalLevel && i < resultsSize; i++, j++) {
                        object = (Object[]) resultList.get(i);
                        String commonColumn = "level" + j;
                        Object levelColumn = commonColumn + "LevelName";
                        String value = getValidatedStringValue(object[NumericConstants.SIX]);
                        outboundTableDTO.addStringProperties(levelColumn, value);

                        value = getValidatedStringValue(object[NumericConstants.EIGHT]);
                        levelColumn = commonColumn + "LevelValueReference";
                        outboundTableDTO.addStringProperties(levelColumn, value);

                        value = getValidatedStringValue(object[NumericConstants.NINE]);
                        levelColumn = commonColumn + "TableName";
                        outboundTableDTO.addStringProperties(levelColumn, value);

                        value = getValidatedStringValue(object[NumericConstants.TEN]);
                        levelColumn = commonColumn + "FieldName";
                        outboundTableDTO.addStringProperties(levelColumn, value);

                        value = getValidatedStringValue(object[NumericConstants.ELEVEN]);
                        levelColumn = commonColumn + "InclusionRuleType";
                        outboundTableDTO.addStringProperties(levelColumn, value);

                        value = getValidatedStringValue(object[NumericConstants.TWELVE]);
                        levelColumn = commonColumn + "InclusionRule";
                        outboundTableDTO.addStringProperties(levelColumn, value);

                        value = getValidatedStringValue(object[NumericConstants.THIRTEEN]);
                        levelColumn = commonColumn + "InclusionCondition";
                        outboundTableDTO.addStringProperties(levelColumn, value);

                        value = getValidatedStringValue(object[NumericConstants.FOURTEEN]);
                        levelColumn = commonColumn + "ExclusionRuleType";
                        outboundTableDTO.addStringProperties(levelColumn, value);

                        value = getValidatedStringValue(object[NumericConstants.FIFTEEN]);
                        levelColumn = commonColumn + "ExclusionRule";
                        outboundTableDTO.addStringProperties(levelColumn, value);

                        value = getValidatedStringValue(object[NumericConstants.SIXTEEN]);
                        levelColumn = commonColumn + "ExclusionCondition";
                        outboundTableDTO.addStringProperties(levelColumn, value);
                    }
                    outboundTableResults.add(outboundTableDTO);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.debug("getCustomizedOutboundTableDTO return List<OutboundTableDTO> OutboundTableResults= {}" , outboundTableResults.size());
        return outboundTableResults;
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
            List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(CommonUtil.replacedQuery(input, "getRelationshipBuilderOutbound"));
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
            date = format.parse(tempDate);
        }

        return date;
    }
         
    public Set getAllCffIds() {
        String query = SQlUtil.getQuery("getCffOuboundSearchResults");
        Set checkedCffsSet = new HashSet();

        List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                checkedCffsSet.add(String.valueOf(list.get(i)));
            }
        }
        return checkedCffsSet;
    }

    public String getFormattedIds(String query) {
        StringBuilder allIds = new StringBuilder();
        List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                allIds.append(',').append(String.valueOf(list.get(i)));
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

                rbOuboundDTO.setRelationshipName(getValidatedStringValue(obj[NumericConstants.THREE]));
                rbOuboundDTO.setRelationshipDescription(getValidatedStringValue(obj[NumericConstants.FOUR]));
                rbOuboundDTO.setRelationshipType(getValidatedStringValue(obj[NumericConstants.FIVE]));
                rbOuboundDTO.setHierarchyName(getValidatedStringValue(obj[NumericConstants.SIX]));

                rbOuboundDTO.setHierarchyVersionNo(getValidatedStringValue(obj[NumericConstants.EIGHT]));
                
                if (obj[NumericConstants.NINE] != null) {
                    String startDate = dateformat.format((Date) (obj[NumericConstants.NINE]));
                    rbOuboundDTO.setStartDate(startDate);
                }
                rbOuboundDTO.setBuildType(getValidatedStringValue(obj[NumericConstants.TEN]));
                int sid = Integer.parseInt(String.valueOf(obj[1] != null ? obj[1] : "0"));
                int nextId = sid;
                for (; i < listSize && (sid == nextId); i++) {
                    obj = (Object[]) list.get(i);
                    if (listSize != (i + 1)) {
                        Object[] nextObj = (Object[]) list.get(i + 1);
                        nextId = Integer.parseInt(String.valueOf(nextObj[1] != null ? nextObj[1] : "0"));
                    }
                    rbOuboundDTO.setRelationshipTree(getValidatedStringValue(obj[NumericConstants.TWO]));
                    rbOuboundDTO.setHierarchyLevelName(getValidatedStringValue(obj[NumericConstants.SEVEN]));
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
            String rbQuery=SQlUtil.getQuery("getRelationshipOuboundScheduleResults");
            List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(rbQuery);
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
            StringBuilder queryBuilder = new StringBuilder();
           
            if (rbMap.isEmpty()) {
                rbMap.clear();
                rbMap.put(StringConstantUtils.RELATIONSHIP_NAME, "R.RELATIONSHIP_NAME");
                rbMap.put(StringConstantUtils.RELATIONSHIP_TYPE_PROPERTY, "RELATIONSHIP_TYPE");
                rbMap.put(StringConstantUtils.RELATIONSHIP_DESCRIPTION_PROPERTY, "R.RELATIONSHIP_DESCRIPTION");
                rbMap.put(StringConstantUtils.HIERARCHY_NAME_DDLB, StringConstantUtils.HDHIERARCHY_NAME);
                rbMap.put(StringConstantUtils.START_DATE_FROM, "R.START_DATE");
                rbMap.put(StringConstantUtils.START_DATE_TO, "R.START_DATE");
                rbMap.put(StringConstantUtils.CREATION_DATE_FROM, "R.CREATED_DATE");
                rbMap.put(StringConstantUtils.CREATION_DATE_TO, "R.CREATED_DATE");
            }
            Set<Map.Entry<String, String>> keys = rbMap.entrySet();
            for (Map.Entry<String, String> entry : keys) {
                String fields = entry.getKey();
                String value = entry.getValue();
                if (searchFields.getField(fields) != null) {
                    if (StringConstantUtils.HIERARCHY_NAME_DDLB.equalsIgnoreCase(fields)) {
                        if (searchFields.getField(StringConstantUtils.HIERARCHY_NAME_DDLB).getValue() != null && StringUtils.isNotBlank(searchFields.getField(StringConstantUtils.HIERARCHY_NAME_DDLB).getValue().toString())
                                && ((HelperDTO) searchFields.getField(StringConstantUtils.HIERARCHY_NAME_DDLB).getValue()).getId() != 0) {
                            queryBuilder.append(StringConstantUtils.AND_SPACE).append(value).append(" = '").append(((HelperDTO) searchFields.getField(fields).getValue()).getDescription()).append('\'');
                        }
                    } else if (searchFields.getField(fields).getValue() != null && !String.valueOf(searchFields.getField(fields).getValue()).trim().isEmpty()) {
                        if (StringConstantUtils.RELATIONSHIP_TYPE_PROPERTY.equalsIgnoreCase(fields)) {
                            queryBuilder.append(StringConstantUtils.AND_SPACE).append(value).append(" = '").append(hierarchyTypeMap.get(String.valueOf(searchFields.getField(fields).getValue()))).append('\'');
                        } else if (StringConstantUtils.START_DATE_FROM.equalsIgnoreCase(fields) || StringConstantUtils.CREATION_DATE_FROM.equalsIgnoreCase(fields)) {
                            queryBuilder.append(StringConstantUtils.AND_SPACE).append(value).append(" > '").append(dbDate.format((Date) searchFields.getField(fields).getValue())).append('\'');
                        } else if (StringConstantUtils.START_DATE_TO.equalsIgnoreCase(fields) || StringConstantUtils.CREATION_DATE_TO.equalsIgnoreCase(fields)) {
                            queryBuilder.append(StringConstantUtils.AND_SPACE).append(value).append(" < '").append(dbDate.format((Date) searchFields.getField(fields).getValue())).append('\'');
                        } else {
                            queryBuilder.append(StringConstantUtils.AND_SPACE).append(value).append(StringConstantUtils.LIKE_QUOTE).append(String.valueOf(searchFields.getField(fields).getValue()).trim().replace('*', '%')).append('\'');
                        }
                    }
                }
            }

            Map<String, String> input = new HashMap<>();
            input.put("?ANDQUERY", queryBuilder.toString());
            input.put("?LWHEREQUERY", queryBuilder.toString().replaceFirst("AND", "WHERE"));

            List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(CommonUtil.replacedQuery(input, "getRelationshipOuboundCheckAllResults"));
            rbOutboundList = getCustomizedRbOutbound(list);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return rbOutboundList;
    }
  
    public static String getDBColumnName(String visibleColumnName) {
        return columnNames.get(visibleColumnName);
    }

    public static HashMap<String, String> loadColumnName() {

        columnNames.put("relationshipName", "relationshipName");
        columnNames.put("relationshipDesc", RELATIONSHIP_DESCRIPTION);
        columnNames.put("relationshipType", "relationshipType");
        columnNames.put("hierarchyName", "hierarchyDefinitionSid");
        columnNames.put(ConstantsUtils.VERSION_NO, ConstantsUtils.VERSION_NO);
        columnNames.put("startDate", "startDate");
        columnNames.put(ConstantsUtils.CREATED_DATE, ConstantsUtils.CREATED_DATE);
        columnNames.put(ConstantsUtils.MODIFIED_DATE, ConstantsUtils.MODIFIED_DATE);
        columnNames.put(ConstantsUtils.CREATED_BY, ConstantsUtils.CREATED_BY);

        return columnNames;
    }
}