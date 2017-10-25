package com.stpl.app.service.persistence;

import com.stpl.app.model.MProjectionSelection;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the m projection selection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MProjectionSelectionPersistenceImpl
 * @see MProjectionSelectionUtil
 * @generated
 */
public interface MProjectionSelectionPersistence extends BasePersistence<MProjectionSelection> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link MProjectionSelectionUtil} to access the m projection selection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the m projection selection in the entity cache if it is enabled.
    *
    * @param mProjectionSelection the m projection selection
    */
    public void cacheResult(
        com.stpl.app.model.MProjectionSelection mProjectionSelection);

    /**
    * Caches the m projection selections in the entity cache if it is enabled.
    *
    * @param mProjectionSelections the m projection selections
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.MProjectionSelection> mProjectionSelections);

    /**
    * Creates a new m projection selection with the primary key. Does not add the m projection selection to the database.
    *
    * @param mProjectionSelectionSid the primary key for the new m projection selection
    * @return the new m projection selection
    */
    public com.stpl.app.model.MProjectionSelection create(
        int mProjectionSelectionSid);

    /**
    * Removes the m projection selection with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param mProjectionSelectionSid the primary key of the m projection selection
    * @return the m projection selection that was removed
    * @throws com.stpl.app.NoSuchMProjectionSelectionException if a m projection selection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.MProjectionSelection remove(
        int mProjectionSelectionSid)
        throws com.stpl.app.NoSuchMProjectionSelectionException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.MProjectionSelection updateImpl(
        com.stpl.app.model.MProjectionSelection mProjectionSelection)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the m projection selection with the primary key or throws a {@link com.stpl.app.NoSuchMProjectionSelectionException} if it could not be found.
    *
    * @param mProjectionSelectionSid the primary key of the m projection selection
    * @return the m projection selection
    * @throws com.stpl.app.NoSuchMProjectionSelectionException if a m projection selection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.MProjectionSelection findByPrimaryKey(
        int mProjectionSelectionSid)
        throws com.stpl.app.NoSuchMProjectionSelectionException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the m projection selection with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param mProjectionSelectionSid the primary key of the m projection selection
    * @return the m projection selection, or <code>null</code> if a m projection selection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.MProjectionSelection fetchByPrimaryKey(
        int mProjectionSelectionSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the m projection selections.
    *
    * @return the m projection selections
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.MProjectionSelection> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the m projection selections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of m projection selections
    * @param end the upper bound of the range of m projection selections (not inclusive)
    * @return the range of m projection selections
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.MProjectionSelection> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the m projection selections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of m projection selections
    * @param end the upper bound of the range of m projection selections (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of m projection selections
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.MProjectionSelection> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the m projection selections from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of m projection selections.
    *
    * @return the number of m projection selections
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
