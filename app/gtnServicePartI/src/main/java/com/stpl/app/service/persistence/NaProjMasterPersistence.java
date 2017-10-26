package com.stpl.app.service.persistence;

import com.stpl.app.model.NaProjMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the na proj master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NaProjMasterPersistenceImpl
 * @see NaProjMasterUtil
 * @generated
 */
public interface NaProjMasterPersistence extends BasePersistence<NaProjMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link NaProjMasterUtil} to access the na proj master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the na proj master in the entity cache if it is enabled.
    *
    * @param naProjMaster the na proj master
    */
    public void cacheResult(com.stpl.app.model.NaProjMaster naProjMaster);

    /**
    * Caches the na proj masters in the entity cache if it is enabled.
    *
    * @param naProjMasters the na proj masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.NaProjMaster> naProjMasters);

    /**
    * Creates a new na proj master with the primary key. Does not add the na proj master to the database.
    *
    * @param naProjMasterSid the primary key for the new na proj master
    * @return the new na proj master
    */
    public com.stpl.app.model.NaProjMaster create(int naProjMasterSid);

    /**
    * Removes the na proj master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param naProjMasterSid the primary key of the na proj master
    * @return the na proj master that was removed
    * @throws com.stpl.app.NoSuchNaProjMasterException if a na proj master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.NaProjMaster remove(int naProjMasterSid)
        throws com.stpl.app.NoSuchNaProjMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.NaProjMaster updateImpl(
        com.stpl.app.model.NaProjMaster naProjMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the na proj master with the primary key or throws a {@link com.stpl.app.NoSuchNaProjMasterException} if it could not be found.
    *
    * @param naProjMasterSid the primary key of the na proj master
    * @return the na proj master
    * @throws com.stpl.app.NoSuchNaProjMasterException if a na proj master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.NaProjMaster findByPrimaryKey(int naProjMasterSid)
        throws com.stpl.app.NoSuchNaProjMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the na proj master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param naProjMasterSid the primary key of the na proj master
    * @return the na proj master, or <code>null</code> if a na proj master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.NaProjMaster fetchByPrimaryKey(
        int naProjMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the na proj masters.
    *
    * @return the na proj masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.NaProjMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the na proj masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NaProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of na proj masters
    * @param end the upper bound of the range of na proj masters (not inclusive)
    * @return the range of na proj masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.NaProjMaster> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the na proj masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NaProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of na proj masters
    * @param end the upper bound of the range of na proj masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of na proj masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.NaProjMaster> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the na proj masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of na proj masters.
    *
    * @return the number of na proj masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
