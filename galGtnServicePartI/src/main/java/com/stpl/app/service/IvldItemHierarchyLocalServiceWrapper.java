package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link IvldItemHierarchyLocalService}.
 *
 * @author
 * @see IvldItemHierarchyLocalService
 * @generated
 */
public class IvldItemHierarchyLocalServiceWrapper
    implements IvldItemHierarchyLocalService,
        ServiceWrapper<IvldItemHierarchyLocalService> {
    private IvldItemHierarchyLocalService _ivldItemHierarchyLocalService;

    public IvldItemHierarchyLocalServiceWrapper(
        IvldItemHierarchyLocalService ivldItemHierarchyLocalService) {
        _ivldItemHierarchyLocalService = ivldItemHierarchyLocalService;
    }

    /**
    * Adds the ivld item hierarchy to the database. Also notifies the appropriate model listeners.
    *
    * @param ivldItemHierarchy the ivld item hierarchy
    * @return the ivld item hierarchy that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldItemHierarchy addIvldItemHierarchy(
        com.stpl.app.model.IvldItemHierarchy ivldItemHierarchy)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldItemHierarchyLocalService.addIvldItemHierarchy(ivldItemHierarchy);
    }

    /**
    * Creates a new ivld item hierarchy with the primary key. Does not add the ivld item hierarchy to the database.
    *
    * @param ivldItemHierarchySid the primary key for the new ivld item hierarchy
    * @return the new ivld item hierarchy
    */
    @Override
    public com.stpl.app.model.IvldItemHierarchy createIvldItemHierarchy(
        int ivldItemHierarchySid) {
        return _ivldItemHierarchyLocalService.createIvldItemHierarchy(ivldItemHierarchySid);
    }

    /**
    * Deletes the ivld item hierarchy with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldItemHierarchySid the primary key of the ivld item hierarchy
    * @return the ivld item hierarchy that was removed
    * @throws PortalException if a ivld item hierarchy with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldItemHierarchy deleteIvldItemHierarchy(
        int ivldItemHierarchySid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldItemHierarchyLocalService.deleteIvldItemHierarchy(ivldItemHierarchySid);
    }

    /**
    * Deletes the ivld item hierarchy from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldItemHierarchy the ivld item hierarchy
    * @return the ivld item hierarchy that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldItemHierarchy deleteIvldItemHierarchy(
        com.stpl.app.model.IvldItemHierarchy ivldItemHierarchy)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldItemHierarchyLocalService.deleteIvldItemHierarchy(ivldItemHierarchy);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _ivldItemHierarchyLocalService.dynamicQuery();
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
        return _ivldItemHierarchyLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldItemHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ivldItemHierarchyLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldItemHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ivldItemHierarchyLocalService.dynamicQuery(dynamicQuery, start,
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
        return _ivldItemHierarchyLocalService.dynamicQueryCount(dynamicQuery);
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
        return _ivldItemHierarchyLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.IvldItemHierarchy fetchIvldItemHierarchy(
        int ivldItemHierarchySid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldItemHierarchyLocalService.fetchIvldItemHierarchy(ivldItemHierarchySid);
    }

    /**
    * Returns the ivld item hierarchy with the primary key.
    *
    * @param ivldItemHierarchySid the primary key of the ivld item hierarchy
    * @return the ivld item hierarchy
    * @throws PortalException if a ivld item hierarchy with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldItemHierarchy getIvldItemHierarchy(
        int ivldItemHierarchySid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldItemHierarchyLocalService.getIvldItemHierarchy(ivldItemHierarchySid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldItemHierarchyLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ivld item hierarchies.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldItemHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld item hierarchies
    * @param end the upper bound of the range of ivld item hierarchies (not inclusive)
    * @return the range of ivld item hierarchies
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.IvldItemHierarchy> getIvldItemHierarchies(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldItemHierarchyLocalService.getIvldItemHierarchies(start, end);
    }

    /**
    * Returns the number of ivld item hierarchies.
    *
    * @return the number of ivld item hierarchies
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getIvldItemHierarchiesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldItemHierarchyLocalService.getIvldItemHierarchiesCount();
    }

    /**
    * Updates the ivld item hierarchy in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ivldItemHierarchy the ivld item hierarchy
    * @return the ivld item hierarchy that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldItemHierarchy updateIvldItemHierarchy(
        com.stpl.app.model.IvldItemHierarchy ivldItemHierarchy)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldItemHierarchyLocalService.updateIvldItemHierarchy(ivldItemHierarchy);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _ivldItemHierarchyLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _ivldItemHierarchyLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _ivldItemHierarchyLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public IvldItemHierarchyLocalService getWrappedIvldItemHierarchyLocalService() {
        return _ivldItemHierarchyLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedIvldItemHierarchyLocalService(
        IvldItemHierarchyLocalService ivldItemHierarchyLocalService) {
        _ivldItemHierarchyLocalService = ivldItemHierarchyLocalService;
    }

    @Override
    public IvldItemHierarchyLocalService getWrappedService() {
        return _ivldItemHierarchyLocalService;
    }

    @Override
    public void setWrappedService(
        IvldItemHierarchyLocalService ivldItemHierarchyLocalService) {
        _ivldItemHierarchyLocalService = ivldItemHierarchyLocalService;
    }
}
