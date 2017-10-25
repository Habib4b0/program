package com.stpl.app.service.persistence;

public interface ItemGroupFinder {
    public java.util.List getItemGroupMaster(java.lang.String itemGroupName);

    public java.util.List getItemGroupDetails(java.lang.String itemType,
        java.lang.String itemDesc, java.lang.String brand,
        java.lang.String strength, java.lang.String itemNoCriteria,
        java.lang.String therapeuticCriteria, java.lang.String formCriteria,
        java.lang.String selectedItemIds);

    public java.util.List getAvailableSearchResults(
        java.lang.String finalCriteria);
}
