package com.stpl.app.service.persistence;

public interface ImtdCfpDetailsFinder {
    public java.lang.Object deleteAll(java.lang.String userId,
        java.lang.String sessionId, java.lang.String dateCreated,
        java.lang.String operation, java.lang.Object tempCfpSystemId,
        java.lang.Object deleteYesterdayRecordFlag, java.lang.Object future3,
        java.lang.Object future4);

    public java.lang.Object updateAll(java.lang.String userId,
        java.lang.String sessionId, java.lang.String dateCreated,
        java.lang.String operation, java.lang.Object tempCfpSystemId,
        java.lang.Object deleteYesterdayRecordFlag, java.lang.Object future3,
        java.lang.Object future4);

    public java.lang.Object updateOperation(
        java.lang.String cfpMasterSystemId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4);

    public java.lang.Object updateForPopulate(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object searchField,
        java.lang.Object value, java.lang.Object date, java.lang.Object future4);

    public java.lang.Object updateForPopulateAll(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object searchField,
        java.lang.Object value, java.lang.Object date, java.lang.Object future4);

    public java.util.List getCompanyLazyList(int start, int offset,
        java.lang.Object userSessionArray, java.lang.Object operation,
        java.lang.Object searchValue, java.lang.String column,
        java.lang.String orderBy,
        java.util.Map<java.lang.Object, java.lang.Object> filterMap);

    public java.lang.Object updateToCFPDetails(int cfpMasterSystemId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object updateFlag, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4);

    public java.lang.Object insertTempCfpDetailsInADD(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String cfpAttachedDate, java.lang.Object searchValues,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4);

    public java.lang.Object insertTempCfpDetailsInEdit(
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String cfpMasterSystemId,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4);

    public java.lang.Object validateTempCFPDeatils(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String process, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4);

    public java.lang.Object getTempCFPLazyList(
        java.lang.String cfpMasterSystemId, java.lang.String start,
        java.lang.String offset, java.lang.String operation,
        java.lang.Object column, java.lang.Object orderBy,
        java.lang.Object future1, java.lang.Object future2,
        java.util.Map<java.lang.Object, java.lang.Object> filterMap);

    public java.lang.Object updateCFPDetails(int cfpMasterSystemId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object updateFlag, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4);

    public java.lang.Object updatestatusCFPDetails(int cfpMasterSystemId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object updateFlag, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4);

    public java.util.List getTempCfpDetails(java.lang.String userId,
        java.lang.String sessionId, int start, int offset,
        java.lang.String column, java.lang.String orderBy,
        java.util.Map<java.lang.Object, java.lang.Object> filterMap);

    public java.util.List getSelectedCompanies(int start, int offset,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String columnName, java.lang.String orderBy,
        java.util.Map<java.lang.Object, java.lang.Object> filterMap,
        java.lang.String operation);

    public java.lang.Boolean loadTempCompanydetails(
        java.util.List<java.lang.Object> input, java.lang.Object future);

    public java.lang.Boolean massPopulate(
        java.util.List<java.lang.Object> input, java.lang.Object future);

    public java.lang.Boolean massPopulateAll(
        java.util.List<java.lang.Object> input, java.lang.Object future);

    public java.lang.Boolean saveCompany(
        java.util.List<java.lang.Object> input, java.lang.Object future);

    public java.lang.Boolean deleteAll(java.util.List<java.lang.Object> input,
        java.lang.Object future);

    public java.lang.Boolean updateAll(java.util.List<java.lang.Object> input,
        java.lang.Object future);

    public java.lang.Object getOverlapedCompanies(
        java.util.List<java.lang.Object> input, java.lang.Object future);

    public java.util.List getSelectedCompanies(java.lang.String userID,
        java.lang.String sessionID, int start, int offset,
        java.lang.String column, java.lang.String orderBy, boolean flag,
        java.lang.Object future1,
        java.util.Map<java.lang.String, java.lang.Object> filterMap,
        boolean isCount);
}
