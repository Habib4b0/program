package com.stpl.app.service;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for StMSupplementalDiscMaster. This utility wraps
 * {@link com.stpl.app.service.impl.StMSupplementalDiscMasterLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see StMSupplementalDiscMasterLocalService
 * @see com.stpl.app.service.base.StMSupplementalDiscMasterLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.StMSupplementalDiscMasterLocalServiceImpl
 * @generated
 */
public class StMSupplementalDiscMasterLocalServiceUtil {
    private static StMSupplementalDiscMasterLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.StMSupplementalDiscMasterLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the st m supplemental disc master to the database. Also notifies the appropriate model listeners.
    *
    * @param stMSupplementalDiscMaster the st m supplemental disc master
    * @return the st m supplemental disc master that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StMSupplementalDiscMaster addStMSupplementalDiscMaster(
        com.stpl.app.model.StMSupplementalDiscMaster stMSupplementalDiscMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService()
                   .addStMSupplementalDiscMaster(stMSupplementalDiscMaster);
    }

    /**
    * Creates a new st m supplemental disc master with the primary key. Does not add the st m supplemental disc master to the database.
    *
    * @param stMSupplementalDiscMasterPK the primary key for the new st m supplemental disc master
    * @return the new st m supplemental disc master
    */
    public static com.stpl.app.model.StMSupplementalDiscMaster createStMSupplementalDiscMaster(
        com.stpl.app.service.persistence.StMSupplementalDiscMasterPK stMSupplementalDiscMasterPK) {
        return getService()
                   .createStMSupplementalDiscMaster(stMSupplementalDiscMasterPK);
    }

    /**
    * Deletes the st m supplemental disc master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stMSupplementalDiscMasterPK the primary key of the st m supplemental disc master
    * @return the st m supplemental disc master that was removed
    * @throws PortalException if a st m supplemental disc master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StMSupplementalDiscMaster deleteStMSupplementalDiscMaster(
        com.stpl.app.service.persistence.StMSupplementalDiscMasterPK stMSupplementalDiscMasterPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService()
                   .deleteStMSupplementalDiscMaster(stMSupplementalDiscMasterPK);
    }

    /**
    * Deletes the st m supplemental disc master from the database. Also notifies the appropriate model listeners.
    *
    * @param stMSupplementalDiscMaster the st m supplemental disc master
    * @return the st m supplemental disc master that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StMSupplementalDiscMaster deleteStMSupplementalDiscMaster(
        com.stpl.app.model.StMSupplementalDiscMaster stMSupplementalDiscMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService()
                   .deleteStMSupplementalDiscMaster(stMSupplementalDiscMaster);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StMSupplementalDiscMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StMSupplementalDiscMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.stpl.app.model.StMSupplementalDiscMaster fetchStMSupplementalDiscMaster(
        com.stpl.app.service.persistence.StMSupplementalDiscMasterPK stMSupplementalDiscMasterPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService()
                   .fetchStMSupplementalDiscMaster(stMSupplementalDiscMasterPK);
    }

    /**
    * Returns the st m supplemental disc master with the primary key.
    *
    * @param stMSupplementalDiscMasterPK the primary key of the st m supplemental disc master
    * @return the st m supplemental disc master
    * @throws PortalException if a st m supplemental disc master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StMSupplementalDiscMaster getStMSupplementalDiscMaster(
        com.stpl.app.service.persistence.StMSupplementalDiscMasterPK stMSupplementalDiscMasterPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService()
                   .getStMSupplementalDiscMaster(stMSupplementalDiscMasterPK);
    }

    public static com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the st m supplemental disc masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StMSupplementalDiscMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st m supplemental disc masters
    * @param end the upper bound of the range of st m supplemental disc masters (not inclusive)
    * @return the range of st m supplemental disc masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StMSupplementalDiscMaster> getStMSupplementalDiscMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getStMSupplementalDiscMasters(start, end);
    }

    /**
    * Returns the number of st m supplemental disc masters.
    *
    * @return the number of st m supplemental disc masters
    * @throws SystemException if a system exception occurred
    */
    public static int getStMSupplementalDiscMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getStMSupplementalDiscMastersCount();
    }

    /**
    * Updates the st m supplemental disc master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param stMSupplementalDiscMaster the st m supplemental disc master
    * @return the st m supplemental disc master that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StMSupplementalDiscMaster updateStMSupplementalDiscMaster(
        com.stpl.app.model.StMSupplementalDiscMaster stMSupplementalDiscMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService()
                   .updateStMSupplementalDiscMaster(stMSupplementalDiscMaster);
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

    public static StMSupplementalDiscMasterLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    StMSupplementalDiscMasterLocalService.class.getName());

            if (invokableLocalService instanceof StMSupplementalDiscMasterLocalService) {
                _service = (StMSupplementalDiscMasterLocalService) invokableLocalService;
            } else {
                _service = new StMSupplementalDiscMasterLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(StMSupplementalDiscMasterLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(StMSupplementalDiscMasterLocalService service) {
    }
}
