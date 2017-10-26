package com.stpl.app.service;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for StSelectionTable. This utility wraps
 * {@link com.stpl.app.service.impl.StSelectionTableLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see StSelectionTableLocalService
 * @see com.stpl.app.service.base.StSelectionTableLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.StSelectionTableLocalServiceImpl
 * @generated
 */
public class StSelectionTableLocalServiceUtil {
    private static StSelectionTableLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.StSelectionTableLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the st selection table to the database. Also notifies the appropriate model listeners.
    *
    * @param stSelectionTable the st selection table
    * @return the st selection table that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StSelectionTable addStSelectionTable(
        com.stpl.app.model.StSelectionTable stSelectionTable)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().addStSelectionTable(stSelectionTable);
    }

    /**
    * Creates a new st selection table with the primary key. Does not add the st selection table to the database.
    *
    * @param stSelectionTablePK the primary key for the new st selection table
    * @return the new st selection table
    */
    public static com.stpl.app.model.StSelectionTable createStSelectionTable(
        com.stpl.app.service.persistence.StSelectionTablePK stSelectionTablePK) {
        return getService().createStSelectionTable(stSelectionTablePK);
    }

    /**
    * Deletes the st selection table with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stSelectionTablePK the primary key of the st selection table
    * @return the st selection table that was removed
    * @throws PortalException if a st selection table with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StSelectionTable deleteStSelectionTable(
        com.stpl.app.service.persistence.StSelectionTablePK stSelectionTablePK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteStSelectionTable(stSelectionTablePK);
    }

    /**
    * Deletes the st selection table from the database. Also notifies the appropriate model listeners.
    *
    * @param stSelectionTable the st selection table
    * @return the st selection table that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StSelectionTable deleteStSelectionTable(
        com.stpl.app.model.StSelectionTable stSelectionTable)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteStSelectionTable(stSelectionTable);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StSelectionTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StSelectionTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.stpl.app.model.StSelectionTable fetchStSelectionTable(
        com.stpl.app.service.persistence.StSelectionTablePK stSelectionTablePK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().fetchStSelectionTable(stSelectionTablePK);
    }

    /**
    * Returns the st selection table with the primary key.
    *
    * @param stSelectionTablePK the primary key of the st selection table
    * @return the st selection table
    * @throws PortalException if a st selection table with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StSelectionTable getStSelectionTable(
        com.stpl.app.service.persistence.StSelectionTablePK stSelectionTablePK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getStSelectionTable(stSelectionTablePK);
    }

    public static com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the st selection tables.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StSelectionTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st selection tables
    * @param end the upper bound of the range of st selection tables (not inclusive)
    * @return the range of st selection tables
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StSelectionTable> getStSelectionTables(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getStSelectionTables(start, end);
    }

    /**
    * Returns the number of st selection tables.
    *
    * @return the number of st selection tables
    * @throws SystemException if a system exception occurred
    */
    public static int getStSelectionTablesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getStSelectionTablesCount();
    }

    /**
    * Updates the st selection table in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param stSelectionTable the st selection table
    * @return the st selection table that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StSelectionTable updateStSelectionTable(
        com.stpl.app.model.StSelectionTable stSelectionTable)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().updateStSelectionTable(stSelectionTable);
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

    public static StSelectionTableLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    StSelectionTableLocalService.class.getName());

            if (invokableLocalService instanceof StSelectionTableLocalService) {
                _service = (StSelectionTableLocalService) invokableLocalService;
            } else {
                _service = new StSelectionTableLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(StSelectionTableLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(StSelectionTableLocalService service) {
    }
}
