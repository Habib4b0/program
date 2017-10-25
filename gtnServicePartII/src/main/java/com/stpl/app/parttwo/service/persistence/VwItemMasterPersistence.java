package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.VwItemMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the vw item master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwItemMasterPersistenceImpl
 * @see VwItemMasterUtil
 * @generated
 */
public interface VwItemMasterPersistence extends BasePersistence<VwItemMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link VwItemMasterUtil} to access the vw item master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the vw item master in the entity cache if it is enabled.
    *
    * @param vwItemMaster the vw item master
    */
    public void cacheResult(
        com.stpl.app.parttwo.model.VwItemMaster vwItemMaster);

    /**
    * Caches the vw item masters in the entity cache if it is enabled.
    *
    * @param vwItemMasters the vw item masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.VwItemMaster> vwItemMasters);

    /**
    * Creates a new vw item master with the primary key. Does not add the vw item master to the database.
    *
    * @param itemMasterSid the primary key for the new vw item master
    * @return the new vw item master
    */
    public com.stpl.app.parttwo.model.VwItemMaster create(int itemMasterSid);

    /**
    * Removes the vw item master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param itemMasterSid the primary key of the vw item master
    * @return the vw item master that was removed
    * @throws com.stpl.app.parttwo.NoSuchVwItemMasterException if a vw item master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.VwItemMaster remove(int itemMasterSid)
        throws com.stpl.app.parttwo.NoSuchVwItemMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.parttwo.model.VwItemMaster updateImpl(
        com.stpl.app.parttwo.model.VwItemMaster vwItemMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the vw item master with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchVwItemMasterException} if it could not be found.
    *
    * @param itemMasterSid the primary key of the vw item master
    * @return the vw item master
    * @throws com.stpl.app.parttwo.NoSuchVwItemMasterException if a vw item master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.VwItemMaster findByPrimaryKey(
        int itemMasterSid)
        throws com.stpl.app.parttwo.NoSuchVwItemMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the vw item master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param itemMasterSid the primary key of the vw item master
    * @return the vw item master, or <code>null</code> if a vw item master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.VwItemMaster fetchByPrimaryKey(
        int itemMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the vw item masters.
    *
    * @return the vw item masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.VwItemMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the vw item masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw item masters
    * @param end the upper bound of the range of vw item masters (not inclusive)
    * @return the range of vw item masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.VwItemMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the vw item masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw item masters
    * @param end the upper bound of the range of vw item masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of vw item masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.VwItemMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the vw item masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of vw item masters.
    *
    * @return the number of vw item masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
