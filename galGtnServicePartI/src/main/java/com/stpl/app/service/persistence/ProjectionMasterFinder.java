package com.stpl.app.service.persistence;

public interface ProjectionMasterFinder {
    public java.util.List getRelationshipHierarchy(
        java.util.Map<java.lang.String, java.lang.Object> parameters);

    public java.util.List getCcpDetails(
        java.util.Map<java.lang.String, java.lang.Object> parameters);

    public java.util.List getCustomerProductGroup(
        java.util.Map<java.lang.String, java.lang.Object> parameters);

    public java.util.List searchDsProjection(
        java.util.Map<java.lang.String, java.lang.Object> parameters);

    public java.util.List getProjection(int projectionId);

    public java.util.List getRelationShipValuesforchannel(
        java.util.Map<java.lang.String, java.lang.Object> parameters);

    public java.util.List getRelationShipValues(
        java.util.Map<java.lang.String, java.lang.Object> parameters);

    public java.lang.String deleteProjection(java.lang.String screenName,
        int projectionID);

    public java.util.List getParentLevels(int levelNo,
        int relationshipLevelSid,
        java.util.Map<java.lang.String, java.lang.Object> parameters);

    public java.util.List executeQuery(
        java.util.Map<java.lang.String, java.lang.Object> parameters);

    public java.util.List getItemsFromBrand(
        java.util.Map<java.lang.String, java.lang.Object> parameters);

    public java.util.List getInnerLevel(
        java.util.Map<java.lang.String, java.lang.Object> parameters);

    public void saveCcp(
        java.util.Map<java.lang.String, java.lang.Object> parameters);

    public java.util.List getCcpMap(
        java.util.Map<java.lang.String, java.lang.Object> parameters);

    public java.util.List getLevelCcp(
        java.util.Map<java.lang.String, java.lang.Object> parameters);

    public java.lang.Object tempOperation(
        java.util.Map<java.lang.String, java.lang.Object> input,
        java.lang.String queryName);

    public java.util.List getChildLevels(
        java.util.Map<java.lang.String, java.lang.Object> parameters);

    public void saveCcpForMandated(
        java.util.Map<java.lang.String, java.lang.Object> parameters);

    public java.util.List searchProjections(
        java.util.Map<java.lang.String, java.lang.Object> parameters);

    public int getForecastViewCount(java.lang.String viewName,
        java.lang.String viewType);

    public java.util.List executeQueryforchannel(
        java.util.Map<java.lang.String, java.lang.Object> parameters);

    public java.util.List getCcpMapforchannel(
        java.util.Map<java.lang.String, java.lang.Object> parameters);

    public void saveCcpforchannel(
        java.util.Map<java.lang.String, java.lang.Object> parameters);

    public java.util.List getCustomerProductGroupforchannel(
        java.util.Map<java.lang.String, java.lang.Object> parameters);

    public java.util.List getInnerLevelforchannel(
        java.util.Map<java.lang.String, java.lang.Object> parameters);

    public java.util.List getParentLevelsforchannel(int levelNo,
        int relationshipLevelSid,
        java.util.Map<java.lang.String, java.lang.Object> parameters);

    public java.util.List getChildLevelsforchannel(
        java.util.Map<java.lang.String, java.lang.Object> parameters);

    public java.util.List getProjectionforchannel(int projectionId);

    public java.lang.String deleteProjectionforchannel(
        java.lang.String tableName, int projectionID);
}
