package com.stpl.app.service;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for ChActualSales. This utility wraps
 * {@link com.stpl.app.service.impl.ChActualSalesLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see ChActualSalesLocalService
 * @see com.stpl.app.service.base.ChActualSalesLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.ChActualSalesLocalServiceImpl
 * @generated
 */
public class ChActualSalesLocalServiceUtil {
    private static ChActualSalesLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.ChActualSalesLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the ch actual sales to the database. Also notifies the appropriate model listeners.
    *
    * @param chActualSales the ch actual sales
    * @return the ch actual sales that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ChActualSales addChActualSales(
        com.stpl.app.model.ChActualSales chActualSales)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().addChActualSales(chActualSales);
    }

    /**
    * Creates a new ch actual sales with the primary key. Does not add the ch actual sales to the database.
    *
    * @param chActualSalesPK the primary key for the new ch actual sales
    * @return the new ch actual sales
    */
    public static com.stpl.app.model.ChActualSales createChActualSales(
        com.stpl.app.service.persistence.ChActualSalesPK chActualSalesPK) {
        return getService().createChActualSales(chActualSalesPK);
    }

    /**
    * Deletes the ch actual sales with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param chActualSalesPK the primary key of the ch actual sales
    * @return the ch actual sales that was removed
    * @throws PortalException if a ch actual sales with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ChActualSales deleteChActualSales(
        com.stpl.app.service.persistence.ChActualSalesPK chActualSalesPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteChActualSales(chActualSalesPK);
    }

    /**
    * Deletes the ch actual sales from the database. Also notifies the appropriate model listeners.
    *
    * @param chActualSales the ch actual sales
    * @return the ch actual sales that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ChActualSales deleteChActualSales(
        com.stpl.app.model.ChActualSales chActualSales)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteChActualSales(chActualSales);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChActualSalesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChActualSalesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.stpl.app.model.ChActualSales fetchChActualSales(
        com.stpl.app.service.persistence.ChActualSalesPK chActualSalesPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().fetchChActualSales(chActualSalesPK);
    }

    /**
    * Returns the ch actual sales with the primary key.
    *
    * @param chActualSalesPK the primary key of the ch actual sales
    * @return the ch actual sales
    * @throws PortalException if a ch actual sales with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ChActualSales getChActualSales(
        com.stpl.app.service.persistence.ChActualSalesPK chActualSalesPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getChActualSales(chActualSalesPK);
    }

    public static com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ch actual saleses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChActualSalesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ch actual saleses
    * @param end the upper bound of the range of ch actual saleses (not inclusive)
    * @return the range of ch actual saleses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ChActualSales> getChActualSaleses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getChActualSaleses(start, end);
    }

    /**
    * Returns the number of ch actual saleses.
    *
    * @return the number of ch actual saleses
    * @throws SystemException if a system exception occurred
    */
    public static int getChActualSalesesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getChActualSalesesCount();
    }

    /**
    * Updates the ch actual sales in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param chActualSales the ch actual sales
    * @return the ch actual sales that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ChActualSales updateChActualSales(
        com.stpl.app.model.ChActualSales chActualSales)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().updateChActualSales(chActualSales);
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

    public static ChActualSalesLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ChActualSalesLocalService.class.getName());

            if (invokableLocalService instanceof ChActualSalesLocalService) {
                _service = (ChActualSalesLocalService) invokableLocalService;
            } else {
                _service = new ChActualSalesLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(ChActualSalesLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(ChActualSalesLocalService service) {
    }
}
