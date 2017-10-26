package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.VwIvldReturnReserve;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the vw ivld return reserve service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwIvldReturnReservePersistenceImpl
 * @see VwIvldReturnReserveUtil
 * @generated
 */
public interface VwIvldReturnReservePersistence extends BasePersistence<VwIvldReturnReserve> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link VwIvldReturnReserveUtil} to access the vw ivld return reserve persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the vw ivld return reserve in the entity cache if it is enabled.
    *
    * @param vwIvldReturnReserve the vw ivld return reserve
    */
    public void cacheResult(
        com.stpl.app.parttwo.model.VwIvldReturnReserve vwIvldReturnReserve);

    /**
    * Caches the vw ivld return reserves in the entity cache if it is enabled.
    *
    * @param vwIvldReturnReserves the vw ivld return reserves
    */
    public void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.VwIvldReturnReserve> vwIvldReturnReserves);

    /**
    * Creates a new vw ivld return reserve with the primary key. Does not add the vw ivld return reserve to the database.
    *
    * @param ivldReturnReserveSid the primary key for the new vw ivld return reserve
    * @return the new vw ivld return reserve
    */
    public com.stpl.app.parttwo.model.VwIvldReturnReserve create(
        int ivldReturnReserveSid);

    /**
    * Removes the vw ivld return reserve with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldReturnReserveSid the primary key of the vw ivld return reserve
    * @return the vw ivld return reserve that was removed
    * @throws com.stpl.app.parttwo.NoSuchVwIvldReturnReserveException if a vw ivld return reserve with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.VwIvldReturnReserve remove(
        int ivldReturnReserveSid)
        throws com.stpl.app.parttwo.NoSuchVwIvldReturnReserveException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.parttwo.model.VwIvldReturnReserve updateImpl(
        com.stpl.app.parttwo.model.VwIvldReturnReserve vwIvldReturnReserve)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the vw ivld return reserve with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchVwIvldReturnReserveException} if it could not be found.
    *
    * @param ivldReturnReserveSid the primary key of the vw ivld return reserve
    * @return the vw ivld return reserve
    * @throws com.stpl.app.parttwo.NoSuchVwIvldReturnReserveException if a vw ivld return reserve with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.VwIvldReturnReserve findByPrimaryKey(
        int ivldReturnReserveSid)
        throws com.stpl.app.parttwo.NoSuchVwIvldReturnReserveException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the vw ivld return reserve with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldReturnReserveSid the primary key of the vw ivld return reserve
    * @return the vw ivld return reserve, or <code>null</code> if a vw ivld return reserve with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.VwIvldReturnReserve fetchByPrimaryKey(
        int ivldReturnReserveSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the vw ivld return reserves.
    *
    * @return the vw ivld return reserves
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.VwIvldReturnReserve> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the vw ivld return reserves.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwIvldReturnReserveModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw ivld return reserves
    * @param end the upper bound of the range of vw ivld return reserves (not inclusive)
    * @return the range of vw ivld return reserves
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.VwIvldReturnReserve> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the vw ivld return reserves.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwIvldReturnReserveModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw ivld return reserves
    * @param end the upper bound of the range of vw ivld return reserves (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of vw ivld return reserves
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.VwIvldReturnReserve> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the vw ivld return reserves from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of vw ivld return reserves.
    *
    * @return the number of vw ivld return reserves
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
