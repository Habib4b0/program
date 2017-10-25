package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ReturnsMasterLocalService}.
 *
 * @author
 * @see ReturnsMasterLocalService
 * @generated
 */
public class ReturnsMasterLocalServiceWrapper
    implements ReturnsMasterLocalService,
        ServiceWrapper<ReturnsMasterLocalService> {
    private ReturnsMasterLocalService _returnsMasterLocalService;

    public ReturnsMasterLocalServiceWrapper(
        ReturnsMasterLocalService returnsMasterLocalService) {
        _returnsMasterLocalService = returnsMasterLocalService;
    }

    /**
    * Adds the returns master to the database. Also notifies the appropriate model listeners.
    *
    * @param returnsMaster the returns master
    * @return the returns master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ReturnsMaster addReturnsMaster(
        com.stpl.app.model.ReturnsMaster returnsMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _returnsMasterLocalService.addReturnsMaster(returnsMaster);
    }

    /**
    * Creates a new returns master with the primary key. Does not add the returns master to the database.
    *
    * @param returnsMasterSid the primary key for the new returns master
    * @return the new returns master
    */
    @Override
    public com.stpl.app.model.ReturnsMaster createReturnsMaster(
        int returnsMasterSid) {
        return _returnsMasterLocalService.createReturnsMaster(returnsMasterSid);
    }

    /**
    * Deletes the returns master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param returnsMasterSid the primary key of the returns master
    * @return the returns master that was removed
    * @throws PortalException if a returns master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ReturnsMaster deleteReturnsMaster(
        int returnsMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _returnsMasterLocalService.deleteReturnsMaster(returnsMasterSid);
    }

    /**
    * Deletes the returns master from the database. Also notifies the appropriate model listeners.
    *
    * @param returnsMaster the returns master
    * @return the returns master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ReturnsMaster deleteReturnsMaster(
        com.stpl.app.model.ReturnsMaster returnsMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _returnsMasterLocalService.deleteReturnsMaster(returnsMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _returnsMasterLocalService.dynamicQuery();
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
        return _returnsMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ReturnsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _returnsMasterLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ReturnsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _returnsMasterLocalService.dynamicQuery(dynamicQuery, start,
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
        return _returnsMasterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _returnsMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.ReturnsMaster fetchReturnsMaster(
        int returnsMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _returnsMasterLocalService.fetchReturnsMaster(returnsMasterSid);
    }

    /**
    * Returns the returns master with the primary key.
    *
    * @param returnsMasterSid the primary key of the returns master
    * @return the returns master
    * @throws PortalException if a returns master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ReturnsMaster getReturnsMaster(
        int returnsMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _returnsMasterLocalService.getReturnsMaster(returnsMasterSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _returnsMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the returns masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ReturnsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of returns masters
    * @param end the upper bound of the range of returns masters (not inclusive)
    * @return the range of returns masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.ReturnsMaster> getReturnsMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _returnsMasterLocalService.getReturnsMasters(start, end);
    }

    /**
    * Returns the number of returns masters.
    *
    * @return the number of returns masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getReturnsMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _returnsMasterLocalService.getReturnsMastersCount();
    }

    /**
    * Updates the returns master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param returnsMaster the returns master
    * @return the returns master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ReturnsMaster updateReturnsMaster(
        com.stpl.app.model.ReturnsMaster returnsMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _returnsMasterLocalService.updateReturnsMaster(returnsMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _returnsMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _returnsMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _returnsMasterLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ReturnsMasterLocalService getWrappedReturnsMasterLocalService() {
        return _returnsMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedReturnsMasterLocalService(
        ReturnsMasterLocalService returnsMasterLocalService) {
        _returnsMasterLocalService = returnsMasterLocalService;
    }

    @Override
    public ReturnsMasterLocalService getWrappedService() {
        return _returnsMasterLocalService;
    }

    @Override
    public void setWrappedService(
        ReturnsMasterLocalService returnsMasterLocalService) {
        _returnsMasterLocalService = returnsMasterLocalService;
    }
}
