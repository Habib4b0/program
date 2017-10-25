package com.stpl.app.service.persistence;

import com.stpl.app.model.StNmDiscountProjMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the st nm discount proj master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StNmDiscountProjMasterPersistenceImpl
 * @see StNmDiscountProjMasterUtil
 * @generated
 */
public interface StNmDiscountProjMasterPersistence extends BasePersistence<StNmDiscountProjMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link StNmDiscountProjMasterUtil} to access the st nm discount proj master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the st nm discount proj master in the entity cache if it is enabled.
    *
    * @param stNmDiscountProjMaster the st nm discount proj master
    */
    public void cacheResult(
        com.stpl.app.model.StNmDiscountProjMaster stNmDiscountProjMaster);

    /**
    * Caches the st nm discount proj masters in the entity cache if it is enabled.
    *
    * @param stNmDiscountProjMasters the st nm discount proj masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.StNmDiscountProjMaster> stNmDiscountProjMasters);

    /**
    * Creates a new st nm discount proj master with the primary key. Does not add the st nm discount proj master to the database.
    *
    * @param stNmDiscountProjMasterPK the primary key for the new st nm discount proj master
    * @return the new st nm discount proj master
    */
    public com.stpl.app.model.StNmDiscountProjMaster create(
        StNmDiscountProjMasterPK stNmDiscountProjMasterPK);

    /**
    * Removes the st nm discount proj master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stNmDiscountProjMasterPK the primary key of the st nm discount proj master
    * @return the st nm discount proj master that was removed
    * @throws com.stpl.app.NoSuchStNmDiscountProjMasterException if a st nm discount proj master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StNmDiscountProjMaster remove(
        StNmDiscountProjMasterPK stNmDiscountProjMasterPK)
        throws com.stpl.app.NoSuchStNmDiscountProjMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.StNmDiscountProjMaster updateImpl(
        com.stpl.app.model.StNmDiscountProjMaster stNmDiscountProjMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the st nm discount proj master with the primary key or throws a {@link com.stpl.app.NoSuchStNmDiscountProjMasterException} if it could not be found.
    *
    * @param stNmDiscountProjMasterPK the primary key of the st nm discount proj master
    * @return the st nm discount proj master
    * @throws com.stpl.app.NoSuchStNmDiscountProjMasterException if a st nm discount proj master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StNmDiscountProjMaster findByPrimaryKey(
        StNmDiscountProjMasterPK stNmDiscountProjMasterPK)
        throws com.stpl.app.NoSuchStNmDiscountProjMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the st nm discount proj master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param stNmDiscountProjMasterPK the primary key of the st nm discount proj master
    * @return the st nm discount proj master, or <code>null</code> if a st nm discount proj master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StNmDiscountProjMaster fetchByPrimaryKey(
        StNmDiscountProjMasterPK stNmDiscountProjMasterPK)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the st nm discount proj masters.
    *
    * @return the st nm discount proj masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StNmDiscountProjMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the st nm discount proj masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st nm discount proj masters
    * @param end the upper bound of the range of st nm discount proj masters (not inclusive)
    * @return the range of st nm discount proj masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StNmDiscountProjMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the st nm discount proj masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st nm discount proj masters
    * @param end the upper bound of the range of st nm discount proj masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of st nm discount proj masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StNmDiscountProjMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the st nm discount proj masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of st nm discount proj masters.
    *
    * @return the number of st nm discount proj masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
