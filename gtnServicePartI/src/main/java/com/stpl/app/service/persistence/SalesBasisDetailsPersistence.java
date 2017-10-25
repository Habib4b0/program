package com.stpl.app.service.persistence;

import com.stpl.app.model.SalesBasisDetails;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the sales basis details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see SalesBasisDetailsPersistenceImpl
 * @see SalesBasisDetailsUtil
 * @generated
 */
public interface SalesBasisDetailsPersistence extends BasePersistence<SalesBasisDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link SalesBasisDetailsUtil} to access the sales basis details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the sales basis details in the entity cache if it is enabled.
    *
    * @param salesBasisDetails the sales basis details
    */
    public void cacheResult(
        com.stpl.app.model.SalesBasisDetails salesBasisDetails);

    /**
    * Caches the sales basis detailses in the entity cache if it is enabled.
    *
    * @param salesBasisDetailses the sales basis detailses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.SalesBasisDetails> salesBasisDetailses);

    /**
    * Creates a new sales basis details with the primary key. Does not add the sales basis details to the database.
    *
    * @param salesBasisDetailsSid the primary key for the new sales basis details
    * @return the new sales basis details
    */
    public com.stpl.app.model.SalesBasisDetails create(int salesBasisDetailsSid);

    /**
    * Removes the sales basis details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param salesBasisDetailsSid the primary key of the sales basis details
    * @return the sales basis details that was removed
    * @throws com.stpl.app.NoSuchSalesBasisDetailsException if a sales basis details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesBasisDetails remove(int salesBasisDetailsSid)
        throws com.stpl.app.NoSuchSalesBasisDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.SalesBasisDetails updateImpl(
        com.stpl.app.model.SalesBasisDetails salesBasisDetails)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the sales basis details with the primary key or throws a {@link com.stpl.app.NoSuchSalesBasisDetailsException} if it could not be found.
    *
    * @param salesBasisDetailsSid the primary key of the sales basis details
    * @return the sales basis details
    * @throws com.stpl.app.NoSuchSalesBasisDetailsException if a sales basis details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesBasisDetails findByPrimaryKey(
        int salesBasisDetailsSid)
        throws com.stpl.app.NoSuchSalesBasisDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the sales basis details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param salesBasisDetailsSid the primary key of the sales basis details
    * @return the sales basis details, or <code>null</code> if a sales basis details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.SalesBasisDetails fetchByPrimaryKey(
        int salesBasisDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the sales basis detailses.
    *
    * @return the sales basis detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.SalesBasisDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the sales basis detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesBasisDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of sales basis detailses
    * @param end the upper bound of the range of sales basis detailses (not inclusive)
    * @return the range of sales basis detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.SalesBasisDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the sales basis detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesBasisDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of sales basis detailses
    * @param end the upper bound of the range of sales basis detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of sales basis detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.SalesBasisDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the sales basis detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of sales basis detailses.
    *
    * @return the number of sales basis detailses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
