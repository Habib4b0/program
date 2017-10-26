package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.IvldCompanyTradeClass;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the ivld company trade class service. This utility wraps {@link IvldCompanyTradeClassPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldCompanyTradeClassPersistence
 * @see IvldCompanyTradeClassPersistenceImpl
 * @generated
 */
public class IvldCompanyTradeClassUtil {
    private static IvldCompanyTradeClassPersistence _persistence;

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
    public static void clearCache(IvldCompanyTradeClass ivldCompanyTradeClass) {
        getPersistence().clearCache(ivldCompanyTradeClass);
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
    public static List<IvldCompanyTradeClass> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<IvldCompanyTradeClass> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<IvldCompanyTradeClass> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static IvldCompanyTradeClass update(
        IvldCompanyTradeClass ivldCompanyTradeClass) throws SystemException {
        return getPersistence().update(ivldCompanyTradeClass);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static IvldCompanyTradeClass update(
        IvldCompanyTradeClass ivldCompanyTradeClass,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(ivldCompanyTradeClass, serviceContext);
    }

    /**
    * Caches the ivld company trade class in the entity cache if it is enabled.
    *
    * @param ivldCompanyTradeClass the ivld company trade class
    */
    public static void cacheResult(
        com.stpl.app.parttwo.model.IvldCompanyTradeClass ivldCompanyTradeClass) {
        getPersistence().cacheResult(ivldCompanyTradeClass);
    }

    /**
    * Caches the ivld company trade classes in the entity cache if it is enabled.
    *
    * @param ivldCompanyTradeClasses the ivld company trade classes
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.IvldCompanyTradeClass> ivldCompanyTradeClasses) {
        getPersistence().cacheResult(ivldCompanyTradeClasses);
    }

    /**
    * Creates a new ivld company trade class with the primary key. Does not add the ivld company trade class to the database.
    *
    * @param ivldCompanyTradeClassSid the primary key for the new ivld company trade class
    * @return the new ivld company trade class
    */
    public static com.stpl.app.parttwo.model.IvldCompanyTradeClass create(
        int ivldCompanyTradeClassSid) {
        return getPersistence().create(ivldCompanyTradeClassSid);
    }

    /**
    * Removes the ivld company trade class with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldCompanyTradeClassSid the primary key of the ivld company trade class
    * @return the ivld company trade class that was removed
    * @throws com.stpl.app.parttwo.NoSuchIvldCompanyTradeClassException if a ivld company trade class with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.IvldCompanyTradeClass remove(
        int ivldCompanyTradeClassSid)
        throws com.stpl.app.parttwo.NoSuchIvldCompanyTradeClassException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(ivldCompanyTradeClassSid);
    }

    public static com.stpl.app.parttwo.model.IvldCompanyTradeClass updateImpl(
        com.stpl.app.parttwo.model.IvldCompanyTradeClass ivldCompanyTradeClass)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(ivldCompanyTradeClass);
    }

    /**
    * Returns the ivld company trade class with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchIvldCompanyTradeClassException} if it could not be found.
    *
    * @param ivldCompanyTradeClassSid the primary key of the ivld company trade class
    * @return the ivld company trade class
    * @throws com.stpl.app.parttwo.NoSuchIvldCompanyTradeClassException if a ivld company trade class with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.IvldCompanyTradeClass findByPrimaryKey(
        int ivldCompanyTradeClassSid)
        throws com.stpl.app.parttwo.NoSuchIvldCompanyTradeClassException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(ivldCompanyTradeClassSid);
    }

    /**
    * Returns the ivld company trade class with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldCompanyTradeClassSid the primary key of the ivld company trade class
    * @return the ivld company trade class, or <code>null</code> if a ivld company trade class with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.IvldCompanyTradeClass fetchByPrimaryKey(
        int ivldCompanyTradeClassSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(ivldCompanyTradeClassSid);
    }

    /**
    * Returns all the ivld company trade classes.
    *
    * @return the ivld company trade classes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.IvldCompanyTradeClass> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the ivld company trade classes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld company trade classes
    * @param end the upper bound of the range of ivld company trade classes (not inclusive)
    * @return the range of ivld company trade classes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.IvldCompanyTradeClass> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the ivld company trade classes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld company trade classes
    * @param end the upper bound of the range of ivld company trade classes (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ivld company trade classes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.IvldCompanyTradeClass> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the ivld company trade classes from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of ivld company trade classes.
    *
    * @return the number of ivld company trade classes
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static IvldCompanyTradeClassPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (IvldCompanyTradeClassPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.parttwo.service.ClpSerializer.getServletContextName(),
                    IvldCompanyTradeClassPersistence.class.getName());

            ReferenceRegistry.registerReference(IvldCompanyTradeClassUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(IvldCompanyTradeClassPersistence persistence) {
    }
}
