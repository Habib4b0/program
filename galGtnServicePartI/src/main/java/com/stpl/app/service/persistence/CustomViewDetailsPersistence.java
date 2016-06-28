package com.stpl.app.service.persistence;

import com.stpl.app.model.CustomViewDetails;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the custom view details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CustomViewDetailsPersistenceImpl
 * @see CustomViewDetailsUtil
 * @generated
 */
public interface CustomViewDetailsPersistence extends BasePersistence<CustomViewDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link CustomViewDetailsUtil} to access the custom view details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the custom view details in the entity cache if it is enabled.
    *
    * @param customViewDetails the custom view details
    */
    public void cacheResult(
        com.stpl.app.model.CustomViewDetails customViewDetails);

    /**
    * Caches the custom view detailses in the entity cache if it is enabled.
    *
    * @param customViewDetailses the custom view detailses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.CustomViewDetails> customViewDetailses);

    /**
    * Creates a new custom view details with the primary key. Does not add the custom view details to the database.
    *
    * @param customViewDetailsSid the primary key for the new custom view details
    * @return the new custom view details
    */
    public com.stpl.app.model.CustomViewDetails create(int customViewDetailsSid);

    /**
    * Removes the custom view details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param customViewDetailsSid the primary key of the custom view details
    * @return the custom view details that was removed
    * @throws com.stpl.app.NoSuchCustomViewDetailsException if a custom view details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CustomViewDetails remove(int customViewDetailsSid)
        throws com.stpl.app.NoSuchCustomViewDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.CustomViewDetails updateImpl(
        com.stpl.app.model.CustomViewDetails customViewDetails)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the custom view details with the primary key or throws a {@link com.stpl.app.NoSuchCustomViewDetailsException} if it could not be found.
    *
    * @param customViewDetailsSid the primary key of the custom view details
    * @return the custom view details
    * @throws com.stpl.app.NoSuchCustomViewDetailsException if a custom view details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CustomViewDetails findByPrimaryKey(
        int customViewDetailsSid)
        throws com.stpl.app.NoSuchCustomViewDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the custom view details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param customViewDetailsSid the primary key of the custom view details
    * @return the custom view details, or <code>null</code> if a custom view details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CustomViewDetails fetchByPrimaryKey(
        int customViewDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the custom view detailses.
    *
    * @return the custom view detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CustomViewDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the custom view detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CustomViewDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of custom view detailses
    * @param end the upper bound of the range of custom view detailses (not inclusive)
    * @return the range of custom view detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CustomViewDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the custom view detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CustomViewDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of custom view detailses
    * @param end the upper bound of the range of custom view detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of custom view detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CustomViewDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the custom view detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of custom view detailses.
    *
    * @return the number of custom view detailses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
