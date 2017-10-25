package com.stpl.app.service;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for NmPpaProjectionMaster. This utility wraps
 * {@link com.stpl.app.service.impl.NmPpaProjectionMasterLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see NmPpaProjectionMasterLocalService
 * @see com.stpl.app.service.base.NmPpaProjectionMasterLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.NmPpaProjectionMasterLocalServiceImpl
 * @generated
 */
public class NmPpaProjectionMasterLocalServiceUtil {
    private static NmPpaProjectionMasterLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.NmPpaProjectionMasterLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the nm ppa projection master to the database. Also notifies the appropriate model listeners.
    *
    * @param nmPpaProjectionMaster the nm ppa projection master
    * @return the nm ppa projection master that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NmPpaProjectionMaster addNmPpaProjectionMaster(
        com.stpl.app.model.NmPpaProjectionMaster nmPpaProjectionMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().addNmPpaProjectionMaster(nmPpaProjectionMaster);
    }

    /**
    * Creates a new nm ppa projection master with the primary key. Does not add the nm ppa projection master to the database.
    *
    * @param projectionDetailsSid the primary key for the new nm ppa projection master
    * @return the new nm ppa projection master
    */
    public static com.stpl.app.model.NmPpaProjectionMaster createNmPpaProjectionMaster(
        int projectionDetailsSid) {
        return getService().createNmPpaProjectionMaster(projectionDetailsSid);
    }

    /**
    * Deletes the nm ppa projection master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param projectionDetailsSid the primary key of the nm ppa projection master
    * @return the nm ppa projection master that was removed
    * @throws PortalException if a nm ppa projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NmPpaProjectionMaster deleteNmPpaProjectionMaster(
        int projectionDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteNmPpaProjectionMaster(projectionDetailsSid);
    }

    /**
    * Deletes the nm ppa projection master from the database. Also notifies the appropriate model listeners.
    *
    * @param nmPpaProjectionMaster the nm ppa projection master
    * @return the nm ppa projection master that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NmPpaProjectionMaster deleteNmPpaProjectionMaster(
        com.stpl.app.model.NmPpaProjectionMaster nmPpaProjectionMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteNmPpaProjectionMaster(nmPpaProjectionMaster);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmPpaProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmPpaProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.stpl.app.model.NmPpaProjectionMaster fetchNmPpaProjectionMaster(
        int projectionDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().fetchNmPpaProjectionMaster(projectionDetailsSid);
    }

    /**
    * Returns the nm ppa projection master with the primary key.
    *
    * @param projectionDetailsSid the primary key of the nm ppa projection master
    * @return the nm ppa projection master
    * @throws PortalException if a nm ppa projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NmPpaProjectionMaster getNmPpaProjectionMaster(
        int projectionDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getNmPpaProjectionMaster(projectionDetailsSid);
    }

    public static com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the nm ppa projection masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmPpaProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of nm ppa projection masters
    * @param end the upper bound of the range of nm ppa projection masters (not inclusive)
    * @return the range of nm ppa projection masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.NmPpaProjectionMaster> getNmPpaProjectionMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getNmPpaProjectionMasters(start, end);
    }

    /**
    * Returns the number of nm ppa projection masters.
    *
    * @return the number of nm ppa projection masters
    * @throws SystemException if a system exception occurred
    */
    public static int getNmPpaProjectionMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getNmPpaProjectionMastersCount();
    }

    /**
    * Updates the nm ppa projection master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param nmPpaProjectionMaster the nm ppa projection master
    * @return the nm ppa projection master that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NmPpaProjectionMaster updateNmPpaProjectionMaster(
        com.stpl.app.model.NmPpaProjectionMaster nmPpaProjectionMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().updateNmPpaProjectionMaster(nmPpaProjectionMaster);
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

    public static java.util.List getPPAProjectionList(
        java.lang.Integer projectionId, int levelNo, java.lang.String parent,
        boolean last, int startIndex, int endIndex, boolean isCount,
        java.lang.String levelName) {
        return getService()
                   .getPPAProjectionList(projectionId, levelNo, parent, last,
            startIndex, endIndex, isCount, levelName);
    }

    public static void setPPAProjectionMassUpdate(java.lang.Object priceCap,
        int startQuater, int endQuater, int startYear, int endYear,
        int projectionId, java.lang.String parent, java.lang.String levelValue) {
        getService()
            .setPPAProjectionMassUpdate(priceCap, startQuater, endQuater,
            startYear, endYear, projectionId, parent, levelValue);
    }

    public static java.util.List getPPAResults(java.lang.Integer projectionId,
        int levelNo, java.lang.String parent, boolean last, int startIndex,
        int endIndex, boolean isCount, java.util.List<java.lang.String> input,
        java.lang.String levelName) {
        return getService()
                   .getPPAResults(projectionId, levelNo, parent, last,
            startIndex, endIndex, isCount, input, levelName);
    }

    public static java.util.List getLevelValues(int projectionId, int levelNo,
        java.lang.String parent) {
        return getService().getLevelValues(projectionId, levelNo, parent);
    }

    public static java.util.List getProductHierarchyLevel(int projectionId,
        int levelNo, java.lang.String parent) {
        return getService()
                   .getProductHierarchyLevel(projectionId, levelNo, parent);
    }

    public static void clearService() {
        _service = null;
    }

    public static NmPpaProjectionMasterLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    NmPpaProjectionMasterLocalService.class.getName());

            if (invokableLocalService instanceof NmPpaProjectionMasterLocalService) {
                _service = (NmPpaProjectionMasterLocalService) invokableLocalService;
            } else {
                _service = new NmPpaProjectionMasterLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(NmPpaProjectionMasterLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(NmPpaProjectionMasterLocalService service) {
    }
}
