package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldDemandActual;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ivld demand actual service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldDemandActualPersistenceImpl
 * @see IvldDemandActualUtil
 * @generated
 */
public interface IvldDemandActualPersistence extends BasePersistence<IvldDemandActual> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link IvldDemandActualUtil} to access the ivld demand actual persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the ivld demand actual in the entity cache if it is enabled.
    *
    * @param ivldDemandActual the ivld demand actual
    */
    public void cacheResult(
        com.stpl.app.model.IvldDemandActual ivldDemandActual);

    /**
    * Caches the ivld demand actuals in the entity cache if it is enabled.
    *
    * @param ivldDemandActuals the ivld demand actuals
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.IvldDemandActual> ivldDemandActuals);

    /**
    * Creates a new ivld demand actual with the primary key. Does not add the ivld demand actual to the database.
    *
    * @param ivldDemandActualSid the primary key for the new ivld demand actual
    * @return the new ivld demand actual
    */
    public com.stpl.app.model.IvldDemandActual create(int ivldDemandActualSid);

    /**
    * Removes the ivld demand actual with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldDemandActualSid the primary key of the ivld demand actual
    * @return the ivld demand actual that was removed
    * @throws com.stpl.app.NoSuchIvldDemandActualException if a ivld demand actual with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IvldDemandActual remove(int ivldDemandActualSid)
        throws com.stpl.app.NoSuchIvldDemandActualException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.IvldDemandActual updateImpl(
        com.stpl.app.model.IvldDemandActual ivldDemandActual)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ivld demand actual with the primary key or throws a {@link com.stpl.app.NoSuchIvldDemandActualException} if it could not be found.
    *
    * @param ivldDemandActualSid the primary key of the ivld demand actual
    * @return the ivld demand actual
    * @throws com.stpl.app.NoSuchIvldDemandActualException if a ivld demand actual with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IvldDemandActual findByPrimaryKey(
        int ivldDemandActualSid)
        throws com.stpl.app.NoSuchIvldDemandActualException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ivld demand actual with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldDemandActualSid the primary key of the ivld demand actual
    * @return the ivld demand actual, or <code>null</code> if a ivld demand actual with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IvldDemandActual fetchByPrimaryKey(
        int ivldDemandActualSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ivld demand actuals.
    *
    * @return the ivld demand actuals
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IvldDemandActual> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ivld demand actuals.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldDemandActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld demand actuals
    * @param end the upper bound of the range of ivld demand actuals (not inclusive)
    * @return the range of ivld demand actuals
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IvldDemandActual> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ivld demand actuals.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldDemandActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld demand actuals
    * @param end the upper bound of the range of ivld demand actuals (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ivld demand actuals
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IvldDemandActual> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ivld demand actuals from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ivld demand actuals.
    *
    * @return the number of ivld demand actuals
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
