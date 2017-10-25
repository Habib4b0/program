package com.stpl.app.service.persistence;

public interface FcpActualsFinder {
    public java.lang.Object executeSelectQuery(java.lang.String query);

    public java.lang.Object executeBulkUpdateQuery(java.lang.String query);

    public java.lang.Object executeUpdateQuery(
        java.util.List<java.lang.StringBuilder> queryList);

    public java.lang.Object executeUpdateQuery(java.lang.String query);
}
