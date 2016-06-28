package com.stpl.app.service.persistence;

import com.stpl.app.model.StChSalesProjectionMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the st ch sales projection master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StChSalesProjectionMasterPersistenceImpl
 * @see StChSalesProjectionMasterUtil
 * @generated
 */
public interface StChSalesProjectionMasterPersistence extends BasePersistence<StChSalesProjectionMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link StChSalesProjectionMasterUtil} to access the st ch sales projection master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the st ch sales projection master in the entity cache if it is enabled.
    *
    * @param stChSalesProjectionMaster the st ch sales projection master
    */
    public void cacheResult(
        com.stpl.app.model.StChSalesProjectionMaster stChSalesProjectionMaster);

    /**
    * Caches the st ch sales projection masters in the entity cache if it is enabled.
    *
    * @param stChSalesProjectionMasters the st ch sales projection masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.StChSalesProjectionMaster> stChSalesProjectionMasters);

    /**
    * Creates a new st ch sales projection master with the primary key. Does not add the st ch sales projection master to the database.
    *
    * @param stChSalesProjectionMasterPK the primary key for the new st ch sales projection master
    * @return the new st ch sales projection master
    */
    public com.stpl.app.model.StChSalesProjectionMaster create(
        StChSalesProjectionMasterPK stChSalesProjectionMasterPK);

    /**
    * Removes the st ch sales projection master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stChSalesProjectionMasterPK the primary key of the st ch sales projection master
    * @return the st ch sales projection master that was removed
    * @throws com.stpl.app.NoSuchStChSalesProjectionMasterException if a st ch sales projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StChSalesProjectionMaster remove(
        StChSalesProjectionMasterPK stChSalesProjectionMasterPK)
        throws com.stpl.app.NoSuchStChSalesProjectionMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.StChSalesProjectionMaster updateImpl(
        com.stpl.app.model.StChSalesProjectionMaster stChSalesProjectionMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the st ch sales projection master with the primary key or throws a {@link com.stpl.app.NoSuchStChSalesProjectionMasterException} if it could not be found.
    *
    * @param stChSalesProjectionMasterPK the primary key of the st ch sales projection master
    * @return the st ch sales projection master
    * @throws com.stpl.app.NoSuchStChSalesProjectionMasterException if a st ch sales projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StChSalesProjectionMaster findByPrimaryKey(
        StChSalesProjectionMasterPK stChSalesProjectionMasterPK)
        throws com.stpl.app.NoSuchStChSalesProjectionMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the st ch sales projection master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param stChSalesProjectionMasterPK the primary key of the st ch sales projection master
    * @return the st ch sales projection master, or <code>null</code> if a st ch sales projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StChSalesProjectionMaster fetchByPrimaryKey(
        StChSalesProjectionMasterPK stChSalesProjectionMasterPK)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the st ch sales projection masters.
    *
    * @return the st ch sales projection masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StChSalesProjectionMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the st ch sales projection masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st ch sales projection masters
    * @param end the upper bound of the range of st ch sales projection masters (not inclusive)
    * @return the range of st ch sales projection masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StChSalesProjectionMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the st ch sales projection masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st ch sales projection masters
    * @param end the upper bound of the range of st ch sales projection masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of st ch sales projection masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StChSalesProjectionMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the st ch sales projection masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of st ch sales projection masters.
    *
    * @return the number of st ch sales projection masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
