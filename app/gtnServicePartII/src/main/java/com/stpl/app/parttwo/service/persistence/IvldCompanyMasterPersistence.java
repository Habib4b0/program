package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.IvldCompanyMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ivld company master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldCompanyMasterPersistenceImpl
 * @see IvldCompanyMasterUtil
 * @generated
 */
public interface IvldCompanyMasterPersistence extends BasePersistence<IvldCompanyMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link IvldCompanyMasterUtil} to access the ivld company master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the ivld company master in the entity cache if it is enabled.
    *
    * @param ivldCompanyMaster the ivld company master
    */
    public void cacheResult(
        com.stpl.app.parttwo.model.IvldCompanyMaster ivldCompanyMaster);

    /**
    * Caches the ivld company masters in the entity cache if it is enabled.
    *
    * @param ivldCompanyMasters the ivld company masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.IvldCompanyMaster> ivldCompanyMasters);

    /**
    * Creates a new ivld company master with the primary key. Does not add the ivld company master to the database.
    *
    * @param ivldCompanyMasterSid the primary key for the new ivld company master
    * @return the new ivld company master
    */
    public com.stpl.app.parttwo.model.IvldCompanyMaster create(
        int ivldCompanyMasterSid);

    /**
    * Removes the ivld company master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldCompanyMasterSid the primary key of the ivld company master
    * @return the ivld company master that was removed
    * @throws com.stpl.app.parttwo.NoSuchIvldCompanyMasterException if a ivld company master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.IvldCompanyMaster remove(
        int ivldCompanyMasterSid)
        throws com.stpl.app.parttwo.NoSuchIvldCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.parttwo.model.IvldCompanyMaster updateImpl(
        com.stpl.app.parttwo.model.IvldCompanyMaster ivldCompanyMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ivld company master with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchIvldCompanyMasterException} if it could not be found.
    *
    * @param ivldCompanyMasterSid the primary key of the ivld company master
    * @return the ivld company master
    * @throws com.stpl.app.parttwo.NoSuchIvldCompanyMasterException if a ivld company master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.IvldCompanyMaster findByPrimaryKey(
        int ivldCompanyMasterSid)
        throws com.stpl.app.parttwo.NoSuchIvldCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ivld company master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldCompanyMasterSid the primary key of the ivld company master
    * @return the ivld company master, or <code>null</code> if a ivld company master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.IvldCompanyMaster fetchByPrimaryKey(
        int ivldCompanyMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ivld company masters.
    *
    * @return the ivld company masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.IvldCompanyMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ivld company masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld company masters
    * @param end the upper bound of the range of ivld company masters (not inclusive)
    * @return the range of ivld company masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.IvldCompanyMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ivld company masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld company masters
    * @param end the upper bound of the range of ivld company masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ivld company masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.IvldCompanyMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ivld company masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ivld company masters.
    *
    * @return the number of ivld company masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
