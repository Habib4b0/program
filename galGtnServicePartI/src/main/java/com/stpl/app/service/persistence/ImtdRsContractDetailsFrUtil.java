package com.stpl.app.service.persistence;

import com.stpl.app.model.ImtdRsContractDetailsFr;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the imtd rs contract details fr service. This utility wraps {@link ImtdRsContractDetailsFrPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ImtdRsContractDetailsFrPersistence
 * @see ImtdRsContractDetailsFrPersistenceImpl
 * @generated
 */
public class ImtdRsContractDetailsFrUtil {
    private static ImtdRsContractDetailsFrPersistence _persistence;

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
        ImtdRsContractDetailsFr imtdRsContractDetailsFr) {
        getPersistence().clearCache(imtdRsContractDetailsFr);
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
    public static List<ImtdRsContractDetailsFr> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ImtdRsContractDetailsFr> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ImtdRsContractDetailsFr> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static ImtdRsContractDetailsFr update(
        ImtdRsContractDetailsFr imtdRsContractDetailsFr)
        throws SystemException {
        return getPersistence().update(imtdRsContractDetailsFr);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static ImtdRsContractDetailsFr update(
        ImtdRsContractDetailsFr imtdRsContractDetailsFr,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(imtdRsContractDetailsFr, serviceContext);
    }

    /**
    * Caches the imtd rs contract details fr in the entity cache if it is enabled.
    *
    * @param imtdRsContractDetailsFr the imtd rs contract details fr
    */
    public static void cacheResult(
        com.stpl.app.model.ImtdRsContractDetailsFr imtdRsContractDetailsFr) {
        getPersistence().cacheResult(imtdRsContractDetailsFr);
    }

    /**
    * Caches the imtd rs contract details frs in the entity cache if it is enabled.
    *
    * @param imtdRsContractDetailsFrs the imtd rs contract details frs
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.ImtdRsContractDetailsFr> imtdRsContractDetailsFrs) {
        getPersistence().cacheResult(imtdRsContractDetailsFrs);
    }

    /**
    * Creates a new imtd rs contract details fr with the primary key. Does not add the imtd rs contract details fr to the database.
    *
    * @param imtdRsContractDetailsFrSid the primary key for the new imtd rs contract details fr
    * @return the new imtd rs contract details fr
    */
    public static com.stpl.app.model.ImtdRsContractDetailsFr create(
        int imtdRsContractDetailsFrSid) {
        return getPersistence().create(imtdRsContractDetailsFrSid);
    }

    /**
    * Removes the imtd rs contract details fr with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param imtdRsContractDetailsFrSid the primary key of the imtd rs contract details fr
    * @return the imtd rs contract details fr that was removed
    * @throws com.stpl.app.NoSuchImtdRsContractDetailsFrException if a imtd rs contract details fr with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdRsContractDetailsFr remove(
        int imtdRsContractDetailsFrSid)
        throws com.stpl.app.NoSuchImtdRsContractDetailsFrException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(imtdRsContractDetailsFrSid);
    }

    public static com.stpl.app.model.ImtdRsContractDetailsFr updateImpl(
        com.stpl.app.model.ImtdRsContractDetailsFr imtdRsContractDetailsFr)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(imtdRsContractDetailsFr);
    }

    /**
    * Returns the imtd rs contract details fr with the primary key or throws a {@link com.stpl.app.NoSuchImtdRsContractDetailsFrException} if it could not be found.
    *
    * @param imtdRsContractDetailsFrSid the primary key of the imtd rs contract details fr
    * @return the imtd rs contract details fr
    * @throws com.stpl.app.NoSuchImtdRsContractDetailsFrException if a imtd rs contract details fr with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdRsContractDetailsFr findByPrimaryKey(
        int imtdRsContractDetailsFrSid)
        throws com.stpl.app.NoSuchImtdRsContractDetailsFrException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(imtdRsContractDetailsFrSid);
    }

    /**
    * Returns the imtd rs contract details fr with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param imtdRsContractDetailsFrSid the primary key of the imtd rs contract details fr
    * @return the imtd rs contract details fr, or <code>null</code> if a imtd rs contract details fr with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdRsContractDetailsFr fetchByPrimaryKey(
        int imtdRsContractDetailsFrSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(imtdRsContractDetailsFrSid);
    }

    /**
    * Returns all the imtd rs contract details frs.
    *
    * @return the imtd rs contract details frs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ImtdRsContractDetailsFr> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the imtd rs contract details frs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdRsContractDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of imtd rs contract details frs
    * @param end the upper bound of the range of imtd rs contract details frs (not inclusive)
    * @return the range of imtd rs contract details frs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ImtdRsContractDetailsFr> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the imtd rs contract details frs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdRsContractDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of imtd rs contract details frs
    * @param end the upper bound of the range of imtd rs contract details frs (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of imtd rs contract details frs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ImtdRsContractDetailsFr> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the imtd rs contract details frs from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of imtd rs contract details frs.
    *
    * @return the number of imtd rs contract details frs
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ImtdRsContractDetailsFrPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ImtdRsContractDetailsFrPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    ImtdRsContractDetailsFrPersistence.class.getName());

            ReferenceRegistry.registerReference(ImtdRsContractDetailsFrUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ImtdRsContractDetailsFrPersistence persistence) {
    }
}
