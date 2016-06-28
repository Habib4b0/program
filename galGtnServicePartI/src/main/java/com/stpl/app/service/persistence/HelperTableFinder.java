package com.stpl.app.service.persistence;

public interface HelperTableFinder {
    public java.util.List executeSelectQuery(java.lang.String query);

    public void executeUpdateQuery(java.lang.String query);

    public int executeUpdateQueryCount(java.lang.String query);
}
