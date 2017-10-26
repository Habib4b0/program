package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.CffAdditionalInfo;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the cff additional info service. This utility wraps {@link CffAdditionalInfoPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CffAdditionalInfoPersistence
 * @see CffAdditionalInfoPersistenceImpl
 * @generated
 */
public class CffAdditionalInfoUtil {
    private static CffAdditionalInfoPersistence _persistence;

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
    public static void clearCache(CffAdditionalInfo cffAdditionalInfo) {
        getPersistence().clearCache(cffAdditionalInfo);
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
    public static List<CffAdditionalInfo> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<CffAdditionalInfo> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<CffAdditionalInfo> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static CffAdditionalInfo update(CffAdditionalInfo cffAdditionalInfo)
        throws SystemException {
        return getPersistence().update(cffAdditionalInfo);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static CffAdditionalInfo update(
        CffAdditionalInfo cffAdditionalInfo, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(cffAdditionalInfo, serviceContext);
    }

    /**
    * Caches the cff additional info in the entity cache if it is enabled.
    *
    * @param cffAdditionalInfo the cff additional info
    */
    public static void cacheResult(
        com.stpl.app.parttwo.model.CffAdditionalInfo cffAdditionalInfo) {
        getPersistence().cacheResult(cffAdditionalInfo);
    }

    /**
    * Caches the cff additional infos in the entity cache if it is enabled.
    *
    * @param cffAdditionalInfos the cff additional infos
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.CffAdditionalInfo> cffAdditionalInfos) {
        getPersistence().cacheResult(cffAdditionalInfos);
    }

    /**
    * Creates a new cff additional info with the primary key. Does not add the cff additional info to the database.
    *
    * @param cffAdditionalInfoSid the primary key for the new cff additional info
    * @return the new cff additional info
    */
    public static com.stpl.app.parttwo.model.CffAdditionalInfo create(
        int cffAdditionalInfoSid) {
        return getPersistence().create(cffAdditionalInfoSid);
    }

    /**
    * Removes the cff additional info with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param cffAdditionalInfoSid the primary key of the cff additional info
    * @return the cff additional info that was removed
    * @throws com.stpl.app.parttwo.NoSuchCffAdditionalInfoException if a cff additional info with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.CffAdditionalInfo remove(
        int cffAdditionalInfoSid)
        throws com.stpl.app.parttwo.NoSuchCffAdditionalInfoException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(cffAdditionalInfoSid);
    }

    public static com.stpl.app.parttwo.model.CffAdditionalInfo updateImpl(
        com.stpl.app.parttwo.model.CffAdditionalInfo cffAdditionalInfo)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(cffAdditionalInfo);
    }

    /**
    * Returns the cff additional info with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchCffAdditionalInfoException} if it could not be found.
    *
    * @param cffAdditionalInfoSid the primary key of the cff additional info
    * @return the cff additional info
    * @throws com.stpl.app.parttwo.NoSuchCffAdditionalInfoException if a cff additional info with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.CffAdditionalInfo findByPrimaryKey(
        int cffAdditionalInfoSid)
        throws com.stpl.app.parttwo.NoSuchCffAdditionalInfoException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(cffAdditionalInfoSid);
    }

    /**
    * Returns the cff additional info with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param cffAdditionalInfoSid the primary key of the cff additional info
    * @return the cff additional info, or <code>null</code> if a cff additional info with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.CffAdditionalInfo fetchByPrimaryKey(
        int cffAdditionalInfoSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(cffAdditionalInfoSid);
    }

    /**
    * Returns all the cff additional infos.
    *
    * @return the cff additional infos
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.CffAdditionalInfo> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the cff additional infos.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffAdditionalInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cff additional infos
    * @param end the upper bound of the range of cff additional infos (not inclusive)
    * @return the range of cff additional infos
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.CffAdditionalInfo> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the cff additional infos.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffAdditionalInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cff additional infos
    * @param end the upper bound of the range of cff additional infos (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of cff additional infos
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.CffAdditionalInfo> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the cff additional infos from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of cff additional infos.
    *
    * @return the number of cff additional infos
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static CffAdditionalInfoPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (CffAdditionalInfoPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.parttwo.service.ClpSerializer.getServletContextName(),
                    CffAdditionalInfoPersistence.class.getName());

            ReferenceRegistry.registerReference(CffAdditionalInfoUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(CffAdditionalInfoPersistence persistence) {
    }
}
