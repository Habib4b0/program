package com.stpl.app.service.persistence;

public interface ProjectionCustHierarchyFinder {
    public void insert(java.util.List list, java.lang.String string1,
        java.lang.String string2);

    public java.util.List retrive(java.util.List list,
        java.lang.String string1, java.lang.String string2);

    public java.util.List getComparisonSearch(java.lang.String workflowStatus,
        java.lang.String marketType, java.lang.String brand,
        java.lang.String projName, java.lang.String contHldr,
        java.lang.String ndcNo, java.lang.String ndcName,
        java.lang.String desc, java.lang.String contract,
        java.lang.String from, java.lang.String to);
}
