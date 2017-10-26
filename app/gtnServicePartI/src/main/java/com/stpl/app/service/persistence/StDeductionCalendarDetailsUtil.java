package com.stpl.app.service.persistence;

import com.stpl.app.model.StDeductionCalendarDetails;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the st deduction calendar details service. This utility wraps {@link StDeductionCalendarDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StDeductionCalendarDetailsPersistence
 * @see StDeductionCalendarDetailsPersistenceImpl
 * @generated
 */
public class StDeductionCalendarDetailsUtil {
    private static StDeductionCalendarDetailsPersistence _persistence;

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
        StDeductionCalendarDetails stDeductionCalendarDetails) {
        getPersistence().clearCache(stDeductionCalendarDetails);
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
    public static List<StDeductionCalendarDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<StDeductionCalendarDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<StDeductionCalendarDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static StDeductionCalendarDetails update(
        StDeductionCalendarDetails stDeductionCalendarDetails)
        throws SystemException {
        return getPersistence().update(stDeductionCalendarDetails);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static StDeductionCalendarDetails update(
        StDeductionCalendarDetails stDeductionCalendarDetails,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence()
                   .update(stDeductionCalendarDetails, serviceContext);
    }

    /**
    * Caches the st deduction calendar details in the entity cache if it is enabled.
    *
    * @param stDeductionCalendarDetails the st deduction calendar details
    */
    public static void cacheResult(
        com.stpl.app.model.StDeductionCalendarDetails stDeductionCalendarDetails) {
        getPersistence().cacheResult(stDeductionCalendarDetails);
    }

    /**
    * Caches the st deduction calendar detailses in the entity cache if it is enabled.
    *
    * @param stDeductionCalendarDetailses the st deduction calendar detailses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.StDeductionCalendarDetails> stDeductionCalendarDetailses) {
        getPersistence().cacheResult(stDeductionCalendarDetailses);
    }

    /**
    * Creates a new st deduction calendar details with the primary key. Does not add the st deduction calendar details to the database.
    *
    * @param stDeductionCalendarDetailsPK the primary key for the new st deduction calendar details
    * @return the new st deduction calendar details
    */
    public static com.stpl.app.model.StDeductionCalendarDetails create(
        StDeductionCalendarDetailsPK stDeductionCalendarDetailsPK) {
        return getPersistence().create(stDeductionCalendarDetailsPK);
    }

    /**
    * Removes the st deduction calendar details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stDeductionCalendarDetailsPK the primary key of the st deduction calendar details
    * @return the st deduction calendar details that was removed
    * @throws com.stpl.app.NoSuchStDeductionCalendarDetailsException if a st deduction calendar details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StDeductionCalendarDetails remove(
        StDeductionCalendarDetailsPK stDeductionCalendarDetailsPK)
        throws com.stpl.app.NoSuchStDeductionCalendarDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(stDeductionCalendarDetailsPK);
    }

    public static com.stpl.app.model.StDeductionCalendarDetails updateImpl(
        com.stpl.app.model.StDeductionCalendarDetails stDeductionCalendarDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(stDeductionCalendarDetails);
    }

    /**
    * Returns the st deduction calendar details with the primary key or throws a {@link com.stpl.app.NoSuchStDeductionCalendarDetailsException} if it could not be found.
    *
    * @param stDeductionCalendarDetailsPK the primary key of the st deduction calendar details
    * @return the st deduction calendar details
    * @throws com.stpl.app.NoSuchStDeductionCalendarDetailsException if a st deduction calendar details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StDeductionCalendarDetails findByPrimaryKey(
        StDeductionCalendarDetailsPK stDeductionCalendarDetailsPK)
        throws com.stpl.app.NoSuchStDeductionCalendarDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(stDeductionCalendarDetailsPK);
    }

    /**
    * Returns the st deduction calendar details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param stDeductionCalendarDetailsPK the primary key of the st deduction calendar details
    * @return the st deduction calendar details, or <code>null</code> if a st deduction calendar details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StDeductionCalendarDetails fetchByPrimaryKey(
        StDeductionCalendarDetailsPK stDeductionCalendarDetailsPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(stDeductionCalendarDetailsPK);
    }

    /**
    * Returns all the st deduction calendar detailses.
    *
    * @return the st deduction calendar detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StDeductionCalendarDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the st deduction calendar detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StDeductionCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st deduction calendar detailses
    * @param end the upper bound of the range of st deduction calendar detailses (not inclusive)
    * @return the range of st deduction calendar detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StDeductionCalendarDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the st deduction calendar detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StDeductionCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st deduction calendar detailses
    * @param end the upper bound of the range of st deduction calendar detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of st deduction calendar detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StDeductionCalendarDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the st deduction calendar detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of st deduction calendar detailses.
    *
    * @return the number of st deduction calendar detailses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static StDeductionCalendarDetailsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (StDeductionCalendarDetailsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    StDeductionCalendarDetailsPersistence.class.getName());

            ReferenceRegistry.registerReference(StDeductionCalendarDetailsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(
        StDeductionCalendarDetailsPersistence persistence) {
    }
}
