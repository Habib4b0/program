/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.additionalinformation.logic;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.nationalassumptions.util.CommonUtils;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.CommonConstants.PROJECTION_ID;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.model.AdditionalNotes;
import com.stpl.app.model.DocDetails;
import com.stpl.app.service.AdditionalNotesLocalServiceUtil;
import com.stpl.app.service.DocDetailsLocalServiceUtil;
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
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author gopinath
 */
public class NotesTabLogic {

    /**
     * Method to get attached doc List
     *
     * @param masterTableSid
     * @param moduleName
     * @param filepath
     * @return attachmentDTOList
     */
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(NotesTabLogic.class);
    
    /**
     * Gets the attachment dto list.
     *
     * @param projectionId - Projection id to be passed .
     * @param moduleName - Module Name to be passed may be Non Mandated .
     * @return List of AttachmentDTO Object .
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public NotesTabLogic(){
    	super();
    }
    
    public List<NotesDTO> getAttachmentDTOList(final int projectionId, final String moduleName, String fileUploadPath) throws SystemException {

        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        TimeZone central = TimeZone.getTimeZone("CST");
        dateTimeFormat.setTimeZone(central);
        final List<NotesDTO> attachmentDTOList = new ArrayList<>();
        final DynamicQuery dynamicQuery = DocDetailsLocalServiceUtil.dynamicQuery();
        dynamicQuery.add(RestrictionsFactoryUtil.eq(PROJECTION_ID.getConstant(), projectionId));
        dynamicQuery.add(RestrictionsFactoryUtil.ilike(Constant.FORECAST_TYPE, moduleName));
        List<DocDetails> docDetailsList;
        NotesDTO attachmentDTO;
        LOGGER.debug("getAttachmentDTOList with projectionId= {}, moduleName= {} " , projectionId, moduleName);
        docDetailsList = DocDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);
        if (docDetailsList != null && !docDetailsList.isEmpty()) {
            for (final Iterator<DocDetails> iterator = docDetailsList.iterator(); iterator.hasNext();) {
                final DocDetails docDetails = iterator.next();
                attachmentDTO = new NotesDTO();
                if (docDetails.getFileType().trim().length() > 0) {
                    attachmentDTO.setDocumentName((docDetails.getFileName().contains("_".concat(String.valueOf(projectionId)))
                            ? docDetails.getFileName().replace("_".concat(String.valueOf(projectionId)), StringUtils.EMPTY) : docDetails.getFileName()) + '.' + docDetails.getFileType());
                } else {
                    attachmentDTO.setDocumentName(docDetails.getFileName().contains("_".concat(String.valueOf(projectionId)))
                            ? docDetails.getFileName().replace("_".concat(String.valueOf(projectionId)), StringUtils.EMPTY) : docDetails.getFileName());
                }

                attachmentDTO.setDocumentFullPath(fileUploadPath + docDetails.getFileName() + "." + docDetails.getFileType());
                attachmentDTO.setDocDetailsId(docDetails.getDocDetailsId());
                attachmentDTO.setDateAdded(dateTimeFormat.format(docDetails.getUploadedDate()));
                attachmentDTO.setUserName(docDetails.getUploadedBy());
                attachmentDTOList.add(attachmentDTO);
            }
        }
        LOGGER.debug("End of getAttachmentDTOList method");
        LOGGER.debug("In getAttachmentDTOList() --size of attachmentDTOList= {}" , attachmentDTOList.size());
        return attachmentDTOList;
    }


    /**
     * Method o Delete the Uploaded File
     *
     * @param docDetailsId
     * @param moduleName
     * @param fileName
     * @return true or false
     * @throws SystemException
     * @throws PortalException
     */
    public Boolean deleteUploadedFile(int docDetailsId, String fileName) throws PortalException {
        if (docDetailsId != 0) {
            DocDetailsLocalServiceUtil.deleteDocDetails(docDetailsId);
        }
        File file = new File(fileName);
        boolean isFileExists=file.delete();
        LOGGER.info("File deleted successfully= {} ",isFileExists);
        return BooleanConstant.getTrueFlag();
    }

