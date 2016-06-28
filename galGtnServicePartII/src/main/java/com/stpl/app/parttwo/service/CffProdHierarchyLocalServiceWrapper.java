package com.stpl.app.parttwo.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CffProdHierarchyLocalService}.
 *
 * @author
 * @see CffProdHierarchyLocalService
 * @generated
 */
public class CffProdHierarchyLocalServiceWrapper
    implements CffProdHierarchyLocalService,
        ServiceWrapper<CffProdHierarchyLocalService> {
    private CffProdHierarchyLocalService _cffProdHierarchyLocalService;

    public CffProdHierarchyLocalServiceWrapper(
        CffProdHierarchyLocalService cffProdHierarchyLocalService) {
        _cffProdHierarchyLocalService = cffProdHierarchyLocalService;
    }

    /**
    * Adds the cff prod hierarchy to the database. Also notifies the appropriate model listeners.
    *
    * @param cffProdHierarchy the cff prod hierarchy
    * @return the cff prod hierarchy that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.CffProdHierarchy addCffProdHierarchy(
        com.stpl.app.parttwo.model.CffProdHierarchy cffProdHierarchy)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cffProdHierarchyLocalService.addCffProdHierarchy(cffProdHierarchy);
    }

    /**
    * Creates a new cff prod hierarchy with the primary key. Does not add the cff prod hierarchy to the database.
    *
    * @param cffProdHierarchySid the primary key for the new cff prod hierarchy
    * @return the new cff prod hierarchy
    */
    @Override
    public com.stpl.app.parttwo.model.CffProdHierarchy createCffProdHierarchy(
        int cffProdHierarchySid) {
        return _cffProdHierarchyLocalService.createCffProdHierarchy(cffProdHierarchySid);
    }

    /**
    * Deletes the cff prod hierarchy with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param cffProdHierarchySid the primary key of the cff prod hierarchy
    * @return the cff prod hierarchy that was removed
    * @throws PortalException if a cff prod hierarchy with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.CffProdHierarchy deleteCffProdHierarchy(
        int cffProdHierarchySid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _cffProdHierarchyLocalService.deleteCffProdHierarchy(cffProdHierarchySid);
    }

    /**
    * Deletes the cff prod hierarchy from the database. Also notifies the appropriate model listeners.
    *
    * @param cffProdHierarchy the cff prod hierarchy
    * @return the cff prod hierarchy that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.CffProdHierarchy deleteCffProdHierarchy(
        com.stpl.app.parttwo.model.CffProdHierarchy cffProdHierarchy)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cffProdHierarchyLocalService.deleteCffProdHierarchy(cffProdHierarchy);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _cffProdHierarchyLocalService.dynamicQuery();
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
        return _cffProdHierarchyLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffProdHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _cffProdHierarchyLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffProdHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _cffProdHierarchyLocalService.dynamicQuery(dynamicQuery, start,
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
        return _cffProdHierarchyLocalService.dynamicQueryCount(dynamicQuery);
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
        return _cffProdHierarchyLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.parttwo.model.CffProdHierarchy fetchCffProdHierarchy(
        int cffProdHierarchySid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cffProdHierarchyLocalService.fetchCffProdHierarchy(cffProdHierarchySid);
    }

    /**
    * Returns the cff prod hierarchy with the primary key.
    *
    * @param cffProdHierarchySid the primary key of the cff prod hierarchy
    * @return the cff prod hierarchy
    * @throws PortalException if a cff prod hierarchy with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.CffProdHierarchy getCffProdHierarchy(
        int cffProdHierarchySid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _cffProdHierarchyLocalService.getCffProdHierarchy(cffProdHierarchySid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _cffProdHierarchyLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the cff prod hierarchies.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffProdHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cff prod hierarchies
    * @param end the upper bound of the range of cff prod hierarchies (not inclusive)
    * @return the range of cff prod hierarchies
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.parttwo.model.CffProdHierarchy> getCffProdHierarchies(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cffProdHierarchyLocalService.getCffProdHierarchies(start, end);
    }

    /**
    * Returns the number of cff prod hierarchies.
    *
    * @return the number of cff prod hierarchies
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getCffProdHierarchiesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cffProdHierarchyLocalService.getCffProdHierarchiesCount();
    }

    /**
    * Updates the cff prod hierarchy in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param cffProdHierarchy the cff prod hierarchy
    * @return the cff prod hierarchy that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.CffProdHierarchy updateCffProdHierarchy(
        com.stpl.app.parttwo.model.CffProdHierarchy cffProdHierarchy)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cffProdHierarchyLocalService.updateCffProdHierarchy(cffProdHierarchy);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _cffProdHierarchyLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _cffProdHierarchyLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _cffProdHierarchyLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public CffProdHierarchyLocalService getWrappedCffProdHierarchyLocalService() {
        return _cffProdHierarchyLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedCffProdHierarchyLocalService(
        CffProdHierarchyLocalService cffProdHierarchyLocalService) {
        _cffProdHierarchyLocalService = cffProdHierarchyLocalService;
    }

    @Override
    public CffProdHierarchyLocalService getWrappedService() {
        return _cffProdHierarchyLocalService;
    }

    @Override
    public void setWrappedService(
        CffProdHierarchyLocalService cffProdHierarchyLocalService) {
        _cffProdHierarchyLocalService = cffProdHierarchyLocalService;
    }
}
