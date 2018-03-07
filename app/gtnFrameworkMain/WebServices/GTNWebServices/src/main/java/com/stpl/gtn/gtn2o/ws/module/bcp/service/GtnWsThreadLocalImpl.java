package com.stpl.gtn.gtn2o.ws.module.bcp.service;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.stpl.gtn.gtn2o.ws.GtnFileNameUtils;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnWsThreadLocalImpl extends ThreadLocal {
	public static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsThreadLocalImpl.class);
	private final AtomicInteger atomicInteger;
	private final List<String> fileList;
	private final List<Closeable> fileOperationList;

	public GtnWsThreadLocalImpl(final AtomicInteger atomicInteger, final List<String> fileList,
			final List<Closeable> fileOperationList) {
		this.atomicInteger = atomicInteger;
		this.fileList = new ArrayList<>(fileList);
		this.fileOperationList = new ArrayList<>(fileOperationList);
	}

	@Override
	protected BufferedWriter initialValue() {
		try {
			String finalPath = System.getProperty(GtnFrameworkCommonStringConstants.GTN_DATA_PATH) + "/Cumulative_Logic/";
                        LOGGER.info("final path in initialValue method ========"+finalPath);
			String fileName = finalPath + "File" + atomicInteger.getAndIncrement() + ".csv";
			fileList.add(fileName);
                        LOGGER.info("file name in initial value ======"+fileName);
                        LOGGER.info("fileList size in initialValue ======"+fileList.size());
			FileWriter fileWriter = GtnFileNameUtils.getFileWriter(fileName);
			LOGGER.info(Thread.currentThread().getName());
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			fileOperationList.add(fileWriter);
			fileOperationList.add(bufferedWriter);
			return bufferedWriter;

		} catch (IOException ex) {
			LOGGER.error("Exception in Thread local", ex);
		}
		return null;
	}

    public List<String> getFileList() {
        return fileList != null ? new ArrayList<>(fileList)
				: fileList;
    }

    public List<Closeable> getFileOperationList() {
        return fileOperationList != null ? new ArrayList<>(fileOperationList)
				: fileOperationList;
    }
    
}
