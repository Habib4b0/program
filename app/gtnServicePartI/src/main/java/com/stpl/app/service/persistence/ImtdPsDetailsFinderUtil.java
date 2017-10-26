package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;


public class ImtdPsDetailsFinderUtil {
    private static ImtdPsDetailsFinder _finder;

    public static java.lang.Object deleteAll(java.lang.String userId,
        java.lang.String sessionId, java.lang.String dateCreated,
        java.lang.String operation, java.lang.Object type,
        java.lang.Object psId, java.lang.Object ifpId, java.lang.Object future4) {
        return getFinder()
                   .deleteAll(userId, sessionId, dateCreated, operation, type,
            psId, ifpId, future4);
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
        java.lang.Object populateValue, java.lang.Object mode,
        java.lang.Object psMasterId) {
        return getFinder()
                   .updateForPopulate(userId, sessionId, createdDate,
            operation, populateField, populateValue, mode, psMasterId);
    }

    public static java.lang.Object updateForPopulateAll(
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object populateField, java.lang.Object populateValue,
        java.lang.Object mode, java.lang.Object psMasterId) {
        return getFinder()
                   .updateForPopulateAll(userId, sessionId, createdDate,
            operation, populateField, populateValue, mode, psMasterId);
    }

    public static java.util.List getIfpLazyList(int start, int offset,
        java.lang.Object ifpIdList, java.lang.Object operation,
        java.lang.Object searchValue) {
        return getFinder()
                   .getIfpLazyList(start, offset, ifpIdList, operation,
            searchValue);
    }

    public static java.lang.Object updateToPsDetails(int psSystemId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return getFinder()
                   .updateToPsDetails(psSystemId, userId, sessionId,
            createdDate, operation, future1, future2, future3, future4);
    }

    public static java.lang.Object insertTempPsDetailsInADD(
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return getFinder()
                   .insertTempPsDetailsInADD(userId, sessionId, createdDate,
            operation, future1, future2, future3, future4);
    }

    public static java.lang.Object insertTempPsDetailsInEdit(
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return getFinder()
                   .insertTempPsDetailsInEdit(userId, sessionId, createdDate,
            operation, future1, future2, future3, future4);
    }

    public static java.lang.Object validateTempPSDeatils(
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String process,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return getFinder()
                   .validateTempPSDeatils(userId, sessionId, createdDate,
            process, future1, future2, future3, future4);
    }

    public static java.lang.Object getTempPSLazyList(java.lang.String psId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object future1,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4) {
        return getFinder()
                   .getTempPSLazyList(psId, sessionId, createdDate, operation,
            future1, future2, future3, future4);
    }

    public static java.util.List getItemPriceDetails(int start, int offset,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String columnName, java.lang.String orderBy,
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getFinder()
                   .getItemPriceDetails(start, offset, userId, sessionId,
            columnName, orderBy, parameters);
    }

    public static java.lang.Object executeUpdateQuery(
        java.lang.String queryList, java.lang.Object obj1, java.lang.Object obj2) {
        return getFinder().executeUpdateQuery(queryList, obj1, obj2);
    }

    public static ImtdPsDetailsFinder getFinder() {
        if (_finder == null) {
            _finder = (ImtdPsDetailsFinder) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    ImtdPsDetailsFinder.class.getName());

            ReferenceRegistry.registerReference(ImtdPsDetailsFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(ImtdPsDetailsFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(ImtdPsDetailsFinderUtil.class,
            "_finder");
    }
}
