package com.stpl.app.service.persistence;

import com.stpl.app.model.HistCompanyGroupDetails;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the hist company group details service. This utility wraps {@link HistCompanyGroupDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HistCompanyGroupDetailsPersistence
 * @see HistCompanyGroupDetailsPersistenceImpl
 * @generated
 */
public class HistCompanyGroupDetailsUtil {
    private static HistCompanyGroupDetailsPersistence _persistence;

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
    public static void clearCache(
        HistCompanyGroupDetails histCompanyGroupDetails) {
        getPersistence().clearCache(histCompanyGroupDetails);
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
    public static List<HistCompanyGroupDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<HistCompanyGroupDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<HistCompanyGroupDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static HistCompanyGroupDetails update(
        HistCompanyGroupDetails histCompanyGroupDetails)
        throws SystemException {
        return getPersistence().update(histCompanyGroupDetails);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static HistCompanyGroupDetails update(
        HistCompanyGroupDetails histCompanyGroupDetails,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(histCompanyGroupDetails, serviceContext);
    }

    /**
    * Caches the hist company group details in the entity cache if it is enabled.
    *
    * @param histCompanyGroupDetails the hist company group details
    */
    public static void cacheResult(
        com.stpl.app.model.HistCompanyGroupDetails histCompanyGroupDetails) {
        getPersistence().cacheResult(histCompanyGroupDetails);
    }

    /**
    * Caches the hist company group detailses in the entity cache if it is enabled.
    *
    * @param histCompanyGroupDetailses the hist company group detailses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.HistCompanyGroupDetails> histCompanyGroupDetailses) {
        getPersistence().cacheResult(histCompanyGroupDetailses);
    }

    /**
    * Creates a new hist company group details with the primary key. Does not add the hist company group details to the database.
    *
    * @param histCompanyGroupDetailsPK the primary key for the new hist company group details
    * @return the new hist company group details
    */
    public static com.stpl.app.model.HistCompanyGroupDetails create(
        HistCompanyGroupDetailsPK histCompanyGroupDetailsPK) {
        return getPersistence().create(histCompanyGroupDetailsPK);
    }

    /**
    * Removes the hist company group details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param histCompanyGroupDetailsPK the primary key of the hist company group details
    * @return the hist company group details that was removed
    * @throws com.stpl.app.NoSuchHistCompanyGroupDetailsException if a hist company group details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HistCompanyGroupDetails remove(
        HistCompanyGroupDetailsPK histCompanyGroupDetailsPK)
        throws com.stpl.app.NoSuchHistCompanyGroupDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(histCompanyGroupDetailsPK);
    }

    public static com.stpl.app.model.HistCompanyGroupDetails updateImpl(
        com.stpl.app.model.HistCompanyGroupDetails histCompanyGroupDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(histCompanyGroupDetails);
    }

    /**
    * Returns the hist company group details with the primary key or throws a {@link com.stpl.app.NoSuchHistCompanyGroupDetailsException} if it could not be found.
    *
    * @param histCompanyGroupDetailsPK the primary key of the hist company group details
    * @return the hist company group details
    * @throws com.stpl.app.NoSuchHistCompanyGroupDetailsException if a hist company group details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HistCompanyGroupDetails findByPrimaryKey(
        HistCompanyGroupDetailsPK histCompanyGroupDetailsPK)
        throws com.stpl.app.NoSuchHistCompanyGroupDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(histCompanyGroupDetailsPK);
    }

    /**
    * Returns the hist company group details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param histCompanyGroupDetailsPK the primary key of the hist company group details
    * @return the hist company group details, or <code>null</code> if a hist company group details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HistCompanyGroupDetails fetchByPrimaryKey(
        HistCompanyGroupDetailsPK histCompanyGroupDetailsPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(histCompanyGroupDetailsPK);
    }

    /**
    * Returns all the hist company group detailses.
    *
    * @return the hist company group detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.HistCompanyGroupDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the hist company group detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistCompanyGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hist company group detailses
    * @param end the upper bound of the range of hist company group detailses (not inclusive)
    * @return the range of hist company group detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.HistCompanyGroupDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the hist company group detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistCompanyGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hist company group detailses
    * @param end the upper bound of the range of hist company group detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of hist company group detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.HistCompanyGroupDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the hist company group detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of hist company group detailses.
    *
    * @return the number of hist company group detailses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static HistCompanyGroupDetailsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (HistCompanyGroupDetailsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    HistCompanyGroupDetailsPersistence.class.getName());

            ReferenceRegistry.registerReference(HistCompanyGroupDetailsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(HistCompanyGroupDetailsPersistence persistence) {
    }
}
