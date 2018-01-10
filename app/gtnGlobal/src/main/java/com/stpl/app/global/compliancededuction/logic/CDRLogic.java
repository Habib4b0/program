
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.compliancededuction.logic;

import com.stpl.app.global.abstractsearch.dto.SearchResultsDTO;
import com.stpl.app.global.abstractsearch.util.ConstantUtil;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.common.util.HelperListUtil;
import com.stpl.app.global.compliancededuction.dto.CDRDto;
import com.stpl.app.global.dao.impl.ComplianceDeductionDaoImpl;
import com.stpl.app.global.dao.impl.StplSecurityDAOImpl;
import com.stpl.app.model.CdrModel;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.RsModelLocalServiceUtil;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.NotesTabLogic;
import com.stpl.app.util.QueryUtils;
import com.stpl.domain.global.ComplianceDeduction.ComplianceDeductionDao;
import com.stpl.domain.global.security.StplSecurityDAO;
import com.stpl.ifs.ui.NotesDTO;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CommonUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.SimpleStringFilter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.jboss.logging.Logger;

/**
 *
 * @author mohamed.hameed
 */
public class CDRLogic {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(CDRLogic.class);
    private static final ResourceBundle CONSTANT_PROPERTIES = ResourceBundle.getBundle("properties.constants");
    private static final HashMap<String, String> CRITERIA = new HashMap<String, String>();
    public static final SimpleDateFormat DB_DATE = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat COMMON_DATE = new SimpleDateFormat("MM-dd-yyy");
    private final StplSecurityDAO securityDto = new StplSecurityDAOImpl();

    /**
     * Gets the CDR Count for search.
     *
     * @param searchFields
     * @param searchItemForm the search item form
     * @param search
     * @return the dynamic query search
     *
     * public int getCDRCount(final ErrorfulFieldGroup searchFields, final
     * Set<Container.Filter> filterSet) throws Exception { int count = 0;
     * StringBuilder queryBuilder = new StringBuilder(); queryBuilder =
     * buildSearchQuery(searchFields, true, 0, 0);
     * LOGGER.debug(queryBuilder.toString()); List<Object> masterData =
     * (List<Object>)
     * RsModelLocalServiceUtil.executeSelectQuery(queryBuilder.toString(),
     * StringUtils.EMPTY, StringUtils.EMPTY); if (masterData != null &&
     * !masterData.isEmpty()) { Object ob = masterData.get(0); count +=
     * Integer.valueOf(String.valueOf(ob)); } return count; }/ /** Gets the CDR
     * Count for search.
     *
     * @param searchFields
     * @param searchItemForm the search item form
     * @param search
     * @return the dynamic query search
     */
    public int getCDRCount(final ErrorfulFieldGroup searchFields, final Set<Container.Filter> filterSet) {
        int count = 0;
        StringBuilder queryBuilder;
        queryBuilder = buildSearchQuery(searchFields, true);
            queryBuilder = getFilterQuery(filterSet, queryBuilder);
        LOGGER.debug(queryBuilder.toString());
        List<Object> masterData = (List<Object>) RsModelLocalServiceUtil.executeSelectQuery(queryBuilder.toString(), StringUtils.EMPTY, StringUtils.EMPTY);
        if (masterData != null && !masterData.isEmpty()) {
            Object ob = masterData.get(0);
            count += Integer.valueOf(String.valueOf(ob));
        }
        return count;
    }

