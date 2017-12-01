/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.dao.impl;

import com.stpl.app.gtnforecasting.dao.DataSelectionDAO;
import static com.stpl.app.gtnforecasting.logic.CommonLogic.LOGGER;
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
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.forecastds.dto.HierarchyLookupDTO;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.stpl.app.gtnforecasting.service.finderImpl.ForecastingViewMasterImpl;
import com.stpl.app.gtnforecasting.service.finderImpl.HierarchyDefinitionImpl;
import com.stpl.app.gtnforecasting.service.finderImpl.ProjectionMasterImpl;
import java.util.Collections;
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
            throws SystemException, PortalException {
        return new ForecastingViewMasterImpl().findViewByName(viewName, forecastType, userId, viewType);
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
    public ForecastingViewMaster deleteForecastingViewMaster(final int systemId) throws SystemException, PortalException {
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
    public int getForecastViewMasterdynamicQueryCount(final DynamicQuery dynamicQuery) throws SystemException{
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
    public User getUser(final Long systemId) throws SystemException, PortalException{
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
    public List getItemMaster(final DynamicQuery query) throws SystemException{
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
    public ProjectionMaster addProjectionMaster(final ProjectionMaster projectionMaster) throws SystemException {
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
    public ProjectionCustDetails addProjectionCustDetails(final ProjectionCustDetails projectionCustDetails) throws SystemException{
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
    public ProjectionProdDetails addProjectionProdDetails(final ProjectionProdDetails projectionProdDetails) throws SystemException{
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
    public ForecastingViewMaster getForecastingViewMaster(final int systemId) throws SystemException, PortalException{
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
    public ForecastingViewMaster addForecastingViewMaster(final ForecastingViewMaster forecastViewMaster) throws SystemException{
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
    public ForecastingViewMaster updateForecastingViewMaster(final ForecastingViewMaster forecastViewMaster) throws SystemException{
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
    public ProjectionMaster getProjectionMaster(final int systemId) throws PortalException, SystemException {
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
    public ProjectionMaster updateProjectionMaster(final ProjectionMaster projectionMaster) throws PortalException, SystemException{
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
    public List<ProjectionCustDetails> findCustDetailsByProjectionId(final DynamicQuery query) throws SystemException{
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
    public void deleteProjectionCustDetails(final ProjectionCustDetails projectionCustDetails) throws SystemException {
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
    public List<ProjectionProdDetails> findProdDetailsByProjectionId(final DynamicQuery query) throws SystemException {
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
    public ProjectionProdDetails deleteProjectionProdDetailsById(final int systemId) throws SystemException, PortalException {
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
    public List<BrandMaster> getBrandMasterList(final DynamicQuery query) throws SystemException{
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
    public List getCompanyMasterList(final DynamicQuery query) throws SystemException{
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

        return Collections.emptyList();
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
    public int generateProjection(DataSelectionDTO dataSelectionDto) throws SystemException{

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

        return new ProjectionMasterImpl().searchDsProjection(parameters);
    }

    /**
     * Saves nm projection.
     *
     * @param projectionId the projection id
     */
    @Override
    public void saveNMProjection(int projectionId) {
        return;
    }

    /**
     * Submits nm projection.
     *
     * @param projectionId the projection id
     */
    @Override
    public void submitNMProjection(int projectionId) {
        return;
    }

    /* (non-Javadoc)
     * @see com.stpl.app.gtnforecasting.dao.DataSelectionDAO#getHierarchyGroup(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public List getHierarchyGroup(final String hierarchyName, final String hierarchyType, final String customerOrProduct, final String action) {
        return new HierarchyDefinitionImpl().getHierarchyGroup(hierarchyName, hierarchyType, customerOrProduct, action);
    }


    /* (non-Javadoc)
     * @see com.stpl.app.gtnforecasting.dao.DataSelectionDAO#getCustomerForecastLevel(com.liferay.portal.kernel.dao.orm.DynamicQuery)
     */
    @Override
    public List getCustomerForecastLevel(DynamicQuery query) {
        try {
            return HierarchyDefinitionLocalServiceUtil.dynamicQuery(query);
        } catch (SystemException ex) {
                LOGGER.error(ex); 
        }
        return Collections.emptyList();
    }

    /**
     * Gets the hierarchy level and its values from Relationship builder
     *
     * @param parameters the parameters
     * @return result list
     * @throws com.liferay.portal.kernel.exception.SystemException
     * @throws com.liferay.portal.kernel.exception.PortalException
     */
    @Override
    public List getRelationshipHierarchy(final Map<String, Object> parameters) throws SystemException{
        return new ProjectionMasterImpl().getRelationshipHierarchy(parameters);
    }

    /**
     * Save Customer hierarchy
     *
     * @param projectionCustHierarchy
     * @throws com.liferay.portal.kernel.exception.SystemException
     */
    @Override
    public void addProjectionCustHierarchy(final ProjectionCustHierarchy projectionCustHierarchy) throws SystemException {
        ProjectionCustHierarchyLocalServiceUtil.updateProjectionCustHierarchy(projectionCustHierarchy);
    }

    /**
     * Save Product hierarchy
     *
     * @param projectionProdHierarchy
     * @throws com.liferay.portal.kernel.exception.SystemException
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
    public List getCcpDetails(Map<String, Object> parameters) throws SystemException {
        return new ProjectionMasterImpl().getCcpDetails(parameters);
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
    public List getLevelsFromHierarchy(Map<String, Object> parameters) throws SystemException{
        return new HierarchyDefinitionImpl().getLevelsFromHierarchy(parameters);
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
    public List getCustomerGroup(final Map<String, Object> parameters) throws SystemException{
        return new ProjectionMasterImpl().getCustomerProductGroup(parameters);
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
    public List getProductGroup(final Map<String, Object> parameters) throws SystemException {
        return new ProjectionMasterImpl().getCustomerProductGroup(parameters);
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
    public List getAllCustomerGroup(DynamicQuery dynamicQuery) throws SystemException {
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
    public List<Object[]> getCompanies(DynamicQuery dynamicQuery) throws SystemException {
        return CompanyMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    @Override
    public int getCompaniesCount(DynamicQuery dynamicQuery) throws SystemException {
        return (int) CompanyMasterLocalServiceUtil.dynamicQueryCount(dynamicQuery);
    }

    @Override
    public List<ProjectionCustHierarchy> findCustHierarchyByProjectionId(DynamicQuery query) throws SystemException{
        return ProjectionCustHierarchyLocalServiceUtil.dynamicQuery(query);
    }

    @Override
    public List<ProjectionProdHierarchy> findProdHierarchyByProjectionId(DynamicQuery query) throws SystemException {
        return ProjectionProdHierarchyLocalServiceUtil.dynamicQuery(query);
    }

    @Override
    public void deleteProjectionCustHierarchies(ProjectionCustHierarchy projectionCustHierarchy) throws SystemException{
        ProjectionCustHierarchyLocalServiceUtil.deleteProjectionCustHierarchy(projectionCustHierarchy);
    }

    @Override
    public void deleteProjectionProdHierarchies(ProjectionProdHierarchy projectionProdHierarchy) throws SystemException {
        ProjectionProdHierarchyLocalServiceUtil.deleteProjectionProdHierarchy(projectionProdHierarchy);
    }

    @Override
    public List<ProjectionDetails> findProjDetailsByProjectionId(DynamicQuery query) throws SystemException {
        return ProjectionDetailsLocalServiceUtil.dynamicQuery(query);
    }

    @Override
    public void deleteProjectionDetails(ProjectionDetails projectionDetails) throws SystemException{
        ProjectionDetailsLocalServiceUtil.deleteProjectionDetails(projectionDetails);
    }

    @Override
    public List getForecastViewFromName(DynamicQuery dynamicQuery) throws SystemException{
        return ForecastingViewMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    @Override
    public List getProjection(int projectionId) {
        return new ProjectionMasterImpl().getProjection(projectionId);
    }

    @Override
    public List getRelationShipValues(Map<String, Object> parameters) throws SystemException{
        return new ProjectionMasterImpl().getRelationShipValues(parameters);
    }

    @Override
    public String deleteProjection(String str, int id)  {
        return new ProjectionMasterImpl().deleteProjection(str, id);
    }

    @Override
    public List getParentLevels(int levelNo, int relationshipLevelSid, final Map<String, Object> parameters) throws SystemException {
        return new ProjectionMasterImpl().getParentLevels(levelNo, relationshipLevelSid, parameters);
    }

    @Override
    public List getCustomerGroupDetails(final DynamicQuery dynamicQuery) throws SystemException {
        return CompanyGroupDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    @Override
    public List getItemGroupDetails(final DynamicQuery dynamicQuery) throws SystemException {
        return ItemGroupDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    @Override
    public List executeQuery(Map<String, Object> parameters) throws SystemException{
        return  new ProjectionMasterImpl().executeQuery(parameters);
    }

    @Override
    public List getHelperTableListNames(final DynamicQuery dynamicQuery) throws SystemException {
        return HelperTableLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    @Override
    public List getItemIdFromCompanyInCCp(DynamicQuery dynamicQuery) throws SystemException {
        return CcpDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    @Override
    public List getItemsFromBrand(Map<String, Object> parameters) throws SystemException {
        return new ProjectionMasterImpl().getItemsFromBrand(parameters);
    }

    @Override
    public List getItemsFromBrand(DynamicQuery dynamicQuery) throws SystemException{
        return ItemMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    @Override
    public List getInnerLevel(Map<String, Object> parameters) throws SystemException {
        return new ProjectionMasterImpl().getInnerLevel(parameters);
    }

    @Override
    public List getForecastConfig(DynamicQuery dynamicQuery) throws SystemException {
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
    public List getCcpMap(Map<String, Object> parameters) throws SystemException {
        return new ProjectionMasterImpl().getCcpMap(parameters);
    }

    @Override
    public void saveCcp(Map<String, Object> parameters) throws SystemException{
        new ProjectionMasterImpl().saveCcp(parameters);
    }

    @Override
    public ProjectionDetails addProjectionDetails(ProjectionDetails projectionDetails) throws PortalException, SystemException{
        return ProjectionDetailsLocalServiceUtil.addProjectionDetails(projectionDetails);
    }

    @Override
    public Object tempOperation(Map<String, Object> input, String queryName) throws SystemException {
        return new ProjectionMasterImpl().tempOperation(input, queryName);
    }

    @Override
    public List getProjections(DynamicQuery dynamicQuery) throws SystemException, PortalException {
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
        return new ProjectionMasterImpl().getChildLevels(parameters);
    }
    @Override
    public int getDiscountCount(DynamicQuery dynamicQuery) throws SystemException {
        return (int) RsModelLocalServiceUtil.dynamicQueryCount(dynamicQuery);
    }
    
    @Override
    public List<Object[]> getDiscounts(DynamicQuery dynamicQuery) throws SystemException {
        return RsModelLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    @Override
    public List executeQueryforchannel(Map<String, Object> parameters) throws SystemException {
       return new ProjectionMasterImpl().executeQueryforchannel(parameters);
    }

    @Override
    public List getHelperTableList(DynamicQuery dynamicQuery) throws PortalException, SystemException  {
        return HelperTableLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
}
