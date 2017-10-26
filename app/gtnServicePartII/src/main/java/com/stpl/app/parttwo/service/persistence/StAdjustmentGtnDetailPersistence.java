package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.StAdjustmentGtnDetail;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the st adjustment gtn detail service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StAdjustmentGtnDetailPersistenceImpl
 * @see StAdjustmentGtnDetailUtil
 * @generated
 */
public interface StAdjustmentGtnDetailPersistence extends BasePersistence<StAdjustmentGtnDetail> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link StAdjustmentGtnDetailUtil} to access the st adjustment gtn detail persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the st adjustment gtn detail in the entity cache if it is enabled.
    *
    * @param stAdjustmentGtnDetail the st adjustment gtn detail
    */
    public void cacheResult(
        com.stpl.app.parttwo.model.StAdjustmentGtnDetail stAdjustmentGtnDetail);

    /**
    * Caches the st adjustment gtn details in the entity cache if it is enabled.
    *
    * @param stAdjustmentGtnDetails the st adjustment gtn details
    */
    public void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.StAdjustmentGtnDetail> stAdjustmentGtnDetails);

    /**
    * Creates a new st adjustment gtn detail with the primary key. Does not add the st adjustment gtn detail to the database.
    *
    * @param stAdjustmentGtnDetailPK the primary key for the new st adjustment gtn detail
    * @return the new st adjustment gtn detail
    */
    public com.stpl.app.parttwo.model.StAdjustmentGtnDetail create(
        StAdjustmentGtnDetailPK stAdjustmentGtnDetailPK);

    /**
    * Removes the st adjustment gtn detail with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stAdjustmentGtnDetailPK the primary key of the st adjustment gtn detail
    * @return the st adjustment gtn detail that was removed
    * @throws com.stpl.app.parttwo.NoSuchStAdjustmentGtnDetailException if a st adjustment gtn detail with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.StAdjustmentGtnDetail remove(
        StAdjustmentGtnDetailPK stAdjustmentGtnDetailPK)
        throws com.stpl.app.parttwo.NoSuchStAdjustmentGtnDetailException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.parttwo.model.StAdjustmentGtnDetail updateImpl(
        com.stpl.app.parttwo.model.StAdjustmentGtnDetail stAdjustmentGtnDetail)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the st adjustment gtn detail with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchStAdjustmentGtnDetailException} if it could not be found.
    *
    * @param stAdjustmentGtnDetailPK the primary key of the st adjustment gtn detail
    * @return the st adjustment gtn detail
    * @throws com.stpl.app.parttwo.NoSuchStAdjustmentGtnDetailException if a st adjustment gtn detail with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.StAdjustmentGtnDetail findByPrimaryKey(
        StAdjustmentGtnDetailPK stAdjustmentGtnDetailPK)
        throws com.stpl.app.parttwo.NoSuchStAdjustmentGtnDetailException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the st adjustment gtn detail with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param stAdjustmentGtnDetailPK the primary key of the st adjustment gtn detail
    * @return the st adjustment gtn detail, or <code>null</code> if a st adjustment gtn detail with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.StAdjustmentGtnDetail fetchByPrimaryKey(
        StAdjustmentGtnDetailPK stAdjustmentGtnDetailPK)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the st adjustment gtn details.
    *
    * @return the st adjustment gtn details
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.StAdjustmentGtnDetail> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the st adjustment gtn details.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StAdjustmentGtnDetailModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st adjustment gtn details
    * @param end the upper bound of the range of st adjustment gtn details (not inclusive)
    * @return the range of st adjustment gtn details
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.StAdjustmentGtnDetail> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the st adjustment gtn details.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StAdjustmentGtnDetailModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st adjustment gtn details
    * @param end the upper bound of the range of st adjustment gtn details (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of st adjustment gtn details
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.StAdjustmentGtnDetail> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the st adjustment gtn details from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of st adjustment gtn details.
    *
    * @return the number of st adjustment gtn details
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
