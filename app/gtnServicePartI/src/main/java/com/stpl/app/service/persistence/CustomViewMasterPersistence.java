package com.stpl.app.service.persistence;

import com.stpl.app.model.CustomViewMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the custom view master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CustomViewMasterPersistenceImpl
 * @see CustomViewMasterUtil
 * @generated
 */
public interface CustomViewMasterPersistence extends BasePersistence<CustomViewMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link CustomViewMasterUtil} to access the custom view master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the custom view master in the entity cache if it is enabled.
    *
    * @param customViewMaster the custom view master
    */
    public void cacheResult(
        com.stpl.app.model.CustomViewMaster customViewMaster);

    /**
    * Caches the custom view masters in the entity cache if it is enabled.
    *
    * @param customViewMasters the custom view masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.CustomViewMaster> customViewMasters);

    /**
    * Creates a new custom view master with the primary key. Does not add the custom view master to the database.
    *
    * @param customViewMasterSid the primary key for the new custom view master
    * @return the new custom view master
    */
    public com.stpl.app.model.CustomViewMaster create(int customViewMasterSid);

    /**
    * Removes the custom view master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param customViewMasterSid the primary key of the custom view master
    * @return the custom view master that was removed
    * @throws com.stpl.app.NoSuchCustomViewMasterException if a custom view master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CustomViewMaster remove(int customViewMasterSid)
        throws com.stpl.app.NoSuchCustomViewMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.CustomViewMaster updateImpl(
        com.stpl.app.model.CustomViewMaster customViewMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the custom view master with the primary key or throws a {@link com.stpl.app.NoSuchCustomViewMasterException} if it could not be found.
    *
    * @param customViewMasterSid the primary key of the custom view master
    * @return the custom view master
    * @throws com.stpl.app.NoSuchCustomViewMasterException if a custom view master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CustomViewMaster findByPrimaryKey(
        int customViewMasterSid)
        throws com.stpl.app.NoSuchCustomViewMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the custom view master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param customViewMasterSid the primary key of the custom view master
    * @return the custom view master, or <code>null</code> if a custom view master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CustomViewMaster fetchByPrimaryKey(
        int customViewMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the custom view masters.
    *
    * @return the custom view masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CustomViewMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the custom view masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CustomViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of custom view masters
    * @param end the upper bound of the range of custom view masters (not inclusive)
    * @return the range of custom view masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CustomViewMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the custom view masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CustomViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of custom view masters
    * @param end the upper bound of the range of custom view masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of custom view masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CustomViewMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the custom view masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of custom view masters.
    *
    * @return the number of custom view masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
