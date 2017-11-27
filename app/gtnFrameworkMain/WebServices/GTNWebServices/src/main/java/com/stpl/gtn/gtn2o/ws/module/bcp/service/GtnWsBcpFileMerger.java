package com.stpl.gtn.gtn2o.ws.module.bcp.service;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.ws.GtnFileNameUtils;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.util.GtnWsProcessUtil;

@Service
public class GtnWsBcpFileMerger {
	public GtnWsBcpFileMerger() {
		super();
	}
	private static final GtnWSLogger GTNLOGGER = GtnWSLogger.getGTNLogger(GtnWsBcpFileMerger.class);

	public void mergeFiles(List<String> fileList, String finalFile, List<Closeable> fileOperationList,
			String folderName) throws IOException, InterruptedException {

		Path path = GtnFileNameUtils.getPath(finalFile.substring(0, finalFile.lastIndexOf('/')));
		if (!Files.exists(path)) {
			Files.createDirectories(path);
		}

		File file = GtnFileNameUtils.getFile(finalFile);
		boolean isCreated = file.createNewFile();
		GTNLOGGER.debug(Boolean.toString(isCreated));
		long time = System.currentTimeMillis();
		String[] command;
		if (System.getProperty("os.name").toLowerCase(Locale.ENGLISH).contains("windows")) {
			command = createCommandForWindows(fileList, finalFile);
		} else {
			StringBuilder strb = new StringBuilder();
			strb.append("cat ");
			for (String sourceFile : fileList) {
				strb.append(sourceFile).append(" ");
			}
			strb.append(" > ");
			strb.append(finalFile);
			File shellFile = GtnFileNameUtils
					.getFile(folderName + "/Cumulative_Logic" + File.separator + "Concat_exec.sh");

			try (FileOutputStream outShell = GtnFileNameUtils.getFileOutputStream(shellFile)) {
				outShell.write(strb.toString().getBytes());
				outShell.flush();
			}
			boolean isExecutable = shellFile.setExecutable(true, false);
			boolean isWritable = shellFile.setWritable(true, false);
			boolean isReadable = shellFile.setReadable(true, false);
			GTNLOGGER.debug(Boolean.toString(isExecutable) + isWritable + isReadable);
			command = new String[1];
			command[0] = shellFile.getAbsolutePath();
			fileList.add(shellFile.getAbsolutePath());
		}
		ProcessBuilder builder = GtnWsProcessUtil.createProcess(command);
		Process p = builder.start();
		p.waitFor();

		for (Closeable closeable : fileOperationList) {
			closeable.close();
		}

		for (String fileName : fileList) {
			Files.delete(GtnFileNameUtils.getPath(fileName));
		}

		GTNLOGGER.info("Merge Time: " + (System.currentTimeMillis() - time));

	}

	public String[] createCommandForWindows(List<String> fileList, String finalFile) {
		String[] command = new String[fileList.size() + 2];
		command[0] = "cmd.exe";
		command[1] = "/c";

		for (int i = 0; i < fileList.size(); i++) {
			command[i + 2] = "type " + fileList.get(i).replace("/", "\\") + " >> " + finalFile;
		}
		return command;
	}
}
