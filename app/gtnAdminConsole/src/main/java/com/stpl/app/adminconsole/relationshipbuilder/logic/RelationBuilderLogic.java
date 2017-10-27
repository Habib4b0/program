/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.relationshipbuilder.logic;

import com.stpl.app.adminconsole.abstractsearch.dto.SearchResultsDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

import com.stpl.app.adminconsole.bpm.dto.VwHelperListDto;
import com.stpl.app.adminconsole.bpm.logic.BpmLogic;
import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.common.util.CommonUtil;
import com.stpl.app.adminconsole.dao.CommonDAO;
import com.stpl.app.adminconsole.dao.RelationBuilderLogicDAO;
import com.stpl.app.adminconsole.dao.impl.CommonDAOImpl;
import com.stpl.app.adminconsole.dao.impl.RelationBuilderLogicDAOImpl;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.HierarchyDefinition;
import com.stpl.app.model.HierarchyLevelDefinition;
import com.stpl.app.model.HierarchyLevelValues;
import com.stpl.app.model.HistHierarchyDefinition;
import com.stpl.app.model.HistHierarchyLevelDefn;
import com.stpl.app.model.HistHierarchyLevelValues;
import com.stpl.app.model.HistRelationshipBuilder;
import com.stpl.app.model.HistRelationshipLevelDefn;
import com.stpl.app.model.RelationshipBuilder;
import com.stpl.app.model.RelationshipLevelDefinition;
import com.stpl.app.adminconsole.relationshipbuilder.dto.HierarchyLevelsDTO;
import com.stpl.app.adminconsole.relationshipbuilder.dto.RelationshipBuilderDTO;
import com.stpl.app.adminconsole.relationshipbuilder.util.CommonUtils;
import com.stpl.app.service.HistRelationshipBuilderLocalServiceUtil;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.ErrorCodeUtil;
import com.stpl.app.adminconsole.util.ErrorCodes;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.RelationshipBuilderLocalServiceUtil;
import com.stpl.app.service.RelationshipLevelDefinitionLocalServiceUtil;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.ifs.ui.CustomFieldGroup;
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
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Notification;
import java.text.SimpleDateFormat;

import java.util.Set;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;

/**
 * The Class RelationBuilderLogic.
 *
 * @author vishalakshi
 */
public class RelationBuilderLogic {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(RelationBuilderLogic.class);
    /**
     * The dao.
     */
    public static RelationBuilderLogicDAO dao = new RelationBuilderLogicDAOImpl();

    static HashMap<String, String> columnNames = new HashMap<String, String>();

    /**
     * @return the dao
     */
    public RelationBuilderLogicDAO getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(final RelationBuilderLogicDAO dao) {
        this.dao = dao;
    }
    SessionDTO sessionDTO;

    public RelationBuilderLogic(SessionDTO sessionDTO) {
        this.sessionDTO = sessionDTO;
    }

    public RelationBuilderLogic() {

    }

    /**
     * Load hierarchy.
     *
     * @return the list
     * @throws com.stpl.portal.kernel.exception.SystemException
     * @throws java.lang.Exception
     */
    public ComboBox loadHierarchy(ComboBox select, SessionDTO sessionDTO) throws SystemException {
        LOGGER.debug("loadHierarchy started");
        final String fromViewPage = sessionDTO.getFromViewPage();

        if (fromViewPage.equalsIgnoreCase(ConstantsUtils.SMALL_YES) || fromViewPage.equalsIgnoreCase(ConstantsUtils.EDIT)) {
            select = CommonUtils.getHistHierarchy(select, sessionDTO);

        } else {
            select = CommonUtils.getHierarchy(select);
        }
        LOGGER.debug("loadHierarchy return List<HelperDTO> hierarchiesList");
        return select;
    }

