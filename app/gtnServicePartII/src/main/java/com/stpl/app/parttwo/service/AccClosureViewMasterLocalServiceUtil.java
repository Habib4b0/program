package com.stpl.app.parttwo.service;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for AccClosureViewMaster. This utility wraps
 * {@link com.stpl.app.parttwo.service.impl.AccClosureViewMasterLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see AccClosureViewMasterLocalService
 * @see com.stpl.app.parttwo.service.base.AccClosureViewMasterLocalServiceBaseImpl
 * @see com.stpl.app.parttwo.service.impl.AccClosureViewMasterLocalServiceImpl
 * @generated
 */
public class AccClosureViewMasterLocalServiceUtil {
    private static AccClosureViewMasterLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.stpl.app.parttwo.service.impl.AccClosureViewMasterLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the acc closure view master to the database. Also notifies the appropriate model listeners.
    *
    * @param accClosureViewMaster the acc closure view master
    * @return the acc closure view master that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.AccClosureViewMaster addAccClosureViewMaster(
        com.stpl.app.parttwo.model.AccClosureViewMaster accClosureViewMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().addAccClosureViewMaster(accClosureViewMaster);
    }

    /**
    * Creates a new acc closure view master with the primary key. Does not add the acc closure view master to the database.
    *
    * @param accClosureViewMasterSid the primary key for the new acc closure view master
    * @return the new acc closure view master
    */
    public static com.stpl.app.parttwo.model.AccClosureViewMaster createAccClosureViewMaster(
        int accClosureViewMasterSid) {
        return getService().createAccClosureViewMaster(accClosureViewMasterSid);
    }

    /**
    * Deletes the acc closure view master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param accClosureViewMasterSid the primary key of the acc closure view master
    * @return the acc closure view master that was removed
    * @throws PortalException if a acc closure view master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.AccClosureViewMaster deleteAccClosureViewMaster(
        int accClosureViewMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteAccClosureViewMaster(accClosureViewMasterSid);
    }

    /**
    * Deletes the acc closure view master from the database. Also notifies the appropriate model listeners.
    *
    * @param accClosureViewMaster the acc closure view master
    * @return the acc closure view master that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.AccClosureViewMaster deleteAccClosureViewMaster(
        com.stpl.app.parttwo.model.AccClosureViewMaster accClosureViewMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteAccClosureViewMaster(accClosureViewMaster);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AccClosureViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AccClosureViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.stpl.app.parttwo.model.AccClosureViewMaster fetchAccClosureViewMaster(
        int accClosureViewMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().fetchAccClosureViewMaster(accClosureViewMasterSid);
    }

    /**
    * Returns the acc closure view master with the primary key.
    *
    * @param accClosureViewMasterSid the primary key of the acc closure view master
    * @return the acc closure view master
    * @throws PortalException if a acc closure view master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.AccClosureViewMaster getAccClosureViewMaster(
        int accClosureViewMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getAccClosureViewMaster(accClosureViewMasterSid);
    }

    public static com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the acc closure view masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AccClosureViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of acc closure view masters
    * @param end the upper bound of the range of acc closure view masters (not inclusive)
    * @return the range of acc closure view masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.AccClosureViewMaster> getAccClosureViewMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getAccClosureViewMasters(start, end);
    }

    /**
    * Returns the number of acc closure view masters.
    *
    * @return the number of acc closure view masters
    * @throws SystemException if a system exception occurred
    */
    public static int getAccClosureViewMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getAccClosureViewMastersCount();
    }

    /**
    * Updates the acc closure view master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param accClosureViewMaster the acc closure view master
    * @return the acc closure view master that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.AccClosureViewMaster updateAccClosureViewMaster(
        com.stpl.app.parttwo.model.AccClosureViewMaster accClosureViewMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().updateAccClosureViewMaster(accClosureViewMaster);
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

    public static AccClosureViewMasterLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    AccClosureViewMasterLocalService.class.getName());

            if (invokableLocalService instanceof AccClosureViewMasterLocalService) {
                _service = (AccClosureViewMasterLocalService) invokableLocalService;
            } else {
                _service = new AccClosureViewMasterLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(AccClosureViewMasterLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(AccClosureViewMasterLocalService service) {
    }
}
