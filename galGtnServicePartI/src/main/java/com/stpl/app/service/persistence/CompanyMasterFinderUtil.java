package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;


public class CompanyMasterFinderUtil {
    private static CompanyMasterFinder _finder;

    public static java.util.List findCompanyMaster(java.lang.String companyId,
        java.lang.String companyNo, java.lang.String companyName,
        java.lang.String companyStatus, java.lang.String companyType,
        java.lang.String tradeClass, int identifierType,
        java.lang.String identifier, java.lang.String orderByColumn,
        java.lang.Boolean sortOrder) {
        return getFinder()
                   .findCompanyMaster(companyId, companyNo, companyName,
            companyStatus, companyType, tradeClass, identifierType, identifier,
            orderByColumn, sortOrder);
    }

    public static java.util.List findCompanyMasterV1(
        java.lang.String companyId, java.lang.String companyNo,
        java.lang.String companyName, java.lang.String companyStatus,
        java.lang.String companyType, java.lang.String companyCategory,
        java.lang.String companyGroup, java.lang.String tradeClass,
        int identifierType, java.lang.String identifier,
        java.lang.String orderByColumn, java.lang.Boolean sortOrder,
        java.lang.Object index, java.lang.Object next,
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getFinder()
                   .findCompanyMasterV1(companyId, companyNo, companyName,
            companyStatus, companyType, companyCategory, companyGroup,
            tradeClass, identifierType, identifier, orderByColumn, sortOrder,
            index, next, parameters);
    }

    public static java.util.List getCompanyTradeClass(int companySystemId) {
        return getFinder().getCompanyTradeClass(companySystemId);
    }

    public static java.util.List getCompanyTradeClassUniqueCheck(
        java.lang.String tradeClass, java.util.Date tradeStartDate) {
        return getFinder()
                   .getCompanyTradeClassUniqueCheck(tradeClass, tradeStartDate);
    }

    public static java.util.List deleteCompanyTradeClassForUpdate(
        int companySystemId) {
        return getFinder().deleteCompanyTradeClassForUpdate(companySystemId);
    }

    public static java.util.List getCustomerSearchDetails(
        java.lang.String customerNo, java.lang.String tradeClass,
        java.lang.String customerStatus, java.lang.String state,
        java.lang.String customerName, java.lang.String customerType,
        java.lang.String city, java.lang.String zipCode) {
        return getFinder()
                   .getCustomerSearchDetails(customerNo, tradeClass,
            customerStatus, state, customerName, customerType, city, zipCode);
    }

    public static java.util.List getPriorParentNo(
        java.lang.String priorSystemId) {
        return getFinder().getPriorParentNo(priorSystemId);
    }

    public static java.util.List getAvailableSearchResults(
        java.lang.String searchCriteria) {
        return getFinder().getAvailableSearchResults(searchCriteria);
    }

    public static java.lang.Object executeSelectQuery(java.lang.String query,
        java.lang.Object udc1, java.lang.Object udc2) {
        return getFinder().executeSelectQuery(query, udc1, udc2);
    }

    public static java.util.List searchTPCompanies(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getFinder().searchTPCompanies(parameters);
    }

    public static java.util.List getColumnNames(java.lang.String tablename) {
        return getFinder().getColumnNames(tablename);
    }

    public static java.util.List getCompanyTypeCount(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getFinder().getCompanyTypeCount(parameters);
    }

    public static java.util.List searchCompanies(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getFinder().searchCompanies(parameters);
    }

    public static java.util.List executeQuery(java.lang.String query) {
        return getFinder().executeQuery(query);
    }

    public static int executeUpdateQuery(java.lang.String query) {
        return getFinder().executeUpdateQuery(query);
    }

    public static CompanyMasterFinder getFinder() {
        if (_finder == null) {
            _finder = (CompanyMasterFinder) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    CompanyMasterFinder.class.getName());

            ReferenceRegistry.registerReference(CompanyMasterFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(CompanyMasterFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(CompanyMasterFinderUtil.class,
            "_finder");
    }
}