    /**
     * Gets the hierarchy levels.
     *
     * @param selectedHierarchyId the selected hierarchy id
     * @return the hierarchy levels
     * @throws com.stpl.portal.kernel.exception.SystemException
     * @throws java.lang.Exception
     */
    public List<HierarchyLevelDefinition> getHierarchyLevels(final int selectedHierarchyId) throws SystemException {
        LOGGER.debug("getHierarchyLevels started with P1:int selectedHierarchyId=" + selectedHierarchyId);

        List<HierarchyLevelDefinition> hierarchyLevelsList;
        final DynamicQuery itemgroupDynamicQuery = DynamicQueryFactoryUtil.forClass(HierarchyLevelDefinition.class);
        itemgroupDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.HIERARCHY_DEFINATION_SID, selectedHierarchyId));
        itemgroupDynamicQuery.addOrder(OrderFactoryUtil.asc(LEVEL_NO));
        hierarchyLevelsList = dao.getHierarchyLevelDefinitionList(itemgroupDynamicQuery);
        LOGGER.debug("getHierarchyLevels return List<HierarchyLevelDefinition> hierarchyLevelsList");
        return hierarchyLevelsList;
    }
    public static final String LEVEL_NO = "levelNo";

    /**
     * Gets the hierarchy levels from hist.
     *
     * @param selectedHierarchyId the selected hierarchy id
     * @param sessionDTO
     * @return the hierarchy levels
     * @throws com.stpl.portal.kernel.exception.SystemException
     * @throws java.lang.Exception
     */
    public List<HistHierarchyLevelDefn> getHistHierarchyLevels(final int selectedHierarchyId, final SessionDTO sessionDTO) throws SystemException {
        LOGGER.debug("getHierarchyLevels started with P1:int selectedHierarchyId=" + selectedHierarchyId);
        final int versionNo = sessionDTO.getHierarchyVersion();
        List<HistHierarchyLevelDefn> hierarchyLevelsList;
        final DynamicQuery itemgroupDynamicQuery = DynamicQueryFactoryUtil.forClass(HistHierarchyLevelDefn.class);
        itemgroupDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.HIERARCHY_DEFINATION_SID, selectedHierarchyId));
        itemgroupDynamicQuery.addOrder(OrderFactoryUtil.asc(LEVEL_NO));
        itemgroupDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.PRIMARY_KEY + ConstantsUtils.VERSION_NO, versionNo));
        itemgroupDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.PRIMARY_KEY + ConstantsUtils.ACTION_FLAG, "D"));
        hierarchyLevelsList = dao.getHistHierarchyLevelDefinitionList(itemgroupDynamicQuery);
        LOGGER.debug("getHierarchyLevels return List<HierarchyLevelDefinition> hierarchyLevelsList");
        return hierarchyLevelsList;
    }

    /**
     * Gets the search results.
     *
     * @param relationBuilderForm the relation builder form
     * @return the search results
     * @throws com.stpl.portal.kernel.exception.SystemException
     * @throws java.lang.Exception
     */
    public List<SearchResultsDTO> getSearchResults(final ErrorfulFieldGroup relationBuilderForm, final String flag, final int startIndex,
            final int endIndex, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet, boolean isCount) throws SystemException {
        LOGGER.debug("getSearchResults started with P1:CustomFieldGroup relationBuilderForm");
        List<SearchResultsDTO> itemGroupList = new ArrayList<>();
        Date startDateFrom;
        Date startDateTo;
        Date creationDateFrom;
        Date creationDateTo;
        try {
            final DynamicQuery relationBuilderDynamicQuery = DynamicQueryFactoryUtil.forClass(RelationshipBuilder.class);
            final DynamicQuery relationBuilderHistDynamicQuery = DynamicQueryFactoryUtil.forClass(HistRelationshipBuilder.class);
            if (relationBuilderForm.getField(ConstantsUtils.OPTION2).getValue() != null
                    && StringUtils.isNotBlank(relationBuilderForm.getField(ConstantsUtils.OPTION2).getValue().toString())) {
                final String relationshipName = relationBuilderForm.getField(ConstantsUtils.OPTION2).getValue().toString().trim();
                int relationShipType = CommonUtil.getIDFromHelper(relationshipName, "RELATIONSHIP_TYPE");
                if (ConstantsUtils.SEARCH.equals(flag)) {
                    relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.RELATIONSHIP_TYPE, relationShipType));
                } else {
                    relationBuilderHistDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.RELATIONSHIP_TYPE, relationShipType));
                }

            }
            if (relationBuilderForm.getField(ConstantsUtils.TEXT1).getValue() != null
                    && StringUtils.isNotBlank(relationBuilderForm.getField(ConstantsUtils.TEXT1).getValue().toString())) {
                final String relationshipName = relationBuilderForm.getField(ConstantsUtils.TEXT1).getValue().toString().trim();
                final String relationshipName1 = relationshipName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);

                if (ConstantsUtils.SEARCH.equals(flag)) {
                    relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.RELATIONSHIP_NAME, relationshipName1));
                } else {
                    relationBuilderHistDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.RELATIONSHIP_NAME, relationshipName1));
                }

            }
            if (relationBuilderForm.getField(ConstantsUtils.TEXT4).getValue() != null
                    && StringUtils.isNotBlank(relationBuilderForm.getField(ConstantsUtils.TEXT4).getValue().toString())) {
                final String relationshipDesc = relationBuilderForm.getField(ConstantsUtils.TEXT4).getValue().toString().trim();
                final String relationshipDesc1 = relationshipDesc.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
                if (ConstantsUtils.SEARCH.equals(flag)) {
                    relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.ilike(RELATIONSHIP_DESCRIPTION, relationshipDesc1));
                } else {
                    relationBuilderHistDynamicQuery.add(RestrictionsFactoryUtil.ilike(RELATIONSHIP_DESCRIPTION, relationshipDesc1));
                }

            }
            if (relationBuilderForm.getField(ConstantsUtils.COMBO1).getValue() != null && StringUtils.isNotBlank(relationBuilderForm.getField(ConstantsUtils.COMBO1).getValue().toString())
                    && ((HelperDTO) relationBuilderForm.getField(ConstantsUtils.COMBO1).getValue()).getId() != 0) {
                if (ConstantsUtils.SEARCH.equals(flag)) {
                    relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.HIERARCHY_DEFINATION_SID, ((HelperDTO) relationBuilderForm.getField(ConstantsUtils.COMBO1).getValue()).getId()));
                } else {
                    relationBuilderHistDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.HIERARCHY_DEFINATION_SID, ((HelperDTO) relationBuilderForm.getField(ConstantsUtils.COMBO1).getValue()).getId()));
                }

            }
            if(relationBuilderForm.getField(ConstantsUtils.DATE1).getValue() != null && relationBuilderForm.getField(ConstantsUtils.DATE2).getValue() == null){
                startDateFrom = (Date) relationBuilderForm.getField(ConstantsUtils.DATE1).getValue();
                if (ConstantsUtils.SEARCH.equals(flag)) {
                    relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.START_DATE, startDateFrom));
                } else {
                    relationBuilderHistDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.START_DATE, startDateFrom));
                }
            }
            if(relationBuilderForm.getField(ConstantsUtils.DATE1).getValue() == null && relationBuilderForm.getField(ConstantsUtils.DATE2).getValue() != null){
                startDateTo = (Date) relationBuilderForm.getField(ConstantsUtils.DATE2).getValue();
                if (ConstantsUtils.SEARCH.equals(flag)) {
                    relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.le(ConstantsUtils.START_DATE, startDateTo));
                } else {
                    relationBuilderHistDynamicQuery.add(RestrictionsFactoryUtil.le(ConstantsUtils.START_DATE, startDateTo));
                }
            }
            if (relationBuilderForm.getField(ConstantsUtils.DATE1).getValue() != null && relationBuilderForm.getField(ConstantsUtils.DATE2).getValue() != null) {
                startDateFrom = (Date) relationBuilderForm.getField(ConstantsUtils.DATE1).getValue();
                startDateTo = (Date) relationBuilderForm.getField(ConstantsUtils.DATE2).getValue();
                if (ConstantsUtils.SEARCH.equals(flag)) {
                    relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.between(ConstantsUtils.START_DATE, startDateFrom, startDateTo));
                } else {
                    relationBuilderHistDynamicQuery.add(RestrictionsFactoryUtil.between(ConstantsUtils.START_DATE, startDateFrom, startDateTo));
                }

            }
            if (relationBuilderForm.getField(ConstantsUtils.DATE3).getValue() != null && relationBuilderForm.getField(ConstantsUtils.DATE4).getValue() == null) {
                creationDateFrom = (Date) relationBuilderForm.getField(ConstantsUtils.DATE3).getValue();
                if (ConstantsUtils.SEARCH.equals(flag)) {
                    relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.CREATED_DATE, creationDateFrom));
                } else {
                    relationBuilderHistDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.CREATED_DATE, creationDateFrom));
                }

            }
            if (relationBuilderForm.getField(ConstantsUtils.DATE3).getValue() == null && relationBuilderForm.getField(ConstantsUtils.DATE4).getValue() != null) {
                creationDateTo = (Date) relationBuilderForm.getField(ConstantsUtils.DATE4).getValue();
                if (ConstantsUtils.SEARCH.equals(flag)) {
                    SimpleDateFormat convert=new SimpleDateFormat("MM/dd/YYYY");
                    relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.sqlRestriction(" CAST(CREATED_DATE AS DATE) <= '"+convert.format(creationDateTo)+"'"));
                } else {
                    relationBuilderHistDynamicQuery.add(RestrictionsFactoryUtil.le(ConstantsUtils.CREATED_DATE, creationDateTo));
                }

            }
            if (relationBuilderForm.getField(ConstantsUtils.DATE3).getValue() != null && relationBuilderForm.getField(ConstantsUtils.DATE4).getValue() != null) {
                creationDateFrom = (Date) relationBuilderForm.getField(ConstantsUtils.DATE3).getValue();
                creationDateTo = (Date) relationBuilderForm.getField(ConstantsUtils.DATE4).getValue();
                if (ConstantsUtils.SEARCH.equals(flag)) {
                     SimpleDateFormat convert=new SimpleDateFormat("MM/dd/YYYY");
                    relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.sqlRestriction(" CAST(CREATED_DATE AS DATE) >= '"+convert.format(creationDateFrom)+"'"+ "AND CAST(CREATED_DATE AS DATE) <= '"+convert.format(creationDateTo)+"'"));
                } else {
                    relationBuilderHistDynamicQuery.add(RestrictionsFactoryUtil.between(ConstantsUtils.CREATED_DATE, creationDateFrom, creationDateTo));
                }

            }
            if (!ConstantsUtils.SEARCH.equals(flag)) {
                relationBuilderHistDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.PRIMARY_KEY + ConstantsUtils.ACTION_FLAG, "D"));
            }
            if (filterSet != null) {
                for (Container.Filter filter : filterSet) {
                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        String filterString = "%" + stringFilter.getFilterString() + "%";
                        if (ConstantsUtils.RELATIONSHIP_TYPE.equals(stringFilter.getPropertyId())) {
                            com.stpl.app.adminconsole.util.CommonUtils commonUtils = new com.stpl.app.adminconsole.util.CommonUtils();
                            if (ConstantsUtils.SEARCH.equals(flag)) {
                                List resultList = commonUtils.getFilterValueFromHelper( filterString);
                                if (!resultList.isEmpty()) {
                                    relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.in(ConstantsUtils.RELATIONSHIP_TYPE, resultList));
                                }else{
                                    resultList.add(0);
                                     relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.in(ConstantsUtils.RELATIONSHIP_TYPE, resultList));
                                }
                            } else {
                                List resultList = commonUtils.getFilterValueFromHelper( filterString);
                                if (!resultList.isEmpty()) {
                                    relationBuilderHistDynamicQuery.add(RestrictionsFactoryUtil.in(ConstantsUtils.RELATIONSHIP_TYPE, resultList));
                                }else{
                                    resultList.add(0);
                                    relationBuilderHistDynamicQuery.add(RestrictionsFactoryUtil.in(ConstantsUtils.RELATIONSHIP_TYPE, resultList));    
                                }
                            }
                        }
                        if (ConstantsUtils.RELATIONSHIP_DESC.equals(stringFilter.getPropertyId())) {
                            if (ConstantsUtils.SEARCH.equals(flag)) {
                                relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.ilike(RELATIONSHIP_DESCRIPTION, filterString));
                            } else {
                                relationBuilderHistDynamicQuery.add(RestrictionsFactoryUtil.ilike(RELATIONSHIP_DESCRIPTION, filterString));
                            }
                        }
                        if (ConstantsUtils.RELATIONSHIP_NAME.equals(stringFilter.getPropertyId())) {
                            if (ConstantsUtils.SEARCH.equals(flag)) {
                                relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.RELATIONSHIP_NAME, filterString));
                            } else {
                                relationBuilderHistDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.RELATIONSHIP_NAME, filterString));
                            }
                        }
                       
                        if (ConstantsUtils.CREATED_BY.equals(stringFilter.getPropertyId())) {
                            if (ConstantsUtils.SEARCH.equals(flag)) {

                                List<String> resultList = CommonUtils.getCreatedBy(filterString);
                                List<Integer> list = new ArrayList<>();
                                if (!resultList.isEmpty()) {
                                    for (int i = 0; i < resultList.size(); i++) {
                                        list.add(Integer.valueOf(String.valueOf(resultList.get(i))));
                                    }
                                    relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.in(ConstantsUtils.CREATED_BY, list));
                                } else {
                                    list.add(0);
                                    relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.in(ConstantsUtils.CREATED_BY, list));
                                }
                            } else {

                                List<String> resultList = CommonUtils.getCreatedBy(filterString);
                                List<Integer> list = new ArrayList<>();
                                if (!resultList.isEmpty()) {
                                    for (int i = 0; i < resultList.size(); i++) {
                                        list.add(Integer.valueOf(String.valueOf(resultList.get(i))));
                                    }
                                    relationBuilderHistDynamicQuery.add(RestrictionsFactoryUtil.in(ConstantsUtils.CREATED_BY, list));
                                } else {
                                    list.add(0);
                                    relationBuilderHistDynamicQuery.add(RestrictionsFactoryUtil.in(ConstantsUtils.CREATED_BY, list));
                                }
                            }
                        }
                        if ("hierarchyName".equals(stringFilter.getPropertyId())) {
                            final DynamicQuery hierarchyDynamicQuery = DynamicQueryFactoryUtil.forClass(HierarchyDefinition.class);
                            final ProjectionList projList = ProjectionFactoryUtil.projectionList();
                            projList.add(ProjectionFactoryUtil.property(ConstantsUtils.HIERARCHY_DEFINATION_SID));
                            hierarchyDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.HIERARCHY_NAME, filterString));
                            hierarchyDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));
                            List resultList = dao.getHierachyDefinitionList(hierarchyDynamicQuery);
                            if (resultList.size() > 0) {
                                if (ConstantsUtils.SEARCH.equals(flag)) {
                                    relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.in(ConstantsUtils.HIERARCHY_DEFINATION_SID, resultList));
                                } else {
                                    relationBuilderHistDynamicQuery.add(RestrictionsFactoryUtil.in(ConstantsUtils.HIERARCHY_DEFINATION_SID, resultList));
                                }
                            } else {
                                if (ConstantsUtils.SEARCH.equals(flag)) {
                                    relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.HIERARCHY_DEFINATION_SID, 0));
                                } else {
                                    relationBuilderHistDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.HIERARCHY_DEFINATION_SID, 0));
                                }
                            }
                        }
                    } else if (filter instanceof Compare) {

                        Compare compare = (Compare) filter;
                        Compare.Operation operation = compare.getOperation();
                        if (ConstantsUtils.VERSION_NO.equalsIgnoreCase(String.valueOf(compare.getPropertyId()))) {
                            int value = Integer.valueOf(String.valueOf(compare.getValue()));
                            if (operation.GREATER.toString().equalsIgnoreCase(operation.name())) {
                                if (ConstantsUtils.SEARCH.equals(flag)) {
                                    relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.VERSION_NO, value));
                                } else {
                                    relationBuilderHistDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.VERSION_NO, value));
                                }
                            } else if (operation.LESS.toString().equalsIgnoreCase(operation.name())) {
                                if (ConstantsUtils.SEARCH.equals(flag)) {
                                    relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.le(ConstantsUtils.VERSION_NO, value));
                                } else {
                                    relationBuilderHistDynamicQuery.add(RestrictionsFactoryUtil.le(ConstantsUtils.VERSION_NO, value));
                                }
                            } else if (operation.EQUAL.toString().equalsIgnoreCase(operation.name())) {
                                if (ConstantsUtils.SEARCH.equals(flag)) {
                                    relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.VERSION_NO, value));
                                } else {
                                    relationBuilderHistDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.VERSION_NO, value));
                                }
                            }
                        }
                    } else if (filter instanceof Between) {

                        Between stringFilter = (Between) filter;
                        Date filterString = (Date) stringFilter.getStartValue();
                        Date filterString1 = (Date) stringFilter.getEndValue();

                        if (ConstantsUtils.CREATED_DATE.equals(stringFilter.getPropertyId())) {
                            if (ConstantsUtils.SEARCH.equals(flag)) {
                                relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.CREATED_DATE, filterString));
                                relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantsUtils.CREATED_DATE, filterString1));
                            } else {
                                relationBuilderHistDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.CREATED_DATE, filterString));
                                relationBuilderHistDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantsUtils.CREATED_DATE, filterString1));
                            }
                        }
                        if (ConstantsUtils.MODIFIED_DATE.equals(stringFilter.getPropertyId())) {
                            if (ConstantsUtils.SEARCH.equals(flag)) {
                                relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.MODIFIED_DATE, filterString));
                                relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantsUtils.MODIFIED_DATE, filterString1));
                            } else {
                                relationBuilderHistDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.MODIFIED_DATE, filterString));
                                relationBuilderHistDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantsUtils.MODIFIED_DATE, filterString1));
                            }
                        }
                        if (ConstantsUtils.START_DATE.equals(stringFilter.getPropertyId())) {
                            if (ConstantsUtils.SEARCH.equals(flag)) {
                                relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.START_DATE, filterString));
                                relationBuilderDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantsUtils.START_DATE, filterString1));
                            } else {
                                relationBuilderHistDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.START_DATE, filterString));
                                relationBuilderHistDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantsUtils.START_DATE, filterString1));
                            }
                        }
                    }
                }
            }

            loadColumnName();
            if (sortByColumns != null) {
                for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                    final SortByColumn orderByColumn = (SortByColumn) iterator.next();
                    if (ConstantsUtils.SEARCH.equals(flag)) {
                        if (orderByColumn.getType() == SortByColumn.Type.ASC) {
                            relationBuilderDynamicQuery.addOrder(OrderFactoryUtil.asc(getDBColumnName(orderByColumn.getName())));
                        } else {
                            relationBuilderDynamicQuery.addOrder(OrderFactoryUtil.desc(getDBColumnName(orderByColumn.getName())));
                        }
                    } else {
                        if (orderByColumn.getType() == SortByColumn.Type.ASC) {
                            relationBuilderHistDynamicQuery.addOrder(OrderFactoryUtil.asc(getDBColumnName(orderByColumn.getName())));
                        } else {
                            relationBuilderHistDynamicQuery.addOrder(OrderFactoryUtil.desc(getDBColumnName(orderByColumn.getName())));
                        }
                    }
                }
            }

            if (ConstantsUtils.SEARCH.equals(flag)) {
                if (!isCount) {
                    relationBuilderDynamicQuery.setLimit(startIndex, endIndex);
                }
                final List<RelationshipBuilder> resultList = dao.getRelationshipBuilderList(relationBuilderDynamicQuery);
                if (!resultList.isEmpty()) {
                    itemGroupList = getCustomizedResults(resultList);
                }
            } else {
                if (!isCount) {
                    relationBuilderHistDynamicQuery.setLimit(startIndex, endIndex);
                }
                final List<HistRelationshipBuilder> resultList = dao.getRelationshipBuilderHistoryList(relationBuilderHistDynamicQuery);
                if (!resultList.isEmpty()) {
                    itemGroupList = getCustomizedHistoryResults(resultList);

                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4011));
        }
        LOGGER.debug("getSearchResults return List<RelationshipBuilderDTO> itemGroupList=" + itemGroupList.size());
        return itemGroupList;
    }
    public static final String RELATIONSHIP_DESCRIPTION = "relationshipDescription";

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
        columnNames.put("modifiedDate", "modifiedDate");
        columnNames.put("createdBy", "createdBy");

        return columnNames;
    }

    /**
     * Gets the customized results.
     *
     * @param resultList the result list
     * @return the customized results
     */
    private List<SearchResultsDTO> getCustomizedResults(final List<RelationshipBuilder> resultList) throws SystemException, PortalException {
        LOGGER.debug("getCustomizedResults started with P1:List<RelationshipBuilder> resultList");
        final List<SearchResultsDTO> relationBuilderList = new ArrayList<>();
        final Map hierarchyInfo = CommonUtils.getHierarchyInfo();
        final HashMap<String, String> userInfoMap = (HashMap<String, String>) CommonUtil.getCreatedByUser();

        for (int i = 0; i < resultList.size(); i++) {
            String hierarchyName;
            final RelationshipBuilder relationBuilder = resultList.get(i);
            final SearchResultsDTO relationBuilderDTO = new SearchResultsDTO();
            relationBuilderDTO.setRelationshipName(relationBuilder.getRelationshipName());
            relationBuilderDTO.setRelationshipDesc(relationBuilder.getRelationshipDescription());
            relationBuilderDTO.setRelationshipType(CommonUtil.getDescriptionFromHelper(Integer.valueOf(relationBuilder.getRelationshipType())));
            relationBuilderDTO.setHierarchyVersionNo(relationBuilder.getHierarchyVersion());
            hierarchyName = String.valueOf(hierarchyInfo.get(String.valueOf(relationBuilder.getHierarchyDefinitionSid())));
            relationBuilderDTO.setVersionNo(relationBuilder.getVersionNo());
            if (hierarchyName != null && !hierarchyName.equals(ConstantsUtils.NULL)) {
                relationBuilderDTO.setHierarchyName(hierarchyName);
            }
            relationBuilderDTO.setStartDate(relationBuilder.getStartDate());

            relationBuilderDTO.setCreatedBy(userInfoMap.get(String.valueOf(relationBuilder.getCreatedBy())));
            relationBuilderDTO.setCreatedDate(relationBuilder.getCreatedDate());
            if (relationBuilder.getModifiedDate() != null && !relationBuilder.getModifiedDate().equals(ConstantsUtils.NULL)) {
                relationBuilderDTO.setModifiedDate(relationBuilder.getModifiedDate());
            }
            relationBuilderDTO.setRbSystemId(relationBuilder.getRelationshipBuilderSid());
            relationBuilderDTO.setSystemID(String.valueOf(relationBuilder.getRelationshipBuilderSid()));
            relationBuilderList.add(relationBuilderDTO);
        }
        LOGGER.debug("getCustomizedResults return List<RelationshipBuilderDTO> relationBuilderList=" + relationBuilderList.size());
        return relationBuilderList;
    }

    /**
     * Gets the customized results.
     *
     * @param resultList the result list
     * @return the customized results
     */
    private List<SearchResultsDTO> getCustomizedHistoryResults(final List<HistRelationshipBuilder> resultList) throws SystemException, PortalException {
        LOGGER.debug("getCustomizedHistoryResults started :List<RelationshipBuilder> resultList");
        final List<SearchResultsDTO> relationBuilderList = new ArrayList<>();
        final Map hierarchyInfo = CommonUtils.getHierarchyInfo();
        final HashMap<String, String> userInfoMap = (HashMap<String, String>) CommonUtil.getCreatedByUser();

        for (int i = 0; i < resultList.size(); i++) {
            String hierarchyName;
            final HistRelationshipBuilder relationBuilder = resultList.get(i);
            final SearchResultsDTO relationBuilderDTO = new SearchResultsDTO();
            relationBuilderDTO.setRelationshipName(relationBuilder.getRelationshipName());
            relationBuilderDTO.setRelationshipDesc(relationBuilder.getRelationshipDescription());
            relationBuilderDTO.setRelationshipType(CommonUtil.getDescriptionFromHelper(Integer.valueOf(relationBuilder.getRelationshipType())));
            relationBuilderDTO.setHierarchyVersionNo(relationBuilder.getHierarchyVersion());
            hierarchyName = String.valueOf(hierarchyInfo.get(String.valueOf(relationBuilder.getHierarchyDefinitionSid())));
            relationBuilderDTO.setVersionNo(relationBuilder.getVersionNo());
            if (hierarchyName != null && !hierarchyName.equals(ConstantsUtils.NULL)) {
                relationBuilderDTO.setHierarchyName(hierarchyName);
            }
            relationBuilderDTO.setStartDate(relationBuilder.getStartDate());
            relationBuilderDTO.setCreatedBy(userInfoMap.get(String.valueOf(relationBuilder.getCreatedBy())));
            relationBuilderDTO.setCreatedDate(relationBuilder.getCreatedDate());
            if (relationBuilder.getModifiedDate() != null && !relationBuilder.getModifiedDate().equals(ConstantsUtils.NULL)) {
                relationBuilderDTO.setModifiedDate(relationBuilder.getModifiedDate());
            }
            relationBuilderDTO.setRbSystemId(relationBuilder.getRelationshipBuilderSid());
            relationBuilderDTO.setSystemID(String.valueOf(relationBuilder.getRelationshipBuilderSid()));
            relationBuilderList.add(relationBuilderDTO);
        }
        LOGGER.debug("getCustomizedHistoryResults return List<RelationshipBuilderDTO> relationBuilderList=" + relationBuilderList.size());
        return relationBuilderList;
    }

    /**
     * Gets the all level values.
     *
     * @param levelsList the levels list
     * @return the all level values
     * @throws com.stpl.portal.kernel.exception.SystemException
     * @throws com.stpl.portal.kernel.exception.PortalException
     * @throws java.lang.Exception
     */
    public Map<String, List<HierarchyLevelsDTO>> getAllLevelValues(final List<HierarchyLevelDefinition> levelsList) throws SystemException, PortalException {
        LOGGER.debug("getAllLevelValues started with P1:List<HierarchyLevelDefinition> levelsList");

        final Map<String, List<HierarchyLevelsDTO>> hierarchyLevelsList = new HashMap<>();
        List<HierarchyLevelValues> levelValuesList;
        final List levelSystemIds = new ArrayList();
        HierarchyLevelsDTO levelValuesDTO;
        final HashMap levelNamesMap = new HashMap();
        final HashMap levelNosMap = new HashMap();
        final Map<Integer, String> linkedLevelNamesMap = new HashMap<>();
        final int hierarchySystemId = levelsList.get(0).getHierarchyDefinitionSid();
        final DynamicQuery hierarchyDynamicQuery = DynamicQueryFactoryUtil.forClass(HierarchyDefinition.class);
        hierarchyDynamicQuery.add(RestrictionsFactoryUtil.eq("hierarchyDefinitionSid", hierarchySystemId));
        List<HierarchyDefinition> hierarchyList;
        hierarchyList = dao.getHierachyDefinitionList(hierarchyDynamicQuery);

        final List hierarchyDTO = new ArrayList();
        hierarchyDTO.add(hierarchyList.get(0).getHierarchyType());
        final int hierCategoryCode = hierarchyList.get(0).getHierarchyCategory();
        HelperTable category;
        category = dao.getCategory(hierCategoryCode);
        hierarchyDTO.add(category.getDescription());

        for (int i = 0; i < levelsList.size(); i++) {
            final HierarchyLevelDefinition levelObj = levelsList.get(i);
            if (levelObj.getLevelValueReference().equals(USER_DEFINED_LABEL)) {
                levelSystemIds.add(levelObj.getHierarchyLevelDefinitionSid());
            } else {
                linkedLevelNamesMap.put(levelObj.getHierarchyLevelDefinitionSid(), levelObj.getTableName() + "," + levelObj.getFieldName());
            }
            levelNamesMap.put(String.valueOf(levelObj.getHierarchyLevelDefinitionSid()), String.valueOf(levelObj.getLevelName()));
            levelNosMap.put(String.valueOf(levelObj.getHierarchyLevelDefinitionSid()), String.valueOf(levelObj.getLevelNo()));
        }

        if (levelSystemIds.size() > ConstantsUtils.ZERO_NUM) {
            final DynamicQuery levelValuesDynamicQuery = DynamicQueryFactoryUtil.forClass(HierarchyLevelValues.class);
            levelValuesDynamicQuery.add(RestrictionsFactoryUtil.in("hierarchyLevelDefinitionSid", levelSystemIds));
            levelValuesDynamicQuery.addOrder(OrderFactoryUtil.asc("levelValues"));
            levelValuesList = dao.getHierarchylevelValuesList(levelValuesDynamicQuery);

            for (int i = 0; i < levelValuesList.size(); i++) {
                final HierarchyLevelValues person = levelValuesList.get(i);
                levelValuesDTO = new HierarchyLevelsDTO();
                final String key = String.valueOf(person.getHierarchyLevelDefinitionSid());
                if (hierarchyLevelsList.get(key) == null) {
                    hierarchyLevelsList.put(key, new ArrayList<HierarchyLevelsDTO>());
                }
                levelValuesDTO.setLevelValue(person.getLevelValues());
                levelValuesDTO.setHierarchyLevelSystemId(person.getHierarchyLevelDefinitionSid());
                levelValuesDTO.setParentNode("0");
                levelValuesDTO.setRelationshipLevelSystemId(Integer.valueOf("0"));
                levelValuesDTO.setLevelName(String.valueOf(levelNamesMap.get(key)));
                levelValuesDTO.setLevelNo(String.valueOf(levelNosMap.get(key).toString()));
                hierarchyLevelsList.get(key).add(levelValuesDTO);
            }
        }
        if (linkedLevelNamesMap.size() > ConstantsUtils.ZERO_NUM) {
            for (final Iterator<Entry<Integer, String>> iterator = linkedLevelNamesMap.entrySet().iterator(); iterator.hasNext();) {
                final Map.Entry<Integer, String> entry = iterator.next();
                List levelValues;
                final int levelSystemId = entry.getKey();
                final String tableFieldValue = entry.getValue();
                final String[] tableFieldValueArr = tableFieldValue.split(",");
                final String tableName = tableFieldValueArr[0];
                final String columnName = tableFieldValueArr[1];
                hierarchyDTO.add(NumericConstants.TWO, Integer.parseInt(String.valueOf(levelNosMap.get(String.valueOf(levelSystemId)))));
                levelValues = dao.getRelationshipBuilderList(tableName, columnName, hierarchyDTO);
                if (levelValues.size() > ConstantsUtils.ZERO_NUM) {
                    for (int i = 0; i < levelValues.size(); i++) {

                        levelValuesDTO = new HierarchyLevelsDTO();
                        final String key = String.valueOf(levelSystemId);
                        if (hierarchyLevelsList.get(key) == null) {
                            hierarchyLevelsList.put(key, new ArrayList<HierarchyLevelsDTO>());
                        }
                        levelValuesDTO.setLevelValue(String.valueOf(levelValues.get(i)));
                        levelValuesDTO.setParentNode("0");
                        levelValuesDTO.setRelationshipLevelSystemId(Integer.valueOf("0"));
                        levelValuesDTO.setHierarchyLevelSystemId(levelSystemId);
                        levelValuesDTO.setLevelName(String.valueOf(levelNamesMap.get(key)));
                        levelValuesDTO.setLevelNo(String.valueOf(levelNosMap.get(key)));
                        hierarchyLevelsList.get(key).add(levelValuesDTO);
                    }
                }
            }
        }
        LOGGER.debug("getAllLevelValues return Map<String, List<HierarchyLevelsDTO>> hierarchyLevelsList");
        return hierarchyLevelsList;
    }
    public static final String USER_DEFINED_LABEL = "User Defined";

    /**
     * Gets the all level values.
     *
     * @param levelsList the levels list
     * @param sessionDTO
     * @return the all level values
     * @throws com.stpl.portal.kernel.exception.SystemException
     * @throws com.stpl.portal.kernel.exception.PortalException
     * @throws java.lang.Exception
     */
    public Map<String, List<HierarchyLevelsDTO>> getHistAllLevelValues(final List<HistHierarchyLevelDefn> levelsList, final SessionDTO sessionDTO) throws SystemException, PortalException {
        LOGGER.debug("getAllLevelValues started with P1:List<HierarchyLevelDefinition> levelsList");

        final Map<String, List<HierarchyLevelsDTO>> hierarchyLevelsList = new HashMap<>();
        final int versionNo = sessionDTO.getHierarchyVersion();
        List<HistHierarchyLevelValues> levelValuesList;
        final List levelSystemIds = new ArrayList();
        HierarchyLevelsDTO levelValuesDTO;
        final HashMap levelNamesMap = new HashMap();
        final HashMap levelNosMap = new HashMap();
        final Map<Integer, String> linkedLevelNamesMap = new LinkedHashMap<>();
        final int hierarchySystemId = levelsList.get(0).getHierarchyDefinitionSid();

        final DynamicQuery hierarchyDynamicQuery = DynamicQueryFactoryUtil.forClass(HistHierarchyDefinition.class);
        hierarchyDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.PRIMARY_KEY + ConstantsUtils.HIERARCHY_DEFINATION_SID, hierarchySystemId));
        hierarchyDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.PRIMARY_KEY + ConstantsUtils.VERSION_NO, versionNo));
        List<HistHierarchyDefinition> hierarchyList;
        hierarchyList = dao.getHistHierachyDefinitionList(hierarchyDynamicQuery);

        final List hierarchyDTO = new ArrayList();
        hierarchyDTO.add(hierarchyList.get(0).getHierarchyType());
        final int hierCategoryCode = hierarchyList.get(0).getHierarchyCategory();
        final String hierarchyDefName = hierarchyList.get(0).getHierarchyName();
        Map<Integer, String> inclustionExculstionRules = BpmLogic.getInclusionExclusionRulesWithoutBPM(hierarchyDefName);

        HelperTable category;
        category = dao.getCategory(hierCategoryCode);
        hierarchyDTO.add(category.getDescription());

        for (int i = 0; i < levelsList.size(); i++) {
            final HistHierarchyLevelDefn levelObj = levelsList.get(i);
            if (levelObj.getLevelValueReference().equals(USER_DEFINED_LABEL)) {
                levelSystemIds.add(levelObj.getHierarchyLevelDefinitionSid());
            } else {
                String rules = inclustionExculstionRules.get(levelObj.getLevelNo());
                linkedLevelNamesMap.put(levelObj.getHierarchyLevelDefinitionSid(), levelObj.getTableName() + "~" + levelObj.getFieldName() + "~" + (StringUtils.isNotBlank(rules) ? rules : " "));
                BpmLogic.addTableName(levelObj.getTableName());
                BpmLogic.addColumnName(levelObj.getFieldName());
            }
            levelNamesMap.put(String.valueOf(levelObj.getHierarchyLevelDefinitionSid()), String.valueOf(levelObj.getLevelName()));
            levelNosMap.put(String.valueOf(levelObj.getHierarchyLevelDefinitionSid()), String.valueOf(levelObj.getLevelNo()));
        }

        if (levelSystemIds.size() > ConstantsUtils.ZERO_NUM) {
            final DynamicQuery levelValuesDynamicQuery = DynamicQueryFactoryUtil.forClass(HistHierarchyLevelValues.class);
            levelValuesDynamicQuery.add(RestrictionsFactoryUtil.in("hierarchyLevelDefinitionSid", levelSystemIds));
            levelValuesDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.PRIMARY_KEY + ConstantsUtils.VERSION_NO, versionNo));
            levelValuesDynamicQuery.addOrder(OrderFactoryUtil.asc("levelValues"));
            levelValuesList = dao.getHistHierarchylevelValuesList(levelValuesDynamicQuery);

            for (int i = 0; i < levelValuesList.size(); i++) {
                final HistHierarchyLevelValues person = levelValuesList.get(i);
                levelValuesDTO = new HierarchyLevelsDTO();
                final String key = String.valueOf(person.getHierarchyLevelDefinitionSid());
                if (hierarchyLevelsList.get(key) == null) {
                    hierarchyLevelsList.put(key, new ArrayList<HierarchyLevelsDTO>());
                }
                levelValuesDTO.setLevelValue(person.getLevelValues());
                levelValuesDTO.setHiddenId(String.valueOf(person.getHierarchyLevelValuesSid()));
                levelValuesDTO.setHierarchyLevelSystemId(person.getHierarchyLevelDefinitionSid());
                levelValuesDTO.setParentNode("0");
                levelValuesDTO.setRelationshipLevelSystemId(Integer.valueOf("0"));
                levelValuesDTO.setLevelName(String.valueOf(levelNamesMap.get(key)));
                levelValuesDTO.setLevelNo(String.valueOf(levelNosMap.get(key).toString()));
                hierarchyLevelsList.get(key).add(levelValuesDTO);
            }
        }
        if (linkedLevelNamesMap.size() > ConstantsUtils.ZERO_NUM) {
            Map<String, VwHelperListDto> dependenciesMap = BpmLogic.getDependenciesList();
            for (final Iterator<Entry<Integer, String>> iterator = linkedLevelNamesMap.entrySet().iterator(); iterator.hasNext();) {
                final Map.Entry<Integer, String> entry = iterator.next();
                List levelValues;
                final int levelSystemId = entry.getKey();
                final String tableFieldValue = entry.getValue();
                final String[] tableFieldValueArr = tableFieldValue.split("~");
                final String tableName = tableFieldValueArr[0];
                final String columnName = tableFieldValueArr[1];
                final String rule = tableFieldValueArr[NumericConstants.TWO];
                hierarchyDTO.add(NumericConstants.TWO, Integer.parseInt(String.valueOf(levelNosMap.get(String.valueOf(levelSystemId)))));
                hierarchyDTO.add(NumericConstants.THREE, rule);
                hierarchyDTO.add(NumericConstants.FOUR, dependenciesMap);
                String sqlString = finderImplInLogic(tableName, columnName, hierarchyDTO);
                String primaryKeyColumn =CommonUtils.getTableSystemId(tableName);
                levelValues = dao.getHistRelationshipBuilderList(sqlString, columnName, hierarchyDTO);
                if (levelValues.size() > ConstantsUtils.ZERO_NUM) {
                    for (int i = 0; i < levelValues.size(); i++) {
                        Object[] obj = (Object[]) levelValues.get(i);
                        levelValuesDTO = new HierarchyLevelsDTO();
                        final String key = String.valueOf(levelSystemId);
                        if (hierarchyLevelsList.get(key) == null) {
                            hierarchyLevelsList.put(key, new ArrayList<HierarchyLevelsDTO>());
                        }
                        levelValuesDTO.setLevelValue(String.valueOf(obj[0]));
                        levelValuesDTO.setHiddenId(String.valueOf(obj[1]));
                        levelValuesDTO.setParentNode("0");
                        levelValuesDTO.setRelationshipLevelSystemId(Integer.valueOf("0"));
                        levelValuesDTO.setHierarchyLevelSystemId(levelSystemId);
                        levelValuesDTO.setLevelName(String.valueOf(levelNamesMap.get(key)));
                        levelValuesDTO.setLevelNo(String.valueOf(levelNosMap.get(key)));
                        levelValuesDTO.setPrimaryKeyColumn(primaryKeyColumn);
                        hierarchyLevelsList.get(key).add(levelValuesDTO);
                    }
                }
            }
        }
        LOGGER.debug("getAllLevelValues return Map<String, List<HierarchyLevelsDTO>> hierarchyLevelsList");
        return hierarchyLevelsList;
    }

    String finderImplInLogic(String tableName, String columnName, List hierListValues) {


        String hierarchyType = hierListValues.get(0).toString();
        String hierarchyCategory = hierListValues.get(1).toString();
        int levelNo = Integer.valueOf(hierListValues.get(NumericConstants.TWO).toString());
        String rule = String.valueOf(hierListValues.get(NumericConstants.THREE));
        Map<String, VwHelperListDto> dependenciesMap = (HashMap<String, VwHelperListDto>) hierListValues.get(NumericConstants.FOUR);
        VwHelperListDto dto = dependenciesMap.get(tableName + "|" + columnName);
        String sqlString;
        if (dto != null) {
            String joinCondition = ConstantsUtils.EMPTY;
            String whereCondition;
            sqlString = "SELECT DISTINCT SUB." + dto.getReferenceColumnName() + ", SUB." + dto.getMappingColumnName() + " FROM " + tableName + " MAIN ";
            joinCondition += " JOIN " + dto.getReferenceTableName() + " SUB ON SUB." + dto.getMappingColumnName() + " = MAIN." + dto.getActualColumnName();
            if (StringUtils.isNotBlank(dto.getListName()) && !"null".equals(dto.getListName())) {
                joinCondition += " AND SUB.LIST_NAME='" + dto.getListName() + "'";
            }
            whereCondition = " WHERE SUB." + dto.getReferenceColumnName() + " IS NOT NULL ";

            if (!rule.equals("null") && StringUtils.isNotBlank(rule)) {

                rule = BpmLogic.buildFinalQuery(tableName, columnName, rule, "MAIN.", "SUB." + dto.getReferenceColumnName());
                whereCondition += " and " + rule + " ";
            }
            sqlString += joinCondition + whereCondition;
        } else {
            sqlString = "SELECT DISTINCT " + columnName + ", " + CommonUtils.getTableSystemId(tableName) + " FROM " + tableName + " WHERE " + columnName + " IS NOT NULL ";
            if (!rule.equals("null") && StringUtils.isNotBlank(rule)) {

                rule = BpmLogic.buildFinalQuery(tableName, columnName, rule, ConstantsUtils.EMPTY, columnName);
                sqlString += " and " + rule + " ";
            }
        }
        if (1 == levelNo && "Data Selection".equals(hierarchyCategory) && "Primary".equals(hierarchyType) && "COMPANY_MASTER".equals(tableName)) {
                sqlString += " and COMPANY_TYPE='GLCOMP'";
        }
        return sqlString;

    }

    /**
     * Check for duplicate tree.
     *
     * @param companyValue the company value
     * @param sessionDTO
     * @return true, if successful
     * @throws com.stpl.portal.kernel.exception.SystemException
     * @throws java.lang.Exception
     */
    public boolean checkForDuplicateTree(final String companyValue, final SessionDTO sessionDTO) throws SystemException {
        LOGGER.debug("checkForDuplicateTree started with P1:String companyValue=" + companyValue);
        boolean companyTreeExists;
        final DynamicQuery itemDynamicQuery = DynamicQueryFactoryUtil.forClass(RelationshipLevelDefinition.class);
        itemDynamicQuery.add(RestrictionsFactoryUtil.eq("relationshipLevelValues", companyValue));
        itemDynamicQuery.add(RestrictionsFactoryUtil.eq(LEVEL_NO, "1"));
        final List<RelationshipLevelDefinition> treeList = dao.getRelationshipLevelList(itemDynamicQuery);
        if (treeList.isEmpty()) {
            companyTreeExists = false;
        } else {
            final int selectedRelationshipId = sessionDTO.getSystemId();
            if (selectedRelationshipId == ConstantsUtils.ZERO_NUM) {
                companyTreeExists = true;
            } else {
                final Map relationMap = new HashMap();
                for (int i = 0; i < treeList.size(); i++) {
                    final RelationshipLevelDefinition levelDef = treeList.get(i);
                    if (!relationMap.containsKey(levelDef.getRelationshipBuilderSid())) {
                        relationMap.put(levelDef.getRelationshipBuilderSid(), levelDef.getRelationshipBuilderSid());
                    }
                }
                if (relationMap.size() == ConstantsUtils.ONE) {
                    companyTreeExists = false;
                } else {
                    companyTreeExists = true;
                }
            }
        }
        LOGGER.debug("checkForDuplicateTree return boolean companyTreeExists=" + companyTreeExists);
        return companyTreeExists;
    }

    /**
     * Save relationship builder.
     *
     * @param relationshipForm the relationship form
     * @param selectedLevelValues the selected level values
     *
     * @return the string
     * @throws com.stpl.portal.kernel.exception.SystemException
     * @throws com.stpl.portal.kernel.exception.PortalException
     * @throws java.lang.Exception
     */
    public List<Integer> saveRelationshipBuilder(final CustomFieldGroup relationshipForm, final List<HierarchyLevelsDTO> selectedLevelValues, final SessionDTO sessionDTO) throws SystemException, PortalException {
        LOGGER.debug("saveRelationshipBuilder started with P1:CustomFieldGroup relationshipForm and P2:List<HierarchyLevelsDTO> selectedLevelValues ");
        final List<Integer> idList = new ArrayList<>();
        RelationshipBuilder relationshipBuilder;
        final int selectedRelationshipId = sessionDTO.getSystemId();
        final int selectedHierarchyId = sessionDTO.getSelectedHierarchySessionId();
        final int userId = Integer.valueOf(sessionDTO.getUserId());
        int versionNo = 0;
        String value;
        if (selectedRelationshipId == ConstantsUtils.ZERO_NUM || ConstantsUtils.COPY.equals(sessionDTO.getMode())) {
            relationshipBuilder = RelationshipBuilderLocalServiceUtil.createRelationshipBuilder(0);
            relationshipBuilder.setRelationshipName(String.valueOf(relationshipForm.getField(ConstantsUtils.RELATIONSHIP_NAME).getValue()).trim());
            relationshipBuilder.setRelationshipDescription(String.valueOf(relationshipForm.getField(ConstantsUtils.RELATIONSHIP_DESC).getValue()).trim());
            relationshipBuilder.setRelationshipType(CommonUtil.getIDFromHelper(String.valueOf(relationshipForm.getField(ConstantsUtils.RELATIONSHIP_TYPE).getValue()),"RELATIONSHIP_TYPE"));
            relationshipBuilder.setHierarchyDefinitionSid(selectedHierarchyId);
            relationshipBuilder.setHierarchyVersion(Integer.valueOf(String.valueOf(relationshipForm.getField(ConstantsUtils.VERSION_NO).getValue())));
            relationshipBuilder.setStartDate((Date) relationshipForm.getField(ConstantsUtils.START_DATE).getValue());
            relationshipBuilder.setCreatedBy(userId);
            relationshipBuilder.setCreatedDate(new Date());
            relationshipBuilder.setModifiedDate(new Date());
            relationshipBuilder.setVersionNo(versionNo + 1);
            relationshipBuilder.setBuildType(String.valueOf(relationshipForm.getField(ConstantsUtils.BUILD_TYPE).getValue()).trim());
            relationshipBuilder = dao.addRelationshipBuilder(relationshipBuilder);

            value = saveRelationshipBuilderWindow(relationshipBuilder, selectedLevelValues, sessionDTO);
        } else {
            deleteAssociatedHierarchy(selectedRelationshipId);
            relationshipBuilder = dao.getRelationshipBuilder(selectedRelationshipId);
            versionNo = getExistingVersion(selectedRelationshipId);
            relationshipBuilder.setRelationshipName(String.valueOf(relationshipForm.getField(ConstantsUtils.RELATIONSHIP_NAME).getValue()).trim());
            relationshipBuilder.setRelationshipDescription(String.valueOf(relationshipForm.getField(ConstantsUtils.RELATIONSHIP_DESC).getValue()).trim());
            relationshipBuilder.setRelationshipType(CommonUtil.getIDFromHelper(String.valueOf(relationshipForm.getField(ConstantsUtils.RELATIONSHIP_TYPE).getValue())));
            relationshipBuilder.setHierarchyDefinitionSid(selectedHierarchyId);
            relationshipBuilder.setHierarchyVersion(Integer.valueOf(String.valueOf(relationshipForm.getField(ConstantsUtils.VERSION_NO).getValue())));
            relationshipBuilder.setStartDate((Date) relationshipForm.getField(ConstantsUtils.START_DATE).getValue());
            relationshipBuilder.setCreatedBy(relationshipBuilder.getCreatedBy());
            relationshipBuilder.setCreatedDate(relationshipBuilder.getCreatedDate());
            relationshipBuilder.setModifiedBy(userId);
            relationshipBuilder.setModifiedDate(new Date());

            relationshipBuilder.setVersionNo(versionNo + 1);
            relationshipBuilder.setBuildType(String.valueOf(relationshipForm.getField(ConstantsUtils.BUILD_TYPE).getValue()).trim());
            relationshipBuilder = dao.updateRelationshipBuilder(relationshipBuilder);

            value = saveRelationshipBuilderWindow(relationshipBuilder, selectedLevelValues, sessionDTO);
        }
        idList.add(relationshipBuilder.getRelationshipBuilderSid());
        idList.add(relationshipBuilder.getVersionNo());
        idList.add(relationshipBuilder.getHierarchyVersion());
        if (!value.equals("fail")) {
            final Notification notif = new Notification(" '" + String.valueOf(relationshipForm.getField(ConstantsUtils.RELATIONSHIP_NAME).getValue()).trim() + "' has been saved successfully", Notification.Type.HUMANIZED_MESSAGE);
            notif.setPosition(Position.BOTTOM_CENTER);
            notif.setStyleName(ConstantsUtils.MY_STYLE);
            notif.setDelayMsec(NumericConstants.THREE_THOUSAND);
            notif.show(Page.getCurrent());
        }
        LOGGER.debug("saveRelationshipBuilder return relationshipBuilder.getRelationshipName()");
        return idList;
    }

    /**
     * Method added to save the relationship builder
     *
     * @param relationshipBuilder the relationship builder dto
     * @param selectedLevelValues the selected level values list
     * @throws SystemException in case of system exception
     * @throws PortalException in case of portal exception
     * @throws Exception in case of run time exception
     */
    private String saveRelationshipBuilderWindow(final RelationshipBuilder relationshipBuilder, final List<HierarchyLevelsDTO> selectedLevelValues, SessionDTO sessionDto) throws SystemException, PortalException {
        LOGGER.debug("saveRelationshipBuilderWindow started with P1:RelationshipBuilder relationshipBuilder and P2:List<HierarchyLevelsDTO> selectedLevelValues");
        final Date date = new Date();
        final int relationBuilderSystemId = relationshipBuilder.getRelationshipBuilderSid();
        final int relationBuilderSessionId = sessionDto.getSystemId();
        final DynamicQuery itemDynamicQuery = DynamicQueryFactoryUtil.forClass(RelationshipLevelDefinition.class);
        itemDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.RELATIONSHIP_BUILDER_SYSTEM_ID, relationBuilderSessionId));
        final List<RelationshipLevelDefinition> relationshipLevelToRemove = dao.getRelationshipBuilderWindow(itemDynamicQuery);
        final HashMap savedLevelValuesMap = new HashMap();
        for (final RelationshipLevelDefinition relationLevelValue : relationshipLevelToRemove) {
            savedLevelValuesMap.put(relationLevelValue.getRelationshipLevelSid(), relationLevelValue);
        }
        for (final HierarchyLevelsDTO hierarchyLevelDTO : selectedLevelValues) {
            RelationshipLevelDefinition relationshipLevel = RelationshipLevelDefinitionLocalServiceUtil.createRelationshipLevelDefinition(0);
            relationshipLevel.setRelationshipBuilderSid(relationBuilderSystemId);
            relationshipLevel.setHierarchyLevelDefinitionSid(hierarchyLevelDTO.getHierarchyLevelSystemId());
            relationshipLevel.setRelationshipLevelValues(hierarchyLevelDTO.getHiddenId());
            relationshipLevel.setLevelNo(hierarchyLevelDTO.getLevelNo());
            relationshipLevel.setLevelName(hierarchyLevelDTO.getLevelName());
            relationshipLevel.setParentNode(hierarchyLevelDTO.getParentNode());
            String dot = hierarchyLevelDTO.getHierarchyNo().endsWith(".") ? ConstantsUtils.EMPTY : ".";
            String hierarchyNo = hierarchyLevelDTO.getHierarchyNo().contains("-") ? hierarchyLevelDTO.getHierarchyNo() + dot : relationBuilderSystemId + "-" + hierarchyLevelDTO.getHierarchyNo() + dot;
            relationshipLevel.setHierarchyNo(hierarchyNo);
            relationshipLevel.setCreatedDate(date);
            relationshipLevel.setModifiedDate(date);
            // to ensure that particular Item Group ADD or EDIT
            if (relationBuilderSessionId == ConstantsUtils.ZERO_NUM || ConstantsUtils.COPY.equals(sessionDTO.getMode())) {
                relationshipLevel.setVersionNo(relationshipBuilder.getVersionNo());
                dao.addRelationshipLevelDefinition(relationshipLevel);
            } else {// to save new Item group details info
                // To ensure that specific item already exists in Item Group or
                // not
                if (savedLevelValuesMap.containsKey(hierarchyLevelDTO.getRelationshipLevelSystemId())) {
                    relationshipLevel = (RelationshipLevelDefinition) savedLevelValuesMap.get(hierarchyLevelDTO.getRelationshipLevelSystemId());
                    relationshipLevel.setVersionNo(relationshipBuilder.getVersionNo());
                    dao.updateRelationshipLevelDefinition(relationshipLevel);
                    savedLevelValuesMap.remove(hierarchyLevelDTO.getRelationshipLevelSystemId());
                } else {// to save new Item in existing item group
                    relationshipLevel.setVersionNo(relationshipBuilder.getVersionNo());
                    dao.addRelationshipLevelDefinition(relationshipLevel);
                }
            }
        }
        LOGGER.debug("saveRelationshipBuilderWindow return success");
        return "success";
    }

    /**
     * Gets the relation builder info.
     *
     * @param sessionDTO
     * @return the relation builder info
     * @throws com.stpl.portal.kernel.exception.SystemException
     * @throws java.lang.Exception
     * @throws com.stpl.portal.kernel.exception.PortalException
     */
    public RelationshipBuilderDTO getRelationBuilderInfo(final SessionDTO sessionDTO) throws SystemException, PortalException {

        LOGGER.debug("getRelationBuilderInfo started");
        final RelationshipBuilderDTO relationBuilderDTO = new RelationshipBuilderDTO();
        final int rbSystemId = sessionDTO.getSystemId();
        final String fromViewPage = sessionDTO.getFromViewPage();
        final int versionNo = sessionDTO.getVersionNo();
        Map hierarchyInfo;
        RelationshipBuilder relationBuilder;
        List<HistRelationshipBuilder> resultList;
        if (fromViewPage.equalsIgnoreCase(String.valueOf("Yes")) || fromViewPage.equalsIgnoreCase(ConstantsUtils.EDIT)) {

            hierarchyInfo = CommonUtils.getRelationShipHierarchyInfo();
            final DynamicQuery query = DynamicQueryFactoryUtil.forClass(HistRelationshipBuilder.class);
            query.add(RestrictionsFactoryUtil.eq("primaryKey.relationshipBuilderSid", rbSystemId));
            query.add(RestrictionsFactoryUtil.eq(PRIMARY_KEYVERSION_NO, versionNo));
            resultList = dao.getHistRelationshipBuilder(query);

            for (int i = 0; i < resultList.size(); i++) {
                final HistRelationshipBuilder histRelationBuilder = resultList.get(i);
                relationBuilderDTO.setRelationshipType(CommonUtil.getDescriptionFromHelper(histRelationBuilder.getRelationshipType()));
                relationBuilderDTO.setRelationshipName(histRelationBuilder.getRelationshipName());

                relationBuilderDTO.setHierarchyVersionNo(histRelationBuilder.getHierarchyVersion());
                relationBuilderDTO.setRelationshipDesc(histRelationBuilder.getRelationshipDescription());
                relationBuilderDTO.setHierarchy(String.valueOf(histRelationBuilder.getHierarchyDefinitionSid()));
                relationBuilderDTO.setRbSystemId(rbSystemId);
                relationBuilderDTO.setStartDate(histRelationBuilder.getStartDate());
                relationBuilderDTO.setCreatedBy(histRelationBuilder.getCreatedBy());
                relationBuilderDTO.setCreatedDate(histRelationBuilder.getCreatedDate());
                relationBuilderDTO.setModifiedBy(histRelationBuilder.getModifiedBy());
                relationBuilderDTO.setModifiedDate(histRelationBuilder.getModifiedDate());
                relationBuilderDTO.setHierarchyName(String.valueOf(hierarchyInfo.get(relationBuilderDTO.getHierarchy())));
                relationBuilderDTO.setBuildType(histRelationBuilder.getBuildType());
            }

        } else {

            hierarchyInfo = CommonUtils.getHierarchyInfo();
            relationBuilder = dao.getRelationshipBuilder(rbSystemId);
            relationBuilderDTO.setRelationshipType(CommonUtil.getDescriptionFromHelper(relationBuilder.getRelationshipType()));
            relationBuilderDTO.setRelationshipName(relationBuilder.getRelationshipName());
            relationBuilderDTO.setRelationshipDesc(relationBuilder.getRelationshipDescription());

            relationBuilderDTO.setHierarchyVersionNo(relationBuilder.getHierarchyVersion());
            relationBuilderDTO.setHierarchy(String.valueOf(relationBuilder.getHierarchyDefinitionSid()));
            relationBuilderDTO.setRbSystemId(rbSystemId);
            relationBuilderDTO.setStartDate(relationBuilder.getStartDate());
            relationBuilderDTO.setCreatedBy(relationBuilder.getCreatedBy());
            relationBuilderDTO.setCreatedDate(relationBuilder.getCreatedDate());
            relationBuilderDTO.setModifiedBy(relationBuilder.getModifiedBy());
            relationBuilderDTO.setModifiedDate(relationBuilder.getModifiedDate());
            relationBuilderDTO.setHierarchyName(String.valueOf(hierarchyInfo.get(relationBuilderDTO.getHierarchy())));
        }

        LOGGER.debug("getRelationBuilderInfo return relationBuilderDTO");
        return relationBuilderDTO;

    }
    public static final String PRIMARY_KEYVERSION_NO = "primaryKey.versionNo";

    /**
     * Gets the saved level values list.
     *
     * @param levelValuesMap the level values map
     * @param levelsList the levels list
     *
     * @param sessionDTO
     * @return the saved level values list
     */
    public List<Map<String, List<HierarchyLevelsDTO>>> getSavedLevelValuesList(final Map<String, List<HierarchyLevelsDTO>> levelValuesMap, final List<HierarchyLevelDefinition> levelsList,
            final SessionDTO sessionDTO) throws SystemException {
        LOGGER.debug("getSavedLevelValuesList started with P1:Map<String, List<HierarchyLevelsDTO>> levelValuesMap and P2:List<HierarchyLevelDefinition> levelsList");
        final List<Map<String, List<HierarchyLevelsDTO>>> availSavedLevelValuesCollection = new ArrayList<>();
        List<RelationshipLevelDefinition> levelValuesList;
        final int rbSystemId = sessionDTO.getSystemId();

        HierarchyLevelsDTO levelValuesDTO;
        final Map levelNamesMap = new HashMap();
        final Map levelNosMap = new HashMap();

        for (int i = 0; i < levelsList.size(); i++) {
            final HierarchyLevelDefinition levelObj = levelsList.get(i);
            levelNamesMap.put(String.valueOf(levelObj.getHierarchyLevelDefinitionSid()), String.valueOf(levelObj.getLevelName()));
            levelNosMap.put(String.valueOf(levelObj.getHierarchyLevelDefinitionSid()), String.valueOf(levelObj.getLevelNo()));
        }
        if (rbSystemId != ConstantsUtils.ZERO_NUM) {
            final List<HierarchyLevelsDTO> finalSavedLevelValues = new ArrayList<>();
            final Map<String, List<HierarchyLevelsDTO>> finalSavedLevelValuesListMap = new HashMap<>();
            final Map<String, List<HierarchyLevelsDTO>> savedLevelValuesListMap = new HashMap<>();
            final DynamicQuery levelValuesDynamicQuery = DynamicQueryFactoryUtil.forClass(RelationshipLevelDefinition.class);
            levelValuesDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.RELATIONSHIP_BUILDER_SYSTEM_ID, rbSystemId));
            levelValuesList = dao.getRelationshipLevelList(levelValuesDynamicQuery);

            for (int i = 0; i < levelValuesList.size(); i++) {
                final RelationshipLevelDefinition person = levelValuesList.get(i);
                levelValuesDTO = new HierarchyLevelsDTO();
                final String key = String.valueOf(person.getHierarchyLevelDefinitionSid());
                if (savedLevelValuesListMap.get(key) == null) {
                    savedLevelValuesListMap.put(key, new ArrayList<HierarchyLevelsDTO>());
                }
                levelValuesDTO.setRelationshipLevelSystemId(person.getRelationshipLevelSid());
                levelValuesDTO.setLevelValue(person.getRelationshipLevelValues());
                levelValuesDTO.setParentNode(person.getParentNode());
                levelValuesDTO.setHierarchyLevelSystemId(person.getHierarchyLevelDefinitionSid());
                levelValuesDTO.setLevelName(String.valueOf(levelNamesMap.get(key)));
                levelValuesDTO.setLevelNo(String.valueOf(levelNosMap.get(key)));
                finalSavedLevelValues.add(levelValuesDTO);
                savedLevelValuesListMap.get(key).add(levelValuesDTO.getH(levelValuesDTO));
            }

            final Map<String, List<HierarchyLevelsDTO>> totalHierarchyLevelValuesMap = levelValuesMap;
            final Map<String, List<HierarchyLevelsDTO>> availableLevelValuesMap = new HashMap<>();
            for (final Iterator<Entry<String, List<HierarchyLevelsDTO>>> iterator = totalHierarchyLevelValuesMap.entrySet().iterator(); iterator.hasNext();) {
                final Map.Entry<String, List<HierarchyLevelsDTO>> entry = iterator.next();
                final List<HierarchyLevelsDTO> availableLevelValuesList = new ArrayList<>();
                List<HierarchyLevelsDTO> savedLevelValues;
                savedLevelValues = savedLevelValuesListMap.get(entry.getKey());
                if (!savedLevelValues.isEmpty()) {
                    for (int i = 0; i < savedLevelValues.size(); i++) {
                        final HierarchyLevelsDTO savedDTO = savedLevelValues.get(i);
                        savedDTO.setParentNode("0");
                        savedDTO.setRelationshipLevelSystemId(Integer.valueOf("0"));
                    }
                }
                availableLevelValuesList.addAll(entry.getValue());
                if (!savedLevelValues.isEmpty()) {
                    availableLevelValuesList.removeAll(savedLevelValues);
                }
                availableLevelValuesMap.put(entry.getKey(), availableLevelValuesList);
            }
            finalSavedLevelValuesListMap.put(String.valueOf(rbSystemId), finalSavedLevelValues);
            availSavedLevelValuesCollection.add(availableLevelValuesMap);
            availSavedLevelValuesCollection.add(savedLevelValuesListMap);
            availSavedLevelValuesCollection.add(finalSavedLevelValuesListMap);

        }
        LOGGER.debug("getSavedLevelValuesList return List<Map<String, List<HierarchyLevelsDTO>>> availSavedLevelValuesCollection");
        return availSavedLevelValuesCollection;
    }

    /**
     * Gets the saved level values list for history tables.
     *
     * @param levelValuesMap the level values map
     * @param levelsList the levels list
     *
     */
    public List<Map<String, List<HierarchyLevelsDTO>>> getSavedHistLevelValuesList(final Map<String, List<HierarchyLevelsDTO>> levelValuesMap, final List<HistHierarchyLevelDefn> levelsList,
            final SessionDTO sessionDTO) throws SystemException {
        LOGGER.debug("getSavedLevelValuesList started with P1:Map<String, List<HierarchyLevelsDTO>> levelValuesMap and P2:List<HierarchyLevelDefinition> levelsList");
        final List<Map<String, List<HierarchyLevelsDTO>>> availSavedLevelValuesCollection = new ArrayList<>();
        List<HistRelationshipLevelDefn> levelValuesList;
        final int rbSystemId = sessionDTO.getSystemId();
        final int versionNo = sessionDTO.getVersionNo();
        HierarchyLevelsDTO levelValuesDTO;
        final Map levelNamesMap = new HashMap();
        final Map levelNosMap = new HashMap();

        for (int i = 0; i < levelsList.size(); i++) {
            final HistHierarchyLevelDefn levelObj = levelsList.get(i);
            levelNamesMap.put(String.valueOf(levelObj.getHierarchyLevelDefinitionSid()), String.valueOf(levelObj.getLevelName()));
            levelNosMap.put(String.valueOf(levelObj.getHierarchyLevelDefinitionSid()), String.valueOf(levelObj.getLevelNo()));
        }
        if (rbSystemId != ConstantsUtils.ZERO_NUM) {

            try {
                final List<HierarchyLevelsDTO> finalSavedLevelValues = new ArrayList<>();
                final Map<String, List<HierarchyLevelsDTO>> finalSavedLevelValuesListMap = new HashMap<>();
                final Map<String, List<HierarchyLevelsDTO>> savedLevelValuesListMap = new HashMap<>();
                final DynamicQuery levelValuesDynamicQuery = DynamicQueryFactoryUtil.forClass(HistRelationshipLevelDefn.class);
                levelValuesDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.RELATIONSHIP_BUILDER_SYSTEM_ID, rbSystemId));
                levelValuesDynamicQuery.add(RestrictionsFactoryUtil.eq(PRIMARY_KEYVERSION_NO, versionNo));
                levelValuesDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.PRIMARY_KEY + ConstantsUtils.ACTION_FLAG, "D"));

                levelValuesList = dao.getHistRelationshipLevelList(levelValuesDynamicQuery);

                for (int i = 0; i < levelValuesList.size(); i++) {
                    
                    final HistRelationshipLevelDefn person = levelValuesList.get(i);
                    levelValuesDTO = new HierarchyLevelsDTO();
                    final String key = String.valueOf(person.getHierarchyLevelDefinitionSid());

                    if (savedLevelValuesListMap.get(key) == null) {
                        savedLevelValuesListMap.put(key, new ArrayList<HierarchyLevelsDTO>());
                    }
                    int herarchyLevelSid = person.getHierarchyLevelDefinitionSid();
                    String relationshipValue = String.valueOf(person.getRelationshipLevelValues());
                    levelValuesDTO.setHierarchyNo(person.getHierarchyNo());
                    String query1 = "select LEVEL_VALUE_REFERENCE,TABLE_NAME,FIELD_NAME from HIERARCHY_LEVEL_DEFINITION where HIERARCHY_LEVEL_DEFINITION_SID=" + herarchyLevelSid;
                    List list = HistRelationshipBuilderLocalServiceUtil.executeQuery(query1);
                    if (list != null && list.size() > 0) {
                        Object[] obj = (Object[]) list.get(0);
                        String refType = String.valueOf(obj[0]);
                        String tableName = String.valueOf(obj[1]);
                        String columnName = String.valueOf(obj[NumericConstants.TWO]);
                        if (refType.equals(USER_DEFINED_LABEL)) {
                            String query2 = "select LEVEL_VALUES,HIERARCHY_LEVEL_VALUES_SID from dbo.HIST_HIERARCHY_LEVEL_VALUES where HIERARCHY_LEVEL_VALUES_SID='" + relationshipValue + "'";
                            List listValue = HistRelationshipBuilderLocalServiceUtil.executeQuery(query2);
                            if (listValue != null && listValue.size() > 0) {
                                Object[] obje = (Object[]) listValue.get(0);
                                String hiddenId = String.valueOf(obje[1]);
                                String value = String.valueOf(obje[0]);
                                levelValuesDTO.setLevelValue(value);
                                levelValuesDTO.setHiddenId(hiddenId);
                            }
                        } else {

                            String query = "select REFERENCE_TABLE_NAME,REFERENCE_COLUMN_NAME,PRIMARY_KEY_COLUMN_NAME from dbo.vw_helper_list where ACTUAL_TABLE_NAME='" + tableName + "' and ACTUAL_COLUMN_NAME='" + columnName + "'";
                            List list1 = HistRelationshipBuilderLocalServiceUtil.executeQuery(query);

                            if (list1 != null && list1.size() > 0) {
                                Object[] obje = (Object[]) list1.get(0);
                                String viewTable = String.valueOf(obje[0]);
                                String viewColumn = String.valueOf(obje[1]);
                                String primaryColumn = String.valueOf(obje[NumericConstants.TWO]);
                                if (tableName.equals(viewTable)) {
                                    String sqlquery = "select " + viewColumn + "," + primaryColumn + " from " + viewTable + " where " + primaryColumn + "='" + relationshipValue + "'";
                                    List queryList = HistRelationshipBuilderLocalServiceUtil.executeQuery(sqlquery);
                                    if (queryList != null && queryList.size() > 0) {
                                        Object[] object = (Object[]) queryList.get(0);
                                        String value = String.valueOf(object[0]);
                                        String hiddenId = String.valueOf(object[1]);
                                        levelValuesDTO.setLevelValue(value);
                                        levelValuesDTO.setHiddenId(hiddenId);
                                        levelValuesDTO.setPrimaryKeyColumn(CommonUtils.getTableSystemId(viewTable));
                                    }
                                } else {

                                    if (viewTable.equals("HELPER_TABLE")) {
                                        String sqlString = "select HELPER_TABLE_SID,DESCRIPTION from dbo.HELPER_TABLE where HELPER_TABLE_SID='" + relationshipValue + "'";
                                        List helperList = HistRelationshipBuilderLocalServiceUtil.executeQuery(sqlString);
                                        if (helperList != null && helperList.size() > 0) {
                                            Object[] object = (Object[]) helperList.get(0);
                                            String value = String.valueOf(object[1]);
                                            String hiddenId = String.valueOf(object[0]);
                                            levelValuesDTO.setLevelValue(value);
                                            levelValuesDTO.setHiddenId(hiddenId);
                                            levelValuesDTO.setPrimaryKeyColumn(CommonUtils.getTableSystemId(viewTable));
                                        }
                                    } else {
                                        String descColumn = String.valueOf(obje[NumericConstants.SIX]);
                                        String query3 = "select " + viewColumn + "," + descColumn + " from " + viewTable + " where " + viewColumn + "='" + relationshipValue + "'";
                                        List listValue = HistRelationshipBuilderLocalServiceUtil.executeQuery(query3);
                                        if (listValue != null && listValue.size() > 0) {
                                            Object[] object = (Object[]) listValue.get(0);
                                            String value = String.valueOf(object[1]);
                                            String hiddenId = String.valueOf(object[0]);
                                            levelValuesDTO.setLevelValue(value);
                                            levelValuesDTO.setHiddenId(hiddenId);
                                            levelValuesDTO.setPrimaryKeyColumn(CommonUtils.getTableSystemId(viewTable));
                                        }
                                    }
                                }
                            }

                        }
                    }

                    levelValuesDTO.setRelationshipLevelSystemId(person.getRelationshipLevelSid());

                    levelValuesDTO.setParentNode(person.getParentNode());
                    levelValuesDTO.setHierarchyLevelSystemId(person.getHierarchyLevelDefinitionSid());
                    levelValuesDTO.setLevelName(String.valueOf(levelNamesMap.get(key)));
                    levelValuesDTO.setLevelNo(String.valueOf(levelNosMap.get(key)));
                    finalSavedLevelValues.add(levelValuesDTO);

                    savedLevelValuesListMap.get(key).add(levelValuesDTO.getH(levelValuesDTO));
                }

                final Map<String, List<HierarchyLevelsDTO>> totalHierarchyLevelValuesMap = levelValuesMap;
                final Map<String, List<HierarchyLevelsDTO>> availableLevelValuesMap = new HashMap<>();

                for (final Iterator<Entry<String, List<HierarchyLevelsDTO>>> iterator = totalHierarchyLevelValuesMap.entrySet().iterator(); iterator.hasNext();) {
                    final Map.Entry<String, List<HierarchyLevelsDTO>> entry = iterator.next();
                    final List<HierarchyLevelsDTO> availableLevelValuesList = new ArrayList<>();
                    List<HierarchyLevelsDTO> savedLevelValues;

                    savedLevelValues = savedLevelValuesListMap.get(entry.getKey());
                    if (savedLevelValues != null && !savedLevelValues.isEmpty()) {

                        for (int i = 0; i < savedLevelValues.size(); i++) {
                            final HierarchyLevelsDTO savedDTO = savedLevelValues.get(i);
                            savedDTO.setParentNode("0");
                            savedDTO.setRelationshipLevelSystemId(Integer.valueOf("0"));
                        }
                    }
                    availableLevelValuesList.addAll(entry.getValue());

                    if (savedLevelValues != null && !savedLevelValues.isEmpty()) {
                        availableLevelValuesList.removeAll(savedLevelValues);
                    }
                    availableLevelValuesMap.put(entry.getKey(), availableLevelValuesList);
                }
                finalSavedLevelValuesListMap.put(String.valueOf(rbSystemId), finalSavedLevelValues);
                availSavedLevelValuesCollection.add(availableLevelValuesMap);
                availSavedLevelValuesCollection.add(savedLevelValuesListMap);
                availSavedLevelValuesCollection.add(finalSavedLevelValuesListMap);
            } catch (Exception e) {
                LOGGER.error(e);
            }
        }
        LOGGER.debug("getSavedLevelValuesList return List<Map<String, List<HierarchyLevelsDTO>>> availSavedLevelValuesCollection");
        return availSavedLevelValuesCollection;
    }

    /**
     * Delete relationship.
     *
     * @param rbSystemId the rb system id
     * @return the string
     * @throws com.stpl.portal.kernel.exception.SystemException
     * @throws com.stpl.portal.kernel.exception.PortalException
     */
    public String deleteRelationship(final int rbSystemId) throws SystemException, PortalException {
        RelationshipBuilder relationshipBuilder;
        String deletedRelationName = StringUtils.EMPTY;
        List<RelationshipLevelDefinition> relationshipLevelValues;
        LOGGER.debug("deleteRelationship started with P1:int rbSystemId=" + rbSystemId);
        final DynamicQuery itemDetailsDynamicQuery = DynamicQueryFactoryUtil.forClass(RelationshipLevelDefinition.class);
        itemDetailsDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.RELATIONSHIP_BUILDER_SYSTEM_ID, rbSystemId));
        relationshipLevelValues = dao.getRelationshipLevelList(itemDetailsDynamicQuery);

        if (relationshipLevelValues != null && !relationshipLevelValues.isEmpty()) {
            for (int i = 0; i < relationshipLevelValues.size(); i++) {
                final RelationshipLevelDefinition levelValue = relationshipLevelValues.get(i);
                dao.deleteRelationshipLevelDefinition(levelValue);
            }
        } 
        relationshipBuilder = dao.deleteRelationshipBuilder(rbSystemId);
        deletedRelationName = relationshipBuilder.getRelationshipName();
        LOGGER.debug("deleteRelationship return String deletedRelationName=" + deletedRelationName);
        return deletedRelationName;
    }

    /**
     * It contains the existing relationship names.
     *
     * @return results
     * @throws com.stpl.portal.kernel.exception.SystemException
     */
    public Map<String,String> getExistingRelationshipNames() throws SystemException {
        LOGGER.debug("getExistingRelationshipNames Started");
        final HashMap<String,String> results = new HashMap<>();
        final DynamicQuery companyDynamicQuery = DynamicQueryFactoryUtil.forClass(RelationshipBuilder.class);
        final ProjectionList projList = ProjectionFactoryUtil.projectionList();
        projList.add(ProjectionFactoryUtil.property(ConstantsUtils.RELATIONSHIP_BUILDER_SYSTEM_ID));
        projList.add(ProjectionFactoryUtil.property(ConstantsUtils.RELATIONSHIP_NAME));
        companyDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));
        List<Object[]> resultList;
        resultList = dao.getRelationshipBuilderList(companyDynamicQuery);
        for (int i = 0; i < resultList.size(); i++) {
            final Object[] obj = resultList.get(i);
            results.put(String.valueOf(obj[0]), String.valueOf(obj[1]));
        }
        LOGGER.debug("getExistingRelationshipNames return HashMap results");
        return results;

    }

    /**
     * get count for hierarchy name
     *
     * @param filterText
     * @return
     * @throws PortalException
     * @throws SystemException
     */
    public static int getLazyHierarchyNameCount(final String filterText) throws SystemException {
        final String filterText1 = StringUtils.trimToEmpty(filterText) + "%";
        LOGGER.debug("Entering getLazyCompanyQualifierNameCount method with filterText" + filterText1);
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HierarchyDefinition.class);
        dynamicQuery.setProjection(ProjectionFactoryUtil.count(ConstantsUtils.HIERARCHY_DEFINITION_ID));
        dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.HIERARCHY_NAME, filterText1));
        dynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(ConstantsUtils.HIERARCHY_NAME, StringUtils.EMPTY)));
        dynamicQuery.add(RestrictionsFactoryUtil.isNotNull(ConstantsUtils.HIERARCHY_NAME));
        final List<Object[]> list = dao.getHierachyDefinitionList(dynamicQuery);
        return Integer.parseInt(String.valueOf(list.get(0)));
    }

    /**
     * getting results for CompanyQualifierName
     *
     * @param start
     * @param end
     * @param filteredText
     * @param filterText
     * @return
     * @throws PortalException
     * @throws SystemException
     */
    public static List<HelperDTO> getLazyHierarchyNameResults(final int start, final int end, final String filteredText) throws SystemException {
        final String filterText = StringUtils.trimToEmpty(filteredText) + "%";
        LOGGER.debug("Entering getLazyHierarchyNameResults method with filterText" + filterText);
        final List<HelperDTO> list = new ArrayList<>();

        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HierarchyDefinition.class);
        dynamicQuery.setLimit(start, end);
        final ProjectionList projectionList = ProjectionFactoryUtil.projectionList();
        projectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.HIERARCHY_DEFINITION_ID));
        projectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.HIERARCHY_NAME));
        dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projectionList));
        dynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.HIERARCHY_NAME));
        dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.HIERARCHY_NAME, filterText));
        dynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(ConstantsUtils.HIERARCHY_NAME, StringUtils.EMPTY)));
        dynamicQuery.add(RestrictionsFactoryUtil.isNotNull(ConstantsUtils.HIERARCHY_NAME));
        final List<Object[]> returnList = dao.getHierachyDefinitionList(dynamicQuery);

        HelperDTO dto;
        if (start == ConstantsUtils.ZERO_NUM) {
            dto = new HelperDTO(ConstantsUtils.SELECT_ONE);
            list.add(dto);
        }
        for (final Iterator<Object[]> iterator = returnList.iterator(); iterator.hasNext();) {
            final Object[] value = iterator.next();
            dto = new HelperDTO(StringUtils.EMPTY);
            dto.setId(value[0] == null ? 0 : Integer.valueOf(value[0].toString()));
            dto.setDescription(value[1] == null ? StringUtils.EMPTY : value[1].toString());
            if (!StringUtils.EMPTY.equals(dto.getDescription())) {
                list.add(dto);
            }
        }

        LOGGER.debug("return getLazyHierarchyNameResults size -" + list.size());
        return list;
    }

    /**
     *
     * @param selectedRelationshipId
     * @return
     * @throws SystemException
     * @throws PortalException
     * @throws Exception
     */
    public int getExistingVersion(final int selectedRelationshipId) throws SystemException {
        LOGGER.debug("getExistingItemgroupNames started");
        int version = 0;

        final DynamicQuery rBHistoryDynamicQuery = DynamicQueryFactoryUtil.forClass(HistRelationshipBuilder.class);

        rBHistoryDynamicQuery.add(RestrictionsFactoryUtil.eq("primaryKey.relationshipBuilderSid", selectedRelationshipId));

        final ProjectionList projList = ProjectionFactoryUtil.projectionList();
        projList.add(ProjectionFactoryUtil.property(PRIMARY_KEYVERSION_NO));
        rBHistoryDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));
        final List<Integer> finalList = new ArrayList<>();
        final List<Integer> historyList = dao.getRelationshipBuilderHistoryList(rBHistoryDynamicQuery);
        finalList.addAll(historyList);
        final int size = finalList.size();
        if (size > ConstantsUtils.ZERO_NUM) {
            Collections.sort(finalList);
            version = finalList.get(size - 1);
        }

        LOGGER.debug("getExistingItemgroupNames return HashMap results");
        return version;

    }

    /**
     *
     * @param selectedRelationshipId
     * @return
     * @throws SystemException
     * @throws PortalException
     * @throws Exception
     */
    public int getExistingHierarchyVersion(final int selectedRelationshipId) throws SystemException {
        LOGGER.debug("getExistingItemgroupNames started");
        int version = 0;
        final DynamicQuery rBHistoryDynamicQuery = DynamicQueryFactoryUtil.forClass(HistHierarchyDefinition.class);
        rBHistoryDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.PRIMARY_KEY + ConstantsUtils.HIERARCHY_DEFINATION_SID, selectedRelationshipId));
        final ProjectionList projList = ProjectionFactoryUtil.projectionList();
        projList.add(ProjectionFactoryUtil.property(ConstantsUtils.PRIMARY_KEY + ConstantsUtils.VERSION_NO));
        rBHistoryDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));
        final List<Integer> finalList = new ArrayList<>();
        final List<Integer> historyList = dao.getHistHierachyDefinitionList(rBHistoryDynamicQuery);
        finalList.addAll(historyList);
        final int size = finalList.size();
        if (size > ConstantsUtils.ZERO_NUM) {
            Collections.sort(finalList);
            version = finalList.get(size - 1);
        }
        LOGGER.debug("getExistingItemgroupNames return HashMap results");
        return version;
    }

    public boolean relationshipIsUsed(final int relationBuilderSId) {
        boolean check = false;
        try {
            String projQuery = "SELECT COUNT(*) FROM PROJECTION_MASTER WHERE (CUST_RELATIONSHIP_BUILDER_SID = "+relationBuilderSId+"  OR  PROD_RELATIONSHIP_BUILDER_SID = "+relationBuilderSId+") AND SAVE_FLAG = 1";
            String cffQuery = "SELECT COUNT(*) FROM CFF_MASTER WHERE  (CUST_RELATIONSHIP_BUILDER_SID = "+relationBuilderSId+" OR PROD_RELATIONSHIP_BUILDER_SID  = "+relationBuilderSId+") AND (CFF_NAME != '' OR CFF_TYPE !=0)";
            List<Object[]> projResultList = HelperTableLocalServiceUtil.executeSelectQuery(projQuery);
            List<Object[]> cffResultList = HelperTableLocalServiceUtil.executeSelectQuery(cffQuery);
            int projCount = !projResultList.isEmpty() ? Integer.valueOf(String.valueOf(projResultList.get(0))) : 0;
            int cffCount = !cffResultList.isEmpty() ? Integer.valueOf(String.valueOf(cffResultList.get(0))) : 0;
            if(projCount > 0 || cffCount > 0) {
                check = true;
            } 
        } catch (Exception e) {
            LOGGER.error(e);
            check = false;
        }
        return check;
    }
    
    public boolean relationshipIsCurentlyInUse(final int relationBuilderSId) {
        boolean check = false;
        try {
            String projQuery = "SELECT COUNT(*) FROM PROJECTION_MASTER WHERE  (CUST_RELATIONSHIP_BUILDER_SID = "+relationBuilderSId+"  OR PROD_RELATIONSHIP_BUILDER_SID = "+relationBuilderSId+") AND SAVE_FLAG = 0";
            String cffQuery = "SELECT COUNT(*) FROM CFF_MASTER WHERE (CUST_RELATIONSHIP_BUILDER_SID  = "+relationBuilderSId+" OR  PROD_RELATIONSHIP_BUILDER_SID = "+relationBuilderSId+") AND (CFF_NAME = '' OR CFF_TYPE =0)";
            List<Object[]> projResultList = HelperTableLocalServiceUtil.executeSelectQuery(projQuery);
            List<Object[]> cffResultList = HelperTableLocalServiceUtil.executeSelectQuery(cffQuery);
            int projCount = !projResultList.isEmpty() ? Integer.valueOf(String.valueOf(projResultList.get(0))) : 0;
            int cffCount = !cffResultList.isEmpty() ? Integer.valueOf(String.valueOf(cffResultList.get(0))) : 0;
            if(projCount > 0 || cffCount > 0) {
                check = true;
            } 
        } catch (Exception e) {
            LOGGER.error(e);
            check = false;
        }
        return check;
    }

    /**
     * Used to delete the save hierarchy for this RelationshipBuilder
     *
     * @param selectedRelationshipId
     */
    private void deleteAssociatedHierarchy(int selectedRelationshipId) {
        LOGGER.debug("Delete the associated Hierarchy --> " + selectedRelationshipId);
        CommonDAO commonDAO = new CommonDAOImpl();
        String query = "DELETE FROM RELATIONSHIP_LEVEL_DEFINITION WHERE RELATIONSHIP_BUILDER_SID = " + selectedRelationshipId;
        commonDAO.executeBulkUpdateQuery(query, null, null);
    }
}
