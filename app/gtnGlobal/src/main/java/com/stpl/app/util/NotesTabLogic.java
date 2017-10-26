package com.stpl.app.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.jboss.logging.Logger;

import com.stpl.app.model.MasterDataFiles;
import com.stpl.app.service.MasterDataFilesLocalServiceUtil;
import com.stpl.app.security.StplSecurity;
import com.stpl.ifs.ui.NotesDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;

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
	private static final Logger LOGGER = Logger.getLogger(NotesTabLogic.class);

	@SuppressWarnings("unchecked")
	public List<NotesDTO> getAttachmentDTOList(int masterTableSid, String moduleName, String filepath) {
		List<NotesDTO> attachmentDTOList = new ArrayList<NotesDTO>();
		DynamicQuery docDetailsDynamicQuery = DynamicQueryFactoryUtil.forClass(MasterDataFiles.class);
		docDetailsDynamicQuery.add(RestrictionsFactoryUtil.eq("masterTableSid", masterTableSid));
		docDetailsDynamicQuery.add(RestrictionsFactoryUtil.ilike("masterTableName", moduleName));
		List<MasterDataFiles> docDetailsList = null;

		try {
			docDetailsList = MasterDataFilesLocalServiceUtil.dynamicQuery(docDetailsDynamicQuery);

			if (docDetailsList != null && docDetailsList.size() > 0) {
				for (MasterDataFiles docDetails : docDetailsList) {
					NotesDTO attachmentDTO = new NotesDTO();
					attachmentDTO.setDocDetailsId(docDetails.getMasterDataFilesSid());
					String filePath = docDetails.getFilePath();
					attachmentDTO.setDocumentFullPath(filePath);
					attachmentDTO.setDocumentName(filePath.substring(filePath.lastIndexOf("/") + 1, filePath.lastIndexOf(".")));
					String tempfilePath = docDetails.getFilePath();
					attachmentDTO.setDocumentFullPath(tempfilePath);
					String fileNameWithId = tempfilePath.replace(filepath, "");
					StringBuilder fileName = new StringBuilder(fileNameWithId);
					fileName.replace(fileName.lastIndexOf("."),fileName.length(), "");
					attachmentDTO.setDocumentName(fileName.toString());
					SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
					TimeZone central = TimeZone.getTimeZone("CST");
					format.setTimeZone(central);
					attachmentDTO.setDateAdded(format.format(docDetails.getCreatedDate()));
                                        attachmentDTO.setUserId(docDetails.getCreatedBy());
					attachmentDTO.setUserName(StplSecurity.userMap.get(docDetails.getCreatedBy()));
                                        attachmentDTOList.add(attachmentDTO);
				}
			}
		} catch (SystemException e) {
			LOGGER.error(e);
		}
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
	public Boolean deleteUploadedFile(int docDetailsId, String fileName) throws PortalException, SystemException {
		if (docDetailsId != 0) {
			MasterDataFilesLocalServiceUtil.deleteMasterDataFiles(docDetailsId);
		}
		File file = new File(fileName);
		file.delete();
		return true;
	}

	public void saveUploadedInformation(List<NotesDTO> availableUploadedInformation, String moduleName, int moduleSystemId) throws SystemException, PortalException {

		if (availableUploadedInformation != null && availableUploadedInformation.size() > 0) {

			for (NotesDTO uploadDetails : availableUploadedInformation) {
                            MasterDataFiles masterDataFiles ;
				if (uploadDetails.getDocDetailsId() == 0) {
                                    masterDataFiles = MasterDataFilesLocalServiceUtil.createMasterDataFiles(0);
					masterDataFiles.setMasterTableName(moduleName);
					masterDataFiles.setMasterTableSid(moduleSystemId);
					masterDataFiles.setFilePath(uploadDetails.getDocumentFullPath());
					masterDataFiles.setCreatedBy(uploadDetails.getUserId());
					masterDataFiles.setCreatedDate(new Date());
					masterDataFiles = MasterDataFilesLocalServiceUtil.addMasterDataFiles(masterDataFiles);
				} else {
					masterDataFiles = MasterDataFilesLocalServiceUtil.getMasterDataFiles(uploadDetails.getDocDetailsId());
                                        masterDataFiles.setMasterTableName(moduleName);
					masterDataFiles.setMasterTableSid(moduleSystemId);
					masterDataFiles.setFilePath(uploadDetails.getDocumentFullPath());
					masterDataFiles.setCreatedBy(uploadDetails.getUserId());
					masterDataFiles.setCreatedDate(new Date());
					MasterDataFilesLocalServiceUtil.updateMasterDataFiles(masterDataFiles);
				}
			}

		}
	}
}
