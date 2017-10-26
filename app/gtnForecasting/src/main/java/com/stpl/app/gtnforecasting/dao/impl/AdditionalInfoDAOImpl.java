/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.dao.impl;

import com.stpl.app.gtnforecasting.dao.AdditionalInfoDAO;
import com.stpl.app.model.AdditionalNotes;
import com.stpl.app.model.DocDetails;
import com.stpl.app.service.AdditionalNotesLocalServiceUtil;
import com.stpl.app.service.DocDetailsLocalServiceUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class AdditionalInfoDAOImpl.
 *
 * @author lokeshwari
 */
public class AdditionalInfoDAOImpl implements AdditionalInfoDAO {

    /**
     * Gets the attachment dto list.
     *
     * @param dynamicQuery the dynamic query
     * @return the attachment dto list
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    @Override
    public List getAttachmentDTOList(final DynamicQuery dynamicQuery) throws SystemException {
        return DocDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    /**
     * Adds the doc details.
     *
     * @param docDetails the doc details
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    @Override
    public void addDocDetails(final DocDetails docDetails) throws SystemException{
        DocDetailsLocalServiceUtil.addDocDetails(docDetails);
    }

    /**
     * Update doc details.
     *
     * @param docDetails the doc details
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    @Override
    public void updateDocDetails(final DocDetails docDetails) throws SystemException{
        DocDetailsLocalServiceUtil.updateDocDetails(docDetails);
    }

    /**
     * Delete doc details.
     *
     * @param docDetailsId the doc details id
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    @Override
    public void deleteDocDetails(final int docDetailsId) throws SystemException, PortalException {
        DocDetailsLocalServiceUtil.deleteDocDetails(docDetailsId);
    }

    /**
     * Save notes.
     *
     * @param additionalNotesList the additional notes list
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    @Override
    public void saveNotes(final AdditionalNotes additionalNotesList) throws SystemException{
        AdditionalNotesLocalServiceUtil.addAdditionalNotes(additionalNotesList);
    }

    /**
     * Gets the notes.
     *
     * @param dynamicQuery the dynamic query
     * @return the notes
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    @Override
    public List getNotes(final DynamicQuery dynamicQuery) throws SystemException {

        return AdditionalNotesLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
}
