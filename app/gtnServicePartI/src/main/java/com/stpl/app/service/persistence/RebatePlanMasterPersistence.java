package com.stpl.app.service.persistence;

import com.stpl.app.model.RebatePlanMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the rebate plan master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RebatePlanMasterPersistenceImpl
 * @see RebatePlanMasterUtil
 * @generated
 */
public interface RebatePlanMasterPersistence extends BasePersistence<RebatePlanMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link RebatePlanMasterUtil} to access the rebate plan master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the rebate plan masters where rebatePlanId = &#63;.
    *
    * @param rebatePlanId the rebate plan ID
    * @return the matching rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RebatePlanMaster> findByRebatePlanId(
        java.lang.String rebatePlanId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the rebate plan masters where rebatePlanId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rebatePlanId the rebate plan ID
    * @param start the lower bound of the range of rebate plan masters
    * @param end the upper bound of the range of rebate plan masters (not inclusive)
    * @return the range of matching rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RebatePlanMaster> findByRebatePlanId(
        java.lang.String rebatePlanId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the rebate plan masters where rebatePlanId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rebatePlanId the rebate plan ID
    * @param start the lower bound of the range of rebate plan masters
    * @param end the upper bound of the range of rebate plan masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RebatePlanMaster> findByRebatePlanId(
        java.lang.String rebatePlanId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first rebate plan master in the ordered set where rebatePlanId = &#63;.
    *
    * @param rebatePlanId the rebate plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rebate plan master
    * @throws com.stpl.app.NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RebatePlanMaster findByRebatePlanId_First(
        java.lang.String rebatePlanId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRebatePlanMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first rebate plan master in the ordered set where rebatePlanId = &#63;.
    *
    * @param rebatePlanId the rebate plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RebatePlanMaster fetchByRebatePlanId_First(
        java.lang.String rebatePlanId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last rebate plan master in the ordered set where rebatePlanId = &#63;.
    *
    * @param rebatePlanId the rebate plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rebate plan master
    * @throws com.stpl.app.NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RebatePlanMaster findByRebatePlanId_Last(
        java.lang.String rebatePlanId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRebatePlanMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last rebate plan master in the ordered set where rebatePlanId = &#63;.
    *
    * @param rebatePlanId the rebate plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RebatePlanMaster fetchByRebatePlanId_Last(
        java.lang.String rebatePlanId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the rebate plan masters before and after the current rebate plan master in the ordered set where rebatePlanId = &#63;.
    *
    * @param rebatePlanMasterSid the primary key of the current rebate plan master
    * @param rebatePlanId the rebate plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next rebate plan master
    * @throws com.stpl.app.NoSuchRebatePlanMasterException if a rebate plan master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RebatePlanMaster[] findByRebatePlanId_PrevAndNext(
        int rebatePlanMasterSid, java.lang.String rebatePlanId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRebatePlanMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the rebate plan masters where rebatePlanId = &#63; from the database.
    *
    * @param rebatePlanId the rebate plan ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByRebatePlanId(java.lang.String rebatePlanId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of rebate plan masters where rebatePlanId = &#63;.
    *
    * @param rebatePlanId the rebate plan ID
    * @return the number of matching rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public int countByRebatePlanId(java.lang.String rebatePlanId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the rebate plan masters where rebatePlanNo = &#63;.
    *
    * @param rebatePlanNo the rebate plan no
    * @return the matching rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RebatePlanMaster> findByRebatePlanNo(
        java.lang.String rebatePlanNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the rebate plan masters where rebatePlanNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rebatePlanNo the rebate plan no
    * @param start the lower bound of the range of rebate plan masters
    * @param end the upper bound of the range of rebate plan masters (not inclusive)
    * @return the range of matching rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RebatePlanMaster> findByRebatePlanNo(
        java.lang.String rebatePlanNo, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the rebate plan masters where rebatePlanNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rebatePlanNo the rebate plan no
    * @param start the lower bound of the range of rebate plan masters
    * @param end the upper bound of the range of rebate plan masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RebatePlanMaster> findByRebatePlanNo(
        java.lang.String rebatePlanNo, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first rebate plan master in the ordered set where rebatePlanNo = &#63;.
    *
    * @param rebatePlanNo the rebate plan no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rebate plan master
    * @throws com.stpl.app.NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RebatePlanMaster findByRebatePlanNo_First(
        java.lang.String rebatePlanNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRebatePlanMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first rebate plan master in the ordered set where rebatePlanNo = &#63;.
    *
    * @param rebatePlanNo the rebate plan no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RebatePlanMaster fetchByRebatePlanNo_First(
        java.lang.String rebatePlanNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last rebate plan master in the ordered set where rebatePlanNo = &#63;.
    *
    * @param rebatePlanNo the rebate plan no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rebate plan master
    * @throws com.stpl.app.NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RebatePlanMaster findByRebatePlanNo_Last(
        java.lang.String rebatePlanNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRebatePlanMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last rebate plan master in the ordered set where rebatePlanNo = &#63;.
    *
    * @param rebatePlanNo the rebate plan no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RebatePlanMaster fetchByRebatePlanNo_Last(
        java.lang.String rebatePlanNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the rebate plan masters before and after the current rebate plan master in the ordered set where rebatePlanNo = &#63;.
    *
    * @param rebatePlanMasterSid the primary key of the current rebate plan master
    * @param rebatePlanNo the rebate plan no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next rebate plan master
    * @throws com.stpl.app.NoSuchRebatePlanMasterException if a rebate plan master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RebatePlanMaster[] findByRebatePlanNo_PrevAndNext(
        int rebatePlanMasterSid, java.lang.String rebatePlanNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRebatePlanMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the rebate plan masters where rebatePlanNo = &#63; from the database.
    *
    * @param rebatePlanNo the rebate plan no
    * @throws SystemException if a system exception occurred
    */
    public void removeByRebatePlanNo(java.lang.String rebatePlanNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of rebate plan masters where rebatePlanNo = &#63;.
    *
    * @param rebatePlanNo the rebate plan no
    * @return the number of matching rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public int countByRebatePlanNo(java.lang.String rebatePlanNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the rebate plan masters where rebatePlanName = &#63;.
    *
    * @param rebatePlanName the rebate plan name
    * @return the matching rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RebatePlanMaster> findByRebatePlanName(
        java.lang.String rebatePlanName)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the rebate plan masters where rebatePlanName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rebatePlanName the rebate plan name
    * @param start the lower bound of the range of rebate plan masters
    * @param end the upper bound of the range of rebate plan masters (not inclusive)
    * @return the range of matching rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RebatePlanMaster> findByRebatePlanName(
        java.lang.String rebatePlanName, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the rebate plan masters where rebatePlanName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rebatePlanName the rebate plan name
    * @param start the lower bound of the range of rebate plan masters
    * @param end the upper bound of the range of rebate plan masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RebatePlanMaster> findByRebatePlanName(
        java.lang.String rebatePlanName, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first rebate plan master in the ordered set where rebatePlanName = &#63;.
    *
    * @param rebatePlanName the rebate plan name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rebate plan master
    * @throws com.stpl.app.NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RebatePlanMaster findByRebatePlanName_First(
        java.lang.String rebatePlanName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRebatePlanMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first rebate plan master in the ordered set where rebatePlanName = &#63;.
    *
    * @param rebatePlanName the rebate plan name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RebatePlanMaster fetchByRebatePlanName_First(
        java.lang.String rebatePlanName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last rebate plan master in the ordered set where rebatePlanName = &#63;.
    *
    * @param rebatePlanName the rebate plan name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rebate plan master
    * @throws com.stpl.app.NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RebatePlanMaster findByRebatePlanName_Last(
        java.lang.String rebatePlanName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRebatePlanMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last rebate plan master in the ordered set where rebatePlanName = &#63;.
    *
    * @param rebatePlanName the rebate plan name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RebatePlanMaster fetchByRebatePlanName_Last(
        java.lang.String rebatePlanName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the rebate plan masters before and after the current rebate plan master in the ordered set where rebatePlanName = &#63;.
    *
    * @param rebatePlanMasterSid the primary key of the current rebate plan master
    * @param rebatePlanName the rebate plan name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next rebate plan master
    * @throws com.stpl.app.NoSuchRebatePlanMasterException if a rebate plan master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RebatePlanMaster[] findByRebatePlanName_PrevAndNext(
        int rebatePlanMasterSid, java.lang.String rebatePlanName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRebatePlanMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the rebate plan masters where rebatePlanName = &#63; from the database.
    *
    * @param rebatePlanName the rebate plan name
    * @throws SystemException if a system exception occurred
    */
    public void removeByRebatePlanName(java.lang.String rebatePlanName)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of rebate plan masters where rebatePlanName = &#63;.
    *
    * @param rebatePlanName the rebate plan name
    * @return the number of matching rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public int countByRebatePlanName(java.lang.String rebatePlanName)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the rebate plan masters where rebatePlanStatus = &#63;.
    *
    * @param rebatePlanStatus the rebate plan status
    * @return the matching rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RebatePlanMaster> findByRebatePlanStatus(
        int rebatePlanStatus)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the rebate plan masters where rebatePlanStatus = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rebatePlanStatus the rebate plan status
    * @param start the lower bound of the range of rebate plan masters
    * @param end the upper bound of the range of rebate plan masters (not inclusive)
    * @return the range of matching rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RebatePlanMaster> findByRebatePlanStatus(
        int rebatePlanStatus, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the rebate plan masters where rebatePlanStatus = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rebatePlanStatus the rebate plan status
    * @param start the lower bound of the range of rebate plan masters
    * @param end the upper bound of the range of rebate plan masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RebatePlanMaster> findByRebatePlanStatus(
        int rebatePlanStatus, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first rebate plan master in the ordered set where rebatePlanStatus = &#63;.
    *
    * @param rebatePlanStatus the rebate plan status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rebate plan master
    * @throws com.stpl.app.NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RebatePlanMaster findByRebatePlanStatus_First(
        int rebatePlanStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRebatePlanMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first rebate plan master in the ordered set where rebatePlanStatus = &#63;.
    *
    * @param rebatePlanStatus the rebate plan status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RebatePlanMaster fetchByRebatePlanStatus_First(
        int rebatePlanStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last rebate plan master in the ordered set where rebatePlanStatus = &#63;.
    *
    * @param rebatePlanStatus the rebate plan status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rebate plan master
    * @throws com.stpl.app.NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RebatePlanMaster findByRebatePlanStatus_Last(
        int rebatePlanStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRebatePlanMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last rebate plan master in the ordered set where rebatePlanStatus = &#63;.
    *
    * @param rebatePlanStatus the rebate plan status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RebatePlanMaster fetchByRebatePlanStatus_Last(
        int rebatePlanStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the rebate plan masters before and after the current rebate plan master in the ordered set where rebatePlanStatus = &#63;.
    *
    * @param rebatePlanMasterSid the primary key of the current rebate plan master
    * @param rebatePlanStatus the rebate plan status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next rebate plan master
    * @throws com.stpl.app.NoSuchRebatePlanMasterException if a rebate plan master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RebatePlanMaster[] findByRebatePlanStatus_PrevAndNext(
        int rebatePlanMasterSid, int rebatePlanStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRebatePlanMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the rebate plan masters where rebatePlanStatus = &#63; from the database.
    *
    * @param rebatePlanStatus the rebate plan status
    * @throws SystemException if a system exception occurred
    */
    public void removeByRebatePlanStatus(int rebatePlanStatus)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of rebate plan masters where rebatePlanStatus = &#63;.
    *
    * @param rebatePlanStatus the rebate plan status
    * @return the number of matching rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public int countByRebatePlanStatus(int rebatePlanStatus)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the rebate plan masters where rebatePlanType = &#63;.
    *
    * @param rebatePlanType the rebate plan type
    * @return the matching rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RebatePlanMaster> findByRebatePlanType(
        int rebatePlanType)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the rebate plan masters where rebatePlanType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rebatePlanType the rebate plan type
    * @param start the lower bound of the range of rebate plan masters
    * @param end the upper bound of the range of rebate plan masters (not inclusive)
    * @return the range of matching rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RebatePlanMaster> findByRebatePlanType(
        int rebatePlanType, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the rebate plan masters where rebatePlanType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rebatePlanType the rebate plan type
    * @param start the lower bound of the range of rebate plan masters
    * @param end the upper bound of the range of rebate plan masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RebatePlanMaster> findByRebatePlanType(
        int rebatePlanType, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first rebate plan master in the ordered set where rebatePlanType = &#63;.
    *
    * @param rebatePlanType the rebate plan type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rebate plan master
    * @throws com.stpl.app.NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RebatePlanMaster findByRebatePlanType_First(
        int rebatePlanType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRebatePlanMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first rebate plan master in the ordered set where rebatePlanType = &#63;.
    *
    * @param rebatePlanType the rebate plan type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RebatePlanMaster fetchByRebatePlanType_First(
        int rebatePlanType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last rebate plan master in the ordered set where rebatePlanType = &#63;.
    *
    * @param rebatePlanType the rebate plan type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rebate plan master
    * @throws com.stpl.app.NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RebatePlanMaster findByRebatePlanType_Last(
        int rebatePlanType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRebatePlanMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last rebate plan master in the ordered set where rebatePlanType = &#63;.
    *
    * @param rebatePlanType the rebate plan type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RebatePlanMaster fetchByRebatePlanType_Last(
        int rebatePlanType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the rebate plan masters before and after the current rebate plan master in the ordered set where rebatePlanType = &#63;.
    *
    * @param rebatePlanMasterSid the primary key of the current rebate plan master
    * @param rebatePlanType the rebate plan type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next rebate plan master
    * @throws com.stpl.app.NoSuchRebatePlanMasterException if a rebate plan master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RebatePlanMaster[] findByRebatePlanType_PrevAndNext(
        int rebatePlanMasterSid, int rebatePlanType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRebatePlanMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the rebate plan masters where rebatePlanType = &#63; from the database.
    *
    * @param rebatePlanType the rebate plan type
    * @throws SystemException if a system exception occurred
    */
    public void removeByRebatePlanType(int rebatePlanType)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of rebate plan masters where rebatePlanType = &#63;.
    *
    * @param rebatePlanType the rebate plan type
    * @return the number of matching rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public int countByRebatePlanType(int rebatePlanType)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Caches the rebate plan master in the entity cache if it is enabled.
    *
    * @param rebatePlanMaster the rebate plan master
    */
    public void cacheResult(
        com.stpl.app.model.RebatePlanMaster rebatePlanMaster);

    /**
    * Caches the rebate plan masters in the entity cache if it is enabled.
    *
    * @param rebatePlanMasters the rebate plan masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.RebatePlanMaster> rebatePlanMasters);

    /**
    * Creates a new rebate plan master with the primary key. Does not add the rebate plan master to the database.
    *
    * @param rebatePlanMasterSid the primary key for the new rebate plan master
    * @return the new rebate plan master
    */
    public com.stpl.app.model.RebatePlanMaster create(int rebatePlanMasterSid);

    /**
    * Removes the rebate plan master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param rebatePlanMasterSid the primary key of the rebate plan master
    * @return the rebate plan master that was removed
    * @throws com.stpl.app.NoSuchRebatePlanMasterException if a rebate plan master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RebatePlanMaster remove(int rebatePlanMasterSid)
        throws com.stpl.app.NoSuchRebatePlanMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.RebatePlanMaster updateImpl(
        com.stpl.app.model.RebatePlanMaster rebatePlanMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the rebate plan master with the primary key or throws a {@link com.stpl.app.NoSuchRebatePlanMasterException} if it could not be found.
    *
    * @param rebatePlanMasterSid the primary key of the rebate plan master
    * @return the rebate plan master
    * @throws com.stpl.app.NoSuchRebatePlanMasterException if a rebate plan master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RebatePlanMaster findByPrimaryKey(
        int rebatePlanMasterSid)
        throws com.stpl.app.NoSuchRebatePlanMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the rebate plan master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param rebatePlanMasterSid the primary key of the rebate plan master
    * @return the rebate plan master, or <code>null</code> if a rebate plan master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RebatePlanMaster fetchByPrimaryKey(
        int rebatePlanMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the rebate plan masters.
    *
    * @return the rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RebatePlanMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the rebate plan masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of rebate plan masters
    * @param end the upper bound of the range of rebate plan masters (not inclusive)
    * @return the range of rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RebatePlanMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the rebate plan masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of rebate plan masters
    * @param end the upper bound of the range of rebate plan masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RebatePlanMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the rebate plan masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of rebate plan masters.
    *
    * @return the number of rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
