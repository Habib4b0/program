package com.stpl.app.service.persistence;

public interface ContractMasterFinder {
    public java.util.List getContractPriceInfo(int contractSystemId, int cfpId,
        int ifpId, int psId);

    public java.util.List getTradingPartnerList(java.lang.String companyId,
        java.lang.String companyNo, java.lang.String companyName,
        int companyStatus, int companyType,
        java.util.Map<java.lang.String, java.lang.Object> filterMap, int start,
        int offset, java.lang.String column, java.lang.String orederBy);

    public java.util.List getContractList(java.lang.String contractId,
        java.lang.String contractNo, java.lang.String contractName,
        int contractStatus, int contractType, java.lang.String tradeClass,
        int tradingPartner,
        java.util.Map<java.lang.String, java.lang.Object> filterMap,
        java.lang.String orderBy, java.lang.String column, int start,
        int offset, boolean isCount);

    public java.util.List fetchFieldsForSecurity(java.lang.String moduleName,
        java.lang.String tabName, java.lang.Object obj1, java.lang.Object obj2,
        java.lang.Object obj3);

    public java.util.List searchContractsForPromoteTp(
        java.util.Map<java.lang.String, java.lang.Object> parameters);

    public java.lang.Object executeSelectQueries(java.lang.String[] queries);
}
