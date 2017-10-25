package com.stpl.app.service.persistence;

import com.stpl.app.model.AccrualMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the accrual master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AccrualMasterPersistenceImpl
 * @see AccrualMasterUtil
 * @generated
 */
public interface AccrualMasterPersistence extends BasePersistence<AccrualMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link AccrualMasterUtil} to access the accrual master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the accrual master in the entity cache if it is enabled.
    *
    * @param accrualMaster the accrual master
    */
    public void cacheResult(com.stpl.app.model.AccrualMaster accrualMaster);

    /**
    * Caches the accrual masters in the entity cache if it is enabled.
    *
    * @param accrualMasters the accrual masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.AccrualMaster> accrualMasters);

    /**
    * Creates a new accrual master with the primary key. Does not add the accrual master to the database.
    *
    * @param accrualMasterSid the primary key for the new accrual master
    * @return the new accrual master
    */
    public com.stpl.app.model.AccrualMaster create(int accrualMasterSid);

    /**
    * Removes the accrual master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param accrualMasterSid the primary key of the accrual master
    * @return the accrual master that was removed
    * @throws com.stpl.app.NoSuchAccrualMasterException if a accrual master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.AccrualMaster remove(int accrualMasterSid)
        throws com.stpl.app.NoSuchAccrualMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.AccrualMaster updateImpl(
        com.stpl.app.model.AccrualMaster accrualMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the accrual master with the primary key or throws a {@link com.stpl.app.NoSuchAccrualMasterException} if it could not be found.
    *
    * @param accrualMasterSid the primary key of the accrual master
    * @return the accrual master
    * @throws com.stpl.app.NoSuchAccrualMasterException if a accrual master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.AccrualMaster findByPrimaryKey(
        int accrualMasterSid)
        throws com.stpl.app.NoSuchAccrualMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the accrual master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param accrualMasterSid the primary key of the accrual master
    * @return the accrual master, or <code>null</code> if a accrual master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.AccrualMaster fetchByPrimaryKey(
        int accrualMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the accrual masters.
    *
    * @return the accrual masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.AccrualMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the accrual masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AccrualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of accrual masters
    * @param end the upper bound of the range of accrual masters (not inclusive)
    * @return the range of accrual masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.AccrualMaster> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the accrual masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AccrualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of accrual masters
    * @param end the upper bound of the range of accrual masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of accrual masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.AccrualMaster> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the accrual masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of accrual masters.
    *
    * @return the number of accrual masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
