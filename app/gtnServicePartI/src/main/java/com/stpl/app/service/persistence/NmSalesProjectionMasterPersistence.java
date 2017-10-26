package com.stpl.app.service.persistence;

import com.stpl.app.model.NmSalesProjectionMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the nm sales projection master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NmSalesProjectionMasterPersistenceImpl
 * @see NmSalesProjectionMasterUtil
 * @generated
 */
public interface NmSalesProjectionMasterPersistence extends BasePersistence<NmSalesProjectionMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link NmSalesProjectionMasterUtil} to access the nm sales projection master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the nm sales projection master in the entity cache if it is enabled.
    *
    * @param nmSalesProjectionMaster the nm sales projection master
    */
    public void cacheResult(
        com.stpl.app.model.NmSalesProjectionMaster nmSalesProjectionMaster);

    /**
    * Caches the nm sales projection masters in the entity cache if it is enabled.
    *
    * @param nmSalesProjectionMasters the nm sales projection masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.NmSalesProjectionMaster> nmSalesProjectionMasters);

    /**
    * Creates a new nm sales projection master with the primary key. Does not add the nm sales projection master to the database.
    *
    * @param projectionDetailsSid the primary key for the new nm sales projection master
    * @return the new nm sales projection master
    */
    public com.stpl.app.model.NmSalesProjectionMaster create(
        int projectionDetailsSid);

    /**
    * Removes the nm sales projection master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param projectionDetailsSid the primary key of the nm sales projection master
    * @return the nm sales projection master that was removed
    * @throws com.stpl.app.NoSuchNmSalesProjectionMasterException if a nm sales projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.NmSalesProjectionMaster remove(
        int projectionDetailsSid)
        throws com.stpl.app.NoSuchNmSalesProjectionMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.NmSalesProjectionMaster updateImpl(
        com.stpl.app.model.NmSalesProjectionMaster nmSalesProjectionMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the nm sales projection master with the primary key or throws a {@link com.stpl.app.NoSuchNmSalesProjectionMasterException} if it could not be found.
    *
    * @param projectionDetailsSid the primary key of the nm sales projection master
    * @return the nm sales projection master
    * @throws com.stpl.app.NoSuchNmSalesProjectionMasterException if a nm sales projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.NmSalesProjectionMaster findByPrimaryKey(
        int projectionDetailsSid)
        throws com.stpl.app.NoSuchNmSalesProjectionMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the nm sales projection master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param projectionDetailsSid the primary key of the nm sales projection master
    * @return the nm sales projection master, or <code>null</code> if a nm sales projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.NmSalesProjectionMaster fetchByPrimaryKey(
        int projectionDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the nm sales projection masters.
    *
    * @return the nm sales projection masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.NmSalesProjectionMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the nm sales projection masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of nm sales projection masters
    * @param end the upper bound of the range of nm sales projection masters (not inclusive)
    * @return the range of nm sales projection masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.NmSalesProjectionMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the nm sales projection masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of nm sales projection masters
    * @param end the upper bound of the range of nm sales projection masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of nm sales projection masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.NmSalesProjectionMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the nm sales projection masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of nm sales projection masters.
    *
    * @return the number of nm sales projection masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
