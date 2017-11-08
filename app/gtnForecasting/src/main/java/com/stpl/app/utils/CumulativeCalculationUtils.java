package com.stpl.app.utils;

import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.NATIONAL_ASSUMPTIONS;
import static com.stpl.app.utils.Constants.LabelConstants.SALES_PROJ;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang.StringUtils;

import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.ui.util.StandaloneParser;
import com.stpl.ifs.util.CommonUtil;
import com.stpl.ifs.util.GtnFileUtil;
import com.stpl.ifs.util.constants.ForecastingConstants;

public class CumulativeCalculationUtils {

	public List<Object[]> peocedureResultList;
	public static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger
			.getLogger(CumulativeCalculationUtils.class);
	private String tabName = StringUtils.EMPTY;
	boolean salesFlag = false;
	private StandaloneParser credentials = StandaloneParser.getInstance();
	private String finalFile = System.getProperty(Constant.CUMULATIVE_FILE_PATH);
	private String folderName = System.getProperty(Constant.CUMULATIVE_FILE_PATH);
	String methodology = StringUtils.EMPTY;
	private String userId = StringUtils.EMPTY;
	private String sessionId = StringUtils.EMPTY;
	String tableName;

	public CumulativeCalculationUtils(List<Object[]> peocedureResultList, final String userId, final String sessionId,
			final String methodology, final String tabName, final String tableName) {
		this.peocedureResultList = peocedureResultList;
		this.tabName = tabName;
		this.tableName = tableName;
		this.methodology = methodology;
		this.userId = userId;
		this.sessionId = sessionId;
		init();
	}

	private void init() {
		salesFlag = SALES_PROJ.getConstant().equals(tabName) || NATIONAL_ASSUMPTIONS.getConstant().equals(tabName)
				|| "Returns".equals(tabName);
		calculate(String.valueOf(userId), String.valueOf(sessionId));

	}

