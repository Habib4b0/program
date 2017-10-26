package com.stpl.app.service;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for IfpDetails. This utility wraps
 * {@link com.stpl.app.service.impl.IfpDetailsLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see IfpDetailsLocalService
 * @see com.stpl.app.service.base.IfpDetailsLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.IfpDetailsLocalServiceImpl
 * @generated
 */
public class IfpDetailsLocalServiceUtil {
    private static IfpDetailsLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.IfpDetailsLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the ifp details to the database. Also notifies the appropriate model listeners.
    *
    * @param ifpDetails the ifp details
    * @return the ifp details that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IfpDetails addIfpDetails(
        com.stpl.app.model.IfpDetails ifpDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().addIfpDetails(ifpDetails);
    }

    /**
    * Creates a new ifp details with the primary key. Does not add the ifp details to the database.
    *
    * @param ifpDetailsSid the primary key for the new ifp details
    * @return the new ifp details
    */
    public static com.stpl.app.model.IfpDetails createIfpDetails(
        int ifpDetailsSid) {
        return getService().createIfpDetails(ifpDetailsSid);
    }

    /**
    * Deletes the ifp details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ifpDetailsSid the primary key of the ifp details
    * @return the ifp details that was removed
    * @throws PortalException if a ifp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IfpDetails deleteIfpDetails(
        int ifpDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteIfpDetails(ifpDetailsSid);
    }

    /**
    * Deletes the ifp details from the database. Also notifies the appropriate model listeners.
    *
    * @param ifpDetails the ifp details
    * @return the ifp details that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IfpDetails deleteIfpDetails(
        com.stpl.app.model.IfpDetails ifpDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteIfpDetails(ifpDetails);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.stpl.app.model.IfpDetails fetchIfpDetails(
        int ifpDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().fetchIfpDetails(ifpDetailsSid);
    }

    /**
    * Returns the ifp details with the primary key.
    *
    * @param ifpDetailsSid the primary key of the ifp details
    * @return the ifp details
    * @throws PortalException if a ifp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IfpDetails getIfpDetails(int ifpDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getIfpDetails(ifpDetailsSid);
    }

    public static com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ifp detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ifp detailses
    * @param end the upper bound of the range of ifp detailses (not inclusive)
    * @return the range of ifp detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IfpDetails> getIfpDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getIfpDetailses(start, end);
    }

    /**
    * Returns the number of ifp detailses.
    *
    * @return the number of ifp detailses
    * @throws SystemException if a system exception occurred
    */
    public static int getIfpDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getIfpDetailsesCount();
    }

    /**
    * Updates the ifp details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ifpDetails the ifp details
    * @return the ifp details that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IfpDetails updateIfpDetails(
        com.stpl.app.model.IfpDetails ifpDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().updateIfpDetails(ifpDetails);
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

    public static java.util.List<com.stpl.app.model.IfpDetails> findByItemFamilyPlanDetails(
        int ifpModelSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().findByItemFamilyPlanDetails(ifpModelSid);
    }

    public static void clearService() {
        _service = null;
    }

    public static IfpDetailsLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    IfpDetailsLocalService.class.getName());

            if (invokableLocalService instanceof IfpDetailsLocalService) {
                _service = (IfpDetailsLocalService) invokableLocalService;
            } else {
                _service = new IfpDetailsLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(IfpDetailsLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(IfpDetailsLocalService service) {
    }
}
