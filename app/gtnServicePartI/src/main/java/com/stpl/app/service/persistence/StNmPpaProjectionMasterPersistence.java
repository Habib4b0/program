package com.stpl.app.service.persistence;

import com.stpl.app.model.StNmPpaProjectionMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the st nm ppa projection master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StNmPpaProjectionMasterPersistenceImpl
 * @see StNmPpaProjectionMasterUtil
 * @generated
 */
public interface StNmPpaProjectionMasterPersistence extends BasePersistence<StNmPpaProjectionMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link StNmPpaProjectionMasterUtil} to access the st nm ppa projection master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the st nm ppa projection master in the entity cache if it is enabled.
    *
    * @param stNmPpaProjectionMaster the st nm ppa projection master
    */
    public void cacheResult(
        com.stpl.app.model.StNmPpaProjectionMaster stNmPpaProjectionMaster);

    /**
    * Caches the st nm ppa projection masters in the entity cache if it is enabled.
    *
    * @param stNmPpaProjectionMasters the st nm ppa projection masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.StNmPpaProjectionMaster> stNmPpaProjectionMasters);

    /**
    * Creates a new st nm ppa projection master with the primary key. Does not add the st nm ppa projection master to the database.
    *
    * @param stNmPpaProjectionMasterPK the primary key for the new st nm ppa projection master
    * @return the new st nm ppa projection master
    */
    public com.stpl.app.model.StNmPpaProjectionMaster create(
        StNmPpaProjectionMasterPK stNmPpaProjectionMasterPK);

    /**
    * Removes the st nm ppa projection master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stNmPpaProjectionMasterPK the primary key of the st nm ppa projection master
    * @return the st nm ppa projection master that was removed
    * @throws com.stpl.app.NoSuchStNmPpaProjectionMasterException if a st nm ppa projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StNmPpaProjectionMaster remove(
        StNmPpaProjectionMasterPK stNmPpaProjectionMasterPK)
        throws com.stpl.app.NoSuchStNmPpaProjectionMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.StNmPpaProjectionMaster updateImpl(
        com.stpl.app.model.StNmPpaProjectionMaster stNmPpaProjectionMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the st nm ppa projection master with the primary key or throws a {@link com.stpl.app.NoSuchStNmPpaProjectionMasterException} if it could not be found.
    *
    * @param stNmPpaProjectionMasterPK the primary key of the st nm ppa projection master
    * @return the st nm ppa projection master
    * @throws com.stpl.app.NoSuchStNmPpaProjectionMasterException if a st nm ppa projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StNmPpaProjectionMaster findByPrimaryKey(
        StNmPpaProjectionMasterPK stNmPpaProjectionMasterPK)
        throws com.stpl.app.NoSuchStNmPpaProjectionMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the st nm ppa projection master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param stNmPpaProjectionMasterPK the primary key of the st nm ppa projection master
    * @return the st nm ppa projection master, or <code>null</code> if a st nm ppa projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StNmPpaProjectionMaster fetchByPrimaryKey(
        StNmPpaProjectionMasterPK stNmPpaProjectionMasterPK)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the st nm ppa projection masters.
    *
    * @return the st nm ppa projection masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StNmPpaProjectionMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the st nm ppa projection masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmPpaProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st nm ppa projection masters
    * @param end the upper bound of the range of st nm ppa projection masters (not inclusive)
    * @return the range of st nm ppa projection masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StNmPpaProjectionMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the st nm ppa projection masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmPpaProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st nm ppa projection masters
    * @param end the upper bound of the range of st nm ppa projection masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of st nm ppa projection masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StNmPpaProjectionMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the st nm ppa projection masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of st nm ppa projection masters.
    *
    * @return the number of st nm ppa projection masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
