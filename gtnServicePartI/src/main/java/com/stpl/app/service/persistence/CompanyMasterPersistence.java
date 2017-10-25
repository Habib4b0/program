package com.stpl.app.service.persistence;

import com.stpl.app.model.CompanyMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the company master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CompanyMasterPersistenceImpl
 * @see CompanyMasterUtil
 * @generated
 */
public interface CompanyMasterPersistence extends BasePersistence<CompanyMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link CompanyMasterUtil} to access the company master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the company masters where companyNo = &#63;.
    *
    * @param companyNo the company no
    * @return the matching company masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CompanyMaster> findByCompanyNo(
        java.lang.String companyNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the company masters where companyNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyNo the company no
    * @param start the lower bound of the range of company masters
    * @param end the upper bound of the range of company masters (not inclusive)
    * @return the range of matching company masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CompanyMaster> findByCompanyNo(
        java.lang.String companyNo, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the company masters where companyNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyNo the company no
    * @param start the lower bound of the range of company masters
    * @param end the upper bound of the range of company masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching company masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CompanyMaster> findByCompanyNo(
        java.lang.String companyNo, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first company master in the ordered set where companyNo = &#63;.
    *
    * @param companyNo the company no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching company master
    * @throws com.stpl.app.NoSuchCompanyMasterException if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyMaster findByCompanyNo_First(
        java.lang.String companyNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first company master in the ordered set where companyNo = &#63;.
    *
    * @param companyNo the company no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching company master, or <code>null</code> if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyMaster fetchByCompanyNo_First(
        java.lang.String companyNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last company master in the ordered set where companyNo = &#63;.
    *
    * @param companyNo the company no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching company master
    * @throws com.stpl.app.NoSuchCompanyMasterException if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyMaster findByCompanyNo_Last(
        java.lang.String companyNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last company master in the ordered set where companyNo = &#63;.
    *
    * @param companyNo the company no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching company master, or <code>null</code> if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyMaster fetchByCompanyNo_Last(
        java.lang.String companyNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the company masters before and after the current company master in the ordered set where companyNo = &#63;.
    *
    * @param companyMasterSid the primary key of the current company master
    * @param companyNo the company no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next company master
    * @throws com.stpl.app.NoSuchCompanyMasterException if a company master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyMaster[] findByCompanyNo_PrevAndNext(
        int companyMasterSid, java.lang.String companyNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the company masters where companyNo = &#63; from the database.
    *
    * @param companyNo the company no
    * @throws SystemException if a system exception occurred
    */
    public void removeByCompanyNo(java.lang.String companyNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of company masters where companyNo = &#63;.
    *
    * @param companyNo the company no
    * @return the number of matching company masters
    * @throws SystemException if a system exception occurred
    */
    public int countByCompanyNo(java.lang.String companyNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the company masters where companyId = &#63;.
    *
    * @param companyId the company ID
    * @return the matching company masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CompanyMaster> findByCompanyId(
        java.lang.String companyId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the company masters where companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyId the company ID
    * @param start the lower bound of the range of company masters
    * @param end the upper bound of the range of company masters (not inclusive)
    * @return the range of matching company masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CompanyMaster> findByCompanyId(
        java.lang.String companyId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the company masters where companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyId the company ID
    * @param start the lower bound of the range of company masters
    * @param end the upper bound of the range of company masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching company masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CompanyMaster> findByCompanyId(
        java.lang.String companyId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first company master in the ordered set where companyId = &#63;.
    *
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching company master
    * @throws com.stpl.app.NoSuchCompanyMasterException if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyMaster findByCompanyId_First(
        java.lang.String companyId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first company master in the ordered set where companyId = &#63;.
    *
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching company master, or <code>null</code> if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyMaster fetchByCompanyId_First(
        java.lang.String companyId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last company master in the ordered set where companyId = &#63;.
    *
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching company master
    * @throws com.stpl.app.NoSuchCompanyMasterException if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyMaster findByCompanyId_Last(
        java.lang.String companyId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last company master in the ordered set where companyId = &#63;.
    *
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching company master, or <code>null</code> if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyMaster fetchByCompanyId_Last(
        java.lang.String companyId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the company masters before and after the current company master in the ordered set where companyId = &#63;.
    *
    * @param companyMasterSid the primary key of the current company master
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next company master
    * @throws com.stpl.app.NoSuchCompanyMasterException if a company master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyMaster[] findByCompanyId_PrevAndNext(
        int companyMasterSid, java.lang.String companyId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the company masters where companyId = &#63; from the database.
    *
    * @param companyId the company ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByCompanyId(java.lang.String companyId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of company masters where companyId = &#63;.
    *
    * @param companyId the company ID
    * @return the number of matching company masters
    * @throws SystemException if a system exception occurred
    */
    public int countByCompanyId(java.lang.String companyId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the company masters where companyName = &#63;.
    *
    * @param companyName the company name
    * @return the matching company masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CompanyMaster> findByCompanyName(
        java.lang.String companyName)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the company masters where companyName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyName the company name
    * @param start the lower bound of the range of company masters
    * @param end the upper bound of the range of company masters (not inclusive)
    * @return the range of matching company masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CompanyMaster> findByCompanyName(
        java.lang.String companyName, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the company masters where companyName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyName the company name
    * @param start the lower bound of the range of company masters
    * @param end the upper bound of the range of company masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching company masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CompanyMaster> findByCompanyName(
        java.lang.String companyName, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first company master in the ordered set where companyName = &#63;.
    *
    * @param companyName the company name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching company master
    * @throws com.stpl.app.NoSuchCompanyMasterException if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyMaster findByCompanyName_First(
        java.lang.String companyName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first company master in the ordered set where companyName = &#63;.
    *
    * @param companyName the company name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching company master, or <code>null</code> if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyMaster fetchByCompanyName_First(
        java.lang.String companyName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last company master in the ordered set where companyName = &#63;.
    *
    * @param companyName the company name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching company master
    * @throws com.stpl.app.NoSuchCompanyMasterException if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyMaster findByCompanyName_Last(
        java.lang.String companyName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last company master in the ordered set where companyName = &#63;.
    *
    * @param companyName the company name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching company master, or <code>null</code> if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyMaster fetchByCompanyName_Last(
        java.lang.String companyName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the company masters before and after the current company master in the ordered set where companyName = &#63;.
    *
    * @param companyMasterSid the primary key of the current company master
    * @param companyName the company name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next company master
    * @throws com.stpl.app.NoSuchCompanyMasterException if a company master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyMaster[] findByCompanyName_PrevAndNext(
        int companyMasterSid, java.lang.String companyName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the company masters where companyName = &#63; from the database.
    *
    * @param companyName the company name
    * @throws SystemException if a system exception occurred
    */
    public void removeByCompanyName(java.lang.String companyName)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of company masters where companyName = &#63;.
    *
    * @param companyName the company name
    * @return the number of matching company masters
    * @throws SystemException if a system exception occurred
    */
    public int countByCompanyName(java.lang.String companyName)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the company masters where companyType = &#63;.
    *
    * @param companyType the company type
    * @return the matching company masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CompanyMaster> findByCompanyType(
        int companyType)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the company masters where companyType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyType the company type
    * @param start the lower bound of the range of company masters
    * @param end the upper bound of the range of company masters (not inclusive)
    * @return the range of matching company masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CompanyMaster> findByCompanyType(
        int companyType, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the company masters where companyType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyType the company type
    * @param start the lower bound of the range of company masters
    * @param end the upper bound of the range of company masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching company masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CompanyMaster> findByCompanyType(
        int companyType, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first company master in the ordered set where companyType = &#63;.
    *
    * @param companyType the company type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching company master
    * @throws com.stpl.app.NoSuchCompanyMasterException if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyMaster findByCompanyType_First(
        int companyType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first company master in the ordered set where companyType = &#63;.
    *
    * @param companyType the company type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching company master, or <code>null</code> if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyMaster fetchByCompanyType_First(
        int companyType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last company master in the ordered set where companyType = &#63;.
    *
    * @param companyType the company type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching company master
    * @throws com.stpl.app.NoSuchCompanyMasterException if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyMaster findByCompanyType_Last(
        int companyType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last company master in the ordered set where companyType = &#63;.
    *
    * @param companyType the company type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching company master, or <code>null</code> if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyMaster fetchByCompanyType_Last(
        int companyType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the company masters before and after the current company master in the ordered set where companyType = &#63;.
    *
    * @param companyMasterSid the primary key of the current company master
    * @param companyType the company type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next company master
    * @throws com.stpl.app.NoSuchCompanyMasterException if a company master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyMaster[] findByCompanyType_PrevAndNext(
        int companyMasterSid, int companyType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the company masters where companyType = &#63; from the database.
    *
    * @param companyType the company type
    * @throws SystemException if a system exception occurred
    */
    public void removeByCompanyType(int companyType)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of company masters where companyType = &#63;.
    *
    * @param companyType the company type
    * @return the number of matching company masters
    * @throws SystemException if a system exception occurred
    */
    public int countByCompanyType(int companyType)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the company masters where companyStatus = &#63;.
    *
    * @param companyStatus the company status
    * @return the matching company masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CompanyMaster> findByCompanyStatus(
        int companyStatus)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the company masters where companyStatus = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyStatus the company status
    * @param start the lower bound of the range of company masters
    * @param end the upper bound of the range of company masters (not inclusive)
    * @return the range of matching company masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CompanyMaster> findByCompanyStatus(
        int companyStatus, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the company masters where companyStatus = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyStatus the company status
    * @param start the lower bound of the range of company masters
    * @param end the upper bound of the range of company masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching company masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CompanyMaster> findByCompanyStatus(
        int companyStatus, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first company master in the ordered set where companyStatus = &#63;.
    *
    * @param companyStatus the company status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching company master
    * @throws com.stpl.app.NoSuchCompanyMasterException if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyMaster findByCompanyStatus_First(
        int companyStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first company master in the ordered set where companyStatus = &#63;.
    *
    * @param companyStatus the company status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching company master, or <code>null</code> if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyMaster fetchByCompanyStatus_First(
        int companyStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last company master in the ordered set where companyStatus = &#63;.
    *
    * @param companyStatus the company status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching company master
    * @throws com.stpl.app.NoSuchCompanyMasterException if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyMaster findByCompanyStatus_Last(
        int companyStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last company master in the ordered set where companyStatus = &#63;.
    *
    * @param companyStatus the company status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching company master, or <code>null</code> if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyMaster fetchByCompanyStatus_Last(
        int companyStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the company masters before and after the current company master in the ordered set where companyStatus = &#63;.
    *
    * @param companyMasterSid the primary key of the current company master
    * @param companyStatus the company status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next company master
    * @throws com.stpl.app.NoSuchCompanyMasterException if a company master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyMaster[] findByCompanyStatus_PrevAndNext(
        int companyMasterSid, int companyStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the company masters where companyStatus = &#63; from the database.
    *
    * @param companyStatus the company status
    * @throws SystemException if a system exception occurred
    */
    public void removeByCompanyStatus(int companyStatus)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of company masters where companyStatus = &#63;.
    *
    * @param companyStatus the company status
    * @return the number of matching company masters
    * @throws SystemException if a system exception occurred
    */
    public int countByCompanyStatus(int companyStatus)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the company masters where companyId = &#63;.
    *
    * @param companyId the company ID
    * @return the matching company masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CompanyMaster> findByCompanyUnique(
        java.lang.String companyId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the company masters where companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyId the company ID
    * @param start the lower bound of the range of company masters
    * @param end the upper bound of the range of company masters (not inclusive)
    * @return the range of matching company masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CompanyMaster> findByCompanyUnique(
        java.lang.String companyId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the company masters where companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyId the company ID
    * @param start the lower bound of the range of company masters
    * @param end the upper bound of the range of company masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching company masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CompanyMaster> findByCompanyUnique(
        java.lang.String companyId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first company master in the ordered set where companyId = &#63;.
    *
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching company master
    * @throws com.stpl.app.NoSuchCompanyMasterException if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyMaster findByCompanyUnique_First(
        java.lang.String companyId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first company master in the ordered set where companyId = &#63;.
    *
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching company master, or <code>null</code> if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyMaster fetchByCompanyUnique_First(
        java.lang.String companyId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last company master in the ordered set where companyId = &#63;.
    *
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching company master
    * @throws com.stpl.app.NoSuchCompanyMasterException if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyMaster findByCompanyUnique_Last(
        java.lang.String companyId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last company master in the ordered set where companyId = &#63;.
    *
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching company master, or <code>null</code> if a matching company master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyMaster fetchByCompanyUnique_Last(
        java.lang.String companyId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the company masters before and after the current company master in the ordered set where companyId = &#63;.
    *
    * @param companyMasterSid the primary key of the current company master
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next company master
    * @throws com.stpl.app.NoSuchCompanyMasterException if a company master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyMaster[] findByCompanyUnique_PrevAndNext(
        int companyMasterSid, java.lang.String companyId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the company masters where companyId = &#63; from the database.
    *
    * @param companyId the company ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByCompanyUnique(java.lang.String companyId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of company masters where companyId = &#63;.
    *
    * @param companyId the company ID
    * @return the number of matching company masters
    * @throws SystemException if a system exception occurred
    */
    public int countByCompanyUnique(java.lang.String companyId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Caches the company master in the entity cache if it is enabled.
    *
    * @param companyMaster the company master
    */
    public void cacheResult(com.stpl.app.model.CompanyMaster companyMaster);

    /**
    * Caches the company masters in the entity cache if it is enabled.
    *
    * @param companyMasters the company masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.CompanyMaster> companyMasters);

    /**
    * Creates a new company master with the primary key. Does not add the company master to the database.
    *
    * @param companyMasterSid the primary key for the new company master
    * @return the new company master
    */
    public com.stpl.app.model.CompanyMaster create(int companyMasterSid);

    /**
    * Removes the company master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param companyMasterSid the primary key of the company master
    * @return the company master that was removed
    * @throws com.stpl.app.NoSuchCompanyMasterException if a company master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyMaster remove(int companyMasterSid)
        throws com.stpl.app.NoSuchCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.CompanyMaster updateImpl(
        com.stpl.app.model.CompanyMaster companyMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the company master with the primary key or throws a {@link com.stpl.app.NoSuchCompanyMasterException} if it could not be found.
    *
    * @param companyMasterSid the primary key of the company master
    * @return the company master
    * @throws com.stpl.app.NoSuchCompanyMasterException if a company master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyMaster findByPrimaryKey(
        int companyMasterSid)
        throws com.stpl.app.NoSuchCompanyMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the company master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param companyMasterSid the primary key of the company master
    * @return the company master, or <code>null</code> if a company master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CompanyMaster fetchByPrimaryKey(
        int companyMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the company masters.
    *
    * @return the company masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CompanyMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the company masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of company masters
    * @param end the upper bound of the range of company masters (not inclusive)
    * @return the range of company masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CompanyMaster> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the company masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of company masters
    * @param end the upper bound of the range of company masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of company masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CompanyMaster> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the company masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of company masters.
    *
    * @return the number of company masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
