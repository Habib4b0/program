package com.stpl.app.service.persistence;

import com.stpl.app.model.RelationshipLevelDefinition;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the relationship level definition service. This utility wraps {@link RelationshipLevelDefinitionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RelationshipLevelDefinitionPersistence
 * @see RelationshipLevelDefinitionPersistenceImpl
 * @generated
 */
public class RelationshipLevelDefinitionUtil {
    private static RelationshipLevelDefinitionPersistence _persistence;

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
        RelationshipLevelDefinition relationshipLevelDefinition) {
        getPersistence().clearCache(relationshipLevelDefinition);
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
    public static List<RelationshipLevelDefinition> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<RelationshipLevelDefinition> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<RelationshipLevelDefinition> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static RelationshipLevelDefinition update(
        RelationshipLevelDefinition relationshipLevelDefinition)
        throws SystemException {
        return getPersistence().update(relationshipLevelDefinition);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static RelationshipLevelDefinition update(
        RelationshipLevelDefinition relationshipLevelDefinition,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence()
                   .update(relationshipLevelDefinition, serviceContext);
    }

    /**
    * Caches the relationship level definition in the entity cache if it is enabled.
    *
    * @param relationshipLevelDefinition the relationship level definition
    */
    public static void cacheResult(
        com.stpl.app.model.RelationshipLevelDefinition relationshipLevelDefinition) {
        getPersistence().cacheResult(relationshipLevelDefinition);
    }

    /**
    * Caches the relationship level definitions in the entity cache if it is enabled.
    *
    * @param relationshipLevelDefinitions the relationship level definitions
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.RelationshipLevelDefinition> relationshipLevelDefinitions) {
        getPersistence().cacheResult(relationshipLevelDefinitions);
    }

    /**
    * Creates a new relationship level definition with the primary key. Does not add the relationship level definition to the database.
    *
    * @param relationshipLevelSid the primary key for the new relationship level definition
    * @return the new relationship level definition
    */
    public static com.stpl.app.model.RelationshipLevelDefinition create(
        int relationshipLevelSid) {
        return getPersistence().create(relationshipLevelSid);
    }

    /**
    * Removes the relationship level definition with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param relationshipLevelSid the primary key of the relationship level definition
    * @return the relationship level definition that was removed
    * @throws com.stpl.app.NoSuchRelationshipLevelDefinitionException if a relationship level definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RelationshipLevelDefinition remove(
        int relationshipLevelSid)
        throws com.stpl.app.NoSuchRelationshipLevelDefinitionException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(relationshipLevelSid);
    }

    public static com.stpl.app.model.RelationshipLevelDefinition updateImpl(
        com.stpl.app.model.RelationshipLevelDefinition relationshipLevelDefinition)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(relationshipLevelDefinition);
    }

    /**
    * Returns the relationship level definition with the primary key or throws a {@link com.stpl.app.NoSuchRelationshipLevelDefinitionException} if it could not be found.
    *
    * @param relationshipLevelSid the primary key of the relationship level definition
    * @return the relationship level definition
    * @throws com.stpl.app.NoSuchRelationshipLevelDefinitionException if a relationship level definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RelationshipLevelDefinition findByPrimaryKey(
        int relationshipLevelSid)
        throws com.stpl.app.NoSuchRelationshipLevelDefinitionException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(relationshipLevelSid);
    }

    /**
    * Returns the relationship level definition with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param relationshipLevelSid the primary key of the relationship level definition
    * @return the relationship level definition, or <code>null</code> if a relationship level definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RelationshipLevelDefinition fetchByPrimaryKey(
        int relationshipLevelSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(relationshipLevelSid);
    }

    /**
    * Returns all the relationship level definitions.
    *
    * @return the relationship level definitions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RelationshipLevelDefinition> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the relationship level definitions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RelationshipLevelDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of relationship level definitions
    * @param end the upper bound of the range of relationship level definitions (not inclusive)
    * @return the range of relationship level definitions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RelationshipLevelDefinition> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the relationship level definitions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RelationshipLevelDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of relationship level definitions
    * @param end the upper bound of the range of relationship level definitions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of relationship level definitions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RelationshipLevelDefinition> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the relationship level definitions from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of relationship level definitions.
    *
    * @return the number of relationship level definitions
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static RelationshipLevelDefinitionPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (RelationshipLevelDefinitionPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    RelationshipLevelDefinitionPersistence.class.getName());

            ReferenceRegistry.registerReference(RelationshipLevelDefinitionUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(
        RelationshipLevelDefinitionPersistence persistence) {
    }
}
