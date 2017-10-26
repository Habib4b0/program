package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldItemHierarchyDefinition;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the ivld item hierarchy definition service. This utility wraps {@link IvldItemHierarchyDefinitionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldItemHierarchyDefinitionPersistence
 * @see IvldItemHierarchyDefinitionPersistenceImpl
 * @generated
 */
public class IvldItemHierarchyDefinitionUtil {
    private static IvldItemHierarchyDefinitionPersistence _persistence;

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
        IvldItemHierarchyDefinition ivldItemHierarchyDefinition) {
        getPersistence().clearCache(ivldItemHierarchyDefinition);
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
    public static List<IvldItemHierarchyDefinition> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<IvldItemHierarchyDefinition> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<IvldItemHierarchyDefinition> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static IvldItemHierarchyDefinition update(
        IvldItemHierarchyDefinition ivldItemHierarchyDefinition)
        throws SystemException {
        return getPersistence().update(ivldItemHierarchyDefinition);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static IvldItemHierarchyDefinition update(
        IvldItemHierarchyDefinition ivldItemHierarchyDefinition,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence()
                   .update(ivldItemHierarchyDefinition, serviceContext);
    }

    /**
    * Caches the ivld item hierarchy definition in the entity cache if it is enabled.
    *
    * @param ivldItemHierarchyDefinition the ivld item hierarchy definition
    */
    public static void cacheResult(
        com.stpl.app.model.IvldItemHierarchyDefinition ivldItemHierarchyDefinition) {
        getPersistence().cacheResult(ivldItemHierarchyDefinition);
    }

    /**
    * Caches the ivld item hierarchy definitions in the entity cache if it is enabled.
    *
    * @param ivldItemHierarchyDefinitions the ivld item hierarchy definitions
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.IvldItemHierarchyDefinition> ivldItemHierarchyDefinitions) {
        getPersistence().cacheResult(ivldItemHierarchyDefinitions);
    }

    /**
    * Creates a new ivld item hierarchy definition with the primary key. Does not add the ivld item hierarchy definition to the database.
    *
    * @param ivldItemHierarchyDefinitionSid the primary key for the new ivld item hierarchy definition
    * @return the new ivld item hierarchy definition
    */
    public static com.stpl.app.model.IvldItemHierarchyDefinition create(
        int ivldItemHierarchyDefinitionSid) {
        return getPersistence().create(ivldItemHierarchyDefinitionSid);
    }

    /**
    * Removes the ivld item hierarchy definition with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldItemHierarchyDefinitionSid the primary key of the ivld item hierarchy definition
    * @return the ivld item hierarchy definition that was removed
    * @throws com.stpl.app.NoSuchIvldItemHierarchyDefinitionException if a ivld item hierarchy definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldItemHierarchyDefinition remove(
        int ivldItemHierarchyDefinitionSid)
        throws com.stpl.app.NoSuchIvldItemHierarchyDefinitionException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(ivldItemHierarchyDefinitionSid);
    }

    public static com.stpl.app.model.IvldItemHierarchyDefinition updateImpl(
        com.stpl.app.model.IvldItemHierarchyDefinition ivldItemHierarchyDefinition)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(ivldItemHierarchyDefinition);
    }

    /**
    * Returns the ivld item hierarchy definition with the primary key or throws a {@link com.stpl.app.NoSuchIvldItemHierarchyDefinitionException} if it could not be found.
    *
    * @param ivldItemHierarchyDefinitionSid the primary key of the ivld item hierarchy definition
    * @return the ivld item hierarchy definition
    * @throws com.stpl.app.NoSuchIvldItemHierarchyDefinitionException if a ivld item hierarchy definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldItemHierarchyDefinition findByPrimaryKey(
        int ivldItemHierarchyDefinitionSid)
        throws com.stpl.app.NoSuchIvldItemHierarchyDefinitionException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(ivldItemHierarchyDefinitionSid);
    }

    /**
    * Returns the ivld item hierarchy definition with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldItemHierarchyDefinitionSid the primary key of the ivld item hierarchy definition
    * @return the ivld item hierarchy definition, or <code>null</code> if a ivld item hierarchy definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldItemHierarchyDefinition fetchByPrimaryKey(
        int ivldItemHierarchyDefinitionSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(ivldItemHierarchyDefinitionSid);
    }

    /**
    * Returns all the ivld item hierarchy definitions.
    *
    * @return the ivld item hierarchy definitions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IvldItemHierarchyDefinition> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the ivld item hierarchy definitions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldItemHierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld item hierarchy definitions
    * @param end the upper bound of the range of ivld item hierarchy definitions (not inclusive)
    * @return the range of ivld item hierarchy definitions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IvldItemHierarchyDefinition> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the ivld item hierarchy definitions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldItemHierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld item hierarchy definitions
    * @param end the upper bound of the range of ivld item hierarchy definitions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ivld item hierarchy definitions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IvldItemHierarchyDefinition> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the ivld item hierarchy definitions from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of ivld item hierarchy definitions.
    *
    * @return the number of ivld item hierarchy definitions
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static IvldItemHierarchyDefinitionPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (IvldItemHierarchyDefinitionPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    IvldItemHierarchyDefinitionPersistence.class.getName());

            ReferenceRegistry.registerReference(IvldItemHierarchyDefinitionUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(
        IvldItemHierarchyDefinitionPersistence persistence) {
    }
}
