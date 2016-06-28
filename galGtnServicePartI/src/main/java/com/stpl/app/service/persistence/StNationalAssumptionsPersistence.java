package com.stpl.app.service.persistence;

import com.stpl.app.model.StNationalAssumptions;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the st national assumptions service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StNationalAssumptionsPersistenceImpl
 * @see StNationalAssumptionsUtil
 * @generated
 */
public interface StNationalAssumptionsPersistence extends BasePersistence<StNationalAssumptions> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link StNationalAssumptionsUtil} to access the st national assumptions persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the st national assumptions in the entity cache if it is enabled.
    *
    * @param stNationalAssumptions the st national assumptions
    */
    public void cacheResult(
        com.stpl.app.model.StNationalAssumptions stNationalAssumptions);

    /**
    * Caches the st national assumptionses in the entity cache if it is enabled.
    *
    * @param stNationalAssumptionses the st national assumptionses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.StNationalAssumptions> stNationalAssumptionses);

    /**
    * Creates a new st national assumptions with the primary key. Does not add the st national assumptions to the database.
    *
    * @param stNationalAssumptionsPK the primary key for the new st national assumptions
    * @return the new st national assumptions
    */
    public com.stpl.app.model.StNationalAssumptions create(
        StNationalAssumptionsPK stNationalAssumptionsPK);

    /**
    * Removes the st national assumptions with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stNationalAssumptionsPK the primary key of the st national assumptions
    * @return the st national assumptions that was removed
    * @throws com.stpl.app.NoSuchStNationalAssumptionsException if a st national assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StNationalAssumptions remove(
        StNationalAssumptionsPK stNationalAssumptionsPK)
        throws com.stpl.app.NoSuchStNationalAssumptionsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.StNationalAssumptions updateImpl(
        com.stpl.app.model.StNationalAssumptions stNationalAssumptions)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the st national assumptions with the primary key or throws a {@link com.stpl.app.NoSuchStNationalAssumptionsException} if it could not be found.
    *
    * @param stNationalAssumptionsPK the primary key of the st national assumptions
    * @return the st national assumptions
    * @throws com.stpl.app.NoSuchStNationalAssumptionsException if a st national assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StNationalAssumptions findByPrimaryKey(
        StNationalAssumptionsPK stNationalAssumptionsPK)
        throws com.stpl.app.NoSuchStNationalAssumptionsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the st national assumptions with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param stNationalAssumptionsPK the primary key of the st national assumptions
    * @return the st national assumptions, or <code>null</code> if a st national assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StNationalAssumptions fetchByPrimaryKey(
        StNationalAssumptionsPK stNationalAssumptionsPK)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the st national assumptionses.
    *
    * @return the st national assumptionses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StNationalAssumptions> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the st national assumptionses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNationalAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st national assumptionses
    * @param end the upper bound of the range of st national assumptionses (not inclusive)
    * @return the range of st national assumptionses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StNationalAssumptions> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the st national assumptionses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNationalAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st national assumptionses
    * @param end the upper bound of the range of st national assumptionses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of st national assumptionses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StNationalAssumptions> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the st national assumptionses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of st national assumptionses.
    *
    * @return the number of st national assumptionses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
