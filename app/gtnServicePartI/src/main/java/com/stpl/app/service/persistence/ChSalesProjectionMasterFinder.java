package com.stpl.app.service.persistence;

public interface ChSalesProjectionMasterFinder {
    public java.lang.Object executeSelectQuery(java.lang.String query,
        java.lang.Object udc1, java.lang.Object udc2);

    public java.lang.Object executeBulkUpdateQuery(java.lang.String query,
        java.lang.Object udc1, java.lang.Object udc2);

    public java.lang.Object executeUpdateQuery(java.util.List<?> nmSalesList,
        java.lang.Object udc1, java.lang.Object udc2, java.lang.Object udc3);

    public java.util.List getAssumptionResult(java.util.List input,
        java.lang.String queryName);
}
