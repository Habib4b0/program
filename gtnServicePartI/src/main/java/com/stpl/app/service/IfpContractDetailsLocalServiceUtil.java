package com.stpl.app.service;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for IfpContractDetails. This utility wraps
 * {@link com.stpl.app.service.impl.IfpContractDetailsLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see IfpContractDetailsLocalService
 * @see com.stpl.app.service.base.IfpContractDetailsLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.IfpContractDetailsLocalServiceImpl
 * @generated
 */
public class IfpContractDetailsLocalServiceUtil {
    private static IfpContractDetailsLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.IfpContractDetailsLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the ifp contract details to the database. Also notifies the appropriate model listeners.
    *
    * @param ifpContractDetails the ifp contract details
    * @return the ifp contract details that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IfpContractDetails addIfpContractDetails(
        com.stpl.app.model.IfpContractDetails ifpContractDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().addIfpContractDetails(ifpContractDetails);
    }

    /**
    * Creates a new ifp contract details with the primary key. Does not add the ifp contract details to the database.
    *
    * @param ifpContractDetailsSid the primary key for the new ifp contract details
    * @return the new ifp contract details
    */
    public static com.stpl.app.model.IfpContractDetails createIfpContractDetails(
        int ifpContractDetailsSid) {
        return getService().createIfpContractDetails(ifpContractDetailsSid);
    }

    /**
    * Deletes the ifp contract details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ifpContractDetailsSid the primary key of the ifp contract details
    * @return the ifp contract details that was removed
    * @throws PortalException if a ifp contract details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IfpContractDetails deleteIfpContractDetails(
        int ifpContractDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteIfpContractDetails(ifpContractDetailsSid);
    }

    /**
    * Deletes the ifp contract details from the database. Also notifies the appropriate model listeners.
    *
    * @param ifpContractDetails the ifp contract details
    * @return the ifp contract details that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IfpContractDetails deleteIfpContractDetails(
        com.stpl.app.model.IfpContractDetails ifpContractDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteIfpContractDetails(ifpContractDetails);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.stpl.app.model.IfpContractDetails fetchIfpContractDetails(
        int ifpContractDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().fetchIfpContractDetails(ifpContractDetailsSid);
    }

    /**
    * Returns the ifp contract details with the primary key.
    *
    * @param ifpContractDetailsSid the primary key of the ifp contract details
    * @return the ifp contract details
    * @throws PortalException if a ifp contract details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IfpContractDetails getIfpContractDetails(
        int ifpContractDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getIfpContractDetails(ifpContractDetailsSid);
    }

    public static com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ifp contract detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ifp contract detailses
    * @param end the upper bound of the range of ifp contract detailses (not inclusive)
    * @return the range of ifp contract detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IfpContractDetails> getIfpContractDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getIfpContractDetailses(start, end);
    }

    /**
    * Returns the number of ifp contract detailses.
    *
    * @return the number of ifp contract detailses
    * @throws SystemException if a system exception occurred
    */
    public static int getIfpContractDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getIfpContractDetailsesCount();
    }

    /**
    * Updates the ifp contract details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ifpContractDetails the ifp contract details
    * @return the ifp contract details that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IfpContractDetails updateIfpContractDetails(
        com.stpl.app.model.IfpContractDetails ifpContractDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().updateIfpContractDetails(ifpContractDetails);
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

    public static java.lang.Boolean saveIfpDetailsAttached(
        java.util.List<java.lang.Object> input, java.lang.Object future) {
        return getService().saveIfpDetailsAttached(input, future);
    }

    public static java.util.List findIFP(java.lang.Object field,
        java.lang.Object value, java.util.List<java.lang.Integer> future,
        java.util.Map<java.lang.String, java.lang.Object> filterMap, int start,
        int end, java.lang.String column, java.lang.String orderBy,
        java.lang.Object future1) {
        return getService()
                   .findIFP(field, value, future, filterMap, start, end,
            column, orderBy, future1);
    }

    public static void clearService() {
        _service = null;
    }

    public static IfpContractDetailsLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    IfpContractDetailsLocalService.class.getName());

            if (invokableLocalService instanceof IfpContractDetailsLocalService) {
                _service = (IfpContractDetailsLocalService) invokableLocalService;
            } else {
                _service = new IfpContractDetailsLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(IfpContractDetailsLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(IfpContractDetailsLocalService service) {
    }
}
