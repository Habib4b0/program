package com.stpl.app.service.persistence;

import com.stpl.app.model.CpiIndexMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the cpi index master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CpiIndexMasterPersistenceImpl
 * @see CpiIndexMasterUtil
 * @generated
 */
public interface CpiIndexMasterPersistence extends BasePersistence<CpiIndexMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link CpiIndexMasterUtil} to access the cpi index master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the cpi index masters where status = &#63;.
    *
    * @param status the status
    * @return the matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CpiIndexMaster> findByStatus(
        java.lang.String status)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the cpi index masters where status = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param status the status
    * @param start the lower bound of the range of cpi index masters
    * @param end the upper bound of the range of cpi index masters (not inclusive)
    * @return the range of matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CpiIndexMaster> findByStatus(
        java.lang.String status, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the cpi index masters where status = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param status the status
    * @param start the lower bound of the range of cpi index masters
    * @param end the upper bound of the range of cpi index masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CpiIndexMaster> findByStatus(
        java.lang.String status, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first cpi index master in the ordered set where status = &#63;.
    *
    * @param status the status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cpi index master
    * @throws com.stpl.app.NoSuchCpiIndexMasterException if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CpiIndexMaster findByStatus_First(
        java.lang.String status,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCpiIndexMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first cpi index master in the ordered set where status = &#63;.
    *
    * @param status the status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CpiIndexMaster fetchByStatus_First(
        java.lang.String status,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last cpi index master in the ordered set where status = &#63;.
    *
    * @param status the status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cpi index master
    * @throws com.stpl.app.NoSuchCpiIndexMasterException if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CpiIndexMaster findByStatus_Last(
        java.lang.String status,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCpiIndexMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last cpi index master in the ordered set where status = &#63;.
    *
    * @param status the status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CpiIndexMaster fetchByStatus_Last(
        java.lang.String status,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the cpi index masters before and after the current cpi index master in the ordered set where status = &#63;.
    *
    * @param cpiIndexMasterSid the primary key of the current cpi index master
    * @param status the status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next cpi index master
    * @throws com.stpl.app.NoSuchCpiIndexMasterException if a cpi index master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CpiIndexMaster[] findByStatus_PrevAndNext(
        int cpiIndexMasterSid, java.lang.String status,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCpiIndexMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the cpi index masters where status = &#63; from the database.
    *
    * @param status the status
    * @throws SystemException if a system exception occurred
    */
    public void removeByStatus(java.lang.String status)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of cpi index masters where status = &#63;.
    *
    * @param status the status
    * @return the number of matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public int countByStatus(java.lang.String status)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the cpi index masters where indexId = &#63;.
    *
    * @param indexId the index ID
    * @return the matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CpiIndexMaster> findByIndexId(
        java.lang.String indexId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the cpi index masters where indexId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param indexId the index ID
    * @param start the lower bound of the range of cpi index masters
    * @param end the upper bound of the range of cpi index masters (not inclusive)
    * @return the range of matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CpiIndexMaster> findByIndexId(
        java.lang.String indexId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the cpi index masters where indexId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param indexId the index ID
    * @param start the lower bound of the range of cpi index masters
    * @param end the upper bound of the range of cpi index masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CpiIndexMaster> findByIndexId(
        java.lang.String indexId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first cpi index master in the ordered set where indexId = &#63;.
    *
    * @param indexId the index ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cpi index master
    * @throws com.stpl.app.NoSuchCpiIndexMasterException if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CpiIndexMaster findByIndexId_First(
        java.lang.String indexId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCpiIndexMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first cpi index master in the ordered set where indexId = &#63;.
    *
    * @param indexId the index ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CpiIndexMaster fetchByIndexId_First(
        java.lang.String indexId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last cpi index master in the ordered set where indexId = &#63;.
    *
    * @param indexId the index ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cpi index master
    * @throws com.stpl.app.NoSuchCpiIndexMasterException if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CpiIndexMaster findByIndexId_Last(
        java.lang.String indexId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCpiIndexMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last cpi index master in the ordered set where indexId = &#63;.
    *
    * @param indexId the index ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CpiIndexMaster fetchByIndexId_Last(
        java.lang.String indexId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the cpi index masters before and after the current cpi index master in the ordered set where indexId = &#63;.
    *
    * @param cpiIndexMasterSid the primary key of the current cpi index master
    * @param indexId the index ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next cpi index master
    * @throws com.stpl.app.NoSuchCpiIndexMasterException if a cpi index master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CpiIndexMaster[] findByIndexId_PrevAndNext(
        int cpiIndexMasterSid, java.lang.String indexId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCpiIndexMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the cpi index masters where indexId = &#63; from the database.
    *
    * @param indexId the index ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByIndexId(java.lang.String indexId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of cpi index masters where indexId = &#63;.
    *
    * @param indexId the index ID
    * @return the number of matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public int countByIndexId(java.lang.String indexId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the cpi index masters where indexValue = &#63;.
    *
    * @param indexValue the index value
    * @return the matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CpiIndexMaster> findByIndexValue(
        java.lang.String indexValue)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the cpi index masters where indexValue = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param indexValue the index value
    * @param start the lower bound of the range of cpi index masters
    * @param end the upper bound of the range of cpi index masters (not inclusive)
    * @return the range of matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CpiIndexMaster> findByIndexValue(
        java.lang.String indexValue, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the cpi index masters where indexValue = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param indexValue the index value
    * @param start the lower bound of the range of cpi index masters
    * @param end the upper bound of the range of cpi index masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CpiIndexMaster> findByIndexValue(
        java.lang.String indexValue, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first cpi index master in the ordered set where indexValue = &#63;.
    *
    * @param indexValue the index value
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cpi index master
    * @throws com.stpl.app.NoSuchCpiIndexMasterException if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CpiIndexMaster findByIndexValue_First(
        java.lang.String indexValue,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCpiIndexMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first cpi index master in the ordered set where indexValue = &#63;.
    *
    * @param indexValue the index value
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CpiIndexMaster fetchByIndexValue_First(
        java.lang.String indexValue,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last cpi index master in the ordered set where indexValue = &#63;.
    *
    * @param indexValue the index value
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cpi index master
    * @throws com.stpl.app.NoSuchCpiIndexMasterException if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CpiIndexMaster findByIndexValue_Last(
        java.lang.String indexValue,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCpiIndexMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last cpi index master in the ordered set where indexValue = &#63;.
    *
    * @param indexValue the index value
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CpiIndexMaster fetchByIndexValue_Last(
        java.lang.String indexValue,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the cpi index masters before and after the current cpi index master in the ordered set where indexValue = &#63;.
    *
    * @param cpiIndexMasterSid the primary key of the current cpi index master
    * @param indexValue the index value
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next cpi index master
    * @throws com.stpl.app.NoSuchCpiIndexMasterException if a cpi index master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CpiIndexMaster[] findByIndexValue_PrevAndNext(
        int cpiIndexMasterSid, java.lang.String indexValue,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCpiIndexMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the cpi index masters where indexValue = &#63; from the database.
    *
    * @param indexValue the index value
    * @throws SystemException if a system exception occurred
    */
    public void removeByIndexValue(java.lang.String indexValue)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of cpi index masters where indexValue = &#63;.
    *
    * @param indexValue the index value
    * @return the number of matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public int countByIndexValue(java.lang.String indexValue)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the cpi index masters where indexType = &#63;.
    *
    * @param indexType the index type
    * @return the matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CpiIndexMaster> findByIndexType(
        java.lang.String indexType)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the cpi index masters where indexType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param indexType the index type
    * @param start the lower bound of the range of cpi index masters
    * @param end the upper bound of the range of cpi index masters (not inclusive)
    * @return the range of matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CpiIndexMaster> findByIndexType(
        java.lang.String indexType, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the cpi index masters where indexType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param indexType the index type
    * @param start the lower bound of the range of cpi index masters
    * @param end the upper bound of the range of cpi index masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CpiIndexMaster> findByIndexType(
        java.lang.String indexType, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first cpi index master in the ordered set where indexType = &#63;.
    *
    * @param indexType the index type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cpi index master
    * @throws com.stpl.app.NoSuchCpiIndexMasterException if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CpiIndexMaster findByIndexType_First(
        java.lang.String indexType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCpiIndexMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first cpi index master in the ordered set where indexType = &#63;.
    *
    * @param indexType the index type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CpiIndexMaster fetchByIndexType_First(
        java.lang.String indexType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last cpi index master in the ordered set where indexType = &#63;.
    *
    * @param indexType the index type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cpi index master
    * @throws com.stpl.app.NoSuchCpiIndexMasterException if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CpiIndexMaster findByIndexType_Last(
        java.lang.String indexType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCpiIndexMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last cpi index master in the ordered set where indexType = &#63;.
    *
    * @param indexType the index type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CpiIndexMaster fetchByIndexType_Last(
        java.lang.String indexType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the cpi index masters before and after the current cpi index master in the ordered set where indexType = &#63;.
    *
    * @param cpiIndexMasterSid the primary key of the current cpi index master
    * @param indexType the index type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next cpi index master
    * @throws com.stpl.app.NoSuchCpiIndexMasterException if a cpi index master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CpiIndexMaster[] findByIndexType_PrevAndNext(
        int cpiIndexMasterSid, java.lang.String indexType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCpiIndexMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the cpi index masters where indexType = &#63; from the database.
    *
    * @param indexType the index type
    * @throws SystemException if a system exception occurred
    */
    public void removeByIndexType(java.lang.String indexType)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of cpi index masters where indexType = &#63;.
    *
    * @param indexType the index type
    * @return the number of matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public int countByIndexType(java.lang.String indexType)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the cpi index masters where effectiveDate = &#63;.
    *
    * @param effectiveDate the effective date
    * @return the matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CpiIndexMaster> findByEffectiveDate(
        java.util.Date effectiveDate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the cpi index masters where effectiveDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param effectiveDate the effective date
    * @param start the lower bound of the range of cpi index masters
    * @param end the upper bound of the range of cpi index masters (not inclusive)
    * @return the range of matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CpiIndexMaster> findByEffectiveDate(
        java.util.Date effectiveDate, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the cpi index masters where effectiveDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param effectiveDate the effective date
    * @param start the lower bound of the range of cpi index masters
    * @param end the upper bound of the range of cpi index masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CpiIndexMaster> findByEffectiveDate(
        java.util.Date effectiveDate, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first cpi index master in the ordered set where effectiveDate = &#63;.
    *
    * @param effectiveDate the effective date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cpi index master
    * @throws com.stpl.app.NoSuchCpiIndexMasterException if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CpiIndexMaster findByEffectiveDate_First(
        java.util.Date effectiveDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCpiIndexMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first cpi index master in the ordered set where effectiveDate = &#63;.
    *
    * @param effectiveDate the effective date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CpiIndexMaster fetchByEffectiveDate_First(
        java.util.Date effectiveDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last cpi index master in the ordered set where effectiveDate = &#63;.
    *
    * @param effectiveDate the effective date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cpi index master
    * @throws com.stpl.app.NoSuchCpiIndexMasterException if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CpiIndexMaster findByEffectiveDate_Last(
        java.util.Date effectiveDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCpiIndexMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last cpi index master in the ordered set where effectiveDate = &#63;.
    *
    * @param effectiveDate the effective date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CpiIndexMaster fetchByEffectiveDate_Last(
        java.util.Date effectiveDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the cpi index masters before and after the current cpi index master in the ordered set where effectiveDate = &#63;.
    *
    * @param cpiIndexMasterSid the primary key of the current cpi index master
    * @param effectiveDate the effective date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next cpi index master
    * @throws com.stpl.app.NoSuchCpiIndexMasterException if a cpi index master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CpiIndexMaster[] findByEffectiveDate_PrevAndNext(
        int cpiIndexMasterSid, java.util.Date effectiveDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCpiIndexMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the cpi index masters where effectiveDate = &#63; from the database.
    *
    * @param effectiveDate the effective date
    * @throws SystemException if a system exception occurred
    */
    public void removeByEffectiveDate(java.util.Date effectiveDate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of cpi index masters where effectiveDate = &#63;.
    *
    * @param effectiveDate the effective date
    * @return the number of matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public int countByEffectiveDate(java.util.Date effectiveDate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the cpi index masters where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
    *
    * @param indexId the index ID
    * @param status the status
    * @param indexType the index type
    * @param effectiveDate the effective date
    * @return the matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CpiIndexMaster> findByCpiIndexUnique(
        java.lang.String indexId, java.lang.String status,
        java.lang.String indexType, java.util.Date effectiveDate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the cpi index masters where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param indexId the index ID
    * @param status the status
    * @param indexType the index type
    * @param effectiveDate the effective date
    * @param start the lower bound of the range of cpi index masters
    * @param end the upper bound of the range of cpi index masters (not inclusive)
    * @return the range of matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CpiIndexMaster> findByCpiIndexUnique(
        java.lang.String indexId, java.lang.String status,
        java.lang.String indexType, java.util.Date effectiveDate, int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the cpi index masters where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param indexId the index ID
    * @param status the status
    * @param indexType the index type
    * @param effectiveDate the effective date
    * @param start the lower bound of the range of cpi index masters
    * @param end the upper bound of the range of cpi index masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CpiIndexMaster> findByCpiIndexUnique(
        java.lang.String indexId, java.lang.String status,
        java.lang.String indexType, java.util.Date effectiveDate, int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first cpi index master in the ordered set where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
    *
    * @param indexId the index ID
    * @param status the status
    * @param indexType the index type
    * @param effectiveDate the effective date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cpi index master
    * @throws com.stpl.app.NoSuchCpiIndexMasterException if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CpiIndexMaster findByCpiIndexUnique_First(
        java.lang.String indexId, java.lang.String status,
        java.lang.String indexType, java.util.Date effectiveDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCpiIndexMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first cpi index master in the ordered set where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
    *
    * @param indexId the index ID
    * @param status the status
    * @param indexType the index type
    * @param effectiveDate the effective date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CpiIndexMaster fetchByCpiIndexUnique_First(
        java.lang.String indexId, java.lang.String status,
        java.lang.String indexType, java.util.Date effectiveDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last cpi index master in the ordered set where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
    *
    * @param indexId the index ID
    * @param status the status
    * @param indexType the index type
    * @param effectiveDate the effective date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cpi index master
    * @throws com.stpl.app.NoSuchCpiIndexMasterException if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CpiIndexMaster findByCpiIndexUnique_Last(
        java.lang.String indexId, java.lang.String status,
        java.lang.String indexType, java.util.Date effectiveDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCpiIndexMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last cpi index master in the ordered set where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
    *
    * @param indexId the index ID
    * @param status the status
    * @param indexType the index type
    * @param effectiveDate the effective date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CpiIndexMaster fetchByCpiIndexUnique_Last(
        java.lang.String indexId, java.lang.String status,
        java.lang.String indexType, java.util.Date effectiveDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the cpi index masters before and after the current cpi index master in the ordered set where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
    *
    * @param cpiIndexMasterSid the primary key of the current cpi index master
    * @param indexId the index ID
    * @param status the status
    * @param indexType the index type
    * @param effectiveDate the effective date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next cpi index master
    * @throws com.stpl.app.NoSuchCpiIndexMasterException if a cpi index master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CpiIndexMaster[] findByCpiIndexUnique_PrevAndNext(
        int cpiIndexMasterSid, java.lang.String indexId,
        java.lang.String status, java.lang.String indexType,
        java.util.Date effectiveDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCpiIndexMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the cpi index masters where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63; from the database.
    *
    * @param indexId the index ID
    * @param status the status
    * @param indexType the index type
    * @param effectiveDate the effective date
    * @throws SystemException if a system exception occurred
    */
    public void removeByCpiIndexUnique(java.lang.String indexId,
        java.lang.String status, java.lang.String indexType,
        java.util.Date effectiveDate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of cpi index masters where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
    *
    * @param indexId the index ID
    * @param status the status
    * @param indexType the index type
    * @param effectiveDate the effective date
    * @return the number of matching cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public int countByCpiIndexUnique(java.lang.String indexId,
        java.lang.String status, java.lang.String indexType,
        java.util.Date effectiveDate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Caches the cpi index master in the entity cache if it is enabled.
    *
    * @param cpiIndexMaster the cpi index master
    */
    public void cacheResult(com.stpl.app.model.CpiIndexMaster cpiIndexMaster);

    /**
    * Caches the cpi index masters in the entity cache if it is enabled.
    *
    * @param cpiIndexMasters the cpi index masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.CpiIndexMaster> cpiIndexMasters);

    /**
    * Creates a new cpi index master with the primary key. Does not add the cpi index master to the database.
    *
    * @param cpiIndexMasterSid the primary key for the new cpi index master
    * @return the new cpi index master
    */
    public com.stpl.app.model.CpiIndexMaster create(int cpiIndexMasterSid);

    /**
    * Removes the cpi index master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param cpiIndexMasterSid the primary key of the cpi index master
    * @return the cpi index master that was removed
    * @throws com.stpl.app.NoSuchCpiIndexMasterException if a cpi index master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CpiIndexMaster remove(int cpiIndexMasterSid)
        throws com.stpl.app.NoSuchCpiIndexMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.CpiIndexMaster updateImpl(
        com.stpl.app.model.CpiIndexMaster cpiIndexMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the cpi index master with the primary key or throws a {@link com.stpl.app.NoSuchCpiIndexMasterException} if it could not be found.
    *
    * @param cpiIndexMasterSid the primary key of the cpi index master
    * @return the cpi index master
    * @throws com.stpl.app.NoSuchCpiIndexMasterException if a cpi index master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CpiIndexMaster findByPrimaryKey(
        int cpiIndexMasterSid)
        throws com.stpl.app.NoSuchCpiIndexMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the cpi index master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param cpiIndexMasterSid the primary key of the cpi index master
    * @return the cpi index master, or <code>null</code> if a cpi index master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CpiIndexMaster fetchByPrimaryKey(
        int cpiIndexMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the cpi index masters.
    *
    * @return the cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CpiIndexMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the cpi index masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cpi index masters
    * @param end the upper bound of the range of cpi index masters (not inclusive)
    * @return the range of cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CpiIndexMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the cpi index masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cpi index masters
    * @param end the upper bound of the range of cpi index masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CpiIndexMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the cpi index masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of cpi index masters.
    *
    * @return the number of cpi index masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
