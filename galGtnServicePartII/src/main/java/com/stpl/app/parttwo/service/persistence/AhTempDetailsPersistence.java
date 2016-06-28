package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.AhTempDetails;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ah temp details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AhTempDetailsPersistenceImpl
 * @see AhTempDetailsUtil
 * @generated
 */
public interface AhTempDetailsPersistence extends BasePersistence<AhTempDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link AhTempDetailsUtil} to access the ah temp details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the ah temp details in the entity cache if it is enabled.
    *
    * @param ahTempDetails the ah temp details
    */
    public void cacheResult(
        com.stpl.app.parttwo.model.AhTempDetails ahTempDetails);

    /**
    * Caches the ah temp detailses in the entity cache if it is enabled.
    *
    * @param ahTempDetailses the ah temp detailses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.AhTempDetails> ahTempDetailses);

    /**
    * Creates a new ah temp details with the primary key. Does not add the ah temp details to the database.
    *
    * @param componentMasterSid the primary key for the new ah temp details
    * @return the new ah temp details
    */
    public com.stpl.app.parttwo.model.AhTempDetails create(
        int componentMasterSid);

    /**
    * Removes the ah temp details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param componentMasterSid the primary key of the ah temp details
    * @return the ah temp details that was removed
    * @throws com.stpl.app.parttwo.NoSuchAhTempDetailsException if a ah temp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.AhTempDetails remove(
        int componentMasterSid)
        throws com.stpl.app.parttwo.NoSuchAhTempDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.parttwo.model.AhTempDetails updateImpl(
        com.stpl.app.parttwo.model.AhTempDetails ahTempDetails)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ah temp details with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchAhTempDetailsException} if it could not be found.
    *
    * @param componentMasterSid the primary key of the ah temp details
    * @return the ah temp details
    * @throws com.stpl.app.parttwo.NoSuchAhTempDetailsException if a ah temp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.AhTempDetails findByPrimaryKey(
        int componentMasterSid)
        throws com.stpl.app.parttwo.NoSuchAhTempDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ah temp details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param componentMasterSid the primary key of the ah temp details
    * @return the ah temp details, or <code>null</code> if a ah temp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.AhTempDetails fetchByPrimaryKey(
        int componentMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ah temp detailses.
    *
    * @return the ah temp detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.AhTempDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ah temp detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AhTempDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ah temp detailses
    * @param end the upper bound of the range of ah temp detailses (not inclusive)
    * @return the range of ah temp detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.AhTempDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ah temp detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AhTempDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ah temp detailses
    * @param end the upper bound of the range of ah temp detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ah temp detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.AhTempDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ah temp detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ah temp detailses.
    *
    * @return the number of ah temp detailses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
