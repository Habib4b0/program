package com.stpl.app.service;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for PsContractDetails. This utility wraps
 * {@link com.stpl.app.service.impl.PsContractDetailsLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see PsContractDetailsLocalService
 * @see com.stpl.app.service.base.PsContractDetailsLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.PsContractDetailsLocalServiceImpl
 * @generated
 */
public class PsContractDetailsLocalServiceUtil {
    private static PsContractDetailsLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.PsContractDetailsLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the ps contract details to the database. Also notifies the appropriate model listeners.
    *
    * @param psContractDetails the ps contract details
    * @return the ps contract details that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.PsContractDetails addPsContractDetails(
        com.stpl.app.model.PsContractDetails psContractDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().addPsContractDetails(psContractDetails);
    }

    /**
    * Creates a new ps contract details with the primary key. Does not add the ps contract details to the database.
    *
    * @param psContractDetailsSid the primary key for the new ps contract details
    * @return the new ps contract details
    */
    public static com.stpl.app.model.PsContractDetails createPsContractDetails(
        int psContractDetailsSid) {
        return getService().createPsContractDetails(psContractDetailsSid);
    }

    /**
    * Deletes the ps contract details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param psContractDetailsSid the primary key of the ps contract details
    * @return the ps contract details that was removed
    * @throws PortalException if a ps contract details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.PsContractDetails deletePsContractDetails(
        int psContractDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().deletePsContractDetails(psContractDetailsSid);
    }

    /**
    * Deletes the ps contract details from the database. Also notifies the appropriate model listeners.
    *
    * @param psContractDetails the ps contract details
    * @return the ps contract details that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.PsContractDetails deletePsContractDetails(
        com.stpl.app.model.PsContractDetails psContractDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().deletePsContractDetails(psContractDetails);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.stpl.app.model.PsContractDetails fetchPsContractDetails(
        int psContractDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().fetchPsContractDetails(psContractDetailsSid);
    }

    /**
    * Returns the ps contract details with the primary key.
    *
    * @param psContractDetailsSid the primary key of the ps contract details
    * @return the ps contract details
    * @throws PortalException if a ps contract details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.PsContractDetails getPsContractDetails(
        int psContractDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getPsContractDetails(psContractDetailsSid);
    }

    public static com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ps contract detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ps contract detailses
    * @param end the upper bound of the range of ps contract detailses (not inclusive)
    * @return the range of ps contract detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.PsContractDetails> getPsContractDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getPsContractDetailses(start, end);
    }

    /**
    * Returns the number of ps contract detailses.
    *
    * @return the number of ps contract detailses
    * @throws SystemException if a system exception occurred
    */
    public static int getPsContractDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getPsContractDetailsesCount();
    }

    /**
    * Updates the ps contract details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param psContractDetails the ps contract details
    * @return the ps contract details that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.PsContractDetails updatePsContractDetails(
        com.stpl.app.model.PsContractDetails psContractDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().updatePsContractDetails(psContractDetails);
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

    public static java.lang.Boolean savePsDetailsAttached(
        java.util.List<java.lang.Object> input, java.lang.Object future) {
        return getService().savePsDetailsAttached(input, future);
    }

    public static void clearService() {
        _service = null;
    }

    public static PsContractDetailsLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PsContractDetailsLocalService.class.getName());

            if (invokableLocalService instanceof PsContractDetailsLocalService) {
                _service = (PsContractDetailsLocalService) invokableLocalService;
            } else {
                _service = new PsContractDetailsLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(PsContractDetailsLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(PsContractDetailsLocalService service) {
    }
}