    public List<SearchResultsDTO> loadCDRResults(
            final ErrorfulFieldGroup searchFields, final int start, final int end, final List<SortByColumn> columns, final Set<Container.Filter> filterSet) {
        List<SearchResultsDTO> searchList;
        LOGGER.debug("Entering searchRebatePlan with start of=" + start + "and endIndex of= " + end + "  Column Size +" + ((columns == null) ? columns : columns.size()));
        StringBuilder queryBuilder;
        queryBuilder = buildSearchQuery(searchFields, false);
        queryBuilder = getFilterQuery(filterSet, queryBuilder);
        if (columns.isEmpty()) {
            queryBuilder.append("ORDER BY RULE_NO ASC");
        } else {
            for (Iterator<SortByColumn> iterator = columns.iterator(); iterator.hasNext();) {
                SortByColumn orderByColumn = (SortByColumn) iterator.next();
                String columnId = orderByColumn.getName();
                if ("ruleNo".equalsIgnoreCase(columnId)) {
                    queryBuilder.append("ORDER BY RULE_NO");
                }
                if ("ruleType".equalsIgnoreCase(columnId)) {
                    queryBuilder.append("ORDER BY RULE_TYPE");
                }
                if ("ruleName".equalsIgnoreCase(columnId)) {
                    queryBuilder.append("ORDER BY RULE_NAME");
                }
                if ("ruleCategory".equalsIgnoreCase(columnId)) {
                    queryBuilder.append("ORDER BY RULE_CATEGORY");
                }
                if ("cdrCreatedDate".equalsIgnoreCase(columnId)) {
                    queryBuilder.append("ORDER BY CREATED_DATE");
                }
                if ("createdBy".equalsIgnoreCase(columnId)) {
                    queryBuilder.append("ORDER BY CREATED_BY");
                }
                if ("cdrModifiedDate".equalsIgnoreCase(columnId)) {
                    queryBuilder.append("ORDER BY MODIFIED_DATE");
                }
                if ("modifiedBy".equalsIgnoreCase(columnId)) {
                    queryBuilder.append("ORDER BY MODIFIED_BY");
                }
                if (orderByColumn.getType() == SortByColumn.Type.ASC) {
                    queryBuilder.append(" ASC");
                } else {
                    queryBuilder.append(" DESC");
                }
            }
        }
        queryBuilder.append(false ? StringUtils.EMPTY : (" OFFSET " + start + " ROWS FETCH NEXT " + (end) + " ROWS ONLY"));
        final List list = (List) RsModelLocalServiceUtil.executeSelectQuery(queryBuilder.toString(), StringUtils.EMPTY, StringUtils.EMPTY);

        searchList = getCustomizedSearchFormToDTO(list);
        return searchList;
    }

    private void loadCriteriaInMap() {
        CRITERIA.clear();
        CRITERIA.put(ConstantsUtils.COMBO1, "RULE_TYPE");
        CRITERIA.put(ConstantsUtils.TEXT6, "RULE_NO");
        CRITERIA.put(ConstantsUtils.TEXT7, "RULE_NAME");
        CRITERIA.put(ConstantsUtils.COMBO6, "RULE_CATEGORY");
    }

    private StringBuilder buildSearchQuery(ErrorfulFieldGroup searchFields, boolean isCount) {
        StringBuilder queryBuilder = new StringBuilder();
        String query = isCount ? "COUNT(*)" : " CDR_MODEL_SID,RULE_TYPE,RULE_NO,\n"
                + "RULE_NAME,RULE_CATEGORY,CREATED_DATE,\n"
                + "CREATED_BY,MODIFIED_DATE,MODIFIED_BY ";
        queryBuilder.append(" SELECT " + query + " FROM CDR_MODEL  WHERE");
        if (CRITERIA.isEmpty()) {
            loadCriteriaInMap();
        }
        Set<String> keys = CRITERIA.keySet();
        for (String fields : keys) {

            if ((ConstantsUtils.COMBO1.equals(fields) || ConstantsUtils.COMBO6.equals(fields)) && searchFields.getField(fields).getValue() != null && !ConstantUtil.SELECT_ONE.equals(searchFields.getField(fields).getValue().toString())) {
                queryBuilder.append(ConstantsUtils.AND).append(CRITERIA.get(fields)).append(ConstantsUtils.LIKE_QUOTE).append(String.valueOf(((com.stpl.app.util.HelperDTO) searchFields.getField(fields).getValue()).getId())).append(ConstantsUtils.SINGLE_QUOTE);

            } else if (searchFields.getField(fields).getValue() != null && !ConstantUtil.SELECT_ONE.equals(searchFields.getField(fields).getValue().toString()) && !searchFields.getField(fields).getValue().toString().trim().isEmpty()) {
                queryBuilder.append(ConstantsUtils.AND).append(CRITERIA.get(fields)).append(ConstantsUtils.LIKE_QUOTE).append(CommonUtil.buildSearchCriteria(searchFields.getField(fields).getValue().toString())).append(ConstantsUtils.SINGLE_QUOTE);
            }
        }
        queryBuilder = new StringBuilder(queryBuilder.toString().replace("WHERE AND", " WHERE "));
        return queryBuilder;
    }

