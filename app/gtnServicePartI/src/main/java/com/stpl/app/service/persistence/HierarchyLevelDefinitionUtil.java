package com.stpl.app.service.persistence;

import com.stpl.app.model.HierarchyLevelDefinition;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the hierarchy level definition service. This utility wraps {@link HierarchyLevelDefinitionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HierarchyLevelDefinitionPersistence
 * @see HierarchyLevelDefinitionPersistenceImpl
 * @generated
 */
public class HierarchyLevelDefinitionUtil {
    private static HierarchyLevelDefinitionPersistence _persistence;

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
        HierarchyLevelDefinition hierarchyLevelDefinition) {
        getPersistence().clearCache(hierarchyLevelDefinition);
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
    public static List<HierarchyLevelDefinition> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<HierarchyLevelDefinition> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<HierarchyLevelDefinition> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static HierarchyLevelDefinition update(
        HierarchyLevelDefinition hierarchyLevelDefinition)
        throws SystemException {
        return getPersistence().update(hierarchyLevelDefinition);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static HierarchyLevelDefinition update(
        HierarchyLevelDefinition hierarchyLevelDefinition,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(hierarchyLevelDefinition, serviceContext);
    }

    /**
    * Caches the hierarchy level definition in the entity cache if it is enabled.
    *
    * @param hierarchyLevelDefinition the hierarchy level definition
    */
    public static void cacheResult(
        com.stpl.app.model.HierarchyLevelDefinition hierarchyLevelDefinition) {
        getPersistence().cacheResult(hierarchyLevelDefinition);
    }

    /**
    * Caches the hierarchy level definitions in the entity cache if it is enabled.
    *
    * @param hierarchyLevelDefinitions the hierarchy level definitions
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.HierarchyLevelDefinition> hierarchyLevelDefinitions) {
        getPersistence().cacheResult(hierarchyLevelDefinitions);
    }

    /**
    * Creates a new hierarchy level definition with the primary key. Does not add the hierarchy level definition to the database.
    *
    * @param hierarchyLevelDefinitionSid the primary key for the new hierarchy level definition
    * @return the new hierarchy level definition
    */
    public static com.stpl.app.model.HierarchyLevelDefinition create(
        int hierarchyLevelDefinitionSid) {
        return getPersistence().create(hierarchyLevelDefinitionSid);
    }

    /**
    * Removes the hierarchy level definition with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param hierarchyLevelDefinitionSid the primary key of the hierarchy level definition
    * @return the hierarchy level definition that was removed
    * @throws com.stpl.app.NoSuchHierarchyLevelDefinitionException if a hierarchy level definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HierarchyLevelDefinition remove(
        int hierarchyLevelDefinitionSid)
        throws com.stpl.app.NoSuchHierarchyLevelDefinitionException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(hierarchyLevelDefinitionSid);
    }

    public static com.stpl.app.model.HierarchyLevelDefinition updateImpl(
        com.stpl.app.model.HierarchyLevelDefinition hierarchyLevelDefinition)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(hierarchyLevelDefinition);
    }

    /**
    * Returns the hierarchy level definition with the primary key or throws a {@link com.stpl.app.NoSuchHierarchyLevelDefinitionException} if it could not be found.
    *
    * @param hierarchyLevelDefinitionSid the primary key of the hierarchy level definition
    * @return the hierarchy level definition
    * @throws com.stpl.app.NoSuchHierarchyLevelDefinitionException if a hierarchy level definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HierarchyLevelDefinition findByPrimaryKey(
        int hierarchyLevelDefinitionSid)
        throws com.stpl.app.NoSuchHierarchyLevelDefinitionException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(hierarchyLevelDefinitionSid);
    }

    /**
    * Returns the hierarchy level definition with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param hierarchyLevelDefinitionSid the primary key of the hierarchy level definition
    * @return the hierarchy level definition, or <code>null</code> if a hierarchy level definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HierarchyLevelDefinition fetchByPrimaryKey(
        int hierarchyLevelDefinitionSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(hierarchyLevelDefinitionSid);
    }

    /**
    * Returns all the hierarchy level definitions.
    *
    * @return the hierarchy level definitions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.HierarchyLevelDefinition> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the hierarchy level definitions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HierarchyLevelDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hierarchy level definitions
    * @param end the upper bound of the range of hierarchy level definitions (not inclusive)
    * @return the range of hierarchy level definitions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.HierarchyLevelDefinition> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the hierarchy level definitions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HierarchyLevelDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hierarchy level definitions
    * @param end the upper bound of the range of hierarchy level definitions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of hierarchy level definitions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.HierarchyLevelDefinition> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the hierarchy level definitions from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of hierarchy level definitions.
    *
    * @return the number of hierarchy level definitions
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static HierarchyLevelDefinitionPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (HierarchyLevelDefinitionPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    HierarchyLevelDefinitionPersistence.class.getName());

            ReferenceRegistry.registerReference(HierarchyLevelDefinitionUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(HierarchyLevelDefinitionPersistence persistence) {
    }
}
