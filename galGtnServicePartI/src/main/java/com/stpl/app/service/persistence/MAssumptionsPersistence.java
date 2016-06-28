package com.stpl.app.service.persistence;

import com.stpl.app.model.MAssumptions;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the m assumptions service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MAssumptionsPersistenceImpl
 * @see MAssumptionsUtil
 * @generated
 */
public interface MAssumptionsPersistence extends BasePersistence<MAssumptions> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link MAssumptionsUtil} to access the m assumptions persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the m assumptions in the entity cache if it is enabled.
    *
    * @param mAssumptions the m assumptions
    */
    public void cacheResult(com.stpl.app.model.MAssumptions mAssumptions);

    /**
    * Caches the m assumptionses in the entity cache if it is enabled.
    *
    * @param mAssumptionses the m assumptionses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.MAssumptions> mAssumptionses);

    /**
    * Creates a new m assumptions with the primary key. Does not add the m assumptions to the database.
    *
    * @param mAssumptionsSid the primary key for the new m assumptions
    * @return the new m assumptions
    */
    public com.stpl.app.model.MAssumptions create(int mAssumptionsSid);

    /**
    * Removes the m assumptions with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param mAssumptionsSid the primary key of the m assumptions
    * @return the m assumptions that was removed
    * @throws com.stpl.app.NoSuchMAssumptionsException if a m assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.MAssumptions remove(int mAssumptionsSid)
        throws com.stpl.app.NoSuchMAssumptionsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.MAssumptions updateImpl(
        com.stpl.app.model.MAssumptions mAssumptions)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the m assumptions with the primary key or throws a {@link com.stpl.app.NoSuchMAssumptionsException} if it could not be found.
    *
    * @param mAssumptionsSid the primary key of the m assumptions
    * @return the m assumptions
    * @throws com.stpl.app.NoSuchMAssumptionsException if a m assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.MAssumptions findByPrimaryKey(int mAssumptionsSid)
        throws com.stpl.app.NoSuchMAssumptionsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the m assumptions with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param mAssumptionsSid the primary key of the m assumptions
    * @return the m assumptions, or <code>null</code> if a m assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.MAssumptions fetchByPrimaryKey(
        int mAssumptionsSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the m assumptionses.
    *
    * @return the m assumptionses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.MAssumptions> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the m assumptionses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of m assumptionses
    * @param end the upper bound of the range of m assumptionses (not inclusive)
    * @return the range of m assumptionses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.MAssumptions> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the m assumptionses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of m assumptionses
    * @param end the upper bound of the range of m assumptionses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of m assumptionses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.MAssumptions> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the m assumptionses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of m assumptionses.
    *
    * @return the number of m assumptionses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
