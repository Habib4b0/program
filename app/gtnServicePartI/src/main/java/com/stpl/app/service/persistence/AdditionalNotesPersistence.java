package com.stpl.app.service.persistence;

import com.stpl.app.model.AdditionalNotes;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the additional notes service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AdditionalNotesPersistenceImpl
 * @see AdditionalNotesUtil
 * @generated
 */
public interface AdditionalNotesPersistence extends BasePersistence<AdditionalNotes> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link AdditionalNotesUtil} to access the additional notes persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the additional notes in the entity cache if it is enabled.
    *
    * @param additionalNotes the additional notes
    */
    public void cacheResult(com.stpl.app.model.AdditionalNotes additionalNotes);

    /**
    * Caches the additional noteses in the entity cache if it is enabled.
    *
    * @param additionalNoteses the additional noteses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.AdditionalNotes> additionalNoteses);

    /**
    * Creates a new additional notes with the primary key. Does not add the additional notes to the database.
    *
    * @param additionalNotesId the primary key for the new additional notes
    * @return the new additional notes
    */
    public com.stpl.app.model.AdditionalNotes create(int additionalNotesId);

    /**
    * Removes the additional notes with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param additionalNotesId the primary key of the additional notes
    * @return the additional notes that was removed
    * @throws com.stpl.app.NoSuchAdditionalNotesException if a additional notes with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.AdditionalNotes remove(int additionalNotesId)
        throws com.stpl.app.NoSuchAdditionalNotesException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.AdditionalNotes updateImpl(
        com.stpl.app.model.AdditionalNotes additionalNotes)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the additional notes with the primary key or throws a {@link com.stpl.app.NoSuchAdditionalNotesException} if it could not be found.
    *
    * @param additionalNotesId the primary key of the additional notes
    * @return the additional notes
    * @throws com.stpl.app.NoSuchAdditionalNotesException if a additional notes with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.AdditionalNotes findByPrimaryKey(
        int additionalNotesId)
        throws com.stpl.app.NoSuchAdditionalNotesException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the additional notes with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param additionalNotesId the primary key of the additional notes
    * @return the additional notes, or <code>null</code> if a additional notes with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.AdditionalNotes fetchByPrimaryKey(
        int additionalNotesId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the additional noteses.
    *
    * @return the additional noteses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.AdditionalNotes> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the additional noteses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AdditionalNotesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of additional noteses
    * @param end the upper bound of the range of additional noteses (not inclusive)
    * @return the range of additional noteses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.AdditionalNotes> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the additional noteses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AdditionalNotesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of additional noteses
    * @param end the upper bound of the range of additional noteses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of additional noteses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.AdditionalNotes> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the additional noteses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of additional noteses.
    *
    * @return the number of additional noteses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
