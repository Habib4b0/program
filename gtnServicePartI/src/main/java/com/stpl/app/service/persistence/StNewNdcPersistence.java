package com.stpl.app.service.persistence;

import com.stpl.app.model.StNewNdc;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the st new ndc service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StNewNdcPersistenceImpl
 * @see StNewNdcUtil
 * @generated
 */
public interface StNewNdcPersistence extends BasePersistence<StNewNdc> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link StNewNdcUtil} to access the st new ndc persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the st new ndc in the entity cache if it is enabled.
    *
    * @param stNewNdc the st new ndc
    */
    public void cacheResult(com.stpl.app.model.StNewNdc stNewNdc);

    /**
    * Caches the st new ndcs in the entity cache if it is enabled.
    *
    * @param stNewNdcs the st new ndcs
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.StNewNdc> stNewNdcs);

    /**
    * Creates a new st new ndc with the primary key. Does not add the st new ndc to the database.
    *
    * @param stNewNdcPK the primary key for the new st new ndc
    * @return the new st new ndc
    */
    public com.stpl.app.model.StNewNdc create(StNewNdcPK stNewNdcPK);

    /**
    * Removes the st new ndc with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stNewNdcPK the primary key of the st new ndc
    * @return the st new ndc that was removed
    * @throws com.stpl.app.NoSuchStNewNdcException if a st new ndc with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StNewNdc remove(StNewNdcPK stNewNdcPK)
        throws com.stpl.app.NoSuchStNewNdcException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.StNewNdc updateImpl(
        com.stpl.app.model.StNewNdc stNewNdc)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the st new ndc with the primary key or throws a {@link com.stpl.app.NoSuchStNewNdcException} if it could not be found.
    *
    * @param stNewNdcPK the primary key of the st new ndc
    * @return the st new ndc
    * @throws com.stpl.app.NoSuchStNewNdcException if a st new ndc with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StNewNdc findByPrimaryKey(StNewNdcPK stNewNdcPK)
        throws com.stpl.app.NoSuchStNewNdcException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the st new ndc with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param stNewNdcPK the primary key of the st new ndc
    * @return the st new ndc, or <code>null</code> if a st new ndc with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StNewNdc fetchByPrimaryKey(StNewNdcPK stNewNdcPK)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the st new ndcs.
    *
    * @return the st new ndcs
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StNewNdc> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the st new ndcs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st new ndcs
    * @param end the upper bound of the range of st new ndcs (not inclusive)
    * @return the range of st new ndcs
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StNewNdc> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the st new ndcs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st new ndcs
    * @param end the upper bound of the range of st new ndcs (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of st new ndcs
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StNewNdc> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the st new ndcs from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of st new ndcs.
    *
    * @return the number of st new ndcs
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
