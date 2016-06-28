package com.stpl.app.adminconsole.hierarchybuilder.logic;

import com.stpl.app.adminconsole.abstractsearch.dto.SearchResultsDTO;
import com.stpl.app.adminconsole.common.dto.SessionDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

import com.stpl.app.adminconsole.common.util.CommonUtil;
import com.stpl.app.adminconsole.dao.HierarchyBuilderLogicDAO;
import com.stpl.app.adminconsole.dao.impl.HierarchyBuilderLogicDAOImpl;
import com.stpl.app.adminconsole.hierarchybuilder.dto.HierarchyBuilderDTO;
import com.stpl.app.adminconsole.hierarchybuilder.dto.HierarchyBuilderLevelDTO;
import com.stpl.app.adminconsole.hierarchybuilder.dto.LevelDTO;
import com.stpl.app.adminconsole.hierarchybuilder.dto.TableFieldLookUpDTO;
import com.stpl.app.adminconsole.util.CommonUtils;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.HierarchyDefinition;
import com.stpl.app.model.HierarchyLevelDefinition;
import com.stpl.app.model.HierarchyLevelValues;
import com.stpl.app.model.HistHierarchyDefinition;
import com.stpl.app.model.HistHierarchyLevelDefn;
import com.stpl.app.model.HistHierarchyLevelValues;
import com.stpl.app.model.ImtdLevelValues;
import com.stpl.app.model.RelationshipBuilder;
import com.stpl.app.model.VwUserTables;
import com.stpl.app.service.HierarchyDefinitionLocalServiceUtil;
import com.stpl.app.service.HierarchyLevelDefinitionLocalServiceUtil;
import com.stpl.app.service.HierarchyLevelValuesLocalServiceUtil;
import com.stpl.app.service.HistHierarchyDefinitionLocalServiceUtil;
import com.stpl.app.service.HistRelationshipBuilderLocalServiceUtil;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.service.ImtdLevelValuesLocalServiceUtil;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.ifs.ui.CustomFieldGroup;
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
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.TextField;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Set;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;

// TODO: Auto-generated Javadoc
/**
 * The Class HierarchyBuilderLogic.
 */
public class HierarchyBuilderLogic {

    /**
     * The map user.
     */
    public static Map<String, String> mapUser = new HashMap<String, String>();

    private static final Logger LOGGER = Logger.getLogger(HierarchyBuilderLogic.class);

    private static final String[] NULLOBJECT = null;

    private final HierarchyBuilderLogicDAO dao = new HierarchyBuilderLogicDAOImpl();

    CommonUtils commonUtils = new CommonUtils();

    static HashMap<String, String> columnNames = new HashMap<String, String>();

    SessionDTO sessionDTO;

   
    public static Map<String, String> getMapUser() {
        return mapUser;
    }

   
    public HierarchyBuilderLogicDAO getDao() {
        return dao;
    }

    public HierarchyBuilderLogic(SessionDTO sessionDTO) {
        this.sessionDTO = sessionDTO;
    }

    public HierarchyBuilderLogic() {

    }

