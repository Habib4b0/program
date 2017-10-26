package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;


public class ImtdItemPriceRebateDetailsFinderUtil {
    private static ImtdItemPriceRebateDetailsFinder _finder;

    public static java.lang.Boolean loadTempItemdetails(
        java.util.List<java.lang.Object> input, java.lang.Object future) {
        return getFinder().loadTempItemdetails(input, future);
    }

    public static java.lang.Boolean massPopulate(
        java.util.List<java.lang.Object> input, java.lang.Object future) {
        return getFinder().massPopulate(input, future);
    }

    public static java.lang.Boolean massPopulateAll(
        java.util.List<java.lang.Object> input, java.lang.Object future) {
        return getFinder().massPopulateAll(input, future);
    }

    public static java.lang.Boolean saveItem(
        java.util.List<java.lang.Object> input, java.lang.Object future) {
        return getFinder().saveItem(input, future);
    }

    public static java.lang.Boolean deleteAll(
        java.util.List<java.lang.Object> input, java.lang.Object future) {
        return getFinder().deleteAll(input, future);
    }

    public static java.lang.Boolean updateAll(
        java.util.List<java.lang.Object> input, java.lang.Object future) {
        return getFinder().updateAll(input, future);
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

    public static java.util.List getSelectedItemList(java.lang.String userID,
        java.lang.String sessionID, int start, int offset,
        java.lang.String column, java.lang.Boolean orderBy,
        java.util.Map<java.lang.String, java.lang.Object> filterMap,
        boolean isCount, java.lang.Object future1, java.lang.Object future2,
        java.lang.Object future3) {
        return getFinder()
                   .getSelectedItemList(userID, sessionID, start, offset,
            column, orderBy, filterMap, isCount, future1, future2, future3);
    }

    public static void mergeImtdRsContractDetailsFormula(int contRsdSid,
        int itemSid, java.lang.String userId, java.lang.String sessionId) {
        getFinder()
            .mergeImtdRsContractDetailsFormula(contRsdSid, itemSid, userId,
            sessionId);
    }

    public static void deleteTempRsContractTableRecords(int contRsSid,
        int contRsdSid, java.lang.String userId, java.lang.String sessionId) {
        getFinder()
            .deleteTempRsContractTableRecords(contRsSid, contRsdSid, userId,
            sessionId);
    }

    public static void insertFormulaToContractRsdFrImtd(int contRsdSid,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate) {
        getFinder()
            .insertFormulaToContractRsdFrImtd(contRsdSid, userId, sessionId,
            createdDate);
    }

    public static void addAllFormulaToContractRsdFrImtd(int itemSid,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate) {
        getFinder()
            .addAllFormulaToContractRsdFrImtd(itemSid, userId, sessionId,
            createdDate);
    }

    public static void remaoveAllFormulaToContractRsdFrImtd(int itemSid,
        java.lang.String userId, java.lang.String sessionId,
        java.lang.String createdDate) {
        getFinder()
            .remaoveAllFormulaToContractRsdFrImtd(itemSid, userId, sessionId,
            createdDate);
    }

    public static ImtdItemPriceRebateDetailsFinder getFinder() {
        if (_finder == null) {
            _finder = (ImtdItemPriceRebateDetailsFinder) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    ImtdItemPriceRebateDetailsFinder.class.getName());

            ReferenceRegistry.registerReference(ImtdItemPriceRebateDetailsFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(ImtdItemPriceRebateDetailsFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(ImtdItemPriceRebateDetailsFinderUtil.class,
            "_finder");
    }
}
