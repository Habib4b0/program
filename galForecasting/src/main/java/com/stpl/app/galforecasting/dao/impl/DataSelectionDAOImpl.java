/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.dao.impl;

import com.stpl.app.galforecasting.dao.DataSelectionDAO;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.forecastds.dto.HierarchyLookupDTO;
import com.stpl.app.model.BrandMaster;
import com.stpl.app.model.ForecastingViewMaster;
import com.stpl.app.model.ProjectionCustDetails;
import com.stpl.app.model.ProjectionCustHierarchy;
import com.stpl.app.model.ProjectionDetails;
import com.stpl.app.model.ProjectionMaster;
import com.stpl.app.model.ProjectionProdDetails;
import com.stpl.app.model.ProjectionProdHierarchy;
import com.stpl.app.service.BrandMasterLocalServiceUtil;
import com.stpl.app.service.CcpDetailsLocalServiceUtil;
import com.stpl.app.service.CompanyGroupDetailsLocalServiceUtil;
import com.stpl.app.service.CompanyGroupLocalServiceUtil;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.ForecastConfigLocalServiceUtil;
import com.stpl.app.service.ForecastingViewMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.HierarchyDefinitionLocalServiceUtil;
import com.stpl.app.service.ItemGroupDetailsLocalServiceUtil;
import com.stpl.app.service.ItemMasterLocalServiceUtil;
import com.stpl.app.service.ProjectionCustDetailsLocalServiceUtil;
import com.stpl.app.service.ProjectionCustHierarchyLocalServiceUtil;
import com.stpl.app.service.ProjectionDetailsLocalServiceUtil;
import com.stpl.app.service.ProjectionMasterLocalServiceUtil;
import com.stpl.app.service.ProjectionProdDetailsLocalServiceUtil;
import com.stpl.app.service.ProjectionProdHierarchyLocalServiceUtil;
import com.stpl.app.service.RelationshipBuilderLocalServiceUtil;
import com.stpl.app.service.RsModelLocalServiceUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.stpl.portal.service.UserLocalServiceUtil;

import java.util.List;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class DataSelectionDAOImpl.
 *
 * @author lokeshwari
 */
public class DataSelectionDAOImpl implements DataSelectionDAO {