    /**
     * Gets the hierachy category.
     *
     * @param listName the list name
     * @return the hierachy category
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public List<HelperDTO> getHierachyCategory(final String listName) throws SystemException, Exception {

        final DynamicQuery companyDynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);

        final List<HelperDTO> results = new ArrayList<HelperDTO>();
        final ProjectionList projList = ProjectionFactoryUtil.projectionList();
        LOGGER.info("getHierachyCategory started with P1:String listName=" + listName);
        companyDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.LIST_NAME, listName));
        projList.add(ProjectionFactoryUtil.property(ConstantsUtils.CODE));
        projList.add(ProjectionFactoryUtil.property(ConstantsUtils.DESCRIPTION));
        companyDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));
        companyDynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.DESCRIPTION));
        final List<Object[]> resultList = dao.getHierachyDefinitionList(companyDynamicQuery);

        for (final Iterator<Object[]> iterator = resultList.iterator(); iterator.hasNext();) {
            final Object[] obj = iterator.next();
            final HelperDTO dto = new HelperDTO();
            dto.setId((Integer) obj[0]);
            dto.setDescription(String.valueOf(obj[1]));
            results.add(dto);
        }
        LOGGER.info("getHierachyCategory return List<HelperDTO> results=" + results.size());
        return results;
    }

    /**
     * Gets the search results.
     *
     * @param hierarchyBuilderForm the hierarchy builder form
     * @param startIndex
     * @param endIndex
     * @param sortByColumns
     * @param filterSet
     * @param isCount
     * @return the search results
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public List<SearchResultsDTO> getSearchResults(final ErrorfulFieldGroup hierarchyBuilderForm, final int startIndex, final int endIndex, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet, boolean isCount) throws SystemException, Exception {
        LOGGER.info("getSearchResults started with P1:CustomFieldGroup hierarchyBuilderForm");
        final DynamicQuery hierarchyBuilderDynamicQuery = DynamicQueryFactoryUtil.forClass(HierarchyDefinition.class);
        final DynamicQuery hierarchyLevelDefenitionDynamicQuery = DynamicQueryFactoryUtil.forClass(HierarchyLevelDefinition.class);
        final DynamicQuery hierarchyLevelValuesDynamicQuery = DynamicQueryFactoryUtil.forClass(HierarchyLevelValues.class);

        String hierarchycate = String.valueOf(hierarchyBuilderForm.getField("combo3").getValue());
        int hierarchyCategory = 0;
        if (hierarchycate != null && !"null".equals(hierarchycate)) {
            hierarchyCategory = CommonUtil.getIDFromHelper(hierarchycate);
        }
        if (hierarchyCategory != 0) {
            hierarchyBuilderDynamicQuery.add(RestrictionsFactoryUtil.eq("hierarchyCategory", hierarchyCategory));
        }
        if (hierarchyBuilderForm.getField("text1").getValue() != null
                && StringUtils.isNotBlank(hierarchyBuilderForm.getField("text1").getValue().toString())) {
            final String hierarchyName1 = hierarchyBuilderForm.getField("text1").getValue().toString().trim();
            final String hierarchyName = hierarchyName1.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            hierarchyBuilderDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.HIERARCHY_NAME, hierarchyName));
        }
        if (hierarchyBuilderForm.getField("option1").getValue() != null
                && StringUtils.isNotBlank(hierarchyBuilderForm.getField("option1").getValue().toString())) {
            final String hierarchyType = hierarchyBuilderForm.getField("option1").getValue().toString();
            hierarchyBuilderDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.HIERARCHY_TYPE, CommonUtil.getIDFromHelper(hierarchyType)));

        }
        if (hierarchyBuilderForm.getField("date1").getValue() == null || hierarchyBuilderForm.getField("date2").getValue() == null) {

            if (hierarchyBuilderForm.getField("date1").getValue() != null) {
                final Date from = (Date) hierarchyBuilderForm.getField("date1").getValue();
                hierarchyBuilderDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.CREATED_DATE, from));

            }
            if (hierarchyBuilderForm.getField("date2").getValue() != null) {
                final Date toDate = (Date) hierarchyBuilderForm.getField("date2").getValue();
                hierarchyBuilderDynamicQuery.add(RestrictionsFactoryUtil.le(ConstantsUtils.CREATED_DATE, toDate));
            }
        } else {
            final Date from = (Date) hierarchyBuilderForm.getField("date1").getValue();
            final Date toDate = (Date) hierarchyBuilderForm.getField("date2").getValue();
            hierarchyBuilderDynamicQuery.add(RestrictionsFactoryUtil.between(ConstantsUtils.CREATED_DATE, from, toDate));
        }
        if (hierarchyBuilderForm.getField("text4").getValue() != null && StringUtils.isNotBlank(hierarchyBuilderForm.getField("text4").getValue().toString())) {
            final String levelName1 = hierarchyBuilderForm.getField("text4").getValue().toString().trim();
            final String levelName = levelName1.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            hierarchyLevelDefenitionDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.LEVEL_NAME, levelName));
            hierarchyLevelDefenitionDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property(ConstantsUtils.HIERARCHY_DEFINITION_ID)));

        }
        if (hierarchyBuilderForm.getField("text5").getValue() != null && StringUtils.isNotBlank(hierarchyBuilderForm.getField("text5").getValue().toString())) {
            final String levelValues1 = hierarchyBuilderForm.getField("text5").getValue().toString().trim();
            final String levelValues = levelValues1.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            hierarchyLevelValuesDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.LEVEL_VALUES, levelValues));
            hierarchyLevelValuesDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property(ConstantsUtils.LEVEL_SYS_ID)));

        }
        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = "%" + stringFilter.getFilterString() + "%";
                    if (ConstantsUtils.HIERARCHY_NAME.equals(stringFilter.getPropertyId())) {
                        hierarchyBuilderDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.HIERARCHY_NAME, filterString));
                    }
                    if (ConstantsUtils.HIERARCHY_TYPE.equals(stringFilter.getPropertyId())) {
                        List resultList = commonUtils.getFilterValueFromHelper("RELATIONSHIP_TYPE", filterString);
                        if (!resultList.isEmpty()) {
                            hierarchyBuilderDynamicQuery.add(RestrictionsFactoryUtil.in(ConstantsUtils.HIERARCHY_TYPE, resultList));
                        }
                    }
                    if ("hierarchyCategory".equals(stringFilter.getPropertyId())) {

                        List resultList = commonUtils.getFilterValueFromHelper("HIERARCHY_CATEGORY", filterString);
                        if (!resultList.isEmpty()) {
                            hierarchyBuilderDynamicQuery.add(RestrictionsFactoryUtil.in("hierarchyCategory", resultList));
                        }
                    }

                } else if (filter instanceof Compare) {
                    Compare compare = (Compare) filter;
                    Compare.Operation operation = compare.getOperation();
                    if (ConstantsUtils.VERSION_NO.equalsIgnoreCase(String.valueOf(compare.getPropertyId()))) {
                        int value = Integer.valueOf(String.valueOf(compare.getValue()));
                        if (operation.GREATER.toString().equalsIgnoreCase(operation.name())) {
                            hierarchyBuilderDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.VERSION_NO, value));
                        } else if (operation.LESS.toString().equalsIgnoreCase(operation.name())) {
                            hierarchyBuilderDynamicQuery.add(RestrictionsFactoryUtil.le(ConstantsUtils.VERSION_NO, value));
                        } else if (operation.EQUAL.toString().equalsIgnoreCase(operation.name())) {
                            hierarchyBuilderDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.VERSION_NO, value));
                        }
                    }
                    if ("noOfLevels".equalsIgnoreCase(String.valueOf(compare.getPropertyId()))) {
                        int value = Integer.valueOf(String.valueOf(compare.getValue()));
                        if (operation.GREATER.toString().equalsIgnoreCase(operation.name())) {
                            hierarchyBuilderDynamicQuery.add(RestrictionsFactoryUtil.ge("noOfLevels", value));
                        } else if (operation.LESS.toString().equalsIgnoreCase(operation.name())) {
                            hierarchyBuilderDynamicQuery.add(RestrictionsFactoryUtil.le("noOfLevels", value));
                        } else if (operation.EQUAL.toString().equalsIgnoreCase(operation.name())) {
                            hierarchyBuilderDynamicQuery.add(RestrictionsFactoryUtil.eq("noOfLevels", value));
                        }
                    }
                    if (compare.getValue() instanceof Date) {
                        Date filterString = (Date) compare.getValue();
                        if (operation.GREATER_OR_EQUAL.toString().equalsIgnoreCase(operation.name())) {
                            hierarchyBuilderDynamicQuery.add(RestrictionsFactoryUtil.ge(String.valueOf(compare.getPropertyId()), filterString));
                        } else if (operation.LESS_OR_EQUAL.toString().equalsIgnoreCase(operation.name())) {
                            hierarchyBuilderDynamicQuery.add(RestrictionsFactoryUtil.le(String.valueOf(compare.getPropertyId()), filterString));
                        }
                    }
                } else if (filter instanceof Between) {
                    Between stringFilter = (Between) filter;
                    Date filterString = (Date) stringFilter.getStartValue();
                    Date filterString1 = (Date) stringFilter.getEndValue();
                    if (ConstantsUtils.CREATED_DATE.equals(stringFilter.getPropertyId())) {
                        hierarchyBuilderDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.CREATED_DATE, filterString));
                        hierarchyBuilderDynamicQuery.add(RestrictionsFactoryUtil.le(ConstantsUtils.CREATED_DATE, filterString1));
                    }
                    if (ConstantsUtils.MODIFIED_DATE.equals(stringFilter.getPropertyId())) {
                        hierarchyBuilderDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.MODIFIED_DATE, filterString));
                        hierarchyBuilderDynamicQuery.add(RestrictionsFactoryUtil.le(ConstantsUtils.MODIFIED_DATE, filterString1));
                    }

                }
            }
        }
        loadColumnName();
        if (sortByColumns != null) {
            for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                final SortByColumn orderByColumn = (SortByColumn) iterator.next();

                if (orderByColumn.getType() == SortByColumn.Type.ASC) {

                    hierarchyBuilderDynamicQuery.addOrder(OrderFactoryUtil.asc(getDBColumnName(orderByColumn.getName())));
                } else {
                    hierarchyBuilderDynamicQuery.addOrder(OrderFactoryUtil.desc(getDBColumnName(orderByColumn.getName())));
                }
            }
        }
        List<SearchResultsDTO> hierarchyBuilderDTO;
        List<HierarchyDefinition> resultList;
        List<HierarchyLevelDefinition> levelDefinitionList;
        if (hierarchyBuilderForm.getField("text5").getValue() != null && StringUtils.isNotBlank(hierarchyBuilderForm.getField("text5").getValue().toString())) {
            resultList = dao.getHierachyDefinitionList(hierarchyBuilderDynamicQuery);

            final List<SearchResultsDTO> list = getCustomizedResults(resultList);
            final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HierarchyLevelDefinition.class);
            if (hierarchyBuilderForm.getField("text4").getValue() != null && StringUtils.isNotBlank(hierarchyBuilderForm.getField("text4").getValue().toString())) {
                final String levelName1 = hierarchyBuilderForm.getField("text4").getValue().toString().trim();
                final String levelName = levelName1.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
                dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.LEVEL_NAME, levelName));

            }
            levelDefinitionList = dao.getHierarchylevelDefinitionList(dynamicQuery);
            final List<HierarchyBuilderLevelDTO> levelList = getCustomizedLevelDefenition(levelDefinitionList);
            final List defList = dao.getHierarchylevelValuesList(hierarchyLevelValuesDynamicQuery);
            String[] defSys;
            if (defList == null) {
                defSys = NULLOBJECT;
            } else {
                defSys = getdefSysList(defList);
            }
            final List<HierarchyBuilderLevelDTO> levelDTOList = new ArrayList<HierarchyBuilderLevelDTO>();
            for (int i = 0; i < levelList.size(); i++) {
                for (int j = 0; j < defSys.length; j++) {
                    final HierarchyBuilderLevelDTO defObj = levelList.get(i);
                    final int sysId = Integer.valueOf(defSys[j]);
                    if (defObj.getLevelSystemId() == sysId) {
                        levelDTOList.add(defObj);
                    }
                }
            }

            hierarchyBuilderDTO = new ArrayList<SearchResultsDTO>();
            for (int k = 0; k < list.size(); k++) {
                for (int l = 0; l < levelDTOList.size(); l++) {
                    if (list.get(k).getHierarchyDefinitionSystemId() == levelDTOList.get(l).getHierarchyDefinitionSystemId()) {
                        hierarchyBuilderDTO.add(list.get(k));
                    }
                }
            }

        } else if (hierarchyBuilderForm.getField("text4").getValue() != null
                && StringUtils.isNotBlank(hierarchyBuilderForm.getField("text4").getValue().toString())) {
            resultList = dao.getHierachyDefinitionList(hierarchyBuilderDynamicQuery);
            final List<SearchResultsDTO> list = getCustomizedResults(resultList);

            final List defSysList = dao.getHierarchylevelDefinitionList(hierarchyLevelDefenitionDynamicQuery);
            String[] defSys = null;
            if (defSysList != null) {
                defSys = getdefSysList(defSysList);
            }

            hierarchyBuilderDTO = new ArrayList<SearchResultsDTO>();
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < defSys.length; j++) {
                    final SearchResultsDTO defObj = list.get(i);
                    final int sysId = Integer.valueOf(defSys[j]);
                    if (defObj.getHierarchyDefinitionSystemId() == sysId) {
                        hierarchyBuilderDTO.add(defObj);
                    }
                }
            }

        } else {
            resultList = dao.getHierachyDefinitionList(hierarchyBuilderDynamicQuery);
            hierarchyBuilderDTO = getCustomizedResults(resultList);

        }
        LOGGER.info("getSearchResults return List<HierarchyBuilderDTO> hierarchyBuilderDTO");
        return hierarchyBuilderDTO;
    }

    public static String getDBColumnName(String visibleColumnName) {
        return columnNames.get(visibleColumnName);
    }

    public static HashMap<String, String> loadColumnName() {

        columnNames.put("hierarchyName", "hierarchyName");
        columnNames.put("hierarchyType", "hierarchyType");
        columnNames.put("hierarchyCategory", "hierarchyCategory");
        columnNames.put("noOfLevels", "noOfLevels");
        columnNames.put("versionNo", "versionNo");
        columnNames.put("createdDate", "createdDate");
        columnNames.put("modifiedDate", "modifiedDate");
        columnNames.put("createdBy", "createdBy");

        return columnNames;

    }

    /**
     * Gets the history search results.
     *
     * @param hierarchyBuilderForm the hierarchy builder form
     * @param flag the flag
     * @return the history search results
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public List<SearchResultsDTO> getHistorySearchResults(final ErrorfulFieldGroup hierarchyBuilderForm, final int startIndex, final int endIndex, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet, boolean isCount) throws SystemException, Exception {
        LOGGER.info("getSearchResults started with P1:CustomFieldGroup hierarchyBuilderForm");

        final DynamicQuery histHierarchyBuilderDynamicQuery = DynamicQueryFactoryUtil.forClass(HistHierarchyDefinition.class);
        histHierarchyBuilderDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.PRIMARY_KEY + ConstantsUtils.ACTION_FLAG, "D"));
        final DynamicQuery histHierarchyLevelDefnDynamicQuery = DynamicQueryFactoryUtil.forClass(HistHierarchyLevelDefn.class);
        final DynamicQuery hierarchyLevelValuesDynamicQuery = DynamicQueryFactoryUtil.forClass(HistHierarchyLevelValues.class);
        String hierarchycate = String.valueOf(hierarchyBuilderForm.getField(ConstantsUtils.COMBO3).getValue());
        int hierarchyCategory = 0;
        if (!hierarchycate.equals("null")) {
            hierarchyCategory = CommonUtil.getIDFromHelper(hierarchycate);
        }
        if (hierarchyCategory != 0) {
            histHierarchyBuilderDynamicQuery.add(RestrictionsFactoryUtil.eq("hierarchyCategory", hierarchyCategory));
        }
        if (hierarchyBuilderForm.getField(ConstantsUtils.TEXT1).getValue() != null
                && StringUtils.isNotBlank(hierarchyBuilderForm.getField(ConstantsUtils.TEXT1).getValue().toString())) {
            final String hierarchyName1 = hierarchyBuilderForm.getField(ConstantsUtils.TEXT1).getValue().toString().trim();
            final String hierarchyName = hierarchyName1.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            histHierarchyBuilderDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.HIERARCHY_NAME, hierarchyName));
        }
        if (hierarchyBuilderForm.getField(ConstantsUtils.OPTION1).getValue() != null
                && StringUtils.isNotBlank(hierarchyBuilderForm.getField(ConstantsUtils.OPTION1).getValue().toString())) {
            final String hierarchyType = hierarchyBuilderForm.getField(ConstantsUtils.OPTION1).getValue().toString();

            histHierarchyBuilderDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.HIERARCHY_TYPE, CommonUtil.getIDFromHelper(hierarchyType)));
        }

        if (hierarchyBuilderForm.getField("date1").getValue() == null || hierarchyBuilderForm.getField("date2").getValue() == null) {

            if (hierarchyBuilderForm.getField("date1").getValue() != null) {
                final Date from = (Date) hierarchyBuilderForm.getField("date1").getValue();
                histHierarchyBuilderDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.CREATED_DATE, from));

            }
            if (hierarchyBuilderForm.getField("date2").getValue() != null) {
                final Date toDate = (Date) hierarchyBuilderForm.getField("date2").getValue();
                histHierarchyBuilderDynamicQuery.add(RestrictionsFactoryUtil.le(ConstantsUtils.CREATED_DATE, toDate));

            }

        } else {

            final Date from = (Date) hierarchyBuilderForm.getField("date1").getValue();
            final Date toDate = (Date) hierarchyBuilderForm.getField("date2").getValue();

            histHierarchyBuilderDynamicQuery.add(RestrictionsFactoryUtil.between(ConstantsUtils.CREATED_DATE, from, toDate));

        }

        if (hierarchyBuilderForm.getField("text4").getValue() != null && StringUtils.isNotBlank(hierarchyBuilderForm.getField("text4").getValue().toString())) {
            final String levelName1 = hierarchyBuilderForm.getField("text4").getValue().toString().trim();
            final String levelName = levelName1.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            histHierarchyLevelDefnDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.LEVEL_NAME, levelName));
            histHierarchyLevelDefnDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property(ConstantsUtils.HIERARCHY_DEFINITION_ID)));
        }

        if (hierarchyBuilderForm.getField("text5").getValue() != null && StringUtils.isNotBlank(hierarchyBuilderForm.getField("text5").getValue().toString())) {
            final String levelValues1 = hierarchyBuilderForm.getField("text5").getValue().toString().trim();
            final String levelValues = levelValues1.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            hierarchyLevelValuesDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.LEVEL_VALUES, levelValues));
            hierarchyLevelValuesDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property(ConstantsUtils.LEVEL_SYS_ID)));
        }

        List<SearchResultsDTO> hierarchyBuilderDTO;
        List<HistHierarchyDefinition> resultList;
        List<HierarchyLevelDefinition> levelDefinitionList;
        Object object = new Object();
        if (hierarchyBuilderForm.getField("text5").getValue() != null && StringUtils.isNotBlank(hierarchyBuilderForm.getField("text5").getValue().toString())) {
            resultList = dao.getHistHierachyDefinitionList(histHierarchyBuilderDynamicQuery);

            final List<SearchResultsDTO> list = getCustomizedHistoryResults(resultList);
            final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HierarchyLevelDefinition.class);
            if (hierarchyBuilderForm.getField("text4").getValue() != null && StringUtils.isNotBlank(hierarchyBuilderForm.getField("text4").getValue().toString())) {
                final String levelName1 = hierarchyBuilderForm.getField("text4").getValue().toString().trim();
                final String levelName = levelName1.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
                dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.LEVEL_NAME, levelName));

            }
            levelDefinitionList = dao.getHierarchylevelDefinitionList(dynamicQuery);
            final List<HierarchyBuilderLevelDTO> levelList = getCustomizedLevelDefenition(levelDefinitionList);
            final List defList = dao.getHierarchylevelValuesList(hierarchyLevelValuesDynamicQuery);
            String[] defSys;
            if (defList == null) {
                defSys = NULLOBJECT;
            } else {
                defSys = getdefSysList(defList);
            }
            final List<HierarchyBuilderLevelDTO> levelDTOList = new ArrayList<HierarchyBuilderLevelDTO>();
            for (int i = 0; i < levelList.size(); i++) {
                for (int j = 0; j < defSys.length; j++) {
                    final HierarchyBuilderLevelDTO defObj = levelList.get(i);
                    final int sysId = Integer.valueOf(defSys[j]);
                    if (defObj.getLevelSystemId() == sysId) {
                        levelDTOList.add(defObj);
                    }
                }
            }

            hierarchyBuilderDTO = new ArrayList<SearchResultsDTO>();
            for (int k = 0; k < list.size(); k++) {
                for (int l = 0; l < levelDTOList.size(); l++) {
                    if (list.get(k).getHierarchyDefinitionSystemId() == levelDTOList.get(l).getHierarchyDefinitionSystemId()) {
                        hierarchyBuilderDTO.add(list.get(k));
                    }
                }
            }

        } else if (hierarchyBuilderForm.getField("text4").getValue() != null
                && StringUtils.isNotBlank(hierarchyBuilderForm.getField("text4").getValue().toString())) {

            resultList = dao.getHierachyDefinitionList(histHierarchyBuilderDynamicQuery);
            final List<SearchResultsDTO> list = getCustomizedHistoryResults(resultList);

            final List defSysList = dao.getHistHierarchylevelDefnList(histHierarchyLevelDefnDynamicQuery);
            String[] defSys = null;
            if (defSysList != null) {
                defSys = getdefSysList(defSysList);
            }

            hierarchyBuilderDTO = new ArrayList<SearchResultsDTO>();
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < defSys.length; j++) {
                    final SearchResultsDTO defObj = list.get(i);
                    final int sysId = Integer.valueOf(defSys[j]);
                    if (defObj.getHierarchyDefinitionSystemId() == sysId) {
                        hierarchyBuilderDTO.add(defObj);
                    }
                }
            }

        } else {

            resultList = dao.getHistHierachyDefinitionList(histHierarchyBuilderDynamicQuery);
            hierarchyBuilderDTO = getCustomizedHistoryResults(resultList);

        }
        LOGGER.info("getSearchResults return List<HierarchyBuilderDTO> hierarchyBuilderDTO");
        return hierarchyBuilderDTO;

    }

    /**
     * Gets the def sys list.
     *
     * @param levelDefenitionList the level defenition list
     * @return the def sys list
     */
    private String[] getdefSysList(final List levelDefenitionList) {
        LOGGER.info("getdefSysList started with P1:List levelDefenitionList");
        final int size = levelDefenitionList.size();
        final String[] obj = new String[size];
        for (int i = 0; i < size; i++) {
            final Integer value = (Integer) levelDefenitionList.get(i);
            obj[i] = String.valueOf(value);
        }
        LOGGER.info("getdefSysList return String[] obj");
        return obj;
    }

