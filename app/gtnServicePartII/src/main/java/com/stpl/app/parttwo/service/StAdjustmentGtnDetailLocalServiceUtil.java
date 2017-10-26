package com.stpl.app.parttwo.service;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for StAdjustmentGtnDetail. This utility wraps
 * {@link com.stpl.app.parttwo.service.impl.StAdjustmentGtnDetailLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see StAdjustmentGtnDetailLocalService
 * @see com.stpl.app.parttwo.service.base.StAdjustmentGtnDetailLocalServiceBaseImpl
 * @see com.stpl.app.parttwo.service.impl.StAdjustmentGtnDetailLocalServiceImpl
 * @generated
 */
public class StAdjustmentGtnDetailLocalServiceUtil {
    private static StAdjustmentGtnDetailLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.stpl.app.parttwo.service.impl.StAdjustmentGtnDetailLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the st adjustment gtn detail to the database. Also notifies the appropriate model listeners.
    *
    * @param stAdjustmentGtnDetail the st adjustment gtn detail
    * @return the st adjustment gtn detail that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.StAdjustmentGtnDetail addStAdjustmentGtnDetail(
        com.stpl.app.parttwo.model.StAdjustmentGtnDetail stAdjustmentGtnDetail)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().addStAdjustmentGtnDetail(stAdjustmentGtnDetail);
    }

    /**
    * Creates a new st adjustment gtn detail with the primary key. Does not add the st adjustment gtn detail to the database.
    *
    * @param stAdjustmentGtnDetailPK the primary key for the new st adjustment gtn detail
    * @return the new st adjustment gtn detail
    */
    public static com.stpl.app.parttwo.model.StAdjustmentGtnDetail createStAdjustmentGtnDetail(
        com.stpl.app.parttwo.service.persistence.StAdjustmentGtnDetailPK stAdjustmentGtnDetailPK) {
        return getService().createStAdjustmentGtnDetail(stAdjustmentGtnDetailPK);
    }

    /**
    * Deletes the st adjustment gtn detail with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stAdjustmentGtnDetailPK the primary key of the st adjustment gtn detail
    * @return the st adjustment gtn detail that was removed
    * @throws PortalException if a st adjustment gtn detail with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.StAdjustmentGtnDetail deleteStAdjustmentGtnDetail(
        com.stpl.app.parttwo.service.persistence.StAdjustmentGtnDetailPK stAdjustmentGtnDetailPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteStAdjustmentGtnDetail(stAdjustmentGtnDetailPK);
    }

    /**
    * Deletes the st adjustment gtn detail from the database. Also notifies the appropriate model listeners.
    *
    * @param stAdjustmentGtnDetail the st adjustment gtn detail
    * @return the st adjustment gtn detail that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.StAdjustmentGtnDetail deleteStAdjustmentGtnDetail(
        com.stpl.app.parttwo.model.StAdjustmentGtnDetail stAdjustmentGtnDetail)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteStAdjustmentGtnDetail(stAdjustmentGtnDetail);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StAdjustmentGtnDetailModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StAdjustmentGtnDetailModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.stpl.app.parttwo.model.StAdjustmentGtnDetail fetchStAdjustmentGtnDetail(
        com.stpl.app.parttwo.service.persistence.StAdjustmentGtnDetailPK stAdjustmentGtnDetailPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().fetchStAdjustmentGtnDetail(stAdjustmentGtnDetailPK);
    }

    /**
    * Returns the st adjustment gtn detail with the primary key.
    *
    * @param stAdjustmentGtnDetailPK the primary key of the st adjustment gtn detail
    * @return the st adjustment gtn detail
    * @throws PortalException if a st adjustment gtn detail with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.StAdjustmentGtnDetail getStAdjustmentGtnDetail(
        com.stpl.app.parttwo.service.persistence.StAdjustmentGtnDetailPK stAdjustmentGtnDetailPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getStAdjustmentGtnDetail(stAdjustmentGtnDetailPK);
    }

    public static com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the st adjustment gtn details.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StAdjustmentGtnDetailModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st adjustment gtn details
    * @param end the upper bound of the range of st adjustment gtn details (not inclusive)
    * @return the range of st adjustment gtn details
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.StAdjustmentGtnDetail> getStAdjustmentGtnDetails(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getStAdjustmentGtnDetails(start, end);
    }

    /**
    * Returns the number of st adjustment gtn details.
    *
    * @return the number of st adjustment gtn details
    * @throws SystemException if a system exception occurred
    */
    public static int getStAdjustmentGtnDetailsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getStAdjustmentGtnDetailsCount();
    }

    /**
    * Updates the st adjustment gtn detail in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param stAdjustmentGtnDetail the st adjustment gtn detail
    * @return the st adjustment gtn detail that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.StAdjustmentGtnDetail updateStAdjustmentGtnDetail(
        com.stpl.app.parttwo.model.StAdjustmentGtnDetail stAdjustmentGtnDetail)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().updateStAdjustmentGtnDetail(stAdjustmentGtnDetail);
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

    public static StAdjustmentGtnDetailLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    StAdjustmentGtnDetailLocalService.class.getName());

            if (invokableLocalService instanceof StAdjustmentGtnDetailLocalService) {
                _service = (StAdjustmentGtnDetailLocalService) invokableLocalService;
            } else {
                _service = new StAdjustmentGtnDetailLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(StAdjustmentGtnDetailLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(StAdjustmentGtnDetailLocalService service) {
    }
}
