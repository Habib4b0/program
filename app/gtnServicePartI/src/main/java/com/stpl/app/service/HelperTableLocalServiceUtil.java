package com.stpl.app.service;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.InvokableLocalService;
import org.jboss.logging.Logger;

/**
 * Provides the local service utility for HelperTable. This utility wraps
 * {@link com.stpl.app.service.impl.HelperTableLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see HelperTableLocalService
 * @see com.stpl.app.service.base.HelperTableLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.HelperTableLocalServiceImpl
 * @generated
 */
public class HelperTableLocalServiceUtil {
    private static HelperTableLocalService _service;
    private static final Logger LOGGER = Logger.getLogger(HelperTableLocalServiceUtil.class);
    
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.HelperTableLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the helper table to the database. Also notifies the appropriate model listeners.
    *
    * @param helperTable the helper table
    * @return the helper table that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HelperTable addHelperTable(
        com.stpl.app.model.HelperTable helperTable)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().addHelperTable(helperTable);
    }

    /**
    * Creates a new helper table with the primary key. Does not add the helper table to the database.
    *
    * @param helperTableSid the primary key for the new helper table
    * @return the new helper table
    */
    public static com.stpl.app.model.HelperTable createHelperTable(
        int helperTableSid) {
        return getService().createHelperTable(helperTableSid);
    }

    /**
    * Deletes the helper table with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param helperTableSid the primary key of the helper table
    * @return the helper table that was removed
    * @throws PortalException if a helper table with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HelperTable deleteHelperTable(
        int helperTableSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteHelperTable(helperTableSid);
    }

    /**
    * Deletes the helper table from the database. Also notifies the appropriate model listeners.
    *
    * @param helperTable the helper table
    * @return the helper table that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HelperTable deleteHelperTable(
        com.stpl.app.model.HelperTable helperTable)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteHelperTable(helperTable);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HelperTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HelperTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.stpl.app.model.HelperTable fetchHelperTable(
        int helperTableSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().fetchHelperTable(helperTableSid);
    }

    /**
    * Returns the helper table with the primary key.
    *
    * @param helperTableSid the primary key of the helper table
    * @return the helper table
    * @throws PortalException if a helper table with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HelperTable getHelperTable(
        int helperTableSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getHelperTable(helperTableSid);
    }

    public static com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the helper tables.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HelperTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of helper tables
    * @param end the upper bound of the range of helper tables (not inclusive)
    * @return the range of helper tables
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.HelperTable> getHelperTables(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getHelperTables(start, end);
    }

    /**
    * Returns the number of helper tables.
    *
    * @return the number of helper tables
    * @throws SystemException if a system exception occurred
    */
    public static int getHelperTablesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getHelperTablesCount();
    }

    /**
    * Updates the helper table in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param helperTable the helper table
    * @return the helper table that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HelperTable updateHelperTable(
        com.stpl.app.model.HelperTable helperTable)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().updateHelperTable(helperTable);
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

    public static java.util.List<com.stpl.app.model.HelperTable> findByHelperTableDetails(
        java.lang.String listName)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().findByHelperTableDetails(listName);
    }

    /**
    * Custom query to select records from data base
    *
    * @param query - Framed SQL Query
    * @return List<Object[]> result list
    */
    public static java.util.List executeSelectQuery(java.lang.String query) {
        LOGGER.debug(query);
        return getService().executeSelectQuery(query);
    }

    /**
    * Updates sql statement
    *
    * @param query
    */
    public static void executeUpdateQuery(java.lang.String query) {
        LOGGER.debug(query);
        getService().executeUpdateQuery(query);
    }

    public static int executeUpdateQueryCount(java.lang.String query) {
        LOGGER.debug(query);
        return getService().executeUpdateQueryCount(query);
    }

    public static void clearService() {
        _service = null;
    }

    public static HelperTableLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    HelperTableLocalService.class.getName());

            if (invokableLocalService instanceof HelperTableLocalService) {
                _service = (HelperTableLocalService) invokableLocalService;
            } else {
                _service = new HelperTableLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(HelperTableLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(HelperTableLocalService service) {
    }
}
