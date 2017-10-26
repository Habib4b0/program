package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;


public class RsModelFinderUtil {
    private static RsModelFinder _finder;

    public static java.util.List findRSModel(java.lang.String rsId,
        java.lang.String rsNo, java.lang.String rsName,
        java.lang.String rsStatus, java.lang.String rpType,
        java.lang.String itemId, java.lang.String itemNo,
        java.lang.String itemName, java.lang.String rsType,
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getFinder()
                   .findRSModel(rsId, rsNo, rsName, rsStatus, rpType, itemId,
            itemNo, itemName, rsType, parameters);
    }

    public static java.util.List findIFP(java.lang.String ifpNumber,
        java.lang.String ifpName, java.lang.String ifpType,
        java.lang.String ifpTypeString, java.lang.String ifpStartDate,
        java.lang.String ifpEndDate, java.lang.String itemNo,
        java.lang.String itemName,
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getFinder()
                   .findIFP(ifpNumber, ifpName, ifpType, ifpTypeString,
            ifpStartDate, ifpEndDate, itemNo, itemName, parameters);
    }

    public static java.util.List getItemDetailsOfIfp(java.lang.String ifpId) {
        return getFinder().getItemDetailsOfIfp(ifpId);
    }

    public static java.util.List getRebateScheduleDetails(
        int rebateScheduleSystemId, java.lang.Object future1,
        java.lang.Object future2) {
        return getFinder()
                   .getRebateScheduleDetails(rebateScheduleSystemId, future1,
            future2);
    }

    public static java.util.List getRebateScheduleDetailsUniqueCheck(
        java.lang.String rebateScheduleId, java.lang.String itemId,
        java.util.Date itemStartDate) {
        return getFinder()
                   .getRebateScheduleDetailsUniqueCheck(rebateScheduleId,
            itemId, itemStartDate);
    }

    public static java.util.List getRebateScheduleDetailsUniqueCheckWithSysId(
        int rebateScheduleSystemId, java.lang.String rebateScheduleId,
        java.lang.String itemId, java.util.Date itemStartDate) {
        return getFinder()
                   .getRebateScheduleDetailsUniqueCheckWithSysId(rebateScheduleSystemId,
            rebateScheduleId, itemId, itemStartDate);
    }

    public static java.util.List getParentRsList(java.lang.String rebateSchId,
        java.lang.String rebateSchNo, java.lang.String rebateSchName,
        java.lang.String rebateSchStatus, java.lang.String rebateSchType,
        java.lang.String rebateProgType, java.lang.String itemId,
        java.lang.String itemNo, java.lang.String itemName, int start,
        int offset, java.lang.String column, java.lang.String orderBy,
        java.util.Map<java.lang.String, java.lang.Object> parameters,
        boolean isCount) {
        return getFinder()
                   .getParentRsList(rebateSchId, rebateSchNo, rebateSchName,
            rebateSchStatus, rebateSchType, rebateProgType, itemId, itemNo,
            itemName, start, offset, column, orderBy, parameters, isCount);
    }

    public static java.util.List getIfpList(java.lang.String ifpNumber,
        java.lang.String ifpName, java.lang.Object ifpType,
        java.lang.String ifpStartDate, java.lang.String ifpEndDate,
        java.lang.String itemNo, java.lang.String itemName, int start,
        int offset, java.lang.String column, java.lang.String orderBy,
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getFinder()
                   .getIfpList(ifpNumber, ifpName, ifpType, ifpStartDate,
            ifpEndDate, itemNo, itemName, start, offset, column, orderBy,
            parameters);
    }

    public static java.lang.Object executeSelectQuery(java.lang.String query,
        java.lang.Object udc1, java.lang.Object udc2) {
        return getFinder().executeSelectQuery(query, udc1, udc2);
    }

    public static java.lang.Object executeUpdateQuery(
        java.util.List<java.lang.StringBuilder> queryList,
        java.lang.Object obj1, java.lang.Object obj2) {
        return getFinder().executeUpdateQuery(queryList, obj1, obj2);
    }

    public static java.lang.Object executeUpdateQuery(java.lang.String query,
        java.lang.Object obj1, java.lang.Object obj2) {
        return getFinder().executeUpdateQuery(query, obj1, obj2);
    }

    public static RsModelFinder getFinder() {
        if (_finder == null) {
            _finder = (RsModelFinder) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    RsModelFinder.class.getName());

            ReferenceRegistry.registerReference(RsModelFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(RsModelFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(RsModelFinderUtil.class, "_finder");
    }
}
