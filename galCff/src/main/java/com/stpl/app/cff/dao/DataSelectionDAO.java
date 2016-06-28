/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.dao;

import com.stpl.app.model.BrandMaster;
import com.stpl.app.model.ForecastingViewMaster;
import com.stpl.app.model.ProjectionCustDetails;
import com.stpl.app.model.ProjectionMaster;
import com.stpl.app.model.ProjectionProdDetails;
import com.stpl.app.parttwo.model.CffCustHierarchy;
import com.stpl.app.parttwo.model.CffDetails;
import com.stpl.app.parttwo.model.CffMaster;
import com.stpl.app.parttwo.model.CffProdHierarchy;
import com.stpl.app.parttwo.model.CffViewMaster;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.forecastds.dto.HierarchyLookupDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mohamed.hameed
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
            throws SystemException, PortalException, Exception;

    /**
     * Delete forecasting view master.
     *
     * @param systemId the system id
     * @return the forecasting view master
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    CffViewMaster deleteForecastingViewMaster(int systemId) throws SystemException, PortalException, Exception;

    /**
     * Gets the forecast view masterdynamic query count.
     *
     * @param dynamicQuery the dynamic query
     * @return the forecast view masterdynamic query count
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    int getForecastViewMasterdynamicQueryCount(DynamicQuery dynamicQuery) throws SystemException, Exception;

    /**
     * Gets the user.
     *
     * @param systemId the system id
     * @return the user
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    User getUser(Long systemId) throws SystemException, PortalException, Exception;

    /**
     * Gets the item master.
     *
     * @param query the query
     * @return the item master
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    List getItemMaster(DynamicQuery query) throws SystemException, Exception;

    /**
     * Adds the projection master.
     *
     * @param projectionMaster the projection master
     * @return the projection master
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    ProjectionMaster addProjectionMaster(ProjectionMaster projectionMaster) throws SystemException, Exception;

    /**
     * Adds the projection cust details.
     *
     * @param projectionCustDetails the projection cust details
     * @return the projection cust details
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    ProjectionCustDetails addProjectionCustDetails(ProjectionCustDetails projectionCustDetails) throws SystemException, Exception;

    /**
     * Adds the projection prod details.
     *
     * @param projectionProdDetails the projection prod details
     * @return the projection prod details
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    ProjectionProdDetails addProjectionProdDetails(ProjectionProdDetails projectionProdDetails) throws SystemException, Exception;

    /**
     * Gets the forecasting view master.
     *
     * @param systemId the system id
     * @return the forecasting view master
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    CffViewMaster getForecastingViewMaster(int systemId) throws SystemException, PortalException, Exception;

    /**
     * Adds the forecasting view master.
     *
     * @param forecastViewMaster the forecast view master
     * @return the forecasting view master
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    CffViewMaster addForecastingViewMaster(CffViewMaster forecastViewMaster) throws SystemException, Exception;

    /**
     * Update forecasting view master.
     *
     * @param forecastViewMaster the forecast view master
     * @return the forecasting view master
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    CffViewMaster updateForecastingViewMaster(CffViewMaster forecastViewMaster) throws SystemException, Exception;

    /**
     * Gets the projection master.
     *
     * @param systemId the system id
     * @return the projection master
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    ProjectionMaster getProjectionMaster(int systemId) throws PortalException, SystemException, Exception;

    /**
     * Update projection master.
     *
     * @param projectionMaster the projection master
     * @return the projection master
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    ProjectionMaster updateProjectionMaster(ProjectionMaster projectionMaster) throws PortalException, SystemException, Exception;

    /**
     * Find by projection id_ projection cust details.
     *
     * @param query the query
     * @return the list< projection cust details>
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    List<ProjectionCustDetails> findCustDetailsByProjectionId(DynamicQuery query) throws SystemException, Exception;

    /**
     * Delete projection cust details.
     *
     * @param projectionCustDetails the projection cust details
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    void deleteProjectionCustDetails(ProjectionCustDetails projectionCustDetails) throws SystemException, Exception;

    /**
     * Find by projection id_ projection prod details.
     *
     * @param query the query
     * @return the list< projection prod details>
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    List<ProjectionProdDetails> findProdDetailsByProjectionId(DynamicQuery query) throws SystemException, Exception;

    /**
     * Delete projection prod details by id.
     *
     * @param systemId the system id
     * @return the projection prod details
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    ProjectionProdDetails deleteProjectionProdDetailsById(int systemId) throws SystemException, PortalException, Exception;

    /**
     * Gets the brand master list.
     *
     * @param query the query
     * @return the brand master list
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    List<BrandMaster> getBrandMasterList(DynamicQuery query) throws SystemException, Exception;

    /**
     * Gets the company master list.
     *
     * @param query the query
     * @return the company master list
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    List getCompanyMasterList(DynamicQuery query) throws SystemException, Exception;

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
    public int generateProjection(final DataSelectionDTO dataSelectionDto) throws SystemException, Exception;

    /**
     * Search for projections.
     *
     * @param parameters the parameters
     * @return the projection master result list
     * @throws com.stpl.portal.kernel.exception.SystemException
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
     * @throws com.stpl.portal.kernel.exception.SystemException
     */
    public List getRelationshipHierarchy(final Map<String, Object> parameters) throws SystemException, Exception;

    /**
     * Save Customer hierarchy
     *
     * @param projectionCustHierarchy
     * @throws com.stpl.portal.kernel.exception.SystemException
     */
    public void addProjectionCustHierarchy(final CffCustHierarchy cffCustHierarchy) throws SystemException;

    /**
     * Save Product hierarchy
     *
     * @param cffProdHierarchy
     * @param projectionProdHierarchy
     * @throws com.stpl.portal.kernel.exception.SystemException
     */
    public void addProjectionProdHierarchy(final CffProdHierarchy cffProdHierarchy) throws SystemException;

    public List getCcpDetails(final Map<String, Object> parameters) throws SystemException, Exception;

    public List getLevelsFromHierarchy(final Map<String, Object> parameters) throws SystemException, Exception;

    /**
     * Gets the list of company groups
     *
     * @param parameters the parameters
     * @return list of company groups
     * @throws SystemException
     * @throws Exception
     */
    public List getCustomerGroup(final Map<String, Object> parameters) throws SystemException, Exception;

    /**
     * Gets the list of company groups
     *
     * @param dynamicQuery the dynamicQuery
     * @return list of company groups
     * @throws SystemException
     * @throws Exception
     */
    public List getAllCustomerGroup(final DynamicQuery dynamicQuery) throws SystemException, Exception;

    /**
     * Gets the list of Product groups.
     *
     * @param parameters the parameters
     * @return list of Product groups
     * @throws SystemException
     * @throws Exception
     */
    public List getProductGroup(final Map<String, Object> parameters) throws SystemException, Exception;

    /**
     * Gets list of Companies
     *
     * @param dynamicQuery
     * @return
     * @throws com.stpl.portal.kernel.exception.SystemException
     */
    public List<Object[]> getCompanies(DynamicQuery dynamicQuery) throws SystemException, Exception;

    public int getCompaniesCount(DynamicQuery dynamicQuery) throws SystemException, Exception;

    public List<CffCustHierarchy> findCustHierarchyByProjectionId(final DynamicQuery query) throws SystemException, Exception;

    public List<CffProdHierarchy> findProdHierarchyByProjectionId(final DynamicQuery query) throws SystemException, Exception;

    public List<CffDetails> findProjDetailsByProjectionId(final DynamicQuery query) throws SystemException, Exception;

    public void deleteProjectionCustHierarchies(CffCustHierarchy projectionCustHierarchy) throws SystemException, Exception;

    public void deleteProjectionProdHierarchies(CffProdHierarchy projectionProdHierarchy) throws SystemException, Exception;

    public void deleteProjectionDetails(CffDetails projectionDetails) throws SystemException, Exception;

    public List getForecastViewFromName(DynamicQuery dynamicQuery) throws SystemException, Exception;

    public List getProjection(int projectionId);

    public List getRelationShipValues(final Map<String, Object> parameters) throws SystemException, Exception;

    public String deleteProjection(String string, int projectionId) throws Exception;

    public List getParentLevels(final int levelNo, final int relationshipLevelSid, final Map<String, Object> parameters) throws SystemException, Exception;

    public List getCustomerGroupDetails(final DynamicQuery dynamicQuery) throws SystemException, Exception;

    public List getItemGroupDetails(final DynamicQuery dynamicQuery) throws SystemException, Exception;

    public List executeQuery(final Map<String, Object> parameters) throws SystemException, Exception;

    public List executeQueryforchannel(final Map<String, Object> parameters) throws SystemException, Exception;

    public List getHelperTableListNames(final DynamicQuery dynamicQuery) throws SystemException, Exception;

    public List getItemIdFromCompanyInCCp(final DynamicQuery dynamicQuery) throws SystemException, Exception;

    public List getItemsFromBrand(final Map<String, Object> parameters) throws SystemException, Exception;

    public List getItemsFromBrand(final DynamicQuery dynamicQuery) throws SystemException, Exception;

    public List getInnerLevel(final Map<String, Object> parameters) throws SystemException, Exception;

    public List getForecastConfig(final DynamicQuery dynamicQuery) throws SystemException, Exception;

    public List<CffDetails> getProjectionDetails(final DynamicQuery dynamicQuery) throws PortalException, SystemException;

    public CffDetails deleteProjectionDetails(final int systemId) throws PortalException, SystemException;

    public List<CffCustHierarchy> getProjectionCustHierarchy(final DynamicQuery dynamicQuery) throws PortalException, SystemException;

    public CffCustHierarchy deleteProjectionCustHierarchy(final int systemId) throws PortalException, SystemException;

    public List<CffProdHierarchy> getProjectionProdHierarchy(final DynamicQuery dynamicQuery) throws PortalException, SystemException;

    public CffProdHierarchy deleteProjectionProdHierarchy(final int systemId) throws PortalException, SystemException;

    public List<CffMaster> getProjectionMaster(final DynamicQuery dynamicQuery) throws PortalException, SystemException;

    public CffMaster deleteProjectionMaster(final int systemId) throws PortalException, SystemException;

    public List getCcpMap(final Map<String, Object> parameters) throws SystemException, Exception;

    public void saveCcp(final Map<String, Object> parameters) throws SystemException, Exception;

    CffDetails addProjectionDetails(CffDetails projectionDetails) throws PortalException, SystemException, Exception;

    public Object tempOperation(final Map<String, Object> input, final String queryName) throws SystemException, Exception;

    public List getProjections(final DynamicQuery dynamicQuery) throws SystemException, PortalException, Exception;

    public int getRelationshipCount(DynamicQuery dynamicQuery) throws SystemException, PortalException;

    public List getRelationship(DynamicQuery query) throws SystemException, PortalException;

    public List getChildLevels(final Map<String, Object> parameters) throws SystemException, PortalException;

    public int getDiscountCount(DynamicQuery dynamicQuery) throws SystemException, Exception;

    public List<Object[]> getDiscounts(DynamicQuery dynamicQuery) throws SystemException, Exception;

    CffMaster addCffMaster(CffMaster cffMaster) throws SystemException, Exception;

    public List getHelperTableList(DynamicQuery dynamicQuery) throws PortalException, SystemException;
}
