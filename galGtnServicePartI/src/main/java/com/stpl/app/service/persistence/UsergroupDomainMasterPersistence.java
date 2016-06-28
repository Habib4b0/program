package com.stpl.app.service.persistence;

import com.stpl.app.model.UsergroupDomainMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the usergroup domain master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see UsergroupDomainMasterPersistenceImpl
 * @see UsergroupDomainMasterUtil
 * @generated
 */
public interface UsergroupDomainMasterPersistence extends BasePersistence<UsergroupDomainMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link UsergroupDomainMasterUtil} to access the usergroup domain master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the usergroup domain master in the entity cache if it is enabled.
    *
    * @param usergroupDomainMaster the usergroup domain master
    */
    public void cacheResult(
        com.stpl.app.model.UsergroupDomainMaster usergroupDomainMaster);

    /**
    * Caches the usergroup domain masters in the entity cache if it is enabled.
    *
    * @param usergroupDomainMasters the usergroup domain masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.UsergroupDomainMaster> usergroupDomainMasters);

    /**
    * Creates a new usergroup domain master with the primary key. Does not add the usergroup domain master to the database.
    *
    * @param usergroupDomainSid the primary key for the new usergroup domain master
    * @return the new usergroup domain master
    */
    public com.stpl.app.model.UsergroupDomainMaster create(
        int usergroupDomainSid);

    /**
    * Removes the usergroup domain master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param usergroupDomainSid the primary key of the usergroup domain master
    * @return the usergroup domain master that was removed
    * @throws com.stpl.app.NoSuchUsergroupDomainMasterException if a usergroup domain master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.UsergroupDomainMaster remove(
        int usergroupDomainSid)
        throws com.stpl.app.NoSuchUsergroupDomainMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.UsergroupDomainMaster updateImpl(
        com.stpl.app.model.UsergroupDomainMaster usergroupDomainMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the usergroup domain master with the primary key or throws a {@link com.stpl.app.NoSuchUsergroupDomainMasterException} if it could not be found.
    *
    * @param usergroupDomainSid the primary key of the usergroup domain master
    * @return the usergroup domain master
    * @throws com.stpl.app.NoSuchUsergroupDomainMasterException if a usergroup domain master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.UsergroupDomainMaster findByPrimaryKey(
        int usergroupDomainSid)
        throws com.stpl.app.NoSuchUsergroupDomainMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the usergroup domain master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param usergroupDomainSid the primary key of the usergroup domain master
    * @return the usergroup domain master, or <code>null</code> if a usergroup domain master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.UsergroupDomainMaster fetchByPrimaryKey(
        int usergroupDomainSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the usergroup domain masters.
    *
    * @return the usergroup domain masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.UsergroupDomainMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the usergroup domain masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.UsergroupDomainMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of usergroup domain masters
    * @param end the upper bound of the range of usergroup domain masters (not inclusive)
    * @return the range of usergroup domain masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.UsergroupDomainMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the usergroup domain masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.UsergroupDomainMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of usergroup domain masters
    * @param end the upper bound of the range of usergroup domain masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of usergroup domain masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.UsergroupDomainMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the usergroup domain masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of usergroup domain masters.
    *
    * @return the number of usergroup domain masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
