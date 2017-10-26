package com.stpl.app.service.persistence;

public interface ImtdRsDetailsFinder {
    public java.lang.Object deleteAll(java.lang.String userId,
        java.lang.String sessionId, java.lang.String dateCreated,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4);

    public java.lang.Object updateOperation(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4);

    public java.lang.Object updateForPopulate(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object populateField,
        java.lang.Object populateValue, java.lang.Object rebatePlanLevel,
        java.lang.Object future4);

    public java.lang.Object updateForPopulateAll(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object populateField,
        java.lang.Object populateValue, java.lang.Object future3,
        java.lang.Object future4);

    public java.util.List getIfpLazyList(int start, int offset,
        java.lang.Object ifpIdList, java.lang.Object operation,
        java.lang.Object searchValue);

    public java.lang.Object updateToRsDetails(int ifpMasterSystemId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String flag,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4);

    public java.lang.Object insertTempRsDetailsInADD(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String ifpSystemId, java.lang.Object idValue,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4);

    public java.lang.Object insertTempRsDetailsInEdit(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String rsSystemId, java.lang.Object rebateScheduleId,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4);

    public java.util.List getItemLazyList(int start, int offset,
        java.lang.Object companyIdList, java.lang.Object operation,
        java.lang.Object searchValue);

    public java.lang.Object updateToIFPDetails(int ifpMasterSystemId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4);

    public java.lang.Object insertTempIfpDetailsInADD(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4);

    public java.lang.Object insertTempIfpDetailsInEdit(
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4);

    public java.lang.Object validateTempRSDeatils(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4);

    public java.lang.Object getTempRSLazyList(java.lang.String rsId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object start,
        java.lang.Object offset, java.lang.Object column,
        java.lang.Object orderBy);

    public java.util.List loadIFPItems(int start, int offset,
        java.lang.Object userID, java.lang.Object sessionID,
        java.lang.Object column, java.lang.Object orderBy,
        java.lang.Object value1, java.lang.Object value2,
        java.util.Map<java.lang.String, java.lang.Object> parameters);

    public void mergeImtdRsDetailsFormula(int rsdSid, int itemSid,
        java.lang.String userId, java.lang.String sessionId);

    public void deleteTempTableRecords(int rsSid, int rsdSid,
        java.lang.String userId, java.lang.String sessionId);

    public void insertFormulaToRsdFrImtd(int rsdSid, java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate);

    public void addAllFormulaToRsdFrImtd(int itemSid, java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate);

    public void remaoveAllFormulaToRsdFrImtd(int itemSid,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate);

    public void deleteRsdFr(int rsdSid);
}
