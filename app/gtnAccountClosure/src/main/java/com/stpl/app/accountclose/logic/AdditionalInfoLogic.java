/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.logic;

import com.stpl.app.accountclose.dao.AdditionalInfoDAO;
import com.stpl.app.accountclose.dao.impl.AdditionalInfoDAOImpl;
import com.stpl.app.accountclose.dto.AttachmentDTO;
import com.stpl.app.accountclose.utils.Constants;
import com.stpl.app.model.AdditionalNotes;
import com.stpl.app.model.DocDetails;
import com.stpl.app.service.AdditionalNotesLocalServiceUtil;
import com.stpl.app.service.DocDetailsLocalServiceUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.io.File;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author kasiammal.m
 */
public class AdditionalInfoLogic {

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getLogger(AdditionalInfoLogic.class);
    /**
     * The additional info logic dao.
     */
    private AdditionalInfoDAO addInfoDao = new AdditionalInfoDAOImpl();

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
    public Boolean saveNotes(final int projectionId, final String createdBy, final String notes, final String moduleName) throws SystemException, Exception {

        final AdditionalNotes additionalNotes = AdditionalNotesLocalServiceUtil.createAdditionalNotes(0);
        additionalNotes.setCreatedDate(new Date());
        additionalNotes.setCreatedBy(createdBy);
        additionalNotes.setForecastType(moduleName);
        additionalNotes.setProjectionId(projectionId);
        additionalNotes.setNotes(notes);
        LOGGER.info("Entering saveNotes method with with projectionId " + projectionId + " createdBy " + createdBy + " notes " + notes + " moduleName " + moduleName);
        addInfoDao.saveNotes(additionalNotes);

        LOGGER.info("End of saveNotes method");

        return true;
    }

