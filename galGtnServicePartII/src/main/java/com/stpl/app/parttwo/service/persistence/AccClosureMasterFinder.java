package com.stpl.app.parttwo.service.persistence;

public interface AccClosureMasterFinder {
    public java.lang.Object executeSelectQuery(java.util.List input,
        java.lang.String queryName, java.lang.String quaryName2);

    public java.lang.Boolean executeUpdateQuery(java.util.List input,
        java.lang.String queryName);

    public java.lang.String getQuery(java.util.List input,
        java.lang.String queryName);
}
