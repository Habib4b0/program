package com.stpl.app.service;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for NmSalesProjection. This utility wraps
 * {@link com.stpl.app.service.impl.NmSalesProjectionLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see NmSalesProjectionLocalService
 * @see com.stpl.app.service.base.NmSalesProjectionLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.NmSalesProjectionLocalServiceImpl
 * @generated
 */
public class NmSalesProjectionLocalServiceUtil {
    private static NmSalesProjectionLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.NmSalesProjectionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the nm sales projection to the database. Also notifies the appropriate model listeners.
    *
    * @param nmSalesProjection the nm sales projection
    * @return the nm sales projection that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NmSalesProjection addNmSalesProjection(
        com.stpl.app.model.NmSalesProjection nmSalesProjection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().addNmSalesProjection(nmSalesProjection);
    }

    /**
    * Creates a new nm sales projection with the primary key. Does not add the nm sales projection to the database.
    *
    * @param nmSalesProjectionPK the primary key for the new nm sales projection
    * @return the new nm sales projection
    */
    public static com.stpl.app.model.NmSalesProjection createNmSalesProjection(
        com.stpl.app.service.persistence.NmSalesProjectionPK nmSalesProjectionPK) {
        return getService().createNmSalesProjection(nmSalesProjectionPK);
    }

    /**
    * Deletes the nm sales projection with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param nmSalesProjectionPK the primary key of the nm sales projection
    * @return the nm sales projection that was removed
    * @throws PortalException if a nm sales projection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NmSalesProjection deleteNmSalesProjection(
        com.stpl.app.service.persistence.NmSalesProjectionPK nmSalesProjectionPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteNmSalesProjection(nmSalesProjectionPK);
    }

    /**
    * Deletes the nm sales projection from the database. Also notifies the appropriate model listeners.
    *
    * @param nmSalesProjection the nm sales projection
    * @return the nm sales projection that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NmSalesProjection deleteNmSalesProjection(
        com.stpl.app.model.NmSalesProjection nmSalesProjection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteNmSalesProjection(nmSalesProjection);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmSalesProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmSalesProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.stpl.app.model.NmSalesProjection fetchNmSalesProjection(
        com.stpl.app.service.persistence.NmSalesProjectionPK nmSalesProjectionPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().fetchNmSalesProjection(nmSalesProjectionPK);
    }

    /**
    * Returns the nm sales projection with the primary key.
    *
    * @param nmSalesProjectionPK the primary key of the nm sales projection
    * @return the nm sales projection
    * @throws PortalException if a nm sales projection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NmSalesProjection getNmSalesProjection(
        com.stpl.app.service.persistence.NmSalesProjectionPK nmSalesProjectionPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getNmSalesProjection(nmSalesProjectionPK);
    }

    public static com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the nm sales projections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmSalesProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of nm sales projections
    * @param end the upper bound of the range of nm sales projections (not inclusive)
    * @return the range of nm sales projections
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.NmSalesProjection> getNmSalesProjections(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getNmSalesProjections(start, end);
    }

    /**
    * Returns the number of nm sales projections.
    *
    * @return the number of nm sales projections
    * @throws SystemException if a system exception occurred
    */
    public static int getNmSalesProjectionsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getNmSalesProjectionsCount();
    }

    /**
    * Updates the nm sales projection in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param nmSalesProjection the nm sales projection
    * @return the nm sales projection that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NmSalesProjection updateNmSalesProjection(
        com.stpl.app.model.NmSalesProjection nmSalesProjection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().updateNmSalesProjection(nmSalesProjection);
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

    public static java.util.List getSalesResult(java.lang.Object[] inputs) {
        return getService().getSalesResult(inputs);
    }

    public static java.util.List getAssumptionResult(java.util.List input,
        java.lang.String queryName) {
        return getService().getAssumptionResult(input, queryName);
    }

    public static java.util.List getSalesProjectionResults(
        java.lang.Object[] inputs) {
        return getService().getSalesProjectionResults(inputs);
    }

    public static java.util.List getSalesProjectionResultLevels(
        java.lang.Object[] inputs) {
        return getService().getSalesProjectionResultLevels(inputs);
    }

    public static java.util.List getVarianceSales(int projectionId,
        java.lang.String frequency,
        java.util.List<java.lang.Integer> startAndEndPeriods,
        java.lang.String actualsOrProjections, java.lang.String parentName,
        java.lang.String year, int levelNo, java.lang.String sales) {
        return getService()
                   .getVarianceSales(projectionId, frequency,
            startAndEndPeriods, actualsOrProjections, parentName, year,
            levelNo, sales);
    }

    public static void clearService() {
        _service = null;
    }

    public static NmSalesProjectionLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    NmSalesProjectionLocalService.class.getName());

            if (invokableLocalService instanceof NmSalesProjectionLocalService) {
                _service = (NmSalesProjectionLocalService) invokableLocalService;
            } else {
                _service = new NmSalesProjectionLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(NmSalesProjectionLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(NmSalesProjectionLocalService service) {
    }
}
