package com.stpl.app.service.persistence;

public interface ImtdPsDetailsFinder {
    public java.lang.Object deleteAll(java.lang.String userId,
        java.lang.String sessionId, java.lang.String dateCreated,
        java.lang.String operation, java.lang.Object type,
        java.lang.Object psId, java.lang.Object ifpId, java.lang.Object future4);

    public java.lang.Object updateOperation(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4);

    public java.lang.Object updateForPopulate(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object populateField,
        java.lang.Object populateValue, java.lang.Object mode,
        java.lang.Object psMasterId);

    public java.lang.Object updateForPopulateAll(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object populateField,
        java.lang.Object populateValue, java.lang.Object mode,
        java.lang.Object psMasterId);

    public java.util.List getIfpLazyList(int start, int offset,
        java.lang.Object ifpIdList, java.lang.Object operation,
        java.lang.Object searchValue);

    public java.lang.Object updateToPsDetails(int psSystemId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4);

    public java.lang.Object insertTempPsDetailsInADD(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4);

    public java.lang.Object insertTempPsDetailsInEdit(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4);

    public java.lang.Object validateTempPSDeatils(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String process, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4);

    public java.lang.Object getTempPSLazyList(java.lang.String psId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4);

    public java.util.List getItemPriceDetails(int start, int offset,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String columnName, java.lang.String orderBy,
        java.util.Map<java.lang.String, java.lang.Object> parameters);

    public java.lang.Object executeUpdateQuery(java.lang.String queryList,
        java.lang.Object obj1, java.lang.Object obj2);
}
