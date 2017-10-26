package com.stpl.app.service.persistence;

public interface MSalesProjectionMasterFinder {
    public java.lang.Object executeSelectQuery(java.lang.String query,
        java.lang.Object udc1, java.lang.Object udc2);

    public java.lang.Object executeUpdateQuery(
        java.util.List<java.lang.StringBuilder> queryList,
        java.lang.Object obj1, java.lang.Object obj2);

    public java.util.List executeUpdateQuery(java.lang.String query,
        java.lang.Object obj1, java.lang.Object obj2);

    public java.util.List getAssumptionResult(java.util.List input,
        java.lang.String queryName);

    public java.lang.Object executeUpdateSQL(java.lang.String query,
        java.lang.Object obj1, java.lang.Object obj2);

    public java.lang.Object executeUpdateQuery(java.util.List<?> nmSalesList,
        java.lang.Object udc1, java.lang.Object udc2, java.lang.Object udc3);
}