    /**
     * Level check count.
     *
     * @param levelNo the level no
     * @return the int
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public int levelCheckCount(final int levelNo) throws SystemException, Exception {

        LOGGER.info("levelCheckCount started with P1:int levelNo=" + levelNo);
        final DynamicQuery countQuery = DynamicQueryFactoryUtil.forClass(ImtdLevelValues.class);
        countQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.LEVEL_NO, levelNo));
        final int count = dao.levelCheckCount(countQuery);
        LOGGER.info("levelCheckCount return int count=" + count);
        return count;
    }

    /**
     * Update level check count.
     *
     * @param levelSystemId the level system id
     * @param levelNo the level no
     * @return the int
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public int updateLevelCheckCount(final int levelSystemId, final int levelNo) throws SystemException, Exception {
        LOGGER.info("updateLevelCheckCount started with P1:int levelSystemId=" + levelSystemId + " and int levelNo" + levelNo);
        int updateCount;
        final DynamicQuery countQuery = DynamicQueryFactoryUtil.forClass(ImtdLevelValues.class);
        final DynamicQuery upQuery = DynamicQueryFactoryUtil.forClass(HierarchyLevelValues.class);
        countQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.LEVEL_NO, levelNo));
        if (levelSystemId > ConstantsUtils.ZERO_NUM) {
            upQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.LEVEL_SYS_ID, levelSystemId));
        }

        final int count = dao.getCount(countQuery);
        if (levelSystemId > ConstantsUtils.ZERO_NUM) {
            updateCount = dao.getCount(upQuery);
        } else {
            updateCount = 0;
        }
        final int finalCount = count + updateCount;
        LOGGER.info("updateLevelCheckCount return int finalCount=" + finalCount);
        return finalCount;
    }

    /**
     * Save hierarchy.
     *
     * @param hierarchyBuilderBinder the hierarchy builder binder
     * @param hierarchyBuilderLevelDTOBean the hierarchy builder level dto bean
     * @return the string
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public String saveHierarchy(final CustomFieldGroup hierarchyBuilderBinder, final BeanItemContainer<HierarchyBuilderLevelDTO> hierarchyBuilderLevelDTOBean, final SessionDTO sessionDTO) throws SystemException, PortalException,
            Exception {
        LOGGER.info("saveHierarchy started with P1:CustomFieldGroup hierarchyBuilderBinder and BeanItemContainer<HierarchyBuilderLevelDTO> hierarchyBuilderLevelDTOBean");
        int versionNo = 0;
        int user = Integer.valueOf(sessionDTO.getUserId());
        final List<HierarchyBuilderLevelDTO> list = new ArrayList<HierarchyBuilderLevelDTO>();
        if (hierarchyBuilderLevelDTOBean != null) {

            for (int i = 0; i < hierarchyBuilderLevelDTOBean.size(); i++) {
                final HierarchyBuilderLevelDTO obj = (HierarchyBuilderLevelDTO) hierarchyBuilderLevelDTOBean.getIdByIndex(i);
                obj.setLevelList(getLevelList(obj.getLevelNo()));
                list.add(obj);
            }
        }
        int systemId = sessionDTO.getSystemId();

        HierarchyDefinition item = HierarchyDefinitionLocalServiceUtil.createHierarchyDefinition(0);
        final int hierarchyCategory = sessionDTO.getSelectedHierarchyCategory();
        HierarchyDefinition result;
        LOGGER.info("systemId 1-====" + systemId);
        if (systemId == 0) {

            item.setHierarchyName(String.valueOf(hierarchyBuilderBinder.getField(ConstantsUtils.HIERARCHY_NAME).getValue()));

            item.setHierarchyType(CommonUtil.getIDFromHelper(String.valueOf(hierarchyBuilderBinder.getField(ConstantsUtils.HIERARCHY_TYPE).getValue() == null ? ConstantsUtils.PRIMARY : hierarchyBuilderBinder.getField(ConstantsUtils.HIERARCHY_TYPE).getValue())));
            item.setHierarchyCategory(hierarchyCategory);
            if (!list.isEmpty()) {
                item.setNoOfLevels(list.size());
            }
            item.setCreatedDate(new Date());
            item.setCreatedBy(user);
            item.setVersionNo(versionNo + 1);
            item.setModifiedDate(new Date());
            item.setModifiedBy(user);
            result = dao.addHierarchyDefinition(item);
            LOGGER.info("item======================id=================" + item.getHierarchyDefinitionSid());
            saveLevelList(result, list, sessionDTO);
        } else {
            item = dao.getHierarchyDefinition(systemId);
            item.setHierarchyName(String.valueOf(hierarchyBuilderBinder.getField(ConstantsUtils.HIERARCHY_NAME).getValue()));
            item.setHierarchyType(CommonUtil.getIDFromHelper(String.valueOf(hierarchyBuilderBinder.getField(ConstantsUtils.HIERARCHY_TYPE).getValue())));
            if (!list.isEmpty()) {
                item.setNoOfLevels(list.size());
            }
            item.setHierarchyCategory(hierarchyCategory);
            versionNo = getExistingVersion(systemId);
            item.setHierarchyDefinitionSid(systemId);
            item.setModifiedDate(new Date());
            item.setModifiedBy(user);
            item.setVersionNo(versionNo + 1);
            result = dao.updateHierarchyDefinition(item);
            saveLevelList(result, list, sessionDTO);
        }
        LOGGER.info("saveHierarchy return msg=Success");
        return ConstantsUtils.SUCCESS;
    }

    /**
     * Save level list.
     *
     * @param dto the dto
     * @param levelList the level list
     * @param sessionDTO
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public void saveLevelList(final HierarchyDefinition dto, final List<HierarchyBuilderLevelDTO> levelList, final SessionDTO sessionDTO) throws SystemException, Exception {
        LOGGER.info("saveLevelList started with P1:HierarchyDefinition dto and List<HierarchyBuilderLevelDTO> levelList size:" + levelList.size());
        int user = Integer.valueOf(sessionDTO.getUserId());
        HierarchyLevelDefinition level = null;
        HierarchyBuilderLevelDTO obj;
        HierarchyLevelDefinition levelModel;
        final List idList = new ArrayList();
        final Date date = new Date();

        try {
            for (final Iterator<HierarchyBuilderLevelDTO> iterator = levelList.iterator(); iterator.hasNext();) {
                final HierarchyBuilderLevelDTO levelDto = iterator.next();
                idList.add(levelDto.getLevelSystemId());

            }
            int systemId = sessionDTO.getSystemId();
            if (!idList.isEmpty()) {
                final DynamicQuery deleteCfpQuery = DynamicQueryFactoryUtil.forClass(HierarchyLevelDefinition.class);
                final Criterion criteria = RestrictionsFactoryUtil.in(ConstantsUtils.LEVEL_SYS_ID, idList);
                deleteCfpQuery.add(RestrictionsFactoryUtil.not(criteria));
                deleteCfpQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.HIERARCHY_DEFINITION_ID, dto.getHierarchyDefinitionSid()));
                final List<HierarchyLevelDefinition> delList = dao.getHierarchylevelDefinitionList(deleteCfpQuery);
                for (final Iterator<HierarchyLevelDefinition> it = delList.iterator(); it.hasNext();) {
                    final HierarchyLevelDefinition rsDetailsAttached = it.next();
                    dao.deleteHierarchylevelDefinition(rsDetailsAttached);
                }
            }

            for (int i = 0; i < levelList.size(); i++) {
                obj = (HierarchyBuilderLevelDTO) levelList.get(i);
                if (obj.getLevelSystemId() == 0) {
                    level = HierarchyLevelDefinitionLocalServiceUtil.createHierarchyLevelDefinition(0);
                } else {
                    level = HierarchyLevelDefinitionLocalServiceUtil.getHierarchyLevelDefinition(obj.getLevelSystemId());
                }

                level.setHierarchyDefinitionSid(dto.getHierarchyDefinitionSid());
                level.setLevelNo(obj.getLevelNo());
                level.setLevelName(obj.getLevelName());
                level.setTableName(obj.getTableName());
                level.setFieldName(obj.getFieldName());
                level.setLevelValueReference(obj.getUserDefinedOrLinked());
                if (obj.getLevelSystemId() == ConstantsUtils.ZERO_NUM) {
                    level.setCreatedBy(user);
                    level.setCreatedDate(date);
                    level.setVersionNo(dto.getVersionNo());
                    level.setModifiedBy(1);
                    level.setModifiedDate(date);
                    levelModel = dao.addHierarchylevelDefinition(level);
                } else {
                    level.setHierarchyLevelDefinitionSid(obj.getLevelSystemId());
                    if (obj.getCreatedBy() != null) {
                        level.setCreatedBy(Integer.valueOf(obj.getCreatedBy()));
                    }
                    level.setCreatedDate(obj.getCreatedDate());
                    level.setModifiedBy(user);
                    level.setModifiedDate(date);

                    level.setVersionNo(dto.getVersionNo());
                    levelModel = dao.updateHierarchylevelDefinition(level);
                }
                for (int j = 0; j < obj.getLevelList().size(); j++) {
                    obj.getLevelList().get(j).setLevelSystemId(obj.getLevelSystemId());
                }
                saveLevelValues(obj.getLevelList(), levelModel, sessionDTO);
            }
            LOGGER.info("saveLevelList Ended");
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * Save level values.
     *
     * @param levelValuesList the level values list
     * @param result the result
     * @param sessionDTO
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public void saveLevelValues(final List<LevelDTO> levelValuesList, final HierarchyLevelDefinition result, final SessionDTO sessionDTO) throws SystemException, Exception {
        final Date date = new Date();
        LOGGER.info("saveLevelValues started with P1:List<LevelDTO> levelValuesList size:" + levelValuesList.size() + "and HierarchyLevelDefinition result");
        HierarchyLevelValues objDTO;
        int user = Integer.valueOf(sessionDTO.getUserId());
        final List idList = new ArrayList();
        for (final Iterator<LevelDTO> iterator = levelValuesList.iterator(); iterator.hasNext();) {
            final LevelDTO levelDto = iterator.next();
            idList.add(levelDto.getLevelSystemId());
        }

        if (!idList.isEmpty()) {
            final DynamicQuery deleteCfpQuery = DynamicQueryFactoryUtil.forClass(HierarchyLevelValues.class);
            final Criterion criteria = RestrictionsFactoryUtil.in(ConstantsUtils.LEVEL_SYS_ID, idList);
            deleteCfpQuery.add(criteria);
            final List<HierarchyLevelValues> delList = dao.getHierarchylevelValuesList(deleteCfpQuery);
            for (final Iterator<HierarchyLevelValues> it = delList.iterator(); it.hasNext();) {
                final HierarchyLevelValues rsDetailsAttached = it.next();
                dao.deleteHierarchylevelValues(rsDetailsAttached);
            }
        }
        for (final Iterator<LevelDTO> iterator = levelValuesList.iterator(); iterator.hasNext();) {
            final LevelDTO hierarchyLevelValues = iterator.next();
            objDTO = HierarchyLevelValuesLocalServiceUtil.createHierarchyLevelValues(0);
            objDTO.setHierarchyLevelDefinitionSid(result.getHierarchyLevelDefinitionSid());
            objDTO.setLevelValues(hierarchyLevelValues.getLevelValues());
            if (hierarchyLevelValues.getLevelValueSystemId() == ConstantsUtils.ZERO_NUM) {
                objDTO.setCreatedBy(user);
                objDTO.setCreatedDate(date);
                objDTO.setVersionNo(result.getVersionNo());
                objDTO.setModifiedBy(1);
                objDTO.setModifiedDate(date);
                dao.addHierarchylevelValues(objDTO);
            } else {
                objDTO.setHierarchyLevelValuesSid(hierarchyLevelValues.getLevelValueSystemId());
                objDTO.setCreatedBy(hierarchyLevelValues.getCreatedBy());
                objDTO.setCreatedDate(hierarchyLevelValues.getCreatedDate());
                objDTO.setModifiedBy(user);
                objDTO.setModifiedDate(date);

                objDTO.setVersionNo(result.getVersionNo());
                dao.updateHierarchylevelValues(objDTO);
            }
        }
        LOGGER.info("saveLevelValues Ended");
    }

    /**
     * Gets the customized results.
     *
     * @param resultList the result list
     * @return the customized results
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    private List<SearchResultsDTO> getCustomizedResults(final List<HierarchyDefinition> resultList) throws SystemException, Exception {
        LOGGER.info("getCustomizedResults started with P1:List<HierarchyDefinition> resultList size" + resultList.size());
        final HashMap<String, String> userInfoMap = (HashMap<String, String>) CommonUtil.getCreatedByUser();
        final List<SearchResultsDTO> hierarchyDefinitionList = new ArrayList<SearchResultsDTO>();

        for (final Iterator<HierarchyDefinition> iterator = resultList.iterator(); iterator.hasNext();) {
            final HierarchyDefinition hierarchyDefinition = iterator.next();
            final SearchResultsDTO hierarchyBuilderDTO = new SearchResultsDTO();
            hierarchyBuilderDTO.setHierarchyDefinitionSystemId(hierarchyDefinition.getHierarchyDefinitionSid());
            hierarchyBuilderDTO.setHierarchyName(hierarchyDefinition.getHierarchyName());
            hierarchyBuilderDTO.setHierarchyType(CommonUtil.getDescriptionFromHelper(hierarchyDefinition.getHierarchyType()));
            hierarchyBuilderDTO.setHierarchyCategory(CommonUtil.getDescriptionFromHelper(hierarchyDefinition.getHierarchyCategory()));
            hierarchyBuilderDTO.setVersionNo(hierarchyDefinition.getVersionNo());
            if (hierarchyDefinition.getNoOfLevels() != ConstantsUtils.ZERO_NUM) {
                hierarchyBuilderDTO.setNoOfLevels(hierarchyDefinition.getNoOfLevels());
            }
            final Object createdBy = hierarchyDefinition.getCreatedBy();
            hierarchyBuilderDTO.setCreatedBy(userInfoMap.get(String.valueOf(createdBy)));
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            Date createdDate = df.parse(CommonUtils.convertDateToString(hierarchyDefinition.getCreatedDate()));
            hierarchyBuilderDTO.setCreatedDate(createdDate);
            Date modifiedDate = df.parse(CommonUtils.convertDateToString(hierarchyDefinition.getModifiedDate()));
            hierarchyBuilderDTO.setModifiedDate(modifiedDate);
            hierarchyBuilderDTO.setSystemID(String.valueOf(hierarchyBuilderDTO.getHierarchyDefinitionSystemId()));
            hierarchyDefinitionList.add(hierarchyBuilderDTO);
        }
        LOGGER.info("getCustomizedResults return List<HierarchyBuilderDTO> hierarchyDefinitionList");
        return hierarchyDefinitionList;
    }

    /**
     * Gets the customized history results.
     *
     * @param resultList the result list
     * @return the customized history results
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    private List<SearchResultsDTO> getCustomizedHistoryResults(final List<HistHierarchyDefinition> resultList) throws SystemException, Exception {
        LOGGER.info("getCustomizedResults started with P1:List<HierarchyDefinition> resultList size" + resultList.size());
        final HashMap<String, String> userInfoMap = (HashMap<String, String>) CommonUtil.getCreatedByUser();
        final List<SearchResultsDTO> hierarchyDefinitionList = new ArrayList<SearchResultsDTO>();

        for (final Iterator<HistHierarchyDefinition> iterator = resultList.iterator(); iterator.hasNext();) {
            final HistHierarchyDefinition hierarchyDefinition = iterator.next();
            final SearchResultsDTO hierarchyBuilderDTO = new SearchResultsDTO();
            hierarchyBuilderDTO.setHierarchyDefinitionSystemId(hierarchyDefinition.getHierarchyDefinitionSid());
            hierarchyBuilderDTO.setHierarchyName(hierarchyDefinition.getHierarchyName());
            hierarchyBuilderDTO.setHierarchyType(CommonUtil.getDescriptionFromHelper(hierarchyDefinition.getHierarchyType()));
            hierarchyBuilderDTO.setVersionNo(hierarchyDefinition.getVersionNo());
            hierarchyBuilderDTO.setHierarchyCategory(CommonUtil.getDescriptionFromHelper(hierarchyDefinition.getHierarchyCategory()));
            if (hierarchyDefinition.getNoOfLevels() != ConstantsUtils.ZERO_NUM) {
                hierarchyBuilderDTO.setNoOfLevels(hierarchyDefinition.getNoOfLevels());
            }
            final Object createdBy = hierarchyDefinition.getCreatedBy();

            hierarchyBuilderDTO.setCreatedBy(userInfoMap.get(String.valueOf(createdBy)));
            hierarchyBuilderDTO.setCreatedDate(hierarchyDefinition.getCreatedDate());
            hierarchyBuilderDTO.setModifiedDate(hierarchyDefinition.getModifiedDate());
            hierarchyBuilderDTO.setSystemID(String.valueOf(hierarchyBuilderDTO.getHierarchyDefinitionSystemId()));

            hierarchyDefinitionList.add(hierarchyBuilderDTO);
        }
        LOGGER.info("getCustomizedResults return List<HierarchyBuilderDTO> hierarchyDefinitionList");
        return hierarchyDefinitionList;
    }

    /**
     * Gets the customized results1.
     *
     * @param resultList the result list
     * @return the customized results1
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    private List<HierarchyBuilderDTO> getCustomizedResults1(final List<HierarchyDefinition> resultList) throws SystemException, Exception {
        LOGGER.info("getCustomizedResults started with P1:List<HierarchyDefinition> resultList size" + resultList.size());
        final HashMap<String, String> userInfoMap = (HashMap<String, String>) CommonUtil.getCreatedByUser();
        final List<HierarchyBuilderDTO> hierarchyDefinitionList = new ArrayList<HierarchyBuilderDTO>();

        for (final Iterator<HierarchyDefinition> iterator = resultList.iterator(); iterator.hasNext();) {
            final HierarchyDefinition hierarchyDefinition = iterator.next();
            final HierarchyBuilderDTO hierarchyBuilderDTO = new HierarchyBuilderDTO();
            hierarchyBuilderDTO.setHierarchyDefinitionSystemId(hierarchyDefinition.getHierarchyDefinitionSid());
            hierarchyBuilderDTO.setHierarchyName(hierarchyDefinition.getHierarchyName());
            hierarchyBuilderDTO.setHierarchyType(CommonUtil.getDescriptionFromHelper(hierarchyDefinition.getHierarchyType()));
            if (hierarchyDefinition.getNoOfLevels() != ConstantsUtils.ZERO_NUM) {
                hierarchyBuilderDTO.setNoOfLevels(String.valueOf(hierarchyDefinition.getNoOfLevels()));
            }
            final Object createdBy = hierarchyDefinition.getCreatedBy();
            hierarchyBuilderDTO.setCreatedBy(userInfoMap.get(String.valueOf(createdBy)));
            hierarchyBuilderDTO.setCreatedDate(hierarchyDefinition.getCreatedDate());
            hierarchyBuilderDTO.setModifiedDate(hierarchyDefinition.getModifiedDate());

            hierarchyDefinitionList.add(hierarchyBuilderDTO);
        }
        LOGGER.info("getCustomizedResults return List<HierarchyBuilderDTO> hierarchyDefinitionList");
        return hierarchyDefinitionList;
    }

    /**
     * Gets the customized level defenition.
     *
     * @param resultList the result list
     * @return the customized level defenition
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    private List<HierarchyBuilderLevelDTO> getCustomizedLevelDefenition(final List<HierarchyLevelDefinition> resultList) throws SystemException, Exception {
        final List<HierarchyBuilderLevelDTO> hierarchyLevelDefinitionList = new ArrayList<HierarchyBuilderLevelDTO>();
        LOGGER.info("getCustomizedLevelDefenition started with P1:List<HierarchyLevelDefinition> resultList size:" + resultList.size());

        final Map hashMap = CommonUtil.getCreatedByUser();
        for (final Iterator<HierarchyLevelDefinition> iterator = resultList.iterator(); iterator.hasNext();) {
            final HierarchyLevelDefinition hierarchyLevelDefinition = iterator.next();
            final HierarchyBuilderLevelDTO hierarchyBuilderLevelDTO = new HierarchyBuilderLevelDTO();
            hierarchyBuilderLevelDTO.setHierarchyDefinitionSystemId(hierarchyLevelDefinition.getHierarchyDefinitionSid());
            hierarchyBuilderLevelDTO.setLevelSystemId(hierarchyLevelDefinition.getHierarchyLevelDefinitionSid());
            hierarchyBuilderLevelDTO.setLevelNo(hierarchyLevelDefinition.getLevelNo());
            hierarchyBuilderLevelDTO.setLevelName(hierarchyLevelDefinition.getLevelName());
            hierarchyBuilderLevelDTO.setUserDefinedOrLinked(hierarchyLevelDefinition.getLevelValueReference());
            hierarchyBuilderLevelDTO.setTableName(hierarchyLevelDefinition.getTableName());
            hierarchyBuilderLevelDTO.setFieldName(hierarchyLevelDefinition.getFieldName());
            final Object createdBy = hashMap.get(hierarchyLevelDefinition.getCreatedBy());
            if (createdBy != null) {
                hierarchyBuilderLevelDTO.setCreatedBy(createdBy.toString());
            }
            hierarchyBuilderLevelDTO.setCreatedDate(hierarchyLevelDefinition.getCreatedDate());
            hierarchyBuilderLevelDTO.setModifiedDate(hierarchyLevelDefinition.getModifiedDate());
            hierarchyBuilderLevelDTO.setModifiedBy(String.valueOf(hierarchyLevelDefinition.getModifiedBy()));
            hierarchyLevelDefinitionList.add(hierarchyBuilderLevelDTO);
        }
        LOGGER.info("getCustomizedLevelDefenition return List<HierarchyBuilderLevelDTO> hierarchyLevelDefinitionList size:" + hierarchyLevelDefinitionList.size());
        return hierarchyLevelDefinitionList;
    }

    /**
     * Gets the customized hist level defn.
     *
     * @param resultList the result list
     * @return the customized hist level defn
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    private List<HierarchyBuilderLevelDTO> getCustomizedHistLevelDefn(final List<HistHierarchyLevelDefn> resultList) throws SystemException, Exception {
        final List<HierarchyBuilderLevelDTO> hierarchyLevelDefinitionList = new ArrayList<HierarchyBuilderLevelDTO>();
        LOGGER.info("getCustomizedLevelDefenition started with P1:List<HierarchyLevelDefinition> resultList size:" + resultList.size());

        final Map hashMap = CommonUtil.getCreatedByUser();
        for (final Iterator<HistHierarchyLevelDefn> iterator = resultList.iterator(); iterator.hasNext();) {
            final HistHierarchyLevelDefn hierarchyLevelDefinition = iterator.next();
            final HierarchyBuilderLevelDTO hierarchyBuilderLevelDTO = new HierarchyBuilderLevelDTO();
            hierarchyBuilderLevelDTO.setHierarchyDefinitionSystemId(hierarchyLevelDefinition.getHierarchyDefinitionSid());
            hierarchyBuilderLevelDTO.setLevelSystemId(hierarchyLevelDefinition.getHierarchyLevelDefinitionSid());
            hierarchyBuilderLevelDTO.setLevelNo(hierarchyLevelDefinition.getLevelNo());
            hierarchyBuilderLevelDTO.setLevelName(hierarchyLevelDefinition.getLevelName());
            hierarchyBuilderLevelDTO.setUserDefinedOrLinked(hierarchyLevelDefinition.getLevelValueReference());
            hierarchyBuilderLevelDTO.setTableName(hierarchyLevelDefinition.getTableName());
            hierarchyBuilderLevelDTO.setFieldName(hierarchyLevelDefinition.getFieldName());
            final Object createdBy = hashMap.get(hierarchyLevelDefinition.getCreatedBy());
            if (createdBy != null) {
                hierarchyBuilderLevelDTO.setCreatedBy(createdBy.toString());
            }
            hierarchyBuilderLevelDTO.setCreatedDate(hierarchyLevelDefinition.getCreatedDate());
            hierarchyBuilderLevelDTO.setModifiedDate(hierarchyLevelDefinition.getModifiedDate());
            hierarchyBuilderLevelDTO.setModifiedBy(String.valueOf(hierarchyLevelDefinition.getModifiedBy()));
            hierarchyLevelDefinitionList.add(hierarchyBuilderLevelDTO);
        }
        LOGGER.info("getCustomizedLevelDefenition return List<HierarchyBuilderLevelDTO> hierarchyLevelDefinitionList size:" + hierarchyLevelDefinitionList.size());
        return hierarchyLevelDefinitionList;
    }

    /**
     * Gets the hierarchy builder dto.
     *
     * @param systemId the system id
     * @return the hierarchy builder dto
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public HierarchyBuilderDTO gethierarchyBuilderDTO(final int systemId) throws SystemException, PortalException, Exception {
        final HierarchyBuilderDTO objDTO = new HierarchyBuilderDTO();
        LOGGER.info("gethierarchyBuilderDTO started with P1:int systemId=" + systemId);
        final HashMap<String, String> userInfoMap = (HashMap<String, String>) CommonUtil.getCreatedByUser();
        final HierarchyDefinition hierarchyDefinition = dao.getHierarchyDefinition(systemId);

        objDTO.setHierarchyDefinitionSystemId(hierarchyDefinition.getHierarchyDefinitionSid());
        objDTO.setHierarchyName(hierarchyDefinition.getHierarchyName());
        objDTO.setHierarchyType(CommonUtil.getDescriptionFromHelper(hierarchyDefinition.getHierarchyType()));
        final int hierCategoryCode = hierarchyDefinition.getHierarchyCategory();

        final HelperTable category = dao.getCategory(hierCategoryCode);
        final HelperDTO hierCate = new HelperDTO();
        hierCate.setId(hierCategoryCode);
        hierCate.setDescription(category.getDescription());
        objDTO.setHierarchyCategory(hierCate);
        if (hierarchyDefinition.getNoOfLevels() != ConstantsUtils.ZERO_NUM) {
            objDTO.setNoOfLevels(String.valueOf(hierarchyDefinition.getNoOfLevels()));
        }
        objDTO.setCreatedBy(userInfoMap.get(String.valueOf(hierarchyDefinition.getCreatedBy())));
        objDTO.setCreatedDate(hierarchyDefinition.getCreatedDate());
        objDTO.setModifiedBy(hierarchyDefinition.getModifiedBy());
        objDTO.setModifiedDate(hierarchyDefinition.getModifiedDate());

        LOGGER.info("gethierarchyBuilderDTO return HierarchyBuilderDTO objDTO");
        return objDTO;
    }

    /**
     * Gets the hist hierarchy builder dto.
     *
     * @param systemId the system id
     * @param version the version
     * @return the hist hierarchy builder dto
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public HierarchyBuilderDTO getHistHierarchyBuilderDTO(final int systemId, final int version) throws SystemException, PortalException, Exception {
        final HierarchyBuilderDTO objDTO = new HierarchyBuilderDTO();
        LOGGER.info("gethierarchyBuilderDTO started with P1:int systemId=" + systemId);
        final HashMap<String, String> userInfoMap = (HashMap<String, String>) CommonUtil.getCreatedByUser();
        final DynamicQuery hierarchydefinitionQuery = DynamicQueryFactoryUtil.forClass(HistHierarchyDefinition.class);
        hierarchydefinitionQuery.add(RestrictionsFactoryUtil.eq("primaryKey.hierarchyDefinitionSid", systemId));
        hierarchydefinitionQuery.add(RestrictionsFactoryUtil.eq("primaryKey.versionNo", version));
        hierarchydefinitionQuery.add(RestrictionsFactoryUtil.ne("primaryKey.actionFlag", "D"));
        final List<HistHierarchyDefinition> resultsList = HistHierarchyDefinitionLocalServiceUtil.dynamicQuery(hierarchydefinitionQuery);
        for (final Iterator<HistHierarchyDefinition> iterator = resultsList.iterator(); iterator.hasNext();) {
            final HistHierarchyDefinition hierarchyDefinition = iterator.next();
            objDTO.setHierarchyDefinitionSystemId(hierarchyDefinition.getHierarchyDefinitionSid());
            objDTO.setHierarchyName(hierarchyDefinition.getHierarchyName());
            objDTO.setHierarchyType(CommonUtil.getDescriptionFromHelper(hierarchyDefinition.getHierarchyType()));
            final int hierCategoryCode = hierarchyDefinition.getHierarchyCategory();
            final HelperTable category = dao.getCategory(hierCategoryCode);
            final HelperDTO hierCate = new HelperDTO();
            hierCate.setId(hierCategoryCode);
            hierCate.setDescription(category.getDescription());
            objDTO.setHierarchyCategory(hierCate);
            if (hierarchyDefinition.getNoOfLevels() != ConstantsUtils.ZERO_NUM) {
                objDTO.setNoOfLevels(String.valueOf(hierarchyDefinition.getNoOfLevels()));
            }
            objDTO.setCreatedBy(userInfoMap.get(String.valueOf(hierarchyDefinition.getCreatedBy())));
            objDTO.setCreatedDate(hierarchyDefinition.getCreatedDate());
            objDTO.setModifiedBy(hierarchyDefinition.getModifiedBy());
            objDTO.setModifiedDate(hierarchyDefinition.getModifiedDate());
        }
        LOGGER.info("gethierarchyBuilderDTO return HierarchyBuilderDTO objDTO");
        return objDTO;
    }

    /**
     * Gets the hierarchy builder level dto.
     *
     * @param systemId the system id
     * @return the hierarchy builder level dto
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public HierarchyBuilderLevelDTO getHierarchyBuilderLevelDTO(final int systemId) throws SystemException, PortalException {
        final HierarchyBuilderLevelDTO objDTO = new HierarchyBuilderLevelDTO();
        LOGGER.info("getHierarchyBuilderLevelDTO started with P1:int systemId=" + systemId);

        final HierarchyLevelDefinition hierarchyLevelDefinition = dao.getHierarchylevelDefinition(systemId);

        objDTO.setLevelSystemId(hierarchyLevelDefinition.getHierarchyLevelDefinitionSid());
        objDTO.setHierarchyDefinitionSystemId(hierarchyLevelDefinition.getHierarchyDefinitionSid());
        objDTO.setLevelNo(hierarchyLevelDefinition.getLevelNo());
        objDTO.setLevelName(hierarchyLevelDefinition.getLevelName());
        objDTO.setUserDefinedOrLinked(hierarchyLevelDefinition.getLevelValueReference());
        objDTO.setTableName(hierarchyLevelDefinition.getTableName());
        objDTO.setFieldName(hierarchyLevelDefinition.getFieldName());
        objDTO.setCreatedBy(String.valueOf(hierarchyLevelDefinition.getCreatedBy()));
        objDTO.setCreatedDate(hierarchyLevelDefinition.getCreatedDate());
        objDTO.setModifiedBy(String.valueOf(hierarchyLevelDefinition.getModifiedBy()));
        objDTO.setModifiedDate(hierarchyLevelDefinition.getModifiedDate());
        LOGGER.info("getHierarchyBuilderLevelDTO return HierarchyBuilderLevelDTO objDTO");
        return objDTO;
    }

    /**
     * Gets the hierarchy level dto by definition system id.
     *
     * @param systemId the system id
     * @return the hierarchy level dto by definition system id
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public List<HierarchyBuilderLevelDTO> getHierarchyLevelDTOByDefinitionSystemId(final int systemId) throws SystemException, Exception {
        LOGGER.info("getHierarchyLevelDTOByDefinitionSystemId started with P1:int systemId=" + systemId);
        final List<HierarchyBuilderLevelDTO> finalList = new ArrayList<HierarchyBuilderLevelDTO>();
        final DynamicQuery hierarchyLevelDefenitionDynamicQuery = DynamicQueryFactoryUtil.forClass(HierarchyLevelDefinition.class);
        hierarchyLevelDefenitionDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.HIERARCHY_DEFINITION_ID, systemId));
        hierarchyLevelDefenitionDynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.LEVEL_NO));

        final List<HierarchyLevelDefinition> levelDefenitionList = dao.getHierarchylevelDefinitionList(hierarchyLevelDefenitionDynamicQuery);
        final List<HierarchyBuilderLevelDTO> resultList = getCustomizedLevelDefenition(levelDefenitionList);
        for (final Iterator<HierarchyBuilderLevelDTO> iterator = resultList.iterator(); iterator.hasNext();) {
            final HierarchyBuilderLevelDTO dto = iterator.next();
            dto.setLevelList(getLevels(dto.getLevelSystemId()));
            finalList.add(dto);
        }
        LOGGER.info("getHierarchyLevelDTOByDefinitionSystemId return List<HierarchyBuilderLevelDTO> size:" + finalList.size());
        return finalList;
    }

    /**
     * Gets the hist hierarchy level dto.
     *
     * @param systemId the system id
     * @param version the version
     * @return the hist hierarchy level dto
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public List<HierarchyBuilderLevelDTO> getHistHierarchyLevelDTO(final int systemId, final int version) throws SystemException, Exception {
        LOGGER.info("getHistHierarchyLevelDTO started with P1:int systemId=" + systemId + " version=" + version);
        final List<HierarchyBuilderLevelDTO> finalList = new ArrayList<HierarchyBuilderLevelDTO>();
        final DynamicQuery HistHierarchyLevelDefenDynamicQuery = DynamicQueryFactoryUtil.forClass(HistHierarchyLevelDefn.class);
        HistHierarchyLevelDefenDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.HIERARCHY_DEFINITION_ID, systemId));
        HistHierarchyLevelDefenDynamicQuery.add(RestrictionsFactoryUtil.eq("primaryKey.versionNo", version));
        HistHierarchyLevelDefenDynamicQuery.add(RestrictionsFactoryUtil.ne("primaryKey.actionFlag", "D"));
        HistHierarchyLevelDefenDynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.LEVEL_NO));

        final List<HistHierarchyLevelDefn> levelDefenitionList = dao.getHistHierarchylevelDefnList(HistHierarchyLevelDefenDynamicQuery);
        final List<HierarchyBuilderLevelDTO> resultList = getCustomizedHistLevelDefn(levelDefenitionList);
        for (final Iterator<HierarchyBuilderLevelDTO> iterator = resultList.iterator(); iterator.hasNext();) {
            final HierarchyBuilderLevelDTO dto = iterator.next();
            dto.setLevelList(getLevels(dto.getLevelSystemId()));
            finalList.add(dto);
        }
        LOGGER.info("getHierarchyLevelDTOByDefinitionSystemId return List<HierarchyBuilderLevelDTO> size:" + finalList.size());
        return finalList;
    }

    /**
     * Gets the levels.
     *
     * @param systemId the system id
     * @return the levels
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public List<LevelDTO> getLevels(final int systemId) throws SystemException, Exception {
        final List<LevelDTO> list = new ArrayList<LevelDTO>();
        LevelDTO objDTO;
        LOGGER.info("getLevels started with P1:int systemId=" + systemId);
        final DynamicQuery hierarchyLevelValuesDynamicQuery = DynamicQueryFactoryUtil.forClass(HierarchyLevelValues.class);
        hierarchyLevelValuesDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.LEVEL_SYS_ID, systemId));

        final List<HierarchyLevelValues> resultList = dao.getHierarchylevelValuesList(hierarchyLevelValuesDynamicQuery);

        for (final Iterator<HierarchyLevelValues> iterator = resultList.iterator(); iterator.hasNext();) {
            final HierarchyLevelValues hierarchyLevelValues = iterator.next();
            objDTO = new LevelDTO();
            objDTO.setLevelValueSystemId(hierarchyLevelValues.getHierarchyLevelValuesSid());
            objDTO.setLevelSystemId(hierarchyLevelValues.getHierarchyLevelDefinitionSid());
            objDTO.setLevelValues(hierarchyLevelValues.getLevelValues());
            objDTO.setCreatedBy(hierarchyLevelValues.getCreatedBy());
            objDTO.setCreatedDate(hierarchyLevelValues.getCreatedDate());
            objDTO.setModifiedBy(hierarchyLevelValues.getModifiedBy());
            objDTO.setModifiedDate(hierarchyLevelValues.getModifiedDate());
            list.add(objDTO);
        }
        LOGGER.info("getLevels return List<LevelDTO> size:" + list.size());
        return list;
    }

    /**
     * Delete hierarchy.
     *
     * @param systemId the system id
     * @return the string
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public String deleteHierarchy(final int systemId) throws SystemException, PortalException, Exception {
        LOGGER.info("deleteHierarchy started with P1:int id=" + systemId);

        final DynamicQuery rbDynamicQuery = DynamicQueryFactoryUtil.forClass(RelationshipBuilder.class);
        rbDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.HIERARCHY_DEFINITION_ID, systemId));
        final List<RelationshipBuilder> rblist = dao.getRelationshipBuilderList(rbDynamicQuery);
        if (rblist.isEmpty()) {
            final DynamicQuery hierarchyDynamicQuery = DynamicQueryFactoryUtil.forClass(HierarchyDefinition.class);
            hierarchyDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.HIERARCHY_DEFINITION_ID, systemId));
            final List<HierarchyDefinition> list = dao.getHierarchyDefinitionList(hierarchyDynamicQuery);
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    final DynamicQuery hierarchyLevelDynamicQuery = DynamicQueryFactoryUtil.forClass(HierarchyLevelDefinition.class);
                    hierarchyLevelDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.HIERARCHY_DEFINITION_ID, systemId));
                    final List<HierarchyLevelDefinition> hierarchyLevel = dao.getHierarchyDefinitionLevelList(hierarchyLevelDynamicQuery);
                    for (int j = 0; j < hierarchyLevel.size(); j++) {
                        final DynamicQuery hierarchyLevelValuesDynamicQuery = DynamicQueryFactoryUtil.forClass(HierarchyLevelValues.class);
                        hierarchyLevelValuesDynamicQuery.add(RestrictionsFactoryUtil.like("hierarchyLevelDefinitionSid", hierarchyLevel.get(j).getHierarchyLevelDefinitionSid()));
                        final List<HierarchyLevelValues> hierarchyLevelValues = dao.getHierarchyDefinitionLevelValueList(hierarchyLevelValuesDynamicQuery);
                        for (int k = 0; k < hierarchyLevelValues.size(); k++) {
                            HierarchyLevelValuesLocalServiceUtil.deleteHierarchyLevelValues(hierarchyLevelValues.get(k));
                        }
                        HierarchyLevelDefinitionLocalServiceUtil.deleteHierarchyLevelDefinition(hierarchyLevel.get(j));
                    }
                    HierarchyDefinitionLocalServiceUtil.deleteHierarchyDefinition(list.get(i));
                }

            }

            LOGGER.info("deleteHierarchy return String deletedHrName=");
            return ConstantsUtils.SUCCESS;

        } else {
            return ConstantsUtils.FAILURE;
        }

    }

    /**
     * Delete hierarchy level values.
     *
     * @param levelSystemId the level system id
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public void deleteHierarchyLevelValues(final int levelSystemId) throws SystemException, PortalException, Exception {
        LOGGER.info("deleteHierarchyLevelValues started with P1:int levelSystemId=" + levelSystemId);

        final DynamicQuery hierarchyLevelValuesDynamicQuery = DynamicQueryFactoryUtil.forClass(HierarchyLevelValues.class);
        hierarchyLevelValuesDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.LEVEL_SYS_ID, levelSystemId));

        final List<HierarchyLevelValues> resultList = dao.getHierarchylevelValuesList(hierarchyLevelValuesDynamicQuery);
        if (resultList != null) {
            for (final Iterator<HierarchyLevelValues> iterator = resultList.iterator(); iterator.hasNext();) {
                final HierarchyLevelValues values = iterator.next();
                dao.deleteHierarchylevelValues(values.getHierarchyLevelValuesSid());
            }
        }

        LOGGER.info("deleteHierarchyLevelValues ended");

    }

    /**
     * Gets the table and field.
     *
     * @param tableName the table name
     * @param fieldName the field name
     * @return the table and field
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public List<TableFieldLookUpDTO> getTableAndField(final TextField tableName, final TextField fieldName) throws SystemException, Exception {
        LOGGER.info("getTableAndField started with P1:TextField tableName and P2:TextField fieldName");
        final List<TableFieldLookUpDTO> lookUpList = new ArrayList<TableFieldLookUpDTO>();
        final DynamicQuery vwUserTablesDynamicQuery = DynamicQueryFactoryUtil.forClass(VwUserTables.class);
        TableFieldLookUpDTO lookUp;

        if (tableName.getValue() != null) {
            final String table1 = tableName.getValue().trim();
            final String table = table1.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            vwUserTablesDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.TABLE_NAME, table));
        }

        if (fieldName.getValue() != null) {
            final String field1 = fieldName.getValue().trim();
            final String field = field1.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            vwUserTablesDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.COLUMN_NAME, field));
        }

        final List<VwUserTables> list = dao.getLookUpData(vwUserTablesDynamicQuery);
        for (final Iterator<VwUserTables> iterator = list.iterator(); iterator.hasNext();) {
            final VwUserTables dto = iterator.next();
            lookUp = new TableFieldLookUpDTO();
            lookUp.setUniqueId(dto.getUniqueId());
            lookUp.setTableName(dto.getTableName());
            lookUp.setFieldName(dto.getColumnName());
            lookUpList.add(lookUp);
        }
        LOGGER.info("getTableAndField return List<TableFieldLookUpDTO> size:" + lookUpList.size());
        return lookUpList;
    }

    /**
     * Gets the table name.
     *
     * @param tableName the table name
     * @return the table name
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public List<TableFieldLookUpDTO> getTableName(final String tableName, int startIndex, int offset, Set<Container.Filter> filters, List<SortByColumn> sortByColumns) throws PortalException, Exception {
        LOGGER.info("getTableName started with P1:String tableName=" + tableName + " StartIndex --> " + startIndex + "  Offset-->>  " + offset);
        final List<TableFieldLookUpDTO> lookUpList = new ArrayList<TableFieldLookUpDTO>();
        TableFieldLookUpDTO lookUp;
        final DynamicQuery vwUserTablesDynamicQuery = configureDynamicQueryForFieldName(tableName, filters);
        if (sortByColumns != null) {
            for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                final SortByColumn orderByColumn = (SortByColumn) iterator.next();
                if (orderByColumn.getType() == SortByColumn.Type.ASC) {
                    vwUserTablesDynamicQuery.addOrder(OrderFactoryUtil.asc("columnName"));
                } else {
                    vwUserTablesDynamicQuery.addOrder(OrderFactoryUtil.desc("columnName"));
                }
            }
        }
        vwUserTablesDynamicQuery.setLimit(startIndex, startIndex + offset);
        final List<VwUserTables> list = dao.getLookUpData(vwUserTablesDynamicQuery);
        for (final Iterator<VwUserTables> iterator = list.iterator(); iterator.hasNext();) {
            final VwUserTables dto = iterator.next();
            lookUp = new TableFieldLookUpDTO();
            lookUp.setUniqueId(dto.getUniqueId());
            lookUp.setTableName(dto.getTableName());
            lookUp.setFieldName(dto.getColumnName());
            lookUpList.add(lookUp);
        }
        LOGGER.info("getTableName return List<TableFieldLookUpDTO> lookUpList=" + lookUpList.size());
        return lookUpList;
    }

    public int getFieldNameCount(final String tableName, Set<Container.Filter> filters) throws SystemException, Exception {
        final DynamicQuery vwUserTablesDynamicQuery = configureDynamicQueryForFieldName(tableName, filters);
        final List<VwUserTables> list = dao.getLookUpData(vwUserTablesDynamicQuery);
        return list == null ? 0 : list.size();
    }

    /**
     * Gets the unique table.
     *
     * @param tableName the table name
     * @return the unique table
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public List<TableFieldLookUpDTO> getUniqueTable(final TableFieldLookUpDTO tableFieldLookUpDTO, int startIndex, int offset, Set<Container.Filter> filters, List<SortByColumn> sortByColumns) throws PortalException, Exception {
        LOGGER.info("getUniqueTable started with P1:TextField tableName  start " + startIndex + "--- offset  " + (startIndex + offset));
        final List<TableFieldLookUpDTO> lookUpList = new ArrayList<TableFieldLookUpDTO>();
        TableFieldLookUpDTO lookUp;
        final DynamicQuery vwUserTablesDynamicQuery = configureDynamicQuery(tableFieldLookUpDTO, filters);
        if (sortByColumns != null) {
            for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                final SortByColumn orderByColumn = (SortByColumn) iterator.next();

                if (orderByColumn.getType() == SortByColumn.Type.ASC) {
                    vwUserTablesDynamicQuery.addOrder(OrderFactoryUtil.asc(orderByColumn.getName()));
                } else {
                    vwUserTablesDynamicQuery.addOrder(OrderFactoryUtil.desc(orderByColumn.getName()));
                }
            }
        }
        vwUserTablesDynamicQuery.setLimit(startIndex, startIndex + offset);
        final List<String> list = dao.getLookUpData(vwUserTablesDynamicQuery);
        for (final Iterator<String> iterator = list.iterator(); iterator.hasNext();) {
            final String dto = iterator.next();
            lookUp = new TableFieldLookUpDTO();
            lookUp.setTableName(dto);
            lookUpList.add(lookUp);
        }
        LOGGER.info("getUniqueTable return List<TableFieldLookUpDTO> lookUpList=" + lookUpList.size());
        return lookUpList;
    }

    public int getTableCount(final TableFieldLookUpDTO tableFieldLookUpDTO, Set<Container.Filter> filters) throws SystemException, Exception {
        final DynamicQuery vwUserTablesDynamicQuery = configureDynamicQuery(tableFieldLookUpDTO, filters);
        final List<String> list = dao.getLookUpData(vwUserTablesDynamicQuery);
        return list == null ? 0 : list.size();
    }

    /**
     * Gets the level list.
     *
     * @param levelNo the level no
     * @return the level list
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public List<LevelDTO> getLevelList(final int levelNo) throws SystemException, Exception {
        LOGGER.info("getLevelList started with P1:int levelNo=" + levelNo);
        final List<LevelDTO> list = new ArrayList<LevelDTO>();
        LevelDTO levelDTO;
        final DynamicQuery tempDynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdLevelValues.class);
        tempDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.LEVEL_NO, levelNo));
        final List<ImtdLevelValues> temList = dao.getLevelList(tempDynamicQuery);
        for (final Iterator<ImtdLevelValues> iterator = temList.iterator(); iterator.hasNext();) {
            final ImtdLevelValues dto = iterator.next();
            levelDTO = new LevelDTO();
            levelDTO.setLevelValues(dto.getLevelValues());
            list.add(levelDTO);
            dao.deleteLevels(dto.getImtdLevelValuesSid());
        }
        LOGGER.info("getLevelList return List<LevelDTO> list=" + list.size());
        return list;
    }

    /**
     * Save temp data.
     *
     * @param levelNo the level no
     * @param levelName the level name
     * @param levelDTOBean the level dto bean
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public void saveTempData(final int levelNo, final String levelName, final BeanItemContainer<LevelDTO> levelDTOBean) throws SystemException, Exception {
        LOGGER.info("saveTempData started with P1:int levelNo=" + levelNo + " P2:String levelName=" + levelName + " P3:BeanItemContainer<LevelDTO> levelDTOBean");
        final ImtdLevelValues levelValues = ImtdLevelValuesLocalServiceUtil.createImtdLevelValues(0);
        final List<LevelDTO> list = levelDTOBean.getItemIds();
        final Date date = new Date();
        for (final Iterator<LevelDTO> iterator = list.iterator(); iterator.hasNext();) {
            final LevelDTO dto = iterator.next();
            levelValues.setLevelNo(levelNo);
            levelValues.setLevelName(levelName);
            levelValues.setLevelValues(dto.getLevelValues());
            levelValues.setCreatedDate(date);
            levelValues.setModifiedDate(date);
            dao.addLevels(levelValues);
        }
        LOGGER.info("saveTempData Ended");
    }

    /**
     * Gets the level values.
     *
     * @param tableName the table name
     * @param fieldName the field name
     * @param hierDTO the hier dto
     * @return the level values
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public List<TableFieldLookUpDTO> getLevelValues(String tableName, String fieldName, int startIndex, int offset, Set<Container.Filter> filters, List<SortByColumn> sortByColumns) throws SystemException, Exception {
        LOGGER.info("getLevelValues started with P1:String tableName=" + tableName + " P2:String fieldName=" + fieldName + " P3:List hierDTO");
        final List<TableFieldLookUpDTO> levelList = new ArrayList<TableFieldLookUpDTO>();
        TableFieldLookUpDTO dto;
        String order = StringUtils.EMPTY;
        String filterValue = StringUtils.EMPTY;

        List resultList = getLevelValuesResultList(tableName, fieldName);
        if (resultList != null && resultList.size() > 0) {
            for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                final SortByColumn orderByColumn = (SortByColumn) iterator.next();
                LOGGER.info("orderByColumn.getType()  " + orderByColumn.getType());
                order = String.valueOf(orderByColumn.getType());
                LOGGER.info("Order " + order);
            }

//            filters
            if (filters != null) {
                for (Container.Filter filter : filters) {
                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        String filterString = "'%" + stringFilter.getFilterString() + "%'";
                        if ("levelValues".equals(stringFilter.getPropertyId())) {
                            filterValue = filterString;
                        }
                    }
                }
            }

            Object[] obj = (Object[]) resultList.get(0);
            String table = String.valueOf(obj[0]);
            String field = String.valueOf(obj[1]);
            String listName = String.valueOf(obj[2]);
            if (table.equals("HELPER_TABLE")) {
                String helperQuery = "select DESCRIPTION from HELPER_TABLE where LIST_NAME='" + listName + "'";
                if (!filterValue.isEmpty()) {
                    helperQuery += "and DESCRIPTION like " + filterValue;
                }
                helperQuery += " ORDER BY DESCRIPTION " + order + " OFFSET " + startIndex + " ROWS FETCH NEXT " + offset + " ROWS ONLY; ";
                List valueList = HistRelationshipBuilderLocalServiceUtil.executeQuery(helperQuery);
                if (valueList != null && valueList.size() > 0) {
                    for (int i = 0; i < valueList.size(); i++) {
                        dto = new TableFieldLookUpDTO();
                        dto.setLevelValues(String.valueOf(valueList.get(i)));
                        levelList.add(dto);
                    }
                }
            } else {
                String valueQuery = "select distinct " + field + " from " + table + " where " + field + "!='null'";
                if (!filterValue.isEmpty()) {
                    valueQuery += "and " + field + " like " + filterValue;
                }
                valueQuery += " ORDER BY " + field + " " + order + " OFFSET " + startIndex + " ROWS FETCH NEXT " + offset + " ROWS ONLY; ";
                List valueList = HistRelationshipBuilderLocalServiceUtil.executeQuery(valueQuery);
                if (valueList != null && valueList.size() > 0) {
                    for (int i = 0; i < valueList.size(); i++) {
                        dto = new TableFieldLookUpDTO();
                        dto.setLevelValues(String.valueOf(valueList.get(i)));
                        levelList.add(dto);
                    }
                }
            }
        }
        LOGGER.info("getLevelValues return List<LevelDTO> levelList=" + levelList.size());
        return levelList;
    }

    public int getLevelValuesCount(String tableName, String fieldName, Set<Container.Filter> filters) throws SystemException, Exception {
        List resultList = getLevelValuesResultList(tableName, fieldName);
        String filterValue = StringUtils.EMPTY;
        if (resultList != null && resultList.size() > 0) {
            if (filters != null) {
                for (Container.Filter filter : filters) {
                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        String filterString = "'%" + stringFilter.getFilterString() + "%'";
                        if ("levelValues".equals(stringFilter.getPropertyId())) {
                            filterValue = filterString;
                        }
                    }
                }
            }
            Object[] obj = (Object[]) resultList.get(0);
            String table = String.valueOf(obj[0]);
            String field = String.valueOf(obj[1]);
            String listName = String.valueOf(obj[2]);
            if (table.equals("HELPER_TABLE")) {
                String helperQuery = "select DESCRIPTION from HELPER_TABLE where LIST_NAME='" + listName + "'";
                if (!filterValue.isEmpty()) {
                    helperQuery += "and DESCRIPTION like " + filterValue;
                }
                LOGGER.info("HelperQuery  " + helperQuery);
                resultList = HistRelationshipBuilderLocalServiceUtil.executeQuery(helperQuery);
            } else {
                String valueQuery = "select distinct " + field + " from " + table + " where " + field + "!='null'";
                if (!filterValue.isEmpty()) {
                    valueQuery += "and " + field + " like " + filterValue;
                }
                LOGGER.info("valueQuery  " + valueQuery);
                resultList = HistRelationshipBuilderLocalServiceUtil.executeQuery(valueQuery);
            }
        }
        return resultList == null ? 0 : resultList.size();
    }

    /**
     * Delete temp level values.
     *
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public void deleteTempLevelValues() throws SystemException, Exception {
        LOGGER.info("deleteTempLevelValues started");
        final List<ImtdLevelValues> tempList = dao.deleteMultipleLevels();
        dao.deleteTempLevelValues(tempList);
        LOGGER.info("deleteTempLevelValues Ended");
    }

    /**
     * Gets the hierarchy definition list.
     *
     * @return the hierarchy definition list
     * @throws SystemException the SystemException
     * @throws Exception the exception
     */
    public List<HierarchyBuilderDTO> getHierachyDefinitionList() throws SystemException, Exception {
        LOGGER.info("getHierachyDefinitionList started");
        final DynamicQuery hierarchyBuilderDynamicQuery = DynamicQueryFactoryUtil.forClass(HierarchyDefinition.class);
        List<HierarchyDefinition> resultList;
        resultList = dao.getHierachyDefinitionList(hierarchyBuilderDynamicQuery);
        final List<HierarchyBuilderDTO> list = getCustomizedResults1(resultList);
        LOGGER.info("getHierachyDefinitionList return List<HierarchyBuilderDTO> list");
        return list;
    }

