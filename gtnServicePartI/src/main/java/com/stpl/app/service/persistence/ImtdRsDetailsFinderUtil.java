package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;


public class ImtdRsDetailsFinderUtil {
    private static ImtdRsDetailsFinder _finder;

    public static java.lang.Object deleteAll(java.lang.String userId,
        java.lang.String sessionId, java.lang.String dateCreated,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4) {
        return getFinder()
                   .deleteAll(userId, sessionId, dateCreated, operation,
            future1, future2, future3, future4);
    }

    public static java.lang.Object updateOperation(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4) {
        return getFinder()
                   .updateOperation(userId, sessionId, createdDate, operation,
            future1, future2, future3, future4);
    }

    public static java.lang.Object updateForPopulate(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object populateField,
        java.lang.Object populateValue, java.lang.Object rebatePlanLevel,
        java.lang.Object future4) {
        return getFinder()
                   .updateForPopulate(userId, sessionId, createdDate,
            operation, populateField, populateValue, rebatePlanLevel, future4);
    }

    public static java.lang.Object updateForPopulateAll(
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object populateField, java.lang.Object populateValue,
        java.lang.Object future3, java.lang.Object future4) {
        return getFinder()
                   .updateForPopulateAll(userId, sessionId, createdDate,
            operation, populateField, populateValue, future3, future4);
    }

    public static java.util.List getIfpLazyList(int start, int offset,
        java.lang.Object ifpIdList, java.lang.Object operation,
        java.lang.Object searchValue) {
        return getFinder()
                   .getIfpLazyList(start, offset, ifpIdList, operation,
            searchValue);
    }

    public static java.lang.Object updateToRsDetails(int ifpMasterSystemId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String flag,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return getFinder()
                   .updateToRsDetails(ifpMasterSystemId, userId, sessionId,
            createdDate, flag, future1, future2, future3, future4);
    }

    public static java.lang.Object insertTempRsDetailsInADD(
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String ifpSystemId,
        java.lang.Object idValue, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return getFinder()
                   .insertTempRsDetailsInADD(userId, sessionId, createdDate,
            ifpSystemId, idValue, future2, future3, future4);
    }

    public static java.lang.Object insertTempRsDetailsInEdit(
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String rsSystemId,
        java.lang.Object rebateScheduleId, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return getFinder()
                   .insertTempRsDetailsInEdit(userId, sessionId, createdDate,
            rsSystemId, rebateScheduleId, future2, future3, future4);
    }

    public static java.util.List getItemLazyList(int start, int offset,
        java.lang.Object companyIdList, java.lang.Object operation,
        java.lang.Object searchValue) {
        return getFinder()
                   .getItemLazyList(start, offset, companyIdList, operation,
            searchValue);
    }

    public static java.lang.Object updateToIFPDetails(int ifpMasterSystemId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return getFinder()
                   .updateToIFPDetails(ifpMasterSystemId, userId, sessionId,
            createdDate, operation, future1, future2, future3, future4);
    }

    public static java.lang.Object insertTempIfpDetailsInADD(
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return getFinder()
                   .insertTempIfpDetailsInADD(userId, sessionId, createdDate,
            operation, future1, future2, future3, future4);
    }

    public static java.lang.Object insertTempIfpDetailsInEdit(
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return getFinder()
                   .insertTempIfpDetailsInEdit(userId, sessionId, createdDate,
            operation, future1, future2, future3, future4);
    }

    public static java.lang.Object validateTempRSDeatils(
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return getFinder()
                   .validateTempRSDeatils(userId, sessionId, createdDate,
            operation, future1, future2, future3, future4);
    }

    public static java.lang.Object getTempRSLazyList(java.lang.String rsId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object start,
        java.lang.Object offset, java.lang.Object column,
        java.lang.Object orderBy) {
        return getFinder()
                   .getTempRSLazyList(rsId, sessionId, createdDate, operation,
            start, offset, column, orderBy);
    }

    public static java.util.List loadIFPItems(int start, int offset,
        java.lang.Object userID, java.lang.Object sessionID,
        java.lang.Object column, java.lang.Object orderBy,
        java.lang.Object value1, java.lang.Object value2,
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getFinder()
                   .loadIFPItems(start, offset, userID, sessionID, column,
            orderBy, value1, value2, parameters);
    }

    public static void mergeImtdRsDetailsFormula(int rsdSid, int itemSid,
        java.lang.String userId, java.lang.String sessionId) {
        getFinder().mergeImtdRsDetailsFormula(rsdSid, itemSid, userId, sessionId);
    }

    public static void deleteTempTableRecords(int rsSid, int rsdSid,
        java.lang.String userId, java.lang.String sessionId) {
        getFinder().deleteTempTableRecords(rsSid, rsdSid, userId, sessionId);
    }

    public static void insertFormulaToRsdFrImtd(int rsdSid,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate) {
        getFinder()
            .insertFormulaToRsdFrImtd(rsdSid, userId, sessionId, createdDate);
    }

    public static void addAllFormulaToRsdFrImtd(int itemSid,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate) {
        getFinder()
            .addAllFormulaToRsdFrImtd(itemSid, userId, sessionId, createdDate);
    }

    public static void remaoveAllFormulaToRsdFrImtd(int itemSid,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate) {
        getFinder()
            .remaoveAllFormulaToRsdFrImtd(itemSid, userId, sessionId,
            createdDate);
    }

    public static void deleteRsdFr(int rsdSid) {
        getFinder().deleteRsdFr(rsdSid);
    }

    public static ImtdRsDetailsFinder getFinder() {
        if (_finder == null) {
            _finder = (ImtdRsDetailsFinder) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    ImtdRsDetailsFinder.class.getName());

            ReferenceRegistry.registerReference(ImtdRsDetailsFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(ImtdRsDetailsFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(ImtdRsDetailsFinderUtil.class,
            "_finder");
    }
}
