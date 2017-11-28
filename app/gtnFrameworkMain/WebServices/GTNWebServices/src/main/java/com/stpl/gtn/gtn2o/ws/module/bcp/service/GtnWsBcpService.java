package com.stpl.gtn.gtn2o.ws.module.bcp.service;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.ws.bean.bcp.GtnWsBcpServiceBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.bcp.GtnWsBcpServiceRequest;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

@Service
public class GtnWsBcpService {
	@Autowired
	private GtnWsSqlService gtnWsSqlService;
	@Autowired
	private GtnWsBcpFileUploader bcpFileUploader;
	@Autowired
	private GtnWsBcpFileMerger bcpFileMerger;
	public GtnWsBcpService() {
		super();
	}


	private static final GtnWSLogger GTNLOGGER = GtnWSLogger.getGTNLogger(GtnWsBcpService.class);

	public void calculate(GtnUIFrameworkWebserviceRequest bcpServiceRequest) throws GtnFrameworkGeneralException {
		GTNLOGGER.info("Inside calculate method");
		GtnWsBcpServiceRequest gtnWsBcpServiceRequest = bcpServiceRequest.getGtnWsBcpServiceRequest();
		GtnWsBcpServiceBean gtnWsBcpServiceBean = gtnWsBcpServiceRequest.getGtnWsBcpServiceBean();
		String userId = gtnWsBcpServiceBean.getUserId();
		String sessionId = gtnWsBcpServiceBean.getSessionId();
		String finalFile = System.getProperty(GtnFrameworkCommonStringConstants.GTN_BASE_PATH)
				+ System.getProperty(GtnFrameworkCommonStringConstants.CUMULATIVE_FILE_PATH);
		boolean salesFlag = gtnWsBcpServiceBean.getSalesFlag();
		List<Object[]> peocedureResultList = gtnWsSqlService.getResultFromProcedure("PRC_GROWTH_CALCULATION",
				gtnWsBcpServiceBean.getProcedureInputs());


		try {
			if (!peocedureResultList.isEmpty()) {
				final List<String> fileList = new ArrayList<>();
				List<Closeable> fileOperationList = new ArrayList<>();
				final AtomicInteger atomicInteger = new AtomicInteger();
				GtnWsThreadLocalImpl threadLocalImpl = new GtnWsThreadLocalImpl(atomicInteger, fileList,
						fileOperationList);
				ExecutorService executorService = Executors.newFixedThreadPool(5);
				int count = 0;
				AtomicInteger aint = new AtomicInteger();
				List<Future> task = new ArrayList<>();
				List<Object[]> objectList = new ArrayList<>();
				Semaphore sema = new Semaphore(5);
				String lastCCP = StringUtils.EMPTY;
				boolean isProcessAllowed = false;

				for (Object[] object : peocedureResultList) {
					if (!String.valueOf(object[0]).equals(lastCCP)) {
						count++;
						lastCCP = String.valueOf(object[0]);
						isProcessAllowed = count % 100 == 0;
					}
					if (isProcessAllowed) {
						sema.acquire(1);
						task.add(executorService.submit(
								new GtnWsBcpMultiplicationJob(objectList, aint, sema, threadLocalImpl,
										gtnWsBcpServiceBean)));
						objectList = new ArrayList<>();
						isProcessAllowed = false;
						objectList.add(object);
					} else {
						objectList.add(object);
					}
				}

				if (count > 0) {
					task.add(executorService.submit(
							new GtnWsBcpMultiplicationJob(objectList, aint, sema, threadLocalImpl,
									gtnWsBcpServiceBean)));
				}
				executorService.shutdown();
				executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);

				String finalFileName = getFinalFileName(salesFlag, userId, sessionId, finalFile);
				mergeFiles(fileList, finalFileName, fileOperationList, gtnWsBcpServiceBean.getFolderName());
				uploadFiles(finalFileName, bcpServiceRequest);
			}

		} catch (Exception ex) {
			throw new GtnFrameworkGeneralException("Exception occure in bcp processing.", ex);
		}
	}

	public void mergeFiles(List<String> fileList, String finalFile, List<Closeable> fileOperationList,
			String folderName) throws IOException, InterruptedException {
		bcpFileMerger.mergeFiles(fileList, finalFile, fileOperationList, folderName);
	}
	public void uploadFiles(String finalFileParam, GtnUIFrameworkWebserviceRequest bcpServiceRequest)
			throws IOException, InterruptedException {
		bcpFileUploader.uploadFiles(finalFileParam, bcpServiceRequest);
	}

	public String getFinalFileName(boolean salesFlag, String userId, String sessionId, String finalFile) {
		String finalFileName = finalFile;
		if (salesFlag) {
			finalFileName += "/Cumulative_Multiplication_sales" + userId + "_" + sessionId + ".csv";
		} else {
			finalFileName += "/Cumulative_Multiplication_disc" + userId + "_" + sessionId + ".csv";
		}
		return finalFileName;
	}
}


