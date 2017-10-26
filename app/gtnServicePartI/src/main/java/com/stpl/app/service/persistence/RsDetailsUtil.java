package com.stpl.app.service.persistence;

import com.stpl.app.model.RsDetails;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the rs details service. This utility wraps {@link RsDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RsDetailsPersistence
 * @see RsDetailsPersistenceImpl
 * @generated
 */
public class RsDetailsUtil {
    private static RsDetailsPersistence _persistence;

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
    public static void clearCache(RsDetails rsDetails) {
        getPersistence().clearCache(rsDetails);
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
    public static List<RsDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<RsDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<RsDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static RsDetails update(RsDetails rsDetails)
        throws SystemException {
        return getPersistence().update(rsDetails);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static RsDetails update(RsDetails rsDetails,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(rsDetails, serviceContext);
    }

    /**
    * Caches the rs details in the entity cache if it is enabled.
    *
    * @param rsDetails the rs details
    */
    public static void cacheResult(com.stpl.app.model.RsDetails rsDetails) {
        getPersistence().cacheResult(rsDetails);
    }

    /**
    * Caches the rs detailses in the entity cache if it is enabled.
    *
    * @param rsDetailses the rs detailses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.RsDetails> rsDetailses) {
        getPersistence().cacheResult(rsDetailses);
    }

    /**
    * Creates a new rs details with the primary key. Does not add the rs details to the database.
    *
    * @param rsDetailsSid the primary key for the new rs details
    * @return the new rs details
    */
    public static com.stpl.app.model.RsDetails create(int rsDetailsSid) {
        return getPersistence().create(rsDetailsSid);
    }

    /**
    * Removes the rs details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param rsDetailsSid the primary key of the rs details
    * @return the rs details that was removed
    * @throws com.stpl.app.NoSuchRsDetailsException if a rs details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsDetails remove(int rsDetailsSid)
        throws com.stpl.app.NoSuchRsDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(rsDetailsSid);
    }

    public static com.stpl.app.model.RsDetails updateImpl(
        com.stpl.app.model.RsDetails rsDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(rsDetails);
    }

    /**
    * Returns the rs details with the primary key or throws a {@link com.stpl.app.NoSuchRsDetailsException} if it could not be found.
    *
    * @param rsDetailsSid the primary key of the rs details
    * @return the rs details
    * @throws com.stpl.app.NoSuchRsDetailsException if a rs details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsDetails findByPrimaryKey(
        int rsDetailsSid)
        throws com.stpl.app.NoSuchRsDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(rsDetailsSid);
    }

    /**
    * Returns the rs details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param rsDetailsSid the primary key of the rs details
    * @return the rs details, or <code>null</code> if a rs details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsDetails fetchByPrimaryKey(
        int rsDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(rsDetailsSid);
    }

    /**
    * Returns all the rs detailses.
    *
    * @return the rs detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RsDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the rs detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of rs detailses
    * @param end the upper bound of the range of rs detailses (not inclusive)
    * @return the range of rs detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RsDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the rs detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of rs detailses
    * @param end the upper bound of the range of rs detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of rs detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RsDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the rs detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of rs detailses.
    *
    * @return the number of rs detailses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static RsDetailsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (RsDetailsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    RsDetailsPersistence.class.getName());

            ReferenceRegistry.registerReference(RsDetailsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(RsDetailsPersistence persistence) {
    }
}
