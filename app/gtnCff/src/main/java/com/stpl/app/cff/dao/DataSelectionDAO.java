/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.dao;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.stpl.app.model.BrandMaster;
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
import java.util.List;
import java.util.Map;

/**
 *
 * @author mohamed.hameed
 */
public interface DataSelectionDAO {

    /**
     * Delete forecasting view master.
     *
     * @param systemId the system id
     * @return the forecasting view master
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    CffViewMaster deleteForecastingViewMaster(int systemId) throws SystemException, PortalException;

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
    CffViewMaster getForecastingViewMaster(int systemId) throws SystemException, PortalException;

    /**
     * Adds the forecasting view master.
     *
     * @param forecastViewMaster the forecast view master
     * @return the forecasting view master
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    CffViewMaster addForecastingViewMaster(CffViewMaster forecastViewMaster) throws SystemException;

    /**
     * Update forecasting view master.
     *
     * @param forecastViewMaster the forecast view master
     * @return the forecasting view master
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    CffViewMaster updateForecastingViewMaster(CffViewMaster forecastViewMaster) throws SystemException;

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
     * Save Customer hierarchy
     *
     * @param projectionCustHierarchy
     * @throws com.liferay.portal.kernel.exception.SystemException
     */
    public void addProjectionCustHierarchy(final CffCustHierarchy cffCustHierarchy) throws SystemException;

    /**
     * Save Product hierarchy
     *
     * @param cffProdHierarchy
     * @param projectionProdHierarchy
     * @throws com.liferay.portal.kernel.exception.SystemException
     */
    public void addProjectionProdHierarchy(final CffProdHierarchy cffProdHierarchy) throws SystemException;

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
     * Gets list of Companies
     *
     * @param dynamicQuery
     * @return
     * @throws com.liferay.portal.kernel.exception.SystemException
     */
    public List<Object[]> getCompanies(DynamicQuery dynamicQuery) throws SystemException;

    public int getCompaniesCount(DynamicQuery dynamicQuery) throws SystemException;

    public List<CffCustHierarchy> findCustHierarchyByProjectionId(final DynamicQuery query) throws SystemException;

    public List<CffProdHierarchy> findProdHierarchyByProjectionId(final DynamicQuery query) throws SystemException;

    public List<CffDetails> findProjDetailsByProjectionId(final DynamicQuery query) throws SystemException;

    public void deleteProjectionCustHierarchies(CffCustHierarchy projectionCustHierarchy) throws SystemException;

    public void deleteProjectionProdHierarchies(CffProdHierarchy projectionProdHierarchy) throws SystemException;

    public void deleteProjectionDetails(CffDetails projectionDetails) throws SystemException;

    public List getForecastViewFromName(DynamicQuery dynamicQuery) throws SystemException;

    public List getParentLevels(final int levelNo, final int relationshipLevelSid, final Map<String, Object> parameters) throws SystemException;

    public List getCustomerGroupDetails(final DynamicQuery dynamicQuery) throws SystemException;

    public List getItemGroupDetails(final DynamicQuery dynamicQuery) throws SystemException;

    public List executeQuery(final Map<String, Object> parameters) throws SystemException;

    public List executeQueryforchannel(final Map<String, Object> parameters) throws SystemException;

    public List getItemIdFromCompanyInCCp(final DynamicQuery dynamicQuery) throws SystemException;

    public List getInnerLevel(final Map<String, Object> parameters) throws SystemException;

    public List getForecastConfig(final DynamicQuery dynamicQuery) throws SystemException;

    public List getCcpMap(final Map<String, Object> parameters) throws SystemException;

    public Object tempOperation(final Map<String, Object> input, final String queryName) throws SystemException;

    public int getRelationshipCount(DynamicQuery dynamicQuery) throws SystemException, PortalException;

    public List getRelationship(DynamicQuery query) throws SystemException, PortalException;

    public List<Object[]> getDiscounts(DynamicQuery dynamicQuery) throws SystemException;

    CffMaster addCffMaster(CffMaster cffMaster) throws SystemException;

    public List getHelperTableList(DynamicQuery dynamicQuery) throws PortalException, SystemException;
}
