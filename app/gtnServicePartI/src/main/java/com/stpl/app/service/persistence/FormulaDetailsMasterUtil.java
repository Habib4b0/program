package com.stpl.app.service.persistence;

import com.stpl.app.model.FormulaDetailsMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the formula details master service. This utility wraps {@link FormulaDetailsMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see FormulaDetailsMasterPersistence
 * @see FormulaDetailsMasterPersistenceImpl
 * @generated
 */
public class FormulaDetailsMasterUtil {
    private static FormulaDetailsMasterPersistence _persistence;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#clearCache()
     */
    public static void clearCache() {
        getPersistence().clearCache();
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#clearCache(com.stpl.portal.model.BaseModel)
     */
    public static void clearCache(FormulaDetailsMaster formulaDetailsMaster) {
        getPersistence().clearCache(formulaDetailsMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
     */
    public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().countWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
     */
    public static List<FormulaDetailsMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<FormulaDetailsMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<FormulaDetailsMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static FormulaDetailsMaster update(
        FormulaDetailsMaster formulaDetailsMaster) throws SystemException {
        return getPersistence().update(formulaDetailsMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static FormulaDetailsMaster update(
        FormulaDetailsMaster formulaDetailsMaster, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(formulaDetailsMaster, serviceContext);
    }

    /**
    * Returns all the formula details masters where formulaId = &#63;.
    *
    * @param formulaId the formula ID
    * @return the matching formula details masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.FormulaDetailsMaster> findByFormulaId(
        java.lang.String formulaId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByFormulaId(formulaId);
    }

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
    public static java.util.List<com.stpl.app.model.FormulaDetailsMaster> findByFormulaId(
        java.lang.String formulaId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByFormulaId(formulaId, start, end);
    }

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
    public static java.util.List<com.stpl.app.model.FormulaDetailsMaster> findByFormulaId(
        java.lang.String formulaId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByFormulaId(formulaId, start, end, orderByComparator);
    }

    /**
    * Returns the first formula details master in the ordered set where formulaId = &#63;.
    *
    * @param formulaId the formula ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching formula details master
    * @throws com.stpl.app.NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.FormulaDetailsMaster findByFormulaId_First(
        java.lang.String formulaId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByFormulaId_First(formulaId, orderByComparator);
    }

    /**
    * Returns the first formula details master in the ordered set where formulaId = &#63;.
    *
    * @param formulaId the formula ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching formula details master, or <code>null</code> if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.FormulaDetailsMaster fetchByFormulaId_First(
        java.lang.String formulaId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByFormulaId_First(formulaId, orderByComparator);
    }

    /**
    * Returns the last formula details master in the ordered set where formulaId = &#63;.
    *
    * @param formulaId the formula ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching formula details master
    * @throws com.stpl.app.NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.FormulaDetailsMaster findByFormulaId_Last(
        java.lang.String formulaId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByFormulaId_Last(formulaId, orderByComparator);
    }

    /**
    * Returns the last formula details master in the ordered set where formulaId = &#63;.
    *
    * @param formulaId the formula ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching formula details master, or <code>null</code> if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.FormulaDetailsMaster fetchByFormulaId_Last(
        java.lang.String formulaId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByFormulaId_Last(formulaId, orderByComparator);
    }

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
    public static com.stpl.app.model.FormulaDetailsMaster[] findByFormulaId_PrevAndNext(
        int formulaDetailsMasterSid, java.lang.String formulaId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByFormulaId_PrevAndNext(formulaDetailsMasterSid,
            formulaId, orderByComparator);
    }

    /**
    * Removes all the formula details masters where formulaId = &#63; from the database.
    *
    * @param formulaId the formula ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByFormulaId(java.lang.String formulaId)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByFormulaId(formulaId);
    }

    /**
    * Returns the number of formula details masters where formulaId = &#63;.
    *
    * @param formulaId the formula ID
    * @return the number of matching formula details masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByFormulaId(java.lang.String formulaId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByFormulaId(formulaId);
    }

    /**
    * Returns all the formula details masters where formulaNo = &#63;.
    *
    * @param formulaNo the formula no
    * @return the matching formula details masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.FormulaDetailsMaster> findByFormulaNo(
        java.lang.String formulaNo)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByFormulaNo(formulaNo);
    }

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
    public static java.util.List<com.stpl.app.model.FormulaDetailsMaster> findByFormulaNo(
        java.lang.String formulaNo, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByFormulaNo(formulaNo, start, end);
    }

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
    public static java.util.List<com.stpl.app.model.FormulaDetailsMaster> findByFormulaNo(
        java.lang.String formulaNo, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByFormulaNo(formulaNo, start, end, orderByComparator);
    }

    /**
    * Returns the first formula details master in the ordered set where formulaNo = &#63;.
    *
    * @param formulaNo the formula no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching formula details master
    * @throws com.stpl.app.NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.FormulaDetailsMaster findByFormulaNo_First(
        java.lang.String formulaNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByFormulaNo_First(formulaNo, orderByComparator);
    }

    /**
    * Returns the first formula details master in the ordered set where formulaNo = &#63;.
    *
    * @param formulaNo the formula no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching formula details master, or <code>null</code> if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.FormulaDetailsMaster fetchByFormulaNo_First(
        java.lang.String formulaNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByFormulaNo_First(formulaNo, orderByComparator);
    }

    /**
    * Returns the last formula details master in the ordered set where formulaNo = &#63;.
    *
    * @param formulaNo the formula no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching formula details master
    * @throws com.stpl.app.NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.FormulaDetailsMaster findByFormulaNo_Last(
        java.lang.String formulaNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByFormulaNo_Last(formulaNo, orderByComparator);
    }

    /**
    * Returns the last formula details master in the ordered set where formulaNo = &#63;.
    *
    * @param formulaNo the formula no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching formula details master, or <code>null</code> if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.FormulaDetailsMaster fetchByFormulaNo_Last(
        java.lang.String formulaNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByFormulaNo_Last(formulaNo, orderByComparator);
    }

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
    public static com.stpl.app.model.FormulaDetailsMaster[] findByFormulaNo_PrevAndNext(
        int formulaDetailsMasterSid, java.lang.String formulaNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByFormulaNo_PrevAndNext(formulaDetailsMasterSid,
            formulaNo, orderByComparator);
    }

    /**
    * Removes all the formula details masters where formulaNo = &#63; from the database.
    *
    * @param formulaNo the formula no
    * @throws SystemException if a system exception occurred
    */
    public static void removeByFormulaNo(java.lang.String formulaNo)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByFormulaNo(formulaNo);
    }

    /**
    * Returns the number of formula details masters where formulaNo = &#63;.
    *
    * @param formulaNo the formula no
    * @return the number of matching formula details masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByFormulaNo(java.lang.String formulaNo)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByFormulaNo(formulaNo);
    }

    /**
    * Returns all the formula details masters where itemId = &#63;.
    *
    * @param itemId the item ID
    * @return the matching formula details masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.FormulaDetailsMaster> findByItemId(
        java.lang.String itemId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByItemId(itemId);
    }

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
    public static java.util.List<com.stpl.app.model.FormulaDetailsMaster> findByItemId(
        java.lang.String itemId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByItemId(itemId, start, end);
    }

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
    public static java.util.List<com.stpl.app.model.FormulaDetailsMaster> findByItemId(
        java.lang.String itemId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemId(itemId, start, end, orderByComparator);
    }

    /**
    * Returns the first formula details master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching formula details master
    * @throws com.stpl.app.NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.FormulaDetailsMaster findByItemId_First(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByItemId_First(itemId, orderByComparator);
    }

    /**
    * Returns the first formula details master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching formula details master, or <code>null</code> if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.FormulaDetailsMaster fetchByItemId_First(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByItemId_First(itemId, orderByComparator);
    }

    /**
    * Returns the last formula details master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching formula details master
    * @throws com.stpl.app.NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.FormulaDetailsMaster findByItemId_Last(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByItemId_Last(itemId, orderByComparator);
    }

    /**
    * Returns the last formula details master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching formula details master, or <code>null</code> if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.FormulaDetailsMaster fetchByItemId_Last(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByItemId_Last(itemId, orderByComparator);
    }

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
    public static com.stpl.app.model.FormulaDetailsMaster[] findByItemId_PrevAndNext(
        int formulaDetailsMasterSid, java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemId_PrevAndNext(formulaDetailsMasterSid, itemId,
            orderByComparator);
    }

    /**
    * Removes all the formula details masters where itemId = &#63; from the database.
    *
    * @param itemId the item ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByItemId(java.lang.String itemId)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByItemId(itemId);
    }

    /**
    * Returns the number of formula details masters where itemId = &#63;.
    *
    * @param itemId the item ID
    * @return the number of matching formula details masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByItemId(java.lang.String itemId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByItemId(itemId);
    }

    /**
    * Returns all the formula details masters where companyId = &#63;.
    *
    * @param companyId the company ID
    * @return the matching formula details masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.FormulaDetailsMaster> findByCompanyId(
        java.lang.String companyId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByCompanyId(companyId);
    }

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
    public static java.util.List<com.stpl.app.model.FormulaDetailsMaster> findByCompanyId(
        java.lang.String companyId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByCompanyId(companyId, start, end);
    }

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
    public static java.util.List<com.stpl.app.model.FormulaDetailsMaster> findByCompanyId(
        java.lang.String companyId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyId(companyId, start, end, orderByComparator);
    }

    /**
    * Returns the first formula details master in the ordered set where companyId = &#63;.
    *
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching formula details master
    * @throws com.stpl.app.NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.FormulaDetailsMaster findByCompanyId_First(
        java.lang.String companyId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyId_First(companyId, orderByComparator);
    }

    /**
    * Returns the first formula details master in the ordered set where companyId = &#63;.
    *
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching formula details master, or <code>null</code> if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.FormulaDetailsMaster fetchByCompanyId_First(
        java.lang.String companyId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByCompanyId_First(companyId, orderByComparator);
    }

    /**
    * Returns the last formula details master in the ordered set where companyId = &#63;.
    *
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching formula details master
    * @throws com.stpl.app.NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.FormulaDetailsMaster findByCompanyId_Last(
        java.lang.String companyId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyId_Last(companyId, orderByComparator);
    }

    /**
    * Returns the last formula details master in the ordered set where companyId = &#63;.
    *
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching formula details master, or <code>null</code> if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.FormulaDetailsMaster fetchByCompanyId_Last(
        java.lang.String companyId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByCompanyId_Last(companyId, orderByComparator);
    }

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
    public static com.stpl.app.model.FormulaDetailsMaster[] findByCompanyId_PrevAndNext(
        int formulaDetailsMasterSid, java.lang.String companyId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyId_PrevAndNext(formulaDetailsMasterSid,
            companyId, orderByComparator);
    }

    /**
    * Removes all the formula details masters where companyId = &#63; from the database.
    *
    * @param companyId the company ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCompanyId(java.lang.String companyId)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByCompanyId(companyId);
    }

    /**
    * Returns the number of formula details masters where companyId = &#63;.
    *
    * @param companyId the company ID
    * @return the number of matching formula details masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByCompanyId(java.lang.String companyId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByCompanyId(companyId);
    }

    /**
    * Returns all the formula details masters where startDate = &#63;.
    *
    * @param startDate the start date
    * @return the matching formula details masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.FormulaDetailsMaster> findByStartDate(
        java.util.Date startDate)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByStartDate(startDate);
    }

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
    public static java.util.List<com.stpl.app.model.FormulaDetailsMaster> findByStartDate(
        java.util.Date startDate, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByStartDate(startDate, start, end);
    }

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
    public static java.util.List<com.stpl.app.model.FormulaDetailsMaster> findByStartDate(
        java.util.Date startDate, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByStartDate(startDate, start, end, orderByComparator);
    }

    /**
    * Returns the first formula details master in the ordered set where startDate = &#63;.
    *
    * @param startDate the start date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching formula details master
    * @throws com.stpl.app.NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.FormulaDetailsMaster findByStartDate_First(
        java.util.Date startDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByStartDate_First(startDate, orderByComparator);
    }

    /**
    * Returns the first formula details master in the ordered set where startDate = &#63;.
    *
    * @param startDate the start date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching formula details master, or <code>null</code> if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.FormulaDetailsMaster fetchByStartDate_First(
        java.util.Date startDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByStartDate_First(startDate, orderByComparator);
    }

    /**
    * Returns the last formula details master in the ordered set where startDate = &#63;.
    *
    * @param startDate the start date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching formula details master
    * @throws com.stpl.app.NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.FormulaDetailsMaster findByStartDate_Last(
        java.util.Date startDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByStartDate_Last(startDate, orderByComparator);
    }

    /**
    * Returns the last formula details master in the ordered set where startDate = &#63;.
    *
    * @param startDate the start date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching formula details master, or <code>null</code> if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.FormulaDetailsMaster fetchByStartDate_Last(
        java.util.Date startDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByStartDate_Last(startDate, orderByComparator);
    }

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
    public static com.stpl.app.model.FormulaDetailsMaster[] findByStartDate_PrevAndNext(
        int formulaDetailsMasterSid, java.util.Date startDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByStartDate_PrevAndNext(formulaDetailsMasterSid,
            startDate, orderByComparator);
    }

    /**
    * Removes all the formula details masters where startDate = &#63; from the database.
    *
    * @param startDate the start date
    * @throws SystemException if a system exception occurred
    */
    public static void removeByStartDate(java.util.Date startDate)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByStartDate(startDate);
    }

    /**
    * Returns the number of formula details masters where startDate = &#63;.
    *
    * @param startDate the start date
    * @return the number of matching formula details masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByStartDate(java.util.Date startDate)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByStartDate(startDate);
    }

    /**
    * Returns all the formula details masters where endDate = &#63;.
    *
    * @param endDate the end date
    * @return the matching formula details masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.FormulaDetailsMaster> findByEndDate(
        java.util.Date endDate)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByEndDate(endDate);
    }

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
    public static java.util.List<com.stpl.app.model.FormulaDetailsMaster> findByEndDate(
        java.util.Date endDate, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByEndDate(endDate, start, end);
    }

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
    public static java.util.List<com.stpl.app.model.FormulaDetailsMaster> findByEndDate(
        java.util.Date endDate, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByEndDate(endDate, start, end, orderByComparator);
    }

    /**
    * Returns the first formula details master in the ordered set where endDate = &#63;.
    *
    * @param endDate the end date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching formula details master
    * @throws com.stpl.app.NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.FormulaDetailsMaster findByEndDate_First(
        java.util.Date endDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByEndDate_First(endDate, orderByComparator);
    }

    /**
    * Returns the first formula details master in the ordered set where endDate = &#63;.
    *
    * @param endDate the end date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching formula details master, or <code>null</code> if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.FormulaDetailsMaster fetchByEndDate_First(
        java.util.Date endDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByEndDate_First(endDate, orderByComparator);
    }

    /**
    * Returns the last formula details master in the ordered set where endDate = &#63;.
    *
    * @param endDate the end date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching formula details master
    * @throws com.stpl.app.NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.FormulaDetailsMaster findByEndDate_Last(
        java.util.Date endDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByEndDate_Last(endDate, orderByComparator);
    }

    /**
    * Returns the last formula details master in the ordered set where endDate = &#63;.
    *
    * @param endDate the end date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching formula details master, or <code>null</code> if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.FormulaDetailsMaster fetchByEndDate_Last(
        java.util.Date endDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByEndDate_Last(endDate, orderByComparator);
    }

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
    public static com.stpl.app.model.FormulaDetailsMaster[] findByEndDate_PrevAndNext(
        int formulaDetailsMasterSid, java.util.Date endDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByEndDate_PrevAndNext(formulaDetailsMasterSid, endDate,
            orderByComparator);
    }

    /**
    * Removes all the formula details masters where endDate = &#63; from the database.
    *
    * @param endDate the end date
    * @throws SystemException if a system exception occurred
    */
    public static void removeByEndDate(java.util.Date endDate)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByEndDate(endDate);
    }

    /**
    * Returns the number of formula details masters where endDate = &#63;.
    *
    * @param endDate the end date
    * @return the number of matching formula details masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByEndDate(java.util.Date endDate)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByEndDate(endDate);
    }

    /**
    * Returns all the formula details masters where formulaId = &#63;.
    *
    * @param formulaId the formula ID
    * @return the matching formula details masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.FormulaDetailsMaster> findByFormulaDetailsUnique(
        java.lang.String formulaId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByFormulaDetailsUnique(formulaId);
    }

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
    public static java.util.List<com.stpl.app.model.FormulaDetailsMaster> findByFormulaDetailsUnique(
        java.lang.String formulaId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByFormulaDetailsUnique(formulaId, start, end);
    }

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
    public static java.util.List<com.stpl.app.model.FormulaDetailsMaster> findByFormulaDetailsUnique(
        java.lang.String formulaId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByFormulaDetailsUnique(formulaId, start, end,
            orderByComparator);
    }

    /**
    * Returns the first formula details master in the ordered set where formulaId = &#63;.
    *
    * @param formulaId the formula ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching formula details master
    * @throws com.stpl.app.NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.FormulaDetailsMaster findByFormulaDetailsUnique_First(
        java.lang.String formulaId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByFormulaDetailsUnique_First(formulaId,
            orderByComparator);
    }

    /**
    * Returns the first formula details master in the ordered set where formulaId = &#63;.
    *
    * @param formulaId the formula ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching formula details master, or <code>null</code> if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.FormulaDetailsMaster fetchByFormulaDetailsUnique_First(
        java.lang.String formulaId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByFormulaDetailsUnique_First(formulaId,
            orderByComparator);
    }

    /**
    * Returns the last formula details master in the ordered set where formulaId = &#63;.
    *
    * @param formulaId the formula ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching formula details master
    * @throws com.stpl.app.NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.FormulaDetailsMaster findByFormulaDetailsUnique_Last(
        java.lang.String formulaId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByFormulaDetailsUnique_Last(formulaId, orderByComparator);
    }

    /**
    * Returns the last formula details master in the ordered set where formulaId = &#63;.
    *
    * @param formulaId the formula ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching formula details master, or <code>null</code> if a matching formula details master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.FormulaDetailsMaster fetchByFormulaDetailsUnique_Last(
        java.lang.String formulaId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByFormulaDetailsUnique_Last(formulaId,
            orderByComparator);
    }

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
    public static com.stpl.app.model.FormulaDetailsMaster[] findByFormulaDetailsUnique_PrevAndNext(
        int formulaDetailsMasterSid, java.lang.String formulaId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByFormulaDetailsUnique_PrevAndNext(formulaDetailsMasterSid,
            formulaId, orderByComparator);
    }

    /**
    * Removes all the formula details masters where formulaId = &#63; from the database.
    *
    * @param formulaId the formula ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByFormulaDetailsUnique(java.lang.String formulaId)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByFormulaDetailsUnique(formulaId);
    }

    /**
    * Returns the number of formula details masters where formulaId = &#63;.
    *
    * @param formulaId the formula ID
    * @return the number of matching formula details masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByFormulaDetailsUnique(java.lang.String formulaId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByFormulaDetailsUnique(formulaId);
    }

    /**
    * Caches the formula details master in the entity cache if it is enabled.
    *
    * @param formulaDetailsMaster the formula details master
    */
    public static void cacheResult(
        com.stpl.app.model.FormulaDetailsMaster formulaDetailsMaster) {
        getPersistence().cacheResult(formulaDetailsMaster);
    }

    /**
    * Caches the formula details masters in the entity cache if it is enabled.
    *
    * @param formulaDetailsMasters the formula details masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.FormulaDetailsMaster> formulaDetailsMasters) {
        getPersistence().cacheResult(formulaDetailsMasters);
    }

    /**
    * Creates a new formula details master with the primary key. Does not add the formula details master to the database.
    *
    * @param formulaDetailsMasterSid the primary key for the new formula details master
    * @return the new formula details master
    */
    public static com.stpl.app.model.FormulaDetailsMaster create(
        int formulaDetailsMasterSid) {
        return getPersistence().create(formulaDetailsMasterSid);
    }

    /**
    * Removes the formula details master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param formulaDetailsMasterSid the primary key of the formula details master
    * @return the formula details master that was removed
    * @throws com.stpl.app.NoSuchFormulaDetailsMasterException if a formula details master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.FormulaDetailsMaster remove(
        int formulaDetailsMasterSid)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(formulaDetailsMasterSid);
    }

    public static com.stpl.app.model.FormulaDetailsMaster updateImpl(
        com.stpl.app.model.FormulaDetailsMaster formulaDetailsMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(formulaDetailsMaster);
    }

    /**
    * Returns the formula details master with the primary key or throws a {@link com.stpl.app.NoSuchFormulaDetailsMasterException} if it could not be found.
    *
    * @param formulaDetailsMasterSid the primary key of the formula details master
    * @return the formula details master
    * @throws com.stpl.app.NoSuchFormulaDetailsMasterException if a formula details master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.FormulaDetailsMaster findByPrimaryKey(
        int formulaDetailsMasterSid)
        throws com.stpl.app.NoSuchFormulaDetailsMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(formulaDetailsMasterSid);
    }

    /**
    * Returns the formula details master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param formulaDetailsMasterSid the primary key of the formula details master
    * @return the formula details master, or <code>null</code> if a formula details master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.FormulaDetailsMaster fetchByPrimaryKey(
        int formulaDetailsMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(formulaDetailsMasterSid);
    }

    /**
    * Returns all the formula details masters.
    *
    * @return the formula details masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.FormulaDetailsMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

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
    public static java.util.List<com.stpl.app.model.FormulaDetailsMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

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
    public static java.util.List<com.stpl.app.model.FormulaDetailsMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the formula details masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of formula details masters.
    *
    * @return the number of formula details masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static FormulaDetailsMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (FormulaDetailsMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    FormulaDetailsMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(FormulaDetailsMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(FormulaDetailsMasterPersistence persistence) {
    }
}
