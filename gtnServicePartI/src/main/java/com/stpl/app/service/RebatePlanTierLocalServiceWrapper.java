package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link RebatePlanTierLocalService}.
 *
 * @author
 * @see RebatePlanTierLocalService
 * @generated
 */
public class RebatePlanTierLocalServiceWrapper
    implements RebatePlanTierLocalService,
        ServiceWrapper<RebatePlanTierLocalService> {
    private RebatePlanTierLocalService _rebatePlanTierLocalService;

    public RebatePlanTierLocalServiceWrapper(
        RebatePlanTierLocalService rebatePlanTierLocalService) {
        _rebatePlanTierLocalService = rebatePlanTierLocalService;
    }

    /**
    * Adds the rebate plan tier to the database. Also notifies the appropriate model listeners.
    *
    * @param rebatePlanTier the rebate plan tier
    * @return the rebate plan tier that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.RebatePlanTier addRebatePlanTier(
        com.stpl.app.model.RebatePlanTier rebatePlanTier)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _rebatePlanTierLocalService.addRebatePlanTier(rebatePlanTier);
    }

    /**
    * Creates a new rebate plan tier with the primary key. Does not add the rebate plan tier to the database.
    *
    * @param rebatePlanTierSid the primary key for the new rebate plan tier
    * @return the new rebate plan tier
    */
    @Override
    public com.stpl.app.model.RebatePlanTier createRebatePlanTier(
        int rebatePlanTierSid) {
        return _rebatePlanTierLocalService.createRebatePlanTier(rebatePlanTierSid);
    }

    /**
    * Deletes the rebate plan tier with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param rebatePlanTierSid the primary key of the rebate plan tier
    * @return the rebate plan tier that was removed
    * @throws PortalException if a rebate plan tier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.RebatePlanTier deleteRebatePlanTier(
        int rebatePlanTierSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _rebatePlanTierLocalService.deleteRebatePlanTier(rebatePlanTierSid);
    }

    /**
    * Deletes the rebate plan tier from the database. Also notifies the appropriate model listeners.
    *
    * @param rebatePlanTier the rebate plan tier
    * @return the rebate plan tier that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.RebatePlanTier deleteRebatePlanTier(
        com.stpl.app.model.RebatePlanTier rebatePlanTier)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _rebatePlanTierLocalService.deleteRebatePlanTier(rebatePlanTier);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _rebatePlanTierLocalService.dynamicQuery();
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _rebatePlanTierLocalService.dynamicQuery(dynamicQuery);
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
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException {
        return _rebatePlanTierLocalService.dynamicQuery(dynamicQuery, start, end);
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
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _rebatePlanTierLocalService.dynamicQuery(dynamicQuery, start,
            end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    @Override
    public long dynamicQueryCount(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _rebatePlanTierLocalService.dynamicQueryCount(dynamicQuery);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    @Override
    public long dynamicQueryCount(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.stpl.portal.kernel.dao.orm.Projection projection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _rebatePlanTierLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.RebatePlanTier fetchRebatePlanTier(
        int rebatePlanTierSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _rebatePlanTierLocalService.fetchRebatePlanTier(rebatePlanTierSid);
    }

    /**
    * Returns the rebate plan tier with the primary key.
    *
    * @param rebatePlanTierSid the primary key of the rebate plan tier
    * @return the rebate plan tier
    * @throws PortalException if a rebate plan tier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.RebatePlanTier getRebatePlanTier(
        int rebatePlanTierSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _rebatePlanTierLocalService.getRebatePlanTier(rebatePlanTierSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _rebatePlanTierLocalService.getPersistedModel(primaryKeyObj);
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
    @Override
    public java.util.List<com.stpl.app.model.RebatePlanTier> getRebatePlanTiers(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _rebatePlanTierLocalService.getRebatePlanTiers(start, end);
    }

    /**
    * Returns the number of rebate plan tiers.
    *
    * @return the number of rebate plan tiers
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getRebatePlanTiersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _rebatePlanTierLocalService.getRebatePlanTiersCount();
    }

    /**
    * Updates the rebate plan tier in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param rebatePlanTier the rebate plan tier
    * @return the rebate plan tier that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.RebatePlanTier updateRebatePlanTier(
        com.stpl.app.model.RebatePlanTier rebatePlanTier)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _rebatePlanTierLocalService.updateRebatePlanTier(rebatePlanTier);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _rebatePlanTierLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _rebatePlanTierLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _rebatePlanTierLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public RebatePlanTierLocalService getWrappedRebatePlanTierLocalService() {
        return _rebatePlanTierLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedRebatePlanTierLocalService(
        RebatePlanTierLocalService rebatePlanTierLocalService) {
        _rebatePlanTierLocalService = rebatePlanTierLocalService;
    }

    @Override
    public RebatePlanTierLocalService getWrappedService() {
        return _rebatePlanTierLocalService;
    }

    @Override
    public void setWrappedService(
        RebatePlanTierLocalService rebatePlanTierLocalService) {
        _rebatePlanTierLocalService = rebatePlanTierLocalService;
    }
}
