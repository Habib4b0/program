package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.CffCustomViewMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the cff custom view master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CffCustomViewMasterPersistenceImpl
 * @see CffCustomViewMasterUtil
 * @generated
 */
public interface CffCustomViewMasterPersistence extends BasePersistence<CffCustomViewMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link CffCustomViewMasterUtil} to access the cff custom view master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the cff custom view master in the entity cache if it is enabled.
    *
    * @param cffCustomViewMaster the cff custom view master
    */
    public void cacheResult(
        com.stpl.app.parttwo.model.CffCustomViewMaster cffCustomViewMaster);

    /**
    * Caches the cff custom view masters in the entity cache if it is enabled.
    *
    * @param cffCustomViewMasters the cff custom view masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.CffCustomViewMaster> cffCustomViewMasters);

    /**
    * Creates a new cff custom view master with the primary key. Does not add the cff custom view master to the database.
    *
    * @param cffCustomViewMasterSid the primary key for the new cff custom view master
    * @return the new cff custom view master
    */
    public com.stpl.app.parttwo.model.CffCustomViewMaster create(
        int cffCustomViewMasterSid);

    /**
    * Removes the cff custom view master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param cffCustomViewMasterSid the primary key of the cff custom view master
    * @return the cff custom view master that was removed
    * @throws com.stpl.app.parttwo.NoSuchCffCustomViewMasterException if a cff custom view master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.CffCustomViewMaster remove(
        int cffCustomViewMasterSid)
        throws com.stpl.app.parttwo.NoSuchCffCustomViewMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.parttwo.model.CffCustomViewMaster updateImpl(
        com.stpl.app.parttwo.model.CffCustomViewMaster cffCustomViewMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the cff custom view master with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchCffCustomViewMasterException} if it could not be found.
    *
    * @param cffCustomViewMasterSid the primary key of the cff custom view master
    * @return the cff custom view master
    * @throws com.stpl.app.parttwo.NoSuchCffCustomViewMasterException if a cff custom view master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.CffCustomViewMaster findByPrimaryKey(
        int cffCustomViewMasterSid)
        throws com.stpl.app.parttwo.NoSuchCffCustomViewMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the cff custom view master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param cffCustomViewMasterSid the primary key of the cff custom view master
    * @return the cff custom view master, or <code>null</code> if a cff custom view master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.CffCustomViewMaster fetchByPrimaryKey(
        int cffCustomViewMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the cff custom view masters.
    *
    * @return the cff custom view masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.CffCustomViewMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the cff custom view masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffCustomViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cff custom view masters
    * @param end the upper bound of the range of cff custom view masters (not inclusive)
    * @return the range of cff custom view masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.CffCustomViewMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the cff custom view masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffCustomViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cff custom view masters
    * @param end the upper bound of the range of cff custom view masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of cff custom view masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.CffCustomViewMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the cff custom view masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of cff custom view masters.
    *
    * @return the number of cff custom view masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
