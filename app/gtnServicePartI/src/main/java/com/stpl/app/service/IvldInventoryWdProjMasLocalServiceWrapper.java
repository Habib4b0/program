package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link IvldInventoryWdProjMasLocalService}.
 *
 * @author
 * @see IvldInventoryWdProjMasLocalService
 * @generated
 */
public class IvldInventoryWdProjMasLocalServiceWrapper
    implements IvldInventoryWdProjMasLocalService,
        ServiceWrapper<IvldInventoryWdProjMasLocalService> {
    private IvldInventoryWdProjMasLocalService _ivldInventoryWdProjMasLocalService;

    public IvldInventoryWdProjMasLocalServiceWrapper(
        IvldInventoryWdProjMasLocalService ivldInventoryWdProjMasLocalService) {
        _ivldInventoryWdProjMasLocalService = ivldInventoryWdProjMasLocalService;
    }

    /**
    * Adds the ivld inventory wd proj mas to the database. Also notifies the appropriate model listeners.
    *
    * @param ivldInventoryWdProjMas the ivld inventory wd proj mas
    * @return the ivld inventory wd proj mas that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldInventoryWdProjMas addIvldInventoryWdProjMas(
        com.stpl.app.model.IvldInventoryWdProjMas ivldInventoryWdProjMas)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldInventoryWdProjMasLocalService.addIvldInventoryWdProjMas(ivldInventoryWdProjMas);
    }

    /**
    * Creates a new ivld inventory wd proj mas with the primary key. Does not add the ivld inventory wd proj mas to the database.
    *
    * @param ivldInventoryWdProjMasSid the primary key for the new ivld inventory wd proj mas
    * @return the new ivld inventory wd proj mas
    */
    @Override
    public com.stpl.app.model.IvldInventoryWdProjMas createIvldInventoryWdProjMas(
        int ivldInventoryWdProjMasSid) {
        return _ivldInventoryWdProjMasLocalService.createIvldInventoryWdProjMas(ivldInventoryWdProjMasSid);
    }

    /**
    * Deletes the ivld inventory wd proj mas with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldInventoryWdProjMasSid the primary key of the ivld inventory wd proj mas
    * @return the ivld inventory wd proj mas that was removed
    * @throws PortalException if a ivld inventory wd proj mas with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldInventoryWdProjMas deleteIvldInventoryWdProjMas(
        int ivldInventoryWdProjMasSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldInventoryWdProjMasLocalService.deleteIvldInventoryWdProjMas(ivldInventoryWdProjMasSid);
    }

    /**
    * Deletes the ivld inventory wd proj mas from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldInventoryWdProjMas the ivld inventory wd proj mas
    * @return the ivld inventory wd proj mas that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldInventoryWdProjMas deleteIvldInventoryWdProjMas(
        com.stpl.app.model.IvldInventoryWdProjMas ivldInventoryWdProjMas)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldInventoryWdProjMasLocalService.deleteIvldInventoryWdProjMas(ivldInventoryWdProjMas);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _ivldInventoryWdProjMasLocalService.dynamicQuery();
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
        return _ivldInventoryWdProjMasLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldInventoryWdProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ivldInventoryWdProjMasLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldInventoryWdProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ivldInventoryWdProjMasLocalService.dynamicQuery(dynamicQuery,
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
        return _ivldInventoryWdProjMasLocalService.dynamicQueryCount(dynamicQuery);
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
        return _ivldInventoryWdProjMasLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.IvldInventoryWdProjMas fetchIvldInventoryWdProjMas(
        int ivldInventoryWdProjMasSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldInventoryWdProjMasLocalService.fetchIvldInventoryWdProjMas(ivldInventoryWdProjMasSid);
    }

    /**
    * Returns the ivld inventory wd proj mas with the primary key.
    *
    * @param ivldInventoryWdProjMasSid the primary key of the ivld inventory wd proj mas
    * @return the ivld inventory wd proj mas
    * @throws PortalException if a ivld inventory wd proj mas with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldInventoryWdProjMas getIvldInventoryWdProjMas(
        int ivldInventoryWdProjMasSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldInventoryWdProjMasLocalService.getIvldInventoryWdProjMas(ivldInventoryWdProjMasSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldInventoryWdProjMasLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ivld inventory wd proj mases.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldInventoryWdProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld inventory wd proj mases
    * @param end the upper bound of the range of ivld inventory wd proj mases (not inclusive)
    * @return the range of ivld inventory wd proj mases
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.IvldInventoryWdProjMas> getIvldInventoryWdProjMases(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldInventoryWdProjMasLocalService.getIvldInventoryWdProjMases(start,
            end);
    }

    /**
    * Returns the number of ivld inventory wd proj mases.
    *
    * @return the number of ivld inventory wd proj mases
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getIvldInventoryWdProjMasesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldInventoryWdProjMasLocalService.getIvldInventoryWdProjMasesCount();
    }

    /**
    * Updates the ivld inventory wd proj mas in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ivldInventoryWdProjMas the ivld inventory wd proj mas
    * @return the ivld inventory wd proj mas that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldInventoryWdProjMas updateIvldInventoryWdProjMas(
        com.stpl.app.model.IvldInventoryWdProjMas ivldInventoryWdProjMas)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldInventoryWdProjMasLocalService.updateIvldInventoryWdProjMas(ivldInventoryWdProjMas);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _ivldInventoryWdProjMasLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _ivldInventoryWdProjMasLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _ivldInventoryWdProjMasLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public IvldInventoryWdProjMasLocalService getWrappedIvldInventoryWdProjMasLocalService() {
        return _ivldInventoryWdProjMasLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedIvldInventoryWdProjMasLocalService(
        IvldInventoryWdProjMasLocalService ivldInventoryWdProjMasLocalService) {
        _ivldInventoryWdProjMasLocalService = ivldInventoryWdProjMasLocalService;
    }

    @Override
    public IvldInventoryWdProjMasLocalService getWrappedService() {
        return _ivldInventoryWdProjMasLocalService;
    }

    @Override
    public void setWrappedService(
        IvldInventoryWdProjMasLocalService ivldInventoryWdProjMasLocalService) {
        _ivldInventoryWdProjMasLocalService = ivldInventoryWdProjMasLocalService;
    }
}
