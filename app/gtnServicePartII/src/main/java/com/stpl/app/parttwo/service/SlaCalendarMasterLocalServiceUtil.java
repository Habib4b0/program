package com.stpl.app.parttwo.service;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for SlaCalendarMaster. This utility wraps
 * {@link com.stpl.app.parttwo.service.impl.SlaCalendarMasterLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see SlaCalendarMasterLocalService
 * @see com.stpl.app.parttwo.service.base.SlaCalendarMasterLocalServiceBaseImpl
 * @see com.stpl.app.parttwo.service.impl.SlaCalendarMasterLocalServiceImpl
 * @generated
 */
public class SlaCalendarMasterLocalServiceUtil {
    private static SlaCalendarMasterLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.stpl.app.parttwo.service.impl.SlaCalendarMasterLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the sla calendar master to the database. Also notifies the appropriate model listeners.
    *
    * @param slaCalendarMaster the sla calendar master
    * @return the sla calendar master that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.SlaCalendarMaster addSlaCalendarMaster(
        com.stpl.app.parttwo.model.SlaCalendarMaster slaCalendarMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().addSlaCalendarMaster(slaCalendarMaster);
    }

    /**
    * Creates a new sla calendar master with the primary key. Does not add the sla calendar master to the database.
    *
    * @param slaCalendarMasterSid the primary key for the new sla calendar master
    * @return the new sla calendar master
    */
    public static com.stpl.app.parttwo.model.SlaCalendarMaster createSlaCalendarMaster(
        int slaCalendarMasterSid) {
        return getService().createSlaCalendarMaster(slaCalendarMasterSid);
    }

    /**
    * Deletes the sla calendar master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param slaCalendarMasterSid the primary key of the sla calendar master
    * @return the sla calendar master that was removed
    * @throws PortalException if a sla calendar master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.SlaCalendarMaster deleteSlaCalendarMaster(
        int slaCalendarMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteSlaCalendarMaster(slaCalendarMasterSid);
    }

    /**
    * Deletes the sla calendar master from the database. Also notifies the appropriate model listeners.
    *
    * @param slaCalendarMaster the sla calendar master
    * @return the sla calendar master that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.SlaCalendarMaster deleteSlaCalendarMaster(
        com.stpl.app.parttwo.model.SlaCalendarMaster slaCalendarMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteSlaCalendarMaster(slaCalendarMaster);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.SlaCalendarMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.SlaCalendarMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.stpl.app.parttwo.model.SlaCalendarMaster fetchSlaCalendarMaster(
        int slaCalendarMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().fetchSlaCalendarMaster(slaCalendarMasterSid);
    }

    /**
    * Returns the sla calendar master with the primary key.
    *
    * @param slaCalendarMasterSid the primary key of the sla calendar master
    * @return the sla calendar master
    * @throws PortalException if a sla calendar master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.SlaCalendarMaster getSlaCalendarMaster(
        int slaCalendarMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getSlaCalendarMaster(slaCalendarMasterSid);
    }

    public static com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the sla calendar masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.SlaCalendarMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of sla calendar masters
    * @param end the upper bound of the range of sla calendar masters (not inclusive)
    * @return the range of sla calendar masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.SlaCalendarMaster> getSlaCalendarMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getSlaCalendarMasters(start, end);
    }

    /**
    * Returns the number of sla calendar masters.
    *
    * @return the number of sla calendar masters
    * @throws SystemException if a system exception occurred
    */
    public static int getSlaCalendarMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getSlaCalendarMastersCount();
    }

    /**
    * Updates the sla calendar master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param slaCalendarMaster the sla calendar master
    * @return the sla calendar master that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.SlaCalendarMaster updateSlaCalendarMaster(
        com.stpl.app.parttwo.model.SlaCalendarMaster slaCalendarMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().updateSlaCalendarMaster(slaCalendarMaster);
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

    public static SlaCalendarMasterLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    SlaCalendarMasterLocalService.class.getName());

            if (invokableLocalService instanceof SlaCalendarMasterLocalService) {
                _service = (SlaCalendarMasterLocalService) invokableLocalService;
            } else {
                _service = new SlaCalendarMasterLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(SlaCalendarMasterLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(SlaCalendarMasterLocalService service) {
    }
}
