package com.stpl.app.service.persistence;

public interface HistRelationshipBuilderFinder {
    public java.util.List findByTableName(java.lang.String tableName,
        java.lang.String columnName);

    public java.util.List findByTableName(java.lang.String tableName,
        java.lang.String columnName, java.util.List hierListValues);

    public java.util.List findFilteredLevelValues(java.lang.String query);

    public java.util.List executeQuery(java.lang.String query);
}
