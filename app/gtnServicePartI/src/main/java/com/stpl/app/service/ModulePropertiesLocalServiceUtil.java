package com.stpl.app.service;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for ModuleProperties. This utility wraps
 * {@link com.stpl.app.service.impl.ModulePropertiesLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see ModulePropertiesLocalService
 * @see com.stpl.app.service.base.ModulePropertiesLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.ModulePropertiesLocalServiceImpl
 * @generated
 */
public class ModulePropertiesLocalServiceUtil {
    private static ModulePropertiesLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.ModulePropertiesLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the module properties to the database. Also notifies the appropriate model listeners.
    *
    * @param moduleProperties the module properties
    * @return the module properties that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ModuleProperties addModuleProperties(
        com.stpl.app.model.ModuleProperties moduleProperties)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().addModuleProperties(moduleProperties);
    }

    /**
    * Creates a new module properties with the primary key. Does not add the module properties to the database.
    *
    * @param modulePropertySid the primary key for the new module properties
    * @return the new module properties
    */
    public static com.stpl.app.model.ModuleProperties createModuleProperties(
        int modulePropertySid) {
        return getService().createModuleProperties(modulePropertySid);
    }

    /**
    * Deletes the module properties with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param modulePropertySid the primary key of the module properties
    * @return the module properties that was removed
    * @throws PortalException if a module properties with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ModuleProperties deleteModuleProperties(
        int modulePropertySid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteModuleProperties(modulePropertySid);
    }

    /**
    * Deletes the module properties from the database. Also notifies the appropriate model listeners.
    *
    * @param moduleProperties the module properties
    * @return the module properties that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ModuleProperties deleteModuleProperties(
        com.stpl.app.model.ModuleProperties moduleProperties)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteModuleProperties(moduleProperties);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ModulePropertiesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ModulePropertiesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.stpl.app.model.ModuleProperties fetchModuleProperties(
        int modulePropertySid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().fetchModuleProperties(modulePropertySid);
    }

    /**
    * Returns the module properties with the primary key.
    *
    * @param modulePropertySid the primary key of the module properties
    * @return the module properties
    * @throws PortalException if a module properties with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ModuleProperties getModuleProperties(
        int modulePropertySid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getModuleProperties(modulePropertySid);
    }

    public static com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the module propertieses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ModulePropertiesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of module propertieses
    * @param end the upper bound of the range of module propertieses (not inclusive)
    * @return the range of module propertieses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ModuleProperties> getModulePropertieses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getModulePropertieses(start, end);
    }

    /**
    * Returns the number of module propertieses.
    *
    * @return the number of module propertieses
    * @throws SystemException if a system exception occurred
    */
    public static int getModulePropertiesesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getModulePropertiesesCount();
    }

    /**
    * Updates the module properties in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param moduleProperties the module properties
    * @return the module properties that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ModuleProperties updateModuleProperties(
        com.stpl.app.model.ModuleProperties moduleProperties)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().updateModuleProperties(moduleProperties);
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

    public static ModulePropertiesLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ModulePropertiesLocalService.class.getName());

            if (invokableLocalService instanceof ModulePropertiesLocalService) {
                _service = (ModulePropertiesLocalService) invokableLocalService;
            } else {
                _service = new ModulePropertiesLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(ModulePropertiesLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(ModulePropertiesLocalService service) {
    }
}
