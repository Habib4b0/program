package com.stpl.app.service.persistence;

import com.stpl.app.model.ActualsMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the actuals master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ActualsMasterPersistenceImpl
 * @see ActualsMasterUtil
 * @generated
 */
public interface ActualsMasterPersistence extends BasePersistence<ActualsMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ActualsMasterUtil} to access the actuals master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the actuals masters where accountId = &#63;.
    *
    * @param accountId the account ID
    * @return the matching actuals masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ActualsMaster> findByAccountId(
        java.lang.String accountId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the actuals masters where accountId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param accountId the account ID
    * @param start the lower bound of the range of actuals masters
    * @param end the upper bound of the range of actuals masters (not inclusive)
    * @return the range of matching actuals masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ActualsMaster> findByAccountId(
        java.lang.String accountId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the actuals masters where accountId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param accountId the account ID
    * @param start the lower bound of the range of actuals masters
    * @param end the upper bound of the range of actuals masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching actuals masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ActualsMaster> findByAccountId(
        java.lang.String accountId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first actuals master in the ordered set where accountId = &#63;.
    *
    * @param accountId the account ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching actuals master
    * @throws com.stpl.app.NoSuchActualsMasterException if a matching actuals master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster findByAccountId_First(
        java.lang.String accountId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchActualsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first actuals master in the ordered set where accountId = &#63;.
    *
    * @param accountId the account ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster fetchByAccountId_First(
        java.lang.String accountId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last actuals master in the ordered set where accountId = &#63;.
    *
    * @param accountId the account ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching actuals master
    * @throws com.stpl.app.NoSuchActualsMasterException if a matching actuals master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster findByAccountId_Last(
        java.lang.String accountId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchActualsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last actuals master in the ordered set where accountId = &#63;.
    *
    * @param accountId the account ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster fetchByAccountId_Last(
        java.lang.String accountId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the actuals masters before and after the current actuals master in the ordered set where accountId = &#63;.
    *
    * @param actualsMasterSid the primary key of the current actuals master
    * @param accountId the account ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next actuals master
    * @throws com.stpl.app.NoSuchActualsMasterException if a actuals master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster[] findByAccountId_PrevAndNext(
        int actualsMasterSid, java.lang.String accountId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchActualsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the actuals masters where accountId = &#63; from the database.
    *
    * @param accountId the account ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByAccountId(java.lang.String accountId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of actuals masters where accountId = &#63;.
    *
    * @param accountId the account ID
    * @return the number of matching actuals masters
    * @throws SystemException if a system exception occurred
    */
    public int countByAccountId(java.lang.String accountId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the actuals masters where actualId = &#63;.
    *
    * @param actualId the actual ID
    * @return the matching actuals masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ActualsMaster> findByActualId(
        java.lang.String actualId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the actuals masters where actualId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param actualId the actual ID
    * @param start the lower bound of the range of actuals masters
    * @param end the upper bound of the range of actuals masters (not inclusive)
    * @return the range of matching actuals masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ActualsMaster> findByActualId(
        java.lang.String actualId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the actuals masters where actualId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param actualId the actual ID
    * @param start the lower bound of the range of actuals masters
    * @param end the upper bound of the range of actuals masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching actuals masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ActualsMaster> findByActualId(
        java.lang.String actualId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first actuals master in the ordered set where actualId = &#63;.
    *
    * @param actualId the actual ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching actuals master
    * @throws com.stpl.app.NoSuchActualsMasterException if a matching actuals master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster findByActualId_First(
        java.lang.String actualId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchActualsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first actuals master in the ordered set where actualId = &#63;.
    *
    * @param actualId the actual ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster fetchByActualId_First(
        java.lang.String actualId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last actuals master in the ordered set where actualId = &#63;.
    *
    * @param actualId the actual ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching actuals master
    * @throws com.stpl.app.NoSuchActualsMasterException if a matching actuals master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster findByActualId_Last(
        java.lang.String actualId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchActualsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last actuals master in the ordered set where actualId = &#63;.
    *
    * @param actualId the actual ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster fetchByActualId_Last(
        java.lang.String actualId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the actuals masters before and after the current actuals master in the ordered set where actualId = &#63;.
    *
    * @param actualsMasterSid the primary key of the current actuals master
    * @param actualId the actual ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next actuals master
    * @throws com.stpl.app.NoSuchActualsMasterException if a actuals master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster[] findByActualId_PrevAndNext(
        int actualsMasterSid, java.lang.String actualId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchActualsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the actuals masters where actualId = &#63; from the database.
    *
    * @param actualId the actual ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByActualId(java.lang.String actualId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of actuals masters where actualId = &#63;.
    *
    * @param actualId the actual ID
    * @return the number of matching actuals masters
    * @throws SystemException if a system exception occurred
    */
    public int countByActualId(java.lang.String actualId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the actuals masters where divisionId = &#63;.
    *
    * @param divisionId the division ID
    * @return the matching actuals masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ActualsMaster> findByDivisionId(
        java.lang.String divisionId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the actuals masters where divisionId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param divisionId the division ID
    * @param start the lower bound of the range of actuals masters
    * @param end the upper bound of the range of actuals masters (not inclusive)
    * @return the range of matching actuals masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ActualsMaster> findByDivisionId(
        java.lang.String divisionId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the actuals masters where divisionId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param divisionId the division ID
    * @param start the lower bound of the range of actuals masters
    * @param end the upper bound of the range of actuals masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching actuals masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ActualsMaster> findByDivisionId(
        java.lang.String divisionId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first actuals master in the ordered set where divisionId = &#63;.
    *
    * @param divisionId the division ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching actuals master
    * @throws com.stpl.app.NoSuchActualsMasterException if a matching actuals master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster findByDivisionId_First(
        java.lang.String divisionId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchActualsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first actuals master in the ordered set where divisionId = &#63;.
    *
    * @param divisionId the division ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster fetchByDivisionId_First(
        java.lang.String divisionId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last actuals master in the ordered set where divisionId = &#63;.
    *
    * @param divisionId the division ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching actuals master
    * @throws com.stpl.app.NoSuchActualsMasterException if a matching actuals master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster findByDivisionId_Last(
        java.lang.String divisionId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchActualsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last actuals master in the ordered set where divisionId = &#63;.
    *
    * @param divisionId the division ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster fetchByDivisionId_Last(
        java.lang.String divisionId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the actuals masters before and after the current actuals master in the ordered set where divisionId = &#63;.
    *
    * @param actualsMasterSid the primary key of the current actuals master
    * @param divisionId the division ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next actuals master
    * @throws com.stpl.app.NoSuchActualsMasterException if a actuals master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster[] findByDivisionId_PrevAndNext(
        int actualsMasterSid, java.lang.String divisionId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchActualsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the actuals masters where divisionId = &#63; from the database.
    *
    * @param divisionId the division ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByDivisionId(java.lang.String divisionId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of actuals masters where divisionId = &#63;.
    *
    * @param divisionId the division ID
    * @return the number of matching actuals masters
    * @throws SystemException if a system exception occurred
    */
    public int countByDivisionId(java.lang.String divisionId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the actuals masters where contractId = &#63;.
    *
    * @param contractId the contract ID
    * @return the matching actuals masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ActualsMaster> findByContractId(
        java.lang.String contractId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the actuals masters where contractId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contractId the contract ID
    * @param start the lower bound of the range of actuals masters
    * @param end the upper bound of the range of actuals masters (not inclusive)
    * @return the range of matching actuals masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ActualsMaster> findByContractId(
        java.lang.String contractId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the actuals masters where contractId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contractId the contract ID
    * @param start the lower bound of the range of actuals masters
    * @param end the upper bound of the range of actuals masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching actuals masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ActualsMaster> findByContractId(
        java.lang.String contractId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first actuals master in the ordered set where contractId = &#63;.
    *
    * @param contractId the contract ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching actuals master
    * @throws com.stpl.app.NoSuchActualsMasterException if a matching actuals master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster findByContractId_First(
        java.lang.String contractId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchActualsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first actuals master in the ordered set where contractId = &#63;.
    *
    * @param contractId the contract ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster fetchByContractId_First(
        java.lang.String contractId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last actuals master in the ordered set where contractId = &#63;.
    *
    * @param contractId the contract ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching actuals master
    * @throws com.stpl.app.NoSuchActualsMasterException if a matching actuals master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster findByContractId_Last(
        java.lang.String contractId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchActualsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last actuals master in the ordered set where contractId = &#63;.
    *
    * @param contractId the contract ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster fetchByContractId_Last(
        java.lang.String contractId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the actuals masters before and after the current actuals master in the ordered set where contractId = &#63;.
    *
    * @param actualsMasterSid the primary key of the current actuals master
    * @param contractId the contract ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next actuals master
    * @throws com.stpl.app.NoSuchActualsMasterException if a actuals master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster[] findByContractId_PrevAndNext(
        int actualsMasterSid, java.lang.String contractId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchActualsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the actuals masters where contractId = &#63; from the database.
    *
    * @param contractId the contract ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByContractId(java.lang.String contractId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of actuals masters where contractId = &#63;.
    *
    * @param contractId the contract ID
    * @return the number of matching actuals masters
    * @throws SystemException if a system exception occurred
    */
    public int countByContractId(java.lang.String contractId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the actuals masters where provisionId = &#63;.
    *
    * @param provisionId the provision ID
    * @return the matching actuals masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ActualsMaster> findByProvisionId(
        java.lang.String provisionId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the actuals masters where provisionId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param provisionId the provision ID
    * @param start the lower bound of the range of actuals masters
    * @param end the upper bound of the range of actuals masters (not inclusive)
    * @return the range of matching actuals masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ActualsMaster> findByProvisionId(
        java.lang.String provisionId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the actuals masters where provisionId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param provisionId the provision ID
    * @param start the lower bound of the range of actuals masters
    * @param end the upper bound of the range of actuals masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching actuals masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ActualsMaster> findByProvisionId(
        java.lang.String provisionId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first actuals master in the ordered set where provisionId = &#63;.
    *
    * @param provisionId the provision ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching actuals master
    * @throws com.stpl.app.NoSuchActualsMasterException if a matching actuals master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster findByProvisionId_First(
        java.lang.String provisionId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchActualsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first actuals master in the ordered set where provisionId = &#63;.
    *
    * @param provisionId the provision ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster fetchByProvisionId_First(
        java.lang.String provisionId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last actuals master in the ordered set where provisionId = &#63;.
    *
    * @param provisionId the provision ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching actuals master
    * @throws com.stpl.app.NoSuchActualsMasterException if a matching actuals master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster findByProvisionId_Last(
        java.lang.String provisionId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchActualsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last actuals master in the ordered set where provisionId = &#63;.
    *
    * @param provisionId the provision ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster fetchByProvisionId_Last(
        java.lang.String provisionId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the actuals masters before and after the current actuals master in the ordered set where provisionId = &#63;.
    *
    * @param actualsMasterSid the primary key of the current actuals master
    * @param provisionId the provision ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next actuals master
    * @throws com.stpl.app.NoSuchActualsMasterException if a actuals master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster[] findByProvisionId_PrevAndNext(
        int actualsMasterSid, java.lang.String provisionId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchActualsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the actuals masters where provisionId = &#63; from the database.
    *
    * @param provisionId the provision ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByProvisionId(java.lang.String provisionId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of actuals masters where provisionId = &#63;.
    *
    * @param provisionId the provision ID
    * @return the number of matching actuals masters
    * @throws SystemException if a system exception occurred
    */
    public int countByProvisionId(java.lang.String provisionId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the actuals masters where itemId = &#63;.
    *
    * @param itemId the item ID
    * @return the matching actuals masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ActualsMaster> findByItemId(
        java.lang.String itemId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the actuals masters where itemId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemId the item ID
    * @param start the lower bound of the range of actuals masters
    * @param end the upper bound of the range of actuals masters (not inclusive)
    * @return the range of matching actuals masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ActualsMaster> findByItemId(
        java.lang.String itemId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the actuals masters where itemId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemId the item ID
    * @param start the lower bound of the range of actuals masters
    * @param end the upper bound of the range of actuals masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching actuals masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ActualsMaster> findByItemId(
        java.lang.String itemId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first actuals master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching actuals master
    * @throws com.stpl.app.NoSuchActualsMasterException if a matching actuals master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster findByItemId_First(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchActualsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first actuals master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster fetchByItemId_First(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last actuals master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching actuals master
    * @throws com.stpl.app.NoSuchActualsMasterException if a matching actuals master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster findByItemId_Last(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchActualsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last actuals master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster fetchByItemId_Last(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the actuals masters before and after the current actuals master in the ordered set where itemId = &#63;.
    *
    * @param actualsMasterSid the primary key of the current actuals master
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next actuals master
    * @throws com.stpl.app.NoSuchActualsMasterException if a actuals master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster[] findByItemId_PrevAndNext(
        int actualsMasterSid, java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchActualsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the actuals masters where itemId = &#63; from the database.
    *
    * @param itemId the item ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByItemId(java.lang.String itemId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of actuals masters where itemId = &#63;.
    *
    * @param itemId the item ID
    * @return the number of matching actuals masters
    * @throws SystemException if a system exception occurred
    */
    public int countByItemId(java.lang.String itemId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the actuals masters where itemNo = &#63;.
    *
    * @param itemNo the item no
    * @return the matching actuals masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ActualsMaster> findByItemNo(
        java.lang.String itemNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the actuals masters where itemNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemNo the item no
    * @param start the lower bound of the range of actuals masters
    * @param end the upper bound of the range of actuals masters (not inclusive)
    * @return the range of matching actuals masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ActualsMaster> findByItemNo(
        java.lang.String itemNo, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the actuals masters where itemNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemNo the item no
    * @param start the lower bound of the range of actuals masters
    * @param end the upper bound of the range of actuals masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching actuals masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ActualsMaster> findByItemNo(
        java.lang.String itemNo, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first actuals master in the ordered set where itemNo = &#63;.
    *
    * @param itemNo the item no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching actuals master
    * @throws com.stpl.app.NoSuchActualsMasterException if a matching actuals master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster findByItemNo_First(
        java.lang.String itemNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchActualsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first actuals master in the ordered set where itemNo = &#63;.
    *
    * @param itemNo the item no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster fetchByItemNo_First(
        java.lang.String itemNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last actuals master in the ordered set where itemNo = &#63;.
    *
    * @param itemNo the item no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching actuals master
    * @throws com.stpl.app.NoSuchActualsMasterException if a matching actuals master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster findByItemNo_Last(
        java.lang.String itemNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchActualsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last actuals master in the ordered set where itemNo = &#63;.
    *
    * @param itemNo the item no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster fetchByItemNo_Last(
        java.lang.String itemNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the actuals masters before and after the current actuals master in the ordered set where itemNo = &#63;.
    *
    * @param actualsMasterSid the primary key of the current actuals master
    * @param itemNo the item no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next actuals master
    * @throws com.stpl.app.NoSuchActualsMasterException if a actuals master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster[] findByItemNo_PrevAndNext(
        int actualsMasterSid, java.lang.String itemNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchActualsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the actuals masters where itemNo = &#63; from the database.
    *
    * @param itemNo the item no
    * @throws SystemException if a system exception occurred
    */
    public void removeByItemNo(java.lang.String itemNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of actuals masters where itemNo = &#63;.
    *
    * @param itemNo the item no
    * @return the number of matching actuals masters
    * @throws SystemException if a system exception occurred
    */
    public int countByItemNo(java.lang.String itemNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the actuals masters where accountNo = &#63;.
    *
    * @param accountNo the account no
    * @return the matching actuals masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ActualsMaster> findByAccountNo(
        java.lang.String accountNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the actuals masters where accountNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param accountNo the account no
    * @param start the lower bound of the range of actuals masters
    * @param end the upper bound of the range of actuals masters (not inclusive)
    * @return the range of matching actuals masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ActualsMaster> findByAccountNo(
        java.lang.String accountNo, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the actuals masters where accountNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param accountNo the account no
    * @param start the lower bound of the range of actuals masters
    * @param end the upper bound of the range of actuals masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching actuals masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ActualsMaster> findByAccountNo(
        java.lang.String accountNo, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first actuals master in the ordered set where accountNo = &#63;.
    *
    * @param accountNo the account no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching actuals master
    * @throws com.stpl.app.NoSuchActualsMasterException if a matching actuals master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster findByAccountNo_First(
        java.lang.String accountNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchActualsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first actuals master in the ordered set where accountNo = &#63;.
    *
    * @param accountNo the account no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster fetchByAccountNo_First(
        java.lang.String accountNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last actuals master in the ordered set where accountNo = &#63;.
    *
    * @param accountNo the account no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching actuals master
    * @throws com.stpl.app.NoSuchActualsMasterException if a matching actuals master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster findByAccountNo_Last(
        java.lang.String accountNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchActualsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last actuals master in the ordered set where accountNo = &#63;.
    *
    * @param accountNo the account no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster fetchByAccountNo_Last(
        java.lang.String accountNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the actuals masters before and after the current actuals master in the ordered set where accountNo = &#63;.
    *
    * @param actualsMasterSid the primary key of the current actuals master
    * @param accountNo the account no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next actuals master
    * @throws com.stpl.app.NoSuchActualsMasterException if a actuals master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster[] findByAccountNo_PrevAndNext(
        int actualsMasterSid, java.lang.String accountNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchActualsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the actuals masters where accountNo = &#63; from the database.
    *
    * @param accountNo the account no
    * @throws SystemException if a system exception occurred
    */
    public void removeByAccountNo(java.lang.String accountNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of actuals masters where accountNo = &#63;.
    *
    * @param accountNo the account no
    * @return the number of matching actuals masters
    * @throws SystemException if a system exception occurred
    */
    public int countByAccountNo(java.lang.String accountNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the actuals masters where marketId = &#63;.
    *
    * @param marketId the market ID
    * @return the matching actuals masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ActualsMaster> findByMarketId(
        java.lang.String marketId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the actuals masters where marketId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param marketId the market ID
    * @param start the lower bound of the range of actuals masters
    * @param end the upper bound of the range of actuals masters (not inclusive)
    * @return the range of matching actuals masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ActualsMaster> findByMarketId(
        java.lang.String marketId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the actuals masters where marketId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param marketId the market ID
    * @param start the lower bound of the range of actuals masters
    * @param end the upper bound of the range of actuals masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching actuals masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ActualsMaster> findByMarketId(
        java.lang.String marketId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first actuals master in the ordered set where marketId = &#63;.
    *
    * @param marketId the market ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching actuals master
    * @throws com.stpl.app.NoSuchActualsMasterException if a matching actuals master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster findByMarketId_First(
        java.lang.String marketId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchActualsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first actuals master in the ordered set where marketId = &#63;.
    *
    * @param marketId the market ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster fetchByMarketId_First(
        java.lang.String marketId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last actuals master in the ordered set where marketId = &#63;.
    *
    * @param marketId the market ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching actuals master
    * @throws com.stpl.app.NoSuchActualsMasterException if a matching actuals master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster findByMarketId_Last(
        java.lang.String marketId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchActualsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last actuals master in the ordered set where marketId = &#63;.
    *
    * @param marketId the market ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster fetchByMarketId_Last(
        java.lang.String marketId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the actuals masters before and after the current actuals master in the ordered set where marketId = &#63;.
    *
    * @param actualsMasterSid the primary key of the current actuals master
    * @param marketId the market ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next actuals master
    * @throws com.stpl.app.NoSuchActualsMasterException if a actuals master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster[] findByMarketId_PrevAndNext(
        int actualsMasterSid, java.lang.String marketId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchActualsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the actuals masters where marketId = &#63; from the database.
    *
    * @param marketId the market ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByMarketId(java.lang.String marketId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of actuals masters where marketId = &#63;.
    *
    * @param marketId the market ID
    * @return the number of matching actuals masters
    * @throws SystemException if a system exception occurred
    */
    public int countByMarketId(java.lang.String marketId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the actuals masters where brandId = &#63;.
    *
    * @param brandId the brand ID
    * @return the matching actuals masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ActualsMaster> findByBrandId(
        java.lang.String brandId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the actuals masters where brandId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param brandId the brand ID
    * @param start the lower bound of the range of actuals masters
    * @param end the upper bound of the range of actuals masters (not inclusive)
    * @return the range of matching actuals masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ActualsMaster> findByBrandId(
        java.lang.String brandId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the actuals masters where brandId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param brandId the brand ID
    * @param start the lower bound of the range of actuals masters
    * @param end the upper bound of the range of actuals masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching actuals masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ActualsMaster> findByBrandId(
        java.lang.String brandId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first actuals master in the ordered set where brandId = &#63;.
    *
    * @param brandId the brand ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching actuals master
    * @throws com.stpl.app.NoSuchActualsMasterException if a matching actuals master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster findByBrandId_First(
        java.lang.String brandId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchActualsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first actuals master in the ordered set where brandId = &#63;.
    *
    * @param brandId the brand ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster fetchByBrandId_First(
        java.lang.String brandId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last actuals master in the ordered set where brandId = &#63;.
    *
    * @param brandId the brand ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching actuals master
    * @throws com.stpl.app.NoSuchActualsMasterException if a matching actuals master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster findByBrandId_Last(
        java.lang.String brandId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchActualsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last actuals master in the ordered set where brandId = &#63;.
    *
    * @param brandId the brand ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster fetchByBrandId_Last(
        java.lang.String brandId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the actuals masters before and after the current actuals master in the ordered set where brandId = &#63;.
    *
    * @param actualsMasterSid the primary key of the current actuals master
    * @param brandId the brand ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next actuals master
    * @throws com.stpl.app.NoSuchActualsMasterException if a actuals master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster[] findByBrandId_PrevAndNext(
        int actualsMasterSid, java.lang.String brandId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchActualsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the actuals masters where brandId = &#63; from the database.
    *
    * @param brandId the brand ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByBrandId(java.lang.String brandId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of actuals masters where brandId = &#63;.
    *
    * @param brandId the brand ID
    * @return the number of matching actuals masters
    * @throws SystemException if a system exception occurred
    */
    public int countByBrandId(java.lang.String brandId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the actuals masters where actualId = &#63;.
    *
    * @param actualId the actual ID
    * @return the matching actuals masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ActualsMaster> findByActualsUnique(
        java.lang.String actualId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the actuals masters where actualId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param actualId the actual ID
    * @param start the lower bound of the range of actuals masters
    * @param end the upper bound of the range of actuals masters (not inclusive)
    * @return the range of matching actuals masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ActualsMaster> findByActualsUnique(
        java.lang.String actualId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the actuals masters where actualId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param actualId the actual ID
    * @param start the lower bound of the range of actuals masters
    * @param end the upper bound of the range of actuals masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching actuals masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ActualsMaster> findByActualsUnique(
        java.lang.String actualId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first actuals master in the ordered set where actualId = &#63;.
    *
    * @param actualId the actual ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching actuals master
    * @throws com.stpl.app.NoSuchActualsMasterException if a matching actuals master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster findByActualsUnique_First(
        java.lang.String actualId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchActualsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first actuals master in the ordered set where actualId = &#63;.
    *
    * @param actualId the actual ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster fetchByActualsUnique_First(
        java.lang.String actualId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last actuals master in the ordered set where actualId = &#63;.
    *
    * @param actualId the actual ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching actuals master
    * @throws com.stpl.app.NoSuchActualsMasterException if a matching actuals master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster findByActualsUnique_Last(
        java.lang.String actualId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchActualsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last actuals master in the ordered set where actualId = &#63;.
    *
    * @param actualId the actual ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster fetchByActualsUnique_Last(
        java.lang.String actualId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the actuals masters before and after the current actuals master in the ordered set where actualId = &#63;.
    *
    * @param actualsMasterSid the primary key of the current actuals master
    * @param actualId the actual ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next actuals master
    * @throws com.stpl.app.NoSuchActualsMasterException if a actuals master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster[] findByActualsUnique_PrevAndNext(
        int actualsMasterSid, java.lang.String actualId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchActualsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the actuals masters where actualId = &#63; from the database.
    *
    * @param actualId the actual ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByActualsUnique(java.lang.String actualId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of actuals masters where actualId = &#63;.
    *
    * @param actualId the actual ID
    * @return the number of matching actuals masters
    * @throws SystemException if a system exception occurred
    */
    public int countByActualsUnique(java.lang.String actualId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Caches the actuals master in the entity cache if it is enabled.
    *
    * @param actualsMaster the actuals master
    */
    public void cacheResult(com.stpl.app.model.ActualsMaster actualsMaster);

    /**
    * Caches the actuals masters in the entity cache if it is enabled.
    *
    * @param actualsMasters the actuals masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.ActualsMaster> actualsMasters);

    /**
    * Creates a new actuals master with the primary key. Does not add the actuals master to the database.
    *
    * @param actualsMasterSid the primary key for the new actuals master
    * @return the new actuals master
    */
    public com.stpl.app.model.ActualsMaster create(int actualsMasterSid);

    /**
    * Removes the actuals master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param actualsMasterSid the primary key of the actuals master
    * @return the actuals master that was removed
    * @throws com.stpl.app.NoSuchActualsMasterException if a actuals master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster remove(int actualsMasterSid)
        throws com.stpl.app.NoSuchActualsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.ActualsMaster updateImpl(
        com.stpl.app.model.ActualsMaster actualsMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the actuals master with the primary key or throws a {@link com.stpl.app.NoSuchActualsMasterException} if it could not be found.
    *
    * @param actualsMasterSid the primary key of the actuals master
    * @return the actuals master
    * @throws com.stpl.app.NoSuchActualsMasterException if a actuals master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster findByPrimaryKey(
        int actualsMasterSid)
        throws com.stpl.app.NoSuchActualsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the actuals master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param actualsMasterSid the primary key of the actuals master
    * @return the actuals master, or <code>null</code> if a actuals master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ActualsMaster fetchByPrimaryKey(
        int actualsMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the actuals masters.
    *
    * @return the actuals masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ActualsMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the actuals masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of actuals masters
    * @param end the upper bound of the range of actuals masters (not inclusive)
    * @return the range of actuals masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ActualsMaster> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the actuals masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of actuals masters
    * @param end the upper bound of the range of actuals masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of actuals masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ActualsMaster> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the actuals masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of actuals masters.
    *
    * @return the number of actuals masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
