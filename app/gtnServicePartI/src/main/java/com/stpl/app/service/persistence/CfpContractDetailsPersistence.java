package com.stpl.app.service.persistence;

import com.stpl.app.model.CfpContractDetails;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the cfp contract details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CfpContractDetailsPersistenceImpl
 * @see CfpContractDetailsUtil
 * @generated
 */
public interface CfpContractDetailsPersistence extends BasePersistence<CfpContractDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link CfpContractDetailsUtil} to access the cfp contract details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the cfp contract detailses where companyMasterSid = &#63; and cfpContractSid = &#63;.
    *
    * @param companyMasterSid the company master sid
    * @param cfpContractSid the cfp contract sid
    * @return the matching cfp contract detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CfpContractDetails> findByCFPDetails(
        int companyMasterSid, int cfpContractSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the cfp contract detailses where companyMasterSid = &#63; and cfpContractSid = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyMasterSid the company master sid
    * @param cfpContractSid the cfp contract sid
    * @param start the lower bound of the range of cfp contract detailses
    * @param end the upper bound of the range of cfp contract detailses (not inclusive)
    * @return the range of matching cfp contract detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CfpContractDetails> findByCFPDetails(
        int companyMasterSid, int cfpContractSid, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the cfp contract detailses where companyMasterSid = &#63; and cfpContractSid = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyMasterSid the company master sid
    * @param cfpContractSid the cfp contract sid
    * @param start the lower bound of the range of cfp contract detailses
    * @param end the upper bound of the range of cfp contract detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching cfp contract detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CfpContractDetails> findByCFPDetails(
        int companyMasterSid, int cfpContractSid, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first cfp contract details in the ordered set where companyMasterSid = &#63; and cfpContractSid = &#63;.
    *
    * @param companyMasterSid the company master sid
    * @param cfpContractSid the cfp contract sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cfp contract details
    * @throws com.stpl.app.NoSuchCfpContractDetailsException if a matching cfp contract details could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpContractDetails findByCFPDetails_First(
        int companyMasterSid, int cfpContractSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpContractDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first cfp contract details in the ordered set where companyMasterSid = &#63; and cfpContractSid = &#63;.
    *
    * @param companyMasterSid the company master sid
    * @param cfpContractSid the cfp contract sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cfp contract details, or <code>null</code> if a matching cfp contract details could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpContractDetails fetchByCFPDetails_First(
        int companyMasterSid, int cfpContractSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last cfp contract details in the ordered set where companyMasterSid = &#63; and cfpContractSid = &#63;.
    *
    * @param companyMasterSid the company master sid
    * @param cfpContractSid the cfp contract sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cfp contract details
    * @throws com.stpl.app.NoSuchCfpContractDetailsException if a matching cfp contract details could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpContractDetails findByCFPDetails_Last(
        int companyMasterSid, int cfpContractSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpContractDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last cfp contract details in the ordered set where companyMasterSid = &#63; and cfpContractSid = &#63;.
    *
    * @param companyMasterSid the company master sid
    * @param cfpContractSid the cfp contract sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cfp contract details, or <code>null</code> if a matching cfp contract details could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpContractDetails fetchByCFPDetails_Last(
        int companyMasterSid, int cfpContractSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the cfp contract detailses before and after the current cfp contract details in the ordered set where companyMasterSid = &#63; and cfpContractSid = &#63;.
    *
    * @param cfpContractDetailsSid the primary key of the current cfp contract details
    * @param companyMasterSid the company master sid
    * @param cfpContractSid the cfp contract sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next cfp contract details
    * @throws com.stpl.app.NoSuchCfpContractDetailsException if a cfp contract details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpContractDetails[] findByCFPDetails_PrevAndNext(
        int cfpContractDetailsSid, int companyMasterSid, int cfpContractSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpContractDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the cfp contract detailses where companyMasterSid = &#63; and cfpContractSid = &#63; from the database.
    *
    * @param companyMasterSid the company master sid
    * @param cfpContractSid the cfp contract sid
    * @throws SystemException if a system exception occurred
    */
    public void removeByCFPDetails(int companyMasterSid, int cfpContractSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of cfp contract detailses where companyMasterSid = &#63; and cfpContractSid = &#63;.
    *
    * @param companyMasterSid the company master sid
    * @param cfpContractSid the cfp contract sid
    * @return the number of matching cfp contract detailses
    * @throws SystemException if a system exception occurred
    */
    public int countByCFPDetails(int companyMasterSid, int cfpContractSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Caches the cfp contract details in the entity cache if it is enabled.
    *
    * @param cfpContractDetails the cfp contract details
    */
    public void cacheResult(
        com.stpl.app.model.CfpContractDetails cfpContractDetails);

    /**
    * Caches the cfp contract detailses in the entity cache if it is enabled.
    *
    * @param cfpContractDetailses the cfp contract detailses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.CfpContractDetails> cfpContractDetailses);

    /**
    * Creates a new cfp contract details with the primary key. Does not add the cfp contract details to the database.
    *
    * @param cfpContractDetailsSid the primary key for the new cfp contract details
    * @return the new cfp contract details
    */
    public com.stpl.app.model.CfpContractDetails create(
        int cfpContractDetailsSid);

    /**
    * Removes the cfp contract details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param cfpContractDetailsSid the primary key of the cfp contract details
    * @return the cfp contract details that was removed
    * @throws com.stpl.app.NoSuchCfpContractDetailsException if a cfp contract details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpContractDetails remove(
        int cfpContractDetailsSid)
        throws com.stpl.app.NoSuchCfpContractDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.CfpContractDetails updateImpl(
        com.stpl.app.model.CfpContractDetails cfpContractDetails)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the cfp contract details with the primary key or throws a {@link com.stpl.app.NoSuchCfpContractDetailsException} if it could not be found.
    *
    * @param cfpContractDetailsSid the primary key of the cfp contract details
    * @return the cfp contract details
    * @throws com.stpl.app.NoSuchCfpContractDetailsException if a cfp contract details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpContractDetails findByPrimaryKey(
        int cfpContractDetailsSid)
        throws com.stpl.app.NoSuchCfpContractDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the cfp contract details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param cfpContractDetailsSid the primary key of the cfp contract details
    * @return the cfp contract details, or <code>null</code> if a cfp contract details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpContractDetails fetchByPrimaryKey(
        int cfpContractDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the cfp contract detailses.
    *
    * @return the cfp contract detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CfpContractDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the cfp contract detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cfp contract detailses
    * @param end the upper bound of the range of cfp contract detailses (not inclusive)
    * @return the range of cfp contract detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CfpContractDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the cfp contract detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cfp contract detailses
    * @param end the upper bound of the range of cfp contract detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of cfp contract detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CfpContractDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the cfp contract detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of cfp contract detailses.
    *
    * @return the number of cfp contract detailses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
