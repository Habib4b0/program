package com.stpl.app.service.persistence;

public interface PsDetailsFinder {
    public java.util.List getItemAndPricingForPs(int psSystemId);

    public java.util.List getPsSearchList(java.lang.String psId,
        java.lang.String psNo, java.lang.String psName, int psStatus,
        int psType, java.lang.String itemId, java.lang.String itemNo,
        java.lang.String itemName,
        java.util.Map<java.lang.String, java.lang.Object> filterMap, int start,
        int end, java.lang.String column, java.lang.String orderBy,
        boolean isCount);
}
