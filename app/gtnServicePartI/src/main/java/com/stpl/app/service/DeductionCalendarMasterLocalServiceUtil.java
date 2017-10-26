package com.stpl.app.service;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for DeductionCalendarMaster. This utility wraps
 * {@link com.stpl.app.service.impl.DeductionCalendarMasterLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see DeductionCalendarMasterLocalService
 * @see com.stpl.app.service.base.DeductionCalendarMasterLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.DeductionCalendarMasterLocalServiceImpl
 * @generated
 */
public class DeductionCalendarMasterLocalServiceUtil {
    private static DeductionCalendarMasterLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.DeductionCalendarMasterLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the deduction calendar master to the database. Also notifies the appropriate model listeners.
    *
    * @param deductionCalendarMaster the deduction calendar master
    * @return the deduction calendar master that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.DeductionCalendarMaster addDeductionCalendarMaster(
        com.stpl.app.model.DeductionCalendarMaster deductionCalendarMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().addDeductionCalendarMaster(deductionCalendarMaster);
    }

    /**
    * Creates a new deduction calendar master with the primary key. Does not add the deduction calendar master to the database.
    *
    * @param deductionCalendarMasterSid the primary key for the new deduction calendar master
    * @return the new deduction calendar master
    */
    public static com.stpl.app.model.DeductionCalendarMaster createDeductionCalendarMaster(
        int deductionCalendarMasterSid) {
        return getService()
                   .createDeductionCalendarMaster(deductionCalendarMasterSid);
    }

    /**
    * Deletes the deduction calendar master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param deductionCalendarMasterSid the primary key of the deduction calendar master
    * @return the deduction calendar master that was removed
    * @throws PortalException if a deduction calendar master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.DeductionCalendarMaster deleteDeductionCalendarMaster(
        int deductionCalendarMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService()
                   .deleteDeductionCalendarMaster(deductionCalendarMasterSid);
    }

    /**
    * Deletes the deduction calendar master from the database. Also notifies the appropriate model listeners.
    *
    * @param deductionCalendarMaster the deduction calendar master
    * @return the deduction calendar master that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.DeductionCalendarMaster deleteDeductionCalendarMaster(
        com.stpl.app.model.DeductionCalendarMaster deductionCalendarMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService()
                   .deleteDeductionCalendarMaster(deductionCalendarMaster);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionCalendarMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionCalendarMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.stpl.app.model.DeductionCalendarMaster fetchDeductionCalendarMaster(
        int deductionCalendarMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService()
                   .fetchDeductionCalendarMaster(deductionCalendarMasterSid);
    }

    /**
    * Returns the deduction calendar master with the primary key.
    *
    * @param deductionCalendarMasterSid the primary key of the deduction calendar master
    * @return the deduction calendar master
    * @throws PortalException if a deduction calendar master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.DeductionCalendarMaster getDeductionCalendarMaster(
        int deductionCalendarMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService()
                   .getDeductionCalendarMaster(deductionCalendarMasterSid);
    }

    public static com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the deduction calendar masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionCalendarMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of deduction calendar masters
    * @param end the upper bound of the range of deduction calendar masters (not inclusive)
    * @return the range of deduction calendar masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.DeductionCalendarMaster> getDeductionCalendarMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getDeductionCalendarMasters(start, end);
    }

    /**
    * Returns the number of deduction calendar masters.
    *
    * @return the number of deduction calendar masters
    * @throws SystemException if a system exception occurred
    */
    public static int getDeductionCalendarMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getDeductionCalendarMastersCount();
    }

    /**
    * Updates the deduction calendar master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param deductionCalendarMaster the deduction calendar master
    * @return the deduction calendar master that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.DeductionCalendarMaster updateDeductionCalendarMaster(
        com.stpl.app.model.DeductionCalendarMaster deductionCalendarMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService()
                   .updateDeductionCalendarMaster(deductionCalendarMaster);
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

    public static DeductionCalendarMasterLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    DeductionCalendarMasterLocalService.class.getName());

            if (invokableLocalService instanceof DeductionCalendarMasterLocalService) {
                _service = (DeductionCalendarMasterLocalService) invokableLocalService;
            } else {
                _service = new DeductionCalendarMasterLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(DeductionCalendarMasterLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(DeductionCalendarMasterLocalService service) {
    }
}
