package com.stpl.app.service;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for HierarchyLevelValues. This utility wraps
 * {@link com.stpl.app.service.impl.HierarchyLevelValuesLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see HierarchyLevelValuesLocalService
 * @see com.stpl.app.service.base.HierarchyLevelValuesLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.HierarchyLevelValuesLocalServiceImpl
 * @generated
 */
public class HierarchyLevelValuesLocalServiceUtil {
    private static HierarchyLevelValuesLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.HierarchyLevelValuesLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the hierarchy level values to the database. Also notifies the appropriate model listeners.
    *
    * @param hierarchyLevelValues the hierarchy level values
    * @return the hierarchy level values that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HierarchyLevelValues addHierarchyLevelValues(
        com.stpl.app.model.HierarchyLevelValues hierarchyLevelValues)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().addHierarchyLevelValues(hierarchyLevelValues);
    }

    /**
    * Creates a new hierarchy level values with the primary key. Does not add the hierarchy level values to the database.
    *
    * @param hierarchyLevelValuesSid the primary key for the new hierarchy level values
    * @return the new hierarchy level values
    */
    public static com.stpl.app.model.HierarchyLevelValues createHierarchyLevelValues(
        int hierarchyLevelValuesSid) {
        return getService().createHierarchyLevelValues(hierarchyLevelValuesSid);
    }

    /**
    * Deletes the hierarchy level values with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param hierarchyLevelValuesSid the primary key of the hierarchy level values
    * @return the hierarchy level values that was removed
    * @throws PortalException if a hierarchy level values with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HierarchyLevelValues deleteHierarchyLevelValues(
        int hierarchyLevelValuesSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteHierarchyLevelValues(hierarchyLevelValuesSid);
    }

    /**
    * Deletes the hierarchy level values from the database. Also notifies the appropriate model listeners.
    *
    * @param hierarchyLevelValues the hierarchy level values
    * @return the hierarchy level values that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HierarchyLevelValues deleteHierarchyLevelValues(
        com.stpl.app.model.HierarchyLevelValues hierarchyLevelValues)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteHierarchyLevelValues(hierarchyLevelValues);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HierarchyLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HierarchyLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.stpl.app.model.HierarchyLevelValues fetchHierarchyLevelValues(
        int hierarchyLevelValuesSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().fetchHierarchyLevelValues(hierarchyLevelValuesSid);
    }

    /**
    * Returns the hierarchy level values with the primary key.
    *
    * @param hierarchyLevelValuesSid the primary key of the hierarchy level values
    * @return the hierarchy level values
    * @throws PortalException if a hierarchy level values with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HierarchyLevelValues getHierarchyLevelValues(
        int hierarchyLevelValuesSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getHierarchyLevelValues(hierarchyLevelValuesSid);
    }

    public static com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the hierarchy level valueses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HierarchyLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hierarchy level valueses
    * @param end the upper bound of the range of hierarchy level valueses (not inclusive)
    * @return the range of hierarchy level valueses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.HierarchyLevelValues> getHierarchyLevelValueses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getHierarchyLevelValueses(start, end);
    }

    /**
    * Returns the number of hierarchy level valueses.
    *
    * @return the number of hierarchy level valueses
    * @throws SystemException if a system exception occurred
    */
    public static int getHierarchyLevelValuesesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getHierarchyLevelValuesesCount();
    }

    /**
    * Updates the hierarchy level values in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param hierarchyLevelValues the hierarchy level values
    * @return the hierarchy level values that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HierarchyLevelValues updateHierarchyLevelValues(
        com.stpl.app.model.HierarchyLevelValues hierarchyLevelValues)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().updateHierarchyLevelValues(hierarchyLevelValues);
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

    public static void clearService() {
        _service = null;
    }

    public static HierarchyLevelValuesLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    HierarchyLevelValuesLocalService.class.getName());

            if (invokableLocalService instanceof HierarchyLevelValuesLocalService) {
                _service = (HierarchyLevelValuesLocalService) invokableLocalService;
            } else {
                _service = new HierarchyLevelValuesLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(HierarchyLevelValuesLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(HierarchyLevelValuesLocalService service) {
    }
}
