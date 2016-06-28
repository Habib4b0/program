/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.netsalesformula.logic;

import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.common.util.HelperListUtil;
import com.stpl.app.global.item.util.CommonUtils;
import com.stpl.app.global.netsalesformula.dto.NetSalesRuleLookupDto;
import com.stpl.app.global.netsalesformula.dto.SalesBasisDto;
import com.stpl.app.global.netsalesformula.ui.form.SalesBasis;
import com.stpl.app.model.ImtdSalesBasisDetails;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.ImtdSalesBasisDetailsLocalServiceUtil;
import com.stpl.app.service.RsModelLocalServiceUtil;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.HelperUtils;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.util.dao.orm.CustomSQLUtil;
import com.vaadin.data.Container;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.server.VaadinSession;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
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
 *
 * @author Asha
 */
public class SalesLogic {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(SalesLogic.class);
    HelperListUtil helperListUtil = HelperListUtil.getInstance();
    public static ResourceBundle constantProperties = ResourceBundle.getBundle("properties.constants");
    static HashMap<String, String> criteria = new HashMap<>();
    SessionDTO sessiondto;
    static HashMap<String, String> availCriteria = new HashMap<>();
    static HashMap<String, String> customerCriteria = new HashMap<>();
    static HashMap<String, String> customerOrder = new HashMap<>();
    
    public int getCount(ErrorfulFieldGroup binder, SalesBasis obj, int start, int offset, final boolean isCount, final List<SortByColumn> columns, final Set<Container.Filter> filterSet) throws Exception {
        int count;
        count = (Integer) loadSales(binder, obj, start, offset, isCount, columns, filterSet);
        return count;
    }

    public List getSearchResults(ErrorfulFieldGroup binder, SalesBasis obj, int start, int offset, final boolean isCount, final List<SortByColumn> columns, final Set<Container.Filter> filterSet) throws Exception {
        List list;
        list = (List) loadSales(binder, obj, start, offset, isCount, columns, filterSet);
        return list;
    }

    public Object loadSales(ErrorfulFieldGroup binder, SalesBasis obj, int start, int offset, final boolean isCount, final List<SortByColumn> columns, final Set<Container.Filter> filterSet) throws Exception {

        Object object;
        if (isCount) {
            object = getResultsForContract(binder, start, start + offset, columns, filterSet, true);
        } else {
            object = getResultsForContract(binder, start, start + offset, columns, filterSet, false);
        }
        return object;
    }

    /**
     *
     * @param searchContractForm
     * @param start
     * @param end
     * @param sortByColumns
     * @param filterSet
     * @return
     * @throws SystemException
     * @throws PortalException
     * @throws Exception
     */
    public Object getResultsForContract(final ErrorfulFieldGroup searchContractForm, final int start, final int end, final List<SortByColumn> sortByColumns, final Set<Filter> filterSet, boolean isCount) throws SystemException,
            PortalException, Exception {
        Map<String, Object> parameters =getDisplaySearchMap(searchContractForm, null);
        Object object = new Object();
        String sql = getQueryForContractMaster(sortByColumns,start, end, parameters, isCount,filterSet);
        List resultList = (List) CompanyMasterLocalServiceUtil.executeSelectQuery(sql, null, null);
        if (isCount) {
            object = (Integer) resultList.get(0);

        } else {
            object = getCustomizedSearchFormFromObject(resultList);
        }

        return object;
    }

