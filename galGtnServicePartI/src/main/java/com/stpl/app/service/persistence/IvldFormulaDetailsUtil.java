package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldFormulaDetails;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the ivld formula details service. This utility wraps {@link IvldFormulaDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldFormulaDetailsPersistence
 * @see IvldFormulaDetailsPersistenceImpl
 * @generated
 */
public class IvldFormulaDetailsUtil {
    private static IvldFormulaDetailsPersistence _persistence;

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
    public static void clearCache(IvldFormulaDetails ivldFormulaDetails) {
        getPersistence().clearCache(ivldFormulaDetails);
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
    public static List<IvldFormulaDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<IvldFormulaDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<IvldFormulaDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static IvldFormulaDetails update(
        IvldFormulaDetails ivldFormulaDetails) throws SystemException {
        return getPersistence().update(ivldFormulaDetails);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static IvldFormulaDetails update(
        IvldFormulaDetails ivldFormulaDetails, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(ivldFormulaDetails, serviceContext);
    }

    /**
    * Caches the ivld formula details in the entity cache if it is enabled.
    *
    * @param ivldFormulaDetails the ivld formula details
    */
    public static void cacheResult(
        com.stpl.app.model.IvldFormulaDetails ivldFormulaDetails) {
        getPersistence().cacheResult(ivldFormulaDetails);
    }

    /**
    * Caches the ivld formula detailses in the entity cache if it is enabled.
    *
    * @param ivldFormulaDetailses the ivld formula detailses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.IvldFormulaDetails> ivldFormulaDetailses) {
        getPersistence().cacheResult(ivldFormulaDetailses);
    }

    /**
    * Creates a new ivld formula details with the primary key. Does not add the ivld formula details to the database.
    *
    * @param ivldFormulaDetailsSid the primary key for the new ivld formula details
    * @return the new ivld formula details
    */
    public static com.stpl.app.model.IvldFormulaDetails create(
        int ivldFormulaDetailsSid) {
        return getPersistence().create(ivldFormulaDetailsSid);
    }

    /**
    * Removes the ivld formula details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldFormulaDetailsSid the primary key of the ivld formula details
    * @return the ivld formula details that was removed
    * @throws com.stpl.app.NoSuchIvldFormulaDetailsException if a ivld formula details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldFormulaDetails remove(
        int ivldFormulaDetailsSid)
        throws com.stpl.app.NoSuchIvldFormulaDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(ivldFormulaDetailsSid);
    }

    public static com.stpl.app.model.IvldFormulaDetails updateImpl(
        com.stpl.app.model.IvldFormulaDetails ivldFormulaDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(ivldFormulaDetails);
    }

    /**
    * Returns the ivld formula details with the primary key or throws a {@link com.stpl.app.NoSuchIvldFormulaDetailsException} if it could not be found.
    *
    * @param ivldFormulaDetailsSid the primary key of the ivld formula details
    * @return the ivld formula details
    * @throws com.stpl.app.NoSuchIvldFormulaDetailsException if a ivld formula details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldFormulaDetails findByPrimaryKey(
        int ivldFormulaDetailsSid)
        throws com.stpl.app.NoSuchIvldFormulaDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(ivldFormulaDetailsSid);
    }

    /**
    * Returns the ivld formula details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldFormulaDetailsSid the primary key of the ivld formula details
    * @return the ivld formula details, or <code>null</code> if a ivld formula details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldFormulaDetails fetchByPrimaryKey(
        int ivldFormulaDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(ivldFormulaDetailsSid);
    }

    /**
    * Returns all the ivld formula detailses.
    *
    * @return the ivld formula detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IvldFormulaDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the ivld formula detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldFormulaDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld formula detailses
    * @param end the upper bound of the range of ivld formula detailses (not inclusive)
    * @return the range of ivld formula detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IvldFormulaDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the ivld formula detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldFormulaDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld formula detailses
    * @param end the upper bound of the range of ivld formula detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ivld formula detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IvldFormulaDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the ivld formula detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of ivld formula detailses.
    *
    * @return the number of ivld formula detailses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static IvldFormulaDetailsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (IvldFormulaDetailsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    IvldFormulaDetailsPersistence.class.getName());

            ReferenceRegistry.registerReference(IvldFormulaDetailsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(IvldFormulaDetailsPersistence persistence) {
    }
}
