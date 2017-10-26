package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link IvldGlBalanceLocalService}.
 *
 * @author
 * @see IvldGlBalanceLocalService
 * @generated
 */
public class IvldGlBalanceLocalServiceWrapper
    implements IvldGlBalanceLocalService,
        ServiceWrapper<IvldGlBalanceLocalService> {
    private IvldGlBalanceLocalService _ivldGlBalanceLocalService;

    public IvldGlBalanceLocalServiceWrapper(
        IvldGlBalanceLocalService ivldGlBalanceLocalService) {
        _ivldGlBalanceLocalService = ivldGlBalanceLocalService;
    }

    /**
    * Adds the ivld gl balance to the database. Also notifies the appropriate model listeners.
    *
    * @param ivldGlBalance the ivld gl balance
    * @return the ivld gl balance that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldGlBalance addIvldGlBalance(
        com.stpl.app.model.IvldGlBalance ivldGlBalance)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldGlBalanceLocalService.addIvldGlBalance(ivldGlBalance);
    }

    /**
    * Creates a new ivld gl balance with the primary key. Does not add the ivld gl balance to the database.
    *
    * @param ivldGlBalanceSid the primary key for the new ivld gl balance
    * @return the new ivld gl balance
    */
    @Override
    public com.stpl.app.model.IvldGlBalance createIvldGlBalance(
        int ivldGlBalanceSid) {
        return _ivldGlBalanceLocalService.createIvldGlBalance(ivldGlBalanceSid);
    }

    /**
    * Deletes the ivld gl balance with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldGlBalanceSid the primary key of the ivld gl balance
    * @return the ivld gl balance that was removed
    * @throws PortalException if a ivld gl balance with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldGlBalance deleteIvldGlBalance(
        int ivldGlBalanceSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldGlBalanceLocalService.deleteIvldGlBalance(ivldGlBalanceSid);
    }

    /**
    * Deletes the ivld gl balance from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldGlBalance the ivld gl balance
    * @return the ivld gl balance that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldGlBalance deleteIvldGlBalance(
        com.stpl.app.model.IvldGlBalance ivldGlBalance)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldGlBalanceLocalService.deleteIvldGlBalance(ivldGlBalance);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _ivldGlBalanceLocalService.dynamicQuery();
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
        return _ivldGlBalanceLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldGlBalanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ivldGlBalanceLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldGlBalanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ivldGlBalanceLocalService.dynamicQuery(dynamicQuery, start,
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
        return _ivldGlBalanceLocalService.dynamicQueryCount(dynamicQuery);
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
        return _ivldGlBalanceLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.IvldGlBalance fetchIvldGlBalance(
        int ivldGlBalanceSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldGlBalanceLocalService.fetchIvldGlBalance(ivldGlBalanceSid);
    }

    /**
    * Returns the ivld gl balance with the primary key.
    *
    * @param ivldGlBalanceSid the primary key of the ivld gl balance
    * @return the ivld gl balance
    * @throws PortalException if a ivld gl balance with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldGlBalance getIvldGlBalance(
        int ivldGlBalanceSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldGlBalanceLocalService.getIvldGlBalance(ivldGlBalanceSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldGlBalanceLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ivld gl balances.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldGlBalanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld gl balances
    * @param end the upper bound of the range of ivld gl balances (not inclusive)
    * @return the range of ivld gl balances
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.IvldGlBalance> getIvldGlBalances(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldGlBalanceLocalService.getIvldGlBalances(start, end);
    }

    /**
    * Returns the number of ivld gl balances.
    *
    * @return the number of ivld gl balances
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getIvldGlBalancesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldGlBalanceLocalService.getIvldGlBalancesCount();
    }

    /**
    * Updates the ivld gl balance in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ivldGlBalance the ivld gl balance
    * @return the ivld gl balance that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldGlBalance updateIvldGlBalance(
        com.stpl.app.model.IvldGlBalance ivldGlBalance)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldGlBalanceLocalService.updateIvldGlBalance(ivldGlBalance);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _ivldGlBalanceLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _ivldGlBalanceLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _ivldGlBalanceLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public IvldGlBalanceLocalService getWrappedIvldGlBalanceLocalService() {
        return _ivldGlBalanceLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedIvldGlBalanceLocalService(
        IvldGlBalanceLocalService ivldGlBalanceLocalService) {
        _ivldGlBalanceLocalService = ivldGlBalanceLocalService;
    }

    @Override
    public IvldGlBalanceLocalService getWrappedService() {
        return _ivldGlBalanceLocalService;
    }

    @Override
    public void setWrappedService(
        IvldGlBalanceLocalService ivldGlBalanceLocalService) {
        _ivldGlBalanceLocalService = ivldGlBalanceLocalService;
    }
}
