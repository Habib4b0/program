package com.stpl.app.service.persistence;

public interface ItemMasterFinder {
    public java.util.List findItemMaster(java.lang.String itemId,
        java.lang.String itemNo, java.lang.String itemName,
        java.lang.String itemStatus, java.lang.String itemType,
        java.lang.String itemDesc, java.lang.String manufacturerId,
        int identifierType, java.lang.String start, java.lang.String offset,
        java.lang.String orderByColumn, java.lang.Boolean sortOrder,
        java.util.Map<java.lang.String, java.lang.Object> parameters);
}
