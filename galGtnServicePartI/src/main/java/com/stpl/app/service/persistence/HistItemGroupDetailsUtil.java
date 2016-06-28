package com.stpl.app.service.persistence;

import com.stpl.app.model.HistItemGroupDetails;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the hist item group details service. This utility wraps {@link HistItemGroupDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HistItemGroupDetailsPersistence
 * @see HistItemGroupDetailsPersistenceImpl
 * @generated
 */
public class HistItemGroupDetailsUtil {
    private static HistItemGroupDetailsPersistence _persistence;

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
    public static void clearCache(HistItemGroupDetails histItemGroupDetails) {
        getPersistence().clearCache(histItemGroupDetails);
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
    public static List<HistItemGroupDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<HistItemGroupDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<HistItemGroupDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static HistItemGroupDetails update(
        HistItemGroupDetails histItemGroupDetails) throws SystemException {
        return getPersistence().update(histItemGroupDetails);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static HistItemGroupDetails update(
        HistItemGroupDetails histItemGroupDetails, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(histItemGroupDetails, serviceContext);
    }

    /**
    * Caches the hist item group details in the entity cache if it is enabled.
    *
    * @param histItemGroupDetails the hist item group details
    */
    public static void cacheResult(
        com.stpl.app.model.HistItemGroupDetails histItemGroupDetails) {
        getPersistence().cacheResult(histItemGroupDetails);
    }

    /**
    * Caches the hist item group detailses in the entity cache if it is enabled.
    *
    * @param histItemGroupDetailses the hist item group detailses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.HistItemGroupDetails> histItemGroupDetailses) {
        getPersistence().cacheResult(histItemGroupDetailses);
    }

    /**
    * Creates a new hist item group details with the primary key. Does not add the hist item group details to the database.
    *
    * @param histItemGroupDetailsPK the primary key for the new hist item group details
    * @return the new hist item group details
    */
    public static com.stpl.app.model.HistItemGroupDetails create(
        HistItemGroupDetailsPK histItemGroupDetailsPK) {
        return getPersistence().create(histItemGroupDetailsPK);
    }

    /**
    * Removes the hist item group details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param histItemGroupDetailsPK the primary key of the hist item group details
    * @return the hist item group details that was removed
    * @throws com.stpl.app.NoSuchHistItemGroupDetailsException if a hist item group details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HistItemGroupDetails remove(
        HistItemGroupDetailsPK histItemGroupDetailsPK)
        throws com.stpl.app.NoSuchHistItemGroupDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(histItemGroupDetailsPK);
    }

    public static com.stpl.app.model.HistItemGroupDetails updateImpl(
        com.stpl.app.model.HistItemGroupDetails histItemGroupDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(histItemGroupDetails);
    }

    /**
    * Returns the hist item group details with the primary key or throws a {@link com.stpl.app.NoSuchHistItemGroupDetailsException} if it could not be found.
    *
    * @param histItemGroupDetailsPK the primary key of the hist item group details
    * @return the hist item group details
    * @throws com.stpl.app.NoSuchHistItemGroupDetailsException if a hist item group details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HistItemGroupDetails findByPrimaryKey(
        HistItemGroupDetailsPK histItemGroupDetailsPK)
        throws com.stpl.app.NoSuchHistItemGroupDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(histItemGroupDetailsPK);
    }

    /**
    * Returns the hist item group details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param histItemGroupDetailsPK the primary key of the hist item group details
    * @return the hist item group details, or <code>null</code> if a hist item group details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HistItemGroupDetails fetchByPrimaryKey(
        HistItemGroupDetailsPK histItemGroupDetailsPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(histItemGroupDetailsPK);
    }

    /**
    * Returns all the hist item group detailses.
    *
    * @return the hist item group detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.HistItemGroupDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the hist item group detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistItemGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hist item group detailses
    * @param end the upper bound of the range of hist item group detailses (not inclusive)
    * @return the range of hist item group detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.HistItemGroupDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the hist item group detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistItemGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hist item group detailses
    * @param end the upper bound of the range of hist item group detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of hist item group detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.HistItemGroupDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the hist item group detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of hist item group detailses.
    *
    * @return the number of hist item group detailses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static HistItemGroupDetailsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (HistItemGroupDetailsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    HistItemGroupDetailsPersistence.class.getName());

            ReferenceRegistry.registerReference(HistItemGroupDetailsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(HistItemGroupDetailsPersistence persistence) {
    }
}
