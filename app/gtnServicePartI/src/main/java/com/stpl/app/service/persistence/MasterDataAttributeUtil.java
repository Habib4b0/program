package com.stpl.app.service.persistence;

import com.stpl.app.model.MasterDataAttribute;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the master data attribute service. This utility wraps {@link MasterDataAttributePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MasterDataAttributePersistence
 * @see MasterDataAttributePersistenceImpl
 * @generated
 */
public class MasterDataAttributeUtil {
    private static MasterDataAttributePersistence _persistence;

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
    public static void clearCache(MasterDataAttribute masterDataAttribute) {
        getPersistence().clearCache(masterDataAttribute);
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
    public static List<MasterDataAttribute> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<MasterDataAttribute> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<MasterDataAttribute> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static MasterDataAttribute update(
        MasterDataAttribute masterDataAttribute) throws SystemException {
        return getPersistence().update(masterDataAttribute);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static MasterDataAttribute update(
        MasterDataAttribute masterDataAttribute, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(masterDataAttribute, serviceContext);
    }

    /**
    * Returns all the master data attributes where masterType = &#63;.
    *
    * @param masterType the master type
    * @return the matching master data attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.MasterDataAttribute> findByMasterType(
        java.lang.String masterType)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByMasterType(masterType);
    }

    /**
    * Returns a range of all the master data attributes where masterType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param masterType the master type
    * @param start the lower bound of the range of master data attributes
    * @param end the upper bound of the range of master data attributes (not inclusive)
    * @return the range of matching master data attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.MasterDataAttribute> findByMasterType(
        java.lang.String masterType, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByMasterType(masterType, start, end);
    }

    /**
    * Returns an ordered range of all the master data attributes where masterType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param masterType the master type
    * @param start the lower bound of the range of master data attributes
    * @param end the upper bound of the range of master data attributes (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching master data attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.MasterDataAttribute> findByMasterType(
        java.lang.String masterType, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByMasterType(masterType, start, end, orderByComparator);
    }

    /**
    * Returns the first master data attribute in the ordered set where masterType = &#63;.
    *
    * @param masterType the master type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching master data attribute
    * @throws com.stpl.app.NoSuchMasterDataAttributeException if a matching master data attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MasterDataAttribute findByMasterType_First(
        java.lang.String masterType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchMasterDataAttributeException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByMasterType_First(masterType, orderByComparator);
    }

    /**
    * Returns the first master data attribute in the ordered set where masterType = &#63;.
    *
    * @param masterType the master type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching master data attribute, or <code>null</code> if a matching master data attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MasterDataAttribute fetchByMasterType_First(
        java.lang.String masterType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByMasterType_First(masterType, orderByComparator);
    }

    /**
    * Returns the last master data attribute in the ordered set where masterType = &#63;.
    *
    * @param masterType the master type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching master data attribute
    * @throws com.stpl.app.NoSuchMasterDataAttributeException if a matching master data attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MasterDataAttribute findByMasterType_Last(
        java.lang.String masterType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchMasterDataAttributeException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByMasterType_Last(masterType, orderByComparator);
    }

    /**
    * Returns the last master data attribute in the ordered set where masterType = &#63;.
    *
    * @param masterType the master type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching master data attribute, or <code>null</code> if a matching master data attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MasterDataAttribute fetchByMasterType_Last(
        java.lang.String masterType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByMasterType_Last(masterType, orderByComparator);
    }

    /**
    * Returns the master data attributes before and after the current master data attribute in the ordered set where masterType = &#63;.
    *
    * @param masterDataAttributeSid the primary key of the current master data attribute
    * @param masterType the master type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next master data attribute
    * @throws com.stpl.app.NoSuchMasterDataAttributeException if a master data attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MasterDataAttribute[] findByMasterType_PrevAndNext(
        int masterDataAttributeSid, java.lang.String masterType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchMasterDataAttributeException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByMasterType_PrevAndNext(masterDataAttributeSid,
            masterType, orderByComparator);
    }

    /**
    * Removes all the master data attributes where masterType = &#63; from the database.
    *
    * @param masterType the master type
    * @throws SystemException if a system exception occurred
    */
    public static void removeByMasterType(java.lang.String masterType)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByMasterType(masterType);
    }

    /**
    * Returns the number of master data attributes where masterType = &#63;.
    *
    * @param masterType the master type
    * @return the number of matching master data attributes
    * @throws SystemException if a system exception occurred
    */
    public static int countByMasterType(java.lang.String masterType)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByMasterType(masterType);
    }

    /**
    * Returns all the master data attributes where masterAttribute = &#63;.
    *
    * @param masterAttribute the master attribute
    * @return the matching master data attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.MasterDataAttribute> findByMasterAttribute(
        java.lang.String masterAttribute)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByMasterAttribute(masterAttribute);
    }

    /**
    * Returns a range of all the master data attributes where masterAttribute = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param masterAttribute the master attribute
    * @param start the lower bound of the range of master data attributes
    * @param end the upper bound of the range of master data attributes (not inclusive)
    * @return the range of matching master data attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.MasterDataAttribute> findByMasterAttribute(
        java.lang.String masterAttribute, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByMasterAttribute(masterAttribute, start, end);
    }

    /**
    * Returns an ordered range of all the master data attributes where masterAttribute = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param masterAttribute the master attribute
    * @param start the lower bound of the range of master data attributes
    * @param end the upper bound of the range of master data attributes (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching master data attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.MasterDataAttribute> findByMasterAttribute(
        java.lang.String masterAttribute, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByMasterAttribute(masterAttribute, start, end,
            orderByComparator);
    }

    /**
    * Returns the first master data attribute in the ordered set where masterAttribute = &#63;.
    *
    * @param masterAttribute the master attribute
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching master data attribute
    * @throws com.stpl.app.NoSuchMasterDataAttributeException if a matching master data attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MasterDataAttribute findByMasterAttribute_First(
        java.lang.String masterAttribute,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchMasterDataAttributeException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByMasterAttribute_First(masterAttribute,
            orderByComparator);
    }

    /**
    * Returns the first master data attribute in the ordered set where masterAttribute = &#63;.
    *
    * @param masterAttribute the master attribute
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching master data attribute, or <code>null</code> if a matching master data attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MasterDataAttribute fetchByMasterAttribute_First(
        java.lang.String masterAttribute,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByMasterAttribute_First(masterAttribute,
            orderByComparator);
    }

    /**
    * Returns the last master data attribute in the ordered set where masterAttribute = &#63;.
    *
    * @param masterAttribute the master attribute
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching master data attribute
    * @throws com.stpl.app.NoSuchMasterDataAttributeException if a matching master data attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MasterDataAttribute findByMasterAttribute_Last(
        java.lang.String masterAttribute,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchMasterDataAttributeException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByMasterAttribute_Last(masterAttribute,
            orderByComparator);
    }

    /**
    * Returns the last master data attribute in the ordered set where masterAttribute = &#63;.
    *
    * @param masterAttribute the master attribute
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching master data attribute, or <code>null</code> if a matching master data attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MasterDataAttribute fetchByMasterAttribute_Last(
        java.lang.String masterAttribute,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByMasterAttribute_Last(masterAttribute,
            orderByComparator);
    }

    /**
    * Returns the master data attributes before and after the current master data attribute in the ordered set where masterAttribute = &#63;.
    *
    * @param masterDataAttributeSid the primary key of the current master data attribute
    * @param masterAttribute the master attribute
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next master data attribute
    * @throws com.stpl.app.NoSuchMasterDataAttributeException if a master data attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MasterDataAttribute[] findByMasterAttribute_PrevAndNext(
        int masterDataAttributeSid, java.lang.String masterAttribute,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchMasterDataAttributeException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByMasterAttribute_PrevAndNext(masterDataAttributeSid,
            masterAttribute, orderByComparator);
    }

    /**
    * Removes all the master data attributes where masterAttribute = &#63; from the database.
    *
    * @param masterAttribute the master attribute
    * @throws SystemException if a system exception occurred
    */
    public static void removeByMasterAttribute(java.lang.String masterAttribute)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByMasterAttribute(masterAttribute);
    }

    /**
    * Returns the number of master data attributes where masterAttribute = &#63;.
    *
    * @param masterAttribute the master attribute
    * @return the number of matching master data attributes
    * @throws SystemException if a system exception occurred
    */
    public static int countByMasterAttribute(java.lang.String masterAttribute)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByMasterAttribute(masterAttribute);
    }

    /**
    * Returns all the master data attributes where masterId = &#63;.
    *
    * @param masterId the master ID
    * @return the matching master data attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.MasterDataAttribute> findByMasterId(
        java.lang.String masterId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByMasterId(masterId);
    }

    /**
    * Returns a range of all the master data attributes where masterId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param masterId the master ID
    * @param start the lower bound of the range of master data attributes
    * @param end the upper bound of the range of master data attributes (not inclusive)
    * @return the range of matching master data attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.MasterDataAttribute> findByMasterId(
        java.lang.String masterId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByMasterId(masterId, start, end);
    }

    /**
    * Returns an ordered range of all the master data attributes where masterId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param masterId the master ID
    * @param start the lower bound of the range of master data attributes
    * @param end the upper bound of the range of master data attributes (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching master data attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.MasterDataAttribute> findByMasterId(
        java.lang.String masterId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByMasterId(masterId, start, end, orderByComparator);
    }

    /**
    * Returns the first master data attribute in the ordered set where masterId = &#63;.
    *
    * @param masterId the master ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching master data attribute
    * @throws com.stpl.app.NoSuchMasterDataAttributeException if a matching master data attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MasterDataAttribute findByMasterId_First(
        java.lang.String masterId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchMasterDataAttributeException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByMasterId_First(masterId, orderByComparator);
    }

    /**
    * Returns the first master data attribute in the ordered set where masterId = &#63;.
    *
    * @param masterId the master ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching master data attribute, or <code>null</code> if a matching master data attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MasterDataAttribute fetchByMasterId_First(
        java.lang.String masterId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByMasterId_First(masterId, orderByComparator);
    }

    /**
    * Returns the last master data attribute in the ordered set where masterId = &#63;.
    *
    * @param masterId the master ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching master data attribute
    * @throws com.stpl.app.NoSuchMasterDataAttributeException if a matching master data attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MasterDataAttribute findByMasterId_Last(
        java.lang.String masterId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchMasterDataAttributeException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByMasterId_Last(masterId, orderByComparator);
    }

    /**
    * Returns the last master data attribute in the ordered set where masterId = &#63;.
    *
    * @param masterId the master ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching master data attribute, or <code>null</code> if a matching master data attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MasterDataAttribute fetchByMasterId_Last(
        java.lang.String masterId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByMasterId_Last(masterId, orderByComparator);
    }

    /**
    * Returns the master data attributes before and after the current master data attribute in the ordered set where masterId = &#63;.
    *
    * @param masterDataAttributeSid the primary key of the current master data attribute
    * @param masterId the master ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next master data attribute
    * @throws com.stpl.app.NoSuchMasterDataAttributeException if a master data attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MasterDataAttribute[] findByMasterId_PrevAndNext(
        int masterDataAttributeSid, java.lang.String masterId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchMasterDataAttributeException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByMasterId_PrevAndNext(masterDataAttributeSid,
            masterId, orderByComparator);
    }

    /**
    * Removes all the master data attributes where masterId = &#63; from the database.
    *
    * @param masterId the master ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByMasterId(java.lang.String masterId)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByMasterId(masterId);
    }

    /**
    * Returns the number of master data attributes where masterId = &#63;.
    *
    * @param masterId the master ID
    * @return the number of matching master data attributes
    * @throws SystemException if a system exception occurred
    */
    public static int countByMasterId(java.lang.String masterId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByMasterId(masterId);
    }

    /**
    * Returns all the master data attributes where masterAttribute = &#63; and masterId = &#63; and masterType = &#63;.
    *
    * @param masterAttribute the master attribute
    * @param masterId the master ID
    * @param masterType the master type
    * @return the matching master data attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.MasterDataAttribute> findByMasterDataAttributeUnique(
        java.lang.String masterAttribute, java.lang.String masterId,
        java.lang.String masterType)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByMasterDataAttributeUnique(masterAttribute, masterId,
            masterType);
    }

    /**
    * Returns a range of all the master data attributes where masterAttribute = &#63; and masterId = &#63; and masterType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param masterAttribute the master attribute
    * @param masterId the master ID
    * @param masterType the master type
    * @param start the lower bound of the range of master data attributes
    * @param end the upper bound of the range of master data attributes (not inclusive)
    * @return the range of matching master data attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.MasterDataAttribute> findByMasterDataAttributeUnique(
        java.lang.String masterAttribute, java.lang.String masterId,
        java.lang.String masterType, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByMasterDataAttributeUnique(masterAttribute, masterId,
            masterType, start, end);
    }

    /**
    * Returns an ordered range of all the master data attributes where masterAttribute = &#63; and masterId = &#63; and masterType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param masterAttribute the master attribute
    * @param masterId the master ID
    * @param masterType the master type
    * @param start the lower bound of the range of master data attributes
    * @param end the upper bound of the range of master data attributes (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching master data attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.MasterDataAttribute> findByMasterDataAttributeUnique(
        java.lang.String masterAttribute, java.lang.String masterId,
        java.lang.String masterType, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByMasterDataAttributeUnique(masterAttribute, masterId,
            masterType, start, end, orderByComparator);
    }

    /**
    * Returns the first master data attribute in the ordered set where masterAttribute = &#63; and masterId = &#63; and masterType = &#63;.
    *
    * @param masterAttribute the master attribute
    * @param masterId the master ID
    * @param masterType the master type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching master data attribute
    * @throws com.stpl.app.NoSuchMasterDataAttributeException if a matching master data attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MasterDataAttribute findByMasterDataAttributeUnique_First(
        java.lang.String masterAttribute, java.lang.String masterId,
        java.lang.String masterType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchMasterDataAttributeException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByMasterDataAttributeUnique_First(masterAttribute,
            masterId, masterType, orderByComparator);
    }

    /**
    * Returns the first master data attribute in the ordered set where masterAttribute = &#63; and masterId = &#63; and masterType = &#63;.
    *
    * @param masterAttribute the master attribute
    * @param masterId the master ID
    * @param masterType the master type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching master data attribute, or <code>null</code> if a matching master data attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MasterDataAttribute fetchByMasterDataAttributeUnique_First(
        java.lang.String masterAttribute, java.lang.String masterId,
        java.lang.String masterType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByMasterDataAttributeUnique_First(masterAttribute,
            masterId, masterType, orderByComparator);
    }

    /**
    * Returns the last master data attribute in the ordered set where masterAttribute = &#63; and masterId = &#63; and masterType = &#63;.
    *
    * @param masterAttribute the master attribute
    * @param masterId the master ID
    * @param masterType the master type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching master data attribute
    * @throws com.stpl.app.NoSuchMasterDataAttributeException if a matching master data attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MasterDataAttribute findByMasterDataAttributeUnique_Last(
        java.lang.String masterAttribute, java.lang.String masterId,
        java.lang.String masterType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchMasterDataAttributeException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByMasterDataAttributeUnique_Last(masterAttribute,
            masterId, masterType, orderByComparator);
    }

    /**
    * Returns the last master data attribute in the ordered set where masterAttribute = &#63; and masterId = &#63; and masterType = &#63;.
    *
    * @param masterAttribute the master attribute
    * @param masterId the master ID
    * @param masterType the master type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching master data attribute, or <code>null</code> if a matching master data attribute could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MasterDataAttribute fetchByMasterDataAttributeUnique_Last(
        java.lang.String masterAttribute, java.lang.String masterId,
        java.lang.String masterType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByMasterDataAttributeUnique_Last(masterAttribute,
            masterId, masterType, orderByComparator);
    }

    /**
    * Returns the master data attributes before and after the current master data attribute in the ordered set where masterAttribute = &#63; and masterId = &#63; and masterType = &#63;.
    *
    * @param masterDataAttributeSid the primary key of the current master data attribute
    * @param masterAttribute the master attribute
    * @param masterId the master ID
    * @param masterType the master type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next master data attribute
    * @throws com.stpl.app.NoSuchMasterDataAttributeException if a master data attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MasterDataAttribute[] findByMasterDataAttributeUnique_PrevAndNext(
        int masterDataAttributeSid, java.lang.String masterAttribute,
        java.lang.String masterId, java.lang.String masterType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchMasterDataAttributeException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByMasterDataAttributeUnique_PrevAndNext(masterDataAttributeSid,
            masterAttribute, masterId, masterType, orderByComparator);
    }

    /**
    * Removes all the master data attributes where masterAttribute = &#63; and masterId = &#63; and masterType = &#63; from the database.
    *
    * @param masterAttribute the master attribute
    * @param masterId the master ID
    * @param masterType the master type
    * @throws SystemException if a system exception occurred
    */
    public static void removeByMasterDataAttributeUnique(
        java.lang.String masterAttribute, java.lang.String masterId,
        java.lang.String masterType)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence()
            .removeByMasterDataAttributeUnique(masterAttribute, masterId,
            masterType);
    }

    /**
    * Returns the number of master data attributes where masterAttribute = &#63; and masterId = &#63; and masterType = &#63;.
    *
    * @param masterAttribute the master attribute
    * @param masterId the master ID
    * @param masterType the master type
    * @return the number of matching master data attributes
    * @throws SystemException if a system exception occurred
    */
    public static int countByMasterDataAttributeUnique(
        java.lang.String masterAttribute, java.lang.String masterId,
        java.lang.String masterType)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByMasterDataAttributeUnique(masterAttribute, masterId,
            masterType);
    }

    /**
    * Caches the master data attribute in the entity cache if it is enabled.
    *
    * @param masterDataAttribute the master data attribute
    */
    public static void cacheResult(
        com.stpl.app.model.MasterDataAttribute masterDataAttribute) {
        getPersistence().cacheResult(masterDataAttribute);
    }

    /**
    * Caches the master data attributes in the entity cache if it is enabled.
    *
    * @param masterDataAttributes the master data attributes
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.MasterDataAttribute> masterDataAttributes) {
        getPersistence().cacheResult(masterDataAttributes);
    }

    /**
    * Creates a new master data attribute with the primary key. Does not add the master data attribute to the database.
    *
    * @param masterDataAttributeSid the primary key for the new master data attribute
    * @return the new master data attribute
    */
    public static com.stpl.app.model.MasterDataAttribute create(
        int masterDataAttributeSid) {
        return getPersistence().create(masterDataAttributeSid);
    }

    /**
    * Removes the master data attribute with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param masterDataAttributeSid the primary key of the master data attribute
    * @return the master data attribute that was removed
    * @throws com.stpl.app.NoSuchMasterDataAttributeException if a master data attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MasterDataAttribute remove(
        int masterDataAttributeSid)
        throws com.stpl.app.NoSuchMasterDataAttributeException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(masterDataAttributeSid);
    }

    public static com.stpl.app.model.MasterDataAttribute updateImpl(
        com.stpl.app.model.MasterDataAttribute masterDataAttribute)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(masterDataAttribute);
    }

    /**
    * Returns the master data attribute with the primary key or throws a {@link com.stpl.app.NoSuchMasterDataAttributeException} if it could not be found.
    *
    * @param masterDataAttributeSid the primary key of the master data attribute
    * @return the master data attribute
    * @throws com.stpl.app.NoSuchMasterDataAttributeException if a master data attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MasterDataAttribute findByPrimaryKey(
        int masterDataAttributeSid)
        throws com.stpl.app.NoSuchMasterDataAttributeException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(masterDataAttributeSid);
    }

    /**
    * Returns the master data attribute with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param masterDataAttributeSid the primary key of the master data attribute
    * @return the master data attribute, or <code>null</code> if a master data attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MasterDataAttribute fetchByPrimaryKey(
        int masterDataAttributeSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(masterDataAttributeSid);
    }

    /**
    * Returns all the master data attributes.
    *
    * @return the master data attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.MasterDataAttribute> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the master data attributes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of master data attributes
    * @param end the upper bound of the range of master data attributes (not inclusive)
    * @return the range of master data attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.MasterDataAttribute> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the master data attributes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of master data attributes
    * @param end the upper bound of the range of master data attributes (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of master data attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.MasterDataAttribute> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the master data attributes from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of master data attributes.
    *
    * @return the number of master data attributes
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static MasterDataAttributePersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (MasterDataAttributePersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    MasterDataAttributePersistence.class.getName());

            ReferenceRegistry.registerReference(MasterDataAttributeUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(MasterDataAttributePersistence persistence) {
    }
}
