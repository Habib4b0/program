package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PhsProjLocalService}.
 *
 * @author
 * @see PhsProjLocalService
 * @generated
 */
public class PhsProjLocalServiceWrapper implements PhsProjLocalService,
    ServiceWrapper<PhsProjLocalService> {
    private PhsProjLocalService _phsProjLocalService;

    public PhsProjLocalServiceWrapper(PhsProjLocalService phsProjLocalService) {
        _phsProjLocalService = phsProjLocalService;
    }

    /**
    * Adds the phs proj to the database. Also notifies the appropriate model listeners.
    *
    * @param phsProj the phs proj
    * @return the phs proj that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.PhsProj addPhsProj(
        com.stpl.app.model.PhsProj phsProj)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _phsProjLocalService.addPhsProj(phsProj);
    }

    /**
    * Creates a new phs proj with the primary key. Does not add the phs proj to the database.
    *
    * @param phsProjPK the primary key for the new phs proj
    * @return the new phs proj
    */
    @Override
    public com.stpl.app.model.PhsProj createPhsProj(
        com.stpl.app.service.persistence.PhsProjPK phsProjPK) {
        return _phsProjLocalService.createPhsProj(phsProjPK);
    }

    /**
    * Deletes the phs proj with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param phsProjPK the primary key of the phs proj
    * @return the phs proj that was removed
    * @throws PortalException if a phs proj with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.PhsProj deletePhsProj(
        com.stpl.app.service.persistence.PhsProjPK phsProjPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _phsProjLocalService.deletePhsProj(phsProjPK);
    }

    /**
    * Deletes the phs proj from the database. Also notifies the appropriate model listeners.
    *
    * @param phsProj the phs proj
    * @return the phs proj that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.PhsProj deletePhsProj(
        com.stpl.app.model.PhsProj phsProj)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _phsProjLocalService.deletePhsProj(phsProj);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _phsProjLocalService.dynamicQuery();
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
        return _phsProjLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PhsProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _phsProjLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PhsProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _phsProjLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _phsProjLocalService.dynamicQueryCount(dynamicQuery);
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
        return _phsProjLocalService.dynamicQueryCount(dynamicQuery, projection);
    }

    @Override
    public com.stpl.app.model.PhsProj fetchPhsProj(
        com.stpl.app.service.persistence.PhsProjPK phsProjPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _phsProjLocalService.fetchPhsProj(phsProjPK);
    }

    /**
    * Returns the phs proj with the primary key.
    *
    * @param phsProjPK the primary key of the phs proj
    * @return the phs proj
    * @throws PortalException if a phs proj with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.PhsProj getPhsProj(
        com.stpl.app.service.persistence.PhsProjPK phsProjPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _phsProjLocalService.getPhsProj(phsProjPK);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _phsProjLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the phs projs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PhsProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of phs projs
    * @param end the upper bound of the range of phs projs (not inclusive)
    * @return the range of phs projs
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.PhsProj> getPhsProjs(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException {
        return _phsProjLocalService.getPhsProjs(start, end);
    }

    /**
    * Returns the number of phs projs.
    *
    * @return the number of phs projs
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getPhsProjsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _phsProjLocalService.getPhsProjsCount();
    }

    /**
    * Updates the phs proj in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param phsProj the phs proj
    * @return the phs proj that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.PhsProj updatePhsProj(
        com.stpl.app.model.PhsProj phsProj)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _phsProjLocalService.updatePhsProj(phsProj);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _phsProjLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _phsProjLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _phsProjLocalService.invokeMethod(name, parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PhsProjLocalService getWrappedPhsProjLocalService() {
        return _phsProjLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPhsProjLocalService(
        PhsProjLocalService phsProjLocalService) {
        _phsProjLocalService = phsProjLocalService;
    }

    @Override
    public PhsProjLocalService getWrappedService() {
        return _phsProjLocalService;
    }

    @Override
    public void setWrappedService(PhsProjLocalService phsProjLocalService) {
        _phsProjLocalService = phsProjLocalService;
    }
}
