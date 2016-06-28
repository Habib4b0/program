package com.stpl.app.service.persistence;

import com.stpl.app.model.ChAssumptions;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ch assumptions service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ChAssumptionsPersistenceImpl
 * @see ChAssumptionsUtil
 * @generated
 */
public interface ChAssumptionsPersistence extends BasePersistence<ChAssumptions> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ChAssumptionsUtil} to access the ch assumptions persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the ch assumptions in the entity cache if it is enabled.
    *
    * @param chAssumptions the ch assumptions
    */
    public void cacheResult(com.stpl.app.model.ChAssumptions chAssumptions);

    /**
    * Caches the ch assumptionses in the entity cache if it is enabled.
    *
    * @param chAssumptionses the ch assumptionses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.ChAssumptions> chAssumptionses);

    /**
    * Creates a new ch assumptions with the primary key. Does not add the ch assumptions to the database.
    *
    * @param chAssumptionsSid the primary key for the new ch assumptions
    * @return the new ch assumptions
    */
    public com.stpl.app.model.ChAssumptions create(int chAssumptionsSid);

    /**
    * Removes the ch assumptions with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param chAssumptionsSid the primary key of the ch assumptions
    * @return the ch assumptions that was removed
    * @throws com.stpl.app.NoSuchChAssumptionsException if a ch assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ChAssumptions remove(int chAssumptionsSid)
        throws com.stpl.app.NoSuchChAssumptionsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.ChAssumptions updateImpl(
        com.stpl.app.model.ChAssumptions chAssumptions)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ch assumptions with the primary key or throws a {@link com.stpl.app.NoSuchChAssumptionsException} if it could not be found.
    *
    * @param chAssumptionsSid the primary key of the ch assumptions
    * @return the ch assumptions
    * @throws com.stpl.app.NoSuchChAssumptionsException if a ch assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ChAssumptions findByPrimaryKey(
        int chAssumptionsSid)
        throws com.stpl.app.NoSuchChAssumptionsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ch assumptions with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param chAssumptionsSid the primary key of the ch assumptions
    * @return the ch assumptions, or <code>null</code> if a ch assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ChAssumptions fetchByPrimaryKey(
        int chAssumptionsSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ch assumptionses.
    *
    * @return the ch assumptionses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ChAssumptions> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ch assumptionses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ch assumptionses
    * @param end the upper bound of the range of ch assumptionses (not inclusive)
    * @return the range of ch assumptionses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ChAssumptions> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ch assumptionses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ch assumptionses
    * @param end the upper bound of the range of ch assumptionses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ch assumptionses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ChAssumptions> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ch assumptionses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ch assumptionses.
    *
    * @return the number of ch assumptionses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
