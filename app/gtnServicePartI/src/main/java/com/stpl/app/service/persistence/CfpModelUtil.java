package com.stpl.app.service.persistence;

import com.stpl.app.model.CfpModel;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the cfp model service. This utility wraps {@link CfpModelPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CfpModelPersistence
 * @see CfpModelPersistenceImpl
 * @generated
 */
public class CfpModelUtil {
    private static CfpModelPersistence _persistence;

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
    public static void clearCache(CfpModel cfpModel) {
        getPersistence().clearCache(cfpModel);
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
    public static List<CfpModel> findWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<CfpModel> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<CfpModel> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static CfpModel update(CfpModel cfpModel) throws SystemException {
        return getPersistence().update(cfpModel);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static CfpModel update(CfpModel cfpModel,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(cfpModel, serviceContext);
    }

    /**
    * Returns all the cfp models where cfpId = &#63;.
    *
    * @param cfpId the cfp ID
    * @return the matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CfpModel> findByCfpId(
        java.lang.String cfpId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByCfpId(cfpId);
    }

    /**
    * Returns a range of all the cfp models where cfpId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param cfpId the cfp ID
    * @param start the lower bound of the range of cfp models
    * @param end the upper bound of the range of cfp models (not inclusive)
    * @return the range of matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CfpModel> findByCfpId(
        java.lang.String cfpId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByCfpId(cfpId, start, end);
    }

    /**
    * Returns an ordered range of all the cfp models where cfpId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param cfpId the cfp ID
    * @param start the lower bound of the range of cfp models
    * @param end the upper bound of the range of cfp models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CfpModel> findByCfpId(
        java.lang.String cfpId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByCfpId(cfpId, start, end, orderByComparator);
    }

    /**
    * Returns the first cfp model in the ordered set where cfpId = &#63;.
    *
    * @param cfpId the cfp ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cfp model
    * @throws com.stpl.app.NoSuchCfpModelException if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpModel findByCfpId_First(
        java.lang.String cfpId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByCfpId_First(cfpId, orderByComparator);
    }

    /**
    * Returns the first cfp model in the ordered set where cfpId = &#63;.
    *
    * @param cfpId the cfp ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cfp model, or <code>null</code> if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpModel fetchByCfpId_First(
        java.lang.String cfpId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByCfpId_First(cfpId, orderByComparator);
    }

    /**
    * Returns the last cfp model in the ordered set where cfpId = &#63;.
    *
    * @param cfpId the cfp ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cfp model
    * @throws com.stpl.app.NoSuchCfpModelException if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpModel findByCfpId_Last(
        java.lang.String cfpId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByCfpId_Last(cfpId, orderByComparator);
    }

    /**
    * Returns the last cfp model in the ordered set where cfpId = &#63;.
    *
    * @param cfpId the cfp ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cfp model, or <code>null</code> if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpModel fetchByCfpId_Last(
        java.lang.String cfpId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByCfpId_Last(cfpId, orderByComparator);
    }

    /**
    * Returns the cfp models before and after the current cfp model in the ordered set where cfpId = &#63;.
    *
    * @param cfpModelSid the primary key of the current cfp model
    * @param cfpId the cfp ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next cfp model
    * @throws com.stpl.app.NoSuchCfpModelException if a cfp model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpModel[] findByCfpId_PrevAndNext(
        int cfpModelSid, java.lang.String cfpId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCfpId_PrevAndNext(cfpModelSid, cfpId,
            orderByComparator);
    }

    /**
    * Removes all the cfp models where cfpId = &#63; from the database.
    *
    * @param cfpId the cfp ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCfpId(java.lang.String cfpId)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByCfpId(cfpId);
    }

    /**
    * Returns the number of cfp models where cfpId = &#63;.
    *
    * @param cfpId the cfp ID
    * @return the number of matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public static int countByCfpId(java.lang.String cfpId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByCfpId(cfpId);
    }

    /**
    * Returns all the cfp models where cfpNo = &#63;.
    *
    * @param cfpNo the cfp no
    * @return the matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CfpModel> findByCfpNo(
        java.lang.String cfpNo)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByCfpNo(cfpNo);
    }

    /**
    * Returns a range of all the cfp models where cfpNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param cfpNo the cfp no
    * @param start the lower bound of the range of cfp models
    * @param end the upper bound of the range of cfp models (not inclusive)
    * @return the range of matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CfpModel> findByCfpNo(
        java.lang.String cfpNo, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByCfpNo(cfpNo, start, end);
    }

    /**
    * Returns an ordered range of all the cfp models where cfpNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param cfpNo the cfp no
    * @param start the lower bound of the range of cfp models
    * @param end the upper bound of the range of cfp models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CfpModel> findByCfpNo(
        java.lang.String cfpNo, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByCfpNo(cfpNo, start, end, orderByComparator);
    }

    /**
    * Returns the first cfp model in the ordered set where cfpNo = &#63;.
    *
    * @param cfpNo the cfp no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cfp model
    * @throws com.stpl.app.NoSuchCfpModelException if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpModel findByCfpNo_First(
        java.lang.String cfpNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByCfpNo_First(cfpNo, orderByComparator);
    }

    /**
    * Returns the first cfp model in the ordered set where cfpNo = &#63;.
    *
    * @param cfpNo the cfp no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cfp model, or <code>null</code> if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpModel fetchByCfpNo_First(
        java.lang.String cfpNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByCfpNo_First(cfpNo, orderByComparator);
    }

    /**
    * Returns the last cfp model in the ordered set where cfpNo = &#63;.
    *
    * @param cfpNo the cfp no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cfp model
    * @throws com.stpl.app.NoSuchCfpModelException if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpModel findByCfpNo_Last(
        java.lang.String cfpNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByCfpNo_Last(cfpNo, orderByComparator);
    }

    /**
    * Returns the last cfp model in the ordered set where cfpNo = &#63;.
    *
    * @param cfpNo the cfp no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cfp model, or <code>null</code> if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpModel fetchByCfpNo_Last(
        java.lang.String cfpNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByCfpNo_Last(cfpNo, orderByComparator);
    }

    /**
    * Returns the cfp models before and after the current cfp model in the ordered set where cfpNo = &#63;.
    *
    * @param cfpModelSid the primary key of the current cfp model
    * @param cfpNo the cfp no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next cfp model
    * @throws com.stpl.app.NoSuchCfpModelException if a cfp model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpModel[] findByCfpNo_PrevAndNext(
        int cfpModelSid, java.lang.String cfpNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCfpNo_PrevAndNext(cfpModelSid, cfpNo,
            orderByComparator);
    }

    /**
    * Removes all the cfp models where cfpNo = &#63; from the database.
    *
    * @param cfpNo the cfp no
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCfpNo(java.lang.String cfpNo)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByCfpNo(cfpNo);
    }

    /**
    * Returns the number of cfp models where cfpNo = &#63;.
    *
    * @param cfpNo the cfp no
    * @return the number of matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public static int countByCfpNo(java.lang.String cfpNo)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByCfpNo(cfpNo);
    }

    /**
    * Returns all the cfp models where cfpName = &#63;.
    *
    * @param cfpName the cfp name
    * @return the matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CfpModel> findByCfpName(
        java.lang.String cfpName)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByCfpName(cfpName);
    }

    /**
    * Returns a range of all the cfp models where cfpName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param cfpName the cfp name
    * @param start the lower bound of the range of cfp models
    * @param end the upper bound of the range of cfp models (not inclusive)
    * @return the range of matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CfpModel> findByCfpName(
        java.lang.String cfpName, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByCfpName(cfpName, start, end);
    }

    /**
    * Returns an ordered range of all the cfp models where cfpName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param cfpName the cfp name
    * @param start the lower bound of the range of cfp models
    * @param end the upper bound of the range of cfp models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CfpModel> findByCfpName(
        java.lang.String cfpName, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCfpName(cfpName, start, end, orderByComparator);
    }

    /**
    * Returns the first cfp model in the ordered set where cfpName = &#63;.
    *
    * @param cfpName the cfp name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cfp model
    * @throws com.stpl.app.NoSuchCfpModelException if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpModel findByCfpName_First(
        java.lang.String cfpName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByCfpName_First(cfpName, orderByComparator);
    }

    /**
    * Returns the first cfp model in the ordered set where cfpName = &#63;.
    *
    * @param cfpName the cfp name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cfp model, or <code>null</code> if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpModel fetchByCfpName_First(
        java.lang.String cfpName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByCfpName_First(cfpName, orderByComparator);
    }

    /**
    * Returns the last cfp model in the ordered set where cfpName = &#63;.
    *
    * @param cfpName the cfp name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cfp model
    * @throws com.stpl.app.NoSuchCfpModelException if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpModel findByCfpName_Last(
        java.lang.String cfpName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByCfpName_Last(cfpName, orderByComparator);
    }

    /**
    * Returns the last cfp model in the ordered set where cfpName = &#63;.
    *
    * @param cfpName the cfp name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cfp model, or <code>null</code> if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpModel fetchByCfpName_Last(
        java.lang.String cfpName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByCfpName_Last(cfpName, orderByComparator);
    }

    /**
    * Returns the cfp models before and after the current cfp model in the ordered set where cfpName = &#63;.
    *
    * @param cfpModelSid the primary key of the current cfp model
    * @param cfpName the cfp name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next cfp model
    * @throws com.stpl.app.NoSuchCfpModelException if a cfp model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpModel[] findByCfpName_PrevAndNext(
        int cfpModelSid, java.lang.String cfpName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCfpName_PrevAndNext(cfpModelSid, cfpName,
            orderByComparator);
    }

    /**
    * Removes all the cfp models where cfpName = &#63; from the database.
    *
    * @param cfpName the cfp name
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCfpName(java.lang.String cfpName)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByCfpName(cfpName);
    }

    /**
    * Returns the number of cfp models where cfpName = &#63;.
    *
    * @param cfpName the cfp name
    * @return the number of matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public static int countByCfpName(java.lang.String cfpName)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByCfpName(cfpName);
    }

    /**
    * Returns all the cfp models where cfpType = &#63;.
    *
    * @param cfpType the cfp type
    * @return the matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CfpModel> findByCfpType(
        int cfpType) throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByCfpType(cfpType);
    }

    /**
    * Returns a range of all the cfp models where cfpType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param cfpType the cfp type
    * @param start the lower bound of the range of cfp models
    * @param end the upper bound of the range of cfp models (not inclusive)
    * @return the range of matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CfpModel> findByCfpType(
        int cfpType, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByCfpType(cfpType, start, end);
    }

    /**
    * Returns an ordered range of all the cfp models where cfpType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param cfpType the cfp type
    * @param start the lower bound of the range of cfp models
    * @param end the upper bound of the range of cfp models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CfpModel> findByCfpType(
        int cfpType, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCfpType(cfpType, start, end, orderByComparator);
    }

    /**
    * Returns the first cfp model in the ordered set where cfpType = &#63;.
    *
    * @param cfpType the cfp type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cfp model
    * @throws com.stpl.app.NoSuchCfpModelException if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpModel findByCfpType_First(int cfpType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByCfpType_First(cfpType, orderByComparator);
    }

    /**
    * Returns the first cfp model in the ordered set where cfpType = &#63;.
    *
    * @param cfpType the cfp type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cfp model, or <code>null</code> if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpModel fetchByCfpType_First(
        int cfpType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByCfpType_First(cfpType, orderByComparator);
    }

    /**
    * Returns the last cfp model in the ordered set where cfpType = &#63;.
    *
    * @param cfpType the cfp type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cfp model
    * @throws com.stpl.app.NoSuchCfpModelException if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpModel findByCfpType_Last(int cfpType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByCfpType_Last(cfpType, orderByComparator);
    }

    /**
    * Returns the last cfp model in the ordered set where cfpType = &#63;.
    *
    * @param cfpType the cfp type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cfp model, or <code>null</code> if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpModel fetchByCfpType_Last(int cfpType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByCfpType_Last(cfpType, orderByComparator);
    }

    /**
    * Returns the cfp models before and after the current cfp model in the ordered set where cfpType = &#63;.
    *
    * @param cfpModelSid the primary key of the current cfp model
    * @param cfpType the cfp type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next cfp model
    * @throws com.stpl.app.NoSuchCfpModelException if a cfp model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpModel[] findByCfpType_PrevAndNext(
        int cfpModelSid, int cfpType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCfpType_PrevAndNext(cfpModelSid, cfpType,
            orderByComparator);
    }

    /**
    * Removes all the cfp models where cfpType = &#63; from the database.
    *
    * @param cfpType the cfp type
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCfpType(int cfpType)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByCfpType(cfpType);
    }

    /**
    * Returns the number of cfp models where cfpType = &#63;.
    *
    * @param cfpType the cfp type
    * @return the number of matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public static int countByCfpType(int cfpType)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByCfpType(cfpType);
    }

    /**
    * Returns all the cfp models where cfpStatus = &#63;.
    *
    * @param cfpStatus the cfp status
    * @return the matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CfpModel> findByCfpStatus(
        int cfpStatus) throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByCfpStatus(cfpStatus);
    }

    /**
    * Returns a range of all the cfp models where cfpStatus = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param cfpStatus the cfp status
    * @param start the lower bound of the range of cfp models
    * @param end the upper bound of the range of cfp models (not inclusive)
    * @return the range of matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CfpModel> findByCfpStatus(
        int cfpStatus, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByCfpStatus(cfpStatus, start, end);
    }

    /**
    * Returns an ordered range of all the cfp models where cfpStatus = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param cfpStatus the cfp status
    * @param start the lower bound of the range of cfp models
    * @param end the upper bound of the range of cfp models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CfpModel> findByCfpStatus(
        int cfpStatus, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCfpStatus(cfpStatus, start, end, orderByComparator);
    }

    /**
    * Returns the first cfp model in the ordered set where cfpStatus = &#63;.
    *
    * @param cfpStatus the cfp status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cfp model
    * @throws com.stpl.app.NoSuchCfpModelException if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpModel findByCfpStatus_First(
        int cfpStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCfpStatus_First(cfpStatus, orderByComparator);
    }

    /**
    * Returns the first cfp model in the ordered set where cfpStatus = &#63;.
    *
    * @param cfpStatus the cfp status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cfp model, or <code>null</code> if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpModel fetchByCfpStatus_First(
        int cfpStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByCfpStatus_First(cfpStatus, orderByComparator);
    }

    /**
    * Returns the last cfp model in the ordered set where cfpStatus = &#63;.
    *
    * @param cfpStatus the cfp status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cfp model
    * @throws com.stpl.app.NoSuchCfpModelException if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpModel findByCfpStatus_Last(
        int cfpStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCfpStatus_Last(cfpStatus, orderByComparator);
    }

    /**
    * Returns the last cfp model in the ordered set where cfpStatus = &#63;.
    *
    * @param cfpStatus the cfp status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cfp model, or <code>null</code> if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpModel fetchByCfpStatus_Last(
        int cfpStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByCfpStatus_Last(cfpStatus, orderByComparator);
    }

    /**
    * Returns the cfp models before and after the current cfp model in the ordered set where cfpStatus = &#63;.
    *
    * @param cfpModelSid the primary key of the current cfp model
    * @param cfpStatus the cfp status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next cfp model
    * @throws com.stpl.app.NoSuchCfpModelException if a cfp model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpModel[] findByCfpStatus_PrevAndNext(
        int cfpModelSid, int cfpStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCfpStatus_PrevAndNext(cfpModelSid, cfpStatus,
            orderByComparator);
    }

    /**
    * Removes all the cfp models where cfpStatus = &#63; from the database.
    *
    * @param cfpStatus the cfp status
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCfpStatus(int cfpStatus)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByCfpStatus(cfpStatus);
    }

    /**
    * Returns the number of cfp models where cfpStatus = &#63;.
    *
    * @param cfpStatus the cfp status
    * @return the number of matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public static int countByCfpStatus(int cfpStatus)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByCfpStatus(cfpStatus);
    }

    /**
    * Returns all the cfp models where cfpTradeClass = &#63;.
    *
    * @param cfpTradeClass the cfp trade class
    * @return the matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CfpModel> findByCfpTradeClass(
        int cfpTradeClass)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByCfpTradeClass(cfpTradeClass);
    }

    /**
    * Returns a range of all the cfp models where cfpTradeClass = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param cfpTradeClass the cfp trade class
    * @param start the lower bound of the range of cfp models
    * @param end the upper bound of the range of cfp models (not inclusive)
    * @return the range of matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CfpModel> findByCfpTradeClass(
        int cfpTradeClass, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByCfpTradeClass(cfpTradeClass, start, end);
    }

    /**
    * Returns an ordered range of all the cfp models where cfpTradeClass = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param cfpTradeClass the cfp trade class
    * @param start the lower bound of the range of cfp models
    * @param end the upper bound of the range of cfp models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CfpModel> findByCfpTradeClass(
        int cfpTradeClass, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCfpTradeClass(cfpTradeClass, start, end,
            orderByComparator);
    }

    /**
    * Returns the first cfp model in the ordered set where cfpTradeClass = &#63;.
    *
    * @param cfpTradeClass the cfp trade class
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cfp model
    * @throws com.stpl.app.NoSuchCfpModelException if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpModel findByCfpTradeClass_First(
        int cfpTradeClass,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCfpTradeClass_First(cfpTradeClass, orderByComparator);
    }

    /**
    * Returns the first cfp model in the ordered set where cfpTradeClass = &#63;.
    *
    * @param cfpTradeClass the cfp trade class
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cfp model, or <code>null</code> if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpModel fetchByCfpTradeClass_First(
        int cfpTradeClass,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByCfpTradeClass_First(cfpTradeClass, orderByComparator);
    }

    /**
    * Returns the last cfp model in the ordered set where cfpTradeClass = &#63;.
    *
    * @param cfpTradeClass the cfp trade class
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cfp model
    * @throws com.stpl.app.NoSuchCfpModelException if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpModel findByCfpTradeClass_Last(
        int cfpTradeClass,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCfpTradeClass_Last(cfpTradeClass, orderByComparator);
    }

    /**
    * Returns the last cfp model in the ordered set where cfpTradeClass = &#63;.
    *
    * @param cfpTradeClass the cfp trade class
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cfp model, or <code>null</code> if a matching cfp model could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpModel fetchByCfpTradeClass_Last(
        int cfpTradeClass,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByCfpTradeClass_Last(cfpTradeClass, orderByComparator);
    }

    /**
    * Returns the cfp models before and after the current cfp model in the ordered set where cfpTradeClass = &#63;.
    *
    * @param cfpModelSid the primary key of the current cfp model
    * @param cfpTradeClass the cfp trade class
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next cfp model
    * @throws com.stpl.app.NoSuchCfpModelException if a cfp model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpModel[] findByCfpTradeClass_PrevAndNext(
        int cfpModelSid, int cfpTradeClass,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCfpTradeClass_PrevAndNext(cfpModelSid, cfpTradeClass,
            orderByComparator);
    }

    /**
    * Removes all the cfp models where cfpTradeClass = &#63; from the database.
    *
    * @param cfpTradeClass the cfp trade class
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCfpTradeClass(int cfpTradeClass)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByCfpTradeClass(cfpTradeClass);
    }

    /**
    * Returns the number of cfp models where cfpTradeClass = &#63;.
    *
    * @param cfpTradeClass the cfp trade class
    * @return the number of matching cfp models
    * @throws SystemException if a system exception occurred
    */
    public static int countByCfpTradeClass(int cfpTradeClass)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByCfpTradeClass(cfpTradeClass);
    }

    /**
    * Caches the cfp model in the entity cache if it is enabled.
    *
    * @param cfpModel the cfp model
    */
    public static void cacheResult(com.stpl.app.model.CfpModel cfpModel) {
        getPersistence().cacheResult(cfpModel);
    }

    /**
    * Caches the cfp models in the entity cache if it is enabled.
    *
    * @param cfpModels the cfp models
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.CfpModel> cfpModels) {
        getPersistence().cacheResult(cfpModels);
    }

    /**
    * Creates a new cfp model with the primary key. Does not add the cfp model to the database.
    *
    * @param cfpModelSid the primary key for the new cfp model
    * @return the new cfp model
    */
    public static com.stpl.app.model.CfpModel create(int cfpModelSid) {
        return getPersistence().create(cfpModelSid);
    }

    /**
    * Removes the cfp model with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param cfpModelSid the primary key of the cfp model
    * @return the cfp model that was removed
    * @throws com.stpl.app.NoSuchCfpModelException if a cfp model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpModel remove(int cfpModelSid)
        throws com.stpl.app.NoSuchCfpModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(cfpModelSid);
    }

    public static com.stpl.app.model.CfpModel updateImpl(
        com.stpl.app.model.CfpModel cfpModel)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(cfpModel);
    }

    /**
    * Returns the cfp model with the primary key or throws a {@link com.stpl.app.NoSuchCfpModelException} if it could not be found.
    *
    * @param cfpModelSid the primary key of the cfp model
    * @return the cfp model
    * @throws com.stpl.app.NoSuchCfpModelException if a cfp model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpModel findByPrimaryKey(int cfpModelSid)
        throws com.stpl.app.NoSuchCfpModelException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(cfpModelSid);
    }

    /**
    * Returns the cfp model with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param cfpModelSid the primary key of the cfp model
    * @return the cfp model, or <code>null</code> if a cfp model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpModel fetchByPrimaryKey(int cfpModelSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(cfpModelSid);
    }

    /**
    * Returns all the cfp models.
    *
    * @return the cfp models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CfpModel> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the cfp models.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cfp models
    * @param end the upper bound of the range of cfp models (not inclusive)
    * @return the range of cfp models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CfpModel> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the cfp models.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cfp models
    * @param end the upper bound of the range of cfp models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of cfp models
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CfpModel> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the cfp models from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of cfp models.
    *
    * @return the number of cfp models
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static CfpModelPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (CfpModelPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    CfpModelPersistence.class.getName());

            ReferenceRegistry.registerReference(CfpModelUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(CfpModelPersistence persistence) {
    }
}
