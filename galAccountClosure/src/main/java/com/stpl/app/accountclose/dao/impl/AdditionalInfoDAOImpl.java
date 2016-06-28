/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.dao.impl;

import com.stpl.app.accountclose.dao.AdditionalInfoDAO;
import com.stpl.app.model.AdditionalNotes;
import com.stpl.app.model.DocDetails;
import com.stpl.app.service.AdditionalNotesLocalServiceUtil;
import com.stpl.app.service.DocDetailsLocalServiceUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.List;

/**
 *
 * @author kasiammal.m
 */
public class AdditionalInfoDAOImpl implements AdditionalInfoDAO {

    public void saveNotes(AdditionalNotes additionalNotesList) throws SystemException, Exception {
         AdditionalNotesLocalServiceUtil.addAdditionalNotes(additionalNotesList);
    }

    public List getAttachmentDTOList(DynamicQuery dynamicQuery) throws SystemException, Exception {
          return DocDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    public void addDocDetails(DocDetails docDetails) throws SystemException, Exception {
           DocDetailsLocalServiceUtil.addDocDetails(docDetails);
    }

    public void updateDocDetails(DocDetails docDetails) throws SystemException, Exception {
       DocDetailsLocalServiceUtil.updateDocDetails(docDetails);
    }

    public void deleteDocDetails(int docDetailsId) throws SystemException, PortalException, Exception {
         DocDetailsLocalServiceUtil.deleteDocDetails(docDetailsId);
    }

    public List getNotes(DynamicQuery dynamicQuery) throws SystemException, Exception {
        return AdditionalNotesLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
}
