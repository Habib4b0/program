/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.customergroup.logic;

import com.stpl.app.adminconsole.abstractsearch.dto.SearchResultsDTO;
import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.common.util.AbstractFilterLogic;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

import com.stpl.app.adminconsole.common.util.CommonUtil;
import com.stpl.app.adminconsole.customergroup.dto.CustomerDetailsDTO;
import com.stpl.app.adminconsole.customergroup.dto.CustomerGroupDTO;
import com.stpl.app.adminconsole.dao.CustomerGroupLogicDAO;
import com.stpl.app.adminconsole.dao.impl.CustomerGroupLogicDAOImpl;
import com.stpl.app.adminconsole.itemgroup.util.CommonUtils;
import com.stpl.app.model.CompanyGroup;
import com.stpl.app.model.CompanyGroupDetails;
import com.stpl.app.model.CompanyMaster;
import com.stpl.app.model.HistCompanyGroup;
import com.stpl.app.model.HistCompanyGroupDetails;
import com.stpl.app.model.ProjectionMaster;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.CompanyTradeClassLocalServiceUtil;
import com.stpl.app.service.HistCompanyGroupDetailsLocalServiceUtil;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.QueryReader;
import com.stpl.app.service.CompanyGroupDetailsLocalServiceUtil;
import com.stpl.app.service.CompanyGroupLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.GlobalConstants;
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
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;

/**
 * The Class CustomerGroupLogic.
 *
 * @author nandhakumar
 */
public class CustomerGroupLogic {

    private static final Logger LOGGER = Logger.getLogger(CustomerGroupLogic.class);

    private final CustomerGroupLogicDAO dao = new CustomerGroupLogicDAOImpl();

    static HashMap<String, String> columnNames = new HashMap<String, String>();

    final SimpleDateFormat dateFormat = new SimpleDateFormat(CommonUtils.MMDDYYYY);

    SessionDTO sessionDTO;

    public CustomerGroupLogic(SessionDTO sessionDTO) {
        this.sessionDTO = sessionDTO;
    }

    public CustomerGroupLogic() {

    }

