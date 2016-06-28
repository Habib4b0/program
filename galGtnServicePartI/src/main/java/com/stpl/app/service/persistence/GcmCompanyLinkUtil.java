package com.stpl.app.service.persistence;

import com.stpl.app.model.GcmCompanyLink;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the gcm company link service. This utility wraps {@link GcmCompanyLinkPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see GcmCompanyLinkPersistence
 * @see GcmCompanyLinkPersistenceImpl
 * @generated
 */
public class GcmCompanyLinkUtil {
    private static GcmCompanyLinkPersistence _persistence;

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
    public static void clearCache(GcmCompanyLink gcmCompanyLink) {
        getPersistence().clearCache(gcmCompanyLink);
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
    public static List<GcmCompanyLink> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<GcmCompanyLink> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<GcmCompanyLink> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static GcmCompanyLink update(GcmCompanyLink gcmCompanyLink)
        throws SystemException {
        return getPersistence().update(gcmCompanyLink);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static GcmCompanyLink update(GcmCompanyLink gcmCompanyLink,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(gcmCompanyLink, serviceContext);
    }

    /**
    * Caches the gcm company link in the entity cache if it is enabled.
    *
    * @param gcmCompanyLink the gcm company link
    */
    public static void cacheResult(
        com.stpl.app.model.GcmCompanyLink gcmCompanyLink) {
        getPersistence().cacheResult(gcmCompanyLink);
    }

    /**
    * Caches the gcm company links in the entity cache if it is enabled.
    *
    * @param gcmCompanyLinks the gcm company links
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.GcmCompanyLink> gcmCompanyLinks) {
        getPersistence().cacheResult(gcmCompanyLinks);
    }

    /**
    * Creates a new gcm company link with the primary key. Does not add the gcm company link to the database.
    *
    * @param gcmCompanyLinkSid the primary key for the new gcm company link
    * @return the new gcm company link
    */
    public static com.stpl.app.model.GcmCompanyLink create(
        int gcmCompanyLinkSid) {
        return getPersistence().create(gcmCompanyLinkSid);
    }

    /**
    * Removes the gcm company link with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param gcmCompanyLinkSid the primary key of the gcm company link
    * @return the gcm company link that was removed
    * @throws com.stpl.app.NoSuchGcmCompanyLinkException if a gcm company link with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GcmCompanyLink remove(
        int gcmCompanyLinkSid)
        throws com.stpl.app.NoSuchGcmCompanyLinkException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(gcmCompanyLinkSid);
    }

    public static com.stpl.app.model.GcmCompanyLink updateImpl(
        com.stpl.app.model.GcmCompanyLink gcmCompanyLink)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(gcmCompanyLink);
    }

    /**
    * Returns the gcm company link with the primary key or throws a {@link com.stpl.app.NoSuchGcmCompanyLinkException} if it could not be found.
    *
    * @param gcmCompanyLinkSid the primary key of the gcm company link
    * @return the gcm company link
    * @throws com.stpl.app.NoSuchGcmCompanyLinkException if a gcm company link with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GcmCompanyLink findByPrimaryKey(
        int gcmCompanyLinkSid)
        throws com.stpl.app.NoSuchGcmCompanyLinkException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(gcmCompanyLinkSid);
    }

    /**
    * Returns the gcm company link with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param gcmCompanyLinkSid the primary key of the gcm company link
    * @return the gcm company link, or <code>null</code> if a gcm company link with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.GcmCompanyLink fetchByPrimaryKey(
        int gcmCompanyLinkSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(gcmCompanyLinkSid);
    }

    /**
    * Returns all the gcm company links.
    *
    * @return the gcm company links
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.GcmCompanyLink> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the gcm company links.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GcmCompanyLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of gcm company links
    * @param end the upper bound of the range of gcm company links (not inclusive)
    * @return the range of gcm company links
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.GcmCompanyLink> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the gcm company links.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GcmCompanyLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of gcm company links
    * @param end the upper bound of the range of gcm company links (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of gcm company links
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.GcmCompanyLink> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the gcm company links from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of gcm company links.
    *
    * @return the number of gcm company links
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static GcmCompanyLinkPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (GcmCompanyLinkPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    GcmCompanyLinkPersistence.class.getName());

            ReferenceRegistry.registerReference(GcmCompanyLinkUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(GcmCompanyLinkPersistence persistence) {
    }
}
