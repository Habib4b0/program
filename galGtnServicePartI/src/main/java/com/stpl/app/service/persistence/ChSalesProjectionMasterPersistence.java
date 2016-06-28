package com.stpl.app.service.persistence;

import com.stpl.app.model.ChSalesProjectionMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ch sales projection master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ChSalesProjectionMasterPersistenceImpl
 * @see ChSalesProjectionMasterUtil
 * @generated
 */
public interface ChSalesProjectionMasterPersistence extends BasePersistence<ChSalesProjectionMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ChSalesProjectionMasterUtil} to access the ch sales projection master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the ch sales projection master in the entity cache if it is enabled.
    *
    * @param chSalesProjectionMaster the ch sales projection master
    */
    public void cacheResult(
        com.stpl.app.model.ChSalesProjectionMaster chSalesProjectionMaster);

    /**
    * Caches the ch sales projection masters in the entity cache if it is enabled.
    *
    * @param chSalesProjectionMasters the ch sales projection masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.ChSalesProjectionMaster> chSalesProjectionMasters);

    /**
    * Creates a new ch sales projection master with the primary key. Does not add the ch sales projection master to the database.
    *
    * @param projectionDetailsSid the primary key for the new ch sales projection master
    * @return the new ch sales projection master
    */
    public com.stpl.app.model.ChSalesProjectionMaster create(
        int projectionDetailsSid);

    /**
    * Removes the ch sales projection master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param projectionDetailsSid the primary key of the ch sales projection master
    * @return the ch sales projection master that was removed
    * @throws com.stpl.app.NoSuchChSalesProjectionMasterException if a ch sales projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ChSalesProjectionMaster remove(
        int projectionDetailsSid)
        throws com.stpl.app.NoSuchChSalesProjectionMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.ChSalesProjectionMaster updateImpl(
        com.stpl.app.model.ChSalesProjectionMaster chSalesProjectionMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ch sales projection master with the primary key or throws a {@link com.stpl.app.NoSuchChSalesProjectionMasterException} if it could not be found.
    *
    * @param projectionDetailsSid the primary key of the ch sales projection master
    * @return the ch sales projection master
    * @throws com.stpl.app.NoSuchChSalesProjectionMasterException if a ch sales projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ChSalesProjectionMaster findByPrimaryKey(
        int projectionDetailsSid)
        throws com.stpl.app.NoSuchChSalesProjectionMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ch sales projection master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param projectionDetailsSid the primary key of the ch sales projection master
    * @return the ch sales projection master, or <code>null</code> if a ch sales projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ChSalesProjectionMaster fetchByPrimaryKey(
        int projectionDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ch sales projection masters.
    *
    * @return the ch sales projection masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ChSalesProjectionMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ch sales projection masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ch sales projection masters
    * @param end the upper bound of the range of ch sales projection masters (not inclusive)
    * @return the range of ch sales projection masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ChSalesProjectionMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ch sales projection masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ch sales projection masters
    * @param end the upper bound of the range of ch sales projection masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ch sales projection masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ChSalesProjectionMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ch sales projection masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ch sales projection masters.
    *
    * @return the number of ch sales projection masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
