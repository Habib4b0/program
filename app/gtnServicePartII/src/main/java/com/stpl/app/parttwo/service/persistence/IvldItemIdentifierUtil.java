package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.IvldItemIdentifier;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the ivld item identifier service. This utility wraps {@link IvldItemIdentifierPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldItemIdentifierPersistence
 * @see IvldItemIdentifierPersistenceImpl
 * @generated
 */
public class IvldItemIdentifierUtil {
    private static IvldItemIdentifierPersistence _persistence;

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
    public static void clearCache(IvldItemIdentifier ivldItemIdentifier) {
        getPersistence().clearCache(ivldItemIdentifier);
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
    public static List<IvldItemIdentifier> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<IvldItemIdentifier> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<IvldItemIdentifier> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static IvldItemIdentifier update(
        IvldItemIdentifier ivldItemIdentifier) throws SystemException {
        return getPersistence().update(ivldItemIdentifier);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static IvldItemIdentifier update(
        IvldItemIdentifier ivldItemIdentifier, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(ivldItemIdentifier, serviceContext);
    }

    /**
    * Caches the ivld item identifier in the entity cache if it is enabled.
    *
    * @param ivldItemIdentifier the ivld item identifier
    */
    public static void cacheResult(
        com.stpl.app.parttwo.model.IvldItemIdentifier ivldItemIdentifier) {
        getPersistence().cacheResult(ivldItemIdentifier);
    }

    /**
    * Caches the ivld item identifiers in the entity cache if it is enabled.
    *
    * @param ivldItemIdentifiers the ivld item identifiers
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.IvldItemIdentifier> ivldItemIdentifiers) {
        getPersistence().cacheResult(ivldItemIdentifiers);
    }

    /**
    * Creates a new ivld item identifier with the primary key. Does not add the ivld item identifier to the database.
    *
    * @param ivldItemIdentifierSid the primary key for the new ivld item identifier
    * @return the new ivld item identifier
    */
    public static com.stpl.app.parttwo.model.IvldItemIdentifier create(
        int ivldItemIdentifierSid) {
        return getPersistence().create(ivldItemIdentifierSid);
    }

    /**
    * Removes the ivld item identifier with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldItemIdentifierSid the primary key of the ivld item identifier
    * @return the ivld item identifier that was removed
    * @throws com.stpl.app.parttwo.NoSuchIvldItemIdentifierException if a ivld item identifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.IvldItemIdentifier remove(
        int ivldItemIdentifierSid)
        throws com.stpl.app.parttwo.NoSuchIvldItemIdentifierException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(ivldItemIdentifierSid);
    }

    public static com.stpl.app.parttwo.model.IvldItemIdentifier updateImpl(
        com.stpl.app.parttwo.model.IvldItemIdentifier ivldItemIdentifier)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(ivldItemIdentifier);
    }

    /**
    * Returns the ivld item identifier with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchIvldItemIdentifierException} if it could not be found.
    *
    * @param ivldItemIdentifierSid the primary key of the ivld item identifier
    * @return the ivld item identifier
    * @throws com.stpl.app.parttwo.NoSuchIvldItemIdentifierException if a ivld item identifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.IvldItemIdentifier findByPrimaryKey(
        int ivldItemIdentifierSid)
        throws com.stpl.app.parttwo.NoSuchIvldItemIdentifierException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(ivldItemIdentifierSid);
    }

    /**
    * Returns the ivld item identifier with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldItemIdentifierSid the primary key of the ivld item identifier
    * @return the ivld item identifier, or <code>null</code> if a ivld item identifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.IvldItemIdentifier fetchByPrimaryKey(
        int ivldItemIdentifierSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(ivldItemIdentifierSid);
    }

    /**
    * Returns all the ivld item identifiers.
    *
    * @return the ivld item identifiers
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.IvldItemIdentifier> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the ivld item identifiers.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld item identifiers
    * @param end the upper bound of the range of ivld item identifiers (not inclusive)
    * @return the range of ivld item identifiers
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.IvldItemIdentifier> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the ivld item identifiers.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld item identifiers
    * @param end the upper bound of the range of ivld item identifiers (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ivld item identifiers
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.IvldItemIdentifier> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the ivld item identifiers from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of ivld item identifiers.
    *
    * @return the number of ivld item identifiers
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static IvldItemIdentifierPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (IvldItemIdentifierPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.parttwo.service.ClpSerializer.getServletContextName(),
                    IvldItemIdentifierPersistence.class.getName());

            ReferenceRegistry.registerReference(IvldItemIdentifierUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(IvldItemIdentifierPersistence persistence) {
    }
}
