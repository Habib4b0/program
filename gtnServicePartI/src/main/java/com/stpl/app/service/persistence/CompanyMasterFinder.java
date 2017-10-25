package com.stpl.app.service.persistence;

public interface CompanyMasterFinder {
    public java.util.List findCompanyMaster(java.lang.String companyId,
        java.lang.String companyNo, java.lang.String companyName,
        java.lang.String companyStatus, java.lang.String companyType,
        java.lang.String tradeClass, int identifierType,
        java.lang.String identifier, java.lang.String orderByColumn,
        java.lang.Boolean sortOrder);

    public java.util.List findCompanyMasterV1(java.lang.String companyId,
        java.lang.String companyNo, java.lang.String companyName,
        java.lang.String companyStatus, java.lang.String companyType,
        java.lang.String companyCategory, java.lang.String companyGroup,
        java.lang.String tradeClass, int identifierType,
        java.lang.String identifier, java.lang.String orderByColumn,
        java.lang.Boolean sortOrder, java.lang.Object index,
        java.lang.Object next,
        java.util.Map<java.lang.String, java.lang.Object> parameters);

    public java.util.List getCompanyTradeClass(int companySystemId);

    public java.util.List getCompanyTradeClassUniqueCheck(
        java.lang.String tradeClass, java.util.Date tradeStartDate);

    public java.util.List deleteCompanyTradeClassForUpdate(int companySystemId);

    public java.util.List getCustomerSearchDetails(
        java.lang.String customerNo, java.lang.String tradeClass,
        java.lang.String customerStatus, java.lang.String state,
        java.lang.String customerName, java.lang.String customerType,
        java.lang.String city, java.lang.String zipCode);

    public java.util.List getPriorParentNo(java.lang.String priorSystemId);

    public java.util.List getAvailableSearchResults(
        java.lang.String searchCriteria);

    public java.lang.Object executeSelectQuery(java.lang.String query,
        java.lang.Object udc1, java.lang.Object udc2);

    public java.util.List searchTPCompanies(
        java.util.Map<java.lang.String, java.lang.Object> parameters);

    public java.util.List getColumnNames(java.lang.String tablename);

    public java.util.List getCompanyTypeCount(
        java.util.Map<java.lang.String, java.lang.Object> parameters);

    public java.util.List searchCompanies(
        java.util.Map<java.lang.String, java.lang.Object> parameters);

    public java.util.List executeQuery(java.lang.String query);

    public int executeUpdateQuery(java.lang.String query);
}
