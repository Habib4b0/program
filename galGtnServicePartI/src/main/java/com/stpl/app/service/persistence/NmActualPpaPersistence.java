package com.stpl.app.service.persistence;

import com.stpl.app.model.NmActualPpa;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the nm actual ppa service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NmActualPpaPersistenceImpl
 * @see NmActualPpaUtil
 * @generated
 */
public interface NmActualPpaPersistence extends BasePersistence<NmActualPpa> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link NmActualPpaUtil} to access the nm actual ppa persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the nm actual ppa in the entity cache if it is enabled.
    *
    * @param nmActualPpa the nm actual ppa
    */
    public void cacheResult(com.stpl.app.model.NmActualPpa nmActualPpa);

    /**
    * Caches the nm actual ppas in the entity cache if it is enabled.
    *
    * @param nmActualPpas the nm actual ppas
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.NmActualPpa> nmActualPpas);

    /**
    * Creates a new nm actual ppa with the primary key. Does not add the nm actual ppa to the database.
    *
    * @param nmActualPpaPK the primary key for the new nm actual ppa
    * @return the new nm actual ppa
    */
    public com.stpl.app.model.NmActualPpa create(NmActualPpaPK nmActualPpaPK);

    /**
    * Removes the nm actual ppa with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param nmActualPpaPK the primary key of the nm actual ppa
    * @return the nm actual ppa that was removed
    * @throws com.stpl.app.NoSuchNmActualPpaException if a nm actual ppa with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.NmActualPpa remove(NmActualPpaPK nmActualPpaPK)
        throws com.stpl.app.NoSuchNmActualPpaException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.NmActualPpa updateImpl(
        com.stpl.app.model.NmActualPpa nmActualPpa)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the nm actual ppa with the primary key or throws a {@link com.stpl.app.NoSuchNmActualPpaException} if it could not be found.
    *
    * @param nmActualPpaPK the primary key of the nm actual ppa
    * @return the nm actual ppa
    * @throws com.stpl.app.NoSuchNmActualPpaException if a nm actual ppa with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.NmActualPpa findByPrimaryKey(
        NmActualPpaPK nmActualPpaPK)
        throws com.stpl.app.NoSuchNmActualPpaException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the nm actual ppa with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param nmActualPpaPK the primary key of the nm actual ppa
    * @return the nm actual ppa, or <code>null</code> if a nm actual ppa with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.NmActualPpa fetchByPrimaryKey(
        NmActualPpaPK nmActualPpaPK)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the nm actual ppas.
    *
    * @return the nm actual ppas
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.NmActualPpa> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the nm actual ppas.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmActualPpaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of nm actual ppas
    * @param end the upper bound of the range of nm actual ppas (not inclusive)
    * @return the range of nm actual ppas
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.NmActualPpa> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the nm actual ppas.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmActualPpaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of nm actual ppas
    * @param end the upper bound of the range of nm actual ppas (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of nm actual ppas
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.NmActualPpa> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the nm actual ppas from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of nm actual ppas.
    *
    * @return the number of nm actual ppas
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
