package com.stpl.app.service.persistence;

import com.stpl.app.model.GlBalanceMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the gl balance master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see GlBalanceMasterPersistenceImpl
 * @see GlBalanceMasterUtil
 * @generated
 */
public interface GlBalanceMasterPersistence extends BasePersistence<GlBalanceMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link GlBalanceMasterUtil} to access the gl balance master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the gl balance masters where accountNo = &#63;.
    *
    * @param accountNo the account no
    * @return the matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GlBalanceMaster> findByAccountNo(
        java.lang.String accountNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the gl balance masters where accountNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param accountNo the account no
    * @param start the lower bound of the range of gl balance masters
    * @param end the upper bound of the range of gl balance masters (not inclusive)
    * @return the range of matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GlBalanceMaster> findByAccountNo(
        java.lang.String accountNo, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the gl balance masters where accountNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param accountNo the account no
    * @param start the lower bound of the range of gl balance masters
    * @param end the upper bound of the range of gl balance masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GlBalanceMaster> findByAccountNo(
        java.lang.String accountNo, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first gl balance master in the ordered set where accountNo = &#63;.
    *
    * @param accountNo the account no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching gl balance master
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlBalanceMaster findByAccountNo_First(
        java.lang.String accountNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first gl balance master in the ordered set where accountNo = &#63;.
    *
    * @param accountNo the account no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlBalanceMaster fetchByAccountNo_First(
        java.lang.String accountNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last gl balance master in the ordered set where accountNo = &#63;.
    *
    * @param accountNo the account no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching gl balance master
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlBalanceMaster findByAccountNo_Last(
        java.lang.String accountNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last gl balance master in the ordered set where accountNo = &#63;.
    *
    * @param accountNo the account no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlBalanceMaster fetchByAccountNo_Last(
        java.lang.String accountNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the gl balance masters before and after the current gl balance master in the ordered set where accountNo = &#63;.
    *
    * @param glBalanceMasterSid the primary key of the current gl balance master
    * @param accountNo the account no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next gl balance master
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlBalanceMaster[] findByAccountNo_PrevAndNext(
        int glBalanceMasterSid, java.lang.String accountNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the gl balance masters where accountNo = &#63; from the database.
    *
    * @param accountNo the account no
    * @throws SystemException if a system exception occurred
    */
    public void removeByAccountNo(java.lang.String accountNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of gl balance masters where accountNo = &#63;.
    *
    * @param accountNo the account no
    * @return the number of matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public int countByAccountNo(java.lang.String accountNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the gl balance masters where isActive = &#63;.
    *
    * @param isActive the is active
    * @return the matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GlBalanceMaster> findByIsActive(
        java.lang.String isActive)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the gl balance masters where isActive = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param isActive the is active
    * @param start the lower bound of the range of gl balance masters
    * @param end the upper bound of the range of gl balance masters (not inclusive)
    * @return the range of matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GlBalanceMaster> findByIsActive(
        java.lang.String isActive, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the gl balance masters where isActive = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param isActive the is active
    * @param start the lower bound of the range of gl balance masters
    * @param end the upper bound of the range of gl balance masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GlBalanceMaster> findByIsActive(
        java.lang.String isActive, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first gl balance master in the ordered set where isActive = &#63;.
    *
    * @param isActive the is active
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching gl balance master
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlBalanceMaster findByIsActive_First(
        java.lang.String isActive,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first gl balance master in the ordered set where isActive = &#63;.
    *
    * @param isActive the is active
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlBalanceMaster fetchByIsActive_First(
        java.lang.String isActive,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last gl balance master in the ordered set where isActive = &#63;.
    *
    * @param isActive the is active
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching gl balance master
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlBalanceMaster findByIsActive_Last(
        java.lang.String isActive,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last gl balance master in the ordered set where isActive = &#63;.
    *
    * @param isActive the is active
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlBalanceMaster fetchByIsActive_Last(
        java.lang.String isActive,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the gl balance masters before and after the current gl balance master in the ordered set where isActive = &#63;.
    *
    * @param glBalanceMasterSid the primary key of the current gl balance master
    * @param isActive the is active
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next gl balance master
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlBalanceMaster[] findByIsActive_PrevAndNext(
        int glBalanceMasterSid, java.lang.String isActive,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the gl balance masters where isActive = &#63; from the database.
    *
    * @param isActive the is active
    * @throws SystemException if a system exception occurred
    */
    public void removeByIsActive(java.lang.String isActive)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of gl balance masters where isActive = &#63;.
    *
    * @param isActive the is active
    * @return the number of matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public int countByIsActive(java.lang.String isActive)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the gl balance masters where uploadDate = &#63;.
    *
    * @param uploadDate the upload date
    * @return the matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GlBalanceMaster> findByUploadDate(
        java.util.Date uploadDate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the gl balance masters where uploadDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param uploadDate the upload date
    * @param start the lower bound of the range of gl balance masters
    * @param end the upper bound of the range of gl balance masters (not inclusive)
    * @return the range of matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GlBalanceMaster> findByUploadDate(
        java.util.Date uploadDate, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the gl balance masters where uploadDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param uploadDate the upload date
    * @param start the lower bound of the range of gl balance masters
    * @param end the upper bound of the range of gl balance masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GlBalanceMaster> findByUploadDate(
        java.util.Date uploadDate, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first gl balance master in the ordered set where uploadDate = &#63;.
    *
    * @param uploadDate the upload date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching gl balance master
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlBalanceMaster findByUploadDate_First(
        java.util.Date uploadDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first gl balance master in the ordered set where uploadDate = &#63;.
    *
    * @param uploadDate the upload date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlBalanceMaster fetchByUploadDate_First(
        java.util.Date uploadDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last gl balance master in the ordered set where uploadDate = &#63;.
    *
    * @param uploadDate the upload date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching gl balance master
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlBalanceMaster findByUploadDate_Last(
        java.util.Date uploadDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last gl balance master in the ordered set where uploadDate = &#63;.
    *
    * @param uploadDate the upload date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlBalanceMaster fetchByUploadDate_Last(
        java.util.Date uploadDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the gl balance masters before and after the current gl balance master in the ordered set where uploadDate = &#63;.
    *
    * @param glBalanceMasterSid the primary key of the current gl balance master
    * @param uploadDate the upload date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next gl balance master
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlBalanceMaster[] findByUploadDate_PrevAndNext(
        int glBalanceMasterSid, java.util.Date uploadDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the gl balance masters where uploadDate = &#63; from the database.
    *
    * @param uploadDate the upload date
    * @throws SystemException if a system exception occurred
    */
    public void removeByUploadDate(java.util.Date uploadDate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of gl balance masters where uploadDate = &#63;.
    *
    * @param uploadDate the upload date
    * @return the number of matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public int countByUploadDate(java.util.Date uploadDate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the gl balance masters where accountId = &#63;.
    *
    * @param accountId the account ID
    * @return the matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GlBalanceMaster> findByAccountId(
        java.lang.String accountId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the gl balance masters where accountId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param accountId the account ID
    * @param start the lower bound of the range of gl balance masters
    * @param end the upper bound of the range of gl balance masters (not inclusive)
    * @return the range of matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GlBalanceMaster> findByAccountId(
        java.lang.String accountId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the gl balance masters where accountId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param accountId the account ID
    * @param start the lower bound of the range of gl balance masters
    * @param end the upper bound of the range of gl balance masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GlBalanceMaster> findByAccountId(
        java.lang.String accountId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first gl balance master in the ordered set where accountId = &#63;.
    *
    * @param accountId the account ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching gl balance master
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlBalanceMaster findByAccountId_First(
        java.lang.String accountId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first gl balance master in the ordered set where accountId = &#63;.
    *
    * @param accountId the account ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlBalanceMaster fetchByAccountId_First(
        java.lang.String accountId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last gl balance master in the ordered set where accountId = &#63;.
    *
    * @param accountId the account ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching gl balance master
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlBalanceMaster findByAccountId_Last(
        java.lang.String accountId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last gl balance master in the ordered set where accountId = &#63;.
    *
    * @param accountId the account ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlBalanceMaster fetchByAccountId_Last(
        java.lang.String accountId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the gl balance masters before and after the current gl balance master in the ordered set where accountId = &#63;.
    *
    * @param glBalanceMasterSid the primary key of the current gl balance master
    * @param accountId the account ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next gl balance master
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlBalanceMaster[] findByAccountId_PrevAndNext(
        int glBalanceMasterSid, java.lang.String accountId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the gl balance masters where accountId = &#63; from the database.
    *
    * @param accountId the account ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByAccountId(java.lang.String accountId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of gl balance masters where accountId = &#63;.
    *
    * @param accountId the account ID
    * @return the number of matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public int countByAccountId(java.lang.String accountId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the gl balance masters where year = &#63;.
    *
    * @param year the year
    * @return the matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GlBalanceMaster> findByYear(
        java.lang.String year)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the gl balance masters where year = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param year the year
    * @param start the lower bound of the range of gl balance masters
    * @param end the upper bound of the range of gl balance masters (not inclusive)
    * @return the range of matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GlBalanceMaster> findByYear(
        java.lang.String year, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the gl balance masters where year = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param year the year
    * @param start the lower bound of the range of gl balance masters
    * @param end the upper bound of the range of gl balance masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GlBalanceMaster> findByYear(
        java.lang.String year, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first gl balance master in the ordered set where year = &#63;.
    *
    * @param year the year
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching gl balance master
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlBalanceMaster findByYear_First(
        java.lang.String year,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first gl balance master in the ordered set where year = &#63;.
    *
    * @param year the year
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlBalanceMaster fetchByYear_First(
        java.lang.String year,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last gl balance master in the ordered set where year = &#63;.
    *
    * @param year the year
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching gl balance master
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlBalanceMaster findByYear_Last(
        java.lang.String year,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last gl balance master in the ordered set where year = &#63;.
    *
    * @param year the year
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlBalanceMaster fetchByYear_Last(
        java.lang.String year,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the gl balance masters before and after the current gl balance master in the ordered set where year = &#63;.
    *
    * @param glBalanceMasterSid the primary key of the current gl balance master
    * @param year the year
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next gl balance master
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlBalanceMaster[] findByYear_PrevAndNext(
        int glBalanceMasterSid, java.lang.String year,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the gl balance masters where year = &#63; from the database.
    *
    * @param year the year
    * @throws SystemException if a system exception occurred
    */
    public void removeByYear(java.lang.String year)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of gl balance masters where year = &#63;.
    *
    * @param year the year
    * @return the number of matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public int countByYear(java.lang.String year)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the gl balance masters where period = &#63;.
    *
    * @param period the period
    * @return the matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GlBalanceMaster> findByPeriod(
        java.lang.String period)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the gl balance masters where period = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param period the period
    * @param start the lower bound of the range of gl balance masters
    * @param end the upper bound of the range of gl balance masters (not inclusive)
    * @return the range of matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GlBalanceMaster> findByPeriod(
        java.lang.String period, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the gl balance masters where period = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param period the period
    * @param start the lower bound of the range of gl balance masters
    * @param end the upper bound of the range of gl balance masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GlBalanceMaster> findByPeriod(
        java.lang.String period, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first gl balance master in the ordered set where period = &#63;.
    *
    * @param period the period
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching gl balance master
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlBalanceMaster findByPeriod_First(
        java.lang.String period,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first gl balance master in the ordered set where period = &#63;.
    *
    * @param period the period
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlBalanceMaster fetchByPeriod_First(
        java.lang.String period,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last gl balance master in the ordered set where period = &#63;.
    *
    * @param period the period
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching gl balance master
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlBalanceMaster findByPeriod_Last(
        java.lang.String period,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last gl balance master in the ordered set where period = &#63;.
    *
    * @param period the period
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlBalanceMaster fetchByPeriod_Last(
        java.lang.String period,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the gl balance masters before and after the current gl balance master in the ordered set where period = &#63;.
    *
    * @param glBalanceMasterSid the primary key of the current gl balance master
    * @param period the period
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next gl balance master
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlBalanceMaster[] findByPeriod_PrevAndNext(
        int glBalanceMasterSid, java.lang.String period,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the gl balance masters where period = &#63; from the database.
    *
    * @param period the period
    * @throws SystemException if a system exception occurred
    */
    public void removeByPeriod(java.lang.String period)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of gl balance masters where period = &#63;.
    *
    * @param period the period
    * @return the number of matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public int countByPeriod(java.lang.String period)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the gl balance masters where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
    *
    * @param accountNo the account no
    * @param period the period
    * @param uploadDate the upload date
    * @return the matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GlBalanceMaster> findByGlBalanceUnique(
        java.lang.String accountNo, java.lang.String period,
        java.util.Date uploadDate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the gl balance masters where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param accountNo the account no
    * @param period the period
    * @param uploadDate the upload date
    * @param start the lower bound of the range of gl balance masters
    * @param end the upper bound of the range of gl balance masters (not inclusive)
    * @return the range of matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GlBalanceMaster> findByGlBalanceUnique(
        java.lang.String accountNo, java.lang.String period,
        java.util.Date uploadDate, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the gl balance masters where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param accountNo the account no
    * @param period the period
    * @param uploadDate the upload date
    * @param start the lower bound of the range of gl balance masters
    * @param end the upper bound of the range of gl balance masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GlBalanceMaster> findByGlBalanceUnique(
        java.lang.String accountNo, java.lang.String period,
        java.util.Date uploadDate, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first gl balance master in the ordered set where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
    *
    * @param accountNo the account no
    * @param period the period
    * @param uploadDate the upload date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching gl balance master
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlBalanceMaster findByGlBalanceUnique_First(
        java.lang.String accountNo, java.lang.String period,
        java.util.Date uploadDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first gl balance master in the ordered set where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
    *
    * @param accountNo the account no
    * @param period the period
    * @param uploadDate the upload date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlBalanceMaster fetchByGlBalanceUnique_First(
        java.lang.String accountNo, java.lang.String period,
        java.util.Date uploadDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last gl balance master in the ordered set where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
    *
    * @param accountNo the account no
    * @param period the period
    * @param uploadDate the upload date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching gl balance master
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlBalanceMaster findByGlBalanceUnique_Last(
        java.lang.String accountNo, java.lang.String period,
        java.util.Date uploadDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last gl balance master in the ordered set where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
    *
    * @param accountNo the account no
    * @param period the period
    * @param uploadDate the upload date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlBalanceMaster fetchByGlBalanceUnique_Last(
        java.lang.String accountNo, java.lang.String period,
        java.util.Date uploadDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the gl balance masters before and after the current gl balance master in the ordered set where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
    *
    * @param glBalanceMasterSid the primary key of the current gl balance master
    * @param accountNo the account no
    * @param period the period
    * @param uploadDate the upload date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next gl balance master
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlBalanceMaster[] findByGlBalanceUnique_PrevAndNext(
        int glBalanceMasterSid, java.lang.String accountNo,
        java.lang.String period, java.util.Date uploadDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the gl balance masters where accountNo = &#63; and period = &#63; and uploadDate = &#63; from the database.
    *
    * @param accountNo the account no
    * @param period the period
    * @param uploadDate the upload date
    * @throws SystemException if a system exception occurred
    */
    public void removeByGlBalanceUnique(java.lang.String accountNo,
        java.lang.String period, java.util.Date uploadDate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of gl balance masters where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
    *
    * @param accountNo the account no
    * @param period the period
    * @param uploadDate the upload date
    * @return the number of matching gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public int countByGlBalanceUnique(java.lang.String accountNo,
        java.lang.String period, java.util.Date uploadDate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Caches the gl balance master in the entity cache if it is enabled.
    *
    * @param glBalanceMaster the gl balance master
    */
    public void cacheResult(com.stpl.app.model.GlBalanceMaster glBalanceMaster);

    /**
    * Caches the gl balance masters in the entity cache if it is enabled.
    *
    * @param glBalanceMasters the gl balance masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.GlBalanceMaster> glBalanceMasters);

    /**
    * Creates a new gl balance master with the primary key. Does not add the gl balance master to the database.
    *
    * @param glBalanceMasterSid the primary key for the new gl balance master
    * @return the new gl balance master
    */
    public com.stpl.app.model.GlBalanceMaster create(int glBalanceMasterSid);

    /**
    * Removes the gl balance master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param glBalanceMasterSid the primary key of the gl balance master
    * @return the gl balance master that was removed
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlBalanceMaster remove(int glBalanceMasterSid)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.GlBalanceMaster updateImpl(
        com.stpl.app.model.GlBalanceMaster glBalanceMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the gl balance master with the primary key or throws a {@link com.stpl.app.NoSuchGlBalanceMasterException} if it could not be found.
    *
    * @param glBalanceMasterSid the primary key of the gl balance master
    * @return the gl balance master
    * @throws com.stpl.app.NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlBalanceMaster findByPrimaryKey(
        int glBalanceMasterSid)
        throws com.stpl.app.NoSuchGlBalanceMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the gl balance master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param glBalanceMasterSid the primary key of the gl balance master
    * @return the gl balance master, or <code>null</code> if a gl balance master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlBalanceMaster fetchByPrimaryKey(
        int glBalanceMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the gl balance masters.
    *
    * @return the gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GlBalanceMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the gl balance masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of gl balance masters
    * @param end the upper bound of the range of gl balance masters (not inclusive)
    * @return the range of gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GlBalanceMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the gl balance masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of gl balance masters
    * @param end the upper bound of the range of gl balance masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GlBalanceMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the gl balance masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of gl balance masters.
    *
    * @return the number of gl balance masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
