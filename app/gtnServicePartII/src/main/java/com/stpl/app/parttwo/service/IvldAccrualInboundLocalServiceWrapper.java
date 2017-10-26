package com.stpl.app.parttwo.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link IvldAccrualInboundLocalService}.
 *
 * @author
 * @see IvldAccrualInboundLocalService
 * @generated
 */
public class IvldAccrualInboundLocalServiceWrapper
    implements IvldAccrualInboundLocalService,
        ServiceWrapper<IvldAccrualInboundLocalService> {
    private IvldAccrualInboundLocalService _ivldAccrualInboundLocalService;

    public IvldAccrualInboundLocalServiceWrapper(
        IvldAccrualInboundLocalService ivldAccrualInboundLocalService) {
        _ivldAccrualInboundLocalService = ivldAccrualInboundLocalService;
    }

    /**
    * Adds the ivld accrual inbound to the database. Also notifies the appropriate model listeners.
    *
    * @param ivldAccrualInbound the ivld accrual inbound
    * @return the ivld accrual inbound that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.IvldAccrualInbound addIvldAccrualInbound(
        com.stpl.app.parttwo.model.IvldAccrualInbound ivldAccrualInbound)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldAccrualInboundLocalService.addIvldAccrualInbound(ivldAccrualInbound);
    }

    /**
    * Creates a new ivld accrual inbound with the primary key. Does not add the ivld accrual inbound to the database.
    *
    * @param ivldAccrualInboundSid the primary key for the new ivld accrual inbound
    * @return the new ivld accrual inbound
    */
    @Override
    public com.stpl.app.parttwo.model.IvldAccrualInbound createIvldAccrualInbound(
        int ivldAccrualInboundSid) {
        return _ivldAccrualInboundLocalService.createIvldAccrualInbound(ivldAccrualInboundSid);
    }

    /**
    * Deletes the ivld accrual inbound with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldAccrualInboundSid the primary key of the ivld accrual inbound
    * @return the ivld accrual inbound that was removed
    * @throws PortalException if a ivld accrual inbound with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.IvldAccrualInbound deleteIvldAccrualInbound(
        int ivldAccrualInboundSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldAccrualInboundLocalService.deleteIvldAccrualInbound(ivldAccrualInboundSid);
    }

    /**
    * Deletes the ivld accrual inbound from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldAccrualInbound the ivld accrual inbound
    * @return the ivld accrual inbound that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.IvldAccrualInbound deleteIvldAccrualInbound(
        com.stpl.app.parttwo.model.IvldAccrualInbound ivldAccrualInbound)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldAccrualInboundLocalService.deleteIvldAccrualInbound(ivldAccrualInbound);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _ivldAccrualInboundLocalService.dynamicQuery();
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
        return _ivldAccrualInboundLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldAccrualInboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ivldAccrualInboundLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldAccrualInboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ivldAccrualInboundLocalService.dynamicQuery(dynamicQuery,
            start, end, orderByComparator);
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
        return _ivldAccrualInboundLocalService.dynamicQueryCount(dynamicQuery);
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
        return _ivldAccrualInboundLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.parttwo.model.IvldAccrualInbound fetchIvldAccrualInbound(
        int ivldAccrualInboundSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldAccrualInboundLocalService.fetchIvldAccrualInbound(ivldAccrualInboundSid);
    }

    /**
    * Returns the ivld accrual inbound with the primary key.
    *
    * @param ivldAccrualInboundSid the primary key of the ivld accrual inbound
    * @return the ivld accrual inbound
    * @throws PortalException if a ivld accrual inbound with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.IvldAccrualInbound getIvldAccrualInbound(
        int ivldAccrualInboundSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldAccrualInboundLocalService.getIvldAccrualInbound(ivldAccrualInboundSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldAccrualInboundLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ivld accrual inbounds.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldAccrualInboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld accrual inbounds
    * @param end the upper bound of the range of ivld accrual inbounds (not inclusive)
    * @return the range of ivld accrual inbounds
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.parttwo.model.IvldAccrualInbound> getIvldAccrualInbounds(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldAccrualInboundLocalService.getIvldAccrualInbounds(start, end);
    }

    /**
    * Returns the number of ivld accrual inbounds.
    *
    * @return the number of ivld accrual inbounds
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getIvldAccrualInboundsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldAccrualInboundLocalService.getIvldAccrualInboundsCount();
    }

    /**
    * Updates the ivld accrual inbound in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ivldAccrualInbound the ivld accrual inbound
    * @return the ivld accrual inbound that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.IvldAccrualInbound updateIvldAccrualInbound(
        com.stpl.app.parttwo.model.IvldAccrualInbound ivldAccrualInbound)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldAccrualInboundLocalService.updateIvldAccrualInbound(ivldAccrualInbound);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _ivldAccrualInboundLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _ivldAccrualInboundLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _ivldAccrualInboundLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public IvldAccrualInboundLocalService getWrappedIvldAccrualInboundLocalService() {
        return _ivldAccrualInboundLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedIvldAccrualInboundLocalService(
        IvldAccrualInboundLocalService ivldAccrualInboundLocalService) {
        _ivldAccrualInboundLocalService = ivldAccrualInboundLocalService;
    }

    @Override
    public IvldAccrualInboundLocalService getWrappedService() {
        return _ivldAccrualInboundLocalService;
    }

    @Override
    public void setWrappedService(
        IvldAccrualInboundLocalService ivldAccrualInboundLocalService) {
        _ivldAccrualInboundLocalService = ivldAccrualInboundLocalService;
    }
}
