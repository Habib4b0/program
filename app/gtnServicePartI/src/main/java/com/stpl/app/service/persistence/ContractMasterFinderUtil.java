package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;


public class ContractMasterFinderUtil {
    private static ContractMasterFinder _finder;

    public static java.util.List getContractPriceInfo(int contractSystemId,
        int cfpId, int ifpId, int psId) {
        return getFinder()
                   .getContractPriceInfo(contractSystemId, cfpId, ifpId, psId);
    }

    public static java.util.List getTradingPartnerList(
        java.lang.String companyId, java.lang.String companyNo,
        java.lang.String companyName, int companyStatus, int companyType,
        java.util.Map<java.lang.String, java.lang.Object> filterMap, int start,
        int offset, java.lang.String column, java.lang.String orederBy) {
        return getFinder()
                   .getTradingPartnerList(companyId, companyNo, companyName,
            companyStatus, companyType, filterMap, start, offset, column,
            orederBy);
    }

    public static java.util.List getContractList(java.lang.String contractId,
        java.lang.String contractNo, java.lang.String contractName,
        int contractStatus, int contractType, java.lang.String tradeClass,
        int tradingPartner,
        java.util.Map<java.lang.String, java.lang.Object> filterMap,
        java.lang.String orderBy, java.lang.String column, int start,
        int offset, boolean isCount) {
        return getFinder()
                   .getContractList(contractId, contractNo, contractName,
            contractStatus, contractType, tradeClass, tradingPartner,
            filterMap, orderBy, column, start, offset, isCount);
    }

    public static java.util.List fetchFieldsForSecurity(
        java.lang.String moduleName, java.lang.String tabName,
        java.lang.Object obj1, java.lang.Object obj2, java.lang.Object obj3) {
        return getFinder()
                   .fetchFieldsForSecurity(moduleName, tabName, obj1, obj2, obj3);
    }

    public static java.util.List searchContractsForPromoteTp(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getFinder().searchContractsForPromoteTp(parameters);
    }

    public static java.lang.Object executeSelectQueries(
        java.lang.String[] queries) {
        return getFinder().executeSelectQueries(queries);
    }

    public static ContractMasterFinder getFinder() {
        if (_finder == null) {
            _finder = (ContractMasterFinder) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    ContractMasterFinder.class.getName());

            ReferenceRegistry.registerReference(ContractMasterFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(ContractMasterFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(ContractMasterFinderUtil.class,
            "_finder");
    }
}
