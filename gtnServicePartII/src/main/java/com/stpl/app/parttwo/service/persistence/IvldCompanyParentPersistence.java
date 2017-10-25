package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.IvldCompanyParent;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ivld company parent service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldCompanyParentPersistenceImpl
 * @see IvldCompanyParentUtil
 * @generated
 */
public interface IvldCompanyParentPersistence extends BasePersistence<IvldCompanyParent> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link IvldCompanyParentUtil} to access the ivld company parent persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the ivld company parent in the entity cache if it is enabled.
    *
    * @param ivldCompanyParent the ivld company parent
    */
    public void cacheResult(
        com.stpl.app.parttwo.model.IvldCompanyParent ivldCompanyParent);

    /**
    * Caches the ivld company parents in the entity cache if it is enabled.
    *
    * @param ivldCompanyParents the ivld company parents
    */
    public void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.IvldCompanyParent> ivldCompanyParents);

    /**
    * Creates a new ivld company parent with the primary key. Does not add the ivld company parent to the database.
    *
    * @param ivldCompanyParentSid the primary key for the new ivld company parent
    * @return the new ivld company parent
    */
    public com.stpl.app.parttwo.model.IvldCompanyParent create(
        int ivldCompanyParentSid);

    /**
    * Removes the ivld company parent with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldCompanyParentSid the primary key of the ivld company parent
    * @return the ivld company parent that was removed
    * @throws com.stpl.app.parttwo.NoSuchIvldCompanyParentException if a ivld company parent with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.IvldCompanyParent remove(
        int ivldCompanyParentSid)
        throws com.stpl.app.parttwo.NoSuchIvldCompanyParentException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.parttwo.model.IvldCompanyParent updateImpl(
        com.stpl.app.parttwo.model.IvldCompanyParent ivldCompanyParent)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ivld company parent with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchIvldCompanyParentException} if it could not be found.
    *
    * @param ivldCompanyParentSid the primary key of the ivld company parent
    * @return the ivld company parent
    * @throws com.stpl.app.parttwo.NoSuchIvldCompanyParentException if a ivld company parent with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.IvldCompanyParent findByPrimaryKey(
        int ivldCompanyParentSid)
        throws com.stpl.app.parttwo.NoSuchIvldCompanyParentException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ivld company parent with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldCompanyParentSid the primary key of the ivld company parent
    * @return the ivld company parent, or <code>null</code> if a ivld company parent with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.IvldCompanyParent fetchByPrimaryKey(
        int ivldCompanyParentSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ivld company parents.
    *
    * @return the ivld company parents
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.IvldCompanyParent> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ivld company parents.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyParentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld company parents
    * @param end the upper bound of the range of ivld company parents (not inclusive)
    * @return the range of ivld company parents
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.IvldCompanyParent> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ivld company parents.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyParentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld company parents
    * @param end the upper bound of the range of ivld company parents (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ivld company parents
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.IvldCompanyParent> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ivld company parents from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ivld company parents.
    *
    * @return the number of ivld company parents
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