	private void calculate(final String userId, final String sessionId) {

		final List<String> fileList = new ArrayList<>();

		List<Closeable> fileOperationList = new ArrayList<>();

		final AtomicInteger atomicInteger = new AtomicInteger();
		ThreadLocalImpl<BufferedWriter> threadLocalImpl = new ThreadLocalImpl<>(atomicInteger, fileList,
				fileOperationList, userId, sessionId);

		long startTime = System.currentTimeMillis();
		ExecutorService executorService = Executors.newFixedThreadPool(NumericConstants.FIVE);

		int count = 0;
		AtomicInteger aint = new AtomicInteger();
		List<Future> task = new ArrayList<>();

		try {

			List<Object[]> objectList = new ArrayList<>();
			Semaphore sema = new Semaphore(NumericConstants.FIVE);
			String lastCCP = StringUtils.EMPTY;
			boolean isProcessAllowed = false;
			if (peocedureResultList.size() != 0) {
				for (Object[] object : peocedureResultList) {
					if (!String.valueOf(object[0]).equals(lastCCP)) {
						count++;
						lastCCP = String.valueOf(object[0]);
						isProcessAllowed = count % NumericConstants.HUNDRED == 0;
					}

					if (!isProcessAllowed) {
						objectList.add(object);
					}

					if (isProcessAllowed) {

						sema.acquire(1);
						task.add(
								executorService.submit(new MultiplicationJob(objectList, aint, sema, threadLocalImpl)));
						objectList = new ArrayList<>();
						isProcessAllowed = false;
						objectList.add(object);

					}

				}

				if (count > 0) {
					task.add(executorService.submit(new MultiplicationJob(objectList, aint, sema, threadLocalImpl)));
				}

				executorService.shutdown();
				executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);

				if (salesFlag) {
					finalFile += "/Cumulative_Multiplication_sales" + userId + "_" + sessionId + ".csv";
				} else {
					finalFile += "/Cumulative_Multiplication_disc" + userId + "_" + sessionId + ".csv";
				}

				mergeFiles(fileList, finalFile, fileOperationList);
				uploadFiles(finalFile);
			}
			long end = System.currentTimeMillis();
			LOGGER.info("Time Taken  : " + (end - startTime));
			LOGGER.info("Task size   : " + task.size());
			LOGGER.info("Total Count : " + count);
			LOGGER.info("Total Lines : " + aint.get());
		} catch (Exception ex) {
			LOGGER.error(ex);
		} finally {
			long end = System.currentTimeMillis();
			LOGGER.info("Time Taken  : " + (end - startTime));
			LOGGER.info("Task size   : " + task.size());
			LOGGER.info("Total Count : " + count);
			LOGGER.info("Total Lines : " + aint.get());
		}

	}

	private void uploadFiles(String finalFile) throws IOException, InterruptedException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		String newTableName = StringUtils.EMPTY;
		String query = StringUtils.EMPTY;
		ProcessBuilder builder = null;
		List<String> fileList = new ArrayList<>();
		String logPath = folderName + "/Cumulative_Multiplication_" + userId + "_" + sessionId + ".log";
		if (salesFlag) {
			newTableName = tableName + userId + "_" + sessionId + "_" + UiUtils.getDate();
		} else {
			newTableName = tableName + userId + "_" + sessionId + "_" + UiUtils.getDate();
		}
		if (System.getProperty("os.name").toLowerCase().contains("windows")) {
			finalFile = finalFile.replace("/", "\\");
			logPath = logPath.replace("/", "\\");

			LOGGER.info("bcp " + newTableName + " IN " + finalFile + " -c -d " + credentials.getSchema() + " -t , -S "
					+ credentials.getServer() + " -U " + credentials.getUser() + " -P \"" + credentials.getPassword()
					+ "\" > " + logPath);
			builder = new ProcessBuilder("cmd.exe", "/c",
					"bcp " + newTableName + " IN " + finalFile + " -c -d" + credentials.getSchema() + " -t , -S "
							+ credentials.getServer() + " -U " + credentials.getUser() + " -P \""
							+ credentials.getPassword() + "\" > " + logPath);
		} else {
			query = newTableName + " IN ";
			StringBuilder strb = new StringBuilder();
			strb.append(System.getProperty("bcp.location"));
			strb.append(" ");
			strb.append(query);
			strb.append(finalFile);
			strb.append(" -c ");
			strb.append("-d ");
			strb.append(credentials.getSchema());
			strb.append(" -t ");
			strb.append(", ");
			strb.append("-S ");
			strb.append(credentials.getServer());
			strb.append(" -U ");
			strb.append(credentials.getUser());
			strb.append(" -P ");
			strb.append("'").append(credentials.isIsPasswordEncrypted()
					? getDecryptedPassword(credentials.getPassword()) : credentials.getPassword()).append("'");
			strb.append(">");
			strb.append(logPath);
			File shellFile = CommonUtil.getFilePath(folderName + File.separator + "exec.sh");
			fileList.add(shellFile.getAbsolutePath());
			try (FileOutputStream outShell = GtnFileUtil.getFileOutputStream(shellFile)) {
				outShell.write(strb.toString().getBytes());
				outShell.flush();
			}
			shellFile.setExecutable(true, false);
			shellFile.setWritable(true, false);
			shellFile.setReadable(true, false);

			builder = new ProcessBuilder(shellFile.getAbsolutePath());
			File dir = CommonUtil.getFilePath(folderName);
			if (!dir.exists()) {
				dir.mkdir();
				dir.setExecutable(true, false);
				dir.setWritable(true, false);
				dir.setReadable(true, false);
			}
			builder.directory(dir);

		}
		Process p = builder.start();
		p.waitFor();
		fileList.add(finalFile);
		fileList.add(logPath);
		for (String fileName : fileList) {
			Files.delete(GtnFileUtil.getPath(fileName));
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
	public void mergeFiles(List<String> fileList, String finalFile, List<Closeable> fileOperationList)
			throws IOException, InterruptedException {

		Path path = GtnFileUtil.getPath(finalFile.substring(0, finalFile.lastIndexOf("/")));
		if (!Files.exists(path)) {
			Files.createDirectories(path);
		}

		File file = CommonUtil.getFilePath(finalFile);
		file.createNewFile();

		long time = System.currentTimeMillis();
		String[] command;
		ProcessBuilder builder = null;
		if (System.getProperty("os.name").toLowerCase().contains("windows")) {

			command = new String[fileList.size() + NumericConstants.TWO];
			command[0] = "cmd.exe";
			command[1] = "/c";

			for (int i = 0; i < fileList.size(); i++) {
				command[i + NumericConstants.TWO] = "type " + fileList.get(i).replace("/", "\\") + " >> " + finalFile;
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
			File shellFile = CommonUtil
					.getFilePath(folderName + "/Cumulative_Logic" + File.separator + "Concat_exec.sh");

			try (FileOutputStream outShell = GtnFileUtil.getFileOutputStream(shellFile)) {
				outShell.write(strb.toString().getBytes());
				outShell.flush();
			}
			shellFile.setExecutable(true, false);
			shellFile.setWritable(true, false);
			shellFile.setReadable(true, false);

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
			Files.delete(GtnFileUtil.getPath(fileName));
		}

		LOGGER.info("Merge Time: " + (System.currentTimeMillis() - time));

	}

	class MultiplicationJob implements Runnable {

		List<Object[]> lineList;
		final AtomicInteger processedLineCount;
		final Semaphore semaphore;
		final ThreadLocalImpl<BufferedWriter> threadLocalImpl;

		public MultiplicationJob(List<Object[]> lineList, AtomicInteger processedLineCount, Semaphore semaphore,
				ThreadLocalImpl<BufferedWriter> threadLocalImpl) {
			this.lineList = lineList;
			this.processedLineCount = processedLineCount;
			this.semaphore = semaphore;
			this.threadLocalImpl = threadLocalImpl;
		}

		@Override
		public void run() {

			try {
				StringBuilder updatedLine = new StringBuilder();
				if ("Rolling Annual Trend".equals(methodology)) {
					rollingAnnualTrendDataFromList(updatedLine, lineList);
				} else if (NATIONAL_ASSUMPTIONS.getConstant().equals(tabName)) {
					writeDataFromListForNA(updatedLine, lineList);
				} else {
					writeDataFromList(updatedLine, lineList);
				}
				BufferedWriter bw = (BufferedWriter) threadLocalImpl.get();
				bw.write(updatedLine.toString());
				bw.flush();

				processedLineCount.set(processedLineCount.get() + lineList.size());
				lineList.clear();
				lineList = null;

			} catch (Exception ex) {
				LOGGER.error(ex);
			} finally {
				semaphore.release(1);
			}
		}

		public void writeDataFromListForNA(StringBuilder updatedLine, List<Object[]> lineList) {
			/** calculating for NA **/
			BigDecimal lastValue[] = new BigDecimal[lineList.get(0).length - 3];
			for (int i = 0; i < lastValue.length; i++) {
				lastValue[i] = new BigDecimal(1.0);

			}
			String lastCCP = StringUtils.EMPTY;
			int ndcIndex = NumericConstants.ZERO;
			int priceTypeIndex = NumericConstants.EIGHT;
			for (Object[] sourceLine : lineList) {
				BigDecimal currentValue[] = new BigDecimal[lineList.get(0).length - 3];
				System.arraycopy(sourceLine, NumericConstants.TWO, currentValue, NumericConstants.ZERO,
						lineList.get(0).length - 3);
				BigDecimal newValue[] = new BigDecimal[lineList.get(0).length - 3];
				if (lastCCP.equals(String.valueOf(sourceLine[ndcIndex]) + String.valueOf(sourceLine[priceTypeIndex]))) {
					newValue[0] = currentValue[0].multiply(lastValue[0]).setScale(NumericConstants.FIFTEEN,
							RoundingMode.FLOOR);
					newValue[1] = currentValue[1].multiply(lastValue[1]).setScale(NumericConstants.FIFTEEN,
							RoundingMode.FLOOR);
					newValue[2] = currentValue[2].multiply(lastValue[2]).setScale(NumericConstants.FIFTEEN,
							RoundingMode.FLOOR);
					newValue[3] = currentValue[3].multiply(lastValue[3]).setScale(NumericConstants.FIFTEEN,
							RoundingMode.FLOOR);
					newValue[4] = currentValue[4].multiply(lastValue[4]).setScale(NumericConstants.FIFTEEN,
							RoundingMode.FLOOR);
					newValue[5] = currentValue[5].multiply(lastValue[5]).setScale(NumericConstants.FIFTEEN,
							RoundingMode.FLOOR);

					updatedLine.append(sourceLine[ndcIndex]).append(",").append(sourceLine[1]).append(",")
							.append(newValue[0].toPlainString()).append(",").append(newValue[1].toPlainString())
							.append(",").append(newValue[2].toPlainString()).append(",")
							.append(newValue[3].toPlainString()).append(",").append(newValue[4].toPlainString())
							.append(",").append(newValue[5].toPlainString()).append(",")
							.append(String.valueOf(sourceLine[priceTypeIndex])).append(System.lineSeparator());
				} else {
					for (int i = 0; i < lastValue.length; i++) {
						lastValue[i] = new BigDecimal(1.0);
					}
					lastCCP = String.valueOf(sourceLine[ndcIndex]) + String.valueOf(sourceLine[priceTypeIndex]);
					newValue[0] = currentValue[0].multiply(lastValue[0]).setScale(NumericConstants.FIFTEEN,
							RoundingMode.FLOOR);
					newValue[1] = currentValue[1].multiply(lastValue[1]).setScale(NumericConstants.FIFTEEN,
							RoundingMode.FLOOR);
					newValue[2] = currentValue[2].multiply(lastValue[2]).setScale(NumericConstants.FIFTEEN,
							RoundingMode.FLOOR);
					newValue[3] = currentValue[3].multiply(lastValue[3]).setScale(NumericConstants.FIFTEEN,
							RoundingMode.FLOOR);
					newValue[4] = currentValue[4].multiply(lastValue[4]).setScale(NumericConstants.FIFTEEN,
							RoundingMode.FLOOR);
					newValue[5] = currentValue[5].multiply(lastValue[5]).setScale(NumericConstants.FIFTEEN,
							RoundingMode.FLOOR);
					updatedLine.append(sourceLine[ndcIndex]).append(",").append(sourceLine[1]).append(",")
							.append(newValue[0].toPlainString()).append(",").append(newValue[1].toPlainString())
							.append(",").append(newValue[2].toPlainString()).append(",")
							.append(newValue[3].toPlainString()).append(",").append(newValue[4].toPlainString())
							.append(",").append(newValue[5].toPlainString()).append(",")
							.append(String.valueOf(sourceLine[priceTypeIndex])).append(System.lineSeparator());
					;
				}
				lastValue = newValue;
			}
		}

		public void writeDataFromList(StringBuilder updatedLine, List<Object[]> lineList) {
			BigDecimal lastValue = new BigDecimal(1.0);
			String lastCCP = StringUtils.EMPTY;

			for (Object[] sourceLine : lineList) {
				BigDecimal currentValue;
				if (salesFlag) {
					currentValue = new BigDecimal(String.valueOf(sourceLine[NumericConstants.TWO]));
				} else {
					currentValue = new BigDecimal(String.valueOf(sourceLine[NumericConstants.THREE]));
				}
				BigDecimal newValue;

				if (lastCCP.equals(String.valueOf(sourceLine[0]))) {
					newValue = currentValue.multiply(lastValue).setScale(NumericConstants.FIFTEEN, RoundingMode.FLOOR);
					if (salesFlag) {
						updatedLine.append(sourceLine[0]).append(",").append(sourceLine[1]).append(",")
								.append(newValue.toPlainString()).append(System.lineSeparator());
					} else {

						updatedLine.append(sourceLine[0]).append(",").append(sourceLine[1]).append(",")
								.append(sourceLine[NumericConstants.TWO]).append(",").append(newValue.toPlainString())
								.append(",").append(sourceLine[5]).append(System.lineSeparator());
					}

				} else {
					lastValue = BigDecimal.ONE;
					lastCCP = String.valueOf(sourceLine[0]);
					newValue = currentValue.multiply(lastValue).setScale(NumericConstants.FIFTEEN, RoundingMode.FLOOR);
					if (salesFlag) {
						updatedLine.append(String.valueOf(sourceLine[0])).append(",").append(sourceLine[1]).append(",")
								.append(newValue.toPlainString()).append(System.lineSeparator());
					} else {
						updatedLine.append(String.valueOf(sourceLine[0])).append(",").append(sourceLine[1]).append(",")
								.append(sourceLine[NumericConstants.TWO]).append(",").append(newValue.toPlainString())
								.append(",").append(sourceLine[5]).append(System.lineSeparator());
					}
				}

				lastValue = newValue;

			}
		}

		public void rollingAnnualTrendDataFromList(StringBuilder updatedLine, List<Object[]> lineList) {
			BigDecimal lastValue = new BigDecimal(1.0);
			String lastCCP = StringUtils.EMPTY;
			String lastPeriod = StringUtils.EMPTY;
			String period;

			for (Object[] sourceLine : lineList) {
				BigDecimal currentValue;
				if (salesFlag) {
					currentValue = new BigDecimal(String.valueOf(sourceLine[NumericConstants.TWO]));
					period = String.valueOf(sourceLine[NumericConstants.THREE]);
				} else {
					currentValue = new BigDecimal(String.valueOf(sourceLine[NumericConstants.THREE]));
					period = String.valueOf(sourceLine[NumericConstants.FOUR]);
				}
				BigDecimal newValue;

				if (lastCCP.equals(String.valueOf(sourceLine[0])) && lastPeriod.equals(period)) {
					newValue = currentValue.multiply(lastValue).setScale(NumericConstants.FIFTEEN, RoundingMode.FLOOR);
					if (salesFlag) {
						updatedLine.append(sourceLine[0]).append(",").append(sourceLine[1]).append(",")
								.append(newValue.toPlainString()).append(System.lineSeparator());
					} else {
						updatedLine.append(sourceLine[0]).append(",").append(sourceLine[1]).append(",")
								.append(sourceLine[NumericConstants.TWO]).append(",").append(newValue.toPlainString())
								.append(",").append(sourceLine[5]).append(System.lineSeparator());
					}

				} else if (lastCCP.equals(String.valueOf(sourceLine[0])) && period.length() > 1) {
					newValue = currentValue.multiply(lastValue).setScale(NumericConstants.FIFTEEN, RoundingMode.FLOOR);
					if (salesFlag) {
						updatedLine.append(sourceLine[0]).append(",").append(sourceLine[1]).append(",")
								.append(newValue.toPlainString()).append(System.lineSeparator());
					} else {
						updatedLine.append(sourceLine[0]).append(",").append(sourceLine[1]).append(",")
								.append(sourceLine[NumericConstants.TWO]).append(",").append(newValue.toPlainString())
								.append(",").append(sourceLine[5]).append(System.lineSeparator());
					}

				} else {

					lastValue = BigDecimal.ONE;
					lastCCP = String.valueOf(sourceLine[0]);
					lastPeriod = period;
					newValue = currentValue.multiply(lastValue).setScale(NumericConstants.FIFTEEN, RoundingMode.FLOOR);
					if (salesFlag) {
						updatedLine.append(String.valueOf(sourceLine[0])).append(",").append(sourceLine[1]).append(",")
								.append(newValue.toPlainString()).append(System.lineSeparator());
					} else {
						updatedLine.append(String.valueOf(sourceLine[0])).append(",").append(sourceLine[1]).append(",")
								.append(sourceLine[NumericConstants.TWO]).append(",").append(newValue.toPlainString())
								.append(",").append(sourceLine[5]).append(System.lineSeparator());
					}

				}

				lastValue = newValue;

			}
		}

	}

	private static String getDecryptedPassword(String secret) throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		byte[] kbytes = "jaas is the way".getBytes();
		SecretKeySpec key = new SecretKeySpec(kbytes, ForecastingConstants.getPassword());
		BigInteger n = new BigInteger(secret, NumericConstants.SIXTEEN);
		byte[] encoding = n.toByteArray();
		if (encoding.length % NumericConstants.EIGHT != 0) {
			int length = encoding.length;
			int newLength = ((length / NumericConstants.EIGHT) + 1) * NumericConstants.EIGHT;
			int pad = newLength - length; // number of leading zero values
			byte[] old = encoding;
			encoding = new byte[newLength];
			for (int i = old.length - 1; i >= 0; i--) {
				encoding[i + pad] = old[i];
			}
			if (n.signum() == -1) {
				for (int i = 0; i < newLength - length; i++) {
					encoding[i] = (byte) -1;
				}
			}
		}
		Cipher cipher = Cipher.getInstance(ForecastingConstants.getPassword());
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] decode = cipher.doFinal(encoding);
		return new String(decode);
	}

}