    /**
     * Find view by name.
     *
     * @param viewName the view name
     * @param forecastType the forecast type
     * @param userId the user id
     * @param viewType the view type
     * @return the list
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    @Override
    public List findViewByName(final String viewName, final String forecastType, final String userId, final String viewType)
            throws SystemException, PortalException, Exception {
        return ForecastingViewMasterLocalServiceUtil.findViewByName(viewName, forecastType, userId, viewType);
    }

    /**
     * Delete forecasting view master.
     *
     * @param systemId the system id
     * @return the forecasting view master
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    @Override
    public ForecastingViewMaster deleteForecastingViewMaster(final int systemId) throws SystemException, PortalException, Exception {
        return ForecastingViewMasterLocalServiceUtil.deleteForecastingViewMaster(systemId);
    }

    /**
     * Gets the forecast view masterdynamic query count.
     *
     * @param dynamicQuery the dynamic query
     * @return the forecast view masterdynamic query count
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    @Override
    public int getForecastViewMasterdynamicQueryCount(final DynamicQuery dynamicQuery) throws SystemException, Exception {
        return (int) ForecastingViewMasterLocalServiceUtil.dynamicQueryCount(dynamicQuery);
    }

    /**
     * Gets the user.
     *
     * @param systemId the system id
     * @return the user
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    @Override
    public User getUser(final Long systemId) throws SystemException, PortalException, Exception {
        return UserLocalServiceUtil.getUser(systemId);
    }

    /**
     * Gets the item master list.
     *
     * @param query the query
     * @return the item master list
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    @Override
    public List getItemMaster(final DynamicQuery query) throws SystemException, Exception {
        return ItemMasterLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * Adds the projection master.
     *
     * @param projectionMaster the projection master
     * @return the projection master
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    @Override
    public ProjectionMaster addProjectionMaster(final ProjectionMaster projectionMaster) throws SystemException, Exception {
        return ProjectionMasterLocalServiceUtil.addProjectionMaster(projectionMaster);
    }

    /**
     * Adds the projection cust details.
     *
     * @param projectionCustDetails the projection cust details
     * @return the projection cust details
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    @Override
    public ProjectionCustDetails addProjectionCustDetails(final ProjectionCustDetails projectionCustDetails) throws SystemException, Exception {
        return ProjectionCustDetailsLocalServiceUtil.addProjectionCustDetails(projectionCustDetails);
    }

    /**
     * Adds the projection prod details.
     *
     * @param projectionProdDetails the projection prod details
     * @return the projection prod details
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    @Override
    public ProjectionProdDetails addProjectionProdDetails(final ProjectionProdDetails projectionProdDetails) throws SystemException, Exception {
        return ProjectionProdDetailsLocalServiceUtil.addProjectionProdDetails(projectionProdDetails);
    }

    /**
     * Gets the forecasting view master.
     *
     * @param systemId the system id
     * @return the forecasting view master
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    @Override
    public ForecastingViewMaster getForecastingViewMaster(final int systemId) throws SystemException, PortalException, Exception {
        return ForecastingViewMasterLocalServiceUtil.getForecastingViewMaster(systemId);
    }

    /**
     * Adds the forecasting view master.
     *
     * @param forecastViewMaster the forecast view master
     * @return the forecasting view master
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    @Override
    public ForecastingViewMaster addForecastingViewMaster(final ForecastingViewMaster forecastViewMaster) throws SystemException, Exception {
        return ForecastingViewMasterLocalServiceUtil.addForecastingViewMaster(forecastViewMaster);
    }

    /**
     * Update forecasting view master.
     *
     * @param forecastViewMaster the forecast view master
     * @return the forecasting view master
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    @Override
    public ForecastingViewMaster updateForecastingViewMaster(final ForecastingViewMaster forecastViewMaster) throws SystemException, Exception {
        return ForecastingViewMasterLocalServiceUtil.updateForecastingViewMaster(forecastViewMaster);
    }

    /**
     * Gets the projection master.
     *
     * @param systemId the system id
     * @return the projection master
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    @Override
    public ProjectionMaster getProjectionMaster(final int systemId) throws PortalException, SystemException, Exception {
        return ProjectionMasterLocalServiceUtil.getProjectionMaster(systemId);
    }

    /**
     * Update projection master.
     *
     * @param projectionMaster the projection master
     * @return the projection master
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    @Override
    public ProjectionMaster updateProjectionMaster(final ProjectionMaster projectionMaster) throws PortalException, SystemException, Exception {
        return ProjectionMasterLocalServiceUtil.updateProjectionMaster(projectionMaster);
    }

    /**
     * Find by projection id_ projection cust details.
     *
     * @param query the query
     * @return the list< projection cust details>
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    @Override
    public List<ProjectionCustDetails> findCustDetailsByProjectionId(final DynamicQuery query) throws SystemException, Exception {
        return ProjectionCustDetailsLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * Delete projection cust details.
     *
     * @param projectionCustDetails the projection cust details
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    @Override
    public void deleteProjectionCustDetails(final ProjectionCustDetails projectionCustDetails) throws SystemException, Exception {
        ProjectionCustDetailsLocalServiceUtil.deleteProjectionCustDetails(projectionCustDetails);
    }

    /**
     * Find by projection id_ projection prod details.
     *
     * @param query the query
     * @return the list< projection prod details>
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    @Override
    public List<ProjectionProdDetails> findProdDetailsByProjectionId(final DynamicQuery query) throws SystemException, Exception {
        return ProjectionProdDetailsLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * Delete projection prod details by id.
     *
     * @param systemId the system id
     * @return the projection prod details
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    @Override
    public ProjectionProdDetails deleteProjectionProdDetailsById(final int systemId) throws SystemException, PortalException, Exception {
        return ProjectionProdDetailsLocalServiceUtil.deleteProjectionProdDetails(systemId);
    }

    /**
     * Gets the brand master list.
     *
     * @param query the query
     * @return the brand master list
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    @Override
    public List<BrandMaster> getBrandMasterList(final DynamicQuery query) throws SystemException, Exception {
        return BrandMasterLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * Gets the company master list.
     *
     * @param query the query
     * @return the company master list
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    @Override
    public List getCompanyMasterList(final DynamicQuery query) throws SystemException, Exception {
        return CompanyMasterLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * Gets the hierarchy data.
     *
     * @param indicator the indicator whether Customer hierarchy or Product
     * hierarchy
     * @param hierarchyName the hierarchy name
     * @param hierarchyType the hierarchy type
     * @return the hierarchy data
     */
    public List<HierarchyLookupDTO> getHierarchyData(final String indicator,
            final String hierarchyName, final String hierarchyType) {

        return null;
    }

