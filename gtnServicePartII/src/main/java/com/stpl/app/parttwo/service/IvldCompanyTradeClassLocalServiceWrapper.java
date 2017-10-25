package com.stpl.app.parttwo.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link IvldCompanyTradeClassLocalService}.
 *
 * @author
 * @see IvldCompanyTradeClassLocalService
 * @generated
 */
public class IvldCompanyTradeClassLocalServiceWrapper
    implements IvldCompanyTradeClassLocalService,
        ServiceWrapper<IvldCompanyTradeClassLocalService> {
    private IvldCompanyTradeClassLocalService _ivldCompanyTradeClassLocalService;

    public IvldCompanyTradeClassLocalServiceWrapper(
        IvldCompanyTradeClassLocalService ivldCompanyTradeClassLocalService) {
        _ivldCompanyTradeClassLocalService = ivldCompanyTradeClassLocalService;
    }

    /**
    * Adds the ivld company trade class to the database. Also notifies the appropriate model listeners.
    *
    * @param ivldCompanyTradeClass the ivld company trade class
    * @return the ivld company trade class that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.IvldCompanyTradeClass addIvldCompanyTradeClass(
        com.stpl.app.parttwo.model.IvldCompanyTradeClass ivldCompanyTradeClass)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldCompanyTradeClassLocalService.addIvldCompanyTradeClass(ivldCompanyTradeClass);
    }

    /**
    * Creates a new ivld company trade class with the primary key. Does not add the ivld company trade class to the database.
    *
    * @param ivldCompanyTradeClassSid the primary key for the new ivld company trade class
    * @return the new ivld company trade class
    */
    @Override
    public com.stpl.app.parttwo.model.IvldCompanyTradeClass createIvldCompanyTradeClass(
        int ivldCompanyTradeClassSid) {
        return _ivldCompanyTradeClassLocalService.createIvldCompanyTradeClass(ivldCompanyTradeClassSid);
    }

    /**
    * Deletes the ivld company trade class with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldCompanyTradeClassSid the primary key of the ivld company trade class
    * @return the ivld company trade class that was removed
    * @throws PortalException if a ivld company trade class with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.IvldCompanyTradeClass deleteIvldCompanyTradeClass(
        int ivldCompanyTradeClassSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldCompanyTradeClassLocalService.deleteIvldCompanyTradeClass(ivldCompanyTradeClassSid);
    }

    /**
    * Deletes the ivld company trade class from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldCompanyTradeClass the ivld company trade class
    * @return the ivld company trade class that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.IvldCompanyTradeClass deleteIvldCompanyTradeClass(
        com.stpl.app.parttwo.model.IvldCompanyTradeClass ivldCompanyTradeClass)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldCompanyTradeClassLocalService.deleteIvldCompanyTradeClass(ivldCompanyTradeClass);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _ivldCompanyTradeClassLocalService.dynamicQuery();
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
        return _ivldCompanyTradeClassLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ivldCompanyTradeClassLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ivldCompanyTradeClassLocalService.dynamicQuery(dynamicQuery,
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
        return _ivldCompanyTradeClassLocalService.dynamicQueryCount(dynamicQuery);
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
        return _ivldCompanyTradeClassLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.parttwo.model.IvldCompanyTradeClass fetchIvldCompanyTradeClass(
        int ivldCompanyTradeClassSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldCompanyTradeClassLocalService.fetchIvldCompanyTradeClass(ivldCompanyTradeClassSid);
    }

    /**
    * Returns the ivld company trade class with the primary key.
    *
    * @param ivldCompanyTradeClassSid the primary key of the ivld company trade class
    * @return the ivld company trade class
    * @throws PortalException if a ivld company trade class with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.IvldCompanyTradeClass getIvldCompanyTradeClass(
        int ivldCompanyTradeClassSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldCompanyTradeClassLocalService.getIvldCompanyTradeClass(ivldCompanyTradeClassSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldCompanyTradeClassLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ivld company trade classes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld company trade classes
    * @param end the upper bound of the range of ivld company trade classes (not inclusive)
    * @return the range of ivld company trade classes
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.parttwo.model.IvldCompanyTradeClass> getIvldCompanyTradeClasses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldCompanyTradeClassLocalService.getIvldCompanyTradeClasses(start,
            end);
    }

    /**
    * Returns the number of ivld company trade classes.
    *
    * @return the number of ivld company trade classes
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getIvldCompanyTradeClassesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldCompanyTradeClassLocalService.getIvldCompanyTradeClassesCount();
    }

    /**
    * Updates the ivld company trade class in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ivldCompanyTradeClass the ivld company trade class
    * @return the ivld company trade class that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.IvldCompanyTradeClass updateIvldCompanyTradeClass(
        com.stpl.app.parttwo.model.IvldCompanyTradeClass ivldCompanyTradeClass)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldCompanyTradeClassLocalService.updateIvldCompanyTradeClass(ivldCompanyTradeClass);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _ivldCompanyTradeClassLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _ivldCompanyTradeClassLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _ivldCompanyTradeClassLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public IvldCompanyTradeClassLocalService getWrappedIvldCompanyTradeClassLocalService() {
        return _ivldCompanyTradeClassLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedIvldCompanyTradeClassLocalService(
        IvldCompanyTradeClassLocalService ivldCompanyTradeClassLocalService) {
        _ivldCompanyTradeClassLocalService = ivldCompanyTradeClassLocalService;
    }

    @Override
    public IvldCompanyTradeClassLocalService getWrappedService() {
        return _ivldCompanyTradeClassLocalService;
    }

    @Override
    public void setWrappedService(
        IvldCompanyTradeClassLocalService ivldCompanyTradeClassLocalService) {
        _ivldCompanyTradeClassLocalService = ivldCompanyTradeClassLocalService;
    }
}
