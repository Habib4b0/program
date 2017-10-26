package com.stpl.app.parttwo.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ArpOutboundLocalService}.
 *
 * @author
 * @see ArpOutboundLocalService
 * @generated
 */
public class ArpOutboundLocalServiceWrapper implements ArpOutboundLocalService,
    ServiceWrapper<ArpOutboundLocalService> {
    private ArpOutboundLocalService _arpOutboundLocalService;

    public ArpOutboundLocalServiceWrapper(
        ArpOutboundLocalService arpOutboundLocalService) {
        _arpOutboundLocalService = arpOutboundLocalService;
    }

    /**
    * Adds the arp outbound to the database. Also notifies the appropriate model listeners.
    *
    * @param arpOutbound the arp outbound
    * @return the arp outbound that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.ArpOutbound addArpOutbound(
        com.stpl.app.parttwo.model.ArpOutbound arpOutbound)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _arpOutboundLocalService.addArpOutbound(arpOutbound);
    }

    /**
    * Creates a new arp outbound with the primary key. Does not add the arp outbound to the database.
    *
    * @param arpOutboundPK the primary key for the new arp outbound
    * @return the new arp outbound
    */
    @Override
    public com.stpl.app.parttwo.model.ArpOutbound createArpOutbound(
        com.stpl.app.parttwo.service.persistence.ArpOutboundPK arpOutboundPK) {
        return _arpOutboundLocalService.createArpOutbound(arpOutboundPK);
    }

    /**
    * Deletes the arp outbound with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param arpOutboundPK the primary key of the arp outbound
    * @return the arp outbound that was removed
    * @throws PortalException if a arp outbound with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.ArpOutbound deleteArpOutbound(
        com.stpl.app.parttwo.service.persistence.ArpOutboundPK arpOutboundPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _arpOutboundLocalService.deleteArpOutbound(arpOutboundPK);
    }

    /**
    * Deletes the arp outbound from the database. Also notifies the appropriate model listeners.
    *
    * @param arpOutbound the arp outbound
    * @return the arp outbound that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.ArpOutbound deleteArpOutbound(
        com.stpl.app.parttwo.model.ArpOutbound arpOutbound)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _arpOutboundLocalService.deleteArpOutbound(arpOutbound);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _arpOutboundLocalService.dynamicQuery();
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
        return _arpOutboundLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.ArpOutboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _arpOutboundLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.ArpOutboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _arpOutboundLocalService.dynamicQuery(dynamicQuery, start, end,
            orderByComparator);
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
        return _arpOutboundLocalService.dynamicQueryCount(dynamicQuery);
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
        return _arpOutboundLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.parttwo.model.ArpOutbound fetchArpOutbound(
        com.stpl.app.parttwo.service.persistence.ArpOutboundPK arpOutboundPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _arpOutboundLocalService.fetchArpOutbound(arpOutboundPK);
    }

    /**
    * Returns the arp outbound with the primary key.
    *
    * @param arpOutboundPK the primary key of the arp outbound
    * @return the arp outbound
    * @throws PortalException if a arp outbound with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.ArpOutbound getArpOutbound(
        com.stpl.app.parttwo.service.persistence.ArpOutboundPK arpOutboundPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _arpOutboundLocalService.getArpOutbound(arpOutboundPK);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _arpOutboundLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the arp outbounds.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.ArpOutboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of arp outbounds
    * @param end the upper bound of the range of arp outbounds (not inclusive)
    * @return the range of arp outbounds
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.parttwo.model.ArpOutbound> getArpOutbounds(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _arpOutboundLocalService.getArpOutbounds(start, end);
    }

    /**
    * Returns the number of arp outbounds.
    *
    * @return the number of arp outbounds
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getArpOutboundsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _arpOutboundLocalService.getArpOutboundsCount();
    }

    /**
    * Updates the arp outbound in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param arpOutbound the arp outbound
    * @return the arp outbound that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.ArpOutbound updateArpOutbound(
        com.stpl.app.parttwo.model.ArpOutbound arpOutbound)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _arpOutboundLocalService.updateArpOutbound(arpOutbound);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _arpOutboundLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _arpOutboundLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _arpOutboundLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ArpOutboundLocalService getWrappedArpOutboundLocalService() {
        return _arpOutboundLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedArpOutboundLocalService(
        ArpOutboundLocalService arpOutboundLocalService) {
        _arpOutboundLocalService = arpOutboundLocalService;
    }

    @Override
    public ArpOutboundLocalService getWrappedService() {
        return _arpOutboundLocalService;
    }

    @Override
    public void setWrappedService(
        ArpOutboundLocalService arpOutboundLocalService) {
        _arpOutboundLocalService = arpOutboundLocalService;
    }
}
