/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.dao;

import com.stpl.app.parttwo.model.CffAdditionalInfo;
import com.stpl.app.parttwo.model.CffDocDetails;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import java.util.List;

/**
 *
 * @author Manasa
 */
public interface AdditionalInfoDAO {
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
     * Gets the notes.
     *
     * @param dynamicQuery the dynamic query
     * @return the notes
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    List getNotes(DynamicQuery dynamicQuery) throws SystemException;
    /**
     * Save notes.
     *
     * @param additionalNotesList the additional notes list
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    void saveNotes(CffAdditionalInfo additionalNotesList) throws SystemException;
    /**
     * Adds the doc details.
     *
     * @param docDetails the doc details
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    void addCffDocDetails(CffDocDetails docDetails) throws SystemException;

    /**
     * Update doc details.
     *
     * @param docDetails the doc details
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    void updateCffDocDetails(CffDocDetails docDetails) throws SystemException;
    /**
     * Delete doc details.
     *
     * @param docDetailsId the doc details id
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    void deleteCffDocDetails(int docDetailsId) throws SystemException, PortalException;
}
