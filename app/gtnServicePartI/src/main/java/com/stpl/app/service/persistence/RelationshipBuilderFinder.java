package com.stpl.app.service.persistence;

public interface RelationshipBuilderFinder {
    public java.util.List findByTableName(java.lang.String tableName,
        java.lang.String columnName);

    public java.util.List findByTableName(java.lang.String tableName,
        java.lang.String columnName, java.util.List hierListValues);
}
