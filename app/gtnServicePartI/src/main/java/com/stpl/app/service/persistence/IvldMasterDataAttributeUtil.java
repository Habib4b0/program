package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldMasterDataAttribute;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the ivld master data attribute service. This utility wraps {@link IvldMasterDataAttributePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldMasterDataAttributePersistence
 * @see IvldMasterDataAttributePersistenceImpl
 * @generated
 */
public class IvldMasterDataAttributeUtil {
    private static IvldMasterDataAttributePersistence _persistence;

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
        IvldMasterDataAttribute ivldMasterDataAttribute) {
        getPersistence().clearCache(ivldMasterDataAttribute);
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
    public static List<IvldMasterDataAttribute> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<IvldMasterDataAttribute> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<IvldMasterDataAttribute> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static IvldMasterDataAttribute update(
        IvldMasterDataAttribute ivldMasterDataAttribute)
        throws SystemException {
        return getPersistence().update(ivldMasterDataAttribute);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static IvldMasterDataAttribute update(
        IvldMasterDataAttribute ivldMasterDataAttribute,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(ivldMasterDataAttribute, serviceContext);
    }

    /**
    * Caches the ivld master data attribute in the entity cache if it is enabled.
    *
    * @param ivldMasterDataAttribute the ivld master data attribute
    */
    public static void cacheResult(
        com.stpl.app.model.IvldMasterDataAttribute ivldMasterDataAttribute) {
        getPersistence().cacheResult(ivldMasterDataAttribute);
    }

    /**
    * Caches the ivld master data attributes in the entity cache if it is enabled.
    *
    * @param ivldMasterDataAttributes the ivld master data attributes
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.IvldMasterDataAttribute> ivldMasterDataAttributes) {
        getPersistence().cacheResult(ivldMasterDataAttributes);
    }

    /**
    * Creates a new ivld master data attribute with the primary key. Does not add the ivld master data attribute to the database.
    *
    * @param ivldMasterDataAtbteSid the primary key for the new ivld master data attribute
    * @return the new ivld master data attribute
    */
    public static com.stpl.app.model.IvldMasterDataAttribute create(
        int ivldMasterDataAtbteSid) {
        return getPersistence().create(ivldMasterDataAtbteSid);
    }

    /**
    * Removes the ivld master data attribute with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldMasterDataAtbteSid the primary key of the ivld master data attribute
    * @return the ivld master data attribute that was removed
    * @throws com.stpl.app.NoSuchIvldMasterDataAttributeException if a ivld master data attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldMasterDataAttribute remove(
        int ivldMasterDataAtbteSid)
        throws com.stpl.app.NoSuchIvldMasterDataAttributeException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(ivldMasterDataAtbteSid);
    }

    public static com.stpl.app.model.IvldMasterDataAttribute updateImpl(
        com.stpl.app.model.IvldMasterDataAttribute ivldMasterDataAttribute)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(ivldMasterDataAttribute);
    }

    /**
    * Returns the ivld master data attribute with the primary key or throws a {@link com.stpl.app.NoSuchIvldMasterDataAttributeException} if it could not be found.
    *
    * @param ivldMasterDataAtbteSid the primary key of the ivld master data attribute
    * @return the ivld master data attribute
    * @throws com.stpl.app.NoSuchIvldMasterDataAttributeException if a ivld master data attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldMasterDataAttribute findByPrimaryKey(
        int ivldMasterDataAtbteSid)
        throws com.stpl.app.NoSuchIvldMasterDataAttributeException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(ivldMasterDataAtbteSid);
    }

    /**
    * Returns the ivld master data attribute with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldMasterDataAtbteSid the primary key of the ivld master data attribute
    * @return the ivld master data attribute, or <code>null</code> if a ivld master data attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldMasterDataAttribute fetchByPrimaryKey(
        int ivldMasterDataAtbteSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(ivldMasterDataAtbteSid);
    }

    /**
    * Returns all the ivld master data attributes.
    *
    * @return the ivld master data attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IvldMasterDataAttribute> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the ivld master data attributes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldMasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld master data attributes
    * @param end the upper bound of the range of ivld master data attributes (not inclusive)
    * @return the range of ivld master data attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IvldMasterDataAttribute> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the ivld master data attributes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldMasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld master data attributes
    * @param end the upper bound of the range of ivld master data attributes (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ivld master data attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IvldMasterDataAttribute> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the ivld master data attributes from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of ivld master data attributes.
    *
    * @return the number of ivld master data attributes
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static IvldMasterDataAttributePersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (IvldMasterDataAttributePersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    IvldMasterDataAttributePersistence.class.getName());

            ReferenceRegistry.registerReference(IvldMasterDataAttributeUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(IvldMasterDataAttributePersistence persistence) {
    }
}
