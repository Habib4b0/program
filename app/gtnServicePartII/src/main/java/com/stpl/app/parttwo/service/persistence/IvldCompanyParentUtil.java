package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.IvldCompanyParent;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the ivld company parent service. This utility wraps {@link IvldCompanyParentPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldCompanyParentPersistence
 * @see IvldCompanyParentPersistenceImpl
 * @generated
 */
public class IvldCompanyParentUtil {
    private static IvldCompanyParentPersistence _persistence;

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
    public static void clearCache(IvldCompanyParent ivldCompanyParent) {
        getPersistence().clearCache(ivldCompanyParent);
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
    public static List<IvldCompanyParent> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<IvldCompanyParent> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<IvldCompanyParent> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static IvldCompanyParent update(IvldCompanyParent ivldCompanyParent)
        throws SystemException {
        return getPersistence().update(ivldCompanyParent);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static IvldCompanyParent update(
        IvldCompanyParent ivldCompanyParent, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(ivldCompanyParent, serviceContext);
    }

    /**
    * Caches the ivld company parent in the entity cache if it is enabled.
    *
    * @param ivldCompanyParent the ivld company parent
    */
    public static void cacheResult(
        com.stpl.app.parttwo.model.IvldCompanyParent ivldCompanyParent) {
        getPersistence().cacheResult(ivldCompanyParent);
    }

    /**
    * Caches the ivld company parents in the entity cache if it is enabled.
    *
    * @param ivldCompanyParents the ivld company parents
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.IvldCompanyParent> ivldCompanyParents) {
        getPersistence().cacheResult(ivldCompanyParents);
    }

    /**
    * Creates a new ivld company parent with the primary key. Does not add the ivld company parent to the database.
    *
    * @param ivldCompanyParentSid the primary key for the new ivld company parent
    * @return the new ivld company parent
    */
    public static com.stpl.app.parttwo.model.IvldCompanyParent create(
        int ivldCompanyParentSid) {
        return getPersistence().create(ivldCompanyParentSid);
    }

    /**
    * Removes the ivld company parent with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldCompanyParentSid the primary key of the ivld company parent
    * @return the ivld company parent that was removed
    * @throws com.stpl.app.parttwo.NoSuchIvldCompanyParentException if a ivld company parent with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.IvldCompanyParent remove(
        int ivldCompanyParentSid)
        throws com.stpl.app.parttwo.NoSuchIvldCompanyParentException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(ivldCompanyParentSid);
    }

    public static com.stpl.app.parttwo.model.IvldCompanyParent updateImpl(
        com.stpl.app.parttwo.model.IvldCompanyParent ivldCompanyParent)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(ivldCompanyParent);
    }

    /**
    * Returns the ivld company parent with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchIvldCompanyParentException} if it could not be found.
    *
    * @param ivldCompanyParentSid the primary key of the ivld company parent
    * @return the ivld company parent
    * @throws com.stpl.app.parttwo.NoSuchIvldCompanyParentException if a ivld company parent with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.IvldCompanyParent findByPrimaryKey(
        int ivldCompanyParentSid)
        throws com.stpl.app.parttwo.NoSuchIvldCompanyParentException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(ivldCompanyParentSid);
    }

    /**
    * Returns the ivld company parent with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldCompanyParentSid the primary key of the ivld company parent
    * @return the ivld company parent, or <code>null</code> if a ivld company parent with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.IvldCompanyParent fetchByPrimaryKey(
        int ivldCompanyParentSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(ivldCompanyParentSid);
    }

    /**
    * Returns all the ivld company parents.
    *
    * @return the ivld company parents
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.IvldCompanyParent> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the ivld company parents.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyParentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld company parents
    * @param end the upper bound of the range of ivld company parents (not inclusive)
    * @return the range of ivld company parents
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.IvldCompanyParent> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the ivld company parents.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyParentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld company parents
    * @param end the upper bound of the range of ivld company parents (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ivld company parents
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.IvldCompanyParent> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the ivld company parents from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of ivld company parents.
    *
    * @return the number of ivld company parents
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static IvldCompanyParentPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (IvldCompanyParentPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.parttwo.service.ClpSerializer.getServletContextName(),
                    IvldCompanyParentPersistence.class.getName());

            ReferenceRegistry.registerReference(IvldCompanyParentUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(IvldCompanyParentPersistence persistence) {
    }
}
