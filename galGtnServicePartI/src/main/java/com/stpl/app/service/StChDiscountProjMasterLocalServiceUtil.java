package com.stpl.app.service;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for StChDiscountProjMaster. This utility wraps
 * {@link com.stpl.app.service.impl.StChDiscountProjMasterLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see StChDiscountProjMasterLocalService
 * @see com.stpl.app.service.base.StChDiscountProjMasterLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.StChDiscountProjMasterLocalServiceImpl
 * @generated
 */
public class StChDiscountProjMasterLocalServiceUtil {
    private static StChDiscountProjMasterLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.StChDiscountProjMasterLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the st ch discount proj master to the database. Also notifies the appropriate model listeners.
    *
    * @param stChDiscountProjMaster the st ch discount proj master
    * @return the st ch discount proj master that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StChDiscountProjMaster addStChDiscountProjMaster(
        com.stpl.app.model.StChDiscountProjMaster stChDiscountProjMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().addStChDiscountProjMaster(stChDiscountProjMaster);
    }

    /**
    * Creates a new st ch discount proj master with the primary key. Does not add the st ch discount proj master to the database.
    *
    * @param stChDiscountProjMasterPK the primary key for the new st ch discount proj master
    * @return the new st ch discount proj master
    */
    public static com.stpl.app.model.StChDiscountProjMaster createStChDiscountProjMaster(
        com.stpl.app.service.persistence.StChDiscountProjMasterPK stChDiscountProjMasterPK) {
        return getService()
                   .createStChDiscountProjMaster(stChDiscountProjMasterPK);
    }

    /**
    * Deletes the st ch discount proj master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stChDiscountProjMasterPK the primary key of the st ch discount proj master
    * @return the st ch discount proj master that was removed
    * @throws PortalException if a st ch discount proj master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StChDiscountProjMaster deleteStChDiscountProjMaster(
        com.stpl.app.service.persistence.StChDiscountProjMasterPK stChDiscountProjMasterPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService()
                   .deleteStChDiscountProjMaster(stChDiscountProjMasterPK);
    }

    /**
    * Deletes the st ch discount proj master from the database. Also notifies the appropriate model listeners.
    *
    * @param stChDiscountProjMaster the st ch discount proj master
    * @return the st ch discount proj master that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StChDiscountProjMaster deleteStChDiscountProjMaster(
        com.stpl.app.model.StChDiscountProjMaster stChDiscountProjMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteStChDiscountProjMaster(stChDiscountProjMaster);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.stpl.app.model.StChDiscountProjMaster fetchStChDiscountProjMaster(
        com.stpl.app.service.persistence.StChDiscountProjMasterPK stChDiscountProjMasterPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().fetchStChDiscountProjMaster(stChDiscountProjMasterPK);
    }

    /**
    * Returns the st ch discount proj master with the primary key.
    *
    * @param stChDiscountProjMasterPK the primary key of the st ch discount proj master
    * @return the st ch discount proj master
    * @throws PortalException if a st ch discount proj master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StChDiscountProjMaster getStChDiscountProjMaster(
        com.stpl.app.service.persistence.StChDiscountProjMasterPK stChDiscountProjMasterPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getStChDiscountProjMaster(stChDiscountProjMasterPK);
    }

    public static com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the st ch discount proj masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st ch discount proj masters
    * @param end the upper bound of the range of st ch discount proj masters (not inclusive)
    * @return the range of st ch discount proj masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StChDiscountProjMaster> getStChDiscountProjMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getStChDiscountProjMasters(start, end);
    }

    /**
    * Returns the number of st ch discount proj masters.
    *
    * @return the number of st ch discount proj masters
    * @throws SystemException if a system exception occurred
    */
    public static int getStChDiscountProjMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getStChDiscountProjMastersCount();
    }

    /**
    * Updates the st ch discount proj master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param stChDiscountProjMaster the st ch discount proj master
    * @return the st ch discount proj master that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StChDiscountProjMaster updateStChDiscountProjMaster(
        com.stpl.app.model.StChDiscountProjMaster stChDiscountProjMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().updateStChDiscountProjMaster(stChDiscountProjMaster);
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

    public static java.util.List executeQuery(java.lang.String query) {
        return getService().executeQuery(query);
    }

    public static int updateQuery(java.lang.String query) {
        return getService().updateQuery(query);
    }

    public static void clearService() {
        _service = null;
    }

    public static StChDiscountProjMasterLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    StChDiscountProjMasterLocalService.class.getName());

            if (invokableLocalService instanceof StChDiscountProjMasterLocalService) {
                _service = (StChDiscountProjMasterLocalService) invokableLocalService;
            } else {
                _service = new StChDiscountProjMasterLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(StChDiscountProjMasterLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(StChDiscountProjMasterLocalService service) {
    }
}