    /**
     * Gets the existing version.
     *
     * @param systemId the system id
     * @return the existing version
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public int getExistingVersion(final int systemId) throws SystemException, PortalException, Exception {
        LOGGER.info("getExistingItemgroupNames started");
        int version = 0;
        final DynamicQuery hdHistoryDynamicQuery = DynamicQueryFactoryUtil.forClass(HistHierarchyDefinition.class);
        hdHistoryDynamicQuery.add(RestrictionsFactoryUtil.eq("primaryKey.hierarchyDefinitionSid", systemId));
        hdHistoryDynamicQuery.add(RestrictionsFactoryUtil.ne("primaryKey.actionFlag", "D"));
        final ProjectionList projList = ProjectionFactoryUtil.projectionList();
        projList.add(ProjectionFactoryUtil.property("primaryKey.versionNo"));
        hdHistoryDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));
        final List<Integer> finalList = new ArrayList<Integer>();
        final List<Integer> historyList = dao.getHierachyDefinitionHistoryList(hdHistoryDynamicQuery);
        finalList.addAll(historyList);
        final int size = finalList.size();
        if (size > ConstantsUtils.ZERO_NUM) {
            Collections.sort(finalList);
            version = finalList.get(size - 1);
        }
        LOGGER.info("getExistingItemgroupNames return results");
        return version;
    }

    private DynamicQuery configureDynamicQuery(TableFieldLookUpDTO tableFieldLookUpDTO, Set<Container.Filter> filters) {
        final DynamicQuery vwUserTablesDynamicQuery = DynamicQueryFactoryUtil.forClass(VwUserTables.class);
        String table = String.valueOf(tableFieldLookUpDTO.getTableName()).trim();
        String field = String.valueOf(tableFieldLookUpDTO.getFieldName()).trim();
        if (!table.equals(ConstantsUtils.EMPTY)) {

            final String tableValue = table.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            vwUserTablesDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.TABLE_NAME, tableValue));
        }

        if (!field.equals(ConstantsUtils.EMPTY)) {

            final String fieldValue = field.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            vwUserTablesDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.COLUMN_NAME, fieldValue));
        }

        vwUserTablesDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property(ConstantsUtils.TABLE_NAME)));

        if (filters != null) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = "%" + stringFilter.getFilterString() + "%";
                    if ("tableName".equals(stringFilter.getPropertyId())) {

                        vwUserTablesDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.TABLE_NAME, filterString));
                    }
                }
            }
        }

        return vwUserTablesDynamicQuery;

    }

    private DynamicQuery configureDynamicQueryForFieldName(String tableName, Set<Container.Filter> filters) {
        final DynamicQuery vwUserTablesDynamicQuery = DynamicQueryFactoryUtil.forClass(VwUserTables.class);

        if (tableName != null) {
            final String table1 = tableName.trim();
            final String table = table1.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            vwUserTablesDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.TABLE_NAME, table));
        }

        if (filters != null) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = "%" + stringFilter.getFilterString() + "%";
                    if ("fieldName".equals(stringFilter.getPropertyId())) {

                        vwUserTablesDynamicQuery.add(RestrictionsFactoryUtil.ilike("columnName", filterString));
                    }
                }
            }
        }
        return vwUserTablesDynamicQuery;
    }

    private List getLevelValuesResultList(String tableName, String fieldName) {
        String query = "SELECT CASE WHEN JVIEW.REFERENCE_TABLE_NAME = 'HELPER_TABLE' THEN 'HELPER_TABLE'"
                + " ELSE JVIEW.REFERENCE_TABLE_NAME END AS TABLE_NAME, CASE WHEN JVIEW.ACTUAL_TABLE_NAME = JVIEW.REFERENCE_TABLE_NAME THEN JVIEW.ACTUAL_COLUMN_NAME"
                + " ELSE CASE WHEN JVIEW.REFERENCE_TABLE_NAME = 'HELPER_TABLE' THEN 'DESCRIPTION' ELSE JVIEW.DESC_COLUMN_NAME"
                + " END END AS FIELD_NAME,CASE WHEN JVIEW.ACTUAL_TABLE_NAME = JVIEW.REFERENCE_TABLE_NAME THEN JVIEW.PRIMARY_KEY_COLUMN_NAME"
                + " ELSE CASE WHEN JVIEW.REFERENCE_TABLE_NAME = 'HELPER_TABLE' THEN JVIEW.LIST_NAME ELSE JVIEW.MAPPING_COLUMN_NAME"
                + " END END AS SID FROM   vw_helper_list JVIEW WHERE  ACTUAL_TABLE_NAME = '" + tableName + "' AND ACTUAL_COLUMN_NAME = '" + fieldName + "'";

        return HistRelationshipBuilderLocalServiceUtil.executeQuery(query);
    }

}
