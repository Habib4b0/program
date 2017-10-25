package com.stpl.app.service.persistence;

import com.stpl.app.model.IfpDetails;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ifp details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IfpDetailsPersistenceImpl
 * @see IfpDetailsUtil
 * @generated
 */
public interface IfpDetailsPersistence extends BasePersistence<IfpDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link IfpDetailsUtil} to access the ifp details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the ifp detailses where ifpModelSid = &#63;.
    *
    * @param ifpModelSid the ifp model sid
    * @return the matching ifp detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IfpDetails> findByItemFamilyPlanDetails(
        int ifpModelSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ifp detailses where ifpModelSid = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param ifpModelSid the ifp model sid
    * @param start the lower bound of the range of ifp detailses
    * @param end the upper bound of the range of ifp detailses (not inclusive)
    * @return the range of matching ifp detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IfpDetails> findByItemFamilyPlanDetails(
        int ifpModelSid, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ifp detailses where ifpModelSid = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param ifpModelSid the ifp model sid
    * @param start the lower bound of the range of ifp detailses
    * @param end the upper bound of the range of ifp detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching ifp detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IfpDetails> findByItemFamilyPlanDetails(
        int ifpModelSid, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first ifp details in the ordered set where ifpModelSid = &#63;.
    *
    * @param ifpModelSid the ifp model sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ifp details
    * @throws com.stpl.app.NoSuchIfpDetailsException if a matching ifp details could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpDetails findByItemFamilyPlanDetails_First(
        int ifpModelSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchIfpDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first ifp details in the ordered set where ifpModelSid = &#63;.
    *
    * @param ifpModelSid the ifp model sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ifp details, or <code>null</code> if a matching ifp details could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpDetails fetchByItemFamilyPlanDetails_First(
        int ifpModelSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last ifp details in the ordered set where ifpModelSid = &#63;.
    *
    * @param ifpModelSid the ifp model sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ifp details
    * @throws com.stpl.app.NoSuchIfpDetailsException if a matching ifp details could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpDetails findByItemFamilyPlanDetails_Last(
        int ifpModelSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchIfpDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last ifp details in the ordered set where ifpModelSid = &#63;.
    *
    * @param ifpModelSid the ifp model sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ifp details, or <code>null</code> if a matching ifp details could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpDetails fetchByItemFamilyPlanDetails_Last(
        int ifpModelSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ifp detailses before and after the current ifp details in the ordered set where ifpModelSid = &#63;.
    *
    * @param ifpDetailsSid the primary key of the current ifp details
    * @param ifpModelSid the ifp model sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next ifp details
    * @throws com.stpl.app.NoSuchIfpDetailsException if a ifp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpDetails[] findByItemFamilyPlanDetails_PrevAndNext(
        int ifpDetailsSid, int ifpModelSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchIfpDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ifp detailses where ifpModelSid = &#63; from the database.
    *
    * @param ifpModelSid the ifp model sid
    * @throws SystemException if a system exception occurred
    */
    public void removeByItemFamilyPlanDetails(int ifpModelSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ifp detailses where ifpModelSid = &#63;.
    *
    * @param ifpModelSid the ifp model sid
    * @return the number of matching ifp detailses
    * @throws SystemException if a system exception occurred
    */
    public int countByItemFamilyPlanDetails(int ifpModelSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Caches the ifp details in the entity cache if it is enabled.
    *
    * @param ifpDetails the ifp details
    */
    public void cacheResult(com.stpl.app.model.IfpDetails ifpDetails);

    /**
    * Caches the ifp detailses in the entity cache if it is enabled.
    *
    * @param ifpDetailses the ifp detailses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.IfpDetails> ifpDetailses);

    /**
    * Creates a new ifp details with the primary key. Does not add the ifp details to the database.
    *
    * @param ifpDetailsSid the primary key for the new ifp details
    * @return the new ifp details
    */
    public com.stpl.app.model.IfpDetails create(int ifpDetailsSid);

    /**
    * Removes the ifp details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ifpDetailsSid the primary key of the ifp details
    * @return the ifp details that was removed
    * @throws com.stpl.app.NoSuchIfpDetailsException if a ifp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpDetails remove(int ifpDetailsSid)
        throws com.stpl.app.NoSuchIfpDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.IfpDetails updateImpl(
        com.stpl.app.model.IfpDetails ifpDetails)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ifp details with the primary key or throws a {@link com.stpl.app.NoSuchIfpDetailsException} if it could not be found.
    *
    * @param ifpDetailsSid the primary key of the ifp details
    * @return the ifp details
    * @throws com.stpl.app.NoSuchIfpDetailsException if a ifp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpDetails findByPrimaryKey(int ifpDetailsSid)
        throws com.stpl.app.NoSuchIfpDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ifp details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ifpDetailsSid the primary key of the ifp details
    * @return the ifp details, or <code>null</code> if a ifp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpDetails fetchByPrimaryKey(int ifpDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ifp detailses.
    *
    * @return the ifp detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IfpDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ifp detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ifp detailses
    * @param end the upper bound of the range of ifp detailses (not inclusive)
    * @return the range of ifp detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IfpDetails> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ifp detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ifp detailses
    * @param end the upper bound of the range of ifp detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ifp detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IfpDetails> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ifp detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ifp detailses.
    *
    * @return the number of ifp detailses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