    public void saveUploadedFile(int projectionId, String fileName, String uploadedBy, int fileSize, String moduleName) throws SystemException {
        final DecimalFormat formatter = new DecimalFormat("#.#");
        final DynamicQuery dynamicQuery = DocDetailsLocalServiceUtil.dynamicQuery();
        dynamicQuery.add(RestrictionsFactoryUtil.eq(PROJECTION_ID.getConstant(), projectionId));
        dynamicQuery.add(RestrictionsFactoryUtil.ilike(Constant.FORECAST_TYPE, moduleName));
        if (fileName.indexOf('.') == -1) {
            dynamicQuery.add(RestrictionsFactoryUtil.ilike(Constant.FILE_NAME, fileName));
        } else {
            dynamicQuery.add(RestrictionsFactoryUtil.ilike(Constant.FILE_NAME, fileName.substring(0, fileName.indexOf('.'))));
            dynamicQuery.add(RestrictionsFactoryUtil.ilike("fileType", fileName.substring(fileName.lastIndexOf('.') + 1)));
        }
        List<DocDetails> docDetailsList;
        DocDetails docDetails;

        LOGGER.debug("Entering saveUploadedFile method with projectionId= {}, fileName= {}, uploadedBy= {}, fileSize= {}, moduleName= {} " , projectionId, fileName, uploadedBy, fileSize, moduleName);

        docDetailsList = DocDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);

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
            DocDetailsLocalServiceUtil.addDocDetails(docDetails);
        } else {
            LOGGER.debug("In saveUploadedFile() --size of docDetailsList+ {}" , docDetailsList.size());
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
            DocDetailsLocalServiceUtil.updateDocDetails(docDetails);

        }
        LOGGER.debug("End of saveUploadedFile method");

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
    public Boolean saveNotes(final int projectionId, final String createdBy, final String notes, final String moduleName) throws SystemException {
        LOGGER.debug("Entering saveNotes method with with projectionId= {}, createdBy= {}, notes= {}, moduleName= {} " , projectionId, createdBy, notes, moduleName);
        final AdditionalNotes additionalNotes = AdditionalNotesLocalServiceUtil.createAdditionalNotes(0);
        additionalNotes.setCreatedDate(new Date());
        additionalNotes.setCreatedBy(CommonUtils.getUserNameById(createdBy));
        additionalNotes.setForecastType(moduleName);
        additionalNotes.setProjectionId(projectionId);
        additionalNotes.setNotes(notes);

        AdditionalNotesLocalServiceUtil.addAdditionalNotes(additionalNotes);

        LOGGER.debug("End of saveNotes method");

        return BooleanConstant.getTrueFlag();
    }

    /**
     * Adds the user file.
     *
     * @param list - List of AttachmentDTO Object to be passed .
     * @return List of AttachmentDTO Object .
     */
    public List<NotesDTO> addUserFile(final List<NotesDTO> list) {
        final List<NotesDTO> finalList = new ArrayList<>();
        LOGGER.debug("Entering addUserFile method with list size= {} " , list.size());
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
    public String getNotes(final int projectionId, final String moduleName, List<String> notesListTemp) throws SystemException {

        LOGGER.debug("Entering getNotes method with projectionId= {}, moduleName= {} " , projectionId, moduleName);

        final StringBuilder notes = new StringBuilder();
        notes.append(StringUtils.EMPTY);

        final DynamicQuery dynamicQuery = AdditionalNotesLocalServiceUtil.dynamicQuery();
        dynamicQuery.add(RestrictionsFactoryUtil.eq(PROJECTION_ID.getConstant(), projectionId));
        dynamicQuery.add(RestrictionsFactoryUtil.ilike(Constant.FORECAST_TYPE, moduleName));
        List<AdditionalNotes> notesList;

        notesList = AdditionalNotesLocalServiceUtil.dynamicQuery(dynamicQuery);
        if (notesList != null && !notesList.isEmpty()) {
            for (final Iterator<AdditionalNotes> it = notesList.iterator(); it.hasNext();) {
                final AdditionalNotes additionalNotes = it.next();
                notes.append(additionalNotes.getNotes() ).append( '\n');
                notesListTemp.add(additionalNotes.getNotes() + "\n");
            }
            LOGGER.debug("In getNotes() --size of additionalNotesList= {}" , notesList.size());
        }
        LOGGER.debug("End of getNotes method");

        return notes.toString();
    }
    
    public void saveAttachFile (List<NotesDTO> availableUploadedInformation, String moduleName,
            int moduleSystemId) throws  PortalException {
             for (NotesDTO uploadDetails : availableUploadedInformation) {
                String query = com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil.getQuery("insertAttachQuery");
                query = query.replace("?ATTACHMENT_TABLE_SID","'" + moduleSystemId + "'");
                query = query.replace("?MASTER_TABLE_NAME","'" + moduleName + "'");
                try {
                    query = query.replace("?FILE_DATA", "'" + Arrays.toString(readBytesFromFile(uploadDetails.getDocumentFullPath()))+ "'" );
                } catch (IOException e) {
                	LOGGER.error("Error While Fetching");
                }
                query = query.replace("?FILE_NAME","'"  + uploadDetails.getDocumentName() + "'" );
                query = query.replace("?CREATED_BY", "'" + uploadDetails.getUserId()+ "'" );
                HelperTableLocalServiceUtil.executeUpdateQuery(query);
             }
            }
         private static byte[] readBytesFromFile(String filePath) throws IOException {
                File inputFile = GtnFileUtil.getFile(filePath);
                FileInputStream inputStreamIm= GtnFileUtil.getFileInputStreamFile(inputFile);
                byte[] fileBytes = new byte[(int) inputFile.length()];
                int i=inputStreamIm.read(fileBytes);
                if(i>0) 
                   return fileBytes;
                return  new byte[0];
            }


}
