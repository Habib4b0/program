/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.logic;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.cff.dao.AdditionalInfoDAO;
import com.stpl.app.cff.dao.impl.AdditionalInfoDAOImpl;
import com.stpl.app.cff.queryutils.CommonQueryUtils;
import com.stpl.app.cff.util.xmlparser.SQlUtil;
import com.stpl.app.parttwo.model.CffAdditionalInfo;
import com.stpl.app.parttwo.model.CffDocDetails;
import com.stpl.app.parttwo.service.CffAdditionalInfoLocalServiceUtil;
import com.stpl.app.parttwo.service.CffDocDetailsLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.ui.NotesDTO;
import com.stpl.ifs.util.GtnFileUtil;
import com.stpl.ifs.util.constants.BooleanConstant;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Manasa
 */
public class AdditionalInfoLogic {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AdditionalInfoLogic.class);
    
    
    
    /**
     * The additional info logic dao.
     */
    private final AdditionalInfoDAO addInfoDao = new AdditionalInfoDAOImpl();

    /**
     * Gets the attachment dto list.
     *
     * @param projectionId - Projection id to be passed .
     * @param moduleName - Module Name to be passed may be Non Mandated .
     * @return List of AttachmentDTO Object .
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public List<NotesDTO> getAttachmentDTOList(final int projectionId, final String moduleName) throws SystemException {
        final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MM/dd/yyyy");
        final List<NotesDTO> attachmentDTOList = new ArrayList<>();
        final DynamicQuery dynamicQuery = CffDocDetailsLocalServiceUtil.dynamicQuery();
        dynamicQuery.add(RestrictionsFactoryUtil.eq("cffMasterSid", projectionId));
        List<CffDocDetails> docDetailsList;
        NotesDTO attachmentDTO;

        LOGGER.debug("getAttachmentDTOList with projectionId= {} and moduleName= {}", projectionId, moduleName);

        docDetailsList = addInfoDao.getAttachmentDTOList(dynamicQuery);

        if (docDetailsList != null && !docDetailsList.isEmpty()) {
            for (final Iterator<CffDocDetails> iterator = docDetailsList.iterator(); iterator.hasNext();) {
                final CffDocDetails docDetails = iterator.next();
                attachmentDTO = new NotesDTO();
                if (docDetails.getFileType().trim().length() > 0) {
                    attachmentDTO.setDocumentName(docDetails.getFileName() + "." + docDetails.getFileType());
                } else {
                    attachmentDTO.setDocumentName(docDetails.getFileName());
                }
                attachmentDTO.setDocDetailsId(docDetails.getCffDocDetailsSid());
                attachmentDTO.setDateAdded(dateTimeFormat.format(docDetails.getUploadDate()));
                attachmentDTO.setUserName(String.valueOf(docDetails.getUploadBy()));
                attachmentDTOList.add(attachmentDTO);
            }
        }
        LOGGER.debug("End of getAttachmentDTOList method");
        LOGGER.debug("In getAttachmentDTOList() --size of attachmentDTOList= {}", attachmentDTOList.size());
        return attachmentDTOList;
    }

    /**
     * Adds the user file.
     *
     * @param list - List of AttachmentDTO Object to be passed .
     * @return List of AttachmentDTO Object .
     */
    public List<NotesDTO> addUserFile(final List<NotesDTO> list) {
        final List<NotesDTO> finalList = new ArrayList<>();
        LOGGER.debug("Entering addUserFile method with list size= {}", list.size());
        for (final Iterator<NotesDTO> iterator = list.iterator(); iterator.hasNext();) {
            final NotesDTO dto = iterator.next();

            finalList.add(dto);
        }
        LOGGER.debug("End of addUserFile method");
        return finalList;

    }

    /**
     * Gets the notes.
     *
     * @param projectionId - projectionId to be saved .
     * @param moduleName the module name
     * @return updated notes values .
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public String getNotes(final int projectionId, final String moduleName) throws SystemException {

        LOGGER.debug("Entering getNotes method with projectionId= {} and moduleName= {} ", projectionId, moduleName);

        final StringBuilder notes = new StringBuilder();
        notes.append(StringUtils.EMPTY);
        final DynamicQuery dynamicQuery = CffAdditionalInfoLocalServiceUtil.dynamicQuery();
        dynamicQuery.add(RestrictionsFactoryUtil.eq("cffMasterSid", projectionId));
        List<CffAdditionalInfo> notesList;

        notesList = addInfoDao.getNotes(dynamicQuery);
        if (notesList != null && !notesList.isEmpty()) {
            for (final Iterator<CffAdditionalInfo> it = notesList.iterator(); it.hasNext();) {
                final CffAdditionalInfo additionalNotes = it.next();
                notes.append(additionalNotes.getNotes());
                notes.append('\n');
            }
            LOGGER.debug("In getNotes() --size of additionalNotesList= {}", notesList.size());
        }
        LOGGER.debug("End of getNotes method");

        return notes.toString();
    }

    /**
     * Method is used for saving the notes entered .
     *
     * @param projectionId - projectionId to be saved .
     * @param createdBy - Created User name .
     * @param notes - Entering Notes .
     * @param moduleName - Module Name like NM.
     * @return boolean value .
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public Boolean saveNotes(final int projectionId, final String createdBy, final String notes, final String moduleName) {
        LOGGER.debug("Entering saveNotes method with with projectionId= {} and createdBy= {} and notes= {} and moduleName= {}", projectionId, createdBy, notes, moduleName);
        List input = new ArrayList();
        input.add(Integer.valueOf(createdBy));
        input.add(projectionId);
        input.add(notes);
        CommonQueryUtils.updateAppData(input, "insertaddinfo");
        LOGGER.debug("End of saveNotes method");

        return BooleanConstant.getTrueFlag();
    }

    /**
     * method to Save uploaded file .
     *
     * @param projectionId - projectionId to be saved .
     * @param fileName - name of the uploaded files .
     * @param uploadedBy - Name of the uploaded user .
     * @param fileSize - size of the file uploaded .
     * @param moduleName - module Name of the functionality available .
     * @return boolean value .
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public Boolean saveUploadedFile(final int projectionId, final String fileName, final String uploadedBy, final Double fileSize, final String moduleName) {
        List input = new ArrayList<>();
        final DecimalFormat formatter = new DecimalFormat("#.#");
        input.add(projectionId);
        if (fileName.indexOf('.') == -1) {
            input.add(" AND FILE_NAME like '" + fileName + "'");
            input.add(" ");
        } else {
            input.add(" AND FILE_NAME like '" + fileName.substring(0, fileName.indexOf('.')) + "'");
            input.add(" AND FILE_TYPE like '" + fileName.substring(fileName.lastIndexOf('.') + 1) + "'");
        }

        List docDetailsList;

        LOGGER.debug("Entering saveUploadedFile method with projectionId= {} and fileName= {} and uploadedBy= {} and fileSize= {} and moduleName= {} ", projectionId, fileName, uploadedBy, fileSize, moduleName);
        docDetailsList = CommonQueryUtils.getAppData(input, "selectdocdetails", null);

        List applist = new ArrayList();
        if (docDetailsList == null || docDetailsList.isEmpty()) {
            LOGGER.debug("Inside INSRT FOR DOC DETAILS");
            if (fileName.indexOf('.') == -1) {
                applist.add(fileName);
                applist.add("");
            } else {
                applist.add(fileName.substring(0, fileName.indexOf('.')));
                applist.add(fileName.substring(fileName.lastIndexOf('.') + 1));
            }
            applist.add(Integer.valueOf(uploadedBy));
            applist.add(projectionId);
            applist.add(formatter.format(fileSize));
            CommonQueryUtils.updateAppData(applist, "insertdocdetails");
        } else {
            LOGGER.debug("In saveUploadedFile() --size of docDetailsList= {}", docDetailsList.size());

            if (fileName.indexOf('.') == -1) {
                applist.add(fileName);
                applist.add("");
            } else {
                applist.add(fileName.substring(0, fileName.indexOf('.')));
                applist.add(fileName.substring(fileName.lastIndexOf('.') + 1));
            }
            applist.add(Integer.valueOf(uploadedBy));
            applist.add(projectionId);
            applist.add(formatter.format(fileSize));
            CommonQueryUtils.updateAppData(applist, "updatedocdetails");
        }
        LOGGER.debug("End of saveUploadedFile method");

        return BooleanConstant.getTrueFlag();

    }

    /**
     * Method to delete the uploaded file .
     *
     * @param docDetailsId - id for the data to delete .
     * @return boolean .
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public Boolean deleteUploadedFile(final int docDetailsId) throws PortalException { // changed
        LOGGER.debug("Entering deleteUploadedFile method with docDetailsId= {}", docDetailsId);
        addInfoDao.deleteCffDocDetails(docDetailsId);
        LOGGER.debug("End of deleteUploadedFile method");
        return BooleanConstant.getTrueFlag();
    }
    
	public void saveAttachFile(List<NotesDTO> availableUploadedInformation, String moduleName, int moduleSystemId)
			throws  PortalException {
		for (NotesDTO uploadDetails : availableUploadedInformation) {
			String query = SQlUtil.getQuery("insertAttachQuery");
			query = query.replace("?ATTACHMENT_TABLE_SID", "'" + moduleSystemId + "'");
			query = query.replace("?MASTER_TABLE_NAME", "'" + moduleName + "'");
			try {
				query = query.replace("?FILE_DATA", "'" + Arrays.toString(readBytesFromFile(uploadDetails.getDocumentFullPath())) + "'");
			} catch (IOException e) {
				LOGGER.error("Error in save Attachment");
			}
			query = query.replace("?FILE_NAME", "'" + uploadDetails.getDocumentName() + "'");
			query = query.replace("?CREATED_BY", "'" + uploadDetails.getUserId() + "'");
			HelperTableLocalServiceUtil.executeUpdateQuery(query);
		}
	}

	private static byte[] readBytesFromFile(String filePath) throws IOException {
		File inputFile = GtnFileUtil.getFile(filePath);
		FileInputStream inputStreamIm = GtnFileUtil.getFileInputStreamFile(inputFile);
		byte[] fileBytes = new byte[(int) inputFile.length()];
		int i = inputStreamIm.read(fileBytes);
		if (i > 0)
			return fileBytes;
		return new byte[0];
	}

}
