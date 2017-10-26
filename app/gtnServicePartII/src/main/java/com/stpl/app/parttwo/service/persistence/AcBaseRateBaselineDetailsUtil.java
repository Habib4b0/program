package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.AcBaseRateBaselineDetails;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the ac base rate baseline details service. This utility wraps {@link AcBaseRateBaselineDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AcBaseRateBaselineDetailsPersistence
 * @see AcBaseRateBaselineDetailsPersistenceImpl
 * @generated
 */
public class AcBaseRateBaselineDetailsUtil {
    private static AcBaseRateBaselineDetailsPersistence _persistence;

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
        AcBaseRateBaselineDetails acBaseRateBaselineDetails) {
        getPersistence().clearCache(acBaseRateBaselineDetails);
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
    public static List<AcBaseRateBaselineDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<AcBaseRateBaselineDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<AcBaseRateBaselineDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static AcBaseRateBaselineDetails update(
        AcBaseRateBaselineDetails acBaseRateBaselineDetails)
        throws SystemException {
        return getPersistence().update(acBaseRateBaselineDetails);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static AcBaseRateBaselineDetails update(
        AcBaseRateBaselineDetails acBaseRateBaselineDetails,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(acBaseRateBaselineDetails, serviceContext);
    }

    /**
    * Caches the ac base rate baseline details in the entity cache if it is enabled.
    *
    * @param acBaseRateBaselineDetails the ac base rate baseline details
    */
    public static void cacheResult(
        com.stpl.app.parttwo.model.AcBaseRateBaselineDetails acBaseRateBaselineDetails) {
        getPersistence().cacheResult(acBaseRateBaselineDetails);
    }

    /**
    * Caches the ac base rate baseline detailses in the entity cache if it is enabled.
    *
    * @param acBaseRateBaselineDetailses the ac base rate baseline detailses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.AcBaseRateBaselineDetails> acBaseRateBaselineDetailses) {
        getPersistence().cacheResult(acBaseRateBaselineDetailses);
    }

    /**
    * Creates a new ac base rate baseline details with the primary key. Does not add the ac base rate baseline details to the database.
    *
    * @param acBrMethodologyDetailsSid the primary key for the new ac base rate baseline details
    * @return the new ac base rate baseline details
    */
    public static com.stpl.app.parttwo.model.AcBaseRateBaselineDetails create(
        int acBrMethodologyDetailsSid) {
        return getPersistence().create(acBrMethodologyDetailsSid);
    }

    /**
    * Removes the ac base rate baseline details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param acBrMethodologyDetailsSid the primary key of the ac base rate baseline details
    * @return the ac base rate baseline details that was removed
    * @throws com.stpl.app.parttwo.NoSuchAcBaseRateBaselineDetailsException if a ac base rate baseline details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.AcBaseRateBaselineDetails remove(
        int acBrMethodologyDetailsSid)
        throws com.stpl.app.parttwo.NoSuchAcBaseRateBaselineDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(acBrMethodologyDetailsSid);
    }

    public static com.stpl.app.parttwo.model.AcBaseRateBaselineDetails updateImpl(
        com.stpl.app.parttwo.model.AcBaseRateBaselineDetails acBaseRateBaselineDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(acBaseRateBaselineDetails);
    }

    /**
    * Returns the ac base rate baseline details with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchAcBaseRateBaselineDetailsException} if it could not be found.
    *
    * @param acBrMethodologyDetailsSid the primary key of the ac base rate baseline details
    * @return the ac base rate baseline details
    * @throws com.stpl.app.parttwo.NoSuchAcBaseRateBaselineDetailsException if a ac base rate baseline details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.AcBaseRateBaselineDetails findByPrimaryKey(
        int acBrMethodologyDetailsSid)
        throws com.stpl.app.parttwo.NoSuchAcBaseRateBaselineDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(acBrMethodologyDetailsSid);
    }

    /**
    * Returns the ac base rate baseline details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param acBrMethodologyDetailsSid the primary key of the ac base rate baseline details
    * @return the ac base rate baseline details, or <code>null</code> if a ac base rate baseline details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.AcBaseRateBaselineDetails fetchByPrimaryKey(
        int acBrMethodologyDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(acBrMethodologyDetailsSid);
    }

    /**
    * Returns all the ac base rate baseline detailses.
    *
    * @return the ac base rate baseline detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.AcBaseRateBaselineDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the ac base rate baseline detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AcBaseRateBaselineDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ac base rate baseline detailses
    * @param end the upper bound of the range of ac base rate baseline detailses (not inclusive)
    * @return the range of ac base rate baseline detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.AcBaseRateBaselineDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the ac base rate baseline detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AcBaseRateBaselineDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ac base rate baseline detailses
    * @param end the upper bound of the range of ac base rate baseline detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ac base rate baseline detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.AcBaseRateBaselineDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the ac base rate baseline detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of ac base rate baseline detailses.
    *
    * @return the number of ac base rate baseline detailses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static AcBaseRateBaselineDetailsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (AcBaseRateBaselineDetailsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.parttwo.service.ClpSerializer.getServletContextName(),
                    AcBaseRateBaselineDetailsPersistence.class.getName());

            ReferenceRegistry.registerReference(AcBaseRateBaselineDetailsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(AcBaseRateBaselineDetailsPersistence persistence) {
    }
}
