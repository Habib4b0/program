package com.stpl.app.service.persistence;

import com.stpl.app.model.DocDetails;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the doc details service. This utility wraps {@link DocDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see DocDetailsPersistence
 * @see DocDetailsPersistenceImpl
 * @generated
 */
public class DocDetailsUtil {
    private static DocDetailsPersistence _persistence;

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
    public static void clearCache(DocDetails docDetails) {
        getPersistence().clearCache(docDetails);
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
    public static List<DocDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<DocDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<DocDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static DocDetails update(DocDetails docDetails)
        throws SystemException {
        return getPersistence().update(docDetails);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static DocDetails update(DocDetails docDetails,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(docDetails, serviceContext);
    }

    /**
    * Caches the doc details in the entity cache if it is enabled.
    *
    * @param docDetails the doc details
    */
    public static void cacheResult(com.stpl.app.model.DocDetails docDetails) {
        getPersistence().cacheResult(docDetails);
    }

    /**
    * Caches the doc detailses in the entity cache if it is enabled.
    *
    * @param docDetailses the doc detailses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.DocDetails> docDetailses) {
        getPersistence().cacheResult(docDetailses);
    }

    /**
    * Creates a new doc details with the primary key. Does not add the doc details to the database.
    *
    * @param docDetailsId the primary key for the new doc details
    * @return the new doc details
    */
    public static com.stpl.app.model.DocDetails create(int docDetailsId) {
        return getPersistence().create(docDetailsId);
    }

    /**
    * Removes the doc details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param docDetailsId the primary key of the doc details
    * @return the doc details that was removed
    * @throws com.stpl.app.NoSuchDocDetailsException if a doc details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.DocDetails remove(int docDetailsId)
        throws com.stpl.app.NoSuchDocDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(docDetailsId);
    }

    public static com.stpl.app.model.DocDetails updateImpl(
        com.stpl.app.model.DocDetails docDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(docDetails);
    }

    /**
    * Returns the doc details with the primary key or throws a {@link com.stpl.app.NoSuchDocDetailsException} if it could not be found.
    *
    * @param docDetailsId the primary key of the doc details
    * @return the doc details
    * @throws com.stpl.app.NoSuchDocDetailsException if a doc details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.DocDetails findByPrimaryKey(
        int docDetailsId)
        throws com.stpl.app.NoSuchDocDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(docDetailsId);
    }

    /**
    * Returns the doc details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param docDetailsId the primary key of the doc details
    * @return the doc details, or <code>null</code> if a doc details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.DocDetails fetchByPrimaryKey(
        int docDetailsId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(docDetailsId);
    }

    /**
    * Returns all the doc detailses.
    *
    * @return the doc detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.DocDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the doc detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DocDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of doc detailses
    * @param end the upper bound of the range of doc detailses (not inclusive)
    * @return the range of doc detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.DocDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the doc detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DocDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of doc detailses
    * @param end the upper bound of the range of doc detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of doc detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.DocDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the doc detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of doc detailses.
    *
    * @return the number of doc detailses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static DocDetailsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (DocDetailsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    DocDetailsPersistence.class.getName());

            ReferenceRegistry.registerReference(DocDetailsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(DocDetailsPersistence persistence) {
    }
}
