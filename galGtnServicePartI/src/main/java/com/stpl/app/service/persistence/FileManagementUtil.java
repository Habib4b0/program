package com.stpl.app.service.persistence;

import com.stpl.app.model.FileManagement;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the file management service. This utility wraps {@link FileManagementPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see FileManagementPersistence
 * @see FileManagementPersistenceImpl
 * @generated
 */
public class FileManagementUtil {
    private static FileManagementPersistence _persistence;

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
    public static void clearCache(FileManagement fileManagement) {
        getPersistence().clearCache(fileManagement);
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
    public static List<FileManagement> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<FileManagement> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<FileManagement> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static FileManagement update(FileManagement fileManagement)
        throws SystemException {
        return getPersistence().update(fileManagement);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static FileManagement update(FileManagement fileManagement,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(fileManagement, serviceContext);
    }

    /**
    * Caches the file management in the entity cache if it is enabled.
    *
    * @param fileManagement the file management
    */
    public static void cacheResult(
        com.stpl.app.model.FileManagement fileManagement) {
        getPersistence().cacheResult(fileManagement);
    }

    /**
    * Caches the file managements in the entity cache if it is enabled.
    *
    * @param fileManagements the file managements
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.FileManagement> fileManagements) {
        getPersistence().cacheResult(fileManagements);
    }

    /**
    * Creates a new file management with the primary key. Does not add the file management to the database.
    *
    * @param fileManagementSid the primary key for the new file management
    * @return the new file management
    */
    public static com.stpl.app.model.FileManagement create(
        int fileManagementSid) {
        return getPersistence().create(fileManagementSid);
    }

    /**
    * Removes the file management with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param fileManagementSid the primary key of the file management
    * @return the file management that was removed
    * @throws com.stpl.app.NoSuchFileManagementException if a file management with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.FileManagement remove(
        int fileManagementSid)
        throws com.stpl.app.NoSuchFileManagementException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(fileManagementSid);
    }

    public static com.stpl.app.model.FileManagement updateImpl(
        com.stpl.app.model.FileManagement fileManagement)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(fileManagement);
    }

    /**
    * Returns the file management with the primary key or throws a {@link com.stpl.app.NoSuchFileManagementException} if it could not be found.
    *
    * @param fileManagementSid the primary key of the file management
    * @return the file management
    * @throws com.stpl.app.NoSuchFileManagementException if a file management with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.FileManagement findByPrimaryKey(
        int fileManagementSid)
        throws com.stpl.app.NoSuchFileManagementException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(fileManagementSid);
    }

    /**
    * Returns the file management with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param fileManagementSid the primary key of the file management
    * @return the file management, or <code>null</code> if a file management with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.FileManagement fetchByPrimaryKey(
        int fileManagementSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(fileManagementSid);
    }

    /**
    * Returns all the file managements.
    *
    * @return the file managements
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.FileManagement> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the file managements.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FileManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of file managements
    * @param end the upper bound of the range of file managements (not inclusive)
    * @return the range of file managements
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.FileManagement> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the file managements.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FileManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of file managements
    * @param end the upper bound of the range of file managements (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of file managements
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.FileManagement> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the file managements from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of file managements.
    *
    * @return the number of file managements
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static FileManagementPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (FileManagementPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    FileManagementPersistence.class.getName());

            ReferenceRegistry.registerReference(FileManagementUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(FileManagementPersistence persistence) {
    }
}
