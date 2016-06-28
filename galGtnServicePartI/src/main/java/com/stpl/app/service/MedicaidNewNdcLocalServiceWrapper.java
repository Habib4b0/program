package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MedicaidNewNdcLocalService}.
 *
 * @author
 * @see MedicaidNewNdcLocalService
 * @generated
 */
public class MedicaidNewNdcLocalServiceWrapper
    implements MedicaidNewNdcLocalService,
        ServiceWrapper<MedicaidNewNdcLocalService> {
    private MedicaidNewNdcLocalService _medicaidNewNdcLocalService;

    public MedicaidNewNdcLocalServiceWrapper(
        MedicaidNewNdcLocalService medicaidNewNdcLocalService) {
        _medicaidNewNdcLocalService = medicaidNewNdcLocalService;
    }

    /**
    * Adds the medicaid new ndc to the database. Also notifies the appropriate model listeners.
    *
    * @param medicaidNewNdc the medicaid new ndc
    * @return the medicaid new ndc that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MedicaidNewNdc addMedicaidNewNdc(
        com.stpl.app.model.MedicaidNewNdc medicaidNewNdc)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _medicaidNewNdcLocalService.addMedicaidNewNdc(medicaidNewNdc);
    }

    /**
    * Creates a new medicaid new ndc with the primary key. Does not add the medicaid new ndc to the database.
    *
    * @param ndc9 the primary key for the new medicaid new ndc
    * @return the new medicaid new ndc
    */
    @Override
    public com.stpl.app.model.MedicaidNewNdc createMedicaidNewNdc(
        java.lang.String ndc9) {
        return _medicaidNewNdcLocalService.createMedicaidNewNdc(ndc9);
    }

    /**
    * Deletes the medicaid new ndc with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ndc9 the primary key of the medicaid new ndc
    * @return the medicaid new ndc that was removed
    * @throws PortalException if a medicaid new ndc with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MedicaidNewNdc deleteMedicaidNewNdc(
        java.lang.String ndc9)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _medicaidNewNdcLocalService.deleteMedicaidNewNdc(ndc9);
    }

    /**
    * Deletes the medicaid new ndc from the database. Also notifies the appropriate model listeners.
    *
    * @param medicaidNewNdc the medicaid new ndc
    * @return the medicaid new ndc that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MedicaidNewNdc deleteMedicaidNewNdc(
        com.stpl.app.model.MedicaidNewNdc medicaidNewNdc)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _medicaidNewNdcLocalService.deleteMedicaidNewNdc(medicaidNewNdc);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _medicaidNewNdcLocalService.dynamicQuery();
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
        return _medicaidNewNdcLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MedicaidNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _medicaidNewNdcLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MedicaidNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _medicaidNewNdcLocalService.dynamicQuery(dynamicQuery, start,
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
        return _medicaidNewNdcLocalService.dynamicQueryCount(dynamicQuery);
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
        return _medicaidNewNdcLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.MedicaidNewNdc fetchMedicaidNewNdc(
        java.lang.String ndc9)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _medicaidNewNdcLocalService.fetchMedicaidNewNdc(ndc9);
    }

    /**
    * Returns the medicaid new ndc with the primary key.
    *
    * @param ndc9 the primary key of the medicaid new ndc
    * @return the medicaid new ndc
    * @throws PortalException if a medicaid new ndc with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MedicaidNewNdc getMedicaidNewNdc(
        java.lang.String ndc9)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _medicaidNewNdcLocalService.getMedicaidNewNdc(ndc9);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _medicaidNewNdcLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the medicaid new ndcs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MedicaidNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of medicaid new ndcs
    * @param end the upper bound of the range of medicaid new ndcs (not inclusive)
    * @return the range of medicaid new ndcs
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.MedicaidNewNdc> getMedicaidNewNdcs(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _medicaidNewNdcLocalService.getMedicaidNewNdcs(start, end);
    }

    /**
    * Returns the number of medicaid new ndcs.
    *
    * @return the number of medicaid new ndcs
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getMedicaidNewNdcsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _medicaidNewNdcLocalService.getMedicaidNewNdcsCount();
    }

    /**
    * Updates the medicaid new ndc in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param medicaidNewNdc the medicaid new ndc
    * @return the medicaid new ndc that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MedicaidNewNdc updateMedicaidNewNdc(
        com.stpl.app.model.MedicaidNewNdc medicaidNewNdc)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _medicaidNewNdcLocalService.updateMedicaidNewNdc(medicaidNewNdc);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _medicaidNewNdcLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _medicaidNewNdcLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _medicaidNewNdcLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public MedicaidNewNdcLocalService getWrappedMedicaidNewNdcLocalService() {
        return _medicaidNewNdcLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedMedicaidNewNdcLocalService(
        MedicaidNewNdcLocalService medicaidNewNdcLocalService) {
        _medicaidNewNdcLocalService = medicaidNewNdcLocalService;
    }

    @Override
    public MedicaidNewNdcLocalService getWrappedService() {
        return _medicaidNewNdcLocalService;
    }

    @Override
    public void setWrappedService(
        MedicaidNewNdcLocalService medicaidNewNdcLocalService) {
        _medicaidNewNdcLocalService = medicaidNewNdcLocalService;
    }
}
