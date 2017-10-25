package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.IvldItemMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ivld item master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldItemMasterPersistenceImpl
 * @see IvldItemMasterUtil
 * @generated
 */
public interface IvldItemMasterPersistence extends BasePersistence<IvldItemMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link IvldItemMasterUtil} to access the ivld item master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the ivld item master in the entity cache if it is enabled.
    *
    * @param ivldItemMaster the ivld item master
    */
    public void cacheResult(
        com.stpl.app.parttwo.model.IvldItemMaster ivldItemMaster);

    /**
    * Caches the ivld item masters in the entity cache if it is enabled.
    *
    * @param ivldItemMasters the ivld item masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.IvldItemMaster> ivldItemMasters);

    /**
    * Creates a new ivld item master with the primary key. Does not add the ivld item master to the database.
    *
    * @param ivldItemMasterSid the primary key for the new ivld item master
    * @return the new ivld item master
    */
    public com.stpl.app.parttwo.model.IvldItemMaster create(
        int ivldItemMasterSid);

    /**
    * Removes the ivld item master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldItemMasterSid the primary key of the ivld item master
    * @return the ivld item master that was removed
    * @throws com.stpl.app.parttwo.NoSuchIvldItemMasterException if a ivld item master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.IvldItemMaster remove(
        int ivldItemMasterSid)
        throws com.stpl.app.parttwo.NoSuchIvldItemMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.parttwo.model.IvldItemMaster updateImpl(
        com.stpl.app.parttwo.model.IvldItemMaster ivldItemMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ivld item master with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchIvldItemMasterException} if it could not be found.
    *
    * @param ivldItemMasterSid the primary key of the ivld item master
    * @return the ivld item master
    * @throws com.stpl.app.parttwo.NoSuchIvldItemMasterException if a ivld item master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.IvldItemMaster findByPrimaryKey(
        int ivldItemMasterSid)
        throws com.stpl.app.parttwo.NoSuchIvldItemMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ivld item master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldItemMasterSid the primary key of the ivld item master
    * @return the ivld item master, or <code>null</code> if a ivld item master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.IvldItemMaster fetchByPrimaryKey(
        int ivldItemMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ivld item masters.
    *
    * @return the ivld item masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.IvldItemMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ivld item masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld item masters
    * @param end the upper bound of the range of ivld item masters (not inclusive)
    * @return the range of ivld item masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.IvldItemMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ivld item masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld item masters
    * @param end the upper bound of the range of ivld item masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ivld item masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.IvldItemMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ivld item masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ivld item masters.
    *
    * @return the number of ivld item masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
