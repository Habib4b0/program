/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.dao.impl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.cff.dao.AdditionalInfoDAO;
import com.stpl.app.parttwo.model.CffAdditionalInfo;
import com.stpl.app.parttwo.model.CffDocDetails;
import com.stpl.app.parttwo.service.CffAdditionalInfoLocalServiceUtil;
import com.stpl.app.parttwo.service.CffDocDetailsLocalServiceUtil;
import java.util.List;

/**
 *
 * @author Manasa
 */
public class AdditionalInfoDAOImpl implements AdditionalInfoDAO{
    
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
        return CffDocDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);
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
        return CffAdditionalInfoLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
    /**
     * Save notes.
     *
     * @param additionalNotesList the additional notes list
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    @Override
    public void saveNotes(final CffAdditionalInfo additionalNotesList) throws SystemException {
        CffAdditionalInfoLocalServiceUtil.addCffAdditionalInfo(additionalNotesList);
    }
    /**
     * Adds the doc details.
     *
     * @param docDetails the doc details
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    @Override
    public void addCffDocDetails(final CffDocDetails docDetails) throws SystemException {
        CffDocDetailsLocalServiceUtil.addCffDocDetails(docDetails);
    }

    /**
     * Update doc details.
     *
     * @param docDetails the doc details
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    @Override
    public void updateCffDocDetails(final CffDocDetails docDetails) throws SystemException {
        CffDocDetailsLocalServiceUtil.updateCffDocDetails(docDetails);
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
    public void deleteCffDocDetails(final int docDetailsId) throws PortalException {
        CffDocDetailsLocalServiceUtil.deleteCffDocDetails(docDetailsId);
    }
    }
