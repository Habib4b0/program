package com.stpl.app.service.persistence;

import com.stpl.app.model.CfpDetails;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the cfp details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CfpDetailsPersistenceImpl
 * @see CfpDetailsUtil
 * @generated
 */
public interface CfpDetailsPersistence extends BasePersistence<CfpDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link CfpDetailsUtil} to access the cfp details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the cfp detailses where cfpModelSid = &#63;.
    *
    * @param cfpModelSid the cfp model sid
    * @return the matching cfp detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CfpDetails> findByCfpModelSid(
        int cfpModelSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the cfp detailses where cfpModelSid = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param cfpModelSid the cfp model sid
    * @param start the lower bound of the range of cfp detailses
    * @param end the upper bound of the range of cfp detailses (not inclusive)
    * @return the range of matching cfp detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CfpDetails> findByCfpModelSid(
        int cfpModelSid, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the cfp detailses where cfpModelSid = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param cfpModelSid the cfp model sid
    * @param start the lower bound of the range of cfp detailses
    * @param end the upper bound of the range of cfp detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching cfp detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CfpDetails> findByCfpModelSid(
        int cfpModelSid, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first cfp details in the ordered set where cfpModelSid = &#63;.
    *
    * @param cfpModelSid the cfp model sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cfp details
    * @throws com.stpl.app.NoSuchCfpDetailsException if a matching cfp details could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpDetails findByCfpModelSid_First(
        int cfpModelSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first cfp details in the ordered set where cfpModelSid = &#63;.
    *
    * @param cfpModelSid the cfp model sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cfp details, or <code>null</code> if a matching cfp details could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpDetails fetchByCfpModelSid_First(
        int cfpModelSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last cfp details in the ordered set where cfpModelSid = &#63;.
    *
    * @param cfpModelSid the cfp model sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cfp details
    * @throws com.stpl.app.NoSuchCfpDetailsException if a matching cfp details could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpDetails findByCfpModelSid_Last(
        int cfpModelSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last cfp details in the ordered set where cfpModelSid = &#63;.
    *
    * @param cfpModelSid the cfp model sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cfp details, or <code>null</code> if a matching cfp details could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpDetails fetchByCfpModelSid_Last(
        int cfpModelSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the cfp detailses before and after the current cfp details in the ordered set where cfpModelSid = &#63;.
    *
    * @param cfpDetailsSid the primary key of the current cfp details
    * @param cfpModelSid the cfp model sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next cfp details
    * @throws com.stpl.app.NoSuchCfpDetailsException if a cfp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpDetails[] findByCfpModelSid_PrevAndNext(
        int cfpDetailsSid, int cfpModelSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the cfp detailses where cfpModelSid = &#63; from the database.
    *
    * @param cfpModelSid the cfp model sid
    * @throws SystemException if a system exception occurred
    */
    public void removeByCfpModelSid(int cfpModelSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of cfp detailses where cfpModelSid = &#63;.
    *
    * @param cfpModelSid the cfp model sid
    * @return the number of matching cfp detailses
    * @throws SystemException if a system exception occurred
    */
    public int countByCfpModelSid(int cfpModelSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Caches the cfp details in the entity cache if it is enabled.
    *
    * @param cfpDetails the cfp details
    */
    public void cacheResult(com.stpl.app.model.CfpDetails cfpDetails);

    /**
    * Caches the cfp detailses in the entity cache if it is enabled.
    *
    * @param cfpDetailses the cfp detailses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.CfpDetails> cfpDetailses);

    /**
    * Creates a new cfp details with the primary key. Does not add the cfp details to the database.
    *
    * @param cfpDetailsSid the primary key for the new cfp details
    * @return the new cfp details
    */
    public com.stpl.app.model.CfpDetails create(int cfpDetailsSid);

    /**
    * Removes the cfp details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param cfpDetailsSid the primary key of the cfp details
    * @return the cfp details that was removed
    * @throws com.stpl.app.NoSuchCfpDetailsException if a cfp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpDetails remove(int cfpDetailsSid)
        throws com.stpl.app.NoSuchCfpDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.CfpDetails updateImpl(
        com.stpl.app.model.CfpDetails cfpDetails)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the cfp details with the primary key or throws a {@link com.stpl.app.NoSuchCfpDetailsException} if it could not be found.
    *
    * @param cfpDetailsSid the primary key of the cfp details
    * @return the cfp details
    * @throws com.stpl.app.NoSuchCfpDetailsException if a cfp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpDetails findByPrimaryKey(int cfpDetailsSid)
        throws com.stpl.app.NoSuchCfpDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the cfp details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param cfpDetailsSid the primary key of the cfp details
    * @return the cfp details, or <code>null</code> if a cfp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpDetails fetchByPrimaryKey(int cfpDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the cfp detailses.
    *
    * @return the cfp detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CfpDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the cfp detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cfp detailses
    * @param end the upper bound of the range of cfp detailses (not inclusive)
    * @return the range of cfp detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CfpDetails> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the cfp detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cfp detailses
    * @param end the upper bound of the range of cfp detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of cfp detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CfpDetails> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the cfp detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of cfp detailses.
    *
    * @return the number of cfp detailses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
