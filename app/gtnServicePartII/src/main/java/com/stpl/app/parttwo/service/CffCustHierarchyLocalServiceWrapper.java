package com.stpl.app.parttwo.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CffCustHierarchyLocalService}.
 *
 * @author
 * @see CffCustHierarchyLocalService
 * @generated
 */
public class CffCustHierarchyLocalServiceWrapper
    implements CffCustHierarchyLocalService,
        ServiceWrapper<CffCustHierarchyLocalService> {
    private CffCustHierarchyLocalService _cffCustHierarchyLocalService;

    public CffCustHierarchyLocalServiceWrapper(
        CffCustHierarchyLocalService cffCustHierarchyLocalService) {
        _cffCustHierarchyLocalService = cffCustHierarchyLocalService;
    }

    /**
    * Adds the cff cust hierarchy to the database. Also notifies the appropriate model listeners.
    *
    * @param cffCustHierarchy the cff cust hierarchy
    * @return the cff cust hierarchy that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.CffCustHierarchy addCffCustHierarchy(
        com.stpl.app.parttwo.model.CffCustHierarchy cffCustHierarchy)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cffCustHierarchyLocalService.addCffCustHierarchy(cffCustHierarchy);
    }

    /**
    * Creates a new cff cust hierarchy with the primary key. Does not add the cff cust hierarchy to the database.
    *
    * @param cffCustHierarchySid the primary key for the new cff cust hierarchy
    * @return the new cff cust hierarchy
    */
    @Override
    public com.stpl.app.parttwo.model.CffCustHierarchy createCffCustHierarchy(
        int cffCustHierarchySid) {
        return _cffCustHierarchyLocalService.createCffCustHierarchy(cffCustHierarchySid);
    }

    /**
    * Deletes the cff cust hierarchy with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param cffCustHierarchySid the primary key of the cff cust hierarchy
    * @return the cff cust hierarchy that was removed
    * @throws PortalException if a cff cust hierarchy with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.CffCustHierarchy deleteCffCustHierarchy(
        int cffCustHierarchySid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _cffCustHierarchyLocalService.deleteCffCustHierarchy(cffCustHierarchySid);
    }

    /**
    * Deletes the cff cust hierarchy from the database. Also notifies the appropriate model listeners.
    *
    * @param cffCustHierarchy the cff cust hierarchy
    * @return the cff cust hierarchy that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.CffCustHierarchy deleteCffCustHierarchy(
        com.stpl.app.parttwo.model.CffCustHierarchy cffCustHierarchy)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cffCustHierarchyLocalService.deleteCffCustHierarchy(cffCustHierarchy);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _cffCustHierarchyLocalService.dynamicQuery();
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
        return _cffCustHierarchyLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffCustHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _cffCustHierarchyLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffCustHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _cffCustHierarchyLocalService.dynamicQuery(dynamicQuery, start,
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
        return _cffCustHierarchyLocalService.dynamicQueryCount(dynamicQuery);
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
        return _cffCustHierarchyLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.parttwo.model.CffCustHierarchy fetchCffCustHierarchy(
        int cffCustHierarchySid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cffCustHierarchyLocalService.fetchCffCustHierarchy(cffCustHierarchySid);
    }

    /**
    * Returns the cff cust hierarchy with the primary key.
    *
    * @param cffCustHierarchySid the primary key of the cff cust hierarchy
    * @return the cff cust hierarchy
    * @throws PortalException if a cff cust hierarchy with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.CffCustHierarchy getCffCustHierarchy(
        int cffCustHierarchySid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _cffCustHierarchyLocalService.getCffCustHierarchy(cffCustHierarchySid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _cffCustHierarchyLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the cff cust hierarchies.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffCustHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cff cust hierarchies
    * @param end the upper bound of the range of cff cust hierarchies (not inclusive)
    * @return the range of cff cust hierarchies
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.parttwo.model.CffCustHierarchy> getCffCustHierarchies(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cffCustHierarchyLocalService.getCffCustHierarchies(start, end);
    }

    /**
    * Returns the number of cff cust hierarchies.
    *
    * @return the number of cff cust hierarchies
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getCffCustHierarchiesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cffCustHierarchyLocalService.getCffCustHierarchiesCount();
    }

    /**
    * Updates the cff cust hierarchy in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param cffCustHierarchy the cff cust hierarchy
    * @return the cff cust hierarchy that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.CffCustHierarchy updateCffCustHierarchy(
        com.stpl.app.parttwo.model.CffCustHierarchy cffCustHierarchy)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _cffCustHierarchyLocalService.updateCffCustHierarchy(cffCustHierarchy);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _cffCustHierarchyLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _cffCustHierarchyLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _cffCustHierarchyLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public CffCustHierarchyLocalService getWrappedCffCustHierarchyLocalService() {
        return _cffCustHierarchyLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedCffCustHierarchyLocalService(
        CffCustHierarchyLocalService cffCustHierarchyLocalService) {
        _cffCustHierarchyLocalService = cffCustHierarchyLocalService;
    }

    @Override
    public CffCustHierarchyLocalService getWrappedService() {
        return _cffCustHierarchyLocalService;
    }

    @Override
    public void setWrappedService(
        CffCustHierarchyLocalService cffCustHierarchyLocalService) {
        _cffCustHierarchyLocalService = cffCustHierarchyLocalService;
    }
}
