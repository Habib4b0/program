package com.stpl.app.service.persistence;

public interface ImtdIfpDetailsFinder {
    public java.lang.Object deleteAll(java.lang.String userId,
        java.lang.String sessionId, java.lang.String dateCreated,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4);

    public java.lang.Object updateOperation(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object searchField,
        java.lang.Object searchValue, java.lang.Object searchList,
        java.lang.Object future4,
        java.util.Map<java.lang.String, java.lang.Object> parameters);

    public java.lang.Object updateForPopulate(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object populateField,
        java.lang.Object populateValue, java.lang.Object future3,
        java.lang.Object future4);

    public java.lang.Object updateForPopulateAll(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object populateField,
        java.lang.Object populateValue, java.lang.Object future3,
        java.lang.Object future4);

    public java.lang.Object checkAll(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate, int value,
        java.lang.Object populateField, java.lang.Object populateValue,
        java.lang.Object future3, java.lang.Object future4);

    public java.util.List getItemLazyList(int start, int offset,
        java.lang.Object itemIdList, java.lang.Object operation,
        java.lang.Object searchValue, java.lang.Object valueList,
        java.lang.Object columnName, java.lang.Object orderBy,
        java.lang.Object future3, java.lang.Object future4,
        java.util.Map<java.lang.String, java.lang.Object> parameters);

    public java.lang.Object updateToIFPDetails(int ifpMasterSystemId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4);

    public java.lang.Object updateIFPDetails(int ifpMasterSystemId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4);

    public java.lang.Object insertTempIfpDetailsInADD(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object searchField,
        java.lang.Object searchValue, java.lang.Object future3,
        java.lang.Object future4);

    public java.lang.Object insertTempIfpDetailsInEdit(
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object ifpId, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4);

    public java.lang.Object validateTempIFPDeatils(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4);

    public java.lang.Object getTempIFPLazyList(java.lang.String ifpId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object column,
        java.lang.Object orderBy);

    public java.lang.Object updateAll(java.lang.String userId,
        java.lang.String sessionId, java.lang.String dateCreated,
        java.lang.String operation, java.lang.Object tempIfpSystemId,
        java.lang.Object deleteYesterdayRecordFlag, java.lang.Object future3,
        java.lang.Object future4);

    public java.util.List getIFPSearchList(java.lang.String ifpId,
        java.lang.String ifpNo, java.lang.String ifpName, int ifpType,
        int ifpStatus, java.lang.String itemId, java.lang.String itemNo,
        java.lang.String itemName, int start, int offset,
        java.lang.String column, java.lang.String orderBy, boolean countFlag,
        java.util.Map<java.lang.String, java.lang.Object> parameters);

    public java.util.List fetchFieldsForSecurity(java.lang.String moduleName,
        java.lang.String tabName, java.lang.Object obj1, java.lang.Object obj2,
        java.lang.Object obj3);
}