    /**
     * Generates projection.
     *
     * @param dataSelectionDto the dataselection dto with values to generate
     * @return the projection ID
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    @Override
    public int generateProjection(DataSelectionDTO dataSelectionDto) throws SystemException, Exception {

        return 1;
    }

    /**
     * Search for projections.
     *
     * @param parameters the parameters
     * @return the projection master result list
     */
    @Override
    public List searchDSProjections(final Map<String, Object> parameters) throws SystemException {

        return ProjectionMasterLocalServiceUtil.searchDsProjection(parameters);
    }

    /**
     * Saves nm projection.
     *
     * @param projectionId the projection id
     */
    @Override
    public void saveNMProjection(int projectionId) {

    }

    /**
     * Submits nm projection.
     *
     * @param projectionId the projection id
     */
    @Override
    public void submitNMProjection(int projectionId) {

    }

    /* (non-Javadoc)
     * @see com.stpl.app.galforecasting.dao.DataSelectionDAO#getHierarchyGroup(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public List getHierarchyGroup(final String hierarchyName, final String hierarchyType, final String customerOrProduct, final String action) {
        return HierarchyDefinitionLocalServiceUtil.getHierarchyGroup(hierarchyName, hierarchyType, customerOrProduct, action);

    }


    /* (non-Javadoc)
     * @see com.stpl.app.galforecasting.dao.DataSelectionDAO#getCustomerForecastLevel(com.stpl.portal.kernel.dao.orm.DynamicQuery)
     */
    @Override
    public List getCustomerForecastLevel(DynamicQuery query) {
        try {
            return HierarchyDefinitionLocalServiceUtil.dynamicQuery(query);
        } catch (SystemException ex) {

        }
        return null;
    }

    /**
     * Gets the hierarchy level and its values from Relationship builder
     *
     * @param parameters the parameters
     * @return result list
     * @throws com.stpl.portal.kernel.exception.SystemException
     * @throws com.stpl.portal.kernel.exception.PortalException
     */
    @Override
    public List getRelationshipHierarchy(final Map<String, Object> parameters) throws SystemException, PortalException, Exception {
        return ProjectionMasterLocalServiceUtil.getRelationshipHierarchy(parameters);
    }

    /**
     * Save Customer hierarchy
     *
     * @param projectionCustHierarchy
     * @throws com.stpl.portal.kernel.exception.SystemException
     */
    @Override
    public void addProjectionCustHierarchy(final ProjectionCustHierarchy projectionCustHierarchy) throws SystemException {
        ProjectionCustHierarchyLocalServiceUtil.updateProjectionCustHierarchy(projectionCustHierarchy);
    }

    /**
     * Save Product hierarchy
     *
     * @param projectionProdHierarchy
     * @throws com.stpl.portal.kernel.exception.SystemException
     */
    @Override
    public void addProjectionProdHierarchy(final ProjectionProdHierarchy projectionProdHierarchy) throws SystemException {
        ProjectionProdHierarchyLocalServiceUtil.updateProjectionProdHierarchy(projectionProdHierarchy);
    }

    /**
     * Gets the CCP details list
     *
     * @param parameters contains set of parameters
     * @return
     * @throws SystemException
     * @throws Exception
     */
    @Override
    public List getCcpDetails(Map<String, Object> parameters) throws SystemException, Exception {
        return ProjectionMasterLocalServiceUtil.getCcpDetails(parameters);
    }

    /**
     * Gets the Levels from selected hierarchy
     *
     * @param parameters contains set of parameters
     * @return
     * @throws SystemException
     * @throws Exception
     */
    @Override
    public List getLevelsFromHierarchy(Map<String, Object> parameters) throws SystemException, Exception {
        return HierarchyDefinitionLocalServiceUtil.getLevelsFromHierarchy(parameters);
    }

    /**
     * Gets the list of company groups
     *
     * @param parameters the parameters
     * @return list of company groups
     * @throws SystemException
     * @throws Exception
     */
    @Override
    public List getCustomerGroup(final Map<String, Object> parameters) throws SystemException, Exception {
        return ProjectionMasterLocalServiceUtil.getCustomerProductGroup(parameters);
    }

