package com.stpl.app.parttwo.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link StArpOutboundLocalService}.
 *
 * @author
 * @see StArpOutboundLocalService
 * @generated
 */
public class StArpOutboundLocalServiceWrapper
    implements StArpOutboundLocalService,
        ServiceWrapper<StArpOutboundLocalService> {
    private StArpOutboundLocalService _stArpOutboundLocalService;

    public StArpOutboundLocalServiceWrapper(
        StArpOutboundLocalService stArpOutboundLocalService) {
        _stArpOutboundLocalService = stArpOutboundLocalService;
    }

    /**
    * Adds the st arp outbound to the database. Also notifies the appropriate model listeners.
    *
    * @param stArpOutbound the st arp outbound
    * @return the st arp outbound that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.StArpOutbound addStArpOutbound(
        com.stpl.app.parttwo.model.StArpOutbound stArpOutbound)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stArpOutboundLocalService.addStArpOutbound(stArpOutbound);
    }

    /**
    * Creates a new st arp outbound with the primary key. Does not add the st arp outbound to the database.
    *
    * @param stArpOutboundPK the primary key for the new st arp outbound
    * @return the new st arp outbound
    */
    @Override
    public com.stpl.app.parttwo.model.StArpOutbound createStArpOutbound(
        com.stpl.app.parttwo.service.persistence.StArpOutboundPK stArpOutboundPK) {
        return _stArpOutboundLocalService.createStArpOutbound(stArpOutboundPK);
    }

    /**
    * Deletes the st arp outbound with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stArpOutboundPK the primary key of the st arp outbound
    * @return the st arp outbound that was removed
    * @throws PortalException if a st arp outbound with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.StArpOutbound deleteStArpOutbound(
        com.stpl.app.parttwo.service.persistence.StArpOutboundPK stArpOutboundPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stArpOutboundLocalService.deleteStArpOutbound(stArpOutboundPK);
    }

    /**
    * Deletes the st arp outbound from the database. Also notifies the appropriate model listeners.
    *
    * @param stArpOutbound the st arp outbound
    * @return the st arp outbound that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.StArpOutbound deleteStArpOutbound(
        com.stpl.app.parttwo.model.StArpOutbound stArpOutbound)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stArpOutboundLocalService.deleteStArpOutbound(stArpOutbound);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _stArpOutboundLocalService.dynamicQuery();
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
        return _stArpOutboundLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StArpOutboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _stArpOutboundLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StArpOutboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _stArpOutboundLocalService.dynamicQuery(dynamicQuery, start,
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
        return _stArpOutboundLocalService.dynamicQueryCount(dynamicQuery);
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
        return _stArpOutboundLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.parttwo.model.StArpOutbound fetchStArpOutbound(
        com.stpl.app.parttwo.service.persistence.StArpOutboundPK stArpOutboundPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stArpOutboundLocalService.fetchStArpOutbound(stArpOutboundPK);
    }

    /**
    * Returns the st arp outbound with the primary key.
    *
    * @param stArpOutboundPK the primary key of the st arp outbound
    * @return the st arp outbound
    * @throws PortalException if a st arp outbound with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.StArpOutbound getStArpOutbound(
        com.stpl.app.parttwo.service.persistence.StArpOutboundPK stArpOutboundPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stArpOutboundLocalService.getStArpOutbound(stArpOutboundPK);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stArpOutboundLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the st arp outbounds.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StArpOutboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st arp outbounds
    * @param end the upper bound of the range of st arp outbounds (not inclusive)
    * @return the range of st arp outbounds
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.parttwo.model.StArpOutbound> getStArpOutbounds(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stArpOutboundLocalService.getStArpOutbounds(start, end);
    }

    /**
    * Returns the number of st arp outbounds.
    *
    * @return the number of st arp outbounds
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getStArpOutboundsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stArpOutboundLocalService.getStArpOutboundsCount();
    }

    /**
    * Updates the st arp outbound in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param stArpOutbound the st arp outbound
    * @return the st arp outbound that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.StArpOutbound updateStArpOutbound(
        com.stpl.app.parttwo.model.StArpOutbound stArpOutbound)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stArpOutboundLocalService.updateStArpOutbound(stArpOutbound);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _stArpOutboundLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _stArpOutboundLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _stArpOutboundLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public StArpOutboundLocalService getWrappedStArpOutboundLocalService() {
        return _stArpOutboundLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedStArpOutboundLocalService(
        StArpOutboundLocalService stArpOutboundLocalService) {
        _stArpOutboundLocalService = stArpOutboundLocalService;
    }

    @Override
    public StArpOutboundLocalService getWrappedService() {
        return _stArpOutboundLocalService;
    }

    @Override
    public void setWrappedService(
        StArpOutboundLocalService stArpOutboundLocalService) {
        _stArpOutboundLocalService = stArpOutboundLocalService;
    }
}
