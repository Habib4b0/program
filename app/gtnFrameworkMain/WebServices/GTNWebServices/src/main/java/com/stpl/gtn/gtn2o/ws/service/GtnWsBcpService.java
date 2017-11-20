package com.stpl.gtn.gtn2o.ws.service;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang.StringUtils;

import com.stpl.gtn.gtn2o.ws.GtnFileNameUtils;
import com.stpl.gtn.gtn2o.ws.bean.bcp.GtnWsBcpServiceBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.bcp.GtnWsBcpServiceRequest;


public class GtnWsBcpService {
	public GtnWsBcpService() {
		super();
	}

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsBcpService.class);

	public void calculate(GtnUIFrameworkWebserviceRequest bcpServiceRequest) throws GtnFrameworkGeneralException {

		GtnWsBcpServiceRequest gtnWsBcpServiceRequest = bcpServiceRequest.getGtnWsBcpServiceRequest();
		GtnWsBcpServiceBean gtnWsBcpServiceBean = gtnWsBcpServiceRequest.getGtnWsBcpServiceBean();
		String userId = gtnWsBcpServiceBean.getUserId();
		String sessionId = gtnWsBcpServiceBean.getSessionId();
		String finalFile = gtnWsBcpServiceBean.getCumulativeFilePath();
		boolean salesFlag = gtnWsBcpServiceBean.getSalesFlag();
		List<Object[]> peocedureResultList = gtnWsBcpServiceBean.getPeocedureResultList();
		final List<String> fileList = new ArrayList<>();

		List<Closeable> fileOperationList = new ArrayList<>();

		final AtomicInteger atomicInteger = new AtomicInteger();
		ThreadLocalImpl threadLocalImpl = new ThreadLocalImpl(atomicInteger, fileList, fileOperationList,
				gtnWsBcpServiceBean);

		ExecutorService executorService = Executors.newFixedThreadPool(5);

		int count = 0;
		AtomicInteger aint = new AtomicInteger();
		List<Future> task = new ArrayList<>();

		try {

			List<Object[]> objectList = new ArrayList<>();
			Semaphore sema = new Semaphore(5);
			String lastCCP = StringUtils.EMPTY;
			boolean isProcessAllowed = false;
			if (!peocedureResultList.isEmpty()) {
				for (Object[] object : peocedureResultList) {
					if (!String.valueOf(object[0]).equals(lastCCP)) {
						count++;
						lastCCP = String.valueOf(object[0]);
						isProcessAllowed = count % 100 == 0;
					}
					if (isProcessAllowed) {
						sema.acquire(1);
						task.add(executorService.submit(
								new MultiplicationJob(objectList, aint, sema, threadLocalImpl, gtnWsBcpServiceBean)));
						objectList = new ArrayList<>();
						isProcessAllowed = false;
						objectList.add(object);
					} else {
						objectList.add(object);
					}
				}

				if (count > 0) {
					task.add(executorService.submit(
							new MultiplicationJob(objectList, aint, sema, threadLocalImpl, gtnWsBcpServiceBean)));
				}
				executorService.shutdown();
				executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);

				finalFile += "/Cumulative_Multiplication_disc" + userId + "_" + sessionId + ".csv";
				if (salesFlag) {
					finalFile += "/Cumulative_Multiplication_sales" + userId + "_" + sessionId + ".csv";
				}

				mergeFiles(fileList, finalFile, fileOperationList, gtnWsBcpServiceBean.getFolderName());
				uploadFiles(finalFile, bcpServiceRequest);
			}

		} catch (Exception ex) {
			throw new GtnFrameworkGeneralException("Exception occure in bcp processing.", ex);
		}
	}

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
		String folderName = gtnWsBcpServiceBean.getFolderName();

		String finalFile = finalFileParam;
		String serverName = gtnWsBcpServiceBean.getServerName();
		String schemaName = gtnWsBcpServiceBean.getSchemaName();
		String userName = gtnWsBcpServiceBean.getUserName();
		String password = gtnWsBcpServiceBean.getPassword();
		ProcessBuilder builder = null;
		List<String> fileList = new ArrayList<>();
		String logPath = gtnWsBcpServiceBean.getFolderName() + "/Cumulative_Multiplication_" + userId + "_" + sessionId
				+ ".log";

		newTableName = tableName + userId + "_" + sessionId + "_" + currentDate;

		if (System.getProperty("os.name").toLowerCase().contains("windows")) {
			finalFile = finalFile.replace("/", "\\");
			logPath = logPath.replace("/", "\\");

			LOGGER.info("bcp " + newTableName + " IN " + finalFile + " -c -d " + schemaName + " -t , -S " + serverName
					+ " -U " + userName + " -P \"" + password + "\" > " + logPath);
			builder = new ProcessBuilder("cmd.exe", "/c",
					"bcp " + newTableName + " IN " + finalFile + " -c -d" + schemaName + " -t , -S " + serverName
							+ " -U " + userName + " -P \"" + password + "\" > " + logPath);
		} else {
			query = newTableName + " IN ";
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
			LOGGER.info("bcp ====" + strb.toString());
			File shellFile = GtnFileNameUtils.getFile(folderName + File.separator + "exec.sh");
			fileList.add(shellFile.getAbsolutePath());
			try (FileOutputStream outShell = GtnFileNameUtils.getFileOutputStream(shellFile)) {
				outShell.write(strb.toString().getBytes());
				outShell.flush();
			}
			boolean isExecutable = shellFile.setExecutable(true, false);
			boolean isWritable = shellFile.setWritable(true, false);
			boolean isReadable = shellFile.setReadable(true, false);
			LOGGER.debug(Boolean.toString(isExecutable) + isWritable + isReadable);
			builder = new ProcessBuilder(shellFile.getAbsolutePath());
			File dir = GtnFileNameUtils.getFile(folderName);
			if (!dir.exists()) {
				dir.mkdir();
				isExecutable = dir.setExecutable(true, false);
				isWritable = dir.setWritable(true, false);
				isReadable = dir.setReadable(true, false);
				LOGGER.debug(Boolean.toString(isExecutable) + isWritable + isReadable);
			}
			builder.directory(dir);

		}
		Process p = builder.start();
		p.waitFor();
		fileList.add(finalFile);
		fileList.add(logPath);
		for (String fileName : fileList) {
			Files.delete(GtnFileNameUtils.getPath(fileName));
		}

		LOGGER.info("Upload Complete");
	}

	/**
	 *
	 * @param fileList
	 * @param finalFile
	 * @param fileOperationList
	 * @throws IOException
	 * @throws java.lang.InterruptedException
	 */
	public void mergeFiles(List<String> fileList, String finalFile, List<Closeable> fileOperationList,
			String folderName) throws IOException, InterruptedException {

		Path path = GtnFileNameUtils.getPath(finalFile.substring(0, finalFile.lastIndexOf('/')));
		if (!Files.exists(path)) {
			Files.createDirectories(path);
		}

		File file = GtnFileNameUtils.getFile(finalFile);
		boolean isCreated = file.createNewFile();
		LOGGER.info(Boolean.toString(isCreated));
		long time = System.currentTimeMillis();
		String[] command;
		ProcessBuilder builder = null;
		if (System.getProperty("os.name").toLowerCase().contains("windows")) {

			command = new String[fileList.size() + 2];
			command[0] = "cmd.exe";
			command[1] = "/c";

			for (int i = 0; i < fileList.size(); i++) {
				command[i + 2] = "type " + fileList.get(i).replace("/", "\\") + " >> " + finalFile;
			}
			builder = new ProcessBuilder(command);
			Process p = builder.start();
			p.waitFor();

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
			LOGGER.debug(Boolean.toString(isExecutable) + isWritable + isReadable);
			builder = new ProcessBuilder();
			builder.command(shellFile.getAbsolutePath());
			Process p = builder.start();
			p.waitFor();
			fileList.add(shellFile.getAbsolutePath());
		}

		for (Closeable closeable : fileOperationList) {
			closeable.close();
		}

		for (String fileName : fileList) {
			Files.delete(GtnFileNameUtils.getPath(fileName));
		}

		LOGGER.info("Merge Time: " + (System.currentTimeMillis() - time));

	}

	class MultiplicationJob implements Runnable {

		List<Object[]> lineList;
		final AtomicInteger processedLineCount;
		final Semaphore semaphore;
		final ThreadLocalImpl threadLocalImpl;
		final GtnWsBcpServiceBean gtnWsBcpServiceBean;

		public MultiplicationJob(List<Object[]> lineList, AtomicInteger processedLineCount, Semaphore semaphore,
				ThreadLocalImpl threadLocalImpl, GtnWsBcpServiceBean gtnWsBcpServiceBean) {
			this.lineList = lineList;
			this.processedLineCount = processedLineCount;
			this.semaphore = semaphore;
			this.threadLocalImpl = threadLocalImpl;
			this.gtnWsBcpServiceBean = gtnWsBcpServiceBean;
		}

		@Override
		public void run() {

			try {
				StringBuilder updatedLine = new StringBuilder();
				if ("Rolling Annual Trend".equals(gtnWsBcpServiceBean.getMethodology())) {
					rollingAnnualTrendDataFromList(updatedLine, lineList, gtnWsBcpServiceBean.getSalesFlag());
				} else if ("National Assumptions".equals(gtnWsBcpServiceBean.getTabName())) {
					writeDataFromListForNA(updatedLine, lineList);
				} else {
					writeDataFromList(updatedLine, lineList, gtnWsBcpServiceBean.getSalesFlag());
				}
				BufferedWriter bw = (BufferedWriter) threadLocalImpl.get();
				bw.write(updatedLine.toString());
				bw.flush();

				processedLineCount.set(processedLineCount.get() + lineList.size());
				lineList.clear();
				lineList = null;

			} catch (Exception ex) {
				LOGGER.error("Exception during executing multiplication thread.", ex);
			} finally {
				semaphore.release(1);
			}
		}

		public void writeDataFromListForNA(StringBuilder updatedLine, List<Object[]> lineList) {
			/** calculating for NA **/
			BigDecimal[] lastValue = new BigDecimal[lineList.get(0).length - 3];
			for (int i = 0; i < lastValue.length; i++) {
				lastValue[i] = BigDecimal.valueOf(1.0);

			}
			String lastCCP = StringUtils.EMPTY;
			int ndcIndex = 0;
			int priceTypeIndex = 8;
			for (Object[] sourceLine : lineList) {
				BigDecimal[] currentValue = new BigDecimal[lineList.get(0).length - 3];
				System.arraycopy(sourceLine, 2, currentValue, 0, lineList.get(0).length - 3);
				BigDecimal[] newValue = new BigDecimal[lineList.get(0).length - 3];
				if (lastCCP.equals(String.valueOf(sourceLine[ndcIndex]) + String.valueOf(sourceLine[priceTypeIndex]))) {
					newValue[0] = currentValue[0].multiply(lastValue[0]).setScale(15, RoundingMode.FLOOR);
					newValue[1] = currentValue[1].multiply(lastValue[1]).setScale(15, RoundingMode.FLOOR);
					newValue[2] = currentValue[2].multiply(lastValue[2]).setScale(15, RoundingMode.FLOOR);
					newValue[3] = currentValue[3].multiply(lastValue[3]).setScale(15, RoundingMode.FLOOR);
					newValue[4] = currentValue[4].multiply(lastValue[4]).setScale(15, RoundingMode.FLOOR);
					newValue[5] = currentValue[5].multiply(lastValue[5]).setScale(15, RoundingMode.FLOOR);

					updatedLine.append(sourceLine[ndcIndex]).append(",").append(sourceLine[1]).append(",")
							.append(newValue[0].toPlainString()).append(",").append(newValue[1].toPlainString())
							.append(",").append(newValue[2].toPlainString()).append(",")
							.append(newValue[3].toPlainString()).append(",").append(newValue[4].toPlainString())
							.append(",").append(newValue[5].toPlainString()).append(",")
							.append(String.valueOf(sourceLine[priceTypeIndex])).append(System.lineSeparator());
				} else {
					for (int i = 0; i < lastValue.length; i++) {
						lastValue[i] = BigDecimal.valueOf(1.0);
					}
					lastCCP = String.valueOf(sourceLine[ndcIndex]) + String.valueOf(sourceLine[priceTypeIndex]);
					newValue[0] = currentValue[0].multiply(lastValue[0]).setScale(15, RoundingMode.FLOOR);
					newValue[1] = currentValue[1].multiply(lastValue[1]).setScale(15, RoundingMode.FLOOR);
					newValue[2] = currentValue[2].multiply(lastValue[2]).setScale(15, RoundingMode.FLOOR);
					newValue[3] = currentValue[3].multiply(lastValue[3]).setScale(15, RoundingMode.FLOOR);
					newValue[4] = currentValue[4].multiply(lastValue[4]).setScale(15, RoundingMode.FLOOR);
					newValue[5] = currentValue[5].multiply(lastValue[5]).setScale(15, RoundingMode.FLOOR);
					updatedLine.append(sourceLine[ndcIndex]).append(",").append(sourceLine[1]).append(",")
							.append(newValue[0].toPlainString()).append(",").append(newValue[1].toPlainString())
							.append(",").append(newValue[2].toPlainString()).append(",")
							.append(newValue[3].toPlainString()).append(",").append(newValue[4].toPlainString())
							.append(",").append(newValue[5].toPlainString()).append(",")
							.append(String.valueOf(sourceLine[priceTypeIndex])).append(System.lineSeparator());
				}
				lastValue = newValue;
			}
		}

		public void writeDataFromList(StringBuilder updatedLine, List<Object[]> lineList, boolean salesFlag) {
			BigDecimal lastValue = BigDecimal.valueOf(1.0);
			String lastCCP = StringUtils.EMPTY;

			for (Object[] sourceLine : lineList) {
				BigDecimal currentValue;
				if (salesFlag) {
					currentValue = new BigDecimal(String.valueOf(sourceLine[2]));
				} else {
					currentValue = new BigDecimal(String.valueOf(sourceLine[3]));
				}
				BigDecimal newValue;

				if (lastCCP.equals(String.valueOf(sourceLine[0]))) {
					newValue = currentValue.multiply(lastValue).setScale(15, RoundingMode.FLOOR);
					if (salesFlag) {
						updatedLine.append(sourceLine[0]).append(",").append(sourceLine[1]).append(",")
								.append(newValue.toPlainString()).append(System.lineSeparator());
					} else {

						updatedLine.append(sourceLine[0]).append(",").append(sourceLine[1]).append(",")
								.append(sourceLine[2]).append(",").append(newValue.toPlainString()).append(",")
								.append(sourceLine[5]).append(System.lineSeparator());
					}

				} else {
					lastValue = BigDecimal.ONE;
					lastCCP = String.valueOf(sourceLine[0]);
					newValue = currentValue.multiply(lastValue).setScale(15, RoundingMode.FLOOR);
					if (salesFlag) {
						updatedLine.append(String.valueOf(sourceLine[0])).append(",").append(sourceLine[1]).append(",")
								.append(newValue.toPlainString()).append(System.lineSeparator());
					} else {
						updatedLine.append(String.valueOf(sourceLine[0])).append(",").append(sourceLine[1]).append(",")
								.append(sourceLine[2]).append(",").append(newValue.toPlainString()).append(",")
								.append(sourceLine[5]).append(System.lineSeparator());
					}
				}

				lastValue = newValue;

			}
		}

		public void rollingAnnualTrendDataFromList(StringBuilder updatedLine, List<Object[]> lineList,
				boolean salesFlag) {
			BigDecimal lastValue = BigDecimal.valueOf(1.0);
			String lastCCP = StringUtils.EMPTY;
			String lastPeriod = StringUtils.EMPTY;
			String period;

			for (Object[] sourceLine : lineList) {
				BigDecimal currentValue;
				if (salesFlag) {
					currentValue = new BigDecimal(String.valueOf(sourceLine[2]));
					period = String.valueOf(sourceLine[3]);
				} else {
					currentValue = new BigDecimal(String.valueOf(sourceLine[3]));
					period = String.valueOf(sourceLine[4]);
				}
				BigDecimal newValue;

				if (lastCCP.equals(String.valueOf(sourceLine[0])) && lastPeriod.equals(period)
						|| lastCCP.equals(String.valueOf(sourceLine[0])) && period.length() > 1) {
					newValue = currentValue.multiply(lastValue).setScale(15, RoundingMode.FLOOR);
					salesFlagCheck(salesFlag, sourceLine, newValue, updatedLine);

				} else {
					lastValue = BigDecimal.ONE;
					lastCCP = String.valueOf(sourceLine[0]);
					lastPeriod = period;
					newValue = currentValue.multiply(lastValue).setScale(15, RoundingMode.FLOOR);
					salesFlagCheck(salesFlag, sourceLine, newValue, updatedLine);
				}

				lastValue = newValue;

			}
		}

		private void salesFlagCheck(boolean salesFlag, Object[] sourceLine, BigDecimal newValue,
				StringBuilder updatedLine) {
			if (salesFlag) {
				updatedLine.append(sourceLine[0]).append(",").append(sourceLine[1]).append(",")
						.append(newValue.toPlainString()).append(System.lineSeparator());
			} else {
				updatedLine.append(sourceLine[0]).append(",").append(sourceLine[1]).append(",").append(sourceLine[2])
						.append(",").append(newValue.toPlainString()).append(",").append(sourceLine[5])
						.append(System.lineSeparator());
			}
		}
	}

}

class ThreadLocalImpl extends ThreadLocal {
	public static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(ThreadLocalImpl.class);
	final AtomicInteger atomicInteger;
	final List<String> fileList;
	final List<Closeable> fileOperationList;
	final String userId;
	final String sessionId;
	final GtnWsBcpServiceBean gtnWsBcpServiceBean;

	public ThreadLocalImpl(final AtomicInteger atomicInteger, final List<String> fileList,
			final List<Closeable> fileOperationList, final GtnWsBcpServiceBean gtnWsBcpServiceBean) {
		this.atomicInteger = atomicInteger;
		this.fileList = fileList;
		this.fileOperationList = fileOperationList;
		this.gtnWsBcpServiceBean = gtnWsBcpServiceBean;
		this.userId = gtnWsBcpServiceBean.getUserId();
		this.sessionId = gtnWsBcpServiceBean.getSessionId();
	}

	@Override
	protected BufferedWriter initialValue() {
		try {
			String finalPath = gtnWsBcpServiceBean.getCumulativeFilePath() + "/Cumulative_Logic/";
			String fileName = finalPath + "File" + atomicInteger.getAndIncrement() + ".csv";
			fileList.add(fileName);
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

}
