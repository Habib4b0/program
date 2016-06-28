package com.stpl.app.service.impl;

import com.stpl.app.service.base.ProjectionMasterLocalServiceBaseImpl;
import com.stpl.app.service.persistence.ProjectionMasterFinderUtil;
import java.util.List;
import java.util.Map;

/**
 * The implementation of the projection master local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.stpl.app.service.ProjectionMasterLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.base.ProjectionMasterLocalServiceBaseImpl
 * @see com.stpl.app.service.ProjectionMasterLocalServiceUtil
 */
public class ProjectionMasterLocalServiceImpl
    extends ProjectionMasterLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.stpl.app.service.ProjectionMasterLocalServiceUtil} to access the projection master local service.
     */

    public java.util.List getRelationshipHierarchy(
            java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return ProjectionMasterFinderUtil.getRelationshipHierarchy(parameters);
    }

    public java.util.List getCcpDetails(
            java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return ProjectionMasterFinderUtil.getCcpDetails(parameters);
    }

    public java.util.List getCustomerProductGroup(
            java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return ProjectionMasterFinderUtil.getCustomerProductGroup(parameters);
    }

    public java.util.List searchDsProjection(
            java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return ProjectionMasterFinderUtil.searchDsProjection(parameters);
    }

    public java.util.List getProjection(int projectionId) {
        return ProjectionMasterFinderUtil.getProjection(projectionId);
    }

    public java.util.List getRelationShipValues(
            java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return ProjectionMasterFinderUtil.getRelationShipValues(parameters);
    }

    public java.lang.String deleteProjection(
            java.lang.String TableName, int projectionID) {
        return ProjectionMasterFinderUtil.deleteProjection(TableName, projectionID);
    }

    public java.util.List getParentLevels(int levelNo,
            int relationshipLevelSid,
            java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return ProjectionMasterFinderUtil.getParentLevels(levelNo, relationshipLevelSid, parameters);
    }

    public java.util.List executeQuery(
            java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return ProjectionMasterFinderUtil.executeQuery(parameters);
    }

    public java.util.List getItemsFromBrand(
            java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return ProjectionMasterFinderUtil.getItemsFromBrand(parameters);
    }

     public java.util.List getInnerLevel(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return ProjectionMasterFinderUtil.getInnerLevel(parameters);
    }

    public void saveCcp(final Map<String, Object> parameters) {
        ProjectionMasterFinderUtil.saveCcp(parameters);
    }
    public void saveCcpForMandated(final Map<String, Object> parameters) {
        ProjectionMasterFinderUtil.saveCcp(parameters);
    }

    public List getCcpMap(final Map<String, Object> parameters) {
        return ProjectionMasterFinderUtil.getCcpMap(parameters);
    }

    public List getLevelCcp(final Map<String, Object> parameters) {
            return ProjectionMasterFinderUtil.getLevelCcp(parameters);
    }  

    public Object tempOperation(final Map<String, Object> input, final String queryName) {
            return ProjectionMasterFinderUtil.tempOperation(input, queryName);
    }  
    
     public List getChildLevels(final Map<String, Object> parameters) {
            return ProjectionMasterFinderUtil.getChildLevels(parameters);
    }  
     
    public java.util.List getRelationShipValuesforchannel(
            java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return ProjectionMasterFinderUtil.getRelationShipValuesforchannel(parameters);
    }
    
    
    public java.util.List searchProjections(
        java.util.Map<java.lang.String, java.lang.Object> parameters){
        return ProjectionMasterFinderUtil.searchProjections(parameters);
    }

    public int getForecastViewCount(java.lang.String viewName,
        java.lang.String viewType){
        return ProjectionMasterFinderUtil.getForecastViewCount(viewName, viewType);
    }

    public java.util.List executeQueryforchannel(
        java.util.Map<java.lang.String, java.lang.Object> parameters){
    return ProjectionMasterFinderUtil.executeQueryforchannel(parameters);
    }

    public java.util.List getCcpMapforchannel(
        java.util.Map<java.lang.String, java.lang.Object> parameters){
        return ProjectionMasterFinderUtil.getCcpMapforchannel(parameters);
    }

    public void saveCcpforchannel(
        java.util.Map<java.lang.String, java.lang.Object> parameters){
    }

    public java.util.List getCustomerProductGroupforchannel(
        java.util.Map<java.lang.String, java.lang.Object> parameters){
        return ProjectionMasterFinderUtil.getCustomerProductGroupforchannel(parameters);
    }

    public java.util.List getInnerLevelforchannel(
        java.util.Map<java.lang.String, java.lang.Object> parameters){
        return ProjectionMasterFinderUtil.getInnerLevelforchannel(parameters);
    }

    public java.util.List getParentLevelsforchannel(int levelNo,
        int relationshipLevelSid,
        java.util.Map<java.lang.String, java.lang.Object> parameters){
        return ProjectionMasterFinderUtil.getParentLevelsforchannel(levelNo, relationshipLevelSid, parameters);
    }

    public java.util.List getChildLevelsforchannel(
        java.util.Map<java.lang.String, java.lang.Object> parameters){
        return ProjectionMasterFinderUtil.getChildLevelsforchannel(parameters);
    }

    public java.util.List getProjectionforchannel(int projectionId){
        return ProjectionMasterFinderUtil.getProjectionforchannel(projectionId);
    }

    public java.lang.String deleteProjectionforchannel(
        java.lang.String tableName, int projectionID){
        return ProjectionMasterFinderUtil.deleteProjectionforchannel(tableName, projectionID);
    }
}

