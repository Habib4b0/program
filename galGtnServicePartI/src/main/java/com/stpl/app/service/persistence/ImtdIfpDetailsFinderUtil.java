package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;


public class ImtdIfpDetailsFinderUtil {
    private static ImtdIfpDetailsFinder _finder;

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
        java.lang.String operation, java.lang.Object searchField,
        java.lang.Object searchValue, java.lang.Object searchList,
        java.lang.Object future4,
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getFinder()
                   .updateOperation(userId, sessionId, createdDate, operation,
            searchField, searchValue, searchList, future4, parameters);
    }

    public static java.lang.Object updateForPopulate(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object populateField,
        java.lang.Object populateValue, java.lang.Object future3,
        java.lang.Object future4) {
        return getFinder()
                   .updateForPopulate(userId, sessionId, createdDate,
            operation, populateField, populateValue, future3, future4);
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

    public static java.lang.Object checkAll(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate, int value,
        java.lang.Object populateField, java.lang.Object populateValue,
        java.lang.Object future3, java.lang.Object future4) {
        return getFinder()
                   .checkAll(userId, sessionId, createdDate, value,
            populateField, populateValue, future3, future4);
    }

    public static java.util.List getItemLazyList(int start, int offset,
        java.lang.Object itemIdList, java.lang.Object operation,
        java.lang.Object searchValue, java.lang.Object valueList,
        java.lang.Object columnName, java.lang.Object orderBy,
        java.lang.Object future3, java.lang.Object future4,
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getFinder()
                   .getItemLazyList(start, offset, itemIdList, operation,
            searchValue, valueList, columnName, orderBy, future3, future4,
            parameters);
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

    public static java.lang.Object updateIFPDetails(int ifpMasterSystemId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return getFinder()
                   .updateIFPDetails(ifpMasterSystemId, userId, sessionId,
            createdDate, operation, future1, future2, future3, future4);
    }

    public static java.lang.Object insertTempIfpDetailsInADD(
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object searchField, java.lang.Object searchValue,
        java.lang.Object future3, java.lang.Object future4) {
        return getFinder()
                   .insertTempIfpDetailsInADD(userId, sessionId, createdDate,
            operation, searchField, searchValue, future3, future4);
    }

    public static java.lang.Object insertTempIfpDetailsInEdit(
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object ifpId, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return getFinder()
                   .insertTempIfpDetailsInEdit(userId, sessionId, createdDate,
            operation, ifpId, future2, future3, future4);
    }

    public static java.lang.Object validateTempIFPDeatils(
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return getFinder()
                   .validateTempIFPDeatils(userId, sessionId, createdDate,
            operation, future1, future2, future3, future4);
    }

    public static java.lang.Object getTempIFPLazyList(java.lang.String ifpId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object column,
        java.lang.Object orderBy) {
        return getFinder()
                   .getTempIFPLazyList(ifpId, sessionId, createdDate,
            operation, future1, future2, column, orderBy);
    }

    public static java.lang.Object updateAll(java.lang.String userId,
        java.lang.String sessionId, java.lang.String dateCreated,
        java.lang.String operation, java.lang.Object tempIfpSystemId,
        java.lang.Object deleteYesterdayRecordFlag, java.lang.Object future3,
        java.lang.Object future4) {
        return getFinder()
                   .updateAll(userId, sessionId, dateCreated, operation,
            tempIfpSystemId, deleteYesterdayRecordFlag, future3, future4);
    }

    public static java.util.List getIFPSearchList(java.lang.String ifpId,
        java.lang.String ifpNo, java.lang.String ifpName, int ifpType,
        int ifpStatus, java.lang.String itemId, java.lang.String itemNo,
        java.lang.String itemName, int start, int offset,
        java.lang.String column, java.lang.String orderBy, boolean countFlag,
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getFinder()
                   .getIFPSearchList(ifpId, ifpNo, ifpName, ifpType, ifpStatus,
            itemId, itemNo, itemName, start, offset, column, orderBy,
            countFlag, parameters);
    }

    public static java.util.List fetchFieldsForSecurity(
        java.lang.String moduleName, java.lang.String tabName,
        java.lang.Object obj1, java.lang.Object obj2, java.lang.Object obj3) {
        return getFinder()
                   .fetchFieldsForSecurity(moduleName, tabName, obj1, obj2, obj3);
    }

    public static ImtdIfpDetailsFinder getFinder() {
        if (_finder == null) {
            _finder = (ImtdIfpDetailsFinder) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    ImtdIfpDetailsFinder.class.getName());

            ReferenceRegistry.registerReference(ImtdIfpDetailsFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(ImtdIfpDetailsFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(ImtdIfpDetailsFinderUtil.class,
            "_finder");
    }
}
