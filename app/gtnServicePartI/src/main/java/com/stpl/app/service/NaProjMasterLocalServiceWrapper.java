package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link NaProjMasterLocalService}.
 *
 * @author
 * @see NaProjMasterLocalService
 * @generated
 */
public class NaProjMasterLocalServiceWrapper implements NaProjMasterLocalService,
    ServiceWrapper<NaProjMasterLocalService> {
    private NaProjMasterLocalService _naProjMasterLocalService;

    public NaProjMasterLocalServiceWrapper(
        NaProjMasterLocalService naProjMasterLocalService) {
        _naProjMasterLocalService = naProjMasterLocalService;
    }

    /**
    * Adds the na proj master to the database. Also notifies the appropriate model listeners.
    *
    * @param naProjMaster the na proj master
    * @return the na proj master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NaProjMaster addNaProjMaster(
        com.stpl.app.model.NaProjMaster naProjMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _naProjMasterLocalService.addNaProjMaster(naProjMaster);
    }

    /**
    * Creates a new na proj master with the primary key. Does not add the na proj master to the database.
    *
    * @param naProjMasterSid the primary key for the new na proj master
    * @return the new na proj master
    */
    @Override
    public com.stpl.app.model.NaProjMaster createNaProjMaster(
        int naProjMasterSid) {
        return _naProjMasterLocalService.createNaProjMaster(naProjMasterSid);
    }

    /**
    * Deletes the na proj master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param naProjMasterSid the primary key of the na proj master
    * @return the na proj master that was removed
    * @throws PortalException if a na proj master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NaProjMaster deleteNaProjMaster(
        int naProjMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _naProjMasterLocalService.deleteNaProjMaster(naProjMasterSid);
    }

    /**
    * Deletes the na proj master from the database. Also notifies the appropriate model listeners.
    *
    * @param naProjMaster the na proj master
    * @return the na proj master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NaProjMaster deleteNaProjMaster(
        com.stpl.app.model.NaProjMaster naProjMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _naProjMasterLocalService.deleteNaProjMaster(naProjMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _naProjMasterLocalService.dynamicQuery();
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
        return _naProjMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NaProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _naProjMasterLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NaProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _naProjMasterLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _naProjMasterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _naProjMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.NaProjMaster fetchNaProjMaster(
        int naProjMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _naProjMasterLocalService.fetchNaProjMaster(naProjMasterSid);
    }

    /**
    * Returns the na proj master with the primary key.
    *
    * @param naProjMasterSid the primary key of the na proj master
    * @return the na proj master
    * @throws PortalException if a na proj master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NaProjMaster getNaProjMaster(int naProjMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _naProjMasterLocalService.getNaProjMaster(naProjMasterSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _naProjMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the na proj masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NaProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of na proj masters
    * @param end the upper bound of the range of na proj masters (not inclusive)
    * @return the range of na proj masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.NaProjMaster> getNaProjMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _naProjMasterLocalService.getNaProjMasters(start, end);
    }

    /**
    * Returns the number of na proj masters.
    *
    * @return the number of na proj masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getNaProjMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _naProjMasterLocalService.getNaProjMastersCount();
    }

    /**
    * Updates the na proj master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param naProjMaster the na proj master
    * @return the na proj master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NaProjMaster updateNaProjMaster(
        com.stpl.app.model.NaProjMaster naProjMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _naProjMasterLocalService.updateNaProjMaster(naProjMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _naProjMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _naProjMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _naProjMasterLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    @Override
    public java.util.List getAvailableProducts(java.lang.Object companyValue,
        java.lang.Object therapeuticClassValue, java.lang.Object future2,
        java.lang.Object future3) {
        return _naProjMasterLocalService.getAvailableProducts(companyValue,
            therapeuticClassValue, future2, future3);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public NaProjMasterLocalService getWrappedNaProjMasterLocalService() {
        return _naProjMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedNaProjMasterLocalService(
        NaProjMasterLocalService naProjMasterLocalService) {
        _naProjMasterLocalService = naProjMasterLocalService;
    }

    @Override
    public NaProjMasterLocalService getWrappedService() {
        return _naProjMasterLocalService;
    }

    @Override
    public void setWrappedService(
        NaProjMasterLocalService naProjMasterLocalService) {
        _naProjMasterLocalService = naProjMasterLocalService;
    }
}
