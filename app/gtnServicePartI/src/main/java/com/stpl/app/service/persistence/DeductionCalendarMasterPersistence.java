package com.stpl.app.service.persistence;

import com.stpl.app.model.DeductionCalendarMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the deduction calendar master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see DeductionCalendarMasterPersistenceImpl
 * @see DeductionCalendarMasterUtil
 * @generated
 */
public interface DeductionCalendarMasterPersistence extends BasePersistence<DeductionCalendarMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link DeductionCalendarMasterUtil} to access the deduction calendar master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the deduction calendar master in the entity cache if it is enabled.
    *
    * @param deductionCalendarMaster the deduction calendar master
    */
    public void cacheResult(
        com.stpl.app.model.DeductionCalendarMaster deductionCalendarMaster);

    /**
    * Caches the deduction calendar masters in the entity cache if it is enabled.
    *
    * @param deductionCalendarMasters the deduction calendar masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.DeductionCalendarMaster> deductionCalendarMasters);

    /**
    * Creates a new deduction calendar master with the primary key. Does not add the deduction calendar master to the database.
    *
    * @param deductionCalendarMasterSid the primary key for the new deduction calendar master
    * @return the new deduction calendar master
    */
    public com.stpl.app.model.DeductionCalendarMaster create(
        int deductionCalendarMasterSid);

    /**
    * Removes the deduction calendar master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param deductionCalendarMasterSid the primary key of the deduction calendar master
    * @return the deduction calendar master that was removed
    * @throws com.stpl.app.NoSuchDeductionCalendarMasterException if a deduction calendar master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.DeductionCalendarMaster remove(
        int deductionCalendarMasterSid)
        throws com.stpl.app.NoSuchDeductionCalendarMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.DeductionCalendarMaster updateImpl(
        com.stpl.app.model.DeductionCalendarMaster deductionCalendarMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the deduction calendar master with the primary key or throws a {@link com.stpl.app.NoSuchDeductionCalendarMasterException} if it could not be found.
    *
    * @param deductionCalendarMasterSid the primary key of the deduction calendar master
    * @return the deduction calendar master
    * @throws com.stpl.app.NoSuchDeductionCalendarMasterException if a deduction calendar master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.DeductionCalendarMaster findByPrimaryKey(
        int deductionCalendarMasterSid)
        throws com.stpl.app.NoSuchDeductionCalendarMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the deduction calendar master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param deductionCalendarMasterSid the primary key of the deduction calendar master
    * @return the deduction calendar master, or <code>null</code> if a deduction calendar master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.DeductionCalendarMaster fetchByPrimaryKey(
        int deductionCalendarMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the deduction calendar masters.
    *
    * @return the deduction calendar masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.DeductionCalendarMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the deduction calendar masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionCalendarMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of deduction calendar masters
    * @param end the upper bound of the range of deduction calendar masters (not inclusive)
    * @return the range of deduction calendar masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.DeductionCalendarMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the deduction calendar masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionCalendarMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of deduction calendar masters
    * @param end the upper bound of the range of deduction calendar masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of deduction calendar masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.DeductionCalendarMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the deduction calendar masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of deduction calendar masters.
    *
    * @return the number of deduction calendar masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
