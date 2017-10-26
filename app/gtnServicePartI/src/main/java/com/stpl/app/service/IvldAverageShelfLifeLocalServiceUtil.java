package com.stpl.app.service;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for IvldAverageShelfLife. This utility wraps
 * {@link com.stpl.app.service.impl.IvldAverageShelfLifeLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see IvldAverageShelfLifeLocalService
 * @see com.stpl.app.service.base.IvldAverageShelfLifeLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.IvldAverageShelfLifeLocalServiceImpl
 * @generated
 */
public class IvldAverageShelfLifeLocalServiceUtil {
    private static IvldAverageShelfLifeLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.IvldAverageShelfLifeLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the ivld average shelf life to the database. Also notifies the appropriate model listeners.
    *
    * @param ivldAverageShelfLife the ivld average shelf life
    * @return the ivld average shelf life that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldAverageShelfLife addIvldAverageShelfLife(
        com.stpl.app.model.IvldAverageShelfLife ivldAverageShelfLife)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().addIvldAverageShelfLife(ivldAverageShelfLife);
    }

    /**
    * Creates a new ivld average shelf life with the primary key. Does not add the ivld average shelf life to the database.
    *
    * @param ivldAverageShelfLifeSid the primary key for the new ivld average shelf life
    * @return the new ivld average shelf life
    */
    public static com.stpl.app.model.IvldAverageShelfLife createIvldAverageShelfLife(
        int ivldAverageShelfLifeSid) {
        return getService().createIvldAverageShelfLife(ivldAverageShelfLifeSid);
    }

    /**
    * Deletes the ivld average shelf life with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldAverageShelfLifeSid the primary key of the ivld average shelf life
    * @return the ivld average shelf life that was removed
    * @throws PortalException if a ivld average shelf life with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldAverageShelfLife deleteIvldAverageShelfLife(
        int ivldAverageShelfLifeSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteIvldAverageShelfLife(ivldAverageShelfLifeSid);
    }

    /**
    * Deletes the ivld average shelf life from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldAverageShelfLife the ivld average shelf life
    * @return the ivld average shelf life that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldAverageShelfLife deleteIvldAverageShelfLife(
        com.stpl.app.model.IvldAverageShelfLife ivldAverageShelfLife)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteIvldAverageShelfLife(ivldAverageShelfLife);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldAverageShelfLifeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldAverageShelfLifeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.stpl.app.model.IvldAverageShelfLife fetchIvldAverageShelfLife(
        int ivldAverageShelfLifeSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().fetchIvldAverageShelfLife(ivldAverageShelfLifeSid);
    }

    /**
    * Returns the ivld average shelf life with the primary key.
    *
    * @param ivldAverageShelfLifeSid the primary key of the ivld average shelf life
    * @return the ivld average shelf life
    * @throws PortalException if a ivld average shelf life with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldAverageShelfLife getIvldAverageShelfLife(
        int ivldAverageShelfLifeSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getIvldAverageShelfLife(ivldAverageShelfLifeSid);
    }

    public static com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ivld average shelf lifes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldAverageShelfLifeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld average shelf lifes
    * @param end the upper bound of the range of ivld average shelf lifes (not inclusive)
    * @return the range of ivld average shelf lifes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IvldAverageShelfLife> getIvldAverageShelfLifes(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getIvldAverageShelfLifes(start, end);
    }

    /**
    * Returns the number of ivld average shelf lifes.
    *
    * @return the number of ivld average shelf lifes
    * @throws SystemException if a system exception occurred
    */
    public static int getIvldAverageShelfLifesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getIvldAverageShelfLifesCount();
    }

    /**
    * Updates the ivld average shelf life in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ivldAverageShelfLife the ivld average shelf life
    * @return the ivld average shelf life that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldAverageShelfLife updateIvldAverageShelfLife(
        com.stpl.app.model.IvldAverageShelfLife ivldAverageShelfLife)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().updateIvldAverageShelfLife(ivldAverageShelfLife);
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

    public static IvldAverageShelfLifeLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    IvldAverageShelfLifeLocalService.class.getName());

            if (invokableLocalService instanceof IvldAverageShelfLifeLocalService) {
                _service = (IvldAverageShelfLifeLocalService) invokableLocalService;
            } else {
                _service = new IvldAverageShelfLifeLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(IvldAverageShelfLifeLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(IvldAverageShelfLifeLocalService service) {
    }
}
