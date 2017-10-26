package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.CffViewMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the cff view master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CffViewMasterPersistenceImpl
 * @see CffViewMasterUtil
 * @generated
 */
public interface CffViewMasterPersistence extends BasePersistence<CffViewMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link CffViewMasterUtil} to access the cff view master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the cff view master in the entity cache if it is enabled.
    *
    * @param cffViewMaster the cff view master
    */
    public void cacheResult(
        com.stpl.app.parttwo.model.CffViewMaster cffViewMaster);

    /**
    * Caches the cff view masters in the entity cache if it is enabled.
    *
    * @param cffViewMasters the cff view masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.CffViewMaster> cffViewMasters);

    /**
    * Creates a new cff view master with the primary key. Does not add the cff view master to the database.
    *
    * @param cffViewMasterSid the primary key for the new cff view master
    * @return the new cff view master
    */
    public com.stpl.app.parttwo.model.CffViewMaster create(int cffViewMasterSid);

    /**
    * Removes the cff view master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param cffViewMasterSid the primary key of the cff view master
    * @return the cff view master that was removed
    * @throws com.stpl.app.parttwo.NoSuchCffViewMasterException if a cff view master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.CffViewMaster remove(int cffViewMasterSid)
        throws com.stpl.app.parttwo.NoSuchCffViewMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.parttwo.model.CffViewMaster updateImpl(
        com.stpl.app.parttwo.model.CffViewMaster cffViewMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the cff view master with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchCffViewMasterException} if it could not be found.
    *
    * @param cffViewMasterSid the primary key of the cff view master
    * @return the cff view master
    * @throws com.stpl.app.parttwo.NoSuchCffViewMasterException if a cff view master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.CffViewMaster findByPrimaryKey(
        int cffViewMasterSid)
        throws com.stpl.app.parttwo.NoSuchCffViewMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the cff view master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param cffViewMasterSid the primary key of the cff view master
    * @return the cff view master, or <code>null</code> if a cff view master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.CffViewMaster fetchByPrimaryKey(
        int cffViewMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the cff view masters.
    *
    * @return the cff view masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.CffViewMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the cff view masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cff view masters
    * @param end the upper bound of the range of cff view masters (not inclusive)
    * @return the range of cff view masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.CffViewMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the cff view masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cff view masters
    * @param end the upper bound of the range of cff view masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of cff view masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.CffViewMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the cff view masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of cff view masters.
    *
    * @return the number of cff view masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
