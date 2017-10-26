package com.stpl.app.service.persistence;

public interface ImtdItemPriceRebateDetailsFinder {
    public java.lang.Boolean loadTempItemdetails(
        java.util.List<java.lang.Object> input, java.lang.Object future);

    public java.lang.Boolean massPopulate(
        java.util.List<java.lang.Object> input, java.lang.Object future);

    public java.lang.Boolean massPopulateAll(
        java.util.List<java.lang.Object> input, java.lang.Object future);

    public java.lang.Boolean saveItem(java.util.List<java.lang.Object> input,
        java.lang.Object future);

    public java.lang.Boolean deleteAll(java.util.List<java.lang.Object> input,
        java.lang.Object future);

    public java.lang.Boolean updateAll(java.util.List<java.lang.Object> input,
        java.lang.Object future);

    public java.lang.Object validateTempRSDeatils(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4);

    public java.util.List getSelectedItemList(java.lang.String userID,
        java.lang.String sessionID, int start, int offset,
        java.lang.String column, java.lang.Boolean orderBy,
        java.util.Map<java.lang.String, java.lang.Object> filterMap,
        boolean isCount, java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3);

    public void mergeImtdRsContractDetailsFormula(int contRsdSid, int itemSid,
        java.lang.String userId, java.lang.String sessionId);

    public void deleteTempRsContractTableRecords(int contRsSid, int contRsdSid,
        java.lang.String userId, java.lang.String sessionId);

    public void insertFormulaToContractRsdFrImtd(int contRsdSid,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate);

    public void addAllFormulaToContractRsdFrImtd(int itemSid,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate);

    public void remaoveAllFormulaToContractRsdFrImtd(int itemSid,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate);
}
