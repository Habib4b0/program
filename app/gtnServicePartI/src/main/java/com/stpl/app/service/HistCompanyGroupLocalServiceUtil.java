package com.stpl.app.service;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for HistCompanyGroup. This utility wraps
 * {@link com.stpl.app.service.impl.HistCompanyGroupLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see HistCompanyGroupLocalService
 * @see com.stpl.app.service.base.HistCompanyGroupLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.HistCompanyGroupLocalServiceImpl
 * @generated
 */
public class HistCompanyGroupLocalServiceUtil {
    private static HistCompanyGroupLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.HistCompanyGroupLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the hist company group to the database. Also notifies the appropriate model listeners.
    *
    * @param histCompanyGroup the hist company group
    * @return the hist company group that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HistCompanyGroup addHistCompanyGroup(
        com.stpl.app.model.HistCompanyGroup histCompanyGroup)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().addHistCompanyGroup(histCompanyGroup);
    }

    /**
    * Creates a new hist company group with the primary key. Does not add the hist company group to the database.
    *
    * @param histCompanyGroupPK the primary key for the new hist company group
    * @return the new hist company group
    */
    public static com.stpl.app.model.HistCompanyGroup createHistCompanyGroup(
        com.stpl.app.service.persistence.HistCompanyGroupPK histCompanyGroupPK) {
        return getService().createHistCompanyGroup(histCompanyGroupPK);
    }

    /**
    * Deletes the hist company group with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param histCompanyGroupPK the primary key of the hist company group
    * @return the hist company group that was removed
    * @throws PortalException if a hist company group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HistCompanyGroup deleteHistCompanyGroup(
        com.stpl.app.service.persistence.HistCompanyGroupPK histCompanyGroupPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteHistCompanyGroup(histCompanyGroupPK);
    }

    /**
    * Deletes the hist company group from the database. Also notifies the appropriate model listeners.
    *
    * @param histCompanyGroup the hist company group
    * @return the hist company group that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HistCompanyGroup deleteHistCompanyGroup(
        com.stpl.app.model.HistCompanyGroup histCompanyGroup)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteHistCompanyGroup(histCompanyGroup);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistCompanyGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistCompanyGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.stpl.app.model.HistCompanyGroup fetchHistCompanyGroup(
        com.stpl.app.service.persistence.HistCompanyGroupPK histCompanyGroupPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().fetchHistCompanyGroup(histCompanyGroupPK);
    }

    /**
    * Returns the hist company group with the primary key.
    *
    * @param histCompanyGroupPK the primary key of the hist company group
    * @return the hist company group
    * @throws PortalException if a hist company group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HistCompanyGroup getHistCompanyGroup(
        com.stpl.app.service.persistence.HistCompanyGroupPK histCompanyGroupPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getHistCompanyGroup(histCompanyGroupPK);
    }

    public static com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the hist company groups.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistCompanyGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hist company groups
    * @param end the upper bound of the range of hist company groups (not inclusive)
    * @return the range of hist company groups
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.HistCompanyGroup> getHistCompanyGroups(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getHistCompanyGroups(start, end);
    }

    /**
    * Returns the number of hist company groups.
    *
    * @return the number of hist company groups
    * @throws SystemException if a system exception occurred
    */
    public static int getHistCompanyGroupsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getHistCompanyGroupsCount();
    }

    /**
    * Updates the hist company group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param histCompanyGroup the hist company group
    * @return the hist company group that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HistCompanyGroup updateHistCompanyGroup(
        com.stpl.app.model.HistCompanyGroup histCompanyGroup)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().updateHistCompanyGroup(histCompanyGroup);
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

    public static HistCompanyGroupLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    HistCompanyGroupLocalService.class.getName());

            if (invokableLocalService instanceof HistCompanyGroupLocalService) {
                _service = (HistCompanyGroupLocalService) invokableLocalService;
            } else {
                _service = new HistCompanyGroupLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(HistCompanyGroupLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(HistCompanyGroupLocalService service) {
    }
}
