/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.dao;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.stpl.app.model.BrandMaster;
import com.stpl.app.model.ForecastingViewMaster;
import com.stpl.app.model.ProjectionCustDetails;
import com.stpl.app.model.ProjectionCustHierarchy;
import com.stpl.app.model.ProjectionDetails;
import com.stpl.app.model.ProjectionMaster;
import com.stpl.app.model.ProjectionProdDetails;
import com.stpl.app.model.ProjectionProdHierarchy;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.forecastds.dto.HierarchyLookupDTO;
import java.util.List;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Interface DataSelectionDAO.
 *
 * @author lokeshwari
 */
public interface DataSelectionDAO {

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
    List findViewByName(String viewName, String forecastType, String userId, String viewType)
            throws SystemException, PortalException;

    /**
     * Delete forecasting view master.
     *
     * @param systemId the system id
     * @return the forecasting view master
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    ForecastingViewMaster deleteForecastingViewMaster(int systemId) throws SystemException, PortalException;

    /**
     * Gets the forecast view masterdynamic query count.
     *
     * @param dynamicQuery the dynamic query
     * @return the forecast view masterdynamic query count
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    int getForecastViewMasterdynamicQueryCount(DynamicQuery dynamicQuery) throws SystemException;

    /**
     * Gets the user.
     *
     * @param systemId the system id
     * @return the user
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    User getUser(Long systemId) throws SystemException, PortalException;

    /**
     * Gets the item master.
     *
     * @param query the query
     * @return the item master
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    List getItemMaster(DynamicQuery query) throws SystemException;

    /**
     * Adds the projection master.
     *
     * @param projectionMaster the projection master
     * @return the projection master
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    ProjectionMaster addProjectionMaster(ProjectionMaster projectionMaster) throws SystemException;

    /**
     * Adds the projection cust details.
     *
     * @param projectionCustDetails the projection cust details
     * @return the projection cust details
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    ProjectionCustDetails addProjectionCustDetails(ProjectionCustDetails projectionCustDetails) throws SystemException;

    /**
     * Adds the projection prod details.
     *
     * @param projectionProdDetails the projection prod details
     * @return the projection prod details
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    ProjectionProdDetails addProjectionProdDetails(ProjectionProdDetails projectionProdDetails) throws SystemException;

    /**
     * Gets the forecasting view master.
     *
     * @param systemId the system id
     * @return the forecasting view master
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    ForecastingViewMaster getForecastingViewMaster(int systemId) throws SystemException, PortalException;

    /**
     * Adds the forecasting view master.
     *
     * @param forecastViewMaster the forecast view master
     * @return the forecasting view master
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    ForecastingViewMaster addForecastingViewMaster(ForecastingViewMaster forecastViewMaster) throws SystemException;

    /**
     * Update forecasting view master.
     *
     * @param forecastViewMaster the forecast view master
     * @return the forecasting view master
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    ForecastingViewMaster updateForecastingViewMaster(ForecastingViewMaster forecastViewMaster) throws SystemException;

    /**
     * Gets the projection master.
     *
     * @param systemId the system id
     * @return the projection master
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    ProjectionMaster getProjectionMaster(int systemId) throws PortalException, SystemException;

    /**
     * Update projection master.
     *
     * @param projectionMaster the projection master
     * @return the projection master
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    ProjectionMaster updateProjectionMaster(ProjectionMaster projectionMaster) throws PortalException, SystemException;

    /**
     * Find by projection id_ projection cust details.
     *
     * @param query the query
     * @return the list< projection cust details>
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    List<ProjectionCustDetails> findCustDetailsByProjectionId(DynamicQuery query) throws SystemException;

    /**
     * Delete projection cust details.
     *
     * @param projectionCustDetails the projection cust details
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    void deleteProjectionCustDetails(ProjectionCustDetails projectionCustDetails) throws SystemException;

    /**
     * Find by projection id_ projection prod details.
     *
     * @param query the query
     * @return the list< projection prod details>
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    List<ProjectionProdDetails> findProdDetailsByProjectionId(DynamicQuery query) throws SystemException;

    /**
     * Delete projection prod details by id.
     *
     * @param systemId the system id
     * @return the projection prod details
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    ProjectionProdDetails deleteProjectionProdDetailsById(int systemId) throws SystemException, PortalException;

    /**
     * Gets the brand master list.
     *
     * @param query the query
     * @return the brand master list
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    List<BrandMaster> getBrandMasterList(DynamicQuery query) throws SystemException;

    /**
     * Gets the company master list.
     *
     * @param query the query
     * @return the company master list
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    List getCompanyMasterList(DynamicQuery query) throws SystemException;

    public List getHierarchyGroup(final String hierarchyName, final String hierarchyType, final String customerOrProduct, final String action);

    public List getCustomerForecastLevel(DynamicQuery query);

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
            final String hierarchyName, final String hierarchyType);

    /**
     * Generates projection.
     *
     * @param dataSelectionDto the dataselection dto with values to generate
     * @return the projection ID
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public int generateProjection(final DataSelectionDTO dataSelectionDto) throws SystemException;

    /**
     * Search for projections.
     *
     * @param parameters the parameters
     * @return the projection master result list
     * @throws com.liferay.portal.kernel.exception.SystemException
     */
    public List searchDSProjections(final Map<String, Object> parameters) throws SystemException;

