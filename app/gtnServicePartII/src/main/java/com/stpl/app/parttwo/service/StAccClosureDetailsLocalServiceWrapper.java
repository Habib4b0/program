package com.stpl.app.parttwo.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link StAccClosureDetailsLocalService}.
 *
 * @author
 * @see StAccClosureDetailsLocalService
 * @generated
 */
public class StAccClosureDetailsLocalServiceWrapper
    implements StAccClosureDetailsLocalService,
        ServiceWrapper<StAccClosureDetailsLocalService> {
    private StAccClosureDetailsLocalService _stAccClosureDetailsLocalService;

    public StAccClosureDetailsLocalServiceWrapper(
        StAccClosureDetailsLocalService stAccClosureDetailsLocalService) {
        _stAccClosureDetailsLocalService = stAccClosureDetailsLocalService;
    }

    /**
    * Adds the st acc closure details to the database. Also notifies the appropriate model listeners.
    *
    * @param stAccClosureDetails the st acc closure details
    * @return the st acc closure details that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.StAccClosureDetails addStAccClosureDetails(
        com.stpl.app.parttwo.model.StAccClosureDetails stAccClosureDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stAccClosureDetailsLocalService.addStAccClosureDetails(stAccClosureDetails);
    }

    /**
    * Creates a new st acc closure details with the primary key. Does not add the st acc closure details to the database.
    *
    * @param accClosureMasterSid the primary key for the new st acc closure details
    * @return the new st acc closure details
    */
    @Override
    public com.stpl.app.parttwo.model.StAccClosureDetails createStAccClosureDetails(
        int accClosureMasterSid) {
        return _stAccClosureDetailsLocalService.createStAccClosureDetails(accClosureMasterSid);
    }

    /**
    * Deletes the st acc closure details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param accClosureMasterSid the primary key of the st acc closure details
    * @return the st acc closure details that was removed
    * @throws PortalException if a st acc closure details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.StAccClosureDetails deleteStAccClosureDetails(
        int accClosureMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stAccClosureDetailsLocalService.deleteStAccClosureDetails(accClosureMasterSid);
    }

    /**
    * Deletes the st acc closure details from the database. Also notifies the appropriate model listeners.
    *
    * @param stAccClosureDetails the st acc closure details
    * @return the st acc closure details that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.StAccClosureDetails deleteStAccClosureDetails(
        com.stpl.app.parttwo.model.StAccClosureDetails stAccClosureDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stAccClosureDetailsLocalService.deleteStAccClosureDetails(stAccClosureDetails);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _stAccClosureDetailsLocalService.dynamicQuery();
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
        return _stAccClosureDetailsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StAccClosureDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _stAccClosureDetailsLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StAccClosureDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _stAccClosureDetailsLocalService.dynamicQuery(dynamicQuery,
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
        return _stAccClosureDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _stAccClosureDetailsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.parttwo.model.StAccClosureDetails fetchStAccClosureDetails(
        int accClosureMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stAccClosureDetailsLocalService.fetchStAccClosureDetails(accClosureMasterSid);
    }

    /**
    * Returns the st acc closure details with the primary key.
    *
    * @param accClosureMasterSid the primary key of the st acc closure details
    * @return the st acc closure details
    * @throws PortalException if a st acc closure details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.StAccClosureDetails getStAccClosureDetails(
        int accClosureMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stAccClosureDetailsLocalService.getStAccClosureDetails(accClosureMasterSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stAccClosureDetailsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the st acc closure detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StAccClosureDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st acc closure detailses
    * @param end the upper bound of the range of st acc closure detailses (not inclusive)
    * @return the range of st acc closure detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.parttwo.model.StAccClosureDetails> getStAccClosureDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stAccClosureDetailsLocalService.getStAccClosureDetailses(start,
            end);
    }

    /**
    * Returns the number of st acc closure detailses.
    *
    * @return the number of st acc closure detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getStAccClosureDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stAccClosureDetailsLocalService.getStAccClosureDetailsesCount();
    }

    /**
    * Updates the st acc closure details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param stAccClosureDetails the st acc closure details
    * @return the st acc closure details that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.StAccClosureDetails updateStAccClosureDetails(
        com.stpl.app.parttwo.model.StAccClosureDetails stAccClosureDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stAccClosureDetailsLocalService.updateStAccClosureDetails(stAccClosureDetails);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _stAccClosureDetailsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _stAccClosureDetailsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _stAccClosureDetailsLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public StAccClosureDetailsLocalService getWrappedStAccClosureDetailsLocalService() {
        return _stAccClosureDetailsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedStAccClosureDetailsLocalService(
        StAccClosureDetailsLocalService stAccClosureDetailsLocalService) {
        _stAccClosureDetailsLocalService = stAccClosureDetailsLocalService;
    }

    @Override
    public StAccClosureDetailsLocalService getWrappedService() {
        return _stAccClosureDetailsLocalService;
    }

    @Override
    public void setWrappedService(
        StAccClosureDetailsLocalService stAccClosureDetailsLocalService) {
        _stAccClosureDetailsLocalService = stAccClosureDetailsLocalService;
    }
}
