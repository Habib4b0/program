/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.netsalesformula.logic;

import com.stpl.app.global.abstractsearch.dto.SearchResultsDTO;
import com.stpl.app.global.abstractsearch.util.ConstantUtil;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.common.util.HelperListUtil;
import com.stpl.app.global.company.util.CommonUtils;
import com.stpl.app.global.netsalesformula.dto.NetSalesRuleLookupDto;
import com.stpl.app.global.netsalesformula.dto.NsfDto;
import com.stpl.app.global.netsalesformula.dto.SalesBasisDto;
import com.stpl.app.global.netsalesformula.ui.view.NSFView;
import com.stpl.app.model.CdrModel;
import com.stpl.app.model.NetSalesFormulaMaster;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.service.CdrModelLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.NetSalesFormulaMasterLocalServiceUtil;
import com.stpl.app.service.RsModelLocalServiceUtil;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.Constants;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CommonUtil;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.util.dao.orm.CustomSQLUtil;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.jboss.logging.Logger;

/**
 * The class Nsf Logic .
 * @author vinodhini
 */
public class NsfLogic {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(NsfLogic.class);
    public static ResourceBundle constantProperties = ResourceBundle.getBundle("properties.constants");
    static HashMap<String, String> criteria = new HashMap<>();
    static HashMap<String, String> nsRuleCriteria = new HashMap<>();
    public static final SimpleDateFormat DB_DATE = new SimpleDateFormat("yyyy-MM-dd");
    HelperListUtil helperListUtil = HelperListUtil.getInstance();
    static HashMap<String, String> nsrDetailsDbMap = new HashMap<>();
    static HashMap<String, String> nsRuleFilterCriteria = new HashMap<>();

    public int getNsfCount(final ErrorfulFieldGroup searchFields, final Set<Container.Filter> filterSet) throws ParseException {
        int count = 0;
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder = buildSearchQuery(searchFields, true);
        queryBuilder = getFilterQuery(filterSet, queryBuilder);


        List<Object> masterData = (List<Object>) RsModelLocalServiceUtil.executeSelectQuery(queryBuilder.toString(), StringUtils.EMPTY, StringUtils.EMPTY);
        if (masterData != null && !masterData.isEmpty()) {
            Object ob = masterData.get(0);
            count += Integer.valueOf(String.valueOf(ob));
        }
        LOGGER.debug(" nsfCount count=" + count);
        return count;
    }

    public List<SearchResultsDTO> loadNsfResults(
            final ErrorfulFieldGroup searchFields, final int start, final int end, final List<SortByColumn> columns, final Set<Container.Filter> filterSet) throws SystemException, ParseException {
        List<SearchResultsDTO> searchList = new ArrayList<>();
        LOGGER.debug("Entering loadNsfResults with start of=" + start + "and endIndex of= " + end + "  Column Size +" + ((columns == null) ? columns : columns.size()));
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder = buildSearchQuery(searchFields, false);
        queryBuilder = getFilterQuery(filterSet, queryBuilder);
        queryBuilder = getOrderQuery(queryBuilder, columns, start, end);
       
        queryBuilder = new StringBuilder(queryBuilder.toString().replaceAll(ConstantsUtils.WHERE_AND, ConstantsUtils.QUERY_WHERE));
        queryBuilder = new StringBuilder(queryBuilder.toString().endsWith(ConstantsUtils.WHERE) ? queryBuilder.toString().replace(ConstantsUtils.WHERE, " ") : queryBuilder);

        final List list = (List) RsModelLocalServiceUtil.executeSelectQuery(queryBuilder.toString(), StringUtils.EMPTY, StringUtils.EMPTY);
        searchList = getCustomizedSearchFormToDTO(list);
        return searchList;
    }

    private void loadCriteriaInMap() {
        criteria.clear();
        criteria.put("combo2", "NET_SALES_FORMULA_TYPE");
        criteria.put("text6", "NET_SALES_FORMULA_ID");
        criteria.put("text7", "NET_SALES_FORMULA_NO");
        criteria.put("text8", "NET_SALES_FORMULA_NAME");
    }

    private StringBuilder buildSearchQuery(ErrorfulFieldGroup searchFields, boolean isCount) {
        StringBuilder queryBuilder = new StringBuilder();
        String query = isCount ? "COUNT( * )" : constantProperties.getString("netSalesFormula");
        queryBuilder.append(" SELECT ").append(query).append(" FROM NET_SALES_FORMULA_MASTER WHERE INBOUND_STATUS <> 'D' ");
        if (criteria.isEmpty()) {
            loadCriteriaInMap();
        }
        Set<String> keys = criteria.keySet();
        for (String fields : keys) {

            if (searchFields.getField(fields).getValue() != null && !ConstantUtil.SELECT_ONE.equals(searchFields.getField(fields).getValue().toString()) && !searchFields.getField(fields).getValue().toString().trim().isEmpty()) {
                if ("combo2".equalsIgnoreCase(fields)) {
                    queryBuilder.append(ConstantsUtils.AND).append(criteria.get(fields)).append(" = ").append(((com.stpl.app.util.HelperDTO) searchFields.getField("combo2").getValue()).getSystemId());
                } else {
                    queryBuilder.append(ConstantsUtils.AND).append(criteria.get(fields)).append(" LIKE '").append(searchFields.getField(fields).getValue().toString().trim().replace("*", "%")).append("'");
                }
            }
        }
        return queryBuilder;
    }

