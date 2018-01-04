/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.dao.impl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.stpl.app.cff.dao.CommonServiceImpl;
import com.stpl.app.cff.dao.DataSelectionDAO;
import com.stpl.app.model.BrandMaster;
import com.stpl.app.model.ProjectionCustDetails;
import com.stpl.app.model.ProjectionMaster;
import com.stpl.app.model.ProjectionProdDetails;
import com.stpl.app.parttwo.model.CffCustHierarchy;
import com.stpl.app.parttwo.model.CffDetails;
import com.stpl.app.parttwo.model.CffMaster;
import com.stpl.app.parttwo.model.CffProdHierarchy;
import com.stpl.app.parttwo.model.CffViewMaster;
import com.stpl.app.parttwo.service.CffCustHierarchyLocalServiceUtil;
import com.stpl.app.parttwo.service.CffDetailsLocalServiceUtil;
import com.stpl.app.parttwo.service.CffMasterLocalServiceUtil;
import com.stpl.app.parttwo.service.CffProdHierarchyLocalServiceUtil;
import com.stpl.app.parttwo.service.CffViewMasterLocalServiceUtil;
import com.stpl.app.service.BrandMasterLocalServiceUtil;
import com.stpl.app.service.CcpDetailsLocalServiceUtil;
import com.stpl.app.service.CompanyGroupDetailsLocalServiceUtil;
import com.stpl.app.service.CompanyGroupLocalServiceUtil;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.ForecastConfigLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.HierarchyDefinitionLocalServiceUtil;
import com.stpl.app.service.ItemGroupDetailsLocalServiceUtil;
import com.stpl.app.service.ItemMasterLocalServiceUtil;
import com.stpl.app.service.ProjectionCustDetailsLocalServiceUtil;
import com.stpl.app.service.ProjectionMasterLocalServiceUtil;
import com.stpl.app.service.ProjectionProdDetailsLocalServiceUtil;
import com.stpl.app.service.RelationshipBuilderLocalServiceUtil;
import com.stpl.app.service.RsModelLocalServiceUtil;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.forecastds.dto.HierarchyLookupDTO;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.jboss.logging.Logger;

/**
 *
 * @author mohamed.hameed
 */
public class DataSelectionDAOImpl implements DataSelectionDAO {
    
