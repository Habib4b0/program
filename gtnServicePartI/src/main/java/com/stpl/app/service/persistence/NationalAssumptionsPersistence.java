package com.stpl.app.service.persistence;

import com.stpl.app.model.NationalAssumptions;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the national assumptions service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NationalAssumptionsPersistenceImpl
 * @see NationalAssumptionsUtil
 * @generated
 */
public interface NationalAssumptionsPersistence extends BasePersistence<NationalAssumptions> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link NationalAssumptionsUtil} to access the national assumptions persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the national assumptions in the entity cache if it is enabled.
    *
    * @param nationalAssumptions the national assumptions
    */
    public void cacheResult(
        com.stpl.app.model.NationalAssumptions nationalAssumptions);

    /**
    * Caches the national assumptionses in the entity cache if it is enabled.
    *
    * @param nationalAssumptionses the national assumptionses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.NationalAssumptions> nationalAssumptionses);

    /**
    * Creates a new national assumptions with the primary key. Does not add the national assumptions to the database.
    *
    * @param nationalAssumptionsPK the primary key for the new national assumptions
    * @return the new national assumptions
    */
    public com.stpl.app.model.NationalAssumptions create(
        NationalAssumptionsPK nationalAssumptionsPK);

    /**
    * Removes the national assumptions with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param nationalAssumptionsPK the primary key of the national assumptions
    * @return the national assumptions that was removed
    * @throws com.stpl.app.NoSuchNationalAssumptionsException if a national assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.NationalAssumptions remove(
        NationalAssumptionsPK nationalAssumptionsPK)
        throws com.stpl.app.NoSuchNationalAssumptionsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.NationalAssumptions updateImpl(
        com.stpl.app.model.NationalAssumptions nationalAssumptions)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the national assumptions with the primary key or throws a {@link com.stpl.app.NoSuchNationalAssumptionsException} if it could not be found.
    *
    * @param nationalAssumptionsPK the primary key of the national assumptions
    * @return the national assumptions
    * @throws com.stpl.app.NoSuchNationalAssumptionsException if a national assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.NationalAssumptions findByPrimaryKey(
        NationalAssumptionsPK nationalAssumptionsPK)
        throws com.stpl.app.NoSuchNationalAssumptionsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the national assumptions with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param nationalAssumptionsPK the primary key of the national assumptions
    * @return the national assumptions, or <code>null</code> if a national assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.NationalAssumptions fetchByPrimaryKey(
        NationalAssumptionsPK nationalAssumptionsPK)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the national assumptionses.
    *
    * @return the national assumptionses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.NationalAssumptions> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the national assumptionses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NationalAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of national assumptionses
    * @param end the upper bound of the range of national assumptionses (not inclusive)
    * @return the range of national assumptionses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.NationalAssumptions> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the national assumptionses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NationalAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of national assumptionses
    * @param end the upper bound of the range of national assumptionses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of national assumptionses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.NationalAssumptions> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the national assumptionses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of national assumptionses.
    *
    * @return the number of national assumptionses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