    public List<AttachmentDTO> getAttachmentDTOList(final int projectionId, final String moduleName) throws SystemException, Exception {
        final List<AttachmentDTO> attachmentDTOList = new ArrayList<AttachmentDTO>();
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DocDetails.class);
        dynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.PROJECTION_ID, projectionId));
        dynamicQuery.add(RestrictionsFactoryUtil.ilike("forecastType", moduleName));
        List<DocDetails> docDetailsList;
        AttachmentDTO attachmentDTO;

        LOGGER.info("getAttachmentDTOList with projectionId - " + projectionId + " moduleName - " + moduleName);

        docDetailsList = addInfoDao.getAttachmentDTOList(dynamicQuery);

        if (docDetailsList != null && !docDetailsList.isEmpty()) {
            for (final Iterator<DocDetails> iterator = docDetailsList.iterator(); iterator.hasNext();) {
                final DocDetails docDetails = iterator.next();
                attachmentDTO = new AttachmentDTO();
                if (docDetails.getFileType().trim().length() > Constants.ZERO) {
                    attachmentDTO.setDocumentName((docDetails.getFileName().contains("_".concat(String.valueOf(projectionId)))
                            ? docDetails.getFileName().replace("_".concat(String.valueOf(projectionId)), StringUtils.EMPTY) : docDetails.getFileName()) + "." + docDetails.getFileType());
                } else {
                    attachmentDTO.setDocumentName(docDetails.getFileName().contains("_".concat(String.valueOf(projectionId)))
                            ? docDetails.getFileName().replace("_".concat(String.valueOf(projectionId)), StringUtils.EMPTY) : docDetails.getFileName());
                }
                attachmentDTO.setDocDetailsId(docDetails.getDocDetailsId());
                attachmentDTO.setDateAdded(docDetails.getUploadedDate());
                attachmentDTO.setUserName(docDetails.getUploadedBy());
                attachmentDTOList.add(attachmentDTO);
            }
        }
        LOGGER.info("End of getAttachmentDTOList method");
        LOGGER.info("In getAttachmentDTOList() --size of attachmentDTOList" + attachmentDTOList.size());
        return attachmentDTOList;
    }

    public List<AttachmentDTO> addUserFile(final List<AttachmentDTO> list, final String userName) {
        final List<AttachmentDTO> finalList = new ArrayList<AttachmentDTO>();

        LOGGER.info("Entering addUserFile method with list size " + list.size() + " userName " + userName);
        for (final Iterator<AttachmentDTO> iterator = list.iterator(); iterator.hasNext();) {
            final AttachmentDTO dto = iterator.next();

            finalList.add(dto);
        }
        LOGGER.info("End of addUserFile method");
        return finalList;

    }

    public String getNotes(final int projectionId, final String moduleName, final String userName) throws SystemException, Exception {
        final StringBuilder notes = new StringBuilder();
        notes.append(StringUtils.EMPTY);
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AdditionalNotes.class);
        dynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.PROJECTION_ID, projectionId));
        dynamicQuery.add(RestrictionsFactoryUtil.ilike("forecastType", moduleName));
        List<AdditionalNotes> notesList;

        LOGGER.info("Entering getNotes method with projectionId " + projectionId + " moduleName " + moduleName + " userName " + userName);

        notesList = addInfoDao.getNotes(dynamicQuery);
        if (notesList != null && !notesList.isEmpty()) {
            for (final Iterator<AdditionalNotes> it = notesList.iterator(); it.hasNext();) {
                final AdditionalNotes additionalNotes = it.next();

                if (userName.equals(additionalNotes.getCreatedBy())) {
                    notes.append(Constants.BTN_LEFT);
                    notes.append(additionalNotes.getCreatedBy());
                    notes.append(">");
                    notes.append(" ");
                    DateFormat dateFormat = new SimpleDateFormat("<MM/dd/yyyy> <hh:mm:ss a> ");
                    Date date = additionalNotes.getCreatedDate();
                    notes.append(dateFormat.format(date));
                    notes.append(": ");
                    notes.append(additionalNotes.getNotes());
                    notes.append('\n');

                }
            }
            LOGGER.info("In getNotes() --size of additionalNotesList" + notesList.size());
        }
        LOGGER.info("End of getNotes method");

        return notes.toString();
    }

    public Boolean deleteUploadedFile(final int docDetailsId, final File file) throws SystemException, PortalException, Exception { 
        LOGGER.info("Entering deleteUploadedFile method with docDetailsId " + docDetailsId + " file " + file);
        addInfoDao.deleteDocDetails(docDetailsId);
        file.delete();
        LOGGER.info("End of deleteUploadedFile method");
        return true;
    }

    public Boolean saveUploadedFile(final int projectionId, final String fileName, final String uploadedBy, final Double fileSize, final String moduleName) throws SystemException, Exception {

        final DecimalFormat formatter = new DecimalFormat("#.#");
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DocDetails.class);
        dynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.PROJECTION_ID, projectionId));
        dynamicQuery.add(RestrictionsFactoryUtil.ilike("forecastType", moduleName));
        if (fileName.indexOf('.') == -1) {
            dynamicQuery.add(RestrictionsFactoryUtil.ilike("fileName", fileName));
        } else {
            dynamicQuery.add(RestrictionsFactoryUtil.ilike("fileName", fileName.substring(0, fileName.indexOf('.'))));
            dynamicQuery.add(RestrictionsFactoryUtil.ilike("fileType", fileName.substring(fileName.lastIndexOf('.') + 1)));
        }
        List<DocDetails> docDetailsList;
        DocDetails docDetails;

        LOGGER.info("Entering saveUploadedFile method with projectionId " + projectionId + " fileName " + fileName + " uploadedBy " + uploadedBy + " fileSize " + fileSize + " moduleName "
                + moduleName);

        docDetailsList = addInfoDao.getAttachmentDTOList(dynamicQuery);

        if (docDetailsList == null || docDetailsList.isEmpty()) {
            docDetails = DocDetailsLocalServiceUtil.createDocDetails(0);
            if (fileName.indexOf('.') == -1) {
                docDetails.setFileName(fileName);
                docDetails.setFileType(StringUtils.EMPTY);
            } else {
                docDetails.setFileName(fileName.substring(0, fileName.indexOf('.')));
                docDetails.setFileType(fileName.substring(fileName.lastIndexOf('.') + 1));
            }
            docDetails.setUploadedBy(uploadedBy);
            docDetails.setForecastType(moduleName);
            docDetails.setProjectionId(projectionId);
            docDetails.setUploadedDate(new Date());
            docDetails.setFileSize(formatter.format(fileSize));
            addInfoDao.addDocDetails(docDetails);
        } else {
            LOGGER.info("In saveUploadedFile() --size of docDetailsList" + docDetailsList.size());
            docDetails = docDetailsList.get(0);
            if (fileName.indexOf('.') == -1) {
                docDetails.setFileName(fileName);
                docDetails.setFileType(StringUtils.EMPTY);
            } else {
                docDetails.setFileName(fileName.substring(0, fileName.indexOf('.')));
                docDetails.setFileType(fileName.substring(fileName.lastIndexOf('.') + 1));
            }
            docDetails.setUploadedBy(uploadedBy);
            docDetails.setForecastType(moduleName);
            docDetails.setProjectionId(projectionId);
            docDetails.setUploadedDate(new Date());
            docDetails.setFileSize(formatter.format(fileSize));
            addInfoDao.updateDocDetails(docDetails);
        }
        LOGGER.info("End of saveUploadedFile method");

        return true;

    }
}
