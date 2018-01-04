/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.dao;


import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.model.AdditionalNotes;
import com.stpl.app.model.DocDetails;
import java.util.List;

/**
 *
 * @author Vinodhini
 */
public interface NAAdditionalInfoDAO {
   /**
     * Gets the attachment dto list.
     *
     * @param dynamicQuery the dynamic query
     * @return the attachment dto list
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    List getAttachmentDTOList(DynamicQuery dynamicQuery) throws SystemException;

    /**
     * Adds the doc details.
     *
     * @param docDetails the doc details
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    void addDocDetails(DocDetails docDetails) throws SystemException;

    /**
     * Update doc details.
     *
     * @param docDetails the doc details
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    void updateDocDetails(DocDetails docDetails) throws SystemException;

    /**
     * Delete doc details.
     *
     * @param docDetailsId the doc details id
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    void deleteDocDetails(int docDetailsId) throws SystemException, PortalException;

    /**
     * Save notes.
     *
     * @param additionalNotesList the additional notes list
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    void saveNotes(AdditionalNotes additionalNotesList) throws SystemException;

    /**
     * Gets the notes.
     *
     * @param dynamicQuery the dynamic query
     * @return the notes
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    List getNotes(DynamicQuery dynamicQuery) throws SystemException;
}
