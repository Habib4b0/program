package com.stpl.app.service.persistence;

import com.stpl.app.model.NetSalesFormulaMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the net sales formula master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NetSalesFormulaMasterPersistenceImpl
 * @see NetSalesFormulaMasterUtil
 * @generated
 */
public interface NetSalesFormulaMasterPersistence extends BasePersistence<NetSalesFormulaMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link NetSalesFormulaMasterUtil} to access the net sales formula master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the net sales formula master in the entity cache if it is enabled.
    *
    * @param netSalesFormulaMaster the net sales formula master
    */
    public void cacheResult(
        com.stpl.app.model.NetSalesFormulaMaster netSalesFormulaMaster);

    /**
    * Caches the net sales formula masters in the entity cache if it is enabled.
    *
    * @param netSalesFormulaMasters the net sales formula masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.NetSalesFormulaMaster> netSalesFormulaMasters);

    /**
    * Creates a new net sales formula master with the primary key. Does not add the net sales formula master to the database.
    *
    * @param netSalesFormulaMasterSid the primary key for the new net sales formula master
    * @return the new net sales formula master
    */
    public com.stpl.app.model.NetSalesFormulaMaster create(
        int netSalesFormulaMasterSid);

    /**
    * Removes the net sales formula master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param netSalesFormulaMasterSid the primary key of the net sales formula master
    * @return the net sales formula master that was removed
    * @throws com.stpl.app.NoSuchNetSalesFormulaMasterException if a net sales formula master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.NetSalesFormulaMaster remove(
        int netSalesFormulaMasterSid)
        throws com.stpl.app.NoSuchNetSalesFormulaMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.NetSalesFormulaMaster updateImpl(
        com.stpl.app.model.NetSalesFormulaMaster netSalesFormulaMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the net sales formula master with the primary key or throws a {@link com.stpl.app.NoSuchNetSalesFormulaMasterException} if it could not be found.
    *
    * @param netSalesFormulaMasterSid the primary key of the net sales formula master
    * @return the net sales formula master
    * @throws com.stpl.app.NoSuchNetSalesFormulaMasterException if a net sales formula master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.NetSalesFormulaMaster findByPrimaryKey(
        int netSalesFormulaMasterSid)
        throws com.stpl.app.NoSuchNetSalesFormulaMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the net sales formula master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param netSalesFormulaMasterSid the primary key of the net sales formula master
    * @return the net sales formula master, or <code>null</code> if a net sales formula master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.NetSalesFormulaMaster fetchByPrimaryKey(
        int netSalesFormulaMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the net sales formula masters.
    *
    * @return the net sales formula masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.NetSalesFormulaMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the net sales formula masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NetSalesFormulaMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of net sales formula masters
    * @param end the upper bound of the range of net sales formula masters (not inclusive)
    * @return the range of net sales formula masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.NetSalesFormulaMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the net sales formula masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NetSalesFormulaMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of net sales formula masters
    * @param end the upper bound of the range of net sales formula masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of net sales formula masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.NetSalesFormulaMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the net sales formula masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of net sales formula masters.
    *
    * @return the number of net sales formula masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