    public String getQueryForContractMaster(final List<SortByColumn> sortByColumns, Object index, Object next, Map<String, Object> parameters, boolean isCount, final Set<Filter> filterSet) {
        try {
            String sql;
            String andOperator = StringUtils.EMPTY;
            String contractNo = parameters.get(ConstantsUtils.CONTRACT_NO_FILTER).toString();
            String contractName = parameters.get(ConstantsUtils.CONTRACT_NAME_FILTER).toString();
            String contractHolder = parameters.get(ConstantsUtils.CONTRACT_HOLDER_FILTER).toString();
            String cfpNo = parameters.get(ConstantsUtils.CFP_NO_FILTER).toString();
            String cfpName = parameters.get(ConstantsUtils.CFP_NAME_FILTER).toString();
            String ifpNo = parameters.get(ConstantsUtils.IFP_NO_FILTER).toString();
            String ifpName = parameters.get(ConstantsUtils.IFP_NAME_FILTER).toString();
            String companyNo = parameters.get(ConstantsUtils.COMPANY_NO_FILTER).toString();
            String companyName = parameters.get(ConstantsUtils.COMPANY_NAME_FILTER).toString();
            String itemNo = parameters.get(ConstantsUtils.ITEM_NO_FILTER).toString();
            String itemName = parameters.get(ConstantsUtils.ITEM_NAME_FILTER).toString();
            String marketType = parameters.get(ConstantsUtils.MARKET_TYPE_FILTER).toString();

            if (isCount) {
                sql = CustomSQLUtil.get("availableContractsCount");
            } else {
                sql = CustomSQLUtil.get("availableContracts");
            }
            sql = sql.replaceFirst(ConstantsUtils.QUESTION_MARK, contractHolder);
            sql = sql.replaceFirst(ConstantsUtils.QUESTION_MARK, contractNo);
            sql = sql.replaceFirst(ConstantsUtils.QUESTION_MARK, contractName);
            sql = sql.replaceFirst(ConstantsUtils.QUESTION_MARK, cfpNo);
            sql = sql.replaceFirst(ConstantsUtils.QUESTION_MARK, cfpName);
            sql = sql.replaceFirst(ConstantsUtils.QUESTION_MARK, ifpNo);
            sql = sql.replaceFirst(ConstantsUtils.QUESTION_MARK, ifpName);
            sql = sql.replaceFirst(ConstantsUtils.QUESTION_MARK, companyNo);
            sql = sql.replaceFirst(ConstantsUtils.QUESTION_MARK, companyName);
            sql = sql.replaceFirst(ConstantsUtils.QUESTION_MARK, itemNo);
            sql = sql.replaceFirst(ConstantsUtils.QUESTION_MARK, itemName);

            if (!isCount) {
                if (!ConstantsUtils.PERCENCTAGE.equals(contractHolder)) {
                    sql += "AND CON_HOL.COMPANY_NAME LIKE '" + contractHolder + "'";
                }
                if (!ConstantsUtils.ZERO.equals(marketType)) {
                    sql += "  AND H.HELPER_TABLE_SID = '" + marketType + "'";
                }
                
                sql += getAvailContractFilterQuery(filterSet, new StringBuilder());

                boolean sortOrder = false;
                String orderByColumn = null;
                if (sortByColumns != null) {
                    for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                        final SortByColumn sortByColumn = (SortByColumn) iterator.next();
                        orderByColumn = availCriteria.get(sortByColumn.getName());
                        sortOrder = sortByColumn.getType() != SortByColumn.Type.ASC;
                    }
                }
                if (orderByColumn == null || StringUtils.EMPTY.equals(orderByColumn)) {
                    sql += " ORDER BY cm.CONTRACT_MASTER_SID" + ((!sortOrder) ? " ASC " : " DESC ");
                } else {
                    sql += "ORDER BY " + (orderByColumn) + ((!sortOrder) ? " ASC " : " DESC ");
                }
              
                sql += (" " + "OFFSET ");
                sql += (index);
                sql += (" ROWS FETCH NEXT ");
                sql += (((Integer) next - (Integer) index));
                sql += (" ROWS ONLY;");
            } else {
                if (!ConstantsUtils.PERCENCTAGE.equals(contractHolder)) {
                    sql += "AND CON_HOL.COMPANY_NAME LIKE '" + contractHolder + "'"; 
                }
                
                sql +=getAvailContractFilterQuery(filterSet, new StringBuilder());
                  
                if (!ConstantsUtils.ZERO.equals(marketType)) {
                    sql += "  AND H.HELPER_TABLE_SID = '" + marketType + "') A";
                } else {
                    sql += " ) A";
                }

               
                
            }
            return sql;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Gets the customized list of SearchCompanyForm from list of CompanyMaster.
     *
     * @param list the list
     * @return the customized search form from object
     * @throws Exception the exception
     */
    public List<SalesBasisDto> getCustomizedSearchFormFromObject(final List list) throws Exception {
        try {
            final List<SalesBasisDto> searchItemList = new ArrayList<SalesBasisDto>();

            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    final SalesBasisDto searchCompanyForm = new SalesBasisDto();
                    final Object[] obj = (Object[]) list.get(i);
                    final Integer systemId = (Integer) obj[0];
                    searchCompanyForm.setContractMasterSid(String.valueOf(systemId));
                    searchCompanyForm.setContractNo(HelperUtils.getString(obj[1]));
                    searchCompanyForm.setContractName(HelperUtils.getString(obj[2]));
                    if (obj[3] != null && StringUtils.isNotBlank(obj[3].toString()) && (Integer) obj[3] != 0) {
                        searchCompanyForm.setMarketType(helperListUtil.getIdHelperDTOMap().get(obj[3] != null ? Integer.valueOf(String.valueOf(obj[3])) : 0));
                    }

                    searchCompanyForm.setCompanyFamilyPlanName(HelperUtils.getString(obj[4]));

                    searchCompanyForm.setItemFamilyPlanName(HelperUtils.getString(obj[5]));
                    searchCompanyForm.setCompanyNo(HelperUtils.getString(obj[6]));
                    searchCompanyForm.setCompanyName(HelperUtils.getString(obj[7]));
                    searchCompanyForm.setContractHolder(HelperUtils.getString(obj[8]));
                    searchCompanyForm.setItemNo(HelperUtils.getString(obj[9]));
                    searchCompanyForm.setItemName(HelperUtils.getString(obj[10]));
                    searchCompanyForm.setPriceScheduleName(HelperUtils.getString(obj[11]));
                    searchCompanyForm.setCompanyFamilyPlanNo(HelperUtils.getString(obj[12]));
                    searchCompanyForm.setItemFamilyPlanNo(HelperUtils.getString(obj[13]));
                    searchCompanyForm.setPriceScheduleNo(HelperUtils.getString(obj[14]));
                    searchCompanyForm.setDeductionNo(HelperUtils.getString(obj[15]));
                    searchCompanyForm.setDeductionName(HelperUtils.getString(obj[16]));

                    searchItemList.add(searchCompanyForm);
                }
            }
            return searchItemList;
        } catch (Exception e) {
            return null;
        }
    }
    
    public int getCustomerCount(int start, int offset, final boolean isCount, final List<SortByColumn> columns, final Set<Container.Filter> filterSet, final String contractMasterSid) throws Exception {
        int count;
        count = (Integer) loadCustomers(start, offset, isCount, columns, filterSet, contractMasterSid);
        return count;
    }

    public List getCustomerSearchResults(int start, int offset, final boolean isCount, final List<SortByColumn> columns, final Set<Container.Filter> filterSet, final String contractMasterSid) throws Exception {
        List list;
        list = (List) loadCustomers(start, offset, isCount, columns, filterSet,contractMasterSid);
        return list;
    }

    public Object loadCustomers(int start, int offset, final boolean isCount, final List<SortByColumn> columns, final Set<Container.Filter> filterSet, final String contractMasterSid) throws Exception {
        Object object;
        if (isCount) {
            object = getResultsForCustomer(start, start + offset, columns, filterSet, true, contractMasterSid);
        } else {
            object = getResultsForCustomer(start, start + offset, columns, filterSet, false, contractMasterSid);
        }
        return object;
    }
    public Object getResultsForCustomer(final int start, final int end, final List<SortByColumn> sortByColumns, final Set<Filter> filterSet, boolean isCount, final String contractMasterSid) throws SystemException,
            PortalException, Exception {
        Object object = new Object();
        String sql = getQueryForCustomer(sortByColumns, start, end, contractMasterSid,isCount,filterSet);
        List resultList = (List) CompanyMasterLocalServiceUtil.executeSelectQuery(sql, null, null);
        if (isCount) {
            object = (Integer) resultList.get(0);
        } else {
            object = getCustomizedCustomerFormFromObject(resultList);
        }
        return object;
    }
    
    public String getQueryForCustomer(final List<SortByColumn> sortByColumns, Object index, Object next, String contractMasterSid, boolean isCount, final Set<Filter> filterSet) {
        try {
            String sql;
           
            if (isCount) {
                sql = CustomSQLUtil.get("availableCustomersCount");
            } else {
                sql = CustomSQLUtil.get("availableCustomers");
            }
            sql = sql.replaceFirst(ConstantsUtils.QUESTION_MARK, contractMasterSid);

            sql =  sql.replace("?Filter", getAvailCustomerFilterQuery(filterSet, new StringBuilder()));
            
            if (!isCount) {
                if (customerOrder.isEmpty()) {
                customerOrder.clear();
                customerOrder.put(ConstantsUtils.COMPANY_NO, "CUSTOMER_NO");
                customerOrder.put(ConstantsUtils.COMPANY_NAME, "CUSTOMER_NAME");
                customerOrder.put(ConstantsUtils.CONTRACT_NO, "CONTRACT_NO");
                customerOrder.put(ConstantsUtils.CONTRACT_NAME, "CONTRACT_NAME");
                customerOrder.put(ConstantsUtils.CFP_NO, "CFP_NO");
                customerOrder.put(ConstantsUtils.CFP_NAME, "CFP_NAME");
                  }
                  boolean sortOrder = false;
                String orderByColumn = null;
                if (sortByColumns != null) {
                    for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                        final SortByColumn sortByColumn = (SortByColumn) iterator.next();
                        orderByColumn = customerOrder.get(sortByColumn.getName());
                        sortOrder = sortByColumn.getType() != SortByColumn.Type.ASC;
                    }
                }
               
                if (orderByColumn == null || StringUtils.EMPTY.equals(orderByColumn)) {
                    sql += " ORDER BY CUSTOMER_NAME" + ((!sortOrder) ? " ASC " : " DESC ");
                } else {
                    sql += "ORDER BY " + (orderByColumn) + ((!sortOrder) ? " ASC " : " DESC ");
                }

                sql += (" " + "OFFSET ");
                sql += (index);
                sql += (" ROWS FETCH NEXT ");
                sql += (((Integer) next - (Integer) index));
                sql += (" ROWS ONLY;");
            } 
            return sql;
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Gets the customized list of SearchCompanyForm from list of CompanyMaster.
     *
     * @param list the list
     * @return the customized search form from object
     * @throws Exception the exception
     */
    public List<SalesBasisDto> getCustomizedCustomerFormFromObject(final List list) throws Exception {
        try {
            final List<SalesBasisDto> searchItemList = new ArrayList<SalesBasisDto>();
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    final SalesBasisDto searchCompanyForm = new SalesBasisDto();
                    final Object[] obj = (Object[]) list.get(i);
                    
                    searchCompanyForm.setCompanyNo(HelperUtils.getString(obj[0]));
                    searchCompanyForm.setCompanyName(HelperUtils.getString(obj[1]));
                    searchCompanyForm.setContractNo(HelperUtils.getString(obj[2]));
                    searchCompanyForm.setContractName(HelperUtils.getString(obj[3]));
                    searchCompanyForm.setCompanyFamilyPlanNo(HelperUtils.getString(obj[4]));
                    searchCompanyForm.setCompanyFamilyPlanName(HelperUtils.getString(obj[5]));
                    final Integer systemId = (Integer) obj[6];
                    searchCompanyForm.setContractMasterSid(String.valueOf(systemId));
                    final Integer contractSid = (Integer) obj[7];
                    searchCompanyForm.setCfpContractDetailsSid(String.valueOf(contractSid));

                    searchItemList.add(searchCompanyForm);
                }
            }
            return searchItemList;
        } catch (Exception e) {
            return null;
        }
    }
       
    public void addToTempTable(SalesBasisDto item, SessionDTO sessiondto, NetSalesRuleLookupDto ruleDto,StringBuilder insertQuery) throws SystemException {
        
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));

        if (!StringUtils.EMPTY.equals(item.getContractMasterSid())) {
         
            insertQuery.append("INSERT INTO dbo.IMTD_SALES_BASIS_DETAILS (SALES_BASIS_DETAILS_SID, NET_SALES_FORMULA_MASTER_SID, CONTRACT_MASTER_SID, CONTRACT_NO, CONTRACT_NAME, CFP_CONTRACT_DETAILS_SID, CFP_NO, CFP_NAME, CUSTOMER_NO, CUSTOMER_NAME, CDR_MODEL_SID, CHECK_RECORD, INBOUND_STATUS, USERS_SID, SESSION_ID, IMTD_CREATED_DATE, \"OPERATION\", CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE) "
                    + "	VALUES (0, 0,");

            insertQuery.append(item.getContractMasterSid()).append(",'");
            insertQuery.append(item.getContractNo()).append("','");
            insertQuery.append(item.getContractName()).append("',");
            insertQuery.append(item.getCfpContractDetailsSid()).append(",'");
            insertQuery.append(item.getCompanyFamilyPlanNo()).append("','");

            insertQuery.append(item.getCompanyFamilyPlanName()).append("','");
            insertQuery.append(item.getCompanyNo()).append("','");
            insertQuery.append(item.getCompanyName()).append("',");
            insertQuery.append(StringUtils.isNotBlank(ruleDto.getRuleSystemId()) ? ruleDto.getRuleSystemId() : "NULL").append(",");
            insertQuery.append("'false','");
            insertQuery.append(ConstantsUtils.A).append("',");
            insertQuery.append("'").append(userId).append("',");
            insertQuery.append("'").append(sessiondto.getUiSessionId()).append("',");
            insertQuery.append("CURRENT_TIMESTAMP,'");
            insertQuery.append(ConstantsUtils.A).append("','");
            insertQuery.append(userId).append("',");
            insertQuery.append("CURRENT_TIMESTAMP,'");
            insertQuery.append(userId).append("',");
            insertQuery.append("CURRENT_TIMESTAMP);");
           
    }
    }
    
    
       public int tempTableCount(final Set<Container.Filter> filterSet, SessionDTO sessionDTO) throws Exception {
        try {
        int count = 0;
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder = buildSearchQuery(true,sessionDTO);
        queryBuilder = getFilterQuery(filterSet, queryBuilder);

        List<Object> masterData = (List<Object>) RsModelLocalServiceUtil.executeSelectQuery(queryBuilder.toString(), StringUtils.EMPTY, StringUtils.EMPTY);
        if (masterData != null && !masterData.isEmpty()) {
            Object ob = masterData.get(0);
            count += Integer.valueOf(String.valueOf(ob));
        }
        LOGGER.info("\n count===>" + count);
        return count;
        } catch (Exception e) {
            return 0;
        }
    }

    public List<SalesBasisDto> tempTableResults(final SessionDTO sessionDTO,
            final int start, final int end, final List<SortByColumn> columns, final Set<Container.Filter> filterSet) throws SystemException, Exception {
        List<SalesBasisDto> searchList = new ArrayList<>();
        LOGGER.info("Entering tempTableResults with start of=" + start + "and endIndex of= " + end + "  Column Size +" + ((columns == null) ? columns : columns.size()));
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder = buildSearchQuery(false,sessionDTO);
        queryBuilder = getFilterQuery(filterSet, queryBuilder);
        queryBuilder = getOrderQuery(queryBuilder, columns, start, end);
        final List list = (List) RsModelLocalServiceUtil.executeSelectQuery(queryBuilder.toString(), StringUtils.EMPTY, StringUtils.EMPTY);
        searchList = getCustomizedSearchFormToDTO(list);
        return searchList;
    }

    private void loadCriteriaInMap() {
        criteria.clear();
        criteria.put(ConstantsUtils.CONTRACT_NO, "CONTRACT_NO");
        criteria.put(ConstantsUtils.CONTRACT_NAME, "CONTRACT_NAME");
        criteria.put(ConstantsUtils.CFP_NO, "CFP_NO");
        criteria.put(ConstantsUtils.CFP_NAME, "CFP_NAME");
        criteria.put(ConstantsUtils.COMPANY_NO, "CUSTOMER_NO");
        criteria.put(ConstantsUtils.COMPANY_NAME, "CUSTOMER_NAME");
        
    }

    private StringBuilder buildSearchQuery(boolean isCount,SessionDTO sessionDTO) {
        StringBuilder queryBuilder = new StringBuilder();
        String query = isCount ? "COUNT( * )" : constantProperties.getString("nsfSalesBasisTemp");
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        int user = Integer.valueOf(userId);
        if(isCount){
            queryBuilder.append(" SELECT ").append(query).append(" FROM IMTD_SALES_BASIS_DETAILS im\n" +
        " LEFT JOIN DBO.CDR_MODEL cdr ON cdr.CDR_MODEL_SID = im.CDR_MODEL_SID WHERE USERS_SID ='").append(user).append("' AND SESSION_ID='").append(sessionDTO.getUiSessionId()).append("' AND INBOUND_STATUS <> 'D' AND \"OPERATION\" <> 'D'");
        }else{
        queryBuilder.append(" SELECT ").append(query).append(" FROM IMTD_SALES_BASIS_DETAILS im\n" +
        " LEFT JOIN DBO.CDR_MODEL cdr ON cdr.CDR_MODEL_SID = im.CDR_MODEL_SID WHERE USERS_SID ='").append(user).append("' AND SESSION_ID='").append(sessionDTO.getUiSessionId()).append("' AND INBOUND_STATUS <> 'D' AND \"OPERATION\" <> 'D'");
        }
        return queryBuilder;
    }

    private List<SalesBasisDto> getCustomizedSearchFormToDTO(List list) {
        final List<SalesBasisDto> searchResultsList = new ArrayList<>();
        try {
            if (list != null) {                
                for (Object nsfList : list) {
                    final SalesBasisDto searchDTO = new SalesBasisDto();
                    final Object[] object = (Object[]) nsfList;
                    searchDTO.setCompanyName(!ConstantsUtils.NULL.equals(String.valueOf(object[9])) && StringUtils.isNotBlank(String.valueOf(object[9])) ? String.valueOf(object[9]) : StringUtils.EMPTY);
                    searchDTO.setCompanyNo(!ConstantsUtils.NULL.equals(String.valueOf(object[8])) && StringUtils.isNotBlank(String.valueOf(object[8])) ? String.valueOf(object[8]) : StringUtils.EMPTY);
                    searchDTO.setContractNo(!ConstantsUtils.NULL.equals(String.valueOf(object[3])) && StringUtils.isNotBlank(String.valueOf(object[3])) ? String.valueOf(object[3]) : StringUtils.EMPTY);
                    searchDTO.setContractName(!ConstantsUtils.NULL.equals(String.valueOf(object[4])) && StringUtils.isNotBlank(String.valueOf(object[4])) ? String.valueOf(object[4]) : StringUtils.EMPTY);
                    searchDTO.setCompanyFamilyPlanNo(!ConstantsUtils.NULL.equals(String.valueOf(object[6])) && StringUtils.isNotBlank(String.valueOf(object[6])) ? String.valueOf(object[6]) : StringUtils.EMPTY);
                    searchDTO.setCompanyFamilyPlanName(!ConstantsUtils.NULL.equals(String.valueOf(object[7])) && StringUtils.isNotBlank(String.valueOf(object[7])) ? String.valueOf(object[7]) : StringUtils.EMPTY);
                    final Integer systemId = (Integer) object[2];
                    searchDTO.setContractMasterSid(String.valueOf(systemId));
                    searchDTO.setAvailableCheck((Boolean)object[11]);
                    searchDTO.setImtdSalesBasisDetailsSid((Integer) object[23]);
                    final Integer crdModelSid = (Integer) object[10];
                    searchDTO.setCrdModelSid(String.valueOf(crdModelSid));
                    searchDTO.setNetSalesRuleNo(!ConstantsUtils.NULL.equals(String.valueOf(object[21])) && StringUtils.isNotBlank(String.valueOf(object[21])) ? String.valueOf(object[21]) : StringUtils.EMPTY);
                    searchDTO.setNetSalesRuleName(!ConstantsUtils.NULL.equals(String.valueOf(object[22])) && StringUtils.isNotBlank(String.valueOf(object[22])) ? String.valueOf(object[22]) : StringUtils.EMPTY);
                    searchDTO.setCreatedBy(!ConstantsUtils.NULL.equals(String.valueOf(object[17])) && StringUtils.isNotBlank(String.valueOf(object[17])) ? String.valueOf(object[17]) : StringUtils.EMPTY);
                    final Integer cfpContractSid = (Integer) object[5];
                    searchDTO.setCfpContractDetailsSid(String.valueOf(cfpContractSid));
                    searchDTO.setRuleType(helperListUtil.getIdHelperDTOMap().get(object[24] != null ? Integer.valueOf(String.valueOf(object[24])) : 0));
                    searchDTO.setRuleCategory(helperListUtil.getIdHelperDTOMap().get(object[25] != null ? Integer.valueOf(String.valueOf(object[25])) : 0));
                    searchResultsList.add(searchDTO);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return searchResultsList;
    }

    private StringBuilder getFilterQuery(final Set<Container.Filter> filterSet, final StringBuilder stringBuilder) throws ParseException {
        if (criteria.isEmpty()) {
            loadCriteriaInMap();
        }
        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    stringBuilder.append(" AND ").append(criteria.get(stringFilter.getPropertyId().toString())).append(" LIKE '%").append(stringFilter.getFilterString()).append("%'");
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
                orderByColumn = criteria.get(sortByColumn.getName());
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

     public void removeFromTempTable(SessionDTO sessiondto) throws SystemException, PortalException {
         StringBuilder query = new StringBuilder(StringUtils.EMPTY);
         query.append("UPDATE IMTD_SALES_BASIS_DETAILS SET \"OPERATION\" = 'D',INBOUND_STATUS = 'D'");
         query.append(" WHERE CHECK_RECORD = 1 ");
         query.append(" AND SESSION_ID = '").append(sessiondto.getUiSessionId()).append("'");
         CompanyMasterLocalServiceUtil.executeUpdateQuery(query.toString());

    }
     
     public void updateTempTable(boolean checkValue, SalesBasisDto dto, String sessionId) {
         StringBuilder query = new StringBuilder(StringUtils.EMPTY);
         query.append("UPDATE IMTD_SALES_BASIS_DETAILS SET CHECK_RECORD = ").append(checkValue ? 1:0);
         query.append(" WHERE IMTD_SALES_BASIS_DETAILS_SID = ").append(dto.getImtdSalesBasisDetailsSid());
         CompanyMasterLocalServiceUtil.executeUpdateQuery(query.toString());
     }
     
     public void checkAllSalesBasisTempTable(boolean checkValue, SalesBasisDto dto, String sessionId) {
         StringBuilder query = new StringBuilder(StringUtils.EMPTY);
         query.append("UPDATE IMTD_SALES_BASIS_DETAILS SET CHECK_RECORD = ").append(checkValue ? 1:0);
         query.append(" WHERE SESSION_ID = '").append(sessionId).append("' AND OPERATION <> 'D'");
         CompanyMasterLocalServiceUtil.executeUpdateQuery(query.toString());
     }
     
     public void updateRuleToTempTable(String ruleSystemId,SalesBasisDto salesDto,  String sessionId) {
         StringBuilder query = new StringBuilder(StringUtils.EMPTY);
         query.append("UPDATE IMTD_SALES_BASIS_DETAILS SET CDR_MODEL_SID = ").append("0".equals(ruleSystemId)?null:ruleSystemId);
         query.append(" WHERE IMTD_SALES_BASIS_DETAILS_SID = ").append(salesDto.getImtdSalesBasisDetailsSid());
         CompanyMasterLocalServiceUtil.executeUpdateQuery(query.toString());
     }
     
     public static Boolean isChecked(final String userId, final String sessionId){
         StringBuilder query = new StringBuilder(StringUtils.EMPTY);
         query.append("SELECT COUNT(SALES_BASIS_DETAILS_SID) FROM dbo.IMTD_SALES_BASIS_DETAILS WHERE CHECK_RECORD = 1 ");
         query.append(" AND USERS_SID =").append(userId).append(" AND SESSION_ID = '").append(sessionId).append("' AND OPERATION <> 'D';");
         List list = HelperTableLocalServiceUtil.executeSelectQuery(query.toString());
         return Integer.valueOf(String.valueOf(list.get(0))) > 0;
     }
    
     public void populateLogic(Object... receivedObj) {
        List convertedList = Arrays.asList(receivedObj);
        final String userId = (String) convertedList.get(0);
        final String sessionId = (String) convertedList.get(1);
        final String createdDate = (String) convertedList.get(2);
        final String populateField = (String) convertedList.get(3);
        final String populateValue = (String) convertedList.get(4);
        final SalesBasisDto dto = (SalesBasisDto) convertedList.get(5); 
        updateForPopulate(userId, sessionId, createdDate,populateField, populateValue, dto);
    }

    public void updateForPopulate(String userId, String sessionId, String createdDate,Object populateField, Object populateValue, SalesBasisDto searchForm) {
        try {
            StringBuilder query = new StringBuilder();
            query.append("UPDATE IMTD_SALES_BASIS_DETAILS SET ");
            query.append(populateField);
            query.append("=");
            query.append(populateValue);
            query.append(" WHERE USERS_SID='");
            query.append(userId);
            query.append("' AND SESSION_ID='");
            query.append(sessionId);
            query.append("' AND INBOUND_STATUS <> 'D' AND \"OPERATION\" NOT IN ('D') AND CHECK_RECORD = 1;");
            CompanyMasterLocalServiceUtil.executeUpdateQuery(query.toString());
        } catch (Exception e) {
            LOGGER.error(e);
        } 
    }
    public void removeAll(SessionDTO sessDto) throws SystemException, PortalException {
//
        StringBuilder query = new StringBuilder(StringUtils.EMPTY);
        query.append("DELETE FROM IMTD_SALES_BASIS_DETAILS ");
        query.append(" WHERE SESSION_ID = '").append(String.valueOf(sessDto.getUiSessionId())).append("'");
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        query.append(" AND USERS_SID='");
        query.append(userId).append("'");
        CompanyMasterLocalServiceUtil.executeUpdateQuery(query.toString());



    }
    
    private String getAvailContractFilterQuery(final Set<Container.Filter> filterSet, final StringBuilder stringBuilder) throws ParseException {
       if(availCriteria.isEmpty()){
           loadavailCriteriaMap();
       }
        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    stringBuilder.append(" AND ").append(availCriteria.get(stringFilter.getPropertyId().toString())).append(" LIKE '%").append(stringFilter.getFilterString()).append("%'");
                }
            }
        }
        return stringBuilder.toString();
    }
    private void loadavailCriteriaMap() {
        availCriteria.clear();
        availCriteria.put(ConstantsUtils.CONTRACT_NO, "CM.CONTRACT_NO");
        availCriteria.put(ConstantsUtils.CONTRACT_NAME, "CM.CONTRACT_NAME");
        availCriteria.put(ConstantsUtils.CONTRACT_HOLDER, "CON_HOL.COMPANY_NAME");
        availCriteria.put(ConstantsUtils.MARKET_TYPE, "H.HELPER_TABLE_SID");
        availCriteria.put(ConstantsUtils.CFP_NO, "CFPM.CFP_NO");
        availCriteria.put(ConstantsUtils.CFP_NAME, "CFP.CFP_NAME");
        availCriteria.put("itemFamilyPlanNo", "IFPM.IFP_NO");
        availCriteria.put("itemFamilyPlanName", "IFP.IFP_NAME");
        availCriteria.put("priceScheduleNo", "PSM.PS_NO");
        availCriteria.put("priceScheduleName", "PS.PS_NAME");
        availCriteria.put("deductionNo", "RS.RS_NO");
        availCriteria.put("deductionName", "RS.RS_NAME");

    }
    
        public boolean addDuplicateValidation(final SalesBasisDto sales, final SessionDTO dto) throws SystemException, PortalException {
            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdSalesBasisDetails.class);
            dynamicQuery.add(RestrictionsFactoryUtil.eq("contractMasterSid", Integer.valueOf(sales.getContractMasterSid())));
            dynamicQuery.add(RestrictionsFactoryUtil.eq("cfpContractDetailsSid", Integer.valueOf(sales.getCfpContractDetailsSid())));
            dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.USERS_ID, Integer.valueOf(userId)));
            dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.SESSION_ID, dto.getUiSessionId()));
            dynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.OPERATION, ConstantsUtils.D));
            dynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.D));
            final long count = ImtdSalesBasisDetailsLocalServiceUtil.dynamicQueryCount(dynamicQuery);
           
            return (count == 0);
    }
    private String getAvailCustomerFilterQuery(final Set<Container.Filter> filterSet, final StringBuilder stringBuilder) throws ParseException {
        stringBuilder.append(" ");
        if (customerCriteria.isEmpty()) {
            loadcustomerCriteriaMap();
        }
        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    stringBuilder.append("AND ").append(customerCriteria.get(stringFilter.getPropertyId().toString())).append(" LIKE '%").append(stringFilter.getFilterString()).append("%'");
                }
            }
        }
        return stringBuilder.toString();
    }

    private void loadcustomerCriteriaMap() {
        customerCriteria.clear();
        customerCriteria.put(ConstantsUtils.COMPANY_NO, "COMPANY_NO");
        customerCriteria.put(ConstantsUtils.COMPANY_NAME, "COMPANY_NAME");
        customerCriteria.put(ConstantsUtils.CONTRACT_NO, "CONTRACT_NO");
        customerCriteria.put(ConstantsUtils.CONTRACT_NAME, "CONTRACT_NAME");
        customerCriteria.put(ConstantsUtils.CFP_NO, "CFP_NO");
        customerCriteria.put(ConstantsUtils.CFP_NAME, "CFP_NAME");

    }
    
    public Map<String, Object> getDisplaySearchMap(ErrorfulFieldGroup searchContractForm, Map displaySearchMap) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        if (displaySearchMap == null) {
            displaySearchMap = new HashMap();
        }

        String contractNo = StringUtils.EMPTY;
        String contractName = StringUtils.EMPTY;
        String contractHolder = StringUtils.EMPTY;
        String marketType = StringUtils.EMPTY;
        String cfpNo = StringUtils.EMPTY;
        String cfpName = StringUtils.EMPTY;
        String ifpNo = StringUtils.EMPTY;
        String ifpName = StringUtils.EMPTY;
        String companyNo = StringUtils.EMPTY;
        String companyName = StringUtils.EMPTY;
        String itemNo = StringUtils.EMPTY;
        String itemName = StringUtils.EMPTY;
        if (searchContractForm.getField(ConstantsUtils.CONTRACT_NO).getValue().toString() != null && StringUtils.isNotBlank(searchContractForm.getField(ConstantsUtils.CONTRACT_NO).getValue().toString())) {
            contractNo = searchContractForm.getField(ConstantsUtils.CONTRACT_NO).getValue().toString().trim();
            displaySearchMap.put(ConstantsUtils.CONTRACT_NO, contractNo);
            contractNo = contractNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put(ConstantsUtils.CONTRACT_NO_FILTER, contractNo);
        } else {
            parameters.put(ConstantsUtils.CONTRACT_NO_FILTER, ConstantsUtils.PERCENCTAGE);
            displaySearchMap.put(ConstantsUtils.CONTRACT_NO, StringUtils.EMPTY);
        }

        if (searchContractForm.getField(ConstantsUtils.CONTRACT_NAME).getValue().toString() != null && StringUtils.isNotBlank(searchContractForm.getField(ConstantsUtils.CONTRACT_NAME).getValue().toString())) {

            contractName = searchContractForm.getField(ConstantsUtils.CONTRACT_NAME).getValue().toString().trim();
            displaySearchMap.put(ConstantsUtils.CONTRACT_NAME, contractNo);
            contractName = contractName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put(ConstantsUtils.CONTRACT_NAME_FILTER, contractName);
        } else {
            parameters.put(ConstantsUtils.CONTRACT_NAME_FILTER, ConstantsUtils.PERCENCTAGE);
            displaySearchMap.put(ConstantsUtils.CONTRACT_NAME, StringUtils.EMPTY);
        }
        if (searchContractForm.getField(ConstantsUtils.CONTRACT_HOLDER).getValue().toString() != null && StringUtils.isNotBlank(searchContractForm.getField(ConstantsUtils.CONTRACT_HOLDER).getValue().toString())) {
            contractHolder = searchContractForm.getField(ConstantsUtils.CONTRACT_HOLDER).getValue().toString().trim();
            displaySearchMap.put(ConstantsUtils.CONTRACT_HOLDER, contractHolder);
            contractHolder = contractHolder.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put(ConstantsUtils.CONTRACT_HOLDER_FILTER, contractHolder);
        } else {
            parameters.put(ConstantsUtils.CONTRACT_HOLDER_FILTER, ConstantsUtils.PERCENCTAGE);
            displaySearchMap.put(ConstantsUtils.CONTRACT_HOLDER, StringUtils.EMPTY);

        }
        if (searchContractForm.getField(ConstantsUtils.CFP_NO).getValue().toString() != null && StringUtils.isNotBlank(searchContractForm.getField(ConstantsUtils.CFP_NO).getValue().toString())) {
            cfpNo = searchContractForm.getField(ConstantsUtils.CFP_NO).getValue().toString().trim();
            displaySearchMap.put(ConstantsUtils.CFP_NO, cfpNo);
            cfpNo = cfpNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put(ConstantsUtils.CFP_NO_FILTER, cfpNo);
        } else {
            parameters.put(ConstantsUtils.CFP_NO_FILTER, ConstantsUtils.PERCENCTAGE);
            displaySearchMap.put(ConstantsUtils.CFP_NO, StringUtils.EMPTY);
        }
        if (searchContractForm.getField(ConstantsUtils.CFP_NAME).getValue().toString() != null && StringUtils.isNotBlank(searchContractForm.getField(ConstantsUtils.CFP_NAME).getValue().toString())) {
            cfpName = searchContractForm.getField(ConstantsUtils.CONTRACT_HOLDER).getValue().toString().trim();
            displaySearchMap.put(ConstantsUtils.CFP_NAME, cfpName);
            cfpName = cfpName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put(ConstantsUtils.CFP_NAME_FILTER, cfpName);
        } else {
            parameters.put(ConstantsUtils.CFP_NAME_FILTER, ConstantsUtils.PERCENCTAGE);
            displaySearchMap.put(ConstantsUtils.CFP_NAME, StringUtils.EMPTY);
        }
        if (searchContractForm.getField(ConstantsUtils.ITEM_FAMILY_PLAN_NO).getValue().toString() != null && StringUtils.isNotBlank(searchContractForm.getField(ConstantsUtils.ITEM_FAMILY_PLAN_NO).getValue().toString())) {
            ifpNo = searchContractForm.getField(ConstantsUtils.ITEM_FAMILY_PLAN_NO).getValue().toString().trim();
            displaySearchMap.put(ConstantsUtils.ITEM_FAMILY_PLAN_NO, ifpNo);
            ifpNo = ifpNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put(ConstantsUtils.IFP_NO_FILTER, ifpNo);
        } else {
            parameters.put(ConstantsUtils.IFP_NO_FILTER, ConstantsUtils.PERCENCTAGE);
            displaySearchMap.put(ConstantsUtils.ITEM_FAMILY_PLAN_NO, StringUtils.EMPTY);
        }
        if (searchContractForm.getField(ConstantsUtils.ITEM_FAMILY_PLAN_NAME).getValue().toString() != null && StringUtils.isNotBlank(searchContractForm.getField(ConstantsUtils.ITEM_FAMILY_PLAN_NAME).getValue().toString())) {
            ifpName = searchContractForm.getField(ConstantsUtils.ITEM_FAMILY_PLAN_NAME).getValue().toString().trim();
            displaySearchMap.put(ConstantsUtils.ITEM_FAMILY_PLAN_NAME, ifpNo);
            ifpName = ifpName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put(ConstantsUtils.IFP_NAME_FILTER, ifpName);
        } else {
            parameters.put(ConstantsUtils.IFP_NAME_FILTER, ConstantsUtils.PERCENCTAGE);
            displaySearchMap.put(ConstantsUtils.ITEM_FAMILY_PLAN_NAME, StringUtils.EMPTY);

        }
        if (searchContractForm.getField(ConstantsUtils.COMPANY_NO).getValue().toString() != null && StringUtils.isNotBlank(searchContractForm.getField(ConstantsUtils.COMPANY_NO).getValue().toString())) {
            companyNo = searchContractForm.getField(ConstantsUtils.COMPANY_NO).getValue().toString().trim();
            displaySearchMap.put(ConstantsUtils.COMPANY_NO, companyNo);
            companyNo = companyNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put(ConstantsUtils.COMPANY_NO_FILTER, companyNo);
        } else {
            parameters.put(ConstantsUtils.COMPANY_NO_FILTER, ConstantsUtils.PERCENCTAGE);
            displaySearchMap.put(ConstantsUtils.COMPANY_NO, StringUtils.EMPTY);
        }
        if (searchContractForm.getField(ConstantsUtils.COMPANY_NAME).getValue().toString() != null && StringUtils.isNotBlank(searchContractForm.getField(ConstantsUtils.COMPANY_NAME).getValue().toString())) {
            companyName = searchContractForm.getField(ConstantsUtils.COMPANY_NAME).getValue().toString().trim();
            displaySearchMap.put(ConstantsUtils.COMPANY_NAME, companyNo);
            companyName = companyName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put(ConstantsUtils.COMPANY_NAME_FILTER, companyName);
        } else {
            parameters.put(ConstantsUtils.COMPANY_NAME_FILTER, ConstantsUtils.PERCENCTAGE);
            displaySearchMap.put(ConstantsUtils.COMPANY_NAME, StringUtils.EMPTY);
        }
        if (searchContractForm.getField(ConstantsUtils.ITEM_NO).getValue().toString() != null && StringUtils.isNotBlank(searchContractForm.getField(ConstantsUtils.ITEM_NO).getValue().toString())) {
            itemNo = searchContractForm.getField(ConstantsUtils.ITEM_NO).getValue().toString().trim();
            displaySearchMap.put(ConstantsUtils.ITEM_NO, itemNo);
            itemNo = itemNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put(ConstantsUtils.ITEM_NO_FILTER, itemNo);
        } else {
            parameters.put(ConstantsUtils.ITEM_NO_FILTER, ConstantsUtils.PERCENCTAGE);
            displaySearchMap.put(ConstantsUtils.ITEM_NO, StringUtils.EMPTY);
        }
        if (searchContractForm.getField(ConstantsUtils.ITEM_NAME).getValue().toString() != null && StringUtils.isNotBlank(searchContractForm.getField(ConstantsUtils.ITEM_NAME).getValue().toString())) {
            itemName = searchContractForm.getField(ConstantsUtils.ITEM_NAME).getValue().toString().trim();
            displaySearchMap.put(ConstantsUtils.ITEM_NAME, itemName);
            itemName = itemName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put(ConstantsUtils.ITEM_NAME_FILTER, itemName);
        } else {
            parameters.put(ConstantsUtils.ITEM_NAME_FILTER, ConstantsUtils.PERCENCTAGE);
            displaySearchMap.put(ConstantsUtils.ITEM_NAME, StringUtils.EMPTY);
        }
        if (searchContractForm.getField(ConstantsUtils.MARKET_TYPE).getValue() != null && !ConstantsUtils.SELECT_ONE.equals(String.valueOf(searchContractForm.getField(ConstantsUtils.MARKET_TYPE).getValue()))) {

            final HelperDTO helperDTO = (HelperDTO) searchContractForm.getField(ConstantsUtils.MARKET_TYPE).getValue();
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

                marketType = String.valueOf(helperDTO.getId());
                parameters.put(ConstantsUtils.MARKET_TYPE_FILTER, marketType);
                displaySearchMap.put(ConstantsUtils.MARKET_TYPE, marketType);
            }
        } else {
            parameters.put(ConstantsUtils.MARKET_TYPE_FILTER, ConstantsUtils.ZERO);
            displaySearchMap.put(ConstantsUtils.MARKET_TYPE, ConstantsUtils.ZERO);
        }
        return parameters;
    }
     public  boolean isEmpty(SessionDTO sessDto)
     {
         
         final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
         int user = Integer.valueOf(userId);
         String sql = "select count(*) from (select * from dbo.IMTD_SALES_BASIS_DETAILS  ";

         sql += " WHERE USERS_SID = " + user;
         sql += " AND CHECK_RECORD = 1";
         sql += " AND SESSION_ID like '" + sessDto.getUiSessionId() + "' AND \"OPERATION\" <> 'D') a ";
         List resultList = (List) HelperTableLocalServiceUtil.executeSelectQuery(sql);
       int val=resultList == null ? 0 : Integer.valueOf(String.valueOf(resultList.get(0)));
       return val==0?true:false;
}

}