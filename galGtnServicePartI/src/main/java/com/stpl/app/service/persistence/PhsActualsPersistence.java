package com.stpl.app.service.persistence;

import com.stpl.app.model.PhsActuals;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the phs actuals service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see PhsActualsPersistenceImpl
 * @see PhsActualsUtil
 * @generated
 */
public interface PhsActualsPersistence extends BasePersistence<PhsActuals> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link PhsActualsUtil} to access the phs actuals persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the phs actuals in the entity cache if it is enabled.
    *
    * @param phsActuals the phs actuals
    */
    public void cacheResult(com.stpl.app.model.PhsActuals phsActuals);

    /**
    * Caches the phs actualses in the entity cache if it is enabled.
    *
    * @param phsActualses the phs actualses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.PhsActuals> phsActualses);

    /**
    * Creates a new phs actuals with the primary key. Does not add the phs actuals to the database.
    *
    * @param phsActualsPK the primary key for the new phs actuals
    * @return the new phs actuals
    */
    public com.stpl.app.model.PhsActuals create(PhsActualsPK phsActualsPK);

    /**
    * Removes the phs actuals with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param phsActualsPK the primary key of the phs actuals
    * @return the phs actuals that was removed
    * @throws com.stpl.app.NoSuchPhsActualsException if a phs actuals with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PhsActuals remove(PhsActualsPK phsActualsPK)
        throws com.stpl.app.NoSuchPhsActualsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.PhsActuals updateImpl(
        com.stpl.app.model.PhsActuals phsActuals)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the phs actuals with the primary key or throws a {@link com.stpl.app.NoSuchPhsActualsException} if it could not be found.
    *
    * @param phsActualsPK the primary key of the phs actuals
    * @return the phs actuals
    * @throws com.stpl.app.NoSuchPhsActualsException if a phs actuals with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PhsActuals findByPrimaryKey(
        PhsActualsPK phsActualsPK)
        throws com.stpl.app.NoSuchPhsActualsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the phs actuals with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param phsActualsPK the primary key of the phs actuals
    * @return the phs actuals, or <code>null</code> if a phs actuals with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PhsActuals fetchByPrimaryKey(
        PhsActualsPK phsActualsPK)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the phs actualses.
    *
    * @return the phs actualses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.PhsActuals> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the phs actualses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PhsActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of phs actualses
    * @param end the upper bound of the range of phs actualses (not inclusive)
    * @return the range of phs actualses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.PhsActuals> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the phs actualses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PhsActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of phs actualses
    * @param end the upper bound of the range of phs actualses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of phs actualses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.PhsActuals> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the phs actualses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of phs actualses.
    *
    * @return the number of phs actualses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
