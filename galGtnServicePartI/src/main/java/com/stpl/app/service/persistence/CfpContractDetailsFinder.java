package com.stpl.app.service.persistence;

public interface CfpContractDetailsFinder {
    public java.lang.Boolean saveCfpDetailsAttached(
        java.util.List<java.lang.Object> input, java.lang.Object future);

    public java.util.List getCompaniesList(java.lang.String searchField,
        java.lang.String searchVal,
        java.util.Map<java.lang.String, java.lang.Object> filterMap, int start,
        int offset, java.lang.String column, java.lang.String orderBy,
        java.lang.Object future1, java.lang.Object future2);
}
