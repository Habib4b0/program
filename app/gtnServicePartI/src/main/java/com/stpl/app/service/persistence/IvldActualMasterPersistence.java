package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldActualMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ivld actual master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldActualMasterPersistenceImpl
 * @see IvldActualMasterUtil
 * @generated
 */
public interface IvldActualMasterPersistence extends BasePersistence<IvldActualMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link IvldActualMasterUtil} to access the ivld actual master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the ivld actual master in the entity cache if it is enabled.
    *
    * @param ivldActualMaster the ivld actual master
    */
    public void cacheResult(
        com.stpl.app.model.IvldActualMaster ivldActualMaster);

    /**
    * Caches the ivld actual masters in the entity cache if it is enabled.
    *
    * @param ivldActualMasters the ivld actual masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.IvldActualMaster> ivldActualMasters);

    /**
    * Creates a new ivld actual master with the primary key. Does not add the ivld actual master to the database.
    *
    * @param ivldActualMasterSid the primary key for the new ivld actual master
    * @return the new ivld actual master
    */
    public com.stpl.app.model.IvldActualMaster create(int ivldActualMasterSid);

    /**
    * Removes the ivld actual master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldActualMasterSid the primary key of the ivld actual master
    * @return the ivld actual master that was removed
    * @throws com.stpl.app.NoSuchIvldActualMasterException if a ivld actual master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IvldActualMaster remove(int ivldActualMasterSid)
        throws com.stpl.app.NoSuchIvldActualMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.IvldActualMaster updateImpl(
        com.stpl.app.model.IvldActualMaster ivldActualMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ivld actual master with the primary key or throws a {@link com.stpl.app.NoSuchIvldActualMasterException} if it could not be found.
    *
    * @param ivldActualMasterSid the primary key of the ivld actual master
    * @return the ivld actual master
    * @throws com.stpl.app.NoSuchIvldActualMasterException if a ivld actual master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IvldActualMaster findByPrimaryKey(
        int ivldActualMasterSid)
        throws com.stpl.app.NoSuchIvldActualMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ivld actual master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldActualMasterSid the primary key of the ivld actual master
    * @return the ivld actual master, or <code>null</code> if a ivld actual master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IvldActualMaster fetchByPrimaryKey(
        int ivldActualMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ivld actual masters.
    *
    * @return the ivld actual masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IvldActualMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ivld actual masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldActualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld actual masters
    * @param end the upper bound of the range of ivld actual masters (not inclusive)
    * @return the range of ivld actual masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IvldActualMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ivld actual masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldActualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld actual masters
    * @param end the upper bound of the range of ivld actual masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ivld actual masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IvldActualMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ivld actual masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ivld actual masters.
    *
    * @return the number of ivld actual masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
