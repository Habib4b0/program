package com.stpl.app.service.persistence;

import com.stpl.app.model.BestPriceMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the best price master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see BestPriceMasterPersistenceImpl
 * @see BestPriceMasterUtil
 * @generated
 */
public interface BestPriceMasterPersistence extends BasePersistence<BestPriceMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link BestPriceMasterUtil} to access the best price master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the best price masters where itemId = &#63;.
    *
    * @param itemId the item ID
    * @return the matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.BestPriceMaster> findByItemId(
        java.lang.String itemId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the best price masters where itemId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemId the item ID
    * @param start the lower bound of the range of best price masters
    * @param end the upper bound of the range of best price masters (not inclusive)
    * @return the range of matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.BestPriceMaster> findByItemId(
        java.lang.String itemId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the best price masters where itemId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemId the item ID
    * @param start the lower bound of the range of best price masters
    * @param end the upper bound of the range of best price masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.BestPriceMaster> findByItemId(
        java.lang.String itemId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first best price master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching best price master
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BestPriceMaster findByItemId_First(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first best price master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching best price master, or <code>null</code> if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BestPriceMaster fetchByItemId_First(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last best price master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching best price master
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BestPriceMaster findByItemId_Last(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last best price master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching best price master, or <code>null</code> if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BestPriceMaster fetchByItemId_Last(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the best price masters before and after the current best price master in the ordered set where itemId = &#63;.
    *
    * @param bestPriceMasterSid the primary key of the current best price master
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next best price master
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a best price master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BestPriceMaster[] findByItemId_PrevAndNext(
        int bestPriceMasterSid, java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the best price masters where itemId = &#63; from the database.
    *
    * @param itemId the item ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByItemId(java.lang.String itemId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of best price masters where itemId = &#63;.
    *
    * @param itemId the item ID
    * @return the number of matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public int countByItemId(java.lang.String itemId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the best price masters where itemNo = &#63;.
    *
    * @param itemNo the item no
    * @return the matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.BestPriceMaster> findByItemNo(
        java.lang.String itemNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the best price masters where itemNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemNo the item no
    * @param start the lower bound of the range of best price masters
    * @param end the upper bound of the range of best price masters (not inclusive)
    * @return the range of matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.BestPriceMaster> findByItemNo(
        java.lang.String itemNo, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the best price masters where itemNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemNo the item no
    * @param start the lower bound of the range of best price masters
    * @param end the upper bound of the range of best price masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.BestPriceMaster> findByItemNo(
        java.lang.String itemNo, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first best price master in the ordered set where itemNo = &#63;.
    *
    * @param itemNo the item no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching best price master
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BestPriceMaster findByItemNo_First(
        java.lang.String itemNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first best price master in the ordered set where itemNo = &#63;.
    *
    * @param itemNo the item no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching best price master, or <code>null</code> if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BestPriceMaster fetchByItemNo_First(
        java.lang.String itemNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last best price master in the ordered set where itemNo = &#63;.
    *
    * @param itemNo the item no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching best price master
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BestPriceMaster findByItemNo_Last(
        java.lang.String itemNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last best price master in the ordered set where itemNo = &#63;.
    *
    * @param itemNo the item no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching best price master, or <code>null</code> if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BestPriceMaster fetchByItemNo_Last(
        java.lang.String itemNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the best price masters before and after the current best price master in the ordered set where itemNo = &#63;.
    *
    * @param bestPriceMasterSid the primary key of the current best price master
    * @param itemNo the item no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next best price master
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a best price master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BestPriceMaster[] findByItemNo_PrevAndNext(
        int bestPriceMasterSid, java.lang.String itemNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the best price masters where itemNo = &#63; from the database.
    *
    * @param itemNo the item no
    * @throws SystemException if a system exception occurred
    */
    public void removeByItemNo(java.lang.String itemNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of best price masters where itemNo = &#63;.
    *
    * @param itemNo the item no
    * @return the number of matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public int countByItemNo(java.lang.String itemNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the best price masters where contractNo = &#63;.
    *
    * @param contractNo the contract no
    * @return the matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.BestPriceMaster> findByContractNo(
        java.lang.String contractNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the best price masters where contractNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contractNo the contract no
    * @param start the lower bound of the range of best price masters
    * @param end the upper bound of the range of best price masters (not inclusive)
    * @return the range of matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.BestPriceMaster> findByContractNo(
        java.lang.String contractNo, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the best price masters where contractNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contractNo the contract no
    * @param start the lower bound of the range of best price masters
    * @param end the upper bound of the range of best price masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.BestPriceMaster> findByContractNo(
        java.lang.String contractNo, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first best price master in the ordered set where contractNo = &#63;.
    *
    * @param contractNo the contract no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching best price master
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BestPriceMaster findByContractNo_First(
        java.lang.String contractNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first best price master in the ordered set where contractNo = &#63;.
    *
    * @param contractNo the contract no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching best price master, or <code>null</code> if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BestPriceMaster fetchByContractNo_First(
        java.lang.String contractNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last best price master in the ordered set where contractNo = &#63;.
    *
    * @param contractNo the contract no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching best price master
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BestPriceMaster findByContractNo_Last(
        java.lang.String contractNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last best price master in the ordered set where contractNo = &#63;.
    *
    * @param contractNo the contract no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching best price master, or <code>null</code> if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BestPriceMaster fetchByContractNo_Last(
        java.lang.String contractNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the best price masters before and after the current best price master in the ordered set where contractNo = &#63;.
    *
    * @param bestPriceMasterSid the primary key of the current best price master
    * @param contractNo the contract no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next best price master
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a best price master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BestPriceMaster[] findByContractNo_PrevAndNext(
        int bestPriceMasterSid, java.lang.String contractNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the best price masters where contractNo = &#63; from the database.
    *
    * @param contractNo the contract no
    * @throws SystemException if a system exception occurred
    */
    public void removeByContractNo(java.lang.String contractNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of best price masters where contractNo = &#63;.
    *
    * @param contractNo the contract no
    * @return the number of matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public int countByContractNo(java.lang.String contractNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the best price masters where contractId = &#63;.
    *
    * @param contractId the contract ID
    * @return the matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.BestPriceMaster> findByContractId(
        java.lang.String contractId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the best price masters where contractId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contractId the contract ID
    * @param start the lower bound of the range of best price masters
    * @param end the upper bound of the range of best price masters (not inclusive)
    * @return the range of matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.BestPriceMaster> findByContractId(
        java.lang.String contractId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the best price masters where contractId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contractId the contract ID
    * @param start the lower bound of the range of best price masters
    * @param end the upper bound of the range of best price masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.BestPriceMaster> findByContractId(
        java.lang.String contractId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first best price master in the ordered set where contractId = &#63;.
    *
    * @param contractId the contract ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching best price master
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BestPriceMaster findByContractId_First(
        java.lang.String contractId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first best price master in the ordered set where contractId = &#63;.
    *
    * @param contractId the contract ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching best price master, or <code>null</code> if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BestPriceMaster fetchByContractId_First(
        java.lang.String contractId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last best price master in the ordered set where contractId = &#63;.
    *
    * @param contractId the contract ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching best price master
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BestPriceMaster findByContractId_Last(
        java.lang.String contractId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last best price master in the ordered set where contractId = &#63;.
    *
    * @param contractId the contract ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching best price master, or <code>null</code> if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BestPriceMaster fetchByContractId_Last(
        java.lang.String contractId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the best price masters before and after the current best price master in the ordered set where contractId = &#63;.
    *
    * @param bestPriceMasterSid the primary key of the current best price master
    * @param contractId the contract ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next best price master
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a best price master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BestPriceMaster[] findByContractId_PrevAndNext(
        int bestPriceMasterSid, java.lang.String contractId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the best price masters where contractId = &#63; from the database.
    *
    * @param contractId the contract ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByContractId(java.lang.String contractId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of best price masters where contractId = &#63;.
    *
    * @param contractId the contract ID
    * @return the number of matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public int countByContractId(java.lang.String contractId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the best price masters where accountId = &#63;.
    *
    * @param accountId the account ID
    * @return the matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.BestPriceMaster> findByAccountId(
        java.lang.String accountId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the best price masters where accountId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param accountId the account ID
    * @param start the lower bound of the range of best price masters
    * @param end the upper bound of the range of best price masters (not inclusive)
    * @return the range of matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.BestPriceMaster> findByAccountId(
        java.lang.String accountId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the best price masters where accountId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param accountId the account ID
    * @param start the lower bound of the range of best price masters
    * @param end the upper bound of the range of best price masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.BestPriceMaster> findByAccountId(
        java.lang.String accountId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first best price master in the ordered set where accountId = &#63;.
    *
    * @param accountId the account ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching best price master
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BestPriceMaster findByAccountId_First(
        java.lang.String accountId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first best price master in the ordered set where accountId = &#63;.
    *
    * @param accountId the account ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching best price master, or <code>null</code> if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BestPriceMaster fetchByAccountId_First(
        java.lang.String accountId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last best price master in the ordered set where accountId = &#63;.
    *
    * @param accountId the account ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching best price master
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BestPriceMaster findByAccountId_Last(
        java.lang.String accountId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last best price master in the ordered set where accountId = &#63;.
    *
    * @param accountId the account ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching best price master, or <code>null</code> if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BestPriceMaster fetchByAccountId_Last(
        java.lang.String accountId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the best price masters before and after the current best price master in the ordered set where accountId = &#63;.
    *
    * @param bestPriceMasterSid the primary key of the current best price master
    * @param accountId the account ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next best price master
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a best price master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BestPriceMaster[] findByAccountId_PrevAndNext(
        int bestPriceMasterSid, java.lang.String accountId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the best price masters where accountId = &#63; from the database.
    *
    * @param accountId the account ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByAccountId(java.lang.String accountId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of best price masters where accountId = &#63;.
    *
    * @param accountId the account ID
    * @return the number of matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public int countByAccountId(java.lang.String accountId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the best price masters where period = &#63;.
    *
    * @param period the period
    * @return the matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.BestPriceMaster> findByPeriod(
        java.lang.String period)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the best price masters where period = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param period the period
    * @param start the lower bound of the range of best price masters
    * @param end the upper bound of the range of best price masters (not inclusive)
    * @return the range of matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.BestPriceMaster> findByPeriod(
        java.lang.String period, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the best price masters where period = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param period the period
    * @param start the lower bound of the range of best price masters
    * @param end the upper bound of the range of best price masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.BestPriceMaster> findByPeriod(
        java.lang.String period, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first best price master in the ordered set where period = &#63;.
    *
    * @param period the period
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching best price master
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BestPriceMaster findByPeriod_First(
        java.lang.String period,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first best price master in the ordered set where period = &#63;.
    *
    * @param period the period
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching best price master, or <code>null</code> if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BestPriceMaster fetchByPeriod_First(
        java.lang.String period,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last best price master in the ordered set where period = &#63;.
    *
    * @param period the period
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching best price master
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BestPriceMaster findByPeriod_Last(
        java.lang.String period,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last best price master in the ordered set where period = &#63;.
    *
    * @param period the period
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching best price master, or <code>null</code> if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BestPriceMaster fetchByPeriod_Last(
        java.lang.String period,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the best price masters before and after the current best price master in the ordered set where period = &#63;.
    *
    * @param bestPriceMasterSid the primary key of the current best price master
    * @param period the period
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next best price master
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a best price master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BestPriceMaster[] findByPeriod_PrevAndNext(
        int bestPriceMasterSid, java.lang.String period,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the best price masters where period = &#63; from the database.
    *
    * @param period the period
    * @throws SystemException if a system exception occurred
    */
    public void removeByPeriod(java.lang.String period)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of best price masters where period = &#63;.
    *
    * @param period the period
    * @return the number of matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public int countByPeriod(java.lang.String period)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the best price masters where itemId = &#63; and accountId = &#63; and period = &#63; and contractNo = &#63;.
    *
    * @param itemId the item ID
    * @param accountId the account ID
    * @param period the period
    * @param contractNo the contract no
    * @return the matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.BestPriceMaster> findByBestPriceUnique(
        java.lang.String itemId, java.lang.String accountId,
        java.lang.String period, java.lang.String contractNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the best price masters where itemId = &#63; and accountId = &#63; and period = &#63; and contractNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemId the item ID
    * @param accountId the account ID
    * @param period the period
    * @param contractNo the contract no
    * @param start the lower bound of the range of best price masters
    * @param end the upper bound of the range of best price masters (not inclusive)
    * @return the range of matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.BestPriceMaster> findByBestPriceUnique(
        java.lang.String itemId, java.lang.String accountId,
        java.lang.String period, java.lang.String contractNo, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the best price masters where itemId = &#63; and accountId = &#63; and period = &#63; and contractNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemId the item ID
    * @param accountId the account ID
    * @param period the period
    * @param contractNo the contract no
    * @param start the lower bound of the range of best price masters
    * @param end the upper bound of the range of best price masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.BestPriceMaster> findByBestPriceUnique(
        java.lang.String itemId, java.lang.String accountId,
        java.lang.String period, java.lang.String contractNo, int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first best price master in the ordered set where itemId = &#63; and accountId = &#63; and period = &#63; and contractNo = &#63;.
    *
    * @param itemId the item ID
    * @param accountId the account ID
    * @param period the period
    * @param contractNo the contract no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching best price master
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BestPriceMaster findByBestPriceUnique_First(
        java.lang.String itemId, java.lang.String accountId,
        java.lang.String period, java.lang.String contractNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first best price master in the ordered set where itemId = &#63; and accountId = &#63; and period = &#63; and contractNo = &#63;.
    *
    * @param itemId the item ID
    * @param accountId the account ID
    * @param period the period
    * @param contractNo the contract no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching best price master, or <code>null</code> if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BestPriceMaster fetchByBestPriceUnique_First(
        java.lang.String itemId, java.lang.String accountId,
        java.lang.String period, java.lang.String contractNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last best price master in the ordered set where itemId = &#63; and accountId = &#63; and period = &#63; and contractNo = &#63;.
    *
    * @param itemId the item ID
    * @param accountId the account ID
    * @param period the period
    * @param contractNo the contract no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching best price master
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BestPriceMaster findByBestPriceUnique_Last(
        java.lang.String itemId, java.lang.String accountId,
        java.lang.String period, java.lang.String contractNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last best price master in the ordered set where itemId = &#63; and accountId = &#63; and period = &#63; and contractNo = &#63;.
    *
    * @param itemId the item ID
    * @param accountId the account ID
    * @param period the period
    * @param contractNo the contract no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching best price master, or <code>null</code> if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BestPriceMaster fetchByBestPriceUnique_Last(
        java.lang.String itemId, java.lang.String accountId,
        java.lang.String period, java.lang.String contractNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the best price masters before and after the current best price master in the ordered set where itemId = &#63; and accountId = &#63; and period = &#63; and contractNo = &#63;.
    *
    * @param bestPriceMasterSid the primary key of the current best price master
    * @param itemId the item ID
    * @param accountId the account ID
    * @param period the period
    * @param contractNo the contract no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next best price master
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a best price master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BestPriceMaster[] findByBestPriceUnique_PrevAndNext(
        int bestPriceMasterSid, java.lang.String itemId,
        java.lang.String accountId, java.lang.String period,
        java.lang.String contractNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the best price masters where itemId = &#63; and accountId = &#63; and period = &#63; and contractNo = &#63; from the database.
    *
    * @param itemId the item ID
    * @param accountId the account ID
    * @param period the period
    * @param contractNo the contract no
    * @throws SystemException if a system exception occurred
    */
    public void removeByBestPriceUnique(java.lang.String itemId,
        java.lang.String accountId, java.lang.String period,
        java.lang.String contractNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of best price masters where itemId = &#63; and accountId = &#63; and period = &#63; and contractNo = &#63;.
    *
    * @param itemId the item ID
    * @param accountId the account ID
    * @param period the period
    * @param contractNo the contract no
    * @return the number of matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public int countByBestPriceUnique(java.lang.String itemId,
        java.lang.String accountId, java.lang.String period,
        java.lang.String contractNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Caches the best price master in the entity cache if it is enabled.
    *
    * @param bestPriceMaster the best price master
    */
    public void cacheResult(com.stpl.app.model.BestPriceMaster bestPriceMaster);

    /**
    * Caches the best price masters in the entity cache if it is enabled.
    *
    * @param bestPriceMasters the best price masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.BestPriceMaster> bestPriceMasters);

    /**
    * Creates a new best price master with the primary key. Does not add the best price master to the database.
    *
    * @param bestPriceMasterSid the primary key for the new best price master
    * @return the new best price master
    */
    public com.stpl.app.model.BestPriceMaster create(int bestPriceMasterSid);

    /**
    * Removes the best price master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param bestPriceMasterSid the primary key of the best price master
    * @return the best price master that was removed
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a best price master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BestPriceMaster remove(int bestPriceMasterSid)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.BestPriceMaster updateImpl(
        com.stpl.app.model.BestPriceMaster bestPriceMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the best price master with the primary key or throws a {@link com.stpl.app.NoSuchBestPriceMasterException} if it could not be found.
    *
    * @param bestPriceMasterSid the primary key of the best price master
    * @return the best price master
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a best price master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BestPriceMaster findByPrimaryKey(
        int bestPriceMasterSid)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the best price master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param bestPriceMasterSid the primary key of the best price master
    * @return the best price master, or <code>null</code> if a best price master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BestPriceMaster fetchByPrimaryKey(
        int bestPriceMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the best price masters.
    *
    * @return the best price masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.BestPriceMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the best price masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of best price masters
    * @param end the upper bound of the range of best price masters (not inclusive)
    * @return the range of best price masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.BestPriceMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the best price masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of best price masters
    * @param end the upper bound of the range of best price masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of best price masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.BestPriceMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the best price masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of best price masters.
    *
    * @return the number of best price masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
