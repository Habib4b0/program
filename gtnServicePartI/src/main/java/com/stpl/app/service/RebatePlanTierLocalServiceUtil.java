package com.stpl.app.service;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for RebatePlanTier. This utility wraps
 * {@link com.stpl.app.service.impl.RebatePlanTierLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see RebatePlanTierLocalService
 * @see com.stpl.app.service.base.RebatePlanTierLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.RebatePlanTierLocalServiceImpl
 * @generated
 */
public class RebatePlanTierLocalServiceUtil {
    private static RebatePlanTierLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.RebatePlanTierLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the rebate plan tier to the database. Also notifies the appropriate model listeners.
    *
    * @param rebatePlanTier the rebate plan tier
    * @return the rebate plan tier that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RebatePlanTier addRebatePlanTier(
        com.stpl.app.model.RebatePlanTier rebatePlanTier)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().addRebatePlanTier(rebatePlanTier);
    }

    /**
    * Creates a new rebate plan tier with the primary key. Does not add the rebate plan tier to the database.
    *
    * @param rebatePlanTierSid the primary key for the new rebate plan tier
    * @return the new rebate plan tier
    */
    public static com.stpl.app.model.RebatePlanTier createRebatePlanTier(
        int rebatePlanTierSid) {
        return getService().createRebatePlanTier(rebatePlanTierSid);
    }

    /**
    * Deletes the rebate plan tier with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param rebatePlanTierSid the primary key of the rebate plan tier
    * @return the rebate plan tier that was removed
    * @throws PortalException if a rebate plan tier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RebatePlanTier deleteRebatePlanTier(
        int rebatePlanTierSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteRebatePlanTier(rebatePlanTierSid);
    }

    /**
    * Deletes the rebate plan tier from the database. Also notifies the appropriate model listeners.
    *
    * @param rebatePlanTier the rebate plan tier
    * @return the rebate plan tier that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RebatePlanTier deleteRebatePlanTier(
        com.stpl.app.model.RebatePlanTier rebatePlanTier)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteRebatePlanTier(rebatePlanTier);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanTierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanTierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.stpl.app.model.RebatePlanTier fetchRebatePlanTier(
        int rebatePlanTierSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().fetchRebatePlanTier(rebatePlanTierSid);
    }

    /**
    * Returns the rebate plan tier with the primary key.
    *
    * @param rebatePlanTierSid the primary key of the rebate plan tier
    * @return the rebate plan tier
    * @throws PortalException if a rebate plan tier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RebatePlanTier getRebatePlanTier(
        int rebatePlanTierSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getRebatePlanTier(rebatePlanTierSid);
    }

    public static com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the rebate plan tiers.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanTierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of rebate plan tiers
    * @param end the upper bound of the range of rebate plan tiers (not inclusive)
    * @return the range of rebate plan tiers
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RebatePlanTier> getRebatePlanTiers(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getRebatePlanTiers(start, end);
    }

    /**
    * Returns the number of rebate plan tiers.
    *
    * @return the number of rebate plan tiers
    * @throws SystemException if a system exception occurred
    */
    public static int getRebatePlanTiersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getRebatePlanTiersCount();
    }

    /**
    * Updates the rebate plan tier in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param rebatePlanTier the rebate plan tier
    * @return the rebate plan tier that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RebatePlanTier updateRebatePlanTier(
        com.stpl.app.model.RebatePlanTier rebatePlanTier)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().updateRebatePlanTier(rebatePlanTier);
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

    public static RebatePlanTierLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    RebatePlanTierLocalService.class.getName());

            if (invokableLocalService instanceof RebatePlanTierLocalService) {
                _service = (RebatePlanTierLocalService) invokableLocalService;
            } else {
                _service = new RebatePlanTierLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(RebatePlanTierLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(RebatePlanTierLocalService service) {
    }
}
