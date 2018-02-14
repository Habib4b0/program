package com.stpl.gtn.gtn2o.ws.module.bcp.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.ws.GtnFileNameUtils;
import com.stpl.gtn.gtn2o.ws.bean.bcp.GtnWsBcpServiceBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.bcp.GtnWsBcpServiceRequest;
import java.nio.file.Files;
import java.util.Arrays;

@Service
public class GtnWsBcpFileUploader {
	public GtnWsBcpFileUploader() {
		super();
	}

	private static final GtnWSLogger GTNLOGGER = GtnWSLogger.getGTNLogger(GtnWsBcpFileUploader.class);

	public void uploadFiles(String finalFileParam, GtnUIFrameworkWebserviceRequest bcpServiceRequest)
			throws IOException, InterruptedException {
		GtnWsBcpServiceRequest gtnWsBcpServiceRequest = bcpServiceRequest.getGtnWsBcpServiceRequest();
		GtnWsBcpServiceBean gtnWsBcpServiceBean = gtnWsBcpServiceRequest.getGtnWsBcpServiceBean();
		String newTableName = StringUtils.EMPTY;
		String query = StringUtils.EMPTY;
		String userId = gtnWsBcpServiceBean.getUserId();
		String sessionId = gtnWsBcpServiceBean.getSessionId();
		String currentDate = gtnWsBcpServiceBean.getCurrentDateInString();
		String tableName = gtnWsBcpServiceBean.getTableName();
		String cumulativeBasePath = System.getProperty(GtnFrameworkCommonStringConstants.GTN_BASE_PATH)
				+ System.getProperty(GtnFrameworkCommonStringConstants.CUMULATIVE_FILE_PATH);

		String finalFile = finalFileParam;
		String serverName = gtnWsBcpServiceBean.getServerName();
		String schemaName = gtnWsBcpServiceBean.getSchemaName();
		String userName = gtnWsBcpServiceBean.getUserName();
		String password = gtnWsBcpServiceBean.getPassword();
		ProcessBuilder builder = null;
		List<String> fileList = new ArrayList<>();
		String logPath = cumulativeBasePath + "/Cumulative_Multiplication_" + userId + "_" + sessionId
				+ ".log";

		newTableName = tableName + userId + "_" + sessionId + "_" + currentDate;
		String[] commandArray = null;
		if (System.getProperty("os.name").toLowerCase(Locale.ENGLISH).contains("windows")) {
			finalFile = finalFile.replace("/", "\\");
			logPath = logPath.replace("/", "\\");
			StringBuilder command = new StringBuilder();
			commandArray = new String[3];
			commandArray[0] = "cmd.exe";
			commandArray[1] = "/c";
			command = createWindowsCommand(newTableName, finalFile, schemaName, serverName, userName, password,
					logPath);
			commandArray[2] = command.toString();
			builder = GtnWsProcessService.createProcess(commandArray);
		} else {
			query = newTableName + " IN ";
			StringBuilder strb = new StringBuilder();
			strb = createLinuxCommand(query, finalFile, schemaName, serverName, userName, password, logPath);
			GTNLOGGER.info("bcp ====" + strb.toString());
			File shellFile = GtnFileNameUtils.getFile(cumulativeBasePath + File.separator + "exec.sh");
			fileList.add(shellFile.getAbsolutePath());
			try (FileOutputStream outShell = GtnFileNameUtils.getFileOutputStream(shellFile)) {
				outShell.write(strb.toString().getBytes());
				outShell.flush();
			}
			boolean isExecutable = shellFile.setExecutable(true, false);
			boolean isWritable = shellFile.setWritable(true, false);
			boolean isReadable = shellFile.setReadable(true, false);
			GTNLOGGER.debug(Boolean.toString(isExecutable) + isWritable + isReadable);
			commandArray = new String[1];
			commandArray[0] = shellFile.getAbsolutePath();
			builder = GtnWsProcessService.createProcess(commandArray);
			File dir = GtnFileNameUtils.getFile(cumulativeBasePath);
			if (!dir.exists()) {
				dir.mkdir();
				isExecutable = dir.setExecutable(true, false);
				isWritable = dir.setWritable(true, false);
				isReadable = dir.setReadable(true, false);
				GTNLOGGER.debug(Boolean.toString(isExecutable) + isWritable + isReadable);
			}
                        for (String fileName : fileList) {
                        GTNLOGGER.debug("Deleted filename " + fileName);
                    }
			builder.directory(dir);

		}
                GTNLOGGER.info("uploadFiles command : "+Arrays.toString(commandArray));
		Process p = builder.start();
		p.waitFor();
		fileList.add(finalFile);
		fileList.add(logPath);
		GTNLOGGER.info("Upload Complete");
	}

	private StringBuilder createWindowsCommand(String newTableName, String finalFile, String schemaName,
			String serverName, String userName, String password, String logPath) {
		StringBuilder command = new StringBuilder();
		command.append("bcp ").append(newTableName).append(" IN ").append(finalFile).append(" -c -d").append(schemaName)
				.append(" -t , -S ").append(serverName).append(" -U ").append(userName).append(" -P \"")
				.append(password).append("\" > ").append(logPath);
		return command;

	}

	private StringBuilder createLinuxCommand(String query, String finalFile, String schemaName, String serverName,
			String userName, String password, String logPath) {
		StringBuilder strb = new StringBuilder();
		strb.append(System.getProperty("bcp.location"));
		strb.append(" ");
		strb.append(query);
		strb.append(finalFile);
		strb.append(" -c ");
		strb.append("-d ");
		strb.append(schemaName);
		strb.append(" -t ");
		strb.append(", ");
		strb.append("-S ");
		strb.append(serverName);
		strb.append(" -U ");
		strb.append(userName);
		strb.append(" -P ");
		strb.append("'").append(password).append("'");
		strb.append(">");
		strb.append(logPath);
		return strb;

	}

}
