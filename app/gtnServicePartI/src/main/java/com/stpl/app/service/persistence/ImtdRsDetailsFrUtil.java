package com.stpl.app.service.persistence;

import com.stpl.app.model.ImtdRsDetailsFr;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the imtd rs details fr service. This utility wraps {@link ImtdRsDetailsFrPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ImtdRsDetailsFrPersistence
 * @see ImtdRsDetailsFrPersistenceImpl
 * @generated
 */
public class ImtdRsDetailsFrUtil {
    private static ImtdRsDetailsFrPersistence _persistence;

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
    public static void clearCache(ImtdRsDetailsFr imtdRsDetailsFr) {
        getPersistence().clearCache(imtdRsDetailsFr);
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
    public static List<ImtdRsDetailsFr> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ImtdRsDetailsFr> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ImtdRsDetailsFr> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static ImtdRsDetailsFr update(ImtdRsDetailsFr imtdRsDetailsFr)
        throws SystemException {
        return getPersistence().update(imtdRsDetailsFr);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static ImtdRsDetailsFr update(ImtdRsDetailsFr imtdRsDetailsFr,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(imtdRsDetailsFr, serviceContext);
    }

    /**
    * Caches the imtd rs details fr in the entity cache if it is enabled.
    *
    * @param imtdRsDetailsFr the imtd rs details fr
    */
    public static void cacheResult(
        com.stpl.app.model.ImtdRsDetailsFr imtdRsDetailsFr) {
        getPersistence().cacheResult(imtdRsDetailsFr);
    }

    /**
    * Caches the imtd rs details frs in the entity cache if it is enabled.
    *
    * @param imtdRsDetailsFrs the imtd rs details frs
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.ImtdRsDetailsFr> imtdRsDetailsFrs) {
        getPersistence().cacheResult(imtdRsDetailsFrs);
    }

    /**
    * Creates a new imtd rs details fr with the primary key. Does not add the imtd rs details fr to the database.
    *
    * @param imtdRsDetailsFrSid the primary key for the new imtd rs details fr
    * @return the new imtd rs details fr
    */
    public static com.stpl.app.model.ImtdRsDetailsFr create(
        int imtdRsDetailsFrSid) {
        return getPersistence().create(imtdRsDetailsFrSid);
    }

    /**
    * Removes the imtd rs details fr with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param imtdRsDetailsFrSid the primary key of the imtd rs details fr
    * @return the imtd rs details fr that was removed
    * @throws com.stpl.app.NoSuchImtdRsDetailsFrException if a imtd rs details fr with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdRsDetailsFr remove(
        int imtdRsDetailsFrSid)
        throws com.stpl.app.NoSuchImtdRsDetailsFrException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(imtdRsDetailsFrSid);
    }

    public static com.stpl.app.model.ImtdRsDetailsFr updateImpl(
        com.stpl.app.model.ImtdRsDetailsFr imtdRsDetailsFr)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(imtdRsDetailsFr);
    }

    /**
    * Returns the imtd rs details fr with the primary key or throws a {@link com.stpl.app.NoSuchImtdRsDetailsFrException} if it could not be found.
    *
    * @param imtdRsDetailsFrSid the primary key of the imtd rs details fr
    * @return the imtd rs details fr
    * @throws com.stpl.app.NoSuchImtdRsDetailsFrException if a imtd rs details fr with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdRsDetailsFr findByPrimaryKey(
        int imtdRsDetailsFrSid)
        throws com.stpl.app.NoSuchImtdRsDetailsFrException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(imtdRsDetailsFrSid);
    }

    /**
    * Returns the imtd rs details fr with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param imtdRsDetailsFrSid the primary key of the imtd rs details fr
    * @return the imtd rs details fr, or <code>null</code> if a imtd rs details fr with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdRsDetailsFr fetchByPrimaryKey(
        int imtdRsDetailsFrSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(imtdRsDetailsFrSid);
    }

    /**
    * Returns all the imtd rs details frs.
    *
    * @return the imtd rs details frs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ImtdRsDetailsFr> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the imtd rs details frs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdRsDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of imtd rs details frs
    * @param end the upper bound of the range of imtd rs details frs (not inclusive)
    * @return the range of imtd rs details frs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ImtdRsDetailsFr> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the imtd rs details frs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdRsDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of imtd rs details frs
    * @param end the upper bound of the range of imtd rs details frs (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of imtd rs details frs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ImtdRsDetailsFr> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the imtd rs details frs from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of imtd rs details frs.
    *
    * @return the number of imtd rs details frs
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ImtdRsDetailsFrPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ImtdRsDetailsFrPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    ImtdRsDetailsFrPersistence.class.getName());

            ReferenceRegistry.registerReference(ImtdRsDetailsFrUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ImtdRsDetailsFrPersistence persistence) {
    }
}
