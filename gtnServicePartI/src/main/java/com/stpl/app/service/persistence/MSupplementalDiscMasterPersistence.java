package com.stpl.app.service.persistence;

import com.stpl.app.model.MSupplementalDiscMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the m supplemental disc master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MSupplementalDiscMasterPersistenceImpl
 * @see MSupplementalDiscMasterUtil
 * @generated
 */
public interface MSupplementalDiscMasterPersistence extends BasePersistence<MSupplementalDiscMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link MSupplementalDiscMasterUtil} to access the m supplemental disc master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the m supplemental disc master in the entity cache if it is enabled.
    *
    * @param mSupplementalDiscMaster the m supplemental disc master
    */
    public void cacheResult(
        com.stpl.app.model.MSupplementalDiscMaster mSupplementalDiscMaster);

    /**
    * Caches the m supplemental disc masters in the entity cache if it is enabled.
    *
    * @param mSupplementalDiscMasters the m supplemental disc masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.MSupplementalDiscMaster> mSupplementalDiscMasters);

    /**
    * Creates a new m supplemental disc master with the primary key. Does not add the m supplemental disc master to the database.
    *
    * @param projectionDetailsSid the primary key for the new m supplemental disc master
    * @return the new m supplemental disc master
    */
    public com.stpl.app.model.MSupplementalDiscMaster create(
        int projectionDetailsSid);

    /**
    * Removes the m supplemental disc master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param projectionDetailsSid the primary key of the m supplemental disc master
    * @return the m supplemental disc master that was removed
    * @throws com.stpl.app.NoSuchMSupplementalDiscMasterException if a m supplemental disc master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.MSupplementalDiscMaster remove(
        int projectionDetailsSid)
        throws com.stpl.app.NoSuchMSupplementalDiscMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.MSupplementalDiscMaster updateImpl(
        com.stpl.app.model.MSupplementalDiscMaster mSupplementalDiscMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the m supplemental disc master with the primary key or throws a {@link com.stpl.app.NoSuchMSupplementalDiscMasterException} if it could not be found.
    *
    * @param projectionDetailsSid the primary key of the m supplemental disc master
    * @return the m supplemental disc master
    * @throws com.stpl.app.NoSuchMSupplementalDiscMasterException if a m supplemental disc master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.MSupplementalDiscMaster findByPrimaryKey(
        int projectionDetailsSid)
        throws com.stpl.app.NoSuchMSupplementalDiscMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the m supplemental disc master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param projectionDetailsSid the primary key of the m supplemental disc master
    * @return the m supplemental disc master, or <code>null</code> if a m supplemental disc master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.MSupplementalDiscMaster fetchByPrimaryKey(
        int projectionDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the m supplemental disc masters.
    *
    * @return the m supplemental disc masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.MSupplementalDiscMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the m supplemental disc masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MSupplementalDiscMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of m supplemental disc masters
    * @param end the upper bound of the range of m supplemental disc masters (not inclusive)
    * @return the range of m supplemental disc masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.MSupplementalDiscMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the m supplemental disc masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MSupplementalDiscMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of m supplemental disc masters
    * @param end the upper bound of the range of m supplemental disc masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of m supplemental disc masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.MSupplementalDiscMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the m supplemental disc masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of m supplemental disc masters.
    *
    * @return the number of m supplemental disc masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
