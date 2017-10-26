package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.AccClosureMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the acc closure master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AccClosureMasterPersistenceImpl
 * @see AccClosureMasterUtil
 * @generated
 */
public interface AccClosureMasterPersistence extends BasePersistence<AccClosureMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link AccClosureMasterUtil} to access the acc closure master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the acc closure master in the entity cache if it is enabled.
    *
    * @param accClosureMaster the acc closure master
    */
    public void cacheResult(
        com.stpl.app.parttwo.model.AccClosureMaster accClosureMaster);

    /**
    * Caches the acc closure masters in the entity cache if it is enabled.
    *
    * @param accClosureMasters the acc closure masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.AccClosureMaster> accClosureMasters);

    /**
    * Creates a new acc closure master with the primary key. Does not add the acc closure master to the database.
    *
    * @param accClosureMasterSid the primary key for the new acc closure master
    * @return the new acc closure master
    */
    public com.stpl.app.parttwo.model.AccClosureMaster create(
        int accClosureMasterSid);

    /**
    * Removes the acc closure master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param accClosureMasterSid the primary key of the acc closure master
    * @return the acc closure master that was removed
    * @throws com.stpl.app.parttwo.NoSuchAccClosureMasterException if a acc closure master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.AccClosureMaster remove(
        int accClosureMasterSid)
        throws com.stpl.app.parttwo.NoSuchAccClosureMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.parttwo.model.AccClosureMaster updateImpl(
        com.stpl.app.parttwo.model.AccClosureMaster accClosureMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the acc closure master with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchAccClosureMasterException} if it could not be found.
    *
    * @param accClosureMasterSid the primary key of the acc closure master
    * @return the acc closure master
    * @throws com.stpl.app.parttwo.NoSuchAccClosureMasterException if a acc closure master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.AccClosureMaster findByPrimaryKey(
        int accClosureMasterSid)
        throws com.stpl.app.parttwo.NoSuchAccClosureMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the acc closure master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param accClosureMasterSid the primary key of the acc closure master
    * @return the acc closure master, or <code>null</code> if a acc closure master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.AccClosureMaster fetchByPrimaryKey(
        int accClosureMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the acc closure masters.
    *
    * @return the acc closure masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.AccClosureMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the acc closure masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AccClosureMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of acc closure masters
    * @param end the upper bound of the range of acc closure masters (not inclusive)
    * @return the range of acc closure masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.AccClosureMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the acc closure masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AccClosureMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of acc closure masters
    * @param end the upper bound of the range of acc closure masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of acc closure masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.AccClosureMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the acc closure masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of acc closure masters.
    *
    * @return the number of acc closure masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
