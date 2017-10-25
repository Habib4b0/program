package com.stpl.app.service.persistence;

import com.stpl.app.model.ChDiscountProjMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ch discount proj master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ChDiscountProjMasterPersistenceImpl
 * @see ChDiscountProjMasterUtil
 * @generated
 */
public interface ChDiscountProjMasterPersistence extends BasePersistence<ChDiscountProjMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ChDiscountProjMasterUtil} to access the ch discount proj master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the ch discount proj master in the entity cache if it is enabled.
    *
    * @param chDiscountProjMaster the ch discount proj master
    */
    public void cacheResult(
        com.stpl.app.model.ChDiscountProjMaster chDiscountProjMaster);

    /**
    * Caches the ch discount proj masters in the entity cache if it is enabled.
    *
    * @param chDiscountProjMasters the ch discount proj masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.ChDiscountProjMaster> chDiscountProjMasters);

    /**
    * Creates a new ch discount proj master with the primary key. Does not add the ch discount proj master to the database.
    *
    * @param chDiscountProjMasterPK the primary key for the new ch discount proj master
    * @return the new ch discount proj master
    */
    public com.stpl.app.model.ChDiscountProjMaster create(
        ChDiscountProjMasterPK chDiscountProjMasterPK);

    /**
    * Removes the ch discount proj master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param chDiscountProjMasterPK the primary key of the ch discount proj master
    * @return the ch discount proj master that was removed
    * @throws com.stpl.app.NoSuchChDiscountProjMasterException if a ch discount proj master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ChDiscountProjMaster remove(
        ChDiscountProjMasterPK chDiscountProjMasterPK)
        throws com.stpl.app.NoSuchChDiscountProjMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.ChDiscountProjMaster updateImpl(
        com.stpl.app.model.ChDiscountProjMaster chDiscountProjMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ch discount proj master with the primary key or throws a {@link com.stpl.app.NoSuchChDiscountProjMasterException} if it could not be found.
    *
    * @param chDiscountProjMasterPK the primary key of the ch discount proj master
    * @return the ch discount proj master
    * @throws com.stpl.app.NoSuchChDiscountProjMasterException if a ch discount proj master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ChDiscountProjMaster findByPrimaryKey(
        ChDiscountProjMasterPK chDiscountProjMasterPK)
        throws com.stpl.app.NoSuchChDiscountProjMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ch discount proj master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param chDiscountProjMasterPK the primary key of the ch discount proj master
    * @return the ch discount proj master, or <code>null</code> if a ch discount proj master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ChDiscountProjMaster fetchByPrimaryKey(
        ChDiscountProjMasterPK chDiscountProjMasterPK)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ch discount proj masters.
    *
    * @return the ch discount proj masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ChDiscountProjMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ch discount proj masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ch discount proj masters
    * @param end the upper bound of the range of ch discount proj masters (not inclusive)
    * @return the range of ch discount proj masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ChDiscountProjMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ch discount proj masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ch discount proj masters
    * @param end the upper bound of the range of ch discount proj masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ch discount proj masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ChDiscountProjMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ch discount proj masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ch discount proj masters.
    *
    * @return the number of ch discount proj masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
