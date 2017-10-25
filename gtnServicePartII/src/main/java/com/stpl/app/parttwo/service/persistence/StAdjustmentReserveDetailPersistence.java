package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.StAdjustmentReserveDetail;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the st adjustment reserve detail service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StAdjustmentReserveDetailPersistenceImpl
 * @see StAdjustmentReserveDetailUtil
 * @generated
 */
public interface StAdjustmentReserveDetailPersistence extends BasePersistence<StAdjustmentReserveDetail> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link StAdjustmentReserveDetailUtil} to access the st adjustment reserve detail persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the st adjustment reserve detail in the entity cache if it is enabled.
    *
    * @param stAdjustmentReserveDetail the st adjustment reserve detail
    */
    public void cacheResult(
        com.stpl.app.parttwo.model.StAdjustmentReserveDetail stAdjustmentReserveDetail);

    /**
    * Caches the st adjustment reserve details in the entity cache if it is enabled.
    *
    * @param stAdjustmentReserveDetails the st adjustment reserve details
    */
    public void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.StAdjustmentReserveDetail> stAdjustmentReserveDetails);

    /**
    * Creates a new st adjustment reserve detail with the primary key. Does not add the st adjustment reserve detail to the database.
    *
    * @param stAdjustmentReserveDetailPK the primary key for the new st adjustment reserve detail
    * @return the new st adjustment reserve detail
    */
    public com.stpl.app.parttwo.model.StAdjustmentReserveDetail create(
        StAdjustmentReserveDetailPK stAdjustmentReserveDetailPK);

    /**
    * Removes the st adjustment reserve detail with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stAdjustmentReserveDetailPK the primary key of the st adjustment reserve detail
    * @return the st adjustment reserve detail that was removed
    * @throws com.stpl.app.parttwo.NoSuchStAdjustmentReserveDetailException if a st adjustment reserve detail with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.StAdjustmentReserveDetail remove(
        StAdjustmentReserveDetailPK stAdjustmentReserveDetailPK)
        throws com.stpl.app.parttwo.NoSuchStAdjustmentReserveDetailException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.parttwo.model.StAdjustmentReserveDetail updateImpl(
        com.stpl.app.parttwo.model.StAdjustmentReserveDetail stAdjustmentReserveDetail)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the st adjustment reserve detail with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchStAdjustmentReserveDetailException} if it could not be found.
    *
    * @param stAdjustmentReserveDetailPK the primary key of the st adjustment reserve detail
    * @return the st adjustment reserve detail
    * @throws com.stpl.app.parttwo.NoSuchStAdjustmentReserveDetailException if a st adjustment reserve detail with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.StAdjustmentReserveDetail findByPrimaryKey(
        StAdjustmentReserveDetailPK stAdjustmentReserveDetailPK)
        throws com.stpl.app.parttwo.NoSuchStAdjustmentReserveDetailException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the st adjustment reserve detail with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param stAdjustmentReserveDetailPK the primary key of the st adjustment reserve detail
    * @return the st adjustment reserve detail, or <code>null</code> if a st adjustment reserve detail with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.StAdjustmentReserveDetail fetchByPrimaryKey(
        StAdjustmentReserveDetailPK stAdjustmentReserveDetailPK)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the st adjustment reserve details.
    *
    * @return the st adjustment reserve details
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.StAdjustmentReserveDetail> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the st adjustment reserve details.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StAdjustmentReserveDetailModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st adjustment reserve details
    * @param end the upper bound of the range of st adjustment reserve details (not inclusive)
    * @return the range of st adjustment reserve details
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.StAdjustmentReserveDetail> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the st adjustment reserve details.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StAdjustmentReserveDetailModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st adjustment reserve details
    * @param end the upper bound of the range of st adjustment reserve details (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of st adjustment reserve details
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.StAdjustmentReserveDetail> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the st adjustment reserve details from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of st adjustment reserve details.
    *
    * @return the number of st adjustment reserve details
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
