package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldLotMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ivld lot master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldLotMasterPersistenceImpl
 * @see IvldLotMasterUtil
 * @generated
 */
public interface IvldLotMasterPersistence extends BasePersistence<IvldLotMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link IvldLotMasterUtil} to access the ivld lot master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the ivld lot master in the entity cache if it is enabled.
    *
    * @param ivldLotMaster the ivld lot master
    */
    public void cacheResult(com.stpl.app.model.IvldLotMaster ivldLotMaster);

    /**
    * Caches the ivld lot masters in the entity cache if it is enabled.
    *
    * @param ivldLotMasters the ivld lot masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.IvldLotMaster> ivldLotMasters);

    /**
    * Creates a new ivld lot master with the primary key. Does not add the ivld lot master to the database.
    *
    * @param ivldLotMasterSid the primary key for the new ivld lot master
    * @return the new ivld lot master
    */
    public com.stpl.app.model.IvldLotMaster create(int ivldLotMasterSid);

    /**
    * Removes the ivld lot master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldLotMasterSid the primary key of the ivld lot master
    * @return the ivld lot master that was removed
    * @throws com.stpl.app.NoSuchIvldLotMasterException if a ivld lot master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IvldLotMaster remove(int ivldLotMasterSid)
        throws com.stpl.app.NoSuchIvldLotMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.IvldLotMaster updateImpl(
        com.stpl.app.model.IvldLotMaster ivldLotMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ivld lot master with the primary key or throws a {@link com.stpl.app.NoSuchIvldLotMasterException} if it could not be found.
    *
    * @param ivldLotMasterSid the primary key of the ivld lot master
    * @return the ivld lot master
    * @throws com.stpl.app.NoSuchIvldLotMasterException if a ivld lot master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IvldLotMaster findByPrimaryKey(
        int ivldLotMasterSid)
        throws com.stpl.app.NoSuchIvldLotMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ivld lot master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldLotMasterSid the primary key of the ivld lot master
    * @return the ivld lot master, or <code>null</code> if a ivld lot master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IvldLotMaster fetchByPrimaryKey(
        int ivldLotMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ivld lot masters.
    *
    * @return the ivld lot masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IvldLotMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ivld lot masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldLotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld lot masters
    * @param end the upper bound of the range of ivld lot masters (not inclusive)
    * @return the range of ivld lot masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IvldLotMaster> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ivld lot masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldLotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld lot masters
    * @param end the upper bound of the range of ivld lot masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ivld lot masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IvldLotMaster> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ivld lot masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ivld lot masters.
    *
    * @return the number of ivld lot masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