    private List<SearchResultsDTO> getCustomizedSearchFormToDTO(List list) {
        final List<SearchResultsDTO> searchResultsList = new ArrayList<>();
        try {
            if (list != null) {
                Map<Integer, String> userMap = StplSecurity.userMap;
                if (userMap.isEmpty()) {
                    userMap = StplSecurity.getUserName();
                }
                for (int i = 0; i < list.size(); i++) {
                    final SearchResultsDTO searchDTO = new SearchResultsDTO();
                    final Object[] object = (Object[]) list.get(i);
                    searchDTO.setNetSalesFormulaType((helperListUtil.getIdHelperDTOMap().get(object[0] != null ? Integer.valueOf(String.valueOf(object[0])) : 0)).getDescription());
                    searchDTO.setNetSalesFormulaId(!ConstantsUtils.NULL.equals(String.valueOf(object[1])) && StringUtils.isNotBlank(String.valueOf(object[1])) ? String.valueOf(object[1]) : StringUtils.EMPTY);
                    searchDTO.setNetSalesFormulaNo(!ConstantsUtils.NULL.equals(String.valueOf(object[NumericConstants.TWO])) && StringUtils.isNotBlank(String.valueOf(object[NumericConstants.TWO])) ? String.valueOf(object[NumericConstants.TWO]) : StringUtils.EMPTY);
                    searchDTO.setNetSalesFormulaName(!ConstantsUtils.NULL.equals(String.valueOf(object[NumericConstants.THREE])) && StringUtils.isNotBlank(String.valueOf(object[NumericConstants.THREE])) ? String.valueOf(object[NumericConstants.THREE]) : StringUtils.EMPTY);
                    if (object[NumericConstants.FOUR] != null) {
                        Date createdDate = (Date) object[NumericConstants.FOUR];
                        searchDTO.setNsfcreateDate(CommonUtils.convertDateToString(createdDate));
                        DateFormat df = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
                        createdDate = df.parse(searchDTO.getNsfcreateDate());
                        searchDTO.setNsfcreatedDate(createdDate);
                    }
                    searchDTO.setNsfcreatedBy(object[NumericConstants.FIVE] != null ? userMap.get(Integer.valueOf(String.valueOf(object[NumericConstants.FIVE]))) : StringUtils.EMPTY);
                    if (object[NumericConstants.SIX] != null) {
                        Date modifyDate = (Date) object[NumericConstants.SIX];
                        searchDTO.setNsfmodifyDate(CommonUtils.convertDateToString(modifyDate));
                        DateFormat df = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
                        modifyDate = df.parse(searchDTO.getNsfmodifyDate());
                        searchDTO.setNsfmodifiedDate(modifyDate);
                    }
                    searchDTO.setNsfmodifiedBy(object[NumericConstants.SEVEN] != null ? userMap.get(Integer.valueOf(String.valueOf(object[NumericConstants.SEVEN]))) : StringUtils.EMPTY);
                    searchDTO.setSystemID(!ConstantsUtils.NULL.equals(String.valueOf(object[NumericConstants.EIGHT])) && StringUtils.isNotBlank(String.valueOf(object[NumericConstants.EIGHT])) ? String.valueOf(object[NumericConstants.EIGHT]) : "0");
                    searchResultsList.add(searchDTO);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return searchResultsList;
    }

    private StringBuilder getFilterQuery(final Set<Container.Filter> filterSet, final StringBuilder stringBuilder) throws ParseException {
        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    stringBuilder.append(ConstantsUtils.AND).append(constantProperties.getString(stringFilter.getPropertyId().toString())).append(" LIKE '").append(CommonUtil.buildFilterCriteria(stringFilter.getFilterString())).append("'");
                } else if (filter instanceof Between) {

                    Between stringFilter = (Between) filter;
                    String startValue = DB_DATE.format(stringFilter.getStartValue());
                    String endValue = DB_DATE.format(stringFilter.getEndValue());

                    if (startValue != null) {
                        stringBuilder.append(ConstantsUtils.AND).append(constantProperties.getString(stringFilter.getPropertyId().toString())).append(" >= '").append(startValue).append("' ");
                    } 
                    if (endValue != null) {
                        stringBuilder.append(ConstantsUtils.AND).append(constantProperties.getString(stringFilter.getPropertyId().toString())).append(" <= '").append(endValue).append("' ");
                    }
                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    Compare.Operation operation = stringFilter.getOperation();
                    if (stringFilter.getValue() instanceof Date) {
                        String filterString = DB_DATE.format(stringFilter.getValue());
                        if (Compare.Operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                            stringBuilder.append(ConstantsUtils.AND).append(constantProperties.getString(String.valueOf(stringFilter.getPropertyId()))).append(" >= '").append(filterString).append("' ");
                        } else {
                            stringBuilder.append(ConstantsUtils.AND).append(constantProperties.getString(String.valueOf(stringFilter.getPropertyId()))).append(" <= '").append(filterString).append("' ");
                        }
                    }
                }
            }
        }

        return stringBuilder;
    }

    private StringBuilder getOrderQuery(final StringBuilder stringBuilder, final List<SortByColumn> sortByColumns, final int startIndex, final int endIndex) {
        boolean sortOrder = false;
        String orderByColumn = null;
        if (sortByColumns != null) {
            for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                final SortByColumn sortByColumn = (SortByColumn) iterator.next();
                orderByColumn = constantProperties.getString(sortByColumn.getName());
                sortOrder = sortByColumn.getType() != SortByColumn.Type.ASC;
            }
        }
        if (orderByColumn == null || StringUtils.EMPTY.equals(orderByColumn)) {
            stringBuilder.append(" ORDER BY CREATED_DATE ");
        } else {
            stringBuilder.append(" ORDER BY ").append(orderByColumn).append((!sortOrder) ? " ASC " : " DESC ");
        }

        stringBuilder.append(" OFFSET ").append(startIndex);
        stringBuilder.append(" ROWS FETCH NEXT ").append(endIndex).append(" ROWS ONLY;");