class ThreadLocalImpl<T extends BufferedWriter> extends ThreadLocal {
	public static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(ThreadLocalImpl.class);
	final AtomicInteger atomicInteger;
	final List<String> fileList;
	final List<Closeable> fileOperationList;
	final String userId;
	final String sessionId;

	public ThreadLocalImpl(final AtomicInteger atomicInteger, final List<String> fileList,
			final List<Closeable> fileOperationList, final String userId, final String sessionId) {
		this.atomicInteger = atomicInteger;
		this.fileList = fileList;
		this.fileOperationList = fileOperationList;
		this.userId = userId;
		this.sessionId = sessionId;
	}

	@Override
	protected BufferedWriter initialValue() {
		try {
			String finalPath = System.getProperty(Constant.CUMULATIVE_FILE_PATH) + "/Cumulative_Logic/";
			String fileName = finalPath + "File" + atomicInteger.getAndIncrement() + ".csv";
			fileList.add(fileName);
			FileWriter fileWriter = GtnFileUtil.getFileWriter(fileName);
			LOGGER.info(Thread.currentThread().getName());
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			fileOperationList.add(fileWriter);
			fileOperationList.add(bufferedWriter);
			return bufferedWriter;

		} catch (IOException ex) {
			LOGGER.error(ex);
		}
		return null;
	}

}