    private List<SearchResultsDTO> getCustomizedSearchFormToDTO(List list) {
        final List<SearchResultsDTO> searchResultsList = new ArrayList<>();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final SearchResultsDTO searchDto = new SearchResultsDTO();
                final Object[] object = (Object[]) list.get(i);
                searchDto.setSystemID(!ConstantsUtils.NULL.equals(String.valueOf(object[0])) && StringUtils.isNotBlank(String.valueOf(object[0])) ? String.valueOf(object[0]) : StringUtils.EMPTY);
                searchDto.setRuleNo(!ConstantsUtils.NULL.equals(String.valueOf(object[NumericConstants.TWO])) && StringUtils.isNotBlank(String.valueOf(object[NumericConstants.TWO])) ? String.valueOf(object[NumericConstants.TWO]) : StringUtils.EMPTY);
                searchDto.setRuleName(!ConstantsUtils.NULL.equals(String.valueOf(object[NumericConstants.THREE])) && StringUtils.isNotBlank(String.valueOf(object[NumericConstants.THREE])) ? String.valueOf(object[NumericConstants.THREE]) : StringUtils.EMPTY);
                searchDto.setCdrCreatedDate(object[NumericConstants.FIVE] != null && StringUtils.isNotBlank(String.valueOf(object[NumericConstants.FIVE])) && !ConstantsUtils.NULL.equals(String.valueOf(object[NumericConstants.FIVE])) ? (Date) object[NumericConstants.FIVE] : null);
                searchDto.setCdrModifiedDate(object[NumericConstants.SEVEN] != null && StringUtils.isNotBlank(String.valueOf(object[NumericConstants.SEVEN])) && !ConstantsUtils.NULL.equals(String.valueOf(object[NumericConstants.SEVEN])) ? (Date) object[NumericConstants.SEVEN] : null);
                searchDto.setRecordLockStatus(false);
                if (!ConstantsUtils.NULL.equals(String.valueOf(object[NumericConstants.SIX])) && StringUtils.isNotBlank(String.valueOf(object[NumericConstants.SIX]))) {
                    try {
                        User createdUser = (User) securityDto.getUserByUserId(Long.valueOf(String.valueOf(object[NumericConstants.SIX])));
                        searchDto.setCreatedBy(createdUser == null ? StringUtils.EMPTY : createdUser.getFullName());
                    } catch (SystemException | PortalException ex) {
                        java.util.logging.Logger.getLogger(CDRLogic.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                if (!ConstantsUtils.NULL.equals(String.valueOf(object[NumericConstants.EIGHT])) && StringUtils.isNotBlank(String.valueOf(object[NumericConstants.EIGHT]))) {
                    try {
                        User createdUser = (User) securityDto.getUserByUserId(Long.valueOf(String.valueOf(object[NumericConstants.EIGHT])));
                        searchDto.setModifiedBy(createdUser == null ? StringUtils.EMPTY : createdUser.getFullName());
                    } catch (SystemException | PortalException ex) {
                        java.util.logging.Logger.getLogger(CDRLogic.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

                if (!ConstantsUtils.NULL.equals(String.valueOf(object[NumericConstants.FOUR])) && StringUtils.isNotBlank(String.valueOf(object[NumericConstants.FOUR])) && !ConstantsUtils.ZERO.equals(String.valueOf(object[NumericConstants.FOUR]))) {
                    searchDto.setRuleCategory(HelperListUtil.getInstance().getIdDescMap().get(Integer.valueOf(String.valueOf(object[NumericConstants.FOUR]).trim())));

                } else {
                    searchDto.setRuleCategory(StringUtils.EMPTY);
                }

                if (!ConstantsUtils.NULL.equals(String.valueOf(object[1])) && StringUtils.isNotBlank(String.valueOf(object[1])) && !ConstantsUtils.ZERO.equals(String.valueOf(object[1]))) {
                    searchDto.setRuleType(HelperListUtil.getInstance().getIdDescMap().get(Integer.valueOf(String.valueOf(object[1]).trim())));

                } else {
                    searchDto.setRuleType(StringUtils.EMPTY);
                }

                searchResultsList.add(searchDto);
            }
        }
        return searchResultsList;
    }

    private StringBuilder getFilterQuery(final Set<Container.Filter> filterSet, final StringBuilder stringBuilder) {
        Map<Integer, String> userMap = StplSecurity.userMap;
        if (filterSet != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    if (!"createdBy".equals(stringFilter.getPropertyId().toString()) && !"modifiedBy".equals(stringFilter.getPropertyId().toString())) {
                        stringBuilder.append(ConstantsUtils.AND).append(CONSTANT_PROPERTIES.getString(stringFilter.getPropertyId().toString())).append(ConstantsUtils.LIKE_QUOTE).append(CommonUtil.buildFilterCriteria(stringFilter.getFilterString())).append("'");
                    } else {
                        stringBuilder.append(ConstantsUtils.AND).append(CONSTANT_PROPERTIES.getString(stringFilter.getPropertyId().toString())).append(" like '").append(CommonUtil.buildFilterCriteria(stringFilter.getFilterString())).append("'");
                    }
                } else if (filter instanceof Between) {
                    Between betweenFilter = (Between) filter;
                    Date startValue = (Date) betweenFilter.getStartValue();
                    Date endValue = (Date) betweenFilter.getEndValue();
                    if (startValue != null) {
                        stringBuilder.append(ConstantsUtils.AND).append(CONSTANT_PROPERTIES.getString(betweenFilter.getPropertyId().toString())).append(" >= '").append(dateFormat.format(startValue)).append("' ");
                    }
                    if (endValue != null) {
                        stringBuilder.append(ConstantsUtils.AND).append(CONSTANT_PROPERTIES.getString(betweenFilter.getPropertyId().toString())).append(" <= '").append(dateFormat.format(endValue)).append("' ");
                    }
                } else if (filter instanceof Compare) {
                    Compare compare = (Compare) filter;
                    Compare.Operation operation = compare.getOperation();
                    Date value = (Date) compare.getValue();
                    if (Compare.Operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                        stringBuilder.append(ConstantsUtils.AND).append(CONSTANT_PROPERTIES.getString(compare.getPropertyId().toString())).append(" >= '").append(dateFormat.format(value)).append("' ");
                    } else {
                        stringBuilder.append(ConstantsUtils.AND).append(CONSTANT_PROPERTIES.getString(compare.getPropertyId().toString())).append(" <= '").append(dateFormat.format(value)).append("' ");
                    }
                }

            }
        }

        return stringBuilder;
    }

    /**
     * Update Rules Logic
     *
     * @param ruleInfoList
     * @param sessionDTO
     */
    public void updateRulesDetailsEditLogic(final List ruleInfoList, final SessionDTO sessionDTO) {
        try {
            if (!ruleInfoList.isEmpty()) {
                Object beanObj = ruleInfoList.get(1);
                StringBuilder detailsQuery = new StringBuilder(StringUtils.EMPTY);
                for (CDRDto object : (List<CDRDto>) beanObj) {
                    if (object.getCdrDetailsSid() == 0) {
                        String value = (StringUtils.EMPTY.equals(object.getValueText()) || ConstantsUtils.NULL.equals(object.getValueText())) ? ConstantsUtils.ZERO : object.getValueText().replace(ConstantsUtils.COMMA, StringUtils.EMPTY).replace(ConstantsUtils.PERCENCTAGE, StringUtils.EMPTY).replace("$", StringUtils.EMPTY).trim();
                        detailsQuery.append(", ( ").append(sessionDTO.getSystemId())
                                .append(ConstantsUtils.COMMA).
                                append(ConstantsUtils.SINGLE_QUOTE).append(object.getLineTypeDdlb().getId()).append(ConstantsUtils.SINGLE_QUOTE).append(ConstantsUtils.COMMA).
                                append(ConstantsUtils.SINGLE_QUOTE).append(object.getItemGroupDdlb().getId()).append(ConstantsUtils.SINGLE_QUOTE).append(ConstantsUtils.COMMA).
                                append(ConstantsUtils.SINGLE_QUOTE).append(object.getKeywordDdlb().getId()).append(ConstantsUtils.SINGLE_QUOTE).append(ConstantsUtils.COMMA).
                                append(ConstantsUtils.SINGLE_QUOTE).append(object.getOperatorDdlb().getId()).append(ConstantsUtils.SINGLE_QUOTE).append(ConstantsUtils.COMMA).
                                append(ConstantsUtils.SINGLE_QUOTE).append(value).append(ConstantsUtils.SINGLE_QUOTE).append(ConstantsUtils.COMMA).
                                append(ConstantsUtils.SINGLE_QUOTE).append(object.getComparisonDdlb() != null ? object.getComparisonDdlb().getId() : 0).append(ConstantsUtils.SINGLE_QUOTE).append(ConstantsUtils.COMMA)
                                .append(ConstantsUtils.SINGLE_QUOTE).append(object.getLogicalOperatorDdlb() != null ? object.getLogicalOperatorDdlb().getId() : 0).append(ConstantsUtils.SINGLE_QUOTE).append(ConstantsUtils.COMMA)
                                .append(ConstantsUtils.SINGLE_QUOTE).append(DB_DATE.format(new Date())).append(ConstantsUtils.SINGLE_QUOTE).append(ConstantsUtils.COMMA)
                                .append(ConstantsUtils.SINGLE_QUOTE).append(sessionDTO.getUserId() != null ? sessionDTO.getUserId() : ConstantsUtils.ZERO).append(ConstantsUtils.SINGLE_QUOTE).append(ConstantsUtils.COMMA)
                                .append(ConstantsUtils.SINGLE_QUOTE).append(DB_DATE.format(new Date())).append(ConstantsUtils.SINGLE_QUOTE).append(ConstantsUtils.COMMA)
                                .append(ConstantsUtils.SINGLE_QUOTE).append(sessionDTO.getUserId() != null ? sessionDTO.getUserId() : ConstantsUtils.ZERO).append(ConstantsUtils.SINGLE_QUOTE)
                                .append(")");
                    }
                }
                if (!detailsQuery.toString().isEmpty()) {

                    String masterQuery = QueryUtils.getQuery(null, "insertRuleDetails-Edit");
                    String detailQuery = detailsQuery.toString().replaceFirst(ConstantsUtils.COMMA, StringUtils.EMPTY);
                    String finalQuery = masterQuery.concat(detailQuery);
                    QueryUtils.queryBulkUpdate(finalQuery);
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public void deleteRules(List ruleDetailsIds, String columnSid) {
        if (!ruleDetailsIds.isEmpty()) {
            StringBuilder idsBuilder = new StringBuilder();
            for (Object id : ruleDetailsIds) {
                idsBuilder.append(ConstantsUtils.COMMA + String.valueOf(id));
            }
            String ids = idsBuilder.toString().replaceFirst(ConstantsUtils.COMMA, StringUtils.EMPTY);
            String masterQuery = "delete FROM CDR_DETAILS where " + columnSid + "  in (" + ids + ") ";
            QueryUtils.queryBulkUpdate(masterQuery);
        }
    }

    /**
     * Get Input for master
     *
     * @param binderDto
     * @param sessionDTO
     * @return List
     */
    List getInputForModelInsert(final CDRDto binderDto, final SessionDTO sessionDTO) {
        List queryList = new ArrayList();
        queryList.add(binderDto.getRuleTypeDto().getId());
        queryList.add(binderDto.getRuleNo());
        queryList.add(binderDto.getRuleName());
        queryList.add(binderDto.getRuleCategoryDto() != null ? binderDto.getRuleCategoryDto().getId() : 0);
        queryList.add(DB_DATE.format(new Date()));
        queryList.add(sessionDTO.getUserId() != null ? sessionDTO.getUserId() : ConstantsUtils.ZERO);
        queryList.add(DB_DATE.format(new Date()));
        queryList.add(sessionDTO.getUserId() != null ? sessionDTO.getUserId() : ConstantsUtils.ZERO);
        return queryList;
    }

    public static List getSavedRuleDetails(final int systemId) {
        List list = new ArrayList();
        list.add(systemId);
        return QueryUtils.querySelect(list, "getRuleInfo-Edit", null);
    }

    public void updateRuleModelEditLogic(CDRDto binderDto, SessionDTO sessionDTO, String notes) {
        ComplianceDeductionDao dao = new ComplianceDeductionDaoImpl();
        CdrModel cdrModel;
        try {
            if (ConstantsUtils.EDIT.equals(sessionDTO.getMode()) && sessionDTO.getSystemId() != 0) {
                cdrModel = dao.getCdrModelBySystemId(sessionDTO.getSystemId());
            } else {
                cdrModel = dao.createCdrModel(0);
                cdrModel.setCreatedBy(Integer.valueOf(sessionDTO.getUserId()));
                cdrModel.setCreatedDate(new Date());
            }
            cdrModel.setRuleNo(binderDto.getRuleNo());
            cdrModel.setRuleName(binderDto.getRuleName());
            cdrModel.setRuleType(binderDto.getRuleTypeDto() == null ? 0 : binderDto.getRuleTypeDto().getId());
            cdrModel.setRuleCategory(binderDto.getRuleCategoryDto() == null ? 0 : binderDto.getRuleCategoryDto().getId());
            cdrModel.setModifiedBy(Integer.valueOf(sessionDTO.getUserId()));
            cdrModel.setModifiedDate(new Date());
            if (!notes.isEmpty() && !ConstantsUtils.NULL.equals(notes)) {
                cdrModel.setInternalNotes(notes);
            }
            CdrModel cdrModel1 = dao.updateCdrModel(cdrModel);
            sessionDTO.setSystemId(cdrModel1.getCdrModelSid());
        } catch (SystemException | PortalException ex) {
            java.util.logging.Logger.getLogger(CDRLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void saveOrUpdateNotesTab(List<NotesDTO> uploadedData, List<NotesDTO> removeDetailsList, int cdrModelSid) {
        NotesTabLogic notesLogic = new NotesTabLogic();
        try {
            LOGGER.debug("removeDetailsList.size()" + removeDetailsList.size());
            if (removeDetailsList.size() != 0) {
                for (int i = 0; i < removeDetailsList.size(); i++) {
                    NotesDTO dtoValue = removeDetailsList.get(i);
                    if (dtoValue.getDocDetailsId() != 0) {
                        notesLogic.deleteUploadedFile(dtoValue.getDocDetailsId(), dtoValue.getDocumentFullPath());
                    }
                }
            }
            notesLogic.saveUploadedInformation(uploadedData, ConstantsUtils.CDR_MODEL, cdrModelSid);
        } catch (SystemException | PortalException ex) {
            java.util.logging.Logger.getLogger(CDRLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteCDRRecords(Integer cdrModelSId) {
        List list = new ArrayList();
        list.add(cdrModelSId);
        deleteRules(list, "CDR_MODEL_SID");
        deleteAttachments(cdrModelSId);
        try {
            ComplianceDeductionDao dao = new ComplianceDeductionDaoImpl();
            dao.deleteCdrModel(cdrModelSId);
        } catch (SystemException | PortalException ex) {
            java.util.logging.Logger.getLogger(CDRLogic.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void deleteAttachments(Integer cdrModelSId) {

        String masterQuery = "delete from MASTER_DATA_FILES"
                + " where MASTER_TABLE_SID=" + cdrModelSId + " and"
                + " MASTER_TABLE_NAME='CDR_MODEL' ";
        QueryUtils.queryBulkUpdate(masterQuery);

    }

    public boolean isSameRuleNoAndName(CDRDto binderDto) {
        String countQuery = "select count(CDR_MODEL_SID) from CDR_MODEL where RULE_NO='" + binderDto.getRuleNo() + "' and rule_name ='" + binderDto.getRuleName() + "' ";
        List<Object[]> list = HelperTableLocalServiceUtil.executeSelectQuery(countQuery);
        return getCount(list) == 0 ? Boolean.FALSE : Boolean.TRUE;
    }

    public static int getCount(List<Object[]> list) {
        if (!list.isEmpty()) {
            Object obj = list.get(0);
            return obj == null ? 0 : (Integer) obj;
        }
        return 0;
    }
}
