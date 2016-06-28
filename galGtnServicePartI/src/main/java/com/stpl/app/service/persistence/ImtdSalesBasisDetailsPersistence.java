package com.stpl.app.service.persistence;

import com.stpl.app.model.ImtdSalesBasisDetails;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the imtd sales basis details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ImtdSalesBasisDetailsPersistenceImpl
 * @see ImtdSalesBasisDetailsUtil
 * @generated
 */
public interface ImtdSalesBasisDetailsPersistence extends BasePersistence<ImtdSalesBasisDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ImtdSalesBasisDetailsUtil} to access the imtd sales basis details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the imtd sales basis details in the entity cache if it is enabled.
    *
    * @param imtdSalesBasisDetails the imtd sales basis details
    */
    public void cacheResult(
        com.stpl.app.model.ImtdSalesBasisDetails imtdSalesBasisDetails);

    /**
    * Caches the imtd sales basis detailses in the entity cache if it is enabled.
    *
    * @param imtdSalesBasisDetailses the imtd sales basis detailses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.ImtdSalesBasisDetails> imtdSalesBasisDetailses);

    /**
    * Creates a new imtd sales basis details with the primary key. Does not add the imtd sales basis details to the database.
    *
    * @param imtdSalesBasisDetailsSid the primary key for the new imtd sales basis details
    * @return the new imtd sales basis details
    */
    public com.stpl.app.model.ImtdSalesBasisDetails create(
        int imtdSalesBasisDetailsSid);

    /**
    * Removes the imtd sales basis details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param imtdSalesBasisDetailsSid the primary key of the imtd sales basis details
    * @return the imtd sales basis details that was removed
    * @throws com.stpl.app.NoSuchImtdSalesBasisDetailsException if a imtd sales basis details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ImtdSalesBasisDetails remove(
        int imtdSalesBasisDetailsSid)
        throws com.stpl.app.NoSuchImtdSalesBasisDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.ImtdSalesBasisDetails updateImpl(
        com.stpl.app.model.ImtdSalesBasisDetails imtdSalesBasisDetails)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the imtd sales basis details with the primary key or throws a {@link com.stpl.app.NoSuchImtdSalesBasisDetailsException} if it could not be found.
    *
    * @param imtdSalesBasisDetailsSid the primary key of the imtd sales basis details
    * @return the imtd sales basis details
    * @throws com.stpl.app.NoSuchImtdSalesBasisDetailsException if a imtd sales basis details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ImtdSalesBasisDetails findByPrimaryKey(
        int imtdSalesBasisDetailsSid)
        throws com.stpl.app.NoSuchImtdSalesBasisDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the imtd sales basis details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param imtdSalesBasisDetailsSid the primary key of the imtd sales basis details
    * @return the imtd sales basis details, or <code>null</code> if a imtd sales basis details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ImtdSalesBasisDetails fetchByPrimaryKey(
        int imtdSalesBasisDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the imtd sales basis detailses.
    *
    * @return the imtd sales basis detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ImtdSalesBasisDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the imtd sales basis detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdSalesBasisDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of imtd sales basis detailses
    * @param end the upper bound of the range of imtd sales basis detailses (not inclusive)
    * @return the range of imtd sales basis detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ImtdSalesBasisDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the imtd sales basis detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdSalesBasisDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of imtd sales basis detailses
    * @param end the upper bound of the range of imtd sales basis detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of imtd sales basis detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ImtdSalesBasisDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the imtd sales basis detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of imtd sales basis detailses.
    *
    * @return the number of imtd sales basis detailses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
