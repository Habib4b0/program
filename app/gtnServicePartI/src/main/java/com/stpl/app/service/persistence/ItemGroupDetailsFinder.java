package com.stpl.app.service.persistence;

public interface ItemGroupDetailsFinder {
    public java.util.List getProductGroups(java.lang.String productGroupNo,
        java.lang.String productGroupName, java.lang.String company,
        java.lang.String segment, int startIndex, int offset,
        java.util.Set<com.vaadin.data.Container.Filter> filters,
        java.util.List<org.asi.ui.extfilteringtable.paged.logic.SortByColumn> sortByColumns);

    public java.util.List getItemMasterRecords(java.lang.String[] query);
}
