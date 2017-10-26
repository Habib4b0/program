package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldMasterDataAttribute;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ivld master data attribute service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldMasterDataAttributePersistenceImpl
 * @see IvldMasterDataAttributeUtil
 * @generated
 */
public interface IvldMasterDataAttributePersistence extends BasePersistence<IvldMasterDataAttribute> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link IvldMasterDataAttributeUtil} to access the ivld master data attribute persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the ivld master data attribute in the entity cache if it is enabled.
    *
    * @param ivldMasterDataAttribute the ivld master data attribute
    */
    public void cacheResult(
        com.stpl.app.model.IvldMasterDataAttribute ivldMasterDataAttribute);

    /**
    * Caches the ivld master data attributes in the entity cache if it is enabled.
    *
    * @param ivldMasterDataAttributes the ivld master data attributes
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.IvldMasterDataAttribute> ivldMasterDataAttributes);

    /**
    * Creates a new ivld master data attribute with the primary key. Does not add the ivld master data attribute to the database.
    *
    * @param ivldMasterDataAtbteSid the primary key for the new ivld master data attribute
    * @return the new ivld master data attribute
    */
    public com.stpl.app.model.IvldMasterDataAttribute create(
        int ivldMasterDataAtbteSid);

    /**
    * Removes the ivld master data attribute with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldMasterDataAtbteSid the primary key of the ivld master data attribute
    * @return the ivld master data attribute that was removed
    * @throws com.stpl.app.NoSuchIvldMasterDataAttributeException if a ivld master data attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IvldMasterDataAttribute remove(
        int ivldMasterDataAtbteSid)
        throws com.stpl.app.NoSuchIvldMasterDataAttributeException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.IvldMasterDataAttribute updateImpl(
        com.stpl.app.model.IvldMasterDataAttribute ivldMasterDataAttribute)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ivld master data attribute with the primary key or throws a {@link com.stpl.app.NoSuchIvldMasterDataAttributeException} if it could not be found.
    *
    * @param ivldMasterDataAtbteSid the primary key of the ivld master data attribute
    * @return the ivld master data attribute
    * @throws com.stpl.app.NoSuchIvldMasterDataAttributeException if a ivld master data attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IvldMasterDataAttribute findByPrimaryKey(
        int ivldMasterDataAtbteSid)
        throws com.stpl.app.NoSuchIvldMasterDataAttributeException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ivld master data attribute with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldMasterDataAtbteSid the primary key of the ivld master data attribute
    * @return the ivld master data attribute, or <code>null</code> if a ivld master data attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IvldMasterDataAttribute fetchByPrimaryKey(
        int ivldMasterDataAtbteSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ivld master data attributes.
    *
    * @return the ivld master data attributes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IvldMasterDataAttribute> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ivld master data attributes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldMasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld master data attributes
    * @param end the upper bound of the range of ivld master data attributes (not inclusive)
    * @return the range of ivld master data attributes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IvldMasterDataAttribute> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ivld master data attributes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldMasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld master data attributes
    * @param end the upper bound of the range of ivld master data attributes (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ivld master data attributes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IvldMasterDataAttribute> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ivld master data attributes from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ivld master data attributes.
    *
    * @return the number of ivld master data attributes
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
