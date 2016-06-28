package com.stpl.app.service;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for HistHierarchyDefinition. This utility wraps
 * {@link com.stpl.app.service.impl.HistHierarchyDefinitionLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see HistHierarchyDefinitionLocalService
 * @see com.stpl.app.service.base.HistHierarchyDefinitionLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.HistHierarchyDefinitionLocalServiceImpl
 * @generated
 */
public class HistHierarchyDefinitionLocalServiceUtil {
    private static HistHierarchyDefinitionLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.HistHierarchyDefinitionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the hist hierarchy definition to the database. Also notifies the appropriate model listeners.
    *
    * @param histHierarchyDefinition the hist hierarchy definition
    * @return the hist hierarchy definition that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HistHierarchyDefinition addHistHierarchyDefinition(
        com.stpl.app.model.HistHierarchyDefinition histHierarchyDefinition)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().addHistHierarchyDefinition(histHierarchyDefinition);
    }

    /**
    * Creates a new hist hierarchy definition with the primary key. Does not add the hist hierarchy definition to the database.
    *
    * @param histHierarchyDefinitionPK the primary key for the new hist hierarchy definition
    * @return the new hist hierarchy definition
    */
    public static com.stpl.app.model.HistHierarchyDefinition createHistHierarchyDefinition(
        com.stpl.app.service.persistence.HistHierarchyDefinitionPK histHierarchyDefinitionPK) {
        return getService()
                   .createHistHierarchyDefinition(histHierarchyDefinitionPK);
    }

    /**
    * Deletes the hist hierarchy definition with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param histHierarchyDefinitionPK the primary key of the hist hierarchy definition
    * @return the hist hierarchy definition that was removed
    * @throws PortalException if a hist hierarchy definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HistHierarchyDefinition deleteHistHierarchyDefinition(
        com.stpl.app.service.persistence.HistHierarchyDefinitionPK histHierarchyDefinitionPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService()
                   .deleteHistHierarchyDefinition(histHierarchyDefinitionPK);
    }

    /**
    * Deletes the hist hierarchy definition from the database. Also notifies the appropriate model listeners.
    *
    * @param histHierarchyDefinition the hist hierarchy definition
    * @return the hist hierarchy definition that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HistHierarchyDefinition deleteHistHierarchyDefinition(
        com.stpl.app.model.HistHierarchyDefinition histHierarchyDefinition)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService()
                   .deleteHistHierarchyDefinition(histHierarchyDefinition);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistHierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistHierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.stpl.app.model.HistHierarchyDefinition fetchHistHierarchyDefinition(
        com.stpl.app.service.persistence.HistHierarchyDefinitionPK histHierarchyDefinitionPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService()
                   .fetchHistHierarchyDefinition(histHierarchyDefinitionPK);
    }

    /**
    * Returns the hist hierarchy definition with the primary key.
    *
    * @param histHierarchyDefinitionPK the primary key of the hist hierarchy definition
    * @return the hist hierarchy definition
    * @throws PortalException if a hist hierarchy definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HistHierarchyDefinition getHistHierarchyDefinition(
        com.stpl.app.service.persistence.HistHierarchyDefinitionPK histHierarchyDefinitionPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getHistHierarchyDefinition(histHierarchyDefinitionPK);
    }

    public static com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the hist hierarchy definitions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistHierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hist hierarchy definitions
    * @param end the upper bound of the range of hist hierarchy definitions (not inclusive)
    * @return the range of hist hierarchy definitions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.HistHierarchyDefinition> getHistHierarchyDefinitions(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getHistHierarchyDefinitions(start, end);
    }

    /**
    * Returns the number of hist hierarchy definitions.
    *
    * @return the number of hist hierarchy definitions
    * @throws SystemException if a system exception occurred
    */
    public static int getHistHierarchyDefinitionsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getHistHierarchyDefinitionsCount();
    }

    /**
    * Updates the hist hierarchy definition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param histHierarchyDefinition the hist hierarchy definition
    * @return the hist hierarchy definition that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HistHierarchyDefinition updateHistHierarchyDefinition(
        com.stpl.app.model.HistHierarchyDefinition histHierarchyDefinition)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService()
                   .updateHistHierarchyDefinition(histHierarchyDefinition);
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

    public static HistHierarchyDefinitionLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    HistHierarchyDefinitionLocalService.class.getName());

            if (invokableLocalService instanceof HistHierarchyDefinitionLocalService) {
                _service = (HistHierarchyDefinitionLocalService) invokableLocalService;
            } else {
                _service = new HistHierarchyDefinitionLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(HistHierarchyDefinitionLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(HistHierarchyDefinitionLocalService service) {
    }
}
