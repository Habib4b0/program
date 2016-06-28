package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;


public class ImtdCfpDetailsFinderUtil {
    private static ImtdCfpDetailsFinder _finder;

    public static java.lang.Object deleteAll(java.lang.String userId,
        java.lang.String sessionId, java.lang.String dateCreated,
        java.lang.String operation, java.lang.Object tempCfpSystemId,
        java.lang.Object deleteYesterdayRecordFlag, java.lang.Object future3,
        java.lang.Object future4) {
        return getFinder()
                   .deleteAll(userId, sessionId, dateCreated, operation,
            tempCfpSystemId, deleteYesterdayRecordFlag, future3, future4);
    }

    public static java.lang.Object updateAll(java.lang.String userId,
        java.lang.String sessionId, java.lang.String dateCreated,
        java.lang.String operation, java.lang.Object tempCfpSystemId,
        java.lang.Object deleteYesterdayRecordFlag, java.lang.Object future3,
        java.lang.Object future4) {
        return getFinder()
                   .updateAll(userId, sessionId, dateCreated, operation,
            tempCfpSystemId, deleteYesterdayRecordFlag, future3, future4);
    }

    public static java.lang.Object updateOperation(
        java.lang.String cfpMasterSystemId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return getFinder()
                   .updateOperation(cfpMasterSystemId, sessionId, createdDate,
            operation, future1, future2, future3, future4);
    }

    public static java.lang.Object updateForPopulate(java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object searchField,
        java.lang.Object value, java.lang.Object date, java.lang.Object future4) {
        return getFinder()
                   .updateForPopulate(userId, sessionId, createdDate,
            operation, searchField, value, date, future4);
    }

    public static java.lang.Object updateForPopulateAll(
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object searchField, java.lang.Object value,
        java.lang.Object date, java.lang.Object future4) {
        return getFinder()
                   .updateForPopulateAll(userId, sessionId, createdDate,
            operation, searchField, value, date, future4);
    }

    public static java.util.List getCompanyLazyList(int start, int offset,
        java.lang.Object userSessionArray, java.lang.Object operation,
        java.lang.Object searchValue, java.lang.String column,
        java.lang.String orderBy,
        java.util.Map<java.lang.Object, java.lang.Object> filterMap) {
        return getFinder()
                   .getCompanyLazyList(start, offset, userSessionArray,
            operation, searchValue, column, orderBy, filterMap);
    }

    public static java.lang.Object updateToCFPDetails(int cfpMasterSystemId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object updateFlag, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return getFinder()
                   .updateToCFPDetails(cfpMasterSystemId, userId, sessionId,
            createdDate, operation, updateFlag, future2, future3, future4);
    }

    public static java.lang.Object insertTempCfpDetailsInADD(
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String cfpAttachedDate,
        java.lang.Object searchValues, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return getFinder()
                   .insertTempCfpDetailsInADD(userId, sessionId, createdDate,
            cfpAttachedDate, searchValues, future2, future3, future4);
    }

    public static java.lang.Object insertTempCfpDetailsInEdit(
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String cfpMasterSystemId,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return getFinder()
                   .insertTempCfpDetailsInEdit(userId, sessionId, createdDate,
            cfpMasterSystemId, future1, future2, future3, future4);
    }

    public static java.lang.Object validateTempCFPDeatils(
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String process,
        java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return getFinder()
                   .validateTempCFPDeatils(userId, sessionId, createdDate,
            process, future1, future2, future3, future4);
    }

    public static java.lang.Object getTempCFPLazyList(
        java.lang.String cfpMasterSystemId, java.lang.String start,
        java.lang.String offset, java.lang.String operation,
        java.lang.Object column, java.lang.Object orderBy,
        java.lang.Object future1, java.lang.Object future2,
        java.util.Map<java.lang.Object, java.lang.Object> filterMap) {
        return getFinder()
                   .getTempCFPLazyList(cfpMasterSystemId, start, offset,
            operation, column, orderBy, future1, future2, filterMap);
    }

