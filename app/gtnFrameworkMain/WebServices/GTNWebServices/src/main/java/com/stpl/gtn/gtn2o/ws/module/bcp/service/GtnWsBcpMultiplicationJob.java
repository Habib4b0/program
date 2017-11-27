package com.stpl.gtn.gtn2o.ws.module.bcp.service;

import java.io.BufferedWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang.StringUtils;

import com.stpl.gtn.gtn2o.ws.bean.bcp.GtnWsBcpServiceBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnWsBcpMultiplicationJob implements Runnable {
	private static final GtnWSLogger GTNLOGGER = GtnWSLogger.getGTNLogger(GtnWsBcpMultiplicationJob.class);

	private List<Object[]> lineList;
	private final AtomicInteger processedLineCount;
	private final Semaphore semaphore;
	private final GtnWsThreadLocalImpl threadLocalImpl;
	private final GtnWsBcpServiceBean gtnWsBcpServiceBean;

	public GtnWsBcpMultiplicationJob(List<Object[]> lineList, AtomicInteger processedLineCount, Semaphore semaphore,
			GtnWsThreadLocalImpl threadLocalImpl, GtnWsBcpServiceBean gtnWsBcpServiceBean) {
		this.lineList = new ArrayList<>(lineList);
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
			GTNLOGGER.error("Exception during executing multiplication thread.", ex);
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
						.append(newValue[0].toPlainString()).append(",").append(newValue[1].toPlainString()).append(",")
						.append(newValue[2].toPlainString()).append(",").append(newValue[3].toPlainString()).append(",")
						.append(newValue[4].toPlainString()).append(",").append(newValue[5].toPlainString()).append(",")
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
						.append(newValue[0].toPlainString()).append(",").append(newValue[1].toPlainString()).append(",")
						.append(newValue[2].toPlainString()).append(",").append(newValue[3].toPlainString()).append(",")
						.append(newValue[4].toPlainString()).append(",").append(newValue[5].toPlainString()).append(",")
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

	public void rollingAnnualTrendDataFromList(StringBuilder updatedLine, List<Object[]> lineList, boolean salesFlag) {
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
