package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MedicaidUraActualsLocalService}.
 *
 * @author
 * @see MedicaidUraActualsLocalService
 * @generated
 */
public class MedicaidUraActualsLocalServiceWrapper
    implements MedicaidUraActualsLocalService,
        ServiceWrapper<MedicaidUraActualsLocalService> {
    private MedicaidUraActualsLocalService _medicaidUraActualsLocalService;

    public MedicaidUraActualsLocalServiceWrapper(
        MedicaidUraActualsLocalService medicaidUraActualsLocalService) {
        _medicaidUraActualsLocalService = medicaidUraActualsLocalService;
    }

    /**
    * Adds the medicaid ura actuals to the database. Also notifies the appropriate model listeners.
    *
    * @param medicaidUraActuals the medicaid ura actuals
    * @return the medicaid ura actuals that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MedicaidUraActuals addMedicaidUraActuals(
        com.stpl.app.model.MedicaidUraActuals medicaidUraActuals)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _medicaidUraActualsLocalService.addMedicaidUraActuals(medicaidUraActuals);
    }

    /**
    * Creates a new medicaid ura actuals with the primary key. Does not add the medicaid ura actuals to the database.
    *
    * @param medicaidUraActualsPK the primary key for the new medicaid ura actuals
    * @return the new medicaid ura actuals
    */
    @Override
    public com.stpl.app.model.MedicaidUraActuals createMedicaidUraActuals(
        com.stpl.app.service.persistence.MedicaidUraActualsPK medicaidUraActualsPK) {
        return _medicaidUraActualsLocalService.createMedicaidUraActuals(medicaidUraActualsPK);
    }

    /**
    * Deletes the medicaid ura actuals with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param medicaidUraActualsPK the primary key of the medicaid ura actuals
    * @return the medicaid ura actuals that was removed
    * @throws PortalException if a medicaid ura actuals with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MedicaidUraActuals deleteMedicaidUraActuals(
        com.stpl.app.service.persistence.MedicaidUraActualsPK medicaidUraActualsPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _medicaidUraActualsLocalService.deleteMedicaidUraActuals(medicaidUraActualsPK);
    }

    /**
    * Deletes the medicaid ura actuals from the database. Also notifies the appropriate model listeners.
    *
    * @param medicaidUraActuals the medicaid ura actuals
    * @return the medicaid ura actuals that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MedicaidUraActuals deleteMedicaidUraActuals(
        com.stpl.app.model.MedicaidUraActuals medicaidUraActuals)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _medicaidUraActualsLocalService.deleteMedicaidUraActuals(medicaidUraActuals);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _medicaidUraActualsLocalService.dynamicQuery();
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
        return _medicaidUraActualsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MedicaidUraActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _medicaidUraActualsLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MedicaidUraActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _medicaidUraActualsLocalService.dynamicQuery(dynamicQuery,
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
        return _medicaidUraActualsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _medicaidUraActualsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.MedicaidUraActuals fetchMedicaidUraActuals(
        com.stpl.app.service.persistence.MedicaidUraActualsPK medicaidUraActualsPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _medicaidUraActualsLocalService.fetchMedicaidUraActuals(medicaidUraActualsPK);
    }

    /**
    * Returns the medicaid ura actuals with the primary key.
    *
    * @param medicaidUraActualsPK the primary key of the medicaid ura actuals
    * @return the medicaid ura actuals
    * @throws PortalException if a medicaid ura actuals with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MedicaidUraActuals getMedicaidUraActuals(
        com.stpl.app.service.persistence.MedicaidUraActualsPK medicaidUraActualsPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _medicaidUraActualsLocalService.getMedicaidUraActuals(medicaidUraActualsPK);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _medicaidUraActualsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the medicaid ura actualses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MedicaidUraActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of medicaid ura actualses
    * @param end the upper bound of the range of medicaid ura actualses (not inclusive)
    * @return the range of medicaid ura actualses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.MedicaidUraActuals> getMedicaidUraActualses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _medicaidUraActualsLocalService.getMedicaidUraActualses(start,
            end);
    }

    /**
    * Returns the number of medicaid ura actualses.
    *
    * @return the number of medicaid ura actualses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getMedicaidUraActualsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _medicaidUraActualsLocalService.getMedicaidUraActualsesCount();
    }

    /**
    * Updates the medicaid ura actuals in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param medicaidUraActuals the medicaid ura actuals
    * @return the medicaid ura actuals that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MedicaidUraActuals updateMedicaidUraActuals(
        com.stpl.app.model.MedicaidUraActuals medicaidUraActuals)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _medicaidUraActualsLocalService.updateMedicaidUraActuals(medicaidUraActuals);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _medicaidUraActualsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _medicaidUraActualsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _medicaidUraActualsLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public MedicaidUraActualsLocalService getWrappedMedicaidUraActualsLocalService() {
        return _medicaidUraActualsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedMedicaidUraActualsLocalService(
        MedicaidUraActualsLocalService medicaidUraActualsLocalService) {
        _medicaidUraActualsLocalService = medicaidUraActualsLocalService;
    }

    @Override
    public MedicaidUraActualsLocalService getWrappedService() {
        return _medicaidUraActualsLocalService;
    }

    @Override
    public void setWrappedService(
        MedicaidUraActualsLocalService medicaidUraActualsLocalService) {
        _medicaidUraActualsLocalService = medicaidUraActualsLocalService;
    }
}
