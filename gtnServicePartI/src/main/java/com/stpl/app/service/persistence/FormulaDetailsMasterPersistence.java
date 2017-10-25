package com.stpl.app.service.persistence;

import com.stpl.app.model.FormulaDetailsMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the formula details master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see FormulaDetailsMasterPersistenceImpl
 * @see FormulaDetailsMasterUtil
 * @generated
 */
public interface FormulaDetailsMasterPersistence extends BasePersistence<FormulaDetailsMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link FormulaDetailsMasterUtil} to access the formula details master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the formula details masters where formulaId = &#63;.
    *
    * @param formulaId the formula ID
    * @return the matching formula details masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.FormulaDetailsMaster> findByFormulaId(
        java.lang.String formulaId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the formula details masters where formulaId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param formulaId the formula ID
    * @param start the lower bound of the range of formula details masters
    * @param end the upper bound of the range of formula details masters (not inclusive)
    * @return the range of matching formula details masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.FormulaDetailsMaster> findByFormulaId(
        java.lang.String formulaId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the formula details masters where formulaId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param formulaId the formula ID
    * @param start the lower bound of the range of formula details masters
    * @param end the upper bound of the range of formula details masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching formula details masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.FormulaDetailsMaster> findByFormulaId(
        java.lang.String formulaId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first formula details master in the ordered set where formulaId = &#63;.
    *
    * @param formulaId the formula ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching formula details master
    * @throws com.stpl.app.NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.FormulaDetailsMaster findByFormulaId_First(
        java.lang.String formulaId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first formula details master in the ordered set where formulaId = &#63;.
    *
    * @param formulaId the formula ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching formula details master, or <code>null</code> if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.FormulaDetailsMaster fetchByFormulaId_First(
        java.lang.String formulaId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last formula details master in the ordered set where formulaId = &#63;.
    *
    * @param formulaId the formula ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching formula details master
    * @throws com.stpl.app.NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.FormulaDetailsMaster findByFormulaId_Last(
        java.lang.String formulaId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last formula details master in the ordered set where formulaId = &#63;.
    *
    * @param formulaId the formula ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching formula details master, or <code>null</code> if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.FormulaDetailsMaster fetchByFormulaId_Last(
        java.lang.String formulaId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the formula details masters before and after the current formula details master in the ordered set where formulaId = &#63;.
    *
    * @param formulaDetailsMasterSid the primary key of the current formula details master
    * @param formulaId the formula ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next formula details master
    * @throws com.stpl.app.NoSuchFormulaDetailsMasterException if a formula details master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.FormulaDetailsMaster[] findByFormulaId_PrevAndNext(
        int formulaDetailsMasterSid, java.lang.String formulaId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the formula details masters where formulaId = &#63; from the database.
    *
    * @param formulaId the formula ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByFormulaId(java.lang.String formulaId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of formula details masters where formulaId = &#63;.
    *
    * @param formulaId the formula ID
    * @return the number of matching formula details masters
    * @throws SystemException if a system exception occurred
    */
    public int countByFormulaId(java.lang.String formulaId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the formula details masters where formulaNo = &#63;.
    *
    * @param formulaNo the formula no
    * @return the matching formula details masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.FormulaDetailsMaster> findByFormulaNo(
        java.lang.String formulaNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the formula details masters where formulaNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param formulaNo the formula no
    * @param start the lower bound of the range of formula details masters
    * @param end the upper bound of the range of formula details masters (not inclusive)
    * @return the range of matching formula details masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.FormulaDetailsMaster> findByFormulaNo(
        java.lang.String formulaNo, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the formula details masters where formulaNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param formulaNo the formula no
    * @param start the lower bound of the range of formula details masters
    * @param end the upper bound of the range of formula details masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching formula details masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.FormulaDetailsMaster> findByFormulaNo(
        java.lang.String formulaNo, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first formula details master in the ordered set where formulaNo = &#63;.
    *
    * @param formulaNo the formula no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching formula details master
    * @throws com.stpl.app.NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.FormulaDetailsMaster findByFormulaNo_First(
        java.lang.String formulaNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first formula details master in the ordered set where formulaNo = &#63;.
    *
    * @param formulaNo the formula no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching formula details master, or <code>null</code> if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.FormulaDetailsMaster fetchByFormulaNo_First(
        java.lang.String formulaNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last formula details master in the ordered set where formulaNo = &#63;.
    *
    * @param formulaNo the formula no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching formula details master
    * @throws com.stpl.app.NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.FormulaDetailsMaster findByFormulaNo_Last(
        java.lang.String formulaNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last formula details master in the ordered set where formulaNo = &#63;.
    *
    * @param formulaNo the formula no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching formula details master, or <code>null</code> if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.FormulaDetailsMaster fetchByFormulaNo_Last(
        java.lang.String formulaNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the formula details masters before and after the current formula details master in the ordered set where formulaNo = &#63;.
    *
    * @param formulaDetailsMasterSid the primary key of the current formula details master
    * @param formulaNo the formula no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next formula details master
    * @throws com.stpl.app.NoSuchFormulaDetailsMasterException if a formula details master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.FormulaDetailsMaster[] findByFormulaNo_PrevAndNext(
        int formulaDetailsMasterSid, java.lang.String formulaNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the formula details masters where formulaNo = &#63; from the database.
    *
    * @param formulaNo the formula no
    * @throws SystemException if a system exception occurred
    */
    public void removeByFormulaNo(java.lang.String formulaNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of formula details masters where formulaNo = &#63;.
    *
    * @param formulaNo the formula no
    * @return the number of matching formula details masters
    * @throws SystemException if a system exception occurred
    */
    public int countByFormulaNo(java.lang.String formulaNo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the formula details masters where itemId = &#63;.
    *
    * @param itemId the item ID
    * @return the matching formula details masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.FormulaDetailsMaster> findByItemId(
        java.lang.String itemId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the formula details masters where itemId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemId the item ID
    * @param start the lower bound of the range of formula details masters
    * @param end the upper bound of the range of formula details masters (not inclusive)
    * @return the range of matching formula details masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.FormulaDetailsMaster> findByItemId(
        java.lang.String itemId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the formula details masters where itemId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemId the item ID
    * @param start the lower bound of the range of formula details masters
    * @param end the upper bound of the range of formula details masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching formula details masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.FormulaDetailsMaster> findByItemId(
        java.lang.String itemId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first formula details master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching formula details master
    * @throws com.stpl.app.NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.FormulaDetailsMaster findByItemId_First(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first formula details master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching formula details master, or <code>null</code> if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.FormulaDetailsMaster fetchByItemId_First(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last formula details master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching formula details master
    * @throws com.stpl.app.NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.FormulaDetailsMaster findByItemId_Last(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last formula details master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching formula details master, or <code>null</code> if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.FormulaDetailsMaster fetchByItemId_Last(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the formula details masters before and after the current formula details master in the ordered set where itemId = &#63;.
    *
    * @param formulaDetailsMasterSid the primary key of the current formula details master
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next formula details master
    * @throws com.stpl.app.NoSuchFormulaDetailsMasterException if a formula details master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.FormulaDetailsMaster[] findByItemId_PrevAndNext(
        int formulaDetailsMasterSid, java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the formula details masters where itemId = &#63; from the database.
    *
    * @param itemId the item ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByItemId(java.lang.String itemId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of formula details masters where itemId = &#63;.
    *
    * @param itemId the item ID
    * @return the number of matching formula details masters
    * @throws SystemException if a system exception occurred
    */
    public int countByItemId(java.lang.String itemId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the formula details masters where companyId = &#63;.
    *
    * @param companyId the company ID
    * @return the matching formula details masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.FormulaDetailsMaster> findByCompanyId(
        java.lang.String companyId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the formula details masters where companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyId the company ID
    * @param start the lower bound of the range of formula details masters
    * @param end the upper bound of the range of formula details masters (not inclusive)
    * @return the range of matching formula details masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.FormulaDetailsMaster> findByCompanyId(
        java.lang.String companyId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the formula details masters where companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyId the company ID
    * @param start the lower bound of the range of formula details masters
    * @param end the upper bound of the range of formula details masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching formula details masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.FormulaDetailsMaster> findByCompanyId(
        java.lang.String companyId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first formula details master in the ordered set where companyId = &#63;.
    *
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching formula details master
    * @throws com.stpl.app.NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.FormulaDetailsMaster findByCompanyId_First(
        java.lang.String companyId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first formula details master in the ordered set where companyId = &#63;.
    *
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching formula details master, or <code>null</code> if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.FormulaDetailsMaster fetchByCompanyId_First(
        java.lang.String companyId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last formula details master in the ordered set where companyId = &#63;.
    *
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching formula details master
    * @throws com.stpl.app.NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.FormulaDetailsMaster findByCompanyId_Last(
        java.lang.String companyId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last formula details master in the ordered set where companyId = &#63;.
    *
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching formula details master, or <code>null</code> if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.FormulaDetailsMaster fetchByCompanyId_Last(
        java.lang.String companyId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the formula details masters before and after the current formula details master in the ordered set where companyId = &#63;.
    *
    * @param formulaDetailsMasterSid the primary key of the current formula details master
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next formula details master
    * @throws com.stpl.app.NoSuchFormulaDetailsMasterException if a formula details master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.FormulaDetailsMaster[] findByCompanyId_PrevAndNext(
        int formulaDetailsMasterSid, java.lang.String companyId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the formula details masters where companyId = &#63; from the database.
    *
    * @param companyId the company ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByCompanyId(java.lang.String companyId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of formula details masters where companyId = &#63;.
    *
    * @param companyId the company ID
    * @return the number of matching formula details masters
    * @throws SystemException if a system exception occurred
    */
    public int countByCompanyId(java.lang.String companyId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the formula details masters where startDate = &#63;.
    *
    * @param startDate the start date
    * @return the matching formula details masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.FormulaDetailsMaster> findByStartDate(
        java.util.Date startDate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the formula details masters where startDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param startDate the start date
    * @param start the lower bound of the range of formula details masters
    * @param end the upper bound of the range of formula details masters (not inclusive)
    * @return the range of matching formula details masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.FormulaDetailsMaster> findByStartDate(
        java.util.Date startDate, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the formula details masters where startDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param startDate the start date
    * @param start the lower bound of the range of formula details masters
    * @param end the upper bound of the range of formula details masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching formula details masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.FormulaDetailsMaster> findByStartDate(
        java.util.Date startDate, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first formula details master in the ordered set where startDate = &#63;.
    *
    * @param startDate the start date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching formula details master
    * @throws com.stpl.app.NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.FormulaDetailsMaster findByStartDate_First(
        java.util.Date startDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first formula details master in the ordered set where startDate = &#63;.
    *
    * @param startDate the start date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching formula details master, or <code>null</code> if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.FormulaDetailsMaster fetchByStartDate_First(
        java.util.Date startDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last formula details master in the ordered set where startDate = &#63;.
    *
    * @param startDate the start date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching formula details master
    * @throws com.stpl.app.NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.FormulaDetailsMaster findByStartDate_Last(
        java.util.Date startDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last formula details master in the ordered set where startDate = &#63;.
    *
    * @param startDate the start date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching formula details master, or <code>null</code> if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.FormulaDetailsMaster fetchByStartDate_Last(
        java.util.Date startDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the formula details masters before and after the current formula details master in the ordered set where startDate = &#63;.
    *
    * @param formulaDetailsMasterSid the primary key of the current formula details master
    * @param startDate the start date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next formula details master
    * @throws com.stpl.app.NoSuchFormulaDetailsMasterException if a formula details master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.FormulaDetailsMaster[] findByStartDate_PrevAndNext(
        int formulaDetailsMasterSid, java.util.Date startDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the formula details masters where startDate = &#63; from the database.
    *
    * @param startDate the start date
    * @throws SystemException if a system exception occurred
    */
    public void removeByStartDate(java.util.Date startDate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of formula details masters where startDate = &#63;.
    *
    * @param startDate the start date
    * @return the number of matching formula details masters
    * @throws SystemException if a system exception occurred
    */
    public int countByStartDate(java.util.Date startDate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the formula details masters where endDate = &#63;.
    *
    * @param endDate the end date
    * @return the matching formula details masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.FormulaDetailsMaster> findByEndDate(
        java.util.Date endDate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the formula details masters where endDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param endDate the end date
    * @param start the lower bound of the range of formula details masters
    * @param end the upper bound of the range of formula details masters (not inclusive)
    * @return the range of matching formula details masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.FormulaDetailsMaster> findByEndDate(
        java.util.Date endDate, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the formula details masters where endDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param endDate the end date
    * @param start the lower bound of the range of formula details masters
    * @param end the upper bound of the range of formula details masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching formula details masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.FormulaDetailsMaster> findByEndDate(
        java.util.Date endDate, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first formula details master in the ordered set where endDate = &#63;.
    *
    * @param endDate the end date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching formula details master
    * @throws com.stpl.app.NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.FormulaDetailsMaster findByEndDate_First(
        java.util.Date endDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first formula details master in the ordered set where endDate = &#63;.
    *
    * @param endDate the end date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching formula details master, or <code>null</code> if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.FormulaDetailsMaster fetchByEndDate_First(
        java.util.Date endDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last formula details master in the ordered set where endDate = &#63;.
    *
    * @param endDate the end date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching formula details master
    * @throws com.stpl.app.NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.FormulaDetailsMaster findByEndDate_Last(
        java.util.Date endDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last formula details master in the ordered set where endDate = &#63;.
    *
    * @param endDate the end date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching formula details master, or <code>null</code> if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.FormulaDetailsMaster fetchByEndDate_Last(
        java.util.Date endDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the formula details masters before and after the current formula details master in the ordered set where endDate = &#63;.
    *
    * @param formulaDetailsMasterSid the primary key of the current formula details master
    * @param endDate the end date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next formula details master
    * @throws com.stpl.app.NoSuchFormulaDetailsMasterException if a formula details master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.FormulaDetailsMaster[] findByEndDate_PrevAndNext(
        int formulaDetailsMasterSid, java.util.Date endDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the formula details masters where endDate = &#63; from the database.
    *
    * @param endDate the end date
    * @throws SystemException if a system exception occurred
    */
    public void removeByEndDate(java.util.Date endDate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of formula details masters where endDate = &#63;.
    *
    * @param endDate the end date
    * @return the number of matching formula details masters
    * @throws SystemException if a system exception occurred
    */
    public int countByEndDate(java.util.Date endDate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the formula details masters where formulaId = &#63;.
    *
    * @param formulaId the formula ID
    * @return the matching formula details masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.FormulaDetailsMaster> findByFormulaDetailsUnique(
        java.lang.String formulaId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the formula details masters where formulaId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param formulaId the formula ID
    * @param start the lower bound of the range of formula details masters
    * @param end the upper bound of the range of formula details masters (not inclusive)
    * @return the range of matching formula details masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.FormulaDetailsMaster> findByFormulaDetailsUnique(
        java.lang.String formulaId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the formula details masters where formulaId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param formulaId the formula ID
    * @param start the lower bound of the range of formula details masters
    * @param end the upper bound of the range of formula details masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching formula details masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.FormulaDetailsMaster> findByFormulaDetailsUnique(
        java.lang.String formulaId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first formula details master in the ordered set where formulaId = &#63;.
    *
    * @param formulaId the formula ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching formula details master
    * @throws com.stpl.app.NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.FormulaDetailsMaster findByFormulaDetailsUnique_First(
        java.lang.String formulaId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first formula details master in the ordered set where formulaId = &#63;.
    *
    * @param formulaId the formula ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching formula details master, or <code>null</code> if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.FormulaDetailsMaster fetchByFormulaDetailsUnique_First(
        java.lang.String formulaId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last formula details master in the ordered set where formulaId = &#63;.
    *
    * @param formulaId the formula ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching formula details master
    * @throws com.stpl.app.NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.FormulaDetailsMaster findByFormulaDetailsUnique_Last(
        java.lang.String formulaId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last formula details master in the ordered set where formulaId = &#63;.
    *
    * @param formulaId the formula ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching formula details master, or <code>null</code> if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.FormulaDetailsMaster fetchByFormulaDetailsUnique_Last(
        java.lang.String formulaId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the formula details masters before and after the current formula details master in the ordered set where formulaId = &#63;.
    *
    * @param formulaDetailsMasterSid the primary key of the current formula details master
    * @param formulaId the formula ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next formula details master
    * @throws com.stpl.app.NoSuchFormulaDetailsMasterException if a formula details master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.FormulaDetailsMaster[] findByFormulaDetailsUnique_PrevAndNext(
        int formulaDetailsMasterSid, java.lang.String formulaId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the formula details masters where formulaId = &#63; from the database.
    *
    * @param formulaId the formula ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByFormulaDetailsUnique(java.lang.String formulaId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of formula details masters where formulaId = &#63;.
    *
    * @param formulaId the formula ID
    * @return the number of matching formula details masters
    * @throws SystemException if a system exception occurred
    */
    public int countByFormulaDetailsUnique(java.lang.String formulaId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Caches the formula details master in the entity cache if it is enabled.
    *
    * @param formulaDetailsMaster the formula details master
    */
    public void cacheResult(
        com.stpl.app.model.FormulaDetailsMaster formulaDetailsMaster);

    /**
    * Caches the formula details masters in the entity cache if it is enabled.
    *
    * @param formulaDetailsMasters the formula details masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.FormulaDetailsMaster> formulaDetailsMasters);

    /**
    * Creates a new formula details master with the primary key. Does not add the formula details master to the database.
    *
    * @param formulaDetailsMasterSid the primary key for the new formula details master
    * @return the new formula details master
    */
    public com.stpl.app.model.FormulaDetailsMaster create(
        int formulaDetailsMasterSid);

    /**
    * Removes the formula details master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param formulaDetailsMasterSid the primary key of the formula details master
    * @return the formula details master that was removed
    * @throws com.stpl.app.NoSuchFormulaDetailsMasterException if a formula details master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.FormulaDetailsMaster remove(
        int formulaDetailsMasterSid)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.FormulaDetailsMaster updateImpl(
        com.stpl.app.model.FormulaDetailsMaster formulaDetailsMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the formula details master with the primary key or throws a {@link com.stpl.app.NoSuchFormulaDetailsMasterException} if it could not be found.
    *
    * @param formulaDetailsMasterSid the primary key of the formula details master
    * @return the formula details master
    * @throws com.stpl.app.NoSuchFormulaDetailsMasterException if a formula details master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.FormulaDetailsMaster findByPrimaryKey(
        int formulaDetailsMasterSid)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the formula details master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param formulaDetailsMasterSid the primary key of the formula details master
    * @return the formula details master, or <code>null</code> if a formula details master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.FormulaDetailsMaster fetchByPrimaryKey(
        int formulaDetailsMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the formula details masters.
    *
    * @return the formula details masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.FormulaDetailsMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the formula details masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of formula details masters
    * @param end the upper bound of the range of formula details masters (not inclusive)
    * @return the range of formula details masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.FormulaDetailsMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the formula details masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of formula details masters
    * @param end the upper bound of the range of formula details masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of formula details masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.FormulaDetailsMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the formula details masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of formula details masters.
    *
    * @return the number of formula details masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
