package com.stpl.app.service.persistence;

import com.stpl.app.model.RsModel;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the rs model service. This utility wraps {@link RsModelPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RsModelPersistence
 * @see RsModelPersistenceImpl
 * @generated
 */
public class RsModelUtil {
    private static RsModelPersistence _persistence;

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
    public static void clearCache(RsModel rsModel) {
        getPersistence().clearCache(rsModel);
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
    public static List<RsModel> findWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<RsModel> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<RsModel> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static RsModel update(RsModel rsModel) throws SystemException {
        return getPersistence().update(rsModel);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static RsModel update(RsModel rsModel, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(rsModel, serviceContext);
    }

    /**
    * Returns all the rs models where rsId = &#63;.
    *
    * @param rsId the rs ID
    * @return the matching rs models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RsModel> findByRebateScheduleId(
        java.lang.String rsId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByRebateScheduleId(rsId);
    }

    /**
    * Returns a range of all the rs models where rsId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rsId the rs ID
    * @param start the lower bound of the range of rs models
    * @param end the upper bound of the range of rs models (not inclusive)
    * @return the range of matching rs models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RsModel> findByRebateScheduleId(
        java.lang.String rsId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByRebateScheduleId(rsId, start, end);
    }

    /**
    * Returns an ordered range of all the rs models where rsId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rsId the rs ID
    * @param start the lower bound of the range of rs models
    * @param end the upper bound of the range of rs models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rs models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RsModel> findByRebateScheduleId(
        java.lang.String rsId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebateScheduleId(rsId, start, end, orderByComparator);
    }

    /**
    * Returns the first rs model in the ordered set where rsId = &#63;.
    *
    * @param rsId the rs ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rs model
    * @throws com.stpl.app.NoSuchRsModelException if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsModel findByRebateScheduleId_First(
        java.lang.String rsId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRsModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebateScheduleId_First(rsId, orderByComparator);
    }

    /**
    * Returns the first rs model in the ordered set where rsId = &#63;.
    *
    * @param rsId the rs ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rs model, or <code>null</code> if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsModel fetchByRebateScheduleId_First(
        java.lang.String rsId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByRebateScheduleId_First(rsId, orderByComparator);
    }

    /**
    * Returns the last rs model in the ordered set where rsId = &#63;.
    *
    * @param rsId the rs ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rs model
    * @throws com.stpl.app.NoSuchRsModelException if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsModel findByRebateScheduleId_Last(
        java.lang.String rsId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRsModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebateScheduleId_Last(rsId, orderByComparator);
    }

    /**
    * Returns the last rs model in the ordered set where rsId = &#63;.
    *
    * @param rsId the rs ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rs model, or <code>null</code> if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsModel fetchByRebateScheduleId_Last(
        java.lang.String rsId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByRebateScheduleId_Last(rsId, orderByComparator);
    }

    /**
    * Returns the rs models before and after the current rs model in the ordered set where rsId = &#63;.
    *
    * @param rsModelSid the primary key of the current rs model
    * @param rsId the rs ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next rs model
    * @throws com.stpl.app.NoSuchRsModelException if a rs model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsModel[] findByRebateScheduleId_PrevAndNext(
        int rsModelSid, java.lang.String rsId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRsModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebateScheduleId_PrevAndNext(rsModelSid, rsId,
            orderByComparator);
    }

    /**
    * Removes all the rs models where rsId = &#63; from the database.
    *
    * @param rsId the rs ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByRebateScheduleId(java.lang.String rsId)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByRebateScheduleId(rsId);
    }

    /**
    * Returns the number of rs models where rsId = &#63;.
    *
    * @param rsId the rs ID
    * @return the number of matching rs models
    * @throws SystemException if a system exception occurred
    */
    public static int countByRebateScheduleId(java.lang.String rsId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByRebateScheduleId(rsId);
    }

    /**
    * Returns all the rs models where rsNo = &#63;.
    *
    * @param rsNo the rs no
    * @return the matching rs models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RsModel> findByRebateScheduleNo(
        java.lang.String rsNo)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByRebateScheduleNo(rsNo);
    }

    /**
    * Returns a range of all the rs models where rsNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rsNo the rs no
    * @param start the lower bound of the range of rs models
    * @param end the upper bound of the range of rs models (not inclusive)
    * @return the range of matching rs models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RsModel> findByRebateScheduleNo(
        java.lang.String rsNo, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByRebateScheduleNo(rsNo, start, end);
    }

    /**
    * Returns an ordered range of all the rs models where rsNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rsNo the rs no
    * @param start the lower bound of the range of rs models
    * @param end the upper bound of the range of rs models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rs models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RsModel> findByRebateScheduleNo(
        java.lang.String rsNo, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebateScheduleNo(rsNo, start, end, orderByComparator);
    }

    /**
    * Returns the first rs model in the ordered set where rsNo = &#63;.
    *
    * @param rsNo the rs no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rs model
    * @throws com.stpl.app.NoSuchRsModelException if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsModel findByRebateScheduleNo_First(
        java.lang.String rsNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRsModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebateScheduleNo_First(rsNo, orderByComparator);
    }

    /**
    * Returns the first rs model in the ordered set where rsNo = &#63;.
    *
    * @param rsNo the rs no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rs model, or <code>null</code> if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsModel fetchByRebateScheduleNo_First(
        java.lang.String rsNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByRebateScheduleNo_First(rsNo, orderByComparator);
    }

    /**
    * Returns the last rs model in the ordered set where rsNo = &#63;.
    *
    * @param rsNo the rs no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rs model
    * @throws com.stpl.app.NoSuchRsModelException if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsModel findByRebateScheduleNo_Last(
        java.lang.String rsNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRsModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebateScheduleNo_Last(rsNo, orderByComparator);
    }

    /**
    * Returns the last rs model in the ordered set where rsNo = &#63;.
    *
    * @param rsNo the rs no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rs model, or <code>null</code> if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsModel fetchByRebateScheduleNo_Last(
        java.lang.String rsNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByRebateScheduleNo_Last(rsNo, orderByComparator);
    }

    /**
    * Returns the rs models before and after the current rs model in the ordered set where rsNo = &#63;.
    *
    * @param rsModelSid the primary key of the current rs model
    * @param rsNo the rs no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next rs model
    * @throws com.stpl.app.NoSuchRsModelException if a rs model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsModel[] findByRebateScheduleNo_PrevAndNext(
        int rsModelSid, java.lang.String rsNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRsModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebateScheduleNo_PrevAndNext(rsModelSid, rsNo,
            orderByComparator);
    }

    /**
    * Removes all the rs models where rsNo = &#63; from the database.
    *
    * @param rsNo the rs no
    * @throws SystemException if a system exception occurred
    */
    public static void removeByRebateScheduleNo(java.lang.String rsNo)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByRebateScheduleNo(rsNo);
    }

    /**
    * Returns the number of rs models where rsNo = &#63;.
    *
    * @param rsNo the rs no
    * @return the number of matching rs models
    * @throws SystemException if a system exception occurred
    */
    public static int countByRebateScheduleNo(java.lang.String rsNo)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByRebateScheduleNo(rsNo);
    }

    /**
    * Returns all the rs models where rsName = &#63;.
    *
    * @param rsName the rs name
    * @return the matching rs models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RsModel> findByRebateScheduleName(
        java.lang.String rsName)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByRebateScheduleName(rsName);
    }

    /**
    * Returns a range of all the rs models where rsName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rsName the rs name
    * @param start the lower bound of the range of rs models
    * @param end the upper bound of the range of rs models (not inclusive)
    * @return the range of matching rs models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RsModel> findByRebateScheduleName(
        java.lang.String rsName, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByRebateScheduleName(rsName, start, end);
    }

    /**
    * Returns an ordered range of all the rs models where rsName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rsName the rs name
    * @param start the lower bound of the range of rs models
    * @param end the upper bound of the range of rs models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rs models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RsModel> findByRebateScheduleName(
        java.lang.String rsName, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebateScheduleName(rsName, start, end,
            orderByComparator);
    }

    /**
    * Returns the first rs model in the ordered set where rsName = &#63;.
    *
    * @param rsName the rs name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rs model
    * @throws com.stpl.app.NoSuchRsModelException if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsModel findByRebateScheduleName_First(
        java.lang.String rsName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRsModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebateScheduleName_First(rsName, orderByComparator);
    }

    /**
    * Returns the first rs model in the ordered set where rsName = &#63;.
    *
    * @param rsName the rs name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rs model, or <code>null</code> if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsModel fetchByRebateScheduleName_First(
        java.lang.String rsName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByRebateScheduleName_First(rsName, orderByComparator);
    }

    /**
    * Returns the last rs model in the ordered set where rsName = &#63;.
    *
    * @param rsName the rs name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rs model
    * @throws com.stpl.app.NoSuchRsModelException if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsModel findByRebateScheduleName_Last(
        java.lang.String rsName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRsModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebateScheduleName_Last(rsName, orderByComparator);
    }

    /**
    * Returns the last rs model in the ordered set where rsName = &#63;.
    *
    * @param rsName the rs name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rs model, or <code>null</code> if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsModel fetchByRebateScheduleName_Last(
        java.lang.String rsName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByRebateScheduleName_Last(rsName, orderByComparator);
    }

    /**
    * Returns the rs models before and after the current rs model in the ordered set where rsName = &#63;.
    *
    * @param rsModelSid the primary key of the current rs model
    * @param rsName the rs name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next rs model
    * @throws com.stpl.app.NoSuchRsModelException if a rs model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsModel[] findByRebateScheduleName_PrevAndNext(
        int rsModelSid, java.lang.String rsName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRsModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebateScheduleName_PrevAndNext(rsModelSid, rsName,
            orderByComparator);
    }

    /**
    * Removes all the rs models where rsName = &#63; from the database.
    *
    * @param rsName the rs name
    * @throws SystemException if a system exception occurred
    */
    public static void removeByRebateScheduleName(java.lang.String rsName)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByRebateScheduleName(rsName);
    }

    /**
    * Returns the number of rs models where rsName = &#63;.
    *
    * @param rsName the rs name
    * @return the number of matching rs models
    * @throws SystemException if a system exception occurred
    */
    public static int countByRebateScheduleName(java.lang.String rsName)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByRebateScheduleName(rsName);
    }

    /**
    * Returns all the rs models where rsType = &#63;.
    *
    * @param rsType the rs type
    * @return the matching rs models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RsModel> findByRebateScheduleType(
        int rsType) throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByRebateScheduleType(rsType);
    }

    /**
    * Returns a range of all the rs models where rsType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rsType the rs type
    * @param start the lower bound of the range of rs models
    * @param end the upper bound of the range of rs models (not inclusive)
    * @return the range of matching rs models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RsModel> findByRebateScheduleType(
        int rsType, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByRebateScheduleType(rsType, start, end);
    }

    /**
    * Returns an ordered range of all the rs models where rsType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rsType the rs type
    * @param start the lower bound of the range of rs models
    * @param end the upper bound of the range of rs models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rs models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RsModel> findByRebateScheduleType(
        int rsType, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebateScheduleType(rsType, start, end,
            orderByComparator);
    }

    /**
    * Returns the first rs model in the ordered set where rsType = &#63;.
    *
    * @param rsType the rs type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rs model
    * @throws com.stpl.app.NoSuchRsModelException if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsModel findByRebateScheduleType_First(
        int rsType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRsModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebateScheduleType_First(rsType, orderByComparator);
    }

    /**
    * Returns the first rs model in the ordered set where rsType = &#63;.
    *
    * @param rsType the rs type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rs model, or <code>null</code> if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsModel fetchByRebateScheduleType_First(
        int rsType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByRebateScheduleType_First(rsType, orderByComparator);
    }

    /**
    * Returns the last rs model in the ordered set where rsType = &#63;.
    *
    * @param rsType the rs type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rs model
    * @throws com.stpl.app.NoSuchRsModelException if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsModel findByRebateScheduleType_Last(
        int rsType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRsModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebateScheduleType_Last(rsType, orderByComparator);
    }

    /**
    * Returns the last rs model in the ordered set where rsType = &#63;.
    *
    * @param rsType the rs type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rs model, or <code>null</code> if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsModel fetchByRebateScheduleType_Last(
        int rsType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByRebateScheduleType_Last(rsType, orderByComparator);
    }

    /**
    * Returns the rs models before and after the current rs model in the ordered set where rsType = &#63;.
    *
    * @param rsModelSid the primary key of the current rs model
    * @param rsType the rs type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next rs model
    * @throws com.stpl.app.NoSuchRsModelException if a rs model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsModel[] findByRebateScheduleType_PrevAndNext(
        int rsModelSid, int rsType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRsModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebateScheduleType_PrevAndNext(rsModelSid, rsType,
            orderByComparator);
    }

    /**
    * Removes all the rs models where rsType = &#63; from the database.
    *
    * @param rsType the rs type
    * @throws SystemException if a system exception occurred
    */
    public static void removeByRebateScheduleType(int rsType)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByRebateScheduleType(rsType);
    }

    /**
    * Returns the number of rs models where rsType = &#63;.
    *
    * @param rsType the rs type
    * @return the number of matching rs models
    * @throws SystemException if a system exception occurred
    */
    public static int countByRebateScheduleType(int rsType)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByRebateScheduleType(rsType);
    }

    /**
    * Returns all the rs models where rsStatus = &#63;.
    *
    * @param rsStatus the rs status
    * @return the matching rs models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RsModel> findByRebateScheduleStatus(
        int rsStatus) throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByRebateScheduleStatus(rsStatus);
    }

    /**
    * Returns a range of all the rs models where rsStatus = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rsStatus the rs status
    * @param start the lower bound of the range of rs models
    * @param end the upper bound of the range of rs models (not inclusive)
    * @return the range of matching rs models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RsModel> findByRebateScheduleStatus(
        int rsStatus, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByRebateScheduleStatus(rsStatus, start, end);
    }

    /**
    * Returns an ordered range of all the rs models where rsStatus = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rsStatus the rs status
    * @param start the lower bound of the range of rs models
    * @param end the upper bound of the range of rs models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rs models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RsModel> findByRebateScheduleStatus(
        int rsStatus, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebateScheduleStatus(rsStatus, start, end,
            orderByComparator);
    }

    /**
    * Returns the first rs model in the ordered set where rsStatus = &#63;.
    *
    * @param rsStatus the rs status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rs model
    * @throws com.stpl.app.NoSuchRsModelException if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsModel findByRebateScheduleStatus_First(
        int rsStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRsModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebateScheduleStatus_First(rsStatus, orderByComparator);
    }

    /**
    * Returns the first rs model in the ordered set where rsStatus = &#63;.
    *
    * @param rsStatus the rs status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rs model, or <code>null</code> if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsModel fetchByRebateScheduleStatus_First(
        int rsStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByRebateScheduleStatus_First(rsStatus,
            orderByComparator);
    }

    /**
    * Returns the last rs model in the ordered set where rsStatus = &#63;.
    *
    * @param rsStatus the rs status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rs model
    * @throws com.stpl.app.NoSuchRsModelException if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsModel findByRebateScheduleStatus_Last(
        int rsStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRsModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebateScheduleStatus_Last(rsStatus, orderByComparator);
    }

    /**
    * Returns the last rs model in the ordered set where rsStatus = &#63;.
    *
    * @param rsStatus the rs status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rs model, or <code>null</code> if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsModel fetchByRebateScheduleStatus_Last(
        int rsStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByRebateScheduleStatus_Last(rsStatus, orderByComparator);
    }

    /**
    * Returns the rs models before and after the current rs model in the ordered set where rsStatus = &#63;.
    *
    * @param rsModelSid the primary key of the current rs model
    * @param rsStatus the rs status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next rs model
    * @throws com.stpl.app.NoSuchRsModelException if a rs model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsModel[] findByRebateScheduleStatus_PrevAndNext(
        int rsModelSid, int rsStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRsModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebateScheduleStatus_PrevAndNext(rsModelSid,
            rsStatus, orderByComparator);
    }

    /**
    * Removes all the rs models where rsStatus = &#63; from the database.
    *
    * @param rsStatus the rs status
    * @throws SystemException if a system exception occurred
    */
    public static void removeByRebateScheduleStatus(int rsStatus)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByRebateScheduleStatus(rsStatus);
    }

    /**
    * Returns the number of rs models where rsStatus = &#63;.
    *
    * @param rsStatus the rs status
    * @return the number of matching rs models
    * @throws SystemException if a system exception occurred
    */
    public static int countByRebateScheduleStatus(int rsStatus)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByRebateScheduleStatus(rsStatus);
    }

    /**
    * Returns all the rs models where rebateProgramType = &#63;.
    *
    * @param rebateProgramType the rebate program type
    * @return the matching rs models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RsModel> findByRebateProgramType(
        int rebateProgramType)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByRebateProgramType(rebateProgramType);
    }

    /**
    * Returns a range of all the rs models where rebateProgramType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rebateProgramType the rebate program type
    * @param start the lower bound of the range of rs models
    * @param end the upper bound of the range of rs models (not inclusive)
    * @return the range of matching rs models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RsModel> findByRebateProgramType(
        int rebateProgramType, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebateProgramType(rebateProgramType, start, end);
    }

    /**
    * Returns an ordered range of all the rs models where rebateProgramType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rebateProgramType the rebate program type
    * @param start the lower bound of the range of rs models
    * @param end the upper bound of the range of rs models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rs models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RsModel> findByRebateProgramType(
        int rebateProgramType, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebateProgramType(rebateProgramType, start, end,
            orderByComparator);
    }

    /**
    * Returns the first rs model in the ordered set where rebateProgramType = &#63;.
    *
    * @param rebateProgramType the rebate program type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rs model
    * @throws com.stpl.app.NoSuchRsModelException if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsModel findByRebateProgramType_First(
        int rebateProgramType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRsModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebateProgramType_First(rebateProgramType,
            orderByComparator);
    }

    /**
    * Returns the first rs model in the ordered set where rebateProgramType = &#63;.
    *
    * @param rebateProgramType the rebate program type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rs model, or <code>null</code> if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsModel fetchByRebateProgramType_First(
        int rebateProgramType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByRebateProgramType_First(rebateProgramType,
            orderByComparator);
    }

    /**
    * Returns the last rs model in the ordered set where rebateProgramType = &#63;.
    *
    * @param rebateProgramType the rebate program type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rs model
    * @throws com.stpl.app.NoSuchRsModelException if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsModel findByRebateProgramType_Last(
        int rebateProgramType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRsModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebateProgramType_Last(rebateProgramType,
            orderByComparator);
    }

    /**
    * Returns the last rs model in the ordered set where rebateProgramType = &#63;.
    *
    * @param rebateProgramType the rebate program type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rs model, or <code>null</code> if a matching rs model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsModel fetchByRebateProgramType_Last(
        int rebateProgramType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByRebateProgramType_Last(rebateProgramType,
            orderByComparator);
    }

    /**
    * Returns the rs models before and after the current rs model in the ordered set where rebateProgramType = &#63;.
    *
    * @param rsModelSid the primary key of the current rs model
    * @param rebateProgramType the rebate program type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next rs model
    * @throws com.stpl.app.NoSuchRsModelException if a rs model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsModel[] findByRebateProgramType_PrevAndNext(
        int rsModelSid, int rebateProgramType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRsModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebateProgramType_PrevAndNext(rsModelSid,
            rebateProgramType, orderByComparator);
    }

    /**
    * Removes all the rs models where rebateProgramType = &#63; from the database.
    *
    * @param rebateProgramType the rebate program type
    * @throws SystemException if a system exception occurred
    */
    public static void removeByRebateProgramType(int rebateProgramType)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByRebateProgramType(rebateProgramType);
    }

    /**
    * Returns the number of rs models where rebateProgramType = &#63;.
    *
    * @param rebateProgramType the rebate program type
    * @return the number of matching rs models
    * @throws SystemException if a system exception occurred
    */
    public static int countByRebateProgramType(int rebateProgramType)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByRebateProgramType(rebateProgramType);
    }

    /**
    * Caches the rs model in the entity cache if it is enabled.
    *
    * @param rsModel the rs model
    */
    public static void cacheResult(com.stpl.app.model.RsModel rsModel) {
        getPersistence().cacheResult(rsModel);
    }

    /**
    * Caches the rs models in the entity cache if it is enabled.
    *
    * @param rsModels the rs models
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.RsModel> rsModels) {
        getPersistence().cacheResult(rsModels);
    }

    /**
    * Creates a new rs model with the primary key. Does not add the rs model to the database.
    *
    * @param rsModelSid the primary key for the new rs model
    * @return the new rs model
    */
    public static com.stpl.app.model.RsModel create(int rsModelSid) {
        return getPersistence().create(rsModelSid);
    }

    /**
    * Removes the rs model with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param rsModelSid the primary key of the rs model
    * @return the rs model that was removed
    * @throws com.stpl.app.NoSuchRsModelException if a rs model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsModel remove(int rsModelSid)
        throws com.stpl.app.NoSuchRsModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(rsModelSid);
    }

    public static com.stpl.app.model.RsModel updateImpl(
        com.stpl.app.model.RsModel rsModel)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(rsModel);
    }

    /**
    * Returns the rs model with the primary key or throws a {@link com.stpl.app.NoSuchRsModelException} if it could not be found.
    *
    * @param rsModelSid the primary key of the rs model
    * @return the rs model
    * @throws com.stpl.app.NoSuchRsModelException if a rs model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsModel findByPrimaryKey(int rsModelSid)
        throws com.stpl.app.NoSuchRsModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(rsModelSid);
    }

    /**
    * Returns the rs model with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param rsModelSid the primary key of the rs model
    * @return the rs model, or <code>null</code> if a rs model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsModel fetchByPrimaryKey(int rsModelSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(rsModelSid);
    }

    /**
    * Returns all the rs models.
    *
    * @return the rs models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RsModel> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the rs models.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of rs models
    * @param end the upper bound of the range of rs models (not inclusive)
    * @return the range of rs models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RsModel> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the rs models.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of rs models
    * @param end the upper bound of the range of rs models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of rs models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RsModel> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the rs models from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of rs models.
    *
    * @return the number of rs models
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static RsModelPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (RsModelPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    RsModelPersistence.class.getName());

            ReferenceRegistry.registerReference(RsModelUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(RsModelPersistence persistence) {
    }
}
