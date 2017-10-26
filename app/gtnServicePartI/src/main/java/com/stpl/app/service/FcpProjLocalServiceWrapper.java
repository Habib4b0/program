package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link FcpProjLocalService}.
 *
 * @author
 * @see FcpProjLocalService
 * @generated
 */
public class FcpProjLocalServiceWrapper implements FcpProjLocalService,
    ServiceWrapper<FcpProjLocalService> {
    private FcpProjLocalService _fcpProjLocalService;

    public FcpProjLocalServiceWrapper(FcpProjLocalService fcpProjLocalService) {
        _fcpProjLocalService = fcpProjLocalService;
    }

    /**
    * Adds the fcp proj to the database. Also notifies the appropriate model listeners.
    *
    * @param fcpProj the fcp proj
    * @return the fcp proj that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.FcpProj addFcpProj(
        com.stpl.app.model.FcpProj fcpProj)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _fcpProjLocalService.addFcpProj(fcpProj);
    }

    /**
    * Creates a new fcp proj with the primary key. Does not add the fcp proj to the database.
    *
    * @param fcpProjPK the primary key for the new fcp proj
    * @return the new fcp proj
    */
    @Override
    public com.stpl.app.model.FcpProj createFcpProj(
        com.stpl.app.service.persistence.FcpProjPK fcpProjPK) {
        return _fcpProjLocalService.createFcpProj(fcpProjPK);
    }

    /**
    * Deletes the fcp proj with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param fcpProjPK the primary key of the fcp proj
    * @return the fcp proj that was removed
    * @throws PortalException if a fcp proj with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.FcpProj deleteFcpProj(
        com.stpl.app.service.persistence.FcpProjPK fcpProjPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _fcpProjLocalService.deleteFcpProj(fcpProjPK);
    }

    /**
    * Deletes the fcp proj from the database. Also notifies the appropriate model listeners.
    *
    * @param fcpProj the fcp proj
    * @return the fcp proj that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.FcpProj deleteFcpProj(
        com.stpl.app.model.FcpProj fcpProj)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _fcpProjLocalService.deleteFcpProj(fcpProj);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _fcpProjLocalService.dynamicQuery();
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
        return _fcpProjLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FcpProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _fcpProjLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FcpProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _fcpProjLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _fcpProjLocalService.dynamicQueryCount(dynamicQuery);
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
        return _fcpProjLocalService.dynamicQueryCount(dynamicQuery, projection);
    }

    @Override
    public com.stpl.app.model.FcpProj fetchFcpProj(
        com.stpl.app.service.persistence.FcpProjPK fcpProjPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _fcpProjLocalService.fetchFcpProj(fcpProjPK);
    }

    /**
    * Returns the fcp proj with the primary key.
    *
    * @param fcpProjPK the primary key of the fcp proj
    * @return the fcp proj
    * @throws PortalException if a fcp proj with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.FcpProj getFcpProj(
        com.stpl.app.service.persistence.FcpProjPK fcpProjPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _fcpProjLocalService.getFcpProj(fcpProjPK);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _fcpProjLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the fcp projs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FcpProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of fcp projs
    * @param end the upper bound of the range of fcp projs (not inclusive)
    * @return the range of fcp projs
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.FcpProj> getFcpProjs(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException {
        return _fcpProjLocalService.getFcpProjs(start, end);
    }

    /**
    * Returns the number of fcp projs.
    *
    * @return the number of fcp projs
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getFcpProjsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _fcpProjLocalService.getFcpProjsCount();
    }

    /**
    * Updates the fcp proj in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param fcpProj the fcp proj
    * @return the fcp proj that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.FcpProj updateFcpProj(
        com.stpl.app.model.FcpProj fcpProj)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _fcpProjLocalService.updateFcpProj(fcpProj);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _fcpProjLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _fcpProjLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _fcpProjLocalService.invokeMethod(name, parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public FcpProjLocalService getWrappedFcpProjLocalService() {
        return _fcpProjLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedFcpProjLocalService(
        FcpProjLocalService fcpProjLocalService) {
        _fcpProjLocalService = fcpProjLocalService;
    }

    @Override
    public FcpProjLocalService getWrappedService() {
        return _fcpProjLocalService;
    }

    @Override
    public void setWrappedService(FcpProjLocalService fcpProjLocalService) {
        _fcpProjLocalService = fcpProjLocalService;
    }
}
