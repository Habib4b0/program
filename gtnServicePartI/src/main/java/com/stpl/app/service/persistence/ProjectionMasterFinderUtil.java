package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;


public class ProjectionMasterFinderUtil {
    private static ProjectionMasterFinder _finder;

    public static java.util.List getRelationshipHierarchy(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getFinder().getRelationshipHierarchy(parameters);
    }

    public static java.util.List getCcpDetails(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getFinder().getCcpDetails(parameters);
    }

    public static java.util.List getCustomerProductGroup(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getFinder().getCustomerProductGroup(parameters);
    }

    public static java.util.List searchDsProjection(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getFinder().searchDsProjection(parameters);
    }

    public static java.util.List getProjection(int projectionId) {
        return getFinder().getProjection(projectionId);
    }

    public static java.util.List getRelationShipValuesforchannel(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getFinder().getRelationShipValuesforchannel(parameters);
    }

    public static java.util.List getRelationShipValues(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getFinder().getRelationShipValues(parameters);
    }

    public static java.lang.String deleteProjection(
        java.lang.String screenName, int projectionID) {
        return getFinder().deleteProjection(screenName, projectionID);
    }

    public static java.util.List getParentLevels(int levelNo,
        int relationshipLevelSid,
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getFinder()
                   .getParentLevels(levelNo, relationshipLevelSid, parameters);
    }

    public static java.util.List executeQuery(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getFinder().executeQuery(parameters);
    }

    public static java.util.List getItemsFromBrand(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getFinder().getItemsFromBrand(parameters);
    }

    public static java.util.List getInnerLevel(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getFinder().getInnerLevel(parameters);
    }

    public static void saveCcp(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        getFinder().saveCcp(parameters);
    }

    public static java.util.List getCcpMap(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getFinder().getCcpMap(parameters);
    }

    public static java.util.List getLevelCcp(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getFinder().getLevelCcp(parameters);
    }

    public static java.lang.Object tempOperation(
        java.util.Map<java.lang.String, java.lang.Object> input,
        java.lang.String queryName) {
        return getFinder().tempOperation(input, queryName);
    }

    public static java.util.List getChildLevels(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getFinder().getChildLevels(parameters);
    }

    public static void saveCcpForMandated(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        getFinder().saveCcpForMandated(parameters);
    }

    public static java.util.List searchProjections(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getFinder().searchProjections(parameters);
    }

    public static int getForecastViewCount(java.lang.String viewName,
        java.lang.String viewType) {
        return getFinder().getForecastViewCount(viewName, viewType);
    }

    public static java.util.List executeQueryforchannel(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getFinder().executeQueryforchannel(parameters);
    }

    public static java.util.List getCcpMapforchannel(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getFinder().getCcpMapforchannel(parameters);
    }

    public static void saveCcpforchannel(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        getFinder().saveCcpforchannel(parameters);
    }

    public static java.util.List getCustomerProductGroupforchannel(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getFinder().getCustomerProductGroupforchannel(parameters);
    }

    public static java.util.List getInnerLevelforchannel(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getFinder().getInnerLevelforchannel(parameters);
    }

    public static java.util.List getParentLevelsforchannel(int levelNo,
        int relationshipLevelSid,
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getFinder()
                   .getParentLevelsforchannel(levelNo, relationshipLevelSid,
            parameters);
    }

    public static java.util.List getChildLevelsforchannel(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getFinder().getChildLevelsforchannel(parameters);
    }

    public static java.util.List getProjectionforchannel(int projectionId) {
        return getFinder().getProjectionforchannel(projectionId);
    }

    public static java.lang.String deleteProjectionforchannel(
        java.lang.String tableName, int projectionID) {
        return getFinder().deleteProjectionforchannel(tableName, projectionID);
    }

    public static ProjectionMasterFinder getFinder() {
        if (_finder == null) {
            _finder = (ProjectionMasterFinder) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    ProjectionMasterFinder.class.getName());

            ReferenceRegistry.registerReference(ProjectionMasterFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(ProjectionMasterFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(ProjectionMasterFinderUtil.class,
            "_finder");
    }
}
