package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ChDiscountProjMasterLocalService}.
 *
 * @author
 * @see ChDiscountProjMasterLocalService
 * @generated
 */
public class ChDiscountProjMasterLocalServiceWrapper
    implements ChDiscountProjMasterLocalService,
        ServiceWrapper<ChDiscountProjMasterLocalService> {
    private ChDiscountProjMasterLocalService _chDiscountProjMasterLocalService;

    public ChDiscountProjMasterLocalServiceWrapper(
        ChDiscountProjMasterLocalService chDiscountProjMasterLocalService) {
        _chDiscountProjMasterLocalService = chDiscountProjMasterLocalService;
    }

    /**
    * Adds the ch discount proj master to the database. Also notifies the appropriate model listeners.
    *
    * @param chDiscountProjMaster the ch discount proj master
    * @return the ch discount proj master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ChDiscountProjMaster addChDiscountProjMaster(
        com.stpl.app.model.ChDiscountProjMaster chDiscountProjMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _chDiscountProjMasterLocalService.addChDiscountProjMaster(chDiscountProjMaster);
    }

    /**
    * Creates a new ch discount proj master with the primary key. Does not add the ch discount proj master to the database.
    *
    * @param chDiscountProjMasterPK the primary key for the new ch discount proj master
    * @return the new ch discount proj master
    */
    @Override
    public com.stpl.app.model.ChDiscountProjMaster createChDiscountProjMaster(
        com.stpl.app.service.persistence.ChDiscountProjMasterPK chDiscountProjMasterPK) {
        return _chDiscountProjMasterLocalService.createChDiscountProjMaster(chDiscountProjMasterPK);
    }

    /**
    * Deletes the ch discount proj master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param chDiscountProjMasterPK the primary key of the ch discount proj master
    * @return the ch discount proj master that was removed
    * @throws PortalException if a ch discount proj master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ChDiscountProjMaster deleteChDiscountProjMaster(
        com.stpl.app.service.persistence.ChDiscountProjMasterPK chDiscountProjMasterPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _chDiscountProjMasterLocalService.deleteChDiscountProjMaster(chDiscountProjMasterPK);
    }

    /**
    * Deletes the ch discount proj master from the database. Also notifies the appropriate model listeners.
    *
    * @param chDiscountProjMaster the ch discount proj master
    * @return the ch discount proj master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ChDiscountProjMaster deleteChDiscountProjMaster(
        com.stpl.app.model.ChDiscountProjMaster chDiscountProjMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _chDiscountProjMasterLocalService.deleteChDiscountProjMaster(chDiscountProjMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _chDiscountProjMasterLocalService.dynamicQuery();
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
        return _chDiscountProjMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _chDiscountProjMasterLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _chDiscountProjMasterLocalService.dynamicQuery(dynamicQuery,
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
        return _chDiscountProjMasterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _chDiscountProjMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.ChDiscountProjMaster fetchChDiscountProjMaster(
        com.stpl.app.service.persistence.ChDiscountProjMasterPK chDiscountProjMasterPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _chDiscountProjMasterLocalService.fetchChDiscountProjMaster(chDiscountProjMasterPK);
    }

    /**
    * Returns the ch discount proj master with the primary key.
    *
    * @param chDiscountProjMasterPK the primary key of the ch discount proj master
    * @return the ch discount proj master
    * @throws PortalException if a ch discount proj master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ChDiscountProjMaster getChDiscountProjMaster(
        com.stpl.app.service.persistence.ChDiscountProjMasterPK chDiscountProjMasterPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _chDiscountProjMasterLocalService.getChDiscountProjMaster(chDiscountProjMasterPK);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _chDiscountProjMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ch discount proj masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ch discount proj masters
    * @param end the upper bound of the range of ch discount proj masters (not inclusive)
    * @return the range of ch discount proj masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.ChDiscountProjMaster> getChDiscountProjMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _chDiscountProjMasterLocalService.getChDiscountProjMasters(start,
            end);
    }

    /**
    * Returns the number of ch discount proj masters.
    *
    * @return the number of ch discount proj masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getChDiscountProjMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _chDiscountProjMasterLocalService.getChDiscountProjMastersCount();
    }

    /**
    * Updates the ch discount proj master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param chDiscountProjMaster the ch discount proj master
    * @return the ch discount proj master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ChDiscountProjMaster updateChDiscountProjMaster(
        com.stpl.app.model.ChDiscountProjMaster chDiscountProjMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _chDiscountProjMasterLocalService.updateChDiscountProjMaster(chDiscountProjMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _chDiscountProjMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _chDiscountProjMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _chDiscountProjMasterLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ChDiscountProjMasterLocalService getWrappedChDiscountProjMasterLocalService() {
        return _chDiscountProjMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedChDiscountProjMasterLocalService(
        ChDiscountProjMasterLocalService chDiscountProjMasterLocalService) {
        _chDiscountProjMasterLocalService = chDiscountProjMasterLocalService;
    }

    @Override
    public ChDiscountProjMasterLocalService getWrappedService() {
        return _chDiscountProjMasterLocalService;
    }

    @Override
    public void setWrappedService(
        ChDiscountProjMasterLocalService chDiscountProjMasterLocalService) {
        _chDiscountProjMasterLocalService = chDiscountProjMasterLocalService;
    }
}
