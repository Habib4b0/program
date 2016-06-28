package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.CffMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the cff master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CffMasterPersistenceImpl
 * @see CffMasterUtil
 * @generated
 */
public interface CffMasterPersistence extends BasePersistence<CffMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link CffMasterUtil} to access the cff master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the cff master in the entity cache if it is enabled.
    *
    * @param cffMaster the cff master
    */
    public void cacheResult(com.stpl.app.parttwo.model.CffMaster cffMaster);

    /**
    * Caches the cff masters in the entity cache if it is enabled.
    *
    * @param cffMasters the cff masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.CffMaster> cffMasters);

    /**
    * Creates a new cff master with the primary key. Does not add the cff master to the database.
    *
    * @param cffMasterSid the primary key for the new cff master
    * @return the new cff master
    */
    public com.stpl.app.parttwo.model.CffMaster create(int cffMasterSid);

    /**
    * Removes the cff master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param cffMasterSid the primary key of the cff master
    * @return the cff master that was removed
    * @throws com.stpl.app.parttwo.NoSuchCffMasterException if a cff master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.CffMaster remove(int cffMasterSid)
        throws com.stpl.app.parttwo.NoSuchCffMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.parttwo.model.CffMaster updateImpl(
        com.stpl.app.parttwo.model.CffMaster cffMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the cff master with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchCffMasterException} if it could not be found.
    *
    * @param cffMasterSid the primary key of the cff master
    * @return the cff master
    * @throws com.stpl.app.parttwo.NoSuchCffMasterException if a cff master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.CffMaster findByPrimaryKey(
        int cffMasterSid)
        throws com.stpl.app.parttwo.NoSuchCffMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the cff master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param cffMasterSid the primary key of the cff master
    * @return the cff master, or <code>null</code> if a cff master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.CffMaster fetchByPrimaryKey(
        int cffMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the cff masters.
    *
    * @return the cff masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.CffMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the cff masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cff masters
    * @param end the upper bound of the range of cff masters (not inclusive)
    * @return the range of cff masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.CffMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the cff masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cff masters
    * @param end the upper bound of the range of cff masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of cff masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.CffMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the cff masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of cff masters.
    *
    * @return the number of cff masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
