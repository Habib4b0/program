package com.stpl.app.service.persistence;

import com.stpl.app.model.FileManagement;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the file management service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see FileManagementPersistenceImpl
 * @see FileManagementUtil
 * @generated
 */
public interface FileManagementPersistence extends BasePersistence<FileManagement> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link FileManagementUtil} to access the file management persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the file management in the entity cache if it is enabled.
    *
    * @param fileManagement the file management
    */
    public void cacheResult(com.stpl.app.model.FileManagement fileManagement);

    /**
    * Caches the file managements in the entity cache if it is enabled.
    *
    * @param fileManagements the file managements
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.FileManagement> fileManagements);

    /**
    * Creates a new file management with the primary key. Does not add the file management to the database.
    *
    * @param fileManagementSid the primary key for the new file management
    * @return the new file management
    */
    public com.stpl.app.model.FileManagement create(int fileManagementSid);

    /**
    * Removes the file management with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param fileManagementSid the primary key of the file management
    * @return the file management that was removed
    * @throws com.stpl.app.NoSuchFileManagementException if a file management with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.FileManagement remove(int fileManagementSid)
        throws com.stpl.app.NoSuchFileManagementException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.FileManagement updateImpl(
        com.stpl.app.model.FileManagement fileManagement)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the file management with the primary key or throws a {@link com.stpl.app.NoSuchFileManagementException} if it could not be found.
    *
    * @param fileManagementSid the primary key of the file management
    * @return the file management
    * @throws com.stpl.app.NoSuchFileManagementException if a file management with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.FileManagement findByPrimaryKey(
        int fileManagementSid)
        throws com.stpl.app.NoSuchFileManagementException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the file management with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param fileManagementSid the primary key of the file management
    * @return the file management, or <code>null</code> if a file management with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.FileManagement fetchByPrimaryKey(
        int fileManagementSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the file managements.
    *
    * @return the file managements
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.FileManagement> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

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
    public java.util.List<com.stpl.app.model.FileManagement> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

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
    public java.util.List<com.stpl.app.model.FileManagement> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the file managements from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of file managements.
    *
    * @return the number of file managements
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
