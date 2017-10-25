package com.stpl.app.service.persistence;

import com.stpl.app.model.PsModel;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the ps model service. This utility wraps {@link PsModelPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see PsModelPersistence
 * @see PsModelPersistenceImpl
 * @generated
 */
public class PsModelUtil {
    private static PsModelPersistence _persistence;

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
    public static void clearCache(PsModel psModel) {
        getPersistence().clearCache(psModel);
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
    public static List<PsModel> findWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<PsModel> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<PsModel> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static PsModel update(PsModel psModel) throws SystemException {
        return getPersistence().update(psModel);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static PsModel update(PsModel psModel, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(psModel, serviceContext);
    }

    /**
    * Returns all the ps models where psId = &#63;.
    *
    * @param psId the ps ID
    * @return the matching ps models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.PsModel> findBypsId(
        java.lang.String psId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findBypsId(psId);
    }

    /**
    * Returns a range of all the ps models where psId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param psId the ps ID
    * @param start the lower bound of the range of ps models
    * @param end the upper bound of the range of ps models (not inclusive)
    * @return the range of matching ps models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.PsModel> findBypsId(
        java.lang.String psId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findBypsId(psId, start, end);
    }

    /**
    * Returns an ordered range of all the ps models where psId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param psId the ps ID
    * @param start the lower bound of the range of ps models
    * @param end the upper bound of the range of ps models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching ps models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.PsModel> findBypsId(
        java.lang.String psId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findBypsId(psId, start, end, orderByComparator);
    }

    /**
    * Returns the first ps model in the ordered set where psId = &#63;.
    *
    * @param psId the ps ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ps model
    * @throws com.stpl.app.NoSuchPsModelException if a matching ps model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.PsModel findBypsId_First(
        java.lang.String psId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchPsModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findBypsId_First(psId, orderByComparator);
    }

    /**
    * Returns the first ps model in the ordered set where psId = &#63;.
    *
    * @param psId the ps ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ps model, or <code>null</code> if a matching ps model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.PsModel fetchBypsId_First(
        java.lang.String psId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchBypsId_First(psId, orderByComparator);
    }

    /**
    * Returns the last ps model in the ordered set where psId = &#63;.
    *
    * @param psId the ps ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ps model
    * @throws com.stpl.app.NoSuchPsModelException if a matching ps model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.PsModel findBypsId_Last(
        java.lang.String psId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchPsModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findBypsId_Last(psId, orderByComparator);
    }

    /**
    * Returns the last ps model in the ordered set where psId = &#63;.
    *
    * @param psId the ps ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ps model, or <code>null</code> if a matching ps model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.PsModel fetchBypsId_Last(
        java.lang.String psId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchBypsId_Last(psId, orderByComparator);
    }

    /**
    * Returns the ps models before and after the current ps model in the ordered set where psId = &#63;.
    *
    * @param psModelSid the primary key of the current ps model
    * @param psId the ps ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next ps model
    * @throws com.stpl.app.NoSuchPsModelException if a ps model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.PsModel[] findBypsId_PrevAndNext(
        int psModelSid, java.lang.String psId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchPsModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBypsId_PrevAndNext(psModelSid, psId, orderByComparator);
    }

    /**
    * Removes all the ps models where psId = &#63; from the database.
    *
    * @param psId the ps ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeBypsId(java.lang.String psId)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeBypsId(psId);
    }

    /**
    * Returns the number of ps models where psId = &#63;.
    *
    * @param psId the ps ID
    * @return the number of matching ps models
    * @throws SystemException if a system exception occurred
    */
    public static int countBypsId(java.lang.String psId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countBypsId(psId);
    }

    /**
    * Returns all the ps models where psNo = &#63;.
    *
    * @param psNo the ps no
    * @return the matching ps models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.PsModel> findBypsNo(
        java.lang.String psNo)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findBypsNo(psNo);
    }

    /**
    * Returns a range of all the ps models where psNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param psNo the ps no
    * @param start the lower bound of the range of ps models
    * @param end the upper bound of the range of ps models (not inclusive)
    * @return the range of matching ps models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.PsModel> findBypsNo(
        java.lang.String psNo, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findBypsNo(psNo, start, end);
    }

    /**
    * Returns an ordered range of all the ps models where psNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param psNo the ps no
    * @param start the lower bound of the range of ps models
    * @param end the upper bound of the range of ps models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching ps models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.PsModel> findBypsNo(
        java.lang.String psNo, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findBypsNo(psNo, start, end, orderByComparator);
    }

    /**
    * Returns the first ps model in the ordered set where psNo = &#63;.
    *
    * @param psNo the ps no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ps model
    * @throws com.stpl.app.NoSuchPsModelException if a matching ps model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.PsModel findBypsNo_First(
        java.lang.String psNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchPsModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findBypsNo_First(psNo, orderByComparator);
    }

    /**
    * Returns the first ps model in the ordered set where psNo = &#63;.
    *
    * @param psNo the ps no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ps model, or <code>null</code> if a matching ps model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.PsModel fetchBypsNo_First(
        java.lang.String psNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchBypsNo_First(psNo, orderByComparator);
    }

    /**
    * Returns the last ps model in the ordered set where psNo = &#63;.
    *
    * @param psNo the ps no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ps model
    * @throws com.stpl.app.NoSuchPsModelException if a matching ps model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.PsModel findBypsNo_Last(
        java.lang.String psNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchPsModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findBypsNo_Last(psNo, orderByComparator);
    }

    /**
    * Returns the last ps model in the ordered set where psNo = &#63;.
    *
    * @param psNo the ps no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ps model, or <code>null</code> if a matching ps model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.PsModel fetchBypsNo_Last(
        java.lang.String psNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchBypsNo_Last(psNo, orderByComparator);
    }

    /**
    * Returns the ps models before and after the current ps model in the ordered set where psNo = &#63;.
    *
    * @param psModelSid the primary key of the current ps model
    * @param psNo the ps no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next ps model
    * @throws com.stpl.app.NoSuchPsModelException if a ps model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.PsModel[] findBypsNo_PrevAndNext(
        int psModelSid, java.lang.String psNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchPsModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBypsNo_PrevAndNext(psModelSid, psNo, orderByComparator);
    }

    /**
    * Removes all the ps models where psNo = &#63; from the database.
    *
    * @param psNo the ps no
    * @throws SystemException if a system exception occurred
    */
    public static void removeBypsNo(java.lang.String psNo)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeBypsNo(psNo);
    }

    /**
    * Returns the number of ps models where psNo = &#63;.
    *
    * @param psNo the ps no
    * @return the number of matching ps models
    * @throws SystemException if a system exception occurred
    */
    public static int countBypsNo(java.lang.String psNo)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countBypsNo(psNo);
    }

    /**
    * Returns all the ps models where psName = &#63;.
    *
    * @param psName the ps name
    * @return the matching ps models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.PsModel> findBypsName(
        java.lang.String psName)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findBypsName(psName);
    }

    /**
    * Returns a range of all the ps models where psName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param psName the ps name
    * @param start the lower bound of the range of ps models
    * @param end the upper bound of the range of ps models (not inclusive)
    * @return the range of matching ps models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.PsModel> findBypsName(
        java.lang.String psName, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findBypsName(psName, start, end);
    }

    /**
    * Returns an ordered range of all the ps models where psName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param psName the ps name
    * @param start the lower bound of the range of ps models
    * @param end the upper bound of the range of ps models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching ps models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.PsModel> findBypsName(
        java.lang.String psName, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBypsName(psName, start, end, orderByComparator);
    }

    /**
    * Returns the first ps model in the ordered set where psName = &#63;.
    *
    * @param psName the ps name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ps model
    * @throws com.stpl.app.NoSuchPsModelException if a matching ps model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.PsModel findBypsName_First(
        java.lang.String psName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchPsModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findBypsName_First(psName, orderByComparator);
    }

    /**
    * Returns the first ps model in the ordered set where psName = &#63;.
    *
    * @param psName the ps name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ps model, or <code>null</code> if a matching ps model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.PsModel fetchBypsName_First(
        java.lang.String psName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchBypsName_First(psName, orderByComparator);
    }

    /**
    * Returns the last ps model in the ordered set where psName = &#63;.
    *
    * @param psName the ps name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ps model
    * @throws com.stpl.app.NoSuchPsModelException if a matching ps model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.PsModel findBypsName_Last(
        java.lang.String psName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchPsModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findBypsName_Last(psName, orderByComparator);
    }

    /**
    * Returns the last ps model in the ordered set where psName = &#63;.
    *
    * @param psName the ps name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ps model, or <code>null</code> if a matching ps model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.PsModel fetchBypsName_Last(
        java.lang.String psName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchBypsName_Last(psName, orderByComparator);
    }

    /**
    * Returns the ps models before and after the current ps model in the ordered set where psName = &#63;.
    *
    * @param psModelSid the primary key of the current ps model
    * @param psName the ps name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next ps model
    * @throws com.stpl.app.NoSuchPsModelException if a ps model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.PsModel[] findBypsName_PrevAndNext(
        int psModelSid, java.lang.String psName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchPsModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBypsName_PrevAndNext(psModelSid, psName,
            orderByComparator);
    }

    /**
    * Removes all the ps models where psName = &#63; from the database.
    *
    * @param psName the ps name
    * @throws SystemException if a system exception occurred
    */
    public static void removeBypsName(java.lang.String psName)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeBypsName(psName);
    }

    /**
    * Returns the number of ps models where psName = &#63;.
    *
    * @param psName the ps name
    * @return the number of matching ps models
    * @throws SystemException if a system exception occurred
    */
    public static int countBypsName(java.lang.String psName)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countBypsName(psName);
    }

    /**
    * Returns all the ps models where psType = &#63;.
    *
    * @param psType the ps type
    * @return the matching ps models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.PsModel> findBypsType(
        int psType) throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findBypsType(psType);
    }

    /**
    * Returns a range of all the ps models where psType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param psType the ps type
    * @param start the lower bound of the range of ps models
    * @param end the upper bound of the range of ps models (not inclusive)
    * @return the range of matching ps models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.PsModel> findBypsType(
        int psType, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findBypsType(psType, start, end);
    }

    /**
    * Returns an ordered range of all the ps models where psType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param psType the ps type
    * @param start the lower bound of the range of ps models
    * @param end the upper bound of the range of ps models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching ps models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.PsModel> findBypsType(
        int psType, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBypsType(psType, start, end, orderByComparator);
    }

    /**
    * Returns the first ps model in the ordered set where psType = &#63;.
    *
    * @param psType the ps type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ps model
    * @throws com.stpl.app.NoSuchPsModelException if a matching ps model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.PsModel findBypsType_First(int psType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchPsModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findBypsType_First(psType, orderByComparator);
    }

    /**
    * Returns the first ps model in the ordered set where psType = &#63;.
    *
    * @param psType the ps type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ps model, or <code>null</code> if a matching ps model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.PsModel fetchBypsType_First(int psType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchBypsType_First(psType, orderByComparator);
    }

    /**
    * Returns the last ps model in the ordered set where psType = &#63;.
    *
    * @param psType the ps type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ps model
    * @throws com.stpl.app.NoSuchPsModelException if a matching ps model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.PsModel findBypsType_Last(int psType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchPsModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findBypsType_Last(psType, orderByComparator);
    }

    /**
    * Returns the last ps model in the ordered set where psType = &#63;.
    *
    * @param psType the ps type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ps model, or <code>null</code> if a matching ps model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.PsModel fetchBypsType_Last(int psType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchBypsType_Last(psType, orderByComparator);
    }

    /**
    * Returns the ps models before and after the current ps model in the ordered set where psType = &#63;.
    *
    * @param psModelSid the primary key of the current ps model
    * @param psType the ps type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next ps model
    * @throws com.stpl.app.NoSuchPsModelException if a ps model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.PsModel[] findBypsType_PrevAndNext(
        int psModelSid, int psType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchPsModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBypsType_PrevAndNext(psModelSid, psType,
            orderByComparator);
    }

    /**
    * Removes all the ps models where psType = &#63; from the database.
    *
    * @param psType the ps type
    * @throws SystemException if a system exception occurred
    */
    public static void removeBypsType(int psType)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeBypsType(psType);
    }

    /**
    * Returns the number of ps models where psType = &#63;.
    *
    * @param psType the ps type
    * @return the number of matching ps models
    * @throws SystemException if a system exception occurred
    */
    public static int countBypsType(int psType)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countBypsType(psType);
    }

    /**
    * Returns all the ps models where psStatus = &#63;.
    *
    * @param psStatus the ps status
    * @return the matching ps models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.PsModel> findBypsStatus(
        int psStatus) throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findBypsStatus(psStatus);
    }

    /**
    * Returns a range of all the ps models where psStatus = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param psStatus the ps status
    * @param start the lower bound of the range of ps models
    * @param end the upper bound of the range of ps models (not inclusive)
    * @return the range of matching ps models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.PsModel> findBypsStatus(
        int psStatus, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findBypsStatus(psStatus, start, end);
    }

    /**
    * Returns an ordered range of all the ps models where psStatus = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param psStatus the ps status
    * @param start the lower bound of the range of ps models
    * @param end the upper bound of the range of ps models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching ps models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.PsModel> findBypsStatus(
        int psStatus, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBypsStatus(psStatus, start, end, orderByComparator);
    }

    /**
    * Returns the first ps model in the ordered set where psStatus = &#63;.
    *
    * @param psStatus the ps status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ps model
    * @throws com.stpl.app.NoSuchPsModelException if a matching ps model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.PsModel findBypsStatus_First(
        int psStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchPsModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findBypsStatus_First(psStatus, orderByComparator);
    }

    /**
    * Returns the first ps model in the ordered set where psStatus = &#63;.
    *
    * @param psStatus the ps status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ps model, or <code>null</code> if a matching ps model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.PsModel fetchBypsStatus_First(
        int psStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchBypsStatus_First(psStatus, orderByComparator);
    }

    /**
    * Returns the last ps model in the ordered set where psStatus = &#63;.
    *
    * @param psStatus the ps status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ps model
    * @throws com.stpl.app.NoSuchPsModelException if a matching ps model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.PsModel findBypsStatus_Last(int psStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchPsModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findBypsStatus_Last(psStatus, orderByComparator);
    }

    /**
    * Returns the last ps model in the ordered set where psStatus = &#63;.
    *
    * @param psStatus the ps status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ps model, or <code>null</code> if a matching ps model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.PsModel fetchBypsStatus_Last(
        int psStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchBypsStatus_Last(psStatus, orderByComparator);
    }

    /**
    * Returns the ps models before and after the current ps model in the ordered set where psStatus = &#63;.
    *
    * @param psModelSid the primary key of the current ps model
    * @param psStatus the ps status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next ps model
    * @throws com.stpl.app.NoSuchPsModelException if a ps model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.PsModel[] findBypsStatus_PrevAndNext(
        int psModelSid, int psStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchPsModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBypsStatus_PrevAndNext(psModelSid, psStatus,
            orderByComparator);
    }

    /**
    * Removes all the ps models where psStatus = &#63; from the database.
    *
    * @param psStatus the ps status
    * @throws SystemException if a system exception occurred
    */
    public static void removeBypsStatus(int psStatus)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeBypsStatus(psStatus);
    }

    /**
    * Returns the number of ps models where psStatus = &#63;.
    *
    * @param psStatus the ps status
    * @return the number of matching ps models
    * @throws SystemException if a system exception occurred
    */
    public static int countBypsStatus(int psStatus)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countBypsStatus(psStatus);
    }

    /**
    * Caches the ps model in the entity cache if it is enabled.
    *
    * @param psModel the ps model
    */
    public static void cacheResult(com.stpl.app.model.PsModel psModel) {
        getPersistence().cacheResult(psModel);
    }

    /**
    * Caches the ps models in the entity cache if it is enabled.
    *
    * @param psModels the ps models
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.PsModel> psModels) {
        getPersistence().cacheResult(psModels);
    }

    /**
    * Creates a new ps model with the primary key. Does not add the ps model to the database.
    *
    * @param psModelSid the primary key for the new ps model
    * @return the new ps model
    */
    public static com.stpl.app.model.PsModel create(int psModelSid) {
        return getPersistence().create(psModelSid);
    }

    /**
    * Removes the ps model with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param psModelSid the primary key of the ps model
    * @return the ps model that was removed
    * @throws com.stpl.app.NoSuchPsModelException if a ps model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.PsModel remove(int psModelSid)
        throws com.stpl.app.NoSuchPsModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(psModelSid);
    }

    public static com.stpl.app.model.PsModel updateImpl(
        com.stpl.app.model.PsModel psModel)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(psModel);
    }

    /**
    * Returns the ps model with the primary key or throws a {@link com.stpl.app.NoSuchPsModelException} if it could not be found.
    *
    * @param psModelSid the primary key of the ps model
    * @return the ps model
    * @throws com.stpl.app.NoSuchPsModelException if a ps model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.PsModel findByPrimaryKey(int psModelSid)
        throws com.stpl.app.NoSuchPsModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(psModelSid);
    }

    /**
    * Returns the ps model with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param psModelSid the primary key of the ps model
    * @return the ps model, or <code>null</code> if a ps model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.PsModel fetchByPrimaryKey(int psModelSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(psModelSid);
    }

    /**
    * Returns all the ps models.
    *
    * @return the ps models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.PsModel> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the ps models.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ps models
    * @param end the upper bound of the range of ps models (not inclusive)
    * @return the range of ps models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.PsModel> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the ps models.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ps models
    * @param end the upper bound of the range of ps models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ps models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.PsModel> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the ps models from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of ps models.
    *
    * @return the number of ps models
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static PsModelPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (PsModelPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    PsModelPersistence.class.getName());

            ReferenceRegistry.registerReference(PsModelUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(PsModelPersistence persistence) {
    }
}
