package com.stpl.app.service.persistence;

import com.stpl.app.model.RelationshipBuilder;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the relationship builder service. This utility wraps {@link RelationshipBuilderPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RelationshipBuilderPersistence
 * @see RelationshipBuilderPersistenceImpl
 * @generated
 */
public class RelationshipBuilderUtil {
    private static RelationshipBuilderPersistence _persistence;

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
    public static void clearCache(RelationshipBuilder relationshipBuilder) {
        getPersistence().clearCache(relationshipBuilder);
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
    public static List<RelationshipBuilder> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<RelationshipBuilder> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<RelationshipBuilder> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static RelationshipBuilder update(
        RelationshipBuilder relationshipBuilder) throws SystemException {
        return getPersistence().update(relationshipBuilder);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static RelationshipBuilder update(
        RelationshipBuilder relationshipBuilder, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(relationshipBuilder, serviceContext);
    }

    /**
    * Caches the relationship builder in the entity cache if it is enabled.
    *
    * @param relationshipBuilder the relationship builder
    */
    public static void cacheResult(
        com.stpl.app.model.RelationshipBuilder relationshipBuilder) {
        getPersistence().cacheResult(relationshipBuilder);
    }

    /**
    * Caches the relationship builders in the entity cache if it is enabled.
    *
    * @param relationshipBuilders the relationship builders
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.RelationshipBuilder> relationshipBuilders) {
        getPersistence().cacheResult(relationshipBuilders);
    }

    /**
    * Creates a new relationship builder with the primary key. Does not add the relationship builder to the database.
    *
    * @param relationshipBuilderSid the primary key for the new relationship builder
    * @return the new relationship builder
    */
    public static com.stpl.app.model.RelationshipBuilder create(
        int relationshipBuilderSid) {
        return getPersistence().create(relationshipBuilderSid);
    }

    /**
    * Removes the relationship builder with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param relationshipBuilderSid the primary key of the relationship builder
    * @return the relationship builder that was removed
    * @throws com.stpl.app.NoSuchRelationshipBuilderException if a relationship builder with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RelationshipBuilder remove(
        int relationshipBuilderSid)
        throws com.stpl.app.NoSuchRelationshipBuilderException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(relationshipBuilderSid);
    }

    public static com.stpl.app.model.RelationshipBuilder updateImpl(
        com.stpl.app.model.RelationshipBuilder relationshipBuilder)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(relationshipBuilder);
    }

    /**
    * Returns the relationship builder with the primary key or throws a {@link com.stpl.app.NoSuchRelationshipBuilderException} if it could not be found.
    *
    * @param relationshipBuilderSid the primary key of the relationship builder
    * @return the relationship builder
    * @throws com.stpl.app.NoSuchRelationshipBuilderException if a relationship builder with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RelationshipBuilder findByPrimaryKey(
        int relationshipBuilderSid)
        throws com.stpl.app.NoSuchRelationshipBuilderException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(relationshipBuilderSid);
    }

    /**
    * Returns the relationship builder with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param relationshipBuilderSid the primary key of the relationship builder
    * @return the relationship builder, or <code>null</code> if a relationship builder with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RelationshipBuilder fetchByPrimaryKey(
        int relationshipBuilderSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(relationshipBuilderSid);
    }

    /**
    * Returns all the relationship builders.
    *
    * @return the relationship builders
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RelationshipBuilder> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the relationship builders.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RelationshipBuilderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of relationship builders
    * @param end the upper bound of the range of relationship builders (not inclusive)
    * @return the range of relationship builders
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RelationshipBuilder> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the relationship builders.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RelationshipBuilderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of relationship builders
    * @param end the upper bound of the range of relationship builders (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of relationship builders
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RelationshipBuilder> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the relationship builders from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of relationship builders.
    *
    * @return the number of relationship builders
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static RelationshipBuilderPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (RelationshipBuilderPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    RelationshipBuilderPersistence.class.getName());

            ReferenceRegistry.registerReference(RelationshipBuilderUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(RelationshipBuilderPersistence persistence) {
    }
}
