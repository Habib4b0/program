package com.stpl.app.service.persistence;

import com.stpl.app.model.PsContractDetails;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the ps contract details service. This utility wraps {@link PsContractDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see PsContractDetailsPersistence
 * @see PsContractDetailsPersistenceImpl
 * @generated
 */
public class PsContractDetailsUtil {
    private static PsContractDetailsPersistence _persistence;

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
    public static void clearCache(PsContractDetails psContractDetails) {
        getPersistence().clearCache(psContractDetails);
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
    public static List<PsContractDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<PsContractDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<PsContractDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static PsContractDetails update(PsContractDetails psContractDetails)
        throws SystemException {
        return getPersistence().update(psContractDetails);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static PsContractDetails update(
        PsContractDetails psContractDetails, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(psContractDetails, serviceContext);
    }

    /**
    * Caches the ps contract details in the entity cache if it is enabled.
    *
    * @param psContractDetails the ps contract details
    */
    public static void cacheResult(
        com.stpl.app.model.PsContractDetails psContractDetails) {
        getPersistence().cacheResult(psContractDetails);
    }

    /**
    * Caches the ps contract detailses in the entity cache if it is enabled.
    *
    * @param psContractDetailses the ps contract detailses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.PsContractDetails> psContractDetailses) {
        getPersistence().cacheResult(psContractDetailses);
    }

    /**
    * Creates a new ps contract details with the primary key. Does not add the ps contract details to the database.
    *
    * @param psContractDetailsSid the primary key for the new ps contract details
    * @return the new ps contract details
    */
    public static com.stpl.app.model.PsContractDetails create(
        int psContractDetailsSid) {
        return getPersistence().create(psContractDetailsSid);
    }

    /**
    * Removes the ps contract details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param psContractDetailsSid the primary key of the ps contract details
    * @return the ps contract details that was removed
    * @throws com.stpl.app.NoSuchPsContractDetailsException if a ps contract details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.PsContractDetails remove(
        int psContractDetailsSid)
        throws com.stpl.app.NoSuchPsContractDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(psContractDetailsSid);
    }

    public static com.stpl.app.model.PsContractDetails updateImpl(
        com.stpl.app.model.PsContractDetails psContractDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(psContractDetails);
    }

    /**
    * Returns the ps contract details with the primary key or throws a {@link com.stpl.app.NoSuchPsContractDetailsException} if it could not be found.
    *
    * @param psContractDetailsSid the primary key of the ps contract details
    * @return the ps contract details
    * @throws com.stpl.app.NoSuchPsContractDetailsException if a ps contract details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.PsContractDetails findByPrimaryKey(
        int psContractDetailsSid)
        throws com.stpl.app.NoSuchPsContractDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(psContractDetailsSid);
    }

    /**
    * Returns the ps contract details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param psContractDetailsSid the primary key of the ps contract details
    * @return the ps contract details, or <code>null</code> if a ps contract details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.PsContractDetails fetchByPrimaryKey(
        int psContractDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(psContractDetailsSid);
    }

    /**
    * Returns all the ps contract detailses.
    *
    * @return the ps contract detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.PsContractDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the ps contract detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ps contract detailses
    * @param end the upper bound of the range of ps contract detailses (not inclusive)
    * @return the range of ps contract detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.PsContractDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the ps contract detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ps contract detailses
    * @param end the upper bound of the range of ps contract detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ps contract detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.PsContractDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the ps contract detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of ps contract detailses.
    *
    * @return the number of ps contract detailses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static PsContractDetailsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (PsContractDetailsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    PsContractDetailsPersistence.class.getName());

            ReferenceRegistry.registerReference(PsContractDetailsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(PsContractDetailsPersistence persistence) {
    }
}
