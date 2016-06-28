package com.stpl.app.service.persistence;

import com.stpl.app.model.GlCostCenterMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the gl cost center master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see GlCostCenterMasterPersistenceImpl
 * @see GlCostCenterMasterUtil
 * @generated
 */
public interface GlCostCenterMasterPersistence extends BasePersistence<GlCostCenterMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link GlCostCenterMasterUtil} to access the gl cost center master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the gl cost center masters where companyCostCenter = &#63;.
    *
    * @param companyCostCenter the company cost center
    * @return the matching gl cost center masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GlCostCenterMaster> findByCompanyCostCenter(
        java.lang.String companyCostCenter)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the gl cost center masters where companyCostCenter = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyCostCenter the company cost center
    * @param start the lower bound of the range of gl cost center masters
    * @param end the upper bound of the range of gl cost center masters (not inclusive)
    * @return the range of matching gl cost center masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GlCostCenterMaster> findByCompanyCostCenter(
        java.lang.String companyCostCenter, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the gl cost center masters where companyCostCenter = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyCostCenter the company cost center
    * @param start the lower bound of the range of gl cost center masters
    * @param end the upper bound of the range of gl cost center masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching gl cost center masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GlCostCenterMaster> findByCompanyCostCenter(
        java.lang.String companyCostCenter, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first gl cost center master in the ordered set where companyCostCenter = &#63;.
    *
    * @param companyCostCenter the company cost center
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching gl cost center master
    * @throws com.stpl.app.NoSuchGlCostCenterMasterException if a matching gl cost center master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlCostCenterMaster findByCompanyCostCenter_First(
        java.lang.String companyCostCenter,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlCostCenterMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first gl cost center master in the ordered set where companyCostCenter = &#63;.
    *
    * @param companyCostCenter the company cost center
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching gl cost center master, or <code>null</code> if a matching gl cost center master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlCostCenterMaster fetchByCompanyCostCenter_First(
        java.lang.String companyCostCenter,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last gl cost center master in the ordered set where companyCostCenter = &#63;.
    *
    * @param companyCostCenter the company cost center
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching gl cost center master
    * @throws com.stpl.app.NoSuchGlCostCenterMasterException if a matching gl cost center master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlCostCenterMaster findByCompanyCostCenter_Last(
        java.lang.String companyCostCenter,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlCostCenterMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last gl cost center master in the ordered set where companyCostCenter = &#63;.
    *
    * @param companyCostCenter the company cost center
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching gl cost center master, or <code>null</code> if a matching gl cost center master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlCostCenterMaster fetchByCompanyCostCenter_Last(
        java.lang.String companyCostCenter,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the gl cost center masters before and after the current gl cost center master in the ordered set where companyCostCenter = &#63;.
    *
    * @param glCostCenterMasterSid the primary key of the current gl cost center master
    * @param companyCostCenter the company cost center
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next gl cost center master
    * @throws com.stpl.app.NoSuchGlCostCenterMasterException if a gl cost center master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlCostCenterMaster[] findByCompanyCostCenter_PrevAndNext(
        int glCostCenterMasterSid, java.lang.String companyCostCenter,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlCostCenterMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the gl cost center masters where companyCostCenter = &#63; from the database.
    *
    * @param companyCostCenter the company cost center
    * @throws SystemException if a system exception occurred
    */
    public void removeByCompanyCostCenter(java.lang.String companyCostCenter)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of gl cost center masters where companyCostCenter = &#63;.
    *
    * @param companyCostCenter the company cost center
    * @return the number of matching gl cost center masters
    * @throws SystemException if a system exception occurred
    */
    public int countByCompanyCostCenter(java.lang.String companyCostCenter)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the gl cost center masters where ndc8 = &#63;.
    *
    * @param ndc8 the ndc8
    * @return the matching gl cost center masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GlCostCenterMaster> findByNdc8(
        java.lang.String ndc8)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the gl cost center masters where ndc8 = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param ndc8 the ndc8
    * @param start the lower bound of the range of gl cost center masters
    * @param end the upper bound of the range of gl cost center masters (not inclusive)
    * @return the range of matching gl cost center masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GlCostCenterMaster> findByNdc8(
        java.lang.String ndc8, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the gl cost center masters where ndc8 = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param ndc8 the ndc8
    * @param start the lower bound of the range of gl cost center masters
    * @param end the upper bound of the range of gl cost center masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching gl cost center masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GlCostCenterMaster> findByNdc8(
        java.lang.String ndc8, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first gl cost center master in the ordered set where ndc8 = &#63;.
    *
    * @param ndc8 the ndc8
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching gl cost center master
    * @throws com.stpl.app.NoSuchGlCostCenterMasterException if a matching gl cost center master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlCostCenterMaster findByNdc8_First(
        java.lang.String ndc8,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlCostCenterMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first gl cost center master in the ordered set where ndc8 = &#63;.
    *
    * @param ndc8 the ndc8
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching gl cost center master, or <code>null</code> if a matching gl cost center master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlCostCenterMaster fetchByNdc8_First(
        java.lang.String ndc8,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last gl cost center master in the ordered set where ndc8 = &#63;.
    *
    * @param ndc8 the ndc8
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching gl cost center master
    * @throws com.stpl.app.NoSuchGlCostCenterMasterException if a matching gl cost center master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlCostCenterMaster findByNdc8_Last(
        java.lang.String ndc8,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlCostCenterMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last gl cost center master in the ordered set where ndc8 = &#63;.
    *
    * @param ndc8 the ndc8
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching gl cost center master, or <code>null</code> if a matching gl cost center master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlCostCenterMaster fetchByNdc8_Last(
        java.lang.String ndc8,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the gl cost center masters before and after the current gl cost center master in the ordered set where ndc8 = &#63;.
    *
    * @param glCostCenterMasterSid the primary key of the current gl cost center master
    * @param ndc8 the ndc8
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next gl cost center master
    * @throws com.stpl.app.NoSuchGlCostCenterMasterException if a gl cost center master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlCostCenterMaster[] findByNdc8_PrevAndNext(
        int glCostCenterMasterSid, java.lang.String ndc8,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlCostCenterMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the gl cost center masters where ndc8 = &#63; from the database.
    *
    * @param ndc8 the ndc8
    * @throws SystemException if a system exception occurred
    */
    public void removeByNdc8(java.lang.String ndc8)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of gl cost center masters where ndc8 = &#63;.
    *
    * @param ndc8 the ndc8
    * @return the number of matching gl cost center masters
    * @throws SystemException if a system exception occurred
    */
    public int countByNdc8(java.lang.String ndc8)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the gl cost center masters where companyCode = &#63;.
    *
    * @param companyCode the company code
    * @return the matching gl cost center masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GlCostCenterMaster> findByCompanyCode(
        java.lang.String companyCode)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the gl cost center masters where companyCode = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyCode the company code
    * @param start the lower bound of the range of gl cost center masters
    * @param end the upper bound of the range of gl cost center masters (not inclusive)
    * @return the range of matching gl cost center masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GlCostCenterMaster> findByCompanyCode(
        java.lang.String companyCode, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the gl cost center masters where companyCode = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyCode the company code
    * @param start the lower bound of the range of gl cost center masters
    * @param end the upper bound of the range of gl cost center masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching gl cost center masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GlCostCenterMaster> findByCompanyCode(
        java.lang.String companyCode, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first gl cost center master in the ordered set where companyCode = &#63;.
    *
    * @param companyCode the company code
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching gl cost center master
    * @throws com.stpl.app.NoSuchGlCostCenterMasterException if a matching gl cost center master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlCostCenterMaster findByCompanyCode_First(
        java.lang.String companyCode,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlCostCenterMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first gl cost center master in the ordered set where companyCode = &#63;.
    *
    * @param companyCode the company code
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching gl cost center master, or <code>null</code> if a matching gl cost center master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlCostCenterMaster fetchByCompanyCode_First(
        java.lang.String companyCode,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last gl cost center master in the ordered set where companyCode = &#63;.
    *
    * @param companyCode the company code
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching gl cost center master
    * @throws com.stpl.app.NoSuchGlCostCenterMasterException if a matching gl cost center master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlCostCenterMaster findByCompanyCode_Last(
        java.lang.String companyCode,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlCostCenterMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last gl cost center master in the ordered set where companyCode = &#63;.
    *
    * @param companyCode the company code
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching gl cost center master, or <code>null</code> if a matching gl cost center master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlCostCenterMaster fetchByCompanyCode_Last(
        java.lang.String companyCode,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the gl cost center masters before and after the current gl cost center master in the ordered set where companyCode = &#63;.
    *
    * @param glCostCenterMasterSid the primary key of the current gl cost center master
    * @param companyCode the company code
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next gl cost center master
    * @throws com.stpl.app.NoSuchGlCostCenterMasterException if a gl cost center master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlCostCenterMaster[] findByCompanyCode_PrevAndNext(
        int glCostCenterMasterSid, java.lang.String companyCode,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlCostCenterMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the gl cost center masters where companyCode = &#63; from the database.
    *
    * @param companyCode the company code
    * @throws SystemException if a system exception occurred
    */
    public void removeByCompanyCode(java.lang.String companyCode)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of gl cost center masters where companyCode = &#63;.
    *
    * @param companyCode the company code
    * @return the number of matching gl cost center masters
    * @throws SystemException if a system exception occurred
    */
    public int countByCompanyCode(java.lang.String companyCode)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the gl cost center masters where companyCostCenter = &#63; and ndc8 = &#63; and companyCode = &#63;.
    *
    * @param companyCostCenter the company cost center
    * @param ndc8 the ndc8
    * @param companyCode the company code
    * @return the matching gl cost center masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GlCostCenterMaster> findByGlCostCenterUnique(
        java.lang.String companyCostCenter, java.lang.String ndc8,
        java.lang.String companyCode)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the gl cost center masters where companyCostCenter = &#63; and ndc8 = &#63; and companyCode = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyCostCenter the company cost center
    * @param ndc8 the ndc8
    * @param companyCode the company code
    * @param start the lower bound of the range of gl cost center masters
    * @param end the upper bound of the range of gl cost center masters (not inclusive)
    * @return the range of matching gl cost center masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GlCostCenterMaster> findByGlCostCenterUnique(
        java.lang.String companyCostCenter, java.lang.String ndc8,
        java.lang.String companyCode, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the gl cost center masters where companyCostCenter = &#63; and ndc8 = &#63; and companyCode = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyCostCenter the company cost center
    * @param ndc8 the ndc8
    * @param companyCode the company code
    * @param start the lower bound of the range of gl cost center masters
    * @param end the upper bound of the range of gl cost center masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching gl cost center masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GlCostCenterMaster> findByGlCostCenterUnique(
        java.lang.String companyCostCenter, java.lang.String ndc8,
        java.lang.String companyCode, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first gl cost center master in the ordered set where companyCostCenter = &#63; and ndc8 = &#63; and companyCode = &#63;.
    *
    * @param companyCostCenter the company cost center
    * @param ndc8 the ndc8
    * @param companyCode the company code
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching gl cost center master
    * @throws com.stpl.app.NoSuchGlCostCenterMasterException if a matching gl cost center master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlCostCenterMaster findByGlCostCenterUnique_First(
        java.lang.String companyCostCenter, java.lang.String ndc8,
        java.lang.String companyCode,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlCostCenterMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first gl cost center master in the ordered set where companyCostCenter = &#63; and ndc8 = &#63; and companyCode = &#63;.
    *
    * @param companyCostCenter the company cost center
    * @param ndc8 the ndc8
    * @param companyCode the company code
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching gl cost center master, or <code>null</code> if a matching gl cost center master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlCostCenterMaster fetchByGlCostCenterUnique_First(
        java.lang.String companyCostCenter, java.lang.String ndc8,
        java.lang.String companyCode,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last gl cost center master in the ordered set where companyCostCenter = &#63; and ndc8 = &#63; and companyCode = &#63;.
    *
    * @param companyCostCenter the company cost center
    * @param ndc8 the ndc8
    * @param companyCode the company code
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching gl cost center master
    * @throws com.stpl.app.NoSuchGlCostCenterMasterException if a matching gl cost center master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlCostCenterMaster findByGlCostCenterUnique_Last(
        java.lang.String companyCostCenter, java.lang.String ndc8,
        java.lang.String companyCode,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlCostCenterMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last gl cost center master in the ordered set where companyCostCenter = &#63; and ndc8 = &#63; and companyCode = &#63;.
    *
    * @param companyCostCenter the company cost center
    * @param ndc8 the ndc8
    * @param companyCode the company code
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching gl cost center master, or <code>null</code> if a matching gl cost center master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlCostCenterMaster fetchByGlCostCenterUnique_Last(
        java.lang.String companyCostCenter, java.lang.String ndc8,
        java.lang.String companyCode,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the gl cost center masters before and after the current gl cost center master in the ordered set where companyCostCenter = &#63; and ndc8 = &#63; and companyCode = &#63;.
    *
    * @param glCostCenterMasterSid the primary key of the current gl cost center master
    * @param companyCostCenter the company cost center
    * @param ndc8 the ndc8
    * @param companyCode the company code
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next gl cost center master
    * @throws com.stpl.app.NoSuchGlCostCenterMasterException if a gl cost center master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlCostCenterMaster[] findByGlCostCenterUnique_PrevAndNext(
        int glCostCenterMasterSid, java.lang.String companyCostCenter,
        java.lang.String ndc8, java.lang.String companyCode,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchGlCostCenterMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the gl cost center masters where companyCostCenter = &#63; and ndc8 = &#63; and companyCode = &#63; from the database.
    *
    * @param companyCostCenter the company cost center
    * @param ndc8 the ndc8
    * @param companyCode the company code
    * @throws SystemException if a system exception occurred
    */
    public void removeByGlCostCenterUnique(java.lang.String companyCostCenter,
        java.lang.String ndc8, java.lang.String companyCode)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of gl cost center masters where companyCostCenter = &#63; and ndc8 = &#63; and companyCode = &#63;.
    *
    * @param companyCostCenter the company cost center
    * @param ndc8 the ndc8
    * @param companyCode the company code
    * @return the number of matching gl cost center masters
    * @throws SystemException if a system exception occurred
    */
    public int countByGlCostCenterUnique(java.lang.String companyCostCenter,
        java.lang.String ndc8, java.lang.String companyCode)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Caches the gl cost center master in the entity cache if it is enabled.
    *
    * @param glCostCenterMaster the gl cost center master
    */
    public void cacheResult(
        com.stpl.app.model.GlCostCenterMaster glCostCenterMaster);

    /**
    * Caches the gl cost center masters in the entity cache if it is enabled.
    *
    * @param glCostCenterMasters the gl cost center masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.GlCostCenterMaster> glCostCenterMasters);

    /**
    * Creates a new gl cost center master with the primary key. Does not add the gl cost center master to the database.
    *
    * @param glCostCenterMasterSid the primary key for the new gl cost center master
    * @return the new gl cost center master
    */
    public com.stpl.app.model.GlCostCenterMaster create(
        int glCostCenterMasterSid);

    /**
    * Removes the gl cost center master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param glCostCenterMasterSid the primary key of the gl cost center master
    * @return the gl cost center master that was removed
    * @throws com.stpl.app.NoSuchGlCostCenterMasterException if a gl cost center master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlCostCenterMaster remove(
        int glCostCenterMasterSid)
        throws com.stpl.app.NoSuchGlCostCenterMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.GlCostCenterMaster updateImpl(
        com.stpl.app.model.GlCostCenterMaster glCostCenterMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the gl cost center master with the primary key or throws a {@link com.stpl.app.NoSuchGlCostCenterMasterException} if it could not be found.
    *
    * @param glCostCenterMasterSid the primary key of the gl cost center master
    * @return the gl cost center master
    * @throws com.stpl.app.NoSuchGlCostCenterMasterException if a gl cost center master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlCostCenterMaster findByPrimaryKey(
        int glCostCenterMasterSid)
        throws com.stpl.app.NoSuchGlCostCenterMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the gl cost center master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param glCostCenterMasterSid the primary key of the gl cost center master
    * @return the gl cost center master, or <code>null</code> if a gl cost center master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GlCostCenterMaster fetchByPrimaryKey(
        int glCostCenterMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the gl cost center masters.
    *
    * @return the gl cost center masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GlCostCenterMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the gl cost center masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of gl cost center masters
    * @param end the upper bound of the range of gl cost center masters (not inclusive)
    * @return the range of gl cost center masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GlCostCenterMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the gl cost center masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of gl cost center masters
    * @param end the upper bound of the range of gl cost center masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of gl cost center masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GlCostCenterMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the gl cost center masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of gl cost center masters.
    *
    * @return the number of gl cost center masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
