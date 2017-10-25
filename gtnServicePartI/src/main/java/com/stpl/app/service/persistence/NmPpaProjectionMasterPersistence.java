package com.stpl.app.service.persistence;

import com.stpl.app.model.NmPpaProjectionMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the nm ppa projection master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NmPpaProjectionMasterPersistenceImpl
 * @see NmPpaProjectionMasterUtil
 * @generated
 */
public interface NmPpaProjectionMasterPersistence extends BasePersistence<NmPpaProjectionMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link NmPpaProjectionMasterUtil} to access the nm ppa projection master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the nm ppa projection master in the entity cache if it is enabled.
    *
    * @param nmPpaProjectionMaster the nm ppa projection master
    */
    public void cacheResult(
        com.stpl.app.model.NmPpaProjectionMaster nmPpaProjectionMaster);

    /**
    * Caches the nm ppa projection masters in the entity cache if it is enabled.
    *
    * @param nmPpaProjectionMasters the nm ppa projection masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.NmPpaProjectionMaster> nmPpaProjectionMasters);

    /**
    * Creates a new nm ppa projection master with the primary key. Does not add the nm ppa projection master to the database.
    *
    * @param projectionDetailsSid the primary key for the new nm ppa projection master
    * @return the new nm ppa projection master
    */
    public com.stpl.app.model.NmPpaProjectionMaster create(
        int projectionDetailsSid);

    /**
    * Removes the nm ppa projection master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param projectionDetailsSid the primary key of the nm ppa projection master
    * @return the nm ppa projection master that was removed
    * @throws com.stpl.app.NoSuchNmPpaProjectionMasterException if a nm ppa projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.NmPpaProjectionMaster remove(
        int projectionDetailsSid)
        throws com.stpl.app.NoSuchNmPpaProjectionMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.NmPpaProjectionMaster updateImpl(
        com.stpl.app.model.NmPpaProjectionMaster nmPpaProjectionMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the nm ppa projection master with the primary key or throws a {@link com.stpl.app.NoSuchNmPpaProjectionMasterException} if it could not be found.
    *
    * @param projectionDetailsSid the primary key of the nm ppa projection master
    * @return the nm ppa projection master
    * @throws com.stpl.app.NoSuchNmPpaProjectionMasterException if a nm ppa projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.NmPpaProjectionMaster findByPrimaryKey(
        int projectionDetailsSid)
        throws com.stpl.app.NoSuchNmPpaProjectionMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the nm ppa projection master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param projectionDetailsSid the primary key of the nm ppa projection master
    * @return the nm ppa projection master, or <code>null</code> if a nm ppa projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.NmPpaProjectionMaster fetchByPrimaryKey(
        int projectionDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the nm ppa projection masters.
    *
    * @return the nm ppa projection masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.NmPpaProjectionMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the nm ppa projection masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmPpaProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of nm ppa projection masters
    * @param end the upper bound of the range of nm ppa projection masters (not inclusive)
    * @return the range of nm ppa projection masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.NmPpaProjectionMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the nm ppa projection masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmPpaProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of nm ppa projection masters
    * @param end the upper bound of the range of nm ppa projection masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of nm ppa projection masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.NmPpaProjectionMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the nm ppa projection masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of nm ppa projection masters.
    *
    * @return the number of nm ppa projection masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