    /**
     * Saves nm projection.
     *
     * @param projectionId the projection id
     */
    public void saveNMProjection(int projectionId);

    /**
     * Submits nm projection.
     *
     * @param projectionId the projection id
     */
    public void submitNMProjection(int projectionId);

    /**
     * Gets the hierarchy level and its values from Relationship builder
     *
     * @param parameters the parameters
     * @return result list
     * @throws com.liferay.portal.kernel.exception.SystemException
     */
    public List getRelationshipHierarchy(final Map<String, Object> parameters) throws SystemException;

    /**
     * Save Customer hierarchy
     *
     * @param projectionCustHierarchy
     * @throws com.liferay.portal.kernel.exception.SystemException
     */
    public void addProjectionCustHierarchy(final ProjectionCustHierarchy projectionCustHierarchy) throws SystemException;

    /**
     * Save Product hierarchy
     *
     * @param projectionProdHierarchy
     * @throws com.liferay.portal.kernel.exception.SystemException
     */
    public void addProjectionProdHierarchy(final ProjectionProdHierarchy projectionProdHierarchy) throws SystemException;

    public List getCcpDetails(final Map<String, Object> parameters) throws SystemException;

    public List getLevelsFromHierarchy(final Map<String, Object> parameters) throws SystemException;

    /**
     * Gets the list of company groups
     *
     * @param parameters the parameters
     * @return list of company groups
     * @throws SystemException
     * @throws Exception
     */
    public List getCustomerGroup(final Map<String, Object> parameters) throws SystemException;

    /**
     * Gets the list of company groups
     *
     * @param dynamicQuery the dynamicQuery
     * @return list of company groups
     * @throws SystemException
     * @throws Exception
     */
    public List getAllCustomerGroup(final DynamicQuery dynamicQuery) throws SystemException;

    /**
     * Gets the list of Product groups.
     *
     * @param parameters the parameters
     * @return list of Product groups
     * @throws SystemException
     * @throws Exception
     */
    public List getProductGroup(final Map<String, Object> parameters) throws SystemException;

    /**
     * Gets list of Companies
     *
     * @param dynamicQuery
     * @return
     * @throws com.liferay.portal.kernel.exception.SystemException
     */
    public List<Object[]> getCompanies(DynamicQuery dynamicQuery) throws SystemException;

    public int getCompaniesCount(DynamicQuery dynamicQuery) throws SystemException;

    public List<ProjectionCustHierarchy> findCustHierarchyByProjectionId(final DynamicQuery query) throws SystemException;

