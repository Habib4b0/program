package com.stpl.app.service;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for StNewNdc. This utility wraps
 * {@link com.stpl.app.service.impl.StNewNdcLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see StNewNdcLocalService
 * @see com.stpl.app.service.base.StNewNdcLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.StNewNdcLocalServiceImpl
 * @generated
 */
public class StNewNdcLocalServiceUtil {
    private static StNewNdcLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.StNewNdcLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the st new ndc to the database. Also notifies the appropriate model listeners.
    *
    * @param stNewNdc the st new ndc
    * @return the st new ndc that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StNewNdc addStNewNdc(
        com.stpl.app.model.StNewNdc stNewNdc)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().addStNewNdc(stNewNdc);
    }

    /**
    * Creates a new st new ndc with the primary key. Does not add the st new ndc to the database.
    *
    * @param stNewNdcPK the primary key for the new st new ndc
    * @return the new st new ndc
    */
    public static com.stpl.app.model.StNewNdc createStNewNdc(
        com.stpl.app.service.persistence.StNewNdcPK stNewNdcPK) {
        return getService().createStNewNdc(stNewNdcPK);
    }

    /**
    * Deletes the st new ndc with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stNewNdcPK the primary key of the st new ndc
    * @return the st new ndc that was removed
    * @throws PortalException if a st new ndc with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StNewNdc deleteStNewNdc(
        com.stpl.app.service.persistence.StNewNdcPK stNewNdcPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteStNewNdc(stNewNdcPK);
    }

    /**
    * Deletes the st new ndc from the database. Also notifies the appropriate model listeners.
    *
    * @param stNewNdc the st new ndc
    * @return the st new ndc that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StNewNdc deleteStNewNdc(
        com.stpl.app.model.StNewNdc stNewNdc)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteStNewNdc(stNewNdc);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.stpl.app.model.StNewNdc fetchStNewNdc(
        com.stpl.app.service.persistence.StNewNdcPK stNewNdcPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().fetchStNewNdc(stNewNdcPK);
    }

    /**
    * Returns the st new ndc with the primary key.
    *
    * @param stNewNdcPK the primary key of the st new ndc
    * @return the st new ndc
    * @throws PortalException if a st new ndc with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StNewNdc getStNewNdc(
        com.stpl.app.service.persistence.StNewNdcPK stNewNdcPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getStNewNdc(stNewNdcPK);
    }

    public static com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the st new ndcs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st new ndcs
    * @param end the upper bound of the range of st new ndcs (not inclusive)
    * @return the range of st new ndcs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StNewNdc> getStNewNdcs(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getStNewNdcs(start, end);
    }

    /**
    * Returns the number of st new ndcs.
    *
    * @return the number of st new ndcs
    * @throws SystemException if a system exception occurred
    */
    public static int getStNewNdcsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getStNewNdcsCount();
    }

    /**
    * Updates the st new ndc in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param stNewNdc the st new ndc
    * @return the st new ndc that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StNewNdc updateStNewNdc(
        com.stpl.app.model.StNewNdc stNewNdc)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().updateStNewNdc(stNewNdc);
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

    public static StNewNdcLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    StNewNdcLocalService.class.getName());

            if (invokableLocalService instanceof StNewNdcLocalService) {
                _service = (StNewNdcLocalService) invokableLocalService;
            } else {
                _service = new StNewNdcLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(StNewNdcLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(StNewNdcLocalService service) {
    }
}
