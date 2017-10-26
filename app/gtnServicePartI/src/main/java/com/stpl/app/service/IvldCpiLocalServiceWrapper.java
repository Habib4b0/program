package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link IvldCpiLocalService}.
 *
 * @author
 * @see IvldCpiLocalService
 * @generated
 */
public class IvldCpiLocalServiceWrapper implements IvldCpiLocalService,
    ServiceWrapper<IvldCpiLocalService> {
    private IvldCpiLocalService _ivldCpiLocalService;

    public IvldCpiLocalServiceWrapper(IvldCpiLocalService ivldCpiLocalService) {
        _ivldCpiLocalService = ivldCpiLocalService;
    }

    /**
    * Adds the ivld cpi to the database. Also notifies the appropriate model listeners.
    *
    * @param ivldCpi the ivld cpi
    * @return the ivld cpi that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldCpi addIvldCpi(
        com.stpl.app.model.IvldCpi ivldCpi)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldCpiLocalService.addIvldCpi(ivldCpi);
    }

    /**
    * Creates a new ivld cpi with the primary key. Does not add the ivld cpi to the database.
    *
    * @param ivldCpiSid the primary key for the new ivld cpi
    * @return the new ivld cpi
    */
    @Override
    public com.stpl.app.model.IvldCpi createIvldCpi(int ivldCpiSid) {
        return _ivldCpiLocalService.createIvldCpi(ivldCpiSid);
    }

    /**
    * Deletes the ivld cpi with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldCpiSid the primary key of the ivld cpi
    * @return the ivld cpi that was removed
    * @throws PortalException if a ivld cpi with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldCpi deleteIvldCpi(int ivldCpiSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldCpiLocalService.deleteIvldCpi(ivldCpiSid);
    }

    /**
    * Deletes the ivld cpi from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldCpi the ivld cpi
    * @return the ivld cpi that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldCpi deleteIvldCpi(
        com.stpl.app.model.IvldCpi ivldCpi)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldCpiLocalService.deleteIvldCpi(ivldCpi);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _ivldCpiLocalService.dynamicQuery();
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
        return _ivldCpiLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldCpiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ivldCpiLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldCpiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ivldCpiLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _ivldCpiLocalService.dynamicQueryCount(dynamicQuery);
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
        return _ivldCpiLocalService.dynamicQueryCount(dynamicQuery, projection);
    }

    @Override
    public com.stpl.app.model.IvldCpi fetchIvldCpi(int ivldCpiSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldCpiLocalService.fetchIvldCpi(ivldCpiSid);
    }

    /**
    * Returns the ivld cpi with the primary key.
    *
    * @param ivldCpiSid the primary key of the ivld cpi
    * @return the ivld cpi
    * @throws PortalException if a ivld cpi with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldCpi getIvldCpi(int ivldCpiSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldCpiLocalService.getIvldCpi(ivldCpiSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldCpiLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ivld cpis.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldCpiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld cpis
    * @param end the upper bound of the range of ivld cpis (not inclusive)
    * @return the range of ivld cpis
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.IvldCpi> getIvldCpis(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldCpiLocalService.getIvldCpis(start, end);
    }

    /**
    * Returns the number of ivld cpis.
    *
    * @return the number of ivld cpis
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getIvldCpisCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldCpiLocalService.getIvldCpisCount();
    }

    /**
    * Updates the ivld cpi in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ivldCpi the ivld cpi
    * @return the ivld cpi that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldCpi updateIvldCpi(
        com.stpl.app.model.IvldCpi ivldCpi)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldCpiLocalService.updateIvldCpi(ivldCpi);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _ivldCpiLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _ivldCpiLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _ivldCpiLocalService.invokeMethod(name, parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public IvldCpiLocalService getWrappedIvldCpiLocalService() {
        return _ivldCpiLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedIvldCpiLocalService(
        IvldCpiLocalService ivldCpiLocalService) {
        _ivldCpiLocalService = ivldCpiLocalService;
    }

    @Override
    public IvldCpiLocalService getWrappedService() {
        return _ivldCpiLocalService;
    }

    @Override
    public void setWrappedService(IvldCpiLocalService ivldCpiLocalService) {
        _ivldCpiLocalService = ivldCpiLocalService;
    }
}
