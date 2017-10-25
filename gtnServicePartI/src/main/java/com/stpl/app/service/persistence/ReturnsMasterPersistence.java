package com.stpl.app.service.persistence;

import com.stpl.app.model.ReturnsMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the returns master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ReturnsMasterPersistenceImpl
 * @see ReturnsMasterUtil
 * @generated
 */
public interface ReturnsMasterPersistence extends BasePersistence<ReturnsMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ReturnsMasterUtil} to access the returns master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the returns master in the entity cache if it is enabled.
    *
    * @param returnsMaster the returns master
    */
    public void cacheResult(com.stpl.app.model.ReturnsMaster returnsMaster);

    /**
    * Caches the returns masters in the entity cache if it is enabled.
    *
    * @param returnsMasters the returns masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.ReturnsMaster> returnsMasters);

    /**
    * Creates a new returns master with the primary key. Does not add the returns master to the database.
    *
    * @param returnsMasterSid the primary key for the new returns master
    * @return the new returns master
    */
    public com.stpl.app.model.ReturnsMaster create(int returnsMasterSid);

    /**
    * Removes the returns master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param returnsMasterSid the primary key of the returns master
    * @return the returns master that was removed
    * @throws com.stpl.app.NoSuchReturnsMasterException if a returns master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ReturnsMaster remove(int returnsMasterSid)
        throws com.stpl.app.NoSuchReturnsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.ReturnsMaster updateImpl(
        com.stpl.app.model.ReturnsMaster returnsMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the returns master with the primary key or throws a {@link com.stpl.app.NoSuchReturnsMasterException} if it could not be found.
    *
    * @param returnsMasterSid the primary key of the returns master
    * @return the returns master
    * @throws com.stpl.app.NoSuchReturnsMasterException if a returns master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ReturnsMaster findByPrimaryKey(
        int returnsMasterSid)
        throws com.stpl.app.NoSuchReturnsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the returns master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param returnsMasterSid the primary key of the returns master
    * @return the returns master, or <code>null</code> if a returns master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ReturnsMaster fetchByPrimaryKey(
        int returnsMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the returns masters.
    *
    * @return the returns masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ReturnsMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the returns masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ReturnsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of returns masters
    * @param end the upper bound of the range of returns masters (not inclusive)
    * @return the range of returns masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ReturnsMaster> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the returns masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ReturnsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of returns masters
    * @param end the upper bound of the range of returns masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of returns masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ReturnsMaster> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the returns masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of returns masters.
    *
    * @return the number of returns masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
