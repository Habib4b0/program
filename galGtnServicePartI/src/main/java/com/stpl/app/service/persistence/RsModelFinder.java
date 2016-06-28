package com.stpl.app.service.persistence;

public interface RsModelFinder {
    public java.util.List findRSModel(java.lang.String rsId,
        java.lang.String rsNo, java.lang.String rsName,
        java.lang.String rsStatus, java.lang.String rpType,
        java.lang.String itemId, java.lang.String itemNo,
        java.lang.String itemName, java.lang.String rsType,
        java.util.Map<java.lang.String, java.lang.Object> parameters);

    public java.util.List findIFP(java.lang.String ifpNumber,
        java.lang.String ifpName, java.lang.String ifpType,
        java.lang.String ifpTypeString, java.lang.String ifpStartDate,
        java.lang.String ifpEndDate, java.lang.String itemNo,
        java.lang.String itemName,
        java.util.Map<java.lang.String, java.lang.Object> parameters);

    public java.util.List getItemDetailsOfIfp(java.lang.String ifpId);

    public java.util.List getRebateScheduleDetails(int rebateScheduleSystemId,
        java.lang.Object future1, java.lang.Object future2);

    public java.util.List getRebateScheduleDetailsUniqueCheck(
        java.lang.String rebateScheduleId, java.lang.String itemId,
        java.util.Date itemStartDate);

    public java.util.List getRebateScheduleDetailsUniqueCheckWithSysId(
        int rebateScheduleSystemId, java.lang.String rebateScheduleId,
        java.lang.String itemId, java.util.Date itemStartDate);

    public java.util.List getParentRsList(java.lang.String rebateSchId,
        java.lang.String rebateSchNo, java.lang.String rebateSchName,
        java.lang.String rebateSchStatus, java.lang.String rebateSchType,
        java.lang.String rebateProgType, java.lang.String itemId,
        java.lang.String itemNo, java.lang.String itemName, int start,
        int offset, java.lang.String column, java.lang.String orderBy,
        java.util.Map<java.lang.String, java.lang.Object> parameters,
        boolean isCount);

    public java.util.List getIfpList(java.lang.String ifpNumber,
        java.lang.String ifpName, java.lang.Object ifpType,
        java.lang.String ifpStartDate, java.lang.String ifpEndDate,
        java.lang.String itemNo, java.lang.String itemName, int start,
        int offset, java.lang.String column, java.lang.String orderBy,
        java.util.Map<java.lang.String, java.lang.Object> parameters);

    public java.lang.Object executeSelectQuery(java.lang.String query,
        java.lang.Object udc1, java.lang.Object udc2);

    public java.lang.Object executeUpdateQuery(
        java.util.List<java.lang.StringBuilder> queryList,
        java.lang.Object obj1, java.lang.Object obj2);

    public java.lang.Object executeUpdateQuery(java.lang.String query,
        java.lang.Object obj1, java.lang.Object obj2);
}
