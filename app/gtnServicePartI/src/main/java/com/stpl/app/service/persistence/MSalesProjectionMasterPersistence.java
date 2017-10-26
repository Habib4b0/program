package com.stpl.app.service.persistence;

import com.stpl.app.model.MSalesProjectionMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the m sales projection master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MSalesProjectionMasterPersistenceImpl
 * @see MSalesProjectionMasterUtil
 * @generated
 */
public interface MSalesProjectionMasterPersistence extends BasePersistence<MSalesProjectionMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link MSalesProjectionMasterUtil} to access the m sales projection master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the m sales projection master in the entity cache if it is enabled.
    *
    * @param mSalesProjectionMaster the m sales projection master
    */
    public void cacheResult(
        com.stpl.app.model.MSalesProjectionMaster mSalesProjectionMaster);

    /**
    * Caches the m sales projection masters in the entity cache if it is enabled.
    *
    * @param mSalesProjectionMasters the m sales projection masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.MSalesProjectionMaster> mSalesProjectionMasters);

    /**
    * Creates a new m sales projection master with the primary key. Does not add the m sales projection master to the database.
    *
    * @param projectionDetailsSid the primary key for the new m sales projection master
    * @return the new m sales projection master
    */
    public com.stpl.app.model.MSalesProjectionMaster create(
        int projectionDetailsSid);

    /**
    * Removes the m sales projection master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param projectionDetailsSid the primary key of the m sales projection master
    * @return the m sales projection master that was removed
    * @throws com.stpl.app.NoSuchMSalesProjectionMasterException if a m sales projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.MSalesProjectionMaster remove(
        int projectionDetailsSid)
        throws com.stpl.app.NoSuchMSalesProjectionMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.MSalesProjectionMaster updateImpl(
        com.stpl.app.model.MSalesProjectionMaster mSalesProjectionMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the m sales projection master with the primary key or throws a {@link com.stpl.app.NoSuchMSalesProjectionMasterException} if it could not be found.
    *
    * @param projectionDetailsSid the primary key of the m sales projection master
    * @return the m sales projection master
    * @throws com.stpl.app.NoSuchMSalesProjectionMasterException if a m sales projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.MSalesProjectionMaster findByPrimaryKey(
        int projectionDetailsSid)
        throws com.stpl.app.NoSuchMSalesProjectionMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the m sales projection master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param projectionDetailsSid the primary key of the m sales projection master
    * @return the m sales projection master, or <code>null</code> if a m sales projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.MSalesProjectionMaster fetchByPrimaryKey(
        int projectionDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the m sales projection masters.
    *
    * @return the m sales projection masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.MSalesProjectionMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the m sales projection masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of m sales projection masters
    * @param end the upper bound of the range of m sales projection masters (not inclusive)
    * @return the range of m sales projection masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.MSalesProjectionMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the m sales projection masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of m sales projection masters
    * @param end the upper bound of the range of m sales projection masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of m sales projection masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.MSalesProjectionMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the m sales projection masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of m sales projection masters.
    *
    * @return the number of m sales projection masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
