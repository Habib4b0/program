package com.stpl.app.service.persistence;

import com.stpl.app.model.HierarchyDefinition;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the hierarchy definition service. This utility wraps {@link HierarchyDefinitionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HierarchyDefinitionPersistence
 * @see HierarchyDefinitionPersistenceImpl
 * @generated
 */
public class HierarchyDefinitionUtil {
    private static HierarchyDefinitionPersistence _persistence;

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
    public static void clearCache(HierarchyDefinition hierarchyDefinition) {
        getPersistence().clearCache(hierarchyDefinition);
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
    public static List<HierarchyDefinition> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<HierarchyDefinition> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<HierarchyDefinition> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static HierarchyDefinition update(
        HierarchyDefinition hierarchyDefinition) throws SystemException {
        return getPersistence().update(hierarchyDefinition);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static HierarchyDefinition update(
        HierarchyDefinition hierarchyDefinition, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(hierarchyDefinition, serviceContext);
    }

    /**
    * Caches the hierarchy definition in the entity cache if it is enabled.
    *
    * @param hierarchyDefinition the hierarchy definition
    */
    public static void cacheResult(
        com.stpl.app.model.HierarchyDefinition hierarchyDefinition) {
        getPersistence().cacheResult(hierarchyDefinition);
    }

    /**
    * Caches the hierarchy definitions in the entity cache if it is enabled.
    *
    * @param hierarchyDefinitions the hierarchy definitions
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.HierarchyDefinition> hierarchyDefinitions) {
        getPersistence().cacheResult(hierarchyDefinitions);
    }

    /**
    * Creates a new hierarchy definition with the primary key. Does not add the hierarchy definition to the database.
    *
    * @param hierarchyDefinitionSid the primary key for the new hierarchy definition
    * @return the new hierarchy definition
    */
    public static com.stpl.app.model.HierarchyDefinition create(
        int hierarchyDefinitionSid) {
        return getPersistence().create(hierarchyDefinitionSid);
    }

    /**
    * Removes the hierarchy definition with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param hierarchyDefinitionSid the primary key of the hierarchy definition
    * @return the hierarchy definition that was removed
    * @throws com.stpl.app.NoSuchHierarchyDefinitionException if a hierarchy definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HierarchyDefinition remove(
        int hierarchyDefinitionSid)
        throws com.stpl.app.NoSuchHierarchyDefinitionException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(hierarchyDefinitionSid);
    }

    public static com.stpl.app.model.HierarchyDefinition updateImpl(
        com.stpl.app.model.HierarchyDefinition hierarchyDefinition)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(hierarchyDefinition);
    }

    /**
    * Returns the hierarchy definition with the primary key or throws a {@link com.stpl.app.NoSuchHierarchyDefinitionException} if it could not be found.
    *
    * @param hierarchyDefinitionSid the primary key of the hierarchy definition
    * @return the hierarchy definition
    * @throws com.stpl.app.NoSuchHierarchyDefinitionException if a hierarchy definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HierarchyDefinition findByPrimaryKey(
        int hierarchyDefinitionSid)
        throws com.stpl.app.NoSuchHierarchyDefinitionException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(hierarchyDefinitionSid);
    }

    /**
    * Returns the hierarchy definition with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param hierarchyDefinitionSid the primary key of the hierarchy definition
    * @return the hierarchy definition, or <code>null</code> if a hierarchy definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HierarchyDefinition fetchByPrimaryKey(
        int hierarchyDefinitionSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(hierarchyDefinitionSid);
    }

    /**
    * Returns all the hierarchy definitions.
    *
    * @return the hierarchy definitions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.HierarchyDefinition> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the hierarchy definitions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hierarchy definitions
    * @param end the upper bound of the range of hierarchy definitions (not inclusive)
    * @return the range of hierarchy definitions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.HierarchyDefinition> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the hierarchy definitions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hierarchy definitions
    * @param end the upper bound of the range of hierarchy definitions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of hierarchy definitions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.HierarchyDefinition> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the hierarchy definitions from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of hierarchy definitions.
    *
    * @return the number of hierarchy definitions
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static HierarchyDefinitionPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (HierarchyDefinitionPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    HierarchyDefinitionPersistence.class.getName());

            ReferenceRegistry.registerReference(HierarchyDefinitionUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(HierarchyDefinitionPersistence persistence) {
    }
}
