package com.stpl.app.service.persistence;

import com.stpl.app.model.StMSupplementalDiscActuals;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the st m supplemental disc actuals service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StMSupplementalDiscActualsPersistenceImpl
 * @see StMSupplementalDiscActualsUtil
 * @generated
 */
public interface StMSupplementalDiscActualsPersistence extends BasePersistence<StMSupplementalDiscActuals> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link StMSupplementalDiscActualsUtil} to access the st m supplemental disc actuals persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the st m supplemental disc actuals in the entity cache if it is enabled.
    *
    * @param stMSupplementalDiscActuals the st m supplemental disc actuals
    */
    public void cacheResult(
        com.stpl.app.model.StMSupplementalDiscActuals stMSupplementalDiscActuals);

    /**
    * Caches the st m supplemental disc actualses in the entity cache if it is enabled.
    *
    * @param stMSupplementalDiscActualses the st m supplemental disc actualses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.StMSupplementalDiscActuals> stMSupplementalDiscActualses);

    /**
    * Creates a new st m supplemental disc actuals with the primary key. Does not add the st m supplemental disc actuals to the database.
    *
    * @param stMSupplementalDiscActualsPK the primary key for the new st m supplemental disc actuals
    * @return the new st m supplemental disc actuals
    */
    public com.stpl.app.model.StMSupplementalDiscActuals create(
        StMSupplementalDiscActualsPK stMSupplementalDiscActualsPK);

    /**
    * Removes the st m supplemental disc actuals with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stMSupplementalDiscActualsPK the primary key of the st m supplemental disc actuals
    * @return the st m supplemental disc actuals that was removed
    * @throws com.stpl.app.NoSuchStMSupplementalDiscActualsException if a st m supplemental disc actuals with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StMSupplementalDiscActuals remove(
        StMSupplementalDiscActualsPK stMSupplementalDiscActualsPK)
        throws com.stpl.app.NoSuchStMSupplementalDiscActualsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.StMSupplementalDiscActuals updateImpl(
        com.stpl.app.model.StMSupplementalDiscActuals stMSupplementalDiscActuals)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the st m supplemental disc actuals with the primary key or throws a {@link com.stpl.app.NoSuchStMSupplementalDiscActualsException} if it could not be found.
    *
    * @param stMSupplementalDiscActualsPK the primary key of the st m supplemental disc actuals
    * @return the st m supplemental disc actuals
    * @throws com.stpl.app.NoSuchStMSupplementalDiscActualsException if a st m supplemental disc actuals with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StMSupplementalDiscActuals findByPrimaryKey(
        StMSupplementalDiscActualsPK stMSupplementalDiscActualsPK)
        throws com.stpl.app.NoSuchStMSupplementalDiscActualsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the st m supplemental disc actuals with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param stMSupplementalDiscActualsPK the primary key of the st m supplemental disc actuals
    * @return the st m supplemental disc actuals, or <code>null</code> if a st m supplemental disc actuals with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StMSupplementalDiscActuals fetchByPrimaryKey(
        StMSupplementalDiscActualsPK stMSupplementalDiscActualsPK)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the st m supplemental disc actualses.
    *
    * @return the st m supplemental disc actualses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StMSupplementalDiscActuals> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the st m supplemental disc actualses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StMSupplementalDiscActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st m supplemental disc actualses
    * @param end the upper bound of the range of st m supplemental disc actualses (not inclusive)
    * @return the range of st m supplemental disc actualses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StMSupplementalDiscActuals> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the st m supplemental disc actualses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StMSupplementalDiscActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st m supplemental disc actualses
    * @param end the upper bound of the range of st m supplemental disc actualses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of st m supplemental disc actualses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StMSupplementalDiscActuals> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the st m supplemental disc actualses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of st m supplemental disc actualses.
    *
    * @return the number of st m supplemental disc actualses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
