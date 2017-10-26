package com.stpl.app.service.persistence;

import com.stpl.app.model.NationalAssumptionsActuals;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the national assumptions actuals service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NationalAssumptionsActualsPersistenceImpl
 * @see NationalAssumptionsActualsUtil
 * @generated
 */
public interface NationalAssumptionsActualsPersistence extends BasePersistence<NationalAssumptionsActuals> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link NationalAssumptionsActualsUtil} to access the national assumptions actuals persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the national assumptions actuals in the entity cache if it is enabled.
    *
    * @param nationalAssumptionsActuals the national assumptions actuals
    */
    public void cacheResult(
        com.stpl.app.model.NationalAssumptionsActuals nationalAssumptionsActuals);

    /**
    * Caches the national assumptions actualses in the entity cache if it is enabled.
    *
    * @param nationalAssumptionsActualses the national assumptions actualses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.NationalAssumptionsActuals> nationalAssumptionsActualses);

    /**
    * Creates a new national assumptions actuals with the primary key. Does not add the national assumptions actuals to the database.
    *
    * @param nationalAssumptionsActualsPK the primary key for the new national assumptions actuals
    * @return the new national assumptions actuals
    */
    public com.stpl.app.model.NationalAssumptionsActuals create(
        NationalAssumptionsActualsPK nationalAssumptionsActualsPK);

    /**
    * Removes the national assumptions actuals with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param nationalAssumptionsActualsPK the primary key of the national assumptions actuals
    * @return the national assumptions actuals that was removed
    * @throws com.stpl.app.NoSuchNationalAssumptionsActualsException if a national assumptions actuals with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.NationalAssumptionsActuals remove(
        NationalAssumptionsActualsPK nationalAssumptionsActualsPK)
        throws com.stpl.app.NoSuchNationalAssumptionsActualsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.NationalAssumptionsActuals updateImpl(
        com.stpl.app.model.NationalAssumptionsActuals nationalAssumptionsActuals)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the national assumptions actuals with the primary key or throws a {@link com.stpl.app.NoSuchNationalAssumptionsActualsException} if it could not be found.
    *
    * @param nationalAssumptionsActualsPK the primary key of the national assumptions actuals
    * @return the national assumptions actuals
    * @throws com.stpl.app.NoSuchNationalAssumptionsActualsException if a national assumptions actuals with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.NationalAssumptionsActuals findByPrimaryKey(
        NationalAssumptionsActualsPK nationalAssumptionsActualsPK)
        throws com.stpl.app.NoSuchNationalAssumptionsActualsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the national assumptions actuals with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param nationalAssumptionsActualsPK the primary key of the national assumptions actuals
    * @return the national assumptions actuals, or <code>null</code> if a national assumptions actuals with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.NationalAssumptionsActuals fetchByPrimaryKey(
        NationalAssumptionsActualsPK nationalAssumptionsActualsPK)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the national assumptions actualses.
    *
    * @return the national assumptions actualses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.NationalAssumptionsActuals> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the national assumptions actualses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NationalAssumptionsActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of national assumptions actualses
    * @param end the upper bound of the range of national assumptions actualses (not inclusive)
    * @return the range of national assumptions actualses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.NationalAssumptionsActuals> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the national assumptions actualses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NationalAssumptionsActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of national assumptions actualses
    * @param end the upper bound of the range of national assumptions actualses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of national assumptions actualses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.NationalAssumptionsActuals> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the national assumptions actualses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of national assumptions actualses.
    *
    * @return the number of national assumptions actualses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
