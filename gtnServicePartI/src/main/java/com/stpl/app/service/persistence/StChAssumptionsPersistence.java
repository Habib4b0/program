package com.stpl.app.service.persistence;

import com.stpl.app.model.StChAssumptions;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the st ch assumptions service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StChAssumptionsPersistenceImpl
 * @see StChAssumptionsUtil
 * @generated
 */
public interface StChAssumptionsPersistence extends BasePersistence<StChAssumptions> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link StChAssumptionsUtil} to access the st ch assumptions persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the st ch assumptions in the entity cache if it is enabled.
    *
    * @param stChAssumptions the st ch assumptions
    */
    public void cacheResult(com.stpl.app.model.StChAssumptions stChAssumptions);

    /**
    * Caches the st ch assumptionses in the entity cache if it is enabled.
    *
    * @param stChAssumptionses the st ch assumptionses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.StChAssumptions> stChAssumptionses);

    /**
    * Creates a new st ch assumptions with the primary key. Does not add the st ch assumptions to the database.
    *
    * @param stChAssumptionsPK the primary key for the new st ch assumptions
    * @return the new st ch assumptions
    */
    public com.stpl.app.model.StChAssumptions create(
        StChAssumptionsPK stChAssumptionsPK);

    /**
    * Removes the st ch assumptions with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stChAssumptionsPK the primary key of the st ch assumptions
    * @return the st ch assumptions that was removed
    * @throws com.stpl.app.NoSuchStChAssumptionsException if a st ch assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StChAssumptions remove(
        StChAssumptionsPK stChAssumptionsPK)
        throws com.stpl.app.NoSuchStChAssumptionsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.StChAssumptions updateImpl(
        com.stpl.app.model.StChAssumptions stChAssumptions)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the st ch assumptions with the primary key or throws a {@link com.stpl.app.NoSuchStChAssumptionsException} if it could not be found.
    *
    * @param stChAssumptionsPK the primary key of the st ch assumptions
    * @return the st ch assumptions
    * @throws com.stpl.app.NoSuchStChAssumptionsException if a st ch assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StChAssumptions findByPrimaryKey(
        StChAssumptionsPK stChAssumptionsPK)
        throws com.stpl.app.NoSuchStChAssumptionsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the st ch assumptions with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param stChAssumptionsPK the primary key of the st ch assumptions
    * @return the st ch assumptions, or <code>null</code> if a st ch assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StChAssumptions fetchByPrimaryKey(
        StChAssumptionsPK stChAssumptionsPK)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the st ch assumptionses.
    *
    * @return the st ch assumptionses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StChAssumptions> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the st ch assumptionses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st ch assumptionses
    * @param end the upper bound of the range of st ch assumptionses (not inclusive)
    * @return the range of st ch assumptionses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StChAssumptions> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the st ch assumptionses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st ch assumptionses
    * @param end the upper bound of the range of st ch assumptionses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of st ch assumptionses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StChAssumptions> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the st ch assumptionses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of st ch assumptionses.
    *
    * @return the number of st ch assumptionses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
