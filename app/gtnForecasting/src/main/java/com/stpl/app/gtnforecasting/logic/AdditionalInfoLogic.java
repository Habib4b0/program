/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.logic;

import com.stpl.app.gtnforecasting.dao.AdditionalInfoDAO;
import com.stpl.app.gtnforecasting.dao.impl.AdditionalInfoDAOImpl;
import com.stpl.app.gtnforecasting.dto.AttachmentDTO;
import com.stpl.app.model.AdditionalNotes;
import com.stpl.app.model.DocDetails;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.service.AdditionalNotesLocalServiceUtil;
import com.stpl.app.service.DocDetailsLocalServiceUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class AdditionalInfoLogic.
 *
 * @author lokeshwari
 */
public class AdditionalInfoLogic {

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getLogger(AdditionalInfoLogic.class);
    /**
     * The additional info logic dao.
     */
    private final AdditionalInfoDAO addInfoDao = new AdditionalInfoDAOImpl();
    public SimpleDateFormat wordFormat = new SimpleDateFormat("<MM/dd/yyyy> <hh:mm:ss a>");

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
    public Boolean saveUploadedFile(final int projectionId, final String fileName, final String uploadedBy, final Double fileSize, final String moduleName) throws SystemException {

        final DecimalFormat formatter = new DecimalFormat("#.#");
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DocDetails.class);
        dynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_ID, projectionId));
        dynamicQuery.add(RestrictionsFactoryUtil.ilike(Constant.FORECAST_TYPE, moduleName));
        if (fileName.indexOf('.') == -1) {
            dynamicQuery.add(RestrictionsFactoryUtil.ilike(Constant.FILE_NAME, fileName));
        } else {
            dynamicQuery.add(RestrictionsFactoryUtil.ilike(Constant.FILE_NAME, fileName.substring(0, fileName.indexOf('.'))));
            dynamicQuery.add(RestrictionsFactoryUtil.ilike("fileType", fileName.substring(fileName.lastIndexOf('.') + 1)));
        }
        List<DocDetails> docDetailsList;
        DocDetails docDetails;

        LOGGER.debug("Entering saveUploadedFile method with projectionId " + projectionId + " fileName " + fileName + " uploadedBy " + uploadedBy + " fileSize " + fileSize + " moduleName  "
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
            LOGGER.debug("In saveUploadedFile() --size of docDetailsList" + docDetailsList.size());
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
        LOGGER.debug("End of saveUploadedFile method");

        return true;

    }

    /**
     * Gets the attachment dto list.
     *
     * @param projectionId - Projection id to be passed .
     * @param moduleName - Module Name to be passed may be Non Mandated .
     * @return List of AttachmentDTO Object .
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public List<AttachmentDTO> getAttachmentDTOList(final int projectionId, final String moduleName) throws SystemException {
        final List<AttachmentDTO> attachmentDTOList = new ArrayList<>();
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DocDetails.class);
        dynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_ID, projectionId));
        dynamicQuery.add(RestrictionsFactoryUtil.ilike(Constant.FORECAST_TYPE, moduleName));
        List<DocDetails> docDetailsList;
        AttachmentDTO attachmentDTO;

        LOGGER.debug("getAttachmentDTOList with projectionId - " + projectionId + " moduleName - " + moduleName);

        docDetailsList = addInfoDao.getAttachmentDTOList(dynamicQuery);

        if (docDetailsList != null && !docDetailsList.isEmpty()) {
            for (final Iterator<DocDetails> iterator = docDetailsList.iterator(); iterator.hasNext();) {
                final DocDetails docDetails = iterator.next();
                attachmentDTO = new AttachmentDTO();
                if (docDetails.getFileType().trim().length() > Constant.ZERO) {
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
        LOGGER.debug("End of getAttachmentDTOList method");
        LOGGER.debug("In getAttachmentDTOList() --size of attachmentDTOList" + attachmentDTOList.size());
        return attachmentDTOList;
    }

    /**
     * Method to delete the uploaded file .
     *
     * @param docDetailsId - id for the data to delete .
     * @param file the file
     * @return boolean .
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public Boolean deleteUploadedFile(final int docDetailsId, final File file) throws SystemException, PortalException{
        LOGGER.debug("Entering deleteUploadedFile method with docDetailsId " + docDetailsId + " file " + file);
        addInfoDao.deleteDocDetails(docDetailsId);
        file.delete();
        LOGGER.debug("End of deleteUploadedFile method");
        return true;
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
    public Boolean saveNotes(final int projectionId, final String createdBy, final String notes, final String moduleName) throws SystemException{

        final AdditionalNotes additionalNotes = AdditionalNotesLocalServiceUtil.createAdditionalNotes(0);
        additionalNotes.setCreatedDate(new Date());
        additionalNotes.setCreatedBy(createdBy);
        additionalNotes.setForecastType(moduleName);
        additionalNotes.setProjectionId(projectionId);
        additionalNotes.setNotes(notes);

        LOGGER.debug("Entering saveNotes method with with projectionId " + projectionId + " createdBy " + createdBy + " notes " + notes + " moduleName " + moduleName);
        addInfoDao.saveNotes(additionalNotes);

        LOGGER.debug("End of saveNotes method");

        return true;
    }

    /**
     * Adds the user file.
     *
     * @param list - List of AttachmentDTO Object to be passed .
     * @param userName - User Name .
     * @return List of AttachmentDTO Object .
     */
    public List<AttachmentDTO> addUserFile(final List<AttachmentDTO> list, final String userName) {
        final List<AttachmentDTO> finalList = new ArrayList<>();

        LOGGER.debug("Entering addUserFile method with list size " + list.size() + " userName " + userName);
        for (final Iterator<AttachmentDTO> iterator = list.iterator(); iterator.hasNext();) {
            final AttachmentDTO dto = iterator.next();

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
     * @param userName - User Name .
     * @return updated notes values .
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public String getNotes(final int projectionId, final String moduleName, final String userName, List wordList) throws SystemException{
        final StringBuilder notes = new StringBuilder();
        notes.append(StringUtils.EMPTY);
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AdditionalNotes.class);
        dynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_ID, projectionId));
        dynamicQuery.add(RestrictionsFactoryUtil.ilike(Constant.FORECAST_TYPE, moduleName));
        List<AdditionalNotes> notesList;

        LOGGER.debug("Entering getNotes method with projectionId " + projectionId + " moduleName " + moduleName + " userName " + userName);

        notesList = addInfoDao.getNotes(dynamicQuery);
        if (notesList != null && !notesList.isEmpty()) {
            for (final Iterator<AdditionalNotes> it = notesList.iterator(); it.hasNext();) {
                final AdditionalNotes additionalNotes = it.next();

                if (userName.equals(additionalNotes.getCreatedBy())) {
                    String str = StringUtils.EMPTY;
                    str += Constant.BTN_LEFT + additionalNotes.getCreatedBy() + "> " + wordFormat.format(additionalNotes.getCreatedDate()) + " : " + additionalNotes.getNotes() + '\n';
                    notes.append(str);
                    wordList.add(str);
                }
            }
            LOGGER.debug("In getNotes() --size of additionalNotesList" + notesList.size());
        }
        LOGGER.debug("End of getNotes method");

        return notes.toString();
    }
}
