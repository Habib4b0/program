package com.stpl.app.service.persistence;

import com.stpl.app.model.MedicaidUraActuals;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the medicaid ura actuals service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MedicaidUraActualsPersistenceImpl
 * @see MedicaidUraActualsUtil
 * @generated
 */
public interface MedicaidUraActualsPersistence extends BasePersistence<MedicaidUraActuals> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link MedicaidUraActualsUtil} to access the medicaid ura actuals persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the medicaid ura actuals in the entity cache if it is enabled.
    *
    * @param medicaidUraActuals the medicaid ura actuals
    */
    public void cacheResult(
        com.stpl.app.model.MedicaidUraActuals medicaidUraActuals);

    /**
    * Caches the medicaid ura actualses in the entity cache if it is enabled.
    *
    * @param medicaidUraActualses the medicaid ura actualses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.MedicaidUraActuals> medicaidUraActualses);

    /**
    * Creates a new medicaid ura actuals with the primary key. Does not add the medicaid ura actuals to the database.
    *
    * @param medicaidUraActualsPK the primary key for the new medicaid ura actuals
    * @return the new medicaid ura actuals
    */
    public com.stpl.app.model.MedicaidUraActuals create(
        MedicaidUraActualsPK medicaidUraActualsPK);

    /**
    * Removes the medicaid ura actuals with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param medicaidUraActualsPK the primary key of the medicaid ura actuals
    * @return the medicaid ura actuals that was removed
    * @throws com.stpl.app.NoSuchMedicaidUraActualsException if a medicaid ura actuals with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.MedicaidUraActuals remove(
        MedicaidUraActualsPK medicaidUraActualsPK)
        throws com.stpl.app.NoSuchMedicaidUraActualsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.MedicaidUraActuals updateImpl(
        com.stpl.app.model.MedicaidUraActuals medicaidUraActuals)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the medicaid ura actuals with the primary key or throws a {@link com.stpl.app.NoSuchMedicaidUraActualsException} if it could not be found.
    *
    * @param medicaidUraActualsPK the primary key of the medicaid ura actuals
    * @return the medicaid ura actuals
    * @throws com.stpl.app.NoSuchMedicaidUraActualsException if a medicaid ura actuals with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.MedicaidUraActuals findByPrimaryKey(
        MedicaidUraActualsPK medicaidUraActualsPK)
        throws com.stpl.app.NoSuchMedicaidUraActualsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the medicaid ura actuals with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param medicaidUraActualsPK the primary key of the medicaid ura actuals
    * @return the medicaid ura actuals, or <code>null</code> if a medicaid ura actuals with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.MedicaidUraActuals fetchByPrimaryKey(
        MedicaidUraActualsPK medicaidUraActualsPK)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the medicaid ura actualses.
    *
    * @return the medicaid ura actualses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.MedicaidUraActuals> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

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
    public java.util.List<com.stpl.app.model.MedicaidUraActuals> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the medicaid ura actualses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MedicaidUraActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of medicaid ura actualses
    * @param end the upper bound of the range of medicaid ura actualses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of medicaid ura actualses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.MedicaidUraActuals> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the medicaid ura actualses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of medicaid ura actualses.
    *
    * @return the number of medicaid ura actualses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