    public List<ProjectionProdHierarchy> findProdHierarchyByProjectionId(final DynamicQuery query) throws SystemException;

    public List<ProjectionDetails> findProjDetailsByProjectionId(final DynamicQuery query) throws SystemException;

    public void deleteProjectionCustHierarchies(ProjectionCustHierarchy projectionCustHierarchy) throws SystemException;

    public void deleteProjectionProdHierarchies(ProjectionProdHierarchy projectionProdHierarchy) throws SystemException;

    public void deleteProjectionDetails(ProjectionDetails projectionDetails) throws SystemException;

    public List getForecastViewFromName(DynamicQuery dynamicQuery) throws SystemException;

    public List getProjection(int projectionId);

    public List getRelationShipValues(final Map<String, Object> parameters) throws SystemException;

    public String deleteProjection(String string, int projectionId);

    public List getParentLevels(final int levelNo, final int relationshipLevelSid, final Map<String, Object> parameters) throws SystemException;

    public List getCustomerGroupDetails(final DynamicQuery dynamicQuery) throws SystemException;

    public List getItemGroupDetails(final DynamicQuery dynamicQuery) throws SystemException;

    public List executeQuery(final Map<String, Object> parameters) throws SystemException;
    
    public List executeQueryforchannel(final Map<String, Object> parameters) throws SystemException;

    public List getHelperTableListNames(final DynamicQuery dynamicQuery) throws SystemException;

    public List getItemIdFromCompanyInCCp(final DynamicQuery dynamicQuery) throws SystemException;

    public List getItemsFromBrand(final Map<String, Object> parameters) throws SystemException;

    public List getItemsFromBrand(final DynamicQuery dynamicQuery) throws SystemException;

    public List getInnerLevel(final Map<String, Object> parameters) throws SystemException;

    public List getForecastConfig(final DynamicQuery dynamicQuery) throws SystemException;

    public List<ProjectionDetails> getProjectionDetails(final DynamicQuery dynamicQuery) throws PortalException, SystemException;

    public ProjectionDetails deleteProjectionDetails(final int systemId) throws PortalException, SystemException;

    public List<ProjectionCustHierarchy> getProjectionCustHierarchy(final DynamicQuery dynamicQuery) throws PortalException, SystemException;

    public ProjectionCustHierarchy deleteProjectionCustHierarchy(final int systemId) throws PortalException, SystemException;

    public List<ProjectionProdHierarchy> getProjectionProdHierarchy(final DynamicQuery dynamicQuery) throws PortalException, SystemException;

    public ProjectionProdHierarchy deleteProjectionProdHierarchy(final int systemId) throws PortalException, SystemException;

    public List<ProjectionMaster> getProjectionMaster(final DynamicQuery dynamicQuery) throws PortalException, SystemException;

    public ProjectionMaster deleteProjectionMaster(final int systemId) throws PortalException, SystemException;

    public List getCcpMap(final Map<String, Object> parameters) throws SystemException;

    public void saveCcp(final Map<String, Object> parameters) throws SystemException;

    ProjectionDetails addProjectionDetails(ProjectionDetails projectionDetails) throws PortalException, SystemException;
    
    public Object tempOperation(final Map<String, Object> input, final String queryName) throws SystemException;

    public List getProjections(final DynamicQuery dynamicQuery) throws SystemException, PortalException;

    public int getRelationshipCount(DynamicQuery dynamicQuery) throws SystemException, PortalException;

    public List getRelationship(DynamicQuery query) throws SystemException, PortalException;

    public List getChildLevels(final Map<String, Object> parameters) throws SystemException, PortalException;
    
    public int getDiscountCount(DynamicQuery dynamicQuery) throws SystemException;
    
    public List<Object[]> getDiscounts(DynamicQuery dynamicQuery) throws SystemException;
    
    public List getHelperTableList(DynamicQuery dynamicQuery) throws PortalException, SystemException;
    
}
