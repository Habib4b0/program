package com.stpl.app.service.persistence;

import com.stpl.app.model.ImtdItemPriceRebateDetails;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the imtd item price rebate details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ImtdItemPriceRebateDetailsPersistenceImpl
 * @see ImtdItemPriceRebateDetailsUtil
 * @generated
 */
public interface ImtdItemPriceRebateDetailsPersistence extends BasePersistence<ImtdItemPriceRebateDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ImtdItemPriceRebateDetailsUtil} to access the imtd item price rebate details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the imtd item price rebate details in the entity cache if it is enabled.
    *
    * @param imtdItemPriceRebateDetails the imtd item price rebate details
    */
    public void cacheResult(
        com.stpl.app.model.ImtdItemPriceRebateDetails imtdItemPriceRebateDetails);

    /**
    * Caches the imtd item price rebate detailses in the entity cache if it is enabled.
    *
    * @param imtdItemPriceRebateDetailses the imtd item price rebate detailses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.ImtdItemPriceRebateDetails> imtdItemPriceRebateDetailses);

    /**
    * Creates a new imtd item price rebate details with the primary key. Does not add the imtd item price rebate details to the database.
    *
    * @param imtdItemPriceRebateSid the primary key for the new imtd item price rebate details
    * @return the new imtd item price rebate details
    */
    public com.stpl.app.model.ImtdItemPriceRebateDetails create(
        int imtdItemPriceRebateSid);

    /**
    * Removes the imtd item price rebate details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param imtdItemPriceRebateSid the primary key of the imtd item price rebate details
    * @return the imtd item price rebate details that was removed
    * @throws com.stpl.app.NoSuchImtdItemPriceRebateDetailsException if a imtd item price rebate details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ImtdItemPriceRebateDetails remove(
        int imtdItemPriceRebateSid)
        throws com.stpl.app.NoSuchImtdItemPriceRebateDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.ImtdItemPriceRebateDetails updateImpl(
        com.stpl.app.model.ImtdItemPriceRebateDetails imtdItemPriceRebateDetails)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the imtd item price rebate details with the primary key or throws a {@link com.stpl.app.NoSuchImtdItemPriceRebateDetailsException} if it could not be found.
    *
    * @param imtdItemPriceRebateSid the primary key of the imtd item price rebate details
    * @return the imtd item price rebate details
    * @throws com.stpl.app.NoSuchImtdItemPriceRebateDetailsException if a imtd item price rebate details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ImtdItemPriceRebateDetails findByPrimaryKey(
        int imtdItemPriceRebateSid)
        throws com.stpl.app.NoSuchImtdItemPriceRebateDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the imtd item price rebate details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param imtdItemPriceRebateSid the primary key of the imtd item price rebate details
    * @return the imtd item price rebate details, or <code>null</code> if a imtd item price rebate details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ImtdItemPriceRebateDetails fetchByPrimaryKey(
        int imtdItemPriceRebateSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the imtd item price rebate detailses.
    *
    * @return the imtd item price rebate detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ImtdItemPriceRebateDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the imtd item price rebate detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdItemPriceRebateDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of imtd item price rebate detailses
    * @param end the upper bound of the range of imtd item price rebate detailses (not inclusive)
    * @return the range of imtd item price rebate detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ImtdItemPriceRebateDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the imtd item price rebate detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdItemPriceRebateDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of imtd item price rebate detailses
    * @param end the upper bound of the range of imtd item price rebate detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of imtd item price rebate detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ImtdItemPriceRebateDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the imtd item price rebate detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of imtd item price rebate detailses.
    *
    * @return the number of imtd item price rebate detailses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