        return stringBuilder;
    }

    public int getNsRuleCount(final ErrorfulFieldGroup searchFields, final Set<Container.Filter> filterSet,String tabName) throws ParseException {
        int count = 0;
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder = buildNsRuleSearchQuery(searchFields, true,tabName);
        queryBuilder = getNsRuleFilterQuery(filterSet, queryBuilder);

        queryBuilder = new StringBuilder(queryBuilder.toString().replaceAll(ConstantsUtils.WHERE_AND, ConstantsUtils.QUERY_WHERE));
        queryBuilder = new StringBuilder(queryBuilder.toString().endsWith(ConstantsUtils.WHERE) ? queryBuilder.toString().replace(ConstantsUtils.WHERE, " ") : queryBuilder);


        List<Object> masterData = (List<Object>) RsModelLocalServiceUtil.executeSelectQuery(queryBuilder.toString(), StringUtils.EMPTY, StringUtils.EMPTY);
        if (masterData != null && !masterData.isEmpty()) {
            Object ob = masterData.get(0);
            count += Integer.valueOf(String.valueOf(ob));
        }
        LOGGER.debug(" ns rule lookup count===>" + count);
        return count;
    }

    public List<NetSalesRuleLookupDto> loadNsRuleResults(
            final ErrorfulFieldGroup searchFields, final int start, final int end, final List<SortByColumn> columns, final Set<Container.Filter> filterSet,String tabName) throws SystemException, ParseException {
        List<NetSalesRuleLookupDto> searchList = new ArrayList<>();
        LOGGER.debug("Entering loadNsRuleResults with start of=" + start + "and endIndex of= " + end + "  Column Size +" + ((columns == null) ? columns : columns.size()));
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder = buildNsRuleSearchQuery(searchFields, false,tabName);
        queryBuilder = getNsRuleFilterQuery(filterSet, queryBuilder);
        queryBuilder = getNsRuleOrderQuery(queryBuilder, columns, start, end);
     
        queryBuilder = new StringBuilder(queryBuilder.toString().replaceAll(ConstantsUtils.WHERE_AND, ConstantsUtils.QUERY_WHERE));
        queryBuilder = new StringBuilder(queryBuilder.toString().endsWith(ConstantsUtils.WHERE) ? queryBuilder.toString().replace(ConstantsUtils.WHERE, " ") : queryBuilder);

        final List list = (List) RsModelLocalServiceUtil.executeSelectQuery(queryBuilder.toString(), StringUtils.EMPTY, StringUtils.EMPTY);
        searchList = getCustomizedNsRuleDTO(list);
        LOGGER.debug("End of loadNsRuleResults");
        return searchList;
    }

    private void loadNsRuleCriteriaMap() {
        nsRuleCriteria.clear(); 
        nsRuleCriteria.put("ruleType", "MODEL.RULE_TYPE");
        nsRuleCriteria.put("ruleNo", "MODEL.RULE_NO");
        nsRuleCriteria.put("ruleName", "MODEL.RULE_NAME");
        nsRuleCriteria.put("ruleCategory", "MODEL.RULE_CATEGORY");
    }
    private StringBuilder buildNsRuleSearchQuery(ErrorfulFieldGroup searchFields, boolean isCount,String tabName) {
        StringBuilder queryBuilder = new StringBuilder();
        String query = isCount ? "COUNT (DISTINCT MODEL.CDR_MODEL_SID ) " : constantProperties.getString("netSalesRule");
        boolean isNotNSF=false;
        if (StringUtils.isBlank(tabName)) {
            queryBuilder.append(" SELECT ").append(query).append(" FROM CDR_MODEL MODEL WHERE");
            isNotNSF=true;
        } else if ("Sales Basis".equalsIgnoreCase(tabName)) {
            queryBuilder.append(" SELECT ").append(query).append(" FROM dbo.CDR_MODEL MODEL JOIN CDR_DETAILS DETAIL ON DETAIL.CDR_MODEL_SID=MODEL.CDR_MODEL_SID AND DETAIL.KEYWORD = (SELECT HELPER_TABLE_SID FROM dbo.HELPER_TABLE WHERE LIST_NAME LIKE 'KEYWORD' AND DESCRIPTION LIKE 'Contract Sales') ");
        } else if ("Deduction".equalsIgnoreCase(tabName)) {
            queryBuilder.append(" SELECT ").append(query).append(" FROM dbo.CDR_MODEL MODEL JOIN CDR_DETAILS DETAIL ON DETAIL.CDR_MODEL_SID=MODEL.CDR_MODEL_SID AND DETAIL.KEYWORD = (SELECT HELPER_TABLE_SID FROM dbo.HELPER_TABLE WHERE LIST_NAME LIKE 'KEYWORD' AND DESCRIPTION LIKE 'Contract Deductions') ");
        }
       
        if (nsRuleCriteria.isEmpty()) {
            loadNsRuleCriteriaMap();
        }
        Set<String> keys = nsRuleCriteria.keySet();
        for (String fields : keys) {

            if (searchFields.getField(fields).getValue() != null && !ConstantUtil.SELECT_ONE.equals(searchFields.getField(fields).getValue().toString()) && !searchFields.getField(fields).getValue().toString().trim().isEmpty()) {
                if ("ruleType".equalsIgnoreCase(fields) || "ruleCategory".equalsIgnoreCase(fields)) {
                    if (isNotNSF) {
                        queryBuilder.append(ConstantsUtils.AND).append(nsRuleCriteria.get(fields)).append(" = '").append(((HelperDTO) searchFields.getField(fields).getValue()).getId()).append("'");
                    } else {
                        if (!"ruleType".equalsIgnoreCase(fields)) {
                            queryBuilder.append(ConstantsUtils.AND).append(nsRuleCriteria.get(fields)).append(" = '").append(((HelperDTO) searchFields.getField(fields).getValue()).getId()).append("'");
                        }
                    }
                } else {
                    queryBuilder.append(ConstantsUtils.AND).append(nsRuleCriteria.get(fields)).append(" LIKE '").append(CommonUtil.buildSearchCriteria(String.valueOf(searchFields.getField(fields).getValue()))).append("'");
                }
            }
        }

        return queryBuilder;
    }

    private List<NetSalesRuleLookupDto> getCustomizedNsRuleDTO(List list) {
        final List<NetSalesRuleLookupDto> searchResultsList = new ArrayList<>();
        try {
            if (list != null) {
              
                for (int i = 0; i < list.size(); i++) {
                    final NetSalesRuleLookupDto searchDTO = new NetSalesRuleLookupDto();
                    final Object[] object = (Object[]) list.get(i);
                    searchDTO.setRuleSystemId(!ConstantsUtils.NULL.equals(String.valueOf(object[0])) && StringUtils.isNotBlank(String.valueOf(object[0])) ? String.valueOf(object[0]) : "0");
                    searchDTO.setRuleType(helperListUtil.getIdHelperDTOMap().get(object[1] != null ? Integer.valueOf(String.valueOf(object[1])) : 0));
                    searchDTO.setRuleNo(!ConstantsUtils.NULL.equals(String.valueOf(object[NumericConstants.TWO])) && StringUtils.isNotBlank(String.valueOf(object[NumericConstants.TWO])) ? String.valueOf(object[NumericConstants.TWO]) : StringUtils.EMPTY);
                    searchDTO.setRuleName(!ConstantsUtils.NULL.equals(String.valueOf(object[NumericConstants.THREE])) && StringUtils.isNotBlank(String.valueOf(object[NumericConstants.THREE])) ? String.valueOf(object[NumericConstants.THREE]) : StringUtils.EMPTY);
                    if (object[NumericConstants.FOUR] != null) {
                    searchDTO.setRuleCategory(helperListUtil.getIdHelperDTOMap().get(object[NumericConstants.FOUR] != null ? Integer.valueOf(String.valueOf(object[NumericConstants.FOUR])) : 0));
                    searchDTO.setRuleCategoryString(ConstantsUtils.SELECT_ONE.equals(searchDTO.getRuleCategory().getDescription()) ? StringUtils.EMPTY : searchDTO.getRuleCategory().getDescription());
                    }
                    searchResultsList.add(searchDTO);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return searchResultsList;
    }

    private StringBuilder getNsRuleFilterQuery(final Set<Container.Filter> filterSet, final StringBuilder stringBuilder) throws ParseException {
        if (nsRuleFilterCriteria.isEmpty()) {
            nsRuleFilterCriteria.clear();
            nsRuleFilterCriteria.put("ruleType", "MODEL.RULE_TYPE");
            nsRuleFilterCriteria.put("ruleNo", "MODEL.RULE_NO");
            nsRuleFilterCriteria.put("ruleName", "MODEL.RULE_NAME");
            nsRuleFilterCriteria.put("ruleCategoryString", "MODEL.RULE_CATEGORY");
        }
              
        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    stringBuilder.append(ConstantsUtils.AND).append(nsRuleFilterCriteria.get(stringFilter.getPropertyId().toString())).append(" LIKE '").append(CommonUtil.buildFilterCriteria(stringFilter.getFilterString())).append("'");
                } 
            }
        }

        return stringBuilder;
    }

    private StringBuilder getNsRuleOrderQuery(StringBuilder stringBuilder, final List<SortByColumn> sortByColumns, final int startIndex, final int endIndex) {
        boolean sortOrder = false;
        String orderByColumn = null;
        if (nsRuleFilterCriteria.isEmpty()) {
            nsRuleFilterCriteria.clear();
            nsRuleFilterCriteria.put("ruleType", "RULE_TYPE");
            nsRuleFilterCriteria.put("ruleNo", "RULE_NO");
            nsRuleFilterCriteria.put("ruleName", "RULE_NAME");
            nsRuleFilterCriteria.put("ruleCategoryString", "RULE_CATEGORY");
        }
        if (sortByColumns != null) {
            for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                final SortByColumn sortByColumn = (SortByColumn) iterator.next();
                orderByColumn = nsRuleFilterCriteria.get(sortByColumn.getName());
                sortOrder = sortByColumn.getType() != SortByColumn.Type.ASC;
            }
        }
        stringBuilder = new StringBuilder(stringBuilder.toString().endsWith(ConstantsUtils.WHERE) ? stringBuilder.toString().replace(ConstantsUtils.WHERE, " ") : stringBuilder);
        if (orderByColumn == null || StringUtils.EMPTY.equals(orderByColumn)) {
            stringBuilder.append(" ORDER BY MODEL.CDR_MODEL_SID ");
        } else {
            stringBuilder.append(" ORDER BY ").append(orderByColumn).append((!sortOrder) ? " ASC " : " DESC ");
        }

        stringBuilder.append(" OFFSET ").append(startIndex);
        stringBuilder.append(" ROWS FETCH NEXT ").append(endIndex).append(" ROWS ONLY;");

        return stringBuilder;
    }
    
       public int getNsRuleDetailsCount(final String ruleSystemId, final Set<Container.Filter> filterSet) throws ParseException {
        LOGGER.debug("Entering getNsRuleDetailsCount ");
        int count = 0;
        if(!"0".equals(ruleSystemId)){
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder = buildNsRuleDetailsQuery(ruleSystemId, true);
        queryBuilder = getNsRuleDetailsFilterQuery(filterSet, queryBuilder);

        queryBuilder = new StringBuilder(queryBuilder.toString().replaceAll(ConstantsUtils.WHERE_AND, ConstantsUtils.QUERY_WHERE));
        queryBuilder = new StringBuilder(queryBuilder.toString().endsWith(ConstantsUtils.WHERE) ? queryBuilder.toString().replace(ConstantsUtils.WHERE, " ") : queryBuilder);


        List<Object> masterData = (List<Object>) RsModelLocalServiceUtil.executeSelectQuery(queryBuilder.toString(), StringUtils.EMPTY, StringUtils.EMPTY);
        if (masterData != null && !masterData.isEmpty()) {
            Object ob = masterData.get(0);
            count += Integer.valueOf(String.valueOf(ob));
        }
       }
        LOGGER.debug("End of getNsRuleDetailsCount= "+count);
        return count;
    }

    public List<NetSalesRuleLookupDto> loadNsRuleDetailsResults(
            final String ruleSystemId, final int start, final int end, final List<SortByColumn> columns, final Set<Container.Filter> filterSet) throws SystemException {
        List<NetSalesRuleLookupDto> searchList = new ArrayList<>();
        try{
        LOGGER.debug("Entering loadNsRuleResults with start of=" + start + "and endIndex of= " + end + "  Column Size +" + ((columns == null) ? columns : columns.size()));
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder = buildNsRuleDetailsQuery(ruleSystemId, false);
        queryBuilder = getNsRuleDetailsFilterQuery(filterSet, queryBuilder);
        queryBuilder = getNsRuleDetailsOrderQuery(queryBuilder, columns, start, end);

        queryBuilder = new StringBuilder(queryBuilder.toString().replaceAll(ConstantsUtils.WHERE_AND, ConstantsUtils.QUERY_WHERE));
        queryBuilder = new StringBuilder(queryBuilder.toString().endsWith(ConstantsUtils.WHERE) ? queryBuilder.toString().replace(ConstantsUtils.WHERE, " ") : queryBuilder);
     
        final List list = (List) RsModelLocalServiceUtil.executeSelectQuery(queryBuilder.toString(), StringUtils.EMPTY, StringUtils.EMPTY);
        searchList = getCustomizedNsRuleDetailsDTO(list);
        }catch(Exception e){
        LOGGER.error(e);
        }
        LOGGER.debug("End of loadNsRuleResults");
        return searchList;
    }

    private void loadNsRuleDetailsMap() {
        nsrDetailsDbMap.clear();
        nsrDetailsDbMap.put("lineType", "LINE_TYPE");
        nsrDetailsDbMap.put("association", "ITEM_GROUP_MS_ASSOCIATION");
        nsrDetailsDbMap.put("keyword", "KEYWORD");
        nsrDetailsDbMap.put("operator", "OPERATOR");
        nsrDetailsDbMap.put("value", "VALUE");
        nsrDetailsDbMap.put("comparison", "COMPARISON");
        nsrDetailsDbMap.put("logicalOperator", "LOGICAL_OPERATOR");
    }
    private StringBuilder buildNsRuleDetailsQuery(String ruleSystemId, boolean isCount) {
        StringBuilder queryBuilder = new StringBuilder();
        String query = isCount ? "COUNT( * )" : constantProperties.getString("netSalesRuleDetails");
        queryBuilder.append(" SELECT ").append(query).append(" FROM CDR_DETAILS WHERE CDR_MODEL_SID = ").append(ruleSystemId);
       
        return queryBuilder;
    }

    private List<NetSalesRuleLookupDto> getCustomizedNsRuleDetailsDTO(List list) {
        final List<NetSalesRuleLookupDto> searchResultsList = new ArrayList<>();
        try {
            if (list != null) {
              
                for (Object nsrList : list) {
                    final NetSalesRuleLookupDto searchDTO = new NetSalesRuleLookupDto();
                    final Object[] object = (Object[]) nsrList;
                    searchDTO.setRuleSystemId(!ConstantsUtils.NULL.equals(String.valueOf(object[0])) && StringUtils.isNotBlank(String.valueOf(object[0])) ? String.valueOf(object[0]) : "0");
                    searchDTO.setLineType(object[1] != null ?getDescById((int)object[1]):StringUtils.EMPTY);
                    searchDTO.setAssociation(object[NumericConstants.TWO] != null ? getDescById((int)object[NumericConstants.TWO]):StringUtils.EMPTY);
                    searchDTO.setKeyword(object[NumericConstants.THREE] != null ? getDescById((int)object[NumericConstants.THREE]):StringUtils.EMPTY);
                    searchDTO.setOperator(object[NumericConstants.FOUR] != null ? getDescById((int)object[NumericConstants.FOUR]):StringUtils.EMPTY);
                    searchDTO.setValue(!ConstantsUtils.NULL.equals(String.valueOf(object[NumericConstants.FIVE])) && StringUtils.isNotBlank(String.valueOf(object[NumericConstants.FIVE])) ? String.valueOf(object[NumericConstants.FIVE]) : StringUtils.EMPTY);
                    searchDTO.setComparison(object[NumericConstants.SIX] != null ?getDescById((int)object[NumericConstants.SIX]):StringUtils.EMPTY);
                    searchDTO.setLogicalOperator(object[NumericConstants.SEVEN] != null?getDescById((int)object[NumericConstants.SEVEN]):StringUtils.EMPTY);
                    searchResultsList.add(searchDTO);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return searchResultsList;
    }

    private StringBuilder getNsRuleDetailsFilterQuery(final Set<Container.Filter> filterSet, final StringBuilder stringBuilder) throws ParseException {
      
        if (nsrDetailsDbMap.isEmpty()) {
        loadNsRuleDetailsMap();
        }
              
        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    stringBuilder.append(ConstantsUtils.AND).append(nsrDetailsDbMap.get(stringFilter.getPropertyId().toString())).append(" LIKE '").append(CommonUtil.buildFilterCriteria(stringFilter.getFilterString())).append("'");
                } 
            }
        }

        return stringBuilder;
    }

    private StringBuilder getNsRuleDetailsOrderQuery(final StringBuilder stringBuilder, final List<SortByColumn> sortByColumns, final int startIndex, final int endIndex) {
        boolean sortOrder = false;
        String orderByColumn = null;
        if (sortByColumns != null) {
            for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                final SortByColumn sortByColumn = (SortByColumn) iterator.next();
                orderByColumn = nsrDetailsDbMap.get(sortByColumn.getName());
                sortOrder = sortByColumn.getType() != SortByColumn.Type.ASC;
            }
        }
        if (orderByColumn == null || StringUtils.EMPTY.equals(orderByColumn)) {
            stringBuilder.append(" ORDER BY CREATED_DATE ");
        } else {
            stringBuilder.append(" ORDER BY ").append(orderByColumn).append((!sortOrder) ? " ASC " : " DESC ");
        }

        stringBuilder.append(" OFFSET ").append(startIndex);
        stringBuilder.append(" ROWS FETCH NEXT ").append(endIndex).append(" ROWS ONLY;");

        return stringBuilder;
    }
    
    
     public Object nsfInsert(SessionDTO sessionDto,final String queryName,boolean isSelected,String sbName) throws SystemException {                     
        Map<String, Object> input = new HashMap<>();
        String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        int user = Integer.valueOf(userId);
        input.put("?UID",user);
        input.put("?SID",sessionDto.getUiSessionId());
        input.put("?NSFID",sessionDto.getSystemId());
        return tempOperation(input,queryName,isSelected,sbName);
    }
    
     public Object tempOperation(final Map<String, Object> input, final String queryName,boolean isSelected,String sbName){
      
        try {
            LOGGER.debug("Entering tempOperation method ");
            LOGGER.debug("Query Name : " + queryName);
                        
            String customSql = CustomSQLUtil.get(queryName);
            if(isSelected){
                customSql=customSql+CustomSQLUtil.get(sbName);
            }
          
            for (String key : input.keySet()) {
                LOGGER.debug("Key : " + key);
                customSql = customSql.replace(key, String.valueOf(input.get(key)));
            }
          
           
            LOGGER.debug("End of tempOperation method");
            Object temp =RsModelLocalServiceUtil.executeUpdateQuery(customSql, this, this);
            return temp;
        } catch (Exception e) {
           
            LOGGER.error(e);
            return null;
        }
    }

   public void saveNsf(SessionDTO sessionDto, ErrorfulFieldGroup binder,HelperDTO formulaType, String contractSelected, int nsrId) throws PortalException, SystemException {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        int user = Integer.valueOf(userId);
        NetSalesFormulaMaster nsf;
        boolean isSelected=true;
        if(!ConstantsUtils.EDIT.equalsIgnoreCase(sessionDto.getMode())){
            nsf = NetSalesFormulaMasterLocalServiceUtil.createNetSalesFormulaMaster(0);

            nsf.setNetSalesFormulaId(String.valueOf(binder.getField(ConstantsUtils.FORMULA_ID).getValue()));
            nsf.setNetSalesFormulaNo(String.valueOf(binder.getField(ConstantsUtils.FORMULA_NO).getValue()));
            nsf.setNetSalesFormulaName(String.valueOf(binder.getField(ConstantsUtils.FORMULA_NAME).getValue()));
            nsf.setNetSalesFormulaCategory(checkEmptyDataFromFields(ConstantsUtils.FORMULA_CATEGORY, binder) ? Constants.ZERO : ((HelperDTO) binder.getField(ConstantsUtils.FORMULA_CATEGORY).getValue()).getId());
            nsf.setNetSalesFormulaType(formulaType.getId());
            nsf.setContractSelection(contractSelected);
            nsf.setCreatedBy(user);
            nsf.setModifiedBy(user);
            nsf.setInboundStatus("A");
            nsf.setModifiedDate(new Date());
            nsf.setCreatedDate(new Date());
            if (nsf.getContractSelection().startsWith("Existing")) {
                if (nsrId != 0) {
                    nsf.setCdrModelSid(String.valueOf(nsrId));
                }
            } else {
                nsf.setCdrModelSid(null);
            }
            nsf = NetSalesFormulaMasterLocalServiceUtil.addNetSalesFormulaMaster(nsf);
            sessionDto.setSystemId(nsf.getNetSalesFormulaMasterSid());

        } else {
            nsf = NetSalesFormulaMasterLocalServiceUtil.getNetSalesFormulaMaster(sessionDto.getSystemId());
            nsf.setNetSalesFormulaId(String.valueOf(binder.getField(ConstantsUtils.FORMULA_ID).getValue()));
            nsf.setNetSalesFormulaNo(String.valueOf(binder.getField(ConstantsUtils.FORMULA_NO).getValue()));
            nsf.setNetSalesFormulaName(String.valueOf(binder.getField(ConstantsUtils.FORMULA_NAME).getValue()));
            nsf.setNetSalesFormulaCategory(checkEmptyDataFromFields(ConstantsUtils.FORMULA_CATEGORY, binder) ? Constants.ZERO : ((HelperDTO) binder.getField(ConstantsUtils.FORMULA_CATEGORY).getValue()).getId());
            nsf.setNetSalesFormulaType(formulaType.getId());
            nsf.setContractSelection(contractSelected);
            nsf.setModifiedBy(user);
            nsf.setInboundStatus("A");
            nsf.setModifiedDate(new Date());
            if (nsf.getContractSelection().startsWith("Existing")) {
                if (nsrId != 0) {
                    nsf.setCdrModelSid(String.valueOf(nsrId));
                }else{
                    nsf.setCdrModelSid(null);
                }
            } else {
                nsf.setCdrModelSid(null);
            }
            NetSalesFormulaMasterLocalServiceUtil.updateNetSalesFormulaMaster(nsf);
        }
        if(contractSelected.startsWith("Existing")){
           if (isPresent(sessionDto.getSystemId())) {
               StringBuilder query = new StringBuilder(StringUtils.EMPTY);
               query.append("UPDATE SALES_BASIS_DETAILS SET INBOUND_STATUS = 'D'");
               query.append(ConstantsUtils.QUERY_NSF).append(sessionDto.getSystemId()).append(ConstantsUtils.SINGLE_QUOTE);
               RsModelLocalServiceUtil.executeUpdateQuery(query.toString(), this, this);
           } 
               isSelected = false;
       }
 
        if ("Contract".equals(formulaType.getDescription())) {
            nsfInsert(sessionDto, "tempDeductionSaveContract",isSelected,"tempSaveSB");
        } else {
            nsfInsert(sessionDto, "tempDeductionSave",isSelected,"tempSaveSB");
        }
    }

    /**
     * Remove all the records from the Temp Table.
     *
     * @param sessionDto
     * @throws SystemException
     */
    public void removeAllFromTempTable(SessionDTO sessionDto) throws SystemException, PortalException {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final String sessionId = String.valueOf(sessionDto.getUiSessionId());

        StringBuilder deleteQuery = new StringBuilder();

        deleteQuery.append("delete FROM dbo.IMTD_DEDUCTION_DETAILS WHERE USERS_SID='");
        deleteQuery.append(userId);
        deleteQuery.append("' and SESSION_ID='");
        deleteQuery.append(sessionId).append("';");
        deleteQuery.append("delete FROM dbo.IMTD_SALES_BASIS_DETAILS WHERE USERS_SID='");
        deleteQuery.append(userId);
        deleteQuery.append("' and SESSION_ID='");
        deleteQuery.append(sessionId).append("';");
        RsModelLocalServiceUtil.executeUpdateQuery(deleteQuery.toString(), this, this);

    }
    
     /**
     * 
     * @param fieldName
     * @param binder
     * @return 
     */
    private boolean checkEmptyDataFromFields(String fieldName, ErrorfulFieldGroup binder) {
        boolean isEmpty = false;        
        
        if (binder.getField(fieldName) instanceof TextField) {
            TextField textField = (TextField)binder.getField(fieldName);
            isEmpty = StringUtils.isBlank(textField.getValue()) || ConstantsUtils.NULL.equals(textField.getValue());
        }
        if (binder.getField(fieldName) instanceof ComboBox) {
            
            ComboBox comboBox = (ComboBox) binder.getField(fieldName);
            if (comboBox.getValue() instanceof com.stpl.app.util.HelperDTO) {
                isEmpty = comboBox.getValue() == null || ConstantsUtils.SELECT_ONE.equals(((com.stpl.app.util.HelperDTO) comboBox.getValue()).getDescription());
            } else if ( comboBox.getValue() instanceof HelperDTO) {
                isEmpty = comboBox.getValue() == null || ConstantsUtils.SELECT_ONE.equals(((HelperDTO) comboBox.getValue()).getDescription());
            } else if( comboBox.getValue() == null){
                isEmpty = true;
            }
            
        }      
        return isEmpty;
    }
    
    public NsfDto getNsfMasterById(final int idValue) throws SystemException, PortalException {

        LOGGER.debug("In getNsfMasterById P1:id=" + idValue);
        NsfDto nsfDTO = new NsfDto();
        if(idValue!=0){
        NetSalesFormulaMaster nsf = NetSalesFormulaMasterLocalServiceUtil.getNetSalesFormulaMaster(idValue);
        nsfDTO = convertToDTO(nsf, nsfDTO);
        }
        LOGGER.debug("getNsfMasterById Returns NetSalesFormulaMaster NsfDto");
        return nsfDTO;

    }

    private NsfDto convertToDTO(NetSalesFormulaMaster nsf, NsfDto nsfDTO) throws SystemException, PortalException {
        if (nsf != null) {
            nsfDTO.setFormulaNo(nsf.getNetSalesFormulaNo());
            nsfDTO.setFormulaName(nsf.getNetSalesFormulaName());
            nsfDTO.setFormulaId(nsf.getNetSalesFormulaId());
            nsfDTO.setFormulaCategory(helperListUtil.getIdHelperDTOMap().get(nsf.getNetSalesFormulaCategory()));
            NSFView.setFormCategory(nsfDTO.getFormulaCategory());
            nsfDTO.setFormulaType(helperListUtil.getIdHelperDTOMap().get(nsf.getNetSalesFormulaType()));
            if (nsf.getContractSelection() != null) {
                if (nsf.getContractSelection().startsWith("Existing")) {
                    nsfDTO.setContractSelected(nsf.getContractSelection());
                    if (StringUtils.isNotBlank(nsf.getCdrModelSid())) {
                        nsfDTO.setNsRuleId(Integer.valueOf(nsf.getCdrModelSid()));
                        if (nsfDTO.getNsRuleId() != 0) {
                            CdrModel cdr = CdrModelLocalServiceUtil.getCdrModel(nsfDTO.getNsRuleId());
                            nsfDTO.setNsfRuleName(cdr.getRuleName());
                        }
                    }
                    nsfDTO.setIsSelected(false);
                } else {
                    nsfDTO.setContractSelected(nsf.getContractSelection());
                    nsfDTO.setNsRuleId(0);
                    nsfDTO.setIsSelected(true);
                }
            }
        }
        return nsfDTO;
    }
         
    /**
     * To delete NSF.
     *
     * @param idValue
     * @throws SystemException
     */
    public void deleteNsfMasterById(final int idValue) throws SystemException, PortalException {
        LOGGER.debug("In deleteNsfMasterById P1:id=" + idValue);
        StringBuilder deleteQuery = new StringBuilder();
        deleteQuery.append("UPDATE dbo.SALES_BASIS_DETAILS SET INBOUND_STATUS ='D' WHERE NET_SALES_FORMULA_MASTER_SID ='");
        deleteQuery.append(idValue);
        deleteQuery.append("' ;");
        deleteQuery.append(" UPDATE dbo.DEDUCTION_DETAILS SET INBOUND_STATUS ='D' WHERE NET_SALES_FORMULA_MASTER_SID ='");
        deleteQuery.append(idValue);
        deleteQuery.append("' ;");
        deleteQuery.append(" UPDATE dbo.NET_SALES_FORMULA_MASTER SET INBOUND_STATUS ='D' WHERE NET_SALES_FORMULA_MASTER_SID ='");
        deleteQuery.append(idValue);
        deleteQuery.append("' ;");
        RsModelLocalServiceUtil.executeUpdateQuery(deleteQuery.toString(), this, this);
        LOGGER.debug("deleteNsfMasterById ends");
    }
    
      public boolean checkNsf(SessionDTO sessionDto,String queryCheck,String dbTableName){
        LOGGER.debug("Entering checkNsf");
          boolean checkFlag = false;
          final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
          final String sessionId = sessionDto.getUiSessionId();
          StringBuilder queryBuilder = new StringBuilder();

          if ("INDICATOR_CHECK".equals(queryCheck)) {
              queryBuilder.append("select count(*) from ").append(dbTableName).append(" \n"
                      + "where INDICATOR not in ('+','-') and OPERATION <> 'D' and INBOUND_STATUS <> 'D' and session_ID = '").append(sessionId);
              queryBuilder.append("' and USERS_SID ='").append(userId).append("' ;");
          }

          final List list = (List) RsModelLocalServiceUtil.executeSelectQuery(queryBuilder.toString(), StringUtils.EMPTY, StringUtils.EMPTY);
          int count = Integer.parseInt(String.valueOf(list.get(0)));
              if (count != 0) {
                  checkFlag = true;
              }
        
        LOGGER.debug("End of checkNsf checkFlag="+checkFlag);
        return checkFlag;
    }
   
    public void saveProjectionSelection(Map map, int nsfID, String screenName) throws PortalException {
        try {
            int count = 0;
            if(nsfID !=0){
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("SELECT COUNT(*) from NSF_SELECTION ").append(" \n"
                    + "WHERE NET_SALES_FORMULA_MASTER_SID = '").append(nsfID);
            queryBuilder.append("' and SCREEN_NAME ='").append(screenName).append("' ;");
            List list = (List) RsModelLocalServiceUtil.executeSelectQuery(queryBuilder.toString(), StringUtils.EMPTY, StringUtils.EMPTY);
            if (list != null && !list.isEmpty()) {
                count = Integer.parseInt(StringUtils.isNotBlank(String.valueOf(list.get(0))) ? String.valueOf(list.get(0)) : "0");
                list = null;
            }
            }
            if (count == 0) {
                saveSelection(map, nsfID, screenName, "save");
            } else {
                saveSelection(map, nsfID, screenName, "update");
            }

        } catch (Exception ex) {
            LOGGER.debug(ex);
        }
    }

    public void saveSelection(Map map, int projectionID, String screenName, String saveOrUpdate) throws PortalException, SystemException {
        Object[] obj = map.keySet().toArray();
        StringBuilder queryBuilder = new StringBuilder();
        if ("save".equalsIgnoreCase(saveOrUpdate)) {
            for (int i = 0; i < map.size(); i++) {
                queryBuilder.append("INSERT INTO NSF_SELECTION (NET_SALES_FORMULA_MASTER_SID,SCREEN_NAME,FIELD_NAME,FIELD_VALUES) VALUES ");
                queryBuilder.append("('").append(projectionID).append("','").append(screenName).append("','");
                queryBuilder.append(obj[i]).append("','").append(map.get(obj[i])).append("')\n");
            }
        } else {
            for (int i = 0; i < map.size(); i++) {
                queryBuilder.append("UPDATE NSF_SELECTION SET FIELD_NAME = '");
                queryBuilder.append(obj[i]).append("',").append("FIELD_VALUES = '").append(map.get(obj[i])).append("'");
                queryBuilder.append(ConstantsUtils.QUERY_NSF).append(projectionID).append("' AND SCREEN_NAME = '").append(screenName).append("' AND FIELD_NAME ='").append(obj[i]).append("'\n");
            }
        }
        RsModelLocalServiceUtil.executeUpdateQuery(queryBuilder.toString(), this, this);
    }
    
    /**
     * Get Sales Basis Selection Map
     *
     * @param nsfID
     * @param screenName
     * @return
     */
    public Map<Object, Object> getSalesBasisSelectionMap(final int nsfID, final String screenName) {
        List list = null;
        Map<Object, Object> map = new HashMap<>();
        try {
            if (nsfID != 0) {
                StringBuilder queryBuilder = new StringBuilder();
                queryBuilder.append("SELECT FIELD_NAME, FIELD_VALUES from NSF_SELECTION ").append(" \n"
                        + "WHERE NET_SALES_FORMULA_MASTER_SID = '").append(nsfID);
                queryBuilder.append("' and SCREEN_NAME ='").append(screenName).append("' ;");
                list = (List) RsModelLocalServiceUtil.executeSelectQuery(queryBuilder.toString(), StringUtils.EMPTY, StringUtils.EMPTY);
            }
            if (list != null && !list.isEmpty()) {
                for (Object nsfList : list) {
                    Object[] obj = (Object[]) nsfList;
                    map.put(obj[0], obj[1]);
                }
                list = null;
            }

            return map;
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return null;
    }

       private String getDescById(int systemId) throws PortalException, SystemException {
        if (systemId == 0) {
            return StringUtils.EMPTY;
        } else {
            return helperListUtil.getIdHelperDTOMap().get(systemId).getDescription();
        }

    }
        public SalesBasisDto getSalesBasisSelection(final int nsfID) {
        Map<Object, Object> map = getSalesBasisSelectionMap(nsfID, "Sales Basis");
        SalesBasisDto sbDto = new SalesBasisDto();
        if (map != null && !map.isEmpty()) {

            Object value = map.get("contractNo");

            if (value != null) {
                sbDto.setContractNo(String.valueOf(value));
            }

            value = map.get("contractName");
            if (value != null) {
                sbDto.setContractName(String.valueOf(value));
            }

            value = map.get("contractHolder");
            if (value != null) {
                sbDto.setContractHolder(String.valueOf(value));
            }

            value = map.get(ConstantsUtils.CFP_NO);
            if (value != null) {
                sbDto.setCompanyFamilyPlanNo(String.valueOf(value));
            }

            value = map.get(ConstantsUtils.CFP_NAME);
            if (value != null) {
                sbDto.setCompanyFamilyPlanName(String.valueOf(value));
            }

            value = map.get(ConstantsUtils.ITEM_FAMILY_PLAN_NO);
            if (value != null) {
                sbDto.setItemFamilyPlanNo(String.valueOf(value));
            }

            value = map.get(ConstantsUtils.ITEM_FAMILY_PLAN_NAME);
            if (value != null) {
                sbDto.setItemFamilyPlanName(String.valueOf(value));
            }

            value = map.get("companyNo");
            if (value != null) {
                sbDto.setCompanyNo(String.valueOf(value));
            }

            value = map.get("companyName");
            if (value != null) {
                sbDto.setCompanyName(String.valueOf(value));
            }

            value = map.get("itemNo");
            if (value != null) {
                sbDto.setItemNo(String.valueOf(value));
            }

            value = map.get("itemName");
            if (value != null) {
                sbDto.setItemName(String.valueOf(value));
            }

            value = map.get("marketType");
            if (value != null) {
                sbDto.setMarketType(helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(String.valueOf(value))));
            }
            
// To load Available Customers Table
            value = map.get("contractMasterSid");
            if (value != null) {
                sbDto.setContractMasterSid(String.valueOf(value));
            }
        }
        return sbDto;
    }
    public String duplicateCheck(ErrorfulFieldGroup binder) {

        String noQuery = "select count(*) from NET_SALES_FORMULA_MASTER where NET_SALES_FORMULA_ID='" + String.valueOf(binder.getField(ConstantsUtils.FORMULA_ID).getValue()).trim() + "'";
        List resultList = (List) HelperTableLocalServiceUtil.executeSelectQuery(noQuery);
        if (Integer.valueOf(String.valueOf(resultList.get(0))) > 0) {
            return "IDEXIST";
        }

        noQuery = "select count(*) from NET_SALES_FORMULA_MASTER where NET_SALES_FORMULA_NAME='" + String.valueOf(binder.getField(ConstantsUtils.FORMULA_NAME).getValue()).trim() + "'";
        resultList = (List) HelperTableLocalServiceUtil.executeSelectQuery(noQuery);
        if (Integer.valueOf(String.valueOf(resultList.get(0))) > 0) {
            return "NAMEEXIST";
        }
        noQuery = "select count(*) from NET_SALES_FORMULA_MASTER where NET_SALES_FORMULA_NO='" + String.valueOf(binder.getField(ConstantsUtils.FORMULA_NO).getValue()).trim() + "'";
        resultList = (List) HelperTableLocalServiceUtil.executeSelectQuery(noQuery);
        if (Integer.valueOf(String.valueOf(resultList.get(0))) > 0) {
            return "NOEXIST";
        }
        return "S";
    }
    public void update(String query) {
        if (StringUtils.isNotBlank(query)) {
            RsModelLocalServiceUtil.executeUpdateQuery(query, this, this);
        }
    }
    public boolean isPresent(int nsfId) {
        StringBuilder query = new StringBuilder(StringUtils.EMPTY);
        query.append(" SELECT COUNT(NET_SALES_FORMULA_MASTER_SID) FROM SALES_BASIS_DETAILS");
        query.append(ConstantsUtils.QUERY_NSF).append(nsfId).append(ConstantsUtils.SINGLE_QUOTE);
       
        int count = 0;
        List resultList = (List) HelperTableLocalServiceUtil.executeSelectQuery(query.toString());
        if (resultList != null && !resultList.isEmpty()) {
            count = (int) resultList.get(0);
        }
        return count > 0;
    }
}
