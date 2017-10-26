package com.stpl.app.service.persistence;

import com.stpl.app.model.Period;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the period service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see PeriodPersistenceImpl
 * @see PeriodUtil
 * @generated
 */
public interface PeriodPersistence extends BasePersistence<Period> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link PeriodUtil} to access the period persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the period in the entity cache if it is enabled.
    *
    * @param period the period
    */
    public void cacheResult(com.stpl.app.model.Period period);

    /**
    * Caches the periods in the entity cache if it is enabled.
    *
    * @param periods the periods
    */
    public void cacheResult(java.util.List<com.stpl.app.model.Period> periods);

    /**
    * Creates a new period with the primary key. Does not add the period to the database.
    *
    * @param periodSid the primary key for the new period
    * @return the new period
    */
    public com.stpl.app.model.Period create(int periodSid);

    /**
    * Removes the period with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param periodSid the primary key of the period
    * @return the period that was removed
    * @throws com.stpl.app.NoSuchPeriodException if a period with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.Period remove(int periodSid)
        throws com.stpl.app.NoSuchPeriodException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.Period updateImpl(
        com.stpl.app.model.Period period)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the period with the primary key or throws a {@link com.stpl.app.NoSuchPeriodException} if it could not be found.
    *
    * @param periodSid the primary key of the period
    * @return the period
    * @throws com.stpl.app.NoSuchPeriodException if a period with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.Period findByPrimaryKey(int periodSid)
        throws com.stpl.app.NoSuchPeriodException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the period with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param periodSid the primary key of the period
    * @return the period, or <code>null</code> if a period with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.Period fetchByPrimaryKey(int periodSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the periods.
    *
    * @return the periods
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.Period> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the periods.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of periods
    * @param end the upper bound of the range of periods (not inclusive)
    * @return the range of periods
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.Period> findAll(int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the periods.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of periods
    * @param end the upper bound of the range of periods (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of periods
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.Period> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the periods from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of periods.
    *
    * @return the number of periods
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
