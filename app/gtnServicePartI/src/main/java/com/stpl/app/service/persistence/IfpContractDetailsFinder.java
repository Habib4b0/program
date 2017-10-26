package com.stpl.app.service.persistence;

public interface IfpContractDetailsFinder {
    public java.lang.Boolean saveIfpDetailsAttached(
        java.util.List<java.lang.Object> input, java.lang.Object future);

    public java.util.List findIFP(java.lang.Object field,
        java.lang.Object value, java.util.List<java.lang.Integer> future,
        java.util.Map<java.lang.String, java.lang.Object> filterMap, int start,
        int end, java.lang.String column, java.lang.String orderBy,
        java.lang.Object future1);
}
