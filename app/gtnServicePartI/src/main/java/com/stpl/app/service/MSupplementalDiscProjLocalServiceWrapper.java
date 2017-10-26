package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MSupplementalDiscProjLocalService}.
 *
 * @author
 * @see MSupplementalDiscProjLocalService
 * @generated
 */
public class MSupplementalDiscProjLocalServiceWrapper
    implements MSupplementalDiscProjLocalService,
        ServiceWrapper<MSupplementalDiscProjLocalService> {
    private MSupplementalDiscProjLocalService _mSupplementalDiscProjLocalService;

    public MSupplementalDiscProjLocalServiceWrapper(
        MSupplementalDiscProjLocalService mSupplementalDiscProjLocalService) {
        _mSupplementalDiscProjLocalService = mSupplementalDiscProjLocalService;
    }

    /**
    * Adds the m supplemental disc proj to the database. Also notifies the appropriate model listeners.
    *
    * @param mSupplementalDiscProj the m supplemental disc proj
    * @return the m supplemental disc proj that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MSupplementalDiscProj addMSupplementalDiscProj(
        com.stpl.app.model.MSupplementalDiscProj mSupplementalDiscProj)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mSupplementalDiscProjLocalService.addMSupplementalDiscProj(mSupplementalDiscProj);
    }

    /**
    * Creates a new m supplemental disc proj with the primary key. Does not add the m supplemental disc proj to the database.
    *
    * @param projectionDetailsSid the primary key for the new m supplemental disc proj
    * @return the new m supplemental disc proj
    */
    @Override
    public com.stpl.app.model.MSupplementalDiscProj createMSupplementalDiscProj(
        int projectionDetailsSid) {
        return _mSupplementalDiscProjLocalService.createMSupplementalDiscProj(projectionDetailsSid);
    }

    /**
    * Deletes the m supplemental disc proj with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param projectionDetailsSid the primary key of the m supplemental disc proj
    * @return the m supplemental disc proj that was removed
    * @throws PortalException if a m supplemental disc proj with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MSupplementalDiscProj deleteMSupplementalDiscProj(
        int projectionDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _mSupplementalDiscProjLocalService.deleteMSupplementalDiscProj(projectionDetailsSid);
    }

    /**
    * Deletes the m supplemental disc proj from the database. Also notifies the appropriate model listeners.
    *
    * @param mSupplementalDiscProj the m supplemental disc proj
    * @return the m supplemental disc proj that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MSupplementalDiscProj deleteMSupplementalDiscProj(
        com.stpl.app.model.MSupplementalDiscProj mSupplementalDiscProj)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mSupplementalDiscProjLocalService.deleteMSupplementalDiscProj(mSupplementalDiscProj);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _mSupplementalDiscProjLocalService.dynamicQuery();
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
        return _mSupplementalDiscProjLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MSupplementalDiscProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _mSupplementalDiscProjLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MSupplementalDiscProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _mSupplementalDiscProjLocalService.dynamicQuery(dynamicQuery,
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
        return _mSupplementalDiscProjLocalService.dynamicQueryCount(dynamicQuery);
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
        return _mSupplementalDiscProjLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.MSupplementalDiscProj fetchMSupplementalDiscProj(
        int projectionDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mSupplementalDiscProjLocalService.fetchMSupplementalDiscProj(projectionDetailsSid);
    }

    /**
    * Returns the m supplemental disc proj with the primary key.
    *
    * @param projectionDetailsSid the primary key of the m supplemental disc proj
    * @return the m supplemental disc proj
    * @throws PortalException if a m supplemental disc proj with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MSupplementalDiscProj getMSupplementalDiscProj(
        int projectionDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _mSupplementalDiscProjLocalService.getMSupplementalDiscProj(projectionDetailsSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _mSupplementalDiscProjLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the m supplemental disc projs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MSupplementalDiscProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of m supplemental disc projs
    * @param end the upper bound of the range of m supplemental disc projs (not inclusive)
    * @return the range of m supplemental disc projs
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.MSupplementalDiscProj> getMSupplementalDiscProjs(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mSupplementalDiscProjLocalService.getMSupplementalDiscProjs(start,
            end);
    }

    /**
    * Returns the number of m supplemental disc projs.
    *
    * @return the number of m supplemental disc projs
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getMSupplementalDiscProjsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mSupplementalDiscProjLocalService.getMSupplementalDiscProjsCount();
    }

    /**
    * Updates the m supplemental disc proj in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param mSupplementalDiscProj the m supplemental disc proj
    * @return the m supplemental disc proj that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MSupplementalDiscProj updateMSupplementalDiscProj(
        com.stpl.app.model.MSupplementalDiscProj mSupplementalDiscProj)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mSupplementalDiscProjLocalService.updateMSupplementalDiscProj(mSupplementalDiscProj);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _mSupplementalDiscProjLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _mSupplementalDiscProjLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _mSupplementalDiscProjLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public MSupplementalDiscProjLocalService getWrappedMSupplementalDiscProjLocalService() {
        return _mSupplementalDiscProjLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedMSupplementalDiscProjLocalService(
        MSupplementalDiscProjLocalService mSupplementalDiscProjLocalService) {
        _mSupplementalDiscProjLocalService = mSupplementalDiscProjLocalService;
    }

    @Override
    public MSupplementalDiscProjLocalService getWrappedService() {
        return _mSupplementalDiscProjLocalService;
    }

    @Override
    public void setWrappedService(
        MSupplementalDiscProjLocalService mSupplementalDiscProjLocalService) {
        _mSupplementalDiscProjLocalService = mSupplementalDiscProjLocalService;
    }
}
