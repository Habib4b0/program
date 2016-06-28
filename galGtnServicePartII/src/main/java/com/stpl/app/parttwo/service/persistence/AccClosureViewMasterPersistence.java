package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.AccClosureViewMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the acc closure view master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AccClosureViewMasterPersistenceImpl
 * @see AccClosureViewMasterUtil
 * @generated
 */
public interface AccClosureViewMasterPersistence extends BasePersistence<AccClosureViewMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link AccClosureViewMasterUtil} to access the acc closure view master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the acc closure view master in the entity cache if it is enabled.
    *
    * @param accClosureViewMaster the acc closure view master
    */
    public void cacheResult(
        com.stpl.app.parttwo.model.AccClosureViewMaster accClosureViewMaster);

    /**
    * Caches the acc closure view masters in the entity cache if it is enabled.
    *
    * @param accClosureViewMasters the acc closure view masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.AccClosureViewMaster> accClosureViewMasters);

    /**
    * Creates a new acc closure view master with the primary key. Does not add the acc closure view master to the database.
    *
    * @param accClosureViewMasterSid the primary key for the new acc closure view master
    * @return the new acc closure view master
    */
    public com.stpl.app.parttwo.model.AccClosureViewMaster create(
        int accClosureViewMasterSid);

    /**
    * Removes the acc closure view master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param accClosureViewMasterSid the primary key of the acc closure view master
    * @return the acc closure view master that was removed
    * @throws com.stpl.app.parttwo.NoSuchAccClosureViewMasterException if a acc closure view master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.AccClosureViewMaster remove(
        int accClosureViewMasterSid)
        throws com.stpl.app.parttwo.NoSuchAccClosureViewMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.parttwo.model.AccClosureViewMaster updateImpl(
        com.stpl.app.parttwo.model.AccClosureViewMaster accClosureViewMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the acc closure view master with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchAccClosureViewMasterException} if it could not be found.
    *
    * @param accClosureViewMasterSid the primary key of the acc closure view master
    * @return the acc closure view master
    * @throws com.stpl.app.parttwo.NoSuchAccClosureViewMasterException if a acc closure view master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.AccClosureViewMaster findByPrimaryKey(
        int accClosureViewMasterSid)
        throws com.stpl.app.parttwo.NoSuchAccClosureViewMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the acc closure view master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param accClosureViewMasterSid the primary key of the acc closure view master
    * @return the acc closure view master, or <code>null</code> if a acc closure view master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.AccClosureViewMaster fetchByPrimaryKey(
        int accClosureViewMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the acc closure view masters.
    *
    * @return the acc closure view masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.AccClosureViewMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the acc closure view masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AccClosureViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of acc closure view masters
    * @param end the upper bound of the range of acc closure view masters (not inclusive)
    * @return the range of acc closure view masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.AccClosureViewMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the acc closure view masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AccClosureViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of acc closure view masters
    * @param end the upper bound of the range of acc closure view masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of acc closure view masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.AccClosureViewMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the acc closure view masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of acc closure view masters.
    *
    * @return the number of acc closure view masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