    public static java.lang.Object updateCFPDetails(int cfpMasterSystemId,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate, java.lang.String operation,
        java.lang.Object updateFlag, java.lang.Object future2,
        java.lang.Object future3, java.lang.Object future4) {
        return getFinder()
                   .updateCFPDetails(cfpMasterSystemId, userId, sessionId,
            createdDate, operation, updateFlag, future2, future3, future4);
    }

    public static java.lang.Object updatestatusCFPDetails(
        int cfpMasterSystemId, java.lang.String userId,
        java.lang.String sessionId, java.lang.String createdDate,
        java.lang.String operation, java.lang.Object updateFlag,
        java.lang.Object future2, java.lang.Object future3,
        java.lang.Object future4) {
        return getFinder()
                   .updatestatusCFPDetails(cfpMasterSystemId, userId,
            sessionId, createdDate, operation, updateFlag, future2, future3,
            future4);
    }

    public static java.util.List getTempCfpDetails(java.lang.String userId,
        java.lang.String sessionId, int start, int offset,
        java.lang.String column, java.lang.String orderBy,
        java.util.Map<java.lang.Object, java.lang.Object> filterMap) {
        return getFinder()
                   .getTempCfpDetails(userId, sessionId, start, offset, column,
            orderBy, filterMap);
    }

    public static java.util.List getSelectedCompanies(int start, int offset,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String columnName, java.lang.String orderBy,
        java.util.Map<java.lang.Object, java.lang.Object> filterMap,
        java.lang.String operation) {
        return getFinder()
                   .getSelectedCompanies(start, offset, userId, sessionId,
            columnName, orderBy, filterMap, operation);
    }

    public static java.lang.Boolean loadTempCompanydetails(
        java.util.List<java.lang.Object> input, java.lang.Object future) {
        return getFinder().loadTempCompanydetails(input, future);
    }

    public static java.lang.Boolean massPopulate(
        java.util.List<java.lang.Object> input, java.lang.Object future) {
        return getFinder().massPopulate(input, future);
    }

    public static java.lang.Boolean massPopulateAll(
        java.util.List<java.lang.Object> input, java.lang.Object future) {
        return getFinder().massPopulateAll(input, future);
    }

    public static java.lang.Boolean saveCompany(
        java.util.List<java.lang.Object> input, java.lang.Object future) {
        return getFinder().saveCompany(input, future);
    }

    public static java.lang.Boolean deleteAll(
        java.util.List<java.lang.Object> input, java.lang.Object future) {
        return getFinder().deleteAll(input, future);
    }

    public static java.lang.Boolean updateAll(
        java.util.List<java.lang.Object> input, java.lang.Object future) {
        return getFinder().updateAll(input, future);
    }

    public static java.lang.Object getOverlapedCompanies(
        java.util.List<java.lang.Object> input, java.lang.Object future) {
        return getFinder().getOverlapedCompanies(input, future);
    }

    public static java.util.List getSelectedCompanies(java.lang.String userID,
        java.lang.String sessionID, int start, int offset,
        java.lang.String column, java.lang.String orderBy, boolean flag,
        java.lang.Object future1,
        java.util.Map<java.lang.String, java.lang.Object> filterMap,
        boolean isCount) {
        return getFinder()
                   .getSelectedCompanies(userID, sessionID, start, offset,
            column, orderBy, flag, future1, filterMap, isCount);
    }

    public static ImtdCfpDetailsFinder getFinder() {
        if (_finder == null) {
            _finder = (ImtdCfpDetailsFinder) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    ImtdCfpDetailsFinder.class.getName());

            ReferenceRegistry.registerReference(ImtdCfpDetailsFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(ImtdCfpDetailsFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(ImtdCfpDetailsFinderUtil.class,
            "_finder");
    }
}