     private static final Logger LOGGER = Logger.getLogger(DataSelectionDAOImpl.class);

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
    public CffViewMaster deleteForecastingViewMaster(final int systemId) throws SystemException, PortalException {
        return CffViewMasterLocalServiceUtil.deleteCffViewMaster(systemId);
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
    public int getForecastViewMasterdynamicQueryCount(final DynamicQuery dynamicQuery) throws SystemException {
        return (int) CffViewMasterLocalServiceUtil.dynamicQueryCount(dynamicQuery);
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
    public User getUser(final Long systemId) throws SystemException, PortalException {
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
    public List getItemMaster(final DynamicQuery query) throws SystemException {
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
    public ProjectionCustDetails addProjectionCustDetails(final ProjectionCustDetails projectionCustDetails) throws SystemException {
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
    public ProjectionProdDetails addProjectionProdDetails(final ProjectionProdDetails projectionProdDetails) throws SystemException {
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
    public CffViewMaster getForecastingViewMaster(final int systemId) throws SystemException, PortalException {
        return CffViewMasterLocalServiceUtil.getCffViewMaster(systemId);
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
    public CffViewMaster addForecastingViewMaster(final CffViewMaster forecastViewMaster) throws SystemException {
        return CffViewMasterLocalServiceUtil.addCffViewMaster(forecastViewMaster);
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
    public CffViewMaster updateForecastingViewMaster(final CffViewMaster forecastViewMaster) throws SystemException {
        return CffViewMasterLocalServiceUtil.updateCffViewMaster(forecastViewMaster);
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
    public ProjectionMaster updateProjectionMaster(final ProjectionMaster projectionMaster) throws PortalException, SystemException {
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
    public List<ProjectionCustDetails> findCustDetailsByProjectionId(final DynamicQuery query) throws SystemException {
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
    public List<BrandMaster> getBrandMasterList(final DynamicQuery query) throws SystemException {
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
    public List getCompanyMasterList(final DynamicQuery query) throws SystemException {
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
     @Override
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
    public int generateProjection(DataSelectionDTO dataSelectionDto) throws SystemException {

        return 1;
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
        return CommonServiceImpl.getInstance().getHierarchyGroup(hierarchyName, hierarchyType, customerOrProduct, action);

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
     * Save Customer hierarchy
     *
     * @param projectionCustHierarchy
     * @throws com.liferay.portal.kernel.exception.SystemException
     */
    @Override
    public void addProjectionCustHierarchy(final CffCustHierarchy cffCustHierarchy) throws SystemException {
        CffCustHierarchyLocalServiceUtil.updateCffCustHierarchy(cffCustHierarchy);
    }

    /**
     * Save Product hierarchy
     *
     * @param projectionProdHierarchy
     * @throws com.liferay.portal.kernel.exception.SystemException
     */
    @Override
    public void addProjectionProdHierarchy(final CffProdHierarchy cffProdHierarchy) throws SystemException {
        CffProdHierarchyLocalServiceUtil.updateCffProdHierarchy(cffProdHierarchy);
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
    public List<CffCustHierarchy> findCustHierarchyByProjectionId(DynamicQuery query) throws SystemException {
        return CffCustHierarchyLocalServiceUtil.dynamicQuery(query);
    }

    @Override
    public List<CffProdHierarchy> findProdHierarchyByProjectionId(DynamicQuery query) throws SystemException {
        return CffProdHierarchyLocalServiceUtil.dynamicQuery(query);
    }

    @Override
    public void deleteProjectionCustHierarchies(CffCustHierarchy projectionCustHierarchy) throws SystemException {
        CffCustHierarchyLocalServiceUtil.deleteCffCustHierarchy(projectionCustHierarchy);
    }

    @Override
    public void deleteProjectionProdHierarchies(CffProdHierarchy projectionProdHierarchy) throws SystemException {
        CffProdHierarchyLocalServiceUtil.deleteCffProdHierarchy(projectionProdHierarchy);
    }

    @Override
    public List<CffDetails> findProjDetailsByProjectionId(DynamicQuery query) throws SystemException {
        return CffDetailsLocalServiceUtil.dynamicQuery(query);
    }

    @Override
    public void deleteProjectionDetails(CffDetails projectionDetails) throws SystemException {
        CffDetailsLocalServiceUtil.deleteCffDetails(projectionDetails);
    }

    @Override
    public List getForecastViewFromName(DynamicQuery dynamicQuery) throws SystemException {
        return CffViewMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    @Override
    public List getParentLevels(int levelNo, int relationshipLevelSid, final Map<String, Object> parameters) throws SystemException {
        return CommonServiceImpl.getInstance().getParentLevels(levelNo, relationshipLevelSid, parameters);
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
    public List executeQuery(Map<String, Object> parameters) throws SystemException {
        return CommonServiceImpl.getInstance().executeQuery(parameters);
    }

    @Override
    public List getItemIdFromCompanyInCCp(DynamicQuery dynamicQuery) throws SystemException {
        return CcpDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    @Override
    public List getInnerLevel(Map<String, Object> parameters) throws SystemException {
        return CommonServiceImpl.getInstance().getInnerLevel(parameters);
    }

    @Override
    public List getForecastConfig(DynamicQuery dynamicQuery) throws SystemException {
        return ForecastConfigLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    @Override
    public List getCcpMap(Map<String, Object> parameters) throws SystemException {
        return CommonServiceImpl.getInstance().getCcpMap(parameters);
    }
    
    @Override
    public Object tempOperation(Map<String, Object> input, String queryName) throws SystemException {
        return CommonServiceImpl.getInstance().tempOperation(input, queryName);
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
    public List<Object[]> getDiscounts(DynamicQuery dynamicQuery) throws SystemException {
        return RsModelLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    @Override
    public List executeQueryforchannel(Map<String, Object> parameters) throws SystemException {
        return CommonServiceImpl.getInstance().executeQueryforchannel(parameters);
    }

     @Override
    public CffMaster addCffMaster(CffMaster cffMaster) throws SystemException {
        return CffMasterLocalServiceUtil.addCffMaster(cffMaster);

    }
      @Override
    public List getHelperTableList(DynamicQuery dynamicQuery) throws PortalException, SystemException  {
        return HelperTableLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
}
