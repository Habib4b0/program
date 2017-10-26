package com.stpl.app.service.impl;

import java.util.Date;

import com.stpl.app.service.base.CompanyMasterLocalServiceBaseImpl;
import com.stpl.app.service.persistence.CompanyMasterFinderUtil;
import java.util.List;

/**
 * The implementation of the company master local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link com.stpl.app.service.CompanyMasterLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.base.CompanyMasterLocalServiceBaseImpl
 * @see com.stpl.app.service.CompanyMasterLocalServiceUtil
 */
public class CompanyMasterLocalServiceImpl
        extends CompanyMasterLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.stpl.app.global.service.CompanyMasterLocalServiceUtil} to access the company master local service.
     */

    public java.util.List findCompanyMaster(java.lang.String companyId,
            java.lang.String companyNo, java.lang.String companyName,
            java.lang.String companyStatus, java.lang.String companyType,
            java.lang.String tradeClass, int identifierType,
            java.lang.String identifier, String orderByColumn, Boolean sortOrder) {
        return CompanyMasterFinderUtil
                .findCompanyMaster(companyId, companyNo, companyName,
                        companyStatus, companyType, tradeClass, identifierType, identifier, orderByColumn, sortOrder);
    }

    public java.util.List getCompanyTradeClass(int companySystemId) {
        return CompanyMasterFinderUtil
                .getCompanyTradeClass(companySystemId);
    }

    public java.util.List getCompanyTradeClassUniqueCheck(String tradeClass, Date tradeStartDate) {
        return CompanyMasterFinderUtil
                .getCompanyTradeClassUniqueCheck(tradeClass, tradeStartDate);
    }

    public java.util.List deleteCompanyTradeClassForUpdate(int companySystemId) {
        return CompanyMasterFinderUtil
                .deleteCompanyTradeClassForUpdate(companySystemId);
    }

    public java.util.List findCompanyMasterV1(
            java.lang.String companyId, java.lang.String companyNo,
            java.lang.String companyName, java.lang.String companyStatus,
            java.lang.String companyType, java.lang.String companyCategory, java.lang.String companyGroup, java.lang.String tradeClass,
            int identifierType, java.lang.String identifier,
            java.lang.String orderByColumn, java.lang.Boolean sortOrder,
            java.lang.Object index, java.lang.Object next, java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return CompanyMasterFinderUtil
                .findCompanyMasterV1(companyId, companyNo, companyName,
                        companyStatus, companyType, companyCategory, companyGroup, tradeClass, identifierType, identifier,
                        orderByColumn, sortOrder, index, next, parameters);
    }

    public java.util.List getCustomerSearchDetails(java.lang.String customerNo, java.lang.String tradeClass, java.lang.String customerStatus, java.lang.String state, java.lang.String customerName,
            java.lang.String customerType, java.lang.String city, java.lang.String zipCode) {
        return CompanyMasterFinderUtil.getCustomerSearchDetails(customerNo, tradeClass, customerStatus, state, customerName, customerType, city, zipCode);
    }

    public java.util.List getPriorParentNo(java.lang.String priorSystemId) {
        return CompanyMasterFinderUtil.getPriorParentNo(priorSystemId);
    }

    public java.util.List getAvailableSearchResults(java.lang.String searchCriteria) {
        return CompanyMasterFinderUtil.getAvailableSearchResults(searchCriteria);
    }

    public Object executeSelectQuery(String query, Object udc1, Object udc2) {

        return CompanyMasterFinderUtil
                .executeSelectQuery(query, udc1, udc2);
    }

    public java.util.List searchTPCompanies(
            java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return CompanyMasterFinderUtil.searchTPCompanies(parameters);
    }

    public java.util.List getColumnNames(java.lang.String tablename) {
        return CompanyMasterFinderUtil.getColumnNames(tablename);
    }

    public java.util.List getCompanyTypeCount(
            java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return CompanyMasterFinderUtil.getCompanyTypeCount(parameters);
    }

    public java.util.List searchCompanies(
            java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return CompanyMasterFinderUtil.searchCompanies(parameters);
    }

    public java.util.List executeQuery(java.lang.String query) {
        return CompanyMasterFinderUtil.executeQuery(query);
    }

    public int executeUpdateQuery(java.lang.String query) {
        return CompanyMasterFinderUtil.executeUpdateQuery(query);
    }
}
