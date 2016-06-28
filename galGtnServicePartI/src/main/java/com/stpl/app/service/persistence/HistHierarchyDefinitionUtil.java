package com.stpl.app.service.persistence;

import com.stpl.app.model.HistHierarchyDefinition;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the hist hierarchy definition service. This utility wraps {@link HistHierarchyDefinitionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HistHierarchyDefinitionPersistence
 * @see HistHierarchyDefinitionPersistenceImpl
 * @generated
 */
public class HistHierarchyDefinitionUtil {
    private static HistHierarchyDefinitionPersistence _persistence;

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
        HistHierarchyDefinition histHierarchyDefinition) {
        getPersistence().clearCache(histHierarchyDefinition);
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
    public static List<HistHierarchyDefinition> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<HistHierarchyDefinition> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<HistHierarchyDefinition> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static HistHierarchyDefinition update(
        HistHierarchyDefinition histHierarchyDefinition)
        throws SystemException {
        return getPersistence().update(histHierarchyDefinition);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static HistHierarchyDefinition update(
        HistHierarchyDefinition histHierarchyDefinition,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(histHierarchyDefinition, serviceContext);
    }

    /**
    * Caches the hist hierarchy definition in the entity cache if it is enabled.
    *
    * @param histHierarchyDefinition the hist hierarchy definition
    */
    public static void cacheResult(
        com.stpl.app.model.HistHierarchyDefinition histHierarchyDefinition) {
        getPersistence().cacheResult(histHierarchyDefinition);
    }

    /**
    * Caches the hist hierarchy definitions in the entity cache if it is enabled.
    *
    * @param histHierarchyDefinitions the hist hierarchy definitions
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.HistHierarchyDefinition> histHierarchyDefinitions) {
        getPersistence().cacheResult(histHierarchyDefinitions);
    }

    /**
    * Creates a new hist hierarchy definition with the primary key. Does not add the hist hierarchy definition to the database.
    *
    * @param histHierarchyDefinitionPK the primary key for the new hist hierarchy definition
    * @return the new hist hierarchy definition
    */
    public static com.stpl.app.model.HistHierarchyDefinition create(
        HistHierarchyDefinitionPK histHierarchyDefinitionPK) {
        return getPersistence().create(histHierarchyDefinitionPK);
    }

    /**
    * Removes the hist hierarchy definition with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param histHierarchyDefinitionPK the primary key of the hist hierarchy definition
    * @return the hist hierarchy definition that was removed
    * @throws com.stpl.app.NoSuchHistHierarchyDefinitionException if a hist hierarchy definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HistHierarchyDefinition remove(
        HistHierarchyDefinitionPK histHierarchyDefinitionPK)
        throws com.stpl.app.NoSuchHistHierarchyDefinitionException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(histHierarchyDefinitionPK);
    }

    public static com.stpl.app.model.HistHierarchyDefinition updateImpl(
        com.stpl.app.model.HistHierarchyDefinition histHierarchyDefinition)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(histHierarchyDefinition);
    }

    /**
    * Returns the hist hierarchy definition with the primary key or throws a {@link com.stpl.app.NoSuchHistHierarchyDefinitionException} if it could not be found.
    *
    * @param histHierarchyDefinitionPK the primary key of the hist hierarchy definition
    * @return the hist hierarchy definition
    * @throws com.stpl.app.NoSuchHistHierarchyDefinitionException if a hist hierarchy definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HistHierarchyDefinition findByPrimaryKey(
        HistHierarchyDefinitionPK histHierarchyDefinitionPK)
        throws com.stpl.app.NoSuchHistHierarchyDefinitionException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(histHierarchyDefinitionPK);
    }

    /**
    * Returns the hist hierarchy definition with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param histHierarchyDefinitionPK the primary key of the hist hierarchy definition
    * @return the hist hierarchy definition, or <code>null</code> if a hist hierarchy definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.HistHierarchyDefinition fetchByPrimaryKey(
        HistHierarchyDefinitionPK histHierarchyDefinitionPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(histHierarchyDefinitionPK);
    }

    /**
    * Returns all the hist hierarchy definitions.
    *
    * @return the hist hierarchy definitions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.HistHierarchyDefinition> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the hist hierarchy definitions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistHierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hist hierarchy definitions
    * @param end the upper bound of the range of hist hierarchy definitions (not inclusive)
    * @return the range of hist hierarchy definitions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.HistHierarchyDefinition> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the hist hierarchy definitions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistHierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hist hierarchy definitions
    * @param end the upper bound of the range of hist hierarchy definitions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of hist hierarchy definitions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.HistHierarchyDefinition> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the hist hierarchy definitions from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of hist hierarchy definitions.
    *
    * @return the number of hist hierarchy definitions
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static HistHierarchyDefinitionPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (HistHierarchyDefinitionPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    HistHierarchyDefinitionPersistence.class.getName());

            ReferenceRegistry.registerReference(HistHierarchyDefinitionUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(HistHierarchyDefinitionPersistence persistence) {
    }
}