    /**
     * Gets the search results.
     *
     * @param customerGroupForm the customer group form
     * @param version the version
     * @return the search results
     */
    public Object getSearchResults(final ErrorfulFieldGroup customerGroupForm, final String version, final int startIndex, final int endIndex, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet, boolean isCount) {

        LOGGER.debug("Entering getSearchResults with P1:CustomFieldGroup customerGroupForm");
        DynamicQuery projectionDynamicQuery = null;
        Object object = new Object();
        try {
            if (version.equalsIgnoreCase(ConstantsUtils.VERSION_CURRENT)) {
                projectionDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyGroup.class);
            } else if (version.equalsIgnoreCase(ConstantsUtils.VERSION_HIST)) {
                projectionDynamicQuery = DynamicQueryFactoryUtil.forClass(HistCompanyGroup.class);
                projectionDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.PRIMARY_KEY + ConstantsUtils.ACTION_FLAG, "D"));
            }

            if (customerGroupForm.getField(CommonUtils.TEXT1).getValue() != null && StringUtils.isNotEmpty(customerGroupForm.getField(CommonUtils.TEXT1).getValue().toString())) {
                final String customerGName = customerGroupForm.getField(CommonUtils.TEXT1).getValue().toString();
                if (customerGName.contains(GlobalConstants.getPercent())) {
                    String customerGroupName = customerGName.replace(GlobalConstants.getPercent(), GlobalConstants.getPercentForEscape());
                    customerGroupName = customerGroupName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
                    projectionDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.COMPANY_GROUP_NAME, customerGroupName));
                } else {
                    String customerGroupName = customerGName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
                    projectionDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.COMPANY_GROUP_NAME, customerGroupName));
                }
            }
            if (customerGroupForm.getField(CommonUtils.TEXT2).getValue() != null && StringUtils.isNotEmpty(customerGroupForm.getField(CommonUtils.TEXT2).getValue().toString())) {
                final String customerGNo = customerGroupForm.getField(CommonUtils.TEXT2).getValue().toString();
                if (customerGNo.contains(GlobalConstants.getPercent())) {
                    String customerGroupNo = customerGNo.replace(GlobalConstants.getPercent(), GlobalConstants.getPercentForEscape());
                    customerGroupNo = customerGroupNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
                    projectionDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.COMPANY_GROUP_NO, customerGroupNo));
                } else {
                    String customerGroupNo = customerGNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
                    projectionDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.COMPANY_GROUP_NO, customerGroupNo));
                }
            }
            if (customerGroupForm.getField(CommonUtils.TEXT3).getValue() != null && StringUtils.isNotEmpty(customerGroupForm.getField(CommonUtils.TEXT3).getValue().toString())) {
                final String customerGDesc = customerGroupForm.getField(CommonUtils.TEXT3).getValue().toString();
                if (customerGDesc.contains(GlobalConstants.getPercent())) {
                    String customerGroupDesc = customerGDesc.replace(GlobalConstants.getPercent(), GlobalConstants.getPercentForEscape());
                    customerGroupDesc = customerGroupDesc.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
                    projectionDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.COMPANY_GROUP_DESC, customerGroupDesc));
                } else {
                    String customerGroupDesc = customerGDesc.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
                    projectionDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.COMPANY_GROUP_DESC, customerGroupDesc));
                }
            }

            if (filterSet != null) {
                for (Container.Filter filter : filterSet) {
                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        String filterString = "%" + stringFilter.getFilterString() + "%";
                        if (ConstantsUtils.COMPANY_GROUP_NAME.equals(stringFilter.getPropertyId())) {
                            projectionDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.COMPANY_GROUP_NAME, filterString));
                        }
                        if (ConstantsUtils.COMPANY_GROUP_NO.equals(stringFilter.getPropertyId())) {
                            projectionDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.COMPANY_GROUP_NO, filterString));
                        }
                        if (ConstantsUtils.COMPANY_GROUP_DESC.equals(stringFilter.getPropertyId())) {
                            projectionDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.COMPANY_GROUP_DESC, filterString));
                        }

                    } else if (filter instanceof Compare) {
                        Compare compare = (Compare) filter;
                        Compare.Operation operation = compare.getOperation();
                        if (ConstantsUtils.VERSION_NO.equalsIgnoreCase(String.valueOf(compare.getPropertyId()))) {
                            int value = Integer.valueOf(String.valueOf(compare.getValue()));
                            if (operation.GREATER.toString().equalsIgnoreCase(operation.name())) {
                                projectionDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.VERSION_NO, value));
                            } else if (operation.LESS.toString().equalsIgnoreCase(operation.name())) {
                                projectionDynamicQuery.add(RestrictionsFactoryUtil.le(ConstantsUtils.VERSION_NO, value));
                            } else if (operation.EQUAL.toString().equalsIgnoreCase(operation.name())) {
                                projectionDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.VERSION_NO, value));
                            }
                        } else if (compare.getValue() instanceof Date) {
                            Date filterString = (Date) compare.getValue();
                            if (operation.GREATER_OR_EQUAL.toString().equalsIgnoreCase(operation.name())) {
                                if (ConstantsUtils.CREATED_DATE.equals(compare.getPropertyId())) {
                                    projectionDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.CREATED_DATE, filterString));
                                } else if (ConstantsUtils.MODIFIED_DATE.equals(compare.getPropertyId())) {
                                    projectionDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.MODIFIED_DATE, filterString));
                                }
                            } else if (operation.LESS_OR_EQUAL.toString().equalsIgnoreCase(operation.name())) {
                                if (ConstantsUtils.CREATED_DATE.equals(compare.getPropertyId())) {
                                    projectionDynamicQuery.add(RestrictionsFactoryUtil.le(ConstantsUtils.CREATED_DATE, filterString));
                                } else if (ConstantsUtils.MODIFIED_DATE.equals(compare.getPropertyId())) {
                                    projectionDynamicQuery.add(RestrictionsFactoryUtil.le(ConstantsUtils.MODIFIED_DATE, filterString));
                                }
                            }
                        }
                    } else if (filter instanceof Between) {

                        Between stringFilter = (Between) filter;
                        Date filterString = (Date) stringFilter.getStartValue();
                        Date filterString1 = (Date) stringFilter.getEndValue();

                        if (ConstantsUtils.CREATED_DATE.equals(stringFilter.getPropertyId())) {
                            projectionDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.CREATED_DATE, filterString));
                            projectionDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantsUtils.CREATED_DATE, filterString1));
                        }
                        if (ConstantsUtils.MODIFIED_DATE.equals(stringFilter.getPropertyId())) {
                            projectionDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.MODIFIED_DATE, filterString));
                            projectionDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantsUtils.MODIFIED_DATE, filterString1));
                        }

                    }
                }
            }
            loadColumnName();
            if (sortByColumns != null) {
                for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                    final SortByColumn orderByColumn = (SortByColumn) iterator.next();

                    if (orderByColumn.getType() == SortByColumn.Type.ASC) {

                        projectionDynamicQuery.addOrder(OrderFactoryUtil.asc(getDBColumnName(orderByColumn.getName())));
                    } else {
                        projectionDynamicQuery.addOrder(OrderFactoryUtil.desc(getDBColumnName(orderByColumn.getName())));
                    }
                }
            }
            if (!isCount) {
                projectionDynamicQuery.setLimit(startIndex, startIndex + endIndex);
            }
            List<CompanyGroup> currentResultList;
            List<HistCompanyGroup> auditResultList;
            if (version.equalsIgnoreCase(ConstantsUtils.VERSION_CURRENT)) {
                if (isCount) {
                    object = (Integer) dao.getCompanyGroupListCount(projectionDynamicQuery);
                } else {
                    currentResultList = dao.getCompanyGroupList(projectionDynamicQuery);
                    object = getCustomizedResults(currentResultList, null);
                }

            } else if (version.equalsIgnoreCase(ConstantsUtils.VERSION_HIST)) {
                if (isCount) {
                    object = (Integer) dao.getCgmHistoryCount(projectionDynamicQuery);
                } else {
                    auditResultList = dao.getCompanyGroupDetailsHistoryList(projectionDynamicQuery);
                    object = getCustomizedResults(null, auditResultList);
                }
            }

            LOGGER.debug("getSearchResults Ended  ");
        } catch (Exception ex) {
            LOGGER.error(ex);

        }
        return object;
    }

    public static String getDBColumnName(String visibleColumnName) {
        return columnNames.get(visibleColumnName);
    }

    public static HashMap<String, String> loadColumnName() {
        columnNames.put("companyGroupName", "companyGroupName");
        columnNames.put("companyGroupDescription", "companyGroupDescription");
        columnNames.put("companyGroupNo", "companyGroupNo");
        columnNames.put(CommonUtils.VERSION_NO, CommonUtils.VERSION_NO);
        columnNames.put("createdDate", "createdDate");
        columnNames.put("modifiedDate", "modifiedDate");
        columnNames.put("createdBy", "createdBy");

        return columnNames;
    }

    /**
     * Gets the search results.
     *
     * @param customerGroupForm the customer group form
     * @param version the version
     * @return the search results
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public int getSearchResultsCount(final CustomFieldGroup customerGroupForm, final String version) throws SystemException, PortalException {

        LOGGER.debug("Entering getSearchResultsCount with P1:CustomFieldGroup customerGroupForm");
        DynamicQuery projectionDynamicQuery = null;
        int count = 0;
        if (version != null) {
            if (version.equalsIgnoreCase(ConstantsUtils.VERSION_CURRENT)) {
                projectionDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyGroup.class);
            } else if (version.equalsIgnoreCase(ConstantsUtils.VERSION_HIST)) {
                projectionDynamicQuery = DynamicQueryFactoryUtil.forClass(HistCompanyGroup.class);
                projectionDynamicQuery.add(RestrictionsFactoryUtil.ne("primaryKey.actionFlag", "D"));
            }
        }
        if (customerGroupForm.getField(ConstantsUtils.CUST_GROUP_NAME).getValue() != null && StringUtils.isNotEmpty(customerGroupForm.getField(ConstantsUtils.CUST_GROUP_NAME).getValue().toString())) {
            final String customerGName = customerGroupForm.getField(ConstantsUtils.CUST_GROUP_NAME).getValue().toString();
            final String customerGroupName = customerGName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            projectionDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.COMPANY_GROUP_NAME, customerGroupName));
        }
        if (customerGroupForm.getField(ConstantsUtils.CUST_GROUP_NO).getValue() != null && StringUtils.isNotEmpty(customerGroupForm.getField(ConstantsUtils.CUST_GROUP_NO).getValue().toString())) {
            final String customerGNo = customerGroupForm.getField(ConstantsUtils.CUST_GROUP_NO).getValue().toString();
            final String customerGroupNo = customerGNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            projectionDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.COMPANY_GROUP_NO, customerGroupNo));
        }
        if (customerGroupForm.getField(ConstantsUtils.CUST_GROUP_DESC).getValue() != null && StringUtils.isNotEmpty(customerGroupForm.getField(ConstantsUtils.CUST_GROUP_DESC).getValue().toString())) {
            final String customerGDesc = customerGroupForm.getField(ConstantsUtils.CUST_GROUP_DESC).getValue().toString();
            final String customerGroupDesc = customerGDesc.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            projectionDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.COMPANY_GROUP_DESC, customerGroupDesc));
        }

        if (version.equalsIgnoreCase(ConstantsUtils.VERSION_CURRENT)) {
            count = dao.getCompanyGroupListCount(projectionDynamicQuery);

        } else if (version.equalsIgnoreCase(ConstantsUtils.VERSION_HIST)) {
            count = dao.getCgmHistoryCount(projectionDynamicQuery);

        }
        LOGGER.debug("getSearchResults Ended  ");
        return count;

    }

    public CustomerGroupLogicDAO getDao() {
        return dao;
    }

    /**
     * Gets the customized results.
     *
     * @param currentList the current list
     * @param resultList the result list
     * @return the customized results
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    private List<SearchResultsDTO> getCustomizedResults(final List<CompanyGroup> currentList, final List<HistCompanyGroup> resultList) throws SystemException, ParseException {

        final List<SearchResultsDTO> customerGroupsList = new ArrayList<>();

        if (currentList == null) {
            if (resultList != null && resultList.size() > ConstantsUtils.ZERO_NUM) {

                    final Map<String, String> userInfoMap = CommonUtil.getCreatedByUser();
                    for (final Iterator<HistCompanyGroup> iterator = resultList.iterator(); iterator.hasNext();) {
                        final HistCompanyGroup customerGroup = iterator.next();
                        final SearchResultsDTO customerGroupDto = new SearchResultsDTO();
                        customerGroupDto.setCompanyGroupNo(customerGroup.getCompanyGroupNo());
                        customerGroupDto.setCompanyGroupName(customerGroup.getCompanyGroupName());
                        customerGroupDto.setCompanyGroupDescription(customerGroup.getCompanyGroupDescription());
                        final String createdBy = userInfoMap.get(String.valueOf(customerGroup.getCreatedBy()));
                        if (StringUtils.isNotBlank(createdBy) && !createdBy.equals("null")) {
                            customerGroupDto.setCreatedBy(createdBy);
                        }
                        String tempStringDate = CommonUtils.convertDateToString(customerGroup.getCreatedDate());
                        DateFormat df = new SimpleDateFormat(CommonUtils.MMDDYYYY);
                        Date tempStartDate = df.parse(tempStringDate);
                        customerGroupDto.setCreatedDate(tempStartDate);
                        String tempModifiedDate = CommonUtils.convertDateToString(customerGroup.getCreatedDate());
                        Date ModifiedDate = df.parse(tempModifiedDate);
                        customerGroupDto.setModifiedDate(ModifiedDate);
                        customerGroupDto.setVersionNo(customerGroup.getVersionNo());
                        customerGroupDto.setCustomerGroupSystemId(customerGroup.getCompanyGroupSid());
                        customerGroupDto.setStatus(customerGroup.getActionFlag());
                        customerGroupDto.setSystemID(String.valueOf(customerGroup.getCompanyGroupSid()));
                        customerGroupsList.add(customerGroupDto);
                    }

            }
        } else if (currentList.size() > ConstantsUtils.ZERO_NUM) {
            final Map<String, String> userInfoMap = CommonUtil.getCreatedByUser();
            for (final Iterator<CompanyGroup> iterator = currentList.iterator(); iterator.hasNext();) {
                final CompanyGroup customerGroup = iterator.next();
                final SearchResultsDTO customerGroupDto = new SearchResultsDTO();
                customerGroupDto.setCompanyGroupNo(customerGroup.getCompanyGroupNo());
                customerGroupDto.setCompanyGroupName(customerGroup.getCompanyGroupName());
                customerGroupDto.setCompanyGroupDescription(customerGroup.getCompanyGroupDescription());
                final String createdBy = userInfoMap.get(String.valueOf(customerGroup.getCreatedBy()));
                if (StringUtils.isNotBlank(createdBy) && !createdBy.equals("null")) {
                    customerGroupDto.setCreatedBy(createdBy);
                }
                String tempStringDate = CommonUtils.convertDateToString(customerGroup.getCreatedDate());
                DateFormat df = new SimpleDateFormat(CommonUtils.MMDDYYYY);
                Date tempStartDate = df.parse(tempStringDate);
                customerGroupDto.setCreatedDate(tempStartDate);

                String tempModifiedDate = CommonUtils.convertDateToString(customerGroup.getCreatedDate());
                Date ModifiedDate = df.parse(tempModifiedDate);
                customerGroupDto.setModifiedDate(ModifiedDate);
                customerGroupDto.setVersionNo(customerGroup.getVersionNo());
                customerGroupDto.setCustomerGroupSystemId(customerGroup.getCompanyGroupSid());
                customerGroupDto.setSystemID(String.valueOf(customerGroup.getCompanyGroupSid()));
                customerGroupsList.add(customerGroupDto);
            }
        }

        LOGGER.debug("getCustomizedResults return customerGroupList=" + customerGroupsList.size());

        return customerGroupsList;
    }

    /**
     * Gets the customer search results.
     *
     * @param companyForm the company form
     * @param count
     * @param startIndex
     * @param endIndex
     * @return the customer search results
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public List<CustomerDetailsDTO> getCustomerSearchResults(final ErrorfulFieldGroup companyForm, final int count) {
        LOGGER.debug("Entering getCustomerSearchResults with P1:CustomFieldGroup companyForm");


        DynamicQueryFactoryUtil.forClass(CompanyMaster.class);

        List<String> criteria = getSearchCriteria(companyForm, count);
        List<CustomerDetailsDTO> itemsList = new ArrayList<>();
        try {

            final List resultList = CompanyMasterLocalServiceUtil.getCustomerSearchDetails(criteria.get(0), criteria.get(1), criteria.get(2), criteria.get(3), criteria.get(4), criteria.get(5), criteria.get(6), criteria.get(7));
            final HashMap resultList1 = new HashMap();
            itemsList = getCustomizedCustomerResults(resultList, resultList1);
            LOGGER.debug("getCustomerSearchResults return itemsList=" + itemsList.size());
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return itemsList;
    }

    public List<String> getSearchCriteria(final ErrorfulFieldGroup companyForm, final int count) {
        final List<String> criteria = new ArrayList<>();
        String customerNo = ConstantsUtils.EMPTY;
        String customerName = ConstantsUtils.EMPTY;
        String tradeClass = ConstantsUtils.EMPTY;
        String city = ConstantsUtils.EMPTY;
        String state = ConstantsUtils.EMPTY;
        String zipCode = ConstantsUtils.EMPTY;
        String customerStatus = ConstantsUtils.EMPTY;
        String customerType = ConstantsUtils.EMPTY;
        String searchCriteria = ConstantsUtils.EMPTY;
        boolean flag = true;
        if (count > 1) {
            searchCriteria += " ~ ";
        }
        if (companyForm.getField(ConstantsUtils.CUST_NO).getValue() != null && StringUtils.isNotEmpty(companyForm.getField(ConstantsUtils.CUST_NO).getValue().toString())) {
            final String custNo = companyForm.getField(ConstantsUtils.CUST_NO).getValue().toString();
            customerNo = com.stpl.ifs.util.CommonUtil.buildSearchCriteria(custNo);
            searchCriteria += " AND CM.company_No like '" + customerNo + "' ";
            flag = false;
        }
        if (companyForm.getField(ConstantsUtils.TRADE_CLASS).getValue() != null && StringUtils.isNotEmpty(companyForm.getField(ConstantsUtils.TRADE_CLASS).getValue().toString())
                && !ConstantsUtils.SELECT_ONE.equalsIgnoreCase(companyForm.getField(ConstantsUtils.TRADE_CLASS).getValue().toString())) {
            final String tClass = companyForm.getField(ConstantsUtils.TRADE_CLASS).getValue().toString();
            tradeClass = com.stpl.ifs.util.CommonUtil.buildSearchCriteria(tClass);
            searchCriteria += " and HT_TC.DESCRIPTION ='" + tradeClass + "' " + " AND CTC.COMPANY_TRADE_CLASS=HT_TC.HELPER_TABLE_SID ";
            flag = false;
        }
        if (companyForm.getField(ConstantsUtils.CUST_STATUS).getValue() != null && StringUtils.isNotEmpty(companyForm.getField(ConstantsUtils.CUST_STATUS).getValue().toString())
                && !ConstantsUtils.SELECT_ONE.equalsIgnoreCase(companyForm.getField(ConstantsUtils.CUST_STATUS).getValue().toString())) {
            customerStatus = companyForm.getField(ConstantsUtils.CUST_STATUS).getValue().toString();

            searchCriteria += " and HT_CS.DESCRIPTION = '" + customerStatus + "' " + " AND CM.COMPANY_STATUS = HT_CS.HELPER_TABLE_SID ";
            flag = false;
        }
        if (companyForm.getField(ConstantsUtils.STATE).getValue() != null && StringUtils.isNotEmpty(companyForm.getField(ConstantsUtils.STATE).getValue().toString())
                && !ConstantsUtils.SELECT_ONE.equalsIgnoreCase(companyForm.getField(ConstantsUtils.STATE).getValue().toString())) {
            final String tempState = companyForm.getField(ConstantsUtils.STATE).getValue().toString();
            state = com.stpl.ifs.util.CommonUtil.buildSearchCriteria(tempState);
            searchCriteria += " and HT_ST.DESCRIPTION like '" + state + "' " + " AND CM.STATE = HT_ST.HELPER_TABLE_SID ";
            flag = false;
        }
        if (companyForm.getField(ConstantsUtils.CUST_NAME).getValue() != null && StringUtils.isNotEmpty(companyForm.getField(ConstantsUtils.CUST_NAME).getValue().toString())) {
            final String custName = companyForm.getField(ConstantsUtils.CUST_NAME).getValue().toString();
            customerName = com.stpl.ifs.util.CommonUtil.buildSearchCriteria(custName);
            searchCriteria += " and CM.company_Name like '" + customerName + "' ";
            flag = false;
        }
        if (companyForm.getField(ConstantsUtils.CUST_TYPE).getValue() != null && StringUtils.isNotEmpty(companyForm.getField(ConstantsUtils.CUST_TYPE).getValue().toString())
                && !ConstantsUtils.SELECT_ONE.equalsIgnoreCase(companyForm.getField(ConstantsUtils.CUST_TYPE).getValue().toString())) {

            customerType = companyForm.getField(ConstantsUtils.CUST_TYPE).getValue().toString();
            searchCriteria += " and HT_CT.DESCRIPTION like '" + customerType + "' " + " AND CM.COMPANY_TYPE = HT_CT.HELPER_TABLE_SID ";
            flag = false;
        }
        if (companyForm.getField(ConstantsUtils.CITY).getValue() != null && StringUtils.isNotEmpty(companyForm.getField(ConstantsUtils.CITY).getValue().toString())) {
            final String tempCity = companyForm.getField(ConstantsUtils.CITY).getValue().toString();
            city = com.stpl.ifs.util.CommonUtil.buildSearchCriteria(tempCity);
            searchCriteria += " and CM.city like'" + city + "' ";
            flag = false;
        }

        if (companyForm.getField(ConstantsUtils.ZIP_CODE).getValue() != null && StringUtils.isNotEmpty(companyForm.getField(ConstantsUtils.ZIP_CODE).getValue().toString())) {
            final String zCode = companyForm.getField(ConstantsUtils.ZIP_CODE).getValue().toString();
            zipCode = com.stpl.ifs.util.CommonUtil.buildSearchCriteria(zCode);
            searchCriteria += " and CM.zip_Code like '" + zipCode + "' ";
            flag = false;
        }
        if (flag) {
            searchCriteria = "*";
        }
        criteria.add(customerNo);
        criteria.add(tradeClass);
        criteria.add(customerStatus);

        criteria.add(state);
        criteria.add(customerName);
        criteria.add(customerType);

        criteria.add(city);
        criteria.add(zipCode);
        criteria.add(searchCriteria);
        return criteria;
    }

    /**
     * Gets the customized customer results.
     *
     * @param resultList the result list
     * @param resultList1 the result list1
     * @return the customized customer results
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public List<CustomerDetailsDTO> getCustomizedCustomerResults(final List resultList, final Map resultList1) throws ParseException {
        final List<CustomerDetailsDTO> customerDetailsList = new ArrayList<>();
        
        for (int i = 0; i < resultList.size(); i++) {
            final Object[] obj = (Object[]) resultList.get(i);

            final CustomerDetailsDTO customerDetailsDTO = new CustomerDetailsDTO();
            if (obj[0] == null) {
                customerDetailsDTO.setOrganizationKey(ConstantsUtils.EMPTY);
            } else {
                customerDetailsDTO.setOrganizationKey(obj[0].toString());
            }
            if (obj[1] == null) {
                customerDetailsDTO.setCustomerId(ConstantsUtils.EMPTY);
            } else {
                customerDetailsDTO.setCustomerId(obj[1].toString());
            }
            if (obj[NumericConstants.TWO] == null) {
                customerDetailsDTO.setCustomerNo(ConstantsUtils.EMPTY);
            } else {
                customerDetailsDTO.setCustomerNo(obj[NumericConstants.TWO].toString());
            }
            if (obj[NumericConstants.THREE] == null) {
                customerDetailsDTO.setCustomerName(ConstantsUtils.EMPTY);
            } else {
                customerDetailsDTO.setCustomerName(obj[NumericConstants.THREE].toString());
            }
            if (obj[NumericConstants.FOUR] == null) {
                customerDetailsDTO.setTradeClass(ConstantsUtils.EMPTY);
            } else {
                customerDetailsDTO.setTradeClass(obj[NumericConstants.FOUR].toString());
            }
            if (obj[NumericConstants.FIVE] != null) {

                Date d = dateFormat.parse(CommonUtils.convertDateToString((Date) obj[NumericConstants.FIVE]));
                customerDetailsDTO.setTradeClassStartDate(d);
            }
            if (obj[NumericConstants.SIX] != null) {
                Date d = dateFormat.parse(CommonUtils.convertDateToString((Date) obj[NumericConstants.SIX]));
                customerDetailsDTO.setTradeClassEndDate((Date) d);
            }
            if (obj[NumericConstants.SEVEN] == null) {
                customerDetailsDTO.setCustomerType(ConstantsUtils.EMPTY);
            } else {
                customerDetailsDTO.setCustomerType(obj[NumericConstants.SEVEN].toString());
            }
            if (obj[NumericConstants.EIGHT] == null) {
                customerDetailsDTO.setCustomerStatus(ConstantsUtils.EMPTY);
            } else {
                customerDetailsDTO.setCustomerStatus(obj[NumericConstants.EIGHT].toString());
            }

            if (obj[NumericConstants.NINE] != null) {
                Date d = dateFormat.parse(CommonUtils.convertDateToString((Date) obj[NumericConstants.NINE]));
                customerDetailsDTO.setCustomerEndDate(d);
            }
            if (obj[NumericConstants.TEN] == null || ConstantsUtils.SELECT_ONE.equalsIgnoreCase((String) obj[NumericConstants.TEN])) {
                customerDetailsDTO.setUdc1(ConstantsUtils.EMPTY);
            } else {
                customerDetailsDTO.setUdc1(obj[NumericConstants.TEN].toString());
            }
            if (obj[NumericConstants.ELEVEN] == null || ConstantsUtils.SELECT_ONE.equalsIgnoreCase((String) obj[NumericConstants.ELEVEN])) {
                customerDetailsDTO.setUdc2(ConstantsUtils.EMPTY);
            } else {
                customerDetailsDTO.setUdc2(obj[NumericConstants.ELEVEN].toString());
            }
            if (obj[NumericConstants.TWELVE] == null || ConstantsUtils.SELECT_ONE.equalsIgnoreCase((String) obj[NumericConstants.TWELVE])) {
                customerDetailsDTO.setUdc3(ConstantsUtils.EMPTY);
            } else {
                customerDetailsDTO.setUdc3(obj[NumericConstants.TWELVE].toString());
            }
            if (obj[NumericConstants.THIRTEEN] == null || ConstantsUtils.SELECT_ONE.equalsIgnoreCase((String) obj[NumericConstants.THIRTEEN])) {
                customerDetailsDTO.setUdc4(ConstantsUtils.EMPTY);
            } else {
                customerDetailsDTO.setUdc4(obj[NumericConstants.THIRTEEN].toString());
            }
            if (obj[NumericConstants.FOURTEEN] == null || ConstantsUtils.SELECT_ONE.equalsIgnoreCase((String) obj[NumericConstants.FOURTEEN])) {
                customerDetailsDTO.setUdc5(ConstantsUtils.EMPTY);
            } else {
                customerDetailsDTO.setUdc5(obj[NumericConstants.FOURTEEN].toString());
            }
            if (obj[NumericConstants.FIFTEEN] == null || ConstantsUtils.SELECT_ONE.equalsIgnoreCase((String) obj[NumericConstants.FIFTEEN])) {
                customerDetailsDTO.setUdc6(ConstantsUtils.EMPTY);
            } else {
                customerDetailsDTO.setUdc6(obj[NumericConstants.FIFTEEN].toString());
            }
            if (obj[NumericConstants.SIXTEEN] == null) {
                customerDetailsDTO.setCustomerGroup(ConstantsUtils.EMPTY);
            } else {
                customerDetailsDTO.setCustomerGroup(obj[NumericConstants.SIXTEEN].toString());
            }
            if (obj[NumericConstants.SEVENTEEN] == null) {
                customerDetailsDTO.setFinancialSystem(ConstantsUtils.EMPTY);
            } else {
                customerDetailsDTO.setFinancialSystem(obj[NumericConstants.SEVENTEEN].toString());
            }
            if (obj[NumericConstants.EIGHTEEN] == null) {
                customerDetailsDTO.setAddress1(ConstantsUtils.EMPTY);
            } else {
                customerDetailsDTO.setAddress1(obj[NumericConstants.EIGHTEEN].toString());
            }
            if (obj[NumericConstants.NINETEEN] == null) {
                customerDetailsDTO.setAddress2(ConstantsUtils.EMPTY);
            } else {
                customerDetailsDTO.setAddress2(obj[NumericConstants.NINETEEN].toString());
            }
            if (obj[NumericConstants.TWENTY] == null) {
                customerDetailsDTO.setCity(ConstantsUtils.EMPTY);
            } else {
                customerDetailsDTO.setCity(obj[NumericConstants.TWENTY].toString());
            }
            if (obj[NumericConstants.TWENTY_ONE] == null || ConstantsUtils.SELECT_ONE.equalsIgnoreCase((String) obj[NumericConstants.TWENTY_ONE])) {
                customerDetailsDTO.setState(ConstantsUtils.EMPTY);
            } else {
                customerDetailsDTO.setState(obj[NumericConstants.TWENTY_ONE].toString());
            }
            if (obj[NumericConstants.TWENTY_TWO] == null) {
                customerDetailsDTO.setZipCode(ConstantsUtils.EMPTY);
            } else {
                customerDetailsDTO.setZipCode(obj[NumericConstants.TWENTY_TWO].toString());
            }
            if (obj[NumericConstants.TWENTY_THREE] == null || ConstantsUtils.SELECT_ONE.equalsIgnoreCase((String) obj[NumericConstants.TWENTY_THREE])) {
                customerDetailsDTO.setCountry(ConstantsUtils.EMPTY);
            } else {
                customerDetailsDTO.setCountry(obj[NumericConstants.TWENTY_THREE].toString());
            }
            if (obj[NumericConstants.TWENTY_FOUR] == null) {
                customerDetailsDTO.setRegionCode(ConstantsUtils.EMPTY);
            } else {
                customerDetailsDTO.setRegionCode(obj[NumericConstants.TWENTY_FOUR].toString());
            }
            if (obj[NumericConstants.TWENTY_FIVE] == null) {
                customerDetailsDTO.setParentCustomerNo(ConstantsUtils.EMPTY);
            } else if (Integer.valueOf(obj[NumericConstants.TWENTY_FIVE].toString()) != ConstantsUtils.ZERO_NUM) {
                final List resultList2 = CompanyMasterLocalServiceUtil.getPriorParentNo(obj[NumericConstants.TWENTY_FIVE].toString());
                if (resultList2 != null && !resultList2.isEmpty()) {
                    final Object objectDTO = resultList2.get(0);
                    customerDetailsDTO.setParentCustomerNo(objectDTO.toString());
                }
            }
            if (obj[NumericConstants.TWENTY_SIX] != null) {
                Date d = dateFormat.parse(CommonUtils.convertDateToString((Date) obj[NumericConstants.TWENTY_SIX]));
                customerDetailsDTO.setParentStartDate(d);
            }
            if (obj[NumericConstants.TWENTY_SEVEN] != null) {
                Date d = dateFormat.parse(CommonUtils.convertDateToString((Date) obj[NumericConstants.TWENTY_SEVEN]));
                customerDetailsDTO.setParentEndDate(d);
            }
            if (obj[NumericConstants.TWENTY_EIGHT] != null) {
                Date d = dateFormat.parse(CommonUtils.convertDateToString((Date) obj[NumericConstants.TWENTY_EIGHT]));
                customerDetailsDTO.setCustomerStartDate(d);
            }
            if (obj[NumericConstants.TWENTY_NINE] != null) {
                Date d = dateFormat.parse(CommonUtils.convertDateToString((Date) obj[NumericConstants.TWENTY_NINE]));
                customerDetailsDTO.setPriorParentStartDate(d);
            }
            if (obj[NumericConstants.THIRTY] == null) {
                customerDetailsDTO.setPriorParentCustomerNo(ConstantsUtils.EMPTY);
            } else if (Integer.valueOf(obj[NumericConstants.THIRTY].toString()) != ConstantsUtils.ZERO_NUM) {
                final List resultList2 = CompanyMasterLocalServiceUtil.getPriorParentNo(obj[NumericConstants.THIRTY].toString());
                if (resultList2 != null && !resultList2.isEmpty()) {
                    final Object objectDTO = resultList2.get(0);
                    customerDetailsDTO.setPriorParentCustomerNo(objectDTO.toString());
                }
            }
            customerDetailsDTO.setCompanySystemId(Integer.valueOf(obj[NumericConstants.THIRTY_ONE].toString()));
            customerDetailsDTO.setTradeClassSysId(Integer.valueOf(obj[NumericConstants.THIRTY_TWO] == null ? "0" : obj[NumericConstants.THIRTY_TWO].toString()));
            customerDetailsDTO.setParentSysId(Integer.valueOf(obj[NumericConstants.THIRTY_THREE] == null ? "0" : obj[NumericConstants.THIRTY_THREE].toString()));
            if (obj[NumericConstants.THIRTY_FOUR] == null) {
                customerDetailsDTO.setLives(ConstantsUtils.EMPTY);
            } else {
                customerDetailsDTO.setLives(obj[NumericConstants.THIRTY_FOUR].toString());
            }
            if (!(resultList1.isEmpty())) {
                final CompanyGroupDetails itemGroup = (CompanyGroupDetails) resultList1.get(Integer.valueOf(obj[NumericConstants.THIRTY_ONE].toString()));
                Date d = dateFormat.parse(CommonUtils.convertDateToString(itemGroup.getCreatedDate()));
                Date d2 = dateFormat.parse(CommonUtils.convertDateToString(itemGroup.getModifiedDate()));

                customerDetailsDTO.setCreatedBy(itemGroup.getCreatedBy());
                customerDetailsDTO.setModifiedBy(itemGroup.getModifiedBy());
                customerDetailsDTO.setCreatedDate(d);
                customerDetailsDTO.setModifiedDate(d2);
            }

            customerDetailsList.add(customerDetailsDTO);
        }

        LOGGER.debug("getSearchResults return customerDetailsList=" + customerDetailsList.size());

        return customerDetailsList;
    }

    /**
     * Save customer group.
     *
     * @param customerGroupForm the customer group form
     * @param selectedCustomers the selected customers
     * @param searchCriteria
     * @return the string
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public List<Integer> saveCustomerGroup(final ErrorfulFieldGroup customerGroupForm, final List<CustomerDetailsDTO> selectedCustomers, final String searchCriteria, final SessionDTO sessionDTO) throws SystemException, PortalException {

        LOGGER.debug("Entering saveCustomerGroup with P1:CustomFieldGroup customerGroupForm and P2:List<CustomerDetailsDTO> selectedCustomers size: " + selectedCustomers.size());
        final List<Integer> idList = new ArrayList<>();
        final int userId = Integer.valueOf(sessionDTO.getUserId());
        CompanyGroup customerGroup;
        int versionNo = ConstantsUtils.ZERO_NUM;
        final int customerGroupSystemId = sessionDTO.getSystemId();
        if (customerGroupSystemId == ConstantsUtils.ZERO_NUM) {
            customerGroup = CompanyGroupLocalServiceUtil.createCompanyGroup(0);
            customerGroup.setCompanyGroupNo(customerGroupForm.getField(ConstantsUtils.CUST_GROUP_NO).getValue().toString());
            customerGroup.setCompanyGroupName(customerGroupForm.getField(ConstantsUtils.CUST_GROUP_NAME).getValue().toString());
            customerGroup.setCompanyGroupDescription(customerGroupForm.getField(ConstantsUtils.CUST_GROUP_DESC).getValue().toString());
            customerGroup.setCreatedDate(new Date());
            customerGroup.setModifiedDate(new Date());
            customerGroup.setCreatedBy(userId);
            customerGroup.setVersionNo(versionNo + 1);
            customerGroup.setCompanyFilter(searchCriteria);
            customerGroup = dao.addCompanyGroup(customerGroup);

        } else {
            customerGroup = dao.getCompanyGroup(customerGroupSystemId);
            versionNo = getExistingVersion(customerGroupSystemId);
            customerGroup.setCompanyGroupNo(customerGroupForm.getField(ConstantsUtils.CUST_GROUP_NO).getValue().toString());
            customerGroup.setCompanyGroupName(customerGroupForm.getField(ConstantsUtils.CUST_GROUP_NAME).getValue().toString());
            customerGroup.setCompanyGroupDescription(customerGroupForm.getField(ConstantsUtils.CUST_GROUP_DESC).getValue().toString());

            customerGroup.setVersionNo(versionNo + 1);
            customerGroup.setModifiedDate(new Date());
            customerGroup.setModifiedBy(userId);
            customerGroup.setCompanyFilter(searchCriteria);
            dao.updateCompanyGroup(customerGroup);

        }
        LOGGER.debug("saveCustomerGroup return success");
        String status = saveCompanyGroupDetails(customerGroup, selectedCustomers, sessionDTO);
        if (status.equalsIgnoreCase(ConstantsUtils.SUCCESS)) {
            idList.add(customerGroup.getCompanyGroupSid());
            idList.add(customerGroup.getVersionNo());
        }
        return idList;

    }

    /**
     * Save customer group details.
     *
     * @param companyGroup the company group
     * @param selectedCustomers the selected customers
     * @return the string
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    private String saveCompanyGroupDetails(final CompanyGroup companyGroup, final List<CustomerDetailsDTO> selectedCustomers, final SessionDTO sessionDTO) throws SystemException, PortalException {

        LOGGER.debug("Entering saveCustomerGroupDetails with P1:CompanyGroup itemGroup and P2:List<CustomerDetailsDTO> selectedCustomers size()" + selectedCustomers.size());
        final Date date = new Date();
        final int companyGroupSystemId = companyGroup.getCompanyGroupSid();
        final int companyGroupSessionId = sessionDTO.getSystemId();
        final int userId = Integer.valueOf(sessionDTO.getUserId());
        final DynamicQuery companyDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyGroupDetails.class);
        companyDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COMPANY_GROUP_SYS_ID, companyGroupSessionId));
        final List<CompanyGroupDetails> itemToRemove = dao.getCompanyGroupDetailsList(companyDynamicQuery);
        final HashMap savedCustomersMap = new HashMap();
        for (final Iterator<CompanyGroupDetails> iterator = itemToRemove.iterator(); iterator.hasNext();) {
            final CompanyGroupDetails itemDetail = iterator.next();
            savedCustomersMap.put(itemDetail.getCompanyMasterSid(), itemDetail);
        }
        for (final Iterator<CustomerDetailsDTO> iterator = selectedCustomers.iterator(); iterator.hasNext();) {
            final CustomerDetailsDTO company = iterator.next();
            CompanyGroupDetails companyGroupDetails = CompanyGroupDetailsLocalServiceUtil.createCompanyGroupDetails(0);
            companyGroupDetails.setCompanyGroupSid(companyGroupSystemId);
            if (company.getCompanySystemId() != 0) {

                companyGroupDetails.setCompanyMasterSid(company.getCompanySystemId());
            }
            if (company.getTradeClassSysId() != 0) {
                companyGroupDetails.setCompanyTradeclassSid(company.getTradeClassSysId());

            }
            if (company.getParentSysId() != 0) {
                companyGroupDetails.setCompanyParentDetailsSid(String.valueOf(company.getParentSysId()));

            } else {
                companyGroupDetails.setCompanyParentDetailsSid(null);
            }

            companyGroupDetails.setVersionNo(companyGroup.getVersionNo());
            if (companyGroupSessionId == ConstantsUtils.ZERO_NUM) {

                companyGroupDetails.setCreatedBy(userId);
                companyGroupDetails.setCreatedDate(date);
                companyGroupDetails.setModifiedDate(date);
                dao.addCompanyGroupDetails(companyGroupDetails);
            } else// to ensure that particular Customer Group ADD or EDIT
             if (savedCustomersMap.containsKey(company.getCompanySystemId())) {// To
                    // ensure that specific Group or not
                    companyGroupDetails = (CompanyGroupDetails) savedCustomersMap.get(company.getCompanySystemId());
                    companyGroupDetails.setModifiedDate(date);
                    companyGroupDetails.setModifiedBy(userId);
                    companyGroupDetails.setVersionNo(companyGroup.getVersionNo());

                    dao.updateCompanyGroupDetails(companyGroupDetails);
                    savedCustomersMap.remove(company.getCompanySystemId());
                } else {// to save new Customer in existing item group
                    companyGroupDetails.setCreatedBy(userId);
                    companyGroupDetails.setCreatedDate(date);
                    companyGroupDetails.setModifiedDate(date);
                    companyGroupDetails.setVersionNo(companyGroup.getVersionNo());
                    dao.addCompanyGroupDetails(companyGroupDetails);
                }
        }
        if (!savedCustomersMap.isEmpty()) {
            final List<CompanyGroupDetails> companyListToDelete = new ArrayList<>(savedCustomersMap.values());
            for (final Iterator<CompanyGroupDetails> iterator = companyListToDelete.iterator(); iterator.hasNext();) {
                final CompanyGroupDetails companyToDelete = iterator.next();
                dao.deleteCompanyGroupDetails(companyToDelete);
            }
        }
        LOGGER.debug("saveCustomerGroupDetails return success");

        return ConstantsUtils.SUCCESS;
    }

    /**
     * Gets the customer group info.
     *
     * @param sessionDTO
     * @return the customer group info
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public CustomerGroupDTO getCustomerGroupInfo(SessionDTO sessionDTO) throws SystemException, PortalException {
        LOGGER.debug("Entering getCustomerGroupInfo()");
        final int customerGroupSystemId = sessionDTO.getSystemId();
        final CustomerGroupDTO customerGroupDTO = new CustomerGroupDTO();
        CompanyGroup customerGroup;
        if (customerGroupSystemId != ConstantsUtils.ZERO_NUM) {
            customerGroup = dao.getCompanyGroup(customerGroupSystemId);
            customerGroupDTO.setCustomerGroupNo(customerGroup.getCompanyGroupNo());
            customerGroupDTO.setCustomerGroupName(customerGroup.getCompanyGroupName());
            customerGroupDTO.setCustomerGroupDesc(customerGroup.getCompanyGroupDescription());
            customerGroupDTO.setCustomerGroupSystemId(customerGroupSystemId);
            customerGroupDTO.setCreatedBy(String.valueOf(customerGroup.getCreatedBy()));
            customerGroupDTO.setCreatedDate(customerGroup.getCreatedDate());
            customerGroupDTO.setModifiedBy(String.valueOf(customerGroup.getModifiedBy()));
            customerGroupDTO.setModifiedDate(customerGroup.getModifiedDate());
        }

        LOGGER.debug("getCustomerGroupInfo return customerGroupDTO");
        return customerGroupDTO;

    }

    /**
     * Gets the hist customer group info.
     *
     * @param versionNo the version no
     * @param sessionDTO
     * @return the hist customer group info
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public CustomerGroupDTO getHistCustomerGroupInfo(final int versionNo, final SessionDTO sessionDTO) throws SystemException {
        LOGGER.debug("Entering getCustomerGroupInfo()");
        final int customerGroupSystemId = sessionDTO.getSystemId();
        final CustomerGroupDTO customerGroupDTO = new CustomerGroupDTO();
        if (customerGroupSystemId != ConstantsUtils.ZERO_NUM) {
            final DynamicQuery companyDynamicQuery = DynamicQueryFactoryUtil.forClass(HistCompanyGroup.class);
            companyDynamicQuery.add(RestrictionsFactoryUtil.eq("primaryKey.companyGroupSid", customerGroupSystemId));
            companyDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.PRIMARY_KEY + ConstantsUtils.VERSION_NO, versionNo));
            companyDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.PRIMARY_KEY + ConstantsUtils.ACTION_FLAG, "D"));
            final List<HistCompanyGroup> companyGroup = HistCompanyGroupDetailsLocalServiceUtil.dynamicQuery(companyDynamicQuery);
            if (companyGroup.size() > ConstantsUtils.ZERO_NUM) {

                for (final Iterator<HistCompanyGroup> iterator = companyGroup.iterator(); iterator.hasNext();) {
                    final HistCompanyGroup resultList = iterator.next();
                    customerGroupDTO.setCustomerGroupNo(resultList.getCompanyGroupNo());
                    customerGroupDTO.setCustomerGroupName(resultList.getCompanyGroupName());
                    customerGroupDTO.setCustomerGroupDesc(resultList.getCompanyGroupDescription());
                    customerGroupDTO.setCustomerGroupSystemId(customerGroupSystemId);
                    customerGroupDTO.setCreatedBy(String.valueOf(resultList.getCreatedBy()));
                    customerGroupDTO.setCreatedDate(resultList.getCreatedDate());
                    customerGroupDTO.setModifiedBy(String.valueOf(resultList.getModifiedBy()));
                    customerGroupDTO.setModifiedDate(resultList.getModifiedDate());
                    customerGroupDTO.setSearchCriteria(resultList.getCompanyFilter());

                }
            }
        }
        LOGGER.debug("getCustomerGroupInfo return customerGroupDTO");
        return customerGroupDTO;

    }

    /**
     * Gets the saved customer details.
     *
     * @param customerGroupSystemId the customer group system id
     * @param versionNo the version no
     * @return the saved customer details
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public List<CustomerDetailsDTO> getSavedCustomerDetails(final int customerGroupSystemId, final String versionNo) throws SystemException, PortalException {

        LOGGER.debug("Entering getSavedCustomerDetails with P1:int customerGroupSystemId=" + customerGroupSystemId);
        final HashMap companiesMap = new HashMap();
        final List<CustomerDetailsDTO> customerDetailsList = new ArrayList<>();
        final DynamicQuery companyGroupDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyGroupDetails.class);
        companyGroupDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COMPANY_GROUP_SYS_ID, customerGroupSystemId));
        companyGroupDynamicQuery.add(RestrictionsFactoryUtil.eq(CommonUtils.VERSION_NO, Integer.valueOf(versionNo)));
        final List<CompanyGroupDetails> resultList = dao.getCompanyGroupDetailsList(companyGroupDynamicQuery);
        for (final Iterator<CompanyGroupDetails> iterator = resultList.iterator(); iterator.hasNext();) {
            final List companies = new ArrayList();
            final CompanyGroupDetails companyGroupDetail = iterator.next();
            companiesMap.put(companyGroupDetail.getCompanyMasterSid(), companyGroupDetail);
            companies.add(Integer.valueOf(companyGroupDetail.getCompanyMasterSid()));
            final DynamicQuery companyDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
            companyDynamicQuery.add(RestrictionsFactoryUtil.in(ConstantsUtils.COMPANY_SYS_ID, companies));
            final List<CompanyMaster> companiesList = dao.getCompanyMasterList(companyDynamicQuery);
            for (final Iterator<CompanyMaster> companyIterator = companiesList.iterator(); companyIterator.hasNext();) {
                final CompanyMaster customer = companyIterator.next();
                final CustomerDetailsDTO customerDetailsDTO = new CustomerDetailsDTO();
                customerDetailsDTO.setOrganizationKey(customer.getOrgKey());
                customerDetailsDTO.setCustomerId(customer.getCompanyId());
                customerDetailsDTO.setCustomerNo(customer.getCompanyNo());
                customerDetailsDTO.setCustomerName(customer.getCompanyName());
                customerDetailsDTO.setCustomerType(String.valueOf(customer.getCompanyType()));
                customerDetailsDTO.setCustomerStatus(String.valueOf(customer.getCompanyStatus()));
                final ProjectionList projList = ProjectionFactoryUtil.projectionList();
                projList.add(ProjectionFactoryUtil.property(ConstantsUtils.TRADE_CLASS));
                projList.add(ProjectionFactoryUtil.property(TRADE_CLASS_START_DATE));
                projList.add(ProjectionFactoryUtil.property(TRADE_CLASS_END_DATE));
                final List tradeClassList = CompanyTradeClassLocalServiceUtil.getTradeClassDetails(String.valueOf(customer.getCompanyMasterSid()),
                        String.valueOf(companyGroupDetail.getCompanyTradeclassSid()));
                if (tradeClassList != null && !tradeClassList.isEmpty()) {
                        final Object[] obj = (Object[]) tradeClassList.get(0);
                        customerDetailsDTO.setTradeClass(obj[0].toString());
                        customerDetailsDTO.setTradeClassStartDate((Date) obj[1]);
                        customerDetailsDTO.setTradeClassEndDate((Date) obj[NumericConstants.TWO]);
                }

                customerDetailsDTO.setCustomerGroup(customer.getCompanyGroup());
                customerDetailsDTO.setFinancialSystem(customer.getFinancialSystem());
                customerDetailsDTO.setAddress1(customer.getAddress1());
                customerDetailsDTO.setAddress2(customer.getAddress2());
                customerDetailsDTO.setCity(customer.getCity());
                customerDetailsDTO.setState(String.valueOf(customer.getState()));
                customerDetailsDTO.setZipCode(customer.getZipCode());
                customerDetailsDTO.setCountry(String.valueOf(customer.getCountry()));
                customerDetailsDTO.setRegionCode(customer.getRegionCode());
                customerDetailsDTO.setCustomerStartDate(customer.getCompanyStartDate());
                customerDetailsDTO.setCompanySystemId(customer.getCompanyMasterSid());
                customerDetailsDTO.setParentSysId(Integer.valueOf(!"null".equals(companyGroupDetail.getCompanyParentDetailsSid()) && !StringUtils.isBlank(companyGroupDetail.getCompanyParentDetailsSid()) ? 0 : Integer.valueOf(companyGroupDetail.getCompanyParentDetailsSid())));
                if (customer.getCompanyMasterSid() != ConstantsUtils.ZERO_NUM) {
                    final List resultList2 = CompanyTradeClassLocalServiceUtil.getCompanyparentDetails(String.valueOf(customer.getCompanyMasterSid()),
                            String.valueOf(companyGroupDetail.getCompanyParentDetailsSid()));
                    if (resultList2 != null && !resultList2.isEmpty()) {

                        final Object[] objectValue = (Object[]) resultList2.get(0);
                        customerDetailsDTO.setParentStartDate((Date) objectValue[1]);
                        customerDetailsDTO.setParentEndDate((Date) objectValue[NumericConstants.TWO]);
                        customerDetailsDTO.setPriorParentStartDate((Date) objectValue[NumericConstants.THREE]);

                        if (Integer.valueOf(objectValue[0].toString()) != ConstantsUtils.ZERO_NUM) {

                            final List parentList = CompanyMasterLocalServiceUtil.getPriorParentNo(objectValue[0].toString());
                            if (parentList != null && parentList.size() > ConstantsUtils.ZERO_NUM) {
                                    final Object obje = parentList.get(0);
                                    customerDetailsDTO.setParentCustomerNo(obje.toString());
                            }
                            if (objectValue[NumericConstants.FOUR] != null && Integer.valueOf(objectValue[NumericConstants.FOUR].toString()) != ConstantsUtils.ZERO_NUM) {
                                    final List parentSysIdList = CompanyMasterLocalServiceUtil.getPriorParentNo(objectValue[0].toString());
                                    if (parentSysIdList != null && !parentSysIdList.isEmpty()) {
                                            final Object obje = parentSysIdList.get(0);
                                            customerDetailsDTO.setParentCustomerNo(obje.toString());
                                }
                            }
                        }
                    }
                }
                customerDetailsList.add(customerDetailsDTO);
            }
        }
        LOGGER.debug("getSavedCustomerDetails return companyDetailsList=" + customerDetailsList.size());
        return customerDetailsList;

    }
    public static final String TRADE_CLASS_END_DATE = "tradeClassEndDate";
    public static final String TRADE_CLASS_START_DATE = "tradeClassStartDate";

    /**
     * Gets the saved customer details.
     *
     * @param customerGroupSystemId the customer group system id
     * @param versionNo the version no
     * @return the saved customer details
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public List<CustomerDetailsDTO> getSavedHistCustomerDetails(final int customerGroupSystemId, final int versionNo) throws SystemException, PortalException, ParseException {

        LOGGER.debug("Entering getSavedCustomerDetails with P1:int customerGroupSystemId=" + customerGroupSystemId);
        final HashMap companiesMap = new HashMap();
        final List<CustomerDetailsDTO> customerDetailsList = new ArrayList<>();
        final DynamicQuery companyGroupDynamicQuery = DynamicQueryFactoryUtil.forClass(HistCompanyGroupDetails.class);
        companyGroupDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COMPANY_GROUP_SYS_ID, customerGroupSystemId));
        companyGroupDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.PRIMARY_KEY + ConstantsUtils.ACTION_FLAG, "D"));
        companyGroupDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.PRIMARY_KEY + ConstantsUtils.VERSION_NO, versionNo));
        final List<HistCompanyGroupDetails> resultList = dao.getCompanyGroupDetailsList(companyGroupDynamicQuery);
        for (final Iterator<HistCompanyGroupDetails> iterator = resultList.iterator(); iterator.hasNext();) {
            final List companies = new ArrayList();
            final HistCompanyGroupDetails companyGroupDetail = iterator.next();
            companiesMap.put(companyGroupDetail.getCompanyMasterSid(), companyGroupDetail);
            companies.add(companyGroupDetail.getCompanyMasterSid());
            final DynamicQuery companyDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
            companyDynamicQuery.add(RestrictionsFactoryUtil.in(ConstantsUtils.COMPANY_SYS_ID, companies));
            final List<CompanyMaster> companiesList = dao.getCompanyMasterList(companyDynamicQuery);

            for (final Iterator<CompanyMaster> companyIterator = companiesList.iterator(); companyIterator.hasNext();) {
                final CompanyMaster customer = companyIterator.next();
                final CustomerDetailsDTO customerDetailsDTO = new CustomerDetailsDTO();
                customerDetailsDTO.setOrganizationKey(customer.getOrgKey());
                customerDetailsDTO.setCustomerId(customer.getCompanyId());
                customerDetailsDTO.setCustomerNo(customer.getCompanyNo());
                customerDetailsDTO.setCustomerName(customer.getCompanyName());
                final String customerType = CommonUtil.getDescriptionFromHelper(customer.getCompanyType());
                customerDetailsDTO.setCustomerType(customerType);
                final String customerStatus = CommonUtil.getDescriptionFromHelper(customer.getCompanyStatus());
                customerDetailsDTO.setCustomerStatus(customerStatus);
                final ProjectionList projList = ProjectionFactoryUtil.projectionList();
                projList.add(ProjectionFactoryUtil.property(ConstantsUtils.TRADE_CLASS));
                projList.add(ProjectionFactoryUtil.property(TRADE_CLASS_START_DATE));
                projList.add(ProjectionFactoryUtil.property(TRADE_CLASS_END_DATE));
                final List tradeClassList = CompanyTradeClassLocalServiceUtil.getTradeClassDetails(String.valueOf(customer.getCompanyMasterSid()),
                        String.valueOf(companyGroupDetail.getCompanyTradeclassSid()));
                if (tradeClassList != null && !tradeClassList.isEmpty()) {
                        final Object[] obj = (Object[]) tradeClassList.get(0);
                        customerDetailsDTO.setTradeClass(CommonUtil.getDescriptionFromHelper(Integer.valueOf(obj[0].toString())));
                        if (obj[1] != null) {
                            customerDetailsDTO.setTradeClassStartDate(dateFormat.parse(CommonUtils.convertDateToString((Date) obj[1])));
                        }
                        if (obj[NumericConstants.TWO] != null) {
                            customerDetailsDTO.setTradeClassEndDate(dateFormat.parse(CommonUtils.convertDateToString((Date) obj[NumericConstants.TWO])));
                        }
                }

                customerDetailsDTO.setCustomerGroup(customer.getCompanyGroup());
                customerDetailsDTO.setFinancialSystem(customer.getFinancialSystem());
                customerDetailsDTO.setAddress1(customer.getAddress1());
                customerDetailsDTO.setAddress2(customer.getAddress2());
                customerDetailsDTO.setCity(customer.getCity());
                customerDetailsDTO.setState(String.valueOf(customer.getState()));
                customerDetailsDTO.setZipCode(customer.getZipCode());
                customerDetailsDTO.setCountry(String.valueOf(customer.getCountry()));
                customerDetailsDTO.setRegionCode(customer.getRegionCode());
                if (customer.getCompanyStartDate() != null) {
                    customerDetailsDTO.setCustomerStartDate(dateFormat.parse(CommonUtils.convertDateToString(customer.getCompanyStartDate())));

                }
                customerDetailsDTO.setCompanySystemId(customer.getCompanyMasterSid());
                customerDetailsDTO.setParentSysId(Integer.valueOf(!"null".equals(companyGroupDetail.getCompanyParentDetailsSid()) && !StringUtils.isBlank(companyGroupDetail.getCompanyParentDetailsSid()) ? companyGroupDetail.getCompanyParentDetailsSid() : "0"));
                customerDetailsDTO.setTradeClassSysId(companyGroupDetail.getCompanyTradeclassSid());
                if (customer.getCompanyMasterSid() != ConstantsUtils.ZERO_NUM) {
                    final List resultList2 = CompanyTradeClassLocalServiceUtil.getCompanyparentDetails(String.valueOf(customer.getCompanyMasterSid()),
                            String.valueOf(companyGroupDetail.getCompanyParentDetailsSid()));
                    if (resultList2 != null && !resultList2.isEmpty()) {

                        final Object[] objectValue = (Object[]) resultList2.get(0);
                        if (objectValue[1] != null) {
                            customerDetailsDTO.setParentStartDate(dateFormat.parse(CommonUtils.convertDateToString((Date) objectValue[1])));
                        }
                        if (objectValue[NumericConstants.TWO] != null) {
                            customerDetailsDTO.setParentEndDate(dateFormat.parse(CommonUtils.convertDateToString((Date) objectValue[NumericConstants.TWO])));
                        }
                        if (objectValue[NumericConstants.THREE] != null) {
                            customerDetailsDTO.setPriorParentStartDate(dateFormat.parse(CommonUtils.convertDateToString((Date) objectValue[NumericConstants.THREE])));
                        }

                        if (Integer.valueOf(objectValue[0].toString()) != ConstantsUtils.ZERO_NUM) {

                            final List parentList = CompanyMasterLocalServiceUtil.getPriorParentNo(objectValue[0].toString());
                            if (parentList != null && !parentList.isEmpty()) {
                                    final Object obje = parentList.get(0);
                                    customerDetailsDTO.setParentCustomerNo(obje.toString());
                            }
                            if (objectValue[NumericConstants.FOUR] != null && Integer.valueOf(objectValue[NumericConstants.FOUR].toString()) != ConstantsUtils.ZERO_NUM) {
                                    final List parentSysIdList = CompanyMasterLocalServiceUtil.getPriorParentNo(objectValue[0].toString());
                                    if (parentSysIdList != null && parentSysIdList.size() > ConstantsUtils.ZERO_NUM) {
                                            final Object obje = parentSysIdList.get(0);
                                            customerDetailsDTO.setParentCustomerNo(obje.toString());
                                }
                            }
                        }
                    }
                }
                customerDetailsList.add(customerDetailsDTO);
            }
        }
        LOGGER.debug("getSavedCustomerDetails return companyDetailsList=" + customerDetailsList.size());
        return customerDetailsList;

    }

    /**
     * Delete customer group.
     *
     * @param companyGroupSystemId the company group system id
     * @param versionNo the version no
     * @return the string
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public String deleteCustomerGroup(final int companyGroupSystemId, final int versionNo) throws SystemException, PortalException {

        CompanyGroup companyGroup;
        List<CompanyGroupDetails> companiesList;
        LOGGER.debug("Entering deleteCustomerGroup with P1:int companyGroupSystemId=" + companyGroupSystemId + " versionNo=" + versionNo);
        int count;
        final DynamicQuery projectionMasterQuery = DynamicQueryFactoryUtil.forClass(ProjectionMaster.class);
        projectionMasterQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COMPANY_GROUP_SYS_ID, String.valueOf(companyGroupSystemId)));
        count = dao.getProjectionCount(projectionMasterQuery);
        // delete item details
        if (count > 0) {
            MessageBox.showPlain(Icon.ERROR, "Delete", "Cannot Delete the Company group which are already associated with existing projection", ButtonId.OK);
            return StringUtils.EMPTY;
        } else {
            // delete item details
            final DynamicQuery itemDetailsDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyGroupDetails.class);
            itemDetailsDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COMPANY_GROUP_SYS_ID, companyGroupSystemId));
            itemDetailsDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.VERSION_NO, versionNo));
            companiesList = dao.getCompanyGroupDetailsList(itemDetailsDynamicQuery);

            if (!(companiesList.isEmpty())) {
                for (final Iterator<CompanyGroupDetails> iterator = companiesList.iterator(); iterator.hasNext();) {
                    final CompanyGroupDetails companyDetails = iterator.next();
                    dao.deleteCompanyGroupDetails(companyDetails);
                }
            }
            companyGroup = dao.deleteCompanyGroup(companyGroupSystemId);
            final String deletedCustomerGroupName = companyGroup.getCompanyGroupName();
            LOGGER.debug("deleteCustomerGroup return deletedCustomerGroupName=" + deletedCustomerGroupName);
            return deletedCustomerGroupName;
        }
    }

    /**
     * Gets the existing customergroup names.
     *
     * @return the existing customergroup names
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public Map getExistingCustomergroupNames() throws SystemException {
        LOGGER.debug("Entering getExistingCustomergroupNames");
        final Map results = new HashMap();
        final DynamicQuery companyDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyGroup.class);
        final ProjectionList projList = ProjectionFactoryUtil.projectionList();
        projList.add(ProjectionFactoryUtil.property(ConstantsUtils.COMPANY_GROUP_SYS_ID));
        projList.add(ProjectionFactoryUtil.property(ConstantsUtils.COMPANY_GROUP_NAME));
        companyDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));
        final List<Object[]> resultList = dao.getCompanyGroupList(companyDynamicQuery);

        for (final Iterator<Object[]> iterator = resultList.iterator(); iterator.hasNext();) {
            final Object[] obj = iterator.next();
            results.put(String.valueOf(obj[0]), String.valueOf(obj[1]));
        }
        LOGGER.debug("getExistingCustomergroupNames return HashMap results size" + results.size());
        return results;

    }

    /**
     * Gets the existing version.
     *
     * @param customerGroupSystemId the customer group system id
     * @return the existing version
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public int getExistingVersion(final int customerGroupSystemId) {
        LOGGER.debug("getExistingVersion started");

        int version = ConstantsUtils.ZERO_NUM;
        try {
            final DynamicQuery customerGroupHistoryDynamicQuery = DynamicQueryFactoryUtil.forClass(HistCompanyGroup.class);
            customerGroupHistoryDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.PRIMARY_KEY + ConstantsUtils.COMPANY_GROUP_SYS_ID, customerGroupSystemId));
            final ProjectionList projList = ProjectionFactoryUtil.projectionList();
            projList.add(ProjectionFactoryUtil.property(ConstantsUtils.PRIMARY_KEY + ConstantsUtils.VERSION_NO));
            customerGroupHistoryDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));

            final List<Integer> finalList = new ArrayList<>();
            final List<Integer> historyList = dao.getCompanyGroupDetailsHistoryList(customerGroupHistoryDynamicQuery);
            finalList.addAll(historyList);
            final int size = finalList.size();
            if (size > ConstantsUtils.ZERO_NUM) {
                Collections.sort(finalList);
                version = finalList.get(size - 1);
            }
            LOGGER.debug("getExistingCompanyGroupNames return ");
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return version;
    }

    public List getAvailableSearchResults(final String searchCriteria) {
        LOGGER.debug("Entering getAvailableSearchResults");
        return CompanyMasterLocalServiceUtil.getAvailableSearchResults(searchCriteria);
    }

    public int getCustomerSearchResultsCount(final ErrorfulFieldGroup customerGroupForm) {
        List<String> criteria = getSearchCriteria(customerGroupForm, 0);
        try {
            final List resultList = CompanyMasterLocalServiceUtil.getCustomerSearchDetails(criteria.get(0), criteria.get(1), criteria.get(2), criteria.get(3), criteria.get(4), criteria.get(5), criteria.get(6), criteria.get(7));
            return resultList != null ? resultList.size() : 0;
        } catch (Exception e) {

            LOGGER.error(e);
            return 0;
        }
    }

    public String duplicateCheck(ErrorfulFieldGroup binder) {
        String noQuery = "select count(*) from dbo.COMPANY_GROUP where COMPANY_GROUP_NAME='" + String.valueOf(binder.getField("customerGroupName").getValue()).trim() + "'";
        List resultList = (List) HelperTableLocalServiceUtil.executeSelectQuery(noQuery);
        if (Integer.valueOf(String.valueOf(resultList.get(0))) > 0) {
            return "NAMEEXIST";
        }
        noQuery = "select count(*) from dbo.COMPANY_GROUP where COMPANY_GROUP_NO='" + String.valueOf(binder.getField("customerGroupNo").getValue()).trim() + "'";
        resultList = (List) HelperTableLocalServiceUtil.executeSelectQuery(noQuery);
        if (Integer.valueOf(String.valueOf(resultList.get(0))) > 0) {
            return "NOEXIST";
        }
        return "S";
    }

    /**
     * Method to load the count
     *
     * @param dto
     * @return getCompaniesCount
     */
    public int getCompaniesCount(CustomerDetailsDTO dto, Set<Container.Filter> filterSet) {
        List list = dto.isAvailableContainer() ? getAvailableCompaniesInput(dto, filterSet, null) : getSelectedCompaniesInput(dto, filterSet, null);
        int count = getCount(QueryReader.getAppData(list, dto.getQueryName(), null));
        return count;
    }

    /**
     * Method to load the count
     *
     * @param dto
     * @return getCompaniesCount
     */
    public List<CustomerDetailsDTO> getCompaniesResults(CustomerDetailsDTO dto, Set<Container.Filter> filterSet, List<SortByColumn> sortedSet) throws ParseException {
        List list = dto.isAvailableContainer() ? getAvailableCompaniesInput(dto, filterSet, sortedSet) : getSelectedCompaniesInput(dto, filterSet, sortedSet);
        List<Object[]> resultList = (List<Object[]>) QueryReader.getAppData(list, dto.getQueryName(), null);
        List<CustomerDetailsDTO> customizedList = getCustomizedCustomerResults(resultList, new HashMap());
        return customizedList;
    }

    /**
     * Method to get the inputs
     *
     * @param dto
     * @return List
     */
    private List getAvailableCompaniesInput(CustomerDetailsDTO dto, Set<Container.Filter> filterSet, List<SortByColumn> sortedSet) {
        List list = new ArrayList<>();
        if (dto.getMaster_Sid_List() != null && !dto.getMaster_Sid_List().isEmpty()) {
            list.add(CommonUtils.CollectionToString(dto.getMaster_Sid_List(), false));
        } else {
            list.add(0);
        }
        
        if (dto.getTradeClass() != null && !dto.getTradeClass().isEmpty()) {
            list.add(dto.getTradeClass().replace(ConstantsUtils.ASTERISK, ConstantsUtils.PERCENT));
        } else {
            list.add(ConstantsUtils.PERCENT);
        }
         if (dto.getState() != null && !dto.getState().isEmpty()) {
            list.add(dto.getState().replace(ConstantsUtils.ASTERISK, ConstantsUtils.PERCENT));
        } else {
            list.add(ConstantsUtils.PERCENT);
        }
        if (dto.getCustomerNo() != null && !dto.getCustomerNo().isEmpty()) {
            list.add(dto.getCustomerNo().replace(ConstantsUtils.ASTERISK, ConstantsUtils.PERCENT));
        } else {
            list.add(ConstantsUtils.PERCENT);
        }
        if (dto.getCustomerStatus() != null && !dto.getCustomerStatus().isEmpty()) {
            list.add(dto.getCustomerStatus().replace(ConstantsUtils.ASTERISK, ConstantsUtils.PERCENT));
        } else {
            list.add(ConstantsUtils.PERCENT);
        }
        if (dto.getCustomerName() != null && !dto.getCustomerName().isEmpty()) {
            list.add(dto.getCustomerName().replace(ConstantsUtils.ASTERISK, ConstantsUtils.PERCENT));
        } else {
            list.add(ConstantsUtils.PERCENT);
        }
        if (dto.getCustomerType() != null && !dto.getCustomerType().isEmpty()) {
            list.add(dto.getCustomerType().replace(ConstantsUtils.ASTERISK, ConstantsUtils.PERCENT));
        } else {
            list.add(ConstantsUtils.PERCENT);
        }
        if (dto.getCity() != null && !dto.getCity().isEmpty()) {
            list.add(dto.getCity().replace(ConstantsUtils.ASTERISK, ConstantsUtils.PERCENT));
        } else {
            list.add(ConstantsUtils.PERCENT);
        }
        if (dto.getZipCode() != null && !dto.getZipCode().isEmpty()) {
            list.add(dto.getZipCode().replace(ConstantsUtils.ASTERISK, ConstantsUtils.PERCENT));
        } else {
            list.add(ConstantsUtils.PERCENT);
        }
        if (dto.getState() != null && !dto.getState().isEmpty()) {
            list.add(dto.getState().replace(ConstantsUtils.ASTERISK, ConstantsUtils.PERCENT));
            list.add(StringUtils.EMPTY);
        } else {
            list.add(ConstantsUtils.PERCENT);
            list.add("OR HT_ST.DESCRIPTION IS NULL");
        }
        StringBuilder filteredQuery = AbstractFilterLogic.getInstance().filterQueryGenerator(filterSet, getFilterMap());
        if (filteredQuery != null) {
            list.add(filteredQuery.toString().replace("where", " AND "));
        } else {
            list.add(" ");
        }
        if (!dto.isCount()) {
            StringBuilder sortedQuery = AbstractFilterLogic.getInstance().orderByQueryGenerator(sortedSet, getFilterMap(), StringUtils.EMPTY);
            list.add(sortedQuery.toString());
            list.add(dto.getStart());
            list.add(dto.getOffset());
        }
        return list;
    }

    /**
     * Method to get the inputs
     *
     * @param dto
     * @return List
     */
    private List getSelectedCompaniesInput(CustomerDetailsDTO dto, Set<Container.Filter> filterSet, List<SortByColumn> sortedSet) {
        List list = new ArrayList<>();
        if (!dto.isEditMode()) {
            list.add(CommonUtils.CollectionToString(dto.getMaster_Sid_List(), false));
        } else {
            list.add(dto.getCompanySystemId()); // this variable hold company group master sid
            list.add(dto.getVersionNo());
        }
        StringBuilder filteredQuery = AbstractFilterLogic.getInstance().filterQueryGenerator(filterSet, getFilterMap());
        if (filteredQuery != null) {
            list.add(filteredQuery.toString().replace("where", " AND "));
        } else {
            list.add(" ");
        }
        if (!dto.isCount()) {
            StringBuilder sortedQuery = AbstractFilterLogic.getInstance().orderByQueryGenerator(sortedSet, getFilterMap(), StringUtils.EMPTY);
            list.add(sortedQuery.toString());
            list.add(dto.getStart());
            list.add(dto.getOffset());
        }

        return list;
    }

    /**
     * MEthod to convert object to Integer for count
     *
     * @param list
     * @return count
     */
    private int getCount(List<Object[]> list) {
        if (!list.isEmpty()) {
            Object obj = list.get(0);
            int count = obj == null ? 0 : (Integer) obj;
            return count;
        }
        return 0;
    }

    private Map<String, String> getFilterMap() {
        Map<String, String> inputMap = new HashMap<>();
        inputMap.put("organizationKey", "CM.ORGANIZATION_KEY");
        inputMap.put("customerId", "CM.COMPANY_ID");
        inputMap.put("customerNo", "CM.COMPANY_NO");
        inputMap.put("customerName", "CM.COMPANY_NAME");
        inputMap.put("tradeClass", "CTC.DESCRIPTION");
        inputMap.put(TRADE_CLASS_START_DATE, "CTC.TRADE_CLASS_START_DATE");
        inputMap.put(TRADE_CLASS_END_DATE, "CTC.TRADE_CLASS_END_DATE");
        inputMap.put("customerType", "HT_CT.DESCRIPTION");
        inputMap.put("customerStatus", "HT_CS.DESCRIPTION");
        inputMap.put("lives", "CM.LIVES");
        inputMap.put("customerStartDate", "CM.COMPANY_START_DATE");
        inputMap.put("customerEndDate", "CM.COMPANY_END_DATE");
        inputMap.put("customerGroup", "CM.COMPANY_GROUP");
        inputMap.put("financialSystem", "CM.FINANCIAL_SYSTEM");
        inputMap.put("address1", "CM.ADDRESS1");
        inputMap.put("address2", "CM.ADDRESS2");
        inputMap.put("city", "CM.CITY");
        inputMap.put("state", "HT_ST.DESCRIPTION");
        inputMap.put("zipCode", "CM.ZIP_CODE");
        inputMap.put("country", "HT_CN.DESCRIPTION");
        inputMap.put("regionCode", "CM.REGION_CODE");
        inputMap.put("parentCustomerNo", "CM.COMPANY_NO");
        inputMap.put("parentStartDate", "CPD.PARENT_START_DATE");
        inputMap.put("parentEndDate", "CPD.PARENT_END_DATE");
        inputMap.put("priorParentStartDate", "CPD.PRIOR_PARENT_START_DATE");
        inputMap.put("priorParentCustomerNo", "CPD.PRIOR_PARENT_CMPY_MASTER_SID");
        inputMap.put("SID", "CM.COMPANY_MASTER_SID");
        inputMap.put("udc1", "UDC1");
        inputMap.put("udc2", "UDC2");
        inputMap.put("udc3", "UDC3");
        inputMap.put("udc4", "UDC4");
        inputMap.put("udc5", "UDC5");
        inputMap.put("udc6", "UDC6");
        return inputMap;
    }

    /**
     * GetCombinations of keys
     *
     * @param masterSid
     * @return String
     */
    public String getCombinationsKey(Set<String> masterSid) {
        String input;
        StringBuilder str = new StringBuilder(StringUtils.EMPTY);
        for (String string : masterSid) {
            str.append(" SELECT ").append(string.replace("~", ",")).append(" UNION ALL \n ");
        }
        input = str.substring(0, str.lastIndexOf("UNION ALL"));
        return input;
    }

    /**
     * getCompaniesFromQuery
     *
     * @param dto
     * @param filterSet
     * @param sortedSet
     * @return string
     */
    public Set<String> getCompaniesFromQuery(CustomerDetailsDTO dto, Set<Container.Filter> filterSet, List<SortByColumn> sortedSet) {
        dto.setCount(Boolean.FALSE);
        List input = new ArrayList<>(getAvailableCompaniesInput(dto, filterSet, sortedSet));
        dto.setCount(Boolean.TRUE);
        input.remove(0);
        input.remove(input.size() - 1);
        input.remove(input.size() - 1);

        Set<String> set = new LinkedHashSet<>();
        List<Object[]> results = (List<Object[]>) QueryReader.getAppData(input, "getCustomerDetailsForCombinations", null);
        if (results != null && !results.isEmpty()) {
            for (int i = 0; i < results.size(); i++) {
                Object[] obj = (Object[]) results.get(i);
                String companyMasterSid = obj[0] != null && !"null".equals(String.valueOf(obj[0])) && !"0".equals(String.valueOf(obj[0])) ? String.valueOf(obj[0]) : "null";
                set.add(companyMasterSid);
            }
        }
        return set;
    }

    /**
     * Save In ADD MODE
     *
     * @param customerDetailsDTO
     * @param sessionDTO
     * @param masterSidsList
     * @return List<Integer>
     */
    public List<Integer> saveCGM(CustomerGroupDTO customerDetailsDTO, SessionDTO sessionDTO, Set<String> masterSidsList) throws SystemException {
        List<Integer> integerList = new ArrayList<>(NumericConstants.TWO);
        List list;
        String queryName;
        if (!ConstantsUtils.LOWERCASE_EDIT.equalsIgnoreCase(sessionDTO.getLogic())) {
            list = getAddModeSaveInput(customerDetailsDTO, sessionDTO, masterSidsList);
            queryName = "insert-Add-Mode";
        } else {
            list = getEditModeSaveInput(sessionDTO, masterSidsList);
            queryName = "update-Edit-Mode";
        }
        List<Object[]> resultList = (List<Object[]>) QueryReader.getAppData(list, queryName, null);
        if (resultList != null && !resultList.isEmpty()) {
            Object[] obj = resultList.get(0);
            int sysId = obj[0] != null ? Integer.valueOf(String.valueOf(obj[0])) : 0;
            int version = obj[1] != null ? Integer.valueOf(String.valueOf(obj[1])) : 1;
            integerList.add(sysId);
            integerList.add(version);
        }
        return integerList;
    }

    /**
     * getAddModeSaveInput
     *
     * @param customerDetailsDTO
     * @param sessionDTO
     * @param masterSidsList
     * @return
     */
    private List getAddModeSaveInput(CustomerGroupDTO customerDetailsDTO, SessionDTO sessionDTO, Set<String> masterSidsList) {
        List list = new ArrayList<>(NumericConstants.SIX);
        list.add(CommonUtils.CollectionToString(masterSidsList, false));
        list.add(sessionDTO.getUserId());
        list.add(customerDetailsDTO.getCustomerGroupNo());
        list.add(customerDetailsDTO.getCustomerGroupName());
        list.add(customerDetailsDTO.getCustomerGroupDesc());
        list.add(customerDetailsDTO.getSearchCriteria());
        return list;
    }

    /**
     * getEditModeSaveInput
     *
     * @param customerDetailsDTO
     * @param sessionDTO
     * @param masterSidsList
     * @return
     */
    private List getEditModeSaveInput(SessionDTO sessionDTO, Set<String> masterSidsList) throws SystemException {
        List list = new ArrayList<>(NumericConstants.THREE);
        int versionNo = getExistingVersion(sessionDTO.getSystemId());
        list.add(sessionDTO.getSystemId());
        list.add(CommonUtils.CollectionToString(masterSidsList, false));
        list.add(versionNo + 1);
        return list;
    }

     /**
     * Method to get the Selected companies list
     *
     * @param systemId
     * @param mode
     * @return Set<String>
     */
    public Set<String> getSelectedCompanyMasterSids(int systemId) {
        List input = new ArrayList<>();
        input.add(systemId);
        List<Object[]> resultList;
        
        if (ConstantsUtils.VIEW.equalsIgnoreCase(sessionDTO.getLogic())) {
            resultList = QueryReader.getAppData(input, "getSelectedCompaniesSidForView", "");
        } else {
            resultList = QueryReader.getAppData(input, "getSelectedCompaniesSid", "");
        }
        
        Set<String> masterListSid = new LinkedHashSet<>();
        if (resultList != null && !resultList.isEmpty()) {
            for (int i = 0; i < resultList.size(); i++) {
                Object[] obj = resultList.get(i);
                String companyMasterSid = obj[1] != null ? String.valueOf(obj[1]) : StringUtils.EMPTY;
                masterListSid.add(companyMasterSid);
            }
        }
        return masterListSid;
    }
}
