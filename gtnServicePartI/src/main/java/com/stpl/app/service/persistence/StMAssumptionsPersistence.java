package com.stpl.app.service.persistence;

import com.stpl.app.model.StMAssumptions;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the st m assumptions service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StMAssumptionsPersistenceImpl
 * @see StMAssumptionsUtil
 * @generated
 */
public interface StMAssumptionsPersistence extends BasePersistence<StMAssumptions> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link StMAssumptionsUtil} to access the st m assumptions persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the st m assumptions in the entity cache if it is enabled.
    *
    * @param stMAssumptions the st m assumptions
    */
    public void cacheResult(com.stpl.app.model.StMAssumptions stMAssumptions);

    /**
    * Caches the st m assumptionses in the entity cache if it is enabled.
    *
    * @param stMAssumptionses the st m assumptionses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.StMAssumptions> stMAssumptionses);

    /**
    * Creates a new st m assumptions with the primary key. Does not add the st m assumptions to the database.
    *
    * @param stMAssumptionsPK the primary key for the new st m assumptions
    * @return the new st m assumptions
    */
    public com.stpl.app.model.StMAssumptions create(
        StMAssumptionsPK stMAssumptionsPK);

    /**
    * Removes the st m assumptions with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stMAssumptionsPK the primary key of the st m assumptions
    * @return the st m assumptions that was removed
    * @throws com.stpl.app.NoSuchStMAssumptionsException if a st m assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StMAssumptions remove(
        StMAssumptionsPK stMAssumptionsPK)
        throws com.stpl.app.NoSuchStMAssumptionsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.StMAssumptions updateImpl(
        com.stpl.app.model.StMAssumptions stMAssumptions)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the st m assumptions with the primary key or throws a {@link com.stpl.app.NoSuchStMAssumptionsException} if it could not be found.
    *
    * @param stMAssumptionsPK the primary key of the st m assumptions
    * @return the st m assumptions
    * @throws com.stpl.app.NoSuchStMAssumptionsException if a st m assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StMAssumptions findByPrimaryKey(
        StMAssumptionsPK stMAssumptionsPK)
        throws com.stpl.app.NoSuchStMAssumptionsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the st m assumptions with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param stMAssumptionsPK the primary key of the st m assumptions
    * @return the st m assumptions, or <code>null</code> if a st m assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StMAssumptions fetchByPrimaryKey(
        StMAssumptionsPK stMAssumptionsPK)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the st m assumptionses.
    *
    * @return the st m assumptionses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StMAssumptions> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the st m assumptionses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StMAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st m assumptionses
    * @param end the upper bound of the range of st m assumptionses (not inclusive)
    * @return the range of st m assumptionses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StMAssumptions> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the st m assumptionses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StMAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st m assumptionses
    * @param end the upper bound of the range of st m assumptionses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of st m assumptionses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StMAssumptions> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the st m assumptionses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of st m assumptionses.
    *
    * @return the number of st m assumptionses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
