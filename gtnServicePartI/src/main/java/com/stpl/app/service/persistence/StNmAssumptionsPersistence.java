package com.stpl.app.service.persistence;

import com.stpl.app.model.StNmAssumptions;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the st nm assumptions service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StNmAssumptionsPersistenceImpl
 * @see StNmAssumptionsUtil
 * @generated
 */
public interface StNmAssumptionsPersistence extends BasePersistence<StNmAssumptions> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link StNmAssumptionsUtil} to access the st nm assumptions persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the st nm assumptions in the entity cache if it is enabled.
    *
    * @param stNmAssumptions the st nm assumptions
    */
    public void cacheResult(com.stpl.app.model.StNmAssumptions stNmAssumptions);

    /**
    * Caches the st nm assumptionses in the entity cache if it is enabled.
    *
    * @param stNmAssumptionses the st nm assumptionses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.StNmAssumptions> stNmAssumptionses);

    /**
    * Creates a new st nm assumptions with the primary key. Does not add the st nm assumptions to the database.
    *
    * @param stNmAssumptionsPK the primary key for the new st nm assumptions
    * @return the new st nm assumptions
    */
    public com.stpl.app.model.StNmAssumptions create(
        StNmAssumptionsPK stNmAssumptionsPK);

    /**
    * Removes the st nm assumptions with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stNmAssumptionsPK the primary key of the st nm assumptions
    * @return the st nm assumptions that was removed
    * @throws com.stpl.app.NoSuchStNmAssumptionsException if a st nm assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StNmAssumptions remove(
        StNmAssumptionsPK stNmAssumptionsPK)
        throws com.stpl.app.NoSuchStNmAssumptionsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.StNmAssumptions updateImpl(
        com.stpl.app.model.StNmAssumptions stNmAssumptions)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the st nm assumptions with the primary key or throws a {@link com.stpl.app.NoSuchStNmAssumptionsException} if it could not be found.
    *
    * @param stNmAssumptionsPK the primary key of the st nm assumptions
    * @return the st nm assumptions
    * @throws com.stpl.app.NoSuchStNmAssumptionsException if a st nm assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StNmAssumptions findByPrimaryKey(
        StNmAssumptionsPK stNmAssumptionsPK)
        throws com.stpl.app.NoSuchStNmAssumptionsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the st nm assumptions with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param stNmAssumptionsPK the primary key of the st nm assumptions
    * @return the st nm assumptions, or <code>null</code> if a st nm assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StNmAssumptions fetchByPrimaryKey(
        StNmAssumptionsPK stNmAssumptionsPK)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the st nm assumptionses.
    *
    * @return the st nm assumptionses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StNmAssumptions> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the st nm assumptionses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st nm assumptionses
    * @param end the upper bound of the range of st nm assumptionses (not inclusive)
    * @return the range of st nm assumptionses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StNmAssumptions> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the st nm assumptionses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st nm assumptionses
    * @param end the upper bound of the range of st nm assumptionses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of st nm assumptionses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StNmAssumptions> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the st nm assumptionses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of st nm assumptionses.
    *
    * @return the number of st nm assumptionses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
