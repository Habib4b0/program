package com.stpl.app.service;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for ProjectionMaster. This utility wraps
 * {@link com.stpl.app.service.impl.ProjectionMasterLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see ProjectionMasterLocalService
 * @see com.stpl.app.service.base.ProjectionMasterLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.ProjectionMasterLocalServiceImpl
 * @generated
 */
public class ProjectionMasterLocalServiceUtil {
    private static ProjectionMasterLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.ProjectionMasterLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the projection master to the database. Also notifies the appropriate model listeners.
    *
    * @param projectionMaster the projection master
    * @return the projection master that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ProjectionMaster addProjectionMaster(
        com.stpl.app.model.ProjectionMaster projectionMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().addProjectionMaster(projectionMaster);
    }

    /**
    * Creates a new projection master with the primary key. Does not add the projection master to the database.
    *
    * @param projectionMasterSid the primary key for the new projection master
    * @return the new projection master
    */
    public static com.stpl.app.model.ProjectionMaster createProjectionMaster(
        int projectionMasterSid) {
        return getService().createProjectionMaster(projectionMasterSid);
    }

    /**
    * Deletes the projection master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param projectionMasterSid the primary key of the projection master
    * @return the projection master that was removed
    * @throws PortalException if a projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ProjectionMaster deleteProjectionMaster(
        int projectionMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteProjectionMaster(projectionMasterSid);
    }

    /**
    * Deletes the projection master from the database. Also notifies the appropriate model listeners.
    *
    * @param projectionMaster the projection master
    * @return the projection master that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ProjectionMaster deleteProjectionMaster(
        com.stpl.app.model.ProjectionMaster projectionMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteProjectionMaster(projectionMaster);
    }

    public static com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return getService().dynamicQuery();
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService()
                   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.stpl.portal.kernel.dao.orm.Projection projection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery, projection);
    }

    public static com.stpl.app.model.ProjectionMaster fetchProjectionMaster(
        int projectionMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().fetchProjectionMaster(projectionMasterSid);
    }

    /**
    * Returns the projection master with the primary key.
    *
    * @param projectionMasterSid the primary key of the projection master
    * @return the projection master
    * @throws PortalException if a projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ProjectionMaster getProjectionMaster(
        int projectionMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getProjectionMaster(projectionMasterSid);
    }

    public static com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the projection masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of projection masters
    * @param end the upper bound of the range of projection masters (not inclusive)
    * @return the range of projection masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ProjectionMaster> getProjectionMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getProjectionMasters(start, end);
    }

    /**
    * Returns the number of projection masters.
    *
    * @return the number of projection masters
    * @throws SystemException if a system exception occurred
    */
    public static int getProjectionMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getProjectionMastersCount();
    }

    /**
    * Updates the projection master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param projectionMaster the projection master
    * @return the projection master that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ProjectionMaster updateProjectionMaster(
        com.stpl.app.model.ProjectionMaster projectionMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().updateProjectionMaster(projectionMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public static java.lang.String getBeanIdentifier() {
        return getService().getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public static void setBeanIdentifier(java.lang.String beanIdentifier) {
        getService().setBeanIdentifier(beanIdentifier);
    }

    public static java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return getService().invokeMethod(name, parameterTypes, arguments);
    }

    public static java.util.List getRelationshipHierarchy(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getService().getRelationshipHierarchy(parameters);
    }

    public static java.util.List getCcpDetails(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getService().getCcpDetails(parameters);
    }

    public static java.util.List getCustomerProductGroup(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getService().getCustomerProductGroup(parameters);
    }

    public static java.util.List searchDsProjection(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getService().searchDsProjection(parameters);
    }

    public static java.util.List getProjection(int projectionId) {
        return getService().getProjection(projectionId);
    }

    public static java.util.List getRelationShipValues(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getService().getRelationShipValues(parameters);
    }

    public static java.lang.String deleteProjection(
        java.lang.String TableName, int projectionID) {
        return getService().deleteProjection(TableName, projectionID);
    }

    public static java.util.List getParentLevels(int levelNo,
        int relationshipLevelSid,
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getService()
                   .getParentLevels(levelNo, relationshipLevelSid, parameters);
    }

    public static java.util.List executeQuery(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getService().executeQuery(parameters);
    }

    public static java.util.List getItemsFromBrand(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getService().getItemsFromBrand(parameters);
    }

    public static java.util.List getInnerLevel(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getService().getInnerLevel(parameters);
    }

    public static void saveCcp(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        getService().saveCcp(parameters);
    }

    public static void saveCcpForMandated(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        getService().saveCcpForMandated(parameters);
    }

    public static java.util.List getCcpMap(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getService().getCcpMap(parameters);
    }

    public static java.util.List getLevelCcp(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getService().getLevelCcp(parameters);
    }

    public static java.lang.Object tempOperation(
        java.util.Map<java.lang.String, java.lang.Object> input,
        java.lang.String queryName) {
        return getService().tempOperation(input, queryName);
    }

    public static java.util.List getChildLevels(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getService().getChildLevels(parameters);
    }

    public static java.util.List getRelationShipValuesforchannel(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getService().getRelationShipValuesforchannel(parameters);
    }

    public static java.util.List searchProjections(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getService().searchProjections(parameters);
    }

    public static int getForecastViewCount(java.lang.String viewName,
        java.lang.String viewType) {
        return getService().getForecastViewCount(viewName, viewType);
    }

    public static java.util.List executeQueryforchannel(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getService().executeQueryforchannel(parameters);
    }

    public static java.util.List getCcpMapforchannel(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getService().getCcpMapforchannel(parameters);
    }

    public static void saveCcpforchannel(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        getService().saveCcpforchannel(parameters);
    }

    public static java.util.List getCustomerProductGroupforchannel(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getService().getCustomerProductGroupforchannel(parameters);
    }

    public static java.util.List getInnerLevelforchannel(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getService().getInnerLevelforchannel(parameters);
    }

    public static java.util.List getParentLevelsforchannel(int levelNo,
        int relationshipLevelSid,
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getService()
                   .getParentLevelsforchannel(levelNo, relationshipLevelSid,
            parameters);
    }

    public static java.util.List getChildLevelsforchannel(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return getService().getChildLevelsforchannel(parameters);
    }

    public static java.util.List getProjectionforchannel(int projectionId) {
        return getService().getProjectionforchannel(projectionId);
    }

    public static java.lang.String deleteProjectionforchannel(
        java.lang.String tableName, int projectionID) {
        return getService().deleteProjectionforchannel(tableName, projectionID);
    }

    public static void clearService() {
        _service = null;
    }

    public static ProjectionMasterLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ProjectionMasterLocalService.class.getName());

            if (invokableLocalService instanceof ProjectionMasterLocalService) {
                _service = (ProjectionMasterLocalService) invokableLocalService;
            } else {
                _service = new ProjectionMasterLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(ProjectionMasterLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(ProjectionMasterLocalService service) {
    }
}