    /**
     * Gets the list of Product groups.
     *
     * @param parameters the parameters
     * @return list of Product groups
     * @throws SystemException
     * @throws Exception
     */
    @Override
    public List getProductGroup(final Map<String, Object> parameters) throws SystemException, Exception {
        return ProjectionMasterLocalServiceUtil.getCustomerProductGroup(parameters);
    }

    /**
     * Gets the list of company groups
     *
     * @param dynamicQuery the dynamicQuery
     * @return list of company groups
     * @throws SystemException
     * @throws Exception
     */
    @Override
    public List getAllCustomerGroup(DynamicQuery dynamicQuery) throws SystemException, Exception {
        return CompanyGroupLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    /**
     * Gets list of Companies
     *
     * @param dynamicQuery
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public List<Object[]> getCompanies(DynamicQuery dynamicQuery) throws SystemException, Exception {
        return CompanyMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    @Override
    public int getCompaniesCount(DynamicQuery dynamicQuery) throws SystemException, Exception {
        return (int) CompanyMasterLocalServiceUtil.dynamicQueryCount(dynamicQuery);
    }

    @Override
    public List<ProjectionCustHierarchy> findCustHierarchyByProjectionId(DynamicQuery query) throws SystemException, Exception {
        return ProjectionCustHierarchyLocalServiceUtil.dynamicQuery(query);
    }

    @Override
    public List<ProjectionProdHierarchy> findProdHierarchyByProjectionId(DynamicQuery query) throws SystemException, Exception {
        return ProjectionProdHierarchyLocalServiceUtil.dynamicQuery(query);
    }

    @Override
    public void deleteProjectionCustHierarchies(ProjectionCustHierarchy projectionCustHierarchy) throws SystemException, Exception {
        ProjectionCustHierarchyLocalServiceUtil.deleteProjectionCustHierarchy(projectionCustHierarchy);
    }

    @Override
    public void deleteProjectionProdHierarchies(ProjectionProdHierarchy projectionProdHierarchy) throws SystemException, Exception {
        ProjectionProdHierarchyLocalServiceUtil.deleteProjectionProdHierarchy(projectionProdHierarchy);
    }

    @Override
    public List<ProjectionDetails> findProjDetailsByProjectionId(DynamicQuery query) throws SystemException, Exception {
        return ProjectionDetailsLocalServiceUtil.dynamicQuery(query);
    }

    @Override
    public void deleteProjectionDetails(ProjectionDetails projectionDetails) throws SystemException, Exception {
        ProjectionDetailsLocalServiceUtil.deleteProjectionDetails(projectionDetails);
    }

    @Override
    public List getForecastViewFromName(DynamicQuery dynamicQuery) throws SystemException, Exception {
        return ForecastingViewMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    @Override
    public List getProjection(int projectionId) {
        return ProjectionMasterLocalServiceUtil.getProjection(projectionId);
    }

    @Override
    public List getRelationShipValues(Map<String, Object> parameters) throws SystemException, Exception {
        return ProjectionMasterLocalServiceUtil.getRelationShipValues(parameters);
    }

    @Override
    public String deleteProjection(String str, int id) throws Exception {
        return ProjectionMasterLocalServiceUtil.deleteProjection(str, id);
    }

    @Override
    public List getParentLevels(int levelNo, int relationshipLevelSid, final Map<String, Object> parameters) throws SystemException, Exception {
        return ProjectionMasterLocalServiceUtil.getParentLevels(levelNo, relationshipLevelSid, parameters);
    }

    @Override
    public List getCustomerGroupDetails(final DynamicQuery dynamicQuery) throws SystemException, Exception {
        return CompanyGroupDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    @Override
    public List getItemGroupDetails(final DynamicQuery dynamicQuery) throws SystemException, Exception {
        return ItemGroupDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    @Override
    public List executeQuery(Map<String, Object> parameters) throws SystemException, Exception {
        return ProjectionMasterLocalServiceUtil.executeQuery(parameters);
    }

    @Override
    public List getHelperTableListNames(final DynamicQuery dynamicQuery) throws SystemException, Exception {
        return HelperTableLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    @Override
    public List getItemIdFromCompanyInCCp(DynamicQuery dynamicQuery) throws SystemException, Exception {
        return CcpDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    @Override
    public List getItemsFromBrand(Map<String, Object> parameters) throws SystemException, Exception {
        return ProjectionMasterLocalServiceUtil.getItemsFromBrand(parameters);
    }

    @Override
    public List getItemsFromBrand(DynamicQuery dynamicQuery) throws SystemException, Exception {
        return ItemMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    @Override
    public List getInnerLevel(Map<String, Object> parameters) throws SystemException, Exception {
        return ProjectionMasterLocalServiceUtil.getInnerLevel(parameters);
    }

    @Override
    public List getForecastConfig(DynamicQuery dynamicQuery) throws SystemException, Exception {
        return ForecastConfigLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    @Override
    public ProjectionDetails deleteProjectionDetails(int systemId) throws PortalException, SystemException {
        return ProjectionDetailsLocalServiceUtil.deleteProjectionDetails(systemId);
    }

    @Override
    public List<ProjectionDetails> getProjectionDetails(final DynamicQuery dynamicQuery) throws PortalException, SystemException {
        return ProjectionDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    @Override
    public ProjectionCustHierarchy deleteProjectionCustHierarchy(int systemId) throws PortalException, SystemException {
        return ProjectionCustHierarchyLocalServiceUtil.deleteProjectionCustHierarchy(systemId);
    }

    @Override
    public List<ProjectionCustHierarchy> getProjectionCustHierarchy(final DynamicQuery dynamicQuery) throws PortalException, SystemException {
        return ProjectionCustHierarchyLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    @Override
    public ProjectionProdHierarchy deleteProjectionProdHierarchy(int systemId) throws PortalException, SystemException {
        return ProjectionProdHierarchyLocalServiceUtil.deleteProjectionProdHierarchy(systemId);
    }

    @Override
    public List<ProjectionProdHierarchy> getProjectionProdHierarchy(final DynamicQuery dynamicQuery) throws PortalException, SystemException {
        return ProjectionProdHierarchyLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    @Override
    public ProjectionMaster deleteProjectionMaster(int systemId) throws PortalException, SystemException {
        return ProjectionMasterLocalServiceUtil.deleteProjectionMaster(systemId);
    }

    @Override
    public List<ProjectionMaster> getProjectionMaster(final DynamicQuery dynamicQuery) throws PortalException, SystemException {
        return ProjectionMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    @Override
    public List getCcpMap(Map<String, Object> parameters) throws SystemException, Exception {
        return ProjectionMasterLocalServiceUtil.getCcpMap(parameters);
    }

    @Override
    public void saveCcp(Map<String, Object> parameters) throws SystemException, Exception {
        ProjectionMasterLocalServiceUtil.saveCcp(parameters);
    }

    @Override
    public ProjectionDetails addProjectionDetails(ProjectionDetails projectionDetails) throws PortalException, SystemException, Exception {
        return ProjectionDetailsLocalServiceUtil.addProjectionDetails(projectionDetails);
    }

    @Override
    public Object tempOperation(Map<String, Object> input, String queryName) throws SystemException, Exception {
        return ProjectionMasterLocalServiceUtil.tempOperation(input, queryName);
    }

    @Override
    public List getProjections(DynamicQuery dynamicQuery) throws SystemException, PortalException, Exception {
        return ProjectionDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    @Override
    public int getRelationshipCount(DynamicQuery dynamicQuery) throws SystemException, PortalException {
        return (int) RelationshipBuilderLocalServiceUtil.dynamicQueryCount(dynamicQuery);
    }

    @Override
    public List getRelationship(DynamicQuery dynamicQuery) throws SystemException, PortalException {
        return RelationshipBuilderLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    @Override
    public List getChildLevels(Map<String, Object> parameters) throws SystemException, PortalException {
        return ProjectionMasterLocalServiceUtil.getChildLevels(parameters);
    }
    @Override
    public int getDiscountCount(DynamicQuery dynamicQuery) throws SystemException, Exception {
        return (int) RsModelLocalServiceUtil.dynamicQueryCount(dynamicQuery);
    }
    
    @Override
    public List<Object[]> getDiscounts(DynamicQuery dynamicQuery) throws SystemException, Exception {
        return RsModelLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    @Override
    public List executeQueryforchannel(Map<String, Object> parameters) throws SystemException, Exception {
       return ProjectionMasterLocalServiceUtil.executeQueryforchannel(parameters);
    }

    @Override
    public List getHelperTableList(DynamicQuery dynamicQuery) throws PortalException, SystemException  {
        return HelperTableLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
}
