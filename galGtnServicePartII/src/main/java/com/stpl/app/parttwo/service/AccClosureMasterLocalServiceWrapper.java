package com.stpl.app.parttwo.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AccClosureMasterLocalService}.
 *
 * @author
 * @see AccClosureMasterLocalService
 * @generated
 */
public class AccClosureMasterLocalServiceWrapper
    implements AccClosureMasterLocalService,
        ServiceWrapper<AccClosureMasterLocalService> {
    private AccClosureMasterLocalService _accClosureMasterLocalService;

    public AccClosureMasterLocalServiceWrapper(
        AccClosureMasterLocalService accClosureMasterLocalService) {
        _accClosureMasterLocalService = accClosureMasterLocalService;
    }

    /**
    * Adds the acc closure master to the database. Also notifies the appropriate model listeners.
    *
    * @param accClosureMaster the acc closure master
    * @return the acc closure master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.AccClosureMaster addAccClosureMaster(
        com.stpl.app.parttwo.model.AccClosureMaster accClosureMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _accClosureMasterLocalService.addAccClosureMaster(accClosureMaster);
    }

    /**
    * Creates a new acc closure master with the primary key. Does not add the acc closure master to the database.
    *
    * @param accClosureMasterSid the primary key for the new acc closure master
    * @return the new acc closure master
    */
    @Override
    public com.stpl.app.parttwo.model.AccClosureMaster createAccClosureMaster(
        int accClosureMasterSid) {
        return _accClosureMasterLocalService.createAccClosureMaster(accClosureMasterSid);
    }

    /**
    * Deletes the acc closure master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param accClosureMasterSid the primary key of the acc closure master
    * @return the acc closure master that was removed
    * @throws PortalException if a acc closure master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.AccClosureMaster deleteAccClosureMaster(
        int accClosureMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _accClosureMasterLocalService.deleteAccClosureMaster(accClosureMasterSid);
    }

    /**
    * Deletes the acc closure master from the database. Also notifies the appropriate model listeners.
    *
    * @param accClosureMaster the acc closure master
    * @return the acc closure master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.AccClosureMaster deleteAccClosureMaster(
        com.stpl.app.parttwo.model.AccClosureMaster accClosureMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _accClosureMasterLocalService.deleteAccClosureMaster(accClosureMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _accClosureMasterLocalService.dynamicQuery();
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
        return _accClosureMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AccClosureMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _accClosureMasterLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AccClosureMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _accClosureMasterLocalService.dynamicQuery(dynamicQuery, start,
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
        return _accClosureMasterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _accClosureMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.parttwo.model.AccClosureMaster fetchAccClosureMaster(
        int accClosureMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _accClosureMasterLocalService.fetchAccClosureMaster(accClosureMasterSid);
    }

    /**
    * Returns the acc closure master with the primary key.
    *
    * @param accClosureMasterSid the primary key of the acc closure master
    * @return the acc closure master
    * @throws PortalException if a acc closure master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.AccClosureMaster getAccClosureMaster(
        int accClosureMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _accClosureMasterLocalService.getAccClosureMaster(accClosureMasterSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _accClosureMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the acc closure masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AccClosureMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of acc closure masters
    * @param end the upper bound of the range of acc closure masters (not inclusive)
    * @return the range of acc closure masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.parttwo.model.AccClosureMaster> getAccClosureMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _accClosureMasterLocalService.getAccClosureMasters(start, end);
    }

    /**
    * Returns the number of acc closure masters.
    *
    * @return the number of acc closure masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getAccClosureMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _accClosureMasterLocalService.getAccClosureMastersCount();
    }

    /**
    * Updates the acc closure master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param accClosureMaster the acc closure master
    * @return the acc closure master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.AccClosureMaster updateAccClosureMaster(
        com.stpl.app.parttwo.model.AccClosureMaster accClosureMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _accClosureMasterLocalService.updateAccClosureMaster(accClosureMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _accClosureMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _accClosureMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _accClosureMasterLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    @Override
    public java.lang.Object executeSelectQuery(java.util.List input,
        java.lang.String queryName, java.lang.String quaryName2) {
        return _accClosureMasterLocalService.executeSelectQuery(input,
            queryName, quaryName2);
    }

    @Override
    public java.lang.Boolean executeUpdateQuery(java.util.List input,
        java.lang.String queryName) {
        return _accClosureMasterLocalService.executeUpdateQuery(input, queryName);
    }

    @Override
    public java.lang.String getQuery(java.util.List input,
        java.lang.String queryName) {
        return _accClosureMasterLocalService.getQuery(input, queryName);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public AccClosureMasterLocalService getWrappedAccClosureMasterLocalService() {
        return _accClosureMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedAccClosureMasterLocalService(
        AccClosureMasterLocalService accClosureMasterLocalService) {
        _accClosureMasterLocalService = accClosureMasterLocalService;
    }

    @Override
    public AccClosureMasterLocalService getWrappedService() {
        return _accClosureMasterLocalService;
    }

    @Override
    public void setWrappedService(
        AccClosureMasterLocalService accClosureMasterLocalService) {
        _accClosureMasterLocalService = accClosureMasterLocalService;
    }
}
