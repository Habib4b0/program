package com.stpl.app.service.persistence;

import com.stpl.app.model.IfpContractDetails;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ifp contract details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IfpContractDetailsPersistenceImpl
 * @see IfpContractDetailsUtil
 * @generated
 */
public interface IfpContractDetailsPersistence extends BasePersistence<IfpContractDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link IfpContractDetailsUtil} to access the ifp contract details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the ifp contract detailses where ifpContractSid = &#63; and itemMasterSid = &#63;.
    *
    * @param ifpContractSid the ifp contract sid
    * @param itemMasterSid the item master sid
    * @return the matching ifp contract detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IfpContractDetails> findByIFPDetails(
        int ifpContractSid, int itemMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ifp contract detailses where ifpContractSid = &#63; and itemMasterSid = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param ifpContractSid the ifp contract sid
    * @param itemMasterSid the item master sid
    * @param start the lower bound of the range of ifp contract detailses
    * @param end the upper bound of the range of ifp contract detailses (not inclusive)
    * @return the range of matching ifp contract detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IfpContractDetails> findByIFPDetails(
        int ifpContractSid, int itemMasterSid, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ifp contract detailses where ifpContractSid = &#63; and itemMasterSid = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param ifpContractSid the ifp contract sid
    * @param itemMasterSid the item master sid
    * @param start the lower bound of the range of ifp contract detailses
    * @param end the upper bound of the range of ifp contract detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching ifp contract detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IfpContractDetails> findByIFPDetails(
        int ifpContractSid, int itemMasterSid, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first ifp contract details in the ordered set where ifpContractSid = &#63; and itemMasterSid = &#63;.
    *
    * @param ifpContractSid the ifp contract sid
    * @param itemMasterSid the item master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ifp contract details
    * @throws com.stpl.app.NoSuchIfpContractDetailsException if a matching ifp contract details could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpContractDetails findByIFPDetails_First(
        int ifpContractSid, int itemMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchIfpContractDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first ifp contract details in the ordered set where ifpContractSid = &#63; and itemMasterSid = &#63;.
    *
    * @param ifpContractSid the ifp contract sid
    * @param itemMasterSid the item master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ifp contract details, or <code>null</code> if a matching ifp contract details could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpContractDetails fetchByIFPDetails_First(
        int ifpContractSid, int itemMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last ifp contract details in the ordered set where ifpContractSid = &#63; and itemMasterSid = &#63;.
    *
    * @param ifpContractSid the ifp contract sid
    * @param itemMasterSid the item master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ifp contract details
    * @throws com.stpl.app.NoSuchIfpContractDetailsException if a matching ifp contract details could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpContractDetails findByIFPDetails_Last(
        int ifpContractSid, int itemMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchIfpContractDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last ifp contract details in the ordered set where ifpContractSid = &#63; and itemMasterSid = &#63;.
    *
    * @param ifpContractSid the ifp contract sid
    * @param itemMasterSid the item master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ifp contract details, or <code>null</code> if a matching ifp contract details could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpContractDetails fetchByIFPDetails_Last(
        int ifpContractSid, int itemMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ifp contract detailses before and after the current ifp contract details in the ordered set where ifpContractSid = &#63; and itemMasterSid = &#63;.
    *
    * @param ifpContractDetailsSid the primary key of the current ifp contract details
    * @param ifpContractSid the ifp contract sid
    * @param itemMasterSid the item master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next ifp contract details
    * @throws com.stpl.app.NoSuchIfpContractDetailsException if a ifp contract details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpContractDetails[] findByIFPDetails_PrevAndNext(
        int ifpContractDetailsSid, int ifpContractSid, int itemMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchIfpContractDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ifp contract detailses where ifpContractSid = &#63; and itemMasterSid = &#63; from the database.
    *
    * @param ifpContractSid the ifp contract sid
    * @param itemMasterSid the item master sid
    * @throws SystemException if a system exception occurred
    */
    public void removeByIFPDetails(int ifpContractSid, int itemMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ifp contract detailses where ifpContractSid = &#63; and itemMasterSid = &#63;.
    *
    * @param ifpContractSid the ifp contract sid
    * @param itemMasterSid the item master sid
    * @return the number of matching ifp contract detailses
    * @throws SystemException if a system exception occurred
    */
    public int countByIFPDetails(int ifpContractSid, int itemMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Caches the ifp contract details in the entity cache if it is enabled.
    *
    * @param ifpContractDetails the ifp contract details
    */
    public void cacheResult(
        com.stpl.app.model.IfpContractDetails ifpContractDetails);

    /**
    * Caches the ifp contract detailses in the entity cache if it is enabled.
    *
    * @param ifpContractDetailses the ifp contract detailses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.IfpContractDetails> ifpContractDetailses);

    /**
    * Creates a new ifp contract details with the primary key. Does not add the ifp contract details to the database.
    *
    * @param ifpContractDetailsSid the primary key for the new ifp contract details
    * @return the new ifp contract details
    */
    public com.stpl.app.model.IfpContractDetails create(
        int ifpContractDetailsSid);

    /**
    * Removes the ifp contract details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ifpContractDetailsSid the primary key of the ifp contract details
    * @return the ifp contract details that was removed
    * @throws com.stpl.app.NoSuchIfpContractDetailsException if a ifp contract details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpContractDetails remove(
        int ifpContractDetailsSid)
        throws com.stpl.app.NoSuchIfpContractDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.IfpContractDetails updateImpl(
        com.stpl.app.model.IfpContractDetails ifpContractDetails)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ifp contract details with the primary key or throws a {@link com.stpl.app.NoSuchIfpContractDetailsException} if it could not be found.
    *
    * @param ifpContractDetailsSid the primary key of the ifp contract details
    * @return the ifp contract details
    * @throws com.stpl.app.NoSuchIfpContractDetailsException if a ifp contract details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpContractDetails findByPrimaryKey(
        int ifpContractDetailsSid)
        throws com.stpl.app.NoSuchIfpContractDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ifp contract details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ifpContractDetailsSid the primary key of the ifp contract details
    * @return the ifp contract details, or <code>null</code> if a ifp contract details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpContractDetails fetchByPrimaryKey(
        int ifpContractDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ifp contract detailses.
    *
    * @return the ifp contract detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IfpContractDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ifp contract detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ifp contract detailses
    * @param end the upper bound of the range of ifp contract detailses (not inclusive)
    * @return the range of ifp contract detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IfpContractDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ifp contract detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ifp contract detailses
    * @param end the upper bound of the range of ifp contract detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ifp contract detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IfpContractDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ifp contract detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ifp contract detailses.
    *
    * @return the number of ifp contract detailses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
